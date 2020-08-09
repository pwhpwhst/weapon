package com.pwhTest.ibatisTest.dao;
import com.ibatis.sqlmap.client.SqlMapClient;
import java.util.Map;
import java.util.List;

public interface ITxAnalyzeDao {
	public void insertTxAnalyze(SqlMapClient sqlMap, Map<String,Object> transferMap);

	public void updateTxAnalyze(SqlMapClient sqlMap, Map<String,Object> transferMap);

	public List<Map<String,Object>> queryTxAnalyzeList(SqlMapClient sqlMap,Map<String,Object> transferMap);
}