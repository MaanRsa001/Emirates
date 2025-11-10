<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<%@ page isELIgnored="false"%>
<html>
	<head>
		<link href="<%=request.getContextPath()%>/cssbootstrap/footable-0.1.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
			function stopRKey(evt) { 
			  var evt = (evt) ? evt : ((event) ? event : null); 
			  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
			  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
			} 
			document.onkeypress = stopRKey;
		
			$(function(){
				var index = '<s:property value="index"/>';
				var t = $('#tabs');
				var tabs = t.tabs('tabs');
					t.tabs('select', tabs[index].panel('options').title);
			});
			
			function getMenu(val, id){
				if(val.length>=2){
					var searchBy=document.getElementById("searchBy1").value;
					postRequest('${pageContext.request.contextPath}/ajaxListAdmin.action?reqFrom='+id+'&searchBy='+searchBy+'&searchValue='+val, id);
				}
			}
			
			function getBroker(val, id){
				if(val.length>=2){
					var searchBy=document.getElementById("searchBy").value;
					postRequest('${pageContext.request.contextPath}/ajaxListAdmin.action?reqFrom='+id+'&searchBy='+searchBy+'&searchValue='+val, id);
				}
			}
			
			function fnsubmit(from, frm){
				if(from=='menuback'){
					document.menulist.action="adminMgtAdmin.action?index=1";
					document.menulist.submit();
				}else if(from=='menu'){
					document.menulist.action="menuSaveAdmin.action?index=1";
					document.menulist.submit();
				}else if(from=='adminback'){
					document.adminlist.action="adminMgtAdmin.action?index=0";
					document.adminlist.submit();
				}else if(from=='admin'){
					if(document.getElementById('pwd').value!='' && document.getElementById('mode').value!='new'){
						conf=false;
						conf = confirm("Are You want to change Password?");
						if(!conf)
							document.getElementById('pwd').value='';
					}
					document.adminlist.action="adminSaveAdmin.action?index=0";
					document.adminlist.submit();
				}
			}
			
			function forward(id, action, frm){
				if(frm=='menulist'){
					document.menulist.action=action+".action";
					document.getElementById('mid').value=id;
					document.getElementById('mode1').value='edit';
					document.menulist.submit();
				}else if(frm=='adminlist'){
					document.adminlist.action=action+".action";
					document.getElementById('mode').value='edit';
					document.getElementById('loginID').value=id;
					document.adminlist.submit();
				}
			}
		</script>
		<style type="text/css">
		th.sortable a {
			background-color: #337ab7;
		}
		</style>
	</head>
	<body>
	 <div style="margin:10px 0;"></div>
