/**
 * 
 *  Author  : Rajesh R  [16/08/2007]
 *	Company : MaanSarovar Software Private Limited  Chennai-1
 *	Project : E-MarineInsurance
 *  Purpose : This Bean To ManipulateSELECT The Date
 * 
 */
package com.maan.Home.DataManipualtion;

import java.util.Calendar;

import org.apache.logging.log4j.Logger;

import com.maan.common.LogUtil;
import com.maan.common.exception.BaseException;
import com.maan.common.util.StringUtil;
import com.maan.services.util.ValidationFormat;
import com.maan.services.util.runner;

public class DataSelectCustomer 
{
	private static final Logger logger = LogUtil.getLogger(DataSelectCustomer.class);

	public final static long SECOND_FACTOR = 1000;
	public final static long MINUTE_FACTOR = SECOND_FACTOR * 60;
	public final static long HOUR_FACTOR = MINUTE_FACTOR * 60;
	public final static long DAY_FACTOR = HOUR_FACTOR * 24;
	public final static long YEAR_FACTOR = DAY_FACTOR * 365;
	final static transient private String INVALID = "Invalid";
	final static transient private String SELECTS = "select";
	final static transient private String MONTHS = "MMM";
	final static transient private String YEARS = "YYYY";
	final static transient private String DAYS = "DD";
	final static transient private String ENTER = "- Enter";
	final static transient private String EXIT = "- Exit";
	
	public String[][] homeCustomerInfoSelect(final String LoginID,final String Customer_Id) throws BaseException 
	{
		logger.info("homeCustomerInfoSelect method()");
		logger.debug("- Enter");
		String result[][] = new String[0][0];
		final String args[] = { Customer_Id };
		result = runner.multipleSelection("select * from personal_info where customer_id=?", args);
		logger.debug("- Exit");
		
		return result;
	}
	public String getcutomerName(final String quote) throws BaseException{
		logger.info("getcutomerName method()");
		logger.debug("- Enter");
		final StringBuffer namesBuf = new StringBuffer();
		final String qry = "select nvl(first_name,company_name),last_name from personal_info where customer_id=(select customer_id from home_position_master where quote_no=?)";
		final String[] args = {quote};
		final String[][] returnVal = runner.multipleSelection(qry, args);
			if (returnVal.length > 0){
				namesBuf.append(returnVal[0][0] == null ? "" : returnVal[0][0]);
				namesBuf.append(" ");
				namesBuf.append(returnVal[0][1] == null ? "" : returnVal[0][0]);
			}
		logger.debug("- Exit");
		
		return namesBuf.toString();
	}
	public String getCustomerId(final String qno) throws BaseException
	{
		logger.info("getCustomerId method()");
		logger.debug("- Enter");
		String result = "";
		final String args[] = { qno };
		result = runner.singleSelection("select CUSTOMER_ID from HOME_POSITION_MASTER where QUOTE_NO=?", args);
		if (result == null){
			result = "";
		}
		logger.debug("- Exit");
		
		return result;
	}

	public String getUserType(final String loginIds) throws BaseException {
		logger.info("getUserType method()");
		logger.debug("- Enter");
		String res;
		final String args[] = { loginIds };
		res = runner.singleSelection("select usertype from login_master where login_id=?", args);
		logger.debug("- Exit");
		
		return res;
	}

	public String[][] getExistingCustomerss123(final String loginIds)throws BaseException 
	{
		logger.info("getExistingCustomerss123 method()");
		logger.debug("- Enter");
		String[][] result = null;
		String[][] valuess;
		final String utype = getUserType(loginIds);
		if ("Freight".equalsIgnoreCase(utype)) {
			valuess = runner.multipleSelection("select login_id from personal_info where login_id in('"+loginIds+"') and login_id!='NONE' and application_id in ('5')");
		} else {
			final String args[] = { loginIds };
			valuess = runner.multipleSelection("select distinct login_id from personal_info where login_id in(select login_id from login_master where agency_code in(select agency_code from login_user_details where agency_code in (select agency_code from login_master where oa_code=(select oa_code from login_master where login_id=?)))) and login_id!='NONE' and application_id in ('1','2','3')", args);
		}

		String loginAllIds = "";
		for (int i = 0; i < valuess.length; i++) {
			loginAllIds = "'" + valuess[i][0] + "'," + loginAllIds;
		}
		if (loginAllIds.length() > 0){
			loginAllIds = loginAllIds.substring(0, loginAllIds.lastIndexOf(','));
		}
		else if(loginAllIds.length()==0)
		{
			loginAllIds = "''";
		}
		result = runner.multipleSelection("select customer_id,nvl(first_name,company_name),last_name,email,nvl(MOBILE,TELEPHONE),login_id,COMPANY_NAME,replace('P.O.BOX.'||nvl(pobox,' ')||nvl(address1,' ')||' '||nvl(emirate,' '),'select','') from personal_info where login_id in ("+loginAllIds+") and application_id=1 order by customer_id desc");
		logger.debug("- Exit");
		
		return result;
	}

