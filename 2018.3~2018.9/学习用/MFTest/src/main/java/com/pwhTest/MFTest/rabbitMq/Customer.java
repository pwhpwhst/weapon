package com.pwhTest.MFTest.rabbitMq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.GetResponse;

public class Customer {
    private final static String QUEUE_NAME = "rabbitMQ.test";

//    public static void main(String[] args) throws IOException, TimeoutException {
//    	
//    	ConnectionFactory factory = new ConnectionFactory();
//    	factory.setHost("localhost");
//    	Connection connection = factory.newConnection();
//    	Channel channel = connection.createChannel();
//	    //声明要关注的队列
//	    channel.queueDeclare("test", true, false, false, null);
//	    
//	    GetResponse  response = channel.basicGet("test", true);
//	    if(response!=null){
//		    String message = new String(response.getBody(), "UTF-8");
//		    
////		    String message = "Hello RabbitMQ";
////		    channel.basicPublish("corp.fanout", "first", null, message.getBytes("UTF-8"));
//		    
//		    System.out.println("a="+message);
//	    }
//	    
//    }
    
}
