package com.maan.customer.service;


import java.util.LinkedList;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;

import com.maan.common.LogUtil;
import com.maan.common.Validation;
import com.maan.customer.CustomerBean;
import com.maan.customer.dao.CustomerDAO;
import com.maan.services.util.ValidationFormat;

public class CustomerService{
	private static final Logger logger = LogUtil.getLogger(CustomerService.class);
	CustomerDAO cusDAO=new CustomerDAO();
	public String getSearch(CustomerBean cusBean) {
		return cusDAO.getSearch(cusBean);
	}
	public String getUpdate(CustomerBean cusBean) {
		return cusDAO.getUpdate(cusBean);
	}
	public LinkedList<String> getValidate(CustomerBean cusBean,String type)
	{
		LinkedList<String> list=new LinkedList<String>();
		try{
		ValidationFormat val = new ValidationFormat();
		Validation validation=new Validation();
		if("CustomerInfo".equalsIgnoreCase(type))
		{
			if(StringUtils.isEmpty(cusBean.getCustomerIdYN())||!"Y".equalsIgnoreCase(cusBean.getCustomerIdYN()))
			{
				if(StringUtils.isEmpty(cusBean.getTitle()))
				{
					list.add("error.customer.title");
				}
				if(StringUtils.isEmpty(cusBean.getFullName()))
				{
					list.add("error.customer.fullName");
				}else if(!val.validateStringWithSpace(cusBean.getFullName()))
				{
					list.add("error.customer.fullName.valid");
				}
				if(StringUtils.isEmpty(cusBean.getGender()))
				{
					list.add("error.customer.gender");
				}
				if(StringUtils.isEmpty(cusBean.getAddress()))
				{
					list.add("error.customer.address");
				}
				if(StringUtils.isEmpty(cusBean.getCusCivilId()))
				{
					list.add("error.customer.cusCivilId");
				}else if(!StringUtils.isNumeric(cusBean.getCusCivilId()))
				{
					list.add("error.customer.cusCivilId.valid");
				}else if(cusBean.getCusCivilId().length()!=10)
				{
					list.add("error.customer.cusCivilId.digitValid");
				}else
				{ 
					String tdisplay=cusBean.getDisplay();
					cusBean.setDisplay("checkCivilId");
					/*if("EXISTS".equalsIgnoreCase(getSearch(cusBean)))
					{
						list.add("error.customer.cusCivilId.exists");
					}*/
					cusBean.setDisplay(tdisplay);
				}
				if(StringUtils.isEmpty(cusBean.getPoBox()))
				{
					list.add("error.customer.poBox");
				}else if(!StringUtils.isNumeric(cusBean.getPoBox()))
				{
					list.add("error.customer.poBox.valid");
				}else if(cusBean.getPoBox().length()>6)
				{
					list.add("error.customer.poBox.digitValid");
				}
				if(StringUtils.isEmpty(cusBean.getCity()))
				{
					list.add("error.customer.city");
				}
				if(StringUtils.isEmpty(cusBean.getTelephoneNo()))
				{
					list.add("error.customer.telephoneNo");
				}else if(!StringUtils.isNumeric(cusBean.getTelephoneNo()))
				{
					list.add("error.customer.telephoneNo.valid");
				}else if(cusBean.getTelephoneNo().length()!=10)
				{
					list.add("error.customer.telephoneNo.dightValid");
				}
				if(StringUtils.isEmpty(cusBean.getMobileNo()))
				{
					list.add("error.customer.mobileNo");
				}else if(!StringUtils.isNumeric(cusBean.getMobileNo()))
				{
					list.add("error.customer.mobileNo.valid");
				}else if(cusBean.getMobileNo().length()!=10)
				{
					list.add("error.customer.mobileNo.dightValid");
				}
				if(StringUtils.isNotBlank(cusBean.getFax()))
				{
					if(!StringUtils.isNumeric(cusBean.getFax()))
					{
						list.add("error.customer.fax.valid");
					}else if(cusBean.getFax().length()!=10)
					{
						list.add("error.customer.fax.dightValid");
					}
				}
				if(StringUtils.isEmpty(cusBean.getEmailId()))
				{
					list.add("error.customer.emailId");
				}else if("invalid".equalsIgnoreCase(validation.emailValidate(cusBean.getEmailId())))
				{	
					list.add("error.customer.valid.emailId");		
				}
				if(StringUtils.isNotEmpty(cusBean.getDob()))
				{
					if(!val.IsDateValidationFormat(cusBean.getDob())){
						list.add("error.customer.dob.validFormat");
					}else if(!val.sysDateValidation(cusBean.getDob()))
					{
						list.add("error.customer.dob.valid");
					}else if(new com.maan.common.dao.CommonDAO().getCalculatedAge(cusBean.getDob())>100)
					{
						list.add("error.customer.age.valid");
					}
				}
				if(StringUtils.isEmpty(cusBean.getOccupation()))
				{
					list.add("error.customer.occupation");
				}
			}else if(StringUtils.isEmpty(cusBean.getCustomerId())){
				list.add("error.customer.customerId.select");
				getSearch(cusBean);
			}
			if(!list.isEmpty()) {
				if(!StringUtils.isBlank(cusBean.getDob()))
					cusBean.setDob(dateFormat(cusBean.getDob()));
			}
		}else if("SearchCustomer".equalsIgnoreCase(type))
		{
			if(StringUtils.isEmpty(cusBean.getFullName()) &&StringUtils.isEmpty(cusBean.getCusCivilId()) && StringUtils.isEmpty(cusBean.getMobileNo())&&StringUtils.isEmpty(cusBean.getTelephoneNo())&&StringUtils.isEmpty(cusBean.getEmailId())&&StringUtils.isEmpty(cusBean.getDob()))
			{
				list.add("error.customer.search");
			}else
			{
				if(!StringUtils.isNumeric(cusBean.getMobileNo()))
				{
					list.add("error.customer.mobileNo.valid");
				}
				if(!StringUtils.isNumeric(cusBean.getTelephoneNo()))
				{
					list.add("error.customer.telephoneNo.valid");
				}
			}
		}
		}catch(Exception e)
		{
			logger.debug(e);
		}
		return list;
	}
	public LinkedList<String> getCustomerValidate(String issuer,String brokerCode,String executive,String title,String customerName,String city,String poBox,String mobileNo,String email)
	{
		Validation validation=new Validation();
		ValidationFormat val = new ValidationFormat();
		LinkedList<String> list=new LinkedList<String>();
		try{
			//Customer Information-Start
			if(StringUtils.isNotBlank(issuer))
			{
				if(StringUtils.isEmpty(brokerCode)){
					list.add("error.customer.title");	
				}
				if(StringUtils.isEmpty(brokerCode)){
					list.add("error.customer.title");	
				}
				
			}
			if(StringUtils.isEmpty(title))
			{
				list.add("error.customer.title");
			}
			if(StringUtils.isEmpty(customerName))
			{
				list.add("error.customer.customerName");
			}else if(!val.validateStringWithSpace(customerName))
			{
				list.add("error.customer.customerName.valid");
			}
			if(StringUtils.isEmpty(city))
			{
				list.add("error.customer.city");
			}
			if(StringUtils.isEmpty(poBox))
			{
				list.add("error.customer.poBox");
			}else if(!StringUtils.isNumeric(poBox))
			{
				list.add("error.customer.poBox.valid");
			}
			if(StringUtils.isEmpty(mobileNo))
			{
				list.add("error.customer.mobileNo");
			}else if(!StringUtils.isNumeric(mobileNo))
			{
				list.add("error.customer.mobileNo.valid");
			}
			if(StringUtils.isEmpty(mobileNo))
			{
				list.add("error.customer.email");
			}
			else if("invalid".equalsIgnoreCase(validation.emailValidate(email)))
			{	
				list.add("error.customer.valid.email");
			}
			//Customer Information-End
		}catch(Exception e)
		{
			logger.debug(e);
		}
		return list;
	}
	public String getUpdateCustomerDtl(String customerId,String loginId,String branchCode,String productId,String title,String customerName,String mobileNo,String email,String address1,String address2,String poBox,String city,String custAppCode,String clientCustomerId,String custArNo){
		return cusDAO.getUpdateCustomerDtl(customerId, loginId, branchCode, productId, title, customerName, mobileNo, email, address1, address2, poBox, city, custAppCode, clientCustomerId, custArNo);	
	}
	private String dateFormat(String name){
		String arr[]=name.split("/");
		return arr[1]+"/"+arr[0]+"/"+arr[2];
	}
}
