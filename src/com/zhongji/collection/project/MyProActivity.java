package com.zhongji.collection.project;

import android.os.Bundle;

import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.base.BaseSecondActivity;

/**
 * 我的项目
 * @author admin
 *
 */
public class MyProActivity extends BaseSecondActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mypro);
	}
	
	@Override
	protected void init() {
		// TODO Auto-generated method stub
		
		setTitle("我的项目");
		
	}

}
