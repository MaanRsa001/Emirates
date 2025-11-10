package com.maan.adminnew.underwriterManagement;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;

import com.maan.adminnew.common.CommonService;
import com.maan.common.LogUtil;
import com.maan.common.Validation;
import com.maan.common.password.passwordEnc;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


public class UnderwriterMgtAction extends ActionSupport implements ModelDriven<UnderwriterMgtBean>{
	private static final Logger logger = LogUtil.getLogger(UnderwriterMgtAction.class);
	
	private static final long serialVersionUID = 1L;
	private UnderwriterMgtBean bean = new UnderwriterMgtBean();
	UnderwriterMgtService service=new UnderwriterMgtService();
	Map<String, Object> session=ActionContext.getContext().getSession();
	String branchCode=(String)session.get("BranchCode");
	String belongingBranch=(String)session.get("BelongingBranch");
	String login_id=(String)session.get("underwriter");
	String sessionLoginId=(String)session.get("user");
	private List <Map<String, Object>> underwriterList;
	private List <Map<String, Object>> underwriterInfo;
	private List <Map<String, Object>> issuerList;
	private List <Map<String, Object>> includeIssuer;
	private List <Map<String, Object>> excludeIssuer;
	CommonService cservice=new CommonService();
	passwordEnc pass = new passwordEnc();
	private List <Map<String, Object>> commisionDetails=new ArrayList<Map<String, Object>>();
	//String mainbranch=((Map)session.get("BrokerDetails")).get("mainbranch").toString();
	
	public List<Map<String, Object>> getExcludeIssuer() {
		return excludeIssuer;
	}
	public void setExcludeIssuer(List<Map<String, Object>> excludeIssuer) {
		this.excludeIssuer = excludeIssuer;
	}
	
	public List<Map<String, Object>> getIncludeIssuer() {
		return includeIssuer;
	}
	public void setIncludeIssuer(List<Map<String, Object>> includeIssuer) {
		this.includeIssuer = includeIssuer;
	}

	public List<Map<String, Object>> getIssuerList() {
		return issuerList;
	}
	public void setIssuerList(List<Map<String, Object>> issuerList) {
		this.issuerList = issuerList;
	}
	
	public List<Map<String, Object>> getUnderwriterList() {
		return underwriterList;
	}
	public void setUnderwriterList(List<Map<String, Object>> underwriterList) {
		this.underwriterList = underwriterList;
	}
	public List<Map<String, Object>> getUnderwriterInfo() {
		return underwriterInfo;
	}
	public void setUnderwriterInfo(List<Map<String, Object>> underwriterInfo) {
		this.underwriterInfo = underwriterInfo;
	}
	
	public UnderwriterMgtBean getModel() {
		return bean;
	}
	
	public List<Map<String, Object>> getProductList(){ 
		return cservice.getProductsDET(belongingBranch,bean.getAgencyCode());
	}
	
	public String getABList(){
    	logger.info("ENTER-->Method to getABList");
		underwriterList=service.getAdminUnderwriterList(bean, bean.getAgencyCode(), bean.getMode1(),branchCode);
		logger.info("getABList() - Exit");
		return "underwriterList";
    }
	
