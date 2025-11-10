<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
        
       	<link href="${pageContext.request.contextPath}/css/displaytag.css" rel="stylesheet" type="text/css">
        <meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page"> 
		<link href="css/styles.css" rel="stylesheet" type="text/css" />
		<style>
			.refMsg{
				font-size: 25px !important;
			}
		</style>
    </head>

<body>
<s:form name="referralStatusInfo" theme="simple" >
	<table width="80%" border="0" cellspacing="0" cellpadding="2" align="center" bgcolor="#FFFFFF">
		<tr>
			<td class="heading" width="100%">	
     			<s:label key="premiumInfo.referralStatusInfo" theme="simple"/>			
			</td>
		</tr>	
		<tr>
			<td class="bg" align="center" height="150px" style="refMsg">
				<b><s:label key="premiumInfo.quoteNo" theme="simple"/>&nbsp;:&nbsp;<span style="color:blue; font-weight:bold"><s:property value="quoteNo"/></span><br/>
				<s:label key="premiumInfo.referralStatus.msg" theme="simple"/></b>				    
			</td>
		</tr>
		<tr>
			<td>
				&nbsp;
			</td>
		
		</tr>
		<tr align="center">
			<td class="text" colspan="7">			
				<s:submit type="submit" name="submit" value="Proceed" cssClass="btn" onclick="SubmitForm()"/>
			</td>
		</tr>
	</table>
</s:form>	
</body>
<script type="text/javascript">
function SubmitForm()
{
	document.referralStatusInfo.action='${pageContext.request.contextPath}/getOCAjaxReferal.action';
	//document.referralStatusInfo.submit();
}
</script>
</html>
