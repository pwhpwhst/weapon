package com.example.demo.controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import javax.annotation.Resource;

@RestController
public class HelloWorldController {
    @RequestMapping("/hello")
    public Mono<String> index() {
        return Mono.just("Welcome to reactive world ~");
    }
}