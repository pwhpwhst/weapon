package com.example.demo;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import com.example.demo.webServiceServer.impl.AppService;
import javax.xml.ws.Endpoint;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContext;

public class WebAppContextListener implements ApplicationListener<ApplicationStartedEvent> {
	
    private ServletContext servletContext;

    public WebAppContextListener(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public void contextInitialized(ServletContextEvent sce) {
		System.out.println("contextInitialized");
      String address = "http://127.0.0.1:8089/hubo/Webservice";
        //使用Endpoint类提供的publish方法发布WebService，发布时要保证使用的端口号没有被其他应用程序占用
        Endpoint.publish(address , new AppService());
        System.out.println("发布webservice成功!");
    }

    public void contextDestroyed(ServletContextEvent sce) {
    }

	 public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
        System.out.print("spring application start");
        this.contextInitialized(new ServletContextEvent(this.servletContext));
    }

}