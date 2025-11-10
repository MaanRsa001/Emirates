package com.maan.admin.DAO;

import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.StringTokenizer;

import proj.date.DateFunction;

import com.maan.common.error.ErrorInfo;
import com.maan.services.util.runner;

public class AdminBean extends ErrorInfo {
	private PrintWriter out = null;

	private String title = "";

	private String firstName = "";

	private String gender = "";

	private String lastName = "";

	private String nationality = "";

	private String brokerDate = "";

	private String telephone = "";

	private String mobile = "";

	private String fax = "";

	private String email = "";

	private String address1 = "";

	private String address2 = "";

	private String occupation = "";

	private String emirate = "";

	private String country = "";

	private String poBox = "";

	private String brokerCompanyName = "";

	private String brokerId = "";

	private String password = "";

	private String retypePassword = "";

	private String brokerIds = "";

	private String broktype = null;

	private int i = 0;

	private String mode = "";

	private String AgencyCode = "";

	private String OACode = "";

	private String loginPersonId = "";

	private String dobDay = "";

	private String dobMonth = "";

	private String dobYear = "";

	private String dobDay1 = "";

	private String dobMonth1 = "";

	private String dobYear1 = "";

	private String rep = "";

	private String company = "";

	private String data1 = "";

	private String data2 = "";

	String policyno = "", inceptiondate = "", modeoftransport = "",
			transportdescription = "";

	private String currencyType = "";

	private String exchangeRate = "";

	private String effectDate = "";

	private String disStatus = "";

	private String userType = "";

	private String error = "";
	
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	String[][] brokerLoginIds = new String[0][0];

	public void setBrokerLoginIds(String[][] brokerLoginIds) {
		this.brokerLoginIds = brokerLoginIds;
	}

	public String[][] getBrokerLoginIds() {
		return brokerLoginIds;
	}

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

	public String getCurrencyType() {
		return currencyType;
	}

	public void setData3(String broktype) {
		this.broktype = broktype;
	}

	public String getData3() {
		return broktype;
	}

