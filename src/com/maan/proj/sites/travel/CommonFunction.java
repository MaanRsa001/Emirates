package com.maan.proj.sites.travel;


import proj.sql.QueryBuilder;
import proj.date.DateFunction;
import proj.util.HtmlReader;

import java.sql.Statement;
import java.util.Calendar;

/**
  * <ul>
  * <li> Author  : Manikandan.
  *	<li> Project : Common file for all projects.
  * <li> Purpose : To handle common functionalities like session validation, error, header, footer etc.
  * </ul>
  */

public class CommonFunction implements TravelConstant
{
	
	private Statement smt;
	public final int MAX_SESSION  = 30;


	public CommonFunction(Statement smt)
	{
		this.smt = smt;
	}

	public CommonFunction()
	{
	}
	
	/**
	  * To check the validity of the uid.
	  * @param uid pass the user id as String.
	  * @param smt statement object for execute the SQL queries.
	  * @return StringBuffer.
	  */

	public StringBuffer isValidUID(String uid) 
	{
		String query = "";
		String retValue[][] = new String[0][0];
		QueryBuilder sql = new QueryBuilder(smt);
		
		try
		{
			// Check the availability of the uid in session_master.
			query = "select session_id from travel_session where unix_timestamp(sysdate()) - unix_timestamp(out_time) < "+(MAX_SESSION * 60)+" and status = 'Y' and session_id = '"+uid+"'";
				
			retValue = sql.getResultSet(query);
			if(retValue.length > 0)
			{
				query = "update travel_session set out_time = sysdate() where session_id = '"+uid+"'";
				smt.executeUpdate(query);
				return new StringBuffer();
			}
			else
			{
				query = "update travel_session set status = 'N' where session_id = '"+uid+"'";
				smt.executeUpdate(query);

				String replaceDealerInfo[][] = { {"%%error%%","Your session is invalid"},
												 {"%%caption%%","Home"},
												 {"%%form_action%%","/cgi-bin/home.pl"}
											   };
				return printBody("/html/session.htm", replaceDealerInfo);

			}

		}
		catch(Exception e)
		{
			// if any exception show the Error page.
			 StringBuffer ret = new StringBuffer("Exeception : "+e);
			 ret.append(printUIDError());
			 return ret;
		}
	}


	/**
	  * To print the header part from common.htm file.
	  * @param replaceArray an Array contains read and replace part.
	  * @return StringBuffer of Header file.
	  */

	public StringBuffer printHead(String replaceArray[][])
	{
		// read the header file using HtmlReader class and return the content as StringBuffer.
		return (new HtmlReader().replaceValue(PATH+"/html/royaltravel/common.htm",replaceArray));
	}

	/**
	  * To print the header part other than common.htm file.
	  * @param replaceArray an Array contains read and replace part.
	  * @param fileName the file to be read.
	  * @return StringBuffer of Header file.
	  */

	public StringBuffer printHead(String replaceArray[][], String fileName)
	{
		// read the header file using HtmlReader class and return the content as StringBuffer.
		return (new HtmlReader().replaceValue(PATH+"/html/royaltravel/"+fileName,replaceArray));
	}


	/**
	  * To print the admin header part.
	  * @param replaceArray an Array contains read and replace part.
	  * @return StringBuffer of admin Header file.
	  */

	public StringBuffer printAdminHead(String replaceArray[][])
	{
		// read the header file using HtmlReader class and return the content as StringBuffer.
		return (new HtmlReader().replaceValue(PATH+"/html/royaltravel/admin_head.htm",replaceArray));
	}



	/**
	  * To print the footer par from footer.htm file.
	  * @param replaceArray an Array contains read and replace part.
	  * @return StringBuffer of footer file.
	  */

