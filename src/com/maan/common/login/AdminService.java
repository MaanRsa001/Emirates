package com.maan.common.login;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Logger;

import com.maan.common.LogUtil;

public class AdminService {
	private static final Logger logger = LogUtil.getLogger(AdminService.class);
	AdminDAO dao=new AdminDAO();
    
    public List <Map<String, Object>> getappList(){
    	return dao.getappList();
    }
    
    public Map<String,String> getmailList(final AdminAction aa){
    	return dao.getmailList(aa);
    }
    
    public int insertUser(Object obj[], String reqFrom){
    	 return dao.insertUser(obj, reqFrom);
    }
    
    public void mailSave(Object obj[]){
   	 dao.mailSave(obj);
   }
   
   public List <Map<String, Object>> getexistUser(String appId){
	   return dao.getexistUser(appId);
   }
   
   public List <Map<String, Object>> getUserInfo(final AdminAction ac,String userID, String appId){
	  return dao.getUserInfo(ac,userID, appId);
   }
   
   public int getLoginAvailable(String userID, String appIds){
  	 return dao.getLoginAvailable(userID, appIds);
   }
}