package com.zhongji.collection.project.view;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.android.phone.R.id;
import com.zhongji.collection.entity.Project;
import com.zhongji.collection.util.ToastUtils;

/**
 * 桩基基坑
 * @author Admin
 *
 */


public class FoundationView extends BaseView implements OnClickListener {
	
	private String type="pileFoundationUnitContacts";
	public View view_foundation;
	public GridPhotoView mGridView;
	public TextView tv_foundationcompany;//桩基分包单位
	
	public FoundationView(Context context,Project project){
		
		this.context=context;
		this.project = project;
		this.contactsLists = project.getBaseContacts();
		this.imagesLists = project.getProjectImgs();
		view_foundation=View.inflate(context,R.layout.view_foundation,null);
		mGridView = new GridPhotoView(context, view_foundation);
		tv_foundationcompany=(TextView) view_foundation.findViewById(id.tv_foundationcompany);
		layout_contacts = (LinearLayout) view_foundation.findViewById(R.id.layout_contacts);
	
		tv_foundationcompany.setOnClickListener(this);
		
		update();
	 }

	public void update() {
		this.imgsType = "pileFoundation";
		updateImg(mGridView);
		updateContacts(type);
	}
	
      public View getView(){
		return view_foundation;
	
        }

	@Override
	public void onClick(View arg0) {
		// TODO 自动生成的方法存根
		if (arg0.getId() == R.id.tv_foundationcompany) {
			// 桩基分包单位

			if(getListCount(type)<3){
				showUnitDialog(-1, null, type,R.array.generalcontractor);
			}else{
				ToastUtils.getStance(context).showShortToast("名额已经满了！");
			}
		}

	}
}