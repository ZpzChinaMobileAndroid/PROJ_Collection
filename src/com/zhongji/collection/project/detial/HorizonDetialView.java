package com.zhongji.collection.project.detial;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.entity.Project;

/**
 * 主体施工-地平阶段
 * @author admin
 *
 */
public class HorizonDetialView {
	
	@SuppressWarnings("unused")
	private Context context;
	private int resId = R.drawable.ic_mainconstruction_small;
	private String stageName = "地平阶段";
	private String imgsType = "horizon";
	private String contactType = "contractorUnitContacts";
	private int contactscount = 3;
	public View view_horizon_detial;
	public DetialTitleView view_detial_title;
	public DetialImgsView view_detial_imgs;
	public LinearLayout layout_contacts;
	

	public HorizonDetialView(Context context, LinearLayout parent) {
		this.context = context;
		view_horizon_detial = View.inflate(context, R.layout.view_horizon_detial, null);
		view_detial_title = new DetialTitleView(context, view_horizon_detial, resId, stageName);
		view_detial_imgs = new DetialImgsView(context, view_horizon_detial);
		layout_contacts = (LinearLayout) view_horizon_detial.findViewById(R.id.layout_contacts);
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
		return view_horizon_detial;
	}

	/**
	 * 更新页面
	 * @param project
	 */
	public void updateUI(Project project) {
		// TODO Auto-generated method stub
		view_detial_title.updateUI_Horizon(project);
		view_detial_imgs.updateUI(project, imgsType);
		int count = layout_contacts.getChildCount();
		for(int i=0;i<count;i++){
			View view = layout_contacts.getChildAt(i);
			DetialContactView view_contact = (DetialContactView) view.getTag();
			view_contact.updateUI(project,i,contactType);
		}
		
	}

  }
