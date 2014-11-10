package com.zhongji.collection.project.detial;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.entity.Project;

/**
 * 装修阶段
 * @author admin
 *
 */
public class FitmentDetialView {
	
	@SuppressWarnings("unused")
	private Context context;
	private int resId = R.drawable.ic_mainconstruction_small;
	private String stageName = "装修阶段";
	private String imgsType = "electroweak";
	public View view_fitment_detial;
	public DetialTitleView view_detial_title;
	public DetialImgsView view_detial_imgs;
	public TextView tv_electroweakInstallation;
	public TextView tv_decorationSituation;
	public TextView tv_decorationProgress;
	

	public FitmentDetialView(Context context, LinearLayout parent) {
		this.context = context;
		view_fitment_detial = View.inflate(context, R.layout.view_fitment_detial, null);
		view_detial_title = new DetialTitleView(context, view_fitment_detial, resId, stageName, true);
		view_detial_imgs = new DetialImgsView(context, view_fitment_detial);
		tv_electroweakInstallation = (TextView) view_fitment_detial.findViewById(R.id.tv_electroweakInstallation);
		tv_decorationSituation = (TextView) view_fitment_detial.findViewById(R.id.tv_decorationSituation);
		tv_decorationProgress = (TextView) view_fitment_detial.findViewById(R.id.tv_decorationProgress);
	}
	
	/**
	 * 获取布局
	 * @return
	 */
	public View getView(){
		return view_fitment_detial;
	}

	/**
	 * 更新页面
	 * @param project
	 */
	public void updateUI(Project project) {
		// TODO Auto-generated method stub
		view_detial_title.updateUI_Foundation(project);
		view_detial_imgs.updateUI(project, imgsType);
		tv_electroweakInstallation.setText(project.getElectroweakInstallation());
		tv_decorationSituation.setText(project.getDecorationSituation());
		tv_decorationProgress.setText(project.getDecorationProgress());
		
	}

  }
