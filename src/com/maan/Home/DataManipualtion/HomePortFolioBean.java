package com.maan.Home.DataManipualtion;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Logger;

import com.maan.common.LogUtil;
import com.maan.common.exception.BaseException;
import com.maan.services.util.runner;
public class HomePortFolioBean
{
	private static final Logger logger = LogUtil.getLogger(HomePortFolioBean.class);
	final static transient private String INVALID = "Invalid";
	final static transient private String ENTER = "- Enter";
	final static transient private String EXIT = "- Exit";
	final static transient private String LOGINSQL = "select login_id from login_master where agency_code=(select agency_code from login_master where login_id=?) and login_id not in('NONE','NON')";
	final static transient private String POLSQL="select a.policy_no,a.customer_id,a.OVERALL_PREMIUM,to_char(a.EFFECTIVE_DATE,'dd')as days,to_char(a.EFFECTIVE_DATE,'MM')as months,to_char(a.EFFECTIVE_DATE,'YYYY')as years,nvl(b.COMPANY_NAME,b.first_name),b.last_name,a.login_id,a.quote_no,b.COMPANY_NAME from home_position_master a,personal_info b where a.login_id in (";
	final static transient private String DECSQL=" and a.declaration_status is null order by a.inception_date desc";
	final static transient private String POLMULSQL="select a.policy_no,a.customer_id,sum(nvl(a.OVERALL_PREMIUM,'0')),nvl(b.COMPANY_NAME,b.first_name),b.last_name,a.login_id,count(a.policy_no),b.COMPANY_NAME from home_position_master a,personal_info b where a.login_id in (";
	final static transient private String DELLINKSQL = " and nvl(a.declaration_status,0) in ('LINKED') group by a.policy_no,a.customer_id,nvl(b.COMPANY_NAME,b.first_name),b.last_name,a.login_id,b.COMPANY_NAME  order by substr(a.policy_no,9,18) desc";
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

