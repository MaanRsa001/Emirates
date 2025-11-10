

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
	<jsp:setProperty name="broker" property="out" value="<%= response.getWriter() %>" /> 
	</jsp:useBean>

<%
	
	session.removeAttribute("menuLink");	
	session.removeAttribute("product");	
	String error=(String)request.getAttribute("error")==null?"":(String)request.getAttribute("error");
	
	String user_Name = request.getParameter("user_Name")==null?"":request.getParameter("user_Name");
	String email_id = request.getParameter("email_id")==null?"":request.getParameter("email_id");
	String oneOff = request.getParameter("oneOff")==null?"":request.getParameter("oneOff");
	String openCover = request.getParameter("openCover")==null?"":request.getParameter("openCover");
	String login_Id = request.getParameter("login_Id")==null?"":request.getParameter("login_Id");
	String coreLoginId = request.getParameter("coreLoginId")==null?"":request.getParameter("coreLoginId");
	String user_Pass = request.getParameter("user_Pass")==null?"":request.getParameter("user_Pass");
	String editAdminCode=request.getParameter("editAdmin")==null?"":request.getParameter("editAdmin");

	String mode=request.getParameter("mode")==null?"":request.getParameter("mode");
	String editstatus = request.getParameter("editstatus")==null?"":request.getParameter("editstatus");
	String branch=request.getParameter("branch")==null?"0":request.getParameter("branch");
	String prodType=request.getParameter("prodType")==null?"":request.getParameter("prodType");
	
	String[][] userType = new String[0][0]; 
	String[][] EditUserType = new String[0][0]; 
	String[][] adminDetails = new String[0][0];
	String loadUserType = "";
	String loginBranch = "";
	String loginCountry = "";
	String loginPerson = "";
	String branchName="";
	String homeSelect ="";
	String marineSelect ="";
	String oneoffSelect ="";
	String openSelect ="";
	String loadProdType[] = new String[2];
	
	loginBranch = (String) session.getAttribute("LoginBranchCode");
	loginCountry = (String)session.getAttribute("AdminCountryId"); 
	loginPerson = (String) session.getAttribute("user"); 
	branchName = broker.getBranchName(loginBranch);
	loadUserType = broker.getAdminCategory(loginPerson,loginBranch);
	loadProdType = broker.getProductCode(login_Id);
	
	if(mode.equalsIgnoreCase("edit") && !editAdminCode.equalsIgnoreCase(""))
	{
		editstatus = "readonly";
		adminDetails =broker.getRSAIssuerDetails(editAdminCode);
		if(adminDetails.length > 0)
		{
			user_Name = adminDetails[0][1];
			login_Id = adminDetails[0][2];
			user_Pass = adminDetails[0][3];
			email_id = adminDetails[0][5];
			loadProdType = broker.getProductCode(login_Id);
			coreLoginId=adminDetails[0][6];
		}
	}
	if(loadProdType[0].equalsIgnoreCase("3") || oneOff.equalsIgnoreCase("3")){
		oneoffSelect ="checked";		
	}
	
	else{
		oneoffSelect ="";
	}
	if(loadProdType[1].equalsIgnoreCase("11") || openCover.equalsIgnoreCase("11")){
		openSelect ="checked";
	}
	else{
		openSelect ="";
	}
	
	EditUserType = broker.getRSAIssuerLogins(loginBranch);

%>

<form name="newUser" method="post" action="BrokerCreationController" >
<table width="213" border="0" cellspacing="0" cellpadding="0">

<%--@ include file="left.jsp" --%>

</tr> </table> 

</td> 
<td width="1"> </td>
<td align="left" valign="top">

<table width="100%"  border="0" cellspacing="0" cellpadding="0">
<tr> <td align="center" ></td> <tr> <tr> <td width="8" height="25" class="heading" bgcolor="#FCC721">&nbsp;</td>
<td width="1"></td> <td width="100%" class="heading"><strong>UNDERWRITER CREATION</strong></td>
</tr> <tr align="center"> <td colspan="3">&nbsp;</td> </tr> <tr align="center"> <td colspan="3">
<table width="100%" border="0" align="center" cellspacing='1'> 

<%@ include file="admin_sub_menu_RSAIssuer.jsp" %>

<%	
	if(error.length()>0)
	{
%>
	<tr>
	<td colspan="6" height="15" align="center">
	<font color="red"><%=error%></font></td>
	</tr>
<%	
	}
