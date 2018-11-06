package com.juvenxu.mvnbook.helloworld;
import com.juvenxu.mvnbook.helloworld.OutputService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import com.juvenxu.mvnbook.helloworld.IOutputService;
import com.juvenxu.mvnbook.helloworld.OutputAction;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test{
	public static void main(String[] args){

		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("appContext.xml");

		IOutputService outputService = (IOutputService) applicationContext.getBean("outputService");
		System.out.println("service:"+outputService.output());


	}
}