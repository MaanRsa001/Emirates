<%@ page import = "com.maan.DBCon.DBConnectionStatus" %>
<%
	String pathh = request.getContextPath();
	String basePathh = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+pathh+"/";
	String usrModeSC=(String)session.getAttribute("userLoginMode")==null?"":(String)session.getAttribute("userLoginMode");
	DBConnectionStatus.statusStatic=usrModeSC;
	//System.out.println("RoyalTest for databese mode checking in Sessionchecknoramlhome.."+usrModeSC);
try
{
	if(session.getAttribute("ses")==null)
	{
		response.sendRedirect(pathh+"/login/error_messg.jsp");
		return;
	}
}
catch(Exception e)
{
	out.println("Exception in sessionchecknormal"+e.toString());
	e.printStackTrace();
}
%>