package com.maan.adminnew.report;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;

import com.maan.common.LogUtil;
import com.maan.common.MyJdbcTemplate;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.LocalizedTextProvider;

public class ReportDAO extends MyJdbcTemplate
{
	private static final Logger logger = LogUtil.getLogger(ReportDAO.class);
	private String query="";
	private Object[] obj=null;
	
	public Map<Object, Object> schedule(final String broker){
		Map<Object, Object>map=null;
    	List <Map<String, Object>> regQuote=null;
    	obj=new Object[]{broker};
    	try{
    		query=getQuery("GET_QUOTE_REGISTER_LIST");
			logger.info("Query===>" + query);
			logger.info("args===>" + StringUtils.join(obj, ", "));
			regQuote=this.mytemplate.queryForList(query,obj);
		}catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}
    	return map;
    }
	public List<Map<String, Object>> getPolicyReport(final Object[] obj){

    	List <Map<String, Object>> policyReport=null;
    	Object obj1[]=null;
    	try{
    		String broker=obj[3].toString();
    		broker="'"+broker.replaceAll(",", "','")+"'";
    		
    		if("11".equals(obj[0]) ){
    			if("reportBR".equals(obj[4])) {
    				//query = LocalizedTextUtil.findDefaultText("GET_POLICY_REPORT_LIST", Locale.ENGLISH, new String[]{broker});
    				query="select * from table(broker_count(?,?,'BROKER',11,'',?))";
    				if(!obj[3].toString().equalsIgnoreCase("ALL")) {
    					query=query+" where login_id in ('"+obj[3].toString().replaceAll(", ", ",").replaceAll(" ,", ",").replaceAll(",", "','")+"')";
    				}
    				obj1=new Object[]{obj[1],obj[2],obj[5]};
    			}
    			else {
    				//query = "SELECT a.company_name, b.RSA_BRANCH_CODE, c.missippi_opencover_no, NVL (d.first_name, d.company_name) customer_name, b.login_id, e.open_cover_no, COUNT (e.status) policy, TO_NUMBER (NVL (SUM (e.premium + e.excess_premium), 0)) premium, NVL (SUM (e.COMMISSION), 0) COMMISSION, NVL (SUM (e.PRO_COMMISSION), 0) PRO_COMMISSION FROM   position_master e, open_cover_master c, login_master b, broker_company_master a, personal_info d WHERE e.status = 'P' AND b.agency_code = d.agency_code AND c.missippi_opencover_no = e.open_cover_no AND b.login_id = e.login_id AND b.agency_code = a.agency_code and e.APPLICATION_ID in ("+broker+") AND e.product_id = ? AND e.INCEPTION_DATE BETWEEN TO_DATE (?, 'DD/MM/YYYY') AND  TO_DATE (?, 'DD/MM/YYYY') + 1 GROUP BY   e.open_cover_no,  c.missippi_opencover_no, b.login_id, a.company_name, NVL (d.first_name, d.company_name),b.RSA_BRANCH_CODE ORDER BY open_cover_no";
    				query="select * from table(broker_count(?,?,'ISSUER',11,?,?))";
    				obj1=new Object[]{obj[1],obj[2],obj[3],obj[5]};
    			}
    				
    			
    		}else if("3".equals(obj[0])){
    			if("reportBR".equals(obj[4])) {
    				query="select * from table(broker_count(?,?,'BROKER',3,'',?))";
    				if(!obj[3].toString().equalsIgnoreCase("ALL")){
    					query=query+" where LOGIN_ID in ('"+obj[3].toString().replaceAll(", ", ",").replaceAll(" ,", ",").replaceAll(",", "','")+"')";
    				}
    				obj1=new Object[]{obj[1],obj[2],obj[5]};
    				//query ="select company_name,customer_name,login_id,policy,premium,COMMISSION,PRO_COMMISSION,rsa_branch_code, (select branch_name from rsa_branch_details where branch_code=rsa_branch_code) rsa_branch_name from (SELECT   a.company_name,NVL (d.first_name, d.company_name) customer_name,b.login_id,COUNT (e.status) policy, TO_NUMBER (NVL (SUM (e.premium + e.excess_premium), 0)) premium, NVL (SUM (e.COMMISSION), 0) COMMISSION,NVL (SUM (e.PRO_COMMISSION), 0) PRO_COMMISSION,a.rsa_branch_code FROM   position_master e,login_master b,broker_company_master a, personal_info d WHERE   e.status = 'P' AND b.agency_code = d.agency_code AND b.login_id IN ("+broker+") AND b.login_id = e.login_id AND b.agency_code = a.agency_code  AND e.product_id = ? AND e.INCEPTION_DATE BETWEEN TO_DATE (?, 'DD/MM/YYYY') AND  TO_DATE (?, 'DD/MM/YYYY') + 1 GROUP BY   b.login_id, a.company_name, NVL (d.first_name, d.company_name),a.rsa_branch_code)";
    				/*	"SELECT a.company_name, NVL (d.first_name, d.company_name) customer_name,  b.login_id,  COUNT (e.status) policy, TO_NUMBER (NVL (SUM (e.premium + e.excess_premium), 0)) premium,  NVL (SUM (e.COMMISSION), 0) COMMISSION, NVL (SUM (e.PRO_COMMISSION), 0) PRO_COMMISSION FROM position_master e,"+
    				" login_master b, broker_company_master a, personal_info d WHERE e.status = 'P'  AND b.agency_code = d.agency_code and  b.login_id in("+broker+") AND b.login_id = e.login_id AND b.agency_code = a.agency_code  AND e.product_id =? and e.INCEPTION_DATE between to_date(?,'DD/MM/YYYY') and to_date(?,'DD/MM/YYYY')+1 group by b.login_id,a.company_name,nvl(d.first_name,d.company_name)";*/
    			}
    			else{
    				query="select * from table(broker_count(?,?,'ISSUER',3,?,?))";
    				//query ="select company_name,customer_name,login_id,policy,premium,COMMISSION,PRO_COMMISSION,rsa_branch_code, (select branch_name from rsa_branch_details where branch_code=rsa_branch_code) rsa_branch_name from (SELECT   a.company_name,NVL (d.first_name, d.company_name) customer_name,b.login_id,COUNT (e.status) policy, TO_NUMBER (NVL (SUM (e.premium + e.excess_premium), 0)) premium, NVL (SUM (e.COMMISSION), 0) COMMISSION,NVL (SUM (e.PRO_COMMISSION), 0) PRO_COMMISSION,a.rsa_branch_code FROM   position_master e,login_master b,broker_company_master a, personal_info d WHERE   e.status = 'P' AND b.agency_code = d.agency_code AND e.APPLICATION_ID IN ("+broker+") AND b.login_id = e.login_id AND b.agency_code = a.agency_code  AND e.product_id = ? AND e.INCEPTION_DATE BETWEEN TO_DATE (?, 'DD/MM/YYYY') AND  TO_DATE (?, 'DD/MM/YYYY') + 1 GROUP BY   b.login_id, a.company_name, NVL (d.first_name, d.company_name),a.rsa_branch_code)";
    				//query ="SELECT a.company_name, NVL (d.first_name, d.company_name) customer_name, b.login_id,  COUNT (e.status) policy, TO_NUMBER (NVL (SUM (e.premium + e.excess_premium), 0)) premium, NVL (SUM (e.COMMISSION), 0) COMMISSION, NVL (SUM (e.PRO_COMMISSION), 0) PRO_COMMISSION FROM   position_master e, open_cover_master c, login_master b, broker_company_master a, personal_info d WHERE e.status = 'P' AND b.agency_code = d.agency_code AND b.login_id = e.login_id AND b.agency_code = a.agency_code and e.APPLICATION_ID in ("+broker+") AND e.product_id = ? AND e.INCEPTION_DATE BETWEEN TO_DATE (?, 'DD/MM/YYYY') AND  TO_DATE (?, 'DD/MM/YYYY') + 1 GROUP BY b.login_id, a.company_name, NVL (d.first_name, d.company_name)";
    				obj1=new Object[]{obj[1],obj[2],obj[3],obj[5]};
    			}
    		}else{
    			obj1=new Object[]{obj[1],obj[2],obj[0]};
    			if("reportBR".equals(obj[4])){

    			    LocalizedTextProvider ltp = ActionContext.getContext().getInstance(LocalizedTextProvider.class);
    				query = ltp.findDefaultText("GET_POLICY_REPORT_LIST_OTHERS", Locale.ENGLISH, new String[]{broker});
    			}else
    				query ="select pi.LOGIN_ID, (select USERNAME from login_master llm where llm.login_id=pi.LOGIN_ID) company_name,first_name customer_name,POLICY_NO,(select count(*) from home_position_master hp where hp.policy_no=hpm.policy_no) policy,premium,commission from home_position_master hpm,personal_info pi where hpm.CUSTOMER_ID=pi.CUSTOMER_ID AND (hpm.INCEPTION_DATE BETWEEN TO_DATE (?, 'DD/MM/YYYY') AND TO_DATE (?, 'DD/MM/YYYY') + 1) and hpm.APPLICATION_ID in ("+broker+") and hpm.status='P'  and hpm.product_id=?";
    		}
			logger.info("Query===>" + query);
			logger.info("args===>" + StringUtils.join(obj1, ", "));
			policyReport=this.mytemplate.queryForList(query,obj1);
    	}catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}
    	return policyReport;
    
		/*
    	List <Map<String, Object>> policyReport=null;
    	Object obj1[]=null;
    	try{
    		String broker=obj[3].toString();
    		broker="'"+broker.replaceAll(",", "','")+"'";
    		
    		if("11".equals(obj[0]) ){
    			if("reportBR".equals(obj[4]))
    				query = LocalizedTextUtil.findDefaultText("GET_POLICY_REPORT_LIST", Locale.ENGLISH, new String[]{broker});
    			else
    				query = "SELECT a.company_name, c.missippi_opencover_no, NVL (d.first_name, d.company_name) customer_name, b.login_id, e.open_cover_no, COUNT (e.status) policy, TO_NUMBER (NVL (SUM (e.premium + e.excess_premium), 0)) premium, NVL (SUM (e.COMMISSION), 0) COMMISSION, NVL (SUM (e.PRO_COMMISSION), 0) PRO_COMMISSION FROM   position_master e, open_cover_master c, login_master b, broker_company_master a, personal_info d WHERE e.status = 'P' AND b.agency_code = d.agency_code AND c.missippi_opencover_no = e.open_cover_no AND b.login_id = e.login_id AND b.agency_code = a.agency_code and e.APPLICATION_ID in ("+broker+") AND e.product_id = ? AND e.INCEPTION_DATE BETWEEN TO_DATE (?, 'DD/MM/YYYY') AND  TO_DATE (?, 'DD/MM/YYYY') + 1 GROUP BY   e.open_cover_no,  c.missippi_opencover_no, b.login_id, a.company_name, NVL (d.first_name, d.company_name) ORDER BY open_cover_no";
    			obj1=new Object[]{obj[0],obj[1],obj[2]};
    		}else if("3".equals(obj[0])){
    			if("reportBR".equals(obj[4]))
    				query ="SELECT a.company_name, NVL (d.first_name, d.company_name) customer_name,  b.login_id,  COUNT (e.status) policy, TO_NUMBER (NVL (SUM (e.premium + e.excess_premium), 0)) premium,  NVL (SUM (e.COMMISSION), 0) COMMISSION, NVL (SUM (e.PRO_COMMISSION), 0) PRO_COMMISSION FROM position_master e,"+
    				" login_master b, broker_company_master a, personal_info d WHERE e.status = 'P'  AND b.agency_code = d.agency_code and  b.login_id in("+broker+") AND b.login_id = e.login_id AND b.agency_code = a.agency_code  AND e.product_id =? and e.INCEPTION_DATE between to_date(?,'DD/MM/YYYY') and to_date(?,'DD/MM/YYYY')+1 group by b.login_id,a.company_name,nvl(d.first_name,d.company_name)";
    			else
    				query ="SELECT a.company_name, NVL (d.first_name, d.company_name) customer_name, b.login_id,  COUNT (e.status) policy, TO_NUMBER (NVL (SUM (e.premium + e.excess_premium), 0)) premium, NVL (SUM (e.COMMISSION), 0) COMMISSION, NVL (SUM (e.PRO_COMMISSION), 0) PRO_COMMISSION FROM   position_master e, open_cover_master c, login_master b, broker_company_master a, personal_info d WHERE e.status = 'P' AND b.agency_code = d.agency_code AND b.login_id = e.login_id AND b.agency_code = a.agency_code and e.APPLICATION_ID in ("+broker+") AND e.product_id = ? AND e.INCEPTION_DATE BETWEEN TO_DATE (?, 'DD/MM/YYYY') AND  TO_DATE (?, 'DD/MM/YYYY') + 1 GROUP BY b.login_id, a.company_name, NVL (d.first_name, d.company_name)";
    			obj1=new Object[]{obj[0],obj[1],obj[2]};
    		}else{
    			obj1=new Object[]{obj[1],obj[2],obj[0]};
    			if("reportBR".equals(obj[4]))
    				query = LocalizedTextUtil.findDefaultText("GET_POLICY_REPORT_LIST_OTHERS", Locale.ENGLISH, new String[]{broker});
    			else
    				query ="select pi.LOGIN_ID, (select USERNAME from login_master llm where llm.login_id=pi.LOGIN_ID) company_name,first_name customer_name,POLICY_NO,(select count(*) from home_position_master hp where hp.policy_no=hpm.policy_no) policy,premium,commission from home_position_master hpm,personal_info pi where hpm.CUSTOMER_ID=pi.CUSTOMER_ID AND (hpm.INCEPTION_DATE BETWEEN TO_DATE (?, 'DD/MM/YYYY') AND TO_DATE (?, 'DD/MM/YYYY') + 1) and hpm.APPLICATION_ID in ("+broker+") and hpm.status='P'  and hpm.product_id=?";
    		}
			logger.info("Query===>" + query);
			for(Object a:obj1)
				logger.info("args===>" + a);
			policyReport=this.mytemplate.queryForList(query,obj1);
    	}catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}
    	return policyReport;
    */
	}
	
	public List<Map<String, Object>> getUwList(String branchCode){
		List <Map<String, Object>> uwList=null;
    	try{
    		query=getQuery("GET_UNDERWRITER_LIST");
			logger.info("Query===>" + query);
			uwList=this.mytemplate.queryForList(query,new Object[]{branchCode});
    	}catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}
    	return uwList;
    }
	
	public List<Map<String, Object>> getLcSmartList(final ReportBean bean){
		List <Map<String, Object>> lcSmartList=null;
    	Object obj[]=null;
    	Object[] obj1=null;
    	try{
    		obj=new Object[]{bean.getBcode(), bean.getBroker()};
    		if(bean.getSearchBy()!=null && bean.getSearchValue()!=null)
    			obj1=new Object[]{" and lower("+bean.getSearchBy()+") like '%'|| lower('"+bean.getSearchValue()+"')||'%' "};
    		else
    			obj1=new Object[]{""};
		    LocalizedTextProvider ltp = ActionContext.getContext().getInstance(LocalizedTextProvider.class);
    		query = ltp.findDefaultText("GET_LCSMART_REPORT_LIST", Locale.ENGLISH, obj1);
			logger.info("Query===>" + query);
			logger.info("args===>" + StringUtils.join(obj, ", "));
			lcSmartList=this.mytemplate.queryForList(query,obj);
    	}catch (Exception e) {
			logger.debug("EXCEPTION @ { " + e + " }");
		}
    	return lcSmartList;
	}
	
	public List<Map<String, Object>> getCommodityList(final String branchCode){
		List <Map<String, Object>> commodityList=null;
    	Object obj[]=null;
    	try{
			obj=new Object[]{branchCode, branchCode};
			query=getQuery("GET_COMMODITY_LIST");
			logger.info("Query===>" + query);
			logger.info("args===>" + StringUtils.join(obj, ", "));
			commodityList=this.mytemplate.queryForList(query,obj);
    	}catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}
    	return commodityList;
	}
	public List<Map<String, Object>> getTransportModeList(final String branchCode){
		List <Map<String, Object>> transModeList=null;
    	Object obj[]=null;
    	try{
			obj=new Object[]{branchCode};
			query=getQuery("GET_TRANSPORT_MODE_LIST");
			logger.info("Query===>" + query);
			logger.info("args===>" + StringUtils.join(obj, ", "));
			transModeList=this.mytemplate.queryForList(query,obj);
    	}catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}
    	return transModeList;
	}
	public List<Map<String, Object>> getCoverList(final String branchCode){
		List <Map<String, Object>> coverList=null;
    	Object obj[]=null;
    	try{
			obj=new Object[]{branchCode, branchCode};
			query=getQuery("GET_COVER_LIST");
			logger.info("Query===>" + query);
			logger.info("args===>" + StringUtils.join(obj, ", "));
			coverList=this.mytemplate.queryForList(query,obj);
    	}catch (Exception e) {
			logger.debug("EXCEPTION @ { " + e + " }");
		}
    	return coverList;
	}
	public List<Map<String, Object>> getSmartList(final Object[] obj){
		List <Map<String, Object>> smartList=null;
    	try{
		    LocalizedTextProvider ltp = ActionContext.getContext().getInstance(LocalizedTextProvider.class);
    		query = ltp.findDefaultText("GET_SMART_REPORT_LIST", Locale.ENGLISH, obj);
    		//query="select * from table(smart_report(?,?,?,?,?,?,?,?,?,?,?,?))";
			logger.info("Query===>" + query);
			logger.info("args===>" + StringUtils.join(obj, ", "));
			smartList=this.mytemplate.queryForList(query);
    	}catch (Exception e) {
			logger.debug("EXCEPTION @ { " + e + " }");
		}
    	return smartList;
	}
	public List<Map<String, Object>> getBranchReport(ReportBean bean){
		List <Map<String, Object>> branchReport=null;
		logger.info("branchReport - Enter");
		try{
		String sql="select * from table(adminReports(?,?,?,?,?))";//branch_code='"+bean.getBranch()+"' and product_id='"+bean.getProductID()+"'";
		
		if(StringUtils.isNotBlank(bean.getLoginId()))
			sql=sql+" where ApplicationId='"+bean.getLoginId()+"' ";
		else if("reportUW".equalsIgnoreCase(bean.getMode1())){
			sql=sql+" where applicationid!='1' ";
		}else if("reportBR".equalsIgnoreCase(bean.getMode1())){
			sql=sql+" where applicationid='1' ";
		}
		if(StringUtils.isNotBlank(bean.getBroker()))
			sql=sql+" and login_id='"+bean.getBroker()+"' ";
		Object args[]=new Object[5];
		args[0] = bean.getStartDate();
		args[1] = bean.getEndDate();
		args[2] = bean.getReportStatus();
		args[3] = bean.getProductID();
		args[4] = bean.getBranch();
		
		logger.info("Query for branch report "+sql);
		logger.info("Args "+StringUtils.join(args,","));
		/*logger.info("SQL=>"+sql);
		logger.info("Start Date=>"+bean.getStartDate());
		logger.info("End Date=>"+bean.getEndDate());
		logger.info("Product=>"+bean.getProductID());
		logger.info("Report=>"+bean.getReportStatus());
		logger.info("Branch Code=>"+bean.getLoginBranch());
		logger.info("Rsa Branch =>"+bean.getBranch());*/
		branchReport=this.mytemplate.queryForList(sql,args);	
		}
		catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}		
		logger.info("branchReport - Exit || Result: " + branchReport.size());
		return branchReport;
	}
	public List<Map<String, Object>> getExchangeReport(ReportBean bean){
		List <Map<String, Object>> exchangeReport=null;
		logger.info("exchangeReport - Enter");
		try{
			String sql=getQuery("GET_EXCHANGE_REPORT_LIST");
			logger.info("SQL=>"+sql);
			logger.info("EFF DATE=>"+bean.getEffDate());
			exchangeReport=this.mytemplate.queryForList(sql,new String[]{"1", bean.getEffDate(),"1",bean.getEffDate(),"1"});			
		}
		catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}		
		logger.info("branchReport - Exit || Result: " + exchangeReport.size());
		return exchangeReport;
	}
	public List<Map<String, Object>> getBranchName(String branchCode){
    	List <Map<String, Object>> getBranchName=null;
		logger.info("getBranchName - Enter");
		try{
			String sql=getQuery("GET_BRANCH_NAME_LIST");
			logger.info("SQL=>"+sql);
			logger.info("Branch Code=>"+branchCode);
			getBranchName=this.mytemplate.queryForList(sql,new String[]{branchCode});			
		}
		catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}		
		logger.info("getBranchName - Exit || Result: " + getBranchName.size());
    	return getBranchName;
    }
	
	public List<Map<String, Object>> getOpenCoverList(ReportBean bean, String branchCode){
		List <Map<String, Object>> openCover=null;
		logger.info("getOpenCoverList - Enter");
		try{
			/*String sql=getQuery("GET_OPENCOVER_REPORT_LIST");
			if("ALL".equals(bean.getBroker()))
				sql+=" branch_code='"+branchCode+"')) group by open_cover_no) A ) B order by status desc";
			else
				sql+=" oa_code in('"+bean.getBroker()+"'))) group by open_cover_no) A ) B order by status desc";
			Object obj[]=new Object[]{bean.getStartDate(), bean.getEndDate()};
			logger.info("SQL=>"+sql);
			logger.info("Args==>"+StringUtils.join(obj, ", "));*/
			String sql = "select * from table(open_cover_count(?,?,?,?))";
			Object obj[]=new Object[]{branchCode,bean.getStartDate(), bean.getEndDate(),bean.getBroker()};
			logger.info("SQL=>"+sql);
			logger.info("Args==>"+StringUtils.join(obj, ", "));
			openCover=this.mytemplate.queryForList(sql,obj);
		} catch (Exception e) {
			logger.debug("EXCEPTION @ { " + e + " }");
			e.printStackTrace();
		}		
		logger.info("getOpenCoverList - Exit");
    	return openCover;
    }
	
	public List<Map<String, Object>> getConsigneeList(ReportBean bean, String branchCode){
		List <Map<String, Object>> openCover=null;
		logger.info("getConsigneeList - Enter");
		try{
			String sql=getQuery("GET_MULTI_OPENCOVER_LIST");
			/*if("P".equals(bean.getReportStatus()))
				sql+=" AND PM.STATUS='P')   group by TRANSACTION_ID,SUPPLIER_NAME, VESSEL_NAME, FIRST_NAME, username, policy_no  order by policy_no, supplier_name";
			else*/
			if("result1".equals(bean.getReqFrom()))
				sql="select * from table(AdminReports(?,?,'11','P',?,'ALL')) where VESSEL_NAME='"+bean.getVesselName()+"' and TRANSACTION_ID='"+bean.getTranId()+"'";
			Object obj[]=new Object[]{bean.getStartDate(), bean.getEndDate(), branchCode};
			logger.info("SQL=>"+sql);
			logger.info("Args==>"+StringUtils.join(obj, ", "));
			openCover=this.mytemplate.queryForList(sql,obj);
		} catch (Exception e) {
			logger.debug("EXCEPTION @ { " + e + " }");
			e.printStackTrace();
		}		
		logger.info("getConsigneeList - Exit");
    	return openCover;
    }
	 public List <Map<String, Object>> getAdminBrokerList(String branchCode, String appId){
    	List <Map<String, Object>> brokerList=null;
    	try{
			String query="SELECT 'ALL' login_id, 'ALL' COMPANY_NAME FROM DUAL UNION ALL SELECT pi.login_id,  bcm.COMPANY_NAME FROM   BROKER_COMPANY_MASTER bcm, personal_info pi WHERE bcm.agency_code = pi.agency_code AND bcm.branch_code IN (?) AND APPLICATION_ID = ? AND pi.login_id IN (SELECT login_id FROM login_master WHERE oa_code IN (SELECT agency_code FROM broker_company_master WHERE branch_code IN (?)))";
			logger.info("Query===>" + query);
			logger.info("branchCode===>" + branchCode);
			brokerList=this.mytemplate.queryForList(query,new Object[]{branchCode, appId, branchCode});		
		   }
		catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}
    	return brokerList;
	}
	 
	public List <Map<String, Object>> getCountrySmartList(){
		List <Map<String, Object>> brokerList=new ArrayList<Map<String, Object>>();
    	try{
			String query="SELECT 0 COUNTRY_ID, 'ALL' COUNTRY_NAME FROM DUAL UNION ALL SELECT E.COUNTRY_ID, E.COUNTRY_NAME FROM COUNTRY_MASTER E WHERE E.AMEND_ID = (SELECT MAX (AMEND_ID) FROM COUNTRY_MASTER WHERE COUNTRY_NAME = E.COUNTRY_NAME) order by COUNTRY_NAME, country_id";
			logger.info("Query===>" + query);
			brokerList=this.mytemplate.queryForList(query);		
		   }
		catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}
    	return brokerList;
	 }
	public List<Map<String, Object>> getIntgReport(ReportBean bean) {
		List <Map<String, Object>> intgList=new ArrayList<Map<String, Object>>();
    	try{
			String query=getQuery("GET_INTG_REPORT");
			logger.info("Query===>" + query);
			Object args[]=new Object[5];
			args[0] = bean.getStartDate();
			args[1] = bean.getEndDate();
			args[2] = bean.getProductID();
			args[3] =bean.getReportStatus(); 
			args[4] = bean.getBranch();
			intgList=this.mytemplate.queryForList(query,args);		
		   }
		catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}
    	return intgList;
	 }
	public List<Map<String, Object>> getMultiIntgReport(String policyNo) {
		List <Map<String, Object>> intgList=new ArrayList<Map<String, Object>>();
    	try{
			String query=getQuery("GET_INTG_REPORT_POL");
			logger.info("Query===>" + query);
			Object args[]=new Object[1];
			args[0] = policyNo;;
			intgList=this.mytemplate.queryForList(query,args);		
		   }
		catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}
    	return intgList;
	 }
}