package com.zhongji.collection.project.view;

import android.content.Context;
import android.view.View;

import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.entity.Project;

/**
 * 主体施工
 * @author Admin
 *
 */


public class ConstuctView extends BaseView{

	public View view_constuct;
	public GridPhotoView mGridView;
	
	
	public ConstuctView(Context context, Project project) {
		
		this.context=context;
		this.project = project;
		this.contactsLists = project.getBaseContacts();
		this.imagesLists = project.getProjectImgs();
		view_constuct = View.inflate(context, R.layout.view_constuct, null);
		mGridView = new GridPhotoView(context, view_constuct);
		
		update();
	}

	public void update() {
		this.imgsType = "mainPart";
		updateImg(mGridView);
	}
	
	public View getView(){
		return view_constuct;
	}
}
