package com.maan.procommission.DAO;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Logger;

import proj.date.DateFunction;

import com.maan.common.LogUtil;
import com.maan.common.exception.BaseException;
import com.maan.services.util.ValidationFormat;
import com.maan.services.util.runner;

public class ProCommissionBean
{
	private static final Logger logger = LogUtil.getLogger(ProCommissionBean.class);
	final static transient private String ENTER = "- Enter";
	final static transient private String EXIT = "- Exit";
	final static transient private String QUERY = "Query";
	final static transient private String ARGS = "Args";
	
	private String dobDay = "";
	private String dobMonth = "";
	private String dobYear = "";
	private String dobDay1 = "";
	private String dobMonth1 = "";
	private String dobYear1 = "";
	private String dobDay2 = "";
	private String dobMonth2 = "";
	private String dobYear2 = "";
	private String data1 = "";
	private String data2 = "";
	private String data3 = "";
	private String brokerIds = "";
	private String openCoverNo = "";
	private String proCom = "";
	private String branch = "";
	private String pid = "";
	private String proStaus = "";
	
	public void setDobDay(final String dobDay){
		this.dobDay=dobDay;
	}
	public void setDobMonth(final String dobMonth){
		this.dobMonth=dobMonth;
	}
	public void setDobYear(final String dobYear){
		this.dobYear=dobYear;
	}
	public String getDobDay(){
		return dobDay;
	}
	public String getDobMonth(){
		return dobMonth;
	}
	public String getDobYear(){
		return dobYear;
	}
	public void setDobDay1(final String dobDay1){
		this.dobDay1=dobDay1;
	}
	public void setDobMonth1(final String dobMonth1){
		this.dobMonth1=dobMonth1;
	}
	public void setDobYear1(final String dobYear1){
		this.dobYear1=dobYear1;
	}
	public void setDobDay2(final String dobDay2){
		this.dobDay2=dobDay2;
	}
	public void setDobMonth2(final String dobMonth2){
		this.dobMonth2=dobMonth2;
	}
	public void setDobYear2(String dobYear2){
		this.dobYear2=dobYear2;
	}
	public String getDobDay2(){
		return dobDay2;
	}
	public String getDobMonth2(){
		return dobMonth2;
	}
	public String getDobYear2(){
		return dobYear2;
	}
	public void setOpenCoverNo(final String openCoverNo){
		this.openCoverNo = openCoverNo;
	}
	public String getOpenCoverNo(){
		return openCoverNo;
	}
	public String getDobDay1(){
		return dobDay1;
	}
	public String getDobMonth1(){
		return dobMonth1;
	}
	public String getDobYear1(){
		return dobYear1;
	}
	public void setData1(final String data1){
		this.data1=data1;
	}
	public void setData2(final String data2){
		this.data2=data2;
	}
	public String getData1(){
		return this.data1;
	}
	public String getData2(){
		return this.data2;
	}
	public void setData3(final String data3){
		this.data3=data3;
	}
	public String getData3(){
		return this.data3;
	}
	public void setbrokerIds(final String brokerIds){
		this.brokerIds = brokerIds;
	}
	public String getbrokerIds(){
		return brokerIds;
	}
	public void setProCom(final String proCom){
		this.proCom = proCom;
	}
	public String getProCom(){
		return proCom;
	}
	public void setBranch(final String branch){
		this.branch = branch;
	}
	public String getBranch(){
		return branch;
	}
	public void setPid(final String pid){
		this.pid = pid;
	}
	public String getPid(){
		return pid;
	}
	public void setProStaus(final String proStaus){
		this.proStaus = proStaus;
	}
	public String getProStaus(){
		return proStaus;
	}
	
