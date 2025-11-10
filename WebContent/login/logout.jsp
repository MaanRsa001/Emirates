<%@ page import="com.bitmechanic.sql.*" %>
<%@ page import = "java.sql.Connection, java.sql.DriverManager" %>



<%@ include file="home1.jsp" %>


<jsp:useBean id="sestrack" class="com.maan.session.LoginSession" >
	<jsp:setProperty name="sestrack" property="*" />
</jsp:useBean>
<%String path = request.getContextPath();

String sess="";
String user="";
sess =(String) session.getAttribute("ses");
user=(String) session.getAttribute("user");
try	
{
	sestrack.endSession(sess,user);
	sestrack.updateLoginStatus(user,"loggedout",sess);
}
catch(Exception ex)
{
	System.out.println("the EXCEPTION SESSION ENDING IS "+ex.toString());
}
session.invalidate();
response.sendRedirect(path);
%>