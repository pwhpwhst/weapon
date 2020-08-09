package com.pwhTest.httpTool;

import java.util.ArrayList;
import java.util.List;

import java.io.IOException;  
import java.io.UnsupportedEncodingException;
import org.apache.http.HttpEntity;  
import org.apache.http.HttpResponse;  
import org.apache.http.HttpStatus;  
import org.apache.http.NameValuePair;  
import org.apache.http.StatusLine;  
import org.apache.http.client.HttpClient;  
import org.apache.http.client.entity.UrlEncodedFormEntity;  
import org.apache.http.client.methods.CloseableHttpResponse;  
import org.apache.http.client.methods.HttpGet;  
import org.apache.http.client.methods.HttpPost;  
import org.apache.http.entity.StringEntity;  
import org.apache.http.impl.client.CloseableHttpClient;  
import org.apache.http.impl.client.DefaultHttpClient;  
import org.apache.http.impl.client.HttpClients;  
import org.apache.http.message.BasicNameValuePair;  
import org.apache.http.protocol.HTTP;  
import org.apache.http.util.EntityUtils;  

public class HttpTool
{
	public static String doGet(String url) {
        try {
        	HttpClient client = new DefaultHttpClient();
            //发送get请求
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);
 
            /**请求发送成功，并得到响应**/
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                /**读取服务器返回过来的json字符串数据**/
                String strResult = EntityUtils.toString(response.getEntity());
                
                return strResult;
            }
        } 
        catch (IOException e) {
        	e.printStackTrace();
        }
        
        return null;
	}

	public static List<String> analyzeText(String str){
		List<String> resultList=new ArrayList<String>();
		try{
			String url="http://api.pullword.com/post.php";
			url+="?source="+java.net.URLEncoder.encode(str,"utf-8");
			url+="&param1=0";
			url+="&param2=1";
			
			String result=HttpTool.doGet(url);
			String[] resultArr=result.split("\n");
			for(int i1=0;i1<resultArr.length;i1++){
				if(resultArr[i1].indexOf(":")>=0){
					String word=resultArr[i1].split(":")[0];
					Double num=Double.valueOf(resultArr[i1].split(":")[1]);
					if(num>=0.6d){
						resultList.add(word);
					}
				}
			}
			 
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
		return resultList;
	}


}