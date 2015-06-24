package com.wxframe.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 安全加密工具类
 * @author GeneZ
 * @version 1.0.0
 */
public class SecurityUtil {
	
	public static final String SHA_1 = "SHA-1";
	public static final String MD5 = "MD5";
	
	/**
	 * MD5加密方式加密
	 * @param strSrc 明文
	 * @return 密文
	 */
	public static String encodeByMD5(String strSrc){
		return encode(strSrc,MD5);
	}
	
	/**
	 * SHA1加密方式加密
	 * @param strSrc 明文
	 * @return 密文
	 */
	public static String encodeBySHA1(String strSrc){
		return encode(strSrc,SHA_1);
	}
	
	/**
	 * 根据类型加密字符串
	 * @param strSrc 明文
	 * @param encodeType 加密类型
	 * @return 密文
	 */
	public static String encode(String strSrc, String encodeType) {
		MessageDigest md = null;
		String strDes = null;
		byte[] bt = strSrc.getBytes();
		try {
			if (encodeType == null || "".equals(encodeType))
				encodeType = MD5;//默认使用MD5
			md = MessageDigest.getInstance(encodeType);
			md.update(bt);
			strDes = bytes2Hex(md.digest());
		} catch (NoSuchAlgorithmException e) {
			return strSrc;
		}
		return strDes;
	}

	public static String bytes2Hex(byte[] bts) {
		String des = "";
		String tmp = null;
		for (int i = 0; i < bts.length; i++) {
			tmp = (Integer.toHexString(bts[i] & 0xFF));
			if (tmp.length() == 1) {
				des += "0";
			}
			des += tmp;
		}
		return des;
	}
}
