package com.maan.admin.DAO;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Map;

import org.apache.logging.log4j.Logger;

import proj.date.DateFunction;

import com.maan.Home.DataManipualtion.HomeValidationBean;
//import com.maan.Office.DAO.OfficeShieldBean;
import com.maan.common.LogUtil;
import com.maan.common.error.ErrorInfo;
import com.maan.common.exception.BaseException;
import com.maan.services.util.runner;

public class AdminPortBean extends ErrorInfo
{
	private static final Logger logger = LogUtil.getLogger(AdminPortBean.class);
    private String dobDay		      = "";
    private String dobMonth		      = "";
    private String dobYear		      = "";
    private String dobDay1			  = "";
    private String dobMonth1		  = "";
    private String dobYear1		      = "";
    private String rep			      = "";
    private String data1			  = "";
    private String data2			  = "";
	private String travel 			  = "";
	private String[][] brokerLoginIds = new String[0][0];
	private transient String query = "";
	final private transient HomeValidationBean homeValid = new HomeValidationBean();
	final private transient AdminCommonBean ACB = new AdminCommonBean();

	final private transient static String ENTER = "- Enter";
	final private transient static String INVALID = "Invalid";
	final private transient static String EXIT ="- Exit";
	final private transient static String BROKER = "Broker";
	final private transient static String ALL = "all";
	final private transient static String TOTALuSERS = "totalUsers";
	final private transient static String ROYAL = "royal";
	final private transient static String LOGIN = "login";
	final private transient static String LOGINID = "Login_Id";
	final private transient static String POLICYNO ="Policy_No";
	final private transient static String PREMIUM = "Premium";
	final private transient static String COMMISSION = "commsion";
	final private transient static String QUOTES = "Quotes";
	final private transient static String REFERRAL = "Referral";
	final private transient static String LAPSED = "Lapsed";
	final private transient static String USER = "User";
	final private transient static String BRSTAR = "<br>*";
	final private transient static char SINGLECOMMA = ',';

