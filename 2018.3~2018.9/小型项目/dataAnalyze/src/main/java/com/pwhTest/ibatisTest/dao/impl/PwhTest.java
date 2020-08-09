package com.pwhTest.ibatisTest.dao.impl;

import java.util.ArrayList;
import java.util.List;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder; 
import java.net.URLEncoder; 

import java.io.IOException;
import java.io.Reader;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import com.pwhTest.httpTool.HttpTool;
import java.lang.Integer;
import java.util.Map;
import java.util.TreeMap;
import java.util.Comparator;
import java.util.Collections;
import java.util.Set;
import java.util.HashSet;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import com.pwhTest.ibatisTest.dao.IClawerContentDao;
import com.pwhTest.ibatisTest.dao.ITxAnalyzeDao;
import com.pwhTest.ibatisTest.dao.ITxAnalyze2Dao;

public class PwhTest {

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


	public static void main(String[] args){
		//handle1();	
		handle2();		
	}

	public static void handle1(){
		IClawerContentDao clawerContentDao=new ClawerContentDao();
		ITxAnalyzeDao	txAnalyzeDao=new TxAnalyzeDao();
		ITxAnalyze2Dao	txAnalyze2Dao=new TxAnalyze2Dao();



		

		Map<String,Object> transferMap=new HashMap<String,Object>();
		transferMap.put("task","TxData");
		Integer recordNum=clawerContentDao.queryClawerContentCUNum(sqlmapclient,transferMap);

		System.out.println("总共"+recordNum+"条记录");
		for(int i1=0;i1<recordNum;i1++){
			System.out.println("处理第"+(i1+1)+"条记录");
			transferMap.clear();
			transferMap.put("task","TxData");
			transferMap.put("recordIndex",i1);
			List<Map<String,Object>> list=clawerContentDao.queryClawerContentCUList(sqlmapclient,transferMap);
			Map<String,Object> map=list.get(0);
			Integer clawerContentId=(Integer)map.get("id");
			String content=(String)map.get("content");

			List<String> subKeyList=HttpTool.analyzeText(content);

			StringBuffer sb=new StringBuffer();

			for(int i2=0;i2<subKeyList.size();i2++){
				sb.append(subKeyList.get(i2));
				if(i2!=(subKeyList.size()-1)){
					sb.append(",");
				}
			}

			transferMap.clear();

			transferMap.put("clawerContentId",clawerContentId);
			transferMap.put("keyWord",sb.toString());

			txAnalyze2Dao.insertTxAnalyze2(sqlmapclient,transferMap);

		}
	}


	public static void handle2(){

		ITxAnalyzeDao	txAnalyzeDao=new TxAnalyzeDao();
		ITxAnalyze2Dao	txAnalyze2Dao=new TxAnalyze2Dao();

		Comparator<Map<String, Object>> cmpr=new Comparator<Map<String, Object>>(){
			@Override
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				String keyWord1=(String)o1.get("keyWord");
				String keyWord2=(String)o2.get("keyWord");
				
				return keyWord1.compareTo(keyWord2);
			}
		};

		Map<String,Object> searchMap=new TreeMap<String,Object>();

		Map<String,Object> transferMap=new HashMap<String,Object>();
		Integer recordNum=txAnalyze2Dao.queryTxAnalyze2Num(sqlmapclient,transferMap);

		for(int i1=0;i1<recordNum;i1++){
			System.out.println("处理第"+(i1+1)+"条记录");
			transferMap.clear();
			transferMap.put("recordIndex",i1);
			List<Map<String,Object>> list=txAnalyze2Dao.queryTxAnalyze2List(sqlmapclient,transferMap);
			String keyWordStr=(String)list.get(0).get("keyWord");
			String[] keyWordArr=keyWordStr.split(",");

			List<String> subKeyList=new ArrayList<String>();
			for(int i2=0;i2<keyWordArr.length;i2++){
				subKeyList.add(keyWordArr[i2]);
			}

			transferMap.clear();
			transferMap.put("keyList",subKeyList);
			List<Map<String,Object>> _txAnalyzeList=txAnalyzeDao.queryTxAnalyzeList(sqlmapclient,transferMap);
			Collections.sort(_txAnalyzeList, cmpr);



			List<Map<String,Object>> notExistTxAnalyzeList=new ArrayList<Map<String,Object>>();

			for(String e:subKeyList){
				searchMap.put("keyWord",e);
				int index=Collections.binarySearch(_txAnalyzeList, searchMap,cmpr);
				if(index<0){
					Map<String,Object> newMap=new HashMap<String,Object>();
					newMap.put("keyWord",e);
					newMap.put("num",0);
					notExistTxAnalyzeList.add(newMap);
				}
			}


			if(notExistTxAnalyzeList.size()>0){
				Set<String> notExistKeySet=new HashSet<String>();
				for(int i2=0;i2<notExistTxAnalyzeList.size();i2++){
					Map<String,Object> _map=notExistTxAnalyzeList.get(i2);
					if(!notExistKeySet.contains(_map.get("keyWord"))){
						notExistKeySet.add((String)_map.get("keyWord"));
						txAnalyzeDao.insertTxAnalyze(sqlmapclient, _map);
					}
				}
				transferMap.clear();
				transferMap.put("keyList",subKeyList);
				_txAnalyzeList=txAnalyzeDao.queryTxAnalyzeList(sqlmapclient,transferMap);
				Collections.sort(_txAnalyzeList, cmpr);
			}


			for(String e:subKeyList){
				searchMap.put("keyWord",e);
				int index=Collections.binarySearch(_txAnalyzeList, searchMap,cmpr);
				int num=(Integer)_txAnalyzeList.get(index).get("num");
				num++;
				_txAnalyzeList.get(index).put("num",num);
			}

			for(int i2=0;i2<_txAnalyzeList.size();i2++){
				txAnalyzeDao.updateTxAnalyze(sqlmapclient, _txAnalyzeList.get(i2));
			}

	}
}
}

