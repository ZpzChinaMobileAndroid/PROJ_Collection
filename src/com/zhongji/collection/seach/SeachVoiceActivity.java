package com.zhongji.collection.seach;

import net.tsz.afinal.annotation.view.ViewInject;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.iflytek.cloud.SpeechUtility;
import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.base.BaseSecondActivity;
import com.zhongji.collection.util.IflytekSpeechDialog;
import com.zhongji.collection.util.IflytekSpeechDialog.IflytekSpeechInterface;

/**
 * 高级语音搜索
 * @author Admin
 *
 */
public class SeachVoiceActivity  extends BaseSecondActivity implements OnClickListener, IflytekSpeechInterface{

	@ViewInject(id=R.id.et_seach_voice_show)
	private EditText et_seach_voice_show;
	@ViewInject(id=R.id.iv_seach_voice_start)
	private ImageView iv_seach_voice_start;
	private IflytekSpeechDialog iflytekSpeechDialog;
	@ViewInject(id=R.id.tv_right)
	private TextView tv_right;
	private String keyword;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_seach_voice);
		
		SpeechUtility.createUtility(getApplicationContext(), "appid=54632333");
		iflytekSpeechDialog = new IflytekSpeechDialog(this,this);
		iv_seach_voice_start.setOnClickListener(this);
		
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
	if(v.getId()==R.id.iv_seach_voice_start){
	//语音搜索	
		et_seach_voice_show.setText("");
		iflytekSpeechDialog.show();
				
		
	}else if(v.getId()==R.id.tv_right){
		   
		  keyword=et_seach_voice_show.getText().toString().trim();
		  if(keyword.equals("")){
			  showShortToast("请输入要查询的语句");
			  return;
		  }else{
		  Intent intent =new Intent(SeachVoiceActivity.this,SeachResultActivity.class);
		  intent.putExtra("keyword", keyword);
		  startActivity(intent);
		  }	   
	   }	
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		iflytekSpeechDialog.onDestroy();
	}

	@Override
	public void onInit() {
		// TODO 自动生成的方法存根
		iv_seach_voice_start.setEnabled(true);
	}

	@Override
	public void onResult(String result) {
		// TODO 自动生成的方法存根
		et_seach_voice_show.append(result);
	}

	@Override
	public void onError(String error) {
		// TODO 自动生成的方法存根
		
	}
}
