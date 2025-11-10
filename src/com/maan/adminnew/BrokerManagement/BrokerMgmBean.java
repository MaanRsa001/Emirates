package com.maan.adminnew.BrokerManagement;

import java.io.File;

public class BrokerMgmBean {
	private String agencyCode;
	private String mode;
	private String customerName;
	private String agencyName;
	private String branch;
	private String nameAndCode;
	private String approvedby;
	private String bcode;
	private String borganization;
	private String address1;
	private String address2;
	private String country;
	private String pobox;
	private String telephone;
	private String missippiId;
	private String fax;
	private String emirate;
	private String othercity;
	private String title;
	private String gender;
	private String firstname;
	private String lastname;
	private String nationality;
	private String dob;
	private String occupation;
	private String mobile;
	private String bemail;
	private String executive;
	private String CIMSNO;
	private String ARACC;
	private String login_Id;
	private String policy_fee;
	private String policFee;
	private String gov_fee;
	private String govtTax;
	private String emer_fee;
	private String emergencyfund;
	private String app_for;
	private String effecdate;
	private String companyName;
	private String status;
	private String searchBy;
	private String searchValue;
	private String reqFrom;
	private String newpassword;
	private String repassword;
	private String display;
	private String display1;
	private String entryDate;
	private boolean prostatus;
	private String productName;
	private String company_Id;
	private String fval;
	private int broker_Code;
	private int customer_id;
	private String loginid;
	private String password;
	private String rpassword;
	private String productID;
	private String validNcheck="";
	private String countryName;
	/*private Map<Integer, String> commission=new HashMap<Integer, String>();
	private Map<Integer, String> insurance_End_Limit=new HashMap<Integer, String>();
	private Map<Integer, String> min_Premium_Amount=new HashMap<Integer, String>();
	private Map<Integer, String> loadingPremium=new HashMap<Integer, String>();
	private Map<Integer, String> discountPremium=new HashMap<Integer, String>();
	private Map<Integer, String> back_Date_Allowed=new HashMap<Integer, String>();
	private Map<Integer, Boolean> productStatus=new HashMap<Integer, Boolean>();
	private Map<Integer, String> product_id=new HashMap<Integer, String>();
	private Map<Integer, String> user_Id_Creation=new HashMap<Integer, String>();
	private Map<Integer, String> remark=new HashMap<Integer, String>();
	private Map<Integer, String> freight=new HashMap<Integer, String>();
	private Map<Integer, String> provision=new HashMap<Integer, String>();*/
	private String commission;
	private String insurance_End_Limit;
	private String min_Premium_Amount;
	private String loadingPremium;
	private String discountPremium;
	private String back_Date_Allowed;
	private String productStatus;
	private String product_id;
	private String user_Id_Creation;
	private String remark;
	private String freight;
	private String provision;
	private String payReceipt;
	private String index="0";
	private String mode1;
	private String nationalityNa;
	private String brcode;
	private String oneOffCommission;
	private String openCoverCommission;
	
	private File broImgUpload;
	private String broImgName;
	private String broImgContentType;
	
	private File upload;
	private String uploadFileName;
	private String uploadContentType;
	private String vatRegNo;
	
