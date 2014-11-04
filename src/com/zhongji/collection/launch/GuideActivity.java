package com.zhongji.collection.launch;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.base.BaseActivity;

/**
 * 引导
 * @author Administrator
 *
 */
public class GuideActivity extends BaseActivity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.launch_activity_guide);
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if(arg0.getId() == R.id.btn_skip){
			//跳过
			startActivity(new Intent(GuideActivity.this, EmptyActivity.class));
			finish();
		}
	}

}
