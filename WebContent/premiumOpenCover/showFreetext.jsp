<link href="../css/style.css" rel="stylesheet" type="text/css">
<%@ page import="java.util.*" %>
<jsp:useBean id="theBean" class="com.maan.opencover.bean.opencoverSummary">
<jsp:setProperty name="theBean" property="*"/>
</jsp:useBean>
<%
String proposalNo="";

proposalNo = request.getParameter("proposalNo")==null?(String)
session.getAttribute("proposalNo"): (String)request.getAttribute("proposalNo");
int n = 1;
int flag = 0;

  String[][] commodities= theBean.getFreeText(proposalNo,request.getParameter("coverNo"));

%>
<%
   String pathq = request.getContextPath();
   String basePaths = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+pathq+"/";
%>
<html>
<head>
    <title>** Emirates - Marine Insurance</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <link href="../css/style.css" rel="stylesheet" type="text/css">
    <link href="../css/footable-0.1.css" rel="stylesheet" type="text/css">
</head>
<body>
<form name="wsrcc" method="post">	
<br>
<table  align="center" border="0" cellpadding="2" cellspacing="2" width="100%">
	<tr>
		<td>
			<span class="heading">FreeText Details</span>
		</td>
	</tr>
</table>
<div  style="border-left: 0px gray solid;padding:1px; padding-left:5px; margin: 1px">
<table class="footable" width="100%">
<tbody>
<% 	if(commodities.length > 0) {
		for(int i=0;i<commodities.length;i++) {
			if(!"9999".equals(commodities[i][1])) {%>
<tr>
	<td><%=n%></td>
	<td><%=commodities[i][1]%></td>
</tr>
<%
				n++;
			} else {flag = 1;}
		}
	} else { flag = 1; }
	if(flag == 1) {%>	
<tr>
	<td colspan="2">No FreeText Available</td>
</tr>
<%}%>
</tbody>
</table>
</div>
<input type="hidden" name="totalLength" value="<%=commodities.length%>">
<input type="hidden" name="coverNo" value="<%=request.getParameter("coverNo")%>">
<!--<input type="image"  src="../images/btn_cancel.gif"   onClick="back()" tabindex=0 accesskey='c'>-->	
<!--	<input type="button"  value="ADD CLAUSES"   onClick="return addText()">	-->
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<!--<input type="image"  src="../images/button_submit.gif"   onClick="window.close()" tabindex=0 accesskey='p'>
&nbsp;&nbsp;&nbsp;</td></tr>-->
<input type="hidden" name="coverName" value="<%=request.getParameter("coverName")%>">
<input type="hidden" name="n" value="<%=n%>">
<div style="margin-top: 10px;" align="center">
	<a href='javascript:history.go(-1)' class="buttonsMenu" > <img src="<%=pathq%>/images/Back.jpg">	</a>
	&nbsp;&nbsp;&nbsp;
	<a href= "#" onClick='window.close()' class="buttonsMenu" > <img src="<%=pathq%>/images/Close.jpg"  >		</a>
</div>
        </form>
    </body>
</html>

<script>
function back()
{
	document.wsrcc.action="showCoverages.jsp?id=1";
	document.wsrcc.submit();
	return true;
}
function addText()
{
	
	
	document.wsrcc.action="showClausesEdit.jsp?input=new";
	document.wsrcc.submit();
	return true;
}
				
</script>
