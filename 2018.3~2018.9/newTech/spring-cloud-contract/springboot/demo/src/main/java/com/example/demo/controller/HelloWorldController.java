package com.example.demo.controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;



import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiImplicitParams; 
import io.swagger.annotations.ApiImplicitParam; 
import org.json.JSONObject;

@RestController
public class HelloWorldController {


//	http://127.0.0.1:8080/hello/123
@ApiOperation(value = "/get",notes = "get Demo",httpMethod="POST")
@RequestMapping(value = "/hello/{sqlId}",method = RequestMethod.POST)
	    @ApiImplicitParams({
			@ApiImplicitParam(name = "sqlId", value = "对应后台系统中某条SQL", required = true, dataType = "Long",paramType = "path")
    })
public String get(@PathVariable(value = "sqlId") Long sqlId){

System.out.println(sqlId);

        JSONObject jo=new JSONObject();
        jo.put("SUCCESS", "true");
        jo.put("name", "pwh");
        jo.put("id", "123456");

return jo.toString();

}

}





