package com.example.demo;

import com.example.demo.webServiceServer.impl.AppService;
import javax.xml.ws.Endpoint;

public class DemoApplication {



	public static void main(String[] args) {
       		System.out.println("contextInitialized");
      String address = "http://127.0.0.1:8089/hubo/Webservice";
        //使用Endpoint类提供的publish方法发布WebService，发布时要保证使用的端口号没有被其他应用程序占用
        Endpoint.publish(address , new AppService());
        System.out.println("发布webservice成功!");
		
    }
}
