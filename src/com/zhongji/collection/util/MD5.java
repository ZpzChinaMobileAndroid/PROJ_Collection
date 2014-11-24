package com.zhongji.collection.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;




public class MD5 {
	
	
	public static String md5(String string) {
		// TODO Auto-generated method stub
		StringBuffer sb=new StringBuffer();
		try {
			MessageDigest MD5=MessageDigest.getInstance("MD5");
			MD5.update(string.getBytes());
			byte[] digest=MD5.digest();
			for (int i = 0; i < digest.length; i++) {
				int x=digest[i]&0xff;
				if(x<16){
					sb.append(0);
				}
				sb.append(Integer.toHexString(x));
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}
}