package com.zhongji.collection.util;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.os.Environment;

public class Util {

	/**
	 * sd卡程序目录
	 */
	public static final String PROJECT_SD_DIR = Environment.getExternalStorageDirectory().getPath()
			+ File.separator
			+ "microsearch";
	/**
	 * 图片缓存目录
	 */
	public static final String PROJECT_CACHE_DIR = PROJECT_SD_DIR
			+ File.separator + "cache";

	/**
	 * 验证输入手机号码
	 * 
	 * @param 待验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean isPhone(String str) {
		String regex = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
		return match(regex, str);
	}

	/**
	 * 检测邮箱地址是否合法
	 * 
	 * @param email
	 * @return true合法 false不合法
	 */
	public static boolean isEmail(String email) {
		if (null == email || "".equals(email))
			return false;
		// Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}"); //简单匹配
		Pattern p = Pattern
				.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");// 复杂匹配
		Matcher m = p.matcher(email);
		return m.matches();
	}

	/**
	 * @param regex
	 *            正则表达式字符串
	 * @param str
	 *            要匹配的字符串
	 * @return 如果str 符合 regex的正则表达式格式,返回true, 否则返回 false;
	 */
	private static boolean match(String regex, String str) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}

	/**
	 * 获取不同的率换算
	 * 
	 * @param count
	 * @return
	 */
	public static int getAcception(double count) {
		// return Integer.valueOf(new
		// DecimalFormat("0").format(Double.valueOf(count) * 100));
		return (int) (count * 100);
	}

}
