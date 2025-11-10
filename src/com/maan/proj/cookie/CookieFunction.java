package com.maan.proj.cookie;

import javax.servlet.http.*;

/**
  * <ul>
  * <li> Author  : Manikandan.
  *	<li> Project : Common file for all projects.
  * <li> Purpose : To handle cookies related request.	  
  * </ul>
  */

public class CookieFunction
{
	/**
	  * Check the user's browser supports cookies.
	  * @param req pass user's request as an parameter.
	  * @return boolen.
	  */

	public boolean checkCookie(HttpServletRequest req)
	{
		// get the reference for HttpSession.
		HttpSession session = req.getSession(true);
        
		// get the support of cookie in user's browser.
		boolean id_cookie = req.isRequestedSessionIdFromCookie();
		return id_cookie;
	}

	/**
	  * To create an new cookie with session Id.
	  * @param res pass user's response to set the cookie with response.
	  * @return boolen.
	  */

	public boolean createCookie(String uid, HttpServletResponse res)
	{
		try
		{
			// create a new cookie with name and session id as a value.
			Cookie c = new Cookie("AccurateTrussBass",uid);
			
			// set the expiry time for the cookie.
			c.setMaxAge(1000*60*60*2);
			
			// add the cookie to the response object.
			res.addCookie(c);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	
	/**
	  * To get the user id from the cookie 
	  * @param req pass user's request as a parameter.
	  * @return uid as string.
	  */

	public String getUserID(HttpServletRequest req)
	{
		// check for the supportivity of the user's browser for cookies.
		if(checkCookie(req))
		{
			String name,value;
			value = name = "";
		
			// get the cookie from the request.
			Cookie[] cookies = req.getCookies();
			if(cookies != null)
			{
				for(int i = 0;i < cookies.length;i++)
				{
					// get cookies name, value which were setted by our site.
					if(cookies[i].getName().equals("AccurateTrussBass"))
					{
						name = cookies[i].getName();
						value = cookies[i].getValue();
					}
			    }
			}

			// if the cookies available return the cookie
			if(value.length() > 0)
			{
				return value;
			}
			else // else show the message.
			{
				return "No cookie was set";
			}
		}
		else // if not supported show message.
		{
			return "Your browser not support Cookies";
		}
	}
}