	// This For Search Option in Portfolio - For Office Shield // Declaration_status */
	public Map getTotalPolicy123(final String loginIds, final String searchOption,
			final String searchValue, final String productId, final String schemeId)throws BaseException {
		final HomeValidationBean homeValid = new HomeValidationBean();
		logger.info("getTotalPolicy123 method()");
		logger.debug(ENTER);
		Map getsTotal = new HashMap();
		String sql;
		String msql;
			final String argss[] = {loginIds};
			final String[][] valuess = runner.multipleSelection(LOGINSQL, argss);
			final String loginAllIds = homeValid.removeLastChar(homeValid.getStringFromArray(valuess),',');
			final String scheme = getSchemeCheckSql(schemeId);
			getsTotal.put("total", Integer.toString(valuess.length));
			getsTotal.put("getCus", valuess);
			final Map queryCollection = new HashMap();
							
				sql = POLSQL
						+ loginAllIds
						+ ") and lower(a.status)=lower('P') and b.customer_id=a.customer_id and a.product_id=? "
						+ scheme+(isFleetStatus()?(" and a.fleet_no="+this.getFleetNo()):"")
						+ DECSQL;
				final String args[] = {productId};
				queryCollection.put("SingleSqlSelect", sql);
				queryCollection.put("SingleArgsSelect", args);
				queryCollection.put("SingleSql", sql);
				queryCollection.put("SingleArgs", args);
				msql = POLMULSQL
						+ loginAllIds
						+ ") and lower(a.status)=lower('P') and b.customer_id=a.customer_id and a.product_id=? "
						+ scheme+(isFleetStatus()?(" and a.fleet_no="+this.getFleetNo()):"")
						+ DELLINKSQL;
				queryCollection.put("MultipleSqlSelect", msql);
				queryCollection.put("MultipleArgsSelect", args);
				queryCollection.put("MultipleSql", msql);
				queryCollection.put("MultipleArgs", args);
				sql = POLSQL
						+ loginAllIds
						+ ") and lower(a.status)=lower('P') and b.customer_id=a.customer_id and(lower(trim(b.company_name)) like ? or lower(trim(b.first_name)) like ?) and a.product_id=? "
						+ scheme+(isFleetStatus()?(" and a.fleet_no="+this.getFleetNo()):"")
						+ DECSQL;
				final String args2[] = {"%" + (searchValue.trim()).toLowerCase() + "%","%" + (searchValue.trim()).toLowerCase() + "%",productId};
				queryCollection.put("SingleSqlFIRST_NAME", sql);
				queryCollection.put("SingleArgsFIRST_NAME", args2);

				msql = POLMULSQL
						+ loginAllIds
						+ ") and lower(a.status)=lower('P') and b.customer_id=a.customer_id and(lower(trim(b.company_name)) like ? or lower(trim(b.first_name)) like ?) and a.product_id=? "
						+ scheme+(isFleetStatus()?(" and a.fleet_no="+this.getFleetNo()):"")
						+ DELLINKSQL;
				queryCollection.put("MultipleSqlFIRST_NAME", msql);
				queryCollection.put("MultipleArgsFIRST_NAME", args2);
				
				sql = POLSQL
						+ loginAllIds
						+ ") and lower(a.status)=lower('P') and b.customer_id=a.customer_id and (a.quote_no like ?) and a.product_id=? "
						+ scheme+(isFleetStatus()?(" and a.fleet_no="+this.getFleetNo()):"")
						+ DECSQL;
				final String args3[] = {"%" + (searchValue.trim()) + "%",productId};
				queryCollection.put("SingleSqlquote_nos", sql);
				queryCollection.put("SingleArgsquote_nos", args3);
				
				msql = POLMULSQL
						+ loginAllIds
						+ ") and lower(a.status)=lower('P') and b.customer_id=a.customer_id and (a.quote_no like ?) and a.product_id=? "
						+ scheme+(isFleetStatus()?(" and a.fleet_no="+this.getFleetNo()):"")
						+ DELLINKSQL;
				queryCollection.put("MultipleSqlquote_nos", msql);
				queryCollection.put("MultipleArgsquote_nos", args3);
				
				sql = POLSQL
						+ loginAllIds
						+ ") and lower(a.status)=lower('P') and b.customer_id=a.customer_id and (a.policy_no like ?) and a.product_id=? "
						+ scheme+(isFleetStatus()?(" and a.fleet_no="+this.getFleetNo()):"")
						+ DECSQL;
				final String args4[] = {"%" + (searchValue.trim()) + "%",productId};
				queryCollection.put("SingleSqlpolicy_nos", sql);
				queryCollection.put("SingleArgspolicy_nos", args4);
				
				msql = POLMULSQL
						+ loginAllIds
						+ ") and lower(a.status)=lower('P') and b.customer_id=a.customer_id and (a.policy_no like ?) and a.product_id=? "
						+ scheme+(isFleetStatus()?(" and a.fleet_no="+this.getFleetNo()):"")
						+ DELLINKSQL;
				queryCollection.put("MultipleSqlpolicy_nos", msql);
				queryCollection.put("MultipleArgspolicy_nos", args4);
				
				sql = "select a.policy_no,a.customer_id,a.OVERALL_PREMIUM,to_char(a.EFFECTIVE_DATE,'dd')as days,to_char(a.EFFECTIVE_DATE,'MM')as months,to_char(a.EFFECTIVE_DATE,'YYYY')as years,nvl(b.COMPANY_NAME,b.first_name),b.last_name,a.login_id,a.quote_no,b.COMPANY_NAME,a.inception_date from home_position_master a,personal_info b where a.login_id in ("
						+ loginAllIds
						+ ") and lower(a.status)=lower('P') and b.customer_id=a.customer_id and to_char(a.EFFECTIVE_DATE,'dd/MM/YYYY')=? and a.product_id=? "
						+ scheme+(isFleetStatus()?(" and a.fleet_no="+this.getFleetNo()):"")
						+ DECSQL;
				final String args5[] = {getDateSearchValue(searchValue,searchOption),productId};
				queryCollection.put("SingleSqlDateWise", sql);
				queryCollection.put("SingleArgsDateWise", args5);
				
				msql = POLMULSQL
						+ loginAllIds
						+ ") and lower(a.status)=lower('P') and b.customer_id=a.customer_id and to_char(a.EFFECTIVE_DATE,'dd/MM/YYYY')=? and a.product_id=? "
						+ scheme+(isFleetStatus()?(" and a.fleet_no="+this.getFleetNo()):"")
						+ DELLINKSQL;
				queryCollection.put("MultipleSqlDateWise", msql);
				queryCollection.put("MultipleArgsDateWise", args5);
				
		getsTotal = homeValid.getMapResult((HashMap)queryCollection,searchOption);
		logger.debug(EXIT);
		
		return (HashMap)getsTotal;
	}
	
	public String getSchemeCheckSql(final String schemeId)throws BaseException{
		String scheme;
		if (schemeId.equalsIgnoreCase("Home")){
			scheme = "and a.scheme_id is null";
		}
		else{
			scheme = "and a.scheme_id='" + schemeId + "'";
		}
		return scheme;
	}
	
	public String getDateSearchValue(final String searchValue,final String searchOption)throws BaseException{
		HomeQuoteRegisterBean quoteReg = new HomeQuoteRegisterBean();
		return quoteReg.getDateSearchValue(searchValue,searchOption);
	}
	
	public String[][] getUserBrokerInformationforPorfolio(final String login,final String pid) throws BaseException{
		logger.info("getUserBrokerInformationforPorfolio method()");
		logger.debug(ENTER);
		final Map useHash = new HashMap();
			if (getUserTypeInformation(login).equalsIgnoreCase(UTYPE)) {
				final String[] args = {pid,pid,login};
				final String[][] userBroker = runner.multipleSelection("select login_id from personal_info where login_id in(select distinct login_id from home_position_master where QUOTE_NO is not null and POLICY_NO is not null and lower(status) in('p') and PRODUCT_ID=? "+(isFleetStatus()?(" and fleet_no="+this.getFleetNo()):"") +" and login_id in(select login_id from login_master where agency_code in(select agency_code from login_user_details where product_id=? and agency_code in (select agency_code from login_master where oa_code=(select oa_code from login_master where login_id=?))))) and login_id!='NONE' and application_id in ('2','3')", args);
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