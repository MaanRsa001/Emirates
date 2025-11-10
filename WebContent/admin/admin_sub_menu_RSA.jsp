<%
	StringBuffer urlfrom=request.getRequestURL();
	String url=""+urlfrom.substring(urlfrom.lastIndexOf("/"),urlfrom.length());
%>

<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color: #FFFFFF;
}
a:hover {
	color: #003366;
	text-decoration: none;
}
a:visited {
	text-decoration: none;
	color: #FFFFFF;
}
a:visited:hover {
	text-decoration: none;
	color: #000000;
}
a:active {
	color: #003366;
	text-decoration: none;

}
-->
</style>
<table width="100%"  border="0" cellspacing="0" cellpadding="0">
<tr> 
<td colspan="5" >

<table width="100%" border="0" cellspacing="1" cellpadding="2">
<%	
	if(url.equals("/newMenuCreation.jsp")) 
	{
%>
	<tr class="royamenuhead">
		<td width="25%" class="bottomtext"><a href="../admin/newMenuCreation.jsp">MENU CREATION</a> </td>
		<td width="25%" ><a href="../admin/newUser.jsp">ADMIN ID CREATION</a> </td>
		<td width="25%" ><a href="../admin/RSAassignPassword.jsp">ASSIGN NEW PASSWORD </a></td>
		<td width="25%" ><a href="../admin/RSAusers1.jsp">ACTIVATION AND DEACTIVATION</a></td>
	</tr>
<%
	}
	if(url.equals("/newUser.jsp") )
	{
%>
	<tr class="royamenuhead">
		<td width="25%" ><a href="../admin/newMenuCreation.jsp">MENU CREATION</a> </td>
		<td width="25%" class="bottomtext"><a href="../admin/newUser.jsp">ADMIN ID CREATION</a> </td>
		<td width="25%" ><a href="../admin/RSAassignPassword.jsp">ASSIGN NEW PASSWORD </a></td>
		<td width="25%" ><a href="../admin/RSAusers1.jsp">ACTIVATION AND DEACTIVATION</a></td>
	</tr>
<%
	}
	if (url.equals("/RSAassignPassword.jsp") ) 
	{
%>
	<tr class="royamenuhead">
		<td width="25%" ><a href="../admin/newMenuCreation.jsp">MENU CREATION</a> </td>
		<td width="25%" ><a href="../admin/newUser.jsp">ADMIN ID CREATION</a> </td>
		<td width="25%" class="bottomtext"><a href="../admin/RSAassignPassword.jsp">ASSIGN NEW PASSWORD </a></td>
		<td width="25%" ><a href="../admin/RSAusers1.jsp">ACTIVATION AND DEACTIVATION</a></td>
	</tr>
<%
	}
	if(url.equals("/RSAusers1.jsp") )
	{
%>	
	<tr class="royamenuhead">
		<td width="25%" ><a href="../admin/newMenuCreation.jsp">MENU CREATION</a> </td>
		<td width="25%" ><a href="../admin/newUser.jsp">ADMIN ID CREATION</a> </td>
		<td width="25%" ><a href="../admin/RSAassignPassword.jsp">ASSIGN NEW PASSWORD </a></td>
		<td width="25%" class="bottomtext"><a href="../admin/RSAusers1.jsp">ACTIVATION AND DEACTIVATION</a></td>
	</tr>
<%
	}
%>
</table>
<%
	session.setAttribute("adminUser",""+session.getAttribute("user"));
%>