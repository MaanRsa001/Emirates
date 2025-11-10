<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="header.jsp" %>
<%@ taglib uri="../WEB-INF/displaytag.tld" prefix="display"%>
<%@ taglib uri="../WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="../WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page isELIgnored ="false" %>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/css/displaytag.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="<%=request.getContextPath() %>/css/calendar1.js"></script>
<style type="text/css">
<!--
.headerClass {color: #efefef;background-color: #666666;}
-->
</style>
</head>
<body >
<br/><br/>
<form name="CommodityRate" method="post" action="/CommodityCountryRate.do">
<% boolean editFlag=false;%>
<table width="900" border="0" cellspacing="0" cellpadding="0">
<tr>
	<td valign="top" align="center" width="80%">
		<table  border="0" style="margin-top: 20px" width="50%">
			<tr>
				<td colspan=2 align="center">
					<span class="heading"><B>Opencover Reports</B></span>
				</td>
			</tr>
			<%
				String productId=request.getParameter("productId")==null?"":request.getParameter("productId");
				String brokerId=request.getParameter("brokerId")==null?"":request.getParameter("brokerId");
				String openCoverNo=request.getParameter("openCoverNo")==null?"":request.getParameter("openCoverNo");
			%>
			<logic:notEmpty name="error" scope="request">
				<tr>
					<td colspan=2 style="color: red;">
						<%=request.getAttribute("error")%>
					</td>
				</tr>
			</logic:notEmpty>
			<logic:equal name="display" scope="request" value="rateList">
			
			<tr>
				<td colspan="2">
					<table cellspacing="2" cellpadding="2" border=1>
						<tr>
							<td>
								<display:table name="requestScope.rateList" pagesize="10" uid="row" id="record" style="width:600px;">
								<display:setProperty name="paging.banner.one_item_found" value="" />
								<display:setProperty name="paging.banner.one_items_found" value="" />
								<display:setProperty name="paging.banner.all_items_found" value="" />
								<display:setProperty name="paging.banner.some_items_found" value="" />
								<display:setProperty name="paging.banner.placement" value="bottom" />
								<display:setProperty name="paging.banner.onepage" value="" />
								<display:setProperty name="basic.empty.showtable" value="true" />
								<display:setProperty name="paging.banner.no_items_found" value="" />
								<display:column style="text-align:center;" sortable="false" title="S.No" property="sno" class="formtxtc" headerClass="headerClass"/>
								<display:column style="text-align:left;" sortable="false" title="Mode of Transport" property="modeOfTransportDesc" class="formtxtc" headerClass="headerClass"/>
								<display:column sortable="false" style="text-align:left;" title="Coverage" property="coverageName" class="formtxtc" headerClass="headerClass"/>
								<display:column sortable="false" style="text-align:left;" title="Commodity" property="commodityName" class="formtxtc" headerClass="headerClass"/>
								<display:column sortable="false" style="text-align:left;" title="Origin Country" property="originCountryName" class="formtxtc" headerClass="headerClass"/>
								<display:column sortable="false" style="text-align:left;" title="Dest. Country" property="destCountryName" class="formtxtc" headerClass="headerClass"/>
								<display:column sortable="false" style="text-align:left;" title="Rate" property="rate" class="formtxtc" headerClass="headerClass"/>
								<display:column sortable="false" style="text-align:center;" title="Edit" class="formtxtc" headerClass="headerClass">
									<input type="radio" name="select" onclick="editRate('editRate','${record.modeOfTransport}','${record.coverageId}','${record.commodityId}','${record.originCountryId}','${record.destCountryId}')"/>
								</display:column>
								</display:table>
							</td>
						</tr>
					</table>
					<input type="hidden" name="productId" value="<%=productId %>"/>
					<input type="hidden" name="brokerId" value="<%=brokerId %>"/>
					<input type="hidden" name="modeOfTransport" />
					<input type="hidden" name="coverId" />
					<input type="hidden" name="commodityId" />
					<input type="hidden" name="originCountryId" />
					<input type="hidden" name="destCountryId" />
					<input type="hidden" name="openCoverNo" value="<%=openCoverNo%>"/>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" name="back" value="Back" onclick="fnSubmit('')">
					<input type="submit" name="addRate" value="Add New" onclick="fnSubmit('addRate')">
				</td>
			</tr>
			</logic:equal>
			<logic:empty name="list" scope="request" >
			<%
				String[][] brokerList=request.getAttribute("brokerList")==null?new String[0][0]:(String[][])request.getAttribute("brokerList");
				String[][] openCoverList=request.getAttribute("openCoverList")==null?new String[0][0]:(String[][])request.getAttribute("openCoverList");
			%>
				<tr>
					<td colspan=2>
						&nbsp;
					</td>
				</tr>
				<tr class="formtxtf">
					<td align="left">
						Broker List
					</td>
					<td>&nbsp;
						<select name="brokerId" id="brokerId" style="width:200px" onchange="getOpenCoverList('openCoverList')">
							<option value="">--select--</option>
							<% 
							if(brokerList!=null && brokerList.length>0){
							for(int i=0; i<brokerList.length; i++) {%>
								<option value="<%=brokerList[i][0]%>" <%=brokerId.equalsIgnoreCase(brokerList[i][0])?"selected":""%>><%=brokerList[i][1]%></option>
							<%}} %>
						</select>
					</td>
				</tr>
				
				<tr class="text" align="left">
					<td colspan="2" align="center">
						<a href="#" onClick="fnSubmit('BrokerList')"><img src='<%= request.getContextPath()%>/images/Proceed.jpg' /></a>
					</td>
				</tr>
			</logic:empty>
		</table>
	</td>
</form>
<script language="JavaScript">
function fnSubmit(display)
{
	document.CommodityRate.display.value=display
	document.CommodityRate.action="CommodityCountryRate.do";
	document.CommodityRate.submit();
}	

function getOpenCoverList(id)
{
	var index=document.CommodityRate.brokerId.selectedIndex;
	var brokerId=document.CommodityRate.brokerId.options[index].value;
	index=document.CommodityRate.productId.selectedIndex;
	var productId=document.CommodityRate.productId.options[index].value;
	if(brokerId=='')
		alert('Please select broker');
	else if(productId=='3')
	{
		document.getElementById('openCoverBlock').style.display='none';
		document.getElementById('openCoverNo').vlaue='';
	}
	else if(productId=='11')
	{
		document.getElementById('openCoverBlock').style.display='block';
		var url='<%=request.getContextPath()%>/CommodityCountryRate.do?display=openCoverList&brokerId='+brokerId;
		postRequest(url, id);
	}
}

</script>
</body>
</html>
