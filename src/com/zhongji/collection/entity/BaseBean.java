package com.zhongji.collection.entity;

import java.io.Serializable;

public class BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Status status;
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "BaseBean [status=" + status + "]";
	}


}
