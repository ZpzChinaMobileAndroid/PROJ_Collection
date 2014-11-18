package com.zhongji.collection.project.view;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.android.phone.R.id;
import com.zhongji.collection.entity.Project;
import com.zhongji.collection.util.DataTimeAlertDialog;
import com.zhongji.collection.util.ToastUtils;
import com.zhongji.collection.util.DataTimeAlertDialog.Click;

/**
 * 地平
 * @author Admin
 *
 */




public class HorizonView extends BaseView implements OnClickListener {
	
	private String type="contractorUnitContacts";
	public View view_horizon;
	public GridPhotoView mGridView;
	public TextView tv_horizonStarttime;//实际开工时间
	public TextView tv_horizoncompany;//施工总承包
	
	public HorizonView(Context context,Project project){
		
		this.context=context;
		this.project = project;
		this.contactsLists = project.getBaseContacts();
		this.imagesLists = project.getProjectImgs();
		view_horizon=View.inflate(context,R.layout.view_horizon,null);
		mGridView = new GridPhotoView(context, view_horizon);
		tv_horizonStarttime=(TextView) view_horizon.findViewById(id.tv_horizonStarttime);
		tv_horizoncompany=(TextView) view_horizon.findViewById(id.tv_horizoncompany);
		layout_contacts = (LinearLayout) view_horizon.findViewById(R.id.layout_contacts);
		
		tv_horizoncompany.setOnClickListener(this);
		tv_horizonStarttime.setOnClickListener(this);
	
		update(true);
	 }

	public void update(boolean bool) {
		this.imgsType = "horizon";
		if(bool){
			updateImg(mGridView);
		}
		updateTime();
		updateContacts(type);
	}
	
     private void updateTime() {
		// TODO Auto-generated method stub
    	 tv_horizonStarttime.setText("实际施工时间: " + project.getActualStartTime());
	}

	public View getView(){
		return view_horizon;
	
     }

	@Override
	public void onClick(View arg0) {
		// TODO 自动生成的方法存根
		if (arg0.getId() == R.id.tv_horizonStarttime) {
			// 实际开工时间
			DataTimeAlertDialog dialog = new DataTimeAlertDialog(context);
			dialog.setClickListener(new Click() {

				@Override
				public void sure(String date, String time) {
					// TODO Auto-generated method stub
					tv_horizonStarttime.setText("实际施工时间: " + date);
					project.setActualStartTime(tv_horizonStarttime.getText().toString().replace("实际施工时间: ", ""));
				}

				@Override
				public void cancel() {
					// TODO Auto-generated method stub
				}
			});
			dialog.show();
		} else if (arg0.getId() == R.id.tv_horizoncompany) {
			// 施工总承包
			if (getListCount(type)<3) {
				showUnitDialog(-1, null, type, R.array.generalcontractor);
			} else {
				ToastUtils.getStance(context).showShortToast("名额已经满了！");
			}

		}
	}
}