	public StringBuffer printFooter(String replaceArray[][])
	{
		// read the footer file using HtmlReader class and return the content as StringBuffer.
		return (new HtmlReader().replaceValue(PATH+"/html/royaltravel/footer.htm",replaceArray));
	}

	
	/**
	  * To read any html file
	  * @param fileName the name of the file to be read.
	  * @param replaceArray an Array contains read and replace part.
	  * @return StringBuffer of footer file.
	  */

	public StringBuffer printBody(String fileName, String replaceArray[][])
	{
		// read the given file using HtmlReader class and return the content as StringBuffer.
		return (new HtmlReader().replaceValue(PATH+fileName,replaceArray));
	}

	
	/**
	  * To print the error message using warn.htm file.
	  * @param errorString the error message to be print.
	  * @return StringBuffer of error file.
	  */

	public StringBuffer printError(String errorString)
	{
		HtmlReader readHtml = new HtmlReader();
		String replaceArray[][] = { {"%%error%%",errorString}
								  };
		StringBuffer returnValue = new StringBuffer("<br> \n");

		// read the error page using HtmlReader class and return the content as StringBuffer.
		returnValue.append(readHtml.replaceValue(PATH+"/html/warn.htm",replaceArray));
		
		return returnValue;
	}


	/**
	  * To print the error message using warn.htm file.
	  * @param errorString the error message to be print.
	  * @return StringBuffer of error file.
	  */

	public StringBuffer printSessionError(String errorString)
	{
		HtmlReader readHtml = new HtmlReader();
		String replaceArray[][] = { {"%%error%%",errorString},
									{"%%caption%%","Login"},
									{"%%form_action%%","http://www.insurancemust.com.com/servlets/RoyalLogin"}
								  };
		StringBuffer returnValue = new StringBuffer("<br> \n");

		// read the error page using HtmlReader class and return the content as StringBuffer.
		returnValue.append(readHtml.replaceValue(PATH+"/html/session.htm",replaceArray));
		
		return returnValue;
	}


	public StringBuffer printMessage(String title, String body, String tail)
	{
		String replace[][] = {  {"%%msg_title%%", title},
								{"%%msg_tail%%", tail},
								{"%%msg_body%%", body},
							 };
		return printBody("/html/royaltravel/message.htm",replace);
	}

	public StringBuffer printMessage(String[][] replace)
	{
		return printBody("/html/royaltravel/message.htm",replace);
	}


	/**
	  * To print the invalid sesion errors by using uid_error.htm file.
	  * @return StringBuffer of invalid session error file.
	  */

	public StringBuffer printUIDError()
	{
		String replaceArray[][] = { {"%%uid%%",""}};

		// read the uid error page using HtmlReader class and return the content as StringBuffer.
		return (new HtmlReader().replaceValue(PATH+"/html/uid_error.htm",replaceArray));
	}

	/**
	  * To Check the login name is valid for particular session id.
	  * @param uid pass the user id as a String.
	  * @param login pass the login as a String.
	  * @param smt statement object for execute the SQL queries.
	  * @return boolean depends on the validity of the login.
	  */

	public boolean checkLogin(String uid, String login) throws Exception 
	{
		QueryBuilder sql = new QueryBuilder(smt);

		// get the login for the particular uid from login_info table.
		String query = "select login_id from travel_session where session_id='"+uid+"' and status ='Y'";
		String dbLogin[][] = sql.getResultSet(query);

		// if the login name is equals to the given login, return true.
		if(dbLogin[0][0].trim().equalsIgnoreCase(login.trim()))
			return true;
		
		// if both are not equal return false.
		return false;
	}

	
	/**
	  * To Check the admin login name is valid for particular session id.
	  * @param uid pass the user id as a String.
	  * @param login pass the login as a String.
	  * @param smt statement object for execute the SQL queries.
	  * @return boolean depends on the validity of the login.
	  */

