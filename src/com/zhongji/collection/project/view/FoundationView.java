package com.zhongji.collection.project.view;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.android.phone.R.id;

/**
 * 桩基基坑
 * @author Admin
 *
 */


public class FoundationView {
	public View view_foundation;
	public TextView tv_foundationcompany;//桩基分包单位
	
	public FoundationView(Context context){
		view_foundation=View.inflate(context,R.layout.view_foundation,null);
		tv_foundationcompany=(TextView) view_foundation.findViewById(id.tv_foundationcompany);
	
	 }
	
      public View getView(){
		return view_foundation;
	
        }
     }