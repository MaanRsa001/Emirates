package com.maan.Home.DataManipualtion;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Logger;

import com.maan.common.LogUtil;
import com.maan.common.exception.BaseException;
import com.maan.services.util.runner;
public class HomeReferralBean
{
	private static final Logger logger = LogUtil.getLogger(HomeReferralBean.class);
	final static transient private String EDECLARATION = ",27,";
	final static transient private String INVALID = "Invalid";
	final static transient private String LOWERCOMPANY = " and (lower(trim(b.company_name)) like '%";
	final static transient private String LOWERFIRST = "%' or lower(trim(b.first_name)) like '%";
	final static transient private String QUOTESQL = "select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.INCEPTION_DATE,'dd')as days,to_char(a.INCEPTION_DATE,'MM')as months,to_char(a.INCEPTION_DATE,'YYYY')as years,initcap(b.first_name),initcap(b.LAST_NAME),a.login_id,b.COMPANY_NAME,a.REFERRAL_DESCRIPTION,a.application_no from home_position_master a,personal_info b where a.login_id in (";
	final static transient private String REFSQL = " and a.status='R' and b.customer_id=a.customer_id and a.ADMIN_REFERRAL_STATUS='N'";
	final static transient private String PIDSQL = " and a.product_id in(";
	final static transient private String PAYSQL = " and a.PAYMENT_STATUS is null order by a.quote_no desc";
	final static transient private String MULTISQL = "select a.customer_id,initcap(b.first_name||b.LAST_NAME||b.COMPANY_NAME),a.login_id,b.COMPANY_NAME,a.REFERRAL_DESCRIPTION,count(a.quote_no) from home_position_master a,personal_info b where a.login_id in (";
	final static transient private String GROUPSQL =" group by a.customer_id,initcap(b.first_name||b.LAST_NAME||b.COMPANY_NAME),a.login_id,b.COMPANY_NAME,a.REFERRAL_DESCRIPTION";
	final static transient private String STATUSSQL = ") and a.status='Y' and b.customer_id=a.customer_id and (a.REMARKS in ('";
	final static transient private String SINSQL = "SingleSql";
	final static transient private String ENTER = "- Enter";
	final static transient private String EXIT = "- Exit";
	final static transient private String LOGINSQL = "select login_id from login_master where agency_code=(select agency_code from login_master where login_id=?) and login_id not in('NONE','NON')";
	final static transient private String UTYPE = "Broker";
	final static transient private String UBROKER = "UserBroker";
	
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

