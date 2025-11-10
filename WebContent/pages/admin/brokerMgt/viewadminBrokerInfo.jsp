<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="/struts-jquery-tags" prefix="sj"%>
<html>
	<head>
		<script>
			function fnCall(from){
				if(from=='edit')
					document.info.action = from+"BrokerMgm.action?mode=edit";
				else if(from=='userDetail')
					document.info.action = "getABListUserMgm.action?mode1=broker";
				else if(from=='customerDetail')
					document.info.action = "getABListCustomerMgm.action?mode1=broker";
				else if(from=='referal')
					document.info.action = "getOCListReferal.action";
				else if(from=='openCover')
					document.info.action = "opencoverOC.action";
				else if(from=='statistics')
					document.info.action = "statisticsRE.action";
				<%--alert(from);
					postRequest('${pageContext.request.contextPath}/getABListCustomerMgm.action?agencyCode=10016&borganization='+'<s:property value="borganization"/>'+'&bcode='+'<s:hidden name="bcode"/>'+'&mode=mainTab', 'mainTab');
				}--%>else
					document.info.action = from+"BrokerMgm.action";
				document.info.submit();
			}
			
			function stopRKey(evt) { 
			  var evt = (evt) ? evt : ((event) ? event : null); 
			  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
			  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
			} 
			document.onkeypress = stopRKey;
		</script>
		<style >
		.heading{
			padding: 10px;
		}
		
		.viewBrokerInfo td{
			padding: 10px;
		}
		
		.brokerTable th, .brokerTable td{
 			border: 1px solid #ccc;
		}
		</style>
	</head>
	<body>
    <div style="margin:10px 0;"></div>
    <div class="easyui-layout" style="height:100vh;">
       <div data-options="region:'west',split:true" title="Options" style="width:150px;">
            <div class="easyui-accordion" data-options="fit:true,border:false">
                <input type="button" class="btntab" value="Edit" onclick="fnCall('edit')"/><br/>
                <input type="button" class="btntab" value="Change Password" onclick="fnCall('changePwd')"/><br/>
                <input type="button" class="btntab" value="User Details" onclick="fnCall('userDetail')"/><br/>
                <%--
                <input type="button" class="btntab" value="Customer Details" onclick="fnCall('customerDetail')"/><br/>
                --%>
                <input type="button" class="btntab" value="OpenCover" onclick="fnCall('openCover')"/><br/>
                <input type="button" class="btntab" value="Referral" onclick="fnCall('referal')"/><br/>
                <%--
                <input type="button" class="btntab" value="Statistics" onclick="fnCall('statistics')"/><br/>
                --%>
            </div>
        </div>
        <div data-options="region:'center',title:'<s:property value="borganization"/>(<s:property value="agencyCode"/>)',iconCls:'icon-ok'">
            <div class="easyui-tabs" data-options="fit:true,border:false,plain:true" id="mainTab">
                <div title="Broker Info" style="padding:5px">
                	<table width="100%">
                          <tr>
						    <td height="5"></td>
						  </tr>
						  <tr>
						    <td><table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#E5E5E5">
						      <tr>
						        <td bgcolor="#FFFFFF" style="padding:10px; background:#F8F8F8">
						        <s:form id="info" name="info" method="post" action="" theme="simple">
						            <table width="100%" border="0" cellspacing="0" cellpadding="0">
						            	<tr>
							       	   		<td  style="color:red;"><s:actionerror/></td>
							       		</tr>
										<tr> <td>&nbsp;&nbsp;</td> </tr>
										<tr><td class="heading"><s:text name="broker.brokermanagement"/></td></tr>
										<tr><td>&nbsp;</td></tr>
										<tr align="center">
												 <td bgcolor="#FFFFFF"><table width="100%" border="0" cellspacing="0" cellpadding="0">
														<tr>
															<td class="heading" colspan="5" align="left"><s:text name="broker.companyinfo"/>
															</td>
														</tr>
														<tr class="viewBrokerInfo">
														  <td class="bg"><table width="100%" border="0" align="center" cellpadding="4" cellspacing="0">
															<tr>
																<td width="5%">&nbsp;</td>
																<td width="35%" align="left">
																	<b><s:text name="broker.branch"/> </b><br/>
																	<s:property value="%{branchData[0].BRANCH_NAME}"/>
																</td>
																<td width="35%" align="left">
																	<b><s:text name="broker.mississ"/></b><br/>
																	<s:property value="missippiId"/>
																</td>
																<td width="30%" align="left">
																	<b><s:text name="broker.brokercode"/></b><br/>
																	<s:property value="bcode"/>
																</td>
																<td width="5%">&nbsp;</td>
															</tr>
															<tr>
																<td width="5%">&nbsp;</td>
																<td align="left">
																	<b><s:text name="broker.brokerOrg"/></b><br/>
																	<s:property value="companyName"/>
																</td>
																<td align="left">
																	<b><s:text name="broker.address1"/></b><br/>
																	<s:property value="address1"/>
																</td>
																<td align="left">
																	<b><s:text name="broker.address2"/></b><br/>
																	<s:property value="address2"/>
																</td>
																<td width="5%">&nbsp;</td>
															</tr>
															<tr>
																<td width="5%">&nbsp;</td>
																<td align="left">
																	<b><s:text name="broker.city"/></b><br/>
																	<s:property value="emirate"/>
																</td>
																<td align="left">
																	<b><s:text name="broker.country"/></b><br/>
																	<s:property value="countryName"/>
																</td>
																<td align="left">
																	<b><s:text name="broker.pobox"/></b><br/>
																	<s:property value="pobox"/>
																</td>
																<td width="5%">&nbsp;</td>
															</tr>
															<tr>
																<td width="5%">&nbsp;</td>
																<td align="left">
																	<b><s:text name="broker.telephone"/></b><br/>
																	<s:property value="telephone"/>
																</td>
																<td align="left">
																	<b><s:text name="broker.fax"/></b><br/>
																	<s:property value="fax"/>
																</td>
																<td>
																	<b><s:text name="broker.images" /></b> <br/>
																	<s:if test='broImgName!=null'>
																		<img src='${pageContext.request.contextPath}<s:property value="broImgName"/>' border="0" width="150" height="60"/><br>
																	</s:if>
			                         							</td> 
			                         							
																<td colspan="2" width="5%">&nbsp;</td>
															</tr>
														 </table></td>
														 </tr>
														<tr>
															<td width="100%" class="heading" colspan="5" align="left">
																<b><font size="2"><s:text name="broker.contactpersonInfo"/></font> </b>
															</td>
														</tr>
														<tr class="viewBrokerInfo">
														   <td class="bg"><table width="100%" border="0" align="center" cellpadding="4" cellspacing="0">
																<tr>
																	<td width="5%">&nbsp;</td>
																	<td width="35%" align="left">
																		<b><s:text name="broker.name"/></b><br/>
																		<s:property value="firstname"/><s:property value="lastname"/>
																	</td>
																	<td width="35%" align="left">
																		<b><s:text name="broker.gender"/></b><br/>
																		<s:property value='%{gender=="M"?"Male":"Female"}'/>
																	</td>
																	<td align="left">
																		<b><s:text name="broker.dob"/></b><br/>
																		<s:property value="dob"/>
																	</td>
																	<td width="5%">&nbsp;</td>
																</tr>
																<tr>
																	<td width="5%">&nbsp;</td>
																	<td align="left">
																		<b><s:text name="broker.occupation"/></b><br/>
																		<s:property value="occupation"/>
																	</td>
																	<td align="left">
																		<b><s:text name="broker.mobile"/></b><br/>
																		<s:property value="mobile"/>
																	</td>
																	<td align="left">
																		<b><s:text name="broker.email"/></b><br/>
																		<s:property value="bemail"/>
																	</td>
																	<td width="5%">&nbsp;</td>
																</tr>
																<tr>
																	<td width="5%">&nbsp;</td>
																	<td width="30%" align="left">
																		<b><s:text name="broker.nationality"/></b><br/>
																		<s:property value="nationalityNa"/>
																	</td>
																	<td align="left">
																		<b><s:text name="broker.loginid"/></b><br/>
																		<s:property value="login_Id"/>
																    </td>
																	<td colspan="2">&nbsp;</td>
																</tr>
															</table></td>
														</tr>
													<!-- 	<tr>
															<td class="heading" colspan="5" align="left"><s:text name="broker.status"/>
															</td>
														</tr>
														<tr>
														  <td class="bg"><table width="100%" border="0" align="center" cellpadding="4" cellspacing="0">
															<tr>
																<td width="5%">&nbsp;</td>
																<td colspan="3" align="center">
																	<s:if test='"Y".equals(status)'> <s:label key="status.active"/></s:if>
																	<s:elseif test='"N".equals(status)'> <s:label key="status.deactive"/></s:elseif>
																	<s:elseif test='"D".equals(status)'> <s:label key="status.delete"/></s:elseif>
																	<s:elseif test='"L".equals(status)'> <s:label key="status.locked"/></s:elseif>
																</td>
																<td width="5%">&nbsp;</td>
															</tr>
															
															</table></td>
														</tr> -->
														<tr>
															<td width="100%" class="heading" colspan="5" align="left">
																<b><font size="2"><s:text name="broker.productinfo"/></font> </b>
															</td>
														</tr>
														<tr class="viewBrokerInfo">
															<td class="bg"><table width="100%" border="0" align="center" cellpadding="4" cellspacing="0">
															<tr>
																<td width="5%">&nbsp;</td>
																<td colspan="3">
																	<display:table  name="commisionDetails"  pagesize="10" requestURI="" class="table brokerTable" uid="row" id="record">
																			<display:setProperty name="paging.banner.one_item_found" value="" />
																			<display:setProperty name="paging.banner.one_items_found" value="" />
																			<display:setProperty name="paging.banner.all_items_found" value="" />
																			<display:setProperty name="paging.banner.some_items_found" value="" />
																			<display:setProperty name="paging.banner.placement"	value="bottom" />
																			<display:setProperty name="paging.banner.onepage" value="" />
																			<display:column sortable="true" style="text-align:left;" title="Products" property="product_name"/>
																			<display:column sortable="true" style="text-align:left;" title="Sum Insured" property="INSURANCE_END_LIMIT"/>
																			<display:column sortable="true" style="text-align:left;"  title="Commission [%]" property="COMMISSION" />
																			<display:column sortable="true" style="text-align:left;" title="Min Premium" property="MIN_PREMIUM_AMOUNT" />
																			<display:column sortable="true" style="text-align:left;" title="Loading % Max" property="LOADING_PREMIUM" />
																			<display:column sortable="true" style="text-align:left;" title="Discount % Max" property="DISCOUNT_PREMIUM" />
																	</display:table>
																</td>
																<td width="5%">&nbsp;</td>
															</tr>
														</table>
													  </td>
													</tr>
													<tr>
														<td>
															<table width="100%" border="0" cellspacing="0" cellpadding="0">
																<tr>
																	<td height="25" align="center" valign="middle">
																		<s:submit action="getABListBrokerMgm" name="submit1" cssClass="btn" value="Back " />
																	</td>
																</tr>
															</table>
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
    								<s:hidden name="agencyCode"/>
									<s:hidden name="login_Id"/>
									<s:hidden name="borganization"/>
									<s:hidden name="bcode"/>
								</s:form>
								</td>
								</tr>
								</table></td></tr>
                    </table>
                </div>
            </div>
        </div>
    </div>
</body>
</html>

