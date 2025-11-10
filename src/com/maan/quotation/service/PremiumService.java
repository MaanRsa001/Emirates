package com.maan.quotation.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.maan.common.exception.BaseException;
import com.maan.quotation.PremiumAction;
import com.maan.quotation.dao.PremiumDAO;
import com.maan.webservice.PolicyGenerationAction;
import com.maan.webservice.VatPremiumCalc;
import com.maan.webservice.dao.PolicyGenerationDAO;



public class PremiumService {
	final static transient private String NOTHING = "NOTHING";
	PremiumDAO premiumDAO=new PremiumDAO();	
	PolicyGenerationDAO policyDAO=new PolicyGenerationDAO();	
	public List<Map<String, Object>> getQuotationInformation(String applicationNo,String branchCode){
		return premiumDAO.getQuotationInformation(applicationNo,branchCode);
	}
	 public List<Map<String, Object>> getQuotationInsuredValue(String applicationNo,String branchCode){
		 return premiumDAO.getQuotationInsuredValue(applicationNo,branchCode);
	 }
	 public int updatePolicyInformation(PremiumAction premiumAction)throws  BaseException{
		 return premiumDAO.updatePolicyInformation(premiumAction);
	 }
	 public Map<String, Object> calculatePremium(String refNo, String[][] commoidty)throws  BaseException{
		 return new PolicyGenerationAction(refNo).calculate(commoidty);
	 }
	 public String updatePremiumInfo(String applicationNo, String totalPremium, String excessPremium, String policyFee,String totalWarPremium,String vatTax)throws  BaseException{
			totalPremium= String.valueOf(Double.parseDouble(totalPremium)+Double.parseDouble(policyFee));
		 return this.premiumDAO.updatePremiumInfo(applicationNo, totalPremium, excessPremium, policyFee, totalWarPremium,vatTax);
	 }
	 public void updateReferralInfo(String applicationNo,String refStatus, String adminRefRemarks, String commission, String adminLogin, String stat)throws  BaseException{
		 premiumDAO.updateAdminReferralInfo(applicationNo, refStatus, adminRefRemarks, commission, adminLogin, stat);
	 }
	 public List<Map<String, Object>> getPolicyInformation(String applicationNo)
	 {
		 return premiumDAO.getPolicyInformation(applicationNo);
	 }
	 public Map<String, Object> getConditionsInfo(String applicationNo, String branchCode)throws  BaseException{
		 return premiumDAO.getConditions(applicationNo, branchCode);
	 }
	 public String convertConditions(List<Object> idList, List<Object> valueList)throws  BaseException
	 {
		 String result="";
		 if(idList!=null && !idList.isEmpty())	
			{
				for (int i = 0; i < idList.size(); i++) {
					if(StringUtils.isNotEmpty(valueList.get(i).toString()))
					result+=idList.get(i)+"~"+valueList.get(i)+"#";
				}
				if ("".equals(result)) {
					result = NOTHING;
				} else {
					result = result.substring(0, result.length() - 1);
				}
			}
		 return result;
	 }
	 public String policyGeneration(String refNo)
	 {
		 return new PolicyGenerationAction(refNo).policyGeneration();
	 }
	 public boolean getCommissionStatus(String loginId, String productId, String openCoverNo, String issuer, String applicationNo, String branchCode)
	 {
		 String commission=policyDAO.getCommissionPercent(loginId, productId, openCoverNo, issuer, applicationNo, branchCode);
		 return Double.parseDouble(commission)>0;
	 }
	 public boolean getCustAccountStatus(String applicationNo)
	 {
		 return premiumDAO.getCustAccountStatus(applicationNo);
	 }
	 public void updateClausesInfo(String args[])
	 {
		 premiumDAO.updateClausesInfo(args);
	 }
	 public List<Map<String, Object>> getExistingConditions(String option, String branchCode, String coverId, List<String> id)
	 {
		 return premiumDAO.getExistingConditions(option, branchCode, coverId, id);
	 }
	 public void addConditionsInfo(String args[])
	 {
		 premiumDAO.addConditionsInfo(args);
	 }
	 public void sendMail(String refNo, String userType, String refStatus)throws  BaseException{
		 refStatus="A".equals(refStatus)?"accept":("R".equals(refStatus)?"reject":"none");
		 new PolicyGenerationAction(refNo, userType).mailGeneration(refStatus);
	 }
	 public void quoteMail(String refNo, String userType, String refStatus, String toMailAddress)throws  BaseException{
		 refStatus="A".equals(refStatus)?"accept":("R".equals(refStatus)?"reject":"none");
		 new PolicyGenerationAction(refNo, userType).guoteMailGeneration(refStatus,toMailAddress);
	 }
	 public void referralResponseMail(String refNo, String userType, String refStatus)throws  BaseException{
		 refStatus="A".equals(refStatus)?"accepted":("R".equals(refStatus)?"rejected":"pending");
		 new PolicyGenerationAction(refNo, userType).referralResponseMailGeneration(refStatus);
	 }
	 public boolean getOpenCoverCustomer(String openCoverNo)
	 {
		 return premiumDAO.getOpenCoverCustomer(openCoverNo);
	 }
	 public List<Map<String, Object>> getBasisValList(String branchCode){
		 return premiumDAO.getBasisValList(branchCode);
	 }
	 public Map<String, Object> getPremiumLoadDisc(String loginId)throws  BaseException{
		 return new PolicyGenerationDAO().getResultMap("GET_PREM_LOAD_DISC", new String[]{loginId});
	 }
	 public String getBrokerType(String loginId)
	 {
		 return policyDAO.getValue("brokerType", new String[]{loginId});
	 }
	 public String getLoginId(String applicationNo)
	 {
		 return policyDAO.getValue("GET_LOGIN_ID", new String[]{applicationNo});
	 }
	 public void updateEndtPremium(String quoteNo){
		 policyDAO.updateEndtPremium(quoteNo);
	 }
	 public Map<String, Object> getEndtPremiumInfo(String quoteNo){
		 return premiumDAO.getEndtPremiumInfo(quoteNo);
	 }
	 public boolean isEndt(String applicationNo) {
		 return policyDAO.isEndt(applicationNo);
	 }
	 public boolean isFinancial(String applicationNo) {
		 return policyDAO.isFinancial(applicationNo);
	 }
	 public boolean endtPolicyFee(String applicationNo) {
			return policyDAO.endtPolicyFee(applicationNo);
	 }
	 public void updateEndtPolicyFee(String applicationNo, String policyFee){
		 policyDAO.updateEndtPolicyFee(applicationNo, policyFee);
	 }
	 public List<Map<String, Object>> getPolicyDeductibles(String applicationNo)
	 {
		 return premiumDAO.getPolicyDeductibles(applicationNo);
	 }
	 public String getNoteTypeInfo(String applicationNo)
	 {
		 return policyDAO.getNoteTypeInfo(applicationNo);
	 }
	 public String getPaymentModeInfo(String applicationNo)
	 {
		 return policyDAO.getPaymentModeInfo(applicationNo);
	 }
	 public Map<String,Object> getExistingCustInfo(String customerId) {
		 return premiumDAO.getExistingCustInfo(customerId);
	 }
	 public String getCreditNoteStatus(String loginId,String productId){
		 return premiumDAO.getCreditNoteStatus(loginId,productId);
	 }
	 public String validateSumInsured(String applicationNo, String openCoverNo) {
		 return policyDAO.validateSumInsured(applicationNo,openCoverNo);
	 }
	public void updateExcess(String applicationNo,String policyExcessVal) {
		premiumDAO.updateExcess(applicationNo,policyExcessVal);
		
	}
	public String getExistingCustOsAmt(String customerId) {
		 return premiumDAO.getExistingCustOsAmt(customerId);
	}
	public void updateMarineVatinfo(String quoteNo, String branchCode,String productId) {
		new VatPremiumCalc().updateMarineVatinfo(quoteNo,branchCode,productId);
		
	}
	public String createMerchantRefNoWithStatus(String quoteNo, String applicationNo, String status, String paymentMethod, String productId, PremiumAction premiumAction) {
		return premiumDAO.createMerchantRefNoWithStatus(quoteNo,applicationNo,status,paymentMethod,productId,premiumAction);		
	}
	public List<Map<String, Object>> getPaymentInformation(String merchantRefno) {
		// TODO Auto-generated method stub
		return premiumDAO.getPaymentInformation(merchantRefno);
	}
	public void sentPolicyMail(Map<String, Object> paymentDetail, String policyNo,Map<String, Object> quoteInfo) {
		premiumDAO.sentPolicyMail(paymentDetail,policyNo,quoteInfo);		
	}
	public void deactivatePay(String quoteNo, String applicationNo) {
		premiumDAO.deactivatePay(quoteNo,applicationNo);		
	}
}
