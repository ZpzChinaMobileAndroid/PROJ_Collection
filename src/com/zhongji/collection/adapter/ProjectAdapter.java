package com.zhongji.collection.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.entity.Project;

public class ProjectAdapter extends BaseAdapter{
	
	private int[] stagesImgs = {R.drawable.pro_stage1,R.drawable.pro_stage2,R.drawable.pro_stage3,R.drawable.pro_stage4};
	private Context context;
	private List<Project> lists;
	
	public ProjectAdapter(Context context) {
		this.context = context;
		this.lists = new ArrayList<Project>();
	}
 
	public void setLists(List<Project> lists) {
		this.lists = lists;
	}

	@Override 
	public int getCount() {
		// TODO Auto-generated method stub
		return lists.size();
	}

	@Override
	public Project getItem(int arg0) {
		// TODO Auto-generated method stub
		return lists.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if(convertView == null){
			convertView = View.inflate(context, R.layout.items_list_project, null);
			holder = new ViewHolder();
			
			holder.tv_proname = (TextView) convertView.findViewById(R.id.tv_proname);
			holder.iv_prostage = (ImageView) convertView.findViewById(R.id.iv_prostage);
			holder.tv_investmentmoney = (TextView) convertView.findViewById(R.id.tv_investmentmoney);
			holder.tv_buildarea = (TextView) convertView.findViewById(R.id.tv_buildarea);
			holder.tv_starttime = (TextView) convertView.findViewById(R.id.tv_starttime);
			holder.tv_endtime = (TextView) convertView.findViewById(R.id.tv_endtime);
			holder.iv_map = (ImageView) convertView.findViewById(R.id.iv_map);
			holder.tv_district = (TextView) convertView.findViewById(R.id.tv_district);
			holder.tv_address = (TextView) convertView.findViewById(R.id.tv_address);
			holder.iv_more = (ImageView) convertView.findViewById(R.id.iv_more);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		Project pro = lists.get(position);
		
		
		if(pro!=null){
//			ImageLoaderUtils.getInstance().displayImage(context, info.getTximg(), holder.IvHead);
			int stage = Integer.parseInt(pro.getProjectStage());
			
			Double double1= new Double(pro.getAreaOfStructure());
			java.text.NumberFormat nfFormat=java.text.NumberFormat.getInstance();
			nfFormat.setGroupingUsed(false);
			
			holder.iv_prostage.setImageResource(stagesImgs[stage-1]);
			holder.tv_proname.setText(pro.getProjectName());
			holder.tv_investmentmoney.setText(pro.getInvestment());
			holder.tv_buildarea.setText(pro.getAreaOfStructure());
			holder.tv_starttime.setText(pro.getExpectedStartTime());
			holder.tv_endtime.setText(pro.getExpectedFinishTime());
			holder.tv_district.setText(pro.getDistrict());
			holder.tv_address.setText(pro.getLandAddress());
		}
		
		return convertView;
	}
	
	class ViewHolder{
		TextView tv_proname;
		ImageView iv_prostage;
		TextView tv_investmentmoney;
		TextView tv_buildarea;
		TextView tv_starttime;
		TextView tv_endtime;
		ImageView iv_map;
		TextView tv_district;
		TextView tv_address;
		ImageView iv_more;
	}

}
