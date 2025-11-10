

<%@ page import = "java.io.*, java.util.*, java.sql.*" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ include file="header.jsp" %>

<html>
	<head>
		<title>Emirates</title>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<style type="text/css">
		<!--
			body {
				margin-left: 0px;
				margin-top: 0px;
				margin-right: 0px;
				margin-bottom: 0px;
			}
		-->
	    </style>
	<link href="style.css" rel="stylesheet" type="text/css">
	<style type="text/css">
<!--
.style1 {color: #E8E6DC}
-->
    </style>
	</head>

	<jsp:useBean id="broker" class="com.maan.admin.BrokerCreationBean">
	</jsp:useBean>
	<jsp:useBean id="adminBean" class="com.maan.admin.AdminBean">
	</jsp:useBean>

<%
	String error=(String) request.getAttribute("error")==null?"":(String)request.getAttribute("error");

	String user_Name = request.getParameter("user_Name")==null?"":request.getParameter("user_Name");
	String email_id = request.getParameter("email_id")==null?"":request.getParameter("email_id");
	String login_Id = request.getParameter("login_Id")==null?"":request.getParameter("login_Id");
	String coreLoginId = request.getParameter("coreLoginId")==null?"":request.getParameter("coreLoginId");
	String user_Pass = request.getParameter("user_Pass")==null?"":request.getParameter("user_Pass");
	String editAdminCode=request.getParameter("editAdmin")==null?"":request.getParameter("editAdmin");
	String mode=request.getParameter("mode")==null?"":request.getParameter("mode");
	String editstatus = request.getParameter("editstatus")==null?"":request.getParameter("editstatus");
	String branch=request.getParameter("branch")==null?"0":request.getParameter("branch");
	String prodType=request.getParameter("prodType")==null?"":request.getParameter("k");
	String oneOff = request.getParameter("oneOff")==null?"":request.getParameter("oneOff");
	String openCover = request.getParameter("openCover")==null?"":request.getParameter("openCover");
	System.out.println("oneOff]openCover]::>"+oneOff+openCover);
		
	String status="";
	String status1="";
	String loginBranch = "";
	loginBranch = (String) session.getAttribute("LoginBranchCode");
	
	String loginPerson = "";
	loginPerson = (String) session.getAttribute("user"); 
	String loginCountry = "";
	loginCountry = (String)session.getAttribute("AdminCountryId");
	String admTyp="";	
		//For getting admin login products from Login_master
		String userLogin = "";
		userLogin = (String)session.getAttribute("user");
		userLogin = userLogin == null ? "" : userLogin;
		String pro_Id="";
		pro_Id = (String)session.getAttribute("pro_Id");
		pro_Id = pro_Id == null ? "" : pro_Id;
		if(pro_Id.length()<=0)
		{
			pro_Id = adminBean.getLoginProIds(userLogin); 
		}
		else
		{
			session.setAttribute("pro_Id",pro_Id);
		}
		//For Getting alloted Broker codes for admin login
		String broker_codes="";
		broker_codes = (String)session.getAttribute("broker_codes");
		broker_codes = broker_codes == null ? "" : broker_codes;
		if(broker_codes.length()<=0)
		{
			broker_codes = adminBean.getBrokerCodes(loginBranch);
			broker_codes = broker_codes==null?"":broker_codes;
			session.setAttribute("broker_codes",broker_codes);
		}
		else
		{
			session.setAttribute("broker_codes",broker_codes);
		}
	String[][] brokers = new String[0][0];
	String[][] editDetails = new String[0][0];
	
		
	if(mode.equalsIgnoreCase("edit"))
	{
		editDetails = broker.getEditRSABrokerProductDetails(login_Id);
	}
	brokers = broker.getRSABrokerProductDetails(pro_Id,broker_codes,loginBranch);
	
%>


<form name="newUser" method="post" action="BrokerCreationController" >
<table width="213" border="0" cellspacing="0" cellpadding="0">

<%--@ include file="left.jsp"  --%>

</tr> </table> 

</td> 
<td width="1"> </td>
<td align="left" valign="top">

<table width="100%"  border="0" cellspacing="0" cellpadding="0">
<tr> <td align="center" ></td> <tr> <tr> <td width="8" height="25" class="heading" bgcolor="#FCC721">&nbsp;</td>
<td width="1"></td> <td width="100%" class="heading"><strong>ADMIN INFORMATION </strong></td>
</tr> <tr align="center"> <td colspan="3">&nbsp;</td> </tr> <tr align="center"> <td colspan="3">


<%@ include file="admin_sub_menu_RSAIssuer.jsp" %>
<table width="1200" border="0" align="center" cellspacing='1' cellpadding="1"> 
<%	
	if(error.length()>0)
	{
%>
	<tr>
	<td colspan="8" height="15" align="center">
	<font color="red"><%=error%></font></td>
	</tr>
<%	
	}
%>
	<tr>
   <td colspan="8" height="15" class = "heading"> 
	<div align="center" ><B>Underwriter : <%=user_Name%></div>	</td> </tr> 
	<tr >
	<td  width="10" align="center" class="text"><b>Select</b></td>
	<td  width="200" align="center" class="text"><b>Broker Name</b></td>
	<td  width="150" align="center" class="text"><b>Product Name</b></td>
	<!--<td  width="50" align="center" class="text"><b>Commission %</b></td>-->
	<td  width="200" align="center" class="text"><b>Start Date</b></td>
	<td  width="200" align="center" class="text"><b>End Date</b></td>
	<td  width="100" align="center" class="text"><b>Activate</b></td>
	<!--<td  width="200" align="center" class="text"><b>Deactive Date</b></td>-->
	</tr>
	<%
          System.out.println("Enters edit details...");
		for(int i=0;i<brokers.length;i++)
		{ 	
			String startDay = "";
			String endDay = "";
			String startMonth = "";
			String endMonth = "";
			String startYear = "";
			String endYear = "";
			String activeStatus = "";
			String commission = "";
			String deactiveDay = "0";
			String deactiveMonth = "0";
			String deactiveYear = "0";
			String broSel = "";
			if(editDetails.length>0 )
			{
			    System.out.println("Enters edit details..."+editDetails.length);
				for(int j=0;j<editDetails.length;j++)
				{
					if(brokers[i][2].equalsIgnoreCase(editDetails[j][0])&&brokers[i][3].equalsIgnoreCase(editDetails[j][1]))
					{
						commission = editDetails[j][2]!=null?editDetails[j][2]:"";
						startDay = editDetails[j][3]!=null?editDetails[j][3]:"";
						startMonth = editDetails[j][4]!=null?editDetails[j][4]:"";
						startYear = editDetails[j][5]!=null?editDetails[j][5]:"";
						endDay = editDetails[j][6]!=null?editDetails[j][6]:"";
						endMonth = editDetails[j][7]!=null?editDetails[j][7]:"";
						endYear = editDetails[j][8]!=null?editDetails[j][8]:"";
						activeStatus = editDetails[j][9]!=null?editDetails[j][9]:"";
						deactiveDay = editDetails[j][10]!=null?editDetails[j][10]:"0";
						deactiveMonth = editDetails[j][11]!=null?editDetails[j][11]:"0";
						deactiveYear = editDetails[j][12]!=null?editDetails[j][12]:"0";		
						broSel = "Yes";
					}

				}
			}
			else
			{
				
				startDay = "";endDay = "";startMonth = "";endMonth = "";startYear = "";endYear = "";commission = "";activeStatus = ""; deactiveDay = "0";deactiveMonth = "0";deactiveYear = "0";
			}
			if(broSel.length()<=0)
				broSel = request.getParameter("sel"+i)!=null?request.getParameter("sel"+i):"";
			if(startDay.length()<=0)
				startDay = request.getParameter("startDay"+i)!=null?request.getParameter("startDay"+i):"";
			if(endDay.length()<=0)
				endDay = request.getParameter("endDay"+i)!=null?request.getParameter("endDay"+i):"";
			if(deactiveDay.equals("0"))
				deactiveDay = request.getParameter("deactiveDay"+i)!=null?request.getParameter("deactiveDay"+i):"0";
			if(startMonth.length()<=0)
				startMonth = request.getParameter("startMonth"+i)!=null?request.getParameter("startMonth"+i):"";
			if(endMonth.length()<=0)
				endMonth = request.getParameter("endMonth"+i)!=null?request.getParameter("endMonth"+i):"";
			if(deactiveMonth.equals("0"))
				deactiveMonth = request.getParameter("deactiveMonth"+i)!=null?request.getParameter("deactiveMonth"+i):"0";
			if(startYear.length()<=0)
				startYear = request.getParameter("startYear"+i)!=null?request.getParameter("startYear"+i):"";
			if(endYear.length()<=0)
				endYear = request.getParameter("endYear"+i)!=null?request.getParameter("endYear"+i):"";
			if(deactiveYear.equals("0"))
				deactiveYear = request.getParameter("deactiveYear"+i)!=null?request.getParameter("deactiveYear"+i):"0";
			if(activeStatus.length()<=0)
				activeStatus = request.getParameter("activeStatus"+i)!=null?request.getParameter("activeStatus"+i):"";
			if(commission.length()<=0)
				commission = request.getParameter("comm"+i)!=null?request.getParameter("comm"+i):"0";
			java.util.Calendar calendar	 = java.util.Calendar.getInstance();
			if(startDay.length()<=0)
			{
				startDay=""+calendar.get(java.util.Calendar.DATE);
				startMonth=""+(calendar.get(java.util.Calendar.MONTH)+1);
				startYear=""+calendar.get(java.util.Calendar.YEAR);
			}
			if(endDay.length()<=0)
			{
				endDay=""+calendar.get(java.util.Calendar.DATE);
				endMonth=""+(calendar.get(java.util.Calendar.MONTH)+1);
				endYear=""+calendar.get(java.util.Calendar.YEAR);
			}
	%>
			<tr class="formtxtr" >
				<td width="10"><input type="checkbox" name="sel<%=i%>" value="Yes" <%=broSel.equalsIgnoreCase("Yes")?"checked":""%>/></td>
				<td width="200" align="left" class="text"><%=brokers[i][0]%>
				<input type="hidden" name="brokerIds<%=i%>" value="<%=brokers[i][2]%>"/>
				<input type="hidden" name="pids<%=i%>" value="<%=brokers[i][3]%>"/>
				</td>
				<%
					if(!brokers[i][3].equals("11"))
					{
				%>
				<td width="150" align="left" class="text"><%=brokers[i][1]%></td>
				<%
					}
					else
					{
				%>
				<td width="150" align="left" class="text"><button id="occ" onClick="return openCoverPopUp('<%=brokers[i][4]%>','<%=brokers[i][3]%>','<%=login_Id%>','<%=brokers[i][2]%>');"><b><%=brokers[i][1]%></b></a></button></td>
				<%
					}
				%>
				<%--  <td width="50" align="left" class="text"><input type="text" name="comm<%=i%>" value="<%=commission%>"/></td>--%>
				<td width="200" align="left" class="text"><input type="hidden" name="comm<%=i%>" value="<%=commission%>"/><SELECT name="startDay<%=i%>">  
		<OPTION value='0' >DD</OPTION>
		<%
				String sday1="";
				for(int d=1;d<=31;d++)
				{
					if(Integer.parseInt(startDay)==d)
					{
						sday1="selected";
					}
					else
					{
						sday1="";
					}
		%>
				<option value=<%=d%> <%=sday1%>><%=d%></option>
		<%
				}
		%>
				</SELECT>
				<SELECT name="startMonth<%=i%>">
				<OPTION value='0'>MMM</OPTION>
				<%
				String smon1="";
				String[] months={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
				for(int d=1;d<=months.length;d++)
				{
					if(Integer.parseInt(startMonth)==d)
					{
					smon1="selected";
					}
					else
					{
					smon1="";
					}
				%>
				<option value=<%=d%> <%=smon1%> ><%=months[d-1]%></option>
				<%}%>
				</SELECT>
				<SELECT name="startYear<%=i%>">
				<OPTION value='0' >YYYY</OPTION>
				<%
				java.util.Date dt=new java.util.Date();
				int maxYear=dt.getYear()+1900 ;
				String syear1="";
				for(int d=2008;d<(maxYear+3);d++)
				{
					if(Integer.parseInt(startYear)==d)
					{
					syear1="selected";
					}
					else
					{
					syear1="";
					}
				%>
				<option value=<%=d%> <%=syear1%>><%=d%></option>
				<%}%>
				</SELECT></td>
				<td align="left" class="text" width="200"><SELECT name="endDay<%=i%>">
			<OPTION value='0'>DD</OPTION>
			<%
			String sday2="";
			for(int d=1;d<=31;d++)
			{
				if(Integer.parseInt(endDay)==d)
				{
					sday2="selected";
				}
				else
				{
					sday2="";
				}
			%>
			<option value=<%=d%> <%=sday2%>><%=d%></option>
			<% } %>
			</SELECT>

			<SELECT name="endMonth<%=i%>">
			<OPTION value='0' >MMM</OPTION>
			<%
			String smon2="";
			String[] months1={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
			for(int d=1;d<=months1.length;d++)
			{
				if(Integer.parseInt(endMonth)==d)
				{
					smon2="selected";
				}
				else
				{
					smon2="";
				}
			%>
			<option value=<%=d%> <%=smon2%> ><%=months[d-1]%></option>
			<%}%>
			</SELECT>
			<SELECT name="endYear<%=i%>">
			<OPTION value='0' >YYYY</OPTION>
			<%
			java.util.Date dt1=new java.util.Date();
			int maxYear1=dt1.getYear()+1900 ;
			String syear2="";
			for(int d=2008;d<(maxYear1+3);d++)
			{
				if(Integer.parseInt(endYear)==d)
				{
					syear2="selected";
				}
				else
				{
					syear2="";
				}
			%>
				<option value=<%=d%> <%=syear2%>><%=d%></option>
			<%}%>
			</SELECT>
			</td>
				<td align="left" class="text" width="100"><input type="radio" name="activeStatus<%=i%>" value="Y" <%=(activeStatus.equalsIgnoreCase("Y")?"checked":"")%> <%=(activeStatus.length()<=0?"checked":"")%>/>Yes&nbsp;&nbsp;<input type="radio" name="activeStatus<%=i%>" value="N" <%=(activeStatus.equalsIgnoreCase("N")?"checked":"")%>/>No</td>
				<%-- <td align="left" class="text" width="200">
			<SELECT name="deactiveDay<%=i%>">
			<OPTION value='0'>DD</OPTION>
			<%
			String sday3="";
			for(int d=1;d<=31;d++)
			{
			if(Integer.parseInt(deactiveDay)==d)
			{
				sday3="selected";
			}
			else
			{
				sday3="";
			}
			%>
			<option value=<%=d%> <%=sday3%>><%=d%></option>
			<% } %>
			</SELECT>

			<SELECT name="deactiveMonth<%=i%>">
			<OPTION value='0' >MMM</OPTION>
			<%
			String smon3="";
			String[] months2={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
			for(int d=1;d<=months2.length;d++)
			{
			if(Integer.parseInt(deactiveMonth)==d)
			{
				smon3="selected";
			}
			else
			{
				smon3="";
			}
			%>
			<option value=<%=d%> <%=smon3%> ><%=months2[d-1]%></option>
			<%}%>
			</SELECT>
			<SELECT name="deactiveYear<%=i%>">
			<OPTION value='0' >YYYY</OPTION>
			<%
			java.util.Date dt2=new java.util.Date();
			int maxYear2=dt2.getYear()+1900 ;
			String syear3="";
			for(int d=2008;d<(maxYear2+3);d++)
			{
			if(Integer.parseInt(deactiveYear)==d)
			{
				syear3="selected";
			}
			else
			{
				syear3="";
			}
			%>
			<option value=<%=d%> <%=syear3%>><%=d%></option>
			<%}%>
			</SELECT>
			</td>--%>
			</tr>
	<%
		}
	%>
	</table>
	<div align="Center">&nbsp;</div>
	<div align="Center">
		<input type = "image"  src  = "../images/Submit.gif">
	</div>
	<input type = "hidden" name = "length" value="<%=brokers.length%>">
	<input type = "hidden" name = "mode" value="<%=mode%>">
	<input type = "hidden" name = "user_Name" value="<%=user_Name%>"/>
	<input type = "hidden" name = "email_id" value="<%=email_id%>"/>
	<input type = "hidden" name = "login_Id" value="<%=login_Id%>"/>
	<input type = "hidden" name = "coreLoginId" value="<%=coreLoginId%>"/>
	<input type = "hidden" name = "user_Pass" value="<%=user_Pass%>"/>
	<input type = "hidden" name = "branch" value="<%=loginBranch%>"/>
	<input type = "hidden" name = "editstatus" value="<%=editstatus%>">
	<input type = "hidden" name = "editAdminCode" value="<%=editAdminCode%>">
	<input type = "hidden" name = "openCover" value="<%=openCover%>"/>
	<input type = "hidden" name = "oneOff" value="<%=oneOff%>"/>
	<input type = "hidden" name = "requestfrom"  value="newRSAUserBroSelection">
</form>
</body>
</html>

<script language="JavaScript">
function openCoverPopUp(broId,pid,userLogId,bcode) 
{
	
	var URL = "../admin/OpenCoverPopUp.jsp?editbroker="+broId+"&pid="+pid+"&userLogId="+userLogId+"&brokerCode="+bcode;
	var windowName = "OpenCoverCerificatesAllocation";
	var width  = screen.availWidth;
	var height = screen.availHeight;
	var w = 750;
	var h = 460;
	var features =
		"width="          + w +
		",height="		  + h +
		",left="		  + ((0) * .5)  +
		",top="			  + ((0) * .5) +
		",directories=no" +
		",location=no"	  +
		",menubar=no"	  +
		",scrollbars=yes" +
		",status=no"	  +
		",toolbar=no"	  +
		",resizable=no";
	var strOpen = window.open (URL, windowName, features);
	strOpen.focus();
	return false;
}
</script>