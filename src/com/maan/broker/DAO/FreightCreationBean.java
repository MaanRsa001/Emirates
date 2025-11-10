package com.maan.broker.DAO;

import java.util.HashMap;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

import com.maan.common.util.dataCollection;
import com.maan.DBCon.DBConnection;
import com.maan.common.error.ErrorInfo;
import com.maan.common.password.passwordEnc;
import com.maan.common.util.StringUtil;
import com.maan.services.util.runner;
import com.maan.common.util.OracleDateConversion;	

public class FreightCreationBean extends ErrorInfo 
{	
	private Statement smt = null;
	private ResultSet rs = null;
	private PreparedStatement psmt = null;	
	private String title = "";
	private String firstName = "";
	private String gender = "";
	private String lastName = "";
	private String nationality = "";
	private String brokerDate = "";
	private String telephone = "";
	private String mobile = "";
	private String city = "";
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
	private String dobDay = "";
	private String dobMonth = "";
	private String dobYear = "";
	private String mode = "";
	private String AgencyCode = "";
	private String OACode = "";
	private String userType = "";
	private String provision = "";
	private String ucode = "";
	private String loginPersonId = "";
	private String disStatus = "";
	private String error = "";	
	private String freightfwd = "";
	
	// Multi Freight Login Creation Start
	private String fdCode ="";
	
	public String getFdCode() {
		return fdCode;
	}
	public void setFdCode(String fdCode) {
		this.fdCode = fdCode;
	}
	// Multi Freight Login Creation End
	
