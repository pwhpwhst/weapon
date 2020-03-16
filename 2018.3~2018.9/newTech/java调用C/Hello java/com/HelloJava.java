package com;
public class HelloJava{

    public native void helloWorld(); // 注意，这个native方法就是调用C语言接口用的
    static{
        System.load("C://Users//Administrator//Desktop//java调用C//Hello java//com//HelloC.dll");  // 这行是调用动态链接库
//			System.loadLibrary("HelloC");
    }

	public static void main(String[] args){
		new HelloJava().helloWorld();
	}
}