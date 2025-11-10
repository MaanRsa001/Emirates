package com.maan.customer;

import java.io.ByteArrayInputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.maan.common.LogUtil;
import com.maan.common.Validation;
import com.maan.customer.service.CustomerService;
import com.maan.services.util.ValidationFormat;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CustomerAction extends ActionSupport implements ModelDriven<CustomerBean>{
	private static final Logger logger = LogUtil.getLogger(CustomerAction.class);
	private CustomerBean cusBean=new CustomerBean(); 
	HttpServletRequest request=ServletActionContext.getRequest();
	Map<String, Object> session=ActionContext.getContext().getSession();
	CustomerService service=new CustomerService();
	private static final long serialVersionUID = 1L;
	private static final String DROPDOWN = "dropdown";
	private static final String STREAM = "stream";
	private static final String FIELD = "ELEMENT_NAME";
	private String branchCode=(String)session.get("LoginBranchCode");
	private String productId=(String) session.get("product_id");
	private String actualBranch=(String)session.get("adminBranch");
	//private String brokerCode=(String) session.get("brokerCode");
	private String loginId=(String)session.get("user");
	private String issuer=(String)session.get("RSAISSUER");
	private String userType=(String)session.get("usertype");
	private String user=(String)session.get("user1");
	
	public Object[] getParams()
	{
	Object[] objects=new String[]{cusBean.getOption(),productId,actualBranch,"","","",cusBean.getOriginCountry(),
			cusBean.getDestCountry(),"","","",loginId,cusBean.getBrokerCode()};
		return objects;
	}
	
	public List<Map<String, Object>> getTitleList()
	{
		return new com.maan.common.dao.CommonDAO().getOptionsList("title", getParams());
	}
	
	public List<Map<String, Object>> getCityList()
	{
		return new com.maan.common.dao.CommonDAO().getOptionsList("city", getParams());
	}
	public List<Map<String, Object>> getCountryList()
	{
		return new com.maan.common.dao.CommonDAO().getOptionsList("country", getParams());
	}
	public List<Map<String, Object>> getNationalityList()
	{																			 
		return new com.maan.common.dao.CommonDAO().getOptionsList("nationality", getParams());
	}
	public List<Map<String, Object>> getOccupationList()
	{
		return new com.maan.common.dao.CommonDAO().getOptionsList("occupation", getParams());
	}
	public String getDetail()
	{
		if(session.get("b2c")!=null&&"b2c".equalsIgnoreCase(session.get("b2c").toString())){
			session.put("product_id",cusBean.getProductId());
			productId=cusBean.getProductId();
		}
		cusBean.setDisplay("getDetail");
		return INPUT;
	}
	public String getSearch()
	{
		getValidate("SearchCustomer");
		if(!StringUtils.isBlank(cusBean.getDob()))
			cusBean.setDob(dateFormat(cusBean.getDob()));
		if(getActionErrors().isEmpty())
		{
			String result=service.getSearch(cusBean);
			if(!"SUCCESS".equalsIgnoreCase(result))
				addActionError(getText("error.customer.search.valid"));
		}
		
		cusBean.setDisplay("getDetail");
		return INPUT;
	}
	public String getUpdate()
	{
			String forward=INPUT;
			getValidate("CustomerInfo");
			if(getActionErrors().isEmpty())
			{
				if(StringUtils.isEmpty(cusBean.getCustomerIdYN())||!"Y".equalsIgnoreCase(cusBean.getCustomerIdYN())){
					String result=service.getUpdate(cusBean);
					if("SUCCESS".equalsIgnoreCase(result))
					{
						if("Save".equalsIgnoreCase(cusBean.getActionType())){
							//addActionMessage(getText("error.customer.save"));
							session.put("MSG", getText("error.customer.save"));
							forward="initReport";
						}else if("31".equalsIgnoreCase(cusBean.getProductId())||"32".equalsIgnoreCase(cusBean.getProductId())||"33".equalsIgnoreCase(cusBean.getProductId())||"34".equalsIgnoreCase(cusBean.getProductId())){
							forward="redirectTravel";
						}else if("41".equalsIgnoreCase(cusBean.getProductId())){
							forward="redirectHealth";
						}else if("30".equalsIgnoreCase(cusBean.getProductId())){
							forward="redirectHome";
						}
					}
					else
						addActionError(getText("error.customer.update"));
					}else if("Submit".equalsIgnoreCase(cusBean.getActionType()))
					{
						cusBean.setDisplay("getQuote");
						service.getSearch(cusBean);
						cusBean.setDisplay("getDetail");
					}else if("31".equalsIgnoreCase(cusBean.getProductId())||"32".equalsIgnoreCase(cusBean.getProductId())||"33".equalsIgnoreCase(cusBean.getProductId())){
						forward="redirectTravel";
					}else if("41".equalsIgnoreCase(cusBean.getProductId())){
						forward="redirectHealth";
					}else if("30".equalsIgnoreCase(cusBean.getProductId())){
						forward="redirectHome";
					}
			}
		return forward;
	}
	public String edit()
	{
		service.getSearch(cusBean);
		cusBean.setDisplay("getDetail");
		return INPUT;
	}
	public List<Map<String, Object>> getExecutiveList()
	{
		return  new com.maan.common.dao.CommonDAO().getOptionsList("executive", getParams());
	}
	public String executiveList()
	{
		request.setAttribute(FIELD, "executive"); 
		return DROPDOWN;
	}
	public List<Map<String, Object>> getCustomerSelection() {
		if(StringUtils.isNotEmpty(cusBean.getBrokerName())){		
			return new com.maan.common.dao.CommonDAO().getCustomerSelectionList(cusBean.getBrokerName(),cusBean.getSearchBy(),cusBean.getSearchValue(),(String)session.get("openCoverNo"));
		}else{
			return new com.maan.common.dao.CommonDAO().getCustomerSelectionList(loginId,cusBean.getSearchBy(),cusBean.getSearchValue(),(String)session.get("openCoverNo"));
		}
	}
	public String customerSelection()
	{
		logger.info("brokerCode"+cusBean.getBrokerCode());		
		if(StringUtils.isNotEmpty(cusBean.getBrokerCode())){
			cusBean.setBrokerName(new com.maan.common.dao.CommonDAO().getBrokerLoginId(cusBean.getBrokerCode()));
		}	
		if(cusBean.getSearchValue()==null)
		{
			return "customerSelection";
		}
		else{
			request.setAttribute(FIELD, "customerList");
			return DROPDOWN;
		}	
	}
	public String customerPopulate()
	{
		String value="<script type='text/javascript'>window.close();</script>";
		byte[] byteArray = value.getBytes();
		cusBean.setInputStream(new ByteArrayInputStream(byteArray));
		return STREAM;
	}
	public String coreCustSearch()
	{
		return "coreCustomer";
	}
	public void getValidate(String type)
	{
		LinkedList<String> list=service.getValidate(cusBean,type);
		for (String st : list)
			addActionError(getText(st));
	}
	public CustomerBean getModel() {
		cusBean.setLoginId(loginId);
		cusBean.setBranchCode(branchCode);
		cusBean.setProductId(productId);
		cusBean.setDestCountry("1");
		cusBean.setOriginCountry("1");
		cusBean.setUserType(userType);
		//cusBean.setUser(user);
		return cusBean;
	}
	private String dateFormat(String name){
		String arr[]=name.split("/");
		return arr[1]+"/"+arr[0]+"/"+arr[2];
	}
}
