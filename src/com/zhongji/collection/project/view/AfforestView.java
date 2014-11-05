package com.zhongji.collection.project.view;

import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.android.phone.R.id;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

/**
 * 消防景观/绿化
 * @author Admin
 *
 */


public class AfforestView {

	public View view_afforestView;
	public TextView tv_afforestcontrol;//消防
	public TextView tv_afforestlandscape;//景观绿化
	
	public AfforestView(Context context){
		view_afforestView=View.inflate(context,R.layout.view_afforest,null);
		tv_afforestcontrol=(TextView) view_afforestView.findViewById(id.tv_afforestcontrol);
		tv_afforestlandscape=(TextView) view_afforestView.findViewById(id.tv_afforestlandscape);
	
	 }
	
      public View getView(){
		return view_afforestView;
	
        }
     }
