package com.zhongji.collection.project;

import android.os.Bundle;

import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.base.BaseSecondActivity;

/**
 * 全部项目
 * @author admin
 *
 */
public class AllProActivity extends BaseSecondActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_allpro);
	}
	
	@Override
	protected void init() {
		// TODO Auto-generated method stub
		
		setTitle("全部项目");
		
	}

}
