
<%
//////out.println("hello"); %>
<%@ page import = "java.io.*, java.util.*, java.sql.*" buffer="100kb"%>
<%@ page import = "javax.servlet.http.HttpServlet.*, javax.servlet.ServletContext.*" %>
<%@ page import = "proj.date.DateFunction" %>
<%@ include file="../admin/header.jsp" %>
<%
///out.println("hai"); %>
<jsp:useBean id="policy" class="com.maan.admin.ratingAdmin">
<jsp:setProperty name="policy" property="out" value="<%=response.getWriter()%>" /> 
</jsp:useBean>


<%
try{
////out.println("hello");
DateFunction dt=new DateFunction();
String branch = (String)session.getAttribute("LoginBranchCode");
String error=request.getAttribute("error")==null?"":(String)request.getAttribute("error");
///out.println("error"+error);
String stdate=request.getParameter("startdate")==null?"":request.getParameter("startdate");
String endate=request.getParameter("enddate")==null?"":request.getParameter("enddate");
String product=request.getParameter("product")==null?"":request.getParameter("product");
String status=request.getParameter("status")==null?"":request.getParameter("status");
String policyno=request.getParameter("policyno")==null?"":request.getParameter("policyno");
///out.println("hellopolicy");
String rownum=request.getParameter("rownum")==null?"0":request.getParameter("rownum");
String firstr=request.getParameter("first")==null?"1":request.getParameter("first");
///out.println("rownum");
int spage = 1;
int start = 1;
	if (request.getParameter("spage") != null
							&& !request.getParameter("spage").equalsIgnoreCase(""))
						spage = request.getParameter("spage") == null ? 1 : Integer
						.parseInt(request.getParameter("spage"));
					if (request.getParameter("start") != null
							&& !request.getParameter("start").equalsIgnoreCase(""))
						start = request.getParameter("start") == null ? 1: Integer
						.parseInt(request.getParameter("start"));
//String start=request.getParameter("start")==null?"1":request.getParameter("start");

/*out.println("stdate:"+stdate);
out.println("endate:"+endate);
out.println("product:"+product);
out.println("status:"+status);
out.println("policyno:"+policyno);*/
String[][] PolicyDetails=new String[0][0];
String first="";
String second="";
//out.println("sdfsdfsdfsdfsdfs"+WARRANTY_ID1234);
String head="";
int st=0;
int endval=0;
int endv=0;
String end="1";

 end=request.getParameter("rownum")==null?"0":request.getParameter("rownum");
 ////out.println("end:"+end);
if(end.equals(""))
{
///out.println("if");
end="1";
}
int en=Integer.parseInt(end);
///out.println("firstr:"+firstr);
if(firstr.equals("1") || firstr.equals("0") || firstr.equals(""))
{
en=1;
firstr="1";
}
////out.println("en:"+en);
	
String cnt="0";
PolicyDetails=policy.getPolicy(stdate,endate,product,branch,status,en,firstr);
/////out.println("welcome");
cnt=policy.getPolicyCnt(stdate,endate,product,branch,status);
int c=Integer.parseInt(cnt);
/////out.println("end:"+request.getParameter("end"));
if (request.getParameter("end") != null && !request.getParameter("end").equalsIgnoreCase("") 
   && !request.getParameter("end").equalsIgnoreCase("0"))
	endv = request.getParameter("end") == null ? c : Integer
	.parseInt(request.getParameter("end"));
else if (endv<c){
endv=c;
}
	


///////////////    PAGINATION 
	                int no_of_records = 15;
					int no_of_records1 = 15;
					int displaypages = 5;
					int samplepages = displaypages;
					int displaypages1 = 5;

                 int length123 = 0;
					if (PolicyDetails.length == 0) {
						length123 = 1;
					} else {
						length123 = PolicyDetails.length;
					}
					int pages = length123 / no_of_records;
					int rem = length123 % no_of_records;
			
					if (rem != 0) {
						pages = pages + 1;
					}
					int display = 0;
				
				
					/*display = no_of_records * spage;
					if (spage >= displaypages) {
						if (pages > displaypages) {
							start++;
							displaypages++;
						}
					} else if ((displaypages - spage) == (samplepages - 1)
							&& start != 0) {
						start--;
						displaypages--;
					}*/

%>

<html>
<head>
<title align="center">Emirates</title>
<h2 align="center" height="25" class = "DKblueBG"><font size="4"><font></h2>
</head>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<body>
<form  method='post' name="group_view" action ="">
<input type="hidden" name="startdate" id="" value="<%=stdate%>"/>
<input type="hidden" name="enddate" value="<%=endate%>"/>
<input type="hidden" name="product" value="<%=product%>"/>
<input type="hidden" name="status" value="<%=status%>"/>
<input type="hidden" name="branch" value="<%=branch%>"/>
<table width="800px"  border="0" cellspacing="0" cellpadding="0">

<tr>
<td colspan="2"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
<tr>
<td width="213" align="left" valign="top">
<table width="213" border="0" cellspacing="0" cellpadding="0">
<%--@ include file="/admin/left.jsp" --%>
</tr>
</table>
</td>
<td width="1"></td>
<td align="left" valign="top">    
<table width="777"  border="0" cellspacing="0" cellpadding="0">
<tr> 
<td width="8" height="25" class="dkbgyellow">&nbsp;</td>
<td width="1"></td>
<td width="98%" class="heading"><strong>POLICY REPORT</strong></td>
</tr>
<tr align="center">
<td colspan="3">&nbsp;</td>
</tr>

<table width="100%" border="0" cellpadding='2' cellspacing='1' align='center'>
<tr>
<td colspan="2">

</td>
</tr>
<tr>
<td >
<table width="80%" border="0" cellpadding='2' cellspacing='1' align='center'>
<tr>
<td  colspan="2"><font color="red"></font></td></tr>
<tr align="center" class="royamenuhead"> 
	
<%


if(status.equals("Y"))
{
head="FULLY INTEGRATED";
}
else if(status.equals("P"))
{
head="PARTIALLY INTEGRATED";
}
else{
head="INTEGRATION ERROR";
}

%>
<tr class = "DKblueBG">
<td align="center" class = "bottomtext" colspan="8"><b>
<%=head%>
</b>
</td>
</tr >
<tr><td colspan="7" align="center"><%=error%></td></tr>
<tr class = "DKblueBG">
<td width="5%" class = "bottomtext">S.NO</td>
<td width="15%"   class = "bottomtext">POLICY NO</td>
<td width="15%" class = "bottomtext">QUOTE NO</td>
<td width="15%" nowrap class = "bottomtext">POLICY DATE</td>
<td width="15%" nowrap class = "bottomtext">CUSTOMER NAME</td>
<%if(status.equals("Y") )
{
%>
<td colspan="3" class = "bottomtext">CKEY</td>

<%}else if(status.equals("P"))
{
%>
 <td width="20%" class = "bottomtext">CKEY </td>
  <td width="20%" class = "bottomtext">Core Application KEY</td>
   <td width="20%" class = "bottomtext">REINTEGRATION</td>
<%
}
else{
 %>
 <td  width="20%" class = "bottomtext">INTEGRATION ERROR</td>
  <td width="5%" class = "bottomtext">REINTEGRATE</td>
 <%
 } %>

</tr>
<%

for(int k=0;k<PolicyDetails.length;k++)
{
 %>
   <tr class="DKblackBG">
     <td><%=k+1%></td>
   <% 
     for(int i=0;i<5;i++ )
      {
   %>
 <td  align="center"><%=PolicyDetails[k][i]%></td> 
   <%    
 }if(status.equals("Y")) { %>
 
<%
}
else if(status.equals("P"))
{
 %>
 <td><!--<input type="text" name="txtPartiallYInt<%=k%>" id="txtPartiallYInt<%=k%>"/>--><input type="button" class="DKblueBG"  value="UPDATE Core Application" onclick="OpenPolicyIntegrationPopUp('<%=PolicyDetails[k][0]%>','<%=PolicyDetails[k][4]%>','<%=k%>','U','<%=stdate%>','<%=endate%>','<%=product%>','<%=branch%>','<%=status %>')" /></td>
<td><input type="button" class="DKblueBG"  value="ReIntegration" onclick="funcpart('<%=PolicyDetails[k][0]%>','<%=PolicyDetails[k][4]%>','<%=k%>','R')" /></td>
 <%
 }else {
  %>
  <td><input type="button" class="DKblueBG"  value="ReIntegration" onclick="func('<%=PolicyDetails[k][0]%>')" /></td>
  <%
  } %> 
 
 </tr>
 <%
 st=Integer.parseInt(PolicyDetails[0][5]);
 endval=Integer.parseInt(PolicyDetails[k][5]);
 
 
 }

%>
<tr><td colspan="5" align="right">
<%
							if (PolicyDetails.length == 0) {
							%>
						<table>
					<tr align="center">
						<td colspan="6">
							<font size=2 color="red">THERE IS NO QUOTE FOR THIS USER 
						</td>
					</tr></table>
					<%
					}else{
/////out.println("start:"+start);
///out.println("endv:"+endv);
					for(int y=1;y<=c;y++)
								{%>						
							<a	href="javaScript:ExistingCustomers_B2B('<%=y%>','<%=displaypages%>','S','<%=endval%>')"><font
								color="#4f6180"><%=y%>
								</font>&nbsp;&nbsp;</a>
								<%
								} 
								
								}%>								

</td></tr>
<td></td>
					</tr>

<input type="hidden" name="policyno"  id="policyno" value=""/>
<input type="hidden" name="partid"  id="partid" value=""/>
<input type="hidden" name="ckey"  id="ckey" value=""/>
<input type="hidden" name="Type"  id="Type" value=""/>
<input type="hidden" name="rownum" id="rownum" value=""/>
<input type="hidden" name="first" id="first" value=""/>
<input type="hidden" name="start" id="start" value=""/>
<input type="hidden" name="end" id="end" value=""/>

<input type="hidden" name="requestfrom"   value="procpol"/>
</table>
<%int a=1; %>
<p align="center">
<input type="button" class="DKblueBG"  value="Back" 
onclick="func('1')">
</p>
</form>
<script>

function func(val)
{
document.getElementById("policyno").value=val;
document.group_view.action="<%=request.getContextPath()%>/admin/AdminController";
document.group_view.submit();
}
function funcpart(val,ckey,id,type)
{
///alert("val"+val)
document.getElementById("policyno").value=val;
document.getElementById("partid").value=id;
document.getElementById("ckey").value=ckey;
document.getElementById("Type").value=type;
document.group_view.action="<%=request.getContextPath()%>/admin/AdminController";
document.group_view.submit();

}
function OpenPolicyIntegrationPopUp(policyno,ckey,id,Type,stdate,endate,product,branch,status) 
{
var val="";
	var URL = "<%=request.getContextPath()%>/reports/PolicyIntegrationPopUp.jsp?policyno="+policyno+"&ckey="+ckey+"&id="+id+"&Type="+Type+"&startdate="+stdate+"&enddate="+endate+"&product="+product+"&branch="+branch+"&status="+status;
	var windowName = "GroupView";
	var width  = screen.availWidth;
	var height = screen.availHeight;
	var w = 400;
	var h = 200;
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

function ExistingCustomers_B2B(a,b,c,end)
{
/*alert("a:"+a);
alert("b:"+b);
alert("c:"+c);
alert("d:"+end);*/

document.getElementById("rownum").value=end;
document.getElementById("start").value=a;
if(c=='S')
{
document.getElementById("start").value=a;
}
else
{
document.getElementById("end").value=a;
}
document.getElementById("first").value=a;
document.group_view.action="<%=request.getContextPath()%>/reports/PolicyReportDetails.jsp";
document.group_view.submit();
}
function close123()
{

window.opener.focus();
self.close();
}
</script>

<%
}catch (Exception e)
{///out.println(e.getMessage());
}
%>