package com.juvenxu.mvnbook.helloworld;
import com.juvenxu.mvnbook.helloworld.OutputService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import com.juvenxu.mvnbook.helloworld.OutputService;
import com.juvenxu.mvnbook.helloworld.OutputAction;

public class Test{
	public static void main(String[] args){
		Resource rs = new ClassPathResource("appContext.xml");
		BeanFactory factory = new XmlBeanFactory(rs);

//		OutputService outputService = (OutputService) factory.getBean("OutputService");
		OutputAction outputAction = (OutputAction) factory.getBean("OutputAction");


		outputAction.output();

	}
}