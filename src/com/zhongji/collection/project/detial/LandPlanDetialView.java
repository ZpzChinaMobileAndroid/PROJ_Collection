package com.zhongji.collection.project.detial;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.entity.Project;

/**
 * 土地信息－土地规划
 * @author admin
 *
 */
public class LandPlanDetialView {
	
	@SuppressWarnings("unused")
	private Context context;
	private int resId = R.drawable.ic_landinfo_small;
	private String stageName = "土地规划/拍卖";
	private String imgsType = "plan";
	private String contactType = "auctionUnitContacts";
	private int contactscount = 3;
	public View view_landplan;
	public DetialTitleView view_detial_title;
	public DetialImgsView view_detial_imgs;
	public TextView tv_landarea;
	public TextView tv_plotRatio;
	public TextView tv_usage;
	public LinearLayout layout_contacts;
	

	public LandPlanDetialView(Context context, LinearLayout parent) {
		this.context = context;
		view_landplan = View.inflate(context, R.layout.view_landplan_detial, null);
		view_detial_title = new DetialTitleView(context, view_landplan, resId, stageName);
		view_detial_imgs = new DetialImgsView(context, view_landplan);
		tv_landarea = (TextView) view_landplan.findViewById(R.id.tv_landarea);
		tv_plotRatio = (TextView) view_landplan.findViewById(R.id.tv_plotRatio);
		tv_usage = (TextView) view_landplan.findViewById(R.id.tv_usage);
		layout_contacts = (LinearLayout) view_landplan.findViewById(R.id.layout_contacts);
		layout_contacts.removeAllViews();
		for(int i=0;i<contactscount;i++){
			DetialContactView view_contact = new DetialContactView(context, layout_contacts);
			layout_contacts.addView(view_contact.getView());
		}
		
	}
	
	/**
	 * 获取布局
	 * @return
	 */
	public View getView(){
		return view_landplan;
	}

	/**
	 * 更新页面
	 * @param project
	 */
	public void updateUI(Project project) {
		// TODO Auto-generated method stub
		view_detial_title.updateUI(project);
		view_detial_imgs.updateUI(project, imgsType);
		tv_landarea.setText(project.getArea()+"㎡");
		tv_plotRatio.setText(project.getPlotRatio()+"%");
		tv_usage.setText(project.getUsage());
		int count = layout_contacts.getChildCount();
		for(int i=0;i<count;i++){
			View view = layout_contacts.getChildAt(i);
			DetialContactView view_contact = (DetialContactView) view.getTag();
			view_contact.updateUI(project,i,contactType);
		}
		
	}

  }
