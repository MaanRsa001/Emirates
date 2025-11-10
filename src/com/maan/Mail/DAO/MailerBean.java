package com.maan.Mail.DAO;



import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Logger;

import com.maan.common.LogUtil;
import com.maan.common.exception.BaseException;
import com.maan.services.util.runner;

public class MailerBean 
{
	private static final Logger logger = LogUtil.getLogger(MailerBean.class);
	private String policyNo;
	private String quoteNo;
	private String loginId;
	private String applicationNo;
	private String fromId;
	private String ccId;
	private String toId;
	private String emailSub;
	private String mess;
	private String productId;
	private String openCoverNo;

	
	public void setLoginId(final String login) {
		this.loginId = login;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setApplicationNo(final String applicationNo) {
		this.applicationNo = applicationNo;
	}
	public String getApplicationNo() {
		return applicationNo;
	}
	final static transient private String ENTER = "- Enter";
	final static transient private String EXIT = "- Exit";
	final static transient private String EMAIL = "email";
	final static transient private String USERNAME = "username";
	
	public String getAdminMail(final String loginBranch) throws BaseException{
		logger.info("getAdminMail mailerBean method()");
		logger.debug(ENTER);
			final String values[] = {loginBranch};
			final String adminId = runner.singleSelection("select email_to from mail_details where mail_no = 1 and BRANCH_CODE=?",values);
		logger.debug(EXIT);
		
		return adminId;
	}
	public String getEMailDetailsAttach() throws BaseException{
		logger.info("getEMailDetailsAttach mailerBean method()");
		logger.debug(ENTER);
			final String values[] = {policyNo};
			final String sqlQuery = "select nvl(email,'NO') from personal_info where customer_id = (select customer_id from login_user_details where agency_code= (select oa_code from login_master where login_id= (select login_id From position_master where quote_no = ? )))";
			String emailId =runner.singleSelection(sqlQuery,values);
			if (emailId.length()<=0) {
				emailId = "NO";
			}
		logger.debug(EXIT);
			
		return emailId;
	}
	public String geteMailDetails() throws BaseException{
		logger.info("geteMailDetails mailerBean method()");
		logger.debug(ENTER);
			final String values[] = {policyNo};
			final String sqlQuery = "select nvl(email,'NO') from personal_info where customer_id = (select customer_id From " +
					"position_master where quote_no = ?)";
			String emailId =runner.singleSelection(sqlQuery,values);
			if (emailId.length()<=0) {
				emailId = "NO";
			}
		logger.debug(EXIT);
			
		return emailId;
	}
	public void getQuoteDetails(final String option) throws BaseException{
		logger.info("getAppliNo mailerBean method()");
		logger.debug(ENTER);
			final String values[] = {(option.equalsIgnoreCase("Quote")?quoteNo:applicationNo)};
			final String sqlQuery = "select application_no,login_id,product_id,nvl(open_cover_no,'0'),quote_no from position_master " +
					"where "+((option.equalsIgnoreCase("Quote")?"quote_no":"application_no"))+" = ? ";
			final String applNo[][] = runner.multipleSelection(sqlQuery,values);
			if(applNo.length>0){
				applicationNo = applNo[0][0];
				loginId = applNo[0][1];
				productId = applNo[0][2];
				openCoverNo = applNo[0][3];
				quoteNo = applNo[0][4];
			}
		logger.debug(EXIT);
			
	}
	public Map geteMailQuoteNo()throws BaseException{
		logger.info("geteMailQuoteNo mailerBean method()");
		logger.debug(ENTER);
			Map mailDetails = new HashMap();
			final String values[] = {quoteNo,quoteNo};
			final String sqlQuery = "select nvl(pf.email,''),initcap(pf.first_name), pf.address1, pf.address2, initcap(pf.country), pf.telephone, pf.title,pf.company_name, to_char(pm.entry_date,'dd-mm-YYYY'),initcap(pf.EMIRATE), pf.POBOX " +
					" from personal_info pf, position_master pm where pf.customer_id= pm.customer_id " +
					"and pf.customer_id = (select customer_id from position_master where quote_no = ?) and pm.quote_no=? ";
			mailDetails = getHashDetails(mailDetails,values,sqlQuery);
		logger.debug(EXIT);
			
		return mailDetails; 
	}
	public Map getHashDetails(final Map details,final String values[],final String sqlQuery)throws BaseException{
		final String result[][] = runner.multipleSelection(sqlQuery,values);
		if(result.length>0) {
				details.put(EMAIL, isNull(result[0][0],"0"));
				details.put("firstname", isNull(result[0][1], result[0][7]));
				details.put("quotdate", isNull(result[0][8],""));
				details.put("title", isNull(result[0][6],"M/S"));
				details.put("comp", isNull(result[0][7],result[0][1]));
				details.put("addr1", isNull(result[0][2],""));
				details.put("addr2", isNull(result[0][3],""));
				details.put("count", isNull(result[0][4],""));
				details.put("tele", result[0][5]==null?"":"TEL "+result[0][5]);
				details.put("city", isNull(result[0][9],""));
				details.put("pobox", result[0][10]==null?"":"P O BOX "+result[0][10]);
				
			}
		return details;
	}
	public String isNull(final String content,final String value)throws BaseException{
		final StringBuffer contents = new StringBuffer();
		if(content==null||content.length()<=0){
			contents.append(value);
		}
		else if(content.equalsIgnoreCase("select")){
			contents.append(value);
		}
		else{
			contents.append(content);
		}
		return contents.toString();
	}
	public Map getPortDetails()throws BaseException{
		logger.info("getPortDetails mailerBean method()");
		logger.debug(ENTER);
			final Map portDetails = new HashMap();
			final String[][] result = runner.multipleSelection("select host_name,user_name,password,remarks,from_address from mail_constant_values");
			portDetails.put("PortDetails", result);
				if(result.length>0) {
					portDetails.put("hostname", result[0][0]);
					portDetails.put(USERNAME, result[0][1]);
					portDetails.put("password", result[0][2]);
					portDetails.put("webaddress", result[0][3]);
					portDetails.put("Address", result[0][4]);
				}
		logger.debug(EXIT);
			
		return portDetails;
	}
	public Map getRemarks()throws BaseException{
		logger.info("getRemarks mailerBean method()");
		logger.debug(ENTER);
			final Map mailDetails = new HashMap();
			final String values[] ={quoteNo};
			final String sqlQuery = "select pf.title,pf.first_name,pf.last_name, pf.telephone, pf.fax, pf.email from personal_info pf " +
					"where pf.customer_id= (select customer_id from broker_company_master where agency_code = " +
					"(select oa_code from login_master where login_id =(select login_id from position_master where quote_no=?)))";
			final String result[][] = runner.multipleSelection(sqlQuery,values);
			mailDetails.put("Remarks", result);
			if (result.length > 0) {
					mailDetails.put("mailId", result[0][5]);
					mailDetails.put("tit", result[0][0]);
					mailDetails.put("firstname", result[0][1]);
					mailDetails.put("lastname", result[0][2]);
					mailDetails.put("telephone", result[0][3]);
					mailDetails.put("faxno", result[0][4]);
			}
			final String remarks = runner.singleSelection("select remarks from marine_data where application_no = (select application_no from position_master where quote_no = ?)",values);
			mailDetails.put("remarks", remarks);
			final String valuess[] = {remarks};
			final String emailId =  runner.singleSelection("select email_cc from mail_details where email_type = ? ",valuess);
			mailDetails.put("emailcc", emailId);
		logger.debug(EXIT);
			
		return mailDetails;
	}

	public Map getOpencoverBrokerInformation()throws BaseException{
		logger.info("getOpencoverBrokerInformation mailerBean method()");
		logger.debug(ENTER);
			final Map broker = new HashMap();
			final String values[]={quoteNo};
			final String sqlQuery = "select company_name,contact_person,address1,city,pobox, phone, fax from broker_company_master where agency_code = (select oa_code from login_master where login_id = (select login_id from position_master where quote_no = ?))";
			final String result[][] = runner.multipleSelection(sqlQuery,values);
			if (result.length > 0)
			{
					broker.put("company_name", result[0][0]);
					broker.put("contact_person", result[0][1]);
					broker.put("address1", result[0][2]);
					broker.put("city", result[0][3]);
					broker.put("pobox", result[0][4]);
					broker.put("phone", result[0][5]);
					broker.put("fax", result[0][6]);
			}
		logger.debug(EXIT);
			
		return broker;
	}
	public Map getFreightUserInformation(final String quoteNo)throws BaseException{
		logger.info("getFreightUserInformation mailerBean method()");
		logger.debug(ENTER);
			final Map freight = new HashMap();
			final String values[] = {quoteNo};
			final String sqlQuery = "select a.username, b.email, nvl(b.first_name,b.company_name), nvl(b.address1,b.address2) from " +
					"login_master a, personal_info b where a.agency_code in	(select AGENCY_CODE from LOGIN_USER_DETAILS where" +
					" LOGIN_ID= (select login_id from position_master where quote_no = ?) and a.agency_code = b.agency_code) " +
					"and a.usertype='Freight' and a.agency_code = b.agency_code";
			final String result[][] = runner.multipleSelection(sqlQuery,values);
			if (result.length > 0) 
			{
					freight.put(USERNAME, result[0][0]);
					freight.put(EMAIL, result[0][1]);
					freight.put("first_name", result[0][2]);
					freight.put("address1", result[0][3]);
			}
		logger.debug(EXIT);
			
		return freight;
	}
	public Map getFreightBrokerLoginId(final String loginId)throws BaseException{
		logger.info("getFreightBrokerLoginId mailerBean method()");
		logger.debug(ENTER);
			final String values[] = {loginId};
			final String sqlQuery = "select a.company_name, b.email from broker_company_master a, personal_info b where a.agency_code =(select oa_code from login_master where login_id = ?)and a.agency_code = b.agency_code";
			final Map freight = freightDetails(sqlQuery,values);
		logger.debug(EXIT);
			
		return freight;
	}
	public Map getFreightUserLoginId(final String quoteNo)throws BaseException{
		logger.info("getFreightUserLoginId mailerBean method()");
		logger.debug(ENTER);
			final Map freightuser = new HashMap();
			int count = 0;
			final String sqlQuery = "select  a.username, b.email,c.quote_no from login_master a, personal_info b, position_master c where a.agency_code in(select AGENCY_CODE from LOGIN_USER_DETAILS where LOGIN_ID in (select login_id from position_master " +
			"where quote_no in ("+quoteNo+"))and a.agency_code = b.agency_code) and a.usertype='Freight' and a.agency_code = b.agency_code " +
			"and b.login_id = c.login_id and a.login_id = c.login_id and c.quote_no in ("+quoteNo+")";
			final String asx[][] = runner.multipleSelection(sqlQuery);
			if(asx.length > 0) 
			{
				String quotesCount[] = new String[asx.length];
				for (int i = 0; i < asx.length; i++) 
				{
					quotesCount[i] = isNull(asx[i][2],"");
					freightuser.put("Name" + asx[i][2], asx[i][0]);
					freightuser.put(asx[i][1] + asx[i][2], asx[i][2]);
					if (freightuser.containsValue(asx[i][1])){
						freightuser.put(asx[i][1] + asx[i][2], asx[i][2]);
					}
					else{
						freightuser.put(EMAIL + count++, asx[i][1]);
					}
				}
				freightuser.put("countQuotesLen", Integer.toString(quotesCount.length));
				freightuser.put("countQuotes", quotesCount);
			}
			freightuser.put("count", Integer.toString(count));
		logger.debug(EXIT);
			
		return freightuser;
	}
	public Map getFreightBrokerInformation(final String quoteNo)throws BaseException{
		logger.info("getFreightBrokerInformation mailerBean method()");
		logger.debug(ENTER);
			final Map freight = new HashMap();
			final String values[]= {quoteNo};
			final String sqlQuery = "select a.company_name, b.email,nvl(b.first_name,b.last_name),nvl(b.address1,'') from broker_company_master a, personal_info b " +
					"where a.agency_code =(select oa_code from login_master where login_id = (select login_id from position_master where quote_no = ?)) " +
					"and a.agency_code = b.agency_code";
			final String result[][] = runner.multipleSelection(sqlQuery,values);
			if (result.length > 0) {
				freight.put(USERNAME, isNull(result[0][0],""));
				freight.put(EMAIL, isNull(result[0][1],""));
				freight.put("Name", isNull(result[0][2],""));
				freight.put("Address", isNull(result[0][3],""));
			}
		logger.debug(EXIT);
			
		return freight;
	}
	public Map freightDetails(final String sql,final String values[])throws BaseException{
		logger.info("freightDetails mailerBean method()");
		logger.debug(ENTER);
		final Map details = new HashMap();
			final String result[][] = runner.multipleSelection(sql,values);
			if (result.length > 0) {
				details.put(USERNAME, result[0][0]);
				details.put(EMAIL, result[0][1]);
			}
		logger.debug(EXIT);
			
		return details;
	}
	public Map getFreightUserBrokerMode(final String quoteNo)throws BaseException{
		logger.info("getFreightUserBrokerMode mailerBean method()");
		logger.debug(ENTER);
			final String values[] = {quoteNo};
			final String sqlQuery = "select a.username, b.email from login_master a, personal_info b where a.agency_code in	(select AGENCY_CODE from LOGIN_USER_DETAILS where LOGIN_ID= (select login_id from position_master where quote_no = ?)and a.agency_code = b.agency_code) and a.usertype='Freight' and a.agency_code = b.agency_code";
			final Map freight = freightDetails(sqlQuery,values);
		logger.debug(EXIT);
			
		return freight;
	}
	public String getPremiumProvision(final String user)throws BaseException{
		logger.info("getPremiumProvision mailerBean method()");
		logger.debug(ENTER);
			final String values[] = {user};
			final String result = runner.singleSelection("select PROVISION_FOR_PREMIUM from LOGIN_USER_DETAILS where LOGIN_ID=?",values);
		logger.debug(EXIT);
			
		return result;
	}
	public String getFreightStatus(final String quoteNo)throws BaseException{
		logger.info("getFreightStatus mailerBean method()");
		logger.debug(ENTER);
			final String values[] = {quoteNo};
			final String result = runner.singleSelection("select status from freight_position_master where quote_no =?",values);
		logger.debug(EXIT);
		
		return result;
	}
	public Map getQuoteInformation(final String quoteNo)throws BaseException{
		logger.info("getQuoteInformation mailerBean method()");
		logger.debug(ENTER);
			final Map quoteInfo = new HashMap();
			final String values[] = {quoteNo};
			final String sqlQuery = "select nvl(p.freight_status,''), nvl(f.status,''),nvl(f.allow_to_generate_policy_sts,'') from position_master p, freight_position_master f where p.quote_no = f.quote_no and p.quote_no = ?";
			final String result[][] = runner.multipleSelection(sqlQuery,values);
			quoteInfo.put("quotestatus", result);
			if (result.length > 0) {
				quoteInfo.put("freightstatus", result[0][0]);
				quoteInfo.put("quotestatus", result[0][1]);
				quoteInfo.put("policystatus", result[0][2]);
			}
		logger.debug(EXIT);
		
		return quoteInfo;
	}
	public Map getBrokerInformation() throws BaseException{
		logger.info("getBrokerInformation mailerBean method()");
		logger.debug(ENTER);
			final Map<String,String> broker = new HashMap<String,String>();
			final String values[] = {quoteNo};
			final String sqlQuery = "select nvl(company_name,' '),nvl(contact_person,' '),nvl(address1,' '),nvl(city,' '),pobox,phone,fax from broker_company_master where agency_code =  (select oa_code from login_master where login_id = (select login_id from position_master where quote_no = ?))";
			final String result[][] = runner.multipleSelection(sqlQuery,values);
			if (result.length > 0) {
				broker.put("company_name", isNull(result[0][0],""));
				broker.put("contact_person", isNull(result[0][1],""));
				broker.put("address1", isNull(result[0][2],""));
				broker.put("city",  isNull(result[0][3],""));
				broker.put("pobox", isNull(result[0][4],""));
				broker.put("phone", isNull(result[0][5],""));
				broker.put("fax", isNull(result[0][6],""));
			}
		logger.debug(EXIT);
		
		return broker;
	}
	public Map getReferralDetails(final String loginBra)throws BaseException{
		logger.info("getReferralDetails mailerBean method()");
		logger.debug(ENTER);
			final Map refer = new HashMap();
			final String values[]= {loginBra};
			final String sqlQuery = "select email_to, email_cc, email_subject, email_message, email_from_name, email_from_phoneno,email_from_faxno, email_type, remarks, status from mail_details " +
					"where BRANCH_CODE=? and MAIL_NO = '1'";
			final String result[][] = runner.multipleSelection(sqlQuery,values);
			refer.put("referal", result);
			if (result.length > 0) {
					refer.put("emailto", result[0][0]);
					refer.put("emailcc", result[0][1]);
					refer.put("emailsubject", result[0][2]);
					refer.put("emailmessage", result[0][3]);
					refer.put("emailname", result[0][4]);
					refer.put("emailphno", result[0][5]);
					refer.put("emailfax", result[0][6]);
					refer.put("emailtype", result[0][7]);
					refer.put("emailrema", result[0][8]);
					refer.put("emailstat", result[0][9]);
			}
		logger.debug(EXIT);
		
		return refer;
	}
	public Map getCommonTextA() throws BaseException{
		logger.info("getCommonTextA mailerBean method()");
		logger.debug(ENTER);
			final Map comtextA = new HashMap();
			final String sqlQueryx = "select email_to, email_cc, substr(email_message,1,9), substr(email_message,11,16), substr(email_message,28,96), email_from_name, email_from_phoneno, email_from_faxno, email_type, substr(remarks,1,537), substr(remarks,538,800) from mail_details where email_type  = 'Quote Accepted' and status = 'Y' ";
			final String result[][] = runner.multipleSelection(sqlQueryx);
			comtextA.put("comtextA", result);
			if (result.length > 0) {
				comtextA.put("email_to", result[0][0]);
				comtextA.put("email_cc", result[0][1]);
				comtextA.put("email_message1", result[0][2]);
				comtextA.put("email_message2", result[0][3]);
				comtextA.put("email_message3", result[0][4]);
				comtextA.put("email_from_name", result[0][5]);
				comtextA.put("email_from_phoneno", result[0][6]);
				comtextA.put("email_from_faxno", result[0][7]);
				comtextA.put("email_type", result[0][8]);
				comtextA.put("remarks1", result[0][9]);
				comtextA.put("remarks2", result[0][10]);
			}
		logger.debug(EXIT);
		
		return comtextA;
	}
	public Map getCommonTextB() throws BaseException{
		logger.info("getCommonTextB mailerBean method()");
		logger.debug(ENTER);
			final Map comtextB = new HashMap();
			final String sqlQueryx = "select email_to, email_cc, substr(email_message,1,9), substr(email_message,11,16), substr(email_message,28,75), email_from_name, email_from_phoneno, email_from_faxno, email_type, substr(remarks,1,537), substr(remarks,538,800) from mail_details where email_type  = 'Quote Rejected' and status = 'Y'";
			final String result[][] = runner.multipleSelection(sqlQueryx);
			comtextB.put("comtextB", result);
			if (result.length > 0) {
					comtextB.put("email_to", result[0][0]);
					comtextB.put("email_cc", result[0][1]);
					comtextB.put("email_message1", result[0][2]);
					comtextB.put("email_message2", result[0][3]);
					comtextB.put("email_message3", result[0][4]);
					comtextB.put("email_from_name", result[0][5]);
					comtextB.put("email_from_phoneno", result[0][6]);
					comtextB.put("email_from_faxno", result[0][7]);
					comtextB.put("email_type", result[0][8]);
					comtextB.put("remarks1", result[0][9]);
					comtextB.put("remarks2", result[0][10]);
			}
		logger.debug(EXIT);
		
		return comtextB;
	}
	public String marineRemarks() throws BaseException{
		logger.info("marineRemarks mailerBean method()");
		logger.debug(ENTER);
			final String values[] = {quoteNo};
			final String sqlQuery = "select nvl(remarks,'Normal') from marine_data where application_no = (select application_no from position_master where quote_no = ?) ";
			final String admrem = runner.singleSelection(sqlQuery,values);
		logger.debug(EXIT);
		
		return admrem;
	}
	public String adminRefStatus() throws BaseException{
		logger.info("marineRemarks mailerBean method()");
		logger.debug(ENTER);
			final String values[] = {quoteNo};
			final String sqlQuery = "select nvl(admin_referral_status,'N') from marine_data where application_no = (select application_no from position_master where quote_no = ?) ";
			final String admrem = runner.singleSelection(sqlQuery,values);
		logger.debug(EXIT);
		
		return admrem;
	}
	public Map getApprovalQuote() throws BaseException{
		logger.info("getApprovalQuote mailerBean method()");
		logger.debug(ENTER);
			String values[]={applicationNo};
			String sqlQuery = "select first_name, last_name,email,address1, address2, country,telephone, title, emirate, fax,customer_id, application_id from personal_info" +
					" where customer_id = (select customer_id from login_user_details where login_id = (select oa_code from login_master " +
					"where login_id = (select login_id from position_master where application_no = ?))) and application_id=2";
			final Map approval = brokerDetails(values,sqlQuery);
			if (quoteNo == null) {
				values[0]=applicationNo;
				sqlQuery = "select remarks, quote_no from position_master where application_no =? ";
			} else {
				values[0]=quoteNo;
				sqlQuery = "select remarks, quote_no from position_master where quote_no = ? ";
			}
			final String result[][] = runner.multipleSelection(sqlQuery,values);
			if(result.length>0){
				approval.put("remarks", result[0][0]);
				approval.put("Number", result[0][1]);
			}
		logger.debug(EXIT);
		
		return approval;
	}
	public String getAdminBrokerMailId(final String qno) throws BaseException{
		logger.info("getAdminBrokerMailId mailerBean method1()");
		logger.debug(ENTER);
			final StringBuffer sql = new StringBuffer(1000);
			final StringBuffer sqlArgs = new StringBuffer();
			final String values[]={qno};
			final String mailIds = runner.singleSelection("select login_id from position_master where quote_no = ? and freight_status='F'",values);
			if(mailIds.length() > 0){       
				sqlArgs.append(mailIds);
		          sql.append("select email from personal_info where agency_code =(select agency_code from login_master where agency_code in(select agency_code from broker_company_master " +
		          		"where agency_code=(select oa_code from login_master where login_id=?)))");
			}
			else{
				sqlArgs.append(qno);
				sql.append("select distinct(email) from personal_info where agency_code = (select agency_code from login_master where " +
						"login_id = (select login_id from position_master where quote_no = ?))");
			}
			final String args[] = {sqlArgs.toString()};
			final String broker = runner.singleSelection(sql.toString(),args);
		logger.debug(EXIT);
		
		return broker;
	}
	public String getAdminBrokerMailId(final String qno, final String redirection) throws BaseException{
		logger.info("getAdminBrokerMailId mailerBean method2()");
		logger.debug(ENTER);
			String sql;
			String values[]=new String[1];
			values[0]=qno;
			final String mailIds = runner.singleSelection("select login_id from position_master where quote_no = ? and freight_status='F'",values);
			final String rsaissuer = runner.singleSelection("select nvl(APPLICATION_ID,'1') from POSITION_MASTER where QUOTE_NO=?",values);
			if(mailIds.length() > 0 && redirection.equalsIgnoreCase("N")){
				values[0]=mailIds;
				sql = "select email from personal_info where agency_code =(select agency_code from login_master where agency_code in(select agency_code from broker_company_master where agency_code=(select oa_code from login_master where login_id=?)))";
			}
			else{
				values[0]=qno;
				if(rsaissuer.equalsIgnoreCase("1")){
					 sql = "select distinct(email) from personal_info where agency_code = (select agency_code from login_master where login_id = (select login_id from position_master where quote_no = ?))";
				}else{
					 sql = "select distinct(email) from personal_info where agency_code = (select agency_code from login_master where login_id = (select APPLICATION_ID from position_master where quote_no = ?))";
				}
			}
			final String broker = runner.singleSelection(sql,values);
		logger.debug(EXIT);
		
		return broker;
	}
	public String[][] getLoginByQno(final String qno) throws BaseException{
		logger.info("getLoginByQno mailerBean method()");
		logger.debug(ENTER);
			final String[] values = {qno};
			final String[][] result = runner.multipleSelection("select LOGIN_ID,PRODUCT_ID from POSITION_MASTER where QUOTE_NO=?",values);
		logger.debug(EXIT);
		
		return result;
	}
	public String getFreightRateOption(final String user)  throws BaseException{
		logger.info("getFreightRateOption mailerBean method()");
		logger.debug(ENTER);
			final String[] values = {user};
			final String result = runner.singleSelection("select FREIGHT_RATE_OPTION from LOGIN_USER_DETAILS where LOGIN_ID=?",values);
		logger.debug(EXIT);
		
		return result;
	}
	public String getFreightRedirection(final String login) throws BaseException{
		logger.info("getFreightRedirection mailerBean method()");
		logger.debug(ENTER);
			final String values[]={login};
			final String result = runner.singleSelection("select nvl(FREIGHT_ADMIN_OPTION,'N') from LOGIN_USER_DETAILS where login_id=?",values);
		logger.debug(EXIT);
		
		return result;
	}
	public String[][] getFreightAdminMail(final String loginBranch) throws BaseException{
		logger.info("getFreightAdminMail mailerBean method()");
		logger.debug(ENTER);
			final String values[] = {loginBranch};
			String result[][] = runner.multipleSelection("select nvl(email_to,' ') from mail_details where email_type = 'Admin Referral' and branch_code=?",values);
			if(result.length<0){
				result = runner.multipleSelection("select email_to from mail_details where mail_no = 1 and branch_code=?",values);
			}
		logger.debug(EXIT);
		
		return result;
	}
	//from customermailerbean -Rajesh
	public String getApplicationForQuote(final String qid) throws BaseException{
		logger.info("getApplicationForQuote mailerBean method()");
		logger.debug(ENTER);
			final String values[]={qid};
			final String appsNo = runner.singleSelection("select nvl(application_no,'0') from position_master where quote_no=?",values);
		logger.debug(EXIT);
		
		return appsNo;
	}
	public Map getBrokInformation() throws BaseException{
		logger.info("getBrokInformation mailerBean method()");
		logger.debug(ENTER);
			final String values[] = {applicationNo};
			final String sqlQuery = "select first_name,last_name,email,address1,address2,country,telephone,title,emirate,pobox,fax  from personal_info " +
			"where customer_id = (select distinct(customer_id) from login_user_details where agency_code = (select oa_code from login_master " +
			"where login_id = (select distinct login_id from tracking_master where application_no  = ? ))) and application_id = '2'";
			final Map broker = brokerDetails(values,sqlQuery);
		logger.debug(EXIT);
		
		return broker;
	}
	public Map brokerDetails(final String values[],final String sqlQuery) throws BaseException{
		logger.info("brokerDetails mailerBean method()");
		logger.debug(ENTER);
			final String result[][] = runner.multipleSelection(sqlQuery,values);
			final Map broker = new HashMap();
			if (result.length > 0) {
				broker.put("brfirnam", isNull(result[0][0],""));
				broker.put("brlasnam", isNull(result[0][1],""));
				broker.put("bremail", isNull(result[0][2],""));
				broker.put("braddr1", isNull(result[0][3],""));
				broker.put("braddr2", isNull(result[0][4],""));
				broker.put("brcountry", isNull(result[0][5],""));
				broker.put("telephone", isNull(result[0][6],""));
				broker.put("title", isNull(result[0][7],""));
				broker.put("emirate", isNull(result[0][8] ,""));
				broker.put("pbox", isNull(result[0][9],""));
				broker.put("fax", isNull(result[0][9],""));
			}
		logger.debug(EXIT);
		
		return broker;
	}
	public Map getBrokInformation(final String login) throws BaseException{
		logger.info("getBrokInformation mailerBean method()");
		logger.debug(ENTER);
			final String values[] = {login};
			final String sqlQuery = "select first_name,last_name,email,address1,address2,country,telephone,title,emirate,pobox,fax  from personal_info where customer_id = (select distinct(customer_id) from login_user_details where agency_code = (select oa_code from login_master where login_id = ?)) and application_id = '2'";
			final Map broker = brokerDetails(values,sqlQuery);
		logger.debug(EXIT);
		
		return broker;
	}
	public String getUserType(final String values[]){
		final String sqlQuery = "select usertype from login_master where login_id=(select login_id from tracking_master where application_no=? )";
		return runner.singleSelection(sqlQuery,values);
	}
	public Map geteMailQuoteNo1() throws BaseException{
		logger.info("geteMailQuoteNo1 mailerBean method()");
		logger.debug(ENTER);
			Map mailDetails = new HashMap();
			final String values[] = {applicationNo};
			mailDetails.put(EMAIL, new String[0][0]);
			final String type = getUserType(values);
			String sqlQuery = "";
			if (type.equalsIgnoreCase("Broker")) {
				sqlQuery = "select pf.email,initcap(pf.first_name), pf.address1, pf.address2, initcap(pf.country), pf.telephone, pf.title,pf.company_name, substr(pm.entry_date,0,10) from personal_info pf, position_master pm where pf.customer_id= pm.customer_id and pf.customer_id = (select customer_id from position_master where  application_no = ?)  and application_id = '1' ";
			}
			if (type.equalsIgnoreCase("User")) {
				sqlQuery = "select first_name, last_name,email, address1, address2,country, telephone, title, emirate, pobox  from personal_info where customer_id = (select customer_id from login_user_details where login_id =  (select login_id from position_master where application_no  = ? )) and application_id = '3'  ";
			}
			mailDetails = getHashDetails(mailDetails,values,sqlQuery);
		logger.debug(EXIT);
				
		return mailDetails;
	}
	public String getQuoteNumber()throws BaseException{
		logger.info("getQuoteNo mailerBean method()");
		logger.debug(ENTER);
			final String values[] = {applicationNo};
			final String quoteNo = runner.singleSelection("select quote_no from position_master where application_no = ? ",values);
		logger.debug(EXIT);
				
		return quoteNo;
	}
	public String validateText()throws BaseException {
		final com.maan.services.util.dataCollection data = new com.maan.services.util.dataCollection();
		final StringBuffer error = new StringBuffer();
		String values = data.validString(toId, false);
		if ("needed".equalsIgnoreCase(values)){
			error.append("<br>*");
			error.append(runner.getErrormsg("104"));
		}
		values = data.validString(emailSub, false);
		if ("needed".equalsIgnoreCase(values)){
			error.append("<br>*");
			error.append(runner.getErrormsg("106"));
		}
		values = data.validString(mess, false);
		if ("needed".equalsIgnoreCase(values)){
			error.append("<br>*");
			error.append(runner.getErrormsg("107"));
		}
		return error.toString();
	}
	public String getEmailSub() {
		return emailSub;
	}
	public void setEmailSub(final String emailSub) {
		this.emailSub = emailSub;
	}
	public String getMess() {
		return mess;
	}
	public void setMess(final String mess) {
		this.mess = mess;
	}
	public String getToId() {
		return toId;
	}
	public void setToId(final String toId) {
		this.toId = toId;
	}
	public String getCcId() {
		return ccId;
	}
	public void setCcId(final String ccId) {
		this.ccId = ccId;
	}
	public String getFromId() {
		return fromId;
	}
	public void setFromId(final String fromId) {
		this.fromId = fromId;
	}
	public String getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(final String policyNo) {
		this.policyNo = policyNo;
	}
	public String getQuoteNo() {
		return quoteNo;
	}
	public void setQuoteNo(final String quoteNo) {
		this.quoteNo = quoteNo;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getOpenCoverNo() {
		return openCoverNo;
	}
	public void setOpenCoverNo(String openCoverNo) {
		this.openCoverNo = openCoverNo;
	}
	public String getFormatDigit(final String decimalPlace)throws BaseException{
		final StringBuffer decimal = new StringBuffer();
		if(decimalPlace.equalsIgnoreCase("2")){
			decimal.append("##,##0.00");
		}else if(decimalPlace.equalsIgnoreCase("3")){
			decimal.append("##,##0.000");
		}else if(decimalPlace.equalsIgnoreCase("4")){
			decimal.append("##,##0.0000");
		}else{
			decimal.append("##,##0.00");
		}
		return decimal.toString();
	}
}