	public String[][] getExistingCustomer(final String cusLoginId)throws BaseException {
		logger.info("getExistingCustomer method()");
		logger.debug("- Enter");
		String[][] select = new String[0][0];
		final String args[] = { cusLoginId, cusLoginId };
		select = runner.multipleSelection("select customer_id,nvl(first_name,company_name),last_name,email,nvl(MOBILE,TELEPHONE),login_id,COMPANY_NAME,replace('P.O.BOX.'||nvl(pobox,' ')||nvl(address1,' ')||' '||nvl(emirate,' '),'select','') from personal_info where (CUSTOMER_LOGIN_ID=? or  LOGIN_ID=?) and application_id=1 order by customer_id desc", args);
		logger.debug("- Exit");
		
		return select;
	}

	public String[][] getExistingCustomerss123(final String loginIds,final String customerName, final String searchBy)	throws BaseException 
	{
		logger.info("getExistingCustomerss123 method()");
		logger.debug("- Enter");
		
		String[][] select = new String[0][0];
		String[][] valuess = new String[0][0];
		String loginAllIds = "";
		final String utype = getUserType(loginIds);
		String sql = "";
		if("Freight".equalsIgnoreCase(utype)) {
			valuess = runner.multipleSelection("select login_id from personal_info where login_id in('"+loginIds+"') and login_id!='NONE' and application_id in ('5')");
		} else {
			final String args[] = { loginIds };
			valuess = runner.multipleSelection("select distinct login_id from personal_info where login_id in(select login_id from login_master where agency_code in(select agency_code from login_user_details where agency_code in (select agency_code from login_master where oa_code=(select oa_code from login_master where login_id=?)))) and login_id!='NONE' and application_id in ('1','2','3')", args);
		}
		for(int i = 0; i < valuess.length; i++) {
			loginAllIds = "'" + valuess[i][0] + "'," + loginAllIds;
		}
		if(loginAllIds.length() > 0){
			loginAllIds = loginAllIds.substring(0, loginAllIds.lastIndexOf(','));
		}
		if(searchBy.equalsIgnoreCase("FIRST_NAME")) {
			sql = "select customer_id,nvl(first_name,company_name),last_name,email,nvl(MOBILE,TELEPHONE),login_id,COMPANY_NAME,replace('P.O.BOX.'||nvl(pobox,' ')||nvl(address1,' ')||' '||nvl(emirate,' '),'select','') from personal_info where login_id in ("
					+ loginAllIds
					+ ")  and (lower(trim(first_name)) like ? or  lower(trim(last_name)) like ? or  lower(trim(COMPANY_NAME)) like ?) and application_id=1 order by customer_id desc";
			final String args[] = {
					"%" + (customerName.trim()).toLowerCase() + "%",
					"%" + (customerName.trim()).toLowerCase() + "%",
					"%" + (customerName.trim()).toLowerCase() + "%" };
			select = runner.multipleSelection(sql, args);
		} else {
			sql = "select customer_id,nvl(first_name,company_name),last_name,email,nvl(MOBILE,TELEPHONE),login_id,COMPANY_NAME,replace('P.O.BOX.'||nvl(pobox,' ')||nvl(address1,' ')||' '||nvl(emirate,' '),'select','') from personal_info where login_id in ("
					+ loginAllIds
					+ ")  and "
					+ searchBy
					+ " like ? and application_id=1 order by customer_id desc";
			final String args[] = { "%" + customerName.trim() + "%" };
			select = runner.multipleSelection(sql, args);
		}
		logger.debug("- Exit");
		
		return select;
	}

	public String[][] getCustomerData(final String id) throws BaseException 
	{
		logger.info("getCustomerData method()");
		logger.debug("- Enter");
		String[][] customer = new String[0][0];
		final String args[] = { id.trim() };
		try {
			customer = runner.multipleSelection("select customer_id,nvl(first_name,company_name),last_name,email,nvl(MOBILE,TELEPHONE),login_id,COMPANY_NAME from personal_info where customer_id=? and application_id='1'", args);
		} catch (Exception e) {
			logger.debug(e);
		}
		logger.debug("- Exit");
		
		return customer;
	}

