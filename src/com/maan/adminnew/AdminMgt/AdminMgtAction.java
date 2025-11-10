package com.maan.adminnew.AdminMgt;

import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import com.maan.adminnew.common.CommonService;
import com.maan.common.Validation;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminMgtAction extends ActionSupport implements ModelDriven<AdminMgtBean>
{
	private static final long serialVersionUID = 1589654L;
	private AdminMgtBean bean = new AdminMgtBean();
	Validation validation=new Validation();
	AdminMgtService service=new AdminMgtService();
	CommonService cservice=new CommonService();
	Map<String, Object> session=ActionContext.getContext().getSession();
	String branchCode=(String)session.get("BranchCode");
	String appId=(String)session.get("AppId");
	String login_id=(String)session.get("user");
	
	public AdminMgtBean getModel() {
		return bean;
	}
	public List<Map<String, Object>>getUtypeList(){
		return service.UtypeList(branchCode, appId);
	}
	public List<Map<String, Object>>getMenuList(){
		return service.getMenuList(bean,branchCode);
	}
	public List<Map<String, Object>>getAdminList(){
		return service.adminList(bean, branchCode, appId);
	}
	public List<Map<String,Object>>getProductList(){
		return cservice.getProductsDET(branchCode, "");
	}
	public List<Map<String, Object>>getBrokerList(){
		return cservice.getAdminBrokerList(branchCode, appId);
	}
	
	public List<Map<String, Object>> getDashBoard(){
		return service.getDashBoard(login_id,bean);
	}
	
	public String home(){
		return "adminHome";
	}
	
	public String adminMgt(){
		bean.setFrom("alist");
		bean.setFrom1("mlist");
		return "adminMgt";
	}
	
	public String editadmin(){
		bean.setFrom("aform");
		bean.setFrom1("mlist");
		bean.setIndex("0");
		if("edit".equals(bean.getMode()))
			service.getAdminInfo(bean, branchCode, appId);
		bean.setBranch(service.getBranch(branchCode));
		return "adminMgt";
	}
	
	public String editMenu(){
		bean.setFrom("alist");
		bean.setFrom1("mform");
		bean.setIndex("1");
		if("edit".equals(bean.getMode1()))
			service.getMenuInfo(bean, branchCode);
		return "adminMgt";
	}
	
	public String menuSave(){
		bean.setFrom("alist");
		bean.setFrom1("mform");
		bean.setIndex("1");
		try{
			validatemenu();
			if(!hasActionErrors()){
				if("new".equals(bean.getMode1())){
					service.insNewMenu(bean, branchCode);
					bean.setFrom1("addSuccess");
				}else if("edit".equals(bean.getMode1())){
					service.updateMenu(bean, branchCode);
					bean.setFrom1("updateSuccess");
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "adminMgt";
	}
	public String adminSave(){
		bean.setFrom("aform");
		bean.setFrom1("mlist");
		bean.setIndex("0");
		String pid="";
		if(bean.getProductID()!=null && bean.getProductID().length>0){
			for(String str: bean.getProductID())
				pid=pid+","+str;
			pid=pid.substring(1);
		}
		bean.setProduct(pid);
		try{
			validateadmin();
			if(!hasActionErrors()){
				if("new".equals(bean.getMode())){
					service.insNewAdmin(bean, branchCode, appId, login_id);
					bean.setFrom("addSuccess");
				}else if("edit".equals(bean.getMode())){
					service.updateAdmin(bean, branchCode, appId, login_id);
					bean.setFrom("updateSuccess");
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "adminMgt";
	}
	
	public String menuSelection(){
		return "menuSelect";
	}
	
	public String brokerSelection(){
		return "brokerSelect";
	}
	
	public String ajaxList(){
		return "ajax";
	}
	
    public void validatemenu(){
    	if(StringUtils.isBlank(bean.getMname()))
    		addActionError(getText("error.menu.name.required"));
    	if(StringUtils.isBlank(bean.getUrlPath()))
    		addActionError(getText("error.url.path.required"));
    	if(StringUtils.isBlank(bean.getParent()))
    		addActionError(getText("error.parent.required"));
    	if(StringUtils.isBlank(bean.getStatus()))
    		addActionError(getText("error.status.required"));
    }
    
    public void validateadmin(){
    	if(StringUtils.isBlank(bean.getUtype()))
    		addActionError(getText("error.admin.utype.required"));
    	if(StringUtils.isBlank(bean.getUname()))
    		addActionError(getText("error.admin.uname.required"));
    	if(StringUtils.isBlank(bean.getLoginID()))
    		addActionError(getText("error.admin.loginid.required"));
    	else if(StringUtils.contains(bean.getLoginID(), " "))
	 		addActionError("Login Id should not contain white spaces");
    	if("new".equals(bean.getMode())){
    		if(cservice.getAdminInfo(bean.getLoginID()).size()>0)
        		addActionError(getText("error.loginid.notavailable"));
	    	if(StringUtils.isBlank(bean.getPwd()))
	    		addActionError(getText("error.admin.pwd.required"));
	    	else if(StringUtils.contains(bean.getPwd(), " "))
		 		addActionError("Password should not contain white spaces");
    	}if(StringUtils.isBlank(bean.getProduct()))
    		addActionError(getText("error.admin.product.required"));
    	if(StringUtils.isBlank(bean.getMid()))
    		addActionError(getText("error.admin.mid.required"));
    	//if(StringUtils.isBlank(bean.getBroker()))
    		//addActionError(getText("error.admin.broker.required"));
    	if(StringUtils.isBlank(bean.getEmail()))
    		addActionError(getText("error.admin.email.required"));
    	else if(StringUtils.contains(bean.getEmail(), " "))
	 		addActionError("Email Id should not contain white spaces");
    	else if("invalid".equalsIgnoreCase(validation.emailValidate(bean.getEmail())))
    		addActionError(getText("error.admin.email.invalid"));
    	if(StringUtils.isBlank(bean.getStatus()))
    		addActionError(getText("error.admin.status.required"));
    }
}