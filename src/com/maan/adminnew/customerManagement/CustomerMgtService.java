package com.maan.adminnew.customerManagement;

import java.util.List;
import java.util.Map;

public class CustomerMgtService
{	
	CustomerMgtDAO dao=new CustomerMgtDAO();
	
    public List <Map<String, Object>> getAdminCustomerList(String agencyCode){
    	return dao.getAdminCustomerList(agencyCode);
    }
    public List <Map<String, Object>> getCustomerDetails(final CustomerMgtBean ba){
    	return dao.getCustomerDetails(ba);
    }
    public List <Map<String, Object>> getCommisionData(String cagencyCode){
    	return dao.getCommisionData(cagencyCode);
    }
    public List <Map<String, Object>> getOpenCoverList(final CustomerMgtBean ba){
    	return dao.getOpenCoverList(ba);
    }
    public List<Map<String, Object>> getCustomerListAjax(String agencyCode, String searchBy, String searchValue){
  	  return dao.getCustomerListAjax(agencyCode, searchBy, searchValue);
    }
}