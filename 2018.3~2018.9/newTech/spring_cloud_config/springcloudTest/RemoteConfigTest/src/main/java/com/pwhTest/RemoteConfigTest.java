package com.pwhTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Configuration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

//@EnableDiscoveryClient
@Configuration
@EnableAutoConfiguration
@SpringBootApplication
public class RemoteConfigTest{
	public static void main(String args[]){
		SpringApplication.run(RemoteConfigTest.class, args);
	}
}




