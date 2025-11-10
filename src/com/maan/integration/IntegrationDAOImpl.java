package com.maan.integration;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;


import com.maan.common.MyJdbcTemplate;
import com.maan.common.util.StringUtil;


public class IntegrationDAOImpl extends MyJdbcTemplate implements IntegrationDAO {
	/*public Object[] constantList(String rsaCode,List<Map<String,Object>> records){
		Object[] result=null;
		try{
			String query="select DETAIL_NAME,REMARKS from constant_detail where CATEGORY_ID = 156 AND STATUS = 'Y' and RSACODE='"+rsaCode+"' ORDER BY PERCENT";
			List<Map<String,Object>> constList = this.mytemplate.queryForList(query);
			Object[] xmlKey=null;
			Object[] dbKey=null;
			if(constList!=null && constList.size()>0){
				xmlKey=new Object[constList.size()];
				dbKey=new Object[constList.size()];
				for(int i=0;i<constList.size();i++){
					Map<String, Object> record = constList.get(i);
					xmlKey[i]=record.get("DETAIL_NAME")==null?"":record.get("DETAIL_NAME");
					dbKey[i]=record.get("REMARKS")==null?"":record.get("REMARKS");
				}
			}
			logger.info("DBKey Length"+dbKey.length);
			if(records!=null && records.size()>0 && dbKey.length>0){
				result=new Object[dbKey.length];
				Map<String, Object> record = records.get(0);				
				for(int i=0;i<dbKey.length;i++){
					result[i]=record.get(dbKey[i]);
				}
			}else{
				if(dbKey.length>0){
					result=new Object[dbKey.length];
					for(int i=0;i<dbKey.length;i++){
						result[i]="";
					}
				}
			}
			removeNull(result);
			logger.info("Result Length"+result.length);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}*/

