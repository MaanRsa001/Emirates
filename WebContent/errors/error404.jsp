<%@page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="">
	<meta name="keywords" content="Maan Sarovar, Emirates, Marine Insurance">	
	<meta name="author" content="">
</head>
<body>
<div class="main" style="margin-top: 30px;">
<div class="container_12">
	<div id="header">
		<h1><img id="royalImg" alt="Emirates" src="<%=request.getContextPath()%>/assets/logo-main.png" ></h1>
		<br class="clear" />
	</div>
	<br class="clear"/>
	<section id="content">
		<div class="wrapper" align="center">
			<h2>404 - Page not found</h2>
			<p>The page you are looking for might have been removed had its name changed or is temporarily unavailable.</p>
			<a href='<%=request.getContextPath()%>/'>Home Page</a>
		</div>
	</section>
</div>
<div id="footer">
	   <div class="container_12">
	   		<article class="grid_4 fleft">
	        	<div class="div-footer">All Rights are Reserved for Emirates Insurance</div> 
	        </article>
	   </div>
	   <br class="clear"/>
</div>
</div>
</body>
</html>