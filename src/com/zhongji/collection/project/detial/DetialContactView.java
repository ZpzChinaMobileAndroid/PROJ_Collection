package com.zhongji.collection.project.detial;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhongji.collection.android.phone.R;
import com.zhongji.collection.entity.Contacts;
import com.zhongji.collection.entity.ContactsListBean;
import com.zhongji.collection.entity.Project;

/**
 * 详情联系人
 * @author admin
 *
 */
public class DetialContactView{
	
	@SuppressWarnings("unused")
	private Context context;
	public View view_detial_contact;
	public TextView tv_name;
	public TextView tv_duties;
	public TextView tv_telephone;
	public TextView tv_workat;
	public TextView tv_workaddress;
	private List<Contacts> lists;
	

	public DetialContactView(Context context, LinearLayout parent) {
		this.lists = new ArrayList<Contacts>();
		this.context = context;
		view_detial_contact = LayoutInflater.from(context).inflate(R.layout.view_detial_contact, parent, false);
		tv_name = (TextView) view_detial_contact.findViewById(R.id.tv_name);
		tv_duties = (TextView) view_detial_contact.findViewById(R.id.tv_duties);
		tv_telephone = (TextView) view_detial_contact.findViewById(R.id.tv_telephone);
		tv_workat = (TextView) view_detial_contact.findViewById(R.id.tv_workat);
		tv_workaddress = (TextView) view_detial_contact.findViewById(R.id.tv_workaddress);
		view_detial_contact.setTag(this);
	}
	
	/**
	 * 获取布局
	 * @return
	 */
	public View getView(){
		return view_detial_contact;
	}
	
	/**
	 * 更新页面
	 * @param project
	 */
	public void updateUI(Project project, int pos, String type) {
		// TODO Auto-generated method stub
		//计算分组
		getList(project, type);
		
		if(pos<lists.size()){
			Contacts contacts = lists.get(pos);
			//拍卖单位
			tv_name.setText(contacts.getName());
			tv_duties.setText(contacts.getDuties());
			tv_telephone.setText(contacts.getTelephone());
			tv_workat.setText(contacts.getWorkAt());
			tv_workaddress.setText(contacts.getWorkAddress());
		}
		
	}

	/**
	 * 计算分组
	 * @param project
	 */
	private void getList(Project project, String type) {
		lists.clear();
		List<ContactsListBean> conbeans = project.getBaseContacts();
		if(conbeans!=null && conbeans.size()>0){
			ContactsListBean conb = conbeans.get(0);
			List<Contacts> contactslists = conb.getData();
			if(contactslists!=null && contactslists.size()>0){
				for(Contacts contacts : contactslists){
					if(type.equals(contacts.getCategory())){
						lists.add(contacts);
					}
				}
			}
		}
	}
		
}

