package com.zhongji.collection.project.view;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.entity.Project;
import com.zhongji.collection.util.DialogUtils;
import com.zhongji.collection.util.ToastUtils;

/**
 * 土地信息－土地规划
 * 
 * @author admin
 * 
 */
public class LandPlanView extends BaseView implements OnClickListener {

	private String type = "auctionUnitContacts";
	public View view_landplan;
	public GridPhotoView mGridView;
	public EditText et_landname; // 地块名称
	public TextView tv_provinces; // 所属省市
	public EditText et_address; // 地块地址
	public EditText et_landarea; // 土地面积
	public EditText et_landvolume; // 土地容积率
	public TextView tv_landuse; // 地块用途
	public TextView tv_auctionunit; // 拍卖单位
	public TextView tv_landuse_value;
	public TextView tv_provinces_value;
	

	public LandPlanView(Context context, Project project) {
		this.context = context;
		this.project = project;
		this.contactsLists = project.getBaseContacts();
		this.imagesLists = project.getProjectImgs();
		view_landplan = View.inflate(context, R.layout.view_landplan, null);
		mGridView = new GridPhotoView(context, view_landplan);
		et_landname = (EditText) view_landplan.findViewById(R.id.et_landname);
		tv_provinces = (TextView) view_landplan.findViewById(R.id.tv_provinces);
		et_address = (EditText) view_landplan.findViewById(R.id.et_address);
		et_landarea = (EditText) view_landplan.findViewById(R.id.et_landarea);
		et_landvolume = (EditText) view_landplan
				.findViewById(R.id.et_landvolume);
		tv_landuse = (TextView) view_landplan.findViewById(R.id.tv_landuse);
		tv_auctionunit = (TextView) view_landplan
				.findViewById(R.id.tv_auctionunit);
		tv_landuse_value = (TextView) view_landplan.findViewById(R.id.tv_landuse_value);
		tv_provinces_value = (TextView) view_landplan.findViewById(R.id.tv_provinces_value);
		layout_contacts = (LinearLayout) view_landplan.findViewById(R.id.layout_contacts);

		tv_landuse.setOnClickListener(this);
		tv_auctionunit.setOnClickListener(this);
		setOnTextChange(et_landname);
		setOnTextChange(et_address);
		setOnTextChange(et_landarea);
		setOnTextChange(et_landvolume);
		setSoftInput(context, view_landplan);
		
//		view_landplan.setOnTouchListener(new OnTouchListener() {
//			private float startX=0;
//			@Override
//			public boolean onTouch(View arg0, MotionEvent arg1) {
//				// TODO Auto-generated method stub
//				float x = arg1.getX();
//				float y = arg1.getY();
//				if (arg1.getAction() == MotionEvent.ACTION_DOWN) {
//					startX = x;
//				} else if (arg1.getAction() == MotionEvent.ACTION_UP) {
//					float res = x - startX;
//					if(res>0){
//						//左划
//						System.out.println("111");
//					}else if(res<0){
//						//右划
//						System.out.println("222");
//					}
//				} else if (arg1.getAction() == MotionEvent.ACTION_MOVE) {
//
//				}
//				return false;
//			}
//		});
		update(true);
	}

	public void update(boolean bool) {
		this.imgsType = "plan";
		if(bool){
			updateImg(mGridView);
		}
		updateEditText();
		updateUsage();
		updateContacts(type);
	}
	
	@Override
	protected void setValue(EditText et, String text) {
		// TODO Auto-generated method stub
		super.setValue(et, text);
		switch(et.getId()){
		case R.id.et_landname:
			project.setLandName(text);
			break;
		case R.id.et_address:
			project.setLandAddress(text);
			break;
		case R.id.et_landarea:
			project.setArea(text);
			break;
		case R.id.et_landvolume:
			project.setPlotRatio(text);
			break;
		}
	}
	
	public View getView() {
		return view_landplan;
	}

	@Override
	public void onClick(View arg0) {
		// TODO 自动生成的方法存根

		if (arg0.getId() == R.id.tv_landuse) {
			// 地块用途
			final String[] items = context.getResources().getStringArray(R.array.Landusage);
			final boolean[] checkedItems = new boolean[] { false, false, false,false, false, false };
			DialogUtils.showMultiChoiceDialog(context, "地块用途", items, checkedItems, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					// TODO Auto-generated method stub
					project.setUsage(getCheckedText(items, checkedItems));
					
					updateUsage();
				}

			});

		} else if (arg0.getId() == R.id.tv_auctionunit) {
			// 拍卖单位
			if(getListCount(type)<3){
				showUnitDialog(-1, null, type);
			}else{
				ToastUtils.getStance(context).showShortToast("名额已经满了！");
			}

		}
	}

	
	
	/**
	 *更新用途
	 */
	private void updateUsage() {
		tv_provinces_value.setText(project.getProvince()+" "+project.getCity()+" "+project.getDistrict());
		tv_landuse_value.setText(project.getUsage());
	}
	
	/**
	 * 更新编辑框
	 */
	private void updateEditText() {
		// TODO Auto-generated method stub
		et_landname.setText(project.getLandName());
		et_address.setText(project.getLandAddress());
		et_landarea.setText(project.getArea());
		et_landvolume.setText(project.getPlotRatio());
	}
}
