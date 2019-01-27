package com.pwhTest;

import java.util.Calendar;
import java.util.Date;

import org.json.JSONObject;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.cloudapi.model.v20160714.CreateApiGroupRequest;
import com.aliyuncs.cloudapi.model.v20160714.CreateApiGroupResponse;
import com.aliyuncs.cloudapi.model.v20160714.CreateApiRequest;
import com.aliyuncs.cloudapi.model.v20160714.CreateApiResponse;
import com.aliyuncs.cloudapi.model.v20160714.DeployApiRequest;
import com.aliyuncs.cloudapi.model.v20160714.DeployApiResponse;
import com.aliyuncs.cloudapi.model.v20160714.SetAppsAuthoritiesRequest;
import com.aliyuncs.cloudapi.model.v20160714.SetAppsAuthoritiesResponse;
import com.aliyuncs.ecs.model.v20140526.DescribeInstancesRequest;
import com.aliyuncs.ecs.model.v20140526.DescribeInstancesResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;

public class HttpTest2{

    public static void main(String[] args) {
        DefaultProfile profile = DefaultProfile.getProfile(
                "cn-shenzhen",          // 地域ID
                "LTAIZ0xetUOnTYEN",      // RAM账号的AccessKey ID
                "BnxJASZtx9ncTyK9uvmjTT8R7D1Etc"); // RAM账号Access Key Secret
            IAcsClient client = new DefaultAcsClient(profile);
    	

            
    	System.out.println("创建分组");
    	createApiGatewayGroup(client);
//        System.out.println("创建API");
//    	createApi(client);
//          System.out.println("发布API");
//        	deployApi(client);
//        System.out.println("授权到某APP");
//    	authorizeApi(client);
//    	debugApi();
    }


  public static void createApiGatewayGroup(IAcsClient client){
      CreateApiGroupRequest createApiGroupRequest=new CreateApiGroupRequest();
      createApiGroupRequest.setGroupName("pwhTest");//重要
      createApiGroupRequest.setDescription("潘伟豪的第一个分组");//重要
      
      // 发起请求并处理应答或异常
      CreateApiGroupResponse response;
      try {
          response = client.getAcsResponse(createApiGroupRequest);

      	
          System.out.println("response.requestId ="+response.getRequestId());
          System.out.println("response.groupId ="+response.getGroupId());
          System.out.println("response.groupName ="+response.getGroupName());
          System.out.println("response.subDomain ="+response.getSubDomain());
          System.out.println("response.description ="+response.getDescription());

//          response.requestId =112C9500-4146-409B-961C-AFAE2576CF21
//        		  response.groupId =a705188388e44bb28b90ca51026c0593
//        		  response.groupName =pwhTest
//        		  response.subDomain =a705188388e44bb28b90ca51026c0593-cn-shenzhen.alicloudapi.com
//        		  response.description =潘伟豪的第一个分组

         
      } catch (ServerException e) {
          e.printStackTrace();
      } catch (ClientException e) {
          e.printStackTrace();
      }

  }
  
//  http://40a8b87.nat123.cc/hello
  

