package com.pwhTest.MFTest.MFSubstatusTable;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pwhTest.MFTest.MFCode.MFCodeImpl;
import com.pwhTest.MFTest.MFSubstatusTable.dao.MFSubStatusTableDao;

public class CreateMFSubStatusTable {
	public static void main(String[] args) {
		
		MFCodeImpl mf =new MFCodeImpl();
		
		String type="05";

		//初始化 begin
		Map<String,Map<String,Object>> statusTable=new HashMap<String,Map<String,Object>>();
		Deque<Map<String,Object>> deq=new ArrayDeque<Map<String,Object>>();
		
		Map<String,Object> _map=new HashMap<String,Object>();
		_map.put("status", mf.extractMFSubStatusExt(type));
		_map.put("score", 0);
		deq.add(_map);
		
		statusTable.put(mf.extractMFSubStatusExt(type), _map);
		
		//初始化 end
		

		int num=0;
		long t1=System.currentTimeMillis();
		while(!deq.isEmpty()){

			num++;

			if(num%10000==9999) {
				System.out.println("已解析："+(num+1));
			}
			
			Map<String,Object> map=deq.pollLast();
			
			String status=(String)map.get("status");
			Integer score=(Integer)map.get("score");
			String moveStep=(String)map.get("moveStep");
			
			if(moveStep!=null) {
				mf.move(moveStep);
			}
			
			for(int i1=0;i1<18;i1++) {
				mf.move(i1);
				String subStatus=mf.extractMFSubStatusExt(type);
				if(statusTable.get(subStatus)==null) {
					Map<String,Object> map2=new HashMap<String,Object>();
					map2.put("parentStatus", status);
					map2.put("status", subStatus);
					map2.put("score", score+1);
					map2.put("moveStep", moveStep==null?String.valueOf(i1):moveStep+","+i1);
					deq.addFirst(map2);
					statusTable.put(subStatus, map2);
				}
				mf.remove(i1);
			}
			
			if(moveStep!=null) {
				mf.remove(moveStep);
			}
			

		}
		long t2=System.currentTimeMillis();
		long t3=t2-t1;
		
		List<Map<String,Object>> resultList=new ArrayList<Map<String,Object>>();
		
		for(String key:statusTable.keySet()) {
			resultList.add(statusTable.get(key));
		}
		
		
		Comparator<Map<String, Object>> cmpr=new Comparator<Map<String, Object>>(){
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				String status1=(String)o1.get("status");
				String status2=(String)o2.get("status");
				
				return status1.compareTo(status2);
			}
		};
		Collections.sort(resultList, cmpr);
		
		 t1=System.currentTimeMillis();
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
		 t2=System.currentTimeMillis();
		 long t4=t2-t1;
		 System.out.println("遍历耗时："+(t3)/1000);
		System.out.println("导入数据库耗时："+(t4)/1000);
		
	}
}
