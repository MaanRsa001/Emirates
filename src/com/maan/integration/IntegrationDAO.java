package com.maan.integration;

import java.util.List;
import java.util.Map;


public interface IntegrationDAO {
	public List<Map<String,Object>> getCustomerInfo(String policyNo);
	public List<Map<String,Object>> getUserInfo(String policyNo);
	public List<Map<String,Object>> getPolicyTagInfo(String policyNo);
	public List<Map<String,Object>> getRiskTag(String policyNo);
	public List<Map<String,Object>> getRiskVesselTag(String policyNo);
	public List<Map<String,Object>> getRiskSmiTagInfo(String policyNo);
	public List<Map<String,Object>> getRiskCoverTagInfo(String policyNo);
	public List<Map<String,Object>> getRiskDedtTag(String policyNo);
	public List<Map<String,Object>> getRiskDisTag(String policyNo);
	public List<Map<String,Object>> getRiskLoadTag(String policyNo);
	public List<Map<String,Object>> getDeductibleTagInfo(String policyNo);
	public List<Map<String,Object>> getDiscountTagInfo(String policyNo);
	public List<Map<String,Object>> getLoadingTagInfo(String policyNo);
	public List<Map<String,Object>> getConditionTagInfo(String policyNo);
	public List<Map<String,Object>> getChargesTagInfo(String policyNo);
	public List<Map<String,Object>> getBrokersTagInfo(String policyNo);
	public List<Map<String,Object>> getMarDirFacsharedtlTag(String policyNo);
	public List<Map<String,Object>> getMarDirFacparDtlsTag(String policyNo);
	public List<Map<String,Object>> getMarDirFacpartCommTag(String policyNo);
	public List<Map<String,Object>> getAccountTagInfo(String policyNo);
	public List<Map<String,Object>> getVatTagInfo(String policyNo);
	public Object[] constantList(String string, Map<String, Object> map);

	/** Endorsement Tags
	 * @param tranId **/
	public List<Map<String, Object>> getEndorsePolicyTag(String policyNo, String tranId);
	public List<Map<String, Object>> getEndtRiskTag(String policyNo, String tranId);
	public List<Map<String, Object>> getEndtRiskVesselTag(String policyNo, String tranId);
	public List<Map<String, Object>> getEndtRiskCoverTagInfo(String policyNo, String tranId);
	public List<Map<String, Object>> getEndtRiskDedtTag(String policyNo, String tranId);
	public List<Map<String, Object>> getEndtRiskDisTag(String policyNo, String tranId);
	public List<Map<String, Object>> getEndtRiskLoadTag(String policyNo, String tranId);
	public List<Map<String, Object>> getEndtDeductibleTagInfo(String policyNo, String tranId);
	public List<Map<String, Object>> getEndtDiscountTagInfo(String policyNo, String tranId);
	public List<Map<String, Object>> getEndtLoadingTagInfo(String policyNo, String tranId);
	public List<Map<String, Object>> getEndtRiskSmiTagInfo(String policyNo, String tranId);
	public List<Map<String, Object>> getEndtConditionTagInfo(String policyNo, String tranId);
	public List<Map<String, Object>> getEndtChargesTagInfo(String policyNo, String tranId);
	public List<Map<String, Object>> getEndtBrokersTagInfo(String policyNo, String tranId);
	public String getMaxTranId(String policyNo);
	public List<Map<String, Object>> getEndtAccTagInfo(String policyNo,String tranId);
	public List<Map<String, Object>> getEndtVatTagInfo(String policyNo,String tranId);
	public void updateStatus(String policyNo,String status, String msg, String res);
	public List<Map<String, Object>> getCustomerInfoCust(String customerId);
	public void updateXmlRequest(String policyNo, String status, String msg,String policyType);
//	public List<Map<String, Object>> getCustomerVatInfo(String quoteNo);
	public List<Map<String, Object>> getCustomerInfoBroker(String customerId);



}
