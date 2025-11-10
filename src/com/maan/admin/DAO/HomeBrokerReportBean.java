package com.maan.admin.DAO;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.apache.logging.log4j.Logger;

import com.maan.common.LogUtil;
import com.maan.common.exception.BaseException;
import com.maan.services.util.runner;

public class HomeBrokerReportBean
{
	private static final Logger logger = LogUtil.getLogger(HomeBrokerReportBean.class);
	final private transient static String ENTER = "- Enter";
	final private transient static String EXIT ="- Exit";
	final private transient static String ALL = "all";
	final private transient static String TOTALUSERS = "totalUsers";
	final private transient static String LOGINID = "Login_Id";
	final private transient static String POLICYNO ="Policy_No";
	final private transient static String PREMIUM = "Premium";
	final private transient static String COMMISSION = "commsion";
	final private transient static String QUOTES = "Quotes";
	final private transient static String REFERRAL = "Referral";
	final private transient static String LAPSED = "Lapsed";
	final private transient static String LOGIN = "login";
	final private transient static String USER = "User";
	final private transient static String BROKER = "Broker";
	final private transient static char SINGLECOMMA = ',';
	final private transient AdminCommonBean ACB = new AdminCommonBean();
	private transient String query = "";
	private String travel = "";
	private String[][] brokerLoginIds = new String[0][0];
	
	public void setBrokerLoginIds(final String[][] brokerLoginIds){
        this.brokerLoginIds=(String[][])brokerLoginIds.clone();
    }
	
	public String[][] getBrokerLoginIds(){
	    return (String[][])brokerLoginIds.clone();
	}
    
	public void setTravel(final String travel){
			this.travel = travel;
	}
	
	public String getTravel(){
		return this.travel;
	}
	
	public HashMap reportCheckingValues(final String data1,final String data2,final String broktype,final String pid,final String loginBra) throws BaseException
	{
    	logger.info("reportCheckingValues method()");
		logger.debug(ENTER);
        String values[][]    = new String[0][0];
        final Map totalUsers   = new HashMap();
		String produc_ids 	 = "";
		String qry			 = "";
        String fullTravelIds = "";
		if(ALL.equalsIgnoreCase(pid)){
			produc_ids = ACB.getAllHomeTravelPids();
		}
		else{
			fullTravelIds = ACB.getAllTravelPids(pid,loginBra);
			if(fullTravelIds.length()>0){
				produc_ids = fullTravelIds;
			}
			else{
				produc_ids = pid;
			}
		}
        final String userType =  ACB.getUserTypeInformation(broktype);
		values = ACB.getBrokerUserPersonalInfo(userType,broktype);
		final String startdate = data1;
        final String enddate = data2;
        int pVal=0;
        int totalLen = 0;

        for(int i=0;i<values.length;i++)
        {
           qry="select a.policy_no,to_char(a.inception_date,'yyyy-MM-dd'),a.OVERALL_PREMIUM,a.remarks,a.login_id,a.quote_no,a.debit_note_no,(b.first_name||b.last_name||b.company_name),a.login_id,a.COMMISSION,nvl(a.PAYMENT_MODE,'Nil') from home_position_master a,personal_info b where a.inception_date between to_date(?,'dd-mm-yyyy') and to_date(?,'dd-mm-yyyy')+1 and a.login_id in ('"+values[i][1]+"') and a.status='P' and a.PRODUCT_ID in ("+produc_ids+") and a.DEBIT_NOTE_NO is not null and a.customer_id=b.customer_id  order by a.inception_date desc";
            final String args1[] = {startdate,enddate};
            final String[][] returnval=runner.multipleSelection(qry,args1);
            if(returnval.length>0){
               totalLen = totalLen+returnval.length;
            }
        }

        String totalBrokerUsers[][]=new String[totalLen][11];
        for(int i=0;i<values.length;i++){
            query="select a.policy_no,to_char(a.inception_date,'yyyy-MM-dd'),a.OVERALL_PREMIUM,a.remarks,a.login_id,a.quote_no,a.debit_note_no,(b.first_name||b.last_name||b.company_name),a.login_id,a.COMMISSION,nvl(a.PAYMENT_MODE,'Nil') from home_position_master a,personal_info b where a.inception_date between to_date(?,'dd-mm-yyyy') and to_date(?,'dd-mm-yyyy')+1 and a.login_id in ('"+values[i][1]+"') and a.status='P' and  a.PRODUCT_ID in ("+produc_ids+") and a.DEBIT_NOTE_NO is not null and a.customer_id=b.customer_id  order by a.inception_date desc";
			final String args1[] = {startdate,enddate};
			final String[][] returnval=runner.multipleSelection(query,args1);
            if(returnval.length>0) {
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
                    pVal++;
                }
            }
        }
        totalUsers.put(TOTALUSERS,totalBrokerUsers);
        logger.debug(EXIT);
        
