package com.zhongji.collection.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ContactsListBean extends BaseBean implements Serializable {

	/**
	 * 联系人列表
	 */
	private static final long serialVersionUID = 1L;
	private List<Contacts> data;


	public List<Contacts> getData() {
		if(data==null){
			return new ArrayList<Contacts>();
		}
		return data;
	}


	public void setData(List<Contacts> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "UserListBean [data=" + data + ", status=" + status + "]";
	}

}
