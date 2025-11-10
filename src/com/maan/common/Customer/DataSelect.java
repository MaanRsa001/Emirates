package com.maan.common.Customer;

import java.util.Calendar;
import java.util.Locale;

import org.apache.logging.log4j.Logger;

import com.maan.Home.DataManipualtion.HomeValidationBean;
import com.maan.common.LogUtil;
import com.maan.common.exception.BaseException;
import com.maan.services.util.ValidationFormat;
import com.maan.services.util.runner;

public class DataSelect
{
	private static final Logger logger = LogUtil.getLogger(DataSelect.class);
	private transient final static long SECOND_FACTOR = 1000;
	private transient final static long MINUTE_FACTOR = SECOND_FACTOR * 60;
	private transient final static long HOUR_FACTOR	  = MINUTE_FACTOR * 60;
	private transient final static long DAY_FACTOR	  = HOUR_FACTOR * 24;
	private transient final static long YEAR_FACTOR	  = DAY_FACTOR * 365;
	private transient final static String SELECT  	  = "select";
	private transient final static String MMM         = "MMM";
	private transient final static String DD      	  = "DD";
	private transient final static String YYYY        = "YYYY";
	private transient final static String ENTER       = "- Enter";
	private transient final static String EXIT        = "- Exit";
	private transient final static String NIL		  = "NIL";
	private transient final int    countNo     =	0;

	private String	LoginId				=	"";
    private String curCus 				= 	"";
	private transient String source		=	"";
    private String otherSource			=	"";
    private String title				=	"";
	private String gender				=	"";
	private String firstName			=	"";
	private String lastName				=	"";
	private String nationality			=	"";
	private String dobDay				=	"";
	private String dobMonth				=	"";
	private String dobYear				=	"";
	private String telephone			=	"";
	private String mobile				=	"";
	private String fax					=	"";
	private String email				=	"";
	private String address1				=	"";
	private String address2				=	"";
	private String occupation			=	"";
	private String emirate				=	"";
	private String country				=	"";
	private String poBox				=	"";
    private String orgName				=	"";
    private String quoteNo				=	"";

	public void setLoginId(final String LoginId){
        this.LoginId = LoginId;
    }
    public String getLoginId(){
        return LoginId;
    }
	public void setCurCus(final String curCus){
        this.curCus = curCus;
    }
    public String getCurCus(){
        return this.curCus;
    }
	public String getSource(){
        return this.source;
    }
	public void setOtherSource(final String otherSource){
        this.otherSource = otherSource;
    }
    public String getOtherSource(){
        return this.otherSource;
    }
    public void setTitle(final String title){
    	this.title=title;
    }
    public void setGender(final String gender){
    	this.gender=gender;
    }
    public void setFirstName(final String firstName){
    	this.firstName=firstName;
    }
    public void setLastName(final String lastName){
    	this.lastName=lastName;
    }
    public void setNationality(final String nationality){
    	this.nationality=nationality;
    }
    public void setDobDay(final String dobDay){
    	this.dobDay=dobDay;
    }
    public void setDobMonth(final String dobMonth){
    	this.dobMonth=dobMonth;
    }
    public void setDobYear(final String dobYear){
    	this.dobYear=dobYear;
    }
    public void setTelephone(final String telephone){
    	this.telephone=telephone;
    }
    public void setMobile(final String mobile){
    	this.mobile=mobile;
    }
    public void setFax(final String fax){
    	this.fax=fax;
    }
    public void setEmail(final String email){
    	this.email=email;
    }
    public void setAddress1(final String address1){
    	this.address1=address1;
    }
	public void setAddress2(final String address2){
		this.address2=address2;
	}
	public void setOccupation(final String occupation){
		this.occupation=occupation;
	}
	public void setEmirate(final String emirate){
		this.emirate=emirate;
	}
	public void setCountry(final String country){
		this.country=country;
	}
	public void setPoBox(final String poBox){
		this.poBox=poBox;
	}
	public void setOrgName(final String orgName){
		this.orgName=orgName;
	}
	public void setQuoteNo(final String quoteNo){
		this.quoteNo = quoteNo;
	}

