package com.zhongji.collection.project.view;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.util.DataTimeAlertDialog;
import com.zhongji.collection.util.DataTimeAlertDialog.Click;
import com.zhongji.collection.util.DialogUtils;

/**
 * 土地信息－项目立项
 * @author admin
 *
 */
public class ProCreateView implements OnClickListener{
	
	private Context context;
	public View view_procreate;
	public EditText et_proname;		//项目名称
	public TextView tv_proaddress;	//项目地址	
	public EditText et_prodes;		//项目描述
	public TextView tv_ownerunit;	//业主单位
	public TextView tv_starttime;	//开工时间
	public TextView tv_endtime;		//完工时间
	public EditText et_investmoney;	//投资额
	public EditText et_buildarea;	//建筑面积
	public EditText et_buildfloor;	//建筑层数
	public TextView tv_wzjoin;		//外资参与
	public TextView tv_wzjoinshow;  //外资参与
	public TextView tv_ownertype;	//业主类型
	
	public ProCreateView(Context context) {
		this.context=context;
		view_procreate = View.inflate(context, R.layout.view_procreate, null);
		et_proname = (EditText) view_procreate.findViewById(R.id.et_proname);
		tv_proaddress = (TextView) view_procreate.findViewById(R.id.tv_proaddress);
		et_prodes = (EditText) view_procreate.findViewById(R.id.et_prodes);
		tv_ownerunit = (TextView) view_procreate.findViewById(R.id.tv_ownerunit); 
		tv_starttime = (TextView) view_procreate.findViewById(R.id.tv_starttime);
		tv_endtime = (TextView) view_procreate.findViewById(R.id.tv_endtime);
		et_investmoney = (EditText) view_procreate.findViewById(R.id.et_investmoney);
		et_buildarea = (EditText) view_procreate.findViewById(R.id.et_buildarea);
		et_buildfloor = (EditText) view_procreate.findViewById(R.id.et_buildfloor);
		tv_wzjoin = (TextView) view_procreate.findViewById(R.id.tv_wzjoin);
		tv_wzjoinshow = (TextView) view_procreate.findViewById(R.id.tv_wzjoinshow);
		tv_ownertype = (TextView) view_procreate.findViewById(R.id.tv_ownertype);
		
		tv_starttime.setOnClickListener(this);
		tv_endtime.setOnClickListener(this);
		tv_wzjoin.setOnClickListener(this);
		tv_ownertype.setOnClickListener(this);
		tv_ownerunit.setOnClickListener(this);
		
	}
	
	public View getView(){
		return view_procreate;
	}

	@Override
	public void onClick(View arg0) {
		// TODO 自动生成的方法存根
		
		if(arg0.getId()==R.id.tv_starttime){
		//预计开始时间
			DataTimeAlertDialog dialog=new DataTimeAlertDialog(context);
			dialog.setClickListener(new Click() {

				@Override
				public void sure(String date, String time) {
					// TODO Auto-generated method stub
				}
				@Override
				public void cancel() {
					// TODO Auto-generated method stub
				}
			});
			dialog.show();
			
		}else if(arg0.getId()==R.id.tv_endtime) {
		//预计竣工时间
			DataTimeAlertDialog dialog=new DataTimeAlertDialog(context);
			dialog.setClickListener(new Click() {

				@Override
				public void sure(String date, String time) {
					// TODO Auto-generated method stub
				}
				@Override
				public void cancel() {
					// TODO Auto-generated method stub
				}
			});
			dialog.show();
			
		}else if(arg0.getId()==R.id.tv_wzjoin) {
		//外资参与
			
		//DialogUtils.showChoiceDialog(context, R.array.foreigninvestment);	
			
		}else if(arg0.getId()==R.id.tv_ownertype) {
		//业主类型
			
		DialogUtils.showMultiChoiceDialog(context,R.array.typeofowner);	
			
		}else if(arg0.getId()==R.id.tv_ownerunit){
		//业主单位	
		DialogUtils.showContactsDialog(context, R.array.ownercompanypost);	
		
		}
	}
}
