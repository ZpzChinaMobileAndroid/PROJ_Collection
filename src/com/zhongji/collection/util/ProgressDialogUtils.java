package com.zhongji.collection.util;

import android.app.ProgressDialog;
import android.content.Context;

import com.zhongji.collection.android.phone.R;

public class ProgressDialogUtils {

	private static ProgressDialogUtils mProgressDialogUtils;
	private ProgressDialog mProgressDialog = null;
	private Context context;
	private int count = 0;		//同时调用多个接口，全部完成取消对话框

	public ProgressDialogUtils(Context context) {
		mProgressDialog = new ProgressDialog(context);
		this.context = context;
	}

	public static ProgressDialogUtils getStance(Context context) {
		if (mProgressDialogUtils == null) {
			mProgressDialogUtils = new ProgressDialogUtils(context);
		}
		return mProgressDialogUtils;
	}
	
	public void showProgressDialog() {
		showProgressDialog(context.getString(R.string.please_wait_str));
	}
	
	public void showProgressDialog(String msg) {
		count = count + 1;
		if(mProgressDialog!=null && !mProgressDialog.isShowing()){
			mProgressDialog.setTitle(null);
			mProgressDialog.setMessage(msg);
			mProgressDialog.setCancelable(false);
			mProgressDialog.show();
		}
	}

	public void dismissProgressDialog() {
		count = 0;
		count = count - 1;
		if (mProgressDialog!=null && mProgressDialog.isShowing() && count == 0) {
			mProgressDialog.dismiss();
		}
	}
}
