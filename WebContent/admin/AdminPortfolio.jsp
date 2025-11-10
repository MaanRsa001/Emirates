<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
%>

<html>
<%@ include file="header.jsp" %>
    <head>
        <title></title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
        <link href="../css/style.css" rel="stylesheet" type="text/css">
        <style type="text/css">
            <!--
				.style1 {color: #FF0000}
            -->
        </style>
    </head>

<jsp:useBean id= "admin" class = "com.maan.admin.AdminBean">
<jsp:setProperty name= "admin"   property = "*"/>
</jsp:useBean>

<jsp:useBean id="broker" class="com.maan.admin.BrokerCreationBean">
<jsp:setProperty name="broker" property="out" value="<%= response.getWriter() %>" /> 
</jsp:useBean>

<%  
	String loginId = (String) session.getAttribute("user");
	String broker_codes="";
	broker_codes = admin.getBrokerCodes(loginId); 
	broker_codes = broker_codes == null ? "" : broker_codes;
	session.setAttribute("broker_codes",broker_codes);

	String loginProIds = "";
	loginProIds = admin.getLoginProIds(loginId);
	loginProIds = loginProIds == null ? "": loginProIds;
	session.setAttribute("loginProIds",loginProIds);
	
	boolean isEditable=false,disp=true,readOnly=false;
	com.maan.services.util.dataCollection data=new com.maan.services.util.dataCollection();
	String[][] products=broker.getMarineProducts(braCode,loginProIds);
	String pid=request.getParameter("pid")==null?"":request.getParameter("pid");
	if(pid.length()>0)
	{
		session.removeAttribute("pid");
		session.setAttribute("pid",pid);
	}
	else
	{
		pid=session.getAttribute("pid")==null?"":(String)session.getAttribute("pid");
	}
%>
<br/><br/>
<body >
<%@ include file="admin_sub_menu_reports.jsp" %>
<form name="personal" method="post" action="BrokerCreationController">
<table width="90%"  border="0" cellspacing="0" cellpadding="0">
	<td width="1"></td>
            <td align="left" valign="top">
<table width="90%"  border="0" cellspacing="0" cellpadding="0">
<tr> <td align="center" ></td> <tr>
<td width="1"></td> <td width="90%" class="heading"><strong>PORTFOLIO</strong></td>
</tr>
	<tr align="center">
	<td colspan="3">&nbsp;</td>
	</tr>
	<tr align="center">
	<td colspan="3">
		<table width="90%"  border="0" cellspacing="1" cellpadding="0">
		<tr><td align="center"> 
<%
	String sdate=request.getParameter("data1")==null?"":request.getParameter("data1");
	String edate=request.getParameter("data2")==null?"":request.getParameter("data2");
	String dobDay=request.getParameter("dobDay")==null?"0":request.getParameter("dobDay");
	String dobMonth=request.getParameter("dobMonth")==null?"0":request.getParameter("dobMonth");
	String dobYear=request.getParameter("dobYear")==null?"0":request.getParameter("dobYear");
	String dobDay1=request.getParameter("dobDay1")==null?"0":request.getParameter("dobDay1");
	String dobMonth1=request.getParameter("dobMonth1")==null?"0":request.getParameter("dobMonth1");
	String dobYear1=request.getParameter("dobYear1")==null?"0":request.getParameter("dobYear1");
	String freightStatus="";
		freightStatus=request.getParameter("freightStatus")==null?"":request.getParameter("freightStatus");
	java.util.Calendar calendar	 = java.util.Calendar.getInstance();
	if(sdate.length()>7)
	{
		dobDay=sdate.substring(0,sdate.indexOf("-"));
		dobMonth=sdate.substring((sdate.indexOf("-")+1),sdate.lastIndexOf("-"));
		dobYear=sdate.substring((sdate.lastIndexOf("-")+1),sdate.length());

		dobDay1=edate.substring(0,edate.indexOf("-"));
		dobMonth1=edate.substring((edate.indexOf("-")+1),edate.lastIndexOf("-"));
		dobYear1=edate.substring((edate.lastIndexOf("-")+1),edate.length());
	}
	else if(request.getAttribute("errorDetail")==null)
	{
		String [][]currDate = new String[0][0];
		currDate = admin.getTodaysDate(braCode);
		if(currDate.length > 0)
		{
			dobDay = currDate[0][0];
			dobMonth = currDate[0][1];
			dobYear = currDate[0][2];
			
			dobDay1 = currDate[0][0];
			dobMonth1 = currDate[0][1];
			dobYear1 = currDate[0][2];
		}
		/*dobDay=""+calendar.get(java.util.Calendar.DATE);
		dobMonth=""+(calendar.get(java.util.Calendar.MONTH)+1);
		dobYear=""+calendar.get(java.util.Calendar.YEAR);
		dobDay1=""+calendar.get(java.util.Calendar.DATE);
		dobMonth1=""+(calendar.get(java.util.Calendar.MONTH)+1);
		dobYear1=""+calendar.get(java.util.Calendar.YEAR);*/
	}
%>	  
<table width="80%"  align="center"  border="0" cellspacing="0" cellpadding="0">
<tr align="center"><td colspan="3">
	<table style="align:justify; font-family:Arial;font-size:12px;font-weight:normal;">
	<tr align="left">
	<td colspan="3">
	<font color="red" size="3" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<%=request.getAttribute("errorDetail")!=null?(String)request.getAttribute("errorDetail"):"&nbsp;"%>
	</font>
	</td>
	</tr>
	</table>

<%
	//String[][] brokers=admin.getBrokerksByAdmin();
%>
	
	<table width="90%" border ="0" cellpadding="0" cellspacing="0">
	<tr  height="25">
	<td align="right" width="45%">
	<font size="2"><b>Start Date :</b></td>
    <td align="left" >
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
	<%}%>
   </SELECT>
	<!--  DOB MONTH    ----------->
   <SELECT name=dobMonth <%=readOnly?"DISABLED":""%>>
   <OPTION value='0'  >MMM</OPTION>
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
	<%}%>
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
		  <%}%>
	  </SELECT>
	  </td></tr>
	  <tr height="25">
	  <td align="right" width="45%"><font size="2"><b>End Date :</b></td>
	  <td align="left">
	  <SELECT name=dobDay1 <%=readOnly?"DISABLED":""%>>
      <OPTION value='0'>DD</OPTION>
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
	  </SELECT>
	  </td>
      </tr>
	  </table>
	
	  <table width="90%">
	  <tr>
	  <td align="right"><b>Select Product :</b></td>
	  <td colspan="3" align="left">
	  <select name="pid">
	  <option value="all">All</option>
