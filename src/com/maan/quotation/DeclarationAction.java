package com.maan.quotation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.maan.common.LogUtil;
import com.maan.quotation.service.DeclarationService;
import com.maan.quotation.service.PremiumService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class DeclarationAction extends ActionSupport {
	private static final Logger logger = LogUtil.getLogger(DeclarationAction.class);
	
	HttpServletRequest request=ServletActionContext.getRequest();
	Map<String, Object> session=ActionContext.getContext().getSession();
	DeclarationService service=new DeclarationService();
	private static final long serialVersionUID = 1L;
	private String loginId=(String)session.get("userName");
	private String openCoverNo=(String)session.get("openCoverNo");
	private String issuer=(String)session.get("RSAISSUER");
	private String branchCode=(String)session.get("LoginBranchCode");
	private String productId=(String) session.get("product_id");
	private String menuType;
	private String tranId;
	private String policyNo;
	private String applicationNo;
	private String quoteNo;
	private String rubberStamp;
	private String noteType;
	private String paymentMode;
	private String currencyId;
	private String currencyValue;
	private String debitCustomerId;
	private String generatePolicy;
	private List< Object> selectedQuote;
	private List<Map<String, Object>> policyList;	
	private List<Map<String, Object>> declarationList;
	private String supplier;
	private String listType;
	private String vessel;
	private String reqFrom;
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public String getListType() {
		return listType;
	}
	public void setListType(String listType) {
		this.listType = listType;
	}
	public String getVessel() {
		return vessel;
	}
	public void setVessel(String vessel) {
		this.vessel = vessel;
	}
	public String getReqFrom() {
		return reqFrom;
	}
	public void setReqFrom(String reqFrom) {
		this.reqFrom = reqFrom;
	}
	/**
	 * @return the currencyId
	 */
	public String getCurrencyId() {
		return currencyId;
	}
	/**
	 * @param currencyId the currencyId to set
	 */
	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}
	/**
	 * @return the currencyValue
	 */
	public String getCurrencyValue() {
		return currencyValue;
	}
	/**
	 * @param currencyValue the currencyValue to set
	 */
	public void setCurrencyValue(String currencyValue) {
		this.currencyValue = currencyValue;
	}
	/**
	 * @return the debitCustomerId
	 */
	public String getDebitCustomerId() {
		return debitCustomerId;
	}
	/**
	 * @param debitCustomerId the debitCustomerId to set
	 */
	public void setDebitCustomerId(String debitCustomerId) {
		this.debitCustomerId = debitCustomerId;
	}
	/**
	 * @return the commissionStatus
	 */
	public boolean getCommissionStatus() {
		return new PremiumService().getCommissionStatus(loginId, productId, openCoverNo, issuer, applicationNo, branchCode);
	}
	/**
	 * @return the accountStatus
	 */
	public boolean getAccountStatus() {
		return new PremiumService().getCustAccountStatus(applicationNo);
	}
	
	/**
	 * @return the quoteNo
	 */
	public String getQuoteNo() {
		return quoteNo;
	}
	/**
	 * @param quoteNo the quoteNo to set
	 */
	public void setQuoteNo(String quoteNo) {
		this.quoteNo = quoteNo;
	}
	/**
	 * @return the rubberStamp
	 */
	public String getRubberStamp() {
		return rubberStamp;
	}
	/**
	 * @param rubberStamp the rubberStamp to set
	 */
	public void setRubberStamp(String rubberStamp) {
		rubberStamp = rubberStamp;
	}
	/**
	 * @return the noteType
	 */
	public String getNoteType() {
		return noteType;
	}
	/**
	 * @param noteType the noteType to set
	 */
	public void setNoteType(String noteType) {
		this.noteType = noteType;
	}
	/**
	 * @return the paymentMode
	 */
	public String getPaymentMode() {
		return paymentMode;
	}
	/**
	 * @param paymentMode the paymentMode to set
	 */
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	/**
	 * @return the generatePolicy
	 */
	public String getGeneratePolicy() {
		return generatePolicy;
	}
	/**
	 * @param generatePolicy the generatePolicy to set
	 */
	public void setGeneratePolicy(String generatePolicy) {
		this.generatePolicy = generatePolicy;
	}
	/**
	 * @return the declarationList
	 */
	public List<Map<String, Object>> getDeclarationList() {
		return declarationList;
	}
	/**
	 * @param selectedQuote the selectedQuote to set
	 */
	public void setSelectedQuote(List<Object> selectedQuote) {
		this.selectedQuote = selectedQuote;
	}
	/**
	 * @return the tranId
	 */
	public String getTranId() {
		return tranId;
	}
	/**
	 * @param tranId the tranId to set
	 */
	public void setTranId(String tranId) {
		this.tranId = tranId;
	}
	/**
	 * @return the menuType
	 */
	public String getMenuType() {
		return menuType;
	}
	/**
	 * @param menuType the menuType to set
	 */
	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	public List<Map<String, Object>> getPolicyList() {
		return policyList;
	}
	public void setPolicyList(List<Map<String, Object>> policyList) {
		this.policyList = policyList;
	}
	/*public String init()
	{   
		try
		{	
			if ("D".equalsIgnoreCase(menuType)) {
				if(StringUtils.isEmpty(tranId)){
					if ("D".equalsIgnoreCase(menuType) || "DT".equalsIgnoreCase(menuType) ) {
						policyList=service.getDeclarationList(menuType,new String[]{branchCode,openCoverNo,loginId,issuer});
					}else{
						policyList=service.getDeclarationList(menuType,new String[]{openCoverNo,loginId,issuer});
					}
				}else{
					policyList=service.getDeclarationList("DT",new String[]{branchCode,openCoverNo,loginId,issuer,tranId});
					menuType="DP";
				}
			}
			else if ("MD".equalsIgnoreCase(menuType)) {
				if(StringUtils.isEmpty(tranId)){
					policyList=service.getDeclarationList("D",new String[]{branchCode,openCoverNo,loginId,issuer});
				}else{
					policyList=service.getDeclarationList("DT",new String[]{branchCode,openCoverNo,loginId,issuer,tranId});
				}
				if(selectedQuote==null || selectedQuote.size()<2){
					addActionError("error.declaration.selectQuote");
					menuType="D";
				}else{
					if(policyList!=null && !policyList.isEmpty()){
						declarationList=new ArrayList<Map<String, Object>>();
						Map<String,String> quoteInfo=null;
						for (int i = 0; i < policyList.size(); i++) {
							quoteInfo=(Map<String,String>)policyList.get(i);
							if (selectedQuote!=null && !selectedQuote.isEmpty()) {
								for (int j = 0; j < selectedQuote.size(); j++) {
									if (String.valueOf(quoteInfo.get("QUOTE_NO")).equals(selectedQuote.get(j))) {
										declarationList.add(quoteInfo);
									}
								}
							}
						}
					}
				}
			}else if("DE".equals(menuType)){
				policyList= service.getDeclarationList("DE", reqFrom, new String[]{loginId,productId,"",new com.maan.report.service.ReportService().getOriginalPolicyNo(openCoverNo)});
			}else if("DEQ".equals(menuType)){
				policyList= service.getDeclarationList("DE", reqFrom, new String[]{loginId,productId,"",new com.maan.report.service.ReportService().getOriginalPolicyNo(openCoverNo)});
				if(selectedQuote==null && selectedQuote.size()<2){
					addActionError("Please select minimum two Quotes");
					menuType="DE";					 
				}else{
					if(policyList!=null && policyList.size()>0){
							declarationList=new ArrayList<Map<String, Object>>();
							Map<String,String> quoteInfo=null;
							for (int i = 0; i < policyList.size(); i++) {
								quoteInfo=(Map<String,String>)policyList.get(i);
								if (selectedQuote!=null && !selectedQuote.isEmpty()) {
									for (int j = 0; j < selectedQuote.size(); j++) {
										if (String.valueOf(quoteInfo.get("QUOTE_NO")).equals(selectedQuote.get(j))) {
											declarationList.add(quoteInfo);
										}
									}
								}
							}	
						}
				}
			}
			
		}
		catch(Exception e)
		{
			logger.debug("EXCEPTION @ "+e);e.printStackTrace();
		}
		return SUCCESS;		
	}*/
	public String init() {   
		try {	
			if ("D".equalsIgnoreCase(menuType)) {
					if ("D".equalsIgnoreCase(menuType) || "DT".equalsIgnoreCase(menuType) ) {
						if(StringUtils.isNotBlank(tranId) && StringUtils.isNotBlank(vessel)  && StringUtils.isNotBlank(supplier)){
							policyList=service.getDeclarationList(menuType,reqFrom, new String[]{branchCode,new com.maan.report.service.ReportService().getOriginalPolicyNo(openCoverNo),loginId,issuer,tranId, supplier, vessel});
							listType="details";
						}else if("result".equals(reqFrom) || "declare".equals(reqFrom)){
							policyList=service.getDeclarationList(menuType, reqFrom, new String[]{branchCode,new com.maan.report.service.ReportService().getOriginalPolicyNo(openCoverNo),loginId,issuer,tranId});
							if("declare".equals(reqFrom))
								menuType="DP";
						}else
							policyList=service.getDeclarationList(menuType, reqFrom, new String[]{openCoverNo});
					}else{
						policyList=service.getDeclarationList(menuType,reqFrom, new String[]{openCoverNo,loginId,issuer});
					}
				/*}else{
					if(StringUtils.isNotBlank(tranId) && StringUtils.isNotBlank(vessel)  && StringUtils.isNotBlank(supplier)){
						policyList=service.getDeclarationList(menuType,reqFrom, new String[]{branchCode,openCoverNo,loginId,issuer,tranId, supplier, vessel});
						listType="details";
					}else{
						policyList=service.getDeclarationList("DT",reqFrom, new String[]{branchCode,openCoverNo,loginId,issuer,tranId});
						menuType="DP";
					}
				}*/
			}
			else if ("MD".equalsIgnoreCase(menuType)){
				
				if(StringUtils.isEmpty(tranId)){
					policyList=service.getDeclarationList("D",reqFrom, new String[]{branchCode,new com.maan.report.service.ReportService().getOriginalPolicyNo(openCoverNo),loginId,issuer});
				}else if(StringUtils.isNotBlank(tranId) && StringUtils.isNotBlank(vessel)  && StringUtils.isNotBlank(supplier)){
					reqFrom="result";
					policyList=service.getDeclarationList("D",reqFrom, new String[]{branchCode,new com.maan.report.service.ReportService().getOriginalPolicyNo(openCoverNo),loginId,issuer,tranId, supplier, vessel});
				}/*{
					policyList=service.getDeclarationList("DT",new String[]{branchCode,openCoverNo,loginId,issuer,tranId});
				}*/
				if(selectedQuote==null || selectedQuote.size()<2){
					addActionError("Please select minimum two Quotes");
					menuType="D";
					listType="details";
				}else{
					if(policyList!=null && !policyList.isEmpty()){
						declarationList=new ArrayList<Map<String, Object>>();
						Map<String,Object> quoteInfo=null;
						for (int i = 0; i < policyList.size(); i++) {
							quoteInfo=(Map<String,Object>)policyList.get(i);
							if (selectedQuote!=null && !selectedQuote.isEmpty()) {
								for (int j = 0; j < selectedQuote.size(); j++) {
									if (String.valueOf(quoteInfo.get("QUOTE_NO")).equals(selectedQuote.get(j))) {
										declarationList.add(quoteInfo);
									}
								}
							}
						}
					}
				}
			}
			else if("DE".equals(menuType)){
				policyList= service.getDeclarationList("DE", reqFrom, new String[]{loginId,productId,"",new com.maan.report.service.ReportService().getOriginalPolicyNo(openCoverNo)});
			}else if("DEQ".equals(menuType)){
				policyList= service.getDeclarationList("DE", reqFrom, new String[]{loginId,productId,"",new com.maan.report.service.ReportService().getOriginalPolicyNo(openCoverNo)});
				if(selectedQuote==null && selectedQuote.size()<2){
					addActionError("Please select minimum two Quotes");
					menuType="DE";					 
				}else{
					if(policyList!=null && policyList.size()>0){
							declarationList=new ArrayList<Map<String, Object>>();
							Map<String,Object> quoteInfo=null;
							for (int i = 0; i < policyList.size(); i++) {
								quoteInfo=(Map<String,Object>)policyList.get(i);
								if (selectedQuote!=null && !selectedQuote.isEmpty()) {
									for (int j = 0; j < selectedQuote.size(); j++) {
										if (String.valueOf(quoteInfo.get("QUOTE_NO")).equals(selectedQuote.get(j))) {
											declarationList.add(quoteInfo);
										}
									}
								}
							}	
						}
				}
			}
				
		}
		catch(Exception e)
		{
			logger.debug("EXCEPTION @ "+e);e.printStackTrace();
		}
		return SUCCESS;		
	}
	public String update()
	{
		try
		{validateMultiQuotes();
		if(!hasActionErrors()){
			service.updatePolicyInfo(selectedQuote, generatePolicy, rubberStamp);
			if("Y".equalsIgnoreCase(generatePolicy)){
      			policyNo=service.policyGeneration(loginId, productId, openCoverNo, selectedQuote, branchCode, StringUtils.defaultIfEmpty(rubberStamp, "N"), noteType, paymentMode, currencyId, debitCustomerId, currencyValue, issuer);
      		}if(StringUtils.isEmpty(policyNo)){
      			addActionError(getText("error.premiumInfo.policy.invalid"));
      		}
		}
		declarationList=service.getDeclarationList(selectedQuote);
		menuType="MC";
		}catch (Exception e)
		{
			logger.debug("Exception @ "+e);e.printStackTrace();
		}
		return SUCCESS;
	}
	public void validateMultiQuotes(){
		  List<String> validateFields = service.validateFields(selectedQuote);
		  List<String> validateExcessPremium = service.validateExcessPremium(selectedQuote);
		  for(int i=0;i<validateFields.size();i++){
			  addActionError(getText(validateFields.get(i)));
		  }
		  for(int i=0;i<validateExcessPremium.size();i++){
			  addActionError(getText(validateExcessPremium.get(i)));
		  }
		    
	}
}
