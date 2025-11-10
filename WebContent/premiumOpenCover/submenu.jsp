

<%
if(session.getAttribute("ses")==null)
{

response.sendRedirect("../login/error_messg.jsp");
return;

}

String usrModeSC=(String)session.getAttribute("userLoginMode")==null?"":(String)session.getAttribute("userLoginMode");
com.maan.DBCon.DBConnectionStatus.statusStatic=usrModeSC;
%>