	public String dateValidation(final String row)throws BaseException
	{
		logger.info("dateValidation ProCommossionBean method()");
		logger.debug(ENTER);
		com.maan.services.util.dataCollection data = null; 
		com.maan.Home.DataManipualtion.DataSelect dataSelect =null;
		data = new com.maan.services.util.dataCollection();
		dataSelect = new com.maan.Home.DataManipualtion.DataSelect();
		StringBuffer error = null; error = new StringBuffer(1000);
		String values = null;
		String values1 = null;
		Calendar cal1 = null; cal1 = Calendar.getInstance();
		Calendar cal2 = null; cal2 = Calendar.getInstance();
		String[][] serverDate = new String[0][0]; 
		
		long diff=0;
		if(	proCom.length()>0)
		{
			if(!ValidationFormat.isNumberValue(proCom)){
				error.append("<br>* Invalid Promotional Commission percentage for row "+row);
			}

			values1=data.checkDate(dobDay2+"/"+dobMonth2+"/"+dobYear2);
			values1 = values1==null?"Valid":values1;
			DateFunction dbr = null; dbr = new DateFunction();
			values=data.checkDate(dobDay+"/"+dobMonth+"/"+dobYear);
			if("Invalid".equalsIgnoreCase(values)){
				error.append("<br>*"+runner.getErrormsg("62")+" for row "+row);
			}
			else if("Invalid".equalsIgnoreCase(values1)&&!proStaus.equalsIgnoreCase("Y"))
			{
				
				cal1.set(Integer.parseInt(dobYear),Integer.parseInt(dobMonth),Integer.parseInt(dobDay));
				serverDate = dataSelect.getTodaysDate();
				cal2.set(Integer.parseInt(serverDate[0][0]),Integer.parseInt(serverDate[0][1]),Integer.parseInt(serverDate[0][2]));
				diff = 0;
				try {
                   diff = dbr.getDayDifference(cal1,cal2);
				}catch(Exception ex) {
                   logger.info(ex);
				}
				if(diff > 0) {
					error.append("<br>*Start Date cannot be less than todays Date for row "+row);
				}
			}
			values=data.checkDate(dobDay1+"/"+dobMonth1+"/"+dobYear1);
			if("Invalid".equalsIgnoreCase(values)){
				error.append("<br>*"+runner.getErrormsg("63")+" for row "+row);
			}
			else if(Integer.parseInt(dobYear1)<Integer.parseInt(dobYear)){
				error.append("<br>*"+runner.getErrormsg("72")+" for row "+row);
			}
			else if(Integer.parseInt(dobYear1)==Integer.parseInt(dobYear)) 
			{
				if(Integer.parseInt(dobMonth1)<Integer.parseInt(dobMonth)){
					error.append("<br>*"+runner.getErrormsg("72")+" for row "+row);
				}
				else if(Integer.parseInt(dobMonth1)==Integer.parseInt(dobMonth) && Integer.parseInt(dobDay1)<Integer.parseInt(dobDay))
				{
					error.append("<br>*"+runner.getErrormsg("72")+" for row "+row);
				}
			}
			
			String enteredDate = dobDay+"/"+dobMonth+"/"+dobYear;
			String lastendDate = "";
			
			if("Invalid".equalsIgnoreCase(values1)&&!proStaus.equalsIgnoreCase("Y"))
			{
				try
				{
					if(!pid.equals("11")){
						lastendDate = com.maan.services.util.runner.singleSelection("select to_char(END_DATE,'dd-mm-yyyy') from LOGIN_PRO_COMMISSION where PRODUCT_ID='"+pid+"' and AGENCY_CODE='"+brokerIds+"' and branch_code='"+branch+"' and open_cover_no='"+openCoverNo+"' and amend_id=(select max(amend_id) from LOGIN_PRO_COMMISSION where PRODUCT_ID='"+pid+"' and AGENCY_CODE='"+brokerIds+"' and branch_code='"+branch+"' and open_cover_no='"+openCoverNo+"')");
					}
					else {
						lastendDate = com.maan.services.util.runner.singleSelection("select to_char(END_DATE,'dd-mm-yyyy') from LOGIN_PRO_COMMISSION where PRODUCT_ID='"+pid+"' and AGENCY_CODE=(select AGENCY_CODE from login_master where login_id='"+brokerIds+"') and branch_code='"+branch+"' and open_cover_no='"+openCoverNo+"' and amend_id=(select max(amend_id) from LOGIN_PRO_COMMISSION where PRODUCT_ID='"+pid+"' and AGENCY_CODE=(select AGENCY_CODE from login_master where login_id='"+brokerIds+"') and branch_code='"+branch+"' and open_cover_no='"+openCoverNo+"')");
					}
					if(lastendDate.length()>0){
						diff=dbr.getDayDifference(dbr.getCalendar(lastendDate),dbr.getCalendar(enteredDate));
					}
					else{
						diff = 1;
					}
				}
				catch(Exception e){
					logger.info(e);
				}
				if(diff<=0)	{
					error.append("<br>* Start Date should be greater than Last End Date("+lastendDate+") for row "+row);
				}
			}
			values = values1;
			if("Invalid".equalsIgnoreCase(values)&&proStaus.equalsIgnoreCase("Y")){
				error.append("<br>*Please select Cancel/Stop date for row "+row+" to Cancel the Promotional Commission");
			}
			else if(!proStaus.equalsIgnoreCase("Y")&&proStaus.length()>0&&"Valid".equalsIgnoreCase(values)){
				error.append("<br>*Please select Cancel Status as Yes for row "+row+" to Cancel the Promotional Commission");
			}
			else if(proStaus.equalsIgnoreCase("Y")&&"Valid".equalsIgnoreCase(values))
			{
              cal1.set(Integer.parseInt(dobYear2),Integer.parseInt(dobMonth2),Integer.parseInt(dobDay2));
		      serverDate = dataSelect.getTodaysDate();
	          cal2.set(Integer.parseInt(serverDate[0][0]),Integer.parseInt(serverDate[0][1]),Integer.parseInt(serverDate[0][2]));
              diff = 0;
              try {
                   diff = dbr.getDayDifference(cal1,cal2);
              }
              catch(Exception e){
                   logger.info(e);
              }
			  if(diff >= 0){
				error.append("<br>*Cancel/Stop date should be greater than todays date for row "+row+" to Cancel the Promotional Commission");
			  }
			  cal2.set(Integer.parseInt(dobYear1),Integer.parseInt(dobMonth1),Integer.parseInt(dobDay1));
			  diff = 0;
              try {
                   diff = dbr.getDayDifference(cal1,cal2);
              } catch(Exception e){
                   logger.info(e);
              }
              if(diff < 0){
				error.append("<br>*Cancel/Stop date should be less than Expiry date for row "+row+" to Cancel the Promotional Commission");
              }
			}
		}
		else {
			error.append("<br>* Please Enter Promotional Commission Percentage");
		}
		logger.debug(EXIT);
		
		return error.toString();		
	}
	