    public void setDobDay(final String dobDay) {
        this.dobDay=dobDay;
    }
    public void setDobMonth(final String dobMonth) {
        this.dobMonth=dobMonth;
    }
    public void setDobYear(final String dobYear) {
        this.dobYear=dobYear;
    }
    public void setRep(final String rep) {
        this.rep=rep;
    }
    public void setDobDay1(final String dobDay1) {
        this.dobDay1=dobDay1;
    }
    public void setDobMonth1(final String dobMonth1) {
        this.dobMonth1=dobMonth1;
    }
    public void setDobYear1(final String dobYear1) {
        this.dobYear1=dobYear1;
    }
    public void setData1(final String data1) {
        this.data1=data1;
    }
    public void setData2(final String data2) {
        this.data2=data2;
    }
    public void setTravel(final String travel)
	{
		this.travel = travel;
	}
    public void setBrokerLoginIds(final String[][] brokerLoginIds){
        this.brokerLoginIds=(String[][])brokerLoginIds.clone();
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
    public String getDobDay1() {
        return dobDay1;
    }
    public String getDobMonth1() {
        return dobMonth1;
    }
    public String getDobYear1() {
        return dobYear1;
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
	public String getTravel(){
		return this.travel;
	}
    public String[][] getBrokerLoginIds(){
        return (String[][])brokerLoginIds.clone();
    }

    public String checkDate(final String strDate) 
    {
    	logger.info("checkDate method()");
		logger.debug(ENTER);
        String returnVal="";
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy" , Locale.ENGLISH);
        dateFormat.setTimeZone(new java.util.SimpleTimeZone(14400000,"GMT"));            	
    	dateFormat.setLenient(false);
        final ParsePosition pos = new ParsePosition(0);
        final Date date = dateFormat.parse(strDate, pos);

        if ((date == null) || (pos.getErrorIndex() != -1)) {
            logger.info("Error: " + pos.getIndex());
            if (date == null) {
                returnVal= INVALID;
            }
            if (pos.getErrorIndex() != -1) {
            	returnVal= INVALID;
            }
            returnVal = INVALID;
        }
        logger.debug(EXIT);
		
        return returnVal;
    }

    public String validateTrashFields() throws BaseException,Exception 
    {
    	logger.info("validateTrashFields method()");
		logger.debug(ENTER);
        final StringBuffer error = new StringBuffer(1000);
        String values="";

        if("p".equals(rep)||"c".equals(rep) || "d".equals(rep) || "".equals(rep)) {
            	values=checkDate(dobDay+"/"+dobMonth+"/"+dobYear);
            	if(INVALID.equalsIgnoreCase(values)){
            		error.append(BRSTAR);
            		error.append(runner.getErrormsg("62"));
            	}
            	values=checkDate(dobDay1+"/"+dobMonth1+"/"+dobYear1);
            	if(INVALID.equalsIgnoreCase(values)){
            		error.append(BRSTAR);
            		error.append(runner.getErrormsg("63"));
            	}
            	else if(Integer.parseInt(dobYear1)<Integer.parseInt(dobYear)){
            		error.append(BRSTAR);
            		error.append(runner.getErrormsg("72"));
            	}
            	else if(Integer.parseInt(dobYear1)==Integer.parseInt(dobYear) ) 
            	{
            		if(Integer.parseInt(dobMonth1)<Integer.parseInt(dobMonth)){
            			error.append(BRSTAR);
            			error.append(runner.getErrormsg("72"));
            		}
            		else if(Integer.parseInt(dobMonth1)==Integer.parseInt(dobMonth)) {
            			if(Integer.parseInt(dobDay1)<Integer.parseInt(dobDay)){
            				error.append(BRSTAR);
            				error.append(runner.getErrormsg("72"));
            			}
            		}
            	}
            	final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy" , Locale.ENGLISH);
                dateFormat.setTimeZone(new java.util.SimpleTimeZone(14400000,"GMT"));            	
            	final String sVal = dateFormat.format(new java.util.Date());
            	final String enteredDate1=dobDay1+"/"+dobMonth1+"/"+dobYear1;
            	final String enteredDate=dobDay+"/"+dobMonth+"/"+dobYear;
            	long diff1=0;
            	long diff=0;

        		final DateFunction dbr=new DateFunction();
        		diff1=dbr.getDayDifference(dbr.getCalendar(sVal),dbr.getCalendar(enteredDate1));
        		diff=dbr.getDayDifference(dbr.getCalendar(sVal),dbr.getCalendar(enteredDate));

            	if(diff>0){
            		error.append(BRSTAR);
            		error.append(runner.getErrormsg("70"));
            	}
            	if(diff1>0){
            		error.append(BRSTAR);
            		error.append(runner.getErrormsg("71"));
            	}
        }
        logger.debug(EXIT);
		
        return error.toString();
    }

    /*public String validateFields() throws BaseException
	{
        String error="";
        String values="";
        try {

            if("d".equals(rep)) {
                values=checkDate(dobDay+"/"+dobMonth+"/"+dobYear);
                if(INVALID.equalsIgnoreCase(values))
                    error=error+"<br>*"+runner.getErrormsg("62");
                values=checkDate(dobDay1+"/"+dobMonth1+"/"+dobYear1);
                if(INVALID.equalsIgnoreCase(values))
                    error=error+"<br>*"+runner.getErrormsg("63");
                else
                    if(Integer.parseInt(dobYear1)<Integer.parseInt(dobYear))
                        error=error+"<br>*"+runner.getErrormsg("72");
                    else if(Integer.parseInt(dobYear1)==Integer.parseInt(dobYear)) {

                    if(Integer.parseInt(dobMonth1)<Integer.parseInt(dobMonth))
                        error=error+"<br>*"+runner.getErrormsg("72");
                    else if(Integer.parseInt(dobMonth1)==Integer.parseInt(dobMonth)) {
                        if(Integer.parseInt(dobDay1)<Integer.parseInt(dobDay))
                            error=error+"<br>*"+runner.getErrormsg("72");
                    }
                    }

                final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy" , Locale.ENGLISH);
		        dateFormat.setTimeZone(new java.util.SimpleTimeZone(14400000,"GMT"));            	
		    	String s = dateFormat.format(new java.util.Date());
                String enteredDate1=dobDay1+"/"+dobMonth1+"/"+dobYear1;
                String enteredDate=dobDay+"/"+dobMonth+"/"+dobYear;
                long diff1=0;
                long diff=0;

                try
				{
					 s = runner.singleSelection("select to_char(sysdate+8/24,'dd/MM/YYYY') from dual");
					DateFunction dbr=new DateFunction();
					if(s.length() > 0)
					{
						diff1 = dbr.getDayDifference(dbr.getCalendar(s),dbr.getCalendar(enteredDate1));
						diff = dbr.getDayDifference(dbr.getCalendar(s),dbr.getCalendar(enteredDate));
					}
                }
				catch(Exception e) {
                }
                if(diff>0)
                    error=error+"<br>*"+runner.getErrormsg("70");
                if(diff1>0)
                    error=error+"<br>*"+runner.getErrormsg("71");
            }
        } catch(Exception e) {
            System.out.println("Exception in "+e.toString());
        } finally {
            try {
                if(con!=null)
                    con.close();
            } catch(Exception e) {
                System.out.println("ERROR "+e.toString());
            }
        }
        return error;
    }*/

    public String validateProFields() throws BaseException
	{
    	logger.info("validateProFields method()");
		logger.debug(ENTER);
        String error="";
        String values="";
        try {
            
            values=checkDate(dobDay+"/"+dobMonth+"/"+dobYear);
            if("Invalid".equalsIgnoreCase(values))
                error=error+"<br>*"+runner.getErrormsg("62");
            values=checkDate(dobDay1+"/"+dobMonth1+"/"+dobYear1);
            if("Invalid".equalsIgnoreCase(values))
                error=error+"<br>*"+runner.getErrormsg("63");
            else
                if(Integer.parseInt(dobYear1)<Integer.parseInt(dobYear))
                    error=error+"<br>*"+runner.getErrormsg("72");
                else if(Integer.parseInt(dobYear1)==Integer.parseInt(dobYear)) {
                
                if(Integer.parseInt(dobMonth1)<Integer.parseInt(dobMonth))
                    error=error+"<br>*"+runner.getErrormsg("72");
                else if(Integer.parseInt(dobMonth1)==Integer.parseInt(dobMonth)) {
                    if(Integer.parseInt(dobDay1)<Integer.parseInt(dobDay))
                        error=error+"<br>*"+runner.getErrormsg("72");
                }
                }
            
            final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy" , Locale.ENGLISH);
            dateFormat.setTimeZone(new java.util.SimpleTimeZone(14400000,"GMT"));            	
        	 String s = dateFormat.format(new java.util.Date());
            String enteredDate=dobDay+"/"+dobMonth+"/"+dobYear;
            long diff=0;
            String temp = "sysdate";
    		//temp = runner.getSysdate("01");
    		
            try {
            	s = runner.singleSelection("select to_char("+temp+",'dd/MM/YYYY') from dual");
                DateFunction dbr=new DateFunction();
                diff=dbr.getDayDifference(dbr.getCalendar(s),dbr.getCalendar(enteredDate));
            } catch(Exception e) {
            }
            if(diff>0){
                error=error+"<br>*"+runner.getErrormsg("70");
            }
        } catch(Exception e) {
            System.out.println("Exception in "+e.toString());
        } 
        return error;
    }

    public HashMap getProductHashMap(final String loginBranch) throws BaseException
    {
    	logger.info("getProductHashMap method()");
		logger.debug(ENTER);
        final Map pnameMap = new HashMap();
    	final String args[] = {loginBranch};
		final String pnames[][]  = runner.multipleSelection("select product_name,remarks from product_master where company_id not in('1','2') and branch_code=?",args);
		for(int i=0;i<pnames.length;i++){
			pnameMap.put(pnames[i][1],pnames[i][0]);
		}
		logger.debug(EXIT);
		
		return (HashMap)pnameMap;
    }

    public String getLoginList(final String userType,final String broktype,final String syntax) throws BaseException
    {
    	logger.info("getLoginList method()");
		logger.debug(ENTER);
		String loginList = "";
		final String[][] values = ACB.getBrokerUserPersonalInfo(userType,broktype,syntax);
		loginList =homeValid.removeLastChar(homeValid.getStringFromArray(values,1),SINGLECOMMA);
		logger.debug(EXIT);
		
		return loginList;
    }

	//This is for Admin Side - policy wise reports
	public HashMap reportCheckingValues(final String data1,final String data2,final String broktype,final  String pid,final String loginBranch, final String brokerCodes,final String adminPids) throws BaseException
	{
		logger.info("reportCheckingValues method()");
		logger.debug(ENTER);
        final Map totalUsers = new HashMap();
		String produc_ids = "";
		String loginList = "";
        String fullTravelIds = "";
		final HashMap pnameMap = getProductHashMap(loginBranch);
		final String syntax = ACB.getBrokerCodesQuery(brokerCodes,loginBranch,broktype);
		if(ALL.equalsIgnoreCase(pid)){
			produc_ids = ACB.getAllHomeTravelPids(adminPids,loginBranch);
		}
		else{
			fullTravelIds = ACB.getAllTravelPids(pid,loginBranch);
			if(fullTravelIds.length()>0){
				produc_ids = fullTravelIds;
			}
			else{
				produc_ids = pid;
			}
		}

        final String userType =  ACB.getUserTypeInformation(broktype);
        loginList = getLoginList( userType,  broktype,  syntax);

        final String startdate=data1;
        final String enddate=data2;
        int pVal=0;
        int totalLen = 0;
        String returnval[][] = null;
	    if(loginList.length()>0)
	    {
            	returnval=new String[0][0];
            	query = "select a.policy_no,to_char(a.inception_date,,'dd/mm/yyyy'),a.OVERALL_PREMIUM,a.remarks,a.login_id,a.quote_no,a.debit_note_no,nvl(b.company_name,b.first_name),a.login_id,a.COMMISSION,nvl(a.PAYMENT_MODE,'Nil'),to_char(a.EFFECTIVE_DATE,'dd/mm/yyyy'),to_char(a.EXPIRY_DATE,'dd/mm/yyyy'),pm.product_description,nvl(pm.broker_id,'Home') from home_position_master a,personal_info b,home_product_master pm where a.inception_date between convert(date,?,'dd/mm/yyyy') and DATEADD(day, +1, convert(date,?,'dd/mm/yyyy')) and a.login_id in ("+loginList+") and a.status='P' and a.PRODUCT_ID in ("+produc_ids+") and a.DEBIT_NOTE_NO is not null and a.customer_id=b.customer_id and pm.product_id = a.product_id  order by a.inception_date desc";
				final String args1[] = {startdate,enddate};
                returnval=runner.multipleSelection(query,args1);
				if(returnval.length>0){
					totalLen = totalLen+returnval.length;
				}
        }
        String totalBrokerUsers[][]=new String[totalLen][15];
        if(loginList.length()>0){
            returnval=new String[0][0];
            query="select a.policy_no,to_char(a.inception_date,'dd/mm/yyyy'),a.OVERALL_PREMIUM,a.remarks,a.login_id,a.quote_no,a.debit_note_no,nvl(b.company_name,b.first_name),a.login_id,a.COMMISSION,nvl(a.PAYMENT_MODE,'Nil'),to_char(a.EFFECTIVE_DATE,'dd/mm/yyyy'),to_char(a.EXPIRY_DATE,'dd/mm/yyyy'),pm.product_description,nvl(pm.broker_id,'Home') from home_position_master a,personal_info b,home_product_master pm where a.inception_date between convert(date,?,'dd/mm/yyyy') and DATEADD(day, +1, convert(date,?,'dd/mm/yyyy')) and a.login_id in ("+loginList+") and a.status='P' and  a.PRODUCT_ID in ("+produc_ids+") and a.DEBIT_NOTE_NO is not null and a.customer_id=b.customer_id and pm.product_id = a.product_id  order by a.inception_date desc";
			final String args1[] = {startdate,enddate};
			returnval=runner.multipleSelection(query,args1);
            if(returnval.length>0) 
            {
                for(int t=0;t<returnval.length;t++)
                {
                    totalBrokerUsers[pVal][0]  = returnval[t][0];
                    totalBrokerUsers[pVal][1]  = returnval[t][1];
                    totalBrokerUsers[pVal][2]  = returnval[t][2];
                    totalBrokerUsers[pVal][3]  = returnval[t][3];
                    totalBrokerUsers[pVal][4]  = returnval[t][4];
                    totalBrokerUsers[pVal][5]  = returnval[t][5];
                    totalBrokerUsers[pVal][6]  = returnval[t][6];
                    totalBrokerUsers[pVal][7]  = returnval[t][7];
                    totalBrokerUsers[pVal][8]  = returnval[t][8];
                    totalBrokerUsers[pVal][9]  = returnval[t][9];
                    totalBrokerUsers[pVal][10] = returnval[t][10];
                    totalBrokerUsers[pVal][11] = returnval[t][11];
                    totalBrokerUsers[pVal][12] = returnval[t][12];
					String schemeName = "";
					if("Home".equalsIgnoreCase(returnval[t][14])){
						schemeName = returnval[t][13];
					}
					else{
						schemeName = (String)pnameMap.get(returnval[t][14]);
					}
					totalBrokerUsers[pVal][13] = schemeName;
					totalBrokerUsers[pVal][14] = returnval[t][14];
					pVal++;
                }
            }
        }
        totalUsers.put(TOTALuSERS,totalBrokerUsers);
		logger.debug(EXIT);
		
        return (HashMap)totalUsers;
	}

	//This is for Admin Side - policy wise reports for all brokers
	public String getBroComName(final String login) throws BaseException
	{
		logger.info("getBroComName method()");
		logger.debug(ENTER);
		final String args[] = {login};
		final String res= runner.singleSelection("select nvl(company_name,'') from broker_company_master where agency_code=(select oa_code from login_master where login_id=?)",args);
		logger.debug(EXIT);
		
		return res;
	}

	public String getLoginListAll(final String syntax) throws BaseException
	{
		logger.info("getLoginListAll method()");
		logger.debug(ENTER);
        String loginList = "";
		final String[][] values=runner.multipleSelection("select application_id,login_id,agency_code,oa_Code,first_name from personal_info where oa_code in("+syntax+") and login_id!='NONE' and application_id in ('2','3')");
		loginList = homeValid.removeLastChar(homeValid.getStringFromArray(values,1),SINGLECOMMA);
		logger.debug(EXIT);
		
		return loginList;
	}

	public HashMap reportCheckingValuesAll(final String data1,final String data2,String broktype,final String pid,final String loginBranch,final String brokerCodes,final String adminPids) throws BaseException
	{
		logger.info("reportCheckingValuesAll method()");
		logger.debug(ENTER);
        final Map totalUsers = new HashMap();
		String produc_ids = "";
		String fullTravelIds = "";
		String loginList = "";
		final HashMap pnameMap = getProductHashMap(loginBranch);
		broktype = getAllBrokersLogin(loginBranch,brokerCodes,pid);
		final String syntax = "select agency_code from broker_company_master where branch_code in("+loginBranch+") and agency_code in(select oa_code from login_master where login_id in("+broktype+"))";
		if(ALL.equalsIgnoreCase(pid)){
				produc_ids = ACB.getAllHomeTravelPids(adminPids,loginBranch);
		}
		else{
			fullTravelIds = ACB.getAllTravelPids(pid,loginBranch);
			if(fullTravelIds.length()>0){
				produc_ids = fullTravelIds;
			}
			else{
				produc_ids = pid;
			}
		}
		loginList = getLoginListAll(syntax);

        final String startdate=data1;
        final String enddate=data2;
        int pVal=0;
        int totalLen = 0;
        String returnval[][] = null;

	    if(loginList.length()>0)
	    {
            returnval=new String[0][0];
            query="select a.policy_no,to_char(a.inception_date,'dd/mm/yyyy'),a.OVERALL_PREMIUM,a.remarks,a.login_id,a.quote_no,a.debit_note_no,nvl(b.company_name,b.first_name),a.login_id,a.COMMISSION,nvl(a.PAYMENT_MODE,'Nil'),to_char(a.EFFECTIVE_DATE,'dd/mm/yyyy'),to_char(a.EXPIRY_DATE,'dd/mm/yyyy'),pm.product_description,nvl(pm.broker_id,'Home') from home_position_master a,personal_info b,home_product_master pm where a.inception_date between convert(date,?,'dd/mm/yyyy') and DATEADD(day, +1, convert(date,?,'dd/mm/yyyy')) and a.login_id in ("+loginList+") and a.status='P' and a.PRODUCT_ID in ("+produc_ids+") and a.DEBIT_NOTE_NO is not null and a.customer_id=b.customer_id and pm.product_id = a.product_id order by CONVERT(DATE,a.inception_date,'dd/mm/yyyy') desc";
			final String args1[] = {startdate,enddate};
			returnval=runner.multipleSelection(query,args1);
            if(returnval.length>0){
               totalLen = totalLen+returnval.length;
            }
        }
        String totalBrokerUsers[][]=new String[totalLen][15];

   	    if(loginList.length()>0)
   	    {
            returnval=new String[0][0];
            query="select a.policy_no,to_char(a.inception_date,'dd/mm/yyyy'),a.OVERALL_PREMIUM,a.remarks,a.login_id,a.quote_no,a.debit_note_no,nvl(b.company_name,b.first_name),a.login_id,a.COMMISSION,nvl(a.PAYMENT_MODE,'Nil'),to_char(a.EFFECTIVE_DATE,'dd/mm/yyyy'),to_char(a.EXPIRY_DATE,'dd/mm/yyyy'),pm.product_description,nvl(pm.broker_id,'Home') from home_position_master a,personal_info b,home_product_master pm where a.inception_date between convert(date,?,'dd/mm/yyyy') and DATEADD(day, +1, convert(date,?,'dd/mm/yyyy')) and a.login_id in ("+loginList+") and a.status='P' and  a.PRODUCT_ID in ("+produc_ids+") and a.DEBIT_NOTE_NO is not null and a.customer_id=b.customer_id and pm.product_id = a.product_id order by CONVERT(DATE,a.inception_date,'dd/mm/yyyy') desc";
            final String args1[] = {startdate,enddate};
            returnval=runner.multipleSelection(query,args1);
            if(returnval.length>0) 
            {
                for(int t=0;t<returnval.length;t++) {
                    totalBrokerUsers[pVal][0] = returnval[t][0];
                    totalBrokerUsers[pVal][1] = returnval[t][1];
                    totalBrokerUsers[pVal][2] = returnval[t][2];
                    totalBrokerUsers[pVal][3] = returnval[t][3];
                    totalBrokerUsers[pVal][4] = returnval[t][4];
                    totalBrokerUsers[pVal][5] = returnval[t][5];
                    totalBrokerUsers[pVal][6] = returnval[t][6];
                    totalBrokerUsers[pVal][7] = returnval[t][7];
                    totalBrokerUsers[pVal][8] = returnval[t][8];
                    totalBrokerUsers[pVal][9] = returnval[t][9];
                    totalBrokerUsers[pVal][10] = returnval[t][10];
                    totalBrokerUsers[pVal][11] = returnval[t][11];
                    totalBrokerUsers[pVal][12] = returnval[t][12];
					String schemeName = "";
					if("Home".equalsIgnoreCase(returnval[t][14])){
						schemeName = returnval[t][13];
					}
					else{
						schemeName = (String)pnameMap.get(returnval[t][14]);
					}
					totalBrokerUsers[pVal][13] = schemeName;
					totalBrokerUsers[pVal][14] = returnval[t][14];
					pVal++;
                }
            }
        }
        totalUsers.put(TOTALuSERS,totalBrokerUsers);
        logger.debug(EXIT);
		
		return (HashMap)totalUsers;
	}

	public String getAllBrokersLogin(final String branchCode ,final String brokerCodes,final String proID) throws BaseException
	{
		logger.info("getAllBrokersLogin method()");
		logger.debug(ENTER);
        String[][] getAllBrokers = new String[0][0];
        String pids="";
        String addeddata="";
        String oacode="";
		String[][] homePids = new String[0][0];
		final String syntax = ACB.getAdminBrokerCodesQuery(brokerCodes,branchCode);

		if(proID.length()>0 && !"All".equalsIgnoreCase(proID)){
			homePids = getProducts(branchCode,proID);
		}
		else{
			homePids = getProducts(branchCode,ROYAL);
		}
		pids = homeValid.removeLastChar(homeValid.getStringFromArray(homePids),SINGLECOMMA);
		addeddata = "product_id in("+pids+")";
		
		getAllBrokers=runner.multipleSelection("select l.oa_code,l.login_id from login_master l,broker_company_master b where l.oa_code=b.agency_code and l.login_id in(select distinct login_id from personal_info where login_id in(select login_id from login_master where USERTYPE='Broker' and agency_code in(select agency_code from login_user_details where "+addeddata+" and agency_code in (select agency_code from login_master where oa_code in(select oa_code from login_master where USERTYPE='Broker')))) and login_id!='NONE' and application_id in ('2')) and l.login_id in(select login_id from login_master where oa_code in("+syntax+")) order by b.company_name");
		oacode = homeValid.removeLastChar(homeValid.getStringFromArray(getAllBrokers,1),SINGLECOMMA);
		logger.debug(EXIT);
		
        return oacode;
    }
	//This is Admin side quote wise Reports
	public HashMap reportcheckingvaluesForQuote(final String data1,final String data2,final String broktype,final String pid,final String loginBra,final String brokerCodes,final String adminPids) throws BaseException
	{
		logger.info("reportcheckingvaluesForQuote method()");
		logger.debug(ENTER);

        String values[][]    = new String[0][0];
        final Map totalUsers   = new HashMap();
		String produc_ids    = "";
        String fullTravelIds = "";
		
		final String syntax = ACB.getBrokerCodesQuery(brokerCodes,loginBra,broktype);

		if(ALL.equalsIgnoreCase(pid)){
			produc_ids = ACB.getAllHomeTravelPids(adminPids,loginBra);
		}
		else
		{
			fullTravelIds = ACB.getAllTravelPids(pid,loginBra);
			if(fullTravelIds.length()>0){
				produc_ids = fullTravelIds;
			}
			else{
				produc_ids = pid;
			}
		}

        final String userType = ACB.getUserTypeInformation(broktype);
        values = ACB.getBrokerUserPersonalInfo(userType,broktype,syntax);

        final String startdate=data1;
        final String enddate=data2;
        int pVal=0;
        int totalLen = 0;

        for(int i=0;i<values.length;i++)
		{
            query="select a.quote_no,to_char(a.entry_date,'dd/mm/yyyy'),a.OVERALL_PREMIUM,DATEADD(day, +30, convert(date,a.entry_date,'dd/mm/yyyy')),(nvl(b.first_name,'')+' '+nvl(b.last_name,'')+' '+nvl(b.company_name,'')),a.login_id,a.COMMISSION from home_position_master a,personal_info b where a.entry_date between convert(date,?,'dd/mm/yyyy') and DATEADD(day, +1, convert(date,?,'dd/mm/yyyy')) and a.login_id in ('"+values[i][1]+"') and a.status='Y' and a.remarks is null and a.ADMIN_REFERRAL_STATUS is null and a.PRODUCT_ID in ("+produc_ids+") and a.customer_id=b.customer_id and a.quote_no is not null order by a.inception_date desc";
			final String args1[] = {startdate,enddate};
			final String[][] returnval = runner.multipleSelection(query,args1);

            if(returnval.length>0){
                totalLen = totalLen+returnval.length;
            }
        }

        String totalBrokerUsers[][]=new String[totalLen][7];
        for(int i=0;i<values.length;i++)
        {
            query = "select a.quote_no,to_char(a.entry_date,'dd/mm/yyyy'),a.OVERALL_PREMIUM,DATEADD(day, +30, convert(date,a.entry_date,'dd/mm/yyyy')),(nvl(b.first_name,'')+' '+nvl(b.last_name,'')+' '+nvl(b.company_name,'')),a.login_id,a.COMMISSION from home_position_master a,personal_info b where a.entry_date between convert(date,?,'dd/mm/yyyy') and DATEADD(day, +1, convert(date,?,'dd/mm/yyyy')) and a.login_id in ('"+values[i][1]+"') and a.status='Y' and a.remarks is null and a.ADMIN_REFERRAL_STATUS is null and a.PRODUCT_ID in ("+produc_ids+") and a.customer_id=b.customer_id and a.quote_no is not null order by a.inception_date desc";
			final String args1[] = {startdate,enddate};
           	final String[][] returnval = runner.multipleSelection(query,args1);

            if(returnval.length>0)
            {
                for(int t=0;t<returnval.length;t++)
                {
                    totalBrokerUsers[pVal][0] = returnval[t][0];
                    totalBrokerUsers[pVal][1] = returnval[t][1];
                    totalBrokerUsers[pVal][2] = returnval[t][2];
                    totalBrokerUsers[pVal][3] = returnval[t][3];
                    totalBrokerUsers[pVal][4] = returnval[t][4];
                    totalBrokerUsers[pVal][5] = returnval[t][5];
                    totalBrokerUsers[pVal][6] = returnval[t][6];
                    pVal++;
                }
            }
        }
        totalUsers.put(TOTALuSERS,totalBrokerUsers);
        logger.debug(EXIT);
        
        return (HashMap)totalUsers;
    }

	//This is for Admin side Refferal Quote wise Reports
	public HashMap reportCheckingValuesForReferral(final String data1,final String data2,final String broktype,final String pid,final String loginBranch,final String brokerCodes,final String adminPids) throws BaseException
	{
		logger.info("reportCheckingValuesForReferral method()");
		logger.debug(ENTER);

		//final OfficeShieldBean OSB = new OfficeShieldBean();
        String values[][]=new String[0][0];
        final Map totalUsers = new HashMap();
		String produc_ids = "";
		final Hashtable hashTravelPids = new Hashtable();
		String fullTravelIds = "";
		final String syntax = ACB.getBrokerCodesQuery(brokerCodes,loginBranch, broktype);
		
		if(pid.equalsIgnoreCase(ALL)){
			produc_ids = ACB.getAllHomeTravelPids(adminPids,loginBranch);
		}
		else
		{
			fullTravelIds = ACB.getAllTravelPids(pid,loginBranch);
			if(fullTravelIds.length()>0){
				produc_ids = fullTravelIds;
			}
			else{
				produc_ids = pid;
			}
		}
        final String userType = ACB.getUserTypeInformation(broktype);
        values = ACB.getBrokerUserPersonalInfo(userType,broktype,syntax);

        final String startdate = data1;
        final String enddate = data2;
        int pVal = 0;
        int totalLen =  0;

        for(int i=0;i<values.length;i++)
        {
            query = "select a.quote_no,convert(date,a.entry_date,'dd/mm/yyyy'),(nvl(b.first_name,'')+' '+nvl(b.last_name,'')+' '+nvl(b.company_name,'')),a.REFERRAL_DESCRIPTION,a.status,a.remarks from home_position_master a,personal_info b where a.entry_date between convert(date,?,'dd/mm/yyyy') and DATEADD(day, +1, convert(date,?,'dd/mm/yyyy')) and a.login_id in ('"+values[i][1]+"') and (a.status='R' or a.remarks is not null or a.ADMIN_REFERRAL_STATUS is not null) and a.status not in('P','D') and a.PRODUCT_ID in ("+produc_ids+")  and a.customer_id=b.customer_id order by a.inception_date desc";
			final String args1[] = {startdate,enddate};
            final String[][] returnval=runner.multipleSelection(query,args1);
            if(returnval.length>0){
               totalLen = totalLen+returnval.length;
            }
        }
        final String totalBrokerUsers[][]=new String[totalLen][7];
        String refQuote= "";

        for(int i=0;i<values.length;i++)
        {
             query = "select a.quote_no,convert(date,a.entry_date,'dd/mm/yyyy'),(nvl(b.first_name,'')+' '+nvl(b.last_name,'')+' '+nvl(b.company_name,'')),a.REFERRAL_DESCRIPTION,a.status,a.remarks from home_position_master a,personal_info b where a.entry_date between convert(date,?,'dd/mm/yyyy') and DATEADD(day, +1, convert(date,?,'dd/mm/yyyy')) and a.login_id in ('"+values[i][1]+"') and (a.status='R' or a.remarks is not null or a.ADMIN_REFERRAL_STATUS is not null) and a.status not in('P','D') and a.PRODUCT_ID in ("+produc_ids+")  and a.customer_id=b.customer_id order by a.inception_date desc";
			 final String args1[] = {startdate,enddate};
           	 final String[][] returnval =runner.multipleSelection(query,args1);

             if(returnval.length>0)
             {
                String Status = "";
                for(int t=0;t<returnval.length;t++)
                {
                    refQuote = refQuote+returnval[t][0]+",";
                    totalBrokerUsers[pVal][0] = returnval[t][0];
                    totalBrokerUsers[pVal][1] = returnval[t][1];
                    totalBrokerUsers[pVal][2] = returnval[t][2];
                    totalBrokerUsers[pVal][3] = returnval[t][3];
                    if("R".equalsIgnoreCase(returnval[t][4])){
                        Status = "Rejected";
                    }
                    else if(!"R".equalsIgnoreCase(returnval[t][4]) && returnval[t][5]!=null){
                        if("Admin".equalsIgnoreCase(returnval[t][5])){
                            Status = "Approved";
                        }
                        else if("Referal".equalsIgnoreCase(returnval[t][5])){
                            Status = "Pending";
                        }
                    }

                    totalBrokerUsers[pVal][4] = Status;
                    totalBrokerUsers[pVal][5] = returnval[t][5];
                    if(hashTravelPids.contains(pid)){
                        totalBrokerUsers[pVal][6] = returnval[t][3];
                    }
                    pVal++;
                }
            }
        }
		if(!hashTravelPids.contains(pid))
		{
            if(refQuote.length()>0){
				refQuote = refQuote.substring(0,refQuote.lastIndexOf(SINGLECOMMA));
            }
            String reftypesql = "";
			if(refQuote.length()>0){
				reftypesql = "select QUOTE_NO,REFERRAL_DESCRIPTION,product_id from HOME_POSITION_MASTER where quote_no in("+refQuote+") and REMARKS is not null";
			}
            String refTypes[][]=new String[0][0];
			if(reftypesql.length()>0){
				refTypes = runner.multipleSelection(reftypesql);
			}
            int rtVal=0;
            int flag=0;
            for(int r=0;r<totalBrokerUsers.length;r++) 
            {
                for(rtVal=0;rtVal<refTypes.length;rtVal++) 
                {
                	 if(totalBrokerUsers[r][0].equalsIgnoreCase(refTypes[rtVal][0]) && !"30".equalsIgnoreCase(refTypes[rtVal][2])) {
                         totalBrokerUsers[r][6] = refTypes[rtVal][1];
                         flag = 1;
                     }
                     else{
                     	//final String OFSReferralReason = OSB.getReasonForReferal(refTypes[rtVal][0],refTypes[rtVal][2],loginBranch);
                     	//totalBrokerUsers[r][6] = OFSReferralReason;
                     	flag = 1;
                     }
                }
                if(flag==0){
                    totalBrokerUsers[r][6] = "Admin Referral";
                }
            }
		 }
         totalUsers.put(TOTALuSERS,totalBrokerUsers);
         logger.debug(EXIT);
         
         return (HashMap)totalUsers;
    }

	//This is for Admin side Lapsed Quote wise Reports --- aprl30
	public HashMap reportCheckingValuesForLapsed(final String data1,final String data2,final String broktype,final String pid,final String loginBranch,final String brokerCodes,final String adminPids) throws BaseException
	{
		logger.info("reportCheckingValuesForLapsed method()");
		logger.debug(ENTER);
        final HashMap totalUsers = new HashMap();
		String produc_ids = "";
		String fullTravelIds = "";
		final String syntax = ACB.getBrokerCodesQuery( brokerCodes,  loginBranch,  broktype);
		
		if(ALL.equalsIgnoreCase(pid)){
			produc_ids = ACB.getAllHomeTravelPids(adminPids,loginBranch);
		}
		else{
			fullTravelIds = ACB.getAllTravelPids(pid,loginBranch);
			if(fullTravelIds.length()>0){
				produc_ids = fullTravelIds;
			}
			else{
				produc_ids = pid;
			}
		}

        final String userType = ACB.getUserTypeInformation(broktype);
        final String[][] values = ACB.getBrokerUserPersonalInfo(userType,broktype,syntax);

        final String startdate=data1;
        final String enddate=data2;
        int pVal=0;
        int totalLen = 0;

        for(int i=0;i<values.length;i++){

            query="select a.quote_no,to_char(a.LAPSED_DATE,'dd/mm/yyyy'),a.OVERALL_PREMIUM,to_char(a.EXPIRY_DATE,'dd/mm/yyyy'),(nvl(b.first_name,'')+' '+nvl(b.last_name,'')+' '+nvl(b.company_name,'')),a.login_id,a.LAPSED_REMARKS from home_position_master a,personal_info b where a.entry_date between CONVERT(DATE,?,'dd/mm/yyyy') and DATEADD(day, +1, convert(date,?,'dd/mm/yyyy')) and a.login_id in ('"+values[i][1]+"') and a.status='D'  and a.PRODUCT_ID in ("+produc_ids+")  and a.customer_id=b.customer_id order by a.inception_date desc";//and a.remarks is null and a.ADMIN_REFERRAL_STATUS is null
            final String args1[] = {startdate,enddate};
            final String[][] returnval = runner.multipleSelection(query,args1);
            if(returnval.length>0){
                totalLen = totalLen+returnval.length;
            }
        }

        String totalBrokerUsers[][]=new String[totalLen][7];
        for(int i=0;i<values.length;i++)
        {
            query="select a.quote_no,to_char(a.LAPSED_DATE,'dd/mm/yyyy'),a.OVERALL_PREMIUM,to_char(a.EXPIRY_DATE,'dd/mm/yyyy'),(nvl(b.first_name,'')+' '+nvl(b.last_name,'')+' '+nvl(b.company_name,'')),a.login_id,a.LAPSED_REMARKS from home_position_master a,personal_info b where a.entry_date between CONVERT(DATE,?,'dd/mm/yyyy') and DATEADD(day, +1, convert(date,?,'dd/mm/yyyy')) and a.login_id in ('"+values[i][1]+"') and a.status='D'  and a.PRODUCT_ID in ("+produc_ids+")  and a.customer_id=b.customer_id order by a.inception_date desc";//and a.remarks is null and a.ADMIN_REFERRAL_STATUS is null
            final String args1[] = {startdate,enddate};
            final String[][] returnval=runner.multipleSelection(query,args1);
            if(returnval.length>0)
            {
                for(int t=0;t<returnval.length;t++)
                {
                    totalBrokerUsers[pVal][0] = returnval[t][0];
                    totalBrokerUsers[pVal][1] = returnval[t][1];
                    totalBrokerUsers[pVal][2] = returnval[t][2];
                    totalBrokerUsers[pVal][3] = returnval[t][3];
                    totalBrokerUsers[pVal][4] = returnval[t][4];
                    totalBrokerUsers[pVal][5] = returnval[t][5];
                    totalBrokerUsers[pVal][6] = returnval[t][6];
                    pVal++;
                }
            }
        }
        totalUsers.put(TOTALuSERS,totalBrokerUsers);
        logger.debug(EXIT);
        
        return totalUsers;
    }

	public String[][] getProducts(String loginBranch,final String royal) throws BaseException
	{
    	logger.info("getProducts method()");
		logger.debug(ENTER);
		if(loginBranch.indexOf('\'')!=-1){
			loginBranch = loginBranch.replaceAll("'","");
		}
		final String args[] = {loginBranch};

		if("".equalsIgnoreCase(royal)  || !ROYAL.equalsIgnoreCase(royal)){
			query = "select pm.PRODUCT_ID,pm.PRODUCT_NAME,pm.COMPANY_ID from product_master pm, home_product_master hpm where hpm.PRODUCT_ID = pm.product_id  and pm.status='Y' and (hpm.BROKER_ID is null or hpm.BROKER_ID in ('Travel','Health','Motor') or hpm.BROKER_ID in ('TravelTypes') ) and hpm.status='Y' and pm.product_id in("+royal+") and pm.BRANCH_CODE=?";
		}
		else{
			query ="select pm.PRODUCT_ID,pm.PRODUCT_NAME,pm.COMPANY_ID from product_master pm, home_product_master hpm where hpm.PRODUCT_ID = pm.product_id and pm.status='Y' and (hpm.BROKER_ID is null or hpm.BROKER_ID in ('Travel','Health','Motor') or hpm.BROKER_ID in ('TravelTypes') ) and hpm.status='Y' and pm.BRANCH_CODE=?";
		}
		final String[][] products=runner.multipleSelection(query,args);
        logger.debug(EXIT);
        
		return products;
	}

	public String[][] getAllBrokers() throws BaseException
	{
		logger.info("getAllBrokers method()");
		logger.debug(ENTER);
		final String[][] homePids = getProducts("020",ROYAL);
		final String pids = homeValid.removeLastChar(homeValid.getStringFromArray(homePids),SINGLECOMMA);
		final String addeddata = "product_id in("+pids+")";
        query="select b.company_name,l.login_id from login_master l,broker_company_master b where l.oa_code=b.agency_code and l.login_id in(select distinct login_id from personal_info where login_id in(select login_id from login_master where USERTYPE='Broker' and agency_code in(select agency_code from login_user_details where "+addeddata+" and agency_code in (select agency_code from login_master where oa_code in(select oa_code from login_master where USERTYPE='Broker')))) and login_id!='NONE' and application_id in ('2')) order by b.company_name";
		final String[][] getAllBrokers=runner.multipleSelection(query);
        logger.debug(EXIT);
        

        return getAllBrokers;
    }

    public String[][] getUserBrokerInformation(final String login) throws BaseException
	{
    	logger.info("getUserBrokerInformation method()");
		logger.debug(ENTER);
        String[][] UserBroker = null;
        final String userType =  ACB.getUserTypeInformation(login);
        UserBroker = ACB.getBrokerUserPersonalInfo(userType,login);
        logger.debug(EXIT);
        
        return UserBroker;
    }

    public String[][] getUserBrokerInformation(final String login, final String pid) throws BaseException
	{
    	logger.info("getUserBrokerInformation method()");
		logger.debug(ENTER);
        String[][] UserBroker = new String[0][0];
        final Hashtable hashTravelPids = new Hashtable();
        String fullTravelIds = "";
        final String userType = ACB.getUserTypeInformation(login);

        //for Travell
		final String[][]travelPids = getTravelProductIds();
		for(int i=0;i<travelPids.length;i++){
			hashTravelPids.put("pids"+i,travelPids[i][0]);
            if(i< (travelPids.length-1)){
                    fullTravelIds = fullTravelIds +  travelPids[i][0]  +",";
            }
            else{
                    fullTravelIds =  fullTravelIds +travelPids[i][0];
            }
		}

        if(userType != null && BROKER.equalsIgnoreCase(userType)){
            if(!hashTravelPids.contains(pid)){
                query = "select distinct application_id,login_id,agency_code,oa_Code,first_name from personal_info where login_id in(select distinct login_id from home_position_master where PRODUCT_ID=? and login_id in(select login_id from login_master where agency_code in(select agency_code from login_user_details where product_id=? and agency_code in (select agency_code from login_master where oa_code=(select oa_code from login_master where login_id=?))))) and login_id!='NONE' and application_id in ('2','3')";
				final String args[] = {pid,pid,login};
				UserBroker=runner.multipleSelection(query,args);
			}
			if(hashTravelPids.contains(pid)){
				query = "select distinct application_id,login_id,agency_code,oa_Code,first_name from personal_info where login_id in(select distinct login_id from home_position_master where PRODUCT_ID in ("+fullTravelIds+") and login_id in(select login_id from login_master where agency_code in(select agency_code from login_user_details where product_id in ("+fullTravelIds+") and agency_code in (select agency_code from login_master where oa_code=(select oa_code from login_master where login_id=?))))) and login_id!='NONE' and application_id in ('2','3')";
                final String args[] = {login};
				UserBroker=runner.multipleSelection(query,args);
			}
        }
        else if(userType != null && USER.equalsIgnoreCase(userType)) {
        	query = "select application_id,login_id,agency_code,oa_code,first_name from personal_info where " +
        			"agency_code=(select agency_code from login_master where login_id=?) and login_id!='NONE' and " +
        			"application_id in ('3')";
			final String args[] = {login};
			UserBroker=runner.multipleSelection(query,args);
        }
        logger.debug(EXIT);
        
        return UserBroker;
    }

    public String getUserReportInformation(final String login,final String pid) throws BaseException
	{
    	logger.info("getUserReportInformation method()");
		logger.debug(ENTER);
        String result = "";
		final String args[] = {pid,login};
		final String[][] userReport = runner.multipleSelection("select QUOTE_NO from home_position_master where QUOTE_NO is not null and POLICY_NO is not null and PRODUCT_ID=? and login_id=?",args);
        if(userReport.length<=0){
        	 result = "NoRecordsFound";
        }
        else{
        	 result = "Records Found";
        }
        logger.debug(EXIT);
        
        return result;
    }

    public String[][] getReferralQuoteSearchInfo(final String qno,final String refStatus,final String brokerCodes,final String branchCode) throws BaseException
	{
    	logger.info("getReferralQuoteSearchInfo method()");
		logger.debug(ENTER);
        final String syntax = ACB.getAdminBrokerCodesQuery(brokerCodes,branchCode);
        if("Referal".equalsIgnoreCase(refStatus)){
            query = "select distinct hpm.LOGIN_ID,hpm.QUOTE_NO,hpm.REFERRAL_DESCRIPTION,hpm.PRODUCT_ID,hpm.SUMMARY_REMARKS,hpm.scheme_id,hpm.APPLICATION_NO from HOME_POSITION_MASTER hpm where hpm.QUOTE_NO like '%"+qno+"%' and hpm.REMARKS in ('"+refStatus+"') and lower(hpm.status) in('y') and hpm.login_id in(select login_id from login_master where oa_code in("+syntax+"))";
        }
		else if("Admin".equalsIgnoreCase(refStatus)){
			query = "select distinct hpm.LOGIN_ID,hpm.QUOTE_NO,hpm.REFERRAL_DESCRIPTION,hpm.PRODUCT_ID,hpm.SUMMARY_REMARKS,hpm.scheme_id,hpm.APPLICATION_NO from HOME_POSITION_MASTER hpm where hpm.QUOTE_NO like '%"+qno+"%' and hpm.REMARKS in('"+refStatus+"') and lower(hpm.status) in('y') and hpm.login_id in(select login_id from login_master where oa_code in("+syntax+"))";
        }
        final String[][] refQuotes = runner.multipleSelection(query);

        logger.debug(EXIT);
        
        return refQuotes;
    }

	public String[][] getHomeProductIds() throws BaseException
	{
		logger.info("getHomeProductIds method()");
		logger.debug(ENTER);
        final String[][] prodcutDetails1=runner.multipleSelection("select product_id from HOME_PRODUCT_MASTER where BROKER_ID is null order by product_id");
        logger.debug(EXIT);
        
        return prodcutDetails1;
    }

	public String[][] getTravelProductIds() throws BaseException
	{
		logger.info("getTravelProductIds method()");
		logger.debug(ENTER);
        final String[][] prodcutDetails1 =  runner.multipleSelection("select product_id from HOME_PRODUCT_MASTER where BROKER_ID in('TravelTypes','Travel') order by product_id");
		logger.debug(EXIT);
        
        return prodcutDetails1;
    }

	public String getTypes(final String pid) throws BaseException
	{
		logger.info("getTypes method()");
		logger.debug(ENTER);
		final String args[] = { pid};
        final String res=runner.singleSelection("select nvl(BROKER_ID,'Home') from HOME_PRODUCT_MASTER where  product_id=?",args);
		logger.debug(EXIT);
        
        return res;
    }

	/*** Branchwise Restriction & BrokerCodes Restriction ***/
	public String[][] getPortFolioHomeTravel(final String sdate,final String edate,String status,final String pid,final String branchCode,final String brokerCodes,final String adminPids) throws BaseException
	{
		logger.info("getPortFolioHomeTravel method()");
		logger.debug(ENTER);
        String[][] sVal = null;
	    String fullTravelIds = "";
		String produc_ids = "";
		final String syntax = ACB.getAdminBrokerCodesQuery(brokerCodes,branchCode);

		if(ALL.equalsIgnoreCase(pid)){
			produc_ids = ACB.getAllHomeTravelPids(adminPids,branchCode);
		}
		else{
			fullTravelIds = ACB.getAllTravelPids(pid,branchCode);
			if(fullTravelIds.length()>0){
				produc_ids = fullTravelIds;
			}
			else{
				produc_ids = pid;
			}
		}

		final String addeddata = "and a.product_id in("+produc_ids+")";
		if("D".equalsIgnoreCase(status)){
				query = "select to_char(a.EFFECTIVE_DATE,'dd/mm/yyyy'),count(a.EFFECTIVE_DATE) from home_position_master a, personal_info c where a.EFFECTIVE_DATE between convert(date,?,'dd/mm/yyyy') and DATEADD(day, +1, convert(date,?,'dd/mm/yyyy')) and a.customer_id = c.customer_id "+addeddata+" and (lower(a.status) in('i','c')) and  (a.policy_no is not null ) and a.login_id in(select login_id from login_master where oa_code in("+syntax+")) group by to_char(a.EFFECTIVE_DATE,'dd/mm/yyyy') order by to_char(a.EFFECTIVE_DATE,'dd/mm/yyyy') desc";
				final String args[] = {sdate,edate};
				sVal = runner.multipleSelection(query,args);
		}
		else{
			if("p".equalsIgnoreCase(status) || "f".equalsIgnoreCase(status))
			{
				if("f".equalsIgnoreCase(status)){
					status="p";
				}
				query = "select to_char(a.EFFECTIVE_DATE,'dd/mm/yyyy'),count(a.EFFECTIVE_DATE) from home_position_master a, personal_info c where a.EFFECTIVE_DATE between convert(date,?,'dd/mm/yyyy') and DATEADD(day, +1, convert(date,?,'dd/mm/yyyy')) and a.customer_id = c.customer_id "+addeddata+" and (lower(a.status) in('"+status.toLowerCase(Locale.ENGLISH)+"')) and (a.policy_no is not null ) and a.login_id in(select login_id from login_master where oa_code in("+syntax+")) group by to_char(a.EFFECTIVE_DATE,'dd/mm/yyyy') order by to_char(a.EFFECTIVE_DATE,'dd/mm/yyyy') desc";
				final String args[] = {sdate,edate};
				sVal = runner.multipleSelection(query,args);
			}
			else if(!"p".equalsIgnoreCase(status)){
				query="select to_char(a.EFFECTIVE_DATE,'dd/mm/yyyy'),count(a.EFFECTIVE_DATE) from home_position_master a, personal_info c where  a.EFFECTIVE_DATE between convert(date,?,'dd/mm/yyyy') and DATEADD(day, +1, convert(date,?,'dd/mm/yyyy')) and  a.customer_id = c.customer_id  "+addeddata+" and lower(a.status) in('y') and a.REMARKS is null and a.quote_no is not null and a.login_id in(select login_id from login_master where oa_code in("+syntax+")) group by to_char(a.EFFECTIVE_DATE,'dd/mm/yyyy') order by to_char(a.EFFECTIVE_DATE,'dd/mm/yyyy') desc";
				final String args[] ={sdate,edate};
				sVal = runner.multipleSelection(query,args);
			}
		}
		logger.debug(EXIT);
        
		return sVal;
    }

	public String[][] getPolicySearchDetails(final String pno,final String branchCode,final String brokerCodes) throws BaseException
	{
		logger.info("getPolicySearchDetails method()");
		logger.debug(ENTER);
        final String syntax = ACB.getAdminBrokerCodesQuery(brokerCodes,branchCode);
        final String args[] = {"%"+pno+"%"};
        query = "select  bro.COMPANY_NAME,pol.policy_no,pi.title,(pi.first_name+' '+pi.last_name+' '+pi.company_name),pi.last_name,pol.OVERALL_PREMIUM,pol.login_id,pol.product_id,pol.quote_no from HOME_POSITION_MASTER pol,personal_info pi,BROKER_COMPANY_MASTER bro where pol.customer_id=pi.customer_id and lower(pol.status) in('p') and bro.AGENCY_CODE in(select log.oa_code from LOGIN_MASTER log where log.login_id=pol.login_id) and pol.policy_no like ? and pol.login_id in(select login_id from login_master where oa_code in("+syntax+")) order by pol.policy_no";
        final String[][] policyDetails=runner.multipleSelection(query,args);
        logger.debug(EXIT);
        
        return policyDetails;
    }

    public String[][] getPolicySearchDetails1(final String pno,final String branchCode,final String brokerCodes) throws BaseException
	{
    	logger.info("getPolicySearchDetails1 method()");
		logger.debug(ENTER);
        final String syntax = ACB.getAdminBrokerCodesQuery(brokerCodes,branchCode);
		final String args[] = {pno};
		query = "select to_char(INCEPTION_DATE, 'dd/mm/yyyy'),PRODUCT_ID from HOME_POSITION_MASTER where POLICY_NO=? and login_id in(select login_id from login_master where oa_code in("+syntax+"))";
		final String[][] policyDetails1=runner.multipleSelection(query,args);
		logger.debug(EXIT);
		
        return policyDetails1;
    }

	//Used PortFolio_HomeTravel.jsp - branchwise restriction
	public HashMap getPortfolioForHomeTravel(final String eDate,final String pid,String status,final String branchCode,final String brokerCodes,final String adminPids) throws BaseException
	{
		logger.info("getPortfolioForHomeTravel method()");
		logger.debug(ENTER);
		final HashMap totalPortfolio = new HashMap();
        String[][] result = new String[0][0];
        String[][] result_mul = new String[0][0];
	    String fullTravelIds = "";
		String produc_ids = "";
		String msql = "";
		final String syntax = ACB.getAdminBrokerCodesQuery(brokerCodes,branchCode);
		
		if(pid.equalsIgnoreCase(ALL)){
			produc_ids = ACB.getAllHomeTravelPids(adminPids,branchCode);
		}
		else{
			fullTravelIds = ACB.getAllTravelPids(pid,branchCode);
			if(fullTravelIds.length()>0){
				produc_ids = fullTravelIds;
			}
			else{
				produc_ids = pid;
			}
		}
		final String addeddata = "and pol.product_id in("+produc_ids+")";
		if("D".equalsIgnoreCase(status)){
				query = "select  bro.COMPANY_NAME,pol.policy_no,pi.title,(nvl(pi.first_name,'')+' '+nvl(pi.last_name,'')+' '+nvl(pi.company_name,'')),pi.last_name,pol.OVERALL_PREMIUM,pol.login_id,pol.product_id,pol.QUOTE_NO from home_position_master pol,personal_info pi,BROKER_COMPANY_MASTER bro where (substring(cast(to_char(pol.EFFECTIVE_DATE,'dd/mm/yyyy') as varchar),1,10)='"+eDate+"') and pol.customer_id = pi.customer_id and lower(pol.status) in('i','c') "+addeddata+" and bro.AGENCY_CODE in(select log.oa_code from LOGIN_MASTER log where log.login_id=pol.login_id) and pol.policy_no is not null and nvl(pol.declaration_status,0) not in('LINKED') and pol.login_id in(select login_id from login_master where oa_code in("+syntax+")) order by pol.policy_no";
				msql = "select  bro.COMPANY_NAME,pol.customer_id,pol.policy_no,count(pol.policy_no),(nvl(pi.first_name,'')+' '+nvl(pi.last_name,'')+' '+nvl(pi.company_name,'')),sum(pol.OVERALL_PREMIUM),pol.login_id,pol.product_id,prd.product_name from HOME_POSITION_MASTER pol,personal_info pi,BROKER_COMPANY_MASTER bro,product_master prd where pol.product_id=prd.product_id and  (substring(cast(to_char(pol.EFFECTIVE_DATE,'dd/mm/yyyy') as varchar),1,10)='"+eDate+"') and pol.customer_id=pi.customer_id and lower(pol.status) in('i','c') and bro.AGENCY_CODE in(select log.oa_code from LOGIN_MASTER log where log.login_id=pol.login_id) "+addeddata+"  and pol.policy_no is not null and nvl(pol.declaration_status,0) in('LINKED') and pol.login_id in(select login_id from login_master where oa_code in("+syntax+")) group by bro.COMPANY_NAME,pol.customer_id,pol.policy_no,(nvl(pi.first_name,'')+' '+nvl(pi.last_name,'')+' '+nvl(pi.company_name,'')),pol.login_id,pol.product_id,prd.product_name  order by pol.policy_no";
				result = runner.multipleSelection(query);
				result_mul = runner.multipleSelection(msql);
		}
		else{
			if("p".equalsIgnoreCase(status) || "f".equalsIgnoreCase(status)){
				if("f".equalsIgnoreCase(status)){
					status="p";
				}
				query = "select  bro.COMPANY_NAME,pol.policy_no,pi.title,(nvl(pi.first_name,'')+' '+nvl(pi.last_name,'')+' '+nvl(pi.company_name,'')),pi.last_name,pol.OVERALL_PREMIUM,pol.login_id,pol.product_id,pol.QUOTE_NO from HOME_POSITION_MASTER pol,personal_info pi,BROKER_COMPANY_MASTER bro where (substring(cast(to_char(pol.EFFECTIVE_DATE,'dd/mm/yyyy') as varchar),1,10)='"+eDate+"') and pol.customer_id=pi.customer_id and lower(pol.status) in('"+status.toLowerCase(Locale.ENGLISH)+"') and bro.AGENCY_CODE in(select log.oa_code from LOGIN_MASTER log where log.login_id=pol.login_id) "+addeddata+" and pol.policy_no is not null and nvl(pol.declaration_status,0) not in('LINKED') and pol.login_id in(select login_id from login_master where oa_code in("+syntax+")) order by pol.policy_no";
				msql = "select  bro.COMPANY_NAME,pol.customer_id,pol.policy_no,count(pol.policy_no),(nvl(pi.first_name,'')+' '+nvl(pi.last_name,'')+' '+nvl(pi.company_name,'')),sum(pol.OVERALL_PREMIUM),pol.login_id,pol.product_id,prd.product_name from HOME_POSITION_MASTER pol,personal_info pi,BROKER_COMPANY_MASTER bro,product_master prd where pol.product_id=prd.product_id and  (substring(cast(to_char(pol.EFFECTIVE_DATE,'dd/mm/yyyy') as varchar),1,10)='"+eDate+"') and pol.customer_id=pi.customer_id and lower(pol.status) in('p') and bro.AGENCY_CODE in(select log.oa_code from LOGIN_MASTER log where log.login_id=pol.login_id) "+addeddata+"  and pol.policy_no is not null and nvl(pol.declaration_status,0) in('LINKED') and pol.login_id in(select login_id from login_master where oa_code in("+syntax+")) group by bro.COMPANY_NAME,pol.customer_id,pol.policy_no,(nvl(pi.first_name,'')+' '+nvl(pi.last_name,'')+' '+nvl(pi.company_name,'')),pol.login_id,pol.product_id,prd.product_name  order by pol.policy_no";
				result = runner.multipleSelection(query);
				result_mul = runner.multipleSelection(msql);
			}
			else if(!"p".equalsIgnoreCase(status)){
				query = "select  bro.COMPANY_NAME,pol.QUOTE_NO,pi.title,(nvl(pi.first_name,'')+' '+nvl(pi.last_name,'')+' '+nvl(pi.company_name,'')),pi.last_name,pol.OVERALL_PREMIUM,pol.login_id,pol.product_id,pol.QUOTE_NO from HOME_POSITION_MASTER pol,personal_info pi,BROKER_COMPANY_MASTER bro where (substring(cast(to_char(pol.EFFECTIVE_DATE,'dd/mm/yyyy') as varchar),1,10)='"+eDate+"') and pol.customer_id=pi.customer_id and lower(pol.status) in('y') and pol.REMARKS is null and bro.AGENCY_CODE in(select log.oa_code from LOGIN_MASTER log where log.login_id=pol.login_id) "+addeddata+" and pol.QUOTE_NO is not null and pol.login_id in(select login_id from login_master where oa_code in("+syntax+"))";
				result = runner.multipleSelection(query);
			}
		}
		totalPortfolio.put("Single",result);
		totalPortfolio.put("Multiple",result_mul);
		logger.debug(EXIT);
		
        return totalPortfolio;
	}

	public String getQuoteForPolicy(final String policyNo,final String pid) throws BaseException
	{
		logger.info("getQuoteForPolicy method()");
		logger.debug(ENTER);
		final String args[] = {policyNo};
	    final String quoteNo = runner.singleSelection("select nvl(quote_no,0) from Home_Position_Master where policy_no=?",args);
	    logger.debug(EXIT);
		
		return quoteNo;
    }

	public String[][] getExcessandSign(final String qno,final String branchCode) throws BaseException
	{
		logger.info("getExcessandSign method()");
		logger.debug(ENTER);
		final String args[] = {qno};
		query = "select EXCESS_PREMIUM,EXCESS_SIGN from HOME_POSITION_MASTER where QUOTE_NO=? and login_id in(select login_id from login_master where oa_code in(select agency_code from broker_company_master where branch_code in("+branchCode+")))";
		final String[][] value=runner.multipleSelection(query,args);
        logger.debug(EXIT);
		
        return value;
    }

	public String[][] getAllBrokers(final String branchCode) throws BaseException
	{
		logger.info("getAllBrokers method()");
		logger.debug(ENTER);
		final String[][] homePids = getProducts(branchCode,ROYAL);
		final String pids = homeValid.removeLastChar(homeValid.getStringFromArray(homePids),SINGLECOMMA);
		final String addeddata = "product_id in("+pids+")";
        query="select b.company_name,l.login_id from login_master l,broker_company_master b where l.oa_code=b.agency_code and l.login_id in(select distinct login_id from personal_info where login_id in(select login_id from login_master where USERTYPE='Broker' and agency_code in(select agency_code from login_user_details where "+addeddata+" and agency_code in (select agency_code from login_master where oa_code in(select oa_code from login_master where USERTYPE='Broker')))) and login_id!='NONE' and application_id in ('2')) and l.login_id in(select login_id from login_master where oa_code in(select agency_code from broker_company_master where branch_code in("+branchCode+"))) order by b.company_name";
        final String[][] getAllBrokers=runner.multipleSelection(query);
        logger.debug(EXIT);
		
        return getAllBrokers;
    }

	// Admin Report For Home & Travel BrokerCode Restriction Aug20 - finalhomereport1.jsp
	public HashMap brokerCheckingValues123(final String data1, final String data2, final String loginid, final String pid,final String branchCode,final String brokerCodes,final String adminPids) throws BaseException
	{
		logger.info("brokerCheckingValues123 method()");
		logger.debug(ENTER);
        final String startdate=data1;
        final String enddate=data2;
        String values[][]=new String[0][0];
		int len = 0;
		String produc_ids="";
		final Hashtable hashTravelPids = new Hashtable();
		String fullTravelIds = "";
		final Map brokerData=new HashMap();
        int pval=0;

        final String syntax = ACB.getAdminBrokerCodesQuery(brokerCodes,branchCode);

		if(ALL.equalsIgnoreCase(pid)){
			produc_ids = ACB.getAllHomeTravelPids(adminPids,branchCode);
		}
		else{
			fullTravelIds = ACB.getAllTravelPids(pid,branchCode);
			if(fullTravelIds.length()>0){
				produc_ids = fullTravelIds;
			}
			else{
				produc_ids = pid;
			}
		}
        if(ALL.equalsIgnoreCase(loginid)){
             if(hashTravelPids.contains(pid)){
             	query="select login_id,agency_code,oa_code,usertype from login_master where usertype='Broker' and oa_code in(select oa_code from login_master where login_id in(select login_id from home_position_master where login_id not in ('NONE','NON'))) and oa_code is not null and agency_code is not null  and login_id in(select login_id from login_master where oa_code in("+syntax+")) order by login_id";
             }
             else{
            	 query="select login_id,agency_code,oa_code,usertype from login_master where usertype='Broker' and oa_code in(select oa_code from login_master where login_id in(select login_id from Home_Position_Master where login_id not in ('NONE','NON'))) and oa_code is not null and agency_code is not null  and login_id in(select login_id from login_master where oa_code in("+syntax+")) order by login_id";
             }
        }
		else{
			 query="select login_id,agency_code,oa_code,usertype from login_master where oa_code in(select oa_code from login_master where login_id='"+loginid+"' and login_id not in ('NONE','NON')) and login_id not in ('NONE','NON') and oa_code is not null and agency_code is not null  and login_id in(select login_id from login_master where oa_code in("+syntax+")) order by login_id";

        }
        values=runner.multipleSelection(query);
        brokerLoginIds=values;
        setBrokerLoginIds(values);

        for(int i=0;i<values.length;i++)
        {
        	//For Policy Report
        	query="select count(login_id),count(POLICY_NO),sum(round(OVERALL_PREMIUM,0)),sum(round(COMMISSION,0)) from home_position_master where login_id in(select login_id from login_master where oa_code=(select oa_code from login_master where login_id=?) and login_id!='NONE') and inception_date between convert(date,?,'dd/mm/yyyy') and DATEADD(day, +1, convert(date,?,'dd/mm/yyyy')) and PRODUCT_ID in ("+produc_ids+") and DEBIT_NOTE_NO is not null and status='P' and login_id in(select login_id from login_master where oa_code in("+syntax+"))";
        	final String args[] = {values[i][0],startdate,enddate};
            final String[][] returnval=runner.multipleSelection(query,args);

            //For Quotes Report
            query = "select count(login_id),count(QUOTE_NO) from home_position_master where login_id in(select login_id from login_master where oa_code=(select oa_code from login_master where login_id=?) and login_id!='NONE' ) and entry_date between convert(date,?,'dd/mm/yyyy') and DATEADD(day, +1, convert(date,?,'dd/mm/yyyy')) and PRODUCT_ID in ("+produc_ids+") and QUOTE_NO is not null and lower(status)='y' and REMARKS is null and ADMIN_REFERRAL_STATUS is null and login_id in(select login_id from login_master where oa_code in("+syntax+"))";
			final String args1[] = {values[i][0],startdate,enddate};
			final String[][] quoteRes = runner.multipleSelection(query,args1);

            //For Referral Reports
            query = "select count(login_id),count(REMARKS) from home_position_master where login_id in(select login_id from login_master where oa_code=(select oa_code from login_master where login_id=?) and login_id!='NONE' ) and entry_date between convert(date,?,'dd/mm/yyyy') and DATEADD(day, +1, convert(date,?,'dd/mm/yyyy')) and PRODUCT_ID in ("+produc_ids+") and QUOTE_NO is not null and (ADMIN_REFERRAL_STATUS is not null or REMARKS is not null) and status not in('P','D') and login_id in(select login_id from login_master where oa_code in("+syntax+"))";
			final String[] args2 = {values[i][0],startdate,enddate};
            final String[][]refRes = runner.multipleSelection(query,args2);

            //For Lapsed Reports
            query = "select count(login_id),count(status) from home_position_master where login_id in(select login_id from login_master where oa_code=(select oa_code from login_master where login_id=?) and login_id!='NONE' ) and entry_date between convert(date,?,'dd/mm/yyyy') and DATEADD(day, +1, convert(date,?,'dd/mm/yyyy')) and PRODUCT_ID in ("+produc_ids+") and QUOTE_NO is not null and lower(status)='d' and login_id in(select login_id from login_master where oa_code in("+syntax+"))";
            final String args3[] = {values[i][0],startdate,enddate};
            final String[][] labRes = runner.multipleSelection(query,args3);
            String refs = "0";
            String quos = "0";
            String labs = "0";
            if(!"0".equalsIgnoreCase(returnval[0][0]) || !"0".equalsIgnoreCase(quoteRes[0][0]) || !"0".equalsIgnoreCase(refRes[0][0]) || !"0".equalsIgnoreCase(labRes[0][0])) {
                brokerData.put("SNo"+pval,Integer.toString(pval));
                String pol = "0";
                String Premium = "0";
                String commsion = "0";
                if(returnval.length>0) {
                    //returnval[0][0]=returnval[0][0]!=null?returnval[0][0].equalsIgnoreCase("0")?"NIL":returnval[0][0]:"NIL";
                    pol=returnval[0][1] == null?"0":returnval[0][1];
                    Premium=returnval[0][2]==null?"0":returnval[0][2];
                    commsion=returnval[0][3]==null?"0":returnval[0][3];
                }
                if(quoteRes.length>0){
                    quos = quoteRes[0][1]==null?"0":quoteRes[0][1];
                }
                if(refRes.length>0){
                    refs = refRes[0][1]==null?"0":refRes[0][1];
                }
                if(labRes.length>0){
                    labs = labRes[0][1]==null?"0":labRes[0][1];
                }

                brokerData.put(LOGINID+pval,values[i][0]);
                brokerData.put(POLICYNO+pval,pol);
                brokerData.put(PREMIUM+pval,Premium);
                brokerData.put(COMMISSION+pval,commsion);
                brokerData.put(QUOTES+pval,quos);
                brokerData.put(REFERRAL+pval,refs);
                brokerData.put(LAPSED+pval,labs);
                pval++;
            }
        }
        len = pval;
        brokerData.put("Tsize",Integer.toString(len));

		/** Sorting  **/
		logger.info("Sorting...");
		for (int i=0;i<len-1;i++){
			for (int j=0;j<=len-1-i;j++){
				String policyCount1 = (String) brokerData.get(POLICYNO+j);
				String policyCount2 = (String) brokerData.get(POLICYNO+(j+1));
				final HashMap tempHash = new HashMap();
				policyCount1 = policyCount1 == null ? "0" : policyCount1;
				policyCount2 = policyCount2 == null ? "0" : policyCount2;
				logger.info("Sorting...1"+ policyCount1);
				logger.info("Sorting...2"+ policyCount2);
				if( Integer.parseInt(policyCount1) < Integer.parseInt(policyCount2) ){
					for(int s=0;s<1;s++){
						final String log = (String) brokerData.get(LOGINID+j);
						final String policy = (String) brokerData.get(POLICYNO+j);
						final String premium = (String) brokerData.get(PREMIUM+j);
						final String commission = (String) brokerData.get(COMMISSION+j);
						final String qno = (String) brokerData.get(QUOTES+j);
						final String ref = (String) brokerData.get(REFERRAL+j);
						final String laps = (String) brokerData.get(LAPSED+j);

						 tempHash.put(LOGINID+(s+1),log);
						 tempHash.put(POLICYNO+(s+1),policy);
						 tempHash.put(PREMIUM+(s+1),premium);
						 tempHash.put(COMMISSION+(s+1),commission);
						 tempHash.put(QUOTES+(s+1),qno);
						 tempHash.put(REFERRAL+(s+1),ref);
						 tempHash.put(LAPSED+(s+1),laps);
					}
					for(int s=0;s<1;s++){
						final String log = (String) brokerData.get(LOGINID+(j+1));
						final String policy = (String) brokerData.get(POLICYNO+(j+1));
						final String premium = (String) brokerData.get(PREMIUM+(j+1));
						final String commission = (String) brokerData.get(COMMISSION+(j+1));
						final String qno = (String) brokerData.get(QUOTES+(j+1));
						final String ref = (String) brokerData.get(REFERRAL+(j+1));
						final String laps = (String) brokerData.get(LAPSED+(j+1));

						 brokerData.put(LOGINID+j,log);
						 brokerData.put(POLICYNO+j,policy);
						 brokerData.put(PREMIUM+j,premium);
						 brokerData.put(COMMISSION+j,commission);
						 brokerData.put(QUOTES+j,qno);
						 brokerData.put(REFERRAL+j,ref);
						 brokerData.put(LAPSED+j,laps);
					}
					for(int s=0;s<1;s++){
						final String log = (String) tempHash.get(LOGINID+(s+1));
						final String policy = (String) tempHash.get(POLICYNO+(s+1));
						final String premium = (String) tempHash.get(PREMIUM+(s+1));
						final String commission = (String) tempHash.get(COMMISSION+(s+1));
						final String qno = (String) tempHash.get(QUOTES+(s+1));
						final String ref = (String) tempHash.get(REFERRAL+(s+1));
						final String laps = (String) tempHash.get(LAPSED+(s+1));

						 brokerData.put(LOGINID+(j+1),log);
						 brokerData.put(POLICYNO+(j+1),policy);
						 brokerData.put(PREMIUM+(j+1),premium);
						 brokerData.put(COMMISSION+(j+1),commission);
						 brokerData.put(QUOTES+(j+1),qno);
						 brokerData.put(REFERRAL+(j+1),ref);
						 brokerData.put(LAPSED+(j+1),laps);
					}
				} // If
			} // For J
		}// For I

		/** sorting **/
		logger.debug(EXIT);
		
        return (HashMap)brokerData;
    }

	// Admin Report For Home & Travel BrokerCode Restriction Aug20 - finalhomereport1.jsp
	public HashMap brokerCheckingValues12345(final String data1,final String data2,final String loginid,final String pid,final String branchCode,final String brokerCodes,final String adminPids) throws BaseException
	{
		logger.info("brokerCheckingValues12345 method()");
		logger.debug(ENTER);

        final String startdate=data1;
        final String enddate=data2;
		int len = 0;
		final HashMap Utype = new HashMap();
		String produc_ids = "";
        String fullTravelIds = "";
		final String syntax = ACB.getAdminBrokerCodesQuery(brokerCodes,branchCode);
		
		if(pid.equalsIgnoreCase(ALL)){
			produc_ids = ACB.getAllHomeTravelPids(adminPids,branchCode);
		}
		else{
			fullTravelIds = ACB.getAllTravelPids(pid,branchCode);
			if(fullTravelIds.length()>0){
				produc_ids = fullTravelIds;
			}
			else{
				produc_ids = pid;
			}
		}

        query = "select a.login_id, a.agency_code, a.oa_code, a.first_name, b.usertype from personal_info a,login_master b where A.APPLICATION_ID='2' AND  a.agency_code in(select agency_code from login_user_details where login_id=?) and a.oa_code = b.oa_code and a.agency_code = b.agency_code and a.login_id in(select login_id from login_master where oa_code in("+syntax+"))";
        final String args[] = {loginid};
        final String[][] values=runner.multipleSelection(query,args);
        brokerLoginIds=values;
		for(int i=0;i<values.length;i++){
			Utype.put(LOGIN+values[i][0],values[i][4]);
		}
        setBrokerLoginIds(values);

        Map brokerData=new HashMap();
        int pVal=0;
        String subqry = "";
        for(int i=0;i<values.length;i++){
        	subqry = "";
        	if(BROKER.equalsIgnoreCase((String)Utype.get(LOGIN+values[i][0]))){
        		subqry = "select login_id from personal_info where oa_code=(select oa_code from login_master where login_id='"+values[i][0]+"') and login_id!='NONE' and application_id in ('2','3') and login_id in(select login_id from login_master where oa_code in("+syntax+"))";
        	}
        	else  if(USER.equalsIgnoreCase((String)Utype.get(LOGIN+values[i][0]))){
                    subqry = "'"+values[i][0]+"'";
        	}

            //For Policy Reports
        	query = "select count(login_id),count(POLICY_NO),sum(round(OVERALL_PREMIUM,0)),sum(round(COMMISSION,0)) from home_position_master where login_id in("+subqry+") and inception_date between convert(date,?,'dd/mm/yyyy') and DATEADD(day, +1, convert(date,?,'dd/mm/yyyy')) and PRODUCT_ID in("+produc_ids+") and DEBIT_NOTE_NO is not null and status='P' and login_id in(select login_id from login_master where oa_code in("+syntax+"))";
        	final String args1[] = {startdate,enddate};
        	final String[][] returnval=runner.multipleSelection(query,args1);

            //For Quotes Report
            query = "select count(login_id),count(QUOTE_NO) from home_position_master where login_id in("+subqry+") and entry_date between convert(date,?,'dd/mm/yyyy') and DATEADD(day, +1, convert(date,?,'dd/mm/yyyy')) and  PRODUCT_ID in("+produc_ids+") and QUOTE_NO is not null and lower(status)='y' and REMARKS is null and ADMIN_REFERRAL_STATUS is null and login_id in(select login_id from login_master where oa_code in("+syntax+"))";
			final String args2[] = {startdate,enddate};
            final String[][] quoteRes = runner.multipleSelection(query,args2);

            //For Referral Reports
            query = "select count(login_id),count(REMARKS) from home_position_master where login_id in("+subqry+") and entry_date between convert(date,?,'dd/mm/yyyy') and DATEADD(day, +1, convert(date,?,'dd/mm/yyyy')) and PRODUCT_ID in("+produc_ids+") and QUOTE_NO is not null and (ADMIN_REFERRAL_STATUS is not null or REMARKS is not null) and status not in('P','D') and login_id in(select login_id from login_master where oa_code in("+syntax+"))";
            final String args3[] = {startdate,enddate};
            final String[][] refRes = runner.multipleSelection(query,args3);

            //For Lapsed Reports
            query = "select count(login_id),count(status) from home_position_master where login_id in("+subqry+") and entry_date between convert(date,?,'dd/mm/yyyy') and DATEADD(day, +1, convert(date,?,'dd/mm/yyyy')) and  PRODUCT_ID in("+produc_ids+") and QUOTE_NO is not null and lower(status)='d' and login_id in(select login_id from login_master where oa_code in("+syntax+"))";
			final String args4[] ={startdate,enddate};
            final String[][] labRes = runner.multipleSelection(query,args4);

            String refs = "0";
            String quos = "0";
            String labs = "0";
            if(!"0".equalsIgnoreCase(returnval[0][0])||!"0".equalsIgnoreCase(quoteRes[0][0])||!"0".equalsIgnoreCase(refRes[0][0])||!"0".equalsIgnoreCase(labRes[0][0]))
            {
                    brokerData.put("SNo"+pVal,Integer.toString(pVal));
                    String pol = "0";
                    String Premium = "0";
                    String commsion = "0";
                    if(returnval.length>0)
					{
                        pol=returnval[0][1]==null?"0":returnval[0][1];
                        Premium=returnval[0][2]==null?"0":returnval[0][2];
                        commsion=returnval[0][3]==null?"0":returnval[0][3];
                    }
                    if(quoteRes.length>0){
                        quos = quoteRes[0][1]==null?"0":quoteRes[0][1];
                    }
                    if(refRes.length>0){
                        refs = refRes[0][1]==null?"0":refRes[0][1];
                    }
                    if(labRes.length>0){
                        labs = labRes[0][1]==null?"0":labRes[0][1];
                    }
                    brokerData.put(LOGINID+pVal,values[i][0]);
                    brokerData.put(POLICYNO+pVal,pol);
                    brokerData.put(PREMIUM+pVal,Premium);
                    brokerData.put(COMMISSION+pVal,commsion);
                    brokerData.put(QUOTES+pVal,quos);
                    brokerData.put(REFERRAL+pVal,refs);
                    brokerData.put(LAPSED+pVal,labs);
                    pVal++;
                }
            }
            len = pVal;
            brokerData.put("Tsize",Integer.toString(len));

            /** Sorting  **/

			logger.info("Sorting...");
			for (int i=0;i<len-1;i++){
				for (int j=0;j<=len-1-i;j++){
					String policyCount1 = (String) brokerData.get(POLICYNO+j);
					String policyCount2 = (String) brokerData.get(POLICYNO+(j+1));
					final HashMap tempHash = new HashMap();
					policyCount1 = policyCount1 == null ? "0" : policyCount1;
					policyCount2 = policyCount2 == null ? "0" : policyCount2;
					logger.info("Sorting...1"+ policyCount1);
					logger.info("Sorting...2"+ policyCount2);
					if( Integer.parseInt(policyCount1) < Integer.parseInt(policyCount2) ){
						for(int s=0;s<1;s++){
							final String log = (String) brokerData.get(LOGINID+j);
							final String policy = (String) brokerData.get(POLICYNO+j);
							final String premium = (String) brokerData.get(PREMIUM+j);
							final String commission = (String) brokerData.get(COMMISSION+j);
							final String qno = (String) brokerData.get(QUOTES+j);
							final String ref = (String) brokerData.get(REFERRAL+j);
							final String laps = (String) brokerData.get(LAPSED+j);

							 tempHash.put(LOGINID+(s+1),log);
							 tempHash.put(POLICYNO+(s+1),policy);
							 tempHash.put(PREMIUM+(s+1),premium);
							 tempHash.put(COMMISSION+(s+1),commission);
							 tempHash.put(QUOTES+(s+1),qno);
							 tempHash.put(REFERRAL+(s+1),ref);
							 tempHash.put(LAPSED+(s+1),laps);
						}
						for(int s=0;s<1;s++){
							 final String log = (String) brokerData.get(LOGINID+(j+1));
							 final String policy = (String) brokerData.get(POLICYNO+(j+1));
							 final String premium = (String) brokerData.get(PREMIUM+(j+1));
							 final String commission = (String) brokerData.get(COMMISSION+(j+1));
							 final String qno = (String) brokerData.get(QUOTES+(j+1));
							 final String ref = (String) brokerData.get(REFERRAL+(j+1));
							 final String laps = (String) brokerData.get(LAPSED+(j+1));

							 brokerData.put(LOGINID+j,log);
							 brokerData.put(POLICYNO+j,policy);
							 brokerData.put(PREMIUM+j,premium);
							 brokerData.put(COMMISSION+j,commission);
							 brokerData.put(QUOTES+j,qno);
							 brokerData.put(REFERRAL+j,ref);
							 brokerData.put(LAPSED+j,laps);
						}
						for(int s=0;s<1;s++){
							final String log = (String) tempHash.get(LOGINID+(s+1));
							final String policy = (String) tempHash.get(POLICYNO+(s+1));
							final String premium = (String) tempHash.get(PREMIUM+(s+1));
							final String commission = (String) tempHash.get(COMMISSION+(s+1));
							final String qno = (String) tempHash.get(QUOTES+(s+1));
							final String ref = (String) tempHash.get(REFERRAL+(s+1));
							final String laps = (String) tempHash.get(LAPSED+(s+1));

							 brokerData.put(LOGINID+(j+1),log);
							 brokerData.put(POLICYNO+(j+1),policy);
							 brokerData.put(PREMIUM+(j+1),premium);
							 brokerData.put(COMMISSION+(j+1),commission);
							 brokerData.put(QUOTES+(j+1),qno);
							 brokerData.put(REFERRAL+(j+1),ref);
							 brokerData.put(LAPSED+(j+1),laps);
						}
					} // If
				} // For J
			}// For I
			/** sorting **/
			logger.debug(EXIT);
			
			return (HashMap)brokerData;
	}

	public void updatePolicyStatus(final String pno,final String login) throws BaseException
	{
		logger.info("updatePolicyStatus method()");
		logger.debug(ENTER);
		final String args[] = {login};
		runner.multipleUpdation("update Home_position_master set STATUS='C',cancelled_date=sysdate,cancelled_by=? where POLICY_NO in("+pno+")",args);
		logger.debug(EXIT);
		
	}

	public String getBrokerCodes(final String loginId) throws BaseException
	{
		logger.info("getBrokerCodes method()");
		logger.debug(ENTER);
		final String args[] = {loginId};
		String adminBrokerCodes = runner.singleSelection("select nvl(broker_codes,' ') from login_master where login_id=?",args);
		if(adminBrokerCodes!=null){
			adminBrokerCodes = adminBrokerCodes.trim();
		}
		logger.debug(EXIT);
		
		return adminBrokerCodes;
	}

	public String getLoginProIds(final String loginId) throws BaseException
	{
		logger.info("getLoginProIds method()");
		logger.debug(ENTER);
		String adminProIds= "";
		final String args[] = {loginId};
		adminProIds = runner.singleSelection("select nvl(product_id,' ') from login_master where login_id=?",args);
		if(adminProIds!=null){
			adminProIds = adminProIds.trim();
		}
		logger.debug(EXIT);
		
		return adminProIds;
	}

    //HomePolicyReport.jsp BrokerCodes Restriction
	public String[][] getAllBrokers(final String branchCode,final String brokerCodes,final String proID) throws BaseException
	{
		logger.info("getAllBrokers method()");
		logger.debug(ENTER);
		String[][] homePids = new String[0][0];
		final String syntax = ACB.getAdminBrokerCodesQuery(brokerCodes,branchCode);
		
		if(proID.length()>0 && !"All".equalsIgnoreCase(proID)){
			homePids = getProducts(branchCode,proID);
		}
		else{
			homePids = getProducts(branchCode,ROYAL);
		}
		final String pids = homeValid.removeLastChar(homeValid.getStringFromArray(homePids), SINGLECOMMA);
		final String addeddata = "product_id in("+pids+")";
        query = "select b.company_name,l.login_id from login_master l,broker_company_master b where l.oa_code=b.agency_code and l.login_id in(select distinct login_id from personal_info where login_id in(select login_id from login_master where USERTYPE='Broker' and agency_code in(select agency_code from login_user_details where "+addeddata+" and agency_code in (select agency_code from login_master where oa_code in(select oa_code from login_master where USERTYPE='Broker')))) and login_id!='NONE' and application_id in ('2')) and l.login_id in(select login_id from login_master where oa_code in("+syntax+")) order by b.company_name";
        final String[][] getAllBrokers = runner.multipleSelection(query);
        logger.debug(EXIT);
		
        return getAllBrokers;
    }

	public String getSchmeName(final String qno) throws BaseException
	{
		logger.info("getSchmeName method()");
		logger.debug(ENTER);
		final String args[] = {qno};
		final String res =  runner.singleSelection("select PRODUCT_NAME from PRODUCT_MASTER where PRODUCT_ID=(select nvl(PROPOSAL_NO,PRODUCT_ID) from HOME_POSITION_MASTER where quote_no=?)",args);
		logger.debug(EXIT);
		
		return res;
	}

	public String[][] getTodaysDate() throws BaseException
	{
		logger.info("getTodaysDate method()");
		logger.debug(ENTER);
		final StringBuffer temp = new StringBuffer(1000);
		temp.append("sysdate");
		final String[] args = {"62","1","Y","020"};
		final String hour = runner.singleSelection("select DETAIL_NAME from constant_detail where category_id=? and category_detail_id=? and status=? and branch_code=?", args);
		if (hour.length() > 0){
				temp.append('+');
				temp.append(hour);
		}
		query = "select extract(day from "+ temp.toString()+ "),extract(month from "+ temp.toString()+ "),extract(year from "
				+ temp.toString()+ ") from dual";
		final String[][] result = runner.multipleSelection(query);
		logger.debug(EXIT);
		
		return result;
	}
	public void getUpdatePDFStatus(final String policyNo,final String loginCode,final String clickStatus) throws BaseException
	{
		logger.info("getUpdatePDFStatus method()");
		logger.debug(ENTER);
		final String args[] = {clickStatus,policyNo};
		runner.multipleUpdation("update home_position_master set pdf_broker_status=? where policy_no=? and status='P'",args);
		logger.debug(EXIT);
		
	}
	
}// Class