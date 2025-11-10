package com.maan.adminnew.customerManagement;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Logger;

import com.maan.common.LogUtil;
import com.maan.common.Validation;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CustomerMgtAction extends ActionSupport implements ModelDriven<CustomerMgtBean>
{
	private static final Logger logger = LogUtil.getLogger(CustomerMgtAction.class);
	private static final long serialVersionUID = 1L;
	private CustomerMgtBean bean = new CustomerMgtBean();
	Validation validation=new Validation();
	CustomerMgtService service=new CustomerMgtService();
	Map<String, Object> session=ActionContext.getContext().getSession();
	String branchCode=(String)session.get("BranchCode");
	String login_id=(String)session.get("user");
	private List <Map<String, Object>> customerList;
	private List <Map<String, Object>> customerInfo;
	private List <Map<String, Object>> commisionDetails;
	private List <Map<String, Object>> openCover;
	
	public List<Map<String, Object>> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List<Map<String, Object>> customerList) {
		this.customerList = customerList;
	}

	public List<Map<String, Object>> getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(List<Map<String, Object>> customerInfo) {
		this.customerInfo = customerInfo;
	}

	public List<Map<String, Object>> getCommisionDetails() {
		return commisionDetails;
	}

	public void setCommisionDetails(List<Map<String, Object>> commisionDetails) {
		this.commisionDetails = commisionDetails;
	}

	public List<Map<String, Object>> getOpenCover() {
		return openCover;
	}

	public void setOpenCover(List<Map<String, Object>> openCover) {
		this.openCover = openCover;
	}

	public CustomerMgtBean getModel() {
		return bean;
	}
	
	public String getABList(){
    	logger.info("ENTER-->Method to getABList");
    	customerList=service.getAdminCustomerList(bean.getAgencyCode());
		logger.info("getABList() - Exit");
		/*if("mainTab".equals(bean.getMode()))
			return "userListAjax";
		else */
		return "customerList";
    }
	
	public String view(){
    	logger.info("Method to view");
    	customerInfo=service.getCustomerDetails(bean);
    	//commisionDetails=service.getCommisionData(bean.getCagencyCode());
    	openCover=service.getOpenCoverList(bean);
    	if("opencover".equals(bean.getReqFrom()))
    		bean.setIndex("1");
		logger.info("view() - Exit");
    	return "view";
    }
	
	public String getCustomerAjax(){
    	if("opencover".equals(bean.getReqFrom())){
    		openCover=service.getOpenCoverList(bean);
    	}else if("customerLists".equals(bean.getReqFrom())){
        	customerList=service.getCustomerListAjax(bean.getAgencyCode(), bean.getSearchBy(), bean.getSearchValue());
    	}
    	return "adminAjax";
    }
	
	public String edit(){
		logger.info("Method to edit");
		try{
			if(!"new".equals(bean.getMode())){
		    	customerInfo=service.getCustomerDetails(bean);
		    	commisionDetails=service.getCommisionData(bean.getUagencyCode());
			}
			logger.info("edit() - Exit");
		}catch(Exception e){
			logger.info(e);
			e.printStackTrace();
		}
		return "edit";
	}
}