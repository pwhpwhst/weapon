package com.pwhTest.VOAWord.Mapper;

import com.pwhTest.VOAWord.model.VOAWordEntity;
import org.springframework.jdbc.core.RowMapper; 
import java.sql.ResultSet;
import java.sql.SQLException;



public class VOAWordRowMapper implements RowMapper{

	public Object mapRow(ResultSet set,int index) throws SQLException{
		VOAWordEntity voaWord=new VOAWordEntity();
		voaWord.setId(set.getInt("id"));
		voaWord.setWord(set.getString("word"));
		voaWord.setNum(set.getInt("num"));
		return voaWord;

	}

}

