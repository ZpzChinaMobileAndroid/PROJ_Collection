package com.zhongji.collection.launch;

import android.content.Intent;
import android.os.Bundle;

import com.zhongji.collection.base.BaseActivity;
import com.zhongji.collection.login.LoginActivity;

/**
 * 空
 * @author Administrator
 *
 */
public class EmptyActivity extends BaseActivity{
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		startActivity(new Intent(EmptyActivity.this, LoginActivity.class));
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		super.onNewIntent(intent);
		finish();
	}
}
