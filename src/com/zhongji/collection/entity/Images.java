package com.zhongji.collection.entity;

import java.io.Serializable;

public class Images implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String category;				//plan 土地规划
	private String device;					//ios
	private String imgCompressionContent;	//9j...
	private String imgID;					//5dde6935-fd91-4afa-91e1-28f4eb4744e2
	private String imgName;					//2014\/11\/06\/17\/41\/19
	private String projectID;				//1d821c60-25fe-4e66-aef9-f7fcc39a3f51
	private String projectName;				//
	private String url;						//ProjectImgs\/4aa933de-d1f0-4b85-a6be-b10959c6108f?imgID=5dde6935-fd91-4afa-91e1-28f4eb4744e2
	
	
	public Images(String category, String device, String imgCompressionContent) {
		super();
		this.category = category;
		this.device = device;
		this.imgCompressionContent = imgCompressionContent;
	}
	public Images() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	public String getImgCompressionContent() {
		return imgCompressionContent;
	}
	public void setImgCompressionContent(String imgCompressionContent) {
		this.imgCompressionContent = imgCompressionContent;
	}
	public String getImgID() {
		return imgID;
	}
	public void setImgID(String imgID) {
		this.imgID = imgID;
	}
	public String getImgName() {
		return imgName;
	}
	public void setImgName(String imgName) {
		this.imgName = imgName;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "Images [category=" + category + ", device=" + device
				+ ", imgCompressionContent=" + imgCompressionContent
				+ ", imgID=" + imgID + ", imgName=" + imgName + ", projectID="
				+ projectID + ", projectName=" + projectName + ", url=" + url
				+ "]";
	}


}
