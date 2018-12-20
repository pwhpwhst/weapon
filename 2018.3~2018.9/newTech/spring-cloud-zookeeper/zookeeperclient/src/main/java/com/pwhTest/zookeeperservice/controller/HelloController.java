package com.pwhTest.zookeeperservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pwhTest.zookeeperservice.service.HelloService;
import com.pwhTest.zookeeperservice.service.ServiceFeign;

import java.util.List;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

@RestController
public class HelloController {

@Autowired
private DiscoveryClient discoveryClient;

    @Autowired
    private HelloService helloService;

    @Autowired
    private ServiceFeign serviceFeign;

    @RequestMapping(value = "hello")
    public String hello(@RequestParam String name) {
		System.out.println("hello");
        return helloService.sayHello(name);
    }

    @RequestMapping(value = "hello2")
    public String hello2(@RequestParam String name) {
		System.out.println("hello2");
        return serviceFeign.sayHello(name);
    }

    @RequestMapping(value = "hello3")
    public String hello3(@RequestParam String name) {
		System.out.println("hello3");
		List<ServiceInstance> list = discoveryClient.getInstances("service-zookeeper");
		if (list != null && list.size() > 0 ) {
			return list.get(0).getUri().toString();
		}
		return null;
    }

}




