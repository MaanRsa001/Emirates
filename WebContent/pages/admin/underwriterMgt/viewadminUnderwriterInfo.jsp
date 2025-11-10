<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<html>
	<head>
		<script>
			function fnCall(from){
				if(from=='edit')
					document.underwriter.action ="viewUnderwriterMgm.action?mode=edit";
				else if(from=='list')
					document.underwriter.action = "UnderwriterCreationContoller.action";
				else if(from=='changePwd')
					document.underwriter.action = "changePassUnderwriterMgm.action?mode=changePass";	
				else if(from=='include')
					document.underwriter.action = "includeIssuerUnderwriterMgm.action?type1=include";
				else if(from=='exclude')
					document.underwriter.action = "excludeIssuerUnderwriterMgm.action?type1=exclude";
				else if(from=='openCover')
					document.info.action = "opencoverOC.action";
				else if(from=='statistics')
					document.info.action = "statisticsRE.action";
				<%--alert(from);
					postRequest('${pageContext.request.contextPath}/getABListCustomerMgm.action?agencyCode=10016&borganization='+'<s:property value="borganization"/>'+'&bcode='+'<s:hidden name="bcode"/>'+'&mode=mainTab', 'mainTab');
				}--%>else
					document.underwriter.action = from+"BrokerMgm.action";
				document.underwriter.submit();
			}
			function branchSelection(){
			  var params = "?branchSelected="+document.getElementById('branchSelected').value==null?'':document.getElementById('branchSelected').value;
			  var URL ='${pageContext.request.contextPath}/branchSelectionUnderwriterMgm.do?branchSelected='+params;
			  return popUp(URL,'700','500');
			}
			function stopRKey(evt) { 
			  var evt = (evt) ? evt : ((event) ? event : null); 
			  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
			  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
			} 
			document.onkeypress = stopRKey;
			
			function fnBack(){
				document.underwriter.action ='UnderwriterCreationContoller.action';
				document.underwriter.submit();
			}
		</script>
	</head>
	<body>
    <div style="margin:10px 0;"></div>
    <div class="easyui-layout" style="height:100vh;">
       <div data-options="region:'west',split:true" title="Options" style="width:150px;">
            <div class="easyui-accordion" data-options="fit:true,border:false">
                <input type="button" class="btntab" value="Underwriter Details" onclick="fnCall('list')"/><br/>
            <%--<input type="button" class="btntab" value="Edit" onclick="fnCall('edit')"/><br/> --%>
                <input type="button" class="btntab" value="Change Password" onclick="fnCall('changePwd')"/><br/>
                <input type="button" class="btntab" value="Included Brokers" onclick="fnCall('exclude')"/><br/>
                <input type="button" class="btntab" value="Excluded Brokers" onclick="fnCall('include')"/><br/>
            </div>
        </div>
        <div data-options="region:'center',title:'<s:property value="loginId"/>',iconCls:'icon-ok'">
            
            <div class="easyui-tabs" data-options="fit:true,border:false,plain:true" id="mainTab">            	
                <div title="Under writer Info" style="padding:5px">
                	<table width="100%">
                          <tr>
						    <td height="5"></td>
						  </tr>
						  <tr>
						    <td><table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#E5E5E5">
						      <tr>
						        <td bgcolor="#FFFFFF" style="padding:10px; background:#F8F8F8">
			   					 <s:form id="info" name="underwriter" method="post" action="updateissuerUnderwriterMgm" theme="simple">
			       					 <table width="95%" border="0" cellspacing="0" cellpadding="0">
			       					 <s:if test="'successNew'.equals(display)">
										<tr>
				   							<td bgcolor="#FFFFFF" style="padding:10px; background:#F8F8F8">
				   								<s:label key="user.new.success"/>
											</td>
										</tr>
										<tr>
											<td colspan="3">
												<table width="100%" border="0" cellspacing="0" cellpadding="0">
													<tr>
														<td height="25" align="center" valign="middle">
															<input type="button" onclick="fnsubmit('back','getABListUserMgm', this.form)" name="back" class="btn" value="Back" />
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</s:if>
									<s:elseif test="'successUpdate'.equals(display)">
										<tr>
				   							<td bgcolor="#FFFFFF" style="padding:10px; background:#F8F8F8">
				   								<s:label key="user.update.success"/>
											</td>
										</tr>
										<tr>
											<td colspan="3">
												<table width="100%" border="0" cellspacing="0" cellpadding="0">
													<tr>
														<td height="25" align="center" valign="middle">
															<input type="button" onclick="fnsubmit('back','getABListUserMgm', this.form)" name="back" class="btn" value="Back" />
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</s:elseif>
									<s:else>
			   						 	<tr>
											<td class="heading" colspan="5"><s:text name="Underwriter Management"></s:text> </td>
							 		 	</tr>
							 		 	<tr style="height: 10px"><td>&nbsp;</td></tr>
							 		 	<s:if test='!"product".equals(mode1) && !"login".equals(mode1)'>
				     						<tr>
					         					<td colspan="5" style="color:red;"><s:actionerror/> <s:actionmessage/> </td>
					       					</tr>
					       				</s:if>
					       				<s:if test='!"new".equals(optionMode)'>
				       						<tr>
												<td colspan="5" align="right"> <s:text name="user.status"/>&nbsp;&nbsp;
													<s:select name="ustatus" list="#{'Y':'Active','N':'Deactive','D':'Delete','L':'Lock'}" headerKey="" headerValue="-Select"/>
												</td>
											</tr>
										</s:if>
										<tr style="height: 10px"><td>&nbsp;</td></tr>
			         					<tr>
			           						 <td bgcolor="#FFFFFF" colspan="5"><table width="100%" border="0" cellspacing="0" cellpadding="0">
			               					 	<tr>
			                   						<td class="heading"><s:text name="Edit Underwriter" /> </td>
			               						</tr>
			                					<tr>	                                                 
			                 					   <td class="bg">
			                 					               <table width="100%" border="0" align="center" cellpadding="4" cellspacing="0">
					               								 <tr>
																	<td width="5%">&nbsp;</td>
			                       									<td width="30%"><s:text name="User Name" /> <font color="red">*</font><br/>
			                         							  		 <s:textfield name="issurName"  cssClass="inputBox" size="35"/></td>
																	<td width="30%"><s:text name="Email Id" /> <font color="red">*</font> <br/>
			                         							  		<s:textfield name="emailId"  cssClass="inputBox" size="35"/></td>
			                       									<td width="30%"><s:text name="Login Id" /> <font color="red">*</font><br/>
			                           									<s:textfield name="loginId"  cssClass="inputBox" size="35" readonly="true"/></td>
			                           								<td width="5%">&nbsp;</td>
			                           							</tr>
			                           							<tr>
																	<td width="5%">&nbsp;</td>
			                       									<td><s:text name="Core Login Id" /> <font color="red">*</font><br/>
			                         							  		 <s:textfield name="coreLoginId" maxlength="3" cssClass="inputBox" size="35"/></td>
			                         							  	<td colspan="2"><s:text name="Choose Products" /> <font color="red">*</font><br/>
			                         							  		<s:checkboxlist name="products" list="productList" listKey="PRODUCT_ID" listValue="PRODUCT_NAME"/></td>
			                         							  	<td></td>	 
			                         							<td width="5%">&nbsp;</td>
																</tr>
																
																 <tr>
										                            <td>&nbsp;</td>
										                            	<td>
										                            		<s:text name=" VAT Applicable Authorization " /><br />
											                           		 <s:radio list="#{'Y':'Yes','N':'No'}" name="vatApplicable" id="vatApplicable"  value='%{(vatApplicable==null || "".equals(vatApplicable))?"Y":vatApplicable}' disabled="#disable" onclick="editCustomerInfo();"/>
												                     	</td>
	                           									 </tr>
																 	
			                         					</table></td>	
			               						   </tr>
			               					</table></td>
			               				 </tr>
			               				<%-- <tr>
			               				   <td>
			               					 <table>
			               					  <tr>
                        							  <td width="10%">&nbsp;</td>
                         							  <td width="70%">
                         							  <s:text name="Choose Branches" /> <font color="red">*</font><br/>
                         							   <s:textarea name="branchNames" rows="3" cols="50"></s:textarea>
                         							   <s:hidden name="branchSelected" id="branchSelected" />
                         							   <s:hidden name="optionMode" />
                         							  </td>
                         							  <td><input type="button" onclick="branchSelection()" name="popUpBtn" class="iButton rEdge" style="cursor:pointer;float:left;" value="..." maxlength="150"/></td>
                         						</tr>
                         					</table>	
			               					</td>
			               				 </tr>
			               				  --%>
			          						<s:if test='"new".equals(mode)'>
			         							<tr>
			            							<td height="2" bgcolor="#FFFFFF"></td>
			      							    </tr>
			        						    <tr>
			            							<td bgcolor="#FFFFFF" colspan="5"><table width="100%" border="0" cellspacing="0" cellpadding="0">
			                							<tr>
			                  								<td class="heading"><s:label key="user.login.creation" /></td>
			                							</tr>
			                							<tr>
			                  								<td class="bg"><table width="100%" border="0" align="center" cellpadding="4" cellspacing="0">
					               								 <tr>
																	<td width="5%">&nbsp;</td>
			                       									<td width="30%"><s:label key="user.new" /> <font color="red">*</font><br/>
			                         							  		 <s:textfield name="userId"  cssClass="inputBox" size="35"/></td>
																	<td width="30%"><s:label key="user.password" /> <font color="red">*</font> <br/>
			                         							  		 <s:password name="password" cssClass="inputBox" maxlength="20"/></td>
			                       									<td width="30%"><s:label key="user.rpassword" /> <font color="red">*</font><br/>
			                           									 <s:password name="repassword" cssClass="inputBox" maxlength="20"/></td>
			                           								<td width="5%">&nbsp;</td>
			                           							</tr>
			                           						</table></td>
	                           							</tr>
			           						 		</table></td>
			         							</tr>
			         						</s:if>
			          					<tr>
											<td colspan="5">
												<table width="100%" border="0" cellspacing="0" cellpadding="0">
													<tr>
														<td height="25" align="center" valign="middle">
<%-- 															<s:submit action="UnderwriterCreationContoller" name="submit1" cssClass="btn" value="Back" /> --%>
															<input type="button" onclick="fnBack();" class="btn" value="Back">
															<s:submit name="submit2" cssClass="btn" value="Submit" />
														</td>
													</tr>
												</table>
											</td>
										</tr>
										</s:else>
				          			</table>
				          			<s:hidden name="agencyCode"/>
									<s:hidden name="mode"/>
									<s:hidden name="mode1"/>
									<s:hidden name="uagencyCode"/>
									<s:hidden name="borganization"/>
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

