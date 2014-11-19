package com.zhongji.collection.project.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.entity.Project;
import com.zhongji.collection.project.map.MapActivity;
import com.zhongji.collection.util.DataTimeAlertDialog;
import com.zhongji.collection.util.DataTimeAlertDialog.Click;
import com.zhongji.collection.util.DialogUtils;
import com.zhongji.collection.util.ToastUtils;

/**
 * 土地信息－项目立项
 * 
 * @author admin
 * 
 */
public class ProCreateView extends BaseView implements OnClickListener {

	private String type = "ownerUnitContacts";
	public View view_procreate;
	public EditText et_proname; // 项目名称
	public TextView tv_proaddress; // 项目地址
	public EditText et_prodes; // 项目描述
	public TextView tv_ownerunit; // 业主单位
	public TextView tv_starttime; // 开工时间
	public TextView tv_endtime; // 完工时间
	public EditText et_investmoney; // 投资额
	public EditText et_buildarea; // 建筑面积
	public EditText et_buildfloor; // 建筑层数
	public TextView tv_wzjoin; // 外资参与
	public TextView tv_wzjoinshow; // 外资参与
	public TextView tv_ownertype; // 业主类型
	private TextView tv_ownertype_value;

	public ProCreateView(Context context, Project project) {
		this.context = context;
		this.project = project;
		this.contactsLists = project.getBaseContacts();
		view_procreate = View.inflate(context, R.layout.view_procreate, null);
		et_proname = (EditText) view_procreate.findViewById(R.id.et_proname);
		tv_proaddress = (TextView) view_procreate
				.findViewById(R.id.tv_proaddress);
		et_prodes = (EditText) view_procreate.findViewById(R.id.et_prodes);
		tv_ownerunit = (TextView) view_procreate
				.findViewById(R.id.tv_ownerunit);
		tv_starttime = (TextView) view_procreate
				.findViewById(R.id.tv_starttime);
		tv_endtime = (TextView) view_procreate.findViewById(R.id.tv_endtime);
		et_investmoney = (EditText) view_procreate
				.findViewById(R.id.et_investmoney);
		et_buildarea = (EditText) view_procreate
				.findViewById(R.id.et_buildarea);
		et_buildfloor = (EditText) view_procreate
				.findViewById(R.id.et_buildfloor);
		tv_wzjoin = (TextView) view_procreate.findViewById(R.id.tv_wzjoin);
		tv_wzjoinshow = (TextView) view_procreate
				.findViewById(R.id.tv_wzjoinshow);
		tv_ownertype = (TextView) view_procreate
				.findViewById(R.id.tv_ownertype);
		layout_contacts = (LinearLayout) view_procreate.findViewById(R.id.layout_contacts);
		tv_ownertype_value = (TextView) view_procreate.findViewById(R.id.tv_ownertype_value);

		tv_starttime.setOnClickListener(this);
		tv_endtime.setOnClickListener(this);
		tv_wzjoin.setOnClickListener(this);
		tv_ownertype.setOnClickListener(this);
		tv_ownerunit.setOnClickListener(this);
		tv_proaddress.setOnClickListener(this);
		setOnTextChange(et_proname);
		setOnTextChange(et_prodes);
		setOnTextChange(et_investmoney);
		setOnTextChange(et_buildarea);
		setOnTextChange(et_buildfloor);
		
		setSoftInput(context, view_procreate);
		
		update();

	}

	public void update() {
		updateEditText();
		updateTime();
		updateAddress();
		updateContacts(type);
	}
	
	@Override
	protected void setValue(EditText et, String text) {
		// TODO Auto-generated method stub
		super.setValue(et, text);
		switch(et.getId()){
		case R.id.et_proname:
			project.setProjectName(text);
			break;
		case R.id.et_prodes:
			project.setDescription(text);
			break;
		case R.id.et_investmoney:
			project.setInvestment(text);
			break;
		case R.id.et_buildarea:
			project.setAreaOfStructure(text);
			break;
		case R.id.et_buildfloor:
			project.setStoreyHeight(text);
			break;
		}
	}
	
	public void updateAddress() {
		// TODO Auto-generated method stub
		tv_proaddress.setText(project.getLandAddress());
	}

