package com.maan.login;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.maan.common.LogUtil;
import com.maan.common.exception.BaseException;
import com.maan.common.exception.ExceptionConstants;
//import com.maan.login.service.LoginService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {
	private static final Logger logger = LogUtil.getLogger(LoginAction.class);

	private static final long serialVersionUID = 1L;
	private String loginId;
	private String password;
	private String userType;
	private String menuIds;
	private String productIds;
	
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getMenuIds() {
		return menuIds;
	}

	public void setMenuIds(String menuIds) {
		this.menuIds = menuIds;
	}

	public String getProductIds() {
		return productIds;
	}

	public void setProductIds(String productIds) {
		this.productIds = productIds;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	//LoginService service=new LoginService();
	Map session = ActionContext.getContext().getSession();
	HttpServletRequest request= ServletActionContext.getRequest();
	public String login()throws BaseException {
		logger.info("login - Enter");
		System.out.println("========");
		boolean status=false;
		try
		{
			/*if(loginId.length()<=0)
				addActionError(getText("error.login.loginId"));
			if(password.length()<=0)
				addActionError(getText("error.login.password"));
			*/
			if(getActionErrors().isEmpty())
			{
				//List list=service.userAuth(loginId, password);
				/*if(list==null || list.isEmpty())
					addActionError(getText("error.login.invalid"));
				else
				{*/
					//status=service.insertSessionInfo(loginId, request.getSession().getId());
					//Map loginInfo=(Map)list.get(0);
					session.put("LOGIN_ID", "admin");
					session.put("USER_TYPE", "admin");
					session.put("MENU_LIST", "");
				//}
			}
		}catch(Exception e)
		{
			logger.debug(e);
			throw new BaseException(e, ExceptionConstants.OTHER_ERROR);
		}
		logger.info("login - Exit || Result: "+status);
		/*if(!getActionErrors().isEmpty())
			return INPUT;
		else*/
		System.out.println("Exit sfdsdf"+SUCCESS);
			return SUCCESS;
    }

    public String logout() throws BaseException{
    	logger.info("logout - Enter");
    	loginId=(String)session.get("LOGIN_ID");
    	//boolean status=service.updateSessionInfo(loginId, request.getSession().getId());
    	session.remove("LOGIN_ID");
        session.remove("USERTYPE");
        session.remove("MENU_LIST");
        if (session instanceof org.apache.struts2.dispatcher.SessionMap) {
            try {
                ((org.apache.struts2.dispatcher.SessionMap) session).invalidate();
            } catch (IllegalStateException e) {
                logger.info(e);
            }
        }
        //logger.info("logout - Exit || Result: "+status);
    	return INPUT;
    }
    
}
