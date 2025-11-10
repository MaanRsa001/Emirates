<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<html>
	<head>
		<script>
			function stopRKey(evt) { 
			  var evt = (evt) ? evt : ((event) ? event : null); 
			  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
			  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
			} 
			document.onkeypress = stopRKey;
			
			function fnCall(from){
				if(from=='edit')
					document.info.action = from+"BrokerMgm.action?mode=edit";
				else if(from=='userDetail')
					document.info.action = "getABListUserMgm.action?mode1=broker";
				else if(from=='edituser')
					document.info.action = "editUserMgm.action?mode1=broker&mode=edit";
				else if(from=='customerDetail')
					document.info.action = "getABListCustomerMgm.action?mode1=broker";
				else if(from=='referal')
					document.info.action = "getOCListReferal.action";
				else if(from=='openCover')
					document.info.action = "opencoverOC.action";
				else
					document.info.action = from+"BrokerMgm.action";
				document.info.submit();
			}
			
			function fnBack(){
				document.info.action ='UserCreationContoller.action';
				document.info.submit();
			}
			
			function fnBackBroker(){
				document.info.action ='BrokerDisplayContoller.action';
				document.info.submit();
			}
			
		</script>
		<style type="text/css">
			.viewUserInfo td{
			padding: 10px;
		}
		
		</style>
	</head>
	<body>
    <div style="margin:10px 0;"></div>
    <div class="easyui-layout" style="height:100vh;">
	      	<div data-options="region:'west',split:true" title="Options" style="width:150px;">
    			<s:if test='%{borganization!=null && !"".equals(borganization)}'>
		            <div class="easyui-accordion" data-options="fit:true,border:false">
		                <input type="button" class="btntab" value="Edit" onclick="fnCall('edituser')"/><br/>
		                <input type="button" class="btntab" value="Change Password" onclick="fnCall('changePwd')"/><br/>
		                <input type="button" class="btntab" value="User Details" onclick="fnCall('userDetail')"/><br/>
		                <input type="button" class="btntab" value="Customer Details" onclick="fnCall('customerDetail')"/><br/>
		                <input type="button" class="btntab" value="OpenCover" onclick="fnCall('openCover')"/><br/>
		                <input type="button" class="btntab" value="Referral" onclick="fnCall('referal')"/><br/>
		                <input type="button" class="btntab" value="Statistics" onclick="fnCall('statistics')"/><br/>
		            </div>
		         </s:if>
		         <s:else>
		         	 <input type="button" class="btntab" value="Edit" onclick="fnCall('edituser')"/><br/>
		         	 <input type="button" class="btntab" value="Customer Details" onclick="fnCall('customerDetail')"/><br/>
		         </s:else>
	        </div>
	       <s:if test='%{borganization!=null && borganization!=""}'>
        		<div data-options="region:'center',title:'<s:property value="borganization"/>(<s:property value="agencyCode"/>)',iconCls:'icon-ok'">
        	</s:if>
		    <s:else>
		    	<div data-options="region:'center',title:'User Management',iconCls:'icon-ok'">
		    </s:else>
            <div class="easyui-tabs" data-options="fit:true,border:false,plain:true" id="mainTab">
                <div title="User Info" style="padding:5px">
                	<table width="100%">
                          <tr>
						    <td height="5"></td>
						  </tr>
						  <tr>
						    <td>
						        <s:form id="info" name="info" method="post" action="" theme="simple">
						        <table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#E5E5E5">
						      <tr>
						        <td bgcolor="#FFFFFF" style="padding:10px; background:#F8F8F8">
						            <table width="100%" border="0" cellspacing="0" cellpadding="0">
						            	<tr>
							       	   		<td  style="color:red;"><s:actionerror/></td>
							       		</tr>
										<tr> <td>&nbsp;&nbsp;</td> </tr>
										<tr><td class="heading"><s:text name="user.usermanagement"/></td></tr>
										<tr><td>&nbsp;</td></tr>
										<tr><td style="color:red;"><s:actionerror/> <s:actionmessage/></td></tr>
										
										<tr align="center" class="viewUserInfo">
												 <td bgcolor="#FFFFFF"><table width="100%" border="0" cellspacing="0" cellpadding="0">
														<tr>
															<td class="heading" colspan="5" align="left"><s:text name="user.contactpersonInfo"/>
															</td>
														</tr>
														<tr>
														  <td class="bg"><table width="100%" border="0" align="center" cellpadding="4" cellspacing="0">
															<tr>
																<td width="5%">&nbsp;</td>
																<td width="35%" align="left">
																	<b><s:text name="user.broker"/> </b><br/>
																	<s:property value="brokerName"/>
																</td>
																<td width="35%" align="left">
																	<b><s:text name="user.type"/></b><br/>
																	<s:property value="utype"/>
																</td>
																<td width="30%" align="left">
																	<b><s:text name="user.userId"/></b><br/>
																	<s:property value="userId"/>
																</td>
																<td width="5%">&nbsp;</td>
															</tr>
															<tr>
																<td width="5%">&nbsp;</td>
																<td align="left">
																	<b><s:text name="user.name"/></b><br/>
																	<s:property value="uname"/>
																</td>
																<td width="35%" align="left">
																	<b><s:text name="user.gender"/></b><br/>
																		<s:property value='%{ugender=="M"?"Male":"Female"}'/>
																</td>
																<td align="left">
																	<b><s:text name="user.dob"/></b><br/>
																		<s:property value="udob"/>
																</td>
																<td width="5%">&nbsp;</td>
															</tr>
															<tr>
																<td width="5%">&nbsp;</td>
																<td align="left">
																	<b><s:text name="user.occupation"/></b><br/>
																	<s:property value="uoccupation"/>
																</td>
																<td align="left">
																	<b><s:text name="user.address1"/></b><br/>
																	<s:property value="uaddress1"/>
																</td>
																<td align="left">
																	<b><s:text name="user.address2"/></b><br/>
																	<s:property value="uaddress2"/>
																</td>
																<td width="5%">&nbsp;</td>
															</tr>
															<tr>
																<td width="5%">&nbsp;</td>
																<td align="left">
																	<b><s:text name="user.city"/></b><br/>
																	<s:property value="ucity"/>
																</td>
																<td align="left">
																	<b><s:text name="user.country"/></b><br/>
																	<s:property value="ucountryNa"/>
																</td>
																<td align="left">
																	<b><s:text name="user.pobox"/></b><br/>
																	<s:property value="upobox"/>
																</td>
																<td width="5%">&nbsp;</td>
															</tr>
															<tr>
																<td width="5%">&nbsp;</td>
																<td align="left">
																	<b><s:text name="user.telephone"/></b><br/>
																	<s:property value="uphone"/>
																</td>
																<td align="left">
																	<b><s:text name="user.mobile"/></b><br/>
																	<s:property value="umobile"/>
																</td>
																<td align="left">
																	<b><s:text name="user.email"/></b><br/>
																	<s:property value="uemail"/>
																</td>
																<td width="5%">&nbsp;</td>
															</tr>
															<tr>
																<td width="5%">&nbsp;</td>
																<td align="left">
																	<b><s:text name="user.fax"/></b><br/>
																	<s:property value="ufax"/>
																</td>
																<td align="left">
																	<b><s:text name="user.nationality"/></b><br/>
																	<s:property value="unationalityName"/>
																</td>
																<td align="left">
																	<b><s:text name="user.loginid"/></b><br/>
																	<s:property value="ulogin_Id"/>
																</td>
																<td width="5%">&nbsp;</td>
															</tr>
														 </table></td>
														 </tr>
														<%--<tr>
															<td width="100%" class="heading" colspan="5" align="left">
																<b><font size="2"><s:text name="user.productinfo"/></font> </b>
															</td>
														</tr>
														<tr>
															<td class="bg"><table width="100%" border="0" align="center" cellpadding="4" cellspacing="0">
															<tr>
																<td width="5%">&nbsp;</td>
																<td colspan="3">
																	<display:table name="commisionDetails" pagesize="10" requestURI="" class="table" uid="row" id="record">
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
																			<display:column sortable="true" style="text-align:left;" title="Discount % Max"property="DISCOUNT_PREMIUM" />
																	</display:table>
																</td>
																<td width="5%">&nbsp;</td>
															</tr>
														--%></table>
													  </td>
													</tr>
													<tr>
														<td>
															<table width="100%" border="0" cellspacing="0" cellpadding="0">
																<tr>
																	<td height="25" align="center" valign="middle">
																		<s:if test='%{borganization!=null && !"".equals(borganization)}'>
<%-- 																			<s:submit action="getABListBrokerMgm" name="submit1" cssClass="btn" value=" Back " /> --%>
																			<input type="button" onclick="fnBackBroker();" class="btn" value="Back">
																		</s:if>
																		<s:else>
<%-- 																			<s:submit action="getABListUserMgm" name="submit1" cssClass="btn" value=" Back " /> --%>
																			<input type="button" onclick="fnBack();" class="btn" value="Back">
																		</s:else>
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
    								<s:hidden name="uagencyCode"/>
									<s:hidden name="login_Id"/>
									<s:hidden name="borganization"/>
								</s:form>
							</td>
						</tr>
					</table>
                </div>
            </div>
        </div>
    </div>
</body>
</html>

