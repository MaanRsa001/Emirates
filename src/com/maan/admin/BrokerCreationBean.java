package com.maan.admin;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.logging.log4j.Logger;

import proj.date.DateFunction;

import com.maan.common.LogUtil;
import com.maan.common.error.ErrorInfo;
import com.maan.common.password.passwordEnc;
import com.maan.common.util.StringUtil;
import com.maan.services.util.ValidationFormat;
import com.maan.services.util.runner;

public class BrokerCreationBean extends ErrorInfo 
{

	private static final Logger logger = LogUtil.getLogger(BrokerCreationBean.class);
	private PrintWriter out = null;
	private String title = "";
	private String firstName = "";
	private String gender = "";
	private String lastName = "";
	private String nationality = "";
	private String brokerDate = "";
	private String telephone = "";
	private String mobile = "";
	private String fax = "";
	private String email = "";
	private String address1 = "";
	private String address2 = "";
	private String occupation = "";
	private String emirate = "";
	private String country = "";
	private String poBox = "";
	private String brokerCompanyName = "";
	private String brokerId = "";
	private String password = "";
	private String retypePassword = "";
	private String brokerIds = "";
	private String dobDay = "";
	private String dobMonth = "";
	private String dobYear = "";
	private String mode = "";
	private String city = "";
	private String branch = "";
	private String AgencyCode = "";
	private String rsa_broker_code = "";// Mathan
	private String OACode = "";
	private String loginPersonId = "";
	private String bcode = "";
	private String mcode = "";
	private String approvedby = "";
	private String borganization = "";
	private String address3 = "";
	private String sno = "";
	private String eno = "";
	/** ****************its for creating new admin********************** */
	private String user_Type = "";
	private String login_Id = "";
	private String user_Name = "";
	private String email_id = "";
	private String user_Pass = "";
	private String admTyp = "";
	private String User_Branch = "";
	private String User_prodType = "";
	private String loginCountry = "";
	//private String User_Branch[] = new String[0];
	/*private String menuLink[] = new String[0];
	private String product[] = new String[0];*/
	private String menuIds = "";
	private String proIds = "";
	private String brokers[] = new String[0];
	private String currencyType = "";
	private String currencyNo = "";
	private String exchangeRate = "";
	private String effectDate = "";
	private String status = "";
	private String remarkText = "";
	private String disStatus = "";
	private String error = "";
	private String bankId;
	private String bankName;
	private String rsaCode;
	private String newAdminType;
	private String adminRemarksTxt;
	private String adminStatus;
    private String belongingCountry;
	private String adminCountryId;
	/* New Menu Id Creation */
	private String menuName ="";
	private String menuRemarksTxt="";
	private String menuStatus="";
	private HashMap officeHash;
	/* New Menu Id Creation */
	private String flowBranch="";
	private Map usrAgencyPdtMap = new HashMap();
	private String productId[];
	private String startDay = "";
	private String startMonth = "";
	private String startYear = "";
	private String endDay = "";
	private String endMonth = "";
	private String endYear = "";
	private String deactiveDay = "";
	
	public String getRfactor() {
		return rfactor;
	}

	public void setRfactor(String rfactor) {
		this.rfactor = rfactor;
	}

	public String getSubcurrency() {
		return subcurrency;
	}

	public void setSubcurrency(String subcurrency) {
		this.subcurrency = subcurrency;
	}

	private String deactiveMonth = "";
	private String deactiveYear = "";
	private String executiveId = "";
	private String coreLoginId = "";
	private String brokerName = "";
	private String arNo = "";
	public String getShortname() {
		return shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	private String rfactor = "";
	private String subcurrency = "";
	private String shortname = "";

	

	public String getBrokerName() {
		return brokerName;
	}

	public void setBrokerName(String brokerName) {
		this.brokerName = brokerName;
	}

	public String getArNo() {
		return arNo;
	}

	public void setArNo(String arNo) {
		this.arNo = arNo;
	}

	public String getCoreLoginId() {
		return coreLoginId;
	}

	public void setCoreLoginId(String coreLoginId) {
		this.coreLoginId = coreLoginId;
	}

	public String getExecutiveId() {
		return executiveId;
	}

	public void setExecutiveId(String executiveId) {
		this.executiveId = executiveId;
	}

	public String getDeactiveDay() {
		return deactiveDay;
	}

	public void setDeactiveDay(String deactiveDay) {
		this.deactiveDay = deactiveDay;
	}

	public String getDeactiveMonth() {
		return deactiveMonth;
	}

	public void setDeactiveMonth(String deactiveMonth) {
		this.deactiveMonth = deactiveMonth;
	}

	public String getDeactiveYear() {
		return deactiveYear;
	}

	public void setDeactiveYear(String deactiveYear) {
		this.deactiveYear = deactiveYear;
	}

	public String getEndDay() {
		return endDay;
	}

	public void setEndDay(String endDay) {
		this.endDay = endDay;
	}

	public String getEndMonth() {
		return endMonth;
	}

	public void setEndMonth(String endMonth) {
		this.endMonth = endMonth;
	}

	public String getEndYear() {
		return endYear;
	}

	public void setEndYear(String endYear) {
		this.endYear = endYear;
	}

	public String getStartDay() {
		return startDay;
	}

	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}

	public String getStartMonth() {
		return startMonth;
	}

	public void setStartMonth(String startMonth) {
		this.startMonth = startMonth;
	}

	public String getStartYear() {
		return startYear;
	}

	public void setStartYear(String startYear) {
		this.startYear = startYear;
	}

	public String[] getProductId() {
		return productId;
	}

	public void setProductId(String[] productId) {
		this.productId = productId;
	}

	public String getFlowBranch() {
		return flowBranch;
	}

	public void setFlowBranch(String flowBranch) {
		this.flowBranch = flowBranch;
	}
	public Map getUsrAgencyPdtMap() {
		return usrAgencyPdtMap;
	}

	public void setUsrAgencyPdtMap(Map usrAgencyPdtMap) {
		this.usrAgencyPdtMap = usrAgencyPdtMap;
	}
	public void setOfficeHash(HashMap officeHash){
		this.officeHash = officeHash;
	}
	
	public HashMap getOfficeHash(){
		return officeHash;
	}

    public void setBelongingCountry(String belongingCountry){
        this.belongingCountry = belongingCountry;
    }
    public String getBelongingMaster(){
        return belongingCountry;
    }

	public void setSno(String sno) {
		this.sno = sno;
	}
	public String getSno() {
		return sno;
	}

	public void setEno(String eno) {
		this.eno = eno;
	}
	public String getEno() {
		return eno;
	}

	public void setMCode(String mcode) {
		this.mcode = mcode;
	}
	public String getMCode() {
		return mcode;
	}

	public void setApprovedby(String approvedby) {
		this.approvedby = approvedby;
	}
	public String getApprovedby() {
		return approvedby;
	}

	public void setLogin_Id(String login_Id) {
		this.login_Id = login_Id;
	}
	public String getLogin_Id() {
		return login_Id;
	}

	public void setUser_Name(String user_Name) {
		this.user_Name = user_Name;
	}
	public String getUser_Name() {
		return user_Name;
	}

	public void setUser_Type(String user_Type) {
		this.user_Type = user_Type;
	}

	public String getUser_Type() {
		return user_Type;
	}
	public void setUser_Pass(String user_Pass) {
		this.user_Pass = user_Pass;
	}

	public String getUser_Pass() {
		return user_Pass;
	}
	
	/*public void setUser_Branch(String User_Branch[]) {
		this.User_Branch = User_Branch;
	}

	public String[] getUser_Branch() {
		return User_Branch;
	}*/

	public void setUser_prodType(String User_prodType) {
		this.User_prodType = User_prodType;
	}

	public String getUser_prodType() {
		return User_prodType;
	}

	/*public void setUser_MenuLink(String menuLink[]) {
		this.menuLink = menuLink;
	}

	public String[] getUser_MenuLink() {
		return menuLink;
	}
	public void setUser_Product(String product[]) {
		this.product = product;
	}

	public String[] getUser_Product() {
		return product;
	}*/

	public void setUser_brokerCodes(String brokers[])
	{
		this.brokers = brokers;
	}
	public String[] getUser_brokerCodes()
	{
		return brokers;
	}

	public void setUser_menuIds(String menuIds)
	{
		this.menuIds = menuIds;
	}
	public void setUser_proIds(String proIds)
	{
		this.proIds = proIds;
	}

	public String getUser_menuIds()
	{
		return menuIds;
	}
	public String getUserProIds()
	{
		return proIds;
	}
	
	public void setUser_Country(String loginCountry)
	{
		this.loginCountry = loginCountry;
	}

	public String getUser_Country()
	{
		return loginCountry;
	}

