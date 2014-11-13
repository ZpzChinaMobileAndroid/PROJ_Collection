package com.zhongji.collection.project.view;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;

import com.zhongji.collection.adapter.PhotoAdapter;
import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.interfaces.OnItemViewCilck;
import com.zhongji.collection.util.PhotoUtils;

public class GridPhotoView {
	
	@SuppressWarnings("unused")
	private Context context;
	private PhotoAdapter adapter;
	public PhotoUtils mPhotoUtils;
	public GridView gridView1;
	public List<String> lists;
	private View parent;

	public GridPhotoView(Context context, View parent) {
		this.context = context;
		this.parent = parent;
		this.adapter = new PhotoAdapter(context);
		this.mPhotoUtils = new PhotoUtils(context);
		this.lists = new ArrayList<String>();
		gridView1 = (GridView) parent.findViewById(R.id.gridView1);
		gridView1.setAdapter(adapter);
		
		adapter.setListenter(new OnItemViewCilck() {
			
			@Override
			public void click(int pos) {
				// TODO Auto-generated method stub
				mPhotoUtils.getDialogPhoto();
			}
		});
		
	}
	
	public View getParent(){
		return parent;
	}
	
	public boolean clear(){
		if(lists.size()!=0){
			lists.clear();
			adapter.setLists(lists);
			adapter.notifyDataSetChanged();
			setListViewHeight(gridView1);
			return true;
		}
		return false;
	}
	
	public void addString(String str){
		lists.remove("add");
		
		lists.add(str);
	}
	
	public void notifyDataSetChanged(){
		adapter.setLists(lists);
		adapter.notifyDataSetChanged();
		
		setListViewHeight(gridView1);
	}
	
	private void setListViewHeight(GridView gridview) {  
		//获取ListView对应的Adapter  
		ListAdapter listAdapter = gridview.getAdapter();  
		if (listAdapter == null) {  
		return;  
		}  
		int totalHeight = 0;  
		int linecount = listAdapter.getCount() / 4 + 1;
		for (int i = 0, len = linecount; i < len; i++) { //listAdapter.getCount()返回数据项的数目  
		View listItem = listAdapter.getView(i, null, gridview);  
		listItem.measure(0, 0); //计算子项View 的宽高  
		totalHeight += listItem.getMeasuredHeight(); //统计所有子项的总高度  
		}  
		  
		  
		ViewGroup.LayoutParams params = gridview.getLayoutParams();  
		params.height = totalHeight + linecount * gridview.getHorizontalSpacing();  //+ ((listAdapter.getCount() - 1))
		//listView.getDividerHeight()获取子项间分隔符占用的高度  
		//params.height最后得到整个ListView完整显示需要的高度  
		gridview.setLayoutParams(params);  
		}  
}