	public String[][] getCustomerData(final String id, final String login_id) throws BaseException 
	{
		logger.info("getCustomerData method()");
		logger.debug("- Enter");
		String loginAllIds = "";
		String valuess[][];
		final String utype = getUserType(login_id);
		if ("Freight".equalsIgnoreCase(utype)) {
			valuess = runner.multipleSelection("select login_id from personal_info where login_id in('"+login_id+"') and login_id!='NONE' and application_id in ('5')");
		} else {
			final String args[] = { login_id };
			valuess = runner.multipleSelection("select distinct login_id from personal_info where login_id in(select login_id from login_master where agency_code in(select agency_code from login_user_details where agency_code in (select agency_code from login_master where oa_code=(select oa_code from login_master where login_id=?)))) and login_id!='NONE' and application_id in ('1','2','3')", args);
		}
		for (int i = 0; i < valuess.length; i++) {
			loginAllIds = "'" + valuess[i][0] + "'," + loginAllIds;
		}
		if (loginAllIds.length() > 0) {
			loginAllIds = loginAllIds.substring(0, loginAllIds.lastIndexOf(','));
		}
		String[][] customer = new String[0][0];
		final String args[] = { id };
		customer = runner.multipleSelection("select customer_id,nvl(first_name,company_name),last_name,email,nvl(MOBILE,TELEPHONE),login_id,COMPANY_NAME,replace('P.O.BOX.'||nvl(pobox,' ')||nvl(address1,' ')||' '||nvl(emirate,' '),'select','') from personal_info where customer_id=? and application_id='1' and login_id in("+loginAllIds+")", args);
		logger.debug("- Exit");
		
		return customer;
	}
	
	// From DataSelect - Aprl 27
	
	public String emailValidation(final String email)throws BaseException{
		final ValidationFormat validationFormat = new ValidationFormat();
		final StringBuffer emailBuf = new StringBuffer();
		if (email.length() > 0&& validationFormat.eMailValidation(email)) {
				emailBuf.append(runner.getErrormsg("20"));
				emailBuf.append(',');
		}
		return emailBuf.toString();
	}
	
	public String orgNameValidation(final String title,final String firstName) throws BaseException{
		final StringBuffer errorBuf = new StringBuffer();
		final com.maan.Home.DataManipualtion.DataManipualtion data = new com.maan.Home.DataManipualtion.DataManipualtion();
		if ("M/S".equalsIgnoreCase(title)) {
			final String values = data.validString(firstName, false);
			if ("needed".equalsIgnoreCase(values)) {
				errorBuf.append(runner.getErrormsg("60"));
				errorBuf.append(',');
			} else if (INVALID.equalsIgnoreCase(values)) {
				errorBuf.append(runner.getErrormsg("61"));
				errorBuf.append(',');
			}
		}
		return errorBuf.toString();
	}
	
	public String commonValidation(final String content, final String firstErrorId, final String secondErrorId) throws BaseException{
		final StringBuffer errorBuf = new StringBuffer();
		final com.maan.Home.DataManipualtion.DataManipualtion data = new com.maan.Home.DataManipualtion.DataManipualtion();
		final String values = data.validString(content, false);
		if ("needed".equalsIgnoreCase(values)) {
			errorBuf.append(runner.getErrormsg(firstErrorId));
			errorBuf.append(',');
		} else if (INVALID.equalsIgnoreCase(values)) {
			errorBuf.append(runner.getErrormsg(secondErrorId));
			errorBuf.append(',');
		}
		return errorBuf.toString();
	}
	
	public String commonSpecialValidation(final String firstName,final String message) throws BaseException{
		final StringBuffer errorBuf = new StringBuffer();
		boolean NameFlag = StringUtil.isNumberValue(firstName); // New jan18
		if (NameFlag && firstName.length() > 0) {
			errorBuf.append("Please enter valid ");
			errorBuf.append(message);
			errorBuf.append(',');
		}
		if (!NameFlag && firstName.length() > 0) // new Jan18
		{
			NameFlag = StringUtil.chkOnlySpecial(firstName);
			if (NameFlag) {
				errorBuf.append("Please enter valid ");
				errorBuf.append(message);
				errorBuf.append(',');
			}
		}
		return errorBuf.toString();
	}
	
