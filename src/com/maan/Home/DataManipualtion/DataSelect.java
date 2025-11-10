/**
 *
 *  Author  : Rajesh R  [16/08/2007]
 *	Company : MaanSarovar Software Private Limited  Chennai-1
 *	Project : E-Cargo
 *  Purpose : This Bean To ManipulateSELECT The Date
 *
 */
package com.maan.Home.DataManipualtion;

import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.logging.log4j.Logger;

import com.maan.common.LogUtil;
import com.maan.common.exception.BaseException;
import com.maan.services.util.ValidationFormat;
import com.maan.services.util.runner;

public class DataSelect 
{
	private static final Logger logger = LogUtil.getLogger(DataSelect.class);
	private PrintWriter out = null;
	private int staffSize = 0;
	public final static long SECOND_FACTOR = 1000;
	public final static long MINUTE_FACTOR = SECOND_FACTOR * 60;
	public final static long HOUR_FACTOR = MINUTE_FACTOR * 60;
	public final static long DAY_FACTOR = HOUR_FACTOR * 24;
	public final static long YEAR_FACTOR = DAY_FACTOR * 365;
	final static transient private String NULL = "null";
	final static transient private String qno = "quoteno";
	final static transient private String QNO = "QuoteNo";
	final static transient private String qNO = "quoteNo";
	final static transient private String SELECTS = "select";
	final static transient private String INVALID = "Invalid";
	final static transient private String PIDSQL = " and a.product_id in(";
	final static transient private String ENTER = "- Enter";
	final static transient private String EXIT = "- Exit";
	final static transient private String PROID = "proId";
	final static transient private String LOGINSQL = "select login_id from login_master where agency_code=(select agency_code from login_master where login_id=?) and login_id not in('NONE','NON')";
	
	public long maximumLimit;
	private String source = "";
	private String otherSource = "";
	private String[][] brokerLoginIds;
	private String cid;
	private String fleetNo;
	private boolean fleetStatus;
	
	public boolean isFleetStatus() {
		return fleetStatus;
	}

	public void setFleetStatus(boolean fleetStatus) {
		this.fleetStatus = fleetStatus;
	}

	public String getFleetNo() {
		return fleetNo;
	}

	public void setFleetNo(String fleetNo) {
		this.fleetNo = fleetNo;
	}
	public void setOut(final PrintWriter out1) {
		this.out = out1;
	}
	public PrintWriter getOut() {
		return out;
	}
	public void setMaximumLimit(final long maximumLimit) {
		this.maximumLimit = maximumLimit;
	}
	public long getMaximumLimit() {
		return this.maximumLimit;
	}
	public void setSource(final String source) {
		this.source = source;
	}
	public String getSource() {
		return this.source;
	}
	public void setOtherSource(final String otherSource) {
		this.otherSource = otherSource;
	}
	public String getOtherSource() {
		return this.otherSource;
	}
	public void setBrokerLoginIds(final String[][] brokerLoginIds) {
		this.brokerLoginIds = (String[][])brokerLoginIds.clone();
	}
	public String[][] getBrokerLoginIds() {
		return (String[][])brokerLoginIds.clone();
	}
	private String title = "";
	private String gender = "";
	private String firstName = "";
	private String lastName = "";
	private String nationality = "";
	private String dobDay = "";
	private String dobMonth = "";
	private String dobYear = "";
	private String telephone = "";
	private String mobile = "";
	private String fax = "";
	private String email = "";
	private String address1 = "";
	private String address2 = "";
	private String occupation = "";
	private String emirate = "";
	private String country = "";
	private String poBox = "";
	private String orgName = "";
	private String quoteNo = "";
	private String insuredlocation = "";

