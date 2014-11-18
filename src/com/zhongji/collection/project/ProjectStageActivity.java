package com.zhongji.collection.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.base.BaseSecondActivity;

/**
 * 项目阶段
 * 
 * @author admin
 * 
 */
public class ProjectStageActivity extends BaseSecondActivity implements OnClickListener{

	private int currentItem = 0;
	private int[] ivids = { R.id.iv_0, R.id.iv_1, R.id.iv_2, R.id.iv_3,
			R.id.iv_4, R.id.iv_5, R.id.iv_6, R.id.iv_7, R.id.iv_8, R.id.iv_9 };
	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_projectstage);
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub
		intent = getIntent();
		currentItem = intent.getIntExtra("currentItem", 0);

		setTitle("项目阶段");
		setLeftBtn();

		ImageView iv_checked = (ImageView) findViewById(ivids[currentItem]);
		iv_checked.setVisibility(View.VISIBLE);
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		if(v.getId() != R.id.tv_left){
			String ob = v.getTag().toString();
			if(ob!=null){
				int tag = Integer.parseInt(ob);
				System.out.println(tag+" --?");
				intent.putExtra("tag", tag);
				setResult(10, intent);
				finish();
			}
		}
		
	}

}
