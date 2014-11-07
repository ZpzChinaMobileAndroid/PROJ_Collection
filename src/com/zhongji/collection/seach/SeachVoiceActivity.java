package com.zhongji.collection.seach;

import net.tsz.afinal.annotation.view.ViewInject;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.base.BaseSecondActivity;

/**
 * 高级语音搜索
 * @author Admin
 *
 */
public class SeachVoiceActivity  extends BaseSecondActivity implements OnClickListener{

	@ViewInject(id=R.id.et_seach_voice_show)
	private EditText et_seach_voice_show;
	@ViewInject(id=R.id.iv_seach_voice_start)
	private EditText iv_seach_voice_start;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_seach_voice);
	}

	
	@Override
	protected void init() {
		// TODO 自动生成的方法存根
		setTitle("语音搜索");
		setLeftBtn();
		setRightBtn();
	}
	@Override
	public void onClick(View v) {
		// TODO 自动生成的方法存根
		super.onClick(v);
	if(v.getId()==R.id.et_seach_voice_show){
	//语音搜索	
		
	   }	
	}
}
