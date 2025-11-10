
package com.maan.Home.DataManipualtion;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Logger;

import com.maan.common.LogUtil;
import com.maan.common.exception.BaseException;
import com.maan.services.util.runner;

public class HomeQuoteRegisterBean
{
	private static final Logger logger = LogUtil.getLogger(HomeQuoteRegisterBean.class);
	final static transient private String UTYPE = "Broker";
	final static transient private String UBROKER = "UserBroker";
	final static transient private String INVALID = "Invalid";
	final static transient private String SINSQL = "SingleSql";
	final static transient private String SINARG = "SingleArgs";
	final static transient private String PAYSTATESQL =" and a.payment_status is null order by quote_no DESC";
	final static transient private String VIEWSQL="select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years," +
						"to_char(a.entry_date+30,'dd')as days,to_char(a.entry_date+30,'MM')as months,to_char(a.entry_date+30,'YYYY')as years,nvl(b.COMPANY_NAME,b.first_name),b.last_name,a.login_id,b.COMPANY_NAME,a.effective_date,nvl(a.application_no,0) from home_position_master a,personal_info b where a.login_id in (";
	final static transient private String ENTER = "- Enter";
	final static transient private String EXIT = "- Exit";
	final static transient private String LOGINSQL = "select login_id from login_master where agency_code=(select agency_code from login_master where login_id=?) and login_id not in('NONE','NON')";
	
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

	// Home Travel Office QuoteRegister
	public String[][] getViewQuotesNotApproved(final String loginIds, final String searchOption, final String searchValue, final String productId,final String schemeId)throws BaseException 
	{
		final HomeValidationBean homeValid = new HomeValidationBean();
		logger.info("getViewQuotesNotApproved method()");
		logger.debug(ENTER);
		final Map queryCollection = new HashMap();
			final String argss[] = {loginIds};
			final String[][] valuess = runner.multipleSelection(LOGINSQL, argss);
			final String loginAllIds = homeValid.removeLastChar(homeValid.getStringFromArray(valuess),',');
			final String scheme = getSchemeCheckSql(schemeId);

				String sql = VIEWSQL
						+ loginAllIds
						+ ") and a.ENTRY_DATE+30 >= (select sysdate from dual) and a.status='Y'   and a.remarks is null and a.ADMIN_REFERRAL_STATUS is null and b.customer_id=a.customer_id and a.product_id=? "
						+ scheme +(isFleetStatus()?(" and a.fleet_no="+this.getFleetNo()):"")
						+ PAYSTATESQL;
				final String args[] = {productId};
				queryCollection.put("SingleSqlselect", sql);
				queryCollection.put("SingleArgsselect", args);
				queryCollection.put("SingleSqlSelect", sql);
				queryCollection.put("SingleArgsSelect", args);
				queryCollection.put("SingleSql", sql);
				queryCollection.put("SingleArgs", args);

				sql = VIEWSQL
						+ loginAllIds
						+ ") and a.ENTRY_DATE+30 >= (select sysdate from dual) and a.status='Y' and a.remarks is null and b.customer_id=a.customer_id and (lower(trim(b.company_name)) like ? or lower(trim(b.first_name)) like ?) and a.ADMIN_REFERRAL_STATUS is null and a.product_id=? "
						+ scheme +(isFleetStatus()?(" and a.fleet_no="+this.getFleetNo()):"")
						+ PAYSTATESQL;
				final String args1[] = {"%" + (searchValue.trim()).toLowerCase() + "%","%" + (searchValue.trim()).toLowerCase() + "%",productId};
				queryCollection.put("SingleSqlFIRST_NAME", sql);
				queryCollection.put("SingleArgsFIRST_NAME", args1);

				sql = VIEWSQL
						+ loginAllIds
						+ ") and a.ENTRY_DATE+30 >= (select sysdate from dual) and a.status='Y' and a.remarks is null and b.customer_id=a.customer_id and (a.quote_no like ?) and a.ADMIN_REFERRAL_STATUS is null and a.product_id=? "
						+ scheme +(isFleetStatus()?(" and a.fleet_no="+this.getFleetNo()):"")
						+ PAYSTATESQL;
				final String args2[] = {"%" + (searchValue.trim()) + "%",productId};
				queryCollection.put("SingleSqlquote_nos", sql);
				queryCollection.put("SingleArgsquote_nos", args2);
				
				sql = VIEWSQL
						+ loginAllIds
						+ ") and a.ENTRY_DATE+30 >= (select sysdate from dual) and a.status='Y' and a.remarks is null and a.ADMIN_REFERRAL_STATUS is null and b.customer_id=a.customer_id and to_char(a.entry_date,'dd/MM/YYYY')=? and a.product_id=? "
						+ scheme +(isFleetStatus()?(" and a.fleet_no="+this.getFleetNo()):"")
						+ PAYSTATESQL;
				final String args3[] = { getDateSearchValue(searchValue.trim(),searchOption), productId};
				queryCollection.put("SingleSqlDateWise", sql);
				queryCollection.put("SingleArgsDateWise", args3);
				final String[][] quoteRegister = runner.multipleSelection((String)queryCollection.get(SINSQL+searchOption), (String[])queryCollection.get(SINARG+searchOption));
				logger.debug(EXIT);
				
		return quoteRegister;
	}

