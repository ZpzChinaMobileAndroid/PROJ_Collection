package com.zhongji.collection.util;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;

import com.zhongji.collection.android.phone.R;

public class ProgressDialogUtils {

	private static ProgressDialog mProgressDialog = null;
	private static int count = 0;		//同时调用多个接口，全部完成取消对话框

	
	public static void showProgressDialog(Context context) {
		Activity act = (Activity) context;
		mProgressDialog = new ProgressDialog(context);
		showProgressDialog(context, act.getString(R.string.please_wait_str));
	}
	
	public static void showProgressDialog(Context context, String msg) {
		count = count + 1;
		mProgressDialog = new ProgressDialog(context);
		if(mProgressDialog!=null && !mProgressDialog.isShowing()){
			mProgressDialog.setTitle(null);
			mProgressDialog.setMessage(msg);
			mProgressDialog.setCancelable(true);
			mProgressDialog.show();
		}
	}

	public static void dismissProgressDialog(Context context) {
//		count = count - 1;
		count = 0;
		if(count<0){
			count = 0;
		}
		Activity act = (Activity) context;
		if (mProgressDialog!=null && mProgressDialog.isShowing() && count == 0 && !act.isFinishing()) {
			mProgressDialog.dismiss();
		}
	}
}
