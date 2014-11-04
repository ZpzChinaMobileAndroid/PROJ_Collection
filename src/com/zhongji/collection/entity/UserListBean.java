package com.zhongji.collection.entity;

import java.io.Serializable;
import java.util.List;

public class UserListBean extends BaseBean implements Serializable {

	/**
	 * 用户信息
	 */
	private static final long serialVersionUID = 1L;
	private List<User> data;


	public List<User> getData() {
		return data;
	}


	public void setData(List<User> data) {
		this.data = data;
	}


	@Override
	public String toString() {
		return "UserListBean [data=" + data + ", status=" + status + "]";
	}

}
