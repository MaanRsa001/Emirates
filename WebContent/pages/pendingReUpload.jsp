<%@ page isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<html> 
	<head>
		<script type="text/javascript" src="js/common.js"></script>
		<link href="${pageContext.request.contextPath}/css/displaytag.css" rel="stylesheet" type="text/css">
	</head>
	<body>
		<table width="900px" border="0" align="center" cellpadding="0" cellspacing="0"> 
			<tr><td>
				<s:form name="uploadForm" id="uploadForm" action="redirectOpenUpload" method="post" theme="simple">
					<table width="100%" border="0" cellspacing="2" cellpadding="2" align="center" >
						<tr align="left">
							<td height="20" colspan="2" style="color:red">
								<s:actionerror/><s:actionmessage/>
							</td>
						</tr>
					</table>
					<table width="100%" border="0" cellspacing="0" cellpadding="2" align="center">
				    	<tr class="head">
		    				<td align="center" colspan="2" class="heading">
		    					<b><s:text name="Pending Reupload"/></b>
							</td>
						</tr>
						<tr class="runtext">
							<td height="14" align="right" colspan="2"></td>
						</tr>
						<tr class="runtext">
							<td align="left" colspan="2">
								<table width="100%">
									<tr>
										<td width="30%"><s:text name="Customer Name"/> : <b><s:property value="customerName"/></b><s:hidden name="customerName"/></td>
										<td width="30%"><s:text name="Open Cover No"/> : <b><s:property value="openCoverNo"/></b><s:hidden name="openCoverNo" id="openCoverNo"/></td>
										<td width="30%"><s:text name="Transaction Id"/> : <b><s:property value="tranId"/></b><s:hidden name="tranId" id="tranId"/></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr class="runtext">
							<td height="14" align="right" colspan="2"></td>
						</tr>
						<tr class="runtext">
							<td height="14" align="right" colspan="2"></td>
						</tr>
						<tr>
							<td colspan="2">
								<div id="pendingReupload" style="overflow-x: scroll; width:1320px;"> 
								 	<table width="100%"  align="center" cellpadding="0" cellspacing="1" bgcolor="#E5E5E5">
								 		<tr class="heading">
								 			<td style="width:154px;" align="center"><s:text name="INTEREST"/></td>
								 			<td style="width:154px;" align="center"><s:text name="CONVEYANCE"/></td>
								 			<td style="width:154px;" align="center"><s:text name="COVER"/></td>
								 			<td style="width:154px;" align="center"><s:text name="VOYAGE FROM"/></td>
								 			<td style="width:154px;" align="center"><s:text name="VOYAGE TO"/></td>
								 			<td style="width:154px;" align="center"><s:text name="CURRENCY"/></td>
								 			<td style="width:70px;" align="center"><s:text name="INCOTERMS"/></td>
								 			<td style="width:140px;" colspan="2" align="center"><s:text name="BASIS OF VALUATION"/></td>
								 			<td style="width:154px;" align="center"><s:text name="PACKAGE DESCRIPTION"/></td>
								 			<td style="width:190px;" align="center" colspan="2"><s:text name="COMMODITY DESCRIPTION"/></td>
								 			<td style="width:80px;" align="center"><s:text name="WAR COVER"/></td>
								 			<td style="width:154px;" align="center"><s:text name="OTHERS"/></td>
								 		</tr>
								 		<tr class="runtext">
											<td height="14" align="right" colspan="2"></td>
										</tr>
										<s:iterator value="reuploadList" var="reup" status ="item">
											<s:if test='%{!"".equals(errorValid)}'>
												<tr class="runtext">
													<td style="color:red;" colspan="9">${errorValid}</td>
												</tr>
											</s:if>
										</s:iterator>
									 	<s:iterator value="reuploadList" var="reup" status ="item">
									 		<tr>
									 			<td style="width:154px; color=blue;">${interest}</td>
									 			<td style="width:154px; color=blue;">${conveyance}</td>
									 			<td style="width:154px; color=blue;">${cover}</td>
									 			<td style="width:154px; color=blue;">${voyageFrom}</td>
									 			<td style="width:154px; color=blue;">${voyageTo}</td>
									 			<td style="width:154px; color=blue;">${currency}</td>
									 			<td style="width:70px; color=blue;">${incoTerms}</td>
									 			<td style="width:140px; color=blue;" colspan="2">${basisValue}</td>
									 			<td style="width:154px;">${pkgDesc}</td>
									 			<td style="width:190px; color=blue;" colspan="2">${commodityDesc}</td>
									 			<td style="width:80px;" align="center">${warYN}</td>
								 				<td style="width:154px;" align="center"></td>
									 		</tr>
									 		<tr>
									 			<s:hidden name="usno[%{#item.count}]" value="%{sno}"/>
									 			<td style="width:154px;"><s:textfield name="uinterest[%{#item.count}]" cssStyle="width:154px;"/></td>
									 			<td style="width:154px;"><s:select name="uconveyance[%{#item.count}]" id="conveyance" list="modeList" headerKey="" headerValue="-Select-" listKey="CODE" listValue="CODEDESC" cssClass="input" disabled="#disable"  onchange="getList('?conveyance='+this.value+'&rowNum=%{#item.count}','coverList[%{#item.count}]','coverList');getList('?conveyance='+this.value+'&rowNum=%{#item.count}','packageList[%{#item.count}]','packageList');" cssStyle="width:154px;"/></td>
									 			<td style="width:154px;"><div id="coverList[<s:property value="%{#item.count}"/>]"><s:select name="ucover[%{#item.count}]" list="coverList" headerKey="" headerValue="-Select-" listKey="CODE" listValue="CODEDESC" cssClass="input" disabled="#disable" cssStyle="width:154px;"/></div></td>
									 			<td style="width:154px;"><s:select name="uvoyageFrom[%{#item.count}]" id="voyageFrom" list="originList" headerKey="" headerValue="-Select-" listKey="CODEDESC" listValue="CODEDESC" cssClass="input" disabled="#disable" cssStyle="width:154px;"/></td>
									 			<td style="width:154px;"><s:select name="uvoyageTo[%{#item.count}]" id="voyageTo" list="destList" headerKey="" headerValue="-Select-" listKey="CODEDESC" listValue="CODEDESC" cssClass="input" disabled="#disable" cssStyle="width:154px;"/></td>
									 			<td style="width:154px;"><s:select name="ucurrency[%{#item.count}]" id="currency" list="currencyList" headerKey="" headerValue="-Select-" listKey="CODEDESC" listValue="CODEDESC" cssClass="input" disabled="#disable" cssStyle="width:154px;"/></td>
									 			<td style="width:70px;"><s:select name="uincoTerms[%{#item.count}]" list="saleTermList" headerKey="" headerValue="-Select-" listKey="CODE" listValue="CODEDESC" cssClass="input" disabled="#disable" onchange="getList('?incoTerms='+this.value+'&rowNum=%{#item.count}','percentList[%{#item.count}]','percentList');" cssStyle="width:70px;"/></td>
									 			<td style="width:70px;"><div id="percentList[<s:property value="%{#item.count}"/>]"><s:select name="usaleTermPercent[%{#item.count}]" id="percent" list="percentList" headerKey="" headerValue="-Select-" listKey="CODE" listValue="CODEDESC" cssClass="input" disabled="%{saleTerm=='205' || #disable}" onchange="getList('?saleTermPercent='+this.value+'&rowNum=%{#item.count}','toleranceList[%{#item.count}]','toleranceList');" cssStyle="width:70px;"/></div></td>
									 			<td style="width:70px;"><div id="toleranceList[<s:property value="%{#item.count}"/>]"><s:select name="utolerance[%{#item.count}]" list="toleranceList" listKey="CODE" listValue="CODEDESC" cssClass="input" disabled="#disable" cssStyle="width:70px" cssStyle="width:70px;"/></div></td>
									 			<td style="width:154px;"><div id="packageList[<s:property value="%{#item.count}"/>]"><s:select name="upkgDesc[%{#item.count}]" list="packageList" headerKey="" headerValue="-Select-" listKey="CODEDESC" listValue="CODEDESC" cssClass="input" disabled="#disable2" cssStyle="width:154px;"/></div></td>
									 			<td style="width:154px;"><s:textarea name="ucommodityDesc[%{#item.count}]" id="ucommodityDesc%{#item.count}" cssStyle="width:154px;" rows="3"/></td>
									 			<td style="width:40px;"><s:submit type="button" value="..." onclick="return commoditySelection('%{#item.count}')" cssStyle="width:30px;" cssClass="btn"/></td>
									 			<td style="width:80px;">
										 			<s:if test='"Y".equals(warYN)'>
										 				<s:select name="uwar[%{#item.count}]" id="uwar[%{#item.count}]" list="#{'YES':'YES','NO':'NO'}" headerKey="" headerValue="-Select-" cssClass="input" cssStyle="width:80px;"/>
										 			</s:if>
										 			<s:else>
										 				<s:select name="uwar[%{#item.count}]" id="uwar[%{#item.count}]" list="#{'NO':'NO'}" cssClass="input" cssStyle="width:70px;"/>
										 			</s:else>
										 		</td>
									 			<td style="width:154px;" align="center"><input type="button" name="others" value="Others" onclick="return othersInfo('${sno}')" class="btn" style="width:80px;"/></td>
									 		</tr>
									 		<tr class="runtext">
												<td height="14" align="right" colspan="2"></td>
											</tr>
									 	</s:iterator>
									 	<tr class="runtext">
											<td height="14" align="right" colspan="2"></td>
										</tr>
								 	</table>
								</div>
							</td>
						</tr>
						<tr align="center">
							<td class="text" colspan="2">
								<s:submit name="back11" value="Back" cssClass="btn" action="listOpenUpload"/>
								<s:submit name="submit11" value="Submit" action="reuploadSaveOpenUpload" cssClass="btn"/>
							</td>
						</tr>
					</table>
				</s:form>
			</td></tr>
		</table>
 	</body>
 	<script>
 		function getList(val, id, id1){
 			postRequest('${pageContext.request.contextPath}/'+id1+'OpenUpload.action'+val, id);
 		}
 		function commoditySelection(rowNum){
		     var URL ='${pageContext.request.contextPath}/commodityListOpenUpload.action?rowNum='+rowNum+'&openCoverNo='+document.uploadForm.openCoverNo.value;
		     return popUp(URL,'600','500');
		}
		function othersInfo(sno){
		     var URL ='${pageContext.request.contextPath}/otherInfoOpenUpload.action?sno='+sno+'&tranId='+document.uploadForm.tranId.value;
		     return popUp(URL,'600','500');
		}
	</script>
</html>
