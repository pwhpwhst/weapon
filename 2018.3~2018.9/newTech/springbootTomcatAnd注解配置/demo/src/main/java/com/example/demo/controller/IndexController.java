package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.IIndexService;
import java.rmi.RemoteException;
import java.util.List;



@RestController
@RequestMapping("/v1/data")
public class IndexController {

	@Autowired
	private IIndexService indexService;

    @RequestMapping(value = "/getEasRes2", method = RequestMethod.GET)
    public String getEasRes()throws RemoteException {
        return "addEasCustomer";
    }


	@RequestMapping(value = "/sayHello", method = RequestMethod.GET)
    public String sayHello()throws RemoteException {
		return "haha";
        //return indexService.sayHello();
    }



}