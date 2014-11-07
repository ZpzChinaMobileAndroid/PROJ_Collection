package com.zhongji.collection.entity;

import java.io.Serializable;
import java.util.List;

public class ImagesListBean extends BaseBean implements Serializable {

	/**
	 * 图片列表
	 */
	private static final long serialVersionUID = 1L;
	private List<Images> data;


	public List<Images> getData() {
		return data;
	}


	public void setData(List<Images> data) {
		this.data = data;
	}


	@Override
	public String toString() {
		return "UserListBean [data=" + data + ", status=" + status + "]";
	}

}
