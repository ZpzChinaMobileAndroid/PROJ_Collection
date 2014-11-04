package com.zhongji.collection.entity;

import java.io.Serializable;

/**
 * 用户信息
 * @author admin
 *
 */
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cellphone;
	private String city;
	private String company;
	private String department;
	private String district;
	private String duties;
	private String faceCount;
	private String isFaceRegisted;
	private String leaderLevel;
	private String office;
	private String province;
	private String realName;
	private String region;
	private String street;
	private String supervisor;
	private String userID;
	private String userName;
	private String userToken;
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getDuties() {
		return duties;
	}
	public void setDuties(String duties) {
		this.duties = duties;
	}
	public String getFaceCount() {
		return faceCount;
	}
	public void setFaceCount(String faceCount) {
		this.faceCount = faceCount;
	}
	public String getIsFaceRegisted() {
		return isFaceRegisted;
	}
	public void setIsFaceRegisted(String isFaceRegisted) {
		this.isFaceRegisted = isFaceRegisted;
	}
	public String getLeaderLevel() {
		return leaderLevel;
	}
	public void setLeaderLevel(String leaderLevel) {
		this.leaderLevel = leaderLevel;
	}
	public String getOffice() {
		return office;
	}
	public void setOffice(String office) {
		this.office = office;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getSupervisor() {
		return supervisor;
	}
	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserToken() {
		return userToken;
	}
	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}
	@Override
	public String toString() {
		return "UserData [cellphone=" + cellphone + ", city=" + city
				+ ", company=" + company + ", department=" + department
				+ ", district=" + district + ", duties=" + duties
				+ ", faceCount=" + faceCount + ", isFaceRegisted="
				+ isFaceRegisted + ", leaderLevel=" + leaderLevel + ", office="
				+ office + ", province=" + province + ", realName=" + realName
				+ ", region=" + region + ", street=" + street + ", supervisor="
				+ supervisor + ", userID=" + userID + ", userName=" + userName
				+ ", userToken=" + userToken + "]";
	}
	
}
