package com.maan.payment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.maan.integration.CyberSouceIntegration;
import com.maan.quotation.service.PremiumService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class PaymentAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	
	private static final String HMAC_SHA256 = "HmacSHA256";
    
    HttpServletRequest request = ServletActionContext.getRequest();
	Map<String, Object> session=ActionContext.getContext().getSession();
	 
	private Map<String, String> cyberSourceInfo;
	private Map<String, Object> conditionsList;
	private String merchantRefno;
	private String quoteNo;
	private String applicationNo;
	private String reqFrom;
	
	public String getQuoteNo() {
		return quoteNo;
	}

	public void setQuoteNo(String quoteNo) {
		this.quoteNo = quoteNo;
	}

	public String getApplicationNo() {
		return applicationNo;
	}

	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}

	public String onlinepaymentReq() {
			
			return "cybersource";
	}

	public Map<String, String> getCyberSourceInfo() {
		return cyberSourceInfo;
	}

	public void setCyberSourceInfo(Map<String, String> cyberSourceInfo) {
		this.cyberSourceInfo = cyberSourceInfo;
	}

	public String getMerchantRefno() {
		return merchantRefno;
	}

	public void setMerchantRefno(String merchantRefno) {
		this.merchantRefno = merchantRefno;
	}

	public String onlinepaymentRes() {
		String forward = "";
		try {	
			System.out.println("onlinePaymentRes");
			System.out.println("Request Paramerters - Start");
			java.util.Enumeration en = request.getParameterNames();
			Map<String,String> params=new HashMap<String,String>();
			while(en.hasMoreElements()) {
				Object objOri=en.nextElement();
				String param=(String)objOri;
				String value=request.getParameter(param);
				params.put(param, value);
				System.out.println(param + "->" + value);
			}
			System.out.println("Request Paramerters - End");
			CyberSouceIntegration cyberIntg=new CyberSouceIntegration();			
			Map<String, String> checkStatus = cyberIntg.checkStatus(params );
			this.quoteNo=checkStatus.getOrDefault("quoteNo","0");
			this.applicationNo=checkStatus.getOrDefault("applicationNo","0");
			this.merchantRefno=checkStatus.getOrDefault("merchantRefno","0");		
			//return "updatePolicyInfoMotor";
			//this.reqFrom="policy";
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "cybersource";
	}
	PremiumService service=new PremiumService();
	public List<Map<String, Object>> getQuotationInfo() {
		
		return service.getQuotationInformation(applicationNo,"01");
	}
	public List<Map<String, Object>> getInsuredGoodsInfo() {
		 
		return service.getQuotationInsuredValue(applicationNo,"01");
	}

	public String getReqFrom() {
		return reqFrom;
	}

	public void setReqFrom(String reqFrom) {
		this.reqFrom = reqFrom;
	}
	public String clauses()
	{
		try{
			PremiumService service=new PremiumService();
			setConditionsList(service.getConditionsInfo(applicationNo, "01"));
		}catch (Exception e){
			e.printStackTrace();  
		}	
		return INPUT;
	}

	public Map<String, Object> getConditionsList() {
		return conditionsList;
	}

	public void setConditionsList(Map<String, Object> conditionsList) {
		this.conditionsList = conditionsList;
	}
	public List<Map<String, Object>> getPolicyInformation() {
		return service.getPolicyInformation(applicationNo);
	}
	public List<Map<String, Object>> getPaymentInformation() {
		return service.getPaymentInformation(merchantRefno);
	}
	public String makePay() {
		setReqFrom("formsubmit");
		CyberSouceIntegration cyberIntg=new CyberSouceIntegration();
		cyberSourceInfo=cyberIntg.createPay(merchantRefno);
		return "cybersource";
	}
	public String initx()
	{	
		//display="init";
		return "paymentreport";
	}
	
}