%>
    <tr > 
	<td colspan="6" height="15" class = "heading"> 
	<div align="center" ><B>New User Form</div>	</td> </tr> <tr> 
    </tr> 
	<tr align="center" class="formtxtr">
	<td class = "text" align="left">Select For Edit</TD>
	<td class = "text" width = "194" align="left">
	<select name="editAdmin"  id="editAdmin" class="fde1" onchange='editmode(this.value)'>
	<option value='0'>Select</option>
	<%
		if(mode.equalsIgnoreCase("edit"))
		{
				editAdminCode=request.getParameter("editAdmin")==null?"":request.getParameter("editAdmin");
		}
		
		String select3="";
		for(int i=0;i<EditUserType.length;i++)
		{
			if(editAdminCode.equalsIgnoreCase(EditUserType[i][1]))
			{
              select3="selected";
			}
			else
			{
				 select3="";	
			}
			%>
			<option value='<%=EditUserType[i][1] %>' <%=select3%> ><%=EditUserType[i][0] %>	</option>
	<%	}	%>
		</select>

		</td>
	  </tr>
	  <tr class="formtxtr"> 
		<td class = "text" width = "194" align="left">User Name</td>
		<td class = "text" align="left">
		   <input type="text" name="user_Name" class="fde1"  maxlength='25' value='<%=user_Name%>'></td>
	  </tr >
	  <tr class="formtxtr"> 
		<td class = "text" width = "194" align="left">Email Id</td>
		<td class = "text" align="left">
		   <input type="text" name="email_id" class="fde1"  maxlength='55' value='<%=email_id%>'></td>
	  </tr>
	   <tr class="formtxtr"> 
	 	<td class = "text" width = "194" align="left">Login ID</td>
		<td class = "text"  align="left">
		  <input type="text" name="login_Id" class="fde1" maxlength='15' value=<%=login_Id %> <%=editstatus%>>
		  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Space Not Allowed in LoginId and Password
		</td>
	  </tr>
	   <tr class="formtxtr"> 
	 	<td class = "text" width = "194" align="left">Core Login ID</td>
		<td class = "text"  align="left">
		  <input type="text" name="coreLoginId" class="fde1" maxlength='3' value=<%=coreLoginId %> <%=editstatus%>>
		</td>
	  </tr>
	  <tr class="formtxtr"> 
	 	<td class = "text" width = "194" align="left">Password</td>
		<td class = "text" align="left">
		  <input type="password" name="user_Pass" class="fde1" maxlength='14' 
		value=<%= user_Pass %> <%=editstatus%>></td>
	  </tr>
	 <tr class="formtxtr"> 
	 	<td class = "text" width = "194" align="left">Branch</td>
		<td class = "text" align="left">
		<input type="text" name="branch1" class="fde1" value=<%=branchName%> readonly >
		<input type="hidden" name="branch" class="fde1" value=<%=loginBranch%>>
		<input type="hidden" name="loadUserType" value=<%=loadUserType%>>
		</td>
	  </tr>
	
	<tr class="formtxtr"> 
	 	<td class = "text" width = "194" align="left">Choose Product</td>
		<td class = "text" align="left">
		<input type="hidden" name="prodType" value="Marine" />
		
		<%--
		<input type="radio" name="prodType" value="Marine" <%=marineSelect%> > Marine &nbsp;&nbsp;&nbsp;
		<input type="radio" name="prodType" value="Home" <%=homeSelect%> > Home / Travel </td>
   --%>
   		<input type="checkbox" name="oneOff" value="3" <%=oneoffSelect%>> One Off Policy &nbsp;&nbsp;&nbsp;
		<input type="checkbox" name="openCover" value="11" <%=openSelect%> > Open Cover </td>

   </tr>

	</tr>
	</table>
	
	<div align="Center"> <br/>
	<input type="image" src="../images/Submit.jpg"></div>
	<input type="hidden" name="mode" value="<%=mode%>">
	<input type="hidden" name="editstatus" value="<%=editstatus%>">
	<input type="hidden" name="editAdminCode" value="<%=editAdminCode%>">
	<input type="hidden" name="requestfrom"  value="newRSAuser">
</form>
</body>
</html>

<script language="JavaScript">

function editmode(value)
{			
	document.newUser.mode.value='edit';
	document.newUser.editAdminCode.value=value;
	document.newUser.action="newRSAUser.jsp?mode=edit&editAdmin="+value;
	document.newUser.submit();
}

</script>