	public String[][] getViewQuotesNotApprovedExDate(final String loginIds, final String productId, final String schemeId)throws BaseException 
	{
		final HomeValidationBean homeValid = new HomeValidationBean();
		logger.info("getViewQuotesNotApprovedExDate method()");
		logger.debug(ENTER);
		String sql = "" ;
		final String argss[] = {loginIds,loginIds,loginIds};
		final String[][] valuess = runner.multipleSelection("select login_id from login_master where oa_code=(select agency_code from login_master where login_id=?) or created_by=(select agency_code from login_master where login_id=?) or login_id=? and login_id not in('NONE','NON')", argss);
		final String loginAllIds = homeValid.removeLastChar(homeValid.getStringFromArray(valuess),',');
		final String scheme = getSchemeCheckSql(schemeId);
		sql = "select  a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.entry_date+30,'dd')as days,to_char(a.entry_date+30,'MM')as months,to_char(a.entry_date+30,'YYYY')as years,(b.first_name||b.last_name||b.company_name),nvl(a.application_no,0) from home_position_master a, personal_info b WHERE a.login_id in ("
				+ loginAllIds
				+ ") and a.ENTRY_DATE+30 < (select sysdate from dual) and a.customer_id = b.customer_id and a.product_id=? "
				+ scheme +(isFleetStatus()?(" and a.fleet_no="+this.getFleetNo()):"")+ " and a.status='Y' and a.remarks is null";
		final String args[] = {productId};
		final String[][] lapseQuotes = runner.multipleSelection(sql, args);
		logger.debug(EXIT);
		
		return lapseQuotes;
	}

	public String[][] getPaymentCompletedQuotes(final String loginIds,final String productId, final String status)  throws BaseException
	{
		final HomeValidationBean homeValid = new HomeValidationBean();
		logger.info("getPaymentCompletedQuotes method()");
		logger.debug(ENTER);
		final String args[] = {loginIds};
		final String[][] valuess = runner.multipleSelection(LOGINSQL, args);
		final String loginAllIds = homeValid.removeLastChar(homeValid.getStringFromArray(valuess),',');
		final String[][] paymentDetails = runner.multipleSelection("select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.entry_date+30,'dd')as days,to_char(a.entry_date+30,'MM')as months,to_char(a.entry_date+30,'YYYY')as years,nvl(b.COMPANY_NAME,b.first_name),b.last_name,a.login_id,b.COMPANY_NAME,a.effective_date,nvl(PAYMENT_STATUS,''),nvl(OVERALL_PREMIUM,'0'),nvl(a.application_no,0) from home_position_master a,personal_info b where a.login_id in ("
				+ loginAllIds
				+ ") and a.ENTRY_DATE+30 >= (select sysdate from dual) and a.status='Y' and b.customer_id=a.customer_id and (a.product_id='"
				+ productId
				+ "' or a.PROPOSAL_NO='"
				+ productId
				+ "') and payment_status in("
				+ status
				+ ") order by quote_no DESC");
		logger.debug(EXIT);
			
		return paymentDetails;
	}

