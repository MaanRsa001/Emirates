package com.maan.common.CopyQuote;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.SqlTypeValue;

import com.maan.DBCon.DBConnection;
import com.maan.common.LogUtil;
import com.maan.common.exception.BaseException;
import com.maan.services.policyInfo;
import com.maan.services.util.runner;

public class DataSelect
{
	private static final Logger logger = LogUtil.getLogger(DataSelect.class);
	private transient String query = "";
	public String getPolicyExistOrNot(final String policyNo,final  String pid,final String loginid) throws BaseException
	{
		logger.info("getPolicyExistOrNot method()");
		logger.debug("- Enter");

		String ecode = "";
		final String args[] = {policyNo,loginid};
		final String[][] CheckQuoteNos =   runner.multipleSelection("select policy_no,status,nvl(remarks,'normal'),nvl(admin_referral_status,'N') from Home_position_master where POLICY_NO=? and product_id in("+pid+") and login_id in(select login_id from login_master where oa_code=(select oa_code from login_master where login_id=?))  and status not in('I','C')",args);
		if(CheckQuoteNos.length>0){
			ecode = CheckQuoteNos[0][0];
		}

		logger.debug("- Exit");
		

		return 	  ecode;
	}

	public String getQuoteExistOrNot(final String QuoteNo,final String pid,final String loginid) throws BaseException
	{
		logger.info("getQuoteExistOrNot method()");
		logger.debug("- Enter");

		String ecode = "";
		final String args[] = {QuoteNo,loginid};
		final String CheckQuoteNos[][] =   runner.multipleSelection("select quote_no,status,nvl(remarks,'normal'),nvl(admin_referral_status,'N') from Home_position_master where quote_no=?  and login_id in(select login_id from login_master where oa_code=(select oa_code from login_master where login_id=?)) and product_id in ("+pid+")",args);
		if(CheckQuoteNos.length>0){
			ecode = CheckQuoteNos[0][0];
		}

		logger.debug("- Exit");
		

		return 	  ecode;
	}

	//For B2C start
	//public String getQuoteExistOrNotB2C(final String QuoteNo,final String pid,final String loginid) throws BaseException
	public String[] getQuoteExistOrNotB2C(final String QuoteNo,final String emailId,final String loginid) throws BaseException
	{
		logger.info("getQuoteExistOrNotB2C method()");
		logger.debug("- Enter");

		String[] ecode = new String[4];
		ecode[0] = "";
		ecode[1] = "";
		ecode[2] = "";
		ecode[3] = "";
		final String args[] = {QuoteNo,loginid,emailId};
		//final String[][] CheckQuoteNos =   runner.multipleSelection("select quote_no,status,nvl(remarks,'normal'),nvl(admin_referral_status,'N') from Home_position_master where quote_no=? and login_id in(select login_id from login_master where oa_code=(select oa_code from login_master where login_id=?)) and product_id in ("+pid+")",args);
		String[][] CheckQuoteNos =   runner.multipleSelection("select quote_no,status,nvl(remarks,'normal'),nvl(admin_referral_status,'N'),(case when product_id=100 then  cast(proposal_no as int) else product_id end),customer_id,APPLICATION_NO from Home_position_master where quote_no=? and login_id in(select login_id from login_master where oa_code=(select oa_code from login_master where login_id=?)) and customer_id in (select customer_id from personal_info where email=?)",args);
		//String[][] CheckQuoteNos =   runner.multipleSelection("select quote_no,status,ISNULL(remarks,'normal'),ISNULL(admin_referral_status,'N'),(case when product_id=100 then  cast(proposal_no as int) else product_id end),customer_id,APPLICATION_NO from Home_position_master where quote_no=? and login_id in(select login_id from login_master where oa_code=(select oa_code from login_master where login_id=?)) and customer_id in (select customer_id from personal_info where email=?)",args);
		if(CheckQuoteNos.length>0)
		{
			ecode[1] = CheckQuoteNos[0][4]==null?"":CheckQuoteNos[0][4];
			ecode[2] = CheckQuoteNos[0][5]==null?"":CheckQuoteNos[0][5];
			ecode[3] = CheckQuoteNos[0][6]==null?"":CheckQuoteNos[0][6];
			if("P".equalsIgnoreCase(CheckQuoteNos[0][1])){
				ecode[0] = "294";
			}
			else if("D".equalsIgnoreCase(CheckQuoteNos[0][1])){
				ecode[0] = "297";
			}
			else if("R".equalsIgnoreCase(CheckQuoteNos[0][1])){
				ecode[0] = "298";
			}
			else if("Referal".equalsIgnoreCase(CheckQuoteNos[0][2])){
				ecode[0] = "295";
			}
			else if("Admin".equalsIgnoreCase(CheckQuoteNos[0][2])){
				ecode[0] = "4491";				
			}
			else if("Y".equalsIgnoreCase(CheckQuoteNos[0][3])){
				ecode[0] = "295";
			}
			else{
				ecode[0] = CheckQuoteNos[0][0];
			}
		}
		
		logger.debug("- Exit");
		

		return 	  ecode;
	}

	public String[] getPolicyExistOrNotB2C(final String policyNo,final String pid,final String loginid) throws BaseException
	{
		logger.info("getPolicyExistOrNotB2C method()");
		logger.debug("- Enter");

		String[] CheckQuoteNos = new String[2];
		CheckQuoteNos[0] = "";
		CheckQuoteNos[1] = "";
		String args[] = new String[2];
		args[0] = policyNo;
		args[1] = loginid;
		String[][] result =   runner.multipleSelection("select quote_no,(case when product_id=100 then  cast(proposal_no as int) else product_id end) from Home_position_master where POLICY_NO=? and login_id in(select login_id from login_master where oa_code=(select oa_code from login_master where login_id=?))  and status not in('I','C')",args);
		if(result.length>0){
			CheckQuoteNos[0] = result[0][0];
			CheckQuoteNos[1] = result[0][1];
		}
		logger.debug("- Exit");
		

		return 	CheckQuoteNos;
	}

	public String[] getQuotePolicyExistOrNotB2C(final String quote_no,final String pid,final String loginid) throws BaseException
	{
		logger.info("getQuotePolicyExistOrNotB2C method()");
		logger.debug("- Enter");

		String[] CheckQuoteNos = new String[2];
		CheckQuoteNos[0] = "";
		CheckQuoteNos[1] = "";
		final String args[] = {quote_no,loginid};
		String[][] result = runner.multipleSelection("select quote_no,(case when product_id=100 then  cast(proposal_no as int) else product_id end) from Home_position_master where quote_no=? and login_id in(select login_id from login_master where oa_code=(select oa_code from login_master where login_id=?))  and status not in('I','C','Y')",args);
		if(result.length>0){
			CheckQuoteNos[0] = result[0][0];
			CheckQuoteNos[1] = result[0][1];
		}		
		logger.debug("- Exit");
		

		return 	CheckQuoteNos;
	}
	//FOr B2C End

