package com.pwhTest.ibatisTest.dao;
import com.ibatis.sqlmap.client.SqlMapClient;
import java.util.Map;
import java.util.List;

public interface IClawerUrlDao {
	public List<Map<String,Object>> queryClawerUrlList(SqlMapClient sqlMap,Map<String,Object> transferMap);

	public Integer queryClawerUrlNum(SqlMapClient sqlMap,Map<String,Object> transferMap);
}