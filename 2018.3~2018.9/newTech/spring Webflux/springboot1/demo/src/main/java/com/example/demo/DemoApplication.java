package com.example.demo;

import com.example.demo.service.TimeHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Bean;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.demo.service")
public class DemoApplication {

	@Autowired
	private TimeHandler timeHandler;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


	@Bean
	public RouterFunction<ServerResponse> timerRouter() {
		return route(GET("/time"), req -> timeHandler.getTime(req))
				.andRoute(GET("/date"), timeHandler::getDate)  // 这种方式相对于上一行更加简洁
				.andRoute(GET("/times"), timeHandler::sendTimePerSec); //实现每秒推送时间
	}

}
