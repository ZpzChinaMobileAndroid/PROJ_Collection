package com.zhongji.collection.util;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.apache.http.entity.StringEntity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhongji.collection.network.HttpRestClient;

public class JsonUtils {

	/**
	 * 转换
	 * @param requestParams
	 * @return
	 */
	public static StringEntity change(Map<String, String> requestParams, boolean bool){
		if(bool){
			requestParams.put("token", HttpRestClient.TOKEN);
		}
		StringEntity res = null;
		try {
			res = new StringEntity(JSON.toJSONString(requestParams));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	/**
	 * 转换
	 * @param json
	 * @return
	 */
	public static String parseString(String json){
		return JSONObject.parseObject(json).getString("d");
	}
}