	public String getCustomerQuoteName(final String QuoteNo,final String pid,final String loginid) throws BaseException
	{
		logger.info("getCustomerQuoteName method()");
		logger.debug("- Enter");

	    String quoteNo = QuoteNo.trim();
	    String login_id = loginid.trim();

		final String args[] = {"%"+quoteNo.trim().toLowerCase()+"%","%"+quoteNo.trim().toLowerCase()+"%","%"+quoteNo.trim().toLowerCase()+"%",login_id};
		final String CheckQuoteNo = runner.singleSelection("select (b.first_name||b.last_name||b.company_name),a.quote_no,a.customer_id,a.premium, nvl(a.policy_no,'0') from home_position_master a,personal_info b where (lower((b.first_name)) like ? or (lower(b.last_name)) like ? or (lower(b.company_name)) like ?) and a.customer_id = b.customer_id and a.product_id in("+pid+") and a.status not in ('D','I','C') and a.login_id = ?",args);

		logger.debug("- Exit");
		

		return CheckQuoteNo;
	}

	public String[][] getDetails(final String policyNo, String pid, final String QuoteNoOrPolicyNo,final String loginid,final String loginBranch) throws BaseException
	{
		logger.info("getDetails method()");
		logger.debug("- Enter");

		String[][] CheckQuoteNo	= new String[0][0];
		final String TarvelPID = getAllTravelPids(pid,loginBranch);
		if(TarvelPID.length()>0){
			pid = TarvelPID;
		}
		if("2".equalsIgnoreCase(QuoteNoOrPolicyNo.trim()))
		{
			 final String args[] = {policyNo.trim().toLowerCase(Locale.ENGLISH)};
			 CheckQuoteNo		=   runner.multipleSelection("select a.quote_no,a.customer_id,(b.first_name||b.last_name||b.company_name),a.premium,nvl(a.policy_no,'0') from home_position_master a,personal_info b where a.policy_no=?  and a.customer_id = b.customer_id and a.product_id in("+pid+") and a.status not in ('D')",args);
		}
		else if("3".equalsIgnoreCase(QuoteNoOrPolicyNo.trim()))
		{
			 final String args[] = {policyNo.trim().toLowerCase(Locale.ENGLISH)};
			 CheckQuoteNo		=   runner.multipleSelection("select a.quote_no,a.customer_id,(b.first_name||b.last_name||b.company_name),a.premium,nvl(a.policy_no,'0') from home_position_master a,personal_info b where a.quote_no=? and a.customer_id = b.customer_id and a.product_id in("+pid+") and a.status not in ('D')",args);
		}
		else if("4".equalsIgnoreCase(QuoteNoOrPolicyNo.trim()))
		{
			final String args[] = {"%"+policyNo.trim().toLowerCase(Locale.ENGLISH)+"%","%"+policyNo.trim().toLowerCase(Locale.ENGLISH)+"%","%"+policyNo.trim().toLowerCase(Locale.ENGLISH)+"%", loginid.trim()};
			CheckQuoteNo		=   runner.multipleSelection("select a.quote_no,a.customer_id,(b.first_name||b.last_name||b.company_name),a.premium, nvl(a.policy_no,'0') from home_position_master a,personal_info b where(lower (b.first_name) like ? or (lower(b.last_name)) like ? or (lower(b.company_name)) like ?)  and a.customer_id = b.customer_id and a.product_id in("+pid+") and a.login_id=? and a.status not in ('D')",args);
		}

		logger.debug("- Exit");
		

		return 	CheckQuoteNo;
	}

	//For B2C Start
	public String[][] getDetailsB2C(final String policyNo, String pid,final String QuoteNoOrPolicyNo,final  String loginid,final String loginBranch) throws BaseException
	{
		logger.info("getDetailsB2C method()");
		logger.debug("- Enter");

		String[][] CheckQuoteNo	= new String[0][0];
		String TarvelPID = getAllTravelPids(pid,loginBranch);
		if(TarvelPID.length()>0){
			pid = TarvelPID;
		}

		if("2".equalsIgnoreCase(QuoteNoOrPolicyNo.trim()))
		{
			 final String args[] = {policyNo.trim().toLowerCase(Locale.ENGLISH)};
			 CheckQuoteNo		=   runner.multipleSelection("select a.quote_no,a.customer_id,(b.first_name||b.last_name||b.company_name),a.premium,nvl(a.policy_no,'0') from home_position_master a,personal_info b where a.policy_no=?  and a.customer_id = b.customer_id and a.product_id in("+pid+") and a.status not in ('D') and a.remarks is null",args);
		}
		else if("3".equalsIgnoreCase(QuoteNoOrPolicyNo.trim())) //this one only now
		{
			 final String args[] = {policyNo.trim().toLowerCase(Locale.ENGLISH)};
			 CheckQuoteNo		=   runner.multipleSelection("select a.quote_no,a.customer_id,(b.first_name||b.last_name||b.company_name),a.premium,nvl(a.policy_no,'0') from home_position_master a,personal_info b where a.quote_no=? and a.customer_id = b.customer_id and a.product_id in("+pid+") and a.status not in ('D','R','P') and (a.remarks in('Admin') or a.remarks is null)",args);
		}
		else if("4".equalsIgnoreCase(QuoteNoOrPolicyNo.trim()))
		{
			final String args[] = {"%"+policyNo.trim().toLowerCase(Locale.ENGLISH)+"%","%"+policyNo.trim().toLowerCase(Locale.ENGLISH)+"%","%"+policyNo.trim().toLowerCase(Locale.ENGLISH)+"%",loginid.trim()};
			CheckQuoteNo		=   runner.multipleSelection("select a.quote_no,a.customer_id,(b.first_name||b.last_name||b.company_name),a.premium, nvl(a.policy_no,'0') from home_position_master a,personal_info b where(lower (b.first_name) like ? or (lower(b.last_name)) like ? or (lower(b.company_name)) like ?)  and a.customer_id = b.customer_id and a.product_id in("+pid+") and a.login_id =? and a.status not in ('D') and a.remarks is null",args);
		}

		logger.debug("- Exit");
		

		return 	CheckQuoteNo;
	}

