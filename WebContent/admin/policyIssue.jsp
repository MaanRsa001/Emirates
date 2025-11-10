<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="header.jsp" %>
<head>
<title>Emirates</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="../css/style.css" rel="stylesheet" type="text/css">
	<style type="text/css">
	<!--
		.style1 {color: #FF0000}
	-->
	</style>
</head>

<jsp:useBean id= "cus" class = "com.maan.admin.AdminBean">
<jsp:setProperty name= "cus"   property = "*"/>
</jsp:useBean>

<jsp:useBean id= "cus1" class = "com.maan.session.LoginSession">
<jsp:setProperty name= "cus1"   property = "*"/>
</jsp:useBean>

<body>
<form name="personal" method="post" action="">
<table width="213" border="0" cellspacing="0" cellpadding="0">
<%--@ include file="left.jsp" --%>
</tr>
</table>
</td>
<td width="1"></td>
<td align="left" valign="top">
<table width="100%"  border="0" cellspacing="0" cellpadding="0">
<tr> 
<td width="8" height="25" class="dkbgyellow" bgcolor="#FCC721">&nbsp;</td>
<td width="1"> 
</td>
<td width="98%" class="mdbgyelllow"><strong>Policy Issued</strong></td>
</tr>
<tr align="center">
<td colspan="3">&nbsp;</td>
</tr>
<tr align="center">
<td colspan="3">
<table width="100%"  border="0" cellspacing="1" cellpadding="0" class="blueborder">
<tr>
<td align="center" class="ltbgyellow">
<%//@ include file="admin_sub_menu_reports.jsp" %>
<table width="100%"  border="0" cellspacing="0" cellpadding="0">
<tr align="center">
<td colspan="3">
<table>
<tr align="left">
<td colspan="3">
</td>
</tr>
</table>
</td>
</tr>
<tr align="center">
<td colspan="3">
<table width="70%">
<%
if(request.getMethod().equals("POST"))
{
	if(request.getParameter("userID")!=null&&request.getParameter("userID").equals("0"))
	{
%>
<tr>
<td align="center"><font color="red">Please Select The Broker</font></td></tr>
<%
	}	 
}
%>
<tr class="blueborder" align="center" height=25>
<td colspan=2>POLICY ISSUED</td>
</tr>
<tr height="35">
<td class="blueborder">Select&nbsp;Broker&nbsp;&nbsp;&nbsp;<select name="userID">
<option value="0">Select</option>
		<%
			String[][]  Broker=cus.getBrokerUserId(actualBranch);
			for(int i=0;i<Broker.length;i++)
			{
		%>
				<option value="<%=Broker[i][0]%>"><%=Broker[i][1]%>(<%=Broker[i][0]%>)</option>
		<%	}	%>
			</select>
	   </tr>
</table>
<table>
<tr align="center">
<td height="1" colspan="3"></td>
</tr>
<tr align="center">
<td colspan="3"><table width="95%"  border="0" cellspacing="0" cellpadding="0">
<tr>
<td height="32" align="center" valign="middle" class="medblue">&nbsp;&nbsp;&nbsp;
<input name="image" type="image"  src='../images/Proceed.gif' onclick="document.personal.submit()" >
</td>
</tr>
</table>
<%
					
String BROKER="";						
if(request.getMethod().equalsIgnoreCase("POST"))
{
	try
	{
	BROKER=request.getParameter("userID")==null?"":request.getParameter("userID");
	if(!BROKER.equals("")&&!BROKER.equals("0"))
	{
		session.removeAttribute("user");
		session.setAttribute("user",BROKER);
		session.setAttribute("loginPersonId",BROKER);
		session.setAttribute("mode","s");
		session.setAttribute("user1","Broker");
		session.setAttribute("usertype","Broker");
		session.setAttribute("sessionCheckCondition","Yes");
		//session.setAttribute("userid","1");
		//response.sendRedirect("ViewQuote_B2B.jsp");
	%>

<script language="JavaScript">

	document.personal.action="../login/ProductSelection.jsp";
	document.personal.submit();

</script>

<%
}						
}
	catch(Exception	e)
	{
		
	}
}
else
{
	session.setAttribute("RSAUSER",(String)session.getAttribute("user"));
%>
<%
	}	
%>
</form>
</body>
</html>
