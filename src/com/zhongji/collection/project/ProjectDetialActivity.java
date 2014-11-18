package com.zhongji.collection.project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.tsz.afinal.annotation.view.ViewInject;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.base.BaseSecondActivity;
import com.zhongji.collection.entity.Contacts;
import com.zhongji.collection.entity.ContactsListBean;
import com.zhongji.collection.entity.Images;
import com.zhongji.collection.entity.ImagesListBean;
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
import com.zhongji.collection.widget.XScrollView;
import com.zhongji.collection.widget.XScrollView.OnScrollStateChanged;

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
	@ViewInject(id=R.id.scrollView1)
	private XScrollView scrollView;
	
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
	private boolean isShow = true;
	private Map<Integer,boolean[]> maps = new LinkedHashMap<Integer, boolean[]>();
	private Map<Integer,Integer> mapsStage = new LinkedHashMap<Integer, Integer>();
	private List<String> prostageList = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_projectdetial);
	}
	
	@Override
	protected void init() {
		// TODO Auto-generated method stub
		
		getProStageList();
		setTitle("项目详情");
		setLeftBtn();
		initMenu();
		
		String url = getIntent().getStringExtra("url");
		project = (Project) getIntent().getSerializableExtra("project");
		position = getIntent().getIntExtra("position", 0);
		type = getIntent().getStringExtra("type");
		
		view_landplan = new LandPlanDetialView(ProjectDetialActivity.this, layout_content);
		view_procreate = new ProCreateDetialView(ProjectDetialActivity.this, layout_content);
//		view_explorationStage = new ExplorationStageDetialView(ProjectDetialActivity.this, layout_content);
//		view_design = new DesignDetialView(ProjectDetialActivity.this, layout_content);
//		view_drawStage = new DrawStageDetialView(ProjectDetialActivity.this, layout_content);
		
//		view_horizon = new HorizonDetialView(ProjectDetialActivity.this, layout_content);
//		view_foundation = new FoundationDetialView(ProjectDetialActivity.this, layout_content);
//		view_constuct = new ConstuctDetialView(ProjectDetialActivity.this, layout_content);
//		view_afforest = new AfforestDetialView(ProjectDetialActivity.this, layout_content);
		
//		view_fitment = new FitmentDetialView(ProjectDetialActivity.this, layout_content);
		
		layout_content.addView(view_landplan.getView());
		layout_content.addView(view_procreate.getView());
		
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
		
		scrollView.setOnScrollStateChanged(new OnScrollStateChanged() {
			
			@Override
			public void ScrollTop() {
				// TODO Auto-generated method stub
				Log.e("2", "top....");
			}
			
			@Override
			public void ScrollBottom() {
				// TODO Auto-generated method stub
				if(isShow){
					isShow = false;
					new Handler().postDelayed(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							switch(layout_content.getChildCount()){
							case 2:
								initial2_4();
								isShow = true;
								break;
							case 5:
								initial5_8();
								isShow = true;
								break;
							case 9:
								initial_9();
								isShow = true;
								break;
							}
						}
					}, 1500);
				}
			}

			@Override
			public void Scroll() {
				// TODO Auto-generated method stub
				updateText();
			}

		});
		
	}
	
	/**
	 * 更新标签显示
	 */
	private void updateText() {
		setMapsStage();
		int height = 0;
		int scrollY = scrollView.getScrollY();
		int count = mapsStage.keySet().toArray().length;
		System.out.println("--"+count);
		for(int i=0;i<count;i++){
			height = height + mapsStage.get(i);
			System.out.println("ab"+height);
			System.out.println("aa"+(scrollY + scrollView.getHeight()));
			if((scrollY + scrollView.getHeight()) <= height){
				String str = prostageList.get(i);
				String[] items = str.split(",");
				tv_stagename.setText(items[0]);
				tv_stagechildname.setText(items[1]);
				return;
			}
		}
	}
	
	public void setMapsStage(){
		for(int i=0;i<layout_content.getChildCount();i++){
			View view = layout_content.getChildAt(i);
			view.measure(0, 0);
			mapsStage.put(i, view.getHeight());
		}
	}
	
	/**
	 * 获取各阶段名称
	 */
	private void getProStageList() {
		String[] items = getResources().getStringArray(R.array.prostage);
		for (String item : items) {
			String[] its = item.split(",");
			for (int i = 1; i < its.length; i++) {
				prostageList.add(its[0] + "," + its[i]);
			}
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == 0) {
			return;
		}

		if (requestCode == 10 && resultCode == 10) {
			int tag = data.getIntExtra("tag", -1);
			if(tag!=-1){
				int totalHeight = 0;
				int count = layout_content.getChildCount();
				if(tag<count){
					for(int i=0;i<tag;i++){
						View view = layout_content.getChildAt(i);
						totalHeight = totalHeight + view.getMeasuredHeight();
					}
					scrollToBottom(scrollView, totalHeight);
				}else{
					initial2_4();
					if(tag>4){
						initial5_8();
						if(tag>8){
							initial_9();
						}
					}
					System.out.println("-->"+tag);
					System.out.println("--"+layout_content.getChildCount());
					for(int i=0;i<tag;i++){
						View view = layout_content.getChildAt(i);
						view.measure(0, 0);
						totalHeight = totalHeight + view.getMeasuredHeight();
					}
					scrollToBottom(scrollView, totalHeight);
				}
				
//				updateText();
			}
		}
		
		if (requestCode == 10 && resultCode == 30) {
			setResult(40);
			project = (Project) data.getSerializableExtra("project");
			updateUI(project);
		}
	}

	private void initial_9() {
		view_afforest.removeFooter();
		view_fitment = new FitmentDetialView(ProjectDetialActivity.this, layout_content);
		layout_content.addView(view_fitment.getView()); 
		view_fitment.updateUI(ProjectDetialActivity.this.project);
	}

	private void initial5_8() {
		view_drawStage.removeFooter();
		view_horizon = new HorizonDetialView(ProjectDetialActivity.this, layout_content);
		view_foundation = new FoundationDetialView(ProjectDetialActivity.this, layout_content);
		view_constuct = new ConstuctDetialView(ProjectDetialActivity.this, layout_content);
		view_afforest = new AfforestDetialView(ProjectDetialActivity.this, layout_content);
		layout_content.addView(view_horizon.getView());
		layout_content.addView(view_foundation.getView());
		layout_content.addView(view_constuct.getView());
		layout_content.addView(view_afforest.getView());
		view_foundation.updateUI(ProjectDetialActivity.this.project);
		view_constuct.updateUI(ProjectDetialActivity.this.project);
		view_afforest.updateUI(ProjectDetialActivity.this.project);
	}

	private void initial2_4() {
		view_procreate.removeFooter();
		view_explorationStage = new ExplorationStageDetialView(ProjectDetialActivity.this, layout_content);
		view_design = new DesignDetialView(ProjectDetialActivity.this, layout_content);
		view_drawStage = new DrawStageDetialView(ProjectDetialActivity.this, layout_content);
		layout_content.addView(view_explorationStage.getView());
		layout_content.addView(view_design.getView());
		layout_content.addView(view_drawStage.getView());
		view_explorationStage.updateUI(ProjectDetialActivity.this.project);
		view_design.updateUI(ProjectDetialActivity.this.project);
		view_drawStage.updateUI(ProjectDetialActivity.this.project);
	}
	
	public void scrollToBottom(final View scroll, final int totalHeight) {
		Handler mHandler = new Handler();

		mHandler.post(new Runnable() {
			public void run() {
				if (scroll == null) {
					return;
				}
				
				System.out.println(totalHeight +"---" +scroll.getMeasuredHeight());
				int offset = 0;
				if(totalHeight != 0){
					offset = totalHeight ;
				}

				scroll.scrollTo(0, offset);
				
				updateText();
			}
		});
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		if(v.getId() == R.id.layout_prostage){
			//阶段
			Intent intent = new Intent();
			intent.setClass(ProjectDetialActivity.this, ProjectDetialStageActivity.class);
			initialMap();
			intent.putExtra("maps", (Serializable)maps);
			startActivityForResult(intent, 10);
		}
	}

	/**
	 * 图片、联系人
	 */
	private void initialMap() {
		for(int i=0;i<10;i++){
			maps.put(i, new boolean[2]);
		}
		List<ContactsListBean> blists = project.getBaseContacts();
		if(blists!=null && blists.size()>0){
			ContactsListBean cbean = blists.get(0);
			List<Contacts> clists = cbean.getData();
			if(clists!=null && clists.size()>0){
				for(Contacts contact : clists){
					if("auctionUnitContacts".equals(contact.getCategory()) && !maps.get(0)[1]){
						maps.get(0)[1] = true;
					}else if("ownerUnitContacts".equals(contact.getCategory()) && !maps.get(1)[1]){
						maps.get(1)[1] = true;
					}else if("explorationUnitContacts".equals(contact.getCategory()) && !maps.get(2)[1]){
						maps.get(2)[1] = true;
					}else if("designInstituteContacts".equals(contact.getCategory()) && !maps.get(3)[1]){
						maps.get(3)[1] = true;
					}else if("ownerUnitContacts".equals(contact.getCategory()) && !maps.get(4)[1]){
						maps.get(4)[1] = true;
					}else if("contractorUnitContacts".equals(contact.getCategory()) && !maps.get(5)[1]){
						maps.get(5)[1] = true;
					}else if("pileFoundationUnitContacts".equals(contact.getCategory()) && !maps.get(6)[1]){
						maps.get(6)[1] = true;
					}
					
				}
			}
		}
		
		List<ImagesListBean> ilists = project.getProjectImgs();
		if(ilists!=null && ilists.size()>0){
			ImagesListBean ibean = ilists.get(0);
			List<Images> iilists = ibean.getData();
			if(iilists!=null && iilists.size()>0){
				for(Images img : iilists){
					if("plan".equals(img.getCategory()) && !maps.get(0)[0]){
						maps.get(0)[0] = true;
					}else if("exploration".equals(img.getCategory()) && !maps.get(2)[0]){
						maps.get(2)[0] = true;
					}else if("horizon".equals(img.getCategory()) && !maps.get(5)[0]){
						maps.get(5)[0] = true;
					}else if("pileFoundation".equals(img.getCategory()) && !maps.get(6)[0]){
						maps.get(6)[0] = true;
					}else if("mainPart".equals(img.getCategory()) && !maps.get(7)[0]){
						maps.get(7)[0] = true;
					}else if("fireControl".equals(img.getCategory()) && !maps.get(8)[0]){
						maps.get(8)[0] = true;
					}else if("electroweak".equals(img.getCategory()) && !maps.get(9)[0]){
						maps.get(8)[0] = true;
					}
				}
			}
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
		
		int count = layout_content.getChildCount();
		
		view_landplan.updateUI(project);
		view_procreate.updateUI(project);
		if(count>2){
			view_explorationStage.updateUI(project);
			view_design.updateUI(project);
			view_drawStage.updateUI(project);
			if(count>5){
				view_horizon.updateUI(project);
				view_foundation.updateUI(project);
				view_constuct.updateUI(project);
				view_afforest.updateUI(project);
				if(count>9){
					view_fitment.updateUI(project);
				}
			}
		}

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
