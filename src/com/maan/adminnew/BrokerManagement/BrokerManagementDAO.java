package com.maan.adminnew.BrokerManagement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import com.maan.common.LogUtil;
import com.maan.common.MyJdbcTemplate;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.LocalizedTextProvider;

public class BrokerManagementDAO extends MyJdbcTemplate
{
	private static final Logger logger = LogUtil.getLogger(BrokerManagementDAO.class);
	public List <String> getBrokerDetails(final BrokerMgmBean ba, String agencyCode,String branchCode){
		List brokerDetails=null;
		try{
			String query=getQuery("GET_Broker_Info");
			logger.info("Query===>" + query);
			logger.info("AgencyCode===>" + agencyCode);
			logger.info("BranchCode===>" + branchCode);
			Object[] obj=new Object[2];
			obj[0]=agencyCode;
			obj[1]=branchCode;
			brokerDetails=this.mytemplate.query(query,obj,new RowMapper(){
				public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
					if(rs.getString("CUST_NAME")!=null && rs.getString("missippi_id")!=null)
						ba.setNameAndCode((rs.getString("CUST_NAME"))+"("+rs.getString("missippi_id")+")");
					ba.setApprovedby(rs.getString("APPROVED_PREPARED_BY"));
					ba.setBcode(rs.getString("rsa_broker_code"));
					ba.setBorganization(rs.getString("COMPANY_NAME")==null?rs.getString("FIRST_NAME"):rs.getString("COMPANY_NAME"));
					ba.setAddress1(rs.getString("ADDRESS1"));
					ba.setAddress2(rs.getString("ADDRESS2"));
					ba.setCountry(rs.getString("COUNTRY"));
					ba.setCountryName(rs.getString("COUNTRY_NAME"));
					ba.setPobox(rs.getString("POBOX"));
					ba.setTelephone(rs.getString("TELEPHONE"));
					ba.setFax(rs.getString("FAX"));
					ba.setEmirate(rs.getString("EMIRATE"));
					ba.setOthercity(rs.getString("CITY"));
					ba.setTitle(rs.getString("TITLE"));
					ba.setGender(rs.getString("GENDER"));
					ba.setFirstname(rs.getString("FIRST_NAME"));
					ba.setLastname(rs.getString("LAST_NAME"));
					ba.setNationality(rs.getString("NATIONALITY"));
					ba.setNationalityNa(rs.getString("NATIONALITYNAME"));
					ba.setDob(rs.getString("DOB"));
					ba.setOccupation(rs.getString("OCCUPATION"));
					ba.setMobile(rs.getString("MOBILE"));
					ba.setBemail(rs.getString("EMAIL"));
					ba.setExecutive(rs.getString("AC_EXECUTIVE_ID"));
					ba.setEntryDate(rs.getString("ENTRY_DATE"));
					ba.setLogin_Id(rs.getString("LOGIN_ID"));
					ba.setCustomer_id(rs.getInt("customer_id"));
					ba.setMissippiId(rs.getString("missippi_id"));
					ba.setCompanyName(rs.getString("COMPANY_NAME"));
					ba.setCIMSNO(rs.getString("missippi_id"));
					ba.setCustomerName(rs.getString("CUST_NAME"));
					ba.setStatus(rs.getString("STATUS"));
					ba.setOneOffCommission(rs.getString("ISSUER_COMMISSION_ONEOFF"));
					ba.setOpenCoverCommission(rs.getString("ISSUER_COMMISSION_OPENCOVER"));
					ba.setBroImgName(rs.getString("IMAGE_PATH"));
					ba.setVatRegNo(rs.getString("VAT_REG_NO"));
					return null;
				}
		   });
			ba.setAgencyCode(agencyCode);
		}
		catch (Exception e) {
			logger.debug("EXCEPTION @ { " + e + " }");
		}
    	return brokerDetails;
	}
	
	public List <Map<String, Object>> getCommisionData(String agencyCode) 
	{
		List <Map<String, Object>> commisionDetails=null;
		try 
		{
			logger.info("Method to getCommisionData");
			String query=getQuery("GET_Commission_Data")+" order by lud.product_id";
			logger.info("Query===>" + query);
			logger.info("AgencyCode===>" + agencyCode);
			Object[] obj=new Object[1];
			obj[0]=agencyCode;
			commisionDetails=this.mytemplate.queryForList(query,obj);
			logger.info("getCommisionData() - Exit");
		} 
		catch (Exception e) 
		{
			logger.debug("EXCEPTION @ { " + e + " }");
		}
		return commisionDetails;
	}
	
	public List <Map<String, Object>> getProducts(final BrokerMgmBean ba) 
	{
		List <Map<String, Object>> productData=null;
		try 
		{
			logger.info("Method to getProducts");
			String query=getQuery("GET_Commission_Data")+" AND lud.PRODUCT_ID=?";
			logger.info("Query===>" + query);
			Object[] obj={ba.getAgencyCode(), ba.getProductID()};
			productData=this.mytemplate.queryForList(query,obj);
			if(productData!=null && productData.size()>0){
				Map map=(Map)productData.get(0);
				ba.setProductID(map.get("PRODUCT_ID")==null?"":map.get("PRODUCT_ID").toString());
				ba.setProductName(map.get("product_name")==null?"":map.get("product_name").toString());
				ba.setCommission(map.get("COMMISSION")==null?"":map.get("COMMISSION").toString());
				ba.setInsurance_End_Limit(map.get("INSURANCE_END_LIMIT")==null?"":map.get("INSURANCE_END_LIMIT").toString());
				ba.setUser_Id_Creation(map.get("PROVISION_FOR_PREMIUM")==null?"":map.get("PROVISION_FOR_PREMIUM").toString());
				ba.setDiscountPremium(map.get("DISCOUNT_PREMIUM")==null?"":map.get("DISCOUNT_PREMIUM").toString());
				ba.setMin_Premium_Amount(map.get("MIN_PREMIUM_AMOUNT")==null?"":map.get("MIN_PREMIUM_AMOUNT").toString());
				ba.setBack_Date_Allowed(map.get("BACK_DATE_ALLOWED")==null?"":map.get("BACK_DATE_ALLOWED").toString());
				ba.setProductStatus(map.get("status")==null?"":map.get("status").toString());
				ba.setLoadingPremium(map.get("LOADING_PREMIUM")==null?"":map.get("LOADING_PREMIUM").toString());
				ba.setRemark(map.get("REMARKS")==null?"":map.get("REMARKS").toString());
				ba.setProvision(map.get("RECEIPT_STATUS")==null?"":map.get("RECEIPT_STATUS").toString());
				ba.setFreight(map.get("FREIGHT_DEBIT_OPTION")==null?"":map.get("FREIGHT_DEBIT_OPTION").toString());
				ba.setPayReceipt(map.get("pay_receipt_status")==null?"":map.get("pay_receipt_status").toString());
				ba.setRemark(map.get("REFERAL")==null?"":map.get("REFERAL").toString());
			}
			logger.info("getProducts() - Exit");
		}
		catch (Exception e)
		{
			logger.debug("EXCEPTION @ { " + e + " }");
		}
		return productData;
	}
	
	public List <Map<String, Object>> getBranchDetails(String branchCode)
	{
		List <Map<String, Object>> branchData=null;
		try 
		{
			logger.info("Method to getBranches");
			String query=getQuery("GET_Branch_Detail");
			logger.info("Query===>" + query);
			logger.info("BranchCode===>" + branchCode);
			Object[] obj=new Object[1];
			obj[0]=branchCode;
			branchData=this.mytemplate.queryForList(query,obj);
			logger.info("getBranches() - Exit");
		}
		catch (Exception e) 
		{
			logger.debug("EXCEPTION @ { " + e + " }");
		}
		return branchData;
	}
	public List <Map<String, Object>> getBrokerCode() 
	{
		List <Map<String, Object>> brokerCode=null;
		try 
		{
			logger.info("Method to getBrokerCode");
			String query=getQuery("GET_Broker_Code");
			logger.info("Query===>" + query);
			brokerCode=this.mytemplate.queryForList(query);
			logger.info("getBrokerCode() - Exit");
		}
		catch (Exception e) 
		{
			logger.debug("EXCEPTION @ { " + e + " }");
		}
		return brokerCode;
	}
	
	public List <Map<String, Object>> getExecutives() {
		List <Map<String, Object>> executives =null;
		   try
		   { 
			   logger.info("Method to getExecutives");
				String query=getQuery("GET_Executives");
				logger.info("Query===>" + query);
				executives=this.mytemplate.queryForList(query);
				logger.info("getExecutives() - Exit");
		   }
		   catch (Exception e) 
			{
				logger.debug("EXCEPTION @ { " + e + " }");
			}
		return executives;
	 }
	
	public List <Map<String, Object>> getcoreCustomererInfo(String cust_name, String bcode, String userLoginMode) {
		List <Map<String, Object>> coreCustomerInfo =null;
		   try
		   { 
			   logger.info("Method to getcoreCustomererInfo");
				String query="SELECT CIMSNO,(CASE WHEN CUSTCODE IS NULL OR CUSTGRP IS NULL OR CUSTTYPE IS NULL OR CUSTCLAS IS NULL THEN '' ELSE (CUSTCODE ||'-'|| CUSTGRP ||'-'|| CUSTTYPE ||'-'||CUSTCLAS) END) ARACC, CUSTTITL,CUSTNAME,CUSTADD1,CUSTADD2,CUSTADD3,CUSTADD4 FROM C_CUST@ECARGO_"+("Test".equalsIgnoreCase(userLoginMode)?"DEV":"PROD")+" WHERE lower(CUSTNAME) LIKE '%'||lower(?)||'%' AND BRCODE=?";
				logger.info("Query===>" + query);
				Object[] obj=new Object[2];
				obj[0]=cust_name;
				obj[1]=bcode;
				coreCustomerInfo=this.mytemplate.queryForList(query,obj);
				logger.info("getcoreCustomererInfo() - Exit :   Result-->"+coreCustomerInfo.size());
		   }
		   catch (Exception e) 
			{
				logger.debug("EXCEPTION @ { " + e + " }");
			}
		return coreCustomerInfo;
	}
	
	public List <String> getBrokerTaxInfo(final BrokerMgmBean ba,String[] args){
		logger.info("Method to getBrokerTaxInfo");
		List customerTaxInfo=null;
		try{
			String query=getQuery("GET_Tax_Info");
			logger.info("Query===>" + query);
			customerTaxInfo=this.mytemplate.query(query,args,new RowMapper(){
				public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
					ba.setPolicy_fee(rs.getString("policy_feestatus"));
					ba.setPolicFee(rs.getString("policy_fees"));
					ba.setGov_fee(rs.getString("gov_taxstatus"));
					ba.setGovtTax(rs.getString("govt_tax"));
					ba.setEmer_fee(rs.getString("emergency_fundstatus"));
					ba.setEmergencyfund(rs.getString("emer_fund"));
					ba.setApp_for(rs.getString("TAX_APPLICABLE"));
					ba.setEffecdate(rs.getString("effec_date"));
					return null;
				}
		   });
		}
		catch(Exception e){
			logger.debug("EXCEPTION @ { " + e + " }");
		}
		return customerTaxInfo;
	}
	public void newBrokerCreation(Object[] args, Object[] info){
		logger.info("ENTER-->Method to newBrokerCreation");
    	try{
			String query=getQuery("INS_Broker_Det");
			String query1=getQuery("INS_BCM_DET");
			logger.info("Query===>" + query);
			logger.info("Query1===>" + query1);
			this.mytemplate.update(query1,info);
			logger.info("Success===>"+query1);
			this.mytemplate.update(query,args);
			logger.info("Success===>"+query);
			logger.info("newBrokerCreation() - Exit");
    	}
    	catch (Exception e) {			
    		logger.debug("EXCEPTION @ newBrokerCreation { " + e + " }");
    	}
	}
	public int getBroke_Code(){
		int brokerCode=0;
		try{
			String query=getQuery("GET_BRO_CODE");
			logger.info("Query===>" + query);	
			brokerCode=this.mytemplate.queryForObject(query, Integer.class);
			logger.info("brokerCode===>" + brokerCode);
		}
		catch(Exception e){
			logger.debug("EXCEPTION @ { " + e + " }");
		}
		return brokerCode;
	}
	public void getmax_Broke_Code(Object[] args){
		try{
			String query=getQuery("GET_MAX_BRO_CODE");
			logger.info("Query===>" + query);
			this.mytemplate.update(query,args);
		}
		catch(Exception e){
			logger.debug("EXCEPTION @ { " + e + " }");
		}
	}
	public void update_Broker(Object[] args){
		try{
			String query=getQuery("UPD_BROKER_DET");
			logger.info("Query===>" + query);
			this.mytemplate.update(query,args);
			logger.info("Exit ===>UPD_BROKER_DET");
		}
		catch(Exception e){
			logger.debug("EXCEPTION @ { " + e + " }");
		}
	}
	public void update_PersonalInfo(Object[] args){
		try{
			String query=getQuery("UPD_PERSONAL_INFO");
			logger.info("Query===>" + query);
			this.mytemplate.update(query,args);
			logger.info("Exit ===>update_PersonalInfo");
		}
		catch(Exception e){
			logger.debug("EXCEPTION @ { " + e + " }");
		}
	}
	public int getCustomer_Id(String agencyCode, String branchCode){
		int customer_Id=0;
		try{
			String query=getQuery("GET_CUST_ID");
			logger.info("Query===>" + query);	
			String[] args={agencyCode,branchCode};
			customer_Id=this.mytemplate.queryForObject(query, Integer.class, args);
			logger.info("brokerCode===>" + customer_Id);
		}
		catch(Exception e){
			logger.debug("EXCEPTION @ { " + e + " }");
		}
		return customer_Id;
	}
	public int getMax_amend_Id(String branchCode, int broker_Code){
		int amend_Id=0;
		try{
			String query=getQuery("GET_MAXAMEND_ID");
			logger.info("Query===>" + query);	
			Object[] obj=new Object[2];
			obj[0]=branchCode;
			obj[1]=broker_Code;
			amend_Id=this.mytemplate.queryForObject(query, Integer.class, obj);
		}
		catch(Exception e){
			logger.debug("EXCEPTION @ { " + e + " }");
		}
    	return amend_Id;
    }
    public void insTaxInfo(Object[] val){
		logger.info("ENTER-->Method to newBrokerCreation");
    	try{
			String query=getQuery("INS_TAX_DET");
			logger.info("Query===>" + query);
			this.mytemplate.update(query,val);
			logger.info("Success===>"+query);
			logger.info("insTaxInfo() - Exit");
    	}
    	catch (Exception e) {			
    		logger.debug("EXCEPTION @ { " + e + " }");
    	}
	}
    public void updTaxInfo(Object[] val){
		logger.info("ENTER-->Method to updTaxInfo");
    	try{
			String query=getQuery("UPD_TAX_DET");
			logger.info("Query===>" + query);
			this.mytemplate.update(query,val);
			logger.info("updTaxInfo() - Exit");
    	}
    	catch (Exception e) {			
    		logger.debug("EXCEPTION @ { " + e + " }");
    	}
	}
    public List <Map<String, Object>> getCommissionDET(String agencyCode, String branchCode){
		logger.info("Method to getCommissionDET");
		List <Map<String, Object>> commission_Det=null;
		try{
			String query=getQuery("GET_COMMISSION_DET");
			logger.info("Query===>" + query);
			String[] args={agencyCode, branchCode};
			logger.info("Query===>" + args[0]+"		"+args[1]);
			commission_Det= this.mytemplate.queryForList(query,args);
		}
		catch(Exception e){
			logger.debug("EXCEPTION @ { " + e + " }");
		}
		logger.info("EXIT getCommissionDET : RESULT===>" + commission_Det.size());
		return commission_Det;
    }
    
    public List <String> getCompDet(final BrokerMgmBean ba,String agencyCode){
		logger.info("Method to getCompDet");
		List comp_Det=null;
		try{
			String query=getQuery("GET_COMP_DET");
			logger.info("Query===>" + query);
			String[] args={agencyCode};
			logger.info("Query===>" + args[0]);
			comp_Det=this.mytemplate.query(query,args,new RowMapper(){
				public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
					ba.setBorganization(rs.getString("COMPANY_NAME"));
					ba.setFirstname(rs.getString("FIRST_NAME"));
					return null;
				}
		   });
		}
		catch(Exception e){
			logger.debug("EXCEPTION @ { " + e + " }");
		}
		return comp_Det;
    }
   
    public void updateProduct(Object[] pId){
		logger.info("ENTER-->Method to updateProduct");
    	try
    	{
			String query=getQuery("UPD_PRODUCT_ID");
			logger.info("Query===>" + query);
			this.mytemplate.update(query, pId);
    	}catch(Exception e)
    	{
    		logger.debug("Exception @ "+e);
    	}
    	logger.info("updateProduct() - Exit");
    }
    public List <String> getLogInId(final BrokerMgmBean ba,String agencyCode){
		logger.info("Method to getLogInId");
		List logInId_Det=null;
		try{
			String query=getQuery("GET_LOGIN_ID");
			logger.info("Query===>" + query);
			String[] args={agencyCode};
			logger.info("Query===>" + args[0]);
			logInId_Det=this.mytemplate.query(query,args,new RowMapper(){
				public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
					ba.setLoginid(rs.getString("LOGIN_ID"));
					return null;
				}
		   });
		}
		catch(Exception e){
			logger.debug("EXCEPTION @ { " + e + " }");
		}
		return logInId_Det;
    }
    public void insertLogInDet(String[] args){
		logger.info("ENTER-->Method to insertLogInDet");
    	try
    	{
			String query=getQuery("INS_LOGIN_DET");
			logger.info("Query===>" + query);
			this.mytemplate.update(query, args);
    	}catch(Exception e)
    	{
    		logger.debug("Exception @ "+e);
    	}
    	logger.info("insertLogInDet() - Exit");
    }
    public void updatePersonalLogin(Object[] params){
		logger.info("ENTER-->Method to updatePersonalLogin");
    	try
    	{
			String query=getQuery("UPD_PERSONAL_LOGIN_ID");
			logger.info("Query===>" + query);
			this.mytemplate.update(query, params);
    	}catch(Exception e)
    	{
    		logger.debug("Exception @ "+e);
    	}
    	logger.info("updatePersonalLogin() - Exit");
    }
    public String getBrokerStatus(String agencyCode){
		logger.info("getBrokerStatus - Enter");
		String status="";
		try
		{
			String query=getQuery("GET_BROKER_STATUS");
			String[] obj={agencyCode};
			status=this.mytemplate.queryForObject(query,obj,String.class).toString();			
		}
		catch (Exception e)
		{			
			logger.debug("EXCEPTION @ { " + e + " }");
		}		
		logger.info("getBrokerStatus - Exit ");
		return status;
	}
	public void updateBrokerStatus(String[] obj){
		logger.info("updateBrokerStatus - Enter");
		try
		{
			String query=getQuery("UPD_BROKER_STATUS_LM");
			logger.info("query==>"+query);
			for(Object k: obj)
				logger.info("query==>"+k);
			this.mytemplate.update(query,obj);
			query=getQuery("UPD_BROKER_STATUS_BCM");
			logger.info("query==>"+query);
			this.mytemplate.update(query,new Object[]{obj[1], obj[4]});
		}
		catch (Exception e)
		{			
			logger.debug("EXCEPTION @ { " + e + " }");
		}		
		logger.info("updateBrokerStatus - Exit ");
	}
	/*public List <Map<String, Object>> getProductStatus(String[] obj){
		logger.info("getProductStatus - Enter");
		List <Map<String, Object>> userInfo=null;
		try
		{
			String query=getQuery("GET_USER_LIST");
			userInfo=this.mytemplate.queryForList(query,obj);		
		}
		catch (Exception e)
		{			
			logger.debug("EXCEPTION @ { " + e + " }");
		}		
		logger.info("getProductStatus - Exit ");
		return userInfo;
	}*/
	public List <Map<String, Object>> getProductStatuss(String[] obj){
		logger.info("getProductStatuss - Enter");
		List <Map<String, Object>> userInfo=null;
		try
		{
			String query=getQuery("GET_USER_LISTT");
			userInfo=this.mytemplate.queryForList(query,obj);		
		}
		catch (Exception e)
		{			
			logger.debug("EXCEPTION @ { " + e + " }");
		}		
		logger.info("getProductStatuss - Exit ");
		return userInfo;
	}
	public void updateUserIdCreation(String[] obj){
		logger.info("updateUserIdCreation - Enter");
		try
		{
			String query=getQuery("UPD_USERID_CREATION");
			this.mytemplate.update(query,obj);			
		}
		catch (Exception e)
		{			
			logger.debug("EXCEPTION @ { " + e + " }");
		}		
		logger.info("updateUserIdCreation - Exit ");
	}
	public List<Map<String, Object>> getBrokerListAjax(String branchCode, String searchBy, String searchValue, String appID){
		List <Map<String, Object>> brokerList=null;
		String query="";
		try{
			if("name".equals(searchBy)){
				query=getQuery("GET_BROKER_LIST_BY_NAME");
			}else if("status".equals(searchBy)) {
				query=getQuery("GET_BROKER_LIST_BY_STATUS");
			}
			else 
				query=getQuery("GET_BROKER_LIST_BY_CODE");
			logger.info("Query===>" + query);
			logger.info("branchCode===>" + branchCode);
			brokerList=this.mytemplate.queryForList(query,new Object[]{branchCode, appID, branchCode, searchValue});		
		   }
		catch (Exception e) {			
			e.printStackTrace();
		}
		return brokerList;
	}
	public void deleteProduct(BrokerMgmBean bean) {
		try{
			String query=getQuery("BROKER_DELETE_PRODUCT");
			String args[]=new String[2];
			args[0]=bean.getAgencyCode();
			args[1]=bean.getProductID();
			logger.info("Query "+query);
			logger.info("args "+StringUtils.join(args,","));
			this.mytemplate.update(query,args);
		}catch(Exception e){
			logger.debug("EXCEPTION @ { " + e + " }");
		}
	}
	public int validateBcode(String brokerCode) {
		int result = 0;
		try {
		    LocalizedTextProvider ltp = ActionContext.getContext().getInstance(LocalizedTextProvider.class);
			String query = ltp.findDefaultText("CHK_BROKERCODE_CNT",Locale.ENGLISH);
			result = this.mytemplate.queryForObject(query, Integer.class,new Object[]{brokerCode});
		}
		catch(Exception ex) {
			
		}
		return result; 
	}
	public void deleteBroLogo(String agencyCode) {
		try{
			String query=getQuery("BROKER_DELETE_LOGO");
			logger.info("Query "+query);
			logger.info("agencyCode "+agencyCode);
			this.mytemplate.update(query,new Object[]{agencyCode});
		}catch(Exception e){
			logger.debug("EXCEPTION @ { " + e + " }");
		}
	}
	public int valBraWiseBcode(String brokerCode,String branchCode) {
		int result = 0;
		try {
			String query = getQuery("BRANCH_BCODE_CNT");
			result = this.mytemplate.queryForObject(query, Integer.class,new Object[]{brokerCode,branchCode});
		}
		catch(Exception ex) {
			
		}
		return result; 
	}
}