	public String view(){
    	logger.info("Method to view");
    	bean.setOptionMode("edit");
    	underwriterInfo=service.getUnderwriterDetails(bean, branchCode, bean.getIssurName());
		logger.info("view() - Exit");
    	return "underwriterInfo";
    }
	public String changePass(){
		return "changePass";
	}
	public String getNew(){
		return "edit";
	}
	public String edit(){
		
		return "edit";
	}
	public String insertIssuer(){
		String forward="edit";
		validateInsert();
		if(!hasActionErrors()){
		  service.insertUnderwriter(bean, sessionLoginId);
		  addActionError(getText("Inserted successfully"));
		  forward="underwriterList";
		  underwriterList=service.getAdminUnderwriterList(bean, bean.getAgencyCode(), bean.getMode1(),branchCode);
		}
		return forward;
	}
	public String updateissuer(){
		logger.info("Method to update");
		bean.setOptionMode("edit");
		validateInsert();
		if(!hasActionErrors()){
			service.updateUnderwriter(bean, sessionLoginId);
			addActionError(getText("Issuer details updated successfully"));
			underwriterList=service.getAdminUnderwriterList(bean, bean.getAgencyCode(), bean.getMode1(),branchCode);
		}
		logger.info("update() - Exit");
		return "underwriterInfo";
	}
	public String includeIssuer(){
		logger.info("Method to include");
		includeIssuer=service.includeissuerDetails(bean, bean.getType1());
		//underwriterInfo=service.getUnderwriterDetails(bean, branchCode, bean.getIssurName());
		logger.info("issuer() - Exit");
		return "includeIssuer";
	}
	public String excludeIssuer(){
		logger.info("Method to exclude");
		excludeIssuer=service.excludeissuerDetails(bean, bean.getType1());
		logger.info("exclude() - Exit");
		return "excludeIssuer";
	}
	public String branchSelection(){
		logger.info("Method to branchSelection()");
		bean.setMainBranchCode(branchCode);
		//service.getRSABranches(bean);
		return "branchSelection";
	}
	private void validateInsert(){
		if("edit".equals(bean.getOptionMode())){
	    	if(StringUtils.isBlank(bean.getUstatus())){
	    		addActionError(getText("error.quotation.status"));
	    	}
		}
	 	if(StringUtils.isBlank(bean.getIssurName()))
	 		addActionError(getText("issuer.name.empty"));
	 	if(StringUtils.isBlank(bean.getEmailId()))
	 		addActionError("Please enter Email Id");
	 	else if(StringUtils.contains(bean.getEmailId(), " "))
	 		addActionError("Email Id should not contain white spaces");
	 	else if("invalid".equalsIgnoreCase(new Validation().emailValidate(bean.getEmailId())))
    		addActionError("Please enter valid Email Id");
    	if(StringUtils.isBlank(bean.getLoginId()))
	 		addActionError(getText("issuer.loginId.empty"));
    	else if(StringUtils.contains(bean.getLoginId(), " "))
	 		addActionError("Login Id should not contain white spaces");
	 	else if(cservice.getAdminInfo(bean.getLoginId()).size()>0 && !"edit".equalsIgnoreCase(bean.getOptionMode()))
	 		addActionError(getText("error.loginid.exist"));
	 	if(StringUtils.isBlank(bean.getCoreLoginId()))
	 		addActionError(getText("issuer.coreloginId.empty"));
	 	if(!"edit".equalsIgnoreCase(bean.getOptionMode())){
		 	if(StringUtils.isBlank(bean.getPassword()))
		 		addActionError(getText("issuer.password.empty"));
		 	else if(StringUtils.contains(bean.getPassword(), " "))
		 		addActionError("Password should not contain white spaces");
	 	}
	 	/*if(StringUtils.isBlank(bean.getBranchNames()))
	 		addActionError(getText("issuer.branch.empty"));*/
	 	if(bean.getProducts()==null || bean.getProducts().size()<=0)
	 		addActionError(getText("issuer.product.empty"));
	 		
	}
	public String updatePass(){
    	logger.info("Method to checkPwd()");
    	validatePassword();
    	try{
	    	if(getActionErrors().isEmpty()){
	    		bean.setPassword(pass.crypt(bean.getPassword().substring(0, 3), bean.getPassword()));
				service.changePassword(bean);
				bean.setDisplay("passwordsuccess");
	    	}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return "changePass";
    }
	public void validatePassword()
    {
    	if(StringUtils.isEmpty(bean.getPassword())){
    		addActionError(getText("error.broker.newpassword"));
    	}else if(StringUtils.contains(bean.getPassword(), " "))
	 		addActionError("Password should not contain white spaces");
    	if(StringUtils.isEmpty(bean.getRpassword())){
    		addActionError(getText("error.broker.repassword"));
    	}if(!bean.getRpassword().equals(bean.getPassword())){
    		addActionError(getText("error.different"));
    	}
    }
	public String updateExclude(){
		service.updateExcludedBrokers(bean);
		includeIssuer=service.includeissuerDetails(bean, bean.getType1());
		bean.setProductId(null);
		bean.setStatus(null);
		addActionError(getText("Selected Brokers are Included successfully"));
		return "includeIssuer";
	}
	public String updateInclude(){
		service.updateIncludeBrokers(bean);
		
		excludeIssuer=service.excludeissuerDetails(bean, bean.getType1());
		bean.setProductId(null);
		bean.setStatus(null);
		addActionError(getText("Selected Brokers are Excluded successfully"));
		return "excludeIssuer";
	}
	public String getIssuerAjax(){
		underwriterList=service.getAdminUnderwriterList(bean, bean.getAgencyCode(), bean.getMode1(),branchCode);
		return "adminAjax";
	}
}