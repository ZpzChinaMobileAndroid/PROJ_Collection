package com.zhongji.collection.project;

import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.android.phone.R.id;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

/**
 * 设计阶段
 * @author Admin
 *
 */



public class DesignView {

	public View view_design;
	public TextView tv_designcompany;//设计院
	public TextView tv_designphases;//主体设计阶段	
	
	public DesignView(Context context){
		view_design=View.inflate(context,R.layout.view_design,null);
		tv_designcompany=(TextView) view_design.findViewById(id.tv_designcompany);
		tv_designphases=(TextView) view_design.findViewById(id.tv_designphases);
	
	 }
	
      public View getView(){
		return view_design;
	
        }
     }
