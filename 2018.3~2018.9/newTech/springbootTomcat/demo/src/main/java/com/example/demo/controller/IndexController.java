package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.rmi.RemoteException;
import java.util.List;



@RestController
@RequestMapping("/v1/data")
public class IndexController {


    @RequestMapping(value = "/getEasRes2", method = RequestMethod.GET)
    public String getEasRes()throws RemoteException {
        return "addEasCustomer";
    }



}