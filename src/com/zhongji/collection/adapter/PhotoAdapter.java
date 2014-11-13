package com.zhongji.collection.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.interfaces.OnItemViewCilck;
import com.zhongji.collection.util.BitmapUtil;

public class PhotoAdapter extends BaseAdapter{
	
	private Context context;
	private List<String> lists;
	private OnItemViewCilck listenter;
	
	public PhotoAdapter(Context context) {
		this.context = context;
		this.lists = new ArrayList<String>();
	}

	public void setListenter(OnItemViewCilck listenter) {
		this.listenter = listenter;
	}

	public void setLists(List<String> lists) {
		this.lists = lists;
	}

	@Override 
	public int getCount() {
		// TODO Auto-generated method stub
		int count = lists.size();
		if(count == 0 || !"add".equals(lists.get(count-1))){
			lists.add("add");
		}
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
	public View getView(final int position, View convertView, ViewGroup arg2) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if(convertView == null){
			convertView = View.inflate(context, R.layout.items_grid_photo, null);
			holder = new ViewHolder();
			holder.iv_photo = (ImageView) convertView.findViewById(R.id.iv_photo);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		if(position != lists.size() - 1){
			String str = lists.get(position);
			if(str!=null){
				holder.iv_photo.setImageBitmap(BitmapUtil.base64ToBitmap(str));
			}
			holder.iv_photo.setOnClickListener(null);
		}else{
			holder.iv_photo.setImageResource(R.drawable.ic_grid_photo);
			holder.iv_photo.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					if(listenter!=null){
						listenter.click(position);
					}
				}
			});
		}
		
		
		return convertView;
	}
	
	class ViewHolder{
		ImageView iv_photo;
	}

}
