package com.pwhTest;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.cloudapi.model.v20160714.CreateApiGroupRequest;
import com.aliyuncs.cloudapi.model.v20160714.CreateApiGroupResponse;
import com.aliyuncs.cloudapi.model.v20160714.CreateAppRequest;
import com.aliyuncs.cloudapi.model.v20160714.CreateAppResponse;
import com.aliyuncs.ecs.model.v20140526.DescribeInstancesRequest;
import com.aliyuncs.ecs.model.v20140526.DescribeInstancesResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;

public class HttpTest3{

    public static void main(String[] args) throws IOException {

        DefaultProfile profile = DefaultProfile.getProfile(
                "cn-shenzhen",          // 地域ID
                "LTAIZ0xetUOnTYEN",      // RAM账号的AccessKey ID
                "BnxJASZtx9ncTyK9uvmjTT8R7D1Etc"); // RAM账号Access Key Secret
            IAcsClient client = new DefaultAcsClient(profile);
//            System.out.println("创建APP");
//            createApp(client);
            
            System.out.println("创建API");
            Demo_pwhTest.queryUserListHttpSyncTest();
            
    }

    
    public static void createApp(IAcsClient client){
    	
    	CreateAppRequest createAppRequest=new CreateAppRequest();
    	createAppRequest.setAppName("dsdsa");//重要
    	createAppRequest.setDescription("pwhTest");//重要
        
        // 发起请求并处理应答或异常
        CreateAppResponse response;
        try {
            response = client.getAcsResponse(createAppRequest);
        	
            System.out.println("response.requestId ="+response.getRequestId());
            System.out.println("response.appId ="+response.getAppId());


//            response.requestId =2E3BABEE-A765-4C06-B8A1-BD34394BDD4B
//            		response.appId =7917162
            
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
    
    

}