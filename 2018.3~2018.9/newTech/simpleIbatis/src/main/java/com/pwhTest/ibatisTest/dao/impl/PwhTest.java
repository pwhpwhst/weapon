package com.pwhTest.ibatisTest.dao.impl;

import java.io.IOException;
import java.io.Reader;
import java.util.Map;
import java.util.List;
import java.util.HashMap;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
//import com.ibatis.sqlmap.client.SqlMapClient;

public class PwhTest {

        public static SqlMapClient sqlmapclient = null;  
        static {  
            try {  
                //¶ÁÈ¡xmlÎÄ¼þ   
                Reader reader = Resources.getResourceAsReader("SqlMapConfig.xml");  
                sqlmapclient = SqlMapClientBuilder.buildSqlMapClient(reader);  
                reader.close();  
            } catch (IOException e) {
      
                e.printStackTrace();  
            }  
        }


	public static void main(String[] args){
//		System.out.println("dfsafds");
		ClawerUrlDao clawerUrlDao=new ClawerUrlDao();
		Map<String,Object> transferMap=new HashMap<String,Object>();
		transferMap.put("id",11);
		List<Map<String,Object>> resultList=clawerUrlDao.queryClawerUrlList(sqlmapclient,transferMap);
		System.out.println((String)resultList.get(0).get("url"));
	}
}