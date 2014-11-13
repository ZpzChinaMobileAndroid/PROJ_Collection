package com.zhongji.collection.project;

import java.util.ArrayList;
import java.util.List;

import net.tsz.afinal.annotation.view.ViewInject;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.alibaba.fastjson.JSON;
import com.loopj.android.http.RequestParams;
import com.zhongji.collection.adapter.ProjectAdapter;
import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.base.BaseSecondActivity;
import com.zhongji.collection.entity.Contacts;
import com.zhongji.collection.entity.ContactsListBean;
import com.zhongji.collection.entity.Images;
import com.zhongji.collection.entity.ImagesListBean;
import com.zhongji.collection.entity.Project;
import com.zhongji.collection.entity.ProjectListBean;
import com.zhongji.collection.entity.ProjectResult;
import com.zhongji.collection.entity.ProjectResultListBean;
import com.zhongji.collection.network.HttpAPI;
import com.zhongji.collection.network.HttpRestClient;
import com.zhongji.collection.network.ResponseUtils;
import com.zhongji.collection.util.JsonUtils;
import com.zhongji.collection.util.PreferencesUtils;

/**
 * 我的项目
 * @author admin
 *
 */
public class MyProActivity extends BaseSecondActivity{
	
	private int page=0;
	private int size=10;
	@ViewInject(id=R.id.radioGroup1)
	private RadioGroup radioGroup1;
	private ProjectAdapter adapter;
	@ViewInject(id=R.id.listView1)
	private ListView listView1;
	private List<Project> lists;
	private List<Project> plists;	//本地
	private int uploadIndex=0;
	private int contactsIndex=0;
	private int imagesIndex=0;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mypro);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected void init() {
		// TODO Auto-generated method stub
		
		plists = (List<Project>) PreferencesUtils.getObject(MyProActivity.this, PreferencesUtils.PREFERENCE_KEY);
		if(plists==null){
			plists = new ArrayList<Project>();
		}
		
//		FinalDb db = FinalDb.create(MyProActivity.this);
//		plists = changelists(db.findAll(Project.class));
//		System.out.println("pl--"+plists.toString());
		
		setTitle("我的任务");
		setLeftBtn();
		initMenu();
//		setRightBtn();
		
		lists = new ArrayList<Project>();
		adapter = new ProjectAdapter(MyProActivity.this);
		listView1.setAdapter(adapter);
		listView1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Project pro = adapter.getItem(arg2);
				Intent intent = new Intent();
				intent.setClass(MyProActivity.this, ProjectDetialActivity.class);
				intent.putExtra("url", pro.getUrl());
				intent.putExtra("postion", arg2);
				intent.putExtra("project", pro);
				startActivity(intent);
			}
		});
		radioGroup1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup arg0, int arg1) {
				// TODO Auto-generated method stub
				if(arg1 == R.id.radio0){
					//发布的项目
					setRightBtnGone();
					page = 0;
					lists.clear();
					adapter.setLists(lists);
					adapter.notifyDataSetChanged();
					showProgressDialog();
					getProject();
				}else if(arg1 == R.id.radio1){
					//本地项目
					setRightBtnUpload(new View.OnClickListener() {
						
						@Override
						public void onClick(View arg0) {
							// TODO Auto-generated method stub
							showProgressDialog();
							uploadProject();
						}

					});
					uploadIndex = 0;
					page = 0;
					adapter.setLists(plists);
					adapter.notifyDataSetChanged();
				}
			}

		});
		
		((RadioButton)radioGroup1.getChildAt(0)).setChecked(true);
	}

	
	/**
	 * 上传项目
	 */
	private void uploadProject() {
		// TODO Auto-generated method stub
		contactsIndex = 0;
		imagesIndex = 0;
		if(uploadIndex<plists.size()){
			final Project pro = plists.get(uploadIndex);
			String params = pro.projectToJsonString();
			HttpRestClient.post(MyProActivity.this, HttpAPI.PROJECTS_ALL, JsonUtils.uploadproject(params), new ResponseUtils(MyProActivity.this) {
				
				@Override
				public void getResult(int httpCode, String result) {
					// TODO Auto-generated method stub
					
					if (httpCode == HttpAPI.HTTP_SUCCESS_CODE) {
						
						ProjectResultListBean bean = JSON.parseObject(JsonUtils.parseString(result),ProjectResultListBean.class);
						if (getData(bean)) {
							dismissProgressDialog();
							return; 
						}
						
						List<ProjectResult> lists = bean.getData();
						if(lists!=null && lists.size()>0){
							uploadIndex = uploadIndex + 1;
							ProjectResult res = lists.get(0);
							pro.setProjectCode(res.getProjectCode());
							pro.setProjectID(res.getProjectID());
							List<ContactsListBean> blists = pro.getBaseContacts();
							uploadContacts(pro, blists);
							
						}else{
							showShortToast("上传失败");
							dismissProgressDialog();
						}

					} else {
						dismissProgressDialog();
						showNetShortToast(httpCode);
					}
				}
			});
		}else{
			plists.clear();
			PreferencesUtils.saveObject(MyProActivity.this, PreferencesUtils.PREFERENCE_KEY, plists);
			adapter.setLists(plists);
			adapter.notifyDataSetChanged();
//			showShortToast("完");
			System.out.println("完");
			showShortToast("上传成功");
			dismissProgressDialog();
		}
		
	}
	
	private void uploadContacts(final Project pro, final List<ContactsListBean> blists) {
		// TODO Auto-generated method stub
		
		List<Contacts> cl = null;
		if(blists!=null && blists.size()>0){
			cl = blists.get(0).getData();
		}
		if(cl!=null && contactsIndex<cl.size()){
			Contacts cbean = cl.get(contactsIndex);
			String params = pro.contactsToJsonString(cbean);
			HttpRestClient.post(MyProActivity.this, HttpAPI.CONTACTS, JsonUtils.uploadproject(params), new ResponseUtils(MyProActivity.this) {
				
				@Override
				public void getResult(int httpCode, String result) {
					// TODO Auto-generated method stub
					
					if (httpCode == HttpAPI.HTTP_SUCCESS_CODE) {
						
						ProjectResultListBean bean = JSON.parseObject(JsonUtils.parseString(result),ProjectResultListBean.class);
						if (getData(bean)) {
							dismissProgressDialog();
							return; 
						}
						
						contactsIndex = contactsIndex + 1;
						uploadContacts(pro, blists);

					} else {
						dismissProgressDialog();
						showNetShortToast(httpCode);
					}
				}
			});
		}else{
			System.out.println("联系人上传结束");
			List<ImagesListBean> imglists = pro.getProjectImgs();
			uploadImages(pro, imglists);
		}
	}
	
	private void uploadImages(final Project pro, final List<ImagesListBean> imglists) {
		// TODO Auto-generated method stub
		List<Images> imgl = null;
		if(imglists!=null && imglists.size()>0){
			imgl = imglists.get(0).getData();
		}
		if(imgl!=null && imagesIndex<imgl.size()){
			Images cbean = imgl.get(imagesIndex);
			String params = pro.projectImgsToJsonString(cbean);
			HttpRestClient.post(MyProActivity.this, HttpAPI.PROJECTIMGS, JsonUtils.uploadproject(params), new ResponseUtils(MyProActivity.this) {
				
				@Override
				public void getResult(int httpCode, String result) {
					// TODO Auto-generated method stub
					
					if (httpCode == HttpAPI.HTTP_SUCCESS_CODE) {
						
						ProjectResultListBean bean = JSON.parseObject(JsonUtils.parseString(result),ProjectResultListBean.class);
						if (getData(bean)) {
							dismissProgressDialog();
							return; 
						}
						
						imagesIndex = imagesIndex + 1;
						uploadImages(pro, imglists);

					} else {
						dismissProgressDialog();
						showNetShortToast(httpCode);
					}
				}
			});
		}else{
			System.out.println("图片上传结束");
			uploadProject();
		}
	}
	
	/**
	 * 我的项目
	 */
	private void getProject() {
		// TODO Auto-generated method stub
		RequestParams params = new RequestParams();
		params.put("startIndex", page+"");
		params.put("pageSize", size+"");
		params.put("myProjects", "true");
		HttpRestClient.get(MyProActivity.this, HttpAPI.PROJECTS_ALL, HttpRestClient.TOKEN,
				params,
				new ResponseUtils(MyProActivity.this) {
 
					@Override
					public void getResult(int httpCode, String result) {
						// TODO Auto-generated method stub
						dismissProgressDialog();
						if (httpCode == HttpAPI.HTTP_SUCCESS_CODE) {
							
							if(page == 0){
								lists.clear();
								adapter.setLists(lists);
								adapter.notifyDataSetChanged();
							}
							
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
	
	/**
	 * 数据排序
	 * @param lists
	 * @return
	 */
	@SuppressWarnings("unused")
	private List<Project> changelists(List<Project> lists){
		List<Project> l = new ArrayList<Project>();
		for(int i=lists.size()-1;i>=0;i--){
			l.add(lists.get(i));
		}
		return l;
	}
}
