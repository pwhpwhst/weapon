package com.juvenxu.mvnbook.helloworld;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.ImportResource;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan(basePackages = "com.juvenxu.mvnbook.helloworld")
//@ImportResource("classpath:applicationContext-configuration.xml")

public class TestConfiguration{
    public TestConfiguration() {
        System.out.println("TestConfiguration����������ʼ��������");
    }


    //@Beanע��ע��bean,ͬʱ����ָ����ʼ�������ٷ���
//    @Bean(name="testBean",initMethod="start",destroyMethod="cleanUp")
//    @Scope("prototype")
//    public TestBean testBean() {
//        return new TestBean();
//   }


}