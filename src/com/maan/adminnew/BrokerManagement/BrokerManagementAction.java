package com.maan.adminnew.BrokerManagement;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.maan.adminnew.common.CommonService;
import com.maan.common.LogUtil;
import com.maan.common.Validation;
import com.maan.common.password.passwordEnc;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sun.tools.javac.resources.javac;

public class BrokerManagementAction extends ActionSupport implements ModelDriven<BrokerMgmBean>
{
	private static final Logger logger = LogUtil.getLogger(BrokerManagementAction.class);
	private static final long serialVersionUID = 1L;
	private BrokerMgmBean bean = new BrokerMgmBean();
	private CommonService cservice=new CommonService();
	Validation validation=new Validation();
	private List <Map<String, Object>> brokerList;
	private List <String> brokerInfo;
	private List <Map<String, Object>> productData;
	private List <Map<String, Object>> commisionDetails;
	private List <Map<String, Object>> branchData;
	private List <Map<String, Object>> branchsInfo;
	private List <Map<String, Object>> coreCustomerInfo;
	private List <String> customerTaxInfo;
	private List <Map<String, Object>> commission_Det;
	private List <Map<String,Object>> productList;
	private List <String> comp_Det;
	private List <Map<String, Object>> productInfo=new ArrayList<Map<String, Object>>();
	private List <Map<String, Object>> userInfo;
	private List <Map<String, Object>> userInfo1;
	final Validation validate=new Validation();
	
	BrokerManagementService service=new BrokerManagementService();
	final HttpServletRequest request=ServletActionContext.getRequest();
	Map<String, Object> session=ActionContext.getContext().getSession();
	String branchCode=(String)session.get("BranchCode");
	String appId=(String)session.get("AppId");
	String login_id=(String)session.get("user");
	passwordEnc pass = new passwordEnc();	
	
	public BrokerMgmBean getModel() {
		return bean;
	}
	public List<Map<String, Object>> getProductInfo() {
		return productInfo;
	}
	public void setProductInfo(List<Map<String, Object>> productInfo) {
		this.productInfo = productInfo;
	}
	public List <Map<String, Object>> getBrokerList() {
		return brokerList;
	}
	public List <String> getBrokerInfo() {
		return brokerInfo;
	}
	public List <Map<String, Object>> getProductData() {
		return productData;
	}
	public List <Map<String, Object>> getCommisionDetails() {
		return commisionDetails;
	}
	public List <Map<String, Object>> getCommission_Det() {
		return commission_Det;
	}
	public List <Map<String, Object>> getBranchData() {
		return branchData;
	}
	public List <Map<String, Object>> getBranchsInfo() {
		return branchsInfo;
	}
	public List <Map<String, Object>> getBrokerCode() {
		return service.getBrokerCode();
	}
	public List <Map<String, Object>> getCountriesInfo() {
		return cservice.getCountries(branchCode);
	}
	public List <Map<String, Object>> getEmiratesInfo() {
		return cservice.getEmirates(branchCode);
	}
	public List <Map<String, Object>> getNationalitiesInfo() {
		return cservice.getNationalities();
	}
	public List <Map<String, Object>> getTitlesInfo() {
		return cservice.getTitles(branchCode);
	}
	public List <Map<String,Object>> getProductDet() {
		return cservice.getProductsDET(branchCode,"");
	}
	public List <Map<String, Object>> getExecutivesInfo() {
		return service.getExecutives();
	}
	public List <Map<String, Object>> getCoreCustomerInfo() {
		return coreCustomerInfo;
	}
	public List <String> getCustomerTaxInfo() {
		return customerTaxInfo;
	}
	public List <String> getComp_Det() {
		return comp_Det;
	}
	public List <Map<String, Object>> getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(List <Map<String, Object>> userInfo) {
		this.userInfo = userInfo;
	}
	public List <Map<String, Object>> getUserInfo1() {
		return userInfo1;
	}
	public List <Map<String,Object>> getProductList() {
		return productList;
	}
	