	public Object[] constantList(String rsaCode,Map<String,Object> records){
		Object[] result=null;
		try{
			String query="select DETAIL_NAME,REMARKS from INTEGRATION_TAG_DETAIL where CATEGORY_ID = 156 AND STATUS = 'Y' and RSACODE='"+rsaCode+"' ORDER BY TAG_ORDER";
			List<Map<String,Object>> constList = this.mytemplate.queryForList(query);
			Object[] xmlKey=null;
			Object[] dbKey=null;
			if(constList!=null && constList.size()>0){
				xmlKey=new Object[constList.size()];
				dbKey=new Object[constList.size()];
				for(int i=0;i<constList.size();i++){
					Map<String, Object> record = constList.get(i);
					xmlKey[i]=record.get("DETAIL_NAME")==null?"":record.get("DETAIL_NAME");
					dbKey[i]=record.get("REMARKS")==null?"":record.get("REMARKS");
				}
			}
			//logger.info("DBKey Length"+dbKey.length);
			if(records!=null && records.size()>0 && dbKey.length>0){
				result=new Object[dbKey.length];
				Map<String, Object> record = records;			
				for(int i=0;i<dbKey.length;i++){
					result[i]=record.get(dbKey[i]);
				}
			}else{
				if(dbKey.length>0){
					result=new Object[dbKey.length];
					for(int i=0;i<dbKey.length;i++){
						result[i]="";
					}
				}
			}
			removeNull(result);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	private void removeNull(Object []args) {
		for(int i=0;i<args.length;i++) {
			if(args[i]==null)
				args[i]="";
		}
	}
	public List<Map<String,Object>> getCustomerInfo(String policyNo){
		List<Map<String,Object>> result=null;
		try{
			String query="";
			query="SELECT APPLICATION_ID,CUSTOMER_ID,(TITLE ||'.'||FIRST_NAME ||' '|| LAST_NAME)FIRST_NAME,TO_CHAR(DOB,'DD-MON-YYYY')DOB,GENDER,TELEPHONE,MOBILE,EMAIL,ADDRESS1,ADDRESS2,OCCUPATION,POBOX,COUNTRY,EMIRATE,TO_CHAR(ENTRY_DATE,'DD-MON-YYYY HH24:MI')ENTRY_DATE,LOGIN_ID,AC_EXECUTIVE_ID,AGENCY_CODE,COMPANY_NAME,MISSIPPI_CUSTOMER_CODE,CLIENT_CUSTOMER_ID,CUST_NAME,STATE  FROM PERSONAL_INFO P WHERE P.CUSTOMER_ID=(SELECT CUSTOMER_ID FROM POSITION_MASTER WHERE POLICY_NO=?)";
			result = this.mytemplate.queryForList(query,new Object[]{policyNo});
			//result=constantList("CUSTOMER",result);
		}catch (Exception e) {
			e.printStackTrace(); 
		}
		return result;
	}
	public List<Map<String,Object>> getUserInfo(String policyNo){
		List<Map<String,Object>> result=null;
		try{
			String query="";
			query="select  LOGIN_ID , USERNAME  FIRST_NAME from login_master  where login_id = (select decode(APPLICATION_ID ,'1',login_id, APPLICATION_ID)  from  position_master where POLICY_NO=?)";
			result = this.mytemplate.queryForList(query,new Object[]{policyNo});
			//result=constantList("CUSTOMER",result);
		}catch (Exception e) {
			e.printStackTrace(); 
		}
		return result;
	}
	public List<Map<String,Object>> getCustomerInfoCust(String customerId){
		List<Map<String,Object>> result=null;
		try{
			String query="";
			query="SELECT APPLICATION_ID,CUSTOMER_ID,(TITLE ||'.'||FIRST_NAME ||' '|| LAST_NAME)FIRST_NAME,TO_CHAR(DOB,'DD-MON-YYYY')DOB,GENDER,TELEPHONE,MOBILE,EMAIL,ADDRESS1,ADDRESS2,OCCUPATION,POBOX,COUNTRY,EMIRATE,TO_CHAR(ENTRY_DATE,'DD-MON-YYYY HH24:MI')ENTRY_DATE,LOGIN_ID,AC_EXECUTIVE_ID,AGENCY_CODE,COMPANY_NAME,MISSIPPI_CUSTOMER_CODE,CLIENT_CUSTOMER_ID,CUST_NAME,STATE  FROM PERSONAL_INFO P WHERE P.CUSTOMER_ID=(SELECT CUSTOMER_ID FROM POSITION_MASTER WHERE QUOTE_NO=?)";
			result = this.mytemplate.queryForList(query,new Object[]{customerId});
			//result=constantList("CUSTOMER",result);
		}catch (Exception e) {
			e.printStackTrace(); 
		}
		return result;
	}
	public List<Map<String,Object>> getPolicyTagInfo(String policyNo){
		List<Map<String,Object>> result=null;
		try{
			String query="";
			query=getQuery("GET_INTG_DETAIL");
			//query="select PIFT_TRAN_SYS_ID,PIFT_POL_NO,PIFT_SC_CODE,PIFT_PDS_CODE,PIFT_LEVEL,PIFT_FLEX_FIELD_1,PIFT_FLEX_FIELD_2,PIFT_FLEX_FIELD_3,TO_CHAR(TO_DATE(PIFT_FLEX_FIELD_4,'DD/MM/YYYY'),'DD-MON-YYYY') PIFT_FLEX_FIELD_4,PIFT_FLEX_FIELD_5,PIFT_FLEX_FIELD_6,PIFT_FLEX_FIELD_7,PIFT_FLEX_FIELD_8,PIFT_FLEX_FIELD_9,TO_CHAR(TO_DATE(substr(PIFT_FLEX_FIELD_10,1,10),'DD/MM/YYYY'),'DD-MON-YYYY') PIFT_FLEX_FIELD_10,TO_CHAR(TO_DATE(substr(PIFT_FLEX_FIELD_11,1,10),'DD/MM/YYYY'),'DD-MON-YYYY') PIFT_FLEX_FIELD_11,PIFT_FLEX_FIELD_12,PIFT_FLEX_FIELD_13,PIFT_FLEX_FIELD_14,PIFT_FLEX_FIELD_15,PIFT_FLEX_FIELD_16,PIFT_FLEX_FIELD_17,PIFT_FLEX_FIELD_18,PIFT_FLEX_FIELD_19,PIFT_FLEX_FIELD_20,PIFT_FLEX_FIELD_21,PIFT_FLEX_FIELD_22,PIFT_FLEX_FIELD_23,PIFT_FLEX_FIELD_24,PIFT_FLEX_FIELD_25,PIFT_FLEX_FIELD_26,PIFT_FLEX_FIELD_27,PIFT_FLEX_FIELD_28,PIFT_FLEX_FIELD_29,PIFT_FLEX_FIELD_30,PIFT_STATUS,PIFT_ERROR_MESSAGE from pt_intg_flex_tran where PIFT_LEVEL='P' and PIFT_POL_NO = ?";
			result = this.mytemplate.queryForList(query,new Object[]{"P",policyNo,policyNo});
			//result=constantList("POLICY",result);
		}catch (Exception e) {
			e.printStackTrace(); 
		}
		return result;
	}	
	public List<Map<String,Object>> getRiskTag(String policyNo) {
		List<Map<String,Object>>  result=null;
		try{
			String query="";
			query=getQuery("GET_INTG_DETAIL");
			//query="select PIFT_TRAN_SYS_ID,PIFT_POL_NO,PIFT_SC_CODE,PIFT_PDS_CODE,PIFT_LEVEL,PIFT_FLEX_FIELD_1,PIFT_FLEX_FIELD_2,PIFT_FLEX_FIELD_3,PIFT_FLEX_FIELD_4,PIFT_FLEX_FIELD_5,PIFT_FLEX_FIELD_6,PIFT_FLEX_FIELD_7,TO_CHAR(TO_DATE(PIFT_FLEX_FIELD_8,'DD/MM/YYYY'),'DD-MON-YYYY') PIFT_FLEX_FIELD_8,TO_CHAR(TO_DATE(PIFT_FLEX_FIELD_9,'DD/MM/YYYY'),'DD-MON-YYYY') PIFT_FLEX_FIELD_9,PIFT_FLEX_FIELD_10,PIFT_FLEX_FIELD_11,PIFT_FLEX_FIELD_12,PIFT_FLEX_FIELD_13,PIFT_FLEX_FIELD_14,PIFT_FLEX_FIELD_15,PIFT_FLEX_FIELD_16,PIFT_FLEX_FIELD_17,PIFT_FLEX_FIELD_18,PIFT_FLEX_FIELD_19,PIFT_FLEX_FIELD_20,PIFT_FLEX_FIELD_21,PIFT_FLEX_FIELD_22,PIFT_FLEX_FIELD_23,PIFT_FLEX_FIELD_24,PIFT_FLEX_FIELD_25,PIFT_FLEX_FIELD_26,PIFT_FLEX_FIELD_27,PIFT_FLEX_FIELD_28,PIFT_FLEX_FIELD_29,PIFT_FLEX_FIELD_30,PIFT_STATUS,PIFT_ERROR_MESSAGE from pt_intg_flex_tran where PIFT_LEVEL='R' and PIFT_POL_NO = ?";
			result = this.mytemplate.queryForList(query,new Object[]{"R",policyNo,policyNo});
			//result=constantList("RISK",result);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public List<Map<String,Object>> getRiskVesselTag(String policyNo) {
		List<Map<String,Object>> result=null;
		String query="";
		try{

			query=getQuery("GET_INTG_DETAIL");
			//String query="select PIFT_TRAN_SYS_ID,PIFT_POL_NO,PIFT_SC_CODE,PIFT_PDS_CODE,PIFT_LEVEL,PIFT_FLEX_FIELD_1,PIFT_FLEX_FIELD_2,PIFT_FLEX_FIELD_3,PIFT_FLEX_FIELD_4,TO_CHAR(TO_DATE(substr(PIFT_FLEX_FIELD_5,1,10),'DD/MM/YYYY'),'DD-MON-YYYY') PIFT_FLEX_FIELD_5,PIFT_FLEX_FIELD_6,PIFT_FLEX_FIELD_7,TO_CHAR(TO_DATE(substr(PIFT_FLEX_FIELD_8,1,10),'DD/MM/YYYY'),'DD-MON-YYYY') PIFT_FLEX_FIELD_8,PIFT_FLEX_FIELD_9,PIFT_FLEX_FIELD_10,PIFT_FLEX_FIELD_11,PIFT_FLEX_FIELD_12,PIFT_FLEX_FIELD_13,PIFT_FLEX_FIELD_14,PIFT_FLEX_FIELD_15,PIFT_FLEX_FIELD_16,PIFT_FLEX_FIELD_17,PIFT_FLEX_FIELD_18,PIFT_FLEX_FIELD_19,PIFT_FLEX_FIELD_20,PIFT_FLEX_FIELD_21,PIFT_FLEX_FIELD_22,PIFT_FLEX_FIELD_23,PIFT_FLEX_FIELD_24,PIFT_FLEX_FIELD_25,PIFT_FLEX_FIELD_26,PIFT_FLEX_FIELD_27,PIFT_FLEX_FIELD_28,PIFT_FLEX_FIELD_29,PIFT_FLEX_FIELD_30,PIFT_STATUS,PIFT_ERROR_MESSAGE from pt_intg_flex_tran where PIFT_LEVEL='V' and PIFT_POL_NO = ?";			
			result = this.mytemplate.queryForList(query,new Object[]{"V",policyNo,policyNo});
			//result=constantList("VESSEL",result);
		}catch (Exception e) {
			e.printStackTrace(); 
		}
		return result;
	}
	public List<Map<String,Object>>  getRiskSmiTagInfo(String policyNo) {//Risk Level Smi
		List<Map<String,Object>> result=null;
		String query ="";
		try{
			query=getQuery("GET_INTG_DETAIL");
			result = this.mytemplate.queryForList(query,new Object[]{"C",policyNo,policyNo});
			//	result=constantList("SMIINFO",result);
		}catch (Exception e) {
			e.printStackTrace(); 
		}
		return result;
	}
	public List<Map<String,Object>> getRiskCoverTagInfo(String policyNo){
		List<Map<String,Object>> result=null;
		String query="";
		try{
			query=getQuery("GET_INTG_DETAIL");
			result = this.mytemplate.queryForList(query,new Object[]{"S",policyNo,policyNo});
			//result=constantList("RISKCOVER",result);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public List<Map<String,Object>> getRiskDedtTag(String policyNo) {//Risk Level
		List<Map<String,Object>> result=null;
		String query="";
		try{
			query=getQuery("GET_INTG_DETAIL");
			//String query="select PIFT_TRAN_SYS_ID,PIFT_POL_NO,PIFT_SC_CODE,PIFT_PDS_CODE,PIFT_LEVEL,PIFT_FLEX_FIELD_1,PIFT_FLEX_FIELD_2,PIFT_FLEX_FIELD_3,PIFT_FLEX_FIELD_4,PIFT_FLEX_FIELD_5,PIFT_FLEX_FIELD_6,PIFT_FLEX_FIELD_7,PIFT_FLEX_FIELD_8,PIFT_FLEX_FIELD_9,PIFT_FLEX_FIELD_10,PIFT_FLEX_FIELD_11,PIFT_FLEX_FIELD_12,PIFT_FLEX_FIELD_13,PIFT_FLEX_FIELD_14,PIFT_FLEX_FIELD_15,PIFT_FLEX_FIELD_16,PIFT_FLEX_FIELD_17,PIFT_FLEX_FIELD_18,PIFT_FLEX_FIELD_19,PIFT_FLEX_FIELD_20,PIFT_FLEX_FIELD_21,PIFT_FLEX_FIELD_22,PIFT_FLEX_FIELD_23,PIFT_FLEX_FIELD_24,PIFT_FLEX_FIELD_25,PIFT_FLEX_FIELD_26,PIFT_FLEX_FIELD_27,PIFT_FLEX_FIELD_28,PIFT_FLEX_FIELD_29,PIFT_FLEX_FIELD_30,PIFT_STATUS,PIFT_ERROR_MESSAGE from pt_intg_flex_tran where PIFT_LEVEL='E' and PIFT_POL_NO = ?";
			result = this.mytemplate.queryForList(query,new Object[]{"RE",policyNo,policyNo});
			//result=constantList("RISKDEDUCTIBLE",result);
		}catch (Exception e) {
			e.printStackTrace(); 
		}
		return result;
	}
	public List<Map<String,Object>> getRiskDisTag(String policyNo) {//Risk Discount
		List<Map<String,Object>> result=null;
		String query="";
		try{
			query=getQuery("GET_INTG_DETAIL");
			result = this.mytemplate.queryForList(query,new Object[]{"RED",policyNo,policyNo});
			//result=constantList("RISKDIS",result);
		}catch (Exception e) {
			e.printStackTrace(); 
		}
		return result;
	}
	public List<Map<String,Object>> getRiskLoadTag(String policyNo) {//Risk Level
		List<Map<String,Object>> result=null;
		String query="";
		try{
			query=getQuery("GET_INTG_DETAIL");
			result = this.mytemplate.queryForList(query,new Object[]{"RESD",policyNo,policyNo});
			//result=constantList("RISKLOAD",result);
		}catch (Exception e) {
			e.printStackTrace(); 
		}
		return result;
	}
	public List<Map<String,Object>> getDeductibleTagInfo(String policyNo) {
		List<Map<String,Object>> result=null;
		String query="";
		try{
			query=getQuery("GET_INTG_DETAIL");
			result = this.mytemplate.queryForList(query,new Object[]{"E",policyNo,policyNo});
			//esult=constantList("DEDUCTIBLEINFO",result);
		}catch (Exception e) {
			e.printStackTrace(); 
		}
		return result;
	}

	public List<Map<String,Object>> getDiscountTagInfo(String policyNo) {
		List<Map<String,Object>> result=null;
		String query="";
		try{
			query=getQuery("GET_INTG_DETAIL");
			result = this.mytemplate.queryForList(query,new Object[]{"RD",policyNo,policyNo});
			//result=constantList("DISCOUNTINFO",result);
		}catch (Exception e) {
			e.printStackTrace(); 
		}
		return result;
	}

	public List<Map<String,Object>> getLoadingTagInfo(String policyNo) {
		List<Map<String,Object>> result=null;
		String query="";
		try{
			query=getQuery("GET_INTG_DETAIL");
			result = this.mytemplate.queryForList(query,new Object[]{"RSD",policyNo,policyNo});
			//result=constantList("LOADINGINFO",result);
		}catch (Exception e) {
			e.printStackTrace(); 
		}
		return result;
	}
	public List<Map<String,Object>>  getConditionTagInfo(String policyNo) {
		List<Map<String,Object>> result=null;
		String query="";
		try{
			query=getQuery("GET_INTG_DETAIL");
			result = this.mytemplate.queryForList(query,new Object[]{"COND",policyNo,policyNo});
			//result=constantList("CONDITIONINFO",result);
		}catch (Exception e) {
			e.printStackTrace(); 
		}
		return result;
	}
	public List<Map<String,Object>> getChargesTagInfo(String policyNo) {
		List<Map<String,Object>> result=null;
		String query="";
		try{
			query=getQuery("GET_INTG_DETAIL");
			result = this.mytemplate.queryForList(query,new Object[]{"CH",policyNo,policyNo});
			//result=constantList("CHARGESINFO",result);
		}catch (Exception e) {
			e.printStackTrace(); 
		}
		return result;
	}
	public List<Map<String,Object>> getBrokersTagInfo(String policyNo) {
		List<Map<String,Object>> result=null;
		String query="";
		try{
			query=getQuery("GET_INTG_DETAIL");
			result = this.mytemplate.queryForList(query,new Object[]{"B",policyNo,policyNo});
			//result=constantList("BROKERCOMM",result);
		}catch (Exception e) {
			e.printStackTrace(); 
		}
		return result;
	}
	public List<Map<String,Object>> getMarDirFacsharedtlTag(String policyNo) {
		List<Map<String,Object>> result=new ArrayList<Map<String,Object>>();
		String query="";
		try{
			query=getQuery("GET_INTG_DETAIL");
			result = this.mytemplate.queryForList(query,new Object[]{"F",policyNo,policyNo});
			//result=constantList("FACSHAREDTL",result);
		}catch (Exception e) {
			e.printStackTrace(); 
		}
		return result;
	}
	public List<Map<String,Object>> getMarDirFacparDtlsTag(String policyNo) {
		List<Map<String,Object>> result=new ArrayList<Map<String,Object>>();;
		String query="";
		try{
			query=getQuery("GET_INTG_DETAIL");
			//String query="select PIFT_TRAN_SYS_ID,PIFT_POL_NO,PIFT_SC_CODE,PIFT_PDS_CODE,PIFT_LEVEL,PIFT_FLEX_FIELD_1,PIFT_FLEX_FIELD_2,PIFT_FLEX_FIELD_3,PIFT_FLEX_FIELD_4,PIFT_FLEX_FIELD_5,PIFT_FLEX_FIELD_6,PIFT_FLEX_FIELD_7,PIFT_FLEX_FIELD_8,PIFT_FLEX_FIELD_9,PIFT_FLEX_FIELD_10,PIFT_FLEX_FIELD_11,PIFT_FLEX_FIELD_12,PIFT_FLEX_FIELD_13,PIFT_FLEX_FIELD_14,PIFT_FLEX_FIELD_15,PIFT_FLEX_FIELD_16,PIFT_FLEX_FIELD_17,PIFT_FLEX_FIELD_18,PIFT_FLEX_FIELD_19,PIFT_FLEX_FIELD_20,PIFT_FLEX_FIELD_21,PIFT_FLEX_FIELD_22,PIFT_FLEX_FIELD_23,PIFT_FLEX_FIELD_24,PIFT_FLEX_FIELD_25,PIFT_FLEX_FIELD_26,PIFT_FLEX_FIELD_27,PIFT_FLEX_FIELD_28,PIFT_FLEX_FIELD_29,PIFT_FLEX_FIELD_30,PIFT_STATUS,PIFT_ERROR_MESSAGE from pt_intg_flex_tran where PIFT_LEVEL='CH' and PIFT_POL_NO =?";
			result = this.mytemplate.queryForList(query,new Object[]{"F",policyNo,policyNo});
			//result=constantList("FACPARTDTLS",result);
		}catch (Exception e) {
			e.printStackTrace(); 
		}
		return result;
	}
	public List<Map<String,Object>> getMarDirFacpartCommTag(String policyNo) {
		List<Map<String,Object>> result=new ArrayList<Map<String,Object>>();;
		String query="";
		try{
			query=getQuery("GET_INTG_DETAIL");
			result = this.mytemplate.queryForList(query,new Object[]{"F",policyNo,policyNo});
			//result=constantList("FACPARTCOMM",result);
		}catch (Exception e) {
			e.printStackTrace(); 
		}
		return result;
	}
	public List<Map<String,Object>> getAccountTagInfo(String policyNo) {
		List<Map<String,Object>> result=null;
		String query="";
		try{
			query=getQuery("GET_INTG_DETAIL");
			result = this.mytemplate.queryForList(query,new Object[]{"A",policyNo,policyNo});
			//	result=constantList("ACCOUNTSINFO",records);
		}catch (Exception e) {
			e.printStackTrace(); 
		}
		return result;
	}
	
	public List<Map<String,Object>> getVatTagInfo(String policyNo) {
		List<Map<String,Object>> result=null;
		String query="";
		try{
			query=getQuery("GET_INTG_DETAIL");
			result = this.mytemplate.queryForList(query,new Object[]{"VAT",policyNo,policyNo});
			//	result=constantList("ACCOUNTSINFO",records);
		}catch (Exception e) {
			e.printStackTrace(); 
		}
		return result;
	}


	/**
	 * Endorsement Policy Tag
	 */
	public List<Map<String,Object>>  getEndorsePolicyTag(String policyNo, String tranId) {
		List<Map<String,Object>>  result=null;
		try{
			String query="";
			query=getQuery("GET_ENDT_INTG_DETAIL");
			result = this.mytemplate.queryForList(query,new Object[]{"P",policyNo,tranId});

		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	public List<Map<String,Object>> getEndtRiskTag(String policyNo, String tranId) {
		List<Map<String,Object>>  result=null;
		try{
			String query="";
			query=getQuery("GET_ENDT_INTG_DETAIL");
			result = this.mytemplate.queryForList(query,new Object[]{"R",policyNo,tranId});
			//result=constantList("ENDTRISK",result);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public List<Map<String,Object>> getEndtRiskVesselTag(String policyNo, String tranId) {
		List<Map<String,Object>> result=null;
		String query="";
		try{

			query=getQuery("GET_ENDT_INTG_DETAIL");
			result = this.mytemplate.queryForList(query,new Object[]{"V",policyNo,tranId});
			//result=constantList("ENDTVESSEL",result);
		}catch (Exception e) {
			e.printStackTrace(); 
		}
		return result;
	}
	public List<Map<String,Object>>  getEndtRiskSmiTagInfo(String policyNo, String tranId) {//Risk Level Smi
		List<Map<String,Object>> result=null;
		String query ="";
		try{
			query=getQuery("GET_ENDT_INTG_DETAIL");
			result = this.mytemplate.queryForList(query,new Object[]{"C",policyNo,tranId});
			//	result=constantList("ENDTSMIINFO",result);
		}catch (Exception e) {
			e.printStackTrace(); 
		}
		return result;
	}
	public List<Map<String,Object>> getEndtRiskCoverTagInfo(String policyNo, String tranId){
		List<Map<String,Object>> result=null;
		String query="";
		try{
			query=getQuery("GET_ENDT_INTG_DETAIL");
			result = this.mytemplate.queryForList(query,new Object[]{"S",policyNo,tranId});
			//result=constantList("ENDTRISKCVR",result);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public List<Map<String,Object>> getEndtRiskDedtTag(String policyNo, String tranId) {//Risk Level
		List<Map<String,Object>> result=null;
		String query="";
		try{
			query=getQuery("GET_ENDT_INTG_DETAIL");
			result = this.mytemplate.queryForList(query,new Object[]{"E",policyNo,tranId});
			//result=constantList("ENDTRISKDED",result);
		}catch (Exception e) {
			e.printStackTrace(); 
		}
		return result;
	}
	public List<Map<String,Object>> getEndtRiskDisTag(String policyNo, String tranId) {//Risk Discount
		List<Map<String,Object>> result=null;
		String query="";
		try{
			query=getQuery("GET_ENDT_INTG_DETAIL");
			result = this.mytemplate.queryForList(query,new Object[]{"RD",policyNo,tranId});
			//result=constantList("ENDTRISKDIS",result);
		}catch (Exception e) {
			e.printStackTrace(); 
		}
		return result;
	}
	public List<Map<String,Object>> getEndtRiskLoadTag(String policyNo, String tranId) {//Risk Level
		List<Map<String,Object>> result=null;
		String query="";
		try{
			query=getQuery("GET_ENDT_INTG_DETAIL");
			result = this.mytemplate.queryForList(query,new Object[]{"RSD",policyNo,tranId});
			//result=constantList("ENDTRISKLOAD",result);
		}catch (Exception e) {
			e.printStackTrace(); 
		}
		return result;
	}
	public List<Map<String,Object>> getEndtDeductibleTagInfo(String policyNo, String tranId) {
		List<Map<String,Object>> result=null;
		String query="";
		try{
			query=getQuery("GET_ENDT_INTG_DETAIL");
			result = this.mytemplate.queryForList(query,new Object[]{"E",policyNo,tranId});
			//esult=constantList("ENDTDEDUCTIBLE",result);
		}catch (Exception e) {
			e.printStackTrace(); 
		}
		return result;
	}

	public List<Map<String,Object>> getEndtDiscountTagInfo(String policyNo, String tranId) {
		List<Map<String,Object>> result=null;
		String query="";
		try{
			query=getQuery("GET_ENDT_INTG_DETAIL");
			result = this.mytemplate.queryForList(query,new Object[]{"RD",policyNo,tranId});
			//result=constantList("ENDTDISCOUNT",result);
		}catch (Exception e) {
			e.printStackTrace(); 
		}
		return result;
	}

	public List<Map<String,Object>> getEndtLoadingTagInfo(String policyNo, String tranId) {
		List<Map<String,Object>> result=null;
		String query="";
		try{
			query=getQuery("GET_ENDT_INTG_DETAIL");
			result = this.mytemplate.queryForList(query,new Object[]{"RSD",policyNo,tranId});
			//result=constantList("ENDTLOADINFO",result);
		}catch (Exception e) {
			e.printStackTrace(); 
		}
		return result;
	}
	public List<Map<String,Object>>  getEndtConditionTagInfo(String policyNo, String tranId) {
		List<Map<String,Object>> result=null;
		String query="";
		try{
			query=getQuery("GET_ENDT_INTG_DETAIL");
			result = this.mytemplate.queryForList(query,new Object[]{"COND",policyNo,tranId});
			//result=constantList("ENDTCONDINFO",result);
		}catch (Exception e) {
			e.printStackTrace(); 
		}
		return result;
	}
	public List<Map<String,Object>> getEndtChargesTagInfo(String policyNo, String tranId) {
		List<Map<String,Object>> result=null;
		String query="";
		try{
			query=getQuery("GET_ENDT_INTG_DETAIL");
			result = this.mytemplate.queryForList(query,new Object[]{"CH",policyNo,tranId});
			//result=constantList("ENDTCHARGES",result);
		}catch (Exception e) {
			e.printStackTrace(); 
		}
		return result;
	}
	public List<Map<String,Object>> getEndtBrokersTagInfo(String policyNo, String tranId) {
		List<Map<String,Object>> result=null;
		String query="";
		try{
			query=getQuery("GET_ENDT_INTG_DETAIL");
			result = this.mytemplate.queryForList(query,new Object[]{"B",policyNo,tranId});
			//result=constantList("ENDTBROKER",result);
		}catch (Exception e) {
			e.printStackTrace(); 
		}
		return result;
	}
	public String getMaxTranId(String policyNo)
	{
		String query="";
		String result="";
		try{
			query="select nvl(max(PIFT_TRAN_SYS_ID),0) from pt_intg_flex_tran where PIFT_POL_NO =?";
			result = (String) this.mytemplate.queryForObject(query,new Object[]{policyNo},String.class);		
			}
		catch (Exception e) {
			e.printStackTrace(); 
		}
		return result;
	}
	public List<Map<String, Object>> getEndtAccTagInfo(String policyNo,String tranId) {
		List<Map<String,Object>> result=null;
		String query="";
		try{
			query=getQuery("GET_ENDT_INTG_DETAIL");
			result = this.mytemplate.queryForList(query,new Object[]{"A",policyNo,tranId});
		}catch (Exception e) {
			e.printStackTrace(); 
		}
		return result;

	}
	
	public List<Map<String, Object>> getEndtVatTagInfo(String policyNo,String tranId) {
		List<Map<String,Object>> result=null;
		String query="";
		try{
			query=getQuery("GET_ENDT_INTG_DETAIL");
			result = this.mytemplate.queryForList(query,new Object[]{"VAT",policyNo,tranId});
		}catch (Exception e) {
			e.printStackTrace(); 
		}
		return result;

	}
	public void updateStatus(String policyNo,String status, String msg,String res) {
		String query="";
		try{
			query="update POSITION_MASTER set INTEGRATION_STATUS =(case ? when 'SUCCESS' THEN 'Y' WHEN 'FAILED' THEN  'F' ELSE 'F' END) ,INTEGRATION_ERROR =?,INTEGRATION_RES=? Where POLICY_NO =?";
			this.mytemplate.update(query,new Object[]{status,msg,res==null?"":res,policyNo});
		}catch (Exception e) {
			e.printStackTrace(); 
		}
	}
	public void updateXmlRequest(String policyNo, String xml, String type,String policyType) {
		try{
				this.mytemplate.update("CALL EIC_XML(?,?,?,?)", new String[]{policyNo, xml, type,policyType});

				}catch (Exception e) {
			e.printStackTrace(); 
		}
	}
	/*public List<Map<String, Object>> getCustomerVatInfo(String quoteNo) {
		// TODO Auto-generated method stub
		return null;
	}*/
	public List<Map<String, Object>> getCustomerInfoBroker(String customerId) {
		List<Map<String,Object>> result=null;
		try{
			String query="";
			query="SELECT LOG.LOGIN_ID LOGIN_ID,BCM.COMPANY_NAME FIRST_NAME,SYSDATE ENTRY_DATE, BCM.RSA_BROKER_CODE  MISSIPPI_CUSTOMER_CODE FROM BROKER_COMPANY_MASTER BCM,LOGIN_MASTER LOG,POSITION_MASTER POS WHERE   BCM.AGENCY_CODE=LOG.OA_CODE AND POS.QUOTE_NO=? AND LOG.LOGIN_ID=POS.LOGIN_ID";
			result = this.mytemplate.queryForList(query,new Object[]{customerId});
			//result=constantList("CUSTOMER",result);
		}catch (Exception e) {
			e.printStackTrace(); 
		}
		return result;
	}
}
