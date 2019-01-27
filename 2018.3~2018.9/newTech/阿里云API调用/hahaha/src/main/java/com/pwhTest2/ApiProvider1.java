package com.pwhTest2;

import org.json.JSONArray;
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
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;

public class ApiProvider1 {
	public static void main(String[] args){
		
		String tenantId="tenantId";
		
		String sqlId="sqlId";
		
		String regionId="regionId";
		
		String accessKeyId="accessKeyId";
		
		String secret="secret";
		
		IAcsClient client=getClient(regionId,accessKeyId,secret);
		
		CreateApiGroupResponse createApiGroupResponse = createApiGatewayGroup(client,tenantId);
		
		String groupId=createApiGroupResponse.getGroupId();
		
		
		CreateApiResponse createApiResponse = createApi(client,groupId,sqlId);
		
		DeployApiResponse deployApiResponse=deployApi( client, groupId,createApiResponse.getApiId());
		
	}
	
	
	public static IAcsClient getClient(String regionId,String accessKeyId,String secret){
        DefaultProfile profile = DefaultProfile.getProfile(
        		regionId,          // 地域ID
        		accessKeyId,      // RAM账号的AccessKey ID
        		secret); // RAM账号Access Key Secret
        IAcsClient client = new DefaultAcsClient(profile);
        return client;
	}
	
