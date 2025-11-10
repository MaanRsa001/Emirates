package com.maan.services;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.maan.services.util.runner;

public class customerInfo 
{
	private PrintWriter out = null;
	private String title = "";
	private String gender = "";
	private String firstName = "";
	private String lastName = "";
	private String nationality = "";
	private String dobDay = "";
	private String dobMonth = "";
	private String dobYear = "";
	private String telephone = "";
	public String city = "";
	public String cityStatus = "";
	private String mobile = "";
	private String fax = "";
	private String email = "";
	private String address1 = "";
	private String address2 = "";
	private String occupation = "";
	private String emirate = "";
	private String country = "";
	private String poBox = "";
	private String orgName = "";
	private String quoteNo = "";
	private int countNo = 0;
	private String customerCode = "";
	private String branch="";
	private String clientCustomerId="";
	private String arNo="";
	private String customerName="";

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getArNo() {
		return arNo;
	}

	public void setArNo(String arNo) {
		this.arNo = arNo;
	}

	public String getClientCustomerId() {
		return clientCustomerId;
	}

	public void setClientCustomerId(String clientCustomerId) {
		this.clientCustomerId = clientCustomerId;
	}

	public void setBranch(String branch){
		this.branch = branch;
	}

	public String getBranch(){
		return branch;
	}

