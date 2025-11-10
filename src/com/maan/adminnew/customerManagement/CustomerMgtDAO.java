package com.maan.adminnew.customerManagement;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;

import com.maan.common.LogUtil;
import com.maan.common.MyJdbcTemplate;

public class CustomerMgtDAO extends MyJdbcTemplate{
	private static final Logger logger = LogUtil.getLogger(CustomerMgtDAO.class);
	private String query="";
	private Object obj[]=null;
	
	public List <Map<String, Object>> getAdminCustomerList(String agencyCode){
    	List <Map<String, Object>> brokerList=null;
    	try{
    		if(agencyCode==null || "".equals(agencyCode)){
    			query=getQuery("GET_CUSTOMER_LIST")+" USERTYPE in ('Broker', 'User')) ORDER BY PI.FIRST_NAME";
    			brokerList=this.mytemplate.queryForList(query);
    		}
    		else{
    			query=getQuery("GET_CUSTOMER_LIST")+" AGENCY_CODE=?) ORDER BY PI.FIRST_NAME";
    			obj=new Object[]{agencyCode};
    			brokerList=this.mytemplate.queryForList(query,obj);
    		}logger.info("Query===>" + query);
			logger.info("agencyCode===>" + agencyCode);
		}catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}
    	return brokerList;
    }
	
	public List <Map<String, Object>> getCustomerDetails(final CustomerMgtBean ba){
		List customerDetails=null;
		try{
			query=getQuery("GET_CUSTOMER_INFO");
			logger.info("Query===>" + query);
			logger.info("CustomerId===>" + ba.getCustomerId());
			obj=new Object[]{ba.getCustomerId()};
			customerDetails=this.mytemplate.queryForList(query,obj);

			if(customerDetails!=null || customerDetails.size()>0){
				Map map=(Map)customerDetails.get(0);
					ba.setCfirstname(map.get("FIRST_NAME")==null?"":map.get("FIRST_NAME").toString());
					ba.setClastName(map.get("LAST_NAME")==null?"":map.get("LAST_NAME").toString());
					ba.setCgender(map.get("GENDER")==null?"":map.get("GENDER").toString());
					ba.setCnationality(map.get("NATIONALITY")==null?"":map.get("NATIONALITY").toString());
					ba.setCdob(map.get("DOB")==null?"":map.get("DOB").toString());
					ba.setCphone(map.get("TELEPHONE")==null?"":map.get("TELEPHONE").toString());
					ba.setCmobile(map.get("MOBILE")==null?"":map.get("MOBILE").toString());
					ba.setCfax(map.get("FAX")==null?"":map.get("FAX").toString());
					ba.setCemail(map.get("EMAIL")==null?"":map.get("EMAIL").toString());
					ba.setCaddress1(map.get("ADDRESS1")==null?"":map.get("ADDRESS1").toString());
					ba.setCaddress2(map.get("ADDRESS2")==null?"":map.get("ADDRESS2").toString());
					ba.setCoccupation(map.get("OCCUPATION")==null?"":map.get("OCCUPATION").toString());
					ba.setCcountry(map.get("COUNTRY")==null?"":map.get("COUNTRY").toString());
					ba.setCpobox(map.get("POBOX")==null?"":map.get("POBOX").toString());
					ba.setCagencyCode(map.get("agency_code")==null?"":map.get("agency_code").toString());
					ba.setCcity(map.get("EMIRATE")==null?"":map.get("EMIRATE").toString());
			}
		}catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}
    	return customerDetails;
	}
	
	public List <Map<String, Object>> getCommisionData(String cagencyCode) 
	{
		List <Map<String, Object>> commisionDetails=null;
		try{
			logger.info("Method to getCommisionData");
			query=getQuery("GET_Commission_Data")+" order by lud.product_id";
			logger.info("Query===>" + query);
			logger.info("AgencyCode===>" + cagencyCode);
			obj=new Object[]{cagencyCode};
			commisionDetails=this.mytemplate.queryForList(query,obj);
			logger.info("getCommisionData() - Exit");
		}catch (Exception e){
			logger.debug("EXCEPTION @ { " + e + " }");
		}
		return commisionDetails;
	}
	
	public List <Map<String, Object>> getOpenCoverList(final CustomerMgtBean ba){
		List <Map<String, Object>> openCoverList=null;
    	try{
    		if("".equals(ba.getSearchBy()) || ba.getSearchBy()==null){
    			query=getQuery("GET_OPEN_COVER_LIST_CUSTOMER")+"  order by a.proposal_no desc";
    			obj=new Object[]{ba.getAgencyCode(),ba.getCustomerId()};
    		}else{
	    		if("policy".equals(ba.getSearchBy()))
	    			query=getQuery("GET_OPEN_COVER_LIST_CUSTOMER")+" and lower(b.MISSIPPI_OPENCOVER_NO) like '%'||lower(?)||'%' order by a.proposal_no desc";
	    		else if("ocover".equals(ba.getSearchBy()))
	    			query=getQuery("GET_OPEN_COVER_LIST_CUSTOMER")+" and lower(a.open_cover_no) like '%'||lower(?)||'%' order by a.proposal_no desc";
	    		obj=new Object[]{ba.getAgencyCode(),ba.getCustomerId(), ba.getSearchValue()};
    		}
			logger.info("Query===>" + query);
			logger.info("Customer Id===>" + ba.getCustomerId());
			openCoverList=this.mytemplate.queryForList(query,obj);
		}catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}
    	return openCoverList;
    }
	
	public List<Map<String, Object>> getCustomerListAjax(String agencyCode, String searchBy, String searchValue){
		List <Map<String, Object>> brokerList=null;
		Object args[]=null;
		String strQuery="";
		try{
			if(StringUtils.isNotBlank(agencyCode))
				strQuery=" AND AGENCY_CODE=? ";
			if("name".equals(searchBy)){
				query=getQuery("GET_CUSTOMER_LIST")+" USERTYPE in ('Broker', 'User')"+strQuery+") and lower(PI.first_name||PI.company_name) like '%'||lower(?)||'%' ORDER BY FIRST_NAME";
				args=new Object[]{searchValue};
			}
			else{
				query=getQuery("GET_CUSTOMER_LIST")+" USERTYPE in ('Broker', 'User')"+strQuery+") and lower(PI.customer_id) like '%'||lower(?)||'%' ORDER BY PI.FIRST_NAME ";
				args=new Object[]{searchValue};
			}
			logger.info("Query===>" + query);
			logger.info("agencyCode===>" + agencyCode);
			brokerList=this.mytemplate.queryForList(query,args);
		}catch (Exception e) {			
			e.printStackTrace();
		}
		return brokerList;
	}
}