	 public static CreateApiGroupResponse createApiGatewayGroup(IAcsClient client,String tenantId){
	      CreateApiGroupRequest createApiGroupRequest=new CreateApiGroupRequest();
	      createApiGroupRequest.setGroupName(tenantId);//重要
	      createApiGroupRequest.setDescription(tenantId+" API");//重要 
	      
	      // 发起请求并处理应答或异常
	      CreateApiGroupResponse response =null;
	      try {
	          response = client.getAcsResponse(createApiGroupRequest);

	      	
//	          System.out.println("response.requestId ="+response.getRequestId());
//	          System.out.println("response.groupId ="+response.getGroupId());
//	          System.out.println("response.groupName ="+response.getGroupName());
//	          System.out.println("response.subDomain ="+response.getSubDomain());
//	          System.out.println("response.description ="+response.getDescription());

//	          response.requestId =112C9500-4146-409B-961C-AFAE2576CF21
//	        		  response.groupId =a705188388e44bb28b90ca51026c0593
//	        		  response.groupName =pwhTest
//	        		  response.subDomain =a705188388e44bb28b90ca51026c0593-cn-shenzhen.alicloudapi.com
//	        		  response.description =潘伟豪的第一个分组

	         
	      } catch (ServerException e) {
	          e.printStackTrace();
	      } catch (ClientException e) {
	          e.printStackTrace();
	      }
	      	return response;
	  }
	 
	 
	  public static CreateApiResponse createApi(IAcsClient client,String groupId,String sqlId){
		  CreateApiRequest createApiRequest = new CreateApiRequest();
		  
		  createApiRequest.setGroupId(groupId);//重要
		  createApiRequest.setApiName("getSql");//重要
		  createApiRequest.setVisibility("PUBLIC");//重要
		  createApiRequest.setDescription("Api for getSql");//重要
		  createApiRequest.setAuthType("APP");//重要
		  
		  JSONObject requestConfig=new JSONObject();
		  requestConfig.put("RequestProtocol", "HTTP");
		  requestConfig.put("RequestHttpMethod", "GET");
		  requestConfig.put("RequestPath", "/get/[groupId]/[sqlId]");
		  requestConfig.put("BodyFormat", "");
		  requestConfig.put("PostBodyDescription", "");
		  requestConfig.put("RequestMode", "MAPPING");
		  requestConfig.put("BodyModel", "");
		  createApiRequest.setRequestConfig(requestConfig.toString());


		  
		  JSONObject serviceConfig=new JSONObject();
		  serviceConfig.put("ServiceProtocol","HTTP");
		  serviceConfig.put("ServiceHttpMethod","GET");
		  serviceConfig.put("ServiceAddress","https://domain_real");
		  serviceConfig.put("ServiceTimeout","10000");
		  serviceConfig.put("ServicePath","/get/[groupId]/[sqlId]");
		  serviceConfig.put("Mock","FALSE");
		  serviceConfig.put("MockResult","");
		  serviceConfig.put("MockHeaders", new JSONArray());
		  serviceConfig.put("ServiceVpcEnable","FALSE");
		  serviceConfig.put("VpcConfig",new JSONObject());
		  serviceConfig.put("FunctionComputeConfig",new JSONObject());
		  serviceConfig.put("ContentTypeCatagory","DEFAULT");
		  serviceConfig.put("ContentTypeValue","application/x-www-form-urlencoded; charset=UTF-8");
		  createApiRequest.setServiceConfig(serviceConfig.toString());

		  
		  JSONArray requestParameters=new JSONArray();
		  JSONObject groupIdParameter=new JSONObject();
		  groupIdParameter.put("Required", "REQUIRED");
		  groupIdParameter.put("ParameterType", "String");
		  groupIdParameter.put("ApiParameterName", "sqlId");
		  groupIdParameter.put("DocShow", "PUBLIC");
		  groupIdParameter.put("Location", "Path");
		  groupIdParameter.put("DocOrder", 0);
		  groupIdParameter.put("ParameterCatalog", "REQUEST");
		  groupIdParameter.put("isHide", false);
		  JSONObject parameterLocation=new JSONObject();
		  parameterLocation.put("name", "Parameter Path");
		  parameterLocation.put("orderNumber", 1);
		  groupIdParameter.put("ParameterLocation", parameterLocation);
		  requestParameters.put(groupIdParameter);
		  
		  JSONObject sqlIdParameter=new JSONObject();
		  sqlIdParameter.put("Required", "REQUIRED");
		  sqlIdParameter.put("ParameterType", "String");
		  sqlIdParameter.put("ApiParameterName", "groupId");
		  sqlIdParameter.put("DocShow", "PUBLIC");
		  sqlIdParameter.put("Location", "Path");
		  sqlIdParameter.put("DocOrder", 0);
		  sqlIdParameter.put("ParameterCatalog", "REQUEST");
		  sqlIdParameter.put("isHide", false);
		  parameterLocation=new JSONObject();
		  sqlIdParameter.put("name", "Parameter Path");
		  sqlIdParameter.put("orderNumber", 1);
		  sqlIdParameter.put("ParameterLocation", parameterLocation);
		  requestParameters.put(sqlIdParameter);
	
		  
		  JSONObject caAppIdParameter=new JSONObject();
		  caAppIdParameter.put("ParameterType", "String");
		  caAppIdParameter.put("Required", "OPTIONAL");
		  caAppIdParameter.put("isHide", "false");
		  caAppIdParameter.put("ApiParameterName", "CaAppId");
		  caAppIdParameter.put("Location", "Head");
		  parameterLocation=new JSONObject();
		  parameterLocation.put("name", "Head");
		  parameterLocation.put("orderNumber", 2);
		  caAppIdParameter.put("ParameterLocation", parameterLocation);
		  requestParameters.put(caAppIdParameter);
		  
		  createApiRequest.setRequestParameters(requestParameters.toString());
		  
		  

		  JSONArray serviceParameters=new JSONArray();	  
		  JSONObject serviceParameter=new JSONObject();
		  serviceParameter.put("ServiceParameterName", "groupId");
		  serviceParameter.put("Location", "Path");
		  serviceParameter.put("Type", "String");
		  serviceParameter.put("ParameterCatalog", "REQUEST");
		  serviceParameters.put(serviceParameter);
		  
		  serviceParameter=new JSONObject();
		  serviceParameter.put("ServiceParameterName", "sqlId");
		  serviceParameter.put("Location", "Path");
		  serviceParameter.put("Type", "String");
		  serviceParameter.put("ParameterCatalog", "REQUEST");
		  serviceParameters.put(serviceParameter);
		  
		  
		  serviceParameter=new JSONObject();
		  serviceParameter.put("ServiceParameterName", "CaAppId");
		  serviceParameter.put("Location", "Head");
		  serviceParameter.put("Type", "String");
		  serviceParameter.put("ParameterCatalog", "REQUEST");
		  serviceParameters.put(serviceParameter);
		  
		  createApiRequest.setServiceParameters(serviceParameters.toString());

		  
		  JSONArray serviceParametersMap=new JSONArray();
		  serviceParameter=new JSONObject();
		  serviceParameter.put("ServiceParameterName", "groupId");
		  serviceParameter.put("RequestParameterName", "groupId");
		  serviceParametersMap.put(serviceParameter);
		  serviceParameter=new JSONObject();
		  serviceParameter.put("ServiceParameterName", "sqlId");
		  serviceParameter.put("RequestParameterName", "sqlId");
		  serviceParametersMap.put(serviceParameter);
		  
		  serviceParameter=new JSONObject();
		  serviceParameter.put("ServiceParameterName", "CaAppId");
		  serviceParameter.put("RequestParameterName", "CaAppId");
		  serviceParametersMap.put(serviceParameter);
		  
		  createApiRequest.setServiceParametersMap(serviceParametersMap.toString());
		  createApiRequest.setSystemParameters("");
		  createApiRequest.setConstantParameters("");

		  createApiRequest.setResultType("JSON");
		  createApiRequest.setResultSample("");
		  createApiRequest.setFailResultSample("");
		  createApiRequest.setErrorCodeSamples("[]");
		  createApiRequest.setOpenIdConnectConfig("");
		  createApiRequest.setAllowSignatureMethod("HmacSHA256");

		  CreateApiResponse response = null;
	      try {
	          response = client.getAcsResponse(createApiRequest);


	          System.out.println("response.requestId ="+response.getRequestId());
	          System.out.println("response.apiId ="+response.getApiId());


//	          response.requestId =32151D30-4FA0-453A-A119-FE7E569AA42A
//	        		  response.apiId =01f78747b0da4ce680aa2e2b4e98730f

	          
	      } catch (ServerException e) {
	          e.printStackTrace();
	      } catch (ClientException e) {
	          e.printStackTrace();
	      }
	      return response;
	  }
	  
	  
	  public static DeployApiResponse deployApi(IAcsClient client,String groupId,String apiId){
		  
		  DeployApiRequest deployApiRequest = new DeployApiRequest();
		  
		  deployApiRequest.setGroupId(groupId);
		  deployApiRequest.setApiId(apiId);
		  deployApiRequest.setStageName("RELEASE");
		  deployApiRequest.setDescription("deploy API");

		  DeployApiResponse response=null;
	      try {
	          response = client.getAcsResponse(deployApiRequest);
	    	
	      } catch (ServerException e) {
	          e.printStackTrace();
	      } catch (ClientException e) {
	          e.printStackTrace();
	      }
	      return response;
	  }
	  
	  
	  public static void authorizeApi(IAcsClient client,String groupId,String apiId,String appId){
		  SetAppsAuthoritiesRequest setAppsAuthoritiesRequest = new SetAppsAuthoritiesRequest();
		  
		  setAppsAuthoritiesRequest.setGroupId(groupId);
		  setAppsAuthoritiesRequest.setApiId(apiId);
		  setAppsAuthoritiesRequest.setStageName("Pre");
		  setAppsAuthoritiesRequest.setAppIds(appId);
		  setAppsAuthoritiesRequest.setDescription("HAHAHA");
		  
		  SetAppsAuthoritiesResponse response;
	      try {
	          response = client.getAcsResponse(setAppsAuthoritiesRequest);
	    	
	          System.out.println("response.requestId ="+response.getRequestId());
//	          response.requestId =5C5F3971-2514-4462-9FAC-5895CB0CDC8C
	      } catch (ServerException e) {
	          e.printStackTrace();
	      } catch (ClientException e) {
	          e.printStackTrace();
	      }

	  }
	 
	 
}
