

<%
	String path = request.getContextPath();
	session.invalidate();
	//response.sendRedirect("http://213.42.26.212/fasterquote/NewBrand/content/index.html");
	response.sendRedirect("../.."+path);
	

%>