	public String getTitle(){
		return this.title;
	}
	public String getGender(){
		return this.gender;
	}
	public String getFirstName(){
		return this.firstName;
	}
	public String getLastName(){
		return this.lastName;
	}
	public String getNationality(){
		return this.nationality;
	}
	public String getDobDay(){
		return this.dobDay;
	}
	public String getDobMonth(){
		return this.dobMonth;
	}
	public String getDobYear(){
		return this.dobYear;
	}
	public String getTelephone(){
		return this.telephone;
	}
	public String getMobile(){
		return this.mobile;
	}
	public String getFax(){
		return this.fax;
	}
	public String getEmail(){
		return this.email;
	}
	public String getAddress1(){
		return address1;
	}
	public String getAddress2(){
		return this.address2;
	}
	public String getOccupation(){
		return this.occupation;
	}
	public String getEmirate(){
		return this.emirate;
	}
	public String getCountry(){
		return this.country;
	}
	public String getPoBox(){
		return this.poBox;
	}
	public String getOrgName(){
		return this.orgName;
	}
	public String getQuoteNo(){
		return this.quoteNo;
	}
	public int getCountNo(){
		return this.countNo;
	}

    public String[][] getPremiumName(final String quote_no) throws BaseException
	{
    	logger.info("getPremiumName method()");
		logger.debug(ENTER);

		final String args[] = {quote_no};
		final String[][] getPremiums=runner.multipleSelection("select premium,to_char(inception_date,'dd/MON/yyyy') FROM home_position_master where quote_no=?",args);

		logger.debug(EXIT);
		

		return getPremiums;
	}

	public String[][] titleCollection() throws BaseException
	{
		logger.info("titleCollection method()");
		logger.debug(ENTER);

		final String[][] titles=runner.multipleSelection("select title_name from title_master");

		logger.debug(EXIT);
		

		return titles;
	}

	public String[][] nationalityCollection() throws BaseException
	{
		logger.info("nationalityCollection method()");
		logger.debug(ENTER);

		final String[][] nationality_title=runner.multipleSelection("select NATIONALITY_NAME from country_master order by NATIONALITY_NAME");

		logger.debug(EXIT);
		

		return nationality_title;
	}

	public String[][] countryCollection() throws BaseException
	{
		logger.info("countryCollection method()");
		logger.debug(ENTER);

		final String[][] country_title=runner.multipleSelection("select country_name from country_master");

		logger.debug(EXIT);
		

		return country_title;
	}

	public String[][] emirateCollection() throws BaseException
	{
		logger.info("emirateCollection method()");
		logger.debug(ENTER);

		final String[][] emirate_title=runner.multipleSelection("select city_name from city_master where country_id=1");

		logger.debug(EXIT);
		

		return emirate_title;
	}

	public String storedValues(String customer_id,final String login_id,final String loginBranch,final String pids) throws BaseException
	{
		logger.info("storedValues method(string,string,string,string)");
		logger.debug(ENTER);

		String qry="0";
		if(customer_id.length() > 0){
			qry = "select count(*) from personal_info where customer_id=?";
			final String args[] = {customer_id};
			qry=runner.singleSelection(qry,args);
		}
		if("M/S".equalsIgnoreCase(title)){
			orgName=firstName;
			firstName="";
		}
		if("others".equalsIgnoreCase(source) && otherSource.length()>0){
			source = otherSource;
		}
		if("0".equalsIgnoreCase(qry) || "DIDN'T SELECTED".equalsIgnoreCase(qry)){
			customer_id= com.maan.Home.DataManipualtion.DataManipualtion.getMaxCustomerId(loginBranch,pids);
			qry="insert into personal_info(customer_id,application_id,title,first_name,last_name,amend_id,nationality,dob,gender,telephone,mobile,fax,email,address1,address2,occupation,pobox,country,emirate,status,entry_date,login_id, company_name,customer_source)values(?,'1',?,?,?,'1',?,?,?,?,?,?,?,?,?,?,?,?,?,'Y',(select sysdate from dual),?,?,?)";
			final String args[] = {customer_id,(SELECT.equalsIgnoreCase(title)?"":title),firstName,lastName,(SELECT.equalsIgnoreCase(nationality)?"":nationality),com.maan.common.util.OracleDateConversion.ConvertDate(dobDay+"-"+dobMonth+"-"+dobYear),gender,telephone,mobile,fax,email,address1,address2,("0".equalsIgnoreCase(occupation)?"":occupation),poBox,country,(SELECT.equalsIgnoreCase(emirate)?"":emirate),login_id,orgName,source};
			runner.multipleInsertion(qry,args);
		}
		else{
			qry="update personal_info set application_id='1',title=?,first_name=?,last_name=?,amend_id='1',nationality=?,dob=?,gender=?,telephone=?,mobile=?,fax=?,email=?,address1=?,address2=?,occupation=?,pobox=?,country=?,emirate=?,status='Y',effective_date=(select sysdate from dual),company_name=?,customer_source=? where customer_id=?";
			final String args[] = {(SELECT.equalsIgnoreCase(title)?"":title),firstName,lastName,(SELECT.equalsIgnoreCase(nationality)?"":nationality),com.maan.common.util.OracleDateConversion.ConvertDate(dobDay+"-"+dobMonth+"-"+dobYear),gender,telephone,mobile,fax,email, address1,address2,("0".equalsIgnoreCase(occupation)?"":occupation),poBox, country,(SELECT.equalsIgnoreCase(emirate)?"":emirate),orgName,source,customer_id};
			runner.multipleUpdation(qry,args);
		}

		logger.debug(EXIT);
		

		return customer_id;
	}

