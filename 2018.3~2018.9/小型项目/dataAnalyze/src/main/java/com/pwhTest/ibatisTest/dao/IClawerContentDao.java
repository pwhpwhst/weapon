package com.pwhTest.ibatisTest.dao;
import com.ibatis.sqlmap.client.SqlMapClient;
import java.util.Map;
import java.util.List;

public interface IClawerContentDao {
	public List<Map<String,Object>> queryClawerContentList(SqlMapClient sqlMap,Map<String,Object> transferMap);

	public Integer queryClawerContentNum(SqlMapClient sqlMap,Map<String,Object> transferMap);

	public List<Map<String,Object>> queryClawerContentCUList(SqlMapClient sqlMap,Map<String,Object> transferMap);

	public Integer queryClawerContentCUNum(SqlMapClient sqlMap,Map<String,Object> transferMap);
}