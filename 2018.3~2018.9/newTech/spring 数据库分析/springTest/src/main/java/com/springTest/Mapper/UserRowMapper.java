package com.springTest.Mapper;

import com.springTest.entity.UserEntity;
import org.springframework.jdbc.core.RowMapper; 
import java.sql.ResultSet;
import java.sql.SQLException;



public class UserRowMapper implements RowMapper{

	public Object mapRow(ResultSet set,int index) throws SQLException{
		UserEntity user=new UserEntity();

		user.setId(set.getInt("id"));
		user.setName(set.getString("name"));
		user.setPassword(set.getString("password"));

		return user;

	}

}

