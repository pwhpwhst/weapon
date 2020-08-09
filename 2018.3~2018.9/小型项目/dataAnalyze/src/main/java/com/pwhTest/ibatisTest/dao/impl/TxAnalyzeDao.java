package com.pwhTest.ibatisTest.dao.impl;

import com.pwhTest.ibatisTest.dao.ITxAnalyzeDao;
import java.util.ArrayList;
//import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.SQLException;
import com.ibatis.sqlmap.client.SqlMapClient;


public class TxAnalyzeDao implements ITxAnalyzeDao{

    public void insertTxAnalyze(SqlMapClient sqlMap, Map<String,Object> transferMap) {  
  
        try {  
            sqlMap.insert("insert_tx_analyze", transferMap);  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    }


	public void updateTxAnalyze(SqlMapClient sqlMap, Map<String,Object> transferMap) {  
  
        try {  
            sqlMap.update("update_tx_analyze", transferMap);  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    }

	public List<Map<String,Object>> queryTxAnalyzeList(SqlMapClient sqlMap,Map<String,Object> transferMap) {  
  
        //保存查询结果   
        List<Map<String,Object>> rsList = new ArrayList<Map<String,Object>>();  
  
        try {  
            rsList = (List<Map<String,Object>>)sqlMap.queryForList("query_tx_analyze_list",transferMap);  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
        return rsList;  
    }

}