	public String getAgencyCode() {
		return agencyCode;
	}
	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getAgencyName() {
		return agencyName;
	}
	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getNameAndCode() {
		return nameAndCode;
	}
	public void setNameAndCode(String nameAndCode) {
		this.nameAndCode = nameAndCode;
	}
	public String getApprovedby() {
		return approvedby;
	}
	public void setApprovedby(String approvedby) {
		this.approvedby = approvedby;
	}
	public String getBcode() {
		return bcode;
	}
	public void setBcode(String bcode) {
		this.bcode = bcode;
	}
	public String getBorganization() {
		return borganization;
	}
	public void setBorganization(String borganization) {
		this.borganization = borganization;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPobox() {
		return pobox;
	}
	public void setPobox(String pobox) {
		this.pobox = pobox;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getMissippiId() {
		return missippiId;
	}
	public void setMissippiId(String missippiId) {
		this.missippiId = missippiId;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getEmirate() {
		return emirate;
	}
	public void setEmirate(String emirate) {
		this.emirate = emirate;
	}
	public String getOthercity() {
		return othercity;
	}
	public void setOthercity(String othercity) {
		this.othercity = othercity;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getBemail() {
		return bemail;
	}
	public void setBemail(String bemail) {
		this.bemail = bemail;
	}
	public String getExecutive() {
		return executive;
	}
	public void setExecutive(String executive) {
		this.executive = executive;
	}
	public String getCIMSNO() {
		return CIMSNO;
	}
	public void setCIMSNO(String cIMSNO) {
		CIMSNO = cIMSNO;
	}
	public String getARACC() {
		return ARACC;
	}
	public void setARACC(String aRACC) {
		ARACC = aRACC;
	}
	public String getLogin_Id() {
		return login_Id;
	}
	public void setLogin_Id(String loginId) {
		login_Id = loginId;
	}
	public String getPolicy_fee() {
		return policy_fee;
	}
	public void setPolicy_fee(String policyFee) {
		policy_fee = policyFee;
	}
	public String getPolicFee() {
		return policFee;
	}
	public void setPolicFee(String policFee) {
		this.policFee = policFee;
	}
	public String getGov_fee() {
		return gov_fee;
	}
	public void setGov_fee(String govFee) {
		gov_fee = govFee;
	}
	public String getGovtTax() {
		return govtTax;
	}
	public void setGovtTax(String govtTax) {
		this.govtTax = govtTax;
	}
	public String getEmer_fee() {
		return emer_fee;
	}
	public void setEmer_fee(String emerFee) {
		emer_fee = emerFee;
	}
	public String getEmergencyfund() {
		return emergencyfund;
	}
	public void setEmergencyfund(String emergencyfund) {
		this.emergencyfund = emergencyfund;
	}
	public String getApp_for() {
		return app_for;
	}
	public void setApp_for(String appFor) {
		app_for = appFor;
	}
	public String getEffecdate() {
		return effecdate;
	}
	public void setEffecdate(String effecdate) {
		this.effecdate = effecdate;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getNewpassword() {
		return newpassword;
	}
	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
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
	public String getDisplay1() {
		return display1;
	}
	public void setDisplay1(String display1) {
		this.display1 = display1;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public boolean isProstatus() {
		return prostatus;
	}
	public void setProstatus(boolean prostatus) {
		this.prostatus = prostatus;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getCompany_Id() {
		return company_Id;
	}
	public void setCompany_Id(String companyId) {
		company_Id = companyId;
	}
	public String getFval() {
		return fval;
	}
	public void setFval(String fval) {
		this.fval = fval;
	}
	public int getBroker_Code() {
		return broker_Code;
	}
	public void setBroker_Code(int brokerCode) {
		broker_Code = brokerCode;
	}
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customerId) {
		customer_id = customerId;
	}
	public String getLoginid() {
		return loginid;
	}
	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
	public String getValidNcheck() {
		return validNcheck;
	}
	public void setValidNcheck(String validNcheck) {
		this.validNcheck = validNcheck;
	}
	/*public Map<Integer, String> getCommission() {
		return commission;
	}
	public void setCommission(Map<Integer, String> commission) {
		this.commission = commission;
	}
	public Map<Integer, String> getInsurance_End_Limit() {
		return insurance_End_Limit;
	}
	public void setInsurance_End_Limit(Map<Integer, String> insuranceEndLimit) {
		insurance_End_Limit = insuranceEndLimit;
	}
	public Map<Integer, String> getMin_Premium_Amount() {
		return min_Premium_Amount;
	}
	public void setMin_Premium_Amount(Map<Integer, String> minPremiumAmount) {
		min_Premium_Amount = minPremiumAmount;
	}
	public Map<Integer, String> getLoadingPremium() {
		return loadingPremium;
	}
	public void setLoadingPremium(Map<Integer, String> loadingPremium) {
		this.loadingPremium = loadingPremium;
	}
	public Map<Integer, String> getDiscountPremium() {
		return discountPremium;
	}
	public void setDiscountPremium(Map<Integer, String> discountPremium) {
		this.discountPremium = discountPremium;
	}
	public Map<Integer, String> getBack_Date_Allowed() {
		return back_Date_Allowed;
	}
	public void setBack_Date_Allowed(Map<Integer, String> backDateAllowed) {
		back_Date_Allowed = backDateAllowed;
	}
	public Map<Integer, Boolean> getProductStatus() {
		return productStatus;
	}
	public void setProductStatus(Map<Integer, Boolean> productStatus) {
		this.productStatus = productStatus;
	}
	public Map<Integer, String> getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Map<Integer, String> productId) {
		product_id = productId;
	}
	public Map<Integer, String> getUser_Id_Creation() {
		return user_Id_Creation;
	}
	public void setUser_Id_Creation(Map<Integer, String> userIdCreation) {
		user_Id_Creation = userIdCreation;
	}
	public Map<Integer, String> getRemark() {
		return remark;
	}
	public void setRemark(Map<Integer, String> remark) {
		this.remark = remark;
	}
	public Map<Integer, String> getFreight() {
		return freight;
	}
	public void setFreight(Map<Integer, String> freight) {
		this.freight = freight;
	}
	public Map<Integer, String> getProvision() {
		return provision;
	}
	public void setProvision(Map<Integer, String> provision) {
		this.provision = provision;
	}*/
	public String getCommission() {
		return commission;
	}
	public void setCommission(String commission) {
		this.commission = commission;
	}
	public String getInsurance_End_Limit() {
		return insurance_End_Limit;
	}
	public void setInsurance_End_Limit(String insuranceEndLimit) {
		insurance_End_Limit = insuranceEndLimit;
	}
	public String getMin_Premium_Amount() {
		return min_Premium_Amount;
	}
	public void setMin_Premium_Amount(String minPremiumAmount) {
		min_Premium_Amount = minPremiumAmount;
	}
	public String getLoadingPremium() {
		return loadingPremium;
	}
	public void setLoadingPremium(String loadingPremium) {
		this.loadingPremium = loadingPremium;
	}
	public String getDiscountPremium() {
		return discountPremium;
	}
	public void setDiscountPremium(String discountPremium) {
		this.discountPremium = discountPremium;
	}
	public String getBack_Date_Allowed() {
		return back_Date_Allowed;
	}
	public void setBack_Date_Allowed(String backDateAllowed) {
		back_Date_Allowed = backDateAllowed;
	}
	public String getProductStatus() {
		return productStatus;
	}
	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String productId) {
		product_id = productId;
	}
	public String getUser_Id_Creation() {
		return user_Id_Creation;
	}
	public void setUser_Id_Creation(String userIdCreation) {
		user_Id_Creation = userIdCreation;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getFreight() {
		return freight;
	}
	public void setFreight(String freight) {
		this.freight = freight;
	}
	public String getProvision() {
		return provision;
	}
	public void setProvision(String provision) {
		this.provision = provision;
	}
	public String getPayReceipt() {
		return payReceipt;
	}
	public void setPayReceipt(String payReceipt) {
		this.payReceipt = payReceipt;
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
	public String getRpassword() {
		return rpassword;
	}
	public void setRpassword(String rpassword) {
		this.rpassword = rpassword;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getNationalityNa() {
		return nationalityNa;
	}
	public void setNationalityNa(String nationalityNa) {
		this.nationalityNa = nationalityNa;
	}
	public String getBrcode() {
		return brcode;
	}
	public void setBrcode(String brcode) {
		this.brcode = brcode;
	}
	public String getOneOffCommission() {
		return oneOffCommission;
	}
	public void setOneOffCommission(String oneOffCommission) {
		this.oneOffCommission = oneOffCommission;
	}
	public String getOpenCoverCommission() {
		return openCoverCommission;
	}
	public void setOpenCoverCommission(String openCoverCommission) {
		this.openCoverCommission = openCoverCommission;
	}
	public void setBroImgUpload(File broImgUpload) {
		this.broImgUpload = broImgUpload;
	}
	public File getBroImgUpload() {
		return broImgUpload;
	}
	public void setBroImgName(String broImgName) {
		this.broImgName = broImgName;
	}
	public String getBroImgName() {
		return broImgName;
	}
	public void setBroImgContentType(String broImgContentType) {
		this.broImgContentType = broImgContentType;
	}
	public String getBroImgContentType() {
		return broImgContentType;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public File getUpload() {
		return upload;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	public String getUploadContentType() {
		return uploadContentType;
	}
	public void setVatRegNo(String vatRegNo) {
		this.vatRegNo = vatRegNo;
	}
	public String getVatRegNo() {
		return vatRegNo;
	}
}
