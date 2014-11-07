package com.zhongji.collection.entity;

import java.io.Serializable;

/**
 * 联系人
 * @author admin
 *
 */
public class Contacts implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String baseContactID;	//298fc487-f29d-4a3c-b6b8-e121f5032c6f
	private String category;		//pileFoundationUnitContacts
	private String duties;			//现场经理
	private String name;			//飞机gd
	private String project;			//回家就饿
	private String projectID;		//1d821c60-25fe-4e66-aef9-f7fcc39a3f51
	private String telephone;		//566
	private String url;				//BaseContacts\/4aa933de-d1f0-4b85-a6be-b10959c6108f?baseContactId=298fc487-f29d-4a3c-b6b8-e121f5032c6f
	private String workAddress;		//副i
	private String workAt;			//凤凰花
	public String getBaseContactID() {
		return baseContactID;
	}
	public void setBaseContactID(String baseContactID) {
		this.baseContactID = baseContactID;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDuties() {
		return duties;
	}
	public void setDuties(String duties) {
		this.duties = duties;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public String getProjectID() {
		return projectID;
	}
	public void setProjectID(String projectID) {
		this.projectID = projectID;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getWorkAddress() {
		return workAddress;
	}
	public void setWorkAddress(String workAddress) {
		this.workAddress = workAddress;
	}
	public String getWorkAt() {
		return workAt;
	}
	public void setWorkAt(String workAt) {
		this.workAt = workAt;
	}
	@Override
	public String toString() {
		return "Contacts [baseContactID=" + baseContactID + ", category="
				+ category + ", duties=" + duties + ", name=" + name
				+ ", project=" + project + ", projectID=" + projectID
				+ ", telephone=" + telephone + ", url=" + url
				+ ", workAddress=" + workAddress + ", workAt=" + workAt + "]";
	}
	
	
}
