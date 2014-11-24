package com.zhongji.collection.base;

import java.util.LinkedHashMap;
import java.util.Map;

import net.tsz.afinal.FinalActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.slidingmenu.lib.SlidingMenu;
import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.entity.User;
import com.zhongji.collection.entity.UserListBean;
import com.zhongji.collection.login.LoginActivity;
import com.zhongji.collection.network.HttpAPI;
import com.zhongji.collection.network.HttpRestClient;
import com.zhongji.collection.network.ResponseUtils;
import com.zhongji.collection.util.DataCleanManager;
import com.zhongji.collection.util.JsonUtils;
import com.zhongji.collection.util.PreferencesUtils;
import com.zhongji.collection.util.ProgressDialogUtils;
import com.zhongji.collection.util.ToastUtils;

public abstract class BaseActivity extends FinalActivity{
	
	protected SlidingMenu menu;
	protected TextView tv_name;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	} 
	
	protected abstract void init();
	 
	public void showProgressDialog() {
		ProgressDialogUtils.showProgressDialog(this);
	}
	
	public void showProgressDialog(String msg) {
		ProgressDialogUtils.showProgressDialog(this, msg);
	}
	
	public void dismissProgressDialog() {
		ProgressDialogUtils.dismissProgressDialog(this);
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
		
		User user = (User) PreferencesUtils.getObject(BaseActivity.this, PreferencesUtils.PREFERENCE_KEY_USERS);
		if(user!=null){
			tv_name.setText(user.getRealName());
		}
	}
	
	/**
	 * 登出
	 */
	protected void logout() {
		Map<String, String> params = new LinkedHashMap<String, String>();
		params.put("deviceType"," null");
		HttpRestClient.post(BaseActivity.this, HttpAPI.USERS_LOGOUT,
				JsonUtils.change(params,true),
				new ResponseUtils(BaseActivity.this) {

					@Override
					public void getResult(int httpCode, String result) {
						// TODO Auto-generated method stub
						dismissProgressDialog();
						if (httpCode == HttpAPI.HTTP_SUCCESS_CODE) {
							UserListBean bean = JSON.parseObject(
									JsonUtils.parseString(result),
									UserListBean.class);
							if (getData(bean)) {
								return;
							}
							showShortToast("退出登录");
							HttpRestClient.TOKEN = "";
							DataCleanManager.cleanApplicationData(BaseActivity.this);
							PreferencesUtils.putString(BaseActivity.this, PreferencesUtils.PREFERENCE_KEY_TOKEN, "");
							Intent intent = new Intent(BaseActivity.this, LoginActivity.class);
							startActivity(intent);
							finish();
						} else {
							showNetShortToast(httpCode);
						}
					}
				});
	}
}
