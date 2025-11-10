<%@ include file="submenu.jsp"%>
<%@ page import="java.util.*" %>
<%!
	int n;
	String coverId=null;
	String proposalNo = null;
%>
<%
   String pathq = request.getContextPath();
   String basePaths = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+pathq+"/";
%>
<%
	coverId = request.getParameter("coverId");
	proposalNo = request.getParameter("proposalNo")==null?(String)
	session.getAttribute("proposalNo"): (String)request.getAttribute("proposalNo");

	n = 1;

	String adminBranch = (String) session.getAttribute("AdminBranchCode");
			if(adminBranch.indexOf("'")!=-1)
				adminBranch = adminBranch.replaceAll("'","");
			String cid = (String) session.getAttribute("AdminCountryId");
	
	String[][] commodities=null;
	commodities=com.maan.services.util.runner.multipleSelection("select clauses_id,clauses_description from open_cover_clauses where status='Y' and cover_id='"+coverId+"' and extra_cover_id='0' and proposal_no = '"+proposalNo+"'  and amend_id = (select max(amend_id) from open_cover_clauses where status='Y' and cover_id='"+coverId+"' and extra_cover_id='0' and proposal_no = '"+proposalNo+"')");

%>

<html>
<head>
	<title>** Emirates - Marine Insurance</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <link href="../css/style.css" rel="stylesheet" type="text/css">
    <link href="../css/footable-0.1.css" rel="stylesheet" type="text/css">
</head>
<body>
<form name="commodity" method="post">
<br>
<table  align="center" border="0" cellpadding="2" cellspacing="2" width="100%">
	<tr>
		<td>
			<span class="heading">Clauses  Details</span>
		</td>
	</tr>
</table>
<div  style="border-left: 0px gray solid;padding:1px; padding-left:5px; margin: 1px">
<table class="footable" width="100%">
<tbody>
<%
if(commodities.length > 0) {
	for(int i=0;i<commodities.length;i++) {
%>
<tr>
	<td><%=n%></td>
	<td><%=commodities[i][1]%></td>
</tr>
<%		n++;
	}
} else {%>
<tr>
	<td><b>You are not selecting any Clauses  &amp; Warrenties DETAILS</b></td>
</tr>
<%}%>
</tbody>
</table>
<input type="hidden" name="totalLength" value="<%=commodities.length%>">
</div>
<div style="margin-top: 10px;" align="center">
	<a href= "#" onClick='window.close()' ><img src="<%=pathq%>/images/Close.jpg" ></a>
	<!--<input type="image"  src="../images/btn_cancel.gif"   onClick="window.close()" tabindex=0 accesskey='c'>	
			
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="image"  src="../images/button_submit.gif"   onClick="window.close()" tabindex=0 accesskey='p'>	
				
				&nbsp;&nbsp;&nbsp;</td>-->
	<input type="hidden" name="coverName" value="<%=request.getParameter("coverName")%>">
	<input type="hidden" name="n" value="<%=n%>">
</div>           
	</form>
</body>
<script>
function addText()
{
	document.commodity.action="showClausesEdit.jsp?input=new";
	document.commodity.submit();
	return true;
}
				
</script>
</html>

