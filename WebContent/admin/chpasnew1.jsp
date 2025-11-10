
<%@ page import="java.util.*" %>
<%@ page import = "proj.sql.QueryBuilder,java.util.*,java.sql.*" %>


<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String usrModeHOMEADMINPASS=(String)session.getAttribute("userLoginMode")==null?"":(String)session.getAttribute("userLoginMode");
com.maan.DBCon.DBConnectionStatus.statusStatic=usrModeHOMEADMINPASS;
%><head>
<link href="<%=path%>/css/style.css" rel="stylesheet" type="text/css">
</head>
<html>


	<jsp:useBean id = "log1" class = "com.maan.admin.admLoginNew">
	<jsp:setProperty name = "log1" property = "*" />
	<jsp:setProperty name = "log1" property = "out" value = "<%= response.getWriter() %>" />
	</jsp:useBean>

<table width="100%" cellspacing="0" cellpadding="0">  
<tr>
<td height="150" align="center" valign="top">

<table width="100%" cellspacing="0" cellpadding="0">  
<tr>
              <td height="150" align="center" valign="top">
              <table width="100%" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td height="150" align="center" valign="top">
                    <table width="90%" cellspacing="0" cellpadding="0" align="center">
  <tr>
	<td colspan="3"></td>
  </tr>
  <tr>
	<td colspan="3" background="<%=path%>/images/curvebottom.gif" height='1'></td>
  </tr>
  <tr>
  <td colspan="2" align="right" width="20"></td>
  <td  height="20"></td></tr>
  <tr>
    <td  width="20" align="right"></td>
    <td width="216" align="center" valign="top" ><img src="<%=path%>/images/logo.jpg" ></td>
	<td width=700>&nbsp;</td>
  </tr>
  <tr>
  <td colspan="2" width="20" align="right"></td>
  <td height="20"></td></tr>
  <%--<tr>
	<td colspan="3" background="<%=path%>/images/curvebottom.gif" height='1'></td>
  </tr>
--%></table>
                    </td>
                  </tr>
              </table>

</td>
</tr> 
</table> 

<%

/*String nuuser=request.getParameter("nuuser")==null?"":request.getParameter("nuuser");
String pass1=request.getParameter("pass1")==null?"":request.getParameter("pass1");*/

		/*try
		{
			
		}
		catch(Exception e)
		{
			out.println("Exception : " + e);
		}*/
		
		out.println("<body><form name='frm11' method='post' action='../login/logout.jsp'> <table class='blueborder' border='0' cellspacing='0' cellpadding='0' align='center' width='400'><tr> <td><table width='100%' cellspacing='1' cellpadding='0' align='center'><tr><td align='center'><span class='heading'>Information</span></td></tr><tr><td  bgcolor='#FFFFFF' align='center'>Password Successfully Changed..</td></tr></table></td></tr></table>");
	
	    out.println("<br><table border='0' align='center'><tr><td><input type = 'image' alt = 'Logout' src = '../images/Proceed.jpg'  ></td></tr></table></form></body>");

	%>

	