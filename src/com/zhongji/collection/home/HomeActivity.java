package com.zhongji.collection.home;

import net.tsz.afinal.annotation.view.ViewInject;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.base.BaseIndexActivity;
import com.zhongji.collection.entity.User;
import com.zhongji.collection.project.AllProActivity;
import com.zhongji.collection.project.MyProActivity;
import com.zhongji.collection.project.NewProActivity;

/**
 * 首页
 * @author admin
 *
 */
public class HomeActivity extends BaseIndexActivity implements OnClickListener {

	@ViewInject(id = R.id.tv_name)
	private TextView tv_name;
	@ViewInject(id = R.id.tv_job)
	private TextView tv_job;
	@ViewInject(id = R.id.tv_district)
	private TextView tv_district;
	@ViewInject(id = R.id.tv_city)
	private TextView tv_city;
	@ViewInject(id = R.id.tv_mobile)
	private TextView tv_mobile;
	@ViewInject(id = R.id.tv_company)
	private TextView tv_company;
	private User user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub
		setTitle("信息采集");
		
		user = (User) getIntent().getSerializableExtra("user");
		if(user == null){
			showShortToast("用户不存在");
			return;
		}
		
		tv_name.setText(user.getRealName());
		tv_job.setText(user.getDepartment());
		tv_district.setText(user.getDistrict());
		tv_city.setText(user.getCity());
		tv_mobile.setText(user.getCellphone());
		tv_company.setText(user.getCompany());
		
		setMenuName(user.getRealName());
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		if(v.getId()==R.id.btn_newpro){
			//新建项目
			Intent intent = new Intent(HomeActivity.this, NewProActivity.class);
			if(user!=null){
				intent.putExtra("province", user.getProvince());
				intent.putExtra("city", user.getCity());
				intent.putExtra("district", user.getDistrict());
				intent.putExtra("type", "add");
			}
			startActivity(intent);
			overridePendingTransition(R.anim.in_from_left, R.anim.out_to_left);
		}else if(v.getId()==R.id.btn_allpro){
			//全部项目
			Intent intent = new Intent(HomeActivity.this, AllProActivity.class);
			startActivity(intent);
		}else if(v.getId()==R.id.btn_mypro){
			//我的项目
			Intent intent = new Intent(HomeActivity.this, MyProActivity.class);
			startActivity(intent);
		}
	}
	

	
}