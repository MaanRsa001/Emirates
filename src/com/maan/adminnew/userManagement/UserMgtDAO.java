package com.maan.adminnew.userManagement;

import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;

import com.maan.common.LogUtil;
import com.maan.common.MyJdbcTemplate;

public class UserMgtDAO extends MyJdbcTemplate{
	private static final Logger logger = LogUtil.getLogger(UserMgtDAO.class);
	String query="";
	public List <Map<String, Object>> getAdminUserList(final UserMgtBean ba, String agencyCode, String mode1, String branchCode){
    	List <Map<String, Object>> userList=null;
    	Object[] obj=null;
    	try{
    		if(StringUtils.isNotBlank(ba.getBorganization())){
    			query=getQuery("GET_USER_LIST")+" and log.oa_code=? and log.branch_code=?";
    			obj=new Object[]{"User", agencyCode, branchCode};
    		}
    		else{
    			query=getQuery("GET_USER_LIST")+ " and log.branch_code=?";
    			obj=new Object[]{"User",branchCode };
    		}
			logger.info("Query===>" + query);
			userList=this.mytemplate.queryForList(query,obj);
		   }
		catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}
    	return userList;
    }
	
	public List <Map<String, Object>> getUserDetails(final UserMgtBean ba, String uagencyCode){
		List usertDetails=null;
		try{
			query=getQuery("GET_BROKER_USER_DETAILS");
			logger.info("Query===>" + query);
			logger.info("AgencyCode===>" + ba.getUagencyCode());
			Object[] obj=new Object[1];
			obj[0]= ba.getUagencyCode();
			usertDetails=this.mytemplate.queryForList(query,obj);

			if(usertDetails!=null || usertDetails.size()>0){
				Map map=(Map)usertDetails.get(0);
					ba.setUname(map.get("FIRST_NAME")==null?"":map.get("FIRST_NAME").toString());
					ba.setUfirstname(map.get("FIRST_NAME")==null?"":map.get("FIRST_NAME").toString());
					ba.setUlastname(map.get("LAST_NAME")==null?"":map.get("LAST_NAME").toString());
					ba.setUgender(map.get("GENDER")==null?"":map.get("GENDER").toString());
					ba.setUnationality(map.get("NATIONALITY")==null?"":map.get("NATIONALITY").toString());
					ba.setUnationalityName(map.get("NATIONALITY_NAME")==null?"":map.get("NATIONALITY_NAME").toString());
					ba.setUdob(map.get("DOB")==null?"":map.get("DOB").toString());
					ba.setUphone(map.get("TELEPHONE")==null?"":map.get("TELEPHONE").toString());
					ba.setUmobile(map.get("MOBILE")==null?"":map.get("MOBILE").toString());
					ba.setUfax(map.get("FAX")==null?"":map.get("FAX").toString());
					ba.setUemail(map.get("EMAIL")==null?"":map.get("EMAIL").toString());
					ba.setUaddress1(map.get("ADDRESS1")==null?"":map.get("ADDRESS1").toString());
					ba.setUaddress2(map.get("ADDRESS2")==null?"":map.get("ADDRESS2").toString());
					ba.setUoccupation(map.get("OCCUPATION")==null?"":map.get("OCCUPATION").toString());
					ba.setUcountry(map.get("COUNTRY")==null?"":map.get("COUNTRY").toString());
					ba.setUpobox(map.get("POBOX")==null?"":map.get("POBOX").toString());
					ba.setUagencyCode(map.get("agency_code")==null?"":map.get("agency_code").toString());
					ba.setUcity(map.get("EMIRATE")==null?"":map.get("EMIRATE").toString());
					ba.setUserId(map.get("customer_id")==null?"":map.get("customer_id").toString());
					ba.setUlogin_Id(map.get("login_id")==null?"":map.get("login_id").toString());
					ba.setBname(map.get("bname")==null?"":map.get("bname").toString());
					ba.setUtype(map.get("USERTYPE")==null?"":map.get("USERTYPE").toString());
					ba.setUstatus(map.get("status")==null?"":map.get("status").toString());
					ba.setUcountryNa(map.get("country_name")==null?"":map.get("country_name").toString());
					ba.setBrokerName(map.get("borganization")==null?"":map.get("borganization").toString());
					ba.setAgencyCode(map.get("OA_CODE")==null?"":map.get("OA_CODE").toString());
					ba.setBroker(map.get("OA_CODE")==null?"":map.get("OA_CODE").toString());
					ba.setUtitle(map.get("TITLE")==null?"":map.get("TITLE").toString());
			}
		}catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}
    	return usertDetails;
	}
	
	public List <Map<String, Object>> getCommisionData(String uagencyCode, String agencyCode, String branchCode) 
	{
		List <Map<String, Object>> commisionDetails=null;
		try 
		{
			logger.info("Method to getCommisionData");
			query=getQuery("GET_USER_COMMISSION")+" order by lud.product_id";
			logger.info("Query===>" + query);
			commisionDetails=this.mytemplate.queryForList(query,new Object[]{uagencyCode, agencyCode, branchCode});
			logger.info("getCommisionData() - Exit");
		} 
		catch (Exception e) 
		{
			logger.debug("EXCEPTION @ { " + e + " }");
		}
		return commisionDetails;
	}
	
	public List<Map<String, Object>> getUserListAjax(String agencyCode, String searchBy, String searchValue, String mode1){
		List <Map<String, Object>> brokerList=null;
		try{
			if("name".equals(searchBy)){
				query=getQuery("GET_USER_LIST")+" and lower(nvl(per.first_name||''||per.last_name,per.company_name)) like '%'||lower(?)||'%'";
			}
			else if("status".equals(searchBy)) {
				query=getQuery("GET_USER_LIST")+" and per.status = ?";
			}
			else 
				query=getQuery("GET_USER_LIST")+" and lower(per.agency_code) like '%'||lower(?)||'%'";
			if("broker".equals(mode1)){
				query+=" and log.oa_code='"+agencyCode+"'";
			}
			logger.info("Query===>" + query);
			brokerList=this.mytemplate.queryForList(query,new Object[]{"User", searchValue});
		   }
		catch (Exception e) {			
			e.printStackTrace();
		}
		return brokerList;
	}
	
	/*public List <Map<String, Object>> getProducts(final UserMgtBean ba)decode(?, Active, Y, DeActive, D, Locked, L)];
	{
		List <Map<String, Object>> productData=null;
		try 
		{
			logger.info("Method to getProducts");
			query=getQuery("GET_Commission_Data")+" AND lud.PRODUCT_ID=?";
			logger.info("Query===>" + query);
			Object[] obj={ba.getAgencyCode(), ba.getProductID()};
			productData=this.mytemplate.queryForList(query,obj);
			if(productData!=null && productData.size()>0){
				Map map=(Map)productData.get(0);
				ba.setProductID(map.get("PRODUCT_ID").toString());
				ba.setProductName(map.get("product_name").toString());
				ba.setInsurance_End_Limit(map.get("INSURANCE_END_LIMIT").toString());
				ba.setFreight(map.get("FREIGHT_DEBIT_OPTION").toString());
				ba.setPayReceipt(map.get("pay_receipt_status").toString());
				ba.setSdiscount(map.get("SPECIAL_DISCOUNT").toString());
			}
			logger.info("getProducts() - Exit");
		}
		catch (Exception e)
		{
			logger.debug("EXCEPTION @ { " + e + " }");
		}
		return productData;
	}*/
	
	public List <Map<String, Object>> getOCCertificate(String agencyCode){
    	List <Map<String, Object>> occList=null;
    	Object[] obj=null;
    	try{
			query=getQuery("GET_OPENCOVER_CERTIFICAE");
			obj=new Object[]{agencyCode};
			logger.info("Query===>" + query);
			occList=this.mytemplate.queryForList(query,obj);		
		   }
		catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}
    	return occList;
    }
	
	 public void insertUserInfo(Object[] args, Object[] args1){
    	try{
			query=getQuery("INS_USER_PERSONAL_INFO");
			logger.info("Query===>" + query);
			logger.info("Args==>"+StringUtils.join(args,","));
			this.mytemplate.update(query,args);
			
			query=getQuery("INS_USER_LOGIN_MASTER");
			logger.info("Query===>" + query);
			logger.info("Args==>"+StringUtils.join(args1,","));
			this.mytemplate.update(query,args1);	
		   }
		catch (Exception e) {
			logger.debug("EXCEPTION @ { " + e + " }");
		}
	 }
	 public void updateUserInfo(Object[] args, Object[] args1){
    	try{
    		
			query=getQuery("UPD_USER_PERSONAL_INFO");
			logger.info("Query===>" + query);
			logger.info("Args==>"+StringUtils.join(args,","));
			this.mytemplate.update(query,args);
			
			query=getQuery("UPD_USER_LOGIN_MASTER");
			logger.info("Query===>" + query);
			logger.info("Args==>"+StringUtils.join(args1,","));
			this.mytemplate.update(query,args1);
		   }
		catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}
    }
}