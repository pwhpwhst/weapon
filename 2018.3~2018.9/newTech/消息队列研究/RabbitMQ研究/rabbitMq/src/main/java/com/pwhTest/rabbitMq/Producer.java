package com.pwhTest.rabbitMq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

import com.rabbitmq.client.ConnectionFactory;

public class Producer {
    public final static String QUEUE_NAME="rabbitMQ.test";

    public static void main(String[] args) throws IOException, TimeoutException {
        //�������ӹ���
        ConnectionFactory factory = new ConnectionFactory();
        //����RabbitMQ�����Ϣ
        factory.setHost("localhost");
      //factory.setUsername("lp");
      //factory.setPassword("");
     // factory.setPort(2088);
        //����һ���µ�����
        Connection connection = factory.newConnection();
        //����һ��ͨ��
        Channel channel = connection.createChannel();
        //  ����һ������        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String message = "Hello RabbitMQ";
        //������Ϣ��������
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
        System.out.println("Producer Send +'" + message + "'");
        //�ر�ͨ��������
        channel.close();
        connection.close();
    }
}
