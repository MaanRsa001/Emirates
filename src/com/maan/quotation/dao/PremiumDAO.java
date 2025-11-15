package com.maan.quotation.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.maan.common.DBConstants;
import com.maan.common.LogUtil;
import com.maan.common.MyJdbcTemplate;
import com.maan.common.exception.BaseException;
import com.maan.common.login.LogInDAO;
import com.maan.quotation.PremiumAction;
import com.maan.report.JasperReports;
import com.maan.webservice.dao.PolicyGenerationDAO;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.LocalizedTextProvider;

import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.Multipart;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import rsa.pdf.PDFCreatorBean;
import rsa.pdf.finalprint;
@SuppressWarnings("unchecked")
public class PremiumDAO extends MyJdbcTemplate {
	private static final Logger logger = LogUtil.getLogger(PremiumDAO.class);
	
	 String sql="";
	 
	 public List<Map<String, Object>> getQuotationInformation(String applicationNo,String branchCode){
			List<Map<String, Object>> quoteInfo=null;			
			
			logger.info("Method to quotation information");
			logger.info("applicationNo"+applicationNo+","+branchCode);
			try{
				sql=getQuery(DBConstants.PREMIUM_QUOTATION_INFO);
				quoteInfo=this.mytemplate.queryForList(sql,new String[]{branchCode,branchCode,branchCode,applicationNo,branchCode});			
			   }
			catch (Exception e) {			
				logger.debug("EXCEPTION @ { " + e + " }");
			}		
			logger.info("Query===>" + sql);
			logger.info("getQuotationInformation() - Exit || Result: " + quoteInfo.size());
					
			return quoteInfo;
		}
	 public List<Map<String, Object>> getQuotationInsuredValue(String applicationNo,String branchCode){
			List<Map<String, Object>> quoteInfo=null;
			Object args[]=new Object[2];
			args[0]=applicationNo;
			args[1]=branchCode;
			logger.info("Method to quotation information");
			try{
				sql=getQuery(DBConstants.PREMIUM_GOODS_INFO);
				quoteInfo=this.mytemplate.queryForList(sql,args);			
			   }
			catch (Exception e) {			
				logger.debug("EXCEPTION @ { " + e + " }");
			}		
			logger.info("Query===>" + sql);
			logger.info("getQuotationInsuredValue() - Exit || Result: " + quoteInfo.size());
					
			return quoteInfo;
		}
	 
