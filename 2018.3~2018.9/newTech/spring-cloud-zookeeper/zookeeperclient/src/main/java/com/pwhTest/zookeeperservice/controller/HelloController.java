package com.pwhTest.zookeeperservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pwhTest.zookeeperservice.service.HelloService;
//import com.jsoft.testzookeeper.client.service.ServiceFeign;



@RestController
public class HelloController {

    @Autowired
    private HelloService helloService;

//    @Autowired
//    private ServiceFeign serviceFeign;

    @RequestMapping(value = "hello")
    public String hello(@RequestParam String name) {
        return helloService.sayHello(name);
    }

//    @RequestMapping(value = "hello2")
//    public String hello2(@RequestParam String name) {
//        return serviceFeign.sayHello(name);
//    }
}