	public void setFreightfwd(String freightfwd) {
		this.freightfwd = freightfwd;
	}
	public String getFreightfwd() {
		return freightfwd;
	}
	public void setLoginPersonId(String loginPersonId) {
		this.loginPersonId = loginPersonId;
	}
	public String getLoginPersonId() {
		return loginPersonId;
	}
	public void setUcode(String ucode) {
		this.ucode = ucode;
	}
	public String getUcode() {
		return ucode;
	}
	public void setDisStatus(String disStatus) {
		this.disStatus = disStatus;
	}
	public String getDisStatus() {
		return disStatus;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getUserType() {
		return userType;
	}
	//public void setOut(PrintWriter out) {
	//	this.out = out;
	//}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitle() {
		return title;
	}
	public void setProvision(String provision) {
		this.provision = provision;
	}
	public String getProvision() {
		return provision;
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
	public void setCity(String city) {
		this.city = city;
	}
	public String getCity() {
		return city;
	}

	// ********* for commision
	HashMap proDetails = new HashMap();
	public void setProDetails(HashMap proDetails) {
		this.proDetails = proDetails;
	}
	public HashMap getProDetails() {
		return proDetails;
	}

	public String validate() 
    {
		dataCollection data = new dataCollection();
		String error = "";
		String values = null;
		boolean  flag = false;
		try {
			if ("0".equalsIgnoreCase(userType))
				error = error + "<br>*" + runner.getErrormsg("54");

			if (userType.equals("5")) {
				values = data.validString(ucode, false);
				if ("needed".equalsIgnoreCase(values)) {
					error = error + "<br>*Please Enter Freight Code";
				}
				if (StringUtil.checkSpecial(ucode)) {
					error = error+ "<BR>*Spcial Chars Not Allowed For Freight Code";
				}
				if (!mode.equalsIgnoreCase("edit")) {
					if (checkCreationCode(ucode)) {
						error = error+ "<BR>*This Freight Code Already Existed";
					}
				}
				/*
				 * if("0".equalsIgnoreCase(freightfwd)) { error = error+"<br>*"+runner.getErrormsg("246"); }
				 */
			}
			values = data.validString(firstName, false);
			if ("needed".equalsIgnoreCase(values))
				error = error + "<br>*" + runner.getErrormsg("3");
			/*
			 * values = data.validString(lastName,false);
			 * if("needed".equalsIgnoreCase(values)) error = error+"<br>*"+runner.getErrormsg("4");
			 */
			values = data.validString(fax, true);
			if ("Invalid".equalsIgnoreCase(values))
				error = error + "<br>*" + runner.getErrormsg("16");

			values = data.emailValidate(email);
			if ("needed".equalsIgnoreCase(values))
				error = error + "<br>*" + runner.getErrormsg("19");
			else if ("Invalid".equalsIgnoreCase(values))
				error = error + "<br>*" + runner.getErrormsg("20");
			else if (checkEmailSpecial(email))
				error = error + "<br>*" + runner.getErrormsg("20");
			if ("0".equalsIgnoreCase(emirate))
				error = error + "<br>*" + runner.getErrormsg("25");

			values = data.validString(poBox, true);
			if ("needed".equalsIgnoreCase(values))
				error = error + "<br>*" + runner.getErrormsg("27");
			else if ("Invalid".equalsIgnoreCase(values))
				error = error + "<br>*" + runner.getErrormsg("28");
			else if ("needed".equalsIgnoreCase(data.validLength(poBox, 1)))
				error = error + "<br>*" + runner.getErrormsg("29");

			if ("others".equalsIgnoreCase(emirate)) 
			{
				values = data.validString(city, false);
				if ("needed".equalsIgnoreCase(values))
					error = error + "<br>*" + runner.getErrormsg("55");
				else if ("Invalid".equalsIgnoreCase(values))
					error = error + "<br>*" + runner.getErrormsg("56");
			}

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

		} catch (Exception e) {
			System.out.println("Exception in validation" + e.toString());
		} finally {
		}
		return error;
	}

	/* * * for individual validation you can check the old back up * * */
	public String insertBrokerEntry(String branch,String loginCreationStatus) 
    {
		String user_Id = "1";
		String Query = "";
		Date dd = new Date();
		SimpleDateFormat simpleFormatter = new SimpleDateFormat("dd-MM-yyyy");
		String process = "NOT";
        String[] args = new String[2];
		try {
			String customerId = "";
            args[0] = ucode;
            args[1] = userType;
			String qry = "select count(*) from personal_info where agency_code = ? and application_id = ?";
			qry = runner.singleSelection(qry,args);			
			if ("0".equalsIgnoreCase(qry) || "DIDN'T SELECTED".equalsIgnoreCase(qry)) {
				String date123 = "";
				if (!dobDay.equals("") && !dobDay.equals("0") && !dobMonth.equals("") && !dobMonth.equals("0")	&& !dobYear.equals("") && !dobMonth.equals("0")) {
					date123 = com.maan.common.util.OracleDateConversion.ConvertDate(dobDay + "-" + dobMonth + "-"+ dobYear);
				}
				firstName = firstName.replaceAll("'", "''");
				lastName = lastName.replaceAll("'", "''");
				String oacode = getOACode(loginPersonId);

				String[] qryvalues = new String[27];
				qryvalues[0] = customerId;
				qryvalues[1] = userType;
				qryvalues[2] = title;
				qryvalues[3] = firstName;
				qryvalues[4] = lastName;
				qryvalues[5] = "1";
				qryvalues[6] = nationality;
				qryvalues[7] = date123;
				qryvalues[8] = gender;
				qryvalues[9] = telephone;
				qryvalues[10] = mobile;
				qryvalues[11] = fax;
				qryvalues[12] = email;
				qryvalues[13] = address1;
				qryvalues[14] = address2;
				qryvalues[15] = occupation;
				qryvalues[16] = poBox;
				qryvalues[17] = country;
				qryvalues[18] = emirate;
				qryvalues[19] = "Y";				
				qryvalues[20] = OracleDateConversion.ConvertDate("" + simpleFormatter.format(dd));
				qryvalues[21] = "NONE";
				qryvalues[22] = ucode;
				qryvalues[23] = oacode;
				qryvalues[24] = "Yes";
				// City
				qryvalues[25] = city;
				if(loginCreationStatus.equalsIgnoreCase("SubFreight")){
					qryvalues[26] = fdCode; //fd_code
				}
				else{
					qryvalues[26] = ucode; //fd_code
				}
					
				/*** ** qry="insert into personal_info(customer_id,application_id,title,first_name,last_name,amend_id,nationality,dob,gender,telephone,mobile,fax,email,address1,address2,occupation,pobox,country,emirate,status,entry_date,login_id,AGENCY_CODE,OA_CODE,FREIGHT_FORWARD_USER)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				 * *******/
				// Senthil - Changes here is to City
				qry = "insert into personal_info(customer_id,application_id,title,first_name,last_name,amend_id,nationality,dob,gender,telephone,mobile,fax,email,address1,address2,occupation,pobox,country,emirate,status,entry_date,login_id,AGENCY_CODE,OA_CODE,FREIGHT_FORWARD_USER,city,fd_code)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

				try {
					qryvalues[0] = "" + getMaxCustomerId(branch);
				}
                catch (Exception e) {
				   e.printStackTrace();
				}
				runner.multipleInsertion(qry, qryvalues);
				try{
					String freightAgency = "";
					String sql = "select nvl(detail_name,'0') from constant_detail where CATEGORY_ID='37' and CATEGORY_DETAIL_ID='2' and status='Y' and branch_code='"+branch+"'";
					freightAgency = runner.singleSelection(sql);
					int tepmCusAgency = Integer.parseInt(freightAgency);
					tepmCusAgency = tepmCusAgency+1;
					runner.updation("update constant_detail set detail_name='"+(""+tepmCusAgency)+"' where CATEGORY_ID='37' and CATEGORY_DETAIL_ID='2' and status='Y' and branch_code='"+branch+"'");
				}
				catch(Exception e){
					e.printStackTrace();
				}
			} 
            else {
				String date123 = "";
				if (!dobDay.equals("") && !dobDay.equals("0") && !dobMonth.equals("") && !dobMonth.equals("0")	&& !dobYear.equals("") && !dobMonth.equals("0")) {
					date123 = com.maan.common.util.OracleDateConversion.ConvertDate(dobDay + "-" + dobMonth + "-"+ dobYear);
				}
				firstName = firstName.replaceAll("'", "''");
				lastName = lastName.replaceAll("'", "''");
				String[] qryvalues = new String[20];
				qryvalues[0] = title;
				qryvalues[1] = firstName;
				qryvalues[2] = lastName;
				qryvalues[3] = nationality;
				qryvalues[4] = date123;
				qryvalues[5] = gender;
				qryvalues[6] = telephone;
				qryvalues[7] = mobile;
				qryvalues[8] = fax;
				qryvalues[9] = email;
				qryvalues[10] = address1;
				qryvalues[11] = address2;
				qryvalues[12] = occupation;
				qryvalues[13] = poBox;
				qryvalues[14] = country;
				qryvalues[15] = emirate;
				qryvalues[16] = "Y";				
				qryvalues[17] = OracleDateConversion.ConvertDate("" + simpleFormatter.format(dd));
				qryvalues[18] = "Yes";
				// City
				qryvalues[19] = city;
				/***************************************************************
				 * qry="update personal_info set
				 * title=?,first_name=?,last_name=?,nationality=?,dob=?,gender=?,telephone=?,mobile=?,fax=?,email=?,address1=?,address2=?,occupation=?,pobox=?,country=?,emirate=?,status=?,effective_date=?,FREIGHT_FORWARD_USER=?,city=?
				 * where application_id='"+userType+"' and
				 * agency_code='"+ucode+"'";
				 * 
				 **************************************************************/
				// senthil C Changes here is city
				qry = "update personal_info set title=?,first_name=?,last_name=?,nationality=?,dob=?,gender=?,telephone=?,mobile=?,fax=?,email=?,address1=?,address2=?,occupation=?,pobox=?,country=?,emirate=?,status=?,effective_date=?,FREIGHT_FORWARD_USER=?,city=? where application_id='"+ userType + "' and agency_code='" + ucode + "'";
				qry = runner.multipleUpdation(qry, qryvalues);				
			}
			process = "YES";
		}
        catch (Exception e) {
			e.printStackTrace();
		}
		return process;
	}
    
	public String[][] getUserBrokerInformationForFreight(String login) 
	{				
		String[][] UserBroker = new String[0][0];		
        String[] args = new String[1];
        args[0] = login;
		try {			
			String sql = "select application_id,login_id,agency_code,oa_Code,first_name from personal_info where oa_code=(select oa_code from login_master where login_id = ?) and application_id='5'";
			UserBroker = runner.multipleSelection(sql,args);
		}
        catch (Exception e22) {
			e22.printStackTrace();
		}
		return UserBroker;
	}
	
	public String validateCommision() {
		dataCollection data = new dataCollection();		
		String[][] BrokerCommision = getCommisionData123(loginPersonId);
		String error = "";
		HashMap pro = new HashMap();		
		if (proDetails.size() > 0) {
			pro = (HashMap) proDetails.get("productsdetails");
			String values = "";
			String pid = "";
			pid = (String) pro.get("product");
			if (pid.equals("") || pid.length() <= 0)
				error = error + "<BR>* Please Select Product";
			else {
				for (int i = 0; i < BrokerCommision.length; i++) {

					if (((String) pro.get("product")).equalsIgnoreCase(BrokerCommision[i][0] == null ? "0": BrokerCommision[i][0])) {
						// Validation For Commision
						values = data.validString((String) pro.get("commision"), true);
						if ("needed".equalsIgnoreCase(values))
							error = error + "<br>* Enter Valid Commmision";
						else if ("Invalid".equalsIgnoreCase(values))
							error = error + "<br>* Enter Valid Commmision";
						else if (Double.parseDouble((String) pro.get("commision")) > Double	.parseDouble(BrokerCommision[i][1] == null ? "0.0"	: BrokerCommision[i][1]))
							error = error+ "<br>* Commmision Exceded The Limit";

						// validation for Sum Insured
						values = data.validString((String) pro	.get("suminsured"), true);
						if ("needed".equalsIgnoreCase(values))
							error = error + "<br>* Enter Valid Sum Insured";
						else if ("Invalid".equalsIgnoreCase(values))
							error = error + "<br>* Enter Valid Sum Insured";
						else if (Double.parseDouble((String) pro.get("suminsured")) > Double.parseDouble(BrokerCommision[i][2] == null ? "0.0": BrokerCommision[i][2]))
							error = error	+ "<br>* Sum Insured Exceded The Limit";

						// Validation for Min Premium
						values = data.validString((String) pro.get("minpremium"), true);
						if ("needed".equalsIgnoreCase(values))
							error = error + "<br>* Enter Valid Min Premium";
						else if ("Invalid".equalsIgnoreCase(values))
							error = error + "<br>* Enter Valid Min Premium";
						/*
						 * else
						 * if(Double.parseDouble((String)pro.get("minpremium"))>Double.parseDouble(BrokerCommision[i][8]==null?"0.0":BrokerCommision[i][8]))
						 * error=error+"<br>* Min Premium Exceded The Limit";
						 */
					/*	if (((String) pro.get("loadingSign")).equalsIgnoreCase("select")||((String) pro.get("loadingSign")).equalsIgnoreCase(""))
							error = error+ "<br>* Please select Discount/Loading of Premium";*/
						
						values = data.validString((String) pro.get("loading"), true);
						if ("needed".equalsIgnoreCase(values))
							error = error + "<br>* Please Enter Loading of Premium Amount ";
						else if ("Invalid".equalsIgnoreCase(values))
							error = error + "<br>* Please Enter Valid Loading of Premium Amount";
						values = data.validString((String) pro.get("discount"), true);
						if ("needed".equalsIgnoreCase(values))
							error = error + "<br>* Please Enter discount of Premium Amount ";
						else if ("Invalid".equalsIgnoreCase(values))
							error = error + "<br>* Please Enter Valid discount of Premium Amount";
					}
				}
			}
		}
		return error;
	}
	
	// Multi Login Creation for Freight Creation...
	public String insertOrUpdate(String ucode, String userType,String loginPersonId, String provision,String loginCreationStatus) 
	{
		Connection con=null; 
		Date dd = new Date();
		SimpleDateFormat simpleFormatter = new SimpleDateFormat("dd-MM-yyyy");
		String process = "";
		int i = 0;
		String commision = "";
		String suminsured = "";
		String minpremium = "";
		String backdates = "";
		String loading = "";
		String discount = "";
		String rateOption = "";
		String scheduleOption = "";
		String debitOption = "";
		String adminOption = "";
		String autoLoad = "";
		//String loadingSign = "";
		String provisionPre = "";
		String freightLoginId = "";
		String[][] userdetails = new String[0][0];
		HashMap pro = new HashMap();		
        String[] args = new String[1];  
        args[0] = ucode;
		try{
			freightLoginId = runner.singleSelection("select nvl(login_id,' ') from login_master where agency_code = ?",args);
			freightLoginId = freightLoginId.trim();
			try {
				try{
                    args[0] = ucode;
					String sql = "select CUSTOMER_ID,FIRST_NAME,AGENCY_CODE from personal_info where AGENCY_CODE = ?";					
					userdetails = runner.multipleSelection(sql,args);					
				}
				catch(Exception e){
					e.printStackTrace();
				}				
				String[][] products = new String[0][0];
				String sqlcheck = "";
                args[0] = ucode;
				sqlcheck = "select product_id from login_user_details where AGENCY_CODE = ?";
				try {					
					products = runner.multipleSelection(sqlcheck,args);
				} 
                catch (Exception e) {
					e.printStackTrace();
				}							
				pro = (HashMap) proDetails.get("productsdetails");
				String avail = "not";
				for (int p = 0; p < products.length; p++) {
					avail = "not";
					for (int h = 0; h < proDetails.size(); h++) {
						if (products[p][0].equalsIgnoreCase((String) pro.get("product"))) {
							avail = "yes";
							break;
						}
					}
					if (avail.equals("yes")) {
						System.out.println("available>>>>"+ products[p][0]);
					}
                    else {
                        args = new String[2];
                        args[0] = ucode;
                        args[1] = products[p][0]; 
						String sqlstatus = "";
						con = DBConnection.getInstance().getDBConnection();
                        smt = con.createStatement();
						sqlstatus = "update login_user_details set status='N' where AGENCY_CODE = ? and product_id = ?";
						boolean check1 = false;					
						try {
							int n = smt.executeUpdate(sqlstatus);                            
							if (n > 0) {
								check1 = true;
							}
                            else {
								check1 = false;
							}
						}
                        catch (Exception e) {
							e.printStackTrace();
						}
						finally{
                            try {
								if (smt != null)
									smt.close();
							} 
                            catch (Exception e) {
								e.printStackTrace();
							}
							try {
								if (con != null)
									con.close();
							} 
                            catch (Exception e) {
								e.printStackTrace();
							}
						}						
					}
				}
				for (i = 0; i < proDetails.size(); i++) {
					pro = (HashMap) proDetails.get("productsdetails");
					if (userdetails.length > 0) {
						if (((String) pro.get("commision")).length() > 0	&& pro.get("commision") != null) {
							commision = (String) pro.get("commision");
						}
						if (((String) pro.get("suminsured")).length() > 0 && pro.get("suminsured") != null) {
							suminsured = (String) pro.get("suminsured");
						}
						if (((String) pro.get("minpremium")).length() > 0 && pro.get("minpremium") != null) {
							minpremium = (String) pro.get("minpremium");
						}
						if (((String) pro.get("backdates")).length() > 0 && pro.get("backdates") != null) {
							backdates = (String) pro.get("backdates");
						}
                        else
							backdates = "0";
						if (backdates.length() <= 0)
							backdates = "0";
						if (((String) pro.get("loading")).length() > 0	&& pro.get("loading") != null) {
							loading = (String) pro.get("loading");
						}
					/*	if (((String) pro.get("loadingSign")).length() > 0	&& pro.get("loadingSign") != null) {
							loadingSign = (String) pro.get("loadingSign");
						}
						loading = loadingSign+loading;
						System.out.println("After Adding Sign with load premium..."+loading);	*/
						if (((String) pro.get("provisionPre")).length() > 0 && pro.get("provisionPre") != null) {
							provisionPre = (String) pro.get("provisionPre");
						}
						if (((String) pro.get("discount")).length() > 0	&& pro.get("discount") != null) {
							discount = (String) pro.get("discount");
						}
						if (((String) pro.get("rateOption")).length() > 0	&& pro.get("rateOption") != null) {
							rateOption = (String) pro.get("rateOption");
						}
						if (((String) pro.get("scheduleOption")).length() > 0	&& pro.get("scheduleOption") != null) {
							scheduleOption = (String) pro.get("scheduleOption");
						}
						if (((String) pro.get("debitOption")).length() > 0	&& pro.get("debitOption") != null) {
							debitOption = (String) pro.get("debitOption");
						}
						if (((String) pro.get("adminOption")).length() > 0	&& pro.get("adminOption") != null) {
							adminOption = (String) pro.get("adminOption");
						}
						if (((String) pro.get("autoLoad")).length() > 0	&& pro.get("autoLoad") != null) {
							autoLoad = (String) pro.get("autoLoad");
						}
						
						String sqlboth = "";						
						String sqlcount = "";
                        args = new String[2];
                        args[0] = ucode;
                        args[1] = (String) pro.get("product");
						sqlcount = "select count(*) from login_user_details where agency_code = ? and product_id = ?";						
						int count = 0;
                        String res = "";
						try{
							res = runner.singleSelection(sqlcount,args);
                            res = (res == null || res.equalsIgnoreCase("null") || res.equalsIgnoreCase(""))?"0":res;
                            count = Integer.parseInt(res);
						}
						catch(Exception e){							
							e.printStackTrace();
						}					
						if (count <= 0) {
							int userid = 1;							
							sqlboth = "insert into login_user_details(USER_ID,USER_NAME,LOGIN_ID,AGENCY_CODE,OA_CODE,PRODUCT_ID,COMPANY_ID,COMMISSION,INSURANCE_START_LIMIT,INSURANCE_END_LIMIT,MIN_PREMIUM_AMOUNT,RELATIVE_USER_ID,AMEND_ID,INCEPTION_DATE,EXPIRY_DATE,EFFECTIVE_DATE,ENTRY_DATE,REMARKS,STATUS,CUSTOMER_ID,BACK_DATE_ALLOWED,LOADING_OF_PREMIUM,PROVISION_FOR_PREMIUM,DISCOUNT_OF_PREMIUM,FREIGHT_RATE_OPTION,FREIGHT_SCHEDULE_OPTION,FREIGHT_DEBIT_OPTION,FREIGHT_ADMIN_OPTION,FREIGHT_AUOTO_OPTION,fd_code) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";							
							userid = autoGenCustId("USER_ID","LOGIN_USER_DETAILS");							
							try {
								args = new String[30];
                                args[0] = userid + "";
                                args[1] = userdetails[0][1];   

                                if(freightLoginId.length()<=0)
									args[2] =  "NONE";
								else if(freightLoginId.length()>0)
									args[2] =  freightLoginId;

                                args[3] = userdetails[0][2];
                                args[4] = getOACode(loginPersonId);
                                args[5] = (String) pro.get("product");
                                args[6] = "1";
                                args[7] = Double.parseDouble(commision) + "";
                                args[8] = 1000 + "";
                                args[9] = Long.parseLong(suminsured) + "";
                                args[10] = Double.parseDouble(minpremium) + "";
                                args[11] = "0";
                                args[12] = "1";
                                args[13] = OracleDateConversion.ConvertDate(""+ simpleFormatter.format(dd));
                                args[14] = "";
                                args[15] = "";
                                args[16] = OracleDateConversion.ConvertDate(""+ simpleFormatter.format(dd));
                                args[17] = "";
                                args[18] = "Y";
                                args[19] = userdetails[0][0];
                                args[20] = Integer.parseInt(backdates) + "";
                                args[21] = loading;
                                args[22] = provisionPre; 
                                args[23] = discount;
                                args[24] = rateOption;
                                args[25] = scheduleOption;
                                args[26] = debitOption;
                                args[27] = adminOption;
                                args[28] = autoLoad;                   
                                if(loginCreationStatus.equalsIgnoreCase("SubFreight")){
                                	args[29] = fdCode; //fd_code
                                }else{
                                	args[29] = userdetails[0][2]; //fd_code
                                }
								runner.multipleUpdation(sqlboth,args);
							}
                            catch (Exception e) {
							        e.printStackTrace();
							}							
						} 
                        else {							
                            args = new String[15];
                            args[0] = commision;
                            args[1] = suminsured;
                            args[2] = minpremium;
                            args[3] = backdates;
                            args[4] = loading;
                            args[5] = provisionPre;
                            args[6] = discount;
                            args[7] = rateOption ;
                            args[8] = scheduleOption;
                            args[9] = debitOption;
                            args[10] = freightLoginId;
                            args[11] = adminOption;
                            args[12] = autoLoad;
                            args[13] = ucode;
                            args[14] = (String) pro.get("product")  ;                            


							sqlboth = "update login_user_details set COMMISSION = ?, INSURANCE_END_LIMIT = ?, STATUS='Y', MIN_PREMIUM_AMOUNT = ?, BACK_DATE_ALLOWED = ?, LOADING_OF_PREMIUM = ?, PROVISION_FOR_PREMIUM = ?,DISCOUNT_OF_PREMIUM =?,FREIGHT_RATE_OPTION =? ,FREIGHT_SCHEDULE_OPTION = ?,FREIGHT_DEBIT_OPTION = ?,login_id= ?,FREIGHT_ADMIN_OPTION = ?,FREIGHT_AUOTO_OPTION = ? where AGENCY_CODE = ? and product_id = ?";							
							try {						
								runner.multipleSelection(sqlboth,args);
							}
                            catch (Exception e) {
								e.printStackTrace();
							}							
						}						
						if (!provision.equals("NO")) {
							if (!checkUserCode(ucode, "login_master")) {
								String utype = "";
								utype = "Freight";
								String sql1 = "insert into login_master(LOGIN_ID,PASSWORD,USERTYPE,USERNAME,USERID,AGENCY_CODE,OA_CODE,ACCESSTYPE,RIGHTS,LPASS1,LPASS2,LPASS3,PASSDATE,COMPANY_ID,CREATED_BY,STATUS,USER_ID_CREATION,AC_EXECUTIVE_CREATION,REFERAL,fd_code) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
								try {
                                    args = new String[20];
                                    args[0] = "NONE";
                                    args[1] = "";
                                    args[2] = utype;
                                    args[3] = userdetails[0][1];
                                    args[4] = "1";
                                    args[5] = ucode;
                                    args[6] = getOACode(loginPersonId);
                                    args[7] = "BOTH" ;
                                    args[8] = "" ;
                                    args[9] = "";
                                    args[10] = "";
                                    args[11] = "";
                                    args[12] = OracleDateConversion.ConvertDate(""+ simpleFormatter.format(dd));
                                    args[13] = "1";
                                    args[14] = getOACode(loginPersonId);
                                    args[15] = "Y" ;
                                    args[16] = "";
                                    args[17] = "";
                                    args[18] = "" ;   
                                    if(loginCreationStatus.equalsIgnoreCase("SubFreight")){
                                    	args[19] = fdCode; // fd_code
                                    }
                                    else{
                                    	args[19] = ucode; // fd_code
                                    }
									runner.multipleInsertion(sql1,args);
									process = "insert";
								} 
                                catch (Exception e) {
									e.printStackTrace();
								}								
							}							
						}
					}
				}
			}
            catch (Exception e) {
				e.printStackTrace();
			}
		} 
        catch (Exception e) {
			e.printStackTrace();
		}       
		return process;
	}

	public String insertUserLogin(String ucode, String loginPersonId,	String userType) 
    {		
        String[] args  = new String[4];  
		String process = "";
		passwordEnc pass = new passwordEnc();
		String encpass = pass.crypt(password.substring(0, 3), password);		
		String qry = "update login_master set LOGIN_ID = ? , PASSWORD = ?, OA_CODE = ?  where  agency_code = ?" ;	
		try {			
			args[0] =  brokerId;
			args[1] = encpass;
			args[2] =  getOACode(loginPersonId);
            args[3] = ucode;
			runner.multipleUpdation(qry,args);
			process = "1";
		} 
        catch (Exception e) {
		  e.printStackTrace();
		}		
		String qry1 = "";
		qry1 = "update login_user_details set LOGIN_ID = ?, OA_CODE = ?  where  agency_code = ?";		
		try {						
            args = new String[3];
            args[0] = brokerId;
            args[1] = getOACode(loginPersonId);
            args[2] = ucode;			
			runner.multipleUpdation(qry1,args);
			process = process + "2";
		}
        catch (Exception e) {
			e.printStackTrace();
		}		
		String qry2 = "update personal_info set LOGIN_ID = ? , OA_CODE = ?  where  agency_code = ?";		
		try {	
			 args = new String[3];
            args[0] = brokerId;
            args[1] = getOACode(loginPersonId);
            args[2] = ucode;			
			runner.multipleUpdation(qry2,args);
			process = process + "3";
		} 
        catch (Exception e) {
			e.printStackTrace();
		}        
		return process;
	}

	public String insertUserLogin(String ucode, String loginPersonId,String userType, String dbName) 
	{		
        String[] args = new String[4]; 
		String process = "";
		passwordEnc pass = new passwordEnc();
		String encpass = pass.crypt(password.substring(0, 3), password);		
		String qry = "update login_master set LOGIN_ID=?,PASSWORD=?,OA_CODE=?  where  agency_code = ?";		
		try {		
            args[0] = brokerId;
            args[1] = encpass;
            args[2] = getOACode(loginPersonId);
            args[3] = ucode;                                        
			runner.multipleUpdation(qry,args);

			// Rajesh common Db
			/*int comRes = 0;
			try {
				DBConnection DBCom = new DBConnection();
				commonCon = DBCom.getDBConnectionforCommon("Common");
				st = commonCon.createStatement();
				String ComSql = "insert into LOGIN_MASTER_COMMON(LOGIN_ID,PASSWORD,DATABASE_NAME,AMEND_ID,STATUS) values('"+ brokerId+ "','"+ encpass+ "','"+ dbName+ "',1,'Y')";
				comRes = st.executeUpdate(ComSql);
			} catch (Exception e) {
				System.out.println("updations in login master common "	+ e.toString());
				e.printStackTrace();
			} finally {
				try {
					if (commonCon != null)
						commonCon.close();
					if (st != null)
						st.close();
				} catch (Exception e) {
					System.out.println("ERROR " + e.toString());
				}
			}*/
			process = "1";
		}
        catch (Exception e) {
			e.printStackTrace();
		}		
		String qry1 = "";
		qry1 = "update login_user_details set LOGIN_ID = ? , OA_CODE = ? where  agency_code = ?";       
		try {			
            args = new String[3];
            args[0] = brokerId;
            args[1] = getOACode(loginPersonId);
            args[2] = ucode;
            runner.multipleUpdation(qry1,args);
			process = process + "2";
		}
        catch (Exception e) {
		     e.printStackTrace();
		}		
		String qry2 = "update personal_info set LOGIN_ID = ? , OA_CODE = ? where  agency_code = ?";		
		try {			
            args = new String[3];
            args[0] = brokerId;
            args[1] = getOACode(loginPersonId);
            args[2] = ucode;	
			runner.multipleUpdation(qry2,args);
			process = process + "3";
		}
        catch (Exception e) {
			e.printStackTrace();
		}     
		return process;
	}

	public String[][] getBrokerDetails(String ucode) {
		//Connection con = null;		
		//DBConnection1 DB = new DBConnection1();
		//con = DB.getDBConnection();
		//System.out.println("connection is  " + con);

        String[][] brokerDetails = new String[0][0];
        String[] args = new String[1];
        args[0] = ucode;        
		String sql = "select pi.TITLE,pi.GENDER,pi.FIRST_NAME,pi.LAST_NAME,pi.NATIONALITY,pi.DOB,pi.TELEPHONE,pi.MOBILE,pi.FAX,EMAIL,pi.ADDRESS1,pi.ADDRESS2,pi.OCCUPATION,pi.EMIRATE,pi.COUNTRY,pi.POBOX,pi.entry_date,pi.FREIGHT_FORWARD_USER,pi.city,pi.login_id from personal_info pi where pi.agency_code = ?";		
		try {			
			brokerDetails = runner.multipleSelection(sql,args);
		} 
        catch (Exception e) {
			e.printStackTrace();
		} 
		return brokerDetails;
	}

	public String[][] getBrokerDetails123(String bcode) 
     {
		String[] args = new String[1];
        args[0] = bcode;
		String[][] brokerDetails = new String[0][0];	
		String sql = "select pi.TITLE,pi.GENDER,pi.FIRST_NAME,pi.LAST_NAME,pi.NATIONALITY,pi.DOB,pi.TELEPHONE,pi.MOBILE,pi.FAX,pi.EMAIL,pi.ADDRESS1,pi.ADDRESS2,pi.OCCUPATION,pi.EMIRATE,pi.COUNTRY,pi.POBOX,lm.company_name,lm.agency_code,pi.entry_date,lm.status,lm.ADDRESS3,lm.city from personal_info pi,broker_company_master lm where pi.agency_code=lm.agency_code and  pi.agency_code = ? and pi.application_id = '2'";		
		try {			
			brokerDetails = runner.multipleSelection(sql,args);
		}
        catch (Exception e) {
			e.printStackTrace();
		}        
		return brokerDetails;
	}

	public String[][] getBrokerDetails12(String bcode) 
    {	
		String[][] brokerDetails = new String[0][0];	
        String[] args = new String[1];
        args[0] = bcode;
		String sql = "select pi.TITLE,pi.GENDER,pi.FIRST_NAME,pi.LAST_NAME,pi.NATIONALITY,pi.DOB,pi.TELEPHONE,pi.MOBILE,pi.FAX,pi.EMAIL,pi.ADDRESS1,pi.ADDRESS2,pi.OCCUPATION,pi.EMIRATE,pi.COUNTRY,pi.POBOX,pi.POBOX,pi.agency_code,pi.entry_date,pi.status from personal_info pi where  pi.agency_code = ?";		
		try {		
			brokerDetails = runner.multipleSelection(sql,args);
		} 
        catch (Exception e) {
			e.printStackTrace();
		}       
		return brokerDetails;
	}

	public String[][] getBrokerDetailsDe(String bcode) 
    {		
		String[][] brokerDetails = new String[0][0];	
        String[] args = new  String[1];
        args[0] = getAgencyCode(bcode);
		String sql = "select pi.TITLE,pi.GENDER,pi.FIRST_NAME,pi.LAST_NAME,pi.NATIONALITY,pi.DOB,pi.TELEPHONE,pi.MOBILE,pi.FAX,pi.EMAIL,pi.ADDRESS1,pi.ADDRESS2,pi.OCCUPATION,pi.EMIRATE,pi.COUNTRY,pi.POBOX,lm.company_name,lm.agency_code,pi.entry_date,lm.status,lm.ADDRESS3,lm.city from personal_info pi,broker_company_master lm where pi.agency_code=lm.agency_code and  pi.agency_code = ?";		
		try {		
			brokerDetails = runner.multipleSelection(sql,args);
		}
        catch (Exception e) {
            e.printStackTrace();	
		}        
		return brokerDetails;
	}

	public String[][] getCommisionData(String ucode) 
	{		
		String sql = "";
		String[][] commisionDetails = new String[0][0];	
        String[] args = new String[1];
        args[0] = ucode;
		sql = "select lud.PRODUCT_ID,lud.COMMISSION,lud.INSURANCE_END_LIMIT,lm.USER_ID_CREATION,lm.AC_EXECUTIVE_CREATION,lm.REFERAL,lud.status,lud.SPECIAL_DISCOUNT,lud.MIN_PREMIUM_AMOUNT,lud.BACK_DATE_ALLOWED,lud.LOADING_OF_PREMIUM,lud.PROVISION_FOR_PREMIUM,lud.DISCOUNT_OF_PREMIUM,lud.FREIGHT_RATE_OPTION,lud.FREIGHT_SCHEDULE_OPTION,lud.FREIGHT_DEBIT_OPTION,lud.FREIGHT_ADMIN_OPTION,lud.FREIGHT_AUOTO_OPTION from login_master lm,login_user_details lud where lm.agency_code=lud.agency_code and  lud.agency_code = ? order by lud.product_id";		
		try {
			commisionDetails = runner.multipleSelection(sql,args);
		}
        catch (Exception e) {
		   e.printStackTrace();
		}       
		return commisionDetails;
	}

	public String[][] getLoadingPremium() 
    {		
		String sql = "";
		String[][] loadingDetails = new String[0][0];
		sql = "select PERCENT from CONSTANT_DETAIL where CATEGORY_ID='35' order by PERCENT";
		try {		
			loadingDetails = runner.multipleSelection(sql);
		}
        catch (Exception e) {
            e.printStackTrace();
		}       
		return loadingDetails;
	}

	public String[][] getCommisionData123(String login_id) {		
		String[][] commisionDetails = new String[0][0];		
        String[] args = new String[1];
        args[0] = login_id;
		String sql = "select lud.PRODUCT_ID,lud.COMMISSION,lud.INSURANCE_END_LIMIT,lm.USER_ID_CREATION,lm.AC_EXECUTIVE_CREATION,lm.REFERAL,lud.status,lud.SPECIAL_DISCOUNT,lud.MIN_PREMIUM_AMOUNT,lud.BACK_DATE_ALLOWED from  login_master lm,login_user_details lud where lm.login_id=lud.login_id and lud.login_id = ? order by lud.product_id";		
		try {			
			commisionDetails = runner.multipleSelection(sql,args);
		} 
        catch (Exception e) {
		     e.printStackTrace();
		}        
		return commisionDetails;
	}

	public boolean checkBrokerId(String brokerId) {
		boolean check = false;		
        String res = "";
        String[] args = new String[1];
        args[0] = brokerId;
		String sql = "select count(*) from login_master where login_id = ?";		
		try {			
            res = runner.singleSelection(sql,args);
            res  = (res==null || res.equalsIgnoreCase("null") || res.equalsIgnoreCase("") || res.equalsIgnoreCase(""))?"0":res;
            int c  = Integer.parseInt(res);
            if (c > 0) {
					check = true;
			} 
            else {
					check = false;
			}			
		} 
        catch (Exception e) {
            e.printStackTrace();
		}       
		return check;
	}

	public boolean checkOld(String brokerId, String password) 
    {
		passwordEnc pass = new passwordEnc();
		String encpass = pass.crypt(password.substring(0, 3), password);
		boolean check = false;		
		String pass123 = "";
        String[] args = new String[1];
        args[0] = brokerId;
		String sql = "select password  from  login_master where  agency_code =  ?";		
		try {		
			pass123 = runner.singleSelection(sql,args);		
			if (pass123.trim().equals(encpass.trim())) {
				check = true;
			}
            else {
				check = false;
			}
		}
        catch (Exception e) {
			e.printStackTrace();
		}       
		return check;
	}

	public boolean changePassword(String brokerId, String password) 
     {
		passwordEnc pass = new passwordEnc();
		String encpass = pass.crypt(password.substring(0, 3), password);
		boolean check = false;
		Connection con = null;		
        PreparedStatement pstmt = null;
		String sql = "update login_master set password= ? where agency_code = ?";		       
		try {            
		        con = DBConnection.getInstance().getDBConnection();
			    pstmt =  con.prepareStatement(sql);
                pstmt.setString(1,encpass);
                pstmt.setString(2,brokerId);
			    int n = pstmt.executeUpdate();

			if (n > 0) {
				check = true;
			} else {
				check = false;
			}
		} 
        catch (Exception e) {
			e.printStackTrace();
		} 
        finally {
			/*try {
				if (pstmt != null)
					psmt.close();
            }
            catch(Exception ex){
                ex.printStackTrace();
            } */
            try{
				if (con != null)
					con.close();
			}
            catch (Exception e) {
				e.printStackTrace();
			}
		}
		return check;
	}

	public boolean changePasswordWithDate(String brokerId, String password) 
    {
		passwordEnc pass = new passwordEnc();
		String encpass = pass.crypt(password.substring(0, 3), password);
		boolean check = false;		
		String[][] qry1 = new String[0][0];
        String sql = "";
        String[] args = new String[1];
        args[0] = brokerId;
        String temp = "sysdate";
		//temp = runner.getSysdate(runner.singleSelection("select nvl(branch_code,'01') from BROKER_COMPANY_MASTER where agency_code in ('"+brokerId+"')"));		
		try {			
			try{
			            sql = "select password,lpass1,lpass2,lpass3,lpass4 from login_master where agency_code = ?";			       
			            qry1 = runner.multipleSelection(sql,args);
			}
			catch(Exception e){				
                e.printStackTrace();
			}			
			if (qry1.length > 0) {
                args = new String[7];
                args[0] =  qry1[0][4];
                args[1] =  qry1[0][3];
                args[2] =  qry1[0][2];
                args[3] =  qry1[0][1];
                args[4] =  qry1[0][0];
                args[5] =  encpass;
                args[6] =  brokerId;

				sql = "update login_master set  lpass5 = ?, lpass4 = ?, lpass3 = ?,lpass2 = ? ,lpass1 = ?,password = ?,passdate = "+temp+"+45 ,status='Y' where agency_code = ?";

				runner.multipleUpdation(sql,args);
				check = true;				
			}
            else {
				check = false;
			}			
		} 
        catch (Exception e) {			
            e.printStackTrace();
		}				
		return check;
     }

	public boolean updateStatus(String status, String brokerId,	String selectedType)
    {
		boolean check = false;
		String usertype = "";
		if (selectedType.equals("5")) {
			usertype = "Freight";
		}
		Connection con=null; 
        PreparedStatement  pstmt = null;
		String sql = "update login_master set status = ? where agency_code = ? and lower(usertype) = ?";		
		try {
            con = DBConnection.getInstance().getDBConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,status);        
            pstmt.setString(2,brokerId);    
            pstmt.setString(3,usertype);  
			
			int n = psmt.executeUpdate();
			if (n > 0) {
				check = true;
			} else {
				check = false;
			}
		}
        catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				if (con != null)
					con.close();
			}
            catch (Exception e) {
				e.printStackTrace();
			}
            try{
                if(pstmt != null)
                    pstmt.close();
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
		}
		check = false;		
		String sql1 = "update personal_info set status = ? where agency_code = ? and application_id = ?";		
		try {
			con = DBConnection.getInstance().getDBConnection();
			pstmt = con.prepareStatement(sql1);

            pstmt.setString(1,status);
            pstmt.setString(2,brokerId);
            pstmt.setString(3,selectedType);

			int n = pstmt.executeUpdate();

			if (n > 0) {
				check = true;
			} else {
				check = false;
			}
		} 
        catch (Exception e) {
			e.printStackTrace();
		} 
        finally
		{
			try {
				if (con != null)
					con.close();
			}
            catch (Exception e) {
				e.printStackTrace();
			}
            try{
                if(pstmt != null)
                    pstmt.close();
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
		}
		return check;
	}