	public String mobileValidation(final String mobile, final String telephone) throws BaseException{
		final StringBuffer errorBuf = new StringBuffer();
		final ValidationFormat validationFormat = new ValidationFormat();
		if (mobile.length() <= 0 && telephone.length() <= 0) {
			errorBuf.append(runner.getErrormsg("245"));
			errorBuf.append(',');
		}
		if (validationFormat.isDigitValidationFormat(mobile) == false) {
			errorBuf.append(runner.getErrormsg("277"));
			errorBuf.append(',');
		}
		if (validationFormat.isDigitValidationFormat(telephone) == false) {
			errorBuf.append(runner.getErrormsg("278"));
			errorBuf.append(',');
		}
		return errorBuf.toString();
	}
	
	public String poboxValidation(final String poBox) throws BaseException{
		final StringBuffer errorBuf = new StringBuffer();
		final ValidationFormat validationFormat = new ValidationFormat();
		final com.maan.Home.DataManipualtion.DataManipualtion data = new com.maan.Home.DataManipualtion.DataManipualtion();
		if ("needed".equalsIgnoreCase(data.validLength(poBox, 1))) {
			errorBuf.append(runner.getErrormsg("29"));
			errorBuf.append(',');
		}
		if (validationFormat.isDigitValidationFormat(poBox) == false) {
			errorBuf.append(runner.getErrormsg("28"));
			errorBuf.append(',');
		}
		return errorBuf.toString();
	}
	
	public String faxValidation(final String fax) throws BaseException{
		final StringBuffer errorBuf = new StringBuffer();
		final ValidationFormat validationFormat = new ValidationFormat();
		if (!validationFormat.isDigitValidationFormat(fax)) {
			errorBuf.append(runner.getErrormsg("279"));
			errorBuf.append(',');
		}
		return errorBuf.toString();
	}
	
	public String selectValidation(final String content,final String errorId) throws BaseException{
		final StringBuffer errorBuf = new StringBuffer();
		if (SELECTS.equalsIgnoreCase(content)) {
			errorBuf.append(runner.getErrormsg(errorId));
			errorBuf.append(',');
		}
		return errorBuf.toString();
	}
	
	public String occupationValidation(final String occupation,final String errorId) throws BaseException{
		final StringBuffer errorBuf = new StringBuffer();
		if (occupation == null) {
			errorBuf.append(runner.getErrormsg("282"));
			errorBuf.append(',');
		} else if ("0".equals(occupation)) {
				errorBuf.append(runner.getErrormsg("282"));
				errorBuf.append(',');
		}
		return errorBuf.toString();
	}
	
	public String otherSourceValidation(final String source,final String otherSource) throws BaseException{
		final StringBuffer errorBuf = new StringBuffer(1000);
		if (source.equalsIgnoreCase("others")&&otherSource.length() <= 0) {
				errorBuf.append("Please Enter Customer Source for Others,");
		}
		return errorBuf.toString();
	}
	
	public int calculateAge(final String yyyy, final String mmmm, final String dddd)throws BaseException {
		int result;
		if (yyyy==null||mmmm==null||dddd==null||yyyy.equalsIgnoreCase(YEARS) || mmmm.equalsIgnoreCase(MONTHS)|| dddd.equalsIgnoreCase(DAYS)){
			result = 2000;
		}
		else{
			final int years = Integer.parseInt(yyyy);
			final int months = Integer.parseInt(mmmm);
			final int days = Integer.parseInt(dddd);
			if (years == 0 && months == 0 && days == 0){
				result = 0;
			}
			else{
				final Calendar cal = Calendar.getInstance();
				cal.set(years, months, days);
				final Calendar sysDate = Calendar.getInstance();
				final long diff = sysDate.getTime().getTime() - cal.getTime().getTime();
				result = (int) (diff / YEAR_FACTOR);
			}
		}
		return result;
	}
	
	public String dobValidation(final String dobDay,final String dobMonth,final String dobYear) throws BaseException{
		final StringBuffer errorBuf = new StringBuffer();
		int ages;
		if (dobDay != null && dobMonth != null && dobYear != null&& dobDay.length() > 0 && dobMonth.length() > 0
				&& dobYear.length() > 0&&(!DAYS.equalsIgnoreCase(dobDay)	|| !MONTHS.equalsIgnoreCase(dobMonth)|| !YEARS.equalsIgnoreCase(dobYear))) {
				ages = calculateAge(dobYear, dobMonth, dobDay);
				if (ages == 2000) {
					errorBuf.append("Please Select Proper Date Of Birth,");
				} else {
					errorBuf.append(validateDateOfBirth(Integer.parseInt(dobYear),Integer.parseInt(dobMonth),Integer.parseInt(dobDay)));
			}
		}
		return errorBuf.toString();
	}
	
