package com.example.demo.controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.service.StoreService;
import javax.annotation.Resource;

@RestController
public class HelloWorldController {

@Autowired
private StoreService storeService;

    @RequestMapping("/hello")
    public String index() throws InterruptedException{
        return storeService.getStores(null);
    }
}