	public String[][] getBrokersHasCover(final String branchCode)throws BaseException
	{
		logger.info("getBrokersHasCover ProCommossionBean method()");
		logger.debug(ENTER);
		String[][] getBrokerName=new String[0][0];
		String sql="";
		sql = "select  bcm.company_name,pi.login_id from BROKER_COMPANY_MASTER bcm,personal_info pi where bcm.agency_code=pi.agency_code  and bcm.status='Y' and pi.status=bcm.status and pi.APPLICATION_ID='2' and pi.login_id in(select distinct ocm.broker_id from OPEN_COVER_MASTER ocm,OPEN_COVER_POSITION_MASTER ocpm where ocm.broker_id in(select login_id from login_master where status!='N' and oa_code in(select agency_code from broker_company_master where branch_code in('"+branchCode+"'))) and ocpm.STATUS='P' and ocm.PROPOSAL_NO=ocpm.PROPOSAL_NO and ocpm.EXPIRY_DATE>(select sysdate from dual)) order by lower(bcm.COMPANY_NAME) ";
		getBrokerName=runner.multipleSelection(sql);
		logger.debug(EXIT);
		
		return getBrokerName;
	}

	public String[][] getBrokerDetails(final String loginBra,final String pid)throws BaseException
	{
		logger.info("getBrokerDetails ProCommossionBean method()");
		logger.debug(ENTER);
		String[][] result = new String[0][0];
	  	String sqlQuery="";
		sqlQuery="select nvl(COMPANY_NAME,CONTACT_PERSON),AGENCY_CODE from BROKER_COMPANY_MASTER where BRANCH_CODE=? and AGENCY_CODE in(select oa_code from LOGIN_USER_DETAILS where product_id in('"+pid+"'))";
		String args[] = new String[1];
		args[0] = loginBra;
	    result = runner.multipleSelection(sqlQuery,args);
		logger.debug(EXIT);
		
		return result;
	}
	