	// / For Bank Master ///
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public String getBankId() {
		return this.bankId;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankName() {
		return this.bankName;
	}

	public void setRsaCode(String rsaCode) {
		this.rsaCode = rsaCode;
	}

	public String getRsaCode() {
		return this.rsaCode;
	}

	// For Bank Master //

	/** **** Admin Type Creation  ********* */

	public void setAdmType(String admTyp) {
		this.admTyp = admTyp;
	}

	public String getAdmType() {
		return admTyp;
	}

	public void setNewAdminType(String newAdminType) {
		this.newAdminType = newAdminType;
	}

	public void setNewAdminRemarksTxt(String adminRemarksTxt) {
		this.adminRemarksTxt = adminRemarksTxt;
	}

	public void setAdminTypeStatus(String adminStatus) {
		this.adminStatus = adminStatus;
	}

	public void setUser_Branch(String User_Branch) {
		this.User_Branch = User_Branch;
	}

	public String getUser_Branch() {
		return User_Branch;
	}
	/** *** AdminType Creation End  ******** */

	/* Menu Creation */
	public void setMenuName(String menuName){
		this.menuName = menuName;
	}
	public void setMenuRemarksTxt(String menuRemarksTxt){
		this.menuRemarksTxt = menuRemarksTxt;
	}
	public void setMenuStatus(String menuStatus){
		this.menuStatus = menuStatus;
	}
	
	public String setMenuName(){
		return menuName;
	}
	public String setMenuRemarksTxt(){
		return menuRemarksTxt;
	}
	public String setMenuStatus(){
		 return menuStatus;
	}

	/** for Exchange Rate setter and getter **/

	public void setRemarkText(String remarkText) {
		this.remarkText = remarkText;
	}

	public String getRemarkText() {
		return remarkText;
	}

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

	public String getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyNo(String currencyNo) {
		this.currencyNo = currencyNo;
	}

	public String getCurrencyNo() {
		return currencyNo;
	}

	public void setExchangeRate(String exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public String getExchangeRate() {
		return exchangeRate;
	}

	public void setEffectDate(String effectDate) {
		this.effectDate = effectDate;
	}

	public String getEffectDate() {
		return effectDate;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCity() {
		return city;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getBranch() {
		return branch;
	}
	
	public void setadminCountryId(String adminCountryId)
	{
		this.adminCountryId = adminCountryId;
	}
	
	public String getadminCountryId() 
	{
		return adminCountryId;
	}

	public void setLoginPersonId(String loginPersonId) {
		this.loginPersonId = loginPersonId;
	}

	public String getLoginPersonId() {
		return loginPersonId;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setDisStatus(String disStatus) {
		this.disStatus = disStatus;
	}

	public String getDisStatus() {
		return disStatus;
	}

	public void setBrokerIds(String brokerIds) {
		this.brokerIds = brokerIds;
	}

	public String getBrokerIds() {
		return brokerIds;
	}

	public void setOut(PrintWriter out) {
		this.out = out;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getGender() {
		return gender;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getNationality() {
		return nationality;
	}

	public void setBrokerDate(String brokerDate) {
		this.brokerDate = brokerDate;
	}

	public String getBrokerDate() {
		return brokerDate;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMobile() {
		return mobile;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getFax() {
		return fax;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getAddress3() {
		return address3;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setEmirate(String emirate) {
		this.emirate = emirate;
	}

	public String getEmirate() {
		return emirate;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCountry() {
		return country;
	}

	public void setPoBox(String poBox) {
		this.poBox = poBox;
	}

	public String getPoBox() {
		return poBox;
	}

	public void setBrokerCompanyName(String brokerCompanyName) {
		this.brokerCompanyName = brokerCompanyName;
	}

	public String getBrokerCompanyName() {
		return brokerCompanyName;
	}

	public void setBrokerId(String brokerId) {
		this.brokerId = brokerId;
	}

	public String getBrokerId() {
		return brokerId;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setRetypePassword(String retypePassword) {
		this.retypePassword = retypePassword;
	}

	public String getRetypePassword() {
		return retypePassword;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getError() {
		return error;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getMode() {
		return mode;
	}

	public void setDobDay(String dobDay) {
		this.dobDay = dobDay;
	}

	public void setDobMonth(String dobMonth) {
		this.dobMonth = dobMonth;
	}

	public void setDobYear(String dobYear) {
		this.dobYear = dobYear;
	}

	public String getDobDay() {
		return dobDay;
	}

	public String getDobMonth() {
		return dobMonth;
	}

	public String getDobYear() {
		return dobYear;
	}

	public void setBCode(String bcode) {
		this.bcode = bcode;
	}

	public String getBCode() {
		return bcode;
	}

	public String getRsa_broker_code() {
		return rsa_broker_code;
	}

	public void setRsa_broker_code(String rsa_broker_code) {
		this.rsa_broker_code = rsa_broker_code;
	}

	public void setBOrganization(String borganization) {
		this.borganization = borganization;
	}

	public String getBOrganization() {
		return borganization;
	}

	/*** for commision ***/

	HashMap proDetails = new HashMap();

	public void setProDetails(HashMap proDetails) {
		this.proDetails = proDetails;
	}

	public HashMap getProDetails() {
		return proDetails;
	}
	public void setEmail_Id(String email_id)
	{
		this.email_id = email_id;
	}
	public String getEmail_Id()
	{
		return this.email_id;
	}
	public String validate() 
	{
		com.maan.common.util.dataCollection data = new com.maan.common.util.dataCollection();
		String error = "";
		String values = null;
		boolean flag = false;
		try 
		{
				
			if ("Select".equalsIgnoreCase(branch))
				error = error + "<br>* Select Branch";

			values = data.validString(bcode, false);
			if ("needed".equalsIgnoreCase(values))
				error = error + "<br>*" + runner.getErrormsg("52");
			else if (StringUtil.checkSpecial(bcode)) {
				error = error + "<BR>*Spcial Chars Not Allowed";
			}
			if (!mode.equalsIgnoreCase("edit")) {
				/*
				 * if(checkCode(bcode)) { error=error+"<BR>*This Code Already
				 * Existed"; }
				 */
			}

			values = data.validString(borganization, false);
			
			error = error + commonSpecialValidation(borganization,"Organization Name");

			if ("needed".equalsIgnoreCase(values))
				error = error + "<br>*" + runner.getErrormsg("53");

			/*if (StringUtil.checkSpecial(borganization)) 
				error = error + "<BR>*Spcial Chars Not Allowed "+borganization;*/
		
			if(!address1.equalsIgnoreCase("") && address1 !=null && !address1.equalsIgnoreCase("null"))
			{
				flag = StringUtil.addressValidation(address1); // address1
				 if(!flag)
					 error = error+"<br>*Address1 field special Characters are not allowed "+address1;
			}

			if(!address2.equalsIgnoreCase("") && address2 !=null && !address2.equalsIgnoreCase("null"))
			{
				flag = StringUtil.addressValidation(address2); // address2
				 if(!flag)
					 error = error+"<br>*Address2 field special Characters are not allowed "+address2;
			}
			
			if(!city.equalsIgnoreCase("") && !city.equalsIgnoreCase("null") && city != null)
			{
				if (StringUtil.checkSpecial(city)) 
					error = error + "<BR>*Spcial Chars Not Allowed "+city;
			
				if (!StringUtil.isAlphabets(city)) 
					error = error + "<BR>*City field Not Allowed Numeric Values "+city;
			}
			
			flag = specialCharacterValidation(telephone); //Telephone Field
			if(!flag)
				error = error+"<br>*Special Characters are not allowed "+telephone;
			
			flag = validPhoneMobile(telephone); //Telephone Field
			if(!flag)
				error = error+"<br>*Mobile number or Phone Number Should be digit "+telephone;

			flag = specialCharacterValidation(mobile); //Mobile Field
		    if(!flag)
				error = error+"<br>*Special Characters are not allowed "+mobile;
			
			flag = validPhoneMobile(mobile); //Mobile Field
			if(!flag)
				error = error+"<br>*Mobile number or Phone Number Should be digit "+mobile;

		   flag = specialCharacterValidation(fax); // fax
		   if(!flag)
				error = error+"<br>*Special Characters are not allowed "+fax;

		   flag = validPhoneMobile(fax); // fax
		   if(!flag)
				error = error+"<br>*FAX Should be digit "+fax;

			/*
			 * 
			 * values=data.validLength(address1,5);
			 * if("needed".equalsIgnoreCase(values)) error=error+"<br>*"+runner.getErrormsg("21");
			 * 
			 * values=data.validLength(address2,5);
			 * if("needed".equalsIgnoreCase(values)) error=error+"<br>*"+runner.getErrormsg("22");
			 */
			values = data.validString(poBox, true);

			if ("needed".equalsIgnoreCase(values))
				error = error + "<br>*" + runner.getErrormsg("27");

			else if ("Invalid".equalsIgnoreCase(values))
				error = error + "<br>*" + runner.getErrormsg("28");

			else if ("needed".equalsIgnoreCase(data.validLength(poBox, 1)))
				error = error + "<br>*" + runner.getErrormsg("29");

			values = data.validString(telephone, true);
			// if("needed".equalsIgnoreCase(values))
			// error=error+"<br>*"+runner.getErrormsg("10");

			if ("Invalid".equalsIgnoreCase(values))
				error = error + "<br>*" + runner.getErrormsg("11");
			// else if("needed".equalsIgnoreCase(data.validLength(telephone,6)))
			// error=error+"<br>*"+runner.getErrormsg("12");

			values = data.validString(fax, true);

			// if("needed".equalsIgnoreCase(values))
			// error=error+"<br>*"+runner.getErrormsg("17");
			if ("Invalid".equalsIgnoreCase(values))
				error = error + "<br>*" + runner.getErrormsg("16");
			// else if("needed".equalsIgnoreCase(data.validLength(fax,8)))
			// error=error+"<br>*"+runner.getErrormsg("18");

			if ("0".equalsIgnoreCase(emirate))
				error = error + "<br>*" + runner.getErrormsg("25");
			else if ("others".equalsIgnoreCase(emirate)) {
				values = data.validString(city, false);
				if ("needed".equalsIgnoreCase(values))
					error = error + "<br>*" + runner.getErrormsg("55");
				else if ("Invalid".equalsIgnoreCase(values))
					error = error + "<br>*" + runner.getErrormsg("56");
			}

			values = data.validString(firstName, false);
			if ("needed".equalsIgnoreCase(values))
				error = error + "<br>*" + runner.getErrormsg("3");

			if (StringUtil.checkSpecial(firstName)) 
				error = error + "<BR>*Spcial Chars Not Allowed "+firstName;
			
			if (!StringUtil.isAlphabets(firstName)) 
				error = error + "<BR>*Name field Not Allowed Numeric Values "+firstName;
			
			if(!lastName.equalsIgnoreCase("") && !lastName.equalsIgnoreCase("null") && lastName != null)
			{
				if (StringUtil.checkSpecial(lastName)) 
					error = error + "<BR>*Spcial Chars Not Allowed "+lastName;
			
				if (!StringUtil.isAlphabets(lastName)) 
					error = error + "<BR>*Name field Not Allowed Numeric Values "+lastName;
			}
			
			if(!occupation.equalsIgnoreCase("") && !occupation.equalsIgnoreCase("null") && occupation != null)
			{
				if (StringUtil.checkSpecial(occupation)) 
					error = error + "<BR>*Spcial Chars Not Allowed "+occupation;
			
				if (!StringUtil.isAlphabets(occupation)) 
					error = error + "<BR>*Name field Not Allowed Numeric Values "+occupation;
			}

			// START CERTIFICATE NO and CERTIFICATE NO
			/*
			 * values=data.validString(sno,true);
			 * 
			 * if("needed".equalsIgnoreCase(values)) error=error+"<br>*"+runner.getErrormsg("147");
			 * if("Invalid".equalsIgnoreCase(values)) error=error+"<br>*"+runner.getErrormsg("147");
			 * 
			 * values=data.validString(eno,true);
			 * 
			 * if("needed".equalsIgnoreCase(values)) error=error+"<br>*"+runner.getErrormsg("148");
			 * if("Invalid".equalsIgnoreCase(values)) error=error+"<br>*"+runner.getErrormsg("148");
			 * 
			 * try { if(Integer.parseInt(sno)>Integer.parseInt(eno)) {
			 * error=error+"<br>*"+runner.getErrormsg("149"); }
			 * }catch(Exception e) { System.out.println("Excpetion
			 * "+e.toString()); }
			 * 
			 */

			/*
			 * 
			 * values=data.validString(city,false);
			 * if("needed".equalsIgnoreCase(values)) error=error+"<br>*"+runner.getErrormsg("55");
			 * else if("Invalid".equalsIgnoreCase(values)) error=error+"<br>*"+runner.getErrormsg("56");
			 * 
			 * if("0".equalsIgnoreCase(country)) error=error+"<br>*"+runner.getErrormsg("26");
			 * 
			 * 
			 * 
			 * 
			 * if("0".equalsIgnoreCase(title)) error=error+"<br>*"+runner.getErrormsg("1");
			 * 
			 * 
			
			 */

			if (!dobDay.equalsIgnoreCase("0")
					|| !dobMonth.equalsIgnoreCase("0")
					|| !dobYear.equalsIgnoreCase("0")) {
				values = data.checkDate(dobDay + "/" + dobMonth + "/" + dobYear);

				if ("Invalid".equalsIgnoreCase(values))
					error = error + "<br>*" + runner.getErrormsg("8");
			}

			
			values = data.validString(occupation, false);
			
			if ("Invalid".equalsIgnoreCase(values))
				error = error + "<br>*" + runner.getErrormsg("24");

			values = data.validString(mobile, true);

			
			if ("Invalid".equalsIgnoreCase(values))
				error = error + "<br>*" + runner.getErrormsg("15");
			

			values = data.validString(mcode, true);
			if ("needed".equalsIgnoreCase(values))
				error = error + "<br>*Please enter core application code";

			else if ("Invalid".equalsIgnoreCase(values))
				error = error + "<br>*Core Application Code is invalid";
			else if(arNo == null || "".equalsIgnoreCase(arNo))
				error = error + "<br>*" + runner.getErrormsg("473");
			
			values = data.validString(approvedby, true);
			if ("needed".equalsIgnoreCase(values))
				error = error + "<br>*Please enter Approved By";

			/*else if ("Invalid".equalsIgnoreCase(values))
				error = error + "<br>*Approvedby Code is Invalid";*/
			
			values = data.validString(rsa_broker_code, true);
			if ("needed".equalsIgnoreCase(values))
				error = error + "<br>*Please Enter Broker Code";

		/*	else if ("Invalid".equalsIgnoreCase(values))
				error = error + "<br>* Broker Code is Invalid";
			if(rsa_broker_code.length()>4)
				error = error + "<br>*Please enter Broker Code within 4 digits";*/

			values = data.emailValidate(email);
			if ("needed".equalsIgnoreCase(values))
				error = error + "<br>*" + runner.getErrormsg("19");
			else if ("Invalid".equalsIgnoreCase(values))
				error = error + "<br>*" + runner.getErrormsg("20");

		} catch (Exception e) {
			System.out.println("Exception in " + e.toString());
		}
		return error;
	}

	public String validateLoginCreation() 
	{
		String error = "";

		try {

			if (brokerId.equals("") || brokerId == null	|| brokerId.equalsIgnoreCase("null") || brokerId.length() < 5 || brokerId.indexOf(" ")!=-1) {
				error = error + "<BR>*Enter valid brokerId";
			}
			
			else if (StringUtil.checkSpecial(brokerId)) 
			{
				error = error + "<BR>*Spcial Chars Not Allowed";
			}
			else if (!mode.equalsIgnoreCase("edit")) 
			{
				/*
				 * if(checkCommonBrokerId(brokerId)) { error=error+"<BR>*This
				 * Broker Id Already Existed"; } else
				 */
				if (checkBrokerId(brokerId)) 
				{
					error = error + "<BR>*This broker id already existed";
				}
			}

			if (password.equals("") || password == null	|| password.equalsIgnoreCase("null") || password.indexOf(" ")!= -1) 
			{
				error = error + "<BR>* "+runner.getErrormsg("362");
			}

			else if (password.length() <= 6) {
				error = error + "<BR>* "+runner.getErrormsg("361");//Password should be minimum seven characters";
			} else if (StringUtil.checkSpecial(password)) {
				error = error + "<BR>* "+runner.getErrormsg("363"); //PassWord As Spcial Chars Not Allowed ";
			} else if (!validPassword(password)) {
				error = error + "<BR>* "+runner.getErrormsg("365");//Please Enter Valid Password";
			}
			if (!password.equals(retypePassword)) {
				error = error + "<BR>* "+runner.getErrormsg("364");//Entered passwords are not Matched";
			}

		} catch (Exception e) {
			System.out.println("Exception e" + e.toString());
		}
		return error;
	}

	public boolean validPassword(String value)
	{
		boolean b = false;
		String validChar = "";
		int p = 0;
		int j = 0;
		// value=value.toLowerCase();
		validChar = "abcdefghijklmnopqrstuvwxyz";
		for (int i = 0; i < value.length(); i++) {
			// char c=c.charAt(i);
			if (validChar.indexOf(value.charAt(i)) != -1) {
				p++;
				break;
			}
		}

		validChar = "1234567890";
		for (int i = 0; i < value.length(); i++) {
			// char c=c.charAt(i);
			if (validChar.indexOf(value.charAt(i)) != -1) {
				j++;
				break;
			}
		}
		if (p == 1 && j == 1) {
			b = true;
		} else {
			b = false;
		}

		return b;
	}
	
	public String insertBrokerEntry() 
	{
		java.util.Date dd = new java.util.Date();
		java.text.SimpleDateFormat simpleFormatter = new java.text.SimpleDateFormat("dd-MM-yyyy");
		simpleFormatter.setTimeZone(new java.util.SimpleTimeZone(14400000,"GMT"));            	
    	String user_Id = "1";
		String Query = "";
		String process = "";
		int customerid = 0;
		int brokerid = 0;
		int brokercode = 0;
		String args[] = new String[0];
		String res = "";
		try
		{
			args = new String[2];
			args[0] = bcode;
			args[1] = branch;
			String sql = "select CUSTOMER_ID from broker_company_master where agency_code=? and branch_code=?";
			res = runner.singleSelection(sql,args);
			
			if(res.length() > 0 && !res.equalsIgnoreCase("null")) 
			{
				customerid = Integer.parseInt(res);
			} 
			else 
			{
				brokercode = 0;
			}

		} 
		catch (Exception e) 
		{
			System.out.println("Exception in checking broker id" + e.toString());
			e.printStackTrace();
		}
		
		if (customerid <= 0)
		{
			try
			{
				customerid = Integer.parseInt(getMaxCustomerId(branch));
			}
			 catch(Exception e)
			{
				 System.out.println("Exception getting max in"+e.toString());
				 e.printStackTrace();
			}

			String sqlboth = "insert into broker_company_master(AGENCY_CODE,COMPANY_NAME,CONTACT_PERSON,ADDRESS1,ADDRESS2,ADDRESS3,CITY,COUNTRY,PHONE,POBOX,FAX,EMIRATE,REMARKS,STATUS,CUSTOMER_ID,BRANCH_CODE,MISSIPPI_ID,APPROVED_PREPARED_BY,rsa_broker_code,AC_EXECUTIVE_ID) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
			borganization = borganization.replaceAll("'", "''");
			firstName = firstName.replaceAll("'", "''");
			
			try 
			{
				args = new String[20];
				args[0] = bcode;
				args[1] = borganization;
				args[2] = firstName;
				args[3] = address1;
				args[4] = address2;
				args[5] = address3;
				args[6] = city;
				args[7] = country;
				args[8] = telephone;
				args[9] = poBox;
				args[10] = fax;
				args[11] = emirate;
				args[12] = "";
				args[13] = "Y";
				args[14] = ""+customerid;
				args[15] = branch;
				args[16] = mcode;
				args[17] = approvedby;
				args[18] = rsa_broker_code;
				args[19] = executiveId;
				
				runner.multipleInsertion(sqlboth,args);
	
				String bbcode = "";
				int ii = 0;
				try 
				{
					ii = Integer.parseInt(bcode);
					bbcode = "" + (++ii);
				} 
				catch (Exception e) 
				{
					bbcode = bbcode + "1";
				}
				
				/*args = new String[1];
				args[0] = bbcode;
				sqlboth = "update CONSTANT_DETAIL set DETAIL_NAME=? where CATEGORY_ID=38 and CATEGORY_DETAIL_ID=1 and  branch_code in ("+flowBranch+")";
				System.out.println("Flow Branch......."+flowBranch);
				runner.multipleUpdation(sqlboth,args);*/
			
				process = "one";
				System.out.println("This is Test " + process);
			}
			catch (Exception e) 
			{
				System.out.println("Exception in inserting in compy "	+ e.toString());
				e.printStackTrace();
			}
		}	 
		else 
		{
			
			String sqlboth = "update  broker_company_master set COMPANY_NAME=?,CONTACT_PERSON=?,ADDRESS1=?,ADDRESS2=?,ADDRESS3=?,CITY=?,COUNTRY=?,PHONE=?,POBOX=?,FAX=?,EMIRATE=?,CUSTOMER_ID=?,BRANCH_CODE=?,MISSIPPI_ID=?,APPROVED_PREPARED_BY=?,RSA_BROKER_CODE=?,AC_EXECUTIVE_ID=? where agency_code=? and branch_code=?";
					
			borganization = borganization.replaceAll("'", "''");
			firstName = firstName.replaceAll("'", "''");
			try 
			{
				args = new String[19];
				
				args[0] = borganization;
				args[1] = firstName;
				args[2] = address1;
				args[3] = address2;
				args[4] = address3;
				args[5] = city;
				args[6] = country;
				args[7] = telephone;
				args[8] = poBox;
				args[9] = fax;
				args[10] = emirate;
				args[11] = ""+customerid;
				args[12] = branch;
				args[13] = mcode;
				args[14] = approvedby;
				args[15] = rsa_broker_code;
				args[16] = executiveId;
				args[17] = bcode;
				args[18] = branch;

				runner.multipleUpdation(sqlboth,args);						
				process = "one";
			}
			catch (Exception e)
			{
				System.out.println("exception in updation of the broker details"+ e.toString());
				e.printStackTrace();
			}
		}
		if (!checkCustomerId(String.valueOf(customerid))) 
		{
			try
			{
				String date123 = "";
				if (!dobDay.equals("") && !dobDay.equals("0")&& !dobMonth.equals("") && !dobMonth.equals("0")&& !dobYear.equals("") && !dobMonth.equals("0")) 
				{
					date123 = com.maan.common.util.OracleDateConversion.ConvertDate(dobDay + "-" + dobMonth + "-"+ dobYear);
				}

				String qry = "insert into personal_info(customer_id,application_id,title,first_name,last_name,amend_id,nationality,dob,gender,telephone,mobile,fax,email,address1,address2,occupation,pobox,country,emirate,status,entry_date,login_id,agency_code,oa_code,CUST_NAME,CUST_AR_NO)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				
				lastName = lastName.replaceAll("'", "''");
				firstName = firstName.replaceAll("'", "''");
			
				args = new String[26];
				args[0] = ""+customerid;
				args[1] = "2";
				args[2] = title;
				args[3] = firstName;
				args[4] = lastName;
				args[5] = "1";
				args[6] = nationality;
				args[7] = date123;
				args[8] = gender;
				args[9] = telephone;
				args[10] = mobile;
				args[11] = fax;
				args[12] = email;
				args[13] = address1;
				args[14] = address2;
				args[15] = occupation;
				args[16] = poBox;
				args[17] = country;
				args[18] = emirate;
				args[19] = "Y";
				args[20] = com.maan.common.util.OracleDateConversion.ConvertDate("" + simpleFormatter.format(dd));
				args[21] = brokerId;
				args[22] = bcode;
				args[23] = bcode;
				args[24] = brokerName;
				args[25] = arNo;

				runner.multipleInsertion(qry,args);
			} 
			catch (Exception e) 
			{
				System.out.println("Exception inserting datas in personal info"	+ e.toString());
			}
			
			/*int typeid = autoGenCustId("TYPE_ID", "POLICYNO_GENERATE");
			try 
			{
				psmt.executeUpdate();
			}
			catch (Exception e) 
			{

			}*/
			process = process + "two";
		} 
		else
		{
			String date123 = "";
			if (!dobDay.equals("") && !dobDay.equals("0")&& !dobMonth.equals("") && !dobMonth.equals("0")&& !dobYear.equals("") && !dobYear.equals("0")) 
			{
				date123 = com.maan.common.util.OracleDateConversion	.ConvertDate(dobDay + "-" + dobMonth + "-" + dobYear);
			}

			try
			{
				String qry = "update personal_info set title=?,first_name=?,last_name=?,nationality=?,dob=?,gender=?,telephone=?,mobile=?,fax=?,email=?,address1=?,address2=?,occupation=?,pobox=?,country=?,emirate=?,status=?,effective_date=?,CUST_NAME=?,CUST_AR_NO=?  where application_id='2' and agency_code=?";

				lastName = lastName.replaceAll("'", "''");
				firstName = firstName.replaceAll("'", "''");

				args = new String[21];
				args[0] = title;
				args[1] = firstName;
				args[2] = lastName;
				args[3] = nationality;
				args[4] = date123;
				args[5] = gender;
				args[6] = telephone;
				args[7] = mobile;
				args[8] = fax;
				args[9] = email;
				args[10] = address1;
				args[11] = address2;
				args[12] = occupation;
				args[13] = poBox;
				args[14] = country;
				args[15] = emirate;
				args[16] = "Y";
				args[17] = com.maan.common.util.OracleDateConversion.ConvertDate("" + simpleFormatter.format(dd));
				args[18] = brokerName;
				args[19] = arNo;
				args[20] = bcode;
				
				runner.multipleUpdation(qry,args);

				int pcount = 0;
				try 
				{
					args = new String[2];
					args[0] = bcode;
					args[1] = branch;
					String sqlp = "select count(*) from policyno_generate where agency_code=? and branch_code=?";
					res = runner.singleSelection(sqlp,args);
					if(res.length() > 0 && !res.equalsIgnoreCase("nul")) 
					{
						pcount = Integer.parseInt(res);
					}
					else 
					{
						pcount = 0;
					}

				} catch (Exception e)
				{
					System.out.println("Exception in checking broker id"+ e.toString());
					e.printStackTrace();
				}
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			process = process + "two";
		}
		return process;
	}

	public String validateCommision() // BrokerCreation Commissition Validation
	{

		com.maan.common.util.dataCollection data = new com.maan.common.util.dataCollection();
		String error = "";
		String office = "";
		String offTemp = ""; 
		String schemeId = "";
		HashMap pro = new HashMap();
		
		if (proDetails.size() > 0) 
		{
			HashMap royalTry = new HashMap();
			for (int i = 0; i < proDetails.size(); i++) 
			{
				pro = (HashMap) proDetails.get("productsdetails" + (i + 1));
				if (pro == null) 
				{
					i++;
					continue;
				}
				String values = "";
				if((String)pro.get("product"+(i+1))!=null)	
					offTemp = (String)pro.get("product"+(i+1));

				office = offTemp==null?"":offTemp;
				
				
				if (!office.equals("11") && office.length()<=2&&!royalTry.containsValue(office)) 
				{
					
					values = data.validString((String) pro.get("commision"+ (i + 1)), true);
					if ("needed".equalsIgnoreCase(values))
					{
						error = error+"<br>* Enter Valid Commmision for Product-->  "+getProductById(office);
						System.out.println("Enter Valid Commmision for Product--> " + office);
					}
					else if ("Invalid".equalsIgnoreCase(values))
						error = error+"<br>* Enter Valid Commmision for Product-->  "+getProductById(office);
					//else if (((String) pro.get("commision" + (i + 1))).equalsIgnoreCase("0"))//if (Double.parseDouble((String) pro.get("commision"+ (i + 1))) > 0) //20
						//error = error+ "<br>* Commmision for Product  "+ getProductById((String) pro.get("product"+(i + 1)));
					else if (Double.parseDouble((String) pro.get("commision"+ (i + 1))) > 100)
						error = error+ "<br>* Commmision for Product  "+getProductById(office)+" Should Not Exceed 100%";
					/****/
					values = data.validString((String) pro.get("commision"+ (i + 1)), true);
					if("needed".equalsIgnoreCase(values))
						error = error+ "<br>* Enter Valid Commmision for Product--> "+getProductById(office);
					/****/
					values = data.validString((String) pro.get("suminsured"+(i+1)), true);
					if ("needed".equalsIgnoreCase(values))
						error = error+ "<br>* Enter Valid suminsured for Product--> "+getProductById(office);
					else if ("Invalid".equalsIgnoreCase(values))
						error = error+"<br>* Enter Valid suminsured for Product-->  "+getProductById(office);
					if (pro.get("suminsured" + (i + 1))!=null)
					{
						if (((String) pro.get("suminsured" + (i + 1))).equalsIgnoreCase("0")) 
						{
							error = error+ "<br>* Suminsured Insured Should Not Be Zero for Product   "+getProductById(office);
						}
					}
					values = data.validString((String) pro.get("premium"+(i+1)),true);
					if ("needed".equalsIgnoreCase(values))
						error = error+"<br>* Enter Valid premium for Product-->  "+getProductById(office);
					else if ("Invalid".equalsIgnoreCase(values))
						error = error+ "<br>* Enter Valid premium for Product-->  "+getProductById(office);
					if (pro.get("premium" + (i + 1))!=null)
					{
						if (((String) pro.get("premium" + (i + 1))).equalsIgnoreCase("0")) 
						{
							error = error+"<br>* Premium Insured Should Not Be Zero for Product-->  "+getProductById(office);
						}
					}
					values = data.validString((String) pro.get("loading"+(i+1)),true);System.out.println("Loading Values"+values);
					if ("needed".equalsIgnoreCase(values))
						error = error+ "<br>** Enter Valid Loading % for Product-->"+getProductById(office);
					else if ("Invalid".equalsIgnoreCase(values))
						error = error+ "<br>* Enter Valid Loading % for Product-->"+getProductById(office);

					values = data.validString((String) pro.get("discount"+(i+1)),true);System.out.println("Discount Values"+values);
					if ("needed".equalsIgnoreCase(values))
						error = error+ "<br>* Enter Valid Discount % for Product-->"+getProductById(office);
					else if ("Invalid".equalsIgnoreCase(values))
						error = error+ "<br>* Enter Valid Discount % for Product-->"+getProductById(office);

				/*else if (Double.parseDouble((String) pro.get("discount"+(i + 1))) > Double.parseDouble(getDiscountLimit(adminBranch)))
					error = error+ "<br>* Discount for Product-->  "+ getProductById((String) pro.get("product"+ (i + 1))) + " Should Not Exceed"+ getDiscountLimit(adminBranch) + " %";*/

					values = data.validString((String) pro.get("bday" + (i + 1)), true);
					if ("needed".equalsIgnoreCase(values))
						error = error+ "<br>* Enter Valid Back Days Allowed for Product-->  "+getProductById(office);
					else if ("Invalid".equalsIgnoreCase(values))
						error = error+"<br>* Enter Valid Back Days Allowed for Product-->  "+getProductById(office);
					
				}	// Product Id 11
				royalTry.put("royal",office);
			}
		} else {
			error = error + "<BR>*Select At Least One Product";
		}
		return error;
	}
	
	public String validateCommisionTestSite(String adminBranch)
	{
		com.maan.common.util.dataCollection data = new com.maan.common.util.dataCollection();
		String error = "";
		HashMap pro = new HashMap();
		System.out.println("length of the produs are " + proDetails.size());
		if (proDetails.size() > 0) 
		{

			for (int i = 0; i < proDetails.size(); i++) 
			{
				pro = (HashMap) proDetails.get("productsdetails" + (i + 1));
				if (pro == null) 
				{
					i++;
					continue;
				}

				String values = "";
				if (!((String) pro.get("product" + (i + 1))).equals("11")) {
					values = data.validString((String) pro.get("commision"
							+ (i + 1)), true);
					if ("needed".equalsIgnoreCase(values))
						error = error
								+ "<br>* Enter Valid Commmision for Product-->  "
								+ getProductById((String) pro.get("product"
										+ (i + 1)));
					else if ("Invalid".equalsIgnoreCase(values))
						error = error
								+ "<br>* Enter Valid Commmision for Product-->  "
								+ getProductById((String) pro.get("product"
										+ (i + 1)));
					else if (((String) pro.get("commision" + (i + 1))).equalsIgnoreCase("0")) //(Double.parseDouble((String) pro.get("commision"+ (i + 1))) > 0)
						error = error+ "<br>* Commmision for Product  "	+ getProductById((String) pro.get("product"+ (i + 1)));
					else if (Double.parseDouble((String) pro.get("commision"	+ (i + 1))) > 100)
						error = error+ "<br>* Commmision for Product  "	+ getProductById((String) pro.get("product"+ (i + 1))) + " Should Not Exceed 100%";
					
				/****/
				if(adminBranch.equalsIgnoreCase("020") || adminBranch.equalsIgnoreCase("021") || adminBranch.equalsIgnoreCase("022") )  
				{
					values = data.validString((String) pro.get("commision"+ (i + 1)), true);
					if("needed".equalsIgnoreCase(values))
						error = error+ "<br>* Enter Valid Commmision for Product--> "+getProductById((String)pro.get("product"+(i+1)));
					else
					{
						/*if(Double.parseDouble((String) pro.get("commision"+ (i + 1))) < 20)
						{
							error = error+ "<br>* Commmision for Product  "+ getProductById((String) pro.get("product"+(i+1)))+"Minimum 20%";
						}*/
					}
				}
				/****/


					values = data.validString((String) pro.get("suminsured"
							+ (i + 1)), true);
					if ("needed".equalsIgnoreCase(values))
						error = error
								+ "<br>* Enter Valid suminsured for Product-->  "
								+ getProductById((String) pro.get("product"
										+ (i + 1)));
					else if ("Invalid".equalsIgnoreCase(values))
						error = error
								+ "<br>* Enter Valid suminsured for Product-->  "
								+ getProductById((String) pro.get("product"
										+ (i + 1)));

					if (((String) pro.get("suminsured" + (i + 1))).equalsIgnoreCase("0")) {
						error = error
								+ "<br>* Suminsured Insured Should Not Be Zero for Product   "
								+ getProductById((String) pro.get("product"
										+ (i + 1)));
					}
					values = data.validString((String) pro.get("premium"
							+ (i + 1)), true);
					if ("needed".equalsIgnoreCase(values))
						error = error
								+ "<br>* Enter Valid premium for Product-->  "
								+ getProductById((String) pro.get("product"
										+ (i + 1)));
					else if ("Invalid".equalsIgnoreCase(values))
						error = error
								+ "<br>* Enter Valid premium for Product-->  "
								+ getProductById((String) pro.get("product"
										+ (i + 1)));

					if (((String) pro.get("premium" + (i + 1)))
							.equalsIgnoreCase("0")) {
						error = error
								+ "<br>* Premium Insured Should Not Be Zero for Product-->  "
								+ getProductById((String) pro.get("product"
										+ (i + 1)));
					}

					values = data.validString((String) pro.get("loading"+ (i + 1)), true);
					if ("needed".equalsIgnoreCase(values))
						error = error+ "<br>* Enter Valid Loading % for Product-->  "+ getProductById((String) pro.get("product"+ (i + 1)));
					else if ("Invalid".equalsIgnoreCase(values))
						error = error+ "<br>* Enter Valid Loading % for Product-->  "+ getProductById((String) pro.get("product"+ (i + 1)));

					values = data.validString((String) pro.get("discount"+ (i + 1)), true);
					if ("needed".equalsIgnoreCase(values))
						error = error+ "<br>* Enter Valid discount % for Product-->  "+ getProductById((String) pro.get("product"+ (i + 1)));
					else if ("Invalid".equalsIgnoreCase(values))
						error = error+ "<br>* Enter Valid discount % for Product-->  "+ getProductById((String) pro.get("product"+ (i + 1)));
				/*	else if (Double.parseDouble((String) pro.get("discount"+ (i + 1))) > Double.parseDouble(getDiscountLimit(adminBranch)))
						error = error+ "<br>* Discount for Product-->  "+ getProductById((String) pro.get("product"+ (i + 1))) + " Should Not Exceed"+ getDiscountLimit(adminBranch) + " %";*/


					values = data.validString((String) pro
							.get("bday" + (i + 1)), true);
					if ("needed".equalsIgnoreCase(values))
						error = error
								+ "<br>* Enter Valid Back Days Allowed for Product-->  "
								+ getProductById((String) pro.get("product"
										+ (i + 1)));
					else if ("Invalid".equalsIgnoreCase(values))
						error = error
								+ "<br>* Enter Valid Back Days Allowed for Product-->  "
								+ getProductById((String) pro.get("product"
										+ (i + 1)));
					// else
					// if(Double.parseDouble((String)pro.get("bday"+(i+1)))>Double.parseDouble(getDiscountLimit()))
					// error=error+"<br>* Discount for Product-->
					// "+getProductById((String)pro.get("product"+(i+1)))+"
					// Should Not Exceed"+getDiscountLimit()+" %";

				}
				/*
				 * if(pro.get("commision"+(i+1)).equals("")||pro.get("commision"+(i+1))==null||pro.get("commision"+(i+1)).equals("null")) {
				 * error=error+"<BR>*Enter Valid Commmision for Product
				 * "+(i+1); } else { values=data.validString(telephone,true);
				 * if("needed".equalsIgnoreCase(values)) error=error+"<br>*
				 * Enter Valid Commmision for Product"++(i+1);; else
				 * if("Invalid".equalsIgnoreCase(values)) error=error+"<br>*"+runner.getErrormsg("11"); }
				 * 
				 * 
				 * 
				 * if(pro.get("suminsured"+(i+1)).equals("")||pro.get("suminsured"+(i+1))==null||pro.get("suminsured"+(i+1)).equals("null")) {
				 * error=error+"<BR>*Enter Valid Suminsured for Product
				 * "+(i+1); }
				 * 
				 * if(pro.get("discount"+(i+1)).equals("")||pro.get("discount"+(i+1))==null||pro.get("discount"+(i+1)).equals("null")) {
				 * error=error+"<BR>*Enter Valid discount for Product "+(i+1); }
				 */
			}
		} 
		else 
		{
			error = error + "<BR>*Select At Least One Product";
		}
		return error;
	}

	public String insertOrUpdate(String bcode,String loginPersonId) 
	{
		java.util.Date dd = new java.util.Date();
		java.text.SimpleDateFormat simpleFormatter = new java.text.SimpleDateFormat("dd-MM-yyyy");
		simpleFormatter.setTimeZone(new java.util.SimpleTimeZone(14400000,"GMT"));            	
    	String process = "";
		int i = 0;
		String commision = "0";
		String suminsured = "0";
		String premium = "0";
		String discount = "0";
		String loading = "0";
		String bday = "";
		String user = "";
		String referral = "";
		String account = "";
		String[][] userdetails = new String[0][0];
		String proCommission="0";
		String sdate="";
		String edate="";
		String payReceipt = "";
		String debit = "";
		String loadDis = "";
		String args[] = new String[0];
		String res = "";
		HashMap pro = new HashMap();
		String schemeId = "";
		String office = "";
		String offTemp = ""; 
		
		try 
		{
			pro = (HashMap) proDetails.get("productsdetails" + (i + 1));
			try 
			{
				args = new String[1];
				args[0] = bcode;
				String sql = "select CUSTOMER_ID,FIRST_NAME,AGENCY_CODE from personal_info where AGENCY_CODE=?";

				userdetails = runner.multipleSelection(sql,args);

				String[][] products = new String[0][0];
				args[0] = bcode;
				String sqlcheck = "select product_id,nvl(scheme_id,'0') from login_user_details where AGENCY_CODE=? order by product_id";
				try 
				{
					products = runner.multipleSelection(sqlcheck,args);
				} 
				catch (Exception e) 
				{
					System.out.println("Exception in products status"+ e.toString());
					e.printStackTrace();
				}
				String avail = "not";
				String sqlstatus = "";
				String addedQry = "";
				for (int p = 0; p < products.length; p++) 
				{
					avail = "not";
					for (int h = 0; h < proDetails.size(); h++) 
					{
						if((String)pro.get("product"+(h+1))!=null)
							offTemp = (String)pro.get("product"+(h+1));

						if(offTemp.length() >= 3 && !offTemp.equalsIgnoreCase("null"))
						{
							office = offTemp.substring(0,offTemp.length()-1);
							schemeId = offTemp.substring(offTemp.length()-1,offTemp.length());
						}
						else
						{
							office = offTemp;
							schemeId = "";
						}
						if(products[p][0].equalsIgnoreCase(office)&&products[p][1].equalsIgnoreCase(schemeId)) 
						{
							avail = "yes";
							break;
						}
						else if(products[p][0].equalsIgnoreCase(office)&&schemeId.length()<=0) 
						{
							avail = "yes";
							break;
						}
					}
					if (avail.equals("yes")) 
					{
						System.out.println("available>>>>"+ products[p][0]);
					}
					else
					{
						if(products[p][1].equals("0"))
						{
							sqlstatus = "update login_user_details set status='N' where AGENCY_CODE=? and product_id=?";
							try
							{
								args = new String[2];
								args[0] = bcode;
								args[1] = products[p][0];
								runner.multipleUpdation(sqlstatus,args);
							}
							catch (Exception e) 
							{
								System.out.println("exception in changing password"+e.toString());
								e.printStackTrace();
							}
						}
						else
						{
							sqlstatus = "update login_user_details set status='N' where AGENCY_CODE=? and product_id=? and scheme_id=?";
							try
							{
								args = new String[3];
								args[0] = bcode;
								args[1] = products[p][0];
								args[2] = products[p][1];
								runner.multipleUpdation(sqlstatus,args);
							}
							catch (Exception e) 
							{
								System.out.println("exception in changing password"+e.toString());
								e.printStackTrace();
							}
						}
					}
			}
			for (i = 0; i < proDetails.size(); i++) 
			{
					pro = (HashMap) proDetails.get("productsdetails"+ (i + 1));
					if (userdetails.length > 0)
					{
						offTemp = (String)pro.get("product"+(i+1));
						offTemp = offTemp==null?"":offTemp;
						if(offTemp.length() >= 3 && !offTemp.equalsIgnoreCase("null"))
						{
							office = offTemp.substring(0,offTemp.length()-1);
							schemeId = offTemp.substring(offTemp.length()-1,offTemp.length());
						}
						else
						{
							office = offTemp;
							schemeId = "";
						}
						
						

						if(!office.equalsIgnoreCase("30"))
						{
							System.out.println(" its product not off *************************"+pro.size());
							if (((String) pro.get("commision" + (i + 1))).length() > 0 && pro.get("commision" + (i + 1)) != null) {
								commision = (String) pro.get("commision" + (i + 1));
							}
							if (((String) pro.get("suminsured" + (i + 1))).length() > 0	&& pro.get("suminsured" + (i + 1)) != null) {
								suminsured = (String) pro.get("suminsured"+ (i + 1));
							}
							if (((String) pro.get("premium" + (i + 1))).length() > 0 && pro.get("premium" + (i + 1)) != null) {
								premium = (String) pro.get("premium" + (i + 1));
							}
							/**Loading**/
							if (((String) pro.get("loading" + (i + 1))).length() > 0 && pro.get("loading" + (i + 1)) != null) {
								loading = (String) pro.get("loading" + (i + 1));
							}
							if (((String) pro.get("discount" + (i + 1))).length() > 0 && pro.get("discount" + (i + 1)) != null) {
								discount = (String) pro.get("discount" + (i + 1));
							}
							if (((String) pro.get("bday" + (i + 1))).length() > 0 && pro.get("bday" + (i + 1)) != null) {
								bday = (String) pro.get("bday" + (i + 1));
							}
							if (((String) pro.get("user" + (i + 1))).length() > 0 && pro.get("user" + (i + 1)) != null) {
								user = (String) pro.get("user" + (i + 1));
							}
							if (((String) pro.get("account" + (i + 1))).length() > 0 && pro.get("account" + (i + 1)) != null) {
								account = (String) pro.get("account" + (i + 1));
							}
							if (((String) pro.get("referral" + (i + 1))).length() > 0 && pro.get("referral" + (i + 1)) != null) {
								referral = (String) pro.get("referral" + (i + 1));
							}
							if (((String) pro.get("payReceipt" + (i + 1))).length() > 0 && pro.get("payReceipt" + (i + 1)) != null) {
								payReceipt = (String) pro.get("payReceipt" + (i + 1));
								
							} 
							if (((String) pro.get("debit" + (i + 1))).length() > 0 && pro.get("debit" + (i + 1)) != null) {
								debit = (String) pro.get("debit" + (i + 1));
							}
							if (((String) pro.get("loadDis" + (i + 1))).length() > 0 && pro.get("loadDis" + (i + 1)) != null) {
								loadDis = (String) pro.get("loadDis" + (i + 1));
								System.out.println("pay receipt is *************************"+payReceipt);
								payReceipt=loadDis;
							}
							
							if (((String) pro.get("proCommission" + (i + 1))).length() > 0 && pro.get("proCommission" + (i + 1)) != null) {
								proCommission = (String) pro.get("proCommission" + (i + 1));
							}
							if (((String) pro.get("sdate" + (i + 1))).length() > 0 && pro.get("sdate" + (i + 1)) != null) {
								sdate = (String) pro.get("sdate" + (i + 1));
							}
							if (((String) pro.get("edate" + (i + 1))).length() > 0 && pro.get("edate" + (i + 1)) != null) {
								edate = (String) pro.get("edate" + (i + 1));
							}
						}
						else // Office Scheme
						{	
							System.out.println(" its product off *************************"+pro.size());
							if (((String) pro.get("user" + (i + 1))).length() > 0 && pro.get("user" + (i + 1)) != null) {
								user = (String) pro.get("user" + (i + 1));
							}
							if (((String) pro.get("account" + (i + 1))).length() > 0 && pro.get("account" + (i + 1)) != null) {
								account = (String) pro.get("account" + (i + 1));
							}
							if (((String) pro.get("referral" + (i + 1))).length() > 0 && pro.get("referral" + (i + 1)) != null) {
								referral = (String) pro.get("referral" + (i + 1));
							}
							if (((String) pro.get("payReceipt" + (i + 1))).length() > 0 && pro.get("payReceipt" + (i + 1)) != null) {
								payReceipt = (String) pro.get("payReceipt" + (i + 1));
								System.out.println("pay receipt is *************************"+payReceipt);
								
							} 
							if (((String) pro.get("debit" + (i + 1))).length() > 0 && pro.get("debit" + (i + 1)) != null) {
								debit = (String) pro.get("debit" + (i + 1));
							}
							if (((String) pro.get("loadDis" + (i + 1))).length() > 0 && pro.get("loadDis" + (i + 1)) != null) {
								loadDis = (String) pro.get("loadDis" + (i + 1));
							}
							if (((String) pro.get("proCommission" + (i + 1))).length() > 0 && pro.get("proCommission" + (i + 1)) != null) {
								proCommission = (String) pro.get("proCommission" + (i + 1));
							}
							if (((String) pro.get("sdate" + (i + 1))).length() > 0 && pro.get("sdate" + (i + 1)) != null) {
								sdate = (String) pro.get("sdate" + (i + 1));
							}
							if (((String) pro.get("edate" + (i + 1))).length() > 0 && pro.get("edate" + (i + 1)) != null) {
								edate = (String) pro.get("edate" + (i + 1));
							}
						}
						
						String sqlboth = "";
						String sqlcount = "";
						String existingScheme[][] = new String[0][0];
						int count = 0;
						String newSchemeId = "";
						args = new String[2];
						args[0] = bcode;
						args[1] = office;//(String) pro.get("product" + (i + 1));
					
						if(args[1].equals("30"))
						{
							if(!schemeId.equals("") && !schemeId.equalsIgnoreCase("null") && schemeId.length() > 0)
								newSchemeId = " and scheme_id='"+schemeId+"'";
							sqlcount = "select count(*) from login_user_details where agency_code=? and product_id=?"+newSchemeId;
						}
						else
						{
							sqlcount = "select count(*) from login_user_details where agency_code=? and product_id=?";
						}

						try
						{
							res = runner.singleSelection(sqlcount,args);
							if(res.length() > 0 && !res.equalsIgnoreCase("null")) 
							{
								count = Integer.parseInt(res);
							} 
							else 
							{
								count = 0;
							}
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
						
						if (count <= 0) 
						{
							int userid = 1;
							System.out.println("inside insrtion of the user details");
							sqlboth = "insert into login_user_details(USER_ID,USER_NAME,LOGIN_ID,AGENCY_CODE,OA_CODE,PRODUCT_ID,COMPANY_ID,COMMISSION,INSURANCE_START_LIMIT,INSURANCE_END_LIMIT,DISCOUNT_OF_PREMIUM,RELATIVE_USER_ID,AMEND_ID,INCEPTION_DATE,EXPIRY_DATE,EFFECTIVE_DATE,ENTRY_DATE,REMARKS,STATUS,CUSTOMER_ID,MIN_PREMIUM_AMOUNT,BACK_DATE_ALLOWED,Pro_commission,pro_start_date,pro_expiry_date,LOADING_OF_PREMIUM,pay_receipt_status,FREIGHT_DEBIT_OPTION,PROVISION_FOR_PREMIUM,scheme_id) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					
							userid = autoGenCustId("USER_ID","login_user_details");

							try
							{
								String dumlogin = getLoginByAgency(userdetails[0][2],"");
								
								args = new String[30];
								
								if(office.equalsIgnoreCase("30"))
								{
									int OS = 0;
									String ss = "";
									/*if(officeHash.size() > 0)
									{
										ss = (String)officeHash.get("OSLength");
										if(ss.length() > 0)
											OS = Integer.parseInt(ss);
									}*/
									//for(int s=0;s<OS;s++)
									{
										/*if(((String) officeHash.get("scheme"+(s+1))).length() > 0 && officeHash.get("scheme"+(s+1)) != null) {
											schemeId = (String) officeHash.get("scheme"+(s+1));
										}*/
										schemeId = schemeId;
										if(((String) officeHash.get("commision"+schemeId)).length() > 0 && officeHash.get("commision"+schemeId)!=null) {
											commision = (String) officeHash.get("commision" +schemeId);
										}
										if(((String) officeHash.get("suminsured"+schemeId)).length() > 0 && officeHash.get("suminsured"+schemeId)!=null) {
											suminsured = (String) officeHash.get("suminsured"+schemeId);
										}
										if(((String) officeHash.get("premium"+schemeId)).length() > 0 && officeHash.get("premium"+schemeId)!= null) {
											premium = (String) officeHash.get("premium" +schemeId);
										}
										
										if(((String) officeHash.get("loading"+schemeId)).length() > 0 && officeHash.get("loading"+schemeId)!= null) {
											loading = (String) officeHash.get("loading" +schemeId);
										}
										if(((String) officeHash.get("discount"+schemeId)).length() > 0 && officeHash.get("discount"+schemeId)!= null) {
											discount = (String) officeHash.get("discount"+schemeId);
										}
										if(((String) officeHash.get("bday"+schemeId)).length() > 0 && officeHash.get("bday"+schemeId)!= null) {
											bday = (String) officeHash.get("bday"+schemeId);
										}
									}
									/*if(OS ==1)
									{
										args[0] = ""+userid;
									}
									else
									{*/
										userid = autoGenCustId("USER_ID","LOGIN_USER_DETAILS");
										args[0] = ""+userid;
									//}

									args[1] = userdetails[0][1];
									args[2] = dumlogin;
									args[3] = userdetails[0][2];
									args[4] = userdetails[0][2];
									args[5] = office;//(String)pro.get("product"+(i+1));
									args[6] = "2";
									args[7] = ""+Double.parseDouble(commision);
									args[8] = "1000";
									args[9] = ""+Long.parseLong(suminsured);
									args[10] = ""+Double.parseDouble(discount);
									args[11] = "0";
									args[12] = "1";
									args[13] = com.maan.common.util.OracleDateConversion.ConvertDate(""+ simpleFormatter.format(dd));
									args[14] = "";
									args[15] = "";
									args[16] = com.maan.common.util.OracleDateConversion.ConvertDate(""+ simpleFormatter.format(dd));
									args[17] = "";
									args[18] = "Y";
									args[19] = userdetails[0][0];
									args[20] = ""+Double.parseDouble(premium);
									args[21] = ""+Double.parseDouble(bday);
									args[22] = proCommission;
									args[23] = com.maan.common.util.OracleDateConversion.ConvertDate("" +sdate);
									args[24] = com.maan.common.util.OracleDateConversion.ConvertDate("" +edate);
									args[25] = ""+Double.parseDouble(loading);
									args[26] = payReceipt;
									args[27] = debit;
									args[28] = loadDis;
									args[29] = schemeId;
									
									runner.multipleUpdation(sqlboth,args);
								}
								else
								{
									args[0] = ""+userid;
									args[1] = userdetails[0][1];
									args[2] = dumlogin;
									args[3] = userdetails[0][2];
									args[4] = userdetails[0][2];
									args[5] = office;//(String)pro.get("product"+(i+1));
									args[6] = "2";
									args[7] = ""+Double.parseDouble(commision);
									args[8] = "1000";
									args[9] = ""+Long.parseLong(suminsured);
									args[10] = ""+Double.parseDouble(discount);
									args[11] = "0";
									args[12] = "1";
									args[13] = com.maan.common.util.OracleDateConversion.ConvertDate(""+ simpleFormatter.format(dd));
									args[14] = "";
									args[15] = "";
									args[16] = com.maan.common.util.OracleDateConversion.ConvertDate(""+ simpleFormatter.format(dd));
									args[17] = "";
									args[18] = "Y";
									args[19] = userdetails[0][0];
									args[20] = ""+Double.parseDouble(premium);
									args[21] = ""+Double.parseDouble(bday);
									args[22] = proCommission;
									args[23] = com.maan.common.util.OracleDateConversion.ConvertDate("" +sdate);
									args[24] = com.maan.common.util.OracleDateConversion.ConvertDate("" +edate);
									args[25] = ""+Double.parseDouble(loading);
									args[26] = payReceipt;
									args[27] = debit;
									args[28] = loadDis;
									args[29] = schemeId;
									
									runner.multipleUpdation(sqlboth,args);
								}
							}
							catch (Exception e) 
							{
								System.out.println("Exception in insertion of user details"+e.toString());
								e.printStackTrace();
							}
						} 
						else 
						{
							System.out.println("inside updationd of the user details");
							sqlboth = "update login_user_details set COMMISSION=?,INSURANCE_END_LIMIT=?,STATUS=?,DISCOUNT_OF_PREMIUM=?,MIN_PREMIUM_AMOUNT=? ,BACK_DATE_ALLOWED=?,LOADING_OF_PREMIUM=?,pay_receipt_status=?,FREIGHT_DEBIT_OPTION=?,PROVISION_FOR_PREMIUM=? where AGENCY_CODE=? and product_id=?";
							
							try
							{
								String syntax1 = "";
								if(office.equalsIgnoreCase("30"))
								{
										int OS = 0;
										String ss = "";
										/*if(officeHash.size() > 0)
										{
											ss = (String)officeHash.get("OSLength");
											if(ss.length() > 0)
												OS = Integer.parseInt(ss);
										}*/

										//for(int s=0;s<OS;s++)
										{
											/*if(((String) officeHash.get("scheme"+(s+1))).length() > 0 && officeHash.get("scheme"+(s+1)) != null) {
												schemeId = (String) officeHash.get("scheme"+(s+1));
											}*/
											schemeId = schemeId;
											syntax1 = " and scheme_id='"+schemeId+"'";
											sqlboth = sqlboth + syntax1;
											
											if(((String) officeHash.get("commision"+schemeId)).length() > 0 && officeHash.get("commision"+schemeId)!=null) {
												commision = (String) officeHash.get("commision" + schemeId);
											}
											if(((String) officeHash.get("suminsured"+schemeId)).length() > 0 && officeHash.get("suminsured"+schemeId)!=null) {
												suminsured = (String) officeHash.get("suminsured"+schemeId);
											}
											if(((String) officeHash.get("premium"+schemeId)).length() > 0 && officeHash.get("premium"+schemeId)!= null) {
												premium = (String) officeHash.get("premium" +schemeId);
											}
											
											if(((String) officeHash.get("loading"+schemeId)).length() > 0 && officeHash.get("loading"+schemeId)!= null) {
												loading = (String) officeHash.get("loading" + schemeId);
											}
											if(((String) officeHash.get("discount"+schemeId)).length() > 0 && officeHash.get("discount"+schemeId)!= null) {
												discount = (String) officeHash.get("discount"+schemeId);
											}
											if(((String) officeHash.get("bday"+schemeId)).length() > 0 && officeHash.get("bday"+schemeId)!= null) {
												bday = (String) officeHash.get("bday"+schemeId);
											}
										}
										args = new String[12];
										args[0] = ""+Double.parseDouble(commision);
										args[1] = ""+Long.parseLong(suminsured);
										args[2] = "Y";
										args[3] = ""+Double.parseDouble(discount);
										args[4] = ""+Double.parseDouble(premium);
										args[5] = ""+Double.parseDouble(bday);
										args[6] = ""+Double.parseDouble(loading);
										args[7] = payReceipt;
										args[8] = debit;
										args[9] = loadDis;
										args[10] = bcode;
										args[11] = office;//(String) pro.get("product" + (i + 1));
										runner.multipleUpdation(sqlboth,args);
								}
								else
								{
									args = new String[12];
									args[0] = ""+Double.parseDouble(commision);
									args[1] = ""+Long.parseLong(suminsured);
									args[2] = "Y";
									args[3] = ""+Double.parseDouble(discount);
									args[4] = ""+Double.parseDouble(premium);
									args[5] = ""+Double.parseDouble(bday);
									args[6] = ""+Double.parseDouble(loading);
									args[7] = payReceipt;
									
									logger.info("Product Id-->"+office);
									logger.info("Debit Option-->"+debit);
									args[8] = debit;
									args[9] = loadDis;
									args[10] = bcode;
									args[11] = office;//(String) pro.get("product" + (i + 1));
									runner.multipleUpdation(sqlboth,args);
								}
							} 
							catch (Exception e) 
							{
								System.out.println("Exception in updation"+ e.toString());
								e.printStackTrace();
							}
							/** Broker & user **/
							args = new String[2];
							String args1[] = new String[1];
							args[0] = bcode;
							args[1] = bcode;
							args1[0] = bcode;
							String Bqr = "select agency_code,product_id,status from login_user_details where agency_code=? ";
							String Uqr = "select agency_code,product_id,status from login_user_details where oa_code=? and agency_code!=?";
							String broDet[][] = new String[0][0];
							String usrDet[][] = new String[0][0];
							try
							{
								broDet = runner.multipleSelection(Bqr,args1);
								usrDet = runner.multipleSelection(Uqr,args);
								System.out.println("Bqr .length.."+broDet.length);
								System.out.println("Uqr .length.."+usrDet.length);
							}
							catch(Exception e){
								System.out.println("Broker & User Updation"+e.toString());
								e.printStackTrace();
							}
							for(int c=0;c<usrDet.length;c++)
							{
								for(int d=0;d<broDet.length;d++)
								{
									if( usrDet[c][1].equalsIgnoreCase(broDet[d][1]) && !usrDet[c][2].equalsIgnoreCase(broDet[d][2]) )
									{
										args = new String[3];
										args[0] = broDet[d][2];
										args[1] = usrDet[c][1];
										args[2] = usrDet[c][0];
										String qry = ""; 
										try{
											qry = "update login_user_details set status=? where product_id=? and agency_code=? ";
											runner.multipleUpdation(qry,args);
										} 
										catch (Exception e) {
											System.out.println("Exception in updation Broker & User"+ e.toString());
											e.printStackTrace();
										}		
									}
								}
							}
						/** Broker & User **/
						}

						System.out.println("BrokerCodes...sen is-->"+ bcode);
						if (!checkBrokerCode(bcode, "login_master", ""))
						{
							String[] qryvalues = new String[3];
							if (((String) pro.get("user" + (0 + 1))).length() > 0
									&& pro.get("user" + (0 + 1)) != null) {
								qryvalues[0] = "Y";
							} else {
								qryvalues[0] = "N";
							}

							if (((String) pro.get("account" + (0 + 1)))
									.length() > 0
									&& pro.get("account" + (0 + 1)) != null) {
								qryvalues[1] = "Y";
							} else {
								qryvalues[1] = "N";
							}
							if (((String) pro.get("referral" + (0 + 1)))
									.length() > 0
									&& pro.get("referral" + (0 + 1)) != null) {
								qryvalues[2] = "Y";
							} else {
								qryvalues[2] = "N";
							}
							

							String sql1 = "insert into login_master(LOGIN_ID,PASSWORD,USERTYPE,USERNAME,USERID,AGENCY_CODE,OA_CODE,ACCESSTYPE,RIGHTS,LPASS1,LPASS2,LPASS3,PASSDATE,COMPANY_ID,CREATED_BY,STATUS,USER_ID_CREATION,AC_EXECUTIVE_CREATION,REFERAL,CORE_LOGIN_ID) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

							try 
							{
								args = new String[20];

								args[0] = "NONE";
								args[1] = "";
								args[2] = "Broker";
								args[3] = userdetails[0][1];
								args[4] = "1";
								args[5] = bcode;
								args[6] = bcode;
								args[7] = "BOTH";
								args[8] = "";
								args[9] = "";
								args[10] = "";
								args[11] = "";
								args[12] = com.maan.common.util.OracleDateConversion.ConvertDate(""+simpleFormatter.format(dd));
								args[13] = "2";
								args[14] = loginPersonId;
								args[15] = "Y";
								args[16] = qryvalues[0];
								args[17] = qryvalues[1];
								args[18] = qryvalues[2];
								args[19] = loginPersonId;
								
								runner.multipleInsertion(sql1,args);
								
								process = "insert";
								System.out.println("BrokerCreationBean........"+sql1);
							} 
							catch (Exception e)
							{
								System.out.println("Exception in insertion of user details"+e.toString());
								e.printStackTrace();
							}
						} 
						else
						{
							String[] qryvalues = new String[3];
							if (((String) pro.get("user" + (0 + 1))).length() > 0
									&& pro.get("user" + (0 + 1)) != null) {
								qryvalues[0] = "Y";
							} else {
								qryvalues[0] = "N";
							}

							if (((String) pro.get("account" + (0 + 1)))
									.length() > 0
									&& pro.get("account" + (0 + 1)) != null) {
								qryvalues[1] = "Y";
							} else {
								qryvalues[1] = "N";
							}
							if (((String) pro.get("referral" + (0 + 1)))
									.length() > 0
									&& pro.get("referral" + (0 + 1)) != null) {
								qryvalues[2] = "Y";
							} else {
								qryvalues[2] = "N";
							}
							
							try 
							{
								args = new String[4];
								args[0] = qryvalues[0];
								args[1] = qryvalues[1];
								args[2] = qryvalues[2];
								args[3] = bcode;
								String qry = "update login_master set USER_ID_CREATION=?,AC_EXECUTIVE_CREATION=?,REFERAL=? where  AGENCY_CODE=?";
								process = "update";
							
								qry = runner.multipleUpdation(qry,args);

								System.out.println("execute query isss-->"+ qry);
								
							} 
							catch (Exception e)
							{
								System.out.println("Exception in updating the cretion previliges"+ e.toString());
								e.printStackTrace();
							}
						}
					}
				}
			}
			catch (Exception e)
			{
				System.out.println("exception in " + e.toString());
				e.printStackTrace();
			}
		}
		catch (Exception e) 
		{
			System.out.println("Exception in total updation" + e.toString());
			e.printStackTrace();
		} 
		return process;
	}

	public String[][] getBrokerDetails(String brokerid) 
	{
		String[][] brokerDetails = new String[0][0];
		String args[] = new String[1];
		String sql = ""; 
	
		try 
		{
			args[0] = brokerid;
			sql = "select pi.TITLE,pi.GENDER,pi.FIRST_NAME,pi.LAST_NAME,pi.NATIONALITY,pi.DOB,pi.TELEPHONE,pi.MOBILE,pi.FAX,EMAIL,pi.ADDRESS1,pi.ADDRESS2,pi.OCCUPATION,pi.EMIRATE,pi.COUNTRY,pi.POBOX,lm.username,lm.login_id,pi.entry_date,lm.status from personal_info pi,login_master lm where pi.login_id=lm.login_id and pi.login_id=? and pi.application_id='2'";
			
			brokerDetails = runner.multipleSelection(sql,args);
		}
		catch(Exception e) 
		{
			System.out.println("Exception in selecting broker details"	+ e.toString());
			e.printStackTrace();
		}
		return brokerDetails;
	}

	public String[][] getBrokerDetails123(String bcode,String adminBranch) 
	{
		String[][] brokerDetails = new String[0][0];
		String sql = ""; 
		try 
		{
			sql = "select pi.TITLE,pi.GENDER,pi.FIRST_NAME,pi.LAST_NAME,pi.NATIONALITY,pi.DOB,pi.TELEPHONE,pi.MOBILE,pi.FAX,pi.EMAIL,pi.ADDRESS1,pi.ADDRESS2,pi.OCCUPATION,pi.EMIRATE,pi.COUNTRY,pi.POBOX,lm.company_name,lm.agency_code,pi.entry_date,pi.status,lm.ADDRESS3,lm.city,lm.BRANCH_CODE,lm.MISSIPPI_ID,lm.APPROVED_PREPARED_BY,lm.rsa_broker_code,log.login_id,lm.AC_EXECUTIVE_ID,PI.CUST_NAME,PI.CUST_AR_NO from personal_info pi,broker_company_master lm,login_master log where log.agency_code=lm.agency_code and pi.agency_code=lm.agency_code and  pi.agency_code='"+bcode+"' and lm.branch_code in("+adminBranch+") and pi.application_id='2'";
			brokerDetails = runner.multipleSelection(sql);
		}
		catch (Exception e) 
		{
			System.out.println("Exception in selecting broker details"+ e.toString());
			e.printStackTrace();
		}
		return brokerDetails;
	}

	public String[][] getCommisionData123(String bcode) 
	{
		String[][] commisionDetails = new String[0][0];
		String sql = "" ;
		String args[] = new String[1];
		try 
		{
			args[0] = bcode;
			sql =  "select lud.PRODUCT_ID,lud.COMMISSION,lud.INSURANCE_END_LIMIT,lm.USER_ID_CREATION,lm.AC_EXECUTIVE_CREATION,lm.REFERAL,lud.status,nvl(lud.DISCOUNT_OF_PREMIUM,0),lud.MIN_PREMIUM_AMOUNT,lud.BACK_DATE_ALLOWED,nvl(PRO_COMMISSION,'0'),to_char(PRO_START_DATE,'dd-mm-yyyy'),to_char(PRO_EXPIRY_DATE,'dd-mm-yyyy'),nvl(lud.LOADING_OF_PREMIUM,0),nvl(pay_receipt_status,'N'),nvl(FREIGHT_DEBIT_OPTION,'Y'),nvl(PROVISION_FOR_PREMIUM,'N') from  login_master lm,login_user_details lud where lm.agency_code=lud.agency_code and  lud.agency_code=? order by lud.product_id";
		
			commisionDetails = runner.multipleSelection(sql,args);
		} 
		catch (Exception e) 
		{
			System.out.println("Exception in selecting commision for details"+ e.toString());
		}
		return commisionDetails;
	}

	//Modified Rajesh
	public boolean checkBrokerCode(String bcode, String table) 
	{
		boolean check = false;
		String sql = "";
		String res = "";
		String args[] = new String[1];
		try
		{
			args[0] = bcode;
			sql = "select count(*) from " + table + " where AGENCY_CODE=?";
			res = runner.singleSelection(sql,args);
			if(res.length() > 0 && !res.equalsIgnoreCase("null"))
			{
				if(Integer.parseInt(res) > 0)
				{
					check = true;
				}
				else
				{
					check = false;
				}
			}
		} 
		catch (Exception e)
		{
			System.out.println("exception in validating login id"+ e.toString());
			e.printStackTrace();
		}
		System.out.println("checkBrokerCode1>>>>" + check);
		return check;
	}

	public boolean checkBrokerCode(String bcode, String table, String dummy) 
	{
		boolean check = false;
		String sql = "";
		String res = "";
		String args[] = new String[1];
		try
		{
			args[0] = bcode;
			sql = "select count(*) from " + table + " where AGENCY_CODE=?";
			res = runner.singleSelection(sql,args);
			if(res.length() > 0 && !res.equalsIgnoreCase("null"))
			{
				if(Integer.parseInt(res) > 0)
				{
					check = true;
				}
				else
				{
					check = false;
				}
			}
		} 
		catch (Exception e)
		{
			System.out.println("exception in validating login id"+ e.toString());
			e.printStackTrace();
		}
		System.out.println("checkBrokerCode1>>>>checkBrokerCode1>>>>>" + check);
		return check;
	}

	public boolean checkCustomerId(String customerid, String dummay) 
	{
		boolean check = false;
		String sql = ""; 
		String args[] = new String[1];
		String res = "";
		try 
		{
			args[0] = customerid;
			sql = "select count(*) from personal_info where customer_id=?";
			res = runner.singleSelection(sql,args);
			if (res.length() > 0 && !res.equalsIgnoreCase("null")) 
			{
				if(Integer.parseInt(res) > 0) 
				{
					check = true;
				}
				else 
				{
					check = false;
				}
			}
		} 
		catch (Exception e) 
		{
			System.out.println("exception in validating login id"+ e.toString());
			e.printStackTrace();
		}

		System.out.println("sadasasdasda" + check);
		return check;
	}

	//Rajesh Modified
	public boolean checkCustomerId(String customerid) 
	{
		boolean check = false;
		String sql = "";
		String args[] = new String[1];
		String res = "";
		try
		{
			args[0] = customerid;
			sql =  "select count(*) from personal_info where customer_id=?";
			res = runner.singleSelection(sql,args);
			if (res.length() > 0 && !res.equalsIgnoreCase("null")) 
			{
				if(Integer.parseInt(res) > 0) 
				{
					check = true;
				}
				else 
				{
					check = false;
				}
			}
		} 
		catch (Exception e) 
		{
			System.out.println("exception in validating login id"+ e.toString());
			e.printStackTrace();
		}

		System.out.println("checkCustomerId....September..."+check);
		return check;
	}

	// Rajesh Modified For Common Login
	public boolean checkCommonBrokerId(String brokerId)
	{
		boolean check = false;
		String sql = ""; 
		String args[] = new String[1];
		String res = "";
		try 
		{
			args[0] = brokerId;
			sql = "select count(*) from LOGIN_MASTER_COMMON where login_id=?";
			res = runner.singleSelection(sql,args);
			if(res.length() > 0 && !res.equalsIgnoreCase("null")) 
			{
				if(Integer.parseInt(res) > 0) 
				{
					check = true;
				}
				else 
				{
					check = false;
				}
			}
		} 
		catch (Exception e) 
		{
			System.out.println("exception in validating login id"+ e.toString());
			e.printStackTrace();
		} 
		return check;
	}

	public boolean checkBrokerId(String brokerId) 
	{
		boolean check = false;
		String sql = ""; 
		String args[] = new String[1];
		String res = "";
		try 
		{
			args[0] = brokerId;
			sql = "select count(*) from login_master where login_id=?";
			res = runner.singleSelection(sql,args);

			if(res.length() > 0 && !res.equalsIgnoreCase("null")) 
			{
				if(Integer.parseInt(res) > 0) 
				{
					check = true;
				}
				else 
				{
					check = false;
				}
			}
		} 
		catch (Exception e) 
		{
			System.out.println("exception in validating login id"+ e.toString());
			e.printStackTrace();
		}
		return check;
	}

	public boolean checkCodeForAdmin(String code) 
	{
		boolean check = false;
		String sql = ""; 
		String res = "";
		String args[] = new String[2];
		try 
		{
			args[0] = code;
			args[1] = code;
			sql = "select count(*) from login_master where agency_code=? or login_id=?";
			res = runner.singleSelection(sql,args);
			if(res.length() > 0 && !res.equalsIgnoreCase("null"))
			{
				if (Integer.parseInt(res) > 0) 
				{
					check = true;
				}
				else 
				{
					check = false;
				}
			}
		}
		catch (Exception e) 
		{
			System.out.println("exception in validating login id"+ e.toString());
			e.printStackTrace();
		}
		return check;
	}

	public boolean checkCode(String code)
	{
		boolean check = false;
		String sql = ""; 
		String res = "";
		String args[] = new String[1];
		try
		{
			args[0] = code;
			sql = "select count(*) from personal_info where agency_code=?";
			res = runner.singleSelection(sql,args);
			if(res.length() > 0 && !res.equalsIgnoreCase("null"))
			{
				if (Integer.parseInt(res) > 0) 
				{
					check = true;
				}
				else 
				{
					check = false;
				}
			}
		} 
		catch (Exception e) 
		{
			System.out.println("exception in validating login id"+ e.toString());
			e.printStackTrace();
		}
		return check;
	}
	public boolean changePassword(String brokerId, String password) 
	{
		passwordEnc pass = new passwordEnc();
		String encpass = pass.crypt(password.substring(0, 3), password);
		boolean check = false;
		String sql = "update login_master set password=? where agency_code=?";
	
		try
		{
			String args[] = new String[2];
			args[0] = encpass;
			args[1] = brokerId;
			String temp = runner.multipleUpdation(sql,args);
			if(temp.equalsIgnoreCase("UPDATED")) 
			{
				check = true;
			}
			else 
			{
				check = false;
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return check;
	}
	public String[][] getProducts(String branch) 
	{
		String[][] products = new String[0][0];
		String sql = ""; 
		try 
		{
			sql = "select PRODUCT_ID,PRODUCT_NAME,COMPANY_ID from product_master where status='Y' and COMPANY_ID not in (2) and branch_code in("+branch+") order by product_id";
			products = runner.multipleSelection(sql);
		} 
		catch(Exception e) 
		{
			System.out.println("Exception in getting products" + e.toString());
			e.printStackTrace();
		}
		return products;
	}

	public String[][] getMarineProducts(String adminBranch) 
	{
		String[][] products = new String[0][0];
		String sql = "";
		try
		{
			sql = "select PRODUCT_ID,PRODUCT_NAME,COMPANY_ID from product_master where status='Y' and branch_code in("+adminBranch+") and PRODUCT_ID not in(select PRODUCT_ID from home_product_master where status='Y')";
			products = runner.multipleSelection(sql);
		} 
		catch (Exception e) 
		{
			System.out.println("Exception in getting products" + e.toString());
			e.printStackTrace();
		}
		return products;
	}

	public String[][] getHomeProducts() 
	{
		String[][] products = new String[0][0];
		String sql = "";
		try 
		{
			sql = "select PRODUCT_ID,PRODUCT_NAME,COMPANY_ID from product_master where status='Y' and PRODUCT_ID in(select PRODUCT_ID from home_product_master where status='Y')";
			products = runner.multipleSelection(sql);
		} 
		catch (Exception e) 
		{
			System.out.println("Exception in getting products" + e.toString());
			e.printStackTrace();
		}
		return products;
	}

	public String[][] getEmirates12(String bid) 
	{
		String[][] countries = new String[0][0];
		String sql = "";
		String args[] = new String[1];
		try 
		{
			args[0] = bid;
			sql = "select city_id,city_name  from city_master where country_id='1' and rsacode=? order by city_name";
			countries = runner.multipleSelection(sql,args);
		}
		catch (Exception e) 
		{
			System.out.println("Exception in getting products" + e.toString());
			e.printStackTrace();
		}
		return countries;
	}

	public String[][] getEmirates() 
	{
		String[][] countries = new String[0][0];
		String sql = ""; 
		try	
		{
			sql = "select city_id,city_name  from city_master where country_id='1' order by city_name";
			countries = runner.multipleSelection(sql);
		} 
		catch (Exception e) 
		{
			System.out.println("Exception in getting products" + e.toString());
			e.printStackTrace();
		}
		return countries;
	}

	public String[][] getCountries()
	{
		String[][] countries = new String[0][0];
		String sql = ""; 
		try {
			sql = "select COUNTRY_ID,COUNTRY_NAME  from COUNTRY_MASTER where amend_id||country_id in( select max(amend_id)||country_id from country_master group by country_id)order by COUNTRY_NAME";
			countries = runner.multipleSelection(sql);
		} 
		catch(Exception e) 
		{
			System.out.println("Exception in getting products" + e.toString());
			e.printStackTrace();
		}
		return countries;
	}

	public String[][] getNtionalities() 
	{
		String[][] countries = new String[0][0];
		String sql = ""; 
		try 
		{
//			sql = "select COUNTRY_ID,NATIONALITY_NAME from COUNTRY_MASTER where amend_id||country_id in( select max(amend_id)||country_id from country_master group by country_id) order by NATIONALITY_NAME ";
			sql = "select COUNTRY_ID,NATIONALITY_NAME from COUNTRY_MASTER where amend_id||country_id in( select max(amend_id)||country_id from country_master group by country_id) and NATIONALITY_NAME is not null order by NATIONALITY_NAME";
			countries = runner.multipleSelection(sql);
		} 
		catch (Exception e) 
		{
			System.out.println("Exception in getting products" + e.toString());
			e.printStackTrace();
		}
		return countries;
	}

	public String[][] getTitles(String branch)
	{
		String[][] countries = new String[0][0];
		String sql = "";
		String args[] = new String[1];

		try
		{
			args[0] = branch;
			sql = "select TITLE_ID,TITLE_NAME from TITLE_MASTER where branch_code=? and status='Y' order by TITLE_NAME";
			countries = runner.multipleSelection(sql,args);
		} 
		catch(Exception e) 
		{
			System.out.println("Exception in getting products" + e.toString());
			e.printStackTrace();
		}
		return countries;
	}

	public String[][] getBrokersHasCover()
	{
		String[][] getBrokerName = new String[0][0];
		String[][] countries = new String[0][0];
		String sql = ""; 		
		try 
		{
			sql = "select distinct a.company_name,b.login_id from broker_company_master a,login_master b,open_cover_master c where b.agency_code=a.agency_code and c.broker_id=b.login_id and c.proposal_no in (select proposal_no from open_cover_position_master where open_cover_no is not null and status='P') order by a.company_name";
			getBrokerName = runner.multipleSelection(sql);
		}
		catch (Exception e) 
		{
			System.out.println("Exception in getting products" + e.toString());
			e.printStackTrace();
		}
		return getBrokerName;
	}

	public String[][] getportfolioDetails(String bid) 
	{
		String[][] result = new String[0][0];
		String sqlQuery = "";
		String args[] = new String[1];
		try 
		{
			args[0] = bid;
			sqlQuery = "select a.proposal_no,nvl(a.open_cover_no,'0'),nvl(to_char(b.policy_start_date,'DD-MON-YYYY'),' '),nvl(to_char(b.policy_end_date,'DD-MON-YYYY'),' '),nvl(first_name,nvl(company_name,' ')) from open_cover_position_master a, open_cover_master b,personal_info c where a.status='P' and a.proposal_no = b.proposal_no and b.amend_id = (select max(amend_id) from open_cover_master b where b.proposal_no = a.proposal_no) and a.proposal_no in (select proposal_no from open_cover_master where broker_id=?) and c.customer_id =b.customer_id order by a.proposal_no";
			result = runner.multipleSelection(sqlQuery,args);
		} 
		catch (Exception e1) 
		{
			System.out.println("Error in selection" + e1);
			e1.printStackTrace();
		}
		return result;
	}

	public String[][] getLCs(String openID) 
	{
		String[][] result = new String[0][0]; ;
		String sql = "";
		String args[] = new String[1];
		try 
		{
			args[0] = openID;
			sql ="select OPEN_COVER_NO,BANK_ID,LC_NUMBER,LC_DATE,LC_AMOUNT,ENTRY_DATE,EXPIRY_DATE,EFFECTIVE_DATE,AMEND_ID,REMARKS,STATUS,LC_ID from OPEN_COVER_LC_MASTER where OPEN_COVER_NO=?";
			result = runner.multipleSelection(sql,args);
		}
		catch(Exception e1) 
		{
			System.out.println("Error in selection" + e1);
			e1.printStackTrace();
		}
		return result;
	}

	public String[][] getBrokers() 
	{
		String[][] brokers = new String[0][0];
		String sql = ""; 
		try 
		{
			sql = "select bcm.CUSTOMER_ID,bcm.CONTACT_PERSON,bcm.COMPANY_NAME,pi.AGENCY_CODE from BROKER_COMPANY_MASTER bcm,personal_info pi where bcm.agency_code=pi.agency_code  and bcm.status='Y' and pi.status=bcm.status and APPLICATION_ID='2' order by lower(bcm.COMPANY_NAME)";
			brokers = runner.multipleSelection(sql);
		} 
		catch(Exception e) 
		{
			System.out.println("Exception in getting products" + e.toString());
			e.printStackTrace();
		} 
		return brokers;
	}
			
	public String[][] getBrokersForCustomer(String branchCode) 
	{
		String[][] brokers = new String[0][0];
		String sql = ""; 
		try 
		{
			sql = "select bcm.CUSTOMER_ID,bcm.CONTACT_PERSON,bcm.COMPANY_NAME,pi.oa_code,pi.AGENCY_CODE from BROKER_COMPANY_MASTER bcm,personal_info pi where bcm.agency_code=pi.agency_code  and bcm.status='Y' and pi.status=bcm.status and APPLICATION_ID='2' and pi.login_id in(select login_id from login_master where oa_code in(select agency_code from broker_company_master where branch_code in("+branchCode+"))) order by lower(bcm.COMPANY_NAME)";

			brokers = runner.multipleSelection(sql);
		}
		catch (Exception e) 
		{
			System.out.println("Exception in getting products" + e.toString());
			e.printStackTrace();
		} 
		return brokers;
	}

	public String[][] getBrokersCustomers(String brokerCode) 
	{
		String[][] brokers = new String[0][0];
		String sql = ""; 
		String args[] = new String[1];
		try 
		{
			args[0] = brokerCode;
			sql = "select customer_id,nvl(first_name,COMPANY_NAME) from personal_info where login_id in (select login_id from login_master where oa_code=?) and application_id='1' order by customer_id desc";
			brokers = runner.multipleSelection(sql,args);
		}
		catch(Exception e) 
		{
			System.out.println("Exception in getting products" + e.toString());
			e.printStackTrace();
		} 
		return brokers;
	}

	public String[][] getRSAAdmins(String branch,String type)
	{
		String[][] brokers = new String[0][0];
		String sql = ""; 
		String args[] = new String[1];
		try 
		{
			args[0] = branch;
			if(type.equalsIgnoreCase("RSAIssuer"))
			{
//				sql = "select initcap(usertype),login_id,username,agency_code from login_master where usertype in('RSAIssuer') and branch_code=? order by username";
				//sql = "SELECT INITCAP(A.USERTYPE),A.LOGIN_ID,A.USERNAME,A.AGENCY_CODE FROM LOGIN_MASTER A, BROKER_COMPANY_MASTER B WHERE A.USERTYPE='RSAIssuer' AND B.BRANCH_CODE=? AND B.AGENCY_CODE=A.OA_CODE";
				sql = "SELECT DISTINCT INITCAP(A.USERTYPE),A.LOGIN_ID,A.USERNAME,A.AGENCY_CODE FROM LOGIN_MASTER A, BROKER_COMPANY_MASTER B WHERE A.USERTYPE='RSAIssuer' AND B.BRANCH_CODE=? ";
				brokers = runner.multipleSelection(sql,args);
			}
			else
			{
				String adminType = "";
				if(type.equalsIgnoreCase("UWAdmin")||type.equalsIgnoreCase("RSAUSER")||type.equalsIgnoreCase("admin"))
					adminType = "'UWAdmin','RSAUSER','admin','ClaimUser','Admin'";
				else
					adminType = "'HomeRSAUser','HomeAdmin'";
//				sql = "select initcap(usertype),login_id,username,agency_code from login_master where usertype in("+adminType+") and branch_code=? order by username";
				//sql = "SELECT INITCAP(A.USERTYPE),A.LOGIN_ID,A.USERNAME,A.AGENCY_CODE FROM LOGIN_MASTER A, BROKER_COMPANY_MASTER B WHERE A.USERTYPE in ("+adminType+") AND B.BRANCH_CODE=? AND B.AGENCY_CODE=A.OA_CODE";
				sql = "SELECT DISTINCT INITCAP(A.USERTYPE),A.LOGIN_ID,A.USERNAME,A.AGENCY_CODE FROM LOGIN_MASTER A, BROKER_COMPANY_MASTER B WHERE A.USERTYPE in ("+adminType+") AND B.BRANCH_CODE=? ";
				brokers = runner.multipleSelection(sql,args);
			}
	       // System.out.println("SQL ::>"+sql);
		} 
		catch (Exception e) 
		{
			System.out.println("Exception in getting products" + e.toString());
			e.printStackTrace();
		}
		return brokers;
	}

	public String[][] getRSAAdmins(String login_id) 
	{
		String sql = "";
		String[][] brokers = new String[0][0];
		if (!login_id.equalsIgnoreCase("ALL")) 
		{
			sql = "select usertype,login_id,username,agency_code from login_master where usertype in ('UWAdmin','RSAUSER') ";
			//sql = "select usertype,login_id,username,agency_code from login_master where usertype in ('UWAdmin','RSAUSER') and agency_code='"+ login_id + "'";
		}
		else if (disStatus.length() > 0 && !disStatus.equals("A") && !disStatus.equals("0")) 
		{
			sql = "select usertype,login_id,username,agency_code from login_master where usertype in ('UWAdmin','RSAUSER') and status='"
					+disStatus+"'";
		}
		else 
		{
			sql = "select usertype,login_id,username,agency_code from login_master where usertype in ('UWAdmin','RSAUSER')";
		}
		try 
		{
			brokers = runner.multipleSelection(sql);
		} 
		catch (Exception e) 
		{
			System.out.println("Exception in getting products" + e.toString());
			e.printStackTrace();
		}
		return brokers;
	}

	public String[][] getcheckedRsaStatus(String loginId, String status) 
	{
		String sql = "";
		String[][] brokers = new String[0][0];
		String args[] = new String[0];
	
		if (!loginId.equalsIgnoreCase("ALL")) 
		{
			args = new String[2];
			args[0] = loginId;
			args[1] = status;
			sql = "select usertype,login_id,username,agency_code from login_master where usertype in ('UWAdmin','RSAUSER') and agency_code=?and status=?";
		} 
		else 
		{
			sql = "select usertype,login_id,username,agency_code from login_master where usertype in ('UWAdmin','RSAUSER') and status=?";
		}
		try 
		{
			brokers = runner.multipleSelection(sql,args);
		} 
		catch (Exception e) 
		{
			System.out.println("Exception in getting products" + e.toString());
			e.printStackTrace();
		}
		return brokers;
	}

	public String[][] getBranches1(String branch)
	{
		String[][] brokers = new String[0][0];
		String sql = ""; 
		String args[] = new String[1];
		try 
		{
			args[0] = branch;
			sql = "select BRANCH_CODE,BRANCH_NAME from BRANCH_MASTER where status='Y' and branch_code=?";
			brokers = runner.multipleSelection(sql,args);
		} 
		catch (Exception e) 
		{
			System.out.println("Exception in getting products" + e.toString());
			e.printStackTrace();
		}
		return brokers;
	}

	public String[][] getBranches(String adminBraCode)
	{
		String[][] brokers = new String[0][0];
		String sql = ""; 
		try
		{
			sql = "select BRANCH_CODE,BRANCH_NAME from BRANCH_MASTER where status='Y' and BRANCH_CODE in("+adminBraCode+")";
			brokers = runner.multipleSelection(sql);
		} 
		catch (Exception e) 
		{
			System.out.println("Exception in getting products" + e.toString());
			e.printStackTrace();
		}
		return brokers;
	}

	public String[][] getBrokers(String login_id)
	{
		String sql = "";
		String[][] brokers = new String[0][0];
		String args[] = new String[1];
		try
		{
			if (!login_id.equalsIgnoreCase("ALL")) 
			{
				args[0] = login_id;
				sql = "select bcm.CUSTOMER_ID,pi.APPLICATION_ID,pi.FIRST_NAME,pi.AGENCY_CODE,bcm.CONTACT_PERSON,bcm.COMPANY_NAME,bcm.AGENCY_CODE from BROKER_COMPANY_MASTER bcm,personal_info pi,login_master lm where bcm.agency_code=pi.agency_code and lm.agency_code=pi.agency_code and bcm.agency_code=? and pi.oa_code is not null and pi.APPLICATION_ID='2' order by lower(bcm.COMPANY_NAME)";
				brokers = runner.multipleSelection(sql,args);
			} 
			else if (disStatus.length() > 0 && !disStatus.equals("A") && !disStatus.equals("0")) 
			{
				args[0] = disStatus;
				sql = "select bcm.CUSTOMER_ID,pi.APPLICATION_ID,pi.FIRST_NAME,pi.AGENCY_CODE,bcm.CONTACT_PERSON,bcm.COMPANY_NAME,bcm.AGENCY_CODE from BROKER_COMPANY_MASTER bcm,personal_info pi,login_master lm where bcm.agency_code=pi.agency_code and lm.agency_code=pi.agency_code and lm.status=? and pi.oa_code is not null and pi.APPLICATION_ID='2' order by lower(bcm.COMPANY_NAME)";
				brokers = runner.multipleSelection(sql,args);
			}
			else 
			{
				sql = "select bcm.CUSTOMER_ID,pi.APPLICATION_ID,pi.FIRST_NAME,pi.AGENCY_CODE,bcm.CONTACT_PERSON,bcm.COMPANY_NAME,bcm.AGENCY_CODE from BROKER_COMPANY_MASTER bcm,personal_info pi where bcm.agency_code=pi.agency_code and APPLICATION_ID='2' order by lower(bcm.COMPANY_NAME)";
				brokers = runner.multipleSelection(sql);
			}
		}
		catch(Exception e)
		{
			System.out.println("....Exception is "+ e);
			e.printStackTrace();
		}
		return brokers;
	}

	public String[][] getUsers(String loginId)
	{
		String[][] brokers = new String[0][0];
		String sql = "";
		
		if (!loginId.equalsIgnoreCase("ALL")) 
		{
			sql = "select CUSTOMER_ID,APPLICATION_ID,FIRST_NAME,AGENCY_CODE from personal_info where APPLICATION_ID='3' and oa_code='"					+loginId+"' and oa_code is not null";
		} 
		else if (disStatus.length() > 0 && !disStatus.equals("")) 
		{
			sql = "select CUSTOMER_ID,APPLICATION_ID,FIRST_NAME,agency_code from personal_info where APPLICATION_ID='3' and oa_code in ("+brokerIds+")";
		}
		else 
		{
			sql = "select CUSTOMER_ID,APPLICATION_ID,FIRST_NAME,agency_code from personal_info where APPLICATION_ID='3' and oa_code in  ("+brokerIds+")";
		}
		
		try
		{
			brokers = runner.multipleSelection(sql);
		}
		catch (Exception e) 
		{
			System.out.println("Exception in getting products" + e.toString());
			e.printStackTrace();
		}
		return brokers;
	}

	public String[][] getAccountEx(String loginId)
	{
		String sql = "";
		String[][] brokers = new String[0][0];
		if (!loginId.equalsIgnoreCase("ALL")) 
		{
			sql = "select CUSTOMER_ID,APPLICATION_ID,FIRST_NAME,agency_code from personal_info where APPLICATION_ID='4'   and oa_code='"
					+ loginId + "' and oa_code is not null";
		} 
		else if (disStatus.length() > 0 && !disStatus.equals("")) 
		{
			sql = "select CUSTOMER_ID,APPLICATION_ID,FIRST_NAME,agency_code from personal_info where APPLICATION_ID='4' and  oa_code in ("+ brokerIds + ")";
		} 
		else
		{
			sql = "select CUSTOMER_ID,APPLICATION_ID,FIRST_NAME,agency_code from personal_info where APPLICATION_ID='4' and  oa_code in ("+ brokerIds + ")";
		}
		try 
		{
			brokers = runner.multipleSelection(sql);
		} 
		catch (Exception e) 
		{
			System.out.println("Exception in getting products" + e.toString());
			e.printStackTrace();
		}
		return brokers;
	}

	public String[][] getcheckedStatus(String loginId, String appId,String status) 
	{
		String sql = "";
		String[][] brokers = new String[0][0];
		String args[] = new String[0];
		try 
		{
			if (!loginId.equalsIgnoreCase("ALL")) 
			{
				args = new String[3];
				args[0] = appId;
				args[1] = status;
				args[2] = loginId;
				sql = "select CUSTOMER_ID,APPLICATION_ID,FIRST_NAME,agency_code from personal_info where APPLICATION_ID=? and status=? and oa_code=? and agency_code is not null";
				brokers = runner.multipleSelection(sql,args);
			} 
			else 
			{
				args = new String[2];
				args[0] = appId;
				args[1] = status;
				sql = "select CUSTOMER_ID,APPLICATION_ID,FIRST_NAME,agency_code from personal_info where APPLICATION_ID=? and status=? and agency_code is not null";
				brokers = runner.multipleSelection(sql,args);
			}
		} 
		catch (Exception e) 
		{
			System.out.println("Exception in getting products" + e.toString());
			e.printStackTrace();
		}
		return brokers;
	}

	public String getMailId(String brokerId)
	{
		String email = "";
		String sql = ""; 
		String args[] = new String[1];
		try
		{
			args[0] = getAgencyCode(brokerId);
			sql = "select EMAIL from personal_info where  agency_code=? and APPLICATION_ID='2'";
			email = runner.singleSelection(sql,args);
			
		}
		catch (Exception e) 
		{
			System.out.println("Exception in getting mail id" + e.toString());
			e.printStackTrace();
		} 
		return email;
	}

	// Country Work
	public String[][] getCurrencies() 
	{
		String[][] currency = new String[0][0];
		String sql = ""; 
		String args[] = new String[2];

		try 
		{
			args[0] = adminCountryId;
			args[1] = adminCountryId;
			sql = "select cm.currency_id,cm.currency_name,cm.amend_id,cm.sno__,cm.rsacode from currency_master cm where  cm.amend_id||cm.currency_id  in (select max(amend_id)||currency_id from currency_master where effective_date<=SYSDATE+10/24 and  country_id=? group by currency_id) and cm.effective_date<=SYSDATE+10/24 and country_id=? group by cm.currency_id,cm.currency_name,cm.amend_id,cm.sno__ ,cm.rsacode order by cm.currency_name";
	
			currency = runner.multipleSelection(sql,args);

		} 
		catch (Exception e) 
		{
			System.out.println("Exception in getCurrencies in BrokeCreation Bean" + e.toString());
			e.printStackTrace();
		}
		return currency;
	}

	public String validateExchange() 
	{
		String[][] DateAndTime = new String[0][0];
		java.util.Date systime = new java.util.Date();
		com.maan.common.util.dataCollection data = new com.maan.common.util.dataCollection();
		String error = "";
		String values = null;

		if ("0".equalsIgnoreCase(currencyType))
			error = error + "<br>*" + runner.getErrormsg("47");

		values = data.validString(exchangeRate, true);
		if ("needed".equalsIgnoreCase(values))
			error = error + "<br>*" + runner.getErrormsg("48");
		else if ("Invalid".equalsIgnoreCase(values))
			error = error + "<br>*" + runner.getErrormsg("50");

		values = data.checkPickerDate(effectDate);
		if ("Invalid".equalsIgnoreCase(values))
			error = error + "<br>*" + runner.getErrormsg("49");
		
//		 Exchange master Effective Date Validation
		String currentDate[][] = new String[0][0];
		currentDate = getTodaysDate(branch);
		try
		{
			long diff = 0;
			if(currentDate.length > 0 )
			{
				DateFunction dbr = new DateFunction();
				diff = dbr.getDayDifference(dbr.getCalendar(currentDate[0][0]+"-"+currentDate[0][1]+"-"+currentDate[0][2]),dbr.getCalendar(effectDate));
			}
			System.out.println("Exchange master Effective Date Validation " + diff);
			if(diff > 0)
				error = error + "<br>* Effective date should not be greater than today's date";
		}
		catch(Exception e)
		{
			System.out.println("Exception for validating exchange master effective date " + e);
			e.printStackTrace();
		}
		
		String args[] = new String[2];
		String sql1 = ""; 
		
		try
		{
			args[0] = currencyType;
			args[1] = currencyType;
			sql1 = "select to_char(effective_Date-10/24,'DD-MM-YYYY')as date1,to_char(effective_Date-10/24,'HH24')as HOUR1,to_char(effective_Date,'MI')as MIN1,to_char(effective_Date,'SS')as SECOND1 from exchange_master where amend_id=(select max(amend_id) from exchange_master where currency_id=?) and currency_id=?";
			
			DateAndTime = runner.multipleSelection(sql1,args);
		}
		catch (Exception e) 
		{
			System.out.println("Exception in getting date and time"	+ e.toString());
			e.printStackTrace();
		}

		try {

			if (DateAndTime.length > 0 && DateAndTime != null) {
				long diff = 0;

				try 
				{
					DateFunction dbr = new DateFunction();
					diff = dbr.getDayDifference(dbr	.getCalendar(DateAndTime[0][0]), dbr.getCalendar(effectDate));
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
				
				if (diff < 0) 
				{
					error = error + "<br>* Enter valid Date";
				}
				else if (diff == 0) 
				{
					if (systime.getHours() < Integer.parseInt(DateAndTime[0][1])) 
					{
						error = error + "<br>* Check The System Time (H)";
					} 
					else if (systime.getHours() == Integer.parseInt(DateAndTime[0][1]))
					{
						if (systime.getMinutes() < Integer.parseInt(DateAndTime[0][2])) 
						{
							error = error + "<br>* Check The System Time(m)";
						}
						else if (systime.getMinutes() == Integer.parseInt(DateAndTime[0][2])) 
						{
							if (systime.getSeconds() < Integer.parseInt(DateAndTime[0][3])) 
							{
								error = error+ "<br>* Check The System Time<S>";
							}
						}
					}
				}
			}
		}

		catch (Exception e)
		{
			System.out.println("exception in   " + e.toString());
		}

		return error;
	}

	// Country Work Jun 04
	public String insertExchangeData() 
	{
		String args[] = new String[0];
		String res = "";
		String updation = "";
		System.out.println("Royal test Currency status..."+status);
		String sql = "";
		java.util.Date dd = new java.util.Date();
		java.text.SimpleDateFormat simpleFormatter = new java.text.SimpleDateFormat("dd-MM-yyyy");
		simpleFormatter.setTimeZone(new java.util.SimpleTimeZone(14400000,"GMT"));
		//System.out.println("sys date with time date is"+ com.maan.common.util.OracleDateConversion.ConvertDate("Date Format"+ simpleFormatter.format(dd)));
		// java.util.Date systime=new java.util.Date();
		String eDate = com.maan.common.util.OracleDateConversion.ConvertDate(effectDate)+ " "+ dd.getHours()+ ":"+ dd.getMinutes()		+ ":"+ dd.getSeconds();
		int amendId = 0;
		String time = dd.getHours() + ":" + dd.getMinutes() + ":"+ dd.getSeconds();
		String sysdate_is = dd.getDate() + "-" + dd.getMonth() + "-"+ dd.getYear();
		String pdate = sysdate_is + " " + time;
		int norecords = 0;
		int exid = 0;
		int sno = 0;
		int displayOrder=0;

		
		try 
		{
			args = new String[2];
			args[0] = currencyType;
			args[1] = adminCountryId;
			sql = "select EXCHANGE_ID from exchange_master where CURRENCY_id=? and COUNTRY_ID=?";
			res = runner.singleSelection(sql,args);
			if(res.length() > 0 && !res.equalsIgnoreCase("null")) 
			{
				exid = Integer.parseInt(res);
			}
			else 
			{
				exid = autoGenCustId("EXCHANGE_ID", "exchange_master") ;
			}
		}
		catch (Exception e) 
		{
			System.out.println("Exception in getting amendId" + e.toString());
			e.printStackTrace();
		}

		
		try 
		{
			args = new String[2];
			sql = "select max(amend_id+1) from exchange_master where exchange_id=? and COUNTRY_ID=? ";
			args[0] = ""+exid;
			args[1] = ""+adminCountryId;
			res = runner.singleSelection(sql,args);
			if(res.length() > 0 && !res.equalsIgnoreCase("null")) 
			{
				amendId = Integer.parseInt(res);
			}
			else 
			{
				amendId = 0;
			}
		} 
		catch (Exception e) 
		{
			System.out.println("Exception in getting amendId" + e.toString());
			e.printStackTrace();
		}

		/** Getting Display Order **/
		try 
		{
			args = new String[4];
			args[0] = ""+exid;
			args[1] = ""+adminCountryId;
			args[2] = ""+exid;
			args[3] = ""+adminCountryId;
			sql = "select display_order from exchange_master where exchange_id=? and COUNTRY_ID=? and amend_id =(select max(amend_id) from exchange_master where exchange_id=? and COUNTRY_ID=?) ";
			res = runner.singleSelection(sql,args);
			if(res.length() > 0 && !res.equalsIgnoreCase("null"))
					displayOrder = Integer.parseInt(res);
		} 
		catch (Exception e) 
		{
			System.out.println("Exception in getting amendId" + e.toString());
			e.printStackTrace();
		}

		/** Getting Display Order **/

		sno = autoGenCustId("SNO__", "exchange_master");
		remarkText = remarkText.replaceAll("'", "''");
		try
		{
			runner.multipleUpdation("UPDATE EXCHANGE_MASTER SET EXPIRY_DATE=TO_DATE(?,'DD/MON/YYYY HH24:MI:SS')-1 WHERE EXCHANGE_ID=? AND COUNTRY_ID=? AND AMEND_ID =(SELECT MAX(AMEND_ID) FROM EXCHANGE_MASTER WHERE EXCHANGE_ID=? AND COUNTRY_ID=?)", new String[]{eDate,exid+"", adminCountryId, exid+"", adminCountryId});
			//sql = "insert into exchange_master(SNO__,EXCHANGE_ID,EXCHANGE_RATE,CURRENCY_ID,AMEND_ID,INCEPTION_DATE,EXPIRY_DATE,EFFECTIVE_DATE,ENTRY_DATE,STATUS,REMARKS,RSACODE,COUNTRY_ID,DISPLAY_ORDER) values(?,?,?,?,?,(select SYSDATE+10/24 from dual),?,TO_DATE(?,'dd/mon/yyyy HH24:MI:SS')+10/24,(select SYSDATE+10/24 from dual),?,?,?,?,?)";
			sql = "insert into exchange_master(SNO__,EXCHANGE_ID,EXCHANGE_RATE,CURRENCY_ID,AMEND_ID,INCEPTION_DATE,EXPIRY_DATE,EFFECTIVE_DATE,ENTRY_DATE,STATUS,REMARKS,RSACODE,COUNTRY_ID,DISPLAY_ORDER) values(?,?,?,?,?,(select SYSDATE from dual),TO_DATE(?,'dd/mon/yyyy HH24:MI:SS')+365,TO_DATE(?,'dd/mon/yyyy HH24:MI:SS'),(select SYSDATE from dual),?,?,?,?,?)";
			args = new String[12];
			args[0] = ""+sno;
			args[1] = ""+exid;
			args[2] = exchangeRate;
			args[3] = currencyType;
			args[4] = ""+amendId;
			args[5] = eDate;
			args[6] = eDate;
			args[7] = status;
			args[8] = remarkText;
			args[9] = rsaCode;
			args[10] = ""+adminCountryId;
			args[11] = ""+displayOrder;
			runner.multipleInsertion(sql,args);
			updation = "YES";
		}
		catch (Exception e) 
		{
			System.out.println("Exception in inserting datas in exchange master"+ e.toString());
			e.printStackTrace();
		}
		return updation;
	}

	public String insertCurrencyData() 
	{
		String args[] = new String[0];
		String res = "";
		String updation = "";
		String sql = "";
		java.util.Date dd = new java.util.Date();
		java.text.SimpleDateFormat simpleFormatter = new java.text.SimpleDateFormat("dd-MM-yyyy");
		simpleFormatter.setTimeZone(new java.util.SimpleTimeZone(14400000,"GMT"));
		System.out.println("sys date with time date is"+ com.maan.common.util.OracleDateConversion.ConvertDate(""+ simpleFormatter.format(dd)));
		String eDate = com.maan.common.util.OracleDateConversion.ConvertDate(effectDate)+" "+dd.getHours()+":"+dd.getMinutes()+":"+dd.getSeconds();
	
		int amendId = 0;
		int currencyId = 0;
		int sno = 0;
		int displayOrder=0;

		String time = dd.getHours() + ":" + dd.getMinutes() + ":"+ dd.getSeconds();
		String sysdate_is = dd.getDate() + "-" + dd.getMonth() + "-"+ dd.getYear();
		String pdate = sysdate_is + " " + time;
		int norecords = 0;

		if (currencyNo.length() > 0) 
		{
			args = new String[2];
			args[0] = currencyNo;
			args[1] = adminCountryId;
			sql = "select max(amend_id+1) from currency_master where currency_id=? and country_id=?";
			try 
			{
				res = runner.singleSelection(sql,args);
				if(res.length() > 0 && !res.equalsIgnoreCase("null"))
				{
					amendId = Integer.parseInt(res);
					currencyId = Integer.parseInt(currencyNo);
				}
				else
				{
					currencyId = autoGenCustId("CURRENCY_ID","currency_master");
					amendId = 0;
				}
			} 
			catch (Exception e)
			{
				System.out.println("Exception in getting amendId"+ e.toString());
				e.printStackTrace();
			}
		}
		else 
		{
			currencyId = autoGenCustId("CURRENCY_ID","currency_master");
		}
		
	
		try 
		{
			args = new String[4];
			args[0] = currencyNo;
			args[1] = adminCountryId;
			args[2] = currencyNo;
			args[3] = adminCountryId;
			sql = "select display_order from currency_master where currency_id=? and country_id=? and amend_id=(select max(amend_id) from currency_master where currency_id=? and country_id=?)";
			res = runner.singleSelection(sql,args);
			if(res.length() > 0 && !res.equalsIgnoreCase("null")) 
			{
				displayOrder = Integer.parseInt(res);
			}
			else 
			{
				displayOrder = 0;
			}
		} 
		catch (Exception e)
		{
			System.out.println("Exception in getting amendId"+ e.toString());
			e.printStackTrace();
		}
		
		sno = autoGenCustId("SNO__", "currency_master");
		System.out.println("insert query for amedniddddd>>>"+ sql);
		remarkText = remarkText.replaceAll("'", "''");
		currencyType = currencyType.replaceAll("'", "''");
		try 
		{
			args = new String[13];
			args[0] = ""+sno;
			args[1] = ""+currencyId;
			args[2] = currencyType;
			args[3] = ""+amendId;
			args[4] = eDate;
			args[5] = status;
			args[6] = remarkText;
			args[7] = ""+adminCountryId;
			args[8] = ""+displayOrder;
			args[9] = rsaCode;
			args[10] = rfactor;
			args[11] = subcurrency;
			args[12] = shortname;
			
			//sql = "insert into currency_master(SNO__,CURRENCY_ID,CURRENCY_NAME,AMEND_ID,INCEPTION_DATE,EFFECTIVE_DATE,STATUS,REMARKS,country_id,display_order,RSACODE,RFACTOR,SUB_CURRENCY,SHORT_NAME) values(?,?,?,?,(select SYSDATE+10/24 from dual),TO_DATE(?,'dd/mon/yyyy HH24:MI:SS')+10/24,?,?,?,?,?,?,?,?)";
			sql = "insert into currency_master(SNO__,CURRENCY_ID,CURRENCY_NAME,AMEND_ID,INCEPTION_DATE,EFFECTIVE_DATE,STATUS,REMARKS,country_id,display_order,RSACODE,RFACTOR,SUB_CURRENCY,SHORT_NAME) values(?,?,?,?,(select SYSDATE from dual),TO_DATE(?,'dd/mon/yyyy HH24:MI:SS'),?,?,?,?,?,?,?,?)";

			runner.multipleInsertion(sql,args);
			updation = "YES";
		}
		catch (Exception e) 
		{
			System.out.println("Exception in inserting datas in exchange master"+ e.toString());
			e.printStackTrace();
		}
		return updation;
	}
	
	public String validateCurrencyType(String mode) 
	{
		String args[] = new String[0];
		String res ="";
		String[][] DateAndTime = new String[0][0];
		java.util.Date systime = new java.util.Date();
		com.maan.common.util.dataCollection data = new com.maan.common.util.dataCollection();
		String error = "";
		String values = null;
		
		try 
		{
			values = data.validString(currencyType, false);
			if ("needed".equalsIgnoreCase(values))
				error = error + "<br>*" + runner.getErrormsg("51");
			String sql = "";
			if(mode.equalsIgnoreCase("add") && !currencyType.equalsIgnoreCase("needed") ) {
				try 
				{
					args = new String[2];
					args[0] = currencyType.trim().toLowerCase();
					args[1] = adminCountryId;
					sql = "select currency_id from currency_master where lower(CURRENCY_NAME)=? and country_id=?";
					res = runner.singleSelection(sql,args);
					if(res.length() > 0 && !res.equalsIgnoreCase("null")) 
					{
						error = error + "<br>* Currency Name Already Existed";
					}
				} 
				catch (Exception e) 
				{
					System.out.println("Exception in checking date" + e.toString());
					e.toString();
				}
			}
			values = data.checkPickerDate(effectDate);
			if ("Invalid".equalsIgnoreCase(values))
				error = error + "<br>*" + runner.getErrormsg("49");
			if ("".equalsIgnoreCase(rfactor))
				error = error + "<br>*" + runner.getErrormsg("475");
			if ("".equalsIgnoreCase(subcurrency))
				error = error + "<br>*" + runner.getErrormsg("476");
			if ("".equalsIgnoreCase(shortname))
				error = error + "<br>*" + runner.getErrormsg("477");
		} 
		catch (Exception e) 
		{
			System.out.println("Exception in checkiung date" + e.toString());
			e.printStackTrace();
		}

		String sql1 = ""; 
		try 
		{
			args = new String[4];
			args[0] = adminCountryId;
			args[1] = adminCountryId;
			args[2] = currencyNo;
			args[3] = currencyNo;

			sql1 = "select to_char(effective_Date-10/24,'DD-MM-YYYY')as date1,to_char(effective_Date-10/24,'HH24')as HOUR1,to_char(effective_Date,'MI')as MIN1,to_char(effective_Date,'SS')as SECOND1 from currency_master where country_id=? and amend_id=(select max(amend_id) from currency_master where country_id=? and currency_id=?) and currency_id=?";

			DateAndTime = runner.multipleSelection(sql1,args);

			if (DateAndTime.length > 0 && DateAndTime != null) 
			{
				long diff = 0;
				try 
				{
					DateFunction dbr = new DateFunction();
					diff = dbr.getDayDifference(dbr.getCalendar(DateAndTime[0][0]), dbr.getCalendar(effectDate));
				} 
				catch (Exception e) 
				{
					System.out.println("Currency "+e.toString());
					e.printStackTrace();
				}
				System.out.println("diff--->" + diff);
				if (diff < 0) 
				{
					error = error + "<br>* Enter valid Date";
				}
			}
		} 
		catch (Exception e) 
		{
			System.out.println("Exception in getting date and time"	+ e.toString());
			e.printStackTrace();
		}
		return error;
	}

	public int autoGenCustId(String codes, String table) 
	{
		int customerId = 1;
		String sql = "";
		String res = "";
		try 
		{
			sql = "select max(" + codes + ")+1 from " + table + "";	
			res = runner.singleSelection(sql);
			if (res.length() > 0 && !res.equalsIgnoreCase("null")) 
			{
				customerId = Integer.parseInt(res);
				if (customerId == 0) 
				{
					customerId = 1;
				}
				return customerId;
			}
			
		}
		catch(Exception e) 
		{
			System.out.println("<BR>Exception in autoGenCustId : "+ e.toString());
			e.printStackTrace();
		} 
		return customerId;
	}

	public String insertBrokerLogin(String bcode, String loginPersonId)
	{
		String process = "";
		passwordEnc pass = new passwordEnc();
		String encpass = pass.crypt(password.substring(0, 3), password);
		String qry = ""; 
		String qry2 = ""; 
		String qry1 =""; 
		String args[] = new String[0];
		try 
		{
			args = new String[4];
			qry = "update login_master set LOGIN_ID=?,PASSWORD=?,OA_CODE=? where agency_code=?";
			
			args[0] = brokerId;
			args[1] = encpass;
			args[2] = bcode;
			args[3] = bcode;
			runner.multipleUpdation(qry,args);
			process = "1";
		}
		catch (Exception e) 
		{
			System.out.println("updations in login master " + e.toString());
			e.printStackTrace();
		}
		
		try 
		{
			args = new String[3];
			qry1 = "update login_user_details set LOGIN_ID=?,OA_CODE=? where agency_code=?";
			
			args[0] = brokerId;
			args[1] = bcode;
			args[2] = bcode;
			runner.multipleUpdation(qry1,args);
			process = process + "2";
		} 
		catch (Exception e) 
		{
			System.out.println("updations in login master " + e.toString());
			e.printStackTrace();
		}

		try 
		{
			args = new String[3];
			qry2 = "update personal_info set LOGIN_ID=?,OA_CODE=?  where  agency_code=?";
			
			args[0] = brokerId;
			args[1] = bcode;
			args[2] = bcode;
			
			runner.multipleUpdation(qry2,args);
			process = process + "3";
		} 
		catch (Exception e) 
		{
			System.out.println("updations in login master " + e.toString());
			e.printStackTrace();
		} 
		return process;
	}

	public String LoginIdStatus(String bcode) 
	{
		String status = "";
		String sql = "";
		String args[] = new String[1];
		try
		{
			args[0] = bcode;
			sql = "select LOGIN_ID from login_master where agency_code=?";
			status = runner.singleSelection(sql,args);
		} 
		catch (Exception e) 
		{
			System.out.println("Exception in gettinggetting OA code"+ e.toString());
			e.printStackTrace();
		}
		return status;
	}

	public String getAgencyCode(String logpersonId)
	{
		String oacode = "";
		String sql = "";
		String args[] = new String[1];
		try 
		{
			args[0] = logpersonId;
			sql = "select agency_code from personal_info where login_id=?";
			oacode = runner.singleSelection(sql,args);
		}
		catch (Exception e) 
		{
			System.out.println("Exception in getting oa code" + e.toString());
			e.printStackTrace();
		}
		return oacode;
	}

	public String getOACode(String logpersonId) 
	{
		String oacode = "";
		String sql = "";
		String args[] = new String[1];
		try 
		{
			args[0] = logpersonId;
			sql = "select oa_code from personal_info where login_id=?";
			oacode = runner.singleSelection(sql,args);
		}
		catch (Exception e) 
		{
			System.out.println("Exception in getting oa code" + e.toString());
			e.printStackTrace();
		}
		return oacode;
	}

	public static boolean checkEmailSpecial(String str) 
	{
		boolean splChar = false;
		if (!(str.trim().length() == 0))
		{
			String value = str.trim();
			char[] valuechar1 = value.toCharArray();
			for (int k = 0; k < value.length(); k++)
			{
				if (valuechar1[k] != '-' && valuechar1[k] != '_' && valuechar1[k] != '@' && valuechar1[k] != '.') 
				{
					int number = Character.getNumericValue(valuechar1[k]);
					int val = valuechar1[0];
					// char under='_';
					if (number < 0) 
					{
						splChar = true;
						break;
					} // end of numer if
				}
			} //
		} 
		else 
		{
			splChar = false;
		}
		return splChar;
	}

	//Modified by Rajesh
	public String getMaxCustomerId(String branch) 
	{
		String current_no = "";
		String sql = "";
		String args[] = new String[0];
		try
		{
			args = new String[1];
			args[0] = branch;
			sql = "select nvl(max(current_no)+1,max(start_no)) from policyno_generate where type_id=3 and status='Y' and BRANCH_CODE=?";
			current_no = runner.singleSelection(sql,args);
			args = new String[3];
			args[0] = current_no;
			args[1] = current_no;
			args[2] = branch;
			sql = "update policyno_generate set current_no=?,remarks=? where type_id=3 and status='Y' and BRANCH_CODE=?";
			runner.multipleUpdation(sql,args);
		} 
		catch (Exception e) 
		{
			System.out.println("ERROR in getMaxQuote in BrokerCreationBean  "+ e.toString());
			e.printStackTrace();
		}
		System.out.println(" BrokerCreationBean getMaxCustomerId   "+ current_no);
		return current_no;
	}

	public String getDiscountLimit(String brokerBra) 
	{
		String pname = "";
		String sql = ""; 
		String args[] = new String[2];
		try 
		{
			args[0] = brokerBra;
			args[1] = brokerBra;
			sql = "select percent from CONSTANT_DETAIL where CATEGORY_ID=(select CATEGORY_ID from CONSTANT_MAster where CATEGORY_NAME='PORTFOLIO' and branch_code=?) and CATEGORY_DETAIL_ID='2' and branch_code=? ";
			pname = runner.singleSelection(sql,args);
		} 
		catch (Exception e) 
		{
			System.out.println("Exception in getting pname" + e.toString());
			e.printStackTrace();
		}
		return pname;
	}

	// Country Work June 04
	public String[][] getExchangeDatas(String crid) 
	{
		String[][] brokerDetails = new String[0][0];
		String sno = "";
		String sql = "";
		String args[] = new String[0];
		String res = "";
		try 
		{
			args = new String[4];
			args[0] = crid;
			args[1] = adminCountryId;
			args[2] = adminCountryId;
			args[3] = crid;
//			sql = "select sno__ from exchange_master where amend_id=(select max(amend_id) from exchange_master where currency_id=? and country_id=? and effective_date<=(select sysdate+10/24 from dual)) and country_id=? and currency_id=?";
			sql = "select sno__ from exchange_master where amend_id=(select max(amend_id) from exchange_master where currency_id=? and country_id=?) and country_id=? and currency_id=?";
			res = runner.singleSelection(sql,args);
			if(res.length() > 0 && ! res.equalsIgnoreCase("null")) 
			{
				sno = res;
			}
			else 
			{
				sno = "0";
			}
		}
		catch (Exception e) 
		{
			System.out.println("Exception in getting pname" + e.toString());
			e.printStackTrace();
		}
		
		try 
		{
			args = new String[2];
			args[0] = sno;
			args[1] = adminCountryId;
			sql = "select sno__,exchange_id,exchange_rate,currency_id,amend_id,to_char(EFFECTIVE_DATE,'dd-mm-yyyy'),remarks,status,entry_date,nvl(RSACODE,'')  from exchange_master where sno__=? and country_id=? ";
	
			brokerDetails = runner.multipleSelection(sql,args);
		}
		catch (Exception e) 
		{
			System.out.println("Exception in selecting broker details"+ e.toString());
			e.printStackTrace();
		}
		return brokerDetails;
	}


	public String[][] getExchangeAmendedDatas(String crid)
	{
		String[][] brokerDetails = new String[0][0];
		String sno = "";
		String sql = "";
		String args[] = new String[0];
		String res = "";
		try 
		{
			args = new String[2];
			args[0] = crid;
			args[1] = crid;
			sql = "select sno__ from exchange_master where amend_id=(select max(amend_id) from exchange_master where currency_id=?) and currency_id=?";
			res = runner.singleSelection(sql,args);
			if(res.length() > 0 && !res.equalsIgnoreCase("null")) 
			{
				sno = res;
			}
			else 
			{
				sno = "0";
			}
		} 
		catch (Exception e) 
		{
			System.out.println("Exception in getting pname" + e.toString());
			e.printStackTrace();
		}

		try 
		{
			args = new String[1];
			args[0] = crid;
			sql = "select sno__,exchange_id,exchange_rate,currency_id,amend_id,to_char(EFFECTIVE_DATE,'dd-mm-yyyy'),remarks,status,entry_date from exchange_master where currency_id=?";

			brokerDetails = runner.multipleSelection(sql);
		} 
		catch (Exception e)
		{
			System.out.println("Exception in selecting broker details"+e.toString());
			e.printStackTrace();
		}

		return brokerDetails;
	}

	public String[][] getCurrencyData(String crid,String countryId)
	{
		String[][] brokerDetails = new String[0][0];
		String sno = "";
		String sql = "";
		String args[] = new String[0];
		String res = "";
		try 
		{
			args = new String[4];
			args[0] = crid;
			args[1] = countryId;
			args[2] = crid;
			args[3] = countryId;
			//sql = "select sno__  from currency_master where amend_id=(select max(amend_id) from currency_master where currency_id=? and country_id=? and effective_date<=(select sysdate from dual)+10/24 ) and currency_id=? and country_id=?";
			sql = "select sno__  from currency_master where amend_id=(select max(amend_id) from currency_master where currency_id=? and country_id=? and effective_date<=(select sysdate from dual)) and currency_id=? and country_id=?";
			res = runner.singleSelection(sql,args);		
			if(res.length() > 0 && !res.equalsIgnoreCase("null")) 
			{
				sno = res;
			}
			else 
			{
				sno = "0";
			}
		}
		catch (Exception e) 
		{
			System.out.println("Exception in getting pname" + e.toString());
			e.printStackTrace();
		}
		
		try 
		{
			args = new String[2];
			args[0] = sno;
			args[1] = countryId;
			sql = "select sno__,currency_id,CURRENCY_NAME,amend_id,to_char(EFFECTIVE_DATE,'dd-mm-yyyy'),remarks,status,rsacode,RFACTOR,SUB_CURRENCY,SHORT_NAME from currency_master where sno__=? and country_id=?";

			brokerDetails = runner.multipleSelection(sql,args);
		} 
		catch (Exception e) 
		{
			System.out.println("Exception in selecting broker details"+ e.toString());
			e.printStackTrace();
		}
		return brokerDetails;
	}
	
/** UserCreationController.jsp **/
	public String validateNewAdmin(String mode,String userCategory,String emailOption)
	{
		boolean errorStatus = false;
		String error = "";
		String values = null;
		if(!emailOption.equalsIgnoreCase("Yes"))
		{
			if ((user_Type.equalsIgnoreCase("")) || (user_Type == null)	|| user_Type.equalsIgnoreCase("0"))
				error = error + "<br>* Please select the User Type";
		}
		if ((user_Name.equalsIgnoreCase("")) || (user_Name == null))
			error = error + "<br>* Please enter User Name";
		if ((login_Id.equalsIgnoreCase("")) || (login_Id == null) || login_Id.indexOf(" ")!=-1 ){
			error = error + "<br>* Please enter Login Id";
		}
		else 
		{
			if (!mode.equalsIgnoreCase("edit") && checkCodeForAdmin(login_Id) ) 
			{
				error = error + "<BR>* This Login Id already existing";
			}
		}
		
		if(emailOption.equalsIgnoreCase("Yes")){
			if ((coreLoginId.equalsIgnoreCase("")) || (coreLoginId == null) || coreLoginId.indexOf(" ")!=-1 ){
				error = error + "<br>* Please enter Core Login Id";
			}
			else 
			{
				if (!mode.equalsIgnoreCase("edit"))
				{
					String result=runner.singleSelection("SELECT COUNT(*) FROM LOGIN_MASTER WHERE CORE_LOGIN_ID=?", new String[]{coreLoginId});
					if(!"0".equals(result)){
						error = error + "<BR>* Core Login Id already exists";
					}
				}
			}
		}
		if ((user_Pass.equalsIgnoreCase("")) || (user_Pass == null) || user_Pass.indexOf(" ")!= -1 ) {
			error = error + "<br>* "+runner.getErrormsg("366");//Paasword Should Not Be Empty";
		} else if (user_Pass.length() < 7) {
			error = error + "<br>* "+runner.getErrormsg("361");//Paasword Length Min 8 Chars";
		}
		else if (!mode.equalsIgnoreCase("edit")&& !(StringUtil.checkSpecial(user_Pass))) {
			error = error + "<BR>* Password should contain  special characters ";//PassWord As Spcial Chars Not Allowed ";
		} /*else if (!validPassword(user_Pass)) {
			error = error + "<BR>* "+runner.getErrormsg("365");//Please Enter Valid Password";
		}*/
		try
		{
			if((User_Branch.length() == 0) && (User_Branch.length() <= 0))
				error = error + "<br>* Please Select Branches";
		}
		catch(Exception e)
		{
			error = error + "<br>* Please Select Branches";
		}
		try
		{
			if((User_prodType.length() == 0) && (User_prodType.length() <= 0))
				error = error + "<br>* Please Select  Product Type";
		}
		catch(Exception e)
		{
			error = error + "<br>* Please Select  Product Type";
		}

		try
		{
			if("".equalsIgnoreCase(productId[0]) && "".equalsIgnoreCase(productId[1]))
				 error = error + "<br>* Please Select  Product Type";
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		if(emailOption.equalsIgnoreCase("Yes"))
		{
			if(email_id.length()<=0)
			{
				error = error + "<br>* Please enter Email Id";
			}
			else
			{
				com.maan.services.util.ValidationFormat valid = new com.maan.services.util.ValidationFormat();
				if(valid.EMailValidation(email_id))
					error = error + "<br>* Please enter valid Email Id";
			}
		}
		return error;
	}


	/** ********* New Admin user Creation *********** */
	public boolean performInsertion(String mode) 
	{
		String insertBrokers = "";
		String args[] = new String[0];
		/**Broker Codes**/	
		if(brokers!=null && brokers.length > 0 )
		{
			for(int i=0;i<brokers.length;i++)
			{
				brokers[i] = brokers[i] ==null ? "" :brokers[i];
				insertBrokers = insertBrokers + brokers[i] + ",";
			}
			insertBrokers = insertBrokers.substring(0,insertBrokers.length()-1);
		}
		else
			insertBrokers =insertBrokers;//+" "; 
	
		String sql1 = "";
		int f =0;
		if(!mode.equalsIgnoreCase("edit"))
		{
			try
			{
				sql1 = "insert into login_master(LOGIN_ID,PASSWORD,USERTYPE,USERNAME,USERID,AGENCY_CODE,OA_CODE,COMPANY_ID,CREATED_BY,STATUS,USER_ID_CREATION,AC_EXECUTIVE_CREATION,ACCESSTYPE,PASSDATE,BRANCH_CODE,COUNTRY_ID,MENU_ID,PRODUCT_ID,broker_codes) values(?,?,?,?,?,?,?,?,?,?,?,?,?,(select sysdate from dual),?,?,?,?,?)";
				
				passwordEnc pass = new passwordEnc();
				String encpass = pass.crypt(user_Pass.substring(0, 3), user_Pass);
				boolean check = false;
				args = new String[18];
				args[0] = login_Id;
				args[1] = encpass;
				args[2] = admTyp;
				args[3] = user_Name;
				args[4] = ""+Integer.parseInt(user_Type);
				args[5] = login_Id;
				args[6] = login_Id;
				args[7] = "1";
				args[8] = "Admin";
				args[9] = "Y";
				args[10] = "Y";
				args[11] = "Y";
				args[12] = "BOTH";
				args[13] = User_Branch;
				args[14] = loginCountry;
				args[15] = menuIds;
				args[16] = proIds;
				args[17] = insertBrokers;

				runner.multipleInsertion(sql1,args);
			} 
			catch (Exception ec) 
			{
				System.out.println(" Login Master Insert Query is ..."+ sql1);
				System.out.println("Error in inserting values" + ec.toString());
				ec.printStackTrace();
				f=1;
			}
		}	// Mode If
		else
		{
			try
			{
				args = new String[7];
				sql1 = "update login_master set BRANCH_CODE=?,MENU_ID=?,PRODUCT_ID=?,broker_codes=?,USERNAME=? where login_id=? and lower(USERTYPE)=?";
				args[0] = User_Branch;
				args[1] = menuIds;
				args[2] = proIds;
				args[3] = insertBrokers;
				args[4] = user_Name; 
				args[5] = login_Id;
				args[6] = admTyp.toLowerCase();
				
				runner.multipleUpdation(sql1,args);
			}
			catch(Exception e)
			{
				System.out.println("Login Master Update Query is ..."+sql1);
				System.out.println("Error while updating the values..."+e.toString());
				e.printStackTrace();
				f=1;
			}
		}
		
		if(f==1)
			return false;

		return true;
	}	

	/******** Menu Insertion ***********/
	public String validateNewAdmin()
	{
		boolean errorStatus = false;
		String error = "";
		String values = null;
		String menu = "";

		if ( menuIds.equalsIgnoreCase("") || menuIds == null	|| menuIds.length() <=0 )
			error = error + "<br>* Select The Menu Links";
		
		if ( proIds.equalsIgnoreCase("") || proIds == null || proIds.length()<=0 )
			error = error + "<br>* Select The Products";
			
		try
		{
			StringTokenizer st = new StringTokenizer(menuIds,",");
			while(st.hasMoreTokens())
			{
				menu = st.nextToken();
				/*if((menu.equalsIgnoreCase("3") || menu.equalsIgnoreCase("4") || menu.equalsIgnoreCase("10") || menu.equalsIgnoreCase("14") || menu.equalsIgnoreCase("17") || menu.equalsIgnoreCase("18") || menu.equalsIgnoreCase("19")) && brokers == null && brokers.length ==0 && brokers.length<=0)
				{error = error + "<br>* Please Select brokers";}*/
			}
		}
		catch(Exception e)
		{
			error = error + "<br>* Please Select brokers";
			System.out.println("validateNewAdmin"+ e.toString());
			e.printStackTrace();
		}
		return error;
	}
	
	
	public String getCurrencyTypeById(String cuid) 
	{
		String pname = "";
		String sql = "";
		String args[] = new String[2];
		try 
		{
			args[0] = cuid;
			args[1] = cuid;
		    sql = "select CURRENCY_NAME from currency_master where currency_id=? and amend_id=(select max(amend_id) from currency_master where  currency_id=?)";
				pname =runner.singleSelection(sql,args);
		}
		catch (Exception e) 
		{
			System.out.println("Exception in getting pname" + e.toString());
		}
		return pname;
	}

	public String getBrokerNameByAgency(String agency) 
	{
		String pname = "";
		String sql = ""; 
		String args[] = new String[1];
		try 
		{
			args[0] = agency;
			sql = "select company_name from broker_company_master where agency_code=? and status='Y'";
				pname = runner.singleSelection(sql,args);
		}
		catch (Exception e) 
		{
			System.out.println("Exception in getting pname" + e.toString());
			e.printStackTrace();
		} 
		return pname;
	}

	public String[][] getStartEnd(String agency) 
	{

		String[][] startEnd = new String[0][0];
		String sql = ""; 
		String args[] = new String[2];
		try 
		{
			args[0] = agency;
			args[1] = agency;
			sql = "select START_NO,END_NO from policyno_generate where agency_code=? and status='Y' and amend_id=(select max(amend_id) from policyno_generate where agency_code=? and status='Y')";

			startEnd = runner.multipleSelection(sql,args);

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return startEnd;
	}

	public String getProductById(String pid) 
	{
		String pname = "";
		String sql = "";
		String args[] = new String [1];
		String res = "";
		try 
		{
			args[0] = pid;
			sql = "select product_name from product_master where product_id=? and status='Y'";
			res = runner.singleSelection(sql,args);
			if(res.length() > 0 && !res.equalsIgnoreCase("null")) 
			{
				pname = res;
			}
			else 
			{
				pname = "No Product";
			}
		} 
		catch (Exception e) 
		{
			System.out.println("problem in getting product is" + e.toString());
			e.printStackTrace();
		}
		return pname;
	}

	public String getLoginByAgency(String pid) 
	{
		String pname = "";
		String sql = "";
		String args[] = new String[1];
		try 
		{
			args[0] = pid;
			sql = "select login_id from login_master where agency_code=? and status='Y'";
			pname = runner.singleSelection(sql,args);
			if(pname!=null)
			{
				if(pname.length()<=0)
					pname = "NONE";
			}
		}
		catch (Exception e) 
		{
			System.out.println("problem in getting product is" + e.toString());
			e.printStackTrace();
		} 
		return pname;
	}

	public String getLoginByAgency(String pid,String dummy) 
	{
		String pname = "";
		String sql = "";
		String args[] = new String[1];
		try 
		{
			args[0] = pid;
			sql = "select login_id from login_master where agency_code=? and status='Y'";
			pname = runner.singleSelection(sql,args);
			if(pname!=null)
			{
				if(pname.length()<=0)
					pname = "NONE";
			}
		}
		catch (Exception e) 
		{
			System.out.println("problem in getLoginByAgency product is" + e.toString());
			e.printStackTrace();
		} 
		return pname;
	}

	public String[][] getRefferals(String brokerBra) 
	{
		String[][] startEnd = new String[0][0];
		String sql = ""; 
		String args[] = new String[1];
		try 
		{
			args[0] = brokerBra;
			sql = "select mail_no,EMAIL_SUBJECT from mail_details where EMAIL_SUBJECT not in ('Admin Referal','Normal') and status='Y' and branch_code=?";
			startEnd = runner.multipleSelection(sql,args);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return startEnd;
	}

	/** For Bank Master **/
	public String[][] getBankIds(String adminCountry) 
	{
		String[][] bankid = new String[0][0];
		String sql = ""; 
		String args[]= new String[3];
		try 
		{
			args[0] = adminCountry;
			args[1] = adminCountry;
			args[2] = adminCountry;
			//sql = "select distinct BANK_ID,bank_name from open_cover_bank_master where BELONGING_COUNTRY_ID=? order by bank_name ";

			sql = "select bank_id,bank_name from open_cover_bank_master where BELONGING_COUNTRY_ID=? and amend_id||bank_id in(select max(amend_id)||bank_id from open_cover_bank_master where BELONGING_COUNTRY_ID=? and bank_id in(select bank_id from open_cover_bank_master where BELONGING_COUNTRY_ID=?) group by bank_id) order by bank_name";
			bankid = runner.multipleSelection(sql,args);
		} 
		catch(Exception e) 
		{
			System.out.println("Exception in getting distinct bak ids"+ e.toString());
			e.printStackTrace();
		}
		return bankid;
	}
	
	public String[][] getAmendData(String bid)
	{
		String amendData[][] = new String[0][0];
		bid = bid.replaceAll("'", "''");
		String args[] = new String[1];
		String sql = ""; 
		try
		{
			args[0] = bid;
			sql = "select bank_id,bank_name,to_char(entry_date,'dd-mm-yyyy'),expiry_date,to_char(EFFECTIVE_DATE,'dd-mm-yyyy'),amend_id,remarks,status,rsacode from OPEN_COVER_BANK_MASTER where BANK_ID=?";

			amendData = runner.multipleSelection(sql,args);

		} 
		catch (Exception e) 
		{
			System.out.println("Exception in getting amended datas"+ e.toString());
			e.printStackTrace();
		} 
		return amendData;
	}

	// / For BankMaster ////
	public String validateBankMaster() 
	{
		String error = "";
		String values = "";
		
		try{
			com.maan.common.util.dataCollection data = new com.maan.common.util.dataCollection();
			values = data.validString(getBankId(), true);
			if ("needed".equalsIgnoreCase(values))
				error = error + "<br>*" + runner.getErrormsg("157");
            System.out.println("getBankName() in  bean"+getBankName());
			//values = data.validString(getBankName(), false);
            //System.out.println("getBankName() in  after validation->"+values);
			//if (values != null && !values.equalsIgnoreCase("null")  &&"needed".equalsIgnoreCase(values) )
			//	error = error + "<br>*" + runner.getErrormsg("159");
            if(getBankName() == null || getBankName().equals("") ||  getBankName().equalsIgnoreCase("null") || getBankName().equalsIgnoreCase("select")){
                System.out.println("inside this validation");
                 error = error  + "<br>" +"Invalid Bank Name";
            }           
            //if(belongingCountry.length() == 0 || belongingCountry.equalsIgnoreCase("null") || belongingCountry.equalsIgnoreCase("select")){
            //    error = error + "<br>" +"Please Select Country ";
            //} 
          
		    values = validateBank(getBankId(),belongingCountry); //Dec 08
           System.out.println("Validate Bank Id ..."+values);
            if(values.length() >0){
                error = error + values;
            }	
			values = data.validString(getRsaCode(), true);
			if ("needed".equalsIgnoreCase(values))
				error = error + "<br>*" + runner.getErrormsg("163");
			//if ("Invalid".equalsIgnoreCase(values))
			//	error = error + "<br>*" + runner.getErrormsg("160");
			values = data.checkPickerDate(getEffectDate());
			if ("Invalid".equalsIgnoreCase(values))
				error = error + "<br>*" + runner.getErrormsg("49");
		}
        catch (Exception e) {
            e.printStackTrace();
			return "Error in Database Connection";
			
		}
		return error;
	}

	public String validateBankMaster123() 
	{
		String error = "";
		String values = null;
		
		try 
		{
			com.maan.common.util.dataCollection data = new com.maan.common.util.dataCollection();
			values = data.validString(getBankName(), true);
			if ("needed".equalsIgnoreCase(values))
				error = error + "<br>*" + runner.getErrormsg("159");
			if (getBankName().equalsIgnoreCase("select"))
				error = error + "<br>* Please select Bank Name";
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return "Error in Database Connection";
		}
		return error;
	}

	// / For BankMaster ////
	public String insertBankMasterData()
	{
		String insertion_status = "";
		String sql = "";
		String eDate = com.maan.common.util.OracleDateConversion.ConvertDate(effectDate);
		
		int amendId = 0;
		int norecords = 0;
		String args[] = new String[0];
		String res = "";
		bankName = bankName.replaceAll("'", "''");
		bankName = bankName.replaceAll("\"", "&#34");
		rsaCode = rsaCode.replaceAll("'", "''");
		try
		{
			args = new String[2];
			/*args[0] = bankName;
			args[1] = belongingCountry;
			sql = "select nvl(max(amend_id),0)+1 from OPEN_COVER_BANK_MASTER where bank_name=? and BELONGING_COUNTRY_ID=?";	*/
			args[0] = bankId;
			args[1] = belongingCountry;
			sql = "select nvl(max(amend_id),0)+1 from OPEN_COVER_BANK_MASTER where bank_id=? and BELONGING_COUNTRY_ID=?";	
			res = runner.singleSelection(sql,args);
			int flag = 0;
			if(res.length() > 0 && !res.equalsIgnoreCase("null"))
			{
				amendId = Integer.parseInt(res);
				System.out.println("database amendidsss" + amendId);
				flag = 1;
			}
			if (amendId == 0 && flag == 1)
			{
				amendId = autoGenCustId("amend_id", "OPEN_COVER_BANK_MASTER");
				System.out.println("auto amendidsss" + amendId);
			}
			System.out.println("default amendidsss" + amendId);
		}
        catch (Exception e)
		{
			System.out.println("Exception in getting amendId" + e.toString());
            e.printStackTrace();
		}
		
		bankId = bankId.replaceAll("'", "''");
		remarkText = remarkText.replaceAll("'", "''");
		rsaCode = rsaCode.replaceAll("'", "''");		
		bankName = bankName.replaceAll("&#34", "\"");

		try 
		{
			args = new String[8];
			sql = "insert into OPEN_COVER_BANK_MASTER(BANK_ID,BANK_NAME,ENTRY_DATE,EXPIRY_DATE,EFFECTIVE_DATE,AMEND_ID,REMARKS,STATUS, RSACODE,BELONGING_COUNTRY_ID) values(?,?,(select SYSDATE+10/24 from dual),(select SYSDATE+10/24 from dual),TO_DATE(?,'dd/mon/yyyy')+10/24,?,?,?,?,?)";
			
			args[0] = bankId;
			args[1] = bankName;
			args[2] = eDate;
			args[3] = ""+amendId;
			args[4] = remarkText;
			args[5] = status;
			args[6] = rsaCode;
			args[7] = belongingCountry;

			runner.multipleInsertion(sql,args);
			insertion_status = "YES";
		} 
        catch (Exception e)
		{
			System.out.println("Exception in inserting datas in BANK MASTER"+ e.toString());
            e.printStackTrace();
		}
        return insertion_status;
	}

	// / For BankMaster ////
	public String[][] getBankMasterDatas(String bid) 
	{
		String[][] bankMasterDetails = new String[0][0];
		String sql = "";
		String args[] = new String[0];

		bid = bid.replaceAll("'", "''");
		
		try 
		{
			args = new String[4];
			args[0] = bid;
			args[1] = bid;
			args[2] = belongingCountry;
			args[3] = belongingCountry;
			sql = "select bank_id,bank_name,entry_date,expiry_date,to_char(EFFECTIVE_DATE,'dd-mm-yyyy'),amend_id,remarks,status,rsacode from OPEN_COVER_BANK_MASTER where BANK_ID=? and amend_id in( select max(amend_id) from OPEN_COVER_BANK_MASTER where BANK_ID=? and BELONGING_COUNTRY_ID =?) and BELONGING_COUNTRY_ID =?";
			
			bankMasterDetails = runner.multipleSelection(sql,args);
		} 
		catch (Exception e) 
		{
			System.out.println("Exception in selecting getBankMasterDatas details"+ e.toString());
			e.printStackTrace();
		} 
		System.out.println("getBankMasterDatas..."+sql);
		System.out.println("getBankMasterDatas...bid "+bid);
		System.out.println("getBankMasterDatas...belongingCountry"+belongingCountry);
		System.out.println("getBankMasterDatas...length"+bankMasterDetails.length);
		return bankMasterDetails;
	}

	public String[][] getBrokerCode(String adminbraCode) 
	{
		String[][] brokers = new String[0][0];
		String sql = "";
		String args[] = new String[1];
		try
		{
			adminbraCode = adminbraCode.replaceAll("'","");
			args[0] = adminbraCode;
//			sql = "select detail_name from constant_detail where CATEGORY_ID=38 and CATEGORY_DETAIL_ID=1 and status='Y' and branch_code=?";
			sql = "SELECT BROKER_CODE_SEQ.NEXTVAL FROM DUAL";
			brokers = runner.multipleSelection(sql);
		}
		catch (Exception e) 
		{
			System.out.println("Exception in getBrokerCode " + e.toString());
			e.printStackTrace();
		} 
		return brokers;
	}

	/** * Start - Getting User Type from Constant_Detail ** */

	public String[][] getAdminUserTypeCreation(String branch,String prodType) 
	{
		String syntax="";
		String[][] userType = new String[0][0];
		/*if(prodType.equalsIgnoreCase("Marine"))
		{
			syntax ="2,3,4";
		}
		else
		{
			syntax = "5,6";
		}*/
		String sql = ""; 
		try
		{
			//sql = "select category_detail_id,detail_name from constant_detail where category_id=40 and status='Y' and branch_code='"+branch+"' and category_detail_id in("+syntax+")";
			sql = "select category_detail_id,detail_name from constant_detail where category_id=40 and status='Y' and branch_code=? and remarks=?";
			String args[] = new String[2];
			args[0] = branch;
			args[1] = prodType;
			userType = runner.multipleSelection(sql,args);
		}
		catch (Exception e) 
		{
			System.out.println("Exception in getting user type" + e.toString());
		}
		return userType;
	}
	public String getAdminCategory(String login,String bcode)
	{
		String res = "";
		String sql = "select remarks from constant_detail where lower(DETAIL_NAME)=(select lower(usertype) from login_master where login_id=? and branch_code=?) " +
				"and branch_code=?";
		try
		{
			String args[] = new String[3];
			args[0] = login;
			args[1] = bcode;
			args[2] = bcode;
			res = runner.singleSelection(sql,args);
		}
		catch (Exception e) 
		{
			System.out.println("Exception in type" + e.toString());
			e.printStackTrace();
		}
		return res;
	}
	
	public String[] getProductCode(String login)
	{
		String res[] = new String[2];
		String sql = "select product_id from LOGIN_USER_DETAILS where lower(login_id)= lower(?) and product_id=? and status=?";
		
		System.out.println("Sql+"+sql);
		try
		{
			String args[] = new String[3];
			
			args[0] = login;
			args[1] = "3";
			args[2] = "Y";
			res[0] = runner.singleSelection(sql,args);
			args[1] = "11";
			res[1] = runner.singleSelection(sql,args);
		}
		catch (Exception e) 
		{
			System.out.println("Exception in type" + e.toString());
			e.printStackTrace();
		}
		return res;
	}
	public String getTypeUser(String loginId,String branch) 
	{
		String userType = "";
		String args[] = new String[2];
		String sql = "";
		try
		{
			args[0] = loginId;
			args[1] = branch;
			sql = "select USERID from login_master where login_id=? and branch_code=?";
			userType = runner.singleSelection(sql,args);
		}
		catch (Exception e) 
		{
			System.out.println("Exception in getTypeUser type" + e.toString());
			e.printStackTrace();
		}
		return userType;
	}

	

	public boolean validateNewAdminType() 
	{
		if (newAdminType.equalsIgnoreCase(""))
			return true;
		return false;
	}
	//rajesh created new methods on 12/05
	public String getBrokersStatus(String acode)
	{
		String sql = ""; 
		String res = ""; 
		String args[] = new String[1];
		try
		{
			args[0] = acode;
			sql = "select status from login_master where agency_code=?";
			res = runner.singleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return res;
	}

	public boolean updateStatus(String status, String brokerId) 
	{
		boolean check = false;
		String sql = "update login_master set status=? where agency_code=?";
		try
		{
			String args[] = new String[2];
			args[0] = status;
			args[1] = brokerId;
			String temp = runner.multipleUpdation(sql,args);
			if(temp.equalsIgnoreCase("UPDATED")) 
			{
				check = true;
			}
			else 
			{
				check = false;
			}
		}
		catch (Exception e)
		{
			System.out.println("exception in changing password" + e.toString());e.printStackTrace();
		}
		if(status.equalsIgnoreCase("N"))
		{
			String qry ="update login_master set status=? where oa_code in(select oa_code from login_master where agency_code=?)";
			try
			{
				String args[] = new String[2];
				args[0] = status;
				args[1] = brokerId;
				String temp = runner.multipleUpdation(qry,args);
				if(temp.equalsIgnoreCase("UPDATED")) 
				{
					check = true;
				}
				else 
				{
					check = false;
				}
			}
			catch (Exception e)
			{
				System.out.println("exception in Broker & User BCB" + e.toString());e.printStackTrace();
			}
		}
		/** Broker & User **/
		check = false;
		String sql1 = "update personal_info set status=? where agency_code=?";
		try 
		{
			String args[] = new String[2];
			args[0] = status;
			args[1] = brokerId;
			String temp = runner.multipleUpdation(sql1,args);
			if(temp.equalsIgnoreCase("UPDATED")) 
			{
				check = true;
			}
			else 
			{
				check = false;
			}
		}
		catch (Exception e) 
		{
			System.out.println("exception in changing password" + e.toString());e.printStackTrace();
		}
		return check;
	}
	
	public String[][] getBrokers(String from,String branchCode) //User1.jsp
	{
		String[][] brokers = new String[0][0];
		String sql = ""; 
		try 
		{
			sql = "select bcm.CUSTOMER_ID,bcm.CONTACT_PERSON,bcm.COMPANY_NAME,pi.AGENCY_CODE from BROKER_COMPANY_MASTER bcm,personal_info pi where bcm.agency_code=pi.agency_code and  bcm.branch_code in("+branchCode+") and APPLICATION_ID='2' and pi.login_id in(select login_id from login_master where oa_code in(select agency_code from broker_company_master where branch_code in("+branchCode+"))) order by lower(bcm.COMPANY_NAME)";

			brokers = runner.multipleSelection(sql);
		}
		catch (Exception e) 
		{
			System.out.println("Exception in getting products" + e.toString());
			e.printStackTrace();
		} 
		return brokers;
	}
	
	public String[][] getBrokersList(final String branchCode) //User1.jsp
	{
		String[][] brokers = new String[0][0];
		try 
		{
			final String sql = "select bcm.CUSTOMER_ID,bcm.CONTACT_PERSON,bcm.COMPANY_NAME,pi.AGENCY_CODE,bcm.missippi_id,to_char(pi.ENTRY_DATE,'DD-MM-YYYY'),pi.login_id from BROKER_COMPANY_MASTER bcm,personal_info pi where bcm.agency_code=pi.agency_code and  bcm.branch_code in("+branchCode+") and APPLICATION_ID='2' and pi.login_id in(select login_id from login_master where oa_code in(select agency_code from broker_company_master where branch_code in("+branchCode+"))) order by lower(bcm.COMPANY_NAME)";
			brokers = runner.multipleSelection(sql);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
		return brokers;
	}// broker list
	
	public String getBrokersName(final String brokerID) //User1.jsp
	{
		String brokers = null;
		try 
		{
			final String sql = "select company_name||' ('||AGENCY_CODE||')' from BROKER_COMPANY_MASTER where AGENCY_CODE='"+brokerID+"'";
			brokers = runner.singleSelection(sql);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
		return brokers;
	}// broker list


	public HashMap getBrokersClientList(String acode)
	{
		String sqlUser = "select count(login_id),oa_code from login_master where usertype='User' and oa_code in(select oa_code from login_master where login_id in(select login_id from login_master where agency_code in("+acode+") and login_id not in('NONE')) and login_id not in('NONE')) group by oa_code"; 

		String sqlFreight = "select count(login_id),oa_code from login_master where usertype='Freight' and oa_code in(select oa_code from login_master where login_id in(select login_id from login_master where agency_code in("+acode+") and login_id not in('NONE')) and login_id not in('NONE')) group by oa_code"; 

		String sqlCustomer = "select count(login_id),oa_code from login_master where usertype='Customer' and oa_code in(select oa_code from login_master where login_id in(select login_id from login_master where agency_code in("+acode+") and login_id not in('NONE')) and login_id not in('NONE')) group by oa_code"; 

		String brokersql = "select company_name,agency_code from BROKER_COMPANY_MASTER where agency_code in("+acode+") order by company_name";

		String userDetails[][] = new String[0][0];
		String cusDetails[][] = new String[0][0];
		String freDetails[][] = new String[0][0];
		String broDetails[][] = new String[0][0];
		HashMap fullList = new HashMap();

		userDetails = runner.multipleSelection(sqlUser);
		freDetails = runner.multipleSelection(sqlFreight);
		cusDetails = runner.multipleSelection(sqlCustomer);
		broDetails = runner.multipleSelection(brokersql);

		if(userDetails.length>0)
		{
			for(int u=0;u<userDetails.length;u++)
			{
				fullList.put("user"+userDetails[u][1],(userDetails[u][0]!=null?userDetails[u][0]:"0"));
			}
		}
		if(freDetails.length>0)
		{
			for(int u=0;u<freDetails.length;u++)
			{
				fullList.put("freight"+freDetails[u][1],(freDetails[u][0]!=null?freDetails[u][0]:"0"));
			}
		}
		if(cusDetails.length>0)
		{
			for(int u=0;u<cusDetails.length;u++)
			{
				fullList.put("customer"+cusDetails[u][1],(cusDetails[u][0]!=null?cusDetails[u][0]:"0"));
			}
		}
		for(int i=0;i<broDetails.length;i++)
		{
			fullList.put("bname"+i,(broDetails[i][0]!=null?broDetails[i][0]:""));
			fullList.put("acode"+i,(broDetails[i][1]!=null?broDetails[i][1]:""));
			fullList.put("userCount"+i,(fullList.get("user"+broDetails[i][1])!=null?(String)fullList.get("user"+broDetails[i][1]):"0"));
			fullList.put("freCount"+i,(fullList.get("freight"+broDetails[i][1])!=null?(String)fullList.get("freight"+broDetails[i][1]):"0"));
			fullList.put("cusCount"+i,(fullList.get("customer"+broDetails[i][1])!=null?(String)fullList.get("customer"+broDetails[i][1]):"0"));
		}
		fullList.put("size",""+broDetails.length);
		return fullList;
	}

	public String[][] getBrokerInDetails(String acode, String type)
	{
		String sql = "";
		String res[][] = new String[0][0];
		String args[] = new String[0];
		try
		{
			if(type.equalsIgnoreCase("Broker"))
			{
				args = new String[1];
				args[0] = acode;
				sql = "select nvl(per.first_name||''||per.last_name,per.company_name),per.status,log.usertype,per.application_id,per.agency_code from personal_info per,login_master log where log.oa_code=? and log.agency_code=per.agency_code and log.login_id not in('NONE') and per.application_id!='2' order by per.application_id";
				res = runner.multipleSelection(sql,args);
			}
			else if(type.equalsIgnoreCase("All"))
			{
				sql = "select nvl(per.first_name||''||per.last_name,per.company_name),per.status,log.usertype,bro.company_name,per.agency_code from personal_info per,login_master log, BROKER_COMPANY_MASTER bro  where log.oa_code in('"+acode+"') and log.agency_code=per.agency_code and log.login_id not in('NONE') and bro.agency_code=log.oa_code  and per.application_id!='2' order by bro.company_name||per.application_id";
				res = runner.multipleSelection(sql);
			}
			else if(type.equalsIgnoreCase("User"))//type as User
			{
				args = new String[2];
				args[0] = type;
				args[1] = acode;
				sql = "select nvl(per.first_name||''||per.last_name,per.company_name),per.status,log.usertype,per.application_id,per.agency_code from personal_info per,login_master log where log.usertype=? and log.oa_code=? and log.agency_code=per.agency_code and log.login_id not in('NONE')";
				res = runner.multipleSelection(sql,args);
			}
			else //type as Freight
			{
				args = new String[2];
				args[0] = type;
				args[1] = acode;
				sql = "select nvl(per.first_name||''||per.last_name,per.company_name),per.status,log.usertype,per.application_id,per.agency_code from personal_info per,login_master log where log.usertype=? and log.oa_code=? and log.agency_code=per.agency_code and log.agency_code=log.fd_code and log.login_id not in('NONE')";
				res = runner.multipleSelection(sql,args);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return res;
	}
	public String[][] getBrokerInName(String acode, String type)
	{
		String sql = "";
		String res[][] = new String[0][0];
		String args[] = new String[0];
		try
		{
			if(type.equalsIgnoreCase("Broker"))
			{
				args = new String[1];
				args[0] = acode;
				sql = "select nvl(per.first_name||''||per.last_name,per.company_name),per.status,log.usertype,per.application_id,per.agency_code from personal_info per,login_master log where per.agency_code=? and log.agency_code=per.agency_code and log.login_id not in('NONE') and per.application_id!='2' order by per.application_id";
				res = runner.multipleSelection(sql,args);
			}
			else if(type.equalsIgnoreCase("All"))
			{
				sql = "select nvl(per.first_name||''||per.last_name,per.company_name),per.status,log.usertype,bro.company_name,per.agency_code from personal_info per,login_master log, BROKER_COMPANY_MASTER bro  where per.agency_code in('"+acode+"') and log.agency_code=per.agency_code and log.login_id not in('NONE') and bro.agency_code=log.oa_code  and per.application_id!='2' order by bro.company_name||per.application_id";
				res = runner.multipleSelection(sql);
			}
			else
			{
				args = new String[2];
				args[0] = type;
				args[1] = acode;
				sql = "select nvl(per.first_name||''||per.last_name,per.company_name),per.status,log.usertype,per.application_id,per.agency_code from personal_info per,login_master log where log.usertype=? and per.agency_code=? and log.agency_code=per.agency_code and log.login_id not in('NONE')";
				res = runner.multipleSelection(sql,args);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return res;
	}
	
	/** * End Insert new admin type to Constant_Detail Table  ** */
	
	/** AdminCreation With Dynamic Link, Branch and Product - Start **/
	
	public String[][] getAdminMenu(String branch)
	{
		String[][] menu = new String[0][0];
		String args[] = new String[1];
		try
		{
			args[0] =branch;
			String sql="select CATEGORY_DETAIL_ID,DETAIL_NAME,REMARKS from CONSTANT_DETAIL where status='Y' and CATEGORY_ID='41' and branch_code=? order by CATEGORY_DETAIL_ID";
			menu = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("Exception in getting AdminMenu BreokerCreation Bean...."+e.toString());
			e.printStackTrace();
		}
		return menu;
	}

	// Marine or Home / Travel -Start
	public String[][] getAdminMenu(String branch,String prodType)
	{
		String[][] menu=new String[0][0];
		String sql="";
		String args[] = new String[1];
		try
		{
			args[0] =branch;
			if(prodType.equalsIgnoreCase("Marine"))
			{
				sql = "select CATEGORY_DETAIL_ID,DETAIL_NAME,REMARKS from CONSTANT_DETAIL where status='Y' and CATEGORY_ID='41' and branch_code=? and CATEGORY_DETAIL_ID not in ('17','18','19','20','24','26','27','28','33') order by CATEGORY_DETAIL_ID";
			}
			else
			{
				sql = "select CATEGORY_DETAIL_ID,DETAIL_NAME,REMARKS from CONSTANT_DETAIL where status='Y' and CATEGORY_ID='41' and branch_code=? and CATEGORY_DETAIL_ID in('1','2','8','17','18','19','20','21','24','26','16','27','28','33') order by CATEGORY_DETAIL_ID"; 
			}

			menu = runner.multipleSelection(sql,args);
			System.out.println("menu "+ menu.length);
		}
		catch(Exception e)
		{
			System.out.println("Exception in getting AdminMenu BreokerCreation Bean...."+e.toString());
			e.printStackTrace();
		}
		return menu;
	}

	public String[][] getAdminProducts(String branch,String prodType)
	{
		String[][] product = new String[0][0];
		String sql = "";
		String args[] = new String[1];
		try
		{
			args[0] =branch;
			if(prodType.equalsIgnoreCase("Marine"))
				sql = "select product_id,product_name from product_master where status='Y' and product_id in('3','11') and  branch_code=?";
			else
				sql = "select product_id,product_name from product_master where status='Y' and product_id not in('3','11') and branch_code=?";

			product = runner.multipleSelection(sql,args);
			System.out.println("product .."+product.length);
		}
		catch(Exception e)
		{
			System.out.println("Exception in getting AdminProducts BreokerCreation Bean...."+e.toString());
			e.printStackTrace();
		}
		return product;
	}

	// Marine or Home / Travel - End


	public String[][] getAdminProducts(String branch)
	{
		String[][] product=new String[0][0];
		String args[] = new String[1];
		try
		{
			args[0] = branch;
			String sql="select product_id,product_name from product_master where status='Y' and branch_code=?";
			product = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("Exception in getting AdminProducts BreokerCreation Bean...."+e.toString());
			e.printStackTrace();
		}
		return product;
	}

	public String[][] getMenus(String loginId,String adminBranch)
	{
		String menu[][]= new String[0][0];
		String sql = "";
		String result = "";
		String res="";
		String args[] = new String[2];
		try
		{
			args[0] = loginId;
			args[1] = adminBranch;
			sql = "select Menu_Id from login_master where login_id=? and status='Y' and branch_code=?";
			result = runner.singleSelection(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("Exception is getting from getMenus..."+e.toString());
			e.printStackTrace();
		}
		try
		{
			if(!result.equalsIgnoreCase("All"))
			{
				res = result.replaceAll(",","','");
			}
		}
		catch(Exception e)
		{
			System.out.println("getMenus in brokerCreation Bean.java" +e.toString());
			e.printStackTrace();
		}

		try
		{
			if(result.equalsIgnoreCase("All"))
			{
				sql = "select CATEGORY_DETAIL_ID,DETAIL_NAME,REMARKS from CONSTANT_DETAIL where status='Y' and CATEGORY_ID='41' and branch_code='"+adminBranch+"' order by CATEGORY_DETAIL_ID";
				menu = runner.multipleSelection(sql);
			}
			else
			{
				res = "'"+res+"'";
				sql = "select CATEGORY_DETAIL_ID,DETAIL_NAME,REMARKS from CONSTANT_DETAIL where status='Y' and CATEGORY_ID='41' and CATEGORY_DETAIL_ID in("+res+") and branch_code='"+adminBranch+"' order by CATEGORY_DETAIL_ID";
				menu = runner.multipleSelection(sql);
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception getting from meuIds in constant details"+e.toString());
			e.printStackTrace();
		}
		 return menu;
	}

	public String[][] getAdminEditUser(String adminType,String branch)
	{
		String adminEdit[][] = new String[0][0];
		String sql ="";
		String args[] = new String[2];
		try
		{
		    args[0] = adminType;
			args[1] = branch;
		  sql="select login_id,oa_code from login_master where userid=? and branch_code=?";
		  adminEdit = runner.multipleSelection(sql,args);
		  System.out.println("<<Query for  getAdminEditUser  >>  "+sql);
		}
		catch(Exception e)
		{
			System.out.println("Exception getting from getAdminEditUser ..."+e.toString());
			e.printStackTrace();
		}
		return adminEdit;
	}

	public String[][] getAdminDetails(String oaCode)
	{
		String adminDetails[][] = new String[0][0];
		String sql = "";
		String args[] = new String[1];
		try
		{
			args[0] = oaCode;
			sql ="select USERTYPE,USERNAME,LOGIN_ID,PASSWORD,BRANCH_CODE,MENU_ID,PRODUCT_ID,nvl(broker_codes,'0') from login_master where oa_code =? and status='Y'";
			adminDetails = runner.multipleSelection(sql,args);
			
		}
		catch(Exception e)
		{
			System.out.println("Exception getting from getAdminDeatils for edit mode "+e.toString());
			e.printStackTrace();
		}
		return adminDetails;
	}
	
	//Rajesh Modified on 01/06 for RSA/UWadmin active/deactive
	public boolean updateStatus(String status, String brokerId,String royal) 
	{
		boolean check = false;
		String sql = ""; 
		try
		{
			sql = "update login_master set status=? where agency_code=?";
			String args[] = new String[2];
			args[0] = status;
			args[1] = brokerId;
			String temp = runner.multipleUpdation(sql,args);
			if(temp.equalsIgnoreCase("UPDATED")) 
			{
				check = true;
			}
			else 
			{
				check = false;
			}
		} 
		catch (Exception e) 
		{
			System.out.println("exception in changing password" + e.toString());e.printStackTrace();
		}
		return check;
	}

	public String getAdminUserType(String acode)
	{
		String sql = ""; 
		String args[] = new String[1]; 
		String res = ""; 
		try
		{
			args[0] = acode;
			sql = "select initcap(usertype) from login_master where agency_code=?";
			res = runner.singleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return res;
	}

	//For World Common start
	public String[][] getAdminBranch(String adminLogin)
	{
		String sql = ""; 
		String res[][] = new String[0][0];
		String args[] = new String[1];
		try
		{
			args[0] = adminLogin;
			sql = "SELECT BRANCH_CODE,BRANCH_NAME,BRANCH_CODE from BRANCH_MASTER where BRANCH_CODE=(select BRANCH_CODE from" +
					" LOGIN_MASTER where login_id=?) order by SNO__";
			res = runner.multipleSelection(sql,args);
			if(res.length>0){
				String args1[] = {res[0][0]};
				String result = runner.singleSelection("select DETAIL_NAME from CONSTANT_DETAIL where CATEGORY_ID='116' and CATEGORY_DETAIL_ID=1 and status='Y'" +
						" and branch_code=?",args1);
				res[0][0] = result;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return res;
	}

	public String getAdminCountry(String adminLogin)
	{
		String sql = ""; 
		String res="";
		String args[] = new String[1];
		try
		{
			args[0] = adminLogin;
			sql = "select nvl(COUNTRY_ID,'1') from LOGIN_MASTER where login_id=?";
			res = runner.singleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return res;
	}
	
	public String getAdminProductId(String adminLogin)
	{
		String sql = "";
		String res="";
		String args[] = new String[1];
		try
		{
			args[0] = adminLogin;
			sql = "select nvl(PRODUCT_ID,'3,11') from LOGIN_MASTER where login_id=?";
			res = runner.singleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return res;
	}

	public String[][] getEmirates(String adminCid) 
	{
		String sql = ""; 
		String res[][] = new String[0][0];
		String args[] = new String[1];
		try
		{
			args[0] = adminCid;
			sql = "select city_id,city_name from city_master where country_id=? order by city_name";
			res = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return res;
	}
	
	public String[][] getProducts(String adminPid ,String branch) 
	{
		String sql = "";
		/*if(adminPid.equalsIgnoreCase("All"))
				sql = "select PRODUCT_ID,PRODUCT_NAME,COMPANY_ID from product_master where status='Y' and COMPANY_ID not in (2) and branch_code in("+branch+") order by product_id";
		else
			sql = "select PRODUCT_ID,PRODUCT_NAME,COMPANY_ID from product_master where status='Y' and PRODUCT_ID in ("+adminPid+") and COMPANY_ID not in (2) and branch_code in("+branch+") order by product_id";*/
			sql = "select PRODUCT_ID,PRODUCT_NAME,COMPANY_ID from product_master where status='Y' and COMPANY_ID not in (2) and branch_code in("+branch+") order by product_id";
		String res[][] = new String[0][0];
		res = runner.multipleSelection(sql);
		return res;
	}

	public HashMap getBrokerUserDetails(String bra)
	{
        String query = "SELECT CURRENCY_ABBREVIATION,ORIGINATION_COUNTRY_ID,DESTINATION_COUNTRY_ID,CURRENCY_ABBREVIATION,DECIMAL_PLACES,nvl(tax,'0'),email from BRANCH_MASTER where BRANCH_CODE='"+bra+"' ";
        String result[][] = new String[0][0];
		result = runner.multipleSelection(query);
		HashMap broDetails = new HashMap();
		if(result.length>0)
		{
			broDetails.put("Branch",bra);
			broDetails.put("CurrencyName",(result[0][0]!=null?result[0][0]:"AED"));
			broDetails.put("Orgination",(result[0][1]!=null?result[0][1]:"1"));
			broDetails.put("Destination",(result[0][2]!=null?result[0][2]:"1"));
			broDetails.put("CurrencyAbb",(result[0][3]!=null?result[0][3]:"AED"));
			broDetails.put("CurrencyDecimal",(result[0][4]!=null?result[0][4]:"2"));
			broDetails.put("Tax",(result[0][5]!=null?result[0][5]:"0"));
			broDetails.put("Site",(result[0][6]!=null?result[0][6]:"0"));
		}
        return broDetails;
    }
	//For World Common End

	/** AdminCreation With Dynamic Link, Branch and Product - End  **/


	/** Menu Creation **/

	public boolean validateMenuName() 
	{
		if (menuName.equalsIgnoreCase(""))
			return true;
		return false;
	}

	public boolean newMenuInsertion(String loginBranch) 
	{
		String categoryDetail_id = "";
		String args[] = new String[0];
		try 
		{
			args = new String[1];
			args[0] = loginBranch;
			String sql = "select max(category_detail_id)+1 from constant_detail where category_id='41' and branch_code=?";
			categoryDetail_id = runner.singleSelection(sql,args);
			String sql1 = "insert into constant_detail(CATEGORY_ID,CATEGORY_DETAIL_ID,DETAIL_NAME,REMARKS,STATUS,BRANCH_CODE) values(?,?,?,?,?,?)";
			args = new String[6];
			args[0] = "41";
			args[1] = ""+Integer.parseInt(categoryDetail_id);
			args[2] = menuName;
			args[3] = menuRemarksTxt;
			args[4] = menuStatus;
			args[5] = loginBranch;
			
			runner.multipleInsertion(sql1,args);
		} 
		catch (Exception ec) 
		{
			System.out.println("Error in newMenuInsertion values" + ec);
			ec.printStackTrace();
		}
		return true;
	} 

    //
    public String[][] getCountryList()
    {
        String sql = ""; 
		String res[][] = new String[0][0];
		try
		{
			sql = "select distinct COUNTRY_NAME,COUNTRY_ID from COUNTRY_MASTER";
			res = runner.multipleSelection(sql);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return res;      
    }	

    public String validateBank(String bankId,String countryId)
	{
        String sql = "";
        String res[][] = new String[0][0];
        String result = "";
		String args[] = new String[2];
		try
		{
			args[0] = bankId;
			args[1] = countryId;
			sql = "select max(amend_id+1) from OPEN_COVER_BANK_MASTER where bank_id=? and  BELONGING_COUNTRY_ID = ?";
			res = runner.multipleSelection(sql,args);
    
			if(res != null && res.length > 0)
			{
				/*if(res[0][0] == null ||  res[0][0].equalsIgnoreCase("1"))
				{*/
					args[0] = bankId;
					args[1] = countryId;
					sql = "select BANK_NAME from OPEN_COVER_BANK_MASTER where bank_id = ? and  BELONGING_COUNTRY_ID = ?";
					res = runner.multipleSelection(sql,args);
					if(res.length > 0 )
					   result = "<br>" + "Bank Id is already Exist";
				/*}
				else
				{
				   //  result = "<br>" + "Bank Name already Exist";
				}*/
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
    }

	public String getLoginProducts(String loginPerson, String loginBranch)
	{
		String sql ="";
		String products ="";	 
		String args[] = new String[2];
		try
		{
			args[0] = loginPerson;
			args[1] = loginBranch;
			 sql = "select product_id from login_master where login_id=? and branch_code=? and status='Y' ";
			 products = runner.singleSelection(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("Exception is getLoginProducts ..."+ e.toString());
			e.printStackTrace();
		}
		System.out.println("getLoginProducts .." + products);
		return products;
	}
	

	public String[][] getAdminTypes(String branch,String pdt) 
	{
		String[][] userType = new String[0][0];
		String sql ="";
		String args[] = new String[1];
		try
		{
			args[0] = branch;
			if(pdt.equalsIgnoreCase("marine"))
				sql = "select category_detail_id,detail_name from constant_detail where category_id=40 and status='Y' and branch_code=? and category_detail_id in('2','3','4')";
			else if(pdt.equalsIgnoreCase("home"))
				sql = "select category_detail_id,detail_name from constant_detail where category_id=40 and status='Y' and branch_code=? and category_detail_id not in('2','3','4')";
			else if(pdt.equalsIgnoreCase("both"))
				sql = "select category_detail_id,detail_name from constant_detail where category_id=40 and status='Y' and branch_code=?";
			
			userType = runner.multipleSelection(sql,args);
		}
		catch (Exception e) 
		{
			System.out.println("Exception in getting user type" + e.toString());
			e.printStackTrace();
		}
		return userType;
	}

	public String[][] getAdminBrokers(String branch)
	{
		String brokers[][] = new String[0][0];
		String sql = "";
		try
		{
			sql="select bcm.company_name,pi.login_id,bcm.agency_code,pi.oa_code from BROKER_COMPANY_MASTER bcm,personal_info pi where bcm.agency_code=pi.agency_code and bcm.status='Y' and pi.status=bcm.status and APPLICATION_ID='2' and pi.login_id in(select login_id from login_master where oa_code in(select agency_code from broker_company_master where branch_code in('"+branch+"'))) order by lower(bcm.COMPANY_NAME)";
			brokers = runner.multipleSelection(sql);
		}
		catch(Exception e)
		{
			System.out.println("getAdmin Brokers ..."+e.toString());
			e.printStackTrace();
		}
		return brokers;
	}

	public String getBranchName(String branchCode)
	{
		String sql = "";
		String branchName = "";
		String args[] = new String[1];
		try
		{
			args[0] = branchCode;
			sql = "select branch_name from branch_master where branch_code=? and status='Y'";
			branchName = runner.singleSelection(sql,args);
		}
		catch(Exception E)
		{
			System.out.println("Get Admin Branch Name "+E.toString());
			E.printStackTrace();
		}
		return branchName;
	}

	public String[][] getLoginMenus(String loginPerson)
	{
		String sql = "";
		String menu="";
		String menus[][] = new String[0][0];
		String args[] = new String[1];
		try
		{
			args[0] = loginPerson;
			sql ="select menu_id from login_master where login_id=?";
			menu = runner.singleSelection(sql,args);
			System.out.println("Menu getLoginMenus "+ menu);
		}
		catch(Exception e)
		{
			System.out.println("getLoginMenus " +e.toString());
			e.printStackTrace();
		}
		try
		{
			if(menu.length() >0)
			{ 
				int i=0;
				int count=0;
				StringTokenizer st = new StringTokenizer(menu,",");
				count = st.countTokens();
				menus = new String[count][1];
				while(st.hasMoreElements())
				{
					menus[i++][0] = st.nextToken();
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("getLoginMenus."+e.toString());
			e.printStackTrace();
		}
		System.out.println("Menu getLoginMenus.length"+ menus.length);
		return menus;
	}

	public String[][] getLoginProducts(String loginPerson)
	{
		String sql = "";
		String prod="";
		String products[][] = new String[0][0];
		String args[] = new String[1];
		try
		{
			args[0] = loginPerson;
			sql ="select product_id from login_master where login_id=?";
			prod = runner.singleSelection(sql,args);
			System.out.println("Menu getLoginProducts "+ prod);
		}
		catch(Exception e)
		{
			System.out.println("getLoginProducts " +e.toString());
			e.printStackTrace();
		}
		try
		{
			if(prod.length() >0)
			{ 
				int i=0;
				StringTokenizer st = new StringTokenizer(prod,",");
				int count=0;
				count = st.countTokens();
				products = new String[count][1];
				while(st.hasMoreElements())
				{
					products[i++][0] = st.nextToken();
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("getLoginProducts"+e.toString());
			e.printStackTrace();
		}
		System.out.println("getLoginProducts products.length "+products.length);
		return products;
	}

	public String getUserLoginMenuType(String loginId,String branch)
	{
		String type = "";
		String sql = "";
		String menus = "";
		String temp = "";
		String args[] = new String[2];
		try
		{
			args[0] = loginId;
			args[1] = branch;
			sql = "select menu_id from login_master where login_id=? and branch_code=? ";
			menus = runner.singleSelection(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("get User Login Menu Type"+e.toString());
			e.printStackTrace();
		}

		try
		{
			if(menus.length() >0)
			{
				StringTokenizer st = new StringTokenizer(menus,",");
				while(st.hasMoreElements())
				{
					temp = st.nextToken();
					if( temp.equalsIgnoreCase("3") || temp.equalsIgnoreCase("10") ) 
					{
						type = "Marine";
						break;
					}
					else if( temp.equalsIgnoreCase("17")  || temp.equalsIgnoreCase("19")) 
					{
						type = "Home";
						break;
					}
					else
						type = "Nomenu";
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("get User Login Menu Type 2"+e.toString());
			e.printStackTrace();
		}
		System.out.println("get User Login Menu Type"+type);
		return type;
	}

	public String[][] getMarineProducts(String adminBranch,String loginProIds) 
	{
		String[][] products = new String[0][0];
		String sql = ""; 
		try 
		{
			sql = "select PRODUCT_ID,PRODUCT_NAME,COMPANY_ID from product_master where status='Y' and branch_code in("+adminBranch+") and PRODUCT_ID in("+loginProIds+")";// and PRODUCT_ID not in(select PRODUCT_ID from home_product_master where status='Y')";
			products = runner.multipleSelection(sql);
		}
		catch (Exception e) 
		{
			System.out.println("Exception in getting products" + e.toString());
			e.printStackTrace();
		}
		return products;
	}

	public boolean specialCharacterValidation(String value) // alphabets and number allowed
	{
		boolean flag=true;
		String invalidChar=null;
		try
		{
			value=value.trim();
			if(value.length()>0 && value!=null )
			{
				invalidChar="~!@#$%^&*()_+={}|";
				for(int i=0;i<value.length();i++)
				{
					if(invalidChar.indexOf(value.charAt(i))!= -1)
					{
						flag = false;
						break;
					}
				}
			}
			else
				return flag;
		}
		catch(Exception e)
		{
			System.out.println("specialCharacterValidation in Customer Info.java1 "+value);
			return flag;
		}
		System.out.println("Valid specialCharacterValidation in   "+ flag);
		return flag;
	}

	public boolean validPhoneMobile(String value) // Only Allowed Numbers
	{
		boolean flag=true;
		String validChar=null;
		try
		{
			value=value.trim();
			if(value.length()>0 && value!=null )
			{
				validChar="1234567890";
				for(int i=0;i<value.length();i++)
				{
					if(validChar.indexOf(value.charAt(i))== -1)
					{
						flag = false;
						break;
					}
				}
			}
			else
				return flag;
		}
		catch(Exception e)
		{
			System.out.println("Valid Phone Mobile in CustomerInfo.java1 "+value);
			return flag;
		}
		System.out.println("Valid Phone Mobile in CustomerInfo Java  "+ flag);
		return flag;
	}

	public boolean specialCharacterNumberValidation(String value) // SpecialChar and Number Not Allowed
	{
		boolean flag=true;
		String invalidChar=null;
		try
		{
			value=value.trim();
			if(value.length()>0 && value!=null )
			{
				invalidChar="1234567890~!@#$%^&*()_+=<>:;[]?{}|";
				for(int i=0;i<value.length();i++)
				{
					if(invalidChar.indexOf(value.charAt(i))!= -1)
					{
						flag = false;
						break;
					}
				}
			}
			else
				return flag;
		}
		catch(Exception e)
		{
			System.out.println("specialCharacterNumberValidation in customerInfo.java1 "+value);
			return flag;
		}
		System.out.println("specialCharacterNumberValidation  "+ flag);
		return flag;
	}

	public boolean addressValidation(String value) // SpecialChar and Number Not Allowed
	{
		boolean flag=true;
		String invalidChar=null;
		try
		{
			value=value.trim();
			if(value.length()>0 && value!=null )
			{
				invalidChar="~!@$%^<>&*()_+={};:?[]|";
				for(int i=0;i<value.length();i++)
				{
					if(invalidChar.indexOf(value.charAt(i))!= -1)
					{
						flag = false;
						break;
					}
				}
			}
			else
				return flag;
		}
		catch(Exception e)
		{
			System.out.println("addressValidation in customerInfo.java1 "+value);
			return flag;
		}
		System.out.println("addressValidation  "+ flag);
		return flag;
	}
	
	public String[][] getOfficeProductScheme(String branch)
	{
		String sql = "";
		String officeScheme[][] = new String[0][0];
		String args[] = new String[1];
		try
		{
			branch = branch.replaceAll("'","");
			args[0] = branch;
			sql = "select s.scheme_id,(p.product_name||'-'||s.scheme_name) from product_master p,OFS_SCHEME_MASTER s where p.status=s.status and s.status='Y' and p.branch_code=s.branch_code and s.branch_code=? and s.product_id=p.product_id order by s.scheme_id";
			officeScheme = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return officeScheme;
	}

	public String[][] getCommisionDataForOffice(String bcode,String proId) 
	{
		String[][] commisionDetails = new String[0][0];
		String sql = "" ;
		String args[] = new String[2];
		try 
		{
			args[0] = bcode;
			args[1] = proId;
			sql =  "select lud.PRODUCT_ID,nvl(lud.scheme_id,'0'),lud.COMMISSION,lud.INSURANCE_END_LIMIT,lm.USER_ID_CREATION,lm.AC_EXECUTIVE_CREATION,lm.REFERAL,lud.status,nvl(lud.DISCOUNT_OF_PREMIUM,0),lud.MIN_PREMIUM_AMOUNT,lud.BACK_DATE_ALLOWED,nvl(lud.LOADING_OF_PREMIUM,0) from  login_master lm,login_user_details lud where lm.agency_code=lud.agency_code and lud.agency_code=? and lud.product_id=? and lud.status='Y' order by lud.product_id";
		
			commisionDetails = runner.multipleSelection(sql,args);
		} 
		catch (Exception e) 
		{
			System.out.println("Exception in getCommisionDataForOffice "+ e.toString());
			e.printStackTrace();
		}
		return commisionDetails;
	}
	
	public String validateCommisionOffice(String adminBranch) 
	{
		adminBranch = adminBranch.replaceAll("'","");
		com.maan.common.util.dataCollection data = new com.maan.common.util.dataCollection();
		String error = "";
		String offTemp = "";
		String office = "";
		String schemeId = "";
		HashMap pro = new HashMap();
		
		if (proDetails.size() > 0) 
		{
			for (int i = -1; i < proDetails.size(); i++) 
			{
				pro = (HashMap) proDetails.get("productsdetails"+(i+1));
				
				if (pro == null) 
				{
					i++;
					continue;
				}
				String values = "";
				
				offTemp = (String)pro.get("product"+(i+1));
				offTemp = offTemp==null?"":offTemp;
				if(offTemp.length() >= 3 && !offTemp.equalsIgnoreCase("null"))
				{
					office = offTemp.substring(0,offTemp.length()-1);
					schemeId = offTemp.substring(offTemp.length()-1,offTemp.length());
				}
				else
				{
					office = offTemp;schemeId = "";
				}
				
				String schemeName = "OFFICE SHIELD - ";
				schemeName = schemeName+getSchmeById(schemeId);
				values = data.validString((String) pro.get("commision"+ (i + 1)), true);
				if ("needed".equalsIgnoreCase(values))
				{
					error = error+ "<br>* Enter Valid Commmision for Scheme-->"+ schemeName;
				}
				else if ("Invalid".equalsIgnoreCase(values))
					error = error+ "<br>* Enter Valid Commmision for Scheme-->"+ schemeName;
				
				else if (Double.parseDouble((String) pro.get("commision"+(i+1))) > 100)
					error = error+"<br>* Commmision for Scheme  "+schemeName+" Should Not Exceed 100%";
				
				
				values = data.validString((String) pro.get("suminsured"+(i+1)), true);
				if ("needed".equalsIgnoreCase(values))
					error = error+"<br>* Enter Valid suminsured for Scheme-->  "+schemeName;
				else if ("Invalid".equalsIgnoreCase(values))
					error = error+"<br>* Enter Valid suminsured for Scheme-->  "+schemeName;
				else if (((String) pro.get("suminsured" + (i + 1))).equalsIgnoreCase("0"))
				{
					error = error+"<br>* Suminsured Insured Should Not Be Zero for Scheme   "+schemeName;
				}
				values = data.validString((String) pro.get("premium"+(i+1)), true);
				if ("needed".equalsIgnoreCase(values))
					error = error+"<br>* Enter Valid premium for Scheme-->  "+schemeName;
				else if ("Invalid".equalsIgnoreCase(values))
					error = error+"<br>* Enter Valid premium for Scheme-->  "+schemeName;
				else if (((String) pro.get("premium" + (i + 1))).equalsIgnoreCase("0")) 
				{
					error = error+"<br>* Premium Insured Should Not Be Zero for Scheme-->  "+schemeName;
				}

				values = data.validString((String) pro.get("loading"+(i+1)),true);
				if ("needed".equalsIgnoreCase(values))
					error = error+ "<br>** Enter Valid Loading % for Scheme-->"+schemeName;
				else if ("Invalid".equalsIgnoreCase(values))
					error = error+ "<br>* Enter Valid Loading % for Scheme-->"+schemeName;

				values = data.validString((String) pro.get("discount"+(i+1)),true);
				if ("needed".equalsIgnoreCase(values))
					error = error+ "<br>* Enter Valid Discount % for Scheme-->"+schemeName;
				else if ("Invalid".equalsIgnoreCase(values))
					error = error+ "<br>* Enter Valid Discount % for Scheme-->"+schemeName;

				values = data.validString((String) pro.get("bday" + (i+1)), true);
				if("needed".equalsIgnoreCase(values))
					error = error+"<br>* Enter Valid Back Days Allowed for Scheme-->  "+schemeName;
				else if ("Invalid".equalsIgnoreCase(values))
					error = error+"<br>* Enter Valid Back Days Allowed for Scheme-->  "+schemeName;
			}
		} 
		else 
		{
			error = error + "<BR>* Please select at least one Scheme";
		}
		return error;
	}
	public String[][] getEditRSABrokerProductDetails(String loginId)
	{
		String sql = "select broker_code,product_id,commission,to_char(START_DATE,'dd'),to_char(START_DATE,'MM'),to_char(START_DATE,'YYYY'),to_char(END_DATE,'dd'),to_char(END_DATE,'MM'),to_char(END_DATE,'YYYY'),status,to_char(CANCEL_DATE,'dd'),to_char(CANCEL_DATE,'MM'),to_char(CANCEL_DATE,'YYYY') from LOGIN_RSAUSER_DETAILS where login_id=? and (amend_id||broker_code||product_id) in(select max(amend_id)||broker_code||product_id from LOGIN_RSAUSER_DETAILS where login_id=? group by broker_code,product_id) order by broker_Code,product_id";
		String args[] = new String[2];
		String result[][]=new String[0][0];
		try
		{
			args[0] = loginId;
			args[1] = loginId;
			result = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	public String[][] getRSABrokerProductDetails(String adminPids,String adminBrokerCode,String adminBranch)
	{
		
		String condition = "";
		if(adminBrokerCode.length()>0)
			condition = " and bcm.agency_Code in("+adminBrokerCode+")";
		String sql = "select bcm.company_name,pro.product_name,bcm.agency_Code,pro.product_id,lud.login_id from broker_company_master bcm,product_master pro,login_user_details lud where lud.product_id=pro.product_id and bcm.agency_code=lud.agency_Code and bcm.branch_Code=? and pro.branch_Code=? and pro.product_id in("+adminPids+")"+condition+" group by bcm.company_name,pro.product_name,bcm.agency_Code,pro.product_id,lud.login_id order by bcm.company_name, pro.product_id";
		String args[] = new String[2];
		String result[][]=new String[0][0];
		try
		{
			args[0] = adminBranch;
			args[1] = adminBranch;
			result = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	public String validateRSAIssuer(String row,String mode)
	{
		com.maan.services.util.dataCollection data=new com.maan.services.util.dataCollection();
		com.maan.services.util.ValidationFormat valid = new com.maan.services.util.ValidationFormat();
		String error="";
		String values=null;
		String values1=null;
		try
		{
			long diff1=0;
			long diff=0;
			if(	commission.length()>0)
			{
				if(!valid.isNumberValue(commission))
					error=error+"<br>* Invalid commission percentage for row "+row;
				
				values1=data.checkDate(deactiveDay+"/"+deactiveMonth+"/"+deactiveYear);
				values1 = values1==null?"Valid":values1;
				DateFunction dbr=new DateFunction();
				values=data.checkDate(startDay+"/"+startMonth+"/"+startYear);
				
				if("Invalid".equalsIgnoreCase(values))
					error=error+"<br>*"+runner.getErrormsg("62")+" for row "+row;
				
				else if("Invalid".equalsIgnoreCase(values1)&&!activeStaus.equalsIgnoreCase("N")) 
				{
					  com.maan.Home.DataManipualtion.DataSelect	dataSelect = new com.maan.Home.DataManipualtion.DataSelect();
					  Calendar c1 = Calendar.getInstance();
                      c1.set(Integer.parseInt(startYear),Integer.parseInt(startMonth),Integer.parseInt(startDay));
                      Calendar c2 = Calendar.getInstance();
				      String[][] serverDate =  dataSelect.getTodaysDate();
			           c2.set(Integer.parseInt(serverDate[0][0]),Integer.parseInt(serverDate[0][1]),Integer.parseInt(serverDate[0][2]));
                      diff = 0;
                      try
					  {
                           diff = dbr.getDayDifference(c1,c2);
                           System.out.println("Date Difference  "+diff);
                      }
                     catch(Exception ex)
					 {
                           ex.printStackTrace();
                     }
					if(diff>0)
					{
						error=error+"<br>* Start Date cannot be less than todays Date for row "+row;
					}
				}
				values=data.checkDate(endDay+"/"+endMonth+"/"+endYear);
				if("Invalid".equalsIgnoreCase(values))
					error=error+"<br>*"+runner.getErrormsg("63")+" for row "+row;
				 else if(Integer.parseInt(endYear)<Integer.parseInt(startYear))
				 {
					error=error+"<br>*"+runner.getErrormsg("72")+" for row "+row;
				 }
				 else if(Integer.parseInt(endYear)==Integer.parseInt(startYear))
				 {
					if(Integer.parseInt(endMonth)<Integer.parseInt(startMonth))
					{
					 error=error+"<br>*"+runner.getErrormsg("72")+" for row "+row;
					}
					else if(Integer.parseInt(endMonth)==Integer.parseInt(startMonth))
					{
						if(Integer.parseInt(endDay)<Integer.parseInt(startDay))
						{
						 error=error+"<br>*"+runner.getErrormsg("72")+" for row "+row;
						}
					}
				 }
				SimpleDateFormat simpleFormatter = new SimpleDateFormat("dd/MM/yyyy");
				simpleFormatter.setTimeZone(new java.util.SimpleTimeZone(14400000,"GMT"));
				String s = simpleFormatter.format(new java.util.Date());
				String enteredDate1=endDay+"/"+endMonth+"/"+endYear;
				String enteredDate=startDay+"/"+startMonth+"/"+startYear;
				
				String lastendDate = "";
				
				if(mode.equalsIgnoreCase("edit")&&"Invalid".equalsIgnoreCase(values1)&&!activeStaus.equalsIgnoreCase("N"))
				{
					try
					{
						String args[] = new String[6];
						args[0] = login_Id;
						args[1] = login_Id;
						args[2] = brokerIds;
						args[3] = pid;
						args[4] = brokerIds;
						args[5] = pid;
						lastendDate = runner.singleSelection("select to_char(end_Date,'dd-mm-yyyy') from LOGIN_RSAUSER_DETAILS where login_id=? and START_DATE<=(select to_char(sysdate,'dd-mon-yyyy') from dual) and END_DATE>=(select to_char(sysdate,'dd-mon-yyyy') from dual) and amend_id=(select max(amend_id) from LOGIN_RSAUSER_DETAILS where login_id=? and START_DATE<=(select to_char(sysdate,'dd-mon-yyyy') from dual) and END_DATE>=(select to_char(sysdate,'dd-mon-yyyy') from dual) and broker_code=? and product_id=?) and broker_code=? and status='Y' and product_id=?",args);

						if(lastendDate.length()>0)
							diff=dbr.getDayDifference(dbr.getCalendar(lastendDate),dbr.getCalendar(enteredDate));
						else
							diff = 1;
						
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
					if(diff<=0)
					{
						error=error+"<br>* Start Date should be greater than Last End Date("+lastendDate+") for row "+row;
					}
				}
				values = values1;
				/*if("Invalid".equalsIgnoreCase(values)&&activeStaus.equalsIgnoreCase("N"))
				{
					error=error+"<br>* Please select deactive date for row "+row+" to deactivate the provision";
				}
				else if(!activeStaus.equalsIgnoreCase("N")&&"Valid".equalsIgnoreCase(values))
					error=error+"<br>* Please select Deactive Status as Yes for row "+row+" to deactivate the provision";
				else if(activeStaus.equalsIgnoreCase("N")&&"Valid".equalsIgnoreCase(values))
				{
					com.maan.Home.DataManipualtion.DataSelect 	dataSelect	=	new com.maan.Home.DataManipualtion.DataSelect();
					  Calendar c1 = Calendar.getInstance();
                      c1.set(Integer.parseInt(deactiveYear),Integer.parseInt(deactiveMonth),Integer.parseInt(deactiveDay));
                      Calendar c2 = Calendar.getInstance();
				      String[][] serverDate =  dataSelect.getTodaysDate();
			           c2.set(Integer.parseInt(serverDate[0][0]),Integer.parseInt(serverDate[0][1]),Integer.parseInt(serverDate[0][2]));
                      diff = 0;
                      try
					  {
                           diff = dbr.getDayDifference(c1,c2);
                           System.out.println("Date Difference  "+diff);
                      }
                     catch(Exception ex)
					 {
                           ex.printStackTrace();
                     }
					if(diff>=0)
					{
						error=error+"<br>* Deactive date should be greater than todays date for row "+row+" to deactivate the provision";
					}
					 c2.set(Integer.parseInt(endYear),Integer.parseInt(endMonth),Integer.parseInt(endDay));
					  diff = 0;
                      try
					  {
                           diff = dbr.getDayDifference(c1,c2);
                           System.out.println("Date Difference  "+diff);
                      }
                     catch(Exception ex)
					 {
                           ex.printStackTrace();
                     }
					if(diff<0)
					{
						error=error+"<br>* Deactive date should be less than End date for row "+row+" to deactivate the provision";
					}
				}*/
			}
			else 
				error = error+"<br>* Please Enter Commission Percentage for row "+row;
		}
		catch (Exception e)
		{
			System.out.println("Exception in "+e.toString());
		}
		
		return error;		
	}
	public void insertRSAIssuer(String mode) 
	{
		
		String args[] = new String[0];
		String sql1 = "";
		System.out.println("Product[0][1]::>"+productId[0]+productId[1]);
		if(!mode.equalsIgnoreCase("edit"))
		{
			try
			{
				sql1 = "insert into login_master(LOGIN_ID,PASSWORD,USERTYPE,USERNAME,USERID,AGENCY_CODE,OA_CODE,COMPANY_ID,CREATED_BY,STATUS,USER_ID_CREATION,AC_EXECUTIVE_CREATION,ACCESSTYPE,PASSDATE,BRANCH_CODE,COUNTRY_ID,CORE_LOGIN_ID) values(?,?,?,?,?,?,?,?,?,?,?,?,?,(select sysdate from dual),?,?,?)";
				
				passwordEnc pass = new passwordEnc();
				String encpass = pass.crypt(user_Pass.substring(0, 3), user_Pass);
				boolean check = false;
				args = new String[16];
				args[0] = login_Id;
				args[1] = encpass;
				args[2] = "RSAIssuer";
				args[3] = user_Name;
				args[4] = "8";
				args[5] = login_Id;
				args[6] = login_Id;
				args[7] = "1";
				args[8] = "Admin";
				args[9] = "Y";
				args[10] = "Y";
				args[11] = "Y";
				args[12] = "BOTH";
				args[13] = User_Branch;
				args[14] = loginCountry;
				args[15] = coreLoginId;
				

				runner.multipleInsertion(sql1,args);
			} 
			catch (Exception ec) 
			{
				System.out.println(" Login Master Insert Query is ..."+ sql1);
				System.out.println("Error in inserting values" + ec.toString());
				ec.printStackTrace();
				
			}
			int customerid = 0;
			try
			{
				customerid = Integer.parseInt(getMaxCustomerId(User_Branch));
			}
			 catch(Exception e)
			{
				 System.out.println("Exception getting max in"+e.toString());
				 e.printStackTrace();
			}
			try
			{
				sql1 = "insert into PERSONAL_INFO(CUSTOMER_ID,APPLICATION_ID,FIRST_NAME,EMAIL,STATUS,LOGIN_ID,AGENCY_CODE,OA_CODE,amend_id) values(?,?,?,?,'Y',?,?,?,'1')";
				args = new String[7];
				args[0] = ""+customerid;
				args[1] = "8";
				args[2] = user_Name;
				args[3] = email_id;
				args[4] = login_Id;
				args[5] = login_Id;
				args[6] = login_Id;
				runner.multipleInsertion(sql1,args);
			} 
			catch (Exception ec) 
			{
				System.out.println(" Login Master Insert Query is ..."+ sql1);
				System.out.println("Error in inserting values" + ec.toString());
				ec.printStackTrace();
				
			}
			try
			{
				String count1 = runner.singleSelection("select count(*) from login_user_details where LOGIN_ID='"+login_Id+"' and product_id=3");
				String count2 = runner.singleSelection("select count(*) from login_user_details where LOGIN_ID='"+login_Id+"' and product_id=11");
				
				sql1 = "insert into login_user_details( LOGIN_ID, USER_NAME, USER_ID,AGENCY_CODE,OA_CODE,COMPANY_ID,PRODUCT_ID,AMEND_ID,status ) values(?,?,?,?,?,?,?,?,?)";
			   
				passwordEnc pass = new passwordEnc();
				String userId = runner.singleSelection("select nvl(max(user_id)+1,1) from login_user_details ");
				args = new String[9];
				args[0] = login_Id;
				args[1] = user_Name;
				args[2] = userId;
				args[3] = login_Id;
				args[4] = login_Id;
				args[5] = "1";
				args[6] = productId[0];
				args[7] = "0";
				args[8] = "Y";
				if(!(productId[0].equalsIgnoreCase("")) && (count1.equalsIgnoreCase("0"))){
					runner.multipleInsertion(sql1,args);
				}				
				userId = runner.singleSelection("select nvl(max(user_id)+1,1) from login_user_details ");
				args[2] = userId;
				args[6] = productId[1];
				if(!(productId[1].equalsIgnoreCase("")) && (count2.equalsIgnoreCase("0"))){
					runner.multipleInsertion(sql1,args);
				}
			} 
			catch (Exception ec) 
			{
				System.out.println(" Login Master Insert Query is ..."+ sql1);
				System.out.println("Error in inserting values" + ec.toString());
				ec.printStackTrace();
				
			}
			
		}	
		else
		{
			try
			{
				args = new String[2];
				sql1 = "update login_master set USERNAME=? where login_id=?";
				args[0] = user_Name; 
				args[1] = login_Id;
				runner.multipleUpdation(sql1,args);
			}
			catch(Exception e)
			{
				System.out.println("Login Master Update Query is ..."+sql1);
				System.out.println("Error while updating the values..."+e.toString());
				e.printStackTrace();
			}
			try
			{
				args = new String[3];
				sql1 = "update PERSONAL_INFO set FIRST_NAME=?,EMAIL=? where login_id=?";
				args[0] = user_Name; 
				args[1] = email_id; 
				args[2] = login_Id;
				runner.multipleUpdation(sql1,args);
			}
			catch(Exception e)
			{
				System.out.println("Login Master Update Query is ..."+sql1);
				System.out.println("Error while updating the values..."+e.toString());
				e.printStackTrace();
			}
			try
			{
				args = new String[5];
				String status="";
				String count1 = runner.singleSelection("select count(*) from login_user_details where LOGIN_ID='"+login_Id+"' and product_id=3");
				String count2 = runner.singleSelection("select count(*) from login_user_details where LOGIN_ID='"+login_Id+"' and product_id=11");
				sql1 = "update login_user_details set USER_NAME=? , product_id =? ,status=? where login_id=? and product_id = ?";
				
				if(!(productId[0].equalsIgnoreCase("")) && !(count1.equalsIgnoreCase("0")))
				{
					productId[0]="3";
					status= "Y";
					args[0] = user_Name; 
					args[1] = productId[0];
					args[2] = status;
					args[3] = login_Id;
					args[4] = productId[0];
					runner.multipleUpdation(sql1,args);
				}
				else if(productId[0].equalsIgnoreCase(""))
				{
					productId[0]="3";
					status= "N";
					args[0] = user_Name; 
					args[1] = productId[0];
					args[2] = status;
					args[3] = login_Id;
					args[4] = productId[0];
					runner.multipleUpdation(sql1,args);
				}
				if(!(productId[1].equalsIgnoreCase("")) && !(count2.equalsIgnoreCase("0")))
				{
					productId[1]="11";
					status= "Y";
					args[0] = user_Name; 
					args[1] = productId[1];
					args[2] = status;
					args[3] = login_Id;
					args[4] = productId[1];
					runner.multipleUpdation(sql1,args);
				}
				
				else if(productId[1].equalsIgnoreCase(""))
				{
					productId[1]="11";
					status= "N";
					args[0] = user_Name; 
					args[1] = productId[1];
					args[2] = status;
					args[3] = login_Id;
					args[4] = productId[1];
					runner.multipleUpdation(sql1,args);
					
				}
				
				try
				{
					//sql1 = "insert into login_user_details( LOGIN_ID, USER_NAME, USER_ID,AGENCY_CODE,OA_CODE,COMPANY_ID,PRODUCT_ID,AMEND_ID,status ) values(?,?,?,?,?,?,?,?,?)";
				   
					passwordEnc pass = new passwordEnc();
					String userId = runner.singleSelection("select nvl(max(user_id)+1,1) from login_user_details ");
					String args1[] = new String[9];
					args1[0] = login_Id;
					args1[1] = user_Name;
					args1[2] = userId;
					args1[3] = login_Id;
					args1[4] = login_Id;
					args1[5] = "1";
					args1[6] = productId[0];
					args1[7] = "0";
					args1[8] = "Y";
					if(!(productId[0].equalsIgnoreCase("")) && (count1.equalsIgnoreCase("0"))){
						sql1 = "insert into login_user_details( LOGIN_ID, USER_NAME, USER_ID,AGENCY_CODE,OA_CODE,COMPANY_ID,PRODUCT_ID,AMEND_ID,status ) values(?,?,?,?,?,?,?,?,?)";
						 
						runner.multipleInsertion(sql1,args1);
					}				
					userId = runner.singleSelection("select nvl(max(user_id)+1,1) from login_user_details ");
					args1[2] = userId;
					args1[6] = productId[1];
					if(!(productId[1].equalsIgnoreCase("")) && (count2.equalsIgnoreCase("0"))){
						sql1 = "insert into login_user_details( LOGIN_ID, USER_NAME, USER_ID,AGENCY_CODE,OA_CODE,COMPANY_ID,PRODUCT_ID,AMEND_ID,status ) values(?,?,?,?,?,?,?,?,?)";
						 
						runner.multipleInsertion(sql1,args1);
					}
					
				} 
				catch (Exception ec) 
				{
					System.out.println(" Login Master Insert Query is ..."+ sql1);
					System.out.println("Error in inserting values" + ec.toString());
					ec.printStackTrace();
					
				}
				
			}
			catch(Exception e)
			{
				System.out.println("Login Master Update Query is ..."+sql1);
				System.out.println("Error while updating the values..."+e.toString());
				e.printStackTrace();
			}
		}
	}
	public int getMaxAmendId()
	{
		String sql = "select max(amend_id) from LOGIN_RSAUSER_DETAILS where login_id=? and broker_code=? and product_id=?";
		int maxId = 0;
		try
		{
			String args[] = new String[3];
			args[0] = login_Id;
			args[1] = brokerIds;
			args[2] = pid;
			String temp = runner.singleSelection(sql,args);
			if(temp!=null)
			{
				if(temp.length()>=1)
					maxId = Integer.parseInt(temp);
			}
			maxId = maxId + 1;
		}
		catch(Exception e1)  
		{
			System.out.println("Exception in get MaxAmendId..."+e1);
			e1.printStackTrace();maxId=0;
		}
		return maxId;

	}
	public void insertRSAIssuerDetails()
	{
		String sql ="";
		int amendId = getMaxAmendId();
	
		if(activeStaus.equalsIgnoreCase("Y"))
		{
			sql = "update LOGIN_RSAUSER_DETAILS set status='N' where login_id=? and broker_code=? and PRODUCT_ID=? and BRANCH_CODE=? and amend_id=?";
			try
			{
				String args[] = new String[5];
				args[0] = login_Id;
				args[1] = brokerIds;
				args[2] = pid;
				args[3] = User_Branch;
				args[4] = ""+(amendId-1);
				runner.multipleUpdation(sql,args);
			}
			catch(Exception e1)  
			{
				System.out.println("Exception in update Pro Commission Details..."+e1);
				e1.printStackTrace();
			}
			sql = "insert into LOGIN_RSAUSER_DETAILS(LOGIN_ID,BROKER_CODE,PRODUCT_ID,AMEND_ID,BRANCH_CODE,START_DATE,END_DATE,ENTRY_DATE,COMMISSION,STATUS,OPEN_COVER_NO)  values(?,?,?,?,?,to_date(?,'dd-mm-yyyy'),to_date(?,'dd-mm-yyyy'),(select sysdate from dual),?,'Y',?)";
			try
			{
				String args[] = new String[9];
				args[0] = login_Id;
				args[1] = brokerIds;
				args[2] = pid;
				args[3] = ""+amendId;
				args[4] = User_Branch;
				args[5] = data1;
				args[6] = data2;
				args[7] = commission;
				args[8] = openNo;
				runner.multipleInsertion(sql,args);
			}
			catch(Exception e1)  
			{
				System.out.println("Exception in insert Pro Commission Details..."+e1);
				e1.printStackTrace();
			}
		}
		else
		{
			sql = "update LOGIN_RSAUSER_DETAILS set status='N',CANCEL_DATE=trunc(sysdate),END_DATE=to_date(?,'dd-mm-yyyy')-1,ENTRY_DATE=(select sysdate from dual) where login_id=? and broker_code=? and PRODUCT_ID=? and BRANCH_CODE=? and amend_id=?";
			try
			{
				String args[] = new String[6];
				args[0] = data3;
				args[1] = login_Id;
				args[2] = brokerIds;
				args[3] = pid;
				args[4] = User_Branch;
				args[5] = ""+(amendId-1);
				runner.multipleUpdation(sql,args);
			}
			catch(Exception e1)  
			{
				System.out.println("Exception in update Pro Commission Details..."+e1);
				e1.printStackTrace();
			}
		}
	}

	private String dobDay1 = "";
	private String dobMonth1 = "";
	private String dobYear1 = "";
	private String dobDay2 = "";
	private String dobMonth2 = "";
	private String dobYear2 = "";
	private String data1 = "";
	private String data2 = "";
	private String data3 = "";
	private String openCoverNo = "";
	private String commission = "";
	private String pid = "";
	private String openNo = "";
	String activeStaus = "";
	public void setOpenNo(String openNo)
	{
		this.openNo = openNo;
	}
	public String getOpenNo()
	{
		return this.openNo;
	}
	public void setDobDay1(String dobDay1)
	{
		this.dobDay1=dobDay1;
	}
	public void setDobMonth1(String dobMonth1)
	{
		this.dobMonth1=dobMonth1;
	}
	public void setDobYear1(String dobYear1)
	{
		this.dobYear1=dobYear1;
	}
	public void setDobDay2(String dobDay2)
	{
		this.dobDay2=dobDay2;
	}
	public void setDobMonth2(String dobMonth2)
	{
		this.dobMonth2=dobMonth2;
	}
	public void setDobYear2(String dobYear2)
	{
		this.dobYear2=dobYear2;
	}
	public String getDobDay2()
	{
		return dobDay2;
	}
	public String getDobMonth2()
	{
		return dobMonth2;
	}
	public String getDobYear2()
	{
		return dobYear2;
	}
	public void setOpenCoverNo(String openCoverNo)
	{
		this.openCoverNo = openCoverNo;
	}
	public String getOpenCoverNo()
	{
		return openCoverNo;
	}
	public String getDobDay1()
	{
		return dobDay1;
	}
	public String getDobMonth1()
	{
		return dobMonth1;
	}
	public String getDobYear1()
	{
		return dobYear1;
	}
	public void setData1(String data1)
	{
		this.data1=data1;
	}
	public void setData2(String data2)
	{
		this.data2=data2;
	}
	public String getData1()
	{
		return this.data1;
	}
	public String getData2()
	{
		return this.data2;
	}
	public void setData3(String data3)
	{
		this.data3=data3;
	}
	public String getData3()
	{
		return this.data3;
	}
	public void setbrokerIds(String brokerIds)
	{
		this.brokerIds = brokerIds;
	}
	public String getbrokerIds()
	{
		return brokerIds;
	}
	public void setCommission(String commission)
	{
		this.commission = commission;
	}
	public String getCommission()
	{
		return commission;
	}
	public void setPid(String pid)
	{
		this.pid = pid;
	}
	public String getPid()
	{
		return pid;
	}
	public void setActiveStaus(String activeStaus)
	{
		this.activeStaus = activeStaus;
	}
	public String getActiveStaus()
	{
		return activeStaus;
	}
	public String[][] getRSAIssuerLogins(String branch)
	{
//		String sql = "select username,login_id from LOGIN_MASTER where usertype='RSAIssuer' and branch_code=?";
		//String sql = "SELECT A.USERNAME,A.LOGIN_ID FROM LOGIN_MASTER A, BROKER_COMPANY_MASTER B WHERE A.USERTYPE='RSAIssuer' AND B.BRANCH_CODE=? AND B.AGENCY_CODE=A.OA_CODE";
		String sql = "SELECT USERNAME,LOGIN_ID FROM LOGIN_MASTER  WHERE USERTYPE='RSAIssuer' AND BRANCH_CODE=? ";
		String args[] = new String[1];
		String result[][] = new String[0][0];
		
		try
		{
			args[0] = branch;
			result = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	public String[][] getRSAIssuerDetails(String oaCode)
	{
		String adminDetails[][] = new String[0][0];
		String sql = "";
		String args[] = new String[1];
		try
		{
			args[0] = oaCode;
//			sql = "select lm.USERTYPE,lm.USERNAME,lm.LOGIN_ID,lm.PASSWORD,lm.BRANCH_CODE,pi.email from login_master lm,personal_info pi where lm.oa_code =? and lm.status='Y' and pi.login_id=lm.login_id";
			sql = "select lm.USERTYPE,lm.USERNAME,lm.LOGIN_ID,lm.PASSWORD,lm.BRANCH_CODE,pi.email,lm.CORE_LOGIN_ID from login_master lm,personal_info pi where lm.login_id =? and lm.status='Y' and pi.login_id=lm.login_id";
			adminDetails = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("Exception getting from getAdminDeatils for edit mode "+e.toString());
			e.printStackTrace();
		}
		return adminDetails;
	}
	public String getOpenCoverCertificatesUser(String loginId,String broCode)
	{
		String openCoverNos = "";
		String openNos[][] = new String[0][0];
		try
		{
			String args[] = new String[4];
			args[0] = loginId;
			args[1] = loginId;
			args[2] = broCode;
			args[3] = broCode;
			String  opensql = "select OPEN_COVER_NO from LOGIN_RSAUSER_DETAILS where login_id=? and product_id='11' and START_DATE<=(select to_char(sysdate,'dd-mon-yyyy') from dual) and END_DATE>=(select to_char(sysdate,'dd-mon-yyyy') from dual) and amend_id=(select max(amend_id) from LOGIN_RSAUSER_DETAILS where login_id=? and product_id='11' and START_DATE<=(select to_char(sysdate,'dd-mon-yyyy') from dual) and END_DATE>=(select to_char(sysdate,'dd-mon-yyyy') from dual) and broker_code=?) and broker_code=? and status='Y'";
			openNos=runner.multipleSelection(opensql,args);
			for(int i=0;i<openNos.length;i++)
			{
				openCoverNos=openCoverNos+openNos[i][0]+",";
			}
			if(openCoverNos.length()>0)
				openCoverNos=openCoverNos.substring(0,openCoverNos.lastIndexOf(','));
				
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return openCoverNos;
	}
	 
	public String[][] getTodaysDate(String branch) {
		String query = "";
		String[][] result = new String[0][0];
		String temp = "sysdate";
		String sql = "";
		String hour = "";
		String args[] = new String[0];
		try {
			args = new String[4];
			args[0] = "62";
			args[1] = "1";
			args[2] = "Y";
			args[3] = branch;
			sql = "select DETAIL_NAME from constant_detail where category_id=? and category_detail_id=? and status=? and branch_code=?";
			hour = runner.singleSelection(sql, args);
			if (hour.length() > 0)
				temp = temp + "+" + hour;
			// query = "select extract(day from sysdate+8/24),extract(month from
			// sysdate),extract(year from sysdate) from dual";
			query = "select extract(day from "
					+ temp
					+ "),extract(month from "
					+ temp
					+ "),extract(year from "
					+ temp
					+ ") from dual";
			result = runner.multipleSelection(query);
		} catch (Exception ex) {
			System.out.println("getTodaysDate..." + ex);
			ex.printStackTrace();
		}
		return result;
	}
	public HashMap getExistingOfficeDetails(String acode)
	{
		HashMap officeDetails = new HashMap();
		String sql="select scheme_id,nvl(COMMISSION,'0'),nvl(INSURANCE_START_LIMIT,'0'),nvl(MIN_PREMIUM_AMOUNT,'0'),nvl(LOADING_OF_PREMIUM,'0'),nvl(DISCOUNT_OF_PREMIUM,'0'),nvl(BACK_DATE_ALLOWED,'0') from login_user_Details where agency_Code=? and product_id=30 and status='Y' order by scheme_id";
		String args[] = new String[1];
		String officeScheme[][] = new String[0][0];
		try
		{
			args[0] = acode;
			officeScheme = runner.multipleSelection(sql,args);
			for(int i=0; i<officeScheme.length;i++)
			{
				String schemeId = officeScheme[i][0];
				officeDetails.put("scheme"+i,"s"+schemeId);
				officeDetails.put("commision"+schemeId,officeScheme[i][1]);
				officeDetails.put("suminsured"+schemeId,officeScheme[i][2]);
				officeDetails.put("premium"+schemeId,officeScheme[i][3]);
				officeDetails.put("discount"+schemeId,officeScheme[i][5]);
				officeDetails.put("loading"+schemeId,officeScheme[i][4]);
				officeDetails.put("bday"+schemeId,officeScheme[i][6]);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return officeDetails;
	}
	public String getSchmeById(String offSchemeId)
	{
		String res= "";
		try
		{
			String args[] = new String[1];
			args[0] = offSchemeId;
			res = runner.singleSelection("select SCHEME_NAME from OFS_SCHEME_MASTER where SCHEME_ID=?",args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return res;
	}
	
	public String getUserCount(final String agencyCode, final String userType)
	{
		String res= "";
		try
		{
			final String query = "select count(login_id) from login_master where usertype='"+userType+"' " +
					"and login_id not in('NONE') and oa_code in(select oa_code from login_master where login_id in(select login_id from " +
					"login_master where agency_code in('"+agencyCode+"') and login_id not in('NONE')) " +
					"and login_id not in('NONE')) group by oa_code";
			res = runner.singleSelection(query);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return res;
	}
	
	public String commonSpecialValidation(final String borgName,final String message) {
		final StringBuffer errorBuf = new StringBuffer();
		boolean NameFlag = StringUtil.isNumberValue(borgName); // Aprl 28
		if (NameFlag && firstName.length() > 0) {
			errorBuf.append("<br>*");
			errorBuf.append("Please enter valid ");
			errorBuf.append(message);
		}
		if (!NameFlag && firstName.length() > 0) // new Aprl 28
		{
			NameFlag = StringUtil.chkOnlySpecial(borgName);
			if (NameFlag) {
				errorBuf.append("<br>*");
				errorBuf.append("Please enter valid ");
				errorBuf.append(message);
			}
		}
		return errorBuf.toString();
	}
	
	// Multi Login Creation
	public String getMultiUserCount(final String agencyCode, final String userType) // Multi Freight-Customer Login 
	{
		String res= "";
		try	{
			final String query = "select count(login_id) from login_master where usertype='"+userType+"' " +
					"and login_id not in('NONE') and fd_code in(select fd_code from login_master where login_id in (select login_id from login_master " +
					"where fd_code in('"+agencyCode+"') and login_id not in('NONE')) and login_id not in('NONE')) " +
					"and fd_code!=agency_code group by oa_code";
			System.out.println("getMultiUserCount..."+query);
			res = runner.singleSelection(query);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
	public String[][] getSubFreightCustomerInDetails(String acode, String type,String status)
	{
		String sql = "";
		String res[][] = new String[0][0];
		String args[] = new String[0];
		String masterSub = "";
		try
		{
			args = new String[2];
			args[0] = acode;
			args[1] = type;
			if(status.equalsIgnoreCase("Master")){
				masterSub = " and log.agency_code=log.fd_code";
			}else{
				masterSub = " and log.agency_code!=log.fd_code";
			}
			sql = "select nvl(per.first_name||''||per.last_name,per.company_name),per.status,log.usertype,per.application_id,per.agency_code from personal_info per,login_master log where log.fd_code=? and log.agency_code=per.agency_code and log.login_id not in('NONE') and log.usertype=? "+masterSub+" order by per.agency_code";
			res = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return res;
	}
	
	public String[][] getBrokerLoginId(String alCode, String type,String status)
	{
		String sql = "";
		String res[][] = new String[0][0];
		String args[] = new String[0];
		try
		{
			args = new String[1];
			args[0] = alCode;
			if(status.equalsIgnoreCase("agencyCode")){
				sql = "select log.login_id,bcm.company_name from login_master log,broker_company_master bcm where log.agency_code=bcm.agency_code and log.agency_code=(select oa_code from login_master where agency_code=?)";
			}
			else{
				sql = "select log.agency_code,bcm.company_name from login_master log,broker_company_master bcm where log.agency_code=bcm.agency_code and log.agency_code=(select oa_code from login_master where login_id=?)";
			}
			res = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return res;
	}
	
	public String[][] getMasterFreightName(String acode, String type)
	{
		String sql = "";
		String res[][] = new String[0][0];
		String args[] = new String[0];
		try
		{
			args = new String[1];
			args[0] = acode;
			sql = "select log.login_id,(per.first_name||per.company_name) from login_master log,personal_info per where log.agency_code=per.agency_code and log.agency_code=(select agency_code from login_master where agency_code=?)";
			res = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return res;
	}
	//For Tax related Process start from here as on 13/08/09
	public String[][] getBrokerTaxInfo(final String branchCode,final String aCode){
		logger.info("entered getBrokerTaxInfo()");
		String[][] result = new String[0][0];
		String sql = "";
		try{
			String args[] = {branchCode,aCode,branchCode,aCode};
			sql = "select nvl(POLICY_FEE_STATUS,'N'),nvl(POLICY_FEE,'0'),nvl(GOVT_TAX_STATUS,'N'),nvl(GOVT_TAX,'0'),nvl(EMERGENCY_FUND_STATUS,'N')," +
					"nvl(EMERGENCY_FUND,'0'),to_Char(EFFECTIVE_DATE,'dd-mm-yyyy'), TAX_APPLICABLE from BROKER_MASTER " +
					"where BRANCH_CODE=? and AGENCY_CODE=? and status='Y' and " +
					"AMEND_ID||AGENCY_CODE in(select max(nvl(AMEND_ID,'0'))||AGENCY_CODE from BROKER_MASTER where " +
					"BRANCH_CODE=? and AGENCY_CODE=? and status='Y' group by AGENCY_CODE)";
			result = runner.multipleSelection(sql,args);
			System.out.println("broker tax info query " + sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		logger.info("exit getBrokerTaxInfo()");
		return result;
	}
	public void brokerMasterInsert(final Map broDetails){
		logger.info("entered brokerMasterInsert()");
		try{
			String amendId = getBrokerMaxAmend(broDetails);
			if(amendId.length()>0&&!amendId.equals("0")){
				updateBrokerMaster(broDetails,amendId);
			}
			String sql;sql = "insert into BROKER_MASTER(AGENCY_CODE,BRANCH_CODE,POLICY_FEE_STATUS,GOVT_TAX_STATUS,EMERGENCY_FUND_STATUS," +
					"POLICY_FEE,GOVT_TAX,EMERGENCY_FUND,STATUS,REMARKS,AMEND_ID,TAX_APPLICABLE,ENTRY_DATE,EFFECTIVE_DATE)" +
					" values(?,?,?,?,?,?,?,?,'Y',?,?,?,(select sysdate from dual),to_Date(?,'dd-mm-yyyy'))";
			String cols[] = {(String)broDetails.get("bcode"),(String)broDetails.get("branchCode"),
					(String)broDetails.get("feeStatus"),(String)broDetails.get("taxStatus"),(String)broDetails.get("fundStatus"),
					(String)broDetails.get("policFee"),(String)broDetails.get("govtTax"),(String)broDetails.get("emergencyfund")
					,(String)broDetails.get("loginId"),Integer.toString((Integer.parseInt(amendId)+1)),(String)broDetails.get("isApplicableFor"),(String)broDetails.get("effectiveDate")};
			runner.multipleInsertion(sql, cols);
		}catch(Exception e){
			e.printStackTrace();
		}
		logger.info("exit brokerMasterInsert()");
	}
	public String getBrokerMaxAmend(final Map broDetails){
		logger.info("entered getBrokerMaxAmend()");
		String amendId="";
		try{
			String sql;sql = "select max(AMEND_ID) from BROKER_MASTER where BRANCH_CODE=? and AGENCY_CODE=?" +
					" and status='Y'";
			String args[] = {(String)broDetails.get("branchCode"),(String)broDetails.get("bcode")};
			amendId = runner.singleSelection(sql, args);
			amendId = amendId==null?"0":amendId;
		}catch(Exception e){
			e.printStackTrace();
		}
		logger.info("exit getBrokerMaxAmend()");
		return amendId;
	}
	public void updateBrokerMaster(Map broDetails,String amendId){
		logger.info("entered updateBrokerMaster()");
		try{
			String sql;sql="update BROKER_MASTER set STATUS='N',INCEPTION_DATE=(select sysdate from dual),EXPIRY_DATE=to_Date(?,'dd-mm-yyyy')" +
					" where AGENCY_CODE=? and BRANCH_CODE=? and amend_id=?";
			String cols[] = {(String)broDetails.get("effectiveDate"),(String)broDetails.get("bcode"),(String)broDetails.get("branchCode"),amendId};
			runner.multipleUpdation(sql, cols);
		}catch(Exception e){
			e.printStackTrace();
		}
		logger.info("exit updateBrokerMaster()");
	}
	public String brokerMasterValidation(final Map broDetails){
		logger.info("entered brokerMasterValidation()");
			final ValidationFormat validate = new ValidationFormat();
			final StringBuffer error = new StringBuffer();
			String feeStatus = (String)broDetails.get("feeStatus");
			String policFee = (String)broDetails.get("policFee");
			String taxStatus = (String)broDetails.get("taxStatus");
			String govtTax = (String)broDetails.get("govtTax");
			String fundStatus = (String)broDetails.get("fundStatus");
			String emergencyfund = (String)broDetails.get("emergencyfund");
			String effectiveDate = (String)broDetails.get("effectiveDate");
			if(feeStatus.equalsIgnoreCase("Y")){
				if(policFee.length()<=0||!ValidationFormat.isNumberValue(policFee)){
					error.append("Please enter valid Policy Fee amount<br>");
				}else if(validate.IsNegativeValidationFormat(policFee)){
					error.append("Please enter valid Policy Fee amount<br>");
				}
			}
			if(taxStatus.equalsIgnoreCase("Y")){
				if(govtTax.length()<=0||!ValidationFormat.isNumberValue(govtTax)){
					error.append("Please enter valid Government Tax %<br>");
				}else if(validate.IsNegativeValidationFormat(govtTax)){
					error.append("Please enter valid Government Tax %<br>");
				}
			}
			if(fundStatus.equalsIgnoreCase("Y")){
				if(emergencyfund.length()<=0||!ValidationFormat.isNumberValue(emergencyfund)){
					error.append("Please enter valid Emergency Fund %<br>");
				}else if(validate.IsNegativeValidationFormat(emergencyfund)){
					error.append("Please enter valid Emergency Fund %<br>");
				}
			}
			if(effectiveDate.length()<=0){
				error.append("Please select Effective Date<br>");
			}else{
				/*String amednId = getBrokerMaxAmend(broDetails);
				if(!amednId.equals("0")&&isBrokerMasterEffectiveValid(broDetails,amednId)){
					error.append("Invalid Effective Date<br>");
				}else if(validate.sysDateValidation(effectiveDate)){
					error.append("Invalid Effective Date<br>");
				}*/
				if(validate.sysDateValidation(effectiveDate))
					error.append("Invalid Effective Date<br>");
			}
		logger.info("exit brokerMasterValidation()");
		return error.toString();
	}
	public boolean isBrokerMasterEffectiveValid(final Map broDetails,String amednId){
		logger.info("entered isBrokerMasterEffectiveValid()");
		boolean flag = false;
		try{
			String sql;sql = "select count(*) from BROKER_MASTER where BRANCH_CODE=? and AGENCY_CODE=? and amend_id=?" +
					" and EFFECTIVE_DATE<to_Date(?,'dd-mm-yyyy')";
			String cols[] = {(String)broDetails.get("branchCode"),(String)broDetails.get("bcode"),amednId,(String)broDetails.get("effectiveDate")}; 
			String res = runner.singleSelection(sql, cols);
			if(res.equals("0")){
				flag = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		logger.info("exit isBrokerMasterEffectiveValid()");
		return flag;
	}

	// Product Assign to User Popup Screen...
	public String[][] getBrokerUserInfo(final String brokerAgency,final String proId,final String status){
		String userInfo[][] = new String[0][0];
		String qry = "";
		String args[] = new String[1];
		try{
			if(status.equalsIgnoreCase("WITHOUTPRODUCT")){
				qry = "select log.agency_code,log.username,log.login_id from login_master log,broker_company_master bcm where log.oa_code=bcm.agency_code and log.usertype='User' and log.login_id not in('NONE') and bcm.agency_code=?";
				args[0] = brokerAgency;
				userInfo = runner.multipleSelection(qry,args);
			}else{
				args = new String[2];
				qry = "select agency_code,user_name,login_id from login_user_details where agency_code!=oa_code and oa_code=? and status='Y' and product_id=?";
				args[0] = brokerAgency;
				args[1] = proId;
				userInfo = runner.multipleSelection(qry,args);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return userInfo;
	}
	
	public String pdtAssignToUsr(final String brokerAgency)
	{
		String result = "";
		String brokerPdt[][] = new String[0][0];
		brokerPdt = getCommisionData123(brokerAgency);
		java.util.Date dat = new java.util.Date();
		java.text.SimpleDateFormat simpleFormatter = new java.text.SimpleDateFormat("dd-MM-yyyy");
		simpleFormatter.setTimeZone(new java.util.SimpleTimeZone(14400000,"GMT"));
		//String usrAgencyCode = "";
		String individualAgency = "";
		String qry = "";
		String temp = "";
		String args[] = new String[0];
		boolean bool = false;
		Map sqlMap = null; 
		sqlMap = new HashMap();
		int mapCnt = 0;
		int userid = 1;
		for(int p=0; p<brokerPdt.length;p++){
			if(!usrAgencyPdtMap.isEmpty()){
				if(usrAgencyPdtMap.get("PROD"+brokerPdt[p][0])!= null){
					temp  = (String)usrAgencyPdtMap.get("PROD"+brokerPdt[p][0]);
					StringTokenizer usrAgency = new StringTokenizer(temp,",");
					while(usrAgency.hasMoreElements())
					{
						individualAgency = usrAgency.nextToken();
						if(temp!= null && !temp.equals("") && temp.length() > 0)
						{
							//usrAgencyCode = temp; //(String)usrAgencyPdtMap.get("PROD"+brokerPdt[p][0]);
							bool = userAgencyCheck(individualAgency,brokerPdt[p][0]);
							if(bool){	//insert
								//userid = autoGenCustId("USER_ID","login_user_details");
								qry = "insert into login_user_details(USER_ID,USER_NAME,LOGIN_ID,AGENCY_CODE,OA_CODE,PRODUCT_ID,COMPANY_ID,COMMISSION,INSURANCE_START_LIMIT," +
										"INSURANCE_END_LIMIT,SPECIAL_DISCOUNT,RELATIVE_USER_ID,AMEND_ID,INCEPTION_DATE,EXPIRY_DATE,EFFECTIVE_DATE,ENTRY_DATE," +
										"REMARKS,STATUS,CUSTOMER_ID,FREIGHT_DEBIT_OPTION,open_cover_no,scheme_id) " +
										"values((select nvl(max(USER_ID),0)+1 from login_user_details),(select distinct username from login_master where agency_code='"+individualAgency+"' and oa_code='"+brokerAgency+"'),(select distinct login_id from login_master where agency_code='"+individualAgency+"' and oa_code='"+brokerAgency+"'),?,?,?,?,?,?,(select nvl(INSURANCE_END_LIMIT,0) from login_user_details where agency_code='"+brokerAgency+"' and oa_code='"+brokerAgency+"' and product_id='"+brokerPdt[p][0]+"'),?,?,?,?,?,?,?,?,?,(select CUSTOMER_ID from personal_info where AGENCY_CODE='"+individualAgency+"'),?,?,?)";
								/*args = new String[19];
								args[0] = Integer.toString(userid);
								args[1] = individualAgency;
								args[2] = brokerAgency;
								args[3] = brokerPdt[p][0];
								args[4] = "1";
								args[5] = Integer.toString(0);
								args[6] = "1000";
								args[7] = Integer.toString(0);
								args[8] = "0";
								args[9] = "1";
								args[10] = com.maan.common.util.OracleDateConversion.ConvertDate(simpleFormatter.format(dat));
								args[11] = "";
								args[12] = "";
								args[13] = com.maan.common.util.OracleDateConversion.ConvertDate(simpleFormatter.format(dat));
								args[14] = "";
								args[15] = "Y";
								args[16] = "N";
								args[17] = "";
								args[18] = "";*/
								
								args = new String[18];
								args[0] = individualAgency;
								args[1] = brokerAgency;
								args[2] = brokerPdt[p][0];
								args[3] = "1";
								args[4] = Integer.toString(0);
								args[5] = "1000";
								args[6] = Integer.toString(0);
								args[7] = "0";
								args[8] = "1";
								args[9] = com.maan.common.util.OracleDateConversion.ConvertDate(simpleFormatter.format(dat));
								args[10] = "";
								args[11] = "";
								args[12] = com.maan.common.util.OracleDateConversion.ConvertDate(simpleFormatter.format(dat));
								args[13] = "";
								args[14] = "Y";
								args[15] = "N";
								args[16] = "";
								args[17] = "";
								
		 						sqlMap.put("Query"+mapCnt,qry);
								sqlMap.put("Args"+mapCnt++,args);
							}else{	//update
								args = new String[4];
								args[0] = Integer.toString(0);
								args[1] = Integer.toString(0); 
								args[2] = individualAgency;
								args[3] = brokerPdt[p][0];
								qry = "update login_user_details set COMMISSION=?,INSURANCE_END_LIMIT=(select nvl(INSURANCE_END_LIMIT,0) from login_user_details where agency_code='"+brokerAgency+"' and oa_code='"+brokerAgency+"' and product_id='"+brokerPdt[p][0]+"'),STATUS=(select status from login_user_details where agency_code='"+brokerAgency+"' and oa_code='"+brokerAgency+"' and product_id='"+brokerPdt[p][0]+"'),SPECIAL_DISCOUNT=?,FREIGHT_DEBIT_OPTION=? where AGENCY_CODE=? and product_id=? ";
								sqlMap.put("Query"+mapCnt,qry);
								sqlMap.put("Args"+mapCnt++,args);
							}
						}
					} // While
				}
			}
		}
		if(!sqlMap.isEmpty()){
			sqlMap.put("Count",Integer.toString(mapCnt));
			runner.multipleUpdateTransaction((HashMap)sqlMap);
		}
		return result;
	}
	
	public boolean userAgencyCheck(final String userAgency,final String proId)
	{
		boolean bool = false;
		String sql = "";
		String args[] = new String[2];
		String count = "";
		try{
			args[0] = userAgency;
			args[1] = proId;
			sql = "select count(*) from login_user_details where agency_code=? and product_id=?";
			count = runner.singleSelection(sql, args);
			if(count.equals("0")){
				bool = true;
			}else{
				bool = false;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return bool;
	}
}// Class