	public StringBuffer validateFields()throws BaseException
	{
		logger.info("validateFields method()");
		logger.debug(ENTER);

		com.maan.Home.DataManipualtion.DataManipualtion data=new com.maan.Home.DataManipualtion.DataManipualtion();
		ValidationFormat	 validationFormat		=	new  ValidationFormat();
		StringBuffer error=new StringBuffer(1000);
		String values=null;

        //NAME VALIDATION
		values=data.validString(firstName+lastName,false);
		if("needed".equalsIgnoreCase(values)){
			error.append(runner.getErrormsg("3"));
			error.append(',');
		}

        //POBOX VALIDATION
        if("needed".equalsIgnoreCase(data.validLength(poBox,1)))	{
			error.append(runner.getErrormsg("29"));
			error.append(',');
		}
		if(!validationFormat.isDigitValidationFormat(poBox)){
			error.append(runner.getErrormsg("28"));
			error.append(',');
		}

        //NATIONALITY VALIDATION
        if(SELECT.equalsIgnoreCase(nationality)){
            error.append(runner.getErrormsg("5"));
            error.append(',');
        }

        //OCCUPATION
        values=data.validString(occupation,false);

        //PHONE OR MOBILE VALIDATION
        logger.info("Mobile "+mobile.length());
        logger.info("telephone  "+telephone.length());
        if(mobile.length()==0 && telephone.length() == 0){
            error.append(runner.getErrormsg("245"));
            error.append(',');
        }
        if(mobile!=null && mobile.length()>0 && "needed".equalsIgnoreCase(data.validLength(mobile,10))){
				error=error.append(runner.getErrormsg("13"));
                error.append(',');
		}

         //EMAIL VALIDATION
         if(email != null && email.length()==0){
        	 error.append(runner.getErrormsg("19"));
        	 error.append(',');
         }
		 if(email.length() > 0 && validationFormat.eMailValidation(email)){
			 error.append(runner.getErrormsg("20"));
			 error.append(',');
		 }

		 //DOB VALIDATION
		 if(dobDay!=null && dobMonth!=null && dobYear!=null) {
			 int ages = 0;
			 if(!DD.equalsIgnoreCase(dobDay)|| !MMM.equalsIgnoreCase(dobMonth) || !YYYY.equalsIgnoreCase(dobYear)){
                ages=calculateAge(dobYear,dobMonth,dobDay);
                if(ages  ==  2000){
                       error.append("Please Select Proper Date Of Birth,");
                }
		     }
        }
		if("others".equalsIgnoreCase(source) && otherSource.length()<=0)	{
			 error.append("Please Enter Customer Source for Others,");
		}

		logger.debug(EXIT);
		

		return error;
	}

