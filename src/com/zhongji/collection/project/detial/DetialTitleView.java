package com.zhongji.collection.project.detial;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.entity.Project;

/**
 * 详情头
 * @author admin
 *
 */
public class DetialTitleView{
	
	@SuppressWarnings("unused")
	private Context context;
	public View view_detial_title;
	public ImageView iv_detial_icon;
	public TextView tv_detial_stagechildname;
	private View layout_line;
	public TextView tv_detial_title;
	public TextView tv_detial_area;
	public TextView tv_detial_address;
	
	public TextView tv_exstarttime;
	public TextView tv_exendtime;

	public DetialTitleView(Context context, LinearLayout parent, int resourceid, String text) {
		this.context = context;
		view_detial_title = LayoutInflater.from(context).inflate(R.layout.view_detial_title, parent, false);
		initial(context, view_detial_title, resourceid, text, false);
	}
	
	public DetialTitleView(Context context, View view_detial_title, int resourceid, String text) {
		initial(context, view_detial_title, resourceid, text, false);
	}
	
	public DetialTitleView(Context context, View view_detial_title, int resourceid, String text, boolean bool) {
		initial(context, view_detial_title, resourceid, text, bool);
	}

	/**
	 * 出图阶段单独用
	 */
	public DetialTitleView(Context context, View view_detial_title, int resourceid, String text, String time) {
		initial(context, view_detial_title, resourceid, text, false);
	}
	/**
	 * 初始化
	 * @param context
	 * @param view_detial_title
	 * @param resourceid
	 * @param text
	 */
	private void initial(Context context, View view_detial_title,
			int resourceid, String text, boolean bool) {
		this.context = context;
		iv_detial_icon = (ImageView) view_detial_title.findViewById(R.id.iv_detial_icon);
		tv_detial_stagechildname = (TextView) view_detial_title.findViewById(R.id.tv_detial_stagechildname);
		layout_line = view_detial_title.findViewById(R.id.layout_line);
		tv_detial_title = (TextView) view_detial_title.findViewById(R.id.tv_detial_title);
		tv_detial_area = (TextView) view_detial_title.findViewById(R.id.tv_detial_area);
		tv_detial_address = (TextView) view_detial_title.findViewById(R.id.tv_detial_address);
		tv_exstarttime = (TextView) view_detial_title.findViewById(R.id.tv_exstarttime);
		tv_exendtime = (TextView) view_detial_title.findViewById(R.id.tv_exendtime);
		
		iv_detial_icon.setImageResource(resourceid);
		tv_detial_stagechildname.setText(text);
		
		if(bool){
			layout_line.setVisibility(View.GONE);
			tv_detial_title.setVisibility(View.GONE);
			tv_detial_area.setVisibility(View.GONE);
			tv_detial_address.setVisibility(View.GONE);
		}
	}
	
	/**
	 * 获取布局
	 * @return
	 */
	public View getView(){
		return view_detial_title;
	}

	/**
	 * 更新页面-土地规划
	 * @param project
	 */
	public void updateUI(Project project) {
		// TODO Auto-generated method stub
		tv_detial_title.setText(project.getLandName());
		tv_detial_area.setText(project.getProvince()+" "+project.getDistrict()+" "+project.getCity());
		tv_detial_address.setText(project.getLandAddress());
	}
	
	/**
	 * 更新页面-项目立项
	 * @param project
	 */
	public void updateUI_ProCreate(Project project) {
		// TODO Auto-generated method stub
		tv_detial_title.setText(project.getProjectName());
		tv_detial_area.setText(project.getLandAddress());
		tv_detial_address.setText(project.getDescription());
	}
	
	/**
	 * 更新页面-设计阶段
	 * @param project
	 */
	public void updateUI_Design(Project project) {
		// TODO Auto-generated method stub
		tv_detial_title.setText("主体设计阶段");
		tv_detial_area.setText("");
		tv_detial_area.setVisibility(View.GONE);
		tv_detial_address.setText(project.getMainDesignStage());
	}
	
	/**
	 * 更新页面-出图阶段
	 * @param project
	 */
	public void updateUI_DrawStage(Project project) {
		// TODO Auto-generated method stub
		tv_exstarttime.setText(project.getExpectedStartTime2());
		tv_exendtime.setText(project.getExpectedFinishTime2());
	}
	
	/**
	 * 更新页面-地平阶段
	 * @param project
	 */
	public void updateUI_Horizon(Project project) {
		// TODO Auto-generated method stub
		tv_detial_title.setText("实际开工时间");
		tv_detial_area.setText("");
		tv_detial_area.setVisibility(View.GONE);
		tv_detial_address.setText(project.getActualStartTime());
	}
	
	/**
	 * 更新页面-桩基基坑
	 * @param project
	 */
	public void updateUI_Foundation(Project project) {
		// TODO Auto-generated method stub
	}
	/**
	 * 更新页面-主体施工
	 * @param project
	 */
	public void updateUI_Constuct(Project project) {
		// TODO Auto-generated method stub
	}
}