	public String[][] getUserProducts(String loginId) 
    {		
		String[][] products = new String[0][0];
        String[] args = new String[1];
        args[0] = loginId;
		String sql = "select PRODUCT_ID,PRODUCT_NAME,COMPANY_ID from product_master where status='Y' and PRODUCT_ID in (select product_id from login_user_details where agency_code=(select agency_code from login_master where  login_id =? ) and status='Y')";		
		try {			
			products = runner.multipleSelection(sql,args);
		}
        catch (Exception e) {
			e.printStackTrace();
		}        
		return products;
	}

	public String[][] getProducts(String userType,String branch)
	{		
		String[][] products = new String[0][0];
		String pid = "";
        String[] args = new String[2];        
		if (userType.equalsIgnoreCase("5"))
			pid = "3";

        args[0] = pid;
        args[1] = branch;
		String sql = "select PRODUCT_ID,PRODUCT_NAME,COMPANY_ID from product_master where status='Y' and PRODUCT_ID  = ? and branch_code = ?";
		try {		
			products = runner.multipleSelection(sql,args);
		} 
        catch (Exception e) {
             e.printStackTrace();			
		}        
		return products;
	}

	public String[][] getBrokers(String login_id) 
    {		
		String sql = "";
		String[][] brokers = new String[0][0];
        String[] args = new String[1];
      
		if (!login_id.equalsIgnoreCase("ALL")) {
            args[0] = getAgencyCode(login_id) ;
			sql = "select a.CUSTOMER_ID,a.APPLICATION_ID,b.COMPANY_NAME,b.agency_code from personal_info a,BROKER_COMPANY_MASTER b where a.agency_code=b.agency_code and a.APPLICATION_ID='2' and a.status='Y' and b.agency_code = ?";
		}
        else if (disStatus.length() > 0 && !disStatus.equals("A") 	&& !disStatus.equals("0")) {
            args[0] = disStatus;
			sql = "select CUSTOMER_ID,APPLICATION_ID,FIRST_NAME,agency_code from personal_info where APPLICATION_ID='2' and status = ?";
		}
        else {
            args[0] = "2";
			sql = "select CUSTOMER_ID,APPLICATION_ID,FIRST_NAME,agency_code from personal_info where APPLICATION_ID = ?";
		}		
		try {			
			brokers = runner.multipleSelection(sql,args);
		}
        catch (Exception e) {
			e.printStackTrace();
		}        
		return brokers;
	}

