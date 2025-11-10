<%
String path = request.getContextPath();
%>
<%@ page import="java.util.List" %>

<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<title>::: Emirates :::</title>
<link href="<%= path %>/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<s:form name="userProduct" method="post">
		<table width="60%" align="center" border="1" cellspacing="1" cellpadding="2">
			<tr class="royamenuhead"> <td class="bottomtext"> <b> BROKERCODE: <s:property value="agencyCode"/> </b> </td> <td colspan="2" class="bottomtext"> <b>BROKER ORBGANIZATION: <s:property value="borganization"/></b></td> </tr>
			<tr class="royamenuhead"> <td class="bottomtext">Options </td> <td class="bottomtext"> User Name </td> <td class="bottomtext"> Login Id </td></tr>
			<s:iterator value="userInfo" id="userInfo" status="status">
				<tr> <td align="center"><input type="checkbox" class="textbox" name="userPdt[%{#status.index}]" value='<s:property value="%{userInfo.agency_code)"/>' /> </td> 
					<td class="formtxtf"> <s:property value="%{userInfo.user_name}"/> </td>
					<td class="formtxtf"> <s:property value="%{userInfo.login_id}"/> </td>
				</tr>
			</s:iterator>
		</table>

		<p align="center">
			<input type="button"  value="Cancel" onclick="return close123();">
			<input type="button" value="Proceed" onClick=" return productSelection(<s:property value="%{broker_Code}"/>)">
		</p>
		<s:hidden name="requestfrom" value="userProductPopup" />
		<s:hidden name="brokerAgency" value="agencyCode" />
		<s:hidden name="proId" value="productId" />
		<s:hidden name="brokerCompany" value="borganization" />

	</s:form>
</body>

<script>

function close123()
{
	window.close();
	return false;
}

function productSelection(userLength, proId)
{
	var userAgencyList = "";
	for(var i=0;i<userLength;i++)
	{
		if(eval("document.userProduct.userPdt"+(i+1)+".checked")){
			userAgencyList=userAgencyList+","+eval("document.userProduct.userPdt"+(i+1)+".value")
		}
	}
	var userList = userAgencyList.substring(1,userAgencyList.length)
	window.opener.form1.userAgencyList${proId}.value=userList;
	window.close();
	return false;
}
</script>
</html>