	public String[][] searchValues(final String loginid) throws BaseException
	{
		logger.info("searchValues method(string)");
		logger.debug(ENTER);

        firstName = firstName.toLowerCase(Locale.ENGLISH);
		final String exLogin = getExistingCustomers(loginid);

		String qry="select first_name,last_name,company_name,email,telephone,mobile,dob,customer_id from personal_info where login_id in("+exLogin+") and (lower(first_name)=? or lower(company_name)=?) and email like ? and mobile=? and dob=?";
		final String args[] = {firstName.trim(),firstName.trim(),email.trim(),mobile.trim(),com.maan.common.util.OracleDateConversion.ConvertDate(dobDay+"-"+dobMonth+"-"+dobYear)};
		String [][] returnval=runner.multipleSelection(qry,args);

		if(returnval.length == 0){
			returnval = new String[0][0];
			if(!"".equalsIgnoreCase(firstName)){
				 firstName="%"+firstName+"%";
			}
			if(!"".equalsIgnoreCase(email)){
				 email="%"+email+"%";
			}
			if(MMM.equalsIgnoreCase(dobMonth)){
				dobMonth = "";
			}
			if(DD.equalsIgnoreCase(dobDay)){
				dobDay = "";
			}
			if(YYYY.equalsIgnoreCase(dobYear)){
				dobYear = "";
			}
			String qry1="select first_name,last_name,company_name,email,telephone,mobile,dob,customer_id from personal_info where (lower(first_name) like ? or lower(company_name) like ? or email like ? or mobile=? or dob=? and login_id in("+exLogin+")";

			final String args1[] = {"%"+firstName.trim()+"%","%"+firstName.trim()+"%",email.trim(),mobile.trim(),com.maan.common.util.OracleDateConversion.ConvertDate(dobDay+"-"+dobMonth+"-"+dobYear)};
			returnval=runner.multipleSelection(qry1,args1);
		}

		logger.debug(EXIT);
		

		return returnval;
	}

	public StringBuffer validateFieldsSearch()throws BaseException
	{
		logger.info("validateFieldsSearch method()");
		logger.debug(ENTER);

		com.maan.Home.DataManipualtion.DataManipualtion data=new com.maan.Home.DataManipualtion.DataManipualtion();
		ValidationFormat	 validationFormat		=	new  ValidationFormat();
		StringBuffer error=new StringBuffer(10000);
		String values=null;

		if("".equalsIgnoreCase(mobile) && "".equalsIgnoreCase(email) && "".equalsIgnoreCase(firstName) && "DD/MMM/YYYY".equalsIgnoreCase(dobDay+"/"+dobMonth+"/"+dobYear)){
			error.append("Please Enter Name/Mobile/DOB/Email for Search,");
		}
		if(email.length() > 0 && validationFormat.eMailValidation(email)){
					error.append(runner.getErrormsg("20"));
					error.append(',');
		}

		//Rajesh 12/10
		if(dobDay.length()>0 && dobMonth.length()>0 && dobYear.length()>0 ) {
			if(!DD.equalsIgnoreCase(dobDay)|| !MMM.equalsIgnoreCase(dobMonth) || !YYYY.equalsIgnoreCase(dobYear)) {
				values=data.checkDate(dobDay+"/"+dobMonth+"/"+dobYear);
				if("Invalid".equalsIgnoreCase(values)){
					error.append(runner.getErrormsg("8"));
					error.append(',');
				}
			}
		}

		logger.debug(EXIT);
		

		return error;
	}

	public StringBuffer validateFieldsSearchsss()throws BaseException
	{
		logger.info("validateFieldsSearchsss method()");
		logger.debug(ENTER);

		com.maan.Home.DataManipualtion.DataManipualtion data=new com.maan.Home.DataManipualtion.DataManipualtion();
		ValidationFormat	 validationFormat		=	new  ValidationFormat();
		StringBuffer error=new StringBuffer(1000);
		String values=null;
		if("".equalsIgnoreCase(mobile) && "".equalsIgnoreCase(email) && "".equalsIgnoreCase(firstName) && "DD/MMM/YYYY".equalsIgnoreCase(dobDay+"/"+dobMonth+"/"+dobYear)){
			error.append("Please select Name/Mobile/DOB/Email to Search,");
		}
		if(email.length() > 0 && validationFormat.eMailValidation(email)){
	    	error.append(runner.getErrormsg("20"));
			error.append(',');
		}
		if(!DD.equalsIgnoreCase(dobDay)|| !MMM.equalsIgnoreCase(dobMonth) || !YYYY.equalsIgnoreCase(dobYear)){
			values=data.checkDate(dobDay+"/"+dobMonth+"/"+dobYear);
			if("Invalid".equalsIgnoreCase(values)){
				error.append(runner.getErrormsg("8"));
				error.append(',');
			}
		}

		logger.debug(EXIT);
		

		return error;
	}

