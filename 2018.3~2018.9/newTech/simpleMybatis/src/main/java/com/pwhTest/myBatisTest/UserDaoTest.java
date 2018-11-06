
package com.pwhTest.myBatisTest;

public class UserDaoTest {


	public static void main(String[] args){
		User user = UserDao.findUserById(1);
        System.out.println("id:" + user.getId() + " name:" + user.getName() + " password:" + user.getPassword());
	}

}