package com.maan.admin.DAO;

import java.sql.*;
import java.io.PrintWriter;
import java.util.*;

import proj.sql.QueryBuilder;
import proj.date.DateFunction;
import java.text.SimpleDateFormat;

import com.maan.common.MyJdbcTemplate;
import com.maan.common.password.passwordEnc;
import com.maan.services.util.runner;
import com.maan.common.error.ErrorInfo;

public class ReportBean extends MyJdbcTemplate 
{
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
	public String product_id = "";
	String policyno = "", inceptiondate = "", modeoftransport = "",	transportdescription = "";
	private String currencyType = "";
	private String exchangeRate = "";
	private String effectDate = "";
	private String disStatus = "";
	private String error = "";
	String[][] brokerLoginIds = new String[0][0];
	String[] Brokers = new String[0];
	String[] commodityAll = new String[0];
	String busType1 = "", busType = "";
	String[] modeType = new String[0];
	String[] transType = new String[0];
	String rag1 = "", rag2 = "", rag3 = "";
	String origin = "", destin = "";

	public void setBrokerLoginIds(String[][] brokerLoginIds) {
		this.brokerLoginIds = brokerLoginIds;
	}

	public String[][] getBrokerLoginIds() {
		return brokerLoginIds;
	}

	public void setData3(String broktype) {
		this.broktype = broktype;
	}

	public String getData3() {
		return broktype;
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

	public void setRep(String rep) {
		this.rep = rep;
	}

	public String getRep() {
		return rep;
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

	public void setBroker(String[] Brokers) {
		this.Brokers = Brokers;
	}

	public String[] getBroker() {
		return Brokers;
	}

	public void setCommodityAll(String[] commodityAll) {
		this.commodityAll = commodityAll;
	}

	public String[] getCommodityAll() {
		return commodityAll;
	}

	public void setBusinessType(String busType1) {
		this.busType = busType;
	}

	public String getBusinessType() {
		return busType;
	}

	public void setBusinessType1(String busType1) {
		this.busType1 = busType1;
	}

	public String getBusinessType1() {
		return busType1;
	}

	public void setModeType(String[] modeType) {
		this.modeType = modeType;
	}

	public String[] getModeType() {
		return modeType;
	}

	public void setTransType(String[] transType) {
		this.transType = transType;
	}

	public String[] getTransType() {
		return transType;
	}

	public void setRag1(String rag1) {
		this.rag1 = rag1;
	}

	public String getRag1() {
		return rag1;
	}

	public void setRag2(String rag2) {
		this.rag2 = rag2;
	}

	public String getRag2() {
		return rag2;
	}

	public void setRag3(String rag3) {
		this.rag3 = rag3;
	}

	public String getRag3() {
		return rag3;
	}

	public void setOrigination(String org) {
		this.origin = org;
	}

	public String getOrigination() {
		return origin;
	}

	public void setDestination(String dest) {
		this.destin = dest;
	}

	public String getDestination() {
		return destin;
	}

	public void setProduct(String product_id) {
		this.product_id = product_id;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCompany() {
		return company;
	}

	public String getProduct() {
		return product_id;
	}

	public String validateFields1(String busType) 
	{
		String sea = "", air = "", road = "";
		int k = 0;
		com.maan.services.util.dataCollection data = new com.maan.services.util.dataCollection();
		String error = "";
		String values = null;
		try {
			values = data.checkDate(dobDay + "/" + dobMonth + "/" + dobYear);
			if ("Invalid".equalsIgnoreCase(values))
				error = error + "<br>*" + runner.getErrormsg("62");

			values = data.checkDate(dobDay1 + "/" + dobMonth1 + "/" + dobYear1);
			if ("Invalid".equalsIgnoreCase(values))
				error = error + "<br>*" + runner.getErrormsg("63");
			else if (Integer.parseInt(dobYear1) < Integer.parseInt(dobYear)) {
				error = error + "<br>*" + runner.getErrormsg("72");
			} else if (Integer.parseInt(dobYear1) == Integer.parseInt(dobYear)) {

				if (Integer.parseInt(dobMonth1) < Integer.parseInt(dobMonth)) {
					error = error + "<br>*" + runner.getErrormsg("72");
				} else if (Integer.parseInt(dobMonth1) == Integer
						.parseInt(dobMonth)) {
					if (Integer.parseInt(dobDay1) < Integer.parseInt(dobDay)) {
						error = error + "<br>*" + runner.getErrormsg("72");
					}
				}

				SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH);
		    	sf.setTimeZone(new java.util.SimpleTimeZone(14400000,"GMT")); 	
		    	String s = sf.format(new java.util.Date());
		        String enteredDate1 = dobDay1 + "/" + dobMonth1 + "/"+ dobYear1;
				String enteredDate = dobDay + "/" + dobMonth + "/" + dobYear;
				long diff1 = 0;
				long diff = 0;
				try {
					s = runner.singleSelection("select to_char(sysdate+8/24,'dd/MM/YYYY') from dual");
					DateFunction dbr=new DateFunction();
					if(s.length() > 0)
					{
						diff1 = dbr.getDayDifference(dbr.getCalendar(s),dbr.getCalendar(enteredDate1));
						diff = dbr.getDayDifference(dbr.getCalendar(s),dbr.getCalendar(enteredDate));
					}
				} catch (Exception e) {
				}
				if (diff > 0) {
					error = error + "<br>*" + runner.getErrormsg("70");
				}
				if (diff1 > 0) {
				}
			}
			if ("3".equalsIgnoreCase(product_id)) {
				if (!"1".equalsIgnoreCase(busType)) {
					error = error + "<br>*" + "Please Select New Business";
				}
				else {
				}
			}
			if ((Brokers != null) && (Brokers.length > 0)) {
				for (int i = 0; i < Brokers.length; i++) {
					if ("All".equalsIgnoreCase(Brokers[i])) {
						if (Brokers.length > 1) {
							error = error + "<br>*"	+ "Please Select a Valid Broker";
						}
					}
				}
			}
		} 
		catch (Exception e)
		{
			System.out.println("Exception in " + e.toString());
			e.printStackTrace();
		} 
		return error;
	}

	public String[][] getCustomerData(String index) 
	{
		String[][] CustomerData = new String[0][0];
		String sql = ""; 
		String args[] = new String[1];
		try 
		{
			args[0] = index.toUpperCase()+"%";
			sql = "select CUSTOMER_ID,FIRST_NAME,ADDRESS1,TELEPHONE,ADDRESS2 from personal_info where upper(first_name) like ? and application_id='1'";
			CustomerData = runner.multipleSelection(sql,args);
		}
		catch (Exception e)
		{
			System.out.println("Exception in  getCustomerData "+ e.toString());
			e.printStackTrace();
		}
		return CustomerData;
	}

	public String[][] getQuoteData(String index) 
	{
		String[][] CustomerData = new String[0][0];
		String sql = ""; 
		String args[] = new String[1];
		try 
		{
			args[0] = index;
			sql = "select a.First_Name,b.quote_no,c.product_name,b.PREMIUM,b.entry_date from personal_info a,position_master b,product_master c where a.customer_id=b.customer_id and b.product_id=c.product_id and b.quote_no=?";

			CustomerData = runner.multipleSelection(sql,args);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return CustomerData;
	}

	public String[][] getByProducts(String sdate, String edate) 
	{
		String[][] CustomerData = new String[0][0];
		String sql = ""; 
		String args[] = new String[2];
		try 
		{
			args[0] = sdate;
			args[1] = edate;
			sql = "select product_id,sum(premium),count(policy_no) from position_master where inception_date between to_date(?,'dd-mm-yyyy') and to_date(?,'dd-mm-yyyy')+1 and  policy_no is not null and  premium is not null and  status='P' group by product_id";
		
			CustomerData = runner.multipleSelection(sql,args);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return CustomerData;
	}

	public String getProName(String pid) 
	{
		String pname = "";
		String sql = ""; 
		String args[] = new String[1];
		try
		{
			args[0] = pid;
			sql = "select product_name from product_master where product_id=?";
			pname = runner.singleSelection(sql,args);
		} 
		catch (Exception e) 
		{
			System.out.println("Exception in getting pname" + e.toString());
			e.printStackTrace();
		}
		return pname;
	}

	public String[][] getByBrokers(String sdate, String edate, String pid) 
	{
		String args[] = new String[3];
		String[][] CustomerData = new String[0][0];
		String sql = ""; 
		try 
		{
			args[0] = sdate;
			args[1] = edate;
			args[2] = pid;
			sql = "select b.created_by,count(a.policy_no),sum(a.premium) from position_master a,login_master b where  b.created_by in(select created_by from login_master where login_id in (select distinct login_id from position_master) and created_by is not null and lower(created_by) not in('admin'))and a.login_id=b.login_id and a.inception_date between to_date(?,'dd-mm-yyyy') and to_date(?,'dd-mm-yyyy')+1 and a.status='P' and a.product_id=? and policy_no is not null and  premium is not null group by b.created_by";

			CustomerData = runner.multipleSelection(sql,args);
		} 
		catch (Exception e) 
		{
			System.out.println("Exception in getByBrokers "+ e.toString());
			e.printStackTrace();
		}
		return CustomerData;
	}

	public String[][] getUserBrokerInformation(String login) 
	{
		String[][] UserBroker = new String[0][0];
		String args[] = new String[1];
		try 
		{
			args[0] = login;
			String sql = "select application_id,login_id,agency_code,oa_Code,first_name from personal_info where oa_code=(select oa_code from login_master where login_id=?) and (login_id!='NONE' or application_id='4')";
			UserBroker = runner.multipleSelection(sql,args);
		}
		catch (Exception e22) 
		{
			System.out.println("Exception ...... " + e22);
			e22.printStackTrace();
		}
		return UserBroker;
	}

	public String[][] getUsersByBrokers(String sdate, String edate, String pid) 
	{
		String[][] CustomerData = new String[0][0];
		String sql = ""; 
		String args[] = new String[3];
		try 
		{
			args[0] = sdate;
			args[1] = edate;
			args[2] = pid;

			sql = "select login_id,count(policy_no),sum(premium) from position_master where login_id in(select login_id from login_master where lower(created_by)='admin') and inception_date between to_date(?,'dd-mm-yyyy') and to_date(?,'dd-mm-yyyy')+1 and status='P'  and  product_id=? and policy_no is not null and  premium is not null  group by login_id";
			CustomerData = runner.multipleSelection(sql,args);

		}
		catch (Exception e) 
		{
			System.out.println("Exception in getUsersByBrokers "+ e.toString());
			e.printStackTrace();
		}
		return CustomerData;
	}

	public String[][] getByUsers(String brokerid, String sdate, String edate,String pid) 
	{
		String args[] = new String[4];
		String[][] CustomerData = new String[0][0];
		String sql = ""; 
		try 
		{
			args[0] = brokerid;
			args[1] = sdate;
			args[2] = edate;
			args[3] = pid;
			sql = "select login_id,sum(premium),count(policy_no) from position_master where login_id in(select login_id from login_master where oa_code=?) and inception_date between to_date(?,'dd-mm-yyyy') and to_date(?,'dd-mm-yyyy')+1 and status='P'  and product_id=? and policy_no is not null and  premium is not null group by login_id";
			CustomerData = runner.multipleSelection(sql,args);
		} 
		catch (Exception e) 
		{
			System.out.println("Exception in getByUsers  "+ e.toString());
			e.printStackTrace();
		}
		return CustomerData;
	}

	public String[][] getCustomers(String userid, String sdate, String edate,String pid) 
	{
		String args[] = new String[4];
		String[][] CustomerData = new String[0][0];
		String sql = ""; 
		try 
		{
			args[0] = userid;
			args[1] = sdate;
			args[2] = edate;
			args[3] = pid;
			sql = "select a.first_name,b.policy_no,to_char(b.inception_date,'dd-mm-yyyy'),to_char(b.expiry_date,'dd-mm-yyyy'),c.total_sum_insured,b.premium from personal_info a,position_master b,marine_result c where b.quote_no=c.quote_no and a.customer_id=b.customer_id and b.login_id=? and b.premium is not null and b.policy_no is not null and b.inception_date between to_date(?,'dd-mm-yyyy') and to_date(?,'dd-mm-yyyy')+1 and b.status='P' and b.product_id=? and b.policy_no is not null";
			CustomerData = runner.multipleSelection(sql,args);
		}
		catch (Exception e) 
		{
			System.out.println("Exception in getCustomers "+ e.toString());
			e.printStackTrace();
		}
		return CustomerData;
	}

	public String getBrokerName(String brokerid) 
	{
		String pname = "";
		String args[] = new String[1];
		String sql = ""; 
		try 
		{
			args[0] = brokerid;
			sql = "select first_name from personal_info where login_id=? and application_id='2'";
			pname = runner.singleSelection(sql,args);
			
		} catch (Exception e) {
			System.out.println("Exception in getting pname" + e.toString());
		}
		return pname;
	}

	public String getBrokerNameByLoginID(String loginid) 
	{
		String pname = "";
		String sql = ""; 
		String args[] = new String[1];
		try 
		{
			args[0] = loginid;
			sql = "select first_name from personal_info where agency_code=(select oa_Code from login_master where login_id=?) and application_id='2'";
			pname = runner.singleSelection(sql,args);
		} 
		catch (Exception e) 
		{
			System.out.println("Exception in getting pname" + e.toString());
			e.printStackTrace();
		}
		return pname;
	}

	public String[][] getBrokerNameByLoginID123(String loginid) 
	{
		String pname[][] = new String[0][0];
		String sql = "select distinct bro.company_name,log.login_id,log.oa_code from BROKER_COMPANY_MASTER bro, LOGIN_USER_DETAILS log where bro.AGENCY_CODE in(select oa_code from LOGIN_MASTER  where login_id in ("+loginid+")) and bro.AGENCY_CODE=log.AGENCY_CODE and log.login_id != 'NONE'";
		try 
		{
			pname = runner.multipleSelection(sql);
		} 
		catch (Exception e) 
		{
			System.out.println("ERROR " + e.toString());
			e.printStackTrace();
		}
		return pname;
	}

	public String getAEName(String brokerid) 
	{
		String pname = "";
		String sql =  "";
		String args[] = new String[1];
		try 
		{
			args[0] = brokerid;
			sql = "select first_name from personal_info where login_id=? and application_id='4'";
			pname = runner.singleSelection(sql,args);
		}
		catch (Exception e) 
		{
			System.out.println("Exception in getting pname" + e.toString());
			e.printStackTrace();
		} 
		return pname;
	}

	public String[][] getProducts() 
	{
		String[][] products = new String[0][0];
		String sql ="";
		try
		{
			sql = "select PRODUCT_ID,PRODUCT_NAME,COMPANY_ID from product_master where status='Y' and product_id in (3,11)";
			products = runner.multipleSelection(sql);
		}
		catch (Exception e) 
		{
			System.out.println("Exception in getting products" + e.toString());
			e.printStackTrace();
		}
		return products;
	}

	public String getCustomerName(String brokerid) 
	{
		String pname = "";
		String args[] = new String[1];
		String sql = "";
		try 
		{
			args[0] = brokerid;
			sql = "select first_name from personal_info where login_id=? and application_id='3'";
			pname = runner.singleSelection(sql,args);
		
		}
		catch (Exception e) 
		{
			System.out.println("Exception in getting pname" + e.toString());
			e.printStackTrace();
		}
		return pname;
	}

	public String[][] getAdminReferal() 
	{
		String pname = "";
		String loginids = "";
		String[][] ss = new String[0][0];
		String status123 = "";
		try 
		{
			String sql = "select to_char(a.entry_date,'YYYY-MM-DD'),count(a.entry_date) from position_master a,marine_data b,personal_info pi where a.REMARKS in ('Normal','NORMAL_EXCESS_PRICE','Admin') and a.status='Y' and  a.application_no=b.application_no and pi.customer_id=a.customer_id and b.ADMIN_REFERRAL_STATUS='Y'  group by to_char(a.entry_date,'YYYY-MM-DD') order by to_char(a.entry_date,'YYYY-MM-DD') desc";
			ss = runner.multipleSelection(sql);
		}
		catch (Exception e) 
		{
			System.out.println("Exception in gettin refferal data"	+ e.toString());
			e.printStackTrace();
		} 
		return ss;
	}

	public String[][] getReferal(String pid) 
	{
		String pname = "";
		String loginids = "";
		String[][] ss = new String[0][0];
		String status123 = "";
		String args[] = new String[1];
		try 
		{
			args[0] = pid;
			String sql = "select to_char(a.entry_date,'YYYY-MM-DD'),count(a.entry_date) from position_master a,marine_data b where ((a.REMARKS in ('Referal') and b.ADMIN_REFERRAL_STATUS='N') or (a.REMARKS in ('Admin','Normal','NORMAL_EXCESS_PRICE') and b.ADMIN_REFERRAL_STATUS='Y')) and a.status='Y' and a.product_id=? and  a.application_no=b.application_no  group by to_char(a.entry_date,'YYYY-MM-DD') order by to_char(a.entry_date,'YYYY-MM-DD') desc";
			ss = runner.multipleSelection(sql,args);
		} 
		catch (Exception e) 
		{
			System.out.println("Exception in gettin refferal data"+ e.toString());
			e.printStackTrace();
		} 
		return ss;
	}

	public String[][] getApproved(String pid) 
	{
		String args[] = new String[1];
		String pname = "";
		String loginids = "";
		String[][] ss = new String[0][0];
		String status123 = "";
		try 
		{
			args[0] = pid;
			String sql = "select to_char(a.entry_date,'YYYY-MM-DD'),count(a.entry_date) from position_master a,marine_data b where a.REMARKS in ('Admin','NORMAL_EXCESS_PRICE') and a.application_no=b.application_no and b.ADMIN_REFERRAL_STATUS='N' and a.product_id=? and a.status in('Y') group by to_char(a.entry_date,'YYYY-MM-DD') order by to_char(a.entry_date,'YYYY-MM-DD') desc";
			ss = runner.multipleSelection(sql,args);
		} 
		catch (Exception e) 
		{
			System.out.println("Exception in gettin refferal data"	+ e.toString());
			e.printStackTrace();
		} 
		return ss;
	}

	public String[][] getPortFolio(String sdate, String edate, String status,String pid) 
	{
		String pname = "";
		String loginids = "";
		String addeddata = "";
		if (pid.equals("all")) {
			addeddata = "";
		} else {
			addeddata = "and  a.product_id='" + pid + "'";
		}
		String[][] ss = new String[0][0];
		String status123 = "";
		String sql = "";
		try {
			if (status.equalsIgnoreCase("p")) {
				sql = "select to_char(a.inception_date,'YYYY-MM-DD'),count(a.inception_date) from position_master a,marine_data b,personal_info c where  a.inception_date between to_date('"+ sdate	+ "','dd-mm-yyyy') and to_date('"+ edate+ "','dd-mm-yyyy')+1  and  a.customer_id=c.customer_id and a.application_no=b.application_no "+ addeddata+ " and lower(a.status) in('"+ status.toLowerCase()+ "') group by to_char(a.inception_date,'YYYY-MM-DD') order by to_char(a.inception_date,'YYYY-MM-DD') desc";
			} else {
				sql = "select to_char(a.entry_date,'YYYY-MM-DD'),count(a.entry_date) from position_master a,marine_data b,personal_info c where  a.entry_date between to_date('"+ sdate	+ "','dd-mm-yyyy') and to_date('"+ edate+ "','dd-mm-yyyy')+1  and a.remarks in ('Normal') and   a.customer_id=c.customer_id and a.application_no=b.application_no "	+ addeddata	+ "  and   lower(a.status) in('y') group by to_char(a.entry_date,'YYYY-MM-DD') order by to_char(a.entry_date,'YYYY-MM-DD') desc";
			}
			ss = runner.multipleSelection(sql);
		} 
		catch (Exception e) 
		{
			System.out.println("Exception in gettin refferal data"	+ e.toString());
			e.printStackTrace();
		} 
		return ss;
	}

	public String[][] getByQuote(String qno) 
	{
		String pname = "";
		String args[] = new String[1];
		String loginids = "";
		String[][] ss = new String[0][0];
		String status123 = "";
		String sql = "";
		try 
		{
			args[0] =  qno.trim()+"%";
			sql = "select pm.application_no,pm.login_id,pm.quote_no,pm.remarks,pi.first_name,md.premium,nvl(md.EXCESS_PREMIUM,'0'),pi.COMPANY_NAME,pi.LAST_NAME,pm.quote_no,to_char(pm.entry_date,'DD-MM-YYYY'),pm.status,pm.POLICY_NO,md.remarks from position_master pm,marine_data md,personal_info pi where pm.quote_no like ? and pm.application_no=md.application_no and pi.customer_id=pm.customer_id  and md.ADMIN_REFERRAL_STATUS='Y' and  pm.remarks in ('Normal','NORMAL_EXCESS_PRICE','Admin') and lower(pm.status) in('y')  order by pm.quote_no desc";
			ss = runner.multipleSelection(sql,args);
		} 
		catch (Exception e) 
		{
			System.out.println("Exception in gettin refferal data"+ e.toString());
			e.printStackTrace();
		} 
		return ss;
	}

	public String[][] getPortfolioByPolicy_no(String policy_no, String status) 
	{
		String args[] = new String[1];
		String pname = "";
		String[][] ss = new String[0][0];
		String[][] multiPolicy = new String[0][0];
		String status123 = "";
		try 
		{
			String sql = "";
			String sqlMultiple = "";
			args[0] ="%"+policy_no+"%";
			if (status.equalsIgnoreCase("policy_search")) 
			{
				sql = "select pm.application_no,pm.login_id,pm.quote_no,pm.remarks,pi.first_name,pm.premium,nvl(pm.EXCESS_PREMIUM,'0'),pi.COMPANY_NAME,pi.LAST_NAME,pm.POLICY_NO,md.remarks,pm.PDF_MODIFY_CLAUSE,pm.PDF_MODIFY_WARRANTY,pm.PDF_MODIFY_EXCLUSION,pm.PDF_MODIFY_EXTRACLAUSES,pm.PRODUCT_ID from position_master pm,marine_data md,personal_info pi where pm.policy_no like ? and pm.application_no=md.application_no and pi.customer_id=pm.customer_id and lower(pm.status) in('p')  and nvl(pm.open_cover_int_status,'0') not in ('LINKED') order by pm.quote_no desc";
			}
			else 
			{
				sql = "select pm.application_no,pm.login_id,pm.quote_no,pm.remarks,pi.first_name,pm.premium,nvl(pm.EXCESS_PREMIUM,'0'),pi.COMPANY_NAME,pi.LAST_NAME,pm.POLICY_NO,md.remarks,pm.PDF_MODIFY_CLAUSE,pm.PDF_MODIFY_WARRANTY,pm.PDF_MODIFY_EXCLUSION,pm.PDF_MODIFY_EXTRACLAUSES,pm.PRODUCT_ID from position_master pm,marine_data md,personal_info pi where pm.policy_no like ? and pm.application_no=md.application_no and pi.customer_id=pm.customer_id and  lower(pm.status) in('p')  and nvl(pm.open_cover_int_status,'0') not in ('LINKED') order by pm.quote_no desc";
			}
			ss = runner.multipleSelection(sql,args);
		} 
		catch (Exception e) 
		{
			System.out.println("Exception in gettin refferal data"+ e.toString());
			e.printStackTrace();
		} 
		return ss;
	}

	public String[][] getPortfolioByDate(String eDate, String type,String status, String pid) 
	{
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
				sql = "select pm.application_no,pm.login_id,pm.quote_no,pm.remarks,pi.first_name,pm.premium,nvl(pm.EXCESS_PREMIUM,'0'),pi.COMPANY_NAME,pi.LAST_NAME,pm.POLICY_NO,md.remarks,pm.PDF_MODIFY_CLAUSE,pm.PDF_MODIFY_WARRANTY,pm.PDF_MODIFY_EXCLUSION,pm.PDF_MODIFY_EXTRACLAUSES,pm.PRODUCT_ID from position_master pm,marine_data md,personal_info pi where to_char(pm.inception_date,'YYYY-MM-DD')='"+ eDate+ "' and pm.application_no=md.application_no and pi.customer_id=pm.customer_id  "+ addeddata+ " and lower(pm.status) in('"+ status.toLowerCase()+ "')  and nvl(pm.open_cover_int_status,'0') not in ('LINKED') order by pm.quote_no desc";
				sqlMultiple = "select (a.policy_no),a.login_id,nvl(a.open_cover_no,'0'),sum(a.premium),sum(a.excess_premium) from position_master a where  nvl(a.open_cover_int_status,'0') in ('LINKED') and  to_char(a.inception_date,'YYYY-MM-DD')='"+ eDate	+ "'  "	+ addeddata	+ " and lower(a.status) in('"+ status.toLowerCase()	+ "')  and nvl(a.open_cover_int_status,'0') not in ('LINKED') group by a.policy_no,a.login_id,a.open_cover_no order by a.policy_no";
			} else if (status.equalsIgnoreCase("c")) {
				sql = "select pm.application_no,pm.login_id,pm.quote_no,pm.remarks,pi.first_name,md.premium,nvl(md.EXCESS_PREMIUM,'0'),pi.COMPANY_NAME,pi.LAST_NAME,pm.quote_no,md.remarks,pm.PDF_MODIFY_CLAUSE,pm.PDF_MODIFY_WARRANTY,pm.PDF_MODIFY_EXCLUSION,pm.PDF_MODIFY_EXTRACLAUSES,pm.PRODUCT_ID from position_master pm,marine_data md,personal_info pi where to_char(pm.entry_date,'YYYY-MM-DD')='"+ eDate	+ "' and pm.application_no=md.application_no and pi.customer_id=pm.customer_id and pm.remarks in ('Normal')  "+ addeddata	+ " and    lower(pm.status)  in('y') and nvl(pm.open_cover_int_status,'0') not in ('LINKED')  order by pm.quote_no desc";
			} else {
				sql = "select pm.application_no,pm.login_id,pm.quote_no,pm.remarks,pi.first_name,md.premium,nvl(md.EXCESS_PREMIUM,'0'),pi.COMPANY_NAME,pi.LAST_NAME,pm.quote_no,md.remarks,pm.PDF_MODIFY_CLAUSE,pm.PDF_MODIFY_WARRANTY,pm.PDF_MODIFY_EXCLUSION,pm.PDF_MODIFY_EXTRACLAUSES,pm.PRODUCT_ID from position_master pm,marine_data md,personal_info pi where to_char(pm.entry_date,'YYYY-MM-DD')='"+ eDate	+ "' and pm.application_no=md.application_no and pi.customer_id=pm.customer_id and md.ADMIN_REFERRAL_STATUS='Y' and  pm.remarks in ('Normal','NORMAL_EXCESS_PRICE','Admin') and "+ addeddata	+ " lower(pm.status)  in('y') and nvl(pm.open_cover_int_status,'0') not in ('LINKED') order by pm.quote_no desc";
			}
			ss = runner.multipleSelection(sql);
			multiPolicy = runner.multipleSelection(sqlMultiple);
		} 
		catch (Exception e) 
		{
			System.out.println("Exception in gettin refferal data"	+ e.toString());
			e.printStackTrace();
		} 
		return ss;
	}

	public String[][] getPortfolioByDateMulti(String eDate, String type,String status, String pid) 
	{
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
				sql = "select pm.application_no,pm.login_id,pm.quote_no,pm.remarks,pi.first_name,pm.premium,nvl(pm.EXCESS_PREMIUM,'0'),pi.COMPANY_NAME,pi.LAST_NAME,pm.POLICY_NO,md.remarks,pm.PDF_MODIFY_CLAUSE,pm.PDF_MODIFY_WARRANTY,pm.PDF_MODIFY_EXCLUSION,pm.PDF_MODIFY_EXTRACLAUSES,pm.PRODUCT_ID from position_master pm,marine_data md,personal_info pi where to_char(pm.inception_date,'YYYY-MM-DD')='"+ eDate	+ "' and pm.application_no=md.application_no and pi.customer_id=pm.customer_id  "+ addeddata+ " and     lower(pm.status) in('"+ status.toLowerCase()+ "')  and nvl(pm.open_cover_int_status,'0') not in ('LINKED') order by pm.quote_no desc";
				sqlMultiple = "select (a.policy_no),a.login_id,nvl(a.open_cover_no,'0'),sum(a.premium),sum(a.excess_premium) from position_master a where   to_char(a.inception_date,'YYYY-MM-DD')='"+ eDate+ "'  and lower(a.status) in('"	+ status.toLowerCase()+ "') and nvl(a.open_cover_int_status,'0') in ('LINKED')    group by a.policy_no,a.login_id,a.open_cover_no order by a.policy_no";
			} else if (status.equalsIgnoreCase("c")) {
				sql = "select pm.application_no,pm.login_id,pm.quote_no,pm.remarks,pi.first_name,md.premium,nvl(md.EXCESS_PREMIUM,'0'),pi.COMPANY_NAME,pi.LAST_NAME,pm.quote_no,md.remarks,pm.PDF_MODIFY_CLAUSE,pm.PDF_MODIFY_WARRANTY,pm.PDF_MODIFY_EXCLUSION,pm.PDF_MODIFY_EXTRACLAUSES,pm.PRODUCT_ID from position_master pm,marine_data md,personal_info pi where to_char(pm.entry_date,'YYYY-MM-DD')='"+ eDate	+ "' and pm.application_no=md.application_no and pi.customer_id=pm.customer_id and pm.remarks in ('Normal')  "+ addeddata	+ " and    lower(pm.status)  in('y') and nvl(pm.open_cover_int_status,'0') not in ('LINKED')  order by pm.quote_no desc";
			} else {
				sql = "select pm.application_no,pm.login_id,pm.quote_no,pm.remarks,pi.first_name,md.premium,nvl(md.EXCESS_PREMIUM,'0'),pi.COMPANY_NAME,pi.LAST_NAME,pm.quote_no,md.remarks,pm.PDF_MODIFY_CLAUSE,pm.PDF_MODIFY_WARRANTY,pm.PDF_MODIFY_EXCLUSION,pm.PDF_MODIFY_EXTRACLAUSES,pm.PRODUCT_ID from position_master pm,marine_data md,personal_info pi where to_char(pm.entry_date,'YYYY-MM-DD')='"+ eDate	+ "' and pm.application_no=md.application_no and pi.customer_id=pm.customer_id and md.ADMIN_REFERRAL_STATUS='Y' and  pm.remarks in ('Normal','NORMAL_EXCESS_PRICE','Admin')      "	+ addeddata	+ " lower(pm.status)  in('y') and nvl(pm.open_cover_int_status,'0') not in ('LINKED') order by pm.quote_no desc";
			}
			multiPolicy = runner.multipleSelection(sqlMultiple);
		} 
		catch (Exception e) 
		{
			System.out.println("Exception in gettin refferal data"	+ e.toString());
			e.printStackTrace();
		} 
		return multiPolicy;
	}

