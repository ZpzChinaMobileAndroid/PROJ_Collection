package com.zhongji.collection.project.detial;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.entity.Images;
import com.zhongji.collection.entity.ImagesListBean;
import com.zhongji.collection.entity.Project;
import com.zhongji.collection.util.BitmapUtil;

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
	private void initial(Context context, View view_detial_imgs) {
		this.lists = new ArrayList<Images>();
		this.context = context;
		iv_imgs = (ImageView) view_detial_imgs.findViewById(R.id.iv_imgs);
		tv_number = (TextView) view_detial_imgs.findViewById(R.id.tv_number);
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
			iv_imgs.setImageBitmap(BitmapUtil.base64ToBitmap(img.getImgCompressionContent()));
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
						//土地规划 plan
						lists.add(img);
					}
				}
				
			}
		}
	}
}

