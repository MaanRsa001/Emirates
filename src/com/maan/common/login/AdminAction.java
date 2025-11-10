package com.maan.common.login;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;

import com.maan.common.LogUtil;
import com.opensymphony.xwork2.ActionSupport;

public class AdminAction extends ActionSupport{
	private static final long serialVersionUID = 100123L;
	private static final Logger logger = LogUtil.getLogger(AdminAction.class);
	AdminService service=new AdminService();
	List<Map<String, Object>> list=null;
	Map<String,String> mailList=null;
	private String display="";
	private String username;
	private String userID;
	private String pwd;
	private String appId;
	private String appIds;
	private String status;
	private String mail;
	private String userType;
	private String mailcc;
    private String smtphost;
    private String smtpuser;
    private String smtppwd;
    private String pwdexp;
    private String startdate;
    private String reqFrom;
    private String from;
    List <Map<String, Object>> userInfo;
	List <Map<String, Object>> existUserList;
    List <Map<String, Object>> existUserListAjax;


	public String getAppIds() {
		return appIds;
	}

	public void setAppIds(String appIds) {
		this.appIds = appIds;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public List<Map<String, Object>> getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(List<Map<String, Object>> userInfo) {
		this.userInfo = userInfo;
	}
	
	public void setExistUserListAjax(List<Map<String, Object>> existUserListAjax) {
		this.existUserListAjax = existUserListAjax;
	}

    public List <Map<String, Object>> getExistUserListAjax() {
		return existUserListAjax;
	}

	public List <Map<String, Object>> getExistUserList() {
		return existUserList;
	}

	public String getReqFrom() {
		return reqFrom;
	}

	public void setReqFrom(String reqFrom) {
		this.reqFrom = reqFrom;
	}

    public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getMailcc() {
		return mailcc;
	}

	public void setMailcc(String mailcc) {
		this.mailcc = mailcc;
	}

	public String getSmtphost() {
		return smtphost;
	}

	public void setSmtphost(String smtphost) {
		this.smtphost = smtphost;
	}

	public String getSmtpuser() {
		return smtpuser;
	}

	public void setSmtpuser(String smtpuser) {
		this.smtpuser = smtpuser;
	}

	public String getSmtppwd() {
		return smtppwd;
	}

	public void setSmtppwd(String smtppwd) {
		this.smtppwd = smtppwd;
	}

	public String getPwdexp() {
		return pwdexp;
	}

	public void setPwdexp(String pwdexp) {
		this.pwdexp = pwdexp;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List <Map<String, Object>> getList() {
		return list;
	}
	
	public List <Map<String, Object>> getApplList() {
		return service.getappList();
	}
	
    public String home(){
    	return INPUT;
    }
    
    public String mailMaster(){
    	return "MailMaster";
    }
    
    public String newUser(){
    	if("edit".equals(reqFrom))
    		userInfo=service.getUserInfo(this,userID, appId);
    	return "NewUser";
    }
    
    public String getappList(){
    	logger.info("Entered in getappList");
    	list=service.getappList();
    	return "appList";
    }
    
    public String addnew(){
    	logger.info("Entered in addnew");
    	if("".equals(reqFrom)){
	    	if (username == null || "".equals(username.trim()))
	            addActionError(getText("error.admin.user.name"));
	    	if (userID == null || "".equals(userID.trim()))
	            addActionError(getText("error.admin.new.user.loginname"));
	        if (pwd == null || "".equals(pwd.trim()))
	            addActionError(getText("error.admin.new.user.pwd"));
	        if(0!=service.getLoginAvailable(userID, appIds))
				addActionError(getText("error.admin.new.login.notavailable"));
    	}if (appIds == null || "".equals(appIds.trim()))
           addActionError(getText("error.admin.new.user.allowedapp"));
		if (StringUtils.isEmpty(mail) || !validateEmailAddress(mail))
           addActionError(getText("error.admin.new.user.mail"));
		if (userType== null || "".equals(userType.trim()))
           addActionError(getText("error.admin.new.user.userType"));
		if (status== null || "".equals(status.trim()))
           addActionError(getText("error.admin.new.user.status"));
		if (startdate== null || "".equals(startdate.trim()))
	       addActionError(getText("error.admin.new.user.startdate"));
		try{
			Object[] obj=null;
	    	if (getActionErrors().size() <= 0) {
	    		if("edit".equals(reqFrom)){
	    			obj=new Object[]{appIds, status, mail, userType, startdate, userID, appId};
	    		}else{
	    			String newPassword = new com.maan.common.password.passwordEnc().crypt(pwd.substring(0, 3),pwd);
				    logger.info("Encrypted Password =>"+newPassword);
	    			obj=new Object[]{username, userID, newPassword, appIds, status, mail, userType, startdate};
	    		}
	    		service.insertUser(obj, reqFrom);
	    		display="success";
	    	}
		}
		catch(Exception e){
			e.printStackTrace();
		}
    	return "NewUser";
    }
    
    public boolean validateEmailAddress(String email){
    	Pattern pattern = Pattern.compile("^[A-Za-z0-9_\\+-]+(\\.[A-Za-z0-9_\\+-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*\\.([A-Za-z]{2,4})$");
    	boolean stat = true;
        if(email.contains(",")){
        	String[] emails = email.split(",");        	
        	for(String ids:emails){
        		Matcher matcher = pattern.matcher(ids);
        		if(!matcher.matches()){
        			stat = false;
        			break;
        		}
        	}
    	}else{
            Matcher matcher = pattern.matcher(email);
            stat = matcher.matches();
    	}
        return stat;
    }

    public String mailSave(){
    	logger.info("Entered in mailSave");
    	if (!StringUtils.isEmpty(mailcc) && !validateEmailAddress(mailcc))
           addActionError("Please enter valid E-Mail Id for MAIL CC Field");
		if (StringUtils.isEmpty(smtphost))
           addActionError("Please enter Host Name Field");
		if (StringUtils.isEmpty(smtpuser))
           addActionError("Please enter valid E-Mail Id for Host User name Field");
		/*if (StringUtils.isEmpty(smtppwd)) {
           addActionError("Please enter Host User Password Field");
		}*/if (StringUtils.isEmpty(pwdexp))
           addActionError("Please enter Temporary Password Expiry Time Field");
		if (StringUtils.isEmpty(appId))
           addActionError("Please select Application");
    	if (getActionErrors().size() <= 0) {
    		Object[] obj=new Object[6];
    		obj[0]=mailcc;
    		obj[1]=smtphost;
    		obj[2]=smtpuser;
    		obj[3]=smtppwd;
    		obj[4]=pwdexp;
    		obj[5]=appId;
    		service.mailSave(obj);
    		display="success";
    	}
    	return "MailMaster";
    }
    
    public String ajaxValue(){
    	String forward="existingUser";
    	if(from==null)from="";
    	if("LoadFromExistUser".equals(reqFrom))
    		existUserListAjax=service.getexistUser(appId);
    	else if("LoadFromApp".equals(reqFrom))
        	service.getmailList(this);
    	if("".equals(from) || "ajax".equalsIgnoreCase(from))
    		forward="adminAjax";
    	return forward;
    }
    
    public String existUser(){
    	existUserList=service.getexistUser(appId);
    	return "existingUser";
    }
    
}