	public void setTitle(final String title) {
		this.title = title;
	}
	public void setGender(final String gender) {
		this.gender = gender;
	}
	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}
	public void setNationality(final String nationality) {
		this.nationality = nationality;
	}
	public void setDobDay(final String dobDay) {
		this.dobDay = dobDay;
	}
	public void setDobMonth(final String dobMonth) {
		this.dobMonth = dobMonth;
	}
	public void setDobYear(final String dobYear) {
		this.dobYear = dobYear;
	}
	public void setTelephone(final String telephone) {
		this.telephone = telephone;
	}
	public void setMobile(final String mobile) {
		this.mobile = mobile;
	}
	public void setFax(final String fax) {
		this.fax = fax;
	}
	public void setEmail(final String email) {
		this.email = email;
	}
	public void setAddress1(final String address1) {
		this.address1 = address1;
	}
	public void setAddress2(final String address2) {
		this.address2 = address2;
	}
	public void setOccupation(final String occupation) {
		this.occupation = occupation;
	}
	public void setEmirate(final String emirate) {
		this.emirate = emirate;
	}
	public void setCountry(final String country) {
		this.country = country;
	}
	public void setPoBox(final String poBox) {
		this.poBox = poBox;
	}
	public void setOrgName(final String orgName) {
		this.orgName = orgName;
	}
	public void setQuoteNo(final String quoteNo) {
		this.quoteNo = quoteNo;
	}
	// Getter Method
	public String getTitle() {
		return this.title;
	}
	public String getGender() {
		return this.gender;
	}
	public String getFirstName() {
		return this.firstName;
	}
	public String getLastName() {
		return this.lastName;
	}
	public String getNationality() {
		return this.nationality;
	}
	public String getDobDay() {
		return this.dobDay;
	}
	public String getDobMonth() {
		return this.dobMonth;
	}
	public String getDobYear() {
		return this.dobYear;
	}
	public String getTelephone() {
		return this.telephone;
	}
	public String getMobile() {
		return this.mobile;
	}
	public String getFax() {
		return this.fax;
	}
	public String getEmail() {
		return this.email;
	}
	public String getAddress1() {
		return address1;
	}
	public String getAddress2() {
		return this.address2;
	}
	public String getOccupation() {
		return this.occupation;
	}
	public String getEmirate() {
		return this.emirate;
	}
	public String getCountry() {
		return this.country;
	}
	public String getPoBox() {
		return this.poBox;
	}
	public String getOrgName() {
		return this.orgName;
	}
	public String getQuoteNo() {
		return this.quoteNo;
	}
	public void setInsuredlocation(final String location) {
		insuredlocation = location;
	}
	public String getInsuredlocation() {
		return insuredlocation;
	}

	private String homebuilt = "";
	private String pvtacmation = "";
	private String rclimeland = "";
	private String consdays = "";
	private String conditions = "";
	private String pastthryrs = "";
	private String claim = "";
	private String claimtype = "";
	private String claimamt = "";
	private String property = "";

	public void setHomebuilt(final String homebuilt) {
		this.homebuilt = homebuilt;
	}
	public String getHomebuilt() {
		return homebuilt;
	}
	public void setPvtacmation(final String pvtacmation) {
		this.pvtacmation = pvtacmation;
	}
	public String getPvtacmation() {
		return pvtacmation;
	}
	public void setRclimeland(final String rclimeland) {
		this.rclimeland = rclimeland;
	}
	public String getRclimeland() {
		return rclimeland;
	}
	public void setConsdays(final String consdays) {
		this.consdays = consdays;
	}
	public String getConsdays() {
		return consdays;
	}
	public void setConditions(final String conditions) {
		this.conditions = conditions;
	}
	public String getConditions() {
		return conditions;
	}
	public void setPastthryrs(final String pastthryrs) {
		this.pastthryrs = pastthryrs;
	}
	public String getPastthryrs() {
		return pastthryrs;
	}
	public void setClaim(final String claim) {
		this.claim = claim;
	}
	public String getClaim() {
		return claim;
	}
	public void setClaimtype(final String claimtype) {
		this.claimtype = claimtype;
	}
	public String getClaimtype() {
		return claimtype;
	}
	public void setClaimamt(final String claimamt) {
		this.claimamt = claimamt;
	}
	public String getClaimamt() {
		return claimamt;
	}
	public void setProperty(final String property) {
		this.property = property;
	}
	public String getProperty() {
		return this.property;
	}
	public void setStaffSize(final int staffSize) {
		this.staffSize = staffSize;
	}
	public int getStaffSize() {
		return staffSize;
	}

	transient private String yea[] = new String[staffSize];
	transient private String mon[] = new String[staffSize];
	transient private String dob[] = new String[staffSize];
	transient private String name[] = new String[staffSize];

	public void setName(final String[] name) {
		// name = new String[getStaffSize()];
		this.name = (String[])name.clone();
	}
	public String[] getName() {
		return (String[])name.clone();
	}
	public void setDob(final String[] dob) {
		// dob = new String[getStaffSize()];
		this.dob = (String[])dob.clone();
	}
	public String[] getDob() {
		return (String[])dob.clone();
	}
	public void setMon(final String[] mon) {
		// mon = new String[getStaffSize()];
		this.mon = (String[])mon.clone();
	}
	public String[] getMon() {
		return (String[])mon.clone();
	}
	public void setYear(final String[] yea) {
		// yea = new String[getStaffSize()];
		this.yea = (String[])yea.clone();
	}
	public String[] getYear() {
		return (String[])yea.clone();
	}

	public String[][] getHomeCoverageTransactionDetails(final String quoteNo)throws BaseException {
		logger.info("getHomeCoverageTransactionDetails method()");
		logger.debug(ENTER);
		final String args[] = {quoteNo};
		final String result[][] = runner.multipleSelection("select * from HOME_COVERAGE_TRANSACTION where quote_no=? and amend_id='0' order by HOME_SNO", args);
		logger.debug(EXIT);
		
		return result;
	}

	public String[][] getHomePositionMasterDetails(final String quoteNo)throws BaseException {
		logger.info("getHomePositionMasterDetails method()");
		logger.debug(ENTER);
		final String args[] = {quoteNo};
		final String result[][] = runner.multipleSelection("select to_char(effective_date,'DD'),to_char(effective_date,'MM'),to_char(effective_date,'YYYY'),to_char(EXPIRY_DATE,'DD'),to_char(EXPIRY_DATE,'MM'),to_char(EXPIRY_DATE,'YYYY'),premium,product_id from home_position_master where quote_no=?", args);
		logger.debug(EXIT);
		
		return result;
	}

	public String[][] getHomeCoverageBuildingDetails(final String quoteNo)throws BaseException {
		logger.info("getHomeCoverageBuildingDetails method()");
		logger.debug(ENTER);
		final String args[] = {quoteNo};
		final String result[][] = runner.multipleSelection("select BUILDING_NAME,STREET_NAME,FLAT_VILLA_NO,EMIRATE from HOME_COVERAGE_BUILDING where QUOTE_NO=? and COVER_ID=1", args);
		logger.debug(EXIT);
		
		return result;
	}

	public String[][] getFinanceDetails(final String quoteNo)throws BaseException {
		logger.info("getFinanceDetails method()");
		logger.debug(ENTER);
		final String args[] = {quoteNo};
		final String result[][] = runner.multipleSelection("select FINANCE_COMPANY_NAME,FINANCE_TELEPHONE,LOAN_AMOUNT,POBOX from HOME_COVERAGE_BUILDING where QUOTE_NO=? and COVER_ID not in(1)", args);
		logger.debug(EXIT);
		
		return result;
	}
	
	public String[][] homeCoverageSelect123(final String quoteNo)throws BaseException {
		logger.info("homeCoverageSelect123 method()");
		logger.debug(ENTER);
		final String args[] = {quoteNo};
		final String result[][] = runner.multipleSelection("select * from HOME_COVERAGE_TRANSACTION where QUOTE_NO=? order by home_sno", args);
		logger.debug(EXIT);
		
		return result;
	}
	
	public String homeCoverageSelect125(final String quoteNo)throws BaseException {
		logger.info("homeCoverageSelect125 method()");
		logger.debug(ENTER);
		final String args[] = {quoteNo};
		final String result1 = runner.singleSelection("select nvl(premium,'0') from home_position_master where quote_no=?", args);
		logger.debug(EXIT);
		
		return result1;
	}
	
	public String homeCoverageSelectNakheel(final String quoteNo)throws BaseException {
		logger.info("homeCoverageSelectNakheel method()");
		logger.debug(ENTER);
		final String args[] = {quoteNo};
		final String result1 = runner.singleSelection("select sum(nvl(premium,0)) from HOME_COVERAGE_TRANSACTION where quote_no=?", args);
		logger.debug(EXIT);
		
		return result1;
	}
	
	public String[][] getTotalHomeCoverageTransactionDetails(final String quoteNo, final String coverId) throws BaseException{
		logger.info("getTotalHomeCoverageTransactionDetails method()");
		logger.debug(ENTER);
		final String args[] = {quoteNo,coverId};
		final String result[][] = runner.multipleSelection("select * from HOME_COVERAGE_TRANSACTION where QUOTE_NO=? and COVER_ID=? and amend_id not in(0)", args);
		logger.debug(EXIT);
		
		return result;
	}
	/* QuoteNo Generation And Policy No Generation And Debit Note No Generation */
	public String getTypeId(final String pid, final String desc, final String branchCode)throws BaseException {
		logger.info("getTypeId method()");
		logger.debug(ENTER);
		String branch;
		if (branchCode.length() > 0){
			branch = " and BRANCH_CODE='" + branchCode + "'";
		}
		else{
			branch = " and BRANCH_CODE is not null";
		}
		final String args[] = {desc.toLowerCase(Locale.US)};
		final String result = runner.singleSelection("select TYPE_ID from POLICYNO_GENERATE where PRODUCT_ID='21' and lower(DESCRIPTION)=?"+ branch, args);
		logger.debug(EXIT);
		
		return result;
	}
	public String getMaxQuote(final String productTypeId, final String loginBra)throws BaseException {
		logger.info("Data Select getMaxQuote method()");
		logger.debug(ENTER);
		String sql;
		sql = "select nvl(max(current_no)+1,max(start_no)) from policyno_generate where type_id=(select QUOTE_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE=? and PRODUCT_ID=?) and status='Y' and amend_id=(select max(amend_id) from policyno_generate " +
				"where type_id=(select QUOTE_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE=? and PRODUCT_ID=?) and branch_code=?) and branch_code=? and PRODUCT_ID=?";
		final String args[] = {loginBra,productTypeId,loginBra,productTypeId,loginBra,loginBra,productTypeId};
		final String current_no = runner.singleSelection(sql, args);
		sql = "update policyno_generate set current_no=?,remarks=? where type_id=(select QUOTE_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE=? and PRODUCT_ID=?) and status='Y' and amend_id=(select max(amend_id) from policyno_generate where " +
				"type_id=(select QUOTE_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE=? and PRODUCT_ID=?) and branch_code=?) and branch_code=? and PRODUCT_ID=?";
		final String args1[] = {current_no,current_no,loginBra,productTypeId,loginBra,productTypeId,loginBra,loginBra,productTypeId};
		runner.multipleUpdation(sql, args1);
		logger.debug(EXIT);
		
		return current_no;
	}
	// Debit Note no Based on Branch wise
	public String getMaxDebitNo(final String qno, final String pid, final String loginBra)throws BaseException {
		logger.info("getMaxDebitNo method()");
		logger.debug(ENTER);
		final String branchCode = loginBra;
		final String debiSql = "select nvl(max(current_no)+1,max(start_no)) from policyno_generate where type_id=(select DEBIT_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE=? and PRODUCT_ID=?) and status='Y'" +
				" and branch_code=? and amend_id=(select max(amend_id) from policyno_generate where type_id=(select DEBIT_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE=? and PRODUCT_ID=?) " +
				"and branch_code=? and PRODUCT_ID=?)";
		final String args[] = {loginBra,pid,branchCode,loginBra,pid,branchCode,pid};
		final String current_no = runner.singleSelection(debiSql, args);
		final String debiUpdateSql = "update policyno_generate set current_no=?,remarks=? where type_id=(select DEBIT_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE=? and PRODUCT_ID=?) and status='Y'" +
				" and branch_code=? and amend_id=(select max(amend_id) from policyno_generate where type_id=(select DEBIT_TYPE_ID from " +
				"BRANCH_DETAIL where BRANCH_CODE=? and PRODUCT_ID=?) and branch_code=? and PRODUCT_ID=?)";
		final String args1[] = {current_no,current_no,loginBra,pid,branchCode,loginBra,pid,branchCode,pid};
		runner.multipleUpdation(debiUpdateSql, args1);
		logger.debug(EXIT);
		
		return current_no;
	}
	
	public String policyGeneration(final String quoteId, final String pid, final String loginBra)throws BaseException {
		logger.info("policyGeneration method()");
		logger.debug(ENTER);	
		String currentNo;
		final String args[] = {quoteId,pid,pid};
		final String[][] fromPosition = runner.multipleSelection("select nvl(status,'Y'),policy_no from home_position_master where quote_no=? and (PRODUCT_ID=? OR PROPOSAL_NO=?)", args);
		if (fromPosition[0][0].equalsIgnoreCase("Y")) {
			final String branchCode = loginBra;
			
			final String braSql = "SELECT POLICY_PREFIX,BRANCH_CODE from POLICYNO_GENERATE where  branch_code=? and type_id=(select POLICY_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE=? and PRODUCT_ID=?) and status='Y' and amend_id=(select max(amend_id)from policyno_generate where type_id=(select POLICY_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE=? and PRODUCT_ID=?) and branch_code=? and PRODUCT_ID=?)";
			final String args2[] = {branchCode,loginBra,pid,loginBra,pid,branchCode,pid};
			final String branchCode2[][] = runner.multipleSelection(braSql, args2);
			
			final String newSql = "select nvl(max(current_no)+1,max(start_no)) from policyno_generate where type_id=(select POLICY_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE=? and PRODUCT_ID=?) " +
					"and BRANCH_CODE=? and status='Y' and amend_id=(select max(amend_id) from policyno_generate where type_id=(select POLICY_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE=? and PRODUCT_ID=?) and branch_code=? and PRODUCT_ID=?)";
			final String args1[] = {loginBra,pid,branchCode,loginBra,pid,branchCode,pid};
			currentNo = runner.singleSelection(newSql, args1);
			
			final String remark = branchCode2[0][0] + "/" + branchCode2[0][1] + "/"+ currentNo;
			
			final String newSql1 = "update policyno_generate set current_no=?,remarks=? where type_id=(select POLICY_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE=? and PRODUCT_ID=?) and status='Y' " +
					"and branch_code=? and amend_id=(select max(amend_id) from policyno_generate where type_id=(select POLICY_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE=? and PRODUCT_ID=?) and branch_code=? and PRODUCT_ID=?)";
			final String args3[] = {currentNo,remark,loginBra,pid,branchCode,loginBra,pid,branchCode,pid};
			runner.multipleUpdation(newSql1, args3);
			currentNo = remark;
		}
		else{
			currentNo = fromPosition[0][1];
		}
		logger.debug(EXIT);
		
		return currentNo;
	}
	public String getBranchCodeforLogin(final String quoteId, final String pid)throws BaseException {
		logger.info("getBranchCodeforLogin method()");
		logger.debug(ENTER);
		final String args[] = {quoteId,pid};
		final String branchCode = runner.singleSelection("select BRANCH_CODE from BROKER_COMPANY_MASTER where AGENCY_CODE in(select log.OA_CODE from LOGIN_MASTER log,HOME_POSITION_MASTER home where QUOTE_NO=? and home.PRODUCT_ID=? and log.LOGIN_ID=home.LOGIN_ID)", args);
		logger.debug(EXIT);
		
		return branchCode;
	}
	
	public String productDes(final String pid, final String branch)throws BaseException {
		logger.info("productDes method()");
		logger.debug(ENTER);
			final String args[] = {pid,branch};
			final String productId = runner.singleSelection("select initcap(product_name) from product_master where product_id=? and branch_code=?", args);
		logger.debug(EXIT);
		
		return productId;
	}
	
	public String getPolicyNo(final String quoteNo)throws BaseException {
		logger.info("getPolicyNo method()");
		logger.debug(ENTER);
		final String args[] = {quoteNo};
		final String PolicyNo = runner.singleSelection("select policy_no from home_position_master where quote_no =? and status ='P'", args);
		logger.debug(EXIT);
		
		return PolicyNo;
	}
	
	public String checkDate(final String strDate)throws BaseException {
		logger.info("checkDate method()");
		logger.debug(ENTER);
		final StringBuffer dateCheck = new StringBuffer();
		final java.text.SimpleDateFormat simpleDate = new java.text.SimpleDateFormat("dd/MM/yyyy");
		simpleDate.setLenient(false);
		final java.text.ParsePosition pos = new java.text.ParsePosition(0);
		final java.util.Date date = simpleDate.parse(strDate, pos);
		// Check all possible things that signal a parsing error
		if ((date == null) || (pos.getErrorIndex() != -1)) {
			logger.info("Error: " + pos.getIndex());
			if (date == null) {
				logger.info("Date is null");
				dateCheck.append(INVALID);
			}
			else if (pos.getErrorIndex() != -1) {
				logger.info("Error index: " + pos.getErrorIndex());
				dateCheck.append(INVALID);
			}
			else{
				dateCheck.append(INVALID);}
		}
		logger.debug(EXIT);
		
		return dateCheck.toString();
	}
	
	public StringBuffer doValidations()throws BaseException {
		final HomeValidationBean valBean = new HomeValidationBean();
		final StringBuffer bufError = new StringBuffer();
		if (getCoverageStatus(quoteNo, "4")) {
			for (int d = 0; d < getStaffSize(); d++) {
				bufError.append(valBean.commonEmptyValidation(name[d],"Please Enter Domestic Staff Name"));
				bufError.append(valBean.dobDateValidation(dob[d],mon[d],yea[d]));
			}
		}
		bufError.append(valBean.commonEmptyValidation(homebuilt,"Please select home build of concrete"));
		bufError.append(valBean.commonEmptyValidation(pvtacmation,"Please select private living accommodation for your household"));
		bufError.append(valBean.commonEmptyValidation(rclimeland,"Please select whether you have home been built on reclaimed land"));
		bufError.append(valBean.commonEmptyValidation(consdays,"Please select whether you leave your home more than 60 consecutive days"));
		bufError.append(valBean.commonEmptyValidation(conditions,"Please select have you any insurer declined/imposed special terms or conditions"));
		bufError.append(valBean.claimValidation(claim,pastthryrs));
		bufError.append(valBean.claimNumberValidation(claim));
		bufError.append(valBean.claimSubValidation(claimtype,pastthryrs,"Please select the claim type"));
		bufError.append(valBean.claimSubValidation(claimamt,pastthryrs,"Please enter the claim amount"));
		bufError.append(valBean.commonEmptyValidation(property,"Please select the type of property/house"));
		bufError.append(valBean.claimAmountValidation(claimamt,pastthryrs));
		return bufError;
	}
	
	public StringBuffer doValidationsForSave()throws BaseException {
		final HomeValidationBean valBean = new HomeValidationBean();
		final StringBuffer bufError = new StringBuffer();
		bufError.append(valBean.claimAmountValidation(claimamt,pastthryrs));
		for (int d = 0; d < getStaffSize(); d++) {
			bufError.append(valBean.commonEmptyValidation(name[d],"Please Enter Domestic Staff Name"));
			bufError.append(valBean.dobDateValidation(dob[d],mon[d],yea[d]));
		}
		return bufError;
	}
	
	public String deleteTable(final String quoteNo, final String LapsedRemarks,final String userName)throws BaseException {
		logger.info("deleteTable method()");
		logger.debug(ENTER);
			final String args[] = {LapsedRemarks,userName,quoteNo};
			runner.multipleUpdation("update home_position_master set status = 'D', LAPSED_DATE = (select sysdate from dual),LAPSED_REMARKS=?,LAPSED_UPDATED_BY=? where quote_no =?", args);
		logger.debug(EXIT);
		
		return quoteNo;
	}
	
	public int getUWData() throws BaseException{
		logger.info("getUWData method()");
		logger.debug(ENTER);
			final String args[] = {quoteNo};
			final String[][] checkStatus = runner.multipleSelection("select * from HOME_UW_QUESTIONS where QUOTE_NO=?", args);
			if (checkStatus.length == 0) {
					final String qry = "insert into HOME_UW_QUESTIONS(quote_no,BUILT_OF_CONCRETE,PRIVATE_LIVING_ACCOMODATION,BUILT_ON_RECLAIMED_LAND,UNATTENDED_60_CONSECUTIVE_DAYS,DECLINED_CANELLED_CONDITIONS,ANY_CLAIM_IN_3YRS,NO_OF_CLAIMS,TYPE_OF_CLAIMS,CLAIM_AMOUNT,PROPERTY_TYPE) values(?,?,?,?,?,?,?,?,?,?,?)";
					final String args1[] = {quoteNo,homebuilt,pvtacmation,rclimeland,consdays,conditions,pastthryrs,claim,claimtype,claimamt,property};
					runner.multipleInsertion(qry, args1);
			} else {
				final String sql = "update HOME_UW_QUESTIONS set  BUILT_OF_CONCRETE=?, PRIVATE_LIVING_ACCOMODATION=?, BUILT_ON_RECLAIMED_LAND=?, UNATTENDED_60_CONSECUTIVE_DAYS=?, DECLINED_CANELLED_CONDITIONS=?,ANY_CLAIM_IN_3YRS=?,NO_OF_CLAIMS=?,TYPE_OF_CLAIMS=?,CLAIM_AMOUNT=?,PROPERTY_TYPE=? where QUOTE_NO=?";
				final String args2[] = {homebuilt,pvtacmation,rclimeland,consdays,conditions,pastthryrs,claim,claimtype,claimamt,property,quoteNo};
				runner.multipleUpdation(sql, args2);
			}
			domesticInsertionUpdation();
			
		logger.debug(EXIT);
		
		return 1;
	}
	
	public void domesticDeletion(final String deleteCount,final String qno)throws BaseException {
		final HomeValidationBean homeValid = new HomeValidationBean();
		logger.info("domesticDeletion method()");
		logger.debug(ENTER);
		final String args[] = {qno};
		final String staffSno = homeValid.removeLastChar(deleteCount,',');
		final String deleteSql = "delete from HOME_DOMESTIC_STAFF where QUOTE_NO=? and DOMESTIC_STAFF_SNO not in ("+ staffSno + ")";
		runner.multipleUpdation(deleteSql, args);
		logger.debug(EXIT);
		
	}
	
	public void domesticInsertionUpdation()throws BaseException {
		logger.info("domesticInsertionUpdation method()");
		logger.debug(ENTER);
		final StringBuffer deleteCount = new StringBuffer();
		final String args[] = {quoteNo};
		final String checkStatus[][] = runner.multipleSelection("select * from HOME_DOMESTIC_STAFF where QUOTE_NO=?", args);
		
		if (checkStatus.length == 0) {
			for (int d = 0; d < getStaffSize(); d++) {
				final int dsSno = 1000 + d;
				deleteCount.append(dsSno);
				deleteCount.append(',');
				final String dsdob = com.maan.common.util.OracleDateConversion.ConvertDate(dob[d] + "-" + mon[d] + "-" + yea[d]);
				final String insertSql = "Insert into HOME_DOMESTIC_STAFF(QUOTE_NO,NAME,DATE_OF_BIRTH,NO_DOMESTIC_STAFF,DOMESTIC_STAFF_SNO) values(?,?,?,?,?)";
				final String args3[] = {quoteNo,name[d],dsdob,Integer.toString(staffSize),Integer.toString(dsSno)};
				runner.multipleInsertion(insertSql, args3);
			}
		} else {
			for (int d = 0; d < getStaffSize(); d++) {
				final int dsSno = 1000 + d;
				deleteCount.append(dsSno);
				deleteCount.append(',');
				final String dsdob = com.maan.common.util.OracleDateConversion.ConvertDate(dob[d] + "-" + mon[d] + "-" + yea[d]);
				final String args4[] = {name[d],dsdob,quoteNo,Integer.toString(dsSno)};
				runner.multipleUpdation("update HOME_DOMESTIC_STAFF set NAME=?,DATE_OF_BIRTH=? where QUOTE_NO=? and DOMESTIC_STAFF_SNO=?", args4);
			}
		}
		if(deleteCount.length()>0){
			domesticDeletion(deleteCount.toString(),quoteNo);
		}
		logger.debug(EXIT);
		
	}
	
	public String[][] getClaimData(final String pid)throws BaseException {
		logger.info("getClaimData method()");
		logger.debug(ENTER);
			final String args[] = {pid};
			final String result[][] = runner.multipleSelection("select cover_id,cover_description from home_cover_master where product_id=?  and cover_id not in(7) and status='Y'", args);
		logger.debug(EXIT);
		
		return result;
	}
	
	public String[][] getUWDetails(final String quoteNo)throws BaseException {
		logger.info("getUWDetails method()");
		logger.debug(ENTER);
			final String args[] = {quoteNo};
			final String result[][] = runner.multipleSelection("select QUOTE_NO,BUILT_OF_CONCRETE,PRIVATE_LIVING_ACCOMODATION,BUILT_ON_RECLAIMED_LAND,UNATTENDED_60_CONSECUTIVE_DAYS,DECLINED_CANELLED_CONDITIONS, ANY_CLAIM_IN_3YRS, NO_OF_CLAIMS,TYPE_OF_CLAIMS,CLAIM_AMOUNT,nvl(PROPERTY_TYPE,' ') from HOME_UW_QUESTIONS where quote_no=?",args);
		logger.debug(EXIT);
		
		return result;
	}
	
	public String[][] getDomesticDetails(final String quoteNo)throws BaseException {
		logger.info("getDomesticDetails method()");
		logger.debug(ENTER);
			final String args[] = {quoteNo};
			final String result[][] = runner.multipleSelection("select  NAME,to_char(DATE_OF_BIRTH,'dd') as day ,to_char(DATE_OF_BIRTH,'MM') as month,to_char(DATE_OF_BIRTH,'YYYY') as Year from HOME_DOMESTIC_STAFF where quote_no=? order by DOMESTIC_STAFF_SNO",args);
		logger.debug(EXIT);
		
		return result;
	}
	public String[][] getPremiumName(final String quoteNo)throws BaseException {
		logger.info("getPremiumName method()");
		logger.debug(ENTER);
			final String args[] = {quoteNo};
			final String getPremiums[][] = runner.multipleSelection("select OVERALL_PREMIUM,to_char(inception_date,'dd/mm/yyyy'),POLICY_NO FROM home_position_master where quote_no=?",args);
		logger.debug(EXIT);
		
		return getPremiums;
	}
	public String getDobDayValue(final String dobDays)throws BaseException{
		logger.info("getDobDayValue method()");
		logger.debug(ENTER);
		final StringBuffer dobBuffer = new StringBuffer();
		if (dobDays != null&&Integer.parseInt(dobDays) < 9){
				dobBuffer.append("0"+dobDays);
		}else{
			dobBuffer.append(dobDays);
		}
		logger.debug(EXIT);
		
		return dobBuffer.toString();
	}
	
	public String getDobMonthValue(final String dobMonths)throws BaseException{
		logger.info("getDobMonthValue method()");
		logger.debug(ENTER);
		final StringBuffer dobBuffer = new StringBuffer();
		if (dobMonths != null&&Integer.parseInt(dobMonths) < 9){
				dobBuffer.append("0"+dobMonths);
		}else{
			dobBuffer.append(dobMonths);
		}
		logger.debug(EXIT);
		
		return dobBuffer.toString();
	}
	
	public void settingValues(String customerId)throws BaseException{
		logger.info("settingValues method()");
		logger.debug(ENTER);
			customerId = customerId == null ? "" : customerId;
			final String args[] = {customerId};
			final String[][] values = runner.multipleSelection("select nvl(title,'NIL'),gender,first_name,last_name,nvl(nationality,'NIL'),to_char(dob,'DD') as dobday,to_char(dob,'MM') as dobmonth,to_char(dob,'YYYY') as dobyear,telephone,mobile,fax,email,address1,occupation,emirate,country,pobox,company_name,address2,customer_source from personal_info where customer_id=?", args);
			title = (values[0][0].equalsIgnoreCase(SELECTS) ? "NIL": values[0][0]);
			gender = values[0][1];
			firstName = values[0][2];
			lastName = values[0][3];
			nationality = (values[0][4].equalsIgnoreCase(SELECTS) ? "NIL": values[0][4]);
			dobDay = values[0][5];
			dobMonth = values[0][6];
			dobYear = values[0][7];
			telephone = values[0][8];
			mobile = values[0][9];
			fax = values[0][10];
			email = values[0][11];
			address1 = values[0][12];
			occupation = values[0][13];
			emirate = values[0][14];
			country = values[0][15];
			poBox = values[0][16];
			if (values[0][17] != null) {
				orgName = values[0][17];
			}
			
			insuredlocation = values[0][18];
			source = values[0][19] == null ?"": values[0][19];
		logger.debug(EXIT);
		
	}
	
	public String getSources(final String source,final String otherSource)throws BaseException{
		final StringBuffer sourceBuf = new StringBuffer();
		if (source.equalsIgnoreCase("others")&&otherSource.length() > 0) {
			sourceBuf.append(otherSource);
		}
		else{
			sourceBuf.append(source);
		}
		return sourceBuf.toString();
	}
	
	public String storedValues(String customerId, final String loginId,
			final String loginBranch, final String pids)  throws BaseException{
		logger.info("storedValues method()");
		logger.debug(ENTER);
		customerId = customerId == null ? "" : customerId;
		customerId = NULL.equals(customerId) ? "" : customerId;
			final String args[] = {customerId};
			final String qry = runner.singleSelection("select count(*) from personal_info where customer_id=?", args);
		if ("M/S".equalsIgnoreCase(title)) {
			orgName = firstName;
			firstName = "";
		}
		source = getSources(source,otherSource);
		if ("0".equalsIgnoreCase(qry)|| "DIDN'T SELECTED".equalsIgnoreCase(qry)) {
			customerId = DataManipualtion.getMaxCustomerId(loginBranch, pids);
				final String insertQry = "insert into personal_info(customer_id,application_id,title,first_name,last_name,amend_id,nationality,dob,gender,telephone,mobile,fax,email,address1,address2,occupation,pobox,country,emirate,status,entry_date,login_id, company_name,customer_source) values(?,'1',?,?,?,'1',?,?,?,?,?,?,?,?,?,?,?,?,?,'Y',(select sysdate from dual),?, ?,?)";
				final String args2[] = {customerId,(title.equalsIgnoreCase(SELECTS) ? "" : title),firstName,lastName,(nationality.equalsIgnoreCase(SELECTS) ? ""
						: nationality),com.maan.common.util.OracleDateConversion
						.ConvertDate(dobDay + "-" + dobMonth + "-" + dobYear),gender,telephone,mobile,fax,email,address1,insuredlocation,occupation,poBox,
						(country.equalsIgnoreCase(SELECTS) ? "" : country),(emirate.equalsIgnoreCase(SELECTS) ? "" : emirate),loginId,orgName,source};
				runner.multipleInsertion(insertQry, args2);

		} else {
				final String updateQry = "update personal_info set application_id='1',title=?,first_name=?,last_name=?,amend_id='1',nationality=?,dob=?,gender=?,telephone=?,mobile=?,fax=?,email=?,address1=?,address2=?,occupation=?,pobox=?,country=?,emirate=?,status='Y',effective_date=(select sysdate from dual),company_name = ?,customer_source=? where customer_id=?";
				final String args1[] = {(title.equalsIgnoreCase(SELECTS) ? "" : title),firstName,lastName,(nationality.equalsIgnoreCase(SELECTS) ? ""
						: nationality),com.maan.common.util.OracleDateConversion
						.ConvertDate(dobDay + "-" + dobMonth + "-" + dobYear),gender,telephone,mobile,fax,email,address1,insuredlocation,occupation,poBox,(country.equalsIgnoreCase(SELECTS) ? "" : country),(emirate.equalsIgnoreCase(SELECTS) ? "" : emirate),orgName.replaceAll("'", "''"),source,customerId};
				runner.multipleUpdation(updateQry, args1);
		}
		logger.debug(EXIT);
		
		return customerId;
	}
	
	public StringBuffer validateFields(final String pid,final String prodDesc) throws BaseException{
		logger.info("validateFields Home DataManipulation Data Select method()");
		logger.debug(ENTER);
		final DataSelectCustomer homeValid = new DataSelectCustomer();
		final StringBuffer error = new StringBuffer();
		
		if (!prodDesc.equalsIgnoreCase("Home")&&"1".equalsIgnoreCase(cid)) {
			error.append(homeValid.commonValidation(email,"19","19"));
		}
		else if(prodDesc.equalsIgnoreCase("Home")){
			error.append(homeValid.occupationValidation(occupation,"282"));
			error.append(homeValid.selectValidation(title,"1"));
		}
		else if(prodDesc.equalsIgnoreCase("Travel")){
			error.append(homeValid.selectValidation(nationality,"5"));
		}
		
		error.append(homeValid.emailValidation(email));
		error.append(homeValid.orgNameValidation(title,firstName));
		error.append(homeValid.commonValidation(firstName,"3","6"));
		error.append(homeValid.commonSpecialValidation(firstName,"Customer Name"));
		
		if (!prodDesc.equalsIgnoreCase("Travel")) {
			error.append(homeValid.commonValidation(address1,"274","276"));
			error.append(homeValid.commonSpecialValidation(address1,"Customer address"));
			error.append(homeValid.selectValidation(emirate,"25"));
		}
		
		error.append(homeValid.mobileValidation(mobile,telephone));
		error.append(homeValid.poboxValidation(poBox));
		error.append(homeValid.faxValidation(fax));
		error.append(homeValid.dobValidation(dobDay,dobMonth,dobYear));
		error.append(homeValid.otherSourceValidation(source,otherSource));
		logger.info("validateFields Home DataManipulation Data Select method()  "+error.toString());
		return error;
	}

	public StringBuffer validateFieldsSearch()throws BaseException {
		final DataSelectCustomer homeValid = new DataSelectCustomer();
		final StringBuffer error = new StringBuffer(1000);
		if ("".equalsIgnoreCase(mobile)
				&& "".equalsIgnoreCase(email)
				&& "".equalsIgnoreCase(firstName)
				&& "DD/MMM/YYYY".equalsIgnoreCase(dobDay + "/" + dobMonth + "/"
						+ dobYear)) {
			error.append("Please select Name/Mobile/DOB/Email to Search,");
		}
		error.append(homeValid.emailValidation(email));
		error.append(homeValid.dobValidation(dobDay,dobMonth,dobYear));
		return error;
	}
	
	public String[][] searchValues() throws BaseException{
		final Map searchValues = new HashMap();
		logger.info("searchValues method()");
		logger.debug(ENTER);
			final String oracleDate = com.maan.common.util.OracleDateConversion.ConvertDate(dobDay + "-" + dobMonth + "-" + dobYear);
			final String args[] = {firstName.trim(),firstName.trim(),email.trim(),mobile.trim(),oracleDate};
			final String[][] returnval = runner.multipleSelection("select first_name,last_name,company_name,email,telephone,mobile,dob,customer_id from personal_info where (first_name=? or company_name=?) and email like ? and mobile=? and dob=?", args);
			searchValues.put("searchValues", returnval);
			if (returnval.length == 0) {
				final String args1[] = {"%" + firstName.trim() + "%","%" + firstName.trim() + "%", "%" + email.trim() + "%",mobile.trim(),oracleDate};
				final String[][] returnvals = runner.multipleSelection("select first_name,last_name,company_name,email,telephone,mobile,dob,customer_id from personal_info where first_name like ? or company_name like ? or email like ? or mobile=? or dob=?", args1);
				searchValues.put("searchValues", returnvals);
			}
		logger.debug(EXIT);
		
		return (String[][])searchValues.get("searchValues");
	}

	public String[][] getLogIds(final String loginIds) throws BaseException{
		logger.info("getLogIds method()");
		logger.debug(ENTER);
		final String args[] = {loginIds,loginIds,loginIds};
		final String[][] valuess = runner.multipleSelection("select login_id from login_master where (oa_code=(select agency_code from login_master where login_id=?) or created_by=(select agency_code from login_master where login_id=?) or login_id=?) and login_id not in('NONE','NON')", args);
		logger.debug(EXIT);
		
		return valuess;
	}
	
	public String getBrokerCompanyName123(final String loginId)throws BaseException {
		logger.info("getBrokerCompanyName123 method()");
		logger.debug(ENTER);
		final String args[] = {loginId};
		final String brokerName = runner.singleSelection("select COMPANY_NAME from broker_company_master where agency_code=(select oa_code from login_master where login_id=?)", args);
		logger.debug(EXIT);
		
		return brokerName;
	}
	
	public String getLoginOacode(final String loginId)throws BaseException {
		logger.info("getLoginOacode method()");
		logger.debug(ENTER);
		final String args[] = {loginId};
		final String oaCode = runner.singleSelection("select oa_code from login_master where login_id=?", args);
		logger.debug(EXIT);
		
		return oaCode;
	}
	
	public String getCustomerId(final String quoteNo)throws BaseException {
		logger.info("getCustomerId method()");
		logger.debug(ENTER);
		final String args[] = {quoteNo};
		final String customerId = runner.singleSelection("select customer_id from home_position_master where  quote_no=?", args);
		logger.debug(EXIT);
		
		return customerId;
	}
	
	public String[][] getCustomerDetails(final String customerId)throws BaseException {
		logger.info("getCustomerDetails method()");
		logger.debug(ENTER);
		final String args[] = {customerId};
		final String cusDetails[][] = runner.multipleSelection("select * from personal_info where  customer_id=?", args);
		logger.debug(EXIT);
		
		return cusDetails;
	}
	
	public String getAgencyCode(final String logPersonId)throws BaseException {
		logger.info("getAgencyCode method()");
		logger.debug(ENTER);
		final String args[] = {logPersonId};
		final String agencyCode = runner.singleSelection("select agency_code from login_master where login_id=?", args);
		logger.debug(EXIT);
		
		return agencyCode;
	}
	
	public static String getDate()throws BaseException {
		final Calendar calendar = Calendar.getInstance();
		final String dates = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH)) + "-"
				+ calendar.get(Calendar.MONTH) + "-" + calendar.get(Calendar.YEAR) + "_"
				+ calendar.get(Calendar.HOUR) + "_" + calendar.get(Calendar.MINUTE) + "_"
				+ calendar.get(Calendar.SECOND);
		return dates;
	}
	
	public void updateAdditionalCommision(final String commission, final String quoteNo)throws BaseException {
		logger.info("UpdateAdditionalCommision method()");
		logger.debug(ENTER);
		final String args[] = {commission,quoteNo};
		runner.multipleUpdation("update HOME_POSITION_MASTER set BROKER_ADDITIONAL_COMMISSION=? where QUOTE_NO=?", args);
		logger.debug(EXIT);
		
	}
	
	public String[][] coverSelect(final String pid)throws BaseException {
		logger.info("coverSelect method()");
		logger.debug(ENTER);
		final String args[] = {pid};
		final String result[][] = runner.multipleSelection("select PRODUCT_ID,COVER_ID,COVER_DESCRIPTION,GROUP_ID,EFFECTIVE_DATE,END_DATE,REMARKS,STATUS,RATING_FACTOR_ID,DISPLAY_TYPE,DISPLAY_ID,RATING_FACTOR_SUB_ID,nvl(DISPLAY_SIZE,' '),nvl(VALID_PERSONAL_SUB_ID,'0') from Home_Cover_Master where product_id=? and status='Y' order by (DISPLAY_ORDER)", args);
		logger.debug(EXIT);
		
		return result;
	}

	public String[][] coverSelectController(final String pid)throws BaseException {
		logger.info("coverSelectController method()");
		logger.debug(ENTER);
		final String args[] = {pid};
		final String result[][] = runner.multipleSelection("select PRODUCT_ID,COVER_ID,COVER_DESCRIPTION,GROUP_ID,EFFECTIVE_DATE,END_DATE,REMARKS,STATUS,RATING_FACTOR_ID,DISPLAY_TYPE,DISPLAY_ID,RATING_FACTOR_SUB_ID,nvl(DISPLAY_SIZE,' '),nvl(VALID_PERSONAL_SUB_ID,'0') from Home_Cover_Master where product_id=? order by (cover_id)", args);
		logger.debug(EXIT);
		
		return result;
	}
	
	public String[][] gettingRatingDataNameAndValue(final String pid,
			final String ratingFactorId, final String ratingFactorSubId)throws BaseException {
		logger.info("gettingRatingDataNameAndValue method()");
		logger.debug(ENTER);
		final String args[] = {ratingFactorSubId,ratingFactorId};
		final String result[][] = runner.multipleSelection("select initCap(lower(data_name)),data_value from single_dimension_discrete where rating_factor_sub_id=? and rating_factor_id=? and status='Y' order by Loading_discount_id", args);
		logger.debug(EXIT);
		
		return result;
	}
	
	public String[][] getEmpByBroker(final String loginId) throws BaseException{
		logger.info("getEmpByBroker method()");
		logger.debug(ENTER);
		final String agencyCode = getAgencyCode(loginId);
		final String[] args = {agencyCode,agencyCode,loginId};
		final String[][] valuess = runner.multipleSelection("select login_id from login_master where oa_code=? or created_by=? or login_id=?", args);
		logger.debug(EXIT);
		
		return valuess;
	}
	
	public String[][] getNewLogIds(final String loginIds) throws BaseException{
		logger.info("getNewLogIds method()");
		logger.debug(ENTER);
		final String[] args = {loginIds};
		final String[][] valuess = runner.multipleSelection("select login_id from login_master where oa_code=(select oa_code from login_master where login_id=? and usertype!='User')", args);
		logger.debug(EXIT);
		
		return valuess;
	}
	
	public void updateReferalStatus(final String quoteNo, final String pid,	final String referralName)throws BaseException {
		logger.info("updateReferalStatus method()");
		logger.debug(ENTER);
		final String[] args = {referralName,quoteNo,pid};
		runner.multipleUpdation("update HOME_POSITION_MASTER set REMARKS='Referal',REFERRAL_DESCRIPTION=? where quote_no=? and PRODUCT_ID=?", args);
		logger.debug(EXIT);
		
	}

	public void updateReferalStatus(final String quoteNo, final String pid,
			final String referralName, final String adminRemarks, final String adminStatus)throws BaseException {
		logger.info("updateReferalStatus five arguments method()");
		logger.debug(ENTER);
		final String[] args = {adminRemarks,adminStatus,referralName,quoteNo,pid};
		runner.multipleUpdation("update HOME_POSITION_MASTER set REMARKS='Referal',SUMMARY_REMARKS=?,ADMIN_SUMMARY_STATUS=?, REFERRAL_DESCRIPTION=?,ADMIN_REFERRAL_STATUS='' where quote_no=? and PRODUCT_ID=?", args);
		logger.debug(EXIT);
		
	}
	
	public String getCustomerName(final String quoteNo)throws BaseException {
		final StringBuffer names = new StringBuffer();
		logger.info("getCustomerName method()");
		logger.debug(ENTER);
		final String[] args = {quoteNo};
		final String[][] returnVal = runner.multipleSelection("select nvl(first_name,company_name),last_name from personal_info where customer_id=(select customer_id from home_position_master where quote_no=?)", args);
		if (returnVal.length > 0){
			names.append((returnVal[0][0] == null ? "" : returnVal[0][0])+" "+(returnVal[0][1] == null ? "" : returnVal[0][1]));
		}
		logger.debug(EXIT);
		
		return names.toString();
	}
	
	public void updatePremium(final String quoteNo, final Map PremiumHash,
			double totalPre, final String loginId, final String pid) throws BaseException{
		logger.info("DataSelect updatePremium method()");
		logger.debug(ENTER);
			final String args[] = {quoteNo};
			final String result[][] = runner.multipleSelection("select * from HOME_COVERAGE_TRANSACTION where QUOTE_NO=? order by home_sno", args);
			for (int i = 0; i < result.length; i++) {
				final String IndividualPremium = (String) PremiumHash.get(result[i][1]);
				final String rate = (String) PremiumHash.get("rate"+result[i][1]);
				if (IndividualPremium != null) {
					final String sql = "update HOME_COVERAGE_TRANSACTION set PREMIUM=?,rate=? where QUOTE_NO=? and  HOME_SNO=?";
					final String args1[] = {IndividualPremium,rate,quoteNo,result[i][1]};
					runner.multipleUpdation(sql, args1);
				}
			}
			final String minimumPremium = getMinimumPremium(loginId, pid);
			final double amt = Double.parseDouble((minimumPremium == null|| "".equals(minimumPremium)  || NULL.equals(minimumPremium)) ? "0.0": minimumPremium);
			if (totalPre < amt) {
				totalPre = amt;
			}
			final double commissionAmount = getCommissionAmount(totalPre,Double.parseDouble(getCommision(loginId, pid)));
			final String updateSql = "update HOME_POSITION_MASTER set PREMIUM=?,OVERALL_PREMIUM=?,COMMISSION=? where QUOTE_NO=?";
			final String args2[] = {Double.toString(totalPre),Double.toString(totalPre),Double.toString(commissionAmount),quoteNo};
			runner.multipleUpdation(updateSql, args2);
			logger.debug(EXIT);
			
	}
	
	public double getCommissionAmount(final double premium, final double commission)throws BaseException{
		double commissionAmount;
		if (commission == 0){
			commissionAmount = 0.0;
		}
		else{
			commissionAmount = premium * (commission / 100);
		}
		return commissionAmount;
	}
	
	public String getBuildingDetails(final String quoteNo) throws BaseException{
		logger.info("getBuildingDetails method()");
		logger.debug(ENTER);
		final StringBuffer result = new StringBuffer();
		final String[] args = {quoteNo};
		final String cover = runner.singleSelection("select count(*) from HOME_COVERAGE_TRANSACTION where QUOTE_NO =? and COVER_ID = '1'", args);
		final String[][] building = runner.multipleSelection("select BUILDING_NAME,STREET_NAME,FLAT_VILLA_NO,EMIRATE from HOME_COVERAGE_BUILDING where quote_no=? and COVER_ID=1", args);
		if ("0".equals(cover)) {
			result.append('1');
		} else{
			if (building.length > 0){
				result.append('1');
			}
			else{
				result.append('0');
			}
		}
		logger.debug(EXIT);
		
		return result.toString();
	}
	
	public String[][] getRefrralDetails(final String pid, final String loginBranch)throws BaseException {
		logger.info("getRefrralDetails method()");
		logger.debug(ENTER);
		final String[] args = {pid,loginBranch};
		final String[][] refrralDetails = runner.multipleSelection("select CATEGORY_DETAIL_ID,DETAIL_NAME from CONSTANT_DETAIL where CATEGORY_ID=? and BRANCH_CODE=?", args);
		logger.debug(EXIT);
		
		return refrralDetails;
	}
	
	public String[][] getClaimDetails(final String quoteNo)throws BaseException {
		logger.info("getClaimDetails method()");
		logger.debug(ENTER);
			final String[] args = {quoteNo};
			final String[][] claimDetails = runner.multipleSelection("select BUILT_OF_CONCRETE,PRIVATE_LIVING_ACCOMODATION,BUILT_ON_RECLAIMED_LAND,UNATTENDED_60_CONSECUTIVE_DAYS,DECLINED_CANELLED_CONDITIONS,ANY_CLAIM_IN_3YRS,NO_OF_CLAIMS,TYPE_OF_CLAIMS,CLAIM_AMOUNT from HOME_UW_QUESTIONS where QUOTE_NO=?", args);
		logger.debug(EXIT);
		
		return claimDetails;
	}
	
	public String[][] getClaimTypeNames(final String coverId, final String pid)throws BaseException {
		logger.info("getClaimTypeNames method()");
		logger.debug(ENTER);
			final String[] args = {pid};
			final String[][] claimTypes = runner.multipleSelection("select COVER_DESCRIPTION from HOME_COVER_MASTER where COVER_ID in("+coverId+") and PRODUCT_ID=?", args);
		logger.debug(EXIT);
		
		return claimTypes;
	}
	
	public String[][] getInformation(final String quoteNo)throws BaseException {
		logger.info("getInformation method()");
		logger.debug(ENTER);
			final String[] args = {quoteNo};
			final String[][] info = runner.multipleSelection("select PRODUCT_SUMINSURED from HOME_COVERAGE_TRANSACTION where QUOTE_NO =? and COVER_ID = '4'", args);
		logger.debug(EXIT);
		
		return info;
	}
	
	public boolean getCoverageStatus(final String quoteNo, final String covId)throws BaseException {
		logger.info("getCoverageStatus method()");
		logger.debug(ENTER);
			final String[] args = {quoteNo,covId};
			final String coverage = runner.singleSelection("select nvl(PRODUCT_SUMINSURED,'0') from HOME_COVERAGE_TRANSACTION where QUOTE_NO =? and COVER_ID = ?", args);
		logger.debug(EXIT);
		
		boolean coverageStatus;
		if (coverage.length() <= 0){
			coverageStatus = false;
		}
		else if (coverage.equalsIgnoreCase("0")){
			coverageStatus = false;
		}
		else{
			coverageStatus = true;
		}
		return coverageStatus;
	}
	public String[][] getTodaysDate()throws BaseException {
		logger.info("getTodaysDate method()");
		logger.debug(ENTER);
		final String[][] result = runner.multipleSelection("select extract(year from sysdate) ,extract(month from sysdate),extract(day from sysdate) from dual");
		logger.debug(EXIT);
		
		return result;
	}
	
	public String getMinimumPremium123(final String qno)throws BaseException {
		logger.info("getMinimumPremium123 method()");
		logger.debug(ENTER);
		final String[] args = {qno,qno};
		final String minimumPremium = runner.singleSelection("select nvl(MIN_PREMIUM_AMOUNT,'0') from LOGIN_USER_DETAILS where AGENCY_CODE in(select agency_code from LOGIN_MASTER where login_id = (select login_id from home_position_master where quote_no=?)) and product_id in(select product_id from home_position_master where quote_no=?)", args);
		logger.debug(EXIT);
		
		return minimumPremium;
	}
	
	public String getMinimumPremium(final String loginId, final String pid)throws BaseException {
		logger.info("getMinimumPremium method()");
		logger.debug(ENTER);
			final String[] args = {loginId,pid};
			final String minimumPremium = runner.singleSelection("select nvl(MIN_PREMIUM_AMOUNT,'0') from LOGIN_USER_DETAILS where AGENCY_CODE in(select oa_code from LOGIN_MASTER where login_id = ?) and product_id=?", args);
			logger.debug(EXIT);
			
		return minimumPremium;
	}
	
	public void updateReferalStatusIfNot(final String quoteNo, final String pid) throws BaseException{
		final StringBuffer remarks = new StringBuffer();
		final StringBuffer adminStatus = new StringBuffer();
		logger.info("updateReferalStatusIfNot method()");
		logger.debug(ENTER);
			final String[] args = {quoteNo,pid};
			final String result[][] = runner.multipleSelection("select nvl(REMARKS,' '),nvl(ADMIN_SUMMARY_STATUS,' ') from HOME_POSITION_MASTER where REMARKS='Referal' and quote_no=? and PRODUCT_ID=?", args);
			if (result.length > 0) {
				remarks.append(result[0][0].trim());
				adminStatus.append(result[0][1].trim());
			}
			if (remarks.toString().equalsIgnoreCase("Referal") && !adminStatus.toString().equalsIgnoreCase("Y")) {
				runner.multipleUpdation("update HOME_POSITION_MASTER set REMARKS='',REFERRAL_DESCRIPTION='',SUMMARY_REMARKS='',ADMIN_REFERRAL_STATUS='',ADMIN_SUMMARY_STATUS='' where quote_no=? and PRODUCT_ID=?", args);
			}
		logger.debug(EXIT);
		
	}
	
	public String[][] getIndividualPremium(final String quoteNo)throws BaseException {
		logger.info("getIndividualPremium method()");
		logger.debug(ENTER);
		final String[] args = {quoteNo};
		final String result[][] = runner.multipleSelection("select COVER_ID,PREMIUM from HOME_COVERAGE_TRANSACTION where QUOTE_NO=? and HOME_SNO between 1001 and 1006 order by HOME_SNO", args);
		logger.debug(EXIT);
		
		return result;
	}
	
	public String getLoginForQuote(final String quoteNo)throws BaseException {
		logger.info("getIndividualPremium method()");
		logger.debug(ENTER);
			final String[] args = {quoteNo};
			final String res = runner.singleSelection("select LOGIN_ID from HOME_POSITION_MASTER where QUOTE_NO=?", args);
		logger.debug(EXIT);
		
		return res;
	}
	
	public String getExtraPremium(final String quoteNo)throws BaseException {
		logger.info("getExtraPremium method()");
		logger.debug(ENTER);
			final String[] args = {quoteNo};
			final String expre =  runner.singleSelection("select EXCESS_PREMIUM from HOME_POSITION_MASTER where QUOTE_NO =?", args);
		logger.debug(EXIT);
		
		return expre;
	}
	public String getExtraApplicationPremium(final String appNo)throws BaseException {
		logger.info("getExtraPremium method()");
		logger.debug(ENTER);
			final String[] args = {appNo};
			final String expre =  runner.singleSelection("select EXCESS_PREMIUM from HOME_POSITION_MASTER where application_no =?", args);
		logger.debug(EXIT);
		
		return expre;
	}
	
	public String getAdminRemarks(final String quoteNo)throws BaseException {
		logger.info("getAdminRemarks method()");
		logger.debug(ENTER);
			final String[] args = {quoteNo};
			final String adminRemarks  = runner.singleSelection("select ADMIN_REMARKS from HOME_POSITION_MASTER where QUOTE_NO = ? ", args);
		logger.debug(EXIT);
		
		return adminRemarks;
	}
	
	public String getBrokerAdminRemarks(final String quoteNo)throws BaseException {
		logger.info("getBrokerAdminRemarks method()");
		logger.debug(ENTER);
			final String[] args = {quoteNo};
			final String adminRemarks = runner.singleSelection("select nvl(SUMMARY_REMARKS,' ') from HOME_POSITION_MASTER where QUOTE_NO = ? ", args);
		logger.debug(EXIT);
		
		return adminRemarks;
	}
	
	public String adminUpdateExcessSignPremium(final String quoteNo,final String sign, final String excesPre,final String adminRefStatus,final String adminRemarks)throws BaseException
	{
		logger.info("adminUpdateExcessSignPremium method()");
		logger.debug(ENTER);
		final String excesSign = getSign(sign,excesPre);
		final StringBuffer result = new StringBuffer();
		if (adminRefStatus.equalsIgnoreCase("Y")) {
			final String[] args = {adminRemarks,excesPre,excesSign,quoteNo};
			final String sqlRefUpdate = "update HOME_POSITION_MASTER set REMARKS='Admin',ADMIN_REMARKS=?,EXCESS_PREMIUM=?,ADMIN_REFERRAL_STATUS='N',STATUS='Y',EXCESS_SIGN=?,INCEPTION_DATE=(select sysdate from dual) where QUOTE_NO=?";// old
			result.append("Accepted");
			runner.multipleUpdation(sqlRefUpdate, args);
		} else if (adminRefStatus.equalsIgnoreCase("N")) {
			final String[] args = {adminRemarks,excesPre,excesSign,quoteNo};
			final String sqlRefUpdate = "update HOME_POSITION_MASTER set REMARKS='Referal',ADMIN_REMARKS=?,EXCESS_PREMIUM=?,ADMIN_REFERRAL_STATUS='N',STATUS='R',EXCESS_SIGN=?,INCEPTION_DATE=(select sysdate from dual) where QUOTE_NO=?";
			result.append("Rejected");
			runner.multipleUpdation(sqlRefUpdate, args);
		} else if (adminRefStatus.equalsIgnoreCase("A")) {
			final String[] args = {adminRemarks,excesPre,excesSign,quoteNo};
			final String sqlRefUpdate = "update HOME_POSITION_MASTER set REMARKS='Referal',ADMIN_REMARKS=?,EXCESS_PREMIUM=?,ADMIN_REFERRAL_STATUS='N',STATUS='Y',EXCESS_SIGN=?,INCEPTION_DATE=(select sysdate from dual) where QUOTE_NO=?";
			result.append("Paused");
			runner.multipleUpdation(sqlRefUpdate, args);
		}
		logger.debug(EXIT);
		
		return result.toString();
	}
	
	public String getAdminReferralUpdation(final String quoteNo,
			final String adminRefStatus, final String adminRemarks, final String sign,
			final String excesPre, final String adminLogin, final String royal) throws BaseException {
		logger.info("getAdminReferralUpdation method()");
		logger.debug(ENTER);
			final String res = adminUpdateExcessSignPremium(quoteNo,sign,excesPre,adminRefStatus,adminRemarks);
			final double premium = getPremiumWithExcess(quoteNo,sign,excesPre);
			final String result[][] = getLoginProId(quoteNo);
			final double commis = Double.parseDouble(getCommision((result[0][0]== null?"0":result[0][0]),(result[0][1]== null?"0":result[0][1])));
			final double totcom = getCommissionAmount(premium,commis);
			final String[] args1 = {Double.toString(premium),Double.toString(totcom),adminLogin,quoteNo};
			runner.multipleUpdation("update HOME_POSITION_MASTER set OVERALL_PREMIUM=?,COMMISSION=?,APPROVED_BY=(select nvl(username,' ') from login_master where login_id=?) where QUOTE_NO=?", args1);
		logger.debug(EXIT);
		
		return res;
	}
	
	// For admin Referral unapproved start -- admin side
	public String[][] getHomeApproved(final String pid)  throws BaseException {
		logger.info("getHomeApproved method()");
		logger.debug(ENTER);
		final String sql = "select to_char(a.inception_date,'YYYY-MM-DD'),count(a.inception_date) from home_position_master a where (a.REMARKS='Admin' or a.ADMIN_REFERRAL_STATUS='Y') and a.status='Y'"+PIDSQL
					+ pid
					+ ") group by to_char(a.inception_date,'YYYY-MM-DD') order by to_char(a.inception_date,'YYYY-MM-DD') desc";
		final String[][] approvedDetails = runner.multipleSelection(sql);
		logger.debug(EXIT);
		
		return approvedDetails;
	}

	public String[][] getHomeApprovedByDate(final String eDate, final String type,final String pid) throws BaseException {
		logger.info("getHomeApprovedByDate method()");
		logger.debug(ENTER);
			final String args[] = {eDate,type};
			final String[][] approvedDetails = runner.multipleSelection("select distinct pm.application_no,pm.login_id,pm.quote_no,pm.REFERRAL_DESCRIPTION,nvl(pm.SUMMARY_REMARKS,'') from home_position_master pm where to_char(pm.inception_date,'YYYY-MM-DD')=? and pm.product_id in("+ pid+ ") and (pm.REMARKS=? or pm.ADMIN_REFERRAL_STATUS='Y') and pm.status='Y' order by pm.quote_no desc", args);
		logger.debug(EXIT);
		
		return approvedDetails;
	}

	public String[][] getHomeReferal(final String pid) throws BaseException {
		logger.info("getHomeReferal method()");
		logger.debug(ENTER);
			final String sql = "select to_char(a.entry_date,'YYYY-MM-DD'),count(a.entry_date) from home_position_master a where a.REMARKS= 'Referal' and a.status='Y' "+PIDSQL
					+ pid
					+ ") group by to_char(a.entry_date,'YYYY-MM-DD') order by to_char(a.entry_date,'YYYY-MM-DD') desc";
			final String[][] referralDetails = runner.multipleSelection(sql);
		logger.debug(EXIT);
		
		return referralDetails;
	}
	
	public String[][] getHomeRejectedReferal(final String pid) throws BaseException {
		logger.info("getHomeRejectedReferal method()");
		logger.debug(ENTER);
			final String sql =  "select to_char(a.EFFECTIVE_DATE,'YYYY-MM-DD'),count(a.EFFECTIVE_DATE) from home_position_master a where ADMIN_REFERRAL_STATUS='N' and a.status='R' "+PIDSQL
					+ pid
					+ ") group by to_char(a.EFFECTIVE_DATE,'YYYY-MM-DD') order by to_char(a.EFFECTIVE_DATE,'YYYY-MM-DD') desc";
			final String[][] rejectedDetails = runner.multipleSelection(sql);
		logger.debug(EXIT);
		
		return rejectedDetails;
	}
	
	public String[][] getHomeReferalByDate(final String eDate, final String type, final String pid) throws BaseException {
		logger.info("getHomeReferalByDate method()");
		logger.debug(ENTER);
			final String sql =  "select distinct pm.application_no,pm.login_id,pm.quote_no,pm.REFERRAL_DESCRIPTION,nvl(pm.SUMMARY_REMARKS,'') from home_position_master pm where to_char(pm.entry_date,'YYYY-MM-DD')=? and pm.product_id in("
					+ pid + ") and pm.REMARKS=? and pm.status='Y' order by pm.quote_no desc";
			final String args[] = {eDate,type};
			final String[][] referralDetails = runner.multipleSelection(sql, args);
		logger.debug(EXIT);
		
		return referralDetails;
	}

	public String[][] getHomeRejectedReferalByDate(final String eDate, final String pid) throws BaseException {
		logger.info("getHomeReferalByDate method()");
		logger.debug(ENTER);
			final String sql = "select distinct pm.application_no,pm.login_id,pm.quote_no,pm.REFERRAL_DESCRIPTION,nvl(pm.SUMMARY_REMARKS,'') from home_position_master pm where to_char(pm.EFFECTIVE_DATE,'YYYY-MM-DD')=? and pm.product_id in("
					+ pid
					+ ") and pm.ADMIN_REFERRAL_STATUS='N' and pm.status='R' order by pm.quote_no desc";
			final String args[] = {eDate};
			final String[][] rejectedDetails = runner.multipleSelection(sql, args);
		logger.debug(EXIT);
		
		return rejectedDetails;
	}
	// For admin Referral unapproved End -- admin side
	public String[][] getAdminRefStatussandSign(final String qno) throws BaseException {
		logger.info("getAdminRefStatussandSign method()");
		logger.debug(ENTER);
			final String args[] = {qno};
			final String[][] details = runner.multipleSelection("select ADMIN_REFERRAL_STATUS,EXCESS_SIGN from HOME_POSITION_MASTER where QUOTE_NO=?", args);
		logger.debug(EXIT);
		
		return details;
	}
	
	public String[][] getExcessandSign(final String qno) throws BaseException {
		logger.info("getExcessandSign method()");
		logger.debug(ENTER);
			final String args[] = {qno};
			final String[][] excessDetails = runner.multipleSelection("select EXCESS_PREMIUM,EXCESS_SIGN from HOME_POSITION_MASTER where QUOTE_NO=?", args);
		logger.debug(EXIT);
		
		return excessDetails;
	}
	
	public String getDomesticStaffNo(final String qno) throws BaseException {
		logger.info("getDomesticStaffNo method()");
		logger.debug(ENTER);
			final String args[] = {qno};
			final String res = runner.singleSelection("select NO_DOMESTIC_STAFF from HOME_DOMESTIC_STAFF where QUOTE_NO=?", args);
		logger.debug(EXIT);
		
		return res;
	}
	// need to fix while check with system by Rajesh
	public String domesticUpdation(final int totalRow,final int len,final String staffNo,final String qno)throws BaseException {
		final StringBuffer deleteCount=new StringBuffer();
		for (int d = 0; d < len; d++) {
			final int dsSno = 1000 + d;
			deleteCount.append(dsSno);
			deleteCount.append(',');
			if (d < totalRow) {
				final String dsupsql = "update HOME_DOMESTIC_STAFF set NO_DOMESTIC_STAFF=? where QUOTE_NO=? and DOMESTIC_STAFF_SNO=?";
				final String args2[] = {staffNo,qno,Integer.toString(dsSno)};
				runner.multipleUpdation(dsupsql,args2);
			} else {
				final String dsinssql = "insert into HOME_DOMESTIC_STAFF(QUOTE_NO,NO_DOMESTIC_STAFF,DOMESTIC_STAFF_SNO) values(?,?,?)";
				final String args3[] = {qno,staffNo,Integer.toString(dsSno)};
				runner.multipleInsertion(dsinssql,args3);
			}
		}
		return deleteCount.toString();
	}
	
	public void addDomesticStaffNo(final String qno, final String staffNo)throws BaseException {
		String deleteCount;
		final StringBuffer deleteCounts = new StringBuffer(1000);
		logger.info("addDomesticStaffNo method()");
		logger.debug(ENTER);
		ValidationFormat validObj = new ValidationFormat();
			final String args[] = {qno};
			final String testres = runner.singleSelection("select count(*) from HOME_DOMESTIC_STAFF where QUOTE_NO=?",args);
			int len=0;
			if (validObj.isNumberValue(staffNo)){
				len = Integer.parseInt(staffNo);
			}
			if ("0".equals(testres)) {
				for (int d = 0; d < len; d++) {
					final int dsSno = 1000 + d;
					deleteCounts.append(dsSno);
					deleteCounts.append(',');
					final String args1[] = {qno,staffNo,Integer.toString(dsSno)};
					runner.multipleInsertion("insert into HOME_DOMESTIC_STAFF(QUOTE_NO,NO_DOMESTIC_STAFF,DOMESTIC_STAFF_SNO) values(?,?,?)",args1);
				}
				deleteCount = deleteCounts.toString();
			} else  {
				final int totalRow = Integer.parseInt(testres);
				deleteCount = domesticUpdation(totalRow,len,staffNo,qno);
			}
			domesticDeletion(deleteCount,qno);
			logger.debug(EXIT);
			
	}

	public String[][] getSourcesList()throws BaseException {
		logger.info("getSourcesList method()");
		logger.debug(ENTER);
		final String[][] result = runner.multipleSelection("select Customer_Source_ID,upper(Customer_Source_NAME) from HOME_Customer_Source_MASTER order by Customer_Source_NAME");
		logger.debug(EXIT);
		
		return result;
	}
	
	public String[][] getOccupationList()throws BaseException {
		logger.info("getOccupationList method()");
		logger.debug(ENTER);
		final String[][] result = runner.multipleSelection("select OCCUPATION_ID,OCCUPATION_NAME from HOME_OCCUPATION_MASTER order by OCCUPATION_NAME");
		logger.debug(EXIT);
		
		return result;
	}

	public String getBackDatesAllowed(final String user, final String pid)throws BaseException {
		logger.info("getBackDatesAllowed method()");
		logger.debug(ENTER);
			final String args1[] = {user,pid};
			final String result = runner.singleSelection("select nvl(BACK_DATE_ALLOWED,'0') from login_user_details where agency_code in(select oa_code from login_master where login_id=?) and product_id=?", args1);
		logger.debug(EXIT);
		
		return result;
	}
		
	public String[][] getReferralTypes(final String loginIds, final String pid)throws BaseException {
		final HomeValidationBean homeValid = new HomeValidationBean();
		logger.info("getReferralTypes method()");
		logger.debug(ENTER);
		//final String sql = "select login_id from login_master where agency_code=(select agency_code from login_master where login_id=?) and login_id not in('NONE','NON')";
		final String args[] = {loginIds};
		final String[][] valuess = runner.multipleSelection(LOGINSQL, args);
		final String loginids = homeValid.removeLastChar(homeValid.getStringFromArray(valuess),',');
			final String query = "select QUOTE_NO,(nvl(REFERRAL_DESCRIPTION,'')||' - '||nvl(SUMMARY_REMARKS,'')) from HOME_POSITION_MASTER where REFERRAL_DESCRIPTION is not null and QUOTE_NO in(select QUOTE_NO from HOME_POSITION_MASTER where login_id in("
					+ loginids + ") and PRODUCT_ID=? "+(isFleetStatus()?(" and fleet_no="+this.getFleetNo()):"")+") "+(isFleetStatus()?(" and fleet_no="+this.getFleetNo()):"");
			final String args1[] = {pid};
			final String[][] result = runner.multipleSelection(query, args1);
		logger.debug(EXIT);
		
		return result;
	}
	
	public String getCommision(final String loginId, final String pid)throws BaseException {
		logger.info("getCommision method()");
		logger.debug(ENTER);
		final String args[] = {loginId,pid};
		final String commision = runner.singleSelection("select nvl(commission,'0') from login_user_details where agency_code=(select oa_code from login_master where login_id=? and status='Y') and status='Y' and product_id=?", args);
		logger.debug(EXIT);
		
		return commision;
	}
	
	public String[][] getLoginProId(final String qno)throws BaseException {
		logger.info("getLoginProId method()");
		logger.debug(ENTER);
		final String args[] = {qno};
		final String[][] result = runner.multipleSelection("select LOGIN_ID,nvl(proposal_no,PRODUCT_ID) from HOME_POSITION_MASTER where QUOTE_NO=?", args);
		logger.debug(EXIT);
		
		return result;
	}
	
	public void updateClauses(final String clauses, final String quoteNo,
			final String effectiveDate, final String expDate, final String remarks, final String proId)throws BaseException {
		logger.info("updateClauses method()");
		logger.debug(ENTER);
			final String hour = getSysdateTime(quoteNo,qno);
			final String queryy = "update home_position_master set EFFECTIVE_DATE = to_date(?,'DD-MM-YYYY'), EXPIRY_DATE=to_date(?,'DD-MM-YYYY'),ENTRY_DATE=(select "+hour+" from dual),SUMMARY_CLAUSES = ?, SUMMARY_REMARKS = ? where quote_no=? and PRODUCT_ID = ?";
			final String args[] = {effectiveDate,expDate,clauses,remarks,quoteNo,proId};
			runner.multipleUpdation(queryy, args);
		logger.debug(EXIT);
		
	}
	
	public Map getReferralDetails(final String branch)throws BaseException {
		logger.info("getReferralDetails method()");
		logger.debug(ENTER);
			final Map hashtable = new HashMap();
			String cols[] = {branch};
			final String referralDetails[][] = runner.multipleSelection("select email_to, email_cc, email_subject, email_message, email_from_name, email_from_phoneno,email_from_faxno, email_type, remarks, status from mail_details where email_subject = 'Home Insurance Referral' and STATUS='Y' and BRANCH_CODE=?",cols);
			hashtable.put("referal", referralDetails);
			for (int i = 0; i < referralDetails.length; i++) {
					hashtable.put("emailto", referralDetails[0][0]);
					hashtable.put("emailcc", referralDetails[0][1]);
					hashtable.put("emailsubject", referralDetails[0][2]);
					hashtable.put("emailmessage", referralDetails[0][3]);
					hashtable.put("emailname", referralDetails[0][4]);
					hashtable.put("emailphno", referralDetails[0][5]);
					hashtable.put("emailfax", referralDetails[0][6]);
					hashtable.put("emailtype", referralDetails[0][7]);
					hashtable.put("emailrema", referralDetails[0][8]);
					hashtable.put("emailstat", referralDetails[0][9]);
			}
		logger.debug(EXIT);
		
		return (HashMap)hashtable;
	}
	
	public String[][] getFromEmailForLogin(final String login)throws BaseException {
		logger.info("getFromEmailForLogin method()");
		logger.debug(ENTER);
			final String args[] = {login};
			final String mailFrom[][] = runner.multipleSelection("select per.email,bro.company_name from personal_info per,broker_company_master bro where per.oa_code=bro.agency_code and per.agency_code=(Select agency_code from login_master where login_id=?)",args);
		logger.debug(EXIT);
		
		return mailFrom;
	}
	
	public String getFixedorNot(final String pid, final String coverId)throws BaseException {
		logger.info("getFixedorNot method()");
		logger.debug(ENTER);
		final String args[] = {pid,coverId};
		final String result = runner.singleSelection("select DISPLAY_TYPE from HOME_COVER_MASTER where PRODUCT_ID=? and COVER_ID=?", args);
		logger.debug(EXIT);
		
		return result;
	}
	
	public String[][] getValidationSubIds(final String pid)throws BaseException {
		logger.info("getValidationSubIds method()");
		logger.debug(ENTER);
		final String args[] = {pid};
		final String[][] result = runner.multipleSelection("select VALID_RATING_FACTOR_ID,VALID_RATING_FACTOR_SUB_ID,VALID_PERSONAL_SUB_ID from HOME_COVER_MASTER where product_id=? and cover_id<=3 order by cover_id", args);
		logger.debug(EXIT);
		
		return result;
	}
	
	public String[][] getHomeProducts()throws BaseException {
		logger.info("getHomeProducts method()");
		logger.debug(ENTER);
		final String[][] prodcutDetails1 = runner.multipleSelection("select product_id,PRODUCT_DESCRIPTION from HOME_PRODUCT_MASTER where BROKER_ID is null order by product_id");
		logger.debug(EXIT);
		
		return prodcutDetails1;
	}
	
	public String[][] getHomeProducts(final String pid)throws BaseException {
		logger.info("getHomeProducts arg method()");
		logger.debug(ENTER);
			final String sql = "select product_id,PRODUCT_DESCRIPTION from HOME_PRODUCT_MASTER where BROKER_ID is null and product_id not in('"
					+ pid + "') order by product_id";
			final String[][] prodcutDetails1 = runner.multipleSelection(sql);
		logger.debug(EXIT);
		
		return prodcutDetails1;
	}
	
	public String getTotalPremium(final String quoteNo)throws BaseException {
		logger.info("getTotalPremium method()");
		logger.debug(ENTER);
		final String args[] = {quoteNo};
		final String premium = runner.singleSelection("select nvl(premium,'0') from home_position_master where quote_no = ?", args);
		logger.debug(EXIT);
		
		return premium;
	}
	
	public String getCoverStatus(final String pid, final String coverId)throws BaseException {
		logger.info("getCoverStatus method()");
		logger.debug(ENTER);
		final String args[] = {pid,coverId};
			String status = runner.singleSelection("select nvl(status,'N') from home_cover_master where product_id=? and cover_id=?", args);
			if (status.length() <= 0){
				status = "N";
			}
		logger.debug(EXIT);
		
		return status;
	}
	
	public String getPids(final String productIds)throws BaseException {
		final HomeValidationBean homeValid = new HomeValidationBean();
		logger.info("getPids method()");
		logger.debug(ENTER);
		final String pids[][] = runner.multipleSelection("select Product_id from home_product_master where product_id in("+ productIds + ") order by product_id");
		final String ProIds = homeValid.removeLastChar(homeValid.getStringFromArray(pids),',');
		logger.debug(EXIT);
		
		return ProIds;
	}
	
	public String getPNames(final String productIds) throws BaseException {
		final HomeValidationBean homeValid = new HomeValidationBean();
		logger.info("getPNames method()");
		logger.debug(ENTER);
		final String pids[][] = runner.multipleSelection("select PRODUCT_DESCRIPTION from home_product_master where product_id in("+ productIds + ") order by product_id");
		final String ProIds = homeValid.removeLastChar(homeValid.getStringFromArray(pids),',');
		logger.debug(EXIT);
		
		return ProIds;
	}
	
	public String[][] getCoverNames() throws BaseException {
		logger.info("getCoverNames method()");
		logger.debug(ENTER);
		final String covers[][] = runner.multipleSelection("select distinct(cover_description),cover_id from home_cover_master where status='Y' order by cover_id");
		logger.debug(EXIT);
		
		return covers;
	}
	
	public String getPolicyWordingFileName(final String pid, final String loginBranch) throws BaseException {
		logger.info("getPolicyWordingFileName method()");
		logger.debug(ENTER);
		final String args[] = {pid,loginBranch};
		final String fileName = runner.singleSelection("select nvl(DETAIL_NAME,'Policy_Wording_Standard') from CONSTANT_DETAIL where CATEGORY_ID=? and CATEGORY_DETAIL_ID='7' and BRANCH_CODE=?", args);
		logger.debug(EXIT);
		
		return fileName;
	}
	
	public String getReferralNames(final String qid) throws BaseException {
		logger.info("getReferralNames method()");
		logger.debug(ENTER);
		final String args[] = {qid};
		final String fileName = runner.singleSelection("select trim(nvl(REFERRAL_DESCRIPTION, ' ')) from home_position_master where quote_no=?", args);
		logger.debug(EXIT);
		
		return fileName;
	}
	
	public String getSumInsuredReferralNames(final String qid)throws BaseException {
		logger.info("getSumInsuredReferralNames method()");
		logger.debug(ENTER);
		final StringBuffer result = new StringBuffer();
			final String args[] = {qid};
			final String refName = runner.singleSelection("select REFERRAL_DESCRIPTION from home_position_master where quote_no=? and REFERRAL_DESCRIPTION like '%SumInsured Referral%'", args);
			if (refName.length() <= 0){
				result.append("NotRef");
			}
			else if (refName.length() > 0){
				result.append("Ref");
			}
		logger.debug(EXIT);
		
		return result.toString();
	}
	
	public void updateSumInsuredReferralNames(final String qid, final String pid,
			final String referralName)throws BaseException {
		logger.info("updateSumInsuredReferralNames method()");
		logger.debug(ENTER);
			final String args[] = {referralName,qid,pid};
			runner.multipleUpdation("update HOME_POSITION_MASTER set REFERRAL_DESCRIPTION=? where QUOTE_NO=? and product_id=?", args);
		logger.debug(EXIT);
		
	}
	
	public String getAdminReferralUpdationForTravel(final String quoteNo,
			final String adminRefStatus, final String adminRemarks, final String sign,
			final String excesPre)  throws BaseException {
		logger.info("getAdminReferralUpdation method()");
		logger.debug(ENTER);
			final String res = adminUpdateExcessSignPremium(quoteNo,sign,excesPre,adminRefStatus,adminRemarks);
			final double premium = getPremiumWithExcess(quoteNo,sign,excesPre);
			double commis = 0.00;
			double totcom = 0.00;
			final String result1[][] = getTravelLoginProId(quoteNo);
			if (result1.length > 0){
				commis = Double.parseDouble(getCommision((result1[0][0] == null ? "0":result1[0][0]),
						(result1[0][1] == null ? "0":result1[0][1])));
			}
			totcom = getCommissionAmount(premium,commis);
			final String[] args1 = {Double.toString(premium),Double.toString(totcom),quoteNo};
			runner.multipleUpdation("update HOME_POSITION_MASTER set OVERALL_PREMIUM=?,COMMISSION=? where QUOTE_NO=?", args1);
		logger.debug(EXIT);
		
		return res;
	}
	
	public String[][] getTravelLoginProId(final String qno)throws BaseException {
		logger.info("getTravelLoginProId method()");
		logger.debug(ENTER);
			final String[] args = {qno};
			final String[][] result  = runner.multipleSelection("select LOGIN_ID,PRODUCT_ID from HOME_POSITION_MASTER where QUOTE_NO=?", args);
		logger.debug(EXIT);
		
		return result;
	}
	
	public int insertPaymentDetails(final String policyNo, final String merchantId,
			final String amount, final String receiptNo, final String resCode, final String author,
			final String batchNo, final String transactionNo, final String cardType, final String pid)throws BaseException {
		logger.info("insertPaymentDetails method()");
		logger.debug(ENTER);
		final String[] args = {policyNo,merchantId,amount,receiptNo,resCode,author,batchNo,transactionNo,cardType,pid};
			runner.multipleInsertion("insert into payment (MERCHTXNREF_POLICY_NO,MERCHANTID,AMOUNT_PREMIUM,RECEIPTNO,ACQRESPONSECODE,AUTHORIZEID,BATCHNO,TRANSACTIONNO,CARDTYPE,TRANSACTION_DATE,STATUS,product_id) values(?,?,?,?,?,?,?,?,?,(select sysdate from dual),'Y',?)", args);
		logger.debug(EXIT);
		
		return 1;
	}
	
	public String[][] getBedRoomDetails(final String qno)throws BaseException {
		logger.info("getBedRoomDetails method()");
		logger.debug(ENTER);
			final String[] args = {qno};
			final String[][] result = runner.multipleSelection("select hcm.BED_ROOM,hct.PRODUCT_SUMINSURED from HOME_COVERAGE_TRANSACTION hct,HOME_POSITION_MASTER hcm where hcm.quote_no=? and hct.cover_id='2' and hct.home_sno='1002' and hcm.quote_no=hct.quote_no", args);
		logger.debug(EXIT);
		
		return result;
	}
	
	public String getReferralStatus(final String qid)throws BaseException {
		logger.info("getReferralStatus method()");
		logger.debug(ENTER);
			final String args[] = {qid};
			final String status = runner.singleSelection("select nvl(REMARKS,'Noraml') from HOME_POSITION_MASTER where QUOTE_NO=?", args);
		logger.debug(EXIT);
		
		return status;
	}
	
	public String getMaxApplicationNo() throws BaseException{
		final String applicationNo = runner.singleSelection("select max((APPLICATION_NO))+1 from home_position_master where product_id not in('30')");
		final StringBuffer applicationNos = new StringBuffer();
		if (applicationNo == null || applicationNo.equals("") || applicationNo.length() <= 0){
			applicationNos.append('1');
		}
		else{
			applicationNos.append(applicationNo);
		}
		return applicationNos.toString();
	}
	
	// Loading/Discount new in Broker side
	public boolean getLoadDisStatus(final String login, final String pid) throws BaseException {
		logger.info("getLoadDisStatus method()");
		logger.debug(ENTER);
		final String args[] = {login,pid};
		final String res = runner.singleSelection("select nvl(PROVISION_FOR_PREMIUM,'N') from login_user_details where agency_code=(select oa_code from login_master where login_id=?) and product_id=?", args);
		final boolean result = getBooleanStatus(res,"Y");
		logger.debug(EXIT);
		
		return result;
	}
	
	public boolean getBooleanStatus(final String content,final String conditionCheck) throws BaseException{
		boolean result;
		if (content != null && content.equalsIgnoreCase(conditionCheck)) {
			result = true;
		}
		else{
			result = false;
		}
		return result;
	}
	
	public void updateExcessSignPremium(final String quoteNo, final String sign,final String excesPre) throws BaseException{
		logger.info("updateExcessSignPremium method()");
		logger.debug(ENTER);
		final String excesSign = getSign(sign,excesPre);
		final String args[] = {excesPre,excesSign,quoteNo};
		runner.multipleUpdation("update HOME_POSITION_MASTER set EXCESS_PREMIUM=?,EXCESS_SIGN=? where QUOTE_NO=?", args);
		logger.debug(EXIT);
		
	}
	
	public String getSign(final String sign,final String excesPre) throws BaseException{
		final StringBuffer excesSign = new StringBuffer();
		if (excesPre.length() > 0&& "Plus".equalsIgnoreCase(sign)){
			excesSign.append('+');
		}
		else if (excesPre.length() > 0 && "Minus".equalsIgnoreCase(sign)){
			excesSign.append('-');
		}
		return excesSign.toString();
	}
	
	public String hasContent(final String content) throws BaseException{
		String contents = content.trim();
		if (contents.length() <= 0){
			contents = "";
		}
		return contents;
	}
	
	public double getPremiumWithExcess(final String quoteNo, final String sign, final String excesPre) throws BaseException{
		final String args[] = {quoteNo};
		String pre = runner.singleSelection("select nvl(PREMIUM,'0') from HOME_POSITION_MASTER where QUOTE_NO=?", args);
		pre = hasContent(pre);
		double premium = 0.0;
		final String excesSign = getSign(sign,excesPre);
		if (excesSign.equalsIgnoreCase("+") && !excesPre.equals("")&& (!pre.equals("") && pre != null)){
			premium = Double.parseDouble(pre)+ Double.parseDouble(excesPre);
		}
		else if (excesSign.equalsIgnoreCase("-")&& !excesPre.equals("")	&& (!pre.equals("") && pre != null)){
			premium = Double.parseDouble(pre)- Double.parseDouble(excesPre);
		}
		else if ((!pre.equals("") && pre != null)){
			premium = Double.parseDouble(pre);
		}
		return premium;
	}
	
	// Loading/Discount new in Broker side
	public int getPremium(final String qno, final int amt) throws BaseException {
		int excess = 0;
		logger.info("getPremium method()");
		logger.debug(ENTER);
		final String args[] = {qno};
		final String res = runner.singleSelection("select nvl(premium,'0') from HOME_POSITION_MASTER where quote_no=?", args);
		if (res.length() > 0) {
			final int amount = amt / 100;
			excess = amount % (Integer.parseInt(res));
		}
		logger.debug(EXIT);
		
		return excess;
	}
	
	public String getContentExcessPremium(final String qno)  throws BaseException{
		logger.info("getContentExcessPremium method()");
		logger.debug(ENTER);
		final String args[] = {qno};
		final String res = runner.singleSelection("select nvl(sum(nvl(PREMIUM,0)),'0') from HOME_COVERAGE_TRANSACTION where quote_no=? and HOME_SNO<1000", args);
		final StringBuffer resBuf = new StringBuffer();
		if (res.length() <= 0) {
			resBuf.append('0');
		}
		else{
			resBuf.append(res);
		}
		logger.debug(EXIT);
		
		return resBuf.toString();
	}
	
	public String[][] getHomePolicyExcess(final String coverId,final String pid) throws BaseException{
		final HomeValidationBean homeValid = new HomeValidationBean();
		logger.info("getHomePolicyExcess method()");
		logger.debug(ENTER);
		final String args[] = {pid};
		final String exRes[][] = runner.multipleSelection("select EXCESS_ID from HOME_CLAUSES_ID_MASTER where PRODUCT_ID=? and COVER_ID in("
				+ coverId + ") and SPECIAL_ID='0'", args);
		StringTokenizer excessToken;
		final StringBuffer makePids = new StringBuffer();
		for (int i = 0; i < exRes.length; i++) {
			excessToken = new StringTokenizer(exRes[i][0], ",");
			while (excessToken.hasMoreTokens()) {
				makePids.append(excessToken.nextToken());
				makePids.append(',');
			}
		}
		final String exIds = homeValid.removeLastChar(makePids.toString(),',');
		final String result[][] = runner.multipleSelection("select CLAUSES_DESCRIPTION from HOME_CLAUSES_MASTER where EXCESS_ID in("+exIds+") and SPECIAL_ID='0' and status='Y' order by display_order");
		logger.debug(EXIT);
		
		return result;
	}
	
	public String[][] getHomePolicySpecialCondition(final String coverId,final String pid) throws BaseException{
		final HomeValidationBean homeValid = new HomeValidationBean();
		logger.info("getHomePolicySpecialCondition method()");
		logger.debug(ENTER);
		final String args[] = {pid};
		final String spRes[][] = runner.multipleSelection("select SPECIAL_ID from HOME_CLAUSES_ID_MASTER where PRODUCT_ID=? and COVER_ID in("
				+ coverId + ") and EXCESS_ID='0'", args);
		final StringBuffer makePids = new StringBuffer();
		StringTokenizer spToken;
		for (int i = 0; i < spRes.length; i++) {
			spToken = new StringTokenizer(spRes[i][0], ",");
			while (spToken.hasMoreTokens()) {
				makePids.append(spToken.nextToken());
				makePids.append(',');
			}
		}
		final String spIds = homeValid.removeLastChar(makePids.toString(),',');
		final String result[][] = runner.multipleSelection("select CLAUSES_DESCRIPTION from HOME_CLAUSES_MASTER where SPECIAL_ID in("+ spIds+ ") and EXCESS_ID='0' and status='Y' order by display_order");
		logger.debug(EXIT);
		
		return result;
	}
	
	public String[][] getPolicyClauses(final String pid, final String coverId, final String status) throws BaseException {
		logger.info("getPolicyClauses method()");
		logger.debug(ENTER);
		final Map policyHash = new HashMap();
		if (status.equalsIgnoreCase("Excess")) {
			final String result[][] = getHomePolicyExcess(coverId,pid);
			policyHash.put("policy",result);
		} else if (status.equalsIgnoreCase("Special")) {
			final String result[][] = getHomePolicySpecialCondition(coverId,pid);
			policyHash.put("policy",result);
		}
		logger.debug(EXIT);
		
		return (String[][])policyHash.get("policy");
	}
	
	public String[][] getAreaCityId() throws BaseException {
		logger.info("getAreaCityId method()");
		logger.debug(ENTER);
		final String result[][] = runner.multipleSelection("select RESIDING_PROPERTY_NAME,city_name from HOME_NAKHEEL_RESIDING_PROPERTY order by RESIDING_PROPERTY_NAME");
		logger.debug(EXIT);
		
		return result;
	}
	
	public String getBTOCQuote(final String qno) throws BaseException {
		logger.info("getBTOCQuote method()");
		logger.debug(ENTER);
		final String args[] = {qno};
		final String res = runner.singleSelection("select nvl(BTOC,'Nil') from HOME_POSITION_MASTER where quote_no=?", args);
		logger.debug(EXIT);
		
		return res;
	}
	
	public String getBTOCQuoteCustomerMailId(final String qno) throws BaseException {
		logger.info("getBTOCQuoteCustomerMailId method()");
		logger.debug(ENTER);
		final String args[] = {qno};
		final String res = runner.singleSelection("select email from personal_info where customer_id=(select customer_id from home_position_master where quote_no=?)", args);
		logger.debug(EXIT);
			
		return res;
	}
	/** BTOC **/
	public void settingPaymentStatus(final String qno, final String status) throws BaseException {
		logger.info("settingPaymentStatus method()");
		logger.debug(ENTER);
		// N - Entered, Y- Success, F- Failure
		final String args[] = {status,qno};
		runner.multipleUpdation("update HOME_POSITION_MASTER set PAYMENT_STATUS=? where quote_no=?",args);
		logger.debug(EXIT);
			
	}
	public void settingPaymentStatus(final String qno, final String status, final String loyalty, final String memberNo)
	throws BaseException{
		logger.info("settingPaymentStatus method()");
		logger.debug(ENTER);
			// N - Entered, Y- Success, F- Failure
			String args[] = {status,loyalty,memberNo,qno};
			runner.multipleUpdation("update HOME_POSITION_MASTER set PAYMENT_STATUS=?,csh_id_typ_code=?,airmiles_no=? where quote_no=?",args);
		logger.debug(EXIT);
			
	}
	public String[][] getCoverPremiumStatus(final String productID, final String coverID) throws BaseException {
		logger.info("getCoverPremiumStatus method()");
		logger.debug(ENTER);
		final String args[] ={productID,coverID};
		final String[][] result = runner.multipleSelection("select Add_premium_status, add_sum_limit, ADD_RATING_FACTOR_ID,ADD_RATING_FACTOR_SUB_ID from home_cover_master where product_id=? and cover_id=?",args);
		logger.debug(EXIT);
			
		return result;
	}
	
	public double getAdditionPremium(final String value, final String proID, final String coverID) throws BaseException {
		logger.info("getAdditionPremium method()");
		logger.debug(ENTER);
			final String[][] idResult = getCoverPremiumStatus(proID, coverID);
			final String[][] result = queryForGettingSingleDimensionRange(idResult[0][3], value, idResult[0][2]);
			final double addPremium = Double.parseDouble(value) * Double.parseDouble(result[0][2]);
		logger.debug(EXIT);
			
		return addPremium;
	}
	
	public String[][] queryForGettingSingleDimensionRange(
			final String ratingFactorSubId, final String premiumData,
			final String ratingFactorId)  throws BaseException{
		logger.info("queryForGettingSingleDimensionRange method()");
		logger.debug(ENTER);
			final String[] args = {ratingFactorId,ratingFactorSubId,ratingFactorSubId,ratingFactorId};
			final long premiumDataValue = Long.parseLong(premiumData);
			final String claimResult[][] = runner.multipleSelection("select data_from,data_to,data_value,amend_id,relative_range_id from single_dimension_range where rating_factor_id = ? and  rating_factor_sub_id = ? and effective_date <= sysdate and "
				+ premiumDataValue
				+ " between data_from and data_to and amend_id=(select max(amend_id) from single_dimension_range where rating_factor_sub_id = ?  and rating_factor_id = ? and effective_date <= sysdate and "
				+ premiumDataValue + " between data_from and data_to)", args);
		logger.debug(EXIT);
				
		return claimResult;
	}
	
	public String getSysdateTime(final String qnoBranch,final String status) throws BaseException
	{
		logger.info("getSysdateTime method()");
		logger.debug(ENTER);
		/** Get Hours To Add Sysdate**/
		final StringBuffer systemDate = new StringBuffer("sysdate");
		if(status.equalsIgnoreCase(QNO)){
			final String argss[] = {"62","1","Y",qnoBranch};
			final String hour = runner.singleSelection("select nvl(DETAIL_NAME,'0') from constant_detail where category_id=? and category_detail_id=? and status=? and branch_code=(select branch_code from broker_company_master where agency_code=(select oa_code from login_master where login_id=(select login_id from home_position_master where quote_no=?)))",argss);
		    systemDate.append('+');
		    systemDate.append(hour);
		}
		else if(status.equalsIgnoreCase("Branch")){
			final String argss[] = {"62","1","Y",qnoBranch};
			final String hour= runner.singleSelection("select nvl(DETAIL_NAME,'0') from constant_detail where category_id=? and category_detail_id=? and status=? and branch_code=?",argss);
			systemDate.append('+');
		    systemDate.append(hour);
		}
		else if(status.equalsIgnoreCase("LoginId")){
			final String argss[] = {"62","1","Y",qnoBranch};
			final String hour = runner.singleSelection("select nvl(DETAIL_NAME,'0') from constant_detail where category_id=? and category_detail_id=? and status=? and branch_code=(select branch_code from broker_company_master where agency_code=(select oa_code from login_master where login_id=?))",argss);
		    systemDate.append('+');
		    systemDate.append(hour);
		}
		/** Get Hours To Add Sysdate**/
		logger.debug(EXIT);
		
		return systemDate.toString();
	}
	
	public String[][] getUWMaster(final String pid, final String branchCode) throws BaseException{
		String uwIds;
		logger.info("getUWMaster method()");
		logger.debug(ENTER);
		final String args[] = {pid,branchCode};
		uwIds = runner.singleSelection("select UW_ID from HOME_UWID_MASTER where PRODUCT_ID=? and BRANCH_CODE=? and status='Y'", args);
		final String result[][] = runner.multipleSelection("select UW_DESCRIPTION from HOME_UW_MASTER where UW_ID in("+uwIds+") and status='Y'");
		logger.debug(EXIT);
		
		return result;
	}
	
	public void updateDateDetails(final Map policyCollection,final String hour)throws BaseException{
		logger.info("updateDateDetails method()");
		logger.debug(ENTER);
		final String queryy = "update home_position_master set EFFECTIVE_DATE = to_date(?,'DD-MM-YYYY'),EXPIRY_DATE=to_date(?,'DD-MM-YYYY'),ENTRY_DATE=(select "
			+ hour+ " from dual),SUMMARY_CLAUSES = ?,csh_id_typ_code=?,airmiles_no=? where quote_no=? and PRODUCT_ID =?";
		final String args[] = {(String)policyCollection.get("EffectiveDate"),(String)policyCollection.get("ExpDate"),
				((String)policyCollection.get("clauses")).trim(),(String)policyCollection.get("Loyalty"),(String)policyCollection.get("memberNo"),(String)policyCollection.get(qno),
						(String)policyCollection.get(PROID)};
		runner.multipleUpdation(queryy, args);
		logger.debug(EXIT);
		
	}
	
	public void updatePolicyDetails(final Map policyCollection,final String hour,final String loginBra)throws BaseException{
		logger.info("updatePolicyDetails method()");
		logger.debug(ENTER);
		final String argsss[] = {(String)policyCollection.get(qno),(String)policyCollection.get(PROID)};
		final String policyNo = runner.singleSelection("select policy_no from home_position_master where quote_no=? and PRODUCT_ID=? and policy_no is not null",argsss);
		if (policyNo.equalsIgnoreCase("")) {
			final String remarksPol = policyGeneration((String)policyCollection.get(qno),(String)policyCollection.get(PROID),(String)policyCollection.get("branch"));
			final String args1[] = {remarksPol,(String)policyCollection.get("paymentMode"),(String)policyCollection.get(qno)};
			runner.multipleUpdation("update home_position_master set policy_no=?,status='P',INCEPTION_DATE=(select "
										+ hour+ " from dual),PAYMENT_MODE=? where quote_no=?",	args1);
			updateDebitDetails(policyCollection,hour,loginBra);
			closeTrnDateCalculation((String)policyCollection.get(qno),loginBra,"HTOS");
		}
		logger.debug(EXIT);
		
	}
	
	public void updateDebitDetails(final Map policyCollection,final String hour,final String loginBra)throws BaseException{
		logger.info("updateDabitDetails method()");
		logger.debug(ENTER);
		final String args2[] = {(String)policyCollection.get(PROID)};
		final String debitNo = runner.singleSelection("select debit_note_no from home_position_master where quote_no in("+(String)policyCollection.get(qno)+") and PRODUCT_ID=? and debit_note_no is not null",args2);
		if (debitNo.equalsIgnoreCase("")) {
			final String debitNoteNo = getMaxDebitNo((String)policyCollection.get(qno),(String)policyCollection.get(PROID),(String)policyCollection.get("branch"));
				final String args3[] = {debitNoteNo};
				runner.multipleUpdation("update home_position_master set debit_note_no=?,debit_note_date=(select "+ hour+ " from dual) where quote_no in("+(String)policyCollection.get(qno)+")",args3);
		}
		logger.debug(EXIT);
		
	}
	
	//close trn date calculation
	public String[][] getCloseDate(final String loginBranch,final String quoteNo,final String policy)throws BaseException{
		
		String closeDates[][];
		String pdtCoreCode = "";
		String cols[] = new String[2];
		cols[0] = quoteNo;
		cols[1] = loginBranch;
		pdtCoreCode = runner.singleSelection("select nvl(pm.rsacode,0) from product_master pm,home_position_master hpm where hpm.quote_no=? and pm.status='Y' and pm.branch_code=? and pm.product_id=nvl(hpm.PROPOSAL_NO,hpm.product_id)", cols);
		if("HTOS".equalsIgnoreCase(policy)){
			String args[];
			args = new String[3];
			args[0] = loginBranch;
			args[1] = pdtCoreCode;
			args[2] = quoteNo;
			closeDates = runner.multipleSelection("select to_char(trn.clo_date_closed,'YYYY') as years,to_char(trn.clo_date_closed,'mm') as months,to_char(trn.clo_date_closed,'dd') as days,to_char(trn.clo_date_opened,'YYYY') as years," +
				"to_char(trn.clo_date_opened,'mm') as months,to_char(trn.clo_date_opened,'dd') as days from t_trn_closing trn,product_master pm,home_position_master hpm " +
				"where pm.BRANCH_CODE=trn.branch_code and trn.branch_code=? and pm.product_id=nvl(hpm.PROPOSAL_NO,hpm.product_id) and  pm.rsacode=trn.PRODUCT_CORE_CODE and pm.rsacode=? and hpm.quote_no=?",args);
		}else{
			String args[];
			args = new String[1];
			args[0] = loginBranch;
			closeDates = runner.multipleSelection("select to_char(trn.clo_date_closed,'YYYY') as years,to_char(trn.clo_date_closed,'mm') as months,to_char(trn.clo_date_closed,'dd') as days,to_char(trn.clo_date_opened,'YYYY') as years," +
					"to_char(trn.clo_date_opened,'mm') as months,to_char(trn.clo_date_opened,'dd') as days from t_trn_closing trn,product_master pm,home_position_master hpm " +
					"where pm.BRANCH_CODE=trn.branch_code and trn.branch_code=? and pm.product_id=nvl(hpm.PROPOSAL_NO,hpm.product_id) and pm.rsacode=trn.product_core_code and hpm.quote_no in("+quoteNo+")",args);
		}
		return closeDates;
	}
	public void closeTrnDateCalculation(final String quoteNo,final String loginBranch,final String policy)throws BaseException{
		logger.info("closeTrnDateCalculation method()");
		logger.debug(ENTER);
		Date sysDates;
		Date sysDates1;
		Date closeDate;
		Date openDate;
		String closeDates[][];
		closeDates = getCloseDate(loginBranch,quoteNo,policy);
		sysDates = new Date();
		if(closeDates.length>0){
			boolean policyFlag = true;
			sysDates1 = new Date(sysDates.getYear(), sysDates.getMonth(),sysDates.getDate());
			sysDates = sysDates1;
	
			closeDate = new Date(Integer.parseInt(closeDates[0][0]) - 1900,Integer.parseInt(closeDates[0][1]) - 1, Integer
							.parseInt(closeDates[0][2]));
			openDate = new Date(Integer.parseInt(closeDates[0][3]) - 1900,Integer.parseInt(closeDates[0][4]) - 1, Integer
							.parseInt(closeDates[0][5]));

			if (sysDates.compareTo(openDate) >= 0&& sysDates.compareTo(closeDate) <= 0) {
				policyFlag = false;
			} else {
				sysDates = openDate;
			}
			if (policyFlag) {
				String args1[] = new String[3];
				args1[0] = com.maan.common.util.OracleDateConversion
						.ConvertDate(sysDates.getDate() + "-"+ (sysDates.getMonth() + 1) + "-"+ (sysDates.getYear() + 1900));
				args1[1] = com.maan.common.util.OracleDateConversion
						.ConvertDate(sysDates.getDate() + "-"+ (sysDates.getMonth() + 1) + "-"	+ (sysDates.getYear() + 1900))
						+ " 01.01.01.000000 AM";
				if("HTOS".equalsIgnoreCase(policy)){
					args1[2] = quoteNo;
					runner.multipleUpdation("update home_position_master set debit_note_date=?,inception_date=?  where  quote_no=?",args1);
				}else{
					args1[2] = policy;
					runner.multipleUpdation("update home_position_master set debit_note_date=?,inception_date=?  where  policy_no=?",args1);
				}
			}
		}
		logger.debug(EXIT);
		
	}
	
	public void quoteGeneration(Map quoteDetails)throws BaseException{
		logger.info("quoteGeneration method()");
		logger.debug(ENTER);
		final String applicationNo = getMaxApplicationNo();
		final String args[] = {(String)quoteDetails.get(qNO)};
		final String CheckQuote = runner.singleSelection("select count(*) from home_position_master where quote_no=?",args);
		final String hour = getSysdateTime((String)quoteDetails.get("branch"), "Branch");
		if ("".equalsIgnoreCase(CheckQuote)|| "0".equalsIgnoreCase(CheckQuote)) {
			final String sql = "insert into home_position_master(quote_no,customer_id,company_id,login_id,amend_id,product_id,INCEPTION_DATE,EFFECTIVE_DATE,ENTRY_DATE,EXPIRY_DATE,status,APPLICATION_NO,BED_ROOM,BTOC) values(?,?,'20',?,?,?,(select "
					+ hour
					+ " from dual),to_date(?,'DD-MM-YYYY'),(select "
					+ hour
					+ " from dual),to_date(?,'DD-MM-YYYY'),'Y',?,?,?)";
				final String args1[] = {(String)quoteDetails.get(qNO),(String)quoteDetails.get("customerid"),(String)quoteDetails.get("Login_id"),"0",
						(String)quoteDetails.get("product_id"),(String)quoteDetails.get("EffectiveDate"),(String)quoteDetails.get("ExpDate"),applicationNo,(String)quoteDetails.get("roomNo"),(String)quoteDetails.get("btoc")};
				runner.multipleInsertion(sql, args1);
		} else {
			final String sql = "update home_position_master set  INCEPTION_DATE=(select "
					+ hour
					+ " from dual),EFFECTIVE_DATE=to_date(?,'DD-MM-YYYY'),EXPIRY_DATE=to_date(?,'DD-MM-YYYY'),ENTRY_DATE=(select "
					+ hour
					+ " from dual),customer_id=?,BED_ROOM=? where quote_no=?";
				final String args2[] = {(String)quoteDetails.get("EffectiveDate"),(String)quoteDetails.get("ExpDate"),(String)quoteDetails.get("customerid"),(String)quoteDetails.get("roomNo"),(String)quoteDetails.get(qNO)};
				runner.multipleUpdation(sql, args2);
		}
		logger.debug(EXIT);
		
	}
	public String getProductName(final String id,final String loginBra) throws BaseException
	{
		logger.info("getProductName method()");
		logger.debug(ENTER);
		String PName = "";
		final String args[] = new String[1];
		if (!id.equalsIgnoreCase("ALL"))
		{
				args[0] = loginBra;
				PName = runner.singleSelection("select product_name from product_master where product_id in("+id+") and branch_code=? and status='Y'", args);
				if (id.equals("22") || id.equals("31") || id.equals("35")
						|| id.equals("36") || id.equals("37")
						|| id.equals("50") || id.equals("51")
						|| id.equals("52")) {
					PName = "TRAVEL INSURANCE";
				}
			}
		logger.debug(EXIT);
	    
		return PName;
	}
	public String[][] getDiscountMinPremium(String quote_no) throws BaseException{
		logger.info("getDiscountMinPremium method()");
		logger.debug(ENTER);
			final String args[] = {quote_no,quote_no};
			final String result[][] = runner.multipleSelection("select nvl(DISCOUNT_OF_PREMIUM,'0'),nvl(MIN_PREMIUM_AMOUNT,'0') from LOGIN_USER_DETAILS where agency_code=(select oa_Code from login_master where login_id=(select login_id FROM home_position_master where quote_no=?)) and product_id=(select nvl(proposal_no,product_id) from home_position_master where quote_no=?)",args);
		logger.debug(EXIT);
	    
		return result;
	}
	public void airUpdation(final String loyalty,final String memberNo,final String quoteno)throws BaseException
	{
		logger.info("airUpdation method()");
		logger.debug(ENTER);
			final String args[] = {loyalty,memberNo,quoteno};
			runner.multipleUpdation("update home_position_master set csh_id_typ_code=?,airmiles_no=? where quote_no=?",args);
		logger.debug(EXIT);
	    
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public void removeDemestic(final String quoteNo)throws BaseException
	{
		logger.info("removeDemestic method()");
		logger.debug(ENTER);
			final String cols[] = {quoteNo};
			runner.multipleUpdation("delete from HOME_DOMESTIC_STAFF where QUOTE_NO=?",cols);
		logger.debug(EXIT);
	    
	}
	public Map isSingleArtReferral(final String quoteNo,final String coverId)throws BaseException
	{
		Map status;status = new HashMap();
			String cols[] = {quoteNo,coverId};
			String sql;sql = "select case when hct.PRODUCT_SUMINSURED>hcm.single_limit then 'Yes' else 'No' end,hcm.single_limit from HOME_COVERAGE_TRANSACTION hct,HOME_COVER_MASTER hcm,HOME_POSITION_MASTER hpm " +
					"where hct.quote_no=? and hct.cover_id=? and hct.home_sno<1000 and hct.COVER_ID=hcm.cover_id " +
					"and hpm.product_id = hcm.product_id and hct.quote_no=hpm.quote_no and hcm.single_valid_status='Y'";
			String result[][] = runner.multipleSelection(sql,cols);
			for(int i=0;i<result.length;i++){
				if(result[i][0].equalsIgnoreCase("Yes")){
					status.put("Status", result[i][0]);
					status.put("Limit", result[i][1]);
					break;
				}
			}
		return status;
	}
	public String[][] gettingAreas(final String countryId)throws BaseException {
		logger.info("gettingAreas method()");
		logger.debug(ENTER);
		
		String result[][];
		String arg[]={countryId};
		String qry;
		qry="select RESIDING_PROPERTY_NAME,RESIDING_PROPERTY_ID,CITY_NAME from HOME_RESIDING_PROPERTY where country_id=? order by RESIDING_PROPERTY_ID";
		result=runner.multipleSelection(qry, arg);
		
		logger.debug(EXIT);
	    
	    return result;
	}
	public Map getReferralDetails(final String branch,final String type)throws BaseException {
		logger.info("getReferralDetails method()");
		logger.debug(ENTER);
		String rem="";
		if("Travel".equalsIgnoreCase(type)){
			rem="Travel Insurance Referral";
		}else if("Health".equalsIgnoreCase(type)){
			rem="Health Insurance Referral";
		}else{
			rem="Home Insurance Referral";
		}

			final Map hashtable = new HashMap();
			String cols[] = {branch};
			final String referralDetails[][] = runner.multipleSelection("select email_to, email_cc, email_subject, email_message, email_from_name, email_from_phoneno,email_from_faxno, email_type, remarks, status from mail_details where email_subject = '"+rem+"' and STATUS='Y' and BRANCH_CODE=?",cols);
			hashtable.put("referal", referralDetails);
			
			System.out.println("referralDetails: "+referralDetails.length);
			
			for (int i = 0; i < referralDetails.length; i++) {
					hashtable.put("emailto", referralDetails[0][0]);
					hashtable.put("emailcc", referralDetails[0][1]);
					hashtable.put("emailsubject", referralDetails[0][2]);
					hashtable.put("emailmessage", referralDetails[0][3]);
					hashtable.put("emailname", referralDetails[0][4]);
					hashtable.put("emailphno", referralDetails[0][5]);
					hashtable.put("emailfax", referralDetails[0][6]);
					hashtable.put("emailtype", referralDetails[0][7]);
					hashtable.put("emailrema", referralDetails[0][8]);
					hashtable.put("emailstat", referralDetails[0][9]);
			}
		logger.debug(EXIT);
		
		return (HashMap)hashtable;
	}
	public String getApplicaitonNo(String quoteNo) {
		final String args[] = {quoteNo};
		final String appNo = runner.singleSelection("select APPLICATION_NO from home_position_master where quote_no=?", args);
		return appNo;
	}
	
	public HashMap getMailInfo(final String branch)throws BaseException {
		logger.info("getMailInfo method()");
		final Map hashtable = new HashMap();
		String cols[] = {branch};
		final String mailDetails[][] = runner.multipleSelection("select SMTP_HOST, SMTP_USER, SMTP_PWD, 'Emirates', ADDRESS,SMTP_PORT from mail_master");//,cols);
		for (int i = 0; i < mailDetails.length; i++) {
				hashtable.put("hostname", mailDetails[0][0]);
				hashtable.put("username", mailDetails[0][1]);
				hashtable.put("password", mailDetails[0][2]);
				hashtable.put("webaddress", mailDetails[0][3]);
				hashtable.put("Address", mailDetails[0][4]);
				hashtable.put("port", mailDetails[0][5]);
		}
		
		return (HashMap)hashtable;
	}
	
}// Class close

