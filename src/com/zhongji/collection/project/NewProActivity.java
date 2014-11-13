package com.zhongji.collection.project;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import net.tsz.afinal.annotation.view.ViewInject;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.TextView;

import com.zhongji.collection.adapter.ProViewPager;
import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.base.BaseSecondActivity;
import com.zhongji.collection.entity.Project;
import com.zhongji.collection.project.view.AfforestView;
import com.zhongji.collection.project.view.BaseView;
import com.zhongji.collection.project.view.ConstuctView;
import com.zhongji.collection.project.view.DesignView;
import com.zhongji.collection.project.view.DrawStageView;
import com.zhongji.collection.project.view.ExplorationStageView;
import com.zhongji.collection.project.view.FitmentView;
import com.zhongji.collection.project.view.FoundationView;
import com.zhongji.collection.project.view.GridPhotoView;
import com.zhongji.collection.project.view.HorizonView;
import com.zhongji.collection.project.view.LandPlanView;
import com.zhongji.collection.project.view.ProCreateView;
import com.zhongji.collection.util.BitmapUtil;
import com.zhongji.collection.util.PreferencesUtils;
import com.zhongji.collection.widget.FixedScroller;
import com.zhongji.collection.widget.MyViewPager;

/**
 * 新建项目
 * 
 * @author admin
 * 
 */
