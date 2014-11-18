package com.zhongji.collection.project;

import java.util.ArrayList;
import java.util.List;

import net.tsz.afinal.annotation.view.ViewInject;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

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
import com.zhongji.collection.widget.RTPullListView;
import com.zhongji.collection.widget.RTPullListView.OnRefreshListener;

/**
 * 全部项目
 * @author admin
 *
 */
public class AllProActivity extends BaseSecondActivity{
	
	private int page=0;
	private int size=5;
	private ProjectAdapter adapter;
	@ViewInject(id=R.id.listView1)
	private RTPullListView listView;
	private List<Project> lists;
	@ViewInject(id=R.id.tv_title)
	private TextView tv_title;
	private TextView tv_count = null;

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
		
		setTitlebackgroud("请输入搜索内容",this);
		setLeftBtn();
		initMenu();
		
		lists = new ArrayList<Project>();
		adapter = new ProjectAdapter(AllProActivity.this);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				if(arg2-2<0){
					return;
				}
				Project pro = adapter.getItem(arg2-2);
				Intent intent = new Intent();
				intent.setClass(AllProActivity.this, ProjectDetialActivity.class);
				intent.putExtra("url", pro.getUrl());
				intent.putExtra("type", "show");
				startActivity(intent);
			}
		});
		
		listView.setonRefreshListener(new OnRefreshListener() {
			
			@Override
			public void onRefresh() {
				// TODO Auto-generated method stub
				page=0;
				getProject();
			}
			
			@Override
			public void onLoadMore() {
				// TODO Auto-generated method stub
				page=page+1;
				getProject();
			}
		});
		
		showProgressDialog();
		getProject();
	}
	
	@Override
	public void onClick(View v) {
		// TODO 自动生成的方法存根
		super.onClick(v);
		if(v.getId()==R.id.layout_title || v.getId() == R.id.tv_title){
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
						listView.onRefreshComplete();
						listView.onLoadMoreComplete();
						if (httpCode == HttpAPI.HTTP_SUCCESS_CODE) {
							ProjectListBean bean = JSON.parseObject(JsonUtils.parseString(result),ProjectListBean.class);
							if (getData(bean)) {
								return; 
							}
							
							if(page == 0){
								lists.clear();
								adapter.setLists(lists);
								adapter.notifyDataSetChanged();
							}
							
							List<Project> temp = bean.getData();
							if(temp!=null && temp.size()>0){
								for(Project pro : temp){
									lists.add(pro);
								}
							}
							
							if(tv_count==null){
								tv_count = new TextView(AllProActivity.this);
								tv_count.setGravity(Gravity.CENTER);
								tv_count.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.WRAP_CONTENT));
								tv_count.setTextColor(getResources().getColor(R.color.gray_pro_txt));
								listView.addHeaderView(tv_count);
							}
							
							tv_count.setText("共计"+lists.size()+"条");
							
							adapter.setLists(lists);
							adapter.notifyDataSetChanged();

							if(lists.size()<Integer.parseInt(bean.getStatus().getTotalCount())){
								listView.addFootView();
							}else{
								listView.removeFootView();
							}
							
						} else {
							showNetShortToast(httpCode);
						}
					}
				});
	}
}
