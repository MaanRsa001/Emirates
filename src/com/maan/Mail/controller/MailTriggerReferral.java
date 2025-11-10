package com.maan.Mail.controller;

import org.apache.logging.log4j.Logger;

import com.maan.common.LogUtil;

public class MailTriggerReferral implements Runnable{
	private static final Logger logger = LogUtil.getLogger(MailTriggerReferral.class);
	String emailList[], cc1Address[];
	String emailSubjectTxt, emailMsgTxt, fromName, basePaths, brokerBra;
	
	public MailTriggerReferral(final String recipients[], final String ccreceipients[], final String subject, final String message,
			  final String broCompanyName, final String path, final String branch){
		/*this.emailList=emailList;
		this.cc1Address=cc1Address;
		this.emailSubjectTxt=emailSubjectTxt;
		this.emailMsgTxt=emailMsgTxt;
		this.fromName=fromName;
		this.basePaths=basePaths;
		this.brokerBra=brokerBra;*/
		
		this.emailList=recipients;
		this.cc1Address=ccreceipients;
		this.emailSubjectTxt=subject;
		this.emailMsgTxt=message;
		this.fromName=broCompanyName;
		this.basePaths=path;
		this.brokerBra=branch;
	}
	public void run(){
		try{
			System.out.println("Thread started");
			new com.maan.Mail.controller.mailController().postMail(emailList,cc1Address,emailSubjectTxt, emailMsgTxt.toString(), fromName,basePaths,brokerBra);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}