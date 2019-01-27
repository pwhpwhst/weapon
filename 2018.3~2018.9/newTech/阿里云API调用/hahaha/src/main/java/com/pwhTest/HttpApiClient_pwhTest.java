//
//  Created by  fred on 2017/1/12.
//  Copyright © 2016年 Alibaba. All rights reserved.
//

package com.pwhTest;

import com.alibaba.cloudapi.sdk.client.ApacheHttpClient;
import com.alibaba.cloudapi.sdk.enums.Scheme;
import com.alibaba.cloudapi.sdk.enums.HttpMethod;
import com.alibaba.cloudapi.sdk.model.ApiRequest;
import com.alibaba.cloudapi.sdk.model.ApiResponse;
import com.alibaba.cloudapi.sdk.model.ApiCallback;
import com.alibaba.cloudapi.sdk.model.HttpClientBuilderParams;
import com.alibaba.cloudapi.sdk.enums.ParamPosition;
import com.alibaba.cloudapi.sdk.enums.WebSocketApiType;




public class HttpApiClient_pwhTest extends ApacheHttpClient{
    public final static String HOST = "a705188388e44bb28b90ca51026c0593-cn-shenzhen.alicloudapi.com";
    static HttpApiClient_pwhTest instance = new HttpApiClient_pwhTest();
    public static HttpApiClient_pwhTest getInstance(){return instance;}

    public void init(HttpClientBuilderParams httpClientBuilderParams){
        httpClientBuilderParams.setScheme(Scheme.HTTP);
        httpClientBuilderParams.setHost(HOST);
        super.init(httpClientBuilderParams);
    }




    public void queryUserList(String id , String name , ApiCallback callback) {
        String path = "/querryUserList";
        ApiRequest request = new ApiRequest(HttpMethod.GET , path);
        request.addParam("id" , id , ParamPosition.QUERY , false);
        request.addParam("name" , name , ParamPosition.QUERY , false);



        sendAsyncRequest(request , callback);
    }

    public ApiResponse queryUserList_syncMode(String id , String name) {
        String path = "/querryUserList";
        ApiRequest request = new ApiRequest(HttpMethod.GET , path);
        request.addParam("id" , id , ParamPosition.QUERY , false);
        request.addParam("name" , name , ParamPosition.QUERY , false);



        return sendSyncRequest(request);
    }

}