<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.maan.premium.DAO.PremiumInputsBean" isELIgnored ="false" buffer = "500kb" %>
<%@ include file="header.jsp" %>
<%@ taglib uri="../WEB-INF/displaytag.tld" prefix="display"%>
<%@ taglib uri="../WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="../WEB-INF/struts-logic.tld" prefix="logic"%>
<html>
<head>
<title>Marine - Insurance </title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/skin1.css"/>
<link href="<%=request.getContextPath()%>/css/displaytag.css" rel="stylesheet" type="text/css">
<script src="<%=request.getContextPath()%>/css/jquery.tools.min.js"></script>
<style type="text/css">
<!--
.headerClass {color: #efefef;background-color: #666666;}
-->
</style>
</head>
<body >


<table width="900" border="0" cellspacing="0" cellpadding="0">
<tr>
	<td width="20%">
		<%--@ include file="left.jsp" --%></table>
	</td>
	<td valign="top" align="center" width="80%">
	<form name="OpencoverList" method="post">
		<table  border="0" style="margin-top: 20px" width="50%">
			<tr>
				<td colspan="4" align="center">
					<span class="heading"><B>Deposit Opencover List</B></span>
				</td>
			</tr>
			<% if (("depositList").equals(request.getAttribute("display"))){ %>
			<tr >
				<td colspan="4">
					<table cellspacing="2" cellpadding="2" border=1>
						<tr>
							<td>
								<display:table name="requestScope.depositList" pagesize="10" uid="row" id="record" style="width:500px;" requestURI="/DepositOpencoverList.do" export="false" >
								<display:setProperty name="paging.banner.one_item_found" value="" />
								<display:setProperty name="paging.banner.one_items_found" value="" />
								<display:setProperty name="paging.banner.all_items_found" value="" />
								<display:setProperty name="paging.banner.some_items_found" value="" />
								<display:setProperty name="paging.banner.placement" value="bottom" />
								<display:setProperty name="paging.banner.onepage" value="" />
								<display:setProperty name="basic.empty.showtable" value="true" />
								<display:setProperty name="paging.banner.no_items_found" value="" />
								<display:setProperty name="export.csv" value="false" />
								<display:setProperty name="export.xml" value="false" />
								<display:setProperty name="export.pdf" value="false" />
				                <display:setProperty name="export.excel.filename" value="DepositOpencoverList.xls" />
				                <display:setProperty name="export.pdf.filename" value="DepositOpencoverList.pdf" />
				                
				                <display:column style="text-align:center;" sortable="false" title="select" class="formtxtc" headerClass="headerClass">
									<input type="radio" name="selects" id="select" onclick="assignValue('${record.opencoverNo}')" value="0" />
								</display:column>
				                
								<display:column style="text-align:center;" sortable="false" title="S.No" value="${record_rowNum}" class="formtxtc" headerClass="headerClass"/>
								<display:column style="text-align:center;" sortable="false" title="opencover No" property="opencoverNo" class="formtxtc" headerClass="headerClass"/>
								<display:column style="text-align:center;" sortable="false" title="Customer Name" property="CustomerName" class="formtxtc" headerClass="headerClass"/>
								<display:column sortable="false" style="text-align:left;" title="start Date" property="startDate" class="formtxtc" headerClass="headerClass"/>
								</display:table>
								
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
			<td colspan="2" align="center">
			    <input type="hidden" name="opencoverNo" id="opencoverNo"/>
				<input type="button" onclick="ViewList()" value="Submit"/>
		    </td>
		    </tr>
			<%} if (("getOpencover").equals(request.getAttribute("display"))){ %>
			<tr>
			<td colspan="4">
					<table cellspacing="2" cellpadding="2" border=1>
						<tr>
							<td>
								<display:table name="requestScope.getOpencover" pagesize="10" uid="row" id="record" style="width:500px;" requestURI="/getOpencover.do" export="false" >
								<display:setProperty name="paging.banner.one_item_found" value="" />
								<display:setProperty name="paging.banner.one_items_found" value="" />
								<display:setProperty name="paging.banner.all_items_found" value="" />
								<display:setProperty name="paging.banner.some_items_found" value="" />
								<display:setProperty name="paging.banner.placement" value="bottom" />
								<display:setProperty name="paging.banner.onepage" value="" />
								<display:setProperty name="basic.empty.showtable" value="true" />
								<display:setProperty name="paging.banner.no_items_found" value="" />
								<display:setProperty name="export.csv" value="false" />
								<display:setProperty name="export.xml" value="false" />
								<display:setProperty name="export.pdf" value="false" />
				                <display:setProperty name="export.excel.filename" value="DepositOpencoverList.xls" />
				                <display:setProperty name="export.pdf.filename" value="DepositOpencoverList.pdf" />
				                
				                <display:column style="text-align:center;" sortable="false" title="select" class="formtxtc" headerClass="headerClass">
									<input type="radio" name="selects" id="select" onclick="assignValue2('${record.startDate}','${record.endDate}')" value="0" />
								</display:column>
				                
								<display:column style="text-align:center;" sortable="false" title="S.No" value="${record_rowNum}" class="formtxtc" headerClass="headerClass"/>
								<display:column style="text-align:center;" sortable="false" title="Start Date" property="startDate" class="formtxtc" headerClass="headerClass"/>
								<display:column style="text-align:center;" sortable="false" title="End Date" property="endDate" class="formtxtc" headerClass="headerClass"/>
								</display:table>
								<input type="hidden" name="opencoverNo" id="opencoverNo"/>
								<input type="hidden" name="startDate" id="startDate"/>
								<input type="hidden" name="endDate" id="endDate"/>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
			<td colspan="2" align="center">
				<INPUT type=button value=" Back " onClick="parent.history.back(); return false;">
				<input type="button" onclick="ViewList2()" value="Submit"/>
		    </td>
		    </tr>
			<% } if (("getDepositOpencover").equals(request.getAttribute("display"))){ %>
			<tr>
			<td colspan="4">
					<table cellspacing="2" cellpadding="2" border=1>
						<tr>
							<td>
								<display:table name="requestScope.getDepositOpencover" pagesize="10" uid="row" id="record" style="width:500px;" requestURI="/getDepositOpencover.do" export="true" >
								<display:setProperty name="paging.banner.one_item_found" value="" />
								<display:setProperty name="paging.banner.one_items_found" value="" />
								<display:setProperty name="paging.banner.all_items_found" value="" />
								<display:setProperty name="paging.banner.some_items_found" value="" />
								<display:setProperty name="paging.banner.placement" value="bottom" />
								<display:setProperty name="paging.banner.onepage" value="" />
								<display:setProperty name="basic.empty.showtable" value="true" />
								<display:setProperty name="paging.banner.no_items_found" value="" />
								<display:setProperty name="export.csv" value="false" />
								<display:setProperty name="export.xml" value="false" />
								<display:setProperty name="export.pdf" value="false" />
				                <display:setProperty name="export.excel.filename" value="DepositOpencoverList.xls" />
				                <display:setProperty name="export.pdf.filename" value="DepositOpencoverList.pdf" />
								<display:column style="text-align:center;" sortable="false" title="S.No" value="${record_rowNum}" class="formtxtc" headerClass="headerClass"/>
								<display:column sortable="false" style="text-align:center;"  title="Policy No" property="policyNo" class="formtxtc" headerClass="headerClass"/>
								<display:column sortable="false" style="text-align:center;"  title="Inception Date" property="inceptionDate" class="formtxtc" headerClass="headerClass"/>
								<display:column sortable="false" style="text-align:right;" title="Premium" property="premium" class="formtxtc" headerClass="headerClass"/>
								<display:column sortable="false" style="text-align:right;" title="Issuance Fee" property="issuanceFee" class="formtxtc" headerClass="headerClass"/>
								<display:column sortable="false" style="text-align:right;" title="Total Premium" property="totalPremium" class="formtxtc" headerClass="headerClass"/>
								</display:table>
						   </td>
						</tr>
					</table>
				</td>
				</tr>
				<tr>
				<td colspan="2" align="center">
					<INPUT type=button value=" Back " onClick="parent.history.back(); return false;">
				</td>
				</tr>
				<% } %>
		</table>
		</form>
	</td>
</tr>
</table>
<script type="text/javascript">
var opcoverNo;
var startDate;
var endDate;
function assignValue(val)
{
	opcoverNo=val;
}
function assignValue2(val1,val2)
{
	startDate=val1;
	endDate=val2;
} 
function ViewList()
{
document.forms[0].action="<%=request.getContextPath()%>/admin/getOpencover.do?opencoverNo="+opcoverNo;
document.forms[0].submit();
} 
function ViewList2()
{
document.forms[0].action="<%=request.getContextPath()%>/admin/getDepositOpencover.do?opencoverNo=<%=request.getAttribute("opencoverNo")%>&startDate="+startDate+"&endDate="+endDate;
document.forms[0].submit();
} 
</script>
</body>
</html>