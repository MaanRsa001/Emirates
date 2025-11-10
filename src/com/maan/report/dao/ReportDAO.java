package com.maan.report.dao;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;

import com.maan.common.DBConstants;
import com.maan.common.LogUtil;
import com.maan.common.MyJdbcTemplate;
import com.maan.report.ReportAction;
import com.maan.webservice.dao.PolicyGenerationDAO;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.LocalizedTextProvider;

public class ReportDAO extends MyJdbcTemplate{
	private static final Logger logger = LogUtil.getLogger(ReportDAO.class);
	
	String sql="";
	PolicyGenerationDAO policyDAO=new PolicyGenerationDAO();
	public List<Map<String, Object>> getReportList(String loginId,String productId,String issuer, final String menuType,String openCoverNo,String startDate,String endDate, String quoteNo, String policyNo,String searchBy,String searchValue, String searchField,String searchString,String searchOper,String branchCode)
	{
		List<Map<String, Object>> reportList=new ArrayList<Map<String, Object>>();
		StringUtils.defaultIfEmpty(openCoverNo,"");
		logger.info("args===>" + loginId+","+ productId+","+ issuer+","+ menuType+","+openCoverNo);	
		try
		{

		    LocalizedTextProvider ltp = ActionContext.getContext().getInstance(LocalizedTextProvider.class);
			String args[]=null;
			/*if("31".equalsIgnoreCase(productId)||"32".equalsIgnoreCase(productId)||"33".equalsIgnoreCase(productId)||"41".equalsIgnoreCase(productId)|| "65".equalsIgnoreCase(productId) || ltp.findDefaultText("HOME_INS", Locale.ENGLISH).equalsIgnoreCase(productId)){
				if("P".equalsIgnoreCase(menuType)){
					//sql=getQuery(DBConstants.TRAVEL_REPORT_PORTFOLIO);
					sql=ltp.findDefaultText(DBConstants.TRAVEL_REPORT_PORTFOLIO, Locale.ENGLISH, new String[]{(StringUtils.isBlank(issuer)?"":"gotherPolicyNo".equalsIgnoreCase(searchField)?"":" AND HPM.APPLICATION_ID IN ('"+issuer+"') ")});
					args=new String[]{loginId,productId};
					if("quoteNo".equalsIgnoreCase(searchBy))
					{
						sql+=" "+getQuery("travel.report.portfolioQuoteNo");
						args=new String[]{loginId,productId,"%"+searchValue+"%"};
					}else if("policyNo".equalsIgnoreCase(searchBy))
					{
						sql+=" "+getQuery("travel.report.portfolioPolicyNo");
						args=new String[]{loginId,productId,"%"+searchValue+"%"};
					}else if("custName".equalsIgnoreCase(searchBy))
					{
						sql+=" "+getQuery("travel.report.portfolioCustomerName");
						args=new String[]{loginId,productId,"%"+searchValue+"%","%"+searchValue+"%"};
					}else if("otherUsers".equalsIgnoreCase(searchBy))
					{
						sql=getQuery("travel.report.portfolioissuer");
						args=new String[]{productId,"%"+searchValue+"%"};
					}
				//	sql+=" "+getQuery("travel.report.portfolioOrderBy");
				}else if ("QL".equalsIgnoreCase(menuType)) {
					//sql=getQuery(DBConstants.TRAVEL_REPORT_QUOTEREG_LAPSED);
					sql=ltp.findDefaultText(DBConstants.TRAVEL_REPORT_QUOTEREG_LAPSED, Locale.ENGLISH, new String[]{(StringUtils.isBlank(issuer)?"1":issuer)});
					args=new String[]{loginId,productId};
					if("quoteNo".equalsIgnoreCase(searchBy))
					{
						sql+=" "+getQuery("travel.quotereg.lapsedQuoteNo");
						args=new String[]{loginId,productId,"%"+searchValue+"%"};
					}else if("custName".equalsIgnoreCase(searchBy))
					{
						sql+=" "+getQuery("travel.quotereg.lapsedCustomerName");
						args=new String[]{loginId,productId,"%"+searchValue+"%","%"+searchValue+"%"};
					}
					//sql+=" "+getQuery("travel.quotereg.lapsedOrderBy");
				}else if ("QE".equalsIgnoreCase(menuType)||"QS".equalsIgnoreCase(menuType)) {
					if ("QE".equalsIgnoreCase(menuType))
						sql=ltp.findDefaultText(DBConstants.TRAVEL_QUOTEREG_EXISTING, Locale.ENGLISH, new String[]{(StringUtils.isBlank(issuer)?"1":issuer)});
					else
						sql=ltp.findDefaultText(getQuery("travel.quoteregister.saved"), Locale.ENGLISH, new String[]{(StringUtils.isBlank(issuer)?"1":issuer)});
					args=new String[]{loginId,productId};
					if("quoteNo".equalsIgnoreCase(searchBy))
					{
						sql+=" "+getQuery("travel.quotereg.existingQuoteNo");
						args=new String[]{loginId,productId,"%"+searchValue+"%"};
					}else if("custName".equalsIgnoreCase(searchBy))
					{
						sql+=" "+getQuery("travel.quotereg.existingCustomerName");
						args=new String[]{loginId,productId,"%"+searchValue+"%","%"+searchValue+"%"};
					}
					//sql+=" "+getQuery("travel.quotereg.existingOrderBy");
				}else if ("RU".equalsIgnoreCase(menuType)) {
					sql=ltp.findDefaultText(DBConstants.TRAVEL_REPORT_REFERRAL_UNAPPROVED, Locale.ENGLISH, new String[]{(StringUtils.isBlank(issuer)?"1":issuer)});
					args=new String[]{loginId,productId};
					if("quoteNo".equalsIgnoreCase(searchBy))
					{
						sql+=" "+getQuery("travel.report.referral.unapprovedQuoteNo");
						args=new String[]{loginId,productId,"%"+searchValue+"%"};
					}else if("custName".equalsIgnoreCase(searchBy))
					{
						sql+=" "+getQuery("travel.report.referral.unapprovedCustomerName");
						args=new String[]{loginId,productId,"%"+searchValue+"%","%"+searchValue+"%"};
					}
					//sql+=" "+getQuery("travel.report.referral.unapprovedOrderBy");
				}else if ("RA".equalsIgnoreCase(menuType)) {
					sql=ltp.findDefaultText(DBConstants.TRAVEL_REPORT_REFERRAL_APPROVED, Locale.ENGLISH, new String[]{(StringUtils.isBlank(issuer)?"1":issuer)});
					args=new String[]{loginId,productId};
					if("quoteNo".equalsIgnoreCase(searchBy))
					{
						sql+=" "+getQuery("travel.report.referralQuoteNo");
						args=new String[]{loginId,productId,"%"+searchValue+"%"};
					}else if("custName".equalsIgnoreCase(searchBy))
					{
						sql+=" "+getQuery("travel.report.referral.CustomerName");
						args=new String[]{loginId,productId,"%"+searchValue+"%","%"+searchValue+"%"};
					}
					//sql+=" "+getQuery("travel.report.referral.approvedOrderBy");
				}else if ("RR".equalsIgnoreCase(menuType)) {
					sql=ltp.findDefaultText(DBConstants.TRAVEL_REPORT_REFERRAL_REJECT, Locale.ENGLISH, new String[]{(StringUtils.isBlank(issuer)?"1":issuer)});
					args=new String[]{loginId,productId};
					if("quoteNo".equalsIgnoreCase(searchBy))
					{
						sql+=" "+getQuery("travel.report.referral.rejectQuoteNo");
						args=new String[]{loginId,productId,"%"+searchValue+"%"};
					}else if("custName".equalsIgnoreCase(searchBy))
					{
						sql+=" "+getQuery("travel.report.referral.rejectCustomerName");
						args=new String[]{loginId,productId,"%"+searchValue+"%","%"+searchValue+"%"};
					}
					//sql+=" "+getQuery("travel.report.referral.rejectOrderBy");
				}else if ("L".equalsIgnoreCase(menuType)) {
					sql=ltp.findDefaultText(DBConstants.TRAVEL_REPORT_LAPSEDQUOTE, Locale.ENGLISH, new String[]{(StringUtils.isBlank(issuer)?"1":issuer)});
					args=new String[]{loginId,productId};
					if("quoteNo".equalsIgnoreCase(searchBy))
					{
						sql+=" "+getQuery("travel.report.rejectedQuoteNo");
						args=new String[]{loginId,productId,"%"+searchValue+"%"};
					}else if("custName".equalsIgnoreCase(searchBy))
					{
						sql+=" "+getQuery("travel.report.rejectedCustomerName");
						args=new String[]{loginId,productId,"%"+searchValue+"%","%"+searchValue+"%"};
					}
					//sql+=" "+getQuery("travel.report.rejectedOrderBy");
				}else if("T".equalsIgnoreCase(menuType)){
					sql=getQuery(DBConstants.REPORT_TRANSACTION);
					args=new String[]{openCoverNo};
				}else if("PD".equalsIgnoreCase(menuType)){
					sql=getQuery(DBConstants.REPORT_DECLARATION_POLICY);
					args=new String[]{loginId,productId,issuer,openCoverNo,openCoverNo};
				}else if("C".equalsIgnoreCase(menuType)){
						sql=getQuery(DBConstants.TRAVEL_CUSTOMER_SELECTION);
						args=new String[]{loginId};
						if("custId".equalsIgnoreCase(searchBy))
						{
							sql+=" "+getQuery("travel.customer.selectionCustomerId");
							args=new String[]{loginId,"%"+searchValue+"%"};
						}else if("custName".equalsIgnoreCase(searchBy))
						{
							sql+=" "+getQuery("travel.customer.selectionCustomerName");
							args=new String[]{loginId,"%"+searchValue+"%","%"+searchValue+"%"};
						}else if("cusCivilId".equalsIgnoreCase(searchBy))
						{
							sql+=" "+getQuery("travel.customer.selectionCusCivilId");
							args=new String[]{loginId,"%"+searchValue+"%"};
						}else if("mobileNo".equalsIgnoreCase(searchBy))
						{
							sql+=" "+getQuery("travel.customer.selectionMobileNo");
							args=new String[]{loginId,"%"+searchValue+"%"};
						}
						//sql+=" "+getQuery("travel.customer.selectionOrderBy");
				}else if("R".equalsIgnoreCase(menuType)){
						sql=getQuery(DBConstants.REPORT_REPORT);
						args=new String[]{loginId,loginId};
				}else if("PR".equalsIgnoreCase(menuType)){
						sql=getQuery(DBConstants.TRAVEL_REPORT_POLICY);
						args=new String[]{startDate,endDate,loginId,productId};
				}else if("E".equalsIgnoreCase(menuType)){
					sql=getQuery("GET_TRAVEL_END_LIST");
					args=new String[]{policyNo, productId};
				}else if("PE".equalsIgnoreCase(menuType)){
					sql=getQuery("GET_ENDT_LIST_ISSUER");
					if("11".equals(productId)){
						openCoverNo=getOriginalPolicyNo(openCoverNo);
					}
					args=new String[]{loginId, productId,issuer,openCoverNo};	
				}
			}else
			{*/
				args=new String[]{loginId,productId,StringUtils.isBlank(issuer)?"":"gotherPolicyNo".equalsIgnoreCase(searchField)?"":issuer,StringUtils.isBlank(openCoverNo)?"":openCoverNo};
				if("P".equalsIgnoreCase(menuType)){
					if("11".equals(productId)){
						sql=ltp.findDefaultText("GET_PORTFOLIO_OC", Locale.ENGLISH, new Object[]{"gotherPolicyNo".equalsIgnoreCase(searchField)?"":"A.LOGIN_ID='"+loginId+"' AND "});
						openCoverNo="%"+getOriginalPolicyNo(openCoverNo)+"%";
						//args=new String[]{productId,StringUtils.isBlank(issuer)?"":"gotherPolicyNo".equalsIgnoreCase(searchField)?"":issuer,StringUtils.isBlank(openCoverNo)?"":openCoverNo};
						String applicationId = StringUtils.isBlank(issuer)?"1":"gotherPolicyNo".equalsIgnoreCase(searchField)?"":issuer;
						args=new String[]{productId,applicationId,applicationId,StringUtils.isBlank(openCoverNo)?"":openCoverNo};
					}else{
						sql=ltp.findDefaultText(DBConstants.REPORT_PORTFOLIO, Locale.ENGLISH, new Object[]{"gotherPolicyNo".equalsIgnoreCase(searchField)?"":"A.LOGIN_ID='"+loginId+"' AND "});
						//args=new String[]{productId,StringUtils.isBlank(issuer)?"":"gotherPolicyNo".equalsIgnoreCase(searchField)?"":issuer,StringUtils.isBlank(openCoverNo)?"":openCoverNo};
						String applicationId = StringUtils.isBlank(issuer)?"1":"gotherPolicyNo".equalsIgnoreCase(searchField)?"":issuer;
						args=new String[]{productId,applicationId,applicationId,StringUtils.isBlank(openCoverNo)?"":openCoverNo};
					}
				}else if ("QL".equalsIgnoreCase(menuType)) {
					sql=getQuery(DBConstants.REPORT_QUOTEREG_LAPSED);								
				}else if ("QE".equalsIgnoreCase(menuType)) {
					sql=getQuery(DBConstants.REPORT_QUOTEREG_EXISTING);						
				}else if ("RU".equalsIgnoreCase(menuType)) {
					sql=getQuery(DBConstants.REPORT_REFERRAL_UNAPPROVED);							
				}else if ("RA".equalsIgnoreCase(menuType)) {
					sql=getQuery(DBConstants.REPORT_REFERRAL_APPROVED);							
				}else if ("RR".equalsIgnoreCase(menuType)) {
					sql=getQuery(DBConstants.REPORT_REFERRAL_REJECT);				
				}else if ("L".equalsIgnoreCase(menuType)) {
					sql=getQuery(DBConstants.REPORT_LAPSEDQUOTE);							
				}else if("T".equalsIgnoreCase(menuType)){
					sql=getQuery(DBConstants.REPORT_TRANSACTION);
					args=new String[]{openCoverNo};
				}else if("PD".equalsIgnoreCase(menuType)){
					sql=getQuery(DBConstants.REPORT_DECLARATION_POLICY);
					args=new String[]{loginId,productId,issuer==null?"":issuer,openCoverNo,openCoverNo};
				}else if("C".equalsIgnoreCase(menuType)){
					if(StringUtils.isNotEmpty(openCoverNo)){
						sql=getQuery(DBConstants.OPENCOVER_CUSTOMER_LIST);
						args=new String[]{openCoverNo};
					}else{
						sql=getQuery(DBConstants.CUSTOMER_SELECTION);
						args=new String[]{loginId};
					}
				}else if("R".equalsIgnoreCase(menuType)){
					if(StringUtils.isEmpty(issuer)){
						sql=getQuery(DBConstants.REPORT_REPORT);
						args=new String[]{loginId,loginId}; 
					}else{
						sql=getQuery(DBConstants.REPORT_USERLIST_ISSUER);
						args=new String[]{issuer,productId};					
					}
				}else if("PR".equalsIgnoreCase(menuType)){
					if(StringUtils.isEmpty(issuer)){
						sql=getQuery(DBConstants.REPORT_POLICY);
						args=new String[]{startDate,endDate,loginId,productId};
					}else{
						sql=getQuery(DBConstants.REPORT_POLICY_ISSUER);
						args=new String[]{startDate,endDate,issuer,loginId,productId};
					}
					/*sql = "Select * from table(broker_reports(?,?,?,?,?,?))";
					args=new String[]{loginId,branchCode,startDate,endDate,"ALL",productId};*/
				}else if("E".equalsIgnoreCase(menuType)){
					sql=getQuery("GET_END_LIST");
					args=new String[]{loginId, productId,StringUtils.isBlank(policyNo)?"":policyNo};	
				}else if("PE".equalsIgnoreCase(menuType)){
					sql=getQuery("GET_ENDT_LIST_ISSUER");
					if("11".equals(productId)){
						openCoverNo=getOriginalPolicyNo(openCoverNo);
					}
					args=new String[]{loginId, productId,issuer,openCoverNo};
				}else if ("O".equalsIgnoreCase(menuType)) {
					sql=getQuery("report.quoteregister.paymentpending");						
				}
			//}
			if(!"R".equalsIgnoreCase(menuType)&&!"PR".equalsIgnoreCase(menuType)){
				String str="like '%'||upper(?)||'%'";
				if(searchField!=null && searchString!=null && searchOper!=null && searchField!="" && searchString!="" && searchOper!=""){
					if("nc".equalsIgnoreCase(searchOper))
						str="not like '%'||upper(?)||'%'";
					else if("cn".equalsIgnoreCase(searchOper))
						str="like '%'||upper(?)||'%'";
					else if("eq".equalsIgnoreCase(searchOper))
						str=" = upper(?)?";
					else if("nq".equalsIgnoreCase(searchOper))
						str=" != upper(?)";
					
					if("gquoteNo".equalsIgnoreCase(searchField))
						sql+=" and upper(A.QUOTE_NO) "+str;
					else if("gcustName".equalsIgnoreCase(searchField)){
						if("P".equals(menuType) || "QL".equals(menuType) || "PE".equals(menuType))
							sql+=" and upper(NVL (b.COMPANY_NAME, b.FIRST_NAME)) "+str;
						else if("QE".equals(menuType))
							sql+=" and upper(NVL (b.COMPANY_NAME, b.FIRST_NAME)) "+str;
						else if("L".equals(menuType) || "RU".equals(menuType) || "RR".equals(menuType))
							sql+=" and upper(B.FIRST_NAME) "+str;
					}
					else if("gpolicyNo".equalsIgnoreCase(searchField)||"gotherPolicyNo".equalsIgnoreCase(searchField))
						sql+=" and upper(POLICY_NO) "+str;
					else if("gocCustName".equalsIgnoreCase(searchField))
						sql+=" and upper(OPENCOVER_CUST_NAME) "+str;
					else if("gfirstName".equalsIgnoreCase(searchField))
						sql+=" and upper(FIRST_NAME) "+str;
					else if("gcustomerCivilId".equalsIgnoreCase(searchField))
						sql+=" and upper(CUSTOMER_SOURCE) "+str;
						//sql+=" and upper(CUSTOMER_SOURCE) "+str;
				    String[] newArray = new String[args.length+1];  
				    for(int cnt=0;cnt<args.length;cnt++){  
				        newArray[cnt] = args[cnt];  
				    }  
				    newArray[args.length]=searchString;
					args=newArray;
				}
				
				if(!"PE".equalsIgnoreCase(menuType) && !"E".equalsIgnoreCase(menuType) && !"PD".equalsIgnoreCase(menuType) && !"T".equalsIgnoreCase(menuType)){
					if("C".equalsIgnoreCase(menuType))
						sql+=" ORDER BY FIRST_NAME";
					else if("P".equalsIgnoreCase(menuType))
						sql+=" ORDER BY INCEPTION_DATE DESC";
					else
						sql+=" ORDER BY QUOTE_NO DESC";
				}else if(!"T".equalsIgnoreCase(menuType) && !"PD".equalsIgnoreCase(menuType) && !"E".equalsIgnoreCase(menuType))
					sql+=" ORDER BY A.QUOTE_NO DESC";
				
			/*	if(!"PE".equalsIgnoreCase(menuType) && !"E".equalsIgnoreCase(menuType) && !"PD".equalsIgnoreCase(menuType)){
					if("C".equalsIgnoreCase(menuType))
						sql+=" ORDER BY FIRST_NAME";
					else
						sql+=" ORDER BY QUOTE_NO DESC";
				}else if(!"P".equals(menuType) && !"E".equalsIgnoreCase(menuType) && !"PE".equalsIgnoreCase(menuType))
					sql+=" ORDER BY QUOTE_NO DESC";*/
			}
			logger.info("args[] ==> "+StringUtils.join(args, ","));
			
			for(int i=0;i<args.length;i++){
				if(args[i]==null)
					args[i]="";
			}
			if(StringUtils.isNotEmpty(sql)){
				reportList=this.mytemplate.queryForList(sql,args);
			}
		}
		catch (Exception e)
		{			
			logger.debug("EXCEPTION @ { " + e + " }");
		}		
		logger.info("Query===>" + sql);		
		logger.info("getReportList() - Exit || Result: " + reportList.size());
				
		return reportList;
	}
	
	
	public List<Map<String, Object>> getUserList(String loginId,String productId,String issuer, String searchBy, String branchCode)	
	{
		List<Map<String, Object>> userList=null;	
		logger.info("args===>" + loginId+","+ productId+","+ issuer);
		try
		{
			if("3".equalsIgnoreCase(productId)||"11".equalsIgnoreCase(productId)){
				if("".equalsIgnoreCase(issuer))
				{
					sql=getQuery(DBConstants.REPORT_USERLIST);
					userList=this.mytemplate.queryForList(sql,new String[]{loginId,productId});	
				}else if("OtherUsers".equalsIgnoreCase(searchBy)){
					sql=getQuery(DBConstants.ISSUER_BROKER_LIST);
					userList=this.mytemplate.queryForList(sql,new String[]{productId,issuer,branchCode});	
				}
				else
				{					
					sql=getQuery(DBConstants.REPORT_USERLIST_ISSUER);
					userList=this.mytemplate.queryForList(sql,new String[]{issuer,productId});	
				}
			}else
			{
				if("".equalsIgnoreCase(issuer))
				{
					sql=getQuery(DBConstants.TRAVEL_REPORT_USERLIST);
					userList=this.mytemplate.queryForList(sql,new String[]{loginId,productId});	
				}else if("OtherUsers".equalsIgnoreCase(searchBy)){
					sql=getQuery("travel.issuer.brokerList");
					userList=this.mytemplate.queryForList(sql,new String[]{productId,issuer,branchCode});	
				}
				else
				{					
					sql=getQuery("travel.userlist.issuer");
					userList=this.mytemplate.queryForList(sql,new String[]{issuer,productId});
				}
			}
		}
		catch (Exception e) 
		{			
			logger.debug("EXCEPTION @ { " + e + " }");
		}		
		logger.info("Query===>" + sql);		
		logger.info("getUserList() - Exit || Result: " + userList.size());
				
		return userList;
	}
	public List<Map<String, Object>> getDeclarationPolicyList(String policyNo)	
	{
		List<Map<String, Object>> declarationList=null;	
		logger.info("args===>" + policyNo);
		try
		{
			sql=getQuery(DBConstants.DECLARATION_POLICY_INDIVIDUAL);
			declarationList=this.mytemplate.queryForList(sql,new String[]{policyNo,policyNo});				
					
		}
		catch (Exception e) 
		{			
			logger.debug("EXCEPTION @ { " + e + " }");
		}		
		logger.info("Query===>" + sql);		
		logger.info("getDeclarationPolicyList() - Exit || Result: " + declarationList.size());
				
		return declarationList;
	}
	public List<Map<String, Object>> getLapsedReason(String branchCode)	
	{
		List<Map<String, Object>> lapsedReason=null;			
		try
		{
			sql=getQuery(DBConstants.LAPSED_REASON);
			lapsedReason=this.mytemplate.queryForList(sql,new String[]{branchCode,branchCode});				
					
		}
		catch (Exception e) 
		{			
			logger.debug("EXCEPTION @ { " + e + " }");
		}		
		logger.info("Query===>" + sql);
		logger.info("args===>" + branchCode);
		logger.info("getLapsedReason() - Exit || Result: " + lapsedReason.size());
				
		return lapsedReason;
	}
	public List<Map<String, Object>> getLapsedQuote(String quoteNo,String productId)
	{
		List<Map<String, Object>> lapsedQuote=null;			
		try
		{
			if("33".equalsIgnoreCase(productId)||"41".equalsIgnoreCase(productId)|| "65".equalsIgnoreCase(productId) || "30".equalsIgnoreCase(productId)){
				sql=getQuery(DBConstants.TRAVEL_QUOTE_DETAIL);
			}
			else{
				sql=getQuery(DBConstants.QUOTE_DETAIL);
			}
			lapsedQuote=this.mytemplate.queryForList(sql,new String[]{quoteNo});				
					
		}
		catch (Exception e) 
		{			
			logger.debug("EXCEPTION @ { " + e + " }");
		}		
		logger.info("Query===>" + sql);	
		logger.info("args===>" + quoteNo);
		logger.info("getLapsedQuote() - Exit || Result: " + lapsedQuote.size());
				
		return lapsedQuote;
	}
	public int updateLabsed(String quoteNo,String remarks,String loginId,String productId)	
	{
		int result=0;			
		try
		{
			if("33".equalsIgnoreCase(productId)||"41".equalsIgnoreCase(productId)|| "65".equalsIgnoreCase(productId) || "30".equalsIgnoreCase(productId)){
				sql=getQuery(DBConstants.TRAVEL_LAPSED_UPDATE);
			}
			else{
				sql=getQuery(DBConstants.LAPSED_UPDATE);
			}
			result=this.mytemplate.update(sql,new String[]{remarks,loginId,quoteNo});					
		}
		catch (Exception e) 
		{			
			logger.debug("EXCEPTION @ { " + e + " }");
		}		
		logger.info("Query===>" + sql);	
		logger.info("args===>" + quoteNo);
		logger.info("getLapsedQuote() - Exit || Result: " + result);
				
		return result;
	}
	public int activeLapsed(String quoteNo, String productId)	
	{
		int result=0;			
		try
			{if("33".equalsIgnoreCase(productId)||"41".equalsIgnoreCase(productId) || "65".equalsIgnoreCase(productId) || "30".equalsIgnoreCase(productId)){
				sql=getQuery(DBConstants.TRAVEL_ACTIVE_LAPSED);
			}
			else{
				sql=getQuery(DBConstants.ACTIVE_LAPSED);
			}
			result=this.mytemplate.update(sql,new String[]{quoteNo});					
		}
		catch (Exception e) 
		{			
			logger.debug("EXCEPTION @ { " + e + " }");
		}		
		logger.info("Query===>" + sql);	
		logger.info("args===>" + quoteNo);
		logger.info("getLapsedQuote() - Exit || Result: " + result);
				
		return result;
	}
	public List<Map<String, Object>> getSearchResult(ReportAction action, String userType)
	{
		logger.info("getSearchResult() - Enter");
		List<Map<String, Object>> result=new ArrayList<Map<String, Object>>();			
		try
		{
			String[] args=new String[]{action.getLoginId(),action.getProductId(),action.getIssuer(),action.getOpenCoverNo(), "%"+action.getSearchValue()+"%"};
			
				if("quoteNo".equalsIgnoreCase(action.getSearchBy())){
					sql=getQuery(DBConstants.SEARCH_QUOTE);
					if("QE".equalsIgnoreCase(action.getMenuType()))
						sql=getQuery(DBConstants.SEARCH_QUOTE_EXISTING);
					else if("QL".equalsIgnoreCase(action.getMenuType()))
						sql=getQuery(DBConstants.SEARCH_QUOTE_LAPSED);
					else if("L".equalsIgnoreCase(action.getMenuType()))
						sql=getQuery(DBConstants.SEARCH_QUOTE_REJECT);
					else if("RA".equalsIgnoreCase(action.getMenuType()))
						sql=getQuery(DBConstants.SEARCH_REFERRAL_APPROVED);
					else if("RU".equalsIgnoreCase(action.getMenuType()))
						sql=getQuery(DBConstants.SEARCH_REFERRAL_UNAPPROVED);
					else if("RR".equalsIgnoreCase(action.getMenuType()))
						sql=getQuery(DBConstants.SEARCH_REFERRAL_REJECT);
					else if("P".equalsIgnoreCase(action.getMenuType())){
						sql=getQuery(DBConstants.SEARCH_PORTFOLIO);
						args=new String[]{action.getLoginId(),action.getProductId(),action.getIssuer(),action.getOpenCoverNo(),action.getSearchBy(), "%"+action.getSearchValue()+"%"};
					}else
						args=new String[]{action.getSearchValue(), action.getLoginId(), action.getProductId(),action.getOpenCoverNo(),"",StringUtils.isBlank(action.getIssuer())?"":action.getIssuer()};
					
				}else if("policyNo".equalsIgnoreCase(action.getSearchBy())){
					if(StringUtils.isEmpty(action.getIssuer()) && StringUtils.isEmpty(action.getLoginId())){
						sql=getQuery(DBConstants.SEARCH_POLICY);
					}else{
						sql=getQuery("GET_PORTFOLIO_ISSUER");
						if(!"BS".equalsIgnoreCase(action.getSearchFrom()) && !"S".equalsIgnoreCase(action.getSearchFrom()) && "11".equals(action.getProductId())){
							sql=getQuery("GET_PORTFOLIO_SEARCH_ISSUER");
							args=new String[]{action.getBranchCode(),action.getProductId(),getOriginalPolicyNo(action.getOpenCoverNo()),"%"+action.getSearchValue()+"%"};
						}else{
							if(!"RSAIssuer".equals(userType))
								sql+=" AND A.application_id='1'";
							if("User".equals(userType))
								sql+=" AND A.LOGIN_ID='"+action.getLoginId()+"'";
							args=new String[]{action.getBranchCode(),action.getProductId(),"%"+action.getSearchValue()+"%"};
						}
						if(StringUtils.isNotBlank(sql)){
							sql=sql+" ORDER BY SUBSTR (A.POLICY_NO, 9, 16) DESC";
						}
					}
				}else if("custName".equalsIgnoreCase(action.getSearchBy())){
					sql=getQuery(DBConstants.SEARCH_CUSTOMER);
					args=new String[]{action.getLoginId(),action.getProductId(),action.getIssuer(),action.getOpenCoverNo(), "%"+action.getSearchValue()+"%","%"+action.getSearchValue()+"%"};
					if("QE".equalsIgnoreCase(action.getMenuType()))
						sql=getQuery(DBConstants.SEARCH_CUSTOMER_QUOTE_EXISTING);
					else if("QL".equalsIgnoreCase(action.getMenuType()))
						sql=getQuery(DBConstants.SEARCH_CUSTOMER_QUOTE_LAPSED);
					else if("L".equalsIgnoreCase(action.getMenuType()))
						sql=getQuery(DBConstants.SEARCH_CUSTOMER_QUOTE_REJECT);
					else if("RA".equalsIgnoreCase(action.getMenuType()))
						sql=getQuery(DBConstants.SEARCH_CUSTOMER_REFERRAL_APPROVED);
					else if("RU".equalsIgnoreCase(action.getMenuType()))
						sql=getQuery(DBConstants.SEARCH_CUSTOMER_REFERRAL_UNAPPROVED);
					else if("RR".equalsIgnoreCase(action.getMenuType()))
						sql=getQuery(DBConstants.SEARCH_CUSTOMER_REFERRAL_REJECT);
					else if("P".equalsIgnoreCase(action.getMenuType())){
						sql=getQuery(DBConstants.SEARCH_PORTFOLIO);
						args=new String[]{action.getLoginId(),action.getProductId(),action.getIssuer(),action.getOpenCoverNo(),action.getSearchBy(), "%"+action.getSearchValue()+"%"};
					}else
						args=new String[]{action.getSearchValue(), action.getLoginId(), action.getProductId(),action.getOpenCoverNo(),"customer",StringUtils.isBlank(action.getIssuer())?"":action.getIssuer()};
				}else if("otherUsers".equalsIgnoreCase(action.getSearchBy())){
					sql=getQuery(DBConstants.SEARCH_QUOTE_OTHERS);
				}
			logger.info("args=>"+StringUtils.join(args,","));
			for (int i = 0; i < args.length; i++) {
				if(args[i]==null){
					args[i]="";
				}
			}
			logger.info("Query=>"+sql);
			result=this.mytemplate.queryForList(sql,args);					
		}
		catch (Exception e) 
		{			
			logger.debug("EXCEPTION @ { " + e + " }");
		}		
		logger.info("getSearchResult() - Exit || Result: " + result.size());
				
		return result;
	}
	public List<Map<String, Object>> getEndTypeList(String productId)	
	{
		logger.info("getEndTypeList() - Enter");
		List<Map<String, Object>> list=null;			
		try{
			sql=getQuery("GET_ENDT_TYPE");
			list=this.mytemplate.queryForList(sql, new Object[]{productId});
			if(list==null || list.size()<=0){
				list=this.mytemplate.queryForList(sql, new Object[]{"03"});
			}
		}
		catch (Exception e){			
			logger.debug("EXCEPTION @ { " + e + " }");
		}		
		logger.info("Query===>" + sql);
		logger.info("getEndTypeList() - Exit");
				
		return list;
	}
	public List<Map<String, Object>> getConstantList(String categoryId, String branchCode)	
	{
		logger.info("getConstantList() - Enter || categoryId: " + categoryId+" branchCode: "+branchCode);
		List<Map<String, Object>> list=null;			
		try{
			sql=getQuery("GET_CONSTANT");
			list=this.mytemplate.queryForList(sql,new String[]{branchCode,categoryId});				
		}
		catch (Exception e){			
			logger.debug("EXCEPTION @ { " + e + " }");
		}		
		logger.info("Query===>" + sql);
		logger.info("getConstantList() - Exit || Result: " + list);
				
		return list;
	}
	public List<Map<String, Object>> getSearchResult(String policyNo, String branchCode, String productId)
	{
		logger.info("getSearchResult() - Enter");
		List<Map<String, Object>> result=new ArrayList<Map<String, Object>>();			
		try
		{
			sql=getQuery("GET_PORTFOLIO_ISSUER") +" ORDER BY SUBSTR (A.POLICY_NO, 9, 16) DESC";
			String[] args=new String[]{branchCode,productId,"%"+policyNo+"%"};
			logger.info("args=>"+StringUtils.join(args,","));
			for (int i = 0; i < args.length; i++) {
				if(args[i]==null){
					args[i]="";
				}
			}
			result=this.mytemplate.queryForList(sql,args);					
		}
		catch (Exception e) 
		{			
			logger.debug("EXCEPTION @ { " + e + " }");
		}		
		logger.info("getSearchResult() - Exit || Result: " + result.size());
				
		return result;
	}
	public String getOriginalPolicyNo(String openCoverNo){
		logger.info("getOriginalPolicyNo() - Enter || openCoverNo: "+openCoverNo);
		openCoverNo=policyDAO.getValue("GET_ORIGINAL_POLNO", new String[]{openCoverNo});
		logger.info("getOriginalPolicyNo() - Exit || Result: "+openCoverNo);
		return openCoverNo;
	}
	public List<Map<String, Object>> getEndtPolicyInfo(String branchCode, String policyNo)	
	{
		logger.info("getEndtPolicyInfo() - Enter || branchCode: "+branchCode+" policyNo: "+policyNo);
		List<Map<String, Object>> info=new ArrayList<Map<String, Object>>();;			
		try{
			sql=getQuery("GET_ENDT_POL");
			info=this.mytemplate.queryForList(sql,new String[]{branchCode, policyNo});				
		}catch (Exception e){
			logger.debug("EXCEPTION @ { " + e + " }");
		}		
		logger.info("Query===>" + sql);	
		logger.info("getEndtPolicyInfo() - Exit || Result: " + info.size());
				
		return info;
	}
	public List<Map<String, Object>> getPolicyEndtTypeList(String endtType)	
	{
		logger.info("getPolicyEndtTypeList() - Enter || endtType: "+endtType);
		List<Map<String, Object>> info=new ArrayList<Map<String, Object>>();;			
		try{
			info=this.mytemplate.queryForList(getQuery("GET_POL_ENDT_LIST").replace("?",endtType));
		}catch (Exception e){			
			logger.debug("EXCEPTION @ { " + e + " }");
		}		
		logger.info("Query===>" + sql);	
		logger.info("getPolicyEndtTypeList() - Exit || Result: " + info.size());
				
		return info;
	}
	public int[] getTRPortfolio(String loginId,String productId,String issuer,String openCoverNo)
	{
		logger.info("getTRPortfolio() - Enter");
		int portfolio=0;
		int portfolioAmount=0;
		Object[] obj=null;
		try{

		    LocalizedTextProvider ltp = ActionContext.getContext().getInstance(LocalizedTextProvider.class);
			if("3".equals(productId) || "11".equals(productId)){
			    sql=ltp.findDefaultText(DBConstants.REPORT_CHART_PORTFOLIO,Locale.ENGLISH , new Object[]{(StringUtils.isBlank(issuer)?" A.LOGIN_ID ='"+loginId+"' AND":"")});
				obj=new Object[]{productId,(StringUtils.isBlank(issuer)?"1":issuer),openCoverNo};
				logger.info("SQL=>"+sql);
				logger.info("obj=>"+StringUtils.join(obj,","));
				portfolio=this.mytemplate.queryForObject(sql, Integer.class,obj);
				logger.info("Result=>"+portfolio);
				sql=ltp.findDefaultText(DBConstants.REPORT_CHART_PORTFOLIO_AMOUNT,Locale.ENGLISH , new Object[]{(StringUtils.isBlank(issuer)?" A.LOGIN_ID ='"+loginId+"' AND":"")});
				logger.info("SQL=>"+sql);
				logger.info("obj=>"+StringUtils.join(obj,","));
				portfolioAmount=this.mytemplate.queryForObject(sql, Integer.class,obj);
				logger.info("Result=>"+portfolioAmount);
			}else
			{
				sql=ltp.findDefaultText(DBConstants.TRAVEL_REPORT_CHART_PORTFOLIO,Locale.ENGLISH , new Object[]{(StringUtils.isBlank(issuer)?" A.LOGIN_ID ='"+loginId+"' AND":"")});
				obj=new Object[]{productId,(StringUtils.isBlank(issuer)?"1":issuer)};
				logger.info("SQL=>"+sql);
				logger.info("obj=>"+StringUtils.join(obj,","));
				portfolio=this.mytemplate.queryForObject(sql, Integer.class,obj);
				logger.info("Result=>"+portfolio);
				sql=ltp.findDefaultText(DBConstants.TRAVEL_REPORT_CHART_PORTFOLIO_AMOUNT,Locale.ENGLISH , new Object[]{(StringUtils.isBlank(issuer)?" A.LOGIN_ID ='"+loginId+"' AND":"")});
				logger.info("SQL=>"+sql);
				logger.info("obj=>"+StringUtils.join(obj,","));
				portfolioAmount=this.mytemplate.queryForObject(sql, Integer.class,obj);
				logger.info("Result=>"+portfolioAmount);
			}
		}catch (Exception e){			
			logger.debug("EXCEPTION @ { " + e + " }");
		}
		logger.info("getTRPortfolio() - Exit");
				
		return new int[]{portfolio,portfolioAmount};
	}
	public int[] getTRExisting(String loginId,String productId,String issuer,String openCoverNo)
	{
		logger.info("getTRExisting() - Enter");
		int existing =0;
		int existingAmpont =0;
		Object[] obj=null;
		try{

		    LocalizedTextProvider ltp = ActionContext.getContext().getInstance(LocalizedTextProvider.class);
			if("3".equals(productId) || "11".equals(productId)){
				sql=ltp.findDefaultText(DBConstants.REPORT_QUOTEREGISTER_CHART_EXISTING,Locale.ENGLISH , new Object[]{(StringUtils.isBlank(issuer)?" A.LOGIN_ID ='"+loginId+"' AND":"")});
				logger.info("Query=>" + sql);
				obj=new Object[]{productId,(StringUtils.isBlank(issuer)?"1":issuer),openCoverNo};
				logger.info("obj[]=>"+StringUtils.join(obj,","));
				existing=this.mytemplate.queryForObject(sql, Integer.class,obj);
				logger.info("Result=>" + existing);
				sql=ltp.findDefaultText(DBConstants.REPORT_CHART_EXISTING_AMOUNT,Locale.ENGLISH , new Object[]{(StringUtils.isBlank(issuer)?" A.LOGIN_ID ='"+loginId+"' AND":"")});
				logger.info("Query=>" + sql);
				logger.info("obj[]=>"+StringUtils.join(obj,","));
				existingAmpont=this.mytemplate.queryForObject(sql, Integer.class,obj);
				logger.info("Result=>" + existingAmpont);
			}else
			{
				sql=ltp.findDefaultText(DBConstants.TRAVEL_QUOTEREGISTER_CHART_EXISTING,Locale.ENGLISH , new Object[]{(StringUtils.isBlank(issuer)?" A.LOGIN_ID ='"+loginId+"' AND":"")});
				logger.info("Query=>" + sql);
				obj=new Object[]{productId,(StringUtils.isBlank(issuer)?"1":issuer)};
				logger.info("obj[]=>"+StringUtils.join(obj,","));
				existing=this.mytemplate.queryForObject(sql, Integer.class,obj);
				logger.info("Result=>" + existing);
				sql=ltp.findDefaultText(DBConstants.TRAVEL_QUOTEREGISTER_CHART_EXISTING_AMOUNT,Locale.ENGLISH , new Object[]{(StringUtils.isBlank(issuer)?" A.LOGIN_ID ='"+loginId+"' AND":"")});
				logger.info("Query=>" + sql);
				logger.info("obj[]=>"+StringUtils.join(obj,","));
				existingAmpont=this.mytemplate.queryForObject(sql, Integer.class,obj);
				logger.info("Result=>" + existingAmpont);
			}
			
		}catch (Exception e){			
			logger.debug("EXCEPTION @ { " + e + " }");
		}
		logger.info("getTRExisting() - Exit");
				
		return new int[]{existing,existingAmpont};
	}
	public int[] getTRUnapproved(String loginId,String productId,String issuer,String openCoverNo)
	{
		logger.info("getTRUnapproved() - Enter");
		int unapproved =0;
		int unapprovedAmount =0;
		String[] obj=null;
		try{

		    LocalizedTextProvider ltp = ActionContext.getContext().getInstance(LocalizedTextProvider.class);
			if("3".equals(productId) || "11".equals(productId)){
				sql=ltp.findDefaultText(DBConstants.REPORT_REFERRAL_CHART_UNAPPROVED,Locale.ENGLISH , new Object[]{(StringUtils.isBlank(issuer)?" A.LOGIN_ID ='"+loginId+"' AND":"")});
				obj=new String[]{productId,(StringUtils.isBlank(issuer)?"1":issuer),openCoverNo};
				logger.info("Query=>" + sql);
				logger.info("Args[]=>" + StringUtils.join(obj,","));
				unapproved=this.mytemplate.queryForObject(sql, Integer.class,obj);
				logger.info("Result=>" + unapproved);
				sql=ltp.findDefaultText(DBConstants.REPORT_REFERRAL_CHART_UNAPPROVED_AMOUNT,Locale.ENGLISH , new Object[]{(StringUtils.isBlank(issuer)?" A.LOGIN_ID ='"+loginId+"' AND":"")});
				logger.info("Query=>" + sql);
				logger.info("Args[]=>" + StringUtils.join(obj,","));
				unapprovedAmount=this.mytemplate.queryForObject(sql, Integer.class,obj);
				logger.info("Result=>" + unapprovedAmount);
			}else
			{
				sql=ltp.findDefaultText(DBConstants.TRAVEL_REPORT_REFERRAL_CHART_UNAPPROVED,Locale.ENGLISH , new Object[]{(StringUtils.isBlank(issuer)?" A.LOGIN_ID ='"+loginId+"' AND":"")});
				logger.info("Query=>" + sql);
				obj=new String[]{productId,(StringUtils.isBlank(issuer)?"1":issuer)};
				logger.info("Args[]=>" + StringUtils.join(obj,","));
				unapproved=this.mytemplate.queryForObject(sql, Integer.class,obj);
				logger.info("Result=>" + unapproved);
				sql=ltp.findDefaultText(DBConstants.TRAVEL_REPORT_REFERRAL_CHART_UNAPPROVED_AMOUNT,Locale.ENGLISH , new Object[]{(StringUtils.isBlank(issuer)?" A.LOGIN_ID ='"+loginId+"' AND":"")});
				logger.info("Query=>" + sql);
				logger.info("Args[]=>" + StringUtils.join(obj,","));
				unapprovedAmount=this.mytemplate.queryForObject(sql, Integer.class,obj);
				logger.info("Result=>" + unapprovedAmount);
			}
		}catch (Exception e){			
			logger.debug("EXCEPTION @ { " + e + " }");
		}
		int[] trUnapproved={unapproved,unapprovedAmount};
		logger.info("Query===>" + sql);	
		logger.info("getTRUnapproved() - Exit");
				
		return trUnapproved;
	}
	public int[] getTRApproved(String loginId,String productId,String issuer,String openCoverNo)
	{
		logger.info("getTRApproved() - Enter");
		int approved =0;
		int approvedAmount =0;
		String[] obj=null;
		try{

		    LocalizedTextProvider ltp = ActionContext.getContext().getInstance(LocalizedTextProvider.class);
			if("3".equals(productId) || "11".equals(productId)){
				sql=ltp.findDefaultText(DBConstants.REPORT_REFERRAL_CHART_APPROVED,Locale.ENGLISH , new Object[]{(StringUtils.isBlank(issuer)?" A.LOGIN_ID ='"+loginId+"' AND":"")});
				obj=new String[]{productId,(StringUtils.isBlank(issuer)?"1":issuer),openCoverNo};
				logger.info("Query=>" + sql);
				logger.info("Args[]=>" + StringUtils.join(obj,","));
				approved=this.mytemplate.queryForObject(sql, Integer.class,obj);
				logger.info("Result=>" + approved);
				sql=ltp.findDefaultText(DBConstants.REPORT_REFERRAL_CHART_APPROVED_AMOUNT,Locale.ENGLISH , new Object[]{(StringUtils.isBlank(issuer)?" A.LOGIN_ID ='"+loginId+"' AND":"")});
				logger.info("Query=>" + sql);
				logger.info("Args[]=>" + StringUtils.join(obj,","));
				approvedAmount=this.mytemplate.queryForObject(sql, Integer.class,obj);
				logger.info("Result=>" + approvedAmount);
			}else
			{
				sql=ltp.findDefaultText(DBConstants.TRAVEL_REPORT_REFERRAL_CHART_APPROVED,Locale.ENGLISH , new Object[]{(StringUtils.isBlank(issuer)?" A.LOGIN_ID ='"+loginId+"' AND":"")});
				obj=new String[]{productId,(StringUtils.isBlank(issuer)?"1":issuer)};
				logger.info("Query=>" + sql);
				approved=this.mytemplate.queryForObject(sql, Integer.class,obj);
				logger.info("Result=>" + approved);
				sql=ltp.findDefaultText(DBConstants.TRAVEL_REPORT_REFERRAL_CHART_APPROVED_AMOUNT,Locale.ENGLISH , new Object[]{(StringUtils.isBlank(issuer)?" A.LOGIN_ID ='"+loginId+"' AND":"")});
				logger.info("Query=>" + sql);
				logger.info("Args[]=>" + StringUtils.join(obj,","));
				approvedAmount=this.mytemplate.queryForObject(sql, Integer.class,obj);
				logger.info("Result=>" + approvedAmount);
			}
		}catch (Exception e){			
			logger.debug("EXCEPTION @ { " + e + " }");
		}
		int[] trApproved={approved,approvedAmount};
		logger.info("getTRApproved() - Exit");
				
		return trApproved;
	}
	public int[] getTRReject(String loginId,String productId,String issuer,String openCoverNo)
	{
		logger.info("getTRReject() - Enter");
		int reject =0;
		int rejectAmount =0;
		String[] obj=null;
		try{

		    LocalizedTextProvider ltp = ActionContext.getContext().getInstance(LocalizedTextProvider.class);
			if("3".equals(productId) || "11".equals(productId)){
				obj=new String[]{productId,(StringUtils.isBlank(issuer)?"1":issuer),openCoverNo};
				sql=ltp.findDefaultText(DBConstants.REPORT_REFERRAL_CHART_REJECT,Locale.ENGLISH , new Object[]{(StringUtils.isBlank(issuer)?" A.LOGIN_ID ='"+loginId+"' AND":"")});
				logger.info("Query=>" + sql);
				logger.info("Args[]=>" + StringUtils.join(obj,","));
				reject=this.mytemplate.queryForObject(sql, Integer.class,obj);
				logger.info("Result=>" + reject);
				sql=ltp.findDefaultText(DBConstants.REPORT_REFERRAL_CHART_REJECT_AMOUNT,Locale.ENGLISH , new Object[]{(StringUtils.isBlank(issuer)?" A.LOGIN_ID ='"+loginId+"' AND":"")});
				logger.info("Query=>" + sql);
				logger.info("Args[]=>" + StringUtils.join(obj,","));
				rejectAmount=this.mytemplate.queryForObject(sql, Integer.class,obj);
				logger.info("Result=>" + rejectAmount);
			}else{
				sql=ltp.findDefaultText(DBConstants.TRAVEL_REPORT_REFERRAL_CHART_REJECT,Locale.ENGLISH , new Object[]{(StringUtils.isBlank(issuer)?" A.LOGIN_ID ='"+loginId+"' AND":"")});
				obj=new String[]{productId,(StringUtils.isBlank(issuer)?"1":issuer)};
				logger.info("Query=>" + sql);
				logger.info("Args[]=>" + StringUtils.join(obj,","));
				reject=this.mytemplate.queryForObject(sql, Integer.class,obj);
				logger.info("Result=>" + reject);
				sql=ltp.findDefaultText(DBConstants.TRAVEL_REPORT_REFERRAL_CHART_REJECT_AMOUNT,Locale.ENGLISH , new Object[]{(StringUtils.isBlank(issuer)?" A.LOGIN_ID ='"+loginId+"' AND":"")});
				logger.info("Query=>" + sql);
				logger.info("Args[]=>" + StringUtils.join(obj,","));
				rejectAmount=this.mytemplate.queryForObject(sql, Integer.class,obj);
				logger.info("Result=>" + rejectAmount);
			}
		}catch (Exception e){			
			logger.debug("EXCEPTION @ { " + e + " }");
		}
		int[] trReject={reject, rejectAmount};
		logger.info("getTRReject() - Exit");
				
		return trReject;
	}
	public int[] getTRLapsed(String loginId,String productId,String issuer,String openCoverNo)
	{
		logger.info("getTRLapsed() - Enter");
		int lapsed =0;
		int lapsedAmount =0;
		Object[] obj=null;
		try{

		    LocalizedTextProvider ltp = ActionContext.getContext().getInstance(LocalizedTextProvider.class);
			if("3".equals(productId) || "11".equals(productId)){
				sql=ltp.findDefaultText(DBConstants.REPORT_CHART_QUOTEREGISTER_LAPSED,Locale.ENGLISH , new Object[]{(StringUtils.isBlank(issuer)?" A.LOGIN_ID ='"+loginId+"' AND":"")});
				logger.info("Query=>" + sql);
				obj=new Object[]{productId,(StringUtils.isBlank(issuer)?"1":issuer),openCoverNo};
				logger.info("obj=>" + StringUtils.join(obj,","));
				lapsed=this.mytemplate.queryForObject(sql, Integer.class,obj);
				logger.info("Result=>" + lapsed);
				sql=ltp.findDefaultText(DBConstants.REPORT_CHART_QUOTEREGISTER_LAPSED_AMOUNT,Locale.ENGLISH , new Object[]{(StringUtils.isBlank(issuer)?" A.LOGIN_ID ='"+loginId+"' AND":"")});
				logger.info("Query=>" + sql);
				logger.info("obj=>" + StringUtils.join(obj,","));
				lapsedAmount=this.mytemplate.queryForObject(sql, Integer.class,obj);
				logger.info("Result=>" + lapsedAmount);
			}else
			{
				sql=ltp.findDefaultText(DBConstants.TRAVEL_REPORT_CHART_QUOTEREGISTER_LAPSED,Locale.ENGLISH , new Object[]{(StringUtils.isBlank(issuer)?" A.LOGIN_ID ='"+loginId+"' AND":"")});
				obj=new Object[]{productId,(StringUtils.isBlank(issuer)?"1":issuer)};
				logger.info("Query=>" + sql);
				logger.info("obj=>" + StringUtils.join(obj,","));
				lapsed=this.mytemplate.queryForObject(sql, Integer.class,obj);
				logger.info("Result=>" + lapsed);
				sql=ltp.findDefaultText(DBConstants.TRAVEL_REPORT_CHART_QUOTEREGISTER_LAPSED_AMOUNT,Locale.ENGLISH , new Object[]{(StringUtils.isBlank(issuer)?" A.LOGIN_ID ='"+loginId+"' AND":"")});
				logger.info("Query=>" + sql);
				logger.info("obj=>" + StringUtils.join(obj,","));
				lapsedAmount=this.mytemplate.queryForObject(sql, Integer.class,obj);
				logger.info("Result=>" + lapsedAmount);
			}
		}catch (Exception e){
			logger.debug("EXCEPTION @ { " + e + " }");
		}
		logger.info("getTRLapsed() - Exit");
		
		return new int[]{lapsed,lapsedAmount};
	}
	public List getSingleInfo(String option, String[] args) {
		logger.info("getSingleInfo - Enter || "+option+" args: "+ StringUtils.join(args, ","));
		List result=null;					
		try{
			sql=getQuery(option);
			result=this.mytemplate.queryForList(sql,args);			
		}
		catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}		
		logger.info("Query==>" + sql);
		logger.info("getSingleInfo() - Exit || Result: "+result.size() );
				
