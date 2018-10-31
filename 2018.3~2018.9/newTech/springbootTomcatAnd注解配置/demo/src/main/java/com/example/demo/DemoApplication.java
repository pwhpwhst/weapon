package com.example.demo;


import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages= {"com.example.demo.service"})
public class DemoApplication extends SpringBootServletInitializer{

    private AppStartedListener appStartedListener;

    public void onStartup(ServletContext servletContext) throws ServletException {
        System.out.println("application start up");
        appStartedListener = new AppStartedListener(servletContext);
        super.onStartup(servletContext);
    }


    @Override
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder builder) {
//        return application.sources(DemoApplication.class);
		return builder.sources(this.getClass()).listeners(this.getAppStartedListener());
    }

	public AppStartedListener getAppStartedListener() {
        return appStartedListener;
    }

	public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
