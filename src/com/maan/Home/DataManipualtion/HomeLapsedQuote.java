package com.maan.Home.DataManipualtion;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Logger;

import com.maan.common.LogUtil;
import com.maan.common.exception.BaseException;
import com.maan.services.util.runner;

public class HomeLapsedQuote
{
	private static final Logger logger = LogUtil.getLogger(HomeLapsedQuote.class);
	final static transient private String ENTER = "- Enter";
	final static transient private String EXIT = "- Exit";
	final static transient private String UTYPE = "Broker";
	final static transient private String UBROKER = "UserBroker";
	final static transient private String LOGINSQL = "select login_id from login_master where agency_code=(select agency_code from login_master where login_id=?) and login_id not in('NONE','NON')";
	
	public String[][] getUserBrokerInformationforLapsed(final String login,final String pid) throws BaseException {
		logger.info("getUserBrokerInformationforLapsed method()");
		logger.debug(ENTER);
		final Map useHash = new HashMap();
			if (getUserTypeInformation(login).equalsIgnoreCase(UTYPE)) {
				final String[] args = {pid,pid,login};
				final String[][] userBroker = runner.multipleSelection("select login_id from personal_info where login_id in(select distinct login_id from home_position_master where QUOTE_NO is not null and lower(status) in('d') and PRODUCT_ID=? and login_id in(select login_id from login_master where agency_code in(select agency_code from login_user_details where product_id=? and agency_code in (select agency_code from login_master where oa_code=(select oa_code from login_master where login_id=?))))) and login_id!='NONE' and application_id in ('2','3')", args);
				useHash.put(UBROKER,userBroker);
			}
			else{
				useHash.put(UBROKER, new String[0][0]);
			}
		logger.debug(EXIT);
		
		return (String[][])useHash.get(UBROKER);
	}
	
	public String[][] getTrashQuote(final String loginIds, final String pid)throws BaseException {
		final HomeValidationBean homeValid = new HomeValidationBean();
		logger.info("getTrashQuote method()");
		logger.debug(ENTER);
			final String args[] = {loginIds};
			final String[][] valuess = runner.multipleSelection(LOGINSQL, args);
			final String loginAllIds = homeValid.removeLastChar(homeValid.getStringFromArray(valuess),',');
			final String args1[] ={pid};
			final String[][] trashQuotes = runner.multipleSelection("select a.quote_no,a.customer_id,to_char(a.LAPSED_DATE,'dd/mm/yyyy'),to_char(a.EXPIRY_DATE,'dd/mm/yyyy'),b.first_name,b.last_name,a.login_id,a.OVERALL_PREMIUM,a.status,a.application_no,b.COMPANY_NAME from home_position_master a,personal_info b where a.login_id in ("+loginAllIds+") and PRODUCT_ID=? and (a.status in ('D')) and b.customer_id=a.customer_id order by a.quote_no DESC", args1);
		logger.debug(EXIT);
		
		return trashQuotes;
	}
	
	public String getLapsedStatusRemarks(final String quoteNo, final String pid) throws BaseException{
		logger.info("getLapsedStatusRemarks method()");
		logger.debug(ENTER);
		final String args[] = {quoteNo,pid};
		final String lapsedRemarks = runner.singleSelection("select LAPSED_REMARKS from home_position_master where Quote_No=? and PRODUCT_ID=?", args);
		logger.debug(EXIT);
		
		return lapsedRemarks;
	}
	
	public String getUserTypeInformation(final String login) throws BaseException {
		logger.info("getUserTypeInformation method()");
		logger.debug(ENTER);
		final String[] args = {login};
		final String userType = runner.singleSelection("select nvl(USERTYPE,'Nil') from login_master where login_id=?", args);
		logger.debug(EXIT);
		
		return userType;
	}
	
	public String[][] getLapsedDetails(final String loginId, final String pid)throws BaseException {
		logger.info("getLapsedDetails method()");
		logger.debug(ENTER);
		final String args[] = {loginId,pid};
		final String[][] viewQuotes = runner.multipleSelection("select a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.entry_date+30,'dd')as days,to_char(a.entry_date+30,'MM')as months,to_char(a.entry_date+30,'YYYY')as years,nvl(b.company_name,b.first_name),b.last_name from home_position_master a, personal_info b WHERE a.quote_no=? and PRODUCT_ID=? and a.customer_id = b.customer_id", args);
		logger.debug(EXIT);
		
		return viewQuotes;
	}

	public String[][] getLapsedStatus(final String loginBranch)throws BaseException {
		logger.info("getLapsedStatus method()");
		logger.debug(ENTER);
		final String args[] = {loginBranch,loginBranch};
		final String[][] LapsedStatus = runner.multipleSelection("select category_detail_id,detail_name from constant_detail where category_id = (select category_id from constant_master where category_name = 'LAPSED_QUOTE_REASON' and BRANCH_CODE=?) and BRANCH_CODE=?", args);
		logger.debug(EXIT);
		
		return LapsedStatus;
	}
}