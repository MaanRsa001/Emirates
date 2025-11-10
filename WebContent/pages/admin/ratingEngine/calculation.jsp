<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<%@ page isELIgnored="false"%>
<html>
	<head>
		<script language="JavaScript">
		function stopRKey(evt) { 
		  var evt = (evt) ? evt : ((event) ? event : null); 
		  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
		  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
		} 
		document.onkeypress = stopRKey; 
</script>
		<script type="text/javascript" src="pages/admin/ratingEngine/menu.js">
					</script>
	</head>
	<body>
		<div style="margin: 10px 0;"></div>
		<div class="easyui-layout" style="width: 900px; height: 965px;">
			<div data-options="region:'west',split:true" title="Options"
				style="width: 150px;">
				<div class="easyui-accordion" style="overflow-y: scroll" data-options="fit:true,border:false" >
					<%@ include file="/pages/admin/ratingEngine/ratingEngineMenu.jsp"%>
				</div>
			</div>
			<div data-options="region:'center',title:'',iconCls:''">
				<div class="easyui-tabs"
					data-options="fit:true,border:false,plain:true" id="mainTab">
					<div title="Base Rate" style="padding: 5px">
						<s:form id="info" name="info" method="post" theme="simple">
							<table width="100%">
								<tr>
									<td height="5"></td>
								</tr>
								<tr>
									<td>
										<table width="100%" border="0" align="center" cellpadding="0"
											cellspacing="1" bgcolor="#E5E5E5">
											<tr>
												<td bgcolor="#FFFFFF"
													style="padding: 10px; background: #F8F8F8">

													<table width="100%" border="0" cellspacing="0"
														cellpadding="0">														
														<tr>
															<td class="heading">
																<s:text name="base.rate" />
															</td>
														</tr>
														<tr>
															<td>
																&nbsp;
															</td>
														</tr>
														<tr align="center">
															<td bgcolor="#FFFFFF">
																<table width="100%" border="0" cellspacing="0"
																	cellpadding="0">

																	<tr>
																		<td class="bg">
																			<table cellpadding="4" cellspacing="0">

																				<tr>
																					<td>
																						<s:text name="rate.transport" />
																					</td>
																					<td>
																						<s:textfield name="transportRate" cssClass="input"
																							id="transportRate" readonly="true" />
																					</td>
																				</tr>
																				<tr>
																					<td>
																						<s:text name="rate.sum" />
																					</td>
																					<td>
																						<s:textfield name="sumRRate" cssClass="input"
																							id="sumRRate" readonly="true"/>
																					</td>
																					<td style="color: red;">
																					<s:fielderror> <s:param value="%{'sumRRate'}" /></s:fielderror>																					
																					</td>																				
																				</tr>
																				<tr>
																					<td>
																						<s:text name="rate.fragile" />
																					</td>
																					<td>
																						<s:textfield name="fragileRate" cssClass="input"
																							id="fragileRate" readonly="true"/>
																					</td>
																				</tr>
																				<tr>
																					<td>
																						<s:text name="rate.country" />
																					</td>
																					<td>
																						<s:textfield name="countryRate" cssClass="input"
																							id="countryRate" readonly="true"/>
																					</td>
																					<td style="color: red;">
																					<s:fielderror> <s:param value="%{'countryRate'}" /></s:fielderror>																					
																					</td>																																		
																				
																				</tr>
																				<tr>
																					<td>
																						<s:text name="rate.conveyance" />
																					</td>
																					<td>
																						<s:textfield name="conveyanceRate"
																							cssClass="input" id="conveyanceRate" readonly="true"/>
																					</td>
																				</tr>
																				<tr>
																					<td>
																						<s:text name="rate.commodity" />
																					</td>
																					<td>
																						<s:textfield name="commodityRate" cssClass="input"
																							id="commodityRate" readonly="true"/></td>	
																							<td style="color: red;">
																					<s:fielderror> <s:param value="%{'commodityRate'}" /></s:fielderror>																					
																					</td>
																																										
																				</tr>
																				
																				<tr>
																					<td>
																						<s:text name="rate.wsrcc" />
																					</td>
																					<td>
																						<s:textfield name="wsrccRate" cssClass="input"
																							id="wsrccRate" readonly="true"/>
																					</td>
																				</tr>
																				<tr>
																					<td>
																						<s:text name="rate.final" />
																					</td>
																					<td>
																						<s:textfield name="finalRate" cssClass="input"
																							id="finalRate" readonly="true"/>
																					</td>
																				</tr>
																				<tr>
																					<td></td>
																					<td></td>
																				</tr>
																			</table>
																			<p align="left">
																				<s:submit value="Back" cssClass="btn"
																					action="baserateRatingEngine" />
																			</p>
																		</td>
																	</tr>
																</table>
															</td>
														</tr>
													</table>
													<s:hidden name="agencyCode" />
													<s:hidden name="login_Id" />
													<s:hidden name="borganization" />
													<s:hidden name="bcode" />
													<s:hidden name="transID" />
													<s:hidden name="coverageID" />
													<s:hidden name="coveyID" />
													<s:hidden name="baserateCountryID" />
													<s:hidden name="baserateCommodityID" />
													<s:hidden name="fragile" />
													<s:hidden name="sumrate" />

												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</s:form>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>