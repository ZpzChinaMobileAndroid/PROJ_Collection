package com.zhongji.collection.project.view;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.entity.Contacts;
import com.zhongji.collection.entity.ContactsListBean;
import com.zhongji.collection.entity.Images;
import com.zhongji.collection.entity.ImagesListBean;
import com.zhongji.collection.entity.Project;
import com.zhongji.collection.interfaces.ContactsDialogCilck;
import com.zhongji.collection.util.DensityUtil;
import com.zhongji.collection.util.DialogUtils;

public class BaseView {

	public String imgsType = "";
	protected Context context;
	protected LinearLayout layout_contacts;
	protected Project project;
	protected List<ContactsListBean> contactsLists;
	protected List<ImagesListBean> imagesLists;
	protected InputMethodManager manager; // 隐藏软键盘
	
	protected void setOnTextChange(final EditText et){
		et.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
				// TODO Auto-generated method stub
				setValue(et,arg0.toString());
			}
			
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	protected void setValue(EditText et, String text) {
		// TODO Auto-generated method stub
		switch(et.getId()){
		case R.id.et_investmoney:
		case R.id.et_buildarea:
		case R.id.et_buildfloor:
		case R.id.et_landarea:
		case R.id.et_landvolume:
			if("0".equals(text)){
				et.setText("");
			}
			break;
		}
	}
	
	/**
	 * 单位对话框
	 * @param pos
	 * @param contacts
	 */
	protected void showUnitDialog(final int pos,
			final Contacts contacts, final String type) {
		showUnitDialog(pos, contacts, type, R.array.Auctionunitpost);
	}
	
	protected void showUnitDialog(final int pos,
			final Contacts contacts, final String type,int arrayid) {
		final String[] items = context.getResources().getStringArray(arrayid);
		
		DialogUtils.showContactsDialog(context, items, contacts, new ContactsDialogCilck() {
			
			@Override
			public void save(Contacts contacts, String name, String phone, String station,
					String companyname, String companyaddress) {
				// TODO Auto-generated method stub
				List<Contacts> clists = null;
				if(contacts==null){
					contacts = new Contacts(type, station, name, project.getProjectName(), phone, companyaddress, companyname);
					clists = contactsLists.get(0).getData();
					clists.add(contacts);
				}else{
					contacts.contacts(type, station, name, project.getProjectName(), phone, companyaddress, companyname);
					clists = contactsLists.get(0).getData();
					clists.set(pos, contacts);
				}
				
				ContactsListBean clbean = new ContactsListBean();
				clbean.setData(clists);
				contactsLists.set(0, clbean);
				updateContacts(type);
			}

		});
	}
	
	/**
	 * 联系人显示
	 */
	protected void updateContacts(final String type) {
		// TODO Auto-generated method stub
		if(contactsLists!=null && contactsLists.size()>0){
			layout_contacts.removeAllViews();
			ContactsListBean clbean = contactsLists.get(0);
			List<Contacts> clists = clbean.getData();
			for(int i=0;i<clists.size();i++){
				final int pos = i;
				final Contacts contacts = clists.get(i);
				if(type.equals(contacts.getCategory())){
					LinearLayout.LayoutParams lllp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
					lllp.setMargins(DensityUtil.dip2px(context, 20), 0, 0, 0);
					TextView tv = new TextView(context);
					tv.setLayoutParams(lllp);
					tv.setText(contacts.getName());
					tv.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View arg0) {
							// TODO Auto-generated method stub
							showUnitDialog(pos, contacts,type);
						}

					});
					layout_contacts.addView(tv);
				}
			}
		}else{
			contactsLists = new ArrayList<ContactsListBean>();
		}
	}
	
	/**
	 * 多选显示
	 * @param items
	 * @param checkedItems
	 * @return
	 */
	protected String getCheckedText(String[] items, boolean[] checkedItems){
		String res = "";
		for(int i=0;i<checkedItems.length;i++){
			if(checkedItems[i]){
				res = res + items[i] + ",";
			}
		}
		if(res.length()>0){
			res = res.substring(0, res.length()-1);
		}
		return res;
	}
	
	/**
	 * 图片
	 */
	protected void updateImg(GridPhotoView mGridView) {
		// TODO Auto-generated method stub
		System.out.println(imagesLists.toString());
		if(imagesLists!=null && imagesLists.size()>0){
			ImagesListBean bean = imagesLists.get(0);
			if(bean!=null && bean.getData().size()>0){
//				if(mGridView.clear()){
					mGridView.clear();
					ImagesListBean imgbean = imagesLists.get(0);
					final List<Images> imglists = imgbean.getData();
					for (int i = 0; i < imglists.size(); i++) {
						Images img = imglists.get(i);
						if (imgsType.equals(img.getCategory())) {
							mGridView.addString(img.getImgCompressionContent());
						}
					}
					mGridView.notifyDataSetChanged();
//				}
			}
		}else{
			imagesLists = new ArrayList<ImagesListBean>();
			mGridView.notifyDataSetChanged();
		}
	}
	
	public void setImages(String imgsType, String imgCompressionContent){
		List<Images> imglists = imagesLists.get(0).getData();
		imglists.add(new Images(imgsType, "android", imgCompressionContent));
		
		ImagesListBean imgsbean = new ImagesListBean();
		imgsbean.setData(imglists);
		imagesLists.set(0, imgsbean);
	}
	
	
	protected int getListCount(String type) {
		int i = 0;
		List<Contacts> list = contactsLists.get(0).getData();
		for(Contacts c : list){
			if(type.equals(c.getCategory())){
				i++;
			}
		}
		return i;
	}
	
	protected void setSoftInput(Context context,View view) {
		final Activity act = (Activity) context;
		
		manager = (InputMethodManager) act.getSystemService(Context.INPUT_METHOD_SERVICE);
		view.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				// TODO Auto-generated method stub
				if(act.getCurrentFocus()!=null){
					manager.hideSoftInputFromWindow(act.getCurrentFocus()
							 .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
				}
				
				return false;
			}
		});
		
	}
}


