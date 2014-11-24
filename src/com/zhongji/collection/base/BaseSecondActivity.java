package com.zhongji.collection.base;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.login.EditPasswordActivity;
import com.zhongji.collection.util.DataCleanManager;
import com.zhongji.collection.util.DensityUtil;

public abstract class BaseSecondActivity extends BaseActivity implements OnClickListener{
	
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
		
//		//侧滑设置
//	    initMenu();
				
		init();
		
	}
	
	public void setLeftBtn() {
		tv_left = (TextView) findViewById(R.id.tv_left);
		setDistanceLeft(tv_left, R.drawable.pro_back);
		tv_left.setOnClickListener(this);
		tv_left.setTextColor(Color.TRANSPARENT);
		tv_left.setVisibility(View.VISIBLE);
	}
	
	public void setRightBtn() {
		tv_right = (TextView) findViewById(R.id.tv_right);
		setDistanceRight(tv_right, R.drawable.pro_gou);
		tv_right.setOnClickListener(this);
		tv_right.setTextColor(Color.TRANSPARENT);
		tv_right.setVisibility(View.VISIBLE);
	}
	
	public void setRightBtn(OnClickListener listener) {
		tv_right = (TextView) findViewById(R.id.tv_right);
		setDistanceRight(tv_right, R.drawable.pro_gou);
		tv_right.setOnClickListener(listener);
		tv_right.setTextColor(Color.TRANSPARENT);
		tv_right.setVisibility(View.VISIBLE);
	}
	
	public void setRightBtnUpload(OnClickListener listener) {
		tv_right = (TextView) findViewById(R.id.tv_right);
		setDistanceRight(tv_right, R.drawable.ic_upload);
		tv_right.setOnClickListener(listener);
		tv_right.setTextColor(Color.TRANSPARENT);
		tv_right.setVisibility(View.VISIBLE);
	}
	
	public void setRightBtnGone() {
		tv_right = (TextView) findViewById(R.id.tv_right);
		tv_right.setVisibility(View.GONE);
	}
	
	public void setRightBtnEdit(OnClickListener listener) {
		tv_right = (TextView) findViewById(R.id.tv_right);
		setDistanceRight(tv_right, R.drawable.ic_edit);
		tv_right.setOnClickListener(listener);
		tv_right.setTextColor(Color.TRANSPARENT);
		tv_right.setVisibility(View.VISIBLE);
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
	}
	
	/**
	 * 设置搜索背景
	 * 
	 * @param title
	 */
	public void setTitlebackgroud(String title, OnClickListener listener) {
		LinearLayout layout_title = (LinearLayout) findViewById(R.id.layout_title);
		RelativeLayout.LayoutParams rl = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		rl.addRule(RelativeLayout.LEFT_OF, R.id.tv_right);
		rl.addRule(RelativeLayout.RIGHT_OF, R.id.tv_left);
		rl.addRule(RelativeLayout.CENTER_IN_PARENT);
		layout_title.setLayoutParams(rl);
		layout_title.setOnClickListener(listener);
		layout_title.setBackgroundResource(R.drawable.seach_basemap);
		TextView tv_title = (TextView) findViewById(R.id.tv_title);
		tv_title.setOnClickListener(listener);
		tv_title.setGravity(Gravity.CENTER);
		tv_title.setTextSize(TypedValue.COMPLEX_UNIT_SP, getResources().getDimension(R.dimen.activity_14_size) / getResources().getDisplayMetrics().density);
		Drawable drawable = getResources().getDrawable(R.drawable.seach_find_seach);
		drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
		tv_title.setCompoundDrawables(drawable, null, null, null);
		tv_title.setPadding(0, DensityUtil.dip2px(this, 5), 0, DensityUtil.dip2px(this, 5));
		tv_title.setText(title);
	}
	
	/**
	 * 设置搜索条
	 * 
	 * @param title
	 */
	public void setTitleSeach(OnClickListener listener) {
		TextView tv_title = (TextView) findViewById(R.id.tv_title);
		EditText et_seach_result_start = (EditText) findViewById(R.id.et_seach_result_start);
		tv_right = (TextView) findViewById(R.id.tv_right);
		tv_right.setOnClickListener(listener);
		tv_right.setText("取消");
		tv_title.setVisibility(View.GONE);
		et_seach_result_start.setVisibility(View.VISIBLE);
		
	}
	
	public void onClick(View v) {
		if (v.getId() == R.id.tv_left) {
			// 左
			finish();
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
							.cleanApplicationData(BaseSecondActivity.this);
				}
			});
		} else if (v.getId() == R.id.tv_editpass) {
			// 修改密码
			Intent intent = new Intent(BaseSecondActivity.this, EditPasswordActivity.class);
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
