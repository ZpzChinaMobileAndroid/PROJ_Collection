package com.zhongji.collection.entity;

import java.io.Serializable;

public class ProjectResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String projectCode;
	private String projectID;
	private String projectName;
	public String getProjectCode() {
		return projectCode;
	}
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	public String getProjectID() {
		return projectID;
	}
	public void setProjectID(String projectID) {
		this.projectID = projectID;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	@Override
	public String toString() {
		return "ProjectResult [projectCode=" + projectCode + ", projectID="
				+ projectID + ", projectName=" + projectName + "]";
	}

	
}
