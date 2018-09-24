package com.pwhTest.ibatisTest.dao.impl;

import com.pwhTest.ibatisTest.dao.IClawerUrlDao;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.SQLException;
import com.ibatis.sqlmap.client.SqlMapClient;

//import com.ibatis.sqlmap.client.SqlMapClient;

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


}