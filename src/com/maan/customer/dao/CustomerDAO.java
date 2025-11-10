package com.maan.customer.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

//import com.maan.Motor.controller.MotorBean;
import com.maan.common.dao.CommonDAO;
import com.maan.common.LogUtil;
import com.maan.common.MyJdbcTemplate;
import com.maan.customer.CustomerBean;

public class CustomerDAO extends MyJdbcTemplate{
	private static final Logger logger = LogUtil.getLogger(CustomerDAO.class);
	@SuppressWarnings("unchecked")
	public String getSearch(CustomerBean bean) {
		
		logger.info("getSearch - Enter ");
		String result="";
		try
		{
			List<CustomerBean> customerBean = new ArrayList<CustomerBean>();
			if(!"getQuote".equalsIgnoreCase(bean.getDisplay())){
				String sql="";
				Object[] obj=new Object[0];
				if(!"checkCivilId".equalsIgnoreCase(bean.getDisplay()))
				{
					sql=getQuery("GET_SEARCH1");
					logger.info("Query=>"+sql);
					obj=new Object[8];
					obj[0]=bean.getLoginId();
					obj[1]=StringUtils.isBlank(bean.getFullName())?"":"%"+bean.getFullName()+"%";
					obj[2]=StringUtils.isBlank(bean.getFullName())?"":"%"+bean.getFullName()+"%";
					obj[3]=StringUtils.isBlank(bean.getEmailId())?"":"%"+bean.getEmailId()+"%";
					obj[4]=StringUtils.isBlank(bean.getMobileNo())?"":"%"+bean.getMobileNo()+"%";
					obj[5]=StringUtils.isBlank(bean.getTelephoneNo())?"":"%"+bean.getTelephoneNo()+"%";
					obj[6]=bean.getDob();
					obj[7]=StringUtils.isBlank(bean.getCusCivilId())?"":"%"+bean.getCusCivilId()+"%";
				}else
				{
					sql=getQuery("GET_SEARCH2");
					logger.info("Query=>"+sql);
					obj=new Object[3];
					obj[0]=bean.getLoginId();
					obj[1]=bean.getCusCivilId();
					obj[2]=bean.getCustomerId();
				}
					logger.info("obj[] ==> "+StringUtils.join(obj, ","));
					customerBean = this.mytemplate.query(sql, obj, new RowMapper() {
		    			public Object mapRow(final ResultSet rs, final int idVal) throws SQLException {
		    				final CustomerBean cBean = new CustomerBean();
		    				cBean.setCustomerIdYN("N");
		    				cBean.setCustomerId(rs.getString("CUSTOMER_ID"));
		    				cBean.setTitle(rs.getString("TITLE"));
		    				cBean.setFullName(rs.getString("FIRST_NAME")!=null?rs.getString("FIRST_NAME"):rs.getString("COMPANY_NAME"));
		    				cBean.setGender(rs.getString("GENDER"));
		    				cBean.setNationality(rs.getString("NATIONALITY"));
		    				cBean.setDob(dateFormat(rs.getString("DOB")));
		    				cBean.setTelephoneNo(rs.getString("TELEPHONE"));
		    				cBean.setMobileNo(rs.getString("MOBILE"));
		    				cBean.setFax(rs.getString("FAX"));
		    				cBean.setEmailId(rs.getString("EMAIL"));
		    				cBean.setPoBox(rs.getString("POBOX"));
		    				cBean.setAddress(rs.getString("ADDRESS1"));
		    				cBean.setOccupation(rs.getString("OCCUPATION"));
		    				cBean.setCity(rs.getString("EMIRATE"));
		    				cBean.setAddress(rs.getString("COUNTRY"));
		    				cBean.setCusCivilId(rs.getString("CUSTOMER_SOURCE"));
		    				return cBean;
		    			}});
					if(customerBean.size()>0)
					{
						if(!"checkCivilId".equalsIgnoreCase(bean.getDisplay()))
						{
							if(customerBean.size()==1)
							{
								String appNo=bean.getApplicationNo();
								String quoteStatus=bean.getQuoteStatus();
								BeanUtils.copyProperties(bean, (CustomerBean)customerBean.get(0));
								bean.setApplicationNo(appNo);
								bean.setQuoteStatus(quoteStatus);
							}else
							{
								bean.setSearchList(customerBean);
								bean.setCustomerIdYN("Y");
							}
							result="SUCCESS";
						}else
						{
							bean.setSearchList(customerBean);
							bean.setCustomerIdYN("Y");
							bean.setCustomerId("");
							result="EXISTS";
						}
					}
		}else
		{
			String sql=getQuery("GET_SEARCH3");
			logger.info("Query=>"+sql);
			Object[] obj=new Object[1];
			obj[0]=bean.getCustomerId();
			logger.info("obj[] ==> "+StringUtils.join(obj, ","));
			customerBean=this.mytemplate.query(sql, obj, new RowMapper() {
    			public Object mapRow(final ResultSet rs, final int idVal) throws SQLException {
    				final CustomerBean cBean = new CustomerBean();
    				cBean.setCustomerIdYN("N");
    				cBean.setCustomerId(rs.getString("CUSTOMER_ID"));
    				cBean.setTitle(rs.getString("TITLE"));
    				cBean.setFullName(rs.getString("FIRST_NAME")!=null?rs.getString("FIRST_NAME"):rs.getString("COMPANY_NAME"));
    				cBean.setGender(rs.getString("GENDER"));
    				cBean.setNationality(rs.getString("NATIONALITY"));
    				cBean.setDob(dateFormat(rs.getString("DOB")));
    				cBean.setTelephoneNo(rs.getString("TELEPHONE"));
    				cBean.setMobileNo(rs.getString("MOBILE"));
    				cBean.setFax(rs.getString("FAX"));
    				cBean.setEmailId(rs.getString("EMAIL"));
    				cBean.setPoBox(rs.getString("POBOX"));
    				cBean.setAddress(rs.getString("ADDRESS1"));
    				cBean.setOccupation(rs.getString("OCCUPATION"));
    				cBean.setCity(rs.getString("EMIRATE"));
    				cBean.setAddress(rs.getString("COUNTRY"));
    				cBean.setCusCivilId(rs.getString("CUSTOMER_SOURCE"));
    				return cBean;
    			}});
			if(customerBean.size()>0)
			{
				String appNo=bean.getApplicationNo();
				String quoteNo=bean.getQuoteNo();
				String quoteStatus=bean.getQuoteStatus();
				BeanUtils.copyProperties(bean, (CustomerBean)customerBean.get(0));
				bean.setApplicationNo(appNo);
				bean.setQuoteNo(quoteNo);
				bean.setQuoteStatus(quoteStatus);
				result="SUCCESS";
			}
		}
		}catch(Exception e)
		{
			logger.debug(e);
		}
		
		logger.info("getSearch - Exit");
		return result;	
	}

