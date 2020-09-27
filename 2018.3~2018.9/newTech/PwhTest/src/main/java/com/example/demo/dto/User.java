package com.example.demo.dto;

import io.swagger.annotations.ApiModelProperty;


public class User {

    @ApiModelProperty(value = "用户id",required = true)
    private Long id;
	@ApiModelProperty(value = "用户name",required = true)
    private String name;
	@ApiModelProperty(value = "用户password",required = true)
    private String password;
 

public User(){

}

    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
}

