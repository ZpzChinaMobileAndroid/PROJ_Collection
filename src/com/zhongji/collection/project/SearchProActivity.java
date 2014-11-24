package com.zhongji.collection.project;

import java.util.ArrayList;
import java.util.List;

import net.tsz.afinal.annotation.view.ViewInject;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.loopj.android.http.RequestParams;
import com.zhongji.collection.adapter.ProjectAdapter;
import com.zhongji.collection.adapter.SearchAdapter;
import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.base.BaseSecondActivity;
import com.zhongji.collection.entity.Project;
import com.zhongji.collection.entity.ProjectListBean;
import com.zhongji.collection.network.HttpAPI;
import com.zhongji.collection.network.HttpRestClient;
import com.zhongji.collection.network.ResponseUtils;
import com.zhongji.collection.seach.SeachAdvancedActivity;
import com.zhongji.collection.seach.SeachMapActivity;
import com.zhongji.collection.seach.SeachVoiceActivity;
import com.zhongji.collection.util.DensityUtil;
import com.zhongji.collection.util.JsonUtils;
import com.zhongji.collection.util.PreferencesUtils;
import com.zhongji.collection.widget.KeyboardLayout;
import com.zhongji.collection.widget.KeyboardLayout.onKybdsChangeListener;
import com.zhongji.collection.widget.RTPullListView;
import com.zhongji.collection.widget.RTPullListView.OnRefreshListener;

/**
 * 搜索方式
 * 
 * @author admin
 * 
 */
public class SearchProActivity extends BaseSecondActivity implements
		OnClickListener {

	private int page = 0;
	private int size = 5;
	@ViewInject(id = R.id.bt_seach_advanced)
	private ImageButton bt_seach_advanced;
	@ViewInject(id = R.id.bt_seach_voice)
	private ImageButton bt_seach_voice;
	@ViewInject(id = R.id.bt_seach_map)
	private ImageButton bt_seach_map;
	@ViewInject(id = R.id.et_seach_result_start)
	private EditText et_seach_result_start;
	@ViewInject(id = R.id.lv_seachpro_message)
	private RTPullListView lv_seachpro_message;
	@ViewInject(id = R.id.layout_view)
	private LinearLayout layout_view;
	private KeyboardLayout mainView;
	
	private boolean isSave = true;
	private String type = "str";
	private ProjectAdapter adapter;
	private SearchAdapter adapterSearch;
	private ArrayList<Project> lists = new ArrayList<Project>();
	private List<String> strlists = new ArrayList<String>();
	private boolean key_enter = false;
	private InputMethodManager manager; // 隐藏软键盘
//	private int keystate = KeyboardLayout.KEYBOARD_STATE_HIDE;
	private String content="";
	private boolean isRes = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_searchpro);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void init() {
		// TODO Auto-generated method stub
		mainView = (KeyboardLayout) findViewById(R.id.keyboardLayout1);
//		PreferencesUtils.saveObject(SearchProActivity.this, PreferencesUtils.PREFERENCE_KEY_SEARCH, new ArrayList<String>());
		
		strlists = (List<String>) PreferencesUtils.getObject(SearchProActivity.this, PreferencesUtils.PREFERENCE_KEY_SEARCH);
		if(strlists==null){
			strlists = new ArrayList<String>();
		}
		keyboardlistener();
		manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		setTitleSeach(this);

		adapter = new ProjectAdapter(SearchProActivity.this);
		adapterSearch = new SearchAdapter(SearchProActivity.this);
		
		adapterSearch.setLists(strlists);
		lv_seachpro_message.setAdapter(adapterSearch);
		
		lv_seachpro_message.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				if("str".equals(type)){
					//文本信息
					page = 0;
					isSave = false;
					isRes = true;
					content = adapterSearch.getItem(arg2-1);
					lists.clear();
					adapter.setLists(lists);
					lv_seachpro_message.setAdapter(adapter);
					manager.hideSoftInputFromWindow(getCurrentFocus()
							 .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
					showProgressDialog();
					getSeachProject(content);
				}else if("res".equals(type)){
					//搜索结果
					Project pro = adapter.getItem(arg2-1);
					Intent intent = new Intent();
					intent.setClass(SearchProActivity.this, ProjectDetialActivity.class);
					intent.putExtra("url", pro.getUrl());
					intent.putExtra("type", "show");
					startActivity(intent);
				}
			}
		});

		lv_seachpro_message.setonRefreshListener(new OnRefreshListener() {
			
			@Override
			public void onRefresh() {
				// TODO Auto-generated method stub
				page = 0;
				getSeachProject(content);
			}
			
			@Override
			public void onLoadMore() {
				// TODO Auto-generated method stub
				page = page + 1;
				getSeachProject(content);
			}
		});
	}

	@Override
	public void onClick(View v) {
		// TODO 自动生成的方法存根
		super.onClick(v);
		if (v.getId() == R.id.bt_seach_advanced) {
			// 高级搜索
			isRes = true;
			Intent intent = new Intent(SearchProActivity.this,
					SeachAdvancedActivity.class);
			startActivity(intent);

		} else if (v.getId() == R.id.bt_seach_map) {
			// 地图搜索
			isRes = true;
			Intent intent = new Intent(SearchProActivity.this,
					SeachMapActivity.class);
			startActivity(intent);

		} else if (v.getId() == R.id.bt_seach_voice) {
			// 语音搜索
			isRes = true;
			Intent intent = new Intent(SearchProActivity.this,
					SeachVoiceActivity.class);
			startActivity(intent);
		} else if (v.getId() == R.id.tv_right) {
			// 取消搜索
			finish();
		}
	}

	/**
	 * 显示隐藏文本框
	 * 
	 * @param bool
	 */
	private void layoutchange(boolean bool) {
		if (bool) {
			System.out.println(strlists.toString());
			lv_seachpro_message.setRefreshable(false);
			lv_seachpro_message.removeFootView();
			type = "str";
			adapterSearch.setLists(strlists);
			lv_seachpro_message.setAdapter(adapterSearch);
			layout_view.setVisibility(View.VISIBLE);
			mainView.setBackgroundColor(getResources().getColor(R.color.white));
			lv_seachpro_message.setDividerHeight(DensityUtil.dip2px(this, 1));
			
		} else {
			if(isRes){
				isRes = false;
				page = 0;
				lv_seachpro_message.setRefreshable(true);
				lv_seachpro_message.removeFootView();
				type = "res";
//				lists.clear();
				adapter.setLists(lists);
				lv_seachpro_message.setAdapter(adapter);
				layout_view.setVisibility(View.INVISIBLE);
				mainView.setBackgroundColor(getResources().getColor(R.color.gray_proall_bg));
				lv_seachpro_message.setDividerHeight(DensityUtil.dip2px(this, 10));
			}else{
				finish();
			}
			
		}
	}

	/**
	 * 监听软键盘
	 */
	private void keyboardlistener() {
		mainView.setOnkbdStateListener(new onKybdsChangeListener() {

			@Override
			public void onKeyBoardStateChange(int state) {
				switch (state) {
				case KeyboardLayout.KEYBOARD_STATE_HIDE:
//					keystate = KeyboardLayout.KEYBOARD_STATE_HIDE;
					layoutchange(false);
//					 Toast.makeText(getApplicationContext(), "软键盘隐藏",
//					 Toast.LENGTH_SHORT).show();
					break;
				case KeyboardLayout.KEYBOARD_STATE_SHOW:
//					keystate = KeyboardLayout.KEYBOARD_STATE_SHOW;
					layoutchange(true);
//					 Toast.makeText(getApplicationContext(), "软键盘弹起",
//					 Toast.LENGTH_SHORT).show();
					break;
				}
			}
		});
	}

	/**
	 * 监听回车键
	 * 
	 * @param event
	 * @return
	 */
	public boolean dispatchKeyEvent(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER && !key_enter) {
			content = et_seach_result_start.getText().toString();
			if (!content.trim().equals("")) {
				/* 隐藏软键盘 */
				isRes = true;
				key_enter = true;
				manager.hideSoftInputFromWindow(getCurrentFocus()
				 .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
				isSave = true;
				showProgressDialog();
				getSeachProject(content);
			} else {
				showShortToast("请输入搜索信息");
			}
			return true;
		}
		return super.dispatchKeyEvent(event);
	}

	/**
	 * 显示隐藏软键盘
	 */
