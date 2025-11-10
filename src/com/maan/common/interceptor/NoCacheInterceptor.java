package com.maan.common.interceptor;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Logger;
import org.apache.struts2.StrutsStatics;

import com.maan.common.LogUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;


public class NoCacheInterceptor implements Interceptor {

	private static final long serialVersionUID = 1321L;
	private static final Logger logger = LogUtil.getLogger(NoCacheInterceptor.class);
    private static List<String> hostArray=new ArrayList<String>();
    static {
    	hostArray.add("eicmarine.*");
    	hostArray.add("192.*");
    	hostArray.add("localhost.*");
    }
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        ActionContext context = actionInvocation.getInvocationContext();
        HttpServletRequest request = (HttpServletRequest)context.get(StrutsStatics.HTTP_REQUEST);
        HttpServletResponse response = (HttpServletResponse) context.get(StrutsStatics.HTTP_RESPONSE);
        HttpSession ses = request.getSession(false);
        logger.info("Emirates NoCache interceptor Before Action Invoke => "+ses);
        String sessionid = "";
        String result = null;
        String host = request.getHeader("Host");
        int indexOf =request.getHeader("Host").indexOf(".")==-1?9:request.getHeader("Host").indexOf(".");
        if(!hostArray.contains( request.getHeader("Host").substring(0, indexOf)+".*" ))
        	return  "SessionExpired";
        if(ses!=null){
            sessionid = ses.getId();
        }
        if(response!=null){
            response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Expires", "-1");
            response.setHeader("SET-COOKIE", "JSESSIONID=" + sessionid + ";Domain=.cybersource.com;Path=/; SameSite=Strict; HttpOnly");
            response.setHeader("X-Frame-Options", "deny");
            response.setHeader("X-Content-Type-Options", "nosniff");
            response.setHeader("X-XSS-Protection", "1; mode=block");
            response.addHeader("Content-Security-Policy-Report-Only", "default-src 'self'; object-src 'none'; style-src 'self' 'unsafe-inline';  media-src 'none'; frame-src 'none';  connect-src 'self';");
            response.addHeader("X-Content-Security-Policy-Report-Only", "default-src 'self'; object-src 'none'; style-src 'self' 'unsafe-inline'; media-src 'none'; frame-src 'none'; connect-src 'self'; ");

            //response.setHeader("Content-Security-Policy", "default-src 'none'; script-src 'self'; connect-src 'self'; img-src 'self'; style-src 'self';");
        }
        result = (result==null?actionInvocation.invoke():result);
       logger.info("Emirates NoCache interceptor after Action Invoke => "+ses);
        return result;
    }

    public void destroy() {
        
    }

    public void init() {
        
    }
}
