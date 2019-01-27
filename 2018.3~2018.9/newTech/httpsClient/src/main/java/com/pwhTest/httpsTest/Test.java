package com.pwhTest.httpsTest;


public class Test{
	public static void main(String[] args){ 
        String url = "https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=0&rsv_idx=1&tn=baidu&wd=%E5%B4%A9%E5%9D%8F3&rsv_pq=d5ff942c00000de4&rsv_t=8328ZNTjVV%2FnGlQPI9wLv3OHPfaVkrIZJxuKubU2ZDDGbWYmMdPAsbzAoNY&rqlang=cn&rsv_enter=1&rsv_sug3=14&rsv_sug1=10&rsv_sug7=101";

        String jsonStr = "{xxx}";
        String httpOrgCreateTestRtn = HttpClientUtil.doPost(url, jsonStr, "utf-8");
		System.out.println(httpOrgCreateTestRtn);
    }
} 


