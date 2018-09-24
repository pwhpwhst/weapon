package com.pwhTest.BaseDao;

import java.io.IOException;
import java.io.Reader;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
public class BaseDao {
	public static SqlMapClient sqlmapclient = null;  
    static {  
        try {  
            //读取xml文件   
            Reader reader = Resources.getResourceAsReader("SqlMapConfig.xml");  
            sqlmapclient = SqlMapClientBuilder.buildSqlMapClient(reader);  
            reader.close();  
        } catch (IOException e) {
  
            e.printStackTrace();  
        }  
    }
}
