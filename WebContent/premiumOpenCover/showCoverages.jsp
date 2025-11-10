<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="java.util.*" %>
<%//@ page import="java.util.Hashtable" %>
<%@ include file="home1.jsp" %>

<jsp:useBean id="ct" class = "com.maan.opencover.bean.opencoverSummary">
<jsp:setProperty property="*" name="ct"/>
</jsp:useBean>

<%
boolean flag	=	false;
int tt=0, tt1=0;
String values="";
boolean fragileFlag	=	false;
String commName_S="",desc_S="",checkStatus="unchecked";
String reqFrom=request.getParameter("reqFrom")==null?"":request.getParameter("reqFrom");
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
int k=0;
		String adminBranch = (String) session.getAttribute("AdminBranchCode");
		String belongingBranch = (String) session.getAttribute("BelongingBranch");
		if(adminBranch.indexOf("'")!=-1)
			adminBranch = adminBranch.replaceAll("'","");
		String cid = (String) session.getAttribute("AdminCountryId");
String[][] showCoverages=null;
showCoverages=ct.showCoverages((String)session.getAttribute("proposalNo"),belongingBranch);
String action="";
if("1".equalsIgnoreCase(request.getParameter("id")))
action="showFreetext.jsp";
else
action="freetext.jsp";
%>
<html>
<script>
function controls()
{
	//alert('hai');
	document.clauses.action= '<%=path%>/premiumOpenCover/freetext.jsp';
	document.clauses.submit();
}
function getSubmit(){
var reqFrom='<%=reqFrom%>';
	if('others'==reqFrom){
		document.clauses.action='<%=path%>/'+'getOthersAdmin.action';
		document.clauses.submit();
	}else{
	    document.clauses.submit();
	}
}
</script>
<head>
<title>** Emirates - Marine Insurance</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="../css/style.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="<%=path%>/css/calendar1.js"></script>
</head>
<body>
<form name="clauses" method="post" action="<%=action%>" >
<table  align="center" border="0" cellpadding="2" cellspacing="2" width="100%">
	<tr>
		<td>
			<span class="heading"><%if("others".equals(reqFrom)){ %>Optional Cover Entries <%}else{ %> Free Text Entries <%} %></span>
		</td>
	</tr>
</table>
<div style="width: 75%; margin: 0 auto;">
<table  align="center" border="0" cellpadding="2" cellspacing="2" width="100%">
	<tr>
		<td colspan="2" style="padding: 5px;" align="center">
			<span class="heading">Coverage Selection For <%if("others".equals(reqFrom)){ %>Optional Cover Entries <%}else{ %> Free Text Entries <%} %></span>
		</td>
	</tr>
	<tr>
		<%
		if(showCoverages.length==0)
		{%>
		<td colspan="2" align="center"><font color="red" size="2">There is no Coverages Available in SEA Tranport</font></td>
		<%}else{%>
		<td class="formtxtf1" width="50%" style="padding: 5px;">Select Coverages</td>
		<td class="formtxtf1" width="50%" style="padding: 5px;">
			<select  name="coverNo" class="inputSelect">
				<% for(int i=0;i<showCoverages.length;i++) { %>
				<option value="<%=showCoverages[i][1]%>"><%=showCoverages[i][3]%></option>
				<%}
					String value="";
					for(int i=0;i<showCoverages.length;i++) {
					if(value.length()>1)
						break;
					//if(showCoverages!=null && showCoverages.length>0)
					//		{
					if(showCoverages[i][4].equalsIgnoreCase("1") && showCoverages[i][2]!=null) {
						StringTokenizer tokens=new StringTokenizer(showCoverages[i][2],",");
						while(tokens.hasMoreTokens()) {
							value=tokens.nextToken();
							if("FCL".equalsIgnoreCase(value))
								values="101";
							else if("LCL".equalsIgnoreCase(value))
								values="102";
							else if("BREAK BULK".equalsIgnoreCase(value))
								values="103";
							else if("BULK".equalsIgnoreCase(value))
								values="104";
							else
								values="0";
				
							if(Integer.parseInt(values)>100)
							{%>
					<option value="<%=values%>"><%=value%></option>
				<%}}}}%>
				<%if("freetext".equalsIgnoreCase(reqFrom)){ %>
				<option value="105">WARRANTIES</option>
				<option value="106">EXCLUSIONS</option>
				<%} %>
			</select>			
		</td>
		<%}%>
	</tr>
</table>
</div>
<div style="margin-top: 10px;" align="center">
	<a href="javascript:window.close()"  class="buttonsMenu" > <img src="../images/Back.jpg"></a>
	&nbsp;&nbsp;&nbsp;
	<%if(showCoverages.length!=0) {%>
		<a href="#" onclick="getSubmit();" class="buttonsMenu" > <img src="../images/Proceed.jpg"></a>
	<%}%>
</div>
<input type="hidden" name="reqFrom" value="<%=reqFrom%>" />
                      
</form>
</body>
</html>

			