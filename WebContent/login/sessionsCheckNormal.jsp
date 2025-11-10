<%@ page import = "com.maan.DBCon.DBConnectionStatus" %>
<%

String usrModeSC=(String)session.getAttribute("userLoginMode")==null?"":(String)session.getAttribute("userLoginMode");
DBConnectionStatus.statusStatic=usrModeSC;
String pathh = request.getContextPath();
String basePathh = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+pathh+"/";
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
//...Session Login Check...///
	String sescon = "";
	if(session.getAttribute("sessionCheckCondition")!=null)
		sescon = (String)session.getAttribute("sessionCheckCondition");
	String loginUserTypess = "";
	if(session.getAttribute("user1")!=null)
		loginUserTypess = (String)session.getAttribute("user1");
	if(!sescon.equalsIgnoreCase("Yes")&&!loginUserTypess.equalsIgnoreCase("admin"))
	{
		String sessionMaster_id[][] = new String[0][0];
		String tracking_id[][] = new String[0][0];
		
		sessionMaster_id=com.maan.services.util.runner.multipleSelection("select session_id,login_id from session_master where session_id='"+(String)session.getAttribute("ses")+"'");
		tracking_id=com.maan.services.util.runner.multipleSelection("select session_id,login_id from tracking_master where session_id='"+(String)session.getAttribute("ses")+"'  order by login_id  desc");
		
		try
		{
			if(sessionMaster_id.length>0)
			{
				if(!((String)session.getAttribute("user")).equalsIgnoreCase(sessionMaster_id[0][1]))
				{
					System.out.println("From Menu ID from session_master  "+sessionMaster_id[0][0]);
					System.out.println("From Menu  USERNAME from session_master  "+sessionMaster_id[0][1]);
					response.sendRedirect(pathh+"/login/error_messg.jsp");
					return;
				}
				if(tracking_id.length>0)
				{
					if(!((String)session.getAttribute("user")).equalsIgnoreCase(tracking_id[0][1]))
					{
						System.out.println("From Menu  ID from tracking_master  "+tracking_id[0][0]);
						System.out.println("From Menu  USERNAME from tracking_master  "+tracking_id[0][1]);
						response.sendRedirect(pathh+"/login/error_messg.jsp");
						return;
					}
					if(!(sessionMaster_id[0][1]).equalsIgnoreCase(tracking_id[0][1]))
					{
						response.sendRedirect(pathh+"/login/error_messg.jsp");
						return;
					}
				}			
			}
			else
			{
				response.sendRedirect(pathh+"/login/error_messg.jsp");
				return;
			}
		}catch(Exception e)
		{
			System.out.println("ERROR NOT AVAILABLE SESSION ID\n "+e.toString());
		}
	}
%>