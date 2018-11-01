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
        //ʹ��Endpoint���ṩ��publish��������WebService������ʱҪ��֤ʹ�õĶ˿ں�û�б�����Ӧ�ó���ռ��
        Endpoint.publish(address , new AppService());
        System.out.println("����webservice�ɹ�!");
    }

    public void contextDestroyed(ServletContextEvent sce) {
    }

	 public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
        System.out.print("spring application start");
        this.contextInitialized(new ServletContextEvent(this.servletContext));
    }

}