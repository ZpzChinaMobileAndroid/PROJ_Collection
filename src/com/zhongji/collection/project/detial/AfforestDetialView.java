package com.zhongji.collection.project.detial;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.entity.Project;

/**
 * 主体施工-消防/景观绿化
 * @author admin
 *
 */
public class AfforestDetialView {
	
	@SuppressWarnings("unused")
	private Context context;
	private int resId = R.drawable.ic_mainconstruction_small;
	private String stageName = "消防/景观绿化";
	private String imgsType = "fireControl";
	public View view_afforest_detial;
	public DetialTitleView view_detial_title;
	public DetialImgsView view_detial_imgs;
	public TextView tv_fireControl;
	public TextView tv_green;
	

	public AfforestDetialView(Context context, LinearLayout parent) {
		this.context = context;
		view_afforest_detial = View.inflate(context, R.layout.view_afforest_detial, null);
		view_detial_title = new DetialTitleView(context, view_afforest_detial, resId, stageName, true);
		view_detial_imgs = new DetialImgsView(context, view_afforest_detial);
		tv_fireControl = (TextView) view_afforest_detial.findViewById(R.id.tv_fireControl);
		tv_green = (TextView) view_afforest_detial.findViewById(R.id.tv_green);
	}
	
	/**
	 * 获取布局
	 * @return
	 */
	public View getView(){
		return view_afforest_detial;
	}

	/**
	 * 更新页面
	 * @param project
	 */
	public void updateUI(Project project) {
		// TODO Auto-generated method stub
		view_detial_title.updateUI_Foundation(project);
		view_detial_imgs.updateUI(project, imgsType);
		tv_fireControl.setText(project.getFireControl());
		tv_green.setText(project.getGreen());
		
	}

  }
