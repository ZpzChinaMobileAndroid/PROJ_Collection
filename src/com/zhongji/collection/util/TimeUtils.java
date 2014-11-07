package com.zhongji.collection.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {

	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	
	public static String longtostr(long lon){
		return sdf.format(new Date(lon));
	}
}