	public Map getReferralQuotesSearch(final String loginIds,
				final String searchOption, final String searchValue, final String productId,
				final String option, final String schemeId)throws BaseException {
		final HomeValidationBean homeValid = new HomeValidationBean();
			logger.info("getReferralQuotesSearch method()");
			logger.debug(ENTER);
			final Map refDetails = new HashMap();
			String msql;
			final String declarationRef = EDECLARATION;
			final Map queryCollection = new HashMap();
				final String remarks = getReferralCondition(option);
				final String offdata = getOfficeReferralCheckSql(productId,declarationRef);
				String sql = "select login_id from login_master where agency_code=(select agency_code from login_master where login_id=?) and login_id not in('NONE','NON')";
				final String args[] = {loginIds};
				final String[][] valuess = runner.multipleSelection(LOGINSQL, args);
				final String loginids = homeValid.removeLastChar(homeValid.getStringFromArray(valuess),',');
				final String scheme = getSchemeCheckSql(schemeId);
				final String availStatus = getReferralAvailStatus(option);
						sql = "select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.INCEPTION_DATE,'dd')as days,to_char(a.INCEPTION_DATE,'MM')as months,to_char(a.INCEPTION_DATE,'YYYY')as years,initcap(b.first_name),initcap(b.LAST_NAME),a.login_id,b.COMPANY_NAME ,a.REFERRAL_DESCRIPTION,a.application_no from home_position_master a,personal_info b where a.login_id in ("
								+ loginids
								+ STATUSSQL
								+ remarks
								+ "')) "
								+ offdata
								+ PIDSQL
								+ productId
								+ ") "
								+ scheme+(isFleetStatus()?(" and a.fleet_no="+this.getFleetNo()):"")
								+ PAYSQL;
						queryCollection.put("SingleSqlAvailselect", sql);
						msql = MULTISQL
								+ loginids
								+ STATUSSQL
								+ remarks
								+ "'))  and a.REFERRAL_DESCRIPTION='"+ declarationRef+ "' "+PIDSQL
								+ productId
								+ ") "
								+ scheme+(isFleetStatus()?(" and a.fleet_no="+this.getFleetNo()):"")
								+ GROUPSQL;
						queryCollection.put("MultipleSqlAvailselect", msql);
						sql = QUOTESQL
								+ loginids
								+ ") "+REFSQL
								+ offdata
								+ PIDSQL
								+ productId
								+ ") "
								+ scheme+(isFleetStatus()?(" and a.fleet_no="+this.getFleetNo()):"")
								+ PAYSQL;
						queryCollection.put("SingleSqlUnAvailselect", sql);
						msql = MULTISQL
								+ loginids
								+ ") and a.status='R' and b.customer_id=a.customer_id and (a.REMARKS in ('"
								+ remarks
								+ "'))  and a.REFERRAL_DESCRIPTION='"
								+ declarationRef
								+ "' and a.ADMIN_REFERRAL_STATUS='N'"+PIDSQL
								+ productId
								+ ") "
								+ scheme+(isFleetStatus()?(" and a.fleet_no="+this.getFleetNo()):"")
								+ GROUPSQL;
						queryCollection.put("MultipleSqlUnAvailselect", msql);
						sql = QUOTESQL
								+ loginids
								+ STATUSSQL
								+ remarks
								+ "')) "
								+ offdata
								+ LOWERCOMPANY
								+ searchValue.trim().toLowerCase()
								+ LOWERFIRST
								+ searchValue.trim().toLowerCase()+ "%') "+PIDSQL
								+ productId
								+ ") "
								+ scheme+(isFleetStatus()?(" and a.fleet_no="+this.getFleetNo()):"")
								+ PAYSQL;
						queryCollection.put("SingleSqlAvailFIRST_NAME", sql);
						msql = MULTISQL
								+ loginids
								+ STATUSSQL
								+ remarks
								+ "')) "+LOWERCOMPANY
								+ searchValue.trim().toLowerCase()
								+ LOWERFIRST
								+ searchValue.trim().toLowerCase()
								+ "%') and a.REFERRAL_DESCRIPTION='"
								+ declarationRef
								+ "' "+PIDSQL
								+ productId
								+ ") "
								+ scheme+(isFleetStatus()?(" and a.fleet_no="+this.getFleetNo()):"")
								+ GROUPSQL;
						queryCollection.put("MultipleSqlAvailFIRST_NAME", msql);
						sql = QUOTESQL
								+ loginids
								+ ") "+REFSQL
								+ offdata
								+ LOWERCOMPANY
								+ searchValue.trim().toLowerCase()
								+ LOWERFIRST
								+ searchValue.trim().toLowerCase()
								+ "%') "+PIDSQL
								+ productId
								+ ") "
								+ scheme+(isFleetStatus()?(" and a.fleet_no="+this.getFleetNo()):"")
								+ PAYSQL;
						queryCollection.put("SingleSqlUnAvailFIRST_NAME", sql);
						msql = MULTISQL
								+ loginids
								+ ") and a.status='R' and b.customer_id=a.customer_id and (a.REMARKS in ('"
								+ remarks
								+ "')) and a.ADMIN_REFERRAL_STATUS='N' "+LOWERCOMPANY
								+ searchValue.trim().toLowerCase()
								+ LOWERFIRST
								+ searchValue.trim().toLowerCase()
								+ "%') and a.REFERRAL_DESCRIPTION='"
								+ declarationRef
								+ "' "+PIDSQL
								+ productId
								+ ") "
								+ scheme+(isFleetStatus()?(" and a.fleet_no="+this.getFleetNo()):"")
								+ GROUPSQL;
						queryCollection.put("MultipleSqlUnAvailFIRST_NAME", msql);
						sql = QUOTESQL
								+ loginids
								+ STATUSSQL
								+ remarks
								+ "')) "
								+ offdata
								+ " and (a.quote_no like '%"
								+ searchValue
								+ "%') "+PIDSQL
								+ productId
								+ ") "
								+ scheme+(isFleetStatus()?(" and a.fleet_no="+this.getFleetNo()):"")
								+ PAYSQL;

						queryCollection.put("SingleSqlAvailquote_nos", sql);
						sql = QUOTESQL
								+ loginids
								+ ") "+REFSQL
								+ offdata
								+ " and (a.quote_no like '%"
								+ searchValue
								+ "%') "+PIDSQL
								+ productId
								+ ") "
								+ scheme+(isFleetStatus()?(" and a.fleet_no="+this.getFleetNo()):"")
								+ PAYSQL;

						queryCollection.put("SingleSqlUnAvailquote_nos", sql);
						final String dateSearchValue = getDateSearchValue(searchValue,searchOption);
						sql = QUOTESQL
								+ loginids
								+ STATUSSQL
								+ remarks
								+ "')) "
								+ offdata
								+ " and to_char(a.entry_date,'dd/MM/YYYY')='"
								+ dateSearchValue.trim()
								+ "' "+PIDSQL
								+ productId
								+ ") "
								+ scheme+(isFleetStatus()?(" and a.fleet_no="+this.getFleetNo()):"")
								+ PAYSQL;
						queryCollection.put("SingleSqlAvailDateWise", sql);
						msql = MULTISQL
								+ loginids
								+ STATUSSQL
								+ remarks
								+ "')) and to_char(a.entry_date,'dd/MM/YYYY')='"
								+ dateSearchValue.trim()
								+ "' and a.REFERRAL_DESCRIPTION='"
								+ declarationRef
								+ "' "+PIDSQL
								+ productId
								+ ") "
								+ scheme+(isFleetStatus()?(" and a.fleet_no="+this.getFleetNo()):"")
								+ GROUPSQL;
						queryCollection.put("MultipleSqlAvailDateWise", msql);
						sql = QUOTESQL
								+ loginids
								+ ") "+REFSQL
								+ offdata
								+ " and to_char(a.entry_date,'dd/MM/YYYY')='"
								+ dateSearchValue.trim()
								+ "'  "+PIDSQL
								+ productId
								+ ") "
								+ scheme+(isFleetStatus()?(" and a.fleet_no="+this.getFleetNo()):"")
								+ PAYSQL;
						queryCollection.put("SingleSqlUnAvailDateWise", sql);
						msql = MULTISQL
								+ loginids
								+ ") and a.status='R' and b.customer_id=a.customer_id and (a.REMARKS in ('"
								+ remarks
								+ "'))  and a.REFERRAL_DESCRIPTION='"
								+ declarationRef
								+ "' and a.ADMIN_REFERRAL_STATUS='N' and to_char(a.entry_date,'dd/MM/YYYY')='"
								+ dateSearchValue.trim()
								+ "' "+PIDSQL
								+ productId
								+ ") "
								+ scheme+(isFleetStatus()?(" and a.fleet_no="+this.getFleetNo()):"")
								+ GROUPSQL;
						queryCollection.put("MultipleSqlUnAvailDateWise", msql);
				final String single[][] =	runner.multipleSelection((String)queryCollection.get(SINSQL+availStatus+searchOption));
				final String multipleSql = (String)queryCollection.get("MultipleSql"+availStatus+searchOption);
				if (multipleSql!=null&&multipleSql.length() > 0){
					final String multiple[][] = runner.multipleSelection(multipleSql);
					refDetails.put("Multiple", multiple);
				}
				refDetails.put("Single", single);
			logger.debug(EXIT);
			
			return (HashMap)refDetails;
		}

