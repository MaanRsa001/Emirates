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
<table width="80%"  border="0" cellspacing="0" cellpadding="0">
<tr> 
<td colspan="5" >

<table width="100%" border="0" cellspacing="1" cellpadding="2">
<%--
	<tr class="royamenuhead">
		<td width="25%" ><a href="../admin/MotorBody.jsp">Motor Body Form</a> </td>
		<td width="25%" ><a href="../admin/MotorBodyRate.jsp">Motor Body Rate</a></td>
		<td width="25%" ><a href="../admin/MotorBodyRate.jsp?third=third">Third Party Rate</a></td>
	</tr>
--%>
	<tr class="royamenuhead">
		<td width="20%" ><a href="<%=request.getContextPath() %>/servlet/MotorConfigController?requestFrom=displayeconfig">Area Coverage</a></td>		
		<td width="20%" ><a href="../admin/MotorBody.jsp">Motor Body Form</a> </td>
		<td width="20%" ><a href="../admin/MotorBodyRate.jsp">Motor Body Rate</a></td>
		<td width="20%" ><a href="<%=request.getContextPath() %>/servlet/MotorBodyController?requestFrom=makeconfig">Make Configuration</a></td>		
		<td width="20%" ><a href="<%=request.getContextPath() %>/servlet/MotorConfigController?requestFrom=displaybank">Motor Bank</a></td>			
	</tr>
	<tr class="royamenuhead">
		
		<td width="20%" ><a href="<%=request.getContextPath() %>/servlet/MotorConfigController?requestFrom=displayfinancebank">Motor Finance Bank</a></td>						
		<td width="20%" ><a href="<%=request.getContextPath() %>/servlet/MotorConfigController?requestFrom=displaymotortype">Motor Policy</a></td>	
		<td width="20%" ><a href="<%=request.getContextPath() %>/servlet/MotorConfigController?requestFrom=displayopcover">Motor OPCover</a></td>			
		<td width="20%" ><a href="<%=request.getContextPath() %>/servlet/MotorBodyController?requestFrom=modelconfig&val=config">Model Configuration</a></td>	
		<td width="20%" ><a href="<%=request.getContextPath() %>/servlet/MotorConfigController?requestFrom=displaymotorcolor">Motor Color</a></td>						
			
	</tr>
	<tr class="royamenuhead">
		<td width="20%" ><a href="<%=request.getContextPath() %>/servlet/MotorConfigController?requestFrom=displayMotorCylinder&val=displayBodyList">Motor Cylinder</a></td>						
		<td width="20%" ><a href="<%=request.getContextPath() %>/servlet/MotorConfigController?requestFrom=displayUplodeFile">Motor Uploded File</a></td>						
		<td width="20%" ><a href="<%=request.getContextPath() %>/servlet/MotorConfigController?requestFrom=displayMotorVoluntary">Motor Voluntary</a></td>									
		<td width="20%" >&nbsp;</td>
		<td width="20%" >&nbsp;</td>						
	</tr>
</table>
<%
	session.setAttribute("adminUser",""+session.getAttribute("user"));
%>