	public String[][] getPolicyDetailsB2C(final String policyNo,String pid,final String QuoteNoOrPolicyNo, final String loginid,final String loginBranch) throws BaseException
	{
		logger.info("getPolicyDetailsB2C method()");
		logger.debug("- Enter");

		String[][] CheckQuoteNo	= new String[0][0];
		String TarvelPID = getAllTravelPids(pid,loginBranch);
		if(TarvelPID.length()>0){
			pid = TarvelPID;
		}

		if("2".equalsIgnoreCase(QuoteNoOrPolicyNo.trim())){
			 final String args[] = {policyNo.trim().toLowerCase(Locale.ENGLISH)};
			 CheckQuoteNo		=   runner.multipleSelection("select a.quote_no,a.customer_id,(b.first_name||b.last_name||b.company_name),a.premium,nvl(a.policy_no,'0') from home_position_master a,personal_info b where a.quote_no=?  and a.customer_id = b.customer_id and a.product_id in("+pid+") and a.status in ('P')",args);
		}
		else if("3".equalsIgnoreCase(QuoteNoOrPolicyNo.trim())){
			 String args[] = {policyNo.trim().toLowerCase(Locale.ENGLISH)};
			 CheckQuoteNo		=   runner.multipleSelection("select a.quote_no,a.customer_id,(b.first_name||b.last_name||b.company_name),a.premium,nvl(a.policy_no,'0') from home_position_master a,personal_info b where a.quote_no=? and a.customer_id = b.customer_id and a.product_id in("+pid+") and a.status in('P')",args);
		}
		else if("4".equalsIgnoreCase(QuoteNoOrPolicyNo.trim())){
			final String args[] = {"%"+policyNo.trim().toLowerCase(Locale.ENGLISH)+"%","%"+policyNo.trim().toLowerCase(Locale.ENGLISH)+"%","%"+policyNo.trim().toLowerCase(Locale.ENGLISH)+"%",loginid.trim()};
			CheckQuoteNo		=   runner.multipleSelection("select a.quote_no,a.customer_id,(b.first_name||b.last_name||b.company_name),a.premium, nvl(a.policy_no,'0') from home_position_master a,personal_info b where(lower (b.first_name) like ? or (lower(b.last_name)) like ? or (lower(b.company_name)) like ?)  and a.customer_id = b.customer_id and a.product_id in("+pid+") and a.login_id =? and a.status not in ('D')",args);
		}

		logger.debug("- Exit");
		

		return 	CheckQuoteNo;
	}

	//FOr B2c END
	public String getAllTravelPids(final String pid,final String loginBranch) throws BaseException
	{
		logger.info("getAllTravelPids method()");
		logger.debug("- Enter");

		query = "select PRODUCT_ID from HOME_PRODUCT_MASTER where BROKER_ID=(select REMARKS from PRODUCT_MASTER where PRODUCT_ID=? and BRANCH_CODE=?)";
		final String args[] = {pid,loginBranch};
		String valuess[][]  = new String[0][0];
		StringBuffer result = new StringBuffer(1000);

		valuess=runner.multipleSelection(query,args);
		for(int i=0;i<valuess.length;i++){
			result.append(result);
			result.append(valuess[i][0]);
			result.append(',');
		}
		if(result.length()>0){
			result = new StringBuffer(result.toString().substring(0,(result.length()-1)));
		}

		logger.debug("- Exit");
		

		return result.toString();
	}

	public String getCustomerName(final String quoteNo) throws BaseException
	{
		logger.info("getCustomerName method()");
		logger.debug("- Enter");

		final String args[] = {quoteNo};
		String query =	runner.singleSelection("select nvl(first_name,company_name)  from personal_info where customer_id = (select customer_id from position_master where quote_no = ?)",args);
		if(query==null||query.length()<=0){
			query =	runner.singleSelection("select nvl(first_name,company_name)  from personal_info where customer_id = (select customer_id from home_position_master where quote_no =?)",args);
		}
		if(query==null||query.length()<=0){
			query =	runner.singleSelection("select nvl(first_name,company_name)  from personal_info where customer_id = (select customer_id from Home_position_master where quote_no =?)",args);
		}

		logger.debug("- Exit");
		

		return query;
	}

