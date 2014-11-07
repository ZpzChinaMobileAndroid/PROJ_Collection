package com.zhongji.collection.seach;

import net.tsz.afinal.annotation.view.ViewInject;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.base.BaseSecondActivity;
import com.zhongji.collection.util.DialogUtils;

/**
 * 文字搜索
 * 
 * @author Admin
 * 
 */
public class SeachAdvancedActivity extends BaseSecondActivity implements
		OnClickListener {

	@ViewInject(id = R.id.et_seach_advanced_keyword)
	private EditText et_seach_advanced_keyword;
	@ViewInject(id = R.id.et_seach_advanced_companyname)
	private EditText et_seach_advanced_companyname;
	@ViewInject(id = R.id.tv_seach_district)
	private TextView tv_seach_district;
	@ViewInject(id = R.id.tv_seach_district_show)
	private TextView tv_seach_district_show;
	@ViewInject(id = R.id.tv_seach_city)
	private TextView tv_seach_city;
	@ViewInject(id = R.id.tv_seach_city_show)
	private TextView tv_seach_city_show;
	@ViewInject(id = R.id.tv_seach_projectstage)
	private TextView tv_seach_projectstage;
	@ViewInject(id = R.id.tv_seach_projectstage_show)
	private TextView tv_seach_projectstage_show;
	@ViewInject(id = R.id.tv_seach_projectcategory)
	private TextView tv_seach_projectcategory;
	@ViewInject(id = R.id.tv_seach_projectcategory_show)
	private TextView tv_seach_projectcategory_show;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_seach_advanced);

		tv_seach_district.setOnClickListener(this);
		tv_seach_city.setOnClickListener(this);
		tv_seach_projectcategory.setOnClickListener(this);
		tv_seach_projectstage.setOnClickListener(this);
	}

	@Override
	protected void init() {
		// TODO 自动生成的方法存根
		setTitle("高级搜索");
		setLeftBtn();
		setRightBtn();
	}

	@Override
	public void onClick(View v) {
		// TODO 自动生成的方法存根
		super.onClick(v);
		if (v.getId() == R.id.tv_seach_district) {
			// 区域
			DialogUtils.showDistrictDialog(SeachAdvancedActivity.this,
					new AlertDialog.OnClickListener() {

						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							// TODO 自动生成的方法存根

							for (int i = 0; i < DialogUtils.category1.length; i++) {
								if (i == arg1) {

									tv_seach_district_show.setText(DialogUtils.category1[arg1]);
									arg0.dismiss();
								}
							}
						}
					});

		} else if (v.getId() == R.id.tv_seach_city) {
			// 城市
			DialogUtils.showCityDialog(SeachAdvancedActivity.this,
					new AlertDialog.OnClickListener() {

						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							// TODO 自动生成的方法存根

							for (int i = 0; i < DialogUtils.category2.length; i++) {
								if (i == arg1) {

									tv_seach_city_show.setText(DialogUtils.category2[arg1]);
									arg0.dismiss();
								}
							}
						}
					});

		} else if (v.getId() == R.id.tv_seach_projectstage) {
			// 项目阶段
			final String[] items = getResources().getStringArray(R.array.Landusage);
			DialogUtils.showChoiceDialog(SeachAdvancedActivity.this,items,new AlertDialog.OnClickListener() {

				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					// TODO 自动生成的方法存根
					
					tv_seach_projectstage_show.setText(items[arg1]);
					arg0.dismiss();
				}
			});;

		} else if (v.getId() == R.id.tv_seach_projectcategory) {
			// 项目类别
			final String[] items = getResources().getStringArray(R.array.Landusage);
			DialogUtils.showChoiceDialog(SeachAdvancedActivity.this,items,new AlertDialog.OnClickListener() {

				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					// TODO 自动生成的方法存根

							tv_seach_projectcategory_show.setText(items[arg1]);
							arg0.dismiss();
				}
			});;

		}
	}
}
