package com.pwhTest.MFTest.MFSubstatusTable.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.pwhTest.BaseDao.BaseDao;
import com.pwhTest.MFTest.MFSubstatusTable.IMFSubStatusTableDao;

public class MFSubStatusTableDao implements IMFSubStatusTableDao{

    public void addMFSubStatus( Map<String,Object> transferMap) {  
    	 
        try {  
        	BaseDao.sqlmapclient.insert("insert_MFSubStatus", transferMap);
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    } 
    
    
    public void addMFSubStatusBatch( List<Map<String,Object>> transferList) {  
   	 
        try {  
        	BaseDao.sqlmapclient.insert("insert_MFSubStatus_batch", transferList);
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    } 

}