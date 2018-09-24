package com.pwhTest.MFTest.MFSubstatusTable;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.pwhTest.MFTest.MFCode.MFCodeImpl;
import com.pwhTest.MFTest.MFSubstatusTable.dao.MFSubStatusTableDao;
import com.pwhTest.RedisTest.RedisPool;

import net.sf.json.JSONObject;
import redis.clients.jedis.Jedis;

public class CreateMFSubStatusTable {
	//计算时间花费780s
	public static void main(String[] args) {
		
		MFCodeImpl mf =new MFCodeImpl();
		
		String type="00";


		Jedis redis=RedisPool.getJedis();

		List<Map<String,Object>> resultList=new ArrayList<Map<String,Object>>();
		
		
		Set<String> statusTableKeySet=redis.keys("statusTable*");
		Iterator<String> statusTableKeySetIt= statusTableKeySet.iterator();
		while(statusTableKeySetIt.hasNext()) {
			String key=statusTableKeySetIt.next();
			Map<String,Object> map=new HashMap<String,Object>();
			Map<String, String> map2=redis.hgetAll(key);
			map.putAll(map2);
			map.put("score", Integer.valueOf(map2.get("score")));
			resultList.add(map);
		}
		
		Comparator<Map<String, Object>> cmpr=new Comparator<Map<String, Object>>(){
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				String status1=(String)o1.get("status");
				String status2=(String)o2.get("status");
				
				return status1.compareTo(status2);
			}
		};
		Collections.sort(resultList, cmpr);
		
		long t1=System.currentTimeMillis();
		System.out.println("开始导入数据");
		IMFSubStatusTableDao mfSubStatusTableDao=new MFSubStatusTableDao();
		
		List<Map<String,Object>> _bList=new ArrayList<Map<String,Object>>();
		
		
		for(int i1=0;i1<resultList.size();i1++) {
			_bList.add(resultList.get(i1));
			if(((i1 % 1000) == 999) ||i1==(resultList.size()-1)) {
				mfSubStatusTableDao.addMFSubStatusBatch(_bList);
				_bList.clear();
				System.out.println("已导入" + (i1 + 1) + "个数据");
			}
		}
		long t2=System.currentTimeMillis();
		 long t4=t2-t1;
		System.out.println("导入数据库耗时："+(t4)/1000);
		
	}
}
