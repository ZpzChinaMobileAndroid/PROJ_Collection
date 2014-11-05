package com.zhongji.collection.project;

import java.util.ArrayList;
import java.util.List;

import net.tsz.afinal.annotation.view.ViewInject;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.zhongji.collection.adapter.ProViewPager;
import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.base.BaseSecondActivity;
import com.zhongji.collection.project.view.AfforestView;
import com.zhongji.collection.project.view.ConstuctView;
import com.zhongji.collection.project.view.DesignView;
import com.zhongji.collection.project.view.DrawStageView;
import com.zhongji.collection.project.view.ExplorationStageView;
import com.zhongji.collection.project.view.FitmentView;
import com.zhongji.collection.project.view.FoundationView;
import com.zhongji.collection.project.view.HorizonView;
import com.zhongji.collection.project.view.LandPlanView;
import com.zhongji.collection.project.view.ProCreateView;

/**
 * 新建项目
 * @author admin
 *
 */
public class NewProActivity extends BaseSecondActivity implements OnClickListener{
	
	@ViewInject(id=R.id.viewpager)
	private ViewPager viewPager;
	private ProViewPager adapter;
	private List<View> viewList;
	private List<String> prostageList = new ArrayList<String>();
	private int currentItem = 0;
	
	@ViewInject(id=R.id.tv_stagename)
	private TextView tv_stagename;
	
	private LandPlanView view_planview;						//土地规划/拍卖
	private ProCreateView view_procreate;					//项目立项
	private ExplorationStageView view_explorationstage;		//地勘阶段
	private DesignView view_design;							//设计阶段
	private DrawStageView view_drawstage;					//出图阶段
	private HorizonView view_horizon;						//地平
	private FoundationView view_foundation;					//桩基基坑
	private ConstuctView view_constuct;						//主体施工
	private AfforestView view_afforesview;					//消防/景观绿化
	private FitmentView view_fitment;						//装修阶段

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_newpro);
	}
	
	@Override
	protected void init() {
		// TODO Auto-generated method stub
		
		setTitle("新建项目");
		setLeftBtn();
		setRightBtn();
		 
		getProStageList(); 
		viewList = new ArrayList<View>();
		view_planview = new LandPlanView(NewProActivity.this);
		view_procreate = new ProCreateView(NewProActivity.this);
		view_explorationstage = new ExplorationStageView(NewProActivity.this);
		view_design = new DesignView(NewProActivity.this);
		view_drawstage = new DrawStageView(NewProActivity.this);
		view_horizon = new HorizonView(NewProActivity.this);
		view_foundation = new FoundationView(NewProActivity.this);
		view_constuct = new ConstuctView(NewProActivity.this);
		view_afforesview = new AfforestView(NewProActivity.this);
		view_fitment = new FitmentView(NewProActivity.this);
		
		
		viewList.add(view_planview.getView());
		viewList.add(view_procreate.getView());
		viewList.add(view_explorationstage.getView());
		viewList.add(view_design.getView());
		viewList.add(view_drawstage.getView());
		viewList.add(view_horizon.getView());
		viewList.add(view_foundation.getView());
		viewList.add(view_constuct.getView());
		viewList.add(view_afforesview.getView());
		viewList.add(view_fitment.getView());
		
		adapter = new ProViewPager(viewList);
		viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
        viewPager.setOnPageChangeListener(new MyPageChangeListener());
        
        updateTitle(0);
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		if(v.getId() == R.id.layout_prostage){
			//项目阶段
			Intent intent = new Intent();
			intent.setClass(NewProActivity.this, ProjectStageActivity.class);
			intent.putExtra("currentItem", currentItem);
			startActivityForResult(intent, 10);
		}
	}

	/**
	 * 获取各阶段名称
	 */
	private void getProStageList() {
		String[] items = getResources().getStringArray(R.array.prostage);
		for(String item : items){
			String[] its = item.split(",");
			for(int i=1;i<its.length;i++){
				prostageList.add(its[0]+","+its[i]);
			}
		}
	}
	
	/**
	 * 更新标题文字
	 * @param position
	 */
	private void updateTitle(int position) {
		String str = prostageList.get(position);
		if(!TextUtils.isEmpty(str)){
			String[] item = str.split(",");
			setTitle(item[1]);
			tv_stagename.setText(item[0]);
		}
	}
	
	/**
	 * 当ViewPager中页面的状态发生改变时调用
	 * 
	 * @author Administrator
	 * 
	 */
	private class MyPageChangeListener implements OnPageChangeListener {

		/**
		 * This method will be invoked when a new page becomes selected.
		 * position: Position index of the new selected page.
		 */
		public void onPageSelected(int position) {
			currentItem = position;
			updateTitle(position);
		}

		public void onPageScrollStateChanged(int arg0) {
//			switch (arg0) {
//			case 1:// 手势滑动
//				isScrolled = false;
//				break;
//			case 2:// 界面切换
//				isScrolled = true;
//				break;
//			case 0:// 滑动结束
//
//				// 当前为最后一张，此时从右向左滑，则切换到第一张
//				if (viewPager.getCurrentItem() == viewPager.getAdapter()
//						.getCount() - 1 && !isScrolled) {
//					viewPager.setCurrentItem(0);
//				}
//				// 当前为第一张，此时从左向右滑，则切换到最后一张
//				else if (viewPager.getCurrentItem() == 0 && !isScrolled) {
//					viewPager
//							.setCurrentItem(viewPager.getAdapter().getCount() - 1);
//				}
//				break;
//			}
		}

		public void onPageScrolled(int arg0, float arg1, int arg2) {

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
			int pos = data.getIntExtra("tag", 0);
			viewPager.setCurrentItem(pos);
		}
		
	}
}