	/***Report Screen***/

	public String[][] getBrokerDetailsForReport(final String loginBra,final String pid)throws BaseException
	{
		logger.info("getBrokerDetailsForReport ProCommossionBean method()");
		logger.debug(ENTER);
		String[][] result = new String[0][0];
		String sqlQuery="";
		String args[] = new String[3];
		result = new String[0][0];
		sqlQuery="select nvl(COMPANY_NAME,CONTACT_PERSON),AGENCY_CODE from BROKER_COMPANY_MASTER where BRANCH_CODE=? and agency_code in(select agency_code from LOGIN_PRO_COMMISSION where BRANCH_CODE=? and PRODUCT_ID=? and pro_commission is not null) and AGENCY_CODE in(select oa_code from LOGIN_USER_DETAILS where product_id in('"+pid+"'))";
		args[0] = loginBra;
		args[1] = loginBra;
		args[2] = pid;
		result = runner.multipleSelection(sqlQuery,args);
		logger.debug(EXIT);
		
		return result;
	}
	
	public String[][] getBrokersHasCoverReport(final String branchCode)throws BaseException
	{
		logger.info("getBrokersHasCoverReport ProCommossionBean method()");
		logger.debug(ENTER);
		String[][] getBrokerName = new String[0][0];
		String args[] = new String[1];
		String sql = "";
		sql = "select bcm.company_name,pi.login_id from BROKER_COMPANY_MASTER bcm,personal_info pi where bcm.agency_code=pi.agency_code  and bcm.status='Y' and pi.status=bcm.status and pi.APPLICATION_ID='2' and pi.login_id in(select distinct ocm.broker_id from OPEN_COVER_MASTER ocm,OPEN_COVER_POSITION_MASTER ocpm where ocm.broker_id in(select login_id from login_master where status!='N' and oa_code in (select agency_code from LOGIN_PRO_COMMISSION where BRANCH_CODE=? and PRODUCT_ID='11' and pro_commission is not null) and oa_code in(select agency_code from broker_company_master where branch_code in('"+branchCode+"'))) and ocpm.STATUS='P' and ocm.PROPOSAL_NO=ocpm.PROPOSAL_NO and ocpm.EXPIRY_DATE>(select sysdate from dual)) order by lower(bcm.COMPANY_NAME)";
		args[0] = branchCode;
		getBrokerName = runner.multipleSelection(sql,args);
		logger.debug(EXIT);
		
		return getBrokerName;
	}
	
	/***Report Screen***/
	
	public String[][] getBrokerProCommissionDetails(final String loginBra,final String pid)throws BaseException
	{
		logger.info("getBrokerProCommissionDetails ProCommossionBean method()");
		logger.debug(ENTER);
		String[][] result = new String[0][0];
		String sqlQuery = "";
		result = new String[0][0];
		sqlQuery="select AGENCY_CODE,to_char(START_DATE,'dd'),to_char(START_DATE,'MM'),to_char(START_DATE,'YYYY'),to_char(END_DATE,'dd'),to_char(END_DATE,'MM'),to_char(END_DATE,'YYYY'),PRO_COMMISSION,STATUS,product_id,to_char(CANCEL_DATE,'dd'),to_char(CANCEL_DATE,'MM'),to_char(CANCEL_DATE,'YYYY') from LOGIN_PRO_COMMISSION where BRANCH_CODE=? and open_cover_no='0' and product_id in("+pid+") and STATUS='Y' and AMEND_ID||AGENCY_CODE in(select max(amend_id)||AGENCY_CODE from LOGIN_PRO_COMMISSION where AGENCY_CODE in(select AGENCY_CODE from BROKER_COMPANY_MASTER where BRANCH_CODE=? and AGENCY_CODE in(select oa_code from LOGIN_USER_DETAILS where product_id in("+pid+")))  and product_id in("+pid+") and BRANCH_CODE=? and open_cover_no='0' and STATUS='Y' group by AGENCY_CODE) and AGENCY_CODE in(select AGENCY_CODE from BROKER_COMPANY_MASTER where BRANCH_CODE=? and AGENCY_CODE in(select oa_code from LOGIN_USER_DETAILS where product_id in("+pid+")))";
		String args[] = new String[4];
		args[0] = loginBra;
		args[1] = loginBra;
		args[2] = loginBra;
		args[3] = loginBra;
		result = runner.multipleSelection(sqlQuery,args);
		logger.debug(EXIT);
		
		return result;
	}
	
