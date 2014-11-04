package com.zhongji.collection.util;

import android.content.Context;
import android.widget.Toast;

import com.zhongji.collection.network.HttpAPI;

public class ToastUtils {

	private static ToastUtils mToastUtils = null;
	private static Toast mToast = null;

	public ToastUtils(Context context) {
		mToast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
	}

	public static ToastUtils getStance(Context context) {
		if (mToastUtils == null) {
			mToastUtils = new ToastUtils(context);
		}
		return mToastUtils;
	}
	
	public void showShortToast(String msg) {
		mToast.setText(msg);
		mToast.show();
	}
	
	public void showNetShortToast(int statusCode){
		switch(statusCode){
		case HttpAPI.HTTP_TIME_OUT:{
			showShortToast("网络连接超时");
			break;
		}
		case HttpAPI.HTTP_NOT_FOUND_SERVICE:{
			showShortToast("找不到服务");
			break;
		}
		case HttpAPI.HTTP_SERVER_ERROR:{
			showShortToast("服务器内部错误");
			break;
		}
		default:
			showShortToast("未知错误");
			break;
		}
	}
}
