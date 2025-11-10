<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="display" uri="/WEB-INF/displaytag.tld" %>
<!DOCTYPE html>
<html>
    <head>
    	<TITLE><s:text name="company.name" /> - <s:text name="application.name" /></TITLE>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="Raja.K">
        <link href="${pageContext.request.contextPath}/css/menu/ddlevelsmenu-base.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/css/menu/ddlevelsmenu-topbar.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/css/displaytag.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/css/tcal.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/tcal.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/common.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/menu/ddlevelsmenu.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/menu/ddtabmenu.js"></script>
	</head>
	<body>
		<s:form name="appSelection" theme="simple">
			<table width="55%" border="0" cellspacing="0" cellpadding="2" align="center" class="data-table-border" style="table-layout: fixed">
				<tr>
					<td class="formtxtc" style="text-align: center;">
						<display:table name="list" pagesize="" class="table" uid="row" id="record">
							<display:setProperty name="paging.banner.one_item_found" value="" />
							<display:setProperty name="paging.banner.one_items_found" value="" />
							<display:setProperty name="paging.banner.all_items_found" value="" />
							<display:setProperty name="paging.banner.some_items_found" value="" />
							<display:setProperty name="paging.banner.placement" value="bottom" />
							<display:setProperty name="paging.banner.onepage" value="" />
		
							<display:column headerClass="sortable" sortable="false" style="text-align:center;" title=""> 
								<input type="radio" name="appId" onclick="appSelect('${record.app_id}')"/>
							</display:column>
							<display:column sortable="true" style="text-align:left;" title="Applications" property="app_name" />
						</display:table>
					</td>
					<s:hidden name="appIds"/>
				</tr>
				<tr align="center">
					<td class="text">
						<input type="button" onClick="fnsubmit();" class="btn" value="Submit" style="cursor: hand"/>
						&nbsp;
						<input type="button" class="btn" onClick="javascript: window.close();" 
							value="Close" style="cursor: hand">
					</td>
				</tr>
			</table>
		</s:form>
	</body>
	<script type="text/javascript">
		function appSelect(appId){
			document.appSelection.appIds.value =appId;
		}
		
		function fnsubmit() {
			var value=document.appSelection.appIds.value==null?"":document.appSelection.appIds.value;
			if (value!=""){
				window.opener.document.appList.appIds.value =value;
				window.close();
			}
			else
  				alert('Please Select Application');
			return false;
		}
		
	</script>
</html>