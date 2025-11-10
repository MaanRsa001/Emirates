package com.maan.quotation.dao;
import java.util.*;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import com.maan.common.DBConstants;
import com.maan.common.LogUtil;
import com.maan.common.MyJdbcTemplate;
import com.maan.common.exception.BaseException;
import com.maan.quotation.service.PremiumService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.LocalizedTextProvider;

public class DeclarationDAO extends MyJdbcTemplate{
	private static final Logger logger = LogUtil.getLogger(DeclarationDAO.class);
	
	String sql="";
	public List<Map<String, Object>> getDeclarationList(String option, String[] args) throws  BaseException{
		logger.info("getReportList - Enter || option:" + option+" args: "+StringUtils.join(args, ","));
		List<Map<String, Object>> list=null;
		try
		{
			if("T".equalsIgnoreCase(option)){
				sql=getQuery(DBConstants.REPORT_TRANSACTION);
			}else if("D".equalsIgnoreCase(option)){
				sql=getQuery(DBConstants.REPORT_DECLARATION);
			}else if("DT".equalsIgnoreCase(option)){
				sql=getQuery(DBConstants.REPORT_DECLARATION_TRANSACTION);
			}else if("MC".equalsIgnoreCase(option)){
				sql=getQuery(DBConstants.DECLARATION_QUOTE_INFO);
			}else if("DE".equals(option)){
				//sql="SELECT  A.APPLICATION_NO,A.QUOTE_NO, A.CUSTOMER_ID, TO_CHAR (A.ENTRY_DATE, 'DD/MM/YYYY') QUOTATION_DATE, TO_CHAR (A.EFFECTIVE_DATE, 'DD/MM/YYYY') VALIDITY_DATE, NVL (B.COMPANY_NAME, B.FIRST_NAME) CUSTOMER_NAME, A.LOGIN_ID, B.COMPANY_NAME, A.EFFECTIVE_DATE, NVL (M.OPEN_COVER_NO, '0'), (NVL (M.PREMIUM, 0) + NVL (M.EXCESS_PREMIUM, 0) + NVL (M.GOVT_TAX, 0) + NVL (M.EMERGENCY_FUND, 0 ) + NVL (M.POLICY_FEE, 0)) PREMIUM, B.EMAIL FROM POSITION_MASTER A, PERSONAL_INFO B, MARINE_DATA M WHERE A.LOGIN_ID =? AND (A.FREIGHT_STATUS NOT IN ('F', 'B') OR A.FREIGHT_STATUS IS NULL) AND A.EFFECTIVE_DATE > = SYSDATE  AND A.STATUS  = 'Y' AND (A.REMARKS NOT IN ('Admin', 'Referal', 'NORMAL_EXCESS_PRICE')) AND M.APPLICATION_NO  = A.APPLICATION_NO AND NVL (M.ADMIN_REFERRAL_STATUS, 'N') NOT IN ('Y') AND B.CUSTOMER_ID = A.CUSTOMER_ID AND A.PRODUCT_ID = ? AND A.APPLICATION_ID = NVL(?,'1') AND NVL(A.OPEN_COVER_NO,'0') like NVL(? ||'%','0') ORDER BY QUOTE_NO DESC";
				sql="SELECT A.QUOTE_NO, NVL (B.COMPANY_NAME, B.FIRST_NAME) FIRST_NAME ,M.TOTAL_SUM_INSURED TOTAL_SUM_INSURED,(NVL (M.PREMIUM, 0) + NVL (M.EXCESS_PREMIUM, 0) + NVL (M.GOVT_TAX, 0) +NVL (M.EMERGENCY_FUND, 0 ) + NVL (M.POLICY_FEE, 0)) PREMIUM,( select count(*) from WEBSERVICE_MARINE_QUOTE  WHERE APPLICATION_NO = M.APPLICATION_NO and TRANSACTION_ID is not null ) PACKAGE_DESCRIPTION FROM POSITION_MASTER A, PERSONAL_INFO B, MARINE_DATA M   WHERE A.LOGIN_ID =? AND (A.FREIGHT_STATUS NOT IN ('F', 'B') OR A.FREIGHT_STATUS IS NULL) AND A.EFFECTIVE_DATE > = SYSDATE AND A.STATUS  = 'Y' AND (A.REMARKS NOT IN ('Admin', 'Referal', 'NORMAL_EXCESS_PRICE')) AND M.APPLICATION_NO  = A.APPLICATION_NO AND NVL (M.ADMIN_REFERRAL_STATUS, 'N') NOT IN ('Y')      AND B.CUSTOMER_ID = A.CUSTOMER_ID AND A.PRODUCT_ID = ?  AND A.APPLICATION_ID = NVL(?,'1') AND NVL(A.OPEN_COVER_NO,'0') like NVL(? ||'%','0') ORDER BY QUOTE_NO DESC";
				}
			for (int i = 0; i < args.length; i++) {
				if(args[i]==null){
					args[i]="";
				}
			}
			list=this.mytemplate.queryForList(sql,args);			
		}
		catch (Exception e)
		{			
			logger.debug("EXCEPTION @ { " + e + " }");
		}		
		logger.info("Query=>" + sql);		
		logger.info("getReportList - Exit || Result: " + list.size());
				
		return list;
	}
	public List<Map<String, Object>> getDeclarationList(String option, String reqFrom, String[] args) throws  BaseException{
		logger.info("getReportList - Enter || option:" + option+" args: "+StringUtils.join(args, ","));
		List<Map<String, Object>> list=null;
		try
		{
			if("T".equalsIgnoreCase(option)){
				sql=getQuery(DBConstants.REPORT_TRANSACTION);
			}else if("D".equalsIgnoreCase(option)){
				if("result".equals(reqFrom)){
					if(args.length>5 && StringUtils.isNotBlank(args[4]) && StringUtils.isNotBlank(args[5]) && StringUtils.isNotBlank(args[6]))
						sql=getQuery("report.declaration.vessel.list");
					else if(args.length==5){
						sql=getQuery(DBConstants.REPORT_DECLARATION)+"  and WMQ.TRANSACTION_ID=?) group by TRANSACTION_ID,SUPPLIER_NAME, VESSEL_NAME order by TRANSACTION_ID, supplier_name";
					}else
						sql=getQuery(DBConstants.REPORT_DECLARATION)+") group by TRANSACTION_ID,SUPPLIER_NAME, VESSEL_NAME order by TRANSACTION_ID, supplier_name";
				}else if("declare".equals(reqFrom)){
					sql="SELECT PM.POLICY_NO, PM.QUOTE_NO, PI.FIRST_NAME, PM.LOGIN_ID,MD.TOTAL_SUM_INSURED,(NVL (MD. PREMIUM, 0) + NVL (MD.EXCESS_PREMIUM, 0)) PREMIUM,(SELECT CASE WHEN UPPER (SETTLING_AGENT_NAME) = 'OTHERS' THEN MPD.SETTLING_AGENT_NAME ELSE SETTLING_AGENT_NAME END FROM SETTLING_AGENT_MASTER WHERE SETTLING_AGENT_ID=MPD.SETTLING_AGENT_ID AND BRANCH_CODE=?) SETTLING_AGENT_NAME, ( SELECT COUNT ( * ) FROM MARINE_RESULT_DETAILS WHERE APPLICATION_NO = MD.APPLICATION_NO AND PACKAGE_DESCRIPTION IS NULL AND AMEND_ID = ( SELECT MAX (AMEND_ID) FROM MARINE_RESULT_DETAILS WHERE APPLICATION_NO = MD.APPLICATION_NO)) PACKAGE_DESCRIPTION FROM POSITION_MASTER PM, PERSONAL_INFO PI,MARINE_DATA MD, MARINE_POLICY_DETAILS MPD WHERE PM.OPEN_COVER_NO = ?  AND PM.STATUS = 'P' AND PM.REMARKS IN ('Normal', 'Admin') AND PI.CUSTOMER_ID = PM.CUSTOMER_ID AND PM.EFFECTIVE_DATE > ( SELECT SYSDATE FROM DUAL) AND PM.LOGIN_ID = ? AND PM.APPLICATION_ID = NVL(?,'1') AND PM.DISCOUNT_PREMIUM IS NULL AND (PM.CERTIFICATE_NO IS NULL AND PM.CERTIFICATE_DATE IS NULL) AND MD.APPLICATION_NO=PM.APPLICATION_NO AND MPD.QUOTE_NO=PM.QUOTE_NO and PM.POLICY_NO = ? ORDER BY PM.QUOTE_NO";
				}else{
					sql="SELECT DISTINCT TR.TRANSACTION_ID, TO_CHAR (TR.TRANSACTION_DATE, 'DD/MM/YYYY') TRN_DATE, TR.USER_NAME, TR.FILE_NAME, TR.TOTAL_NO_OF_RECORDS, TR.UPLOADED_COUNT, TR.PENDING_COUNT FROM XL_TRANSACTION_DETAILS TR  WHERE TR.OPENCOVER_POLICY_NUMBER = ? AND TR.transaction_id IS NOT NULL AND tr.issuer IS NULL ORDER BY TR.transaction_id DESC";
				}
			}else if("DT".equalsIgnoreCase(option)){
				sql=getQuery(DBConstants.REPORT_DECLARATION_TRANSACTION);
			}else if("MC".equalsIgnoreCase(option)){
				sql=getQuery(DBConstants.DECLARATION_QUOTE_INFO);
			}else if("DE".equals(option)){
				//sql="SELECT  A.APPLICATION_NO,A.QUOTE_NO, A.CUSTOMER_ID, TO_CHAR (A.ENTRY_DATE, 'DD/MM/YYYY') QUOTATION_DATE, TO_CHAR (A.EFFECTIVE_DATE, 'DD/MM/YYYY') VALIDITY_DATE, NVL (B.COMPANY_NAME, B.FIRST_NAME) CUSTOMER_NAME, A.LOGIN_ID, B.COMPANY_NAME, A.EFFECTIVE_DATE, NVL (M.OPEN_COVER_NO, '0'), (NVL (M.PREMIUM, 0) + NVL (M.EXCESS_PREMIUM, 0) + NVL (M.GOVT_TAX, 0) + NVL (M.EMERGENCY_FUND, 0 ) + NVL (M.POLICY_FEE, 0)) PREMIUM, B.EMAIL FROM POSITION_MASTER A, PERSONAL_INFO B, MARINE_DATA M WHERE A.LOGIN_ID =? AND (A.FREIGHT_STATUS NOT IN ('F', 'B') OR A.FREIGHT_STATUS IS NULL) AND A.EFFECTIVE_DATE > = SYSDATE  AND A.STATUS  = 'Y' AND (A.REMARKS NOT IN ('Admin', 'Referal', 'NORMAL_EXCESS_PRICE')) AND M.APPLICATION_NO  = A.APPLICATION_NO AND NVL (M.ADMIN_REFERRAL_STATUS, 'N') NOT IN ('Y') AND B.CUSTOMER_ID = A.CUSTOMER_ID AND A.PRODUCT_ID = ? AND A.APPLICATION_ID = NVL(?,'1') AND NVL(A.OPEN_COVER_NO,'0') like NVL(? ||'%','0') ORDER BY QUOTE_NO DESC";
				sql="SELECT A.QUOTE_NO, NVL (B.COMPANY_NAME, B.FIRST_NAME) FIRST_NAME ,M.TOTAL_SUM_INSURED TOTAL_SUM_INSURED,(NVL (M.PREMIUM, 0) + NVL (M.EXCESS_PREMIUM, 0) + NVL (M.GOVT_TAX, 0) +NVL (M.EMERGENCY_FUND, 0 ) + NVL (M.POLICY_FEE, 0)) PREMIUM,( select count(*) from WEBSERVICE_MARINE_QUOTE  WHERE APPLICATION_NO = M.APPLICATION_NO and TRANSACTION_ID is not null ) PACKAGE_DESCRIPTION FROM POSITION_MASTER A, PERSONAL_INFO B, MARINE_DATA M   WHERE A.LOGIN_ID =? AND (A.FREIGHT_STATUS NOT IN ('F', 'B') OR A.FREIGHT_STATUS IS NULL) AND A.EFFECTIVE_DATE > = SYSDATE AND A.STATUS  = 'Y' AND (A.REMARKS NOT IN ('Admin', 'Referal', 'NORMAL_EXCESS_PRICE')) AND M.APPLICATION_NO  = A.APPLICATION_NO AND NVL (M.ADMIN_REFERRAL_STATUS, 'N') NOT IN ('Y')      AND B.CUSTOMER_ID = A.CUSTOMER_ID AND A.PRODUCT_ID = ?  AND A.APPLICATION_ID = NVL(?,'1') AND NVL(A.OPEN_COVER_NO,'0') like NVL(? ||'%','0') ORDER BY QUOTE_NO DESC";
				}
			for (int i = 0; i < args.length; i++) {
				if(args[i]==null){
					args[i]="";
				}
			}
			list=this.mytemplate.queryForList(sql,args);			
		}
		catch (Exception e)
		{			
			logger.debug("EXCEPTION @ { " + e + " }");
		}		
		logger.info("Query=>" + sql);		
		logger.info("getReportList - Exit || Result: " + list.size());
				
		return list;
	}
	public List<Map<String, Object>> getDeclarationList(List<Object> selectedQuote) throws  BaseException{
		logger.info("getReportList - Enter || args: "+StringUtils.join(selectedQuote, ","));
		List<Map<String, Object>> list=null;
		try
		{
			sql=getQuery(DBConstants.DECLARATION_QUOTE_INFO);
			NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(this.mytemplate);
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("QUOTE_NOS", selectedQuote);
			list=namedParameterJdbcTemplate.queryForList(sql,parameters);			
		}
		catch (Exception e)
		{			
			logger.debug("EXCEPTION @ { " + e + " }");
		}		
		logger.info("Query=>" + sql);		
		logger.info("getReportList - Exit || Result: " + list.size());
				
		return list;
	}
	 public int updatePolicyInfo(List<Object> selectedQuote, String generateStatus, String stampStatus) throws  BaseException{
		 logger.info("updatePolicyInfo - Enter || args: " + StringUtils.join(selectedQuote,","));
		 int result=0;
		 try{
			 //UPDATE POSITION_MASTER SET PDF_GENERATE_STATUS\=?,PDF_STAMP_STATUS\=? WHERE QUOTE_NO IN (?)
				
			 //sql=getQuery(DBConstants.DECLARATION_UPDATE_QUOTE);
			 Object quoteNos[]=selectedQuote.toArray();
			 String str="";
			 for(Object k:quoteNos)
				 str+=",'"+k+"'";
			 if(str.length()>0)
 				 str=str.substring(1);

			    LocalizedTextProvider ltp = ActionContext.getContext().getInstance(LocalizedTextProvider.class);
			 sql=ltp.findDefaultText(DBConstants.DECLARATION_UPDATE_QUOTE,Locale.ENGLISH , new Object[]{StringUtils.isBlank(generateStatus)?"N":generateStatus, StringUtils.isBlank(stampStatus)?"N":stampStatus, str});
			 result=this.mytemplate.update(sql);
		 }catch(Exception e){
			 logger.debug("EXCEPTION @ { " + e + " }");
			 e.printStackTrace();
		 }
		 logger.info("Query===>" + sql);
		 logger.info("updatePolicyInfo - Exit || Result: " + result);
		 
		 return result;
	 }

