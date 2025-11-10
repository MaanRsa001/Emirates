package com.maan.common.interceptor;

import com.maan.common.LogUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Logger;
import org.apache.struts2.StrutsStatics;
public class SessionInterceptor  implements Interceptor {
	private static final Logger logger = LogUtil.getLogger(SessionInterceptor.class);
 
	private static final long serialVersionUID = 1L;

	public String intercept(ActionInvocation actionInvocation) throws Exception {
		final ActionContext context = actionInvocation.getInvocationContext();
        logger.info("Emirates session interceptor Before Action Invoke => "+context);
		try{
	   	    HttpServletResponse response = (HttpServletResponse) context.get(StrutsStatics.HTTP_RESPONSE);
	        HttpServletRequest request = (HttpServletRequest)context.get(StrutsStatics.HTTP_REQUEST);
	   	    HttpSession ses = request.getSession(false);
	   	    String sessId = "";
	        if(ses!=null){
	        	sessId = ses.getId();
	        	request.getHeader("Host");
	        }
	        if(response!=null){
		        response.setHeader("SET-COOKIE", "JSESSIONID=" + sessId + "; Domain=.cybersource.com;Path=/;SameSite=Strict; Secure; HttpOnly");
	            response.setHeader("X-Frame-Options", "deny");
	            response.setHeader("X-Content-Type-Options", "nosniff");
	            response.setHeader("X-XSS-Protection", "1; mode=block");
	            //response.setHeader("Content-Security-Policy", "default-src 'none'; script-src 'self'; connect-src 'self'; img-src 'self'; style-src 'self';");
	            response.addHeader("Content-Security-Policy-Report-Only", "default-src 'self'; object-src 'none'; style-src 'self' 'unsafe-inline';  media-src 'none'; frame-src 'none';  connect-src 'self';");
	            response.addHeader("X-Content-Security-Policy-Report-Only", "default-src 'self'; object-src 'none'; style-src 'self' 'unsafe-inline'; media-src 'none'; frame-src 'none'; connect-src 'self'; ");
	        }
	    }catch(Exception e){
	    	e.printStackTrace();
		}
       //result = actionInvocation.invoke();
        Map<String, Object> session = context.getSession();
        String result;
        
       if(session.get("user") == null) {
             result = "SessionExpired";
        }else{
            result = actionInvocation.invoke();
        }

       logger.info("Emirates session interceptor after Action Invoke => "+context);
        return result;
    }

    public void destroy() {
        
    }

    public void init() {
        
    }
} 
