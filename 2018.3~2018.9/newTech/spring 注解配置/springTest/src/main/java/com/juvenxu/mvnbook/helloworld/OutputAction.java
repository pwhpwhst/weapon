package com.juvenxu.mvnbook.helloworld;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Controller("outputAction")
public class OutputAction
{
	@Autowired
	private IOutputService outputService;


	public String output(){
		return outputService.output();
	}
}