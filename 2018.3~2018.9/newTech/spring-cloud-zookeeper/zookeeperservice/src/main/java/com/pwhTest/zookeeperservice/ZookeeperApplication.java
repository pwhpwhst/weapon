package com.pwhTest.zookeeperApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class ZookeeperApplication {

	public static void main(String[] args) {
		System.out.println("fdsgdggdfg");
		SpringApplication.run(ZookeeperApplication.class, args);
	}
}