package com.pwhTest.ibatisTest.dao.impl;

import com.pwhTest.ibatisTest.dao.ITxAnalyze2Dao;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.sql.SQLException;
import com.ibatis.sqlmap.client.SqlMapClient;


public class TxAnalyze2Dao implements ITxAnalyze2Dao{

    public void insertTxAnalyze2(SqlMapClient sqlMap, Map<String,Object> transferMap) {  
  
        try {  
            sqlMap.insert("insert_tx_analyze2", transferMap);  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    }


	public void updateTxAnalyze2(SqlMapClient sqlMap, Map<String,Object> transferMap) {  
  
        try {  
            sqlMap.update("update_tx_analyze2", transferMap);  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    }

	public List<Map<String,Object>> queryTxAnalyze2List(SqlMapClient sqlMap,Map<String,Object> transferMap) {  
  
        //保存查询结果   
        List<Map<String,Object>> rsList = new ArrayList<Map<String,Object>>();  
  
        try {  
            rsList = (List<Map<String,Object>>)sqlMap.queryForList("query_tx_analyze2_list",transferMap);  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
        return rsList;  
    }


    public Integer queryTxAnalyze2Num(SqlMapClient sqlMap,Map<String,Object> transferMap) {  
  
		Integer num=0;
		List<Map<String,Object>> rsList = new ArrayList<Map<String,Object>>(); 
        try {  
            rsList = (List<Map<String,Object>>)sqlMap.queryForList("query_tx_analyze2_num",transferMap);
			if(rsList!=null&&rsList.size()!=0){
				num=((Number)rsList.get(0).get("count")).intValue();
			}
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
        return num;  
    }

	

}