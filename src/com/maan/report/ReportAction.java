package com.maan.report;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.util.CollectionUtils;

import com.iii.premia.maansarovar.services.common.CustomerDataResponseInfo;
import com.maan.Mail.controller.mailController;
import com.maan.common.LogUtil;
import com.maan.common.Validation;
import com.maan.common.exception.BaseException;
import com.maan.copyquote.service.CopyQuoteService;
import com.maan.integration.CustXmlBean;
import com.maan.integration.Customer;
import com.maan.integration.CyberSouceIntegration;
import com.maan.integration.IntegrationService;
import com.maan.quotation.service.PremiumService;
import com.maan.report.service.ReportService;
import com.maan.webservice.PolicyGenerationAction;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ReportAction extends ActionSupport {
	private static final Logger logger = LogUtil.getLogger(ReportAction.class);
	final HttpServletRequest request=ServletActionContext.getRequest();
	final HttpServletResponse response=ServletActionContext.getResponse();
	Map<String, Object> session=ActionContext.getContext().getSession();
	ReportService service=new ReportService();
	PremiumService premium=new PremiumService();
	mailController mail=new mailController();
	private static final long serialVersionUID = 1L;
	private String oneOff=getText("ONE_OFF");
	private String openCover=getText("OPEN_COVER");
	private List<Map<String, Object>> policyList;
	private List<Object> existingQuote;	
	private String menuType;	
	private String policyNo;
	private List<Map<String, Object>> declarationPolicy;	
	private String openCoverNo;
	private String loginId=StringUtils.defaultIfEmpty((String)session.get("BROKER_LOGIN_ID"), "");
	private String userType=(String)session.get("usertype");
	private String actualBranch=(String)session.get("adminBranch");
	private String productId=(String)session.get("product_id");
	private String openCustomerId;
	private String issuer=(String)session.get("RSAISSUER");
	private String brokerCode;
	private String policyStartDate;
	private String policyEndDate;
	private String userLoginId;
	private String quoteNo;
	private String applicationNo;
	private String refStatus;
	private String linkType;
	private String branchCode=(String)session.get("LoginBranchCode");
	private String belongingBranch=(String)session.get("BelongingBranch");
	private List<Map<String, Object>> lapsedReason;
	private List<Map<String, Object>> lapsedQuote;
	private String lapsedRemarks;
	private String display;
	private String searchBy;
	private String searchValue;
	private String quoteStatus;
	private String endStatus;
	private String endTypeId;
	private String custName;
	private List<Map<String, Object>> brokerList;
	private List<Object> endType;
	private String brokerCompany;
	private String searchFrom;
	private InputStream inputStream;
	private Integer page= 0;
	private int id;
	private Integer rows=0;
	private String sord;
	private String sidx;
	private String searchField;
	private String searchString;
	private String searchOper;
	private Integer total= 0;
	private Integer records= 0;
	private String option;
	private String reqFrm;
	private String appNo;
	private String quotationNo;
	private String menuFrom;
	private String referalStatus;
	private String endtStatus;
	private String schemeId=(String)session.get("scheme_id");
	private String contenTypeId=(String)session.get("ContentType");
	private String loginUserType=(String)session.get("usertype");
	private String adminLoginId=(String)session.get("user");
	private String endtLoginId;
	private String menuBlocker;
	private String custMissippiCode;
	
	ReportBean bean=new ReportBean();
	private String originalOpenCoverNo;
	private String cancelYN;
	private String cancelRemarks;
	private String quoteMailIds;
	private String reqFrom;

	
	
	private String downloadType;
	private String fileName;
	private String lcFileName;
	private String otherPolicySearch;
	
	
	public String getOtherPolicySearch() {
		return otherPolicySearch;
	}
	public void setOtherPolicySearch(String otherPolicySearch) {
		this.otherPolicySearch = otherPolicySearch;
	}
	private List<ReportBean> reportGridList;
	
	public String getMenuBlocker() {
		return menuBlocker;
	}
	public void setMenuBlocker(String menuBlocker) {
		this.menuBlocker = menuBlocker;
	}
	public String getCancelYN() {
		return cancelYN;
	}
	public void setCancelYN(String cancelYN) {
		this.cancelYN = cancelYN;
	}
	public String getCancelRemarks() {
		return cancelRemarks;
	}
	public void setCancelRemarks(String cancelRemarks) {
		this.cancelRemarks = cancelRemarks;
	}
	public String getOriginalOpenCoverNo() {
		return originalOpenCoverNo;
	}
	public void setOriginalOpenCoverNo(String originalOpenCoverNo) {
		this.originalOpenCoverNo = originalOpenCoverNo;
	}
	public String getSchemeId() {
		return schemeId;
	}
	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}
	public String getContenTypeId() {
		return contenTypeId;
	}
	public void setContenTypeId(String contenTypeId) {
		this.contenTypeId = contenTypeId;
	}
	public List<Map<String, Object>> getBasisValList() {
		return premium.getBasisValList(branchCode);
	}
	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public List<Map<String, Object>> getPolicyInformation() {
		return premium.getPolicyInformation(applicationNo);
	}
	public List<Map<String, Object>> getPolicyList1(){
		menuType="R";
		return service.getReportList(loginId,productId,issuer,menuType,openCoverNo,policyStartDate,policyEndDate,quoteNo,policyNo,searchBy,searchValue, searchField, searchString, searchOper,branchCode);
	}
	public String getAppNo() {
		return appNo;
	}

	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}

	public String getQuotationNo() {
		return quotationNo;
	}

	public void setQuotationNo(String quotationNo) {
		this.quotationNo = quotationNo;
	}

	public String getReferalStatus() {
		return referalStatus;
	}

	public void setReferalStatus(String referalStatus) {
		this.referalStatus = referalStatus;
	}
	public String getEndtStatus() {
		return endtStatus;
	}
	public void setEndtStatus(String endtStatus) {
		this.endtStatus = endtStatus;
	}
	public String ReqFrm() {
		return reqFrm;
	}
	public void setReqFrm(String reqFrm) {
		this.reqFrm = reqFrm;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public String getSearchFrom() {
		return searchFrom;
	}
	public void setSearchFrom(String searchFrom) {
		this.searchFrom = searchFrom;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public String getSord() {
		return sord;
	}
	public void setSord(String sord) {
		this.sord = sord;
	}
	public String getSidx() {
		return sidx;
	}
	public void setSidx(String sidx) {
		this.sidx = sidx;
	}
	public String getSearchField() {
		return searchField;
	}
	public void setSearchField(String searchField) {
		this.searchField = searchField;
	}
	public String getSearchString() {
		return searchString;
	}
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
	public String getSearchOper() {
		return searchOper;
	}
	public void setSearchOper(String searchOper) {
		this.searchOper = searchOper;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getRecords() {
		return records;
	}
	public void setRecords(Integer records) {
		this.records = records;
	}
	public String getOption() {
		return option;
	}
	public void setOption(String option) {
		this.option = option;
	}
	public String getBrokerCompany() {
		return brokerCompany;
	}
	public void setBrokerCompany(String brokerCompany) {
		this.brokerCompany = brokerCompany;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getEndStatus() {
		return endStatus;
	}
	public void setEndStatus(String endStatus) {
		this.endStatus = endStatus;
	}
	public String getEndTypeId() {
		return endTypeId;
	}
	public void setEndTypeId(String endTypeId) {
		this.endTypeId = endTypeId;
	}
	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public List<Object> getEndType() {
		String[] ids=endTypeId.split(",");
		endType=new ArrayList<Object>();
		if(ArrayUtils.isNotEmpty(ids)){
			for (int i = 0; i < ids.length; i++) {
				endType.add(ids[i]);
			}
		}
		return endType;
	}
	public void setEndType(List<Object> endType) {
		this.endType = endType;
	}
	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public List<Map<String, Object>> getEndTypeList() {
		//return service.getEndTypeList(oneOff);
		return service.getEndTypeList("11".equalsIgnoreCase(productId)?oneOff:productId);
	}
	public String getQuoteStatus() {
		return quoteStatus;
	}
	public void setQuoteStatus(String quoteStatus) {
		this.quoteStatus = quoteStatus;
	}
	public List<Map<String, Object>> getBrokerList() {
		return brokerList;
	}
	public String getSearchBy() {
		return searchBy;
	}
	public void setSearchBy(String searchBy) {
		this.searchBy = searchBy;
	}
	public String getSearchValue() {
		return searchValue;
	}
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	public String getIssuer() {
		return issuer;
	}
	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}
	public String getDisplay() {
		return display;
	}
	public void setDisplay(String display) {
		this.display = display;
	}
	public String getMenuFrom() {
		return menuFrom;
	}
	public void setMenuFrom(String menuFrom) {
		this.menuFrom = menuFrom;
	}
	public String getLapsedRemarks() {
		return lapsedRemarks;
	}
	public void setLapsedRemarks(String lapsedRemarks) {
		this.lapsedRemarks = lapsedRemarks;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public List<Map<String, Object>> getLapsedReason() {
		return lapsedReason;
	}
	public void setLapsedReason(List<Map<String, Object>> lapsedReason) {
		this.lapsedReason = lapsedReason;
	}
	public List<Map<String, Object>> getLapsedQuote() {
		return lapsedQuote;
	}
	public void setLapsedQuote(List<Map<String, Object>> lapsedQuote) {
		this.lapsedQuote = lapsedQuote;
	}
	public String getLinkType() {
		return linkType;
	}
	public void setLinkType(String linkType) {
		this.linkType = linkType;
	}
	public String getApplicationNo() {
		return applicationNo;
	}
	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}
	public String getQuoteNo() {
		return quoteNo;
	}
	public void setQuoteNo(String quoteNo) {
		this.quoteNo = quoteNo;
	}
	public String getUserLoginId() {
		return userLoginId;
	}
	public void setUserLoginId(String userLoginId) {
		this.userLoginId = userLoginId;
	}
	public String getPolicyStartDate() {
		return policyStartDate;
	}
	public void setPolicyStartDate(String policyStartDate) {
		this.policyStartDate = policyStartDate;
	}
	public String getPolicyEndDate() {
		return policyEndDate;
	}
	public void setPolicyEndDate(String policyEndDate) {
		this.policyEndDate = policyEndDate;
	}
	public String getBrokerCode() {
		return brokerCode;
	}
	public void setBrokerCode(String brokerCode) {
		session.put("brokerCode", brokerCode);
		this.brokerCode = brokerCode;
	}
	public String getOpenCoverNo() {
		return openCoverNo;
	}
	public void setOpenCoverNo(String openCoverNo) {
		this.openCoverNo = openCoverNo;
		session.put("openCoverNo", openCoverNo);
	}
	public String getOpenCustomerId() {
		return openCustomerId;
	}
	public void setOpenCustomerId(String openCustomerId) {
		this.openCustomerId = openCustomerId;
			session.put("customer_id", openCustomerId);
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
		if("PS".equalsIgnoreCase(request.getParameter("reqFrom"))){
			session.put("product_id", productId);
		}
	}
	public String getMenuType() {
		return menuType;
	}
	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}
	public List<Object> getExistingQuote() {
		return existingQuote;
	}
	public List<Map<String, Object>> getPolicyList() {
		return policyList;
	}
	public void setPolicyList(List<Map<String, Object>> policyList) {
		this.policyList = policyList;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
		if("BS".equalsIgnoreCase(request.getParameter("searchFrom"))){
			session.put("userName", loginId);
		}
	}
	public String getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	public List<Map<String, Object>> getDeclarationPolicy() {
		return declarationPolicy;
	}
	public void setDeclarationPolicy(List<Map<String, Object>> declarationPolicy) {
		this.declarationPolicy = declarationPolicy;
	}
	public List<Map<String, Object>> getUserSelection() {
		return service.getUserList((String)session.get("user"), productId,StringUtils.defaultIfEmpty(issuer, ""), searchBy, actualBranch);
	}
	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public List<Map<String, Object>> getQuotationInfo() {
		return new PremiumService().getQuotationInformation(applicationNo, branchCode);
	}
	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public String getOriginalPolicyNo(){
		return service.getOriginalPolicyNo(openCoverNo);
	}

	public String init(){
		try{
			if("RA".equals(menuFrom) && "RA".equals(quoteStatus))
				menuType="RA";
			else if("RR".equals(menuFrom) && "RR".equals(quoteStatus))
				menuType="RR";
			if(session.get("MSG")!=null) {
				addActionMessage(session.get("MSG").toString());
				session.remove("MSG");
			}
			if(StringUtils.isNotEmpty(productId)){
				session.put("product_id", productId);
			}
			if("30".equals(productId)){
				session.put("scheme_id", schemeId);
				session.put("ContentType", contenTypeId);
			}else if(StringUtils.isEmpty(productId) && StringUtils.isNotEmpty((String)session.get("product_id"))){
				productId=(String)session.get("product_id");
			}
			issuer=(String)session.get("RSAISSUER")==null?"":(String)session.get("RSAISSUER");
			openCoverNo=(String)session.get("openCoverNo")==null?"":(String)session.get("openCoverNo");
			if(openCover.equalsIgnoreCase(productId) && !"User".equalsIgnoreCase(userType)){
				if(StringUtils.isNotBlank(issuer)){
					loginId=(String)session.get("userName");
					session.put("BROKER_LOGIN_ID", loginId);
				}else{
					session.put("BROKER_LOGIN_ID", (String)session.get("userName"));
				}
			
				
			}else if("User".equalsIgnoreCase(userType)){
				loginId=(String)session.get("user");
				session.put("BROKER_LOGIN_ID", loginId);
			}
			if(StringUtils.isNotBlank(getOtherPolicySearch()) && !"E".equalsIgnoreCase(menuType)) {
				searchField = "gotherPolicyNo";
				searchOper = "cn";
				searchString = otherPolicySearch;
				searchBy="gotherPolicyNo";
			}
			
			
			if("E".equalsIgnoreCase(menuType))
			{
				loginId = StringUtils.isNotBlank(endtLoginId)?endtLoginId:loginId;
				List list=service.getSingleInfo("GET_ENDT_INFO",new String[]{loginId, productId,(StringUtils.isBlank(policyNo)?"":policyNo)});
				if(list!=null && list.size()>0)
				{
					Map map=(Map)list.get(0);
					referalStatus=map.get("REF_STATUS").toString();
					endtStatus=map.get("ENDT_STATUS").toString();
					String status=map.get("STATUS").toString();
					if("E".equals(status) && "Y".equals(endtStatus)){
						endtStatus="Y";
					}else if("P".equals(status) && "Y".equals(endtStatus)){
						endtStatus="N";
						appNo=map.get("APPLICATION_NO").toString();
						quotationNo=map.get("QUOTE_NO").toString();
					}else if("C".equals(status) && "Y".equals(endtStatus)){
						endtStatus="Y";
						appNo=map.get("APPLICATION_NO").toString();
						quotationNo=map.get("QUOTE_NO").toString();
					}
				}
			}
			if("PR".equals(menuType)){
				validateFields();
				if(!hasActionErrors()){
					if ("11".equals(productId)) 
						openCoverNo=(String)session.get("openCoverNo")==null?"":(String)session.get("openCoverNo");
					else
						openCoverNo="";
					//policyList=service.getReportList(openCover.equalsIgnoreCase(productId)?loginId:userLoginId,productId,issuer,menuType,getOriginalPolicyNo(),policyStartDate,policyEndDate,quoteNo,policyNo,"","","","","",rsaBranch);
					//policyList=service.getReportList1(mainbranch, getBrokerBranch(), policyStartDate, policyEndDate,userLoginId,issuer,loginId,userType,productId,getOriginalPolicyNo());
					//bean.setProductId(productId);
					
					//policyList=service.getReportList(openCover.equalsIgnoreCase(productId)?loginId:userLoginId,productId,issuer,menuType,getOriginalPolicyNo(),policyStartDate,policyEndDate,quoteNo,policyNo,"","","","","",branchCode);
					policyList=service.getReportList(userLoginId,productId,issuer,menuType,getOriginalPolicyNo(),policyStartDate,policyEndDate,quoteNo,policyNo,"","","","","",branchCode);
				}else{
					menuType="R";	
					policyList=service.getReportList(loginId,productId,issuer,menuType,openCoverNo,policyStartDate,policyEndDate,quoteNo,policyNo,"","","","","",branchCode);
				}
				if(StringUtils.isNotBlank(policyStartDate))
					policyStartDate=dateFormat(policyStartDate);
				if(StringUtils.isNotBlank(policyEndDate))
					policyEndDate=dateFormat(policyEndDate);
			}
			session.put("LANG", "Y");
			/*if("PR".equals(menuType)){
				validateFields();
				if(!hasActionErrors()){
					policyList=service.getReportList(openCover.equalsIgnoreCase(productId)?loginId:userLoginId,productId,issuer,menuType,openCoverNo,policyStartDate,policyEndDate,quoteNo,policyNo);					
				}else{
					menuType="R";	
					policyList=service.getReportList(loginId,productId,issuer,menuType,openCoverNo,policyStartDate,policyEndDate,quoteNo,policyNo);
				}
			}else if(StringUtils.isNotEmpty(searchValue) && !"E".equalsIgnoreCase(menuType)){
				policyList=service.getSearchResult(this);
			}else if(StringUtils.isNotEmpty(loginId)){
				policyList=service.getReportList(loginId,productId,issuer,menuType,openCoverNo,policyStartDate,policyEndDate,quoteNo,policyNo);
			}*/					
		}catch(Exception e){
			logger.debug("EXCEPTION @ "+e);
		}
		return SUCCESS;		
	}
	
	public String mail() throws BaseException 
	{	
		logger.info("linkType"+linkType);
		if("QUOTEMAIL".equals(linkType)){
	    	//premium.sendMail(applicationNo, (String)session.get("user1"), refStatus);
			linkType = "MAIL";
			reqFrom = "QUOTEMAIL";
			lapsedQuote=service.getLapsedQuote(quoteNo,productId);
	    	return "links";	
		}
		else if("MAIL".equals(linkType)){
	    	//premium.sendMail(applicationNo, (String)session.get("user1"), refStatus);
	    	validateQuoteMail();
	    	if(!hasActionErrors()) {
	    		reqFrom = "";
	    		premium.quoteMail(applicationNo, (String)session.get("user1"), refStatus, quoteMailIds);
	    	}
	    	else {
	    		linkType = "MAIL";
				reqFrom = "QUOTEMAIL";
	    		lapsedQuote=service.getLapsedQuote(quoteNo,productId);
	    	}
		    return "links";	
		}else if("LAPSED".equals(linkType)){
			logger.info((String)session.get("LoginBranchCode"));
			lapsedReason=service.getLapsedReason((String
					)session.get("LoginBranchCode"));
			lapsedQuote=service.getLapsedQuote(quoteNo,productId);
			return "links";	
		}else if("ACTIVE".equals(linkType)){
			service.activeLapsed(quoteNo,productId);
			return "redirect";
		}else{
			validateRejectMail();
			if(!hasActionErrors())
				service.updateLabsed(quoteNo, lapsedRemarks, loginId, productId);
			else {
				linkType="LAPSED";
				mail();
			}
		}return "links";
	}
	private void validateQuoteMail() {
		Validation validation=new Validation();
		if(StringUtils.isBlank(quoteMailIds)) {
			this.addActionError(getText("error.quotation.email"));
		}
		else if("invalid".equalsIgnoreCase(validation.emailValidate(quoteMailIds))) {
			this.addActionError(getText("error.quotation.email.invalid"));
		}
	}
	private void validateRejectMail(){
		if(StringUtils.isBlank(lapsedRemarks))
			this.addActionError("Please select Rejected Quote Reason");
	}
	
	public String declaration()
	{	
		logger.info("policyNo"+policyNo);
		declarationPolicy=service.getDeclarationPolicyList(policyNo);
		return "declarationPolicy";
	}
	private void validateFields() {
		if(StringUtils.isEmpty(policyStartDate))
			addActionError(getText("error.report.policyStartDate"));
		if(StringUtils.isEmpty(policyEndDate))
			addActionError(getText("error.report.policyEndDate"));
		if(!hasActionErrors()){
			try{
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		    	Date sdate = sdf.parse(policyStartDate);
		    	Date edate = sdf.parse(policyEndDate);
		    	if(sdate.after(edate))
		    		addActionError("End date should be greater than or equal to Start Date");
			}catch(Exception e){
				e.printStackTrace();
			}
		} 
		if(!"11".equals(productId)&&StringUtils.isEmpty(userLoginId))
			addActionError(getText("error.report.userBroker"));
	}
	public String initSearch()
	{	
		display="init";
		return "search";
	}
	public String search() {	
		display="search";
		validateSearchFields();
		if(!hasActionErrors()){
			policyList=service.getSearchResult(this, userType);
		}
		return "searchResult";
	}
	public String brokerList()
	{
		request.setAttribute("ELEMENT_NAME", "brokerList");
		brokerList=service.getUserList(loginId, productId,StringUtils.defaultIfEmpty(issuer, ""), searchBy, actualBranch);
		return "dropdown";
	}
	public String viewQuote()
	{
		return "viewQuote";
	}
	private void validateSearchFields() {
		if((StringUtils.isEmpty(searchBy)))
		{
			addActionError(getText("error.searchBy.empty"));
		}
		if(StringUtils.isEmpty(loginId))
		{
			addActionError(getText("error.loginId.empty"));
		}
		if(StringUtils.isEmpty(searchValue))
		{
			addActionError(getText("error.searchValue.empty"));
		}
		if(!(StringUtils.isNumeric(searchValue)) &&  StringUtils.isNotEmpty(searchValue) && "quoteNo".equals(searchBy))
		{
			addActionError(getText("error.searchValue.invalid"));
		}
	}
	/*public String endorsement()
	{
		if(!CollectionUtils.isEmpty(endType)){
			endTypeId=StringUtils.join(endType, ",");
			Map <String ,Object> map=null;
			if("31".equalsIgnoreCase(productId)||"32".equalsIgnoreCase(productId)||"33".equalsIgnoreCase(productId)||"41".equalsIgnoreCase(productId)||"65".equalsIgnoreCase(productId)){
				logger.info("Old App No=>"+applicationNo+"Old Quote No=>"+quoteNo);
				map=new CopyQuoteService().travelcopyQuote(loginId, quoteNo, productId, branchCode,endTypeId);
				this.applicationNo=StringUtils.defaultIfEmpty((String)map.get("APPLICATION_NO"), "");
				String status=StringUtils.defaultIfEmpty((String)map.get("ERROR_STATUS"),"");
				this.quoteNo=StringUtils.defaultIfEmpty((String)map.get("QUOTE_NO"), "");
				logger.info("New App No=>"+applicationNo+"New Quote No=>"+quoteNo);
				if(!"".equals(status)){
					addActionError(status);
				}
			}else{
			    map=new CopyQuoteService().copyQuote(loginId, quoteNo,"Endt", endTypeId);
				String status=StringUtils.defaultIfEmpty((String)map.get("ERROR_STATUS"),"");
				this.quoteNo=StringUtils.defaultIfEmpty((String)map.get("QUOTE_NO"), "");
				if(!"Y".equalsIgnoreCase(status)){
					addActionError(status);
				}
			}
		}else{
			addActionError(getText("error.endt.type"));
		}
		return getActionErrors().isEmpty()?"33".equalsIgnoreCase(productId)?"travelEndorsement":"41".equalsIgnoreCase(productId)?"healthEndorsement":"endorsement":SUCCESS;
	}*/
	public String endorsement() {
		try{
				if(!CollectionUtils.isEmpty(endType)){
					endTypeId=StringUtils.join(endType, ",");
					if((StringUtils.contains(endTypeId, "41")) && endType.size()>1){
						addActionError("Please select Cancellation of Certificate/Individual Policy only");
					}else if(StringUtils.contains(endTypeId, "41") && "N".equalsIgnoreCase(cancelYN)){
						addActionError(getText("error.cancel.confirm"));
					}else if(StringUtils.contains(endTypeId, "41") && StringUtils.isBlank(cancelRemarks==null?"":cancelRemarks.trim())){
						addActionError(getText("error.cancel.remarks"));
					}
					if(!hasActionErrors()){
						quoteNo=service.getEndtQuoteNo(quoteNo);
						Map <String ,Object> map=new CopyQuoteService().copyQuote(loginId, quoteNo,"Endt", endTypeId, StringUtils.isBlank(issuer)?"1":issuer);		
						String status=(String)map.get("ERROR_STATUS")==null?"":(String)map.get("ERROR_STATUS");
						this.quoteNo=StringUtils.defaultIfEmpty((String)map.get("QUOTE_NO"), "");
						if(!"Y".equalsIgnoreCase(status)){
							addActionError(status);
						}else if("41".equals(endTypeId)){
							map=service.getQuoteInfo(quoteNo);
							if(map==null || map.size()>0){
								new PolicyGenerationAction((map.get("REFERENCE_NUMBER")==null?"":map.get("REFERENCE_NUMBER").toString()), userType).quoteGeneration();
			      				//PremiumDAO premiumDAO=new PremiumDAO();
		  						status=new PremiumService().policyGeneration(StringUtils.defaultIfEmpty((map.get("REFERENCE_NUMBER")==null?"":map.get("REFERENCE_NUMBER").toString()), (map.get("application_no")==null?"":map.get("application_no").toString())));
		  	  					if(StringUtils.isEmpty(status) || "invalid".equalsIgnoreCase(status)){
						      		addActionError(getText("error.premiumInfo.policy.invalid"));
						      	}else if(status.indexOf("ERROR")!=-1){
						      		addActionError(status);
						      	}else{
					      			policyNo=status;
					      			service.getUpdateEndtStatus(quoteNo,cancelRemarks);
					      			status="policyInfo";
						      	}
								applicationNo=map.get("application_no")==null?"":map.get("application_no").toString();
								return status;
							}else
								addActionError("Invalid Endorsement");
						}
					}
				}else{
					addActionError(getText("error.endt.type"));
				}
		}catch(Exception e){
			e.printStackTrace();
			addActionError("Error Occured");
		}
		return getActionErrors().isEmpty()?"endorsement":SUCCESS;
	}
	
	public String document(){
		try {
			String filePath1 = getText("ENDT_SCHEDULE_PATH")+"/"+policyNo.replaceAll("/", "-")+"-Endt.pdf";
			String filePath=this.request.getSession().getServletContext().getRealPath(filePath1);
			//String fontPath = request.getSession().getServletContext().getRealPath("/" + getText("SCHEDULE_FONT_PATH"));
			String url=request.getSession().getServletContext().getRealPath("/"+ getText("IMAGE_PATH"));
			File file=new File(filePath);
			if(!file.exists()){
				/*List<Map<String, Object>> list=service.getEndtPolicyInfo(branchCode, policyNo);
				DocGenerator.writeDebitPDF(fontPath, policyNo, filePath, url, StringUtils.defaultIfEmpty((String)session.get("userLoginMode"), ""), list, CollectionUtils.arrayToList(endTypeId.split(",")), applicationNo, branchCode,belongingBranch);*/
				JasperReports jr = new JasperReports();
				jr.certificateEndt(policyNo, applicationNo, branchCode, belongingBranch, filePath);
			}
			session.put("pdfFilePath",filePath1);
			//inputStream=new FileInputStream(file);
		} catch (Exception e) {
			//inputStream=new ByteArrayInputStream("File Not Found".getBytes());
			logger.debug(e);
		}
		//return "stream";
		return "redirectPDFReport";
	}
	public String lang() {
		return INPUT;
	}
	
	public List<ReportBean> gridRaja(List<ReportBean> list){
		List<ReportBean>selectedList =  new ArrayList<ReportBean>();
		try{
		    int to = (rows * page);
		    int from = to - rows;
		    records = list.size();
	        if(to > records)
	            to =records;
	        for(int i=from;i<to;i++){
	        	selectedList.add(list.get(i));
	        }
	       /* if (selectedList != null && sord!= null && sord.equalsIgnoreCase("asc")) {
		            Collections.sort(selectedList, null);
		    }
		    if (sord!= null && sord.equalsIgnoreCase("desc")) {
		            Collections.sort(selectedList, null);
		            Collections.reverse(selectedList);
		    }*/
		    total =(int) Math.ceil((double)records / (double)rows);
		}catch(Exception e){
			e.printStackTrace();
		}
		logger.info("hi selectedList===>"+selectedList.size());
		return selectedList;
	}
	
	public String getJson(){
		setReportGridList(gridReport());
		return SUCCESS;
	}
	
	private List<ReportBean> gridReport(){
		logger.info("hi grid");
		logger.info("hi grid=>"+policyNo);
		List<ReportBean> policyList=new ArrayList<ReportBean>();
		List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
		if("PR".equals(menuType))
			list=service.getReportList(openCover.equalsIgnoreCase(productId)?loginId:userLoginId,productId,issuer,menuType,originalOpenCoverNo,policyStartDate,policyEndDate,quoteNo,policyNo,searchBy,searchValue, searchField, searchString, searchOper,branchCode);					
		else if(StringUtils.isNotEmpty(searchBy)&& StringUtils.isBlank(reqFrm))
			list=service.getReportList(loginId,productId,issuer,menuType,originalOpenCoverNo,policyStartDate,policyEndDate,quoteNo,policyNo,searchBy,searchValue,searchField, searchString, searchOper,branchCode);
		else if(StringUtils.isNotEmpty(loginId))
			list=service.getReportList(loginId,productId,issuer,menuType,originalOpenCoverNo,policyStartDate,policyEndDate,quoteNo,policyNo,searchBy,searchValue, searchField, searchString, searchOper,branchCode);
		if(list!=null && list.size()>0){
			for(int i=0; i<list.size(); i++){
				Map map =(Map)list.get(i);
				ReportBean ra=new ReportBean();
				ra.setGapplicationNo(map.get("APPLICATION_NO")==null?"":map.get("APPLICATION_NO").toString());
				ra.setGquoteNo(map.get("QUOTE_NO")==null?"":map.get("QUOTE_NO").toString());
				ra.setGcustomerId(map.get("CUSTOMER_ID")==null?"":map.get("CUSTOMER_ID").toString());
				ra.setGquoteDate(map.get("QUOTATION_DATE")==null?"":map.get("QUOTATION_DATE").toString());
				ra.setGvalidityDate(map.get("VALIDITY_DATE")==null?"":map.get("VALIDITY_DATE").toString());
				ra.setGcustName(map.get("CUSTOMER_NAME")==null?"":map.get("CUSTOMER_NAME").toString());
				ra.setGloginId(map.get("LOGIN_ID")==null?"":map.get("LOGIN_ID").toString());
				ra.setgPolInsDate(map.get("INCEPTION_DATE")==null?"":map.get("INCEPTION_DATE").toString());
				ra.setGpremium(map.get("PREMIUM")==null?"":map.get("PREMIUM").toString());
				ra.setGedit(((map.get("APPLICATION_NO")==null?"":map.get("APPLICATION_NO").toString())+","+(map.get("QUOTE_NO")==null?"":map.get("QUOTE_NO").toString())+
						",'"+menuType+"',"+(map.get("CUSTOMER_ID")==null?"":map.get("CUSTOMER_ID").toString())));
				if("RR".equalsIgnoreCase(menuType)) {
					ra.setGlapsedRemark(map.get("ADMIN_REMARKS")==null?"":map.get("ADMIN_REMARKS").toString());
				}
				else {
					ra.setGlapsedRemark(map.get("LAPSED_REMARKS")==null?"":map.get("LAPSED_REMARKS").toString());
				}
				ra.setGlapsedDate(map.get("LAPSED_DATE")==null?"":map.get("LAPSED_DATE").toString());
				ra.setGvalidityPeriod(map.get("VALIDITY_PERIOD")==null?"":map.get("VALIDITY_PERIOD").toString());
				ra.setGstatus(map.get("STATUS")==null?"":map.get("STATUS").toString());
				ra.setGpolicyNo(map.get("POLICY_NO")==null?"":map.get("POLICY_NO").toString());
				ra.setGbrokerName(map.get("BROKER_NAME")==null?"":map.get("BROKER_NAME").toString());
				ra.setGoverAllPremium(map.get("OVERALL_PREMIUM")==null?"":map.get("OVERALL_PREMIUM").toString());
				ra.setGdebitNoteNo(map.get("DEBIT_NOTE_NO")==null?"":map.get("DEBIT_NOTE_NO").toString());
				ra.setGcreditNoteNo(map.get("CREDIT_NOTE_NO")==null?"":map.get("CREDIT_NOTE_NO").toString());
				ra.setGpolicyWording((map.get("POLICY_NO")==null?"":map.get("POLICY_NO").toString())
						+"&loginid="+ loginId);
				ra.setGtransactionId(map.get("TRANSACTION_ID")==null?"":map.get("TRANSACTION_ID").toString());
				ra.setGtransactionId1(map.get("TRANSACTION_ID")==null?"":map.get("TRANSACTION_ID").toString());
				ra.setGinvalidRecords(map.get("INVALID_RECORDS")==null?"":map.get("INVALID_RECORDS").toString());
				ra.setGocCustName(map.get("OPENCOVER_CUST_NAME")==null?"":map.get("OPENCOVER_CUST_NAME").toString());
				ra.setGfirstName(map.get("FIRST_NAME")==null?"":map.get("FIRST_NAME").toString());
				ra.setGaddress(map.get("ADDRESS")==null?"":map.get("ADDRESS").toString());
				ra.setGcustomerCivilId(map.get("CUSTOMER_SOURCE")==null?"":map.get("CUSTOMER_SOURCE").toString());
				ra.setGemail(map.get("EMAIL")==null?"":map.get("EMAIL").toString());
				ra.setGcustMisspiCode(map.get("MISSIPPI_CUSTOMER_CODE")==null?"":map.get("MISSIPPI_CUSTOMER_CODE").toString());
				ra.setGvatRegNo(map.get("VAT_REG_NO")==null?"":map.get("VAT_REG_NO").toString());
				if(StringUtils.isBlank(ra.getGvatRegNo()))
					ra.setGupdateVat(map.get("MISSIPPI_CUSTOMER_CODE")==null?"":"'"+ map.get("MISSIPPI_CUSTOMER_CODE").toString()+"'");
				/*if(StringUtils.isNotBlank(ra.getGemail()))
					ra.setGemailSent(((map.get("APPLICATION_NO")==null?"":map.get("APPLICATION_NO").toString())+",'MAIL',"+(map.get("QUOTE_NO")==null?"":map.get("QUOTE_NO").toString())));
				else
					ra.setGemailSent("");*/
				ra.setGemailSent(((map.get("APPLICATION_NO")==null?"":map.get("APPLICATION_NO").toString())+",'QUOTEMAIL',"+(map.get("QUOTE_NO")==null?"":map.get("QUOTE_NO").toString())));
				ra.setGmobile(map.get("MOBILE")==null?"":map.get("MOBILE").toString());
				ra.setGstatus(map.get("STATUS")==null?"":map.get("STATUS").toString());
				ra.setGrefSatus(map.get("REF_STATUS")==null?"":map.get("REF_STATUS").toString());
				ra.setGreject(((map.get("APPLICATION_NO")==null?"":map.get("APPLICATION_NO").toString())+",'LAPSED',"+(map.get("QUOTE_NO")==null?"":map.get("QUOTE_NO").toString())));
				ra.setGreject1(("'','LAPSED',"+(map.get("QUOTE_NO")==null?"":map.get("QUOTE_NO").toString())));
				//if("P".equals(ra.getGstatus())) {
					ra.setGschedule(((map.get("POLICY_NO")==null?"":map.get("POLICY_NO").toString())+"&loginid="+(map.get("LOGIN_ID")==null?"":map.get("LOGIN_ID").toString())));
				//}
				ra.setGendorse(("'E',"+(map.get("QUOTE_NO")==null?"":map.get("QUOTE_NO").toString())+",'"+(map.get("POLICY_NO")==null?"":map.get("POLICY_NO").toString())+"','"+(map.get("CUSTOMER_NAME")==null?"":map.get("CUSTOMER_NAME").toString().replaceAll("'", ""))+"','"+(map.get("BROKER_NAME")==null?"":map.get("BROKER_NAME").toString().replaceAll("'", ""))+ "','" + (map.get("LOGIN_ID")==null?"":map.get("LOGIN_ID").toString()) + "'"));
				ra.setGlcupload(("'"+(map.get("POLICY_NO")==null?"":map.get("POLICY_NO").toString())+"'"));
				
				ra.setGactive(("'','ACTIVE',"+(map.get("QUOTE_NO")==null?"":map.get("QUOTE_NO").toString())));
				ra.setGdeactive(("'','LAPSED',"+(map.get("QUOTE_NO")==null?"":map.get("QUOTE_NO").toString())));
				ra.setGview("'"+(map.get("APPLICATION_NO")==null?"":map.get("APPLICATION_NO").toString())+"',"+(map.get("QUOTE_NO")==null?"":map.get("QUOTE_NO").toString())+",'"+productId+"'");
				ra.setGprint1(("'PdfSummary_Draft.Travel?quoteNo="+(map.get("QUOTE_NO")==null?"":map.get("QUOTE_NO").toString())+"'"));
				ra.setGtotalCert("<a type='button' class='btn btn-success btn-oval' name='count'  href='javascript:void(0);' onclick='declaration(&#39;"+(map.get("POLICY_NO")==null?"":map.get("POLICY_NO").toString())+"&#39;)'>"+(map.get("POLICY_COUNT")==null?"":map.get("POLICY_COUNT").toString())+"</a>");
				String creditNoteStatus="Y";
				String debitNoteStatus="Y";
				try {
					String premium=map.get("PREMIUM")==null?"0":map.get("PREMIUM").toString();
					if(Double.parseDouble(premium)>0){
						creditNoteStatus=getCreditNoteStatus();
					}else{
						debitNoteStatus=getCreditNoteStatus();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				if(StringUtils.isNotBlank(ra.getGdebitNoteNo())&&"Y".equalsIgnoreCase(debitNoteStatus)){
					ra.setGdebit(((map.get("POLICY_NO")==null?"":map.get("POLICY_NO").toString())+"&loginid="+(map.get("LOGIN_ID")==null?"":map.get("LOGIN_ID").toString())));
					ra.setGdebitVat(((map.get("POLICY_NO")==null?"":map.get("POLICY_NO").toString())+"&loginid="+(map.get("LOGIN_ID")==null?"":map.get("LOGIN_ID").toString())));
					ra.setGdebit1(map.get("QUOTE_NO")==null?"":map.get("QUOTE_NO").toString());
					ra.setGreceipt(map.get("QUOTE_NO")==null?"":map.get("QUOTE_NO").toString());
				}else{
					ra.setGdebit("");
					ra.setGdebit1("");
					ra.setGreceipt("");
					ra.setGdebitVat("");
				}if(StringUtils.isNotBlank(ra.getGcreditNoteNo())&&"Y".equalsIgnoreCase(creditNoteStatus)){
					ra.setGcredit(map.get("POLICY_NO")==null?"":map.get("POLICY_NO").toString());
					ra.setGcreditVat(map.get("POLICY_NO")==null?"":map.get("POLICY_NO").toString());
				}
				else{
					ra.setGcredit("");
					ra.setGcreditVat("");
				}
					
				
				ra.setGendtStatus(map.get("ENDT_STATUS")==null?"":map.get("ENDT_STATUS").toString());
				ra.setGendtType(map.get("ENDT_TYPE")==null?"":map.get("ENDT_TYPE").toString());
				ra.setGrefStatus(map.get("REF_STATUS")==null?"":map.get("REF_STATUS").toString());
				String str=request.getContextPath();
				//System.out.println("menuBlocker..."+ menuBlocker);
				//if(issuer !=null && issuer.length() > 0){
					//if("E".equals(ra.getGstatus()) && "Y".equals(ra.getGendtStatus())){
					if("E".equals(ra.getGstatus()) && "Y".equals(ra.getGendtStatus()) && (issuer !=null && issuer.length() > 0  || "broker".equalsIgnoreCase(userType))){
						ra.setGendtSchedule("<a class='btn btn-success btn-oval' onclick=endorsementType(&#39;ET&#39;,&#39;P&#39;,&#39;"+ra.getGendtType()+"&#39;,&#39;"+ra.getGquoteNo()+"&#39;,&#39;"+ra.getGrefSatus()+"&#39;,&#39;"+ra.getGapplicationNo()+"&#39;)>Edit</a>");
						//endtStatus="Y";
					}else if("P".equals(ra.getGstatus()) && "Y".equals(ra.getGendtStatus())){
						ra.setGendtSchedule("<a class='btn btn-success btn-oval' onclick=popUp(&#39;"+str+"/documentReport.action?policyNo="+(map.get("POLICY_NO")==null?"":map.get("POLICY_NO").toString())+"&applicationNo="+ra.getGapplicationNo()+"&endTypeId="+ra.getGendtType()+"&#39;,&#39;1000&#39;,&#39;500&#39;)>Endt Schedule</a>");
						endtStatus="N";
						quoteNo=ra.getGquoteNo();
						applicationNo=ra.getGapplicationNo();
					}else if("C".equals(ra.getGstatus()) && "Y".equals(ra.getGendtStatus())){
						ra.setGendtSchedule("<a class='btn btn-success btn-oval' onclick=popUp(&#39;"+str+"/documentReport.action?policyNo="+(map.get("POLICY_NO")==null?"":map.get("POLICY_NO").toString())+"&applicationNo="+ra.getGapplicationNo()+"&endTypeId="+ra.getGendtType()+"&#39;,&#39;1000&#39;,&#39;500&#39;)>Endt Schedule</a>");
						endtStatus="Y";
						quoteNo=ra.getGquoteNo();
						applicationNo=ra.getGapplicationNo();
					}else if("P".equals(ra.getGstatus()) || "C".equals(ra.getGstatus()) || "D".equals(ra.getGstatus()))
						ra.setGendtSchedule("<a class='btn btn-success btn-oval' onclick=popUp(&#39;"+str+"/documentReport.action?policyNo="+(map.get("POLICY_NO")==null?"":map.get("POLICY_NO").toString())+"&applicationNo="+ra.getGapplicationNo()+"&endTypeId="+ra.getGendtType()+"&#39;,&#39;1000&#39;,&#39;500&#39;)>Endt Schedule</a>");
					 
					/*if("E".equals(ra.getGstatus()) && "Y".equals(ra.getGendtStatus())){
						ra.setGendtSchedule("<a href='#' onclick=endorsementType(&#39;ET&#39;,&#39;P&#39;,&#39;"+ra.getGendtType()+"&#39;,&#39;"+ra.getGquoteNo()+"&#39;,&#39;"+ra.getGrefSatus()+"&#39;,&#39;"+ra.getGapplicationNo()+"&#39;)>Edit</a>");
					}else if("P".equals(ra.getGstatus()) && "Y".equals(ra.getGendtStatus())){
						ra.setGendtSchedule("<a href='#' onclick=popUp(&#39;"+str+"/Copyofinformation.jsp?policyMode=schedule&policynumber="+(map.get("POLICY_NO")==null?"":map.get("POLICY_NO").toString())+"&loginid="+(map.get("LOGIN_ID")==null?"":map.get("LOGIN_ID").toString())+"&#39;,&#39;1000&#39;,&#39;500&#39;)>Endt Schedule</a>");
					}else if("C".equals(ra.getGstatus()) && "Y".equals(ra.getGendtStatus())){
						ra.setGendtSchedule("<a href='#' onclick=popUp(&#39;"+str+"/Copyofinformation.jsp?policyMode=schedule&policynumber="+(map.get("POLICY_NO")==null?"":map.get("POLICY_NO").toString())+"&loginid="+(map.get("LOGIN_ID")==null?"":map.get("LOGIN_ID").toString())+"&#39;,&#39;1000&#39;,&#39;500&#39;)>Endt Schedule</a>");
					}else if("P".equals(ra.getGstatus())||"C".equals(ra.getGstatus())){
						ra.setGendtSchedule("<a href='#' onclick=popUp(&#39;"+str+"/Copyofinformation.jsp?policyMode=schedule&policynumber="+(map.get("POLICY_NO")==null?"":map.get("POLICY_NO").toString())+"&loginid="+(map.get("LOGIN_ID")==null?"":map.get("LOGIN_ID").toString())+"&#39;,&#39;1000&#39;,&#39;500&#39;)>Endt Schedule</a>");
					}*/
				//}
				policyList.add(ra);
			}
		}
		return policyList; //gridRaja(policyList); 
	}
	
	public String customerInfo(){
		return "customerInfo";
	}
	
	public String report()throws IOException
	{
		fileName = "PolicyReport";
		//bean.setStarDate(dateFormat(policyStartDate));
		//bean.setEndDate(dateFormat(policyEndDate));
		//bean.setLoginid(userLoginId);		

		String filePath = request.getSession().getServletContext().getRealPath("/"+"/Jasper/"+fileName+".jasper");	
		String creditNoteStatus="";
		JasperReports jasperReports=new JasperReports();
		ByteArrayOutputStream bos=new ByteArrayOutputStream();
		if("PolicyReport".equalsIgnoreCase(fileName))
			creditNoteStatus=getCreditNoteStatus();
		jasperReports.export(dateFormat(policyStartDate),dateFormat(policyEndDate),userLoginId,filePath,bos,fileName,downloadType,creditNoteStatus); 
		inputStream=new ByteArrayInputStream(bos.toByteArray());		  	

		if("excel".equals(downloadType))
		{
			return "excel";
		}
		if("pdf".equals(downloadType))
		{
			return "pdf";
		}
		return "download";
	}
	
	public String clausespdf() {
		try {
			session.put("pdfFilePath","/clauses/"+fileName);
		}
		catch(Exception ex) {
			logger.debug("Exception @ clausespdf() { " + ex + " } ");
		}
		return "redirectPDFReport";
	}
	public String pdf() {
		return "redirectviewPDF";
	}
	public String viewpdf() {
		String forward = "pdfReport";
		try {
			//inputStream = IOUtils.toInputStream("");
			this.fileName = "pdfReport.pdf";
			String fileName = (String)session.get("pdfFilePath");
			//session.remove("pdfFilePath");
			if(StringUtils.isNotBlank(fileName)) {
				String filePath = this.request.getSession().getServletContext().getRealPath(fileName);
				File file=new File(filePath);
				if(file.exists()){
					inputStream=new BufferedInputStream(new FileInputStream(file));
					this.fileName = fileName;
					forward = "streamPDF";
					//forward = "viewPDF";
				}
			}
		}
		catch(Exception exception) {
			logger.debug(exception);
		}
		return forward;
	}
	
	private String dateFormat(String name) {
		String arr[]=name.split("/");
		return arr[1]+"/"+arr[0]+"/"+arr[2];
	}
	public String getDownloadType() {
		return downloadType;
	}
	public void setDownloadType(String downloadType) {
		this.downloadType = downloadType;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	private List<File> upload = new ArrayList<File>();
	private List<String> uploadFileName = new ArrayList<String>();
	private List<String> uploadContentType = new ArrayList<String>();
	private List<String> lcdocremarks = new ArrayList<String>();
	private String uploadId;
	private String downloadFileName;
	
	public String getUploadId() {
		return uploadId;
	}
	public void setUploadId(String uploadId) {
		this.uploadId = uploadId;
	}
	public List<File> getUpload() {
		return upload;
	}
	public void setUpload(List<File> upload) {
		this.upload = upload;
	}
	
	public List<String> getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(List<String> uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	
	public List<String> getLcdocremarks() {
		return lcdocremarks;
	}
	public void setLcdocremarks(List<String> lcdocremarks) {
		this.lcdocremarks = lcdocremarks;
	}
	public void setLcFileName(String lcFileName) {
		this.lcFileName = lcFileName;
	}
	public String getLcFileName() {
		return lcFileName;
	}
	public void setUploadContentType(List<String> uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	public List<String> getUploadContentType() {
		return uploadContentType;
	}
	public List<Map<String, Object>> getLcFileList() {
		return service.getLcFileList(policyNo);
	}
	public void setDownloadFileName(String downloadFileName) {
		this.downloadFileName = downloadFileName;
	}
	public String getDownloadFileName() {
		return downloadFileName;
	}
	private String bankName;
	private String lcNo;
	private List lcUploadDetails;
	private List lcUploadPolicy;
	private List lcUploadPloDtls;
	private List allDocumentsList;
	public String lcUpload() {
		lcUploadDetails = service.getLcUploadDetails(policyNo);
		lcUploadPolicy = service.getLcUploadPolicy(policyNo);
		lcUploadPloDtls = service.getLcUploadPloDtls(policyNo);
		return "lcUpload";
	}
	public String lcFileUpload(){
		 try {
			String lcFilePath = getText("LC_FILE_UPLOAD_PATH")+"/"+policyNo.replaceAll("/", "-")+"_";
			System.out.println("uploadFileName:"+uploadFileName.size());
			File fileToCreate = new File(lcFilePath);
			if(upload.isEmpty()){
				addActionError(getText("Please Attach The File"));
				lcdocremarks.remove("");
			}else{
				service.insertLcFileDtls(policyNo,loginId=(String)session.get("user"),uploadFileName,fileToCreate,lcdocremarks,lcFilePath,upload);
				getLcdocremarks().clear();
			}
			lcUploadDetails = service.getLcUploadDetails(policyNo);
			lcUploadPolicy = service.getLcUploadPolicy(policyNo);
			lcUploadPloDtls = service.getLcUploadPloDtls(policyNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
			return "lcUpload";
	}
	public String deleteLcFile(){
		try{
			File deleteFile = new File(getText("LC_FILE_UPLOAD_PATH")+"/"+lcFileName);
			FileUtils.deleteQuietly(deleteFile);
			service.deleteLcFile(policyNo,uploadId);
			lcUploadDetails = service.getLcUploadDetails(policyNo);
			lcUploadPolicy = service.getLcUploadPolicy(policyNo);
			lcUploadPloDtls = service.getLcUploadPloDtls(policyNo);
			getLcdocremarks().clear();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "lcUpload";
	}
	public String updateVat(){
		try{
			System.out.println("UPdate<<<<<<<<<<<<<"+custMissippiCode);
			String vatRegNo="";
			IntegrationService intg = new IntegrationService();
			CustomerDataResponseInfo responseInfo=intg.customerDataService(custMissippiCode,"search");
			if(responseInfo.getOutPut()!=null){
				CustXmlBean custXmlBean =intg.setCustXmlBean(responseInfo.getOutPut());
				Customer   custBean=custXmlBean.getCustQueryDtls().getCustomer();
				vatRegNo=custBean.getCustVatRegNo();
				service.updateVatRegNo(custMissippiCode,vatRegNo);
				System.out.println("UPdate vatRegNo <<<<<<<<<<<<<"+vatRegNo);
				}
			

		}catch (Exception e) {
			e.printStackTrace();
		}
		return init();
	}
	public String downloadLcFile(){
		String downloadFile = getText("LC_FILE_UPLOAD_PATH")+"/"+lcFileName;
		try {
			inputStream = new FileInputStream(downloadFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return "lcdownload";
	}
	public String viewDocument(){
		allDocumentsList = service.getAllDocuments(policyNo);
		return "viewdocuments";
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankName() {
		return bankName;
	}
	public void setLcNo(String lcNo) {
		this.lcNo = lcNo;
	}
	public String getLcNo() {
		return lcNo;
	}
	public void setLcUploadDetails(List lcUploadDetails) {
		this.lcUploadDetails = lcUploadDetails;
	}
	public List getLcUploadDetails() {
		return lcUploadDetails;
	}
	public void setLcUploadPolicy(List lcUploadPolicy) {
		this.lcUploadPolicy = lcUploadPolicy;
	}
	public List getLcUploadPolicy() {
		return lcUploadPolicy;
	}
	public void setLcUploadPloDtls(List lcUploadPloDtls) {
		this.lcUploadPloDtls = lcUploadPloDtls;
	}
	public List getLcUploadPloDtls() {
		return lcUploadPloDtls;
	}
	public void setAllDocumentsList(List allDocumentsList) {
		this.allDocumentsList = allDocumentsList;
	}
	public List getAllDocumentsList() {
		return allDocumentsList;
	}
	public String getCreditNoteStatus(){
		if("User".equalsIgnoreCase(loginUserType)) {
			return premium.getCreditNoteStatus(adminLoginId,productId);
		}
		else {
			return "Y";
		}
	}
	public String getQuoteMailIds() {
		return quoteMailIds;
	}
	public void setQuoteMailIds(String quoteMailIds) {
		this.quoteMailIds = quoteMailIds;
	}
	public String getReqFrom() {
		return reqFrom;
	}
	public void setReqFrom(String reqFrom) {
		this.reqFrom = reqFrom;
	}
	public String getEndtLoginId() {
		return endtLoginId;
	}
	public void setEndtLoginId(String endtLoginId) {
		this.endtLoginId = endtLoginId;
	}
	public void setCustMissippiCode(String custMissippiCode) {
		this.custMissippiCode = custMissippiCode;
	}
	public String getCustMissippiCode() {
		return custMissippiCode;
	}
	/**
	 * @return the reportGridList
	 */
	public List<ReportBean> getReportGridList() {
		return reportGridList;
	}
	/**
	 * @param reportGridList the reportGridList to set
	 */
	public void setReportGridList(List<ReportBean> reportGridList) {
		this.reportGridList = reportGridList;
	}
	public String initx()
	{	
		display="init";
		return "paymentreport";
	}
	private List<Map<String, Object>> paymentList;
	public String payment() {
		if(StringUtils.isEmpty(policyStartDate))
			addActionError(getText("error.report.policyStartDate"));
		if(StringUtils.isEmpty(policyEndDate))
			addActionError(getText("error.report.policyEndDate"));
		if(!hasActionErrors()){
			try{
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		    	Date sdate = sdf.parse(policyStartDate);
		    	Date edate = sdf.parse(policyEndDate);
		    	if(sdate.after(edate))
		    		addActionError("End date should be greater than or equal to Start Date");
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		if(!hasActionErrors()) {
			CyberSouceIntegration cyberIntg=new CyberSouceIntegration();
			paymentList=cyberIntg.getPaymentList(policyStartDate,policyEndDate,userLoginId);
		}
		display="showreport";
		return "paymentreport";
	}
	public List<Map<String, Object>> getPaymentList() {
		return paymentList;
	}
	public void setPaymentList(List<Map<String, Object>> paymentList) {
		this.paymentList = paymentList;
	}
	public String getPaymentJson(){
		if(StringUtils.isEmpty(policyStartDate))
			addActionError(getText("error.report.policyStartDate"));
		if(StringUtils.isEmpty(policyEndDate))
			addActionError(getText("error.report.policyEndDate"));
		if(!hasActionErrors()){
			try{
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		    	Date sdate = sdf.parse(policyStartDate);
		    	Date edate = sdf.parse(policyEndDate);
		    	if(sdate.after(edate))
		    		addActionError("End date should be greater than or equal to Start Date");
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		if(!hasActionErrors()) {
			CyberSouceIntegration cyberIntg=new CyberSouceIntegration();
			paymentList=cyberIntg.getPaymentList(policyStartDate,policyEndDate,userLoginId);
			setPaymentList(paymentList);
		}
		 
		return SUCCESS;
	}
}
