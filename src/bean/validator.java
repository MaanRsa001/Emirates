package bean;
public class validator
{

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
	
	
	public String validString(String value,int format)
	{
		String returnval=null;
		try
		{
			value=value.trim();
			String validChar,validno,validextra=null;
			String string=new String("");
			if(value.length()>0)
			{
				validChar="abcdefghijklmnopqrstuvwxyz";
				validno="1234567890";
				validextra=".* %^&";
				value=value.toLowerCase();
				if(format==1)
				    string=new String(validChar);
				else if(format==2)
					string=new String(validno);
				else if(format==3)
					string=validChar+validno;
				else if(format==4)
					string=new String(validChar+validextra);
									
				for(int i=0;i<value.length();i++)
				{
					//char c=c.charAt(i);
					if(string.indexOf(value.charAt(i))== -1)
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
		String returnval=null;
		
		try
		{mailid=mailid.trim();
		if(mailid.length()>0)
		{
			if(mailid.indexOf("@")==-1)
			{
				returnval="Invalid";
			}
			else if((mailid.substring(0,mailid.indexOf("@"))).length()<2 || (mailid.substring(mailid.indexOf("@") + 1)).length()<7  || (mailid.substring(mailid.indexOf("@") + 1)).indexOf(".") == -1)
			{
				returnval="Invalid";
			}
    
		}
		else
				returnval="needed";
		}catch(Exception e)
		{
			returnval="needed";
		}
		return returnval;

	}

	


	public String checkDate(String strDate)
	{
		String returnVal=null;
		java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy");
		df.setTimeZone(new java.util.SimpleTimeZone(14400000,"GMT"));
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


}