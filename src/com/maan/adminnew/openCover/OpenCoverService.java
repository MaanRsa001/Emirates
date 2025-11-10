package com.maan.adminnew.openCover;

import java.util.List;
import java.util.Map;

public class OpenCoverService
{	
	OpenCoverDAO dao=new OpenCoverDAO();
	
	public List <Map<String, Object>> getregQuote(String broker){
    	return dao.getregQuote(broker);
    }
	public List <Map<String, Object>> getPortfolio(String brokerLoginId){
    	return dao.getPortfolio(brokerLoginId);
    }
	public List <Map<String, Object>> getCustomerList(String broker, String appId){
    	return dao.getCustomerList(broker, appId);
    }
	public List<Map<String, Object>> getLcList(String branchCode, String policynumber) {
		return dao.getLcList(branchCode, policynumber);
	}
}