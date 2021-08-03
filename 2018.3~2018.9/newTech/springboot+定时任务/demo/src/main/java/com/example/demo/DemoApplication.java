package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling 
@SpringBootApplication(exclude=DataSourceAutoConfiguration.class)
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


    //3.添加定时任务
    @Scheduled(cron = "0/5 * * * * ?")
    private void configureTasks() {
        System.err.println("执行静态定时任务时间: " + System.currentTimeMillis());
    }

}
