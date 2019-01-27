//
//  Created by  fred on 2016/10/26.
//  Copyright 漏 2016骞� Alibaba. All rights reserved.
//

package com.pwhTest;

import com.alibaba.cloudapi.sdk.constant.SdkConstant;
import com.alibaba.cloudapi.sdk.model.ApiCallback;
import com.alibaba.cloudapi.sdk.model.ApiRequest;
import com.alibaba.cloudapi.sdk.model.ApiResponse;
import com.alibaba.cloudapi.sdk.model.HttpClientBuilderParams;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;

import java.io.IOException;



public class Demo_pwhTest {


    static{
        //HTTP Client init
        HttpClientBuilderParams httpParam = new HttpClientBuilderParams();
        httpParam.setAppKey("25522585");
        httpParam.setAppSecret("3e73dbd9444c70ed1c9abbcd20cdeef6");
        HttpApiClient_pwhTest.getInstance().init(httpParam);



    }

    public static void queryUserListHttpTest(){
        HttpApiClient_pwhTest.getInstance().queryUserList("default" , "default" , new ApiCallback() {

            public void onFailure(ApiRequest request, Exception e) {
                e.printStackTrace();
            }


            public void onResponse(ApiRequest request, ApiResponse response) {
                try {
                    System.out.println(getResultString(response));
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });
    }

    public static void queryUserListHttpSyncTest() throws IOException{
        ApiResponse response = HttpApiClient_pwhTest.getInstance().queryUserList_syncMode("default" , "default");
        System.out.println(getResultString(response));
    }




    private static String getResultString(ApiResponse response) throws IOException {
        StringBuilder result = new StringBuilder();
        result.append("Response from backend server").append(SdkConstant.CLOUDAPI_LF).append(SdkConstant.CLOUDAPI_LF);
        result.append("ResultCode:").append(SdkConstant.CLOUDAPI_LF).append(response.getCode()).append(SdkConstant.CLOUDAPI_LF).append(SdkConstant.CLOUDAPI_LF);
        if(response.getCode() != 200){
            result.append("Error description:").append(response.getHeaders().get("X-Ca-Error-Message")).append(SdkConstant.CLOUDAPI_LF).append(SdkConstant.CLOUDAPI_LF);
        }

        result.append("ResultBody:").append(SdkConstant.CLOUDAPI_LF).append(new String(response.getBody() , SdkConstant.CLOUDAPI_ENCODING));

        return result.toString();
    }

}
