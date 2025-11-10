<%@ page language="java" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="java.util.*,java.io.*,java.text.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Emirates</title>
<style>
	 .liveHeading{
	 font-weight:700;
	 color:#2158a8;
	 font-family:"proxima-nova", sans-serif
	 }
	 .smallheading{
	 font-weight:900;
	 }
	 .pipe{
	 font-weight:900;
	 font-size:12px
	 }
	 
	 .headercontent{
	 border:none;
	 box-shadow: rgba(50, 50, 93, 0.25) 0px 2px 5px -1px, rgba(0, 0, 0, 0.3) 0px 1px 3px -1px;
	 }
	 .mylist .list-group-item {
	  box-shadow: rgba(50, 50, 93, 0.25) 0px 2px 5px -1px, rgba(0, 0, 0, 0.3) 0px 1px 3px -1px;  
	  border: 0 !important;
	  margin-top:2px;
	}
</style>
</head>
	 <body>
		<div>
			<ul class="list-group headercontent">
				  <li class="list-group-item" style="padding:0px;border:0px !important;">
					  <div class="row">
						    <div class="col-md-4">
						    	<img src="assets/logo-main.png">
						    </div>
						     <div class="col-md-4 text-center">
						      	<h2 class="liveHeading"><s:property value="#session.userLoginMode"/>Environment</h2> 
						    </div>
						     <div class="col-md-2 col-md-offset-1">  
							       <ul class="list-group mylist" style="margin-top:10px">
										  <li class="list-group-item" >
											   <div class="row">
											      <div class="col-md-4 col-xs-4">
											       <s:if test="#session.user1==getText('admin')">
											         <a class="smallheading" href="${pageContext.request.contextPath}/homeAdmin.action">Home</a>
										           </s:if>
										           <s:else>
											         <a class="smallheading" href="${pageContext.request.contextPath}/HomeUser.action">Home</a>
										           </s:else>
											      </div>
											      <div class="col-md-1 col-xs-1">
											      	<span class="pipe">|</span>
											      </div>
											      <div class="col-md-6 col-xs-6">
											      	<a class="smallheading" href="${pageContext.request.contextPath}/Loginout.action">Sign Out</a>
											      </div>
											   </div>
										  </li>
									  <li href="#" class="list-group-item">
									   <div class="row">
									      <div class="col-md-4 col-xs-4">
									        <label class="smallheading">LoginId</label>
									      </div>
									      <div class="col-md-1 col-xs-1">
									      	<span class="pipe">|</span>
									      </div>
									      <div class="col-md-6 col-xs-6">
									      <label class="smallheading"><s:property value="#session.user"/></label>
									      </div>
									   </div>
									  </li>
								   </ul>  
						    </div>
					  </div>
				  </li>
			</ul>
		</div>
	</body>
<script type="text/javascript">
function setLang(obj, url, lang)
{
	url=url.replace('request_locale=', 'request_locale='+lang);
	obj.href=url;
}
</script>
</html>