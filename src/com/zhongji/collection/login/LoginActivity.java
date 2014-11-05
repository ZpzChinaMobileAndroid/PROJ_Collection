package com.zhongji.collection.login;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.tsz.afinal.annotation.view.ViewInject;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.base.BaseActivity;
import com.zhongji.collection.entity.User;
import com.zhongji.collection.entity.UserListBean;
import com.zhongji.collection.home.HomeActivity;
import com.zhongji.collection.network.HttpAPI;
import com.zhongji.collection.network.HttpRestClient;
import com.zhongji.collection.network.ResponseUtils;
import com.zhongji.collection.util.JsonUtils;

/**
 * 登录页
 * @author admin
 *
 */
public class LoginActivity extends BaseActivity implements OnClickListener {

	@ViewInject(id=R.id.et_username)
	private EditText et_username;
	@ViewInject(id=R.id.et_password)
	private EditText et_password;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		init();//初始化(继承父类的时候)
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub
		et_username.setText("13554672223");
		et_password.setText("Abcd@12345");
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if (arg0.getId() == R.id.btn_sure) {
			// 登录
			// 判断输入的内容
			String username = et_username.getText().toString().trim();
			String password = et_password.getText().toString().trim();

			if (username.equals("")) {
				showShortToast("请输入用户名");
				return;
			}
			if (password.equals("")) {
				showShortToast("请输入密码");
				return;
			}

			showProgressDialog();
			login(username, password);

		}
	}

	/**
	 * 登录
	 */
	private void login(String username,String password) {
		Map<String, String> params = new LinkedHashMap<String, String>();
		params.put("userName", username);
		params.put("password", password);
		params.put("deviceType", "android");
		HttpRestClient.post(LoginActivity.this, HttpAPI.USERS_LOGIN,
				JsonUtils.change(params,false),
				new ResponseUtils(LoginActivity.this) {
 
					@Override
					public void getResult(int httpCode, String result) {
						// TODO Auto-generated method stub
						dismissProgressDialog();
						if (httpCode == HttpAPI.HTTP_SUCCESS_CODE) {
							UserListBean bean = JSON.parseObject(JsonUtils.parseString(result),UserListBean.class);
							if (getData(bean)) {
								return; 
							}
							showShortToast("登录成功");
							List<User> lists = bean.getData();
							if(lists!=null && lists.size()>0){
								User user = lists.get(0);
								HttpRestClient.TOKEN = user.getUserToken();

								Intent intent = new Intent(LoginActivity.this,  HomeActivity.class);
								intent.putExtra("user", user);
								startActivity(intent);
								finish();
							}

						} else {
							showNetShortToast(httpCode);
						}
					}
				});
	}

}