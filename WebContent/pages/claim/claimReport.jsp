<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page isELIgnored="false" %>
<html>
<head>
	<title><s:text name="application.name" /></title>	
	<link href="${pageContext.request.contextPath}/cssbootstrap/main.css" rel="STYLESHEET" type="text/css">
    <link href="${pageContext.request.contextPath}/cssbootstrap/bootstrap.min.css" rel="STYLESHEET" type="text/css">   
    <link href="${pageContext.request.contextPath}/cssbootstrap/loginStyle.css" rel="STYLESHEET" type="text/css">
    <script src="${pageContext.request.contextPath}/js/shortcut.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/cssbootstrap/camera.css" type="text/css" media="screen">
    
    <script type='text/javascript' src='${pageContext.request.contextPath}/jsc/jquery.min.js'></script>
    <script type='text/javascript' src='${pageContext.request.contextPath}/jsc/jquery.mobile.customized.min.js'></script>
    <script type='text/javascript' src='${pageContext.request.contextPath}/jsc/jquery.easing.1.3.js'></script> 
    <script type='text/javascript' src='${pageContext.request.contextPath}/jsc/camera.min.js'></script>
    
    <script type="text/javascript">
    jQuery(function(){			
		jQuery('#camera_wrap').camera({
			thumbnails: false,
			loader: 'none',
			loaderOpacity: 0,
			height: '70%',
			pagination: false
		});			
	});
	</script>
         
	<script type="text/javascript">
	window.history.forward();
	function noBack(){ 
	    window.history.forward(); 
	}
	if(document.attachEvent)
	    document.attachEvent("onkeydown", my_onkeydown_handler);
	function my_onkeydown_handler() {
	    switch (event.keyCode) {
	        case 116 : // 'F5'
	            event.returnValue = false;
	            event.keyCode = 0;
	            window.status = "Refresh Functionality Disabled";
	            break;
	    }
	}
	shortcut.add("f5",function() {window.status = "Refresh Functionality Disabled";});
	shortcut.add("Ctrl+f5",function() {window.status = "Refresh Functionality Disabled";});
	shortcut.add("Ctrl+r",function() {window.status = "Refresh Functionality Disabled";});
	shortcut.add("Ctrl+Shift+r",function() {window.status = "Refresh Functionality Disabled";});
	shortcut.add("Ctrl+n",function() {window.status = "New Window Functionality Disabled";});
	
	function callSubmit(val){
		if(val=='login'){
	    	document.form1.action="${pageContext.request.contextPath}/Loginsubmit.action";
	    }
	    document.form1.submit();
	}
	</script>
</head>
<!--[if lt IE 7 ]>   <body class="ie6">          <![endif]-->
<!--[if IE 7 ]>      <body class="ie7">          <![endif]-->
<!--[if IE 8 ]>      <body class="ie8">          <![endif]-->
<!--[if IE 9 ]>      <body class="ie9">          <![endif]-->
<!--[if (gt IE 9) ]> <body class="modern">       <![endif]-->
<!--[!(IE)]><!-->    <body class="notIE modern"> <!--<![endif]-->

<div class="bodyContainer">
	<div class="ielogo">
		<div style="widows: 50%; float:left;">
		<img id="emiratesLogo" src="images/EmiratesLogo.jpg" alt="Emirates logo" style="border-width:0px;" width="200px" height="80px">
		</div>
		<div style="widows: 50%; float:right;" align="right">
			<font color="#660085" size=5  face="sans serif" style="font-weight: bolder; line-height: 2;"> 
				<b style="font-family: Arial;"><s:text name="Report a Claim" /></b>
			</font><br/>
			<a href="${pageContext.request.contextPath}">Home</a>
		</div>
		<br class="clear"/>
	</div>
	<div class="logonBgC">
		<s:form name="searchForm" method="post" action="claimSearchPolicyNo" theme="simple">
			<div class="ieForm">
				<div class="ieField">
					<s:textfield cssClass="" cssStyle="border: none;background:transparent;color:#000000;font-weight:bold;" value="Policy No." readonly="true" maxlength="20" />
					
					<s:textfield name="policyNo" id="policyNo" cssStyle="margin-top: 25px; border: none;background:transparent;" maxlength="30" />
				</div>
				<div class="ieSubmit">
					<button type="submit" name="search" id="search" style="height:40px;width:40px; border: none; background: transparent;">&nbsp;</button>
				</div>
				<s:hidden name="swidth" id="swidth" value=""/>
				<br class="clear">
			</div>
			<div class="ieLink">
			</div>
		</s:form>
		<div class="ieErrorC">
			<s:if test="hasActionErrors()">
				<font color="red" style="list-style:none; "><s:actionerror cssStyle="background: #ffffff;"/></font>
			</s:if>
			<s:if test="hasActionMessages()">
				<s:actionmessage cssStyle="list-style:none; color:green;background: #ffffff;"/>
			</s:if>
		</div>
	</div>
	<div class="ieloginFooter" align="right">
		<div style="text-align: right; font-size: 9px;">
			2015 &copy; <a href="http://www.eminsco.com/">Copyright Emirates Insurance</a> . All rights reserved. <br/>
			Developed By  <a href="http://www.maansarovar.com" >Maan Saravor Tech Solutions Pvt. Ltd.</a>
		</div>
	</div>
</div>
<script type="text/javascript">
	var width = (window.innerWidth > 0) ? window.innerWidth : screen.width;
	document.getElementById("swidth").value=width;
</script>
</body>
</html>