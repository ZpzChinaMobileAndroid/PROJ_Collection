package com.zhongji.collection.project.view;

import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.android.phone.R.id;
import com.zhongji.collection.util.DialogUtils;

import android.R.raw;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

/**
 * 消防景观/绿化
 * @author Admin
 *
 */


public class AfforestView implements OnClickListener{

	private Context context;
	public View view_afforestView;
	public TextView tv_afforestcontrol;//消防
	public TextView tv_afforestlandscape;//景观绿化
	
	public AfforestView(Context context){
		this.context=context;
		view_afforestView=View.inflate(context,R.layout.view_afforest,null);
		tv_afforestcontrol=(TextView) view_afforestView.findViewById(id.tv_afforestcontrol);
		tv_afforestlandscape=(TextView) view_afforestView.findViewById(id.tv_afforestlandscape);
		
		tv_afforestcontrol.setOnClickListener(this);
		tv_afforestlandscape.setOnClickListener(this);
	
	 }
	
      public View getView(){
		return view_afforestView;
	
        }

	@Override
	public void onClick(View arg0) {
		// TODO 自动生成的方法存根
		if(arg0.getId()==R.id.tv_afforestcontrol){
		//消防	
			DialogUtils.showChoiceDialog(context,R.array.firecontrol);
			
		}else if(arg0.getId()==R.id.tv_afforestlandscape){
		//景观绿化	
			DialogUtils.showChoiceDialog(context,R.array.firecontrol);
			
  		   }
	    }
     }
