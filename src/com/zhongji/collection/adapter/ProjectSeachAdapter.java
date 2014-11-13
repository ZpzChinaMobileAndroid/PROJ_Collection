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
import com.zhongji.collection.android.phone.R.id;
import com.zhongji.collection.entity.Project;
import com.zhongji.collection.project.SearchProActivity;

public class ProjectSeachAdapter extends BaseAdapter {

	private List<Project> lists;
	private Context context;
	
	public ProjectSeachAdapter(Context context) {
		this.context = context;
		this.lists = new ArrayList<Project>();
	}
	
	public ProjectSeachAdapter(SearchProActivity searchProActivity) {
		// TODO 自动生成的构造函数存根
	}

	public void setLists(List<Project> lists) {
		this.lists = lists;
	}

	
	@Override
	public int getCount() {
		// TODO 自动生成的方法存根
		return lists.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO 自动生成的方法存根
		return lists.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO 自动生成的方法存根
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO 自动生成的方法存根
		ViewHolder viewHolder;
		if(arg1==null){
			arg1 = View.inflate(context, R.layout.items_list_project, null);
			viewHolder = new ViewHolder();
			
			viewHolder.iv_seach_find=(ImageView) arg1.findViewById(id.iv_seach_find);
			viewHolder.tv_seach_content=(TextView) arg1.findViewById(id.tv_seach_content);
			viewHolder.tv_seach_number=(TextView) arg1.findViewById(id.tv_seach_number);
			viewHolder.tv_seach_number_quantity=(TextView) arg1.findViewById(id.tv_seach_number_quantity);
			arg1.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder)arg1.getTag();
		}
		
		Project pro=lists.get(arg0);
		if(pro!=null){
			viewHolder.iv_seach_find.setBackgroundResource(R.drawable.seach_find_seach);
			viewHolder.tv_seach_content.setText("");
			viewHolder.tv_seach_number.setText("");
			viewHolder.tv_seach_number_quantity.setText("条");
		}
		return arg1;
	}
	class ViewHolder{
		
		ImageView iv_seach_find;
		TextView tv_seach_content;
		TextView tv_seach_number;
		TextView tv_seach_number_quantity;
		
	}
}
