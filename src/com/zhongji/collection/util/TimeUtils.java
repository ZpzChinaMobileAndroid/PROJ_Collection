package com.zhongji.collection.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {

	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
	
	public static String longtostr(long lon){
		return sdf.format(new Date(lon));
	}
	
	public static String longtostr2(long lon){
		return sdf2.format(new Date(lon));
	}
	
	public static long strtolong(String string){
		try {
			return sdf2.parse(string).getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
}

