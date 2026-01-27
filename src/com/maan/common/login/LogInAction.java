package com.maan.common.login;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.maan.adminnew.common.CommonService;
import com.maan.common.LogUtil;
import com.maan.opencover.bean.newCoverBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.LocalizedTextProvider;
import com.opensymphony.xwork2.ModelDriven;
import com.sun.prism.paint.Color;

import nl.captcha.Captcha;

public class LogInAction extends ActionSupport  implements SessionAware, ServletRequestAware, ModelDriven<CommonBean>{
	private static final Logger logger = LogUtil.getLogger(LogInAction.class);
	private static final long serialVersionUID = 10001L;
	private HttpServletRequest request = ServletActionContext.getRequest();
	ServletContext context=request.getSession().getServletContext();
	private LogInService service=new LogInService();
	private CommonBean bean=new CommonBean(); 
	CommonService cservice=new CommonService();
	private Map<String, Object> session = null;
	

	
	private String appId="16";
    
	public void setSession(Map<String, Object> map) {
	    this.session = map;
	}
	public void setServletRequest(HttpServletRequest hsr) {
	    this.request = hsr;
	}

	public String page(){
		return "page";
    }
	
	public boolean validateCaptcha(String captchavalue, Captcha captcha) {
		
		try {
		if (!captcha.isCorrect(captchavalue)) 
			return false;
		} catch (Exception e) {
		return false;
		}
		return true;
		}

	
	public String submit() {
        String result="page";
        logger.info("Enter==>submit()");
        //validlogin();
        Map <String, String>rs1=service.getMailDetails(appId);
        String pwdlen[]=rs1.get("PWD_LEN").toString().split("-");
        session.put("expiTime", rs1.get("EXP_TIME"));
        session.put("expiDate", rs1.get("EXP_DATE"));
        session.put("pwdCount", rs1.get("PWD_CNT"));
        session.put("pwdLenMin", pwdlen[0]);
        session.put("pwdLenMax", pwdlen[1]);
        session.put("appID", appId);
	    if (getActionErrors().size() <= 0) {
	    	String[] statuses=service.validateUser(bean.getLoginId(), bean.getPwd(),"",(String)session.get("appID"),(String) session.get("pwdCount"));//===>check method through UserId, Password
	    	String status1=statuses[0];
	        if(status1==null){
	        	String userStatus=statuses[1];
	        	boolean pass=service.checkPasswordChange(bean.getLoginId(), userStatus,(String) session.get("expiTime"),(String) session.get("expiDate"),(String) session.get("appID"));
	        	if(pass){
	        		List userDetails=service.getUserInfo(bean.getLoginId(), (String)session.get("appID"));
					 if (userDetails == null || userDetails.size()<=0) 
			        	addActionError(getText("E110"));
			         else{
			        	if("changepwd".equalsIgnoreCase(bean.getStatus())){
			        		bean.setStatus("changepwd");
							result = "changePWD";
			        	}else{
			        		service.insertSessionInfo(bean.getLoginId(), request.getSession(false).getId(), service.getClientIpAddr(request));
			        		if (userDetails != null && userDetails.size()>0){
			        			Map rs=(Map)userDetails.get(0);
			                    if("admin".equalsIgnoreCase(rs.get("USERTYPE").toString())){
		                    		//session.put("MenuList", cservice.getMenuList(rs.get("MENU_ID").toString(), rs.get("BRANCH_CODE").toString(),"41"));
			                    	session.put("MenuList", cservice.getMenuList(rs.get("MENU_ID").toString(), rs.get("BELONGING_BRANCH").toString(),"41"));
		                    		session.put("user", rs.get("LOGIN_ID"));
		                    		session.put("user1", rs.get("USERTYPE"));
		                    		session.put("usertype", rs.get("USERTYPE"));
		                    		session.put("BranchCode", rs.get("BRANCH_CODE"));
		                    		session.put("AdminBranchCode", rs.get("BRANCH_CODE"));
		                    		session.put("LoginBranchCode", rs.get("BRANCH_CODE"));
		                    		session.put("adminBranch", rs.get("BRANCH_CODE"));
		                    		session.put("branchName", rs.get("BRANCH_NAME"));
		                    		session.put("AdminCountryId", rs.get("COUNTRY_ID").toString());
		                    		session.put("AppId", "2");
		                    		session.put("ses", request.getSession(false).getId());
		                    		session.put("userLoginMode", context.getRealPath("").indexOf("Test")!=-1?"Test":"Live");
		                    		session.put("swidth", bean.getSwidth());
		                    		session.put("BrokerDetails", cservice.getBrokerUserDetails(rs.get("BRANCH_CODE").toString()));
		                    		
		                    		String[][] currenctDetials = new newCoverBean().getCurrencyName(rs.get("BRANCH_CODE").toString());
		                    		session.put("currencyType",currenctDetials[0][0]!=null?currenctDetials[0][0]:"AED");
		                    		session.put("decimalPlace",(currenctDetials[0][1]!=null?currenctDetials[0][1]:"2"));
		                    		
		                    		session.put("BelongingBranch",rs.get("BELONGING_BRANCH"));
		                    		result = "adminHome";
		                    	}
			                    else{
			                    	session.put("ses", request.getSession(false).getId());
		                    		session.put("user1", "brokers");
		                    		session.put("rsa_type","s");
		                    		session.put("usertype", rs.get("USERTYPE"));
		                    		session.put("user", rs.get("LOGIN_ID"));
		                    		session.put("userLoginMode", context.getRealPath("").indexOf("Test")!=-1?"Test":"Live");
		                    		session.put("swidth", bean.getSwidth());
		                    		
		                    		String[][] currenctDetials = new newCoverBean().getCurrencyName(rs.get("BRANCH_CODE").toString());
		                    		session.put("currencyType",currenctDetials[0][0]!=null?currenctDetials[0][0]:"AED");
		                    		session.put("decimalPlace",(currenctDetials[0][1]!=null?currenctDetials[0][1]:"2"));
		                    		
		                    		session.put("BelongingBranch",rs.get("BELONGING_BRANCH"));
			                    	result="home";
			                    }
							}
			        	}
			        }
				}else{
					bean.setStatus("changepwd");
						result = "changePWD";
					}
	        }
	        else if("changepwd".equalsIgnoreCase(status1)){
	        	bean.setStatus("changepwd");
				result = "changePWD";
			}
	        else if("changePwd".equalsIgnoreCase(bean.getStatus())){
	        	addActionError(status1);
				result = "changePWD";
			}
	        else{
	        	addActionError(status1);
	        	result = INPUT;
			}
	    }
	    else if("changePwd".equalsIgnoreCase(bean.getStatus())){
	    	result = "changePWD";
	    }
	    else{
	    	result = INPUT;
	    }
	    if("changePWD".equals(result)){

		    LocalizedTextProvider ltp = ActionContext.getContext().getInstance(LocalizedTextProvider.class);
	    	 bean.setPwdMsg(ltp.findDefaultText("E104", Locale.ENGLISH, new String[]{pwdlen[0], pwdlen[1]}));
	    }
        logger.info("Login Submit Method - Exit");
        return result;
    }
	