	public String[][] getUsers(String loginId)
    {	
		String[][] brokers = new String[0][0];
		String sql = "";
        String[] args = new String[1];
        
        try {
		// String sql="select CUSTOMER_ID,APPLICATION_ID,FIRST_NAME,LOGIN_ID
		// from personal_info where APPLICATION_ID='3' and status='Y'";

		if (!loginId.equalsIgnoreCase("ALL")) {
			// sql="select CUSTOMER_ID,APPLICATION_ID,FIRST_NAME,LOGIN_ID from
			// personal_info where status='Y' and LOGIN_ID='"+loginId+"'";
            args[0] = getAgencyCode(loginId);
			sql = "select CUSTOMER_ID,APPLICATION_ID,FIRST_NAME,agency_code from personal_info where  status='Y' and agency_code =  ?";
             brokers = runner.multipleSelection(sql,args);
		}
        else {
			// sql="select
			// pi.CUSTOMER_ID,pi.APPLICATION_ID,pi.FIRST_NAME,lm.created_by from
			// personal_info pi,login_master lm where pi.LOGIN_ID=lm.login_id";
			sql = "select pi.CUSTOMER_ID,pi.APPLICATION_ID,pi.FIRST_NAME,pi.oa_code from personal_info pi,login_master lm where  pi.LOGIN_ID=lm.login_id";
            brokers = runner.multipleSelection(sql);
		}			
	   }
       catch (Exception e) {
			e.printStackTrace();
	   }      
		return brokers;
	}

