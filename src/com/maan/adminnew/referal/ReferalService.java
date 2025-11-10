package com.maan.adminnew.referal;

import java.util.List;
import java.util.Map;

public class ReferalService
{	
	ReferalDAO dao=new ReferalDAO();
	
	public List <Map<String, Object>> getOCList(String reqFrom, String productID, String branchCode, String agencyCode){
    	return dao.getOCList(reqFrom, productID, branchCode, agencyCode);
    }
	
	public List <Map<String, Object>> getPolicyList(String branchCode, String date, String productID, String reqFrom, String agencyCode){
    	return dao.getPolicyList(branchCode, date, productID, reqFrom, agencyCode);
    }
}