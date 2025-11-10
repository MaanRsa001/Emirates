package com.maan.quotation.service;

import java.util.List;
import java.util.Map;
import com.maan.quotation.QuotationAction;
import com.maan.quotation.dao.QuotationDAO;
import com.maan.webservice.PolicyGenerationAction;
import com.maan.webservice.dao.PolicyGenerationDAO;

public class QuotationService {
	QuotationDAO quoteDAO=new QuotationDAO();	
	PolicyGenerationDAO policyDAO=new PolicyGenerationDAO();	
	public List<Map<String, Object>> getOptionsList(String option, Object[] objects)
	{
		return quoteDAO.getOptionsList( option, objects);
	}
	public Map<String, Object> getBranchInfo(String branchCode)
	{
		return quoteDAO.getBranchInfo(branchCode);
	}
	public List<String> getQuoteInfo(QuotationAction action)
	{
		return quoteDAO.getQuoteInfo(action);
	}
	public String addCommodity(String applicationNo,String refNo, List<Map<String, Object>> commList)
	{
		return quoteDAO.addCommodity(applicationNo, refNo, commList);
	}
	public String updateCommodity(String applicationNo, List<Map<String, Object>> commList)
	{
		return quoteDAO.updateCommodity(applicationNo, commList);
	}
	public String getQuote(String applicationNo, Object[] args, String quoteType, String status, String userType, String refNo)
	{
		quoteDAO.insertOrUpdateQuote(applicationNo,args,quoteType);
		if(!"RA".equalsIgnoreCase(status)){
			return new PolicyGenerationAction(refNo, userType).quoteGeneration();
		}else{
			return new PolicyGenerationAction(refNo).update();
		}
	}
	public String getApplicationNo()
	{
		return new PolicyGenerationDAO().getMaxApplicationNo();
	}
	public List<Map<String, Object>> getCustomerSelectionList(String loginId,String searchValue,String openCoverNo)
	{
		return quoteDAO.getCustomerSelectionList(loginId,searchValue,openCoverNo);
	}
	public List<Map<String, Object>> getCoreCustomerSearch(String searchValue, String branchCode, String channelType) {
		return quoteDAO.getCoreCustomerSearch(searchValue,branchCode,channelType);
	}
	public String getSingleInfo(String option, String[] args)
	{
		return quoteDAO.getSingleInfo(option,args);
	}
	public List<Map<String, Object>> getVesselList(String vesselName)
	{
		return quoteDAO.getVesselList(vesselName);
	}
	public String checkExist(String dataValue,String datatype,String applicationNo)
	{
		return quoteDAO.checkExist(dataValue, datatype,applicationNo);
	}
	public String getBrokerLoginId(String brokerCode)
	{
		return quoteDAO.getBrokerLoginId(brokerCode);	
	}
	public String getPolicyEndtType(String applicationNo)
	{
		return quoteDAO.getPolicyEndtType(applicationNo);	
	}
	public Map<String, Object> getPolicyEndtInfo(String quoteNo)
	{
		return policyDAO.getResultMap("GET_ENDT_POL_INFO", new String[]{quoteNo});	
	}
	public Map<String, Object> getOpenCoverInfo(String openCoverNo)
	{
		return policyDAO.getResultMap("GET_OC_INFO", new String[]{openCoverNo});	
	}
	public String getQuoteIssuer(String applicationNo)
	{
		return policyDAO.getValue("GET_QUOTE_ISSUER", new String[]{applicationNo});	
	}
	public String getDirectStatus(String applicationNo,String branchCode)
	{
		return quoteDAO.getDirectStatus(applicationNo,branchCode);
	}
	public String getbranchWiseCountry(String branchCode)
	{
		return quoteDAO.getbranchWiseCountry(branchCode);
	}
	public boolean getDubaiTradeStatus(String brokerCode,String branchCode)
	{
		return quoteDAO.getDubaiTradeStatus(brokerCode,branchCode);
	}
	public List<Map<String, Object>> lcSelectionList(String ocNo, String searchValue, String exact) {
		return quoteDAO.lcSelectionList(ocNo, searchValue, exact);
	}
	public int lcNoExist(String lcNo, String ocNo){
		return quoteDAO.lcNoExist(lcNo, ocNo);
	}
	public int checkValidPromotionalCode(String promotionalCode, String branchCode) {
		return quoteDAO.checkValidPromotionalCode(promotionalCode, branchCode);
	}
	public void updateWSMarineQuote(String[] args){
		quoteDAO.updateWSMarineQuote(args);
	}
	public List<Map<String, Object>> searchList(String applicationNo,String branchCode) {
		return quoteDAO.searchList(applicationNo,branchCode);
	}
	public String addCommodity(String applicationNo,String refNo, List<Map<String, Object>> commList, String branchCode) {
		return quoteDAO.addCommodity(applicationNo, refNo, commList, branchCode);
	}
	public String deleteCommodity(String applicationNo,String commodityCode) {
		return quoteDAO.deleteCommodity(applicationNo, commodityCode);
	}
	public int getCommodityExist(String commodityId, String applicationno){
		 return quoteDAO.getCommodityExist(commodityId, applicationno);
	}
	public String getFragile(String category,String appNo) {
		 return quoteDAO.getFragile(category,appNo);
		
	}
	public double getSaleTermValue(String saleTermPercent, String branchCode) {
		return quoteDAO.getSaleTermValue(saleTermPercent,branchCode);
	}
	public double getToleranceValue(String tolerance, String branchCode) {
		return quoteDAO.getToleranceValue(tolerance,branchCode);
	}
	public void setOpenCustomerInfo(QuotationAction bean, String openCoverNo) {
		quoteDAO.setOpenCustomerInfo(bean, openCoverNo);
	}
	public List<Map<String, Object>> goodsCategoryGroupList() {
		return quoteDAO.goodsCategoryGroupList();
	}
}