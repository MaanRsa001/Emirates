package com.maan.adminnew.report;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReportBean {
	private String agencyCode;
	private String borganization;
	private String bcode;
	private String firstname;
	private String uagencyCode;
	private String mode;
	private String from;
	private String productID;
	private String reqFrom;
	private String from1;
	private String broker;
	private String proposalNo;
	private String policynumber;
	private String docType;
	private String endtstatus;
	private String docNo;
	private String amendId;
	private String loginId;
	private String startDate;
	private String endDate;
	private String startDate1;
	private String endDate1;
	private String utype;
	private String mode1;
	private String index="0";
	private String searchBy;
	private String searchValue;
	private String commodity;
	private String busType;
	private String orginCountry;
	private String destCountry;
	private String[] orginCountries;
	private String[] destCountries;
	private String transportId;
	private String coverId;
	private String[] rag;
	private String[] brokers;
	private String rags;
	private String reportStatus;
	private String branch;
	private String effDate;
	private String vesselName;
	private String tranId;
	private String loginBranch;
	private String fileName;
	private String downloadType;
	private String applicationNo;
	private String quoteNo;
	private String integrationStatus;
	private String[] multiIntegrate;
	
	List<Map<String, Object>> countrySmartList=new ArrayList<Map<String, Object>>();

	public String getEffDate() {
		return effDate;
	}
	public void setEffDate(String effDate) {
		this.effDate = effDate;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getReportStatus() {
		return reportStatus;
	}
	public void setReportStatus(String reportStatus) {
		this.reportStatus = reportStatus;
	}
	public String getFrom1() {
		return from1;
	}
	public void setFrom1(String from1) {
		this.from1 = from1;
	}
	public String getBorganization() {
		return borganization;
	}
	public void setBorganization(String borganization) {
		this.borganization = borganization;
	}
	public String getBcode() {
		return bcode;
	}
	public void setBcode(String bcode) {
		this.bcode = bcode;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getAgencyCode() {
		return agencyCode;
	}
	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
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
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
	public String getReqFrom() {
		return reqFrom;
	}
	public void setReqFrom(String reqFrom) {
		this.reqFrom = reqFrom;
	}
	public String getBroker() {
		return broker;
	}
	public void setBroker(String broker) {
		this.broker = broker;
	}
	public String getProposalNo() {
		return proposalNo;
	}
	public void setProposalNo(String proposalNo) {
		this.proposalNo = proposalNo;
	}
	public String getPolicynumber() {
		return policynumber;
	}
	public void setPolicynumber(String policynumber) {
		this.policynumber = policynumber;
	}
	public String getDocType() {
		return docType;
	}
	public void setDocType(String docType) {
		this.docType = docType;
	}
	public String getEndtstatus() {
		return endtstatus;
	}
	public void setEndtstatus(String endtstatus) {
		this.endtstatus = endtstatus;
	}
	public String getDocNo() {
		return docNo;
	}
	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}
	public String getAmendId() {
		return amendId;
	}
	public void setAmendId(String amendId) {
		this.amendId = amendId;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getStartDate1() {
		return startDate1;
	}
	public void setStartDate1(String startDate1) {
		this.startDate1 = startDate1;
	}
	public String getEndDate1() {
		return endDate1;
	}
	public void setEndDate1(String endDate1) {
		this.endDate1 = endDate1;
	}
	public String getUtype() {
		return utype;
	}
	public void setUtype(String utype) {
		this.utype = utype;
	}
	public String getMode1() {
		return mode1;
	}
	public void setMode1(String mode1) {
		this.mode1 = mode1;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
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
	public String getCommodity() {
		return commodity;
	}
	public void setCommodity(String commodity) {
		this.commodity = commodity;
	}
	public String getBusType() {
		return busType;
	}
	public void setBusType(String busType) {
		this.busType = busType;
	}
	public String getOrginCountry() {
		return orginCountry;
	}
	public void setOrginCountry(String orginCountry) {
		this.orginCountry = orginCountry;
	}
	public String getDestCountry() {
		return destCountry;
	}
	public void setDestCountry(String destCountry) {
		this.destCountry = destCountry;
	}
	public String getTransportId() {
		return transportId;
	}
	public void setTransportId(String transportId) {
		this.transportId = transportId;
	}
	public String getCoverId() {
		return coverId;
	}
	public void setCoverId(String coverId) {
		this.coverId = coverId;
	}
	public String[] getRag(){
		return rag;
	}
	public void setRag(String[] rag) {
		this.rag = rag;
	}
	public String getRags() {
		return rags;
	}
	public void setRags(String rags) {
		this.rags = rags;
	}
	public String getVesselName() {
		return vesselName;
	}
	public void setVesselName(String vesselName) {
		this.vesselName = vesselName;
	}
	public String getTranId() {
		return tranId;
	}
	public void setTranId(String tranId) {
		this.tranId = tranId;
	}
	public String getLoginBranch() {
		return loginBranch;
	}
	public void setLoginBranch(String loginBranch) {
		this.loginBranch = loginBranch;
	}
	public String[] getBrokers() {
		return brokers;
	}
	public void setBrokers(String[] brokers) {
		this.brokers = brokers;
	}
	public List<Map<String, Object>> getCountrySmartList() {
		return countrySmartList;
	}
	public void setCountrySmartList(List<Map<String, Object>> countrySmartList) {
		this.countrySmartList = countrySmartList;
	}
	public String[] getOrginCountries() {
		return orginCountries;
	}
	public String[] getDestCountries() {
		return destCountries;
	}
	public void setOrginCountries(String[] orginCountries) {
		this.orginCountries = orginCountries;
	}
	public void setDestCountries(String[] destCountries) {
		this.destCountries = destCountries;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getDownloadType() {
		return downloadType;
	}
	public void setDownloadType(String downloadType) {
		this.downloadType = downloadType;
	}
	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}
	public String getApplicationNo() {
		return applicationNo;
	}
	public void setQuoteNo(String quoteNo) {
		this.quoteNo = quoteNo;
	}
	public String getQuoteNo() {
		return quoteNo;
	}
	public void setIntegrationStatus(String integrationStatus) {
		this.integrationStatus = integrationStatus;
	}
	public String getIntegrationStatus() {
		return integrationStatus;
	}
	public void setMultiIntegrate(String[] multiIntegrate) {
		this.multiIntegrate = multiIntegrate;
	}
	public String[] getMultiIntegrate() {
		return multiIntegrate;
	}
	
}
