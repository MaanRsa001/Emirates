package com.maan.webservice;

import java.util.Map;
import javax.servlet.http.HttpServlet;

import org.apache.logging.log4j.Logger;

import com.maan.Mail.controller.MailControllerNew;
import com.maan.common.LogUtil;
import com.maan.services.util.controller;
import com.maan.services.util.runner;
import com.maan.webservice.service.PolicyGenerationService;
import com.opensymphony.xwork2.ActionContext;

public class PolicyGenerationAction extends HttpServlet 
{
	private static final Logger logger = LogUtil.getLogger(PolicyGenerationAction.class);
private static final long serialVersionUID = 1L;
private String refNo="";
private String userType="";

WebServicePolicyInfo info;
PolicyGenerationService service=new PolicyGenerationService();
controller controller=new controller();
Map<String, Object> session=ActionContext.getContext().getSession();
//private String belongingBranch=(String)session.get("BelongingBranch");

public PolicyGenerationAction(String refNo)
{
	this.refNo=refNo;
	
	info=(WebServicePolicyInfo)service.getPolicyInfo(refNo);
}
public PolicyGenerationAction(String refNo, String userType)
{
	this.refNo=refNo;
	this.userType = userType;
	info=(WebServicePolicyInfo)service.getPolicyInfo(refNo);
	info.setUserType(userType);
}

String error="";
private String quoteNo="";

public String quoteGeneration()
{
	try
	{
		service.setInfo(info);
		//String[][] commodityInfo=service.getCommodityInfo(refNo, info.getBranchCode());
		String belongingBranch = service.getBelongingBranch(info.getBranchCode());
		String[][] commodityInfo=service.getCommodityInfo(refNo, belongingBranch);
		if(commodityInfo!=null && commodityInfo.length>0){
			service.insertUpdateCommodityInfo(refNo, commodityInfo,belongingBranch);
			info.setCustomerId(service.customerGeneration(info));
			/*if("11".equals(info.getProductId()))
				service.saveLcInfo(info);*/
			quoteNo=service.quoteGeneration(refNo, info, commodityInfo,belongingBranch);
		}
		if("Referal".equals(info.getReferralStatus())||"Y".equalsIgnoreCase(info.getAdminReferralYN())){
			info.setStatus("R");
			if(!"admin".equalsIgnoreCase(userType)){
				Runnable hello = new MailTriggerQuote(info,userType,"none");
			    Thread thread1 = new Thread(hello);
			    thread1.setDaemon(true);
			    thread1.setName("quote");
			    System.out.println("Started Mail Trigger...");
			    thread1.start();
			}
		}else{
			info.setStatus("Q");
			service.updateCommodityInfo(info);
		}
		service.insertOrUpdateMarinePolicyInfo(info);
	}catch(Exception e)
	{
		logger.debug("Exception in QuoteGeneration {"+e+"}");e.printStackTrace();
	}
	return error;
}
public String policyGeneration()
{
	try
	{
		String belongingBranch = service.getBelongingBranch(info.getBranchCode());
		String policyNo=service.policyGeneration(info,belongingBranch);
		logger.info("PolicyNo: "+policyNo);
		info.setPolicyNo(policyNo);
		if("Y".equalsIgnoreCase(info.getPdfGenerateStatus())||"Yes".equalsIgnoreCase(info.getPdfGenerateStatus()))
		{
			String filePath=service.generatePdf(info, "test".equalsIgnoreCase(info.getAppMode())?"TEST POLICY":"ORIGINAL COPY", "");
			info.setPolicyFilePath(filePath);
		}
		service.updatePolicyInfo(info);
		//Policy Integration
		String productId=(String) session.get("product_id");
		Runnable hello = new MailTriggerIntegration(info.getPolicyNo(),info.getApplicationNo(),productId);
	    Thread thread1 = new Thread(hello);
	    thread1.setDaemon(true);
	    thread1.setName("quote");
	    System.out.println("Started Integration Posting  Trigger...");
	    thread1.start();
		

	}catch(Exception e)
	{
		logger.info("Error in Policy Generation: "+e);
		logger.debug(e);
	}
	return info.getPolicyNo();
}
public String receiptGeneration(){
	String filePath="";
	try {
		logger.info("PolicyNo: "+info.getPolicyNo());
		if((info.getPolicyNo()==null || "".equals(info.getPolicyNo())) && (info.getQuoteNo()!=null && !"".equals(info.getQuoteNo())))
			info.setPolicyNo(runner.singleSelection("select nvl(policy_no,'') policy_no from position_master where quote_no='"+info.getQuoteNo()+"'"));
		filePath=service.generatePdf(info, "test".equalsIgnoreCase(info.getAppMode())?"TEST RECEIPT":"RECEIPT", "");
		
		///String Query="SELECT CUSTOMER_ID FROM POSITION_MASTER WHERE POLICY_NO='"+info.getPolicyNo()+"'";
		//String custId	= runner.singleSelection(Query);
		//String Query1="SELECT FIRST_NAME,EMAIL FROM PERSONAL_INFO WHERE CUSTOMER_ID='"+custId+"' AND AMEND_ID=(SELECT MAX(AMEND_ID) FROM PERSONAL_INFO WHERE CUSTOMER_ID='"+custId+"') ";
		//String[][] mail	= runner.multipleSelection(Query1);
		//String Query2="SELECT EMAIL_TO FROM MAIL_DETAILS WHERE BRANCH_CODE='"+info.getBranchCode()+"'";
		//String[][] mail2	= runner.multipleSelection(Query2);
		
		//String emailSubjectTxt="";
		//final StringBuffer emailMsgTxt = new StringBuffer(1000);
		//File attachment=new File(filePath);
		//String path=getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
		//String basePath=path.substring(1, path.indexOf("WEB-INF"));
		//final String basePaths = basePath+"MailServerInfo/MailServerInfo.xml"; 
		//String testMsg=basePath.indexOf("test")!=-1?" - FROM TEST ENVIRONMENT":"";
		//emailSubjectTxt = "Receipt Document";
		
		//emailMsgTxt.append("<b>Dear sir,</b><br><br> <font size = 3 face = 'arial'>Policy no:&nbsp;<b>");
		//emailMsgTxt.append(info.getPolicyNo());
		//emailMsgTxt.append("</b><br><br> <font size = 3 face = 'arial'>Customer Name:&nbsp;<b>");
		///emailMsgTxt.append(mail[0][0]);
		//emailMsgTxt.append("</b><br><br>with this mail for your kind persual.  Looking forward for your reply</font>");
	
		//final String[] emailList = mail[0][1].split(",");
		//final String cc1Address[] = mail2[0][0].split(",");
		//final String cc1Address[] = mail[0][1].split(",");
		//final mailController smtpMailSender = new mailController();
		/*try{
			//smtpMailSender.postMail( emailList,cc1Address,emailSubjectTxt+testMsg, emailMsgTxt.toString(), "Receipt Confirmation",basePaths,info.getBranchCode(), attachment);
		}catch (Exception e){
			e.printStackTrace();
		}*/
	}catch(Exception e) {
		logger.info("Error in Policy Generation: "+e);
		logger.debug(e);
	}
	//return info.getPolicyNo();
	return filePath;
}
public String draftGeneration()
{
	try
	{
		String filePath=service.generatePdf(info, "DRAFT", "draftMode");
		info.setDraftFilePath(filePath);
		service.updateDraftInfo(info);
	}catch(Exception e)
	{
		logger.info("Error in Policy Generation: "+e);
		logger.debug(e);
	}
	return error;
}
public Map<String, Object> calculate(String[][] commodity)
{
	Map<String, Object>premiumInfo=null;
	try
	{
		service.setInfo(info);
		String belongingBranch = service.getBelongingBranch(info.getBranchCode());
		/*premiumInfo=service.calculatePremium(info.getApplicationNo(), info.getModeOfTransport(), info.getCoverages(), info.getSaleTermValue(),
				commodity, info.getToleranceValue(), info.getProductId(), info.getOpenCoverPolicyNo(), info.getBranchCode(), info.getWsrcc(), 
				info.getLoginId(), info.getOriginatingWareHouseCoverage(), info.getDestinationWareHouseCoverage(), info.getOriginatingCountry(), 
				info.getDestinationCountry(), info.getSeaCoverages(), info.getCurrency(), info.getExchangeId(), info.getCurrencyName(), 
				info.getCurrencyValue(), info.getPolicyStartDate(), info.getBrokerType(), info.getMinimumPremium(), info.isEndt(),
				info.getBrokerCode(), info.getLoginBranch(), info.getPromotinalCode());*/
		premiumInfo=service.calculatePremium(info.getApplicationNo(), info.getModeOfTransport(), info.getCoverages(), info.getSaleTermValue(),
				commodity, info.getToleranceValue(), info.getProductId(), info.getOpenCoverPolicyNo(), info.getBranchCode(), info.getWsrcc(), 
				info.getLoginId(), info.getOriginatingWareHouseCoverage(), info.getDestinationWareHouseCoverage(), info.getOriginatingCountry(), 
				info.getDestinationCountry(), info.getSeaCoverages(), info.getCurrency(), info.getExchangeId(), info.getCurrencyName(), 
				info.getCurrencyValue(), info.getPolicyStartDate(), info.getBrokerType(), info.getMinimumPremium(), info.isEndt(),
				info.getBrokerCode(), info.getLoginBranch(), info.getPromotinalCode(), belongingBranch);
		if(premiumInfo!=null && !premiumInfo.isEmpty()){
			info.setTotalPremium((String)premiumInfo.get("totalPremium"));
			info.setTotalWarPremium((String)premiumInfo.get("totalWarPremium"));
			info.setTotalSaleterm((String)premiumInfo.get("totalSaleterm"));
			info.setTotalTolerance((String)premiumInfo.get("totalTolerance"));
		}
		//service.updateMarineAndWarPremium(info.getQuoteNo());
	}catch(Exception e)
	{
		logger.info("Error in Policy Generation: "+e);
		logger.debug(e);
	}
	return premiumInfo;
}
public String update()
{
	try
	{
		service.setInfo(info);
		String belongingBranch = service.getBelongingBranch(info.getBranchCode());
		//String[][] commodityInfo=service.getCommodityInfo(refNo, info.getBranchCode());
		String[][] commodityInfo=service.getCommodityInfo(refNo, belongingBranch);
		service.insertOrUpdateMarinePolicyInfo(info, commodityInfo);
	}catch(Exception e)
	{
		logger.debug("Exception in QuoteGeneration {"+e+"}");e.printStackTrace();
	}
	return "";
}
public void mailGeneration(String refStatus)
{
	service.mailGeneration(info, userType, refStatus);
}
public void guoteMailGeneration(String refStatus, String toMailAddress)
{
	MailControllerNew mailController = new MailControllerNew();
	mailController.guoteMailGeneration(info, userType, refStatus, toMailAddress);
}
public void referralResponseMailGeneration(String refStatus)
{
	MailControllerNew mailController = new MailControllerNew();
	mailController.referralResponseMailGeneration(info, userType, refStatus);
}
public void updateQuoteInfo()
{
	info.setStatus("Q");
	service.setInfo(info);
	service.updateCommodityInfo(info);
	service.updateQuoteInfo(info);
}
public String docGeneration(String docType)
{
	try
	{
		String filePath="", mode="test".equalsIgnoreCase(info.getAppMode())?"TEST ":"";
		if("DRAFT".equalsIgnoreCase(docType)){
			filePath=service.generatePdf(info, "DRAFT", "draftMode");
		}else if("QUOTE".equalsIgnoreCase(docType)){
			filePath=service.generateQuoteSchedule(info);
		}else{
			//filePath=service.generatePdf(info, mode+docType, "");
			filePath=service.generatePdf(info, docType, "");
		}
		service.updateDocInfo(info, docType, filePath);
	}catch(Exception e)
	{
		logger.info("Error in docGeneration: "+e);
	}
	return error;
}
public void updateAdminReferralInfo()
{
	service.updateAdminReferralInfo(info);
	if("Y".equalsIgnoreCase(info.getAdminReferralYN())){
		service.mailGeneration(info,userType,"none");
	}
}
public boolean updateReferralDetails()
{
	boolean referralType = false;
	try
	{
		service.setInfo(info);
		ReferralValidation validation=new ReferralValidation();
		referralType=validation.isPolicyDateReferral(info.getBranchCode(), info.getLoginId(), info.getProductId(), info.getOpenCoverPolicyNo(), info.getPolicyStartDate(), info.getOpenCoverBackDateDays());
		
	}catch(Exception e)
	{
		logger.debug("Exception in updateReferralDetails {"+e+"}");e.printStackTrace();
	}
	return referralType;
}
}

