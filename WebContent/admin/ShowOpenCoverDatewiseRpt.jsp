


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

 <%@ include file="header.jsp" %>
<html>
<head>
<title></title>
<link href="../css/style.css" rel="stylesheet" type="text/css">

</head>

	<jsp:useBean id = "admin" class = "com.maan.admin.AdminBean">
	<jsp:setProperty name = "admin" property = "*"/>
	</jsp:useBean>

	<jsp:useBean id = "broker" class = "com.maan.admin.DatewiseBrokerDetails">
	<jsp:setProperty name = "broker" property = "*"/>
	</jsp:useBean>

<%	
	String err="";
	if(request.getAttribute("errorDetail")!=null)
		err=(String)request.getAttribute("errorDetail");
		String path= request.getContextPath();
		String loginId = (String) session.getAttribute("user");
		String adminBranch = "";
		adminBranch = admin.getAdminBranch(loginId);
		session.setAttribute("adminBranch",adminBranch);
	String brokerName[][] = new String[0][0];
    String brokerIds = request.getParameter("brokerIds")==null?"":request.getParameter("brokerIds");
	boolean readOnly = false;
	String dobDay = request.getParameter("dobDay")==null?"0":request.getParameter("dobDay");
	String dobMonth = request.getParameter("dobMonth")==null?"0":request.getParameter("dobMonth");
	String dobYear = request.getParameter("dobYear")==null?"0":request.getParameter("dobYear");
	String dobDay1  = request.getParameter("dobDay1")==null?"0":request.getParameter("dobDay1");
	String dobMonth1 = request.getParameter("dobMonth1")==null?"0":request.getParameter("dobMonth1");
	String dobYear1 = request.getParameter("dobYear1")==null?"0":request.getParameter("dobYear1");
	String sdate = ""; 
	String edate = ""; 
			
	java.util.Calendar calendar = java.util.Calendar.getInstance();

	if(sdate.length()>7)
	{	
		dobDay=sdate.substring(0,sdate.indexOf("-"));
   		dobMonth=sdate.substring((sdate.indexOf("-")+1),sdate.lastIndexOf("-"));
 		dobYear=sdate.substring((sdate.lastIndexOf("-")+1),sdate.length());
   		dobDay1=edate.substring(0,edate.indexOf("-"));
 		dobMonth1=edate.substring((edate.indexOf("-")+1),edate.lastIndexOf("-"));
 		dobYear1=edate.substring((edate.lastIndexOf("-")+1),edate.length());
	}
	else if(err != null)
	{	
		dobDay=""+calendar.get(java.util.Calendar.DATE);
		dobMonth=""+(calendar.get(java.util.Calendar.MONTH)+1);
		dobYear=""+calendar.get(java.util.Calendar.YEAR);
		dobDay1=""+calendar.get(java.util.Calendar.DATE);
		dobMonth1=""+(calendar.get(java.util.Calendar.MONTH)+1);
		dobYear1=""+calendar.get(java.util.Calendar.YEAR);
	}
	brokerName = broker.getBrokersHasCover(adminBranch);
	session.setAttribute("brokerName",brokerName);
	String msg = "";
	if (err.length()>0)
	{
		msg = err;
	}
%>
<br/><br/>
<body>
<form name="BrokerForm" method="post">
<table width="90%"  border="0" cellspacing="0" cellpadding="0">
            <td align="center" valign="top">
<table width="90%"  border="0" cellspacing="0" cellpadding="0">
<tr> <td width="90%" class="heading"><strong>OPEN COVER REPORTS</strong></td>
</tr>
<tr align="center">
<td colspan="3">&nbsp;</td>
</tr>
<tr align="center">
<td colspan="3">
<table width="100%"  border="0" cellspacing="1" cellpadding="0">
<tr>
<td align="center">
<%@ include file="admin_sub_menu_reports.jsp" %>
<table width="100%"  border="0" cellspacing="0" cellpadding="0">
<tr align="center">
<td colspan="3">
<table>
<tr align="left">
<td colspan="3">
<font color="red" size="3">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<%=msg%></font>
</td>
</tr>
</table>
<table align="center" border="0" cellpadding="0" cellspacing="1" width="60%" style="align:justify; 
font-family:Arial;font-size:12px;font-weight:normal;">
<tr><td></td><!-- <td align="center" class="heading"> --><span class="heading"> <b>Open Cover Report </b></span> <!-- </td> --></tr>
<br/><br/>
<tr ><td class="text">Start Date</td>
<td align="left" class="text">
<SELECT name=dobDay <%=readOnly?"DISABLED":""%>>  
<OPTION value='0' >DD</OPTION>
<%
    String sday1="";
	for(int i=1;i<=31;i++)
	{
		if(Integer.parseInt(dobDay)==i)
		{
			sday1="selected";
		}
		else
		{
			sday1="";
		}
%>
<option value=<%=i%> <%=sday1%>><%=i%></option>
 <% } %></SELECT>


