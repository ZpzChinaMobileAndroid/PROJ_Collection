package com.zhongji.collection.seach;

import java.util.ArrayList;
import java.util.List;

import net.tsz.afinal.annotation.view.ViewInject;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
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
import com.zhongji.collection.project.ProjectDetialActivity;
import com.zhongji.collection.util.JsonUtils;

/**
 * 搜索结果
 * @author Admin
 *
 */

public class SeachResultActivity extends BaseSecondActivity implements OnClickListener {

	@ViewInject(id=R.id.lv_seachpro_message)
	private ListView lv_seachpro_message;
	private ProjectAdapter adapter;
	@ViewInject(id=R.id.lv_seach_result)
	private ListView lv_seach_result;
	private List<Project> lists;
	private int page=0;
	private int size=5;
	String keyword;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_seach_result);
		
	}
	
	
	@Override
	protected void init() {
		// TODO 自动生成的方法存根
		setTitle("搜索结果");
		setLeftBtn();
		lists = new ArrayList<Project>();
		adapter = new ProjectAdapter(SeachResultActivity.this);
	    keyword=getIntent().getStringExtra("keyword");
		lv_seach_result.setAdapter(adapter);
		lv_seach_result.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
				// TODO Auto-generated method stub
				Project pro = adapter.getItem(arg2);
				Intent intent = new Intent();
				intent.setClass(SeachResultActivity.this, ProjectDetialActivity.class);
				intent.putExtra("url", pro.getUrl());
				startActivity(intent);
			}
		});
		
		showProgressDialog();
		getSeachProject();
		
       }


	/**
	 * 获取全部项目
	 */
	private void getSeachProject() {
		RequestParams params = new RequestParams();
		params.put("keywords", keyword);
		params.put("startIndex", page+"");
		params.put("pageSize", size+"");
		HttpRestClient.get(SeachResultActivity.this, HttpAPI.PROJECTS_ALL, HttpRestClient.TOKEN,params,new ResponseUtils(SeachResultActivity.this) {
 
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