public class NewProActivity extends BaseSecondActivity implements
		OnClickListener {

	@ViewInject(id = R.id.viewpager)
	private MyViewPager viewPager;
	private ProViewPager adapter;
	private List<View> viewList;
	private List<String> prostageList = new ArrayList<String>();
	private int currentItem = 0;

	@ViewInject(id = R.id.tv_stagename)
	private TextView tv_stagename;

	private LandPlanView view_planview; // 土地规划/拍卖
	private ProCreateView view_procreate; // 项目立项
	private ExplorationStageView view_explorationstage; // 地勘阶段
	private DesignView view_design; // 设计阶段
	private DrawStageView view_drawstage; // 出图阶段
	private HorizonView view_horizon; // 地平
	private FoundationView view_foundation; // 桩基基坑
	private ConstuctView view_constuct; // 主体施工
	private AfforestView view_afforesview; // 消防/景观绿化
	private FitmentView view_fitment; // 装修阶段
	private Project project;
	private int position=0;
	private String type = "edit";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_newpro);
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub
		
		project = (Project) getIntent().getSerializableExtra("project");
		position = getIntent().getIntExtra("position", 0);
		if(project==null){
			type = "add";
			String province = getIntent().getStringExtra("province");
			String city =  getIntent().getStringExtra("city");
			String district = getIntent().getStringExtra("district");
			project = new Project();
			project.setProvince(province);
			project.setCity(city);
			project.setDistrict(district);
		}
		
		setTitle("新建项目");
		setLeftBtn();
		setRightBtn(this);

		getProStageList();
		viewList = new ArrayList<View>();
		view_planview = new LandPlanView(NewProActivity.this, project);
		view_procreate = new ProCreateView(NewProActivity.this, project);
		view_explorationstage = new ExplorationStageView(NewProActivity.this,project);
		view_design = new DesignView(NewProActivity.this,project);
		view_drawstage = new DrawStageView(NewProActivity.this,project);
		view_horizon = new HorizonView(NewProActivity.this,project);
		view_foundation = new FoundationView(NewProActivity.this,project);
		view_constuct = new ConstuctView(NewProActivity.this,project);
		view_afforesview = new AfforestView(NewProActivity.this,project);
		view_fitment = new FitmentView(NewProActivity.this,project);

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

		setSpeed();

		updateTitle(0);
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		if (v.getId() == R.id.layout_prostage) {
			// 项目阶段
			Intent intent = new Intent();
			intent.setClass(NewProActivity.this, ProjectStageActivity.class);
			intent.putExtra("currentItem", currentItem);
			startActivityForResult(intent, 10);
		}else if (v.getId() == R.id.tv_right) {
			// 右
			System.out.println(project.toString());
			List<Project> plists = (List<Project>) PreferencesUtils.getObject(NewProActivity.this, PreferencesUtils.PREFERENCE_KEY);
			if(plists==null){
				plists = new ArrayList<Project>();
			}
			if(type.equals("add")){
				plists.add(0, project);
			}else {
				plists.set(position, project);
			}
			
			
			PreferencesUtils.saveObject(NewProActivity.this, PreferencesUtils.PREFERENCE_KEY, plists);
			finish();
//			FinalDb db = FinalDb.create(this);
//			db.save(project);
			
		}
	}

	/**
	 * 设置viewpager滑动速度
	 */
	private void setSpeed() {
		try {
			Field mScroller;
			mScroller = ViewPager.class.getDeclaredField("mScroller");
			mScroller.setAccessible(true);
			Interpolator sInterpolator = new AccelerateDecelerateInterpolator();
			FixedScroller scroller = new FixedScroller(viewPager.getContext(),
					sInterpolator);
			mScroller.set(viewPager, scroller);
		} catch (Exception e) {
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

	/**
	 * 更新标题文字
	 * 
	 * @param position
	 */
	private void updateTitle(int position) {
		String str = prostageList.get(position);
		if (!TextUtils.isEmpty(str)) {
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
			switch(position){
			case 0:
				view_planview.update();
				break;
			case 1:
				view_procreate.update();
				break;
			case 2:
				view_explorationstage.update();
				break;
			case 3:
				view_design.update();
				break;
			case 4:
				view_drawstage.update();
				break;
			case 5:
				view_horizon.update();
				break;
			case 6:
				view_foundation.update();
				break;
			case 7:
				view_constuct.update();
				break;
			case 8:
				view_afforesview.update();
				break;
			case 9:
				view_fitment.update();
				break;
			}
		}

		public void onPageScrollStateChanged(int arg0) {
			// switch (arg0) {
			// case 1:// 手势滑动
			// isScrolled = false;
			// break;
			// case 2:// 界面切换
			// isScrolled = true;
			// break;
			// case 0:// 滑动结束
			//
			// // 当前为最后一张，此时从右向左滑，则切换到第一张
			// if (viewPager.getCurrentItem() == viewPager.getAdapter()
			// .getCount() - 1 && !isScrolled) {
			// viewPager.setCurrentItem(0);
			// }
			// // 当前为第一张，此时从左向右滑，则切换到最后一张
			// else if (viewPager.getCurrentItem() == 0 && !isScrolled) {
			// viewPager
			// .setCurrentItem(viewPager.getAdapter().getCount() - 1);
			// }
			// break;
			// }
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
		} else if (requestCode == 10 && resultCode == 20) {
			String address = data.getStringExtra("address");
			double lat = data.getDoubleExtra("lat", 0);
			double lng = data.getDoubleExtra("lng", 0);
			project.setLandAddress(address);
			project.setLatitude(lat+"");
			project.setLongitude(lng+"");
			view_procreate.updateAddress();
		}
		
		if(currentItem == 0){
			//土地规划
			showBitmap(view_planview, view_planview.mGridView, requestCode, resultCode, data);
		}else if(currentItem == 2){
			//地勘阶段
			showBitmap(view_explorationstage,view_explorationstage.mGridView, requestCode, resultCode, data);
		}else if(currentItem == 5){
			//地平
			showBitmap(view_horizon, view_horizon.mGridView, requestCode, resultCode, data);
		}else if(currentItem == 6){
			//桩基基坑
			showBitmap(view_foundation, view_foundation.mGridView, requestCode, resultCode, data);
		}else if(currentItem == 7){
			//主体施工
			showBitmap(view_constuct, view_constuct.mGridView, requestCode, resultCode, data);
		}else if(currentItem == 8){
			//景观绿化
			showBitmap(view_afforesview, view_afforesview.mGridView, requestCode, resultCode, data);
		}else if(currentItem == 9){
			//装修阶段
			showBitmap(view_fitment, view_fitment.mGridView, requestCode, resultCode, data);
		}
		

	}

	private void showBitmap(BaseView baseview, GridPhotoView view, int requestCode, int resultCode, Intent data) {
		Bitmap bitmap = view.mPhotoUtils.onActivityResult(requestCode, resultCode, data);
		if(bitmap!=null){
			String imgstr = BitmapUtil.bitmapToBase64(bitmap);
			view.addString(imgstr);
			view.notifyDataSetChanged();
			baseview.setImages(baseview.imgsType, imgstr);
		}
	}

}