	 public List<String> validateFields(List<Object> selectedQuotes){
		 List<String> result=new ArrayList<String>();
		  try{
			  logger.info("Enter into validateFields()");
			  String query="SELECT APPLICATION_NO,BRANCH_CODE,SETTLING_AGENT,QUOTE_NO FROM WEBSERVICE_MARINE_QUOTE WHERE QUOTE_NO IN("+"'"+StringUtils.join(selectedQuotes,"','")+"'"+")";
			  //String args="'"+StringUtils.join(selectedQuotes,"','")+"'";
			  List<Map<String,Object>> listOfRecord = this.mytemplate.queryForList(query);
			  	if(!listOfRecord.isEmpty() && listOfRecord.size()>0){
			  		for(int i=0;i<listOfRecord.size();i++){
			  			Map<String,Object> record =listOfRecord.get(i); 
			  			 if(record.get("SETTLING_AGENT")==null || StringUtils.isEmpty(record.get("SETTLING_AGENT").toString())){
				 			result.add("Please Go Back & Select Settling Agent in Quote No :"+String.valueOf(record.get("QUOTE_NO")));
				 			}
			  			String string = String.valueOf(record.get("APPLICATION_NO"));
			  			String string2 = record.get("BRANCH_CODE").toString();
			  			 List<Map<String, Object>> list= searchList(string,string2);
				 			for(Map<String, Object> map:list){
				 				if((String)map.get("INVOICE_NUMBER")==null || StringUtils.isBlank((String)map.get("INVOICE_NUMBER")))
				 					result.add("Please Enter Invoice Number for '"+map.get("COMMODITY_NAME").toString()+"' Commodity For Quote No"+ String.valueOf(record.get("QUOTE_NO")));
				 				/*if((String)map.get("INVOICE_DATE")==null || StringUtils.isBlank((String)map.get("INVOICE_DATE")))
				 					result.add("Please Enter Invoice Date for '"+map.get("COMMODITY_NAME").toString()+"' Commodity For Quote No"+  String.valueOf(record.get("QUOTE_NO")));*/
				 			}
			  		}
			  	}
			  logger.info("Exit into validateFields()");
		  }catch (Exception e) {
			e.printStackTrace();
			 result.add(e.getLocalizedMessage());
		}
			return result;
	 }

 
	 public List<String> validateExcessPremium(List<Object> selectedQuotes) 
	 	{
		 boolean endt=false;
		 String totalPremium,additionalPremium;
		  List<String> result=new ArrayList<String>();
		  try{
			  String query="SELECT APPLICATION_NO FROM WEBSERVICE_MARINE_QUOTE WHERE QUOTE_NO IN("+"'"+StringUtils.join(selectedQuotes,"','")+"'"+")";
			 	List<Map<String,Object>> listOfRecord = this.mytemplate.queryForList(query);
		  	for(int i=0;i<listOfRecord.size();i++){
		  		Map<String, Object> record = listOfRecord.get(i);
		  		endt=new PremiumService().isEndt(String.valueOf(record.get("APPLICATION_NO")));
		  		double excessPercent=0.0,loadPercent=0.0,disPercent=0.0,minPre=0.0;
		  		query="select EXCESS_PREMIUM,PREMIUM from marine_data WHERE APPLICATION_NO=?";
		  		List<Map<String,Object>> premium = this.mytemplate.queryForList(query, new Object[]{String.valueOf(record.get("APPLICATION_NO"))});
		  		additionalPremium=String.valueOf(premium.get(0).get("EXCESS_PREMIUM"))!=null?String.valueOf(premium.get(0).get("EXCESS_PREMIUM")):"0";
		  		totalPremium=premium.get(0).get("PREMIUM")!=null?String.valueOf(premium.get(0).get("PREMIUM")):"0";
	 		
		  		if(!endt){
		  			if(Double.parseDouble(StringUtils.isBlank(additionalPremium)?"0":additionalPremium)!=0)
		  				excessPercent= (Double.parseDouble(additionalPremium)/Double.parseDouble(totalPremium))*100;
		  			else
	 				excessPercent=0;
	 			 }
	 			  	if(Double.parseDouble(additionalPremium)>Double.parseDouble(totalPremium)){
	 					result.add("error.premiumInfo.excessPremium.exceeds.totalPre");
	 				}else if(minPre==Double.parseDouble(totalPremium) && excessPercent>0){
	 					result.add("Final premium less than Minimum premium");
	 				}else if(Double.parseDouble(totalPremium)-Double.parseDouble(additionalPremium)<minPre){
	 					result.add("Final premium less than Minimum premium");
	 				}
	 		}
		  }catch (Exception e) {
			 result.add(e.getLocalizedMessage());
		}
		  	return result;
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
		  	
}
