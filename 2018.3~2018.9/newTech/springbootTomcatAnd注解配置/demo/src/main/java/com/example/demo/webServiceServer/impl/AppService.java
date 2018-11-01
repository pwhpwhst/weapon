package com.example.demo.webServiceServer.impl;
import com.example.demo.webServiceServer.IAppService;
import javax.jws.WebService;

@WebService
public class AppService{
	public String getUserName(String id){
		return "userName:pwh";
	}

		public String getUser(String id){
		return "user:pwh";
	}
}