<%
      for(int i=0;i<products.length;i++)
	  {
			String sele="";
			if(pid.equals(products[i][0]))
			{
				sele="Selected";
			}
			else
			{
				sele="";
			}
%>
		<option value="<%=products[i][0]%>" <%=sele%>><%=products[i][1]%></option>
	<%
		}
		String sel = "";
		if(freightStatus.equalsIgnoreCase("Yes"))
			sel = "selected";
		else
			sel = "";
	boolean freightProduct=false;
	if(freightProduct){
		%>
			<option value="FF" <%=sel%>>FREIGHT FORWARDER</option>
		<%
			}
		 %></select>
	</td>
	</tr>
	<tr>
	<td height="25" width="45%"></td>
	<td height="25" align="left">
		<input type="radio" name="rep" value="c" <%=(request.getParameter("rep")!=null && "c".equalsIgnoreCase(request.getParameter("rep")))?"checked":"checked"%>>
		Pending Quotations
	</td>
	</tr>
	<tr>
	<td height="25" width="178"></td>
	<td align="left">
	<input type="radio" name="rep" value="p" <%=(request.getParameter("rep")!=null  && "p".equalsIgnoreCase(request.getParameter("rep")))?"checked":""%>>
	Policy Generated
	</td>
	</tr>
	<!--//Rajesh Modified on 12/05 -->
	<tr>
	<td height="25" width="178"></td>
	<td align="left">
	<input type="radio" name="rep" value="pc" <%=(request.getParameter("rep")!=null  && "pc".equalsIgnoreCase(request.getParameter("rep")))?"checked":""%>>
	For Policy Canceled
	</td>
	</tr>
	<tr>
	<td height="25" width="178"></td>
	<td align="left">
	<input type="radio" name="rep" value="r" <%=(request.getParameter("rep")!=null  && "r".equalsIgnoreCase(request.getParameter("rep")))?"checked":""%>>
	Pending Reports
	</td>
	</tr>
	<tr>
	<td height="25" width="178"></td>
	<td align="left">
	<input type="radio" name="rep" value="A" <%=(request.getParameter("rep")!=null  && "A".equalsIgnoreCase(request.getParameter("rep")))?"checked":""%>>
	Commodity Audit Reports
	</td>
	</tr>
	</table>
</td>
</tr>
</table>

<table width="90%">
<tr align="center"><td height="1" colspan="3"></td></tr>
<tr align="center">
<td colspan="3">
	<table width="80%"  border="0" cellspacing="0" cellpadding="0">
		<tr><td height="32" align="center" valign="middle">&nbsp;&nbsp;&nbsp;
			<input name="image" type="image" src='<%=path%>/images/Proceed.jpg' / >
			</td>
		</tr>
	</table>
</td>
</tr>
</table>
<input type="hidden" name="requestfrom" value="portfolio">
<input type="hidden" name="search_option">
</form>
</body>
<script>
function policy()
{
	 if(document.personal.rep[0].checked ==true)
	 {
		 document.personal.dobDay.disabled=true;
		 document.personal.dobMonth.disabled=true;
		 document.personal.dobYear.disabled=true;
		 document.personal.dobDay1.disabled=true;
		 document.personal.dobMonth1.disabled=true;
		 document.personal.dobYear1.disabled=true;
		 document.personal.dobDay.value='0';
		 document.personal.dobMonth.value='0';
		 document.personal.dobYear.value='0';
		 document.personal.dobDay1.value='0';
		 document.personal.dobMonth1.value='0';
		 document.personal.dobYear1.value='0';
	 }

	 if(document.personal.rep[1].checked == true)
	 {
		 document.personal.dobDay.disabled=true;
		 document.personal.dobMonth.disabled=true;
		 document.personal.dobYear.disabled=true;
		 document.personal.dobDay1.disabled=true;
		 document.personal.dobMonth1.disabled=true;
		 document.personal.dobYear1.disabled=true;
		 document.personal.dobDay.value='0';
		 document.personal.dobMonth.value='0';
		 document.personal.dobYear.value='0';
		 document.personal.dobDay1.value='0';
		 document.personal.dobMonth1.value='0';
		 document.personal.dobYear1.value='0';
	 }
	 if(document.personal.rep[2].checked == true)
	 {
		 document.personal.company.disabled=true;
		 document.personal.dobDay.disabled=false;
		 document.personal.dobMonth.disabled=false;
		 document.personal.dobYear.disabled=false;
		 document.personal.dobDay1.disabled=false;
		 document.personal.dobMonth1.disabled=false;
		 document.personal.dobYear1.disabled=false;
		 document.personal.company.value='';
	 }
}

function search_details()
{
	document.personal.search_option.value="YES";
	document.personal.action="Portfolio_ByDate.jsp";
	document.personal.submit();
}
</script>
</html>
