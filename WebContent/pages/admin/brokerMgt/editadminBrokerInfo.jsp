<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="/struts-jquery-tags" prefix="sj"%>
<html>
	<head>
		<sj:head jqueryui="true" jquerytheme="start" />
		<link href="<%=request.getContextPath()%>/cssbootstrap/footable-0.1.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
			function stopRKey(evt) { 
			  var evt = (evt) ? evt : ((event) ? event : null); 
			  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
			 if ((evt.keyCode == 13) && ((node.type=="text") || (node.type=="password"))) {return false;} 
			} 
			document.onkeypress = stopRKey;
		
			$.fn.datebox.defaults.formatter = function(date){
				var y = date.getFullYear();
				var m = date.getMonth()+1;
				var d = date.getDate();
				return d+'/'+m+'/'+y;
			}
			
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
				else
					document.info.action = from+"BrokerMgm.action";
				document.info.submit();
			}
			function fnsubmit(from){
				if(from=='info'){
					document.BrokerInfoEdit.action = "newBrokerBrokerMgm.action";
					document.BrokerInfoEdit.submit();
				}else if(from=='back'){
					document.BrokerInfoEdit.action = "getABListBrokerMgm.action";
					document.BrokerInfoEdit.submit();
				}
			}
			<%-- $(function() {
        $( "#tabs" ).tabs();
    });
			$(function(){
				$('#tabs').tabs("option", "active", index);
			});
			
			$('#tabs').tabs("option", "active", index);
			
				$('#tabs').tabs({  
				    border:false,  
				    onSelect:function(title){  
				        alert(title+' is selected');  
				    } 
				});
				
				function updateTabs() {
				         var tab = $('#tabs').tabs('getTab',1);
				        $('#tabs').tabs('update', {
				            tab: tab,
				            options: {
				                title: 'Product Selection1'
				            }
				        });
				    }
				    
				    $(function () {
				    $('#tabs').tabs();
				});
				
		$(function(){
			var index = 0;
			var t = $('#tabs');
			var tabs = t.tabs('tabs');
			setInterval(function(){
				t.tabs('select', tabs[index].panel('options').title);
				index++;
				if (index >= tabs.length){
					index = 0;
				}
			}, 3000);
		});--%>
						
		$(function(){
			var index = '<s:property value="index"/>';
			var t = $('#tabs');
			var tabs = t.tabs('tabs');
				t.tabs('select', tabs[index].panel('options').title);
		});
		
  		</script>
  		<style type="text/css">
  		th.sortable a {
			background-color: #337ab7;
		}
  		</style>
	</head>
	<body>
    <div style="margin:10px 0;"></div>
    <div class="easyui-layout" style="height:100vh;">
	     <s:if test='!"new".equals(mode)'>
	       <div data-options="region:'west',split:true" title="Options" style="width:150px;">
	            <div class="easyui-accordion" data-options="fit:true,border:false">
	                <input type="button" class="btntab" value="View" onclick="fnCall('view');"/><br/>
	                <input type="button" class="btntab" value="Change Password" onclick="fnCall('changePwd')"/><br/>
	                <input type="button" class="btntab" value="User Details" onclick="fnCall('userDetail')"/><br/>
	                <input type="button" class="btntab" value="Customer Detail" onclick="fnCall('customerDetail')"/><br/>
	                <input type="button" class="btntab" value="OpenCover" onclick="fnCall('openCover')"/><br/>
	                <input type="button" class="btntab" value="Referral" onclick="fnCall('referal')"/><br/>
	            </div>
	        </div>
	    </s:if>
        <s:if test='"new".equals(mode)'>
        	 <div data-options="region:'center',title:'New Broker Creation',iconCls:'icon-ok'">
        	 	 <div class="easyui-tabs" data-options="fit:true,border:false,plain:true" id="tabs">
                	<div title="Broker Info" style="padding:10px" >
        </s:if>
        <s:else>
        	 <div data-options="region:'center',title:'<s:property value="borganization"/>(<s:property value="agencyCode"/>)',iconCls:'icon-ok'">
	       		<div class="easyui-tabs" data-options="fit:true,border:false,plain:true" id="tabs">
	                <div title="Edit Broker Info" style="padding:10px" >
        </s:else>
                	<table width="100%">
                          <tr>
						    <td height="5"></td>
						  </tr>
						  <tr>
						    <td><table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#E5E5E5">
						      <tr>
						        <td bgcolor="#FFFFFF" style="padding:10px; background:#F8F8F8">
			   					 <s:form id="BrokerInfoEdit" name="BrokerInfoEdit" method="post" action="" theme="simple" enctype="multipart/form-data">
			       					 <table width="100%" border="0" cellspacing="0" cellpadding="0">
			       					 <s:if test="'successNew'.equals(display)">
										<tr>
				   							<td bgcolor="#FFFFFF" style="padding:10px; background:#F8F8F8">
				   								<s:label key="broker.new.success"/>
											</td>
										</tr>
										<tr>
											<td colspan="3">
												<table width="100%" border="0" cellspacing="0" cellpadding="0">
													<tr>
														<td height="25" align="center" valign="middle">
															<input type="button" onclick="fnsubmit('back')" name="back" class="btn" value="Back" />
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</s:if>
									<s:elseif test="'successUpdate'.equals(display)">
										<tr>
				   							<td bgcolor="#FFFFFF" style="padding:10px; background:#F8F8F8">
				   								<s:label key="broker.update.success"/>
											</td>
										</tr>
										<tr>
											<td colspan="3">
												<table width="100%" border="0" cellspacing="0" cellpadding="0">
													<tr>
														<td height="25" align="center" valign="middle">
															<input type="button" onclick="fnsubmit('back')" name="back" class="btn" value="Back" />
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</s:elseif>
									<s:else>
			   						 	<tr>
											<td class="heading"><s:label key="broker.brokermanagement" /></td>
							 		 	</tr>
							 		 	<tr style="height: 10px"><td>&nbsp;</td></tr>
							 		 	<s:if test='!("newAjax".equals(mode1) || "editAjax".equals(mode1))'>
				     						<tr>
					         					<td  style="color:red;"><s:actionerror/> <s:actionmessage/> </td>
					       					</tr>
					       				</s:if>
					       				<s:if test='"edit".equals(mode)'>
			       						<tr>
											<td colspan="5" align="right"> <s:text name="broker.status"/><font color="red">*</font>&nbsp;&nbsp;
												<s:select name="status" list="#{'Y':'Active','N':'Deactive','D':'Delete','L':'Lock'}" headerKey="" headerValue="-Select"/>
											</td>
										</tr>
										</s:if>
										<tr style="height: 10px"><td>&nbsp;</td></tr>
			         					<tr>
			           						 <td bgcolor="#FFFFFF"><table width="100%" border="0" cellspacing="0" cellpadding="0">
			               					 	<tr>
			                   						<td class="heading"><s:label  key="broker.companyinfo" /></td>
			               						</tr>
			                					<tr>	                                                 
			                 						<td class="bg"><table width="100%" border="0" align="center" cellpadding="4" cellspacing="0">
			                      						<tr>
			                      							<td width="5%"></td>
			                        						<td width="30%"><s:label key="broker.approve"/> <font color="red">*</font><br/>
			                           							 <s:textfield name="approvedby" id="approvedby" cssClass="inputBox" size="35" maxlength="30"/></td>
			                        					    <td width="30%"><s:label key="broker.brokercode" /> <font color="red">*</font><br/>
			                            						<s:textfield name="bcode" id="bcode"  cssClass="inputBox"  size="35" maxlength="10" readonly='%{!"new".equals(mode)}'/></td>
			                            					<td width="30%"><s:label key="broker.brokerOrg" /> <font color="red">*</font><br/>
			                           							<s:textfield name="borganization" id="borganization" cssClass="inputBox" maxlength="70" /></td>
			                            					<td width="5%"></td>
			                            				</tr>
			                      						<tr>
			                      							<td width="5%"></td>
			                            				   <td><s:label key="broker.address1" /> <br/>
			                           							 <s:textfield name="address1" id="address1" cssClass="inputBox" size="35" maxlength="50"/></td>
			                           					   <td><s:label key="broker.address2" /> <br/>
			                           							 <s:textfield name="address2" id="address2" cssClass="inputBox" size="35" maxlength="50"/></td>
			                           						<td><s:label key="broker.city" /> <font color="red">*</font><br/>
			                         							<s:select name="emirate" id="emirate" list="emiratesInfo" listKey="CITY_NAME" listValue="CITY_NAME" cssClass="inputSelect" headerKey="" headerValue="--select--" onchange="fnOtherCity(this.value)"/> </td>
			                           						<td width="5%"/>
			                       						</tr>
			                       						<tr>
			                       							 <td width="5%"></td>
			                       							 <td><s:label key="broker.othercity" /> <br/>
			                         							   <s:textfield name="othercity" id="othercity" cssClass="inputBox" size="35" maxlength="30"/></td>
			                       							 <td><s:label key="broker.country" /> <font color="red">*</font><br/>
			                         							  <s:select name="country" id="country" list="countriesInfo" cssClass="inputSelect" listKey="COUNTRY_ID" listValue="COUNTRY_NAME"  headerKey="" headerValue="--select--" /></td>
			                         						 <td><s:label key="broker.pobox" /><br/>
			                         							 <s:textfield name="pobox" id="pobox" cssClass="inputBox" size="35" maxlength="6"/></td>
			                         						 <td width="5%"/>
			                         					</tr>
			                      						<tr>
			                      							 <td width="5%"></td>
			                       							 <td><s:label key="broker.telephone" /> <br/>
			                         							   <s:textfield name="telephone" id="telephone"  cssClass="inputBox" size="35" maxlength="15"/></td>
			                       							 <td><s:label key="broker.fax" /> <br/>
			                         							   <s:textfield name="fax" id="fax" cssClass="inputBox" size="35" maxlength="10"/></td>
			                         						 <td align="left"><s:label key="broker.customercode" /><br/>
				                        						<div>
				                        							<%--	
				                        							<s:textfield name="nameAndCode"  id="nameAndCode" cssClass="input" size="30" readonly="true"/>
				                        							<input type="button" value="..." onclick="getCoreCustomerInfo()"/>
				                        							--%>
				                        							<s:textfield name="nameAndCode"  id="nameAndCode" cssClass="inputBox" size="30" />
				                        							<s:hidden name="CIMSNO"/>
				                        							<s:hidden name="ARACC"/>
				                        							<s:hidden name="customerName"/>
				                        						</div>
				                        					</td>
			                         						 <td width="5%"/>
			                      					   </tr>
			                      					  <s:if test='broImgName!=null'>
			                      					  	<tr>
			                       							<td width="5%"></td>
			                         						  
			                         						  <td><s:label key="VAT Reg No" /> <br/>
			                         							   <s:textfield name="vatRegNo" id="vatRegNo" cssClass="inputBox" size="35" maxlength="25"/></td>
			                         							<td><s:label key="broker.images" /> <br/><img src='${pageContext.request.contextPath}<s:property value="broImgName"/>' border="0" width="150" height="60"  alt="" ><br>
			                         							<a href="#" onclick="deleteLogo('','delete');">Delete Logo</a>
			                         							<s:hidden name="broImgName"/></td> 
			                         					</tr>
			                         				</s:if>
			                         				<s:else>
			                         					<tr>
			                       							<td width="5%"></td>
			                       							 
			                         						  <td><s:label key="VAT Reg No" /> <br/>
			                         							   <s:textfield name="vatRegNo" id="vatRegNo" cssClass="inputBox" size="35" maxlength="25"/></td>
			                         						<td><s:label key="broker.images" /> <br/>
			                         							   <s:file name="upload" id="upload" cssClass="inputBox"/>
			                         						</td>
			                         					</tr>
			                         				</s:else>
			                         					
			                      					   <tr><td colspan="5"></td></tr>
			               						   </table></td>
			               						</tr>
			               						 <tr>
			            							<td height="2" bgcolor="#FFFFFF"></td>
			      							    </tr>
			        						    <tr>
			            							<td bgcolor="#FFFFFF"><table width="100%" border="0" cellspacing="0" cellpadding="0">
			                							<tr>
			                  								<td class="heading"><s:label key="broker.contactpersonInfo" /></td>
			                							</tr>
			               		 						<tr>
			                  								<td class="bg"><table width="100%" border="0" align="center" cellpadding="4" cellspacing="0">
			                     								<tr>
			                        								<td width="5%">&nbsp;</td>
			                        								<td width="30%" align="left">
			                        									<s:label key="broker.title" /> <br/>
			                           									<s:select name="title" id="title" list="titlesInfo" cssClass="inputSelect" listKey="TITLE_ID" listValue="TITLE_NAME"  headerKey="" headerValue="--select--"/></td>
																	
			                       									<td width="30%" align="left"><s:label key="broker.firstname" /> <font color="red">*</font><br/>
			                         							  		 <s:textfield name="firstname" cssClass="inputBox" maxlength="70"/></td> 
			                         							  	<td><s:label key="broker.lastname" /> <br/>
			                         							  		 <s:textfield name="lastname" cssClass="inputBox" size="35" maxlength="30"/></td>
			                         							  	<td width="5%">&nbsp;</td>
			                         							 </tr>
			                         							<tr>
			                         								<td width="5%">&nbsp;</td>
			                         							  	<td width="30%" align="left"><s:label key="broker.gender" /> <br/>
																		<s:radio name="gender" id="gender" list="#{'M':'Male','F':'Female'}" /></td>
																	<td><s:label key="broker.dob" /> :<br/>
																		<div class="inputAppend"><sj:datepicker id="dob" name="dob" cssClass="inputBox1" displayFormat="dd/mm/yy" changeMonth="true" changeYear="true" readonly="true" yearRange="-100:+0" cssStyle="width:80%; border: none; background-color: #ffffff;"/></div>
																	</td>
																	<td><s:label key="broker.nationality" /> <font color="red">*</font><br/>
																		<s:select name="nationality" list="nationalitiesInfo" cssClass="inputSelect" listKey="COUNTRY_ID" listValue="NATIONALITY_NAME" headerKey="" headerValue="-select-"/></td>
																	<td width="5%">&nbsp;</td>
																</tr>
																<tr>
																	<td width="5%">&nbsp;</td>
			                       									<td><s:label key="broker.occupation" /> <br/>
			                         							  		 <s:textfield name="occupation"  cssClass="inputBox" size="35" maxlength="30"/></td>
																	<td><s:label key="broker.mobile" /> <br/>
			                         							  		 <s:textfield name="mobile" cssClass="inputBox" size="35" maxlength="10"/></td>
			                       									<td><s:label key="broker.email" /> <font color="red">*</font><br/>
			                           									 <s:textfield name="bemail" cssClass="inputBox" size="35"/></td>
			                           								<td width="5%">&nbsp;</td>
			                           							</tr>
																<tr>
																	<td width="5%">&nbsp;</td>
																	<td><s:label key="broker.executive" /> : <font color="red">*</font><br/>
			                           									<s:select name="executive" list="executivesInfo" cssClass="inputSelect" listKey="AC_EXECUTIVE_ID" listValue="AC_EXECUTIVE_NAME" headerKey="" headerValue="--select--" /></td>
			                           								<td width="5%" colspan="3">&nbsp;</td>
																</tr>
																<tr><td colspan="5"></td></tr>
			                  								</table></td>
			               								 </tr>
			           						 		</table></td>
			         							</tr>
			         							<s:if test='"new".equals(mode)'>
			         							<tr>
			            							<td height="2" bgcolor="#FFFFFF"></td>
			      							    </tr>
			        						    <tr>
			            							<td bgcolor="#FFFFFF" colspan="5"><table width="100%" border="0" cellspacing="0" cellpadding="0">
			                							<tr>
			                  								<td class="heading"><s:label key="broker.login.creation" /></td>
			                							</tr>
			                							<tr>
			                  								<td class="bg"><table width="100%" border="0" align="center" cellpadding="4" cellspacing="0">
					               								 <tr>
																	<td width="5%">&nbsp;</td>
			                       									<td><s:label key="broker.new" /> <font color="red">*</font> <br/>
			                         							  		 <s:textfield name="loginid"  cssClass="inputBox" size="35" maxlength="15"/></td>
																	<td><s:label key="broker.password" /> <br/>
			                         							  		 <s:password name="password" cssClass="inputBox" size="35" maxlength="20"/></td>
			                       									<td><s:label key="broker.rpassword" /> <font color="red">*</font><br/>
			                           									 <s:password name="repassword" cssClass="inputBox" size="35" maxlength="20"/></td>
			                           								<td width="5%">&nbsp;</td>
			                           							</tr>
			                           						</table></td>
	                           							</tr>
			           						 		</table></td>
			         							</tr>
			         							</s:if>
			         							<tr>
			         								<td bgcolor="#FFFFFF"><table width="100%" border="0" cellspacing="0" cellpadding="0">
			                							<tr><td class="heading"><s:label key="tax.info" /></td></tr>
			                      						<tr><td height="5" colspan="5"></td></tr>
			               		 						<tr>
			               		 							<s:set var="taxInfo" value="customerTaxInfo[0]" />
			                  								<td class="bg"><table width="100%" border="0" align="center" cellpadding="4" cellspacing="0">
			                     								<tr>
			                        								<td width="5%">&nbsp;</td>
			                        								<td width="5%">1</td>
																	<td width="40%"> <s:label key="policy.fee" /> : </td>
																	<td width="40%"> <s:radio id="policy_fee" name="policy_fee" list="#{'Y':'Yes','N':'No'}" onclick="policyStatusDisplay(this.value)" /> </td>
																	<td width="10%">&nbsp;</td>
																</tr>
																
																<tr id="policy_Status_Display" style="display: <s:if test='"Y".equals(policy_fee)'></s:if><s:else>none</s:else>">
																	<td>&nbsp;</td>
																	<td>&nbsp;</td>
																	<td> <s:label key="policy.feein" /> : </td>
																	<td> <s:textfield name="policFee" cssClass="inputBox" maxlength="5" size="15"/> </td>
																	<td>&nbsp;</td>
																</tr>
																<%--
			                      								<tr>
			                        								<td height="5" colspan="5"></td>
			                      								</tr>
																<tr>
																	<td>&nbsp;</td>
			                       									<td>2</td>
																	<td> <s:label key="policy.goverment" /> : </td>
																	<td> <s:radio name="gov_fee" id="gov_fee" list="#{'Y':'Yes','N':'No'}" onclick="govStatusDisplay(this.value)" /> </td>
																	<td>&nbsp;</td>
																</tr>
																<tr id="gov_Status_Display" style="display: <s:if test='"Y".equals(gov_fee)'></s:if><s:else>none</s:else>">
																	<td>&nbsp;</td>
																	<td>&nbsp;</td>
																	<td> <s:label key="policy.gov.in" /> : </td>
																	<td> <s:textfield  name="govtTax" cssClass="inputBox" size="15" maxlength="5"  /> </td>
																	<td>&nbsp;</td>
																</tr>
			                      								<tr><td height="5" colspan="5"></td></tr>
																<tr>
																	<td>&nbsp;</td>
			                        								<td>3</td>
																	<td> <s:label key="policy.emergency" /> : </td>
																	<td> <s:radio name="emer_fee" id="emer_fee" list="#{'Y':'Yes','N':'No'}" onclick="emerStatusDisplay(this.value)" /> </td>
																	<td>&nbsp;</td>
																</tr>
																<tr id="emer_Status_Display" style="display: <s:if test='"Y".equals(emer_fee)'></s:if><s:else>none</s:else>">
																	<td>&nbsp;</td>
																	<td></td>
																	<td> <s:label key="policy.emer.in" /> : </td>
																	<td> <s:textfield name="emergencyfund" cssClass="inputBox" maxlength="5"  size="15"/> </td>
																	<td>&nbsp;</td>
																</tr>
																--%>
			                      								<tr><td height="5" colspan="5"></td>
			                      								</tr><tr>
																	<td>&nbsp;</td>
			                       									<td>2</td>
																	<td> <s:label key="policy.app" /> : </td>
																	<td> <s:radio name="app_for" list="#{'user':'Operation','broker':'Broker','both':'Both'}" /> </td>
																	<td>&nbsp;</td>
																</tr>
																<tr><td height="5" colspan="5"></td>
			                      								</tr><tr>
																	<td>&nbsp;</td>
			                       									<td>3</td>
																	<td>Commission for Issuer Quotes : </td>
																	<td>For One Off Policy&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:textfield name="oneOffCommission" cssClass="inputBox" maxlength="15"  size="15"/></td>
																	<td>&nbsp;</td>
																</tr>
																<tr>
																	<td>&nbsp;</td>
			                       									<td colspan="2"></td>
																	<td>For Open Cover Policy<s:textfield name="openCoverCommission" maxlength="15" cssClass="inputBox" size="15"/></td>
																	<td>&nbsp;</td>
																</tr>
																<tr><td height="5" colspan="5"></td></tr>
			                      					  			<tr>
			                       									<td width="2%">&nbsp;</td>
																	<td colspan="2" width="53%" align="right" style="padding-left:10px"><s:label key="policy.eff" /> : <font color="red">*</font> </td>
																	<td width="45%" style="padding-left:10px">
																		<div class="inputAppend"><sj:datepicker id="effecdate" name="effecdate" cssClass="inputBox1" displayFormat="dd/mm/yy" changeMonth="true" changeYear="true" readonly="true" cssStyle="width:80%; border: none; background-color: #ffffff;"/></div>																		
																</tr>
			                      								<tr><td height="5" colspan="5"></td></tr>
			                  								</table></td>
			               								 </tr>
			           						 		</table></td>
			         							</tr>
			            					</table></td>
			          					</tr>
			          					<tr>
											<td colspan="3">
												<table width="100%" border="0" cellspacing="0" cellpadding="0">
													<tr>
														<td height="25" align="center" valign="middle">
															<input type="button" onclick="fnsubmit('back')" name="back" class="btn" value="Back" />
															<input type="button" name="submit2" class="btn" value="Submit" onclick="fnsubmit('info')"/>
														</td>
													</tr>
												</table>
											</td>
										</tr>
										</s:else>
				          			</table>
				          			<s:hidden name="agencyCode"/>
				          			<s:hidden name="login_Id"/>
									<s:hidden name="mode"/>
				     			</s:form>
								</td>
							</tr>
						</table></td></tr>
                    </table>
                </div>
                <div title="Product Selection" style="padding:10px">
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
										<tr class="bottomtext">
											<td class="heading">
												<s:label key="broker.brokermanagement" />
											</td>
										</tr>
						     			<tr style="height: 10px"><td>&nbsp;</td></tr>
						     			<tr><td align="right"><a href="#" onclick="forward(${agencyCode},'productselections', '','newAjax');">Add New Product</a></td></tr>
						     			<tr style="height: 10px"><td>&nbsp;</td></tr>
										<tr align="center">
											<td>												
												<div id="commisionDetailss">
													<display:table name="commisionDetails" pagesize="10" requestURI="" class="footable" uid="row" id="record">
														<display:setProperty name="paging.banner.one_item_found" value="" />
														<display:setProperty name="paging.banner.one_items_found" value="" />
														<display:setProperty name="paging.banner.all_items_found" value="" />
														<display:setProperty name="paging.banner.some_items_found" value="" />
														<display:setProperty name="paging.banner.placement"	value="bottom" />
														<display:setProperty name="paging.banner.onepage" value="" />
														<display:column sortable="true" style="text-align:left;"  title='Products' property="product_name"/>
														<display:column sortable="true" style="text-align:left;"  title='Sum Insured' property="INSURANCE_END_LIMIT" />
														<display:column sortable="true" style="text-align:left;" title='Commission [%]' property="COMMISSION"/>
														<display:column sortable="true" style="text-align:left;" title='Min Premium' property="MIN_PREMIUM_AMOUNT" />
														<display:column sortable="true" style="text-align:left;" title='Loading % Max' property="LOADING_PREMIUM" />
														<display:column sortable="true" style="text-align:left;" title='Discount % Max' property="DISCOUNT_PREMIUM" />
														<display:column sortable="true" style="text-align:left;" title='Back Days Allowed' property="BACK_DATE_ALLOWED"/>
														<%--
														<display:column sortable="true" style="text-align:left;" title='Provision for Creation'>
															<s:if test='#attr.record.PROVISION_FOR_PREMIUM=="Y"'>Yes</s:if><s:elseif test='#attr.record.PROVISION_FOR_PREMIUM=="N"'>No</s:elseif><s:else>-</s:else>
														</display:column>
														<display:column sortable="true" style="text-align:left;" title='Payment Gateway & Receipt Required'>
															<s:if test='#attr.record.pay_receipt_status=="Y"'>Yes</s:if><s:elseif test='#attr.record.pay_receipt_status=="N"'>No</s:elseif><s:else>-</s:else>
														</display:column>
														<display:column sortable="true" style="text-align:left;" title='Provision for Debit'>
															<s:if test='#attr.record.FREIGHT_DEBIT_OPTION=="Y"'>Yes</s:if><s:elseif test='#attr.record.FREIGHT_DEBIT_OPTION=="N"'>No</s:elseif><s:else>-</s:else>
														</display:column>
														<display:column sortable="true" style="text-align:left;" title='Provision for Loading/ Discount'>
															<s:if test='#attr.record.receipt_status=="Y"'>Yes</s:if><s:else>No</s:else>
														</display:column>
														<display:column sortable="true" style="text-align:left;" title='Remarks Required'>
															<s:if test='#attr.record.REMARKS=="Y"'>Yes</s:if><s:else>No</s:else>
														</display:column>
														--%>
														<display:column sortable="true" style="text-align:left;" title='Edit'>
															<input type="button" name="edit" value="Edit" class="btn" onclick="forward('${agencyCode}','productselections', '${record.PRODUCT_ID}', 'editAjax');"/>
														</display:column>
													<%--<display:column sortable="true" style="text-align:left;" title='History'>
															<input type="button" name="edit" value="History" class="btn" onclick="historyproduct('${agencyCode}','${record.PRODUCT_ID}')"/>
														</display:column> --%>
													</display:table>
												</div>
											</td>
										</tr>
										<tr><td>&nbsp;</td></tr>
									</table>
									<s:hidden name="mode1"/>
									<s:hidden name="agencyCode"/>
									<s:hidden name="borganization"/>
									<s:hidden name="bcode"/>
									<s:hidden name="firstname"/>
									<s:hidden name="login_Id"/>
				     			</s:form>
								</td>
							</tr>
							<tr style="height: 10px"><td>&nbsp;</td></tr>
				 		 	<s:if test='"newAjax".equals(mode1) || "editAjax".equals(mode1)'>
	     						<tr><td  style="color:red;"><s:actionerror/> <s:actionmessage/></td></tr>
		       				</s:if>
							<tr><td><div id="productselections"></div></td></tr>
							<tr><td>&nbsp;</td></tr>
						</table></td></tr>
                    </table>
                </div>
            </div>
        </div>
    </div>
