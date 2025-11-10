package com.maan.adminnew.closeTrn;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import com.maan.adminnew.common.CommonService;
import com.maan.adminnew.closeTrn.CloseTrnBean;
import com.maan.adminnew.closeTrn.CloseTrnService;
import com.maan.common.Validation;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CloseTrnAction extends ActionSupport implements ModelDriven<CloseTrnBean>
{
	private static final long serialVersionUID = 1L;
	private CloseTrnBean bean = new CloseTrnBean();
	Validation validation=new Validation();
	CloseTrnService service=new CloseTrnService();
	final CommonService cservice=new CommonService();
	Map<String, Object> session=ActionContext.getContext().getSession();
	HttpServletRequest request= ServletActionContext.getRequest();
	HttpServletResponse responce= ServletActionContext.getResponse();
	List<Object> transDetList=null;
	ServletContext context=request.getSession().getServletContext();
	String branchCode=(String)session.get("BranchCode");
	String login_id=(String)session.get("user");
	String appId=(String)session.get("AppId");
	
	public String enterDetail(){
		service.viewTrnDetail(bean,branchCode,"1");
		return "enterTrn";
	}
	
	public String viewList(){
		bean.setReqFrom("list");
		bean.setViewList(service.listViewDetail(branchCode));
		return "enterTrn";
	}
	
	public String viewTrn(){
		return "enterTrn";
	}
	public String saveTrn()
	{
		validDate();
		if(!hasActionErrors()){
			int i=service.saveCLoseTrndetails(bean, branchCode, login_id, (String)session.get("AdminCountryId"));
			if(i>0){
				bean.setReqFrom("insSuccess");
			}else{
				addActionError("Problem Occured");
			}
		}
		return "enterTrn";
	}
	public void validDate() {
		if(StringUtils.isBlank(bean.getOpenDate())){
			addActionError("Please select Policy Open Date");
		}if(StringUtils.isBlank(bean.getCloseDate())){
			addActionError("Please select Policy Close Date");
		}if(StringUtils.isBlank(bean.getMonthCloseDate())){
			addActionError("Please select Month Close Date");
		}
		if(!hasActionErrors()){
			try{
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		    	Date openDate = sdf.parse(bean.getOpenDate());
		    	Date closeDate = sdf.parse(bean.getCloseDate());
		    	Date monthCloseDate = sdf.parse(bean.getMonthCloseDate());
		    	if(openDate.after(closeDate))
		    		addActionError("Policy Close Date should be greater than or equal to Policy Open Date");
		    	if(openDate.after(monthCloseDate))
		    		addActionError("Month Close Date should be greater than or equal to Policy Open Date");
		    	if(monthCloseDate.after(closeDate) || monthCloseDate.equals(closeDate))
		    		addActionError("Policy Close Date should be greater than Month Close Date");
			}catch(Exception e){
				e.printStackTrace();
			}
		} if(hasActionErrors()){
			if(StringUtils.isNotBlank(bean.getOpenDate()))
				bean.setOpenDate(dateFormat(bean.getOpenDate()));
			if(StringUtils.isNotBlank(bean.getCloseDate()))
				bean.setCloseDate(dateFormat(bean.getCloseDate()));
			if(StringUtils.isNotBlank(bean.getMonthCloseDate()))
				bean.setMonthCloseDate(dateFormat(bean.getMonthCloseDate()));
		}
	}
	public String dateFormat(String name){
		String arr[]=name.split("/");
		return arr[1]+"/"+arr[0]+"/"+arr[2];
	}
	
	public CloseTrnBean getModel() {
		return bean;
	}
	
}