	public boolean checkAdminLogin(String login) throws Exception 
	{
		QueryBuilder sql = new QueryBuilder(smt);
		
		// get the login for the particular uid from login_admin table.
		String query = "select count(*) from travel_login_admin where login_id='"+login+"' and status='Y'";
		String[][] dbLogin = sql.getResultSet(query);
		
		// if the login name is equals to the given login, return true.
		if(dbLogin.length > 0)
			return true;

		// if both are not equal return false.
		return false;
	}


	/**
	  * To get the From date to To date html.
	  * @param login pass the login as a String.
	  * @param uid pass the user id as a String.
	  * @param fileName to indicate servle file name which will take response after submission of this page.
	  * @param trans is a flag which indicate request from main site or admin part.
	  * @param dealerID pass the dealer id as a String.
	  * @param smt statement object for execute the SQL queries.
	  * @return boolean depends on the validity of the login.
	  */

	public static StringBuffer getFromToHtml(String login, String uid, String fileName, String trans, String dealerID)
	{
		// Array of months.
		String month[] = {"January","February","March","April","May","June","July","August","September","October","November","December"};
		
		// get the current system date.
		DateFunction day = new DateFunction();
		Calendar sysDate = day.getSystemDate();
		int sysDay   = sysDate.get(Calendar.DATE);
		int sysMonth = sysDate.get(Calendar.MONTH);
		int sysYear  = sysDate.get(Calendar.YEAR);

		// Construct the html.
		StringBuffer printString = new StringBuffer();
		printString.append("<html><head> \n");
		printString.append(getDateScript());
		printString.append("</head><body><div align='left'> \n");
		printString.append("<form name=form1 action='/servlets/"+fileName+"' method='post'> \n");
		printString.append("\n");
		printString.append("<table width='650' cellspacing='0' cellpadding='0' border='0'> \n");
		printString.append("<tr bgcolor='#ffffff' align='center'><td> \n");
		printString.append("<table width='75%' cellspacing='0' cellpadding='0' border='0'> \n");
		printString.append("<tr bgcolor='#ffffff' height='27%' align='center'><td colspan='2' height='40'><font face='arial' size='2'>Select the period for which you would like to view your transaction details with AccurateTrussBrace.com.</font> </td></tr>");
		printString.append("<tr bgcolor='#666666' height='27%'><td colspan='2' height='30'><b><font face='arial' size='2' color='#ffffff'>&nbsp;&nbsp;Period of Transaction</font></b></td></tr>");
		printString.append("<tr bgcolor='#FAF7EF' height='27%'><td width='40%' height='33' align='right'><font face='Arial, Helvetica, sans-serif' size='2'>From : &nbsp;&nbsp;</font></td><td width='60%' align='left' height='33'> \n");
		
		// Html part for printing month in from date
		printString.append("<select name=fromMonth> \n");
		for(int i=0; i < 12; i++)
		{
			String selected = "";
			if(sysMonth == i)
				selected = "selected";
			printString.append("<option "+selected+" value="+(i)+">"+month[i].substring(0,3)+"</option> \n");
			selected = null;
		}
		printString.append("</select> \n");

		// Html part for printing day in from date
		printString.append("<select name=fromDay> \n");
		for(int i=1; i <= 31; i++)
		{
			String selected = "";
			if(sysDay == 1)
				selected = "selected";
			printString.append("<option "+selected+" value="+i+">"+i+"</option> \n");	
			selected = null;
		}
		printString.append("</select> \n");
		
		// Html part for printing year in from date
		printString.append("<select name=fromYear> \n");
		for(int i=1980; i <= sysYear; i++)
		{
			String selected = "";
			if(sysYear == i)
				selected = "selected";
			printString.append("<option "+selected+" value="+i+">"+i+"</option> \n");
			selected = null;
		}
		printString.append("</select></td></tr> \n");
		
		// Html part for printing month in to date
		printString.append("<tr bgcolor='#FAF7EF' height='27%'><td width='40%' height='33' align='right'><font face='Arial, Helvetica, sans-serif' size='2'>To : &nbsp;&nbsp;</font></td>\n");
		printString.append("<td width='60%' height='33' align='left'> \n");
		printString.append("<select name=toMonth> \n");
		for(int i=0; i < 12; i++)
		{
			String selected = "";
			if(sysMonth== i)
				selected = "selected";
			printString.append("<option "+selected+" value="+(i)+">"+month[i].substring(0,3)+"</option> \n");
			selected = null;
		}
		printString.append("</select> \n");

		// Html part for printing day in to date
		printString.append("<select name=toDay> \n");
		for(int i=1; i <= 31; i++)
		{
			String selected = "";
			if(sysDay == i)
				selected = "selected";
			printString.append("<option "+selected+" value="+i+">"+i+"</option> \n");		
			selected = null;
		}

		printString.append("</select> \n");
		
		// Html part for printing year in to date
		printString.append("<select name=toYear> \n");
		for(int i=1980; i <= sysYear; i++)
		{
			String selected = "";
			if(sysYear == i)
				selected = "selected";
			printString.append("<option "+selected+" value="+i+">"+i+"</option> \n");
			selected = null;
		}
		printString.append("</select></td></tr> \n");

		// pass uid, login, dealer id as hidden field for next page reference.
		printString.append("<tr bgcolor='#FAF7EF' height='27%'><td colspan='2' align='center' height='33'> \n");
		printString.append("<input type=hidden name=login value="+login+"> \n");
		printString.append("<input type=hidden name=uid value="+uid+"> \n");
		printString.append("<input type=hidden name=trans value="+trans+"> \n");
		printString.append("<input type=hidden name=dealer_id value="+dealerID+"> \n");
		printString.append("<input type=submit value='show' onClick='return validateDate()'> \n");
		printString.append("</td></tr></table></td></tr></table> \n");
		printString.append("</form></div></body></html> \n");
		
		// return the entire html content as StringBuffer
		return printString;
	}



