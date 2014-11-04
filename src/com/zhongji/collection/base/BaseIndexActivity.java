package com.zhongji.collection.base;

import java.util.LinkedHashMap;
import java.util.Map;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.slidingmenu.lib.SlidingMenu;
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
	private SlidingMenu menu;
	private TextView tv_name;
	
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
				
		init();
		
	}

	private void initMenu() {
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
		setLeftBtn();
	
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