	public void settingValues(final String customer_id) throws BaseException
	{
		logger.info("settingValues(String) method()");
		logger.debug(ENTER);

		String qry="select title,gender,first_name,last_name,nationality,to_char(dob,'DD') as dobday,to_char(dob,'MM') as dobmonth,to_char(dob,'YYYY') as dobyear,telephone,mobile,fax,email,address1,address2,occupation,emirate,country,pobox,company_name,customer_id,customer_source from personal_info where customer_id=?";
		final String args[] = {customer_id};
		String[][] values=runner.multipleSelection(qry,args);
		values[0][0] = isNull(values[0][0],NIL);
		values[0][4] = isNull(values[0][4],NIL);
		title       = (SELECT.equalsIgnoreCase(values[0][0])?NIL:values[0][0]);
		gender      = values[0][1];
		firstName   = values[0][2];
		lastName    = isNull(values[0][3]);
		nationality = (SELECT.equalsIgnoreCase(values[0][4])?NIL:values[0][4]);

		/*if(values[0][5]!=null && Integer.parseInt(values[0][5])<9){
			values[0][5]=Integer.toString(Integer.parseInt(values[0][5]));
		}
		if(values[0][6]!=null && Integer.parseInt(values[0][6])<9){
			values[0][6]=Integer.toString(Integer.parseInt(values[0][6]));
		}*/
		dobDay		= values[0][5];
		dobMonth    = values[0][6];
		dobYear		= values[0][7];
		telephone   = values[0][8];
		mobile      = values[0][9];
		fax         = values[0][10];
		email       = values[0][11];
		address1    = values[0][12];
		address2    = values[0][13];
		occupation  = values[0][14];
		emirate     = values[0][15];
		country     = values[0][16];
		poBox       = values[0][17];
		if(values[0][18]!=null){
			orgName = values[0][18];
		}
		if("M/S".equalsIgnoreCase(title)){
			firstName=orgName;
		}
		curCus = values[0][19];
		source = values[0][20];
		source = isNull(source);

		logger.debug(EXIT);
		
	}

	public void settingValuesForCustomer(final String customer_id) throws BaseException
	{
		logger.info("settingValuesForCustomer(String) method()");
		logger.debug(ENTER);

		String qry="select title,gender,first_name,last_name,nationality,to_char(dob,'DD') as dobday,to_char(dob,'MM') as dobmonth,to_char(dob,'YYYY') as dobyear,telephone,mobile,fax,email,address1,address2,occupation,emirate,country,pobox,company_name,customer_source from personal_info where customer_id=?";
		final String args[] = {customer_id};
		final String[][] values=runner.multipleSelection(qry,args);

		title       = isNull(values[0][0]);
		gender      = isNull(values[0][1]);
		firstName   = isNull(values[0][2]);
		lastName    = isNull(values[0][3]);
		nationality = isNull(values[0][4]);

		/*if(values[0][5]!=null && Integer.parseInt(values[0][5])<9){
			values[0][5]=""+Integer.parseInt(values[0][5]);
		}
		if(values[0][6]!=null && Integer.parseInt(values[0][6])<9){
			values[0][6]=""+Integer.parseInt(values[0][6]);
		}*/

		dobDay		=	values[0][5];
		dobMonth	=	values[0][6];
		dobYear		=	isNull(values[0][7]);
		telephone	=	isNull(values[0][8]);
		mobile		=	isNull(values[0][9]);
		fax			=	isNull(values[0][10]);
		email		=	isNull(values[0][11]);
		address1	=	isNull(values[0][12]);
		address2	=	isNull(values[0][13]);
		occupation	=	isNull(values[0][14]);
		emirate		=	isNull(values[0][15]);
		country		=	isNull(values[0][16]);
		poBox		=	isNull(values[0][17]);
		source		=	isNull(values[0][19]);

		if(values[0][18]!=null){
			orgName = values[0][18];
		}
		if("M/S".equalsIgnoreCase(title)){
			firstName=orgName;
		}

		logger.debug(EXIT);
		
	}

