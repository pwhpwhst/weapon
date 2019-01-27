package com.pwhTest;

import java.util.Calendar;
import java.util.Date;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.cloudapi.model.v20160714.CreateApiGroupRequest;
import com.aliyuncs.cloudapi.model.v20160714.CreateApiGroupResponse;
import com.aliyuncs.ecs.model.v20140526.DescribeInstancesRequest;
import com.aliyuncs.ecs.model.v20140526.DescribeInstancesResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;

public class HttpTest{

    public static void main(String[] args) {
        // 创建DefaultAcsClient实例并初始化
        DefaultProfile profile = DefaultProfile.getProfile(
            "cn-shenzhen",          // 地域ID
            "LTAIZ0xetUOnTYEN",      // RAM账号的AccessKey ID
            "BnxJASZtx9ncTyK9uvmjTT8R7D1Etc"); // RAM账号Access Key Secret
        IAcsClient client = new DefaultAcsClient(profile);
        // 创建API请求并设置参数
//        DescribeInstancesRequest request = new DescribeInstancesRequest();
//        request.setPageSize(10);
//        request.setConnectTimeout(5000); //设置连接超时为5000毫秒
//        request.setReadTimeout(5000); //设置读超时为5000毫秒
        
        CreateApiGroupRequest createApiGroupRequest=new CreateApiGroupRequest();
        createApiGroupRequest.setGroupName("weather");
        createApiGroupRequest.setDescription("pwhTest");
        
        
        
        // 发起请求并处理应答或异常
        CreateApiGroupResponse response;
        try {
            response = client.getAcsResponse(createApiGroupRequest);

        	
            System.out.println(response.getGroupId());

        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }


}