	public String[][] getProductDetails(final String loginBra)throws BaseException
	{
		logger.info("getProductDetails ProCommossionBean method()");
		logger.debug(ENTER);
		String[][] result = new String[0][0];
		String sqlQuery="";
		result = new String[0][0];
		sqlQuery="select PRODUCT_ID,PRODUCT_NAME from PRODUCT_MASTER where status='Y' and BRANCH_CODE=? and COMPANY_ID not in(2) and PRODUCT_ID not in('11') order by PRODUCT_ID";
		String args[] = new String[1];
		args[0] = loginBra;
		result = runner.multipleSelection(sqlQuery,args);
		logger.debug(EXIT);
		
		return result;
	}
	
	public String[][] getproCommissionFullDetailsOther(final String loginBra,final String pid)throws BaseException
	{
		logger.info("getproCommissionFullDetailsOther ProCommossionBean method()");
		logger.debug(ENTER);
		String[][] result = new String[0][0];
		String sqlQuery="";
		sqlQuery="select AGENCY_CODE,to_char(START_DATE,'dd'),to_char(START_DATE,'MM'),to_char(START_DATE,'YYYY'),to_char(END_DATE,'dd'),to_char(END_DATE,'MM'),to_char(END_DATE,'YYYY'),PRO_COMMISSION,STATUS,to_char(ENTRY_DATE,'dd-mm-yyyy'),to_char(CANCEL_DATE,'dd-mm-yyyy') from LOGIN_PRO_COMMISSION where BRANCH_CODE=? and PRODUCT_ID=? order by agency_code,amend_ID";
		String args[] = new String[2];
		args[0] = loginBra;
		args[1] = pid;
		result = runner.multipleSelection(sqlQuery,args);
		logger.debug(EXIT);
		
		return result;
	}
	
	public String[][] getportfolioDetails(final String bid)throws BaseException
	{
		logger.info("getportfolioDetails senthil ProCommossionBean method()");
		logger.debug(ENTER);
		String[][] result =  new String[0][0];
		String[] args =  new String[1];
		String sqlQuery="";
		args[0]= bid;		
		sqlQuery="select distinct(a.proposal_no),nvl(a.open_cover_no,'0'),nvl(to_char(b.open_cover_start_date,'DD-MM-YYYY'),' '),nvl(to_char(b.open_cover_end_date,'DD-MM-YYYY'),' '),nvl(c.first_name,c.company_name),d.customer_id,b.amend_id,d.MISSIPPI_OPENCOVER_NO from open_cover_position_master a, open_cover_detail b,personal_info c,open_cover_master d where a.status='P' and a.proposal_no = b.proposal_no and a.expiry_date >=(select sysdate from dual) and b.amend_id = (select max(amend_id) from open_cover_detail  where proposal_no = a.proposal_no) and a.proposal_no in (select (proposal_no) from open_cover_master where broker_id=?) and d.proposal_no=a.proposal_no and c.customer_id =d.customer_id and d.amend_id=(select max(amend_id) from open_cover_master where proposal_no=a.proposal_no) and (a.admin_status is null or a.admin_status='Y') order by a.proposal_no desc";
		result = runner.multipleSelection(sqlQuery,args);
		logger.debug(EXIT);
		
		return result;
	}
	
