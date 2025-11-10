package com.maan.adminnew.userManagement;

import java.util.List;
import java.util.Map;

public class UserMgtService
{	
	UserMgtDAO dao=new UserMgtDAO();
	
    public List <Map<String, Object>> getAdminUserList(final UserMgtBean ba, String agencyCode, String mode1, String branchCode){
    	return dao.getAdminUserList(ba,agencyCode, mode1, branchCode);
    }
    public List <Map<String, Object>> getUserDetails(final UserMgtBean ba, String uagencyCode){
    	return dao.getUserDetails(ba, uagencyCode);
    }
    public List <Map<String, Object>> getCommisionData(String uagencyCode, String agencyCode, String branchCode){
    	return dao.getCommisionData(uagencyCode, agencyCode, branchCode);
    }
    public List<Map<String, Object>> getUserListAjax(String agencyCode, String searchBy, String searchValue, String mode1){
    	  return dao.getUserListAjax(agencyCode, searchBy, searchValue, mode1);
    }
    public List <Map<String, Object>> getOCCertificate(String agencyCode){
    	return dao.getOCCertificate(agencyCode);
    }
    /*public List <Map<String, Object>> getProducts(final UserMgtBean ba){
    	return dao.getProducts(ba);
    }*/
    public void insertUserInfo(Object[]args, Object[] args1){
   	 dao.insertUserInfo(args, args1);
   }
    public void updateUserInfo(Object[]args, Object[] args1){
   	 dao.updateUserInfo(args, args1);
   }
}