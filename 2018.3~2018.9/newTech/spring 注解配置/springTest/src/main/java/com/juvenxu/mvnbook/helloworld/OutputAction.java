package com.juvenxu.mvnbook.helloworld;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


@Controller("OutputAction")
public class OutputAction
{

	private IOutputService outputService;

	@Autowired
	@Qualifier("OutputService")
	public void setOutputService(IOutputService outputService){this.outputService=outputService;}

	public void output(){
		System.out.println(outputService.output()+"dasfdsa");
	}
}