	public String pwdChange() {
		logger.info("Enter==>pwdChange()");
		String status1;
		bean.setStatus("changepwd");
    	String returnResult = "changePWD";
    	if (bean.getNewpwd() == null || "".equals(bean.getNewpwd().trim())) {
	        addActionError(getText("E101"));
		}if (bean.getRepwd() == null || "".equals(bean.getRepwd().trim())) {
	        addActionError(getText("E102"));	//Please Enter Confirm Password
		}else if (!bean.getRepwd().equals(bean.getRepwd())) {
	        addActionError(getText("E103"));	//Both Passwords are Different
 		}else if(!validPassword((bean.getRepwd()))){
		    LocalizedTextProvider ltp = ActionContext.getContext().getInstance(LocalizedTextProvider.class);
			addActionError(ltp.findDefaultText("E104", Locale.ENGLISH, new String[]{(String)session.get("pwdLenMin"), (String)session.get("pwdLenMax")}));	//New Password Should contain one Uppercase, one lowercase, one number & one special character(@#$%)
		}
    	if (getActionErrors().size() <= 0) {
    		status1=service.changePassword(bean.getLoginId(),bean.getRepwd(), appId);
    		if(status1==null){
    			bean.setStatus("success");
    		}
	    	else{
	    		addActionError(status1);
	    	}
    	}
    	logger.info("Exit==>pwdChange()");
        return returnResult;
    	
    }
	public String pwdForgot(){
		logger.info("Enter==>pwdForgot()");
		if (bean.getLoginId() == null || "".equals(bean.getLoginId().trim())) {
            addActionError(getText("E105"));	//Please Enter UserName
        }if (bean.getMailId() == null || "".equals(bean.getMailId().trim())) {
            addActionError(getText("E106"));	//Please Enter Email Id
        } if (StringUtils.isEmpty(bean.getMailId()) || !validEmail((bean.getMailId()))) {
            addActionError(getText("E108"));	//Please Enter valid Email Id
        }if (getActionErrors().size() <= 0) {
        	Object obj[]={bean.getLoginId(),bean.getMailId(), appId};
        	List list=service.validateMailForgot(obj);
        	if(list!=null && list.size()>0){
				String temp="tempPwd";
		    	String status1=service.sendUserPwd(bean.getLoginId(),temp, appId);
		    	if (status1 == null) {
		    		bean.setStatus("success");
		    	}
		    	if(status1!=null){
		    		addActionError(status1);
		    	}
        	}
        	else if(list==null || list.size()==0){
        		addActionError(getText("E109"));	//UserName and Email Id not match for this application
        	}
	    	logger.info("Exit==>pwdForgot()");
		}
        return "forgotPWD";
    }
	