<!--    	 <div class="easyui-layout" style="width:900px;height:700px;"> -->
   	 <div class="easyui-layout" style="height:100vh;">
        <div data-options="region:'center',title:'Admin Management',iconCls:'icon-ok'">
            <div class="easyui-tabs" data-options="fit:true,border:false,plain:true" id="tabs">
                <div title="Admin User List" style="padding:10px" >
               		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#E5E5E5">
					      <tr>
					        <td bgcolor="#FFFFFF" style="padding:10px; background:#F8F8F8">
		   					 <s:form id="adminlist" name="adminlist" method="post" theme="simple">
		       					 <table width="100%" border="0" cellspacing="0" cellpadding="0">
		       					 	<tr><td></td></tr>
		       					 	<s:if test='"alist".equals(from)'>
		   						 		<tr><td class="heading"><s:text name="label.admin.existing.list" /></td></tr>
		   						 	</s:if>
		   						 	<s:elseif test='"aform".equals(from) && "new".equals(mode)'>
		   						 		<tr><td class="heading"><s:text name="label.admin.new" /></td></tr>
		   						 	</s:elseif>
		   						 	<s:elseif test='"aform".equals(from) && "edit".equals(mode)'>
		   						 		<tr><td class="heading"><s:text name="label.admin.edit" /></td></tr>
		   						 	</s:elseif>
									<tr style="height: 10px"><td>&nbsp;</td></tr>
		         					<tr>
		           						 <td bgcolor="#FFFFFF">
		           							<div id="admins">
	           						 			<table width="100%" align="center">
	           						 				<s:if test='"aform".equals(from)'>
	           						 					<tr><td  style="color:red;"><s:actionerror/> <s:actionmessage/> </td></tr>
	           						 				</s:if> 
	           						 				<s:if test='"alist".equals(from)'> 
														<tr><td>
															<table width="100%">
																<tr>
																	<td width="50%">
																		<table width="100%">
																			<tr>
																				<td width="33%">Search By:</td>
																				<td width="33%"><s:select name="searchBy" id="searchBy" cssClass="inputSelect" list="#{'LOGIN_ID':'Login Id', 'USERNAME':'User Name'}" headerKey="" headerValue="-Select-"/></td>
																				<td width="33%"><s:textfield name="searchVal" cssClass="inputBox" onkeyup="getBroker(this.value,'adminlists')"/></td>
																			</tr>
																		</table>
																	</td>
																	<td width="50%" align="right">
																		<a class="btn btn-sm btn-primary" title="new admin" href="editadminAdmin.action?mode=new" style="text-decoration: none;"><s:text name="admin.new.create"/></a>
																	</td>																	
																</tr>
															</table>
														</td></tr>
														<tr><td colspan="5" align="center">
																<div id="adminlists">
																	<display:table name="adminList" pagesize="10" requestURI="adminMgtAdmin.action?index=0" class="footable" uid="row2" id="record1" excludedParams="index">
																			<s:set var="myrow" value="#attr.record1"/>
																			<display:setProperty name="paging.banner.one_item_found" value="" />
																			<display:setProperty name="paging.banner.one_items_found" value="" />
																			<display:setProperty name="paging.banner.all_items_found" value="" />
																			<display:setProperty name="paging.banner.some_items_found" value="" />
																			<display:setProperty name="paging.banner.placement"	value="bottom" />
																			<display:setProperty name="paging.banner.onepage" value="" />
																			<display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="S.No" value="${record1_rowNum}"/>
																			<display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="Login ID" property="LOGIN_ID"/>
																			<display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="User Name" property="USERNAME"/>
																			<display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="UserType" property="userType"/>
																			<display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="Branch" property="BRANCH_NAME"/>
																			<display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="Status" property="STATUS1"/>
																			<display:column sortable="true" style="text-align:center;font-size:13px;height:30px;" title="Edit">
																				<a href="#" onclick="forward('<s:property value="#myrow.LOGIN_ID"/>','editadminAdmin','adminlist')">Edit</a>
																			</display:column>
																	</display:table>
																</div>
														</td></tr>
														<s:hidden name="loginID" id="loginID"/>
													</s:if>
													<tr><td height="5px;">
													<s:elseif test='"aform".equals(from)'>         
														<tr><td class="bg"><table width="100%" border="0" align="center" cellpadding="4" cellspacing="0">
					                      						<tr>
					                      							<td width="20%"></td>
					                        						<td width="25%" align="right"><s:text name="admin.user.type"/> <font color="red">*</font></td>
					                           						<td width="3%"></td>
					                        					    <td width="32%" align="left">
					                        					    <!--<s:select name="utype" id="utype" list="utypeList" headerKey="" headerValue="-Select-" listKey="CATEGORY_DETAIL_ID"  listValue="DETAIL_NAME" cssClass="input1"/>
					                        					    -->
					                        					    <s:textfield name="utype" id="utype" value="admin"  cssClass="inputSelect" cssStyle="width: 50%;" readonly="true" />
					                        					    </td>
					                            					<td width="20%"></td>
					                            				</tr>
					                      						<tr>
					                      							<td width="20%"></td>
					                        						<td width="25%" align="right"><s:text name="admin.user.name"/> <font color="red">*</font></td>
					                           						<td width="3%"></td>
					                        					    <td width="32%" align="left"><s:textfield name="uname" id="uname" cssClass="inputBox" cssStyle="width: 50%;"/></td>
					                            					<td width="20%"></td>
					                            				</tr>
					                            				<tr>
					                      							<td width="20%"></td>
					                        						<td width="25%" align="right"><s:text name="admin.user.login.Id"/> <font color="red">*</font></td>
					                           						<td width="3%"></td>
					                        					    <td width="32%" align="left">
					                        					    	<s:if test='"new".equals(mode)'>
					                        					    		<s:textfield name="loginID" id="loginID" cssClass="inputBox" cssStyle="width: 50%;"/>
					                        					    	</s:if>
					                        					    	<s:elseif test='"edit".equals(mode)'>
					                        					    		<s:property value="loginID"/><s:hidden name="loginID"/>
					                        					    	</s:elseif>
					                        					    </td>
					                            					<td width="20%"></td>
					                            				</tr>
					                            				<tr>
					                      							<td width="20%"></td>
					                      							<td width="25%" align="right"><s:text name="admin.user.pwd"/><s:if test='"new".equals(mode)'><font color="red">*</font></s:if></td>
					                           						<td width="3%"></td>
					                        					    <td width="32%" align="left"><s:password name="pwd" id="pwd" cssClass="inputBox" maxlength="20" cssStyle="width: 50%;"/></td>
					                            					<td width="20%"></td>
					                            				</tr>
					                            				<tr>
					                      							<td width="20%"></td>
					                      							<td width="25%" align="right"><s:text name="admin.user.branch"/> <font color="red">*</font></td>
					                           						<td width="3%"></td>
					                        					    <td width="32%" align="left"><s:property value="branch"/></td><s:hidden name="branch"/>
					                            					<td width="20%"></td>
					                            				</tr>
					                            				<%--<tr>
					                      							<td width="20%"></td>
					                        						<td width="25%" align="right"><s:text name="admin.user.product"/> <font color="red">*</font></td>
					                           						<td width="3%"></td>
					                        					    <td width="32%" align="left"><s:radio name="product" id="product" list="#{'Marine':'Marine', 'Home':'Home / Travel'}" cssClass="input"/></td>
					                            					<td width="20%"></td>
					                            				</tr>
					                            				--%><tr>
					                      							<td width="20%"></td>
					                        						<td width="25%" align="right"><s:text name="admin.user.product.select"/> <font color="red">*</font></td>
					                           						<td width="3%"></td>
					                        					    <td width="42%" align="left"><s:checkboxlist name="productID" id="productID" list="productList" listKey="PRODUCT_ID" listValue="PRODUCT_NAME"  cssClass="inputSelect"/></td>
					                            					<td width="10%"></td>
					                            				</tr>
		                       					   				<tr>
					                      							<td width="20%"></td>
					                        						<td width="25%" align="right"><s:text name="admin.user.menu.select"/> <font color="red">*</font></td>
					                           						<td width="3%"></td>
					                        					    <td width="32%" align="left"><s:textarea name="mid" id="mid" cssClass="inputBoxA" rows="3" cssStyle="width: 50%; float:left;"/><input class="btn" value="..." style="float:left;" type="button" name="menu" onclick="return popUp('${pageContext.request.contextPath}/menuSelectionAdmin.action','600','500')"/></td>
					                            					<td width="20%"></td>
					                            				</tr>
					                            				<tr>
					                      							<td width="20%"></td>
					                        						<td width="25%" align="right"><s:text name="admin.user.broker.select"/> <font color="red">*</font></td>
					                           						<td width="3%"></td>
					                        					    <td width="32%" align="left"><s:textarea name="broker" id="broker" cssClass="inputBoxA" rows="3" cssStyle="width: 50%; float:left;" /><input class="btn" style="float:left;" value="..." type="button" name="menu" onclick="return popUp('${pageContext.request.contextPath}/brokerSelectionAdmin.action','600','500')"/></td>
					                            					<td width="20%"></td>
					                            				</tr>
					                            				<tr>
					                      							<td width="20%"></td>
					                        						<td width="25%" align="right"><s:text name="admin.user.mail"/> <font color="red">*</font></td>
					                           						<td width="3%"></td>
					                        					    <td width="32%" align="left"><s:textfield name="email" id="email" cssClass="inputBox" cssStyle="width: 50%;" /></td>
					                            					<td width="20%"></td>
					                            				</tr>
					                            				<tr>
					                      							<td width="20%"></td>
					                        						<td width="25%" align="right"><s:text name="admin.user.status"/> <font color="red">*</font></td>
					                           						<td width="3%"></td>
					                        					    <td width="32%" align="left"><s:select name="status" list="#{'Y':'Active','N':'Deactive','D':'Delete','L':'Lock'}" headerKey="" headerValue="-Select" cssClass="inputSelect" cssStyle="width: 52%;"/></td>
					                            					<td width="20%"></td>
					                            				</tr>
					                      					   <tr><td colspan="5"></td></tr>
				               						   	</table></td></tr>
				               						 	<tr><td height="2" bgcolor="#FFFFFF"></td></tr>
				        						   		<tr><td>
																<table width="100%" border="0" cellspacing="0" cellpadding="0">
																	<tr>
																		<td height="25" align="center" valign="middle">
																			<input type="button" name="submit2" class="btn" value="Back" onclick="fnsubmit('adminback',this.form)"/>
																			<input type="button" name="submit1" class="btn" value="Submit" onclick="fnsubmit('admin',this.form)"/>&nbsp;&nbsp;&nbsp;&nbsp;
																		</td>
																	</tr>
																</table>
															</td>
														</tr>
													</s:elseif>
													<s:elseif test='"addSuccess".equals(from)'>
														<tr><td height="150px;" align="center">
																<s:text name="admin.new.add.success"/>
														</td></tr>
														<tr><td>
																<table width="100%" border="0" cellspacing="0" cellpadding="0">
																	<tr>
																		<td height="25" align="center" valign="middle">
																			<input type="button" name="submit1" class="btn" value="Back" onclick="fnsubmit('adminback',this.form)"/>
																		</td>
																	</tr>
																</table>
															</td>
														</tr>
													</s:elseif>
													<s:elseif test='"updateSuccess".equals(from)'>
														<tr><td height="150px;" align="center">
																<s:text name="admin.exist.update.success"/>
														</td></tr>
														<tr><td>
																<table width="100%" border="0" cellspacing="0" cellpadding="0">
																	<tr>
																		<td height="25" align="center" valign="middle">
																			<input type="button" name="submit1" class="btn" value="Back" onclick="fnsubmit('adminback',this.form)"/>
																		</td>
																	</tr>
																</table>
															</td>
														</tr>
													</s:elseif>
												</table>
											</div>
									</td></tr>
                   				</table>
                   				<s:hidden name="mode" id="mode"/>
                   				<s:hidden name="from"/>
                   				<s:hidden name="mode1"/>
                   				<s:hidden name="from1"/>
                   			</s:form>
                   		</td>
                   	</tr>
                   </table>
               </div>
               <%--
               <div title="Menu Master List" style="padding:10px" >
                	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#E5E5E5">
					      <tr>
					        <td bgcolor="#FFFFFF" style="padding:10px; background:#F8F8F8">
		   					 <s:form id="menulist" name="menulist" method="post" theme="simple">
		       					 <table width="100%" border="0" cellspacing="0" cellpadding="0">
		       					 	<tr><td></td></tr>
		   						 	<s:if test='"mlist".equals(from1)'> 
		   						 		<tr><td class="heading"><s:text name="label.menu.existing.list" /></td></tr>
		   						 	</s:if>
		   						 	<s:elseif test='"mform".equals(from1) && "new".equals(mode1)'>
		   						 		<tr><td class="heading"><s:text name="label.menu.new" /></td></tr>
		   						 	</s:elseif>
		   						 	<s:elseif test='"mform".equals(from1) && "edit".equals(mode1)'>
		   						 		<tr><td class="heading"><s:text name="label.menu.edit" /></td></tr>
		   						 	</s:elseif>
									<tr style="height: 10px"><td>&nbsp;</td></tr>
		         					<tr>
		           						 <td bgcolor="#FFFFFF">
		           							<div id="menus">
	           						 			<table width="100%" align="center">
	           						 				<s:if test='"mform".equals(from1)'>
	           						 					<tr><td  style="color:red;"><s:actionerror/> <s:actionmessage/> </td></tr>
	           						 				</s:if> 
	           						 				<s:if test='"mlist".equals(from1)'> 
														<tr><td>
															<table width="100%">
																<tr>
																	<td width="50%">
																		<table width="100%">
																			<tr>																				
																				<td width="33%">Search By:</td>
																				<td width="33%"><s:select name="searchBy" id="searchBy1" list="#{'DETAIL_NAME':'Menu Name'}" headerKey="" cssClass="inputSelect" headerValue="-Select-"/></td>
																				<td width="33%"><s:textfield name="searchVal" onkeyup="getMenu(this.value,'menulists')" cssClass="inputBox" /></td>
																			</tr>
																		</table>
																	</td>
																	<td width="50%" align="right">
																		<a class="btn btn-sm btn-primary" title="New Menu" href="editMenuAdmin.action?mode1=new"><s:text name="amenu.new.create"/></a>
																	</td>																	
																</tr>
															</table>
														</td></tr>
														<tr><td colspan="5" align="center">
																<div id="menulists">
																	<display:table name="menuList" pagesize="10" requestURI="adminMgtAdmin.action?index=1" class="footable" uid="row" id="record" excludedParams="index">
																		<s:set name="myrow" value="#attr.record"/>
																		<display:setProperty name="paging.banner.one_item_found" value="" />
																		<display:setProperty name="paging.banner.one_items_found" value="" />
																		<display:setProperty name="paging.banner.all_items_found" value="" />
																		<display:setProperty name="paging.banner.some_items_found" value="" />
																		<display:setProperty name="paging.banner.placement"	value="bottom" />
																		<display:setProperty name="paging.banner.onepage" value="" />
																		<display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="S.No" value="${record_rowNum}"/>
																		<display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="Menu Name" property="DETAIL_NAME"/>
																		<display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="Menu URL Path" property="REMARKS"/>
																		<display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="Parent Menu" property="PARENT_MENU_NAME"/>
																		<display:column sortable="true" style="text-align:center;font-size:13px;height:30px;" title="Status" property="STATUS1"/>
																		<display:column sortable="true" style="text-align:center;font-size:13px;height:30px;" title="Edit">
																			<a class="btn btn-sm btn-primary" style="text-decoration: none;" href="#" onclick="forward('<s:property value="#myrow.CATEGORY_DETAIL_ID"/>','editMenuAdmin','menulist')">Edit</a>
																		</display:column>
																	</display:table>
																</div>
														</td></tr>
													</s:if>
													<tr><td height="5px;">
													<s:elseif test='"mform".equals(from1)'>         
														<tr><td class="bg"><table width="100%" border="0" align="center" cellpadding="4" cellspacing="0">
					                      						<tr>
					                      							<td width="20%"></td>
					                        						<td width="25%" align="right"><s:text name="menu.name"/> <font color="red">*</font></td>
					                           						<td width="3%"></td>
					                        					    <td width="32%" align="left"><s:textfield name="mname" id="mname" cssClass="inputBox" cssStyle="width: 50%;" size="35"/></td>
					                            					<td width="20%"></td>
					                            				</tr>
					                            				<tr>
					                      							<td width="20%"></td>
					                        						<td width="25%" align="right"><s:text name="menu.url.path"/> <font color="red">*</font></td>
					                           						<td width="3%"></td>
					                        					    <td width="32%" align="left"><s:textfield name="urlPath" id="urlPath" cssClass="inputBox" cssStyle="width: 50%;" size="35"/></td>
					                            					<td width="20%"></td>
					                            				</tr>
					                            				<tr>
					                      							<td width="20%"></td>
					                      							<td width="25%" align="right"><s:text name="menu.parent.menu"/> <font color="red">*</font></td>
					                           						<td width="3%"></td>
					                        					    <td width="32%" align="left"><s:select name="parent" id="parent" list="menuList" listKey="CATEGORY_DETAIL_ID" listValue="DETAIL_NAME" headerKey="" headerValue="-Select-" cssClass="inputSelect" cssStyle="width: 52%;"/></td>
					                            					<td width="20%"></td>
					                            				</tr>
					                            				<tr>
					                      							<td width="20%"></td>
					                        						<td width="25%" align="right"><s:text name="menu.status"/> <font color="red">*</font></td>
					                           						<td width="3%"></td>
					                        					    <td width="32%" align="left"><s:radio name="status" id="status" list="#{'Y':'Active', 'N':'InActive'}" cssClass="input"/></td>
					                            					<td width="20%"></td>
					                            				</tr>
					                      					   <tr><td colspan="5"></td></tr>
				               						   	</table></td></tr>
				               						 	<tr><td height="2" bgcolor="#FFFFFF"></td></tr>
				        						   		<tr><td>
																<table width="100%" border="0" cellspacing="0" cellpadding="0">
																	<tr>
																		<td height="25" align="center" valign="middle">
																			<input type="button" name="submit4" class="btn" value="Back" onclick="fnsubmit('menuback',this.form)"/>
																			<input type="button" name="submit3" class="btn" value="Submit" onclick="fnsubmit('menu',this.form)"/>&nbsp;&nbsp;&nbsp;&nbsp;
																		</td>
																	</tr>
																</table>
															</td>
														</tr>
													</s:elseif>
													<s:elseif test='"addSuccess".equals(from1)'>
														<tr><td height="150px;" align="center">
																<s:text name="menu.new.add.success"/>
														</td></tr>
														<tr><td>
																<table width="100%" border="0" cellspacing="0" cellpadding="0">
																	<tr>
																		<td height="25" align="center" valign="middle">
																			<input type="button" name="back1" class="btn" value="Back" onclick="fnsubmit('menuback',this.form)"/>
																		</td>
																	</tr>
																</table>
															</td>
														</tr>
													</s:elseif>
													<s:elseif test='"updateSuccess".equals(from1)'>
														<tr><td height="150px;" align="center">productIDproductID
																<s:text name="menu.exist.update.success"/>
														</td></tr>
														<tr><td>
																<table width="100%" border="0" cellspacing="0" cellpadding="0">
																	<tr>
																		<td height="25" align="center" valign="middle">
																			<input type="button" name="back2" class="btn" value="Back" onclick="fnsubmit('menuback',this.form)"/>
																		</td>
																	</tr>
																</table>
															</td>
														</tr>
													</s:elseif>
												</table>
											</div>
									</td></tr>
								<s:hidden name="mid" id="mid"/>
                   				<s:hidden name="mode"/>
                   				<s:hidden name="from"/>
                   				<s:hidden name="mode1" id="mode1"/>
                   				<s:hidden name="from1"/>
                   				</table>
                   			</s:form>
                   		</td>
                   	</tr>
                </table>
            </div>
            --%>
        </div>
    	</div>
    	</div>
	</body>
</html>
