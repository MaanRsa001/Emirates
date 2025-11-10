package com.maan.common.Customer;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.logging.log4j.Logger;

import com.maan.Home.DataManipualtion.DataManipualtion;
import com.maan.common.LogUtil;
import com.maan.common.exception.BaseException;
import com.maan.common.util.OracleDateConversion;
import com.maan.common.util.dataCollection;
import com.maan.services.util.ValidationFormat;
import com.maan.services.util.runner;

public class HomeCustomerBean {
	private static final Logger logger = LogUtil.getLogger(HomeCustomerBean.class);
	final static transient private long SECOND_FACTOR = 1000;

	final static transient private long MINUTE_FACTOR = SECOND_FACTOR * 60;

	final static transient private long HOUR_FACTOR = MINUTE_FACTOR * 60;

	final static transient private long DAY_FACTOR = HOUR_FACTOR * 24;

	final static transient private long YEAR_FACTOR = DAY_FACTOR * 365;

	final static transient private String SELECT = "select";

	final static transient private String NEEDED = "needed";

	final static transient private String INVALID = "Invalid";

	final static transient private String YYYY = "YYYY";

	final static transient private String MMM = "MMM";

	final static transient private String NIL = "NIL";

	private transient String query = "";

	private transient final int countNo = 0;

	private transient String b2c = "";

	private transient String insuredlocation = "";

	private String loginId = "";
	private String title = "";
	private String gender = "";
	private String firstName = "";
	private String lastName = "";
	private String nationality = "";
	private String dobDay = "";
	private String dobMonth = "";
	private String dobYear = "";
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
	private String orgName = "";
	private String quoteNo = "";
	private String source = "";
	private String otherSource = "";
	private String cid = "";

	public void setLoginId(final String loginId) {
		this.loginId = loginId;
	}