	public String[][] getUsersAccount(String loginId, String appId) {
		
		String[][] brokers = new String[0][0];
		String sql = "";
        String[] args = new String[2];
        args[0] = appId;
        args[1] =  getOACode(loginId);

		sql = "select CUSTOMER_ID,APPLICATION_ID,FIRST_NAME,agency_code from personal_info where  APPLICATION_ID = ? and oa_code = ? and status='Y'";		
		try {		
			brokers = runner.multipleSelection(sql,args);
		}
        catch (Exception e) {		
			e.printStackTrace();
		}        
		return brokers;
	}

	public String[][] getSelected(String loginId, String appId, String status) 
    {
	    String[] args = new String[3];
		String[][] brokers = new String[0][0];	
		String sql = "";
        args[0] = appId;
        args[1] = status;
        args[2] = getOACode(loginId);
		sql = "select CUSTOMER_ID,APPLICATION_ID,FIRST_NAME,agency_code from personal_info where  APPLICATION_ID = ? and status = ?  and oa_code = ?";		
		try {		
			brokers = runner.multipleSelection(sql,args);
		}
        catch (Exception e) {
			e.printStackTrace();
		}         
		return brokers;
	}

	public String[][] getAccountEx(String loginId) 
    {	
		String sql = "";
		String[][] brokers = new String[0][0];
        String[] args = new String[1];
		// String sql="select CUSTOMER_ID,APPLICATION_ID,FIRST_NAME,LOGIN_ID
		// from personal_info where APPLICATION_ID='4' and status='Y' ";
        try {
                if (!loginId.equalsIgnoreCase("ALL")) {
                    args[0] = getOACode(loginId);
                    sql = "select CUSTOMER_ID,APPLICATION_ID,FIRST_NAME,agency_code from personal_info where APPLICATION_ID='4' and status='Y' and LOGIN_ID in (select login_id from login_master where created_by = ?)";
                    brokers = runner.multipleSelection(sql,args);
                }
                else {
                    sql = "select pi.CUSTOMER_ID,pi.APPLICATION_ID,pi.FIRST_NAME,lm.created_by from personal_info pi,login_master lm where pi.APPLICATION_ID='4' and pi.LOGIN_ID=lm.login_id";
                    brokers =runner.multipleSelection(sql);
                }
		}
        catch (Exception e) {
            e.printStackTrace();
		}       
		return brokers;
	}

