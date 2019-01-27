package com.pwhTest3.controller;

import java.util.UUID;

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
import com.pwhTest3.Dto.ApiProviderDto;
import com.pwhTest3.Dto.ApiProviderGroupApiDto;
import com.pwhTest3.Dto.ApiProviderGroupDto;
import com.pwhTest3.Dto.ApiUserApiDto;
import com.pwhTest3.Dto.ApiUserAppDto;
import com.pwhTest3.Dto.ApiUserDto;

public class ApiProviderController {
	
	public  IAcsClient getClient(ApiProviderDto apiProviderDto){
        DefaultProfile profile = DefaultProfile.getProfile(
        		apiProviderDto.getRegionId(),          // 地域ID
        		apiProviderDto.getAccessKeyId(),      // RAM账号的AccessKey ID
        		apiProviderDto.getSecret()); // RAM账号Access Key Secret
        IAcsClient client = new DefaultAcsClient(profile);
        return client;
	}
	
	
	 public ApiProviderGroupDto createGroup(ApiProviderDto apiProviderDto,ApiUserDto apiUserDto){
 
	      
	      IAcsClient client=getClient( apiProviderDto);
	      
	      
	      CreateApiGroupRequest createApiGroupRequest=new CreateApiGroupRequest();
	      createApiGroupRequest.setGroupName("sql_"+apiUserDto.getId());//重要
	      createApiGroupRequest.setDescription("sql_"+apiUserDto.getId());//重要
	      
	      // 发起请求并处理应答或异常
	      CreateApiGroupResponse response =null;
	      try {
	          response = client.getAcsResponse(createApiGroupRequest);
	      } catch (ServerException e) {
	          e.printStackTrace();
	      } catch (ClientException e) {
	          e.printStackTrace();
	      }
	      
	      
	      if(response!=null){
	    	  ApiProviderGroupDto apiProviderGroupDto =new ApiProviderGroupDto();
	    	  apiProviderGroupDto.setId(UUID.randomUUID().toString().replaceAll("-", ""));
	    	  apiProviderGroupDto.setGroupId(response.getGroupId());
	    	  apiProviderGroupDto.setApiProviderId(apiProviderDto.getId());
	    	  return apiProviderGroupDto;
	      }else{
	    	  return null;
	      }

	  }
	
	 
	  public ApiProviderGroupApiDto createApi(ApiProviderDto apiProviderDto,
			  ApiProviderGroupDto apiProviderGroupDto,ApiUserDto apiUserDto){
		  CreateApiRequest createApiRequest = new CreateApiRequest();
		  
		  String apiProviderGroupApiId = UUID.randomUUID().toString().replaceAll("-", "");
		  
		  createApiRequest.setGroupId(apiProviderGroupDto.getGroupId());//重要
		  createApiRequest.setApiName("execSql_"+apiProviderGroupApiId);//重要
		  createApiRequest.setVisibility("PUBLIC");//重要
		  createApiRequest.setDescription("Api for execSql");//重要
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

		  
		  IAcsClient client=getClient( apiProviderDto);
		  
		  CreateApiResponse response = null;
	      try {
	          response = client.getAcsResponse(createApiRequest);

	      } catch (ServerException e) {
	          e.printStackTrace();
	      } catch (ClientException e) {
	          e.printStackTrace();
	      }
	      
	      if(response!=null){
	    	  ApiProviderGroupApiDto apiProviderGroupApiDto =new ApiProviderGroupApiDto();
	    	  apiProviderGroupApiDto.setId(UUID.randomUUID().toString().replaceAll("-", ""));
	    	  apiProviderGroupApiDto.setApiId(response.getApiId());
	    	  apiProviderGroupApiDto.setApiProviderGroupId(apiProviderGroupDto.getId());
	    	  return apiProviderGroupApiDto;
	      }else{
	    	  return null;
	      }
	  }
	
	  
	  public String deployApi(ApiProviderDto apiProviderDto,ApiProviderGroupDto apiProviderGroupDto,
			  ApiProviderGroupApiDto apiProviderGroupApiDto,String env){
		  DeployApiRequest deployApiRequest = new DeployApiRequest();
		  
		  deployApiRequest.setGroupId(apiProviderGroupDto.getGroupId());
		  deployApiRequest.setApiId(apiProviderGroupApiDto.getApiId());
		  deployApiRequest.setStageName(env);
		  deployApiRequest.setDescription(env+"_deploy");

		  IAcsClient client=getClient( apiProviderDto);
		  
		  DeployApiResponse response;
	      try {
	          response = client.getAcsResponse(deployApiRequest);
	          return "SUCCESS";
	      } catch (ServerException e) {
	          e.printStackTrace();
	          return "FAIL";
	      } catch (ClientException e) {
	          e.printStackTrace();
	          return "FAIL";
	      }
	  }
	  
	  
	  public ApiUserApiDto authorizeApi(ApiProviderDto apiProviderDto,ApiUserAppDto apiUserAppDto,
			  ApiProviderGroupDto apiProviderGroupDto,ApiProviderGroupApiDto apiProviderGroupApiDto,String env){
		  SetAppsAuthoritiesRequest setAppsAuthoritiesRequest = new SetAppsAuthoritiesRequest();
		  
		  setAppsAuthoritiesRequest.setGroupId(apiProviderGroupDto.getGroupId());
		  setAppsAuthoritiesRequest.setApiId(apiProviderGroupApiDto.getApiId());
		  setAppsAuthoritiesRequest.setStageName(env);
		  setAppsAuthoritiesRequest.setAppIds(apiUserAppDto.getAppId());
		  setAppsAuthoritiesRequest.setDescription("HAHAHA");
		  
		  IAcsClient client=getClient( apiProviderDto);
		  
		  SetAppsAuthoritiesResponse response =null;
	      try {
	          response = client.getAcsResponse(setAppsAuthoritiesRequest);
	    	

	      } catch (ServerException e) {
	          e.printStackTrace();
	      } catch (ClientException e) {
	          e.printStackTrace();
	      }
	      
	      if(response!=null){
	    	  ApiUserApiDto apiUserApiDto =new ApiUserApiDto();
	    	  apiUserApiDto.setId(UUID.randomUUID().toString().replaceAll("-", ""));
	    	  apiUserApiDto.setApiUserAppId(apiUserAppDto.getId());
	    	  apiUserApiDto.setApiProviderGroupApiId(apiProviderGroupApiDto.getId());
	    	  
	    	  return apiUserApiDto;
	      }else{
	    	  return null;
	      }
	      
	  }
	  
}
