<%@ page isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<html> 
	<head>
		<script type="text/javascript" src="js/common.js"></script>
		<link href="${pageContext.request.contextPath}/css/displaytag.css" rel="stylesheet" type="text/css">
	</head>
	<body>
		<table width="500px" border="1" align="center" cellpadding="0" cellspacing="0" bgcolor="#E5E5E5"> 
			<tr><td class="bg">
				<s:form name="uploadForm" id="uploadForm" action="redirectOpenUpload" method="post" theme="simple">
					<table width="100%" border="0" cellspacing="2" cellpadding="2" align="center" >
						<tr align="left">
							<td height="20" colspan="2" style="color:red">
								<s:actionerror/><s:actionmessage/>
							</td>
						</tr>
					</table>
					<table width="100%" border="0" cellspacing="0" cellpadding="2" align="center" bgcolor="#ffffff">
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
										<td width="30%"><s:text name="Customer Name"/></td>
										<td width="3%" align="center">:</td>
										<td width="67%"><b><s:property value="customerName"/></b><s:hidden name="customerName"/></td>
									</tr>
									<tr>
										<td width="30%"><s:text name="Open Cover No"/></td>
										<td width="3%" align="center">:</td>
										<td width="67%"><b><s:property value="openCoverNo"/></b><s:hidden name="openCoverNo" id="openCoverNo"/></td>
									</tr>
									<tr>
										<td colspan="3">
											<table width="100%"><tr>
												<td width="48%"><s:text name="Transaction Id"/> : <b><s:property value="tranId"/></b><s:hidden name="tranId"/></td>
												<td width="3%" align="center"></td>
												<td width="49%"><s:text name="Referance No"/> : <b><s:property value="sno"/></b><s:hidden name="sno"/></td>
											</tr></table>
										</td>
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
							<td colspan="2" class="bg">
							 	<table width="100%"  align="center" cellpadding="0" cellspacing="1" bgcolor="#ffffff">
							 		<tr><td width="30%"><s:text name="REF. NO."/></td><td width="3%" align="center">:</td><td width="67%"><s:property value="refNo"/></td></tr>
								 	<tr><td><s:text name="ORDER NO."/></td><td width="3%" align="center">:</td><td><s:property value="orderNo"/></td></tr>
								 	<tr><td><s:text name="L/C NO."/></td><td width="3%" align="center">:</td><td><s:property value="lcNo"/></td></tr>
								 	<tr><td><s:text name="VESSEL NAME"/></td><td width="3%" align="center">:</td><td><s:property value="vesselName"/></td></tr>
								 	<tr><td><s:text name="SAILING DATE"/></td><td width="3%" align="center">:</td><td><s:property value="sailingDate"/></td></tr>
								 	<tr><td><s:text name="PACKING DETAILS"/></td><td width="3%" align="center">:</td><td><s:property value="packingDetail"/></td></tr>
								 	<tr><td><s:text name="INVOICE VALUE"/></td><td width="3%" align="center">:</td><td><s:property value="invoiceValue"/></td></tr>
								 	<tr><td><s:text name="ROE"/></td><td width="3%" align="center">:</td><td><s:property value="roe"/></td></tr>
								 	<tr><td><s:text name="SETTLING AGENT"/></td><td width="3%" align="center">:</td><td><s:property value="settlingAgent"/></td></tr>
							 		<tr class="runtext">
										<td height="14" align="right" colspan="3"></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr align="center">
							<td class="text">
								<s:submit type="button" name="close"  value="Close" cssClass="btn" onclick="window.close();return false;"/>
							</td>
						</tr>
					</table>
				</s:form>
			</td></tr>
		</table>
 	</body>
</html>
