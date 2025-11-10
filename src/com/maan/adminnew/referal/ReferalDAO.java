package com.maan.adminnew.referal;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;

import com.maan.common.LogUtil;
import com.maan.common.MyJdbcTemplate;

public class ReferalDAO extends MyJdbcTemplate
{
	private static final Logger logger = LogUtil.getLogger(ReferalDAO.class);
	String query="";
	
	public List <Map<String, Object>> getOCList(String reqFrom, String productID, String branchCode, String agencyCode){
    	List <Map<String, Object>> occList=null;
    	Object[] obj=null;
    	try{
    		if("3".equals(productID)||"11".equals(productID)){
	    		if("pending".equals(reqFrom)){
	    			query=getQuery("GET_OCLIST_PENDING");
	    		}else if("approved".equals(reqFrom)){
	    			query=getQuery("GET_OCLIST_APPROVED");
	    		}else if("rejected".equals(reqFrom)){
	    			query=getQuery("GET_OCLIST_REJECTED");
	    		}
    		}else{
	    		if("pending".equals(reqFrom)){
	    			query=getQuery("GET_HOMEOCLIST_PENDING");
	    		}else if("approved".equals(reqFrom)){
	    			query=getQuery("GET_HOMEOCLIST_APPROVED");
	    		}else if("rejected".equals(reqFrom)){
	    			query=getQuery("GET_HOMEOCLIST_REJECTED");
	    		}
    		}
    		if(StringUtils.isEmpty(agencyCode)){
    			query+=" oa_code in(select agency_code from broker_company_master where branch_code in(?))) group by to_char(a.entry_date,'YYYY-MM-DD') order by to_char(a.entry_date,'YYYY-MM-DD') desc";
    			obj=new Object[]{productID, branchCode};
    		}else{
    			query+=" oa_code=?) group by to_char(a.entry_date,'YYYY-MM-DD') order by to_char(a.entry_date,'YYYY-MM-DD') desc";
    			obj=new Object[]{productID, agencyCode};
    		}
			logger.info("Query===>" + query);
			occList=this.mytemplate.queryForList(query,obj);
		   }
		catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}
    	return occList;
    }
	
	public List <Map<String, Object>> getPolicyList(String branchCode, String date, String productID, String reqFrom, String agencyCode){
    	List <Map<String, Object>> policyList=null;
    	Object[] obj=null;
    	try{
    		if("3".equals(productID)||"11".equals(productID)){
	    		if("pending".equals(reqFrom)){
	    			query=getQuery("GET_POLICIES_PENDING");
	    		}else if("approved".equals(reqFrom)){
	    			query=getQuery("GET_POLICIES_APPROVED");
	    		}else if("rejected".equals(reqFrom)){
	    			query=getQuery("GET_POLICIES_REJECTED");
	    		}
    		}else{
    			if("pending".equals(reqFrom)){
	    			query=getQuery("GET_HOMEPOLICIES_PENDING");
	    		}else if("approved".equals(reqFrom)){
	    			query=getQuery("GET_HOMEPOLICIES_APPROVED");
	    		}else if("rejected".equals(reqFrom)){
	    			query=getQuery("GET_HOMEPOLICIES_REJECTED");
	    		}
    		}
    		if(StringUtils.isEmpty(agencyCode)){
    			query+=" oa_code in(select agency_code from broker_company_master where branch_code in(?))) order by quote_no desc";
    			obj=new Object[]{date, productID, branchCode};
    		}else{
    			query+=" oa_code=?) order by quote_no desc";
    			obj=new Object[]{date, productID, agencyCode};
    		}
			logger.info("Query===>" + query);
			policyList=this.mytemplate.queryForList(query,obj);
		}catch (Exception e) {
			logger.debug("EXCEPTION @ { " + e + " }");
		}
    	return policyList;
    }
}