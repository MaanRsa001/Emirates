package com.maan.adminnew.portfolio;

import java.util.List;
import java.util.Map;

public class PortfolioService {

	PortfolioDAO dao=new PortfolioDAO();
	public List<Map<String, Object>> getProducts(String branchCode) {
		return dao.getProducts(branchCode);
		
	}
	public List<Map<String, Object>> getPortfolioList(Object[] val) {
		return dao.getPortfolioList(val);
	}
	public String updatePortfolioPolicyCancelStatus(Object[] val) {
		return dao.updatePortfolioPolicyCancelStatus(val);
	}
	
	public List<Map<String, Object>> getViewList(Object[] val) {
		return dao.getViewlist(val);
		
	}
	public List<Map<String, Object>> getPortFolio(String sdate, String edate, String status,
			String pid, String branchCode, String brokerCodes,
			String freightStatus,String quoteNo) // Approved_Policy_PortFoolio.jsp
	{
		return dao.getPortFolio(sdate, edate, status, pid, branchCode, brokerCodes, freightStatus,quoteNo);
	}
	public List<Map<String, Object>> getPortfolioByDate(String eDate, String status, String pid, String branchCode, String brokerCodes,
			String freightStatus) // PortFolio_ByDate.jsp BrokerCodes
	// Restriction
	{
		return dao.getPortfolioByDate(eDate,  status, pid, branchCode, brokerCodes, freightStatus);
	}
	
	public List<Map<String, Object>> getPolicyTreatyInfo(PortfolioBean bean) {
		return dao.getPolicyTreatyInfo(bean);
	}
	
	public int saveTreaty(PortfolioBean bean) {
		return dao.saveTreaty(bean);
	}
	
	public List<Map<String, Object>> facultativeInfo(PortfolioBean bean) {
		return dao.facultativeInfo(bean);
	}
	
	public int insertFacultativeinfo(PortfolioBean bean) {
		return dao.insertFacultativeinfo(bean);
	}
	
	public List<Map<String, Object>> getRiList() {
		return dao.getRiList();
	}
	
	public String getbranchWiseCountry(String branchCode){
		 return dao.getbranchWiseCountry(branchCode);
	}
	
}
