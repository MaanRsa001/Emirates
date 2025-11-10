package com.maan.common;

import java.util.Map;

import org.apache.logging.log4j.Logger;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class SessionInterceptor  implements Interceptor {

	private static final long serialVersionUID = 12345L;
	private static final Logger logger = LogUtil.getLogger(SessionInterceptor.class);
       
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        Map<String, Object> session = actionInvocation.getInvocationContext().getSession();
        logger.debug(" Login Id - {}",(String)session.get("LoginId"));
        String result;
        if(session.get("LoginId") == null) {
             result = "SessionExpired";
        }else{
            result = actionInvocation.invoke();
        }
        return result;
    }

    public void destroy() {
    }

    public void init(){
    }
} 
