
package com.pwhTest.myBatisTest;

public class UserDaoTest {


	public static void main(String[] args){
		UserMapper mapper=SessionBean.session.getMapper(UserMapper.class);
        User user=mapper.getUser(2);
		System.out.println("id:" + user.getId() + " name:" + user.getName() + " password:" + user.getPassword());

	}

}