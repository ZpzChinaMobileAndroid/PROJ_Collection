package com.zhongji.collection.project.view;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.android.phone.R.id;

/**
 * 地平
 * @author Admin
 *
 */




public class HorizonView {
	public View view_horizon;
	public TextView tv_horizonStarttime;//实际开工时间
	public TextView tv_horizoncompany;//施工总承包
	
	public HorizonView(Context context){
		view_horizon=View.inflate(context,R.layout.view_horizon,null);
		tv_horizonStarttime=(TextView) view_horizon.findViewById(id.tv_horizonStarttime);
		tv_horizoncompany=(TextView) view_horizon.findViewById(id.tv_horizoncompany);
	
	 }
	
      public View getView(){
		return view_horizon;
	
        }
     }