package com.zhongji.collection.login;

import android.os.Bundle;

import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.base.BaseSecondActivity;

/**
 * 修改密码
 * @author admin
 *
 */
public class EditPasswordActivity extends BaseSecondActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_editpassword);
	}
	
	@Override
	protected void init() {
		// TODO Auto-generated method stub
		
		setTitle("修改密码");
		
	}

}
