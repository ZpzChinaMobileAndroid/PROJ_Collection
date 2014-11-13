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
 * 地勘阶段
 * 
 * @author Admin
 * 
 */

public class ExplorationStageView extends BaseView implements OnClickListener {

	public View view_explorationstage;
	public GridPhotoView mGridView;
	public TextView tv_explorationcompany;// 地勘公司
	private String type="explorationUnitContacts";

	public ExplorationStageView(Context context, Project project) {
		this.context = context;
		this.project = project;
		this.contactsLists = project.getBaseContacts();
		this.imagesLists = project.getProjectImgs();
		view_explorationstage = View.inflate(context,
				R.layout.view_explorationstage, null);
		mGridView = new GridPhotoView(context, view_explorationstage);
		tv_explorationcompany = (TextView) view_explorationstage
				.findViewById(id.tv_explorationcompany);
		layout_contacts = (LinearLayout) view_explorationstage
				.findViewById(R.id.layout_contacts);

		tv_explorationcompany.setOnClickListener(this);

		update();
	}

	public void update() {
		this.imgsType = "exploration";
		updateImg(mGridView);
		updateContacts(type);
	}

	public View getView() {
		return view_explorationstage;

	}

	@Override
	public void onClick(View arg0) {
		// TODO 自动生成的方法存根
		
		if (arg0.getId() == R.id.tv_explorationcompany) {
			// 地勘公司
			if (getListCount(type)<3) {
				showUnitDialog(-1, null, type, R.array.geologicalexplorationunitspost);
			} else {
				ToastUtils.getStance(context).showShortToast("名额已经满了！");
			}
		}
	}
}
