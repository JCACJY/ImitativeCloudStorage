package cn.cloudstorage.qst.util;

import java.security.MessageDigest;

import sun.misc.BASE64Encoder;

public class MD5Util {
	public static String encoding(String message){
		try{
			MessageDigest md = MessageDigest.getInstance("md5");
			byte b[] = md.digest(message.getBytes());
			BASE64Encoder base64 = new BASE64Encoder();
			return base64.encode(b);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
}
