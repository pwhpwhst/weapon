package com;
public class HelloJava{

    public native void helloWorld(); // ע�⣬���native�������ǵ���C���Խӿ��õ�
    static{
        System.load("C://Users//Administrator//Desktop//java����C//Hello java//com//HelloC.dll");  // �����ǵ��ö�̬���ӿ�
//			System.loadLibrary("HelloC");
    }

	public static void main(String[] args){
		new HelloJava().helloWorld();
	}
}