<%@ include file="submenu.jsp"%>
<%@ page import="java.util.*" %>
<%!
	int i=0;
	String wsrcc =null;
	String proposalNo = null;
%>
<%
   String pathq = request.getContextPath();
   String basePaths = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+pathq+"/";
%>
<jsp:useBean id="theBean" class="com.maan.opencover.bean.opencoverSummary">
	<jsp:setProperty name="theBean" property="*"/>
</jsp:useBean>
<%
	wsrcc = (String)session.getAttribute("coverName");
	int sea=0, air=0,road = 0;
	String sb="";
	
	proposalNo = request.getParameter("proposalNo")==null?(String)
	session.getAttribute("proposalNo"): (String)request.getAttribute("proposalNo");
	
	int n = 1;
%>
<%
air = wsrcc.lastIndexOf("AIR");
road = wsrcc.lastIndexOf("ROAD");
sea = wsrcc.lastIndexOf("SEA");
if(air>0)
  {
    air=2;
  }
 else
   {
   air = 101;
   }
if(sea>0)
  {
    sea = 1;
  }
  else
    {
      sea = 101;
      }
if(road>0)
  {
  road = 0;
 
  
  }
  String[][] commodities= theBean.getExclusions(proposalNo);

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
			<span class="heading">Exclusions Details</span>
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
	<td><b>No Exclusions for Commodity</b></td>
</tr>
<%}%>
</tbody>
</table>
<input type="hidden" name="totalLength" value="<%=commodities.length%>">
<!--<input type="image"  src="../images/btn_cancel.gif"   onClick="window.close()" tabindex=0 accesskey='c'>	
<input type="button"  value="ADD CLAUSES"   onClick="return addText()">	-->
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<!--<input type="image"  src="../images/button_submit.gif"   onClick="window.close()" tabindex=0 accesskey='p'>
&nbsp;&nbsp;&nbsp;</td></tr>-->
<input type="hidden" name="coverName" value="<%=request.getParameter("coverName")%>">
<input type="hidden" name="n" value="<%=n%>">
</div>
<div style="margin-top: 10px;" align="center">
	<a href= "#" onClick='window.close()' class="buttonsMenu" ><img src="<%=pathq%>/images/Close.jpg" ></a>
</div>
</form>
</body>

<script>
function addText()
{
	document.wsrcc.action="showClausesEdit.jsp?input=new";
	document.wsrcc.submit();
	return true;
}
				
</script>
</html>