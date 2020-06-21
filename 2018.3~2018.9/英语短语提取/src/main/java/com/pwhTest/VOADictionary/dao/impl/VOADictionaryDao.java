package com.pwhTest.VOADictionary.dao.impl;


import org.springframework.jdbc.core.JdbcTemplate;
import com.pwhTest.VOADictionary.model.VOADictionaryEntity;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import javax.sql.DataSource;
import com.pwhTest.VOADictionary.Mapper.VOADictionaryRowMapper;
import com.pwhTest.VOADictionary.dao.IVOADictionaryDao;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;


public class VOADictionaryDao implements IVOADictionaryDao{
	
	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void save(VOADictionaryEntity voaDictionary){
		jdbcTemplate.update("insert into VOADictionary(wordId,property,chinese) values(?,?,?)",new Object[]{
			voaDictionary.getWordId(),voaDictionary.getProperty(),voaDictionary.getChinese()
		},new int[]{java.sql.Types.INTEGER,
				java.sql.Types.VARCHAR,
				java.sql.Types.VARCHAR
			});
	}


	public void delete(){
		jdbcTemplate.update("delete from VOADictionary");
	}


	public void batchSave(List<VOADictionaryEntity> voaDictionaryList){
	  StringBuffer sqlBuffer=new StringBuffer();
		sqlBuffer.append("insert into VOADictionary(wordId,property,chinese) values");
		for(int i1=0;i1<voaDictionaryList.size();i1++){
			sqlBuffer.append("(?,?,?)");
			if(i1!=voaDictionaryList.size()-1){
				sqlBuffer.append(",");
			}
		}

	   String[] colList={"wordId","property","chinese"};
	   Map<String,Integer> colMap=new HashMap<String,Integer>();
	   for(int i1=0;i1<colList.length;i1++){
		colMap.put(colList[i1],i1);
	   }

		Object[] objArr= new Object[voaDictionaryList.size()*colList.length];
		for(int i1=0;i1<voaDictionaryList.size();i1++){
			objArr[i1*colList.length+colMap.get("wordId")]=voaDictionaryList.get(i1).getWordId();
			objArr[i1*colList.length+colMap.get("property")]=voaDictionaryList.get(i1).getProperty();
			objArr[i1*colList.length+colMap.get("chinese")]=voaDictionaryList.get(i1).getChinese();
		}

		jdbcTemplate.update(sqlBuffer.toString(),objArr);

    }

	public List<VOADictionaryEntity> getVOADictionaryList(){
		List<VOADictionaryEntity> list = jdbcTemplate.query("select * from VOADictionary",new VOADictionaryRowMapper());
		return list;
	}

}





