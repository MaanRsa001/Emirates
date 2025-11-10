<%@ page import = "java.io.*, java.util.*, java.text.*, java.sql.*" buffer="100kb"%>
<%@ page import = "javax.servlet.http.HttpServlet.*, javax.servlet.ServletContext.*" %>
<%@ page import = "proj.date.DateFunction" %>
<%@ include file="../admin/header.jsp" %>
<jsp:useBean id="com" class="com.maan.admin.ratingAdmin">
<jsp:setProperty name="com" property="out" value="<%=response.getWriter()%>" /> 
</jsp:useBean>

<html>
<head>
<title>Emirates</title>
<link href="/css/style.css" rel="stylesheet" type="text/css" />

<%
try{
String path=request.getContextPath();
String startdate=request.getAttribute("startdate")==null?"":(String)request.getAttribute("startdate");
String enddate=request.getAttribute("enddate")==null?"":(String)request.getAttribute("enddate");
String product=request.getAttribute("product")==null?"":(String)request.getAttribute("product");
String branch=request.getAttribute("branch")==null?"":(String)request.getAttribute("branch");


String[][] policyDetails=com.getProductDetails(startdate,enddate,product,branch);

int fullintegrate=0;
int integrateerr=0;
int partintegrate=0;
for(int k=0;k<policyDetails.length;k++)
{
if(policyDetails[k][0].equals("Y"))
{
fullintegrate=Integer.parseInt(policyDetails[k][1]);
}
else if(policyDetails[k][0].equals("P"))
{
partintegrate=Integer.parseInt(policyDetails[k][1]);
}
else {
integrateerr=Integer.parseInt(policyDetails[k][1]);
}
}


 %>
</head>
<body>
<table width="90%" style="margin: 0 auto;">
	<tr>
		<td><%@ include file="/admin/left.jsp" %></td>
	</tr>
	<tr height="5"><td></td></tr>
	<tr>
		<td><div class="heading">POLICY REPORT</div></td>
	</tr>
	<tr height="5"><td></td></tr>
	<tr>
		<td width="100%">
			<table width="75%" style="margin: 0 auto;">
				<tr>
					<td colspan="2" align="center">POLICY INTEGRATION REPORT</td>
				</tr>
				<tr height="5"><td colspan="2"></td></tr>
				<tr>
					<td width="50%"><b style="color: #000000;">Fully Integration</b></td>
					<td width="50%">
						<a> <font color="#6f8db9"><strong><%=fullintegrate%></strong></font> <img src="../images/list_ic.gif"  style="cursor:hand" height="16" src="../images/list_ic.jpg" onClick="javascript:funpolicydetails1('<%=startdate%>','<%=enddate%>','<%=product%>','<%=branch%>','Y');" border='0' name="chkICC_A_SEAClause" alt="Policy"> </a>
					</td>
				</tr>
				<tr height="5"><td colspan="2"></td></tr>
				<tr>
					<td><b style="color: #000000;">Partially Integration</b></td>
					<td><a><font color="#6f8db9"><strong><%=partintegrate%></strong></font>  <img src="../images/list_ic.gif"  style="cursor:hand" height="16" src="../images/list_ic.jpg" onClick="javascript:funpolicydetails1('<%=startdate%>','<%=enddate%>','<%=product%>','<%=branch%>','P');" border='0' name="chkICC_A_SEAClause" alt="Policy"> </a></td>
				</tr>
				<tr height="5"><td colspan="2"></td></tr>
				<tr>
					<td><b style="color: #000000;">Integration Error</b></td>
					<td><a><font color="#6f8db9"><strong><%=integrateerr%></strong></font> <img src="../images/list_ic.gif"  style="cursor:hand" height="16" src="../images/list_ic.jpg" onClick="javascript:funpolicydetails1('<%=startdate%>','<%=enddate%>','<%=product%>','<%=branch%>','F');" border='0' name="chkICC_A_SEAClause" alt="Policy"> </a></td>
				</tr>
				<tr height="5"><td colspan="2"></td></tr>
				<tr>
					<td><b style="color: #000000;">Total</b></td>
					<td><%=(fullintegrate+partintegrate+integrateerr)%></td>
				</tr>
				<tr height="5"><td colspan="2"></td></tr>
				<tr>
					<td colspan="2" align="center"><a href="../admin/ExportTables.jsp"><img src='<%=path%>/images/Back.jpg'/></a></td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</body>
<script type="text/javascript">
function funpolicydetails(stdate,endate,product,branch,status)
{

	var URL = "<%=request.getContextPath()%>/reports/PolicyReportDetails.jsp?startdate="+stdate+"&enddate="+endate+"&product="+product+"&branch="+branch+"&status="+status;
	var windowName = "GroupView";
	var width  = screen.availWidth;
	var height = screen.availHeight;
	var w = 1000;
	var h = 800;
	var features =
		'width='          + w +
		',height='		  + h +
		',left='		  + ((0) * .5)  +
		',top='			  + ((0) * .5) +
		',directories=no' +
		',location=no'	  +
		',menubar=no'	  +
		',scrollbars=yes' +
		',status=no'	  +
		',toolbar=no'	  +
		',resizable=no';
	var strOpen = window.open (URL, windowName, features);
	strOpen.focus();
	return false;
}

function funpolicydetails1(stdate,endate,product,branch,status)
{

	document.form1.action="<%=request.getContextPath()%>/reports/OpenCoverDetails.jsp?startdate="+stdate+"&enddate="+endate+"&product="+product+"&branch="+branch+"&status="+status;
    document.form1.submit();
}

</script>
<%}catch(Exception e)
{
out.println(e.getMessage());
}
%>

</html>