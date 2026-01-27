<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<%@ page isELIgnored="false"%>
<html>
	<head>
		<script>
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
				if(from=='edit'){
					document.underwriter.action ="viewUnderwriterMgm.action";
					document.underwriter.mode.value="edit";
					}
				else if(from=='list'){
					document.underwriter.action = "UnderwriterCreationContoller.action";
					}
				else if(from=='changePwd'){
					document.underwriter.action = "changePassUnderwriterMgm.action";
					document.underwriter.mode.value="changePass";
					}	
				else if(from=='include'){
					document.underwriter.action = "includeIssuerUnderwriterMgm.action";
					document.underwriter.type1.value="include";
					}
				else if(from=='exclude'){
					document.underwriter.action = "excludeIssuerUnderwriterMgm.action";
					document.underwriter.type1.value="exclude";
					}
				else if(from=='openCover')
					document.info.action = "opencoverOC.action";
				else if(from=='statistics')
					document.info.action = "statisticsRE.action";
				else
					document.underwriter.action = from+"BrokerMgm.action";
				document.underwriter.submit();
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
				if (password.length > 11) score++;
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
				document.underwriter.action ='UnderwriterCreationContoller.action';
				document.underwriter.submit();
			}
			
			function fnSubmit(){
				document.underwriter.action ='insertIssuerUnderwriterMgm.action';
				document.underwriter.submit();
			}
		
  		</script>
	</head>
	<body>
    <div style="margin:10px 0;"></div>
<!--     <div class="easyui-layout" style="width:900px;height:700px;"> -->
    <div class="easyui-layout" style="height:100vh;">
    	<s:if test='%{borganization!=null && !"".equals(borganization)}'>
		      <div data-options="region:'west',split:true" title="Options" style="width:150px;">
	            <div class="easyui-accordion" data-options="fit:true,border:false">
    				<s:if test='%{borganization!=null && borganization!=""}'>
		                <input type="button" class="btntab" value="View" onclick="fnCall('viewuser')"/><br/>
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
        <s:elseif test='"new".equals(optionMode)'>
        	 <div data-options="region:'center',title:'New Underwriter Creation',iconCls:'icon-ok'">
        </s:elseif>
        <s:else>
        	<div data-options="region:'center',title:'Underwriter Management',iconCls:'icon-ok'">
        </s:else>
            <div class="easyui-tabs" data-options="fit:true,border:false,plain:true" id="tabs">
                <div title="Enter Underwriter Info" style="padding:10px" >
                	<table width="100%">
                          <tr>
						    <td height="5"></td>
						  </tr>
						  <tr>
						    <td><table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#E5E5E5">
						      <tr>
						        <td bgcolor="#FFFFFF" style="padding:10px; background:#F8F8F8">
			   					 <s:form id="info" name="underwriter" method="post" theme="simple">
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
															<input type="button" onclick="fnsubmit('back','getABListUnderwriterMgm', this.form)" name="back" class="btn" value="Back" />
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
															<input type="button" onclick="fnsubmit('back','getABListUnderwriterMgm', this.form)" name="back" class="btn" value="Back" />
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
			                   						<td class="heading"><s:text name="New Underwriter Form" /> </td>
			               						</tr>
			                					<tr>	                                                 
			                 					   <td class="bg">
			                 					               <table width="100%" border="0" align="center" cellpadding="4" cellspacing="0">
					               								 <tr>
																	<td width="5%">&nbsp;</td>
			                       									<td><s:text name="User Name" /> <font color="red">*</font><br/>
			                         							  		 <s:textfield name="IssurName"  cssClass="inputBox" size="35"/></td>
																	<td><s:text name="Email Id" /> <font color="red">*</font> <br/>
			                         							  		<s:textfield name="emailId"  cssClass="inputBox" size="35"/></td>
			                       									<td><s:text name="Login Id" /> <font color="red">*</font><br/>
			                           									<s:textfield name="loginId"  cssClass="inputBox" size="35"/></td>
			                           								<td width="5%">&nbsp;</td>
			                           							</tr>
			                           							<tr>
																	<td width="5%">&nbsp;</td>
			                       									<td><s:text name="Core Login Id" /> <font color="red">*</font><br/>
			                         							  		 <s:textfield name="coreLoginId" maxlength="3" cssClass="inputBox" size="35"/></td>
			                         							  	<td>Password <font color="red">*</font><br/>
			                         							  		 <s:password name="password"  cssClass="inputBox" size="35"/></td>
			                         							  	<td><s:text name="Choose Products" /> <font color="red">*</font><br/>
			                         							  		<s:checkboxlist name="products" list="productList" listKey="PRODUCT_ID" listValue="PRODUCT_NAME"/></td>
			                         								<td width="5%">&nbsp;</td>
																</tr>
																 <tr>
										                            <td>&nbsp;</td>
										                            	<td>
										                            		<s:text name=" VAT Applicable" /><br />
											                           		 <s:radio list="#{'Y':'Yes','N':'No'}" name="vatApplicable" id="vatApplicable"  value='%{(vatApplicable==null || "".equals(vatApplicable))?"Y":vatApplicable}' disabled="#disable" onclick="editCustomerInfo();"/>
												                     	</td>
	                           									 </tr>
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
			                       									<td><s:label key="user.new" /> <font color="red">*</font><br/>
			                         							  		 <s:textfield name="userId"  cssClass="inputBox" size="35"/></td>
																	<td><s:label key="user.password" /> <font color="red">*</font> <br/>
			                         							  		 <s:password name="password" cssClass="inputBox" maxlength="20"/></td>
			                       									<td><s:label key="user.rpassword" /> <font color="red">*</font><br/>
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
<%-- 															<s:submit action="getABListUnderwriterMgm" name="submit1" cssClass="btn" value="Back" /> --%>
															<input type="button" onclick="fnBack();" class="btn" value="Cancel">
