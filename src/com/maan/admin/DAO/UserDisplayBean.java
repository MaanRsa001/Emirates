package com.maan.admin.DAO;

public class UserDisplayBean {

	private int sno;

	private String brokerName;

	private String userType;

	private String status;

	private String agencyCode;

	private String loginID;
	
	private String noOfUser;
	
	public String getBrokerName() {
		return brokerName;
	}

	public void setBrokerName(String brokerName) {
		this.brokerName = brokerName;
	}

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getAgencyCode() {
		return agencyCode;
	}

	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}

	public String getLoginID() {
		return loginID;
	}

	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}

	public String getNoOfUser() {
		return noOfUser;
	}

	public void setNoOfUser(String noOfUser) {
		this.noOfUser = noOfUser;
	}
}