	public String[][] getproCommissionDetails(final String loginId,final String loginBra,final String pid)throws BaseException
	{
		logger.info("getproCommissionDetails ProCommossionBean method()");
		logger.debug(ENTER);
		String[][] result =  new String[0][0];
	  	String sqlQuery="";
		result = new String[0][0];
		sqlQuery="select OPEN_COVER_NO,to_char(START_DATE,'dd'),to_char(START_DATE,'MM'),to_char(START_DATE,'YYYY'),to_char(END_DATE,'dd'),to_char(END_DATE,'MM'),to_char(END_DATE,'YYYY'),PRO_COMMISSION,STATUS,product_id,to_char(CANCEL_DATE,'dd'),to_char(CANCEL_DATE,'MM'),to_char(CANCEL_DATE,'YYYY') from LOGIN_PRO_COMMISSION where AGENCY_CODE=(select AGENCY_CODE from login_master where login_id=?) and STATUS='Y' and BRANCH_CODE=? and PRODUCT_ID='11' and (AMEND_ID||open_cover_no) in(select max(AMEND_ID)||open_cover_no from LOGIN_PRO_COMMISSION where AGENCY_CODE=(select AGENCY_CODE from login_master where login_id=?) and BRANCH_CODE=? and STATUS='Y' and PRODUCT_ID='11' group by open_cover_no)";
		String args[] = new String[4];
		args[0] = loginId;
		args[1] = loginBra;
		args[2] = loginId;
		args[3] = loginBra;
		result = runner.multipleSelection(sqlQuery,args);
		logger.debug(EXIT);
		
		return result;
	}
	
	public String[][] getproCommissionFullDetails(final String loginId,final String loginBra,final String pid)throws BaseException
	{
		logger.info("getproCommissionFullDetails ProCommossionBean method()");
		logger.debug(ENTER);
		String[][] result =  new String[0][0];
	  	String sqlQuery = "";
		result = new String[0][0];
		sqlQuery="select OPEN_COVER_NO,to_char(START_DATE,'dd'),to_char(START_DATE,'MM'),to_char(START_DATE,'YYYY'),to_char(END_DATE,'dd'),to_char(END_DATE,'MM'),to_char(END_DATE,'YYYY'),PRO_COMMISSION,STATUS,to_char(ENTRY_DATE,'dd-mm-yyyy'),to_char(CANCEL_DATE,'dd-mm-yyyy') from LOGIN_PRO_COMMISSION where AGENCY_CODE=(select AGENCY_CODE from login_master where login_id=?) and BRANCH_CODE=? and PRODUCT_ID=? order by OPEN_COVER_NO,amend_id";
		String args[] = new String[3];
		args[0] = loginId;
		args[1] = loginBra;
		args[2] = pid;
		result = runner.multipleSelection(sqlQuery,args);
		logger.debug(EXIT);
		
		return result;
	}
	
