package com.zhongji.collection.seach;

import java.util.ArrayList;
import java.util.List;

import net.tsz.afinal.annotation.view.ViewInject;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMapClickListener;
import com.baidu.mapapi.map.BaiduMap.OnMarkerClickListener;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.PolygonOptions;
import com.baidu.mapapi.map.Stroke;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.DistanceUtil;
import com.loopj.android.http.RequestParams;
import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.base.BaseSecondActivity;
import com.zhongji.collection.entity.Project;
import com.zhongji.collection.entity.ProjectListBean;
import com.zhongji.collection.network.HttpAPI;
import com.zhongji.collection.network.HttpRestClient;
import com.zhongji.collection.network.ResponseUtils;
import com.zhongji.collection.project.ProjectDetialActivity;
import com.zhongji.collection.util.DensityUtil;
import com.zhongji.collection.util.JsonUtils;
import com.zhongji.collection.widget.MyView;

public class SeachMapActivity extends BaseSecondActivity implements OnClickListener {

	private LocationClient mLocClient = null;
	public MyLocationListenner myListener = new MyLocationListenner();
	private MapView mMapView;
	private BaiduMap mBaiduMap;
	@ViewInject(id=R.id.myview)
	private MyView myview;
	@ViewInject(id=R.id.tv_icon)
	private TextView tv_icon;
	@ViewInject(id=R.id.layout_content)
	private RelativeLayout layout_content;
	@ViewInject(id=R.id.tv_proname)
	private TextView tv_proname;
	@ViewInject(id=R.id.tv_investmentmoney)
	private TextView tv_investmentmoney;
	@ViewInject(id=R.id.tv_buildarea)
	private TextView tv_buildarea;
	@ViewInject(id=R.id.tv_starttime)
	private TextView tv_starttime;
	@ViewInject(id=R.id.tv_endtime)
	private TextView tv_endtime;
	@ViewInject(id=R.id.tv_district)
	private TextView tv_district;
	@ViewInject(id=R.id.tv_address)
	private TextView tv_address;
	private List<Project> prolists = new ArrayList<Project>();
	private List<LatLng> lists = new ArrayList<LatLng>();
	private LatLng startLL;
	private double distance = 0;
	private int count=0;
	private int pos = 0;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map_draw);
	}
	
	@Override
	protected void init() {
		// TODO 自动生成的方法存根
		
		setTitle("地图搜索");
		setLeftBtn();
		
		// 初始化地图
		mMapView = (MapView) findViewById(R.id.bmapView);
		mBaiduMap = mMapView.getMap();
		mBaiduMap.setOnMapClickListener(new OnMapClickListener() {
			
			@Override
			public boolean onMapPoiClick(MapPoi arg0) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public void onMapClick(LatLng arg0) {
				// TODO Auto-generated method stub
				mBaiduMap.hideInfoWindow();
				layout_content.setVisibility(View.GONE);
			}
		});
		mBaiduMap.setOnMarkerClickListener(new OnMarkerClickListener() {
			
			@Override
			public boolean onMarkerClick(Marker arg0) {
				// TODO Auto-generated method stub
				layout_content.setVisibility(View.VISIBLE);
				Bundle bb = arg0.getExtraInfo();
				pos = bb.getInt("pos");
				int count = bb.getInt("count");
				Project pro = prolists.get(pos);
				tv_proname.setText(pro.getProjectName());
				tv_investmentmoney.setText(pro.getInvestment());
				tv_buildarea.setText(pro.getAreaOfStructure());
				tv_starttime.setText(pro.getExpectedStartTime());
				tv_endtime.setText(pro.getExpectedFinishTime());
				tv_district.setText(pro.getDistrict());
				tv_address.setText(pro.getLandAddress());
				setText(count, tv_icon);
				return false;
			}
		});
		layout_content.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Project pro = prolists.get(pos);
				Intent intent = new Intent();
				intent.setClass(SeachMapActivity.this, ProjectDetialActivity.class);
				intent.putExtra("url", pro.getUrl());
				intent.putExtra("type", "show");
				startActivity(intent);
			}
		});
		myview.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View arg0, MotionEvent event) {
				// TODO Auto-generated method stub
				int x = (int) event.getX();
				int y = (int) event.getY();
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					count=0;
					startLL = mBaiduMap.getProjection().fromScreenLocation(new Point(x, y));
					break;
				case MotionEvent.ACTION_MOVE:
					Point p = new Point(x, y);
					LatLng llInfo = mBaiduMap.getProjection().fromScreenLocation(p);
					double dis = DistanceUtil.getDistance(startLL, llInfo);
					distance = Math.max(distance, dis);
					lists.add(llInfo);
					break;
				case MotionEvent.ACTION_UP:
					if(lists.size()>3){
						myview.setVisibility(View.GONE);
						OverlayOptions ooPolygon = new PolygonOptions().points(lists)
						.stroke(new Stroke(6, 0xAAFF0000)).fillColor(0xAA8DEEEE);
							mBaiduMap.addOverlay(ooPolygon);
							
						//调接口
//						showProgressDialog();
						getSeachProject(startLL.latitude+"", startLL.longitude+"", distance+"");
//							Project pro = new Project();
//							pro.setLatitude("31.290965");
//							pro.setLongitude("121.474916");
//							prolists.add(pro);
//							Project pro2 = new Project();
//							pro2.setLatitude("32.390965");
//							pro2.setLongitude("121.574916");
//							prolists.add(pro2);
//							Project pro3 = new Project();
//							pro3.setLatitude("31.490965");
//							pro3.setLongitude("121.674916");
//							prolists.add(pro3);
//							Project pro4 = new Project();
//							pro4.setLatitude("31.590965");
//							pro4.setLongitude("121.774916");
//							prolists.add(pro4);
//							Project pro5 = new Project();
//							pro5.setLatitude("31.690965");
//							pro5.setLongitude("121.874916");
//							prolists.add(pro5);
						
					}
					
					break;
				}
				
				return false;
			}

		});
		
		
