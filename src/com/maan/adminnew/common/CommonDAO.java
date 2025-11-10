package com.maan.adminnew.common;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;

import com.maan.common.LogUtil;
import com.maan.common.MyJdbcTemplate;
import com.maan.services.util.runner;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.LocalizedTextProvider;

public class CommonDAO extends MyJdbcTemplate{
	private static final Logger logger = LogUtil.getLogger(CommonDAO.class);
	String query="";
	public List<Map<String, Object>> getAdminInfo(String user){
		logger.info("Method to getAdminInfo");
    	List<Map<String, Object>> menuIds=null;
    	String sql="";
    	try{
			sql=getQuery("GET_MAIN_USER_INFO");
			menuIds=this.mytemplate.queryForList(sql,new Object[]{user});
		   }
		catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}		
		logger.info("Query===>" + sql);
		logger.info("getAdminInfo() - Exit || Result: " + menuIds.size());
				
    
    	return menuIds;
    }
	
	public List<Map<String, Object>>getMenuList(String menuIds, final String bCode, final String catCode){
		logger.info("Get Menu List - Enter");
        List<Map<String, Object>> list = null;
        try {
		    LocalizedTextProvider ltp = ActionContext.getContext().getInstance(LocalizedTextProvider.class);
            query = ltp.findDefaultText("GET_MAIN_MENU_LIST", Locale.ENGLISH, new String[]{menuIds, bCode, catCode});
            //query = LocalizedTextUtil.findDefaultText("GET_MAIN_MENU_LIST", Locale.ENGLISH, new String[]{menuIds, bCode});
            logger.info("Query===> " + query);
            list = this.mytemplate.queryForList(query);
            logger.info("Menu List Size=>"+list.size());
        } catch (Exception e) {
        	logger.debug("EXCEPTION @ { " + e + " }");
        }
       
        logger.info("Params => " + menuIds);
        logger.info("Get Menu List - Exit");
        return list;
    }
	public List <Map<String, Object>> getCountries(String branchCode)
	{
		List <Map<String, Object>> countries =null;
		try {
			logger.info("Method to getCountries");
			query=getQuery("GET_Countries");
			logger.info("Query===>" + query);
			countries=this.mytemplate.queryForList(query, new Object[]{branchCode});
			logger.info("getCountries() - Exit");
		}
		catch (Exception e) 
		{
			logger.debug("EXCEPTION @ { " + e + " }");
		}
		return countries;
	}
	
	public List <Map<String, Object>> getEmirates(String branchCode)
	{
		List <Map<String, Object>> emirates =null;
		try {
			logger.info("Method to getEmirates");
			query=getQuery("GET_Emirates");
			logger.info("Query===>" + query);
			emirates=this.mytemplate.queryForList(query, new Object[]{branchCode});
			logger.info("getEmirates() - Exit");
		}
		catch (Exception e) 
		{
			logger.debug("EXCEPTION @ { " + e + " }");
		}
		return emirates;
	}
	public List <Map<String, Object>> getNationalities() 
	{
		List <Map<String, Object>> nationalities =null;
		try {
			logger.info("Method to getNationalities");
			query=getQuery("GET_Nationalities");
			logger.info("Query===>" + query);
			nationalities=this.mytemplate.queryForList(query);
			logger.info("getNationalities() - Exit");
		}
		catch (Exception e) 
		{
			logger.debug("EXCEPTION @ { " + e + " }");
		}
		return nationalities;
	}
	public int getExist(Object[] val,String str)throws EmptyResultDataAccessException,IncorrectResultSizeDataAccessException
	{
		int checkList=0;
		try{
			logger.info("Method to getExist()");
			
			if(str.equals("transport"))
			{
				query=getQuery("Check_mode_of_transport");
			}
			else if(str.equals("convey"))
			{
				query=getQuery("Check_convey");
			}
			else if(str.equals("country"))
			{
				query=getQuery("Check_Country");
			}
			else if(str.equals("bank"))
			{
				query=getQuery("Check_Bank");
			}
			else if(str.equals("material"))
			{
				query=getQuery("Check_material");
			}
			else if(str.equals("war"))
			{
				query=getQuery("Check_War");
			}
			else if(str.equals("sale"))
			{
				query=getQuery("Check_Sale");
			}
			else if(str.equals("tole"))
			{
				query=getQuery("Check_tole");
			}
			else if(str.equals("comEx"))
			{
				query=getQuery("Check_comEx");
			}
			else if(str.equals("extraCover"))
			{
				query=getQuery("Check_extraCover");
			}
			else if(str.equals("city"))
			{
				query=getQuery("Check_city");
			}			
			else if(str.equals("vessel"))
			{
				query=getQuery("Check_vessel");
			}
			else if(str.equals("warranty"))
			{
				query=getQuery("Check_Warranty");
			}
			else if(str.equals("constant"))
			{
				query=getQuery("Check_constant");
			}
			else if(str.equals("extra"))
			{
				query=getQuery("Check_extra_name");
			}
			else if(str.equals("countryDet"))
			{
				query=getQuery("Check_countryDet");
			}
			else if(str.equals("agent"))
			{
				query=getQuery("Check_Agent");
			}
			else if(str.equals("commodity"))
			{
				query=getQuery("Check_commodity");
			}
			else if(str.equals("currency"))
			{
				query=getQuery("Check_currency");
			}
			else if(str.equals("exchange"))
			{
				query=getQuery("Check_exchange");
			}
			else if(str.equals("cover"))
			{
				query=getQuery("Check_cover");
			}
			else if(str.equals("exclusion"))
			{
				query=getQuery("Check_Exclusion");
			}
			else if(str.equals("clauseID"))
			{
				query=getQuery("Check_ClauseID");
			}
			else if(str.equals("categ"))
			{
				query=getQuery("Check_Categ");
			}
			else if(str.equals("display"))
			{
				query=getQuery("Check_display_order");
			}
			checkList=this.mytemplate.queryForObject(query,Integer.class,val);
			logger.info("Query===>" + query);
			logger.info("getExist() - Exit");
			}
		    
		    catch(EmptyResultDataAccessException e){
				logger.info("Core Application Code Not Exist");
				
			}
			catch(IncorrectResultSizeDataAccessException e){
				logger.info("Core Application Code Not Exist");
				
			}
			return checkList;
	}
	

	public List <Map<String, Object>> getTitles(String branchCode)
	{
		List <Map<String, Object>> titles =null;
		try {
			logger.info("Method to getTitles");
			query=getQuery("GET_Titles");
			logger.info("Query===>" + query);
			logger.info("BranchCode===>" + branchCode);
			Object[] obj=new Object[1];
			obj[0]=branchCode;
			titles=this.mytemplate.queryForList(query,obj);
			logger.info("getTitles() - Exit");
		}
		catch (Exception e) 
		{
			logger.debug("EXCEPTION @ { " + e + " }");
		}
		return titles;
	}
	
	public List <Map<String,Object>> getProductsDET(String branchCode, String agencyCode){
		logger.info("Method to getProductsDET");
		List <Map<String,Object>> product_Det=null;
		try{
			query=getQuery("GET_PRODUCT_DET");
			if(!"".equals(agencyCode) && agencyCode!=null){
				query+=" and PRODUCT_ID not in (select PRODUCT_ID from LOGIN_USER_DETAILS where AGENCY_CODE='"+agencyCode+"') order by product_id";
			}else query+=" order by product_id";
				String[] args={branchCode};
			logger.info("Query===>" + args[0]);
			product_Det=this.mytemplate.queryForList(query,args);
		}
		catch(Exception e){
			logger.debug("EXCEPTION @ { " + e + " }");
		}
		return product_Det;
    }
	
 
	public int getCustomerId(String branchCode){
		int customer_id=0;
		try{
			query=getQuery("GET_Customer_Id");
			logger.info("Query===>" + query);
			customer_id=this.mytemplate.queryForObject(query,Integer.class,new Object[]{branchCode});
			query=getQuery("UPD_Customer_Id");
			logger.info("Query===>" + query);
			this.mytemplate.update(query,new Object[]{customer_id, customer_id, branchCode});
			logger.info("customer_id===>" + customer_id);
		}
		catch(Exception e){
			logger.debug("EXCEPTION @ { " + e + " }");
		}
		return customer_id;
	}
	public String getUserCode(String branchCode) {
		String agencyCode="";
		try {
			query=getQuery("GET_USER_AGENCYCODE"); 
			int acode=this.mytemplate.queryForObject(query,Integer.class, new Object[]{branchCode});
			agencyCode = String.valueOf(acode);
			query=getQuery("UPD_USER_AGENCYCODE"); 
			this.mytemplate.update(query, new Object[]{acode+1,branchCode});
			logger.info("Query===>" + query);
			logger.info("agencyCode===>" + agencyCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return agencyCode;
	}
	 public int getMaxUserId(){
		int user_Id=0;
		try{
			String query=getQuery("GET_MAXUSER_ID");
			logger.info("Query===>" + query);
			user_Id=this.mytemplate.queryForObject(query,Integer.class);
		}
		catch(Exception e){
			logger.debug("EXCEPTION @ { " + e + " }");
		}
		logger.info("getMaxUserId() - Exit	user_Id===>"+user_Id);
    	return user_Id;
    }
	 public void checkPassword(String[] args) {
	   try
	   { 
			String query=getQuery("UPD_broker_password");
			logger.info("Query===>" + query);
			this.mytemplate.update(query,args);
	   }
	   catch (Exception e) 
		{
			logger.debug("EXCEPTION @ { " + e + " }");
		}
	}
	 public void insertCommission(Object[] info, Object[] obj1){
		logger.info("ENTER-->Method to insertCommission");
    	try
    	{
			String query=getQuery("INS_PRODUCT_DET");
			logger.info("Query===>" + query);
			this.mytemplate.update(query, replaceEmptyIfNull(info));
			if(obj1!=null){
				query=getQuery("UPD_PRODUCT_DET_LM");
				logger.info("Query===>" + query);
				this.mytemplate.update(query, obj1);
			}
    	}catch(Exception e)
    	{
    		logger.info("Exception @ "+e);
    		e.printStackTrace();
    	}
    	logger.info("insertCommission() - Exit");
    }
	 public void updateCommission(Object[] info, Object[] obj1){
		logger.info("ENTER-->Method to updateCommission");
    	try
    	{
			String query=getQuery("UPD_PRODUCT_DET");
			logger.info("Query===>" + query);
			this.mytemplate.update(query, replaceEmptyIfNull(info));
			if(obj1!=null){
				query=getQuery("UPD_PRODUCT_DET_LM");
				logger.info("Query===>" + query);
				this.mytemplate.update(query, obj1);
			}
    	}catch(Exception e)
    	{
    		logger.debug("Exception @ "+e);
    	}
    	logger.info("updateCommission() - Exit");
    }
	 public int checkExistProduct(String productId, String agencyCode){
		 int count=0;
    	try{
			String query=getQuery("GET_EXIST_PRODUCT_COUNT");
			logger.info("Query===>" + query);
			count=this.mytemplate.queryForObject(query,Integer.class, new Object[]{productId, agencyCode});
    	}catch(Exception e){
    		logger.debug("Exception @ "+e);
    	}
    	return count;
	 }
	 public List <Map<String, Object>> getAdminBrokerList(String branchCode, String appId){
    	List <Map<String, Object>> brokerList=null;
    	try{
			String query=getQuery("GET_Broker_List");
			logger.info("Query===>" + query);
			logger.info("branchCode===>" + branchCode);
			brokerList=this.mytemplate.queryForList(query,new Object[]{branchCode, appId, branchCode});		
		   }
		catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}
    	return brokerList;
	 }
	 public List<Map<String, Object>> getBusinessTypeList(final String branchCode) {
		List <Map<String, Object>> brokerTypeList=null;
    	try{
			String query=getQuery("GET_BROKERTYPE_LIST");
			logger.info("Query===>" + query);
			logger.info("branchCode===>" + branchCode);
			brokerTypeList=this.mytemplate.queryForList(query,new Object[]{branchCode});		
		   }
		catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}
    	return brokerTypeList;
    } 
	public List<Map<String, Object>> getCurrencyList() {
		List <Map<String, Object>> currencyList=null;
    	try{
			String query=getQuery("GET_CURRENCY_LIST");
			logger.info("Query===>" + query);
			currencyList=this.mytemplate.queryForList(query);		
		   }
		catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}
    	return currencyList;
    }
	
	public HashMap getBrokerUserDetails(String branch){
		String query ="";
		String args[] = new String[1];
		HashMap<String, Object> broDetails = new HashMap<String, Object> ();
		try{
			args[0] = branch;
			query = "SELECT CURRENCY_ABBREVIATION,ORIGINATION_COUNTRY_ID,DESTINATION_COUNTRY_ID,CURRENCY_ABBREVIATION,DECIMAL_PLACES,nvl(tax,'0'),email,LANG_YN from BRANCH_MASTER where BRANCH_CODE=? ";
			String result[][] = new String[0][0];
			result = runner.multipleSelection(query,args);
			if(result.length>0){
				broDetails.put("Branch",branch);
				broDetails.put("CurrencyName",(result[0][0]!=null?result[0][0]:"AED"));
				broDetails.put("Orgination",(result[0][1]!=null?result[0][1]:"1"));
				broDetails.put("Destination",(result[0][2]!=null?result[0][2]:"1"));
				broDetails.put("CurrencyAbb",(result[0][3]!=null?result[0][3]:"AED"));
				broDetails.put("CurrencyDecimal",(result[0][4]!=null?result[0][4]:"2"));
				broDetails.put("Tax",(result[0][5]!=null?result[0][5]:"0"));
				broDetails.put("Site",(result[0][6]!=null?result[0][6]:" "));
				broDetails.put("LANG",(result[0][7]!=null?result[0][7]:" "));
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return broDetails;
	}
	public Object[] replaceEmptyIfNull(Object[] args){
		if(args!=null){
			for (int i = 0; i < args.length; i++) {
				if(args[i]==null){
					args[i]="";
				}
			}
		}
		return args;
	}
	public List<Map<String, Object>> isBetweenTwo(Object[] val) 
    {
		List<Map<String, Object>> list=null;
		try{
			logger.info("Enter into isBetweenTwo() ");
			query="SELECT   NVL (DEDUCTIBLE, '0') FROM   COMMODITY_EXCESS_PREMIUM  WHERE  ? BETWEEN START_SUM_INSURED AND END_SUM_INSURED AND STATUS = 'Y' and branch_code=?";
			
			logger.info("Values --->"+StringUtils.join(val,","));
			logger.info("Query --->"+query);
			
			list=this.mytemplate.queryForList(query,val);
			logger.info("Exit into isBetweenTwo() ");
		}catch (Exception e) {
			e.printStackTrace();
			logger.info("Exception isBetweenTwo() "+e);
			
		} 
		return list;
    }
}