package com.pwhTest.VOADictionary.Mapper;

import com.pwhTest.VOADictionary.model.VOADictionaryEntity;
import org.springframework.jdbc.core.RowMapper; 
import java.sql.ResultSet;
import java.sql.SQLException;



public class VOADictionaryRowMapper implements RowMapper{

	public Object mapRow(ResultSet set,int index) throws SQLException{
		VOADictionaryEntity voaDictionary=new VOADictionaryEntity();
		voaDictionary.setId(set.getInt("id"));
		voaDictionary.setWordId(set.getInt("wordId"));
		voaDictionary.setProperty(set.getString("property"));
		voaDictionary.setChinese(set.getString("chinese"));
		return voaDictionary;

	}

}