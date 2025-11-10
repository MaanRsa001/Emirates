package com.maan.webservice;

import com.maan.webservice.service.PolicyGenerationService;

public class MailTriggerIntegration implements Runnable{
	String policyNo;
	String applicationNo;
	String productId;
	
	public MailTriggerIntegration(String policyNo,String applicationNo, String productId){
		this.policyNo=policyNo;
		this.applicationNo=applicationNo;
		this.productId=productId;
	}
	public void run(){
		new PolicyGenerationService().callIntegrationWebservice(applicationNo,productId,policyNo);
	}
}