	 public int updatePolicyInformation(PremiumAction premiumAction) throws  BaseException{
		 logger.info("updatePolicyInformation() - Enter");
		 int result=0;
		 Object args[]=new Object[15];
		 Object arg[]=new Object[3];
		 try{			
		
							
				 arg[0]=StringUtils.defaultIfEmpty(premiumAction.getReferralUpdate(), "N");
				 arg[1]=StringUtils.defaultIfEmpty(premiumAction.getReferralComment(), "");
				 arg[2]=premiumAction.getApplicationNo();					 
				 sql=getQuery(DBConstants.PREMIUM_UPDATE_REFERRAL);				 
				 result=this.mytemplate.update(sql,arg);
				 result=this.mytemplate.update(getQuery("UPD_WS_ADMIN_REFERRAL"),arg);
				 
			if(!"Y".equalsIgnoreCase(premiumAction.getReferralUpdate())){
				 if(!"RA".equalsIgnoreCase(premiumAction.getQuoteStatus()) && !premiumAction.isEndt()){
					this.mytemplate.update(getQuery("UPD_PM_ADMIN_REM"), new String[]{"Y", "Normal", premiumAction.getApplicationNo()});
				 }
				 String debitCustId="", paymentMode=premiumAction.getPaymentMode();
				 if("CR".equalsIgnoreCase(paymentMode)){
					 if("11".equals(premiumAction.getProductId())){
						 debitCustId=new PolicyGenerationDAO().getValue("GET_OC_CUST_ID", new String[]{premiumAction.getOpenCoverNo()}) ; 
					 }else{
						 debitCustId=premiumAction.getCustomerId();
					 }
				 }else if("CRO".equalsIgnoreCase(paymentMode)){
					 paymentMode="CR";
					 debitCustId=premiumAction.getCustomerId();
				 }
				 args[0]="false".equalsIgnoreCase(premiumAction.getPremiumYN())? "NO":premiumAction.getPremiumYN();
				 args[1]=StringUtils.isEmpty(premiumAction.getBanker())|| "false".equals(premiumAction.getBanker())?"NO":premiumAction.getBanker();					
				 args[2]="NO";
				 args[3]=StringUtils.isEmpty(premiumAction.getBoth())|| "false".equals(premiumAction.getBoth())? "NO":premiumAction.getBoth();
				 args[4]="false".equalsIgnoreCase(premiumAction.getForeign())? "NO":premiumAction.getForeign();
				 args[5]="false".equalsIgnoreCase(premiumAction.getRubberStamp())? "N":premiumAction.getRubberStamp();
				 args[6]=premiumAction.getNoteType();				
				 args[7]=paymentMode==null?"":paymentMode;				
				 args[8]=premiumAction.getBasisValDesc();				
				 args[9]=StringUtils.isEmpty(premiumAction.getCertClausesYN())|| "false".equals(premiumAction.getCertClausesYN())? "N":premiumAction.getCertClausesYN();				
				 args[10]=debitCustId==null?"":debitCustId;	
				 args[11]=StringUtils.isEmpty(premiumAction.getShowpremiumYN())||"false".equalsIgnoreCase(premiumAction.getShowpremiumYN())? "N":premiumAction.getShowpremiumYN();
				 args[12]=StringUtils.isEmpty(premiumAction.getPrintClausesYN())||"false".equalsIgnoreCase(premiumAction.getPrintClausesYN())? "N":premiumAction.getPrintClausesYN();
				 args[13]="false".equalsIgnoreCase(premiumAction.getExcess())? "NO":premiumAction.getExcess();
				 args[14]=premiumAction.getApplicationNo();				
				 sql=getQuery(DBConstants.PREMIUM_UPDATE_POLICY);
				 result=this.mytemplate.update(sql,args);
				 
				 if(!"Y".equalsIgnoreCase(premiumAction.getGeneratePolicy())){
					 args=new Object[12];
					 args[0]="false".equalsIgnoreCase(premiumAction.getPremiumYN())? "NO":premiumAction.getPremiumYN();
					 args[1]=StringUtils.isEmpty(premiumAction.getBanker())|| "false".equals(premiumAction.getBanker())?"NO":premiumAction.getBanker();					
					 args[2]="NO";
					 args[3]=StringUtils.isEmpty(premiumAction.getBoth())|| "false".equals(premiumAction.getBoth())? "NO":premiumAction.getBoth();
					 args[4]="false".equalsIgnoreCase(premiumAction.getForeign())? "NO":premiumAction.getForeign();
					 args[5]="false".equalsIgnoreCase(premiumAction.getRubberStamp())? "N":premiumAction.getRubberStamp();
					 args[6]=StringUtils.isEmpty(premiumAction.getCertClausesYN())|| "false".equals(premiumAction.getCertClausesYN())? "N":premiumAction.getCertClausesYN();
					 args[7]=StringUtils.isEmpty(premiumAction.getShowpremiumYN())||"false".equalsIgnoreCase(premiumAction.getShowpremiumYN())? "N":premiumAction.getShowpremiumYN();
					 args[8]=StringUtils.isEmpty(premiumAction.getPrintClausesYN())||"false".equalsIgnoreCase(premiumAction.getPrintClausesYN())? "N":premiumAction.getPrintClausesYN();
					 args[9]="false".equalsIgnoreCase(premiumAction.getExcess())? "NO":premiumAction.getExcess();
					 args[10]="false".equalsIgnoreCase(premiumAction.getBasisValDesc())? "ACTUAL AMOUNT":premiumAction.getBasisValDesc();
					 args[11]=premiumAction.getApplicationNo();	
					 result=this.mytemplate.update(getQuery("UPD_POLICY_DOC_INFO"),args);
				 }
			}
		 }catch(Exception e){
			 logger.debug("EXCEPTION @ { " + e + " }");
			 e.printStackTrace();
		 }
		 logger.info("Query===>" + sql);
		 logger.info("Args===>" + StringUtils.join(args,","));		 
		 logger.info("updatePolicyInformation() - Exit || Result: " + result);
		 
		 return result;
	 }
	 public String updatePremiumInfo(String applicationNo,String premium, String excessPremium, String policyFee, String totalWarPremium, String vatTax)
	 {
		logger.info("updatePremiumInfo - Enter || applicationNo: "+applicationNo+" premium: "+premium+" excess: "+excessPremium+" fee: "+policyFee);
		String result="";
		try 
		
		{	
			sql=getQuery(DBConstants.PREMIUM_UPDATE_MD);
			this.mytemplate.update(sql, new String[]{premium,excessPremium,policyFee,totalWarPremium,applicationNo});
			String totalPremium= String.valueOf(Double.parseDouble(premium)+Double.parseDouble(StringUtils.isBlank(vatTax)?"0":vatTax));

			sql=getQuery(DBConstants.PREMIUM_UPDATE_PM);
			this.mytemplate.update(sql, new String[]{totalPremium,excessPremium,applicationNo});
			sql=getQuery("UPD_WS_PRE_INFO");
			this.mytemplate.update(sql, new String[]{premium,excessPremium,totalWarPremium,policyFee,applicationNo});
		} catch (Exception e) {
			logger.debug("EXCEPTION @ { " + e + " }");
		}
		logger.info("updatePremiumInfo - Exit || Result: "+result);
		
		return result;
	 }
	 public List<Map<String, Object>> getPolicyInformation(String applicationNo)
	 {
			List<Map<String, Object>> policyInfo=new ArrayList<Map<String, Object>>();		
			logger.info("getPolicyInformation - Enter");
			try{
				sql=getQuery(DBConstants.PREMIUM_POLICYINFO);
				policyInfo=this.mytemplate.queryForList(sql,new String[]{applicationNo});			
			   }
			catch (Exception e) {			
				logger.debug("EXCEPTION @ { " + e + " }");
			}		
			logger.info("Query===>" + sql+" args==>" +applicationNo);
			logger.info("getPolicyInformation - Exit || Result: " + policyInfo.size());
					
			return policyInfo;
	}	
	 public boolean getCustAccountStatus(String applicationNo)
	 {
		 logger.info("getCustAccountStatus - Enter || applicationNo: "+applicationNo);
		 boolean sts=false;
		 try{
			 sql=getQuery(DBConstants.CUST_ACCOUNT);
			 String result=(String) this.mytemplate.queryForObject(sql,new String[]{applicationNo}, String.class);
			 if(!StringUtils.isEmpty(result)){
				 sts=true;
			 }
		 }
		 catch (Exception e) {			
			 logger.debug("EXCEPTION @ { " + e + " }");
		 }		
		 logger.info("Query===>" + sql+" args==>" +applicationNo);
		 logger.info("getCustAccountStatus() - Exit || Result: " + sts);
		 		
		 return sts;
	 }	
	 public void updateClausesInfo(String[] args)
	 {
		logger.info("updateClausesInfo - Enter || args: "+StringUtils.join(args,","));
		int result=0;
		try 
		{
			sql=getQuery(DBConstants.CLAUSES_UPDATE);
			result=this.mytemplate.update(sql, args);
		} catch (Exception e) {
			logger.debug("EXCEPTION @ { " + e + " }");
		}
		logger.info("updateClausesInfo - Exit || Result: "+result);
		
	 }
	 public List<Map<String, Object>> getExistingConditions(String option, String branchCode, String coverId, List<String> id)
	 {
	 	logger.info("getConditionsList - Enter || "+option);
	 	List<Map<String, Object>> result=new ArrayList<Map<String, Object>>();					
	 	try{
	 		NamedParameterJdbcTemplate namedJdbcTemplate=new NamedParameterJdbcTemplate(this.mytemplate);
	 		MapSqlParameterSource paramters=new MapSqlParameterSource();
	 		paramters.addValue("cover", coverId);
	 		paramters.addValue("id", id);
	 		paramters.addValue("branch", branchCode);
	 		sql=getQuery("condition"+option);
	 		result=namedJdbcTemplate.queryForList(sql, paramters);	
	 	}
	 	catch (Exception e) {			
	 		logger.debug("EXCEPTION @ { " + e + " }");
	 	}		
	 	logger.info("Query==>" + sql);
	 	logger.info("getConditionsList() - Exit || Result: "+result.size());
	 			
	 	return result;
	 }
	 public void addConditionsInfo(String[] args)
	 {
		logger.info("addConditionsInfo - Enter || args: "+StringUtils.join(args,","));
		int result=0;
		try 
		{
			sql=getQuery(DBConstants.CLAUSES_ADD);
			result=this.mytemplate.update(sql, args);
		} catch (Exception e) {
			logger.debug("EXCEPTION @ { " + e + " }");
		}
		logger.info("addConditionsInfo - Exit || Result: "+result);
		
	 }
	
