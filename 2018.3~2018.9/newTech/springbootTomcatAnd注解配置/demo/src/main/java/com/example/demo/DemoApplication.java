package com.example.demo;


import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import com.example.demo.WebAppContextListener;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages= {"com.example.demo.controller","com.example.demo.service"})
public class DemoApplication extends SpringBootServletInitializer{


	private WebAppContextListener webAppContextListener;//其中含有webservice的服务端配置
    @Override
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder application) {
       return application.sources(DemoApplication.class).listeners(webAppContextListener);
 //		  return application.sources(DemoApplication.class);
    }


	public void onStartup(ServletContext servletContext) throws ServletException {
        webAppContextListener = new WebAppContextListener(servletContext);
        super.onStartup(servletContext);
    }

	public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
		
    }
}
