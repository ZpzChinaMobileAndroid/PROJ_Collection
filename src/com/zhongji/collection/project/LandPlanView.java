package com.zhongji.collection.project;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.zhongji.collection.android.phone.R;

/**
 * 土地信息－土地规划
 * @author admin
 *
 */
public class LandPlanView {
	
	public View view_landplan;
	public EditText et_landname;
	public TextView tv_provinces;
	public EditText et_address;
	public EditText et_landarea;
	public EditText et_landvolume;
	public TextView tv_landuse;
	public TextView tv_auctionunit;
	

	public LandPlanView(Context context) {
		view_landplan = View.inflate(context, R.layout.view_landplan, null);
		et_landname = (EditText) view_landplan.findViewById(R.id.et_landname);
		tv_provinces = (TextView) view_landplan.findViewById(R.id.tv_provinces);
		et_address = (EditText) view_landplan.findViewById(R.id.et_address);
		et_landarea = (EditText) view_landplan.findViewById(R.id.et_landarea);
		et_landvolume = (EditText) view_landplan.findViewById(R.id.et_landvolume);
		tv_landuse = (TextView) view_landplan.findViewById(R.id.tv_landuse);
		tv_auctionunit = (TextView) view_landplan.findViewById(R.id.tv_auctionunit);
	}
	
	public View getView(){
		return view_landplan;
	}
}
