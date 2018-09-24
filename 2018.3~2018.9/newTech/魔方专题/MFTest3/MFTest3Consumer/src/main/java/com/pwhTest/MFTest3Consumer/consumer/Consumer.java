package com.pwhTest.MFTest3Consumer.consumer;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pwhTest.MFTest.MFCode.MFCodeImpl;
import com.pwhTest.MFTest.MFSubstatusTable.CreateMFSubStatusTable;
import com.pwhTest.MFTest3Provider.DemoService;

public class Consumer {
	public static String  type="00";
    public static void main(String[] args) {
        System.setProperty("java.net.preferIPv4Stack", "true");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"META-INF/spring/dubbo-demo-consumer.xml"});
        context.start();
        CreateMFSubStatusTable createMFSubStatusTable = (CreateMFSubStatusTable) context.getBean("createMFSubStatusTable"); // get remote service proxy

//        while (true) {
//            try {
//                Thread.sleep(1000);
//                String hello = demoService.sayHello("world"); // call remote method
//                System.out.println(hello); // get result
//
//            } catch (Throwable throwable) {
//                throwable.printStackTrace();
//            }
//
//
//        }

        MFCodeImpl mf =new MFCodeImpl();
        
        while(!createMFSubStatusTable.isFinish()) {
        	Map<String, Object>  map=createMFSubStatusTable.pollOneStaus();
        	
			String status=(String)map.get("status");
			Integer score=(Integer)map.get("score");
			String moveStep=(String)map.get("moveStep");
			
			if(moveStep!=null) {
				mf.move(moveStep);
			}
			
			List<Map<String,Object>> childrenList=new ArrayList<Map<String,Object>>();
			for(int i1=0;i1<18;i1++) {
				mf.move(i1);
				String subStatus=mf.extractMFSubStatusExt(type);

					Map<String,Object> map2=new HashMap<String,Object>();
					map2.put("parentStatus", status);
					map2.put("status", subStatus);
					map2.put("score", score+1);
					map2.put("moveStep", moveStep==null?String.valueOf(i1):moveStep+","+i1);
					childrenList.add(map2);

				mf.remove(i1);
			}
		
//			List<Map<String,Object>> childrenList=(List<Map<String,Object>>)transferMap.get("childrenList");
			
			
			if(moveStep!=null) {
				mf.remove(moveStep);
			}
        	
			Map<String,Object> resultMap=new HashMap<String,Object>();
			resultMap.put("mainStatusMap", map);
			resultMap.put("childrenList", childrenList);
			createMFSubStatusTable.conFirmStatus(resultMap);
        }
    }
}
