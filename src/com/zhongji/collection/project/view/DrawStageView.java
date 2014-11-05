package com.zhongji.collection.project.view;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.zhongji.collection.android.phone.R;

/**
 * 主体设计阶段－出图阶段
 * @author admin
 *
 */
public class DrawStageView {
	
	public View view_drawstage;
	public TextView tv_ownerunit;	//业主单位
	public TextView tv_starttime;	//开工时间
	public TextView tv_endtime;		//完工时间
	public CheckBox cb_lift;		//电梯
	public CheckBox cb_airconditioner;	//空调
	public CheckBox cb_heatingway;		//供暖方式
	public CheckBox cb_wallmaterial;	//外墙材料
	public CheckBox cb_steelstructure;	//钢结构
	
	public DrawStageView(Context context) {
		view_drawstage = View.inflate(context, R.layout.view_drawstage, null);
		tv_ownerunit = (TextView) view_drawstage.findViewById(R.id.tv_ownerunit); 
		tv_starttime = (TextView) view_drawstage.findViewById(R.id.tv_starttime);
		tv_endtime = (TextView) view_drawstage.findViewById(R.id.tv_endtime);
		cb_lift = (CheckBox) view_drawstage.findViewById(R.id.cb_lift);
		cb_airconditioner = (CheckBox) view_drawstage.findViewById(R.id.cb_airconditioner);
		cb_heatingway = (CheckBox) view_drawstage.findViewById(R.id.cb_heatingway);
		cb_wallmaterial = (CheckBox) view_drawstage.findViewById(R.id.cb_wallmaterial);
		cb_steelstructure = (CheckBox) view_drawstage.findViewById(R.id.cb_steelstructure);
		
	}
	
	public View getView(){
		return view_drawstage;
	}
}
