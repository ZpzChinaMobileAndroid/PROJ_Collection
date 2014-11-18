package com.zhongji.collection.project.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.android.phone.R.id;
import com.zhongji.collection.entity.Project;
import com.zhongji.collection.util.DialogUtils;

/**
 * 消防景观/绿化
 * @author Admin
 *
 */


public class AfforestView extends BaseView implements OnClickListener{

	public View view_afforestView;
	public GridPhotoView mGridView;
	public TextView tv_afforestcontrol;//消防
	public TextView tv_afforestlandscape;//景观绿化
	public TextView tv_afforestcontrol_value;
	public TextView tv_afforestlandscape_value;
	
	public AfforestView(Context context, Project project){
		
		this.context=context;
		this.project = project;
		this.contactsLists = project.getBaseContacts();
		this.imagesLists = project.getProjectImgs();
		view_afforestView=View.inflate(context,R.layout.view_afforest,null);
		mGridView = new GridPhotoView(context, view_afforestView);
		tv_afforestcontrol=(TextView) view_afforestView.findViewById(id.tv_afforestcontrol);
		tv_afforestlandscape=(TextView) view_afforestView.findViewById(id.tv_afforestlandscape);
		tv_afforestcontrol_value=(TextView) view_afforestView.findViewById(id.tv_afforestcontrol_value);
		tv_afforestlandscape_value=(TextView) view_afforestView.findViewById(id.tv_afforestlandscape_value);
		
		tv_afforestcontrol.setOnClickListener(this);
		tv_afforestlandscape.setOnClickListener(this);
		
		update(true);
	
	 }

	public void update(boolean bool) {
		this.imgsType="fireControl";
		if(bool){
			updateImg(mGridView);
		}
		updateText();
	}
	
	private void updateText() {
		// TODO Auto-generated method stub
		tv_afforestcontrol_value.setText(project.getFireControl());
		tv_afforestlandscape_value.setText(project.getGreen());
	}

	public View getView(){
		return view_afforestView;
	
        }

	@Override
	public void onClick(View arg0) {
		// TODO 自动生成的方法存根
		if (arg0.getId() == R.id.tv_afforestcontrol) {
			// 消防
			final String[] items = context.getResources().getStringArray(R.array.firecontrol);
			DialogUtils.showChoiceDialog(context, "消防", items,
					new AlertDialog.OnClickListener() {

						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							// TODO Auto-generated method stub
							tv_afforestcontrol_value.setText(items[arg1]);
							project.setFireControl(items[arg1]);
						}
					});

		} else if (arg0.getId() == R.id.tv_afforestlandscape) {
			// 景观绿化
			final String[] items = context.getResources().getStringArray(
					R.array.firecontrol);
			DialogUtils.showChoiceDialog(context, "景观绿化", items,
					new AlertDialog.OnClickListener() {

						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							// TODO Auto-generated method stub
							tv_afforestlandscape_value.setText(items[arg1]);
							project.setGreen(items[arg1]);
						}
					});
		}
	}
}
