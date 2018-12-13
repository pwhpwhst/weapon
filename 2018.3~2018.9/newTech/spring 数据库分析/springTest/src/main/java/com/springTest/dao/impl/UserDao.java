package com.springTest.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import com.springTest.entity.UserEntity;
import java.util.List;
import javax.sql.DataSource;
import com.springTest.Mapper.UserRowMapper;
import com.springTest.dao.IUserDao;


public class UserDao implements IUserDao{
	
	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void save(UserEntity user){
		jdbcTemplate.update("insert into user(id,name,password) values(?,?,?)",new Object[]{
			user.getId(),user.getName(),user.getPassword()
		},new int[]{java.sql.Types.INTEGER,
				java.sql.Types.VARCHAR,
				java.sql.Types.VARCHAR
			});
	}

	public List<UserEntity> getUserList(){
		List<UserEntity> list = jdbcTemplate.query("select * from user",new UserRowMapper());
		return list;
	}


}



