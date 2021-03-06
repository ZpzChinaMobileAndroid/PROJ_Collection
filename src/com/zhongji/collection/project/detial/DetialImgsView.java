package com.zhongji.collection.project.detial;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.entity.Images;
import com.zhongji.collection.entity.ImagesListBean;
import com.zhongji.collection.entity.Project;
import com.zhongji.collection.project.BigPhotoActivity;
import com.zhongji.collection.util.BitmapUtil;
import com.zhongji.collection.util.ImageLoaderUtils;

/**
 * 详情图片集合
 * @author admin
 *
 */
public class DetialImgsView{
	
	@SuppressWarnings("unused")
	private Context context;
	public View view_detial_imgs;
	public ImageView iv_imgs;
	public TextView tv_number;
	private List<Images> lists;
	

	public DetialImgsView(Context context, LinearLayout parent) {
		this.context = context;
		initial(context, view_detial_imgs);
	}
	
	public DetialImgsView(Context context, View view_detial_imgs) {
		initial(context, view_detial_imgs);
	}

	/**
	 * 初始化
	 * @param context
	 * @param view_detial_imgs
	 */
	private void initial(final Context context, View view_detial_imgs) {
		
		this.lists = new ArrayList<Images>();
		this.context = context;
		iv_imgs = (ImageView) view_detial_imgs.findViewById(R.id.iv_imgs);
		tv_number = (TextView) view_detial_imgs.findViewById(R.id.tv_number);
		
		view_detial_imgs.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(lists.size()>0){
					Activity act = (Activity) context;
					Intent intent = new Intent();
					intent.setClass(context, BigPhotoActivity.class);
					intent.putExtra("count", lists.size());
					intent.putExtra("imgslists", (Serializable) lists);
					act.startActivity(intent);
				}
			}
		});
	}
	
	/**
	 * 获取布局
	 * @return
	 */
	public View getView(){
		return view_detial_imgs;
	}

	/**
	 * 更新页面
	 * @param project
	 */
	public void updateUI(Project project, String type) {
		// TODO Auto-generated method stub
		getList(project, type);
		
		if(lists.size()>0){
			Images img = lists.get(0);
			if(img.getImgContent().startsWith("/9j/")){
				//本地
				iv_imgs.setImageBitmap(BitmapUtil.base64ToBitmap(img.getImgContent()));
			}else{
				//网络
				ImageLoaderUtils.getInstance(context).displayImage(img.getImgContent(), iv_imgs);
			}
			
		}
		
		tv_number.setText(lists.size()+"张");
	}

	/**
	 * 计算分组
	 * @param project
	 */
	private void getList(Project project, String type) {
		lists.clear();
		List<ImagesListBean> imgs = project.getProjectImgs();
		if(imgs!=null && imgs.size()>0){
			ImagesListBean images = imgs.get(0);
			List<Images> imglists = images.getData();
			if(imglists!=null && imglists.size()>0){
				for(Images img : imglists){
					if(type.equals(img.getCategory())){
						lists.add(img);
					}
				}
				
			}
		}
	}
}