	public String getMaxQuoteId(final String productTypeId,final String loginBranch) throws BaseException
	{
		logger.info("getMaxQuoteId method()");
		logger.debug("- Enter");

		String currentNo;
		final String args[] = {loginBranch,loginBranch,productTypeId,loginBranch,productTypeId,loginBranch,productTypeId};
		String sql;
		sql = "select nvl(max(current_no)+1,max(start_no)) from policyno_generate where BRANCH_CODE=? and type_id=(select QUOTE_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE=? and PRODUCT_ID=?) and status='Y' and " +
		"amend_id=(select max(amend_id) from policyno_generate where type_id=(select QUOTE_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE=? and PRODUCT_ID=?) and BRANCH_CODE=? and PRODUCT_ID=?)";
		currentNo = runner.singleSelection(sql,args);
		
		sql = "update policyno_generate set current_no=?,remarks=? where BRANCH_CODE=? and type_id=(select QUOTE_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE=? and PRODUCT_ID=?) and status='Y' and " +
		"amend_id=(select max(amend_id) from policyno_generate where type_id=(select QUOTE_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE=? and PRODUCT_ID=?) and BRANCH_CODE=? and PRODUCT_ID=?)";
		final String args1[] = {currentNo,currentNo,loginBranch,loginBranch,productTypeId,loginBranch,productTypeId,loginBranch,productTypeId};
		runner.multipleUpdation(sql,args1);

		logger.debug("- Exit");
		

		return currentNo;
	}
	public String copyQuote(final String QuoteNo,final String pid,final String OldCusId,final String NewCusId,final String pStatus,final String loginBranch) throws BaseException
	{
		logger.info("CopyQuote method()");
		logger.debug("- Enter");

		String 	 Return	=	"";
		if("royalHome".equalsIgnoreCase(pStatus)){
			String NewQuoteNo	=	getMaxQuoteId(pid,loginBranch);
			Map queryCollection; queryCollection = new HashMap();
			query	=	"insert into HOME_POSITION_MASTER(QUOTE_NO,PROPOSAL_NO,CUSTOMER_ID,LOGIN_ID,PRODUCT_ID,COMPANY_ID,POLICY_NO,POLICY_TERM,PREMIUM,AMEND_ID,INCEPTION_DATE,EXPIRY_DATE,EFFECTIVE_DATE,ENTRY_DATE,REMARKS,STATUS,APPLICATION_NO,DEBIT_NOTE_NO,DEBIT_NOTE_DATE,LAPSED_REMARKS,LAPSED_DATE,LAPSED_UPDATED_BY,BROKER_ADDITIONAL_COMMISSION,OVERALL_PREMIUM,COMMISSION,BED_ROOM)" +
					"  select "+NewQuoteNo+",PROPOSAL_NO,CUSTOMER_ID,LOGIN_ID,PRODUCT_ID, COMPANY_ID,'',POLICY_TERM,PREMIUM,AMEND_ID,(SELECT SYSDATE FROM DUAL),(select (sysdate-1) + interval '12' month from dual)," +
							"EFFECTIVE_DATE,(SELECT SYSDATE FROM DUAL),REMARKS,'Y',APPLICATION_NO,'','',LAPSED_REMARKS,LAPSED_DATE,LAPSED_UPDATED_BY,BROKER_ADDITIONAL_COMMISSION,OVERALL_PREMIUM,COMMISSION," +
							"BED_ROOM from HOME_POSITION_MASTER where QUOTE_NO='"+QuoteNo+"'";
			queryCollection.put("Query0", query);

			query	=	"insert into HOME_COVERAGE_BUILDING(QUOTE_NO,COVER_ID,FINANCE_COMPANY_NAME,LOAN_AMOUNT,FINANCE_TELEPHONE,REMARKS,STATUS,AMEND_ID,BUILDING_NAME,STREET_NAME,FLAT_VILLA_NO,EMIRATE,POBOX)" +
					" select "+NewQuoteNo+",COVER_ID,FINANCE_COMPANY_NAME,LOAN_AMOUNT,FINANCE_TELEPHONE,REMARKS,STATUS,AMEND_ID,BUILDING_NAME,STREET_NAME,FLAT_VILLA_NO,EMIRATE,POBOX from HOME_COVERAGE_BUILDING where  QUOTE_NO='"+QuoteNo+"'";
			queryCollection.put("Query1", query);
			query		=	"insert into HOME_COVERAGE_TRANSACTION(QUOTE_NO,HOME_SNO,COVER_ID,PRODUCT_SERIAL_NO,PRODUCT_DESCRIPTION,PRODUCT_SUMINSURED,REMARKS,STATUS,AMEND_ID,PREMIUM,RATE) " +
					"select "+NewQuoteNo+",HOME_SNO,COVER_ID,PRODUCT_SERIAL_NO,PRODUCT_DESCRIPTION,PRODUCT_SUMINSURED,REMARKS,STATUS,AMEND_ID,PREMIUM,RATE " +
					"from HOME_COVERAGE_TRANSACTION where quote_no='"+QuoteNo+"'";
			queryCollection.put("Query2", query);
			
			query = "insert into HOME_UW_QUESTIONS(QUOTE_NO,BUILT_OF_CONCRETE,PRIVATE_LIVING_ACCOMODATION,BUILT_ON_RECLAIMED_LAND,UNATTENDED_60_CONSECUTIVE_DAYS,DECLINED_CANELLED_CONDITIONS,ANY_CLAIM_IN_3YRS,NO_OF_CLAIMS,TYPE_OF_CLAIMS,CLAIM_AMOUNT) " +
					"select "+NewQuoteNo+",BUILT_OF_CONCRETE,PRIVATE_LIVING_ACCOMODATION,BUILT_ON_RECLAIMED_LAND,UNATTENDED_60_CONSECUTIVE_DAYS,DECLINED_CANELLED_CONDITIONS,ANY_CLAIM_IN_3YRS,NO_OF_CLAIMS,TYPE_OF_CLAIMS,CLAIM_AMOUNT from HOME_UW_QUESTIONS where quote_no='"+QuoteNo+"'";
			queryCollection.put("Query3", query);
			query = "insert into HOME_DOMESTIC_STAFF(QUOTE_NO,NAME,DATE_OF_BIRTH,NO_DOMESTIC_STAFF,DOMESTIC_STAFF_SNO) " +
					"select "+NewQuoteNo+",NAME,DATE_OF_BIRTH,NO_DOMESTIC_STAFF,DOMESTIC_STAFF_SNO from HOME_DOMESTIC_STAFF where quote_no='"+QuoteNo+"'";
			queryCollection.put("Query4", query);
			queryCollection.put("Count", "5");
			String result;
			result = runner.insertionTransaction((HashMap)queryCollection);
			if (result.equalsIgnoreCase("INSERRTED")){
				query = "update HOME_POSITION_MASTER set CUSTOMER_ID=?,REMARKS='',NO_CLAIM_DISCOUNT_VALUE=?,POLICY_NO = '', INCEPTION_DATE = '',  DEBIT_NOTE_NO = '', DEBIT_NOTE_DATE='' where QUOTE_NO =?";
				final String args[] = {NewCusId,QuoteNo,NewQuoteNo};
				runner.multipleUpdation(query,args);
			}
			Return	=	NewQuoteNo;
		}
		else{
			String NewQuoteNo	=	getMaxQuoteId(pid,loginBranch);
			Map queryCollection; queryCollection = new HashMap();
			query				=	"insert into Home_position_master(QUOTE_NO,PROPOSAL_NO,CUSTOMER_ID,LOGIN_ID,PRODUCT_ID,COMPANY_ID,POLICY_NO,POLICY_TERM,PREMIUM,AMEND_ID,INCEPTION_DATE,EXPIRY_DATE,EFFECTIVE_DATE,ENTRY_DATE,REMARKS,STATUS,APPLICATION_NO,DEBIT_NOTE_NO,DEBIT_NOTE_DATE,LAPSED_REMARKS,LAPSED_DATE,LAPSED_UPDATED_BY,PDF_BROKER_STATUS,BROKER_ADDITIONAL_COMMISSION,NO_CLAIM_DISCOUNT,NO_CLAIM_DISCOUNT_VALUE,EXCESS_PREMIUM,ADMIN_REMARKS,ADMIN_REFERRAL_STATUS,EXCESS_SIGN,OVERALL_PREMIUM,COMMISSION,SUMMARY_CLAUSES,SUMMARY_REMARKS,AGE_ABOVE_SIXTY_FIVE,EXISTING_MEDICAL_CONDITION,MEDICAL_TRAVEL_CLAIMS,REFERRAL_DESCRIPTION,AIRMILES_NO,INSURANCE_DETAILS,RECEIPT_NO,RECEIPT_DATE,csh_id_typ_code) " +
					"select "+NewQuoteNo+", PROPOSAL_NO,CUSTOMER_ID,LOGIN_ID,PRODUCT_ID,COMPANY_ID,'','',PREMIUM,AMEND_ID,INCEPTION_DATE,EXPIRY_DATE,EFFECTIVE_DATE,ENTRY_DATE,REMARKS,'Y',APPLICATION_NO,'','',LAPSED_REMARKS,LAPSED_DATE,LAPSED_UPDATED_BY,PDF_BROKER_STATUS,BROKER_ADDITIONAL_COMMISSION,NO_CLAIM_DISCOUNT,NO_CLAIM_DISCOUNT_VALUE,EXCESS_PREMIUM,ADMIN_REMARKS,ADMIN_REFERRAL_STATUS,EXCESS_SIGN,OVERALL_PREMIUM,COMMISSION,SUMMARY_CLAUSES,SUMMARY_REMARKS,AGE_ABOVE_SIXTY_FIVE,EXISTING_MEDICAL_CONDITION,MEDICAL_TRAVEL_CLAIMS,REFERRAL_DESCRIPTION,AIRMILES_NO,INSURANCE_DETAILS,RECEIPT_NO,RECEIPT_DATE,csh_id_typ_code from Home_position_master where QUOTE_NO='"+QuoteNo+"'";
			queryCollection.put("Query0", query);

			query	=	"insert into TRAVEL_HEADER(QUOTE_NO,TOTAL_ADULT_PASSENGERS,TOTAL_CHILD_PASSENGERS,TOTAL_NO_OF_PEOPLE_COVERED,COVERAGE_US_CANADA,COVERAGE_WINTERSPORTS,COVERAGE_GOLFCOVER,ADDITIONAL_COVER,POLICY_TERM,POLICY_TRAVEL_TRIPS,CLAIM_Y_N,CLAIM_REMARKS,CLAIM_AMOUNT,ADULT_PREMUIM,CHILD_PREMIUM,WINTER_PREMIUM,GOLF_PREMIUM,ADDITIONAL_PREMIUM,TOTAL_PREMIUM,INSURANCE_START_DATE,INSURANCE_END_DATE,PREMIUM_LOAD_DIS_PERCENT,PREMIUM_LOAD_DIS_DESC,PREMIUM_LOAD_DIS_AMOUNT,ENTRY_DATE,EFFECTIVE_DATE,AMEND_ID,CUSTOMER_ID,LOGIN_ID,REMARKS,STATUS,NET_PREMIUM,PRODUCTID,CHILD_BETWEEN_3_TO_17," +
					"WORLDWIDE,BAGGAGE_EXCLUDED,MEDICAL_EXPENSES_EXCLUDED,EXCLUDE_USA_CANADA,SPOUSE_DISCOUNT,FAMILY_DISCOUNT,DISCOUNTED_VALUE,TERRORISM_EXTENSION)" +
					" select "+NewQuoteNo+", TOTAL_ADULT_PASSENGERS,TOTAL_CHILD_PASSENGERS,TOTAL_NO_OF_PEOPLE_COVERED,COVERAGE_US_CANADA,COVERAGE_WINTERSPORTS,COVERAGE_GOLFCOVER,ADDITIONAL_COVER,POLICY_TERM,POLICY_TRAVEL_TRIPS,CLAIM_Y_N,CLAIM_REMARKS,CLAIM_AMOUNT,ADULT_PREMUIM,CHILD_PREMIUM,WINTER_PREMIUM,GOLF_PREMIUM," +
							"ADDITIONAL_PREMIUM,TOTAL_PREMIUM,INSURANCE_START_DATE,INSURANCE_END_DATE,PREMIUM_LOAD_DIS_PERCENT,PREMIUM_LOAD_DIS_DESC,PREMIUM_LOAD_DIS_AMOUNT,ENTRY_DATE,EFFECTIVE_DATE,0,CUSTOMER_ID,LOGIN_ID,REMARKS,STATUS,NET_PREMIUM,PRODUCTID,CHILD_BETWEEN_3_TO_17,WORLDWIDE,BAGGAGE_EXCLUDED,MEDICAL_EXPENSES_EXCLUDED,EXCLUDE_USA_CANADA,SPOUSE_DISCOUNT,FAMILY_DISCOUNT,DISCOUNTED_VALUE,TERRORISM_EXTENSION from TRAVEL_HEADER where quote_no='"+QuoteNo+"' and amend_id=(select max(amend_id) from TRAVEL_HEADER where QUOTE_NO ='"+QuoteNo+"')";
			queryCollection.put("Query1", query);

			query = "insert into TRAVEL_DETAIL(QUOTE_NO,SERIAL_NO,PASSENGER_NAME,DOB,RELATION,ILLNESS_Y_N,ILLNESS_REMARKS,AMEND_ID,REMARKS,STATUS,GENDER,NATIONALITY,AGE,PREMIUM) " +
					"select "+NewQuoteNo+" ,SERIAL_NO,PASSENGER_NAME,DOB,RELATION,ILLNESS_Y_N,ILLNESS_REMARKS,0,REMARKS,STATUS,GENDER,NATIONALITY,AGE,PREMIUM from TRAVEL_DETAIL where QUOTE_NO='"+QuoteNo+"' and amend_id=(select max(amend_id) from TRAVEL_DETAIL where QUOTE_NO ='"+QuoteNo+"')";
			queryCollection.put("Query2", query);
			queryCollection.put("Count", "3");
			String result;
			result = runner.insertionTransaction((HashMap)queryCollection);
			if (result.equalsIgnoreCase("INSERRTED")){
				String updateSql;
				if("royal".equalsIgnoreCase(pStatus)){
					updateSql = "update Home_position_master set CUSTOMER_ID=?,NO_CLAIM_DISCOUNT_VALUE=?,POLICY_NO = '', INCEPTION_DATE = '',DEBIT_NOTE_NO = '', DEBIT_NOTE_DATE='',RECEIPT_NO='',RECEIPT_DATE='',REMARKS=''," +
							"ADMIN_REFERRAL_STATUS='',EXCESS_PREMIUM='',ADMIN_REMARKS='',EXCESS_SIGN='',INSURANCE_DETAILS='',SUMMARY_REMARKS='',REFERRAL_DESCRIPTION='',SUMMARY_CLAUSES='' where QUOTE_NO=?";
					final String args1[] = {NewCusId,QuoteNo,NewQuoteNo};
					runner.multipleUpdation(updateSql,args1);
				}
				else{
					updateSql = "update Home_position_master set CUSTOMER_ID =?,reissued_policy_no=?,POLICY_NO = '', INCEPTION_DATE = '',DEBIT_NOTE_NO = '', DEBIT_NOTE_DATE='',RECEIPT_NO='',RECEIPT_DATE='',REMARKS='',ADMIN_REFERRAL_STATUS='',EXCESS_PREMIUM='',ADMIN_REMARKS='',EXCESS_SIGN=''," +
								"INSURANCE_DETAILS='',SUMMARY_REMARKS='',REFERRAL_DESCRIPTION='',SUMMARY_CLAUSES='' where QUOTE_NO=?";
						final String args1[] = {NewCusId,pStatus,NewQuoteNo};
						runner.multipleUpdation(updateSql,args1);
				}
				query = "update TRAVEL_HEADER set CUSTOMER_ID=? where QUOTE_NO=?";
				String args[] = {NewCusId,NewQuoteNo};
				runner.multipleUpdation(query,args);
			}
			/*query = "update TRAVEL_DETAIL set DOB = (select DOB from PERSONAL_INFO where CUSTOMER_ID=?), NATIONALITY = (select NATIONALITY from PERSONAL_INFO where CUSTOMER_ID=?),PASSENGER_NAME=(select nvl(FIRST_NAME,COMPANY_NAME) from PERSONAL_INFO " +
					"where CUSTOMER_ID=?) where QUOTE_NO =? and SERIAL_NO='1' and amend_id=0";
			String args1[] = {NewCusId,NewCusId,NewCusId,NewQuoteNo};
			runner.multipleUpdation(query,args1);*/

			Return	=	NewQuoteNo;
		}

		logger.debug("- Exit");
		

		return 	Return;
	}

