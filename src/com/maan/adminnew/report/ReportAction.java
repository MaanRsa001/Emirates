package com.maan.adminnew.report;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import com.maan.adminnew.common.CommonService;
import com.maan.common.LogUtil;
import com.maan.common.Validation;
import com.maan.report.JasperReports;
import com.maan.webservice.MailTriggerIntegration;
import com.maan.webservice.service.PolicyGenerationService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ReportAction extends ActionSupport implements ModelDriven<ReportBean>
{
	private static final Logger logger = LogUtil.getLogger(ReportAction.class);
	private static final long serialVersionUID = 1L;
	private ReportBean bean = new ReportBean();
	Validation validation=new Validation();
	ReportService service=new ReportService();
	final CommonService cservice=new CommonService();
	Map<String, Object> session=ActionContext.getContext().getSession();
	HttpServletRequest request= ServletActionContext.getRequest();
	HttpServletResponse responce= ServletActionContext.getResponse();
	List<Map<String, Object>> transDetList=null;
	ServletContext context=request.getSession().getServletContext();
	String branchCode=(String)session.get("BranchCode");
	String belongingBranch = (String)session.get("BelongingBranch");
	String login_id=(String)session.get("user");
	String appId=(String)session.get("AppId");
	List<Map<String, Object>> branchName=new ArrayList<Map<String, Object>>();
	List<Map<String, Object>> policyList=new ArrayList<Map<String, Object>>();
	List<Map<String,Object>> productList=new ArrayList<Map<String,Object>>();
	List<Map<String, Object>> uwList=new ArrayList<Map<String, Object>>();
	List<Map<String, Object>> brokerList=new ArrayList<Map<String, Object>>();
	List<Map<String, Object>> lcsmartList=new ArrayList<Map<String, Object>>();
	List<Map<String, Object>> coverList=new ArrayList<Map<String, Object>>();
	List<Map<String, Object>> smartList=new ArrayList<Map<String, Object>>();
	List<Map<String, Object>> branchList=new ArrayList<Map<String, Object>>();
	List<Map<String, Object>> exchangeList=new ArrayList<Map<String, Object>>();
	List<Map<String, Object>> consigneeList=new ArrayList<Map<String, Object>>();
	
	private InputStream inputStream;
	
	public List<Map<String, Object>> getExchangeList() {
		return exchangeList;
	}
	public void setExchangeList(List<Map<String, Object>> exchangeList) {
		this.exchangeList = exchangeList;
	}
	public List<Map<String, Object>> getBranchName() {
		return branchName;
	}
	public void setBranchName(List<Map<String, Object>> branchName) {
		this.branchName = branchName;
	}
	public List<Map<String, Object>> getBranchList() {
		return branchList;
	}
	public void setBranchList(List<Map<String, Object>> branchList) {
		this.branchList = branchList;
	}
	public void setCoverList(List<Map<String, Object>> coverList) {
		this.coverList = coverList;
	}
	public List<Map<String, Object>> getPolicyList() {
		return policyList;
	}
	public void setPolicyList(List<Map<String, Object>> policyList) {
		this.policyList = policyList;
	}
	public List<Map<String,Object>> getProductList() {
		return productList;
	}
	public void setProductList(List<Map<String,Object>> productList) {
		this.productList = productList;
	}
	public List<Map<String, Object>> getConsigneeList() {
		return consigneeList;
	}
	public void setConsigneeList(List<Map<String, Object>> consigneeList) {
		this.consigneeList = consigneeList;
	}
	public List <Map<String, Object>> getCountryList(){
    	return cservice.getCountries(branchCode);
    }
	public List<Map<String, Object>> getUwList() {
		return uwList;
	}
	public void setUwList(List<Map<String, Object>> uwList) {
		this.uwList = uwList;
	}
	public List<Map<String, Object>> getBrokerList() {
		return brokerList;
	}
	public void setBrokerList(List<Map<String, Object>> brokerList) {
		this.brokerList = brokerList;
	}
	public List<Map<String, Object>> getCommodityList(){
		return service.getCommodityList(belongingBranch);
	}
	public List<Map<String, Object>> getCoverList(){
		return coverList;
	}
	public List<Map<String, Object>> getTransportModeList(){
		return service.getTransportModeList(belongingBranch);
	}

	/*public String schedule(){
    	logger.info("schedule()===>Enter");
		String returnResult="schedule";
		scheduleMap=service.schedule(bean.getBroker());
    	logger.info("schedule()===>Exit");
		return returnResult;
	}*/
	
	public List<Map<String, Object>> getLcsmartList() {
		return lcsmartList;
	}
	public void setLcsmartList(List<Map<String, Object>> lcsmartList) {
		this.lcsmartList = lcsmartList;
	}
	public List<Map<String, Object>> getSmartList() {
		return smartList;
	}
	public void setSmartList(List<Map<String, Object>> smartList) {
		this.smartList = smartList;
	}
	public String policy(){
		uwList=service.getUwList(branchCode);
		productList=cservice.getProductsDET(branchCode,"");
		brokerList=cservice.getAdminBrokerList(branchCode, appId);
		if(StringUtils.isNotBlank(bean.getStartDate()))
			bean.setStartDate(dateFormat(bean.getStartDate()));
		if(StringUtils.isNotBlank(bean.getEndDate()))
			bean.setEndDate(dateFormat(bean.getEndDate()));
		return "policy";
	}
	
	public String lcSmart(){
		String returnResult="lcsmart";
		brokerList=cservice.getAdminBrokerList(branchCode, appId);
		if(bean.getReqFrom()!=null && bean.getBroker()!=null){
			bean.setBcode(branchCode);
			lcsmartList=service.getLcSmartList(bean);
			if(bean.getReqFrom()!=null && !"".equals(bean.getReqFrom()))
				returnResult="ajax";
			if("ajax".equals(bean.getFrom1()))
				returnResult="lcsmart";
		}return returnResult;
	}
	
	public String lcSmartJasper() {
		try {
			bean.setFileName("LCSmartReport");
			JasperReports jr = new JasperReports();
			String type = "";
			if("ocm.MISSIPPI_OPENCOVER_NO".equalsIgnoreCase(bean.getSearchBy())) {
				type = "OPENCOVER";
			}
			else if("oclm.LC_NUMBER".equalsIgnoreCase(bean.getSearchBy())) {
				type = "LCNUMBER";
			}
			ByteArrayOutputStream bos=new ByteArrayOutputStream();
			jr.LCSmartReport(bean.getBroker(), branchCode, type, bean.getSearchValue(), bean.getDownloadType(), bos);
			inputStream=new ByteArrayInputStream(bos.toByteArray());
		}
		catch(Exception exception) {
			logger.debug("Exception @ lcSmartJasper { " + exception + " } ");
		}
		if("excel".equals(bean.getDownloadType()))
		{
			return "excel";
		}
		if("pdf".equals(bean.getDownloadType()))
		{
			return "pdf";
		}
		return "download";
	}
	
	public String smart(){
		String returnResult="smart";
		brokerList=service.getAdminBrokerList(branchCode, appId);
		productList=cservice.getProductsDET(branchCode,"");
		coverList=service.getCoverList(branchCode);
		bean.setCountrySmartList(service.getCountrySmartList());
		if(StringUtils.isNotBlank(bean.getStartDate()))
			bean.setStartDate(dateFormat(bean.getStartDate()));
		if(StringUtils.isNotBlank(bean.getEndDate()))
			bean.setEndDate(dateFormat(bean.getEndDate()));
		if(StringUtils.isNotBlank(bean.getBroker()))
			bean.setBrokers(bean.getBroker().split(","));
		if(StringUtils.isNotBlank(bean.getRags()))
			bean.setRag(bean.getRags().split(","));
		if(StringUtils.isNotBlank(bean.getOrginCountry())){
			bean.setOrginCountry(bean.getOrginCountry().replaceAll(", ", ","));
			bean.setOrginCountry(bean.getOrginCountry().replaceAll(" ,", ","));
			bean.setOrginCountries(bean.getOrginCountry().split(","));
		}if(StringUtils.isNotBlank(bean.getDestCountry())){
			bean.setDestCountry(bean.getDestCountry().replaceAll(", ", ","));
			bean.setDestCountry(bean.getDestCountry().replaceAll(" ,", ","));
			bean.setDestCountries(bean.getDestCountry().split(","));
		}
		return returnResult;
	}
	
	public String getRePolicy(){
    	logger.info("policy()===>Enter");
		String returnResult="ajax";
    	if("reportBR".equals(bean.getMode1())){
    		bean.setReqFrom("policylistBR");
    		bean.setIndex("1");
    	}else{
    		bean.setIndex("0");
    		bean.setReqFrom("policylistUW");
    	}
		validatepolicy();
		try{
			if(!hasActionErrors()){
				Object obj[]=new Object[]{bean.getProductID(),bean.getStartDate(), bean.getEndDate(), bean.getBroker(), bean.getMode1(),branchCode};
				policyList=service.getPolicyReport(obj);
				bean.setReqFrom("policylist");
				if("ajax".equals(bean.getFrom1())){
					uwList=service.getUwList(branchCode);
					productList=cservice.getProductsDET(branchCode,"");
					brokerList=cservice.getAdminBrokerList(branchCode, appId);
					returnResult="policy";
				}
			}else{
				uwList=service.getUwList(branchCode);
				productList=cservice.getProductsDET(branchCode,"");
				brokerList=cservice.getAdminBrokerList(branchCode, appId);
			}
			logger.info("policy()===>Exit");
		}catch(Exception e){
			e.printStackTrace();
		}
		return returnResult;
	}
	

	public String smartlist(){
		//String rags="";
		String returnResult="";
		if(bean.getOrginCountries().length<=0) {
			bean.setOrginCountries(new String[]{"0"}); 
		}
		validatesmart();
		if(!hasActionErrors()){
			if(Arrays.asList(bean.getBrokers()).contains("ALL"))
				bean.setBroker("ALL");
			else
				bean.setBroker(StringUtils.join(bean.getBrokers(), ","));
			bean.setRags(StringUtils.join(bean.getRag(),","));
			if(Arrays.asList(bean.getOrginCountries()).contains("0"))
				bean.setOrginCountry("ALL");
			else
				bean.setOrginCountry(StringUtils.join(bean.getOrginCountries(),","));
			if(Arrays.asList(bean.getDestCountries()).contains("0"))
				bean.setDestCountry("ALL");
			else
				bean.setDestCountry(StringUtils.join(bean.getDestCountries(),","));
			
			/*Object obj[]=new Object[]{bean.getBroker(),bean.getTransportId().replaceAll(" ", ""), StringUtils.isBlank(bean.getCoverId())?"":bean.getCoverId().replaceAll(" ", "") , bean.getBusType(), bean.getCommodity(), bean.getRags(), 
									bean.getStartDate(), bean.getEndDate(), bean.getProductID(), branchCode, bean.getOrginCountry(), bean.getDestCountry()};*/
			Object obj[]=new Object[]{bean.getBroker(),bean.getTransportId().replaceAll(" ", ""), StringUtils.isBlank(bean.getCoverId())?"":bean.getCoverId().replaceAll(" ", "") , bean.getCommodity(), 
					bean.getStartDate(), bean.getEndDate(), bean.getProductID(), branchCode, bean.getOrginCountry(), bean.getDestCountry()};
			
			/*
			if("all".equals(bean.getOrginCountry()))
				bean.setOrginCountry("select distinct country_id from country_master");
			if("all".equals(bean.getDestCountry()))
				bean.setDestCountry("select distinct country_id from country_master");
			if(bean.getRags()!=null && bean.getRags().length()>0)
				bean.setRags(" and mrd.rag in ('"+bean.getRags().replaceAll(",", "','")+"')");
			else bean.setRags("");
			bean.setTransportId(" and md.mode_of_transport in ('"+bean.getTransportId().replaceAll(",", "','")+"')");
			bean.setCoverId(" and md.cover_id in ('"+bean.getCoverId().replaceAll(",", "','")+"')");
			bean.setCommodity(" and mrd.commodity_id in ('"+bean.getCommodity().replaceAll(",", "','")+"')");
			if(!"all".equals(bean.getBroker())){
				bean.setBroker(bean.getBroker().replaceAll(" ,",","));
				bean.setBroker(bean.getBroker().replaceAll(", ",","));
				bean.setBroker(bean.getBroker().trim());
				bean.setBroker(" and log.LOGIN_ID in ('"+bean.getBroker().replaceAll(",","','").trim()+"')");
			}*/
			
			//Object obj[]=new Object[]{branchCode, branchCode, bean.getBroker(), bean.getProductID(), bean.getTransportId(), bean.getCoverId(), bean.getBusType(), bean.getOrginCountry(), bean.getDestCountry(), bean.getCommodity(), bean.getRags(), "'"+bean.getStartDate()+"'", "'"+bean.getEndDate()+"'"};
			
			smartList=service.getSmartList(obj);
			returnResult="smartList";
		}else 
			returnResult=smart();
		return returnResult;
	}
	
	public String branch(){
		String returnResult="branch";
		productList=cservice.getProductsDET(branchCode,"");
		branchName=service.getBranchName(branchCode);
		if(StringUtils.isNotBlank(bean.getStartDate()))
			bean.setStartDate(dateFormat(bean.getStartDate()));
		if(StringUtils.isNotBlank(bean.getEndDate()))
			bean.setEndDate(dateFormat(bean.getEndDate()));
		return returnResult;
	}
	public String branchResult(){
		String returnResult="branch";
		validbranch();
		if(!hasActionErrors()){
			bean.setBranch(branchCode);
			bean.setLoginBranch(branchCode);
			branchList=service.getBranchReport(bean);
			bean.setReqFrom("branchlist");
		}else{
			productList=cservice.getProductsDET(branchCode,"");
			branchName=service.getBranchName(branchCode);
			bean.setReqFrom(null);
		}
		return returnResult;
	}
	public String integration(){
		String returnResult="integration";
		bean.setReqFrom(null);
		productList=cservice.getProductsDET(branchCode,"");
		branchName=service.getBranchName(branchCode);
		if(StringUtils.isNotBlank(bean.getStartDate()))
			bean.setStartDate(dateFormat(bean.getStartDate()));
		if(StringUtils.isNotBlank(bean.getEndDate()))
			bean.setEndDate(dateFormat(bean.getEndDate()));
		return returnResult;
	}
	public String integrationResult(){
		String returnResult="integration";
		validbranch();
		if(!hasActionErrors()){
			bean.setBranch(branchCode);
			bean.setLoginBranch(branchCode);
			branchList=service.getIntgReport(bean);
			bean.setReqFrom("branchlist");
		}else{
			productList=cservice.getProductsDET(branchCode,"");
			branchName=service.getBranchName(branchCode);
			bean.setReqFrom(null);
		}
		return returnResult;
	}
	
	public String reIntegrate(){
		String returnResult="integration";
		bean.setReqFrom(null);
		new PolicyGenerationService().reIntegrate(bean.getQuoteNo());
		String result=new PolicyGenerationService().callIntegrationWebservice(bean.getApplicationNo(), bean.getProductID(), bean.getPolicynumber());
		/*Runnable hello = new MailTriggerIntegration( bean.getPolicynumber(),bean.getApplicationNo(),bean.getProductID());
	    Thread thread1 = new Thread(hello);
	    thread1.setDaemon(true);
	    thread1.setName("quote");
	    System.out.println("Started Integration Posting  Trigger...");
	    thread1.start();*/
		productList=cservice.getProductsDET(branchCode,"");
		branchName=service.getBranchName(branchCode);
		/*if(StringUtils.isNotBlank(bean.getStartDate()))
			bean.setStartDate(dateFormat(bean.getStartDate()));
		if(StringUtils.isNotBlank(bean.getEndDate()))
			bean.setEndDate(dateFormat(bean.getEndDate()));*/
		bean.setBranch(branchCode);
		bean.setLoginBranch(branchCode);
		branchList=service.getIntgReport(bean);
		bean.setReqFrom("branchlist");
		result="Policy No:"+bean.getPolicynumber()+" "+result;
		bean.setIntegrationStatus(result);
		return returnResult;
	}
	
	public String multiReIntegrate(){
		String returnResult="integration";
		bean.setReqFrom("policyList");
		String [] multiReIntg=bean.getMultiIntegrate();
		for(int i=0;i<multiReIntg.length;i++){
			List<Map<String, Object>> multiRe=service.getMultiIntgReport(multiReIntg[i]);
			if(multiRe!=null&&multiRe.size()>0){
				Map<String,Object> tempMap=(Map<String, Object>) multiRe.get(0);
				new PolicyGenerationService().reIntegrate(tempMap.get("APPLICATION_NO")==null?"":tempMap.get("APPLICATION_NO").toString());
				String result=new PolicyGenerationService().callIntegrationWebservice(tempMap.get("APPLICATION_NO")==null?"":tempMap.get("APPLICATION_NO").toString()
						,tempMap.get("PRODUCT_ID")==null?"":tempMap.get("PRODUCT_ID").toString(), multiReIntg[i]);
				tempMap.put("POLICY_NO", multiReIntg[i]);
				tempMap.put("STATUS", result);
				branchList.add(tempMap);
			}
		}

		return returnResult;
	}
	
	public String exchange(){
		String returnResult="exchange";
		return returnResult;
	}
	public String exchangeResult(){
		String returnResult="exchange";
		validateexchange();
		if(!hasActionErrors()){
			exchangeList=service.getExchangeReport(bean);
			bean.setReqFrom("exchangelist");
		}
		return returnResult;
	}
	
	public String commoditySelect(){
		return "commoditySelect";
	}
	
	public ReportBean getModel() {
		return bean;
	}
	public void validatepolicy() {
		 if(StringUtils.isBlank(bean.getStartDate())){
    		addActionError(getText("error.policy.report.startdate.invalid"));
    	}if(StringUtils.isBlank(bean.getEndDate())){
    		addActionError(getText("error.policy.report.enddate.invalid"));
    	}if(StringUtils.isBlank(bean.getBroker())){
    		if("reportBR".equals(bean.getMode1()))
    			addActionError(getText("error.policy.report.broker.invalid"));
    		else addActionError(getText("error.policy.report.uw.invalid"));
    	}else
        	bean.setBroker(bean.getBroker().substring(bean.getBroker().charAt(0)==','?1:0));
    	if(StringUtils.isBlank(bean.getProductID())){
    		addActionError(getText("error.policy.report.product.invalid"));
    	}if(!hasActionErrors()){
			try{
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		    	Date sdate = sdf.parse(bean.getStartDate());
		    	Date edate = sdf.parse(bean.getEndDate());
		    	if(sdate.after(edate))
		    		addActionError("End date should be greater than or equal to Start Date");
			}catch(Exception e){
				e.printStackTrace();
			}
		} if(hasActionErrors()){
			if(StringUtils.isNotBlank(bean.getStartDate()))
				bean.setStartDate(dateFormat(bean.getStartDate()));
			if(StringUtils.isNotBlank(bean.getEndDate()))
				bean.setEndDate(dateFormat(bean.getEndDate()));
		}
    }
	
	public void validatesmart() {
		 if(StringUtils.isBlank(bean.getStartDate()))
			 addActionError(getText("error.policy.report.startdate.invalid"));
		 if(StringUtils.isBlank(bean.getEndDate()))
			 addActionError(getText("error.policy.report.enddate.invalid"));
		 if(StringUtils.isBlank(bean.getProductID()))
  			 addActionError(getText("error.policy.report.product.invalid"));
		 if(bean.getBrokers().length<=0)
  			 addActionError(getText("error.policy.report.broker.invalid"));
		 if(StringUtils.isBlank(bean.getCommodity()))
  			 addActionError(getText("error.policy.report.commodity.invalid"));
		 if(StringUtils.isBlank(bean.getBusType()))
  			 addActionError(getText("error.policy.report.bustype.invalid"));
		 if(bean.getOrginCountries().length<=0)
  			 addActionError(getText("error.policy.report.origincountry.invalid"));
		 if(bean.getDestCountries().length<=0)
  			 addActionError(getText("error.policy.report.destcountry.invalid"));
		 if(StringUtils.isBlank(bean.getTransportId()) && StringUtils.isBlank(bean.getCoverId()))
  			 addActionError(getText("error.policy.report.coverid.invalid"));
		 if(!hasActionErrors()){
			try{
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		    	Date sdate = sdf.parse(bean.getStartDate());
		    	Date edate = sdf.parse(bean.getEndDate());
		    	if(sdate.after(edate))
		    		addActionError("End date should be greater than or equal to Start Date");
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	public void validbranch() {
		if(StringUtils.isBlank(bean.getStartDate())){
			addActionError(getText("error.policy.report.startdate.invalid"));
		}if(StringUtils.isBlank(bean.getEndDate())){
			addActionError(getText("error.policy.report.enddate.invalid"));
		}if(!hasActionErrors()){
			try{
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		    	Date sdate = sdf.parse(bean.getStartDate());
		    	Date edate = sdf.parse(bean.getEndDate());
		    	if(sdate.after(edate))
		    		addActionError("End date should be greater than or equal to Start Date");
			}catch(Exception e){
				e.printStackTrace();
			}
		} if(hasActionErrors()){
			if(StringUtils.isNotBlank(bean.getStartDate()))
				bean.setStartDate(dateFormat(bean.getStartDate()));
			if(StringUtils.isNotBlank(bean.getEndDate()))
				bean.setEndDate(dateFormat(bean.getEndDate()));
		}if(StringUtils.isBlank(bean.getProductID())){
			addActionError(getText("error.policy.report.product.invalid"));
		}/*if(StringUtils.isBlank(bean.getBranch())){
			addActionError(getText("error.policy.report.branch.invalid"));
		}*/
	}
	
	public void validateexchange() {
	 if(StringUtils.isBlank(bean.getEffDate())){
    		addActionError(getText("error.policy.report.startdate.invalid"));
    	}
	}
	
	public String openCover(){
		String returnResult="opencover";
		try{
			if("result".equals(bean.getReqFrom())){
				validOpenCover("opencover");
				if(!hasActionErrors()){
					coverList=service.getOpenCoverList(bean, branchCode);
					
				}else{
					brokerList=cservice.getAdminBrokerList(branchCode, appId);
					bean.setReqFrom("");
					if(StringUtils.isNotBlank(bean.getStartDate()))
						bean.setStartDate(dateFormat(bean.getStartDate()));
					if(StringUtils.isNotBlank(bean.getEndDate()))
						bean.setEndDate(dateFormat(bean.getEndDate()));
				}
			}else{
				brokerList=cservice.getAdminBrokerList(branchCode, appId);
				bean.setReqFrom("");
				if(StringUtils.isNotBlank(bean.getStartDate()))
					bean.setStartDate(dateFormat(bean.getStartDate()));
				if(StringUtils.isNotBlank(bean.getEndDate()))
					bean.setEndDate(dateFormat(bean.getEndDate()));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return returnResult;
	}
	
	public String openCoverJasper() {
		try {
			bean.setFileName("OpenCoverReport");
			JasperReports jr = new JasperReports();
			ByteArrayOutputStream bos=new ByteArrayOutputStream();
			jr.openCoverReport(bean.getStartDate(), bean.getEndDate(), bean.getBroker(), branchCode, bean.getDownloadType(), bos);
			inputStream=new ByteArrayInputStream(bos.toByteArray());
		}
		catch(Exception exception) {
			logger.debug("Exception @ openCoverJasper { " + exception + " } ");
		}
		if("excel".equals(bean.getDownloadType()))
		{
			return "excel";
		}
		if("pdf".equals(bean.getDownloadType()))
		{
			return "pdf";
		}
		return "download";
	}
	
	public void validOpenCover(String cond){
		if(StringUtils.isBlank(bean.getStartDate()))
			addActionError("Please select Start Date");
		if(StringUtils.isBlank(bean.getEndDate()))
			addActionError("Please select End Date");
		if(StringUtils.isBlank(bean.getBroker()) && "opencover".equals(cond))
			addActionError("Please select Broker");
		/*if(StringUtils.isBlank(bean.getReportStatus()) && "consignee".equals(cond))
			addActionError("Please select Report Type")*/;
		if(!hasActionErrors()){
			try{
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		    	Date sdate = sdf.parse(bean.getStartDate());
		    	Date edate = sdf.parse(bean.getEndDate());
		    	if(sdate.after(edate))
		    		addActionError("End date should be greater than or equal to Start Date");
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		if(hasActionErrors()  && !"opencover".equals(cond)){
			if(StringUtils.isNotBlank(bean.getStartDate()))
				bean.setStartDate(dateFormat(bean.getStartDate()));
			if(StringUtils.isNotBlank(bean.getEndDate()))
				bean.setEndDate(dateFormat(bean.getEndDate()));
		}
	}
	
	public String dateFormat(String name){
		String arr[]=name.split("/");
		return arr[1]+"/"+arr[0]+"/"+arr[2];
	}
	
	public String consignee(){
		String returnResult="consignee";
		try{
			if("result".equals(bean.getReqFrom())){
				validOpenCover("consignee");
				if(!hasActionErrors()){
					consigneeList=service.getConsigneeList(bean, branchCode);
				}else
					bean.setReqFrom("");
			}else if("result1".equals(bean.getReqFrom())){
				consigneeList=service.getConsigneeList(bean, branchCode);
			}else if("policyDetail".equals(bean.getReqFrom())){
				consigneeList=service.getConsigneeList(bean, branchCode);
				if(StringUtils.isNotBlank(bean.getStartDate()))
					bean.setStartDate(dateFormat(bean.getStartDate()));
				if(StringUtils.isNotBlank(bean.getEndDate()))
					bean.setEndDate(dateFormat(bean.getEndDate()));
			}else{
				if(StringUtils.isNotBlank(bean.getStartDate()))
					bean.setStartDate(dateFormat(bean.getStartDate()));
				if(StringUtils.isNotBlank(bean.getEndDate()))
					bean.setEndDate(dateFormat(bean.getEndDate()));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return returnResult;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
}