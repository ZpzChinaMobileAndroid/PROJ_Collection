package com.zhongji.collection.project;

import java.util.List;

import net.tsz.afinal.annotation.view.ViewInject;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.base.BaseSecondActivity;
import com.zhongji.collection.entity.Project;
import com.zhongji.collection.entity.ProjectListBean;
import com.zhongji.collection.network.HttpAPI;
import com.zhongji.collection.network.HttpRestClient;
import com.zhongji.collection.network.ResponseUtils;
import com.zhongji.collection.project.detial.AfforestDetialView;
import com.zhongji.collection.project.detial.ConstuctDetialView;
import com.zhongji.collection.project.detial.DesignDetialView;
import com.zhongji.collection.project.detial.DrawStageDetialView;
import com.zhongji.collection.project.detial.ExplorationStageDetialView;
import com.zhongji.collection.project.detial.FitmentDetialView;
import com.zhongji.collection.project.detial.FoundationDetialView;
import com.zhongji.collection.project.detial.HorizonDetialView;
import com.zhongji.collection.project.detial.LandPlanDetialView;
import com.zhongji.collection.project.detial.ProCreateDetialView;
import com.zhongji.collection.util.JsonUtils;
import com.zhongji.collection.util.PreferencesUtils;

/**
 * 项目详情
 * @author admin
 *
 */
public class ProjectDetialActivity extends BaseSecondActivity implements OnClickListener{
	
	@ViewInject(id=R.id.tv_stagename)
	private TextView tv_stagename;
	@ViewInject(id=R.id.tv_stagechildname)
	private TextView tv_stagechildname;
	@ViewInject(id=R.id.layout_content)
	private LinearLayout layout_content;
	
	private int position = 0;
	private String type = "show";			//show,edit,publish
	private LandPlanDetialView view_landplan;		//土地规划
	private ProCreateDetialView view_procreate;		//项目立项
	private ExplorationStageDetialView view_explorationStage;	//地勘阶段
	private DesignDetialView view_design;			//设计阶段
	private DrawStageDetialView view_drawStage;		//出图阶段
	private HorizonDetialView view_horizon;			//地平阶段
	private FoundationDetialView view_foundation;	//桩基基坑
	private ConstuctDetialView view_constuct;		//主体施工
	private AfforestDetialView view_afforest;		//消防/景观绿化
	private FitmentDetialView view_fitment;			//装修阶段
	private Project project;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_projectdetial);
	}
	
	@Override
	protected void init() {
		// TODO Auto-generated method stub
		
		setTitle("项目详情");
		setLeftBtn();
		
		String url = getIntent().getStringExtra("url");
		Project project = (Project) getIntent().getSerializableExtra("project");
		position = getIntent().getIntExtra("position", 0);
		type = getIntent().getStringExtra("type");
		
		view_landplan = new LandPlanDetialView(ProjectDetialActivity.this, layout_content);
//		view_procreate = new ProCreateDetialView(ProjectDetialActivity.this, layout_content);
//		view_explorationStage = new ExplorationStageDetialView(ProjectDetialActivity.this, layout_content);
//		view_design = new DesignDetialView(ProjectDetialActivity.this, layout_content);
//		view_drawStage = new DrawStageDetialView(ProjectDetialActivity.this, layout_content);
//		view_horizon = new HorizonDetialView(ProjectDetialActivity.this, layout_content);
//		view_foundation = new FoundationDetialView(ProjectDetialActivity.this, layout_content);
//		view_constuct = new ConstuctDetialView(ProjectDetialActivity.this, layout_content);
//		view_afforest = new AfforestDetialView(ProjectDetialActivity.this, layout_content);
//		view_fitment = new FitmentDetialView(ProjectDetialActivity.this, layout_content);
		
		layout_content.addView(view_landplan.getView());
//		layout_content.addView(view_procreate.getView());
//		layout_content.addView(view_explorationStage.getView());
//		layout_content.addView(view_design.getView());
//		layout_content.addView(view_drawStage.getView());
//		layout_content.addView(view_horizon.getView());
//		layout_content.addView(view_foundation.getView());
//		layout_content.addView(view_constuct.getView());
//		layout_content.addView(view_afforest.getView());
//		layout_content.addView(view_fitment.getView()); 
		
		if("edit".equals(type)){
			//编辑
			updateUI(project);
		}else{
			//显示、发布
			showProgressDialog();
			getProject(url);
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == 0) {
			return;
		}

		if (requestCode == 10 && resultCode == 30) {
			setResult(40);
			project = (Project) data.getSerializableExtra("project");
			updateUI(project);
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		if(v.getId() == R.id.layout_prostage){
			//阶段
			Intent intent = new Intent();
			intent.setClass(ProjectDetialActivity.this, ProjectDetialStageActivity.class);
			startActivity(intent);
		}
	}
	
	
	/**
	 * 获取项目详情
	 */
	private void getProject(String url) {
		HttpRestClient.get(ProjectDetialActivity.this, url, new ResponseUtils(
				ProjectDetialActivity.this) {

			@Override
			public void getResult(int httpCode, String result) {
				// TODO Auto-generated method stub
				dismissProgressDialog();
				if (httpCode == HttpAPI.HTTP_SUCCESS_CODE) {
					ProjectListBean bean = JSON.parseObject(
							JsonUtils.parseString(result),
							ProjectListBean.class);
					if (getData(bean)) {
						return;
					}

					List<Project> temp = bean.getData();
					if (temp != null && temp.size() > 0) {
						project = temp.get(0);

						updateUI(project);
					}

				} else {
					showNetShortToast(httpCode);
				}
			}

		});
	}
	
	private void updateUI(final Project project) {
		if("edit".equals(type) || "publish".equals(type)){
			setRightBtnEdit(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					if("edit".equals(type) || checkProjectID()){
						Intent intent = new Intent();
						intent.setClass(ProjectDetialActivity.this, NewProActivity.class);
						intent.putExtra("position", position);
						intent.putExtra("project", project);
						intent.putExtra("type", type);
						startActivityForResult(intent, 10);
					}else{
						showShortToast("此项目已在本地保存项目中");
					}
					
				}
			});
		}
		
		view_landplan.updateUI(project);
//		view_procreate.updateUI(project);
//		view_explorationStage.updateUI(project);
//		view_design.updateUI(project);
//		view_drawStage.updateUI(project);
//		view_horizon.updateUI(project);
//		view_foundation.updateUI(project);
//		view_constuct.updateUI(project);
//		view_afforest.updateUI(project);
//		view_fitment.updateUI(project);
	}
	
	@SuppressWarnings("unchecked")
	public boolean checkProjectID(){
		List<Project> plists = (List<Project>) PreferencesUtils.getObject(ProjectDetialActivity.this, PreferencesUtils.PREFERENCE_KEY);
		if(plists!=null && plists.size()>0){
			for(Project pro : plists){
				String projectid = pro.getProjectID();
				if(projectid!=null && projectid.equals(project.getProjectID())){
					return false;
				}
			}
		}
		return true;
	}
	
}
