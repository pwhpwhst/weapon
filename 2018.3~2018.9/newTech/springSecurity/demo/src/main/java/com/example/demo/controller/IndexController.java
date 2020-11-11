package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.rmi.RemoteException;
import java.util.List;

/**
 * springBoot启动后会默认访问，测试启动是否成功
 * Created by ding.wenhui on 2017/12/8.
 */
@RestController
@RequestMapping("/v1/data")
public class IndexController {


    @RequestMapping(value = "/addEasCustomer", method = RequestMethod.GET)
    public String getEasRes()throws RemoteException {
        return "addEasCustomer";
    }

}