	public void setOut(PrintWriter out) {
		this.out = out;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
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

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public void setEmirate(String emirate) {
		this.emirate = emirate;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setPoBox(String poBox) {
		this.poBox = poBox;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public void setQuoteNo(String quoteNo) {
		this.quoteNo = quoteNo;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setCityStatus(String cityStatus) {
		this.cityStatus = cityStatus;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getTitle() {
		return title;
	}

	public String getGender() {
		return gender;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getNationality() {
		return nationality;
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

	public String getTelephone() {
		return telephone;
	}

	public String getMobile() {
		return mobile;
	}

	public String getFax() {
		return fax;
	}

	public String getEmail() {
		return email;
	}

	public String getAddress1() {
		return address1;
	}

	public String getAddress2() {
		return address2;
	}

	public String getOccupation() {
		return occupation;
	}

	public String getEmirate() {
		return emirate;
	}

	public String getCountry() {
		return country;
	}

	public String getPoBox() {
		return poBox;
	}

	public String getOrgName() {
		return orgName;
	}

	public String getQuoteNo() {
		return quoteNo;
	}

	public String getCity() {
		return city;
	}

	public String getCityStatus() {
		return cityStatus;
	}

	public int getCountNo() {
		return countNo;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public String[][] getExistingCustomers(String login_id)
	{
		String qry = "";
		String args[] = new String[1];
		String[][] returnVal = new String[0][0];
		try
		{
			args[0] = login_id;
			qry = "select first_name,last_name,email,mobile,customer_id from personal_info where login_id=?";
			returnVal = runner.multipleSelection(qry);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return returnVal;
	}

	public String validateFields(String ProductId)
	{
		com.maan.services.util.dataCollection data = new com.maan.services.util.dataCollection();
		String error = "";
		String values = null;
		
		boolean flag = false;
		boolean mobileFlag = false;
		if("".equalsIgnoreCase(title))
			error = error+"<br>*Please Select Title";
		flag = specialCharacterValidation(firstName);
		 if(!flag)
			 error = error+"<br>*Special Characters are not allowed "+firstName;
	
		values = data.validString(firstName, false);
		if ("needed".equalsIgnoreCase(values))
			error = error + "<br>*" + runner.getErrormsg("3");
		

		if(!branch.equalsIgnoreCase("001") && !branch.equalsIgnoreCase("002") && !branch.equalsIgnoreCase("003") && !branch.equalsIgnoreCase("010") ) 
		{
			if (!"DD".equalsIgnoreCase(dobDay) || !"MMM".equalsIgnoreCase(dobMonth)	|| !"YYYY".equalsIgnoreCase(dobYear)) 
			{
				values = data.checkDate(dobDay + "/" + dobMonth + "/" + dobYear);
				if ("Invalid".equalsIgnoreCase(values))
					error = error + "<br>*" + runner.getErrormsg("8");
			}
		}
		
		if(!address1.equalsIgnoreCase("") && address1 !=null && !address1.equalsIgnoreCase("null"))
		{
			flag = addressValidation(address1); // address1
			 if(!flag)
				 error = error+"<br>*Address field special Characters are not allowed "+address1;
		}
		
		if(!address2.equalsIgnoreCase("") && address2 !=null && !address2.equalsIgnoreCase("null"))
		{
			flag = addressValidation(address2); // address2
			 if(!flag)
				 error = error+"<br>*Address field special Characters are not allowed "+address2;
		}
		
		//CITY
		if ("select".equalsIgnoreCase(emirate))
			error = error + "<br>*" + runner.getErrormsg("25");
		
		if ("others".equalsIgnoreCase(emirate) && "".equalsIgnoreCase(city.trim()))
			error = error + "<br>* Please Enter the City ";

		if ("others".equalsIgnoreCase(emirate) && !("".equalsIgnoreCase(city.trim())))
		{
			flag = specialCharacterValidation(city); // City
			 if(!flag)
				 error = error+"<br>*Special Characters are not allowed "+city;
			 emirate = city;
		}

		//poBox
		if ("needed".equalsIgnoreCase(data.validLength(poBox, 1)))
			error = error + "<br>*" + runner.getErrormsg("29");
		
		mobileFlag = validPhoneMobile(poBox); // poBox
		if(!mobileFlag)
			error = error+"<br>*POBOX number Should be digit "+poBox;
		
		values = data.validString(telephone, true);
		
		/*if (mobile != null && mobile.length() > 2) 
		{
			if ("needed".equalsIgnoreCase(data.validLength(mobile, 10)))
				error = error + "<br>*" + runner.getErrormsg("13");
		}*/
		
		//telephone
		flag = specialCharacterValidation(telephone); //Telephone Field
		 if(!flag)
			 error = error+"<br>*Special Characters are not allowed "+telephone;

		mobileFlag = validPhoneMobile(telephone); //Telephone Field
		if(!mobileFlag)
			error = error+"<br>*Mobile number or Phone Number Should be digit "+telephone;

		//mobile
		flag = specialCharacterValidation(mobile); //Mobile Field
		 if(!flag)
			 error = error+"<br>*Special Characters are not allowed "+mobile;

		mobileFlag = validPhoneMobile(mobile); //Mobile Field
		if(!mobileFlag)
			error = error+"<br>*Mobile number or Phone Number Should be digit "+mobile;

		flag = specialCharacterValidation(fax); // fax
		 if(!flag)
			 error = error+"<br>*Special Characters are not allowed "+fax;

		mobileFlag = validPhoneMobile(fax); // fax
		if(!mobileFlag)
			error = error+"<br>*FAX Should be digit "+fax;

		//EMAIL
		values = data.emailValidate(email);
		
		if ("Invalid".equalsIgnoreCase(values))
			error = error + "<br>*" + runner.getErrormsg("20");

		//OCCUPATION
		if(!occupation.equalsIgnoreCase("") && occupation !=null && !occupation.equalsIgnoreCase("null"))
		{
			flag = specialCharacterNumberValidation(occupation); // Occupation
			 if(!flag)
				 error = error+"<br>*Occupation field special Characters and numbers are not allowed "+occupation;
		}
		return error;
	}

	public void settingValues(String customer_id)
	{
		try
		{
			String args[] = new String[1];
			String qry = "";
			args[0] = customer_id;
			qry = "select title,gender,nvl(first_name,company_name),last_name,nationality,to_char(dob,'DD') as dobday,to_char(dob,'MM') as dobmonth,to_char(dob,'YYYY') as dobyear,telephone,mobile,fax,email,address1,address2,occupation,emirate,country,pobox,company_name,nvl(missippi_customer_code,0),city,cust_ar_no,cust_name from personal_info where customer_id=?";

			String[][] values = runner.multipleSelection(qry,args);
		
			values[0][0] = values[0][0]==null?"NIL":values[0][0];
			values[0][4] = values[0][4]==null?"NIL":values[0][4];
			title = (values[0][0].equalsIgnoreCase("select")?"NIL":values[0][0]);
			gender = values[0][1];
			firstName = (values[0][2] == null ? "" : values[0][2]).replaceAll("\"", "&#34;");
			lastName = values[0][3];
			nationality = (values[0][4].equalsIgnoreCase("select")?"NIL":values[0][4]);
			if (values[0][5] != null) {
				if (Integer.parseInt(values[0][5]) < 9)
					values[0][5] = "" + Integer.parseInt(values[0][5]);
			}

			if (values[0][6] != null) {
				if (Integer.parseInt(values[0][6]) < 9)
					values[0][6] = "" + Integer.parseInt(values[0][6]);
			}

			dobDay = values[0][5];
			dobMonth = values[0][6];
			dobYear = values[0][7];
			telephone = values[0][8];
			mobile = values[0][9];
			fax = values[0][10];
			email = values[0][11];
			try {
				address1 = (values[0][12] == null ? "" : values[0][12])
						.replaceAll("\"", "&#34;");
				address2 = (values[0][13] == null ? "" : values[0][13])
						.replaceAll("\"", "&#34;");
			} catch (Exception e) {
				address1 = values[0][12];
				address2 = values[0][13];
			}
			occupation = (values[0][14] == null ? "" : values[0][14])
					.replaceAll("\"", "&#34;");
			emirate = values[0][15];
			country = values[0][16]== null?"":values[0][16];
			poBox = values[0][17];
			customerCode = values[0][19];
			city = values[0][20];
			
			if (values[0][18] != null) {
				orgName = values[0][18];
			}
			
			/*if("M/S".equalsIgnoreCase(title))
				firstName = orgName;*/
			firstName = (firstName == null ? "" : firstName).replaceAll("\"","&#34;");
			arNo = values[0][21];
			customerName = values[0][22];
		} catch (Exception e) {
			System.out.println("  Value are not setting  " + e.toString());
		}
	}

	public String deleteTable(String quot, String valu, String user) 
	{
		String args[] = new String[3];
		String qry = "";
		try
		{
			quoteNo = quot;
			args[0] = valu; 
			args[1] = user;
			args[2] = quoteNo;
			
			qry = "update position_master set status = 'D',effective_date =(select sysdate from dual),lapsed_remarks =?,lapsed_date = (select sysdate from dual), lapsed_updated_by =? where quote_no =?";
			runner.multipleUpdation(qry,args);
		} 
		catch (Exception exm) 
		{
			System.out.println("Error in deleting" + exm);
			exm.printStackTrace();
		}
		return quoteNo;
	}

	public String deleteTable(String quot)
	{
		String qry = "";
		String args[] = new String[1];
		try 
		{
			quoteNo = quot;
			args[0] = quoteNo;
			qry = "update position_master set status ='D',effective_date =(select sysdate from dual) where quote_no =?";
			qry = runner.multipleUpdation(qry,args);
		}
		catch(Exception exm) 
		{
			System.out.println("Error in deleting" + exm);
			exm.printStackTrace();
		}
		return quoteNo;
	}

	public String storedValues(String customer_id, String login_id,String loginBra,String pids) 
	{
		String args[] = new String[0];
		String sResult = "";
		String res = "";
		String qry = "";
		args = new String[1];
		args[0] = customer_id;
		qry = "select count(*) from personal_info where customer_id=?";
		res = runner.singleSelection(qry,args);
			
		/*if ("M/S".equalsIgnoreCase(title)) {
			orgName = firstName;
			orgName = orgName.replaceAll("'", "''");
			firstName = "";
		}*/

		firstName = firstName.replaceAll("'", "''");
		email = (email == null ? "" : email).replaceAll("'", "''");
		address1 = (address1 == null ? "" : address1).replaceAll("'", "''");
		address2 = (address2 == null ? "" : address2).replaceAll("'", "''");
		occupation = (occupation == null ? "" : occupation).replaceAll("'","''");
		if ("0".equalsIgnoreCase(res)|| "DIDN'T SELECTED".equalsIgnoreCase(res))
		{
			try
			{
				customer_id = com.maan.services.util.dataCollection.getMaxCustomerId(loginBra,pids);

				args = new String[27];
				args[0] = customer_id;
				args[1] = "1";
				args[2] = (title.equalsIgnoreCase("select")?"":title);
				args[3] = firstName;
				args[4] = lastName;
				args[5] = "1";
				args[6] = (nationality.equalsIgnoreCase("select")?"":nationality);
				args[7] = com.maan.common.util.OracleDateConversion.ConvertDate(dobDay + "-" + dobMonth + "-"+ dobYear);
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
				args[20] = login_id;
				args[21] = orgName;
				args[22] = customerCode;
				args[23] = city;
				args[24] = clientCustomerId;
				args[25] = arNo;
				args[26] = customerName;

				qry = "insert into personal_info(customer_id,application_id,title,first_name,last_name,amend_id,nationality,dob,gender,telephone,mobile,fax,email,address1,address2,occupation,pobox,country,emirate,status,entry_date,login_id,company_name,missippi_customer_code,city,client_customer_id,cust_ar_no,cust_name) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,(select sysdate from dual),?,?,?,?,?,?,?)";
				sResult = runner.multipleInsertion(qry,args);
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		else 
		{
			try 
			{
				args = new String[27];
				args[0] = "1";
				args[1] = (title.equalsIgnoreCase("select")?"":title);
				args[2] = firstName;
				args[3] = lastName;
				args[4] = "1";
				args[5] = (nationality.equalsIgnoreCase("select")?"":nationality);
				args[6] = com.maan.common.util.OracleDateConversion.ConvertDate(dobDay + "-" + dobMonth + "-"+ dobYear);
				args[7] = gender;
				args[8] = telephone;
				args[9] = mobile;
				args[10] = fax;
				args[11] = email;
				args[12] = address1;
				args[13] = address2;
				args[14] = occupation;
				args[15] = poBox;
				args[16] = country;
				args[17] = emirate;
				args[18] = "Y";
				args[19] = orgName;
				args[20] = customerCode;
				args[21] = city;
				args[22] = clientCustomerId;
				args[23] = arNo;
				args[24] = customerName;
				args[25] = customer_id;
				args[26] = login_id.trim();

				qry = "update personal_info set application_id=?,title=?,first_name=?,last_name=?,amend_id=?,nationality=?,dob=?,gender=?,telephone=?,mobile=?,fax=?,email=?,address1=?,address2=?,occupation=?,pobox=?,country=?,emirate=?,status=?,effective_date=(select sysdate from dual),company_name = ?,missippi_customer_code=?,city=?,client_customer_id=?,cust_ar_no=?,cust_name=? where customer_id=? and login_id in (select a.login_id from login_master a,login_master b where b.login_id=? and a.oa_code=b.oa_code)";
				sResult = runner.multipleUpdation(qry,args);
			} 
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		if ("DIDN'T UPDATE".equalsIgnoreCase(sResult) || "DIDN'T INSERTED".equalsIgnoreCase(sResult)) 
			return sResult;
		else
			return customer_id;
	}

	public java.util.HashMap getExistingCustomerss(String loginIds, String customerName) 
	{
		java.util.HashMap getsTotal = null;
		String[][] ss = new String[0][0];
		String[][] valuess = new String[0][0];
		String args[] = new String[1];
		try 
		{
			args[0] = loginIds;
			String sql = "select usertype,created_by,login_id,oa_code from login_master where login_id=?";
			valuess = runner.multipleSelection(sql,args);
			if ("User".equalsIgnoreCase(valuess[0][0])) 
			{
				args[0] = loginIds;
				sql = "select login_id from login_master where oa_code in (select oa_code from login_master where login_id=?)";
				valuess = runner.multipleSelection(sql,args);
			} 
			else
			{
				args[0] = loginIds;
				sql = "select login_id from login_master where oa_code in (select oa_code from login_master where login_id=?)";
				valuess = runner.multipleSelection(sql,args);
			}

			countNo = 0;
			getsTotal = new java.util.HashMap();
			getsTotal.put("total", "" + valuess.length);
			for (int i = 0; i < valuess.length; i++) 
			{
				ss = new String[0][0];
				sql = "select customer_id,first_name,last_name,email,mobile,login_id from personal_info where login_id='"+valuess[i][0]+ "' and (lower(trim(first_name)) like '"+ (customerName.trim()).toLowerCase()+"%' or  lower(trim(last_name)) like '"+ (customerName.trim()).toLowerCase()+ "%') and application_id=1 order by customer_id";
				ss = runner.multipleSelection(sql);
			
				getsTotal.put("totsub" + i, "" + ss.length);

				for (int j = 0; j < ss.length; j++) 
				{
					getsTotal.put("Id_" + i + "_" + j, ss[j][0].trim());
					getsTotal.put("Fname_" + i + "_" + j, ss[j][1]);
					getsTotal.put("Lname_" + i + "_" + j, ss[j][2]);
					getsTotal.put("email_" + i + "_" + j, ss[j][3]);
					getsTotal.put("mobile_" + i + "_" + j, ss[j][4]);
					getsTotal.put("Login_" + i + "_" + j, ss[j][5]);
					countNo++;
				}
			}

			for (int i = 0; i < Integer.parseInt((String) getsTotal.get("total")); i++) 
			{
				for (int j = 0; j < Integer.parseInt((String) getsTotal.get("totsub" + i)); j++) 
				{
					System.out.println("  IDD   "+ getsTotal.get("Id_" + i + "_" + j));
				}
			}
		}
		catch (Exception e) 
		{
			System.out.println("ERROR in getExistingCustomerss  "+ e.toString());
		}
		return getsTotal;
	}

	public java.util.HashMap getExistingCustomerss(String loginIds) 
	{
		java.util.HashMap getsTotal = null;
		String[][] ss = new String[0][0];
		String[][] valuess = new String[0][0];
		String args[] = new String[1];
		String sql = "";
		try
		{
			args[0] = loginIds;
			sql = "select usertype,created_by,login_id,oa_code from login_master where login_id=?";
			valuess = runner.multipleSelection(sql,args);
		
			if ("User".equalsIgnoreCase(valuess[0][0]))
			{
				args[0] = loginIds;
				sql = "select login_id from login_master where oa_code in (select oa_code from login_master where login_id=?)";
				valuess = runner.multipleSelection(sql,args);
			}
			else
			{
				args[0] = loginIds;
				sql = "select login_id from login_master where oa_code in (select oa_code from login_master where login_id=?)";
				valuess = runner.multipleSelection(sql,args);
			}

			countNo = 0;
			getsTotal = new java.util.HashMap();
			getsTotal.put("total", "" + valuess.length);
			for (int i = 0; i < valuess.length; i++) 
			{
				args[0] = valuess[i][0];
				ss = new String[0][0];
				sql = "select customer_id,first_name,last_name,email,mobile,login_id from personal_info where login_id=? and application_id=1 order by customer_id";
				ss = runner.multipleSelection(sql,args);
			
				getsTotal.put("totsub" + i, "" + ss.length);

				for (int j = 0; j < ss.length; j++) 
				{
					getsTotal.put("Id_" + i + "_" + j, ss[j][0]);
					getsTotal.put("Fname_" + i + "_" + j, ss[j][1]);
					getsTotal.put("Lname_" + i + "_" + j, ss[j][2]);
					getsTotal.put("email_" + i + "_" + j, ss[j][3]);
					getsTotal.put("mobile_" + i + "_" + j, ss[j][4]);
					getsTotal.put("Login_" + i + "_" + j, ss[j][5]);
					countNo++;
				}
			}
		} 
		catch (Exception e) 
		{
			System.out.println("ERROR in getExistingCustomerss  "+ e.toString());
			e.printStackTrace();
		}
		return getsTotal;
	}

	public String[][] getExistingCustomerss123(String loginIds) 
	{
		String[][] ss = null;
		String[][] valuess = new String[0][0];
		String loginAllIds = "";
		try
		{
			String sql = "select usertype,created_by,login_id,oa_code from login_master where login_id='"+loginIds+"'";
			valuess = runner.multipleSelection(sql);
			System.out.println(" User type   " + valuess[0][0]);
			if ("User".equalsIgnoreCase(valuess[0][0])) 
			{
				sql = "select login_id from login_master where oa_code in (select oa_code from login_master where login_id='"+loginIds+ "')";
				valuess = runner.multipleSelection(sql);
			} 
			else
			{
				sql = "select login_id from login_master where oa_code in (select oa_code from login_master where login_id='"+loginIds+ "')";
				valuess = runner.multipleSelection(sql);
			}

			for (int i = 0; i < valuess.length; i++) 
			{	
				loginAllIds = "'" + valuess[i][0] + "'," + loginAllIds;
			}
			loginAllIds = loginAllIds.substring(0, loginAllIds.lastIndexOf(','));

			sql = "select customer_id,first_name,last_name,email,mobile,login_id,COMPANY_NAME from personal_info where login_id in ("+loginAllIds+") and application_id=1 order by customer_id desc";
			ss = runner.multipleSelection(sql);
		}
		catch (Exception e) 
		{
			System.out.println("ERROR in getExistingCustomerss  "+e.toString());
			e.printStackTrace();
		}
		return ss;
	}

	public String[][] getExistingCustomerss123(String loginIds,String customerName, String searchBy)
	{
		String[][] ss = null;
		String[][] valuess = new String[0][0];
		String loginAllIds = "";
		try
		{
			String sql = "select usertype,created_by,login_id,oa_code from login_master where login_id='"+loginIds+"'";
			valuess = runner.multipleSelection(sql);
			System.out.println(" User type   " + valuess[0][0]);
			if ("User".equalsIgnoreCase(valuess[0][0])) 
			{
					sql = "select login_id from login_master where oa_code in (select oa_code from login_master where login_id='"+loginIds+"')";
				valuess = runner.multipleSelection(sql);
			}
			else
			{
				sql = "select login_id from login_master where oa_code in (select oa_code from login_master where login_id='"+loginIds+ "')";
				valuess = runner.multipleSelection(sql);
			}

			for (int i = 0; i < valuess.length; i++) {
				loginAllIds = "'" + valuess[i][0] + "'," + loginAllIds;
			}
			loginAllIds = loginAllIds.substring(0, loginAllIds.lastIndexOf(','));

			if (searchBy.equalsIgnoreCase("FIRST_NAME"))
			{
				sql = "select customer_id,first_name,last_name,email,mobile,login_id,COMPANY_NAME from personal_info where login_id in ("
						+ loginAllIds+ ")  and (lower(trim(first_name)) like '"+ (customerName.trim()).toLowerCase()
						+ "%' or  lower(trim(last_name)) like '"+ (customerName.trim()).toLowerCase()+ "%') and application_id=1 order by customer_id desc";
			}
			else
			{
				sql = "select customer_id,first_name,last_name,email,mobile,login_id,COMPANY_NAME from personal_info where login_id in ("	+ loginAllIds+ ")  and "+ searchBy+ " like '"+ customerName	+ "%' and application_id=1 order by customer_id desc";
			}
			ss = runner.multipleSelection(sql);
		}
		catch (Exception e) 
		{
			System.out.println("ERROR in getExistingCustomerss  "+ e.toString());
			e.printStackTrace();
		}
		return ss;
	}

	public String[][] getCustomerData(String id)
	{
		String[][] cu = new String[0][0];
		String args[] = new String[1];
		String sql = "";
		
		try 
		{
			args[0] = id;
			sql = "select customer_id,first_name,last_name,email,mobile,login_id,COMPANY_NAME from personal_info where customer_id=? and application_id='1'";
			cu = runner.multipleSelection(sql,args);
		}
		catch (Exception e) 
		{
			System.out.println("Exception in >>>>"+ e.toString());
			e.printStackTrace();
		}
		return cu;
	}

	public String[][] getPremium_name(String quote_no)
	{
		String args[] = new String[1];
		String getPremiums[][] = new String[0][0];
		String sql = "";
		try
		{
			args[0] = quote_no;
			sql = "select premium,to_char(inception_date,'dd/MON/yyyy') FROM position_master where quote_no=?";
			getPremiums = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return getPremiums;
	}
	
	public boolean checkingMissippiCode(String proposal, String brokerId) 
	{
//		String value = runner.singleSelection("select count(*) from personal_info where missippi_customer_code in ('"+customerCode+"') and customer_id not in ('"+proposal+"')");
		String value = runner.singleSelection("SELECT COUNT(*) FROM PERSONAL_INFO WHERE MISSIPPI_CUSTOMER_CODE =? AND LOGIN_ID IN (SELECT LOGIN_ID FROM LOGIN_MASTER WHERE OA_CODE=(SELECT OA_CODE FROM LOGIN_MASTER WHERE LOGIN_ID=? AND USERTYPE='Broker')) AND CUSTOMER_ID !=?", new String[]{customerCode,brokerId,proposal});
		try 
		{
			if (Integer.parseInt(value) <= 0)
				return false;
			else
				return true;
		}
		catch (Exception e) 
		{
			return true;
		}
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
				invalidChar="~!@#$%^*_+={}|";
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
				invalidChar="~!@$%^<>*_+={};:?[]|";
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

public static List<customerInfo> getCoreCustomerInfo(String customerName,String branchCode, String mode)
{
	List<customerInfo> list=new ArrayList<customerInfo>();
	String[][] customerList=runner.multipleSelection("SELECT CIMSNO,(CASE WHEN CUSTCODE IS NULL OR CUSTGRP IS NULL OR CUSTTYPE IS NULL OR CUSTCLAS IS NULL THEN '' ELSE (CUSTCODE ||'-'|| CUSTGRP ||'-'|| CUSTTYPE ||'-'||CUSTCLAS) END) ARACC, CUSTTITL,CUSTNAME,CUSTADD1,CUSTADD2,CUSTADD3,CUSTADD4 FROM C_CUST@ECARGO_"+("Test".equalsIgnoreCase(mode)?"DEV":"PROD")+" WHERE CUSTNAME LIKE '%"+customerName.toUpperCase()+"%' AND BRCODE=?",new String[]{branchCode});
	if(customerList!=null && customerList.length>0)
	{
		for (int i = 0; i < customerList.length; i++) {
			customerInfo bean=new customerInfo();
			bean.setCustomerCode(customerList[i][0]==null?"":customerList[i][0]);
			bean.setArNo(customerList[i][1]==null?"":(customerList[i][1]).replaceAll("---", ""));
			bean.setFirstName((customerList[i][2]==null?"":customerList[i][2])+" "+(customerList[i][3]==null?"":customerList[i][3]));
			list.add(bean);
		}
	}
	return list;
}
} // Class