package com.example.demo.service;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.cloud.openfeign.FeignClient;

//In the @FeignClient annotation the String value ("stores" above) is an arbitrary client name, which is used to create a Ribbon load balancer 
//����˵name��ʱ������
@FeignClient(name = "service",url = "http://localhost:8080")
public interface Service {

    @RequestMapping(value = "/hello?name={name}")
    String hello(@PathVariable("name")String name);

}
