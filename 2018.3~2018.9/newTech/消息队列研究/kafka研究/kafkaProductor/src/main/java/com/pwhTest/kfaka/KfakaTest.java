package com.pwhTest.kfaka;

import java.util.Properties;
import org.apache.kafka.clients.admin.AdminClient;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.lang.InterruptedException;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;


public class KfakaTest{
public static void main(String[] args) {
    //创建topic
	System.out.println("-----------------创建Topic Begin------------------");
    Properties props = new Properties();
    props.put("bootstrap.servers", "127.0.0.1:9092");
    AdminClient adminClient = AdminClient.create(props);
    ArrayList<NewTopic> topics = new ArrayList<NewTopic>();
    NewTopic newTopic = new NewTopic("topic-test", 1, (short) 1);
    topics.add(newTopic);
    CreateTopicsResult result = adminClient.createTopics(topics);
    try {
        result.all().get();
    } catch (InterruptedException e) {
        e.printStackTrace();
    } catch (ExecutionException e) {
        e.printStackTrace();
    }
	System.out.println("-----------------创建Topic End------------------");

	System.out.println("-----------------发送消息Topic Begin------------------");
    props = new Properties();
    props.put("bootstrap.servers", "192.168.180.128:9092");
    props.put("acks", "all");
    props.put("retries", 0);
    props.put("batch.size", 16384);
    props.put("linger.ms", 1);
    props.put("buffer.memory", 33554432);
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

    Producer<String, String> producer = new KafkaProducer<String, String>(props);
    for (int i = 0; i < 20;i++){
		producer.send(new ProducerRecord<String, String>("topic-test", Integer.toString(i), Integer.toString(i)));
	}
    producer.close();
	System.out.println("-----------------发送消息Topic End------------------");

}
}