	private void updateTime() {
		// TODO Auto-generated method stub
		tv_starttime.setText("预计开工时间: "+project.getExpectedStartTime());
		tv_endtime.setText("预计竣工时间: " + project.getExpectedFinishTime());
		tv_wzjoinshow.setText("true".equals(project.getForeignInvestment())?"参加":"不参加");
		tv_ownertype_value.setText(project.getOwnerType());
	}

	private void updateEditText() {
		// TODO Auto-generated method stub
		et_proname.setText(project.getProjectName());
		et_prodes.setText(project.getDescription());
		et_investmoney.setText(project.getInvestment());
		et_buildarea.setText(project.getAreaOfStructure());
		et_buildfloor.setText(project.getStoreyHeight());
	}

	public View getView() {
		return view_procreate;
	}

	@Override
	public void onClick(View arg0) {
		// TODO 自动生成的方法存根
		if(arg0.getId() == R.id.tv_proaddress){
			//地图搜索
			Activity act = (Activity) context;
			Intent intent = new Intent();
			intent.setClass(context, MapActivity.class);
//			intent.putExtra("address", project.getLandAddress());
			act.startActivityForResult(intent, 10);
		}else if (arg0.getId() == R.id.tv_starttime) {
			// 预计开始时间
			DataTimeAlertDialog dialog = new DataTimeAlertDialog(context);
			dialog.setClickListener(new Click() {

				@Override
				public void sure(String date, String time) {
					// TODO Auto-generated method stub
//					String endtime = tv_endtime.getText().toString().replace("预计竣工时间: ", "");
//					long end = TimeUtils.strtolong(endtime);
//					long start = TimeUtils.strtolong(date);
					
					tv_starttime.setText("预计开工时间: " + date);
					project.setExpectedStartTime(tv_starttime.getText().toString().replace("预计开工时间: ", ""));
					
//					if(start > end){
//						tv_endtime.setText("预计竣工时间: " + date);
//						project.setExpectedFinishTime(tv_endtime.getText().toString().replace("预计竣工时间: ", ""));
//					}
					
				}

				@Override
				public void cancel() {
					// TODO Auto-generated method stub
				}
			});
			dialog.show();

		} else if (arg0.getId() == R.id.tv_endtime) {
			// 预计竣工时间
			DataTimeAlertDialog dialog = new DataTimeAlertDialog(context);
			dialog.setClickListener(new Click() {

				@Override
				public void sure(String date, String time) {
					// TODO Auto-generated method stub
//					String starttime = tv_starttime.getText().toString().replace("预计开工时间: ", "");
//					long start = TimeUtils.strtolong(starttime);
//					long end = TimeUtils.strtolong(date);
					
					tv_endtime.setText("预计竣工时间: " + date);
					project.setExpectedFinishTime(tv_endtime.getText().toString().replace("预计竣工时间: ", ""));
					
//					if(start > end){
//						tv_starttime.setText("预计开工时间: " + date);
//						project.setExpectedStartTime(tv_starttime.getText().toString().replace("预计开工时间: ", ""));
//					}
					
					
				}

				@Override
				public void cancel() {
					// TODO Auto-generated method stub
				}
			});
			dialog.show();

		} else if (arg0.getId() == R.id.tv_wzjoin) {
			// 外资参与
			 final String[] items = context.getResources().getStringArray(R.array.foreigninvestment);
			 DialogUtils.showChoiceDialog(context, "外资参与", items, new AlertDialog.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					// TODO Auto-generated method stub
					tv_wzjoinshow.setText(items[arg1]);
					project.setForeignInvestment(items[arg1].contains("不")?"false":"true");
				}
			});

		} else if (arg0.getId() == R.id.tv_ownertype) {
			// 业主类型
			final String[] items = context.getResources().getStringArray(R.array.typeofowner);
			final boolean[] checkedItems = new boolean[] { false, false, false,false, false, false };
			DialogUtils.showMultiChoiceDialog(context, "业主类型", items, checkedItems, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					// TODO Auto-generated method stub
					tv_ownertype_value.setText(getCheckedText(items, checkedItems));
					project.setOwnerType(tv_ownertype_value.getText().toString());
				}
			});
			

		} else if (arg0.getId() == R.id.tv_ownerunit) {
			// 业主单位
			if(getListCount(type)<3){
				showUnitDialog(-1, null, type, R.array.ownercompanypost);
			}else{
				ToastUtils.getStance(context).showShortToast("名额已经满了！");
			}

		}
	}
}
