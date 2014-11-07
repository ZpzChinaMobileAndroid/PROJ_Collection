package com.zhongji.collection.entity;

import java.io.Serializable;
import java.util.List;

public class ProjectListBean extends BaseBean implements Serializable {

	/**
	 * 项目列表
	 */
	private static final long serialVersionUID = 1L;
	private List<Project> data;


	public List<Project> getData() {
		return data;
	}


	public void setData(List<Project> data) {
		this.data = data;
	}


	@Override
	public String toString() {
		return "UserListBean [data=" + data + ", status=" + status + "]";
	}

}
