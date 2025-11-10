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


	<tr class="royamenuhead">
	<td width="10%" ><a href="${pageContext.request.contextPath}/schemeTravelConfigure.action">Travel Scheme1</a></td>
	<td width="10%" ><a href="../servlet/TravelConfigController?requestFrom=schemCoverList">Travel Scheme</a></td>
	<td width="10%" ><a href="${pageContext.request.contextPath}/optionTravelConfigure.action">Travel Option1</a> </td>
	<td width="10%" ><a href="../servlet/TravelConfigController?requestFrom=initTravelOption">Travel Option</a> </td>
	<td width="10%" ><a href="${pageContext.request.contextPath}/linkoptionTravelConfigure.action">Travel Link Option 1</a> </td>
	<td width="10%" ><a href="../servlet/TravelConfigController?requestFrom=firstlinkoption">Travel Link Option</a> </td>
	</tr>
	<tr class="royamenuhead">
	<td width="30%" ><a href="${pageContext.request.contextPath}/coverageTravelConfigure.action">Travel Master Coverages 1</a> </td>
	<td width="30%" ><a href="../servlet/TravelConfigController?requestFrom=coveragesmaster">Travel Master Coverages</a> </td>
	<td width="30%" ><a href="<%=request.getContextPath()%>/servlet/TravelConfigController?requestFrom=linkinitcoverages">Travel Link Coverages</a></td>
	<td width="30%" ><a href="${pageContext.request.contextPath}/linkcoverageTravelConfigure.action">Travel Link Coverages 1</a></td>
	<%--<td width="30%" ><a href="<%=request.getContextPath()%>/servlet/TravelConfigController?requestFrom=initTravelCovers">Travel Coverages</a></td>--%>		
	<td width="30%"><a href="<%=request.getContextPath()%>/servlet/TravelConfigController?requestFrom=ratediscount">Travel Discount Rate</a></td>
	<td width="30%"><a href="${pageContext.request.contextPath}/discountrateTravelConfigure.action">Travel Discount Rate 1</a></td>
	</tr>
	<tr class="royamenuhead">
	<td width="30%" ><a href="../servlet/TravelConfigController?requestFrom=relationDiscount">Travel Relation Discount</a></td>
	<td width="30%" ><a href="${pageContext.request.contextPath}/relationdiscountTravelConfigure.action">Travel Relation Discount 1</a></td>
	<td width="30%"><a href="../servlet/TravelConfigController?requestFrom=premiumRate">Travel Premium Rate</a></td>
	<td width="30%"><a href="${pageContext.request.contextPath}/premiumrateTravelConfigure.action">Travel Premium Rate 1</a></td>
	<td width="30%"><a href="${pageContext.request.contextPath}/premiumrateTravelConfigure.action">&nbsp;</a></td>
	<td width="30%"><a href="${pageContext.request.contextPath}/premiumrateTravelConfigure.action">&nbsp;</a></td>
	</tr>
</table>
<%
	session.setAttribute("adminUser",""+session.getAttribute("user"));
%>