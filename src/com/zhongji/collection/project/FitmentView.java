package com.zhongji.collection.project;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.android.phone.R.id;

/**
 * 装修阶段
 * @author Admin
 *
 */



public class FitmentView {
	public View view_fitment;
	public TextView tv_fitmemtweak;//弱电安装
	public TextView tv_fitmentcondition;//装修情况	
	public TextView tv_fitmentstage;//装修阶段	
	
	public FitmentView(Context context){
		view_fitment=View.inflate(context,R.layout.view_fitment,null);
		tv_fitmemtweak=(TextView) view_fitment.findViewById(id.tv_fitmemtweak);
		tv_fitmentcondition=(TextView) view_fitment.findViewById(id.tv_fitmentcondition);
		tv_fitmentstage=(TextView) view_fitment.findViewById(id.tv_fitmentstage);
	
	 }
	
      public View getView(){
		return view_fitment;
	
        }
     }