		return result;
	}
	
	public String getEndtQuoteNo(String quoteNo) {
		logger.info("getEndtQuoteNo - Enter || "+quoteNo);
		String endtQuoteNo="";					
		try{
			sql=getQuery("GET_ENDT_QUOTENO");
			logger.info("Query==>" + sql);
			List list= this.mytemplate.queryForList(sql,new Object[]{quoteNo});
			if(list!=null && list.size()>0){
				Map map=(Map)list.get(0);
				endtQuoteNo=map.get("QUOTE_NO")==null?"":map.get("QUOTE_NO").toString();
			}
			if (StringUtils.isBlank(endtQuoteNo))
				endtQuoteNo=quoteNo;
		}
		catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}	
		return endtQuoteNo;
	}

	public Map<String ,Object> getQuoteInfo(String quoteNo) {
		logger.info("getQuoteInfo - Enter || "+quoteNo);
		Map<String ,Object> quoteInfo=null;
		try{
			sql="select REFERENCE_NUMBER, application_no from webservice_marine_quote where quote_no=?";
			logger.info("Query==>" + sql);
			quoteInfo= this.mytemplate.queryForMap(sql,new Object[]{quoteNo});
		}
		catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}	
		return quoteInfo;
	}
	
	public void getUpdateEndtStatus(String quoteNo , String cancelRemarks) {
		logger.info("getUpdateEndtStatus - Enter || "+quoteNo);
		try{
			String sql="UPDATE POSITION_MASTER SET STATUS='D', PDF_PRE_SHOW_STATUS='YES',CANCEL_REMARKS=?  WHERE QUOTE_NO=?";
			logger.info("Query==>" + sql);
			this.mytemplate.update(sql,new Object[]{cancelRemarks, quoteNo});
			sql = "UPDATE  POSITION_MASTER SET STATUS='D', PDF_BROKER_STATUS='999' WHERE POLICY_NO LIKE (SELECT REGEXP_SUBSTR(POLICY_NO,'[^-]+',1,1) FROM POSITION_MASTER WHERE QUOTE_NO=?) ||'%'";
			logger.info("Query==>" + sql);
			this.mytemplate.update(sql,new Object[]{quoteNo});
		}
		catch (Exception e) {
			logger.debug("EXCEPTION @ { " + e + " }");
		}
	}
	public List getLcUploadDetails(String policyNo) {
		logger.info("getLcUploadDetails - Enter");
		List result=null;					
		try{
			sql=getQuery("GET_LC_UPLOAD_DETAILS");
			result=this.mytemplate.queryForList(sql,new Object[]{policyNo});			
		}
		catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}		
		logger.info("Query==>" + sql);
		logger.info("getLcUploadDetails() - Exit || Result: "+result.size() );
				
		return result;
	}
	public List getLcUploadPolicy(String policyNo) {
		logger.info("getLcUploadPolicy - Enter");
		List result=null;					
		try{
			sql=getQuery("GET_LC_UPLOAD_POLICY_DTLS");
			result=this.mytemplate.queryForList(sql,new Object[]{policyNo});			
		}
		catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}		
		logger.info("Query==>" + sql);
		logger.info("getLcUploadPolicy() - Exit || Result: "+result.size() );
				
		return result;
	}
	public List getLcUploadPloDtls(String policyNo) {
		logger.info("getLcUpoladPolDtls - Enter");
		List result=null;					
		try{
			sql=getQuery("GET_LC_UPLOAD_POL_DTLS");
			result=this.mytemplate.queryForList(sql,new Object[]{policyNo});			
		}
		catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}		
		logger.info("Query==>" + sql);
		logger.info("getLcUploadPolDtls() - Exit || Result: "+result.size() );
				
		return result;
	}
	public void insertLcFileDtls(String policyNo,String loginId,List<String> uploadFileName,File fileToCreate,List<String> lcdocremarks,String lcFilePath,List<File> upload){
		logger.info("getUpdateEndtStatus - Enter || ");
		try{
			sql=getQuery("LC_FILE_INSERT");
			logger.info("Query==>" + sql);
			for(int i=0;i<uploadFileName.size();i++){
				Map uploadId = this.mytemplate.queryForMap("select LC_FILE_UPLOAD_SEQ.nextval uploadid from dual");
				String lcUploadId = uploadId.get("uploadid")==null?"":uploadId.get("uploadid").toString();
				fileToCreate = new File(lcFilePath+(lcUploadId)+"."+FilenameUtils.getExtension(uploadFileName.get(i)));
				FileUtils.copyFile(upload.get(i), fileToCreate);
				this.mytemplate.update(sql,new Object[]{policyNo,fileToCreate.getName(),loginId,uploadFileName.get(i),lcdocremarks.get(i),"Y",lcUploadId});
			}
			
		}
		catch (Exception e) {
			logger.debug("EXCEPTION @ { " + e + " }");
		}
	}
	public List<Map<String, Object>> getLcFileList(String policyNo)	
	{
		logger.info("getLcFileList() - Enter || endtType: ");
		List<Map<String, Object>> info=new ArrayList<Map<String, Object>>();;			
		try{
			//policyNo = "01/3112/327/2015/2231";
			sql=getQuery("GET_LC_FILE_LIST");
			info=this.mytemplate.queryForList(sql,new Object[]{policyNo});
		}catch (Exception e){			
			logger.debug("EXCEPTION @ { " + e + " }");
		}		
		logger.info("Query===>" + sql);	
		logger.info("getLcFileList() - Exit || Result: " + info.size());
				
		return info;
	}
	public void deleteLcFile(String policyNo,String uploadId){
		logger.info("getUpdateEndtStatus - Enter || ");
		try{
			//policyNo = "01/3112/327/2015/2231";
			sql=getQuery("LC_FILE_DELETE");
			logger.info("Query==>" + sql);
			this.mytemplate.update(sql,new Object[]{uploadId.trim()});
		}
		catch (Exception e) {
			logger.debug("EXCEPTION @ { " + e + " }");
		}
	}
	public List<Map<String, Object>> getAllDocuments(String policyNo) {
		List<Map<String, Object>> list=null;
		try{
			String[] policyNumber = policyNo.split("-");
			policyNo = policyNumber[0];
			String query=getQuery("GET_DOCUMENTS_LIST");
			list=this.mytemplate.queryForList(query,new Object[]{policyNo});
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	public boolean getSalesYn(String policyNo,String branchCode,String type) {
		boolean result=false;
		String salesYn="";
		try{
			
			String query=getQuery("GET_VAT_SALES");
			salesYn=(String) this.mytemplate.queryForObject(query,new Object[]{policyNo,branchCode,type},String.class);
			result="Sales".equalsIgnoreCase(salesYn);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}


	public void updateVatRegNo(String custMissippiCode, String vatRegNo) {
		try{
			Object[] obj=new Object[]{vatRegNo,custMissippiCode};
			String query=getQuery("UPD_VAT_PI");
			this.mytemplate.update(query,obj);
			query=getQuery("UPD_VAT_WMQ");
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
