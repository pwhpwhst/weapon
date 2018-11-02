package com.pwh.axis2Client;

import javax.xml.namespace.QName;

import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

public class Axis2Client{

	public static void main(String[] args){

        		        try {
            //����tomcat�˿�Ĭ��Ϊ8081,������wsdl��ַ��һ����
            EndpointReference targetEPR = new EndpointReference("http://localhost:8080/axis2/services/Version.VersionHttpSoap11Endpoint/");  
            RPCServiceClient sender = new RPCServiceClient();  
            Options options = sender.getOptions();  
            options.setTimeOutInMilliSeconds(2*20000L);//��ʱʱ��20s  
            options.setTo(targetEPR);  
            /**
             * ����:
             * 1������ҳ��ִ�� wsdl��xs:schema��ǩ��targetNamespace·��
             * <xs:schema  targetNamespace="http://test.axiswevservice.com">
             * 2��<xs:element name="test"> ======�����ǩ��name��ֵ
             * 
             */
            QName qname = new QName("http://test.axiswevservice.com", "test"); 
            String str = "czxc";  //���������
            Object[] param = new Object[]{str};  
            Class<?>[] types = new Class[]{String.class};  //������Է�ֵ���͵�  
            /** 
             * RPCServiceClient���invokeBlocking����������WebService�еķ����� 
             * invokeBlocking�������������� 
             * ��һ��������������QName���󣬱�ʾҪ���õķ������� 
             * �ڶ���������ʾҪ���õ�WebService�����Ĳ���ֵ����������ΪObject[]�� 
             * ������������ʾWebService�����ķ���ֵ���͵�Class���󣬲�������ΪClass[]�� 
             *  
             * ������û�в���ʱ��invokeBlocking�����ĵڶ�������ֵ������null����Ҫʹ��new Object[]{}�� 
             */  
            Object[] response1 = sender.invokeBlocking(qname, param, types);  
            System.out.println(response1[0]);
        } catch (AxisFault e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}

}