	public String getABList(){
    	logger.info("ENTER-->Method to getABList");
    	brokerList=cservice.getAdminBrokerList(branchCode,appId);
		logger.info("getABList() - Exit"+login_id);
    	return "brokerList";
    }
    public String view(){
    	logger.info("Method to view");
    	brokerInfo=service.getBrokerDetails(bean, bean.getAgencyCode(),branchCode);
    	commisionDetails=service.getCommisionData(bean.getAgencyCode());
    	branchData=service.getBranchDetails(branchCode);
		logger.info("view() - Exit");
    	return "view";
    }
    public String edit(){
    	bean.setBrcode((String)session.get("BranchCode"));
    	logger.info("Method to edit");
    	logger.info("branchCode-->"+branchCode);
    	logger.info("agencyCode-->"+bean.getAgencyCode());
    	try{
    		if(!"new".equals(bean.getMode())){
    			if("delete".equals(bean.getMode1())){
		    		service.deleteBroLogo(bean.getAgencyCode());
		    		//String filePath = getText("GET_BROKER_LOGO_PATH")+bean.getAgencyCode()+"."+FilenameUtils.getExtension(bean.getBroImgName());
					File deleteFile = new File(request.getSession().getServletContext().getRealPath(bean.getBroImgName()));
		    		FileUtils.deleteQuietly(deleteFile);
		    	}
		    	String[] args={branchCode,bean.getAgencyCode(),branchCode,bean.getAgencyCode()};
		    	brokerInfo=service.getBrokerDetails(bean, bean.getAgencyCode(),branchCode);
		    	customerTaxInfo=service.getBrokerTaxInfo(bean, args);
		    	logger.info("getBrokerTaxInfo() - Exit   Result--->"+customerTaxInfo.size());
		    	commisionDetails=service.getCommisionData(bean.getAgencyCode());
    		}
			logger.info("edit() - Exit");
    	}catch(Exception e){
    		logger.info(e);
    		e.printStackTrace();
    	}
    	return "edit";
    }
    public String checkPwd(){
    	logger.info("Method to checkPwd()");
    	validatePassword();
    	try{
	    	if(getActionErrors().isEmpty()){
	    		bean.setPassword(pass.crypt(bean.getPassword().substring(0, 3), bean.getPassword()));
				String args[]={bean.getPassword(),bean.getAgencyCode()};
				cservice.checkPassword(args);
				bean.setDisplay("passwordsuccess");
	    	}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return "assignNew";
    }
    public String getccInfo(){
    	logger.info("Method to getccInfo");
    	if(bean.getMode()==null){
	    	logger.info("customerName-->"+bean.getCustomerName());
	    	logger.info("bcode-->"+branchCode);
	    	coreCustomerInfo=service.getcoreCustomererInfo(bean.getCustomerName(),branchCode, (String)session.get("userLoginMode"));
	    	bean.setMode("display");
    	}else bean.setMode(null);
    	return "coreCust";
    }
    public boolean validPassword(String newpassword)
	{
    	Pattern pattern=Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[@#$%]).{6,20})");//(?=.*[A-Z])
    	Matcher matcher = pattern.matcher(newpassword);
    	return matcher.matches();
	}
    /*public String statusChange(){
    	bean.setStatus(service.getBrokerStatus(bean.getAgencyCode()));
    	return "status";
    }
    public String statusUpdate(){
    	String[] obj={bean.getStatus(),bean.getAgencyCode()};
    	service.updateBrokerStatus(obj);
    	bean.setDisplay("success");
    	return "status";
    }*/
    
    public String newBroker()
    {
    	bean.setBrcode((String)session.get("BranchCode"));
    	logger.info("Enter==>newBroker()");
    	if("trueV".equalsIgnoreCase(bean.getValidNcheck()))
    	{
    		comp_Det=service.getCompDet(bean, bean.getAgencyCode());
    		commission_Det=service.getCommissionDET(bean.getAgencyCode(), branchCode);
    	}else{
	    	validatenewUser();
	    	if(getActionErrors().isEmpty()){
	    		String filePath ="";
	    		if("new".equalsIgnoreCase(bean.getMode())){
	    			bean.setCustomer_id(cservice.getCustomerId(branchCode));
	    			bean.setBroker_Code(service.getBroke_Code());
					Object[] arg={bean.getCustomer_id(), bean.getCustomer_id(), branchCode};
					
					service.getmax_Broke_Code(arg);
					if(bean.getUploadFileName()!=null){
						filePath = getText("GET_BROKER_LOGO_PATH")+bean.getBroker_Code()+"_"+bean.getUploadFileName();
						File fileToCreate = new File(request.getSession().getServletContext().getRealPath(filePath));
						try {
							FileUtils.copyFile(bean.getUpload(), fileToCreate);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
		    		Object[] args={bean.getCustomer_id(),"2", bean.getTitle(), bean.getFirstname(), bean.getLastname(), "1", bean.getNationality(), bean.getDob(), bean.getGender()==null?"":bean.getGender(), bean.getTelephone(), bean.getMobile(), bean.getFax(),
		    				bean.getBemail(), bean.getAddress1(), bean.getAddress2(), bean.getOccupation(), bean.getPobox(), bean.getCountry(), bean.getEmirate(), "Y", bean.getBroker_Code(), bean.getBroker_Code(), bean.getCustomerName(), bean.getARACC(), bean.getLoginid()};
		    		logger.info("args===>" + StringUtils.join(args, ", "));
					Object[] info={bean.getBroker_Code(), bean.getBorganization(), bean.getFirstname(), bean.getAddress1(), bean.getAddress2(), "", bean.getOthercity()==null?"": bean.getOthercity(), bean.getCountry(), bean.getTelephone(), bean.getPobox(), bean.getFax(), bean.getEmirate(),
								"", "Y", bean.getCustomer_id(), branchCode, bean.getCIMSNO(), bean.getApprovedby(), bean.getBcode(), bean.getExecutive(), bean.getOneOffCommission(), bean.getOpenCoverCommission(),filePath,bean.getVatRegNo(), login_id};

					logger.info("info===>" + StringUtils.join(info,", "));
					service.newBrokerCreation(args,info);
					
					String args1[]={bean.getLoginid(), pass.crypt(bean.getPassword().substring(0, 3), bean.getPassword()), "Broker", bean.getFirstname(),"1", String.valueOf(bean.getBroker_Code()),
        					String.valueOf(bean.getBroker_Code()), "2", "Admin", "Y", "Y", "Y", "BOTH", branchCode, bean.getCountry(),  bean.getLogin_Id(), bean.getBemail(), login_id};

        			logger.info("args1===>" + StringUtils.join(args1,", "));
	    			service.insertLogInDet(args1);
					
					int amendId=service.getMax_amend_Id(branchCode, bean.getBroker_Code())==0?1:service.getMax_amend_Id(branchCode,bean.getBroker_Code());
					Object[] val={bean.getBroker_Code(), branchCode, bean.getPolicy_fee()==null?"":bean.getPolicy_fee(), bean.getGov_fee()==null?"":bean.getGov_fee(), bean.getEmer_fee()==null?"":bean.getEmer_fee(), bean.getPolicFee()==null?"":bean.getPolicFee(), bean.getGovtTax()==null?"":bean.getGovtTax(), bean.getEmergencyfund()==null?"":bean.getEmergencyfund(),"", amendId, bean.getApp_for()==null?"":bean.getApp_for(), bean.getEffecdate()};

					logger.info("val===>" + StringUtils.join(val,", "));
					
					service.insTaxInfo(val);
					bean.setDisplay("successNew");
					bean.setAgencyCode(String.valueOf(bean.getBroker_Code()));
					bean.setIndex("0");
	    		}else{
	    			if(bean.getUploadFileName()!=null){
	    				//String dateTime= new Timestamp(new Date().getTime());
		    			filePath = getText("GET_BROKER_LOGO_PATH")+bean.getAgencyCode()+"_"+bean.getUploadFileName();
		    			File fileToCreate = new File(request.getSession().getServletContext().getRealPath(filePath));
						try {
							FileUtils.copyFile(bean.getUpload(), fileToCreate);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	    			}
	    			bean.setCustomer_id(service.getCustomer_Id(bean.getAgencyCode(), branchCode));
	    			Object[] args = {bean.getBorganization(), bean.getFirstname(), bean.getAddress1(), bean.getAddress2(), "", bean.getOthercity()==null?"":bean.getOthercity(), bean.getCountry(), bean.getTelephone(), bean.getPobox(), bean.getFax(), bean.getEmirate(),
	    					bean.getCustomer_id(), branchCode, bean.getCIMSNO(), bean.getApprovedby(), bean.getBcode(), bean.getExecutive(), bean.getOneOffCommission(), bean.getOpenCoverCommission(),filePath,bean.getVatRegNo(),login_id,bean.getAgencyCode(), branchCode};
	    			logger.info("args===>" + StringUtils.join(args,", "));
					service.update_Broker(args);
					
					Object[] info ={bean.getTitle(), bean.getFirstname(), bean.getLastname(), bean.getNationality(), bean.getDob(), bean.getGender()==null?"":bean.getGender(), bean.getTelephone()==null?"":bean.getTelephone(), bean.getMobile(), bean.getFax(), bean.getBemail(), bean.getAddress1(), bean.getAddress2(),
							bean.getOccupation(), bean.getPobox(), bean.getCountry(), bean.getEmirate(), "Y", bean.getEffecdate(), bean.getCustomerName(), bean.getARACC(), appId, bean.getAgencyCode()};
					logger.info("info===>" + StringUtils.join(info,", "));
					service.update_PersonalInfo(info);
					
					String[] obj={bean.getFirstname(), bean.getStatus(), bean.getBemail(),login_id, bean.getAgencyCode()};
			    	service.updateBrokerStatus(obj);
					
					Object[] val={bean.getPolicy_fee()==null?"":bean.getPolicy_fee(), bean.getGov_fee()==null?"":bean.getGov_fee(), bean.getEmer_fee()==null?"":bean.getEmer_fee(), bean.getPolicFee()==null?"":bean.getPolicFee(), bean.getGovtTax()==null?"":bean.getGovtTax(), bean.getEmergencyfund()==null?"":bean.getEmergencyfund(), bean.getApp_for()==null?"":bean.getApp_for(), bean.getEffecdate()};
					
					logger.info("val===>" + StringUtils.join(val,", "));
					
					service.updTaxInfo(val);
					commisionDetails=service.getCommisionData(bean.getAgencyCode());
					bean.setDisplay("successUpdate");
					bean.setIndex("0");
	    		}
	    	}
    	}
    	return "edit";
    }
    
	public String addProduct(){
		logger.info("ENTER===> addProduct");
		String returnResult="edit";
		try {
	    	logger.info("ENTER-->Method to validateProducts");
			validateProducts();
			if(!hasActionErrors()){
				if("newAjax".equals(bean.getMode1())){
					Object[] info ={cservice.getMaxUserId(), bean.getFirstname(), bean.getAgencyCode(), bean.getAgencyCode(), bean.getAgencyCode(), bean.getProductID(), "2", bean.getCommission(), "1000", bean.getInsurance_End_Limit(), bean.getDiscountPremium(),
									"0", "1", "", "", "", "Y", bean.getCustomer_id(), bean.getMin_Premium_Amount(), bean.getBack_Date_Allowed(), "", "", "", bean.getLoadingPremium(), bean.getUser_Id_Creation(), bean.getFreight(), bean.getPayReceipt(), bean.getProvision(), "", "","30".equals(bean.getProductID())?"7":""};
					Object obj1[]=new Object[]{bean.getRemark(), bean.getAgencyCode()};
					
					logger.info("info===>" + StringUtils.join(info, ", "));
					logger.info("obj1===>" + StringUtils.join(obj1, ", "));
					cservice.insertCommission(info, obj1);
					
					/*String[] obj ={bean.getUser_Id_Creation(), bean.getAgencyCode()};
					for(String k: obj){
						logger.info("args===>" + k);
					}
					service.updateUserIdCreation(obj);
					Object[] pID ={bean.getProduct_id(), bean.getAgencyCode()};
					for(Object k: pID){
						logger.info("args===>" + k);
					}
					service.updateProduct(pID);*/
					bean.setDisplay("newsuccess");
				}
				else{
					Object[] info = {bean.getCommission(), bean.getInsurance_End_Limit(), "Y", bean.getDiscountPremium(), bean.getMin_Premium_Amount(), bean.getBack_Date_Allowed(),
								bean.getLoadingPremium(),bean.getPayReceipt(), bean.getFreight(), bean.getUser_Id_Creation(),bean.getProvision(),"","", bean.getAgencyCode(), bean.getProductID()};
					Object obj1[]=new Object[]{bean.getRemark(), bean.getAgencyCode()};
					
					logger.info("info===>" + StringUtils.join(info, ", "));
					logger.info("obj1===>" + StringUtils.join(obj1, ", "));
					
					cservice.updateCommission(info, obj1);
					/*
					String[] obj ={bean.getUser_Id_Creation(), bean.getAgencyCode()};
					for(Object k: obj){
						logger.info("args===>" + k);
					}
					service.updateUserIdCreation(obj);*/
					bean.setDisplay("editsuccess");
				}
				bean.setMode1("");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		edit();
		bean.setIndex("1");
		return returnResult;
	}
	
	/*public String newlogin()
    {
    	logger.info("newlogin");
    	String returnResult="logcreate";
    	try{
	    	validatePassword();
	    	if(getActionErrors().isEmpty())
	    	{
		    	if(bean.getPassword().equals(bean.getRepassword()))
		        	{
		        		if(validPassword(bean.getPassword())){
		        			String args[]={bean.getLoginid(), pass.crypt(bean.getPassword().substring(0, 3), bean.getPassword()), "RSAIssuer", bean.getFirstname(),"8", bean.getAgencyCode(),
		        					bean.getAgencyCode(), "1", "Admin", "Y", "Y", "Y", "BOTH", branchCode, bean.getCountry(),  bean.getLogin_Id()};
		        			for(String k: args){
								logger.info("args===>" + k);
							}
			    			service.insertLogInDet(args);
			    			
			    			Object params[]={bean.getLoginid(), bean.getAgencyCode()};
			    			for(Object k: params){
								logger.info("args===>" + k);
							}
			    			service.updatePersonalLogin(params);
			    			bean.setDisplay("success");
		        		}
		        		else bean.setDisplay("invalid");
		        	}
		        	else bean.setDisplay("different");
		    	logger.info("display-->"+bean.getDisplay());
		    	returnResult="success";
	    	}
	    	else{
	    		returnResult="logcreate";
	    	}
    	}catch(Exception e){
			e.printStackTrace();
		}
    	return returnResult;
    }
	
	public String getBrokerUserInfo(){
		try{
			String args[] = {bean.getAgencyCode()};
			userInfo = service.getProductStatus(args);
			
			String[] args1 = {bean.getAgencyCode(), bean.getProductID()};
			for(Object k: args1){
				logger.info("args===>" + k);
			}
			userInfo1 = service.getProductStatuss(args1);
			bean.setBroker_Code(userInfo.size());
		}catch(Exception e){
			e.printStackTrace();
		}
		return "status";
	}*/
    public void validatenewUser()
    {
    	if("edit".equals(bean.getMode())){
	    	if(StringUtils.isEmpty(bean.getStatus())){
	    		addActionError(getText("error.quotation.status"));
	    	}
    	}
    	/*if(StringUtils.isEmpty(bean.getNameAndCode())){
    		addActionError(getText("error.quotation.nameAndCode"));
    	}*/
    	if(StringUtils.isEmpty(bean.getApprovedby())){
    		addActionError(getText("error.quotation.approvedby"));
    	}if(StringUtils.isEmpty(bean.getBcode())){
    		addActionError(getText("error.quotation.bcode"));
    	}else if(!StringUtils.isNumeric(bean.getBcode())){
    		addActionError(getText("error.quotation.bcode.invalid"));
    	}else if(!service.validateBcode(bean.getBcode()) && "new".equalsIgnoreCase(bean.getMode()) ) {
    		addActionError(getText("error.quotation.bcode.invalid"));
    	}else if("new".equalsIgnoreCase(bean.getMode()) && service.valBraWiseBcode(bean.getBcode(),branchCode)>0) {
    			addActionError(getText("error.branchwise.bcode.invalid"));
    	}
    	if(StringUtils.isEmpty(bean.getEmirate())){
    		addActionError(getText("error.quotation.emirate"));
    	}if("VARIOUS".equalsIgnoreCase(bean.getEmirate())){
    		if(StringUtils.isBlank(bean.getOthercity()))
    			addActionError(getText("error.broker.othercity.required"));
    		else if(!StringUtils.isAlpha(bean.getOthercity())){
        		addActionError(getText("error.broker.othercity"));
        	}
    	}
    	if(StringUtils.isBlank(bean.getOthercity()))
    		bean.setOthercity("");
    	if(StringUtils.isEmpty(bean.getCountry())){
    		addActionError("Please Select Country");
    	}/*if(StringUtils.isEmpty(bean.getPobox())){
    		addActionError(getText("error.quotation.pobox"));
    	}else */
    	if(StringUtils.isNotBlank(bean.getPobox()) && !StringUtils.isNumeric(bean.getPobox())){
    		addActionError(getText("error.quotation.pobox.valid"));
    	}if(!StringUtils.isNumeric(bean.getMobile())){
    		addActionError(getText("error.broker.mobile"));
    	}if(StringUtils.isEmpty(bean.getFirstname())){
    		addActionError(getText("error.quotation.firstname"));
    	}else if(StringUtils.isNumeric(bean.getFirstname())){
    		addActionError(getText("error.quotation.firstname.valid"));
    	}if(StringUtils.isEmpty(bean.getNationality())){
    		addActionError(getText("error.quotation.nationality"));
    	}if(!StringUtils.isNumeric(bean.getMobile())){
    		addActionError(getText("error.broker.mobile"));
    	}if(StringUtils.isEmpty(bean.getBemail())){
    		addActionError(getText("error.quotation.email"));
    	}else if(StringUtils.contains(bean.getBemail(), " "))
	 		addActionError("Email Id should not contain white spaces");
    	else if("invalid".equalsIgnoreCase(validation.emailValidate(bean.getBemail()))){
    		addActionError(getText("error.quotation.valid.bemail"));
    	}if(StringUtils.isEmpty(bean.getExecutive())){
    		addActionError(getText("error.quotation.executive"));
    	}if("new".equals(bean.getMode())){
    		if(StringUtils.isEmpty(bean.getLoginid())){
        		addActionError(getText("error.broker.loginid"));
        	}else if(StringUtils.contains(bean.getLoginid(), " "))
    	 		addActionError("Login Id should not contain white spaces");
        	else if(cservice.getAdminInfo(bean.getLoginid()).size()>0 && !"edit".equalsIgnoreCase(bean.getMode()))
    	 		addActionError(getText("error.loginid.exist"));
    		if(StringUtils.isEmpty(bean.getPassword())){
        		addActionError(getText("error.broker.newpassword"));
        	}else if(StringUtils.contains(bean.getPassword(), " "))
    	 		addActionError("Password should not contain white spaces");
    		if(StringUtils.isEmpty(bean.getRepassword())){
        		addActionError(getText("error.broker.repassword"));
        	}else if(!bean.getPassword().equals(bean.getRepassword())){
        		addActionError(getText("error.different"));
        	}else if(!validPassword(bean.getPassword())){
        		addActionError(getText("error.invalid"));
        	}else if(cservice.getAdminInfo(bean.getLoginid()).size()>0){
        		addActionError(getText("error.loginid.notavailable"));
        	}
    	}
    	if("Y".equalsIgnoreCase(bean.getPolicy_fee())){
    		if(StringUtils.isEmpty(bean.getPolicFee())){
        		addActionError(getText("error.tax.policFee"));
        	}else if(!StringUtils.isNumeric(bean.getPolicFee())){
        		addActionError(getText("error.tax.valid.policFee"));
        	}else if(Integer.parseInt(bean.getPolicFee())>9999.999){
        		addActionError(getText("error.tax.valid.policFee"));
        	}
		}if("Y".equalsIgnoreCase(bean.getGov_fee())){
    		if(StringUtils.isEmpty(bean.getGovtTax())){
        		addActionError(getText("error.tax.govtTax"));
        	}else if(!StringUtils.isNumeric(bean.getGovtTax())){	
        		addActionError(getText("error.tax.valid.govtTax"));		
        	}else if(Integer.parseInt(bean.getGovtTax())>99.999){	
        		addActionError(getText("error.tax.valid.govtTax"));		
        	}
		}if("Y".equalsIgnoreCase(bean.getEmer_fee())){
    		if(StringUtils.isEmpty(bean.getEmergencyfund())){
        		addActionError(getText("error.tax.emergencyfund"));
        	}else if(!StringUtils.isNumeric(bean.getEmergencyfund())){
        		addActionError(getText("error.tax.valid.emergencyfund"));
        	}else if(Integer.parseInt(bean.getEmergencyfund())>99.999){
        		addActionError(getText("error.tax.valid.emergencyfund"));
        	}
		}
		/*if(StringUtils.isEmpty(bean.getOneOffCommission()))
    		addActionError("Please enter Commission for Issuer Quotes : For One Off Policy");
    	else */
    	if(StringUtils.isNotBlank(bean.getOneOffCommission()) && !Validation.decimalValidate(bean.getOneOffCommission()))
    		addActionError("Please enter valid Commission for Issuer Quotes : For One Off Policy");
		/*if(StringUtils.isEmpty(bean.getOpenCoverCommission()))
			addActionError("Please enter Commission for Issuer Quotes : For Open Cover Policy");
		else*/ 
		if(StringUtils.isNotBlank(bean.getOpenCoverCommission()) && !Validation.decimalValidate(bean.getOpenCoverCommission()))
    		addActionError("Please enter valid Commission for Issuer Quotes : For Open Cover Policy");
		if(StringUtils.isEmpty(bean.getEffecdate())){
    		addActionError(getText("error.tax.effecdate"));
    	}
		if(StringUtils.isBlank(bean.getBorganization()))
			addActionError("Please Enter the Broker Organization");
		
		String extensions[] = {"png","jpg","jpeg","gif"};

		if(bean.getUpload()!=null && (bean.getUpload().length())>0)
		{
			if(!FilenameUtils.isExtension(bean.getUploadFileName().toLowerCase(),extensions))
			{
				addActionError(getText("error.broker.image"));
			}
			//String filePath = request.getContextPath()+"/BrokerImages/"+bean.getUploadFileName()+"."+FilenameUtils.getExtension(bean.getUploadFileName());
			//System.out.println("filePath--->"+filePath);
			/*if(bean.getBroImgUpload().length()>10485760)
			{
				addActionError(getText("error.image.size"));
			}*/

		}
    }
    public void validatePassword()
    {
    	if(StringUtils.isEmpty(bean.getPassword())){
    		addActionError(getText("error.broker.newpassword"));
    	}if(StringUtils.isEmpty(bean.getRpassword())){
    		addActionError(getText("error.broker.repassword"));
    	}if(!bean.getRpassword().equals(bean.getPassword())){
    		addActionError(getText("error.different"));
    	}
    }
    public boolean validString(String value,int format)
    { 
    	boolean bo=false;
    	int count=0,c=0;
    	
    	try
    	{
    		value=value.trim();
    		String validChar,validno,validextra=null,validCode,validnumonly;
    		String string=new String("");
    					
    			validChar="abcdefghijklmnopqrstuvwxyz";
    			validno="1234567890-";
    			validextra="1234567890.";
    			validCode="abcdefghijklmnopqrstuvwxyz1234567890 ";
    			validnumonly="1234567890";
    			value=value.toLowerCase();
    			if(format==1)
    			    string=new String(validChar);
    			else if(format==2)
    				string=new String(validno);
    			else if(format==3)
    				string=validChar+validno;
    			else if(format==4)
    				string=new String(validextra);
    			else if(format==5)
    				string=new String(validCode);
    			else if(format==6)
    				string=new String(validnumonly);
    			for(int i=0;i<value.length();i++) {
    				if(string.indexOf(value.charAt(i))== -1)
    				bo=true;
    				if(value.charAt(i)=='.')
    					count++;	
    				if(value.charAt(i)=='-')
    				c++;	
    			}
    			if(count>=2 || c>=2)
    				bo=true;
    			
    	}catch(Exception e){
    		return bo;
    	}
    	return bo;
    }   
    
    public void validateProducts(){
    	if("newAjax".equals(bean.getMode1()) && StringUtils.isEmpty(bean.getProductID())){
    		addActionError(getText("error.product.productID"));
    	}
	    else{
	    		if("3".equals(bean.getProductID())){
		    	if(StringUtils.isBlank(bean.getCommission())){
		    		addActionError(getText("error.product.COMMISSION"));
		    	}else {	
		    		try {
		    			Double.parseDouble(bean.getCommission());
		    		}
		    		catch(Exception exception) {
		    			addActionError(getText("error.product.valid.COMMISSION"));
		    		}		
		    	}if(StringUtils.isBlank(bean.getInsurance_End_Limit())){
		    		addActionError(getText("error.product.INSURANCE_END_LIMIT"));
		    	}else if(!StringUtils.isNumeric(bean.getInsurance_End_Limit())){	
		    		addActionError(getText("error.product.valid.INSURANCE_END_LIMIT"));		
		    	}if(StringUtils.isBlank(bean.getMin_Premium_Amount())){
		    		addActionError(getText("error.product.MIN_PREMIUM_AMOUNT"));
		    	}else if(!StringUtils.isNumeric(bean.getMin_Premium_Amount())){	
		    		addActionError(getText("error.product.valid.MIN_PREMIUM_AMOUNT"));		
		    	}
		    	if("Y".equalsIgnoreCase(bean.getProvision())){
		    		if(StringUtils.isBlank(bean.getLoadingPremium())){
		    			addActionError(getText("error.product.loadingPremium"));
		    		}else if(!StringUtils.isNumeric(bean.getLoadingPremium())){	
		    			addActionError(getText("error.product.valid.loadingPremium"));		
		    		}if(StringUtils.isBlank(bean.getDiscountPremium())){
		    			addActionError(getText("error.product.discountPremium"));
		    		}else if(!StringUtils.isNumeric(bean.getDiscountPremium())){	
		    			addActionError(getText("error.product.valid.discountPremium"));		
		    		}
		    	}else{
					bean.setLoadingPremium(null);
					bean.setDiscountPremium(null);
				}
		    	if(StringUtils.isBlank(bean.getBack_Date_Allowed())){
		    		addActionError(getText("error.product.BACK_DATE_ALLOWED"));
		    	}else if(!StringUtils.isNumeric(bean.getBack_Date_Allowed())){
		    		addActionError(getText("error.product.valid.BACK_DATE_ALLOWED"));
		    	}
		    }if(StringUtils.isBlank(bean.getUser_Id_Creation())){
	    		addActionError(getText("error.product.USER_ID_CREATION"));
	    	}if(StringUtils.isBlank(bean.getPayReceipt())){
	    		addActionError(getText("error.product.payReceipt"));
	    	}if(StringUtils.isBlank(bean.getFreight())){
	    		addActionError(getText("error.product.freight"));
	    	}
	    	if(StringUtils.isBlank(bean.getRemark())){
	    		addActionError("Please Select Remarks Required");
	    	}
	    }
    }
    public String changePwd(){
    	brokerInfo=service.getBrokerDetails(bean, bean.getAgencyCode(),branchCode);
    	bean.setDisplay("");
    	return "assignNew";
    }
    public String getBrokerAjax(){
    	if("brokerLists".equals(bean.getReqFrom())){
    		brokerList=service.getBrokerListAjax(branchCode, bean.getSearchBy(), bean.getSearchValue(), appId);
    	}else if("productselections".equals(bean.getReqFrom())){
    		if("editAjax".equals(bean.getMode1()) && !"ajax".equals(bean.getMode())){
    			productData=service.getProducts(bean);
    		}
    		productList=cservice.getProductsDET(branchCode,bean.getAgencyCode());
    	}
    	return "adminAjax";
    }
    public String deleteProduct(){
    	service.deleteProduct(bean);
    	edit();
    	bean.setMode1("");
    	bean.setProductID("");
    	bean.setIndex("1");
    	return "edit";
    }
	
}