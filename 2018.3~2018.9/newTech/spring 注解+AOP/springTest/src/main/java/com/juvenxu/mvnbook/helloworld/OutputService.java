package com.juvenxu.mvnbook.helloworld;
import org.springframework.stereotype.Service;

@Service("outputService")
public class OutputService implements IOutputService
{
	public String output(){
		return "dasfdsa";
	}
}