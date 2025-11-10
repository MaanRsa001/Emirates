<%@ page language="java" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="java.util.*,java.io.*,java.text.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<title>Emirates</title>
</head>
<table border="0"  cellspacing="0" cellpadding="0" width="100%">
	<s:url forceAddSchemeHostAndPort="true" includeParams="all" var="myurl" escapeAmp="false" encode="false">
		<s:param name="request_locale"/>
	</s:url>
	<tr >
		<td bgColor=#ffffff width="20" align="right"></td>
		<td><img src="assets/logo-main.png"></td>
		<td bgColor=#ffffff width="400" align="right"><font color="red" style="font: bold;font-size: 30px;"><s:property value="#session.userLoginMode"/>&nbsp;&nbsp;Environment</font></td>
		<td bgColor=#ffffff width="300" align="right">
		<%--<s:if test='#session.LANG == "Y"'>
			<a href="" onclick="setLang(this, '<s:property value="#myurl"/>', 'en')" >English</a>&nbsp;&nbsp;|&nbsp;&nbsp;
			<a href="" onclick="setLang(this, '<s:property value="#myurl"/>', 'ar')" >Arabic</a>&nbsp;&nbsp;&nbsp;&nbsp;
		</s:if>--%>
		<s:if test="#session.user1==getText('admin')">
			<a href="${pageContext.request.contextPath}/homeAdmin.action">Home</a>&nbsp;&nbsp;|&nbsp;&nbsp;
		</s:if>
		<s:else>
			<a href="${pageContext.request.contextPath}/HomeUser.action">Home</a>&nbsp;&nbsp;|&nbsp;&nbsp;
		</s:else>				
			<a href="${pageContext.request.contextPath}/Loginout.action">Sign Out</a>&nbsp;&nbsp;&nbsp;&nbsp;
		</td>
	 </tr>
	 <tr >
		 <td colspan="4" align="right">
		 	<font style="font-weight: bold;color:#000000"> LoginId&nbsp;:&nbsp;&nbsp;<s:property value="#session.user"/></font>&nbsp;&nbsp;
		</td>
	</tr>
</table>
<script type="text/javascript">
function setLang(obj, url, lang)
{
	url=url.replace('request_locale=', 'request_locale='+lang);
	obj.href=url;
}
</script>
