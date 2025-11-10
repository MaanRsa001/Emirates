<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<%@ taglib uri="/struts-jquery-tags" prefix="sj"%>
<html>
	<head>
		<sj:head jqueryui="true" jquerytheme="start" />
		<SCRIPT language=Javascript>
       function isNumberKey(evt)
       {
          var charCode = (evt.which) ? evt.which : event.keyCode;
          if (charCode != 46 && charCode > 31 
            && (charCode < 48 || charCode > 57))
             return false;

          return true;
       }
       
    </SCRIPT>
		<script language="JavaScript">
		function stopRKey(evt) { 
		  var evt = (evt) ? evt : ((event) ? event : null); 
		  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
		  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
		} 
		document.onkeypress = stopRKey; 
    </script>
		<script type="text/javascript" src="pages/admin/ratingEngine/menu.js"></script>
		<script type="text/javascript" src="js/tcal.js"></script>
	</head>
	<body>

		<div style="margin: 10px 0;"></div>
		<div class="easyui-layout" style="height:100vh;">
			<div data-options="region:'west',split:true" title="Options"
				style="width: 150px;">
				<div class="easyui-accordion" style="overflow-y: scroll" data-options="fit:true,border:false" >
					<%@ include file="/pages/admin/ratingEngine/ratingEngineMenu.jsp"%>
				</div>
			</div>
			<div data-options="region:'center',title:'',iconCls:''">
				<div class="easyui-tabs"
					data-options="fit:true,border:false,plain:true" id="mainTab">
					<div title="Commodity Master" style="padding: 5px">
						<s:form id="info" name="info" method="post"
							action="updateCommodityRating.action" theme="simple">
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
															<td style="color: red;">
																<s:actionerror />
															</td>
														</tr>
														<tr>
															<td>
																&nbsp;&nbsp;
															</td>
														</tr>
														<tr>
															<td class="heading">
																<s:text name="commodity.edit" />
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
																			<table width="100%" border="0" align="center"
																				cellpadding="4" cellspacing="0">

																			<tr>
																				<td width="5%">&nbsp;</td>
																				<td width="45%">
																					<s:text name="commodity.group"/><font color="red">*</font>
																				</td>
																				<td width="45%">
																					<s:select name="commoditygroupId" list="goodsCategoryGroupList" headerKey="" headerValue="---Select---" listKey="CODE" listValue="CODEDESC" cssClass="inputSelect" disabled="%{#disable1}" onchange="fnloadcomList(this.value);"></s:select>
																				</td>
																				<td width="5%">
																						&nbsp;
																					</td>
																				</tr>

																				<tr>
																					<td width="5%">
																						&nbsp;
																					</td>
																					<td width="45%">
																						<s:text name="commodity.name" />
																						<font color="red">*</font>
																					</td>
																					<td width="45%">
																						<div class="inputAppend">
																							<s:textfield name="commodityName"
																								id="commodityName" cssClass="inputBox1"
																								cssStyle="border: none;background: transparent; width: 90%;"
																								maxlength="100" />
																							&nbsp;
																							<a><img src="images/list_icon.png"
																									style="cursor: hand" height="16"
																									src="images/list_icon.png"
																									onClick="javascript:openAmendedData();"
																									border='0' name="cid" alt="view amended data">
																							</a>
																						</div>
																					</td>
																					<td width="5%">
																						&nbsp;
																					</td>
																				</tr>
																				<%--
																				<tr>
																					<td>
																						&nbsp;
																					</td>
																					<td>
																						<s:text name="RAG" />
																						<font color="red">*</font>
																					</td>
																					<td>
																						<s:select name="commodityTypeID"
																							id="commodityTypeID" cssClass="inputSelect"
																							list="#{'R':'Red','G':'Green','A':'Amber'}"
																							headerKey="-1" headerValue="-- Select --" />
																					</td>
																					<s:hidden name="commodityID" id="commodityID" />
																					<s:hidden name="amendID" />
																					<td>
																						&nbsp;
																					</td>

																				</tr>
																				--%>
																				<s:hidden name="commodityTypeID" id="commodityTypeID" />
																				<s:hidden name="commodityID" id="commodityID" />
																				<s:hidden name="amendID" />
																				<tr>
																					<td>
																						&nbsp;
																					</td>
																					<td>
																						<s:text name="commodity.rate" />
																						<font color="red">*</font>
																					</td>
																					<td>
																						<s:textfield name="commodityRates"
																							id="commodityRates" cssClass="inputBox" maxlength="6"
																							onkeypress="return fun_AllowOnlyAmountAndDot(this.id)" />
																					</td>
																					<td>
																						&nbsp;
																					</td>

																				</tr>
																				<tr>
																					<td>
																						&nbsp;
																					</td>
																					<td>
																						<s:text name="icc.a.sea" />
																					</td>
																					<td>
																						<table width="100%">
																							<tr>
																								<td width="50%">
																									<s:textfield name="txtchkICC_A_SEAClause_1"
																										id="txtchkICC_A_SEAClause_1" readonly="true"
																										cssClass="inputBox" size="25" />
																								</td>
																								<td width="50%">
																									<a><img src="images/list_icon.png"
																											style="cursor: hand" height="16"
																											src="images/list_ic.jpg"
																											onClick="javascript:OpenClausePopUp('C','1','_1','txtchkICC_A_SEAClause_1');"
																											border='0' name="chkICC_A_SEAClause"
																											alt="Add Clause ">&nbsp;&nbsp;&nbsp;&nbsp;Clauses</a>
																								</td>
																							</tr>
																							<tr>
																								<td>
																									<s:textfield name="txtchkICC_A_SEAExclusive_1"
																										id="txtchkICC_A_SEAExclusive_1"
																										readonly="true" cssClass="inputBox" size="25" />
																								</td>
																								<td>
																									<a><img src="images/list_icon.png"
																											style="cursor: hand" height="16"
																											src="images/list_ic.jpg"
																											onClick="javascript:OpenClausePopUp('E','1','_1','txtchkICC_A_SEAExclusive_1');"
																											border='0' name="chkICC_A_SEAExplosion"
																											alt="Add Explosion ">&nbsp;&nbsp;&nbsp;&nbsp;Exclusion</a>
																								</td>
																							</tr>
																							<tr>
																								<td>
																									<s:textfield name="txtchkICC_A_SEAWarranty_1"
																										id="txtchkICC_A_SEAWarranty_1" readonly="true"
																										cssClass="inputBox" size="25" />
																								</td>
																								<td>
																									<a><img src="images/list_icon.png"
																											style="cursor: hand" height="16"
																											src="images/list_ic.jpg"
																											onClick="javascript:OpenClausePopUp('W','1','_1','txtchkICC_A_SEAWarranty_1');"
																											border='0' name="chkICC_A_SEAWarrenry"
																											alt="Add warranties ">&nbsp;&nbsp;&nbsp;&nbsp;Warranty</a>
																								</td>
																							</tr>
																							<tr>
																								<td>
																									<s:textfield name="txtchkICC_A_SEAWarCover_1"
																										id="txtchkICC_A_SEAWarCover_1" readonly="true"
																										cssClass="inputBox" size="25" />
																								</td>
																								<td>
																									<a><img src="images/list_icon.png"
																											style="cursor: hand" height="16"
																											src="images/list_ic.jpg"
																											onClick="javascript:OpenClausePopUp('R','1','_1','txtchkICC_A_SEAWarCover_1');"
																											border='0' name="txtchkICC_A_SEAWarCover"
																											alt="Add Clause ">&nbsp;&nbsp;&nbsp;&nbsp;War
																										Cover</a>
																								</td>
																							</tr>
																							<tr>
																								<td>
																									<s:radio name="radDetective_1"
																										list="#{'Y':'Yes','N':'No'}" />
																								</td>
																								<td>
																									<s:text name="commodity.Deductible" />
																								</td>
																							</tr>
																						</table>
																					</td>
																					<td>
																						&nbsp;
																					</td>

																				</tr>
																				<tr>
																					<td>
																						&nbsp;
																					</td>
																					<td>
																						<s:text name="icc.b.sea" />
																					</td>
																					<td>
																						<table width="100%">
																							<tr>
																								<td width="50%">
																									<s:textfield name="txtchkICC_A_SEAClause_2"
																										id="txtchkICC_A_SEAClause_2" readonly="true"
																										cssClass="inputBox" size="25" />
																								</td>
																								<td width="50%">
																									<a><img src="images/list_icon.png"
																											style="cursor: hand" height="16"
																											src="images/list_ic.jpg"
																											onClick="javascript:OpenClausePopUp('C','2','_2','txtchkICC_A_SEAClause_2');"
																											border='0' name="chkICC_A_SEAClause"
																											alt="Add Clause ">&nbsp;&nbsp;&nbsp;&nbsp;Clauses</a>
																								</td>
																							</tr>
																							<tr>
																								<td>
																									<s:textfield name="txtchkICC_A_SEAExclusive_2"
																										id="txtchkICC_A_SEAExclusive_2"
																										readonly="true" cssClass="inputBox" size="25" />
																								</td>
																								<td>
																									<a><img src="images/list_icon.png"
																											style="cursor: hand" height="16"
																											src="images/list_ic.jpg"
																											onClick="javascript:OpenClausePopUp('E','2','_2','txtchkICC_A_SEAExclusive_2');"
																											border='0' name="chkICC_A_SEAExplosion"
																											alt="Add Explosion ">&nbsp;&nbsp;&nbsp;&nbsp;Exclusion</a>
																								</td>
																							</tr>
																							<tr>
																								<td>
																									<s:textfield name="txtchkICC_A_SEAWarranty_2"
																										id="txtchkICC_A_SEAWarranty_2" readonly="true"
																										cssClass="inputBox" size="25" />
																								</td>
																								<td>
																									<a><img src="images/list_icon.png"
																											style="cursor: hand" height="16"
																											src="images/list_ic.jpg"
																											onClick="javascript:OpenClausePopUp('W','2','_2','txtchkICC_A_SEAWarranty_2');"
																											border='0' name="chkICC_A_SEAWarrenry"
																											alt="Add warranties ">&nbsp;&nbsp;&nbsp;&nbsp;Warranty</a>
																								</td>
																							</tr>
																							<tr>
																								<td>
																									<s:textfield name="txtchkICC_A_SEAWarCover_2"
																										id="txtchkICC_A_SEAWarCover_2" readonly="true"
																										cssClass="inputBox" size="25" />
																								</td>
																								<td>
																									<a><img src="images/list_icon.png"
																											style="cursor: hand" height="16"
																											src="images/list_ic.jpg"
																											onClick="javascript:OpenClausePopUp('R','2','_2','txtchkICC_A_SEAWarCover_2');"
																											border='0' name="txtchkICC_A_SEAWarCover"
																											alt="Add Clause ">&nbsp;&nbsp;&nbsp;&nbsp;War
																										Cover</a>
																								</td>
																							</tr>
																							<tr>
																								<td>
																									<s:radio name="radDetective_2"
																										list="#{'Y':'Yes','N':'No'}" />
																								</td>
																								<td>
																									<s:text name="commodity.Deductible" />
																								</td>
																							</tr>
																						</table>
																					</td>
																					<td>
																						&nbsp;
																					</td>

																				</tr>
																				<tr>
																					<td>
																						&nbsp;
																					</td>
																					<td>
																						<s:text name="icc.c.sea" />
																					</td>
																					<td>
																						<table width="100%">
																							<tr>
																								<td width="50%">
																									<s:textfield name="txtchkICC_A_SEAClause_3"
																										id="txtchkICC_A_SEAClause_3" readonly="true"
																										cssClass="inputBox" size="25" />
																								</td>
																								<td width="50%">
																									<a><img src="images/list_icon.png"
																											style="cursor: hand" height="16"
																											src="images/list_ic.jpg"
																											onClick="javascript:OpenClausePopUp('C','3','_3','txtchkICC_A_SEAClause_3');"
																											border='0' name="chkICC_A_SEAClause"
																											alt="Add Clause ">&nbsp;&nbsp;&nbsp;&nbsp;Clauses</a>
																								</td>
																							</tr>
																							<tr>
																								<td>
																									<s:textfield name="txtchkICC_A_SEAExclusive_3"
																										id="txtchkICC_A_SEAExclusive_3"
																										readonly="true" cssClass="inputBox" size="25" />
																								</td>
																								<td>
																									<a><img src="images/list_icon.png"
																											style="cursor: hand" height="16"
																											src="images/list_ic.jpg"
																											onClick="javascript:OpenClausePopUp('E','3','_3','txtchkICC_A_SEAExclusive_3');"
																											border='0' name="chkICC_A_SEAExplosion"
																											alt="Add Explosion ">&nbsp;&nbsp;&nbsp;&nbsp;Exclusion</a>
																								</td>
																							</tr>
																							<tr>
																								<td>
																									<s:textfield name="txtchkICC_A_SEAWarranty_3"
																										id="txtchkICC_A_SEAWarranty_3" readonly="true"
																										cssClass="inputBox" size="25" />
																								</td>
																								<td>
																									<a><img src="images/list_icon.png"
																											style="cursor: hand" height="16"
																											src="images/list_ic.jpg"
																											onClick="javascript:OpenClausePopUp('W','3','_3','txtchkICC_A_SEAWarranty_3');"
																											border='0' name="chkICC_A_SEAWarrenry"
																											alt="Add warranties ">&nbsp;&nbsp;&nbsp;&nbsp;Warranty</a>
																								</td>
																							</tr>
																							<tr>
																								<td>
																									<s:textfield name="txtchkICC_A_SEAWarCover_3"
																										id="txtchkICC_A_SEAWarCover_3" readonly="true"
																										cssClass="inputBox" size="25" />
																								</td>
																								<td>
																									<a><img src="images/list_icon.png"
																											style="cursor: hand" height="16"
																											src="images/list_ic.jpg"
																											onClick="javascript:OpenClausePopUp('R','3','_3','txtchkICC_A_SEAWarCover_3');"
																											border='0' name="txtchkICC_A_SEAWarCover"
																											alt="Add Clause ">&nbsp;&nbsp;&nbsp;&nbsp;War
																										Cover</a>
																								</td>
																							</tr>
																							<tr>
																								<td>
																									<s:radio name="radDetective_3"
																										list="#{'Y':'Yes','N':'No'}" />
																								</td>
																								<td>
																									<s:text name="commodity.Deductible" />
																								</td>
																							</tr>
																						</table>
																					</td>
																					<td>
																						&nbsp;
																					</td>
																				</tr>
																				<tr>
																					<td>
																						&nbsp;
																					</td>
																					<td>
																						<s:text name="ICC (C) + NON DELIVERY" />
																					</td>
																					<td>
																						<table width="100%">
																							<tr>
																								<td width="50%">
																									<s:textfield name="txtchkICC_A_SEAClause_4"
																										id="txtchkICC_A_SEAClause_4" readonly="true"
																										cssClass="inputBox" size="25" />
																								</td>
																								<td width="50%">
																									<a><img src="images/list_icon.png"
																											style="cursor: hand" height="16"
																											src="images/list_ic.jpg"
																											onClick="javascript:OpenClausePopUp('C','4','_4','txtchkICC_A_SEAClause_4');"
																											border='0' name="chkICC_A_SEAClause"
																											alt="Add Clause ">&nbsp;&nbsp;&nbsp;&nbsp;Clauses</a>
																								</td>
																							</tr>
																							<tr>
																								<td>
																									<s:textfield name="txtchkICC_A_SEAExclusive_4"
																										id="txtchkICC_A_SEAExclusive_4"
																										readonly="true" cssClass="inputBox" size="25" />
																								</td>
																								<td>
																									<a><img src="images/list_icon.png"
																											style="cursor: hand" height="16"
																											src="images/list_ic.jpg"
																											onClick="javascript:OpenClausePopUp('E','4','_4','txtchkICC_A_SEAExclusive_4');"
																											border='0' name="chkICC_A_SEAExplosion"
																											alt="Add Explosion ">&nbsp;&nbsp;&nbsp;&nbsp;Exclusion</a>
																								</td>
																							</tr>
																							<tr>
																								<td>
																									<s:textfield name="txtchkICC_A_SEAWarranty_4"
																										id="txtchkICC_A_SEAWarranty_4" readonly="true"
																										cssClass="inputBox" size="25" />
																								</td>
																								<td>
																									<a><img src="images/list_icon.png"
																											style="cursor: hand" height="16"
																											src="images/list_ic.jpg"
																											onClick="javascript:OpenClausePopUp('W','4','_4','txtchkICC_A_SEAWarranty_4');"
																											border='0' name="chkICC_A_SEAWarrenry"
																											alt="Add warranties ">&nbsp;&nbsp;&nbsp;&nbsp;Warranty</a>
																								</td>
																							</tr>
																							<tr>
																								<td>
																									<s:textfield name="txtchkICC_A_SEAWarCover_4"
																										id="txtchkICC_A_SEAWarCover_4" readonly="true"
																										cssClass="inputBox" size="25" />
																								</td>
																								<td>
																									<a><img src="images/list_icon.png"
																											style="cursor: hand" height="16"
																											src="images/list_ic.jpg"
																											onClick="javascript:OpenClausePopUp('R','4','_4','txtchkICC_A_SEAWarCover_4');"
																											border='0' name="txtchkICC_A_SEAWarCover"
																											alt="Add Clause ">&nbsp;&nbsp;&nbsp;&nbsp;War
																										Cover</a>
																								</td>
																							</tr>
																							<tr>
																								<td>
																									<s:radio name="radDetective_4"
																										list="#{'Y':'Yes','N':'No'}" />
																								</td>
																								<td>
																									<s:text name="commodity.Deductible" />
																								</td>
																							</tr>
																						</table>
																					</td>
																					<td>
																						&nbsp;
																					</td>
																				</tr>
																				<tr>
																					<td>
																						&nbsp;
																					</td>
																					<td>
																						<s:text name="Frozen Meat(A)" />
																					</td>
																					<td>
																						<table width="100%">
																							<tr>
																								<td width="50%">
																									<s:textfield name="txtchkICC_A_SEAClause_5"
																										id="txtchkICC_A_SEAClause_5" readonly="true"
																										cssClass="inputBox" size="25" />
																								</td>
																								<td width="50%">
																									<a><img src="images/list_icon.png"
																											style="cursor: hand" height="16"
																											src="images/list_ic.jpg"
																											onClick="javascript:OpenClausePopUp('C','5','_5','txtchkICC_A_SEAClause_5');"
																											border='0' name="chkICC_A_SEAClause"
																											alt="Add Clause ">&nbsp;&nbsp;&nbsp;&nbsp;Clauses</a>
																								</td>
																							</tr>
																							<tr>
																								<td>
																									<s:textfield name="txtchkICC_A_SEAExclusive_5"
																										id="txtchkICC_A_SEAExclusive_5"
																										readonly="true" cssClass="inputBox" size="25" />
																								</td>
																								<td>
																									<a><img src="images/list_icon.png"
																											style="cursor: hand" height="16"
																											src="images/list_ic.jpg"
																											onClick="javascript:OpenClausePopUp('E','5','_5','txtchkICC_A_SEAExclusive_5');"
																											border='0' name="chkICC_A_SEAExplosion"
																											alt="Add Explosion ">&nbsp;&nbsp;&nbsp;&nbsp;Exclusion</a>
																								</td>
																							</tr>
																							<tr>
																								<td>
																									<s:textfield name="txtchkICC_A_SEAWarranty_5"
																										id="txtchkICC_A_SEAWarranty_5" readonly="true"
																										cssClass="inputBox" size="25" />
																								</td>
																								<td>
																									<a><img src="images/list_icon.png"
																											style="cursor: hand" height="16"
																											src="images/list_ic.jpg"
																											onClick="javascript:OpenClausePopUp('W','5','_5','txtchkICC_A_SEAWarranty_5');"
																											border='0' name="chkICC_A_SEAWarrenry"
																											alt="Add warranties ">&nbsp;&nbsp;&nbsp;&nbsp;Warranty</a>
																								</td>
																							</tr>
																							<tr>
																								<td>
																									<s:textfield name="txtchkICC_A_SEAWarCover_5"
																										id="txtchkICC_A_SEAWarCover_5" readonly="true"
																										cssClass="inputBox" size="25" />
																								</td>
																								<td>
																									<a><img src="images/list_icon.png"
																											style="cursor: hand" height="16"
																											src="images/list_ic.jpg"
																											onClick="javascript:OpenClausePopUp('R','5','_5','txtchkICC_A_SEAWarCover_5');"
																											border='0' name="txtchkICC_A_SEAWarCover"
																											alt="Add Clause ">&nbsp;&nbsp;&nbsp;&nbsp;War
																										Cover</a>
																								</td>
																							</tr>
																							<tr>
																								<td>
																									<s:radio name="radDetective_5"
																										list="#{'Y':'Yes','N':'No'}" />
																								</td>
																								<td>
																									<s:text name="commodity.Deductible" />
																								</td>
																							</tr>
																						</table>
																					</td>
																					<td>
																						&nbsp;
																					</td>
																				</tr>
																				<tr>
																					<td>
																						&nbsp;
																					</td>
																					<td>
																						<s:text name="Frozen Food(A)" />
																					</td>
																					<td>
																						<table width="100%">
																							<tr>
																								<td width="50%">
																									<s:textfield name="txtchkICC_A_SEAClause_6"
																										id="txtchkICC_A_SEAClause_6" readonly="true"
																										cssClass="inputBox" size="25" />
																								</td>
																								<td width="50%">
																									<a><img src="images/list_icon.png"
																											style="cursor: hand" height="16"
																											src="images/list_ic.jpg"
																											onClick="javascript:OpenClausePopUp('C','6','_6','txtchkICC_A_SEAClause_6');"
																											border='0' name="chkICC_A_SEAClause"
																											alt="Add Clause ">&nbsp;&nbsp;&nbsp;&nbsp;Clauses</a>
																								</td>
																							</tr>
																							<tr>
																								<td>
																									<s:textfield name="txtchkICC_A_SEAExclusive_6"
																										id="txtchkICC_A_SEAExclusive_6"
																										readonly="true" cssClass="inputBox" size="25" />
																								</td>
																								<td>
																									<a><img src="images/list_icon.png"
																											style="cursor: hand" height="16"
																											src="images/list_ic.jpg"
																											onClick="javascript:OpenClausePopUp('E','6','_6','txtchkICC_A_SEAExclusive_6');"
																											border='0' name="chkICC_A_SEAExplosion"
																											alt="Add Explosion ">&nbsp;&nbsp;&nbsp;&nbsp;Exclusion</a>
																								</td>
																							</tr>
																							<tr>
																								<td>
																									<s:textfield name="txtchkICC_A_SEAWarranty_6"
																										id="txtchkICC_A_SEAWarranty_6" readonly="true"
																										cssClass="inputBox" size="25" />
																								</td>
																								<td>
																									<a><img src="images/list_icon.png"
																											style="cursor: hand" height="16"
																											src="images/list_ic.jpg"
																											onClick="javascript:OpenClausePopUp('W','6','_6','txtchkICC_A_SEAWarranty_6');"
																											border='0' name="chkICC_A_SEAWarrenry"
																											alt="Add warranties ">&nbsp;&nbsp;&nbsp;&nbsp;Warranty</a>
																								</td>
																							</tr>
																							<tr>
																								<td>
																									<s:textfield name="txtchkICC_A_SEAWarCover_6"
																										id="txtchkICC_A_SEAWarCover_6" readonly="true"
																										cssClass="inputBox" size="25" />
																								</td>
																								<td>
																									<a><img src="images/list_icon.png"
																											style="cursor: hand" height="16"
																											src="images/list_ic.jpg"
																											onClick="javascript:OpenClausePopUp('R','6','_6','txtchkICC_A_SEAWarCover_6');"
																											border='0' name="txtchkICC_A_SEAWarCover"
																											alt="Add Clause ">&nbsp;&nbsp;&nbsp;&nbsp;War
																										Cover</a>
																								</td>
																							</tr>
																							<tr>
																								<td>
																									<s:radio name="radDetective_6"
																										list="#{'Y':'Yes','N':'No'}" />
																								</td>
																								<td>
																									<s:text name="commodity.Deductible" />
																								</td>
																							</tr>
																						</table>
																					</td>
																					<td>
																						&nbsp;
																					</td>
																				</tr>
																				<tr>
																					<td>
																						&nbsp;
																					</td>
																					<td>
																						<s:text name="Frozen Meat(C)" />
																					</td>
																					<td>
																						<table width="100%">
																							<tr>
																								<td width="50%">
																									<s:textfield name="txtchkICC_A_SEAClause_7"
																										id="txtchkICC_A_SEAClause_7" readonly="true"
																										cssClass="inputBox" size="25" />
																								</td>
																								<td width="50%">
																									<a><img src="images/list_icon.png"
																											style="cursor: hand" height="16"
																											src="images/list_ic.jpg"
																											onClick="javascript:OpenClausePopUp('C','7','_7','txtchkICC_A_SEAClause_7');"
																											border='0' name="chkICC_A_SEAClause"
																											alt="Add Clause ">&nbsp;&nbsp;&nbsp;&nbsp;Clauses</a>
																								</td>
																							</tr>
																							<tr>
																								<td>
																									<s:textfield name="txtchkICC_A_SEAExclusive_7"
																										id="txtchkICC_A_SEAExclusive_7"
																										readonly="true" cssClass="inputBox" size="25" />
																								</td>
																								<td>
																									<a><img src="images/list_icon.png"
																											style="cursor: hand" height="16"
																											src="images/list_ic.jpg"
																											onClick="javascript:OpenClausePopUp('E','7','_7','txtchkICC_A_SEAExclusive_7');"
																											border='0' name="chkICC_A_SEAExplosion"
																											alt="Add Explosion ">&nbsp;&nbsp;&nbsp;&nbsp;Exclusion</a>
																								</td>
																							</tr>
																							<tr>
																								<td>
																									<s:textfield name="txtchkICC_A_SEAWarranty_7"
																										id="txtchkICC_A_SEAWarranty_7" readonly="true"
																										cssClass="inputBox" size="25" />
																								</td>
																								<td>
																									<a><img src="images/list_icon.png"
																											style="cursor: hand" height="16"
																											src="images/list_ic.jpg"
																											onClick="javascript:OpenClausePopUp('W','7','_7','txtchkICC_A_SEAWarranty_7');"
																											border='0' name="chkICC_A_SEAWarrenry"
																											alt="Add warranties ">&nbsp;&nbsp;&nbsp;&nbsp;Warranty</a>
																								</td>
																							</tr>
																							<tr>
																								<td>
																									<s:textfield name="txtchkICC_A_SEAWarCover_7"
																										id="txtchkICC_A_SEAWarCover_7" readonly="true"
																										cssClass="inputBox" size="25" />
																								</td>
																								<td>
																									<a><img src="images/list_icon.png"
																											style="cursor: hand" height="16"
																											src="images/list_ic.jpg"
																											onClick="javascript:OpenClausePopUp('R','7','_7','txtchkICC_A_SEAWarCover_7');"
																											border='0' name="txtchkICC_A_SEAWarCover"
																											alt="Add Clause ">&nbsp;&nbsp;&nbsp;&nbsp;War
																										Cover</a>
																								</td>
																							</tr>
																							<tr>
																								<td>
																									<s:radio name="radDetective_7"
																										list="#{'Y':'Yes','N':'No'}" />
																								</td>
																								<td>
																									<s:text name="commodity.Deductible" />
																								</td>
																							</tr>
																						</table>
																					</td>
																					<td>
																						&nbsp;
																					</td>
																				</tr>

																				<tr>
																					<td>
																						&nbsp;
																					</td>
																					<td>
																						<s:text name="Frozen Food(C)" />
																					</td>
																					<td>
																						<table width="100%">
																							<tr>
																								<td width="50%">
																									<s:textfield name="txtchkICC_A_SEAClause_8"
																										id="txtchkICC_A_SEAClause_8" readonly="true"
																										cssClass="inputBox" size="25" />
																								</td>
																								<td width="50%">
																									<a><img src="images/list_icon.png"
																											style="cursor: hand" height="16"
																											src="images/list_ic.jpg"
																											onClick="javascript:OpenClausePopUp('C','8','_8','txtchkICC_A_SEAClause_8');"
																											border='0' name="chkICC_A_SEAClause"
																											alt="Add Clause ">&nbsp;&nbsp;&nbsp;&nbsp;Clauses</a>
																								</td>
																							</tr>
																							<tr>
																								<td>
																									<s:textfield name="txtchkICC_A_SEAExclusive_8"
																										id="txtchkICC_A_SEAExclusive_8"
																										readonly="true" cssClass="inputBox" size="25" />
																								</td>
																								<td>
																									<a><img src="images/list_icon.png"
																											style="cursor: hand" height="16"
																											src="images/list_ic.jpg"
																											onClick="javascript:OpenClausePopUp('E','8','_8','txtchkICC_A_SEAExclusive_8');"
																											border='0' name="chkICC_A_SEAExplosion"
																											alt="Add Explosion ">&nbsp;&nbsp;&nbsp;&nbsp;Exclusion</a>
																								</td>
																							</tr>
																							<tr>
																								<td>
																									<s:textfield name="txtchkICC_A_SEAWarranty_8"
																										id="txtchkICC_A_SEAWarranty_8" readonly="true"
																										cssClass="inputBox" size="25" />
																								</td>
																								<td>
																									<a><img src="images/list_icon.png"
																											style="cursor: hand" height="16"
																											src="images/list_ic.jpg"
																											onClick="javascript:OpenClausePopUp('W','8','_8','txtchkICC_A_SEAWarranty_8');"
																											border='0' name="chkICC_A_SEAWarrenry"
																											alt="Add warranties ">&nbsp;&nbsp;&nbsp;&nbsp;Warranty</a>
																								</td>
																							</tr>
																							<tr>
																								<td>
																									<s:textfield name="txtchkICC_A_SEAWarCover_8"
																										id="txtchkICC_A_SEAWarCover_8" readonly="true"
																										cssClass="inputBox" size="25" />
																								</td>
																								<td>
																									<a><img src="images/list_icon.png"
																											style="cursor: hand" height="16"
																											src="images/list_ic.jpg"
																											onClick="javascript:OpenClausePopUp('R','8','_8','txtchkICC_A_SEAWarCover_8');"
																											border='0' name="txtchkICC_A_SEAWarCover"
																											alt="Add Clause ">&nbsp;&nbsp;&nbsp;&nbsp;War
																										Cover</a>
																								</td>
																							</tr>
																							<tr>
																								<td>
																									<s:radio name="radDetective_8"
																										list="#{'Y':'Yes','N':'No'}" />
																								</td>
																								<td>
																									<s:text name="commodity.Deductible" />
																								</td>
																							</tr>
																						</table>
																					</td>
																					<td>
																						&nbsp;
																					</td>
																				</tr>
																				<tr>
																					<td>
																						&nbsp;
																					</td>
																					<td>
																						<s:text name="icc.air" />
																					</td>
																					<td>
																						<table width="100%">
																							<tr>
																								<td width="50%">
																									<s:textfield name="txtchkICC_A_SEAClause_9"
																										id="txtchkICC_A_SEAClause_9" readonly="true"
																										cssClass="inputBox" size="25" />
																								</td>
																								<td width="50%">
																									<a><img src="images/list_icon.png"
																											style="cursor: hand" height="16"
																											src="images/list_ic.jpg"
																											onClick="javascript:OpenClausePopUp('C','9','_9','txtchkICC_A_SEAClause_9');"
																											border='0' name="chkICC_A_SEAClause"
																											alt="Add Clause ">&nbsp;&nbsp;&nbsp;&nbsp;Clauses</a>
																								</td>
																							</tr>
																							<tr>
																								<td>
																									<s:textfield name="txtchkICC_A_SEAExclusive_9"
																										id="txtchkICC_A_SEAExclusive_9"
																										readonly="true" cssClass="inputBox" size="25" />
																								</td>
																								<td>
																									<a><img src="images/list_icon.png"
																											style="cursor: hand" height="16"
																											src="images/list_ic.jpg"
																											onClick="javascript:OpenClausePopUp('E','9','_9','txtchkICC_A_SEAExclusive_9');"
																											border='0' name="chkICC_A_SEAExplosion"
																											alt="Add Explosion ">&nbsp;&nbsp;&nbsp;&nbsp;Exclusion</a>
																								</td>
																							</tr>
																							<tr>
																								<td>
																									<s:textfield name="txtchkICC_A_SEAWarranty_9"
																										id="txtchkICC_A_SEAWarranty_9" readonly="true"
																										cssClass="inputBox" size="25" />
																								</td>
																								<td>
																									<a><img src="images/list_icon.png"
																											style="cursor: hand" height="16"
																											src="images/list_ic.jpg"
																											onClick="javascript:OpenClausePopUp('W','9','_9','txtchkICC_A_SEAWarranty_9');"
																											border='0' name="chkICC_A_SEAWarrenry"
																											alt="Add warranties ">&nbsp;&nbsp;&nbsp;&nbsp;Warranty</a>
																								</td>
																							</tr>
																							<tr>
																								<td>
																									<s:textfield name="txtchkICC_A_SEAWarCover_9"
																										id="txtchkICC_A_SEAWarCover_9" readonly="true"
																										cssClass="inputBox" size="25" />
																								</td>
																								<td>
																									<a><img src="images/list_icon.png"
																											style="cursor: hand" height="16"
																											src="images/list_ic.jpg"
																											onClick="javascript:OpenClausePopUp('R','9','_9','txtchkICC_A_SEAWarCover_9');"
																											border='0' name="txtchkICC_A_SEAWarCover"
																											alt="Add Clause ">&nbsp;&nbsp;&nbsp;&nbsp;War
																										Cover</a>
																								</td>
																							</tr>
																							<tr>
																								<td>
																									<s:radio name="radDetective_9"
																										list="#{'Y':'Yes','N':'No'}" />
																								</td>
																								<td>
																									<s:text name="commodity.Deductible" />
																								</td>
																							</tr>
																						</table>
																					</td>
																					<td>
																						&nbsp;
																					</td>
																				</tr>
																				<tr>
																					<td>
																						&nbsp;
																					</td>
																					<td>
																						<s:text name="ICC Air Cargo All Risks" />
																					</td>
																					<td>
																						<table width="100%">
																							<tr>
																								<td width="50%">
																									<s:textfield name="txtchkICC_A_SEAClause_10"
																										id="txtchkICC_A_SEAClause_10" readonly="true"
																										cssClass="inputBox" size="25" />
																								</td>
																								<td width="50%">
																									<a><img src="images/list_icon.png"
																											style="cursor: hand" height="16"
																											src="images/list_ic.jpg"
																											onClick="javascript:OpenClausePopUp('C','10','_10','txtchkICC_A_SEAClause_10');"
																											border='0' name="chkICC_A_SEAClause"
																											alt="Add Clause ">&nbsp;&nbsp;&nbsp;&nbsp;Clauses</a>
																								</td>
																							</tr>
																							<tr>
																								<td>
																									<s:textfield name="txtchkICC_A_SEAExclusive_10"
																										id="txtchkICC_A_SEAExclusive_10"
																										readonly="true" cssClass="inputBox" size="25" />
																								</td>
																								<td>
																									<a><img src="images/list_icon.png"
																											style="cursor: hand" height="16"
																											src="images/list_ic.jpg"
																											onClick="javascript:OpenClausePopUp('E','10','_10','txtchkICC_A_SEAExclusive_10');"
																											border='0' name="chkICC_A_SEAExplosion"
																											alt="Add Explosion ">&nbsp;&nbsp;&nbsp;&nbsp;Exclusion</a>
																								</td>
																							</tr>
																							<tr>
																								<td>
																									<s:textfield name="txtchkICC_A_SEAWarranty_10"
																										id="txtchkICC_A_SEAWarranty_10"
																										readonly="true" cssClass="inputBox" size="25" />
																								</td>
																								<td>
																									<a><img src="images/list_icon.png"
																											style="cursor: hand" height="16"
																											src="images/list_ic.jpg"
																											onClick="javascript:OpenClausePopUp('W','10','_10','txtchkICC_A_SEAWarranty_10');"
																											border='0' name="chkICC_A_SEAWarrenry"
																											alt="Add warranties ">&nbsp;&nbsp;&nbsp;&nbsp;Warranty</a>
																								</td>
																							</tr>
																							<tr>
																								<td>
																									<s:textfield name="txtchkICC_A_SEAWarCover_10"
																										id="txtchkICC_A_SEAWarCover_10"
																										readonly="true" cssClass="inputBox" size="25" />
																								</td>
																								<td>
																									<a><img src="images/list_icon.png"
																											style="cursor: hand" height="16"
																											src="images/list_ic.jpg"
																											onClick="javascript:OpenClausePopUp('R','10','_10','txtchkICC_A_SEAWarCover_10');"
																											border='0' name="txtchkICC_A_SEAWarCover"
																											alt="Add Clause ">&nbsp;&nbsp;&nbsp;&nbsp;War
																										Cover</a>
																								</td>
																							</tr>
																							<tr>
																								<td>
																									<s:radio name="radDetective_10"
																										list="#{'Y':'Yes','N':'No'}" />
																								</td>
																								<td>
																									<s:text name="commodity.Deductible" />
																								</td>
																							</tr>
																						</table>
																					</td>
																					<td>
																						&nbsp;
																					</td>

																				</tr>

																				<tr>
																					<td>
																						&nbsp;
																					</td>
																					<td>
																						<s:text name="all.risk.transit" />
																					</td>
																					<td>
																						<table width="100%">
																							<tr>
																								<td width="50%">
																									<s:textfield name="txtchkICC_A_SEAClause_11"
																										id="txtchkICC_A_SEAClause_11" readonly="true"
																										cssClass="inputBox" size="25" />
																								</td>
																								<td width="50%">
																									<a><img src="images/list_icon.png"
																											style="cursor: hand" height="16"
																											src="images/list_ic.jpg"
																											onClick="javascript:OpenClausePopUp('C','11','_11','txtchkICC_A_SEAClause_11');"
																											border='0' name="chkICC_A_SEAClause"
																											alt="Add Clause ">&nbsp;&nbsp;&nbsp;&nbsp;Clauses</a>
																								</td>
																							</tr>
																							<tr>
																								<td>
																									<s:textfield name="txtchkICC_A_SEAExclusive_11"
																										id="txtchkICC_A_SEAExclusive_11"
																										readonly="true" cssClass="inputBox" size="25" />
																								</td>
																								<td>
																									<a><img src="images/list_icon.png"
																											style="cursor: hand" height="16"
																											src="images/list_ic.jpg"
																											onClick="javascript:OpenClausePopUp('E','11','_11','txtchkICC_A_SEAExclusive_11');"
																											border='0' name="chkICC_A_SEAExplosion"
																											alt="Add Explosion ">&nbsp;&nbsp;&nbsp;&nbsp;Exclusion</a>
																								</td>
																							</tr>
																							<tr>
																								<td>
																									<s:textfield name="txtchkICC_A_SEAWarranty_11"
																										id="txtchkICC_A_SEAWarranty_11"
																										readonly="true" cssClass="inputBox" size="25" />
																								</td>
																								<td>
																									<a><img src="images/list_icon.png"
																											style="cursor: hand" height="16"
																											src="images/list_ic.jpg"
																											onClick="javascript:OpenClausePopUp('W','11','_11','txtchkICC_A_SEAWarranty_11');"
																											border='0' name="chkICC_A_SEAWarrenry"
																											alt="Add warranties ">&nbsp;&nbsp;&nbsp;&nbsp;Warranty</a>
																								</td>
																							</tr>
																							<tr>
																								<td>
																									<s:textfield name="txtchkICC_A_SEAWarCover_11"
																										id="txtchkICC_A_SEAWarCover_11"
																										readonly="true" cssClass="inputBox" size="25" />
																								</td>
																								<td>
																									<a><img src="images/list_icon.png"
																											style="cursor: hand" height="16"
																											src="images/list_ic.jpg"
																											onClick="javascript:OpenClausePopUp('R','11','_11','txtchkICC_A_SEAWarCover_11');"
																											border='0' name="txtchkICC_A_SEAWarCover"
																											alt="Add Clause ">&nbsp;&nbsp;&nbsp;&nbsp;War
																										Cover</a>
																								</td>
																							</tr>
																							<tr>
																								<td>
																									<s:radio name="radDetective_11"
																										list="#{'Y':'Yes','N':'No'}" />
																								</td>
																								<td>
																									<s:text name="commodity.Deductible" />
																								</td>
																							</tr>
																						</table>
																					</td>
																					<td>
																						&nbsp;
																					</td>

																				</tr>

																				<tr>
																					<td>
																						&nbsp;
																					</td>
																					<td>
																						<s:text name="land.transit" />
																					</td>
																					<td>
																						<table width="100%">
																							<tr>
																								<td width="50%">
																									<s:textfield name="txtchkICC_A_SEAClause_12"
																										id="txtchkICC_A_SEAClause_12" readonly="true"
																										cssClass="inputBox" size="25" />
																								</td>
																								<td width="50%">
																									<a><img src="images/list_icon.png"
																											style="cursor: hand" height="16"
																											src="images/list_ic.jpg"
																											onClick="javascript:OpenClausePopUp('C','12','_12','txtchkICC_A_SEAClause_12');"
																											border='0' name="chkICC_A_SEAClause"
																											alt="Add Clause ">&nbsp;&nbsp;&nbsp;&nbsp;Clauses</a>
																								</td>
																							</tr>
																							<tr>
																								<td>
																									<s:textfield name="txtchkICC_A_SEAExclusive_12"
																										id="txtchkICC_A_SEAExclusive_12"
																										readonly="true" cssClass="inputBox" size="25" />
																								</td>
																								<td>
																									<a><img src="images/list_icon.png"
																											style="cursor: hand" height="16"
																											src="images/list_ic.jpg"
																											onClick="javascript:OpenClausePopUp('E','12','_12','txtchkICC_A_SEAExclusive_12');"
																											border='0' name="chkICC_A_SEAExplosion"
																											alt="Add Explosion ">&nbsp;&nbsp;&nbsp;&nbsp;Exclusion</a>
																								</td>
																							</tr>
																							<tr>
																								<td>
																									<s:textfield name="txtchkICC_A_SEAWarranty_12"
																										id="txtchkICC_A_SEAWarranty_12"
																										readonly="true" cssClass="inputBox" size="25" />
																								</td>
																								<td>
																									<a><img src="images/list_icon.png"
																											style="cursor: hand" height="16"
																											src="images/list_ic.jpg"
																											onClick="javascript:OpenClausePopUp('W','12','_12','txtchkICC_A_SEAWarranty_12');"
																											border='0' name="chkICC_A_SEAWarrenry"
																											alt="Add warranties ">&nbsp;&nbsp;&nbsp;&nbsp;Warranty</a>
																								</td>
																							</tr>
																							<tr>
																								<td>
																									<s:textfield name="txtchkICC_A_SEAWarCover_12"
																										id="txtchkICC_A_SEAWarCover_12"
																										readonly="true" cssClass="inputBox" size="25" />
																								</td>
																								<td>
																									<a><img src="images/list_icon.png"
																											style="cursor: hand" height="16"
																											src="images/list_ic.jpg"
																											onClick="javascript:OpenClausePopUp('R','12','_12','txtchkICC_A_SEAWarCover_12');"
																											border='0' name="txtchkICC_A_SEAWarCover"
																											alt="Add Clause ">&nbsp;&nbsp;&nbsp;&nbsp;War
																										Cover</a>
																								</td>
																							</tr>
																							<tr>
																								<td>
																									<s:radio name="radDetective_12"
																										list="#{'Y':'Yes','N':'No'}" />
																								</td>
																								<td>
																									<s:text name="commodity.Deductible" />
																								</td>
																							</tr>
																						</table>
																					</td>
																					<td>
																						&nbsp;
																					</td>

																				</tr>
																				<tr>
																					<td>
																						&nbsp;
																					</td>
																					<td>
																						<s:text name="all.risk.parcel" />
																					</td>
																					<td>
																						<table width="100%">
																							<tr>
																								<td width="50%">
																									<s:textfield name="txtchkICC_A_SEAClause_13"
																										id="txtchkICC_A_SEAClause_13" readonly="true"
																										cssClass="inputBox" size="25" />
																								</td>
																								<td width="50%">
																									<a><img src="images/list_icon.png"
																											style="cursor: hand" height="16"
																											src="images/list_ic.jpg"
																											onClick="javascript:OpenClausePopUp('C','13','_13','txtchkICC_A_SEAClause_13');"
																											border='0' name="chkICC_A_SEAClause"
																											alt="Add Clause ">&nbsp;&nbsp;&nbsp;&nbsp;Clauses</a>
																								</td>
																							</tr>
																							<tr>
																								<td>
																									<s:textfield name="txtchkICC_A_SEAExclusive_13"
																										id="txtchkICC_A_SEAExclusive_13"
																										readonly="true" cssClass="inputBox" size="25" />
																								</td>
																								<td>
																									<a><img src="images/list_icon.png"
																											style="cursor: hand" height="16"
																											src="images/list_ic.jpg"
																											onClick="javascript:OpenClausePopUp('E','13','_13','txtchkICC_A_SEAExclusive_13');"
																											border='0' name="chkICC_A_SEAExplosion"
																											alt="Add Explosion ">&nbsp;&nbsp;&nbsp;&nbsp;Exclusion</a>
																								</td>
																							</tr>
																							<tr>
																								<td>
																									<s:textfield name="txtchkICC_A_SEAWarranty_13"
																										id="txtchkICC_A_SEAWarranty_13"
																										readonly="true" cssClass="inputBox" size="25" />
																								</td>
																								<td>
																									<a><img src="images/list_icon.png"
																											style="cursor: hand" height="16"
																											src="images/list_ic.jpg"
																											onClick="javascript:OpenClausePopUp('W','13','_13','txtchkICC_A_SEAWarranty_13');"
																											border='0' name="chkICC_A_SEAWarrenry"
																											alt="Add warranties ">&nbsp;&nbsp;&nbsp;&nbsp;Warranty</a>
																								</td>
																							</tr>
																							<tr>
																								<td>
																									<s:textfield name="txtchkICC_A_SEAWarCover_13"
																										id="txtchkICC_A_SEAWarCover_13"
																										readonly="true" cssClass="inputBox" size="25" />
																								</td>
																								<td>
																									<a><img src="images/list_icon.png"
																											style="cursor: hand" height="16"
																											src="images/list_ic.jpg"
																											onClick="javascript:OpenClausePopUp('R','13','_13','txtchkICC_A_SEAWarCover_13');"
																											border='0' name="txtchkICC_A_SEAWarCover"
																											alt="Add Clause ">&nbsp;&nbsp;&nbsp;&nbsp;War
																										Cover</a>
																								</td>
																							</tr>
																							<tr>
																								<td>
																									<s:radio name="radDetective_13"
																										list="#{'Y':'Yes','N':'No'}" />
																								</td>
																								<td>
																									<s:text name="commodity.Deductible" />
																								</td>
																							</tr>
																						</table>
																					</td>
																					<td>
																						&nbsp;
																					</td>

																				</tr>
																				<tr>
																					<td>
																						&nbsp;
																					</td>
																					<td>
																						<s:text name="all.risk.sea.air" />
																					</td>
																					<td>
																						<table width="100%">
																							<tr>
																								<td width="50%">
																									<s:textfield name="txtchkICC_A_SEAClause_14"
																										id="txtchkICC_A_SEAClause_14" readonly="true"
																										cssClass="inputBox" size="25" />
																								</td>
																								<td width="50%">
																									<a><img src="images/list_icon.png"
																											style="cursor: hand" height="16"
																											src="images/list_ic.jpg"
																											onClick="javascript:OpenClausePopUp('C','14','_14','txtchkICC_A_SEAClause_14');"
																											border='0' name="chkICC_A_SEAClause"
																											alt="Add Clause ">&nbsp;&nbsp;&nbsp;&nbsp;Clauses</a>
																								</td>
																							</tr>
																							<tr>
																								<td>
																									<s:textfield name="txtchkICC_A_SEAExclusive_14"
																										id="txtchkICC_A_SEAExclusive_14"
																										readonly="true" cssClass="inputBox" size="25" />
																								</td>
																								<td>
																									<a><img src="images/list_icon.png"
																											style="cursor: hand" height="16"
																											src="images/list_ic.jpg"
																											onClick="javascript:OpenClausePopUp('E','14','_14','txtchkICC_A_SEAExclusive_14');"
																											border='0' name="chkICC_A_SEAExplosion"
																											alt="Add Explosion ">&nbsp;&nbsp;&nbsp;&nbsp;Exclusion</a>
																								</td>
																							</tr>
																							<tr>
																								<td>
																									<s:textfield name="txtchkICC_A_SEAWarranty_14"
																										id="txtchkICC_A_SEAWarranty_14"
																										readonly="true" cssClass="inputBox" size="25" />
																								</td>
																								<td>
																									<a><img src="images/list_icon.png"
																											style="cursor: hand" height="16"
																											src="images/list_ic.jpg"
																											onClick="javascript:OpenClausePopUp('W','14','_14','txtchkICC_A_SEAWarranty_14');"
																											border='0' name="chkICC_A_SEAWarrenry"
																											alt="Add warranties ">&nbsp;&nbsp;&nbsp;&nbsp;Warranty</a>
																								</td>
																							</tr>
																							<tr>
																								<td>
																									<s:textfield name="txtchkICC_A_SEAWarCover_14"
																										id="txtchkICC_A_SEAWarCover_14"
																										readonly="true" cssClass="inputBox" size="25" />
																								</td>
																								<td>
																									<a><img src="images/list_icon.png"
																											style="cursor: hand" height="16"
																											src="images/list_icon.jpg"
																											onClick="javascript:OpenClausePopUp('R','14','_14','txtchkICC_A_SEAWarCover_14');"
																											border='0' name="txtchkICC_A_SEAWarCover"
																											alt="Add Clause ">&nbsp;&nbsp;&nbsp;&nbsp;War
																										Cover</a>
																								</td>
																							</tr>
																							<tr>
																								<td>
																									<s:radio name="radDetective_14"
																										list="#{'Y':'Yes','N':'No'}" />
																								</td>
																								<td>
																									<s:text name="commodity.Deductible" />
																								</td>
																							</tr>
																						</table>
																					</td>
																					<td>
																						&nbsp;
																					</td>

																				</tr>


																				<tr>
																					<td>
																						&nbsp;
																					</td>
																					<td>
																						<s:text name="coverage.referral" />
																					</td>
																					<td>
																						<div class="inputAppend">
																							<s:textfield name="coverage_Referal"
																								id="coverage_Referal" readonly="true"
																								cssClass="inputBox1"
																								cssStyle="border: none;background: transparent; width: 90%;"
																								size="35" />
																							&nbsp;
																							<a><img src="images/list_icon.png"
																									style="cursor: hand" height="16"
																									src="images/list_ic.jpg"
																									onClick="javascript:openSubTradeView('','cov','coverage_Referal');"
																									border='0' name="EXCLUSION_ID"
																									alt="Add Exclusions ">
																							</a>
																						</div>
																					</td>
																					<td>
																						&nbsp;
																					</td>

																				</tr>
																				<tr>
																					<td>
																						&nbsp;
																					</td>
																					<td>
																						<s:text name="rating.core.code" />
																						<font color="red">*</font>
																					</td>
																					<td>
																						<s:textfield name="code" id="code"
																							cssClass="inputBox" maxlength="10"
																							onkeypress="return checkAlphaNumeric(event)" />
																					</td>
																					<td>
																						&nbsp;
																					</td>

																				</tr>
																				<tr>
																					<td>
																						&nbsp;
																					</td>
																					<td>
																						<s:text name="rating.date" />
																						<font color="red">*</font>
																					</td>
																					<td>
																						<div class="inputAppend">
																							<sj:datepicker name="eff_date" id="eff_date"
																								cssClass="inputBox1" displayFormat="mm/dd/yy"
																								changeMonth="true" changeYear="true"
																								minDate="+0D" maxDate="+1Y" readonly="true"
																								cssStyle="width:85%;border: none;" />
																						</div>
																					</td>
																					<td>
																						&nbsp;
																					</td>
																				</tr>
																				<tr>
																					<td>
																						&nbsp;
																					</td>
																					<td>
																						<s:text name="rating.remark" />
																					</td>
																					<td>
																						<s:textfield name="remarks" id="remarks"
																							cssClass="inputBox" maxlength="100"/>
																					</td>
																					<td>
																						&nbsp;
																					</td>

																				</tr>
																				<tr>
																					<td>
																						&nbsp;
																					</td>
																					<td>
																						<s:text name="rating.status" />
																					</td>
																					<td>
																						<s:radio name="status"
																							list="#{'Y':'Active','N':'DeActive','R':'Referral'}"
																							value='%{status==null?"N":status}' />
																					</td>
																					<td>
																						&nbsp;
																					</td>

																				</tr>

																				<tr>

																					<td colspan="4" align="center">
																						<input type="button" class="btn" value="Back"
																							onclick="fnCall('commoditymaster')" />
																						&nbsp;&nbsp;
																						<s:submit value="Save" cssClass="btn" />
																					</td>


																				</tr>


																			</table>
																		</td>
																	</tr>
																</table>
															</td>
														</tr>
													</table>
											</tr>
										</table>
										<s:hidden name="agencyCode" />
										<s:hidden name="login_Id" />
										<s:hidden name="borganization" />
										<s:hidden name="bcode" />
										<s:hidden name="mode" id="mode" />
										<s:hidden name="prevdate" />
									</td>
								</tr>
							</table>
						</s:form>
					</div>
				</div>
			</div>
		</div>

	</body>
	<script>
