<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="header.jsp" %>
<%@ taglib uri="../WEB-INF/displaytag.tld" prefix="display"%>
<%@ taglib uri="../WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="../WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page isELIgnored ="false" %>
<html>
<head>
<title>Marine - Insurance </title>
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

<form name="CommodityRate" method="post" action="/CommodityCountryRate.do">
<% boolean editFlag=false;%>
<table width="900" border="0" cellspacing="0" cellpadding="0">
<tr>
	<td width="20%">
		<%--@ include file="left.jsp" --%></table>
	</td>
	<td valign="top" align="center" width="80%">
		<table  border="0" style="margin-top: 20px" width="50%">
			<tr>
				<td colspan=2 align="center">
					<span class="heading"><B>Commodity Country Rate</B></span>
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
			<logic:empty name="display" scope="request" >
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
						Broker
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
				<tr class="formtxtf">
					<td align="left">
						Product
					</td>
					<td align="left">&nbsp;
						<select name="productId" style="width:200px" onchange="getOpenCoverList('openCoverList')">
							<option value="">--select--</option>
							<option value="3" <%=productId.equalsIgnoreCase("3")?"selected":""%>>ONE OFF POLICY</option>
							<option value="11" <%=productId.equalsIgnoreCase("11")?"selected":""%>>OPEN COVER</option>
						</select>
					</td>
				</tr>
				<tr class="formtxtf" id="openCoverBlock" style="display:<%="".equals(openCoverNo)?"none":"block"%>">
					<td align="left">
						Open Cover
					</td>
					<td align="left">
						<div id="openCoverList">&nbsp;
							<select name="openCoverNo" style="width:200px" >
								<option value="">--select--</option>
								<% 
								if(openCoverList!=null && openCoverList.length>0){
								for(int i=0; i<openCoverList.length; i++) {%>
									<option value="<%=openCoverList[i][0]%>" <%=openCoverNo.equalsIgnoreCase(openCoverList[i][0])?"selected":""%>><%=openCoverList[i][0]%></option>
								<%}} %>
							</select>
						</div>
					</td>
				</tr>
				<tr class="text" align="left">
					<td colspan="2" align="center">
						<a href="#" onClick="fnSubmit('rateList')"><img src='<%= request.getContextPath()%>/images/Proceed.jpg' /></a>
					</td>
				</tr>
			</logic:empty>
			<%
				if("addRate".equals(request.getAttribute("display")) || "editRate".equals(request.getAttribute("display")))
				{
					String[][] rateInfo=request.getAttribute("rateInfo")==null?new String[0][0]:(String[][])request.getAttribute("rateInfo");
					String[][] modeList=request.getAttribute("modeList")==null?new String[0][0]:(String[][])request.getAttribute("modeList");
					String[][] coverList=request.getAttribute("coverList")==null?new String[0][0]:(String[][])request.getAttribute("coverList");
					String[][] commodityList=request.getAttribute("commodityList")==null?new String[0][0]:(String[][])request.getAttribute("commodityList");
					String[][] countryList=request.getAttribute("countryList")==null?new String[0][0]:(String[][])request.getAttribute("countryList");
					
					String modeOfTransport="";
					String coverId="";
					String commodityId="";
					String originCountry="";
					String destCountry="";
					String originCountryId="";
					String destCountryId="";
					String rate="";
					String clauses="";
					String warranty="";
					String status="";
					String effectiveDate="";
					String curDate;
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
					effectiveDate = sdf.format(new Date());
					System.out.println("effectiveDate " + effectiveDate);
					
					System.out.println("effectiveDate " + effectiveDate);
					if(request.getAttribute("error")!=null)
					{
						modeOfTransport=request.getParameter("modeOfTransport")==null?"":request.getParameter("modeOfTransport");
						coverId=request.getParameter("coverId")==null?"":request.getParameter("coverId");
						commodityId=request.getParameter("commodityId")==null?"":request.getParameter("commodityId");
						originCountryId=request.getParameter("originCountryId")==null?"":request.getParameter("originCountryId");
						destCountryId=request.getParameter("destCountryId")==null?"":request.getParameter("destCountryId");
						rate=request.getParameter("rate")==null?"":request.getParameter("rate");
						clauses=request.getParameter("clauses")==null?"":request.getParameter("clauses");
						warranty=request.getParameter("warranty")==null?"":request.getParameter("warranty");
						status=request.getParameter("status")==null?"":request.getParameter("status");
						effectiveDate=request.getParameter("effectiveDate")==null?"":request.getParameter("effectiveDate");
					}else if(rateInfo.length>0)
					{
						modeOfTransport=rateInfo[0][0];
						coverId=rateInfo[0][1];
						commodityId=rateInfo[0][2];
						originCountryId=rateInfo[0][3];
						destCountryId=rateInfo[0][4];
						rate=rateInfo[0][5];
						clauses=rateInfo[0][6];
						warranty=rateInfo[0][7];
						status=rateInfo[0][8];
						effectiveDate=rateInfo[0][9];
						editFlag=true;
					}
			%>
				<tr>
					<td colspan=2>
						&nbsp;
					</td>
				</tr>
				<tr class='formtxtf'>
					<td align="left">&nbsp;
						Mode of Transport&nbsp;<font color="red">*</font>
					</td>
					<td align="left">&nbsp;
						<select name="modeOfTransport" onchange="getCoverList(this.value,'coverList')" style="width:200px" <%=editFlag==true?"disabled":""%>>
							<option value="">--select--</option>
							<% 
							for(int i=0; i<modeList.length; i++) {%>
								<option value="<%=modeList[i][0]%>" <%=modeOfTransport.equalsIgnoreCase(modeList[i][0])?"selected":""%>><%=modeList[i][1]%></option>
							<%}%>
						</select>
					</td>
				</tr>
				<tr class='formtxtf'>
					<td align="left">&nbsp;
						Coverage&nbsp;<font color="red">*</font>
					</td>
					<td align="left">
						<div id="coverList">&nbsp;
							<select name="coverId" style="width:200px" <%=editFlag==true?"disabled":""%>>
								<option value="">--select--</option>
								<% 
								for(int i=0; i<coverList.length; i++) {%>
									<option value="<%=coverList[i][0]%>" <%=coverId.equalsIgnoreCase(coverList[i][0])?"selected":""%>><%=coverList[i][1]%></option>
								<%}%>
							</select>
						</div>
					</td>
				</tr>
				<tr class='formtxtf'>
					<td align="left">&nbsp;
						Commodity&nbsp;<font color="red">*</font>
					</td>
					<td align="left">&nbsp;
						<select name="commodityId" style="width:200px" <%=editFlag==true?"disabled":""%>>
							<option value="">--select--</option>
							<% 
							for(int i=0; i<commodityList.length; i++) {%>
								<option value="<%=commodityList[i][0]%>" <%=commodityId.equalsIgnoreCase(commodityList[i][0])?"selected":""%>><%=commodityList[i][1]%></option>
							<%}%>
						</select>
					</td>
				</tr>
				<tr class='formtxtf' >
					<td width="20%" class="formtxtf" align="left">Country Of Origin&nbsp;<font color="red">*</font></sup></td>
					<td align="left">&nbsp;
						<select name="originCountryId" style="width:200px" <%=editFlag==true?"disabled":""%>>
							<option value="">--select--</option>
							<% 
							for(int i=0; i<countryList.length; i++) {%>
								<option value="<%=countryList[i][0]%>" <%=originCountryId.equalsIgnoreCase(countryList[i][0])?"selected":""%>><%=countryList[i][1]%></option>
							<%}%>
						</select>
					</td>
				</tr>
				<tr>
					<td  width="20%" class="formtxtf" align="left">Country of Destination&nbsp;<font color="red">*</font></td>
					<td align="left">&nbsp;
						<select name="destCountryId" style="width:200px" <%=editFlag==true?"disabled":""%>>
							<option value="">--select--</option>
							<% 
							for(int i=0; i<countryList.length; i++) {%>
								<option value="<%=countryList[i][0]%>" <%=destCountryId.equalsIgnoreCase(countryList[i][0])?"selected":""%>><%=countryList[i][1]%></option>
							<%}%>
						</select>
					</td>
				</tr>
				<tr class='formtxtf'>
					<td align="left">&nbsp;
						Rate&nbsp;<font color="red">*</font>
					</td>
					<td align="left">&nbsp;
						<input type="text" name="rate" style="width:150px" value="<%=rate %>" onkeyup="checkNumbers(this)"/>
					</td>
				</tr>
				<tr>
					<td  width="20%" class="formtxtf" align="left">Clauses&nbsp;<font color="red">*</font></td>
					<td  width="30%" class='formtxtf' align="left">
						<table width="100%" cellspacing="0" cellpadding="0">
							<tr>
								<td>
									<textarea name="clauses" readOnly class="fdel" cols="15" rows='2'><%=clauses %></textarea>
				                </td>
				                <td>
					                <input name="chooseCommodityC2" value="....."  class="fde1" onClick="showCommodities('clauses')" type="button">
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td  width="20%" class="formtxtf" align="left">Warranties&nbsp;<font color="red">*</font></td>
					<td  width="30%" class='formtxtf' align="left">
						<table width="100%" cellspacing="0" cellpadding="0">
							<tr>
								<td>
									<textarea name="warranty" readOnly class="fdel" cols="15" rows='2'><%=warranty %></textarea>
				                </td>
				                <td>
					                <input name="chooseCommodityC2" value="....."  class="fde1" onClick="showCommodities('warranty')" type="button">
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr class='formtxtf'>
					<td align="left">&nbsp;
						Status&nbsp;<font color="red">*</font>
					</td>
					<td align="left">&nbsp;
						 <input type ='radio' name='status' value='Y'   <%= "Y".equalsIgnoreCase(status)?"checked=\"checked\"":""%>>&nbsp;Yes   <input type ='radio' name='status' value='N' <%= "N".equalsIgnoreCase(status)?"checked=\"checked\"":""%> >&nbsp;No
					</td>
				</tr>
				<tr class='formtxtf'>
					<td align="left">&nbsp;
						Effective Date&nbsp;<font color="red">*</font>
					</td>
					<td align="left">&nbsp;
						<input type="text" id="effectiveDate" name="effectiveDate" value="<%=effectiveDate%>" Readonly size="25" />
		<a href="javascript:cal6.popup();"><img src="<%=request.getContextPath() %>/images/cal.gif" width="16" height="16" border="0" alt="Click Here Pick up the date"></a><br>
					</td>
				</tr>
				<tr align="left">
					<td align="center" colspan="2">
						<a > <img src="<%=request.getContextPath() %>/images/Back.jpg" onclick="fnSubmit('rateList')" alt="Back" style="cursor: pointer;" /> </a>
						<a href="#" onClick="fnSubmit('<%=("addRate".equals(request.getAttribute("display"))?"addRateInfo":"editRateInfo")%>')"><img src='<%=request.getContextPath()%>/images/Proceed.jpg' /></a>
						<input type="hidden" name="productId" value="<%=productId %>"/>
						<input type="hidden" name="brokerId" value="<%=brokerId %>"/>
						<input type="hidden" name="openCoverNo" value="<%=openCoverNo%>"/>
						<%if(editFlag){%>
							<input type="hidden" name="modeOfTransport" value="<%=modeOfTransport%>"/>
							<input type="hidden" name="coverId" value="<%=coverId%>"/>
							<input type="hidden" name="coverId1" value="<%=coverId%>"/>
							<input type="hidden" name="commodityId" value="<%=commodityId%>"/>
							<input type="hidden" name="originCountryId" value="<%=originCountryId%>"/>
							<input type="hidden" name="destCountryId" value="<%=destCountryId%>"/>
						<%} %>
					</td>
				</tr>
			<%}%>
		</table>
		<input type="hidden" name="display" />
	</td>
