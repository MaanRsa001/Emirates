package com.maan.common.login;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Logger;

import com.maan.common.LogUtil;
import com.maan.common.MyJdbcTemplate;

public class AdminDAO extends MyJdbcTemplate {
	private static final Logger logger = LogUtil.getLogger(AdminDAO.class);
	String query="";
	
    public List <Map<String, Object>> getappList(){
    	logger.info("Enter into getappList()");
    	List list=null;
    	try{
    		query=getQuery("GET_APP_LIST");
	         logger.info("query =>"+query);
    		list=this.mytemplate.queryForList(query);
    	}
    	catch(Exception e){
    		logger.error("Exception in getappList()"+e);
    	}
    	logger.info("Exit from getappList()");
    	return list;
    }
    
    public int insertUser(Object[] obj, String reqFrom){
    	logger.info("Enter into insertUser()");
    	int affRow=0;
    	try{
    		if("edit".equals(reqFrom)){
    			query=getQuery("UPD_USER_INFO");
    		}else{
    			query=getQuery("INS_NEW_USER");
    		}
	        logger.info("query =>"+query);
	        affRow=this.mytemplate.update(query,obj);
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	logger.info("Exit from insertUser()");
    	return affRow;
    }
    
    public void mailSave(Object[] obj){
    	logger.info("Enter into mailSave()");
    	try{
    		query=getQuery("UPD_MAIL_MASTER");
	         logger.info("query =>"+query);
    		this.mytemplate.update(query,obj);
    	}
    	catch(Exception e){
    		logger.error("Exception in mailSave=> {}"+e);
    	}
    	logger.info("Exit from mailSave()");
    }
    
    public Map<String, String> getmailList(final AdminAction aa){
    	logger.info("Enter into getmailList()");
    	String appId=aa.getAppId()==null?"0":aa.getAppId();
    	Map map = null;
    	try{
    		query=getQuery("GET_MAIL_DETAILS");
	         logger.info("query =>"+query);
    		List list =this.mytemplate.queryForList(query,new Object[]{appId});
        	/*if(list != null && list.size()>0){
        		for(int i=0;i<list.size();i++){
	        		Map result = (Map) list.get(i);
	        		map.put((String)result.get("ITEM_DESC"), (String)result.get("ITEM_VALUE"));
        		}
        	}*/
    		if(list!=null && list.size()>0){
	    		map = (Map) list.get(0);
	        	aa.setMailcc((String)map.get("MAIL_CC"));
				aa.setSmtphost((String)map.get("SMTP_HOST"));
				aa.setSmtpuser((String)map.get("SMTP_USER"));
				aa.setSmtppwd((String)map.get("SMTP_PWD"));
				aa.setPwdexp((String)map.get("EXP_TIME"));
    		}
		}
    	catch(Exception e){
    		logger.error("Exception in getmailList=> {}"+e);
    	}
    	logger.info("Exit from getmailList()");
    	return map;
    }
    
    public List<Map<String, Object>> getexistUser(String appId){
    	logger.info("Enter into getexistUser()");
    	List existUserList=null;
    	try{
    		query=getQuery("GET_USER_LIST");
	         logger.info("query =>"+query);
    		existUserList=this.mytemplate.queryForList(query,new Object[]{appId});
    	}
    	catch(Exception e){
    		logger.error("Exception in getappList()"+e);
    	}
    	logger.info("Exit from getexistUser()");
    	return existUserList;
    }
    
    public List <Map<String, Object>> getUserInfo(final AdminAction ac, String userID, String appId){
    	logger.info("Enter into getUserInfo()");
    	List userInfo=null;
    	try{
    		query=getQuery("GET_USER_INFO_CHANGE");
	         logger.info("query =>"+query);
	         userInfo=this.mytemplate.queryForList(query,new Object[]{userID, appId});
	         if(userInfo!=null&& userInfo.size()>0){
	        	 Map map=(Map)userInfo.get(0);
	        	 ac.setUsername(map.get("USERNAME").toString());
	        	 ac.setUserID(map.get("LOGIN_ID").toString());
	        	 ac.setUserType(map.get("USERTYPE").toString());
	        	 ac.setStatus(map.get("STATUS").toString());
	        	 ac.setMail(map.get("USER_MAIL").toString());
	        	 ac.setAppIds(map.get("APP_ID").toString());
	        	 ac.setStartdate(map.get("STARTDATE").toString());
	         }
    	}
    	catch(Exception e){
    		logger.error("Exception in getUserInfo()"+e);
    	}
    	logger.info("Exit from getUserInfo()");
    	return userInfo;
     }
    
    public int getLoginAvailable(String userID, String appIds){
    	logger.info("Enter into getLoginAvailable()");
    	int count=0;
    	try{
    		query=getQuery("GET_LOGINID_COUNT");
	         logger.info("query =>"+query);
	         count=this.mytemplate.queryForObject(query, Integer.class, new Object[]{userID, appIds});
    	}
    	catch(Exception e){
    		logger.error("Exception in getUserInfo()"+e);
    	}
    	logger.info("Exit from getLoginAvailable()");
    	return count;
    }
}
