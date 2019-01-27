package com.pwhTest.rsaTest;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;


public class RsaTest{
	public static void main(String args[]){
		try{
			KeyPair keyPair = RSAUtil.getKeyPair();
			String publicKeyStr = RSAUtil.getPublicKey(keyPair);
			String privateKeyStr = RSAUtil.getPrivateKey(keyPair);
			System.out.println("RSA公钥Base64编码:" + publicKeyStr);
			System.out.println("RSA私钥Base64编码:" + privateKeyStr);


			//hello, i am infi, good night!加密
			String message = "hello, i am infi, good night!";
			System.out.println("原字符串：" + message);
			//将Base64编码后的公钥转换成PublicKey对象
			PublicKey publicKey = RSAUtil.string2PublicKey(publicKeyStr);
			//用公钥加密
			byte[] publicEncrypt = RSAUtil.publicEncrypt(message.getBytes(), publicKey);
			//加密后的内容Base64编码
			String byte2Base64 = RSAUtil.byte2Base64(publicEncrypt);
			System.out.println("公钥加密并Base64编码的结果：" + byte2Base64);



			//将Base64编码后的私钥转换成PrivateKey对象
			PrivateKey privateKey = RSAUtil.string2PrivateKey(privateKeyStr);
			//加密后的内容Base64解码
			byte[] base642Byte = RSAUtil.base642Byte(byte2Base64);
			//用私钥解密
			byte[] privateDecrypt = RSAUtil.privateDecrypt(base642Byte, privateKey);
			//解密后的明文
			System.out.println("解密后的明文: " + new String(privateDecrypt));

		}catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("afsadfg");
	}
}