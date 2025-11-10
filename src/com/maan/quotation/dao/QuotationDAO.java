package com.maan.quotation.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import com.maan.adminnew.lcMaster.LCMasterBean;
import com.maan.common.DBConstants;
import com.maan.common.LogUtil;
import com.maan.common.MyJdbcTemplate;
import com.maan.quotation.QuotationAction;
import com.maan.services.util.runner;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.LocalizedTextProvider;

public class QuotationDAO extends MyJdbcTemplate{
	private static final Logger logger = LogUtil.getLogger(QuotationDAO.class);
	private String sql;
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getOptionsList(String option, Object[] objects)
	{
		logger.info("getOptionsList - Enter || option: "+option);
		List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
		try {
			//if(!("branch".equalsIgnoreCase(option)||"broker".equalsIgnoreCase(option))){
			if(!("branch".equalsIgnoreCase(option))){
				if("commodity_oc".equalsIgnoreCase(option)) {
					sql=getQuery(DBConstants.COMMODITY_OC);
				}else if("commodity".equalsIgnoreCase(option)) {
					sql=getQuery(DBConstants.COMMODITY);
				}
				else {
					sql=getQuery(DBConstants.OPTION);
					objects[0]=option;
				}
				logger.info("sql==>"+sql);
				for (int i = 0; i < objects.length; i++) {
					if(objects[i]==null){
						objects[i]="";
					}
				}
				logger.info("args[] ==> "+StringUtils.join(objects, ","));
				list = this.mytemplate.queryForList(sql, objects);

			}else if("branch".equalsIgnoreCase(option)){
				sql=getQuery("issuer.branch");
				list = this.mytemplate.queryForList(sql, objects);
			}/*else if("broker".equalsIgnoreCase(option)){
				sql=getQuery("issuer.broker");
				list = this.mytemplate.queryForList(sql, objects);
			}*/
		}catch(Exception e) {
			logger.debug("Exception @ "+e);
		}
		
		logger.info("getOptionsList - Exit || Result: "+list.size());
		return list;	
	}
	public Map<String, Object> getBranchInfo(String branchCode)
	{
		logger.info("getBranchInfo - Enter || branchCode: "+branchCode);
		Map<String, Object> list=new HashMap<String, Object>();
		try
		{
			sql=getQuery(DBConstants.BRANCH_INFO);
			list = this.mytemplate.queryForMap(sql, new String[]{branchCode});
		}catch(Exception e)
		{
			logger.debug("Exception @ "+e);
		}
		
		logger.info("getBranchInfo - Exit || Result: "+list.size());
		return list;	
	}
	public String addCommodity(String applicationNo, String refNo, List<Map<String, Object>> commList)
	{
		logger.info("addCommodity - Enter || applicationNo: "+applicationNo);
		Map<String, Object> commInfo=null;
		try
		{
			if(commList!=null && commList.size()>0)
			{
				sql=getQuery(DBConstants.COMMODITY_DELETE);
				this.mytemplate.update(sql, new String[]{applicationNo});
				sql=getQuery(DBConstants.COMMODITY_ADD);
				for (int i = 0; i < commList.size(); i++) {
					commInfo=(Map<String, Object>)commList.get(i);
					this.mytemplate.update(sql, new String[]{commInfo.get("COMMODITY_ID").toString(),
							commInfo.get("COMMODITY_NAME").toString(),commInfo.get("SUM_INSURED").toString(),
							commInfo.get("SUPPLIER_NAME").toString(),commInfo.get("PACKAGE_DESC").toString(),
							commInfo.get("INVOICE_NUMBER").toString(),commInfo.get("INVOICE_DATE").toString(),
							commInfo.get("FRAGILE").toString(),refNo,applicationNo});
				}
			}
		}catch(Exception e)
		{
			logger.debug("Exception @ "+e);
		}
		
		logger.info("addCommodity - Exit");
		return "";	
	}
	public String updateCommodity(String applicationNo, List<Map<String, Object>> commList)
	{
		logger.info("addCommodity - Enter || applicationNo: "+applicationNo);
		Map<String, Object> commInfo=null;
		try
		{
			if(commList!=null && commList.size()>0)
			{
				sql=getQuery(DBConstants.COMMODITY_UPDATE);
				for (int i = 0; i < commList.size(); i++) {
					commInfo=(Map<String, Object>)commList.get(i);
					this.mytemplate.update(sql, new String[]{commInfo.get("SUPPLIER_NAME").toString(),commInfo.get("PACKAGE_DESC").toString(),
							commInfo.get("INVOICE_NUMBER").toString(),commInfo.get("INVOICE_DATE").toString(),
							commInfo.get("COMMODITY_GROUP_ID").toString(),commInfo.get("COMMODITY_ID").toString(),applicationNo});
				}
			}
		}catch(Exception e)
		{
			logger.debug("Exception @ "+e);
		}
		
		logger.info("addCommodity - Exit");
		return "";	
	}
	public String insertOrUpdateQuote(String applicationNo, Object[] args, String quoteType)
	{
		logger.info("insertOrUpdateQuote - Enter || applicationNo: "+applicationNo+"quoteType: "+quoteType+" args: "+StringUtils.join(args, ","));
		try
		{
			if("F".equalsIgnoreCase(quoteType)){
				sql=getQuery(DBConstants.QUOTE_ADD);
			}else{
				sql=getQuery(DBConstants.QUOTE_UPDATE);
			}
			logger.info("sql==>"+sql);
			logger.info("args: "+StringUtils.join(args, ","));
			for (int i = 0; i < args.length; i++) {
				if(args[i]==null){
					args[i]="";
				}
			}
			this.mytemplate.update(sql, args);
		}catch(Exception e)
		{
			logger.debug("Exception @ "+e);
		}
		
		logger.info("insertOrUpdateQuote - Exit");
		return "";	
	}
	@SuppressWarnings("unchecked")
	public List<String> getQuoteInfo(final QuotationAction qa)
	{
		logger.info("getQuoteInfo - Enter || applicationNo: "+qa.getApplicationNo()+" quoteNo: "+qa.getQuoteNo());
		List<String> list=null;
		try
		{
			sql=getQuery(DBConstants.QUOTE_EDIT);
			list = (List<String>) this.mytemplate.query(sql, new Object[]{StringUtils.defaultIfEmpty(qa.getApplicationNo(), ""),StringUtils.defaultIfEmpty(qa.getQuoteNo(), "")},new RowMapper() {
				public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
					qa.setCustomerName(rs.getString("CUSTOMER_NAME"));
					qa.setCity(rs.getString("EMIRATE"));
					qa.setMobileNo(rs.getString("MOBILE_NUMBER"));
					qa.setModeOfTransport(rs.getString("MODE_OF_TRANSPORT"));
					qa.setOriginCountry(rs.getString("ORIGINATING_COUNTRY"));
					qa.setDestCountry(rs.getString("DESTINATION_COUNTRY"));
					qa.setOriginCity(rs.getString("ORIGINATING_COUNTRY_CITY"));
					qa.setDestCity(rs.getString("DESTINATION_COUNTRY_CITY"));
					qa.setOriginCityName(rs.getString("ORIGIN_CITY_NAME"));
					qa.setDestCityName(rs.getString("DEST_CITY_NAME"));
					qa.setOriginWarehouse(rs.getString("ORIGINATING_WAREHOUSE_COVERAGE"));
					qa.setDestWarehouse(rs.getString("DESTINATION_WAREHOUSE_COVERAGE"));
					qa.setSaleTerm(rs.getString("SALETERM_VALUE"));
					qa.setCurrency(rs.getString("CURRENCY"));
					qa.setSettlingAgent(rs.getString("SETTLING_AGENT"));
					qa.setAgentOthers(rs.getString("SETTLING_AGENT_NAME"));
					qa.setPolicyStartDate(rs.getString("POLICY_START_DATE"));
					qa.setSailingDate(rs.getString("SAILING_DATE"));
					qa.setVesselName(rs.getString("VESSEL_NAME"));
					qa.setLcNo(rs.getString("LC_NUMBER"));
					qa.setLcBank(rs.getString("ISSUING_BANK"));
					qa.setLcDate(rs.getString("LC_DATE"));
					qa.setPartialShipment(rs.getString("PARTIAL_SHIPMENT"));
					qa.setBlNo(rs.getString("BL_AWB_NUMBER"));
					qa.setBlDate(rs.getString("BL_AWB_DATE"));
					qa.setCover(rs.getString("COVERAGES"));
					qa.setConveyance(rs.getString("SEA_COVERAGES"));
					qa.setWarSrcc(rs.getString("WSRCC"));
					qa.setTolerance(rs.getString("TOLERANCE"));
					qa.setPackageCode(rs.getString("PACKAGE_TYPE"));
					qa.setSaleTermPercent(rs.getString("SALETERM_PERCENT"));
					qa.setRefNo(rs.getString("REFERENCE_NUMBER"));
					qa.setTitle(rs.getString("CUST_TITLE"));
					qa.setCoreAppCode(rs.getString("CUST_CORE_APP_CODE"));
					qa.setAddress1(rs.getString("ADDRESS1"));
					qa.setAddress2(rs.getString("ADDRESS2"));
					qa.setEmail(rs.getString("EMAIL"));
					qa.setPoBox(rs.getString("PO_BOX"));
					qa.setBranchCode(rs.getString("BRANCH_CODE"));
					qa.setBrokerCode(rs.getString("BROKER_CODE"));
					//qa.setProductId(rs.getString("PRODUCT_ID"));
					qa.setApplicationNo(rs.getString("APPLICATION_NO"));
					qa.setQuoteNo(rs.getString("QUOTE_NO"));
					qa.setOpenCoverNo(rs.getString("OPENCOVER_POLICY_NUMBER"));
					qa.setExecutive(rs.getString("AC_EXECUTIVE_ID"));
					qa.setCustomerId(rs.getString("CUSTOMER_ID"));
					qa.setExposureCurrency(rs.getString("EXPOSURE_CURRENCY"));
					qa.setShipmentExposure(rs.getString("SHIPMENT_EXPOSURE"));
					qa.setCustContractNo(rs.getString("CUST_CONTRACT_NO"));
					qa.setCustFmsCaseNo(rs.getString("CUST_FMS_CASE_NO"));
					qa.setCustRefNo(rs.getString("CUST_REFERENCE_NO"));
					qa.setCustArNo(rs.getString("CUST_AR_NO"));
					qa.setVesselId(rs.getString("VESSEL_ID"));
					qa.setPromotionalCode(rs.getString("PROMOTIONAL_CODE"));
					qa.setConsigneeDetail(rs.getString("CONSIGNEE_DET"));
					qa.setSpecialTerm(rs.getString("SPECIAL_TERM"));
					qa.setAddCustomerName(rs.getString("ADD_CUST_NAME"));
					qa.setCoreCustomerName(rs.getString("core_customer_name"));
					qa.setNameAndCode(rs.getString("core_customer_name")+"("+rs.getString("CUST_CORE_APP_CODE")+")");
					qa.setChannelType(rs.getString("CHANNEL_TYPE"));
					qa.setExchageValue(rs.getString("EXCHANGE_RATE"));
					
					qa.setCustVatRegNo(rs.getString("VAT_REG_NO"));
					qa.setCustVatRegYn(rs.getString("CUST_VAT_YN"));
					qa.setVatApplicable(rs.getString("VAT_APPLICABLE"));
					qa.setVesselImoNo(rs.getString("VESSEL_IMO_NO"));
					qa.setShipmenttransit(rs.getString("SHIPMENT_TRANSIT"));
					//qa.setLcAmount(rs.getString("LC_AMT"));
					/*qa.setLcexchageValue(rs.getString("LC_EXCHANGEVALUE"));
					qa.setLcCurrency(rs.getString("LC_CURRECNYID"));*/
					return null;
				}
			});
			sql=getQuery(DBConstants.COMMODITY_TOTAL);
			list=this.mytemplate.query(sql, new Object[]{qa.getRefNo()}, new RowMapper() {
				public Object mapRow(ResultSet rs, int rowNum) throws SQLException {				
					qa.setTotalCommodity(rs.getString("TOTAL_COMMODITY"));
					qa.setTotalSI(rs.getString("SUM_INSURED"));
					qa.setCommodity(rs.getString("COMMODITY_DESCRIPTION"));
					return null;
				}
			});
		}catch(Exception e)
		{
			logger.debug("Exception @ "+e);e.printStackTrace();
		}
		
