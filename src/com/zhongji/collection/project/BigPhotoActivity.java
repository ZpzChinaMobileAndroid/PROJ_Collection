package com.zhongji.collection.project;

import java.util.List;

import net.tsz.afinal.annotation.view.ViewInject;
import android.os.Bundle;

import com.alibaba.fastjson.JSON;
import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.base.BaseSecondActivity;
import com.zhongji.collection.entity.Images;
import com.zhongji.collection.entity.ImagesListBean;
import com.zhongji.collection.network.HttpAPI;
import com.zhongji.collection.network.HttpRestClient;
import com.zhongji.collection.network.ResponseUtils;
import com.zhongji.collection.util.JsonUtils;
import com.zhongji.collection.widget.AdvertView;
import com.zhongji.collection.widget.AdvertView.CheckListener;

/**
 * 大图
 * 
 * @author admin
 * 
 */
public class BigPhotoActivity extends BaseSecondActivity  {

	@ViewInject(id=R.id.advertview)
	private AdvertView advertview;
	private List<Images> imgslists;
	private int position = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.launch_activity_guide);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void init() {
		// TODO Auto-generated method stub
		
		int count = getIntent().getIntExtra("count", 0);
		imgslists = (List<Images>) getIntent().getSerializableExtra("imgslists");
		if(imgslists==null || imgslists.size()==0){
			finish();
			return;
		}
		
		setTitle("大图");
		setLeftBtn();
		
		advertview.initial(count);
		advertview.setCheckListener(new CheckListener() {
			
			public void change(int pos) {
				// TODO Auto-generated method stub
				position = pos;
				Images imgs = imgslists.get(position);
				if(imgs.getUrl() != null){
					showProgressDialog();
					getProject(imgs.getUrl());
				}else{
					advertview.updateVIew(position,imgs);
				}
			}
		});
		
		position = 0;
		Images imgs = imgslists.get(position);
		if(imgs.getUrl() != null){
			showProgressDialog();
			getProject(imgs.getUrl());
		}else{
			advertview.updateVIew(position,imgs);
		}
		
		
	}
	
	/**
	 * 获取项目详情
	 */
	private void getProject(String url) {
		HttpRestClient.get(BigPhotoActivity.this, url, new ResponseUtils(
				BigPhotoActivity.this) {
			
			@Override
			public void getResult(int httpCode, String result) {
				// TODO Auto-generated method stub
				dismissProgressDialog();
				if (httpCode == HttpAPI.HTTP_SUCCESS_CODE) {
					ImagesListBean bean = JSON.parseObject(
							JsonUtils.parseString(result),
							ImagesListBean.class);
					if (getData(bean)) {
						dismissProgressDialog();
						return;
					}
					Images img = bean.getData().get(0);
					advertview.updateVIew(position,img);
				} else {
					dismissProgressDialog();
					showNetShortToast(httpCode);
				}
			}

		});
	}

}