	 public boolean getOpenCoverCustomer(String openCoverNo)
	 {
		 logger.info("getInsuredArnoStatus - Enter || customerId: "+openCoverNo);
		 boolean sts=false;
		 try{
			 sql=getQuery(DBConstants.OPENCOVER_CUSTOMER);
			 String result=(String) this.mytemplate.queryForObject(sql,new String[]{openCoverNo}, String.class);
			 if(!StringUtils.isEmpty(result)){
				 sts=true;
			 }
		 }
		 catch (Exception e) {			
			 logger.debug("EXCEPTION @ { " + e + " }");
		 }		
		 logger.info("Query===>" + sql+" args==>" +openCoverNo);
		 logger.info("getArnoStatus() - Exit || Result: " + sts);
		 		
		 return sts;
		 }	
	 public List<Map<String, Object>> getBasisValList(String branchCode)
	 {
			List<Map<String, Object>> list=null;		
			logger.info("getBasisValList - Enter || branchCode: "+branchCode);
			try{
				sql=getQuery(DBConstants.BASIS_VAL_LIST);
				list=this.mytemplate.queryForList(sql,new String[]{branchCode});			
		    }catch (Exception e) {			
				logger.debug("EXCEPTION @ { " + e + " }");
			}		
			logger.info("getBasisValList - Exit || Result: " + list.size());
					
			return list;
	}
	 
