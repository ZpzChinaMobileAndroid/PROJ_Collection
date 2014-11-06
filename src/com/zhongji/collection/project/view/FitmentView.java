package com.zhongji.collection.project.view;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.android.phone.R.id;
import com.zhongji.collection.util.DialogUtils;

/**
 * 装修阶段
 * @author Admin
 *
 */



public class FitmentView implements OnClickListener{
	
	private Context context;
	public View view_fitment;
	public TextView tv_fitmemtweak;//弱电安装
	public TextView tv_fitmentcondition;//装修情况	
	public TextView tv_fitmentstage;//装修阶段	
	
	public FitmentView(Context context){
		this.context=context;
		view_fitment=View.inflate(context,R.layout.view_fitment,null);
		tv_fitmemtweak=(TextView) view_fitment.findViewById(id.tv_fitmemtweak);
		tv_fitmentcondition=(TextView) view_fitment.findViewById(id.tv_fitmentcondition);
		tv_fitmentstage=(TextView) view_fitment.findViewById(id.tv_fitmentstage);
	
		tv_fitmemtweak.setOnClickListener(this);
		tv_fitmentcondition.setOnClickListener(this);
		tv_fitmentstage.setOnClickListener(this);
		
	 }
	
      public View getView(){
		return view_fitment;
	
        }

	@Override
	public void onClick(View arg0) {
		// TODO 自动生成的方法存根
		if(arg0.getId()==R.id.tv_fitmemtweak){
		//弱电安装	
		DialogUtils.showChoiceDialog(context,R.array.firecontrol);	
			
		}else if(arg0.getId()==R.id.tv_fitmentcondition){
		//装修情况	
			DialogUtils.showChoiceDialog(context,R.array.fitmentcondition);	
			
		}else if(arg0.getId()==R.id.tv_fitmentstage){
		//装修阶段	
			DialogUtils.showChoiceDialog(context,R.array.firecontrol);	
			
		   }
	    }
     }
