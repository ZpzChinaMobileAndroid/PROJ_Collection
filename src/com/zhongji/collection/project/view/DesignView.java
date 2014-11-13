package com.zhongji.collection.project.view;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.android.phone.R.id;
import com.zhongji.collection.entity.Project;
import com.zhongji.collection.util.DialogUtils;
import com.zhongji.collection.util.ToastUtils;

/**
 * 设计阶段
 * 
 * @author Admin
 * 
 */

public class DesignView extends BaseView implements OnClickListener {

	public View view_design;
	public TextView tv_designcompany;// 设计院
	public TextView tv_designphases;// 主体设计阶段
	public TextView tv_designphases_value;
	private String type="designInstituteContacts";

	public DesignView(Context context, Project project) {
		this.context = context;
		this.project = project;
		this.contactsLists = project.getBaseContacts();
		view_design = View.inflate(context, R.layout.view_design, null);
		tv_designcompany = (TextView) view_design
				.findViewById(id.tv_designcompany);
		tv_designphases = (TextView) view_design
				.findViewById(id.tv_designphases);
		tv_designphases_value = (TextView) view_design
				.findViewById(id.tv_designphases_value);
		layout_contacts = (LinearLayout) view_design
				.findViewById(R.id.layout_contacts);

		tv_designcompany.setOnClickListener(this);
		tv_designphases.setOnClickListener(this);

		update();
	}

	public void update() {
		updateCheckBox();
		updateContacts(type);
	}

	private void updateCheckBox() {
		// TODO Auto-generated method stub
		tv_designphases_value.setText(project.getMainDesignStage());
	}

	public View getView() {
		return view_design;

	}

	@Override
	public void onClick(View arg0) {
		// TODO 自动生成的方法存根

		if (arg0.getId() == R.id.tv_designcompany) {
			// 设计院
			
			if(getListCount(type)<3){
				showUnitDialog(-1, null, type, R.array.DesignInstitutepost);
			}else{
				ToastUtils.getStance(context).showShortToast("名额已经满了！");
			}

		} else if (arg0.getId() == R.id.tv_designphases) {
			// 主体设计阶段
			final String[] items = context.getResources().getStringArray(R.array.Themaindesignphase);
			final boolean[] checkedItems = new boolean[] { false, false, false,false, false, false,false,false };
			DialogUtils.showMultiChoiceDialog(context, "主体设计阶段", items, checkedItems, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					// TODO Auto-generated method stub
					tv_designphases_value.setText(getCheckedText(items, checkedItems));
					project.setMainDesignStage(tv_designphases_value.getText().toString());
				}
			});
			

		}
	}
}