	public void setExchangeRate(String exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public String getExchangeRate() {
		return exchangeRate;
	}

	public void setDobDay(String dobDay) {
		this.dobDay = dobDay;
	}

	public void setDobMonth(String dobMonth) {
		this.dobMonth = dobMonth;
	}

	public void setDobYear(String dobYear) {
		this.dobYear = dobYear;
	}

	public String getDobDay() {
		return dobDay;
	}

	public String getDobMonth() {
		return dobMonth;
	}

	public String getDobYear() {
		return dobYear;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public void setRep(String rep) {
		this.rep = rep;
	}

	public void setDobDay1(String dobDay1) {
		this.dobDay1 = dobDay1;
	}

	public void setDobMonth1(String dobMonth1) {
		this.dobMonth1 = dobMonth1;
	}

	public void setDobYear1(String dobYear1) {
		this.dobYear1 = dobYear1;
	}

	public String getDobDay1() {
		return dobDay1;
	}

	public String getDobMonth1() {
		return dobMonth1;
	}

	public String getDobYear1() {
		return dobYear1;
	}

	public String getCompany() {
		return company;
	}

	public String getData1() {
		return data1;
	}

	public String getData2() {
		return data2;
	}

	public String getRep() {
		return rep;
	}

	public void setEffectDate(String effectDate) {
		this.effectDate = effectDate;
	}

	public String getEffectDate() {
		return effectDate;
	}

	public void setData1(String data1) {
		this.data1 = data1;
	}

	public void setData2(String data2) {
		this.data2 = data2;
	}

	public void setLoginPersonId(String loginPersonId) {
		this.loginPersonId = loginPersonId;
	}

	public String getLoginPersonId() {
		return loginPersonId;
	}

	public void setDisStatus(String disStatus) {
		this.disStatus = disStatus;
	}

	public String getDisStatus() {
		return disStatus;
	}

	public String[][] getCustomerData(String index) {
		String[][] CustomerData = new String[0][0];
		String sql = "";
		String args[] = new String[1];
		try {
			args[0] = index.toUpperCase() + "%";
			sql = "select a.CUSTOMER_ID,nvl(a.FIRST_NAME||''||a.last_name,a.company_name),a.ADDRESS1,nvl(a.TELEPHONE,a.mobile),a.ADDRESS2,c.company_name,a.pobox,a.emirate,a.country from personal_info a,login_master b, broker_company_master c where a.login_id = b.login_id and b.oa_code = c.agency_code and upper(a.first_name) like ? and application_id='1' order by a.FIRST_NAME||a.company_name";

			CustomerData = runner.multipleSelection(sql, args);
		} catch (Exception e) {
			System.out
					.println("Exception in getting customer infoooooooooooooooo"
							+ e.toString());
			e.printStackTrace();
		}
		return CustomerData;
	}

	public String[][] getCustomerData(String index, String adminBranch) {
		String args[] = new String[1];
		String[][] CustomerData = new String[0][0];
		String sql = "";

		try {
			args[0] = index.toUpperCase() + "%";
			sql = "select a.CUSTOMER_ID,nvl(a.FIRST_NAME||''||a.last_name,a.company_name),a.ADDRESS1,nvl(a.TELEPHONE,a.mobile),a.ADDRESS2,c.company_name,a.pobox,a.emirate,a.country from personal_info a,login_master b, broker_company_master c where a.login_id = b.login_id and b.oa_code = c.agency_code and upper(a.first_name) like ? and application_id='1' and c.BRANCH_CODE in("
					+ adminBranch + ") order by a.FIRST_NAME||a.company_name";

			CustomerData = runner.multipleSelection(sql, args);
		} catch (Exception e) {
			System.out.println("Exception in getting customer infooo"
					+ e.toString());
			e.printStackTrace();
		}
		return CustomerData;
	}

	public String[][] getQuoteData(String index) {
		String[][] CustomerData = new String[0][0];
		String sql = "";
		String args[] = new String[1];

		try {
			args[0] = index;
			sql = "select a.First_Name,b.quote_no,c.product_name,b.PREMIUM,b.entry_date from personal_info a,position_master b,product_master c where a.customer_id=b.customer_id and b.product_id=c.product_id and b.quote_no=?";

			CustomerData = runner.multipleSelection(sql);
		} catch (Exception e) {
			System.out.println("Exception in getting customer info"
					+ e.toString());
			e.printStackTrace();
		}
		return CustomerData;
	}

	public String[][] getByProducts(String sdate, String edate) {
		String args[] = new String[2];
		String[][] CustomerData = new String[0][0];
		String sql = "";

		try {
			args[0] = sdate;
			args[1] = edate;
			sql = "select product_id,sum(premium),count(policy_no) from position_master where inception_date between to_date(?,'dd-mm-yyyy') and to_date(?,'dd-mm-yyyy')+1 and  policy_no is not null and  premium is not null and  status='P' group by product_id";
			CustomerData = runner.multipleSelection(sql, args);
		} catch (Exception e) {
			System.out.println("Exception in getting customer info"
					+ e.toString());
			e.printStackTrace();
		}
		return CustomerData;
	}

	public String getProName(String pid) {
		String pname = "";
		String sql = "";
		String args[] = new String[1];
		try {
			args[0] = pid;
			sql = "select product_name from product_master where product_id='"
					+ pid + "'";
			pname = runner.singleSelection(sql, args);
		} catch (Exception e) {
			System.out.println("Exception in getting pname" + e.toString());
			e.printStackTrace();
		}
		return pname;
	}

	public String[][] getByBrokers(String sdate, String edate, String pid) {
		String[][] CustomerData = new String[0][0];
		String sql = "";
		try {
			sql = "select b.created_by,count(a.policy_no),sum(a.premium) from position_master a,login_master b where  b.created_by in(select created_by from login_master where login_id in (select distinct login_id from position_master) and created_by is not null and lower(created_by) not in('admin'))and a.login_id=b.login_id and a.inception_date between to_date('"
					+ sdate
					+ "','dd-mm-yyyy') and to_date('"
					+ edate
					+ "','dd-mm-yyyy')+1 and a.status='P' and a.product_id in("
					+ pid
					+ ") and policy_no is not null and  premium is not null group by b.created_by";

			CustomerData = runner.multipleSelection(sql);
		} catch (Exception e) {
			System.out.println("Exception in getting customer infoo"
					+ e.toString());
			e.printStackTrace();
		}
		return CustomerData;
	}

	public String[][] getUserBrokerInformation(String login) {
		String[][] UserBroker = new String[0][0];
		String sql = "";
		String args[] = new String[1];
		try {
			args[0] = login;
			sql = "select application_id,login_id,agency_code,oa_Code,first_name from personal_info where oa_code=(select oa_code from login_master where login_id=?) and (login_id!='NONE' or application_id='4')";
			UserBroker = runner.multipleSelection(sql);
		} catch (Exception e22) {
			System.out.println("Error " + e22);
			e22.printStackTrace();
		}
		return UserBroker;
	}

	public String[][] getUsersByBrokers(String sdate, String edate, String pid) {
		String[][] CustomerData = new String[0][0];
		String sql = "";
		String args[] = new String[2];
		try {
			args[0] = sdate;
			args[1] = edate;
			sql = "select login_id,count(policy_no),sum(premium) from position_master where login_id in(select login_id from login_master where lower(created_by)='admin') and inception_date between to_date(?,'dd-mm-yyyy') and to_date(?,'dd-mm-yyyy')+1 and status='P' and  product_id in("
					+ pid
					+ ") and policy_no is not null and  premium is not null  group by login_id";

			CustomerData = runner.multipleSelection(sql, args);
		} catch (Exception e) {
			System.out.println("Exception in customer Info" + e.toString());
			e.printStackTrace();
		}
		return CustomerData;
	}

	public String[][] getByUsers(String brokerid, String sdate, String edate,
			String pid) {
		String[][] CustomerData = new String[0][0];
		String sql = "";
		String args[] = new String[3];
		try {
			args[0] = brokerid;
			args[1] = sdate;
			args[3] = edate;
			sql = "select  login_id,sum(premium),count(policy_no) from position_master where login_id in(select login_id from login_master where oa_code=?) and inception_date between to_date(?,'dd-mm-yyyy') and to_date(?,'dd-mm-yyyy')+1 and status='P' and product_id in("
					+ pid
					+ ") and policy_no is not null and  premium is not null group by login_id";

			CustomerData = runner.multipleSelection(sql, args);
		} catch (Exception e) {
			System.out.println("Exception in getting customer info "
					+ e.toString());
			e.printStackTrace();
		}
		return CustomerData;
	}

	public String[][] getCustomers(String userid, String sdate, String edate,
			String pid) {
		String[][] CustomerData = new String[0][0];
		String sql = "";
		String args[] = new String[3];
		try {
			args[0] = userid;
			args[1] = sdate;
			args[2] = edate;
			sql = "select a.first_name,b.policy_no,to_char(b.inception_date,'dd-mm-yyyy'),to_char(b.expiry_date,'dd-mm-yyyy'),c.total_sum_insured,b.premium from personal_info a,position_master b,marine_result c where b.quote_no=c.quote_no and a.customer_id=b.customer_id and b.login_id=? and b.premium is not null and b.policy_no is not null and b.inception_date between to_date(?,'dd-mm-yyyy') and to_date(?,'dd-mm-yyyy')+1 and b.status='P'  and b.product_id in("
					+ pid + ") and b.policy_no is not null";

			CustomerData = runner.multipleSelection(sql, args);
		} catch (Exception e) {
			System.out
					.println("Exception in getting customer infoooooooooooooooo"
							+ e.toString());
			e.printStackTrace();
		}
		return CustomerData;
	}

	public String getBrokerName(String brokerid) {
		String sql = "";
		String bname = "";
		String args[] = new String[1];
		try {
			args[0] = brokerid;
			sql = "select first_name from personal_info where login_id=? and application_id='2'";
			bname = runner.singleSelection(sql, args);
		} catch (Exception e) {
			System.out.println("Exception in getting pname" + e.toString());
			e.printStackTrace();
		}
		return bname;
	}

	public String getBrokerNameByLoginID(String loginid) {
		String pname = "";
		String sql = "";
		String args[] = new String[1];
		try {
			args[0] = loginid;
			sql = "select first_name from personal_info where agency_code=(select oa_Code from login_master where login_id=?) and application_id='2'";
			pname = runner.singleSelection(sql, args);
		} catch (Exception e) {
			System.out.println("Exception in getting pname" + e.toString());
			e.printStackTrace();
		}
		return pname;
	}

	public String getAEName(String brokerid) {
		String pname = "";
		String sql = "";
		String args[] = new String[1];
		try {
			args[0] = brokerid;
			sql = "select first_name from personal_info where login_id=? and application_id='4'";
			pname = runner.singleSelection(sql, args);
		} catch (Exception e) {
			System.out.println("Exception in getting pname" + e.toString());
			e.printStackTrace();
		}
		return pname;
	}

	public String getCustomerName(String brokerid) {
		String pname = "";
		String sql = "";
		String args[] = new String[1];
		try {
			args[0] = brokerid;
			sql = "select first_name from personal_info where login_id=? and application_id='3'";
			pname = runner.singleSelection(sql, args);
		} catch (Exception e) {
			System.out.println("Exception in getting pname" + e.toString());
			e.printStackTrace();
		}
		return pname;
	}

	public String[][] getAdminReferal() {
		String loginids = "";
		String[][] ss = new String[0][0];
		String status123 = "";
		try {
			String sql = "select to_char(a.entry_date,'YYYY-MM-DD'),count(a.entry_date) from position_master a,marine_data b,personal_info pi where a.REMARKS in ('Normal','NORMAL_EXCESS_PRICE','Admin') and a.status='Y' and  a.application_no=b.application_no and pi.customer_id=a.customer_id and b.ADMIN_REFERRAL_STATUS='Y'  group by to_char(a.entry_date,'YYYY-MM-DD') order by to_char(a.entry_date,'YYYY-MM-DD') desc";
			ss = runner.multipleSelection(sql);
		} catch (Exception e) {
			System.out.println("Exception in gettin refferal data"
					+ e.toString());
			e.printStackTrace();
		}
		return ss;
	}

	// Used
	public String[][] getReferal(String pid, String branchCode,
			String brokerCodes, String freightStatus) // broker_codes
	// restriction
	{
		String pname = "";
		String loginids = "";
		String[][] ss = new String[0][0];
		String status123 = "";

		String syntax = "";
		if (brokerCodes.equalsIgnoreCase("") || brokerCodes.length() == 0) {
			syntax = "select agency_code from broker_company_master where branch_code in("
					+ branchCode + ")";
		} else {
			brokerCodes = brokerCodes.replaceAll(",", "','");
			syntax = "'" + brokerCodes + "'";
		}
		String freightCheck = "";
		if (freightStatus.equalsIgnoreCase("Yes")) {
			freightCheck = "and a.FREIGHT_STATUS='F'";
		} else {
			freightCheck = "and a.FREIGHT_STATUS is null";
		}
		try {
			if(!pid.equalsIgnoreCase("All")){
			String sql = "select to_char(a.entry_date,'YYYY-MM-DD'),count(a.entry_date) from position_master a,marine_data b where ((a.REMARKS in ('Referal') and b.ADMIN_REFERRAL_STATUS='N') or (a.REMARKS in ('Admin','Normal','NORMAL_EXCESS_PRICE') and b.ADMIN_REFERRAL_STATUS='Y')) and a.status in ('Y','E') and a.product_id in("
					+ pid
					+ ") and  a.application_no=b.application_no and a.login_id in(select login_id from login_master where oa_code in("
					+ syntax
					+ ")) "
					+ freightCheck
					+ " group by to_char(a.entry_date,'YYYY-MM-DD') order by to_char(a.entry_date,'YYYY-MM-DD') desc";
			ss = runner.multipleSelection(sql);
			}
		} catch (Exception e) {
			System.out.println("Exception in gettin refferal data"
					+ e.toString());
			e.printStackTrace();
		}
		return ss;
	}

	// Used
	public String[][] getApproved(String pid, String branchCode,
			String brokerCodes, String freightStatus) {
		String pname = "";
		String loginids = "";
		String[][] ss = new String[0][0];
		String status123 = "";

		String syntax = "";
		if (brokerCodes.equalsIgnoreCase("") || brokerCodes.length() == 0) {
			syntax = "select agency_code from broker_company_master where branch_code in("
					+ branchCode + ")";
		} else {
			brokerCodes = brokerCodes.replaceAll(",", "','");
			syntax = "'" + brokerCodes + "'";
		}
		String freightCheck = "";
		if (freightStatus.equalsIgnoreCase("Yes")) {
			freightCheck = "and a.FREIGHT_STATUS='F'";
		} else {
			freightCheck = "and a.FREIGHT_STATUS is null";
		}
		try {
			String sql = "select to_char(a.entry_date,'YYYY-MM-DD'),count(a.entry_date),sum(nvl(b.premium,'0')+nvl(b.excess_premium,'0')) from position_master a,marine_data b where a.REMARKS in ('Admin','NORMAL_EXCESS_PRICE') and a.application_no=b.application_no and b.ADMIN_REFERRAL_STATUS='N' and a.product_id in ("
					+ pid
					+ ") and a.status  in('Y','E') and a.login_id in(select login_id from login_master where oa_code in("
					+ syntax
					+ ")) "
					+ freightCheck
					+ " group by to_char(a.entry_date,'YYYY-MM-DD') order by to_char(a.entry_date,'YYYY-MM-DD') desc";
			ss = runner.multipleSelection(sql);
		} catch (Exception e) {
			System.out.println("Exception in gettin refferal data"
					+ e.toString());
			e.printStackTrace();
		}
		return ss;
	}

	// Used
	public String[][] getPortFolio(String sdate, String edate, String status,
			String pid, String branchCode, String brokerCodes,
			String freightStatus) // Approved_Policy_PortFoolio.jsp
	{
		String pname = "";
		String loginids = "";
		String addeddata = "";
		String syntax = "";
		if (pid.equals("all")) {
			addeddata = "";
		} else {
			// addeddata="and a.product_id='"+pid+"'";
			addeddata = "and  a.product_id in (" + pid + ")";
		}
		String freightCheck = "";

		if (freightStatus.equalsIgnoreCase("Yes")) {
			freightCheck = "and a.FREIGHT_STATUS='F'";
		} else if (pid.equals("all")) {
			freightCheck = "";
		} else if (pid.indexOf(",") != -1) {
			freightCheck = "";
		} else {
			freightCheck = "and a.FREIGHT_STATUS is null";
		}

		if (brokerCodes.equalsIgnoreCase("") || brokerCodes.length() == 0) {
			syntax = "select agency_code from broker_company_master where branch_code in("
					+ branchCode + ")";
		} else {
			brokerCodes = brokerCodes.replaceAll(",", "','");
			syntax = "'" + brokerCodes + "'";
		}
		String[][] ss = new String[0][0];
		String status123 = "";
		String sql = "";
		try {
			if (status.equalsIgnoreCase("p")) {
				sql = "select to_char(a.inception_date,'YYYY-MM-DD'),count(a.inception_date) from position_master a,marine_data b,personal_info c where  a.inception_date between to_date('"
						+ sdate
						+ "','dd-mm-yyyy') and to_date('"
						+ edate
						+ "','dd-mm-yyyy')+1  and   a.customer_id=c.customer_id and a.application_no=b.application_no   "
						+ addeddata
						+ "  and   lower(a.status) in('"
						+ status.toLowerCase()
						+ "') and a.login_id in(select login_id from login_master where oa_code in("
						+ syntax
						+ ")) "
						+ freightCheck
						+ " group by to_char(a.inception_date,'YYYY-MM-DD') order by to_char(a.inception_date,'YYYY-MM-DD') desc";
			} else if (status.equalsIgnoreCase("r")) {
				sql = "select to_char(a.entry_date,'YYYY-MM-DD'),count(a.entry_date) from position_master a,marine_data b,personal_info c where  a.entry_date between to_date('"
						+ sdate
						+ "','dd-mm-yyyy') and to_date('"
						+ edate
						+ "','dd-mm-yyyy')+1 and a.customer_id=c.customer_id and a.application_no=b.application_no  "
						+ addeddata
						+ " and lower(a.status) in('y') and a.login_id in(select login_id from login_master where oa_code in("
						+ syntax
						+ ")) "
						+ freightCheck
						+ " group by to_char(a.entry_date,'YYYY-MM-DD') order by to_char(a.entry_date,'YYYY-MM-DD') desc";
			} else if (status.equalsIgnoreCase("A")) {
				sql = "select to_char(a.inception_date,'YYYY-MM-DD'),count(a.inception_date) from position_master a,marine_data b,personal_info c where  a.inception_date between to_date('"
						+ sdate
						+ "','dd-mm-yyyy') and to_date('"
						+ edate
						+ "','dd-mm-yyyy')+1  and   a.customer_id=c.customer_id and a.application_no=b.application_no   "
						+ addeddata
						+ "  and   lower(a.status) in('p') and a.login_id in(select login_id from login_master where oa_code in("
						+ syntax
						+ ")) "
						+ freightCheck
						+ " group by to_char(a.inception_date,'YYYY-MM-DD') order by to_char(a.inception_date,'YYYY-MM-DD') desc";
			} else {
				sql = "select to_char(a.entry_date,'YYYY-MM-DD'),count(a.entry_date) from position_master a,marine_data b,personal_info c where  a.entry_date between to_date('"
						+ sdate
						+ "','dd-mm-yyyy') and to_date('"
						+ edate
						+ "','dd-mm-yyyy')+1 and a.remarks in ('Normal') and (b.ADMIN_REFERRAL_STATUS in('N') or b.ADMIN_REFERRAL_STATUS is null) and a.customer_id=c.customer_id and a.application_no=b.application_no  "
						+ addeddata
						+ " and lower(a.status) in('y') and a.login_id in(select login_id from login_master where oa_code in("
						+ syntax
						+ ")) "
						+ freightCheck
						+ " group by to_char(a.entry_date,'YYYY-MM-DD') order by to_char(a.entry_date,'YYYY-MM-DD') desc";
			}
			ss = runner.multipleSelection(sql);
		} catch (Exception e) {
			System.out.println("Exception in getPortFolio data" + e.toString());
			e.printStackTrace();
		}
		return ss;
	}

	public String[][] getPortFolioForSave(String sdate, String edate,
			String status, String pid, String branchCode, String brokerCodes,
			String freightStatus) {
		String pname = "";
		String loginids = "";
		String addeddata = "";
		String syntax = "";
		if (pid.equals("all")) {
			addeddata = "";
		} else {
			// addeddata="and a.product_id='"+pid+"'";
			addeddata = "and  pm.product_id in (" + pid + ")";
		}
		String freightCheck = "";

		if (freightStatus.equalsIgnoreCase("Yes")) {
			freightCheck = "and pm.FREIGHT_STATUS='F'";
		} else if (pid.equals("all")) {
			freightCheck = "";
		} else if (pid.indexOf(",") != -1) {
			freightCheck = "";
		} else {
			freightCheck = "and pm.FREIGHT_STATUS is null";
		}

		if (brokerCodes.equalsIgnoreCase("") || brokerCodes.length() == 0) {
			syntax = "select agency_code from broker_company_master where branch_code in("
					+ branchCode + ")";
		} else {
			brokerCodes = brokerCodes.replaceAll(",", "','");
			syntax = "'" + brokerCodes + "'";
		}
		String[][] ss = new String[0][0];
		String status123 = "";
		String sql = "";
		try {
			sql = "select pm.application_no,pm.login_id,pm.quote_no,pm.remarks,pi.first_name,md.premium,nvl(md.EXCESS_PREMIUM,'0'),pi.COMPANY_NAME,pi.LAST_NAME,pm.quote_no,md.remarks,pm.PDF_MODIFY_CLAUSE,pm.PDF_MODIFY_WARRANTY,pm.PDF_MODIFY_EXCLUSION,pm.PDF_MODIFY_EXTRACLAUSES,pm.PRODUCT_ID,Policy_Cancel_Status,nvl(pm.open_cover_no,'0'),pm.application_id,nvl(md.ADMIN_REFERRAL_STATUS,'N'),to_char(pm.entry_date,'dd-mm-yyyy') from position_master pm,marine_data md,personal_info pi where pm.entry_date between to_date('"
					+ sdate
					+ "','dd-mm-yyyy') and to_date('"
					+ edate
					+ "','dd-mm-yyyy')+1 and pm.application_no=md.application_no and pi.customer_id=pm.customer_id "
					+ addeddata
					+ " and lower(pm.status) in('y') and nvl(pm.open_cover_int_status,'0') not in ('LINKED')  and pm.login_id in(select login_id from login_master where oa_code in("
					+ syntax
					+ ")) "
					+ freightCheck
					+ "  order by pm.entry_date desc";

			ss = runner.multipleSelection(sql);
		} catch (Exception e) {
			System.out.println("Exception in getPortFolio data" + e.toString());
			e.printStackTrace();
		}
		return ss;
	}

	public String[][] getPortFolioForAudit(String sdate, String edate,
			String status, String pid, String branchCode, String brokerCodes,
			String freightStatus) {
		String pname = "";
		String loginids = "";
		String addeddata = "";
		String syntax = "";
		if (pid.equals("all")) {
			addeddata = "";
		} else {
			// addeddata="and a.product_id='"+pid+"'";
			addeddata = "and  pm.product_id in (" + pid + ")";
		}
		String freightCheck = "";

		if (freightStatus.equalsIgnoreCase("Yes")) {
			freightCheck = "and pm.FREIGHT_STATUS='F'";
		} else if (pid.equals("all")) {
			freightCheck = "";
		} else if (pid.indexOf(",") != -1) {
			freightCheck = "";
		} else {
			freightCheck = "and pm.FREIGHT_STATUS is null";
		}

		if (brokerCodes.equalsIgnoreCase("") || brokerCodes.length() == 0) {
			syntax = "select agency_code from broker_company_master where branch_code in("
					+ branchCode + ")";
		} else {
			brokerCodes = brokerCodes.replaceAll(",", "','");
			syntax = "'" + brokerCodes + "'";
		}
		String[][] ss = new String[0][0];
		String status123 = "";
		String sql = "";
		try {
			sql = "select pm.application_no,pm.login_id,pm.quote_no,pm.remarks,pi.first_name,pm.premium,nvl(pm.EXCESS_PREMIUM,'0'),pi.COMPANY_NAME,pi.LAST_NAME,pm.POLICY_NO,md.remarks,pm.PRODUCT_ID,pm.Policy_Cancel_Status,nvl(pm.open_cover_no,'0'),pm.application_id,nvl(md.ADMIN_REFERRAL_STATUS,'N'),to_char(pm.entry_date,'dd-mm-yyyy'),oc.commodity_name,mc.description,mc.agreed_status,to_char(mc.INCEPTION_DATE,'dd-mm-yyyy') from position_master pm,marine_data md,personal_info pi,commoditymaster oc,marine_result_details mc where pm.inception_date between to_date('"
					+ sdate
					+ "','DD-MM-YYYY') and to_date('"
					+ edate
					+ "','DD-MM-YYYY')+1 and pm.application_no=md.application_no and pi.customer_id=pm.customer_id "
					+ addeddata
					+ " and lower(pm.status) in('p') and pm.login_id in(select login_id from login_master where oa_code in("
					+ syntax
					+ ")) and mc.commodity_id = oc.commodity_id and oc.amend_id = (select max(pc.amend_id) from commoditymaster pc where mc.commodity_id = pc.commodity_id and pc.BRANCH_CODE in("
					+ branchCode
					+ ")) and oc.BRANCH_CODE in("
					+ branchCode
					+ ") and pm.application_no=mc.application_no "
					+ freightCheck + " order by pm.quote_no desc";
			System.out
					.println("getPortFolioForAudit......commditity audit report for excel sql......"
							+ sql);
			// Trying query for max amend id
			// select pm.quote_no,pm.policy_no,pm.login_id,oc.BRANCH_CODE from
			// position_master pm,marine_data md,personal_info
			// pi,commoditymaster oc,marine_result_details mc where
			// pm.inception_date between to_date('12-11-2008','DD-MM-YYYY') and
			// to_date('12-11-2008','DD-MM-YYYY')+1 and
			// pm.application_no=md.application_no and
			// mc.application_no=md.application_no and
			// pi.customer_id=pm.customer_id and pm.product_id in (3,11) and
			// lower(pm.status) in('p') and mc.commodity_id = oc.commodity_id
			// and pm.application_no=mc.application_no and pm.FREIGHT_STATUS is
			// null and (oc.amend_id||mc.application_no) in (select
			// max(pc.amend_id)||mrd.application_no from commoditymaster
			// pc,marine_result_details mrd,position_master pos where
			// mrd.commodity_id = pc.commodity_id and
			// mrd.application_no=pos.application_no and pos.inception_date
			// between to_date('12-11-2008','DD-MM-YYYY') and
			// to_date('12-11-2008','DD-MM-YYYY')+1 and pos.status='P' and
			// pc.BRANCH_CODE='020' group by mrd.application_no) and
			// oc.BRANCH_CODE='020' order by pm.quote_no desc
			ss = runner.multipleSelection(sql);
		} catch (Exception e) {
			System.out.println("Exception in getPortFolio data" + e.toString());
			e.printStackTrace();
		}
		return ss;
	}

	public String getOpenCoverDescription(String quoteNo, String bra) {
		String ss = "";
		String sql = "";
		String args[] = new String[3];
		try {
			args[0] = quoteNo;
			args[1] = quoteNo;
			args[2] = quoteNo;
			sql = "select  occm.COMMODITY_NAME_DESC from commoditymaster oc, marine_result_details mc,OPEN_COVER_COMMODITY_MASTER occm,position_master pos where mc.commodity_id = oc.commodity_id and occm.commodity_id = oc.commodity_id and oc.amend_id = (select max(pc.amend_id) from commoditymaster pc where mc.commodity_id = pc.commodity_id ) and pos.quote_no = ? and occm.AMEND_ID=(select max(AMEND_ID) from OPEN_COVER_COMMODITY_MASTER where PROPOSAL_NO=(select PROPOSAL_NO from OPEN_COVER_POSITION_MASTER where OPEN_COVER_NO=(select OPEN_COVER_NO from position_master where quote_no = ?))) and occm.PROPOSAL_NO=(select PROPOSAL_NO from OPEN_COVER_POSITION_MASTER where OPEN_COVER_NO=(select OPEN_COVER_NO from position_master where quote_no = ?)) and pos.application_no=mc.application_no";
			ss = runner.singleSelection(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ss;
	}

	public String[][] getByQuote(String qno) {
		String[][] ss = new String[0][0];
		String sql = "";
		String args[] = new String[1];
		try {
			args[0] = qno.trim() + "%";
			sql = "select pm.application_no,pm.login_id,pm.quote_no,pm.remarks,pi.first_name,md.premium,nvl(md.EXCESS_PREMIUM,'0'),pi.COMPANY_NAME,pi.LAST_NAME,pm.quote_no,to_char(pm.entry_date,'DD-MM-YYYY'),pm.status,pm.POLICY_NO,md.remarks from position_master pm,marine_data md,personal_info pi where pm.quote_no like ? and pm.application_no=md.application_no and pi.customer_id=pm.customer_id  and md.ADMIN_REFERRAL_STATUS='Y' and  pm.remarks in ('Normal','NORMAL_EXCESS_PRICE','Admin') and   lower(pm.status)  in('y')  order by pm.quote_no desc";
			ss = runner.multipleSelection(sql, args);
		} catch (Exception e) {
			System.out.println("Exception in gettin refferal data"
					+ e.toString());
			e.printStackTrace();
		}
		return ss;
	}

	public String[][] getByQuote(String qno, String branchCode) {
		String[][] ss = new String[0][0];
		String sql = "";
		try {
			sql = "select pm.application_no,pm.login_id,pm.quote_no,pm.remarks,pi.first_name,md.premium,nvl(md.EXCESS_PREMIUM,'0'),pi.COMPANY_NAME,pi.LAST_NAME,pm.quote_no,to_char(pm.entry_date,'DD-MM-YYYY'),pm.status,pm.POLICY_NO,md.remarks from position_master pm,marine_data md,personal_info pi where pm.quote_no like '"
					+ qno.trim()
					+ "%' and pm.application_no=md.application_no and pi.customer_id=pm.customer_id  and md.ADMIN_REFERRAL_STATUS='Y' and  pm.remarks in ('Normal','NORMAL_EXCESS_PRICE','Admin') and   lower(pm.status)  in('y')  and pm.login_id in(select login_id from login_master where oa_code in(select agency_code from broker_company_master where branch_code in ("
					+ branchCode + ")))order by pm.quote_no desc";
			ss = runner.multipleSelection(sql);
		} catch (Exception e) {
			System.out.println("Exception in gettin refferal data"
					+ e.toString());
			e.printStackTrace();
		}
		return ss;
	}

	public String[][] getPortfolioByPolicy_no(String policy_no, String status,
			String branch) // Branch wise search
	{
		String[][] ss = new String[0][0];
		String[][] multiPolicy = new String[0][0];
		String status123 = "";
		String args[] = new String[2];
		try {
			args[0] = "%" + policy_no + "%";
			args[1] = branch;
			String sql = "";
			String sqlMultiple = "";
			if (status.equalsIgnoreCase("policy_search")) {
				sql = "select pm.application_no,pm.login_id,pm.quote_no,pm.remarks,pi.first_name,pm.premium,nvl(pm.EXCESS_PREMIUM,'0'),pi.COMPANY_NAME,pi.LAST_NAME,pm.POLICY_NO,md.remarks,pm.PDF_MODIFY_CLAUSE,pm.PDF_MODIFY_WARRANTY,pm.PDF_MODIFY_EXCLUSION,pm.PDF_MODIFY_EXTRACLAUSES,pm.PRODUCT_ID,nvl(pm.open_cover_no,'0'),pm.application_id from position_master pm,marine_data md,personal_info pi where pm.policy_no like ? and pm.application_no=md.application_no and pi.customer_id=pm.customer_id and lower(pm.status) in('p') and nvl(pm.open_cover_int_status,'0') not in ('LINKED') and pm.login_id in(select login_id from login_master where oa_code in(select agency_code from broker_company_master where branch_code=?)) order by pm.quote_no desc";
			} else {
				sql = "select pm.application_no,pm.login_id,pm.quote_no,pm.remarks,pi.first_name,pm.premium,nvl(pm.EXCESS_PREMIUM,'0'),pi.COMPANY_NAME,pi.LAST_NAME,pm.POLICY_NO,md.remarks,pm.PDF_MODIFY_CLAUSE,pm.PDF_MODIFY_WARRANTY,pm.PDF_MODIFY_EXCLUSION,pm.PDF_MODIFY_EXTRACLAUSES,pm.PRODUCT_ID,nvl(pm.open_cover_no,'0'),pm.application_id from position_master pm,marine_data md,personal_info pi where pm.policy_no like ? and pm.application_no=md.application_no and pi.customer_id=pm.customer_id and lower(pm.status) in('p')  and nvl(pm.open_cover_int_status,'0') not in ('LINKED') and pm.login_id in(select login_id from login_master where oa_code in(select agency_code from broker_company_master where branch_code=?)) order by pm.quote_no desc";
			}
			ss = runner.multipleSelection(sql, args);
		} catch (Exception e) {
			System.out.println("Exception in gettin refferal data"
					+ e.toString());
			e.printStackTrace();
		}
		return ss;
	}

	// used
	public String[][] getPortfolioByDate(String eDate, String type,
			String status, String pid, String branchCode, String brokerCodes,
			String freightStatus) // PortFolio_ByDate.jsp BrokerCodes
	// Restriction
	{
		String pname = "";
		String loginids = "";
		String addeddata = "";
		String syntax = "";
		if (pid.equals("all")) {
			addeddata = "";
		} else {
			// addeddata="and pm.product_id='"+pid+"'";
			addeddata = "and  pm.product_id in (" + pid + ")";
		}
		if (brokerCodes.equalsIgnoreCase("") || brokerCodes.length() == 0) {
			syntax = "select agency_code from broker_company_master where branch_code in("
					+ branchCode + ")";
		} else {
			brokerCodes = brokerCodes.replaceAll(",", "','");
			syntax = "'" + brokerCodes + "'";
		}
		String freightCheck = "";
		if (freightStatus.equalsIgnoreCase("Yes")) {
			freightCheck = "and pm.FREIGHT_STATUS='F'";
		} else if (pid.equals("all")) {
			freightCheck = "";
		} else if (pid.indexOf(",") != -1) {
			freightCheck = "";
		} else {
			freightCheck = "and pm.FREIGHT_STATUS is null";
		}

		String[][] ss = new String[0][0];
		String[][] multiPolicy = new String[0][0];
		String status123 = "";
		try {
			String sql = "";
			String sqlMultiple = "";
			if (status.equalsIgnoreCase("p")) {

				sql = "select pm.application_no,pm.login_id,pm.quote_no,pm.remarks,pi.first_name,pm.premium,nvl(pm.EXCESS_PREMIUM,'0'),pi.COMPANY_NAME,pi.LAST_NAME,pm.POLICY_NO,md.remarks,pm.PDF_MODIFY_CLAUSE,pm.PDF_MODIFY_WARRANTY,pm.PDF_MODIFY_EXCLUSION,pm.PDF_MODIFY_EXTRACLAUSES,pm.PRODUCT_ID,Policy_Cancel_Status,nvl(pm.open_cover_no,'0'),pm.application_id,nvl(md.ADMIN_REFERRAL_STATUS,'N'),to_char(pm.entry_date,'dd-mm-yyyy') from position_master pm,marine_data md,personal_info pi where to_char(pm.inception_date,'YYYY-MM-DD')='"
						+ eDate
						+ "' and pm.application_no=md.application_no and pi.customer_id=pm.customer_id  "
						+ addeddata
						+ " and lower(pm.status) in('"
						+ status.toLowerCase()
						+ "')  and nvl(pm.open_cover_int_status,'0') not in ('LINKED') and pm.login_id in(select login_id from login_master where oa_code in("
						+ syntax
						+ ")) "
						+ freightCheck
						+ " order by pm.quote_no desc";

				sqlMultiple = "select (pm.policy_no),pm.login_id,nvl(pm.open_cover_no,'0'),sum(pm.premium),sum(pm.excess_premium) from position_master pm where  nvl(pm.open_cover_int_status,'0') in ('LINKED') and  to_char(pm.inception_date,'YYYY-MM-DD')='"
						+ eDate
						+ "'  "
						+ addeddata
						+ " and lower(pm.status) in('"
						+ status.toLowerCase()
						+ "')  and nvl(pm.open_cover_int_status,'0') not in ('LINKED') and pm.login_id in(select login_id from login_master where oa_code in("
						+ syntax
						+ "))  group by pm.policy_no,pm.login_id,pm.open_cover_no order by pm.policy_no";
			} else if (status.equalsIgnoreCase("c")) {

				sql = "select pm.application_no,pm.login_id,pm.quote_no,pm.remarks,pi.first_name,md.premium,nvl(md.EXCESS_PREMIUM,'0'),pi.COMPANY_NAME,pi.LAST_NAME,pm.quote_no,md.remarks,pm.PDF_MODIFY_CLAUSE,pm.PDF_MODIFY_WARRANTY,pm.PDF_MODIFY_EXCLUSION,pm.PDF_MODIFY_EXTRACLAUSES,pm.PRODUCT_ID,Policy_Cancel_Status,nvl(pm.open_cover_no,'0'),pm.application_id,nvl(md.ADMIN_REFERRAL_STATUS,'N'),to_char(pm.entry_date,'dd-mm-yyyy') from position_master pm,marine_data md,personal_info pi where to_char(pm.entry_date,'YYYY-MM-DD')='"
						+ eDate
						+ "' and pm.application_no=md.application_no and pi.customer_id=pm.customer_id and pm.remarks in ('Normal')  "
						+ addeddata
						+ " and lower(pm.status) in('y') and nvl(pm.open_cover_int_status,'0') not in ('LINKED') and (md.ADMIN_REFERRAL_STATUS in('N') or md.ADMIN_REFERRAL_STATUS is null) and pm.login_id in(select login_id from login_master where oa_code in("
						+ syntax
						+ ")) "
						+ freightCheck
						+ "  order by pm.quote_no desc";
			} else if (status.equalsIgnoreCase("r")) {

				sql = "select pm.application_no,pm.login_id,pm.quote_no,pm.remarks,pi.first_name,md.premium,nvl(md.EXCESS_PREMIUM,'0'),pi.COMPANY_NAME,pi.LAST_NAME,pm.quote_no,md.remarks,pm.PDF_MODIFY_CLAUSE,pm.PDF_MODIFY_WARRANTY,pm.PDF_MODIFY_EXCLUSION,pm.PDF_MODIFY_EXTRACLAUSES,pm.PRODUCT_ID,Policy_Cancel_Status,nvl(pm.open_cover_no,'0'),pm.application_id,nvl(md.ADMIN_REFERRAL_STATUS,'N'),to_char(pm.entry_date,'dd-mm-yyyy') from position_master pm,marine_data md,personal_info pi where to_char(pm.entry_date,'YYYY-MM-DD')='"
						+ eDate
						+ "' and pm.application_no=md.application_no and pi.customer_id=pm.customer_id "
						+ addeddata
						+ " and lower(pm.status) in('y') and nvl(pm.open_cover_int_status,'0') not in ('LINKED')  and pm.login_id in(select login_id from login_master where oa_code in("
						+ syntax
						+ ")) "
						+ freightCheck
						+ "  order by pm.quote_no desc";
			} else {
				sql = "select pm.application_no,pm.login_id,pm.quote_no,pm.remarks,pi.first_name,md.premium,nvl(md.EXCESS_PREMIUM,'0'),pi.COMPANY_NAME,pi.LAST_NAME,pm.quote_no,md.remarks,pm.PDF_MODIFY_CLAUSE,pm.PDF_MODIFY_WARRANTY,pm.PDF_MODIFY_EXCLUSION,pm.PDF_MODIFY_EXTRACLAUSES,pm.PRODUCT_ID,Policy_Cancel_Status,nvl(pm.open_cover_no,'0'),pm.application_id,nvl(md.ADMIN_REFERRAL_STATUS,'N'),to_char(pm.entry_date,'dd-mm-yyyy') from position_master pm,marine_data md,personal_info pi where to_char(pm.entry_date,'YYYY-MM-DD')='"
						+ eDate
						+ "' and pm.application_no=md.application_no and pi.customer_id=pm.customer_id and md.ADMIN_REFERRAL_STATUS='Y' and  pm.remarks in ('Normal','NORMAL_EXCESS_PRICE','Admin') and  "
						+ addeddata
						+ " lower(pm.status)  in('y') and nvl(pm.open_cover_int_status,'0') not in ('LINKED') and pm.login_id in(select login_id from login_master where oa_code in("
						+ syntax
						+ ")) "
						+ freightCheck
						+ " order by pm.quote_no desc";
			}
			ss = runner.multipleSelection(sql);

			if (sqlMultiple.length() > 0) {
				multiPolicy = runner.multipleSelection(sqlMultiple);
			}
		} catch (Exception e) {
			System.out.println("Exception in gettin refferal data"
					+ e.toString());
			e.printStackTrace();
		}
		return ss;
	}

	public String[][] getPortfolioByDateMulti(String eDate, String type,
			String status, String pid) {
		String pname = "";
		String loginids = "";
		String addeddata = "";
		if (pid.equals("all")) {
			addeddata = "";
		} else {
			addeddata = "and  pm.product_id='" + pid + "'";
		}
		String[][] ss = new String[0][0];
		String[][] multiPolicy = new String[0][0];
		String status123 = "";
		try {
			String sql = "";
			String sqlMultiple = "";
			if (status.equalsIgnoreCase("p")) {
				sql = "select pm.application_no,pm.login_id,pm.quote_no,pm.remarks,pi.first_name,pm.premium,nvl(pm.EXCESS_PREMIUM,'0'),pi.COMPANY_NAME,pi.LAST_NAME,pm.POLICY_NO,md.remarks,pm.PDF_MODIFY_CLAUSE,pm.PDF_MODIFY_WARRANTY,pm.PDF_MODIFY_EXCLUSION,pm.PDF_MODIFY_EXTRACLAUSES,pm.PRODUCT_ID from position_master pm,marine_data md,personal_info pi where to_char(pm.inception_date,'YYYY-MM-DD')='"
						+ eDate
						+ "' and pm.application_no=md.application_no and pi.customer_id=pm.customer_id  "
						+ addeddata
						+ " and     lower(pm.status) in('"
						+ status.toLowerCase()
						+ "')  and nvl(pm.open_cover_int_status,'0') not in ('LINKED') order by pm.quote_no desc";

				sqlMultiple = "select (a.policy_no),a.login_id,nvl(a.open_cover_no,'0'),sum(a.premium),sum(a.excess_premium) from position_master a where   to_char(a.inception_date,'YYYY-MM-DD')='"
						+ eDate
						+ "'  and lower(a.status) in('"
						+ status.toLowerCase()
						+ "') and nvl(a.open_cover_int_status,'0') in ('LINKED')    group by a.policy_no,a.login_id,a.open_cover_no order by a.policy_no";

			} else if (status.equalsIgnoreCase("c")) {
				sql = "select pm.application_no,pm.login_id,pm.quote_no,pm.remarks,pi.first_name,md.premium,nvl(md.EXCESS_PREMIUM,'0'),pi.COMPANY_NAME,pi.LAST_NAME,pm.quote_no,md.remarks,pm.PDF_MODIFY_CLAUSE,pm.PDF_MODIFY_WARRANTY,pm.PDF_MODIFY_EXCLUSION,pm.PDF_MODIFY_EXTRACLAUSES,pm.PRODUCT_ID from position_master pm,marine_data md,personal_info pi where to_char(pm.entry_date,'YYYY-MM-DD')='"
						+ eDate
						+ "' and pm.application_no=md.application_no and pi.customer_id=pm.customer_id and pm.remarks in ('Normal')  "
						+ addeddata
						+ " and    lower(pm.status)  in('y') and nvl(pm.open_cover_int_status,'0') not in ('LINKED')  order by pm.quote_no desc";
			} else {
				sql = "select pm.application_no,pm.login_id,pm.quote_no,pm.remarks,pi.first_name,md.premium,nvl(md.EXCESS_PREMIUM,'0'),pi.COMPANY_NAME,pi.LAST_NAME,pm.quote_no,md.remarks,pm.PDF_MODIFY_CLAUSE,pm.PDF_MODIFY_WARRANTY,pm.PDF_MODIFY_EXCLUSION,pm.PDF_MODIFY_EXTRACLAUSES,pm.PRODUCT_ID from position_master pm,marine_data md,personal_info pi where to_char(pm.entry_date,'YYYY-MM-DD')='"
						+ eDate
						+ "' and pm.application_no=md.application_no and pi.customer_id=pm.customer_id and md.ADMIN_REFERRAL_STATUS='Y' and  pm.remarks in ('Normal','NORMAL_EXCESS_PRICE','Admin')      "
						+ addeddata
						+ " lower(pm.status)  in('y') and nvl(pm.open_cover_int_status,'0') not in ('LINKED') order by pm.quote_no desc";
			}
			multiPolicy = runner.multipleSelection(sqlMultiple);
		} catch (Exception e) {
			System.out.println("Exception in gettin refferal data"
					+ e.toString());
			e.printStackTrace();
		}
		return multiPolicy;
	}

	public String[][] getPortfolioByDateMulti(String eDate, String type,
			String status, String pid, String branchCode, String brokerCodes) // PortFolio_ByDate.jsp
	// BrokerCodes
	// Restriction
	{
		String pname = "";
		String loginids = "";
		String addeddata = "";
		String syntax = "";
		if (pid.equals("all")) {
			addeddata = "";
		} else {
			// addeddata="and pm.product_id='"+pid+"'";
			addeddata = "and  pm.product_id in(" + pid + ")";
		}

		if (brokerCodes.equalsIgnoreCase("") || brokerCodes.length() == 0) {
			syntax = "select agency_code from broker_company_master where branch_code in("
					+ branchCode + ")";
		} else {
			brokerCodes = brokerCodes.replaceAll(",", "','");
			syntax = "'" + brokerCodes + "'";
		}

		String[][] ss = new String[0][0];
		String[][] multiPolicy = new String[0][0];
		String status123 = "";
		try {
			String sql = "";
			String sqlMultiple = "";
			if (status.equalsIgnoreCase("p")) {
				sql = "select pm.application_no,pm.login_id,pm.quote_no,pm.remarks,pi.first_name,pm.premium,nvl(pm.EXCESS_PREMIUM,'0'),pi.COMPANY_NAME,pi.LAST_NAME,pm.POLICY_NO,md.remarks,pm.PDF_MODIFY_CLAUSE,pm.PDF_MODIFY_WARRANTY,pm.PDF_MODIFY_EXCLUSION,pm.PDF_MODIFY_EXTRACLAUSES,pm.PRODUCT_ID from position_master pm,marine_data md,personal_info pi where to_char(pm.inception_date,'YYYY-MM-DD')='"
						+ eDate
						+ "' and pm.application_no=md.application_no and pi.customer_id=pm.customer_id  "
						+ addeddata
						+ " and lower(pm.status) in('"
						+ status.toLowerCase()
						+ "')  and nvl(pm.open_cover_int_status,'0') not in ('LINKED') and pm.login_id in(select login_id from login_master where oa_code in("
						+ syntax + ")) order by pm.quote_no desc";

				sqlMultiple = "select (a.policy_no),a.login_id,nvl(a.open_cover_no,'0'),sum(a.premium),sum(a.excess_premium) from position_master a where   to_char(a.inception_date,'YYYY-MM-DD')='"
						+ eDate
						+ "' and lower(a.status) in('"
						+ status.toLowerCase()
						+ "') and nvl(a.open_cover_int_status,'0') in ('LINKED') and a.login_id in(select login_id from login_master where oa_code in("
						+ syntax
						+ ")) group by a.policy_no,a.login_id,a.open_cover_no order by a.policy_no";

			} else if (status.equalsIgnoreCase("c")) {
				sql = "select pm.application_no,pm.login_id,pm.quote_no,pm.remarks,pi.first_name,md.premium,nvl(md.EXCESS_PREMIUM,'0'),pi.COMPANY_NAME,pi.LAST_NAME,pm.quote_no,md.remarks,pm.PDF_MODIFY_CLAUSE,pm.PDF_MODIFY_WARRANTY,pm.PDF_MODIFY_EXCLUSION,pm.PDF_MODIFY_EXTRACLAUSES,pm.PRODUCT_ID from position_master pm,marine_data md,personal_info pi where to_char(pm.entry_date,'YYYY-MM-DD')='"
						+ eDate
						+ "' and pm.application_no=md.application_no and pi.customer_id=pm.customer_id and pm.remarks in ('Normal')  "
						+ addeddata
						+ " and  lower(pm.status)  in('y') and nvl(pm.open_cover_int_status,'0') not in ('LINKED')  and pm.login_id in(select login_id from login_master where oa_code in("
						+ syntax + ")) order by pm.quote_no desc";
			} else {
				sql = "select pm.application_no,pm.login_id,pm.quote_no,pm.remarks,pi.first_name,md.premium,nvl(md.EXCESS_PREMIUM,'0'),pi.COMPANY_NAME,pi.LAST_NAME,pm.quote_no,md.remarks,pm.PDF_MODIFY_CLAUSE,pm.PDF_MODIFY_WARRANTY,pm.PDF_MODIFY_EXCLUSION,pm.PDF_MODIFY_EXTRACLAUSES,pm.PRODUCT_ID from position_master pm,marine_data md,personal_info pi where to_char(pm.entry_date,'YYYY-MM-DD')='"
						+ eDate
						+ "' and pm.application_no=md.application_no and pi.customer_id=pm.customer_id and md.ADMIN_REFERRAL_STATUS='Y' and  pm.remarks in ('Normal','NORMAL_EXCESS_PRICE','Admin') "
						+ addeddata
						+ " lower(pm.status)  in('y') and nvl(pm.open_cover_int_status,'0') not in ('LINKED') and pm.login_id in(select login_id from login_master where oa_code in("
						+ syntax + ")) order by pm.quote_no desc";
			}
			if (sqlMultiple.length() > 0)
				multiPolicy = runner.multipleSelection(sqlMultiple);
		} catch (Exception e) {
			System.out.println("Exception in gettin refferal data"
					+ e.toString());
			e.printStackTrace();
		}
		return multiPolicy;
	}

	// Newly added on Aug 10 th for Declaration policy search
	public String[][] getPortfolioByDateMultiSearch(String policyNo,
			String admBranch) {
		String rersult[][] = new String[0][0];
		String sqlMultiple = "";
		String args[] = new String[2];
		try {
			args[0] = "%" + policyNo + "%";
			args[1] = admBranch;
			sqlMultiple = "select (a.policy_no),a.login_id,nvl(a.open_cover_no,'0'),sum(a.premium),sum(a.excess_premium) from position_master a where lower(a.status) in('p') and nvl(a.open_cover_int_status,'0') in ('LINKED')  and a.policy_no like ? and a.login_id in(select login_id from login_master where oa_code in(select agency_code from broker_company_master where branch_code=?)) group by a.policy_no,a.login_id,a.open_cover_no order by a.policy_no";
			rersult = runner.multipleSelection(sqlMultiple, args);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return rersult;
	}

	public String[][] getPortfolioByDateMultiSearchDate(String policyNo,
			String admBranch) {
		String rersult[][] = new String[0][0];
		String sqlMultiple = "";
		String args[] = new String[2];
		try {
			args[0] = "%" + policyNo + "%";
			args[1] = admBranch;
			sqlMultiple = "select to_char(a.inception_date,'YYYY-MM-DD') from position_master a where lower(a.status) in('p') and nvl(a.open_cover_int_status,'0') in ('LINKED')  and a.policy_no like ? and a.login_id in(select login_id from login_master where oa_code in(select agency_code from broker_company_master where branch_code=?)) order by a.quote_no";
			rersult = runner.multipleSelection(sqlMultiple, args);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return rersult;
	}

	// Newly added on Aug 10 th for Declaration policy search
	public String[][] getPortfolioByDateMultiPol(String eDate, String type,
			String status, String pid, String branchCode, String brokerCodes) // PortFolio_ByDate.jsp
	// BrokerCodes
	// Restriction
	{
		String pname = "";
		String loginids = "";
		String addeddata = "";
		String syntax = "";
		if (pid.equals("all")) {
			addeddata = "";
		} else {
			// addeddata="and pm.product_id='"+pid+"'";
			addeddata = "and pm.product_id in(" + pid + ")";
		}

		if (brokerCodes.equalsIgnoreCase("") || brokerCodes.length() == 0) {
			syntax = "select agency_code from broker_company_master where branch_code in("
					+ branchCode + ")";
		} else {
			brokerCodes = brokerCodes.replaceAll(",", "','");
			syntax = "'" + brokerCodes + "'";
		}

		String[][] ss = new String[0][0];
		String[][] multiPolicy = new String[0][0];
		String status123 = "";
		try {
			String sql = "";
			String sqlMultiple = "";
			if (status.equalsIgnoreCase("p")) {
				sqlMultiple = "select Policy_Cancel_Status from position_master a where   to_char(a.inception_date,'YYYY-MM-DD')='"
						+ eDate
						+ "' and lower(a.status) in('"
						+ status.toLowerCase()
						+ "') and nvl(a.open_cover_int_status,'0') in ('LINKED') and a.login_id in(select login_id from login_master where oa_code in("
						+ syntax + ")) order by a.policy_no";

			}
			if (sqlMultiple.length() > 0)
				multiPolicy = runner.multipleSelection(sqlMultiple);
		} catch (Exception e) {
			System.out.println("Exception in gettin refferal data"
					+ e.toString());
			e.printStackTrace();
		}
		return multiPolicy;
	}

	// used for individual page in admin side
	public String[][] getPortfolioByDateMulti(String eDate, String pid,
			String policyno) {
		String pname = "";
		String loginids = "";
		String addeddata = "";
		if (pid.equals("all")) {
			addeddata = "";
		} else {
			addeddata = "and  pm.product_id='" + pid + "'";
		}
		String[][] ss = new String[0][0];
		String[][] multiPolicy = new String[0][0];
		String status123 = "";
		try {
			String sql = "";
			String sqlMultiple = "";

			sql = "select pm.application_no,pm.login_id,pm.quote_no,pm.remarks,pi.first_name,pm.premium,nvl(pm.EXCESS_PREMIUM,'0'),pi.COMPANY_NAME,pi.LAST_NAME,pm.POLICY_NO,md.remarks,pm.PDF_MODIFY_CLAUSE,pm.PDF_MODIFY_WARRANTY,pm.PDF_MODIFY_EXCLUSION,pm.PDF_MODIFY_EXTRACLAUSES,pm.PRODUCT_ID,nvl(pm.open_cover_no,'0'),pm.application_id from position_master pm,marine_data md,personal_info pi where to_char(pm.inception_date,'YYYY-MM-DD')='"
					+ eDate
					+ "' and pm.application_no=md.application_no and pi.customer_id=pm.customer_id  "
					+ addeddata
					+ " and     lower(pm.status) in('p') and pm.policy_no='"
					+ policyno
					+ "' and nvl(pm.open_cover_int_status,'0')  in ('LINKED') order by pm.quote_no";
			multiPolicy = runner.multipleSelection(sql);
		} catch (Exception e) {
			System.out.println("Exception in gettin refferal data"
					+ e.toString());
			e.printStackTrace();
		}
		return multiPolicy;
	}

	// Used
	public String[][] getReferalByDate(String eDate, String type, String pid,
			String branchCode, String brokerCodes, String freightStatus) {
		String pname = "";
		String loginids = "";
		String[][] ss = new String[0][0];
		String status123 = "";

		String syntax = "";
		if (brokerCodes.equalsIgnoreCase("") || brokerCodes.length() == 0) {
			syntax = "select agency_code from broker_company_master where branch_code in("
					+ branchCode + ")";
		} else {
			brokerCodes = brokerCodes.replaceAll(",", "','");
			syntax = "'" + brokerCodes + "'";
		}
		String freightCheck = "";
		if (freightStatus.equalsIgnoreCase("Yes")) {
			freightCheck = "and pm.FREIGHT_STATUS='F'";
		} else {
			freightCheck = "and pm.FREIGHT_STATUS is null";
		}
		try {
			String sql = "select pm.application_no,pm.login_id,pm.quote_no,md.remarks,nvl(pm.application_id,'1'),pm.OPEN_COVER_NO,PM.STATUS from position_master pm,marine_data md where to_char(pm.entry_date,'YYYY-MM-DD')='"
					+ eDate
					+ "' and pm.application_no=md.application_no  and pm.product_id='"
					+ pid
					+ "'  and ((pm.REMARKS in ('Referal') and md.ADMIN_REFERRAL_STATUS='N') or (pm.REMARKS in ('Admin','Normal','NORMAL_EXCESS_PRICE') and  md.ADMIN_REFERRAL_STATUS='Y'))  and pm.status in ('Y','E')  and pm.login_id in(select login_id from login_master where oa_code in("
					+ syntax
					+ ")) "
					+ freightCheck
					+ " order by pm.quote_no desc";
			ss = runner.multipleSelection(sql);
		} catch (Exception e) {
			System.out.println("Exception in gettin refferal data"
					+ e.toString());
			e.printStackTrace();
		}
		return ss;
	}

	public String[][] getQuoteSearch(String qno, String type,
			String branchCode, String brokerCodes) // Entry Date
	{
		String pname = "";
		String loginids = "";
		String[][] ss = new String[0][0];
		String status123 = "";

		String syntax = "";
		if (brokerCodes.equalsIgnoreCase("") || brokerCodes.length() == 0) {
			syntax = "select agency_code from broker_company_master where branch_code in("
					+ branchCode + ")";
		} else {
			brokerCodes = brokerCodes.replaceAll(",", "','");
			syntax = "'" + brokerCodes + "'";
		}
		try {
			String sql = "";
			if (!type.equalsIgnoreCase("Referal")) {
				sql = "select pm.application_no,pm.login_id,pm.quote_no,md.remarks,pi.first_name,pi.last_name,pm.product_id ,to_char(pm.entry_date,'DD-MM-YYYY'),nvl(pm.application_id,'1'),OPEN_COVER_NO from position_master pm,marine_data md,personal_info pi where pm.quote_no like '"
						+ qno
						+ "%' and pm.application_no=md.application_no and pi.customer_id=pm.customer_id and pm.REMARKS in ('Admin','NORMAL_EXCESS_PRICE') and md.ADMIN_REFERRAL_STATUS='N'  and lower(pm.status)  in('y')  and pm.login_id in(select login_id from login_master where oa_code in("
						+ syntax + "))order by quote_no desc";
			} else {
				sql = "select pm.application_no,pm.login_id,pm.quote_no,md.remarks,pm.product_id,to_char(pm.entry_date,'DD-MM-YYYY'),nvl(pm.application_id,'1'),OPEN_COVER_NO  from position_master pm,marine_data md,personal_info pi where pm.quote_no like '"
						+ qno
						+ "%'  and pm.application_no=md.application_no and pi.customer_id=pm.customer_id and ((pm.REMARKS in ('Referal') and md.ADMIN_REFERRAL_STATUS='N') or (pm.REMARKS in ('Admin','Normal','NORMAL_EXCESS_PRICE') and md.ADMIN_REFERRAL_STATUS='Y')) and pm.status='Y'  and pm.login_id in(select login_id from login_master where oa_code in("
						+ syntax + "))order by pm.quote_no desc";
			}
			ss = runner.multipleSelection(sql);
		} catch (Exception e) {
			System.out.println("Exception in gettin refferal data"
					+ e.toString());
			e.printStackTrace();
		}
		return ss;
	}

	public String[][] getQuoteSearch(String qno, String type, String branchCode) // Entry
	// Date
	{
		String pname = "";
		String loginids = "";
		String[][] ss = new String[0][0];
		String status123 = "";

		try {
			String sql = "";
			if (!type.equalsIgnoreCase("Referal")) {
				sql = "select pm.application_no,pm.login_id,pm.quote_no,md.remarks,pi.first_name,pi.last_name,pm.product_id ,to_char(pm.entry_date,'DD-MM-YYYY'),pm.OPEN_COVER_NO from position_master pm,marine_data md,personal_info pi where pm.quote_no like '"
						+ qno
						+ "%' and pm.application_no=md.application_no and pi.customer_id=pm.customer_id and pm.REMARKS in ('Admin','NORMAL_EXCESS_PRICE') and md.ADMIN_REFERRAL_STATUS='N'  and lower(pm.status)  in('y')  and pm.login_id in(select login_id from login_master where oa_code in(select agency_code from broker_company_master where branch_code in("
						+ branchCode + ")))order by quote_no desc";
			} else {
				sql = "select pm.application_no,pm.login_id,pm.quote_no,md.remarks,pm.product_id,to_char(pm.entry_date,'DD-MM-YYYY'),pm.OPEN_COVER_NO  from position_master pm,marine_data md,personal_info pi where pm.quote_no like '"
						+ qno
						+ "%'  and pm.application_no=md.application_no and pi.customer_id=pm.customer_id and ((pm.REMARKS in ('Referal') and md.ADMIN_REFERRAL_STATUS='N') or (pm.REMARKS in ('Admin','Normal','NORMAL_EXCESS_PRICE') and md.ADMIN_REFERRAL_STATUS='Y')) and pm.status='Y'  and pm.login_id in(select login_id from login_master where oa_code in(select agency_code from broker_company_master where branch_code in("
						+ branchCode + ")))order by pm.quote_no desc";
			}
			ss = runner.multipleSelection(sql);
		} catch (Exception e) {
			System.out.println("Exception in gettin refferal data"
					+ e.toString());
			e.printStackTrace();
		}
		return ss;
	}

	// Used
	public String[][] getApprovedByDate(String eDate, String type, String pid,
			String branchCode, String brokerCodes, String freightStatus) {
		String pname = "";
		String loginids = "";
		String[][] ss = new String[0][0];
		String status123 = "";

		String syntax = "";
		if (brokerCodes.equalsIgnoreCase("") || brokerCodes.length() == 0) {
			syntax = "select agency_code from broker_company_master where branch_code in("
					+ branchCode + ")";
		} else {
			brokerCodes = brokerCodes.replaceAll(",", "','");
			syntax = "'" + brokerCodes + "'";
		}
		String freightCheck = "";
		if (freightStatus.equalsIgnoreCase("Yes")) {
			freightCheck = "and pm.FREIGHT_STATUS='F'";
		} else {
			freightCheck = "and pm.FREIGHT_STATUS is null";
		}
		try {
			String sql = "select pm.application_no,pm.login_id,pm.quote_no,md.remarks,pm.application_id,PM.STATUS from position_master pm,marine_data md where to_char(pm.entry_date,'YYYY-MM-DD')='"
					+ eDate
					+ "' and pm.application_no=md.application_no and pm.REMARKS in ('"
					+ type
					+ "','NORMAL_EXCESS_PRICE') and md.ADMIN_REFERRAL_STATUS='N' and pm.product_id='"
					+ pid
					+ "' and pm.status in('Y','E') and pm.login_id in(select login_id from login_master where oa_code in("
					+ syntax + ")) " + freightCheck + " order by quote_no desc";
			ss = runner.multipleSelection(sql);
		} catch (Exception e) {
			System.out.println("Exception in gettin refferal data"
					+ e.toString());
			e.printStackTrace();
		}
		return ss;
	}

	public String getAgencyCode(String logpersonId) {
		String agencyCode = "";
		String sql = "";
		String args[] = new String[1];
		try {
			args[0] = logpersonId;
			sql = "select agency_code from login_master where login_id=?";
			agencyCode = runner.singleSelection(sql, args);
		} catch (Exception e) {
			System.out.println("Exception in getting agency code"
					+ e.toString());
			e.printStackTrace();
		}
		return agencyCode;
	}

	public String[][] getQuotedPerson(String cid) {
		String[][] brokerName = new String[0][0];
		String sql = "";
		String args[] = new String[1];
		try {
			args[0] = cid;
			sql = "select application_id,nvl(first_name||' '||last_name||' '||company_name,'') from personal_info where login_id=? and application_id in ('3','2','5')";
			brokerName = runner.multipleSelection(sql, args);
		} catch (Exception e) {
			System.out.println("Exception in " + e.toString());
			e.printStackTrace();
		}
		return brokerName;
	}

	public String[][] getBrokerNameByUserId123(String cid) {
		String[][] brokerName = new String[0][0];
		String sql = "";
		String args[] = new String[1];
		try {
			args[0] = cid;
			sql = "select a.first_name,b.company_name,c.usertype,a.application_id from personal_info a,broker_company_master b,login_master c where a.login_id=(select login_id from login_master where oa_code=(select oa_code from login_master where login_id=?) and usertype='Broker') and (a.application_id='2' or (a.application_id='1' ))  and b.agency_code=c.agency_code and a.customer_id=b.customer_id";
			brokerName = runner.multipleSelection(sql, args);
		} catch (Exception e) {
			System.out.println("Exception in " + e.toString());
			e.printStackTrace();
		}
		return brokerName;
	}

	public HashMap getTotalQuotedPersons(String logins, String branchCode) {
		String commodityId1 = "";
		String[][] ss = new String[0][0];
		String sql = "";
		HashMap commHash = new HashMap();
		String loginids = logins;
		loginids = loginids.replaceAll("'", "");
		try {
			sql = "select p.application_id,nvl(p.first_name||' '||p.last_name||' '||p.company_name,''),nvl(p.customer_login_id,l.login_id) from personal_info p,login_master l where (p.login_id in("
					+ logins
					+ ") or p.customer_login_id in("
					+ logins
					+ ")) and p.application_id in ('3','2','5','1') and p.login_id in(select login_id from login_master where oa_code in(select agency_code from broker_company_master where branch_code in("
					+ branchCode + "))) and p.login_id=l.login_id";
			ss = runner.multipleSelection(sql);
			for (int j = 0; j < ss.length; j++) {
				int comFlag = 0;
				String result[][] = new String[1][3];
				StringTokenizer st = new StringTokenizer(loginids, ",");
				while (st.hasMoreTokens()) {
					if (ss[j][2].equalsIgnoreCase(st.nextToken())) {
						result[0][0] = ss[j][0];
						result[0][1] = ss[j][1];
						result[0][2] = ss[j][2];
						comFlag = 1;
					}
				}
				if (!commHash.containsKey("user" + ss[j][2]) && comFlag == 1) {
					commHash.put("user" + ss[j][2], result);
				}

			}
			ss = new String[0][0];
			sql = "select a.first_name,b.company_name,c.usertype,a.application_id,c.login_id from personal_info a,broker_company_master b,login_master c where (a.login_id in(select login_id from login_master where oa_code in(select oa_code from login_master where login_id in("
					+ logins
					+ ")) and usertype='Broker') or a.customer_login_id in(select login_id from login_master where oa_code in(select oa_code from login_master where login_id in("
					+ logins
					+ ")) and usertype='Broker')) and (a.application_id='2' or (a.application_id='1' ))  and b.agency_code=c.agency_code and a.customer_id=b.customer_id and a.login_id in(select login_id from login_master where oa_code in(select agency_code from broker_company_master where branch_code in("
					+ branchCode + ")))";
			ss = runner.multipleSelection(sql);
			for (int j = 0; j < ss.length; j++) {
				int comFlag = 0;
				String result[][] = new String[1][5];
				StringTokenizer st = new StringTokenizer(loginids, ",");
				while (st.hasMoreTokens()) {
					if (ss[j][4].equalsIgnoreCase(st.nextToken())) {
						result[0][0] = ss[j][0];
						result[0][1] = ss[j][1];
						result[0][2] = ss[j][2];
						result[0][3] = ss[j][3];
						result[0][4] = ss[j][4];
						comFlag = 1;
					}
				}
				if (!commHash.containsKey("brokers" + ss[j][4]) && comFlag == 1) {
					commHash.put("brokers" + ss[j][4], result);
				}

			}
		} catch (Exception e) {
			System.out.println("Exception in gettin refferal data"
					+ e.toString());
			e.printStackTrace();
		}
		return commHash;
	}

	public String[][] getQuotedPerson(String cid, String branchCode) {
		String[][] brokerName = new String[0][0];
		String sql = "";
		String qry = "";
		String loginType = "";
		String args[] = new String[0];
		try {
			args = new String[1];
			args[0] = cid;
			qry = "select usertype from login_master where login_id=?";
			loginType = runner.singleSelection(qry, args);
		} catch (Exception e) {
			System.out.println(" getQuotedPerson 1" + e.toString());
			e.printStackTrace();
		}
		if (loginType.length() > 0) {
			if (loginType.equalsIgnoreCase("Customer")) {
				sql = "select application_id,nvl(first_name||' '||last_name||' '||company_name,'') from personal_info where application_id in ('1') and customer_id in(select distinct customer_id from login_user_Details where login_id='"
						+ cid + "')";
				// sql = "select application_id,nvl(first_name||'
				// '||last_name||' '||company_name||'-'||CUSTOMER_LOGIN_ID,'')
				// from personal_info where CUSTOMER_LOGIN_ID='"+cid+"' and
				// application_id in ('1') and agency_code in(select agency_code
				// from login_master where login_id='"+cid+"')";
			} else {
				// sql="select application_id,nvl(first_name||' '||last_name||'
				// '||company_name,'') from personal_info where
				// login_id='"+cid+"' and application_id in ('3','2','5','8')
				// and login_id in(select login_id from login_master where
				// oa_code in(select agency_code from broker_company_master
				// where branch_code in("+branchCode+")))";
				sql = "select application_id,nvl(first_name||' '||last_name||' '||company_name,'') from personal_info where login_id='"
						+ cid + "' and application_id in ('3','2','5','8')";
			}
		}
		try {
			brokerName = runner.multipleSelection(sql);
		} catch (Exception e) {
			System.out.println("Exception in " + e.toString());
			e.printStackTrace();
		}
		return brokerName;
	}

	public String[][] getBrokerNameByUserId123(String cid, String branchCode) {
		String[][] brokerName = new String[0][0];
		String sql = "";
		try {
			sql = "select a.first_name,b.company_name,c.usertype,a.application_id from personal_info a,broker_company_master b,login_master c where a.login_id=(select login_id from login_master where oa_code=(select oa_code from login_master where login_id='"
					+ cid
					+ "') and usertype='Broker') and (a.application_id='2' or (a.application_id='1' ))  and b.agency_code=c.agency_code and a.customer_id=b.customer_id and a.login_id in(select login_id from login_master where oa_code in(select agency_code from broker_company_master where branch_code in("
					+ branchCode + ")))";
			brokerName = runner.multipleSelection(sql);
		} catch (Exception e) {
			System.out.println("Exception in " + e.toString());
			e.printStackTrace();
		}
		return brokerName;
	}

	public String getBrokerNameByUserId(String cid) {
		String brokerName = "";
		String sql = "";
		String args[] = new String[1];
		try {
			args[0] = cid;
			sql = "select first_name from personal_info where login_id=(select login_id from login_master where oa_code=(select oa_code from login_master where login_id=?) and usertype='Broker') and application_id='2'";
			brokerName = runner.singleSelection(sql, args);
		} catch (Exception e) {
			System.out.println("Exception in " + e.toString());
			e.printStackTrace();
		}
		return brokerName;
	}

	public String[][] getViewQuotes123(String loginIds, String status) {
		String loginids = "";
		String[][] valuess = new String[0][0];
		String[][] ss = new String[0][0];
		String status123 = "";
		if (status.equalsIgnoreCase("app")) {
			status123 = "Admin";
		} else if (status.equalsIgnoreCase("unapp")) {
			status123 = "Referal";
		}
		try {
			String sql = "select login_id from login_master where oa_code='"
					+ getAgencyCode(loginIds) + "' or created_by='"
					+ getAgencyCode(loginIds) + "' or login_id='" + loginIds
					+ "'";
			valuess = runner.multipleSelection(sql);
			for (int i = 0; i < valuess.length; i++) {
				loginids = "'" + valuess[i][0] + "'," + loginids;
			}
			if (loginids.length() > 0)
				loginids = loginids.substring(0, loginids.lastIndexOf(','));
			sql = "select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,b.first_name,initcap(b.first_name),a.login_id,a.APPLICATION_NO from position_master a,personal_info b where a.login_id in ("
					+ loginids
					+ ") and a.status='Y' and b.customer_id=a.customer_id and a.REMARKS='"
					+ status123 + "' order by initcap(b.first_name)";
			ss = runner.multipleSelection(sql);
		} catch (Exception e) {
			System.out.println("Exception in gettin refferal data"
					+ e.toString());
			e.printStackTrace();
		}
		return ss;
	}

	// Doubtfull Method - Rajesh 15/02/08
	public HashMap checkingvalues1234(String data1, String data2,
			String login_id) {
		String startdate = data1;
		String enddate = data2;
		String loginid = login_id;
		String brokerIds = "";
		String returnval[][] = new String[0][0];
		String values[][] = new String[0][0];
		String values123[][] = new String[0][0];
		String qry = "";
		qry = "select login_id,username from login_master where  oa_code=(select oa_code from login_master where login_id='"
				+ loginid + "')";

		try {
			values = runner.multipleSelection(qry);
		} catch (Exception e) {
			System.out
					.println("Exception in getiing report dats by using date 1.."
							+ e.toString());
			e.printStackTrace();
		}
		for (i = 0; i < values.length; i++) {
			brokerIds = "'" + values[i][0] + "'," + brokerIds;
		}
		if (brokerIds.length() > 0) {
			brokerIds = brokerIds.substring(0, brokerIds.lastIndexOf(','));
			brokerIds = brokerIds.substring(0, brokerIds.lastIndexOf('\''));
			brokerIds = brokerIds.substring(1, brokerIds.length());
		}
		setBrokerLoginIds(values);
		java.util.HashMap brokerData = new java.util.HashMap();
		java.util.HashMap lids = new java.util.HashMap();
		try {
			int p = 0;
			qry = "select b.login_id,count(b.POLICY_NO),sum(b.premium+nvl(b.EXCESS_PREMIUM,0)),sum(nvl(b.commission,'0')) from personal_info a,position_master b where a.CUSTOMER_ID=b.CUSTOMER_ID and   b.LOGIN_ID in ('"
					+ brokerIds
					+ "') and b.inception_date between to_date('"
					+ startdate
					+ "','dd-mm-yyyy') and to_date('"
					+ enddate
					+ "','dd-mm-yyyy')+1 and  b.status='P' and b.DEBIT_NOTE_NO is not null group by b.login_id";
			returnval = runner.multipleSelection(qry);
			for (int xx = 0; xx < returnval.length; xx++) {
				brokerData.put("Broker" + (p + 1), returnval);
				lids.put("IDS" + (p + 1), values[xx][0].trim());
				lids.put("IDS" + (p + 1), values[xx][0].trim());
				// lids.put("IDS"+ (p+1),values[0][0].trim());
				// lids.put("IDS"+ (p+1),values[0][0].trim());
				p++;
			}
		} catch (Exception e) {
			System.out
					.println("Exception in getiing report dateeeeeees by using date"
							+ e.toString());
			e.printStackTrace();
		}
		brokerData.put("values", values);
		brokerData.put("lids", lids);
		return brokerData;
	}

	public HashMap Newcheckingvalues123(String data1, String data2) {
		String startdate = data1;
		String enddate = data2;
		String brokerIds = "";
		String returnval[][] = new String[0][0];
		String values[][] = new String[0][0];
		System.out.println("not callint");
		String qry = "";
		String args[] = new String[3];
		try {
			qry = "select login_id,agency_code,oa_code from login_master where usertype='Broker' and oa_code is not null and agency_code is not null";
			values = runner.multipleSelection(qry);
			brokerLoginIds = values;
		} catch (Exception e) {
			System.out
					.println("Exception in getiing report dats by using date...2"
							+ e.toString());
			e.printStackTrace();
		}
		setBrokerLoginIds(values);
		java.util.HashMap brokerData = new java.util.HashMap();
		try {
			for (int i = 0; i < values.length; i++) {
				args[0] = values[i][0];
				args[1] = startdate;
				args[2] = enddate;
				qry = "select a.login_id,count(b.POLICY_NO),sum(b.premium+nvl(b.EXCESS_PREMIUM,0)),sum(nvl(b.commission,'0')) from personal_info a,position_master b,marine_result c,login_user_details d where a.CUSTOMER_ID=b.CUSTOMER_ID and b.login_id=d.login_id and b.login_id=a.login_id  and b.application_no=c.application_no  and b.LOGIN_ID=? and b.inception_date between to_date(?,'dd-mm-yyyy') and to_date(?,'dd-mm-yyyy')+1 and  b.status='P'  group by b.login_id";

				returnval = runner.multipleSelection(qry, args);
				brokerData.put("Broker" + values[i][0], returnval);
			}
		} catch (Exception e) {
			System.out
					.println("Exception in getiing report dats by using date...3"
							+ e.toString());
			e.printStackTrace();
		}
		brokerData.put("values", values);
		return brokerData;
	}

	// for direct Client - July 15th
	public String[][] Reportcheckingvalues(String data1, String data2,
			String broktype, String pid, String brokerBra, String rsaissuer) // finalReports2.jsp
																				// BrokerSide
																				// Tax
	{
		String values[][] = new String[0][0];
		String values123[][] = new String[0][0];
		String loginList = "";
		String sql = "";
		String codeValues[][] = new String[0][0];
		String qry1 = "";
		String args[] = new String[0];
		String type = "";
		args = new String[1];

		try {
			args[0] = broktype;
			sql = "select usertype,agency_Code,oa_code from login_master where login_id=?";
			codeValues = runner.multipleSelection(sql, args);
		} catch (Exception e) {
			System.out.println("Exception getting from CodeValues...."
					+ e.toString());
			e.printStackTrace();
		}// for direct Client - July 15th
		if ("Broker".equalsIgnoreCase(codeValues[0][0])) {
			args[0] = codeValues[0][2];
			qry1 = "select application_id,nvl(customer_login_id,login_id),agency_code,oa_Code,first_name from personal_info where oa_code=?";
		} else if ("Customer".equalsIgnoreCase(codeValues[0][0])) {
			args[0] = codeValues[0][1];
			qry1 = "select application_id,customer_login_id,agency_code,oa_Code,first_name from personal_info where agency_Code=?";
			type = "Customer";
		} else if ("User".equalsIgnoreCase(codeValues[0][0])) { // else // Multi
																// Login May
																// 19th
			args[0] = codeValues[0][1];
			qry1 = "select application_id,login_id,agency_code,oa_Code,first_name from personal_info where agency_Code=?";
		} else if ("Freight".equalsIgnoreCase(codeValues[0][0])) { // Multi
																	// Login May
																	// 19th
			args[0] = codeValues[0][1];
			// qry1 = "select
			// application_id,login_id,agency_code,oa_Code,first_name from
			// personal_info where agency_code=?";
			qry1 = "select count (*) from login_master where agency_code=fd_code and agency_code =?";
			String count = runner.singleSelection(qry1, args);
			if (count.equals("0")) {
				qry1 = "select application_id,login_id,agency_code,oa_code,first_name from personal_info where agency_code=(select agency_code from login_master where agency_code=?) and login_id!='NONE' and application_id in ('5')";
			} else if (count.equals("1")) {
				qry1 = "select application_id,login_id,agency_code,oa_code,first_name from personal_info where FD_CODE=(select agency_code from login_master where agency_code=?) and login_id!='NONE' and application_id in ('5')";
			}
		}
		try {
			values = runner.multipleSelection(qry1, args);
			for (int i = 0; i < values.length; i++) {
				loginList = loginList + "'" + values[i][1] + "',";
			}
			if (loginList.length() > 0) {
				loginList = loginList.substring(0, (loginList.length() - 1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		String startdate = data1;
		String enddate = data2;
		String returnval[][] = new String[0][0];
		String qry = "";
		if (values.length > 0 && !values[0][0].equals("4")) {
			if (rsaissuer == null) {
				if (!"Customer".equalsIgnoreCase(type)) {
					qry = "select a.policy_no,to_char(a.inception_date,'DD-MM-YYYY'), b.mode_of_transport,h.transport_description,b.cover_id,d.cover_name, b.sale_term_id, e.sale_term_name,b.extra_cover_id, f.extra_cover_name,  b.total_sum_insured, a.premium ,nvl(a.commission,'0'), a.remarks, a.login_id,b.total_war_charges,a.quote_no,a.debit_note_no,nvl(g.first_name,g.company_name),b.transit_from,b.final_destination,b.total_sum_insured,b.exchange_rate,a.login_id,nvl(mp.bank_name,' '),nvl(mp.carrier_name,' '),a.excess_premium,b.CURRENCY_NAME,a.product_id,b.TOTAL_SALE_TERM_CHARGES,b.transit_from_city_id,b.transit_from_country_id,b.final_destination_city_id,b.final_Destination_country_id,to_char(b.INCEPTION_DATE,'dd-MM-yyyy'),a.PRO_COMMISSION,a.tax,a.application_id from position_master a, marine_data b, cover_master d, sale_term_master e, extra_cover_master f, personal_info g, mode_of_transport h,marine_policy_details mp where b.cover_id=d.cover_id and b.sale_term_id=e.sale_term_id and f.extra_cover_id=b.extra_cover_id and d.branch_code = e.branch_code and d.branch_code = f.branch_code and d.branch_code=h.branch_code and d.branch_code='"
							+ brokerBra
							+ "' and e.branch_code='"
							+ brokerBra
							+ "' and f.branch_code='"
							+ brokerBra
							+ "' and h.branch_code='"
							+ brokerBra
							+ "' and a.application_no=b.application_no and  g.customer_id=a.customer_id   and b.mode_of_transport=h.mode_transport_id and (a.inception_date between to_date('"
							+ startdate
							+ "','dd-mm-yyyy') and to_date('"
							+ enddate
							+ "','dd-mm-yyyy')+1 ) and g.application_id='1' and g.application_id = a.application_id and a.login_id in ("
							+ loginList
							+ ") and  mp.quote_no=a.quote_no and a.status='P' and a.product_id='"
							+ pid
							+ "' and a.DEBIT_NOTE_NO is not null order by a.inception_date desc";
				} else if ("Customer".equalsIgnoreCase(type)) {
					qry = "select a.policy_no,to_char(a.inception_date,'DD-MM-YYYY'), b.mode_of_transport,h.transport_description,b.cover_id,d.cover_name, b.sale_term_id, e.sale_term_name,b.extra_cover_id, f.extra_cover_name,  b.total_sum_insured, a.premium ,nvl(a.commission,'0'), a.remarks, a.login_id,b.total_war_charges,a.quote_no,a.debit_note_no,nvl(g.first_name,g.company_name),b.transit_from,b.final_destination,b.total_sum_insured,b.exchange_rate,a.login_id,nvl(mp.bank_name,' '),nvl(mp.carrier_name,' '),a.excess_premium,b.CURRENCY_NAME,a.product_id,b.TOTAL_SALE_TERM_CHARGES,b.transit_from_city_id,b.transit_from_country_id,b.final_destination_city_id,b.final_Destination_country_id,to_char(b.INCEPTION_DATE,'dd-MM-yyyy'),a.PRO_COMMISSION,a.tax,a.application_id from position_master a, marine_data b, cover_master d, sale_term_master e, extra_cover_master f, personal_info g, mode_of_transport h,marine_policy_details mp where b.cover_id=d.cover_id and b.sale_term_id=e.sale_term_id and f.extra_cover_id=b.extra_cover_id and d.branch_code = e.branch_code and d.branch_code = f.branch_code and d.branch_code=h.branch_code and d.branch_code='"
							+ brokerBra
							+ "' and e.branch_code='"
							+ brokerBra
							+ "' and f.branch_code='"
							+ brokerBra
							+ "' and h.branch_code='"
							+ brokerBra
							+ "' and a.application_no=b.application_no and  g.customer_id=a.customer_id   and b.mode_of_transport=h.mode_transport_id and (a.inception_date between to_date('"
							+ startdate
							+ "','dd-mm-yyyy') and to_date('"
							+ enddate
							+ "','dd-mm-yyyy')+1 ) and g.application_id='1' and g.application_id = a.application_id and (a.customer_id in (select customer_id from personal_info where customer_login_id='"
							+ broktype
							+ "') or a.login_id='"
							+ broktype
							+ "') and  mp.quote_no=a.quote_no and a.status='P' and a.product_id='"
							+ pid
							+ "' and a.DEBIT_NOTE_NO is not null order by a.inception_date desc";
				}
			} else {
				qry = "select a.policy_no,to_char(a.inception_date,'DD-MM-YYYY'), b.mode_of_transport,h.transport_description,b.cover_id,d.cover_name, b.sale_term_id, e.sale_term_name,b.extra_cover_id, f.extra_cover_name,  b.total_sum_insured, a.premium ,nvl(a.commission,'0'), a.remarks, a.login_id,b.total_war_charges,a.quote_no,a.debit_note_no,nvl(g.first_name,g.company_name),b.transit_from,b.final_destination,b.total_sum_insured,b.exchange_rate,a.login_id,nvl(mp.bank_name,' '),nvl(mp.carrier_name,' '),a.excess_premium,b.CURRENCY_NAME,a.product_id,b.TOTAL_SALE_TERM_CHARGES,b.transit_from_city_id,b.transit_from_country_id,b.final_destination_city_id,b.final_Destination_country_id,to_char(b.INCEPTION_DATE,'dd-MM-yyyy'),a.PRO_COMMISSION,a.tax,a.application_id from position_master a, marine_data b, cover_master d, sale_term_master e, extra_cover_master f, personal_info g, mode_of_transport h,marine_policy_details mp where b.cover_id=d.cover_id and b.sale_term_id=e.sale_term_id and f.extra_cover_id=b.extra_cover_id and d.branch_code = e.branch_code and d.branch_code = f.branch_code and d.branch_code=h.branch_code and d.branch_code='"
						+ brokerBra
						+ "' and e.branch_code='"
						+ brokerBra
						+ "' and f.branch_code='"
						+ brokerBra
						+ "' and h.branch_code='"
						+ brokerBra
						+ "' and a.application_no=b.application_no and  g.customer_id=a.customer_id   and b.mode_of_transport=h.mode_transport_id and (a.inception_date between to_date('"
						+ startdate
						+ "','dd-mm-yyyy') and to_date('"
						+ enddate
						+ "','dd-mm-yyyy')+1 ) and g.application_id='1' and a.login_id in ("
						+ loginList
						+ ") and  mp.quote_no=a.quote_no and a.status='P' and a.product_id='"
						+ pid
						+ "' and a.DEBIT_NOTE_NO is not null and a.application_id='"
						+ rsaissuer + "' order by a.inception_date desc";
			}
		} else {
			String qry12 = "";
			try {
				args[0] = codeValues[0][1];
				qry12 = "select AC_EXECUTIVE_ID,AC_EXECUTIVE_NAME,AGENCY_CODE from LOGIN_EXECUTIVE_DETAILS where agency_code=?";
				values123 = runner.multipleSelection(qry12, args);
			} catch (Exception e) {
				System.out
						.println("Exception in getiing report dats by using date...4"
								+ e.toString());
				e.printStackTrace();
			}

			qry = "select a.policy_no,to_char(a.inception_date,'DD-MM-YYYY'), b.mode_of_transport,h.transport_description,b.cover_id,d.cover_name, b.sale_term_id, e.sale_term_name,b.extra_cover_id, f.extra_cover_name,  b.total_sum_insured, a.premium ,nvl(a.commission,'0'), a.remarks, a.login_id,b.total_war_charges,a.quote_no,a.debit_note_no,nvl(g.first_name,g.company_name),b.transit_from,b.final_destination,b.total_sum_insured,b.exchange_rate,a.login_id,nvl(mp.bank_name,' '),nvl(mp.carrier_name,' '),a.excess_premium,b.CURRENCY_NAME,a.product_id,b.TOTAL_SALE_TERM_CHARGES,b.transit_from_city_id,b.transit_from_country_id,b.final_destination_city_id,b.final_Destination_country_id,to_char(b.INCEPTION_DATE,'dd-MM-yyyy'),a.PRO_COMMISSION,a.tax from position_master a, marine_data b, cover_master d, sale_term_master e, extra_cover_master f, personal_info g, mode_of_transport h,marine_policy_details mp where b.cover_id=d.cover_id and b.sale_term_id=e.sale_term_id and f.extra_cover_id=b.extra_cover_id and a.application_no=b.application_no and  g.customer_id=a.customer_id   and b.mode_of_transport=h.mode_transport_id and (a.inception_date between to_date('"
					+ startdate
					+ "','dd-mm-yyyy') and to_date('"
					+ enddate
					+ "','dd-mm-yyyy')+1 ) and g.application_id='1' and (b.AC_EXECUTIVE_ID='"
					+ values123[0][0]
					+ "' or a.login_id in ("
					+ loginList
					+ ")) and mp.quote_no=a.quote_no and a.status='P' and a.product_id='"
					+ pid
					+ "' and a.DEBIT_NOTE_NO is not null order by a.inception_date desc";
		}

		try {
			System.out.println("Reports Query is      " + qry);
			returnval = runner.multipleSelection(qry);
		} catch (Exception e) {
			System.out
					.println("Exception in getiing report dats by using date...5"
							+ e.toString());
			e.printStackTrace();
		}
		return returnval;
	}

	public String[][] Reportcheckingvalues(String data1, String data2,
			String broktype, String pid, String branchCode) {
		String values[][] = new String[0][0];
		String values123[][] = new String[0][0];
		String loginList = "";
		String qry1 = "";
		String args[] = new String[0];
		try {
			qry1 = "select application_id,login_id,agency_code,oa_Code,first_name from personal_info where oa_code='"
					+ broktype
					+ "' and login_id in(select login_id from login_master where oa_code in(select agency_code from broker_company_master where branch_code in('"
					+ branchCode + "')))";
			values = runner.multipleSelection(qry1);
			for (int i = 0; i < values.length; i++) {
				loginList = loginList + "'" + values[i][1] + "',";
			}
			if (loginList.length() > 0)
				loginList = loginList.substring(0, (loginList.length() - 1));
		} catch (Exception e) {
			System.out
					.println("Exception in getiing report dats by using Report checking values.."
							+ e.toString());
			e.printStackTrace();
		}
		String startdate = data1;
		String enddate = data2;
		String returnval[][] = new String[0][0];

		String qry = "";

		if (!values[0][0].equals("4")) {
			qry = "select a.policy_no,to_char(a.inception_date,'DD-MM-YYYY'), b.mode_of_transport,h.transport_description,b.cover_id,d.cover_name, b.sale_term_id, e.sale_term_name,b.extra_cover_id, f.extra_cover_name,  b.total_sum_insured, a.premium ,nvl(a.commission,'0'), a.remarks, a.login_id,b.total_war_charges,a.quote_no,a.debit_note_no,nvl(g.first_name,g.company_name),b.transit_from,b.final_destination,b.total_sum_insured,b.exchange_rate,a.login_id,nvl(mp.bank_name,' '),nvl(mp.carrier_name,' '),a.excess_premium,b.CURRENCY_NAME,a.product_id,b.TOTAL_SALE_TERM_CHARGES,b.transit_from_city_id,b.transit_from_country_id,b.final_destination_city_id,b.final_Destination_country_id,to_char(b.INCEPTION_DATE,'dd-MM-yyyy') from position_master a, marine_data b, cover_master d, sale_term_master e, extra_cover_master f, personal_info g, mode_of_transport h,marine_policy_details mp where b.cover_id=d.cover_id and b.sale_term_id=e.sale_term_id and f.extra_cover_id=b.extra_cover_id and (d.branch_code in('"
					+ branchCode
					+ "') and e.branch_code in('"
					+ branchCode
					+ "') and f.branch_code in('"
					+ branchCode
					+ "') and h.branch_code in('"
					+ branchCode
					+ "'))and a.application_no=b.application_no and  g.customer_id=a.customer_id and b.mode_of_transport=h.mode_transport_id and (a.inception_date between to_date('"
					+ startdate
					+ "','dd-mm-yyyy') and to_date('"
					+ enddate
					+ "','dd-mm-yyyy')+1 ) and g.application_id='1' and a.login_id in ("
					+ loginList
					+ ") and  mp.quote_no=a.quote_no and a.status='P' and a.product_id='"
					+ pid
					+ "' and a.DEBIT_NOTE_NO is not null order by a.inception_date desc";
		} else {
			String qry12 = "";
			args = new String[1];
			args[0] = broktype;

			try {
				qry12 = "select AC_EXECUTIVE_ID,AC_EXECUTIVE_NAME,AGENCY_CODE from LOGIN_EXECUTIVE_DETAILS where agency_code=?";
				values123 = runner.multipleSelection(qry12, args);
			} catch (Exception e) {
				System.out
						.println("Exception in getiing report dats by using date...4"
								+ e.toString());
				e.printStackTrace();
			}

			qry = "select a.policy_no,to_char(a.inception_date,'DD-MM-YYYY'), b.mode_of_transport,h.transport_description,b.cover_id,d.cover_name, b.sale_term_id, e.sale_term_name,b.extra_cover_id, f.extra_cover_name,  b.total_sum_insured, a.premium ,nvl(a.commission,'0'), a.remarks, a.login_id,b.total_war_charges,a.quote_no,a.debit_note_no,nvl(g.first_name,g.company_name),b.transit_from,b.final_destination,b.total_sum_insured,b.exchange_rate,a.login_id,nvl(mp.bank_name,' '),nvl(mp.carrier_name,' '),a.excess_premium,b.CURRENCY_NAME,a.product_id,b.TOTAL_SALE_TERM_CHARGES,b.transit_from_city_id,b.transit_from_country_id,b.final_destination_city_id,b.final_Destination_country_id,to_char(b.INCEPTION_DATE,'dd-MM-yyyy') from position_master a, marine_data b, cover_master d, sale_term_master e, extra_cover_master f, personal_info g, mode_of_transport h,marine_policy_details mp where b.cover_id=d.cover_id and b.sale_term_id=e.sale_term_id and f.extra_cover_id=b.extra_cover_id and (d.branch_code in('"
					+ branchCode
					+ "') and e.branch_code in('"
					+ branchCode
					+ "') and f.branch_code in('"
					+ branchCode
					+ "') and h.branch_code in('"
					+ branchCode
					+ "'))and a.application_no=b.application_no and  g.customer_id=a.customer_id and b.mode_of_transport=h.mode_transport_id and (a.inception_date between to_date('"
					+ startdate
					+ "','dd-mm-yyyy') and to_date('"
					+ enddate
					+ "','dd-mm-yyyy')+1 ) and g.application_id='1' and (b.AC_EXECUTIVE_ID='"
					+ values123[0][0]
					+ "' or a.login_id in ("
					+ loginList
					+ ")) and  mp.quote_no=a.quote_no and a.status='P' and a.product_id='"
					+ pid
					+ "' and a.DEBIT_NOTE_NO is not null order by a.inception_date desc";
		}

		try {
			returnval = runner.multipleSelection(qry);
		} catch (Exception e) {
			System.out
					.println("Exception in getiing report dats by using date...5"
							+ e.toString());
			e.printStackTrace();
		}
		return returnval;
	}

	public HashMap checkingvalues123(String data1, String data2, String pid) {
		String addeddata = "";
		if (pid.equals("all")) {
			addeddata = "";
		} else {
			addeddata = "and  a.product_id='" + pid + "'";

		}
		String startdate = data1;
		String enddate = data2;
		System.out.println("startdate is" + startdate);
		System.out.println("enddate is" + enddate);
		String brokerIds = "";

		String returnval[][] = new String[0][0];
		String values[][] = new String[0][0];
		System.out.println("not callint");
		String qry = "";
		qry = "select a.login_id,a.agency_code,a.oa_code,b.company_name from login_master a,broker_company_master b  where a.usertype='Broker' and a.login_id!='NON' and a.agency_code=b.agency_code and login_id!='NONE' and a.oa_code is not null and a.agency_code is not null order by lower(b.company_name)";
		try {
			values = runner.multipleSelection(qry);
			brokerLoginIds = values;
		} catch (Exception e) {
			System.out
					.println("Exception in getiing report dats by using date..6"
							+ e.toString());
			e.printStackTrace();
		}
		setBrokerLoginIds(values);
		java.util.HashMap brokerData = new java.util.HashMap();
		java.util.HashMap lids = new java.util.HashMap();
		try {
			int p = 0;
			for (int i = 0; i < values.length; i++) {

				qry = "select count(a.POLICY_NO),sum(a.premium+nvl(a.Excess_Premium,0)),sum(nvl(a.commission,'0')) from position_master a, marine_data b, cover_master d, sale_term_master e, extra_cover_master f, personal_info g, mode_of_transport h,marine_policy_details mp where b.cover_id=d.cover_id and b.sale_term_id=e.sale_term_id and f.extra_cover_id=b.extra_cover_id and a.application_no=b.application_no and g.customer_id=a.customer_id and mp.quote_no=a.quote_no  and  b.mode_of_transport=h.mode_transport_id and a.DEBIT_NOTE_NO!='0' and  a.login_id in(select login_id from login_master where oa_Code=(select oa_code from login_master where login_id='"
						+ values[i][0]
						+ "'))   and  a.inception_date between to_date('"
						+ startdate
						+ "','dd-mm-yyyy') and to_date('"
						+ enddate
						+ "','dd-mm-yyyy')+1    "
						+ addeddata
						+ " and a.status='P'";
				returnval = runner.multipleSelection(qry);
				if (!returnval[0][0].equals("0")) {
					brokerData.put("Broker" + (p + 1), returnval);
					lids.put("IDS" + (p + 1), values[i][0]);
					p++;
				}
			}
		} catch (Exception e) {
			System.out
					.println("Exception in getiing report dats by using date...7"
							+ e.toString());
			e.printStackTrace();
		}
		brokerData.put("values", values);
		brokerData.put("lids", lids);
		return brokerData;
	}

	// USed
	public HashMap checkingvalues123(String data1, String data2, String pid,
			String branchCode, String brokerCodes, String freightStatus) { // Tax-4,BrokerCodes
		// Restriction
		// report123.jsp
		String addeddata = "";
		String syntax = "";
		if (pid.equals("all")) {
			addeddata = "";
		} else {
			// addeddata="and a.product_id='"+pid+"'";
			addeddata = "and a.product_id in(" + pid + ")";
		}

		if (brokerCodes.equalsIgnoreCase("") || brokerCodes.length() == 0) {
			syntax = "select agency_code from broker_company_master where branch_code in("
					+ branchCode + ")";
		} else {
			brokerCodes = brokerCodes.replaceAll(",", "','");
			syntax = "'" + brokerCodes + "'";
		}
		String freightCheck = "";
		if (freightStatus.equalsIgnoreCase("Yes")) {
			freightCheck = "and a.FREIGHT_STATUS='F'";
		} else if (pid.equals("all")) {
			freightCheck = "";
		} else if (pid.indexOf(",") != -1) {
			freightCheck = "";
		} else {
			freightCheck = "and a.FREIGHT_STATUS is null";
		}
		String startdate = data1;
		String enddate = data2;
		String brokerIds = "";
		String returnval[][] = new String[0][0];
		String values[][] = new String[0][0];
		System.out.println("not callint");
		String qry = "";

		qry = "select a.login_id,a.agency_code,a.oa_code,b.company_name from login_master a,broker_company_master b  where a.usertype='Broker' and a.login_id!='NON' and a.agency_code=b.agency_code and login_id!='NONE' and a.oa_code is not null and a.agency_code is not null  and a.login_id in(select login_id from login_master where oa_code in("
				+ syntax + ")) order by lower(b.company_name)";
		try {
			values = runner.multipleSelection(qry);
			brokerLoginIds = values;
		} catch (Exception e) {
			System.out
					.println("Exception in getiing report dats by using date..8"
							+ e.toString());
			e.printStackTrace();
		}
		setBrokerLoginIds(values);
		java.util.HashMap brokerData = new java.util.HashMap();
		java.util.HashMap lids = new java.util.HashMap();
		try {
			int p = 0;
			for (int i = 0; i < values.length; i++) {

				qry = "select count(a.POLICY_NO),sum(a.premium+nvl(a.Excess_Premium,0)),sum(nvl(a.commission,'0')),sum(nvl(a.PRO_COMMISSION,'0')),sum(nvl(a.tax,'0')) from position_master a, marine_data b, cover_master d, sale_term_master e, extra_cover_master f, personal_info g, mode_of_transport h,marine_policy_details mp where b.cover_id=d.cover_id and b.sale_term_id=e.sale_term_id and f.extra_cover_id=b.extra_cover_id  and (d.branch_code in("
						+ branchCode
						+ ") and e.branch_code in("
						+ branchCode
						+ ") and f.branch_code in("
						+ branchCode
						+ ") and h.branch_code in("
						+ branchCode
						+ "))and a.application_no=b.application_no and g.customer_id=a.customer_id and mp.quote_no=a.quote_no  and  b.mode_of_transport=h.mode_transport_id and a.DEBIT_NOTE_NO!='0' and  a.login_id in(select login_id from login_master where oa_Code=(select oa_code from login_master where login_id='"
						+ values[i][0]
						+ "'))   and  a.inception_date between to_date('"
						+ startdate
						+ "','dd-mm-yyyy') and to_date('"
						+ enddate
						+ "','dd-mm-yyyy')+1    "
						+ addeddata
						+ " and a.status='P' and a.login_id in(select login_id from login_master where oa_code in("
						+ syntax + ")) " + freightCheck + "";
				returnval = runner.multipleSelection(qry);
				if (!returnval[0][0].equals("0")) {
					brokerData.put("Broker" + (p + 1), returnval);
					lids.put("IDS" + (p + 1), values[i][0]);
					p++;
				}
			}
		} catch (Exception e) {
			System.out
					.println("Exception in getiing report dats by using date...9"
							+ e.toString());
			e.printStackTrace();
		}
		brokerData.put("values", values);
		brokerData.put("lids", lids);
		return brokerData;
	}

	// currently used in admin side for Reports
	public String[][] checkingvalues(String data1, String data2,
			String loginId, String pid, String branchCode, String freightStatus,String actualBranch, String RSAIssuer) { // tax-37
		// in
		// report1.jsp
		String addeddata = "", qry ="";
		if (pid.equals("all")) {
			addeddata = "";
		} else {
			// addeddata="and a.product_id='"+pid+"'";
			addeddata = "and  a.product_id in(" + pid + ")";
		}
		String freightCheck = "";
		if (freightStatus.equalsIgnoreCase("Yes")) {
			freightCheck = "and a.FREIGHT_STATUS='F'";
		} else if (pid.equals("all")) {
			freightCheck = "";
		} else if (pid.indexOf(",") != -1) {
			freightCheck = "";
		} else {
			freightCheck = "and a.FREIGHT_STATUS is null";
		}
		String startdate = data1;
		String enddate = data2;
		String returnval[][] = new String[0][0];
		if(RSAIssuer.equalsIgnoreCase("")){
				qry = "select a.policy_no,to_char(a.inception_date,'dd-MM-yyyy') as InceptioDate, b.mode_of_transport,h.transport_description,b.cover_id,d.cover_name, b.sale_term_id, e.sale_term_name,b.extra_cover_id, f.extra_cover_name,  b.total_sum_insured as TotalSI, a.premium ,nvl(a.commission,'0') as commission, a.remarks, a.login_id,b.total_war_charges,a.quote_no,a.debit_note_no,nvl(g.first_name,g.company_name) as CustName,b.transit_from,b.final_destination,b.total_sum_insured,b.exchange_rate as Exchange,a.login_id as loginId,nvl(mp.bank_name,' ') as BankName,nvl(mp.carrier_name,' ') as CarrierName,a.excess_premium,b.CURRENCY_NAME,a.product_id,b.TOTAL_SALE_TERM_CHARGES,b.transit_from_city_id,b.transit_from_country_id,b.final_destination_city_id,b.final_Destination_country_id, to_char(b.INCEPTION_DATE,'dd-MM-yyyy'),b.EXCHANGE_RATE,nvl(a.PRO_COMMISSION,'0') as ProCommission,nvl(a.tax,'0') as PremiumTax,nvl(a.application_id,'1') as ApplicationId  from position_master a, marine_data b, cover_master d, sale_term_master e, extra_cover_master f, personal_info g, mode_of_transport h,marine_policy_details mp where  b.cover_id=d.cover_id and b.sale_term_id=e.sale_term_id and f.extra_cover_id=b.extra_cover_id and (d.branch_code in("
				+ branchCode
				+ ") and e.branch_code in("
				+ branchCode
				+ ") and f.branch_code in("
				+ branchCode
				+ ") and h.branch_code in("
				+ branchCode
				+ ")) and  a.application_no=b.application_no and a.DEBIT_NOTE_NO!='0' and  g.customer_id=a.customer_id   and b.mode_of_transport=h.mode_transport_id and (a.inception_date between to_date('"
				+ startdate
				+ "','dd-mm-yyyy') and to_date('"
				+ enddate
				+ "','dd-mm-yyyy')+1 ) and g.application_id='1' and g.application_id = a.application_id and a.login_id in (select login_id from login_master where oa_code in(select agency_code from broker_company_master where branch_code='"
				+ actualBranch
				+ "' and agency_code in (select oa_code from login_master where oa_code=agency_code and login_id='"
				+ loginId
				+ "'))) and  mp.quote_no=a.quote_no  "
				+ addeddata
				+ " and a.status='P' "
				+ freightCheck
				+ " order by a.inception_date desc";
		} else {
			if(RSAIssuer.equalsIgnoreCase("all"))
				RSAIssuer = loginId;
			qry = "select a.policy_no,to_char(a.inception_date,'dd-MM-yyyy') as InceptioDate, b.mode_of_transport," +
					"h.transport_description,b.cover_id,d.cover_name, b.sale_term_id, e.sale_term_name,b.extra_cover_id, f.extra_cover_name,  b.total_sum_insured as TotalSI, " +
					"a.premium ,nvl(a.commission,'0') as commission, a.remarks, a.login_id,b.total_war_charges,a.quote_no,a.debit_note_no,nvl(g.first_name,g.company_name) as CustName," +
					"b.transit_from,b.final_destination,b.total_sum_insured,b.exchange_rate as Exchange,a.login_id as loginId,nvl(mp.bank_name,' ') as BankName," +
					"nvl(mp.carrier_name,' ') as CarrierName,a.excess_premium,b.CURRENCY_NAME,a.product_id,b.TOTAL_SALE_TERM_CHARGES,b.transit_from_city_id," +
					"b.transit_from_country_id,b.final_destination_city_id,b.final_Destination_country_id, to_char(b.INCEPTION_DATE,'dd-MM-yyyy'),b.EXCHANGE_RATE," +
					"nvl(a.PRO_COMMISSION,'0') as ProCommission,nvl(a.tax,'0') as PremiumTax,nvl(a.application_id,'1') as ApplicationId  from position_master a, " +
					"marine_data b, cover_master d, sale_term_master e, extra_cover_master f, personal_info g, mode_of_transport h,marine_policy_details mp " +
					" ,login_master LOG,broker_company_master bcm  where  b.cover_id=d.cover_id and b.sale_term_id=e.sale_term_id and f.extra_cover_id=b.extra_cover_id and (d.branch_code in("
					+ branchCode + ") and e.branch_code in(" + branchCode
					+ ") and f.branch_code in("
					+ branchCode
					+ ") and h.branch_code in("
					+ branchCode
					+ ")) and  a.application_no=b.application_no AND a.login_id = LOG.login_id AND LOG.oa_code = bcm.agency_code and  g.customer_id=a.customer_id   and b.mode_of_transport=h.mode_transport_id and (a.inception_date between to_date('"
					+ startdate
					+ "','dd-mm-yyyy') and to_date('"
					+ enddate
					+ "','dd-mm-yyyy')+1 ) and a.application_id= '" + RSAIssuer + "' and  mp.quote_no=a.quote_no  "
					+ addeddata
					+ " and a.status='P' "
					+ freightCheck
					+ " order by a.inception_date desc";
		}
		try {
			returnval = runner.multipleSelection(qry);
			System.out.println("print qry " + qry);
		} catch (Exception e) {
			System.out
					.println("Exception in getiing report dats by using date...10"
							+ e.toString());
			e.printStackTrace();
		}
		return returnval;
	}

	public String fromCityAdmin(String frmcty, String frmcntry, String other) {
		String fcity = "";
		String fcntry = "";
		String args[] = new String[0];
		try {
			if (other.trim().equalsIgnoreCase("noother")) {
				args = new String[1];
				args[0] = frmcty;
				String qry = "select initcap(city_name) from city_master where  city_id=?";
				fcity = runner.singleSelection(qry, args);
			} else {
				fcity = other;
			}
			if (frmcntry.length() > 0) {
				try {
					args = new String[2];
					args[0] = frmcntry;
					args[1] = frmcntry;
					String qry = "select initcap(country_name) from country_master where  country_id=? and  amend_id=(select max(amend_id) from country_master where country_id=?)";
					fcntry = runner.singleSelection(qry, args);
				} catch (Exception e) {
					System.out
							.println("Exception in  by city and country id in reports"
									+ e.toString());
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String tr = "";
		if (fcity.trim().length() > 0) {
			tr = fcntry + "(" + fcity + ")";
		} else {
			tr = fcntry;
		}
		return tr;
	}

	public String fromCity(String frmcty, String frmcntry, String other) {
		String fcity = "";
		String fcntry = "";
		String args[] = new String[0];
		try {
			if (other.trim().equalsIgnoreCase("noother")) {
				args = new String[1];
				args[0] = frmcty;
				String qry = "select initcap(city_name) from city_master where city_id=?";
				fcity = runner.singleSelection(qry, args);
			} else {
				fcity = other;
			}
			if (frmcntry.length() > 0) {
				try {
					args = new String[2];
					args[0] = frmcntry;
					args[1] = frmcntry;
					String qry = "select initcap(country_name) from country_master where  country_id=? and  amend_id=(select max(amend_id) from country_master where country_id=?)";
					fcntry = runner.singleSelection(qry, args);
				} catch (Exception e) {
					System.out
							.println("Exception in  by city and country id in reports"
									+ e.toString());
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String tr = "";
		if (fcity.trim().length() > 0) {
			tr = fcntry + "(" + fcity + ")";
		} else {
			tr = fcntry;
		}
		return tr;
	}

	public String[][] policyvalues123(String data3) {
		String policyno = data3;
		String policyval[][] = new String[0][0];
		String args[] = new String[1];
		String qry = "";
		try {
			args[0] = policyno + "%";
			qry = "select a.policy_no,to_char(a.inception_date,'dd-MM-yyyy'), b.mode_of_transport,h.transport_description,b.cover_id, d.cover_name, b.sale_term_id, e.sale_term_name,b.extra_cover_id, f.extra_cover_name,  b.total_sum_insured,m.AMOUNT_PAYABLE , c.commission, a.remarks, g.login_id  from position_master a, marine_data b, cover_master d, sale_term_master e, extra_cover_master f, personal_info g, mode_of_transport h,login_user_details c,marine_result m where b.cover_id=d.cover_id and b.sale_term_id=e.sale_term_id and b.extra_cover_id=f.extra_cover_id and a.application_no=b.application_no and  m.application_no=b.application_no and g.customer_id=a.customer_id and a.policy_no like ? and b.mode_of_transport=h.mode_transport_id  and c.login_id=g.login_id and g.application_id='1' and a.policy_no is not null";

			policyval = runner.multipleSelection(qry, args);
		} catch (Exception e) {
			System.out.println("Exception in  by policy in reports"
					+ e.toString());
			e.printStackTrace();
		}
		return policyval;
	}

	public String[][] policyvalues(String data3) {
		String policyno = data3;
		String policyval[][] = new String[0][0];
		String qry = "";
		String args[] = new String[1];
		try {
			args[0] = policyno + "%";
			qry = "select a.policy_no,to_char(a.inception_date,'dd-MM-yyyy'), b.mode_of_transport,h.transport_description,b.cover_id, d.cover_name, b.sale_term_id, e.sale_term_name,b.extra_cover_id, f.extra_cover_name,  b.total_sum_insured,m.AMOUNT_PAYABLE , c.commission, a.remarks, g.login_id  from position_master a, marine_data b, cover_master d, sale_term_master e, extra_cover_master f, personal_info g, mode_of_transport h,login_user_details c,marine_result m where b.cover_id=d.cover_id and b.sale_term_id=e.sale_term_id and b.extra_cover_id=f.extra_cover_id and a.application_no=b.application_no and  m.application_no=b.application_no and g.customer_id=a.customer_id and a.policy_no like ? and b.mode_of_transport=h.mode_transport_id  and c.login_id=g.login_id and g.application_id='1' and a.policy_no is not null";

			policyval = runner.multipleSelection(qry, args);
		} catch (Exception e) {
			System.out.println("Exception in  by policy in reports"
					+ e.toString());
			e.printStackTrace();
		}
		return policyval;
	}

	public String[][] customervalues(String data3) {
		String customername = data3;
		String customerval[][] = new String[0][0];
		String qry = "";
		String args[] = new String[1];
		try {
			args[0] = customername + "%";
			qry = "select a.policy_no,to_char(a.inception_date,'dd-MM-yyyy'), b.mode_of_transport,h.transport_description,b.cover_id, d.cover_name, b.sale_term_id, e.sale_term_name,b.extra_cover_id, f.extra_cover_name,  b.total_sum_insured, m.AMOUNT_PAYABLE , c.commission, a.remarks, g.login_id  from position_master a, marine_data b, cover_master d, sale_term_master e, extra_cover_master f, personal_info g, mode_of_transport h,login_user_details c,marine_result m where b.cover_id=d.cover_id and b.sale_term_id=e.sale_term_id and b.extra_cover_id=f.extra_cover_id and a.application_no=b.application_no and m.application_no=b.application_no and g.customer_id=a.customer_id and g.first_name like ? and b.mode_of_transport=h.mode_transport_id  and c.login_id=g.login_id and g.application_id='1' and a.policy_no is not null";

			customerval = runner.multipleSelection(qry);
		} catch (Exception e) {
			System.out.println("Exception in by custemoer reports"
					+ e.toString());
			e.printStackTrace();
		}
		return customerval;
	}

	public String validateTrashFields() {
		com.maan.services.util.dataCollection data = new com.maan.services.util.dataCollection();
		String error = "";
		String values = null;
		try {
			if ("p".equals(rep) || "c".equals(rep)) {
				values = data
						.checkDate(dobDay + "/" + dobMonth + "/" + dobYear);
				if ("Invalid".equalsIgnoreCase(values)) {
					error = error + "<br>*" + runner.getErrormsg("62");

				}
				values = data.checkDate(dobDay1 + "/" + dobMonth1 + "/"
						+ dobYear1);
				if ("Invalid".equalsIgnoreCase(values))
					error = error + "<br>*" + runner.getErrormsg("63");
				else if (Integer.parseInt(dobYear1) < Integer.parseInt(dobYear)) {
					error = error + "<br>*" + runner.getErrormsg("72");
				} else if (Integer.parseInt(dobYear1) == Integer
						.parseInt(dobYear)) {
					if (Integer.parseInt(dobMonth1) < Integer
							.parseInt(dobMonth)) {
						error = error + "<br>*" + runner.getErrormsg("72");
					} else if (Integer.parseInt(dobMonth1) == Integer
							.parseInt(dobMonth)) {
						if (Integer.parseInt(dobDay1) < Integer
								.parseInt(dobDay)) {
							error = error + "<br>*" + runner.getErrormsg("72");
						}
					}
				}
				SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH);
            	sf.setTimeZone(new java.util.SimpleTimeZone(14400000,"GMT"));           	
            	
				String s = sf.format(new java.util.Date());
				
				String enteredDate1 = dobDay1 + "/" + dobMonth1 + "/"
						+ dobYear1;
				String enteredDate = dobDay + "/" + dobMonth + "/" + dobYear;
				long diff1 = 0;
				long diff = 0;
				try {
					DateFunction dbr = new DateFunction();
					diff1 = dbr.getDayDifference(dbr.getCalendar(s), dbr
							.getCalendar(enteredDate1));
					diff = dbr.getDayDifference(dbr.getCalendar(s), dbr
							.getCalendar(enteredDate));
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (diff > 0) {
					error = error + "<br>*" + runner.getErrormsg("70");
				}
				if (diff1 > 0) {
					// error=error+"<br>*"+runner.getErrormsg("71");
				}
			}
		} catch (Exception e) {
			System.out.println("Exception in " + e.toString());
			e.printStackTrace();
		}
		return error;
	}

	public String validateFields(String reqFrom) {
		com.maan.services.util.dataCollection data = new com.maan.services.util.dataCollection();
		String error = "";
		String values = null;
		try {
			if ("".equals(rep)) {
				values = data.validString(company, false);
				if ("needed".equalsIgnoreCase(values))
					error = error + "<br>*" + runner.getErrormsg("3");
			}
			if ("p".equals(rep)) {
				values = data.validString(company, false);
				if ("needed".equalsIgnoreCase(values))
					error = error + "<br>*" + runner.getErrormsg("68");
			} else if ("c".equals(rep)) {
				values = data.validString(company, false);
				if ("needed".equalsIgnoreCase(values))
					error = error + "<br>*" + runner.getErrormsg("67");
			}
			if ("d".equals(rep)) {
				values = data.checkDate(dobDay + "/" + dobMonth + "/" + dobYear);
				if ("Invalid".equalsIgnoreCase(values))
					error = error + "<br>*" + runner.getErrormsg("62");
				values = data.checkDate(dobDay1 + "/" + dobMonth1 + "/"
						+ dobYear1);
				if ("Invalid".equalsIgnoreCase(values))
					error = error + "<br>*" + runner.getErrormsg("63");
				else if (Integer.parseInt(dobYear1) < Integer.parseInt(dobYear)) {
					error = error + "<br>*" + runner.getErrormsg("72");
				} else if (Integer.parseInt(dobYear1) == Integer
						.parseInt(dobYear)) {
					if (Integer.parseInt(dobMonth1) < Integer
							.parseInt(dobMonth)) {
						error = error + "<br>*" + runner.getErrormsg("72");
					} else if (Integer.parseInt(dobMonth1) == Integer
							.parseInt(dobMonth)) {
						if (Integer.parseInt(dobDay1) < Integer
								.parseInt(dobDay)) {
							error = error + "<br>*" + runner.getErrormsg("72");
						}
					}
				}
				String s = new SimpleDateFormat("dd/MM/yyyy")
						.format(new java.util.Date());
				String enteredDate1 = dobDay1 + "/" + dobMonth1 + "/"
						+ dobYear1;
				String enteredDate = dobDay + "/" + dobMonth + "/" + dobYear;
				long diff1 = 0;
				long diff = 0;
				try {
					s = runner.singleSelection("select to_char(sysdate+8/24,'dd/MM/YYYY') from dual");
					DateFunction dbr = new DateFunction();
					if (s.length() > 0) {
						diff1 = dbr.getDayDifference(dbr.getCalendar(s), dbr
								.getCalendar(enteredDate1));
						diff = dbr.getDayDifference(dbr.getCalendar(s), dbr
								.getCalendar(enteredDate));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				if (diff > 0) {
					error = error + "<br>*" + runner.getErrormsg("70");
				}
				if (userType.length() <= 0 && reqFrom.equalsIgnoreCase("reports")) {
					error = error + "<br>Please, Select Usertype.";
				}
			}
		} catch (Exception e) {
			System.out.println("Exception in " + e.toString());
			e.printStackTrace();
		}
		return error;
	}

	public String[][] getBrokerksByAdmin() {
		String[][] brokers = new String[0][0];
		String sql = "";
		try {
			sql = "select lm.login_id,pi.first_name from login_master lm,personal_info pi where pi.login_id=lm.login_id and lm.usertype='Broker' and pi.application_id='2' order by pi.first_name";
			brokers = runner.multipleSelection(sql);
		} catch (Exception e) {
			System.out.println("Exception in by custemoer reports"
					+ e.toString());
			e.printStackTrace();
		}
		return brokers;
	}

	public HashMap TrashToAdmin(String data1, String data2, String status) {
		String startdate = data1;
		String enddate = data2;
		String brokerIds = "";
		String returnval[][] = new String[0][0];
		String values[][] = new String[0][0];
		String qry = "";

		qry = "select login_id,agency_code,oa_code from login_master where usertype='Broker' and oa_code is not null and agency_code is not null and login_id not in('NON','NONE')";
		try {
			values = runner.multipleSelection(qry);
			brokerLoginIds = values;
		} catch (Exception e) {
			System.out
					.println("Exception in getiing report dats by using date..11"
							+ e.toString());
			e.printStackTrace();
		}
		if (status.equalsIgnoreCase("p")) {
			status = "p";
		} else {
			status = "'p','d','r'";
		}
		setBrokerLoginIds(values);
		java.util.HashMap brokerData = new java.util.HashMap();
		try {
			for (int i = 0; i < values.length; i++) {
				if (status.equalsIgnoreCase("p")) {
					qry = "select a.login_id,count(b.POLICY_NO),sum(b.premium),sum(nvl(b.commission,'0')) from personal_info a,position_master b,marine_result c,login_user_details d where a.CUSTOMER_ID=b.CUSTOMER_ID and b.login_id=d.login_id and b.login_id=a.login_id  and b.application_no=c.application_no  and a.LOGIN_ID='"
							+ values[i][0]
							+ "' and b.entry_date between to_date('"
							+ startdate
							+ "','dd-mm-yyyy') and to_date('"
							+ enddate
							+ "','dd-mm-yyyy')+1 and  lower(b.status)='"
							+ status.toLowerCase() + "' group by a.login_id";
				} else {
					qry = "select a.login_id,count(b.quote_No),sum(md.premium),sum(nvl(b.commission,'0')) from personal_info a,position_master b,marine_result c,login_user_details d,marine_data md where a.CUSTOMER_ID=b.CUSTOMER_ID and b.login_id=d.login_id and b.login_id=a.login_id  and b.application_no=c.application_no and b.application_no=md.application_no  and a.LOGIN_ID='"
							+ values[i][0]
							+ "' and b.entry_date between to_date('"
							+ startdate
							+ "','dd-mm-yyyy') and to_date('"
							+ enddate
							+ "','dd-mm-yyyy')+1 and  lower(b.status) not in("
							+ status + ") group by a.login_id";

				}
				returnval = runner.multipleSelection(qry);
				brokerData.put("Broker" + values[i][0], returnval);
			}
		} catch (Exception e) {
			System.out
					.println("Exception in getiing report dats by using date..12"
							+ e.toString());
			e.printStackTrace();
		}
		brokerData.put("values", values);
		return brokerData;
	}

	public String[][] getLogIds(String loginIds) {
		String[][] valuess = new String[0][0];
		String sql = "";
		String args[] = new String[3];
		try {
			args[0] = loginIds;
			args[1] = loginIds;
			args[2] = loginIds;
			sql = "select login_id from login_master where (oa_code=(select agency_code from login_master where login_id=?) or created_by=(select agency_code from login_master where login_id=?) or login_id=?) and login_id not in('NONE','NON')";
			valuess = runner.multipleSelection(sql, args);
		} catch (Exception e) {
			System.out.println("Exception in>>>" + e.toString());
			e.printStackTrace();
		}
		return valuess;
	}

	public String getBrokerCompanyName123(String loginId) {
		String args[] = new String[1];
		String pname = "";
		String sql = "";
		try {
			args[0] = loginId;
			sql = "select COMPANY_NAME from broker_company_master where agency_code=(select oa_code from login_master where login_id=?)";
			pname = runner.singleSelection(sql, args);
		} catch (Exception e) {
			System.out.println("Exception in getting pname" + e.toString());
			e.printStackTrace();
		}
		return pname;
	}

	public String getBrokerCompanyName(String loginId) {
		String sql = "";
		String pname = "";
		String args[] = new String[1];
		try {
			args[0] = loginId;
			sql = "select COMPANY_NAME from broker_company_master where agency_code=(select agency_code from login_master where login_id=?)";
			pname = runner.singleSelection(sql, args);

		} catch (Exception e) {
			System.out.println("Exception in getting pname" + e.toString());
			e.printStackTrace();
		}
		return pname;
	}

	public String[][] getAReferal(String appNo) {
		String[][] valuess = new String[0][0];
		String sql = "";
		String args[] = new String[1];
		try {
			args[0] = appNo;
			sql = "select a.ADMIN_REFERRAL_STATUS,b.remarks,a.remarks,a.REFERAL_STATUS from marine_data a,position_master b where a.application_no=b.application_no and b.application_no=?";
			valuess = runner.multipleSelection(sql, args);
		} catch (Exception e) {
			System.out.println("Exception in>>" + e.toString());
			e.printStackTrace();
		}
		return valuess;
	}

	public String[][] getAllBrokers(String branchCode) {
		String[][] getAllBrokers = null;
		String sql = "";
		try {
			sql = "select bcm.company_name,pi.login_id,bcm.agency_code from BROKER_COMPANY_MASTER bcm,personal_info pi where bcm.agency_code=pi.agency_code  and bcm.status='Y' and pi.status=bcm.status and APPLICATION_ID='2' and pi.login_id in(select login_id from login_master where oa_code in(select agency_code from broker_company_master where branch_code in("
					+ branchCode + "))) order by lower(bcm.COMPANY_NAME)";
			getAllBrokers = runner.multipleSelection(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getAllBrokers;
	}

	public String[][] getAllBrokers(String branchCode, String brokerCodes, String userType) // PolicyReport.jsp
	// BrokerCodes
	// Restriction
	{
		String[][] getAllBrokers = new String[0][0];
		String sql = "";
		String syntax = "";
		if(userType.equalsIgnoreCase("RSAIssuer")){
			sql = "select login_id, login_id, username from login_master where usertype = 'RSAIssuer' and status = 'Y'";	
		} else if (userType.equalsIgnoreCase("Broker")) {
			if (brokerCodes.equalsIgnoreCase("") || brokerCodes.length() == 0) {
				syntax = "select agency_code from broker_company_master where branch_code in("
						+ branchCode + ")";
			} else {
				brokerCodes = brokerCodes.replaceAll(",", "','");
				syntax = "'" + brokerCodes + "'";
			}
			sql = "select bcm.company_name,pi.login_id,bcm.agency_code from LOGIN_MASTER lm, BROKER_COMPANY_MASTER bcm,personal_info pi where bcm.agency_code=pi.agency_code  " +
				  "and bcm.status='Y' AND pi.login_id = lm.login_id AND lm.agency_code = bcm.agency_code AND lm.usertype = 'Broker' and pi.status=bcm.status " +
				  "and APPLICATION_ID='2' and pi.login_id in(select login_id from login_master where oa_code in(" + syntax + 
				  ")) order by lower(bcm.COMPANY_NAME)";
		}
		
		try {
			getAllBrokers = runner.multipleSelection(sql);
		} catch (Exception e) {
			System.out.println("get All Brokers" + e.toString());
			e.printStackTrace();
		}
		return getAllBrokers;
	}

	public static String getDate() {
		Calendar c = Calendar.getInstance();
		String d = "" + c.get(Calendar.DAY_OF_MONTH) + "-"
				+ c.get(Calendar.MONTH) + "-" + c.get(Calendar.YEAR) + "_"
				+ c.get(Calendar.HOUR) + "_" + c.get(Calendar.MINUTE) + "_"
				+ c.get(Calendar.SECOND);

		return d;
	}

	public String[][] getBrokerUserId(String adminBranch) {
		String[][] brokerid = new String[0][0];
		String sql = "";
		String args[] = new String[1];
		try {
			args[0] = adminBranch;
			sql = "select l.login_id,b.company_name from login_master l,BROKER_COMPANY_MASTER b where b.agency_code=l.agency_code and  l.login_id not in ('NON','NONE') and l.status='Y' and l.login_id is not null and b.agency_code is not null and b.branch_code=? order by b.company_name";

			brokerid = runner.multipleSelection(sql, args);
		} catch (Exception e) {
			System.out.println("Exception in getBrokerUserId " + e.toString());
			e.printStackTrace();
		}
		return brokerid;
	}

	public HashMap BrokerCheckingvalues123(String data1, String data2,
			String loginid) {
		String startdate = data1;
		String enddate = data2;
		String brokerIds = "";
		String returnval[][] = new String[0][0];
		String values[][] = new String[0][0];
		String values123[][] = new String[0][0];
		String qry = "";
		String args[] = new String[0];

		try {
			args = new String[1];
			args[0] = loginid;
			qry = "select login_id,agency_code,oa_code,usertype from login_master where  oa_Code in(select oa_code from login_master where login_id=? and login_id not in ('NONE','NON')) and login_id not in ('NONE','NON') and oa_code is not null and agency_code is not null order by login_id";
			values = runner.multipleSelection(qry, args);
			brokerLoginIds = values;
		} catch (Exception e) {
			System.out
					.println("Exception in getiing report dats by using date..13"
							+ e.toString());
			e.printStackTrace();
		}
		setBrokerLoginIds(values);
		java.util.HashMap brokerData = new java.util.HashMap();
		java.util.HashMap lids = new java.util.HashMap();
		try {
			int p = 0;
			for (int i = 0; i < values.length; i++) {
				if (!values[i][3].equalsIgnoreCase("AccountEx")) {
					qry = "select count(a.POLICY_NO),sum(a.premium+nvl(a.Excess_Premium,0)),sum(nvl(a.commission,'0')) from position_master a, marine_data b, cover_master d, sale_term_master e, extra_cover_master f, personal_info g, mode_of_transport h,marine_policy_details mp where b.cover_id=d.cover_id and b.sale_term_id=e.sale_term_id and f.extra_cover_id=b.extra_cover_id and a.application_no=b.application_no and g.customer_id=a.customer_id and mp.quote_no=a.quote_no  and  b.mode_of_transport=h.mode_transport_id and a.DEBIT_NOTE_NO!='0' and  a.login_id in('"
							+ values[i][0]
							+ "')  and  a.inception_date between to_date('"
							+ startdate
							+ "','dd-mm-yyyy') and to_date('"
							+ enddate
							+ "','dd-mm-yyyy')+1 and  lower(a.status)='p'";
				} else {
					args = new String[1];
					args[0] = values[i][1];
					String qry12 = "select AC_EXECUTIVE_ID,AC_EXECUTIVE_NAME,AGENCY_CODE from LOGIN_EXECUTIVE_DETAILS where agency_code=?";

					try {
						values123 = runner.multipleSelection(qry12);
					} catch (Exception e) {
						System.out
								.println("Exception in getiing report dats by using date..14>>>>>"
										+ e.toString());
						e.printStackTrace();
					}
					qry = "select count(a.POLICY_NO),sum(a.premium+nvl(a.Excess_Premium,0)),sum(nvl(a.commission,'0')) from position_master a, marine_data b, cover_master d, sale_term_master e, extra_cover_master f, personal_info g, mode_of_transport h,marine_policy_details mp where b.cover_id=d.cover_id and b.sale_term_id=e.sale_term_id and f.extra_cover_id=b.extra_cover_id and a.application_no=b.application_no and g.customer_id=a.customer_id and mp.quote_no=a.quote_no  and  b.mode_of_transport=h.mode_transport_id and a.DEBIT_NOTE_NO!='0' and  (a.login_id in ('"
							+ values[i][0]
							+ "') or b.AC_EXECUTIVE_ID in ('"
							+ values123[0][0]
							+ "'))  and  a.inception_date between to_date('"
							+ startdate
							+ "','dd-mm-yyyy') and to_date('"
							+ enddate
							+ "','dd-mm-yyyy')+1 and  lower(a.status)='p'";
				}
				returnval = runner.multipleSelection(qry);
				if (!returnval[0][0].equals("0")) {
					brokerData.put("Broker" + (p + 1), returnval);
					lids.put("IDS" + (p + 1), values[i][0]);
					p++;
				}
			}
		} catch (Exception e) {
			System.out
					.println("Exception in getiing report dats by using date..15"
							+ e.toString());
			e.printStackTrace();
		}
		brokerData.put("values", values);
		brokerData.put("lids", lids);
		return brokerData;
	}

	public HashMap BrokerCheckingvalues123(String data1, String data2,
			String loginid, String option, String pid) {
		String startdate = data1;
		String enddate = data2;
		String brokerIds = "";
		String returnval[][] = new String[0][0];
		String quotescount[][] = new String[0][0];
		String lapscount[][] = new String[0][0];
		String refcount[][] = new String[0][0];
		String expcount[][] = new String[0][0];
		String values[][] = new String[0][0];
		String values123[][] = new String[0][0];
		String qry = "";
		String args[] = new String[0];
		args = new String[1];
		if (option.equalsIgnoreCase("All")) {
			args[0] = loginid;
			qry = "select login_id,agency_code,oa_code,usertype from login_master where  oa_Code in(select oa_code from login_master where login_id=? and login_id not in ('NONE','NON')) and login_id not in ('NONE','NON') and oa_code is not null and agency_code is not null order by login_id";
		} else if (!option.equalsIgnoreCase("All")) {
			args[0] = loginid;
			qry = "select login_id,agency_code,oa_code,usertype from login_master where  agency_code in(select agency_code from login_master where login_id=? and login_id not in ('NONE','NON')) and login_id not in ('NONE','NON') and oa_code is not null and agency_code is not null order by login_id";
		}

		try {
			values = runner.multipleSelection(qry, args);
			brokerLoginIds = values;
		} catch (Exception e) {
			System.out
					.println("Exception in getiing report dats by using date..13"
							+ e.toString());
			e.printStackTrace();
		}
		setBrokerLoginIds(values);
		java.util.HashMap brokerData = new java.util.HashMap();
		java.util.HashMap lids = new java.util.HashMap();
		try {
			int p = 0;
			for (int i = 0; i < values.length; i++) {
				if (!values[i][3].equalsIgnoreCase("AccountEx")) {
					qry = "select count(a.POLICY_NO),sum(a.premium+nvl(a.Excess_Premium,0)),sum(nvl(a.commission,'0')) from position_master a, marine_data b, cover_master d, sale_term_master e, extra_cover_master f, personal_info g, mode_of_transport h,marine_policy_details mp where b.cover_id=d.cover_id and b.sale_term_id=e.sale_term_id and f.extra_cover_id=b.extra_cover_id and a.application_no=b.application_no and g.customer_id=a.customer_id and mp.quote_no=a.quote_no  and  b.mode_of_transport=h.mode_transport_id and a.DEBIT_NOTE_NO!='0' and  a.login_id in('"
							+ values[i][0]
							+ "')  and  a.inception_date between to_date('"
							+ startdate
							+ "','dd-mm-yyyy') and to_date('"
							+ enddate
							+ "','dd-mm-yyyy')+1 and  lower(a.status)='p' and a.product_id='"
							+ pid + "'";

				} else {
					args[0] = values[i][1];
					String qry12 = "select AC_EXECUTIVE_ID,AC_EXECUTIVE_NAME,AGENCY_CODE from LOGIN_EXECUTIVE_DETAILS where agency_code=?";

					try {
						values123 = runner.multipleSelection(qry12, args);
					} catch (Exception e) {
						System.out
								.println("Exception in getiing report by using date..14>>>>>"
										+ e.toString());
						e.printStackTrace();
					}
					qry = "select count(a.POLICY_NO),sum(a.premium+nvl(a.Excess_Premium,0)),sum(nvl(a.commission,'0')) from position_master a, marine_data b, cover_master d, sale_term_master e, extra_cover_master f, personal_info g, mode_of_transport h,marine_policy_details mp where b.cover_id=d.cover_id and b.sale_term_id=e.sale_term_id and f.extra_cover_id=b.extra_cover_id and a.application_no=b.application_no and g.customer_id=a.customer_id and mp.quote_no=a.quote_no  and  b.mode_of_transport=h.mode_transport_id and a.DEBIT_NOTE_NO!='0' and  (a.login_id in ('"
							+ values[i][0]
							+ "') or b.AC_EXECUTIVE_ID in ('"
							+ values123[0][0]
							+ "'))  and  a.inception_date between to_date('"
							+ startdate
							+ "','dd-mm-yyyy') and to_date('"
							+ enddate
							+ "','dd-mm-yyyy')+1 and  lower(a.status)='p' and  a.product_id='"
							+ pid + "'";
				}
				returnval = runner.multipleSelection(qry);
				if (!returnval[0][0].equals("0")) {
					brokerData.put("Broker" + (p + 1), returnval);
					lids.put("IDS" + (p + 1), values[i][0]);
					p++;
				}
				String quoteQry = "select count(a.quote_no) from position_master a,personal_info b,marine_data m where a.login_id in ('"
						+ values[i][0]
						+ "') and a.effective_date >= (select sysdate from dual) and a.status='Y' and  a.entry_date between to_date('"
						+ startdate
						+ "','dd-mm-yyyy') and to_date('"
						+ enddate
						+ "','dd-mm-yyyy')+1 and (a.remarks not in('Admin','Referal','NORMAL_EXCESS_PRICE')) and m.application_no=a.application_no and  nvl(m.admin_referral_status,'N') not in ('Y') and b.customer_id=a.customer_id and a.product_id='"
						+ pid + "' order by quote_no DESC";

				quotescount = runner.multipleSelection(quoteQry);
				if (quotescount.length > 0) {
					if (!quotescount[0][0].equals("0")) {
						brokerData.put("quotes" + values[i][0],
								quotescount[0][0]);
					} else
						brokerData.put("quotes" + values[i][0], "0");
				} else
					brokerData.put("quotes" + values[i][0], "0");

				String lapseQry = "select count(a.quote_no) from position_master a,personal_info b,marine_data c where c.application_no=a.application_no and a.login_id in ('"
						+ values[i][0]
						+ "') and (a.status in ('D','R')) and b.customer_id=a.customer_id and a.product_id = '"
						+ pid
						+ "' and a.effective_date between to_date('"
						+ startdate
						+ "','dd-mm-yyyy') and to_date('"
						+ enddate
						+ "','dd-mm-yyyy')+1 order by a.quote_no DESC";
				lapscount = runner.multipleSelection(lapseQry);
				if (lapscount.length > 0) {
					if (!lapscount[0][0].equals("0")) {
						brokerData.put("laps" + values[i][0], lapscount[0][0]);
					} else
						brokerData.put("laps" + values[i][0], "0");
				} else
					brokerData.put("laps" + values[i][0], "0");

				String refQry = "select count(a.quote_no) from position_master a,personal_info b,marine_data c where a.login_id in('"
						+ values[i][0]
						+ "') and a.status in('Y') and (a.remarks in('Referal','Admin','Normal')) and b.customer_id=a.customer_id and a.product_id='"
						+ pid
						+ "' and a.entry_date between to_date('"
						+ startdate
						+ "','dd-mm-yyyy') and to_date('"
						+ enddate
						+ "','dd-mm-yyyy')+1 and a.APPLICATION_NO=c.APPLICATION_NO and c.ADMIN_REFERRAL_STATUS='Y' order by quote_no";

				refcount = runner.multipleSelection(refQry);
				if (refcount.length > 0) {
					if (!refcount[0][0].equals("0")) {
						brokerData.put("refs" + values[i][0], refcount[0][0]);
					} else
						brokerData.put("refs" + values[i][0], "0");
				} else
					brokerData.put("refs" + values[i][0], "0");

				String expQry = "select count(a.quote_no)  from position_master a, personal_info b WHERE a.login_id in ('"
						+ values[i][0]
						+ "') and a.effective_date < (select sysdate from dual) and a.customer_id = b.customer_id and a.product_id='"
						+ pid
						+ "' and a.status='Y' and a.entry_date between to_date('"
						+ startdate
						+ "','dd-mm-yyyy') and to_date('"
						+ enddate
						+ "','dd-mm-yyyy')+1";
				expcount = runner.multipleSelection(expQry);
				if (expcount.length > 0) {
					if (!expcount[0][0].equals("0")) {
						brokerData.put("exps" + values[i][0], expcount[0][0]);
					} else
						brokerData.put("exps" + values[i][0], "0");
				} else
					brokerData.put("exps" + values[i][0], "0");
			}
		} catch (Exception e) {
			System.out
					.println("Exception in getiing report dats by using date..15"
							+ e.toString());
			e.printStackTrace();
		}
		brokerData.put("values", values);
		brokerData.put("lids", lids);
		return brokerData;
	}

	public String getNameByAgencyCode(String agcode, String brokerBra) {
		String pname = "";
		int check = 0;
		String sql1 = "";
		String args[] = new String[0];
		String res = "";
		try {
			args = new String[2];
			args[0] = agcode;
			args[1] = brokerBra;
			sql1 = "select count(*) from broker_company_master where agency_code=? and branch_code=?";
			res = runner.singleSelection(sql1, args);
			if (res.length() > 0 && !res.equalsIgnoreCase("null")) {
				check = Integer.parseInt(res);
			} else {
				check = 0;
			}
		} catch (Exception e) {
			System.out.println("Exception in getting pname" + e.toString());
			e.printStackTrace();
		}
		String sql = "";
		if (check > 0) {
			args = new String[2];
			args[0] = agcode;
			args[1] = brokerBra;
			sql = "select COMPANY_NAME from broker_company_master where agency_code=? and branch_code=?";
		} else {
			args = new String[1];
			args[0] = agcode;
			sql = "select first_name from personal_info where agency_code=?";
		}

		try {
			pname = runner.singleSelection(sql, args);
		} catch (Exception e) {
			System.out.println("Exception in getting pname" + e.toString());
			e.printStackTrace();
		}

		return pname;
	}

	public String getNameByLoginId(String agcode, String brokerBra) {
		String pname = "";
		int check = 0;
		String sql1 = "";
		String args[] = new String[0];
		String res = "";
		try {
			String argss[] = new String[1];
			argss[0] = agcode;
			agcode = runner.singleSelection(
					"select agency_code from login_master where login_id=?",
					argss);
			args = new String[2];
			args[0] = agcode;
			args[1] = brokerBra;
			sql1 = "select count(*) from broker_company_master where agency_code=? and branch_code=?";
			res = runner.singleSelection(sql1, args);
			if (res.length() > 0 && !res.equalsIgnoreCase("null")) {
				check = Integer.parseInt(res);
			} else {
				check = 0;
			}
		} catch (Exception e) {
			System.out.println("Exception in getting pname" + e.toString());
			e.printStackTrace();
		}
		String sql = "";
		if (check > 0) {
			args = new String[2];
			args[0] = agcode;
			args[1] = brokerBra;
			sql = "select COMPANY_NAME from broker_company_master where agency_code=? and branch_code=?";
		} else {
			args = new String[1];
			args[0] = agcode;
			sql = "select first_name from personal_info where agency_code=?";
		}

		try {
			pname = runner.singleSelection(sql, args);
		} catch (Exception e) {
			System.out.println("Exception in getting pname" + e.toString());
			e.printStackTrace();
		}

		return pname;
	}

	public String[][] getCommodityDiscrip(String qid) // senthil ---
	{
		String[][] commodity = new String[0][0];
		String sql = "";
		String args[] = new String[1];
		try {
			args[0] = qid;
			sql = "select application_no,commodity_id,description,commodity_type_id,WARRATE,RATE from marine_result_details  where application_no=(select application_no from position_master where quote_no=?)";
			commodity = runner.multipleSelection(sql, args);
		} catch (Exception e) {
			System.out.println("Exception in getting customer info"
					+ e.toString());
			e.printStackTrace();
		}
		for (int i = 0; i < commodity.length; i++) {
			commodity[i][0] = commodity[i][0] == null ? "" : commodity[i][0];
			commodity[i][1] = commodity[i][1] == null ? "" : commodity[i][1];
			commodity[i][2] = commodity[i][2] == null ? "" : commodity[i][2];
			commodity[i][3] = commodity[i][3] == null ? "" : commodity[i][3];
			commodity[i][4] = commodity[i][4] == null ? "" : commodity[i][4];
			commodity[i][5] = commodity[i][5] == null ? "" : commodity[i][5];
		}
		return commodity;
	}

	// Rajesh For marine Reports

	public String[][] getUserBrokerInformationForReport(String login) {
		String[][] UserBroker = new String[0][0];
		String userType = "";
		String args[] = new String[1];
		try {
			userType = getUserTypeInformation(login);
			String sql = "";
			if (userType != null) {
				if (userType.equalsIgnoreCase("Broker")) {
					args[0] = login;
					sql = "select application_id,login_id,agency_code,oa_Code,first_name from personal_info where oa_code=(select oa_code from login_master where login_id=?) and login_id!='NONE' and application_id in ('2','3','5')";
				} else if (userType.equalsIgnoreCase("User")) {
					args[0] = login;
					sql = "select application_id,login_id,agency_code,oa_code,first_name from personal_info where agency_code=(select agency_code from login_master where login_id=?) and login_id!='NONE' and application_id in ('3')";
				} else if (userType.equalsIgnoreCase("Freight")) {
					args[0] = login;
					sql = "select application_id,login_id,agency_code,oa_code,first_name from personal_info where agency_code=(select agency_code from login_master where login_id=?) and login_id!='NONE' and application_id in ('5')";
				}
				UserBroker = runner.multipleSelection(sql, args);
			}
		} catch (Exception e22) {
			System.out.println("Error " + e22);
			e22.printStackTrace();
		}
		return UserBroker;
	}

	public String getUserTypeInformation(String login) {
		String args[] = new String[1];
		String userType = "";
		String userTypeSql = "";
		try {
			args[0] = login;
			userTypeSql = "select USERTYPE from login_master where login_id=?";
			userType = runner.singleSelection(userTypeSql, args);
		} catch (Exception e22) {
			System.out.println("Error " + e22);
			e22.printStackTrace();
		}
		return userType;
	}

	public HashMap getBrokerCompanyNameUpdt(String loginId) {
		java.util.HashMap BCOMName = new java.util.HashMap();
		String[][] res = new String[0][0];
		String sql = "select distinct bro.company_name,log.login_id from BROKER_COMPANY_MASTER bro, LOGIN_MASTER log where bro.AGENCY_CODE in(select oa_code from LOGIN_MASTER  where login_id in ("
				+ loginId
				+ ")) and bro.AGENCY_CODE=log.AGENCY_CODE and log.login_id != 'NONE'";
		try {
			res = runner.multipleSelection(sql);
			if (res.length > 0) {
				loginId = loginId.replaceAll("'", "");
				StringTokenizer st = new StringTokenizer(loginId, ",");
				while (st.hasMoreTokens()) {
					String tempId = st.nextToken();
					for (int i = 0; i < res.length; i++) {
						if (res[i][1].equalsIgnoreCase(tempId))
							BCOMName.put("bro" + res[i][1], res[i][0]);
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Exception in getting pname" + e.toString());
			e.printStackTrace();
		}
		return BCOMName;
	}

	public HashMap getBrokerCompanyNameUpdt(String loginId, String branchCode) {
		java.util.HashMap BCOMName = new java.util.HashMap();
		String[][] res = new String[0][0];
		String sql = "select distinct bro.company_name,log.login_id from BROKER_COMPANY_MASTER bro, LOGIN_MASTER log where bro.AGENCY_CODE in(select oa_code from LOGIN_MASTER  where login_id in ("
				+ loginId
				+ ")) and bro.AGENCY_CODE=log.AGENCY_CODE and log.login_id != 'NONE' and log.login_id in(select login_id from login_master where oa_code in(select agency_code from broker_company_master where branch_code in("
				+ branchCode + ")))";
		try {
			res = runner.multipleSelection(sql);
			if (res.length > 0) {
				loginId = loginId.replaceAll("'", "");
				StringTokenizer st = new StringTokenizer(loginId, ",");
				while (st.hasMoreTokens()) {
					String tempId = st.nextToken();
					for (int i = 0; i < res.length; i++) {
						if (res[i][1].equalsIgnoreCase(tempId))
							BCOMName.put("bro" + res[i][1], res[i][0]);
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Exception in getting pname" + e.toString());
			e.printStackTrace();
		}
		return BCOMName;
	}

	public String getQuoteNoByLogin(String loginId, String rsaissuer,
			String brokerside) {
		String allQuoteNo[][] = new String[0][0];
		String quoteNos = "";
		String sql = "";
		String args[] = new String[0];
		try {
			if (rsaissuer == null) {
				args = new String[1];
				args[0] = loginId;
				sql = "select login_id,quote_no from position_master where status = 'P' and login_id in (select login_id from login_master where oa_code = (select oa_code from login_master where login_id=?))";
			} else {
				args = new String[2];
				args[0] = loginId;
				args[1] = rsaissuer;
				sql = "select login_id,quote_no from position_master where status = 'P' and login_id=? and application_id=?";
			}
			allQuoteNo = runner.multipleSelection(sql, args);
			if (allQuoteNo.length > 0) {
				for (int i = 0; i < allQuoteNo.length; i++) {
					quoteNos += allQuoteNo[i][1] + ",";
				}
			}
			if (quoteNos.length() > 0)
				quoteNos = quoteNos.substring(0, quoteNos.length() - 1);
		} catch (Exception e) {
			System.out.println("Exception in getting pname" + e.toString());
			e.printStackTrace();
		}
		return quoteNos;
	}

	public String getQuoteNoByLogin(String loginId, String branchCode) {
		String allQuoteNo[][] = new String[0][0];
		String quoteNos = "";
		String sql = "";
		String args[] = new String[1];
		try {
			args[0] = loginId;
			sql = "select login_id,quote_no from position_master where status = 'P' and login_id in (select login_id from login_master where oa_code = (select oa_code from login_master where login_id=?)) " +
					"and login_id in(select login_id from login_master where oa_code in(select agency_code from broker_company_master where branch_code in ("
					+ branchCode + ")))";
			allQuoteNo = runner.multipleSelection(sql, args);
			if (allQuoteNo.length > 0) {
				for (int i = 0; i < allQuoteNo.length; i++) {
					quoteNos += allQuoteNo[i][1] + ",";
				}
			}
			if (quoteNos.length() > 0)
				quoteNos = quoteNos.substring(0, quoteNos.length() - 1);
		} catch (Exception e) {
			System.out.println("Exception in getting pname" + e.toString());
			e.printStackTrace();
		}
		return quoteNos;
	}

	public HashMap getCommodity(String quoteNo, String brokerBra) {
		String commodityId1 = "";
		String[][] ss = new String[0][0];
		String sql = "";
		HashMap commHash = new HashMap();
		HashMap commHash1 = new HashMap();

		/** *** To Form the quote String ***** */
		String tempQuot = "";
		tempQuot = quoteNo;
		StringTokenizer tokenQuote = new StringTokenizer(quoteNo, ",");
		int quotCount = 0;
		int start = 0;
		int s = 0;
		int end = 500;
		int counter = 500;
		int real, decimal;
		quotCount = tokenQuote.countTokens();
		System.out.println("Total Number getCommodityTypeByQuote Of Quotes ..."
				+ quotCount);
		String qno[] = new String[quotCount];
		int q = 0;
		while (tokenQuote.hasMoreTokens()) {
			qno[q++] = tokenQuote.nextToken();
		}
		real = quotCount / counter;
		decimal = quotCount % counter;
		if (decimal > 0 || real == 0) {
			real = real + 1;
		}
		for (int p = 0; p < real; p++) {
			String qryQuot = "";
			if (p != 0) {
				start = end;
				end = end + counter;
			}
			if (decimal > 0 && (p + 1 == real)) {
				end = quotCount;
			}
			for (s = start; s < end; s++) {
				qryQuot = qryQuot + qno[s] + ",";
			}
			qryQuot = qryQuot.substring(0, qryQuot.length() - 1);

			/** *** To Form the quote String ***** */

			try {
				sql = "select oc.commodity_name, mc.commodity_id, mc.description, pos.quote_no from commoditymaster oc, marine_result_details mc,position_master pos, marine_data m where mc.commodity_id = oc.commodity_id and oc.branch_code='"
						+ brokerBra
						+ "' and oc.amend_id = (select max(oc.amend_id) from commoditymaster oc where mc.commodity_id = oc.commodity_id  and oc.branch_code='"
						+ brokerBra
						+ "') and  pos.application_no = m.application_no and mc.application_no=pos.application_no and mc.application_no in (select p.application_no from position_master p where p.quote_no in("
						+ qryQuot + "))"; // existing is quoteNo
				ss = runner.multipleSelection(sql);
				for (int j = 0; j < ss.length; j++) {
					int comFlag = 0;
					String result[][] = new String[1][4];
					StringTokenizer st = new StringTokenizer(qryQuot, ",");
					// StringTokenizer st = new StringTokenizer(quoteNo, ",");
					while (st.hasMoreTokens()) {
						if (ss[j][3].equalsIgnoreCase(st.nextToken())) {
							result[0][0] = ss[j][0];
							result[0][1] = ss[j][1];
							result[0][2] = ss[j][2];
							result[0][3] = ss[j][3];

							comFlag = 1;
						}
					}
					if (!commHash.containsKey("comm" + ss[j][3])
							&& comFlag == 1) {
						commHash.put("comm" + ss[j][3], result);

					} else if (commHash.containsKey("comm" + ss[j][3])
							&& comFlag == 1) {

						String res[][] = new String[0][0];
						String val[][] = new String[0][0];
						res = (String[][]) commHash.get("comm" + ss[j][3]);
						int len = res.length + result.length;
						String newRes[][] = new String[len][4];
						String newVal[][] = new String[len][4];
						int n = 0;
						for (int r = 0; r < res.length; r++, n++) {
							newRes[n][0] = res[r][0];
							newRes[n][1] = res[r][1];
							newRes[n][2] = res[r][2];
							newRes[n][3] = res[r][3];

						}
						for (int r = 0; r < result.length; r++, n++) {
							newRes[n][0] = result[r][0];
							newRes[n][1] = result[r][1];
							newRes[n][2] = result[r][2];
							newRes[n][3] = result[r][3];

						}
						commHash.put("comm" + ss[j][3], newRes);
					}
				}
				System.out.println("Size is" + commHash.size());
			} catch (Exception e) {
				System.out.println("Exception in gettin refferal data"
						+ e.toString());
				e.printStackTrace();
			}
		} // Outer For Loop...
		return commHash;
	}

	public String[][] getRejectedReferal(String pid, String branchCode,
			String brokerCodes, String freightStatus) {
		String pname = "";
		String loginids = "";
		String[][] ss = new String[0][0];
		String status123 = "";
		String freightCheck = "";
		String syntax = "";
		if (brokerCodes.equalsIgnoreCase("") || brokerCodes.length() == 0) {
			syntax = "select agency_code from broker_company_master where branch_code in("
					+ branchCode + ")";
		} else {
			brokerCodes = brokerCodes.replaceAll(",", "','");
			syntax = "'" + brokerCodes + "'";
		}

		if (freightStatus.equalsIgnoreCase("Yes")) {
			freightCheck = "and FREIGHT_STATUS='F'";
		} else {
			freightCheck = "and FREIGHT_STATUS is null";
		}
		try {
			String sql = "select to_char(entry_date,'YYYY-MM-DD'),count(entry_date) from position_master where status = 'R' and product_id ='"
					+ pid
					+ "' and login_id in(select login_id from login_master where oa_code in("
					+ syntax
					+ ")) "
					+ freightCheck
					+ " group by to_char(entry_date,'YYYY-MM-DD') order by to_char(entry_date,'YYYY-MM-DD') desc";
			ss = runner.multipleSelection(sql);
		} catch (Exception e) {
			System.out.println("Exception in gettin refferal data"
					+ e.toString());
			e.printStackTrace();
		}
		return ss;
	}

	// Used
	public String[][] getRejectedReferalByDate(String eDate, String type,
			String pid, String branchCode, String brokerCodes,
			String freightStatus) {
		String pname = "";
		String loginids = "";
		String[][] ss = new String[0][0];
		String status123 = "";
		String freightCheck = "";
		String syntax = "";
		if (brokerCodes.equalsIgnoreCase("") || brokerCodes.length() == 0) {
			syntax = "select agency_code from broker_company_master where branch_code in("
					+ branchCode + ")";
		} else {
			brokerCodes = brokerCodes.replaceAll(",", "','");
			syntax = "'" + brokerCodes + "'";
		}

		if (freightStatus.equalsIgnoreCase("Yes")) {
			freightCheck = "and pm.FREIGHT_STATUS='F'";
		} else {
			freightCheck = "and pm.FREIGHT_STATUS is null";
		}
		try {
			String sql = "select pm.application_no,pm.login_id,pm.quote_no,md.remarks,pm.application_id from position_master pm,marine_data md where to_char(pm.entry_date,'YYYY-MM-DD')='"
					+ eDate
					+ "' and pm.application_no=md.application_no  and pm.product_id='"
					+ pid
					+ "' and pm.status='R'  and pm.login_id in(select login_id from login_master where oa_code in("
					+ syntax
					+ ")) "
					+ freightCheck
					+ " order by pm.quote_no desc";
			ss = runner.multipleSelection(sql);
		} catch (Exception e) {
			System.out.println("Exception in gettin refferal data"
					+ e.toString());
			e.printStackTrace();
		}
		return ss;
	}

	/** ******* Rejected Referral Policies End ******* */

	/*
	 * Do not Remove public String getAdminBranch(String loginId) { String
	 * adminBranch = ""; String allBranches[][] = new String[0][0]; String
	 * branches = ""; String args[] = new String[1]; String sql = ""; try {
	 * args[0] = loginId; sql = "select BRANCH_CODE from LOGIN_MASTER where
	 * login_id=?"; adminBranch = runner.singleSelection(sql,args); adminBranch =
	 * adminBranch ==null ? "":adminBranch; } catch(Exception e) {
	 * System.out.println("Exception in gettin getAdminBranch
	 * data"+e.toString()); e.printStackTrace(); } try { sql = "select
	 * BRANCH_CODE,BRANCH_NAME from branch_master where status='Y'"; allBranches =
	 * runner.multipleSelection(sql); } catch(Exception e) {
	 * System.out.println("allBranches "+e.toString()); e.printStackTrace(); }
	 * if("All".equalsIgnoreCase(adminBranch)) { for(int i=0;i<allBranches.length;i++)
	 * branches = branches+"'"+allBranches[i][0]+"',"; branches =
	 * branches.substring(0,branches.length()-1); //branches = "'020','022'";
	 * //branches = "'001','002','003'"; } else if(adminBranch.length()>0) {
	 * adminBranch = adminBranch.replaceAll(",","','"); branches =
	 * "'"+adminBranch+"'"; } else { for(int i=0;i<allBranches.length;i++)
	 * branches = branches+"'"+allBranches[i][0]+"',"; branches =
	 * branches.substring(0,branches.length()-1); //branches =
	 * "'020','022'";//branches = "'001','002','003'"; }
	 * System.out.println("Admin loginId is..."+loginId);
	 * System.out.println("Admin Branch is..."+branches);
	 * 
	 * return branches; }
	 */

	public String getAdminBranch(String loginId) {
		String adminBranch = "";
		String args[] = new String[1];
		String sql = "";
		try {
			args[0] = loginId;
			sql = "select BRANCH_CODE from LOGIN_MASTER where login_id=?";
			adminBranch = runner.singleSelection(sql, args);
			adminBranch = adminBranch == null ? "" : adminBranch;
		} catch (Exception e) {
			System.out.println("Exception in gettin getAdminBranch data"
					+ e.toString());
			e.printStackTrace();
		}
		return adminBranch;
	}

	// For Direct client Changes - July 15th
	public String[][] getUserBrokerInformationForReport(String login, String pid) {
		String[][] UserBroker = new String[0][0];
		String userType = "";
		String appId = "";
		String args[] = new String[1];
		if (!"11".equalsIgnoreCase(pid)) {
			appId = "'2','3','5'";
		} else {
			appId = "'2','3','1'";
		}
		try {
			userType = getUserTypeInformation(login);
			String sql = "";
			if (userType != null) {
				if (userType.equalsIgnoreCase("Broker")) {
					sql = "select application_id,(case when application_id='1' and customer_login_id is not null  then (customer_login_id) else login_id end),agency_code,oa_Code,first_name from personal_info where (login_id in(select login_id from login_master where oa_code in(select oa_code from login_master where login_id='"
							+ login
							+ "')) or customer_login_id in(select login_id from login_master where oa_code in(select oa_code from login_master where login_id='"
							+ login
							+ "'))) and login_id!='NONE' and application_id in ("
							+ appId
							+ ") and oa_code is not null and agency_code is not null order by application_id";
					UserBroker = runner.multipleSelection(sql);
				} else if (userType.equalsIgnoreCase("User")) {
					args[0] = login;
					sql = "select application_id,login_id,agency_code,oa_code,first_name from personal_info where agency_code=(select agency_code from login_master where login_id=?) and login_id!='NONE' and application_id in ('3')";
					UserBroker = runner.multipleSelection(sql, args);
				} else if (userType.equalsIgnoreCase("Freight")) { // Multi Login May 19th
					/*
					 * args[0] = login; sql = "select
					 * application_id,login_id,agency_code,oa_code,first_name
					 * from personal_info where agency_code=(select agency_code
					 * from login_master where login_id=?) and login_id!='NONE'
					 * and application_id in ('5')";
					 */

					args[0] = login;
					sql = "select count (*) from login_master where agency_code=fd_code and login_id =?";
					String count = runner.singleSelection(sql, args);
					if (count.equals("0")) {
						sql = "select application_id,login_id,agency_code,oa_code,first_name from personal_info where agency_code=(select agency_code from login_master where login_id=?) and login_id!='NONE' and application_id in ('5')";
					} else if (count.equals("1")) {
						sql = "select application_id,login_id,agency_code,oa_code,first_name from personal_info where FD_CODE=(select agency_code from login_master where login_id=?) and login_id!='NONE' and application_id in ('5')";
					}
					UserBroker = runner.multipleSelection(sql, args);
				} else if (userType.equalsIgnoreCase("Customer")) { // Multi Login June 09 th
					/*
					 * args[0] = login; sql = "select
					 * application_id,customer_login_id,agency_code,oa_code,first_name
					 * from personal_info where agency_code=(select agency_code
					 * from login_master where login_id=?) and login_id!='NONE'
					 * and application_id in ('1')";
					 */
					args[0] = login;
					sql = "select count (*) from login_master where agency_code=fd_code and login_id =?";
					String count = runner.singleSelection(sql, args);
					if (count.equals("0")) {
						sql = "select application_id,customer_login_id,agency_code,oa_code,first_name from personal_info where agency_code=(select agency_code from login_master where login_id=?) and login_id!='NONE' and application_id in ('1')";
					} else if (count.equals("1")) {
						sql = "select application_id,customer_login_id,agency_code,oa_code,first_name from personal_info where FD_CODE=(select agency_code from login_master where login_id=?) and login_id!='NONE' and application_id in ('1')";
					}
					UserBroker = runner.multipleSelection(sql, args);
				}
			}
		} catch (Exception e22) {
			System.out.println("Errorrrrr " + e22);
			e22.printStackTrace();
		}
		return UserBroker;
	}

	public void updatePolicyStatus(String policyNo, String status) {
		String sql = "";
		String args[] = new String[2];
		try {
			args[0] = status;
			args[1] = policyNo;
			sql = "update POSITION_MASTER set Policy_Cancel_Status=?,PREMIUM=0,EXCESS_PREMIUM=0,COMMISSION=0,PRO_COMMISSION=0,TAX=0 where policy_no=?";
			runner.multipleUpdation(sql, args);
		} catch (Exception e) {
			System.out.println("updatePolicyStatus ..AdminBean" + e);
			e.printStackTrace();
		}
	}

	public HashMap getCommodityType(String branch) {
		String result[][] = new String[0][0];
		HashMap comType = new HashMap();
		String qry = "";
		String args[] = new String[1];
		try {
			args[0] = branch;
			qry = "select category_detail_id,detail_name from constant_detail where category_id='42' and status='Y' and branch_code=?";
			result = runner.multipleSelection(qry, args);
		} catch (Exception e) {
			System.out.println("getCommodityType in adminBean" + e.toString());
			e.printStackTrace();
		}
		for (int i = 0; i < result.length; i++) {
			comType.put("CT" + result[i][0], result[i][1]);
		}
		System.out.println("Commodity Type Size ... " + comType.size());
		return comType;
	}

	public HashMap getCommodityTypeByQuote(String quoteNo, String branch) {
		String commodityId1 = "";
		String[][] ss = new String[0][0];
		String sql = "";
		HashMap commHash = new HashMap();

		/** *** To Form the quote String ***** */
		String tempQuot = "";
		tempQuot = quoteNo;
		StringTokenizer tokenQuote = new StringTokenizer(quoteNo, ",");
		int quotCount = 0;
		int start = 0;
		int s = 0;
		int end = 500;
		int counter = 500;
		int real, decimal;
		quotCount = tokenQuote.countTokens();
		System.out.println("Total Number getCommodityTypeByQuote Of Quotes ..."
				+ quotCount);
		String qno[] = new String[quotCount];
		int q = 0;
		while (tokenQuote.hasMoreTokens()) {
			qno[q++] = tokenQuote.nextToken();
		}
		real = quotCount / counter;
		decimal = quotCount % counter;
		if (decimal > 0 || real == 0) {
			real = real + 1;
		}
		for (int p = 0; p < real; p++) {
			String qryQuot = "";
			if (p != 0) {
				start = end;
				end = end + counter;
			}
			if (decimal > 0 && (p + 1 == real)) {
				end = quotCount;
			}
			for (s = start; s < end; s++) {
				qryQuot = qryQuot + qno[s] + ",";
			}
			qryQuot = qryQuot.substring(0, qryQuot.length() - 1);

			/** *** To Form the quote String ***** */

			try {
				sql = "select oc.commodity_name, mc.commodity_id, mc.description, pos.quote_no, mc.COMMODITY_TYPE_ID from commoditymaster oc, marine_result_details mc,position_master pos, marine_data m where mc.commodity_id = oc.commodity_id and oc.amend_id = (select max(pc.amend_id) from commoditymaster pc where mc.commodity_id = pc.commodity_id and pc.branch_code='"
						+ branch
						+ "') and oc.branch_code='"
						+ branch
						+ "' and  pos.application_no = m.application_no and mc.application_no=pos.application_no and mc.application_no in (select p.application_no from position_master p where p.quote_no in("
						+ qryQuot + "))";
				ss = runner.multipleSelection(sql);
				for (int j = 0; j < ss.length; j++) {
					int comFlag = 0;
					String result[][] = new String[1][5];
					StringTokenizer st = new StringTokenizer(qryQuot, ",");
					while (st.hasMoreTokens()) {
						if (ss[j][3].equalsIgnoreCase(st.nextToken())) {
							result[0][0] = ss[j][0];
							result[0][1] = ss[j][1];
							result[0][2] = ss[j][2];
							result[0][3] = ss[j][3];
							result[0][4] = ss[j][4];
							comFlag = 1;
						}
					}
					if (!commHash.containsKey("CT" + ss[j][3]) && comFlag == 1) {
						commHash.put("CT" + ss[j][3], result);
					} else if (commHash.containsKey("CT" + ss[j][3])
							&& comFlag == 1) {

						String res[][] = new String[0][0];
						res = (String[][]) commHash.get("CT" + ss[j][3]);
						int len = res.length + result.length;
						String newRes[][] = new String[len][5];
						int n = 0;
						for (int r = 0; r < res.length; r++, n++) {
							newRes[n][0] = res[r][0];
							newRes[n][1] = res[r][1];
							newRes[n][2] = res[r][2];
							newRes[n][3] = res[r][3];
							newRes[n][4] = res[r][4];
						}
						for (int r = 0; r < result.length; r++, n++) {
							newRes[n][0] = result[r][0];
							newRes[n][1] = result[r][1];
							newRes[n][2] = result[r][2];
							newRes[n][3] = result[r][3];
							newRes[n][4] = result[r][4];
						}
						commHash.put("CT" + ss[j][3], newRes);
					}
				}
				System.out.println("Size is" + commHash.size());
			} catch (Exception e) {
				System.out.println("Exception in gettin refferal 2 data"
						+ e.toString());
				e.printStackTrace();
			}
		} // Outer For Loop
		return commHash;
	}

	public String getPromotionalCommission(String login, String productId,
			String openCoverNumber, String loginBra) {
		String sql = "";
		String proComm = "";
		String args[] = new String[0];
		if (productId.equals("11")) {
			args = new String[8];
			args[0] = productId;
			args[1] = login;
			args[2] = loginBra;
			args[3] = openCoverNumber;
			args[4] = productId;
			args[5] = login;
			args[6] = loginBra;
			args[7] = openCoverNumber;

			sql = "select nvl(PRO_COMMISSION,0) from LOGIN_PRO_COMMISSION where PRODUCT_ID=? and AGENCY_CODE=(select oa_code from login_master where login_id=?) and branch_code=? and START_DATE<=(select to_char(sysdate,'dd-mon-yyyy') from dual) and END_DATE>=(select to_char(sysdate,'dd-mon-yyyy') from dual) and open_cover_no=? and amend_id=(select max(amend_id) from LOGIN_PRO_COMMISSION where PRODUCT_ID=? and AGENCY_CODE=(select oa_code from login_master where login_id=?) and branch_code=? and START_DATE<=(select to_char(sysdate,'dd-mon-yyyy') from dual) and END_DATE>=(select to_char(sysdate,'dd-mon-yyyy') from dual) and open_cover_no=?)";
		} else if (productId.equals("3")) {
			args = new String[3];
			args[0] = productId;
			args[1] = login;
			args[2] = loginBra;
			/*args[3] = productId;
			args[4] = login;
			args[5] = loginBra;*/

//			sql = "select PRO_COMMISSION from LOGIN_PRO_COMMISSION where PRODUCT_ID=? and AGENCY_CODE=(select oa_code from login_master where login_id=?) and branch_code=? and START_DATE<=(select to_char(sysdate,'dd-mon-yyyy') from dual) and END_DATE>=(select to_char(sysdate,'dd-mon-yyyy') from dual) and amend_id=(select max(amend_id) from LOGIN_PRO_COMMISSION where PRODUCT_ID=? and AGENCY_CODE=(select oa_code from login_master where login_id=?) and branch_code=? and START_DATE<=(select to_char(sysdate,'dd-mon-yyyy') from dual) and END_DATE>=(select to_char(sysdate,'dd-mon-yyyy') from dual))";
			sql = "SELECT LPC.PRO_COMMISSION FROM LOGIN_PRO_COMMISSION LPC,LOGIN_MASTER LM WHERE  LPC.PRODUCT_ID = ? AND LPC.AGENCY_CODE = LM.OA_CODE AND LM.LOGIN_ID=? AND LPC.BRANCH_CODE = ? AND LPC.START_DATE <= TO_CHAR (SYSDATE, 'dd-mon-yyyy') AND LPC.END_DATE >=TO_CHAR (SYSDATE, 'dd-mon-yyyy') AND LPC.AMEND_ID = (SELECT   MAX (AMEND_ID) FROM   LOGIN_PRO_COMMISSION WHERE  PRODUCT_ID = LPC.PRODUCT_ID AND AGENCY_CODE = LPC.AGENCY_CODE AND BRANCH_CODE = LPC.BRANCH_CODE AND START_DATE =LPC.START_DATE AND END_DATE = LPC.END_DATE)";
		}
		proComm = runner.singleSelection(sql, args);
		proComm = (proComm == null || proComm.equals("") || proComm
				.equalsIgnoreCase("null")) ? "0" : proComm;

		return proComm;
	}

	public HashMap getCommodityRateByQuote(String quoteNo, String branch) {
		String commodityId1 = "";
		String[][] ss = new String[0][0];
		String sql = "";
		HashMap commHash = new HashMap();

		/** *** To Form the quote String ***** */
		String tempQuot = "";
		tempQuot = quoteNo;
		StringTokenizer tokenQuote = new StringTokenizer(quoteNo, ",");
		int quotCount = 0;
		int start = 0;
		int s = 0;
		int end = 500;
		int counter = 500;
		int real, decimal;
		quotCount = tokenQuote.countTokens();
		System.out.println("Total Number Of Quotes ..." + quotCount);
		String qno[] = new String[quotCount];
		int q = 0;
		while (tokenQuote.hasMoreTokens()) {
			qno[q++] = tokenQuote.nextToken();
		}
		real = quotCount / counter;
		decimal = quotCount % counter;
		if (decimal > 0 || real == 0) {
			real = real + 1;
		}
		for (int p = 0; p < real; p++) {
			String qryQuot = "";
			if (p != 0) {
				start = end;
				end = end + counter;
			}
			if (decimal > 0 && (p + 1 == real)) {
				end = quotCount;
			}
			for (s = start; s < end; s++) {
				qryQuot = qryQuot + qno[s] + ",";
			}
			qryQuot = qryQuot.substring(0, qryQuot.length() - 1);

			/** *** To Form the quote String ***** */

			try {
				sql = "select oc.commodity_name, mc.commodity_id, mc.description, pos.quote_no, mc.COMMODITY_TYPE_ID, mc.RATE from commoditymaster oc, marine_result_details mc,position_master pos, marine_data m where mc.commodity_id = oc.commodity_id and oc.amend_id = (select max(pc.amend_id) from commoditymaster pc where mc.commodity_id = pc.commodity_id  and pc.branch_code='"
						+ branch
						+ "') and oc.branch_code='"
						+ branch
						+ "' and  pos.application_no = m.application_no and mc.application_no=pos.application_no and mc.application_no in (select p.application_no from position_master p where p.quote_no in("
						+ qryQuot + "))";
				ss = runner.multipleSelection(sql);
				for (int j = 0; j < ss.length; j++) {
					int comFlag = 0;
					String result[][] = new String[1][6];
					StringTokenizer st = new StringTokenizer(qryQuot, ",");
					while (st.hasMoreTokens()) {
						if (ss[j][3].equalsIgnoreCase(st.nextToken())) {
							result[0][0] = ss[j][0];
							result[0][1] = ss[j][1];
							result[0][2] = ss[j][2];
							result[0][3] = ss[j][3];
							result[0][4] = ss[j][4];
							result[0][5] = ss[j][5];
							comFlag = 1;
						}
					}
					if (!commHash.containsKey("CR" + ss[j][3]) && comFlag == 1) {
						commHash.put("CR" + ss[j][3], result);
					} else if (commHash.containsKey("CR" + ss[j][3])
							&& comFlag == 1) {

						String res[][] = new String[0][0];
						res = (String[][]) commHash.get("CR" + ss[j][3]);
						int len = res.length + result.length;
						String newRes[][] = new String[len][6];
						int n = 0;
						for (int r = 0; r < res.length; r++, n++) {
							newRes[n][0] = res[r][0];
							newRes[n][1] = res[r][1];
							newRes[n][2] = res[r][2];
							newRes[n][3] = res[r][3];
							newRes[n][4] = res[r][4];
							newRes[n][5] = res[r][5];
						}
						for (int r = 0; r < result.length; r++, n++) {
							newRes[n][0] = result[r][0];
							newRes[n][1] = result[r][1];
							newRes[n][2] = result[r][2];
							newRes[n][3] = result[r][3];
							newRes[n][4] = result[r][4];
							newRes[n][5] = result[r][5];
						}
						commHash.put("CR" + ss[j][3], newRes);
					}
				}
				System.out.println("Size is" + commHash.size());
			} catch (Exception e) {
				System.out.println("Exception in getCommodityRateByQuote "
						+ e.toString());
				e.printStackTrace();
			}
		} // Outer For Loop
		return commHash;
	}

	public HashMap getCommodityWARateByQuote(String quoteNo, String branch) {
		String commodityId1 = "";
		String[][] ss = new String[0][0];
		String sql = "";
		HashMap commHash = new HashMap();

		/** *** To Form the quote String ***** */
		String tempQuot = "";
		tempQuot = quoteNo;
		StringTokenizer tokenQuote = new StringTokenizer(quoteNo, ",");
		int quotCount = 0;
		int start = 0;
		int s = 0;
		int end = 500;
		int counter = 500;
		int real, decimal;
		quotCount = tokenQuote.countTokens();
		System.out
				.println("Total Number getCommodityWARateByQuote Of Quotes ..."
						+ quotCount);
		String qno[] = new String[quotCount];
		int q = 0;
		while (tokenQuote.hasMoreTokens()) {
			qno[q++] = tokenQuote.nextToken();
		}
		real = quotCount / counter;
		decimal = quotCount % counter;
		if (decimal > 0 || real == 0) {
			real = real + 1;
		}
		for (int p = 0; p < real; p++) {
			String qryQuot = "";
			if (p != 0) {
				start = end;
				end = end + counter;
			}
			if (decimal > 0 && (p + 1 == real)) {
				end = quotCount;
			}
			for (s = start; s < end; s++) {
				qryQuot = qryQuot + qno[s] + ",";
			}
			qryQuot = qryQuot.substring(0, qryQuot.length() - 1);

			/** *** To Form the quote String ***** */

			try {
				sql = "select oc.commodity_name, mc.commodity_id, mc.description, pos.quote_no, mc.COMMODITY_TYPE_ID, mc.WARRATE from commoditymaster oc, marine_result_details mc,position_master pos, marine_data m where mc.commodity_id = oc.commodity_id and oc.amend_id = (select max(pc.amend_id) from commoditymaster pc where mc.commodity_id = pc.commodity_id and pc.branch_code='"
						+ branch
						+ "') and oc.branch_code='"
						+ branch
						+ "' and  pos.application_no = m.application_no and mc.application_no=pos.application_no and mc.application_no in (select p.application_no from position_master p where p.quote_no in("
						+ qryQuot + "))";
				ss = runner.multipleSelection(sql);
				for (int j = 0; j < ss.length; j++) {
					int comFlag = 0;
					String result[][] = new String[1][6];
					StringTokenizer st = new StringTokenizer(qryQuot, ",");
					while (st.hasMoreTokens()) {
						if (ss[j][3].equalsIgnoreCase(st.nextToken())) {
							result[0][0] = ss[j][0];
							result[0][1] = ss[j][1];
							result[0][2] = ss[j][2];
							result[0][3] = ss[j][3];
							result[0][4] = ss[j][4];
							result[0][5] = ss[j][5];
							comFlag = 1;
						}
					}
					if (!commHash.containsKey("CWR" + ss[j][3]) && comFlag == 1) {
						commHash.put("CWR" + ss[j][3], result);
					} else if (commHash.containsKey("CWR" + ss[j][3])
							&& comFlag == 1) {

						String res[][] = new String[0][0];
						res = (String[][]) commHash.get("CWR" + ss[j][3]);
						int len = res.length + result.length;
						String newRes[][] = new String[len][6];
						int n = 0;
						for (int r = 0; r < res.length; r++, n++) {
							newRes[n][0] = res[r][0];
							newRes[n][1] = res[r][1];
							newRes[n][2] = res[r][2];
							newRes[n][3] = res[r][3];
							newRes[n][4] = res[r][4];
							newRes[n][5] = res[r][5];
						}
						for (int r = 0; r < result.length; r++, n++) {
							newRes[n][0] = result[r][0];
							newRes[n][1] = result[r][1];
							newRes[n][2] = result[r][2];
							newRes[n][3] = result[r][3];
							newRes[n][4] = result[r][4];
							newRes[n][5] = result[r][5];
						}
						commHash.put("CWR" + ss[j][3], newRes);
					}
				}
				System.out.println("Size is" + commHash.size());
			} catch (Exception e) {
				System.out.println("Exception in getCommodityWARateByQuote "
						+ e.toString());
				e.printStackTrace();
			}
		} // Outer for Loop
		return commHash;
	}

	public String[][] getLoginBranchCountry(String loginId) {
		String allBranches[][] = new String[0][0];
		String args[] = new String[1];
		String sql = "";
		try {
			args[0] = loginId;
			sql = "select BRANCH_CODE,country_id from LOGIN_MASTER where login_id=?";
			allBranches = runner.multipleSelection(sql, args);
		} catch (Exception e) {
			System.out
					.println("Exception in gettin getLoginBranchCountry data "
							+ e.toString());
			e.printStackTrace();
		}
		System.out.println("getLoginBranchCountry allBranches  "
				+ allBranches.length);
		return allBranches;
	}

	public HashMap getTotalMasterCountry() {
		String masterCountry[][] = new String[0][0];
		String sql = "";
		HashMap hashCountry = new HashMap();
		try {
			sql = "select distinct country_id,initcap(country_name) from country_master where status='Y' order by country_id";
			masterCountry = runner.multipleSelection(sql);
		} catch (Exception e) {
			System.out.println("getTotalMasterCountry " + e.toString());
			e.printStackTrace();
		}

		for (int i = 0; i < masterCountry.length; i++) {
			masterCountry[i][0] = masterCountry[i][0] == null ? ""
					: masterCountry[i][0];
			masterCountry[i][1] = masterCountry[i][1] == null ? ""
					: masterCountry[i][1];
			hashCountry.put("TC" + masterCountry[i][0], masterCountry[i][1]);
		}
		System.out.println("getTotalMasterCountry .. Size --> "
				+ hashCountry.size());
		return hashCountry;
	}

	public HashMap getTotalMasterCity() {
		String masterCity[][] = new String[0][0];
		String sql = "";
		HashMap hashCity = new HashMap();
		try {
			sql = "select distinct city_id,initcap(city_name) from city_master where status='Y' order by city_id";
			masterCity = runner.multipleSelection(sql);
		} catch (Exception e) {
			System.out.println("getTotalMasterCity " + e.toString());
			e.printStackTrace();
		}

		for (int i = 0; i < masterCity.length; i++) {
			masterCity[i][0] = masterCity[i][0] == null ? "" : masterCity[i][0];
			masterCity[i][1] = masterCity[i][1] == null ? "" : masterCity[i][1];
			hashCity.put("TC" + masterCity[i][0], masterCity[i][1]);
		}
		System.out.println("getTotalMasterCity .. Size --> " + hashCity.size());
		return hashCity;
	}

	public String getBrokerCodes(String loginBranch) {
		String adminBrokerCodes = "";
		String sql = "";
		String args[] = new String[1];
		try {
			args[0] = loginBranch;
			sql = "SELECT RTRIM(XMLAGG(XMLELEMENT(E,OA_CODE || ',')).EXTRACT('//text()'),',')  OA_CODE FROM LOGIN_MASTER LM,BROKER_COMPANY_MASTER bcm WHERE lm.usertype='Broker'	AND LM.OA_CODE = BCM.AGENCY_CODE and BCM.BRANCH_CODE = ?";
			//sql = "select broker_codes from login_master where login_id =?";
			adminBrokerCodes = runner.singleSelection(sql, args);
		} catch (Exception e) {
			System.out.println("getBrokeresCode " + e.toString());
			e.printStackTrace();
		}
		return adminBrokerCodes;
	}

	public String getLoginProIds(String loginId) {
		String adminProIds = "";
		String sql = "";
		String args[] = new String[1];
		try {
			args[0] = loginId;
			sql = "select product_id from login_master where login_id =?";
			adminProIds = runner.singleSelection(sql, args);
		} catch (Exception e) {
			System.out.println("getLoginProIds " + e.toString());
			e.printStackTrace();
		}
		return adminProIds;
	}

	public String[][] getBranchDetails(String brokerBra) {
		String result[][] = new String[0][0];
		String sql = "";
		String args[] = new String[1];
		try {
			args[0] = brokerBra;
			sql = "select BRANCH_NAME,header_img,footer_img,sign_img,stamp,Currency_name,CURRENCY_ABBREVIATION,nvl(CURRENCY_ACRONYM,' '),ADDRESS1,REMARKS,ADDRESS2,CITY,COUNTRY,PHONE,FAX from BRANCH_MASTER where BRANCH_CODE=?";
			result = runner.multipleSelection(sql, args);
		} catch (Exception e) {
			System.out.println("get Branch Details " + e.toString());
			e.printStackTrace();
		}
		return result;
	}

	public String[][] getTodaysDate(String branch) {
		String query = "";
		String[][] result = new String[0][0];
		String temp = "sysdate";
		String sql = "";
		String hour = "";
		String args[] = new String[0];
		try {
			args = new String[4];
			args[0] = "62";
			args[1] = "1";
			args[2] = "Y";
			args[3] = branch;
			sql = "select DETAIL_NAME from constant_detail where category_id=? and category_detail_id=? and status=? and branch_code=?";
			hour = runner.singleSelection(sql, args);
			if (hour.length() > 0)
				temp = temp + "+" + hour;
			// query = "select extract(day from sysdate+8/24),extract(month from
			// sysdate),extract(year from sysdate) from dual";
			query = "select extract(day from " + temp + "),extract(month from "
					+ temp + "),extract(year from " + temp + ") from dual";
			result = runner.multipleSelection(query);
		} catch (Exception ex) {
			System.out.println("getTodaysDate..." + ex);
			ex.printStackTrace();
		}
		return result;
	}

	// Admin Side Policy Report Intermediate screen..... March 09 - 09
	public String[][] adminPolicyReport(String data1, String data2, String pid,
			String branchCode, String brokerCodes, String freightStatus,
			String individual, String RSAIsser) {
		String addedPID = "", adnicUsers ="", syntax = "", brokerIds = "", freightCheck = "", qry = "", individualBroker = "", applicationID ="";
		String startdate = data1;
		String enddate = data2;
		String policyDetails[][] = new String[0][0];
		String adnicUserList[][] = new String[0][0];
		
		if (pid.equals("all"))
			addedPID = " and pos.product_id in(3,11)";
		else
			addedPID = " and pos.product_id in(" + pid + ")";

		if (brokerCodes.equalsIgnoreCase("") || brokerCodes.length() == 0)
			syntax = "";
		else {
			brokerCodes = brokerCodes.replaceAll(",", "','");
			brokerCodes = "'" + brokerCodes + "'";
			syntax = " and bcm.agency_code in(" + brokerCodes + ")";
		}

		if (individual.equalsIgnoreCase("") || individual.length() == 0) {
			individualBroker = "";
		} else {
			individualBroker = " and log.oa_code ='" + individual + "'";
		}

		if (freightStatus.equalsIgnoreCase("Yes"))
			freightCheck = " and pos.FREIGHT_STATUS='F'";
		else if (pid.equals("all"))
			freightCheck = "";
		else if (pid.indexOf(",") != -1)
			freightCheck = "";
		else
			freightCheck = " and pos.FREIGHT_STATUS is null";

		try {
			if(RSAIsser.equalsIgnoreCase(""))
			{
				qry = "select bcm.company_name,count(bcm.company_name),sum(pos.premium+nvl(pos.excess_premium,0)),sum(nvl(pos.commission,0))," +
					"sum(nvl(pos.PRO_COMMISSION,0)),sum(nvl(pos.tax,0)),log.oa_code from position_master pos,broker_company_master bcm,login_master log," +
					"personal_info per, marine_data md,marine_policy_details mpd where pos.status='P' and bcm.branch_code='"+branchCode+
					"' and pos.login_id=log.login_id and log.oa_code=bcm.agency_code " + syntax + " " + individualBroker
					+ " and pos.customer_id=per.customer_id and mpd.quote_no=pos.quote_no and per.application_id=pos.application_id  and md.application_no=pos.application_no "
					+ addedPID
					+ " "
					+ freightCheck
					+ " and pos.inception_date between to_date('"
					+ startdate
					+ "','dd-mm-yyyy') and to_date('"
					+ enddate
					+ "','dd-mm-yyyy')+1 group by bcm.company_name,log.oa_code order by count(bcm.company_name) desc ";
			}else {
				if(RSAIsser.equalsIgnoreCase("all")){
					qry = "select distinct login_id from login_master where lower(usertype) = 'rsaissuer' and status = 'Y'";
					adnicUserList = runner.multipleSelection(qry);
					System.out.println("adnicUserList.length " + adnicUserList.length);
					for(int i =0; i< adnicUserList.length; i++){
						if(adnicUsers.equalsIgnoreCase(""))
							adnicUsers = "'" + adnicUserList[i][0].trim() + "'";
						else
							adnicUsers = adnicUsers + ",'" + adnicUserList[i][0].trim() + "'";
					}
					System.out.println("adnicUsers " + adnicUsers);
					applicationID = "AND pos.application_id IN (" + adnicUsers + ")";
					qry = "SELECT pos.application_id COMPANY_NAME, COUNT (bcm.company_name), SUM (pos.premium + NVL (pos.excess_premium, 0)), " +
						"SUM (NVL (pos.commission, 0)), SUM (NVL (pos.PRO_COMMISSION, 0)), SUM (NVL (pos.tax, 0)),pos.application_id application_id  FROM position_master pos, " +
						"broker_company_master bcm, login_master LOG, personal_info per, marine_data md, marine_policy_details mpd WHERE pos.status = 'P' AND " +
						"bcm.branch_code = '" + branchCode + "' AND pos.login_id = LOG.login_id AND LOG.oa_code = bcm.agency_code " +
						applicationID + " AND pos.customer_id = per.customer_id " +
						"AND mpd.quote_no = pos.quote_no AND md.application_no = pos.application_no " + addedPID + " AND pos.inception_date BETWEEN " +
						"TO_DATE ('" + startdate + "', 'dd-mm-yyyy') AND TO_DATE ('" + enddate + "', 'dd-mm-yyyy') + 1 GROUP BY " +
						" pos.application_id ORDER BY COUNT (pos.application_id) DESC";
				} else {
					applicationID = "AND pos.application_id = '" + RSAIsser + "'";
					qry = "SELECT pos.application_id COMPANY_NAME, COUNT (bcm.company_name), SUM (pos.premium + NVL (pos.excess_premium, 0)), " +
						"SUM (NVL (pos.commission, 0)), SUM (NVL (pos.PRO_COMMISSION, 0)), SUM (NVL (pos.tax, 0)), pos.application_id application_id FROM position_master pos, " +
						"broker_company_master bcm, login_master LOG, personal_info per, marine_data md, marine_policy_details mpd WHERE pos.status = 'P' AND " +
						"bcm.branch_code = '" + branchCode + "' AND pos.login_id = LOG.login_id AND LOG.oa_code = bcm.agency_code " +
						applicationID + " AND pos.customer_id = per.customer_id " +
						"AND mpd.quote_no = pos.quote_no AND md.application_no = pos.application_no " + addedPID + " AND pos.inception_date BETWEEN " +
						"TO_DATE ('" + startdate + "', 'dd-mm-yyyy') AND TO_DATE ('" + enddate + "', 'dd-mm-yyyy') + 1 GROUP BY " +
						" pos.application_id ORDER BY COUNT (pos.application_id) DESC";
				}
			}
			System.out.println("qry................." + qry);
			policyDetails = runner.multipleSelection(qry);
		} catch (Exception e) {
			System.out.println("admin Policy Reports ...." + qry);
			System.out.println("admin Policy Report..." + e);
			e.printStackTrace();
		}
		return policyDetails;
	}

	public String getBrokerLoginFromAgencyCode(String agencyCode) {
		String qry = "";
		String args[] = new String[1];
		String loginId = "";
		try {
			args[0] = agencyCode;
			qry = "select login_id from login_master where agency_code=oa_code and agency_code=?";
			loginId = runner.singleSelection(qry, args);
		} catch (Exception E) {
			System.out.println("get Broker Login From Agency Code..." + E);
			E.printStackTrace();
		}
		return loginId;
	}

	public String getBrokerAgencyCodeFromLogin(String loginId) {
		String qry = "";
		String args[] = new String[1];
		String agency = "";
		try {
			args[0] = loginId;
			qry = "select oa_code from login_master where agency_code=oa_code and usertype='Broker' and login_Id=?";
			agency = runner.singleSelection(qry, args);
		} catch (Exception E) {
			System.out.println("get Broker Login From Agency Code..." + E);
			E.printStackTrace();
		}
		return agency;
	}

	// Admin Side Report1.jsp...........

	public String[][] adminReports(String data1, String data2, String loginId,
			String pid, String branchCode, String freightStatus, int startNo,
			int endNo,String actualBranch,String RSAIsser, String userName,String company) {
		System.out.println("RSAIsser******************"+RSAIsser);
		String addeddata = "", qry ="";
		if (pid.equals("all")) {
			addeddata = "";
		} else {
			addeddata = "and  a.product_id in(" + pid + ")";
		}
		String freightCheck = "";
		if (freightStatus.equalsIgnoreCase("Yes")) {
			freightCheck = "and a.FREIGHT_STATUS='F'";
		} else if (pid.equals("all")) {
			freightCheck = "";
		} else if (pid.indexOf(",") != -1) {
			freightCheck = "";
		} else {
			freightCheck = "and a.FREIGHT_STATUS is null";
		}
		
		String startdate = data1;
		String enddate = data2;
		String returnval[][] = new String[0][0];
		if(RSAIsser.equalsIgnoreCase("")){
			qry = "select * from (select REPORT.*,rownum rnum1 from (select a.policy_no,to_char(a.inception_date,'dd-MM-yyyy') as InceptioDate, b.mode_of_transport,h.transport_description,b.cover_id,d.cover_name, b.sale_term_id, e.sale_term_name,b.extra_cover_id, f.extra_cover_name,  b.total_sum_insured as TotalSI, a.premium ,nvl(a.commission,'0') as commission, a.remarks, a.login_id,b.total_war_charges,a.quote_no,a.debit_note_no,nvl(g.first_name,g.company_name) as CustName,b.transit_from,b.final_destination,b.total_sum_insured,b.exchange_rate as Exchange,a.login_id as loginId,nvl(mp.bank_name,' ') as BankName,nvl(mp.carrier_name,' ') as CarrierName,a.excess_premium,b.CURRENCY_NAME,a.product_id,b.TOTAL_SALE_TERM_CHARGES,b.transit_from_city_id,b.transit_from_country_id,b.final_destination_city_id,b.final_Destination_country_id, to_char(b.INCEPTION_DATE,'dd-MM-yyyy'),b.EXCHANGE_RATE,nvl(a.PRO_COMMISSION,'0') as ProCommission,nvl(a.tax,'0') as PremiumTax,nvl(a.application_id,'1') as ApplicationId,round(b.marine_premium,0),round(b.war_premium,0) from position_master a, marine_data b, cover_master d, sale_term_master e, extra_cover_master f, personal_info g, mode_of_transport h,marine_policy_details mp where  b.cover_id=d.cover_id and b.sale_term_id=e.sale_term_id and f.extra_cover_id=b.extra_cover_id and (d.branch_code in("
				+ branchCode
				+ ") and e.branch_code in("
				+ branchCode
				+ ") and f.branch_code in("
				+ branchCode
				+ ") and h.branch_code in("
				+ branchCode
				+ ")) and  a.application_no=b.application_no and a.DEBIT_NOTE_NO!='0' and  g.customer_id=a.customer_id   and b.mode_of_transport=h.mode_transport_id and (a.inception_date between to_date('"
				+ startdate
				+ "','dd-mm-yyyy') and to_date('"
				+ enddate
				+ "','dd-mm-yyyy')+1 ) and g.application_id='1' and g.application_id = a.application_id and a.login_id in (select login_id from login_master where oa_code in(select agency_code from broker_company_master where branch_code='"
				+ actualBranch
				+ "' and agency_code in (select oa_code from login_master where oa_code=agency_code and login_id='"
				+ loginId
				+ "'))) and  mp.quote_no=a.quote_no  "
				+ addeddata
				+ " and a.status='P' "
				+ freightCheck
				+ " order by a.inception_date desc)REPORT where rownum<="
				+ endNo + ") where rnum1>=" + startNo;
		} else {
			if(RSAIsser.equalsIgnoreCase("all"))
				RSAIsser = userName;
			System.out.println("EmiratesUSER*****************************");
			qry = "select * from (select REPORT.*,rownum rnum1 from (select a.policy_no,to_char(a.inception_date,'dd-MM-yyyy') as InceptioDate, b.mode_of_transport," +
					"h.transport_description,b.cover_id,d.cover_name, b.sale_term_id, e.sale_term_name,b.extra_cover_id, f.extra_cover_name,  b.total_sum_insured as TotalSI, " +
					"a.premium ,nvl(a.commission,'0') as commission, a.remarks, a.login_id,b.total_war_charges,a.quote_no,a.debit_note_no,nvl(g.first_name,g.company_name) as CustName," +
					"b.transit_from,b.final_destination,b.total_sum_insured,b.exchange_rate as Exchange,a.login_id as loginId,nvl(mp.bank_name,' ') as BankName," +
					"nvl(mp.carrier_name,' ') as CarrierName,a.excess_premium,b.CURRENCY_NAME,a.product_id,b.TOTAL_SALE_TERM_CHARGES,b.transit_from_city_id," +
					"b.transit_from_country_id,b.final_destination_city_id,b.final_Destination_country_id, to_char(b.INCEPTION_DATE,'dd-MM-yyyy'),b.EXCHANGE_RATE," +
					"nvl(a.PRO_COMMISSION,'0') as ProCommission,nvl(a.tax,'0') as PremiumTax,nvl(a.application_id,'1') as ApplicationId,round(b.marine_premium,0),round(b.war_premium,0) from position_master a, " +
					"marine_data b, cover_master d, sale_term_master e, extra_cover_master f, personal_info g, mode_of_transport h,marine_policy_details mp " +
					" ,login_master LOG,broker_company_master bcm  where  b.cover_id=d.cover_id and b.sale_term_id=e.sale_term_id and f.extra_cover_id=b.extra_cover_id and (d.branch_code in("
					+ branchCode + ") and e.branch_code in(" + branchCode
					+ ") and f.branch_code in("
					+ branchCode
					+ ") and h.branch_code in("
					+ branchCode
					+ ")) and  a.application_no=b.application_no AND a.login_id = LOG.login_id AND LOG.oa_code = bcm.agency_code and  g.customer_id=a.customer_id   and b.mode_of_transport=h.mode_transport_id and (a.inception_date between to_date('"
					+ startdate
					+ "','dd-mm-yyyy') and to_date('"
					+ enddate
					+ "','dd-mm-yyyy')+1 ) and a.application_id= '" + RSAIsser + "' and  mp.quote_no=a.quote_no  "
					+ addeddata
					+ " and a.status='P' "
					+ freightCheck
					+ " order by a.inception_date desc)REPORT where rownum<="
					+ endNo + ") where rnum1>=" + startNo;
		}
		try {
			System.out.println("Admin Report Query is .........." + qry);
			returnval = runner.multipleSelection(qry);
		} catch (Exception e) {
			System.out
					.println("Exception in getiing report dats by using date...10"
							+ e.toString());
			e.printStackTrace();
		}
		return returnval;
	}
	public String[][] adminReportCount(String data1, String data2,
			String loginId, String pid, String branchCode, String freightStatus,String actualBranch,String company,String RSAIssuer) {
		String addeddata = "",qry="";
		if (pid.equals("all")) {
			addeddata = "";
		} else {
			addeddata = "and  a.product_id in(" + pid + ")";
		}
		String freightCheck = "";
		if (freightStatus.equalsIgnoreCase("Yes")) {
			freightCheck = "and a.FREIGHT_STATUS='F'";
		} else if (pid.equals("all")) {
			freightCheck = "";
		} else if (pid.indexOf(",") != -1) {
			freightCheck = "";
		} else {
			freightCheck = "and a.FREIGHT_STATUS is null";
		}
		String startdate = data1;
		String enddate = data2;
		String returnval[][] = new String[0][0];
		if(RSAIssuer.equalsIgnoreCase("")){
		qry = "select count(*) from position_master a, marine_data b, cover_master d, sale_term_master e, extra_cover_master f, personal_info g, mode_of_transport h,marine_policy_details mp where  b.cover_id=d.cover_id and b.sale_term_id=e.sale_term_id and f.extra_cover_id=b.extra_cover_id and (d.branch_code in("
				+ branchCode
				+ ") and e.branch_code in("
				+ branchCode
				+ ") and f.branch_code in("
				+ branchCode
				+ ") and h.branch_code in("
				+ branchCode
				+ ")) and  a.application_no=b.application_no and a.DEBIT_NOTE_NO!='0' and  g.customer_id=a.customer_id   and b.mode_of_transport=h.mode_transport_id and (a.inception_date between to_date('"
				+ startdate
				+ "','dd-mm-yyyy') and to_date('"
				+ enddate
				+ "','dd-mm-yyyy')+1 ) and g.application_id='1' AND g.application_id = a.application_id and a.login_id in (select login_id from login_master where oa_code in(select agency_code from broker_company_master where branch_code='"
				+ actualBranch
				+ "' and agency_code in (select oa_code from login_master where oa_code=agency_code and login_id='"
				+ loginId
				+ "'))) and  mp.quote_no=a.quote_no  "
				+ addeddata
				+ " and a.status='P' "
				+ freightCheck
				+ " order by a.inception_date desc";
		} else {
			if(RSAIssuer.equalsIgnoreCase("all"))
				RSAIssuer = loginId;
			System.out.println("EmiratesUSER*****************************");
			qry = "select count(*) from position_master a, marine_data b, cover_master d, sale_term_master e, extra_cover_master f, personal_info g, mode_of_transport h,marine_policy_details mp " +
					" ,login_master LOG,broker_company_master bcm  where  b.cover_id=d.cover_id and b.sale_term_id=e.sale_term_id and f.extra_cover_id=b.extra_cover_id and (d.branch_code in("
					+ branchCode + ") and e.branch_code in(" + branchCode
					+ ") and f.branch_code in("
					+ branchCode
					+ ") and h.branch_code in("
					+ branchCode
					+ ")) and  a.application_no=b.application_no AND a.login_id = LOG.login_id AND LOG.oa_code = bcm.agency_code and  g.customer_id=a.customer_id   and b.mode_of_transport=h.mode_transport_id and (a.inception_date between to_date('"
					+ startdate
					+ "','dd-mm-yyyy') and to_date('"
					+ enddate
					+ "','dd-mm-yyyy')+1 ) and a.application_id= '" + RSAIssuer + "' and  mp.quote_no=a.quote_no  "
					+ addeddata
					+ " and a.status='P' "
					+ freightCheck
					+ " order by a.inception_date desc";
		}
		try {
			returnval = runner.multipleSelection(qry);
		} catch (Exception e) {
			System.out
					.println("Exception in getiing report dats by using date...10"
							+ e.toString());
			e.printStackTrace();
		}
		return returnval;
	}
	public String[][] getReferralMails(String branchCode,String mailNo){
		String result[][]=new String[0][0];
		String arg[]={branchCode,mailNo};
		String qry="select email_to,email_cc,EMAIL_SUBJECT from mail_details where branch_code=? and mail_no=?";
		
		try {
			result=runner.multipleSelection(qry, arg);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public String valiadateMail(String mails){
		boolean b=true;		
		System.out.println("......Email validation for referral mail.....");
		String mail[]=mails.split(",");
		for(int i=0;i<mail.length;i++){
		b=validateReferralMail(mail[i]);
		if(b==false){
			error = error + "<br>*" + "Enter valid mailId";	
			break;
		}
		}
		
		return error;
	}
	public boolean updateMails(String emailTo,String emailCC,String branchCode,String mailNo){
		boolean b=false;
		String arg[]={emailTo,emailCC,branchCode,mailNo};
		String qry="update mail_details set email_to=?,email_cc=? where branch_code=? and mail_no=?";
		try{
			runner.multipleUpdation(qry, arg);
			b=true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return b;
		
	}
	
	public boolean validateReferralMail(String mail){
		
		String at="@";
			String dot=".";
			int lat=mail.indexOf(at);
			int lstr=mail.length();
			int ldot=mail.indexOf(dot);
			
			if (mail.indexOf(at)==-1 || mail.indexOf(at)==0 || mail.indexOf(at)==lstr){
			   return false;
			}
		
		if (mail.indexOf(dot)==-1 || mail.indexOf(dot)==0 || mail.indexOf(dot)==lstr){
		    return false;
		}
		if (mail.indexOf(at,(lat+1))!=-1){
		    return false;
		 }
   
		 if (mail.substring(lat-1,lat)==dot || mail.substring(lat+1,lat+2)==dot){
		    return false;
		 }
		 if (mail.indexOf(dot,(lat+2))==-1){
		    return false;
		 }
		
		 if (mail.indexOf(" ")!=-1){
		    return false;
		 }


		return true;
		
	}

}// class
