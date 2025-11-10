package com.maan.adminnew;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.maan.adminnew.common.CommonService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class HomePage extends ActionSupport
{
	private static final long serialVersionUID = 1L;
	List<Object> userInfo=new ArrayList<Object>(); 
	Map<String, Object> session = ActionContext.getContext().getSession();
	private String user;
	private String gid;
	
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	CommonService service=new CommonService();
	
    public String gethome(){
    	
    	return "success";
    }
    
    public String home(){
    	return "success";
    }
} 