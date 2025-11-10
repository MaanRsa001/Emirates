package com.maan.adminnew.branchMgt;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BranchBean {
	
	private String display;
	private String mode;
	private String branchNo;
	private String branchName;
	private String branchPrefix;
	private String address1;
	private String address2;
	private String address3;
	private String city;
	private String country;
	private String currency;
	private String phone;
	private String fax;
	private String email;
	private String status;
	private String effectiveDate;
	private String remarks;
	private String originCountry;
	private String destiCountry;
	private String decimalPlaces;
	private String lang;
	private File headerImage;
	private File footerImage;
	private File signImage;
	private File stampImage;
	private String headerImageFileName;
	private String headerImageContentType;
	private String footerImageFileName;
	private String footerImageContentType;
	private String signImageFileName;
	private String signImageContentType;
	private String stampImageFileName;
	private String stampImageContentType;
	
	private String tax;
	private List<Map<String, Object>> cityList = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> countryList;
	private List<Map<String, Object>> currrencyList;
	private String headerName;
	private String footerName;
	private String signImageName;
	private String stampImageName;
	private List<Map<String, Object>> branchList; 
	private String branchCode;
	// For Emirates
	private String belongingBranch;
	private List<Map<String, Object>> belongingBranchList; 

	public String getBranchNo() {
		return branchNo;
	}

	public void setBranchNo(String branchNo) {
		this.branchNo = branchNo;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getBranchPrefix() {
		return branchPrefix;
	}

	public void setBranchPrefix(String branchPrefix) {
		this.branchPrefix = branchPrefix;
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

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getOriginCountry() {
		return originCountry;
	}

	public void setOriginCountry(String originCountry) {
		this.originCountry = originCountry;
	}

	public String getDestiCountry() {
		return destiCountry;
	}

	public void setDestiCountry(String destiCountry) {
		this.destiCountry = destiCountry;
	}

	public String getDecimalPlaces() {
		return decimalPlaces;
	}

	public void setDecimalPlaces(String decimalPlaces) {
		this.decimalPlaces = decimalPlaces;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}
	public void setDisplay(String display) {
		this.display = display;
	}

	public String getDisplay() {
		return display;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getMode() {
		return mode;
	}
	
	public List<Map<String, Object>> getCountryList() {
		return countryList;
	}

	public void setCountryList(List<Map<String, Object>> countryList) {
		this.countryList = countryList;
	}

	public List<Map<String, Object>> getCurrrencyList() {
		return currrencyList;
	}

	public void setCurrrencyList(List<Map<String, Object>> currrencyList) {
		this.currrencyList = currrencyList;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

	public String getTax() {
		return tax;
	}
	public File getHeaderImage() {
		return headerImage;
	}

	public void setHeaderImage(File headerImage) {
		this.headerImage = headerImage;
	}

	public File getFooterImage() {
		return footerImage;
	}

	public void setFooterImage(File footerImage) {
		this.footerImage = footerImage;
	}

	public File getSignImage() {
		return signImage;
	}

	public void setSignImage(File signImage) {
		this.signImage = signImage;
	}

	public File getStampImage() {
		return stampImage;
	}

	public void setStampImage(File stampImage) {
		this.stampImage = stampImage;
	}
	public String getHeaderName() {
		return headerName;
	}

	public void setHeaderName(String headerName) {
		this.headerName = headerName;
	}

	public String getFooterName() {
		return footerName;
	}

	public void setFooterName(String footerName) {
		this.footerName = footerName;
	}

	public String getSignImageName() {
		return signImageName;
	}

	public void setSignImageName(String signImageName) {
		this.signImageName = signImageName;
	}

	public String getStampImageName() {
		return stampImageName;
	}

	public void setStampImageName(String stampImageName) {
		this.stampImageName = stampImageName;
	}

	public void setBranchList(List<Map<String, Object>> branchList) {
		this.branchList = branchList;
	}

	public List<Map<String, Object>> getBranchList() {
		return branchList;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setCityList(List<Map<String, Object>> cityList) {
		this.cityList = cityList;
	}

	public List<Map<String, Object>> getCityList() {
		return cityList;
	}

	public void setBelongingBranch(String belongingBranch) {
		this.belongingBranch = belongingBranch;
	}

	public String getBelongingBranch() {
		return belongingBranch;
	}

	public void setBelongingBranchList(List<Map<String, Object>> belongingBranchList) {
		this.belongingBranchList = belongingBranchList;
	}

	public List<Map<String, Object>> getBelongingBranchList() {
		return belongingBranchList;
	}
	public String getHeaderImageFileName() {
		return headerImageFileName;
	}

	public void setHeaderImageFileName(String headerImageFileName) {
		this.headerImageFileName = headerImageFileName;
	}

	public String getHeaderImageContentType() {
		return headerImageContentType;
	}

	public void setHeaderImageContentType(String headerImageContentType) {
		this.headerImageContentType = headerImageContentType;
	}

	public String getFooterImageFileName() {
		return footerImageFileName;
	}

	public void setFooterImageFileName(String footerImageFileName) {
		this.footerImageFileName = footerImageFileName;
	}

	public String getFooterImageContentType() {
		return footerImageContentType;
	}

	public void setFooterImageContentType(String footerImageContentType) {
		this.footerImageContentType = footerImageContentType;
	}

	public String getSignImageFileName() {
		return signImageFileName;
	}

	public void setSignImageFileName(String signImageFileName) {
		this.signImageFileName = signImageFileName;
	}

	public String getSignImageContentType() {
		return signImageContentType;
	}

	public void setSignImageContentType(String signImageContentType) {
		this.signImageContentType = signImageContentType;
	}

	public String getStampImageFileName() {
		return stampImageFileName;
	}

	public void setStampImageFileName(String stampImageFileName) {
		this.stampImageFileName = stampImageFileName;
	}

	public String getStampImageContentType() {
		return stampImageContentType;
	}

	public void setStampImageContentType(String stampImageContentType) {
		this.stampImageContentType = stampImageContentType;
	}

	
}
