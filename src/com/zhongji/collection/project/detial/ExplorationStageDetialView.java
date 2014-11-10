package com.zhongji.collection.project.detial;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.entity.Project;

/**
 * 主体设计阶段-地勘阶段
 * @author admin
 *
 */
public class ExplorationStageDetialView {
	
	@SuppressWarnings("unused")
	private Context context;
	private int resId = R.drawable.ic_miandesign_small;
	private String stageName = "地勘阶段";
	private String imgsType = "exploration";
	private String contactType = "explorationUnitContacts";
	private int contactscount = 3;
	public View view_explorationstage_detial;
	public DetialTitleView view_detial_title;
	public DetialImgsView view_detial_imgs;
	public LinearLayout layout_contacts;
	

	public ExplorationStageDetialView(Context context, LinearLayout parent) {
		this.context = context;
		view_explorationstage_detial = View.inflate(context, R.layout.view_explorationstage_detial, null);
		view_detial_title = new DetialTitleView(context, view_explorationstage_detial, resId, stageName, true);
		view_detial_imgs = new DetialImgsView(context, view_explorationstage_detial);
		layout_contacts = (LinearLayout) view_explorationstage_detial.findViewById(R.id.layout_contacts);
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
		return view_explorationstage_detial;
	}

	/**
	 * 更新页面
	 * @param project
	 */
	public void updateUI(Project project) {
		// TODO Auto-generated method stub
		view_detial_title.updateUI(project);
		view_detial_imgs.updateUI(project, imgsType);
		int count = layout_contacts.getChildCount();
		for(int i=0;i<count;i++){
			View view = layout_contacts.getChildAt(i);
			DetialContactView view_contact = (DetialContactView) view.getTag();
			view_contact.updateUI(project,i,contactType);
		}
		
	}

  }
