package com.zhongji.collection.project.view;

import org.apache.commons.codec.digest.DigestUtils;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.util.DialogUtils;

/**
 * 土地信息－土地规划
 * @author admin
 *
 */
public class LandPlanView implements OnClickListener{
	
	private Context context;
	public View view_landplan;
	public EditText et_landname;	//地块名称
	public TextView tv_provinces;	//所属省市
	public EditText et_address;		//地块地址
	public EditText et_landarea;	//土地面积
	public EditText et_landvolume;	//土地容积率
	public TextView tv_landuse;		//地块用途
	public TextView tv_auctionunit;	//拍卖单位
	

	public LandPlanView(Context context) {
		this.context = context;
		view_landplan = View.inflate(context, R.layout.view_landplan, null);
		et_landname = (EditText) view_landplan.findViewById(R.id.et_landname);
		tv_provinces = (TextView) view_landplan.findViewById(R.id.tv_provinces);
		et_address = (EditText) view_landplan.findViewById(R.id.et_address);
		et_landarea = (EditText) view_landplan.findViewById(R.id.et_landarea);
		et_landvolume = (EditText) view_landplan.findViewById(R.id.et_landvolume);
		tv_landuse = (TextView) view_landplan.findViewById(R.id.tv_landuse);
		tv_auctionunit = (TextView) view_landplan.findViewById(R.id.tv_auctionunit);
		
		tv_landuse.setOnClickListener(this);
		tv_auctionunit.setOnClickListener(this);
		
		
	}
	
	public View getView(){
		return view_landplan;
	}

	@Override
	public void onClick(View arg0) {
		// TODO 自动生成的方法存根
		
		 if(arg0.getId()==R.id.tv_landuse){
		//地块用途	
			
		DialogUtils.showMultiChoiceDialog(context, R.array.Landusage);	 
			 
			
		}else if(arg0.getId()==R.id.tv_auctionunit){
		//拍卖单位
			
       DialogUtils.showContactsDialog(context,R.array.Auctionunitpost);
			
		}
	 }
  }
