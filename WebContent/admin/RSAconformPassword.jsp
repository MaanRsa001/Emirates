<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="com.maan.common.util.StringUtil" %>
<%
	String path = request.getContextPath();
%>
<%@ include file="header.jsp" %>
<html>
<head>
<title>Emirates</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="../css/style.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>

<jsp:useBean id="broker" class="com.maan.admin.BrokerCreationBean">
<jsp:setProperty name="broker" property="out" value="<%= response.getWriter() %>" /> 
</jsp:useBean>

</head>
<body>
<form name="form1" method="get" action="">
<table width="213" border="0" cellspacing="0" cellpadding="0">
	<%--@ include file="left.jsp" --%>

</tr>
</table></td>
<td width="1"></td>
<td align="left" valign="top">
<table width="100%"  border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="8" height="25" class="heading" bgcolor="#FCC721">&nbsp;</td>
            <td width="1"></td>
                  <td width="98%" class="heading"><strong>ADMIN INFORMATION</strong></td>
          </tr>
<tr align="center">
            <td colspan="3">&nbsp;</td>
          </tr>
          <tr align="center">
            <td colspan="3">
<table width="95%"  border="0" cellspacing="0" cellpadding="0" align="center">
<tr align="center">
<td colspan="3">&nbsp;</td>
</tr>

<%
	String brokerId=request.getParameter("brokerId")==null?"":request.getParameter("brokerId");
	String password=request.getParameter("password")==null?"":request.getParameter("password");
	String repassword=request.getParameter("repassword")==null?"":request.getParameter("repassword");
	String rsaissuer=request.getParameter("rsaissuer")==null?"":request.getParameter("rsaissuer");
	String error="";

if(password.equals(""))
{
	error="Enter Password";
}
else if(password.length() < 7)
{
	error="Password should be minimum 7 characters";
}
else if(!password.equalsIgnoreCase(repassword))
{
	error="Passwords Not matched";
}
else if(!StringUtil.checkSpecial(password))
{
	error="Password should contain special characters";
}
else if(!broker.validPassword(password))
{
	error=error+"<BR>*Password should be alphanumeric";
}

String mailId="";
String msg="";
if(error.length()>0)
{
	msg	 = error;
}
else
{
	if(broker.changePassword(brokerId,password))
	{
		mailId=broker.getMailId(brokerId);
		msg = "PassWord Changed Successfully";
	}
}
%>
<input type="hidden" name="brokerId" value="<%=brokerId%>"/>
    <tr align="center">
    <td colspan="3"><table width="100%"  border="0" cellspacing="1" cellpadding="0">
    <tr>
    <td align="center">
	<% 
		String paths="";
		if(rsaissuer.equalsIgnoreCase("rsaissuer"))
		{
			paths="RSAIssuerassignPassword.jsp";
	%>
			<%@ include file="admin_sub_menu_RSAIssuer.jsp" %>
	<%
		}
		else
		{
			paths = "RSAassignPassword.jsp";
	%>
			<%@ include file="admin_sub_menu_RSA.jsp" %>
	<%
		}
	%>
	</td>
    </tr>
    <tr>
    <td align="center" class="text">&nbsp;</td>
    </tr>
    <tr>
	<td align="center" class="text"><B>&nbsp;<%=msg%></B></td>
    </tr>
    <tr>
    <td align="center" class="text">&nbsp;</td>
    </tr>
    <tr align="center">
    <td colspan="3" class="text" align="center"><table width="95%"  border="0" cellspacing="0" cellpadding="0" align="center">
    <tr class="text" align="center">
    <td height="32" align="center" valign="middle" class="text"> 
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
    <a href="<%=paths%>">
	<img src="<%=path%>/images/Proceed.gif"></a>
	&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;
    </td>
    </tr>
    </table></td>
    </tr>       
    </table>
    </td>
    </tr>
    </table></td>
    </table></td>
    </tr>
    </table></td>
    </tr>
</table>
</form>
</body>
</html>
