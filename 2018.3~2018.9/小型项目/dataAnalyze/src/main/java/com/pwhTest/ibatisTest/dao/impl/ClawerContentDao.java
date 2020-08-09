package com.pwhTest.ibatisTest.dao.impl;

import com.pwhTest.ibatisTest.dao.IClawerContentDao;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.Integer;
import java.sql.SQLException;
import com.ibatis.sqlmap.client.SqlMapClient;

public class ClawerContentDao implements IClawerContentDao{

    public List<Map<String,Object>> queryClawerContentList(SqlMapClient sqlMap,Map<String,Object> transferMap) {  
  
        //保存查询结果   
        List<Map<String,Object>> rsList = new ArrayList<Map<String,Object>>();  
  
        try {  
            rsList = (List<Map<String,Object>>)sqlMap.queryForList("query_clawer_content_list",transferMap);  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
        return rsList;  
    } 


    public Integer queryClawerContentNum(SqlMapClient sqlMap,Map<String,Object> transferMap) {  
  
		Integer num=0;
		List<Map<String,Object>> rsList = new ArrayList<Map<String,Object>>(); 
        try {  
            rsList = (List<Map<String,Object>>)sqlMap.queryForList("query_clawer_content_num",transferMap);
			if(rsList!=null&&rsList.size()!=0){
				num=((Number)rsList.get(0).get("count")).intValue();
			}
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
        return num;  
    }

	public List<Map<String,Object>> queryClawerContentCUList(SqlMapClient sqlMap,Map<String,Object> transferMap) {  
  
        //保存查询结果   
        List<Map<String,Object>> rsList = new ArrayList<Map<String,Object>>();  
  
        try {  
            rsList = (List<Map<String,Object>>)sqlMap.queryForList("query_clawer_content_cu_list",transferMap);  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
        return rsList;  
    }

	public Integer queryClawerContentCUNum(SqlMapClient sqlMap,Map<String,Object> transferMap) {  
  
		Integer num=0;
		List<Map<String,Object>> rsList = new ArrayList<Map<String,Object>>(); 
        try {  
            rsList = (List<Map<String,Object>>)sqlMap.queryForList("query_clawer_content_cu_num",transferMap);
			if(rsList!=null&&rsList.size()!=0){
				num=((Number)rsList.get(0).get("count")).intValue();
			}
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
        return num;  
    }

}