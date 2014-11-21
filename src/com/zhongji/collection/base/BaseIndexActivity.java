package com.zhongji.collection.base;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.login.EditPasswordActivity;
import com.zhongji.collection.util.DataCleanManager;

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
//			showAkertDialog("是否退出！", new DialogInterface.OnClickListener() {
//				@Override
//				public void onClick(DialogInterface dialog,
//						int which) {
//					// TODO Auto-generated method stub
//					finish();
//				}
//			});
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
		setDistanceLeft(tv_left, R.drawable.home_menu);
		tv_left.setOnClickListener(this);
		tv_left.setVisibility(View.VISIBLE);
		tv_left.setTextColor(Color.TRANSPARENT);
//		tv_left.setBackgroundResource(R.drawable.btn_search);
	}

	public void setRightBtn() {
		tv_right = (TextView) findViewById(R.id.tv_right);
		tv_right.setOnClickListener(this);
		setDistanceRight(tv_right, 0);
		tv_right.setText("");
		tv_right.setVisibility(View.VISIBLE);
//		tv_right.setBackgroundResource(R.drawable.btn_sort);
	}
	
	private void setDistanceLeft(TextView tv, int resId) {
		Drawable drawable = getResources().getDrawable(resId);
		drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
		tv.setCompoundDrawables(drawable, null, null, null);
		tv.setText("间距");
	}
	
	private void setDistanceRight(TextView tv, int resId) {
		Drawable drawable = getResources().getDrawable(resId);
		drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
		tv.setCompoundDrawables(null, null, drawable, null);
		tv.setText("间距");
		tv.setTextColor(Color.TRANSPARENT);
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
			Intent intent = new Intent(BaseIndexActivity.this, EditPasswordActivity.class);
			startActivity(intent);
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
	
}
