package com.zhongji.collection.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhongji.collection.android.phone.R;

public class SearchAdapter extends BaseAdapter{
	
	private Context context;
	private List<String> lists;
	
	public SearchAdapter(Context context) {
		this.context = context;
		this.lists = new ArrayList<String>();
	}

	public void setLists(List<String> lists) {
		this.lists = lists;
	}

	@Override 
	public int getCount() {
		// TODO Auto-generated method stub
		return lists.size();
	}

	@Override
	public String getItem(int arg0) {
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
			convertView = View.inflate(context, R.layout.items_list_string, null);
			holder = new ViewHolder();
			holder.tv_text = (TextView) convertView.findViewById(R.id.tv_text);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		String text = lists.get(position);
		if(text!=null){
			holder.tv_text.setText(text);
		}
		
		return convertView;
	}
	
	class ViewHolder{
		TextView tv_text;
	}

}
