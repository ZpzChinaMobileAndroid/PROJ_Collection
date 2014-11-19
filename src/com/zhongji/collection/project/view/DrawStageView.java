package com.zhongji.collection.project.view;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.entity.Project;
import com.zhongji.collection.util.DataTimeAlertDialog;
import com.zhongji.collection.util.DataTimeAlertDialog.Click;
import com.zhongji.collection.util.ToastUtils;

/**
 * 主体设计阶段－出图阶段
 * @author admin
 *
 */
public class DrawStageView extends BaseView implements OnClickListener,OnCheckedChangeListener{
	
	public View view_drawstage;
	public TextView tv_ownerunit;	    //业主单位
	public TextView tv_starttime;	    //开工时间
	public TextView tv_endtime;		    //完工时间
	public CheckBox cb_lift;		    //电梯
	public CheckBox cb_airconditioner;	//空调
	public CheckBox cb_heatingway;		//供暖方式
	public CheckBox cb_wallmaterial;	//外墙材料
	public CheckBox cb_steelstructure;	//钢结构
	private String type="ownerUnitContacts";
	
	public DrawStageView(Context context, Project project) {
		this.context=context;
		this.project = project;
		this.contactsLists = project.getBaseContacts();
		view_drawstage = View.inflate(context, R.layout.view_drawstage, null);
		tv_ownerunit = (TextView) view_drawstage.findViewById(R.id.tv_ownerunit); 
		tv_starttime = (TextView) view_drawstage.findViewById(R.id.tv_starttime);
		tv_endtime = (TextView) view_drawstage.findViewById(R.id.tv_endtime);
		cb_lift = (CheckBox) view_drawstage.findViewById(R.id.cb_lift);
		cb_airconditioner = (CheckBox) view_drawstage.findViewById(R.id.cb_airconditioner);
		cb_heatingway = (CheckBox) view_drawstage.findViewById(R.id.cb_heatingway);
		cb_wallmaterial = (CheckBox) view_drawstage.findViewById(R.id.cb_wallmaterial);
		cb_steelstructure = (CheckBox) view_drawstage.findViewById(R.id.cb_steelstructure);
		layout_contacts = (LinearLayout) view_drawstage.findViewById(R.id.layout_contacts);
		
		tv_endtime.setOnClickListener(this);
		tv_ownerunit.setOnClickListener(this);
		tv_starttime.setOnClickListener(this);
		cb_lift.setOnCheckedChangeListener(this);
		cb_airconditioner.setOnCheckedChangeListener(this);
		cb_heatingway.setOnCheckedChangeListener(this);
		cb_wallmaterial.setOnCheckedChangeListener(this);
		cb_steelstructure.setOnCheckedChangeListener(this);
		
		update();
	}

	public void update() {
		updateTime();
		updateContacts(type);
	}
	
	private void updateTime() {
		// TODO Auto-generated method stub
		tv_starttime.setText("预计开工时间: "+project.getExpectedStartTime());
		tv_endtime.setText("预计竣工时间: " + project.getExpectedFinishTime());
		cb_lift.setChecked("YES".equals(project.getPropertyElevator())?true:false);
		cb_airconditioner.setChecked("YES".equals(project.getPropertyAirCondition())?true:false);
		cb_heatingway.setChecked("YES".equals(project.getPropertyHeating())?true:false);
		cb_wallmaterial.setChecked("YES".equals(project.getPropertyExternalWallMeterial())?true:false);
		cb_steelstructure.setChecked("YES".equals(project.getPropertyStealStructure())?true:false);
	}

	public View getView(){
		return view_drawstage;
	}

	@Override
	public void onClick(View arg0) {
		// TODO 自动生成的方法存根
		if(arg0.getId()==R.id.tv_ownerunit){
		//业主单位	
		if(getListCount(type)<3){
			showUnitDialog(-1, null, type, R.array.ownercompanypost);
		}else{
			ToastUtils.getStance(context).showShortToast("名额已经满了！");
		}
			
		}else if(arg0.getId()==R.id.tv_starttime){
		//预计开始时间	
			DataTimeAlertDialog dialog=new DataTimeAlertDialog(context);
			dialog.setClickListener(new Click() {

				@Override
				public void sure(String date, String time) {
					// TODO Auto-generated method stub
//					String endtime = tv_endtime.getText().toString().replace("预计竣工时间: ", "");
//					long end = TimeUtils.strtolong(endtime);
//					long start = TimeUtils.strtolong(date);
					
					tv_starttime.setText("预计开工时间: " + date);
					project.setExpectedStartTime(tv_starttime.getText().toString().replace("预计开工时间: ", ""));
					
//					if(start > end){
//						tv_endtime.setText("预计竣工时间: " + date);
//						project.setExpectedFinishTime(tv_endtime.getText().toString().replace("预计竣工时间: ", ""));
//					}
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
//					String starttime = tv_starttime.getText().toString().replace("预计开工时间: ", "");
//					long start = TimeUtils.strtolong(starttime);
//					long end = TimeUtils.strtolong(date);
					
					tv_endtime.setText("预计竣工时间: " + date);
					project.setExpectedFinishTime(tv_endtime.getText().toString().replace("预计竣工时间: ", ""));
					
//					if(start > end){
//						tv_starttime.setText("预计开工时间: " + date);
//						project.setExpectedStartTime(tv_starttime.getText().toString().replace("预计开工时间: ", ""));
//					}
				}
				@Override
				public void cancel() {
					// TODO Auto-generated method stub
				}
			});
			dialog.show();
		}
	}

	@Override
	public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.cb_lift:
			//电梯
			project.setPropertyElevator(arg1+"");
			break;
		case R.id.cb_airconditioner:
			//空调
			project.setPropertyAirCondition(arg1+"");
			break;
		case R.id.cb_heatingway:
			//供暖方式
			project.setPropertyHeating(arg1+"");
			break;
		case R.id.cb_wallmaterial:
			//外墙材料
			project.setPropertyExternalWallMeterial(arg1+"");
			break;
		case R.id.cb_steelstructure:
			//钢结构
			project.setPropertyStealStructure(arg1+"");
			break;
		}
	}
}
