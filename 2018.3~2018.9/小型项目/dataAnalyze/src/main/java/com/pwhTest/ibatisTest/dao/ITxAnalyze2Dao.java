package com.pwhTest.ibatisTest.dao;
import com.ibatis.sqlmap.client.SqlMapClient;
import java.util.Map;
import java.util.List;

public interface ITxAnalyze2Dao {
	public void insertTxAnalyze2(SqlMapClient sqlMap, Map<String,Object> transferMap);
	public void updateTxAnalyze2(SqlMapClient sqlMap, Map<String,Object> transferMap);
	public List<Map<String,Object>> queryTxAnalyze2List(SqlMapClient sqlMap,Map<String,Object> transferMap);
	public Integer queryTxAnalyze2Num(SqlMapClient sqlMap,Map<String,Object> transferMap);
}