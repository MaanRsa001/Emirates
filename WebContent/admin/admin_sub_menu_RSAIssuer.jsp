<%
StringBuffer urlfrom=request.getRequestURL();
//out.println("xzczxczxczxczXC>>>>>>>>>>>>>>>>>>>>"+urlfrom.substring(urlfrom.lastIndexOf("/"),urlfrom.length()));
String url=""+urlfrom.substring(urlfrom.lastIndexOf("/"),urlfrom.length());

%>

<table width="100%"  border="0" cellspacing="0" cellpadding="0">
<tr> 
<td colspan="5" >

<table width="100%" border="0" cellspacing="1" cellpadding="2" style="align:justify; font-family:Arial;font-size:12px;font-weight:normal;"
>
<tr  class="royamenuhead">
	<td width="25%"  <%if(url.equals("/newRSAUser.jsp")){%>class="heading"<%}%>><a href="../admin/newRSAUser.jsp">Emirates ISSUER ID CREATION<a> </td>
	<td width="25%"   <%if(url.equals("/RSAIssuerassignPassword.jsp")){%>class="heading"<%}%>><a href="../admin/RSAIssuerassignPassword.jsp">ASSIGN NEW PASSWORD </a></td>
	<td width="25%" <%if(url.equals("/RSAIssuerusers1.jsp")){%>class="heading"<%}%>><a href="../admin/RSAIssuerusers1.jsp">ACTIVATION AND DEACTIVATION</a></td>
</tr>
</table>
<%
	session.setAttribute("adminUser",""+session.getAttribute("user"));
%>