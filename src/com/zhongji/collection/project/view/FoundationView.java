package com.zhongji.collection.project.view;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.android.phone.R.id;
import com.zhongji.collection.util.DialogUtils;

/**
 * 桩基基坑
 * @author Admin
 *
 */


public class FoundationView implements OnClickListener {
	
	private Context context;
	public View view_foundation;
	public TextView tv_foundationcompany;//桩基分包单位
	
	public FoundationView(Context context){
		this.context=context;
		view_foundation=View.inflate(context,R.layout.view_foundation,null);
		tv_foundationcompany=(TextView) view_foundation.findViewById(id.tv_foundationcompany);
	
		tv_foundationcompany.setOnClickListener(this);
		
	 }
	
      public View getView(){
		return view_foundation;
	
        }

	@Override
	public void onClick(View arg0) {
		// TODO 自动生成的方法存根
	if(arg0.getId()==R.id.tv_foundationcompany){
		//桩基分包单位
		DialogUtils.showContactsDialog(context,R.array.generalcontractor);
		
	}	
	
		
		
		
	   }
     }