package com.zhongji.collection.project;

import java.util.Map;

import net.tsz.afinal.annotation.view.ViewInject;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.base.BaseSecondActivity;

/**
 * 项目详情
 * 
 * @author Admin
 *
 */

public class ProjectDetialStageActivity extends BaseSecondActivity implements OnClickListener {

	private int[] iv_photo_id = { R.id.iv_0, R.id.iv_2, R.id.iv_4, R.id.iv_6, R.id.iv_8,R.id.iv_10,R.id.iv_12, R.id.iv_14, R.id.iv_16, R.id.iv_18};
	private int[] iv_users_id = { R.id.iv_1,R.id.iv_3, R.id.iv_5, R.id.iv_7,R.id.iv_9, R.id.iv_11, R.id.iv_13,  R.id.iv_15, R.id.iv_17, R.id.iv_19};
	@ViewInject(id=R.id.pro_detalis_stage_result)
	private ImageView pro_detalis_stage_result;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_projectdetails_stage);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected void init() {
		// TODO 自动生成的方法存根
		
		Map<Integer, boolean[]> maps = (Map<Integer, boolean[]>) getIntent().getSerializableExtra("maps");
		
		setTitle("项目详情");
		setLeftBtn();
		initMenu();
		
		for(int i=0;i<iv_photo_id.length;i++){
			ImageView iv_photoView=(ImageView) findViewById(iv_photo_id[i]);
			iv_photoView.setVisibility(maps.get(i)[0]?View.VISIBLE:View.INVISIBLE);
		}
		
		for(int i=0;i<iv_users_id.length;i++){
			ImageView iv_usersView=(ImageView) findViewById(iv_users_id[i]);
			iv_usersView.setVisibility(maps.get(i)[1]?View.VISIBLE:View.INVISIBLE);
		}
		
	}
	@Override
	public void onClick(View v) {
		// TODO 自动生成的方法存根
		super.onClick(v);
		Object ob = v.getTag();
		if(ob!=null){
			int tag = Integer.parseInt(ob.toString());
			System.out.println(tag+" --?");
			if(tag!=10){
				Intent intent = new Intent();
				intent.putExtra("tag", tag);
				setResult(10, intent);
			}
			finish();
		
		}
	}
}
