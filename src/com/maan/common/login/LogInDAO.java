package com.maan.common.login;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.Logger;

//import com.maan.common.Jcrypt;
import com.maan.common.LogUtil;
import com.maan.common.MyJdbcTemplate;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.LocalizedTextProvider;

public class LogInDAO extends MyJdbcTemplate {
	private static final Logger logger = LogUtil.getLogger(LogInDAO.class);
	private String query="";
	
	public String getClientIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
	
	public String[] validateUser(final String userId, String pwd,final String display, final String appId,String pwdcount){
		logger.info("Enter into validateUser()");
        String result[] = new String[4];
	    LocalizedTextProvider ltp = ActionContext.getContext().getInstance(LocalizedTextProvider.class);
        int pwdcount1=Integer.parseInt(pwdcount);
        List list=null;
        List list1=null;
        try{
	        String epwd =new com.maan.common.password.passwordEnc().crypt(pwd.substring(0, 3), pwd);// Jcrypt.crypt(pwd.substring(0, 3), pwd);
	        logger.info("Encrypted Password =>"+epwd);
	        logger.info("userId =>"+userId);
	        logger.info("password =>"+pwd);
	        query=getQuery("GET_VALID_USER");
	        logger.info("query =>"+query);
	        list1=this.mytemplate.queryForList(query,new Object[]{userId, appId});
	        if(list1 == null || list1.size()<=0){
	        	result[0] = ltp.findDefaultText("E113", Locale.ENGLISH);	//Invalid User
	        	result[2]="E113";
	        }
	        else{
	        	query=getQuery("GET_USER_INFO");
	        	logger.info("query =>"+query);
			    list=this.mytemplate.queryForList(query, new Object[]{userId, epwd, appId});
			    if(list==null || list.size()<=0){
			    	 list=this.mytemplate.queryForList(query, new Object[]{userId, pwd, appId});
			    }
			    if(list == null || list.size()<=0){
			    	 Map rs1 = (Map)list1.get(0);
			    	if(Integer.parseInt(rs1.get("PWD_COUNT").toString())==(pwdcount1-2)){
		    			result[0]= ltp.findDefaultText("E114", Locale.ENGLISH);	//This User will be lock if one more invalid Login
		    			result[2]="E114";
		    		}else if(Integer.parseInt(rs1.get("PWD_COUNT").toString())==(pwdcount1-1)){
		    			result[0]= ltp.findDefaultText("E117", Locale.ENGLISH, new String[]{Integer.toString(pwdcount1)});	//This User is locked due to invalid login of more than "+pwdcount1+" times
		    			result[2]="E117";
			    	}else{ 
			    		result[0] = ltp.findDefaultText("E115", Locale.ENGLISH);	//Invalid User for this Application
			    		result[2]="E115";
			    	}updateCount(userId,pwdcount, appId);
			     }else{
		            Map rs = (Map)list.get(0);
		            if(rs == null || "N".equals((String)rs.get("status"))){
		                result[0] = ltp.findDefaultText("E116", Locale.ENGLISH);	//User not in Active
		                result[1]=(String)rs.get("status");
		                result[2]="E116";
		            } else if(Integer.parseInt(rs.get("PWD_COUNT").toString())>pwdcount1){
		    			result[0]= ltp.findDefaultText("E117", Locale.ENGLISH, new String[]{Integer.toString(pwdcount1)}); 	//	This User is locked due to invalid login of more than "+pwdcount1+" times
		    			result[2]="E117";
		            } else if("T".equals((String)rs.get("status"))){
		    			result[0]="changepwd";
		    			result[2]="E119";
		    		}else{
		            	result[0] =null;
		                result[1]=(String)rs.get("status");
		                result[2]="E100";
		                pwdCountReset(userId, appId);
		            }
		        }
	        }
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
        return result;
	 }
	 
	 public boolean checkPasswordChange(final String userId,String userStatus,String expiTime,String expiDate, String appId){
		 logger.info("Enter into checkPasswordChange()");
		 logger.info("userId =>"+userId);
		 boolean result=false;
		 if("Y".equals(userStatus)){
			 int expiDate1=Integer.parseInt(expiDate);
			 query=getQuery("GET_VALID_PWD_DAY");
	         logger.info("query =>"+query);
			 int days=this.mytemplate.queryForObject(query, Integer.class, new Object[]{userId, appId});
			 logger.info("Password Changed Before =>"+days+" Days");
			 result=days<expiDate1;
		 }
		 if("T".equals(userStatus)){
			 int expiTime1=Integer.parseInt(expiTime);
			 query=getQuery("GET_VALID_PWD_TIME");
	         logger.info("query =>"+query);
			 int hours=this.mytemplate.queryForObject(query, Integer.class, new Object[]{userId, appId});
			 logger.info("Password Changed Before =>"+hours+" hours");
			 result=hours<expiTime1;
		 }
		 return result;
	 }
	 
	  public List <Object> getUserInfo(final AdminAction ac, String userID, String appId){
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
	 
	 public List<Object>  getUserInfo(String userId, String appId){
		 logger.info("Enter into getUserInfo()");
		 List list=null;
    	try {
    		query=getQuery("GET_USER_BASIC_INFO");
	         logger.info("query =>"+query);
    		list=this.mytemplate.queryForList(query,new Object[]{userId,appId});
        }catch(Exception e){
        	logger.error("Exception in getUserInfo => {}"+e);
        }
        logger.info("UserInfo - Exit");
    	return list;
	 }
	  
	 public List<Object>  validateMailForgot(Object[] obj){
		 logger.info("Enter into validateMailForgot()");
		 List list=null;
    	try {
    		query=getQuery("GET_MAIL_FORGOT_VALID");
	         logger.info("query =>"+query);
    		list=this.mytemplate.queryForList(query,obj);
        }catch(Exception e){
        	logger.error("Exception in validateMailForgot=> {}"+e);
        }
        logger.info("validateMailForgot - Exit");
    	return list;
	 }
	 
	  
	 public String changePassword(final String userId, String newPassword, String appId) {
		 logger.info("Enter into changePassword()");
		 String result = null;
		 newPassword = new com.maan.common.password.passwordEnc().crypt(newPassword.substring(0, 3), newPassword);
		 logger.info("Encrypted New Password => "+newPassword);
		 logger.info("userId =>"+userId);
		 query=getQuery("GET_COUNT_PWD");
         logger.info("query =>"+query);
		 int count =this.mytemplate.queryForObject(query, Integer.class, new Object[]{userId,appId,newPassword});
		 logger.info("Password Available Count => "+count);
		 if(count==0)
		 {
			 logger.info("newpwd =>"+newPassword);
			 logger.info("userId =>"+userId); 
			 query=getQuery("UPD_USER_PWD");
	         logger.info("query =>"+query);
		     int affRow=this.mytemplate.update(query, new Object[]{newPassword,userId,appId});
		     logger.info("Affected Rows => "+affRow);
		 }else
		 {
			  result ="This new Password used in last 10. Please enter new Password";
		 }
		 return result;
	}
	
	 public String getPassword(final String userId, String appId) {
		 logger.info("Enter into getPassword()");
		 final String alphabet = "Aa2Bb@3Cc#4Dd$5Ee%6Ff7Gg&8Hh9Jj2Kk=3L4Mm/5Nn@6Pp7Qq#8Rr$9Ss%2Tt3Uu&4Vv5Ww+6Xx=7Yy8Zz\\9";
		 final int N = alphabet.length();
		 String temppwd="";
		 Random r = new Random();
		 for (int i = 0; i < 10; i++) {
			 temppwd+=alphabet.charAt(r.nextInt(N));
		 }
		 query=getQuery("UPD_USER_PWD_TEMP");
         logger.info("query =>"+query);
		 String password = new com.maan.common.password.passwordEnc().crypt(temppwd.substring(0, 3), temppwd);
		 logger.info("newpwd ==>"+password+":userId ==>"+userId+":Temppassword==>"+temppwd);
	     this.mytemplate.update(query, new Object[]{password,userId, appId});
		 return temppwd;
	}
	
	 public Map<String, String> getMailDetails(String appId){
	    	Map<String, String> details = new HashMap<String, String>();
	    	logger.info("Enter into getMailDetails()");
	    	try {
	    		 query=getQuery("GET_MAIL_DETAILS");
		         logger.info("query =>"+query);
	        	List list =this.mytemplate.queryForList(query,new Object[]{appId});
	        	if(list != null && list.size()>0){
		        	details = (Map)list.get(0);
	        	}
	        }catch(Exception e){
	        	logger.error("Exception in Getting Mail Details => {}"+e);
	        }
	        logger.info("Getting Mail Details - Exit");
	    	return details;
	  }
	 
	 public String getExpireTime(String expTime){
		 logger.info("Enter into getExpireTime()");
    	String expireTime=null;
    	try {
    		 query=getQuery("GET_TEMP_PWD_EXP");
	         logger.info("query =>"+query);
        	expireTime=this.mytemplate.queryForObject(query,new Object[]{expTime},String.class).toString();
        }catch(Exception e){
        	logger.error("Exception getExpireTime => {}"+e);
        }
        logger.info("getExpireTime() - Exit");
    	return expireTime;
	 }
	 
	 public void pwdCountReset(final String user, String appId) {
		 logger.info("Enter into pwdCountReset()");
		 try{
			 query=getQuery("UPD_PWD_CNT_RESET");
	         logger.info("query =>"+query);
			 this.mytemplate.update(query, new Object[]{user, appId});
		 }catch(Exception e){
			 System.out.println(e);
		 }
	}
	
	 public void updateCount(final String user,String pwdcount, String appId) {
		 logger.info("Enter into updateCount()");
		 int pwdcount1=Integer.parseInt(pwdcount);
		 int pwdCount=0;
		 try{
			 query=getQuery("UPD_PWD_CNT");
	         logger.info("query =>"+query);
			 this.mytemplate.update(query, new Object[]{user, appId});
			 query=getQuery("GET_PWD_CNT_INVALID");
	         logger.info("query =>"+query);
			 pwdCount=this.mytemplate.queryForObject(query, Integer.class, new Object[]{user, appId});
			 if(pwdCount==pwdcount1){
				 query=getQuery("UPD_PWD_STATUS");
		         logger.info("query =>"+query);
				 this.mytemplate.update(query, new Object[]{"L",user,appId});
				 final LogInService service= new LogInService();
    			String temp="locked";
    			service.sendUserPwd(user,temp, appId);
			 }
		 }catch(Exception e){
			 System.out.println(e);
		 }
	}
	
	public List<Object>  getappList(){
		 logger.info("Enter into getappList()");
	    	List list=null;
	    	try{
	    		 query=getQuery("GET_APP_LIST");
		         logger.info("query =>"+query);
	    		list=this.mytemplate.queryForList(query);
	    	}
	    	catch(Exception e){
	    		logger.error("Exception in getappList=> {}"+e);
	    	}
	    	return list;
	 }
	 
	 public String getAppName(String appId){
		 logger.info("Enter into getAppName()");
		 String appName=null;
    	try{
    		 query=getQuery("GET_APP_INFO");
	         logger.info("query =>"+query);
    		appName=this.mytemplate.queryForObject(query,new Object[]{appId},String.class).toString();
    	}
    	catch(Exception e){
    		logger.error("Exception in getAppName=> {}"+e);
    	}
    	return appName;
    }
	
	public boolean insertSessionInfo(final String loginId, final String sessionId, final String ipAddress){
        logger.info("Enter==>insertSessionInfo()");
        int affRow=0;
        try{
        	query=getQuery("INS_SESSION_INFO");
        	affRow = this.mytemplate.update(query, new Object[]{loginId, sessionId, ipAddress});
        	logger.info("Query: " + query);
            logger.info("args => " + loginId+"	~	"+sessionId+"	~	"+ipAddress);
        }catch(Exception e){
        	logger.info("Query: " + e.getMessage());
        	//e.printStackTrace();
        }
        logger.info("Exit==>insertSessionInfo()	Result==>"+affRow);
        return affRow>0;
    }
	
	public Map<Object, Object> getUserDetail(String loginId){
		logger.info("Enter==>getUserDetail()");
		Map<Object, Object>map=new HashMap<Object, Object>();
		List list=null;
		try{
			query=getQuery("GET_USER_DETAILS");
			list=this.mytemplate.queryForList(query, new Object[]{loginId});
			if(list!=null && list.size()>0){
				map=(Map)list.get(0);
			}
			logger.info("Query==>"+query);
			logger.info("Params => " + loginId);
		}catch(Exception e){
			e.printStackTrace();
		}
		logger.info("Exit==>getUserDetail()		Result==>"+list.size());
		return map;
	}
	
	public boolean updateSessionInfo(final String userId, final String sessionId){
		logger.info("Enter==>updateSessionInfo()");
        int affRow=0;
        try{
        	query=getQuery("UPD_SESSION_INFO");
        	affRow = this.mytemplate.update(query, new Object[]{userId, sessionId});
        	logger.info("Query: " + query);
            logger.info("args => " + userId+"	~	"+sessionId);
        }catch(Exception e){
        	logger.info(e.getMessage());
        }
        logger.info("Exit==>updateSessionInfo()	Result==>"+affRow);
        return affRow>0;
    } 
}
