package com.maan.claim;

import java.util.List;
import java.util.Map;

public class ClaimBean {

		private String policyNo;
		private String startDate;
		private String assuredName;
		private String address;
		private String saleTerm;
		private String lossDate;
		private String intimationDate;
		private String lossDescription;
		private String voyageFrom;
		private String voyageTo;
		private String modeOfTransport;
		private String modeOfCarriage;
		private String subjectMatterInsured;
		private String awbBillNumber;
		private String basicValuation;
		private String sumInsured;
		private String supplierName;
		private String invoiceNo;
		private String lcNumber;
		private String lcDate;
		private String subjMatter;
		private String commodityExcess;
		private String exchangeRate;
		private String inceptionDate;
		private String rdate;
		private String index="0";
		private String from1;
		private String reqFrom;
		private String remarks;
		private String coverName;
		private String currencyName;
		private List<String> clauseId;
		private List<String> warrentyId;
		private List<String> exclusionId;
		private List<String> warId;
		private List<Map<String, Object>>claimIntimation;
	
		public String getModeOfTransport() {
			return modeOfTransport;
		}
		public void setModeOfTransport(String modeOfTransport) {
			this.modeOfTransport = modeOfTransport;
		}
		public String getModeOfCarriage() {
			return modeOfCarriage;
		}
		public void setModeOfCarriage(String modeOfCarriage) {
			this.modeOfCarriage = modeOfCarriage;
		}
		public String getSubjectMatterInsured() {
			return subjectMatterInsured;
		}
		public void setSubjectMatterInsured(String subjectMatterInsured) {
			this.subjectMatterInsured = subjectMatterInsured;
		}
		public String getAwbBillNumber() {
			return awbBillNumber;
		}
		public void setAwbBillNumber(String awbBillNumber) {
			this.awbBillNumber = awbBillNumber;
		}
		public String getBasicValuation() {
			return basicValuation;
		}
		public void setBasicValuation(String basicValuation) {
			this.basicValuation = basicValuation;
		}
		public String getSumInsured() {
			return sumInsured;
		}
		public void setSumInsured(String sumInsured) {
			this.sumInsured = sumInsured;
		}
		public String getPolicyNo() {
			return policyNo;
		}
		public void setPolicyNo(String policyNo) {
			this.policyNo = policyNo;
		}
		public String getAssuredName() {
			return assuredName;
		}
		public void setAssuredName(String assuredName) {
			this.assuredName = assuredName;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getSaleTerm() {
			return saleTerm;
		}
		public void setSaleTerm(String saleTerm) {
			this.saleTerm = saleTerm;
		}
		
		public String getIntimationDate() {
			return intimationDate;
		}
		public void setIntimationDate(String intimationDate) {
			this.intimationDate = intimationDate;
		}
		public void setVoyageTo(String voyageTo) {
			this.voyageTo = voyageTo;
		}
		public String getVoyageTo() {
			return voyageTo;
		}
		public void setVoyageFrom(String voyageFrom) {
			this.voyageFrom = voyageFrom;
		}
		public String getVoyageFrom() {
			return voyageFrom;
		}
		public void setClauseId(List<String> list) {
			this.clauseId = list;
		}
		public List<String> getClauseId() {
			return clauseId;
		}
		public void setWarrentyId(List<String> warrentyId) {
			this.warrentyId = warrentyId;
		}
		public List<String> getWarrentyId() {
			return warrentyId;
		}
		public void setExclusionId(List<String> exclusionId) {
			this.exclusionId = exclusionId;
		}
		public List<String> getExclusionId() {
			return exclusionId;
		}
		public void setWarId(List<String> warId) {
			this.warId = warId;
		}
		public List<String> getWarId() {
			return warId;
		}
		public void setSupplierName(String supplierName) {
			this.supplierName = supplierName;
		}
		public String getSupplierName() {
			return supplierName;
		}
		public void setInvoiceNo(String invoiceNo) {
			this.invoiceNo = invoiceNo;
		}
		public String getInvoiceNo() {
			return invoiceNo;
		}
		public void setLcNumber(String lcNumber) {
			this.lcNumber = lcNumber;
		}
		public String getLcNumber() {
			return lcNumber;
		}
		public void setLcDate(String lcDate) {
			this.lcDate = lcDate;
		}
		public String getLcDate() {
			return lcDate;
		}
	
		public void setExchangeRate(String exchangeRate) {
			this.exchangeRate = exchangeRate;
		}
		public String getExchangeRate() {
			return exchangeRate;
		}
		public void setCommodityExcess(String commodityExcess) {
			this.commodityExcess = commodityExcess;
		}
		public String getCommodityExcess() {
			return commodityExcess;
		}
		public void setStartDate(String startDate) {
			this.startDate = startDate;
		}
		public String getStartDate() {
			return startDate;
		}
		public void setInceptionDate(String inceptionDate) {
			this.inceptionDate = inceptionDate;
		}
		public String getInceptionDate() {
			return inceptionDate;
		}
		public void setLossDate(String lossDate) {
			this.lossDate = lossDate;
		}
		public String getLossDate() {
			return lossDate;
		}
		public void setLossDescription(String lossDescription) {
			this.lossDescription = lossDescription;
		}
		public String getLossDescription() {
			return lossDescription;
		}
		public void setClaimIntimation(List<Map<String, Object>> claimIntimation) {
			this.claimIntimation = claimIntimation;
		}
		public List<Map<String, Object>> getClaimIntimation() {
			return claimIntimation;
		}
		public void setRdate(String rdate) {
			this.rdate = rdate;
		}
		public String getRdate() {
			return rdate;
		}
		public void setIndex(String index) {
			this.index = index;
		}
		public String getIndex() {
			return index;
		}
		public void setFrom1(String from1) {
			this.from1 = from1;
		}
		public String getFrom1() {
			return from1;
		}
		public void setReqFrom(String reqFrom) {
			this.reqFrom = reqFrom;
		}
		public String getReqFrom() {
			return reqFrom;
		}
		public void setRemarks(String remarks) {
			this.remarks = remarks;
		}
		public String getRemarks() {
			return remarks;
		}
		
		public void setSubjMatter(String subjMatter) {
			this.subjMatter = subjMatter;
		}
		public String getSubjMatter() {
			return subjMatter;
		}
		public void setCoverName(String coverName) {
			this.coverName = coverName;
		}
		public String getCoverName() {
			return coverName;
		}
		public void setCurrencyName(String currencyName) {
			this.currencyName = currencyName;
		}
		public String getCurrencyName() {
			return currencyName;
		}
}