   	public int calculateAge(final String yyyy, final String mmmm, final String dddd) throws BaseException
	{
   		logger.info("calculateAge(string,string,string) method()");
		logger.debug(ENTER);

		int result = 0;
		if(yyyy.equalsIgnoreCase(YYYY) || mmmm.equalsIgnoreCase(MMM) || dddd.equalsIgnoreCase(DD)){
			result = 2000;
		}

        int yyValue = Integer.parseInt(isNull(yyyy,"0"));
        int mmValue = Integer.parseInt(isNull(mmmm,"0"));
        int ddValue = Integer.parseInt(isNull(dddd,"0"));

        if(yyValue == 0 && mmValue == 0 && ddValue == 0){
        	result = 0;
        }

        Calendar cal = Calendar.getInstance();
        cal.set(yyValue,mmValue,ddValue);
        Calendar sysDate = Calendar.getInstance();
        long diff = sysDate.getTime().getTime() - cal.getTime().getTime();
        result = (int)(diff / YEAR_FACTOR);

        logger.info("AGE OF THE PERSON  "+result);

        logger.debug(EXIT);
		

        return result;
    }

    public String validateDateOfBirth(final int year,final int month,final int day) throws BaseException
	{
    	 logger.info("validateDateOfBirth(int,int,int) method()");
 		 logger.debug(ENTER);

	       String mess = "";
	       if((month == 2 && year%4 == 0) && (day == 30 || day == 31)){
	    	   mess ="Please Select a Valid DOB";
	       }
	       else if((month == 2 && year%4 != 0) && (day == 29 || day == 30 || day ==31)){
	    	   mess ="Please Select a Valid DOB";
	       }
	       else if((month == 4 || month == 6 || month == 9 || month == 11) && (day == 31)){
	    	   mess = "Please Select a Valid DOB";
	       }

	       logger.debug(EXIT);
		   

           return mess;
    }

    public String[][] getSourcesList() throws BaseException{
    	 logger.info("getSourcesList method()");
 		 logger.debug(ENTER);

 		 final String[][] result =  runner.multipleSelection("select Customer_Source_ID,upper(Customer_Source_NAME) from HOME_Customer_Source_MASTER order by Customer_Source_NAME");

 		 logger.debug(EXIT);
 		 

 		 return result;
    }

    public String[][] getOccupationList() throws BaseException{
    	 logger.info("getOccupationList method()");
		 logger.debug(ENTER);

		 final String[][] result =  runner.multipleSelection("select OCCUPATION_ID,OCCUPATION_NAME from HOME_OCCUPATION_MASTER order by OCCUPATION_NAME");

		 logger.debug(EXIT);
 		 

 		 return result;
    }

    public String getExistingCustomers(final String loginIds) throws BaseException
	{
    	logger.info("getExistingCustomers method()");
		logger.debug(ENTER);

		final HomeValidationBean homeValid = new HomeValidationBean();
		final String sql          = "select login_id from login_master where oa_code in(select oa_code from login_master where login_id=?) and login_id not in('NONE','NON')";
		final String args[] 	  = {loginIds};
		final String[][] valuess1 = runner.multipleSelection(sql,args);
		final String loginAllIds  = homeValid.removeLastChar(homeValid.getStringFromArray(valuess1),',');

		logger.debug(EXIT);
		

		return loginAllIds;
	}

