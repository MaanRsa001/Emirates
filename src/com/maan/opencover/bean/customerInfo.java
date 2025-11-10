package com.maan.opencover.bean;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;

import com.iii.premia.maansarovar.services.common.CustomerDataResponseInfo;
import com.maan.common.LogUtil;
import com.maan.common.util.StringUtil;
import com.maan.integration.CustXmlBean;
import com.maan.integration.Customer;
import com.maan.integration.IntegrationService;
import com.maan.services.util.runner;

public class customerInfo
{
	private static final Logger logger = LogUtil.getLogger(customerInfo.class);
	private PrintWriter out=null;
	private String title="";
	private String gender="";
	private String firstName="";
	private String lastName="";
	private String nationality="";
	private String dobDay="";
	private String dobMonth="";
	private String dobYear="";
	private String telephone="";
	private String city="";
	private String cityStatus="";
	private String mobile="";
	private String fax="";
	private String email="";
	private String address1="";
	private String address2="";
	private String occupation="";
	private String emirate="";
	private String country="";
	private String poBox="";
	private String orgName="";
	private String quoteNo="";
	private int countNo=0;
	private String cLoginId="";
	private String brokerId="";
	private String cPassword="";
	private String mode="";
	private String customerCode="";
	private String branch="";
	private String arNo="";
	private String customerName="";
	private String clientCustomerId="";
	private String custVatRegNo="";
	private String custVatRegYn="";

	public String getClientCustomerId() {
		return clientCustomerId;
	}

	public void setClientCustomerId(String clientCustomerId) {
		this.clientCustomerId = clientCustomerId;
	}

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
		public void setBranch(String branch)
		{
			this.branch = branch;
		}
		public String getBranch()
		{
			return branch;
		}

		public void setCLoginId(String  cLoginId)
		{
			this.cLoginId=cLoginId;
		}


		public void setBrokerId(String  brokerId)
		{
			this.brokerId=brokerId;
		}


		public void setMode(String  mode)
		{
			this.mode=mode;
		}

		public void setCPassword(String  cPassword)
		{
			this.cPassword=cPassword;
		}
		public void setOut(PrintWriter out)
		{
			this.out=out;
		}
		public void setTitle(String title)
		{
			this.title=title;
		}
		public void setGender(String gender)
		{
			this.gender=gender;
		}
		public void setFirstName(String firstName)
		{
			this.firstName=firstName;
		}
		public void setLastName(String lastName)
		{
			this.lastName=lastName;
		}
		public void setNationality(String nationality)
		{
			this.nationality=nationality;
		}
		public void setDobDay(String dobDay)
		{
			this.dobDay=dobDay;
		}
		public void setDobMonth(String dobMonth)
		{
			this.dobMonth=dobMonth;
		}
		public void setDobYear(String dobYear)
		{
			this.dobYear=dobYear;
		}
		public void setTelephone(String telephone)
		{
			this.telephone=telephone;
		}
		public void setMobile(String mobile)
		{
			this.mobile=mobile;
		}
		public void setFax(String fax)
		{
			this.fax=fax;
		}
		public void setEmail(String email)
		{
			this.email=email;
		}

		public void setAddress1(String address1)
		{
			this.address1=address1;
		}
		public void setAddress2(String address2)
		{
			this.address2=address2;
		}
		public void setOccupation(String occupation)
		{
			this.occupation=occupation;
		}
		public void setEmirate(String emirate)
		{
			this.emirate=emirate;
		}
		public void setCountry(String country)
		{
			this.country=country;
		}
		public void setPoBox(String poBox)
		{
			this.poBox=poBox;
		}
		

		public void setOrgName(String orgName)
		{
		this.orgName=orgName;
		}
		public void setQuoteNo(String quoteNo)
		{
			this.quoteNo = quoteNo;
		}
		
		public void setCity(String city)
		{
			this.city = city;
		}
		public void setCityStatus(String cityStatus)
		{
			this.cityStatus = cityStatus;
		}
	

		public void setCustomerCode(String customerCode)
		{
			this.customerCode=customerCode;
		}


		
		public String getMode()
		{
			return mode;
		}
		public String getBrokerId()
		{
			return brokerId;
		}

		public String getCLoginId()
		{
			return cLoginId;
		}

		public String getCPassword()
		{
			return cPassword;
		}

		public String getTitle()
		{
			return title;
		}
		public String getGender()
		{
			return gender;
		}
		public String getFirstName()
		{
			return firstName;
		}
		public String getLastName()
		{
			return lastName;
		}
		public String getNationality()
		{
			return nationality;
		}
		public String getDobDay()
		{
			return dobDay;
		}
		public String getDobMonth()
		{
			return dobMonth;
		}
		public String getDobYear()
		{
			return dobYear;
		}
		public String getTelephone()
		{
			return telephone;
		}
		public String getMobile()
		{
			return mobile;
		}
		public String getFax()
		{
			return fax;
		}
		public String getEmail()
		{
			return email;
		}
		public String getAddress1()
		{
			return address1;
		}
		public String getAddress2()
		{
			return address2;
		}
		public String getOccupation()
		{
			return occupation;
		}
		public String getEmirate()
		{
			return emirate;
		}
		public String getCountry()
		{
			return country;
		}
		public String getPoBox()
		{
			return poBox;
		}

		public String getOrgName()
		{
			return orgName;
		}
		public String getQuoteNo()
		{
			 return quoteNo;
		}

		public String getCity()
		{
			 return city;
		}
		public String getCityStatus()
		{
			 return cityStatus;
		}
		
