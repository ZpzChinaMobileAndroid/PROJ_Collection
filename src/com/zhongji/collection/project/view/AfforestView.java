package com.zhongji.collection.project.view;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.android.phone.R.id;

/**
 * 消防景观/绿化
 * @author Admin
 *
 */


public class AfforestView implements OnClickListener{

	@SuppressWarnings("unused")
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
//			final String[] items = getResources().getStringArray(R.array.firecontrol);
//			DialogUtils.showChoiceDialog(context,items,new AlertDialog.OnClickListener() {
//
//				@Override
//				public void onClick(DialogInterface arg0, int arg1) {
//					// TODO 自动生成的方法存根
//
//			//				tv_seach_projectcategory_show.setText(items[arg1]);
//			//				arg0.dismiss();
//				}
//			});;
		
		
		}else if(arg0.getId()==R.id.tv_afforestlandscape){
		//景观绿化	
		//	DialogUtils.showChoiceDialog(context,R.array.firecontrol);
			
  		   }
	    }
     }
