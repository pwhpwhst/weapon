package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.logging.slf4j.Slf4jImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.ApplicationContext;
import org.mybatis.spring.mapper.MapperFactoryBean;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;


import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;



@SpringBootApplication
@EnableSwagger2
@EnableTransactionManagement
@MapperScan(basePackages = {
        "com.pwhTest.SpringMybatisTest"
}, sqlSessionFactoryRef = "testSqlSessionFactoryBean")
public class DemoApplication {

    @Bean(name = "dataSource",destroyMethod = "close")
    public DataSource dataSource() throws SQLException {


        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/test");
        dataSource.setUsername("root");
        dataSource.setPassword("059030849");

        // 初始化连接大小
        dataSource.setInitialSize(1);
        // 连接池最大使用连接数量
        dataSource.setMaxActive(5);
        // 连接池最小空闲
        dataSource.setMinIdle(0);
        // 获取连接最大等待时间
        dataSource.setMaxWait(60000);
        dataSource.setValidationQuery("SELECT 1");
        //true-每次检测连接池是否可用
        dataSource.setTestOnBorrow(true);
        dataSource.setTestOnReturn(false);
        dataSource.setTestWhileIdle(true);
        // 间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
        dataSource.setTimeBetweenEvictionRunsMillis(60000);
        // 一个连接在池中最小生存的时间，单位是毫秒
        dataSource.setMinEvictableIdleTimeMillis(25200000);
        dataSource.setRemoveAbandoned(true);
        // 1800秒，也就是30分钟
        dataSource.setRemoveAbandonedTimeout(1800);
        // 关闭abanded连接时输出错误日志
        dataSource.setLogAbandoned(true);
        // 监控数据库
        dataSource.setFilters("mergeStat");

        dataSource.init();

        return dataSource;
    }


    @Bean(name = "testSqlSessionFactoryBean")
    public SqlSessionFactory sqlSessionFactoryBean(@Qualifier("dataSource") DataSource dataSource) throws Exception {
        org.apache.ibatis.session.Configuration config = new org.apache.ibatis.session.Configuration();
        config.setMapUnderscoreToCamelCase(true);
        config.setLogImpl(Slf4jImpl.class);

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setConfiguration(config);



        return sqlSessionFactoryBean.getObject();
    }


    @Bean
    public DataSourceTransactionManager getDataSourceTransactionManager(@Qualifier("dataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }



	@Bean
	public Docket newsApi() {
		Docket docket = new Docket(DocumentationType.SWAGGER_2);
		docket.enable(true);
		docket.apiInfo(apiInfo()).select().paths(PathSelectors.any()).build();
		return docket;
	}
 
	private ApiInfo apiInfo(){
		return new ApiInfoBuilder().title("Pwh接口测试平台")//设置接口文档的标题
				.description("Pwh 项目：")
	             .contact("pwh")//创建人
				.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
//		ApplicationContext context = new AnnotationConfigApplicationContext(DemoApplication.class);

//		UserMapper userDao = (UserMapper) context.getBean("userDao");
//
//        User user=userDao.getUser(2);
//		System.out.println("id:" + user.getId() + " name:" + user.getName() + " password:" + user.getPassword());
	}
}