	public String getMailId(String brokerId) 
    {	
		String email = "";
        String[] args = new String[1];
        args[0] = brokerId;
		String sql = "select EMAIL from personal_info where  agency_code = ?";		
		try {
				email = runner.singleSelection(sql,args);			
		} 
        catch (Exception e) {
			e.printStackTrace();
		}        
		return email;
	}

	public boolean checkUserCode(String ucode, String table) {
		boolean check = false;		
		String sql = "select count(*) from " + table + " where AGENCY_CODE='"	+ ucode + "'";		
        String res = "";
		try {			
            res = runner.singleSelection(sql);
            res = (res==null || res.equalsIgnoreCase("null") || res.equalsIgnoreCase(""))?"0":res;
            int c = Integer.parseInt(res);
            if (c > 0) {
					check = true;
			}
            else {
					check = false;
			}
		}
        catch (Exception e) {
		     e.printStackTrace();	
		}        
		return check;
	}

	public boolean checkUserCode123(String ucode, String table) 
    {
		boolean check = false;
        String res = "";
		String sql = "select count(*) from " + table + " where AGENCY_CODE='"	+ ucode + "'";		
		try {	
            res = runner.singleSelection(sql);
            res = (res==null || res.equalsIgnoreCase("null") || res.equalsIgnoreCase(""))?"0":res;
            int c = Integer.parseInt(res);

            if (c > 0) {
					check = true;
			} else {
					check = false;
			}			
		}
        catch (Exception e) {		
            e.printStackTrace();
		}		
		return check;
	}

	public int autoGenCustId(String codes, String table) 
    {
		int customerId = 0;
        String res = "";
		String sql = "select max(" + codes + ")+1 from " + table + "";		
		try {
             res = runner.singleSelection(sql);
             res = (res==null || res.equalsIgnoreCase("null") || res.equalsIgnoreCase(""))?"0":res;
            customerId = Integer.parseInt(res);		
            return customerId;			
		} 
        catch (Exception e) {
		    e.printStackTrace();	
		}        
		return customerId;
	}

