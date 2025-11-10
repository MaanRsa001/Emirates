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
<jsp:useBean id="theBean" class="com.maan.opencover.ConditionsOpenCover">
	<jsp:setProperty name="theBean" property="*"/>
</jsp:useBean>
<%
	wsrcc = (String)session.getAttribute("coverName");
	if(wsrcc!= null && wsrcc.trim().indexOf("BY LAND OR SEA OR AIR") != -1) {
		wsrcc = wsrcc.replaceAll("BY LAND OR SEA OR AIR","MULTI MODE");
	} 
	int sea=0, air=0,road = 0, multimode=0;
	proposalNo = request.getParameter("proposalNo")==null?(String)
	session.getAttribute("proposalNo"): (String)request.getAttribute("proposalNo");

	int n = 1;

/*air = wsrcc.lastIndexOf("AIR");
road = wsrcc.lastIndexOf("ROAD");
sea = wsrcc.lastIndexOf("SEA");
multimode = wsrcc.lastIndexOf("MULTI MODE");
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
if(multimode>0)
  {
    multimode = 4;
  }
else
{
   multimode = 101;
}
if(road>0)
  {
  road = 0;
  }*/
	String extraCoverIds=theBean.getExtraCoverIds(proposalNo, (String) session.getAttribute("AdminBranchCode"));
	theBean.setProposalNo(proposalNo);
	//String[][] commodities= theBean.getWsrccFromOpenCoverMasterResult(extraCoverIds);
	String[][] commodities= theBean.getWsrccFromOpenCoverMasterResult(extraCoverIds);

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
			<span class="heading">Warranties</span>
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
	<td><b>No warranties for Commodity</b></td>
</tr>
<%}%>
</tbody>
</table>
<input type="hidden" name="totalLength" value="<%=commodities.length%>">
</div>
<div style="margin-top: 10px;" align="center">
	<a href= "#" onClick='window.close()' ><img src="<%=pathq%>/images/Close.jpg" ></a>
</div>
<input type="hidden" name="coverName" value="<%=request.getParameter("coverName")%>">
<input type="hidden" name="n" value="<%=n%>">

        </form>
    </body>
</html>

