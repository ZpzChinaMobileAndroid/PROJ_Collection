package com.zhongji.collection.base;

import net.tsz.afinal.FinalActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import com.zhongji.collection.util.ProgressDialogUtils;
import com.zhongji.collection.util.ToastUtils;

public abstract class BaseActivity extends FinalActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	} 
	
	protected abstract void init();
	 
	public void showProgressDialog() {
		ProgressDialogUtils.getStance(this).showProgressDialog();
	}
	
	public void showProgressDialog(String msg) {
		ProgressDialogUtils.getStance(this).showProgressDialog(msg);
	}
	
	public void dismissProgressDialog() {
		ProgressDialogUtils.getStance(this).dismissProgressDialog();
	}
	
	public void showShortToast(String msg) {
		ToastUtils.getStance(this).showShortToast(msg);
	}
	
	public void showNetShortToast(int statusCode){
		ToastUtils.getStance(this).showNetShortToast(statusCode);
	}
	
	public void showAkertDialog(String message, DialogInterface.OnClickListener listener){
		new AlertDialog.Builder(this).setTitle("提示")
		.setIcon(android.R.drawable.ic_dialog_alert)
		.setMessage(message)
		.setPositiveButton("确定", listener).setNegativeButton("取消", null).show();
	}
}