<SELECT name=dobMonth <%=readOnly?"DISABLED":""%>>
<OPTION value='0' >MMM</OPTION>
<%
   String smon1="";
	String[] months={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
	for(int i=1;i<=months.length;i++)
	{
		if(Integer.parseInt(dobMonth)==i)
		{
			smon1="selected";
		}
		else
		{
			smon1="";
		}
%>
<option value=<%=i%> <%=smon1%> ><%=months[i-1]%></option>
<%  } %>
</SELECT>

<SELECT name=dobYear <%=readOnly?"DISABLED":""%>>
<OPTION value='0' >YYYY</OPTION>
<%
	java.util.Date dt=new java.util.Date();
	int maxYear=dt.getYear()+1900 ;
	String syear1="";
	for(int i=2006;i<(maxYear+2);i++)
	{
		if(Integer.parseInt(dobYear)==i)
		{
			syear1="selected";
		}
		else
		{
			syear1="";
		}
%>
<option value=<%=i%> <%=syear1%>><%=i%></option>
  <% } %>
</SELECT></td>
</tr> 
<tr ><td class="text">End Date</td>
<td align="left" class="text">
<SELECT name=dobDay1 <%=readOnly?"DISABLED":""%>>
<OPTION value='0' >DD</OPTION>
 <%
	String sday2="";
	for(int i=1;i<=31;i++)
	{
		if(Integer.parseInt(dobDay1)==i)
		{
			sday2="selected";
		}
		else
		{
			sday2="";
		}
 %>
	<option value=<%=i%> <%=sday2%>><%=i%></option>
 <% } %>
</SELECT>

   <SELECT name=dobMonth1 <%=readOnly?"DISABLED":""%>>
   <OPTION value='0' >MMM</OPTION>
   <%
		String smon2="";
		String[] months1={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
		for(int i=1;i<=months1.length;i++)
		{
			if(Integer.parseInt(dobMonth1)==i)
			{
				smon2="selected";
			}
			else
			{
				smon2="";
			}
	%>
	<option value=<%=i%> <%=smon2%> ><%=months[i-1]%></option>
	<%}%>
	</SELECT>

    <SELECT name=dobYear1 <%=readOnly?"DISABLED":""%>>
    <OPTION value='0' >YYYY</OPTION>
    <%
	    java.util.Date dt1=new java.util.Date();
		int maxYear1=dt1.getYear()+1900 ;
	    String syear2="";
		for(int i=2006;i<(maxYear1+2);i++)
		{
			if(Integer.parseInt(dobYear1)==i)
			{
				syear2="selected";
			}
			else
			{
				syear2="";
			}
	%>
	<option value=<%=i%> <%=syear2%>><%=i%></option>
	<%}%>
	</SELECT></td></tr>
	<tr ><td align="center" class="text"> Select Broker</td>
	<td align="left" class="text">
	<Select name="brokerIds" >
	<option value="Select">Select</option>
	<option value="all">All</option>
	<%
		for(int i=0;i<brokerName.length;i++)
		{ 
	%>
	<option value="<%=brokerName[i][1]%>" <%= brokerName[i][1].equalsIgnoreCase(brokerIds)?"selected":""%>> 
	<%=brokerName[i][0] %></option>
	 <%
		} 
	 %>
	</Select>	
	</td></tr>
	
	<table align="center">
	<tr><td class="text">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<input name="image" type="image"  src='<%=path%>/images/Proceed.jpg' onclick="proceed('<%=path %>')"/ > 
	</td></tr></table>
	</table>
<input type="hidden" name="requestfrom" value="brokerRpt">
</form>
</body>
</html>

<script>

 function proceed(path)
 {
	document.BrokerForm.action=path+"/admin/DatewiseBrokerDetailController";
	document.BrokerForm.submit();
	return false;
 }
</script>
