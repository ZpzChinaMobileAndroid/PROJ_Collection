package com.zhongji.collection.project;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.base.BaseSecondActivity;

/**
 * 项目详情－选择
 * @author admin
 *
 */
public class ProjectDetialChooseActivity extends BaseSecondActivity implements OnClickListener{
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_projectdetialchoose);
	}
	
	@Override
	protected void init() {
		// TODO Auto-generated method stub
		
		setTitle("项目详情");
		setLeftBtn();
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
	}
	
	
}
