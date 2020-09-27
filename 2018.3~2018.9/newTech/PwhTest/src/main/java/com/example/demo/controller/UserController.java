package com.example.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.dao.UserMapper;
import com.example.demo.dto.User;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


import javax.annotation.Resource;

@RestController
public class UserController {
	
	@Autowired
	UserMapper userDao;
	
	@ApiOperation(value = "/hello",notes = "firstDemo")
    @RequestMapping(value = "/updateUserCount/{userId}/{count}",method = RequestMethod.PUT)
	public  void  updateUserCount(@ApiParam(value = "用户ID",required = true)
			                     @PathVariable(value = "userId")Integer userId,
	                              @ApiParam(value = "减少数量",required = true)
	                              @PathVariable(value = "count")Integer count) throws Exception {
		 System.out.println("userId="+userId);
		 System.out.println("count="+count);
		 User user=userDao.getUser(2);
		 System.out.println(user.getName());
	}
}