	public String[][] getPortfolioByDateMulti(String eDate, String pid,	String policyno) 
	{
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
			sql = "select pm.application_no,pm.login_id,pm.quote_no,pm.remarks,pi.first_name,pm.premium,nvl(pm.EXCESS_PREMIUM,'0'),pi.COMPANY_NAME,pi.LAST_NAME,pm.POLICY_NO,md.remarks,pm.PDF_MODIFY_CLAUSE,pm.PDF_MODIFY_WARRANTY,pm.PDF_MODIFY_EXCLUSION,pm.PDF_MODIFY_EXTRACLAUSES,pm.PRODUCT_ID from position_master pm,marine_data md,personal_info pi where to_char(pm.inception_date,'YYYY-MM-DD')='"+ eDate	+ "' and pm.application_no=md.application_no and pi.customer_id=pm.customer_id  "+ addeddata+ " and     lower(pm.status) in('p') and pm.policy_no='"+ policyno+ "' and nvl(pm.open_cover_int_status,'0')  in ('LINKED') order by pm.quote_no desc";
			multiPolicy = runner.multipleSelection(sql);
		} 
		catch (Exception e) 
		{
			System.out.println("Exception in gettin refferal data"+ e.toString());
			e.printStackTrace();
		}
		return multiPolicy;
	}

	public String[][] getReferalByDate(String eDate, String type, String pid) 
	{
		String pname = "";
		String loginids = "";
		String[][] ss = new String[0][0];
		String status123 = "";
		String args[] = new String[2];
		try 
		{
			args[0] = eDate;
			args[1] = pid;

			String sql = "select pm.application_no,pm.login_id,pm.quote_no,md.remarks from position_master pm,marine_data md where to_char(pm.entry_date,'YYYY-MM-DD')=? and pm.application_no=md.application_no  and pm.product_id=? and ((pm.REMARKS in ('Referal') and md.ADMIN_REFERRAL_STATUS='N') or (pm.REMARKS in ('Admin','Normal','NORMAL_EXCESS_PRICE') and  md.ADMIN_REFERRAL_STATUS='Y'))  and pm.status='Y'  order by pm.quote_no desc";
			ss = runner.multipleSelection(sql,args);
		} 
		catch (Exception e) 
		{
			System.out.println("Exception in gettin refferal data"+ e.toString());
			e.printStackTrace();
		}
		return ss;
	}

	public HashMap getCommodity(String quoteNo, String commodityId,String rag1, String rag2, String rag3,String branch) 
	{
		String commodityId1 = "";
		String[][] ss = new String[0][0];
		String ragVal = "";
		String sql = "";
		HashMap commHash = new HashMap();
		System.out.println("Quottttt" + quoteNo);
		if (!"R".equalsIgnoreCase(rag1) && !"A".equalsIgnoreCase(rag2) && !"G".equalsIgnoreCase(rag3)) {
			ragVal = "";
		} else {
			ragVal = "and mc.rag in ('" + rag1 + "','" + rag2 + "','" + rag3+ "')";
		}
		if (commodityId.length() > 0) {
			commodityId = commodityId.substring(1, commodityId.length());
			commodityId = commodityId.trim();
			commodityId1 = "and oc.commodity_id in (" + commodityId + ")";
		}

		/***** To Form the quote String ******/
		String tempQuot ="";
		tempQuot = quoteNo;
		StringTokenizer tokenQuote = new StringTokenizer(quoteNo,",");
		int quotCount=0;
		int start=0;
		int s=0;
		int end=500;
		int counter=500;
		int real,decimal;
		quotCount = tokenQuote.countTokens();
		System.out.println("Total Number Of Quotes ..."+quotCount);
		String qno[] = new String[quotCount];
		int q=0;
		while(tokenQuote.hasMoreTokens())
		{
			qno[q++] = tokenQuote.nextToken();
		}			
		real = quotCount / counter;
		decimal = quotCount % counter;
		if(decimal > 0 || real == 0)
		{
			real = real +1;
		}
		for(int p=0;p<real;p++)
		{
			String qryQuot="";
			if(p!=0)
			{
				start = end;
				end = end + counter;
			}
			if(decimal > 0 && (p+1 == real))
			{
				end = quotCount;
			}
			for(s=start;s<end;s++)
			{
				qryQuot =qryQuot+qno[s]+",";
			}
			qryQuot = qryQuot.substring(0,qryQuot.length()-1);
		
		/***** To Form the quote String ******/


		try {
			if ("'ALL'".equalsIgnoreCase(commodityId)|| "all".equalsIgnoreCase(commodityId)) {
				sql = "";
				sql = "select oc.commodity_name, mc.commodity_id, mc.description, oc.rag, round(mc.sum_insured), nvl(mc.war_charges,0),pos.quote_no,round((nvl(mc.suminsuredlocal,'0')+nvl(mc.SALE_TERM_CHARGES,'0')+nvl(mc.TOLERANCE_CHARGES,'0'))), nvl(mc.exchange_rate,''),nvl(mc.rate,'0'),round((round(nvl(mc.suminsuredlocal,'0')+nvl(mc.SALE_TERM_CHARGES,'0')+nvl(mc.TOLERANCE_CHARGES,'0')))/m.exchange_rate) from commoditymaster oc, marine_result_details mc,position_master pos, marine_data m where mc.commodity_id = oc.commodity_id and oc.amend_id = (select max(pc.amend_id) from commoditymaster pc where mc.commodity_id = pc.commodity_id  and pc.branch_code='"+branch+"') and oc.branch_code='"+branch+"' and  pos.application_no = m.application_no and mc.application_no=pos.application_no and mc.application_no in (select p.application_no from position_master p where p.quote_no in("+ qryQuot + ")) " + ragVal + " ";
			} else {
				sql = "";
				sql = "select oc.commodity_name, mc.commodity_id, mc.description, oc.rag, round(mc.sum_insured), nvl(mc.war_charges,0),pos.quote_no,round((nvl(mc.suminsuredlocal,'0')+nvl(mc.SALE_TERM_CHARGES,'0')+nvl(mc.TOLERANCE_CHARGES,'0'))),nvl(mc.exchange_rate,''),nvl(mc.rate,'0'),round((round(nvl(mc.suminsuredlocal,'0')+nvl(mc.SALE_TERM_CHARGES,'0')+nvl(mc.TOLERANCE_CHARGES,'0')))/m.exchange_rate)  from commoditymaster oc, marine_result_details mc,position_master pos, marine_data m where mc.commodity_id = oc.commodity_id and oc.amend_id = (select max(pc.amend_id) from commoditymaster pc where mc.commodity_id = pc.commodity_id and pc.branch_code='"+branch+"') and oc.branch_code='"+branch+"' and mc.application_no=pos.application_no and pos.application_no = m.application_no and mc.application_no in (select p.application_no from position_master p where p.quote_no in("	+ qryQuot + ")) " + commodityId1 + "  " + ragVal + "  ";
			}
			ss = runner.multipleSelection(sql);
			for (int j = 0; j < ss.length; j++) 
			{
				int comFlag = 0;
				String result[][] = new String[1][11];
				StringTokenizer st = new StringTokenizer(qryQuot, ",");
				while (st.hasMoreTokens()) {
					if (ss[j][6].equalsIgnoreCase(st.nextToken())) {
						result[0][0] = ss[j][0];
						result[0][1] = ss[j][1];
						result[0][2] = ss[j][2];
						result[0][3] = ss[j][3];
						result[0][4] = ss[j][4];
						result[0][5] = ss[j][5];
						result[0][6] = ss[j][6];
						result[0][7] = ss[j][7];
						result[0][8] = ss[j][8];
						result[0][9] = ss[j][9];
						result[0][10] = ss[j][10];
						comFlag = 1;
					}
				}
				if (!commHash.containsKey("comm" + ss[j][6]) && comFlag == 1) {
					commHash.put("comm" + ss[j][6], result);
				} else if (commHash.containsKey("comm" + ss[j][6])
						&& comFlag == 1) {
					
					String res[][] = new String[0][0];
					res = (String[][]) commHash.get("comm" + ss[j][6]);
					
					
					int len = res.length + result.length;
					String newRes[][] = new String[len][11];
					int n = 0;
					for (int r = 0; r < res.length; r++, n++) {
						newRes[n][0] = res[r][0];
						newRes[n][1] = res[r][1];
						newRes[n][2] = res[r][2];
						newRes[n][3] = res[r][3];
						newRes[n][4] = res[r][4];
						newRes[n][5] = res[r][5];
						newRes[n][6] = res[r][6];
						newRes[n][7] = res[r][7];
						newRes[n][8] = res[r][8];
						newRes[n][9] = res[r][9];
						newRes[n][10] = res[r][10];
					}
					for (int r = 0; r < result.length; r++, n++) {
						newRes[n][0] = result[r][0];
						newRes[n][1] = result[r][1];
						newRes[n][2] = result[r][2];
						newRes[n][3] = result[r][3];
						newRes[n][4] = result[r][4];
						newRes[n][5] = result[r][5];
						newRes[n][6] = result[r][6];
						newRes[n][7] = result[r][7];
						newRes[n][8] = result[r][8];
						newRes[n][9] = result[r][9];
						newRes[n][10] = result[r][10];
					}
					commHash.put("comm" + ss[j][6], newRes);
				}
			}
		System.out.println("Size is" + commHash.size());
		} catch (Exception e) {
			System.out.println("Exception in gettin refferal data"+ e.toString());
			e.printStackTrace();
		} 
	  }// Outer For Loop
		return commHash;
	}

	public HashMap getTotalLCDetails(String quoteNo,String cid) 
	{
		String sql = "";
		java.util.HashMap commHash = new java.util.HashMap();
		String[][] ss = new String[0][0];
		String[][] ss1 = new String[0][0];

		/***** To Form the quote String ******/
		String tempQuot ="";
		tempQuot = quoteNo;
		StringTokenizer tokenQuote = new StringTokenizer(quoteNo,",");
		int quotCount=0;
		int start=0;
		int s=0;
		int end=500;
		int counter=500;
		int real,decimal;
		quotCount = tokenQuote.countTokens();

		String qno[] = new String[quotCount];
		int q=0;
		while(tokenQuote.hasMoreTokens())
		{
			qno[q++] = tokenQuote.nextToken();
		}			
		real = quotCount / counter;
		decimal = quotCount % counter;
		if(decimal > 0 || real == 0)
		{
			real = real +1;
		}
		for(int p=0;p<real;p++)
		{
			String qryQuot="";
			if(p!=0)
			{
				start = end;
				end = end + counter;
			}
			if(decimal > 0 && (p+1 == real))
			{
				end = quotCount;
			}
			for(s=start;s<end;s++)
			{
				qryQuot =qryQuot+qno[s]+",";
			}
			qryQuot = qryQuot.substring(0,qryQuot.length()-1);
		
		/***** To Form the quote String ******/

		try 
		{
			sql = "select nvl(opbm.bank_name,''),nvl(oplc.lc_number,''),nvl(b.open_lc_id,''), nvl(b.open_bank_id,''), b.application_no,pos.quote_no from marine_data b, open_cover_bank_master opbm,open_cover_lc_master oplc,position_master pos where b.application_no in (select application_no from position_master where quote_no in("	+ qryQuot+ ")) and b.open_lc_id = oplc.lc_id and b.open_bank_id = oplc.bank_id and b.open_bank_id = opbm.bank_id and b.OPEN_COVER_NO=oplc.OPEN_COVER_NO and pos.application_no=b.application_no and opbm.BELONGING_COUNTRY_ID='"+cid+"'";
			ss = runner.multipleSelection(sql);
			if (ss.length > 0) 
			{
				for (int j = 0; j < ss.length; j++) 
				{
					int comFlag = 0;
					String result[][] = new String[1][6];
					StringTokenizer st = new StringTokenizer(qryQuot, ",");
					while (st.hasMoreTokens()) 
					{
						if (ss[j][5].equalsIgnoreCase(st.nextToken())) 
						{
							result[0][0] = ss[j][0];
							result[0][1] = ss[j][1];
							result[0][2] = ss[j][2];
							result[0][3] = ss[j][3];
							result[0][4] = ss[j][4];
							result[0][5] = ss[j][5];
							comFlag = 1;
						}
					}
					if (comFlag == 0)
						commHash.put("bank" + ss[j][5], ss1);
					if (!commHash.containsKey("bank" + ss[j][5])&& comFlag == 1) 
					{
						commHash.put("bank" + ss[j][5], result);
					} 
					else if (commHash.containsKey("bank" + ss[j][5])&& comFlag == 1) 
					{
						String res[][] = new String[0][0];
						res = (String[][]) commHash.get("bank" + ss[j][5]);
						int len = res.length + result.length;
						String newRes[][] = new String[len][6];
						int n = 0;
						for (int r = 0; r < res.length; r++, n++) 
						{
							newRes[n][0] = res[r][0];
							newRes[n][1] = res[r][1];
							newRes[n][2] = res[r][2];
							newRes[n][3] = res[r][3];
							newRes[n][4] = res[r][4];
							newRes[n][5] = res[r][5];
						}
						for (int r = 0; r < result.length; r++, n++) 
						{
							newRes[n][0] = result[r][0];
							newRes[n][1] = result[r][1];
							newRes[n][2] = result[r][2];
							newRes[n][3] = result[r][3];
							newRes[n][4] = result[r][4];
							newRes[n][5] = result[r][5];
						}
						commHash.put("bank" + ss[j][5], newRes);
					}
				}
			}
		}
		catch (Exception e) 
		{
			System.out.println("Exception in gettin refferal data"	+ e.toString());
		}
	  } // Outer For Loop
		return commHash;
	}

	public String[][] getCommoditylen(String quoteNo) 
	{
		String[][] ss = new String[0][0];
		String args[] = new String[1];
		try 
		{
			args[0] = quoteNo;
			String sql = "select count(*) from commoditymaster oc, marine_result_details mc where mc.commodity_id = oc.commodity_id and mc.application_no = (select p.application_no from position_master p where p.quote_no = ?)";
			ss = runner.multipleSelection(sql,args);
		} 
		catch (Exception e) 
		{
			System.out.println("Exception in gettin refferal data"+ e.toString());
			e.printStackTrace();
		}
		return ss;
	}

	public String[][] getCountry() 
	{		
		String[][] ss = new String[0][0];
		try
		{
			String sql = "select country_id, country_name from country_master where status = 'Y' order by country_name";
			ss = runner.multipleSelection(sql);
		}
		catch (Exception e) 
		{
			System.out.println("Exception in gettin refferal data"+ e.toString());
			e.printStackTrace();
		}
		return ss;
	}

	public String getDate(String month, String year) 
	{
		int val = 0;
		java.util.GregorianCalendar cal = new java.util.GregorianCalendar(Integer.parseInt(year), Integer.parseInt(month), 00);
		val = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		return "" + val;
	}

	public String[][] getQuoteSearch(String qno, String type) 
	{
		String pname = "";
		String loginids = "";
		String[][] ss = new String[0][0];
		String status123 = "";
		String args[] = new String[1];
		try 
		{
			args[0] = qno+"%";
			String sql = "";
			if (!type.equalsIgnoreCase("Referal")) {
				sql = "select pm.application_no,pi.login_id,pm.quote_no,md.remarks,pi.first_name,pi.last_name from position_master pm,marine_data md,personal_info pi where pm.quote_no like ? and pm.application_no=md.application_no and pi.customer_id=pm.customer_id and pm.REMARKS in ('Admin','NORMAL_EXCESS_PRICE') and md.ADMIN_REFERRAL_STATUS='N'  and lower(pm.status)  in('y') order by quote_no desc";
			}
			else {
				sql = "select pm.application_no,pi.login_id,pm.quote_no,md.remarks from position_master pm,marine_data md,personal_info pi where pm.quote_no like ?  and pm.application_no=md.application_no and pi.customer_id=pm.customer_id and ((pm.REMARKS in ('Referal') and md.ADMIN_REFERRAL_STATUS='N') or (pm.REMARKS in ('Admin','Normal','NORMAL_EXCESS_PRICE') and md.ADMIN_REFERRAL_STATUS='Y')) and pm.status='Y' order by pm.quote_no desc";
			}
			ss = runner.multipleSelection(sql,args);
		} catch (Exception e) {
			System.out.println("Exception in gettin refferal data"+ e.toString());
		} 
		return ss;
	}

	public String[][] getApprovedByDate(String eDate, String type, String pid) 
	{
		String pname = "";
		String loginids = "";
		String[][] ss = new String[0][0];
		String status123 = "";
		try {
			String sql = "select pm.application_no,pm.login_id,pm.quote_no,md.remarks from position_master pm,marine_data md where to_char(pm.entry_date,'YYYY-MM-DD')='"+ eDate	+ "' and pm.product_id='"+ pid+ "'  and pm.application_no=md.application_no and pm.REMARKS in ('"+ type+ "','NORMAL_EXCESS_PRICE') and md.ADMIN_REFERRAL_STATUS='N'  and pm.status  in('Y') order by quote_no desc";
			ss = runner.multipleSelection(sql);
		} catch (Exception e) {
			System.out.println("Exception in gettin refferal data"
					+ e.toString());
		} 
		return ss;
	}

	public String[][] getApprovedByDate(String eDate, String type) 
	{
		String pname = "";
		String loginids = "";
		String[][] ss = new String[0][0];
		String status123 = "";
		String args[] = new String[1];
		try 
		{
			args[0] = eDate;
			String sql = "select pm.application_no,pm.login_id,pm.quote_no,md.remarks from position_master pm,marine_data md where to_char(pm.entry_date,'YYYY-MM-DD')=? and pm.application_no=md.application_no and pm.REMARKS in ('"+type+"','NORMAL_EXCESS_PRICE') and md.ADMIN_REFERRAL_STATUS='N' and pm.status  in('Y') order by quote_no desc";
			ss = runner.multipleSelection(sql,args);
		} 
		catch (Exception e) 
		{
			System.out.println("Exception in gettin refferal data"+ e.toString());
			e.printStackTrace();
		} 
		return ss;
	}

	public String getAgencyCode(String logpersonId) 
	{
		String agencyCode = "";
		String sql = "";
		String args[] = new String[1];
		try 
		{
			args[0] = logpersonId;
			sql = "select agency_code from login_master where login_id=?";
			agencyCode = runner.singleSelection(sql);
		}
		catch (Exception e) 
		{
			System.out.println("Exception in getting agency code"+ e.toString());
			e.printStackTrace();
		} 
		return agencyCode;
	}

	public String[][] getQuotedPerson(String cid) 
	{
		String[][] brokerName = new String[0][0];
		String sql = ""; 
		String args[] = new String[1];
		try 
		{
			args[0] = cid;
			sql = "select application_id,first_name from personal_info where login_id=? and application_id in ('3','2')";
			brokerName = runner.multipleSelection(sql);
		}
		catch (Exception e) 
		{
			System.out.println("Exception in " + e.toString());
			e.printStackTrace();
		} 
		return brokerName;
	}

	public String[][] getBrokerNameByUserId123(String cid) 
	{
		String args[] = new String[1];
		String[][] brokerName = new String[0][0];
		String sql = ""; 
		try 
		{
			args[0] = cid;
			sql = "select a.first_name,b.company_name,c.usertype,a.application_id from personal_info a,broker_company_master b,login_master c where a.login_id=(select login_id from login_master where oa_code=(select oa_code from login_master where login_id=?) and usertype='Broker') and (a.application_id='2' or (a.application_id='1' ))  and b.agency_code=c.agency_code and a.customer_id=b.customer_id";
			brokerName = runner.multipleSelection(sql,args);
		} catch (Exception e) {
			System.out.println("Exception in " + e.toString());
		} 
		return brokerName;
	}

	public String getBrokerNameByUserId(String cid) 
	{
		String args[] = new String[1];
		String brokerName = "";
		String sql = ""; 
		try 
		{
			args[0] = cid;
			sql = "select first_name from personal_info where login_id=(select login_id from login_master where oa_code=(select oa_code from login_master where login_id=?) and usertype='Broker') and application_id='2'";
			brokerName = runner.singleSelection(sql,args);
		} 
		catch (Exception e) 
		{
			System.out.println("Exception in " + e.toString());
			e.printStackTrace();
		} 
		return brokerName;
	}

	public String[][] getViewQuotes123(String loginIds, String status) 
	{
		String loginids = "";
		String[][] valuess = new String[0][0];
		String[][] ss = new String[0][0];
		String status123 = "";
		if (status.equalsIgnoreCase("app"))
		{
			status123 = "Admin";
		} else if (status.equalsIgnoreCase("unapp")) {
			status123 = "Referal";
		}
		try {
			String sql = "select login_id from login_master where oa_code='"+ getAgencyCode(loginIds) + "' or created_by='"+ getAgencyCode(loginIds) + "' or login_id='" + loginIds	+ "'";
			valuess = runner.multipleSelection(sql);
			for (int i = 0; i < valuess.length; i++) {
				loginids = "'" + valuess[i][0] + "'," + loginids;
			}
			loginids = loginids.substring(0, loginids.lastIndexOf(','));
			sql = "select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,b.first_name,initcap(b.first_name),a.login_id,a.APPLICATION_NO from position_master a,personal_info b where a.login_id in ("+ loginids+ ") and a.status='Y' and b.customer_id=a.customer_id and a.REMARKS='"+ status123 + "' order by initcap(b.first_name)";
			ss = runner.multipleSelection(sql);
		} catch (Exception e) {
			System.out.println("Exception in gettin refferal data"
					+ e.toString());
		} 
		return ss;
	}

	public String[][] modeOfTransport(String adminBranch) 
	{
		String[][] ss = new String[0][0];
		String args[] = new String[1];
		try 
		{
			adminBranch = adminBranch.replaceAll("'","");
			args[0] = adminBranch;
			String qry = "select transport_description, mode_transport_id from mode_of_transport where status = 'Y' and branch_code=?";
			ss = runner.multipleSelection(qry,args);
		}
		catch (Exception e) 
		{
			System.out.println("Error in Query" + e);
			e.printStackTrace();
		} 
		return ss;
	}
