package com.juvenxu.mvnbook.helloworld;
import org.springframework.stereotype.Service;

@Service("OutputService")
public class OutputService implements IOutputService
{
	public String output(){
		return "dasfdsa";
	}
}