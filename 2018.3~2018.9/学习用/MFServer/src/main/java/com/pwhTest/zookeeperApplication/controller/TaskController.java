package com.pwhTest.zookeeperApplication.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.sf.json.JSONObject;

@RestController
public class TaskController {

	private static boolean  isTaskFinished=false;
	
    private static final Logger log = LoggerFactory.getLogger(TaskController.class);

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public boolean isTaskFinished() {
        return isTaskFinished;
    }

    public boolean waitTask(String conditionId,JSONObject task){
    	
    	
    	
    	return true;
    }
    
}


