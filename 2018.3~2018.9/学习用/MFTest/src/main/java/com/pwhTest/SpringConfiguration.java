package com.pwhTest;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.logging.slf4j.Slf4jImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = {
        "com.pwhTest.SpringMybatisTest"
}, sqlSessionFactoryRef = "testSqlSessionFactoryBean")
public class SpringConfiguration {

    @Bean(name = "dataSource",destroyMethod = "close")
    public DataSource dataSource() throws SQLException {

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/test");
        dataSource.setUsername("root");
        dataSource.setPassword("059030849");

        // ��ʼ�����Ӵ�С
        dataSource.setInitialSize(1);
        // ���ӳ����ʹ����������
        dataSource.setMaxActive(5);
        // ���ӳ���С����
        dataSource.setMinIdle(0);
        // ��ȡ�������ȴ�ʱ��
        dataSource.setMaxWait(60000);
        dataSource.setValidationQuery("SELECT 1");
        //true-ÿ�μ�����ӳ��Ƿ����
        dataSource.setTestOnBorrow(true);
        dataSource.setTestOnReturn(false);
        dataSource.setTestWhileIdle(true);
        // �����òŽ���һ�μ�⣬�����Ҫ�رյĿ������ӣ���λ�Ǻ���
        dataSource.setTimeBetweenEvictionRunsMillis(60000);
        // һ�������ڳ�����С�����ʱ�䣬��λ�Ǻ���
        dataSource.setMinEvictableIdleTimeMillis(25200000);
        dataSource.setRemoveAbandoned(true);
        // 1800�룬Ҳ����30����
        dataSource.setRemoveAbandonedTimeout(1800);
        // �ر�abanded����ʱ���������־
        dataSource.setLogAbandoned(true);
        // ������ݿ�
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
    
	@Bean(name = "userDao")
	public MapperFactoryBean getUserDao(@Qualifier("testSqlSessionFactoryBean") SqlSessionFactory sqlSessionFactory){
		MapperFactoryBean userDao = new MapperFactoryBean();
		userDao.setSqlSessionFactory(sqlSessionFactory);
		userDao.setMapperInterface(UserMapper.class);
		return userDao;
	}

	
}
