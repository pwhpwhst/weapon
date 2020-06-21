package com.pwhTest.VOAWord.dao.impl;


import org.springframework.jdbc.core.JdbcTemplate;
import com.pwhTest.VOAWord.model.VOAWordEntity;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import javax.sql.DataSource;
import com.pwhTest.VOAWord.Mapper.VOAWordRowMapper;
import com.pwhTest.VOAWord.dao.IVOAWordDao;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;


public class VOAWordDao implements IVOAWordDao{
	
	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void save(VOAWordEntity voaWord){
		jdbcTemplate.update("insert into VOAWord(word,num) values(?,?)",new Object[]{
			voaWord.getWord(),voaWord.getNum()
		},new int[]{java.sql.Types.VARCHAR,
				java.sql.Types.INTEGER
			});
	}

	public void delete(){
		jdbcTemplate.update("delete from VOAWord");
	}


	public void batchSave(List<VOAWordEntity> voaWordList){
	  StringBuffer sqlBuffer=new StringBuffer();
		sqlBuffer.append("insert into VOAWord(word,num) values");
		for(int i1=0;i1<voaWordList.size();i1++){
			sqlBuffer.append("(?,?)");
			if(i1!=voaWordList.size()-1){
				sqlBuffer.append(",");
			}
		}

	   String[] colList={"word","num"};
	   Map<String,Integer> colMap=new HashMap<String,Integer>();
	   for(int i1=0;i1<colList.length;i1++){
		colMap.put(colList[i1],i1);
	   }

		Object[] objArr= new Object[voaWordList.size()*colList.length];
		for(int i1=0;i1<voaWordList.size();i1++){
			objArr[i1*colList.length+colMap.get("word")]=voaWordList.get(i1).getWord();
			objArr[i1*colList.length+colMap.get("num")]=voaWordList.get(i1).getNum();
		}

		jdbcTemplate.update(sqlBuffer.toString(),objArr);

    }

	public List<VOAWordEntity> getVOAWordList(){
		List<VOAWordEntity> list = jdbcTemplate.query("select * from VOAWord",new VOAWordRowMapper());
		return list;
	}

}





