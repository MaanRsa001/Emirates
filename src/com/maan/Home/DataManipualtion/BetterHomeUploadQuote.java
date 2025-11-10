package com.maan.Home.DataManipualtion;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Logger;

import com.maan.common.LogUtil;
import com.maan.common.exception.BaseException;
import com.maan.services.util.runner;

public class BetterHomeUploadQuote
{
	private static final Logger logger = LogUtil.getLogger(BetterHomeUploadQuote.class);
	final static transient private String QUERY = "Query";
	final static transient private String ARGS = "Args";
	final static transient private String ENTER = "- Enter";
	final static transient private String EXIT = "- Exit";
	final static transient private String SINSQL = "SingleSql";
	final static transient private String SINARG = "SingleArgs";
	
	// better home file uplaod process Start
	public String[][] getViewQuotesNotApprovedBhomes(final String loginIds,
			final String searchOption, final String searchValue)throws BaseException {
		logger.info("from getViewQuotesNotApprovedBhomes method()");
		logger.debug(ENTER);
		String sql;
		final Map queryCollection = new HashMap();
				sql = "select QUOTE_NO,CLIENT_NAME,regexp_replace(req_entered_by, '( *[[:punct:]])', ''),to_char(FILE_UPLOAD_DATE,'dd-mm-yyyy'),REQ_ID from BETTER_HOME_MASTER where PRINT_STATUS is null order by quote_no desc";
				queryCollection.put("SingleSqlselect", sql);
				queryCollection.put("SingleArgsselect", new String[0]);
				queryCollection.put("SingleSql", sql);
				queryCollection.put("SingleArgs", new String[0]);
				sql = "select QUOTE_NO,CLIENT_NAME,regexp_replace(req_entered_by, '( *[[:punct:]])', ''),to_char(FILE_UPLOAD_DATE,'dd-mm-yyyy'),REQ_ID from BETTER_HOME_MASTER where lower(CLIENT_NAME) like ? and PRINT_STATUS is null order by quote_no desc";
				final String args[] = {"%" + searchValue.trim().toLowerCase() + "%"};
				queryCollection.put("SingleSqlFIRST_NAME", sql);
				queryCollection.put("SingleArgsFIRST_NAME", args);
				sql = "select QUOTE_NO,CLIENT_NAME,regexp_replace(req_entered_by, '( *[[:punct:]])', ''),to_char(FILE_UPLOAD_DATE,'dd-mm-yyyy'),REQ_ID from BETTER_HOME_MASTER where QUOTE_NO like ? and PRINT_STATUS is null order by quote_no desc";
				final String args1[] = {"%" + searchValue + "%"};
				queryCollection.put("SingleSqlquote_nos", sql);
				queryCollection.put("SingleArgsquote_nos", args1);
				sql = "select QUOTE_NO,CLIENT_NAME,regexp_replace(req_entered_by, '( *[[:punct:]])', ''),to_char(FILE_UPLOAD_DATE,'dd-mm-yyyy'),REQ_ID from BETTER_HOME_MASTER where to_char(FILE_UPLOAD_DATE,'dd/MM/YYYY')=? and PRINT_STATUS is null order by quote_no desc";
				HomeQuoteRegisterBean quoReg;
				quoReg = new HomeQuoteRegisterBean();
				final String args2[] = {quoReg.getDateSearchValue(searchValue,searchOption)};
				queryCollection.put("SingleSqlDateWise", sql);
				queryCollection.put("SingleArgsDateWise", args2);
		sql = (String)queryCollection.get(SINSQL+searchOption);
		final String[][] single = runner.multipleSelection(sql, (String[])queryCollection.get(SINARG+searchOption));
		queryCollection.put("SingleRes", single);
		if(single.length<=0){
			sql = sql.replaceAll("regexp_replace", "replace");
			final String[][] singles = runner.multipleSelection(sql, (String[])queryCollection.get(SINARG+searchOption));
			queryCollection.put("SingleRes", singles);
		}
		return (String[][])queryCollection.get("SingleRes");
	}
	
	public String[][] getQuoteUploadedDetails(final String qno, final String login)throws BaseException {
		logger.info("getQuoteUploadedDetails method()");
		logger.debug(ENTER);
			String[][] valuess = new String[0][0];
			String sql = "select QUOTE_NO,to_char(FILE_UPLOAD_DATE,'dd-mm-yyyy'),nvl(regexp_replace(CLIENT_NAME, '( *[[:punct:]])', ''),' '),nvl(PH_NO,' '), nvl(EMAIL,' '),nvl(regexp_replace(REQ_TYPE, '( *[[:punct:]])', ''),' '), nvl(regexp_replace(CLIENT_TYPE, '( *[[:punct:]])', ''),' '), nvl(regexp_replace(UNIT_TYPE, '( *[[:punct:]])', ''),' '), nvl(regexp_replace(METHOD_OF_CONTACT, '( *[[:punct:]])', ''),' '), nvl(regexp_replace(MEDIA_TYPE, '( *[[:punct:]])', ''),' '), nvl(regexp_replace(MEDIA_NAME, '( *[[:punct:]])', ''),' '), nvl(regexp_replace(REQ_ENTERED_BY, '( *[[:punct:]])', ''),' '), nvl(regexp_replace(REQ_ENTERED_DATE, '( *[[:punct:]])', ''),' '),nvl(PREMIUM_BASED_ON_SPEC,'0'),nvl(SUM_INSURED,'0') from BETTER_HOME_MASTER where quote_no=? and PRINT_STATUS is null order by quote_no desc";
			final String args[] = {qno};
			valuess = runner.multipleSelection(sql, args);
			sql = sql.replaceAll("regexp_replace", "replace");
			if (valuess.length <= 0){
				valuess = runner.multipleSelection(sql, args);
			}
		logger.debug(EXIT);
		
		return valuess;
	}

