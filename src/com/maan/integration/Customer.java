package com.maan.integration;
import javax.xml.bind.annotation.*;

public class Customer {
	
	@XmlElement(name="CustCreditLimit")
	private String CustCreditLimit;
	@XmlElement(name="CustCode")
	private String CustCode;
	@XmlElement(name="CustName")
	private String  CustName;
	@XmlElement(name="CustCcCode")
	private String  CustCcCode;
	@XmlElement(name="CustMailing")
	private String  CustMailing;
	@XmlElement(name="CustResAddress1")
	private String  CustResAddress1;
	@XmlElement(name="CustResAddress2")
	private String  CustResAddress2;
	@XmlElement(name="CustOffAddress1")
	private String  CustOffAddress1;
	@XmlElement(name="CustCreditChkYN")
	private String  CustCreditChkYN;
	@XmlElement(name="CustDfltAssrYN")
	private String  CustDfltAssrYN;
	@XmlElement(name="CustLocalYN")
	private String  CustLocalYN;
	@XmlElement(name="CustEffFromDt")
	private String  CustEffFromDt;
	@XmlElement(name="CustRemarks")
	private String  CustRemarks; 
	@XmlElement(name="CustEffToDt")
	private String  CustEffToDt;
	@XmlElement(name="CustCrUid")
	private String  CustCrUid;
	@XmlElement(name="CustCrDt")
	private String  CustCrDt;
	@XmlElement(name="CustUpdUid")
	private String  CustUpdUid;
	@XmlElement(name="CustUpdDt")
	private String  CustUpdDt;
	@XmlElement(name="CustRateRI")
	private String  CustRateRI;
	@XmlElement(name="CustFrzFlage")
	private String  CustFrzFlage;
	@XmlElement(name="CustCreditBalance")
	private String  CustCreditBalance;
	@XmlElement(name="CustVatApplYn")
	private String CustVatApplYn;
	@XmlElement(name="CustVatRegNo")
	private String CustVatRegNo;
	

	public String getCustCreditLimit() {
		return CustCreditLimit;
	}
	public void setCustCreditLimit(String custCreditLimit) {
		CustCreditLimit = custCreditLimit;
	}
	public String getCustCode() {
		return CustCode;
	}
	public void setCustCode(String custCode) {
		CustCode = custCode;
	}
	public String getCustName() {
		return CustName;
	}
	public void setCustName(String custName) {
		CustName = custName;
	}
	public String getCustCcCode() {
		return CustCcCode;
	}
	public void setCustCcCode(String custCcCode) {
		CustCcCode = custCcCode;
	}
	public String getCustMailing() {
		return CustMailing;
	}
	public void setCustMailing(String custMailing) {
		CustMailing = custMailing;
	}
	public String getCustResAddress1() {
		return CustResAddress1;
	}
	public void setCustResAddress1(String custResAddress1) {
		CustResAddress1 = custResAddress1;
	}
	public String getCustResAddress2() {
		return CustResAddress2;
	}
	public void setCustResAddress2(String custResAddress2) {
		CustResAddress2 = custResAddress2;
	}
	public String getCustOffAddress1() {
		return CustOffAddress1;
	}
	public void setCustOffAddress1(String custOffAddress1) {
		CustOffAddress1 = custOffAddress1;
	}
	public String getCustCreditChkYN() {
		return CustCreditChkYN;
	}
	public void setCustCreditChkYN(String custCreditChkYN) {
		CustCreditChkYN = custCreditChkYN;
	}
	public String getCustDfltAssrYN() {
		return CustDfltAssrYN;
	}
	public void setCustDfltAssrYN(String custDfltAssrYN) {
		CustDfltAssrYN = custDfltAssrYN;
	}
	public String getCustLocalYN() {
		return CustLocalYN;
	}
	public void setCustLocalYN(String custLocalYN) {
		CustLocalYN = custLocalYN;
	}
	public String getCustEffFromDt() {
		return CustEffFromDt;
	}
	public void setCustEffFromDt(String custEffFromDt) {
		CustEffFromDt = custEffFromDt;
	}
	public String getCustRemarks() {
		return CustRemarks;
	}
	public void setCustRemarks(String custRemarks) {
		CustRemarks = custRemarks;
	}
	public String getCustEffToDt() {
		return CustEffToDt;
	}
	public void setCustEffToDt(String custEffToDt) {
		CustEffToDt = custEffToDt;
	}
	public String getCustCrUid() {
		return CustCrUid;
	}
	public void setCustCrUid(String custCrUid) {
		CustCrUid = custCrUid;
	}
	public String getCustCrDt() {
		return CustCrDt;
	}
	public void setCustCrDt(String custCrDt) {
		CustCrDt = custCrDt;
	}
	public String getCustUpdUid() {
		return CustUpdUid;
	}
	public void setCustUpdUid(String custUpdUid) {
		CustUpdUid = custUpdUid;
	}
	public String getCustUpdDt() {
		return CustUpdDt;
	}
	public void setCustUpdDt(String custUpdDt) {
		CustUpdDt = custUpdDt;
	}
	public String getCustRateRI() {
		return CustRateRI;
	}
	public void setCustRateRI(String custRateRI) {
		CustRateRI = custRateRI;
	}
	public void setCustCreditBalance(String custCreditBalance) {
		CustCreditBalance = custCreditBalance;
	}
	public String getCustCreditBalance() {
		return CustCreditBalance;
	}
	public void setCustFrzFlage(String custFrzFlage) {
		CustFrzFlage = custFrzFlage;
	}
	public String getCustFrzFlage() {
		return CustFrzFlage;
	}
	public void setCustVatApplYn(String custVatApplYn) {
		CustVatApplYn = custVatApplYn;
	}
	public String getCustVatApplYn() {
		return CustVatApplYn;
	}
	public void setCustVatRegNo(String custVatRegNo) {
		CustVatRegNo = custVatRegNo;
	}
	public String getCustVatRegNo() {
		return CustVatRegNo;
	}

}
