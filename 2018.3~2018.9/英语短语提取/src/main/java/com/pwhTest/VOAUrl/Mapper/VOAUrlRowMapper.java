package com.pwhTest.VOAUrl.Mapper;

import com.pwhTest.VOAUrl.model.VOAUrlEntity;
import org.springframework.jdbc.core.RowMapper; 
import java.sql.ResultSet;
import java.sql.SQLException;



public class VOAUrlRowMapper implements RowMapper{

	public Object mapRow(ResultSet set,int index) throws SQLException{
		VOAUrlEntity voaUrl=new VOAUrlEntity();
		voaUrl.setId(set.getInt("id"));
		voaUrl.setTitle(set.getString("title"));
		voaUrl.setUrl(set.getString("url"));
		return voaUrl;

	}

}

