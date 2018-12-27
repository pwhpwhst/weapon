package com.pwhTest.zookeeperApplication;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.logging.slf4j.Slf4jImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;
import com.pwhTest.zookeeperApplication.entity.ConditionWaitingEntity;
import com.pwhTest.zookeeperApplication.mapper.ConditionMapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@EnableDiscoveryClient
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = {
        "com.pwhTest.mapper"
}, sqlSessionFactoryRef = "testSqlSessionFactoryBean")
@ComponentScan(basePackages = "com.pwhTest.zookeeperservice.controller,com.pwhTest.zookeeperservice.service")
public class ZookeeperApplication {

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
    
    
	@Bean(name = "conditionDao")
	public MapperFactoryBean getConditionDao(@Qualifier("testSqlSessionFactoryBean") SqlSessionFactory sqlSessionFactory){
		MapperFactoryBean conditionDao = new MapperFactoryBean();
		conditionDao.setSqlSessionFactory(sqlSessionFactory);
		conditionDao.setMapperInterface(ConditionMapper.class);
		return conditionDao;
	}

	@Bean(name = "conditionWaitingDao")
	public MapperFactoryBean getConditionWaitingDao(@Qualifier("testSqlSessionFactoryBean") SqlSessionFactory sqlSessionFactory){
		MapperFactoryBean conditionWaitingDao = new MapperFactoryBean();
		conditionWaitingDao.setSqlSessionFactory(sqlSessionFactory);
		conditionWaitingDao.setMapperInterface(ConditionWaitingEntity.class);
		return conditionWaitingDao;
	}
	
	public static void main(String[] args) {

		SpringApplication.run(ZookeeperApplication.class, args);
		
	}
}