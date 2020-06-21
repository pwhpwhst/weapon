package com.pwhTest.VOAUrl.dao.impl;


import org.springframework.jdbc.core.JdbcTemplate;
import com.pwhTest.VOAUrl.model.VOAUrlEntity;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import javax.sql.DataSource;
import com.pwhTest.VOAUrl.Mapper.VOAUrlRowMapper;
import com.pwhTest.VOAUrl.dao.IVOAUrlDao;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;


public class VOAUrlDao implements IVOAUrlDao{
	
	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void save(VOAUrlEntity voaUrl){
		jdbcTemplate.update("insert into VOAUrl(title,url) values(?,?)",new Object[]{
			voaUrl.getTitle(),voaUrl.getUrl()
		},new int[]{java.sql.Types.VARCHAR,
				java.sql.Types.VARCHAR
			});
	}


	public void batchSave(List<VOAUrlEntity> voaUrlList){
	  StringBuffer sqlBuffer=new StringBuffer();
		sqlBuffer.append("insert into VOAUrl(title,url) values");
		for(int i1=0;i1<voaUrlList.size();i1++){
			sqlBuffer.append("(?,?)");
			if(i1!=voaUrlList.size()-1){
				sqlBuffer.append(",");
			}
		}

	   String[] colList={"title","url"};
	   Map<String,Integer> colMap=new HashMap<String,Integer>();
	   for(int i1=0;i1<colList.length;i1++){
		colMap.put(colList[i1],i1);
	   }

		Object[] objArr= new Object[voaUrlList.size()*colList.length];
		for(int i1=0;i1<voaUrlList.size();i1++){
			objArr[i1*colList.length+colMap.get("title")]=voaUrlList.get(i1).getTitle();
			objArr[i1*colList.length+colMap.get("url")]=voaUrlList.get(i1).getUrl();
		}

		jdbcTemplate.update(sqlBuffer.toString(),objArr);

    }

	public List<VOAUrlEntity> getVOAUrlList(){
		List<VOAUrlEntity> list = jdbcTemplate.query("select * from VOAUrl",new VOAUrlRowMapper());
		return list;
	}

	public Integer getVOAUrlCount(){
		 List<Integer> list = jdbcTemplate.query("select count(1) num from VOAUrl",new RowMapper(){
			public Object mapRow(ResultSet set,int index) throws SQLException{
				return set.getInt("num");
		}});
		return list.get(0);
	}

}





