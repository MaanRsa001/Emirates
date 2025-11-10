<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="../WEB-INF/displaytag.tld" prefix="display"%>
<%@ taglib uri="../WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="../WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page isELIgnored ="false" %>
<html>
<head>
<title>Core Customer Search</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="<%=path%>/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/css/displaytag.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="<%=path %>/css/calendar1.js"></script>
<style type="text/css">
<!--
.headerClass {color: #efefef;background-color: #666666;}
-->
</style>
</head>
<body >
  <form name="CoreCustomer" action="CoreCustomer.core">
  	<table align="center">
	  	<tr >
	  		<td colspan="2" align="center"><span class="heading">Customer Search</span></td>
	  	</tr>
  		<tr class="formtxtf">
  			<td>
  				Customer Name
  			</td>
  			<td>
  				<input type="text" name="customerName" value=""/>&nbsp;&nbsp;
  				<input type='button' value='Search' onClick="return getCustList()" accesskey='s'/>
  			</td>
  		</tr>
  		<tr >
	  		<td colspan="2" align="center">&nbsp;</td>
	  	</tr>
				<logic:equal name="display" scope="request" value="customerList">
			<tr>
				<td colspan="2">
					<table cellspacing="2" cellpadding="2" border=1>
						<tr>
							<td>
								<display:table name="requestScope.customerList" pagesize="10" uid="row" id="record" style="width:600px;" requestURI="/CoreCustomer.core">
								<display:setProperty name="paging.banner.one_item_found" value="" />
								<display:setProperty name="paging.banner.one_items_found" value="" />
								<display:setProperty name="paging.banner.all_items_found" value="" />
								<display:setProperty name="paging.banner.some_items_found" value="" />
								<display:setProperty name="paging.banner.placement" value="bottom" />
								<display:setProperty name="paging.banner.onepage" value="" />
								<display:setProperty name="basic.empty.showtable" value="true" />
								<display:setProperty name="paging.banner.no_items_found" value="" />
								<display:column sortable="false" style="text-align:center;" title="Select" class="formtxtc" headerClass="headerClass">
									<input type="radio" name="select" onclick="selectCustomer('${record.customerCode}','${record.arNo}','${record.firstName}')"/>
								</display:column>
								<display:column sortable="false" style="text-align:left;" title="Customer Name" property="firstName" class="formtxtc" headerClass="headerClass"/>
								<display:column sortable="false" style="text-align:left;" title="Core Application Code" property="customerCode" class="formtxtc" headerClass="headerClass"/>
								<display:column sortable="false" style="text-align:left;" title="A/R No." property="arNo" class="formtxtc" headerClass="headerClass"/>
								</display:table>
								<input type="hidden" name="cimsCode"/>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<a href="#" onClick="return fnSubmit()" class="buttonsMenu" >
		 			<img src="<%=path%>/images/Submit.jpg"> </a>
				</td>
			</tr>
			</logic:equal>
	</table>
  </form>
  <script>
  function getCustList()
  {
  	var name=document.CoreCustomer.customerName.value;
  	if(name=='')
  		alert('Please Enter Customer Name to search');
  	else
  		document.CoreCustomer.submit();
 	return false;
  }
  function selectCustomer(code, arNo, name)
  {
  	document.CoreCustomer.cimsCode.value=code;
  	if(window.opener.document.personal){
	  	window.opener.document.personal.customerCode.value =code;
	  	window.opener.document.personal.arNo.value =arNo;
	  	window.opener.document.personal.customerName.value =name;
	  	window.opener.document.personal.nameAndCode.value =name+' ('+code+')';
  	}else{
	  	/*window.opener.document.form1.mcode.value =code;
	  	window.opener.document.form1.arNo.value =arNo;
	  	window.opener.document.form1.brokerName.value =name;
	  	window.opener.document.form1.nameAndCode.value =name+' ('+code+')';*/
	  	try{
	  	window.opener.document.getElementById("customerId").value="";
	  	window.opener.document.getElementById("coreAppCode").value=code;
  		window.opener.document.getElementById("coreCustomerName").value=name;
  		window.opener.document.getElementById("custArNo").value=arNo;
  		window.opener.document.getElementById("nameAndCode").value=name+' ('+code+')';
  		}catch(e){alert(e)}
  	}
 	return false;
  }
  function fnSubmit()
  {
  	if(document.CoreCustomer.cimsCode.value =='')
  		alert('Please Select Customer');
  	else window.close();
 	return false;
  }
  </script>
  </body>
</html>
