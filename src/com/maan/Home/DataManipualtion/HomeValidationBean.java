package com.maan.Home.DataManipualtion;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Logger;

import com.maan.common.LogUtil;
import com.maan.common.exception.BaseException;
import com.maan.services.util.ValidationFormat;
import com.maan.services.util.runner;

public class HomeValidationBean
{
	private static final Logger logger = LogUtil.getLogger(HomeValidationBean.class);
	final static transient private String MONTHS = "MMM";
	final static transient private String YEARS = "YYYY";
	final static transient private String DAYS = "DD";
	final static transient private String NULL = "null";
	final static transient private String SELECTS = "select";
	final static transient private String ENTER = "- Enter";
	final static transient private String EXIT = "- Exit";
	final static transient private String SINSQL = "SingleSql";
	final static transient private String SINARG = "SingleArgs";
	
	public String dobDateValidation(final String days,final String months,final String years)throws BaseException{
		final StringBuffer errorBuf = new StringBuffer();
		if ((DAYS.equalsIgnoreCase(days) || "".equals(days))&& (MONTHS.equalsIgnoreCase(months) || "".equals(months))
				&& (YEARS.equalsIgnoreCase(years) || "".equals(years))) {
			errorBuf.append("Please select Domestic Staff Date Of Birth,");
		} else {
			errorBuf.append(dobCommonValidation(days,DAYS,"Please select Date"));
			errorBuf.append(dobCommonValidation(months,MONTHS,"Please select Month"));
			errorBuf.append(dobCommonValidation(years,YEARS,"Please select Year"));
		}
		return errorBuf.toString();
	}
	
	public String dobCommonValidation(final String firstStr,final String secondStr,final String message)throws BaseException{
		final StringBuffer errorBuf = new StringBuffer();
		if (firstStr.equalsIgnoreCase(secondStr) || "".equals(firstStr)) {
			errorBuf.append(message);
			errorBuf.append(',');
		}
		return errorBuf.toString();
	}
	
	public String claimValidation(final String claimSub,final String claimStatus)throws BaseException{
		final StringBuffer errorBuf = new StringBuffer(1000);
		if ("".equals(claimStatus) && !"N".equals(claimStatus)) {
			errorBuf.append(" Please select whether you have any claims in the past  3 years,");
		}
		if (("".equals(claimSub) || NULL.equals(claimSub))&& "Y".equals(claimStatus)) {
			errorBuf.append("Please enter the number of claims,");
		}
		return errorBuf.toString();
	}
	
	public String claimAmountValidation(final String claimamt,final String pastthryrs)throws BaseException{
		final StringBuffer errorBuf = new StringBuffer(1000);
		final ValidationFormat validationFormat = new ValidationFormat();
		//logger.info("royal test here number check isDigitValidationFormat..."+validationFormat.isDigitValidationFormat(claimamt));
		//logger.info("royal test here number check IsNegativeValidationFormat..."+validationFormat.IsNegativeValidationFormat(claimamt));
		if (pastthryrs.equals("Y")&&!validationFormat.isDigitValidationFormat(claimamt)&&validationFormat.isNegativeValidationFormat(claimamt)) {
			errorBuf.append("Please enter the valid claim amount,");
		}
		
		return errorBuf.toString();
	}
	
	public String claimNumberValidation(final String claim)throws BaseException{
		final ValidationFormat validationFormat = new ValidationFormat();
		final StringBuffer errorBuf = new StringBuffer(1000);
		if (validationFormat.isDigitValidationFormat(claim) == false) {
			errorBuf.append("Please enter the valid number of claims,");
		}
		else if(claim.length()>0){
			final int claimval = Integer.parseInt(claim);
			if (claimval < 0) {
				errorBuf.append("Number of claims should be positive,");
			}
		}
		return errorBuf.toString();
	}
	
	public String claimSubValidation(final String claimSub,final String claimStatus,final String errorMessage)throws BaseException{
		final StringBuffer errorBuf = new StringBuffer();
		if ((NULL.equals(claimSub)||"".equals(claimSub))&& "Y".equals(claimStatus)) {
			errorBuf.append(errorMessage);
			errorBuf.append(',');
		}
		return errorBuf.toString();
	}
	
	public String commonEmptyValidation(final String content, final String errorMessage)throws BaseException{
		final StringBuffer errorBuf = new StringBuffer();
		if (content==null || NULL.equals(content) || "".equals(content)||content.length()<=0) {
			errorBuf.append(errorMessage);
			errorBuf.append(',');
		}
		else if (SELECTS.equalsIgnoreCase(content)) {
			errorBuf.append(errorMessage);
			errorBuf.append(',');
		}
		return errorBuf.toString();
	}
	
	public String removeLastChar(final String content, final char delimeter) throws BaseException{
		logger.info("removeLastChar method()");
		logger.debug(ENTER);
			final StringBuffer contents = new StringBuffer();
			if (content.length() > 0){
				contents.append(content.substring(0, content.lastIndexOf(delimeter)));
			}
		logger.debug(EXIT);
		return contents.toString();
	}
	
	public String getStringFromArray(final String[][] arrayString) throws BaseException{
		final StringBuffer makeStr = new StringBuffer();
		for (int i = 0; i < arrayString.length; i++) {
			makeStr.append("'"+arrayString[i][0]);
			makeStr.append("',");
		}
		return makeStr.toString();
	}
	public String getStringFromArray(final String[][] arrayString,final int index) throws BaseException{
		final StringBuffer makeStr = new StringBuffer();
		for (int i = 0; i < arrayString.length; i++) {
			makeStr.append("'"+arrayString[i][index]);
			makeStr.append("',");
		}
		return makeStr.toString();
	}
	public Map getMapResult(final Map queryCollection,final String searchOption)throws BaseException{
		final Map getsTotal = new HashMap();
		logger.info("getMapResult method()...searchOption"+searchOption);
		logger.debug(ENTER);
		final String[][] single = runner.multipleSelection((String)queryCollection.get(SINSQL+searchOption), (String[])queryCollection.get(SINARG+searchOption));
		final String[][] multiple = runner.multipleSelection((String)queryCollection.get("MultipleSql"+searchOption), (String[])queryCollection.get("MultipleArgs"+searchOption));
		getsTotal.put("singlePolicys", single);
		getsTotal.put("multiplePolicys", multiple);
		logger.debug(EXIT);
		
		return (HashMap)getsTotal;
	}
}