	public void changePolicyStatus(final String pno,final String status,final String remarks,final String login,final String qno) throws BaseException
	{
		logger.info("changePolicyStatus method()");
		logger.debug("- Enter");

		if("None".equalsIgnoreCase(qno)){
			query  = "update Home_position_master set STATUS=?,cancelled_reason=?,cancelled_date=(select sysdate+8/24 from dual),cancelled_by=? where POLICY_NO=?";
			final String args[] = {status,remarks,login,pno};
			runner.multipleUpdation(query,args);
		}
		else
		{
			query = "update Home_position_master set STATUS=?,cancelled_reason=?,cancelled_date=(select sysdate+8/24 from dual),cancelled_by=?,reissued_quote_no=? where POLICY_NO=?";
			final String args[] = {status,remarks,login,qno,pno};
			runner.multipleUpdation(query,args);
		}

		logger.debug("- Exit");
		
	}

	public boolean getReIssueStatus(final String qno)  throws BaseException
	{
		logger.info("getReIssueStatus method()");
		logger.debug("- Enter");

		boolean result = false;
		query = "select count(*) from Home_position_master where EFFECTIVE_DATE>(select sysdate+8/24 from dual) and quote_no=?";
		final String args[] = {qno};
		final String res = runner.singleSelection(query,args);
		if("0".equals(res)){
			result = false;
		}
		else{
			result = true;
		}

		logger.debug("- Exit");
		

		return result;
	}

