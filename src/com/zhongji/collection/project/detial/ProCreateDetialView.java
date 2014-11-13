package com.zhongji.collection.project.detial;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.entity.Project;

/**
 * 土地信息－项目立项
 * @author admin
 *
 */
public class ProCreateDetialView {
	
	@SuppressWarnings("unused")
	private Context context;
	private int resId = R.drawable.ic_landinfo_small;
	private String stageName = "项目立项";
	private String contactType = "ownerUnitContacts";
	private int contactscount = 3;
	public View view;
	public DetialTitleView view_detial_title;
	public TextView tv_expectedStartTime;
	public TextView tv_storeyHeight;
	public TextView tv_foreignInvestment;
	public TextView tv_expectedFinishTime;
	public TextView tv_investment;
	public TextView tv_areaOfStructure;
	public LinearLayout layout_contacts;
	public TextView tv_ownerType;
	

	public ProCreateDetialView(Context context, LinearLayout parent) {
		this.context = context;
		view = View.inflate(context, R.layout.view_procreate_detial, null);
		view_detial_title = new DetialTitleView(context, view, resId, stageName);
		tv_expectedStartTime = (TextView) view.findViewById(R.id.tv_expectedStartTime);
		tv_storeyHeight = (TextView) view.findViewById(R.id.tv_storeyHeight);
		tv_foreignInvestment = (TextView) view.findViewById(R.id.tv_foreignInvestment);
		tv_expectedFinishTime = (TextView) view.findViewById(R.id.tv_expectedFinishTime);
		tv_investment = (TextView) view.findViewById(R.id.tv_investment);
		tv_areaOfStructure = (TextView) view.findViewById(R.id.tv_areaOfStructure);
		layout_contacts = (LinearLayout) view.findViewById(R.id.layout_contacts);
		layout_contacts.removeAllViews();
		for(int i=0;i<contactscount;i++){
			DetialContactView view_contact = new DetialContactView(context, layout_contacts);
			layout_contacts.addView(view_contact.getView());
		}
		tv_ownerType = (TextView) view.findViewById(R.id.tv_ownerType);
		
	}
	
	/**
	 * 获取布局
	 * @return
	 */
	public View getView(){
		return view;
	}

	/**
	 * 更新页面
	 * @param project
	 */
	public void updateUI(Project project) {
		// TODO Auto-generated method stub
		view_detial_title.updateUI_ProCreate(project);
		tv_expectedStartTime.setText(project.getExpectedStartTime());
		tv_storeyHeight.setText(project.getStoreyHeight()+"M");
		tv_foreignInvestment.setText(project.getForeignInvestment());
		tv_expectedFinishTime.setText(project.getExpectedFinishTime());
		tv_investment.setText(project.getInvestment());
		tv_areaOfStructure.setText(project.getAreaOfStructure()+"㎡");
		int count = layout_contacts.getChildCount();
		for(int i=0;i<count;i++){
			View view = layout_contacts.getChildAt(i);
			DetialContactView view_contact = (DetialContactView) view.getTag();
			view_contact.updateUI(project,i,contactType);
		}
		tv_ownerType.setText(project.getOwnerType().replace(",", "\t\t"));
		
	}

  }
