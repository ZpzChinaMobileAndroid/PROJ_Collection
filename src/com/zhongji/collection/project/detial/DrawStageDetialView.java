package com.zhongji.collection.project.detial;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.entity.Project;

/**
 * 设计阶段－出图阶段
 * @author admin
 *
 */
public class DrawStageDetialView {
	
	private Context context;
	private int resId = R.drawable.ic_miandesign_small;
	private String stageName = "出图阶段";
	private String contactType = "ownerUnitContacts";
	private int contactscount = 3;
	public View view_drawstage_detial;
	public DetialTitleView view_detial_title;
	public LinearLayout layout_contacts;
	public TextView tv_propertyElevator;
	public TextView tv_propertyAirCondition;
	public TextView tv_propertyHeating;
	public TextView tv_propertyExternalWallMeterial;
	public TextView tv_propertyStealStructure;
	

	public DrawStageDetialView(Context context, LinearLayout parent) {
		this.context = context;
		view_drawstage_detial = View.inflate(context, R.layout.view_drawstage_detial, null);
		view_detial_title = new DetialTitleView(context, view_drawstage_detial, resId, stageName, "time");
		layout_contacts = (LinearLayout) view_drawstage_detial.findViewById(R.id.layout_contacts);
		layout_contacts.removeAllViews();
		for(int i=0;i<contactscount;i++){
			DetialContactView view_contact = new DetialContactView(context, layout_contacts);
			layout_contacts.addView(view_contact.getView());
		}
		tv_propertyElevator = (TextView) view_drawstage_detial.findViewById(R.id.tv_propertyElevator);
		tv_propertyAirCondition = (TextView) view_drawstage_detial.findViewById(R.id.tv_propertyAirCondition);
		tv_propertyHeating = (TextView) view_drawstage_detial.findViewById(R.id.tv_propertyHeating);
		tv_propertyExternalWallMeterial = (TextView) view_drawstage_detial.findViewById(R.id.tv_propertyExternalWallMeterial);
		tv_propertyStealStructure = (TextView) view_drawstage_detial.findViewById(R.id.tv_propertyStealStructure);
	}
	
	/**
	 * 获取布局
	 * @return
	 */
	public View getView(){
		return view_drawstage_detial;
	}

	/**
	 * 更新页面
	 * @param project
	 */
	public void updateUI(Project project) {
		// TODO Auto-generated method stub
		view_detial_title.updateUI_DrawStage(project);
		int count = layout_contacts.getChildCount();
		for(int i=0;i<count;i++){
			View view = layout_contacts.getChildAt(i);
			DetialContactView view_contact = (DetialContactView) view.getTag();
			view_contact.updateUI(project,i,contactType);
		}
		tv_propertyElevator.setText(project.getPropertyElevator());
		tv_propertyElevator.setTextColor("YES".equals(project.getPropertyElevator())?context.getResources().getColor(R.color.red_detial_txt):context.getResources().getColor(R.color.gray_detial_txt));
		tv_propertyAirCondition.setText(project.getPropertyAirCondition());
		tv_propertyAirCondition.setTextColor("YES".equals(project.getPropertyAirCondition())?context.getResources().getColor(R.color.red_detial_txt):context.getResources().getColor(R.color.gray_detial_txt));
		tv_propertyHeating.setText(project.getPropertyHeating());
		tv_propertyHeating.setTextColor("YES".equals(project.getPropertyHeating())?context.getResources().getColor(R.color.red_detial_txt):context.getResources().getColor(R.color.gray_detial_txt));
		tv_propertyExternalWallMeterial.setText(project.getPropertyExternalWallMeterial());
		tv_propertyExternalWallMeterial.setTextColor("YES".equals(project.getPropertyExternalWallMeterial())?context.getResources().getColor(R.color.red_detial_txt):context.getResources().getColor(R.color.gray_detial_txt));
		tv_propertyStealStructure.setText(project.getPropertyStealStructure());
		tv_propertyStealStructure.setTextColor("YES".equals(project.getPropertyStealStructure())?context.getResources().getColor(R.color.red_detial_txt):context.getResources().getColor(R.color.gray_detial_txt));
	}

  }