	//Marine Copy Quotes - Start///////////
	public String[][] getMarineDetails(final String policyNo,final String pid,final String QuoteNoOrPolicyNo,final String loginid,final String openCoverNo,final String rsaissuer) throws BaseException
	{
		logger.info("getMarineDetails method()");
		logger.debug("- Enter");

		String[][] CheckQuoteNo	= new String[0][0];
		String syntax = "";
		if("11".equalsIgnoreCase(pid)){
			//syntax = " and a.open_cover_no = '"+openCoverNo+"' ";
			syntax = " and a.open_cover_no like '%"+policyInfo.getOriginalOpenCoverPolicyNo(openCoverNo)+"%'";
		}
		else{
			syntax = " ";
		}
		if("2".equalsIgnoreCase(QuoteNoOrPolicyNo.trim())){
				final String args[] =  { policyNo.trim().toLowerCase(Locale.ENGLISH),pid };
				CheckQuoteNo		=   runner.multipleSelection("select a.quote_no,a.customer_id,(b.first_name||b.last_name||b.company_name),(nvl(a.premium,m.premium)+ NVL (a.excess_premium, m.excess_premium)),nvl(a.policy_no,'0') from position_master a,personal_info b,marine_data m where upper(a.policy_no)=upper(?)  and a.customer_id = b.customer_id and a.product_id=? and a.status='P' and (a.remarks not in('Admin','Referal','NORMAL_EXCESS_PRICE')) and m.application_no=a.application_no "+syntax+" and nvl(m.admin_referral_status,'N') not in ('Y')",args);
		}
		else if("3".equalsIgnoreCase(QuoteNoOrPolicyNo.trim())){
				final String args[] = {policyNo.trim().toLowerCase(Locale.ENGLISH),pid};
				CheckQuoteNo		=   runner.multipleSelection("select a.quote_no,a.customer_id,(b.first_name||b.last_name||b.company_name),(nvl(a.premium,m.premium)+ NVL (a.excess_premium, m.excess_premium)),nvl(a.policy_no,'0') from position_master a,personal_info b,marine_data m where a.quote_no=? and a.customer_id = b.customer_id and a.product_id=? and a.status='Y' and (a.remarks not in('Admin','Referal','NORMAL_EXCESS_PRICE')) and m.application_no=a.application_no "+syntax+" and nvl(m.admin_referral_status,'N') not in ('Y')",args);
		}
		else if("4".equalsIgnoreCase(QuoteNoOrPolicyNo.trim())){

				if(rsaissuer==null){
					query = "select a.quote_no,a.customer_id,(b.first_name||b.last_name||b.company_name),(nvl(a.premium,m.premium)+ NVL (a.excess_premium, m.excess_premium)), nvl(a.policy_no,'0') from position_master a,personal_info b,marine_data m where(lower (b.first_name) like trim(lower('%"+policyNo+"%')) or (lower(b.last_name)) like lower(trim('%"+policyNo+"%')) or (lower(b.company_name)) like lower(trim('%"+policyNo+"%')))  and a.customer_id = b.customer_id and a.product_id='"+pid+"' and a.login_id = '"+loginid.trim()+"' and a.status not in ('D') and (a.remarks not in('Admin','Referal','NORMAL_EXCESS_PRICE')) and m.application_no=a.application_no "+syntax+" and nvl(m.admin_referral_status,'N') not in ('Y') and a.application_id='1'";
					CheckQuoteNo =  runner.multipleSelection(query);
				}
				else{
//					query = "select a.quote_no,a.customer_id,(b.first_name||b.last_name||b.company_name),(nvl(a.premium,m.premium)+ NVL (a.excess_premium, m.excess_premium)), nvl(a.policy_no,'0') from position_master a,personal_info b,marine_data m where(lower (b.first_name) like ? or (lower(b.last_name)) like ? or (lower(b.company_name)) like ?)  and a.customer_id = b.customer_id and a.product_id=? and a.login_id = ? and a.status not in ('D') and (a.remarks not in('Admin','Referal','NORMAL_EXCESS_PRICE')) and m.application_no=a.application_no "+syntax+" and nvl(m.admin_referral_status,'N') not in ('Y') and a.application_id='"+rsaissuer+"'";
//					final String args[] = {"%"+policyNo.trim().toLowerCase(Locale.ENGLISH)+"%","%"+policyNo.trim().toLowerCase(Locale.ENGLISH)+"%","%"+policyNo.trim().toLowerCase(Locale.ENGLISH)+"%",pid,loginid.trim()};
					query = "select a.quote_no,a.customer_id,(b.first_name||b.last_name||b.company_name),(nvl(a.premium,m.premium)+ NVL (a.excess_premium, m.excess_premium)), nvl(a.policy_no,'0') from position_master a,personal_info b,marine_data m where(lower (b.first_name) like ? or (lower(b.last_name)) like ? or (lower(b.company_name)) like ?)  and a.customer_id = b.customer_id and a.product_id=? and a.status not in ('D') and (a.remarks not in('Admin','Referal','NORMAL_EXCESS_PRICE')) and m.application_no=a.application_no "+syntax+" and nvl(m.admin_referral_status,'N') not in ('Y') and a.application_id='"+rsaissuer+"'";
					final String args[] = {"%"+policyNo.trim().toLowerCase(Locale.ENGLISH)+"%","%"+policyNo.trim().toLowerCase(Locale.ENGLISH)+"%","%"+policyNo.trim().toLowerCase(Locale.ENGLISH)+"%",pid};
					CheckQuoteNo =  runner.multipleSelection(query,args);
				}
		}

		logger.debug("- Exit");
		

		return 	CheckQuoteNo;
	}