	public String validateLoginCreation() {		
		dataCollection data = new dataCollection();
		String error = "";
		String values = null;
		try {
			if (brokerId.equals("") || brokerId == null || brokerId.equalsIgnoreCase("null") || brokerId.length() < 5  || brokerId.indexOf(" ")!= -1) {
				error = error + "<BR>*Enter Valid UserId";
			} else if (StringUtil.checkSpecial(brokerId)) {
				error = error + "<BR>*Spcial Chars Not Allowed";
			} else if (!mode.equalsIgnoreCase("edit")) {
				if (checkBrokerId(brokerId)) {
					error = error + "<BR>*This Login Id Already Existed";
				}
			}
			if (password.equals("") || password == null || password.equalsIgnoreCase("null") || password.indexOf(" ")!= -1) 
			{
				error = error + "<BR>*Enter Valid password";
			} else if (password.length() < 7) {
				error = error + "<BR>* Password should be Min 7 Letters";
			} else if (StringUtil.checkSpecial(password)) {
				error = error + "<BR>*Password as Spcial Chars not Allowed ";
			} else if (!validPassword(password)) {
				error = error + "<BR>*Please Enter Valid Password";
			}
			if (!password.equals(retypePassword)) {
				error = error + "<BR>* Entered passwords are not Matched";
			}
		} 
        catch (Exception e) {
			e.printStackTrace();
		} 
		return error;
	}

	public String LoginIdStatus(String ucode) {
		String status = "";		
        String[] args = new String[1];
        args[0] = ucode;
		String sql = "select login_id from personal_info where agency_code = ?";		        
		try {						
				status = runner.singleSelection(sql,args);
		} 
        catch (Exception e) {
		        e.printStackTrace();
		}        
		return status;
	}
	
	public String[][] getUserCode(String branch) 
	{
		String CusAgency[][] = new String[0][0];
        String[] args = new String[1];
        args[0] = branch;
		String sql = "select nvl(detail_name,'0') from constant_detail where CATEGORY_ID='37' and CATEGORY_DETAIL_ID='2' and status='Y' and branch_code = ? ";
		try {
			CusAgency = runner.multipleSelection(sql,args);
			int tepmCusAgency = Integer.parseInt(CusAgency[0][0]);
			tepmCusAgency = tepmCusAgency+1;			
		}
		catch (Exception e) {
			 e.printStackTrace();
		}
		return CusAgency;
	}	

	public String[][] getUsersType(String userType, String loginPersonId) 
    {
        String[] args = new String[2];
        args[0] = userType;
        args[1] = getOACode(loginPersonId);
	
		String[][] brokers = new String[0][0];	
		String sql = "select CUSTOMER_ID,APPLICATION_ID,FIRST_NAME,LOGIN_ID,AGENCY_CODE,FREIGHT_FORWARD_USER from personal_info where APPLICATION_ID = ? and oa_code = ? and status='Y' and LOGIN_ID!='NONE' and LOGIN_ID!='NON'";		
		try {			
			brokers = runner.multipleSelection(sql,args);
		} 
        catch (Exception e) {
			 e.printStackTrace();
		}        
		return brokers;
	}

	public String getOACode(String logpersonId) {		
         String[] args = new String[1];
         args[0] = logpersonId;
		String oacode = "";
		String sql = "select oa_code from login_master where login_id = ?";	
		try {						
			oacode = runner.singleSelection(sql,args);
		} 
        catch (Exception e) {
			 e.printStackTrace();
		}		
		return oacode;
	}

	public String getAgencyCode(String logpersonId) {		
		String oacode = "";
        String[] args = new String[1];
        args[0] = logpersonId;
		String sql = "select agency_code from login_master where login_id = ?";
		try {						
				oacode =  runner.singleSelection(sql,args);
		} 
        catch (Exception e) {
			 e.printStackTrace();
		}		
		return oacode;
	}

	public boolean checkCode(String code, String logedPersonId) 
    {
		boolean check = false;	
        String[] args = new String[2];        
         String res = "";
		String sql = "select count(*) from personal_info where agency_code = ? and APPLICATION_ID in ('5') and oa_code = ?";
		try {			
            args[0] = code;
            args[1] = getOACode(logedPersonId);
            res = runner.singleSelection(sql,args);
            res = (res==null || res.equalsIgnoreCase("null") || res.equalsIgnoreCase(""))?"0":res;
            int c = Integer.parseInt(res);             			
			if (c > 0) {
					check = true;
			}
            else {
					check = false;
			}			
		}
        catch (Exception e) {
            e.printStackTrace();
		}         
		return check;
	}

	public boolean checkCreationCode(String code) {
		boolean check = false;	
        String[] args = new String[1];
        args[0] = code;     
          String res = "";
		String sql = "select count(*) from personal_info where agency_code = ?";
		try {
            res = runner.singleSelection(sql,args);
            res = (res==null || res.equalsIgnoreCase("null") || res.equalsIgnoreCase(""))?"0":res;
            int c = Integer.parseInt(res);        		
            if (c > 0) {
                check = true;
            } else {
                check = false;
            }			
		}
        catch (Exception e) {
		   e.printStackTrace();
		}        
		return check;
	}

