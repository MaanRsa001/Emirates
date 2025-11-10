package com.maan.webservice;

import com.maan.Mail.controller.MailControllerNew;
import com.maan.webservice.service.PolicyGenerationService;

class MailTriggerQuote implements Runnable{
	WebServicePolicyInfo info=new WebServicePolicyInfo();
	String userType;
	String refStatus;
	
	public MailTriggerQuote(WebServicePolicyInfo info, String userType, String refStatus){
		this.info=info;
		this.userType=userType;
		this.refStatus=refStatus;
	}
	public void run(){
		//new PolicyGenerationService().mailGeneration(info,userType,"none");
		new MailControllerNew().referralMail(info,userType,"none");
	}
}