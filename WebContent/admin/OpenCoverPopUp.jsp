<%@ include file="../login/sessionsCheckNormal.jsp" %>
<%@ page import = "java.io.*, java.util.*, java.sql.*" buffer="100kb"%>
<%@ page import = "javax.servlet.http.HttpServlet.*, javax.servlet.ServletContext.*" %>
<%@ page import = "proj.date.DateFunction" %>

<jsp:useBean id="cus" class="com.maan.broker.UserCreationBean">
<jsp:setProperty name="cus" property="out" value="<%=response.getWriter()%>" /> 
</jsp:useBean>
<jsp:useBean id="broker" class="com.maan.admin.BrokerCreationBean">
</jsp:useBean>


<%		DateFunction dt=new DateFunction();		%>

<html>
<head>
<title align="center">Emirates</title>
<h2 align="center" height="25" class = "DKblueBG"><font size="4"><font></h2>
</head>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<body>
<%
String path = request.getContextPath();
%>
	<form name="group_view" method="post">

<table width="100%" border="0" cellpadding="2" cellspacing="1" align="center">
	
<%

String brokerLoginId = request.getParameter("editbroker")==null?"":request.getParameter("editbroker");
String brokerCode = request.getParameter("brokerCode")==null?"":request.getParameter("brokerCode");
String pid=request.getParameter("pid")==null?"":request.getParameter("pid");
String userLogId=request.getParameter("userLogId")==null?"":request.getParameter("userLogId");
String head="Open Cover Certificates Allcation";

%>

<tr class = "mdbgyelllow">
<td align="center" colspan="5"><b><%=head%></b></td>
</tr>
		<tr> 
			<td width="50" class = "LTblueBG" align="center"><b>SNO</b></td>
			<td class = "LTblueBG" align="center"><b>OPEN COVER NO</b></td>
			<td class = "LTblueBG" align="center"><b>CUSTOMER NAME</b></td>
			<td class = "LTblueBG" align="center"><b>MISSIPPI OPENCOVER NO</b></td>
			
		
		</tr>
<%
		String opencovers[][] = new String[0][0];
		String selectedopencovers = "";
		
		opencovers = cus.getOpenCoverCertificates(brokerLoginId);
		selectedopencovers = broker.getOpenCoverCertificatesUser(userLogId,brokerCode);
		if(session.getAttribute("OpenCoverNos"+brokerCode+pid)!=null)
		{
			selectedopencovers = (String)session.getAttribute("OpenCoverNos"+brokerCode+pid);
		}
		for(int i=0;i<opencovers.length;i++)
		{
			String sel="";
			
			StringTokenizer st = new StringTokenizer(selectedopencovers,",");
			while(st.hasMoreTokens())
			{
				if(opencovers[i][0].equalsIgnoreCase(st.nextToken()))
				{
					sel = "checked";
					break;
				}
				else
					sel="";
			}
			
%>
		<tr>
			<td width="50" class = "LTblueBG"><%=(i+1)%>&nbsp;&nbsp;&nbsp;
			<input type="checkbox" name="opencover<%=(i+1)%>" value="<%=opencovers[i][0].trim()%>" <%=sel%>></td>
			<td class = "LTblueBG" align="center"><%=(opencovers[i][0]!=null?opencovers[i][0]:"")%></td>
			<input type="hidden" name="OCNO<%=(i+1)%>" value="<%=(opencovers[i][0]!=null?opencovers[i][0]:"")%>"/>
			<td class = "LTblueBG" align="left"><%=(opencovers[i][6]!=null?opencovers[i][6]:"")%></td>
			<td class = "LTblueBG" align="center"><%=(opencovers[i][2]!=null?opencovers[i][2]:"")%></td>
			
		
		</tr>
<%
	}	
	%>

<tr class = "mdbgyelllow">
<td align="left" colspan="5"><b><a href="#" onClick="SelectAll('<%=opencovers.length%>')">Select All</a></b></td>
</tr>

</table>
<p align="center">

<a href="#" onclick="javascript:close123();"><img src="<%=path%>/images/Back.jpg"></a>
<a href="#" onclick="javascript:close4491('<%=path%>');"><img src="<%=path%>/images/Submit.jpg"></a>
</p>

</body>
<input type="hidden" name="requestfrom"  value="OpenCoverCertificate">
<input type="hidden" name="brokerLoginId"  value="<%=brokerLoginId%>">
<input type="hidden" name="brokerCode"  value="<%=brokerCode%>">
<input type="hidden" name="userLogId"  value="<%=userLogId%>">
<input type="hidden" name="pid"  value="<%=pid%>">
<input type="hidden" name="opencoversLength"  value="<%=opencovers.length%>">

<script>

function SelectAll(len)
{
	for(i=0;i<len;i++)
	{
		document.getElementById("opencover"+(i+1)).checked=true;
	}
}
function close123()
{
	window.opener.focus();
	self.close();
}
function close4491(paths)
{
	document.group_view.action = paths+"/admin/BrokerCreationController";
	document.group_view.submit();
	
}
</script>
</form>