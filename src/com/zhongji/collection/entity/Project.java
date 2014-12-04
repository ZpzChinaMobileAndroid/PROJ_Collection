package com.zhongji.collection.entity;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhongji.collection.util.TimeUtils;

public class Project implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String actualStartTime;//实际开工时间	
	private String area;//土地面积
	private String areaOfStructure;//建筑面积
	private List<ContactsListBean> baseContacts;//-----
	private String city;//市区县
	private String decorationProgress;//装修进度
	private String decorationSituation;//装修情况
	private String description;//项目描述
	private String electroweakInstallation;//弱电安装
	private String expectedConstructionTime;//-----
	private String expectedFinishTime;//预计竣工时间
	private String expectedStartTime;//预计开工时间
	private String fireControl;//消防
	private String foreignInvestment;//外资参与
	private String green;//绿化
	private String investment;//投资额
	private String landName;//地块名称
	private String landAddress;//地块地址（项目地址）
	private String latitude;//纬度
	private String longitude;//经度
	private String mainDesignStage;//主体设计阶段
	private String ownerBy;//-----
	private String ownerType;//业主类型
	private String plotRatio;//土地容积率
	private String projectCode;//项目编号
	private String projectID;//项目ID
	private List<ImagesListBean> projectImgs;//-----
	private String projectName;//项目名称
	private String propertyAirCondition;//空调
	private String propertyElevator;//电梯
	private String propertyExternalWallMeterial;//外墙材料
	private String propertyHeating;//供暖
	private String propertyStealStructure;//钢结构
	private String province;//所在省市
	private String storeyHeight;//建筑层高
	private String url;//页面url　
	private String usage;//地块用途
	/*－－－－－*/
	private String projectStage = "1";
	private String projectVersion;//版本号
	private String district;//所在区域
	private String auctionUnit;//拍卖单位
	private String owner;//业主单位
	
	public String projectToJsonString(){
		String[] keys = {"foreignInvestment","propertyAirCondition","propertyElevator","propertyExternalWallMeterial","propertyHeating","propertyStealStructure"};
		String[] timekeys = {"actualStartTime","expectedFinishTime","expectedStartTime"};
		String s = JSON.toJSONString(this);
		JSONObject jb = JSONObject.parseObject(s);
		jb.remove("baseContacts");
		jb.remove("projectImgs");
		replayValue(jb,keys);
		replayTimeValue(jb, timekeys);
		System.out.println(jb.toJSONString());
		
		return jb.toJSONString();
	}
	
	public String contactsToJsonString(Contacts cbean){
		String s = JSON.toJSONString(cbean);
		JSONObject jb = JSONObject.parseObject(s);
		jb.put("projectID", projectID);
		System.out.println(jb.toJSONString());
		
		return jb.toJSONString();
	}
	
	public String projectImgsToJsonString(Images cbean){
		String s = JSON.toJSONString(cbean);
		JSONObject jb = JSONObject.parseObject(s);
		jb.put("projectID", projectID);
//		jb.put("imgContent", jb.get("imgCompressionContent"));
		jb.put("imgCompressionContent", "");
		System.out.println(jb.toJSONString());
		return jb.toJSONString();
	}
	
	private void replayTimeValue(JSONObject jb, String[] keys) {
		for(String key : keys){
			String a = (String) jb.get(key);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date date = format.parse(a);
				jb.put(key, "/Date("+date.getTime()+"+0800)/");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void replayValue(JSONObject jb, String[] keys) {
		for(String key : keys){
			String a = (String) jb.get(key);
			if("Yes".equals(a) || "YES".equals(a)){
				jb.put(key, "1");
			}else{
				jb.put(key, "0");
			}
		}
		
	}
	
	public List<ContactsListBean> getBaseContacts() {
		if(baseContacts == null){
			baseContacts = new ArrayList<ContactsListBean>();
			ContactsListBean bean = new ContactsListBean();
			baseContacts.add(bean);
			return baseContacts;
		}
		return baseContacts;
	}
	public String getProjectStage() {
		if(TextUtils.isEmpty(projectStage)){
			return "1";
		}
		return projectStage;
	}

	public void setProjectStage(String projectStage) {
		if(TextUtils.isEmpty(projectStage)){
			this.projectStage = "1";
		}
		
		if(Integer.parseInt(this.projectStage) < Integer.parseInt(projectStage)){
			this.projectStage = projectStage;
		}
		
	}

	public void setBaseContacts(List<ContactsListBean> baseContacts) {
		this.baseContacts = baseContacts;
	}
	public String getExpectedConstructionTime() {
		return expectedConstructionTime;
	}
	public void setExpectedConstructionTime(String expectedConstructionTime) {
		this.expectedConstructionTime = expectedConstructionTime;
	}
	public String getOwnerBy() {
		return ownerBy;
	}
	public void setOwnerBy(String ownerBy) {
		this.ownerBy = ownerBy;
	}
	public List<ImagesListBean> getProjectImgs() {
		if(projectImgs == null){
			projectImgs = new ArrayList<ImagesListBean>();
			ImagesListBean bean = new ImagesListBean();
			projectImgs.add(bean);
			return projectImgs;
		}
		return projectImgs;
	}
	public void setProjectImgs(List<ImagesListBean> projectImgs) {
		this.projectImgs = projectImgs;
	}
	public String getProjectID() {
		return projectID;
	}
	public void setProjectID(String projectID) {
		this.projectID = projectID;
	}
	public String getProjectCode() {
		return projectCode;
	}
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	public String getProjectVersion() {
		return projectVersion;
	}
	public void setProjectVersion(String projectVersion) {
		this.projectVersion = projectVersion;
	}
	public String getLandName() {
		return landName;
	}
	public void setLandName(String landName) {
		this.landName = landName;
	}
	public String getDistrict() {
		if(TextUtils.isEmpty(district)){
			return "";
		}
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getProvince() {
		if(TextUtils.isEmpty(province)){
			return "";
		}
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		if(TextUtils.isEmpty(city)){
			return "";
		}
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getLandAddress() {
		return landAddress;
	}
	public void setLandAddress(String landAddress) {
		this.landAddress = landAddress;
	}
	public String getArea() {
		if(TextUtils.isEmpty(area)){
			return "0";
		}
		Double double2= new Double(area);
		java.text.NumberFormat nfFormat=java.text.NumberFormat.getInstance();
		nfFormat.setGroupingUsed(false);
		return nfFormat.format(double2);
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getPlotRatio() {
		if(TextUtils.isEmpty(plotRatio)){
			return "0";
		}
		Double double2= new Double(plotRatio);
		java.text.NumberFormat nfFormat=java.text.NumberFormat.getInstance();
		nfFormat.setGroupingUsed(false);
		return nfFormat.format(double2);
	}
	public void setPlotRatio(String plotRatio) {
		this.plotRatio = plotRatio;
	}
	public String getUsage() {
		return usage;
	}
	public void setUsage(String usage) {
		this.usage = usage;
	}
	public String getAuctionUnit() {
		return auctionUnit;
	}
	public void setAuctionUnit(String auctionUnit) {
		this.auctionUnit = auctionUnit;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getExpectedStartTime() {
		
		return dateToTime(expectedStartTime);
	}
	public void setExpectedStartTime(String expectedStartTime) {
		this.expectedStartTime = expectedStartTime;
	}
	public String getExpectedFinishTime() {
		
		return dateToTime(expectedFinishTime);
	}
	public void setExpectedFinishTime(String expectedFinishTime) {
		this.expectedFinishTime = expectedFinishTime;
	}
	public String getInvestment() {
		if(TextUtils.isEmpty(investment)){
			return "0";
		}
		Double double2= new Double(investment);
		java.text.NumberFormat nfFormat=java.text.NumberFormat.getInstance();
		nfFormat.setGroupingUsed(false);
		return nfFormat.format(double2);
	}
	public void setInvestment(String investment) {
		this.investment = investment;
	}
	public String getAreaOfStructure() {
		if(TextUtils.isEmpty(areaOfStructure)){
			return "0";
		}
		Double double2= new Double(areaOfStructure);
		java.text.NumberFormat nfFormat=java.text.NumberFormat.getInstance();
		nfFormat.setGroupingUsed(false);
		return nfFormat.format(double2);
	}
	public void setAreaOfStructure(String areaOfStructure) {
		this.areaOfStructure = areaOfStructure;
	}
	public String getStoreyHeight() {
		if(TextUtils.isEmpty(storeyHeight)){
			return "0";
		}
		return storeyHeight;
	}
	public void setStoreyHeight(String storeyHeight) {
		this.storeyHeight = storeyHeight;
	}
	public String getForeignInvestment() {
		return returnTFSmal(foreignInvestment);
	}
	public void setForeignInvestment(String foreignInvestment) {
		this.foreignInvestment = foreignInvestment;
	}
	public String getOwnerType() {
		if(TextUtils.isEmpty(ownerType)){
			return "";
		}
		return ownerType;
	}
	public void setOwnerType(String ownerType) {
		this.ownerType = ownerType;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getMainDesignStage() {
		return mainDesignStage;
	}
	public void setMainDesignStage(String mainDesignStage) {
		this.mainDesignStage = mainDesignStage;
	}
	public String getPropertyElevator() {
		return returnTFBig(propertyElevator);
	}
	public void setPropertyElevator(String propertyElevator) {
		this.propertyElevator = propertyElevator;
	}
	public String getPropertyAirCondition() {
		return returnTFBig(propertyAirCondition);
	}
	public void setPropertyAirCondition(String propertyAirCondition) {
		this.propertyAirCondition = propertyAirCondition;
	}
	public String getPropertyHeating() {
		return returnTFBig(propertyHeating);
	}
	public void setPropertyHeating(String propertyHeating) {
		this.propertyHeating = propertyHeating;
	}
	public String getPropertyExternalWallMeterial() {
		return returnTFBig(propertyExternalWallMeterial);
	}
	public void setPropertyExternalWallMeterial(String propertyExternalWallMeterial) {
		this.propertyExternalWallMeterial = propertyExternalWallMeterial;
	}
	public String getPropertyStealStructure() {
		return returnTFBig(propertyStealStructure);
	}
	public void setPropertyStealStructure(String propertyStealStructure) {
		this.propertyStealStructure = propertyStealStructure;
	}
	public String getActualStartTime() {
		return dateToTime(actualStartTime);
	}
	public void setActualStartTime(String actualStartTime) {
		this.actualStartTime = actualStartTime;
	}
	public String getFireControl() {
		return fireControl;
	}
	public void setFireControl(String fireControl) {
		this.fireControl = fireControl;
	}
	public String getGreen() {
		return green;
	}
	public void setGreen(String green) {
		this.green = green;
	}
	public String getElectroweakInstallation() {
		return electroweakInstallation;
	}
	public void setElectroweakInstallation(String electroweakInstallation) {
		this.electroweakInstallation = electroweakInstallation;
	}
	public String getDecorationSituation() {
		return decorationSituation;
	}
	public void setDecorationSituation(String decorationSituation) {
		this.decorationSituation = decorationSituation;
	}
	public String getDecorationProgress() {
		return decorationProgress;
	}
	public void setDecorationProgress(String decorationProgress) {
		this.decorationProgress = decorationProgress;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	/**
	 * 时间格式转换
	 * @param time
	 * @return
	 */
	private String dateToTime(String time) {
		if(TextUtils.isEmpty(time)){
			return "1970-01-01";
		}
		
		String t = time.replace("/Date(", "").replace(")/", "");
		if(t.contains("+")){
			String[] ts = t.split("\\+");
			return TimeUtils.longtostr2(Long.parseLong(ts[0]));
		}
		return time;
	}
	
	public String returnTFBig(String text){
		if("true".equals(text)){
			return "YES";
		}else{
			return "NO";
		}
	}
	
	public String returnTFSmal(String text){
		if("true".equals(text)){
			return "Yes";
		}else{
			return "No";
		}
	}
	
	@Override
	public String toString() {
		return "Project [actualStartTime=" + actualStartTime + ", area=" + area
				+ ", areaOfStructure=" + areaOfStructure + ", baseContacts="
				+ baseContacts + ", city=" + city + ", decorationProgress="
				+ decorationProgress + ", decorationSituation="
				+ decorationSituation + ", description=" + description
				+ ", electroweakInstallation=" + electroweakInstallation
				+ ", expectedConstructionTime=" + expectedConstructionTime
				+ ", expectedFinishTime=" + expectedFinishTime
				+ ", expectedStartTime=" + expectedStartTime + ", fireControl="
				+ fireControl + ", foreignInvestment=" + foreignInvestment
				+ ", green=" + green + ", investment=" + investment
				+ ", landName=" + landName + ", landAddress=" + landAddress
				+ ", latitude=" + latitude + ", longitude=" + longitude
				+ ", mainDesignStage=" + mainDesignStage + ", ownerBy="
				+ ownerBy + ", ownerType=" + ownerType + ", plotRatio="
				+ plotRatio + ", projectCode=" + projectCode + ", projectID="
				+ projectID + ", projectImgs=" + projectImgs + ", projectName="
				+ projectName + ", propertyAirCondition="
				+ propertyAirCondition + ", propertyElevator="
				+ propertyElevator + ", propertyExternalWallMeterial="
				+ propertyExternalWallMeterial + ", propertyHeating="
				+ propertyHeating + ", propertyStealStructure="
				+ propertyStealStructure + ", province=" + province
				+ ", storeyHeight=" + storeyHeight + ", url=" + url
				+ ", usage=" + usage + ", projectVersion=" + projectVersion
				+ ", district=" + district + ", auctionUnit=" + auctionUnit
				+ ", owner=" + owner + "]";
	}
	
	
}
