package com.zhongji.collection.network;

import org.apache.http.entity.StringEntity;

import android.content.Context;
import android.text.TextUtils;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

/**
 * @package com.ananda.tailing.bike.util
 * @description: 网络请求连接管理类
 * @version 1.0
 * @author JackieCheng
 * @email xiaming5368@163.com
 * @date 2014-3-6 下午5:14:10
 */
public class HttpRestClient {

//	private static final String BASE_URL = "http://EIPQAS.zhongjichina.com/";
	private static final String BASE_URL = "http://192.168.222.95:802/";
	private static final String PRO_URL = "ZPZChina.svc/";
	public static final String PIC_URL = "http://192.168.222.95:801/";
	
	private static final int TIME_OUT = 10 * 1000;// 10s 超时
	public static String TOKEN = "";
	private static AsyncHttpClient httpClient = new AsyncHttpClient();

	static {
		httpClient.setTimeout(TIME_OUT);
	}

	/**
	 * get请求
	 * 
	 * @param url
	 * @param params
	 * @param responseHandler
	 */
	public static void get(String url, RequestParams params,
			final ResponseUtils responseUtils) {
		get(url, params, responseUtils, true);
	}

	public static void get(String url, RequestParams requestParams,
			final ResponseUtils responseUtils, boolean isToken) {
		responseUtils.setUrl(url);
		responseUtils.setRequestParams(requestParams);
		responseUtils.setToken(isToken);

		httpClient.get(getAbsoluteUrl(url), requestParams, responseUtils);
	}

	public static void get(Context context, String url, String token, RequestParams requestParams, ResponseUtils responseUtils) {

		System.out.println("url:"+getAbsoluteUrl(url) + token);
		System.out.println("requestParams:"+requestParams.toString());
		httpClient.get(context, getAbsoluteUrl(url) + token, requestParams, responseUtils);
	}
	
	public static void get(Context context, String url, ResponseUtils responseUtils) {

		System.out.println("url:"+getAbsoluteUrl("") + url);
		httpClient.get(context, getAbsoluteUrl("") + url, responseUtils);
	}
	
	/**
	 * post请求
	 * 
	 * @param url
	 * @param params
	 * @param responseHandler
	 */

	public static void post(String url, RequestParams params,
			final ResponseUtils responseUtils) {
		post(url, params, responseUtils, true);
	}

	public static void post(String url, RequestParams requestParams,
			final ResponseUtils responseUtils, boolean isToken) {
		responseUtils.setUrl(url);
		responseUtils.setRequestParams(requestParams);
		responseUtils.setToken(isToken);

		httpClient.post(getAbsoluteUrl(url), requestParams, responseUtils);
	}

	public static void post(Context context, String url, StringEntity requestParams, ResponseUtils responseUtils) {

		httpClient.post(context, getAbsoluteUrl(url), requestParams, "application/json; charset=UTF-8", responseUtils);
	}
	
	public static void put(Context context, String url, StringEntity requestParams, ResponseUtils responseUtils) {

		httpClient.put(context, getAbsoluteUrl(url), requestParams, "application/json; charset=UTF-8", responseUtils);
	}
	
	public static void upload(String keyword, Context context, String url, StringEntity requestParams, ResponseUtils responseUtils) {
		if(TextUtils.isEmpty(keyword)){
			//新增
			httpClient.post(context, getAbsoluteUrl(url), requestParams, "application/json; charset=UTF-8", responseUtils);
		}else{
			//修改
			httpClient.put(context, getAbsoluteUrl(url), requestParams, "application/json; charset=UTF-8", responseUtils);
		}
		
	}
	
	@SuppressWarnings("unused")
	private static String javatophptime(String time) {
		// TODO Auto-generated method stub
		// JAVA时间戳长度是13位，如：1294890876859
		// PHP时间戳长度是10位， 如： 1294890859
		// 主要最后三位的不同，JAVA时间戳在PHP中使用，去掉后三位
		return time.substring(0, 10);
	}

	private static String getAbsoluteUrl(String relativeUrl) {
		return BASE_URL + PRO_URL + relativeUrl;
	}
}
