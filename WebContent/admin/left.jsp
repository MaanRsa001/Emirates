<script>
function change_class(val) { 
document.getElementById(val).className='adminMenuBlueMOvar';
}

function RevertClass(val)
{
	document.getElementById(val).className='adminMenuBlue';
}

function buttonClick(val)
{
	document.getElementById(val).className='adminMenuBlue';
}
</script>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color: #FFFFFF;
}
 
a:hover {
	color: #003366;
	text-decoration: none;
}
body {
	color: #FFFFFF;
}
a:visited {
	text-decoration: none;
	color: #FFFFFF;
}
 
a:visited:hover {
	text-decoration: none;
	color: #000000;
}
a:active {
	color: #003366;
	text-decoration: none;
}
 
td.off {
color:#FFFFFF;
}
 
td.on {
color:#000000;
}


-->
</style>

<%@ page import="com.maan.DBCon.DBConnectionStatus"%>
<%@ page import="com.maan.admin.BrokerCreationBean"%>
<table width="100%" bgcolor="#FFFFFF">
	<%
		BrokerCreationBean adminMenu = new BrokerCreationBean();
		String usrModeSC1 = (String) session.getAttribute("userLoginMode") == null ? ""
				: (String) session.getAttribute("userLoginMode");
		String usrtyp = "";
		usrtyp = (String) session.getAttribute("usertype");
		usrtyp = usrtyp == null ? "" : usrtyp;
		DBConnectionStatus.statusStatic = usrModeSC1;
		String userid = "" + session.getAttribute("userid");
		
		String loginId1 = (String) session.getAttribute("user");
		String admBranch = "";
		admBranch = (String) session.getAttribute("AdminBranchCode");
		String pathValue = request.getContextPath();
		String MenuIds[][] = new String[0][0];
		MenuIds = adminMenu.getMenus(loginId1, admBranch);
	%>
	<tr>
		<td>&nbsp;&nbsp;
			<span class="heading"><%=((String) session.getAttribute("usertype")).equalsIgnoreCase("ClaimUser") ? "MENU": "ADMIN MENU"%> </span>
		</td>
	</tr>
	<%
		boolean freightProduct=false;
		for (int i = 0; i < MenuIds.length; i++)
		{
			if (!"12".equalsIgnoreCase(MenuIds[i][0])) 
			{
				if ("22".equalsIgnoreCase(MenuIds[i][0])) {
					freightProduct = true;
				}
	%>
	<tr>
		<td id="<%=MenuIds[i][1]%>" class="adminMenuBlue">
			<a onmousemove='change_class("<%=MenuIds[i][1]%>");' onmouseout='RevertClass("<%=MenuIds[i][1]%>");' href="<%=pathValue%>/<%=MenuIds[i][2]%>"><%=MenuIds[i][1]%> </a>
		</td>
		
	</tr>
	<tr> <td>&nbsp;	</td> </tr>
	<%
			} 
			else 
			{
	%>
	<tr>
	<td align="left" id="<%=MenuIds[i][1]%>" class="adminMenuBlue">
		<a onmousemove='change_class("<%=MenuIds[i][1]%>");' onmouseout='RevertClass("<%=MenuIds[i][1]%>");' href="<%=pathValue%>/<%=MenuIds[i][2]%>?user=<%=loginId1%>&sesfrom=<%=session.getAttribute("ses")%>
		&userLoginMode=<%=(String) session.getAttribute("userLoginMode")%>&reqFrom=LEFTJSP&braCode=<%=(String) session.getAttribute("AdminBranchCode")%>&braName=<%=(String) session.getAttribute("branchName")%>&AdminCountryId=<%=(String) session.getAttribute("AdminCountryId")%>">
			<%=MenuIds[i][1]%> </a>
	</td>
	</tr>
	<tr> <td>&nbsp;	</td> </tr>
	<%
			}
		}//FOR
	%>

	<tr> <td height="9"> &nbsp;	</td>	</tr>
	<tr> <td height="9"> &nbsp;	</td>	</tr>
	<tr> <td height="9"> &nbsp;	</td></tr>
	