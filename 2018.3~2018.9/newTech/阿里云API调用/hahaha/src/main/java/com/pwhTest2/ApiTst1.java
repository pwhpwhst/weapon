package com.pwhTest2;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.cloudapi.model.v20160714.DescribeApisByAppRequest;
import com.aliyuncs.cloudapi.model.v20160714.DescribeApisByAppResponse;
import com.aliyuncs.cloudapi.model.v20160714.DescribeApisByAppResponse.AppApiRelationInfo;
import com.aliyuncs.cloudapi.model.v20160714.DescribeAuthorizedApisRequest;
import com.aliyuncs.cloudapi.model.v20160714.DescribeAuthorizedApisResponse;
import com.aliyuncs.cloudapi.model.v20160714.DescribeAuthorizedApisResponse.AuthorizedApi;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;

public class ApiTst1 {
	public static void main(String[] args){
		
		


		
	}
	
	
	public static String testApi(String env,String sqlId){

		
        DefaultProfile profile = DefaultProfile.getProfile(
                "cn-shenzhen",          // µÿ”ÚID
                "LTAIZ0xetUOnTYEN",      // RAM’À∫≈µƒAccessKey ID
                "BnxJASZtx9ncTyK9uvmjTT8R7D1Etc"); // RAM’À∫≈Access Key Secret
            IAcsClient client = new DefaultAcsClient(profile);
		
            
//            describeApiDoc.json
            
            DescribeAuthorizedApisRequest request=new DescribeAuthorizedApisRequest();
            

            List<AuthorizedApi> authorizedApiList=new ArrayList<AuthorizedApi>();

            
            try {
            	
            	boolean isFinished=false;
            	int pageNumber=1;
            	while(!isFinished){
	                request.setAppId(7917418l);
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
            
            if("Produce".equals(env)){
            	for(AuthorizedApi e:authorizedApiList){
            		if(("Produce_"+sqlId).equals(e.getApiName())){
            			JSONObject jo=new JSONObject();
            			jo.put("result", "success");
            			jo.put("env", "Product");
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
            			return jo.toString();
            		}
            	}

        			JSONObject jo=new JSONObject();
        			jo.put("result", "fail");
        			jo.put("env", "Product");
        			jo.put("reason", "Produce_"+sqlId+"Œ¥ ⁄»®");
        			return jo.toString();

            }
            
            
            if("Pre".equals(env)){
            	for(AuthorizedApi e:authorizedApiList){
            		if(("Pre_"+sqlId).equals(e.getApiName())){
            			
            			JSONObject jo=new JSONObject();
            			jo.put("result", "success");
            			jo.put("env", "Pre");
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
            			return jo.toString();
            		}
            	}
//    			JSONObject jo=new JSONObject();
//    			jo.put("result", "fail");
//    			jo.put("reason", "Produce_"+sqlId+"Œ¥ ⁄»®");
//    			return jo.toString();
//            	ApiProvider1.authorizeApi(client, groupId, sqlId, appId);
            }
	}
}
