package com.zhongji.collection.project;

import java.util.ArrayList;
import java.util.List;

import net.tsz.afinal.annotation.view.ViewInject;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
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
import com.zhongji.collection.widget.RTPullListView;
import com.zhongji.collection.widget.RTPullListView.OnRefreshListener;

/**
 * 我的项目
 * @author admin
 *
 */
public class MyProActivity extends BaseSecondActivity implements OnRefreshListener{
	
	private int page=0;
	private int size=5;
	@ViewInject(id=R.id.radioGroup1)
	private RadioGroup radioGroup1;
	private ProjectAdapter adapter;
	@ViewInject(id=R.id.listView1)
	private RTPullListView listView;
	private String type = "local";
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
	
	@Override
	protected void init() {
		// TODO Auto-generated method stub
		
//		plists = PreferencesUtils.getProjectLists(MyProActivity.this);
//		plists = (List<Project>) PreferencesUtils.getObject(MyProActivity.this, PreferencesUtils.PREFERENCE_KEY);
		if(plists==null){
			plists = new ArrayList<Project>();
		}
		
		setTitle("我的任务");
		setLeftBtn();
		initMenu();
		
		lists = new ArrayList<Project>();
		adapter = new ProjectAdapter(MyProActivity.this);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				if("publish".equals(type)){
					//发布
					Project pro = adapter.getItem(arg2-1);
					Intent intent = new Intent();
					intent.setClass(MyProActivity.this, ProjectDetialActivity.class);
					intent.putExtra("url", pro.getUrl());
					intent.putExtra("type", type);
					startActivity(intent);
				}else{
					//本地
					Project pro = adapter.getItem(arg2-1);
					Intent intent = new Intent();
					intent.setClass(MyProActivity.this, ProjectDetialActivity.class);
					intent.putExtra("url", pro.getUrl());
					intent.putExtra("position", arg2-1);
//					intent.putExtra("project", pro);
					intent.putExtra("type", type);
					startActivityForResult(intent, 10);
				}
				
			}
		});
		
		listView.setonRefreshListener(MyProActivity.this);
		
		radioGroup1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup arg0, int arg1) {
				// TODO Auto-generated method stub
				if(arg1 == R.id.radio0){
					//发布的项目
					listView.setRefreshable(true);
					listView.removeFootView();
					type = "publish";
					setRightBtnGone();
					page = 0;
					lists.clear();
					adapter.setLists(lists);
					adapter.notifyDataSetChanged();
					listView.setSelection(0);
					showProgressDialog();
					getProject();
				}else if(arg1 == R.id.radio1){
					//本地项目
					 SharedPreferences preferences = getSharedPreferences(PreferencesUtils.PREFERENCE_NAME_PRO,  Context.MODE_PRIVATE);  
					 System.out.println(preferences.getAll().keySet().toString());
					listView.setRefreshable(false);
					listView.removeFootView();
					type = "edit";
					setRightBtnUpload(new View.OnClickListener() {
						
						@Override
						public void onClick(View arg0) {
							// TODO Auto-generated method stub
							if(plists!=null && plists.size()>0){
								showAkertDialog("是否需要同步数据！", new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub
										showProgressDialog("上传中...");
										uploadProject();
									}
								});
							}else{
								showShortToast("没有项目上传");
							}
						}

					});
					
					showProgressDialog();
					new Thread(new LoadThread()).start();
//					plists = (List<Project>) PreferencesUtils.getObject(MyProActivity.this, PreferencesUtils.PREFERENCE_KEY);
					
				}
			}

		});
		
		((RadioButton)radioGroup1.getChildAt(0)).setChecked(true);
	}

	class LoadThread implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			plists = PreferencesUtils.getProjectLists(MyProActivity.this);
			mHandler.sendEmptyMessage(0);
		}
	}
	
	private Handler mHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case 0:
				dismissProgressDialog();
				if(plists==null){
					plists = new ArrayList<Project>();
				}
				uploadIndex = 0;
				page = 0;
				adapter.setLists(plists);
				adapter.notifyDataSetChanged();
				listView.setSelection(0);
				break;
			}
			super.handleMessage(msg);
		}
	};
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == 0) {
			return;
		}

		if (requestCode == 10 && resultCode == 40) {
			plists = PreferencesUtils.getProjectLists(MyProActivity.this);
//			plists = (List<Project>) PreferencesUtils.getObject(MyProActivity.this, PreferencesUtils.PREFERENCE_KEY);
			adapter.setLists(plists);
			adapter.notifyDataSetChanged();
		}
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
			HttpRestClient.upload(pro.getProjectID(), MyProActivity.this, HttpAPI.PROJECTS_ALL, JsonUtils.uploadproject(params), new ResponseUtils(MyProActivity.this) {
				
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
			PreferencesUtils.removeObject(MyProActivity.this);
//			PreferencesUtils.saveObject(MyProActivity.this, PreferencesUtils.PREFERENCE_KEY, plists);
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
			HttpRestClient.upload(cbean.getBaseContactID(), MyProActivity.this, HttpAPI.CONTACTS, JsonUtils.uploadproject(params), new ResponseUtils(MyProActivity.this) {
				
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
			HttpRestClient.upload(cbean.getImgID(), MyProActivity.this, HttpAPI.PROJECTIMGS, JsonUtils.uploadproject(params), new ResponseUtils(MyProActivity.this) {
				
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
						listView.onRefreshComplete();
						listView.onLoadMoreComplete();
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

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		page = 0;
		getProject();
	}

	@Override
	public void onLoadMore() {
		// TODO Auto-generated method stub
		page = page + 1;
		getProject();
	}
	
}
