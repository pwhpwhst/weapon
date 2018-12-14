package com.example.demo.controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class HelloWorldController {
    @RequestMapping("/hello")
    public String index() {
        return "Hello World";
    }
}