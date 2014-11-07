package com.zhongji.collection.project.view;

import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.android.phone.R.id;
import com.zhongji.collection.util.DialogUtils;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

/**
 * 设计阶段
 * @author Admin
 *
 */



public class DesignView implements OnClickListener {
	
	private Context context;
	public View view_design;
	public TextView tv_designcompany;//设计院
	public TextView tv_designphases;//主体设计阶段	
	
	public DesignView(Context context){
		this.context=context;
		view_design=View.inflate(context,R.layout.view_design,null);
		tv_designcompany=(TextView) view_design.findViewById(id.tv_designcompany);
		tv_designphases=(TextView) view_design.findViewById(id.tv_designphases);
		
		tv_designcompany.setOnClickListener(this);
		tv_designphases.setOnClickListener(this);
	
	 }
	
      public View getView(){
		return view_design;
	
        }

	@Override
	public void onClick(View arg0) {
		// TODO 自动生成的方法存根
		
		if(arg0.getId()==R.id.tv_designcompany){
		//设计院
			DialogUtils.showContactsDialog(context, R.array.DesignInstitutepost);
			
		}else if(arg0.getId()==R.id.tv_designphases){
		//主体设计阶段	
		//	DialogUtils.showChoiceDialog(context,R.array.Themaindesignphase);
			
  		    }
	     }
     }
