package com.maan.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
public class StringHelper {

	public boolean checkDate(final String inDate, final String dateFormat) {
		final int dateFormatLength = dateFormat.length();
		boolean result;
		try {
			if (inDate.length() == dateFormatLength) {
				final SimpleDateFormat format = new SimpleDateFormat(dateFormat,Locale.ENGLISH);
				format.setLenient(false);
				format.parse(inDate);
				result = true;
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			result = false;
		}
		return result;
	}

	public Date covertDate(final String inDate, final String dateFormat) {
		Date date;
		final int dateFormatLength = dateFormat.length();
		try {
			if (inDate.length() == dateFormatLength) {
				final SimpleDateFormat format = new SimpleDateFormat(dateFormat,Locale.ENGLISH);
				format.setLenient(false);
				date = format.parse(inDate);
			} else {
				throw new Exception();
				// return date;
			}
		} catch (Exception e) {
			// return null;
			date = null;
		}
		return date;
	}

	public static String getSearchValue(final String type, final String value) {
		String result;

		if ("Start With".equals(type)) {// starting with search
			result = " like \'" + value + "%\'";
		} else if ("End With".equals(type)) { // Endeing With Search
			result = " like \'%" + value + "\'";
		} else if ("Contains".equals(type)) { // contains
			result = " like \'%" + value + "%\'";
		} else { // equal
			result = " = \'" + value + "\'";
		}

		return result;
	}
	public static String removeChars(String dataType, String value)
	{
		if("STRING".equalsIgnoreCase(dataType))
		{
			value=value.replaceAll("[^A-Z]", "");
		}else if("NUMBER".equalsIgnoreCase(dataType))
		{
			value=value.replaceAll("[^0-9]", "");
		}else if("DECIMAL".equalsIgnoreCase(dataType))
		{
			value=value.replaceAll("[^0-9.]", "");
			value=value.equals(".")?"0":value;
		}else if("MOBILE_NO".equalsIgnoreCase(dataType))
		{
			value=value.replace("+91", "");
			value=value.replaceAll("[^0-9]", "");
		}
		return value;
	}
	public static String getFileNameFormat(String fileName, String s1)
	{
		Calendar cal=Calendar.getInstance();
		SimpleDateFormat sdf=new SimpleDateFormat("ddMMMyyyy HH.mm.ss");
		String timestamp=sdf.format(cal.getTime());
		fileName=fileName.replaceAll(" ", "");
		int index=fileName.lastIndexOf(".");
		s1=s1!=null && s1.length()>0?("_"+s1):"";
		fileName=fileName.substring(0, index)+s1+"_"+timestamp+fileName.substring(index, fileName.length());
		return fileName;
	}
	public static boolean validateDate(String dateStr, String formatStr)
	{
		if (formatStr == null) return false;
		SimpleDateFormat df = new SimpleDateFormat(formatStr);
		Date testDate = null;
		try
		{
			testDate = df.parse(dateStr);
		}
		catch (ParseException e)
		{
			return false;
		}
		if (!df.format(testDate).equals(dateStr)) return false;
		return true;
	}
}