	public String validateDateOfBirth(final int year, final int month, final int day) throws BaseException {
		logger.info("validateDateOfBirth method()");
		logger.debug(ENTER);
		final StringBuffer mess = new StringBuffer(1000);
		if ((month == 2 && year % 4 == 0)&&(day == 30 || day == 31)) {
				mess.append("Please Select a Valid Date,");
		} else if ((month == 2 && year % 4 != 0)&&(day == 29 || day == 30 || day == 31)) {
				mess.append("Please Select a Valid Date,");
		} else if ((month == 4 || month == 6 || month == 9 || month == 11)&&day == 31) {
				mess.append("Please Select a Valid Date,");
		}
		logger.debug(EXIT);
		
		return mess.toString();
	}
	
	public String[][] titleCollection(final String branch)throws BaseException {
		logger.info("titleCollection method()");
		logger.debug(ENTER);
			final String args[] = {branch};
			final String[][] titles = runner.multipleSelection("select title_name from title_master where branch_code=? and language is null", args);
		logger.debug(EXIT);
		
		return titles;
	}
	
	public String[][] nationalityCollection()throws BaseException {
		logger.info("nationalityCollection method()");
		logger.debug(ENTER);
			final String[][] nationality = runner.multipleSelection("select distinct NATIONALITY_NAME from country_master order by NATIONALITY_NAME");
		logger.debug(EXIT);
		
		return nationality;
	}
	
	public String[][] countryCollection()throws BaseException {
		logger.info("countryCollection method()");
		logger.debug(ENTER);
		final String[][] country = runner.multipleSelection("select country_name from country_master");
		logger.debug(EXIT);
		
		return country;
	}
	
	public String[][] emirateCollection(final String cid) throws BaseException{
		logger.info("emirateCollection method()");
		logger.debug(ENTER);
			final String args[] = {cid};
			final String[][] emirate = runner.multipleSelection("select city_name from city_master where country_id=? and lower(city_name) not in('others') and city_name not in ('Jebel Ali') order by city_name", args);
		logger.debug(EXIT);
		
		return emirate;
	}
	
	public String[][] getSelectedCustomerDetails(String applicationNo,String  quoteNo) throws BaseException
	{
		logger.info("emirateCollection method()");
		logger.debug(ENTER);
		String val="";
		
		if(quoteNo!=null && quoteNo.length()>0)
		{
			val="quote_no="+quoteNo;
		}else
		{
			val="application_no = "+applicationNo;
		}
			
			final String query = " select customer_id,nvl(first_name,company_name),last_name,email,nvl(MOBILE,TELEPHONE),login_id,COMPANY_NAME, "
								+" replace('P.O.BOX.'||nvl(pobox,' ')||nvl(address1,' ')||' '||nvl(emirate,' '),'select','') from personal_info "
								+" where customer_id = (select customer_id from home_position_master where "+val+" )";
			final String[][] selCustDet = runner.multipleSelection(query);
		logger.debug(EXIT);
		
		return selCustDet;
		
	}
	public String getOpenCoverAdditionalInsuredInfo(String openCoverNo)
	{
		String sql= ""; 
		String result="";
		try{
			sql = "SELECT CASE WHEN A.ADDITIONAL_INSURED IS NULL THEN TO_CHAR(A.CUSTOMER_ID) ELSE TO_CHAR(A.CUSTOMER_ID)||','||A.ADDITIONAL_INSURED END FROM OPEN_COVER_MASTER A WHERE A.MISSIPPI_OPENCOVER_NO=? AND AMEND_ID=(SELECT MAX(AMEND_ID) FROM OPEN_COVER_MASTER WHERE MISSIPPI_OPENCOVER_NO=A.MISSIPPI_OPENCOVER_NO)";
			result = runner.singleSelection(sql,new String[]{openCoverNo});
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	public String getOpenCoverAdditionalInsured(String proposalNo)
	{
		String sql= ""; 
		String result="";
		try{
			sql = "SELECT CASE WHEN A.ADDITIONAL_INSURED IS NULL THEN TO_CHAR(A.CUSTOMER_ID) ELSE TO_CHAR(A.CUSTOMER_ID)||','||A.ADDITIONAL_INSURED END FROM OPEN_COVER_MASTER A WHERE A.PROPOSAL_NO=? AND AMEND_ID=(SELECT MAX(AMEND_ID) FROM OPEN_COVER_MASTER WHERE PROPOSAL_NO=A.PROPOSAL_NO)";
			result = runner.singleSelection(sql,new String[]{proposalNo});
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
}
