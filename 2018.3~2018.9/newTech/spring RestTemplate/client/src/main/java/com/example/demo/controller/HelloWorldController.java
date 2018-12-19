package com.example.demo.controller;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.UserEntity;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloWorldController {
    @RequestMapping("/hello")
    public String index() {
        return "Hello World";
    }

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(value = "getUserFromRestTemplate")
    public List<UserEntity> getUserFromRestTemplate() {

        ResponseEntity<List> responseEntity = restTemplate.getForEntity("http://localhost:8810/getAll", List.class);
        HttpHeaders headers = responseEntity.getHeaders();
        HttpStatus statusCode = responseEntity.getStatusCode();
        int code = statusCode.value();

        List<UserEntity> list = responseEntity.getBody();

        System.out.println(list.toString());


        return list;
    }

}