<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page isELIgnored ="false" %>
<html>
	<head>
		<title>Core Customer Search</title>
		<link href="css/style.css" rel="stylesheet" type="text/css">
		<link href="css/displaytag.css" rel="stylesheet" type="text/css">
		<script language="JavaScript" src="css/calendar1.js"></script>
		<script>
			function stopRKey(evt) {
			  var evt = (evt) ? evt : ((event) ? event : null); 
			  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
			  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
			} 
			document.onkeypress = stopRKey;
		</script>
	</head>
<body>
  <s:form name="CoreCustomer" action="getccInfoBrokerMgm.action" theme="simple">
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
			<s:if test="'display'.equals(mode)">
			<tr>
				<td colspan="2">
					<table cellspacing="2" cellpadding="2" border=1>
						<tr>
							<td>
								<display:table name="requestScope.coreCustomerInfo" pagesize="10" uid="row" id="record" style="width:600px;" requestURI="/getCcinfoBrokerMgm.action">
								<display:setProperty name="paging.banner.one_item_found" value="" />
								<display:setProperty name="paging.banner.one_items_found" value="" />
								<display:setProperty name="paging.banner.all_items_found" value="" />
								<display:setProperty name="paging.banner.some_items_found" value="" />
								<display:setProperty name="paging.banner.placement" value="bottom" />
								<display:setProperty name="paging.banner.onepage" value="" />
								<display:setProperty name="basic.empty.showtable" value="true" />
								<display:setProperty name="paging.banner.no_items_found" value="" />
								<display:column sortable="false" style="text-align:center;" title="Select">
									<input type="radio" name="select" onclick="selectCustomer('${record.CIMSNO}','${record.ARACC}','${record.CUSTNAME}')"/>
								</display:column>
								<display:column sortable="false" style="text-align:left;" title="Customer Name" property="CUSTNAME"/>
								<display:column sortable="false" style="text-align:left;" title="Core Application Code" property="CIMSNO"/>
								<display:column sortable="false" style="text-align:left;" title="A/R No." property="ARACC"/>
								</display:table>
								<input type="hidden" name="cimsCode"/>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="button"  name="submit" value="Submit" onClick="return fnSubmit()" class="btn"/>
				</td>
			</tr>
			</s:if>
	</table>
  </s:form>
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
  	try{
  	document.CoreCustomer.cimsCode.value=code;
  	if(window.opener.document.BrokerInfoEdit){
	  	window.opener.document.BrokerInfoEdit.CIMSNO.value =code;
	  	window.opener.document.BrokerInfoEdit.ARACC.value =arNo;
	  	window.opener.document.BrokerInfoEdit.customerName.value =name;
	  	window.opener.document.BrokerInfoEdit.nameAndCode.value =name+' ('+code+')';
  	}
  	}catch(e){alert(e);}
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