	public void insertProCommissionDetails(final HashMap proHash)throws BaseException
	{
		logger.info("insertProCommissionDetails ProCommossionBean method()");
		logger.debug(ENTER);
		int amendId = 0;
		String loginId = ""; loginId = (String)proHash.get("brokerIds");
		String loginBra = ""; loginBra = (String)proHash.get("admBranch");
		String pid = ""; pid = (String)proHash.get("pid");
		String openNo = ""; openNo = (String)proHash.get("openCoverNo");
		String sdate = ""; sdate = (String)proHash.get("sdate");
		String edate = ""; edate = (String)proHash.get("edate");
		String cdate = ""; cdate = (String)proHash.get("cdate");
		String proCommission = ""; proCommission = (String)proHash.get("proCommission");
		String proStaus = ""; proStaus = (String)proHash.get("proStaus");
		String args[] = new String[0];
		String sql ="";
		Map sqlMap = null; 
		sqlMap = new HashMap();
		int mapCnt = 0;
		if(!proStaus.equalsIgnoreCase("Y")&&cdate.length()<=0)
		{
			amendId = getMaxAmendId(loginId,loginBra,pid,openNo);
			sql = "update LOGIN_PRO_COMMISSION set status='N' where AGENCY_CODE=(select AGENCY_CODE from login_master where login_id=?) and PRODUCT_ID=? and OPEN_COVER_NO=? and BRANCH_CODE=? and amend_id=?";
			args = new String[5];
			args[0] = loginId;
			args[1] = pid;
			args[2] = openNo;
			args[3] = loginBra;
			args[4] = Integer.toString(amendId-1);
			//runner.multipleUpdation(sql,args);
			sqlMap.put(QUERY+mapCnt,sql);
			sqlMap.put(ARGS+mapCnt++,args );
			sql = "insert into LOGIN_PRO_COMMISSION(AGENCY_CODE,OA_CODE,OPEN_COVER_NO,PRODUCT_ID,AMEND_ID,BRANCH_CODE,START_DATE,END_DATE,ENTRY_DATE,PRO_COMMISSION,STATUS) values((select AGENCY_CODE from login_master where login_id=?),(select OA_CODE from login_master where login_id=?),?,?,?,?,to_date(?,'dd-mm-yyyy'),to_date(?,'dd-mm-yyyy'),(select sysdate from dual),?,'Y')";
			args = new String[9];
			args[0] = loginId;
			args[1] = loginId;
			args[2] = openNo;
			args[3] = pid;
			args[4] = Integer.toString(amendId);
			args[5] = loginBra;
			args[6] = sdate;
			args[7] = edate;
			args[8] = proCommission;
			//runner.multipleInsertion(sql,args);
			sqlMap.put(QUERY+mapCnt,sql);
			sqlMap.put(ARGS+mapCnt++,args );
			
			sqlMap.put("Count",Integer.toString(mapCnt));
			runner.multipleUpdateTransaction((HashMap)sqlMap);
		}
		else
		{
			sql = "update LOGIN_PRO_COMMISSION set status='N',CANCEL_DATE=to_date(?,'dd-mm-yyyy'),END_DATE=to_date(?,'dd-mm-yyyy')-1,ENTRY_DATE=(select sysdate from dual) where AGENCY_CODE=(select AGENCY_CODE from login_master where login_id=?) and PRODUCT_ID=? and OPEN_COVER_NO=? and BRANCH_CODE=? and amend_id=(select max(amend_id) from LOGIN_PRO_COMMISSION where AGENCY_CODE=(select AGENCY_CODE from login_master where login_id=?) and PRODUCT_ID=? and OPEN_COVER_NO=? and BRANCH_CODE=?)";
			args = new String[10];
			args[0] = cdate;
			args[1] = cdate;
			args[2] = loginId;
			args[3] = pid;
			args[4] = openNo;
			args[5] = loginBra;
			args[6] = loginId;
			args[7] = pid;
			args[8] = openNo;
			args[9] = loginBra;
			runner.multipleUpdation(sql,args);
		}
		logger.debug(EXIT);
		
	}
	
