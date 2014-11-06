package com.zhongji.collection.project.view;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.android.phone.R.id;
import com.zhongji.collection.util.DataTimeAlertDialog;
import com.zhongji.collection.util.DialogUtils;
import com.zhongji.collection.util.DataTimeAlertDialog.Click;

/**
 * 地平
 * @author Admin
 *
 */




public class HorizonView implements OnClickListener {
	
	private Context context;
	public View view_horizon;
	public TextView tv_horizonStarttime;//实际开工时间
	public TextView tv_horizoncompany;//施工总承包
	
	public HorizonView(Context context){
		this.context=context;
		view_horizon=View.inflate(context,R.layout.view_horizon,null);
		tv_horizonStarttime=(TextView) view_horizon.findViewById(id.tv_horizonStarttime);
		tv_horizoncompany=(TextView) view_horizon.findViewById(id.tv_horizoncompany);
		
		tv_horizoncompany.setOnClickListener(this);
		tv_horizonStarttime.setOnClickListener(this);
	
	 }
	
      public View getView(){
		return view_horizon;
	
        }

	@Override
	public void onClick(View arg0) {
		// TODO 自动生成的方法存根
		if(arg0.getId()==R.id. tv_horizoncompany){
		//实际开工时间	
			DataTimeAlertDialog dialog=new DataTimeAlertDialog(context);
			dialog.setClickListener(new Click() {

				@Override
				public void sure(String date, String time) {
					// TODO Auto-generated method stub
				}
				@Override
				public void cancel() {
					// TODO Auto-generated method stub
				}
			});
			dialog.show();	
		}else if(arg0.getId()==R.id. tv_horizonStarttime){
		//施工总承包	
		DialogUtils.showContactsDialog(context,R.array.generalcontractor);	
			
		   }
	    }
     }