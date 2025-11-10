package com.maan.broker;

import java.io.PrintWriter;
import java.util.HashMap;

import com.maan.common.error.ErrorInfo;
import com.maan.common.password.passwordEnc;
import com.maan.common.util.StringUtil;
import com.maan.services.util.runner;

public class UserCreationBean extends ErrorInfo 
{
	private PrintWriter out = null;
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
	private String fdCode = "";
	
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
	public void setOut(PrintWriter out) {
		this.out = out;
	}
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
	public void setCity(String city) {
		this.city = city;
	}
	public String getCity() {
		return city;
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
	
	HashMap proDetails = new HashMap();
	private HashMap officeHash;

	public void setOfficeHash(HashMap officeHash){
		this.officeHash = officeHash;
	}
	public HashMap getOfficeHash(){
		return officeHash;
	}
	public void setProDetails(HashMap proDetails) {
		this.proDetails = proDetails;
	}
	public HashMap getProDetails() {
		return proDetails;
	}

	public String validate() 
	{
		com.maan.common.util.dataCollection data = new com.maan.common.util.dataCollection();
		String error = "";
		String values = null;
		boolean flag = false;
		try 
		{
			if ("0".equalsIgnoreCase(userType))
				error = error + "<br>*" + runner.getErrormsg("54");

			if (userType.equals("3")) 
			{
				values = data.validString(ucode, false);
				if ("needed".equalsIgnoreCase(values))
					error = error + "<br>*" + runner.getErrormsg("57");
				else if (StringUtil.checkSpecial(ucode)) {
					error = error+ "<BR>*Spcial Chars Not Allowed For User Code";
				}
				if (!mode.equalsIgnoreCase("edit")) {
					if (checkCreationCode(ucode)) {
						error = error + "<BR>*This User Code Already Existed";
					}
				}
			}
			else if (userType.equals("4")) 
			{
				values = data.validString(ucode, false);
				if ("needed".equalsIgnoreCase(values))
					error = error + "<br>*" + runner.getErrormsg("58");
				else if (StringUtil.checkSpecial(ucode)) {
					error = error
							+ "<BR>*Spcial Chars Not Allowed For AccountEx Code";
				}
				if (!mode.equalsIgnoreCase("edit")) {
					if (checkCreationCode(ucode)) {
						error = error
								+ "<BR>*This AccountEx Code Already Existed";
					}
				}
			}

			if ("0".equalsIgnoreCase(emirate))
				error = error + "<br>*" + runner.getErrormsg("25");
			else if ("others".equalsIgnoreCase(emirate)) {
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
			
			values = data.validString(firstName, false);
			if ("needed".equalsIgnoreCase(values))
				error = error + "<br>*" + runner.getErrormsg("3");
		

			if (!dobDay.equalsIgnoreCase("0")
					|| !dobMonth.equalsIgnoreCase("0")
					|| !dobYear.equalsIgnoreCase("0")) {
				values = data
						.checkDate(dobDay + "/" + dobMonth + "/" + dobYear);
				if ("Invalid".equalsIgnoreCase(values))
					error = error + "<br>*" + runner.getErrormsg("8");
			}

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
			
			values = data.validString(poBox, true);

			if ("needed".equalsIgnoreCase(values))
				error = error + "<br>*" + runner.getErrormsg("27");
			else if ("Invalid".equalsIgnoreCase(values))
				error = error + "<br>*" + runner.getErrormsg("28");
			else if ("needed".equalsIgnoreCase(data.validLength(poBox, 1)))
				error = error + "<br>*" + runner.getErrormsg("29");

		}
		catch (Exception e) 
		{
			System.out.println("Exception in validation" + e.toString());
		}
		return error;
	}

	public String insertBrokerEntry(String branch) 
	{
		String user_Id = "1";
		String Query = "";
		java.util.Date dd = new java.util.Date();
		java.text.SimpleDateFormat simpleFormatter = new java.text.SimpleDateFormat("dd-MM-yyyy");
		String process = "NOT";

		try 
		{
			String customerId = "";
			String args[] = new String[2];
			args[0] = ucode;
			args[1] = userType;
			String qry = "select count(*) from personal_info where agency_code=? and application_id=?";
			qry = runner.singleSelection(qry,args);

			if ("0".equalsIgnoreCase(qry)|| "DIDN'T SELECTED".equalsIgnoreCase(qry)) 
			{
				String date123 = "";
				if (!dobDay.equals("") && !dobDay.equals("0")&& !dobMonth.equals("") && !dobMonth.equals("0")
						&& !dobYear.equals("") && !dobMonth.equals("0")) 
				{
					date123 = com.maan.common.util.OracleDateConversion.ConvertDate(dobDay + "-" + dobMonth + "-"+ dobYear);
				}

				firstName = firstName.replaceAll("'", "''");
				lastName = lastName.replaceAll("'", "''");

				String oacode = getOACode(loginPersonId);
				String[] qryvalues = new String[26];
				//String[] qryUpdateValues = new String[2];
				customerId = getMaxCustomerId(branch);
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
				qryvalues[20] = com.maan.common.util.OracleDateConversion.ConvertDate("" + simpleFormatter.format(dd));	
				if(userType.equalsIgnoreCase("1")){
					qryvalues[21] = getMasterCustomerLoginId(fdCode);
				}
				else{
					qryvalues[21] = "NONE";
				}
					
				qryvalues[22] = ucode;
				qryvalues[23] = oacode;
				qryvalues[24] = city;
				qryvalues[25] = fdCode;

				qry = "insert into personal_info(customer_id,application_id,title,first_name,last_name,amend_id,nationality,dob,gender,telephone,mobile,fax,email,address1,address2,occupation,pobox,country,emirate,status,entry_date,login_id,AGENCY_CODE,OA_CODE,city,fd_code)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

				try 
				{
					runner.multipleInsertion(qry, qryvalues);
				}
				catch (Exception e) 
				{
						System.out.println("ERROR autoGenCusId  "+ e.toString());e.printStackTrace();
				}
				
				int ii = 0;
				try 
				{
					/*if(userType.equalsIgnoreCase("3") || userType.equalsIgnoreCase("4") )
					{
						ii = Integer.parseInt(ucode);
						qryUpdateValues[0] = "" + (++ii);
						qryUpdateValues[1] = branch;
						qry = "update CONSTANT_DETAIL set DETAIL_NAME=? where CATEGORY_ID=37 and CATEGORY_DETAIL_ID=1 and branch_code=?";
						runner.multipleUpdation(qry, qryUpdateValues);
					}
					else if(userType.equalsIgnoreCase("1"))
					{
						String tempCode = ucode.replaceAll("c","");
						int tepmCusAgency = Integer.parseInt(tempCode);
						String args1[] = new String[2];
						tepmCusAgency = tepmCusAgency+1;
						args1[0] = ""+tepmCusAgency;
						args1[1] = branch;
						runner.multipleUpdation("update constant_detail set detail_name=? where CATEGORY_ID='37' and CATEGORY_DETAIL_ID='3' and status='Y' and branch_code=?",args1);
					}*/
					System.out.println("branch........."+branch);
					String[] qryUpdateValues = new String[2];
					if(userType.equalsIgnoreCase("3") || userType.equalsIgnoreCase("4") )
					{
//						ii = Integer.parseInt(ucode);
//						qryUpdateValues[0] = "" + (++ii);
						qryUpdateValues[0] = ucode;
						qryUpdateValues[1] = branch;
						qry = "update CONSTANT_DETAIL set DETAIL_NAME=? where CATEGORY_ID=37 and CATEGORY_DETAIL_ID=1 and branch_code=?";
						runner.multipleUpdation(qry, qryUpdateValues);
					}
					else if(userType.equalsIgnoreCase("1"))
					{
						String tempCode = ucode.replaceAll("c","");
						int tepmCusAgency = Integer.parseInt(tempCode);
						String args1[] = new String[2];
						tepmCusAgency = tepmCusAgency+1;
						args1[0] = ""+tepmCusAgency;
						args1[1] = branch;
						runner.multipleUpdation("update constant_detail set detail_name=? where CATEGORY_ID='37' and CATEGORY_DETAIL_ID='3' and status='Y' and branch_code=?",args1);
					}
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
			else 
			{
				String date123 = "";
				if (!dobDay.equals("") && !dobDay.equals("0")&& !dobMonth.equals("") && !dobMonth.equals("0")&& !dobYear.equals("") && !dobMonth.equals("0")) 
				{
					date123 = com.maan.common.util.OracleDateConversion.ConvertDate(dobDay + "-" + dobMonth + "-"+ dobYear);
				}

				firstName = firstName.replaceAll("'", "''");
				lastName = lastName.replaceAll("'", "''");

				String[] qryvalues = new String[21];

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
				qryvalues[17] = com.maan.common.util.OracleDateConversion.ConvertDate("" + simpleFormatter.format(dd));
				qryvalues[18] = city;
				qryvalues[19] = userType;
				qryvalues[20] = ucode;

				qry = "update personal_info set title=?,first_name=?,last_name=?,nationality=?,dob=?,gender=?,telephone=?,mobile=?,fax=?,email=?,address1=?,address2=?,occupation=?,pobox=?,country=?,emirate=?,status=?,effective_date=?,city=?  where application_id=? and agency_code=?";
				qry = runner.multipleUpdation(qry,qryvalues);
			}
			process = "YES";
		}
		catch (Exception e)
		{
			System.out.println("Exception in insertion and updation of the Broker information "+ e.toString());
			e.printStackTrace();
		}
		return process;
	}

	public String validateCommision() //skv
	{
		com.maan.common.util.dataCollection data = new com.maan.common.util.dataCollection();
		String[][] BrokerCommision = getCommisionData123(loginPersonId);
		String error = "";
		HashMap pro = new HashMap();
		String offTemp = "";
		String office = "";
		String schemeId = "";
		if (proDetails.size() > 0) 
		{
			HashMap royalTry = new HashMap();
			for (int i = 0; i < proDetails.size(); i++) 
			{
				pro = (HashMap) proDetails.get("productsdetails"+(i+1));
				
				if (pro == null) 
				{
					i++;
					continue;
				}
				String values = "";

				offTemp = (String)pro.get("product"+(i+1));

				office = offTemp==null?"":offTemp;
				if(!office.equals("11") && !office.equals("75") && office.length() <= 2 && !royalTry.containsValue(office)) 
				{
					

					if (userType.equals("4")) 
					{
						values = data.validString((String) pro.get("commision"+(i+1)), true);
						if ("needed".equalsIgnoreCase(values))
							error = error+ "<br>* Enter Valid Commmision for Product"+ (i + 1);
						else if ("Invalid".equalsIgnoreCase(values))
							error = error+ "<br>* Enter Valid Commmision for Product"+ (i + 1);
						else if (Double.parseDouble((String) pro.get("commision"+(i+1))) > Double							.parseDouble(BrokerCommision[i][1] == null ? "0.0": BrokerCommision[i][1]))
							error = error + "<br>* Commmision for Product"+ (i+1) + " Exceded The Limit";
					} else if (userType.equals("3")) {
						values = data.validString((String) pro.get("discount"+ (i + 1)), true);
						if ("needed".equalsIgnoreCase(values))
							error = error+ "<br>* Enter Valid discount for "+ getProductById(office);
						else if ("Invalid".equalsIgnoreCase(values))
							error = error+ "<br>* Enter Valid discount for "+ getProductById(office);
						else if (Double.parseDouble((String) pro.get("discount"	+ (i + 1))) > Double					.parseDouble(BrokerCommision[i][7] == null ? "0.0":BrokerCommision[i][7]))
							error = error	+ "<br>* Discount for  "+getProductById(office)+" Exceded The Limit";
					}
					if (pro.get("suminsured" + (i + 1))!=null)
					{
						
						values = data.validString((String) pro.get("suminsured"	+ (i + 1)), true);
						if ("needed".equalsIgnoreCase(values)){
							error = error+ "<br>* Enter Valid suminsured for "+ getProductById(office);
						}
						else if ("Invalid".equalsIgnoreCase(values)){
							error = error+ "<br>* Enter Valid suminsured for "+ getProductById(office);
						}
						else if (Long.parseLong((String) pro.get("suminsured"+ (i+1))) > Long.parseLong(getCommisionData123(loginPersonId,(String)pro.get("product"+ (i+1))))){
							//Long.parseLong((String) pro.get("suminsured"+ (i+1))) >Long.parseLong(BrokerCommision[i][2]==null ? "0.0": BrokerCommision[i][2])) 
							System.out.println("Full Map Values "+pro);
							System.out.println("pro.get(suminsured("+i+"+1))--->"+pro.get("suminsured"+ (i+1)));
							System.out.println("BrokerCommision["+i+"][2] -->"+BrokerCommision[i][2]);
							error = error+ "<br>* Suminsured for "+ getProductById(office)+ " Exceded The Limit";
						}
						if (((String) pro.get("suminsured" + (i + 1))).equalsIgnoreCase("0")) {
							error = error+ "<br>* Suminsured Insured Should Not Be Zero for  "+getProductById((String) pro.get("product"+(i+1)));
						}
					}
				}
				royalTry.put("royal",office);
			}
		} else {
			error = error + "<BR>*Select At Least One Product";
		}
		return error;
	}

	public String insertOrUpdate(String ucode, String userType,	String loginPersonId, String provision,String OpenCoverNos) 
	{
		
		java.util.Date dd = new java.util.Date();
		java.text.SimpleDateFormat simpleFormatter = new java.text.SimpleDateFormat("dd-MM-yyyy");
		String process = "";
		int i = 0;
		String commision = "";
		String suminsured = "";
		String discount = "";
		String user = "";
		String referral = "";
		String account = "";
		String offTemp = "";
		String office = "";
		String newSchemeId = "";
		String schemeId = "";
		String[][] userdetails = new String[0][0];
		HashMap pro = new HashMap();
		String debit = "";
		String userLoginId="";
		try 
		{

			pro = (HashMap) proDetails.get("productsdetails" + (i + 1));
			try
			{
				try
				{
					String args[] = new String[1];
					args[0] = ucode;
					userLoginId = runner.singleSelection("select nvl(login_id,' ') from login_master where agency_code=?",args);
					userLoginId = userLoginId.trim();
					String sql = "select CUSTOMER_ID,FIRST_NAME,AGENCY_CODE from personal_info where AGENCY_CODE=?";
					String args1[] = new String[1];
					args1[0] = ucode;
					userdetails = runner.multipleSelection(sql,args1);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				String[][] products = new String[0][0];
				String sqlcheck = "";
				if (userType.equals("3")) 
				{
					sqlcheck = "select product_id,nvl(scheme_id,'0') from login_user_details where AGENCY_CODE=?";
				}
				else 
				{
					sqlcheck = "select product_id,nvl(scheme_id,'0') from LOGIN_EXECUTIVE_DETAILS where AGENCY_CODE=?";
				}
				try
				{
					String args2[] = new String[1];
					args2[0] = ucode;
					products = runner.multipleSelection(sqlcheck,args2);
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
				String avail = "not";
				for (int p = 0; p < products.length; p++) 
				{
					avail = "not";
					for (int h = 0; h < proDetails.size(); h++) 
					{	
						if((String)pro.get("product"+(h+1))!=null)
							offTemp = (String)pro.get("product"+(h+1));
						
						if(offTemp.length() >= 3)
						{
							
							office = offTemp.substring(0,offTemp.length()-1);
							schemeId = offTemp.substring(offTemp.length()-1,offTemp.length());
							
						}
						else
						{
							office = offTemp;schemeId = "";
						}

						if (products[p][0].equalsIgnoreCase(office)&&products[p][1].equalsIgnoreCase(schemeId)) 
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
						System.out.println("available>>>>>>>>"+ products[p][0]);
					} 
					else 
					{
						String sqlstatus = "";
						if(products[p][1].equals("0"))
						{
							if (userType.equals("3")) 
							{
								sqlstatus = "update login_user_details set status='N' where AGENCY_CODE=? and product_id=?";
							} 
							else 
							{
								sqlstatus = "update LOGIN_EXECUTIVE_DETAILS set status='N' where AGENCY_CODE=? and product_id=?";
							}
							boolean check1 = false;
								try 
								{
									String args3[] = new String[2];
									args3[0] = ucode;
									args3[1] = products[p][0];
									String temp = runner.multipleUpdation(sqlstatus,args3);
									if (temp.equalsIgnoreCase("UPDATED"))
									{
										check1 = true;
									}
									else {
										check1 = false;
									}
								} catch (Exception e) {
									e.printStackTrace();
							}
						}
						else
						{
							
							sqlstatus = "update login_user_details set status='N' where AGENCY_CODE=? and product_id=? and scheme_id=?";
							try
							{
								String args[] = new String[3];
								args[0] = ucode;
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
							if (((String) pro.get("commision" + (i + 1))).length() > 0 && pro.get("commision" + (i + 1)) != null) {
								commision = (String) pro.get("commision" + (i + 1));
							}

							if (((String) pro.get("suminsured" + (i + 1))).length() > 0	&& pro.get("suminsured" + (i + 1)) != null) {
								suminsured = (String) pro.get("suminsured"+ (i + 1));
							}

							if (((String) pro.get("discount" + (i + 1))).length() > 0 && pro.get("discount" + (i + 1)) != null) {
								discount = (String) pro.get("discount" + (i + 1));
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
							if (((String) pro.get("debit" + (i + 1))).length() > 0 && pro.get("debit" + (i + 1)) != null) {
								debit = (String) pro.get("debit" + (i + 1));
							}
						}
						else // Office Scheme
						{	
						 	if (((String) pro.get("commision" + (i + 1))).length() > 0 && pro.get("commision" + (i + 1)) != null) {
								commision = (String) pro.get("commision" + (i + 1));
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
							if (((String) pro.get("debit" + (i + 1))).length() > 0 && pro.get("debit" + (i + 1)) != null) {
								debit = (String) pro.get("debit" + (i + 1));
							}	
						}

						String sqlboth = "";
						String sqlcount = "";
						if (userType.equals("3")) 
						{
							if(!schemeId.equals("") && !schemeId.equalsIgnoreCase("null") && schemeId.length() > 0)
								newSchemeId = " and scheme_id='"+schemeId+"'";
							sqlcount = "select count(*) from login_user_details where agency_code=? and product_id=?"+newSchemeId;
						} else if (userType.equals("4")) {
							sqlcount = "select count(*) from LOGIN_EXECUTIVE_DETAILS where agency_code=? and product_id=?";
						}
						int count = 0;
						try
						{
							String args4[] = new String[2];
							args4[0] = ucode;
							args4[1] = office;//(String) pro.get("product"+(i+1));
							String temp = runner.singleSelection(sqlcount,args4);
							if(temp.length()>0)
								count = Integer.parseInt(temp);
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
						if (count <= 0) 
						{
							int userid = 1;
							if (userType.equals("3")) 
							{
								sqlboth = "insert into login_user_details(USER_ID,USER_NAME,LOGIN_ID,AGENCY_CODE,OA_CODE,PRODUCT_ID,COMPANY_ID,COMMISSION,INSURANCE_START_LIMIT,INSURANCE_END_LIMIT,SPECIAL_DISCOUNT,RELATIVE_USER_ID,AMEND_ID,INCEPTION_DATE,EXPIRY_DATE,EFFECTIVE_DATE,ENTRY_DATE,REMARKS,STATUS,CUSTOMER_ID,FREIGHT_DEBIT_OPTION,open_cover_no,scheme_id) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					
								userid = autoGenCustId("USER_ID","LOGIN_USER_DETAILS");
							} 
							else 
							{
								sqlboth = "insert into LOGIN_EXECUTIVE_DETAILS(AC_EXECUTIVE_ID,AC_EXECUTIVE_NAME,LOGIN_ID,AGENCY_CODE,OA_CODE,PRODUCT_ID,COMPANY_ID,COMMISSION,INSURANCE_START_LIMIT,INSURANCE_END_LIMIT,SPECIAL_DISCOUNT,RELATIVE_AC_EXECUTIVE_ID,AMEND_ID,INCEPTION_DATE,EXPIRY_DATE,EFFECTIVE_DATE,ENTRY_DATE,REMARKS,STATUS,CUSTOMER_ID,FREIGHT_DEBIT_OPTION) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

								userid = autoGenCustId("AC_EXECUTIVE_ID","LOGIN_EXECUTIVE_DETAILS");
							}

							try 
							{
								String argss[] = new String[23];
								String oacode = getOACode(loginPersonId);
								String pids = office;//(String) pro.get("product" + (i + 1));
								String UserOpenCoverNos = "";
								if(pids.equals("11") || pids.equals("75"))
									UserOpenCoverNos = OpenCoverNos;
								else
									UserOpenCoverNos = "";
								
								/** Office Testing **/
								if(pids.equalsIgnoreCase("30"))
								{
									System.out.println("..officeHash.size()"+officeHash.size());
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
										/*if(((String) officeHash.get("scheme"+schemeId)).length() > 0 && officeHash.get("scheme"+schemeId) != null) {
											schemeId = (String) officeHash.get("scheme"+schemeId);
										}*/

										schemeId = schemeId;
										
										if(((String) officeHash.get("suminsured"+schemeId)).length() > 0 && officeHash.get("suminsured"+schemeId)!=null) {
											suminsured = (String) officeHash.get("suminsured"+schemeId);
										}
										
										if(((String) officeHash.get("discount"+schemeId)).length() > 0 && officeHash.get("discount"+schemeId)!= null) {
											discount = (String) officeHash.get("discount"+schemeId);
										}
										
										System.out.println("schemeId..."+schemeId);
										System.out.println("suminsured..."+suminsured);
										System.out.println("discount..."+discount);
										
										/*if(OS ==1)
										{
											argss[0] = ""+userid;
										}
										else
										{*/
											userid = autoGenCustId("USER_ID","LOGIN_USER_DETAILS");
											argss[0] = ""+userid;
										//}
										
										argss[1] =  userdetails[0][1];
										
										if(userLoginId.length()<=0)
											argss[2] = "NONE";
										else if(userLoginId.length()>0)
											argss[2] =  userLoginId;

										argss[3] = userdetails[0][2];
										argss[4] = oacode;
										argss[5] = office;//(String) pro.get("product" + (i + 1));
										argss[6] = "1";
										argss[7] = commision;
										argss[8] = "1000";
										argss[9] = suminsured;
										argss[10] = discount;
										argss[11] = "0";
										argss[12] = "1";
										argss[13] = com.maan.common.util.OracleDateConversion.ConvertDate(""+ simpleFormatter.format(dd));
										argss[14] =  "";
										argss[15] = "";
										argss[16] = com.maan.common.util.OracleDateConversion.ConvertDate(""+ simpleFormatter.format(dd));
										argss[17] =  "";
										argss[18] =  "Y";
										argss[19] = userdetails[0][0];
										argss[20] = debit;
										argss[21] = UserOpenCoverNos;
										argss[22] = schemeId;
										System.out.println("user insertion in login user details here");
										runner.multipleInsertion(sqlboth,argss);
									}
								}
								/** Office Testing **/
								else
								{
									argss[0] = ""+userid;
									argss[1] =  userdetails[0][1];
									
									if(userLoginId.length()<=0)
										argss[2] = "NONE";
									else if(userLoginId.length()>0)
										argss[2] =  userLoginId;

									argss[3] = userdetails[0][2];
									argss[4] = oacode;
									argss[5] = office;//(String) pro.get("product" + (i + 1));
									argss[6] = "1";
									argss[7] = commision;
									argss[8] = "1000";
									argss[9] = suminsured;
									argss[10] = discount;
									argss[11] = "0";
									argss[12] = "1";
									argss[13] = com.maan.common.util.OracleDateConversion.ConvertDate(""+ simpleFormatter.format(dd));
									argss[14] =  "";
									argss[15] = "";
									argss[16] = com.maan.common.util.OracleDateConversion.ConvertDate(""+ simpleFormatter.format(dd));
									argss[17] =  "";
									argss[18] =  "Y";
									argss[19] = userdetails[0][0];
									argss[20] = debit;
									argss[21] = UserOpenCoverNos;
									argss[22] = schemeId;
									System.out.println("user insertion in login user details here");
									runner.multipleInsertion(sqlboth,argss);
								}
							} 
							catch (Exception e) 
							{
								e.printStackTrace();
							}
							
						}
						else 
						{
							String pids = office;//(String) pro.get("product" + (i + 1));
							String syntax = "";
							
							if (userType.equals("3")) 
							{
								sqlboth = "update login_user_details set COMMISSION=?,INSURANCE_END_LIMIT=?,STATUS=?,SPECIAL_DISCOUNT=?,FREIGHT_DEBIT_OPTION=?,Login_id='"+userLoginId+"',open_cover_no=? where AGENCY_CODE=? and product_id=? ";
							} 
							else 
							{
								sqlboth = "update LOGIN_EXECUTIVE_DETAILS set COMMISSION=?,INSURANCE_END_LIMIT=?,STATUS=?,SPECIAL_DISCOUNT=?,FREIGHT_DEBIT_OPTION=? where AGENCY_CODE=? and product_id=? ";
							}
							try 
							{
								String argss[] = new String[8];
								String UserOpenCoverNos = "";								
								String syntax1 = "";
								if(pids.equals("11") || pids.equals("75"))
									UserOpenCoverNos = OpenCoverNos;
								else
									UserOpenCoverNos = "";

								if(pids.equalsIgnoreCase("30"))
								{
									System.out.println("..officeHash.size()"+officeHash.size());
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
										syntax1 = "";
										/*if(((String) officeHash.get("scheme"+schemeId)).length() > 0 && officeHash.get("scheme"+schemeId) != null) {
											schemeId = (String) officeHash.get("scheme"+(s+1));
										}*/
										schemeId = schemeId;
										syntax1 = " and scheme_id='"+schemeId+"'";
										sqlboth = sqlboth + syntax1;
										System.out.println("sqlboth....User Updation."+sqlboth);
										if(((String) officeHash.get("suminsured"+schemeId)).length() > 0 && officeHash.get("suminsured"+schemeId)!=null) {
											suminsured = (String) officeHash.get("suminsured"+schemeId);
										}
										
										if(((String) officeHash.get("discount"+schemeId)).length() > 0 && officeHash.get("discount"+schemeId)!= null) {
											discount = (String) officeHash.get("discount"+schemeId);
										}
										argss[0] = commision;
										argss[1] = suminsured;
										argss[2] = "Y";
										argss[3] = discount;
										argss[4] = debit;
										argss[5] = UserOpenCoverNos;
										argss[6] = ucode;
										argss[7] = office;//(String) pro.get("product" + (i + 1));
										runner.multipleUpdation(sqlboth,argss);
									}
								}
								else
								{
									argss[0] = commision;
									argss[1] = suminsured;
									argss[2] = "Y";
									argss[3] = discount;
									argss[4] = debit;
									argss[5] = UserOpenCoverNos;
									argss[6] = ucode;
									argss[7] = office;//(String) pro.get("product" + (i + 1));
									runner.multipleUpdation(sqlboth,argss);
								}
							}
							catch (Exception e) 
							{
								e.printStackTrace();
							}
						}
						if (!provision.equals("NO")) 
						{

							if (!checkUserCode(ucode, "login_master"))
							{

								String[] qryvalues = new String[3];
								if(i==0)
								{
									if (((String) pro.get("user" + (i + 1))).length() > 0&& pro.get("user" + (i + 1)) != null) {
										qryvalues[0] = "Y";
									} else {
										qryvalues[0] = "N";
									}

									if (((String) pro.get("account" + (i + 1))).length() > 0&& pro.get("account" + (i + 1)) != null) {
										qryvalues[1] = "Y";
									} else {
										qryvalues[1] = "N";
									}
									if (((String) pro.get("referral" + (i + 1))).length() > 0&& pro.get("referral" + (i + 1)) != null) 
									{
										qryvalues[2] = "Y";
									} else {
										qryvalues[2] = "N";
									}
								}	
							
								String utype = "";

								if (userType.equals("3")) {
									utype = "User";
								} else {
									utype = "AccountEx";
								}

								String sql1 = "insert into login_master(LOGIN_ID,PASSWORD,USERTYPE,USERNAME,USERID,AGENCY_CODE,OA_CODE,ACCESSTYPE,RIGHTS,LPASS1,LPASS2,LPASS3,PASSDATE,COMPANY_ID,CREATED_BY,STATUS,USER_ID_CREATION,AC_EXECUTIVE_CREATION,REFERAL) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

								try 
								{
									String oacode = getOACode(loginPersonId);
									String argss[] = new String[19];
									argss[0] = "NONE";
									argss[1] = "";
									argss[2] = utype;
									argss[3] = userdetails[0][1];
									argss[4] = "1";
									argss[5] = ucode;
									argss[6] = oacode;
									argss[7] = "BOTH";
									argss[8] = "";
									argss[9] = "";
									argss[10] = "";
									argss[11] = "";
									argss[12] = com.maan.common.util.OracleDateConversion.ConvertDate(""+simpleFormatter.format(dd));
									argss[13] =  "1";
									argss[14] =  oacode;
									argss[15] =  "Y";
									argss[16] = qryvalues[0];
									argss[17] =  qryvalues[1];
									argss[18] =  qryvalues[2];
									runner.multipleInsertion(sql1,argss);
									process = "insert";
								}
								catch (Exception e) 
								{
									e.printStackTrace();
								}
							}
							else 
							{

								String[] qryvalues = new String[3];
								if(i==0)
								{
									if (((String) pro.get("user" + (i + 1))).length() > 0 && pro.get("user" + (i + 1)) != null) {
										qryvalues[0] = "Y";
									} else {
										qryvalues[0] = "N";
									}

									if (((String) pro.get("account" + (i + 1))).length() > 0 && pro.get("account" + (i + 1)) != null) {
										qryvalues[1] = "Y";
									} else {
										qryvalues[1] = "N";
									}
									if (((String) pro.get("referral" + (i + 1))).length() > 0 && pro.get("referral" + (i + 1)) != null)
									{
										
										qryvalues[2] = "Y";
									} else {
										qryvalues[2] = "N";
									}
								
									try 
									{

										String qry = "update login_master set USER_ID_CREATION=?,AC_EXECUTIVE_CREATION=?,REFERAL=? where  AGENCY_CODE=?";
										String argss[] = new String[4];
										argss[0] = qryvalues[0];
										argss[1] = qryvalues[1];
										argss[2] = qryvalues[2];
										argss[3] = ucode;
										process = "update";
										runner.multipleUpdation(qry,argss);
		
									} 
									catch (Exception e) 
									{
										e.printStackTrace();
									}
								}
							}
						}
					}
			}
		}
		catch (Exception e) 
		{
			System.out.println("exception in " + e.toString());e.printStackTrace();
		}
	} 
	catch (Exception e) 
	{
		System.out.println("Exception in total updation" + e.toString());e.printStackTrace();
	}
	return process;
}
	public String[][] getBrokerDetails(String ucode) 
	{
		String[][] brokerDetails = new String[0][0];
		String sql = "select pi.TITLE,pi.GENDER,pi.FIRST_NAME,pi.LAST_NAME,pi.NATIONALITY,pi.DOB,pi.TELEPHONE,pi.MOBILE,pi.FAX,EMAIL,pi.ADDRESS1,pi.ADDRESS2,pi.OCCUPATION,pi.EMIRATE,pi.COUNTRY,pi.POBOX,pi.entry_date,pi.city,pi.login_id from personal_info pi where pi.agency_code=?";
		try 
		{
			String args[] = new String[1];
			args[0] = ucode;
			brokerDetails = runner.multipleSelection(sql,args);
		}
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		return brokerDetails;
	}

	public String[][] getBrokerDetails123(String bcode) 
	{
		String[][] brokerDetails = new String[0][0];
		String sql = "select pi.TITLE,pi.GENDER,pi.FIRST_NAME,pi.LAST_NAME,pi.NATIONALITY,pi.DOB,pi.TELEPHONE,pi.MOBILE,pi.FAX,pi.EMAIL,pi.ADDRESS1,pi.ADDRESS2,pi.OCCUPATION,pi.EMIRATE,pi.COUNTRY,pi.POBOX,lm.company_name,lm.agency_code,pi.entry_date,lm.status,lm.ADDRESS3,lm.city from personal_info pi,broker_company_master lm where pi.agency_code=lm.agency_code and  pi.agency_code=? and pi.application_id='2'";
		try 
		{
			String args[] = new String[1];
			args[0] = bcode;
			brokerDetails = runner.multipleSelection(sql,args);
		}
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		return brokerDetails;
	}

	public String[][] getBrokerDetails12(String bcode) 
	{
		String[][] brokerDetails = new String[0][0];
		String sql = "select pi.TITLE,pi.GENDER,pi.FIRST_NAME,pi.LAST_NAME,pi.NATIONALITY,pi.DOB,pi.TELEPHONE,pi.MOBILE,pi.FAX,pi.EMAIL,pi.ADDRESS1,pi.ADDRESS2,pi.OCCUPATION,pi.EMIRATE,pi.COUNTRY,pi.POBOX,pi.POBOX,pi.agency_code,pi.entry_date,pi.status from personal_info pi where  pi.agency_code=?";

		try 
		{
			String args[] = new String[1];
			args[0] = bcode;
			brokerDetails = runner.multipleSelection(sql,args);
		}
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		return brokerDetails;
	}

	public String[][] getBrokerDetailsDe(String bcode) 
	{
		String[][] brokerDetails = new String[0][0];
		String sql = "select pi.TITLE,pi.GENDER,pi.FIRST_NAME,pi.LAST_NAME,pi.NATIONALITY,pi.DOB,pi.TELEPHONE,pi.MOBILE,pi.FAX,pi.EMAIL,pi.ADDRESS1,pi.ADDRESS2,pi.OCCUPATION,pi.EMIRATE,pi.COUNTRY,pi.POBOX,lm.company_name,lm.agency_code,pi.entry_date,lm.status,lm.ADDRESS3,lm.city from personal_info pi,broker_company_master lm where pi.agency_code=lm.agency_code and  pi.agency_code=?";
		try 
		{
			String acode = getAgencyCode(bcode);
			String args[] = new String[1];
			args[0] = acode;
			brokerDetails = runner.multipleSelection(sql,args);
		}
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		return brokerDetails;
	}

	public String[][] getCommisionData(String ucode, String userType,String provision) 
	{
		String sql = "";
		String[][] commisionDetails = new String[0][0];
		if (userType.equals("3")) 
		{
			sql = "select lud.PRODUCT_ID,lud.COMMISSION,lud.INSURANCE_END_LIMIT,lm.USER_ID_CREATION,lm.AC_EXECUTIVE_CREATION,lm.REFERAL,lud.status,lud.SPECIAL_DISCOUNT,nvl(lud.FREIGHT_DEBIT_OPTION,'Y')  from  login_master lm,login_user_details lud where lm.agency_code=lud.agency_code and  lud.agency_code=? order by lud.product_id";
		} 
		else 
		{
			if (provision.equals("YES"))
			{
				sql = "select PRODUCT_ID,COMMISSION,INSURANCE_END_LIMIT,status,status,status,status,SPECIAL_DISCOUNT from  LOGIN_EXECUTIVE_DETAILS  where agency_code=? order by product_id";
			} 
			else 
			{
				sql = "select PRODUCT_ID,COMMISSION,INSURANCE_END_LIMIT,status,status,status,status,SPECIAL_DISCOUNT from  LOGIN_EXECUTIVE_DETAILS  where agency_code=? order by product_id";
			}
		}
		try 
		{
			String args[] = new String[1];
			args[0] = ucode;
			commisionDetails = runner.multipleSelection(sql,args);
		}
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		return commisionDetails;
	}

	public String[][] getCommisionData123(String login_id)
	{
		String[][] commisionDetails = new String[0][0];
		String sql = "select lud.PRODUCT_ID,lud.COMMISSION,lud.INSURANCE_END_LIMIT,lm.USER_ID_CREATION,lm.AC_EXECUTIVE_CREATION,lm.REFERAL,lud.status,lud.SPECIAL_DISCOUNT from  login_master lm,login_user_details lud where lm.login_id=lud.login_id and  lud.login_id=? order by lud.product_id";
		try 
		{
			String args[] = new String[1];
			args[0] = login_id;
			commisionDetails = runner.multipleSelection(sql,args);
		}
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		return commisionDetails;
	}
	
	public String getCommisionData123(String login_id,String product_id)
	{
		String commisionDetails = "0";
		String sql = "select lud.INSURANCE_END_LIMIT  from login_user_details lud where lud.login_id=? and lud.product_id = ?";
		try 
		{
			String args[] = {login_id,product_id};
			
			commisionDetails = runner.singleSelection(sql,args);
		}
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		return commisionDetails;
	}
	
	public boolean checkBrokerId(String brokerId) 
	{
		boolean check = false;
		String sql = "select count(*) from login_master where login_id=?";
		try 
		{
			String args[] = new String[1];
			args[0] = brokerId;
			String temp = runner.singleSelection(sql,args);
			if (temp.length()>0) 
			{
				int in = Integer.parseInt(temp);
				if (in > 0)
				{
					check = true;
				}
				else 
				{
					check = false;
				}
			}

		}
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		return check;
	}
	public boolean checkOld(String brokerId, String password)
	{
		passwordEnc pass = new passwordEnc();
		String encpass = pass.crypt(password.substring(0, 3), password);
		boolean check = false;
		String pass123 = "";
		String sql = "select password from login_master where agency_code=?";
		try 
		{
			String args[] = new String[1];
			args[0] = brokerId;
			pass123 = runner.singleSelection(sql,args);
			
			if (pass123.trim().equals(encpass.trim())) 
			{
				check = true;
			} 
			else if (pass123.trim().equals(password.trim())) 
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
			System.out.println("Exception in checking password...." + e.toString());
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
	public boolean changePasswordWithDate(String brokerId, String password) 
	{
		passwordEnc pass = new passwordEnc();
		String encpass = pass.crypt(password.substring(0, 3), password);
		boolean check = false;
		String[][] qry1 = new String[0][0];
		try 
		{
			String sql = "select password,lpass1,lpass2,lpass3,lpass4 from login_master where agency_code=?";
			try
			{
				String args[] = new String[1];
				args[0] = brokerId;
				qry1 = runner.multipleSelection(sql,args);			
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			if (qry1.length > 0) 
			{
				sql = "update login_master set  lpass5=?, lpass4=?, lpass3=?,lpass2=?,lpass1=?,password=?,passdate=sysDate+45 ,status='Y' where agency_code=?";
					String args[] = new String[7];
					args[0] = qry1[0][4];
					args[1] = qry1[0][3];
					args[2] = qry1[0][2];
					args[3] = qry1[0][1];
					args[4] = qry1[0][0];
					args[5] = encpass;
					args[6] = brokerId;
				try
				{
					runner.multipleUpdation(sql,args);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
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
	public boolean updateStatus(String status, String brokerId,String selectedType) 
	{
		boolean check = false;
		String usertype = "";
		if (selectedType.equals("3")) 
		{
			usertype = "user";
		}
		else if (selectedType.equals("4")) 
		{
			usertype = "accountex";
		}
		String sql = "update login_master set status=? where agency_code=? and lower(usertype)=?";
		try 
		{
			String args[] = new String[3];
			args[0] = status;
			args[1] = brokerId;
			args[2] = usertype;
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

		check = false;
		String sql1 = "update personal_info set status=? where agency_code=? and application_id=?";
		try 
		{
			String args[] = new String[3];
			args[0] = status;
			args[1] = brokerId;
			args[2] = selectedType;
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
			e.printStackTrace();
		}
		return check;
	}
	public String[][] getUserProducts(String loginId) 
	{
		String[][] products = new String[0][0];

		String sql = "select PRODUCT_ID,PRODUCT_NAME,COMPANY_ID from product_master where status='Y' and PRODUCT_ID in (select product_id from login_user_details where agency_code=(select agency_code from login_master where  login_id=?) and status='Y')";
		try 
		{
			String args[] = new String[1];
			args[0] = loginId;
			products = runner.multipleSelection(sql,args);
		}
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		return products;
	}

	public String[][] getProducts(String branch) 
	{
		String[][] products = new String[0][0];
		String sql = "select PRODUCT_ID,PRODUCT_NAME,COMPANY_ID from product_master where status='Y' and branch_code=?";
		try 
		{
			String args[] = new String[1];
			args[0] = branch;
			products = runner.multipleSelection(sql,args);
		}
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		return products;
	}
	public String[][] getProducts(String broLoginId,String branch) 
	{
		String[][] products = new String[0][0];
		String sql = "select distinct log.product_id,pro.product_name,pro.COMPANY_ID from login_user_details log, PRODUCT_MASTER pro where log.login_id=? and pro.product_id=log.product_id and pro.status='Y' and log.status='Y' and pro.branch_code=? order by log.product_id";
		try 
		{
			String args[] = new String[2];
			args[0] = broLoginId;
			args[1] = branch;
			products = runner.multipleSelection(sql,args);
		}
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		return products;
	}

	public String getCurrencyType(String branch) 
	{
		String result = "";
		try 
		{
			String sql = "select DETAIL_NAME from CONSTANT_DETAIL where CATEGORY_ID=36 and category_detail_id='1' and branch_code=?";
			String args[] = new String[1];
			args[0] = branch;
			result = runner.singleSelection(sql,args);
		}
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		return result;
	}
	public String[][] getEmirates() 
	{
		String[][] countries = new String[0][0];
		String sql = "select city_id,city_name from city_master where country_id='1' order by city_name";
		try 
		{
			countries = runner.multipleSelection(sql);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}		
		return countries;
	}

	public String[][] getNtionalities() 
	{
		String[][] countries = new String[0][0];
		String sql = "select COUNTRY_ID,NATIONALITY_NAME from COUNTRY_MASTER where amend_id||country_id in( select max(amend_id)||country_id from country_master group by country_id) order by NATIONALITY_NAME ";
		try 
		{
			countries = runner.multipleSelection(sql);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}		
		return countries;
	}

	public String[][] getTitles(String branch) 
	{
		String[][] countries = new String[0][0];
		String sql = "select TITLE_ID,TITLE_NAME from TITLE_MASTER where branch_code=? and status='Y' order by TITLE_NAME ";
		try 
		{
			String args[] = new String[1];
			args[0] = branch;
			countries = runner.multipleSelection(sql,args);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}		
		return countries;
	}

	public String[][] getCountries() 
	{
		String[][] countries = new String[0][0];
		String sql = "select COUNTRY_ID,COUNTRY_NAME  from COUNTRY_MASTER where amend_id||country_id in( select max(amend_id)||country_id from country_master group by country_id)order by COUNTRY_NAME";
		try 
		{
			countries = runner.multipleSelection(sql);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}		
		return countries;
	}

	public String[][] getBrokers()
	{
		String[][] brokers = new String[0][0];
		String sql = "select CUSTOMER_ID,APPLICATION_ID,FIRST_NAME,LOGIN_ID from personal_info where APPLICATION_ID='2' and status='Y'";
		try 
		{
			brokers = runner.multipleSelection(sql);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return brokers;
	}

	public String[][] getBrokers(String login_id)
	{
		String sql = "";
		String[][] brokers = new String[0][0];
		
		if (!login_id.equalsIgnoreCase("ALL")) 
		{
			String acode = getAgencyCode(login_id);
			sql = "select a.CUSTOMER_ID,a.APPLICATION_ID,b.COMPANY_NAME,b.agency_code from personal_info a,BROKER_COMPANY_MASTER b where a.agency_code=b.agency_code and a.APPLICATION_ID='2' and a.status='Y' and b.agency_code='"+acode+"'";
		}
		else if (disStatus.length() > 0 && !disStatus.equals("A")	&& !disStatus.equals("0")) 
		{
			sql = "select CUSTOMER_ID,APPLICATION_ID,FIRST_NAME,agency_code from personal_info where APPLICATION_ID='2' and status='"+disStatus+"'";
		}
		else 
		{
			sql = "select CUSTOMER_ID,APPLICATION_ID,FIRST_NAME,agency_code from personal_info where APPLICATION_ID='2'";
		}
		try 
		{
			brokers = runner.multipleSelection(sql);
		}
		catch (Exception e) 
		{
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
			String ocode = getAgencyCode(loginId);
			sql = "select CUSTOMER_ID,APPLICATION_ID,FIRST_NAME,agency_code from personal_info where  status='Y' and agency_code='"+ocode+"'";
		}
		else 
		{
			sql = "select pi.CUSTOMER_ID,pi.APPLICATION_ID,pi.FIRST_NAME,pi.oa_code from personal_info pi,login_master lm where  pi.LOGIN_ID=lm.login_id";
		}
		try 
		{
			brokers = runner.multipleSelection(sql);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return brokers;
	}

	public String[][] getUsersAccount(String loginId, String appId) 
	{
		String[][] brokers = new String[0][0];
		String sql = "";
		String ocode = getOACode(loginId);
		String args[] = new String[2];
		System.out.println("Appp id"+appId);
		if (appId.equalsIgnoreCase("BOTH")) 
		{
			sql = "select CUSTOMER_ID,APPLICATION_ID,FIRST_NAME,agency_code,login_id from personal_info where APPLICATION_ID!=? and  oa_code=? and status='Y' and login_id not in ('NON','NONE')";
			args[0] = "2";
			args[1] = ocode;
		} 
		else if (appId.equalsIgnoreCase("4")) 
		{
			sql = "select CUSTOMER_ID,APPLICATION_ID,FIRST_NAME,agency_code from personal_info where  APPLICATION_ID=? and oa_code=? and status='Y'";
			args[0] = appId;
			args[1] = ocode;
		} 
		else if (appId.equalsIgnoreCase("3")||appId.equalsIgnoreCase("5")) 
		{
			sql = "select CUSTOMER_ID,APPLICATION_ID,FIRST_NAME,agency_code from personal_info where APPLICATION_ID=? and  login_id=? and status='Y' ";
			args[0] = appId;
			args[1] = loginId;
		} 
		else if (appId.equalsIgnoreCase("2")) 
		{
			sql = "select a.CUSTOMER_ID,a.APPLICATION_ID,b.COMPANY_NAME,b.agency_code from personal_info a,BROKER_COMPANY_MASTER b where   a.agency_code=b.agency_code and a.APPLICATION_ID=? and  a.oa_code=?";
			args[0] = appId;
			args[1] = ocode;
		} 
		else if (appId.equalsIgnoreCase("User")) 
		{
			sql = "select CUSTOMER_ID,APPLICATION_ID,FIRST_NAME,agency_code from personal_info where APPLICATION_ID=? and  oa_code=(select oa_code from login_master where login_id=?) and status='Y' ";
			args[0] = "3";
			args[1] = loginId;
		} 
		else if (appId.equalsIgnoreCase("Freight")) 
		{
			sql = "select CUSTOMER_ID,APPLICATION_ID,FIRST_NAME,agency_code from personal_info where APPLICATION_ID=? and  oa_code=(select oa_code from login_master where login_id=?) and status='Y' ";
			args[0] = "5";
			args[1] = loginId;
		} 
		else  if (appId.equalsIgnoreCase("1")) 
		{
			sql = "select CUSTOMER_ID,APPLICATION_ID,FIRST_NAME,agency_code from personal_info where APPLICATION_ID=? and  customer_login_id=? and status='Y' ";
			args[0] = appId;
			args[1] = loginId;
		}
		else  if (appId.equalsIgnoreCase("8")) 
		{
			sql = "select CUSTOMER_ID,APPLICATION_ID,FIRST_NAME,agency_code from personal_info where APPLICATION_ID=? and  login_id=? and status='Y' ";
			args[0] = appId;
			args[1] = loginId;
		}
		try 
		{
			if(sql.length() > 0){
				brokers = runner.multipleSelection(sql,args);
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		System.out.println("SQL:"+sql);
		return brokers;
	}

	public String[][] getSelected(String loginId, String appId, String status)
	{
		String[][] brokers = new String[0][0];
		String sql = "";
		if(status.equalsIgnoreCase("DD"))
			status="Y";
		sql = "select CUSTOMER_ID,APPLICATION_ID,FIRST_NAME,agency_code from personal_info where  APPLICATION_ID=? and status=? and oa_code=?";
		try 
		{
				String ocode = getOACode(loginId);
				String args[] = new String[3];
				args[0] = appId;
				args[1] = status;
				args[2] = ocode;
				brokers = runner.multipleSelection(sql,args);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return brokers;
	}
	public String[][] getStatus(String appID, String agencyID)
	{
		String[][] brokers = new String[0][0];
		String sql = "";
		sql = "select CUSTOMER_ID,APPLICATION_ID,FIRST_NAME,agency_code,status from personal_info where APPLICATION_ID=? and agency_code=?";
		try 
		{
			String args[] = new String[2];
			args[0] = appID;
			args[1] = agencyID;
			brokers = runner.multipleSelection(sql,args);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return brokers;
	}
	public String[][] getAccountEx(String loginId) 
	{
		String sql = "";
		String[][] brokers = new String[0][0];
		try 
		{
			if (!loginId.equalsIgnoreCase("ALL")) 
			{
				sql = "select CUSTOMER_ID,APPLICATION_ID,FIRST_NAME,agency_code from personal_info where APPLICATION_ID='4' and status='Y' and LOGIN_ID in (select login_id from login_master where created_by=?)";
				String ocode = getOACode(loginId);
				String args[] = new String[1];
				args[0] = ocode;
				brokers = runner.multipleSelection(sql,args);
			}
			else 
			{
				sql = "select pi.CUSTOMER_ID,pi.APPLICATION_ID,pi.FIRST_NAME,lm.created_by from personal_info pi,login_master lm where pi.APPLICATION_ID='4' and pi.LOGIN_ID=lm.login_id";
				brokers = runner.multipleSelection(sql);
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}		
		return brokers;
	}

	public String getMailId(String brokerId) 
	{
		String email = "";
		String sql = "select EMAIL from personal_info where agency_code=?";
		try 
		{
			String args[] = new String[1];
			args[0] = brokerId;
			email = runner.singleSelection(sql,args);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}		
		return email;
	}

	public boolean checkUserCode(String ucode, String table) 
	{
		boolean check = false;
		String sql = "select count(*) from " + table + " where AGENCY_CODE=?";
		try 
		{
			String args[] = new String[1];
			args[0] = ucode;
			String status = runner.singleSelection(sql,args);
			if (status.length()>0)
			{
				int in = Integer.parseInt(status);
				if (in > 0) 
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
			e.printStackTrace();
		}		
		return check;
	}

	public boolean checkUserCode123(String ucode, String table)
	{
		boolean check = false;
		String sql = "select count(*) from " + table + " where AGENCY_CODE=?";
		try 
		{
			String args[] = new String[1];
			args[0] = ucode;
			String status = runner.singleSelection(sql,args);
			if (status.length()>0)
			{
				int in = Integer.parseInt(status);
				if (in > 0) 
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
			e.printStackTrace();
		}		
		return check;
	}

	public int autoGenCustId(String codes, String table) 
	{
		int customerId = 0;
		String sql = "select max(" + codes + ")+1 from " + table + "";
		try 
		{
			String status = runner.singleSelection(sql);
			if(status.length()>0)
			{
				customerId = Integer.parseInt(status);
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}		
		return customerId;
	}
	public String insertUserLogin(String ucode, String loginPersonId,String userType) 
	{
		String process = "";
		// * calling encryption method from another bean
		passwordEnc pass = new passwordEnc();
		String encpass = pass.crypt(password.substring(0, 3), password);
		String qry = "update login_master set LOGIN_ID=?,PASSWORD=?,OA_CODE=? where agency_code=?";
		String ocode = getOACode(loginPersonId);
		try 
		{
			String args[] = new String[4];
			args[0] = brokerId;
			args[1] = encpass;
			args[2] = ocode;
			args[3] = ucode;
			runner.multipleUpdation(qry,args);
			process = "1";
		}
		catch (Exception e) 
		{
			System.out.println("updations in login master " + e.toString());e.printStackTrace();
		}
		String qry1 = "";
		if (userType.equals("3")) 
		{
			qry1 = "update login_user_details set LOGIN_ID=?,OA_CODE=?  where  agency_code=?";
		} 
		else 
		{
			qry1 = "update LOGIN_EXECUTIVE_DETAILS set LOGIN_ID=?,OA_CODE=?  where  agency_code=?";
		}
		try
		{
			String args[] = new String[3];
			args[0] = brokerId;
			args[1] = ocode;
			args[2] = ucode;
			runner.multipleUpdation(qry1,args);
			process = process + "2";
		} 
		catch (Exception e) 
		{
			System.out.println("updations in login master " + e.toString());e.printStackTrace();
		}
		String qry2 = "update personal_info set LOGIN_ID=?,OA_CODE=?  where  agency_code=?";
		try
		{
			String args[] = new String[3];
			args[0] = brokerId;
			args[1] = ocode;
			args[2] = ucode;
			runner.multipleUpdation(qry2,args);
			process = process + "3";
		} 
		catch (Exception e) 
		{
			System.out.println("updations in login master " + e.toString());e.printStackTrace();
		}
		return process;
	}
	public String validateLoginCreation() 
	{
		String error = "";
		try 
		{
			if (brokerId.equals("") || brokerId == null	|| brokerId.equalsIgnoreCase("null")|| brokerId.length()< 5 || brokerId.indexOf(" ")!= -1) 
			{
				error = error + "<BR>*Enter Valid UserId";
			} else if (StringUtil.checkSpecial(brokerId)) {
				error = error + "<BR>*Spcial Chars Not Allowed";
			} else if (!mode.equalsIgnoreCase("edit")) {
				if (checkBrokerId(brokerId)) {
					error = error + "<BR>*This Login Id Already Existed";
				}
			}
			if (password.equals("") || password == null	|| password.equalsIgnoreCase("null") || password.indexOf(" ")!=-1) {
				error = error + "<BR>* "+runner.getErrormsg("362");//Enter Valid password";
			}

			else if (password.length() < 7) {
				error = error + "<BR>* "+runner.getErrormsg("361");//Password should be Min Eight Letters";
			} else if (!StringUtil.checkSpecial(password)) {
				error = error + "<BR>* Password should contain special characters";//PassWord As Spcial Chars Not Allowed ";
			} else if (!validPassword(password)) {
				error = error + "<BR>* "+runner.getErrormsg("365");//Please Enter Valid Password";
			}

			if (!password.equals(retypePassword)) {
				error = error + "<BR>* "+runner.getErrormsg("364");//Entered passwords are not Matched";
			}

		} 
		catch (Exception e) 
		{
			System.out.println("Exception in " + e.toString());
		} 
		return error;
	}

	public String LoginIdStatus(String ucode) 
	{
		String status = "";
		String sql = "select login_id from personal_info where agency_code=?";
		try 
		{
			String args[] = new String[1];
			args[0] = ucode;
			status = runner.singleSelection(sql,args);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}		
		return status;
	}

	public String[][] getUserCode(String branch) 
	{
		String[][] brokers = new String[0][0];
		String sql = "select detail_name from constant_detail where CATEGORY_ID=37 and CATEGORY_DETAIL_ID=1 and status='Y' and branch_code=?";
		try 
		{
			String args[] = new String[1];
			args[0] = branch;
			brokers = runner.multipleSelection(sql,args);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return brokers;
	}

	public String[][] getUsersType(String userType, String loginPersonId) 
	{
		String[][] brokers = new String[0][0];
		String sql = "select CUSTOMER_ID,APPLICATION_ID,FIRST_NAME,LOGIN_ID,AGENCY_CODE from personal_info where APPLICATION_ID=? and oa_code=? and status='Y'";
		try 
		{
			String ocode = getOACode(loginPersonId);
			String args[] = new String[2];
			args[0] = userType;
			args[1] = ocode;
			brokers = runner.multipleSelection(sql,args);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return brokers;
	}

	public String getOACode(String logpersonId)
	{
		String oacode = "";
		String sql = "select oa_code from login_master where login_id=?";
		try 
		{
			String args[] = new String[1];
			args[0] = logpersonId;
			oacode = runner.singleSelection(sql,args);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return oacode;
	}

	public String getAgencyCode(String logpersonId)
	{
		String oacode = "";
		String sql = "select agency_code from login_master where login_id=?";
		try 
		{
			String args[] = new String[1];
			args[0] = logpersonId;
			oacode = runner.singleSelection(sql,args);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return oacode;
	}
	public boolean checkCode(String code, String logedPersonId)
	{
			boolean check = false;
			String sql = "select count(*) from personal_info where agency_code=? and APPLICATION_ID in ('3','4','2','5') and oa_code=?";
			String ocode = getOACode(logedPersonId);
			try 
			{
				String args[] = new String[2];
				args[0] = code;
				args[1] = ocode;
				String rs = runner.singleSelection(sql,args);
				//if (Integer.valueOf(rs) > 0) 
				check = true;
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			return check;
	}
	public boolean checkCreationCode(String code) 
	{
		boolean check = false;
		String sql = "select count(*) from personal_info where agency_code=?";
		try 
		{
			String args[] = new String[1];
			args[0] = code;
			String temp = runner.singleSelection(sql,args);
			if (temp.length()>0) 
			{
				int in = Integer.parseInt(temp);
				if (in > 0) 
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
			e.printStackTrace();
		}
		return check;
	}

	public boolean validPassword(String value) {
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
			} //
		} else {
			splChar = false;
		}
		return splChar;
	}

	public String getMaxCustomerId(String branch)
	{
		String current_no = null;
		try 
		{
			String args[] = new String[1];
			args[0] = branch;
			current_no = runner.singleSelection("select nvl(max(current_no)+1,max(start_no)) from policyno_generate where type_id='3' and status='Y' and branch_code=?",args);
			String args1[] = new String[3];
			args1[0] = current_no;
			args1[1] = current_no;
			args1[2] = branch;
			runner.multipleUpdation("update policyno_generate set current_no=?,remarks=? where type_id='3' and status='Y' and branch_code=?",args1);
		} 
		catch (Exception e) 
		{
			System.out.println("ERROR in getMaxQuote in UserCreationBean.  "+ e.toString());e.printStackTrace();
		}
		return current_no;
	}
	public boolean chkpas(String pas, String pass, String brahma)throws Exception 
	{
		passwordEnc pass1 = new passwordEnc();
		String encpass = pass1.crypt(pass.substring(0, 3), pass);
		boolean retpas = false;

		try 
		{
			String sql = "select password,lpass1,lpass2,lpass3,lpass4,lpass5 from login_master where agency_code=?";
			String[][] upas = new String[0][0];
			String args[] = new String[1];
			args[0] = pas;
			upas = runner.multipleSelection(sql,args);
	
			if (upas.length > 0) 
			{
				if ((encpass.equals(upas[0][0]))|| (encpass.equals(upas[0][1]))	|| (encpass.equals(upas[0][2]))	|| (encpass.equals(upas[0][3]))|| (encpass.equals(upas[0][4]))	|| (encpass.equals(upas[0][5]))) 
				{
					retpas = true;
				} 
				else 
				{
					retpas = false;
				}
			}

		} 
		catch (Exception e) 
		{
			System.out.println("Exception >>>>>>>>>>>>>>>>" + e.toString());e.printStackTrace();
		} 
		return retpas;
	}

	public String[][] getBrokersInAdmin() 
	{
		String[][] brokers = new String[0][0];
	
		String sql = "select bcm.CUSTOMER_ID,bcm.CONTACT_PERSON,bcm.COMPANY_NAME,pi.login_id from BROKER_COMPANY_MASTER bcm,personal_info pi where bcm.agency_code=pi.agency_code and APPLICATION_ID='2' order by lower(bcm.COMPANY_NAME)";
		try 
		{
			brokers = runner.multipleSelection(sql);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return brokers;
	}

	
	public String[][] getBrokersInAdmin(String branchCode) //adminCreateUser.jsp
	{
		String[][] brokers = new String[0][0];

		String sql = "select bcm.CUSTOMER_ID,bcm.CONTACT_PERSON,bcm.COMPANY_NAME,pi.login_id from BROKER_COMPANY_MASTER bcm,personal_info pi where bcm.agency_code=pi.agency_code and APPLICATION_ID='2' and pi.login_id in(select login_id from login_master where status!='N' and oa_code in(select agency_code from broker_company_master where branch_code in("+branchCode+")))  order by lower(bcm.COMPANY_NAME)";
		try 
		{
			brokers = runner.multipleSelection(sql);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return brokers;
	}


	public String[][] getsLCDetailsByOpenCover(String opencover, String lcNo) 
	{
		String[][] brokerDetails = new String[0][0];
		String sql = "select  OPEN_COVER_NO,BANK_ID,LC_NUMBER,LC_DATE,LC_AMOUNT,ENTRY_DATE,EXPIRY_DATE,EFFECTIVE_DATE,AMEND_ID,REMARKS,STATUS,LC_ID from open_cover_lc_master where lc_id=? and open_Cover_no=?";
		try 
		{
			String args[] = new String[2];
			args[0] = lcNo;
			args[1] = opencover;
			brokerDetails = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return brokerDetails;
	}

	public String[][] getLcDetails(String openID, String lcNo) 
	{
		String[][] brokerDetails = new String[0][0];
		String sql = "select OPEN_COVER_NO,BANK_ID,LC_NUMBER,to_char(LC_DATE,'DD') DAY,to_char(LC_DATE,'MM') MONTH,to_char(LC_DATE,'YYYY') YEAR,LC_CURRENCY_ID,LC_AMOUNT,ENTRY_DATE,EXPIRY_DATE,EFFECTIVE_DATE,AMEND_ID,REMARKS,STATUS,LC_ID,to_char(EXPIRY_DATE,'DD') DAY,to_char(EXPIRY_DATE,'MM') MONTH,to_char(EXPIRY_DATE,'YYYY') from OPEN_COVER_LC_MASTER where OPEN_COVER_NO=? and LC_ID=?";
		try 
		{
			String args[] = new String[2];
			args[0] = openID;
			args[1] = lcNo;
			brokerDetails = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return brokerDetails;

	}
	public String[][] getLCs(String openID) 
	{
		String[][] brokerDetails = new String[0][0];
		String sql = "select OPEN_COVER_NO,BANK_ID,LC_NUMBER,LC_DATE,LC_AMOUNT,ENTRY_DATE,EXPIRY_DATE,EFFECTIVE_DATE,AMEND_ID,REMARKS,STATUS,LC_ID,LC_CURRENCY_ID from OPEN_COVER_LC_MASTER  where  OPEN_COVER_NO=? and status='Y' and LC_NUMBER not in ('None','NONE')";
		try 
		{
			String args[] = new String[1];
			args[0] = openID;
			brokerDetails = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return brokerDetails;

	}

	public String getBname(String bankid,String cid) 
	{
		String bname = "";
		String sql = "select bank_name from open_cover_bank_master where bank_id=? and status='Y' and BELONGING_COUNTRY_ID=?";
		try 
		{
			String args[] = new String[2];
			args[0] = bankid;
			args[1] = cid;
			bname = runner.singleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return bname;
	}
	public String getCName(String cid,String countryId) 
	{
		String cname = "";
		String sql = "select currency_name from currency_master where currency_id=? and status='Y' and COUNTRY_ID=? and amend_id=(select max(amend_id) from currency_master where currency_id=? and COUNTRY_ID=? and status='Y')";
		try 
		{
			String args[] = new String[4];
			args[0] = cid;
			args[1] = countryId;
			args[2] = cid;
			args[3] = countryId;
			cname = runner.singleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return cname;
	}
	public String[][] getCertificates(String openID, String lcnumber) 
	{
		String[][] brokerDetails = new String[0][0];
		String sql = "select a.open_cover_no,a.policy_no,b.total_sum_insured,b.exchange_rate,b.total_sum_insured*b.exchange_rate suminsured,b.open_lc_id,a.login_id,c.lc_number,c.LC_AMOUNT from position_master a,marine_Data b,open_Cover_lc_master c where a.application_no=b.application_no and b.open_lc_id=c.lc_id and a.open_cover_no=c.open_cover_no and a.open_cover_no=? and c.lc_number=? and  a.status='P'";
		try 
		{
			String args[] = new String[2];
			args[0] = openID;
			args[1] = lcnumber;
			brokerDetails = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return brokerDetails;

	}

	public String getProductById(String pid)
	{
		String pname = "";
		String sql = "select product_name from product_master where product_id=? and status='Y'";
		try 
		{
			String args[] = new String[1];
			args[0] = pid;
			pname = runner.singleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return pname;
	}
	public String getApplicationNo(String login,String type)
	{
		String res="";
		try
		{
			String args[] = new String[2];
			args[0] = login;
			args[1] = login;
			if(!type.equalsIgnoreCase("Customer"))
				res = runner.singleSelection("select nvl(APPLICATION_ID,'0') from personal_info where login_id=? and agency_code=(select agency_code from login_master where login_id=?)",args);
			if(type.equalsIgnoreCase("Customer"))
				res = runner.singleSelection("select nvl(APPLICATION_ID,'0') from personal_info where customer_login_id=? and agency_code=(select agency_code from login_master where login_id=?)",args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return res;
	}
	//FOr opencover resticetion for user login wise
	public String[][] getOpenCoverCertificates(String brokerLoginId)
	{
		String openCover[][] = new String[0][0];
		 
		 try
		{
			openCover = com.maan.services.util.runner.multipleSelection("select distinct(ocpm.open_cover_no),ocpm.proposal_no,ocm.MISSIPPI_OPENCOVER_NO,ocpm.Customer_Schedule,ocpm.Customer_Debit,ocpm.Customer_CustomerDebit,nvl(pi.company_name,pi.first_name) from open_cover_position_master ocpm,open_cover_master ocm,personal_info pi where ocm.broker_id in('"+brokerLoginId+"') and ocm.status='Y' and ocpm.status='P' and ocm.proposal_no=ocpm.proposal_no and pi.customer_id=ocm.customer_id and pi.status='Y' and ocm.amend_id||'-'||ocm.proposal_no in (select max(ocms.amend_id)||'-'||ocms.proposal_no from open_cover_master ocms,open_cover_position_master ocpms where ocpms.proposal_no=ocms.proposal_no  group by ocms.proposal_no) order by ocpm.open_cover_no  DESC");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		 return openCover;
	}
	public String getOpenCoverCertificatesUser(String userAgencyCode)
	{
		String res = "";
		try
		{
			String args[] = new String[1];
			args[0] = userAgencyCode;
			res = runner.singleSelection("select nvl(open_cover_no,' ') from LOGIN_USER_DETAILS where agency_code=? and product_id='11'",args);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return res;
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
		catch(Exception e)
		{
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
		return flag;
	}
	
	public HashMap getOfficeProductScheme(String login,String branch)
	{
		String sql = "";
		String officeScheme[][] = new String[0][0];
		String args[] = new String[2];
		HashMap officeHash = new HashMap();
		try
		{
			args[0] = branch;
			args[1] = login;
			sql = "select s.scheme_id,(p.product_name||'-'||s.scheme_name) from product_master p,OFS_SCHEME_MASTER s where p.status=s.status and s.status='Y' and p.branch_code=s.branch_code and s.branch_code=? and s.product_id=p.product_id and s.scheme_id in (select scheme_id from login_user_details where scheme_id is not null and agency_code=(select agency_code from login_master where login_id=?) and status='Y') order by s.scheme_id";
			officeScheme = runner.multipleSelection(sql,args);
			for(int i=0;i<officeScheme.length;i++)
				officeHash.put(officeScheme[i][0],officeScheme[i][1]);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return officeHash;
	}

	public String[][] getOfficeProductSchemeForBroker(String login,String branch)
	{
		String sql = "";
		String officeScheme[][] = new String[0][0];
		String args[] = new String[2];
		try
		{
			args[0] = branch;
			args[1] = login;
			sql = "select s.scheme_id,(p.product_name||'-'||s.scheme_name) from product_master p,OFS_SCHEME_MASTER s where p.status=s.status and s.status='Y' and p.branch_code=s.branch_code and s.branch_code=? and s.product_id=p.product_id and s.scheme_id in (select scheme_id from login_user_details where scheme_id is not null and agency_code=(select agency_code from login_master where login_id=?) and status='Y') order by s.scheme_id";
			officeScheme = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return officeScheme;
	}
	
	public String[][] getCommisionDataForOffice(String ucode,String proId)
	{
		String officeCommis[][] = new String[0][0];
		String sql = "";
		String args[] = new String[2];
		try
		{
			args[0] = ucode;
			args[1] = proId;
			sql = "select lud.PRODUCT_ID,lud.scheme_id,lud.COMMISSION,lud.INSURANCE_END_LIMIT,lm.USER_ID_CREATION,lm.AC_EXECUTIVE_CREATION,lm.REFERAL,lud.status,lud.SPECIAL_DISCOUNT,nvl(lud.FREIGHT_DEBIT_OPTION,'Y') from login_master lm,login_user_details lud where lm.agency_code=lud.agency_code and lud.agency_code=? and lud.product_id=? and lud.status='Y' order by lud.product_id";
			officeCommis = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return officeCommis;
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
				values = data.validString((String) pro.get("suminsured"+(i+1)), true);
				if ("needed".equalsIgnoreCase(values))
					error = error+"<br>* Enter Valid suminsured for Scheme-->  "+schemeName;
				else if ("Invalid".equalsIgnoreCase(values))
					error = error+"<br>* Enter Valid suminsured for Scheme-->  "+schemeName;

				if (((String) pro.get("suminsured"+(i+1))).equalsIgnoreCase("0"))
				{
					error = error+"<br>* Suminsured Insured Should Not Be Zero for Scheme  "+schemeName;
				}
				
				values = data.validString((String) pro.get("discount"+(i+1)),true);
				if ("needed".equalsIgnoreCase(values))
					error = error+ "<br>* Enter Valid Discount % for Scheme-->"+schemeName;
				else if ("Invalid".equalsIgnoreCase(values))
					error = error+ "<br>* Enter Valid Discount % for Scheme-->"+schemeName;
			}
		} 
		else 
		{
			error = error + "<BR>* Please select at least one Scheme";
		}
		return error;
	}
	public HashMap getExistingOfficeDetails(String acode)
	{
		HashMap officeDetails = new HashMap();
		String sql="select scheme_id,nvl(INSURANCE_START_LIMIT,'0'),nvl(DISCOUNT_OF_PREMIUM,'0') from login_user_Details where agency_Code=? and product_id=30 and status='Y' order by scheme_id";
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
				officeDetails.put("suminsured"+schemeId,officeScheme[i][1]);
				officeDetails.put("discount"+schemeId,officeScheme[i][2]);
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

	public String getFdCode() {
		return fdCode;
	}

	public void setFdCode(final String fdCode) {
		this.fdCode = fdCode;
	}
	
	public String getMasterCustomerLoginId(final String agencyCode)
	{
		String masterLogin ="";
		try{
			final String args[] = {agencyCode};
			masterLogin = runner.singleSelection("select nvl(login_id,'') from login_master where agency_code=?",args);
		}catch(Exception e){
			e.printStackTrace();
		}
		return masterLogin;
	}
	
	public String[][] getFleetCoverCertificates(String brokerLoginId)
    {
        String openCover[][] = new String[0][0];
        String query = "select a.proposal_no,a.proposal_no,a.core_policy_no,null,null,null,replace(b.title||' '||b.first_name||b.last_name,'select') from motor_fleet_master a,personal_info b where a.status='P' and a.broker_id='"+brokerLoginId+"' and a.customer_id=b.customer_id";
        try
        {
            openCover = runner.multipleSelection(query);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return openCover;
    }

    public String getFleetCoverCertificatesUser(String userAgencyCode)
    {
        String res = "";
        try
        {
            String args[] = new String[1];
            args[0] = userAgencyCode;
            res = runner.singleSelection("select nvl(open_cover_no,' ') from LOGIN_USER_DETAILS where agency_code=? and product_id='75'", args);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return res;
    }
}