	public String convertToQuote(final String qno, final String login, final String loginBra) throws BaseException {
		
		final Map queryCollection = new HashMap();
		logger.info("convertToQuote method()");
		logger.debug(ENTER);
		final String args[] = {qno};
		final String[][] valuess = runner.multipleSelection("select nvl(CLIENT_NAME,' '),PH_NO,nvl(EMAIL,' '),nvl(PREMIUM_BASED_ON_SPEC,'0'),nvl(SUM_INSURED,'0') from BETTER_HOME_MASTER where quote_no=? order by quote_no desc", args);
		// Personal_info insertion
		final String firstName = valuess[0][0];
		final String telephone = valuess[0][1];
		final String email = valuess[0][2];
		final String SumInsured = valuess[0][4];
		final String premium = valuess[0][3];
		final String customerId = DataManipualtion.getMaxCustomerId(loginBra, "21");
		final String args1[] = {customerId,firstName,telephone,email,login};
		queryCollection.put(QUERY+0, "insert into personal_info(customer_id,application_id,first_name,amend_id,telephone,email,status,entry_date,login_id) values(?,'1',?,'1',?,?,'Y',(select sysdate from dual),?)");
		queryCollection.put(ARGS+0, args1);
		DataSelect HomeData = new DataSelect();
		final String application_no = HomeData.getMaxApplicationNo();
		// company_id - 20 all other home except better file uplaod. better file uplaod 22 company id
		
		final String hour = HomeData.getSysdateTime(loginBra,"Branch");
		
		final String newQuote = HomeData.getMaxQuote("21", loginBra);
		final String argsPos[] = {newQuote,customerId,login,application_no,premium,premium,premium};
		queryCollection.put(QUERY+1, "insert into home_position_master(quote_no,customer_id,company_id,login_id,amend_id,product_id,INCEPTION_DATE,EFFECTIVE_DATE,ENTRY_DATE,EXPIRY_DATE,status,APPLICATION_NO,BED_ROOM,PREMIUM,OVERALL_PREMIUM) values(?,?,'22',?,'1',(select REMARKS from BHOMEPRODUCT_MASTER where PRODUCT_ID='101'),(select "+hour+" from dual),(select "+hour+" from dual),(select "+hour+" from dual),(select "+hour+"+30 from dual),'Y',?,(select distinct BED_RM_NO from BHOMEPREMIUM_MASTER where premium=?),?,?)");
		queryCollection.put(ARGS+1, argsPos);
		
		final String argsTra[] = {newQuote,SumInsured,premium};
		queryCollection.put(QUERY+2, "insert into HOME_COVERAGE_TRANSACTION(QUOTE_NO,HOME_SNO,COVER_ID,PRODUCT_DESCRIPTION,PRODUCT_SUMINSURED,STATUS,AMEND_ID,PREMIUM) values (?,'1002','2','CONTENTS',?,'Y','0',?)");
		queryCollection.put(ARGS+2, argsTra);
		final String argsb[] = {qno};
		queryCollection.put(QUERY+3, "update BETTER_HOME_MASTER set PRINT_STATUS='N' where quote_no=?");
		queryCollection.put(ARGS+3, argsb);
		queryCollection.put("Count","4");
		runner.multipleUpdateTransaction((HashMap)queryCollection);
		logger.debug(EXIT);
		
		return newQuote;
	}

	public String getBetterHomeId() throws BaseException {
		logger.info("getBetterHomeId method()");
		logger.debug(ENTER);
		final String pid = runner.singleSelection("select REMARKS from BHOMEPRODUCT_MASTER where PRODUCT_ID='101'");
		logger.debug(EXIT);
		
		return pid;
	}
	
	public String getBrokerCname(final String login) throws BaseException {
		logger.info("getBrokerCname method()");
		logger.debug(ENTER);
		final String args[] = {login};
		final String cname = runner.singleSelection("select company_name from BROKER_COMPANY_MASTER where AGENCY_CODE=(select oa_code from login_master where login_id=?)",args);
		logger.debug(EXIT);
		
		return cname;
	}

	public String[][] getBetterMailDetails(final String transId) throws BaseException {
		logger.info("getBetterMailDetails method()");
		logger.debug(ENTER);
		final String args[] = {transId};
		final String result[][] = runner.multipleSelection("select bhm.client_name,bhm.email,bhm.quote_no,bpm.BED_RM_NO,bpm.SUM_INSURED,bpm.PREMIUM from BETTER_HOME_MASTER bhm,BHOMEPREMIUM_MASTER bpm where bhm.tran_id=? and bhm.spec = bpm.spec",args);
		logger.debug(EXIT);
		
		return result;
	}
	// better home file uplaod process end
}