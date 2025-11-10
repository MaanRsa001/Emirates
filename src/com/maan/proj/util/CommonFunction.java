package com.maan.proj.util;

import proj.sql.*;
import proj.date.*;

import java.sql.*;
import java.util.*;

/**
  * <ul>
  * <li> Author  : Manikandan.
  *	<li> Project : Common file for all projects.
  * <li> Purpose : To handle common functionalities like session validation, error, header, footer etc.
  * </ul>
  */

public class CommonFunction implements Constant
{
	
	public final int MAX_SESSION  = 30;
	
	/**
	  * To check the validity of the uid.
	  * @param uid pass the user id as String.
	  * @param SMT statement object for execute the SQL queries.
	  * @return StringBuffer.
	  */

	public StringBuffer isValidUID(String uid, Statement SMT) 
	{
		String query = "";
		
		try
		{
			// Check the availability of the uid in session_master.
			query = "select date_format(access_time,'%d/%m/%Y') from session_master where session_id = '"+uid+"'";
			SqlFunction sql = new SqlFunction();
			
			// if the record is available, return empty StringBuffer.
			//if(sql.getRecordCount(query,SMT,1) > 0)

			String retValue[][] = new String[0][0];
			retValue = sql.getResultSet(query,SMT,1);
		
			// if not available, show the UID Error page.
			if(retValue.length == 0)
			{
				return new StringBuffer(printUIDError().toString());	
			}
			else
			{
				query = "select session_id from session_master where unix_timestamp(sysdate())-unix_timestamp(access_time) < "+(MAX_SESSION * 60)+" and session_id = '"+uid+"'";
				
				retValue = sql.getResultSet(query,SMT,1);
				/*DateFunction date	= new DateFunction();
				Calendar sysDate	= date.getSystemDate();
				Calendar lastAccess = date.getCalendar(retValue[0][0]);
				//long difference		= date.getInterval(lastAccess, sysDate);
				long difference		= sysDate.getTime().getTime() - lastAccess.getTime().getTime(); */
				
				//if((difference / date.MINUTE_FACTOR) <  MAX_SESSION)
				if(retValue.length > 0)
				{
					query = "update session_master set access_time = sysdate() where session_id = '"+uid+"'";
					SMT.executeUpdate(query);
					return new StringBuffer();
				}
				else
				{
					CommonFunction common = new CommonFunction();
					String replaceDealerInfo[][] = { {"%%error%%","Your session is invalid"},
													 {"%%caption%%","Home"},
													 {"%%form_action%%","http://www.accuratetrussbrace.com/cgi-bin/home.pl"}
												   };
					return common.printBody("/html/session.htm", replaceDealerInfo);
				}
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
		return (new HtmlReader().replaceValue(PATH+"/html/common.htm",replaceArray));
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
		return (new HtmlReader().replaceValue(PATH+"/html/"+fileName,replaceArray));
	}


	/**
	  * To print the admin header part.
	  * @param replaceArray an Array contains read and replace part.
	  * @return StringBuffer of admin Header file.
	  */

	public StringBuffer printAdminHead(String replaceArray[][])
	{
		// read the header file using HtmlReader class and return the content as StringBuffer.
		return (new HtmlReader().replaceValue(PATH+"/html/admin_head.htm",replaceArray));
	}

	
	/**
	  * To print the admin header with dynamic content of head strip.
	  * @param uid pass uid as a String.
	  * @param login pass login as a String.
	  * @param SMT statement object for execute the SQL queries.
	  * @return StringBuffer of admin Header file.
	  */

	public StringBuffer printAdminHead(String uid, String login, Statement SMT)
	{
		// to get the rights for the particular user.
		
		try
		{
			if(!checkAdminLogin(uid,login,SMT))
				return new StringBuffer("\n");
		}
		catch(Exception e)
		{
			return new StringBuffer("\n");
		}

		String query = "SELECT username,replace(rights,'~',',') FROM login_master WHERE username = '"+login+"'";
		SqlFunction sql = new SqlFunction();
		String rights[][] = new String[0][0];
		try
		{
			rights = sql.getResultSet(query,SMT,2);
		}
		catch(Exception e)
		{
			return new StringBuffer("Exception : "+e);
		}

		// if no rights are available, show error.
		if(rights.length <= 0)
			return new StringBuffer("Invalid");
		
		// get the corresponding name and links for each rights.
		query = "SELECT option_items,link, category FROM options WHERE option_id in ("+rights[0][1]+") and status='A' order by category";
		String options[][] = new String[0][0];
		
		try
		{
			options = sql.getResultSet(query,SMT,3);
		}
		catch(Exception e)
		{
			return new StringBuffer("Exception : "+e);
		}

		// Construct a html table for the top strip of rights.
		StringBuffer title = new StringBuffer();
		
		title.append("<table width='100%' border='0' cellspacing='0' cellpadding='0'> \n");

		/* By default each group has four items.	
		   counter to check whether four items are printed for each group.*/
		int count = 1;

		// untill the all rights printed
		for(int i=1; i <= options.length; i++)
		{
			title.append("<tr>");
			
			//set the link for each rights.
			String link1 = "<td bgcolor='#F4EBDD'>&nbsp;&nbsp;<img src='/images/bull2.gif' width='12' height='12'>&nbsp;</td><td bgcolor='#F4EBDD'><a href='"+options[i-1][1]+"?login="+login+"&uid="+uid+"'><font face='Arial, Helvetica, sans-serif' color='#000000' size='1'>";
			String link2 = "</a>";

			// if the link is not available, consider this as a table head.
			if(options[i-1][1] != null && options[i-1][1].equalsIgnoreCase(""))
			{
				link1 = "<td bgcolor='#FFCC00' height='17' colspan='2'>&nbsp;&nbsp;<b><font face='verdana','Arial, Helvetica, sans-serif' color='#000000' size='1'>";
				link2 = "</b>";
			}

			title.append(link1+options[i-1][0]+"</font>"+link2+"</td></tr> \n");
		
			// check for the last right and current right is in same group.
			if(i < options.length && !options[i-1][2].equalsIgnoreCase(options[i][2]))
			{
				// if previous and current right are not in same group, put empty space if four items are not formed in a group.
				while(count % 6 != 0)
				{
					title.append("<td bgcolor='#F4EBDD'>&nbsp;</td><td bgcolor='#F4EBDD'>&nbsp;</td></tr>");
					count++;
				}
				title.append("</table></td><td><table width='100%' border='0' cellspacing='0' cellpadding='0'> \n");
			}
			// if it is a last option, print the option then close the table.
			else if(i == options.length)
			{
				while(count % 6 != 0)
				{
					title.append("<td bgcolor='#F4EBDD'>&nbsp;</td><td bgcolor='#F4EBDD'>&nbsp;</td></tr>");
					count++;
				}
				title.append("</table> \n");
			}
			count++;
		}
		
		// read and replace the admin_head.htm file with the content of dynamically generated top strip.
		String replaceArray[][] = { {"%%uid%%", uid},
									{"%%login%%", login},
									{"%%title%%",""+title},
									{"%%matter%%","&nbsp"}
								  };

		// return the replaced admin_head.htm.
		return (new HtmlReader().replaceValue(PATH+"/html/admin_head.htm",replaceArray));
	}


	/**
	  * To print the footer par from footer.htm file.
	  * @param replaceArray an Array contains read and replace part.
	  * @return StringBuffer of footer file.
	  */

	public StringBuffer printFooter(String replaceArray[][])
	{
		// read the footer file using HtmlReader class and return the content as StringBuffer.
		return (new HtmlReader().replaceValue(PATH+"/html/footer.htm",replaceArray));
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
									{"%%form_action%%","http://www.accuratetrussbrace.com/cgi-bin/accurateadmin/login.pl"}
								  };
		StringBuffer returnValue = new StringBuffer("<br> \n");

		// read the error page using HtmlReader class and return the content as StringBuffer.
		returnValue.append(readHtml.replaceValue(PATH+"/html/session.htm",replaceArray));
		
		return returnValue;
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
	  * @param SMT statement object for execute the SQL queries.
	  * @return boolean depends on the validity of the login.
	  */

	public boolean checkLogin(String uid, String login, Statement SMT) throws Exception 
	{
		SqlFunction sql = new SqlFunction();

		// get the login for the particular uid from login_info table.
		String query = "select login from login_info where uid='"+uid+"' and status ='Y'";
		String dbLogin = sql.getResultSet(query,SMT);

		// if the login name is equals to the given login, return true.
		if(dbLogin.trim().equalsIgnoreCase(login.trim()))
			return true;
		
		// if both are not equal return false.
		return false;
	}

	
	/**
	  * To Check the admin login name is valid for particular session id.
	  * @param uid pass the user id as a String.
	  * @param login pass the login as a String.
	  * @param SMT statement object for execute the SQL queries.
	  * @return boolean depends on the validity of the login.
	  */

	public boolean checkAdminLogin(String uid, String login, Statement SMT) throws Exception 
	{
		SqlFunction sql = new SqlFunction();
		
		// get the login for the particular uid from login_admin table.
		String query = "select user_name from login_admin where uid='"+uid+"' and status='Y'";
		String dbLogin = sql.getResultSet(query,SMT);
		
		// if the login name is equals to the given login, return true.
		if(dbLogin.equalsIgnoreCase(login))
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
	  * @param SMT statement object for execute the SQL queries.
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
