
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import = "proj.sql.QueryBuilder,java.util.*,java.sql.*" %>
<%@ page import = "proj.date.DateFunction" %>


<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String usrModeMain=request.getParameter("userLoginMode")==null?"":request.getParameter("userLoginMode");	
%>
<jsp:useBean id="log" class="com.maan.session.LoginSession" >
	<jsp:setProperty name="log" property="*" />
</jsp:useBean>


<jsp:useBean id = "log1" class = "com.maan.admin.admLoginNew">
	<jsp:setProperty name = "log1" property = "*" />
	
</jsp:useBean>
<head>
<link href="<%=path%>/css/style.css" rel="stylesheet" type="text/css">
	
	</head>
	
<table width="100%" cellspacing="0" cellpadding="0">  
<tr>
              <td height="150" align="center" valign="top">
              <table width="100%" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td height="150" align="center" valign="top">
                    <table width="90%" cellspacing="0" cellpadding="0" align="center">
  <tr>
	<td colspan="3"></td>
  </tr>
  <tr>
	<td colspan="3" background="<%=path%>/images/curvebottom.gif" height='1'></td>
  </tr>
  <tr>
  <td colspan="2" align="right" width="20"></td>
  <td  height="20"></td></tr>
  <tr>
    <td  width="20" align="right"></td>
    <td width="216" align="center" valign="top" ><img src="<%=path%>/images/logo.jpg" ></td>
	<td width=700>&nbsp;</td>
  </tr>
  <tr>
  <td colspan="2" width="20" align="right"></td>
  <td height="20"></td></tr>
  <%--<tr>
	<td colspan="3" background="<%=path%>/images/curvebottom.gif" height='1'></td>
  </tr>
--%></table>
                    </td>
                  </tr>
              </table></td>
            </tr> 
         </table> 
		 
<%
String gid, user, chlnk;
gid = user = chlnk = "";
try
{
	java.util.Enumeration enum1 = request.getParameterNames();
	while(enum1.hasMoreElements())
	{
		String s = (String)enum1.nextElement();
		if(s.equals("gid")) gid = request.getParameter(s);
		if(s.equals("user")) user = request.getParameter(s);
	}
}
catch(Exception e)
{
	
	response.sendRedirect("/admin/error.jsp?" + e);
}

//Modified By Rajesh
	String[][] b = new String[0][0];
	b = log1.getUserandStatus(user);

session.setAttribute("userid",b[0][1].trim());

if((b[0][0].trim().equalsIgnoreCase("f")) || (b[0][0].trim().equalsIgnoreCase("fl")))
{
	
	response.sendRedirect("firstloginnew.jsp?user=" + user);
}
else
if((b[0][0].trim().equalsIgnoreCase("L")) || (b[0][0].trim().equalsIgnoreCase("loc")))
{
%>
	
	
	<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
	<form name="frm11" method="post" action="login/logoutnew.jsp?user=<%= user %>">
	
<%
	
	%>
	<table  class="blueborder" border='0' cellspacing='0' cellpadding='0' align='center' width='400'><tr> <td><table width='100%' cellspacing='1' cellpadding='0' align='center'><tr  class="blueborder"><td   class="blueborder" >Information</td></tr><tr><td  class="blueborder" align='center'>Please contact your administrator for assistance and resetting of  password.</td></tr></table></td></tr></table>
	<%
	session.invalidate();
	out.println("<br><table border='0' align='center'><tr><td><input type = 'image' alt = 'Logout' src = '/sell/images/submit_btn.gif' width = '75' height = '23' vspace = '6'></td></tr></table>");

}
else
if(b[0][0].trim().equalsIgnoreCase("Y"))
{
	
	try
	{
		Calendar sysDate = Calendar.getInstance();
		DateFunction date = new DateFunction();
		String rsdt = log1.getPassdate(user);
				
		int i=(rsdt).indexOf(" ");
		
		String str=(rsdt).substring(0,i);
		int	dtdiff = (int)date.getDayDifference(date.getCalendar(date.changeDateFormatDMY(str)), sysDate);
		log.setUser(user);
		String usertype="";
		boolean userTypeFlag = false;
		try
		{
			//usertype=log.getUserType();
			userTypeFlag = log.isAdminType();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		if(dtdiff >= 0)
		{
			
			out.println("<br><br><br><br><br<br><table width='500' border='1' cellspacing='0' cellpadding='1' align='center'><tr><td ><table width='100%' border='1' cellspacing='0 cellpadding='0' align='center'><tr><td height='25' colspan='3'><span class='heading'>Information</span> </td></tr>");
			//admin/chpasnew.jsp?user=" + user + "
			out.println("<tr><td align='center'><a class='two' href='"+path+"/admin/chpasnew.jsp?user=" + user + "'><b><font color='#FF0391'>Click here</font></b></a> <b>to change password. You cannot proceed further without changing the password.</b>");
			
			out.println("</tr><tr><td height='10' colspan='3' bgcolor='#FFFFFF'></td></tr> </table></td></tr></table>");
		
		}
		else if(userTypeFlag)
		{
			session.setAttribute("userLoginMode",request.getParameter("userLoginMode")==null?"":request.getParameter("userLoginMode"));
			response.sendRedirect("homeAdmin.action");
		}
		else
		{
			session.setAttribute("userLoginMode",request.getParameter("userLoginMode")==null?"":request.getParameter("userLoginMode"));
			session.setAttribute("dtdiff",""+dtdiff);
			response.sendRedirect("HomeUser.action");
		}
	}

	catch(Exception e)
	{
		out.println("Exception is : " + e);
	}
}
else
{
	out.println("Sorry. There is a problem with the system. Please contact your administrator.");
}
%>