	public String getMarinePolicyExistOrNot(final String policyNo,final String pid,final String loginid,final String rsaissuer) throws BaseException
	{
		logger.info("getMarinePolicyExistOrNot method()");
		logger.debug("- Enter");

		String CheckQuoteNos[][] = 	new String[0][0];
		String ecode="";
		String sqlCheck = "";
		if(rsaissuer==null){
			sqlCheck = "select a.policy_no,a.status,nvl(a.remarks,'Normal'),nvl(m.admin_referral_status,'N') from position_master a,marine_data m where a.POLICY_NO=? and a.product_id=? and a.login_id in(select login_id from login_master where oa_code=(select oa_code from login_master where login_id=?)) and a.status='P' and m.application_no=a.application_no and a.application_id='1'";
			final String args[] = {policyNo,pid,loginid};
			CheckQuoteNos =  runner.multipleSelection(sqlCheck,args);
		}
		else{
//			sqlCheck = "select a.policy_no,a.status,nvl(a.remarks,'Normal'),nvl(m.admin_referral_status,'N') from position_master a,marine_data m where a.POLICY_NO=? and a.product_id=? and a.login_id in(select login_id from login_master where oa_code=(select oa_code from login_master where login_id=?)) and a.status='P' and m.application_no=a.application_no and a.application_id=?";
			sqlCheck = "select a.policy_no,a.status,nvl(a.remarks,'Normal'),nvl(m.admin_referral_status,'N') from position_master a,marine_data m where a.POLICY_NO=? and a.product_id=? and a.status='P' and m.application_no=a.application_no and a.application_id=?";
//			final String args[] = {policyNo,pid,loginid,rsaissuer};
			final String args[] = {policyNo,pid,rsaissuer};
			CheckQuoteNos =  runner.multipleSelection(sqlCheck,args);
		}
		if(CheckQuoteNos.length>0){

			if("Referal".equalsIgnoreCase(CheckQuoteNos[0][2])||"Admin".equalsIgnoreCase(CheckQuoteNos[0][2]) || "NORMAL_EXCESS_PRICE".equalsIgnoreCase(CheckQuoteNos[0][2])){
				ecode = "299";
			}
			else if("Y".equalsIgnoreCase(CheckQuoteNos[0][3])){
				ecode = "299";
			}
			else{
				ecode = CheckQuoteNos[0][0];
			}
		}
		else{
			ecode = "";
		}

		logger.debug("- Exit");
		

		return 	  ecode;
	}

	public String getMarineCustomerQuoteName(final String QuoteNo,final String pid,final String loginid,final String rsaissuer) throws BaseException
	{
		logger.info("getMarineCustomerQuoteName method()");
		logger.debug("- Enter");

	    String quoteNo = QuoteNo.trim();
	    //String login_id = loginid.trim();
	    String CheckQuoteNo="";
		String sqlCheck = "";
		if(rsaissuer == null){
			sqlCheck = "select (b.first_name||b.last_name||b.company_name) from position_master a,personal_info b,marine_data m where (lower((b.first_name)) like ? or (lower(b.last_name)) like ? or (lower(b.company_name)) like ?) and a.customer_id = b.customer_id and a.product_id=? and a.status in ('P','Y') and (a.remarks not in('Admin','Referal','NORMAL_EXCESS_PRICE')) and m.application_no=a.application_no and nvl(m.admin_referral_status,'N') not in ('Y') and a.product_id = ? and a.login_id = ? and a.application_id='1'";
			final String args[] = {"%"+quoteNo.trim().toLowerCase(Locale.ENGLISH)+"%","%"+quoteNo.trim().toLowerCase(Locale.ENGLISH)+"%","%"+quoteNo.trim().toLowerCase(Locale.ENGLISH)+"%",pid,pid,loginid.trim()};
			CheckQuoteNo = runner.singleSelection(sqlCheck,args);
		}
		else{
//			sqlCheck = "select (b.first_name||b.last_name||b.company_name) from position_master a,personal_info b,marine_data m where (lower((b.first_name)) like ? or (lower(b.last_name)) like ? or (lower(b.company_name)) like ?) and a.customer_id = b.customer_id and a.product_id=? and a.status in ('P','Y') and (a.remarks not in('Admin','Referal','NORMAL_EXCESS_PRICE')) and m.application_no=a.application_no and nvl(m.admin_referral_status,'N') not in ('Y') and a.product_id =? and a.login_id = ? and a.application_id=?";
			sqlCheck = "select (b.first_name||b.last_name||b.company_name) from position_master a,personal_info b,marine_data m where (lower((b.first_name)) like ? or (lower(b.last_name)) like ? or (lower(b.company_name)) like ?) and a.customer_id = b.customer_id and a.product_id=? and a.status in ('P','Y') and (a.remarks not in('Admin','Referal','NORMAL_EXCESS_PRICE')) and m.application_no=a.application_no and nvl(m.admin_referral_status,'N') not in ('Y') and a.product_id =? and a.application_id=?";
//			String args[] = {"%"+quoteNo.trim().toLowerCase(Locale.ENGLISH)+"%","%"+quoteNo.trim().toLowerCase(Locale.ENGLISH)+"%","%"+quoteNo.trim().toLowerCase(Locale.ENGLISH)+"%",pid,pid,loginid.trim(),rsaissuer};
			String args[] = {"%"+quoteNo.trim().toLowerCase(Locale.ENGLISH)+"%","%"+quoteNo.trim().toLowerCase(Locale.ENGLISH)+"%","%"+quoteNo.trim().toLowerCase(Locale.ENGLISH)+"%",pid,pid,rsaissuer};
			CheckQuoteNo = runner.singleSelection(sqlCheck,args);
		}

		logger.debug("- Exit");
		

		return CheckQuoteNo;
	}

