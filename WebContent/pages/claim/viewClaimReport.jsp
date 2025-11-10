<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ page isELIgnored="false"%>
<html>
	<head>
		<sj:head jqueryui="true" jquerytheme="start" />
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/cssbootstrap/bootstrap.min.css" />
		<link
			href="<%=request.getContextPath()%>/cssbootstrap/footable-0.1.css"
			rel="stylesheet" type="text/css" />
		<style type="text/css">
.royamenuhead a {
	color: #fff;
}

th.sortable a {
	background-color: #337ab7;
}

#datePick .ui-datepicker-trigger {
	display: none;
}
</style>
		<script type="text/javascript">
	function stopRKey(evt) { 
		var evt = (evt) ? evt : ((event) ? event : null); 
		var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
		if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
	} 
	document.onkeypress = stopRKey;
</script>
	</head>
	<body>
		<table>
			<tr>
		    	<td>
				   	<s:if test="hasActionErrors()">
						<font color="red" style="list-style:none; "><s:actionerror cssStyle="list-style:none;"/></font>
					</s:if>
					<s:if test="hasActionMessages()">
						<s:actionmessage cssStyle="list-style:none; color:green;"/>
					</s:if>
				</td>
			</tr>
		</table>			
		<div class="panel panel-primary">
			<div class="panel-heading">
				<s:if test='(reqFrom == "View" )'>
					<s:text name="claim.claimIntimation" />
				</s:if>
				<s:else>
					<s:text name="claim.claimDetails" />
				</s:else>
			</div>
			<div id="tabs-1">
				<div class="panel-body">
					<div id="pendingPolicies">
						<s:form name="claimReport" method="post" theme="simple"
							action="claimsave">
							<table width="100%">
								<tr>
									<td>
										<table width="100%" class="footable">
											<tr>
												<td width="20%">
													<b><s:text name="claim.policyNo" /> </b>
												</td>
												<td width="30%">
													<s:property value="policyNo" />
												</td>
												<td width="20%">
													<b><s:text name="claim.startDate" /> </b>
												</td>
												<td width="30%">
													<s:property value="startDate" />
													<s:hidden name="startDate" />
												</td>
											</tr>
											<tr>
												<td width="20%">
													<b><s:text name="claim.purchasedDate" /> </b>
												</td>
												<td width="30%">
													<s:property value="inceptionDate" />
												</td>
												<td colspan="2"></td>
												<s:hidden name="inceptionDate"></s:hidden>
											</tr>
											<tr>
												<td colspan="4">
													<b><s:text name="claim.assuredName" /> </b>
													<br/>
													<table>
														<tr>
															<td width="30%">
																<s:if test='%{assuredName!="" }'>
																	<s:property value="assuredName" />
																</s:if>
															</td>
															<s:hidden name="assuredName"></s:hidden>
														</tr>
														<tr>
															<td width="30%">
																<s:if test='%{assuredName!="" }'>
																	<s:property value="address" />
																</s:if>
															</td>
														</tr>
													</table>
												</td>
											</tr>
											<tr>
												<td colspan="4" class="heading">
													<b><s:text name="claim.voyage" /> </b>
												</td>
											</tr>
											<tr>
												<s:if test='%{voyageFrom!="" && voyageTo!=""}'>
													<td colspan="4">
														<table width="100%">
															<tr>
																<td width="5%" style="padding: 5px;">
																	<b><s:text name="claim.from" /> </b>
																</td>
																<td width="45%" style="padding: 5px;">
																	: &nbsp; <s:property value="voyageFrom" />
																</td>
																<td width="5%" style="padding: 5px;">
																	<b><s:text name="claim.to" /></b>
																</td>
																<td width="45%"  style="padding: 5px;">
																	: &nbsp; <s:property value="voyageTo" />
																</td>
															</tr>
														</table>
													</td>
												</s:if>
											</tr>
											<tr>
												<s:if test='%{modeOfTransport!="" }'>
													<td width="20%">
														<b><s:text name="claim.modeOfTransport" /> </b>
													</td>
													<td width="30%">
														<s:property value="modeOfTransport" />
													</td>

												</s:if>
												<s:if test='%{modeOfTransport!="" }'>
													<td width="20%">
														<b><s:text name="Cover" /> </b>
													</td>
													<td width="30%">
														<s:property value="coverName" />
													</td>

												</s:if>
											</tr>
											<tr>
												<td colspan="2">
													<table width="100%">
														<s:if test='%{exchangeRate!="" || saleTerm!="" }'>
															<tr>
																<td>
																	<b><s:text name="claim.basisValuation" /> </b>
																</td>
															</tr>
															<tr>
																<s:if test='%{saleTerm!="" }'>
																	<td>
																		<b style="margin: 0 0 0 50px;"><s:text
																				name="claim.saleTerm" /> </b>
																	</td>
																	<td>
																		<font style="margin-left: 36px;"></font>
																		<s:property value="saleTerm" />
																	</td>
																</s:if>
															</tr>
															<tr>
																<s:if test='%{exchangeRate!="" }'>
																	<td>
																		<b style="margin: 0 0 0 50px;"><s:text
																				name="claim.exchangeRate" /> </b>
																	</td>
																	<td>
																		<font style="margin-left: 36px;"></font>
																		<s:property value="exchangeRate" />
																	</td>
																</s:if>
															</tr>
														</s:if>
													</table>
												</td>
												<td>
													<b><s:text name="Currency"></s:text></b>
												</td>
												<td><s:property value="currencyName" /></td>
											</tr>
											<tr>
												<td colspan="4" class="heading">
													<s:text name="Commodity" />
												</td>
											</tr>
											<tr>
												<td colspan="4">
													<table class="footable" width="100%">
														<thead>
														<tr>
															<th width="20%"> <s:text name="Commodity Discription" /> </th>
															<th width="20%"> <s:text name="Supplier Name" /> </th>
															<th width="20%"> <s:text name="Invoice No." /> </th>
															<th width="20%"> <s:text name="Invoice Date" /> </th>
															<th width="20%"> <s:text name="Sum Insured" /> </th>
														</tr>
														</thead>
														<tbody>
														<tr>
															<td><s:property value="subjMatter" /></td>
															<td><s:property value="supplierName" /></td>
															<td><s:property value="invoiceNo" /></td>
															<td><s:property value="lcDate" /></td>
															<td><s:property value="sumInsured" /></td>
														</tr>
														</tbody>
													</table>
												</td>
											</tr>
											<%--<tr>
												<td colspan="2">
													<table>
														<tr>
															<s:if test='%{subjectMatterInsured!="" }'>
																<tr>	
																	<td>
																		<b><s:text name="claim.subjectMaterInsured" /> </b>
																	</td>
																	<td>
																		<font style="margin-left: 44px;"></font>
																		<s:property value="subjectMatterInsured" />
																	</td>
																	</tr>
																	<tr>
																		<s:if test='%{supplierName!="" }'>
																			<td>
																				<b><s:text name="" /> </b>
																			</td>
																			<td>
																				<font style="margin-left: 34px;"></font>
																				<s:property value="supplierName" />
																			</td>	
																		</s:if>
																	</tr>
																</s:if>
															</tr>
														<tr>
															<s:if test='%{supplierName!="" }'>
																<td>
																	<b style="margin: 0 0 0 50px;"><s:text
																			name="claim.supplierName" /> </b>
																</td>
																<td>
																	<font style="margin-left: 34px;"></font>
																	<s:property value="supplierName" />
																</td>
															</s:if>
														</tr>
														<tr>
															<s:if test='%{invoiceNo!="" }'>
																<td>
																	<b style="margin: 0 0 0 50px;"><s:text
																			name="claim.invoiceNo" /> </b>
																</td>
																<td>
																	<font style="margin-left: 34px;"></font>
																	<s:property value="invoiceNo" />
																</td>
															</s:if>
														</tr>
														<tr>
															<s:if test='%{lcNumber!="" }'>
																<td>
																	<b style="margin: 0 0 0 50px;"><s:text
																			name="claim.lcNo" /> </b>
																</td>
																<td>
																	<font style="margin-left: 34px;"></font>
																	<s:property value="lcNumber" />
																</td>
															</s:if>
														</tr>
														<tr>
															<s:if test='%{lcDate!="" }'>
																<td>
																	<b style="margin: 0 0 0 50px;"><s:text
																			name="claim.lcDate" /> </b>
																</td>
																<td>
																	<font style="margin-left: 34px;"></font>
																	<s:property value="lcDate" />
																</td>
															</s:if>
														</tr>
													</table>
												</td>
												<td colspan="2">
													<table>
														<tr>
															<td colspan="4" height="5"></td>
														</tr>
														<tr>
															<s:if test='%{awbBillNumber!="" }'>
																<td>
																	<b><s:text name="claim.billNumber" /> </b>
																</td>
																<td>
																	<font style="margin-left: 36px;"></font>
																	<s:property value="awbBillNumber" />
																</td>
															</s:if>
														</tr>
														
													</table>
												</td>
											</tr>
											--%>
										</table>
									</td>
								</tr>
								<tr>
												<td height="5"></td>
								</tr>
								<s:if test='%{clauseId.size()>0 }'>
									<tr>
										<td width="100%" class="heading">
											<b><s:text name="claim.condition" /> </b>
										</td>
									</tr>
									<tr>
										<td>
											<ol>
												<s:iterator value="clauseId">
													<li>
														<s:property />
													</li>
												</s:iterator>
											</ol>
										</td>
									</tr>
								</s:if>
								<tr>
									<td height="5"></td>
								</tr>
								<!-- 
								<s:if test='%{warId.size()>0 }'>
									<tr>
										<td width="100%" class="heading">
											<b><s:text name="claim.warClause" /> </b>
										</td>
									</tr>
									<tr>
										<td>
											<ol>
												<s:iterator value="warId">
												<li>
													<s:property />
												</li>
												</s:iterator>
											</ol>
										</td>
									</tr>
								</s:if>
								<tr>
									<td height="5"></td>
								</tr>
								 -->
								<s:if test='%{warrentyId.size()>0 }'>
									<tr>
										<td width="100%" class="heading">
											<b><s:text name="claim.warenty" /> </b>
										</td>
									</tr>
									<tr>
										<td>
											<ol>
												<s:iterator value="warrentyId">
												<li>
													<s:property />
												</li>
												</s:iterator>
											</ol>
										</td>
									</tr>
								</s:if>
								<tr>
									<td height="5"></td>
								</tr>
								<s:if test='%{exclusionId.size()>0}'>
									<tr>
										<td width="100%" class="heading">
											<b><s:text name="claim.exclusion" /> </b>
										</td>
									</tr>
										<tr>
											<td>
												<ol>
													<s:iterator value="exclusionId">
														<li>
															<s:property />
														</li>
													</s:iterator>
												</ol>
											</td>
										</tr>
									</s:if>
									<tr>
										<td height="5"></td>
									</tr>
									<s:if test='%{commodityExcess!="0 for each and every loss or occurrence." }'>
										<tr>
											<td width="100%" class="heading">
												<b><s:text name="claim.excess" /> </b>
											</td>
										</tr>
										<tr>
											<td>
												<font style="margin-left: 20px;"><s:property value="commodityExcess" /> </font>
											</td>
										</tr>
									</s:if>
							</table>
							<hr />





							<table width="100%">
								<tr>
									<td width="20%">
										<s:text name="claim.lossDate"></s:text>
									</td>
									<td width="30%">
										<s:if test='(reqFrom=="View" || reqFrom.equals("View"))'>
											<div class="inputAppend" id="datePick" >
											<sj:datepicker name="lossDate" id="lossDate" changeYear="true"
												displayFormat="dd-mm-yy " cssClass="inputBox1" cssStyle="border-color: transparent;"
												disabled="true" />
											</div>
										</s:if>
										<s:else>
											<div class="inputAppend">
											<sj:datepicker name="lossDate" id="lossDate" changeYear="true"
												displayFormat="dd-mm-yy " cssClass="inputBox1" cssStyle="border-color: transparent;"
												disabled="rights" />
											</div>
										</s:else>
									</td>
									<s:if test='(reqFrom=="View" || reqFrom.equals("View"))'>
										<td width="20%">
											<s:text name="claim.intimationDate"></s:text>
										</td>
										<td width="30%">
											<div class="inputAppend" id="datePick" style="background-color: rgb(235, 235, 228);">
											<sj:datepicker name="intimationDate" id="intimationDate" 
												changeYear="true" displayFormat="dd-mm-yy" disabled="true" cssClass="inputBox1" cssStyle="border-color: transparent;"
												readonly="true" />
											</div>
										</td>
									</s:if>
									<s:else>
										<td width="20%">
											<s:text name="claim.intimationDate"></s:text>
										</td>
										<td width="30%">
											<div class="inputAppend" id="datePick" style="background-color: rgb(235, 235, 228);">
											<sj:datepicker name="intimationDate" id="intimationDate" 
												changeYear="true" displayFormat="dd-mm-yy" disabled="true" cssClass="inputBox1" cssStyle="border-color: transparent;"
												readonly="true" value="today" />
											</div>
										</td>
									</s:else>
								</tr>
								<tr>
									<td colspan="4">&nbsp;</td>
								</tr>
								<tr>
									
									<s:if test='(reqFrom=="View" || reqFrom.equals("View"))'>
										<td>
											<s:text name="claim.lossDescription"></s:text>
										</td>
										<td>
											<s:textarea name="lossDescription" rows="3" cssStyle="width: 90%" disabled="true"/>
										</td>
										<td>
											<s:text name="claim.remarks"/>
										</td>
										<td>
											<s:textarea name="remarks" cssStyle="width: 90%"/>
										</td>
									</s:if>
									<s:else>
										<td>
											<s:text name="claim.lossDescription"></s:text>
										</td>
										<td colspan="3">
											<s:textarea name="lossDescription" rows="3" cssStyle="width: 96%" />
										</td>
									
									</s:else>
								</tr>
								<tr></tr>
								</table>
								<table style="margin: 20px 0 0 400px;">
								<tr>
									
									<td align="center" >
										<s:if test='(reqFrom=="View" || reqFrom.equals("View"))'>
												<input type="button" value="Submit" class="btn btn-sm btn-success" onclick='fnSave("claimIntimation.action?reqFrom=UpdateRemarks");'/>
												&nbsp;&nbsp;&nbsp;
												<input type="button" value="Back" class="btn btn-sm btn-danger"onclick="funBack();">
										</s:if>
										<s:else>
											<input type="button" value="Submit" class="btn btn-sm btn-success" onclick='fnSave("claimsave");'/>
												&nbsp;&nbsp;&nbsp;
											<input type="button" value="Back" class="btn btn-sm btn-danger" onclick="fnBack();">
										</s:else>
									</td>
								</tr>
							</table>
							<s:hidden name="policyNo"></s:hidden>
						</s:form>
					</div>
				</div>
			</div>
		</div>
	</body>
	<SCRIPT type="text/javascript">
		function fnBack()
		{
			document.claimReport.action = "${pageContext.request.contextPath}/claimDetails.action";
			document.claimReport.submit();
		}
		function funBack()
		{
			document.claimReport.action = "${pageContext.request.contextPath}/claimIntimation.action";
			document.claimReport.submit();
		}
		function fnSave(action){
			document.claimReport.action = "${pageContext.request.contextPath}/"+action;
			document.claimReport.submit();
		}
		
	</SCRIPT>
</html>