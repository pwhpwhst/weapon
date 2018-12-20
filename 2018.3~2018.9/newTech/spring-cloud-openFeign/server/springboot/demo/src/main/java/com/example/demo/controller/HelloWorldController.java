package com.example.demo.controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class HelloWorldController {
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String index(@RequestParam(name = "name") String name) {
        return "Hello World:"+name;
    }
}