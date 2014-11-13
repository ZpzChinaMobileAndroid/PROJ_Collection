package com.zhongji.collection.project.detial;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.entity.Project;

/**
 * 主体施工-主体施工
 * @author admin
 *
 */
public class ConstuctDetialView {
	
	@SuppressWarnings("unused")
	private Context context;
	private int resId = R.drawable.ic_mainconstruction_small;
	private String stageName = "主体施工";
	private String imgsType = "mainPart";
	public View view_constuct_detial;
	public DetialTitleView view_detial_title;
	public DetialImgsView view_detial_imgs;
	

	public ConstuctDetialView(Context context, LinearLayout parent) {
		this.context = context;
		view_constuct_detial = View.inflate(context, R.layout.view_constuct_detial, null);
		view_detial_title = new DetialTitleView(context, view_constuct_detial, resId, stageName, true);
		view_detial_imgs = new DetialImgsView(context, view_constuct_detial);
	}
	
	/**
	 * 获取布局
	 * @return
	 */
	public View getView(){
		return view_constuct_detial;
	}

	/**
	 * 更新页面
	 * @param project
	 */
	public void updateUI(Project project) {
		// TODO Auto-generated method stub
		view_detial_title.updateUI_Constuct(project);
		view_detial_imgs.updateUI(project, imgsType);
		
	}

  }
