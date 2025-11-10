
<%@ page import = "javax.servlet.http.HttpServlet.*, javax.servlet.ServletContext.*" %>
<%@ page import = "java.io.*, java.sql.*, javax.sql.*, java.util.*"%>
<%@ page import="com.maan.common.error.ErrorInfo" %>

<%
	String pathh1 = request.getContextPath();
	String basePathh1 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+pathh1+"/";
	String usrMode="";
	try
	{
		usrMode=(String)session.getAttribute("userLoginMode")==null?"":(String)session.getAttribute("userLoginMode");
		com.maan.DBCon.DBConnectionStatus.statusStatic=usrMode;
		//System.out.println("RoyalTest for databese mode checking in Marine Home1.jsp.."+usrMode);
	}
	catch(Exception e)
	{
		out.println("Exception in homeeeeeeeee"+e.toString());
		e.printStackTrace();
	}

%>