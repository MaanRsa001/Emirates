package com.maan.proj.date;

import java.util.*;

/**
  * <ul>
  * <li> Author  : Manikandan.
  *	<li> Project : Common file for all projects.
  * <li> Purpose : To handle Date related data conversions.	  
  * </ul>
  */

public class DateFunction
{
	/**
	  * Constant value to find the Second(s) from the known millisecond value.
	  */
	public final long SECOND_FACTOR	= 1000;

	/**
	  * Constant value to find the Minute(s) from the known millisecond value.
	  */
	public final long MINUTE_FACTOR	= SECOND_FACTOR * 60;

	/**
	  * Constant value to find the Hour(s) from the known millisecond value.
	  */
	public final long HOUR_FACTOR	= MINUTE_FACTOR * 60;

	/**
	  * Constant value to find the Day(s) from the known millisecond value.
	  */
	public final long DAY_FACTOR	= HOUR_FACTOR * 24;

	/**
	  * Constant value to find the Year(s) from the known millisecond value.
	  */
	public final long YEAR_FACTOR	= DAY_FACTOR * 365;
	
	
	/**
	  * Get the Age for a given date of birth.
	  * @param birthDate Calendar object which has the date of birth.
	  * @return a age as int.
	  * @Exception Exception.
	  */
	
	public int getAge(Calendar birthDate) throws Exception
	{
		// get an instance of Calendar object.
		Calendar sysDate = Calendar.getInstance();
		
		// get the differents between system date and given date in millseconds.
		long diff = sysDate.getTime().getTime() - birthDate.getTime().getTime();
		
		// return the year by dividing millisecond by YEAR_FACTOR.
		return (int) (diff / YEAR_FACTOR);
	}

	
	/**
	  * Get the calendar object for the given String in dd/mm/YYYY format.
	  * @param stringDate String object which has the date in dd/mm/YYYY format.
	  * @return a valid Calendar object.
	  * @Exception Exception.
	  */

	public Calendar getCalendar(String stringDate) throws Exception
	{
		// get an instance of Calendar object.
		Calendar calendarDate = Calendar.getInstance();

		// to find the occurence of first "/" in the given date. 
		int index1 = stringDate.indexOf("/");

		// to find the occurence of last "/" in the given date. 
		int index2 = stringDate.lastIndexOf("/");

		// to check for the occurence of "-" string in the given date, if "/" not found.
		if(index1 == -1 &&  index2 == -1)
		{
			index1 = stringDate.indexOf("-");
			index2 = stringDate.lastIndexOf("-");
		}

		// get the day, month , year from the given date. 
		int d1 = Integer.valueOf(stringDate.substring(0,index1)).intValue();
		int m1 = Integer.valueOf(stringDate.substring(index1+1,index2)).intValue() - 1;
		int y1 = Integer.valueOf(stringDate.substring(index2+1)).intValue();
		
		// set the day, month ,year to a calendar object.
		calendarDate.set(y1,m1,d1);
		
		// return the calendar object
		return calendarDate;
	}

	
	/**
	  * Get the String object as date/month/year hour:min:sec for the given Calendar.
	  * @param date Calendar object. 
	  * @return a String which has date/month/year hour:min:sec format.
	  * @Exception Exception.
	  */

	public String getDateString(Calendar date) throws Exception
	{
		// get the date from the given Calendar.
		int day		= date.get(Calendar.DATE);

		// get the month from the given Calendar.
		int month	= date.get(Calendar.MONTH);

		// get the year from the given Calendar.
		int year	= date.get(Calendar.YEAR);

		// get the hour from the given Calendar.
		int hour	= date.get(Calendar.HOUR);

		// get the minute from the given Calendar.
		int min		= date.get(Calendar.MINUTE);

		// get the second from the given Calendar.
		int sec		= date.get(Calendar.SECOND);

		// return a String which has date/month/year hour:min:sec format.
		return day+"/"+(month+1)+"/"+year+" "+hour+":"+min+":"+sec;
	}


	/**
	  * Get the String object as year-month-day for the given Calendar.
	  * @param date Calendar object. 
	  * @return a String which has year-month-day format.
	  * @Exception Exception.
	  */

	public String getDateStringYMD(Calendar date) throws Exception
	{
		// get the date from the given Calendar.
		int day		= date.get(Calendar.DATE);

		// get the month from the given Calendar.
		int month	= date.get(Calendar.MONTH);

		// get the year from the given Calendar.
		int year	= date.get(Calendar.YEAR);

		// get the hour from the given Calendar.
		int hour	= date.get(Calendar.HOUR);

		// get the minute from the given Calendar.
		int min		= date.get(Calendar.MINUTE);

		// get the second from the given Calendar.
		int sec		= date.get(Calendar.SECOND);

		String retString = ""+year;
		
		// check for the leading zero for the single digit month, if not add the zero.
		if((month+1) < 10)
			retString += "-0"+(month+1);
		else
			retString += "-"+(month+1);

		// check for the leading zero for the single digit date, if not add the zero.
		if(day < 10)
			retString += "-0"+day;
		else
			retString += "-"+day;
		
		// return a String which has year/month/day format.
		return retString;
	}