	public boolean validPassword(String value) {		
		boolean b = false;
		String validChar = "";
		int p = 0;
		int j = 0;		
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

	public static boolean checkEmailSpecial(String str) {
		boolean splChar = false;
		if (!(str.trim().length() == 0)) {
			String value = str.trim();
			char[] valuechar1 = value.toCharArray();
			for (int k = 0; k < value.length(); k++) {
				if (valuechar1[k] != '-' && valuechar1[k] != '_'
						&& valuechar1[k] != '@' && valuechar1[k] != '.') {
					int number = Character.getNumericValue(valuechar1[k]);
					int val = valuechar1[0];
					// char under='_';
					if (number < 0) {
						splChar = true;
						break;
					} // end of numer if
				}
			}
		} 
        else {
			splChar = false;
		}
		return splChar;
	}

	public String getMaxCustomerId(String branch) 
    {		
		String current_no = null;
        String[] args = new String[1];
		try {
            args[0] = branch;
			current_no = runner.singleSelection("select nvl(max(current_no)+1,max(start_no)) from policyno_generate where type_id='3' and status='Y' and branch_code = ?",args);

            args = new String[3];
            args[0] = current_no;
            args[1] = current_no;
            args[2] = branch;
			runner.multipleUpdation("update policyno_generate set current_no = ? , remarks = ? where type_id='3' and status='Y' and branch_code = ?",args);
		}
        catch (Exception e) {		
             e.printStackTrace();
		}				
		return current_no;
	}

	// new added By Brahma
	public boolean chkpas(String pas, String pass, String brahma)	throws Exception 
    {		
		passwordEnc pass1 = new passwordEnc();
		String encpass = pass1.crypt(pass.substring(0, 3), pass);	
		boolean retpas = false;
        String[] args  = new String[1];
        args[0] = pas;
		try {			
			String sql = "select password,lpass1,lpass2,lpass3,lpass4,lpass5 from login_master where agency_code = ?";	
			String[][] upas = runner.multipleSelection(sql,args);
            
			if (upas.length > 0) {
				if ((encpass.equals(upas[0][0]))
						|| (encpass.equals(upas[0][1]))
						|| (encpass.equals(upas[0][2]))
						|| (encpass.equals(upas[0][3]))
						|| (encpass.equals(upas[0][4]))
						|| (encpass.equals(upas[0][5]))) {
					retpas = true;
				} else {
					retpas = false;
				}
			}
		} 
        catch (Exception e) {
            e.printStackTrace();			
		}        
		return retpas;
	}

	public String[][] getBrokersInAdmin() 
    {		
		String[][] brokers = new String[0][0];		
		String sql = "select bcm.CUSTOMER_ID,bcm.CONTACT_PERSON,bcm.COMPANY_NAME,pi.login_id from BROKER_COMPANY_MASTER bcm,personal_info pi where bcm.agency_code=pi.agency_code and APPLICATION_ID='2' and pi.login_id is not null order by lower(bcm.COMPANY_NAME) ";		
		try {		
			brokers = runner.multipleSelection(sql);
		} 
        catch (Exception e) {
		     e.printStackTrace();
		}       
		return brokers;
	}

	public String[][] getBrokersInAdmin(String branchCode)
	{		
		String[][] brokers = new String[0][0];		
		String sql = "select bcm.CUSTOMER_ID,bcm.CONTACT_PERSON,bcm.COMPANY_NAME,pi.login_id from BROKER_COMPANY_MASTER bcm,personal_info pi where bcm.agency_code=pi.agency_code and APPLICATION_ID='2' and pi.login_id is not null and pi.login_id in(select login_id from login_master where status!='N' and oa_code in(select agency_code from broker_company_master where branch_code in ("+branchCode+")))  order by lower(bcm.COMPANY_NAME) ";	
		try {		
			brokers = runner.multipleSelection(sql);
		} 
        catch (Exception e) {
			e.printStackTrace();
		}        
		return brokers;
	}


	public String[][] getsLCDetailsByOpenCover(String opencover, String lcNo) 
    {
		String[][] brokerDetails = new String[0][0];	
        String[] args = new String[2];
        args[0] = lcNo;
        args[1] = opencover;
		String sql = "select  OPEN_COVER_NO,BANK_ID,LC_NUMBER,LC_DATE,LC_AMOUNT,ENTRY_DATE,EXPIRY_DATE,EFFECTIVE_DATE,AMEND_ID,REMARKS,STATUS,LC_ID from open_cover_lc_master where lc_id = ? and open_Cover_no = ?";		
		try {			
			brokerDetails = runner.multipleSelection(sql,args);
		}
        catch (Exception e) {		
            e.printStackTrace();
		}		
		return brokerDetails;
	}

	public String[][] getLcDetails(String openID, String lcNo) 
    {
	    String[] args = new String[2];	
		String[][] brokerDetails = new String[0][0];		
		String sql = "select OPEN_COVER_NO,BANK_ID,LC_NUMBER,to_char(LC_DATE,'DD') DAY,to_char(LC_DATE,'MM') MONTH,to_char(LC_DATE,'YYYY') YEAR,LC_CURRENCY_ID,LC_AMOUNT,ENTRY_DATE,EXPIRY_DATE,EFFECTIVE_DATE,AMEND_ID,REMARKS,STATUS,LC_ID,to_char(EXPIRY_DATE,'DD') DAY,to_char(EXPIRY_DATE,'MM') MONTH,to_char(EXPIRY_DATE,'YYYY') from OPEN_COVER_LC_MASTER  where  OPEN_COVER_NO = ? and  LC_ID = ?";		
        args[0] = openID;
        args[1] = lcNo; 
		try {			
			brokerDetails = runner.multipleSelection(sql,args);
		}
        catch (Exception e) {		
            e.printStackTrace();
		}		
		return brokerDetails;
	}

	public String[][] getLCs(String openID) 
    {		
		String[][] brokerDetails = new String[0][0];
        String[] args = new String[1];
        args[0] = openID;
		String sql = "select OPEN_COVER_NO,BANK_ID,LC_NUMBER,LC_DATE,LC_AMOUNT,ENTRY_DATE,EXPIRY_DATE,EFFECTIVE_DATE,AMEND_ID,REMARKS,STATUS,LC_ID,LC_CURRENCY_ID from OPEN_COVER_LC_MASTER  where  OPEN_COVER_NO = ? and status='Y' and LC_NUMBER not in ('None','NONE')";		
		try {		
			brokerDetails = runner.multipleSelection(sql,args);
		}
        catch (Exception e) {		
            e.printStackTrace();
		}		
		return brokerDetails;
	}

	public String getBname(String bankid) {		
		String email = "";
        String[] args = new String[1];
        args[0] = bankid;
		String sql = "select bank_name from open_cover_bank_master where bank_id = ? and status='Y'";		
		try {			
				email = runner.singleSelection(sql,args);
		}
        catch (Exception e) {
			 e.printStackTrace();
		}        
		return email;
	}

	public String getCName(String cid) 
    {		
		String email = "";
        String[] args = new String[2];
        args[0] = cid;
        args[1] = cid;
		String sql = "select currency_name from currency_master where currency_id = ? and status='Y' and amend_id=(select max(amend_id) from currency_master where currency_id = ? and status='Y')";		
		try {					
				email = runner.singleSelection(sql,args);		
		}
        catch (Exception e) {
			 e.printStackTrace();
		}        
		return email;
	}

	public String[][] getCertificates(String openID, String lcnumber) {
		String[][] brokerDetails = new String[0][0];
        String[] args = new String[2];
        args[0] = openID;
        args[1] = lcnumber;
		String sql = "select a.open_cover_no,a.policy_no,b.total_sum_insured,b.exchange_rate,b.total_sum_insured*b.exchange_rate suminsured,b.open_lc_id,a.login_id,c.lc_number,c.LC_AMOUNT from position_master a,marine_Data b,open_Cover_lc_master c where a.application_no=b.application_no and b.open_lc_id=c.lc_id and a.open_cover_no =c.open_cover_no and a.open_cover_no = ? and c.lc_number = ? and  a.status='P'";	
		try {			
			brokerDetails = runner.multipleSelection(sql,args);
		} 
        catch (Exception e) {		
            e.printStackTrace();
		}	
		return brokerDetails;
	}

	public String getProductById(String pid) 
    {
		String pname = "";		
        String[] args = new String[1];
        args[0] = pid; 
		String sql = "select product_name from product_master where product_id = ? and status='Y'";
		try {				          
				pname = runner.singleSelection(sql,args);		
				pname = (pname==null || pname.equalsIgnoreCase("null") || pname.equalsIgnoreCase(""))?"No Product":pname;		
		}
        catch (Exception e) {
			e.printStackTrace();
		}        
		return pname;
	}

	public String getUserType(String user) throws Exception {		
		String res = "";		
        String[] args = new String[1];
        try{
                args[0] = user;
		        res = runner.singleSelection("select usertype from login_master where login_id = ?", args);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
		return res;
	}

	public String[][] getEmirates() {
		String[][] countries = new String[0][0];
		String sql = "select city_id,city_name  from city_master where country_id='1' order by city_name";
		try {			
			countries = runner.multipleSelection(sql);
		}
        catch (Exception e) {
			e.printStackTrace();
		}	
		return countries;
	}

	public String[][] getNtionalities() {
		
		String[][] countries = new String[0][0];		
		String sql = "select COUNTRY_ID,NATIONALITY_NAME from COUNTRY_MASTER where amend_id+country_id in( select max(amend_id)+country_id from country_master group by country_id) order by NATIONALITY_NAME ";
		try {		
			countries = runner.multipleSelection(sql);
		} 
        catch (Exception e) {
		         e.printStackTrace();
		}		
		return countries;
	}

	public String[][] getTitles(String branch) {	
		String[][] countries = new String[0][0];
        String[] args = new String[1];
         args[0] = branch;
		String sql = "select TITLE_ID,TITLE_NAME  from TITLE_MASTER where branch_code = ? and status='Y' order by TITLE_NAME ";
		try {			
			countries = runner.multipleSelection(sql,args);
		} 
        catch (Exception e) {
			   e.printStackTrace();
		}	
		return countries;
	}

	public String[][] getCountries() {		
		String[][] countries = new String[0][0];		
		String sql = "select COUNTRY_ID,COUNTRY_NAME  from COUNTRY_MASTER where amend_id||country_id in( select max(amend_id)||country_id from country_master group by country_id)order by COUNTRY_NAME";
		try {			
			countries = runner.multipleSelection(sql);
		} 
        catch (Exception e) {
			 e.printStackTrace();
		}        
		return countries;
	}

	public String[][] getBrokers() {	
		String[][] brokers = new String[0][0];
		String sql = "select CUSTOMER_ID,APPLICATION_ID,FIRST_NAME,LOGIN_ID from personal_info where APPLICATION_ID='2' and status='Y'";
		try {
			brokers =runner.multipleSelection(sql);
		}
        catch (Exception e) {
		    e.printStackTrace();
		}       
		return brokers;
	}

	// Rajesh For Freight Forwarder
	public boolean getBrokerHasFreight(String user) throws Exception {		
		String res = "";
        String[] args = new String[1];
        args[0] = user;
		try {
			res = runner.singleSelection("select count(*) from login_master where OA_CODE in(select AGENCY_CODE from LOGIN_USER_DETAILS where LOGIN_ID = ?) and usertype='Freight'",args);
			if (res != null) {
				if (Integer.parseInt(res) > 0)
					return true;
				else
					return false;
			}
            else
				return false;
		}
        catch (Exception e) {
			return false;
		}
	}

	// Penetration Test
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
		catch(Exception e){
			System.out.println("specialCharacterValidation in Customer Info.java1 "+value);
			return flag;
		}		
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
		return flag;
	}
} // Class