</body>
<script>
	if('<s:property value="mode1"/>'=='newAjax' || '<s:property value="mode1"/>'=='editAjax'){
    		postRequest('${pageContext.request.contextPath}/getBrokerAjaxBrokerMgm.action?reqFrom=productselections&agencyCode='+'<s:property value="agencyCode"/>'+'&productID='+'<s:property value="productID"/>'+'&mode1='+'<s:property value="mode1"/>'+'&mode=ajax'+'&productName='+'<s:property value="productName"/>'+'&commission='+'<s:property value="commission"/>'
    					+'&insurance_End_Limit='+'<s:property value="insurance_End_Limit"/>'+'&min_Premium_Amount='+'<s:property value="min_Premium_Amount"/>'+'&loadingPremium='+'<s:property value="loadingPremium"/>'+'&discountPremium='+'<s:property value="discountPremium"/>'+'&back_Date_Allowed='+'<s:property value="back_Date_Allowed"/>'
    					+'&user_Id_Creation='+'<s:property value="user_Id_Creation"/>'+'&payReceipt='+'<s:property value="payReceipt"/>'+'&freight='+'<s:property value="freight"/>'+'&provision='+'<s:property value="provision"/>', 'productselections');
   		}
	function getCoreCustomerInfo()
	{
		var URL='${pageContext.request.contextPath}/getccInfoBrokerMgm.action?mode=info';
		var windowName = "BrokerInfo";
		var width  = screen.availWidth;
		var height = screen.availHeight;
		var w = 700;
		var h = 400;
		var features =
			'width='          + w +
			',height='		  + h +
			',left='		  + ((width - w - 0) * .4)  +
			',top='			  + ((height - h - 0) * .4) +
			',directories=no' +
			',location=no'	  +
			',menubar=no'	  +
			',scrollbars=yes' +
			',status=yes'	  +
			',toolbar=no'	  +
			',resizable=false';
		var strOpen = window.open (URL, windowName, features);
		strOpen.focus();
	}
	function policyStatusDisplay(val){
    		if("Y"==val){
    			document.getElementById("policy_Status_Display").style.display = "";	
    		}else{
    			document.getElementById("policy_Status_Display").style.display = "none";
    		}
    		return true;
    	}
   	function govStatusDisplay(val){
   		if("Y"==val){
   			document.getElementById("gov_Status_Display").style.display = "";	
   		}else{
   			document.getElementById("gov_Status_Display").style.display = "none";
   		}
   		return true;
   	}
   	function emerStatusDisplay(val){
   		if("Y"==val){
   			document.getElementById("emer_Status_Display").style.display = "";	
   		}else{
   			document.getElementById("emer_Status_Display").style.display = "none";
   		}
   		return true;
   	}
   	function provisionDisplay(val){
    		if("Y"==val){
    			document.getElementById("provision_Display").style.display = "";	
    		}else{
    			document.getElementById("provision_Display").style.display = "none";    			
    		}
    		return true;
    	}  
    function productsel(val)
    {
     if(val==11){
     	document.getElementById("opencov").style.display = "";
     	document.getElementById("oneoff").style.display = "none";
     }
     else if(val==3){
     	document.getElementById("oneoff").style.display = "";
     	document.getElementById("opencov").style.display = "none";
     }
     else{
     	document.getElementById("opencov").style.display = "none";
     	document.getElementById("oneoff").style.display = "none";
     }
     return true;
    }  	
    	
   	function forward(agcode, id, value, mode1){

		postRequest('${pageContext.request.contextPath}/getBrokerAjaxBrokerMgm.action?reqFrom='+id+'&agencyCode='+agcode+'&productID='+value+'&mode1='+mode1, id);
   	}
	<s:if test='emirate!=null && !"".equals(emirate)'>
   		fnOtherCity('<s:property value="emirate"/>');
   	</s:if>
   	function fnOtherCity(val){
   		if(val=='VARIOUS'){
   			document.getElementById('othercity').disabled=false;
   		}else{
   			document.getElementById('othercity').value='';
   			document.getElementById('othercity').disabled=true;
   		}
   	}
   	function deleteProduct(){
	  var conf=confirm("Do you want do delete this product?"); 
	  if(conf){
	    document.productSelect.action="deleteProductBrokerMgm.action";
	    document.productSelect.submit();
	  }
	}
	function deleteLogo(agencyCode,mode){
		document.BrokerInfoEdit.action = "editBrokerMgm.action?mode1=delete";
		document.BrokerInfoEdit.submit();
	}
	
	function addProductBroker(){
		document.productSelect.action = "addProductBrokerMgm.action?";
		document.productSelect.submit();
		
	}
	
</script>
</html>
