package com.pwhTest.controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Value; 
import org.springframework.cloud.context.config.annotation.RefreshScope;


@RestController
@RefreshScope
public class HelloWorldController {
    @RequestMapping("/hello")
    public String index() {
        return "info.description="+name;
    }


	@Value("${info.description}")
	private String name = "World2";
}