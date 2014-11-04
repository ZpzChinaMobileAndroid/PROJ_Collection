package com.zhongji.collection.base;

import java.util.LinkedHashMap;
import java.util.Map;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.entity.UserListBean;
import com.zhongji.collection.login.LoginActivity;
import com.zhongji.collection.network.HttpAPI;
import com.zhongji.collection.network.HttpRestClient;
import com.zhongji.collection.network.ResponseUtils;
import com.zhongji.collection.util.DataCleanManager;
import com.zhongji.collection.util.JsonUtils;

public abstract class BaseIndexActivity extends BaseActivity implements OnClickListener{

	private TextView tv_left, tv_right;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	public void setContentView(int layoutResID) {
		// TODO Auto-generated method stub
	    
		View view = View.inflate(this, R.layout.base_activity_index, null);
		LinearLayout base_layout_view = (LinearLayout) view.findViewById(R.id.base_layout_view);
		View child = View.inflate(this, layoutResID, null);
		child.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
		base_layout_view.addView(child);
		setContentView(view);
		
		//侧滑设置
	    initMenu();
	    setLeftBtn();
				
		init();
	}

	public void setMenuName(String name){
		tv_name.setText(name);
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
	
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			showAkertDialog("是否退出！", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog,
						int which) {
					// TODO Auto-generated method stub
					finish();
				}
			});
			break;
		default:
			break;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	/**
	 * 获取当前activity
	 * @return
	 */
	@SuppressWarnings("unused")
	private String getRunningActivityName(){    
        String contextString = BaseIndexActivity.this.toString();
        return contextString.substring(contextString.lastIndexOf(".")+1, contextString.indexOf("@"));
	}
	
	public void setLeftBtn() {
		tv_left = (TextView) findViewById(R.id.tv_left);
		tv_left.setBackgroundResource(R.drawable.home_menu);
		tv_left.setOnClickListener(this);
		tv_left.setText("");
		tv_left.setVisibility(View.VISIBLE);
//		tv_left.setBackgroundResource(R.drawable.btn_search);
	}

	public void setRightBtn() {
		tv_right = (TextView) findViewById(R.id.tv_right);
		tv_right.setOnClickListener(this);
		tv_right.setText("");
		tv_right.setVisibility(View.VISIBLE);
//		tv_right.setBackgroundResource(R.drawable.btn_sort);
	}

	public void onClick(View v) {
		if (v.getId() == R.id.tv_left) {
			// 左
			menu.showMenu();
		} else if (v.getId() == R.id.tv_right) {
			// 右
		} else if (v.getId() == R.id.tv_clear) {
			// 清除缓存
			showAkertDialog("是否清除所有本地数据！", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog,
						int which) {
					// TODO Auto-generated method stub
					DataCleanManager
							.cleanApplicationData(BaseIndexActivity.this);
				}
			});
		} else if (v.getId() == R.id.tv_editpass) {
			// 修改密码
//			Intent intent = new Intent(BaseIndexActivity.this, Modification.class);
//			startActivity(intent);
		} else if (v.getId() == R.id.tv_logout) {
			// 退出登录 
			showAkertDialog("退出后将清除所有本地数据！", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog,
						int which) {
					// TODO Auto-generated method stub
					showProgressDialog();
					logout();
				}
			});
		}
	}

	/**
	 * 设置标题
	 * 
	 * @param title
	 */
	public void setTitle(String title) {
		TextView tv_title = (TextView) findViewById(R.id.tv_title);
		tv_title.setText(title);
	}
	
	/**
	 * 登出
	 */
	private void logout() {
		Map<String, String> params = new LinkedHashMap<String, String>();
		params.put("deviceType"," null");
		HttpRestClient.post(BaseIndexActivity.this, HttpAPI.USERS_LOGOUT,
				JsonUtils.change(params,true),
				new ResponseUtils(BaseIndexActivity.this) {

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

							Intent intent = new Intent(BaseIndexActivity.this, LoginActivity.class);
							startActivity(intent);
							finish();
						} else {
							showNetShortToast(httpCode);
						}
					}
				});
	}
}
