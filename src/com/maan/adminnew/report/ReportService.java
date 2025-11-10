package com.maan.adminnew.report;

import java.util.List;
import java.util.Map;

public class ReportService
{	
	ReportDAO dao=new ReportDAO();
	
	public Map<Object, Object> schedule(final String broker){
    	return dao.schedule(broker);
    }
	public List<Map<String, Object>> getPolicyReport(final Object[] obj){
    	return dao.getPolicyReport(obj);
    }
	public List<Map<String, Object>> getUwList(String branchCode){
		return dao.getUwList(branchCode);
	}
	public List<Map<String, Object>> getLcSmartList(final ReportBean bean){
		return dao.getLcSmartList(bean);
	}
	public List<Map<String, Object>> getCommodityList(final String branchCode){
		return dao.getCommodityList(branchCode);
	}
	public List<Map<String, Object>> getCoverList(final String branchCode){
		return dao.getCoverList(branchCode);
	}
	public List<Map<String, Object>> getTransportModeList(final String branchCode){
		return dao.getTransportModeList(branchCode);
	}
	public List<Map<String, Object>> getSmartList(final Object[] obj){
		return dao.getSmartList(obj);
	}
	public List<Map<String, Object>> getBranchReport(ReportBean bean){
		return dao.getBranchReport(bean);
	}
	public List<Map<String, Object>> getExchangeReport(ReportBean bean){
    	return dao.getExchangeReport(bean);
    }
	public List<Map<String, Object>> getBranchName(String branchCode){
    	return dao.getBranchName(branchCode);
    }
	public List<Map<String, Object>> getOpenCoverList(ReportBean bean, String branchCode){
    	return dao.getOpenCoverList(bean, branchCode);
    }
	public List<Map<String, Object>> getConsigneeList(ReportBean bean, String branchCode){
    	return dao.getConsigneeList(bean, branchCode);
    }
	public List <Map<String, Object>> getAdminBrokerList(String branchCode, String appId){
    	return dao.getAdminBrokerList(branchCode, appId);
    }
	public List <Map<String, Object>> getCountrySmartList(){
    	return dao.getCountrySmartList();
    }
	public List<Map<String, Object>> getIntgReport(ReportBean bean) {
		return dao.getIntgReport(bean);
	}

	public List<Map<String, Object>> getMultiIntgReport(String policyNo) {
		return dao.getMultiIntgReport(policyNo);
	}
}