package com.wxframe.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具
 * @author GeneZ
 * @version 1.0.0
 */
public class DateUtil {
	private static final SimpleDateFormat yyyyMMddFormat = new SimpleDateFormat("yyyyMMdd");
	
	/**
	 * 获取yyyyMMdd格式的当前时间
	 * @return
	 */
	public static String getCurrentDate2YYYYMMDD(){
		return yyyyMMddFormat.format(new Date());
	}
}
