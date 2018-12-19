package com.example.demo.controller;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.UserEntity;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@RestController
public class HelloWorldController {
    @RequestMapping("/hello")
    public String index() {
        return "Hello World";
    }


	@RequestMapping(value = "getAll")
    public List<UserEntity> getUser() {
		System.out.println("dghdfhfhd");
        List<UserEntity> list = new ArrayList<UserEntity>();
		UserEntity userEntity=new UserEntity();
		userEntity.setId("1");
		userEntity.setName("pwh");
		list.add(userEntity);
        return list;
    }

}