package com.zhongji.collection.project.map;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMapClickListener;
import com.baidu.mapapi.map.BaiduMap.OnMarkerClickListener;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.base.BaseSecondActivity;
import com.zhongji.collection.util.DensityUtil;

/**
 * 地图搜索
 * 
 * @author admin
 * 
 */
public class MapActivity extends BaseSecondActivity implements OnGetGeoCoderResultListener{

	private GeoCoder mSearch = null; // 搜索模块，也可去掉地图模块独立使用
	private BaiduMap mBaiduMap = null;
	private MapView mMapView = null;
	private MarkerOptions mo = new MarkerOptions();
	private String city = "";
	private String address = "上海市虹口区广粤路437号";
	private double lat=0;
	private double lng=0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub
		address = getIntent().getStringExtra("address");
		if(TextUtils.isEmpty(address)){
			address = "上海市虹口区广粤路437号";
		}
		
		setTitle("地图搜索");
		setLeftBtn();
		setRightBtn(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.putExtra("lat", lat);
				intent.putExtra("lng", lng);
				intent.putExtra("address", address);
				setResult(20, intent);
				finish();
			}
		});
		
		// 地图初始化
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
				
				mSearch.reverseGeoCode(new ReverseGeoCodeOption().location(arg0));
			}
		});
		mBaiduMap.setOnMarkerClickListener(new OnMarkerClickListener() {
			
			@Override
			public boolean onMarkerClick(Marker arg0) {
				// TODO Auto-generated method stub
				
				Button btn = new Button(MapActivity.this);
				btn.setBackgroundResource(R.drawable.bg_mapicon);
				btn.setText(city + address);
				LatLng ll = arg0.getPosition();
				InfoWindow mInfoWindow = new InfoWindow(BitmapDescriptorFactory.fromView(btn), ll, -97, null);
				mBaiduMap.showInfoWindow(mInfoWindow);
		            
//				LatLng ll = arg0.getPosition();
//				Point p = mBaiduMap.getProjection().toScreenLocation(ll);
//				p.y -= 10;	
//				LatLng llInfo = mBaiduMap.getProjection().fromScreenLocation(p);
//				InfoWindow mInfoWindow = new InfoWindow(tv, llInfo, 0);
//				mBaiduMap.showInfoWindow(mInfoWindow);
				return false;
			}
		});

		// 初始化搜索模块，注册事件监听
		mSearch = GeoCoder.newInstance();
		mSearch.setOnGetGeoCodeResultListener(this);
		
		mSearch.geocode(new GeoCodeOption().city(city).address(address));//437号//439弄18-58号
//		mSearch.reverseGeoCode(new ReverseGeoCodeOption().location(ptCenter));
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
		mMapView.onDestroy();
		mSearch.destroy();
		super.onDestroy();
	}

	@Override
	public void onGetGeoCodeResult(GeoCodeResult result) {
		if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
			showShortToast("抱歉，未能找到结果");
			return;
		}
		mBaiduMap.clear();
		mo.position(result.getLocation());
		TextView parent = createTextView();
		mo.icon(BitmapDescriptorFactory.fromView(parent));
		mBaiduMap.addOverlay(mo);
		mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(result
				.getLocation()));
//		String strInfo = String.format("纬度：%f 经度：%f",
//				result.getLocation().latitude, result.getLocation().longitude);
		address = result.getAddress();
		lat = result.getLocation().latitude;
		lng = result.getLocation().longitude;
//		showShortToast(strInfo);
	}

	@Override
	public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
		if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
			Toast.makeText(MapActivity.this, "抱歉，未能找到结果", Toast.LENGTH_LONG)
					.show();
			return;
		}
		mBaiduMap.clear();
		mo.position(result.getLocation());
		TextView parent = createTextView();
		mo.icon(BitmapDescriptorFactory.fromView(parent));
		mBaiduMap.addOverlay(mo);
		mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(result
				.getLocation()));
		address = result.getAddress();
		lat = result.getLocation().latitude;
		lng = result.getLocation().longitude;
//		showShortToast(result.getAddress());

	}
	
	private TextView createTextView() {
		TextView parent = new TextView(MapActivity.this);
		parent.setPadding(0, 0, 0, DensityUtil.dip2px(MapActivity.this, 15));
		parent.setTextColor(Color.WHITE);
		parent.setGravity(Gravity.CENTER);
		parent.setBackgroundResource(R.drawable.ic_mapicon);
		parent.setText("A");
		return parent;
	}
}
