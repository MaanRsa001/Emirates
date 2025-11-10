<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
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
<body >
<table>
<tr>
	<td>
		<%@ include file="header.jsp" %>
	</td>
</tr>
</table>
<table style="width: 90%; margin: 0 auto;">
<tr height="5"><td></td></tr>
<tr>
	<td>
		<% String ab=(String)session.getAttribute("LoginBranchCode"); %>
		<div class="heading"><b>Export Files/Download Files</b></div>
	</td>
</tr>
<tr height="5"><td></td></tr>
<tr>
	<td>
		<a href="../reports/EmiratesPolicyReport.jsp"><font size="2" color="Blue">Policy Integration Report</font></a>
	</td>
</tr>
<tr height="5"><td></td></tr>
<tr>
	<td>
		<a href="../reports/OpenCoverPolicy.jsp"><font size="2" color="Blue">Opencover Policy Integration Report</font></a>
	</td>
</tr>
</table>
<!--<tr  height="25">
		<td align="center" width="75%"><font size="2"><span class="heading"><b>Select The Choice</b></span></font></td>
		</tr>
		<tr height="25">
		<td align="left" width="45%"><a href="../admin/Export_By_Dates.jsp"><font size="2" color="Blue">Export By Dates</font></a></td>
		</tr>
		<tr height="25">
		<td align="left" width="45%"><a href="../admin/Export_By_Policy_Number.jsp"><font size="2" color="Blue">Export By Policy Numbers</font></a></td> 
		<td align="left" width="45%"><a href="../admin/Mississippi_CustomerId_Dates.jsp"><font size="2" color="Blue">Verify Core Application Customer Code</font></a></td> 
		</tr>
		<tr height="25">
		<td align="left" width="45%"><a href="../admin/Export_By_Policy_Range.jsp"><font size="2" color="Blue">Export By Policy Number Rangewise</font></a></td>
		<td align="left" width="45%"><a href="../admin/Export_Report_By_Date.jsp"><font size="2" color="Blue">Export Details Reports</font></a></td>
		</tr>
		-->
</body>
</html>
