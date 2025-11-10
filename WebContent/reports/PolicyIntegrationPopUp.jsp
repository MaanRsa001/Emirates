<%@ include file="../login/sessionsCheckNormal.jsp" %>
<%@ page import = "java.io.*, java.util.*, java.sql.*" buffer="100kb"%>
<%@ page import = "javax.servlet.http.HttpServlet.*, javax.servlet.ServletContext.*" %>
<%@ page import = "proj.date.DateFunction" %>

<jsp:useBean id="clause" class="com.maan.admin.DAO.ratingAdmin">
<jsp:setProperty name="clause" property="out" value="<%=response.getWriter()%>" /> 
</jsp:useBean>

<%
	DateFunction dt=new DateFunction();
	String branch = (String)session.getAttribute("LoginBranchCode");
%>

<html>
<head>
<title align="center">Emirates</title>
<h2 align="center" height="25" class = "DKblueBG"><font size="4"><font></h2>
</head>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<body>
<form name="group_view" method="post" action="">

<table width="100%" border="0" cellpadding="2" cellspacing="1" align="center">
	
<%
String error = request.getAttribute("error")==null?"":(String)request.getAttribute("error");
////out.println("error:"+error);

String policyno=request.getParameter("policyno")==null?"":request.getParameter("policyno");
String ckey=request.getParameter("ckey")==null?"":request.getParameter("ckey");
String id=request.getParameter("id")==null?"":request.getParameter("id");
String Type=request.getParameter("Type")==null?"":request.getParameter("Type").trim();
String stdate=request.getParameter("startdate")==null?"":request.getParameter("startdate");
String endate=request.getParameter("enddate")==null?"":request.getParameter("enddate");
String product=request.getParameter("product")==null?"":request.getParameter("product");
String status=request.getParameter("status")==null?"":request.getParameter("status");
String cmsvalue=request.getParameter("txtCmsValue")==null?"":request.getParameter("txtCmsValue");
String head="PARTIALLY INTEGRATION UPDATION";

%>
<tr class = "DKblueBG">
<td align="center" colspan="2"><b>
<font color="white"><strong><%=head%></strong></font>
<input type="hidden" name="policyno"  id="policyno" value="<%=policyno%>"/>
<input type="hidden" name="partid"  id="partid" value="<%=id%>"/>
<input type="hidden" name="ckey"  id="ckey" value="<%=ckey%>"/>
<input type="hidden" name="Type"  id="Type" value="<%=Type%>"/>
<input type="hidden" name="startdate" value="<%=stdate%>"/>
<input type="hidden" name="enddate" value="<%=endate%>"/>
<input type="hidden" name="product" value="<%=product%>"/>
<input type="hidden" name="status" value="<%=status%>"/>
<input type="hidden" name="branch" value="<%=branch%>"/>
<input type="hidden" name="requestfrom"  id="policyno" value="procpol"/>
</b>
</td>
</tr>
<tr><td colspan="2"><font color="red"><%=error %></font></td></tr>
<tr class="formtxtf"><td class="mdbgyelllow" align="left" width="50%" nowrap  ><B>Core Application Value</B></td>  
<td style="padding-left:10px"  width="50%" >
<input name="txtCmsValue" type="text" style="width:200px" class="txtbox" value= "<%=cmsvalue%>"   size="55" maxlength="14">
</td>  	  	
</tr>
<tr class="formtxtf"><td class="mdbgyelllow" align="left" nowrap><B>OICD Code</B></td>  
<td style="padding-left:10px"  >
<input name="txtOcidCode" type="text" style="width:200px" class="txtbox" value= ""   size="55" maxlength="5">
</td>  	  	
</tr>
</table>
<p align="center">
<input type="image" src="../images/Proceed.jpg"   name="Image4" id="Image4" border="0" 
onclick="javascript:checkOther();">
<input type="image" src="../images/Cancel.jpg"   name="Image4" id="Image4" border="0" 
onclick="javascript:close123();">
</p>
<script><!--
var calvalue="";
String.prototype.trim = function() {
    return this.replace(/^\s+|\s+$/g, "");
};

function checkOther()
{
document.group_view.action="<%=request.getContextPath()%>/admin/AdminController";
document.group_view.submit();		

}
function close123()
{

window.opener.focus();
self.close();
}
--></script>