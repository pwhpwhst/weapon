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
        // ����DefaultAcsClientʵ������ʼ��
        DefaultProfile profile = DefaultProfile.getProfile(
            "cn-shenzhen",          // ����ID
            "LTAIZ0xetUOnTYEN",      // RAM�˺ŵ�AccessKey ID
            "BnxJASZtx9ncTyK9uvmjTT8R7D1Etc"); // RAM�˺�Access Key Secret
        IAcsClient client = new DefaultAcsClient(profile);
        // ����API�������ò���
//        DescribeInstancesRequest request = new DescribeInstancesRequest();
//        request.setPageSize(10);
//        request.setConnectTimeout(5000); //�������ӳ�ʱΪ5000����
//        request.setReadTimeout(5000); //���ö���ʱΪ5000����
        
        CreateApiGroupRequest createApiGroupRequest=new CreateApiGroupRequest();
        createApiGroupRequest.setGroupName("weather");
        createApiGroupRequest.setDescription("pwhTest");
        
        
        
        // �������󲢴���Ӧ����쳣
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