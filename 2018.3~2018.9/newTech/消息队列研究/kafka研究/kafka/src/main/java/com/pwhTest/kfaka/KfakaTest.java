package com.pwhTest.kfaka;

import java.util.Properties;
import kafka.admin.AdminClient;


public class KfakaTest{
public static void main(String[] args) {
    //����topic
    Properties props = new Properties();
    props.put("bootstrap.servers", "192.168.180.128:9092");
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
}
}