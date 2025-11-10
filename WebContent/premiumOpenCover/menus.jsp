<%@ page language="java" import="java.util.*,java.sql.Connection" pageEncoding="UTF-8"%>
<jsp:useBean id = "openEnt" class = "com.maan.opencover.bean.opencoverEntry">
<jsp:setProperty name = "openEnt" property = "*"/>
</jsp:useBean>
<jsp:useBean id= "product" class = "com.maan.product.ProductSelection">
</jsp:useBean>
<%
String cpath = request.getContextPath();
if(session.getAttribute("ses")==null) {
	String ses1=request.getParameter("sesfrom")==null?"NOTCOMING":request.getParameter("sesfrom");
	if(!ses1.equals("NOTCOMING")&&!ses1.equals(session.getAttribute("ses"))) {
		session.setAttribute("ses",ses1);
	}
}
String loginIds = ((String)session.getAttribute("user")==null || "".equals((String)session.getAttribute("user")))?request.getParameter("user"):(String)session.getAttribute("user");
if("GUEST".equalsIgnoreCase(loginIds)) {
	String braCode = request.getParameter("braCode")==null?"01":request.getParameter("braCode");
    String sessionid = request.getSession().getId();
	Random rndm = new Random();
	String rnd = ""+rndm.nextInt(99999);
	String sesrnd = sessionid + rnd;
	if(session.getAttribute("ses")==null){
		session.setAttribute("ses",sesrnd);
	}
	HashMap brokerDetails = new HashMap();
    brokerDetails = product.getBrokerUserDetails(braCode);
	session.setAttribute("AdminBranchCode",braCode);
	session.setAttribute("adminBranch",braCode);
	session.setAttribute("BrokerDetails",brokerDetails);
	session.setAttribute("userLoginMode","Live");
	session.setAttribute("user",loginIds);
	session.setAttribute("AdminCountryId","1");
}
if(loginIds!=null) {
	session.setAttribute("loginPersonId",loginIds);
} else {
	if(session.getAttribute("loginPersonId")!=null)
		loginIds = (String)session.getAttribute("loginPersonId");
}
if("LEFTJSP".equalsIgnoreCase(request.getParameter("reqFrom")))
	session.setAttribute("user",request.getParameter("user"));
session.setAttribute("product_id","11");
if(request.getParameter("userLoginMode")!=null)
	session.setAttribute("userLoginMode",request.getParameter("userLoginMode"));
if(session.getAttribute("ses")==null) {
	response.sendRedirect("../login/error_messg.jsp");
	return;
}
String usrModeSC=(String)session.getAttribute("userLoginMode")==null?"":(String)session.getAttribute("userLoginMode");
com.maan.DBCon.DBConnectionStatus.statusStatic=usrModeSC;

String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String s1=""+session.getAttribute("mode");

if(session.getAttribute("userLoginMode")==null) {
	out.println("   userLoginMode   "+(String)session.getAttribute("userLoginMode"));
	if(true)
		return;
}
//Royal work work
String countryAdmins = "";
String braName="";
String braCode="";

braCode = request.getParameter("braCode");
if(braCode!=null) {
	session.setAttribute("AdminBranchCode",braCode);
} else {
	if(session.getAttribute("AdminBranchCode")!=null)
		braCode = (String)session.getAttribute("AdminBranchCode");
}
braName = request.getParameter("braName");
if(braName!=null) {
	session.setAttribute("branchName",braName);
} else {
	if(session.getAttribute("branchName")!=null)
		braName = (String)session.getAttribute("branchName");
}
countryAdmins = request.getParameter("AdminCountryId");
if(countryAdmins!=null) {
	session.setAttribute("AdminCountryId",countryAdmins);
} else {
	if(session.getAttribute("AdminCountryId")!=null)
		countryAdmins = (String)session.getAttribute("AdminCountryId");
}
String actualBranch = "";
if(!"GUEST".equalsIgnoreCase(loginIds)) {
	actualBranch = openEnt.getAdminBranch(loginIds);
	session.setAttribute("adminBranch",actualBranch);
} else {
	actualBranch = braCode==null?"01":braCode;
	session.setAttribute("adminBranch",actualBranch);
}	
%>
<html>
<head>
</head>
<body>
<form name="menus" method="post" action="">
<%
	String loginAdmin = "";
	loginAdmin = request.getParameter("user");
	loginAdmin = loginAdmin == null ? "" : loginAdmin;			
