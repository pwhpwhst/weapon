package com.pwhTest.MFTest3Provider.provider;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.pwhTest.MFTest3Provider.DemoService;

public class DemoServiceImpl implements DemoService {


    public String sayHello(String name) {
        System.out.println("[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] Hello " + name );
        return "Hello " + name;
    }

}
