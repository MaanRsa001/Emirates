
<%@ page import="java.util.*" %>
<%@ page import = "proj.sql.QueryBuilder,java.util.*,java.sql.*" %>


<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String usrModeHOMEADMINPASS=(String)session.getAttribute("userLoginMode")==null?"":(String)session.getAttribute("userLoginMode");
com.maan.DBCon.DBConnectionStatus.statusStatic=usrModeHOMEADMINPASS;

%>

<jsp:useBean id = "log1" class = "com.maan.admin.admLoginNew">
	<jsp:setProperty name = "log1" property = "*" />
	<jsp:setProperty name = "log1" property = "out" value = "<%= response.getWriter() %>" />
</jsp:useBean>
<head>
<link href="<%=path%>/css/style.css" rel="stylesheet" type="text/css">
</head>
<html>

<table width="100%" cellspacing="0" cellpadding="0">  
<tr>
<td height="150" align="center" valign="top">

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
              </table>

</td>
</tr> 
</table> 
<%
	String pass1="";
	String pass2="";
	String mode="";
	String nuuser="";
	String errmsg="";
	String flag="";
	int err, j, k ;
	err = 0;j =0; k = 0;

try
{
	Enumeration enumm = request.getParameterNames();
		while(enumm.hasMoreElements())
		{
			String s = (String)enumm.nextElement();
			if(s.equals("mode")) mode = request.getParameter(s);
			if(s.equals("pass1")) pass1 = request.getParameter(s);
			if(s.equals("pass2")) pass2 = request.getParameter(s);
			if(s.equals("user")) nuuser = request.getParameter(s);
			if(s.equals("flag")) flag = request.getParameter(s);
		}
}

catch(Exception e)
{
	out.println("Exception is : " + e);
}

if(mode.equalsIgnoreCase("edit"))
{
	nuuser=(String)session.getAttribute("user");
	if ((pass1.trim().equalsIgnoreCase("")) && (pass2.equalsIgnoreCase("")))
	{
		errmsg = "Both Fields required.";
		err = err + 1;
	}
	else if (!pass1.equals(pass2))
	{
		errmsg = "Passwords do not match.";
		err = err + 1;
	}
	else if(pass1.length() < 7 || pass2.length() < 7 )
	{
		errmsg = "Password Length Minimum 7 Characters";
		err = err + 1;
	}
	else if((pass1.length() >= 7) && (pass2.length() >= 7) )
	{
		for(int i=0;i<pass1.length(); i++)
		{
			Character c = new Character(pass1.charAt(i));
			char c1 = pass1.charAt(i);
			//if(c.isLetter(c1))
			//if(c.isUpperCase(c1))
			if(c.isLowerCase(c1))
			{
				j = 1;
			}
			else if (c.isDigit(c1))
			{
				k = 1;
			}
		}
		if ((j < 1) || (k < 1))
		{
			errmsg = "Password should contain both LowerCase Letters and Digits.";
			err = err + 1;
		}
			// Password chcking last 4 Timess
		if (flag.equalsIgnoreCase("first") && errmsg.equals("") && errmsg.length()==0 )
		{
			if(log1.chkpas(nuuser,pass1,"checking"))
			{
				System.out.println("Change Password Checking JSP");
				errmsg = "Password provided is currently invalid.<br>";
				flag="second";
				err = err + 1;
			}
			else
				flag ="Three";
		}
	}
	else
	{

	}
	if ((mode.equalsIgnoreCase("edit")) && (err == 0) && (flag.equalsIgnoreCase("Three")))
	{
		try
		{
			log1.cpas(nuuser,pass1,"royal");
		}
		catch(Exception e)
		{
			out.println("Exception : " + e);
		}
		response.sendRedirect(path+"/admin/chpasnew1.jsp");	
	}
	else
	{
%>
<body onLoad="document.pasfrm.pass1.focus();">
<form action="" name="pasfrm" method="post">
<input type="hidden" value="<%= mode %>" name="mode">
<input type="hidden" value="first" name="flag">

<table width='450' border='0' cellspacing='0' cellpadding='1' align='center'><tr><td >
<table border='0' cellspacing='0' cellpadding='0' width='100%' height='137' align='center'>
<tr><td width="45%" colspan="2" align="left">&nbsp;&nbsp;&nbsp;<span class="heading">Change Password</span></td></tr>
<tr><td class="blueborder" align="Center" colspan="2">&nbsp;<font color="#FF0391"><%= errmsg %></font></td>
</tr>
<td bgcolor="#FFFFFF" height='25' align='center'>Enter New Password</td>
<td bgcolor="#FFFFFF"><input type="password" value="<%= pass1 %>" name="pass1" ></td></tr>
<tr><td bgcolor="#FFFFFF" height='25' align='center'>Re - enter Password</td>
<td bgcolor="#FFFFFF"><input type="password" value="<%= pass2 %>" name="pass2" ></td></tr>
<tr><td bgcolor="#FFFFFF" colspan='2' height='25' align='center'>
<input type = "image" alt = "Submit" src = "<%=path%>/images/Submit.jpg"  onClick="return cmod()">&nbsp;&nbsp;&nbsp;
<input type = "image" alt = "Submit" src = "<%=path%>/images/Cancel.jpg"  onClick="return proceed()"></td></tr>
</table></td></tr></table>
</form></body></html>
<%
	}
}
else if(!mode.equalsIgnoreCase("edit"))
{
%>

<body onLoad="document.pasfrm.pass1.focus();">
<form action="" name="pasfrm" method="post">
<input type="hidden" value="first" name="flag">
<input type="hidden"  name="mode">
<table width='450' border='0' cellspacing='0' cellpadding='1' align='center'><tr><td >
<table border='0' cellspacing='0' cellpadding='0' width='100%' height='137' align='center'>
<tr><td align="center" colspan="2">&nbsp;&nbsp;&nbsp;<span class="heading">Change Password</span></td>

</tr>
<tr>
<td bgcolor="#FFFFFF" height='25' align='center'>Enter New Password</td>
<td bgcolor="#FFFFFF"><input type="password" value="<%= pass1 %>" name="pass1" class="t"></td></tr>
<tr><td bgcolor="#FFFFFF" height='25' align='center'>Re - enter Password</td>
<td bgcolor="#FFFFFF"><input type="password" value="<%= pass2 %>" name="pass2" class="t"></td></tr>
<tr><td bgcolor="#FFFFFF" colspan='2' height='25' align='center'>
<input type = "image" alt = "Submit" src = "<%=path%>/images/Submit.jpg"  onClick="return cmod()">&nbsp;&nbsp;&nbsp;
<input type = "image" alt = "Submit" src = "<%=path%>/images/Cancel.jpg"  onClick="return proceed()"></td></tr>
</table></td></tr></table>
</form></body></html>
<%
}
%>
<script language="javascript">
function cmod()
{
	document.pasfrm.mode.value = "edit";
	document.pasfrm.action="<%=path%>/admin/chpasnew.jsp";
	document.pasfrm.submit();
	return false;
}

function proceed()
{
	document.pasfrm.mode.value = "edit";
	document.pasfrm.action="<%=path%>";
	document.pasfrm.submit();
	return false;
}
</script>