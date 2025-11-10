package com.maan.Mail.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Logger;

import com.maan.Mail.DAO.MailInformation;
import com.maan.Mail.DAO.MailerBean;
import com.maan.adminnew.common.CommonService;
import com.maan.common.LogUtil;
import com.maan.webservice.WebServicePolicyInfo;

public class MailControllerNew {
	private static final Logger logger = LogUtil.getLogger(MailControllerNew.class);
	public String guoteMailGeneration(WebServicePolicyInfo info, String mailFrom, String refStatus, String toMailAddress) {
		logger.info("processRequest mailcontroller method()");
		try{
			String emailSubjectTxt;
			/*String toId = "";
			String ccId = "";
			String mess = "";*/
			String emailSub = "";
			final MailerBean mailer =new MailerBean();
			final Map<String, String> freDetails = new HashMap<String, String>();
			String quoteNo = info.getQuoteNo();
			String loginId;
			String productId;
			String applicationNo = info.getApplicationNo();
			//String openCoverNo;
			if(applicationNo == null)
			{
				mailer.setQuoteNo(quoteNo);
				mailer.getQuoteDetails("Quote");
				applicationNo = mailer.getApplicationNo();
				loginId = mailer.getLoginId();
				productId = mailer.getProductId();
				//openCoverNo = mailer.getOpenCoverNo();
			}else{
				mailer.setApplicationNo(applicationNo);
				mailer.getQuoteDetails("Application");
				applicationNo = mailer.getApplicationNo();
				loginId = mailer.getLoginId();
				productId = mailer.getProductId();
				//openCoverNo = mailer.getOpenCoverNo();
				quoteNo =mailer.getQuoteNo();
			}

			final String brokerBra = info.getBranchCode();
			String cid="";
			String decimalPlace ="";
			String CurrencyType="";
			CurrencyType=info.getBrokerCurrencyType();
			cid = info.getBrokerOriginatingCountryId();
			decimalPlace = mailer.isNull(info.getBrokerDecimalPlace(),"2");
			final java.text.NumberFormat fmt1 = new java.text.DecimalFormat(mailer.getFormatDigit(decimalPlace));
			mailer.setQuoteNo(quoteNo);
			mailer.setLoginId(loginId);
			mailer.setApplicationNo(applicationNo);

			logger.info("royal test..mailFrom......."+mailFrom);

			final MailInformation mailBean = new MailInformation();
			final StringBuffer emailMsgTxt = new StringBuffer();
			emailMsgTxt.append(mailBean.getQuoteMailMgtTxt(quoteNo));
			final Map<String, String> mailInfo = (Map<String, String>)mailBean.getDetailedTable(brokerBra,cid,quoteNo,loginId,applicationNo,CurrencyType,fmt1,mailFrom,freDetails,"");
			emailMsgTxt.append((String)mailInfo.get("content"));
			//emailMsgTxt.append(mailBean.getCluausesDetails(brokerBra,cid,quoteNo,loginId,applicationNo,productId,"",openCoverNo));
			emailMsgTxt.append("<br></font></html></head></html>");
			final String remarks = ((String)mailInfo.get("remarks")).replace("~", ",");

			final String basePaths = info.getBasePath()+"MailServerInfo/MailServerInfo.xml"; 
			emailSubjectTxt = mailBean.getEmailSubjectTxt(remarks,mailFrom,emailSub,productId,quoteNo,(String)mailInfo.get("ClientName"));
			
			String fromName = (String)mailInfo.get("brokerName");
			/*final String[] emailList = mailBean.getEmailList(remarks,"",admrem,adminid,mailId,"None",mailIds,toId);
			final String cc1Address[] = mailBean.getCcAddress(mailFrom,brokerBra,ccId,scrnFrom,loginId);*/

			Map<String, String[]> quoteMailList = mailBean.quoteMailList(applicationNo,brokerBra,"quoteMail", toMailAddress);
			final String[] emailList = quoteMailList.get("TO_ADDRESS");
			final String[] cc1Address = quoteMailList.get("CC_ADDRESS");

			//final mailController smtpMailSender = new mailController();
			//smtpMailSender.postMail( emailList,cc1Address,emailSubjectTxt, emailMsgTxt.toString(), fromName,basePaths,brokerBra);
			Runnable hello = new MailTriggerReferral(emailList,cc1Address,emailSubjectTxt, emailMsgTxt.toString(), fromName,basePaths,brokerBra);
			Thread thread1 = new Thread(hello);
			thread1.setDaemon(true);
			thread1.setName("ReferralMail");
			System.out.println("Started Mail Trigger...");
			thread1.start();
		}catch(Exception e){
			logger.debug(e);
		}
		
		return "";
	}