	public String getMarineQuoteExistOrNot(final String QuoteNo,final String pid,final String loginid,final String rsaissuer) throws BaseException
	{
		logger.info("getMarineQuoteExistOrNot method()");
		logger.debug("- Enter");

		String CheckQuoteNos[][]	=	new String[0][0];
		String ecode="";
		String sqlCheck = "";

		if(rsaissuer==null){
			sqlCheck = "select a.quote_no,a.status,nvl(a.remarks,'normal'),nvl(m.admin_referral_status,'N') from position_master a,marine_data m where a.quote_no=? and m.application_no=a.application_no and a.login_id in(select login_id from login_master where oa_code=(select oa_code from login_master where login_id=?)) and a.product_id = ? and a.application_id='1'";
			final String args[] = {QuoteNo.trim(),loginid.trim(),pid};
			CheckQuoteNos = runner.multipleSelection(sqlCheck,args);
		}
		else{
//			sqlCheck = "select a.quote_no,a.status,nvl(a.remarks,'normal'),nvl(m.admin_referral_status,'N') from position_master a,marine_data m where a.quote_no=? and m.application_no=a.application_no and a.login_id in(select login_id from login_master where oa_code=(select oa_code from login_master where login_id=?)) and a.product_id = ? and a.application_id=?";
			sqlCheck = "select a.quote_no,a.status,nvl(a.remarks,'normal'),nvl(m.admin_referral_status,'N') from position_master a,marine_data m where a.quote_no=? and m.application_no=a.application_no and a.product_id = ? and a.application_id=?";
//			final String args[] = {QuoteNo.trim(),loginid.trim(),pid,rsaissuer};
			final String args[] = {QuoteNo.trim(),pid,rsaissuer};
			CheckQuoteNos = runner.multipleSelection(sqlCheck,args);
		}

		if(CheckQuoteNos.length>0){

			if("P".equalsIgnoreCase(CheckQuoteNos[0][1])){
				ecode = "294";
			}
			else if("D".equalsIgnoreCase(CheckQuoteNos[0][1])){
				ecode = "297";
			}
			else if("R".equalsIgnoreCase(CheckQuoteNos[0][1])){
				ecode = "298";
			}
			else if("Referal".equalsIgnoreCase(CheckQuoteNos[0][2]) || "Admin".equalsIgnoreCase(CheckQuoteNos[0][2]) || "NORMAL_EXCESS_PRICE".equalsIgnoreCase(CheckQuoteNos[0][2])){
				ecode = "295";
			}
			else if("Y".equalsIgnoreCase(CheckQuoteNos[0][3])){
				ecode = "295";
			}
			else{
				ecode = CheckQuoteNos[0][0];
			}
		}
		else{
			ecode = "";
		}

		logger.debug("- Exit");
		

		return 	  ecode;
	}

	public String marineCopyQuote(final String QuoteNo,final String pid,final String OldCusId,final  String NewCusId,final String loginBranch,final String rsaissuer) throws BaseException,SQLException
	{
		logger.info("marineCopyQuote - Enter || QuoteN0: "+QuoteNo);
		String newQuoteNo="";
		CallableStatement cstmt = null;
		Connection con = null;
		try {
		con = DBConnection.getInstance().getDBConnection();
		cstmt = con.prepareCall("call copy_quote(?)");
		cstmt.registerOutParameter(1, java.sql.Types.VARCHAR);
		cstmt.setString(1, QuoteNo);
		cstmt.execute();
		newQuoteNo=cstmt.getString(1);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
	         
              try {
	                if (cstmt != null)
		             cstmt.close();
                     } catch (Exception e) { e.printStackTrace();} 
	           try {		
	               if (con != null && !con.isClosed())
	              con.close();
  	                 } catch (Exception e) { e.printStackTrace(); }
                  }
		if(rsaissuer!=null){
			String upSql = "update position_master set application_id=? where quote_no=?";
			final String args[] = {rsaissuer,newQuoteNo};
			runner.multipleUpdation(upSql,args);
		}
		logger.info("marineCopyQuote - Exit || newQuoteNo: "+newQuoteNo);
		return 	newQuoteNo;
	}


	public String getCurrentApplicationNo(final String qno) throws BaseException
	{
		logger.info("getCurrentApplicationNo method()");
		logger.debug("- Enter");

		final String res = runner.singleSelection("select application_no from position_master where quote_no='"+qno+"'");

		logger.debug("- Exit");
		

		return res;
	}

	public static String getMaxQuote(final String productTypeId,final String loginBranch) throws BaseException
	{
		logger.info("getMaxQuote method()");
		logger.debug("- Enter");

		String current_no=null;

		final String args[] = {loginBranch,productTypeId,loginBranch};
		current_no=runner.singleSelection("select nvl(max(current_no)+1,max(start_no)) from policyno_generate where type_id=(select QUOTE_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE=? and PRODUCT_ID=?) and BRANCH_CODE=? and status='Y'",args);

		final String args1[] = {current_no,current_no,loginBranch,productTypeId,loginBranch};
		runner.multipleUpdation("update policyno_generate set current_no=?,remarks=? where type_id=(select QUOTE_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE=? and PRODUCT_ID=?) and BRANCH_CODE=? and status='Y'",args1);

		logger.debug("- Exit");
		

		return current_no;
	}

	public static int getMaximumApplicationNo(final String pid,final String loginBranch)  throws BaseException
	{
		logger.info("getMaximumApplicationNo method()");
		logger.debug("- Enter");

		String current_no="";
		int appNo=0;

		final String args[] = {pid,loginBranch,loginBranch,pid};
		current_no=runner.singleSelection("select nvl(max(current_no)+1,max(start_no)) from policyno_generate where type_id=(select APPLICATION_TYPE_ID from BRANCH_DETAIL where product_id=? and BRANCH_CODE=?) and BRANCH_CODE=? and status='Y' and product_id=?",args);

		final String args1[] = {current_no,current_no,pid,loginBranch,loginBranch,pid};
		runner.multipleUpdation("update policyno_generate set current_no=?,remarks=? where type_id=(select APPLICATION_TYPE_ID from BRANCH_DETAIL where product_id=? and BRANCH_CODE=?) and BRANCH_CODE=? and status='Y' and product_id=?",args1);
		if(current_no.length()>0 && current_no!=null && !"null".equals(current_no)){
			appNo = Integer.parseInt(current_no);
		}

		logger.debug("- Exit");
		

		return appNo;
	}
	//Marine Copy Quotes End///////////
	public String isNull(final String content)throws BaseException{
		final StringBuffer contents = new StringBuffer();
		if(content==null){
			contents.append("");
		}
		else{
			contents.append(content);
		}
		return contents.toString();
	}
}
