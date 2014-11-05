package com.zhongji.collection.base;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhongji.collection.android.phone.R;

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
		tv_left.setBackgroundResource(R.drawable.pro_back);
		tv_left.setOnClickListener(this);
		tv_left.setText("");
		tv_left.setVisibility(View.VISIBLE);
	}
	
	public void setRightBtn() {
		tv_right = (TextView) findViewById(R.id.tv_right);
		tv_right.setOnClickListener(this);
		tv_right.setText("");
		tv_right.setVisibility(View.VISIBLE);
		tv_right.setBackgroundResource(R.drawable.pro_gou);
	}
	
	public void onClick(View v) {
		if (v.getId() == R.id.tv_left) {
			// 左
			finish();
		} else if (v.getId() == R.id.tv_right) {
			// 右
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
