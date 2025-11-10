<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>Saudi United Cooperative Insurance Company</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
<%
   try
   {
   		if(session!=null){
   			session.invalidate();
   			session = request.getSession(true); 
   		}
        // if(session.getAttribute("user") !=null)
		//	session.removeAttribute("user");
   }
   catch(Exception e)
   {
	   System.out.println("ROyalTest Exception in Login page"+e.toString());
	   e.printStackTrace();
   }
	
	String path = request.getContextPath();
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0); 
%>

<jsp:useBean id="log" class="com.maan.session.LoginSession" >
<jsp:setProperty name="log" property="*" />
</jsp:useBean>

<style type="text/css">
<!--
	.style2 {font-size: 12pt}
-->
</style>
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/css/displaytag.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/css/style1.css" rel="stylesheet" type="text/css">
</head>
<%@ page import = "java.io.*,java.net.*, java.util.*, java.sql.*" buffer="100kb"%>
<%@ page import = "javax.servlet.http.HttpServlet.*, javax.servlet.ServletContext.*" %>
<body>
<form name="form1" method="post" action="" >
<%
			//session = request.getSession(true); 
			//String sessionid = request.getSession().getId();
			String sessionid = session.getId();
			Random rndm = new Random();
			String rnd = ""+rndm.nextInt(99999);
			String sesrnd = sessionid + rnd;
			String userName = "";
			userName = request.getParameter("userName");
			userName = userName == null ? "" : userName;
			String ipAddress="";
			InetAddress ip=InetAddress.getLocalHost();
			ipAddress=request.getRemoteAddr();
			log.setUser(userName);
			synchronized(this) 
			{
				session.setAttribute("ses",sesrnd);
				//session.setAttribute("rsa_type","s");
				session.setAttribute("user",userName);
				session.setAttribute("userLoginMode","Live");

				//session.setAttribute("mode","s");
				session.removeAttribute("b2c");
				session.setAttribute("b2c","b2c");
				session.setAttribute("usertype",log.getUserType());
				session.setAttribute("sessionCheckCondition","Yes");
				session.setAttribute("bcust","b2c");
			}
			/*log.strtSession(sesrnd,userName,ipAddress);*/
			log.insertLoginStatus(userName,"loggedout",sesrnd);	

			//response.sendRedirect("http://192.168.1.35:4020/E-Cargo-Global/login/ProductSelection.jsp");
			//response.sendRedirect(path+"/login/ProductSelection.jsp?bcust=b2c");
			response.sendRedirect(path+"/login/ProductSelection.jsp");
%>
<input type="hidden" name="userLoginMode" value="Live">
</form>
</body>
</html>

