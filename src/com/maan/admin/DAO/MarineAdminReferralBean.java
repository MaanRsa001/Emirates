package com.maan.admin.DAO;
import java.util.HashMap;
import java.util.StringTokenizer;

import org.apache.logging.log4j.Logger;

import com.maan.common.LogUtil;
import com.maan.common.exception.BaseException;
import com.maan.services.util.runner;

public class MarineAdminReferralBean{
	private static final Logger logger = LogUtil.getLogger(MarineAdminReferralBean.class);
	final static transient private String ENTER = "- Enter";
	final static transient private String EXIT = "- Exit";
	final static transient private String YES = "Yes";

	public String[][] getAReferal(final String appNo) throws BaseException
	{
			logger.info("getAReferal method()");
			logger.debug(ENTER);

			String args[] = {appNo};
			String sql = "select a.ADMIN_REFERRAL_STATUS,b.remarks,a.remarks,a.REFERAL_STATUS from marine_data a,position_master b where a.application_no=b.application_no and b.application_no=?";
			String[][] valuess = runner.multipleSelection(sql, args);

			logger.debug(EXIT);
			

			return valuess;
	}

	public String getAdminBranch(final String loginId) throws BaseException
	{
		logger.info("getAdminBranch method()");
		logger.debug(ENTER);

		String args[] = {loginId};
		String adminBranch = runner.singleSelection("select BRANCH_CODE from LOGIN_MASTER where login_id=?", args);
		adminBranch = adminBranch == null ? "" : adminBranch;

		logger.debug(EXIT);
		

		return adminBranch;
	}
	public String[][] getApproved(final String pid,final String branchCode,final String brokerCodes,final String freightStatus) throws BaseException
	{
		logger.info("getApproved method()");
		logger.debug(ENTER);

		String syntax = "";
		if ("".equalsIgnoreCase(brokerCodes) ) {
			syntax = "select agency_code from broker_company_master where branch_code in("+ branchCode + ")";
		}
		else {
			//brokerCodes = brokerCodes.replaceAll(",", "','");
			syntax = "'" + brokerCodes.replaceAll(",", "','") + "'";
		}
		String freightCheck = "";
		if (YES.equalsIgnoreCase(freightStatus)) {
			freightCheck = "and a.FREIGHT_STATUS='F'";
		}
		else {
			freightCheck = "and a.FREIGHT_STATUS is null";
		}
		String sql = "select to_char(a.entry_date,'YYYY-MM-DD'),count(a.entry_date),sum(nvl(b.premium,'0')+nvl(b.excess_premium,'0')) from position_master a,marine_data b where a.REMARKS in ('Admin','NORMAL_EXCESS_PRICE') and a.application_no=b.application_no and b.ADMIN_REFERRAL_STATUS='N' and a.product_id in ("
					+ pid+ ") and a.status  in('Y') and a.login_id in(select login_id from login_master where oa_code in("
					+ syntax+ ")) "+ freightCheck+ " group by to_char(a.entry_date,'YYYY-MM-DD') order by to_char(a.entry_date,'YYYY-MM-DD') desc";
		final String[][] ssVal = runner.multipleSelection(sql);

		logger.debug(EXIT);
		

		return ssVal;
	}
	public String[][] getAdminReferal() throws BaseException
	{
		logger.info("getAdminReferal method()");
		logger.debug(ENTER);

		final String sql = "select to_char(a.entry_date,'YYYY-MM-DD'),count(a.entry_date) from position_master a,marine_data b,personal_info pi where a.REMARKS in ('Normal','NORMAL_EXCESS_PRICE','Admin') and a.status='Y' and  a.application_no=b.application_no and pi.customer_id=a.customer_id and b.ADMIN_REFERRAL_STATUS='Y'  group by to_char(a.entry_date,'YYYY-MM-DD') order by to_char(a.entry_date,'YYYY-MM-DD') desc";
		final String[][] ssVal = runner.multipleSelection(sql);

		logger.debug(EXIT);
		

		return ssVal;
	}
	public String[][] getRejectedReferal(final String pid,final String branchCode,final String brokerCodes,final  String freightStatus) throws BaseException
	{
		logger.info("getRejectedReferal method()");
		logger.debug(ENTER);

		String freightCheck = "";
		String syntax = "";
		if ("".equalsIgnoreCase(brokerCodes) ) {
			syntax = "select agency_code from broker_company_master where branch_code in("+ branchCode + ")";
		}
		else {
			syntax = "'" + brokerCodes.replaceAll(",", "','") + "'";
		}

		if (YES.equalsIgnoreCase(freightStatus)) {
			freightCheck = "and FREIGHT_STATUS='F'";
		}
		else {
			freightCheck = "and FREIGHT_STATUS is null";
		}
		String sql = "select to_char(entry_date,'YYYY-MM-DD'),count(entry_date) from position_master where status = 'R' and product_id ='"
					+ pid+ "' and login_id in(select login_id from login_master where oa_code in("
					+ syntax+ ")) "+ freightCheck+ " group by to_char(entry_date,'YYYY-MM-DD') order by to_char(entry_date,'YYYY-MM-DD') desc";

		String[][] ssVal = runner.multipleSelection(sql);

		logger.debug(EXIT);
		

		return ssVal;
	}
	public String[][] getReferalByDate(final String eDate, final String type, final String pid,final String branchCode, final String brokerCodes,final String freightStatus) throws BaseException
	{
		logger.info("getReferalByDate method()");
		logger.debug(ENTER);

		String syntax = "";
		String freightCheck = "";
		if ("".equalsIgnoreCase(brokerCodes) ) {
			syntax = "select agency_code from broker_company_master where branch_code in("+ branchCode + ")";
		}
		else {
			syntax = "'" + brokerCodes.replaceAll(",", "','") + "'";
		}

		if (YES.equalsIgnoreCase(freightStatus)) {
			freightCheck = "and pm.FREIGHT_STATUS='F'";
		}
		else {
			freightCheck = "and pm.FREIGHT_STATUS is null";
		}
        final String sql = "select pm.application_no,pm.login_id,pm.quote_no,md.remarks,nvl(pm.application_id,'1'),pm.OPEN_COVER_NO from position_master pm,marine_data md where to_char(pm.entry_date,'YYYY-MM-DD')='"
					+ eDate+ "' and pm.application_no=md.application_no  and pm.product_id='"
					+ pid+ "'  and ((pm.REMARKS in ('Referal') and md.ADMIN_REFERRAL_STATUS='N') or (pm.REMARKS in ('Admin','Normal','NORMAL_EXCESS_PRICE') and  md.ADMIN_REFERRAL_STATUS='Y'))  and pm.status='Y'  and pm.login_id in(select login_id from login_master where oa_code in("
					+ syntax+ ")) "+ freightCheck+ " order by pm.quote_no desc";
        final String[][] ssVal = runner.multipleSelection(sql);

        logger.debug(EXIT);
		

		return ssVal;
	}
	public String[][] getQuoteSearch(final String qno,final String type,final String branchCode,final String brokerCodes) throws BaseException
	{
		 // ENTRY DATE
		logger.info("getQuoteSearch method(string,string,string,string)");
		logger.debug(ENTER);

		String syntax = "";
		String sql = "";
		if ("".equalsIgnoreCase(brokerCodes)) {
			syntax = "select agency_code from broker_company_master where branch_code in("+ branchCode + ")";
		}
		else {
			syntax = "'" + brokerCodes.replaceAll(",", "','") + "'";
		}
		if ("Referal".equalsIgnoreCase(type)) {
				sql = "select pm.application_no,pm.login_id,pm.quote_no,md.remarks,pm.product_id,to_char(pm.entry_date,'DD-MM-YYYY'),nvl(pm.application_id,'1'),OPEN_COVER_NO  from position_master pm,marine_data md,personal_info pi where pm.quote_no like '"
					+ qno+ "%'  and pm.application_no=md.application_no and pi.customer_id=pm.customer_id and ((pm.REMARKS in ('Referal') and md.ADMIN_REFERRAL_STATUS='N') or (pm.REMARKS in ('Admin','Normal','NORMAL_EXCESS_PRICE') and md.ADMIN_REFERRAL_STATUS='Y')) and pm.status='Y'  and pm.login_id in(select login_id from login_master where oa_code in("
					+ syntax + "))order by pm.quote_no desc";
		}
		else {
				sql = "select pm.application_no,pm.login_id,pm.quote_no,md.remarks,pi.first_name,pi.last_name,pm.product_id ,to_char(pm.entry_date,'DD-MM-YYYY'),nvl(pm.application_id,'1'),OPEN_COVER_NO from position_master pm,marine_data md,personal_info pi where pm.quote_no like '"
					+ qno+ "%' and pm.application_no=md.application_no and pi.customer_id=pm.customer_id and pm.REMARKS in ('Admin','NORMAL_EXCESS_PRICE') and md.ADMIN_REFERRAL_STATUS='N'  and lower(pm.status)  in('y')  and pm.login_id in(select login_id from login_master where oa_code in("
					+ syntax + "))order by quote_no desc";
		}
		final String[][] ssVal = runner.multipleSelection(sql);

		logger.debug(EXIT);
		

		return ssVal;
	}
	public HashMap getTotalQuotedPersons(final String logins,final String branchCode) throws BaseException
	{
		logger.info("getTotalQuotedPersons method()");
		logger.debug(ENTER);

		HashMap commHash = new HashMap();
		String loginids = logins;
		loginids = loginids.replaceAll("'", "");
		String sql = "select p.application_id,nvl(p.first_name||' '||p.last_name||' '||p.company_name,''),nvl(p.customer_login_id,l.login_id) from personal_info p,login_master l where (p.login_id in("
					+ logins+ ") or p.customer_login_id in("+ logins+ ")) and p.application_id in ('3','2','5','1') and p.login_id in(select login_id from login_master where oa_code in(select agency_code from broker_company_master where branch_code in("
					+ branchCode + "))) and p.login_id=l.login_id";

		String[][] ssVal = runner.multipleSelection(sql);
		for (int j = 0; j < ssVal.length; j++) {
				int comFlag = 0;
				String result[][] = new String[1][3];
				StringTokenizer stVal = new StringTokenizer(loginids, ",");
				while (stVal.hasMoreTokens()) {
					if (ssVal[j][2].equalsIgnoreCase(stVal.nextToken())) {
						result[0][0] = ssVal[j][0];
						result[0][1] = ssVal[j][1];
						result[0][2] = ssVal[j][2];
						comFlag = 1;
					}
				}
				if (!commHash.containsKey("user" + ssVal[j][2]) && comFlag == 1) {
					commHash.put("user" + ssVal[j][2], result);
				}
		}

		ssVal = new String[0][0];
		sql = "select a.first_name,b.company_name,c.usertype,a.application_id,c.login_id from personal_info a,broker_company_master b,login_master c where (a.login_id in(select login_id from login_master where oa_code in(select oa_code from login_master where login_id in("
			+ logins+ ")) and usertype='Broker') or a.customer_login_id in(select login_id from login_master where oa_code in(select oa_code from login_master where login_id in("+ logins
			+ ")) and usertype='Broker')) and (a.application_id='2' or (a.application_id='1' ))  and b.agency_code=c.agency_code and a.customer_id=b.customer_id and a.login_id in(select login_id from login_master where oa_code in(select agency_code from broker_company_master where branch_code in("
			+ branchCode + ")))";
		ssVal = runner.multipleSelection(sql);
		for (int j = 0; j < ssVal.length; j++) {
				int comFlag = 0;
				String result[][] = new String[1][5];
				StringTokenizer stVal = new StringTokenizer(loginids, ",");
				while (stVal.hasMoreTokens()) {
					if (ssVal[j][4].equalsIgnoreCase(stVal.nextToken())) {
						result[0][0] = ssVal[j][0];
						result[0][1] = ssVal[j][1];
						result[0][2] = ssVal[j][2];
						result[0][3] = ssVal[j][3];
						result[0][4] = ssVal[j][4];
						comFlag = 1;
					}
				}
				if (!commHash.containsKey("brokers" + ssVal[j][4]) && comFlag == 1) {
					commHash.put("brokers" + ssVal[j][4], result);
				}
		}

		logger.debug(EXIT);
		

		return commHash;
	}
	public String[][] getQuotedPerson(final String cid,final String branchCode) throws BaseException
	{
		logger.info("getQuotedPerson method()");
		logger.debug(ENTER);

		String sql = "";
		String args[] = { cid};
		String qry = "select usertype from login_master where login_id=?";
		String loginType = runner.singleSelection(qry, args);

		if (loginType.length() > 0) {
			if (loginType.equalsIgnoreCase("Customer")) {
				sql = "select application_id,nvl(first_name||' '||last_name||' '||company_name,'') from personal_info where application_id in ('1') and customer_id in(select distinct customer_id from login_user_Details where login_id = '"+ cid + "')";
			}
			else {
				sql = "select application_id,nvl(first_name||' '||last_name||' '||company_name,'') from personal_info where login_id ='"+ cid + "' and application_id in ('3','2','5','8')";
			}
		}
		String[][] brokerName = runner.multipleSelection(sql);

		logger.debug(EXIT);
		

		return brokerName;
	}
	public String[][] getQuotedPerson(final String cid) throws BaseException
	{
		logger.info("getQuotedPerson method()");
		logger.debug(ENTER);

		String args[] = { cid };
		String sql    = "select application_id,nvl(first_name||' '||last_name||' '||company_name,'') from personal_info where login_id=? and application_id in ('3','2','5')";
		String[][] brokerName = runner.multipleSelection(sql, args);

		logger.debug(EXIT);
		

		return brokerName;
	}
	public String[][] getBrokerNameByUserId123(final String cid,final String branchCode) throws BaseException
	{
		logger.info("getBrokerNameByUserId123 method()");
		logger.debug(ENTER);

		String sql = "select a.first_name,b.company_name,c.usertype,a.application_id from personal_info a,broker_company_master b,login_master c where a.login_id=(select login_id from login_master where oa_code=(select oa_code from login_master where login_id='"
					+ cid+ "') and usertype='Broker') and (a.application_id='2' or (a.application_id='1' ))  and b.agency_code=c.agency_code and a.customer_id=b.customer_id and a.login_id in(select login_id from login_master where oa_code in(select agency_code from broker_company_master where branch_code in("
					+ branchCode + ")))";
		String[][] brokerName = runner.multipleSelection(sql);

		logger.debug(EXIT);
		

		return brokerName;
	}
	public String[][] getApprovedByDate(final String eDate,final String type,final String pid,final String branchCode, final String brokerCodes,final  String freightStatus) throws BaseException
	{
		logger.info("getApprovedByDate method()");
		logger.debug(ENTER);

		String syntax = "";
		if ("".equalsIgnoreCase(brokerCodes)) {
			syntax = "select agency_code from broker_company_master where branch_code in("+ branchCode + ")";
		}
		else{
			syntax = "'" +  brokerCodes.replaceAll(",", "','") + "'";
		}
		String freightCheck = "";
		if (YES.equalsIgnoreCase(freightStatus)) {
			freightCheck = "and pm.FREIGHT_STATUS='F'";
		}
		else {
			freightCheck = "and pm.FREIGHT_STATUS is null";
		}
		final String sql = "select pm.application_no,pm.login_id,pm.quote_no,md.remarks,pm.application_id from position_master pm,marine_data md where to_char(pm.entry_date,'YYYY-MM-DD')='"
					+ eDate+ "' and pm.application_no=md.application_no and pm.REMARKS in ('"
					+ type+ "','NORMAL_EXCESS_PRICE') and md.ADMIN_REFERRAL_STATUS='N' and pm.product_id='"
					+ pid+ "' and pm.status in('Y') and pm.login_id in(select login_id from login_master where oa_code in("
					+ syntax + ")) " + freightCheck + " order by quote_no desc";
		final String[][] ssVal = runner.multipleSelection(sql);

		logger.debug(EXIT);
		

		return ssVal;
	}