<%-- 															<s:submit name="submit2" cssClass="btn" value="Submit" action="insertIssuerUnderwriterMgm"/> --%>
															<input type="button" onclick="fnSubmit();" class="btn" value="Submit">
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
									<s:hidden name="type1"/>
									<s:hidden name="uagencyCode"/>
									<s:hidden name="borganization"/>
									<s:hidden name="optionMode"/>
				     			</s:form>
								</td>
							</tr>
						</table></td></tr>
                    </table>
                </div>
                <s:if test='!"new".equals(optionMode)'>
	                <div title="Password Change" style="padding:10px">
	                	<s:form id="newPwd" name="newPwd" method="post" theme="simple">
	   					 	<table width="100%" border="0" cellspacing="0" cellpadding="0">
						 		<tr><td class="heading"><s:label key="user.usermanagement"/></td></tr>
								<tr style="height: 25px"><td>&nbsp;</td></tr>
								<s:if test='"login".equals(mode1)'>
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
	               								<td width="30%"><s:password name="password" cssClass="input" maxlength="20" onkeyup="passwordStrength(this.value)"/></td>
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
	               								<td width="30%"><s:password name="repassword" cssClass="input" maxlength="20"/> </td>
	               								<td width="38%">&nbsp;</td>
	               							</tr>
	               							<tr><td>&nbsp;</td></tr>
	               							<tr>
	               								<td colspan="4" align="center"><s:submit action="getABListUserMgm" name="submit1" cssClass="btn" value=" Cancel " />
	               									<s:submit action="checkPwdUserMgm" name="submit1" cssClass="btn" value=" Submit " /></td>
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
	  				</s:form>
	            </div>
	            <div title="Broker Include/Exclude" style="padding:10px">
	            </div>
            </s:if>
            
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
	function branchSelection(){
	  var params = "?branchSelected="+document.getElementById('branchSelected').value==null?'':document.getElementById('branchSelected').value+"&formName=underwriter";
	  var URL ='${pageContext.request.contextPath}/branchSelectionUnderwriterMgm.do?branchSelected='+params;
	  return popUp(URL,'700','500');
	}
</script>

</html>
