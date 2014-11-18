package com.zhongji.collection.seach;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.tsz.afinal.annotation.view.ViewInject;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.alibaba.fastjson.JSON;
import com.loopj.android.http.RequestParams;
import com.zhongji.collection.adapter.ProjectAdapter;
import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.base.BaseSecondActivity;
import com.zhongji.collection.entity.Project;
import com.zhongji.collection.entity.ProjectListBean;
import com.zhongji.collection.network.HttpAPI;
import com.zhongji.collection.network.HttpRestClient;
import com.zhongji.collection.network.ResponseUtils;
import com.zhongji.collection.project.ProjectDetialActivity;
import com.zhongji.collection.util.JsonUtils;
import com.zhongji.collection.widget.RTPullListView;
import com.zhongji.collection.widget.RTPullListView.OnRefreshListener;

/**
 * 搜索结果
 * 
 * @author Admin
 * 
 */

public class SeachResultActivity extends BaseSecondActivity implements
		OnClickListener {

	private ProjectAdapter adapter;
	@ViewInject(id = R.id.lv_seach_result)
	private RTPullListView lv_seach_result;
	private List<Project> lists;
	private int page = 0;
	private int size = 5;
	private Map<String, String> maps;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_seach_result);

	}

	@SuppressWarnings("unchecked")
	@Override
	protected void init() {
		// TODO 自动生成的方法存根
		setTitle("搜索结果");
		setLeftBtn();
		lists = new ArrayList<Project>();
		adapter = new ProjectAdapter(SeachResultActivity.this);
		maps =  (Map<String, String>) getIntent().getSerializableExtra("search");
		lv_seach_result.setAdapter(adapter);
		lv_seach_result.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Project pro = adapter.getItem(arg2-1);
				Intent intent = new Intent();
				intent.setClass(SeachResultActivity.this, ProjectDetialActivity.class);
				intent.putExtra("url", pro.getUrl());
				intent.putExtra("type", "show");
				startActivity(intent);
			}
		});
		
		lv_seach_result.setonRefreshListener(new OnRefreshListener() {
			
			@Override
			public void onRefresh() {
				// TODO Auto-generated method stub
				page = 0;
				getSeachProject(maps);
			}
			
			@Override
			public void onLoadMore() {
				// TODO Auto-generated method stub
				page = page + 1;
				getSeachProject(maps);
			}
		});

		showProgressDialog();
		getSeachProject(maps);

	}

	/**
	 * 获取全部项目
	 */
	private void getSeachProject(Map<String, String> maps) {
		RequestParams params = new RequestParams();
		params.put("keywords", maps.get("keyword"));
		params.put("company", maps.get("district"));
		params.put("district", maps.get("district"));
		params.put("province", maps.get("province"));
		params.put("projectStage", maps.get("projectStage"));
		params.put("category", maps.get("category"));
		params.put("startIndex", page + "");
		params.put("pageSize", size + "");
		HttpRestClient.get(SeachResultActivity.this, HttpAPI.PROJECTS_ALL,
				HttpRestClient.TOKEN, params, new ResponseUtils(
						SeachResultActivity.this) {

					@Override
					public void getResult(int httpCode, String result) {
						// TODO Auto-generated method stub
						dismissProgressDialog();
						lv_seach_result.onRefreshComplete();
						lv_seach_result.onLoadMoreComplete();
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
								lv_seach_result.addFootView();
							}else{
								lv_seach_result.removeFootView();
							}
						} else {
							showNetShortToast(httpCode);
						}
					}
				});
	}
}
