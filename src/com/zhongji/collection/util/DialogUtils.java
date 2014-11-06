package com.zhongji.collection.util;

/**
 * 弹窗工具类
 * 
 */

import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.android.phone.R.id;
import android.R.integer;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DialogUtils {

	public static EditText et_contacts_username, et_contacts_userphone,
			et_contacts_companyname, et_contacts_companyaddress;
	public static Button bt_contacts_save;
	public static TextView tv_contacts_post;

	
	// 多选择弹窗
	public static void showMultiChoiceDialog(Context context, int arrayid) {

		final boolean[] checkedItems = new boolean[] { false, false, false,false, false, false };
		AlertDialog.Builder builder2 = new AlertDialog.Builder(context);
		builder2.setTitle("用途");
		builder2.setMultiChoiceItems(arrayid, checkedItems,
		new AlertDialog.OnMultiChoiceClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1,		boolean arg2) {
				// TODO 自动生成的方法存根
			checkedItems[arg1] = arg2; // 改变被操作列表的状态
		}
	});
		builder2.setPositiveButton("确定", null);
		builder2.setNegativeButton("取消", null);
		builder2.show();
	}

	
	
	// 单选择弹窗
	public static void showChoiceDialog(Context context, int arrayid) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setItems(arrayid, null);
		builder.show();
	}
	
	
	
	// 联系人弹窗
	public static void showContactsDialog(final Context context,final int arrayid) {
		Activity act = (Activity) context;
		View view = act.getLayoutInflater().inflate(R.layout.dialog_contacts,
				null);
		final AlertDialog builder = new AlertDialog.Builder(context).create();
		builder.setView(view, 0, 0, 0, 0);
		et_contacts_username = (EditText) view
				.findViewById(id.et_contacts_username);// 添加姓名
		et_contacts_userphone = (EditText) view
				.findViewById(id.et_contacts_userpassword);// 添加电话
		tv_contacts_post = (TextView) view.findViewById(id.tv_contacts_post);// 岗位
		et_contacts_companyname = (EditText) view
				.findViewById(id.et_contacts_companyname);// 拍卖单位
		et_contacts_companyaddress = (EditText) view
				.findViewById(id.et_contacts_companyaddress);// 拍卖单位
		bt_contacts_save = (Button) view.findViewById(id.bt_contacts_save);// 保存

		// 岗位
		tv_contacts_post.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO 自动生成的方法存根
				AlertDialog.Builder builder = new AlertDialog.Builder(context);
				builder.setItems(arrayid, null);
				builder.show();
			}
		});
		// 保存
		bt_contacts_save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO 自动生成的方法存根
				// 添加电话
				String two = et_contacts_userphone.getText().toString();
				int three = et_contacts_userphone.getText().length();
				if (two.equals("")) {
					Toast.makeText(context, "电话号码不能为空，请输入", Toast.LENGTH_SHORT)	.show();
				}
				// 添加姓名
				String one = et_contacts_companyname.getText().toString();
				int four = et_contacts_companyname.getText().length();
				if (one.equals("")) {
					Toast.makeText(context, "姓名不能为空，请输入", Toast.LENGTH_SHORT).show();
				} else if (four > 4) {
					Toast.makeText(context, "姓名长度最长为4位，请重新输入",
							Toast.LENGTH_SHORT).show();
				}
			}
		});
		builder.show();
		
		
	}
}
