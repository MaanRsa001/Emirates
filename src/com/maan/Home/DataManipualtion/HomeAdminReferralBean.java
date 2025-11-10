package com.maan.Home.DataManipualtion;


import org.apache.logging.log4j.Logger;

import com.maan.common.LogUtil;
import com.maan.common.exception.BaseException;
import com.maan.services.util.runner;

public class HomeAdminReferralBean
{
	private static final Logger logger = LogUtil.getLogger(HomeAdminReferralBean.class);
	final static transient private String EDECLARATION = ",27,";
	final static transient private String ENTER = "- Enter";
	final static transient private String EXIT = "- Exit";
	final static transient private String PIDSQL = " and a.product_id in(";
	
	public String getProductName(final String productId, final String loginBranch)throws BaseException 
	{
		logger.info("getProductName method()");
		logger.debug(ENTER);
		final StringBuffer productName = new StringBuffer();
		if (!productId.equalsIgnoreCase("ALL")) {
			final String args[] = {loginBranch};
			final String pName = runner.singleSelection("select product_name from product_master where product_id in("+ productId + ") and branch_code=? and status='Y'", args);
			productName.append(pName);
			/*if (productId.equals("22") || productId.equals("31") || productId.equals("35")|| productId.equals("36") || productId.equals("37")|| productId.equals("50") || productId.equals("51")|| productId.equals("52")) {
				productName = "TRAVEL INSURANCE";
			}*/
		}
		logger.debug(EXIT);
		
		return productName.toString();
	}

	public String[][] getHomeReferal(final String pid, final String branchCode,final String brokerCodes)throws BaseException 
	{
		logger.info("getHomeReferal method()");
		logger.debug(ENTER);
			final String declarationRef = EDECLARATION;
			final String syntax = getBrokerCodeSyntax(brokerCodes, branchCode);
			final String offdata = getOfficeReferralCheckSql(pid,declarationRef);
			final String pids = getFinalProductId(pid,branchCode);
			final String sql = "select to_char(a.entry_date,'YYYY-MM-DD'),count(a.entry_date) from home_position_master a where a.REMARKS= 'Referal' and a.status='Y' "
					+ offdata
					+ PIDSQL
					+ pids
					+ ") and a.login_id in(select login_id from login_master where oa_code in("
					+ syntax
					+ ")) group by to_char(a.entry_date,'YYYY-MM-DD') order by to_char(a.entry_date,'YYYY-MM-DD') desc";
			final String[][] referralDetail = runner.multipleSelection(sql);
		logger.debug(EXIT);
		
		return referralDetail;
	}

	public String[][] getHomeReferalByDate(final String eDate, final String type,
			final String pid, final String branchCode, final String brokerCodes)throws BaseException {
		logger.info("getHomeReferalByDate method()");
		logger.debug(ENTER);
			final String declarationRef = EDECLARATION;
			final String syntax = getBrokerCodeSyntax(brokerCodes, branchCode);
			final String offdata = getOfficeReferralCheckSql(pid,declarationRef);
			final String pids = getFinalProductId(pid,branchCode);
			final String sql = "select distinct pm.application_no,pm.login_id,pm.quote_no,pm.REFERRAL_DESCRIPTION,nvl(pm.SUMMARY_REMARKS,''),pm.scheme_id  from home_position_master pm where to_char(pm.entry_date,'YYYY-MM-DD')=? and pm.product_id in("
					+ pids
					+ ") and pm.REMARKS=? "
					+ offdata
					+ " and pm.status='Y' and pm.login_id in(select login_id from login_master where oa_code in("
					+ syntax + ")) order by pm.quote_no desc";
			final String args[] = {eDate,type};
			final String[][] referralQuotes = runner.multipleSelection(sql, args);
		logger.debug(EXIT);
		
		return referralQuotes;
	}
	
	public String[][] getHomeApproved(final String pid, final String branchCode,
			final String brokerCodes) throws BaseException {
		logger.info("getHomeApproved method()");
		logger.debug(ENTER);
			final String declarationRef = EDECLARATION;
			final String syntax = getBrokerCodeSyntax(brokerCodes, branchCode);
			final String offdata = getOfficeReferralCheckSql(pid,declarationRef);
			final String pids = getFinalProductId(pid,branchCode);
			final String sql = "select to_char(a.EFFECTIVE_DATE,'YYYY-MM-DD'),count(a.EFFECTIVE_DATE) from home_position_master a where (a.REMARKS='Admin') and a.status='Y' "
					+ offdata
					+ PIDSQL
					+ pids
					+ ") and a.login_id in(select login_id from login_master where oa_code in("
					+ syntax
					+ ")) group by to_char(a.EFFECTIVE_DATE,'YYYY-MM-DD') order by to_char(a.EFFECTIVE_DATE,'YYYY-MM-DD') desc";
			final String[][] approvedDetails = runner.multipleSelection(sql);
		logger.debug(EXIT);
		
		return approvedDetails;
	}
	
	public String[][] getHomeApprovedByDate(final String eDate, final String type,
			final String pid, final String branchCode, final String brokerCodes)throws BaseException {
		logger.info("getHomeApprovedByDate method()");
		logger.debug(ENTER);
			final String declarationRef = EDECLARATION;
			final String syntax = getBrokerCodeSyntax(brokerCodes, branchCode);
			final String offdata = getOfficeReferralCheckSql(pid,declarationRef);
			final String pids = getFinalProductId(pid,branchCode);
			final String sql = "select distinct pm.application_no,pm.login_id,pm.quote_no,pm.REFERRAL_DESCRIPTION,nvl(pm.SUMMARY_REMARKS,''),pm.scheme_id  from home_position_master pm where to_char(pm.EFFECTIVE_DATE,'YYYY-MM-DD')=? and pm.product_id in("
					+ pids
					+ ") and (pm.REMARKS=?) "
					+ offdata
					+ " and pm.status='Y' and pm.login_id in(select login_id from login_master where oa_code in("
					+ syntax + ")) order by pm.quote_no desc";
			final String args[] = {eDate,type};
			final String[][] approvedDetails = runner.multipleSelection(sql, args);
		logger.debug(EXIT);
		
		return approvedDetails;
	}