		logger.info("getQuoteInfo - Exit || Result: "+list.size());
		return list;	
	}
	public List<Map<String, Object>> getCustomerSelectionList(String loginId,String searchValue,String openCoverNo)
	{
		logger.info("getCustomerSelectionList - Enter || "+loginId+","+searchValue);
		List<Map<String, Object>> customerList=null;		
		searchValue=searchValue==null?"":searchValue;
		try{
			if("".equalsIgnoreCase(searchValue)){
				if(StringUtils.isNotEmpty(openCoverNo) && !"0".equals(openCoverNo)){
					sql=getQuery(DBConstants.OPENCOVER_CUSTOMER_LIST);
					customerList=this.mytemplate.queryForList(sql,new String[]{openCoverNo});
				}else{
					sql=getQuery(DBConstants.CUSTOMER_SELECTION);
					customerList=this.mytemplate.queryForList(sql,new String[]{loginId});}
			}
			else
			{
				if(StringUtils.isNotEmpty(openCoverNo) && !"0".equals(openCoverNo)){
					sql=getQuery(DBConstants.OPENCOVER_CUSTOMER_BYNAME);
					customerList=this.mytemplate.queryForList(sql,new String[]{openCoverNo,"%"+searchValue+"%"});
				}else{			
					sql=getQuery(DBConstants.CUSTOMER_SELECTION_BYNAME);
					customerList=this.mytemplate.queryForList(sql,new String[]{loginId,"%"+searchValue+"%"});
				}
			}					
		}
		catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}		
		logger.info("Query==>" + sql);
		logger.info("args: "+loginId+","+searchValue);
		logger.info("getCustomerSelectionList() - Exit size"+ customerList.size() );
				
		return customerList;
	}
	public List<Map<String, Object>> getCoreCustomerSearch(String searchValue, String branchCode, String channelType) {
		List<Map<String, Object>> customerList=null;
		try {
		    LocalizedTextProvider ltp = ActionContext.getContext().getInstance(LocalizedTextProvider.class);
			String sql = ltp.findDefaultText("GET_CORE_CUSTOMER_LIST",Locale.ENGLISH) + " WHERE UPPER(CUST_NAME) LIKE '%"+ searchValue.toUpperCase() +"%' AND CUST_FRZ_FLAG='N' ";
			logger.info("Query==> " + sql);
			/*if("customer".equalsIgnoreCase(channelType)) {
				sql += " AND CUST_CLASS LIKE '02%' ";
			}
			else {
				sql += " AND CUST_CLASS IN ('0181','0182','0183','0184','0185','0186') ";
			}*/
			//sql += " AND CUST_CLASS LIKE '02%' ";
			customerList = this.mytemplate.queryForList(sql);
		}
		catch(Exception e) {
			logger.debug("EXCEPTION @ { " + e + " }");
		}
				
		return customerList;
	}
	public String getSingleInfo(String option, String[] args)
	{
		logger.info("getSingleInfo - Enter || "+option+" args: "+ StringUtils.join(args, ","));
		String result="";					
		try{
			sql=getQuery(option);
			result=(String)this.mytemplate.queryForObject(sql,args, String.class);			
		}
		catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}		
		logger.info("Query==>" + sql);
		logger.info("getSingleInfo() - Exit || Result: "+result );
				
		return result;
	}
	public List<Map<String, Object>> getVesselListNew(String alpha)
	{
		logger.info("getVesselList - Enter || "+alpha);
		List<Map<String, Object>> vesselList=null;					
		logger.info("Method to get Vessel List");
		try{
			sql=getQuery(DBConstants.VESSEL_SELECTIION);
			vesselList=this.mytemplate.queryForList(sql,new String[]{"%"+alpha+"%"});			
		}
		catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}		
		logger.info("Query==>" + sql);
		logger.info("getVesselList() - Exit: size"+ vesselList.size());
				
		return vesselList;
	}
	public String checkExist(String dataValue,String datatype,String applicationNo)
	{
		String result=null;
		logger.info("checkLCNOExist");
		try{
			if("LCNO".equals(datatype))
			{
				sql=getQuery(DBConstants.LCNO_EXIST);
			}
			else {
				sql=getQuery(DBConstants.INVOICE_EXIST);	
			}
			result=(String)this.mytemplate.queryForObject(sql,new String[]{dataValue,applicationNo},String.class);			
		}
		catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}		
		logger.info("Query==>" + sql);
		logger.info("Result - Exit:"+ result);
				
		return result;
	}
	public String getBrokerLoginId(String brokerCode)
	{
		String result=null;
		logger.info("getBrokerLoginId");
		try{	
			sql=getQuery(DBConstants.BROKER_LOGINID);	
			result=(String)this.mytemplate.queryForObject(sql,new String[]{brokerCode},String.class);			
		}
		catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}		
		logger.info("Query==>" + sql+"args :"+brokerCode);
		logger.info("Result - Exit:"+ result);
				
		return result;
	}
	public List<Map<String, Object>> getVesselList(String vesselName){
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		String sql = "";
		try{
			sql = "SELECT VESSEL_ID,VESSEL_NAME,VESSEL_TYPE,VESSEL_CLASS,MANUFACTURE_YEAR FROM VESSEL_MASTER WHERE STATUS IN ('Y','R') AND UPPER(VESSEL_NAME) LIKE UPPER(?) ORDER BY VESSEL_NAME";
			result = this.mytemplate.queryForList(sql,new String[]{"%"+vesselName+"%"});
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	public String getPolicyEndtType(String applicationNo)
	{
		logger.info("getPolicyEndtType - Enter || applicationNo: "+applicationNo);
		String endtType="";
		try{
			endtType=(String)this.mytemplate.queryForObject(getQuery("GET_POL_ENDT"),new String[]{applicationNo},String.class);
			if(StringUtils.isNotEmpty(endtType)){
				endtType=(String)this.mytemplate.queryForObject(getQuery("GET_QUOTE_ENDT_TYPE").replace("?",endtType),String.class);
			}
		}catch(Exception e){
			logger.debug(e);
		}
		logger.info("getPolicyEndtType - Exit || Result: " );
		return endtType;
	}
	public String getDirectStatus(String applicationNo,String branchCode)
	{
		logger.info("getDirectStatus - Enter || applicationNo: "+applicationNo+" branchCode: "+branchCode);
		String result="";
		try{
			sql=getQuery(DBConstants.DIRECT_STATUS);
			result=(String)this.mytemplate.queryForObject(sql,new String[]{branchCode,applicationNo},String.class);
		}
		catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}	
		logger.info("getDirectStatus - Exit:"+ result);
		return result;
	}
	public String getbranchWiseCountry(String branchCode)
	{
		logger.info("getbranchWiseCountry - Enter: branchCode: "+branchCode);
		String result="";
		try{
			sql=getQuery(DBConstants.BRANCH_WISE_COUNTRY);
			result=(String)this.mytemplate.queryForObject(sql,new String[]{branchCode},String.class);
		}
		catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}	
		logger.info("getbranchWiseCountry - Exit:"+ result);
		return result;
	}
	public boolean getDubaiTradeStatus(String brokerCode,String branchCode)
	{
		logger.info("getDubaiTradeStatus - Enter: branchCode: "+branchCode);
		boolean dubaiTradeStaus=false;
		try{
			sql=getQuery("GET_DUBAITRADE_STATUS");
			String result=(String)this.mytemplate.queryForObject(sql,new String[]{brokerCode, branchCode}, String.class);
			if(result!=null && "Y".equals(result))
				dubaiTradeStaus=true;
		}
		catch (Exception e) {			
			//logger.debug("EXCEPTION @ { " + e + " }");
		}	
		logger.info("getDubaiTradeStatus - Exit:"+ dubaiTradeStaus);
		return dubaiTradeStaus;
	}

	public List<Map<String, Object>> lcSelectionList(String ocNo, String searchValue, String exact) {
		logger.info("lcSelection - Enter || ocNo==>"+ocNo+"		searchValue==>"+searchValue);
		List<Map<String, Object>> lcSelectionList=null;
		try{
			sql="SELECT OCLM.BANK_ID,OPBM.BANK_NAME, OCLM.LC_ID, OCLM.LC_NUMBER, to_CHAR(OCLM.LC_DATE,'DD/MM/YYYY') LC_DATE, OCLM.LC_AMOUNT, OCLM.LC_CURRENCY_ID, nvl(OCLM.LC_BALANCE_AMOUNT,0) LC_BALANCE_AMOUNT  FROM OPEN_COVER_LC_MASTER OCLM, OPEN_COVER_BANK_MASTER OPBM WHERE OPBM.BANK_ID=OCLM.BANK_ID  and OCLM.OPEN_COVER_NO = ? and OPBM.AMEND_ID=(select max(opbmm.AMEND_ID) from OPEN_COVER_BANK_MASTER opbmm where opbmm.BANK_ID=opbm.BANK_ID and opbmm.status='Y')";
			if("Y".equals(exact) && StringUtils.isNotBlank(searchValue))
				sql=sql+" and upper(OCLM.LC_NUMBER)=upper('"+searchValue+"') order by OCLM.LC_DATE desc";
			else if(StringUtils.isNotBlank(searchValue))
				sql=sql+" and upper(OCLM.LC_NUMBER) like upper('%"+searchValue+"%') order by OCLM.LC_DATE desc";
			logger.info("Query==>" + sql);
			lcSelectionList=this.mytemplate.queryForList(sql,new String[]{ocNo});
		}
		catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}
		return lcSelectionList;
	}

	public int lcNoExist(String lcNo, String ocNo) {
		logger.info("lcNoExist - Enter || lcNo==>"+lcNo);
		int lcCount=1;
		try{
			sql="select count(OPEN_COVER_NO) from OPEN_COVER_LC_MASTER OCLM where OCLM.lc_number=? and OCLM.OPEN_COVER_NO!=?";
			logger.info("Query==>" + sql);
			lcCount=this.mytemplate.queryForObject(sql, Integer.class ,new String[]{lcNo, ocNo});
		}catch (Exception e) {
			logger.debug("EXCEPTION @ { " + e + " }");
		}
		return lcCount;
	}
	public List<Map<String, Object>>getLCOCDetail(String branchCode, final LCMasterBean bean, String from){
		List <Map<String, Object>> ocDetail=null;
		try{
			Object obj[];
			sql=getQuery("GET_LCOC_DETAILS_LIST");
			if("add".equals(from)){
				sql+=" and LC_number=? and LC_ID=?";
				obj=new Object[]{branchCode,branchCode,branchCode, bean.getOpenCover(), bean.getLcNum(), bean.getLcId()};
			}else 
				obj=new Object[]{branchCode,branchCode,branchCode, bean.getOpenCover()};
			logger.info("Query===>" + sql);
			for(Object a:obj)
				logger.info("args===>" + a);
			ocDetail=this.mytemplate.queryForList(sql,obj);
		}catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}
		return ocDetail;
	}
	public int checkValidPromotionalCode(String promotionalCode, String branchCode) {
		logger.info("checkValidPromotionalCode - Enter || promotionalCode==>"+promotionalCode+"		branchCode==>"+branchCode);
		int validCount=0;
		try{
			sql=getQuery("CHECK_VALID_PROMOTIONAL_CODE");
			logger.info("Query==>" + sql);
			validCount=this.mytemplate.queryForObject(sql, Integer.class ,new String[]{promotionalCode, branchCode});
		}catch (Exception e) {
			logger.debug("EXCEPTION @ { " + e + " }");
		}
		return validCount;
	}

	public void updateWSMarineQuote(String[] args) {
		logger.info("updateWSMarineQuote - Enter");
		try{
			sql=getQuery("UPD_WS_MARINE_QUOTE");
			logger.info("Query==>" + sql);
			logger.info("Args==>"+StringUtils.join(args, ",  "));
			this.mytemplate.update(sql, args);
		}catch (Exception e) {
			e.printStackTrace();
			logger.debug("updateWSMarineQuote - EXCEPTION @ { " + e + " }");
		}
	}
	public List<Map<String, Object>> searchList(String applicationNo,String branchCode){
		List<Map<String, Object>> searchList=null;		
		String query=getQuery("SER_COMM_SHOW");
		logger.info("query "+query+">>>>"+applicationNo+" branchCode==> "+branchCode);
		try{
			searchList=this.mytemplate.queryForList(query,new String[]{branchCode,applicationNo});
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return searchList;
	}
	public String addCommodity(String applicationNo, String refNo, List<Map<String, Object>> commList, String branchCode) {
		logger.info("addCommodity - Enter || applicationNo: "+applicationNo);
		Map<String, Object> commInfo=null;
		try
		{
			if(commList!=null && commList.size()>0)
			{
				String sql1=getQuery(DBConstants.COMMODITY_DELETE);
				sql=getQuery(DBConstants.COMMODITY_ADD);
				for (int i = 0; i < commList.size(); i++) {
					commInfo=(Map<String, Object>)commList.get(i);
					this.mytemplate.update(sql1, new String[]{applicationNo,commInfo.get("COMMODITY_ID").toString()});
					Object[]  args = new Object[]{
							commInfo.get("COMMODITY_ID"),
							commInfo.get("COMMODITY_NAME"), 
							commInfo.get("SUM_INSURED"),
							commInfo.get("SUPPLIER_NAME"),
							commInfo.get("PACKAGE_DESC")==null?"":commInfo.get("PACKAGE_DESC"),
							commInfo.get("INVOICE_NUMBER"),
							commInfo.get("INVOICE_DATE"),
							commInfo.get("FRAGILE"),refNo,applicationNo,
							commInfo.get("COMMODITY_GROUP_ID")
					};
					logger.info("Args==> " + StringUtils.join(args,","));
					this.mytemplate.update(sql, args);
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			logger.debug("Exception @ "+e);
		}
		
		logger.info("addCommodity - Exit");
		return "";	
	}
	public String deleteCommodity(String applicationNo,String  commodityCode)
	{
		logger.info("deleteCommodity - Enter || applicationNo: "+applicationNo +commodityCode);
		try
		{
			sql=getQuery(DBConstants.COMMODITY_DELETE);
			this.mytemplate.update(sql, new String[]{applicationNo,commodityCode});
		}catch(Exception e)
		{
			logger.debug("Exception @ "+e);
		}
		
		logger.info("deleteCommodity - Exit");
		return "";	
	}
	public int getCommodityExist(String commodityId, String applicationno){
		logger.info("getapplicationno - Enter: commodityId: "+commodityId);
		logger.info("getapplicationno - Enter: applicationno: "+applicationno);
		logger.info("Enter into getCommodityExist()");
	 	int count=0;
	 	try{
	 		sql=getQuery("COMMODITY_EXIST");
	 		logger.info("query =>"+sql);
	        count=this.mytemplate.queryForObject(sql, Integer.class ,new String[]{commodityId,applicationno});
	 		}
	        catch(Exception e){
	     		logger.info("Exception in getUserInfo()"+e);
	     	}
	     	logger.info("Exit from getCommodityExist()");
	     	return count;
		}
	public String getFragile(String category,String appNo) {
		logger.info("getFragile() Enter ");
		String fragile="";
		try{
			sql="SELECT   Commodity_Name_Desc, Fragile FROM   Open_Cover_Commodity_Master Occm,Open_Cover_Position_Master Ocpm WHERE Occm.Proposal_No = Ocpm.Proposal_No AND Occm.Amend_Id = (SELECT   MAX (Amend_Id) FROM   Open_Cover_Commodity_Master Oc WHERE   Oc.Proposal_No = Occm.Proposal_No)  AND Ocpm.Status = 'P' AND Ocpm.Open_cover_No=? AND occm.commodity_id =?";
			Object args[]={appNo,category};
			logger.info("Query >>"+sql);
			logger.info("args>>>>"+StringUtils.join(args,","));
			Map result = this.mytemplate.queryForMap(sql,args);
			fragile=result.get("FRAGILE").toString();
		}catch (Exception e) {
			e.printStackTrace();
			logger.info("getFragile() Exception "+e);	
		}
		logger.info("getFragile() Exit ");
		return fragile;
	}
	public double getSaleTermValue(String saleTermPercent, String branchCode) {
		double result = 0;
		try {
			String query = getQuery("GET_SALETERM_VAL");
			String res = (String) this.mytemplate.queryForObject(query, new Object[]{saleTermPercent,branchCode},String.class);
			result = StringUtils.isBlank(res)?0:Double.valueOf(res);
		}
		catch(Exception exception) {
			logger.debug("getSaleTermValue()==> " + exception);
		}
		return result;
	}
	public double getToleranceValue(String tolerance, String branchCode) {
		double result = 0;
		try {
			String query = getQuery("GET_TOLERANCE_PERCENT");
			String res = (String) this.mytemplate.queryForObject(query, new Object[]{tolerance,branchCode},String.class);
			result = StringUtils.isBlank(res)?0:Double.valueOf(res);
		}
		catch(Exception exception) {
			logger.debug("getToleranceValue()==> " + exception);
		}
		return result;
	}
	public void setOpenCustomerInfo(QuotationAction bean, String openCoverNo) {
		try {
			String query = getQuery("GET_OPENCUSTOMER_INFO");
			Map<String, Object> resultMap = this.mytemplate.queryForMap(query, new Object[]{openCoverNo});
			bean.setTitle(resultMap.get("TITLE")==null?"":resultMap.get("TITLE").toString());
			bean.setAddress1(resultMap.get("ADDRESS1")==null?"":resultMap.get("ADDRESS1").toString());
			bean.setAddress2(resultMap.get("ADDRESS2")==null?"":resultMap.get("ADDRESS2").toString());
			bean.setMobileNo(resultMap.get("MOBILE")==null?"":resultMap.get("MOBILE").toString());
			bean.setCity(resultMap.get("EMIRATE")==null?"":resultMap.get("EMIRATE").toString());
			bean.setPoBox(resultMap.get("POBOX")==null?"":resultMap.get("POBOX").toString());
			bean.setCustomerName(resultMap.get("FIRST_NAME")==null?"":resultMap.get("FIRST_NAME").toString());
			bean.setCoreAppCode(resultMap.get("MISSIPPI_CUSTOMER_CODE")==null?"":resultMap.get("MISSIPPI_CUSTOMER_CODE").toString());
			bean.setCustomerId(resultMap.get("CUSTOMER_ID")==null?"":resultMap.get("CUSTOMER_ID").toString());
			bean.setEmail(resultMap.get("EMAIL")==null?"":resultMap.get("EMAIL").toString());
			bean.setCustArNo(resultMap.get("CUST_AR_NO")==null?"":resultMap.get("CUST_AR_NO").toString());
			bean.setCustVatRegNo(resultMap.get("VAT_REG_NO")==null?"":resultMap.get("VAT_REG_NO").toString());
		}
		catch(Exception exception) {
			logger.debug("setOpenCustomerInfo()==> " + exception);
		}
	}
	public List<Map<String, Object>> goodsCategoryGroupList() {
		logger.info("Enter into goodsCategoryGroupList()");
		List<Map<String, Object>> list = new ArrayList<>();
		try {
			String query = getQuery("GET_CATEGORY_GROUP_LIST");
			logger.info("sql || "+query);
			list = this.mytemplate.queryForList(query);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}