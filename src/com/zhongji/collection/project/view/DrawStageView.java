package com.zhongji.collection.project.view;

import org.apache.commons.codec.digest.DigestUtils;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.TextView;

import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.util.DataTimeAlertDialog;
import com.zhongji.collection.util.DataTimeAlertDialog.Click;
import com.zhongji.collection.util.DialogUtils;

/**
 * 主体设计阶段－出图阶段
 * @author admin
 *
 */
public class DrawStageView implements OnClickListener{
	
	private Context context;
	public View view_drawstage;
	public TextView tv_ownerunit;	    //业主单位
	public TextView tv_starttime;	    //开工时间
	public TextView tv_endtime;		    //完工时间
	public CheckBox cb_lift;		    //电梯
	public CheckBox cb_airconditioner;	//空调
	public CheckBox cb_heatingway;		//供暖方式
	public CheckBox cb_wallmaterial;	//外墙材料
	public CheckBox cb_steelstructure;	//钢结构
	
	public DrawStageView(Context context) {
		this.context=context;
		view_drawstage = View.inflate(context, R.layout.view_drawstage, null);
		tv_ownerunit = (TextView) view_drawstage.findViewById(R.id.tv_ownerunit); 
		tv_starttime = (TextView) view_drawstage.findViewById(R.id.tv_starttime);
		tv_endtime = (TextView) view_drawstage.findViewById(R.id.tv_endtime);
		cb_lift = (CheckBox) view_drawstage.findViewById(R.id.cb_lift);
		cb_airconditioner = (CheckBox) view_drawstage.findViewById(R.id.cb_airconditioner);
		cb_heatingway = (CheckBox) view_drawstage.findViewById(R.id.cb_heatingway);
		cb_wallmaterial = (CheckBox) view_drawstage.findViewById(R.id.cb_wallmaterial);
		cb_steelstructure = (CheckBox) view_drawstage.findViewById(R.id.cb_steelstructure);
		
		tv_endtime.setOnClickListener(this);
		tv_ownerunit.setOnClickListener(this);
		tv_starttime.setOnClickListener(this);
		
	}
	
	public View getView(){
		return view_drawstage;
	}

	@Override
	public void onClick(View arg0) {
		// TODO 自动生成的方法存根
		if(arg0.getId()==R.id.tv_ownerunit){
		//业主单位	
			DialogUtils.showContactsDialog(context,R.array.ownercompanypost);
			
		}else if(arg0.getId()==R.id.tv_starttime){
		//预计开始时间	
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
			
		}else if(arg0.getId()==R.id.tv_endtime){
		//预计竣工时间	
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
		}
	}
}
