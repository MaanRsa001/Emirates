package com.maan.adminnew.userManagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;

import com.maan.adminnew.common.CommonService;
import com.maan.common.LogUtil;
import com.maan.common.Validation;
import com.maan.common.password.passwordEnc;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserMgtAction extends ActionSupport implements ModelDriven<UserMgtBean>
{
	private static final Logger logger = LogUtil.getLogger(UserMgtAction.class);
	private static final long serialVersionUID = 1L;
	private UserMgtBean bean = new UserMgtBean();
	private CommonService cservice=new CommonService();
	Validation validation=new Validation();
	UserMgtService service=new UserMgtService();
	Map<String, Object> session=ActionContext.getContext().getSession();
	String branchCode=(String)session.get("BranchCode");
	String login_id=(String)session.get("user");
	private List <Map<String, Object>> userList;
	private List <Map<String, Object>> userInfo;
	private List <Map<String, Object>> commisionDetails=new ArrayList<Map<String, Object>>();
	private List <Map<String,Object>> productList;
	private List <Map<String, Object>> productData;
	private List <Map<String, Object>> occList;
	String appId=(String)session.get("AppId");
	passwordEnc pass = new passwordEnc();
	
	public List<Map<String, Object>> getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(List<Map<String, Object>> userInfo) {
		this.userInfo = userInfo;
	}
	public List<Map<String, Object>> getCommisionDetails() {
		return commisionDetails;
	}
	public void setCommisionDetails(List<Map<String, Object>> commisionDetails) {
		this.commisionDetails = commisionDetails;
	}
	public List<Map<String, Object>> getUserList() {
		return userList;
	}
	public void setUserList(List<Map<String, Object>> userList) {
		this.userList = userList;
	}
	public List<Map<String, Object>> getProductData() {
		return productData;
	}
	public void setProductData(List<Map<String, Object>> productData) {
		this.productData = productData;
	}
	public List<Map<String, Object>> getOccList() {
		return occList;
	}
	public void setOccList(List<Map<String, Object>> occList) {
		this.occList = occList;
	}
	public List<Map<String,Object>> getProductList() {
		return productList;
	}
	public void setProductList(List<Map<String,Object>> productList) {
		this.productList = productList;
	}
	public UserMgtBean getModel() {
		return bean;
	}
	public List <Map<String, Object>> getCountriesInfo() {
		return cservice.getCountries(branchCode);
	}
	public List <Map<String, Object>> getEmiratesInfo() {
		return cservice.getEmirates(branchCode);
	}
	public List <Map<String, Object>> getBrokerList() {
		return cservice.getAdminBrokerList(branchCode, appId);
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
	
	public String getOCCertificate() {
		occList=service.getOCCertificate(bean.getAgencyCode());
		return "openCover";
	}
	public String getABList(){
    	logger.info("ENTER-->Method to getABList");
		logger.info("getABList() - Exit");
		userList=service.getAdminUserList(bean, bean.getAgencyCode(), bean.getMode1(), branchCode);
		return "userList";
    }
	
	public String view(){
    	logger.info("Method to view");
    	userInfo=service.getUserDetails(bean, bean.getUagencyCode());
    	//commisionDetails=service.getCommisionData(bean.getUagencyCode(), bean.getAgencyCode(), branchCode);
		logger.info("view() - Exit");
    	return "view";
    }
	
	public String edit(){
		logger.info("Method to edit");
		try{
			if(!"new".equals(bean.getMode())){
		    	userInfo=service.getUserDetails(bean, bean.getUagencyCode());
		    	commisionDetails=service.getCommisionData(bean.getUagencyCode(), bean.getAgencyCode(), branchCode);
		    	//commisionDetailsUser=service.getCommisionData(bean.getAgencyCode());
			}
			logger.info("edit() - Exit");
		}catch(Exception e){
			logger.info(e);
			e.printStackTrace();
		}
		return "edit";
	}
	
	public String newUser()
    {
    	logger.info("Enter==>newUser()");
    	validatenewUser();
    	if(getActionErrors().isEmpty()){
    		try{
	    		if("new".equalsIgnoreCase(bean.getMode())){
	    			String customerId=String.valueOf(cservice.getCustomerId(branchCode));
	    			bean.setUagencyCode(cservice.getUserCode(branchCode));
	    			if(bean.getAgencyCode()==null || "".equals(bean.getAgencyCode()))
	    				bean.setAgencyCode(bean.getBroker());
	    			Object[] args={customerId, appId, bean.getUtitle(), bean.getUfirstname(), bean.getUlastname(), "1", bean.getUnationality(), bean.getUdob(), bean.getUgender()==null?"":bean.getUgender(), bean.getUphone(), bean.getUmobile(),
	    							bean.getUfax(), bean.getUemail(), bean.getUaddress1(), bean.getUaddress2(), bean.getUoccupation(), bean.getUpobox(), bean.getUcountry(), bean.getUcity(), "Y", bean.getUserId(), bean.getUagencyCode(), bean.getAgencyCode(), bean.getUcity(),""};
					String args1[]={bean.getUserId(), pass.crypt(bean.getPassword().substring(0, 3), bean.getPassword()), bean.getUtype(), bean.getUfirstname(),"1", bean.getUagencyCode(), bean.getAgencyCode(), "BOTH",
									"","","","","1", bean.getAgencyCode(), "Y", "N", "N", "Y", bean.getUemail(), branchCode,bean.getUcountry(),login_id};

	    			service.insertUserInfo(args,args1);
	    			/*String info[]={cservice.getUserId(), bean.getUfirstname(),bean.getUserId(),bean.getUagencyCode(),bean.getAgencyCode(),    "4", bean.getUagencyCode(), bean.getAgencyCode(), "BOTH",
							"","","","","1", bean.getAgencyCode(), "Y", "N", "N", "Y"};
					for(String k: args1){
						logger.info("info===>" + k);
					}
	    			insert into login_user_details(USER_ID,USER_NAME,LOGIN_ID,AGENCY_CODE,OA_CODE,PRODUCT_ID,COMPANY_ID,COMMISSION,INSURANCE_START_LIMIT,INSURANCE_END_LIMIT,SPECIAL_DISCOUNT,RELATIVE_USER_ID,AMEND_ID,INCEPTION_DATE,EXPIRY_DATE,EFFECTIVE_DATE,ENTRY_DATE,REMARKS,STATUS,CUSTOMER_ID,FREIGHT_DEBIT_OPTION,open_cover_no,scheme_id) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)
					*/
					bean.setDisplay("successNew");
					bean.setIndex("0");
	    		}else{
	    			//bean.setUagencyCode(cservice.getUserCode(branchCode));
	    			Object[] args={bean.getUtitle(), bean.getUfirstname(), bean.getUlastname(), bean.getUnationality(), bean.getUdob(), bean.getUgender()==null?"":bean.getUgender(), bean.getUphone(), bean.getUmobile(),
	    							bean.getUfax(), bean.getUemail(), bean.getUaddress1(), bean.getUaddress2(), bean.getUoccupation(), bean.getUpobox(), bean.getUcountry(), bean.getUcity(), bean.getUstatus(), bean.getUcity(), bean.getUagencyCode()};
		    		
//update personal_info set title=?, first_name=?, last_name=?, nationality=?, dob=?, gender=?, telephone=?, mobile=?, fax=?, email=?, address1=?, address2=?, occupation=?, pobox=?, country=?, emirate=?, status=?, city=? where agency_code=?
					String args1[]={bean.getUfirstname(),bean.getUstatus(), bean.getUemail(),login_id,bean.getUagencyCode()};
	    			service.updateUserInfo(args,args1);
	    			bean.setDisplay("successUpdate");
					bean.setIndex("0");
	    		}
    		}catch(Exception e){
	    			e.printStackTrace();
	    	}
    	}
    	commisionDetails=service.getCommisionData(bean.getUagencyCode(), bean.getAgencyCode(), branchCode);
    	return "edit";
    }
	
	public String getUserAjax(){
    	if("userLists".equals(bean.getReqFrom())){
    		userList=service.getUserListAjax(bean.getUagencyCode(), bean.getSearchBy(), bean.getSearchValue(), bean.getMode1());
    	}else if("userproduct".equals(bean.getReqFrom())){
    		commisionDetails=service.getCommisionData(bean.getUagencyCode(), bean.getAgencyCode(), branchCode);
    		productList=cservice.getProductsDET(branchCode,bean.getAgencyCode());
    	}
    	return "adminAjax";
    }
	
	 public String checkPwd(){
    	logger.info("Method to checkPwd()");
    	validatePassword();
    	try{
	    	if(getActionErrors().isEmpty()){
	    		bean.setPassword(pass.crypt(bean.getPassword().substring(0, 3), bean.getPassword()));
				String args[]={bean.getPassword(),bean.getUagencyCode()};
				cservice.checkPassword(args);
				bean.setDisplay("passwordsuccess");
				addActionMessage("Password Updated Successfully");
	    	}
	    	else bean.setMode1("login");
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	userInfo=service.getUserDetails(bean, bean.getUagencyCode());
    	commisionDetails=service.getCommisionData(bean.getUagencyCode(), bean.getAgencyCode(), branchCode);
    	bean.setIndex("2");
    	return "edit";
    }
	 
	 public String addProduct(){
		logger.info("ENTER===> addProduct");
		String returnResult="edit";
		try {
	    	validateProducts();
	    	userInfo=service.getUserDetails(bean, bean.getUagencyCode());
			if(!hasActionErrors()){
				for(int i=0; i<commisionDetails.size();i++){

					Map map=(Map)commisionDetails.get(i);
					 if("Y".equalsIgnoreCase(map.get("product").toString())){
						 int count=cservice.checkExistProduct(map.get("uproductId").toString(), bean.getUagencyCode());
						 String ocover="";
						 if("11".equalsIgnoreCase(map.get("uproductId").toString()))
							 ocover=bean.getOpenCover();
						 if(count==0){
							Object[] info ={cservice.getMaxUserId(), bean.getUfirstname(),  bean.getUagencyCode(), bean.getUagencyCode(), bean.getAgencyCode(), map.get("uproductId"), "1", "", "", map.get("insEndLimit"), "",
											"0", "1", "", "", "", "Y", bean.getUserId(),"","","","","","","",map.get("freight"), map.get("receipt"),"", ocover, map.get("specialDis"),"30".equals(bean.getProductID())?"7":""};
							
							for(Object k: info){
								logger.info("info===>" + k);
							}
							Object obj[]=null;
							cservice.insertCommission(info, obj);
							bean.setDisplay("newsuccess");
						 }else{
							Object[] args = {"",  map.get("insEndLimit"), "Y", "", "", "", "", map.get("receipt"), map.get("freight"), "", map.get("receipt"), ocover, map.get("specialDis"), bean.getUagencyCode(),map.get("uproductId")};
							for(Object k: args){
								logger.info("args===>" + k);
							}
							Object obj[]=null;
							cservice.updateCommission(args, obj);
							bean.setDisplay("updatesuccess");
						 }
					 }else {
						 Object[] args = {"",  map.get("insEndLimit"), "N", "", "", "", "", map.get("receipt"), map.get("freight"), "", map.get("receipt"), "", map.get("specialDis"), bean.getUagencyCode(),map.get("uproductId")};
							for(Object k: args){
								logger.info("args===>" + k);
							}
							Object obj[]=null;
							cservice.updateCommission(args, obj);
							bean.setDisplay("updatesuccess");
					 }
				}
			}else bean.setMode1("product");
		}catch (Exception e) {
			e.printStackTrace();
		}
		bean.setIndex("1");
		return returnResult;
	}
	 
	public void validatePassword(){
    	if(StringUtils.isEmpty(bean.getPassword()))
    		addActionError(getText("error.broker.newpassword"));
    	else if(StringUtils.contains(bean.getPassword(), " "))
	 		addActionError("Password should not contain white spaces");
    	if(StringUtils.isEmpty(bean.getRepassword()))
    		addActionError(getText("error.broker.repassword"));
    	else if(!bean.getPassword().equals(bean.getRepassword()))
    		addActionError(getText("error.different"));
    }
	 
	 public void validateProducts(){
		 int count=0;
		 List<Map<String, Object>> commisionDetail=new ArrayList<Map<String, Object>>();
		 for(int i=0;i<bean.getProduct().size(); i++ ){
			 if("true".equals(bean.getProduct().get(i))){
				if(!"11".equals(bean.getUproductId().get(i))){
					if(StringUtils.isEmpty(bean.getSpecialDis().get(i))){
			    		addActionError(getText("error.product.specialdis"));
			    	}else if(!StringUtils.isNumeric(bean.getSpecialDis().get(i))){
			    		addActionError(getText("error.product.specialdis.valid"));
			    	}if(StringUtils.isEmpty(bean.getInsEndLimit().get(i))){
			    		addActionError(getText("error.product.insEndLimit"));
			    	}else if(!StringUtils.isNumeric(bean.getInsEndLimit().get(i))){
			    		addActionError(getText("error.product.insEndLimit.valid"));
			    	}
				}
		    	count++;
			 }
			 Map<String, Object> map=new HashMap<String, Object>();
			// map.put("product",bean.getProduct().get(i)=="true"?"Y":"N");
			 if("true".equalsIgnoreCase(bean.getProduct().get(i)))
		 		map.put("product","Y");
			 else map.put("product", "N");
			 
			 map.put("uproductId",bean.getUproductId().get(i)==null?"":bean.getUproductId().get(i));
			 map.put("uproductName",bean.getUproductName().get(i)==null?"":bean.getUproductName().get(i));
			 map.put("specialDis",bean.getSpecialDis().get(i)==null?"":bean.getSpecialDis().get(i));
			 map.put("insEndLimit",bean.getInsEndLimit().get(i)==null?"":bean.getInsEndLimit().get(i));
			 map.put("receipt",bean.getReceipt()==null?"N":bean.getReceipt().get(i));
			 map.put("freight",bean.getFreight()==null?"N":bean.getFreight().get(i));
			 if("11".equals(bean.getUproductId().get(i)))
				 map.put("open_cover_no",bean.getOpenCover());
			 else
				 map.put("open_cover_no","");
			 commisionDetail.add(map);
		 }if(count==0){
			 addActionError(getText("error.product.select"));
		 }
		 this.commisionDetails=commisionDetail;
	}
	
	public void validatenewUser(){
    	if("edit".equals(bean.getMode())){
	    	if(StringUtils.isEmpty(bean.getUstatus())){
	    		addActionError(getText("error.quotation.status"));
	    	}
    	}if("new".equals(bean.getUstatus())){
    		if(StringUtils.isEmpty(bean.getUtype())){
    			addActionError(getText("error.user.type"));
    		}
    	}
    	if(bean.getBorganization() == null || "".equals(bean.getBorganization())) {
	    	if(StringUtils.isEmpty(bean.getBroker())) {
				addActionError("Please Select a Broker");
			}
    	}
    	if(StringUtils.isEmpty(bean.getUfirstname())){
    		addActionError(getText("error.quotation.firstname"));
    	}else if(StringUtils.isNumeric(bean.getUfirstname())){
    		addActionError(getText("error.quotation.firstname.valid"));
    	}if(StringUtils.isEmpty(bean.getUnationality())){
    		addActionError(getText("error.quotation.nationality"));
    	}if(StringUtils.isEmpty(bean.getUcity())){
    		addActionError(getText("error.quotation.emirate"));
    	}if(StringUtils.isEmpty(bean.getUcountry())){
    		addActionError("Please select Country");
    	}if(StringUtils.isNotBlank(bean.getUpobox()) &&!StringUtils.isNumeric(bean.getUpobox())){
    		addActionError(getText("error.quotation.pobox.valid"));
    	}if(!StringUtils.isBlank(bean.getUphone()) && !StringUtils.isNumeric(bean.getUphone()))
    		addActionError(getText("error.broker.phone"));
    	if(!StringUtils.isBlank(bean.getUmobile()) && !StringUtils.isNumeric(bean.getUmobile()))
    		addActionError(getText("error.broker.mobile"));
    	if(StringUtils.isEmpty(bean.getUemail()))
    		addActionError(getText("error.quotation.email"));
    	else if(StringUtils.contains(bean.getUemail(), " "))
	 		addActionError("Email Id should not contain white spaces");
    	else if("invalid".equalsIgnoreCase(validation.emailValidate(bean.getUemail()))){
    		addActionError(getText("error.quotation.valid.bemail"));
    	}if("new".equals(bean.getMode())){
    		if(StringUtils.isEmpty(bean.getUserId()))
        		addActionError(getText("error.broker.loginid"));
    		else if(StringUtils.contains(bean.getUserId(), " "))
    	 		addActionError("Login Id should not contain white spaces");
        	if(StringUtils.isEmpty(bean.getPassword()))
        		addActionError(getText("error.broker.newpassword"));
        	else if(StringUtils.contains(bean.getPassword(), " "))
    	 		addActionError("Password should not contain white spaces");
        	if(StringUtils.isEmpty(bean.getRepassword())){
        		addActionError(getText("error.broker.repassword"));
        	}else if(!bean.getPassword().equals(bean.getRepassword())){
        		addActionError(getText("error.different"));
        	}else if(!validPassword(bean.getPassword())){
        		addActionError(getText("error.invalid"));
        	}else if(cservice.getAdminInfo(bean.getUserId()).size()>0){
        		addActionError(getText("error.loginid.notavailable"));
        	}
    	}
    }
	public boolean validPassword(String newpassword)
	{
    	Pattern pattern=Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[@#$%]).{6,20})");//(?=.*[A-Z])
    	Matcher matcher = pattern.matcher(newpassword);
    	return matcher.matches();
	}
}