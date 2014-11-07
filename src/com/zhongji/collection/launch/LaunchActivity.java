package com.zhongji.collection.launch;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.base.BaseActivity;

/**
 * 启动
 * @author Administrator
 *
 */
public class LaunchActivity extends BaseActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.launch_activity_launch);
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				startActivity(new Intent(LaunchActivity.this, GuideActivity.class));
				finish();
			}
		}, 1500);
	}
 
	@Override
	protected void init() {
		// TODO Auto-generated method stub
		
	}
}
