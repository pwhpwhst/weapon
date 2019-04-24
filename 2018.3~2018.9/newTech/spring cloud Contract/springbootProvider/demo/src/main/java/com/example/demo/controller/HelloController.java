package com.example.demo.controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;




@RestController
public class HelloController {

    @RequestMapping(method = RequestMethod.GET, value = "/hello")
    public String world(@RequestParam String name) {
        return "123456";
    }
}

