package com.example.demo.service.impl;

import com.example.demo.service.IIndexService;
import org.springframework.stereotype.Service;

@Service("indexService")
public class IndexService implements IIndexService{
	public String sayHello(){return "hello yes";}
}
