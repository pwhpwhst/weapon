package com.juvenxu.mvnbook.helloworld;
import com.juvenxu.mvnbook.helloworld.OutputService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import com.juvenxu.mvnbook.helloworld.OutputService;

public class Test{
	public static void main(String[] args){
		ApplicationContext context = new AnnotationConfigApplicationContext(TestConfiguration.class);

		OutputService outputService = (OutputService) context
		.getBean("outputService");

		outputService.output();

	}
}