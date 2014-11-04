package com.zhongji.collection.base;

import net.tsz.afinal.FinalActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

import com.slidingmenu.lib.SlidingMenu;
import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.util.ProgressDialogUtils;
import com.zhongji.collection.util.ToastUtils;

public abstract class BaseActivity extends FinalActivity{
	
	protected SlidingMenu menu;
	protected TextView tv_name,abc,abcc,bcdd;  

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
	
	protected void initMenu() {
		DisplayMetrics dm = new DisplayMetrics();getWindowManager().getDefaultDisplay().getMetrics(dm);
		int width = dm.widthPixels;//宽度
//		int height = dm.heightPixels ;//高度
		menu = new SlidingMenu(this);
		menu.setMode(SlidingMenu.LEFT);
	    menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);//设置滑动的屏幕局限，该设置为全屏区域都可以滑动
	    menu.setShadowWidthRes(R.dimen.shadow_width);//设置暗影的宽度
	    menu.setShadowDrawable(R.drawable.shadow);//设置暗影
	    menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);//SlidingMenu划出时主页面显示的剩余宽度
	    menu.setBehindWidth((int)(width*(5.0/6.0)));//设置SlidingMenu菜单的宽度
	    menu.setFadeDegree(0.35f);//SlidingMenu滑动时的渐变程度
	    menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);//使SlidingMenu附加在Activity上
	    menu.setMenu(R.layout.activity_retreat);//设置menu的布局文件
		tv_name = (TextView) menu.findViewById(R.id.tv_name);// 用户名显示
	
	}
}