	public void insertProCommissionDetailsOthers(final Map proHash)throws BaseException
	{
		logger.info("insertProCommissionDetailsOthers ProCommossionBean method()");
		logger.debug(ENTER);
		int amendId = 0;
		String agencyCode ="";agencyCode = (String)proHash.get("agencyCode");
		String loginBra = ""; loginBra = (String)proHash.get("admBranch");
		String pid = ""; pid = (String)proHash.get("pid");
		String openNo = ""; openNo = (String)proHash.get("openCoverNo");
		String sdate = ""; sdate = (String)proHash.get("sdate");
		String edate = ""; edate = (String)proHash.get("edate");
		String cdate = ""; cdate = (String)proHash.get("cdate");
		String proCommission = ""; proCommission = (String)proHash.get("proCommission");
		String proStaus = ""; proStaus = (String)proHash.get("proStaus");
		String args[] = new String[0];
		String sql = "";
		Map sqlMap = null; 
		sqlMap = new HashMap();
		int mapCnt = 0;

		if(!proStaus.equalsIgnoreCase("Y")&&cdate.length()<=0)
		{
			amendId = getMaxAmendIdOthers(agencyCode,loginBra,pid,openNo);
			sql = "update LOGIN_PRO_COMMISSION set status='N' where AGENCY_CODE=? and PRODUCT_ID=? and OPEN_COVER_NO=? and BRANCH_CODE=? and amend_id=?";
			args = new String[5];
			args[0] = agencyCode;
			args[1] = pid;
			args[2] = openNo;
			args[3] = loginBra;
			args[4] = Integer.toString(amendId-1);
			//runner.multipleUpdation(sql,args);
			sqlMap.put(QUERY+mapCnt,sql);
			sqlMap.put(ARGS+mapCnt++,args );
			sql = "insert into LOGIN_PRO_COMMISSION(AGENCY_CODE,OA_CODE,OPEN_COVER_NO,PRODUCT_ID,AMEND_ID,BRANCH_CODE,START_DATE,END_DATE,ENTRY_DATE,PRO_COMMISSION,STATUS) values(?,?,?,?,?,?,to_date(?,'dd-mm-yyyy'),to_date(?,'dd-mm-yyyy'),(select sysdate from dual),?,'Y')";
			args = new String[9];
			args[0] = agencyCode;
			args[1] = agencyCode;
			args[2] = openNo;
			args[3] = pid;
			args[4] = Integer.toString(amendId);
			args[5] = loginBra;
			args[6] = sdate;
			args[7] = edate;
			args[8] = proCommission;
			//runner.multipleInsertion(sql,args);
			sqlMap.put(QUERY+mapCnt,sql);
			sqlMap.put(ARGS+mapCnt++,args );
			
			sqlMap.put("Count",Integer.toString(mapCnt));
			runner.multipleUpdateTransaction((HashMap)sqlMap);
		}
		else
		{
			sql = "update LOGIN_PRO_COMMISSION set status='N',CANCEL_DATE=to_date(?,'dd-mm-yyyy'),END_DATE=to_date(?,'dd-mm-yyyy')-1,ENTRY_DATE=(select sysdate from dual) where AGENCY_CODE=? and PRODUCT_ID=? and OPEN_COVER_NO=? and BRANCH_CODE=? and amend_id=(select max(amend_id) from LOGIN_PRO_COMMISSION where AGENCY_CODE=? and PRODUCT_ID=? and OPEN_COVER_NO=? and BRANCH_CODE=?)";
			args = new String[10];
			args[0] = cdate;
			args[1] = cdate;
			args[2] = agencyCode;
			args[3] = pid;
			args[4] = openNo;
			args[5] = loginBra;
			args[6] = agencyCode;
			args[7] = pid;
			args[8] = openNo;
			args[9] = loginBra;
			runner.multipleUpdation(sql,args);
		}
		logger.debug(EXIT);
		
	}
	
	public int getMaxAmendId(final String loginId,final String loginBra,final String pid,final String openNo)throws BaseException
	{
		logger.info("getMaxAmendId ProCommossionBean method()");
		logger.debug(ENTER);
		String sql = ""; sql = "select nvl(max(AMEND_ID),0) from LOGIN_PRO_COMMISSION where AGENCY_CODE=(select AGENCY_CODE from login_master where login_id=?) and BRANCH_CODE=? and PRODUCT_ID=? and OPEN_COVER_NO=?";
		int maxId = 0;
		String args[] = new String[4];
		args[0] = loginId;
		args[1] = loginBra;
		args[2] = pid;
		args[3] = openNo;
		String temp = runner.singleSelection(sql,args);
		if(temp!=null && temp.length()>=1){
			maxId = Integer.parseInt(temp);
		}
		maxId = maxId + 1;
		logger.debug(EXIT);
		
		return maxId;
	}
	
	public int getMaxAmendIdOthers(final String agencyCode,final String loginBra,final String pid,final String openNo)throws BaseException
	{
		logger.info("getMaxAmendIdOthers ProCommossionBean method()");
		logger.debug(ENTER);
		String sql = ""; sql = "select nvl(max(AMEND_ID),0) from LOGIN_PRO_COMMISSION where AGENCY_CODE=? and BRANCH_CODE=? and PRODUCT_ID=? and OPEN_COVER_NO=?";
		int maxId = 0;
		String args[] = new String[4];
		args[0] = agencyCode;
		args[1] = loginBra;
		args[2] = pid;
		args[3] = openNo;
		String temp = ""; temp = runner.singleSelection(sql,args);
		if(temp!=null && temp.length()>=1)	{
			maxId = Integer.parseInt(temp);
		}
		maxId = maxId + 1;
		logger.debug(EXIT);
		
		return maxId;
	}
	
}// Class