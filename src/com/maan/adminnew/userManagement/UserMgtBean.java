package com.maan.adminnew.userManagement;

import java.util.Map;

public class UserMgtBean {
	private String agencyCode;
	private String uagencyCode;
	private String mode;
	private String uname;
	private String ugender;
	private String utype="User";
	private String userId;
	private String bname;
	private String udob;
	private String uoccupation;
	private String uaddress1;
	private String uaddress2;
	private String ucity;
	private String ucountry;
	private String upobox;
	private String uphone;
	private String umobile;
	private String uemail;
	private String ufax;
	private String unationality;
	private String ulogin_Id;
	private String borganization;
	private String searchBy;
	private String searchValue;
	private String reqFrom;
	private String index="0";
	private String mode1;
	private String utitle;
	private String ufirstname;
	private String ulastname;
	private String productID;
	private String productName;
	private String sdiscount;
	private String ustatus;
	private String password;
	private String repassword;
	private String display;
	private String unationalityName;
	private Map<Integer, String> uproductName;
	private Map<Integer, String> uproductId;
	private Map<Integer, String> specialDis;
	private Map<Integer, String> freight;
	private Map<Integer, String> insEndLimit;
	private Map<Integer, String> receipt;
	private Map<Integer, String> product;
	private String ucountryNa;
	private String broker;
	private String brokerName;
	private String openCover;
	private String login_Id;
	
	public String getLogin_Id() {
		return login_Id;
	}
	public void setLogin_Id(String loginId) {
		login_Id = loginId;
	}
	public String getAgencyCode() {
		return agencyCode;
	}
	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}
	public String getUcountryNa() {
		return ucountryNa;
	}
	public void setUcountryNa(String ucountryNa) {
		this.ucountryNa = ucountryNa;
	}
	public String getUagencyCode() {
		return uagencyCode;
	}
	public void setUagencyCode(String uagencyCode) {
		this.uagencyCode = uagencyCode;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUgender() {
		return ugender;
	}
	public void setUgender(String ugender) {
		this.ugender = ugender;
	}
	public String getUtype() {
		return "User";
	}
	public void setUtype(String utype) {
		this.utype = utype;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public String getUdob() {
		return udob;
	}
	public void setUdob(String udob) {
		this.udob = udob;
	}
	public String getUoccupation() {
		return uoccupation;
	}
	public void setUoccupation(String uoccupation) {
		this.uoccupation = uoccupation;
	}
	public String getUaddress1() {
		return uaddress1;
	}
	public void setUaddress1(String uaddress1) {
		this.uaddress1 = uaddress1;
	}
	public String getUaddress2() {
		return uaddress2;
	}
	public void setUaddress2(String uaddress2) {
		this.uaddress2 = uaddress2;
	}
	public String getUcity() {
		return ucity;
	}
	public void setUcity(String ucity) {
		this.ucity = ucity;
	}
	public String getUcountry() {
		return ucountry;
	}
	public void setUcountry(String ucountry) {
		this.ucountry = ucountry;
	}
	public String getUpobox() {
		return upobox;
	}
	public void setUpobox(String upobox) {
		this.upobox = upobox;
	}
	public String getUphone() {
		return uphone;
	}
	public void setUphone(String uphone) {
		this.uphone = uphone;
	}
	public String getUmobile() {
		return umobile;
	}
	public void setUmobile(String umobile) {
		this.umobile = umobile;
	}
	public String getUemail() {
		return uemail;
	}
	public void setUemail(String uemail) {
		this.uemail = uemail;
	}
	public String getUfax() {
		return ufax;
	}
	public void setUfax(String ufax) {
		this.ufax = ufax;
	}
	public String getUnationality() {
		return unationality;
	}
	public void setUnationality(String unationality) {
		this.unationality = unationality;
	}
	public String getUlogin_Id() {
		return ulogin_Id;
	}
	public void setUlogin_Id(String uloginId) {
		ulogin_Id = uloginId;
	}
	public String getBorganization() {
		return borganization;
	}
	public void setBorganization(String borganization) {
		this.borganization = borganization;
	}
	public String getSearchBy() {
		return searchBy;
	}
	public void setSearchBy(String searchBy) {
		this.searchBy = searchBy;
	}
	public String getSearchValue() {
		return searchValue;
	}
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	public String getReqFrom() {
		return reqFrom;
	}
	public void setReqFrom(String reqFrom) {
		this.reqFrom = reqFrom;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getMode1() {
		return mode1;
	}
	public void setMode1(String mode1) {
		this.mode1 = mode1;
	}
	public String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getSdiscount() {
		return sdiscount;
	}
	public void setSdiscount(String sdiscount) {
		this.sdiscount = sdiscount;
	}
	public String getUtitle() {
		return utitle;
	}
	public void setUtitle(String utitle) {
		this.utitle = utitle;
	}
	public String getUfirstname() {
		return ufirstname;
	}
	public void setUfirstname(String ufirstname) {
		this.ufirstname = ufirstname;
	}
	public String getUlastname() {
		return ulastname;
	}
	public void setUlastname(String ulastname) {
		this.ulastname = ulastname;
	}
	public String getUstatus() {
		return ustatus;
	}
	public void setUstatus(String ustatus) {
		this.ustatus = ustatus;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRepassword() {
		return repassword;
	}
	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
	public String getDisplay() {
		return display;
	}
	public void setDisplay(String display) {
		this.display = display;
	}
	public String getUnationalityName() {
		return unationalityName;
	}
	public void setUnationalityName(String unationalityName) {
		this.unationalityName = unationalityName;
	}
	public Map<Integer, String> getUproductName() {
		return uproductName;
	}
	public void setUproductName(Map<Integer, String> uproductName) {
		this.uproductName = uproductName;
	}
	public Map<Integer, String> getUproductId() {
		return uproductId;
	}
	public void setUproductId(Map<Integer, String> uproductId) {
		this.uproductId = uproductId;
	}
	public Map<Integer, String> getSpecialDis() {
		return specialDis;
	}
	public void setSpecialDis(Map<Integer, String> specialDis) {
		this.specialDis = specialDis;
	}
	public Map<Integer, String> getFreight() {
		return freight;
	}
	public void setFreight(Map<Integer, String> freight) {
		this.freight = freight;
	}
	public Map<Integer, String> getInsEndLimit() {
		return insEndLimit;
	}
	public void setInsEndLimit(Map<Integer, String> insEndLimit) {
		this.insEndLimit = insEndLimit;
	}
	public Map<Integer, String> getReceipt() {
		return receipt;
	}
	public void setReceipt(Map<Integer, String> receipt) {
		this.receipt = receipt;
	}
	public Map<Integer, String> getProduct() {
		return product;
	}
	public void setProduct(Map<Integer, String> product) {
		this.product = product;
	}
	public String getBroker() {
		return broker;
	}
	public void setBroker(String broker) {
		this.broker = broker;
	}
	public String getBrokerName() {
		return brokerName;
	}
	public void setBrokerName(String brokerName) {
		this.brokerName = brokerName;
	}
	public String getOpenCover() {
		return openCover;
	}
	public void setOpenCover(String openCover) {
		this.openCover = openCover;
	}
}
