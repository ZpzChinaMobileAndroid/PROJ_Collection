package com.zhongji.collection.project;

import java.util.ArrayList;

import net.tsz.afinal.annotation.view.ViewInject;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.zhongji.collection.adapter.ProjectSeachAdapter;
import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.base.BaseSecondActivity;
import com.zhongji.collection.entity.Project;
import com.zhongji.collection.seach.SeachAdvancedActivity;
import com.zhongji.collection.seach.SeachMapActivity;
import com.zhongji.collection.seach.SeachVoiceActivity;

/**
 * 搜索方式
 * 
 * @author admin
 * 
 */
public class SearchProActivity extends BaseSecondActivity implements
		OnClickListener {

	@ViewInject(id = R.id.bt_seach_advanced)
	private Button bt_seach_advanced;
	@ViewInject(id = R.id.bt_seach_voice)
	private Button bt_seach_voice;
	@ViewInject(id = R.id.bt_seach_map)
	private Button bt_seach_map;
	@ViewInject(id = R.id.et_seach_result_start)
	private EditText et_seach_result_start;
	@ViewInject(id = R.id.lv_seachpro_message)
	private ListView lv_seachpro_message;
	private ProjectSeachAdapter adapter;
	ArrayList<Project> pro = new ArrayList<Project>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_searchpro);
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub

		setTitleSeach(this);
		adapter = new ProjectSeachAdapter(SearchProActivity.this);
		// lv_seachpro_message.setAdapter(adapter);
		// lv_seachpro_message.setOnKeyListener(new OnKeyListener() {
		//
		// @Override
		// public boolean onKey(View arg0, int arg1, KeyEvent arg2) {
		// // TODO 自动生成的方法存根
		//
		// if(arg1==KeyEvent.KEYCODE_ENTER){
		// String
		// seachcontent=et_seach_result_start.getText().toString().trim();
		// pro.clear();
		// }
		// return false;
		// }
		// });
	}

	@Override
	public void onClick(View v) {
		// TODO 自动生成的方法存根
		super.onClick(v);
		if (v.getId() == R.id.bt_seach_advanced) {
			// 文字搜索
			Intent intent = new Intent(SearchProActivity.this,
					SeachAdvancedActivity.class);
			startActivity(intent);

		} else if (v.getId() == R.id.bt_seach_map) {
			// 地图搜索
			Intent intent = new Intent(SearchProActivity.this,
					SeachMapActivity.class);
			startActivity(intent);

		} else if (v.getId() == R.id.bt_seach_voice) {
			// 语音搜索
			Intent intent = new Intent(SearchProActivity.this,
					SeachVoiceActivity.class);
			startActivity(intent);
		} else if (v.getId() == R.id.tv_right) {
			// 取消搜索
			Intent intent = new Intent(SearchProActivity.this,
					AllProActivity.class);
			startActivity(intent);
			finish();
		}
	}
}
