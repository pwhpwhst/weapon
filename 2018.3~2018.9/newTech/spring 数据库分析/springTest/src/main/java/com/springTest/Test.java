package com.springTest;

import javax.sql.DataSource;
import com.springTest.dao.IUserDao;
import com.springTest.entity.UserEntity;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;


import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class Test{

	public static void main(String[] args){

//	ApplicationContext act =new ClassPathXmlApplicationContext("appContext.xml");


		Resource rs = new ClassPathResource("appContext.xml");
		BeanFactory act = new XmlBeanFactory(rs);

	IUserDao userDao= (IUserDao) act.getBean("userDao");
	UserEntity user=new UserEntity();

	user.setId(3);
	user.setName("pwhpwhst");
	user.setPassword("123456");

	userDao.save(user);

		System.out.println("fsfas");
	}

}