	public Map getViewQuotes123(final String loginIds, final String option,final String productId, final String schemeId)throws BaseException 
	{
		final HomeValidationBean homeValid = new HomeValidationBean();
			logger.info("getViewQuotes123 method()");
			logger.debug(ENTER);
			final Map refDetails = new HashMap();
			String msql;
			final String declarationRef = EDECLARATION;
			final String remarks = getReferralCondition(option);
			final String offdata = getOfficeReferralCheckSql(productId,declarationRef);
				String sql;
				final String args[] = {loginIds};
				final String[][] valuess = runner.multipleSelection(LOGINSQL, args);
				final String loginids = homeValid.removeLastChar(homeValid.getStringFromArray(valuess),',');
				final String scheme = getSchemeCheckSql(schemeId);

				if (option.equalsIgnoreCase("rej")) {
					sql = "select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.INCEPTION_DATE,'dd')as days,to_char(a.INCEPTION_DATE,'MM')as months,to_char(a.INCEPTION_DATE,'YYYY')as years,initcap(b.first_name),initcap(b.LAST_NAME),a.login_id,b.COMPANY_NAME ,a.REFERRAL_DESCRIPTION,a.application_no from home_position_master a,personal_info b where a.login_id in ("
						+ loginids
						+ ") "+REFSQL
						+ offdata
						+ PIDSQL
						+ productId
						+ ") "
						+ scheme+(isFleetStatus()?(" and a.fleet_no="+this.getFleetNo()):"")
						+ PAYSQL;

				msql = MULTISQL
						+ loginids
						+ ") and a.status='R' and b.customer_id=a.customer_id and a.REFERRAL_DESCRIPTION='"+ declarationRef+ "' "+PIDSQL
						+ productId
						+ ") "
						+ scheme+(isFleetStatus()?(" and a.fleet_no="+this.getFleetNo()):"")
						+ GROUPSQL;
				
				} else {
					sql = "select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.INCEPTION_DATE,'dd')as days,to_char(a.INCEPTION_DATE,'MM')as months,to_char(a.INCEPTION_DATE,'YYYY')as years,initcap(b.first_name),initcap(b.LAST_NAME),a.login_id,b.COMPANY_NAME ,a.REFERRAL_DESCRIPTION,a.application_no from home_position_master a,personal_info b where a.login_id in ("
						+ loginids
						+ STATUSSQL
						+ remarks
						+ "')) "
						+ offdata
						+ PIDSQL
						+ productId
						+ ") "
						+ scheme+(isFleetStatus()?(" and a.fleet_no="+this.getFleetNo()):"")
						+ PAYSQL;

				msql = MULTISQL
						+ loginids
						+ STATUSSQL
						+ remarks
						+ "'))  and a.REFERRAL_DESCRIPTION='"+ declarationRef+ "' "+PIDSQL
						+ productId
						+ ") "
						+ scheme+(isFleetStatus()?(" and a.fleet_no="+this.getFleetNo()):"")
						+ GROUPSQL;
				}
				final String[][] singleResult = runner.multipleSelection(sql);
				final String[][] multipleResult = runner.multipleSelection(msql);
				refDetails.put("Single", singleResult);
				refDetails.put("Multiple", multipleResult);
				logger.debug(EXIT);
				
			return (HashMap)refDetails;
		}
		