//not used
	public String[][] getCovers(String id) 
	{
		String[][] ss = new String[0][0];
		String args[] = new String[1];
		try 
		{
			args[0] = id;
			String qry = "select cover_id, cover_name,mode_transport_id from cover_master where status ='Y' and mode_transport_id=?";
			ss = runner.multipleSelection(qry,args);
		} 
		catch (Exception e) 
		{
			System.out.println("Error in Query" + e);
			e.printStackTrace();
		} 
		return ss;
	}

	public HashMap getCoversTotal(String adminBranch) 
	{
		String[][] ss = new String[0][0];
		HashMap result = new HashMap();
		String args[] = new String[2];
		try 
		{
			
			adminBranch = adminBranch.replaceAll("'","");
			args[0] = adminBranch;
			args[1] = adminBranch;
			String qry = "select cover_id,cover_name,mode_transport_id from cover_master where status = 'Y' and branch_code=? and mode_transport_id in(select mode_transport_id from mode_of_transport where status = 'Y' and branch_code=?)";
			ss = runner.multipleSelection(qry,args);
			
			if (ss.length > 0) 
			{
				String modeId[] = new String[ss.length];
				for (int m = 0; m < ss.length; m++)
					modeId[m] = ss[m][2];
				for (int i = 0; i < ss.length; i++) {
					String res[][] = new String[1][3];
					if (ss[i][2].equalsIgnoreCase(modeId[i])) {
						res[0][0] = ss[i][0];
						res[0][1] = ss[i][1];
						res[0][2] = ss[i][2];
					}
					if (result.containsKey("modeId" + ss[i][2])) {
						String res1[][] = new String[0][0];
						res1 = (String[][]) result.get("modeId" + ss[i][2]);
						String finalRes[][] = new String[(res.length + res1.length)][3];
						int f = 0;
						for (int r = 0; r < res.length; r++) {
							finalRes[f][0] = res[r][0];
							finalRes[f][1] = res[r][1];
							finalRes[f][2] = res[r][2];
							f++;
						}
						for (int r = 0; r < res1.length; r++) {
							finalRes[f][0] = res1[r][0];
							finalRes[f][1] = res1[r][1];
							finalRes[f][2] = res1[r][2];
							f++;
						}
						result.put("modeId" + ss[i][2], finalRes);
					} else {
						result.put("modeId" + ss[i][2], res);
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Error in Query" + e);e.printStackTrace();
		} 
		return result;
	}

	public String[][] getCommodityList(String adminBranch) 
	{
		String[][] ss = new String[0][0];
		String args[] = new String[2];
		try 
		{
			adminBranch = adminBranch.replaceAll("'","");
			args[0] = adminBranch;
			args[1] = adminBranch;
			String qry = "select commodity_name, commodity_id  from commoditymaster cm where cm.status in ('Y','R') and cm.branch_code=? and cm.amend_id||'-'||cm.commodity_id in(select max(amend_id)||'-'||commodity_id from commoditymaster where to_date(effective_date,'dd-MON-YYYY') <=to_date(SYSDATE,'dd-MON-YYYY') and status in ('Y','R') and branch_code=? group by commodity_id) order by cm.commodity_id";
			ss = runner.multipleSelection(qry,args);
		}
		catch (Exception e) 
		{
			System.out.println("Error in Query" + e);
			e.printStackTrace();
		} 
		return ss;
	}

	public String[][] getQuoteNoCommodity(String commodity_id, String data1,String data2, String product_id) 
	{
		String[][] result = new String[0][0];
		try {
			if ("all".equalsIgnoreCase(product_id)) {
				product_id = "3,11";
			}
			String qry = "select  quote_no from position_master where application_no in (select distinct application_no from marine_result_details where commodity_id = '"+ commodity_id+ "' and product_id in ( "+ product_id+ " )  and (inception_date between to_date('"+ data1+ "','dd-mm-yyyy') and to_date('"+ data2+ "','dd-mm-yyyy')+1 )) order by application_no, quote_no";
			result = runner.multipleSelection(qry);
		} 
		catch (Exception e) 
		{
			System.out.println("getQuoteNoCommodity "+ e.toString());
			e.printStackTrace();
		} 
		return result;
	}

	public HashMap checkingvalues1234(String data1, String data2,String login_id) 
	{
		String startdate = data1;
		String enddate = data2;
		String loginid = login_id;
		String brokerIds = "";
		String returnval[][] = new String[0][0];
		String values[][] = new String[0][0];
		String values123[][] = new String[0][0];
		String qry = "";
		qry = "select login_id,username from login_master where  oa_code=(select oa_code from login_master where login_id='"+ loginid + "')";
		try {
			values = runner.multipleSelection(qry);
		} catch (Exception e) {
			System.out.println("Exception in getiing report dats by using date"	+ e.toString());
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
			qry = "select b.login_id,count(b.POLICY_NO),sum(b.premium+nvl(b.EXCESS_PREMIUM,0)),sum(nvl(b.commission,'0')) from personal_info a,position_master b where a.CUSTOMER_ID=b.CUSTOMER_ID and   b.LOGIN_ID in ('"+ brokerIds	+ "') and b.inception_date between to_date('"+ startdate+ "','dd-mm-yyyy') and to_date('"+ enddate+ "','dd-mm-yyyy')+1 and  b.status='P' and b.DEBIT_NOTE_NO is not null group by b.login_id";
			returnval = runner.multipleSelection(qry);
			if (returnval.length > 0)
			{
				brokerData.put("Broker" + (p + 1), returnval);
				lids.put("IDS" + (p + 1), values[i][0].trim());
				lids.put("IDS" + (p + 1), values[i][0].trim());
				p++;
			}
		} catch (Exception e) {
			System.out.println("Exception in getiing report dateeeeeees by using date"+ e.toString());
		} 
		brokerData.put("values", values);
		brokerData.put("lids", lids);
		return brokerData;
	}

	public HashMap Newcheckingvalues123(String data1, String data2) 
	{
		String startdate = data1;
		String enddate = data2;
		String brokerIds = "";
		String returnval[][] = new String[0][0];
		String values[][] = new String[0][0];
		String qry = "";
		qry = "select login_id,agency_code,oa_code from login_master where usertype='Broker' and oa_code is not null and agency_code is not null";
		try 
		{
			values = runner.multipleSelection(qry);
			brokerLoginIds = values;
		}
		catch (Exception e) 
		{
			System.out.println("Exception in getiing report dats by using date"	+ e.toString());
			e.printStackTrace();
		}
		setBrokerLoginIds(values);
		java.util.HashMap brokerData = new java.util.HashMap();
		try 
		{
			for (int i = 0; i < values.length; i++)
			{
				qry = "select a.login_id,count(b.POLICY_NO),sum(b.premium+nvl(b.EXCESS_PREMIUM,0)),sum(nvl(b.commission,'0')) from personal_info a,position_master b,marine_result c,login_user_details d where a.CUSTOMER_ID=b.CUSTOMER_ID and b.login_id=d.login_id and b.login_id=a.login_id  and b.application_no=c.application_no  and b.LOGIN_ID='"+ values[i][0]	+ "' and b.inception_date between to_date('"+ startdate+ "','dd-mm-yyyy') and to_date('"+ enddate+ "','dd-mm-yyyy')+1 and  b.status='P' group by b.login_id";
				returnval = runner.multipleSelection(qry);
				brokerData.put("Broker" + values[i][0], returnval);
			}
		} 
		catch (Exception e) 
		{
			System.out.println("Exception in getiing report dats by using date"+ e.toString());
			e.printStackTrace();
		} 
		brokerData.put("values", values);
		return brokerData;
	}
//not used
	public String[][] Reportcheckingvalues(String data1, String data2,String broktype) 
	{
		String values[][] = new String[0][0];
		String values123[][] = new String[0][0];
		String qry1 = "select application_id,login_id,agency_code,oa_Code,first_name from personal_info where agency_code='"+ broktype + "' and (login_id!='NONE' or application_id='4')";
		try 
		{
			values = runner.multipleSelection(qry1);
		}
		catch (Exception e) 
		{
			System.out.println("Exception in getiing report dats by using date"+ e.toString());
			e.printStackTrace();
		}
		String startdate = data1;
		String enddate = data2;
		String returnval[][] = new String[0][0];
		String qry = "";
		if (!values[0][0].equals("4")) {
			qry = "select a.policy_no,to_char(a.inception_date,'yyyy-MM-dd'), b.mode_of_transport,h.transport_description,b.cover_id,d.cover_name, b.sale_term_id, e.sale_term_name,b.extra_cover_id, f.extra_cover_name,  b.total_sum_insured, a.premium ,nvl(a.commission,'0'), a.remarks, a.login_id,b.total_war_charges,a.quote_no,a.debit_note_no,nvl(g.first_name,g.company_name),b.transit_from,b.final_destination,b.total_sum_insured,b.exchange_rate,a.login_id,nvl(mp.bank_name,' '),nvl(mp.carrier_name,' '),a.excess_premium,b.CURRENCY_NAME,a.product_id,b.TOTAL_SALE_TERM_CHARGES,b.transit_from_city_id,b.transit_from_country_id,b.final_destination_city_id,b.final_Destination_country_id   from position_master a, marine_data b, cover_master d, sale_term_master e, extra_cover_master f, personal_info g, mode_of_transport h,marine_policy_details mp where b.cover_id=d.cover_id and b.sale_term_id=e.sale_term_id and f.extra_cover_id=b.extra_cover_id and a.application_no=b.application_no and  g.customer_id=a.customer_id   and b.mode_of_transport=h.mode_transport_id and (a.inception_date between to_date('"+ startdate	+ "','dd-mm-yyyy') and to_date('"+ enddate+ "','dd-mm-yyyy')+1 ) and g.application_id='1' and a.login_id in ('"+ values[0][1]+ "') and  mp.quote_no=a.quote_no and a.status='P' and a.DEBIT_NOTE_NO is not null order by a.inception_date desc";
		} else {
			String qry12 = "select AC_EXECUTIVE_ID,AC_EXECUTIVE_NAME,AGENCY_CODE from LOGIN_EXECUTIVE_DETAILS where agency_code='"+ broktype + "'";
			try {
				values123 = runner.multipleSelection(qry12);
			} catch (Exception e) {
				System.out.println("Exception in getiing report dats by using date"	+ e.toString());
			}
			qry = "select a.policy_no,to_char(a.inception_date,'yyyy-MM-dd'), b.mode_of_transport,h.transport_description,b.cover_id,d.cover_name, b.sale_term_id, e.sale_term_name,b.extra_cover_id, f.extra_cover_name,  b.total_sum_insured, a.premium ,nvl(a.commission,'0'), a.remarks, a.login_id,b.total_war_charges,a.quote_no,a.debit_note_no,nvl(g.first_name,g.company_name),b.transit_from,b.final_destination,b.total_sum_insured,b.exchange_rate,a.login_id,nvl(mp.bank_name,' '),nvl(mp.carrier_name,' '),a.excess_premium,b.CURRENCY_NAME,a.product_id,b.TOTAL_SALE_TERM_CHARGES,b.transit_from_city_id,b.transit_from_country_id,b.final_destination_city_id,b.final_Destination_country_id   from position_master a, marine_data b, cover_master d, sale_term_master e, extra_cover_master f, personal_info g, mode_of_transport h,marine_policy_details mp where b.cover_id=d.cover_id and b.sale_term_id=e.sale_term_id and f.extra_cover_id=b.extra_cover_id and a.application_no=b.application_no and  g.customer_id=a.customer_id   and b.mode_of_transport=h.mode_transport_id and (a.inception_date between to_date('"+ startdate	+ "','dd-mm-yyyy') and to_date('"+ enddate+ "','dd-mm-yyyy')+1 )    and g.application_id='1' and (b.AC_EXECUTIVE_ID='"+ values123[0][0]	+ "' or a.login_id in ('"+ values[0][1]+ "')) and  mp.quote_no=a.quote_no and a.status='P' and a.DEBIT_NOTE_NO is not null  order by a.inception_date desc";
		}
		try
		{
			returnval = runner.multipleSelection(qry);
		}
		catch (Exception e) 
		{
			System.out.println("Exception in getiing report dats by using date"	+ e.toString());
			e.printStackTrace();
		}
		return returnval;
	}

	public HashMap checkingvalues123(String data1, String data2, String pid) 
	{
		String addeddata = "";
		if (pid.equals("all")) {
			addeddata = "";
		} else {
			addeddata = "and  a.product_id='" + pid + "'";
		}
		String startdate = data1;
		String enddate = data2;
		String brokerIds = "";
		String returnval[][] = new String[0][0];
		String values[][] = new String[0][0];
		String qry = "";
		qry = "select a.login_id,a.agency_code,a.oa_code,b.company_name from login_master a,broker_company_master b  where a.usertype='Broker' and a.login_id!='NON' and a.agency_code=b.agency_code and login_id!='NONE' and a.oa_code is not null and a.agency_code is not null order by lower(b.company_name)";
		try 
		{
			values = runner.multipleSelection(qry);
			brokerLoginIds = values;
		}
		catch (Exception e) 
		{
			System.out.println("Exception in getiing report dats by using date"	+ e.toString());
			e.printStackTrace();
		}
		setBrokerLoginIds(values);
		java.util.HashMap brokerData = new java.util.HashMap();
		java.util.HashMap lids = new java.util.HashMap();
		try 
		{
			int p = 0;
			for (int i = 0; i < values.length; i++) 
			{
				qry = "select count(a.POLICY_NO),sum(a.premium+nvl(a.Excess_Premium,0)),sum(nvl(a.commission,'0')) from position_master a, marine_data b, cover_master d, sale_term_master e, extra_cover_master f, personal_info g, mode_of_transport h,marine_policy_details mp where b.cover_id=d.cover_id and b.sale_term_id=e.sale_term_id and f.extra_cover_id=b.extra_cover_id and a.application_no=b.application_no and g.customer_id=a.customer_id and mp.quote_no=a.quote_no  and  b.mode_of_transport=h.mode_transport_id and a.DEBIT_NOTE_NO!='0' and  a.login_id in(select login_id from login_master where oa_Code=(select oa_code from login_master where login_id='"+ values[i][0]+ "'))   and  a.inception_date between to_date('"+ startdate+ "','dd-mm-yyyy') and to_date('"+ enddate+ "','dd-mm-yyyy')+1"	+ addeddata	+ " and a.status='P'";
				returnval = runner.multipleSelection(qry);
				if (!returnval[0][0].equals("0")) 
				{
					System.out.println("sdfsdf>>>>"+ returnval[0][0]);
					brokerData.put("Broker" + (p + 1), returnval);
					lids.put("IDS" + (p + 1), values[i][0]);
					p++;
				}
			}
		} 
		catch (Exception e) 
		{
			System.out.println("Exception in getiing report dats by using date"	+ e.toString());
			e.printStackTrace();
		} 
		brokerData.put("values", values);
		brokerData.put("lids", lids);
		return brokerData;
	}

	public String[][] checkingvalues(String data1, String data2,String loginId, String pid) 
	{
		String addeddata = "";
		if (pid.equals("all")) {
			addeddata = "";
		} else {
			addeddata = "and  a.product_id='" + pid + "'";
		}
		String startdate = data1;
		String enddate = data2;
		String returnval[][] = new String[0][0];
		String qry = "select a.policy_no,to_char(a.inception_date,'yyyy-MM-dd'), b.mode_of_transport,h.transport_description,b.cover_id,d.cover_name, b.sale_term_id, e.sale_term_name,b.extra_cover_id, f.extra_cover_name,  b.total_sum_insured, a.premium ,nvl(a.commission,'0'), a.remarks, a.login_id,b.total_war_charges,a.quote_no,a.debit_note_no,nvl(g.first_name,g.company_name),b.transit_from,b.final_destination,b.total_sum_insured,b.exchange_rate,a.login_id,nvl(mp.bank_name,' '),nvl(mp.carrier_name,' '),a.excess_premium,b.CURRENCY_NAME,a.product_id,b.TOTAL_SALE_TERM_CHARGES,b.transit_from_city_id,b.transit_from_country_id,b.final_destination_city_id,b.final_Destination_country_id   from position_master a, marine_data b, cover_master d, sale_term_master e, extra_cover_master f, personal_info g, mode_of_transport h,marine_policy_details mp where b.cover_id=d.cover_id and b.sale_term_id=e.sale_term_id and f.extra_cover_id=b.extra_cover_id and a.application_no=b.application_no and a.DEBIT_NOTE_NO!='0' and  g.customer_id=a.customer_id   and b.mode_of_transport=h.mode_transport_id and (a.inception_date between to_date('"	+ startdate	+ "','dd-mm-yyyy') and to_date('"+ enddate+ "','dd-mm-yyyy')+1 )  and g.application_id='1' and a.login_id in (select login_id from login_master where oa_Code=(select oa_code from login_master where login_id='"	+ loginId+ "')) and  mp.quote_no=a.quote_no  "+ addeddata+ " and a.status='P' order by a.inception_date desc";
		try
		{
			returnval = runner.multipleSelection(qry);
		}
		catch (Exception e) 
		{
			System.out.println("Exception in getiing report dats by using date"	+ e.toString());
			e.printStackTrace();
		}
		
		return returnval;
	}

	public String[][] checkingvalues(String data1, String data2,String loginId, String pid, String businessType, String carrier) 
	{
		String addeddata = "";
		String businessdata = "";
		if (pid.equals("all")) {
			addeddata = "";
		} else {
			addeddata = "and  a.product_id='" + pid + "'";
		}
		try {
			if ((carrier == null) || ("null".equalsIgnoreCase(carrier))	|| " ".equalsIgnoreCase(carrier)|| "".equalsIgnoreCase(carrier)) {
				carrier = "A";
			}
			if ((businessType == null)|| ("null".equalsIgnoreCase(businessType))|| " ".equalsIgnoreCase(businessType)|| "".equalsIgnoreCase(businessType)) {
				businessType = "B";
			}
			if (!"all".equalsIgnoreCase(businessType.trim())) {
				if (!"B".equalsIgnoreCase(businessType.trim())) {
					businessdata = "and a.business_type = '" + businessType	+ "' ";
				}
			}
			if (!"all".equalsIgnoreCase(carrier.trim())) {
				if (!"A".equalsIgnoreCase(carrier.trim())) {
				businessdata = "and h.transport_description  = '" + carrier	+ "' ";
				}
			}
		} catch (Exception e) {
		}
		String startdate = data1;
		String enddate = data2;
		String returnval[][] = new String[0][0];
		String qry = "select a.policy_no,to_char(a.inception_date,'yyyy-MM-dd'), b.mode_of_transport,h.transport_description,b.cover_id,d.cover_name, b.sale_term_id, e.sale_term_name,b.extra_cover_id, f.extra_cover_name,  round(b.total_sum_insured), a.premium ,nvl(a.commission,'0'), a.remarks, a.login_id,b.total_war_charges,a.quote_no,a.debit_note_no,nvl(g.first_name,g.company_name),b.transit_from,b.final_destination,round(b.total_sum_insured),b.exchange_rate,a.login_id,nvl(mp.bank_name,' '),nvl(mp.carrier_name,' '),a.excess_premium,b.CURRENCY_NAME,a.product_id,b.TOTAL_SALE_TERM_CHARGES,b.transit_from_city_id,b.transit_from_country_id,b.final_destination_city_id,b.final_Destination_country_id,a.business_type	from position_master a, marine_data b, cover_master d, sale_term_master e, extra_cover_master f, personal_info g, mode_of_transport h,marine_policy_details mp where b.cover_id=d.cover_id and b.sale_term_id=e.sale_term_id and f.extra_cover_id=b.extra_cover_id and a.application_no=b.application_no and a.DEBIT_NOTE_NO!='0' and  g.customer_id=a.customer_id   and b.mode_of_transport=h.mode_transport_id and (a.inception_date between to_date('"+ startdate+ "','dd-mm-yyyy') and to_date('"+ enddate+ "','dd-mm-yyyy')+1 )    and g.application_id='1' and a.login_id in (select login_id from login_master where oa_Code=(select oa_code from login_master where login_id='"+ loginId+ "')) and  mp.quote_no=a.quote_no  "+ addeddata+ " and a.status='P' "+ businessdata+ "  order by a.inception_date desc";
		try {
			returnval = runner.multipleSelection(qry);
		} catch (Exception e) {
			System.out.println("Exception in getiing report dats by using date"	+ e.toString());
		}
		return returnval;
	}

	public HashMap checkingvalues(String data1, String data2, String pid,	String businessType, String carrier)
	{
		String addeddata = "";
		String businessdata = "";
		String qry = "";
		if (pid.equals("all")) {
			addeddata = "";
		} else {
			addeddata = "and a.product_id='" + pid + "'";
		}
		System.out.println("Business Type" + businessType);
		try {
			if ("0".equalsIgnoreCase(businessType)) {
				businessType = "and a.business_type = 0 ";
			} 
			else if ("1".equalsIgnoreCase(businessType))
			{
				//businessType = "and a.business_type is null";
				businessType = "and a.business_type='1' ";
			} 
			else 
			{
			}
		} catch (Exception ee) {
			System.out.println("Errooo---" + ee);
		}
		try {
			if ("all".equalsIgnoreCase(carrier.trim())|| "null".equalsIgnoreCase(carrier.trim())|| "".equalsIgnoreCase(carrier.trim())|| carrier.trim() == null) {
			} else {
				businessdata = "and h.transport_description  = '" + carrier	+ "' ";
			}
		}
		catch (Exception e) 
		{
			System.out.println("Eror---" + e);
			e.printStackTrace();
		}
		String values[][] = new String[0][0];
		String startdate = data1;
		String enddate = data2;
		String returnval[][] = new String[0][0];
		qry = "select a.login_id,a.agency_code,a.oa_code,b.company_name from login_master a,broker_company_master b  where a.usertype='Broker' and a.login_id!='NON' and a.agency_code=b.agency_code and login_id!='NONE' and a.oa_code is not null and a.agency_code is not null order by lower(b.company_name)";
		try 
		{
			values = runner.multipleSelection(qry);
			brokerLoginIds = values;
		}
		catch (Exception e) 
		{
			System.out.println("Exception in getiing report dats by using date"	+ e.toString());
			e.printStackTrace();
		}
		setBrokerLoginIds(values);
		java.util.HashMap brokerData = new java.util.HashMap();
		java.util.HashMap lids = new java.util.HashMap();
		try
		{
			int p = 0;
			for (int i = 0; i < values.length; i++) 
			{
				qry = "select count(a.POLICY_NO),sum(a.premium+nvl(a.Excess_Premium,0)),sum(nvl(a.commission,'0')) from position_master a, marine_data b, cover_master d, sale_term_master e, extra_cover_master f, personal_info g, mode_of_transport h,marine_policy_details mp where b.cover_id=d.cover_id and b.sale_term_id=e.sale_term_id and f.extra_cover_id=b.extra_cover_id and a.application_no=b.application_no and g.customer_id=a.customer_id and mp.quote_no=a.quote_no  and  b.mode_of_transport=h.mode_transport_id and a.DEBIT_NOTE_NO!='0' and  a.login_id in(select login_id from login_master where oa_Code=(select oa_code from login_master where login_id='"+ values[i][0]+ "'))   and  a.inception_date between to_date('"	+ startdate+ "','dd-mm-yyyy') and to_date('"+ enddate+ "','dd-mm-yyyy')+1"+ addeddata+ " "+ businessType + " and a.status='P'";
				returnval = runner.multipleSelection(qry);
				if (!returnval[0][0].equals("0")) 
				{
					System.out.println("sdfsdf>>"+ returnval[0][0]);
					brokerData.put("Broker" + (p + 1), returnval);
					lids.put("IDS" + (p + 1), values[i][0]);
					p++;
				}
			}
		}
		catch (Exception e)
		{
			System.out.println("Exception in getiing report dats by using date"	+ e.toString());
			e.printStackTrace();
		} 
		brokerData.put("values", values);
		brokerData.put("lids", lids);
		return brokerData;
	}

	public String[][] checkingvaluesCommodity(String data1, String data2,String pid, String[][] quoteNo) 
	{
		String allNumber = "";
		String businessdata = "";
		String addeddata = "";
		if (pid.equals("all")) {
			addeddata = "";
		} else {
			addeddata = "and  a.product_id='" + pid + "'";
		}
		String startdate = data1;
		String enddate = data2;
		String returnval[][] = new String[0][0];
		if (quoteNo.length > 0) {
			for (int i = 0; i < quoteNo.length; i++) {
				allNumber = allNumber + quoteNo[i][0] + ",";
			}
		}
		allNumber = allNumber.substring(0, allNumber.length() - 1);
		String qry = "select a.policy_no,to_char(a.inception_date,'yyyy-MM-dd'), b.mode_of_transport,h.transport_description,b.cover_id,d.cover_name, b.sale_term_id, e.sale_term_name,b.extra_cover_id, f.extra_cover_name,  round(b.total_sum_insured), a.premium ,nvl(a.commission,'0'), a.remarks, a.login_id,b.total_war_charges,a.quote_no,a.debit_note_no,nvl(g.first_name,g.company_name),b.transit_from,b.final_destination,round(b.total_sum_insured),b.exchange_rate,a.login_id,nvl(mp.bank_name,' '),nvl(mp.carrier_name,' '),a.excess_premium,b.CURRENCY_NAME,a.product_id,b.TOTAL_SALE_TERM_CHARGES,b.transit_from_city_id,b.transit_from_country_id,b.final_destination_city_id,b.final_Destination_country_id,a.business_type	from position_master a, marine_data b, cover_master d, sale_term_master e, extra_cover_master f, personal_info g, mode_of_transport h,marine_policy_details mp where b.cover_id=d.cover_id and b.sale_term_id=e.sale_term_id and f.extra_cover_id=b.extra_cover_id and a.application_no=b.application_no and a.DEBIT_NOTE_NO!='0' and  g.customer_id=a.customer_id   and b.mode_of_transport=h.mode_transport_id and (a.inception_date between to_date('"+ startdate+ "','dd-mm-yyyy') and to_date('"+ enddate+ "','dd-mm-yyyy')+1 )    and g.application_id='1' and  mp.quote_no=a.quote_no  and a.quote_no in ("+ allNumber	+ ")  and a.status='P' order by a.inception_date desc";
		try {
			returnval = runner.multipleSelection(qry);
		} catch (Exception e) {
		}
		return returnval;
	}

	public String fromCityAdmin(String frmcty, String frmcntry, String other) 
	{
		String fcity = "";
		String fcntry = "";
		String args[] = new String[0];
		try 
		{
			if (other.trim().equalsIgnoreCase("noother")) 
			{
				args = new String[1];
				args[0] = frmcty;
				String qry = "select initcap(city_name) from city_master where  city_id=?";
				fcity = runner.singleSelection(qry,args);
			}
			else 
			{
				fcity = other;
			}
			if (frmcntry.length() > 0) 
			{
				try 
				{
					args = new String[2];
					args[0] = frmcntry;
					args[1] = frmcntry;
					String qry = "select initcap(country_name) from country_master where  country_id=? and  amend_id=(select max(amend_id) from country_master where country_id=?)";
					fcntry = runner.singleSelection(qry,args);
				}
				catch (Exception e) 
				{
					System.out.println("Exception in  by city and country id in reports"+ e.toString());
					e.printStackTrace();
				}
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
		String tr = "";
		if (fcity.trim().length() > 0) {
			tr = fcntry + "(" + fcity + ")";
		} else {
			tr = fcntry;
		}
		System.out.println("sdfsdfsdfsdfsdfsdfsd" + tr);
		return tr;
	}

	public String fromCity(String frmcty, String frmcntry, String other) 
	{
		String fcity = "";
		String fcntry = "";
		String args[] = new String[0];
		try 
		{
			if (other.trim().equalsIgnoreCase("noother")) 
			{
				args = new String[1];
				args[0] = frmcty;
				String qry = "select initcap(city_name) from city_master where  city_id=?";
				fcity = runner.singleSelection(qry,args);
			}
			else 
			{
				fcity = other;
			}
			if (frmcntry.length() > 0) 
			{
				try 
				{
					args = new String[2];
					args[0] = frmcntry;
					args[1] = frmcntry;
					String qry = "select initcap(country_name) from country_master where  country_id=? and  amend_id=(select max(amend_id) from country_master where country_id=?)";
					fcntry = runner.singleSelection(qry,args);
				}
				catch (Exception e) 
				{
					System.out.println("Exception in  by city and country id in reports"+ e.toString());
					e.printStackTrace();
				}
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
		String tr = "";
		if (fcity.trim().length() > 0) 
		{
			tr = fcntry + "(" + fcity + ")";
		} 
		else 
		{
			tr = fcntry;
		}
		return tr;
	}

	public String[][] policyvalues123(String data3) 
	{
		String policyno = data3;
		String policyval[][] = new String[0][0];
		String qry = ""; 
		String args[] = new String[1];
		try 
		{
			args[0] = policyno + "%";
			qry = "select a.policy_no,to_char(a.inception_date,'dd-MM-yyyy'), b.mode_of_transport,h.transport_description,b.cover_id, d.cover_name, b.sale_term_id, e.sale_term_name,b.extra_cover_id, f.extra_cover_name,  b.total_sum_insured,m.AMOUNT_PAYABLE , c.commission, a.remarks, g.login_id  from position_master a, marine_data b, cover_master d, sale_term_master e, extra_cover_master f, personal_info g, mode_of_transport h,login_user_details c,marine_result m where b.cover_id=d.cover_id and b.sale_term_id=e.sale_term_id and b.extra_cover_id=f.extra_cover_id and a.application_no=b.application_no and  m.application_no=b.application_no and g.customer_id=a.customer_id and a.policy_no like ? and b.mode_of_transport=h.mode_transport_id  and c.login_id=g.login_id and g.application_id='1' and a.policy_no is not null";

			policyval = runner.multipleSelection(qry,args);
		} 
		catch (Exception e) 
		{
			System.out.println("Exception in  by policy in reports"	+ e.toString());
			e.printStackTrace();
		}
		return policyval;
	}

	public String[][] policyvalues(String data3) 
	{
		String policyno = data3;
		String policyval[][] = new String[0][0];
		String qry = ""; 
		String args[] = new String[1];
		try 
		{
			args[0] = policyno+"%";
			qry = "select a.policy_no,to_char(a.inception_date,'dd-MM-yyyy'), b.mode_of_transport,h.transport_description,b.cover_id, d.cover_name, b.sale_term_id, e.sale_term_name,b.extra_cover_id, f.extra_cover_name,  b.total_sum_insured,m.AMOUNT_PAYABLE , c.commission, a.remarks, g.login_id  from position_master a, marine_data b, cover_master d, sale_term_master e, extra_cover_master f, personal_info g, mode_of_transport h,login_user_details c,marine_result m where b.cover_id=d.cover_id and b.sale_term_id=e.sale_term_id and b.extra_cover_id=f.extra_cover_id and a.application_no=b.application_no and  m.application_no=b.application_no and g.customer_id=a.customer_id and a.policy_no like ? and b.mode_of_transport=h.mode_transport_id  and c.login_id=g.login_id and g.application_id='1' and a.policy_no is not null";
			policyval = runner.multipleSelection(qry,args);
		} catch (Exception e) {
			System.out.println("Exception in  by policy in reports"	+ e.toString());
		}
		return policyval;
	}

	public String[][] customervalues(String data3) 
	{
		String customername = data3;
		String customerval[][] = new String[0][0];
		String qry = ""; 
		String args[] = new String[1];
		try 
		{
			args[0] = customername+"%";
			qry = "select a.policy_no,to_char(a.inception_date,'dd-MM-yyyy'), b.mode_of_transport,h.transport_description,b.cover_id, d.cover_name, b.sale_term_id, e.sale_term_name,b.extra_cover_id, f.extra_cover_name,  b.total_sum_insured, m.AMOUNT_PAYABLE , c.commission, a.remarks, g.login_id  from position_master a, marine_data b, cover_master d, sale_term_master e, extra_cover_master f, personal_info g, mode_of_transport h,login_user_details c,marine_result m where b.cover_id=d.cover_id and b.sale_term_id=e.sale_term_id and b.extra_cover_id=f.extra_cover_id and a.application_no=b.application_no and m.application_no=b.application_no and g.customer_id=a.customer_id and g.first_name like ? and b.mode_of_transport=h.mode_transport_id  and c.login_id=g.login_id and g.application_id='1' and a.policy_no is not null";

			customerval = runner.multipleSelection(qry,args);
		}
		catch (Exception e) 
		{
			System.out.println("Exception in by custemoer reports"+ e.toString());
			e.printStackTrace();
		}
		return customerval;
	}

	public String validateTrashFields() 
	{
		com.maan.services.util.dataCollection data = new com.maan.services.util.dataCollection();
		String error = "";
		String values = null;
		try {
			if ("p".equals(rep) || "c".equals(rep)) {
				values = data.checkDate(dobDay + "/" + dobMonth + "/" + dobYear);
				if ("Invalid".equalsIgnoreCase(values))
					error = error + "<br>*" + runner.getErrormsg("62");
				values = data.checkDate(dobDay1 + "/" + dobMonth1 + "/"	+ dobYear1);
				if ("Invalid".equalsIgnoreCase(values))
					error = error + "<br>*" + runner.getErrormsg("63");
				else if (Integer.parseInt(dobYear1) < Integer.parseInt(dobYear)) {
					error = error + "<br>*" + runner.getErrormsg("72");
				} else if (Integer.parseInt(dobYear1) == Integer.parseInt(dobYear)) {
					if (Integer.parseInt(dobMonth1) < Integer.parseInt(dobMonth)) {
						error = error + "<br>*" + runner.getErrormsg("72");
					} else if (Integer.parseInt(dobMonth1) == Integer.parseInt(dobMonth)) {
						if (Integer.parseInt(dobDay1) < Integer.parseInt(dobDay)) {
							error = error + "<br>*"	+ runner.getErrormsg("72");
						}

					}
				}
				SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH);
		    	sf.setTimeZone(new java.util.SimpleTimeZone(14400000,"GMT")); 	
		    	String s = sf.format(new java.util.Date());
		        String enteredDate1 = dobDay1 + "/" + dobMonth1 + "/"+ dobYear1;
				String enteredDate = dobDay + "/" + dobMonth + "/" + dobYear;
				long diff1 = 0;
				long diff = 0;
				try {
					DateFunction dbr = new DateFunction();
					diff1 = dbr.getDayDifference(dbr.getCalendar(s), dbr.getCalendar(enteredDate1));
					diff = dbr.getDayDifference(dbr.getCalendar(s), dbr.getCalendar(enteredDate));
				} catch (Exception e) {
				}
				if (diff > 0) {
					error = error + "<br>*" + runner.getErrormsg("70");
				}
				if (diff1 > 0) {
				}
			}
		} catch (Exception e) {
			System.out.println("Exception in " + e.toString());
		}
		
		return error;
	}

	public String validateFields() 
	{
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
				values = data.checkDate(dobDay1 + "/" + dobMonth1 + "/"	+ dobYear1);
				if ("Invalid".equalsIgnoreCase(values))
					error = error + "<br>*" + runner.getErrormsg("63");
				else if (Integer.parseInt(dobYear1) < Integer.parseInt(dobYear)) {
					error = error + "<br>*" + runner.getErrormsg("72");
				} else if (Integer.parseInt(dobYear1) == Integer.parseInt(dobYear)) {
					if (Integer.parseInt(dobMonth1) < Integer.parseInt(dobMonth)) {
						error = error + "<br>*" + runner.getErrormsg("72");
					} else if (Integer.parseInt(dobMonth1) == Integer.parseInt(dobMonth)) {
						if (Integer.parseInt(dobDay1) < Integer.parseInt(dobDay)) {
							error = error + "<br>*"+ runner.getErrormsg("72");
						}
					}
				}
				SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH);
		    	sf.setTimeZone(new java.util.SimpleTimeZone(14400000,"GMT")); 	
		    	String s = sf.format(new java.util.Date());
		        String enteredDate1 = dobDay1 + "/" + dobMonth1 + "/"+ dobYear1;
				String enteredDate = dobDay + "/" + dobMonth + "/" + dobYear;
				long diff1 = 0;
				long diff = 0;
				try {
					DateFunction dbr = new DateFunction();
					diff1 = dbr.getDayDifference(dbr.getCalendar(s), dbr.getCalendar(enteredDate1));
					diff = dbr.getDayDifference(dbr.getCalendar(s), dbr.getCalendar(enteredDate));
				} catch (Exception e) {
				}
				if (diff > 0) {
					error = error + "<br>*" + runner.getErrormsg("70");
				}
				if (diff1 > 0) {
				}
		}
		} catch (Exception e) {
			System.out.println("Exception in " + e.toString());
		} 
		return error;
	}

	public String[][] getBrokerksByAdmin() 
	{
		String[][] brokers = new String[0][0];
		String sql = ""; 
		try 
		{
			sql =  "select lm.login_id,pi.first_name from login_master lm,personal_info pi where pi.login_id=lm.login_id and lm.usertype='Broker' and pi.application_id='2' order by pi.first_name";
			brokers = runner.multipleSelection(sql);
		} 
		catch (Exception e) 
		{
			System.out.println("Exception in by custemoer reports"+ e.toString());
			e.printStackTrace();
		}
		return brokers;
	}

	public HashMap TrashToAdmin(String data1, String data2, String status) 
	{
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
			System.out.println("Exception in getiing report dats by using date"+ e.toString());
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
					qry = "select a.login_id,count(b.POLICY_NO),sum(b.premium),sum(nvl(b.commission,'0')) from personal_info a,position_master b,marine_result c,login_user_details d where a.CUSTOMER_ID=b.CUSTOMER_ID and b.login_id=d.login_id and b.login_id=a.login_id  and b.application_no=c.application_no  and a.LOGIN_ID='"+ values[i][0]	+ "' and b.entry_date between to_date('"+ startdate	+ "','dd-mm-yyyy') and to_date('"+ enddate+ "','dd-mm-yyyy')+1 and  lower(b.status)='"+ status.toLowerCase() + "' group by a.login_id";
				} else {
					qry = "select a.login_id,count(b.quote_No),sum(md.premium),sum(nvl(b.commission,'0')) from personal_info a,position_master b,marine_result c,login_user_details d,marine_data md where a.CUSTOMER_ID=b.CUSTOMER_ID and b.login_id=d.login_id and b.login_id=a.login_id  and b.application_no=c.application_no and b.application_no=md.application_no  and a.LOGIN_ID='"	+ values[i][0]+ "' and b.entry_date between to_date('"+ startdate+ "','dd-mm-yyyy') and to_date('"+ enddate	+ "','dd-mm-yyyy')+1 and  lower(b.status) not in("+ status + ") group by a.login_id";
				}
				returnval = runner.multipleSelection(qry);
				brokerData.put("Broker" + values[i][0], returnval);
			}
		} catch (Exception e) {
			System.out.println("Exception in getiing report dats by using date"
					+ e.toString());
		} 
		brokerData.put("values", values);
		return brokerData;
	}

	public String[][] getLogIds(String loginIds)
	{
		String[][] valuess = new String[0][0];
		String sql = ""; 
		String args[] = new String[3];
		try 
		{
			args[0] = loginIds;
			args[1] = loginIds;
			args[3] = loginIds;
			sql = "select login_id from login_master where (oa_code=(select agency_code from login_master where login_id=?) or created_by=(select agency_code from login_master where login_id=?) or login_id=?) and login_id not in('NONE','NON')";

			valuess = runner.multipleSelection(sql,args);
		} 
		catch (Exception e) 
		{
			System.out.println("Exception in>>>>>>" + e.toString());
			e.printStackTrace();
		} 
		return valuess;
	}

	public String getUsername(String loginId) 
	{
		String pname = "";
		String sql = ""; 
		String args[] = new String[1];
		try 
		{
			args[0] = loginId;
			sql = "select first_name from personal_info where customer_id = (select distinct customer_id from login_user_details where login_id = ?)";
			pname = runner.singleSelection(sql,args);
		}
		catch (Exception e) 
		{
			System.out.println("Exception in getting pname" + e.toString());
			e.printStackTrace();
		}
		return pname;
	}

	public HashMap getUserNameBrokerCompanyName(String loginId) 
	{
		String pname = "";
		String res[][] = new String[0][0];
		String res1[][] = new String[0][0];
		HashMap result = new HashMap();
		String sql = "";
		String sql1 = "";
		sql = "select nvl(first_name||' '||last_name,''),login_id from personal_info where customer_id in (select distinct customer_id from login_user_details where login_id in("+ loginId + "))";
		sql1 = "select nvl(bro.company_name,''),log.login_id from broker_company_master bro,login_master log where  bro.agency_code in (select oa_code from login_master where login_id in("+ loginId+ ")) and log.oa_code=bro.agency_code and log.login_id in("+ loginId + ")";
		try 
		{
			res = runner.multipleSelection(sql);
			res1 = runner.multipleSelection(sql1);
			for (int l = 0; l < res.length; l++) 
			{
				StringTokenizer st = new StringTokenizer(loginId, ",");
				while (st.hasMoreTokens())
				{
					String login = st.nextToken().replaceAll("'", "");
					if (login.equalsIgnoreCase(res[l][1])) 
					{
						result.put("user" + res[l][1], res[l][0]);
						break;
					}
				}
			}
			for (int l = 0; l < res1.length; l++) 
			{
				StringTokenizer st = new StringTokenizer(loginId, ",");
				while (st.hasMoreTokens()) 
				{
					String login = st.nextToken().replaceAll("'", "");
					if (login.equalsIgnoreCase(res1[l][1])) 
					{
						result.put("bro" + res1[l][1], res1[l][0]);
						break;
					}
				}
			}
		} 
		catch (Exception e) 
		{
			System.out.println("Exception in getting getUser Name Broker CompanyName.."+ e.toString());
			e.printStackTrace();
		}
		return result;
	}

	public String getBrokerCompanyName123(String loginId) 
	{
		String pname = "";
		String sql = ""; 
		String args[] = new String[1];
		try 
		{
			args[0] = loginId;
			sql = "select COMPANY_NAME from broker_company_master where agency_code=(select oa_code from login_master where login_id=?)";
			pname = runner.singleSelection(sql,args);
		} 
		catch (Exception e) 
		{
			System.out.println("Exception in getting pname" + e.toString());
			e.printStackTrace();
		} 
		return pname;
	}

	public String getBrokerCompanyName(String loginId) 
	{
		String pname = "";
		String sql = ""; 
		String args[] = new String[1];
		try 
		{
			args[0] = loginId;
			sql = "select COMPANY_NAME from broker_company_master where agency_code=(select oa_code from login_master where login_id=?)";
			pname = runner.singleSelection(sql,args);
		} 
		catch (Exception e) 
		{
			System.out.println("Exception in getting pname" + e.toString());
			e.printStackTrace();
		} 
		return pname;
	}

	public String[][] getAReferal(String appNo) 
	{
		String[][] valuess = new String[0][0];
		String sql = ""; 
		String args[] = new String[1];
		try
		{
			args[0] = appNo;
			sql = "select a.ADMIN_REFERRAL_STATUS,b.remarks,a.remarks,a.REFERAL_STATUS from marine_data a,position_master b where a.application_no=b.application_no and b.application_no=?";

			valuess = runner.multipleSelection(sql,args);
		}
		catch (Exception e) 
		{
			System.out.println("Exception in>>>>>>>" + e.toString());
			e.printStackTrace();
		} 
		return valuess;
	}

	public String[][] getAllBrokers() 
	{
		String[][] getAllBrokers = null;
		String sql = "";
		try 
		{
			sql = "select b.company_name,l.login_id from broker_company_master b,login_master l where l.oa_code=b.agency_code and l.usertype='Broker'";
			getAllBrokers = runner.multipleSelection(sql);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return getAllBrokers;
	}

	public String[][] getAllBrokers1() 
	{
		String[][] getAllBrokers = null;
		String sql = "";
		try 
		{
			sql = "select b.company_name,l.login_id,l.oa_code from broker_company_master b,login_master l where l.oa_code=b.agency_code and l.usertype='Broker'";
			getAllBrokers = runner.multipleSelection(sql);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return getAllBrokers;
	}

	public String[][] getAllBrokers(String branchCode,String brokerCodes) 
	{
		String[][] getAllBrokers = new String[0][0];
		String sql = "";
		String syntax="";

		if(brokerCodes.equalsIgnoreCase("") || brokerCodes.length() ==0)
		{
			syntax = "select agency_code from broker_company_master where branch_code in("+branchCode+")";
		}
		else
		{
			brokerCodes = brokerCodes.replaceAll(",","','");
			syntax = "'"+brokerCodes+"'";
		}

		try 
		{
		 sql="select bcm.company_name,pi.login_id from BROKER_COMPANY_MASTER bcm,personal_info pi where bcm.agency_code=pi.agency_code  and bcm.status='Y' and pi.status=bcm.status and APPLICATION_ID='2' and pi.login_id in(select login_id from login_master where oa_code in("+syntax+")) order by lower(bcm.COMPANY_NAME)";
		getAllBrokers = runner.multipleSelection(sql);
		}
		catch (Exception e) 
		{
		}
		return getAllBrokers;
	}

	public static String getDate() 
	{
		Calendar c = Calendar.getInstance();
		String d = "" + c.get(Calendar.DAY_OF_MONTH) + "-"+ c.get(Calendar.MONTH) + "-" + c.get(Calendar.YEAR) + "_"+ c.get(Calendar.HOUR) + "_" + c.get(Calendar.MINUTE) + "_"	+ c.get(Calendar.SECOND);
		return d;
	}

	public String[][] getBrokerUserId() 
	{
		String[][] brokerid = new String[0][0];
		String sql = ""; 
		try 
		{
			sql = "select l.login_id,b.company_name from login_master l,BROKER_COMPANY_MASTER b where b.agency_code=l.agency_code and  l.login_id not in ('NON','NONE') and l.status='Y' and l.login_id is not null and b.agency_code is not null order by b.company_name";
			brokerid = runner.multipleSelection(sql);
		} 
		catch (Exception e) 
		{
			System.out.println("Exception in getting customer info"+ e.toString());
			e.printStackTrace();
		}
		return brokerid;
	}

	public HashMap BrokerCheckingvalues123(String data1, String data2,String loginid) 
	{
		String startdate = data1;
		String enddate = data2;
		String brokerIds = "";
		String returnval[][] = new String[0][0];
		String values[][] = new String[0][0];
		String values123[][] = new String[0][0];
		String qry = "";
		String args[] = new String[1];
		
		try 
		{
			args[0] = loginid;
			qry = "select login_id,agency_code,oa_code,usertype from login_master where  oa_Code in(select oa_code from login_master where login_id=? and login_id not in ('NONE','NON')) and login_id not in ('NONE','NON') and oa_code is not null and agency_code is not null order by login_id";

			values = runner.multipleSelection(qry,args);
			brokerLoginIds = values;
		}
		catch (Exception e) 
		{
			System.out.println("Exception in getiing report dats by using date"+ e.toString());
			e.printStackTrace();
		}
		setBrokerLoginIds(values);
		java.util.HashMap brokerData = new java.util.HashMap();
		java.util.HashMap lids = new java.util.HashMap();
		try {
			int p = 0;
			for (int i = 0; i < values.length; i++) {
				if (!values[i][3].equalsIgnoreCase("AccountEx"))
				{
					qry = "select count(a.POLICY_NO),sum(a.premium+nvl(a.Excess_Premium,0)),sum(nvl(a.commission,'0')) from position_master a, marine_data b, cover_master d, sale_term_master e, extra_cover_master f, personal_info g, mode_of_transport h,marine_policy_details mp where b.cover_id=d.cover_id and b.sale_term_id=e.sale_term_id and f.extra_cover_id=b.extra_cover_id and a.application_no=b.application_no and g.customer_id=a.customer_id and mp.quote_no=a.quote_no  and  b.mode_of_transport=h.mode_transport_id and a.DEBIT_NOTE_NO!='0' and  a.login_id in('"+ values[i][0]+ "')  and  a.inception_date between to_date('"+ startdate+ "','dd-mm-yyyy') and to_date('"+ enddate+ "','dd-mm-yyyy')+1 and  lower(a.status)='p'";
				} 
				else 
				{
					String qry12 = "select AC_EXECUTIVE_ID,AC_EXECUTIVE_NAME,AGENCY_CODE from LOGIN_EXECUTIVE_DETAILS where agency_code='"	+ values[i][1] + "'";
					try
					{
						values123 = runner.multipleSelection(qry12);
					}
					catch (Exception e) 
					{
						System.out.println("Exception in getiing report dats by using date>>"+ e.toString());
						e.printStackTrace();
					}
					qry = "select count(a.POLICY_NO),sum(a.premium+nvl(a.Excess_Premium,0)),sum(nvl(a.commission,'0')) from position_master a, marine_data b, cover_master d, sale_term_master e, extra_cover_master f, personal_info g, mode_of_transport h,marine_policy_details mp where b.cover_id=d.cover_id and b.sale_term_id=e.sale_term_id and f.extra_cover_id=b.extra_cover_id and a.application_no=b.application_no and g.customer_id=a.customer_id and mp.quote_no=a.quote_no  and  b.mode_of_transport=h.mode_transport_id and a.DEBIT_NOTE_NO!='0' and  (a.login_id in ('"+ values[i][0]+ "') or b.AC_EXECUTIVE_ID in ('"+ values123[0][0]+ "'))  and  a.inception_date between to_date('"+ startdate+ "','dd-mm-yyyy') and to_date('"+ enddate+ "','dd-mm-yyyy')+1 and  lower(a.status)='p'";
				}
				returnval = runner.multipleSelection(qry);
				if (!returnval[0][0].equals("0"))
				{
					brokerData.put("Broker" + (p + 1), returnval);
					lids.put("IDS" + (p + 1), values[i][0]);
					p++;
				}
			}
		} 
		catch (Exception e) 
		{
			System.out.println("Exception in getiing report dats by using date"	+ e.toString());
			e.printStackTrace();
		} 
		brokerData.put("values", values);
		brokerData.put("lids", lids);
		return brokerData;
	}

	public String getNameByAgencyCode(String agcode) 
	{
		String args[] = new String[1];
		String pname = "";
		int check = 0;
		String sql1 = ""; 
		String res = "";
		try 
		{
			args[0] = agcode;
			sql1 = "select count(*) from broker_company_master where agency_code=?";
			res = runner.singleSelection(sql1,args);
			if(res.length() > 0 && !res.equalsIgnoreCase("null")) 
			{
				check = Integer.parseInt(res);
			}
			else 
			{
				check = 0;
			}
		} 
		catch (Exception e) 
		{
			System.out.println("Exception in getting pname" + e.toString());
			e.printStackTrace();
		}
		String sql = "";
		args[0] = agcode;
		if (check > 0) 
		{
			sql = "select COMPANY_NAME from broker_company_master where agency_code=?";
		}
		else 
		{
			sql = "select first_name from personal_info where agency_code=?";
		}
		try 
		{
			res = runner.singleSelection(sql,args);
			if(res.length() > 0 && !res.equalsIgnoreCase("null")) 
			{
				pname = res;
			} 
			else 
			{
				pname = "";
			}
		} 
		catch (Exception e) 
		{
			System.out.println("Exception in getting pname" + e.toString());
			e.printStackTrace();
		}
		return pname;
	}

	public String[][] getCommodityDiscrip(String qid) 
	{
		String[][] commodity = new String[0][0];
		String args[] = new String[1];
		String sql = ""; 
		try 
		{
			args[0] = qid;
			sql = "select application_no,commodity_id,description from marine_result_details  where application_no=(select application_no from position_master where quote_no=?)";
			commodity = runner.multipleSelection(sql,args);
		} 
		catch (Exception e) 
		{
			System.out.println("Exception in getting customer info"+ e.toString());
			e.printStackTrace();
		}
		return commodity;
	}
	//Used
	public String[][] customReports(String data1, String data2, String pid,	String[] LoginId, String businessType, String commodityId,String mode, String coverages, String rag1, String rag2,String rag3, String origin, String destin,
			String branchCode,String brokerCodes,String freightStatus,String actualBranch) //SmartReportDisplayAll.jsp Tax Broker_codes Restriction
	{
		String brokerloginid = "";
		String startdate = data1;
		String enddate = data2;
		String businesstype = "";
		String mode1 = "";
		String mot = "";
		String covers = "";
		String coverages1 = "";
		int flag1 = 0;
		String commodityId1 = "";
		String[][] logggid = new String[0][0];
		String qry = "";
		int flag = 0;
		String orgdest = "", orgdest1 = "";
		pid = pid.trim();
		String qrydec = "";
		String ragVal = "";
		String syntax="";
		
		String missippiCheck = "";
		String missippiFrom = "";
		String missippiNo = "";

		String freightCheck = "";
		if(freightStatus.equalsIgnoreCase("Yes"))
		{
			freightCheck = "and a.FREIGHT_STATUS='F'";
		}
		else
		{
			freightCheck = "and a.FREIGHT_STATUS is null";
		}
		if ("11".equalsIgnoreCase(pid)) 
		{
			missippiCheck = "and ocm.status='Y' and ocpm.status in('P','D') and ocm.proposal_no=ocpm.proposal_no and ocm.amend_id||'-'||ocm.proposal_no in (select max(ocms.amend_id)||'-'||ocms.proposal_no from open_cover_master ocms,open_cover_position_master ocpms where ocpms.proposal_no=ocms.proposal_no group by ocms.proposal_no) and a.open_cover_no=ocpm.OPEN_COVER_NO";
		
			missippiFrom = ",open_cover_position_master ocpm,open_cover_master ocm";
		
			missippiNo = ",ocm.MISSIPPI_OPENCOVER_NO";
		}
		else if ("3".equalsIgnoreCase(pid)) 
		{
			missippiNo = ",nvl(a.MISSIPPI_OPENCOVER_NO,'-')";
		}

		if(brokerCodes.equalsIgnoreCase("") || brokerCodes.length() ==0)
		{
			syntax = "select agency_code from broker_company_master where branch_code in("+actualBranch+")";
		}
		else
		{
			brokerCodes = brokerCodes.replaceAll(",","','");
			syntax = "'"+brokerCodes+"'";
		}

		if (!"R".equalsIgnoreCase(rag1) && !"A".equalsIgnoreCase(rag2)&& !"G".equalsIgnoreCase(rag3)) {
			ragVal = "";
		} else {
			ragVal = "and md.rag in ('" + rag1 + "','" + rag2 + "','" + rag3+ "')";
		}
		if ("all".equalsIgnoreCase(pid)) {
			pid = "3,11";
		}
		if ("0".equalsIgnoreCase(businessType)) {
			businesstype = "and a.business_type = 0 ";
		} 
		else if ("1".equalsIgnoreCase(businessType)) 
		{
			//businesstype = "and a.business_type is null";
			businesstype = "and a.business_type='1' ";
		} 
		else 
		{
		
		}
		if ("all".equalsIgnoreCase(origin)) {
			orgdest = "";
		} else {
			orgdest = "and b.transit_from_country_id = " + origin + " ";
		}
		if ("all".equalsIgnoreCase(destin)) {
			orgdest1 = "";
		} else {
			orgdest1 = "and b.final_destination_country_id = " + destin + " ";
		}
		String returnval[][] = new String[0][0];
		if (mode != null) {
			if (mode.length() > 0) {
				if (!",".equalsIgnoreCase(mode)) {
					mode1 = mode.substring(1, mode.length());
					mot = "and f.mode_transport_id in (" + mode1 + ")";
				}
			}
		}
		if (coverages != null) {
			if (coverages.length() > 0 && !",".equalsIgnoreCase(coverages)) {
				coverages1 = coverages.substring(1, coverages.length());
				covers = "and b.cover_id in (" + coverages1 + ")";
			}
		}
		if ((LoginId != null) && (LoginId.length > 0)) 
		{
			for (int i = 0; i < LoginId.length; i++) 
			{
				if ("ALL".equalsIgnoreCase(LoginId[i])) 
				{
					flag = 1;
				} 
				else 
				{
					brokerloginid = brokerloginid + "'" + LoginId[i] + "'"+ ",";
				}
			}
			if (flag != 1) 
			{
				brokerloginid = brokerloginid.substring(0, brokerloginid.length() - 1);
			}
		} 
		else 
		{
			flag = 1;
		}
		if (commodityId.length() > 0) {
			commodityId = commodityId.substring(1, commodityId.length());
			commodityId = commodityId.trim();
			commodityId1 = "and md.commodity_id in (" + commodityId + ")";
		}
		if ("ALL".equalsIgnoreCase(commodityId1)) {
			flag1 = 1;
		} else {
			flag1 = 0;
		}
		if ((flag == 1) && (flag1 == 1)) // 40 - Broker Remarks - 41-Tax 
		{
			qry = "select a.policy_no,to_char(a.EXPIRY_DATE,'dd-MM-yyyy HH12:MI:SS'),f.transport_description,co.cover_name, z.sale_term_name, ex.extra_cover_name,round(b.total_sum_insured), a.premium, nvl(a.commission,0), b.remarks,a.login_id,md.war_charges, a.quote_no,a.debit_note_no,nvl (k.first_name, k.company_name),x.country_name as origination_from, y.country_name as final_destination,b.exchange_rate,a.login_id, a.product_id,nvl(mp.carrier_name,' '), a.excess_premium,b.CURRENCY_NAME,p.product_name, b.TOTAL_SALE_TERM_CHARGES, a.business_type, md.commodity_id, cm.commodity_name, md.description, nvl(b.referral,''), md.rag , round(md.sum_insured),nvl(md.war_charges,0), nvl(b.remarks,''),round(md.sum_insured*b.exchange_rate),md.rate,b.CURRENCY_NAME,md.warrate,nvl(a.PRO_COMMISSION,'0'),b.EXCHANGE_RATE,nvl(a.BROKER_REMARKS,' '),nvl(a.tax,'0'),nvl(a.application_id,'1') "+missippiNo+",t.TOLERANCE_NAME,to_char(a.entry_date,'DD-MM-YYYY HH12:MI:SS') from position_master a, marine_data b, country_master x,country_master y,product_master p, mode_of_transport f, marine_result_details md ,commoditymaster cm, personal_info k,marine_policy_details mp, cover_master co,extra_cover_master ex ,sale_term_master z,TOLERANCE_MASTER t "+missippiFrom+" where a.application_no=b.application_no and a.status='P' and b.mode_of_transport = f.mode_transport_id and b.cover_id = co.cover_id and b.extra_cover_id = ex.extra_cover_id and z.sale_term_id = b.sale_term_id and p.product_id = a.product_id and mp.quote_no = a.quote_no and t.TOLERANCE_ID = b.TOLERANCE_ID and (co.branch_code="+branchCode+" and ex.branch_code="+branchCode+" and z.branch_code="+branchCode+" and cm.branch_code="+branchCode+" and p.branch_code="+branchCode+" and f.branch_code="+branchCode+" and t.branch_code="+branchCode+") and k.customer_id = a.customer_id and k.application_id='1' and  (a.inception_date between to_date('"+ startdate	+ "','dd-mm-yyyy') and to_date('"+ enddate+ "','dd-mm-yyyy')+1 )  and a.login_id in(select l.login_id from broker_company_master bk,login_master l where l.oa_code=bk.agency_code and l.usertype='Broker') and a.product_id in ("+ pid+ ") and x.country_id=b.transit_from_country_id and x.amend_id||'-'||x.country_id in (select max(amend_id) ||'-'|| country_id from country_master where status in ('Y','R') group by country_id) and y.country_id=b.final_destination_country_id and y.amend_id||'-'||y.country_id in (select max(amend_id) ||'-'|| country_id from country_master where status in ('Y','R') group by country_id) and md.application_no=a.application_no and md.application_no=b.application_no and cm.commodity_id=md.commodity_id and cm.amend_id||'-'||cm.commodity_id in (select max(amend_id) ||'-'|| commodity_id from commoditymaster where status in ('Y','R' ) and branch_code="+branchCode+" group by commodity_id) "+ covers+ " "+ commodityId1+ " "+ mot	+ " "+ orgdest	+ " "+ orgdest1+ " "	+ businesstype	+ " "+ ragVal+ " and a.login_id in(select login_id from login_master where oa_code in("+syntax+")) "+freightCheck+" "+missippiCheck+" order by a.inception_date desc";
		} else if ((flag == 0) && (flag1 == 0)) {
			qry = "select a.policy_no,to_char(a.EXPIRY_DATE,'dd-MM-yyyy HH12:MI:SS'),f.transport_description,co.cover_name, z.sale_term_name, ex.extra_cover_name,round(b.total_sum_insured), a.premium, nvl(a.commission,0), b.remarks,a.login_id,md.war_charges, a.quote_no,a.debit_note_no,nvl (k.first_name, k.company_name),x.country_name as origination_from, y.country_name as final_destination,b.exchange_rate,a.login_id, a.product_id,nvl(mp.carrier_name,' '), a.excess_premium,b.CURRENCY_NAME,p.product_name, b.TOTAL_SALE_TERM_CHARGES, a.business_type, md.commodity_id, cm.commodity_name, md.description, nvl(b.referral,''), md.rag , round(md.sum_insured),nvl(md.war_charges,0), nvl(b.remarks,''),round(md.sum_insured*b.exchange_rate),md.rate,b.CURRENCY_NAME,md.warrate,nvl(a.PRO_COMMISSION,'0'),b.EXCHANGE_RATE,nvl(a.BROKER_REMARKS,' '),nvl(a.tax,'0'),nvl(a.application_id,'1') "+missippiNo+",t.TOLERANCE_NAME,to_char(a.entry_date,'DD-MM-YYYY HH12:MI:SS') from position_master a, marine_data b, country_master x,country_master y,product_master p, mode_of_transport f, marine_result_details md ,commoditymaster cm, personal_info k,marine_policy_details mp, cover_master co,extra_cover_master ex ,sale_term_master z,TOLERANCE_MASTER t "+missippiFrom+" where a.application_no=b.application_no and a.status='P' and b.mode_of_transport = f.mode_transport_id and b.cover_id = co.cover_id and b.extra_cover_id = ex.extra_cover_id and z.sale_term_id = b.sale_term_id and p.product_id = a.product_id and mp.quote_no = a.quote_no and t.TOLERANCE_ID = b.TOLERANCE_ID and (co.branch_code="+branchCode+" and ex.branch_code="+branchCode+" and z.branch_code="+branchCode+" and cm.branch_code="+branchCode+" and p.branch_code="+branchCode+" and f.branch_code="+branchCode+" and t.branch_code="+branchCode+") and k.customer_id = a.customer_id and k.application_id='1' and (a.inception_date between to_date('"	+ startdate+ "','dd-mm-yyyy') and to_date('"+ enddate+ "','dd-mm-yyyy')+1 ) and a.login_id in(select l.login_id from login_master l where l.oa_code in("+ brokerloginid	+ "))and a.product_id in ("	+ pid+ ")   and x.country_id=b.transit_from_country_id and x.amend_id||'-'||x.country_id in (select max(amend_id) ||'-'|| country_id from country_master where status in ('Y','R') group by country_id) and y.country_id=b.final_destination_country_id and y.amend_id||'-'||y.country_id in (select max(amend_id) ||'-'|| country_id from country_master where status in ('Y','R') group by country_id) and md.application_no=a.application_no and md.application_no=b.application_no and cm.commodity_id=md.commodity_id and cm.amend_id||'-'||cm.commodity_id in (select max(amend_id) ||'-'|| commodity_id from commoditymaster where status in ('Y','R' ) and branch_code="+branchCode+" group by commodity_id) "	+ covers+ " "+ commodityId1	+ " "+ mot+ " "	+ orgdest+ " "+ orgdest1+ " "+ businesstype	+ " "+ ragVal+ "  and a.login_id in(select login_id from login_master where oa_code in("+syntax+")) "+freightCheck+" "+missippiCheck+"  order by a.inception_date desc";
		}
		else if ((flag != 1) && (flag1 == 1)) {
			qry = "select a.policy_no,to_char(a.EXPIRY_DATE,'dd-MM-yyyy HH12:MI:SS'),f.transport_description,co.cover_name, z.sale_term_name, ex.extra_cover_name,round(b.total_sum_insured), a.premium, nvl(a.commission,0), b.remarks,a.login_id,md.war_charges, a.quote_no,a.debit_note_no,nvl (k.first_name, k.company_name),x.country_name as origination_from, y.country_name as final_destination,b.exchange_rate,a.login_id, a.product_id,nvl(mp.carrier_name,' '), a.excess_premium,b.CURRENCY_NAME,p.product_name, b.TOTAL_SALE_TERM_CHARGES, a.business_type, md.commodity_id, cm.commodity_name, md.description, nvl(b.referral,''), md.rag , round(md.sum_insured),nvl(md.war_charges,0), nvl(b.remarks,''),round(md.sum_insured*b.exchange_rate),md.rate,b.CURRENCY_NAME,md.warrate,nvl(a.PRO_COMMISSION,'0'),b.EXCHANGE_RATE,nvl(a.BROKER_REMARKS,' '),nvl(a.tax,'0'),nvl(a.application_id,'1') "+missippiNo+",t.TOLERANCE_NAME,to_char(a.entry_date,'DD-MM-YYYY HH12:MI:SS') from position_master a, marine_data b, country_master x,country_master y,product_master p, mode_of_transport f, marine_result_details md ,commoditymaster cm, personal_info k,marine_policy_details mp, cover_master co,extra_cover_master ex ,sale_term_master z,TOLERANCE_MASTER t "+missippiFrom+" where a.application_no=b.application_no and a.status='P' and b.mode_of_transport = f.mode_transport_id and b.cover_id = co.cover_id and b.extra_cover_id = ex.extra_cover_id and z.sale_term_id = b.sale_term_id and p.product_id = a.product_id and mp.quote_no = a.quote_no and t.TOLERANCE_ID = b.TOLERANCE_ID  and (co.branch_code="+branchCode+" and ex.branch_code="+branchCode+" and z.branch_code="+branchCode+" and cm.branch_code="+branchCode+" and p.branch_code="+branchCode+" and f.branch_code="+branchCode+" and t.branch_code="+branchCode+")  and k.customer_id = a.customer_id and k.application_id='1' and (a.inception_date between to_date('"	+ startdate	+ "','dd-mm-yyyy') and to_date('"+ enddate+ "','dd-mm-yyyy')+1 ) and a.login_id in(select l.login_id from login_master l where l.oa_Code in ("+ brokerloginid+ "))and a.product_id in ("+ pid+ ")   and x.country_id=b.transit_from_country_id and x.amend_id||'-'||x.country_id in (select max(amend_id) ||'-'|| country_id from country_master where status in ('Y','R') group by country_id) and y.country_id=b.final_destination_country_id and y.amend_id||'-'||y.country_id in (select max(amend_id) ||'-'|| country_id from country_master where status in ('Y','R') group by country_id) and md.application_no=a.application_no and md.application_no=b.application_no and cm.commodity_id=md.commodity_id and cm.amend_id||'-'||cm.commodity_id in (select max(amend_id) ||'-'|| commodity_id from commoditymaster where status in ('Y','R' ) and branch_code="+branchCode+" group by commodity_id) "	+ covers+ " "+ commodityId1	+ " "+ mot+ " "+ orgdest+ " "	+ orgdest1+ " "+ businesstype+ " "+ ragVal+ " and a.login_id in(select login_id from login_master where oa_code in("+syntax+")) "+freightCheck+" "+missippiCheck+" order by a.inception_date desc";
		} else {
			if (brokerloginid.length() < 1) {
				qrydec = "(select l.login_id from broker_company_master k,login_master l where l.oa_code=k.agency_code )";
			} else {
				qrydec = "(select login_id from login_master where oa_Code in ("+ brokerloginid + "))";
			}
			qry = "select a.policy_no,to_char(a.EXPIRY_DATE,'dd-MM-yyyy HH12:MI:SS'),f.transport_description,co.cover_name, z.sale_term_name, ex.extra_cover_name,round(b.total_sum_insured), a.premium, nvl(a.commission,0), b.remarks,a.login_id,md.war_charges, a.quote_no,a.debit_note_no,nvl (k.first_name, k.company_name),x.country_name as origination_from, y.country_name as final_destination,b.exchange_rate,a.login_id, a.product_id,nvl(mp.carrier_name,' '), a.excess_premium,b.CURRENCY_NAME,p.product_name, b.TOTAL_SALE_TERM_CHARGES, a.business_type, md.commodity_id, cm.commodity_name, md.description, nvl(b.referral,''), md.rag , round(md.sum_insured),nvl(md.war_charges,0), nvl(b.remarks,''),round(md.sum_insured*b.exchange_rate),md.rate,b.CURRENCY_NAME,md.warrate,nvl(a.PRO_COMMISSION,'0'),b.EXCHANGE_RATE,nvl(a.BROKER_REMARKS,' '),nvl(a.tax,'0'),nvl(a.application_id,'1') "+missippiNo+",t.TOLERANCE_NAME,to_char(a.entry_date,'DD-MM-YYYY HH12:MI:SS') from position_master a, marine_data b, country_master x,country_master y,product_master p, mode_of_transport f, marine_result_details md ,commoditymaster cm, personal_info k,marine_policy_details mp, cover_master co,extra_cover_master ex ,sale_term_master z,TOLERANCE_MASTER t "+missippiFrom+" where a.application_no=b.application_no and a.status='P' and b.mode_of_transport = f.mode_transport_id and b.cover_id = co.cover_id and b.extra_cover_id = ex.extra_cover_id and z.sale_term_id = b.sale_term_id and p.product_id = a.product_id and mp.quote_no = a.quote_no and t.TOLERANCE_ID = b.TOLERANCE_ID  and (co.branch_code="+branchCode+" and ex.branch_code="+branchCode+" and z.branch_code="+branchCode+" and cm.branch_code="+branchCode+" and p.branch_code="+branchCode+" and f.branch_code="+branchCode+" and t.branch_code="+branchCode+")  and k.customer_id = a.customer_id and k.application_id='1' and  (a.inception_date between to_date('"	+ startdate	+ "','dd-mm-yyyy') and to_date('"+ enddate+ "','dd-mm-yyyy')+1 ) and a.login_id in "	+ qrydec+ " and a.product_id in ("+ pid+ ")   and x.country_id=b.transit_from_country_id and x.amend_id||'-'||x.country_id in (select max(amend_id) ||'-'|| country_id from country_master where status in ('Y','R') group by country_id) and y.country_id=b.final_destination_country_id and y.amend_id||'-'||y.country_id in (select max(amend_id) ||'-'|| country_id from country_master where status in ('Y','R') group by country_id) and md.application_no=a.application_no and md.application_no=b.application_no and cm.commodity_id=md.commodity_id and cm.amend_id||'-'||cm.commodity_id in (select max(amend_id) ||'-'|| commodity_id from commoditymaster where status in ('Y','R' ) and branch_code="+branchCode+" group by commodity_id) "+ covers+ " "+ commodityId1	+ " "+ mot+ " "+ orgdest+ " "+ orgdest1+ " "+ businesstype+ " "	+ ragVal+ "  and a.login_id in(select login_id from login_master where oa_code in("+syntax+")) "+freightCheck+" "+missippiCheck+"  order by a.inception_date desc";
		}
		try {
			returnval = runner.multipleSelection(qry);
		} catch (Exception e) {
		}
		return returnval;
	}
	
	//Used
	public HashMap checkingvaluesSmartReports(String data1, String data2,	String pid, String[] LoginId, String businessType,String commodityId, String mode, String coverages, String rag1,String rag2, String rag3, String origin, String destin,String branchCode,String brokerCodes,String freightStatus) //SmartReportInterMediate.jsp Tax, BrokerCodes Restriction
	{
		String brokerloginid = "";
		String startdate = data1;
		String enddate = data2;
		String businesstype = "";
		String mode1 = "";
		String mot = "";
		String covers = "";
		String coverages1 = "";
		String[][] logggid = new String[0][0];
		String qry = "";
		int polcount = 0;
		double totPrem = 0.0;
		double commis = 0.0;
		int flag = 0, flag1 = 0;
		String commodityId1 = "";
		String ragVal = "";
		String orgdest = "", orgdest1 = "";
		String brokerIds = "";
		String returnval[][] = new String[0][0];
		String values[][] = new String[0][0];
		String loggging[][] = new String[0][0];
		String ragValue[][] = new String[0][0];
		String gettingDatas[][] = new String[0][0];
		String addeddata = "";
		String syntax = "";
		
		String freightCheck = "";
		if(freightStatus.equalsIgnoreCase("Yes"))
		{
			freightCheck = "and a.FREIGHT_STATUS='F'";
		}
		else
		{
			freightCheck = "and a.FREIGHT_STATUS is null";
		}
		if(brokerCodes.equalsIgnoreCase("") || brokerCodes.length() ==0)
		{
			syntax = "select agency_code from broker_company_master where branch_code in("+branchCode+")";
		}
		else
		{
			brokerCodes = brokerCodes.replaceAll(",","','");
			syntax = "'"+brokerCodes+"'";
		}

		if (pid.equals("all")) {
			addeddata = "";
		} else {
			addeddata = "and  a.product_id='" + pid + "'";
		}
		pid = pid.trim();
		if ("all".equalsIgnoreCase(pid)) {
			pid = "3,11";
		}
		if ("0".equalsIgnoreCase(businessType)) {
			businesstype = "and a.business_type = 0 ";
		} 
		else if ("1".equalsIgnoreCase(businessType)) 
		{
			//businesstype = "and a.business_type is null";
			businesstype = "and a.business_type='1' ";

		} else {
		}
		
		if ("all".equalsIgnoreCase(origin)) {
			orgdest = "select distinct country_id from country_master";
		} else {
			orgdest = origin;
		}
		if ("all".equalsIgnoreCase(destin)) {
			orgdest1 = "select distinct country_id from country_master";
		} else {
			orgdest1 = destin;
		}
		if (!"R".equalsIgnoreCase(rag1) && !"A".equalsIgnoreCase(rag2)&& !"G".equalsIgnoreCase(rag3)) {
			ragVal = "";
		} else {
			ragVal = "and v.rag in ('" + rag1 + "','" + rag2 + "','" + rag3	+ "')";
		}
		if (mode != null) {
			if (mode.length() > 0) {
				if (!",".equalsIgnoreCase(mode)) {
					mode1 = mode.substring(1, mode.length());
					mot = "and b.mode_of_transport in (" + mode1 + ")";
				}
			}
		}
		if (coverages != null) {
			if (coverages.length() > 0 && !",".equalsIgnoreCase(coverages)) {
				coverages1 = coverages.substring(1, coverages.length());
				covers = "and b.cover_id in (" + coverages1 + ")";
			}
		}
		if ((LoginId != null) && (LoginId.length > 0)) {
			for (int i = 0; i < LoginId.length; i++) {
				if ("ALL".equalsIgnoreCase(LoginId[i])) {
					flag = 1;
				} else {
					brokerloginid = brokerloginid + "'" + LoginId[i] + "'"+ ",";
					flag = 0;
				}
			}
			if (flag == 0) {
				brokerloginid = brokerloginid.substring(0,brokerloginid.length() - 1).trim();
			}
		} else {
			flag = 1;
		}
		
		if (commodityId.length() > 0) {
			commodityId = commodityId.trim();
			commodityId = commodityId.substring(1, commodityId.length());
			commodityId1 = "and v.commodity_id in (" + commodityId + ")";
		}
		if ("ALL".equalsIgnoreCase(commodityId)
				|| "all".equalsIgnoreCase(commodityId)) {
			flag1 = 1;
		} else {
			flag1 = 0;
		}
		if ((flag == 1) && (flag1 == 1)) {
			qry = "select a.login_id,a.agency_code,a.oa_code,b.company_name from login_master a,broker_company_master b  where a.usertype='Broker' and a.login_id!='NON' and a.agency_code=b.agency_code and login_id!='NONE' and a.oa_code is not null and a.agency_code is not null and a.login_id in(select login_id from login_master where oa_code in("+syntax+")) order by lower(b.company_name)";
		}
		else if ((flag != 1) && (flag1 == 1)) {
			qry = "select a.login_id,a.agency_code,a.oa_code,b.company_name from login_master a,broker_company_master b where a.usertype='Broker' and a.login_id!='NON' and a.agency_code=b.agency_code and login_id!='NONE' and a.oa_code is not null and a.agency_code is not null and a.login_id in (select login_id from login_master where oa_Code in (select oa_code from login_master where login_id in ("+ brokerloginid + "))) and a.login_id in(select login_id from login_master where oa_code in("+syntax+")) order by lower(b.company_name)";
		} else if ((flag != 1) && (flag1 != 1)) {
			qry = "select a.login_id,a.agency_code,a.oa_code,b.company_name from login_master a,broker_company_master b where a.usertype='Broker' and a.login_id!='NON' and a.agency_code=b.agency_code and login_id!='NONE' and a.oa_code is not null and a.agency_code is not null and a.login_id in (select login_id from login_master where oa_Code in (select oa_code from login_master where login_id in ("+ brokerloginid + "))) and a.login_id in(select login_id from login_master where oa_code in("+syntax+")) order by lower(b.company_name)";
		} else {
			qry = "select a.login_id,a.agency_code,a.oa_code,b.company_name from login_master a,broker_company_master b  where a.usertype='Broker' and a.login_id!='NON' and a.agency_code=b.agency_code and login_id!='NONE' and a.oa_code is not null and a.agency_code is not null and a.login_id in(select login_id from login_master where oa_code in("+syntax+")) order by lower(b.company_name)";
		}
		try {
			values = runner.multipleSelection(qry);
			brokerLoginIds = values;
		} catch (Exception e) {
			System.out.println("Exception in getiing report dats by using date"	+ e.toString());
		}
		setBrokerLoginIds(values);
		java.util.HashMap brokerData = new java.util.HashMap();
		java.util.HashMap lids = new java.util.HashMap();
		try {
			if (("".equalsIgnoreCase(ragVal)) || (" ".equalsIgnoreCase(ragVal))) {
				int p = 0;
				for (int i = 0; i < values.length; i++) 
				{
					qry = "select count(a.POLICY_NO),sum(a.premium+nvl(a.Excess_Premium,0)),sum(nvl(a.commission,'0')),sum(nvl(a.PRO_COMMISSION,'0')),sum(nvl(a.tax,'0')) from position_master a, marine_data b, cover_master d, sale_term_master e, extra_cover_master f, personal_info g, mode_of_transport h,marine_policy_details mp where b.cover_id=d.cover_id and b.sale_term_id=e.sale_term_id and f.extra_cover_id=b.extra_cover_id and a.application_no=b.application_no and g.customer_id=a.customer_id and mp.quote_no=a.quote_no and b.mode_of_transport=h.mode_transport_id and a.DEBIT_NOTE_NO!='0' and (d.branch_code in("+branchCode+") and e.branch_code in("+branchCode+") and f.branch_code in("+branchCode+") and h.branch_code in("+branchCode+")) and a.login_id in(select login_id from login_master where oa_Code in(select oa_code from login_master where login_id='"+ values[i][0]+ "')) and a.inception_date between to_date('"+ startdate+ "','dd-mm-yyyy') and to_date('"+ enddate+ "','dd-mm-yyyy')+1 "+ addeddata	+ " "+ businesstype+ "  "+ mot+ "  "+ covers+ " and  b.transit_from_country_id in ("+ orgdest+ ")  and b.final_destination_country_id in ("+ orgdest1+ ") and a.status='P' and a.application_no = b.application_no  and a.application_no in (select v.application_no from marine_result_details v where a.application_no = v.application_no "+ commodityId1 + ") and a.login_id in(select login_id from login_master where oa_code in("+syntax+")) "+freightCheck+"";
					returnval = runner.multipleSelection(qry);
					if (!returnval[0][0].equals("0")) 
					{
						brokerData.put("Broker" + (p + 1), returnval);
						lids.put("IDS" + (p + 1), values[i][0]);
						p++;
					}
				}
				brokerData.put("values", values);
				brokerData.put("lids", lids);
			} else {
				int p = 0;
				int q = 0;
				int t = 0;
				int len = 0;
				for (int i = 0; i < values.length; i++) 
				{
					qry = "select count(a.POLICY_NO),sum(a.premium+nvl(a.Excess_Premium,0)),sum(nvl(a.commission,'0')),sum(nvl(a.PRO_COMMISSION,'0')),sum(nvl(a.tax,'0')) from position_master a, marine_data b, cover_master d, sale_term_master e, extra_cover_master f, personal_info g, mode_of_transport h,marine_policy_details mp where b.cover_id=d.cover_id and b.sale_term_id=e.sale_term_id and f.extra_cover_id=b.extra_cover_id and a.application_no=b.application_no and g.customer_id=a.customer_id and mp.quote_no=a.quote_no  and  b.mode_of_transport=h.mode_transport_id and a.DEBIT_NOTE_NO!='0' and (d.branch_code in("+branchCode+") and e.branch_code in("+branchCode+") and f.branch_code in("+branchCode+") and h.branch_code in("+branchCode+")) and a.login_id in(select login_id from login_master where oa_Code in(select oa_code from login_master where login_id='"+ values[i][0]+ "'))   and  a.inception_date between to_date('"+ startdate+ "','dd-mm-yyyy') and to_date('"+ enddate+ "','dd-mm-yyyy')+1 "+ addeddata	+ " "+ businesstype + "  "+ mot+ "  "+ covers+ " and  b.transit_from_country_id in ("+ orgdest+ ")  and b.final_destination_country_id in ("+ orgdest1+ ")   and a.status='P' and a.application_no = b.application_no  and a.application_no in (select v.application_no from marine_result_details v where a.application_no = v.application_no "	+ commodityId1 + " " + ragVal + ") and a.login_id in(select login_id from login_master where oa_code in("+syntax+")) "+freightCheck+"";
					returnval = runner.multipleSelection(qry);
					if (!returnval[0][0].equals("0")) 
					{
						
						brokerData.put("Broker" + (p + 1), returnval);
						lids.put("IDS" + (p + 1), values[i][0]);
						p++;
					}
				}
				brokerData.put("values", values);
				brokerData.put("lids", lids);
			}
		} catch (Exception e) {
			System.out.println("Exception in getiing report dats by using date"+ e.toString());
		} 
		return brokerData;
	}
	//for RSAIssuer
	public String[][] getRSAIssuerList(String loginBra)
	{
		String args[] = new String[1];
		if(loginBra.indexOf("'")!=-1)
			loginBra = loginBra.replaceAll("'","");
		String sql = ""; 
		String result[][] = new String[0][0];
		try
		{
			args[0] = loginBra;
			sql = "select LOGIN_ID from login_master where  oa_code in(select agency_code from login_master where rights='RSAIssuer' and oa_code in(select agency_code from broker_company_master where branch_code=?)) ";
			result = runner.multipleSelection(sql,args);
		}
		catch (Exception e) 
		{
			System.out.println("Exception in getRSAIssuerList...."+ e.toString());
			e.printStackTrace();
		} 
		return result;
	}

	public HashMap getHashMissCus(String missippiNo)
	{
		HashMap res = new HashMap();
		String tempQuot ="";
		tempQuot = missippiNo;
		StringTokenizer tokenQuote = new StringTokenizer(missippiNo,",");
		int quotCount=0;
		int start=0;
		int s=0;
		int end=500;
		int counter=500;
		int real,decimal;
		quotCount = tokenQuote.countTokens();
		System.out.println("Total Number Of Quotes ..."+quotCount);
		String qno[] = new String[quotCount];
		int q=0;
		while(tokenQuote.hasMoreTokens())
		{
			qno[q++] = tokenQuote.nextToken();
		}			
		real = quotCount / counter;
		decimal = quotCount % counter;
		if(decimal > 0 || real == 0)
		{
			real = real +1;
		}
		for(int p=0;p<real;p++)
		{
			String qryQuot="";
			if(p!=0)
			{
				start = end;
				end = end + counter;
			}
			if(decimal > 0 && (p+1 == real))
			{
				end = quotCount;
			}
			for(s=start;s<end;s++)
			{
				qryQuot =qryQuot+qno[s]+",";
			}
			qryQuot = qryQuot.substring(0,qryQuot.length()-1);

		/***** To Form the quote String ******/
			String sql = "select nvl(per.company_name,per.first_name),ocm.MISSIPPI_OPENCOVER_NO from personal_info per,OPEN_COVER_MASTER ocm where per.customer_id=ocm.customer_id and ocm.amend_id||ocm.MISSIPPI_OPENCOVER_NO in(select max(amend_id)||MISSIPPI_OPENCOVER_NO from OPEN_COVER_MASTER where MISSIPPI_OPENCOVER_NO in("+qryQuot+") group by MISSIPPI_OPENCOVER_NO)";
			String result[][] = new String[0][0];
			
			try
			{
				result = runner.multipleSelection(sql);
			}
			catch (Exception e) 
			{
				System.out.println("Exception in ...."+ e.toString());	e.printStackTrace();
			} 
			for(int i=0;i<result.length;i++)
			{
				res.put("cus"+result[i][1],result[i][0]);
			}
		}
		return res;
	}
	//For Customer Debit Note no in Smart Reports
	public HashMap getCusDebitDetails(String quoteNo)
	{
		String sql = "";
		java.util.HashMap debitHash = new java.util.HashMap();
		String[][] ss = new String[0][0];
		String[][] ss1 = new String[0][0];

		/***** To Form the quote String ******/
		String tempQuot ="";
		tempQuot = quoteNo;
		StringTokenizer tokenQuote = new StringTokenizer(quoteNo,",");
		int quotCount=0;
		int start=0;
		int s=0;
		int end=500;
		int counter=500;
		int real,decimal;
		quotCount = tokenQuote.countTokens();

		String qno[] = new String[quotCount];
		int q=0;
		while(tokenQuote.hasMoreTokens())
		{
			qno[q++] = tokenQuote.nextToken();
		}			
		real = quotCount / counter;
		decimal = quotCount % counter;
		if(decimal > 0 || real == 0)
		{
			real = real +1;
		}
		for(int p=0;p<real;p++)
		{
			String qryQuot="";
			if(p!=0)
			{
				start = end;
				end = end + counter;
			}
			if(decimal > 0 && (p+1 == real))
			{
				end = quotCount;
			}
			for(s=start;s<end;s++)
			{
				qryQuot =qryQuot+qno[s]+",";
			}
			qryQuot = qryQuot.substring(0,qryQuot.length()-1);
		
		/***** To Form the quote String ******/

		try 
		{
			sql = "select quote_no,CUSTOMER_DEBIT_NOTE_NO,to_char(CUSTOMER_DEBIT_NOTE_DATE,'dd-mm-yyyy'),nvl(DISCOUNT_PREMIUM,'0') from MARINE_CUSTOMER_DEBIT_MASTER where quote_no in("+qryQuot+") and CUSTOMER_DEBIT_NOTE_NO is not null";
			ss = runner.multipleSelection(sql);
			if (ss.length > 0) 
			{
				for (int j = 0; j < ss.length; j++) 
				{
					StringTokenizer st = new StringTokenizer(qryQuot, ",");
					while (st.hasMoreTokens()) 
					{
						if (ss[j][0].equalsIgnoreCase(st.nextToken())) 
						{
							if (!debitHash.containsKey("cusDebitNo"+ss[j][0])) 
							{
								debitHash.put("cusDebitNo"+ss[j][0], (ss[j][1]));
								debitHash.put("cusDebitDate"+ss[j][0], (ss[j][2]));
								debitHash.put("cusDebitPre"+ss[j][0], (ss[j][3]));
							} 
						}
					}
					
					
					
				}
			}
		}
		catch (Exception e) 
		{
			System.out.println("Exception in gettin debitHash data"	+ e.toString());
		}
	  } 
		return debitHash;
	}

	// Admin Side Smart Report Intermediate Screen.........

	public String[][] smartReportsIntermediate(String data1, String data2,	String pid, String[] LoginId, 
			String businessType,String commodityId, String mode, String coverages, String rag1,String rag2, 
			String rag3, String origin, String destin,String branchCode,String brokerCodes,String freightStatus,String actualBranch) //SmartReportInterMediate.jsp Tax, BrokerCodes Restriction
	{
		String smartRPTDet[][] = new String[0][0];
		
		String brokerloginid = "";
		String startdate = data1;
		String enddate = data2;
		String businesstype = "";
		String mode1 = "";
		String mot = "";
		String covers = "";
		String coverages1 = "";
		String qry = "";
		int flag = 0, flag1 = 0;
		String commodityId1 = "";
		String ragVal = "";
		String orgdest = "", orgdest1 = "";
		
		String returnval[][] = new String[0][0];
		String values[][] = new String[0][0];
		
		String addeddata = "";
		String syntax = "";
		
		String freightCheck = "";
		if(freightStatus.equalsIgnoreCase("Yes"))
			freightCheck = "and pos.FREIGHT_STATUS='F'";
		else
			freightCheck = "and pos.FREIGHT_STATUS is null";
		
		if(brokerCodes.equalsIgnoreCase("") || brokerCodes.length() == 0)
			syntax = "select agency_code from broker_company_master where branch_code in("+actualBranch+")";
		else
		{
			brokerCodes = brokerCodes.replaceAll(",","','");
			syntax = "'"+brokerCodes+"'";
		}

		if (pid.equals("all")) {
			addeddata = "";
		} else {
			addeddata = "and  pos.product_id='" + pid + "'";
		}
		pid = pid.trim();
		if ("all".equalsIgnoreCase(pid)) 
			pid = "3,11";
		
		
		if ("0".equalsIgnoreCase(businessType)) 
			businesstype = "and pos.business_type = 0 ";
		else if ("1".equalsIgnoreCase(businessType)) 
			businesstype = "and pos.business_type='1' ";
		else {
		}
		
		if ("all".equalsIgnoreCase(origin)) {
			orgdest = "select distinct country_id from country_master";
		} else {
			orgdest = origin;
		}
		if ("all".equalsIgnoreCase(destin)) {
			orgdest1 = "select distinct country_id from country_master";
		} else {
			orgdest1 = destin;
		}
		if (!"R".equalsIgnoreCase(rag1) && !"A".equalsIgnoreCase(rag2)&& !"G".equalsIgnoreCase(rag3)) {
			ragVal = "";
		} else {
			ragVal = "and mrd.rag in ('" + rag1 + "','" + rag2 + "','" + rag3	+ "')";
		}
		
		if (mode != null) {
			if (mode.length() > 0) {
				if (!",".equalsIgnoreCase(mode)) {
					mode1 = mode.substring(1, mode.length());
					mot = "and md.mode_of_transport in (" + mode1 + ")";
				}
			}
		}
		
		if (coverages != null) {
			if (coverages.length() > 0 && !",".equalsIgnoreCase(coverages)) {
				coverages1 = coverages.substring(1, coverages.length());
				covers = "and md.cover_id in (" + coverages1 + ")";
			}
		}
		
		if ((LoginId != null) && (LoginId.length > 0)) {
			for (int i = 0; i < LoginId.length; i++) {
				if ("ALL".equalsIgnoreCase(LoginId[i])) {
					flag = 1;
				} else {
					brokerloginid = brokerloginid + "'" + LoginId[i] + "'"+ ",";
					flag = 0;
				}
			}
			if (flag == 0) {
				brokerloginid = brokerloginid.substring(0,brokerloginid.length() - 1).trim();
			}
		} else {
			flag = 1;
		}
		
		if (commodityId.length() > 0) {
			commodityId = commodityId.trim();
			commodityId = commodityId.substring(1, commodityId.length());
			commodityId1 = "and mrd.commodity_id in (" + commodityId + ")";
		}
		
		if ("ALL".equalsIgnoreCase(commodityId)
				|| "all".equalsIgnoreCase(commodityId)) {
			flag1 = 1;
		} else {
			flag1 = 0;
		}
		
		if ((flag == 1) && (flag1 == 1)) 
		{
			System.out.println("First "+"flag   "+flag+" ...flag1       "+ flag1);
			qry = "select a.login_id,a.agency_code,a.oa_code,b.company_name from login_master a,broker_company_master b  where a.usertype='Broker' and a.login_id!='NON' and a.agency_code=b.agency_code and login_id!='NONE' and a.oa_code is not null and a.agency_code is not null and a.login_id in(select login_id from login_master where oa_code in("+syntax+")) order by lower(b.company_name)";
		}
		else if ((flag != 1) && (flag1 == 1)) {
			System.out.println("Second "+"flag   "+flag+" ...flag1       "+ flag1);
			qry = "select a.login_id,a.agency_code,a.oa_code,b.company_name from login_master a,broker_company_master b where a.usertype='Broker' and a.login_id!='NON' and a.agency_code=b.agency_code and login_id!='NONE' and a.oa_code is not null and a.agency_code is not null and a.login_id in (select login_id from login_master where oa_Code in (select oa_code from login_master where login_id in ("+ brokerloginid + "))) and a.login_id in(select login_id from login_master where oa_code in("+syntax+")) order by lower(b.company_name)";
		} else if ((flag != 1) && (flag1 != 1)) {
			System.out.println("Third "+"flag   "+flag+" ...flag1       "+ flag1);
			qry = "select a.login_id,a.agency_code,a.oa_code,b.company_name from login_master a,broker_company_master b where a.usertype='Broker' and a.login_id!='NON' and a.agency_code=b.agency_code and login_id!='NONE' and a.oa_code is not null and a.agency_code is not null and a.login_id in (select login_id from login_master where oa_Code in (select oa_code from login_master where login_id in ("+ brokerloginid + "))) and a.login_id in(select login_id from login_master where oa_code in("+syntax+")) order by lower(b.company_name)";
		} else {
			System.out.println("Fourth"+"flag   "+flag+" ...flag1       "+ flag1);
			qry = "select a.login_id,a.agency_code,a.oa_code,b.company_name from login_master a,broker_company_master b  where a.usertype='Broker' and a.login_id!='NON' and a.agency_code=b.agency_code and login_id!='NONE' and a.oa_code is not null and a.agency_code is not null and a.login_id in(select login_id from login_master where oa_code in("+syntax+")) order by lower(b.company_name)";
		}
		String agencyCode = "";
		String addedAgency = "";
		try 
		{
			values = runner.multipleSelection(qry);
			
			System.out.println("Smart Report Values ......Login OA_code ...."+values.length);
			for (int i = 0; i < values.length; i++) 
				System.out.println("Login oa_code ...."+values[i][1]);
			
			brokerLoginIds = values;
			//if(values.length > 0 && LoginId.length==1 && !LoginId[0].equalsIgnoreCase("ALL"))
			if(values.length > 0)
			{
				for (int i = 0; i < values.length; i++) 
					agencyCode += values[i][1]+",";
				if(agencyCode.length() > 0)
				{
					if(",".equals(agencyCode.substring(0,agencyCode.length()-1)))
							agencyCode = agencyCode.substring(0,agencyCode.length()-1);
					agencyCode = agencyCode.replaceAll(",","','");
					agencyCode = "'"+agencyCode+"'";
				}
			}
		} 
		catch (Exception e) {
			System.out.println("Exception in getiing report dats by using date"	+ e.toString());
			e.printStackTrace();
		}
				
		if(LoginId.length > 0 && !LoginId[0].equalsIgnoreCase("ALL"))
			addedAgency = " and log.oa_code in("+agencyCode+") ";
		
		setBrokerLoginIds(values);
		
		try 
		{
			if (("".equalsIgnoreCase(ragVal)) || (" ".equalsIgnoreCase(ragVal))) 
				qry = "select bcm.company_name,count(bcm.company_name),sum(pos.premium+nvl(pos.excess_premium,0)),sum(nvl(pos.commission,0)),sum(nvl(pos.PRO_COMMISSION,0)),sum(nvl(pos.tax,0)),bcm.agency_code from position_master pos,broker_company_master bcm,login_master log,personal_info per, marine_data md,cover_master cm,sale_term_master stm,extra_cover_master ecm, mode_of_transport mot,marine_policy_details mpd where pos.status='P' and md.cover_id=cm.cover_id and md.sale_term_id=stm.sale_term_id and md.EXTRA_COVER_ID=ecm.extra_cover_id and md.MODE_OF_TRANSPORT=mot.MODE_TRANSPORT_ID and stm.branch_code=ecm.branch_code and ecm.branch_code=mot.branch_code and mot.branch_code=cm.branch_code and cm.branch_code in("+branchCode+") and bcm.branch_code in("+actualBranch+") and pos.login_id=log.login_id  "+addedAgency+" and log.oa_code=bcm.agency_code and pos.customer_id=per.customer_id and mpd.quote_no=pos.quote_no and md.application_no=pos.application_no "+addeddata+" "+ businesstype+ " "+ mot+ "  "+ covers+ " and md.transit_from_country_id in ("+ orgdest+") and md.final_destination_country_id in ("+ orgdest1+ ") and pos.application_no in(select mrd.application_no from marine_result_details mrd where mrd.application_no=pos.application_no "+ commodityId1 + " ) "+freightCheck+" and pos.inception_date between to_date('"+ startdate+ "','dd-mm-yyyy') and to_date('"+ enddate+ "','dd-mm-yyyy')+1 group by bcm.company_name,bcm.agency_code order by count(bcm.company_name) desc ";
			else 
				qry = "select bcm.company_name,count(bcm.company_name),sum(pos.premium+nvl(pos.excess_premium,0)),sum(nvl(pos.commission,0)),sum(nvl(pos.PRO_COMMISSION,0)),sum(nvl(pos.tax,0)),bcm.agency_code from position_master pos,broker_company_master bcm,login_master log,personal_info per, marine_data md,cover_master cm,sale_term_master stm,extra_cover_master ecm, mode_of_transport mot,marine_policy_details mpd where pos.status='P' and md.cover_id=cm.cover_id and md.sale_term_id=stm.sale_term_id and md.EXTRA_COVER_ID=ecm.extra_cover_id and md.MODE_OF_TRANSPORT=mot.MODE_TRANSPORT_ID and stm.branch_code=ecm.branch_code and ecm.branch_code=mot.branch_code and mot.branch_code=cm.branch_code and cm.branch_code in("+branchCode+") and bcm.branch_code in("+actualBranch+") and pos.login_id=log.login_id  "+addedAgency+" and log.oa_code=bcm.agency_code and pos.customer_id=per.customer_id and mpd.quote_no=pos.quote_no and md.application_no=pos.application_no "+addeddata+" "+ businesstype+ " "+ mot+ "  "+ covers+ " and md.transit_from_country_id in ("+ orgdest+") and md.final_destination_country_id in ("+ orgdest1+ ") and pos.application_no in(select mrd.application_no from marine_result_details mrd where mrd.application_no=pos.application_no "+ commodityId1 + " " + ragVal + " ) "+freightCheck+" and pos.inception_date between to_date('"+ startdate+ "','dd-mm-yyyy') and to_date('"+ enddate+ "','dd-mm-yyyy')+1 group by bcm.company_name,bcm.agency_code order by count(bcm.company_name) desc ";
			System.out.println("Smart Report Inter mediate Query "+qry);
			smartRPTDet = runner.multipleSelection(qry);
		} 
		catch (Exception e) {
			
			System.out.println("Exception in getiing report dats by using date"+ e.toString());
			e.printStackTrace();
		} 
		
		return smartRPTDet;
	}
	
	public String getBrokerLoginFromAgencyCode(String agencyCode)
	{
		String qry = "";
		String args[] = new String[1];
		String loginId = "";
		try
		{
			args[0] = agencyCode;
			qry = "select login_id from login_master where agency_code=oa_code and agency_code=?";
			loginId = runner.singleSelection(qry,args);
		}
		catch(Exception E)
		{
			System.out.println("get Broker Login From Agency Code..."+E);
			E.printStackTrace();
		}
		return loginId;
	}
	
	public String getMultiBrokerLoginFromAgencyCode(String agencyCode)
	{
		System.out.println("getMultiBrokerLoginFromAgencyCode ---------------------");
		String qry = "";
		String logId[][] = new String[0][0];
		String loginId = "";
		String temp="";
		try
		{
			qry = "select login_id from login_master where usertype='Broker' and agency_code in ("+agencyCode+")";
			logId = runner.multipleSelection(qry);
			System.out.println("getMultiBrokerLoginFromAgencyCode ---------------------sql "+qry);
		}
		catch(Exception E)
		{
			System.out.println("get Multi Broker Login From Agency Code..."+E);
			E.printStackTrace();
		}
		for(int i=0;i<logId.length;i++)
		{
			loginId = loginId + logId[i][0]+",";
			System.out.println("dsafdsf   "+loginId);
		}
		if(loginId.length() > 0 )
		{
			loginId = loginId.substring(0,loginId.length()-1);
		}
		return loginId;
	}
	public String[][] getCurrencyDetails(String countryId, String effectiveDate)	
	{
			String currencyInfo[][] = new String[0][0];
			String sql ="SELECT A.EXCHANGE_RATE,B.CURRENCY_NAME,A.AMEND_ID,TO_CHAR(A.EFFECTIVE_DATE,'DD-MM-YYYY'),A.CURRENCY_ID FROM EXCHANGE_MASTER A, CURRENCY_MASTER B WHERE A.CURRENCY_ID=B.CURRENCY_ID AND A.COUNTRY_ID=B.COUNTRY_ID AND A.COUNTRY_ID=? AND A.AMEND_ID||A.EXCHANGE_ID||A.CURRENCY_ID IN(SELECT MAX(AMEND_ID)||EXCHANGE_ID||CURRENCY_ID FROM EXCHANGE_MASTER WHERE NVL(TO_DATE(?,'dd-mm-yyyy'),SYSDATE)+ 10 / 24 BETWEEN EFFECTIVE_DATE AND EXPIRY_DATE AND STATUS='Y' AND COUNTRY_ID=? GROUP BY EXCHANGE_ID,CURRENCY_ID) AND NVL(TO_DATE(?,'dd-mm-yyyy'),SYSDATE)+ 10 / 24 BETWEEN A.EFFECTIVE_DATE AND A.EXPIRY_DATE AND A.STATUS='Y' AND B.STATUS='Y' AND B.STATUS='Y' AND A.STATUS='Y' AND  B.AMEND_ID||B.CURRENCY_ID IN(SELECT MAX(AMEND_ID)||CURRENCY_ID FROM CURRENCY_MASTER WHERE EFFECTIVE_DATE <=SYSDATE+10/24 AND STATUS='Y' AND COUNTRY_ID=? GROUP BY CURRENCY_ID) ORDER BY A.DISPLAY_ORDER";
			try{
				currencyInfo = runner.multipleSelection(sql,new String[]{countryId, effectiveDate, countryId, effectiveDate, countryId});
			}
			catch(Exception e){e.printStackTrace();}
			return currencyInfo;
	}
	public List getBranchList(String startDate,String endDate,String type,String productId) {
		
		List result=null;
		System.out.println("getBranch List Enters..");
		//String sql = "select ENTRY_DATE,BRANCH_NAME,LOGIN_ID,APPLICATIONID,QUOTE_NO,APPLICATION_NO,POLICY_NO,INCEPTION_DATE,EFFECTIVE_DATE,PRODUCT_ID,CUSTOMER_NAME,TRANSPORT_DESCRIPTION,COVER_NAME,SALE_TERM_NAME,EXTRA_COVER_NAME,TOTAL_SUM_INSURED,CURRENCY_NAME,EXCHANGE_RATE,EQUIVALENT_USD,PREMIUM,COMMISSION,TOTAL_WAR_CHARGES,TOTAL_SALE_TERM_CHARGES,EXCESS_PREMIUM,POLICY_FEE,PREMIUMTAX,TRANSIT_FROM_CITY,TRANSIT_FROM_COUNTRY,FINAL_DEST_CITY,FINAL_DESTINATION_COUNTRY,DEBIT_NOTE_NO,REFERRAL,REFERAL_STATUS,STATUS,INTEGRATION_STATUS,INTEGRATION_ERROR from table(select AdminReports(?,?,?) from dual) where Product_id=?";
		String sql = "select ENTRY_DATE,BRANCH_NAME,LOGIN_ID,APPLICATIONID,QUOTE_NO,APPLICATION_NO,POLICY_NO,INCEPTION_DATE,EFFECTIVE_DATE,PRODUCT_ID,CUSTOMER_NAME,TRANSPORT_DESCRIPTION,COVER_NAME,SALE_TERM_NAME,TOTAL_SLAE_TERM_CHARGES,TOTAL_TOLERANCE_CHARGES,EXTRA_COVER_NAME,SUM_INSURED_LOCAL,CURRENCY_NAME,EXCHANGE_RATE,SUM_INSURED_FOREIGN,BASIC_PREMIUM,TOTAL_WAR_CHARGES,EXCESS_PREMIUM,POLICY_FEE,TOTAL_PREMIUM_LOCAL,FOREIGN_CURRENCY,TOTAL_PREMIUM_FOREIGN,COMMISSION,TRANSIT_FROM_CITY,TRANSIT_FROM_COUNTRY,FINAL_DEST_CITY,FINAL_DESTINATION_COUNTRY,DEBIT_NOTE_NO,REFERRAL,REFERAL_STATUS,STATUS,INTEGRATION_STATUS,INTEGRATION_ERROR from table(AdminReports(?,?,?)) where Product_id=?";
		System.out.println("===========>"+sql);
		System.out.println("===========>"+startDate);
		System.out.println("===========>"+endDate);
		System.out.println("===========>"+type);
		System.out.println("===========>"+productId);
		result = this.mytemplate.queryForList(sql,new Object[]{startDate,endDate,type,productId});
		return result;
	}
}// Class