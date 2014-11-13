package com.zhongji.collection.project;

import java.util.ArrayList;
import java.util.List;

import net.tsz.afinal.annotation.view.ViewInject;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

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
import com.zhongji.collection.util.JsonUtils;

/**
 * 全部项目
 * @author admin
 *
 */
public class AllProActivity extends BaseSecondActivity{
	
	private int page=0;
	private int size=10;
	private ProjectAdapter adapter;
	@ViewInject(id=R.id.listView1)
	private ListView listView;
	private List<Project> lists;
	@ViewInject(id=R.id.tv_title)
	private TextView tv_title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_allpro);
		
		tv_title.setOnClickListener(this);
	}
	
	@Override
	protected void init() {
		// TODO Auto-generated method stub
		
		setTitlebackgroud("输入搜索内容");
		setLeftBtn();
		
		lists = new ArrayList<Project>();
		adapter = new ProjectAdapter(AllProActivity.this);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Project pro = adapter.getItem(arg2);
				Intent intent = new Intent();
				intent.setClass(AllProActivity.this, ProjectDetialActivity.class);
				intent.putExtra("url", pro.getUrl());
				startActivity(intent);
			}
		});
		
		showProgressDialog();
		getProject();
	}
	
	@Override
	public void onClick(View v) {
		// TODO 自动生成的方法存根
		super.onClick(v);
		if(v.getId()==R.id.tv_title){
		//搜索
		Intent intent=new Intent(AllProActivity.this,SearchProActivity.class);
		startActivity(intent);
		}
	}
	
	/**
	 * 获取全部项目
	 */
	private void getProject() {
		RequestParams params = new RequestParams();
		params.put("startIndex", page+"");
		params.put("pageSize", size+"");
		params.put("keywords", "");
		HttpRestClient.get(AllProActivity.this, HttpAPI.PROJECTS_ALL, HttpRestClient.TOKEN,
				params,
				new ResponseUtils(AllProActivity.this) {
 
					@Override
					public void getResult(int httpCode, String result) {
						// TODO Auto-generated method stub
						dismissProgressDialog();
						if (httpCode == HttpAPI.HTTP_SUCCESS_CODE) {
							ProjectListBean bean = JSON.parseObject(JsonUtils.parseString(result),ProjectListBean.class);
							if (getData(bean)) {
								return; 
							}
							List<Project> temp = bean.getData();
							if(temp!=null && temp.size()>0){
								for(Project pro : temp){
									lists.add(pro);
								}
							}
							
							adapter.setLists(lists);
							adapter.notifyDataSetChanged();

						} else {
							showNetShortToast(httpCode);
						}
					}
				});
	}
}