//	private Handler m_Handle = new Handler() {
//		public void handleMessage(Message msg) {
//			if (1 == msg.what) {
//				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//				if (keystate == KeyboardLayout.KEYBOARD_STATE_HIDE) {
//					imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
//				}
//			} else if (2 == msg.what) {
//				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//				if (imm.isActive()) {
//					if (keystate == KeyboardLayout.KEYBOARD_STATE_SHOW) {
//						imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,
//								InputMethodManager.HIDE_NOT_ALWAYS);
//					}
//				}
//			}
//		}
//	};

	/**
	 * 获取全部项目
	 */
	private void getSeachProject(String keyword) {
		RequestParams params = new RequestParams();
		params.put("keywords", keyword);
		params.put("startIndex", page + "");
		params.put("pageSize", size + "");
		HttpRestClient.get(SearchProActivity.this, HttpAPI.PROJECTS_ALL,
				HttpRestClient.TOKEN, params, new ResponseUtils(
						SearchProActivity.this) {

					@Override
					public void getResult(int httpCode, String result) {
						// TODO Auto-generated method stub
						
						lv_seachpro_message.onRefreshComplete();
						lv_seachpro_message.onLoadMoreComplete();
						
						if(isSave){
							isSave = false;
							if(!"".equals(content)){
								strlists.add(0, content);
								PreferencesUtils.saveObject(SearchProActivity.this, PreferencesUtils.PREFERENCE_KEY_SEARCH, strlists);
							}
						}
						dismissProgressDialog();
						et_seach_result_start.setText("");
						key_enter = false;
						if (httpCode == HttpAPI.HTTP_SUCCESS_CODE) {
							
							if(page == 0){
								lists.clear();
								adapter.setLists(lists);
								adapter.notifyDataSetChanged();
							}
							
							ProjectListBean bean = JSON.parseObject(
									JsonUtils.parseString(result),
									ProjectListBean.class);
							if (getData(bean)) {
								return;
							}
							List<Project> temp = bean.getData();
							if (temp != null && temp.size() > 0) {
								for (Project pro : temp) {
									lists.add(pro);
								}
							}

							adapter.setLists(lists);
							adapter.notifyDataSetChanged();

							if(lists.size()<Integer.parseInt(bean.getStatus().getTotalCount())){
								lv_seachpro_message.addFootView();
							}else{
								lv_seachpro_message.removeFootView();
							}

						} else {
							showNetShortToast(httpCode);
						}
					}
				});
	}
}
