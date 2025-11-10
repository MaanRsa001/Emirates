package com.maan.common.util;
import com.maan.services.util.ValidationFormat;
public class dataCollection
{
	public String[][] titleCollection()
	{
		String[][] title=new String[3][1];
		title[0][0]="Mr";
		title[1][0]="Mrs";
		title[2][0]="Dr";
		return title;

	}

	public String[][] nationalityCollection()
	{
		String[][] title=new String[3][1];
		title[0][0]="Indian";
		title[1][0]="American";
		title[2][0]="German";
		return title;
	}
	
	public String[][] countryCollection()
	{
		String[][] title=new String[3][1];
		title[0][0]="India";
		title[1][0]="America";
		title[2][0]="Germa";
		return title;
	}

	public String[][] emirateCollection()
	{
		String[][] title=new String[3][1];
		title[0][0]="Dubai";
		title[1][0]="Ajman";
		title[2][0]="Abu Dhabai";
		return title;
	}

	
	
	public String validInteger(String value)
	{
		String returnval=null;
		try
		{
			System.out.println("--"+Integer.parseInt(value));
		}catch(Exception e)
		{
			return "Invalid";
		}
		return returnval;
	}
	
	
	public String validString(String value,boolean format)
	{
		String returnval=null;
		try
		{
			value=value.trim();
			String validChar=null;
			if(value.length()>0)
			{
				value=value.toLowerCase();
				validChar="-abcdefghijklmnopqrstuvwxyz ";
				if(format)
				validChar="1234567890.";
				for(int i=0;i<value.length();i++)
				{
					//char c=c.charAt(i);
					if(validChar.indexOf(value.charAt(i))== -1)
					returnval="Invalid";
				}
			}
			else
				returnval="needed";
		}catch(Exception e)
		{
			return "needed";
		}
		return returnval;
	}

	public String validLength(String value,int len)
	{
		String returnval=null;
		try
		{
			value=value.trim();
			if(value.length()>=len)
			{
				
			}
			else
				returnval="needed";
		}catch(Exception e)
		{
			return "needed";
		}
		return returnval;
	}

	public String emailValidate(String mailid)
	{
		ValidationFormat valid = new ValidationFormat();
		boolean flag = true;
		String returnval=null;
		try
		{
			mailid=mailid.trim();
			if(mailid.length() > 0 )
			{
				flag = valid.EMailValidation(mailid);
				if(flag){
					returnval="Invalid";
				}
			}
			else{
				returnval="needed";
			}
		}
		catch(Exception e)	{
			returnval="needed";
		}
		return returnval;
	}

	public String checkDate(String strDate)
	{
		String returnVal=null;
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

public String checkPickerDate(String strDate)
	{
		String returnVal=null;
		java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("dd-MM-yyyy");
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


public boolean validNumber(String value)
{
	boolean returnval=true;
	try
	{
		value=value.trim();
		String validChar=null;
		if(value.length()>0)
		{
			
			validChar="1234567890.";
			for(int i=0;i<value.length();i++)
			{
				//char c=c.charAt(i);
				if(validChar.indexOf(value.charAt(i))== -1)
				returnval=false;
			}
		}
		else
			returnval=true;
	}catch(Exception e)
	{
		return false;
	}
	System.out.println("validate status---------------------------------------------------------"+returnval);
	return returnval;
}



}