	public String getReferralCondition(final String option)throws BaseException
	{
			final StringBuffer remarks = new StringBuffer();
			if (option.equalsIgnoreCase("app")){
				remarks.append("Admin");
			}
			else if (option.equalsIgnoreCase("unapp")){
				remarks.append("Referal");
			}
			return remarks.toString();
	}
		
	public String getOfficeReferralCheckSql(final String productId,final String declarationRef)throws BaseException
	{
			final StringBuffer offdata = new StringBuffer(1000);
			if (productId.equalsIgnoreCase("30")){
				offdata.append(" and a.REFERRAL_DESCRIPTION!='" + declarationRef + "'");
			}
			return offdata.toString();
	}
		
	public String getSchemeCheckSql(final String schemeId)throws BaseException
	{
			String scheme;
			if (schemeId.equalsIgnoreCase("Home")){
				scheme = "and a.scheme_id is null";
			}
			else{
				scheme = "and a.scheme_id='" + schemeId + "'";
			}
			return scheme;
	}
		
	public String getReferralAvailStatus(final String option)throws BaseException
	{
			final StringBuffer result = new StringBuffer();
			if(option.equalsIgnoreCase("unapp")||option.equalsIgnoreCase("app")){
				result.append("Avail");
			}
			else{
				result.append("UnAvail");
			}
			return result.toString();
	}
		
	public String getDateSearchValue(final String searchValue,final String searchOption)throws BaseException
	{
		String result="";
		if(searchOption.equalsIgnoreCase("DateWise")){	
			final String searchValue1 = searchValue.substring(0, searchValue.indexOf('/'));
			final String searchValue2 = searchValue.substring(searchValue.indexOf('/') + 1, searchValue.lastIndexOf('/'));
			final String searchValue3 = searchValue.substring(searchValue.lastIndexOf('/') + 1, searchValue.length());
			if (Integer.parseInt(searchValue2) < 10){
				result = searchValue1 + "/" + "0"	+ Integer.parseInt(searchValue2) + "/"	+ searchValue3;
			}
			else{
				result = searchValue1 + "/" + Integer.parseInt(searchValue2) + "/"	+ searchValue3;
			}
		}
		return result;
	}
		
	public String[][] getUserBrokerInformationforReferral(final String login,final String pid) throws BaseException 
	{
			logger.info("getUserBrokerInformationforReferral method()");
			logger.debug(ENTER);
			final Map useHash = new HashMap();
				if (getUserTypeInformation(login).equalsIgnoreCase(UTYPE)) {
					final String sql =  "select login_id from personal_info where login_id in(select distinct login_id from home_position_master where REMARKS is not null and lower(status) in('y') and PRODUCT_ID in("
							+ pid
							+ ") and login_id in(select login_id from login_master where agency_code in(select agency_code from login_user_details where product_id=? and agency_code in (select agency_code from login_master where oa_code=(select oa_code from login_master where login_id=?))))) and login_id!='NONE' and application_id in ('2','3')";
					final String[] args = {pid,login};
					final String[][] userBroker = runner.multipleSelection(sql, args);
					useHash.put(UBROKER,userBroker);
				}
				else{
					useHash.put(UBROKER, new String[0][0]);
				}
			logger.debug(EXIT);
			
			return (String[][])useHash.get(UBROKER);
	}
		
	public String getUserTypeInformation(final String login) throws BaseException {
			logger.info("getUserTypeInformation method()");
			logger.debug(ENTER);
			final String[] args = {login};
			final String userType = runner.singleSelection("select nvl(USERTYPE,'Nil') from login_master where login_id=?", args);
			logger.debug(EXIT);
			
			return userType;
	}
}