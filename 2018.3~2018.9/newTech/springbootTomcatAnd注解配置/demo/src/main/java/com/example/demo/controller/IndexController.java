package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.rmi.RemoteException;
import java.util.List;
import com.example.demo.service.IIndexService;


@RestController
@RequestMapping("/v1/data")
public class IndexController {
	@Autowired
	IIndexService indexService;

    @RequestMapping(value = "/getEasRes2", method = RequestMethod.GET)
    public String getEasRes()throws RemoteException {
        return "addEasCustomer";
    }


    @RequestMapping(value = "/indexService", method = RequestMethod.GET)
    public String indexService()throws RemoteException {
        return indexService.sayHello();
    }


}