	public Map<String, Object> getConditions(String applicationNo,String branchCode)
	 {
	 	logger.info("getConditions - Exit || applicationNo: "+applicationNo+" branchCode: "+branchCode);
	 	Map<String, Object> list=new HashMap<String, Object>();
	 	List<Map<String, Object>> result=null;
	 	String sql=getQuery("GET_CONDITIONS");
	 	logger.info("Query==> " + sql);
	 	result=this.mytemplate.queryForList(sql, new String[]{"Clauses",applicationNo, branchCode});
	 	list.put("clausesDesc", result);
	 	result=this.mytemplate.queryForList(sql, new String[]{"War",applicationNo, branchCode});
	 	list.put("extraClausesDesc", result);
	 	result=this.mytemplate.queryForList(sql, new String[]{"Warranty",applicationNo, branchCode});
	 	list.put("warrantyDesc", result);
	 	result=this.mytemplate.queryForList(sql, new String[]{"Exclusion",applicationNo, branchCode});
	 	list.put("exclusionsDesc", result);
	 	logger.info("getConditions - Exit || result: "+list.size());
	 	return list;
	 }
	public void updateAdminReferralInfo(String applicationNo,String refStatus, String adminRefRemarks, String commission, String adminLogin, String stat) {
		logger.info("updateAdminReferralInfo - Enter || applicationNo: "+applicationNo+"		refStatus: "+refStatus+"		adminRefRemarks: "+adminRefRemarks+"		commission: "+commission+"		adminLogin: "+adminLogin+"		stat:"+stat);
		int result=0, count=0;
		/*try{
			if("A".equalsIgnoreCase(refStatus)){
				result=this.mytemplate.update(getQuery("UPD_PM_ADMIN_APPR"), new String[]{adminRefRemarks, commission, adminLogin, applicationNo});
				result=this.mytemplate.update(getQuery("UPD_MD_ADMIN_STS"), new String[]{"N", applicationNo});
			 }else if("N".equalsIgnoreCase(refStatus)){
				 count=this.mytemplate.queryForInt(getQuery("CNT_QUOTE_REFERRAL"), new String[]{applicationNo});
				 if(count<=0){
					 result=this.mytemplate.update(getQuery("UPD_MD_ADMIN_STS"), new String[]{"Y", applicationNo});
					 result=this.mytemplate.update(getQuery("UPD_PM_ADMIN_REM"), new String[]{"Normal", applicationNo});
				 }else{
					 result=this.mytemplate.update(getQuery("UPD_PM_ADMIN_REM"), new String[]{"Referal", applicationNo});
				 }
			 }else if("R".equalsIgnoreCase(refStatus)){
				 result=this.mytemplate.update(getQuery("UPD_MD_ADMIN_STS"), new String[]{"Y", applicationNo});
				 result=this.mytemplate.update(getQuery("UPD_PM_ADMIN_REJ"), new String[]{adminRefRemarks, applicationNo});
				 result=this.mytemplate.update(getQuery("DEL_ENDT_MD"), new String[]{applicationNo});
				 result=this.mytemplate.update(getQuery("DEL_ENDT_MRD"), new String[]{applicationNo});
				 result=this.mytemplate.update(getQuery("UPD_ENDT_STS"), new String[]{applicationNo});
				 result=this.mytemplate.update(getQuery("UPD_ENDT_STS_REJ"), new String[]{applicationNo});
			 }
		} catch (Exception e) {
			logger.debug("EXCEPTION @ { " + e + " }");
		}*/
		try  {
			if("A".equalsIgnoreCase(refStatus)){
				result=this.mytemplate.update(getQuery("UPD_PM_ADMIN_APPR"), new String[]{stat, adminRefRemarks, commission, adminLogin, applicationNo});
				result=this.mytemplate.update(getQuery("UPD_MD_ADMIN_STS"), new String[]{"N", applicationNo});
			 }else if("N".equalsIgnoreCase(refStatus)){
				 count=this.mytemplate.queryForObject(getQuery("CNT_QUOTE_REFERRAL"), Integer.class, new String[]{applicationNo});
				 if(count<=0){
					 result=this.mytemplate.update(getQuery("UPD_MD_ADMIN_STS"), new String[]{"Y", applicationNo});
					 result=this.mytemplate.update(getQuery("UPD_PM_ADMIN_REM"), new String[]{stat, "Normal", applicationNo});
				 }else{
					 result=this.mytemplate.update(getQuery("UPD_PM_ADMIN_REM"), new String[]{stat, "Referal", applicationNo});
				 }
				 List<Map<String, Object>> referralStatus=this.mytemplate.queryForList("select a.REMARKS, b.ADMIN_REFERRAL_STATUS,a.status from Position_master a,marine_data b where a.application_no=b.application_no and a.application_no=?",new Object[]{applicationNo});
				 if(referralStatus!=null){
					 Map<String,Object> temp=(Map<String,Object>)referralStatus.get(0);
					 String status=temp.get("STATUS")==null?"":temp.get("STATUS").toString();
					 String adminReferral=temp.get("ADMIN_REFERRAL_STATUS")==null?"":temp.get("ADMIN_REFERRAL_STATUS").toString();
					 String updateRemarks="",updateStatus="";
					 if("R".equalsIgnoreCase(status) && "Y".equalsIgnoreCase(adminReferral)){
						 updateRemarks="Normal";
						 updateStatus=stat;
					 }else if("R".equalsIgnoreCase(status) && "N".equalsIgnoreCase(adminReferral)){
						 updateRemarks="Referal";
						 updateStatus=stat;
					 }
					 if(StringUtils.isNotBlank(updateRemarks) && StringUtils.isNotBlank(updateStatus)){
					     this.mytemplate.update("update position_master set remarks=? , status=? where application_no=?",new Object[]{updateRemarks,updateStatus,applicationNo});
					 }
				 }
				 
			 }else if("R".equalsIgnoreCase(refStatus)){
				 result=this.mytemplate.update(getQuery("UPD_PM_ADMIN_REJ"), new String[]{adminRefRemarks,adminLogin,applicationNo});
				 //result=this.mytemplate.update(getQuery("DEL_ENDT_MD"), new String[]{applicationNo});
				 //result=this.mytemplate.update(getQuery("DEL_ENDT_MRD"), new String[]{applicationNo});
				 //result=this.mytemplate.update(getQuery("UPD_ENDT_STS"), new String[]{applicationNo});
				 //result=this.mytemplate.update(getQuery("UPD_ENDT_STS_REJ"), new String[]{applicationNo});
			 }
			
		} catch (Exception e) {
			logger.debug("EXCEPTION @ { " + e + " }");
		}
		logger.info("updateAdminReferralInfo - Exit || Result: "+result);
		
	 }
	public Map<String, Object> getEndtPremiumInfo(final String quoteNo)
	{
		logger.info("getEndtPremiumInfo - Enter || quoteNo: "+quoteNo);
		Map<String, Object> map=new HashMap<String, Object>();
		try{
			map=this.mytemplate.queryForMap(getQuery("GET_ENDT_PRE_INFO"), new String[]{quoteNo});
		}catch(Exception e){
			logger.debug(e);
		}
		logger.info("getEndtPremiumInfo - Exit ");
		return 	map;
	}
	public List<Map<String, Object>> getPolicyDeductibles(String policyNo)
	 {
			List<Map<String, Object>> list=null;		
			logger.info("getPolicyDeductibles - Enter || policyNo: "+policyNo);
			try{
				sql=getQuery("GET_POL_DEDUCT");
				list=this.mytemplate.queryForList(sql,new String[]{policyNo});			
		    }catch (Exception e) {			
				logger.debug("EXCEPTION @ { " + e + " }");
			}		
			logger.info("getPolicyDeductibles - Exit || Result: " + list.size());
					
			return list;
	}
	public Map<String,Object> getExistingCustInfo(String customerId) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
		    LocalizedTextProvider ltp = ActionContext.getContext().getInstance(LocalizedTextProvider.class);
			String query = ltp.findDefaultText("GET_EXISTINGCUST_INFO",Locale.ENGLISH);
			resultMap = this.mytemplate.queryForMap(query,new Object[]{customerId});
		}
		catch(Exception exception) {
			logger.debug(exception);
		}
		
		return resultMap;
	}
	public String getCreditNoteStatus(String loginId,String productId){
		String result = "";
		try {
			result = (String) this.mytemplate.queryForObject("select FREIGHT_DEBIT_OPTION from login_user_details where login_id=? and PRODUCT_ID=?", new Object[]{loginId,productId},String.class);
		}
		catch(Exception exception) {
			exception.printStackTrace();
		}
		return result;
	}
	public void updateExcess(String applicationNo,String policyExcessVal) {
		int status=0;
		logger.info("updateExcess ***Admin - Enter || applicationNo: "+applicationNo +"policyExcessVal: "+policyExcessVal);
		try
		{
			sql=getQuery("UPD_EXCESS_WMC");
			status = this.mytemplate.update(sql, new String[]{policyExcessVal,applicationNo});
			sql=getQuery("UPD_EXCESS_MRD");
			status = this.mytemplate.update(sql, new String[]{policyExcessVal,applicationNo});
		}catch(Exception e)
		{
			logger.debug("Exception @ "+e);
		}
		
		logger.info("updateExcess ***Admin - Exit");
	}
	public String getExistingCustOsAmt(String customerId) {
		String result="0";
		try{

		    LocalizedTextProvider ltp = ActionContext.getContext().getInstance(LocalizedTextProvider.class);
			String query = ltp.findDefaultText("GET_EXISTINGCUST_OS_AMT",Locale.ENGLISH);
			result = (String) this.mytemplate.queryForObject(query,new Object[]{customerId},String.class);
		

		}catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	public String createMerchantRefNoWithStatus(String quoteNo, String applicationNo, String status,String paymentMethod,String productId, PremiumAction premiumAction) {
		try {
			
			sql="select * from payment_vendor_master where status='Y' and vendor_id='1'";
			List<Map<String, Object>> vendors = this.mytemplate.queryForList(sql);
			Map<String, Object> vendor = vendors.get(0);
			
			String expTime=vendor.get("PAYLINK_EXP_HOUR")==null?"12":vendor.get("PAYLINK_EXP_HOUR").toString();
			
			String query="SELECT MD.PREMIUM,MD.EXCESS_PREMIUM,NVL(MD.PREMIUM,0)+NVL(MD.EXCESS_PREMIUM,0)+NVL(MD.VAT_TAX_AMT,0) TOTAL_PREMIUM,"
					+ "PI.EMAIL EMAIL,CONCAT(PI.ADDRESS1,PI.ADDRESS2) ADDRESS,PI.EMIRATE CITY,"
					+ "NVL(PI.COUNTRY,'AE') COUNTRY,"
					+ "'EIC'||MERCHANT_REFERENCE_NO.NEXTVAL MERCHANT_REFERENCENO,PI.FIRST_NAME NAME,PI.POBOX POBOX"
					+ ",'AED' RES_REQ_CURRENCY,TO_CHAR(SYSTIMESTAMP + INTERVAL '"+expTime+"' HOUR,'YYYY-MM-DD HH24:MI:SS.FF3') PAYMENT_EXPTIME,'01' BRANCH_CODE FROM POSITION_MASTER PM,PERSONAL_INFO PI,MARINE_DATA MD WHERE PI.CUSTOMER_ID=PM.CUSTOMER_ID AND PM.QUOTE_NO=? AND MD.APPLICATION_NO=PM.APPLICATION_NO";
			
			Map<String, Object> resultMap = this.mytemplate.queryForMap(query,new Object[]{quoteNo});
				
			
			String baseUrl=vendor.get("API_BASE_URL")==null?"":vendor.get("API_BASE_URL").toString();
			baseUrl=baseUrl.replaceAll("<merchantRefNumber>", resultMap.get("MERCHANT_REFERENCENO").toString())
					.replaceAll("<applicationNumber>", applicationNo);
			String tinyUrl=StringUtils.isEmpty(baseUrl)?"":createTinyURL(baseUrl);
			String sql = "INSERT INTO payment_detail (" +
					"QUOTE_NO, PRODUCT_ID, PAYMENT_TYPE, PREMIUM, REQUEST_TIME, " +
					"RESPONSE_STATUS, MERCHANT_REFERENCE, CUSTOMER_EMAIL, CUSTOMER_NAME, " +
					"BILL_TO_FORENAME, BILL_TO_SURNAME, BILL_TO_ADDRESS_LINE1, " +
					"BILL_TO_ADDRESS_CITY, BILL_TO_ADDRESS_COUNTRY, BILL_TO_ADDRESS_POSTAL_CODE, " +
					"BILL_TO_EMAIL, DEVICE_TYPE,RES_REQ_CURRENCY,APPLICATION_NO,LOGIN_ID,OPENCOVER_NO,PAYMENT_LINK,ONLINE_PAYLINK_EXPTIME,BRANCH_CODE" +
					") VALUES (?, ?, ?, ?, sysdate, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,TO_TIMESTAMP(?, 'YYYY-MM-DD HH24:MI:SS.FF3'),?)";
			
 			String args[]=new String[] {
					quoteNo,productId,"O".equals(paymentMethod)?"ONLINE":"CASH",
					resultMap.get("TOTAL_PREMIUM").toString(),
					"P".equals(status)?"PENDING":"SUCCESS",
					resultMap.get("MERCHANT_REFERENCENO").toString(),
					resultMap.get("EMAIL").toString(),
					resultMap.get("NAME").toString(),
					resultMap.get("NAME").toString(),
					resultMap.get("NAME").toString(),					
					resultMap.get("ADDRESS")==null?"NA":resultMap.get("ADDRESS").toString(),
					resultMap.get("CITY").toString(),
					resultMap.get("COUNTRY").toString(),
					resultMap.get("POBOX").toString(),
					resultMap.get("EMAIL").toString(),
					"WEB"	,
					resultMap.get("RES_REQ_CURRENCY").toString(),
					applicationNo,
					premiumAction.getAdminLoginId(),
					premiumAction.getOpenCover()==null?"":premiumAction.getOpenCover(),
							tinyUrl,
					resultMap.get("PAYMENT_EXPTIME").toString(),
					resultMap.get("BRANCH_CODE").toString()
			} ;
 			logger.info("sql:"+sql);
 			logger.info("args:"+StringUtils.join(args,","));
			this.mytemplate.update(sql, args);
			String updQuery="update position_master set ONLINE_PAYLINK_EXPTIME=TO_TIMESTAMP(?, 'YYYY-MM-DD HH24:MI:SS.FF3'),LATEST_PAYMENT_LINK=?,PAYMENT_REFERENCE=?,status='O' where quote_no=? and status!='O'";
			args=new String[] {resultMap.get("PAYMENT_EXPTIME").toString() ,tinyUrl,resultMap.get("MERCHANT_REFERENCENO").toString(),quoteNo};
			logger.info("sql:"+sql);
 			logger.info("args:"+StringUtils.join(args,","));
			this.mytemplate.update(updQuery, args);
			if("O".equals(paymentMethod) && StringUtils.isNotBlank(resultMap.get("EMAIL").toString())) {
				sentPaymentMail(tinyUrl,resultMap,applicationNo,quoteNo,premiumAction.getAdminLoginId(),expTime);
			}
			
			return resultMap.get("MERCHANT_REFERENCENO").toString();
		}
	 catch (Exception e) {
		 e.printStackTrace();
	 }
		return null;
	}
	
	private void sentPaymentMail(String tinyUrl, Map<String, Object> resultMap, String applicationNo, String quoteNo, String loginId, String expTime) {
		try{
			LogInDAO dao=new LogInDAO();
			Map<String, String> mapt=dao.getMailDetails("16");
			 List<Object> userInfo =  dao.getUserInfo(loginId,"16");
 				Map<String, String> details=new HashMap<String, String>();
				if(details != null){
					details.put("MAIL_TO", resultMap.get("EMAIL").toString());
					details.put("MAIL_CC", ((Map) userInfo.get(0) ).get("USER_MAIL").toString());
					details.put("USER", resultMap.get("NAME").toString());					
					details.put("SMTP_HOST", (String)mapt.get("SMTP_HOST"));
					details.put("SMTP_USER", (String)mapt.get("SMTP_USER"));
					details.put("SMTP_PWD", (String)mapt.get("SMTP_PWD"));
					details.put("MAIL_FROM", (String)mapt.get("SMTP_USER"));					
					details.put("SMTP_PORT", (String)mapt.get("SMTP_PORT"));
					details.put("TINY_URL", tinyUrl);
					details.put("QUOTENO", quoteNo);
					details.put("PREMIUM", resultMap.get("TOTAL_PREMIUM").toString() );
					details.put("CURRENCY", resultMap.get("RES_REQ_CURRENCY").toString() );
					details.put("EXP_TIME", expTime );
					details.put("MAIL_CONTENT", mapt.get("PAYMENT_LINK_CONTENT").toString());
					
			}
			createMailContent(details);
			
		}
		
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	 public String createMailContent(Map<String, String> details){
	    	String result = null;
		    LocalizedTextProvider ltp = ActionContext.getContext().getInstance(LocalizedTextProvider.class);
	    	try{
		    	if(details !=null && !details.isEmpty()){
		    		String SMTP_HOST_NAME = details.get("SMTP_HOST");
		    		String user = details.get("SMTP_USER");
		    		String pwd = details.get("SMTP_PWD");
		    		String SMTP_MAIL_FROM = details.get("MAIL_FROM");
		    		String subject = "Ecargo Online Payment Link";
		    		String toAddress = details.get("MAIL_TO");
		    		String ccAddress =details.get("MAIL_CC");
		    		String port= details.get("SMTP_PORT");
		    		if(toAddress!=null && !"".equals(toAddress)){
			    		String[] toAddresses = (toAddress.indexOf(",")!=-1)?toAddress.split(","):new String[]{toAddress};
			    		String[] ccAddresses = new String[0];
			    		if(ccAddress!=null && !"".equals(ccAddress)){
			    			ccAddresses = (ccAddress.indexOf(",")!=-1)?ccAddress.split(","):new String[]{ccAddress};
			    		}
			    	
			    		String mailContent=details.get("MAIL_CONTENT").toString();
			    		for (String key : details.keySet()) {
			    			mailContent=mailContent.replaceAll(Pattern.quote("{"+key+"}"), details.get(key).toString());
			    		}
			        	
			        	 
			        	StringBuffer mailDatas = new StringBuffer();
			    		mailDatas.append("<html><head></head>");
			    		mailDatas.append("<body><table style='min-height: 200px;' align='left'>");
			    		mailDatas.append("<tr height='20'><td>&nbsp;</td></tr><tr><td align='left'>");
			    		mailDatas.append(mailContent);
			    		mailDatas.append("</td></tr><tr height='20'><td>&nbsp;</td></tr>");
			    		mailDatas.append("<tr><td><p>");
			    		mailDatas.append("</table></body></html>");
			    		String message = mailDatas.toString();
			    		sendResponseMail(SMTP_HOST_NAME, user, pwd, SMTP_MAIL_FROM, subject, message, toAddresses, ccAddress,port,details.get("QUOTENO").toString(),null);
		    		}
		    	}
	    	}catch(Exception e){
	    		System.out.println("Exception Send mail => {}"+e);
	    		result = e.getMessage();
	    	}
	    	return result;
	    }
	 public void sendResponseMail(final String SMTP_HOST_NAME, final String user,  final String pwd, final String SMTP_MAIL_FROM, final String subject,
	    		final String message, final String[] toAddress, final String ccAddress,String SMTP_PORT,String quoteNo,List<String> attachments){
	    	
	    	String SMTP_AUTH_USER = user;
	    	String SMTP_AUTH_PWD = pwd;
	    	try{
		    	Properties props = new Properties();
				props.put("mail.smtp.host", SMTP_HOST_NAME);
				props.put("mail.smtp.port", SMTP_PORT);
				props.put("mail.smtp.starttls.enable", "true");
				/*props.put("mail.smtp.ssl.protocols", "TLSv1.2");
				props.put("mail.smtp.starttls.required", "true");
				props.put("mail.smtp.ssl.trust", SMTP_HOST_NAME);
				props.put("mail.smtp.socketFactory.port", SMTP_PORT);*/
				Session session = null; 
				if(SMTP_AUTH_PWD != null && !"".equals(SMTP_AUTH_PWD.trim())){
					props.put("mail.smtp.auth", "true");
					Authenticator auth = new SMTPAuthenticator(SMTP_AUTH_USER,SMTP_AUTH_PWD);
					session = Session.getInstance(props, auth);
				}else{
					props.put("mail.smtp.auth", "false");
					session = Session.getInstance(props);
				}
				session.setDebug(false);
				Message msg1 = new MimeMessage(session);
				InternetAddress addressFrom = new InternetAddress(SMTP_MAIL_FROM, "Ecargo");
				msg1.setFrom(addressFrom);
				if(toAddress != null && toAddress.length>0){
					InternetAddress[] addressTo = new InternetAddress[toAddress.length];			
					for (int i = 0; i < toAddress.length; i++){
						addressTo[i] = new InternetAddress(toAddress[i]);
						msg1.addRecipient(Message.RecipientType.TO, addressTo[i]);
					}
				}
				/*			if(ccAddress != null && ccAddress.length>0){
					InternetAddress[] addressToCC = new InternetAddress[ccAddress.length];			
					for(int i=0;i<ccAddress.length;i++){
						addressToCC[i] = new InternetAddress(ccAddress[i]);
						msg1.addRecipient(Message.RecipientType.CC, addressToCC[i]);
					}
				}*/
				if(ccAddress != null){ 
					InternetAddress[] myToList = InternetAddress.parse(ccAddress);
					msg1.addRecipients(Message.RecipientType.CC, myToList);
				}
				// Create the email body part
				MimeBodyPart bodyPart = new MimeBodyPart();
				bodyPart.setContent(message, "text/html");

				// Create the multipart container
				Multipart multipart = new MimeMultipart("related");
				multipart.addBodyPart(bodyPart);

				// Add attachment(s)			 
				if (attachments != null && attachments.size() > 0) {
				    for (String filePath : attachments) {
				        MimeBodyPart attachmentPart = new MimeBodyPart();
				        filePath=URLDecoder.decode(filePath, "UTF-8");
				        DataSource source = new FileDataSource(filePath);
				        attachmentPart.setDataHandler(new DataHandler(source));
				        attachmentPart.setFileName(new File(filePath).getName());
				        multipart.addBodyPart(attachmentPart);
				    }
				}

				String path=getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
				String basePath= URLDecoder.decode(path.substring(1, path.indexOf("WEB-INF")),"UTF-8");
				 
				{
					MimeBodyPart attachmentPart = new MimeBodyPart();
					DataSource source = new FileDataSource(basePath+"\\assets\\logo-main.png");
			       attachmentPart.setDataHandler(new DataHandler(source));
			       attachmentPart.setHeader("Content-ID", "<mainlogo>");
			       attachmentPart.setDisposition(MimeBodyPart.INLINE); 
 			       multipart.addBodyPart(attachmentPart);
				}
				{
					MimeBodyPart attachmentPart = new MimeBodyPart();
					DataSource source = new FileDataSource(basePath+"\\assets\\facebook.jpg");
			       attachmentPart.setDataHandler(new DataHandler(source));
			       attachmentPart.setHeader("Content-ID", "<fblogo>");
			       attachmentPart.setDisposition(MimeBodyPart.INLINE);
 			       multipart.addBodyPart(attachmentPart);
				} 
				{
					MimeBodyPart attachmentPart = new MimeBodyPart();
					DataSource source = new FileDataSource(basePath+"\\assets\\linkedin.jpg");
			       attachmentPart.setDataHandler(new DataHandler(source));
			       attachmentPart.setHeader("Content-ID", "<linkedlogo>");
			       attachmentPart.setDisposition(MimeBodyPart.INLINE);
 			       multipart.addBodyPart(attachmentPart);
				} 
				{
					MimeBodyPart attachmentPart = new MimeBodyPart();
					DataSource source = new FileDataSource(basePath+"\\assets\\instagram.jpg");
			       attachmentPart.setDataHandler(new DataHandler(source));
			       attachmentPart.setHeader("Content-ID", "<instalogo>");
			       attachmentPart.setDisposition(MimeBodyPart.INLINE);
 			       multipart.addBodyPart(attachmentPart);
				} 
				// Set content to message
				msg1.setContent(multipart);

				msg1.setSubject(subject);
		//		msg1.setContent(message, "text/html");
				System.out.println(msg1);
				Transport.send(msg1);
				System.out.println("Send mail => "+quoteNo);
			}catch(Exception e){
				e.printStackTrace();
			}
	    }
	    
	    private class SMTPAuthenticator extends Authenticator{
	    	private String SMTP_AUTH_USER;
	    	private String SMTP_AUTH_PWD;
	    	
			public SMTPAuthenticator(String sMTP_AUTH_USER, String sMTP_AUTH_PWD) {
				super();
				SMTP_AUTH_USER = sMTP_AUTH_USER;
				SMTP_AUTH_PWD = sMTP_AUTH_PWD;
			}

			public PasswordAuthentication getPasswordAuthentication(){
				String username = SMTP_AUTH_USER;
				String password = SMTP_AUTH_PWD;
				return new PasswordAuthentication(username, password);
			}
		}
	private String createTinyURL(String originalUrl) {
        String apiEndpoint = "https://tinyurl.com/api-create.php?url=" + originalUrl;

        try {
            URL url = new URL(apiEndpoint);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(10000); // 10 seconds
            conn.setReadTimeout(10000);    // 10 seconds
            
            int statusCode = conn.getResponseCode();
            if (statusCode == 200) {
                Scanner scanner = new Scanner(conn.getInputStream());
                String tinyUrl = scanner.nextLine();
                scanner.close();
                return tinyUrl;
            } else {
                System.out.println("TinyURL portal returned error: " + statusCode);
            }
        } catch (Exception e) {
            System.out.println("TinyURL service is down or unreachable: " + e.getMessage());
        }

        return originalUrl; // fallback to original URL
    }
	public List<Map<String, Object>> getPaymentInformation(String merchantRefno) {
		List<Map<String, Object>> policyInfo=new ArrayList<Map<String, Object>>();		
		logger.info("getPaymentInformation - Enter");
		try{
			sql="select * from payment_detail where MERCHANT_REFERENCE=? and sysdate between REQUEST_TIME and ONLINE_PAYLINK_EXPTIME ";
			policyInfo=this.mytemplate.queryForList(sql,new String[]{merchantRefno});			
		   }
		catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}		
		logger.info("Query===>" + sql+" args==>" +merchantRefno);
		logger.info("getPaymentInformation - Exit || Result: " + policyInfo.size());
				
		return policyInfo;
	}
	public Object sentPolicyMail(Map<String, Object> paymentDetail, String policyNo,Map<String, Object> quoteInfo) {

		try{
			LogInDAO dao=new LogInDAO();
			List<Object> userInfo =  dao.getUserInfo(paymentDetail.get("LOGIN_ID").toString(),"16");
			Map<String, String> mapt=dao.getMailDetails("16");
 				Map<String, String> details=new HashMap<String, String>();
				if(details != null){
					details.put("MAIL_TO", paymentDetail.get("BILL_TO_EMAIL").toString());
					details.put("MAIL_CC", ((Map) userInfo.get(0) ).get("USER_MAIL").toString());
					details.put("USER", paymentDetail.get("BILL_TO_FORENAME").toString());					
					details.put("SMTP_HOST", (String)mapt.get("SMTP_HOST"));
					details.put("SMTP_USER", (String)mapt.get("SMTP_USER"));
					details.put("SMTP_PWD", (String)mapt.get("SMTP_PWD"));
					details.put("MAIL_FROM", (String)mapt.get("SMTP_USER"));					
					details.put("SMTP_PORT", (String)mapt.get("SMTP_PORT"));					 
					details.put("QUOTENO", paymentDetail.get("QUOTE_NO").toString());
					details.put("POLICYNO", policyNo);
					details.put("MAIL_CONTENT", mapt.get("POLICY_CONTENT").toString());
					
					String path=getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
					String basePath=path.substring(1, path.indexOf("WEB-INF"));
					JasperReports jr =new JasperReports();
					String dfilePath = URLDecoder.decode(basePath + "debitpdf/"  + policyNo.replaceAll("/","-") + "Receipt.pdf","UTF-8");
					jr.debitNote(policyNo, "01", URLDecoder.decode(dfilePath,"UTF-8"));
					details.put("ATTACH2",dfilePath);
					
					String sfilePath = URLDecoder.decode(basePath + "OriginalPdf/"  + policyNo.replaceAll("/","-") + ".pdf","UTF-8");
					jr.certificateSchedule(paymentDetail.get("APPLICATION_NO").toString(), ((Map) userInfo.get(0)).get("BRANCH_CODE").toString() , "01",  URLDecoder.decode(sfilePath,"UTF-8"));//(policyNo, "01", URLDecoder.decode(dfilePath,"UTF-8"));
					details.put("ATTACH1",sfilePath);
					try {
						finalprint finalBean = new finalprint();
						String[][] pdfFile = finalBean.getPdf("Clauses",paymentDetail.get("APPLICATION_NO").toString(),"01");
						List<InputStream> pdfs = new ArrayList<InputStream>();
						if(pdfFile.length>0) {							
							for(int e=0;e<pdfFile.length;e++)
							{
								File file = new File(URLDecoder.decode(basePath + "/clauses/"  + pdfFile[e][1],"UTF-8"));
								if(file.exists()) {
									pdfs.add(new FileInputStream(file));
								}
							}
						}

						OutputStream output =null;
						try {
							output =new FileOutputStream(URLDecoder.decode(basePath + "clausespdf/"  + policyNo.replaceAll("/","-") + "clause.pdf","UTF-8"));
							PDFCreatorBean creatorBean = new PDFCreatorBean();
							creatorBean.concatPDFs(pdfs, output, true,policyNo.replaceAll("/","-") + ".pdf");
						}finally {
							if(output!=null)
								output.close();
						}
						String clausespdfPath = URLDecoder.decode(basePath + "clausespdf/"  + policyNo.replaceAll("/","-") + "clause.pdf","UTF-8");
						details.put("ATTACH3",clausespdfPath);
					}catch (Exception e) {
						e.printStackTrace();
					}
			}
			createMailContentPolicy(details);
			
		}
		
		catch(Exception e){
			e.printStackTrace();
		} 
 		return null;
	}
	private void createMailContentPolicy(Map<String, String> details) {
    	String result = null;
	    LocalizedTextProvider ltp = ActionContext.getContext().getInstance(LocalizedTextProvider.class);
    	try{
	    	if(details !=null && !details.isEmpty()){
	    		String SMTP_HOST_NAME = details.get("SMTP_HOST");
	    		String user = details.get("SMTP_USER");
	    		String pwd = details.get("SMTP_PWD");
	    		String SMTP_MAIL_FROM = details.get("MAIL_FROM");
	    		String subject = "EIC Schedule " +details.get("POLICYNO").toString();
	    		String toAddress = details.get("MAIL_TO");
	    		String ccAddress =details.get("MAIL_CC");
	    		String port= details.get("SMTP_PORT");
	    		if(toAddress!=null && !"".equals(toAddress)){
		    		String[] toAddresses = (toAddress.indexOf(",")!=-1)?toAddress.split(","):new String[]{toAddress};
		    		String[] ccAddresses = new String[0];
		    		if(ccAddress!=null && !"".equals(ccAddress)){
		    			ccAddresses = (ccAddress.indexOf(",")!=-1)?ccAddress.split(","):new String[]{ccAddress};
		    		}
		    		String mailContent=details.get("MAIL_CONTENT").toString();
		    		for (String key : details.keySet()) {
		    			mailContent=mailContent.replaceAll(Pattern.quote("{"+key+"}"), details.get(key).toString());
		    		}
		        	
		    		
		        	List<String> attach=new ArrayList<String>();
		        	attach.add(details.get("ATTACH1").toString());
		        	attach.add(details.get("ATTACH2").toString());
		        	attach.add(details.get("ATTACH3").toString());
		        	
		        	StringBuffer mailDatas = new StringBuffer();
		    		mailDatas.append("<html><head></head>");
		    		mailDatas.append("<body><table style='min-height: 200px;' align='left'>");
		    		mailDatas.append("<tr height='20'><td>&nbsp;</td></tr><tr><td align='left'>");
		    		mailDatas.append(mailContent);
		    		mailDatas.append("</td></tr><tr height='20'><td>&nbsp;</td></tr>");
		    		mailDatas.append("<tr><td><p>");
		    		mailDatas.append("</table></body></html>");
		    		String message = mailDatas.toString();
		    		sendResponseMail(SMTP_HOST_NAME, user, pwd, SMTP_MAIL_FROM, subject, message, toAddresses, ccAddress,port,details.get("QUOTENO").toString(),attach);
	    		}
	    	}
    	}catch(Exception e){
    		System.out.println("Exception Send mail => {}"+e);
    		result = e.getMessage();
    	}
     
    }
	public void deactivatePay(String quoteNo, String applicationNo) {
		try {
			String queryBackup="INSERT INTO payment_detail_history select * from payment_detail where quote_no=? and response_status='PENDING' order by request_time desc";
			this.mytemplate.update(queryBackup,new String[]{quoteNo});
			String deleteBackUp="delete from payment_detail where quote_no=? and response_status='PENDING'";
			this.mytemplate.update(deleteBackUp,new String[]{quoteNo});
			String updQuery="update position_master set status='Y' where quote_no=?";
			this.mytemplate.update(updQuery,new String[]{quoteNo});
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
