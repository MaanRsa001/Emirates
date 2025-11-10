package com.maan.broker.DAO;

import java.io.PrintWriter;
import java.util.HashMap;

import proj.sql.QueryBuilder;

import com.maan.common.error.ErrorInfo;
import com.maan.common.password.passwordEnc;
import com.maan.common.util.StringUtil;
import com.maan.services.util.runner;

public class CustomerCreationBean extends ErrorInfo 
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
	private String brokerLoginId = "";
	private String customerId = "";
	private String freightfwd = "";
		
	public void setFreightfwd(String freightfwd) 
	{
		this.freightfwd = freightfwd;
	}
	
	public String getFreightfwd() {
		return freightfwd;
	}
	//Customer Creation
	public void setBrokerLoginId(String brokerLoginId) 
	{
		this.brokerLoginId = brokerLoginId;
	}
	
	public String getBrokerLoginId() 
	{
		return brokerLoginId;
	}
	public void setCustomerId(String customerId) 
	{
		this.customerId = customerId;
	}
	
	public String getCustomerId() 
	{
		return customerId;
	}
	//Customer Creation
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
		com.maan.common.util.dataCollection data = new com.maan.common.util.dataCollection();
		String error = "";
		String values = null;
		try 
		{
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


			if ("others".equalsIgnoreCase(emirate)) {
				values = data.validString(city, false);
				if ("needed".equalsIgnoreCase(values))
					error = error + "<br>*" + runner.getErrormsg("55");
				else if ("Invalid".equalsIgnoreCase(values))
					error = error + "<br>*" + runner.getErrormsg("56");
			}


		} catch (Exception e) {
			System.out.println("Exception in validation" + e.toString());
		} 
		return error;
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

	public String getCustomerAgencyCode(String branch) 
	{
		String CusAgency = "";
		String sql = "select nvl(detail_name,'0') from constant_detail where CATEGORY_ID='37' and CATEGORY_DETAIL_ID='3' and status='Y' and branch_code=?";
		try 
		{
			String args[] = new String[1];
			args[0] = branch;
			CusAgency = runner.singleSelection(sql,args);
			int tepmCusAgency = Integer.parseInt(CusAgency);
			tepmCusAgency = tepmCusAgency+1;
			String args1[] = new String[2];
			args1[0] = ""+tepmCusAgency;
			args1[1] = branch;
			runner.multipleUpdation("update constant_detail set detail_name=? where CATEGORY_ID='37' and CATEGORY_DETAIL_ID='3' and status='Y' and branch_code=?",args1);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return CusAgency;
	}
	
	public String insertCustomerEntry(String branch) 
	{
		String user_Id = "1";
		String Query = "";
		String process = "NOT";
		String sql = "";
		String upSql = "";
		String res = "";
		try 
		{
			String cusCheck = runner.singleSelection("select nvl(agency_code,' ') from personal_info where customer_id='"+customerId+"' and application_id='1'"); // and login_id='"+brokerLoginId+"'");	
			cusCheck = cusCheck.trim();
			if(cusCheck.length()<=0)
			{
				ucode = "c"+getCustomerAgencyCode(branch);
				String oacode = getOACode(brokerLoginId);
				res = runner.singleSelection("select oa_code from login_master where login_id=(select login_id from personal_info where customer_id='"+customerId+"')");
				if(res.equalsIgnoreCase(oacode))
				{
					upSql = "update personal_info set agency_code='"+ucode+"',oa_code='"+oacode+"',fd_code='"+ucode+"' where customer_id='"+customerId+"' and application_id='1' ";//and login_id='"+brokerLoginId+"'";
					com.maan.services.util.runner.updation(upSql);
				}
				System.out.println("..insertCustomerEntry.."+upSql);
			}
			else
			{
				ucode = cusCheck;
			}
			setUcode(ucode);
			process = "YES";
		}
		catch (Exception e) 
		{
			System.out.println("Exception in insertion and updation of the Customer information "+ e.toString());
			e.printStackTrace();
		}
		System.out.println("..insertCustomerEntry.."+process);
		return process;
	}

	public String[][] getProducts(String broLoginId,String branch,String adminPids) 
	{
		String[][] products = new String[0][0];
		String sql = "select distinct log.product_id,pro.product_name,pro.COMPANY_ID from login_user_details log, PRODUCT_MASTER pro where log.login_id=? and pro.product_id=log.product_id and pro.status='Y' and log.status='Y' and pro.branch_code=? and pro.product_id in("+adminPids+") order by log.product_id";
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
	public String validateCommision(String brokerLoginId) 
	{
		com.maan.common.util.dataCollection data = new com.maan.common.util.dataCollection();
		String[][] BrokerCommision = getCommisionDataValidation(brokerLoginId);
		String error = "";
		HashMap pro = new HashMap();
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
				if (!((String) pro.get("product" + (i + 1))).equals("11")) 
				{
						String pname = getProductById((String)pro.get("product"	+ (i + 1)));	
						
						values = data.validString((String) pro.get("commision"+ (i + 1)), true);
						if ("needed".equalsIgnoreCase(values))
							error = error+ "<br>* Enter Valid Commmision for Product "+ pname;
						else if ("Invalid".equalsIgnoreCase(values))
							error = error+ "<br>* Enter Valid Commmision for Product "+ pname;
						else if (Double.parseDouble((String) pro.get("commision" + (i + 1))) > Double.parseDouble(BrokerCommision[i][1] == null ? "0.0":BrokerCommision[i][1]))
							error = error + "<br>* Commmision for Product"+ pname + " Exceded The Limit";
						
						values = data.validString((String) pro.get("suminsured"	+ (i + 1)), true);
						if ("needed".equalsIgnoreCase(values))
							error = error+ "<br>* Enter Valid suminsured for "+ pname;
						else if ("Invalid".equalsIgnoreCase(values))
							error = error+ "<br>* Enter Valid suminsured for "+ pname;
						else if (Long.parseLong((String) pro.get("suminsured"+ (i + 1))) > Long	.parseLong(BrokerCommision[i][2] == null ? "0.0": BrokerCommision[i][2]))
							error = error+ "<br>* Suminsured for "+ pname + " Exceded The Limit";
						if (((String) pro.get("suminsured" + (i + 1))).equalsIgnoreCase("0")) 
						{
							error = error+ "<br>* Suminsured Insured Should Not Be Zero for  "+pname;
						}
						
						values = data.validString((String) pro.get("premium"+ (i + 1)), true);
						if ("needed".equalsIgnoreCase(values))
							error = error+ "<br>* Enter Valid Minimum Premium for "+pname;
						else if ("Invalid".equalsIgnoreCase(values))
							error = error+ "<br>* Enter Valid Minimum Premium for "+pname;
						else if (Double.parseDouble((String) pro.get("premium"	+ (i + 1))) > Double.parseDouble(BrokerCommision[i][8] == null ? "0.0"	: BrokerCommision[i][8]))
							error = error+ "<br>* Minimum Premium for  "+pname+" Exceded The Limit";

						values = data.validString((String) pro.get("bday"+ (i + 1)), true);
						if ("needed".equalsIgnoreCase(values))
							error = error+ "<br>* Enter Valid BackDates for "+pname;
						else if ("Invalid".equalsIgnoreCase(values))
							error = error+ "<br>* Enter Valid BackDates for "+pname;
						else if (Double.parseDouble((String) pro.get("bday"	+ (i + 1))) > Double.parseDouble(BrokerCommision[i][9] == null ? "0.0"	: BrokerCommision[i][9]))
							error = error+ "<br>* BackDates for  "+pname+" Exceded The Limit"+i+"..."+(BrokerCommision[i][9] == null ? "0.0"	: BrokerCommision[i][9]);

						values = (String) pro.get("Quote"+ (i + 1));
						if ("select".equalsIgnoreCase(values))
							error = error+ "<br>* Please Select Quote Option for "+pname;
						
					/*
						values = data.validString((String) pro.get("discount"+ (i + 1)), true);
						if ("needed".equalsIgnoreCase(values))
							error = error+ "<br>* Enter Valid discount for "+getProductById((String) pro.get("product"+ (i + 1)));
						else if ("Invalid".equalsIgnoreCase(values))
							error = error+ "<br>* Enter Valid discount for "+ getProductById((String) pro.get("product"	+ (i + 1)));
						else if (Double.parseDouble((String) pro.get("discount"	+ (i + 1))) > Double.parseDouble(BrokerCommision[i][7] == null ? "0.0"	: BrokerCommision[i][7]))
						error = error+ "<br>* Discount for  "+ getProductById((String) pro.get("product"+ (i + 1))) + " Exceded The Limit";
					*/
				}
			}
		} 
		else 
		{
			error = error + "<BR>*Select At Least One Product";
		}
		return error;
	}

	public String validateCommision()
	{
		com.maan.common.util.dataCollection data = new com.maan.common.util.dataCollection();
		
		String[][] BrokerCommision = getCommisionData123(brokerLoginId);
		
		String error = "";
		HashMap pro = new HashMap();
		System.out.println("Royal Test length of the produs are "+ proDetails.size());
		if (proDetails.size() > 0) 
		{
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

	public String insertOrUpdate(String ucode,String brokerLoginId,String adminId,String cusLoginId,String openCoverNos) 
	{
		java.util.Date dd = new java.util.Date();
		java.text.SimpleDateFormat simpleFormatter = new java.text.SimpleDateFormat("dd-MM-yyyy");
		String process = "";
		int i = 0;
		String commision = "";
		String suminsured = "";
		String minpremium = "";
		String backdates = "";
		String quote = "";
		String schedule = "";
		String debit = "";
		String cusDebit = "";
		String CusCertificate = "";
		String policy = "";
		String provisionPre = "";
		String[][] userdetails = new String[0][0];
		HashMap pro = new HashMap();
		String oacode = getOACode(brokerLoginId);

		try 
		{
			try 
			{
				try
				{
					String sql = "select CUSTOMER_ID,FIRST_NAME,AGENCY_CODE from personal_info where AGENCY_CODE=?";
					String args1[] = new String[1];
					args1[0] = ucode;
					userdetails = runner.multipleSelection(sql,args1);
					System.out.println("userdetails........"+userdetails.length);
				}
				catch(Exception e)
				{
					System.out.println("Exception for select Personal Info ::: "+ e);e.printStackTrace();
				}
				
				String[][] products = new String[0][0];
				String sqlcheck = "";
				sqlcheck = "select product_id from login_user_details where AGENCY_CODE=?";
				try
				{
					String args2[] = new String[1];
					args2[0] = ucode;
					products = runner.multipleSelection(sqlcheck,args2);
					System.out.println("products........"+products.length);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
				
				pro = (HashMap) proDetails.get("productsdetails"+(i+1));
				String avail = "not";
				for (int p = 0; p < products.length; p++) 
				{
					avail = "not";
					for (int h = 0; h < proDetails.size(); h++) 
					{
						if (products[p][0].equalsIgnoreCase((String) pro.get("product"+(h+1)))) 
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
						String sqlstatus = "";
						sqlstatus = "update login_user_details set status='N' where AGENCY_CODE=? and product_id=?";
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

						} 
						catch (Exception e) 
						{
							e.printStackTrace();
						}
					}
				}
				for (i = 0; i < proDetails.size(); i++) 
				{
					pro = (HashMap) proDetails.get("productsdetails"+(i+1));
					
					if (userdetails.length > 0) 
					{
						if (((String) pro.get("commision"+(i + 1))).length() > 0&&pro.get("commision"+(i + 1))!= null)
						{
							commision = (String) pro.get("commision"+ (i + 1));
							commision = commision.equals("")?"0":commision;
							commision = commision.length()<=0?"0":commision;
						}
						if (((String) pro.get("suminsured"+ (i + 1))).length() > 0 && pro.get("suminsured"+ (i + 1)) != null)
						{
							suminsured = (String) pro.get("suminsured"+ (i + 1));
						}
						if(((String) pro.get("premium"+ (i + 1))).length() > 0 && pro.get("premium"+ (i + 1)) != null) 
						{
							minpremium = (String) pro.get("premium"+ (i + 1));
						}
						if (((String) pro.get("bday"+ (i + 1))).length() > 0 && pro.get("bday"+ (i + 1)) != null) {
							backdates = (String) pro.get("bday"+ (i + 1));
						} 
						else{
							backdates = "0";
						}
						if (backdates.length() <= 0){
							backdates = "0";
						}
						if (((String) pro.get("Quote"+ (i + 1))).length() > 0 && pro.get("Quote"+ (i + 1)) != null) 
						{
							quote = (String) pro.get("Quote"+ (i + 1));
						}
						if (((String) pro.get("Schedule"+ (i + 1))).length() > 0	&& pro.get("Schedule"+ (i + 1)) != null) 
						{
							schedule = (String) pro.get("Schedule"+ (i + 1));
						}
						if (((String) pro.get("Debit"+ (i + 1))).length() > 0	&& pro.get("Debit"+ (i + 1)) != null) 
						{
							debit = (String) pro.get("Debit"+ (i + 1));
						}
						if (((String) pro.get("CusDebit"+ (i + 1))).length() > 0	&& pro.get("CusDebit"+ (i + 1)) != null) 
						{
							cusDebit = (String) pro.get("CusDebit"+ (i + 1));
						}

						if (((String) pro.get("CusCertificate"+ (i + 1))).length() > 0	&& pro.get("CusCertificate"+ (i + 1)) != null) 
						{
							CusCertificate = (String) pro.get("CusCertificate"+ (i + 1));
						}

						if (((String) pro.get("policy"+ (i + 1))).length() > 0	&& pro.get("policy"+ (i + 1)) != null) 
						{
							policy = (String) pro.get("policy"+ (i + 1));
							System.out.println("<br>.RoyalTEst........policy...."+policy);
						}
						
						String sqlboth = "";
						String sqlcount = "";
						sqlcount = "select count(*) from login_user_details where agency_code=? and product_id=?";
						
						int count = 0;
						try
						{
							String args4[] = new String[2];
							args4[0] = ucode;
							args4[1] = (String) pro.get("product" + (i + 1));
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
							
							sqlboth = "insert into login_user_details(USER_ID,USER_NAME,LOGIN_ID,AGENCY_CODE,OA_CODE,PRODUCT_ID,COMPANY_ID,COMMISSION,INSURANCE_START_LIMIT,INSURANCE_END_LIMIT,MIN_PREMIUM_AMOUNT,RELATIVE_USER_ID,AMEND_ID,INCEPTION_DATE,EXPIRY_DATE,EFFECTIVE_DATE,ENTRY_DATE,REMARKS,STATUS,CUSTOMER_ID,BACK_DATE_ALLOWED,CUSTOMER_QUOTE,CUSTOMER_SCHEDULE,CUSTOMER_DEBIT,CUSTOMER_CUSTOMERDEBIT,CUSTOMER_POLICY,CUSTOMER_CERTIFICATE,fd_code,open_cover_no) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
							
							userid = autoGenCustId("USER_ID","LOGIN_USER_DETAILS");
							
							try 
							{
								String argss[] = new String[29];
								argss[0] = ""+userid;
								argss[1] =  userdetails[0][1];
								if(cusLoginId.length()<=0)
									argss[2] = "NONE";
								if(cusLoginId.length()>0)
									argss[2] =  cusLoginId;
								argss[3] = userdetails[0][2];
								argss[4] = oacode;
								argss[5] = (String) pro.get("product" + (i + 1));
								argss[6] = "1";
								String tempPid = (String)pro.get("product"+(i+1));
								if(!tempPid.equals("11"))
									argss[7] = commision;
								else if(tempPid.equals("11"))
									argss[7] = "0";
								argss[8] = "1000";
								if(!tempPid.equals("11"))
									argss[9] = suminsured;
								else if(tempPid.equals("11"))
									argss[9] = "0";
								if(!tempPid.equals("11"))
									argss[10] = minpremium;
								else if(tempPid.equals("11"))
									argss[10] = "0";
								argss[11] = "0";
								argss[12] = "1";
								argss[13] = com.maan.common.util.OracleDateConversion.ConvertDate(""+ simpleFormatter.format(dd));
								argss[14] =  "";
								argss[15] = "";
								argss[16] = com.maan.common.util.OracleDateConversion.ConvertDate(""+ simpleFormatter.format(dd));
								argss[17] =  "";
								argss[18] =  "Y";
								argss[19] = userdetails[0][0];
								if(!tempPid.equals("11"))
									argss[20] = backdates;
								else if(tempPid.equals("11"))
									argss[20] = "0";
								argss[21] = quote;
								argss[22] = schedule;
								argss[23] =  debit;
								argss[24] =  cusDebit;
								argss[25] = policy;
								argss[26] = CusCertificate;
								argss[27] = getCustFDCode(userdetails[0][2]);
								if(tempPid.equals("11"))
									argss[28] = getOpenCoverNosFromProposal(openCoverNos);	//openCoverNos; // Customer Open Cover No...
								else
									argss[28] = "";
								System.out.println("....CustomerCreation...."+sqlboth);
								runner.multipleInsertion(sqlboth,argss);
							} 
							catch (Exception e) 
							{
								e.printStackTrace();
							}
						} 
						else
						{
							sqlboth = "update login_user_details set COMMISSION=?, INSURANCE_END_LIMIT=?, STATUS=?, MIN_PREMIUM_AMOUNT=?, BACK_DATE_ALLOWED=?, CUSTOMER_QUOTE=?, CUSTOMER_SCHEDULE=?, CUSTOMER_DEBIT=?,CUSTOMER_CUSTOMERDEBIT=?,CUSTOMER_POLICY=?,LOGIN_ID=?,CUSTOMER_CERTIFICATE=?,open_cover_no=? where AGENCY_CODE=? and product_id=?";
							System.out.println("inside insrtion of the user details"+ sqlboth);
							try 
							{
								String argss[] = new String[15];
									
								argss[0] = commision;
								argss[1] = suminsured;
								argss[2] = "Y";
								argss[3] = minpremium;
								argss[4] = backdates;
								argss[5] = quote;
								argss[6] = schedule;
								argss[7] = debit;
								argss[8] = cusDebit;
								argss[9] = policy;
								argss[10] = cusLoginId;
								argss[11] = CusCertificate;
								String tempProId = (String) pro.get("product" + (i + 1));
								System.out.println("openCoverNos ==========================       "+tempProId);
								System.out.println("openCoverNos ==========================       "+openCoverNos);
								if(tempProId.equals("11"))
									argss[12] = getOpenCoverNosFromProposal(openCoverNos);	//openCoverNos; // OpenCoverNos
								else
									argss[12] = "";
								argss[13] = ucode;
								argss[14] = (String) pro.get("product" + (i + 1));
								
								runner.multipleUpdation(sqlboth,argss);
							}
							catch (Exception e) 
							{
								e.printStackTrace();
							}
						}
						if (!provision.equals("NO")) 
						{
							if(!checkUserCode(ucode, "login_master")) 
							{
								String utype = "";
								utype = "Customer";
								String sql1 = "insert into login_master(LOGIN_ID,PASSWORD,USERTYPE,USERNAME,USERID,AGENCY_CODE,OA_CODE,ACCESSTYPE,RIGHTS,LPASS1,LPASS2,LPASS3,PASSDATE,COMPANY_ID,CREATED_BY,STATUS,USER_ID_CREATION,AC_EXECUTIVE_CREATION,REFERAL,fd_code) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
								try 
								{
									String argss[] = new String[20];
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
									argss[12] = com.maan.common.util.OracleDateConversion.ConvertDate(""+ simpleFormatter.format(dd));
									argss[13] = "1";
									argss[14] = adminId;
									argss[15] = "Y";
									argss[16] = "";
									argss[17] = "";
									argss[18] = "";
									argss[19] = getCustFDCode(ucode);
									runner.multipleInsertion(sql1,argss);
									process = "insert";
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
	// not used --- Pls chk and remove
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
		String qry2 = "update personal_info set CUSTOMER_LOGIN_ID=?,OA_CODE=?  where  agency_code=?";
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
	
	public String insertUserLogin(String ucode, String loginPersonId,String sdate,String edate) 
	{
		String process = "";
		passwordEnc pass = new passwordEnc();
		String encpass = pass.crypt(password.substring(0, 3), password);
		java.util.Date dd = new java.util.Date();
		java.text.SimpleDateFormat simpleFormatter = new java.text.SimpleDateFormat("dd-MM-yyyy");
		String qry = ""; 
		String oacode = getOACode(loginPersonId);
		String args[] = new String[0];
		try 
		{
			qry = "update login_master set LOGIN_ID=?,PASSWORD=?,OA_CODE=?,ENTRY_DATE=?,INCEPTION_DATE=?,EXPIRY_DATE=?  where  agency_code=?";
			args = new String[7];

			args[0] = brokerId;
			args[1] = encpass;
			args[2] = oacode;
			args[3] = com.maan.common.util.OracleDateConversion.ConvertDate(""+ simpleFormatter.format(dd));
			args[4] = com.maan.common.util.OracleDateConversion.ConvertDate(""+sdate);
			args[5] = com.maan.common.util.OracleDateConversion.ConvertDate(""+edate);
			args[6] = ucode;
			System.out.println("Process ...1"+qry);
			System.out.println("Process ...1"+args[0]);
			System.out.println("Process ...1"+args[1]);
			System.out.println("Process ...1"+args[2]);
			System.out.println("Process ...1"+args[3]);
			System.out.println("Process ...1"+args[4]);
			System.out.println("Process ...1"+args[5]);
			System.out.println("Process ...1"+args[6]);
			runner.multipleUpdation(qry,args);				
			process = "1";
		} 
		catch (Exception e) 
		{
			System.out.println("updations in login master " + e.toString());
			e.printStackTrace();
		}
		
		String qry1 = "";
		try 
		{
			qry1 = "update login_user_details set LOGIN_ID=?,OA_CODE=? where agency_code=?";
			args = new String[3];
			args[0] = brokerId;
			args[1] = oacode;
			args[2] = ucode;
			runner.multipleUpdation(qry1,args);
			process = process + "2";
		} 
		catch (Exception e) 
		{
			System.out.println("updations in login_user_details "+ e.toString());
			e.printStackTrace();
		}
		
		String qry2 = ""; 

		try
		{
			qry2 = "update personal_info set CUSTOMER_LOGIN_ID=?,OA_CODE=?  where  agency_code=?";
			args = new String[3];
			args[0] = brokerId;
			args[1] = oacode;
			args[2] = ucode;
			runner.multipleUpdation(qry2,args);
			process = process + "3";
		}
		catch (Exception e) 
		{
			System.out.println("updations in personal_info " + e.toString());
			e.printStackTrace();
		}
		System.out.println("insertUserLogin..."+process);
		return process;
	}

	public String[][] getBrokerDetails(String ucode) 
	{
		String[][] brokerDetails = new String[0][0];
		String sql = "select pi.TITLE,pi.GENDER,pi.FIRST_NAME,pi.LAST_NAME,pi.NATIONALITY,pi.DOB,pi.TELEPHONE,pi.MOBILE,pi.FAX,EMAIL,pi.ADDRESS1,pi.ADDRESS2,pi.OCCUPATION,pi.EMIRATE,pi.COUNTRY,pi.POBOX,pi.entry_date,pi.city from personal_info pi where pi.agency_code=?";
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
				sql = "update login_master set  lpass5=?, lpass4=?, lpass3=?,lpass2=?,lpass1=?,password=?,passdate=sysdate+45 ,status='Y' where agency_code=?";
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
	public String[][] getCommisionData123(String login_id)
	{
		String[][] commisionDetails = new String[0][0];
		String sql = "select lud.PRODUCT_ID,lud.COMMISSION,lud.INSURANCE_END_LIMIT,lm.USER_ID_CREATION,lm.AC_EXECUTIVE_CREATION,lm.REFERAL,lud.status,lud.SPECIAL_DISCOUNT,lud.MIN_PREMIUM_AMOUNT,lud.BACK_DATE_ALLOWED,nvl(lud.Customer_Quote,' '), nvl(lud.Customer_Schedule,' '), nvl(lud.Customer_Debit,' '), nvl(lud.Customer_CustomerDebit,' '),nvl(Customer_policy,' '), nvl(lud.CUSTOMER_CERTIFICATE,' '),lud.open_cover_no from login_master lm,login_user_details lud where lm.login_id=lud.login_id and lud.login_id=? order by lud.product_id";
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
	public String[][] getCommisionDataValidation(String login_id)
	{
		String[][] commisionDetails = new String[0][0];
		String sql = "select lud.PRODUCT_ID,lud.COMMISSION,lud.INSURANCE_END_LIMIT,lm.USER_ID_CREATION,lm.AC_EXECUTIVE_CREATION,lm.REFERAL,lud.status,lud.SPECIAL_DISCOUNT,lud.MIN_PREMIUM_AMOUNT,lud.BACK_DATE_ALLOWED,nvl(lud.Customer_Quote,' '), nvl(lud.Customer_Schedule,' '), nvl(lud.Customer_Debit,' '), nvl(lud.Customer_CustomerDebit,' ') from login_master lm,login_user_details lud where lm.login_id=lud.login_id and  lud.login_id=? and lud.product_id!='11' order by lud.product_id";
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
		sql = "select CUSTOMER_ID,APPLICATION_ID,nvl(company_name,FIRST_NAME),agency_code from personal_info where  APPLICATION_ID=? and oa_code=? and status='Y'";
		
		try
		{
			String ocode = getOACode(loginId);
			String args[] = new String[2];
			args[0] = appId;
			args[1] = ocode;
			brokers = runner.multipleSelection(sql,args);
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

	public String validateLoginCreation() 
	{
		com.maan.common.util.dataCollection data = new com.maan.common.util.dataCollection();
		String error = "";
		String values = null;
		try {
			if (brokerId.equals("") || brokerId == null	|| brokerId.equalsIgnoreCase("null")|| brokerId.length() < 5 || brokerId.indexOf(" ")!= -1 ) {
				error = error + "<BR>*Enter Valid UserId";
			} else if (StringUtil.checkSpecial(brokerId)) {
				error = error + "<BR>*Spcial Chars Not Allowed";
			} else if (!mode.equalsIgnoreCase("edit")) {
				if (checkBrokerId(brokerId)) {
					error = error + "<BR>*This Login Id Already Existed";
				}
			}
			if (password.equals("") || password == null	|| password.equalsIgnoreCase("null") 
				|| password.indexOf(" ")!= -1) 
			{
				error = error + "<BR>* "+runner.getErrormsg("362");//Enter Valid password";
			} else if (password.length() < 7) {
				error = error + "<BR>* "+runner.getErrormsg("361");//Password should be Min Eight Letters";
			} else if (StringUtil.checkSpecial(password)) {
				error = error + "<BR>* "+runner.getErrormsg("363");//Password as Spcial Chars not Allowed ";
			} else if (!validPassword(password)) {
				error = error + "<BR>* "+runner.getErrormsg("365");//Please Enter Valid Password";
			}
			if (!password.equals(retypePassword)) {
				error = error + "<BR>* "+runner.getErrormsg("364");//Entered passwords are not Matched";
			}
		} catch (Exception e) {
			System.out.println("Exception in " + e.toString());
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (Exception e) {
				System.out.println("ERROR " + e.toString());
			}
		}
		return error;
	}
	public String LoginIdStatus(String ucode) 
	{
		String status = "";
		String sql = "select CUSTOMER_LOGIN_ID from personal_info where agency_code=?";
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
			String sql = "select count(*) from personal_info where agency_code=? and APPLICATION_ID in ('5') and oa_code=?";
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

	public static boolean checkEmailSpecial(String str) 
	{
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
	public String[][] getBrokersInAdmin(String branchCode) 
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
	public String[][] getCustomerData(String index,String loginId)
	{
		String[][] CustomerData=new String[0][0];
		 String args[] = new String[4];
			 
		String sql="";
		if(index.length()<=1)
		{
			args[0] = index.toUpperCase()+"%";
			args[1] = index.toUpperCase()+"%";
			args[2] = loginId;
			args[3] = loginId;
			sql="select CUSTOMER_ID,nvl(FIRST_NAME||''||last_name,company_name),pobox,ADDRESS1,ADDRESS2,emirate,country,nvl(TELEPHONE,mobile),CUSTOMER_LOGIN_ID from personal_info where (upper(first_name) like ? or upper(company_name) like ?) and application_id='1' and login_id in(select distinct login_id from personal_info where login_id in(select distinct login_id from login_master where agency_code in(select distinct agency_code from login_user_details where agency_code in (select distinct agency_code from login_master where oa_code=(select distinct oa_code from login_master where login_id=?))) and usertype!='Freight')) and customer_id in(select distinct customer_id from OPEN_COVER_MASTER where BROKER_ID=?)";
		}
		else if(index.length()>1)
		{
			sql="select CUSTOMER_ID,nvl(FIRST_NAME||''||last_name,company_name),pobox,ADDRESS1,ADDRESS2,emirate,country,nvl(TELEPHONE,mobile),CUSTOMER_LOGIN_ID from personal_info where (upper(first_name) like ? or upper(company_name) like ?) and application_id='1' and login_id in(select distinct login_id from personal_info where login_id in(select distinct login_id from login_master where agency_code in(select distinct agency_code from login_user_details where agency_code in (select distinct agency_code from login_master where oa_code=(select distinct oa_code from login_master where login_id=?))) and usertype!='Freight')) and customer_id in(select distinct customer_id from OPEN_COVER_MASTER where BROKER_ID=?)";
			args[0] = "%"+index.toUpperCase()+"%";
			args[1] = "%"+index.toUpperCase()+"%";
			args[2] = loginId;
			args[3] = loginId;
		}
		try
		{
			CustomerData=runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("Exception in getting customer infoooooooooooooooo"+e.toString());
			e.printStackTrace();
		}
		return CustomerData;
	}
	public String[][] getOpenCoverCertificates(String brokerLoginId)
	{
		String openCover[][] = new String[0][0];
		try
		{
			 openCover = runner.multipleSelection("select distinct(ocpm.open_cover_no),ocpm.proposal_no,ocm.MISSIPPI_OPENCOVER_NO,ocpm.Customer_Schedule,ocpm.Customer_Debit,ocpm.Customer_CustomerDebit from open_cover_position_master ocpm,open_cover_master ocm,personal_info pi where ocm.broker_id in('"+brokerLoginId+"') and ocm.status='Y' and ocpm.status='P' and ocm.proposal_no=ocpm.proposal_no and pi.customer_id=ocm.customer_id and pi.status='Y' and ocm.amend_id||'-'||ocm.proposal_no in (select max(ocms.amend_id)||'-'||ocms.proposal_no from open_cover_master ocms,open_cover_position_master ocpms where ocpms.proposal_no=ocms.proposal_no  group by ocms.proposal_no) order by ocpm.open_cover_no  DESC");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		 return openCover;
	}
	
	public String[][] getOpenCoverCertificates(String brokerLoginId,String CusId)
	{
		String openCover[][] = new String[0][0];
		try
		{
			 String args[] = new String[1];
			 args[0] = CusId;
			 openCover = runner.multipleSelection("select distinct(ocpm.open_cover_no),ocpm.proposal_no,ocm.MISSIPPI_OPENCOVER_NO,ocpm.Customer_Schedule,ocpm.Customer_Debit,ocpm.Customer_CustomerDebit from open_cover_position_master ocpm,open_cover_master ocm,personal_info pi where ocm.broker_id in('"+brokerLoginId+"') and pi.customer_id=? and ocm.status='Y' and ocpm.status='P' and ocm.proposal_no=ocpm.proposal_no and pi.customer_id=ocm.customer_id and pi.status='Y' and ocm.amend_id||'-'||ocm.proposal_no in (select max(ocms.amend_id)||'-'||ocms.proposal_no from open_cover_master ocms,open_cover_position_master ocpms where ocpms.proposal_no=ocms.proposal_no  group by ocms.proposal_no) order by ocpm.open_cover_no  DESC",args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return openCover;
	}
	
	public String[][] getOpenCoverCertificatesCus(String CusLoginId)
	{
		String openCover[][] = new String[0][0];
		try
		{
			String args[] = new String[1];
			args[0] = CusLoginId;
			 openCover = com.maan.services.util.runner.multipleSelection("select (ocpm.open_cover_no),ocpm.proposal_no,ocm.MISSIPPI_OPENCOVER_NO,ocpm.Customer_Schedule,ocpm.Customer_Debit,ocpm.Customer_CustomerDebit from open_cover_position_master ocpm,open_cover_master ocm,personal_info pi where pi.customer_id=(select distinct customer_id from login_user_details where agency_code=(select agency_code from login_master where login_id=? and usertype='Customer')) and ocm.status='Y' and ocpm.status='P' and ocm.proposal_no=ocpm.proposal_no and pi.customer_id=ocm.customer_id and pi.status='Y' and ocm.amend_id||'-'||ocm.proposal_no in (select max(ocms.amend_id)||'-'||ocms.proposal_no from open_cover_master ocms,open_cover_position_master ocpms where ocpms.proposal_no=ocms.proposal_no  group by ocms.proposal_no) order by ocpm.open_cover_no  DESC",args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		 return openCover;
	}
	public String[][] getOpenCoverCertificatesCus(String CusLoginId,String acode)
	{
		String openCover[][] = new String[0][0];
		try
		{
			String args[] = new String[2];
			args[0] = CusLoginId;
			args[1] = acode;
			openCover = runner.multipleSelection("select (ocpm.open_cover_no),ocpm.proposal_no,ocm.MISSIPPI_OPENCOVER_NO,ocpm.Customer_Schedule,ocpm.Customer_Debit,ocpm.Customer_CustomerDebit from open_cover_position_master ocpm,open_cover_master ocm,personal_info pi where pi.customer_id=ocm.customer_id and pi.customer_id=(select distinct customer_id from login_user_details where agency_code=(select agency_code from login_master where login_id=? and usertype='Customer')) and ocm.BROKER_USER_ID=? and ocm.status='Y' and ocpm.status='P' and ocm.proposal_no=ocpm.proposal_no and pi.customer_id=ocm.customer_id and pi.status='Y' and ocm.amend_id||'-'||ocm.proposal_no in (select max(ocms.amend_id)||'-'||ocms.proposal_no from open_cover_master ocms,open_cover_position_master ocpms where ocpms.proposal_no=ocms.proposal_no  group by ocms.proposal_no) order by ocpm.open_cover_no  DESC",args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		 return openCover;
	}
	public String updateCetificateforCustomer(String openCoverNos,String brokerLoginId,String cusId,String pid,String cusAgencyCode,String royal) 
	{
		String res = "";
		try
		{
			String upSql = "update OPEN_COVER_MASTER set CUSTOMER_ID=?,BROKER_USER_ID=? where BROKER_ID=? and PROPOSAL_NO in("+openCoverNos+") and PRODUCT_ID=?";
			String args[] = new String[4];
			args[0] = cusId;
			args[1] = cusAgencyCode;
			args[2] = brokerLoginId;
			args[3] = pid;
			res = runner.multipleUpdation(upSql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return res;
	}
	public String updateCetificateforCustomer(String openCoverNo,String Schedule,String Debit,String CusDebit,String opt) 
	{
		String upSql = "update OPEN_COVER_POSITION_MASTER set Customer_Schedule=?, Customer_Debit=?, Customer_CustomerDebit=? where OPEN_COVER_NO=?";
		String res = "";
		try
		{
			String args[] = new String[4];
			args[0] = Schedule;
			args[1] = Debit;
			args[2] = CusDebit;
			args[3] = openCoverNo;
			res = runner.multipleUpdation(upSql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return res;
	}
	public String updateCusLogStatus(String accode,String process,String sdate)
	{
		String temp = "sysdate";
		//temp = runner.getSysdate(runner.singleSelection("select nvl(branch_code,'01') from BROKER_COMPANY_MASTER where agency_code in ("+accode+")"));		
		String res="";
		try
		{
			if(process.equalsIgnoreCase("Deactivate"))
			{
				String sql1 = "update personal_info set status='D' where agency_code in("+accode+")";
				String sql2 = "update Login_Master set status='D' where agency_code in("+accode+")";
				 res = com.maan.services.util.runner.updation(sql1);
				res = com.maan.services.util.runner.updation(sql2);
			}
			else if(process.equalsIgnoreCase("Activate"))
			{
				String sql1 = "update personal_info set status='Y' where agency_code in("+accode+")";
				String sql2 = "update Login_Master set status='Y' where agency_code in("+accode+")";
				 res = com.maan.services.util.runner.updation(sql1);
				res = com.maan.services.util.runner.updation(sql2);
			}
			else if(process.equalsIgnoreCase("Date"))
			{
				String sql2 = "update Login_Master set EXPIRY_DATE='"+com.maan.common.util.OracleDateConversion.ConvertDate(""+sdate)+"',ENTRY_DATE=(select "+temp+" from dual) where agency_code in('"+accode+"')";
				res = com.maan.services.util.runner.updation(sql2);
				
			}
			else if(process.equalsIgnoreCase("Deleted"))
			{
				String sql1 = "update personal_info set status='N' where agency_code in("+accode+")";
				String sql2 = "update Login_Master set status='N' where agency_code in("+accode+")";
				 res = com.maan.services.util.runner.updation(sql1);
				res = com.maan.services.util.runner.updation(sql2);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return res; 
	}
	
	public String updateCusLogStatus(final String accode,final String status)
	{
		String res="";
		try
		{
			final String sql1 = "update personal_info set status='"+status+"' where agency_code in('"+accode+"')";
			final String sql2 = "update Login_Master set status='"+status+"' where agency_code in('"+accode+"')";
			res = runner.updation(sql1);
			res = runner.updation(sql2);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return res; 
	}
	public String[][] getSelected(String loginId, String appId, String status) 
	{
		String temp = "sysdate";
		//temp = runner.getSysdate(runner.getuserBranch(loginId));		
		String[][] brokers = new String[0][0];
		String sql = "";
		try
		{
			String oacode=getOACode(loginId);
			if(!status.equalsIgnoreCase("E"))
			{
				if(!status.equalsIgnoreCase("DD"))
				{
					sql = "select per.CUSTOMER_ID,per.APPLICATION_ID,nvl(per.FIRST_NAME,per.company_name),per.agency_code,to_char(log.EXPIRY_DATE,'dd-mm-yyyy') from personal_info per, login_master log where  per.APPLICATION_ID=? and per.oa_code=? and per.agency_code=log.agency_code and log.EXPIRY_DATE>(select "+temp+" from dual) and per.status=?";
					String args[] = new String[3];
					args[0] = appId;
					args[1] = oacode;
					args[2] = status;
					brokers = runner.multipleSelection(sql,args);
				}
				if(status.equalsIgnoreCase("DD"))
				{
					sql = "select per.CUSTOMER_ID,per.APPLICATION_ID,nvl(per.FIRST_NAME,per.company_name),per.agency_code,to_char(log.EXPIRY_DATE,'dd-mm-yyyy') from personal_info per, login_master log where  per.APPLICATION_ID=? and per.oa_code=? and per.agency_code=log.agency_code and log.EXPIRY_DATE>(select "+temp+" from dual) and per.status='Y'";
					String args[] = new String[2];
					args[0] = appId;
					args[1] = oacode;
					brokers = runner.multipleSelection(sql,args);
				}
			}	
			else if(status.equalsIgnoreCase("E"))
			{
				sql = "select per.CUSTOMER_ID,per.APPLICATION_ID,nvl(per.FIRST_NAME,per.company_name),per.agency_code,to_char(log.EXPIRY_DATE,'dd-mm-yyyy') from personal_info per, login_master log where  per.APPLICATION_ID=? and per.oa_code=? and per.agency_code=log.agency_code and log.EXPIRY_DATE<=(select "+temp+" from dual)";
				String args[] = new String[2];
				args[0] = appId;
				args[1] = oacode;
				brokers = runner.multipleSelection(sql,args);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return brokers;
	}

	public String[][] getCustomerRights(String login)
	{
		String result[][] = new String[0][0];
		String sql = "select product_id,CUSTOMER_QUOTE,CUSTOMER_SCHEDULE,CUSTOMER_DEBIT,CUSTOMER_CUSTOMERDEBIT,CUSTOMER_POLICY,CUSTOMER_CERTIFICATE,FREIGHT_DEBIT_OPTION from LOGIN_USER_DETAILS where login_id=? order by product_id";
		try
		{
			String args[] = new String[1];
			args[0] = login;
			result = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	public boolean getIsBrokerHasDC(String login)
	{
		String sql = "select count(*) from LOGIN_USER_DETAILS where oa_code=(select oa_code from login_master where login_id=?) and CUSTOMER_CERTIFICATE='Y' and product_id='11'";
		boolean status = false;
		try
		{
			String result = "";
			String args[] = new String[1];
			args[0] = login;
			result = runner.singleSelection(sql,args);
			if(!result.equals("0"))
			{
				status = true;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
	
	// Multi Login Creation - June 02
	
	public String[][] getBrokerMOCList(final String adminBranch)
	{
		String temp = "sysdate";
		//temp = runner.getSysdate(adminBranch);		
		String result[][] = new String[0][0];
		try
		{
			final String sql = "select bcm.company_name,count(ocm.broker_id),ocm.broker_id from open_cover_position_master ocpm,open_cover_master ocm,broker_company_master bcm,login_master log where ocpm.proposal_no=ocm.proposal_no and ocm.broker_id=log.login_id and bcm.branch_code=? and log.agency_code=bcm.agency_code and ocpm.open_cover_no is not null and ocm.amend_id||'-'||ocm.proposal_no in (select max(ocms.amend_id)||'-'||ocms.proposal_no from open_cover_master ocms,open_cover_position_master ocpms where ocpms.proposal_no=ocms.proposal_no and to_date(ocpm.expiry_date,'dd-MON-YYYY') >= to_date("+temp+",'dd-MON-YYYY') and (ocpm.admin_status is null or ocpm.admin_status='Y') group by ocms.proposal_no) group by bcm.company_name,ocm.broker_id order by bcm.company_name";
			final String args[] = {adminBranch};
			result = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	
	public String[][] getCustomerMOCListForBroker(final String brokerLogin) // Customer Multi Login Creation Second Screen 
	{
		String temp = "sysdate";
		//temp = runner.getSysdate("01");		
		String result[][] = new String[0][0];
		try
		{
			final String sql = "select ocpm.proposal_no,ocpm.open_cover_no,ocm.MISSIPPI_OPENCOVER_NO,(per.first_name||company_name),ocm.broker_id,per.customer_id,nvl(per.CUSTOMER_LOGIN_ID,''),nvl(per.agency_code,'0'),nvl(per.fd_code,'0') from open_cover_position_master ocpm,open_cover_master ocm,personal_info per where ocpm.proposal_no=ocm.proposal_no and per.customer_id=ocm.customer_id and ocpm.open_cover_no is not null and ocm.broker_id in (?) and ocm.amend_id||'-'||ocm.proposal_no in (select max(ocms.amend_id)||'-'||ocms.proposal_no from open_cover_master ocms,open_cover_position_master ocpms where ocpms.proposal_no=ocms.proposal_no and to_date(ocpm.expiry_date,'dd-MON-YYYY') >= to_date("+temp+",'dd-MON-YYYY') and (ocpm.admin_status is null or ocpm.admin_status='Y') group by ocms.proposal_no) order by ocpm.open_cover_no DESC";
			final String args[] = {brokerLogin};
			result = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	
	public String getCustFDCode(final String agencyCode)
	{
		String fdCode = "";
		try{
			final String args[] = {agencyCode};
			fdCode = runner.singleSelection("select fd_code from personal_info where agency_code=?",args);
		}catch(Exception e){
			e.printStackTrace();
		}
		return fdCode;
	}
	
	public String getSubCustomerAgencyCode(final String branch) 
	{
		String CusAgency = "";
		String sql = "select nvl(detail_name,'0') from constant_detail where CATEGORY_ID='37' and CATEGORY_DETAIL_ID='3' and status='Y' and branch_code=?";
		try 
		{
			String args[] = new String[1];
			args[0] = branch;
			CusAgency = runner.singleSelection(sql,args);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return CusAgency;
	}
	
	public String getCustLoginByAgency(String agencyCode)
	{
		String custLogin = "";
		try{
			final String args[] = {agencyCode};
			custLogin = runner.singleSelection("select nvl(customer_login_id,'') from personal_info where agency_code=?",args);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return custLogin;
	}
	
	public String[][] getOpenCoverCertificates(String brokerLoginId,String CusId,String agencyCode)
	{
		String openCover[][] = new String[0][0];
		try
		{
			 final String masCustomerId = runner.singleSelection("select customer_id from personal_info where agency_code=(select fd_code from personal_info where agency_code='"+agencyCode+"')"); 
			 String args[] = new String[1];
			 args[0] = masCustomerId;
			 openCover = runner.multipleSelection("select distinct(ocpm.open_cover_no),ocpm.proposal_no,ocm.MISSIPPI_OPENCOVER_NO,ocpm.Customer_Schedule,ocpm.Customer_Debit,ocpm.Customer_CustomerDebit from open_cover_position_master ocpm,open_cover_master ocm,personal_info pi where ocm.broker_id in('"+brokerLoginId+"') and pi.customer_id=? and ocm.status='Y' and ocpm.status='P' and ocm.proposal_no=ocpm.proposal_no and pi.customer_id=ocm.customer_id and pi.status='Y' and ocm.amend_id||'-'||ocm.proposal_no in (select max(ocms.amend_id)||'-'||ocms.proposal_no from open_cover_master ocms,open_cover_position_master ocpms where ocpms.proposal_no=ocms.proposal_no  group by ocms.proposal_no) order by ocpm.open_cover_no  DESC",args);
		}
		catch(Exception e)
		{
			System.out.println("getOpenCoverCertificates....");
			e.printStackTrace();
		}
		return openCover;
	}
	
	public String[][] getMOCForCustomer(final String CusLoginId,final String acode)
	{
		String openCover[][] = new String[0][0];
		try
		{
			 final String masCustomerId[][] = runner.multipleSelection("select customer_login_id,fd_code from personal_info where agency_code=(select fd_code from personal_info where agency_code='"+acode+"')");
			/* String args[] = new String[2];
		 	 args[0] = CusLoginId;
		 	 args[1] = acode;
		 	 openCover = runner.multipleSelection("select (ocpm.open_cover_no),ocpm.proposal_no,ocm.MISSIPPI_OPENCOVER_NO,ocpm.Customer_Schedule,ocpm.Customer_Debit,ocpm.Customer_CustomerDebit from open_cover_position_master ocpm,open_cover_master ocm,personal_info pi where pi.customer_id=ocm.customer_id and pi.customer_id=(select distinct customer_id from login_user_details where agency_code=(select agency_code from login_master where login_id=? and usertype='Customer')) and ocm.BROKER_USER_ID=? and ocm.status='Y' and ocpm.status='P' and ocm.proposal_no=ocpm.proposal_no and pi.customer_id=ocm.customer_id and pi.status='Y' and ocm.amend_id||'-'||ocm.proposal_no in (select max(ocms.amend_id)||'-'||ocms.proposal_no from open_cover_master ocms,open_cover_position_master ocpms where ocpms.proposal_no=ocms.proposal_no  group by ocms.proposal_no) order by ocpm.open_cover_no DESC",args);*/
			 if(masCustomerId.length > 0 ){
				 String args[] = new String[2];
			 	 args[0] = masCustomerId[0][0];
			 	 args[1] = masCustomerId[0][1];
			 	System.out.println("getMOCForCustomer........................"+masCustomerId[0][0]);
			 	System.out.println("getMOCForCustomer........................"+masCustomerId[0][1]);
			 	 openCover = runner.multipleSelection("select (ocpm.open_cover_no),ocpm.proposal_no,ocm.MISSIPPI_OPENCOVER_NO,ocpm.Customer_Schedule,ocpm.Customer_Debit,ocpm.Customer_CustomerDebit from open_cover_position_master ocpm,open_cover_master ocm,personal_info pi where pi.customer_id=ocm.customer_id and pi.customer_id=(select distinct customer_id from login_user_details where agency_code in (select agency_code from login_master where login_id=? and usertype='Customer')) and ocm.BROKER_USER_ID=? and ocm.status='Y' and ocpm.status='P' and ocm.proposal_no=ocpm.proposal_no and pi.customer_id=ocm.customer_id and pi.status='Y' and ocm.amend_id||'-'||ocm.proposal_no in (select max(ocms.amend_id)||'-'||ocms.proposal_no from open_cover_master ocms,open_cover_position_master ocpms where ocpms.proposal_no=ocms.proposal_no  group by ocms.proposal_no) order by ocpm.open_cover_no DESC",args);
			 }
		}
		catch(Exception e)
		{
			System.out.println("getMOCForCustomer........................");
			e.printStackTrace();
		}
		 return openCover;
	}
	
	public String masterSubCustomerChk( final String agencyCode)
	{
		String count = "";
		try{
			final String args[] = {agencyCode};
			count = runner.singleSelection("select count (*) from personal_info where agency_code=fd_code and agency_code =?", args);
		}catch(Exception e){
			e.printStackTrace();
		}
		return count;
	}
	
	public String getOpenCoverNos( final String ucode)
	{
		String mocNos = "";
		try{
			final String args[] = {ucode};
			mocNos = runner.singleSelection("select proposal_no from open_cover_position_master where open_cover_no in (select nvl(open_cover_no,'') from login_user_details where agency_code = ? and product_id='11')", args);
		}catch(Exception e){
			e.printStackTrace();
		}
		return mocNos;
	}
	
	public String getOpenCoverNosFromProposal(final String proposalNo)
	{
		String openCoverNos = "";
		//String mocNos[][] = new String[0][0];
		try{
			/*mocNos = runner.multipleSelection("select open_cover_no from open_cover_position_master where proposal_no in("+proposalNo+") order by open_cover_no");
			for(int i=0;i<mocNos.length;i++){
				openCoverNos = openCoverNos+mocNos[i][0]+",";
			}
			if(openCoverNos.length()>0){
				openCoverNos = openCoverNos.substring(0,(openCoverNos.length()-1));
			}*/
			final String sql = "SELECT SUBSTR(SYS_CONNECT_BY_PATH(OPEN_COVER_NO,','),2) csv FROM (SELECT OPEN_COVER_NO,ROW_NUMBER() OVER (ORDER BY OPEN_COVER_NO) rn,COUNT(*) OVER() cnt from OPEN_COVER_POSITION_MASTER where proposal_no in("+proposalNo+") order by open_cover_no ) WHERE rn = cnt START WITH rn = 1 CONNECT BY rn = PRIOR rn + 1";
			openCoverNos = runner.singleSelection(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		return openCoverNos;
	}
} // Class
