package com.juvenxu.mvnbook.helloworld;
import com.juvenxu.mvnbook.helloworld.OutputService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import com.juvenxu.mvnbook.helloworld.OutputService;
import com.juvenxu.mvnbook.helloworld.OutputAction;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test{
	public static void main(String[] args){
//		Resource rs = new ClassPathResource("appContext.xml");
//		BeanFactory factory = new XmlBeanFactory(rs);

		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("appContext.xml");

//		OutputService outputService = (OutputService) factory.getBean("outputService");
//		System.out.println("service:"+outputService.output());


		OutputAction outputAction = (OutputAction) applicationContext.getBean("outputAction");
		System.out.println("action:"+outputAction.output());


	}
}