	public String referralMail(WebServicePolicyInfo info, String mailFrom, String refStatus)
	{
		logger.info("processRequest mailcontroller method()");
		try{
			String emailSubjectTxt;
			String emailSub = "";
			final MailerBean mailer =new MailerBean();
			String quoteNo = info.getQuoteNo();
			String loginId;
			String productId;
			String applicationNo = info.getApplicationNo();
			String fromName = "";
			if(applicationNo == null)
			{
				mailer.setQuoteNo(quoteNo);
				mailer.getQuoteDetails("Quote");
				applicationNo = mailer.getApplicationNo();
				loginId = mailer.getLoginId();
				productId = mailer.getProductId();
			}else{
				mailer.setApplicationNo(applicationNo);
				mailer.getQuoteDetails("Application");
				applicationNo = mailer.getApplicationNo();
				loginId = mailer.getLoginId();
				productId = mailer.getProductId();
				quoteNo =mailer.getQuoteNo();
			}

			final String brokerBra = info.getBranchCode();
			
			mailer.setQuoteNo(quoteNo);
			mailer.setLoginId(loginId);
			mailer.setApplicationNo(applicationNo);

			logger.info("royal test..mailFrom......."+mailFrom);

			final MailInformation mailInformation = new MailInformation();
			final StringBuffer emailMsgTxt = new StringBuffer();
			
			Map<String,Object> mailInfo = mailInformation.getReferralMailDatas(applicationNo,brokerBra);
			final String remarks = mailInfo.get("REFERRAL_REMARKS").toString().replaceAll("~", ",");
			
			emailMsgTxt.append(mailInformation.getReferralEmailMsgTxt(mailInfo));
			emailSubjectTxt = mailInformation.getEmailSubjectTxt(remarks,mailFrom,emailSub,productId,quoteNo,(String)mailInfo.get("CUSTOMER_NAME"));
			
			if("1".equalsIgnoreCase(mailInfo.get("APPLICATION_ID").toString())) {
				fromName = mailInfo.get("BROKER_COMPANY_NAME").toString();
			}
			else {
				fromName = mailInfo.get("ISSUER_NAME").toString();
			}
			Map<String, String[]> quoteMailList = mailInformation.quoteMailList(applicationNo,brokerBra,"referralRequest","");
			final String[] emailList = quoteMailList.get("TO_ADDRESS");
			final String[] cc1Address = quoteMailList.get("CC_ADDRESS");
			
			final String basePaths = info.getBasePath()+"MailServerInfo/MailServerInfo.xml"; 
			Runnable hello = new MailTriggerReferral(emailList,cc1Address,emailSubjectTxt, emailMsgTxt.toString(), fromName,basePaths,brokerBra);
			Thread thread1 = new Thread(hello);
			thread1.setDaemon(true);
			thread1.setName("ReferralMail");
			System.out.println("Started Mail Trigger...");
			thread1.start();
		}catch(Exception e){
			logger.debug(e);
		}
		
		return "";
	}
	public String referralResponseMailGeneration(WebServicePolicyInfo info, String mailFrom, String refStatus) {
		logger.info("processRequest mailcontroller method()");
		try{
			String emailSubjectTxt;
			String emailSub = "";
			final MailerBean mailer =new MailerBean();
			String quoteNo = info.getQuoteNo();
			String loginId;
			String productId;
			String applicationNo = info.getApplicationNo();
			String fromName = "Emirates Insurance";
			if(applicationNo == null)
			{
				mailer.setQuoteNo(quoteNo);
				mailer.getQuoteDetails("Quote");
				applicationNo = mailer.getApplicationNo();
				loginId = mailer.getLoginId();
				productId = mailer.getProductId();
			}else{
				mailer.setApplicationNo(applicationNo);
				mailer.getQuoteDetails("Application");
				applicationNo = mailer.getApplicationNo();
				loginId = mailer.getLoginId();
				productId = mailer.getProductId();
				quoteNo =mailer.getQuoteNo();
			}

			final String brokerBra = info.getBranchCode();
			
			mailer.setQuoteNo(quoteNo);
			mailer.setLoginId(loginId);
			mailer.setApplicationNo(applicationNo);

			logger.info("royal test..mailFrom......."+mailFrom);

			final MailInformation mailInformation = new MailInformation();
			final StringBuffer emailMsgTxt = new StringBuffer();
			
			Map<String,Object> mailInfo = mailInformation.getReferralMailDatas(applicationNo,brokerBra);
			final String remarks = mailInfo.get("REFERRAL_REMARKS").toString().replaceAll("~", ",");
			
			emailMsgTxt.append(mailInformation.getReferralResEmailMsgTxt(mailInfo,refStatus));
			emailSubjectTxt = mailInformation.getEmailSubjectTxt(remarks,mailFrom,emailSub,productId,quoteNo,(String)mailInfo.get("CUSTOMER_NAME"));
			
			Map<String, String[]> quoteMailList = mailInformation.quoteMailList(applicationNo,brokerBra,"referralResponse","");
			final String[] emailList = quoteMailList.get("TO_ADDRESS");
			final String[] cc1Address = quoteMailList.get("CC_ADDRESS");
			
			final String basePaths = info.getBasePath()+"MailServerInfo/MailServerInfo.xml"; 
			Runnable hello = new MailTriggerReferral(emailList,cc1Address,emailSubjectTxt, emailMsgTxt.toString(), fromName,basePaths,brokerBra);
			Thread thread1 = new Thread(hello);
			thread1.setDaemon(true);
			thread1.setName("ReferralMail");
			System.out.println("Started Mail Trigger...");
			thread1.start();
		}catch(Exception e){
			logger.debug(e);
		}
		
		return "";
	}
	