	public String getUpdate(CustomerBean cusBean) {

		
		logger.info("getUpdate - Enter ");
		String result="";
		try
		{
			if(StringUtils.isBlank(cusBean.getCustomerId()))
			{
				cusBean.setCustomerId(new CommonDAO().getPolicyNo(cusBean.getBranchCode(), cusBean.getProductId(),"CUSTOMER_TYPE_ID"));
				String sql=getQuery("INS_CUS_DTL");
				logger.info("Query=>"+sql);
				String orgName="";
				if ("M/S".equalsIgnoreCase(cusBean.getTitle())) {
					orgName = cusBean.getFullName();
					cusBean.setFullName("");
				}
				Object[] obj=new Object[20];
				obj[0]=cusBean.getCustomerId();
				obj[1]=cusBean.getTitle();
				obj[2]=cusBean.getFullName();
				obj[3]="";
				obj[4]=cusBean.getNationality();
				obj[5]=StringUtils.isBlank(cusBean.getDob())?"":cusBean.getDob();
				obj[6]=cusBean.getGender();
				obj[7]=cusBean.getTelephoneNo();
				obj[8]=cusBean.getMobileNo();
				obj[9]=StringUtils.isBlank(cusBean.getFax())?"":cusBean.getFax();
				obj[10]=cusBean.getEmailId();
				obj[11]=cusBean.getAddress();
				obj[12]=cusBean.getOccupation();
				obj[13]=cusBean.getPoBox();
				obj[14]=cusBean.getCountry();
				obj[15]=cusBean.getCity();
				obj[16]=cusBean.getLoginId();
				obj[17]=orgName;
				obj[18]="";
				obj[19]=cusBean.getCusCivilId();
				/*obj[20]=cusBean.getLoginId();
				obj[21]=cusBean.getLoginId();*/
				logger.info("obj[] ==> "+StringUtils.join(obj, ","));
				int res=this.mytemplate.update(sql,obj);
				logger.info("Result=>"+res);
				if(res>0)
					result="SUCCESS";
			}else
			{
				String sql=getQuery("UPD_CUS_DTL");
				logger.info("Query=>"+sql);
				String orgName="";
				if ("M/S".equalsIgnoreCase(cusBean.getTitle())) {
					orgName = cusBean.getFullName();
					cusBean.setFullName("");
				}
				Object[] obj=new Object[20];
				obj[0]=cusBean.getTitle();
				obj[1]=cusBean.getFullName();
				obj[2]="";
				obj[3]=cusBean.getNationality();
				obj[4]=StringUtils.isBlank(cusBean.getDob())?"":cusBean.getDob();
				obj[5]=cusBean.getGender();
				obj[6]=cusBean.getTelephoneNo();
				obj[7]=cusBean.getMobileNo();
				obj[8]=StringUtils.isBlank(cusBean.getFax())?"":cusBean.getFax();
				obj[9]=cusBean.getEmailId();
				obj[10]=cusBean.getAddress();
				obj[11]=cusBean.getOccupation();
				obj[12]=cusBean.getPoBox();
				obj[13]=cusBean.getCountry();
				obj[14]=cusBean.getCity();
				obj[15]=orgName;
				obj[16]="";
				obj[17]=cusBean.getCusCivilId();
				/*obj[18]=cusBean.getLoginId();
				obj[19]=cusBean.getLoginId();*/
				obj[18]=cusBean.getCustomerId();
				obj[19]=(String)this.mytemplate.queryForObject(getQuery("GET_CUS_DTL_MAX_AMEND_ID"),new Object[]{cusBean.getCustomerId()},String.class);
				logger.info("obj[] ==> "+StringUtils.join(obj, ","));
				int res=this.mytemplate.update(sql,obj);
				logger.info("Result=>"+res);
				if(res>0)
					result="SUCCESS";
			}
		}catch(Exception e)
		{
			logger.debug(e);
		}
		
		logger.info("getUpdate - Exit");
		return result;	
	}
	public String getUpdateCustomerDtl(String customerId,String loginId,String branchCode,String productId,String title,String customerName,String mobileNo,String email,String address1,String address2,String poBox,String city,String custAppCode,String clientCustomerId,String custArNo) {
		logger.info("getUpdateCustomerDtl - Enter ");
		String result="";
		try
		{
			if(StringUtils.isBlank(customerId))
			{
				customerId=new CommonDAO().getPolicyNo(branchCode, productId,"CUSTOMER_TYPE_ID");
				String sql=getQuery("INS_MOTOR_CUS_DTL");
				logger.info("Query=>"+sql);
				String orgName="";
				if ("M/S".equalsIgnoreCase(title)) {
					orgName = customerName;
					customerName="";
				}
				Object[] obj=new Object[18];
				obj[0]=customerId;
				obj[1]=title;
				obj[2]=customerName;
				obj[3]="";
				obj[4]=mobileNo;
				obj[5]=email;
				obj[6]=address1;
				obj[7]=address2;
				obj[8]=poBox;
				obj[9]=city;
				obj[10]=loginId;
				obj[11]=orgName;
				obj[12]=loginId;
				obj[13]=loginId;
				obj[14]=custAppCode;
				obj[15]=clientCustomerId;
				obj[16]=custArNo;
				obj[17]=customerName;
				logger.info("obj[] ==> "+StringUtils.join(obj, ","));
				int res=this.mytemplate.update(sql,obj);
				logger.info("Result=>"+res);
				if(res>0)
					result="SUCCESS";
			}else
			{
				String sql=getQuery("UPD_MOTOR_CUS_DTL");
				logger.info("Query=>"+sql);
				String orgName="";
				if ("M/S".equalsIgnoreCase(title)) {
					orgName = customerName;
					customerName="";
				}
				Object[] obj=new Object[17];
				obj[0]=title;
				obj[1]=customerName;
				obj[2]="";
				obj[3]=mobileNo;
				obj[4]=email;
				obj[5]=address1;
				obj[6]=address2;
				obj[7]=poBox;
				obj[8]=city;
				obj[9]=orgName;
				obj[10]=loginId;
				obj[11]=loginId;
				obj[12]=custAppCode;
				obj[13]=clientCustomerId;
				obj[14]=custArNo;
				obj[15]=customerName;
				obj[16]=customerId;
				logger.info("obj[] ==> "+StringUtils.join(obj, ","));
				int res=this.mytemplate.update(sql,obj);
				logger.info("Result=>"+res);
				if(res>0)
					result="SUCCESS";
			}
		}catch(Exception e)
		{
			logger.debug(e);
		}
		
		logger.info("getUpdateCustomerDtl - Exit Result=>"+result);
		return customerId;	
	}
	private String dateFormat(String name){
		String arr[]=name.split("/");
		return arr[1]+"/"+arr[0]+"/"+arr[2];
	}
}
