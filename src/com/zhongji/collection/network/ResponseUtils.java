package com.zhongji.collection.network;

import org.apache.http.Header;

import android.content.Context;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.zhongji.collection.entity.BaseBean;
import com.zhongji.collection.util.ToastUtils;

public abstract class ResponseUtils extends AsyncHttpResponseHandler {

	private Context context;
	private String url;
	private RequestParams requestParams;
	private boolean isToken;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public RequestParams getRequestParams() {
		return requestParams;
	}

	public void setRequestParams(RequestParams requestParams) {
		this.requestParams = requestParams;
	}

	public boolean isToken() {
		return isToken;
	}

	public void setToken(boolean isToken) {
		this.isToken = isToken;
	}

	public ResponseUtils(Context context) {
		this.context = context;
	}

	public void onFailure(int statusCode, Header[] headers,
			byte[] responseBody, Throwable error) {
		// TODO Auto-generated method stub
		if (responseBody == null)
			responseBody = new byte[] {};
		httpInvoke(statusCode, new String(responseBody));
	}

	public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
		// TODO Auto-generated method stub
		httpInvoke(statusCode, new String(responseBody));
	}

	public void httpInvoke(int httpCode, String result) {
		// TODO Auto-generated method stub
		System.out.println("httpCode: " + httpCode);
		System.out.println("result: " + result);
		getResult(httpCode, result);
	}

	public abstract void getResult(int httpCode, String result);

	public boolean getData(BaseBean bean) {
		
		if (!"200".equals(bean.getStatus().getStatusCode())) {
			ToastUtils.getStance(context).showShortToast(bean.getStatus().getErrors());
			return true;
		}

		return false;
	}


}