  public static void createApi(IAcsClient client){
	  CreateApiRequest createApiRequest = new CreateApiRequest();
	  
	  createApiRequest.setGroupId("7ac668beac4049a29844bba6c185523e");//重要
	  createApiRequest.setApiName("helloWorld");//重要
	  createApiRequest.setVisibility("PUBLIC");//重要
	  createApiRequest.setDescription("pwh test");//重要
	  createApiRequest.setAuthType("APP");//重要
	  JSONObject requestConfig=new JSONObject();
	  requestConfig.put("RequestProtocol", "HTTP");
	  requestConfig.put("RequestHttpMethod", "GET");
	  requestConfig.put("RequestPath", "/abce");
	  requestConfig.put("BodyFormat", "");
	  requestConfig.put("PostBodyDescription", "");
	  requestConfig.put("RequestMode", "MAPPING");
	  requestConfig.put("BodyModel", "");
	  createApiRequest.setRequestConfig(requestConfig.toString());
		  
	  JSONObject serviceConfig=new JSONObject();
	  serviceConfig.put("ServiceProtocol","HTTP");
	  serviceConfig.put("ServiceHttpMethod","GET");
	  serviceConfig.put("ServiceAddress","http://40a8b87.nat123.cc");
	  serviceConfig.put("ServiceTimeout","10000");
	  serviceConfig.put("ServicePath","/fsrgs");
	  serviceConfig.put("Mock","FALSE");
	  serviceConfig.put("MockResult","");
	  serviceConfig.put("ServiceVpcEnable","FALSE");
	  serviceConfig.put("VpcConfig",new JSONObject());
	  serviceConfig.put("FunctionComputeConfig",new JSONObject());
	  serviceConfig.put("ContentTypeCatagory","DEFAULT");
	  serviceConfig.put("ContentTypeValue","application/x-www-form-urlencoded; charset=UTF-8");
	  createApiRequest.setServiceConfig(serviceConfig.toString());
	  
	  
	  createApiRequest.setRequestParameters("[]");
	  createApiRequest.setSystemParameters("");
	  createApiRequest.setConstantParameters("");
	  createApiRequest.setServiceParameters("[]");
	  createApiRequest.setServiceParametersMap("[]");
	  createApiRequest.setResultType("JSON");
	  createApiRequest.setResultSample("asf");
	  createApiRequest.setFailResultSample("asdsa");
	  createApiRequest.setErrorCodeSamples("[]");
	  createApiRequest.setOpenIdConnectConfig("");
	  createApiRequest.setAllowSignatureMethod("HmacSHA256");

	  CreateApiResponse response;
      try {
          response = client.getAcsResponse(createApiRequest);


    	
          System.out.println("response.requestId ="+response.getRequestId());
          System.out.println("response.apiId ="+response.getApiId());


//          response.requestId =32151D30-4FA0-453A-A119-FE7E569AA42A
//        		  response.apiId =01f78747b0da4ce680aa2e2b4e98730f

          
      } catch (ServerException e) {
          e.printStackTrace();
      } catch (ClientException e) {
          e.printStackTrace();
      }
  }
  

  public static void deployApi(IAcsClient client){
	  DeployApiRequest deployApiRequest = new DeployApiRequest();
	  
	  deployApiRequest.setGroupId("7ac668beac4049a29844bba6c185523e");
	  deployApiRequest.setApiId("01f78747b0da4ce680aa2e2b4e98730f");
	  deployApiRequest.setStageName("RELEASE");
	  deployApiRequest.setDescription("ggregress");

	  DeployApiResponse response;
      try {
          response = client.getAcsResponse(deployApiRequest);
    	
          System.out.println("response.requestId ="+response.getRequestId());
//          response.requestId =87EC5FEF-2746-4839-83C5-42560658FBDB
      } catch (ServerException e) {
          e.printStackTrace();
      } catch (ClientException e) {
          e.printStackTrace();
      }
  }
  
  
  public static void authorizeApi(IAcsClient client){
	  SetAppsAuthoritiesRequest setAppsAuthoritiesRequest = new SetAppsAuthoritiesRequest();
	  
	  setAppsAuthoritiesRequest.setGroupId("7ac668beac4049a29844bba6c185523e");
	  setAppsAuthoritiesRequest.setApiId("01f78747b0da4ce680aa2e2b4e98730f");
	  setAppsAuthoritiesRequest.setStageName("P");
	  setAppsAuthoritiesRequest.setAppIds("7891697");
	  setAppsAuthoritiesRequest.setDescription("HAHAHA");
	  
	  SetAppsAuthoritiesResponse response;
      try {
          response = client.getAcsResponse(setAppsAuthoritiesRequest);
    	

      } catch (ServerException e) {
          e.printStackTrace();
      } catch (ClientException e) {
          e.printStackTrace();
      }
      
      
      
  }
  
  public static void debugApi(){
  	
  }





}