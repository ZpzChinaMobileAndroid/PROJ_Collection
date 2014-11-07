package com.zhongji.collection.project;

import android.os.Bundle;

import com.alibaba.fastjson.JSON;
import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.base.BaseSecondActivity;
import com.zhongji.collection.entity.ProjectListBean;
import com.zhongji.collection.network.HttpAPI;
import com.zhongji.collection.network.HttpRestClient;
import com.zhongji.collection.network.ResponseUtils;
import com.zhongji.collection.util.JsonUtils;

/**
 * 项目详情
 * @author admin
 *
 */
public class ProjectDetialActivity extends BaseSecondActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_projectdetial);
	}
	
	@Override
	protected void init() {
		// TODO Auto-generated method stub
		
		setTitle("项目详情");
		setLeftBtn();
		
		String url = getIntent().getStringExtra("url");
		
		showProgressDialog();
		getProject(url);
	}

	/**
	 * 获取项目详情
	 */
	private void getProject(String url) {
		HttpRestClient.get(ProjectDetialActivity.this, url, new ResponseUtils(ProjectDetialActivity.this) {
 
					@Override
					public void getResult(int httpCode, String result) {
						// TODO Auto-generated method stub
						dismissProgressDialog();
						if (httpCode == HttpAPI.HTTP_SUCCESS_CODE) {
							ProjectListBean bean = JSON.parseObject(JsonUtils.parseString(result),ProjectListBean.class);
							if (getData(bean)) {
								return; 
							}

							
//							List<Project> temp = bean.getData();
//							if(temp!=null && temp.size()>0){
//								for(Project pro : temp){
//									lists.add(pro);
//								}
//							}
//							
//							adapter.setLists(lists);
//							adapter.notifyDataSetChanged();

						} else {
							showNetShortToast(httpCode);
						}
					}
				});
	}
}