	public String option(){
		String returnResult=INPUT;
		if("changePwd".equalsIgnoreCase(bean.getStatus())) {
			returnResult="changePWD";
		}else if("forgotPwd".equalsIgnoreCase(bean.getStatus())) {
			returnResult="forgotPWD";
		}
		 Map rs1=service.getMailDetails(appId);
		 String pwdlen[]=rs1.get("PWD_LEN").toString().split("-");
		    LocalizedTextProvider ltp = ActionContext.getContext().getInstance(LocalizedTextProvider.class);
		 bean.setPwdMsg(ltp.findDefaultText("E104", Locale.ENGLISH, new String[]{pwdlen[0], pwdlen[1]}));
    	return returnResult;
    }
	
	public String out(){
		logger.info("Enter==> Logout()");
        bean.setLoginId((String) session.get("user"));
        service.updateSessionInfo(bean.getLoginId(), request.getSession().getId());
        ((SessionMap<String, Object>) this.session).invalidate();
        logger.info("Exit==> Logout()");
		return "page";
    }
	
	public CommonBean getModel() {
		return bean;
	}
	
	public boolean validPassword(String newpassword){
    	Pattern pattern=Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{12,20})");
    	Matcher matcher = pattern.matcher(newpassword);
    	return matcher.matches();
	}
	public boolean validEmail(String email){
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
	
	public void validlogin(){
		Captcha	captcha= (Captcha) session.get(Captcha.NAME);		
		if(validateCaptcha(bean.getCaptchavalue(), captcha)) {
			if(StringUtils.isEmpty(bean.getLoginId())){
				addActionError(getText("loginid.empty"));
			}if(StringUtils.isEmpty(bean.getPwd())){
				addActionError(getText("pwd.empty"));
			}
		}else {
			addActionError("Invalid Valid Captcha");
		}
	}
	 
}
