package com.maan.ClaimNotification.DAO;

import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Calendar;

import com.maan.services.util.runner;
import proj.date.DateFunction;

public class ClaimNotificationBean
{

	public String validatePolicyExpiryDate(String policyno)
	{
		long diff = 0;
		String[] args = new String[1];
		args[0] = policyno;
		String query = "select extract(year from expiry_date),extract(month from expiry_date),extract(day from expiry_date ) from position_master where policy_no = ? and status in ('P','p')";
		String[][] result = new String[0][0];
		String[][] serverdate = new String[0][0];
		try
		{
			result = runner.multipleSelection(query, args);
			if (result == null || result.length == 0)
			{
				return "NO_POLICY_EXIST";
			}
			/*Calendar c1 = Calendar.getInstance();
			c1.set(Integer.parseInt(result[0][0]), Integer
					.parseInt(result[0][1]), Integer.parseInt(result[0][2]));
			DateFunction df = new DateFunction();
			serverdate = getTodaysDate();
			Calendar c2 = Calendar.getInstance();
			c2.set(Integer.parseInt(serverdate[0][0]), Integer
					.parseInt(serverdate[0][1]), Integer
					.parseInt(serverdate[0][2]));
			diff = df.getDayDifference(c1, c2);
			if (diff > 0) {
				return "INVALID_POLICY";
			}*/
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "VALID";
	}

	public String[][] getTodaysDate() {
		String query = "select extract(year from sysdate+8/24) ,extract(month from sysdate+8/24),extract(day from sysdate+8/24) from dual";
		String[][] result = new String[0][0];
		try {
			result = runner.multipleSelection(query);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public String[][] getDetails(String policy_no, String user) {
		String[][] result = new String[0][0];
		String[][] branCode = new String[0][0];
		String bcode = "";
		String branchquery = "";
		String[] args = new String[1];
		if (user.length() > 0) {
			args[0] = user;
			branchquery = "select BRANCH_CODE from login_master where login_id = ?";
		} else {
			args[0] = policy_no;
			branchquery = "select BRANCH_CODE from login_master where login_id in (select login_id from position_master where policy_no = ?)";
		}
		String query = "";
		try {
			branCode = runner.multipleSelection(branchquery, args);
			if (branCode != null && branCode.length > 0) {
				bcode = branCode[0][0];
			}
			args = new String[4];
			args[0] = bcode;
			args[1] = bcode;
			args[2] = bcode;
			args[3] = policy_no;
			query = "select b.customer_id,b.customer_id ,to_char(c.effective_date,'dd-MON-yyyy'),to_char(b.expiry_date,'dd-MON-yyyy'),(SELECT transport_description from mode_of_transport where mode_transport_id=c.mode_of_transport and BRANCH_CODE = ?),(select cover_name from cover_master where cover_id=c.cover_id and BRANCH_CODE = ? ),c.currency_name,d.SETTLING_AGENT_ID,(select sale_term_name from sale_term_master where sale_term_id=c.sale_term_id and BRANCH_CODE = ?),b.application_no,b.quote_no,a.city,a.country,a.pobox,a.emirate  from personal_info a,position_master b,marine_data c,marine_policy_details d where a.customer_id=b.customer_id and b.application_no=c.application_no and b.quote_no=d.quote_no and b.policy_no = ?";

			result = runner.multipleSelection(query, args);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public String[][] getCommodityDetails(String application_no) {
		String[][] result = new String[0][0];
		String[] args = new String[1];
		String query = "select sno,fragile,package_description,sum_insured,description from marine_result_details  where application_no = ?";
		args[0] = application_no;
		try {
			result = runner.multipleSelection(query, args);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public String[][] getSettlingAgentDetails(String quote_no) {
		String[][] result = new String[0][0];
		String query = "select SETTLING_AGENT_NAME,address1,address2,address3 from settling_agent_master where SETTLING_AGENT_ID=(select SETTLING_AGENT_ID from marine_policy_details where quote_no = ?)";
		String[] args = new String[1];
		try {
			args[0] = quote_no;
			result = runner.multipleSelection(query, args);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public String insertClaim(HashMap ht) {
		String date = "";
		String day = "";
		String month = "";
		String year = "";
		String desc = "";
		String type = "";
		String policy_no = "";
		String quote_no = "";
		String application_no = "";
		int claimid = 0;
		String claimnumber = "";
		String sum_insured = "";
		String userType = "";
		String user = "";
		//String branchQuery = "";

		String query = "";
		String branCode = "";
		String branchquery = "";
		String[][] branchCode = null;
		String[] args = new String[0];

		Calendar cal = new GregorianCalendar();
		int hour12 = cal.get(Calendar.HOUR);
		int min = cal.get(Calendar.MINUTE);
		int sec = cal.get(Calendar.SECOND);
		if (hour12 == 0)
			hour12 = 1;

		day = (String) ht.get("day");
		month = (String) ht.get("month");
		year = (String) ht.get("year");
		desc = (String) ht.get("claimdesc");
		type = (String) ht.get("claimtype");
		policy_no = (String) ht.get("policy_no");
		quote_no = (String) ht.get("quote_no");
		application_no = (String) ht.get("application_no");
		user = (String) ht.get("user");
		userType = (String)ht.get("usertype");

		user = (user == null || user.equalsIgnoreCase("null") || user.equals("")) ? "" : user;
		if (user.length() > 0) {
			args = new String[1];
			args[0] = user;
			if(userType.equalsIgnoreCase("Broker")){
				branchquery = "select branch_code from broker_company_master where agency_code=(select agency_code from login_master where login_id = ?)";
			}
			else{
			   branchquery = "select branch_code from broker_company_master where agency_code=(select oa_code from login_master where login_id = ?)";
			}
		}
		else {
			args = new String[1];
			args[0] = policy_no;
			String[][] usr = runner.multipleSelection("select usertype,agency_code,oa_code  from login_master where login_id in (select login_id from position_master where policy_no = ?)",args);
			if(usr != null && usr.length>0){
				usr[0][0] = usr[0][0]==null?"":usr[0][0];
				usr[0][1] = usr[0][1]==null?"":usr[0][1];
				usr[0][2] = usr[0][2]==null?"":usr[0][2];
				if(usr[0][0].equalsIgnoreCase("Broker")){
					args = new String[1];
					args[0] = usr[0][1];
					branchquery = "select branch_code from broker_company_master where agency_code = ?";
					branCode = runner.singleSelection(branchquery,args);
				}
				else{
					args = new String[1];
					args[0] = usr[0][2];
					branchquery = "select branch_code from broker_company_master where agency_code = ?";
					branCode = runner.singleSelection(branchquery,args);
				}
			}
			//args = new String[1];
			//args[0] = policy_no;
			//branchquery = "select branch_code from broker_company_master where agency_code in (select agency_code from login_master where login_id in (select login_id from position_master where policy_no = ?))";
		}
		date = day + "-" + month + "-" + year + " " + hour12 + ":" + min + ":"+ sec;
		claimid = getClaimId(policy_no);
		claimnumber = getClaimNumber(policy_no);
		sum_insured = getSumInsured(application_no);
		String firstLogin = getFirstLoginId(policy_no);
		try {
			branchCode = runner.multipleSelection(branchquery, args);
			if (branchCode != null && branchCode.length > 0) {
				branCode = branchCode[0][0];
			}
			//System.out.println("branchquery -- > " + branchquery);
			System.out.println("Branch Code Value -->" + branCode);
			args = new String[12];
			args[0] = desc;
			args[1] = date;
			args[2] = claimid + "";
			args[3] = claimnumber;
			args[4] = policy_no;
			args[5] = sum_insured;
			args[6] = policy_no;
			args[7] = policy_no;
			args[8] = type;
			args[9] = policy_no;
			args[10] = firstLogin;
			args[11] = branCode;

			query = "insert into claim_reporting  ( CLAIM_DESCRIPTION,CLAIM_HAPPENED_DATETIME,CLAIM_ID,CLAIM_NO,CLAIM_REPORTED_DATETIME ," +
					"POLICY_NO,STATUS,SUM_INSURED,inception_date,expiry_date,CLAIM_TYPE,PRODUCT_ID,LOGIN_ID," +
					"BRANCH_CODE)  values ( ? , ? , ? , ?, ( select current_timestamp  from dual ) , ?, 'P' , ?," +
					" (select distinct to_char(inception_date,'dd-mon-yyyy') from position_master where policy_no  = ?) , " +
					"(select distinct to_char(expiry_date,'dd-mon-yyyy') from position_master where policy_no = ?), ?," +
					"(select distinct PRODUCT_ID from position_master where policy_no = ?) , ?,?)";

			runner.multipleInsertion(query, args);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return claimnumber;
	}

	public int getClaimId(String policy_no) {
		int claimid = 1;
		String res = "";
		String[] args = new String[1];
		args[0] = policy_no;
		String query = "select max(CLAIM_ID)+1 from CLAIM_REPORTING where POLICY_NO = ?";
		try {
			res = runner.singleSelection(query, args);
			if (res == null || res.equalsIgnoreCase("null")
					|| res.equalsIgnoreCase("") || res.equalsIgnoreCase("0")) {
				claimid = 1;
			} else {
				claimid = Integer.parseInt(res);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return claimid;
	}

	public String getClaimNumber(String policy_no) {
		int pid = 0;
		String res = "";
		String current_no = "";
		String[] args = new String[1];
		args[0] = policy_no;
		String query = "select PRODUCT_ID from position_master where policy_no = ?";
		try {
			res = runner.singleSelection(query, args);
			pid = Integer.parseInt(res);
			if (pid == 11) {
				args = new String[1];
				args[0] = pid + "";
				current_no = runner
						.singleSelection(
								"select nvl(max(current_no)+1,max(start_no)) from policyno_generate where product_id =  ?  and description='Claim' and type_id=17",
								args);
				args = new String[3];
				args[0] = current_no;
				args[1] = current_no;
				args[2] = pid + "";
				runner
						.multipleUpdation(
								"update policyno_generate set current_no = ? ,remarks = ? where product_id = ? and description='Claim' and type_id=17",
								args);
			}
			if (pid == 3) {
				args = new String[1];
				args[0] = pid + "";
				current_no = runner
						.singleSelection(
								"select nvl(max(current_no)+1,max(start_no)) from policyno_generate where product_id = ?  and description='Claim' and type_id = 7",
								args);

				args = new String[3];
				args[0] = current_no;
				args[1] = current_no;
				args[2] = pid + "";
				runner
						.multipleUpdation(
								"update policyno_generate set current_no = ?,remarks = ? where product_id = ? and description='Claim' and type_id=7",
								args);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return current_no;
	}

	public String getSumInsured(String application_no) {
		String[] args = new String[1];
		args[0] = application_no;
		String query = "select TOTAL_SUM_INSURED from marine_data where application_no = ?";
		int suminsured = 0;
		String res = "";
		try {
			res = runner.singleSelection(query, args);
			suminsured = Integer.parseInt(res);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return suminsured + "";
	}

	public String validateClaimOccuredDate(String day, String month,String year, String policy_no)
	{
		String[] args = new String[1];
		args[0] = policy_no;
		String query = "select extract(year from inception_date),extract(month from inception_date),extract(day from inception_date),extract(year from expiry_date),extract(month from expiry_date),extract(day from expiry_date)  from position_master where policy_no = ?";

		String[][] result = new String[0][0];
		String[][] serverdate = new String[0][0];
		long diff1 = 0;
		long diff2 = 0;
		long diff3 = 0;
		String resultstring = "";
		try
		{
			DateFunction df = new DateFunction();
			result = runner.multipleSelection(query, args);

			Calendar entereddate = Calendar.getInstance();
			Calendar startdate = Calendar.getInstance();
			Calendar enddate = Calendar.getInstance();
			Calendar servdate = Calendar.getInstance();
			serverdate = getTodaysDate();
			servdate.set(Integer.parseInt(serverdate[0][0]), Integer.parseInt(serverdate[0][1]), Integer.parseInt(serverdate[0][2]));

			if (result != null && result.length > 0)
			{
				entereddate.set(Integer.parseInt(year), getMonth(month),Integer.parseInt(day));
				startdate.set(Integer.parseInt(result[0][0]), Integer.parseInt(result[0][1]), Integer.parseInt(result[0][2]));
				enddate.set(Integer.parseInt(result[0][3]), Integer.parseInt(result[0][4]), Integer.parseInt(result[0][5]));
			}
			diff1 = df.getDayDifference(startdate, entereddate);
			//diff2 = df.getDayDifference(entereddate, enddate);
			diff3 = df.getDayDifference(entereddate, servdate);
			if (diff3 < 0)
			{
				return "GREATER_THAN_TODAYS_DATE";
			}
			if ((diff1 > 0 || diff1 == 0))
			{
				resultstring = "VALIDDATE";
			}
			else
			{
				resultstring = "INVALID_CLAIM_OCCURED_DATE";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return resultstring;
	}

	public int getMonth(String month) {
		int i = 0;
		if (month != null) {
			if (month.equalsIgnoreCase("Jan"))
				i = 1;
			if (month.equalsIgnoreCase("Feb"))
				i = 2;
			if (month.equalsIgnoreCase("Mar"))
				i = 3;
			if (month.equalsIgnoreCase("Apr"))
				i = 4;
			if (month.equalsIgnoreCase("May"))
				i = 5;
			if (month.equalsIgnoreCase("Jun"))
				i = 6;
			if (month.equalsIgnoreCase("Jul"))
				i = 7;
			if (month.equalsIgnoreCase("Aug"))
				i = 8;
			if (month.equalsIgnoreCase("Sep"))
				i = 9;
			if (month.equalsIgnoreCase("Oct"))
				i = 10;
			if (month.equalsIgnoreCase("Nov"))
				i = 11;
			if (month.equalsIgnoreCase("Dec"))
				i = 12;
		} else {
			i = 0;
		}
		return i;
	}

	public String[][] getClaimTypes(String branch) {
		String[] args = new String[1];
		args[0] = branch;
		String query = "select detail_name from constant_detail where category_id=11 and branch_code = ?";
		String[][] result = new String[0][0];
		try {
			result = runner.multipleSelection(query, args);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public String[][] getAllClaimsForPolicyNumber(String policy_no,
			String status) {
		String[] args = new String[2];
		args[0] = policy_no;
		args[1] = status;

		String query = "select CLAIM_DESCRIPTION,to_char(CLAIM_HAPPENED_DATETIME,'dd-MON-yyyy'),to_char(CLAIM_REPORTED_DATETIME,'dd-MON-yyyy'),CLAIM_ID,CLAIM_NO,POLICY_NO,RSAREMARKS,STATUS from claim_reporting where policy_no = ? and status = ? order by claim_id desc";
		String[][] result = new String[0][0];
		try {
			result = runner.multipleSelection(query, args);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public String[][] getClaimDetail(String claim_id, String policy_no) {
		String[] args = new String[2];
		args[0] = policy_no;
		args[1] = claim_id;
		String query = "select RSAREMARKS,STATUS,to_char(RSAREMARKS_DATETIME,'dd-MON-yyyy'),CLAIM_ID,POLICY_NO,CLAIM_TYPE  from CLAIM_REPORTING where POLICY_NO = ? and claim_id = ?";
		String[][] result = new String[0][0];
		try {
			result = runner.multipleSelection(query, args);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public String[][] getAllClaimPolicyNumber(String status, String product_id) {
		String[] args = new String[2];
		args[0] = status;
		args[1] = product_id;
		String query = "select distinct a.policy_no,(select count(*) from claim_reporting where policy_no = a.policy_no) from claim_reporting a where status = ? and product_id = ?";
		String[][] result = new String[0][0];
		try {
			result = runner.multipleSelection(query, args);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public String UpdateClaimStatus(String claim_id, String policy_no,
			String remarks, String status) {
		if (status != null) {
			if (status.equalsIgnoreCase("Pending"))
				status = "P";
			if (status.equalsIgnoreCase("Accepted"))
				status = "A";
			if (status.equalsIgnoreCase("Rejected"))
				status = "R";
		}
		String[] args = new String[4];
		args[0] = remarks;
		args[1] = status;
		args[2] = policy_no;
		args[3] = claim_id;
		String query = "update claim_reporting set RSAREMARKS = ? , STATUS = ? ,  RSAREMARKS_DATETIME = (select current_timestamp from dual)  where policy_no = ? and claim_id = ?";
		int up = 0;
		String result = "";
		try {
			runner.multipleUpdation(query, args);
		} catch (Exception ex) {
			ex.printStackTrace();
			result = "failure";
		}
		return result;
	}

	public String[][] getOtherSettlingAgent(String quote_no) {
		String[] args = new String[1];
		args[0] = quote_no;
		String query = "select settling_agent_name from marine_policy_details where quote_no = ?";
		String[][] result = new String[0][0];
		try {
			result = runner.multipleSelection(query, args);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public String[][] getStatusAndRemarks(String policy_no, String claim_id) {
		String[] args = new String[2];
		args[0] = policy_no;
		args[1] = claim_id;
		String query = "select STATUS,RSAREMARKS from CLAIM_REPORTING where POLICY_NO = 	? and claim_id = ?";
		String[][] result = new String[0][0];
		try {
			result = runner.multipleSelection(query, args);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public String[][] getDateAndTime(String policy_no, String number) {
		String[] args = new String[2];
		args[0] = policy_no;
		args[1] = number;

		String query = "select to_char(CLAIM_REPORTED_DATETIME, 'dd-MON-yyyy hh:mm:ss'),to_char(CLAIM_HAPPENED_DATETIME, 'dd-MON-yyyy'),CLAIM_TYPE from claim_reporting where policy_no = ? and CLAIM_NO = ?";
		String[][] result = new String[0][0];
		try {
			result = runner.multipleSelection(query, args);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public String[][] getCustomerAndBrokerName(String policy_no) {
		String[][] result = new String[0][0];
		String[] args = new String[1];
		args[0] = policy_no;
		//String query = " select nvl(a.first_name,a.company_name),c.company_name,a.login_id,b.oa_code,(select distinct OPEN_COVER_NO from position_master where policy_no = ?) from personal_info a,login_master b, broker_company_master c where a.customer_id = (select distinct customer_id  from  position_master where policy_no = ?) and a.login_id = b.login_id and b.oa_code = c.agency_code";
		String query = "select nvl(a.first_name,a.company_name),c.company_name,a.login_id,b.oa_code,d.OPEN_COVER_NO,d.OPEN_COVER_INT_STATUS  from personal_info a,login_master b, broker_company_master c,position_master d where a.customer_id = d.customer_id and a.login_id = b.login_id and b.oa_code = c.agency_code and d.policy_no = ?";
		try {
			result = runner.multipleSelection(query, args);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public String[][] getAllClaimsForPolicyNumber(String policy_no) {
		String[] args = new String[1];
		args[0] = policy_no;
		String query = "select CLAIM_DESCRIPTION,to_char(CLAIM_HAPPENED_DATETIME,'dd-MON-yyyy'),to_char(CLAIM_REPORTED_DATETIME,'dd-MON-yyyy'),CLAIM_ID,CLAIM_NO,POLICY_NO,RSAREMARKS,STATUS from claim_reporting where policy_no = ? order by claim_id desc";
		String[][] result = new String[0][0];
		try {
			result = runner.multipleSelection(query, args);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public String[][] getCustomerName(String customer_id) {
		String[] args = new String[1];
		args[0] = customer_id;
		String query = "select nvl(company_name,first_name),nvl(address1,address2) from personal_info where customer_id = ?";
		String[][] result = new String[0][0];
		try {
			result = runner.multipleSelection(query, args);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public String getStatus(String policyno) {
		String stat = "";
		String[] args = new String[2];
		args[0] = policyno;
		args[1] = policyno;
		String qryy = "select status from claim_reporting where claim_id = (select max(claim_id) from claim_reporting where policy_no = ?)  and policy_no = ?";
		try {
			stat = runner.singleSelection(qryy, args);
			stat = stat == null ? "" : stat;
			if (stat.equalsIgnoreCase("P"))
				stat = "CLAIM_ALREADY_PENDING";
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return stat;
	}

	// FOR ADMIN SCREEN
	public String getClaimNo(String policy_no, String status) {
		String claim_no = "";
		String[] args = new String[3];
		args[0] = policy_no;
		args[1] = status;
		args[2] = policy_no;
		String qryy = "select claim_no from claim_reporting where claim_id = (select max(claim_id) from claim_reporting where policy_no = ? and status = ? ) and policy_no = ?";
		try {
			claim_no = runner.singleSelection(qryy, args);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return claim_no;
	}

	public String[][] getProducts(String branch, String loginId) {
		String[][] result = new String[0][0];
		String[] args = new String[1];
		// String query = "select PRODUCT_ID,PRODUCT_NAME,COMPANY_ID from
		// product_master where status='Y' and branch_code = ? and PRODUCT_ID
		// not in(select PRODUCT_ID from home_product_master where status='Y')";
		try {
			String assignedProdId = runner
					.singleSelection("select PRODUCT_ID from login_master  where login_id = '"
							+ loginId + "'");
			args[0] = branch;
			String query = "select PRODUCT_ID,PRODUCT_NAME,COMPANY_ID from product_master where status='Y'"
					+ " and branch_code = ? and PRODUCT_ID in  ("
					+ assignedProdId + ")";
			result = runner.multipleSelection(query, args);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public int getNumberOfClaim(String policy_no, String status) {
		int result = 0;
		String res = "";
		String[] args = new String[2];
		args[0] = policy_no;
		args[1] = status;
		String query = "select count(*) from claim_reporting where policy_no = ? and status = ?";
		try {
			res = runner.singleSelection(query, args);
			if (res == null || res.equalsIgnoreCase("null")
					|| res.equalsIgnoreCase("")) {
				result = 0;
			} else {
				result = Integer.parseInt(res);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public String getClaimID(String policy_no, String status) {
		String claim_id = "";
		String[] args = new String[2];
		args[0] = policy_no;
		args[1] = status;
		String query = "select claim_id from claim_reporting where policy_no =  ?  and status = ?";
		try {
			claim_id = runner.singleSelection(query, args);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return claim_id;
	}

	public String[][] getClaimIDAndNo(String policy_no, String status) {
		String[][] result = new String[0][0];
		String[] args = new String[2];
		args[0] = policy_no;
		args[1] = status;
		String query = "select claim_id,claim_no from claim_reporting where policy_no =  ?  and status = ?";

		try {
			//claim_id = runner.singleSelection(query, args);
			result = runner.multipleSelection(query, args);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public String getFirstLoginId(String policy_no) {
		String claim_id = "";
		String[] args = new String[1];
		String query = "select login_id from position_master where policy_no = ?";
		String[][] result = new String[0][0];
		String first = "";
		try {
			result = runner.multipleSelection(query, args);
			if (result != null && result.length > 0)
				first = result[0][0];
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return first;
	}

	/** *****Branchwise**** */
	public String[][] getAllClaimPolicyNumber(String status, String product_id,String branchCode)
	{
		/*
		 * String query = "select distinct a.policy_no,(select count(*) from
		 * claim_reporting where policy_no = a.policy_no) from claim_reporting a
		 * where status = '"+status+"' and product_id = '"+product_id+"' and
		 * login_id in(select login_id from login_master where oa_code in(select
		 * agency_code from broker_company_master where branch_code
		 * in("+branchCode+")))";
		 */

		String[] args = new String[3];
		String query = "";
		String[][] result = new String[0][0];
		product_id = (product_id==null || product_id.equalsIgnoreCase("null"))?"":product_id;
		try {
			if(product_id.length() > 0){
				args[0] = status;
				args[1] = product_id;
				args[2] = branchCode;
				query = "select distinct a.policy_no,(select count(*) from claim_reporting where policy_no = a.policy_no) from claim_reporting a where status = ? and product_id = ? and branch_code = ?";
				result = runner.multipleSelection(query, args);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public StringBuffer claimUserValidation(String first,String second,String third,String fourth,String loginId,String branchCode)
	{
		StringBuffer error = new StringBuffer();
		if (first.equals("0") || second.equals("0") || third.equals("0") || fourth.equals("0")) {
			error.append("Please Enter a Valid Policy Number");
		}
		else{
			try {
				Integer.parseInt(first);
				Integer.parseInt(second);
				Integer.parseInt(third);
				Long.parseLong(fourth);
			}
			catch (Exception ex) {
				error.append("Please Enter a Valid Policy Number");
			}
		}
		if(error.length() > 0){
		     return error;
		}
		else{
			String policyNumber = first + "/" + second + "/" + third + "/"+ fourth;
			String[] args = new String[2];
			args[0] = "%"+policyNumber+"%";
			args[1] =  branchCode;
			String query = "select count(*)  from position_master where policy_no like (?) and status in ('P','p') and login_id in  (select login_id from login_master where oa_code in (select agency_code from broker_company_master where branch_code=?))";

			String result = runner.singleSelection(query,args);
			result = result==null?"":result;

			if(result.equalsIgnoreCase("") || result.equalsIgnoreCase("0") || result.equalsIgnoreCase("null")){
                 error.append("No Entry found for given policy Number "+policyNumber+"<br>");
			}
		}
		return error;
	}

	public String[][] getDetailsForPolicyNumberSearch(String policy_no,String branch) //Branch wise search
	{
			String[][] result = new String[0][0];
			String args[] = new String[2];
			String sql = "";
			try{
				args[0] = ""+policy_no+"%";
				args[1] = branch;
				sql="select pm.application_no,pm.login_id,pm.quote_no,pm.remarks,pi.first_name,pm.premium,nvl(pm.EXCESS_PREMIUM,'0'),pi.COMPANY_NAME,pi.LAST_NAME,pm.POLICY_NO,md.remarks,pm.PDF_MODIFY_CLAUSE,pm.PDF_MODIFY_WARRANTY,pm.PDF_MODIFY_EXCLUSION,pm.PDF_MODIFY_EXTRACLAUSES,pm.PRODUCT_ID,nvl(pm.open_cover_no,'0'),pm.application_id,to_char(md.INCEPTION_DATE,'dd-Mon-YYYY') from position_master pm,marine_data md,personal_info pi where pm.policy_no like ( ? ) and pm.application_no=md.application_no and pi.customer_id=pm.customer_id and lower(pm.status) in('p') and nvl(pm.open_cover_int_status,'0') not in ('LINKED') and pm.login_id in(select login_id from login_master where oa_code in(select agency_code from broker_company_master where branch_code=?)) order by pm.quote_no desc";
				result = runner.multipleSelection(sql,args);

				//if(result == null || result.length == 0){
				//	select (a.policy_no),a.login_id,nvl(a.open_cover_no,'0'),sum(a.premium),sum(a.excess_premium) from position_master a where lower(a.status) in('p') and nvl(a.open_cover_int_status,'0') in ('LINKED')  and a.policy_no like ? and a.login_id in(select login_id from login_master where oa_code in(select agency_code from broker_company_master where branch_code=?)) group by a.policy_no,a.login_id,a.open_cover_no order by a.policy_no

				//}
			}
			catch(Exception e){
				e.printStackTrace();
			}
			return result;
	}
	public String[][]  getBrokerNameByUserId123(String cid)
	{
		String[][] brokerName = new String[0][0];
		String sql= "";
		String args[] = new String[1];
		try
		{
			args[0] = cid;
			sql = "select a.first_name,b.company_name,c.usertype,a.application_id from personal_info a,broker_company_master b,login_master c where a.login_id=(select login_id from login_master where oa_code=(select oa_code from login_master where login_id=?) and usertype='Broker') and (a.application_id='2' or (a.application_id='1' ))  and b.agency_code=c.agency_code and a.customer_id=b.customer_id";
			brokerName = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("Exception in "+e.toString());
			e.printStackTrace();
		}
		return brokerName;
	}

	public String[][] getPortfolioByDateMultiSearch(String policyNo,String admBranch)
	{
		String rersult[][] = new String[0][0];
		String sqlMultiple = "";
		String args[] = new String[2];
		try
		{
			args[0] = policyNo;
			args[1] = admBranch;
			sqlMultiple = "select (a.policy_no),a.login_id,nvl(a.open_cover_no,'0'),sum(a.premium),sum(a.excess_premium) from position_master a where lower(a.status) in('p') and nvl(a.open_cover_int_status,'0') in ('LINKED')  and a.policy_no = ? and a.login_id in(select login_id from login_master where oa_code in(select agency_code from broker_company_master where branch_code=?)) group by a.policy_no,a.login_id,a.open_cover_no order by a.policy_no";
			rersult = runner.multipleSelection(sqlMultiple,args);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return rersult;
	}

	public String getProductForPolNumber(String policyNumber){
		String pid = "";
		try{
               String query = "Select product_id from position_master where policy_no = '"+policyNumber+"'";
               pid = runner.singleSelection(query);
		}
		catch(Exception ex){
				ex.printStackTrace();
		}
		return pid;
	}

	public String getEmailIdForPolicy(String policyNumber){
		String emailId = "";
		String query = "select EMAIL from PERSONAL_INFO where CUSTOMER_ID in (select customer_id from position_master where policy_no = '"+policyNumber+"')";
		try{
			emailId = new runner().singleSelection(query);
			emailId = emailId==null?"":emailId;
		}
		catch(Exception ex){
                ex.printStackTrace();
		}
		return emailId;
	}

	public HashMap getDetailsForUpdate(String policyNumber,String claimNumber){
		HashMap ht = new HashMap();
		String[] args = new String[2];
		try{
			args[0] = policyNumber;
			args[1] = claimNumber;
			String query = " select extract(day from CLAIM_HAPPENED_DATETIME),to_char(CLAIM_HAPPENED_DATETIME,'mon'), "
						  +" extract(year from CLAIM_HAPPENED_DATETIME),claim_type,CLAIM_DESCRIPTION from claim_reporting where policy_no = ?"
						  +" and CLAIM_NO = ?";

			String[][] result = runner.multipleSelection(query,args);
			if(result != null && result.length >0){
				ht.put("day",result[0][0]==null?"":result[0][0]);
				ht.put("month",result[0][1]==null?"":result[0][1]);
				ht.put("year",result[0][2]==null?"":result[0][2]);
				ht.put("claimdesc",result[0][4]==null?"":result[0][4]);
				ht.put("claimtype",result[0][3]==null?"":result[0][3]);
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return ht;
	}

	public String UpdateClaim(HashMap ht , String policyNumber,String claimNumber){
		Calendar cal = new GregorianCalendar();
		int hour12 = cal.get(Calendar.HOUR);
		int min = cal.get(Calendar.MINUTE);
		int sec = cal.get(Calendar.SECOND);
		if (hour12 == 0)
			hour12 = 1;
		String[] args = new String[5];

		/*
		 *  HashMap ht = new HashMap();
        if(day != null)
             ht.put("day",day);
        if(month != null)
             ht.put("month",month);
        if(year != null)
             ht.put("year",year);
        if(claimdesc != null)
             ht.put("claimdesc",claimdesc);
        if(claimtype != null)
             ht.put("claimtype",claimtype);
        if(policy_no != null)
             ht.put("policy_no",policy_no);
        if(quote_no != null)
             ht.put("quote_no",quote_no);
        if(application_no != null)
             ht.put("application_no",application_no);
        if(user != null)
            ht.put("user",user);
        if(usertype != null)
        	ht.put("usertype",usertype);
		 */
		args[0] = (String)ht.get("day") +"-" +(String)ht.get("month") +"-" +(String)ht.get("year") + " " + hour12 + ":" + min + ":"+ sec;
		args[1] = (String)ht.get("claimdesc");
		args[2] = (String)ht.get("claimtype");
		args[3] = (String)ht.get("policy_no");
		args[4] =  claimNumber;
		try{
			String query =  " update CLAIM_REPORTING set CLAIM_HAPPENED_DATETIME = ?, CLAIM_DESCRIPTION = ?, claim_type = ? "
						   +" where  policy_no = ? and CLAIM_NO = ? ";

			runner.multipleUpdation(query,args);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
        return claimNumber;
	}


} // Class