	public String BranchReportMail(String filePath, String branchName, String branchCode) {
		logger.info("processRequest mailcontroller method()");
		try{
			final MailInformation mailInformation = new MailInformation();
			final StringBuffer emailMsgTxt = new StringBuffer();
			
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy h.mm.ss a");
			String date = sdf.format(cal.getTime());
			
			final String testMsg = CommonService.getAppEnvironment();
			final String emailSubjectTxt = "Day End Report | Generated on " + date + " | Branch : " + branchName + testMsg;
			
			final String basePath=CommonService.getApplicationPath();
			final String basePaths = basePath + "MailServerInfo/MailServerInfo.xml";
			
			Map<String, String[]> quoteMailList = mailInformation.quoteMailList("",branchCode,"branchReport","");
			final String[] emailList = quoteMailList.get("TO_ADDRESS");
			final String[] cc1Address = quoteMailList.get("CC_ADDRESS");
			
			final File attachment = new File(filePath);
			
			emailMsgTxt.append(mailInformation.branchReportMailContent(branchName));
			
			mailController mail = new mailController();
			mail.postMail(emailList, cc1Address, emailSubjectTxt, emailMsgTxt.toString(), "", basePaths, branchCode, attachment);
		}catch(Exception e){
			logger.debug(e);
		}
		
		return "";
	}

	public void referralIntgMail(String policyNo, String mailFrom, String refStatus) {
		logger.info("processRequest mailcontroller method()");
		try{
			String emailSubjectTxt;
			String emailSub = "";
			final MailerBean mailer =new MailerBean();
			String loginId="";
			String productId;
			String fromName = "";
			String clientName ="";
			String intgStatus="";

			
			mailer.setLoginId(loginId);

			logger.info("royal test..mailFrom......."+mailFrom);

			final MailInformation mailInformation = new MailInformation();
			final StringBuffer emailMsgTxt = new StringBuffer();
			
			Map<String,Object> mailInfo = mailInformation.getReferralIntgMailDatas(policyNo);
			clientName = mailInfo.get("CUSTOMER_NAME")==null?"":mailInfo.get("CUSTOMER_NAME").toString();
			emailMsgTxt.append(mailInformation.getIntgEmailMsgTxt(mailInfo));
			emailSubjectTxt = mailInformation.getEmailSubjectIntgTxt(policyNo, clientName, intgStatus);
			
			fromName="eCargo Alert";
			Map<String, String[]> quoteMailList = mailInformation.quoteMailList("","","integration","");
			final String[] emailList = quoteMailList.get("TO_ADDRESS");
			final String[] cc1Address = quoteMailList.get("CC_ADDRESS");
			
			Runnable hello = new MailTriggerReferral(emailList,cc1Address,emailSubjectTxt, emailMsgTxt.toString(), fromName,"","");
			Thread thread1 = new Thread(hello);
			thread1.setDaemon(true);
			thread1.setName("ReferralMail");
			System.out.println("Started Mail Trigger...");
			thread1.start();
		}catch(Exception e){
			logger.debug(e);
		}
		
		return;
	}
}
