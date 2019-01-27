package com.pwhTest.sha1Test;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;


public class SHA1Test{

    private static final String MAC_NAME = "HmacSHA1";  
    private static final String ENCODING = "UTF-8";  


	private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";

	public static String genHMAC(String data, String key) {
		byte[] result = null;
		try {
			//根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称  
			SecretKeySpec signinKey = new SecretKeySpec(key.getBytes(), HMAC_SHA1_ALGORITHM);
			//生成一个指定 Mac 算法 的 Mac 对象  
			Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
			//用给定密钥初始化 Mac 对象  
			mac.init(signinKey);
			//完成 Mac 操作   
			byte[] rawHmac = mac.doFinal(data.getBytes());
			result = Base64.encodeBase64(rawHmac);
 
		} catch (NoSuchAlgorithmException e) {
			System.err.println(e.getMessage());
		} catch (InvalidKeyException e) {
			System.err.println(e.getMessage());
		}
		if (null != result) {
			return new String(result);
		} else {
			return null;
		}
	}




	public static void main(String args[])  throws Exception{

		String encryptText="GET&%2F&AccessKeyId%3Dtestid&Action%3DDescribeRegions&Format%3Djson"+
		"&SignatureMethod%3DHmac-SHA1&SignatureNonce%3Dd48e931b-90c9-49c7-ac86-a70dd3607c88&SignatureVersion%3D1.0"+
		"&Timestamp%3D2016-09-27T09%253A08%253A30Z&Version%3D2016-07-14";
		String encryptKey="testsecret&";
//		byte[] data=HmacSHA1Encrypt(encryptText,encryptKey);
//		String str= new String(data,ENCODING);
		String str=genHMAC(encryptText, encryptKey);

		System.out.println(str);
	}
}