package com.example.demo.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Component;
import java.util.Map;

@Component
public class StoreService{

	@HystrixCommand(fallbackMethod = "defaultStores")
    public String getStores(Map<String, Object> parameters)throws InterruptedException {
		Thread.sleep(60000);
        return "pwhStore";
    }

    public String defaultStores(Map<String, Object> parameters) {

        return "defaultStores";
    }
}





