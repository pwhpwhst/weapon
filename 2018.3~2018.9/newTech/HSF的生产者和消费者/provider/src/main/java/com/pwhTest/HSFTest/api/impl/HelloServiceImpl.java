package com.pwhTest.HSFTest.api.impl;
import com.pwhTest.HSFTest.api.HelloService;

public class HelloServiceImpl implements HelloService{
	@Override
	public String hello() {
		return "hello world";
	}
}