	public String[][] getQuoteSearch(final String qno,final  String type,final String branchCode) throws BaseException
	{
		logger.info("getQuoteSearch method(string,string,string)");
		logger.debug(ENTER);

		String sql = "";
		if ("Referal".equalsIgnoreCase(type)) {

			sql = "select pm.application_no,pm.login_id,pm.quote_no,md.remarks,pm.product_id,to_char(pm.entry_date,'DD-MM-YYYY'),pm.OPEN_COVER_NO  from position_master pm,marine_data md,personal_info pi where pm.quote_no like '"
				+ qno+ "%'  and pm.application_no=md.application_no and pi.customer_id=pm.customer_id and ((pm.REMARKS in ('Referal') and md.ADMIN_REFERRAL_STATUS='N') or (pm.REMARKS in ('Admin','Normal','NORMAL_EXCESS_PRICE') and md.ADMIN_REFERRAL_STATUS='Y')) and pm.status='Y'  and pm.login_id in(select login_id from login_master where oa_code in(select agency_code from broker_company_master where branch_code in("
				+ branchCode + ")))order by pm.quote_no desc";
		}
		else {
			sql = "select pm.application_no,pm.login_id,pm.quote_no,md.remarks,pi.first_name,pi.last_name,pm.product_id ,to_char(pm.entry_date,'DD-MM-YYYY'),pm.OPEN_COVER_NO from position_master pm,marine_data md,personal_info pi where pm.quote_no like '"
				+ qno+ "%' and pm.application_no=md.application_no and pi.customer_id=pm.customer_id and pm.REMARKS in ('Admin','NORMAL_EXCESS_PRICE') and md.ADMIN_REFERRAL_STATUS='N'  and lower(pm.status)  in('y')  and pm.login_id in(select login_id from login_master where oa_code in(select agency_code from broker_company_master where branch_code in("
				+ branchCode + ")))order by quote_no desc";
		}
		final String[][] ssVal = runner.multipleSelection(sql);

		logger.debug(EXIT);
		

		return ssVal;
	}
	public String[][] getRejectedReferalByDate(final String eDate,final String type,final String pid,final String branchCode,final String brokerCodes,final	String freightStatus) throws BaseException
	{
		logger.info("getRejectedReferalByDate method()");
		logger.debug(ENTER);

		String freightCheck = "";
		String syntax = "";

		if ("".equalsIgnoreCase(brokerCodes)) {
			syntax = "select agency_code from broker_company_master where branch_code in("+ branchCode + ")";
		} else {
			syntax = "'" + brokerCodes.replaceAll(",", "','") + "'";
		}

		if (YES.equalsIgnoreCase(freightStatus)) {
			freightCheck = "and pm.FREIGHT_STATUS='F'";
		}
		else {
			freightCheck = "and pm.FREIGHT_STATUS is null";
		}

		String sql = "select pm.application_no,pm.login_id,pm.quote_no,md.remarks,pm.application_id from position_master pm,marine_data md where to_char(pm.entry_date,'YYYY-MM-DD')='"
					+ eDate+ "' and pm.application_no=md.application_no  and pm.product_id='"
					+ pid+ "' and pm.status='R'  and pm.login_id in(select login_id from login_master where oa_code in("
					+ syntax+ ")) "+ freightCheck+ " order by pm.quote_no desc";
		String[][] ssVal = runner.multipleSelection(sql);

		logger.debug(EXIT);
		

		return ssVal;
	}
	public String[][] getBrokerNameByUserId123(final String cid) throws BaseException
	{
		logger.info("getBrokerNameByUserId123 method()");
		logger.debug(ENTER);

		String args[] = { cid };
		String sql =  "select a.first_name,b.company_name,c.usertype,a.application_id from personal_info a,broker_company_master b,login_master c where a.login_id=(select login_id from login_master where oa_code=(select oa_code from login_master where login_id=?) and usertype='Broker') and (a.application_id='2' or (a.application_id='1' ))  and b.agency_code=c.agency_code and a.customer_id=b.customer_id";
		String[][] brokerName = runner.multipleSelection(sql, args);

		logger.debug(EXIT);
		

		return brokerName;
	}
	public String getBrokerCodes(final String loginId) throws BaseException
	{
		logger.info("getBrokerCodes method()");
		logger.debug(ENTER);

		String args[] = {loginId};
		String sql = "select broker_codes from login_master where login_id =?";
		String adminBrokerCodes = runner.singleSelection(sql, args);

		logger.debug(EXIT);
		

		return adminBrokerCodes;
	}
	public String getLoginProIds(final String loginId) throws BaseException
	{
		logger.info("getLoginProIds method()");
		logger.debug(ENTER);

		String args[] = {loginId};
		String adminProIds = runner.singleSelection("select product_id from login_master where login_id =?", args);

		logger.debug(EXIT);
		

		return adminProIds;
	}
	public String[][] getReferal(final String pid,final String branchCode,final String brokerCodes,final String freightStatus) throws BaseException
	{
		//broker_codes	// restriction
		logger.info("getReferal method()");
		logger.debug(ENTER);

		String syntax = "";
		if ("".equalsIgnoreCase(brokerCodes)) {
			syntax = "select agency_code from broker_company_master where branch_code in("+ branchCode + ")";
		}
		else {
			//brokerCodes = brokerCodes.replaceAll(",", "','");
			syntax = "'" + brokerCodes.replaceAll(",", "','") + "'";
		}
		String freightCheck = "";
		if (YES.equalsIgnoreCase(freightStatus)) {
			freightCheck = "and a.FREIGHT_STATUS='F'";
		}
		else {
			freightCheck = "and a.FREIGHT_STATUS is null";
		}
		final String sql = "select to_char(a.entry_date,'YYYY-MM-DD'),count(a.entry_date) from position_master a,marine_data b where ((a.REMARKS in ('Referal') and b.ADMIN_REFERRAL_STATUS='N') or (a.REMARKS in ('Admin','Normal','NORMAL_EXCESS_PRICE') and b.ADMIN_REFERRAL_STATUS='Y')) and a.status='Y' and a.product_id in("
				+ pid+ ") and  a.application_no=b.application_no and a.login_id in(select login_id from login_master where oa_code in("
				+ syntax+ ")) "+ freightCheck+ " group by to_char(a.entry_date,'YYYY-MM-DD') order by to_char(a.entry_date,'YYYY-MM-DD') desc";
		final String[][] ssVal = runner.multipleSelection(sql);

		logger.debug(EXIT);
		

		return ssVal;
	}
}