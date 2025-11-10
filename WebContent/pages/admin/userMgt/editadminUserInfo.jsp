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
			  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
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
				else if(from=='edituser')
					document.info.action = "editUserMgm.action?mode1=broker&mode=edit";
				else if(from=='viewuser')
					document.info.action = "viewUserMgm.action?mode1=broker";
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
			function passwordStrength(password){
				var desc = new Array();
				desc[0] = "Very Weak";
				desc[1] = "Weak";
				desc[2] = "Better";
				desc[3] = "Medium";
				desc[4] = "Strong";
				desc[5] = "Strongest";
				var score   = 0;
				if (password.length > 6) score++;
				if ( ( password.match(/[a-z]/) ) && ( password.match(/[A-Z]/)) ) score++;
				if ( password.match(/\d+/)) score++;
				if ( password.match(/.[@,#,$,%]/))score++;
				if ( (password.length > 10) && (password.match(/.[@,#,$,%]/)) && (password.match(/[a-z]/) ) && (password.match(/[A-Z]/)) && (password.match(/\d+/)))score++;
				document.getElementById("passwordDescription").innerHTML = desc[score];
				document.getElementById("passwordStrength").className = "strength" + score;
			}
			$(function(){
				var index = '<s:property value="index"/>';
				var t = $('#tabs');
				var tabs = t.tabs('tabs');
					t.tabs('select', tabs[index].panel('options').title);
			});
		
			function fnBack(){
				document.info.action ='UserCreationContoller.action';
				document.info.submit();
			}
			
			function addProduct(){
				document.form1.action ='addProductUserMgm.action';
				document.form1.submit();
			}
			
			function fnSubmitPage(value){
				document.newPwd.action =value;
				document.newPwd.submit();
			}
			
  		</script>  		
	</head>
	<body>
    <div style="margin:10px 0;"></div>
    <div class="easyui-layout" style="height:100vh;">
    	<s:if test='%{borganization!=null && !"".equals(borganization)}'>
		      <div data-options="region:'west',split:true" title="Options" style="width:150px;">
	            <div class="easyui-accordion" data-options="fit:true,border:false">
    				<s:if test='%{borganization!=null && borganization!=""}'>
		                <input type="button" class="btntab" value="View" onclick="fnCall('viewuser')"/><br/>
		                <input type="button" class="btntab" value="Change Password" onclick="fnCall('changePwd')"/><br/>
		                <input type="button" class="btntab" value="User Details" onclick="fnCall('userDetail')"/><br/>
		                <input type="button" class="btntab" value="Customer Details" onclick="fnCall('customerDetail')"/><br/>
		                <input type="button" class="btntab" value="OpenCover" onclick="fnCall('openCover')"/><br/>
		                <input type="button" class="btntab" value="Referral" onclick="fnCall('referal')"/><br/>
		                <input type="button" class="btntab" value="Statistics" onclick="fnCall('statistics')"/><br/>
		            </s:if>
		            <s:else>
		            	 <input type="button" class="btntab" value="View" onclick="fnCall('viewuser')"/><br/>
		            	 <input type="button" class="btntab" value="Customer Details" onclick="fnCall('customerDetail')"/><br/>
		            </s:else>
	            </div>
		      </div>
		 </s:if>
		<s:if test='%{borganization!=null && borganization!=""}'>
		     <div data-options="region:'center',title:'<s:property value="borganization"/>(<s:property value="agencyCode"/>)',iconCls:'icon-ok'">
		</s:if>
        <s:elseif test='"new".equals(mode)'>
        	 <div data-options="region:'center',title:'New User Creation',iconCls:'icon-ok'">
        </s:elseif>
        <s:else>
        	<div data-options="region:'center',title:'User Management',iconCls:'icon-ok'">
        </s:else>
            <div class="easyui-tabs" data-options="fit:true,border:false,plain:true" id="tabs">
                <div title="Edit User Info" style="padding:10px" >
                	<table width="100%">
                          <tr>
						    <td height="5"></td>
						  </tr>
						  <tr>
						    <td><table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#E5E5E5">
						      <tr>
						        <td bgcolor="#FFFFFF" style="padding:10px; background:#F8F8F8">
			   					 <s:form id="info" name="info" method="post" action="newUserUserMgm" theme="simple">
			       					 <table width="100%" border="0" cellspacing="0" cellpadding="0">
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
											<td class="heading" colspan="5"><s:label key="user.usermanagement" /></td>
							 		 	</tr>
							 		 	<tr style="height: 10px"><td>&nbsp;</td></tr>
							 		 	<s:if test='!"product".equals(mode1) && !"login".equals(mode1)'>
				     						<tr>
					         					<td colspan="5" style="color:red;"><s:actionerror/> <s:actionmessage/> </td>
					       					</tr>
					       				</s:if>
					       				<s:if test='!"new".equals(mode)'>
				       						<tr>
												<td colspan="5" align="right">
													<table width="50%">
														<tr>
															<td width="50%" align="right"><s:text name="user.status"/></td>
															<td width="50%"><s:select name="ustatus" cssClass="inputSelect" list="#{'Y':'Active','N':'Deactive','D':'Delete','L':'Lock'}" headerKey="" headerValue="-Select"/></td>
														</tr>
													</table>
												</td>
											</tr>
										</s:if>
										<tr style="height: 10px"><td>&nbsp;</td></tr>
			         					<tr>
			           						 <td bgcolor="#FFFFFF" colspan="5"><table width="100%" border="0" cellspacing="0" cellpadding="0">
			               					 	<tr>
			                   						<td class="heading"><s:label  key="user.contactpersonInfo" /></td>
			               						</tr>
			                					<tr>	                                                 
			                 						<td class="bg"><table width="100%" border="0" align="center" cellpadding="4" cellspacing="0">
			                 							<s:if test='"edit".equals(mode)'>
				                      						<tr>
				                      						   <td width="5%"></td>
				                      						   <td  width="30%" align="left"><s:label key="user.broker" /> <br/>
				                           							<b> <s:if test='"".equals(borganization) || borganization==null'>
				                           									<s:select name="broker" list="brokerList" listKey="AGENCY_CODE" listValue="COMPANY_NAME" cssClass="inputSelect" headerKey="" headerValue="-Select-"/>
				                           								</s:if>
				                           								<s:else>
				                           									<s:property value="borganization"/>
				                           								</s:else></b></td>
				                       						   <td  width="30%" align="left"><s:label key="user.type" /> <br/>
				                           						   <b> <s:property value="utype"/></b></td>
				                            				   <td  width="30%" align="left"><s:label key="user.userId" /> <br/>
				                           							<b><s:property value="userId" /></b>
				                           							 <s:hidden name="userId" id="userId"/></td>
				                           					   <td width="5%"/>
				                       						</tr>
			                       						</s:if>
			                       						<s:else>
			                       							<tr>
				                      						   <td width="5%"></td>
				                      						   <td width="30%" align="left"><s:label key="user.broker" /><font color="red">*</font><br/>
				                           							<b> <s:if test='"".equals(borganization) || borganization==null'>
				                           									<s:select name="broker" list="brokerList" listKey="AGENCY_CODE" listValue="CONTACT_PERSON" cssClass="inputSelect" headerKey="" headerValue="-Select-"/>
				                           								</s:if>
				                           								<s:else>
				                           									<s:property value="borganization"/>
				                           								</s:else>
				                           							</b></td>
				                       						   <td width="30%" align="left"><s:label key="user.type" /> <font color="red">*</font><br/>
				                           						    <s:textfield name="utype" cssClass="inputBox" size="35" disabled="true"/></td>
				                            				   <td width="30%" align="left"></td>
				                           					   <td width="5%"/>
				                       						</tr>
			                       						</s:else>
			                       						<tr>
			                       							 <td width="5%"></td>
			                       							 <td><s:label key="user.title" /> <br/>
			                           							<s:select name="utitle" id="title" list="titlesInfo" cssClass="inputSelect" listKey="TITLE_ID" listValue="TITLE_NAME"  headerKey="" headerValue="--select--"/></td>
			                       							 <td><s:label key="user.firstname" /> <font color="red">*</font><br/>
			                         							 <s:textfield name="ufirstname" cssClass="inputBox" size="35" maxlength="30"  /></td> 
			                         						 <td><s:label key="user.lastname" /> <br/>
			                         							  <s:textfield name="ulastname" cssClass="inputBox" size="35" maxlength="30"/></td>
			                         						 <td width="5%"/>
			                       						</tr>
			                       						<tr>
			                       							 <td width="5%"></td>
			                       							 <td><s:label key="user.gender" /> <br/>
																<s:radio name="ugender" id="ugender" list="#{'M':'Male','F':'Female'}" /></td>
															 <td><s:label key="user.nationality" /> <font color="red">*</font><br/>
																 <s:select name="unationality" list="nationalitiesInfo" listKey="COUNTRY_ID" listValue="NATIONALITY_NAME" headerKey="" headerValue="-select-" cssClass="inputSelect" /></td>
															 <td><s:label key="user.dob" /> :<br/>
															 		<div class="inputAppend"><sj:datepicker id="udob" name="udob" cssClass="inputBox1" displayFormat="dd/mm/yy" changeMonth="true" changeYear="true" readonly="true" cssStyle="width:80%; border: none; background-color: #ffffff;"/></div>																  
															 <td width="5%"/></td>
			                       						</tr>
			                       						<tr>
															<td width="5%">&nbsp;</td>
	                       									<td><s:label key="user.occupation" /> <br/>
	                         							  		 <s:textfield name="uoccupation"  cssClass="inputBox" size="35" maxlength="30"/></td>
			                       							 <td><s:label key="user.address1" /> <br/>
			                           							 <s:textfield name="uaddress1" id="address1" cssClass="inputBox" size="35" maxlength="50"/></td>
			                           					     <td><s:label key="user.address2" /> <br/>
			                           							 <s:textfield name="uaddress2" id="address2" cssClass="inputBox" size="35" maxlength="50"/></td> 
	                           								<td width="5%">&nbsp;</td>
	                           							</tr>
			                       						<tr>
															 <td width="5%"></td>
			                       							 <td><s:label key="user.city" /> <font color="red">*</font> <br/>
			                         							<s:select name="ucity" id="emirate" cssClass="inputSelect" list="emiratesInfo" listKey="CITY_NAME" listValue="CITY_NAME"  headerKey="" headerValue="--select--" /> </td>
															 <td><s:label key="user.country" /> <font color="red">*</font> <br/>
			                         							 <s:select name="ucountry" id="country" list="countriesInfo" cssClass="inputSelect" listKey="COUNTRY_ID" listValue="COUNTRY_NAME"  headerKey="" headerValue="--select--" /></td>
			                      							 <td><s:label key="user.pobox" /> <font color="red">*</font> <br/>
			                         							 <s:textfield name="upobox" id="pobox" cssClass="inputBox" size="35" maxlength="6"/></td>
															 <td width="5%"/>
			                         					</tr>
			                      						<tr>
			                      							<td width="5%"></td>
			                      							<td><s:label key="user.telephone" /> <br/>
			                         							   <s:textfield name="uphone" id="telephone"  cssClass="inputBox" size="35" maxlength="15"/></td>
			                         						<td><s:label key="user.mobile" /> <br/>
	                         							  		 <s:textfield name="umobile" cssClass="inputBox" size="35" maxlength="20"/></td>
	                       									<td><s:label key="user.email" /> <font color="red">*</font><br/>
	                           									 <s:textfield name="uemail" cssClass="inputBox" size="50" maxlength="50"/></td>
			                         						<td width="5%"/>
			                      					   </tr>
			                      					   <tr>
			                      							<td width="5%"></td>
			                         						<td><s:label key="user.fax" /> <br/>
			                         							<s:textfield name="ufax" id="ufax" cssClass="inputBox" size="35" maxlength="10"/></td>
	                       									<td></td>
			                       							<td></td>
			                         						<td width="5%"/>
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
			                  								<td class="heading"><s:label key="user.login.creation" /></td>
			                							</tr>
			                							<tr>
			                  								<td class="bg"><table width="100%" border="0" align="center" cellpadding="4" cellspacing="0">
					               								 <tr>
																	<td width="5%">&nbsp;</td>
			                       									<td width="30%"><s:label key="user.new" /> <font color="red">*</font><br/>
			                         							  		 <s:textfield name="userId"  cssClass="inputBox" size="35" maxlength="15"/></td>
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
<%-- 															<s:submit action="getABListUserMgm" name="submit1" cssClass="btn" value="Back" /> --%>
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
									<s:hidden name="login_Id" value="%{#ulogin_Id}"/>
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
						  <s:if test='"product".equals(mode1)'>
     						<tr><td  style="color:red;"><s:actionerror/> <s:actionmessage/></td></tr>
	       				 </s:if>
						  <tr>
						    <td><table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#E5E5E5">
						      <tr>
						        <td bgcolor="#FFFFFF" style="padding:10px; background:#F8F8F8">
			   					 <s:form id="form1" name="form1" method="post" action="" theme="simple">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr class="bottomtext">
											<td class="heading">
												<s:label key="user.product" />
											</td>
										</tr>
						     			<tr style="height: 10px"><td>&nbsp;</td></tr>
						     			<s:if test='"updatesuccess".equals(display)'>
						     				<tr height="200px"><td><s:label key="product.modify.success" /></td></tr>
						     				<tr><td align="center"><input type="button" name="submit1" class="btn" value="Back" onclick="fnsubmit('back','getABListUserMgm', this.form)"/></td> </tr>
						     			</s:if>
						     			<s:elseif test='"newsuccess".equals(display)'>
						     				<tr height="200px"><td><s:label key="product.new.success" /></td></tr>
						     				<tr><td align="center"><input type="button" name="submit1" class="btn" value="Back" onclick="fnsubmit('back','getABListUserMgm', this.form)"/></td> </tr>
						     			</s:elseif>
						     			<s:else>
											<tr align="center">
												<td>
													<s:if test='commisionDetails.size()>0'>
														<table border="1">
															<tr>
																<th class="heading"></th>
																<th class="heading">Products</th>
																<th class="heading">Special Discount</th>
																<th class="heading">Sum Insured (AED)</th>
															<!--<th class="heading">Payment Gateway & Receipt Required</th>
															-->
																<th class="heading">Provision for Credit</th>
																
															</tr>
<!-- 															<div id="commisionDetailss" > -->
																<s:iterator value="commisionDetails" var="cdet"  status="status">
																	<tr>
																			<td align="center"> <s:checkbox name="product[%{#status.index}]" value='%{#cdet.PRODUCT=="Y"?true:false}'/></td>
																			<td align="center"><s:property value="%{#cdet.uproductName}"/></td><s:hidden name="uproductId[%{#status.index}]" value="%{#cdet.uproductId}"/><s:hidden name="uproductName[%{#status.index}]" value="%{#cdet.uproductName}"/>
																		<s:if test='%{uproductId!="11"}'>
																			<td align="center"><s:textfield name="specialDis[%{#status.index}]" value="%{#cdet.specialDis}" cssClass="inputBox" maxlength="15"/></td>
																			<td align="center"><s:textfield name="insEndLimit[%{#status.index}]" value="%{#cdet.insEndLimit}" cssClass="inputBox" maxlength="15"/></td>
																		</s:if>
																		<s:else>
																			<td colspan="2" align="center"><input type="button" name="opencov" value="Open Cover Certificate"  onclick="return popUp('${pageContext.request.contextPath}/getOCCertificateUserMgm.action?uagencyCode=${uagencyCode}&agencyCode=${agencyCode}',650,650)"/></td>
																			<s:hidden name="specialDis[%{#status.index}]" value="%{#cdet.specialDis}"/><s:hidden name="insEndLimit[%{#status.index}]" value="%{#cdet.insEndLimit}"/>
																			<s:hidden name="openCover" id="openCover" value="%{#cdet.open_cover_no}"/>
																		</s:else>
																			<!--<td align="center">
																			<s:radio name="receipt[%{#status.index}]" list="#{'Y':'Yes','N':'No'}" value="%{#cdet.receipt}"/>
																			</td>-->
																			<s:hidden name="receipt[%{#status.index}]" value="N"></s:hidden>
																			<%--<s:hidden name="freight[%{#status.index}]" value="N"></s:hidden>--%>
																			<td align="center"><s:radio name="freight[%{#status.index}]" list="#{'Y':'Yes','N':'No'}" value="%{#cdet.freight}"/></td>																			
																	</tr>
																</s:iterator>
<!-- 															</div> -->
														</table>
															<tr height="30px">
																<td height="25" align="center" valign="middle">
																	<input type="button"  name="submit1" class="btn" value="Back" onclick="fnsubmit('back','getABListUserMgm', this.form)"/>
																	<input type="button" class="btn" onclick="addProduct();" value="Submit">
<%-- 																	<s:submit name="submit3" cssClass="btn" value="Submit" action="addProductUserMgm"/> --%>
																	
																</td>
															</tr>
														
													</s:if>
													<s:else> No products found</s:else>
												</td>
											</tr>
										</s:else>
									</table>
									<s:hidden name="agencyCode"/>
									<s:hidden name="borganization"/>
									<s:hidden name="uagencyCode"/>
									<s:hidden name="mode1"/>
									<s:hidden name="login_Id" value="%{#ulogin_Id}"/>
				     			</s:form>
								</td>
							</tr>
						</table></td></tr>
                    </table>
                </div>
                <s:if test='!"new".equals(mode)'>
	                <div title="Password Change" style="padding:10px">
	                	<s:form id="newPwd" name="newPwd" method="post" theme="simple">
	   					 	<table width="100%" border="0" cellspacing="0" cellpadding="0">
						 		<tr><td class="heading"><s:label key="user.usermanagement"/></td></tr>
								<tr style="height: 25px"><td>&nbsp;</td></tr>
								<s:if test='"login".equals(mode1) || "passwordsuccess".equalsIgnoreCase(display)'>
					 				<tr><td  style="color:red;"><s:actionerror/> <s:actionmessage/> </td></tr>
					 			</s:if>
	  							<tr>                   
	          						<td class="bg">
	          							<table width="100%" border="0" align="center" cellpadding="4" cellspacing="0">
	               							<tr>
	               								<td width="2%">&nbsp;</td>
	                 							<td width="30%" align="right"> <s:label key="user.new"/> : </td>
	                 							<td width="30%"><s:property value="ulogin_Id"/>( <s:property value="uagencyCode"/> ) </td>
	                  							<td width="38%">&nbsp;</td>
	               							</tr>
	               							<tr>
	               								<td width="2%">&nbsp;</td>
	               								<td width="30%" align="right"><s:label key="broker.nameNpassword"/> : </td>
	               								<td width="30%"><s:password name="password" cssClass="inputBox" maxlength="20" onkeyup="passwordStrength(this.value)"/></td>
	               								<td width="38%"><s:label key="broker.passwordmessage"/> </td>
	               							</tr>
	               							<tr align="center">
	                     						<td > &nbsp;</td>
	                     						<td width="30%">&nbsp;</td>
	                      						<td colspan="2" align="left" style="padding-left: 10px;"><div id="passwordDescription">Password not entered</div><div id="passwordStrength" class="strength0"></div></td>
	                  						</tr>
	               							<tr>
	               								<td width="2%">&nbsp;</td>
	               								<td width="30%" align="right"><s:label key="user.rpassword"/> : </td>
	               								<td width="30%"><s:password name="repassword" cssClass="inputBox" maxlength="20"/> </td>
	               								<td width="38%">&nbsp;</td>
	               							</tr>
	               							<tr><td>&nbsp;</td></tr>
	               							<tr>
	               								<td colspan="4" align="center">
<%-- 	               									<s:submit action="getABListUserMgm" name="submit1" cssClass="btn" value=" Cancel " /> --%>
	               									<input type="button" onclick="fnSubmitPage('getABListUserMgm.action');" class="btn" value="Cancel">
<%-- 	               									<s:submit action="checkPwdUserMgm" name="submit1" cssClass="btn" value=" Submit " /> --%>
	               									<input type="button" onclick="fnSubmitPage('checkPwdUserMgm.action');" class="btn" value="Submit">
               									</td>
	               							</tr>
									</table>
								</td>
							</tr>
	       				</table>
	       				<s:hidden name="index" value="2"/>
						<s:hidden name="borganization"/>
						<s:hidden name="agencyCode"/>
						<s:hidden name="firstname"/>
						<s:hidden name="uagencyCode"/>
						<s:hidden name="mode1"/>
						<s:hidden name="login_Id" value="%{#ulogin_Id}"/>
	  				</s:form>
	            </div>
            </s:if>
        </div>
        </div>
        </div>
    </div>
</body>
<script>
	function forward1(agcode, id, value, mode1){
		postRequest('${pageContext.request.contextPath}/getUserAjaxUserMgm.action?reqFrom='+id+'&agencyCode='+agcode+'&productID='+value+'&mode1='+mode1, id);
   	}
   	
	function fnsubmit(from, action, frm){
		frm.action=action+".action";
		frm.submit();
	}
</script>

</html>
