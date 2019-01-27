package com.pwhTest3.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.json.JSONObject;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.cloudapi.model.v20160714.CreateAppRequest;
import com.aliyuncs.cloudapi.model.v20160714.CreateAppResponse;
import com.aliyuncs.cloudapi.model.v20160714.DeployApiResponse;
import com.aliyuncs.cloudapi.model.v20160714.DescribeAuthorizedApisRequest;
import com.aliyuncs.cloudapi.model.v20160714.DescribeAuthorizedApisResponse;
import com.aliyuncs.cloudapi.model.v20160714.DescribeAuthorizedApisResponse.AuthorizedApi;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.pwhTest3.Dto.ApiProviderDto;
import com.pwhTest3.Dto.ApiProviderGroupApiDto;
import com.pwhTest3.Dto.ApiProviderGroupDto;
import com.pwhTest3.Dto.ApiUserApiDto;
import com.pwhTest3.Dto.ApiUserAppDto;
import com.pwhTest3.Dto.ApiUserDto;

public class ApiUserController {

	private ApiProviderController apiProviderController;
	
	
	public  IAcsClient getClient(ApiUserDto apiUserDto){
        DefaultProfile profile = DefaultProfile.getProfile(
        		apiUserDto.getRegionId(),          // 地域ID
        		apiUserDto.getAccessKeyId(),      // RAM账号的AccessKey ID
        		apiUserDto.getSecret()); // RAM账号Access Key Secret
        IAcsClient client = new DefaultAcsClient(profile);
        return client;
	}
	
	public ApiUserAppDto genApp(ApiUserDto apiUserDto){
		IAcsClient client=getClient(apiUserDto);
		
		CreateAppRequest createAppRequest=new CreateAppRequest();
		
		
		createAppRequest.setAppName("sql_"+apiUserDto.getId());
		createAppRequest.setDescription("sql_"+apiUserDto.getId());
		
		CreateAppResponse response=null;
	      try {
	          response = client.getAcsResponse(createAppRequest);
	      } catch (ServerException e) {
	          e.printStackTrace();
	      } catch (ClientException e) {
	          e.printStackTrace();
	      }
		
	      if(response!=null){
	    	  ApiUserAppDto apiUserAppDto =new ApiUserAppDto();
	    	  apiUserAppDto.setId(UUID.randomUUID().toString().replaceAll("-", ""));
	    	  apiUserAppDto.setAppId(response.getAppId().toString());
	    	  apiUserAppDto.setApiUserId(apiUserDto.getId()); 
	    	  return apiUserAppDto;
	      }else{
	    	  return null;
	      }
	}
	
	public ApiUserApiDto applyRightForApi(ApiUserAppDto apiUserAppDto,ApiProviderDto apiProviderDto,ApiProviderGroupDto apiProviderGroupDto,
			ApiProviderGroupApiDto apiProviderGroupApiDto,String env){
		return apiProviderController.authorizeApi( apiProviderDto, apiUserAppDto,
				   apiProviderGroupDto, apiProviderGroupApiDto, env);
	}
	
	public String testApi(ApiUserDto apiUserDto,ApiUserAppDto apiUserAppDto,ApiProviderDto apiProviderDto,ApiProviderGroupDto apiProviderGroupDto,ApiProviderGroupApiDto apiProviderGroupApiDto,String env){

		IAcsClient client=getClient(apiUserDto);

		
           
            
            DescribeAuthorizedApisRequest request=new DescribeAuthorizedApisRequest();
            

            List<AuthorizedApi> authorizedApiList=new ArrayList<AuthorizedApi>();

            
            try {
            	
            	boolean isFinished=false;
            	int pageNumber=1;
            	while(!isFinished){
	                request.setAppId(Long.valueOf(apiUserAppDto.getAppId()));
	                request.setPageNumber(pageNumber++);
	                request.setPageSize(10);
	                DescribeAuthorizedApisResponse response = client.getAcsResponse(request);
	                authorizedApiList.addAll(response.getAuthorizedApis());
	                if(authorizedApiList.size()==response.getTotalCount()){
	                	 isFinished=true;
	                }else{
							Thread.sleep(2000);
	                }
            	}
            	
			} catch (ServerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClientException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            
//  		  createApiRequest.setApiName("execSql_"+apiProviderGroupApiId);//重要
            JSONObject jo=null;
            for(AuthorizedApi e:authorizedApiList){
	        	if(("execSql_"+apiProviderGroupApiDto.getId()).equals(e.getApiName())){
	    			 jo=new JSONObject();
//	    			jo.put("result", "success");
//	    			jo.put("env", "Product");
	    			jo.put("regionId", e.getRegionId());
	    			jo.put("groupId", e.getGroupId());
	    			jo.put("groupName", e.getGroupName());
	    			jo.put("stageName", e.getStageName());
	    			jo.put("operator", e.getOperator());
	    			jo.put("apiId", e.getApiId());
	    			jo.put("apiName", e.getApiName());
	    			jo.put("authorizationSource", e.getAuthorizationSource());
	    			jo.put("description", e.getDescription());
	    			jo.put("authorizedTime", e.getAuthorizedTime());
	    			break;
	    		}
            }

            if("Product".equals(env)){
               if(jo==null){
       			 jo=new JSONObject();
       			jo.put("result", "fail");
       			jo.put("reason", "execSql_"+apiProviderGroupApiDto.getId()+"未授权");
       			return jo.toString();
               }else{
               	String stageName = jo.getString("stageName");
               	if("Product".equals(stageName)){
               		jo.put("result", "success");
               		return jo.toString();
               	}else if("Pre".equals(stageName)){
          			 jo=new JSONObject();
            			jo.put("result", "fail");
            			jo.put("reason", "execSql_"+apiProviderGroupApiDto.getId()+"为预生产环境");
               	}
               }
            }else if("Pre".equals(env)){
                if(jo==null){

                	applyRightForApi(apiUserAppDto,apiProviderDto,apiProviderGroupDto,
                			 apiProviderGroupApiDto, env);
                	return testApi( apiUserDto, apiUserAppDto, apiProviderDto, apiProviderGroupDto, apiProviderGroupApiDto, env);
                	
                  }else{
                 		jo.put("result", "success");
                   		return jo.toString();
                  }
            }

            	return null;
            }
	
	
	
	
}
