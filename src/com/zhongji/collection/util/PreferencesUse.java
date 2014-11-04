package com.zhongji.collection.util;


import android.content.Context;

/**
 * @package com.ananda.tailing.bike.util
 * @description: 
 * @version 1.0
 * @author JackieCheng
 * @email xiaming5368@163.com
 * @date 2014-1-21 下午5:23:17
 */
public class PreferencesUse {
	
	//登录状态
	public static final String USER_PHONE = "phone";
	public static final String USER_PASSWORD = "password";
	public static final String USER_TOKEN = "token";
	public static final String IS_LOGIN = "is_login";
	public static final String USER_IMG = "img";
	
	public static String getPhone(Context context){
		return PreferencesUtils.getString(context, PreferencesUse.USER_PHONE, "");
	}
	
	public static String getPassword(Context context){
		return PreferencesUtils.getString(context, PreferencesUse.USER_PASSWORD, "");
	}
	
	public static String getToken(Context context){
		return PreferencesUtils.getString(context, PreferencesUse.USER_TOKEN, "");
	}
	
	public static boolean getIs_Login(Context context){
		return PreferencesUtils.getBoolean(context, PreferencesUse.IS_LOGIN, false);
	}
	
	public static String getImg(Context context){
		return PreferencesUtils.getString(context, PreferencesUse.USER_IMG, "");
	}
	
	public static void saveData(Context context, String phone, String password, String token, boolean bool){
		PreferencesUtils.putString(context, PreferencesUse.USER_PHONE, phone);
		PreferencesUtils.putString(context, PreferencesUse.USER_PASSWORD, password);
		PreferencesUtils.putString(context, PreferencesUse.USER_TOKEN, token);
		PreferencesUtils.putBoolean(context, PreferencesUse.IS_LOGIN, bool);
	}
	
	public static void saveIs_Login(Context context, boolean bool){
		PreferencesUtils.putBoolean(context, PreferencesUse.IS_LOGIN, bool);
	}
	
	public static void saveImg(Context context, String img){
		PreferencesUtils.putString(context, PreferencesUse.USER_IMG, img);
	}
	
	public static void saveToken(Context context, String token){
		PreferencesUtils.putString(context, PreferencesUse.USER_TOKEN, token);
	}
	
	public static void savePassword(Context context, String password){
		PreferencesUtils.putString(context, PreferencesUse.USER_PASSWORD, password);
	}
}
