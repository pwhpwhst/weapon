package com.pwhTest.zookeeperservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService{

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "sayHelloFallback")
    public String sayHello(String name) {
        return restTemplate.getForEntity("http://192.168.201.88:8800/hello?name=" + name, String.class).getBody();
//        return restTemplate.getForEntity("http://localhost:8810/hello?name=" + name, String.class).getBody();
	}
//http://localhost:8810/hello
    private String sayHelloFallback(String name) {
        return "service error";
    }
}