        return (HashMap)totalUsers;
	}


	public String getloginOacode(final String login_id) throws BaseException
	{
		logger.info("getloginOacode method()");
			logger.debug(ENTER);
			final String args[] = {login_id};
			final String str	= runner.singleSelection("select oa_code from login_master where login_id = ?",args);
		logger.debug(EXIT);
		
		return str;
	}

	public String getNameByAgencyCode(final String login) throws BaseException
	{
    	logger.info("getNameByAgencyCode method()");
		logger.debug(ENTER);
        String pname  = "";
        int check     = 0;
        final String agcode = ACB.getAgencyCode(login);
        final String args[] = {agcode};
        final String res = runner.singleSelection("select count(*) from broker_company_master where agency_code = ? ",args);
        if(res.length()>0){
            check=Integer.parseInt(res);
        }
        else{
            check=0;
        }

        if(check>0){
            query = "select COMPANY_NAME from broker_company_master where agency_code=?";
        }
        else{
            query = "select nvl(first_name,'')+' '+nvl(last_name,'')+' '+nvl(company_name,'') from personal_info where agency_code=?";
        }
        final String res1 = runner.singleSelection(query,args);
        if(res1==null){
        	pname="";
        }
        else{
        	pname=res1;
        }
        logger.debug(EXIT);
        
        return pname;
    }
	
	public String getProductName(final String id,final String loginBra) throws BaseException
	{
		logger.info("getProductName method()");
		logger.debug(ENTER);
		String PName = "";
		final String args[] = new String[1];
		if (!id.equalsIgnoreCase("ALL"))
		{
				args[0] = loginBra;
				PName = runner.singleSelection("select product_name from product_master where product_id in("+id+") and branch_code=? and status='Y'", args);
				if (id.equals("22") || id.equals("31") || id.equals("35")
						|| id.equals("36") || id.equals("37")
						|| id.equals("50") || id.equals("51")
						|| id.equals("52")) {
					PName = "TRAVEL INSURANCE";
				}
			}
		logger.debug(EXIT);
	    
		return PName;
	}
	
	public String getDate() throws BaseException
	{
    	logger.info("getDate method()");
		logger.debug(ENTER);
        final Calendar cal = Calendar.getInstance();
        final String date = cal.get(Calendar.DAY_OF_MONTH)+"-"+cal.get(Calendar.MONTH)+"-"+cal.get(Calendar.YEAR)+"_"+cal.get(Calendar.HOUR)+"_"+cal.get(Calendar.MINUTE)+"_"+cal.get(Calendar.SECOND);
        logger.debug(EXIT);
        
        return date;
	}
	
	//	Broker side Report For Home & Travel BrokerCode Restriction - HomeInsurance/finalReportHome1.jsp
	public HashMap brokerCheckingValues12345(final String data1,final String data2,final String loginid,final String pid,final String branchCode) throws BaseException
	{
		logger.info("brokerCheckingValues12345 method()");
		logger.debug(ENTER);

        final String startdate=data1;
        final String enddate=data2;
		final HashMap Utype = new HashMap();
		String produc_ids = "";
        String fullTravelIds = "";
		if(ALL.equalsIgnoreCase(pid)){
			produc_ids = ACB.getAllHomeTravelPids();
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

        query = "select a.login_id, a.agency_code, a.oa_code, a.first_name, b.usertype from personal_info a,login_master b where a.agency_code in(select agency_code from login_user_details where login_id=?) and a.oa_code = b.oa_code and a.agency_code = b.agency_code and a.login_id in(select login_id from login_master where oa_code in(select agency_code from broker_company_master where branch_code in("+branchCode+")))";
		final String args[] = {loginid};
		final String[][] values=runner.multipleSelection(query,args);
		brokerLoginIds = values;
		for(int i=0;i<values.length;i++){
			Utype.put(LOGIN+values[i][0],values[i][4]);
		}
        setBrokerLoginIds(values);
        final Map brokerData=new HashMap();
        int pval=0;
        String subqry = "";
        for(int i=0;i<values.length;i++){
            subqry = "";
            if(BROKER.equalsIgnoreCase((String)Utype.get(LOGIN+values[i][0]))){
                subqry = "select login_id from personal_info where oa_code=(select oa_code from login_master where login_id='"+values[i][0]+"') and login_id!='NONE' and application_id in ('2','3') and login_id in(select login_id from login_master where oa_code in(select agency_code from broker_company_master where branch_code in("+branchCode+")))";

			}
            else  if(USER.equalsIgnoreCase((String)Utype.get(LOGIN+values[i][0]))){
                subqry = "'"+values[i][0]+"'";
			}

            //For Policy Reports
            query="select count(login_id),count(POLICY_NO),sum(round(OVERALL_PREMIUM,0)),sum(round(COMMISSION,0)) from home_position_master where login_id in("+subqry+") and inception_date between to_date(?,'dd-mm-yyyy') and to_date(?,'dd-mm-yyyy')+1 and PRODUCT_ID in("+produc_ids+") and DEBIT_NOTE_NO is not null and status='P' and login_id in(select login_id from login_master where oa_code in(select agency_code from broker_company_master where branch_code in("+branchCode+")))";
            final String args1[] = {startdate,enddate};
            final String[][] returnval=runner.multipleSelection(query,args1);

            //For Quotes Report
            query = "select count(login_id),count(QUOTE_NO) from home_position_master where login_id in("+subqry+") and entry_date between to_date(?,'dd-mm-yyyy') and to_date(?,'dd-mm-yyyy')+1 and  PRODUCT_ID in("+produc_ids+") and QUOTE_NO is not null and lower(status)='y' and REMARKS is null and ADMIN_REFERRAL_STATUS is null and login_id in(select login_id from login_master where oa_code in(select agency_code from broker_company_master where branch_code in("+branchCode+")))";
			final String args2[] = {startdate,enddate};
            final String[][] quoteRes = runner.multipleSelection(query,args2);

            //For Referral Reports
            query = "select count(login_id),count(REMARKS) from home_position_master where login_id in("+subqry+") and entry_date between to_date(?,'dd-mm-yyyy') and to_date(?,'dd-mm-yyyy')+1 and PRODUCT_ID in("+produc_ids+") and QUOTE_NO is not null and (ADMIN_REFERRAL_STATUS is not null or REMARKS is not null) and status not in('P') and login_id in(select login_id from login_master where oa_code in(select agency_code from broker_company_master where branch_code in("+branchCode+")))";
            final String args3[] = {startdate,enddate};
            final String[][] refRes = runner.multipleSelection(query,args3);

            //For Lapsed Reports
            query = "select count(login_id),count(status) from home_position_master where login_id in("+subqry+") and entry_date between to_date(?,'dd-mm-yyyy') and to_date(?,'dd-mm-yyyy')+1 and  PRODUCT_ID in("+produc_ids+") and QUOTE_NO is not null and lower(status)='d' and login_id in(select login_id from login_master where oa_code in(select agency_code from broker_company_master where branch_code in("+branchCode+")))";
            final String args4[] = {startdate,startdate};
            final String[][] labRes = runner.multipleSelection(query,args4);

            String refs = "0";
            String quos = "0";
            String labs = "0";
            if(!"0".equalsIgnoreCase(returnval[0][0])||!"0".equalsIgnoreCase(quoteRes[0][0])||!"0".equalsIgnoreCase(refRes[0][0])||!"0".equalsIgnoreCase(labRes[0][0]))
            {
                    brokerData.put("SNo"+pval,Integer.toString(pval));
                    String pol = "0";
                    String Premium = "0";
                    String commsion = "0";
                    if(returnval.length>0){
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
            //int len = pval;
            brokerData.put("Tsize",Integer.toString(pval));
            logger.debug(EXIT);
    		
         return (HashMap)brokerData;
    }
	
//	This is for Broker side Lapsed Quote wise Reports
	public HashMap reportCheckingValuesForLapsed(final String data1,final String data2,final String broktype,final  String pid,final String loginBranch) throws BaseException
	{
		logger.info("reportCheckingValuesForLapsed method()");
		logger.debug(ENTER);
        final Map totalUsers = new HashMap();
		String produc_ids = "";
		String fullTravelIds = "";
		if(ALL.equalsIgnoreCase(pid)){
			produc_ids = ACB.getAllHomeTravelPids();
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

        final String userType  = ACB.getUserTypeInformation(broktype);
		final String[][] values = ACB.getBrokerUserPersonalInfo(userType,broktype);

        final String startdate=data1;
        final String enddate=data2;
        int pVal=0;
        int totalLen = 0;

        for(int i=0;i<values.length;i++)
        {
            query = "select a.quote_no,to_char(a.LAPSED_DATE,'yyyy-MM-dd'),a.OVERALL_PREMIUM,to_char(a.EXPIRY_DATE,'dd-MM-yyyy'),(b.first_name||b.last_name||b.company_name),a.login_id,a.LAPSED_REMARKS from home_position_master a,personal_info b where a.entry_date between to_date(?,'dd-mm-yyyy') and to_date(?,'dd-mm-yyyy')+1 and a.login_id in ('"+values[i][1]+"') and a.status='D' and a.remarks is null and a.ADMIN_REFERRAL_STATUS is null and a.PRODUCT_ID in ("+produc_ids+")  and a.customer_id=b.customer_id order by a.inception_date desc";
            final String args1[] = {startdate,enddate};
            final String[][] returnval=runner.multipleSelection(query,args1);
            if(returnval.length>0){
                totalLen = totalLen+returnval.length;
            }
        }

        String totalBrokerUsers[][]=new String[totalLen][7];

        for(int i=0;i<values.length;i++)
        {
            query="select a.quote_no,to_char(a.LAPSED_DATE,'yyyy-MM-dd'),a.OVERALL_PREMIUM,to_char(a.EXPIRY_DATE,'dd-MM-yyyy'),(b.first_name||b.last_name||b.company_name),a.login_id,a.LAPSED_REMARKS from home_position_master a,personal_info b where a.entry_date between to_date(?,'dd-mm-yyyy') and to_date(?,'dd-mm-yyyy')+1 and a.login_id in ('"+values[i][1]+"') and a.status='D' and a.remarks is null and a.ADMIN_REFERRAL_STATUS is null and a.PRODUCT_ID in ("+produc_ids+")  and a.customer_id=b.customer_id order by a.inception_date desc";
            final String args1[] = {startdate,enddate};
            final String[][] returnval=runner.multipleSelection(query,args1);

            if(returnval.length>0){
                for(int t=0;t<returnval.length;t++)	{
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
        totalUsers.put(TOTALUSERS,totalBrokerUsers);
        logger.debug(EXIT);
        
        return (HashMap)totalUsers;
    }
	
	//	This is Broker side quote wise Reports
	public HashMap reportCheckingValuesForQuote(final String data1,final String data2,final String broktype, final String pid,final String loginBranch) throws BaseException
	{
    	logger.info("reportCheckingValuesForQuote method()");
		logger.debug(ENTER);

        String values[][]=new String[0][0];
        final Map totalUsers = new HashMap();
		String produc_ids = "";
        String fullTravelIds = "";

		if(ALL.equalsIgnoreCase(pid)){
			produc_ids = ACB.getAllHomeTravelPids();
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
        values = ACB.getBrokerUserPersonalInfo(userType,broktype);

        final String startdate=data1;
        final String enddate=data2;
        int pVal=0;
        int totalLen = 0;

        for(int i=0;i<values.length;i++)
		{
            query = "select a.quote_no,to_char(a.entry_date,'yyyy-MM-dd'),a.OVERALL_PREMIUM,to_char(a.entry_date+30,'dd-MM-yyyy'),(b.first_name||b.last_name||b.company_name),a.login_id,a.COMMISSION from home_position_master a,personal_info b where a.entry_date between to_date(?,'dd-mm-yyyy') and to_date(?,'dd-mm-yyyy')+1 and a.login_id in ('"+values[i][1]+"') and a.status='Y' and a.remarks is null and a.ADMIN_REFERRAL_STATUS is null and a.PRODUCT_ID in ("+produc_ids+") and a.customer_id=b.customer_id order by a.inception_date desc";
            final String args1[] = {startdate,enddate};
            final String returnval[][] = runner.multipleSelection(query,args1);
            if(returnval.length>0){
                totalLen = totalLen+returnval.length;
            }
        }
        String totalBrokerUsers[][]=new String[totalLen][7];
        for(int i=0;i<values.length;i++){
            query="select a.quote_no,to_char(a.entry_date,'yyyy-MM-dd'),a.OVERALL_PREMIUM,to_char(a.entry_date+30,'dd-MM-yyyy'),(b.first_name||b.last_name||b.company_name),a.login_id,a.COMMISSION from home_position_master a,personal_info b where a.entry_date between to_date(?,'dd-mm-yyyy') and to_date(?,'dd-mm-yyyy')+1 and a.login_id in ('"+values[i][1]+"') and a.status='Y' and a.remarks is null and a.ADMIN_REFERRAL_STATUS is null and a.PRODUCT_ID in ("+produc_ids+") and a.customer_id=b.customer_id order by a.inception_date desc";
			final String args1[] = {startdate,enddate};
            final String[][]  returnval=runner.multipleSelection(query,args1);
            if(returnval.length>0){
                for(int t=0;t<returnval.length;t++){
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
        totalUsers.put(TOTALUSERS,totalBrokerUsers);
        logger.debug(EXIT);
        
        return (HashMap)totalUsers;
    }
	
//	This is for Broker side Refferal Quote wise Reports
    public HashMap reportCheckingValuesForReferral(final String data1,final String data2,final String broktype,final String pid,final String loginBranch) throws BaseException
	{
    	logger.info("reportCheckingValuesForReferral method()");
		logger.debug(ENTER);
        final Map totalUsers = new HashMap();
		String produc_ids = "";
		final Hashtable hashTravelPids = new Hashtable();
		String fullTravelIds = "";
		if(ALL.equalsIgnoreCase(pid)){
			produc_ids = ACB.getAllHomeTravelPids();
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
        final String[][] values = ACB.getBrokerUserPersonalInfo(userType,broktype);
        final String startdate=data1;
        final String enddate=data2;
        int pVal=0;
        int totalLen = 0;
        for(int i=0;i<values.length;i++)
        {
            query="select a.quote_no,to_char(a.entry_date,'yyyy-MM-dd'),(b.first_name||b.last_name||b.company_name),a.REFERRAL_DESCRIPTION,a.status,a.remarks from home_position_master a,personal_info b where a.entry_date between to_date(?,'dd-mm-yyyy') and to_date(?,'dd-mm-yyyy')+1 and a.login_id in ('"+values[i][1]+"') and (a.status='R' or a.remarks is not null or a.ADMIN_REFERRAL_STATUS is not null) and a.status not in('P') and a.PRODUCT_ID in ("+produc_ids+")  and a.customer_id=b.customer_id order by a.inception_date desc";
			final String args1[] = {startdate,enddate};
            final String[][] returnval=runner.multipleSelection(query,args1);
            if(returnval.length>0){
               totalLen = totalLen+returnval.length;
            }
        }
        String totalBrokerUsers[][]=new String[totalLen][7];
        String refQuote= "";
        for(int i=0;i<values.length;i++)
        {
            query="select a.quote_no,to_char(a.entry_date,'yyyy-MM-dd'),(b.first_name||b.last_name||b.company_name),a.REFERRAL_DESCRIPTION,a.status,a.remarks from home_position_master a,personal_info b where a.entry_date between to_date(?,'dd-mm-yyyy') and to_date(?,'dd-mm-yyyy')+1 and a.login_id in ('"+values[i][1]+"') and (a.status='R' or a.remarks is not null or a.ADMIN_REFERRAL_STATUS is not null) and a.status not in('P') and a.PRODUCT_ID in ("+produc_ids+")  and a.customer_id=b.customer_id order by a.inception_date desc";
            final String args1[] = {startdate,enddate};
            final String[][] returnval=runner.multipleSelection(query,args1);
            if(returnval.length>0){
                String Status = "";
                for(int t=0;t<returnval.length;t++){
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
		if(!hashTravelPids.contains(pid)){
            if(refQuote.length()>0){
				refQuote = refQuote.substring(0,refQuote.lastIndexOf(SINGLECOMMA));
            }
            String reftypesql = "";
			if(refQuote.length()>0){
				reftypesql = "select QUOTE_NO,REFERRAL_DESCRIPTION from HOME_POSITION_MASTER where quote_no in("+refQuote+") and REMARKS is not null";
			}
            String refTypes[][]=new String[0][0];
			if(reftypesql.length()>0){
				refTypes = runner.multipleSelection(reftypesql);
			}

            int rtVal=0;
            int flag=0;
            for(int r=0;r<totalBrokerUsers.length;r++) {
                for(rtVal=0;rtVal<refTypes.length;rtVal++) {
                    if(totalBrokerUsers[r][0].equalsIgnoreCase(refTypes[rtVal][0])) {
                        totalBrokerUsers[r][6] = refTypes[rtVal][1];
                        flag = 1;
                    }
                }
                if(flag==0){
                    totalBrokerUsers[r][6] = "Admin Referral";
                }
            }
		 }
        totalUsers.put(TOTALUSERS,totalBrokerUsers);

        logger.debug(EXIT);
        

        return (HashMap)totalUsers;
    }
} // Class