	//Rajesh Newly added for Travel Generic
	public String getAllTravelPids(final String pid,final String loginBranch) throws BaseException
	{
		logger.info("getAllTravelPids method()");
		logger.debug(ENTER);

		final HomeValidationBean homeValid = new HomeValidationBean();
		final String sql = "select PRODUCT_ID from HOME_PRODUCT_MASTER where BROKER_ID=(select REMARKS from PRODUCT_MASTER where PRODUCT_ID=? and BRANCH_CODE=?)";
		final String args[] = {pid,loginBranch};
		final String[][] valuess=runner.multipleSelection(sql,args);
		final String result = homeValid.removeLastChar(homeValid.getStringFromArray(valuess),',');

		logger.debug(EXIT);
		

		return result;
	}

	public void updateMemberShipDetails(final String quoteNo,final String memberNo,final String motor,final String clauses,
			final String modePay,final String loyalty) throws BaseException
	{
		logger.info("updateMemberShipDetails method()");
		logger.debug(ENTER);

		String paymentMode = "";
		if("C".equalsIgnoreCase(modePay)){
			paymentMode = "Cash";
		}
		else if("D".equalsIgnoreCase(modePay)){
			paymentMode = "Credit Card";
		}
		else if("Q".equalsIgnoreCase(modePay)){
			paymentMode = "Cheque";
		}
		final String args[] = {clauses,memberNo,motor,paymentMode,loyalty,quoteNo};
		runner.multipleUpdation("update HOME_POSITION_MASTER set SUMMARY_CLAUSES=?,airmiles_no=?, insurance_details=?," +
				"payment_mode=?,csh_id_typ_code=? where QUOTE_NO=?",args);

		logger.debug(EXIT);
		
	}

	public  String policyGeneration(final String quoteId,final  String pid,final String loginBranch) throws BaseException
	{
		logger.info("policyGeneration method()");
		logger.debug(ENTER);

    	String current_no = "";
		String sql = "select status,policy_no from home_position_master where quote_no=?";
		final String args[] = {quoteId};
		final String[][] fromPosition = runner.multipleSelection(sql,args);
		fromPosition[0][0] = isNull(fromPosition[0][0],"Y");
		String newSql = "";
		String newSql1 = "";
		if("Y".equalsIgnoreCase(fromPosition[0][0])){
			String branchCode = loginBranch;

			newSql = "select nvl(max(current_no)+1,max(start_no)) from policyno_generate where type_id=(select POLICY_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE=? and PRODUCT_ID=?) and BRANCH_CODE=? and status='Y' and amend_id=(select max(amend_id) from policyno_generate where type_id=(select POLICY_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE=? and PRODUCT_ID=?) and branch_code=? and PRODUCT_ID=?)";
			final String args1[] = {loginBranch,pid,branchCode,loginBranch,pid,branchCode,pid};
			current_no=runner.singleSelection(newSql,args1);

			final String branchSql = "SELECT POLICY_PREFIX,BRANCH_CODE from POLICYNO_GENERATE where  branch_code=? and type_id=(select POLICY_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE=? and PRODUCT_ID=?) and status='Y' and amend_id=(select max(amend_id)from policyno_generate where type_id=(select POLICY_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE=? and PRODUCT_ID=?) and branch_code=? and PRODUCT_ID=?)";
			final String args2[] = {branchCode,loginBranch,pid,loginBranch,pid,branchCode,pid};
			final String[][] branchCode2 = runner.multipleSelection(branchSql,args2);

			String remark = "";
			if(branchCode2.length>0){
				remark = branchCode2[0][0]+"/"+branchCode2[0][1]+"/"+current_no;
			}

			newSql1 = "update policyno_generate set current_no=?,remarks=? where type_id=(select POLICY_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE=? and PRODUCT_ID=?) and status='Y' and branch_code=? and amend_id=(select max(amend_id) from policyno_generate where type_id=(select POLICY_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE=? and PRODUCT_ID=?) and branch_code=? and PRODUCT_ID=?)";
			final String args3[] = {current_no,remark,loginBranch,pid,branchCode,loginBranch,pid,branchCode,pid};
			runner.multipleUpdation(newSql1,args3);
			current_no=remark;
		}
		else{
			current_no=fromPosition[0][1];
		}

		logger.debug(EXIT);
		

		return current_no;
	}