	public void setBTOC(final String b2c) {
		this.b2c = b2c;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public void setGender(final String gender) {
		this.gender = gender;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public void setNationality(final String nationality) {
		this.nationality = nationality;
	}

	public void setDobDay(final String dobDay) {
		this.dobDay = dobDay;
	}

	public void setDobMonth(final String dobMonth) {
		this.dobMonth = dobMonth;
	}

	public void setDobYear(final String dobYear) {
		this.dobYear = dobYear;
	}

	public void setTelephone(final String telephone) {
		this.telephone = telephone;
	}

	public void setMobile(final String mobile) {
		this.mobile = mobile;
	}

	public void setFax(final String fax) {
		this.fax = fax;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public void setAddress1(final String address1) {
		this.address1 = address1;
	}

	public void setAddress2(final String address2) {
		this.address2 = address2;
	}

	public void setOccupation(final String occupation) {
		this.occupation = occupation;
	}

	public void setEmirate(final String emirate) {
		this.emirate = emirate;
	}

	public void setCountry(final String country) {
		this.country = country;
	}

	public void setPoBox(final String poBox) {
		this.poBox = poBox;
	}

	public void setOrgName(final String orgName) {
		this.orgName = orgName;
	}

	public void setQuoteNo(final String quoteNo) {
		this.quoteNo = quoteNo;
	}

	public void setInsuredLocation(final String location) {
		insuredlocation = location;
	}

	public void setSource(final String source) {
		this.source = source;
	}

	public void setOtherSource(final String otherSource) {
		this.otherSource = otherSource;
	}

	// Getter Method
	public String getBTOC() {
		return b2c;
	}

	public String getLoginId() {
		return loginId;
	}

	public String getTitle() {
		return this.title;
	}

	public String getGender() {
		return this.gender;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public String getNationality() {
		return this.nationality;
	}

	public String getDobDay() {
		return this.dobDay;
	}

	public String getDobMonth() {
		return this.dobMonth;
	}

	public String getDobYear() {
		return this.dobYear;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public String getMobile() {
		return this.mobile;
	}

	public String getFax() {
		return this.fax;
	}

	public String getEmail() {
		return this.email;
	}

	public String getAddress1() {
		return address1;
	}

	public String getAddress2() {
		return address2;
	}

	public String getOccupation() {
		return this.occupation;
	}

	public String getEmirate() {
		return this.emirate;
	}

	public String getCountry() {
		return this.country;
	}

	public String getPoBox() {
		return this.poBox;
	}

	public String getOrgName() {
		return this.orgName;
	}

	public String getQuoteNo() {
		return this.quoteNo;
	}

	public int getCountNo() {
		return this.countNo;
	}

	public String getInsuredLocation() {
		return insuredlocation;
	}

	public String getSource() {
		return this.source;
	}

	public String getOtherSource() {
		return this.otherSource;
	}

	public void settingValuesForCustomer(final String customerId)
			throws BaseException {
		logger.info("settingValuesForCustomer method()");
		logger.debug("- Enter");

		query = "select title,gender,first_name,last_name,nationality,to_char(dob,'DD') as dobday,to_char(dob,'MM') as dobmonth,to_char(dob,'YYYY') as dobyear,telephone,mobile,fax,email,address1,occupation,emirate,country,pobox,company_name,ADDRESS2,customer_source from personal_info where customer_id=?";
		final String[] args = { customerId };
		final String[][] values = runner.multipleSelection(query, args);

		title = isNull(values[0][0]);
		gender = isNull(values[0][1]);
		firstName = isNull(values[0][2]);
		lastName = isNull(values[0][3]);
		nationality = isNull(values[0][4]);
		if (values[0][5] != null && Integer.parseInt(values[0][5]) < 9) {
			values[0][5] = values[0][5];
		}
		if (values[0][6] != null && Integer.parseInt(values[0][6]) < 9) {
			values[0][6] = values[0][6];
		}
		dobDay = values[0][5];
		dobMonth = values[0][6];
		dobYear = isNull(values[0][7]);
		telephone = isNull(values[0][8]);
		mobile = isNull(values[0][9]);
		fax = isNull(values[0][10]);
		email = isNull(values[0][11]);
		address1 = isNull(values[0][12]);
		address2 = isNull(values[0][18]);
		occupation = isNull(values[0][13]);
		emirate = isNull(values[0][14]);
		country = isNull(values[0][15]);
		poBox = isNull(values[0][16]);
		source = isNull(values[0][19]);
		if (values[0][17] != null) {
			orgName = values[0][17];
		}
		if ("M/S".equalsIgnoreCase(title)) {
			firstName = orgName;
		}

		logger.debug("- Exit");
		
	}

	public StringBuffer validateFieldsSearch() throws BaseException {
		logger.info("validateFieldsSearch method()");
		logger.debug("- Enter");

		ValidationFormat validationFormat = new ValidationFormat();
		StringBuffer error = new StringBuffer(1000);

		if ("".equalsIgnoreCase(mobile)
				&& "".equalsIgnoreCase(email)
				&& "".equalsIgnoreCase(firstName)
				&& "DD/MMM/YYYY".equalsIgnoreCase(dobDay + "/" + dobMonth + "/"
						+ dobYear)) {
			error.append("Please Enter Name/Mobile/DOB/Email for Search,");
		}
		if (email!=null && email.length() > 0 && validationFormat.eMailValidation(email.trim())) {
			error.append(runner.getErrormsg("20"));
			error.append(',');
		}
		
		logger.info("Messages royal tested from travel customer ..dobDay is.."+dobDay);
	    logger.info("Messages royal tested from travel customer ..dobMonth is.."+dobMonth);
	    logger.info("Messages royal tested from travel customer ..dobYear is.."+dobYear);
	    if(dobDay!=null&&dobMonth!=null&&dobYear!=null){
		if ((dobDay!=null||dobDay.length() > 0) && (dobMonth!=null||dobMonth.length() > 0)
				&& (dobYear!=null||dobYear.length() > 0)) {
			if (!"DD".equalsIgnoreCase(dobDay)
					|| !MMM.equalsIgnoreCase(dobMonth)
					|| !YYYY.equalsIgnoreCase(dobYear)) {
				if (INVALID.equalsIgnoreCase(checkDate(dobDay + "/" + dobMonth
						+ "/" + dobYear))) {
					error.append(runner.getErrormsg("8"));
					error.append(',');
				}
			}
		}
	    }
		logger.debug("- Exit");
		

		return error;
	}

	public StringBuffer validateFields(final String pnames)
			throws BaseException {
		logger.info("validateFields method()");
		logger.debug("- Enter");

		DataManipualtion data = new DataManipualtion();
		ValidationFormat validationFormat = new ValidationFormat();
		StringBuffer error = new StringBuffer(1000);
		String values = null;
		String TotalName = firstName + lastName;

		values = data.validString(TotalName, false);

		if (NEEDED.equalsIgnoreCase(values)) {
			error.append(runner.getErrormsg("3"));
			error.append(',');
		}else if (!validationFormat.validateStringWithSpace(TotalName)) {
			error.append("Please Enter a valid Name,");
		}

		// POBOX VALIDATION
		if (NEEDED.equalsIgnoreCase(data.validLength(poBox, 1))) {
			error.append(runner.getErrormsg("29"));
			error.append(',');
		}
		if (!validationFormat.isDigitValidationFormat(poBox)) {
			error.append(runner.getErrormsg("28"));
			error.append(',');
		}

		// NATIONALITY VALIDATION
		if (SELECT.equalsIgnoreCase(nationality)) {
			error.append(runner.getErrormsg("5"));
			error.append(',');
		}
		// OCCUPATION
		// values=data.validString(occupation,false);

		// PHONE OR MOBILE VALIDATION
		if (mobile.length() == 0 && telephone.length() == 0) {
			error.append(runner.getErrormsg("245"));
			error.append(',');
		}

		// EMAIL VALIDATION
		if (email != null && email.length() == 0&&cid.equals("1")) {
			error.append(runner.getErrormsg("19"));
			error.append(',');
		}
		if (email.length() > 0 && validationFormat.eMailValidation(email)) {
			error.append(runner.getErrormsg("20"));
			error.append(',');
		}

		if (dobDay != null && dobMonth != null && dobYear != null) {
			int ages = 0;
			if (!"DD".equalsIgnoreCase(dobDay)
					&& !MMM.equalsIgnoreCase(dobMonth)
					&& !YYYY.equalsIgnoreCase(dobYear)) {
				ages = calculateAge(dobYear, dobMonth, dobDay);
				if (ages == 2000) {
					error.append("Please Select Proper Date Of Birth,");

				}
			} /*else {
				error.append("Please Select Proper Date Of Birth,");
			}*/
		}
		/*
		if (source.equalsIgnoreCase("others") && otherSource.length() <= 0) {
			error.append("Please Enter Customer Source for Others,");

		}*/

		logger.debug("- Exit");
		

		return error;
	}

	public StringBuffer validateFields() throws BaseException {
		logger.info("validateFields method()");
		logger.debug("- Enter");

		ValidationFormat validationFormat = new ValidationFormat();
		dataCollection data = new dataCollection();
		StringBuffer error = new StringBuffer(1000);
		String values = null;

		if (email.length() > 0 && validationFormat.eMailValidation(email)) {
			error.append(runner.getErrormsg("20"));
			error.append(',');
		}

		values = data.emailValidate(email);
		if ("b2c".equalsIgnoreCase(b2c) && NEEDED.equalsIgnoreCase(values)) {
			error.append(runner.getErrormsg("19"));
			error.append(',');
		}

		if (SELECT.equalsIgnoreCase(title)) {
			error.append(runner.getErrormsg("1"));
			error.append(',');
		}

		String TotalName = firstName + lastName;

		boolean flag = specialCharacterValidation(TotalName);
		if (!flag) {
			error.append("Special Characters are not allowed ");
			error.append(TotalName);
			error.append(',');
		}
		/*
		 * else if(TotalName.length() >0) {
		 * if(!validationFormat.validateStringWithSpace(TotalName))
		 * error.append("Please Enter a Valid Name"); }
		 */

		values = validString(TotalName, false);
		if (NEEDED.equalsIgnoreCase(values)) {
			error.append(runner.getErrormsg("3"));
			error.append(',');
		}
		if (mobile.length() <= 0 && telephone.length() <= 0) {
			error.append(runner.getErrormsg("245"));
			error.append(',');
		}
		if (!validationFormat.isDigitValidationFormat(telephone)) {
			error.append(runner.getErrormsg("278"));
			error.append(',');
		}
		if (!validationFormat.isDigitValidationFormat(mobile)) {
			error.append(runner.getErrormsg("277"));
			error.append(',');
		}
		if (SELECT.equalsIgnoreCase(emirate)) {
			error.append(runner.getErrormsg("25"));
			error.append(',');
		}
		if (NEEDED.equalsIgnoreCase(validLength(poBox, 1))) {
			error.append(runner.getErrormsg("29"));
			error.append(',');
		}
		if (!validationFormat.isDigitValidationFormat(poBox)) {
			error.append(runner.getErrormsg("28"));
			error.append(',');
		}
		values = validString(address1, false);
		if (NEEDED.equalsIgnoreCase(values)) {
			error.append(runner.getErrormsg("274"));
			error.append(',');
		} else if (INVALID.equalsIgnoreCase(values)) {
			error.append(runner.getErrormsg("276"));
			error.append(',');
		}

		if (fax != null && fax.length() > 0
				&& !validationFormat.isDigitValidationFormat(fax)) {
			error.append(runner.getErrormsg("16"));
			error.append(',');
		}

		int ages = 0;
		if (dobDay != null && dobMonth != null && dobYear != null) {
			if (!"DD".equalsIgnoreCase(dobDay)
					&& !MMM.equalsIgnoreCase(dobMonth)
					&& !YYYY.equalsIgnoreCase(dobYear)) {
				ages = calculateAge(dobYear, dobMonth, dobDay);
				if (ages == 2000) {
					error.append(runner.getErrormsg("280"));
					error.append(',');
				}else {
					error.append(validateDateOfBirth(Integer.parseInt(dobYear),Integer.parseInt(dobMonth),Integer.parseInt(dobDay)));
				}
			}else if (!"DD".equalsIgnoreCase(dobDay) || !MMM.equalsIgnoreCase(dobMonth)
					|| !YYYY.equalsIgnoreCase(dobYear)) {
				error.append(runner.getErrormsg("280"));
				error.append(',');
			}

		}
		/*if (occupation == null || "0".equals(occupation)) {
			error.append(runner.getErrormsg("282"));
			error.append(',');
		}
		if ("others".equalsIgnoreCase(source) && otherSource.length() <= 0) {
			error.append("Please Specify Customer Source for Others,");

		}*/

		logger.debug("- Exit");
		

		return error;
	}

	public String validString(final String value, final boolean format)
			throws BaseException {
		logger.info("validString method()");
		logger.debug("- Enter");

		String returnval = "";

		// value=value.trim();
		// String validChar=null;
		if (value.trim().length() <= 0) {
			returnval = NEEDED;
		}

		logger.debug("- Exit");
		

		return returnval;
	}

	public boolean specialCharacterValidation(final String value)
			throws BaseException {
		logger.info("specialCharacterValidation method()");
		logger.debug("- Enter");

		boolean flag = true;
		String invalidChar = null;
		if (value != null && "".equalsIgnoreCase(value.trim())) {
			invalidChar = "~!@#$%^*_+={}|";
			for (int i = 0; i < value.trim().length(); i++) {
				if (invalidChar.indexOf(value.charAt(i)) != -1) {
					flag = false;
					break;
				}
			}
		} else {
			flag = true;
		}
		logger.info("Valid Amount in HomeCustomerBean  " + flag);

		logger.debug("- Exit");
		

		return flag;
	}

	public String validLength(final String value, final int len)
			throws BaseException {
		logger.info("validLength method()");
		logger.debug("- Enter");

		String returnval = null;
		if (value.trim().length() >= len) {
			returnval = "";
		} else {
			returnval = NEEDED;
		}

		logger.debug("- Exit");
		

		return returnval;
	}

	public String[][] searchValues(final String loginId) throws BaseException {
		logger.info("searchValues method()");
		logger.debug("- Enter");
		String dob="";
		if (!"DD".equalsIgnoreCase(dobDay)) {
			dob = dobDay+"/";
		}
		if (!MMM.equalsIgnoreCase(dobMonth)) {
			dob = dob+dobMonth+"/";
		}
		if (!YYYY.equalsIgnoreCase(dobYear)) {
			dob = dob+dobYear;
		}

		firstName = firstName.toLowerCase(Locale.ENGLISH);
		String exLogin = getExistingCustomers(loginId);
		
		query = "select first_name,last_name,company_name,email,telephone,mobile,dob,customer_id from personal_info where login_id in("
				+ exLogin
				+ ") and (lower(first_name)=? or lower(company_name)=?) and email like ? and (mobile like ? or telephone like ? )and dob=to_date(?,'dd/mm/yyyy')  and application_id='1'";
		final String args[] = {
				firstName.trim(),
				firstName.trim(),
				email.trim(),
				mobile.trim(),
				mobile.trim(),
				dob};

		String[][] returnval = runner.multipleSelection(query, args);

		if (returnval.length == 0) {

			returnval = new String[0][0];
			if (!"".equalsIgnoreCase(firstName)) {
				firstName = "%" + firstName + "%";
			}	
			if (!"".equalsIgnoreCase(email)) {
				email = "%" + email + "%";
			}
			if (!"".equalsIgnoreCase(mobile)) {
				mobile = "%" + mobile + "%";
			}
			query = "select first_name,last_name,company_name,email,telephone,mobile,dob,customer_id from personal_info where login_id in("
					+ exLogin
					+ ") and (lower(first_name) like ? or lower(company_name) like ? or email like ? or mobile like ? or telephone like ? or  dob=to_date(?,'dd/mm/yyyy')) and application_id='1'";
			
			final String args1[] = {firstName.trim(),firstName.trim(),email.trim(),mobile.trim(),mobile.trim(),dob};
			returnval = runner.multipleSelection(query, args1);
		}

		logger.debug("- Exit");
		

		return returnval;
	}

	public String storedValues(String customerId, final String loginId,
			final String loginBranch, final String pids) throws BaseException {
		logger.info("storedValues method()");
		logger.debug("- Enter");

		customerId = customerId == null ? "" : customerId;
		customerId = "null".equals(customerId) ? "" : customerId;

		String qry = "0";
		if (customerId.length() > 0) {
			qry = "select count(*) from personal_info where customer_id=?";
			final String args[] = { customerId };
			qry = runner.singleSelection(qry, args);
		}
		if ("M/S".equalsIgnoreCase(title)) {
			orgName = firstName;
			firstName = "";
		}
		if ("others".equalsIgnoreCase(source) && otherSource.length() > 0) {
			source = otherSource;
		}

		if ("0".equalsIgnoreCase(qry)
				|| "DIDN'T SELECTED".equalsIgnoreCase(qry)) {

			customerId = DataManipualtion.getMaxCustomerId(loginBranch, pids);
			qry = "insert into personal_info(customer_id,application_id,title,first_name,last_name,amend_id,nationality,dob,gender,telephone,mobile,fax,email,address1,occupation,pobox,country,emirate,status,entry_date,login_id, company_name,address2,customer_source)values(?,'1',?,?,?,'1',?,?,?,?,?,?,?,?,?,?,?,?,'Y',(select sysdate from dual),?, ?,?,?)";
			final String args[] = {
					customerId,
					(SELECT.equalsIgnoreCase(title) ? "" : title),
					firstName,
					lastName,
					(SELECT.equalsIgnoreCase(nationality) ? "" : nationality),
					OracleDateConversion.ConvertDate(dobDay + "-" + dobMonth
							+ "-" + dobYear), gender, telephone, mobile, fax,
					email, address1,
					(occupation.equalsIgnoreCase("0") ? "" : occupation),
					poBox, country,
					(emirate.equalsIgnoreCase(SELECT) ? "" : emirate), loginId,
					orgName, insuredlocation, source };
			runner.multipleInsertion(qry, args);
		} else {

			qry = "update personal_info set application_id='1',title=?,first_name=?,last_name=?,amend_id='1',nationality=?,dob=?,gender=?,telephone=?,mobile=?,fax=?,email=?,address1=?,occupation=?,pobox=?,country=?,emirate=?,status='Y',effective_date=(select sysdate from dual),company_name=?,address2=?,customer_source=? where customer_id=?";
			final String args[] = {
					(title.equalsIgnoreCase(SELECT) ? "" : title),
					firstName,
					lastName,
					(nationality.equalsIgnoreCase(SELECT) ? "" : nationality),
					OracleDateConversion.ConvertDate(dobDay + "-" + dobMonth
							+ "-" + dobYear), gender, telephone, mobile, fax,
					email, address1,
					(occupation.equalsIgnoreCase("0") ? "" : occupation),
					poBox, country,
					(emirate.equalsIgnoreCase(SELECT) ? "" : emirate), orgName,
					insuredlocation, source, customerId };
			runner.multipleUpdation(qry, args);
		}

		logger.debug("- Exit");
		

		return customerId;
	}

	public String checkDate(final String strDate) throws BaseException {
		logger.info("storedValues method()");
		logger.debug("- Enter");

		String returnVal = "";
		SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy",
				Locale.ENGLISH);
		dateformat.setLenient(false);
		ParsePosition pos = new ParsePosition(0);
		Date date = dateformat.parse(strDate, pos);
		if ((date == null) || (pos.getErrorIndex() != -1)) {
			if (date == null) {
				returnVal = INVALID;
			}
			if (pos.getErrorIndex() != -1) {
				returnVal = INVALID;
			}
			returnVal = INVALID;
		}

		logger.debug("- Exit");
		

		return returnVal;
	}

	public void settingValues(final String customerId) throws BaseException {
		logger.info("settingValues method()");
		logger.debug("- Enter");

		query = "select title,gender,first_name,last_name,nationality,to_char(dob,'DD') as dobday,to_char(dob,'MM') as dobmonth,to_char(dob,'YYYY') as dobyear,telephone,mobile,fax,email,address1,occupation,emirate,country,pobox,company_name,ADDRESS2,customer_source from personal_info where customer_id=?";
		final String args[] = { customerId };
		final String[][] values = runner.multipleSelection(query, args);

		values[0][0] = isNull(values[0][0]);
		values[0][4] = isNull(values[0][4]);

		if (values[0][0].length() == 0) {
			values[0][0] = NIL;
		}
		if (values[0][4].length() == 0) {
			values[0][4] = NIL;
		}
		title = (values[0][0].equalsIgnoreCase(SELECT) ? NIL : values[0][0]);
		gender = values[0][1];
		firstName = values[0][2];
		lastName = isNull(values[0][3]);
		nationality = (values[0][4].equalsIgnoreCase(SELECT) ? NIL
				: values[0][4]);

		/*
		 * if(values[0][5]!=null && Integer.parseInt(values[0][5])<9){
		 * values[0][5]=values[0][5]; }
		 *
		 * if(values[0][6]!=null && Integer.parseInt(values[0][6])<9){
		 * values[0][6]=values[0][6]; }
		 */

		dobDay = values[0][5];
		dobMonth = values[0][6];
		dobYear = values[0][7];
		telephone = values[0][8];
		mobile = values[0][9];
		fax = values[0][10];
		email = values[0][11];
		address1 = values[0][12];
		address2 = values[0][18];
		occupation = values[0][13];
		emirate = values[0][14];
		country = values[0][15];
		poBox = values[0][16];
		source = values[0][19];
		source = source == null ? "" : source;

		if (values[0][17] != null) {
			orgName = values[0][17];
		}

		if ("M/S".equalsIgnoreCase(title)) {
			firstName = orgName;
		}

		logger.debug("- Exit");
		
	}

	public int calculateAge(final String yyyy, final String mmmm,
			final String dddd) throws BaseException {
		logger.info("calculateAge method()");
		logger.debug("- Enter");

		int result = 0;
		if (yyyy.equalsIgnoreCase(YYYY) || mmmm.equalsIgnoreCase(MMM)
				|| dddd.equalsIgnoreCase("DD")) {
			result = 2000;
		}

		/*
		 * yyyy = yyyy==null?"0":yyyy; mmmm = mmmm==null?"0":mmmm; dddd =
		 * dddd==null?"0":dddd;
		 */

		int year = Integer.parseInt(yyyy);
		int month = Integer.parseInt(mmmm);
		int day = Integer.parseInt(dddd);

		if (year == 0 && month == 0 && day == 0) {
			result = 0;
		}

		Calendar cal = Calendar.getInstance();
		cal.set(year, month, day);
		Calendar sysDate = Calendar.getInstance();
		long diff = sysDate.getTime().getTime() - cal.getTime().getTime();
		result = (int) (diff / YEAR_FACTOR);
		logger.info("AGE OF THE PERSON  " + result);

		logger.debug("- Exit");
		

		return result;
	}

	public String validateDateOfBirth(final int year, final int month,
			final int day) throws BaseException {
		logger.info("validateDateOfBirth method()");
		logger.debug("- Enter");

		String mess = "";
		if ((month == 2 && year % 4 == 0) && (day == 30 || day == 31)) {
			mess = "Please Select a Valid DOB";

		} else if ((month == 2 && year % 4 != 0)
				&& (day == 29 || day == 30 || day == 31)) {
			mess = "Please Select a Valid DOB";

		} else if ((month == 4 || month == 6 || month == 9 || month == 11)
				&& (day == 31)) {
			mess = "Please Select a Valid DOB";
		}

		logger.debug("- Exit");
		

		return mess;
	}

	public String[][] getOccupationList() throws BaseException {
		logger.info("getOccupationList method()");
		logger.debug("- Enter");

		query = "select OCCUPATION_ID,OCCUPATION_NAME from HOME_OCCUPATION_MASTER order by OCCUPATION_NAME";
		final String[][] result = runner.multipleSelection(query);

		logger.debug("- Exit");
		

		return result;
	}

	public String[][] getSourcesList() throws BaseException {
		logger.info("getSourcesList method()");
		logger.debug("- Enter");

		query = "select Customer_Source_ID,upper(Customer_Source_NAME) from HOME_Customer_Source_MASTER where status='Y' order by Customer_Source_NAME";
		final String[][] result = runner.multipleSelection(query);

		logger.debug("- Exit");
		

		return result;
	}

	public String getExistingCustomers(final String loginIds)
			throws BaseException {
		logger.info("getExistingCustomers method()");
		logger.debug("- Enter");

		String loginAllIds = "";
		query = "select login_id from login_master where oa_code in(select oa_code from login_master where login_id=?) and login_id not in('NONE','NON')";
		final String args[] = { loginIds };
		final String[][] valuess1 = runner.multipleSelection(query, args);
		for (int i = 0; i < valuess1.length; i++) {
			loginAllIds = "'" + valuess1[i][0] + "'," + loginAllIds;
		}
		if (loginAllIds.length() > 0) {
			loginAllIds = loginAllIds
					.substring(0, loginAllIds.lastIndexOf(','));
		}

		logger.debug("- Exit");
		

		return loginAllIds;
	}

	public String[][] emirateCollection(final String cid) throws BaseException {
		logger.info("emirateCollection method()");
		logger.debug("- Enter");

		query = "select city_name from city_master where country_id=? and lower(city_name) not in('others') order by city_name";
		final String args[] = { cid };
		String[][] title;title = runner.multipleSelection(query, args);

		logger.debug("- Exit");
		

		return title;
	}

	public String[][] titleCollection(final String branch) throws BaseException {
		logger.info("titleCollection method()");
		logger.debug("- Enter");

		query = "select title_name from  title_master where branch_code=? and language is null";
		final String args[] = { branch };
		final String[][] title = runner.multipleSelection(query, args);

		logger.debug("- Exit");
		

		return title;
	}

	public String[][] nationalityCollection() throws BaseException {
		logger.info("nationalityCollection method()");
		logger.debug("- Enter");

		query = "select distinct NATIONALITY_NAME from country_master where amend_id||country_id in( select max(amend_id)||country_id from country_master group by country_id) order by NATIONALITY_NAME";
		final String[][] title = runner.multipleSelection(query);

		logger.debug("- Exit");
		

		return title;
	}

	public String isNull(final String content) throws BaseException {
		final StringBuffer contents = new StringBuffer();
		if (content == null) {
			contents.append("");
		} else {
			contents.append(content);
		}
		return contents.toString();
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}
}
