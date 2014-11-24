package com.zhongji.collection.project.detial;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.entity.Project;

/**
 * 主体施工-桩基基坑
 * @author admin
 *
 */
public class FoundationDetialView {
	
	@SuppressWarnings("unused")
	private Context context;
	private int resId = R.drawable.ic_mainconstruction_small;
	private String stageName = "桩基基坑";
	private String imgsType = "pileFoundation";
	private String contactType = "pileFoundationUnitContacts";
	private int contactscount = 3;
	public View view_foundation_detial;
	public DetialTitleView view_detial_title;
	public DetialImgsView view_detial_imgs;
	public LinearLayout layout_contacts;
	

	public FoundationDetialView(Context context, LinearLayout parent) {
		this.context = context;
		view_foundation_detial = View.inflate(context, R.layout.view_foundation_detial, null);
		view_detial_title = new DetialTitleView(context, view_foundation_detial, resId, stageName, true);
		view_detial_imgs = new DetialImgsView(context, view_foundation_detial);
		layout_contacts = (LinearLayout) view_foundation_detial.findViewById(R.id.layout_contacts);
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
		return view_foundation_detial;
	}

	/**
	 * 更新页面
	 * @param project
	 */
	public void updateUI(Project project) {
		// TODO Auto-generated method stub
		System.out.println("------------------------------------");
		view_detial_title.updateUI_Foundation(project);
		view_detial_imgs.updateUI(project, imgsType);
		int count = layout_contacts.getChildCount();
		for(int i=0;i<count;i++){
			View view = layout_contacts.getChildAt(i);
			DetialContactView view_contact = (DetialContactView) view.getTag();
			view_contact.updateUI(project,i,contactType);
		}
		
	}

  }