function openAmendedData()
{
var cid=document.getElementById("commodityID").value;
	var URL = "pages/admin/ratingEngine/amendedData.jsp?cid="+cid;
	var windowName = "GroupView";
	var width  = screen.availWidth;
	var height = screen.availHeight;
	var w = 750;
	var h = 460;
	var features =
		'width='          + w +
		',height='		  + h +
		',left='		  + ((0) * .5)  +
		',top='			  + ((0) * .5) +
		',directories=no' +
		',location=no'	  +
		',menubar=no'	  +
		',scrollbars=yes' +
		',status=no'	  +
		',toolbar=no'	  +
		',resizable=no';
	var strOpen = window.open (URL, windowName, features);
	strOpen.focus();
}

function openSubTradeView(warrenty,from,val) 
{
var val="";
val=document.getElementById("coverage_Referal").value;

	var URL = "pages/admin/ratingEngine/warantyPopUp.jsp?ida="+warrenty+"&from="+from+"&val="+val;
	var windowName = "GroupView";
	var width  = screen.availWidth;
	var height = screen.availHeight;
	var w = 750;
	var h = 460;
	var features =
		'width='          + w +
		',height='		  + h +
		',left='		  + ((0) * .5)  +
		',top='			  + ((0) * .5) +
		',directories=no' +
		',location=no'	  +
		',menubar=no'	  +
		',scrollbars=yes' +
		',status=no'	  +
		',toolbar=no'	  +
		',resizable=no';
	var strOpen = window.open (URL, windowName, features);
	strOpen.focus();
	return false;
}
var cal1 = new calendar1(document.forms['form1'].elements['startDate']);
	cal1.year_scroll = true;
	cal1.time_comp = false;