	public String[][] getUserBrokerInformation(final String login, final String pid) throws BaseException
	{
		logger.info("getUserBrokerInformation method()");
		logger.debug(ENTER);
		final Map useHash = new HashMap();
			if (getUserTypeInformation(login).equalsIgnoreCase(UTYPE)) {
				final String[] args = {pid,pid,login};
				final String[][] userBroker = runner.multipleSelection("select login_id from personal_info where login_id in(select distinct login_id from home_position_master where QUOTE_NO is not null and POLICY_NO is null and lower(status) in('y') and REMARKS is null and PRODUCT_ID=? and login_id in(select login_id from login_master where agency_code in(select agency_code from login_user_details where product_id=? and agency_code in (select agency_code from login_master where oa_code=(select oa_code from login_master where login_id=?))))) and login_id!='NONE' and application_id in ('2','3')", args);
				useHash.put(UBROKER,userBroker);
			}
			else{
				useHash.put(UBROKER, new String[0][0]);
			}
		logger.debug(EXIT);
		
		return (String[][])useHash.get(UBROKER);
	}

	public void getAdminReferralUpdation(final String quoteNo, final String sign,final String excesPre, final String loginId, final String pid, final String broker) throws BaseException 
	{
		logger.info("getAdminReferralUpdation method()");
		logger.debug(ENTER);
		updateExcessSignPremium(quoteNo,sign,excesPre);
		final double premium = getPremiumWithExcess(quoteNo,sign,excesPre);
		double totcom = 0.00;
		final double commis = Double.parseDouble(getCommision(loginId, pid));
		if (commis != 0){
			totcom = premium * (commis / 100);
		}
		final String args1[] = {Double.toString(premium),Double.toString(totcom),quoteNo};
		runner.multipleUpdation("update HOME_POSITION_MASTER set OVERALL_PREMIUM=?,COMMISSION=? where QUOTE_NO=?", args1);
		logger.debug(EXIT);
		
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
	public String checkDate(final String strDate)throws BaseException 
	{
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
	
	public String getDateSearchValue(final String searchValue,final String option)throws BaseException
	{
		logger.info("getDateSearchValue method()");
		logger.debug(ENTER);
		String result=""; 
		if(option.equalsIgnoreCase("DateWise")){
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
		logger.debug(EXIT);
		return result;
	}
	
	public String getUserTypeInformation(final String login) throws BaseException {
		logger.info("getUserTypeInformation method()");
		logger.debug(ENTER);
		final String[] args = {login};
		final String userType = runner.singleSelection("select nvl(USERTYPE,'Nil') from login_master where login_id=?", args);
		logger.debug(EXIT);
		
		return userType;
	}
	
	public void updateExcessSignPremium(final String quoteNo, final String sign,final String excesPre) throws BaseException
	{
		logger.info("updateExcessSignPremium method()");
		logger.debug(ENTER);
		final String excesSign = getSign(sign,excesPre);
		final String args[] = {excesPre,excesSign,quoteNo};
		runner.multipleUpdation("update HOME_POSITION_MASTER set EXCESS_PREMIUM=?,EXCESS_SIGN=? where QUOTE_NO=?", args);
		logger.debug(EXIT);
		
	}
	
	public double getPremiumWithExcess(final String quoteNo, final String sign, final String excesPre) throws BaseException
	{
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
	
	public String getCommision(final String loginId, final String pid)throws BaseException 
	{
		logger.info("getCommision method()");
		logger.debug(ENTER);
		final String args[] = {loginId,pid};
		final String commision = runner.singleSelection("select nvl(commission,'0') from login_user_details where agency_code=(select oa_code from login_master where login_id=? and status='Y') and status='Y' and product_id=?", args);
		logger.debug(EXIT);
		
		return commision;
	}
	
	public String getSign(final String sign,final String excesPre) throws BaseException
	{
		final StringBuffer excesSign = new StringBuffer();
		if (excesPre.length() > 0&& "Plus".equalsIgnoreCase(sign)){
			excesSign.append('+');
		}
		else if (excesPre.length() > 0 && "Minus".equalsIgnoreCase(sign)){
			excesSign.append('-');
		}
		return excesSign.toString();
	}
	
	public String hasContent(final String content) throws BaseException
	{
		String contents = content.trim();
		if (contents.length() <= 0){
			contents = "";
		}
		return contents;
	}
}