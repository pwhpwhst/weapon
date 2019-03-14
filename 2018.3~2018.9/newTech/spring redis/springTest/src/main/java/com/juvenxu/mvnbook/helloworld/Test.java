package com.juvenxu.mvnbook.helloworld;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.redis.core.RedisTemplate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.script.RedisScript;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class Test{
	public static void main(String[] args){
//		Resource rs = new ClassPathResource("appContext.xml");
//		BeanFactory factory = new XmlBeanFactory(rs);

		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("appContext.xml");

		RedisTemplate redisTemplate=(RedisTemplate) applicationContext.getBean("redisTemplate");
		RedisScript<Result> updateAvailableSavingsCard=(RedisScript<Result>) applicationContext.getBean("updateAvailableSavingsCard");
		System.out.println("redisTemplate:"+redisTemplate);
//		OutputService outputService = (OutputService) factory.getBean("outputService");
//		System.out.println("service:"+outputService.output());

		List<String> list=new ArrayList<String>();
		list.add("score:0");
		list.add("123");
		list.add("abc");


		redisTemplate.execute(updateAvailableSavingsCard,  list);
		System.out.println("dasdfas");

//		OutputAction outputAction = (OutputAction) applicationContext.getBean("outputAction");
//		System.out.println("action:"+outputAction.output());


//        redisTemplate.delete("myStr");
//        redisTemplate.opsForValue().set("myStr", "skyLine");
//        System.out.println(redisTemplate.opsForValue().get("myStr"));
//        System.out.println("---------------");
//
//        // List读写
//        redisTemplate.delete("myList");
//        redisTemplate.opsForList().rightPush("myList", "T");
//        redisTemplate.opsForList().rightPush("myList", "L");
//        redisTemplate.opsForList().leftPush("myList", "A");
//        List<String> listCache = redisTemplate.opsForList().range("myList", 0, -1);
//        for (String s : listCache) {
//            System.out.println(s);
//        }
//        System.out.println("---------------");
//
//        // Set读写
//        redisTemplate.delete("mySet");
//        redisTemplate.opsForSet().add("mySet", "A");
//        redisTemplate.opsForSet().add("mySet", "B");
//        redisTemplate.opsForSet().add("mySet", "C");
//        Set<String> setCache = redisTemplate.opsForSet().members("mySet");
//        for (String s : setCache) {
//            System.out.println(s);
//        }
//        System.out.println("---------------");
//
//        // Hash读写
//        redisTemplate.delete("myHash");
//        redisTemplate.opsForHash().put("myHash", "BJ", "北京");
//        redisTemplate.opsForHash().put("myHash", "SH", "上海");
//        redisTemplate.opsForHash().put("myHash", "HN", "河南");
//        Map<String, String> hashCache = redisTemplate.opsForHash().entries("myHash");
//        for (Map.Entry entry : hashCache.entrySet()) {
//            System.out.println(entry.getKey() + " - " + entry.getValue());
//        }
//        System.out.println("---------------");





	}
}