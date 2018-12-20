package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.service.Service;

@RestController
public class HelloWorldController {

	@Autowired
	private Service service;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String index(@RequestParam(name = "name") String name) {
		System.out.println("sfsfgrgd");
		return service.hello(name);
    }
}