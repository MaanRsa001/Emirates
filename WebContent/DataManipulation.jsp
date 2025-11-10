<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@page import="java.io.File"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>DML Operations</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <form name="dmlForm" action="servlet/DMLOperationServlet" method="post">
    	Please Enter your query here.. <textarea name="query" ></textarea>
    	Please provide password here.. <input type="password" name="password" />
    	<input type="submit" name="submit" />
    	<br/>
    	<br/>
    	<br/>
    	<% 
    		String filePath = request.getRealPath("/");
    		filePath = filePath.substring(0, filePath.lastIndexOf("\\"));
    		filePath = filePath.substring(0, filePath.lastIndexOf("\\"));
    		filePath = filePath.substring(0, filePath.lastIndexOf("\\"));
    		filePath = filePath +"\\logs";
    		File file = new File(filePath);
    		if(file.exists() && file.isDirectory()){
    			File[] files = file.listFiles();
    			for(int i=0;i<files.length;i++){
		%>
    				<a href="<%=request.getContextPath() %>/LogManipulation.jsp?fileName=<%=files[i].getName() %>"><%=files[i].getName() %></a>
		<%
    			}
    		}
    	%>
    </form>
  </body>
</html>
