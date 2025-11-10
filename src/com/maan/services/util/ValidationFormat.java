/**
  *
  *  Author  : Sivakumar.k  [13/07/2007]
  *	 Company : MaanSarovar Software Private Limited  Chennai-1
  *	 Project : Common file for all projects.
  *  Purpose : This is the basic java Bean File By Using Common String Validation
  *
*/
package com.maan.services.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.Logger;

import com.maan.common.LogUtil;
import com.maan.common.exception.BaseException;
public class ValidationFormat
{
	private static final Logger logger = LogUtil.getLogger(ValidationFormat.class);
	final static transient private String ENTER = "- Enter";
	final static transient private String EXIT = "- Exit";
	
	public boolean IsDateValidationFormat(String ddmmyyyy)
	{
		DateFormat df = new SimpleDateFormat ("dd/MM/yyyy"); // (replace by "MM/dd/yyyy" if your date format is month/day/year)
        df.setLenient(false);
		boolean b = true;
    	Date dt = new Date();
		try{
			dt = df.parse (ddmmyyyy);
		}
		catch(ParseException e){
			b = false;
		}
		return b;
    }

	public boolean IsLetterValidationFormat(String Value)
	{
		boolean b = true;
		Value = Value.trim();
		for (int i = 0; i < Value.length(); ++i){
			char c = Value.charAt(i);
			if (!Character.isLetter(c))
				b = false;
		}
		return b;
	}

	public boolean IsLetterWithSpaceValidationFormat(String Value)
	{
		boolean b = true;
		Value = Value.trim();
		for (int i = 0; i < Value.length(); ++i){
			char c = Value.charAt(i);
			if (!Character.isLetter(c) && !Character.isSpace(c)){
				b = false;
			}
		}
		return b;
	}

	public boolean IsDigitValidationFormat(String Value)
	{
		boolean b = true;
		Value = Value.trim();
		for (int i = 0; i < Value.length(); ++i)
		{
			char c = Value.charAt(i);
			if (!Character.isDigit(c)&& !Character.isSpace(c))
				b = false;
		}
		return b;
	}

	public boolean IsNegativeValidationFormat(String Value)
	{
		boolean b = true;
		Value = Value.trim();
		if(!Value.substring(0,1).equalsIgnoreCase("-"))
			b	=	false;
		return b;
	}

	public boolean IsLetterOrDigitWithSpaceValidationFormat(String Value)
	{
		System.out.println("kkkkkkk");
		boolean b = true;
		Value = Value.trim();
		for (int i = 0; i < Value.length(); ++i){
			char c = Value.charAt(i);
			if (!Character.isLetterOrDigit(c) && !Character.isSpace(c)){
				b = false;
			}
		}
		return b;
	}

	public boolean IsLetterOrDigitValidationFormat(String Value)
	{
		boolean b = true;
		Value = Value.trim();
		for (int i = 0; i < Value.length(); ++i)
		{
			char c = Value.charAt(i);
			if (!Character.isLetterOrDigit(c))
				b = false;
		}
		return b;
	}

	public boolean EMailValidation(String email)
   {
      boolean b =  true;
      Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
      Matcher m = p.matcher(email);
      boolean matchFound = m.matches();
      if (matchFound)
        b=false;
      else
        b=true;
	  return b;
   }

   public String checkDate(String strDate)
	{
		String returnVal= new String("Valid");;
		java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy");
		df.setLenient(false);
		java.text.ParsePosition pos = new java.text.ParsePosition(0);
		//String strDate = " 12/13/2003";
		java.util.Date date = df.parse(strDate, pos);
		// Check all possible things that signal a parsing error
		if ((date == null) || (pos.getErrorIndex() != -1)) {
			System.out.println("Error: " + pos.getIndex());
			if (date == null) {
				System.out.println("Date is null");
				return "Invalid";
			}
			if (pos.getErrorIndex() != -1) {
				//System.out.println("Error index: " + pos.getErrorIndex());
				return "Invalid";
			}
				return "Invalid";
		}
		return returnVal;
	}
	public static boolean isNumberValue(String str)
	{
        String alphaNum ="1234567890.";
        for (int i = 0; i < str.length(); i++) {
            if (alphaNum.indexOf(str.charAt(i)) == -1) {
                return false;
            }
        }
        return true;
    }

	public boolean validateStringWithSpace(String Value) {
		//String result = "";
		boolean res = true;
		Value = Value.trim();
		if (Value.length() == 0) {
			return false;
		}
		for (int i = 0; i < Value.length(); ++i) {
			char c = Value.charAt(i);
			if (!Character.isLetter(c) && !Character.isSpace(c)) {
				return false;
			}
		}
		return res;
	}
	public boolean sysDateValidation(final String date){
		boolean flag = false;
		try{
			String sql;sql = "select count(*) from dual where sysdate<to_Date(?,'dd-mm-yyyy')+1";
			String cols[] = {date}; 
			String res = runner.singleSelection(sql, cols);
			if(res.equals("0")){
				flag = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
	
	public boolean isDateValidationFormat(final String ddmmyyyy) throws BaseException,ParseException
	{
		logger.info("isDateValidationFormat method()");
		logger.debug(ENTER);

		DateFormat dformat = new SimpleDateFormat ("dd/MM/yyyy",Locale.ENGLISH); 
		dformat.setLenient(false);
		boolean bVal = true;
		dformat.parse (ddmmyyyy);

		logger.debug(EXIT);
		

		return bVal;
    }
	
	public boolean isDigitValidationFormat(final String Value) throws BaseException
	{
		logger.info("isDigitValidationFormat method()");
		logger.debug(ENTER);

		boolean bVal = true;
		for (int i = 0; i < Value.trim().length(); ++i)
		{
			char cVal = Value.trim().charAt(i);
			if (!Character.isDigit(cVal)){
				bVal = false;
			}
		}

		logger.debug(EXIT);
		

		return bVal;
	}
	
	public boolean isNotFullySymbols(final String value) throws BaseException {
		
		logger.info("isNotFullySymbols method(String)");
        logger.debug(ENTER);
        
		boolean flg=false;
		for(int i=0;i<value.length();i++){
			if(Character.isLetterOrDigit(value.charAt(i))){
				flg=true;
				break;
			}
		}
		
		logger.debug(EXIT);
		
		
		return flg;
	}
	public boolean eMailValidation(final String email)  throws BaseException
    {
	  logger.info("Validation Format eMailValidation method()");
      logger.debug(ENTER);

      boolean bVal =  true;
      Pattern pattern = Pattern.compile(".+@.+\\.[a-z]+");
      Matcher matcher = pattern.matcher(email);
      boolean matchFound = matcher.matches();
      if(matchFound){
    	  bVal = false;
      }
      else{
    	  bVal=true;
      }

      logger.debug(EXIT);
	  

	  return bVal;
    }
	public boolean isNegativeValidationFormat(final String Value) throws BaseException
	{
		logger.info(" Validation Format isNegativeValidationFormat method()");
		logger.debug(ENTER);

		boolean bVal = true;
		if(!"-".equalsIgnoreCase(Value.trim().substring(0,1))){
			bVal	=	false;
		}

		logger.debug(EXIT);
		

		return bVal;
	}
}