	/**
	  * Get the String object as month-day-year for the given Calendar.
	  * @param date Calendar object. 
	  * @return a String which has month-day-year format.
	  * @Exception Exception.
	  */

	public String getDateStringMDY(Calendar date) throws Exception
	{
		// get the date from the given Calendar.
		int day		= date.get(Calendar.DATE);

		// get the month from the given Calendar.
		int month	= date.get(Calendar.MONTH);

		// get the year from the given Calendar.
		int year	= date.get(Calendar.YEAR);

		// get the hour from the given Calendar.
		int hour	= date.get(Calendar.HOUR);

		// get the minutes from the given Calendar.
		int min		= date.get(Calendar.MINUTE);

		// get the seconds from the given Calendar.
		int sec		= date.get(Calendar.SECOND);

		String retString = "";
		
		// check for the leading zero for the single digit month, if not add the zero.
		if((month+1) < 10)
			retString += "0"+(month+1);
		else
			retString += (month+1);

		// check for the leading zero for the single digit date, if not add the zero.
		if(day < 10)
			retString += "-0"+day;
		else
			retString += "-"+day;

		retString += "-"+year;
		
		// return a String which has month-day-year format.
		return retString;
	}

	
	/**
	  * Get the number of days between to dates. 
	  * @param startDate Calendar object. 
	  * @param endDate Calendar object. 
	  * @return the difference between the dates as a long.
	  * @Exception Exception.
	  */

	public long getDayDifference(Calendar startDate, Calendar endDate) throws Exception
	{
		// get the millisecond value of start date.
		long startTime = startDate.getTime().getTime();

		// get the millisecond value of end date.
		long endTime   = endDate.getTime().getTime();

		// get the period between the two milliseconds.
		long period = (endTime - startTime) / DAY_FACTOR;

		// return the period.
		return period;
	}

	public long getInterval(Calendar startDate, Calendar endDate) throws Exception
	{
		// get the millisecond value of start date.
		long startTime = startDate.getTime().getTime();

		// get the millisecond value of end date.
		long endTime   = endDate.getTime().getTime();

		// get the period between the two milliseconds.
		long period = endTime - startTime;

		// return the period.
		return period;
	}


	/**
	  * To check the given day is valid for the given month. 
	  * @param day as int. 
	  * @param month as int. 
	  * @param year as int. 
	  * @return true if the date is valid, else returns false.
	  * @Exception Exception.
	  */

	public boolean isValidDay(int day, int month, int year) throws Exception
	{
		// get an instance of Calendar object.
		Calendar myDate = Calendar.getInstance();
		
		// initialize the day to 1;
		int day1 = 1;

		// set the Calendar object to given day, month, year.
		myDate.set(year,month,day1);

		// get the actual maximum days for the given month.
		int maxDay = myDate.getActualMaximum(Calendar.DAY_OF_MONTH);

		// if given day is greater than maximum days of the given month, return false.
		if(day > maxDay)
			return false;
		
		// return true if the day is valid.
		return true;
	}


	/**
	  * To change the given date string from DMY format to YMD format. 
	  * @param dateStr as String. 
	  * @return a String has date in YMD format.
	  * @Exception Exception.
	  */
	
	public String changeDateFormatYMD(String dateStr) throws Exception
	{
		String newFormat = "";
		
		// get an instance of Calendar object with the given date settings
		Calendar newDate = getCalendar(dateStr);
		
		// get the date from the Calendar object.
		int day   = newDate.get(Calendar.DATE);

		// get the month from the Calendar object.
		int month = newDate.get(Calendar.MONTH)+1;

		// get the year from the Calendar object.
		int year  = newDate.get(Calendar.YEAR);

		// get the date in YMD format
		newFormat = ""+year+"/"+month+"/"+day;
		
		// return the date string in YMD format.
		return newFormat;
	}


	/**
	  * To change the given date string to DMY format. 
	  * @param dateStr as String. 
	  * @return a String has date in DMY format.
	  * @Exception Exception.
	  */

	public String changeDateFormatDMY(String dateStr) throws Exception
	{
		String newFormat = "";
		
		// get the first occurance of "/" from the given string.
		int index1 = dateStr.indexOf("/");

		// get the last occurance of "/" from the given string.
		int index2 = dateStr.lastIndexOf("/");

		if(index1 == -1 &&  index2 == -1)
		{
			index1 = dateStr.indexOf("-");
			index2 = dateStr.lastIndexOf("-");
		}
		
		// get the year, month, day from the given string.
		String year  = dateStr.substring(0,index1);
		String month = dateStr.substring(index1+1,index2);
		String day   = dateStr.substring(index2+1);
		
		// get the date string in DMY fromat.
		newFormat = day +"/"+ month +"/"+ year;
		
		// return the date string in DMY format.
		return newFormat;
	}

	
	/**
	  * get the System date as calendar object. 
	  * @return a Calendar object
	  */

	public Calendar getSystemDate()
	{
		// return an instance of Calendar object.
		return Calendar.getInstance();
	}
}