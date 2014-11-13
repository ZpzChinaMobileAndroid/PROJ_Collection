package com.zhongji.collection.entity;

import java.io.Serializable;
import java.util.List;

public class ProjectResultListBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<ProjectResult> data;


	public List<ProjectResult> getData() {
		return data;
	}


	public void setData(List<ProjectResult> data) {
		this.data = data;
	}


	@Override
	public String toString() {
		return "ProjectResultListBean [data=" + data + "]";
	}

}