</tr>
</table>
</form>
<script language="JavaScript">
<%if("addRate".equals(request.getAttribute("display")) || "editRate".equals(request.getAttribute("display"))){%>
	var cal6 = new calendar1(document.getElementById('effectiveDate'));
	cal6.year_scroll = true;
	cal6.time_comp = false;
<%}%>
function fnSubmit(display)
{
	document.CommodityRate.display.value=display
	document.CommodityRate.action="CommodityCountryRate.do";
	document.CommodityRate.submit();
}	
function getCoverList(mode, id)
{
	var url='<%=request.getContextPath()%>/CommodityCountryRate.do?display=coverList&modeOfTransport='+mode;
	postRequest(url, id);
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
function showCommodities(values)
{
 	var URL = '';
  	var windowName = "";
  
	if(values == 'warranty')
	{
		URL='CommodityCountryRate.do?display='+values;
	}else if(values == 'clauses')
	{
		var coverId='';
		if(<%=editFlag%>)
			coverId=document.CommodityRate.coverId1.value;
		else
			coverId=document.CommodityRate.coverId.value;
		if(coverId!='')
			URL='CommodityCountryRate.do?display='+values+'&coverId='+coverId;
		else
		{
			alert('Please Select Cover');
			return;
		}
	}
		var width  = screen.availWidth;
		var height = screen.availHeight;
		var h=450;
		var w=750;
		var features =
			'width='		 + w +
			',height='		 + h +
			',left='		 + ((width - w - 0) * .4)  +
			',top='			 + ((height - h - 0) * .4) +
			',directories=no'+
			',location=no'	 +
			',menubar=no'	 +
			',scrollbars=yes'+
			',status=yes'	 +
			',toolbar=no'	 +
			',resizable=false';
		var strOpen = window.open (URL, windowName, features);
		strOpen.focus();
}

function postRequest(strURL, id) 
{
	var xmlHttp;
    if (window.XMLHttpRequest) { // Mozilla, Safari, ...
		var xmlHttp = new XMLHttpRequest();
    }else if (window.ActiveXObject) { // IE
		var xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
   	}
    xmlHttp.open('POST', strURL, true);
    xmlHttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	xmlHttp.onreadystatechange = function() 
	{
		if (xmlHttp.readyState == 4) 
		{
			document.getElementById(id).innerHTML=xmlHttp.responseText;
		}
   }
	xmlHttp.send(null);
}
function editRate(display, modeOfTransport, coverId, commodityId, originCountryId, destCountryId)
{
	document.CommodityRate.display.value=display
	document.CommodityRate.coverId.value=coverId
	document.CommodityRate.commodityId.value=commodityId
	document.CommodityRate.originCountryId.value=originCountryId
	document.CommodityRate.destCountryId.value=destCountryId
	document.CommodityRate.modeOfTransport.value=modeOfTransport
	document.CommodityRate.action="CommodityCountryRate.do";
	document.CommodityRate.submit();
}
function checkNumbers(val){
	var value=val.value;
	var temp="";
	if(value!=null){
		temp=value.charAt(value.length-1);
		if((isNaN(temp) || temp==" ")&& temp!='.'){
			val.value=value.substring(0,value.length-1);
		}else if(value.indexOf('.')!=-1){
			val.value = value.substring(0,value.indexOf('.')) + value.substring(value.indexOf('.'),(value.indexOf('.')+5));
		}
	}	
}
</script>
</body>
</html>