%>
<table width="100%"  border="0" cellspacing="0" cellpadding="0"  align="center">
<tr>
	<td width="50%" align="left"   bgcolor="#efefef">&nbsp;</td>
    <td width="50%" align="right"  bgcolor="#efefef">
		<table width="100%" border="0" cellspacing="0" cellpadding="0"  bgcolor="#efefef" style="align:justify; font-family:Arial;font-size:12px;font-weight:normal;">
			<tr>
				<% if(!"GUEST".equalsIgnoreCase(loginIds)){%>
				<td align="right" class="text"><a href="<%=cpath%>/homeAdmin.action">&nbsp;Home&nbsp;</a></td>
				<td align="right">|</td>
				<td align="right" class="text"><a href="<%=cpath%>/premiumOpenCover/LapsedOpenCovers.jsp"  class="buttonsMenu">Lapsed OpenCovers</a></td>
				<td align="right">|</td>
				<td align="right"  class="text">&nbsp;Security Info&nbsp;</td>
				<td>|</td>
				<td align="right" class="text"><b>&nbsp;FAQs&nbsp;</b></td>
				<td>|</td>
				<td align="right" class="text" class="text"><a href="#" class="buttonsMenu">&nbsp;Contact us&nbsp;</a></td>
				<td>|</td>
				<td align="right" class="text"><b><a href="<%=cpath%>/login/logout.jsp">&nbsp;Sign out&nbsp;</a></b></td>
				<%}%>
			</tr>
		</table>
    </td>
</tr>
<tr>
	<td colspan="2">
		<table width="100%" bgColor="#FFFFFF" border="0" cellspacing="0" cellpadding="0">
			<tr>
			<td valign="top" align="left">
				<img src="<%=path%>/assets/logo-main.png">
			</td>
			<td valign="middle" align="left" style="color:#003366;font-weight:bold;font-size:x-large">
				OPEN COVER&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
			<td valign="middle" align="left" style="color:red; font: bold; font-size: 30px;">
				<%=(String)session.getAttribute("userLoginMode")%>&nbsp;&nbsp;Environment&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</td>
			</tr>
		</table>
	</td>
</tr>
<tr height="10"><td colspan="2"></td></tr>
<% if(!"GUEST".equalsIgnoreCase(loginIds)){%>
<tr>
	<td>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="30%" class="buttonsMenuBlue" style="text-align: center;"><a href="#" onmousemove='change_class("newQuote");' onmouseout='RevertClass("newQuote");' onClick="return openNew()"><b>&nbsp;NEW QUOTE&nbsp;</b></a></td>
				<td width="3%"></td>
				<td width="30%" class="buttonsMenuBlue" style="text-align: center;"><a href="<%=path%>/premiumOpenCover/ShowOpenCoverEntry.jsp" onmousemove='change_class("quote");' onmouseout='RevertClass("quote");'><b>&nbsp;QUOTE REGISTER&nbsp;</b></a></td>
				<td width="3%"></td>
				<td width="30%" class="buttonsMenuBlue" style="text-align: center;"><a href="<%=path%>/premiumOpenCover/showApprovedCover.jsp" onmousemove='change_class("portFolio");' onmouseout='RevertClass("portFolio");'><b>&nbsp;PORTFOLIO&nbsp;</b></a></td>
			</tr>
		</table>
	</td>
	<td>
		<input type="hidden" name="freshMode">
    	<input type="hidden" name="freshMode1">
	</td>
</tr>
<%}%>
<tr>
	<td colspan="2" align="right">
		<% if(!"GUEST".equalsIgnoreCase(loginIds)){%>
		<% if(s1.trim().equals("s")) { %>
			Login Id: <strong><%=(String)session.getAttribute("RSAUSER")%>&nbsp;</strong>logged in On Behalf of&nbsp;<strong><%=(String)session.getAttribute("user")%></strong>&nbsp;in&nbsp;<strong><%=(String)session.getAttribute("userLoginMode")%>&nbsp;Mode&nbsp; </strong>
		<% } else{ //session.setAttribute("user",request.getParameter("user")); %>
			Login Id: <strong><%=(String)session.getAttribute("user")%></strong>&nbsp;in&nbsp;<strong><%=(String)session.getAttribute("branchName")%></strong>
		<% } }%>
	</td>
</tr> 
</table>
</form>
<script type="text/javascript">
function openNew() {
	document.menus.freshMode.value='fresh';
	document.menus.freshMode1.value='fresh';
	document.menus.action="<%=basePath%>premiumOpenCover/newOpenCover.jsp";
	document.menus.submit();
	return true;
}

function change_class(val) { 
document.getElementById(val).className='buttonsMenuBlueMOvar';
}

function RevertClass(val) {
	document.getElementById(val).className='buttonsMenuBlue';
}
</script>
</body>
</html>