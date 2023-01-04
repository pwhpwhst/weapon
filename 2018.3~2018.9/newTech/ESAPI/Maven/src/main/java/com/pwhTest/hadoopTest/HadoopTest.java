package com.pwhTest.hadoopTest;

import org.owasp.esapi.ESAPI;

class HadoopTest{
	public static void main(String args[]){
		
//			System.out.println(EsapiUtil.xssEncode("acv"));
System.out.println("afsadfg");
System.out.println(ESAPI.encoder().encodeForHTML("<a href='sdfs'></a> < script > alert(); </ script >"));

System.out.println(ESAPI.encoder().encodeForCSS("<a href='sdfs'></a> < script > alert(); </ script >"));


		System.out.println("afsadfg");
	}
}