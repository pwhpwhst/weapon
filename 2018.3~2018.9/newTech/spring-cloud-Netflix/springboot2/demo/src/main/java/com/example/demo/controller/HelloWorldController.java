package com.example.demo.controller;
import org.springframework.web.bind.annotation.*;
import com.netflix.discovery.EurekaClient;
import javax.annotation.Resource;
import com.netflix.appinfo.InstanceInfo;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class HelloWorldController {

@Autowired
private EurekaClient discoveryClient;

//    @RequestMapping("/hello")
//    public String index() {
//
//
//    InstanceInfo instance = discoveryClient.getNextServerFromEureka("PWHPWHST", false);
//    return instance.getHomePageUrl();
//
//    }

	@RequestMapping("/hello")
    public String index() {

    return "hello 8081";

    }
}