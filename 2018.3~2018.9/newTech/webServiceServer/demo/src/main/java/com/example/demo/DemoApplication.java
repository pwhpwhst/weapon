package com.example.demo;

import com.example.demo.webServiceServer.impl.AppService;
import javax.xml.ws.Endpoint;

public class DemoApplication {



	public static void main(String[] args) {
       		System.out.println("contextInitialized");
      String address = "http://127.0.0.1:8089/hubo/Webservice";
        //ʹ��Endpoint���ṩ��publish��������WebService������ʱҪ��֤ʹ�õĶ˿ں�û�б�����Ӧ�ó���ռ��
        Endpoint.publish(address , new AppService());
        System.out.println("����webservice�ɹ�!");
		
    }
}