	public StringBuffer getDayOption(int day)
	{
		StringBuffer outBuffer = new StringBuffer();

		outBuffer.append("<option value=''>day</option>");
		for(int i=1; i <= 31; i++)
		{
			String selected = "";
			if(i == day)
				selected = "selected";
			
			outBuffer.append("<option "+ selected +" value="+i+">"+i+"</option>");
		}

		return outBuffer;
	}


	public StringBuffer getMonthOption(String month)
	{
		StringBuffer outBuffer = new StringBuffer();

		outBuffer.append("<option value=''>month</option>");
		for(int i=0; i < 12; i++)
		{
			String selected = "";
			if(MONTH_ARR[i].equalsIgnoreCase(month))
				selected = "selected";
			
			outBuffer.append("<option "+ selected +" value="+(i+1)+">"+MONTH_ARR[i]+"</option>");
		}

		return outBuffer;
	}

	public StringBuffer getYearOption(int year, int start, int end)
	{
		StringBuffer outBuffer = new StringBuffer();

		outBuffer.append("<option value=''>year</option>");
		for(int i=start; i <= end; i++)
		{
			String selected = "";
			if(i == year)
				selected = "selected";
			
			outBuffer.append("<option "+ selected +" value="+i+">"+i+"</option>");
		}

		return outBuffer;
	}


	public StringBuffer getYearOptionCurrent(int year)
	{
		Calendar sysDate = Calendar.getInstance();
		StringBuffer outBuffer = new StringBuffer();

		int start = sysDate.get(Calendar.YEAR);

		outBuffer.append("<option value=''>year</option>");
		for(int i=start; i < start+2; i++)
		{
			String selected = "";
			if(i == year)
				selected = "selected";
			
			outBuffer.append("<option "+ selected +" value="+i+">"+i+"</option>");
		}

		return outBuffer;
	}


	public boolean isEmpty(String str)
	{
		return str.trim().length() > 0 ? false : true;
	}

