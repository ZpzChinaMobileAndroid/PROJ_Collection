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
 * 装修阶段
 * @author Admin
 *
 */



public class FitmentView extends BaseView implements OnClickListener{
	
	public View view_fitment;
	public GridPhotoView mGridView;
	public TextView tv_fitmemtweak;//弱电安装
	public TextView tv_fitmentcondition;//装修情况	
	public TextView tv_fitmentstage;//装修阶段	
	public TextView tv_fitmemtweak_value;
	public TextView tv_fitmentcondition_value;
	public TextView tv_fitmentstage_value;
	
	public FitmentView(Context context,Project project){
		
		this.context=context;
		this.project = project;
		this.contactsLists = project.getBaseContacts();
		this.imagesLists = project.getProjectImgs();
		view_fitment=View.inflate(context,R.layout.view_fitment,null);
		mGridView = new GridPhotoView(context, view_fitment);
		tv_fitmemtweak=(TextView) view_fitment.findViewById(id.tv_fitmemtweak);
		tv_fitmentcondition=(TextView) view_fitment.findViewById(id.tv_fitmentcondition);
		tv_fitmentstage=(TextView) view_fitment.findViewById(id.tv_fitmentstage);
		tv_fitmemtweak_value=(TextView) view_fitment.findViewById(id.tv_fitmemtweak_value);
		tv_fitmentcondition_value=(TextView) view_fitment.findViewById(id.tv_fitmentcondition_value);
		tv_fitmentstage_value=(TextView) view_fitment.findViewById(id.tv_fitmentstage_value);
	
		tv_fitmemtweak.setOnClickListener(this);
		tv_fitmentcondition.setOnClickListener(this);
		tv_fitmentstage.setOnClickListener(this);
		
		update(true);
		
	 }

	public void update(boolean bool) {
		this.imgsType="electroweak";
		if(bool){
			updateImg(mGridView);
		}
		updateText();
	}

	private void updateText() {
		// TODO Auto-generated method stub
		tv_fitmemtweak_value.setText(project.getElectroweakInstallation());
		tv_fitmentcondition_value.setText(project.getDecorationSituation());
		tv_fitmentstage_value.setText(project.getDecorationProgress());
	}

	public View getView(){
		return view_fitment;
	
        }

	@Override
	public void onClick(View arg0) {
		// TODO 自动生成的方法存根
		if(arg0.getId()==R.id.tv_fitmemtweak){
			//弱电安装	
			final String[] items = context.getResources().getStringArray(R.array.firecontrol);
			DialogUtils.showChoiceDialog(context, "弱电安装", items,
					new AlertDialog.OnClickListener() {

						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							// TODO Auto-generated method stub
							tv_fitmemtweak_value.setText(items[arg1]);
							project.setElectroweakInstallation(items[arg1]);
						}
					});
			
		}else if(arg0.getId()==R.id.tv_fitmentcondition){
			//装修情况	
			final String[] items = context.getResources().getStringArray(R.array.fitmentcondition);
			DialogUtils.showChoiceDialog(context, "装修情况", items,
					new AlertDialog.OnClickListener() {

						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							// TODO Auto-generated method stub
							tv_fitmentcondition_value.setText(items[arg1]);
							project.setDecorationSituation(items[arg1]);
						}
					});
			
		}else if(arg0.getId()==R.id.tv_fitmentstage){
			//装修阶段	
			final String[] items = context.getResources().getStringArray(R.array.firecontrol);
			DialogUtils.showChoiceDialog(context, "装修阶段", items,
					new AlertDialog.OnClickListener() {

						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							// TODO Auto-generated method stub
							tv_fitmentstage_value.setText(items[arg1]);
							project.setDecorationProgress(items[arg1]);
						}
					});
			
		   }
	    }
     }