//		// 设定中心点坐标
//
//		LatLng cenpt = new LatLng(31.297253, 121.473012);
//		// 定义地图状态
//		MapStatus mMapStatus = new MapStatus.Builder().target(cenpt).zoom(18)
//				.build();
//		// 定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
//
//		MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory
//				.newMapStatus(mMapStatus);
//		// 改变地图状态
//		mBaiduMap.setMapStatus(mMapStatusUpdate);
		
		// 开启定位图层
		mBaiduMap.setMyLocationEnabled(true);
		// 定位初始化
		mLocClient = new LocationClient(this);
		mLocClient.registerLocationListener(myListener);
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true);// 打开gps
		option.setCoorType("bd09ll"); // 设置坐标类型
		option.setScanSpan(1000);
		option.setAddrType("all");
		mLocClient.setLocOption(option);
		mLocClient.start();
	}
	
	/**
	 * 定位SDK监听函数
	 */
	public class MyLocationListenner implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			// map view 销毁后不在处理新接收的位置
			if (location == null || mMapView == null){
				showShortToast("抱歉，定位失败");
				return;
			}
			//定位icon
//			MyLocationData locData = new MyLocationData.Builder()
//					.accuracy(location.getRadius())
//					// 此处设置开发者获取到的方向信息，顺时针0-360
//					.direction(100).latitude(location.getLatitude())
//					.longitude(location.getLongitude()).build();
//			
//			mBaiduMap.setMyLocationData(locData);
//			if (isFirstLoc) {
//				isFirstLoc = false;
//				LatLng ll = new LatLng(location.getLatitude(),
//						location.getLongitude());
//				MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
//				mBaiduMap.animateMapStatus(u);
//			}
			
			//默认中心位置
			LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
			MapStatus mMapStatus = new MapStatus.Builder()
	        .target(ll)
	        .zoom(16)
	        .build();
			MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
			mBaiduMap.setMapStatus(mMapStatusUpdate);
			
			mLocClient.stop();
			mLocClient.unRegisterLocationListener(myListener);
			mBaiduMap.setMyLocationEnabled(false);
			
		}

		public void onReceivePoi(BDLocation poiLocation) {
		}
	}
	
	private TextView createTextView() {
		count = count + 1;
		System.out.println("count-->"+count);
		TextView parent = new TextView(SeachMapActivity.this);
		parent.setPadding(0, 0, 0, DensityUtil.dip2px(SeachMapActivity.this, 15));
		parent.setTextColor(Color.WHITE);
		parent.setGravity(Gravity.CENTER);
		parent.setBackgroundResource(R.drawable.ic_mapicon);
		setText(count, parent);
		
		return parent;
	}

	private void setText(int pos, TextView parent) {
		switch(pos){
		case 1:
			parent.setText("A");
			break;
		case 2:
			parent.setText("B");
			break;
		case 3:
			parent.setText("C");
			break;
		case 4:
			parent.setText("D");
			break;
		case 5:
			parent.setText("E");
			break;
		case 6:
			parent.setText("F");
			break;
		}
	}
	
	public boolean contains(float latitude, float longitude) {
		int mPolySize = lists.size();
		boolean result = false;
		for (int i = 0, j = mPolySize - 1; i < mPolySize; j = i++) {
			LatLng lli = lists.get(i);
			LatLng llj = lists.get(j);
			if ((lli.longitude < longitude && llj.longitude >= longitude)
					|| (llj.longitude < longitude && lli.longitude >= longitude)) {
				if (lli.latitude + (longitude - lli.longitude) / (llj.longitude - lli.longitude)
						* (llj.latitude - lli.latitude) < latitude) {
					result = !result;
				}
			}
		}
		return result;
	}
	
	@Override
	public void onClick(View v) {
		// TODO 自动生成的方法存根
		super.onClick(v);
		if(v.getId() == R.id.btn_draw){
			//画图
			layout_content.setVisibility(View.GONE);
			lists.clear();
			clearClick();
			myview.setVisibility(View.VISIBLE);
		}
	}
	
	public void clearClick() {
		// 清除所有图层
		mMapView.getMap().clear();
	}
	
	/**
	 * 获取全部项目
	 * projects/token/mapSearch&latitude={latitude}&longitude={&longitude=}&radius={radius}
	 */
	private void getSeachProject(String latitude, String longitude, String radius) {
		RequestParams params = new RequestParams();
//		params.put("latitude", latitude);
//		params.put("longitude", longitude);
//		params.put("radius", radius);
		HttpRestClient.get(SeachMapActivity.this, HttpAPI.PROJECTS_ALL,
				HttpRestClient.TOKEN + "/mapSearch&latitude="+latitude+"&longitude="+longitude+"&radius="+radius , params, new ResponseUtils(
						SeachMapActivity.this) {

					@Override
					public void getResult(int httpCode, String result) {
						// TODO Auto-generated method stub
						dismissProgressDialog();
						if (httpCode == HttpAPI.HTTP_SUCCESS_CODE) {
							ProjectListBean bean = JSON.parseObject(JsonUtils.parseString(result),ProjectListBean.class);
							if (getData(bean)) {
								return; 
							}
							prolists.clear();
							
							List<Project> temp = bean.getData();
							if(temp!=null && temp.size()>0){
								for(Project pro : temp){
									prolists.add(pro);
								}
							}
							//计算
							calLL_addView();
							
							
						} else {
							showNetShortToast(httpCode);
						}
					}
				});
	}
	
	private void calLL_addView() {
		for(int i=0;i<prolists.size();i++){
			Project pp = prolists.get(i);
			boolean b = contains(Float.parseFloat(pp.getLatitude()), Float.parseFloat(pp.getLongitude()));
			System.out.println(b+"");
			if(b){
				MarkerOptions mo = new MarkerOptions();
				mo.position(new LatLng(Float.parseFloat(pp.getLatitude()), Float.parseFloat(pp.getLongitude())));
				TextView parent = createTextView();
				mo.icon(BitmapDescriptorFactory.fromView(parent));
				Bundle bb = new Bundle();
				bb.putInt("pos", i);
				bb.putInt("count", count);
				mo.extraInfo(bb);
				mBaiduMap.addOverlay(mo);
			}
			
		}
		
		if(count == 0){
			showShortToast("没有数据");
		}
	}
	
	@Override
	protected void onPause() {
		mMapView.onPause();
		super.onPause();
	}

	@Override
	protected void onResume() {
		mMapView.onResume();
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		// 退出时销毁定位
		mLocClient.stop();
		// 关闭定位图层
		mBaiduMap.setMyLocationEnabled(false);
		mMapView.onDestroy();
		super.onDestroy();
	}
}