	public String getMaxDebitNo(final String pid,final String loginBranch) throws BaseException
	{
		logger.info("getMaxDebitNo method()");
		logger.debug(ENTER);

		final String branchCode = loginBranch;
		final String debiSql = "select nvl(max(current_no)+1,max(start_no)) from policyno_generate where type_id=(select DEBIT_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE=? and PRODUCT_ID=?) and status='Y' and branch_code=? and amend_id=(select max(amend_id) from policyno_generate where type_id=(select DEBIT_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE=? and PRODUCT_ID=?) and branch_code=? and PRODUCT_ID=?)";
		final String args[] = {loginBranch,pid,branchCode,loginBranch,pid,branchCode,pid};
		final String current_no=runner.singleSelection(debiSql,args);
		final String debiUpdateSql = "update policyno_generate set current_no=?,remarks=? where type_id=(select DEBIT_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE=? and PRODUCT_ID=?) and status='Y' and branch_code=? and amend_id=(select max(amend_id) from policyno_generate where type_id=(select DEBIT_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE=? and PRODUCT_ID=?) and branch_code=? and PRODUCT_ID=?)";
		final String args1[] = {current_no,current_no,loginBranch,pid,branchCode,loginBranch,pid,branchCode,pid};
		runner.multipleUpdation(debiUpdateSql,args1);

		logger.debug(EXIT);
		

		return current_no;
	}

	public String getMaxReceiptNo(final String pid,final String branchCode) throws BaseException
	{
		logger.info("getMaxReceiptNo method()");
		logger.debug(ENTER);
		final String newSql = "select nvl(max(current_no)+1,max(start_no)) from policyno_generate where " +
				"type_id=(select RECEIPT_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE=? and PRODUCT_ID=?) " +
				"and BRANCH_CODE=? and status='Y' and amend_id=(select max(amend_id) from policyno_generate where " +
				"type_id=(select RECEIPT_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE=? and PRODUCT_ID=?) and " +
				"branch_code=? and PRODUCT_ID=?)";
		final String args[] = {branchCode,pid,branchCode,branchCode,pid,branchCode,pid};
		final String currentNo=runner.singleSelection(newSql,args);
		final String upSql = "update policyno_generate set current_no=?,remarks=? where type_id=(select " +
				"RECEIPT_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE=? and PRODUCT_ID=?) and status='Y' and " +
				"branch_code=? and amend_id=(select max(amend_id) from policyno_generate where " +
				"type_id=(select RECEIPT_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE=? and PRODUCT_ID=?) and " +
				"branch_code=? and PRODUCT_ID=?)";
		final String args3[] = {currentNo,currentNo,branchCode,pid,branchCode,branchCode,pid,branchCode,pid};
		runner.multipleUpdation(upSql,args3);
		logger.debug(EXIT);
		
		return currentNo;
	}

	public String getProductName(final String idval,final String loginBranch) throws BaseException
	{
		logger.info("getProductName method()");
		logger.debug(ENTER);

		final String args[] ={idval,loginBranch};
		final String args1[] = {loginBranch};
		final String PName1=runner.singleSelection("select product_id from product_master where status='Y' and branch_code=?",args1);
		logger.info("product ids"+PName1);
		final String PName=runner.singleSelection("select product_name from product_master where product_id=? and status='Y' and branch_code=?",args);
		logger.info("product names"+PName);
		

		logger.debug(EXIT);
		

		return PName;
	}

	public String isNull(final String content)throws BaseException
	{
		final StringBuffer contents = new StringBuffer();
		if(content==null){
			contents.append("");
		}
		else{
			contents.append(content);
		}
		return contents.toString();
	}

	public String isNull(final String content,final String value)throws BaseException{
		final StringBuffer contents = new StringBuffer();
		if(content==null){
			contents.append(value);
		}
		else{
			contents.append(content);
		}
		return contents.toString();
  }
  public String gerModeofPurchase(final String modePay)throws BaseException{
	  String paymentMode = "";
		if("C".equalsIgnoreCase(modePay)){
			paymentMode = "Cash";
		}else if("D".equalsIgnoreCase(modePay)){
			paymentMode = "Credit Card";
		}else if("Q".equalsIgnoreCase(modePay)){
			paymentMode = "Cheque";
		}
		return paymentMode;
  }
	
}