	public String[][] getHomeRejectedReferal(final String pid, final String branchCode,
			final String brokerCodes)throws BaseException {
		logger.info("getHomeRejectedReferal method()");
		logger.debug(ENTER);
			final String syntax = getBrokerCodeSyntax(brokerCodes, branchCode);
			final String pids = getFinalProductId(pid,branchCode);
			final String sql = "select to_char(a.EFFECTIVE_DATE,'YYYY-MM-DD'),count(a.EFFECTIVE_DATE) from home_position_master a where ADMIN_REFERRAL_STATUS='N' and a.status='R' "+PIDSQL
					+ pids
					+ ")  and a.login_id in(select login_id from login_master where oa_code in("
					+ syntax
					+ ")) group by to_char(a.EFFECTIVE_DATE,'YYYY-MM-DD') order by to_char(a.EFFECTIVE_DATE,'YYYY-MM-DD') desc";

			final String[][] rejectedDetail = runner.multipleSelection(sql);
			logger.debug(EXIT);
			
		return rejectedDetail;
	}

	public String[][] getHomeRejectedReferalByDate(final String eDate, final String pid,
			final String branchCode, final String brokerCodes)throws BaseException {
		logger.info("getHomeRejectedReferalByDate method()");
		logger.debug(ENTER);
			final String declarationRef = EDECLARATION;
			final String syntax = getBrokerCodeSyntax(brokerCodes, branchCode);
			final String offdata = getOfficeReferralCheckSql(pid,declarationRef);
			final String pids = getFinalProductId(pid,branchCode);
			final String sql = "select distinct pm.application_no,pm.login_id,pm.quote_no,pm.REFERRAL_DESCRIPTION,nvl(pm.SUMMARY_REMARKS,''),pm.scheme_id  from home_position_master pm where to_char(pm.EFFECTIVE_DATE,'YYYY-MM-DD')=? and pm.product_id in("
					+ pids
					+ ") and pm.ADMIN_REFERRAL_STATUS='N' and pm.status='R' "
					+ offdata
					+ " and pm.login_id in(select login_id from login_master where oa_code in("
					+ syntax + ")) order by pm.quote_no desc";
			final String args[] = {eDate};
			final String[][] rejectedDetail = runner.multipleSelection(sql, args);
		logger.debug(EXIT);
		
		return rejectedDetail;
	}
	
	public String getBrokerCodeSyntax(final String brokerCodes,final String branchCode)throws BaseException{
		final StringBuffer syntax = new StringBuffer();
		if (brokerCodes.equalsIgnoreCase("") || brokerCodes.length() == 0) {
			syntax.append("select agency_code from broker_company_master where branch_code in("+ branchCode + ")");
		} else {
			final String broCodes = brokerCodes.replaceAll(",", "','");
			syntax.append("'" + broCodes + "'");
		}
		return syntax.toString();
	}
	
	public String getOfficeReferralCheckSql(final String productId,final String declarationRef)throws BaseException{
		final StringBuffer offdata = new StringBuffer(1000);
		if (productId.equalsIgnoreCase("30")){
			offdata.append(" and a.REFERRAL_DESCRIPTION!='" + declarationRef + "'");
		}
		return offdata.toString();
	}
	
	public String getFinalProductId(final String pid,final String branchCode)throws BaseException{
		final StringBuffer producIds = new StringBuffer();
		if (pid.equalsIgnoreCase("all")){
			producIds.append(getAllHomeTravelPids());
		}
		else {
			final String fullTravelIds = getAllTravelPids(pid, branchCode);
			if (fullTravelIds.length() > 0){
				producIds.append(fullTravelIds);
			}
			else{
				producIds.append(pid);
			}
		}
		return producIds.toString();
	}
	
	public String getAllHomeTravelPids()throws BaseException {
		final HomeValidationBean homeValid = new HomeValidationBean();
		logger.info("getAllHomeTravelPids method()");
		logger.debug(ENTER);
			final String proids[][] = runner.multipleSelection("select product_id from HOME_PRODUCT_MASTER where status='Y' order by product_id");
			final String producIds = homeValid.removeLastChar(homeValid.getStringFromArray(proids),',');
		logger.debug(EXIT);
		
		return producIds;
	}
	
	public String getAllTravelPids(final String pid, final String loginBranch)throws BaseException {
		final HomeValidationBean homeValid = new HomeValidationBean();
		logger.info("getAllTravelPids method()");
		logger.debug(ENTER);
			final StringBuffer branch = new StringBuffer();
			if (loginBranch.indexOf("'") != -1){
				branch.append(loginBranch.replaceAll("'", ""));
			}
			else{
				branch.append(loginBranch);
			}
			final String args[] = {pid,branch.toString()};
			final String valuess[][] = runner.multipleSelection("select PRODUCT_ID from HOME_PRODUCT_MASTER where BROKER_ID=(select REMARKS from PRODUCT_MASTER where PRODUCT_ID=? and BRANCH_CODE=?)", args);
			final String result = homeValid.removeLastChar(homeValid.getStringFromArray(valuess),',');
		logger.debug(EXIT);
		
		return result;
	}
}