package com.example.demo.controller;
import org.springframework.web.bind.annotation.*;
import com.netflix.discovery.EurekaClient;
import javax.annotation.Resource;
import com.netflix.appinfo.InstanceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloWorldController {

@Autowired
private EurekaClient discoveryClient;

	@Autowired
	RestTemplate restTemplate;


    @RequestMapping("/hello")
    public String index() {


    InstanceInfo instance = discoveryClient.getNextServerFromEureka("PWHPWHST", false);
    return instance.getHomePageUrl();

    }


	@RequestMapping("/hello2")
    public String hello2() {

		String result=(String)restTemplate.getForObject("http://PWHPWHST/hello",String.class);
		return result;
    }
}