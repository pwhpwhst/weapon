package com.pwhTest.ibatisTest.dao.impl;

import com.pwhTest.ibatisTest.dao.IClawerUrlDao;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.Integer;
import java.sql.SQLException;
import com.ibatis.sqlmap.client.SqlMapClient;

public class ClawerUrlDao implements IClawerUrlDao{

    public List<Map<String,Object>> queryClawerUrlList(SqlMapClient sqlMap,Map<String,Object> transferMap) {  
  
        //保存查询结果   
        List<Map<String,Object>> rsList = new ArrayList<Map<String,Object>>();  
  
        try {  
            rsList = (List<Map<String,Object>>)sqlMap.queryForList("query_clawer_url_list",transferMap);  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
        return rsList;  
    } 


    public Integer queryClawerUrlNum(SqlMapClient sqlMap,Map<String,Object> transferMap) {  
  
		Integer num=0;
		List<Map<String,Object>> rsList = new ArrayList<Map<String,Object>>(); 
        try {  
            rsList = (List<Map<String,Object>>)sqlMap.queryForList("query_clawer_url_num",transferMap);
			if(rsList!=null&&rsList.size()!=0){
				num=(Integer)rsList.get(0).get("count");
			}
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
        return num;  
    } 

}