function OpenClausePopUp(clause,value,id,name) 
{
var val="";
if(clause=="C")
{
  val=document.getElementById("txtchkICC_A_SEAClause"+id).value;
   }
   else if(clause=="E")
   {
   val=document.getElementById("txtchkICC_A_SEAExclusive"+id).value;
   }
   else if(clause=="W")
   {
    val=document.getElementById("txtchkICC_A_SEAWarranty"+id).value;
   }
    else if(clause=="R")
   {
    val=document.getElementById("txtchkICC_A_SEAWarCover"+id).value;
   }
	var URL = "pages/admin/ratingEngine/ClausePopUp.jsp?ida="+clause+"&from="+value+"&id="+id+"&val="+val+"&name="+name;
	var windowName = "GroupView";
	var width  = screen.availWidth;
	var height = screen.availHeight;
	var w = 750;
	var h = 460;
	var features =
		'width='          + w +
		',height='		  + h +
		',left='		  + ((0) * .5)  +
		',top='			  + ((0) * .5) +
		',directories=no' +
		',location=no'	  +
		',menubar=no'	  +
		',scrollbars=yes' +
		',status=no'	  +
		',toolbar=no'	  +
		',resizable=no';
	var strOpen = window.open (URL, windowName, features);
	strOpen.focus();
	return false;

}
</script>

</html>

