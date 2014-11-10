package com.zhongji.collection.project;

import net.tsz.afinal.annotation.view.ViewInject;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.zhongji.collection.adapter.ProjectAdapter;
import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.base.BaseSecondActivity;

/**
 * 我的项目
 * @author admin
 *
 */
public class MyProActivity extends BaseSecondActivity{
	
	@ViewInject(id=R.id.radioGroup1)
	private RadioGroup radioGroup1;
	private ProjectAdapter adapter;
	@ViewInject(id=R.id.listView1)
	private ListView listView1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mypro);
	}
	
	@Override
	protected void init() {
		// TODO Auto-generated method stub
		
		setTitle("我的任务");
		setLeftBtn();
		setRightBtn();
		
		adapter = new ProjectAdapter(MyProActivity.this);
		listView1.setAdapter(adapter);
		radioGroup1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup arg0, int arg1) {
				// TODO Auto-generated method stub
				if(arg1 == R.id.radio0){
					//发布的项目
				}else if(arg1 == R.id.radio1){
					//本地项目
				}
			}
		});
		
		((RadioButton)radioGroup1.getChildAt(0)).setChecked(true);
	}

}
