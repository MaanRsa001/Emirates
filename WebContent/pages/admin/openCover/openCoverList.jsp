<%@ page isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<script language="JavaScript">javascript:window.history.forward(1);</script>
		<script>
		
			function stopRKey(evt) { 
			  var evt = (evt) ? evt : ((event) ? event : null); 
			  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
			  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
			} 
			document.onkeypress = stopRKey;
			function fnTab(val, reqFrom, value, index){
				if(reqFrom=="regQuotes"){
					postRequest('${pageContext.request.contextPath}/getregQuoteOC.action?broker='+val+'&reqFrom='+reqFrom+'&index=1&from1=ajax', reqFrom);
		   		}else if(reqFrom=="portfo"){
					postRequest('${pageContext.request.contextPath}/getPortfolioOC.action?broker='+val+'&reqFrom='+reqFrom+'&index=2&from1=ajax', reqFrom);
		   		}
			}
			
			$('#neqQuote').load({
		      title:'New Quote',
		      href: '${pageContext.request.contextPath}/newQuoteOC.action',
		   });
		   
		   $(function(){
				var index = '<s:property value="index"/>';
				var t = $('#tabs');
				var tabs = t.tabs('tabs');
					t.tabs('select', tabs[index].panel('options').title);
			});
  		
	  		function fnOCType(val){
	  			if('One Stop'==val)
	  				document.getElementById('ocend').style.display='';
	  			else
	  				document.getElementById('ocend').style.display='none';
	  		}
	  		function fncross_percent(val){
	  			if('Y'==val)
	  				document.getElementById('cross_percent').style.display='';
	  			else
	  				document.getElementById('cross_percent').style.display='none';
	  		}
	  		
		 </script>
	</head>
	<body>
		 <div style="margin:10px 0;"></div>
   		 	<div class="easyui-layout" style="height:100vh;">
   		 		 <div data-options="region:'center',title:'Open Cover',iconCls:'icon-ok'">
   		 		  	<div class="easyui-tabs" data-options="fit:true,border:false,plain:true" id="tabs">
                		<div title="New Quote" style="padding:10px">
                			<div id="neqQuote">
			                	<table width="100%">
			                          <tr>
									    <td height="5"></td>
									  </tr>
									  <tr>
									    <td><table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#E5E5E5">
									      <tr>
									        <td bgcolor="#FFFFFF" style="padding:10px; background:#F8F8F8">
						   					 <s:form id="quoteEdit" name="quoteEdit" method="post" action="" theme="simple">
						       					 <table width="100%" border="0" cellspacing="0" cellpadding="0">
						       					 <s:if test="'successNew'.equals(display)">
													<tr>
							   							<td bgcolor="#FFFFFF" style="padding:10px; background:#F8F8F8">
							   								<s:label key="quote.new.success"/>
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
							   								<s:label key="quote.update.success"/>
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
														<td class="heading"><s:label key="New Quote" /></td>
										 		 	</tr>
										 		 	<tr style="height: 10px"><td>&nbsp;</td></tr>
										 		 	<s:if test='!("newAjax".equals(mode1) || "editAjax".equals(mode1))'>
							     						<tr>
								         					<td  style="color:red;"><s:actionerror/> <s:actionmessage/> </td>
								       					</tr>
								       				</s:if>
													<tr style="height: 10px"><td>&nbsp;</td></tr>
						         					<tr>
						           						 <td bgcolor="#FFFFFF"><table width="100%" border="0" cellspacing="0" cellpadding="0">
						               					 	<tr>
						                   						<td class="heading"><s:label  key="label.quote.new.opencover" /></td>
						               						</tr>
						                					<tr>	                                                 
						                 						<td class="bg"><table width="100%" border="0" align="center" cellpadding="4" cellspacing="0">
						                      						<tr>
						                      							<td width="5%"></td>
						                      							<td width="35%" align="left"><s:label key="label.quote.businesstype" /> <font color="red">*</font><br/>
						                         								<s:select name="business" id="business" list="businessTypeList" listKey="REMARKS" listValue="DETAIL_NAME"  headerKey="" headerValue="--select--" cssClass="input"/></td>
						                        						<td width="35%" align="left"><s:label key="label.quote.broker" /> <font color="red">*</font><br/>
						                         								<s:select name="broker" list="brokerList" headerKey="" headerValue="-Select-" listKey="login_id" listValue="COMPANY_NAME" cssClass="input"/></td>
						                        					    <td width="30%" align="left"><s:label key="label.quote.octype" /> <font color="red">*</font><br/>
						                            							<s:radio name="octype" id="octype"  cssClass="input" list="#{'One Stop':'One Stop OpenCover','Standard':'Standard OpenCover'}" onchange="fnOCType(this.value)" /></td>
						                            					<td width="5%"/>
						                            				</tr>
						                      						<tr>
						                      							<td width="5%"></td>
						                            				    <sx:div>
							                        						<td align="left"><s:label key="label.quote.customer" /> <font color="red">*</font><br/>
							                        							<s:textfield name="customer"  id="customer" cssClass="input" readonly="true"/>
							                        							<input type="button" value="..." onclick="return popUp('${pageContext.request.contextPath}/customerPopupOC.action','600','500')" class="btn"/>
							                        							<s:hidden name="customerName"/>
						                        							</td>
							                            				</sx:div>
						                       						   <td><s:label key="label.quote.startdate" /><font color="red">*</font> <br/>
						                           							 <s:textfield name="startdate" id="startdate" cssClass="easyui-datebox"/></td>
						                           					   <td id="ocend" style="display:<s:if test='"One Stop".equals(octype)'></s:if><s:else>none</s:else>">
						                           					   		 <s:label key="label.quote.enddate" /> <font color="red">*</font><br/>
						                           							 <s:textfield name="enddate" id="enddate" cssClass="easyui-datebox"/></td>
						                           						<td width="5%"/>
						                       						</tr>
						               						   </table></td>
						               						</tr>
						               						 <tr>
						            							<td height="2" bgcolor="#FFFFFF"></td>
						      							    </tr>
						        						    <tr>
						            							<td bgcolor="#FFFFFF"><table width="100%" border="0" cellspacing="0" cellpadding="0">
						                							<tr>
						                  								<td class="heading"><s:label key="label.quote.additionalinfo" /></td>
						                							</tr>
						               		 						<tr>
						                  								<td class="bg"><table width="100%" border="0" align="center" cellpadding="4" cellspacing="0">
						                     								<tr>
						                        								<td width="5%">&nbsp;</td>
						                        								<td width="35%" align="left">
						                        									<s:label key="label.quote.annual.turnover" /><font color="red">*</font> <br/>
						                           									<s:textfield name="turnover" cssClass="input"/></td>
						                       									<td width="30%" align="left"><s:label key="label.quote.currency" /> <font color="red">*</font><br/>
						                         							  		 <s:select name="currency" list="currecyList" listKey="CURRENCY_ID" listValue="CURRENCY_NAME" headerKey="" headerValue="-select-" cssClass="input"/><s:textfield name="currencyVal" cssClass="inputsm"/></td>
						                         							  	<td><s:label key="label.quote.free.text" /> <br/>
						                         							  		 <s:radio name="freetext" list="#{'Y':'Yes','N':'No'}" cssClass="input2"/></td>
						                         							  	<td width="5%">&nbsp;</td>
						                         							 </tr>
						                         							<tr>
						                         								<td width="5%">&nbsp;</td>
						                         								<td><s:label key="label.quote.commission" /> <font color="red">*</font><br/>
																					<s:textfield name="commission" cssClass="input"/></td>
						                         							  	<td width="35%" align="left"><s:label key="label.quote.crossvoyage" /> <br/>
																					<s:radio name="crossvoyage" list="#{'Y':'Yes','N':'No'}" cssClass="input2" onchange="fncross_percent(this.value)"/></td>
																				<td id="cross_percent" style="display:<s:if test='"Y".equals(crossvoyage)'></s:if><s:else>none</s:else>"><s:label key="label.quote.crossvoyage.percentage" /><br/>
																					<s:textfield name="crosspercent"  id="crosspercent" cssClass="input"/></td>
																				<td width="5%">&nbsp;</td>
																			</tr>
																			<tr>
																				<td width="5%">&nbsp;</td>
						                       									<td><s:label key="label.quote.exist.cims" /> <br/>
						                         							  		 <s:textfield name="cimsNo" cssClass="input"/><s:textfield name="cimsNo1" cssClass="inputsm"/></td>
																				<td><s:label key="label.quote.minpremium" /><font color="red">*</font> <br/>
						                         							  		 <s:textfield name="minpremium" cssClass="input" /></td>
						                       									<td><s:label key="label.quote.minpremium.issuance" /> <font color="red">*</font><br/>
						                           									 <s:textfield name="minpremiumissu" cssClass="input" /></td>
						                           								<td width="5%">&nbsp;</td>
						                           							</tr>
																			<tr>
																				<td width="5%">&nbsp;</td>
																				<td><s:label key="label.quote.issuancefee" /><font color="red">*</font><br/>
						                           									<s:textfield name="issuancefee"  cssClass="input"/></td>
																				<td><s:label key="label.quote.multiple.declare" /> <br/>
						                         							  		 <s:radio name="multiDeclare" list="#{'I':'Individual','M':'Multiple'}" cssClass="input"/></td>
						                       									<td><s:label key="label.quote.multiple.declare.minpremium" /><br/>
						                           									 <s:textfield name="multiDeclarePremium" cssClass="input" /></td>
						                           								<td width="5%">&nbsp;</td>
						                           							</tr>
						                           							<tr>
																				<td width="5%">&nbsp;</td>
																				<td><s:label key="label.quote.backdate" /><br/>
						                           									<s:textfield name="backDate" cssClass="input"/></td>
																				<sx:div>
									                        						<td width="35%" align="left"><s:label key="label.quote.broker.arcode" /><br/>
									                        							<s:textfield name="nameAndCode"  id="nameAndCode" cssClass="input" readonly="true"/>
									                        							<input type="button" value="..." onclick="getCoreCustomerInfo()"/>
									                        							<s:hidden name="CIMSNO"/>
									                        							<s:hidden name="ARACC"/>
									                        							<s:hidden name="customerName"/>
								                        							</td>
									                            				</sx:div>
						                       									<td></td>
						                           								<td width="5%">&nbsp;</td>
						                           							</tr>
																			<tr><td colspan="5"></td></tr>
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
                		</div>
                		<div title="Registered Quotes" style="padding:10px" >
							<table width="100%">
	                          <tr>
							    <td height="5"></td>
							  </tr>
							  <tr>
							    <td><table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#E5E5E5">
							      <tr>
							        <td bgcolor="#FFFFFF" style="padding:10px; background:#F8F8F8">
				   					<s:form id="regform" name="regform" theme="simple">
								  		<table width="100%" align="center" width="80%">
											<tr><td></td></tr>
											<tr><td align="center"> <s:text name="label.broker.name"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;<s:select name="broker" list="brokerList" headerKey="" headerValue="-Select-" listKey="login_id" listValue="COMPANY_NAME" onchange="fnTab(this.value, 'regQuotes','','1')"/></td></tr>
											<tr><td></td></tr>
											<tr><td>
												<div id="regQuotes">
													<display:table name="regQuoteList" pagesize="10" requestURI="" class="table" uid="row" id="record" excludedParams="from1">
															<display:setProperty name="paging.banner.one_item_found" value="" />
															<display:setProperty name="paging.banner.one_items_found" value="" />
															<display:setProperty name="paging.banner.all_items_found" value="" />
															<display:setProperty name="paging.banner.some_items_found" value="" />
															<display:setProperty name="paging.banner.placement"	value="bottom" />
															<display:setProperty name="paging.banner.onepage" value="" />
															<display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="Proposal No" property="proposal_no"/>
															<display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="Customer Name" property="cust_name"/>
															<display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="Policy Start Date" property="start_date"/>
															<display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="Policy End date" property="end_date"/>
															<display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="Edit">
																<a href="#" onclick="editQuote(${record.proposal_no}, '${session.user}')">Edit</a>
															</display:column>
															<display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="Schedule">
																<s:if test='RENEWAL_STATUS=="Y"'><a href="#" onclick="editQuote(${record.proposal_no}, '${session.user}')">Schedule</a></s:if>
																<s:else>N/A</s:else>
															</display:column>
													</display:table>
												</div>
											</td></tr>
			    						</table>
			    					</s:form>
									</td>
								</tr>
							</table></td></tr>
	                    </table>
	                </div>
	                <div title="Portfolio" style="padding:10px" >
							<table width="100%">
	                          <tr>
							    <td height="5"></td>
							  </tr>
							  <tr>
							    <td><table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#E5E5E5">
							      <tr>
							        <td bgcolor="#FFFFFF" style="padding:10px; background:#F8F8F8">
				   					<s:form id="regform" name="regform" theme="simple">
								  		<table width="100%" align="center" width="80%">
											<tr><td></td></tr>
											<tr><td align="center"> <s:text name="label.broker.name"/>&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;<s:select name="broker" list="brokerList" headerKey="" headerValue="-Select-" listKey="login_id" listValue="COMPANY_NAME" onchange="fnTab(this.value, 'portfo','','1')"/></td></tr>
											<tr><td></td></tr>
											<tr><td>
												<div id="portfo">
													<display:table name="portfolioList" pagesize="10" requestURI="" class="table" uid="row" id="record" excludedParams="from1">
															<display:setProperty name="paging.banner.one_item_found" value="" />
															<display:setProperty name="paging.banner.one_items_found" value="" />
															<display:setProperty name="paging.banner.all_items_found" value="" />
															<display:setProperty name="paging.banner.some_items_found" value="" />
															<display:setProperty name="paging.banner.placement"	value="bottom" />
															<display:setProperty name="paging.banner.onepage" value="" />
															<display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="Core Application Policy No" property="MISSIPPI_OPENCOVER_NO"/>
															<display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="Proposal No" property="PROPOSAL_NO"/>
															<display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="Customer Name" property="cust_name"/>
															<display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="Policy Start Date" property="start_date"/>
															<display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="Policy End date" property="end_date"/>
															<display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="Endorse">
																<s:if test='STATUS=="Y"'><a href="#" onclick="editQuote(${record.proposal_no}, '${session.user}')">Edit</a></s:if>
																<s:elseif test='ENDT_STATUS!="N"'>Endorse</s:elseif>
															</display:column>
															<display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="View">
																<a href="#" onclick="editQuote(${record.proposal_no}, '${session.user}')">View</a>
															</display:column>
															<display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="Schedule">
																<s:if test='STATUS!="Y"'><a href="#" onclick="editQuote(${record.proposal_no}, '${session.user}')">Schedule</a></s:if>
																<s:else>N/A</s:else>
															</display:column>
															
															<display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="Endt Schedule">
																<s:if test='ENDT_STATUS=="N"'><a href="#" onclick="editQuote(${record.proposal_no}, '${session.user}')">Schedule</a></s:if>
																<s:else>N/A</s:else>
															</display:column>
															<display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="DeActivate">
																<s:if test='RENEWAL_STATUS=="Y"'><a href="#" onclick="editQuote(${record.proposal_no}, '${session.user}')">Delete</a></s:if>
																<s:else>N/A</s:else>
															</display:column>
													</display:table>
												</div>
											</td></tr>
			    						</table>
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