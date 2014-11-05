package com.zhongji.collection.project;

import android.content.Context;
import android.view.View;

import com.zhongji.collection.android.phone.R;

/**
 * 主体施工
 * @author Admin
 *
 */


public class ConstuctView {

	public View view_constuct;
	
	
	
	public ConstuctView(Context context) {
		view_constuct = View.inflate(context, R.layout.view_constuct, null);
	}
	
	public View getView(){
		return view_constuct;
	}
}
