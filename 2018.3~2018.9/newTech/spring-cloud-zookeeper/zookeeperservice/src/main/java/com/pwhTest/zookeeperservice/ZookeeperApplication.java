package com.pwhTest.zookeeperApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@EnableDiscoveryClient
@Configuration
@ComponentScan(basePackages = "com.pwhTest.zookeeperservice.controller,com.pwhTest.zookeeperservice.service")
public class ZookeeperApplication {

	public static void main(String[] args) {

		SpringApplication.run(ZookeeperApplication.class, args);
	}
}