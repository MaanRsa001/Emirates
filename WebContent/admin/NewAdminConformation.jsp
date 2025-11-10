<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
		String path= request.getContextPath();
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
</head>

<body>
<form name="form1" method="get" action="">
<table width="213" border="0" cellspacing="0" cellpadding="0">
	<%--@ include file="left.jsp" --%>
</tr>
</table></td>
<td width="1"></td>
<td align="left" valign="top"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
<tr>
<td width="8" height="25" class="heading">&nbsp;</td>
<td width="1"></td>
<td width="98%" class="heading"><strong>ADMIN MANAGEMENT</strong></td>
</tr>
<tr align="center">
<td colspan="3">&nbsp;</td>
</tr>
<tr align="center">
<td colspan="3"><table width="95%"  border="0" cellspacing="0" cellpadding="0">
<tr>
<td align="center" class="text">&nbsp;</td>
</tr>
<tr>
<td align="center" class="text">&nbsp;</td>
</tr>
<% 	String status="Records inserted Successfully";  %>
<tr>
<td align="center" class="text"><B><%=status %>&nbsp;&nbsp;&nbsp;</B></td>
</tr>
<tr>
<td align="center" class="text">&nbsp;</td>
</tr>
<tr align="center">
<td colspan="3"  align="center"><table width="95%"  border="0" cellspacing="0" cellpadding="0"  align="center">
<tr  align="center">
<%
	String from=(String)request.getAttribute("from");
	String rsaissuer=(String)request.getAttribute("rsaissuer");
	String paths="";
	if(rsaissuer==null)
		paths="newUser.jsp";
	else
		paths="newRSAUser.jsp";
	
%>
<td height="32" align="right" valign="middle" align="center"> 
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
<a href="<%=paths%>">
<img src="<%=path%>/images/Proceed.jpg" ></a>&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; 
</center></td>
</tr>
</table></td>
</tr>       
</table>
</td>
</tr>
</table></td>
</tr>
</table></td>
</tr>
</table></td>
</tr>
 
</table>
</form>
</body>
</html>
