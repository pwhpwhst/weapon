package com.example.demo.entity;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

public class UserEntity{
	@Id
	private String id;
	@Indexed(unique = true) // 注解属性username为索引，并且不能重复
	private String username;
	private String phone;
	private String email;
	private String name;
	private Date birthday;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public UserEntity(){};

	public UserEntity(String id,String username,String phone,String email,String name,Date birthday){
		this.id = id;
		this.username = username;
		this.phone = phone;
		this.email = email;
		this.name = name;
		this.birthday = birthday;
	}


}