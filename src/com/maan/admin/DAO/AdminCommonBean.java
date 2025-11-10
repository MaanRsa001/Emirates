package com.maan.admin.DAO;


import com.maan.services.util.runner;

import org.apache.logging.log4j.Logger;

import com.maan.Home.DataManipualtion.HomeValidationBean;
import com.maan.common.LogUtil;
import com.maan.common.exception.BaseException;

public class AdminCommonBean
{
	private static final Logger logger = LogUtil.getLogger(AdminCommonBean.class);
	private transient String query = "";
	final private transient static String ENTER = "- Enter";
	final private transient static String EXIT ="- Exit";
	final private transient static String BROKER = "Broker";
	final private transient static String USER = "User";
	final private transient static String ALL = "all";
	final private transient static char SINGLECOMMA = ',';
	final private transient HomeValidationBean homeValid = new HomeValidationBean();
	
	
	public String getBrokerCodesQuery(String brokerCodes,final String loginBranch,final String broktype)
	{
		logger.info("getBrokerCodesQuery method()");
		logger.debug(ENTER);
		String syntax = "";
		if("".equalsIgnoreCase(brokerCodes)){
			syntax = "select agency_code from broker_company_master where branch_code in("+loginBranch+") and agency_code in(select oa_code from login_master where login_id='"+broktype+"')";
		}
		else{
			brokerCodes = brokerCodes.replaceAll(",","','");
			String syntax1 = "'"+brokerCodes+"'";
			syntax = "select agency_code from broker_company_master where branch_code in("+loginBranch+") and agency_code in(select oa_code from login_master where login_id='"+broktype+"') and agency_code in("+syntax1+")";
		}
		logger.debug(EXIT);
		
		return syntax;
	}
	
	public String getAdminBrokerCodesQuery(String brokerCodes,final String branchCode)
	{
		logger.info("getAdminBrokerCodesQuery method()");
		logger.debug(ENTER);
		String syntax = "";
		if("".equalsIgnoreCase(brokerCodes)){
			syntax = "select agency_code from broker_company_master where branch_code in("+branchCode+")";
		}
		else{
			brokerCodes = brokerCodes.replaceAll(",","','");
			syntax = "'"+brokerCodes+"'";
		}
		logger.debug(EXIT);
		
		return syntax;
	}
	
	public String[][] getBrokerUserPersonalInfo(final String userType,final String broktype) throws BaseException
	{
		logger.info("getBrokerUserPersonalInfo method(2)");
		logger.debug(ENTER);
		if(userType != null  && BROKER.equalsIgnoreCase(userType)){
        	query = "select application_id,login_id,agency_code,oa_Code,first_name from personal_info where oa_code=(select oa_code from login_master where login_id=?) and login_id!='NONE' and application_id in ('2','3')";
		}
		else if(userType!=null && USER.equalsIgnoreCase(userType)){
			query = "select application_id,login_id,agency_code,oa_code,first_name from personal_info where agency_code=(select agency_code from login_master where login_id=?) and login_id!='NONE' and application_id in ('2','3')";
		}
		final String args[] = {broktype};
		final String values[][] = runner.multipleSelection(query,args);
		logger.debug(EXIT);
		
		return values;
	}
	
	public String[][] getBrokerUserPersonalInfo(final String userType,final String broktype,final String syntax) throws BaseException
	{
		logger.info("getBrokerUserPersonalInfo method(3)");
		logger.debug(ENTER);
		if(userType != null && BROKER.equalsIgnoreCase(userType)){
		   query = "select application_id,login_id,agency_code,oa_Code,first_name from personal_info where oa_code in("+syntax+") and login_id!='NONE' and application_id in ('2','3')";
		 }
		else if(userType!=null && USER.equalsIgnoreCase(userType)){
			query = "select application_id,login_id,agency_code,oa_code,first_name from personal_info where agency_code=(select agency_code from login_master where login_id='"+broktype+"') and login_id!='NONE' and application_id in ('2','3')";
		}
		//final String args[] = {broktype};
		final String values[][] = runner.multipleSelection(query);
		logger.debug(EXIT);
		
		return values;
	}
	
	public String getAllHomeTravelPids(final String adminPids,String loginBranch) throws BaseException
	{
		logger.info("getAllHomeTravelPids method()");
		logger.debug(ENTER);
		String proids[][] = new String[0][0];
		if(ALL.equalsIgnoreCase(adminPids) || adminPids.length() <= 0){
			proids = runner.multipleSelection("select product_id from HOME_PRODUCT_MASTER where status='Y' order by product_id");
		}
		else
		{
			if(loginBranch.indexOf('\'')!=-1){
				loginBranch = loginBranch.replaceAll("'","");
			}
			query = "select product_id from HOME_PRODUCT_MASTER where status='Y' and (product_id in("+adminPids+") or product_id in (select PRODUCT_ID from HOME_PRODUCT_MASTER where BROKER_ID in(select REMARKS from PRODUCT_MASTER where PRODUCT_ID in(select PRODUCT_ID from HOME_PRODUCT_MASTER where BROKER_ID is not null and PRODUCT_ID in("+adminPids+")) and BRANCH_CODE=?))) order by product_id";
			final String args[] = {loginBranch};
			proids = runner.multipleSelection(query,args);
		}
		final String produc_ids = homeValid.removeLastChar(homeValid.getStringFromArray(proids),SINGLECOMMA);
		logger.debug(EXIT);
		
		return produc_ids;
	}

	public String getAllHomeTravelPids() throws BaseException
	{
		logger.info("getAllHomeTravelPids method()");
		logger.debug(ENTER);
		final String proids[][] = runner.multipleSelection("select product_id from HOME_PRODUCT_MASTER where status='Y' order by product_id");
		final String produc_ids = homeValid.removeLastChar(homeValid.getStringFromArray(proids), SINGLECOMMA);
		logger.debug(EXIT);
		
		return produc_ids;
	}
	
	public String getUserTypeInformation(final String login) throws BaseException
	{
    	logger.info("getUserTypeInformation method()");
		logger.debug(ENTER);
		final String args[] = {login};
		final String userType = runner.singleSelection("select USERTYPE from login_master where login_id=?",args);
		logger.debug(EXIT);
        
        return userType;
    }
	
	public String getAllTravelPids(final String pid,String loginBranch) throws BaseException
	{
		logger.info("getAllTravelPids method()");
		logger.debug(ENTER);
		if(loginBranch.indexOf('\'')!=-1){
			loginBranch = loginBranch.replaceAll("'","");
		}
		final String args[] = {pid,loginBranch};
		final String valuess[][] = runner.multipleSelection("select PRODUCT_ID from HOME_PRODUCT_MASTER where BROKER_ID=(select REMARKS from PRODUCT_MASTER where PRODUCT_ID=? and BRANCH_CODE=?)",args);
		final String result = homeValid.removeLastChar(homeValid.getStringFromArray(valuess), SINGLECOMMA);
		logger.debug(EXIT);
        
		return result;
	}
	
	public String getAgencyCode(final String logpersonId) throws BaseException
	{
    	logger.info("getAgencyCode method()");
		logger.debug(ENTER);
		final String args[] = {logpersonId};
        final  String agencyCode=runner.singleSelection("select agency_code from login_master where login_id=?",args);
		logger.debug(EXIT);
		
        return agencyCode;
    }
} // Class