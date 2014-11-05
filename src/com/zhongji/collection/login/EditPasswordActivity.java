package com.zhongji.collection.login;

import java.util.LinkedHashMap;
import java.util.Map;

import net.tsz.afinal.annotation.view.ViewInject;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.base.BaseSecondActivity;
import com.zhongji.collection.entity.BaseBean;
import com.zhongji.collection.network.HttpAPI;
import com.zhongji.collection.network.HttpRestClient;
import com.zhongji.collection.network.ResponseUtils;
import com.zhongji.collection.util.JsonUtils;

/**
 * 修改密码
 * 
 * @author admin
 * 
 */
public class EditPasswordActivity extends BaseSecondActivity implements
		OnClickListener {

	@ViewInject(id = R.id.et_oldpassword)
	private EditText et_oldpassword;
	@ViewInject(id = R.id.et_newpassword)
	private EditText et_newpassword;
	@ViewInject(id = R.id.et_newsecendpassword)
	private EditText et_newsencendpassword;
	@ViewInject(id = R.id.bt_modificition)
	private Button bt_modificition;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_editpassword);
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub

		setTitle("修改密码");
	}

	@Override
	public void onClick(View v) {
		// TODO 自动生成的方法存根

		if (v.getId() == R.id.bt_modificition) {
			// 修改密码
			// 判断输入的内容
			String oldpassword = et_oldpassword.getText().toString().trim();
			String newpassword = et_newpassword.getText().toString().trim();
			String newosecendpassword = et_newsencendpassword.getText()
					.toString().trim();
			if (oldpassword.equals("")) {
				showShortToast("请输入原密码");
				return;
			}
			if (newpassword.equals("")) {
				showShortToast("请输入新密码");
				return;
			}
			if (newosecendpassword.equals("")) {
				showShortToast("请再次输入新密码");
				return;
			}
			if (!newpassword.equals(newosecendpassword)) {
				showShortToast("对不起您两次输入的新密码不同，请重新输入");
				return;
			}

			showProgressDialog();
			lomodification(oldpassword, newpassword, newosecendpassword);

		}
	}

	/**
	 * 修改密码
	 * 
	 * @param token
	 */

	private void lomodification(String oldpassword, String newpassword,
			String newosecendpassword) {
		// TODO 自动生成的方法存根
		Map<String, String> parmas = new LinkedHashMap<String, String>();
		parmas.put("oldPassword", oldpassword);
		parmas.put("newPassword", newpassword);
		HttpRestClient.put(EditPasswordActivity.this,
				HttpAPI.USERS_MODIFICATION, JsonUtils.change(parmas, true),
				new ResponseUtils(EditPasswordActivity.this) {

					@Override
					public void getResult(int httpCode, String result) {
						// TODO 自动生成的方法存根

						dismissProgressDialog();
						if (httpCode == HttpAPI.HTTP_SUCCESS_CODE) {
							BaseBean bean = JSON.parseObject(
									JsonUtils.parseString(result),
									BaseBean.class);
							if (getData(bean)) {
								return;
							}
							showShortToast("修改密码成功");
							finish();
						} else {

							showNetShortToast(httpCode);

						}
					}
				});
	}
}
