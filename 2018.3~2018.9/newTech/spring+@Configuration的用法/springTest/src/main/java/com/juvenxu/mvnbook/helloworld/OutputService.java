package com.juvenxu.mvnbook.helloworld;

import org.springframework.stereotype.Component;

@Component("outputService")
public class OutputService
{
	public void output(){
		System.out.println("dasfdsa");
	}
}