	public boolean isNaInteger(String str)
	{
		try
		{
			int temp = Integer.valueOf(str).intValue();
			return false;
		}
		catch(Exception e)
		{
		}
		return true;
	}

	public boolean isNaFloat(String str)
	{
		try
		{
			float temp = Float.valueOf(str).floatValue();
			return false;
		}
		catch(Exception e)
		{
		}
		return true;
	}


	public boolean isNaDouble(String str)
	{
		try
		{
			double temp = Double.valueOf(str).doubleValue();
			return false;
		}
		catch(Exception e)
		{
		}
		return true;
	}

	
	/**
	  * To get the script validateion part for From date to To date html.
	  * @return boolean depends on the validity of the login.
	  */

	public static String getDateScript()
	{
		StringBuffer printString = new StringBuffer();
		printString.append("<script language=javascript> \n");
		
		// script function to validate the date.
		printString.append("function validateDate() \n");
		printString.append("{ \n ");

		// get the form values.
		printString.append("	var fromDay   = document.form1.fromDay.options[document.form1.fromDay.selectedIndex].value; \n");
		printString.append("	var fromMonth = parseInt(document.form1.fromMonth.options[document.form1.fromMonth.selectedIndex].value) -1 ; \n");
		printString.append("	var fromYear  = document.form1.fromYear.options[document.form1.fromYear.selectedIndex].value; \n");
		printString.append("	var toDay     = document.form1.toDay.options[document.form1.toDay.selectedIndex].value; \n");
		printString.append("	var toMonth   = parseInt(document.form1.toMonth.options[document.form1.toMonth.selectedIndex].value)-1; \n");
		printString.append("	var toYear    = document.form1.toYear.options[document.form1.toYear.selectedIndex].value; \n");
		
		// check the from date is less than system date.
		printString.append("	var retValue  = dateCheck(fromDay,fromMonth,fromYear); \n");
		printString.append("	if(retValue == false) \n ");
		printString.append("	{ \n");
		printString.append("		alert('From date should be less than today'); \n");
		printString.append("		return false; \n");
		printString.append("	} \n");

		// check the to date is less than system date.
		printString.append("	retValue  = dateCheck(toDay,toMonth,toYear); \n");
		printString.append("	if(retValue == false) \n ");
		printString.append("	{ \n");
		printString.append("		alert('To date should be less than today'); \n");
		printString.append("		return false; \n");
		printString.append("	} \n");

		// check the from date is less than to date.
		printString.append("	retValue  = compareDate(fromDay,fromMonth,fromYear,toDay,toMonth,toYear); \n");
		printString.append("	if(retValue == false) \n ");
		printString.append("	{ \n");
		printString.append("		alert('From date should be less than To date'); \n");
		printString.append("		return false; \n");
		printString.append("	} \n");
		printString.append("	return true; \n ");
		printString.append("} \n");
		
		// function to compare any date with system date.
		printString.append("function dateCheck(d1,m1,y1) \n");
		printString.append("{ \n");
		printString.append("	sdate = new Date();  \n");
 		printString.append("	date1 = new Date(parseInt(y1),parseInt(m1),parseInt(d1)); \n");
		printString.append("	if(date1.getTime() > sdate.getTime()) \n");
		printString.append("		return false; ");
		printString.append("	return true; \n");
		printString.append("}");
		
		// function to compare two given dates.
		printString.append("function compareDate(d1,m1,y1,d2,m2,y2) \n");
		printString.append("{ \n");
 		printString.append("	date1 = new Date(parseInt(y1),parseInt(m1),parseInt(d1)); \n");
		printString.append("	date2 = new Date(parseInt(y2),parseInt(m2),parseInt(d2)); \n");
		printString.append("	if(date1.getTime() > date2.getTime()) \n");
		printString.append("		return false; ");
		printString.append("	return true; \n");
		printString.append("}");
		printString.append("</script> \n");
		
		return printString.toString();
	}
}