		public String getCustomerCode()
		{
			return customerCode;
		}
		public int getCountNo()
		{
			return countNo;
		}
		
		public String[][] getExistingCustomers(String company_name)
		{
			String args[] = new String[1];
			String qry = ""; 
			String[][] returnVal = new String[0][0];
			try
			{
				args[0] = company_name;
//				qry = "select nvl(first_name,company_name),nvl(last_name,'0'), nvl(email,'0'), nvl(mobile,''), customer_id,nvl(missippi_customer_code,0) from personal_info where login_id in(select l.login_id from broker_company_master bk,login_master l where l.oa_code=bk.agency_code and l.oa_code in(select oa_code from login_master where login_id = ?)) and application_id='1'";
				qry = "SELECT NVL (PI.FIRST_NAME, PI.COMPANY_NAME),NVL (PI.LAST_NAME, '0'),NVL (PI.EMAIL, '0'),NVL (MOBILE, ''),PI.CUSTOMER_ID,NVL (PI.MISSIPPI_CUSTOMER_CODE, 0),nvl(PI.cust_ar_no,'') FROM PERSONAL_INFO PI,BROKER_COMPANY_MASTER BCM,LOGIN_MASTER LM WHERE PI.LOGIN_ID=LM.LOGIN_ID AND LM.OA_CODE=BCM.AGENCY_CODE AND LM.LOGIN_ID=? AND PI.APPLICATION_ID='1'";
				returnVal = runner.multipleSelection(qry,args);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return returnVal;
		}
		/*
public String[][] getCoreCustomers(String brokerId, String searchValue)
		{
			//String args[] = new String[1];
			String qry = ""; 
			String[][] returnVal = new String[0][0];
			try {
				if(StringUtils.isNotBlank(searchValue)) {
					qry = LocalizedTextUtil.findDefaultText("GET_CORE_OPEN_CUSTOMER_LIST", Locale.ENGLISH) + " WHERE UPPER(CUST_NAME) LIKE '%" + searchValue.toUpperCase() +"%' AND CUST_FRZ_FLAG='N' ";
					/*String count = runner.singleSelection("Select count(*) from constant_detail where category_id=129 and remarks=?", new String[]{brokerId});
					if(Integer.valueOf(count)>0) {
						qry += " AND CUST_CLASS LIKE '02%' ";
					}
					else {
						qry += " AND CUST_CLASS IN ('0181','0182','0183','0184','0185','0186') ";
					}
					//qry += " AND CUST_CLASS LIKE '02%' ";
					returnVal = runner.multipleSelection(qry);
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return returnVal;
		}*/
		public String[][] getCoreCustomers(String brokerId, String searchValue)
		{
			//String args[] = new String[1];
			String qry = ""; 
			String[][] returnVal = new String[0][0];
			try {
				if(!"".equalsIgnoreCase(searchValue)&&searchValue!=null){

					IntegrationService intg = new IntegrationService();
					CustomerDataResponseInfo responseInfo=intg.customerDataService(searchValue,"search");
					if(responseInfo.getOutPut()!=null){
						returnVal = new String[1][9];
						CustXmlBean custXmlBean =intg.setCustXmlBean(responseInfo.getOutPut());
						Customer   custBean=custXmlBean.getCustQueryDtls().getCustomer();
						returnVal[0][0]=custBean.getCustName();
						returnVal[0][1]="";
						returnVal[0][2]="";
						returnVal[0][3]="0";
						returnVal[0][4]="";
						returnVal[0][5]=searchValue;
						returnVal[0][6]=custBean.getCustResAddress1();
						returnVal[0][7]=custBean.getCustResAddress2();
						returnVal[0][8]="";
					}

				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return returnVal;
		}

	public String validateFields(String customerId)
	{
		com.maan.opencover.util.dataCollection data = new com.maan.opencover.util.dataCollection();
		String error ="";
		String values = "";

		boolean flag = false;
		boolean mobileFlag = false;
		
		if("".equalsIgnoreCase(title))
			error = error+"<br>*Please Select Title";
		flag = specialCharacterValidation(firstName);

		if(!flag)
		error=error+"<br>*Special Characters are not allowed   "+firstName;

		values = data.validString(firstName,false);
		if("needed".equalsIgnoreCase(values))
			error=error+"<br>*"+runner.getErrormsg("3");

		/*else if("Invalid".equalsIgnoreCase(values))
			error=error+"<br>*"+runner.getErrormsg("6");*/

		/*values=data.validString(lastName,false);
		if("needed".equalsIgnoreCase(values))
			error=error+"<br>*"+runner.getErrormsg("4");
		else if("Invalid".equalsIgnoreCase(values))
			error=error+"<br>*"+runner.getErrormsg("7");

		values=data.validString(gender,false);
		if("needed".equalsIgnoreCase(values))
			error=error+"<br>*"+runner.getErrormsg("2");
		else if("Invalid".equalsIgnoreCase(values))
			error=error+"<br>*"+runner.getErrormsg("2");

		if("select".equalsIgnoreCase(nationality))
			error=error+"<br>*"+runner.getErrormsg("5");
				*/

		/** branch restriction DOB**/

		/*if(!branch.equalsIgnoreCase("001") && !branch.equalsIgnoreCase("002") && !branch.equalsIgnoreCase("003") && !branch.equalsIgnoreCase("010") ) 
		{
			if(!"DD".equalsIgnoreCase(dobDay)|| !"MMM".equalsIgnoreCase(dobMonth) || !"YYYY".equalsIgnoreCase(dobYear))
			{
					values=data.checkDate(dobDay+"/"+dobMonth+"/"+dobYear);
				if("Invalid".equalsIgnoreCase(values))
					error=error+"<br>*"+runner.getErrormsg("8");
			}
		}*/
		/** branch restriction DOB**/


		flag = specialCharacterValidation(telephone); //Telephone Field
		if(!flag)
		error = error+"<br>*Special Characters are not allowed "+telephone;

		mobileFlag = validPhoneMobile(telephone); //Telephone Field
		if(!mobileFlag)
		error = error+"<br>*Mobile number or Phone Number Should be digit "+telephone;


		flag = specialCharacterValidation(mobile); //Mobile Field
		if(!flag)
		error = error+"<br>*Special Characters are not allowed "+mobile;

		mobileFlag = validPhoneMobile(mobile); //Mobile Field
		if(!mobileFlag)
		error = error+"<br>*Mobile number or Phone Number Should be digit "+mobile;

		flag = specialCharacterValidation(fax); //Mobile fax
		if(!flag)
		error = error+"<br>*Special Characters are not allowed "+fax;

		mobileFlag = validPhoneMobile(fax); //Mobile fax
		if(!mobileFlag)
		error = error+"<br>*Fax number Should be digit "+fax;

		mobileFlag = validPhoneMobile(poBox); // poBox
		if(!mobileFlag)
		error = error+"<br>*POBOX number Should be digit "+poBox;

		values = data.validString(telephone,true);

		values = data.emailValidate(email);

		if("Invalid1".equalsIgnoreCase(values))
			error = error+"<br>*"+runner.getErrormsg("20")+" - "+runner.getErrormsg("152");
		else if("Invalid2".equalsIgnoreCase(values))
			error = error+"<br>*"+runner.getErrormsg("20")+" - "+runner.getErrormsg("153");
		else if("Invalid3".equalsIgnoreCase(values))
			error = error+"<br>*"+runner.getErrormsg("20")+" - "+runner.getErrormsg("154");
		else if("Invalid4".equalsIgnoreCase(values))
			error = error+"<br>*"+runner.getErrormsg("20")+" - "+runner.getErrormsg("155");
		else if("Invalid5".equalsIgnoreCase(values))
			error = error+"<br>*"+runner.getErrormsg("20")+" - "+runner.getErrormsg("156");
		else if("Invalid".equalsIgnoreCase(values))
			error = error+"<br>*"+runner.getErrormsg("20");

		if("".equalsIgnoreCase(email))
			error = error+"<br>* Please Enter Email";

		if("select".equalsIgnoreCase(emirate))
		error=error+"<br>*"+runner.getErrormsg("25");

		if("others".equalsIgnoreCase(emirate) && "".equalsIgnoreCase(city.trim()) )
		error=error+"<br>* Please Enter the City ";

		if("others".equalsIgnoreCase(emirate) &&  !("".equalsIgnoreCase(city.trim())) )
		emirate=city;

		if("needed".equalsIgnoreCase(data.validLength(poBox,1)))
		error=error+"<br>*"+runner.getErrormsg("29");

		if(!occupation.equalsIgnoreCase("") && occupation !=null && !occupation.equalsIgnoreCase("null"))
		{
		flag = specialCharacterNumberValidation(occupation); // Occupation
		if(!flag)
		 error = error+"<br>*Occupation field special Characters and numbers are not allowed "+occupation;
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

		/*values=data.validString(customerCode,false);
		if("needed".equalsIgnoreCase(values))
			error=error+"<br>*"+runner.getErrormsg("165");
		else if("Invalid".equalsIgnoreCase(values))	error=error+"<br>*"+runner.getErrormsg("166");*/
		try
		{
			/*if(customerCode.length() > 0)
			{
				flag = true;
				int oldCustCode = 0;
				int newCustCode = 0;
				String temp = "";
				if(customerId.length() > 0 )
				{
					newCustCode = Integer.parseInt(customerCode);
					oldCustCode = getMissippiCustomerCode(customerId);
				}
				else
				{
					newCustCode = 0;
					oldCustCode = 1;
				}

				if( Integer.parseInt(customerCode) >= 0 && oldCustCode != newCustCode)
					flag = customerMissippiCodeValidation(customerCode,branch,brokerId,customerId);
				if(!flag)
					error = error+"<br>* Core customer code is already exist";
				else if(customerCode.equalsIgnoreCase("0"))
					error = error+"<br>* Core customer code not allowed zero";
				else if(isDirectBroker(brokerId,branch) && (arNo==null || "".equals(arNo))){
					error = error+"<br>*"+runner.getErrormsg("474");
				}
			}*/
			/*else if("needed".equalsIgnoreCase(data.validLength(customerCode,1)))
				error = error+"<br>*"+runner.getErrormsg("166");*/
		}
		catch(Exception e)
		{
			error=error+"<br>*"+runner.getErrormsg("166");
		}
		

		if(!mode.equalsIgnoreCase("edit")&&brokerId.equals("RSABROKER123"))
		{
			try
			{
			if(cLoginId.equals("")||cLoginId==null||cLoginId.equalsIgnoreCase("null")||cLoginId.length()<5)
			{
				error=error+"<BR>*Enter Valid LoginId ";
			}
		else if(StringUtil.checkSpecial(cLoginId))
		{
			error=error+"<BR>*Spcial Chars Not Allowed";
		}

		else 
			//if(!mode.equalsIgnoreCase("edit"))
		{
			if(checkCID(cLoginId))
			{
				error=error+"<BR>*This LoginId Id Already Existed";
			}
		}
		
		if(cPassword.equals("")||cPassword==null||cPassword.equalsIgnoreCase("null"))
		{
			error=error+"<BR>*Enter Valid password";
		}

		else if(cPassword.length()<=7)
		{
			error=error+"<BR>* Password should be Min Eight Letters";
		}
		else if(StringUtil.checkSpecial(cPassword))
		{
			error=error+"<BR>*PassWord As Spcial Chars Not Allowed ";
		}
		else if(!validPassword(cPassword))
		{
			error=error+"<BR>*Paasword Should  Contains a LowerCase Letter and Digit";
		}

		}
			catch(Exception e)
			{
				System.out.println("Exception e"+e.toString());
				e.printStackTrace();
			}
		}
		return error;
	}

	public void settingValues(String customer_id)
	{
		try
		{
			String args[] = new String[1];
			String qry="select title,gender,nvl(first_name,company_name),last_name,nationality,to_char(dob,'DD') as dobday,to_char(dob,'MM') as dobmonth,to_char(dob,'YYYY') as dobyear,telephone,mobile,fax,email,address1,address2,occupation,emirate,country,pobox,company_name,nvl(missippi_customer_code,0),city,cust_ar_no,cust_name,VAT_REG_NO,CUST_VAT_YNfrom personal_info where customer_id=?";
			args[0] = customer_id;
			String[][] values = runner.multipleSelection(qry,args);
			title=values[0][0];
			gender=values[0][1];
			firstName=(values[0][2]==null?"":values[0][2]).replaceAll("\"","&#34;");
			lastName=values[0][3];
			nationality=values[0][4];
			if(values[0][5]!=null)
			{
				if(Integer.parseInt(values[0][5])<9)
					values[0][5]=""+Integer.parseInt(values[0][5]);
			}
			if(values[0][6]!=null)
			{
				if(Integer.parseInt(values[0][6])<9)
					values[0][6]=""+Integer.parseInt(values[0][6]);
			}

			dobDay=values[0][5];
			dobMonth=values[0][6];
			dobYear=values[0][7];
			telephone=values[0][8];
			mobile=values[0][9];
			fax=values[0][10];
			email=values[0][11];
			try
			{
			   address1=(values[0][12]==null?"":values[0][12]).replaceAll("\"","&#34;");
			   address2=(values[0][13]==null?"":values[0][13]).replaceAll("\"","&#34;");
			}
			catch(Exception e)
			{
			   address1=values[0][12];
			   address2=values[0][13];
			}
			occupation=(values[0][14]==null?"":values[0][14]).replaceAll("\"","&#34;");
			emirate=values[0][15];
			country=values[0][16];
			poBox=values[0][17];
			customerCode=values[0][19];
			city=values[0][20];
			if(values[0][18]!=null)
			{
				orgName = values[0][18];
			}
			/* Sep27 if("M/S".equalsIgnoreCase(title))
				firstName=orgName; sep27*/
			firstName=(firstName==null?"":firstName).replaceAll("\"","&#34;");
			arNo = values[0][21];
			customerName = values[0][22];
			custVatRegNo=values[0][23];
			custVatRegYn=values[0][24];
		}
		catch(Exception e)
		{
			System.out.println("  Value are not setting  "+e.toString());
			e.printStackTrace();
		}
	}
	
	public void settingCoreCustValues(String customer_id)
	{
		try
		{
			String args[] = new String[1];
			//String qry=LocalizedTextUtil.findDefaultText("GET_CORE_OPEN_CUSTOMER", Locale.ENGLISH);
			args[0] = customer_id;
			String[][] values = gettingCoreCustValues(customer_id);
			title=values[0][0]==null?"":values[0][0];
			gender=values[0][1]==null?"":values[0][1];
			firstName=(values[0][2]==null?"":values[0][2]).replaceAll("\"","&#34;");
			lastName=values[0][3]==null?"":values[0][3];
			nationality=values[0][4]==null?"":values[0][4];
			if(values[0][5]!=null)
			{
				if(Integer.parseInt(values[0][5])<9)
					values[0][5]=""+Integer.parseInt(values[0][5]);
			}
			if(values[0][6]!=null)
			{
				if(Integer.parseInt(values[0][6])<9)
					values[0][6]=""+Integer.parseInt(values[0][6]);
			}

			dobDay=values[0][5]==null?"":values[0][5];
			dobMonth=values[0][6]==null?"":values[0][6];
			dobYear=values[0][7]==null?"":values[0][7];
			telephone=values[0][8]==null?"":values[0][8];
			mobile=values[0][9]==null?"":values[0][9];
			fax=values[0][10]==null?"":values[0][10];
			email=values[0][11]==null?"":values[0][11];
			try
			{
			   address1=(values[0][12]==null?"":values[0][12]).replaceAll("\"","&#34;");
			   address2=(values[0][13]==null?"":values[0][13]).replaceAll("\"","&#34;");
			}
			catch(Exception e)
			{
			   address1=values[0][12]==null?"":values[0][12];
			   address2=values[0][13]==null?"":values[0][13];
			}
			occupation=(values[0][14]==null?"":values[0][14]).replaceAll("\"","&#34;");
			emirate=values[0][15]==null?"":values[0][15];
			country=values[0][16]==null?"":values[0][16];
			poBox=values[0][17]==null?"":values[0][17];
			customerCode=values[0][19]==null?"":values[0][19];
			city=values[0][20]==null?"":values[0][20];
			if(values[0][18]!=null)
			{
				orgName = values[0][18]==null?"":values[0][18];
			}
			/* Sep27 if("M/S".equalsIgnoreCase(title))
				firstName=orgName; sep27*/
			firstName=(firstName==null?"":firstName).replaceAll("\"","&#34;");
			arNo = values[0][21]==null?"":values[0][21];
			customerName = values[0][22]==null?"":values[0][22];
			custVatRegNo=values[0][23]==null?"":values[0][23];
			setCustVatRegYn(values[0][24]==null?"":values[0][24]);
		}
		catch(Exception e)
		{
			System.out.println("  Value are not setting  "+e.toString());
			e.printStackTrace();
		}
	}
		
	private String[][] gettingCoreCustValues(String customerId) {
		//String args[] = new String[1];
		String qry = ""; 
		String[][] returnVal = new String[0][0];
		try {
			if(!"".equalsIgnoreCase(customerId)&&customerId!=null){

				IntegrationService intg = new IntegrationService();
				CustomerDataResponseInfo responseInfo=intg.customerDataService(customerId,"search");
				if(responseInfo.getOutPut()!=null){
					returnVal = new String[1][25];
					CustXmlBean custXmlBean =intg.setCustXmlBean(responseInfo.getOutPut());
					Customer   custBean=custXmlBean.getCustQueryDtls().getCustomer();
					returnVal[0][0]="";
					returnVal[0][1]="";
					returnVal[0][2]=custBean.getCustName();
					returnVal[0][3]="0";
					returnVal[0][4]="";
					returnVal[0][5]=null;
					returnVal[0][6]=null;
					returnVal[0][7]="";
					returnVal[0][8]="";
					returnVal[0][9]="";
					returnVal[0][10]="";
					returnVal[0][11]="";
					
					returnVal[0][12]=custBean.getCustResAddress1();
					returnVal[0][13]=custBean.getCustResAddress2();
					returnVal[0][14]="";
					returnVal[0][15]="";
					returnVal[0][16]="";
					returnVal[0][17]="";
					returnVal[0][18]="";
					returnVal[0][19]=custBean.getCustCode();;
					returnVal[0][20]="";
					returnVal[0][21]="";
					returnVal[0][22]="";
					returnVal[0][23]=custBean.getCustVatRegNo();
					returnVal[0][24]=custBean.getCustVatApplYn();
				}

			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return returnVal;
	}

	public String deleteTable(String quot)
	{
		try
		{
			String args[] = new String[1];
			quoteNo = quot;
			args[0] = quoteNo;
			String qry = "update position_master set status = 'D', effective_date = (select sysdate from dual) where quote_no=? ";
			qry = runner.multipleUpdation(qry,args);
		}
		catch(Exception exm)
		{
			System.out.println("Error in deleting"+exm);
		}
		return quoteNo;
	}

	public String storedValues(String customer_id,String login_id,String loginBra)
	{
		String sResult="";
		String customer=null;
		String qry= ""; 
		String args[]= new String[0];
		args = new String[1];
		args[0] = customer_id;
		qry = "select count(*) from personal_info where customer_id=?";
		qry = runner.singleSelection(qry,args);
		
		firstName = firstName.replaceAll("'","''");
		email =(email==null?"":email).replaceAll("'","''");
		address1 =(address1==null?"":address1).replaceAll("'","''");
		address2 =(address2==null?"":address2).replaceAll("'","''");
		occupation =(occupation==null?"":occupation).replaceAll("'","''");
		if("0".equalsIgnoreCase(qry) || "DIDN'T SELECTED".equalsIgnoreCase(qry))
		{
			try
			{
				//customer_id=com.maan.opencover.util.dataCollection.getMaxCustomerId(loginBra);
				qry = "SELECT CUSTOMER_NUMBER_SEQ.NEXTVAL FROM DUAL";
				customer_id=runner.singleSelection(qry);
				firstName = firstName.replaceAll("'","''");
//				qry="insert into personal_info(customer_id,application_id,title,first_name,last_name,amend_id,nationality,dob,gender,telephone,mobile,fax,email,address1,address2,occupation,pobox,country,emirate,status,entry_date,login_id,company_name,missippi_customer_code,city) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,(select sysdate from dual),?,?,?,?)";
				qry="insert into personal_info(customer_id,application_id,title,first_name,last_name,amend_id,nationality,dob,gender,telephone,mobile,fax,email,address1,address2,occupation,pobox,country,emirate,status,entry_date,login_id,company_name,missippi_customer_code,city,client_customer_id,cust_ar_no,cust_name,VAT_REG_NO,CUST_VAT_YN) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,(select sysdate from dual),?,?,?,?,?,?,?,?,?)";
				args = new String[29];
				args[0] = customer_id;
				args[1] = "1";
				args[2] = title;
				args[3] = firstName;
				args[4] = lastName;
				args[5] = "1";
				args[6] = nationality;
				args[7] = com.maan.common.util.OracleDateConversion.ConvertDate(dobDay+"-"+dobMonth+"-"+dobYear);
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
				args[27] = custVatRegNo;
				args[28] = "1".equalsIgnoreCase(custVatRegYn)?"Y":"N";
				sResult = runner.multipleInsertion(qry,args);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			try
			{
				qry = "update personal_info set application_id=?,title=?,first_name=?,last_name=?,amend_id=?,nationality=?,dob=?,gender=?,telephone=?,mobile=?,fax=?,email=?,address1=?,address2=?,occupation=?,pobox=?,country=?,emirate=?,status=?,effective_date=(select sysdate from dual),company_name = ?,missippi_customer_code=?,city=?,client_customer_id=?,cust_ar_no=?,cust_name=?  where customer_id=? ";
				
				args = new String[26];
				args[0] = "1";
				args[1] = title;
				args[2] = firstName;
				args[3] = lastName;
				args[4] = "1";
				args[5] = nationality;
				args[6] = com.maan.common.util.OracleDateConversion.ConvertDate(dobDay+"-"+dobMonth+"-"+dobYear);
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

				sResult = runner.multipleUpdation(qry,args);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		if("DIDN'T UPDATE".equalsIgnoreCase(sResult) ||"DIDN'T INSERTED".equalsIgnoreCase(sResult) )
			return sResult;
		else
			return customer_id;
	}

	public static String replace(String line, String oldString, String newString)
	{
		if(line != null && oldString != null && newString != null) 
		{
			 int index = 0;
			 while ((index = line.indexOf(oldString, index)) >= 0) 
			 {
				line = line.substring(0, index) +    newString +    line.substring(index + oldString.length());
				 index += newString.length();
			 }
		}
		return line;
	}

	public java.util.HashMap getExistingCustomerss(String loginIds,String customerName)
	{
		java.util.HashMap getsTotal=null;String[][] ss=null;
		String[][] valuess=new String[0][0];
		String args[] = new String[0];
		String sql = "";
		try
		{
			args = new String[1];
			args[0] = loginIds;
			sql="select usertype,created_by,login_id,oa_code from login_master where login_id=?";
			valuess = runner.multipleSelection(sql,args);
			if("User".equalsIgnoreCase(valuess[0][0]))
			{
				args = new String[3];
				args[0] = valuess[0][1];
				args[1] = valuess[0][2];
				args[2] = valuess[0][1];
				sql="select login_id from login_master where created_by=? or created_by=? or login_id=?";
				valuess = runner.multipleSelection(sql,args);
			}
			else
			{
				args = new String[3];
				args[0] = valuess[0][3];
				args[1] = loginIds;
				args[2] = loginIds;
				 sql="select login_id from login_master where oa_code=? or created_by=? or login_id=?";
				valuess = runner.multipleSelection(sql,args);
			}

			countNo=0;

			getsTotal=new java.util.HashMap();
			getsTotal.put("total",""+valuess.length);
			for(int i=0;i<valuess.length;i++)
			{
				ss=new String[0][0];
				sql="select customer_id,first_name,last_name,email,mobile,login_id from personal_info where login_id='"+valuess[i][0]+"'  and (lower(trim(first_name)) like '"+(customerName.trim()).toLowerCase()+"%' or  lower(trim(last_name)) like '"+(customerName.trim()).toLowerCase()+"%') and application_id=1 order by customer_id";
				ss = runner.multipleSelection(sql);
				getsTotal.put("totsub"+i,""+ss.length);

				for(int j=0;j<ss.length;j++)
				{
					getsTotal.put("Id_"+i+"_"+j,ss[j][0].trim());
					getsTotal.put("Fname_"+i+"_"+j,ss[j][1]);
					getsTotal.put("Lname_"+i+"_"+j,ss[j][2]);
					getsTotal.put("email_"+i+"_"+j,ss[j][3]);
					getsTotal.put("mobile_"+i+"_"+j,ss[j][4]);
					getsTotal.put("Login_"+i+"_"+j,ss[j][5]);
					countNo++;
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("ERROR in getExistingCustomerss  "+e.toString());
			e.printStackTrace();
		}
		return getsTotal;
	}



	public java.util.HashMap getExistingCustomerss(String loginIds)
	{
		java.util.HashMap getsTotal=null;String[][] ss=null;
		String[][] valuess=new String[0][0];
		String args[] = new String[0];
		String sql = "";
		try
		{
			args = new String[1];
			args[0] = loginIds;
			sql="select usertype,created_by,login_id,oa_code from login_master where login_id=?";
			valuess=runner.multipleSelection(sql,args);
		
			if("User".equalsIgnoreCase(valuess[0][0]))
			{
				args = new String[3];
				args[0] = valuess[0][1];
				args[1] = valuess[0][2];
				args[2] = valuess[0][1];
				sql="select login_id from login_master where created_by=? or created_by=? or login_id=?";
				valuess = runner.multipleSelection(sql,args);
			}
			else
			{
				args = new String[3];
				args[0] = valuess[0][3];
				args[1] = loginIds;
				args[2] = loginIds;
				 sql="select login_id from login_master where oa_code=? or created_by=? or login_id=?";
				valuess = runner.multipleSelection(sql,args);
			}

			countNo=0;
			getsTotal=new java.util.HashMap();
			getsTotal.put("total",""+valuess.length);
			for(int i=0;i<valuess.length;i++)
			{
				ss=new String[0][0];
				args = new String[1];
				args[0] = valuess[i][0];
				sql="select customer_id,first_name,last_name,email,mobile,login_id from personal_info where login_id=? and application_id=1 order by customer_id";
				ss = runner.multipleSelection(sql,args);
				getsTotal.put("totsub"+i,""+ss.length);

				for(int j=0;j<ss.length;j++)
				{
					getsTotal.put("Id_"+i+"_"+j,ss[j][0]);
					getsTotal.put("Fname_"+i+"_"+j,ss[j][1]);
					getsTotal.put("Lname_"+i+"_"+j,ss[j][2]);
					getsTotal.put("email_"+i+"_"+j,ss[j][3]);
					getsTotal.put("mobile_"+i+"_"+j,ss[j][4]);
					getsTotal.put("Login_"+i+"_"+j,ss[j][5]);
					countNo++;
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("ERROR in getExistingCustomerss  "+e.toString());
			e.printStackTrace();
		}
		return getsTotal;
	}

	public String[][] getExistingCustomerss123(String loginIds)
	{
		java.util.HashMap getsTotal = null;String[][] ss = null;
		String[][] valuess = new String[0][0];
		String loginAllIds="";
		String args[] = new String[0];
		String sql = "";
		try
		{
			sql = "select usertype,created_by,login_id,oa_code from login_master where login_id=?";
			args = new String[1];
			args[0] = loginIds;
			valuess=runner.multipleSelection(sql,args);
			if("User".equalsIgnoreCase(valuess[0][0]))
			{
				args = new String[3];
				args[0] = valuess[0][1];
				args[1] = valuess[0][2];
				args[2] = valuess[0][1];
				sql="select login_id from login_master where created_by=? or created_by=? or login_id=?";
				valuess = runner.multipleSelection(sql,args);
			}
			else
			{
				args = new String[3];
				args[0] = valuess[0][3];
				args[1] = loginIds;
				args[2] = loginIds;
				sql="select login_id from login_master where oa_code=? or created_by=? or login_id=?";
				valuess = runner.multipleSelection(sql,args);
			}
			for(int i=0;i<valuess.length;i++)
			{
				loginAllIds="'"+valuess[i][0]+"',"+loginAllIds;
			}
			loginAllIds=loginAllIds.substring(0,loginAllIds.lastIndexOf(','));

			sql="select customer_id,first_name,last_name,email,mobile,login_id,COMPANY_NAME from personal_info where login_id in ("+loginAllIds+") and application_id=1 order by customer_id desc";
			ss = runner.multipleSelection(sql);
		}
		catch(Exception e)
		{
			System.out.println("ERROR in getExistingCustomerss  "+e.toString());
			e.printStackTrace();
		}
		return ss;
	}



	public String[][] getExistingCustomerss123(String loginIds,String customerName,String searchBy)
	{
		java.util.HashMap getsTotal=null;String[][] ss=null;
		String[][] valuess=new String[0][0];
		String loginAllIds="";
		String args[] = new String[0];
		String sql="";
		try
		{
			args = new String[1];
			args[0] = loginIds;
			sql = "select usertype,created_by,login_id,oa_code from login_master where login_id=?";
			valuess = runner.multipleSelection(sql,args);
			
			if("User".equalsIgnoreCase(valuess[0][0]))
			{
				args = new String[3];
				args[0] = valuess[0][1];
				args[1] = valuess[0][2];
				args[2] = valuess[0][1];
				sql="select login_id from login_master where created_by=? or created_by=? or login_id=?";
				valuess = runner.multipleSelection(sql,args);
			}
			else
			{
				args = new String[3];
				args[0] = valuess[0][3];
				args[1] = loginIds;
				args[2] = loginIds;
				 sql="select login_id from login_master where oa_code=? or created_by=? or login_id=?";
				valuess = runner.multipleSelection(sql,args);
			}

			for(int i=0;i<valuess.length;i++)
			{
				loginAllIds = "'"+valuess[i][0]+"',"+loginAllIds;
			}
			loginAllIds = loginAllIds.substring(0,loginAllIds.lastIndexOf(','));

			if(searchBy.equalsIgnoreCase("FIRST_NAME"))
			{
				sql="select customer_id,first_name,last_name,email,mobile,login_id,COMPANY_NAME from personal_info where login_id in ("+loginAllIds+")  and (lower(trim(first_name)) like '"+(customerName.trim()).toLowerCase()+"%' or  lower(trim(last_name)) like '"+(customerName.trim()).toLowerCase()+"%') and application_id=1 order by customer_id desc";
			}
			else
			{
				sql="select customer_id,first_name,last_name,email,mobile,login_id,COMPANY_NAME from personal_info where login_id in ("+loginAllIds+")  and "+searchBy+" like '"+customerName+"%' and application_id=1 order by customer_id desc";
			}
			ss = runner.multipleSelection(sql);
		}
		catch(Exception e)
		{
			System.out.println("ERROR in getExistingCustomerss  "+e.toString());
			e.printStackTrace();
		}
		return ss;
	}

	public String[][] getCustomerData(String id)
	{
		String args[] = new String[1];
		String[][] cu=new String[0][0];
		String sql="";
		try
		{
			sql = "select customer_id,first_name,last_name,email,mobile,login_id,COMPANY_NAME from personal_info where customer_id=? and application_id='1'";
			args[0]= id;
			 cu=runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("Exception in >>>"+e.toString());
			e.printStackTrace();
		}
		return cu;
	}

	
	public boolean checkCID(String loginId)
	{
		String count="0";
		boolean check=false;
		String sql = "";
		String args[] = new String[1];
		try
		{
			sql = "select count(*) from login_master where login_id=?";
			args[0] = loginId;
			count = runner.singleSelection(sql,args);
			if(!count.equals("0"))
				check=true;
			else
				check=false;
		}
		catch(Exception e)
		{
			System.out.println("exception in validating login id"+e.toString());
			e.printStackTrace();
		}
		return check;
	}

	public boolean validPassword(String value)
	{
		boolean b=false;
		String validChar="";
		int p=0;
		int j=0;
		validChar="abcdefghijklmnopqrstuvwxyz";
		for(int i=0;i<value.length();i++)
		{
			if(validChar.indexOf(value.charAt(i))!= -1)
			{
				p++;
				break;
			}
		}
		validChar="1234567890";
		for(int i=0;i<value.length();i++)
		{
			if(validChar.indexOf(value.charAt(i))!= -1)
			{
				j++;
				break;
			}
		}
		if(p==1 && j==1)
			b=true;
		else
			b=false;
		return b;	
	}

	public boolean ExistedCusId(String bid)
	{
		String args[] = new String[1];
		boolean b=false;
		String qry= ""; 
		try
		{
			qry = "select count(*) from login_master where oa_code=(select agency_Code from login_master where login_id=?) and usertype='Customer'";
			args[0] = bid;
			qry=runner.singleSelection(qry,args);
			if(qry.equals("0"))
				b=false;
			else
				b=true;
		}
		catch(Exception e)
		{
			System.out.println("Exception in getting count direct cutomers"+e.toString());
			e.printStackTrace();
		}
		return b;
	}

	public boolean checkingMissippiCode(String proposal)
	{
		String value = "";
		
		try
		{
			value = runner.singleSelection("select count(*) from personal_info where missippi_customer_code in ('"+customerCode+"') and customer_id not in ('"+proposal+"')");
			if(Integer.parseInt(value)<=0)
				return false;
			else
				return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();			
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
			System.out.println("Valid Amount in OfficeShiledBean.java1 "+value);
			return flag;
		}
		System.out.println("Valid Amount in OSB  "+ flag);
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
				invalidChar="~!@$%^<>&*_+={};?[]|";
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
	
	public boolean customerMissippiCodeValidation(String custCode,String branch, String brokerId, String customerId)
	{
		String value = "";
		String sql = "";
		boolean flag = false;
		try
		{	
			if(!custCode.equalsIgnoreCase("0"))
			{
//				sql = "select count(*) from broker_company_master where branch_code='"+branch+"' and agency_code in (select oa_code from login_master where login_id in (select login_id from personal_info where missippi_customer_code ='"+custCode+"'))";
				//sql = "select count(*) from personal_info where missippi_customer_code ='"+custCode+"'" ;
				sql = "SELECT COUNT(*) FROM PERSONAL_INFO WHERE MISSIPPI_CUSTOMER_CODE =? AND LOGIN_ID IN (SELECT LOGIN_ID FROM LOGIN_MASTER WHERE OA_CODE=(SELECT OA_CODE FROM LOGIN_MASTER WHERE LOGIN_ID=? AND USERTYPE='Broker')) AND CUSTOMER_ID !=?" ;
				value = runner.singleSelection(sql, new String[]{custCode, brokerId, customerId});
				value = value == null ? "0" : value;
							
				System.out.println("value ...."+value);
				if(value.length() > 0 && !value.equalsIgnoreCase("null"))
				{
					if(Integer.parseInt(value) == 0 )
						flag = true;
					else
						flag = false;
				}
			}
			else
				flag = false;
		}
		catch(Exception e)
		{
			e.printStackTrace();			
			return flag;
		}
		return flag;
	}
	
	public int getMissippiCustomerCode(String customerId)
	{
		String result = "";
		String sql = "";
		int res = 0;
		try
		{
			sql = "select nvl(missippi_customer_code,0) from personal_info where customer_id='"+customerId+"'";
			result = runner.singleSelection(sql);
			if(result.length() > 0)
			{
				res = Integer.parseInt(result);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return res;
	}

	public boolean validAmount(String value)
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
			System.out.println("Valid Amount in OfficeShiledBean.java1 "+value);
			return flag;
		}
		System.out.println("Valid Amount in OSB  "+ flag);
		return flag;
	}
	public boolean isDirectBroker(String loginId, String branchCode)
	{
		String result="";
		boolean isDirect=false;
		logger.info("isDirectBroker - Enter || brachCode: "+branchCode+" loginId: "+loginId);
		result=runner.singleSelection("SELECT COUNT(1) FROM CONSTANT_DETAIL WHERE BRANCH_CODE='"+branchCode+"' AND CATEGORY_ID='129' AND REMARKS='"+loginId+"'");
		if(!"0".equalsIgnoreCase(result)){
			isDirect=true;
		}
		logger.info("isDirectBroker - Enter || isDirect: "+isDirect);
		return 	isDirect;
	}
	public String[][] getOnlineCustomers(String company_name,String customerId)
	{
		String args[] = new String[2];
		String qry = ""; 
		String[][] returnVal = new String[0][0];
		try
		{
			args[0] = company_name;
			args[1] = customerId;
//			qry = "select nvl(first_name,company_name),nvl(last_name,'0'), nvl(email,'0'), nvl(mobile,''), customer_id,nvl(missippi_customer_code,0) from personal_info where login_id in(select l.login_id from broker_company_master bk,login_master l where l.oa_code=bk.agency_code and l.oa_code in(select oa_code from login_master where login_id = ?)) and application_id='1'";
			qry = "SELECT NVL (PI.FIRST_NAME, PI.COMPANY_NAME),NVL (PI.LAST_NAME, '0'),NVL (PI.EMAIL, '0'),NVL (MOBILE, ''),PI.CUSTOMER_ID,NVL (PI.MISSIPPI_CUSTOMER_CODE, 0) MISSIPPI_CUSTOMER_CODE,nvl(PI.cust_ar_no,'') FROM PERSONAL_INFO PI,BROKER_COMPANY_MASTER BCM,LOGIN_MASTER LM WHERE PI.LOGIN_ID=LM.LOGIN_ID AND LM.OA_CODE=BCM.AGENCY_CODE AND LM.LOGIN_ID=? AND PI.CUSTOMER_ID=? AND PI.APPLICATION_ID='1'";
			returnVal = runner.multipleSelection(qry,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return returnVal;
	}

	public void setCustVatRegNo(String custVatRegNo) {
		this.custVatRegNo = custVatRegNo;
	}

	public String getCustVatRegNo() {
		return custVatRegNo;
	}

	public void setCustVatRegYn(String custVatRegYn) {
		this.custVatRegYn = custVatRegYn;
	}

	public String getCustVatRegYn() {
		return custVatRegYn;
	}
} // Class