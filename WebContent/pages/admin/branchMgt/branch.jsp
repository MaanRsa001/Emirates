<%@ page isELIgnored="false" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<%@ taglib uri="/struts-jquery-tags" prefix="sj"%>
<!DOCTYPE HTML>
<html>
<head>
	<sj:head jqueryui="true" jquerytheme="start" />
	<link href="<%=request.getContextPath()%>/cssbootstrap/footable-0.1.css" rel="stylesheet" type="text/css" />
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">		
	<style type="text/css">
	th.sortable a {
		background-color: #337ab7;
	}
	</style>
	<s:if test='"edit".equals(display)'>
	<script type="text/javascript">
		function stopRKey(evt) { 
		  var evt = (evt) ? evt : ((event) ? event : null); 
		  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
		 if ((evt.keyCode == 13) && ((node.type=="text") || (node.type=="password"))) {return false;} 
		} 
		document.onkeypress = stopRKey;	
		/*
		$(function(){
			var index = '<s:property value="index"/>';
			var t = $('#tabs');
			var tabs = t.tabs('tabs');
				t.tabs('select', tabs[index].panel('options').title);
		});
		*/
	</script>
	</s:if>
</head>
<body>
<s:if test='"list".equals(display)'>
<s:form name="form1" method="post" action="" theme="simple" validate="false" >
	<table width="90%" border="0" align="center" cellspacing="0" cellpadding="0" class='text'>
		<tr> <td>&nbsp;&nbsp;</td> </tr>
		<tr><td class="heading"><s:text name="label.branchMaster"/></td></tr>
		<tr><td>&nbsp;</td></tr>
		<tr><td>
				<table width="100%">
				<tr>
					<td width="50%">
						<table width="100%">
							<tr>
								<td width="33%">Search By:</td>
								<td width="33%"><s:select name="searchBy" id="searchBy" cssClass="inputSelect" list="#{'name':'Branch Name', 'code':'Branch Code'}" headerKey="" headerValue="-Select-"/></td>
								<td width="33%"><s:textfield name="searchVal" cssClass="inputBox" /></td>
							</tr>
						</table>
					</td>
					<td width="50%" align="right">
						<a class="btn btn-sm btn-primary" style="text-decoration: none;" title="Branch Creation" href="editBranch.action?mode=new"><s:text name="label.newBranchCreation"/></a>
					</td>							
				</tr>
			</table></td>
		</tr>
		<tr>
			<td>&nbsp;&nbsp;</td>
		</tr>
		<tr align="center">
			<td bgcolor="#FFFFFF" style="padding:10px; background:#F8F8F8" align="center" width="100%">
				<div id="brokerLists">
				<display:table name="branchList" pagesize="10" class="footable" uid="row" id="record">
						<display:setProperty name="paging.banner.one_item_found" value=""/>
						<display:setProperty name="paging.banner.one_items_found" value=""/>
						<display:setProperty name="paging.banner.all_items_found" value=""/>
						<display:setProperty name="paging.banner.some_items_found" value=""/>
						<display:setProperty name="paging.banner.placement"	value="bottom"/>
						<display:setProperty name="paging.banner.onepage" value="" />
						<display:column sortable="true" title="S.No." value="${record_rowNum}"/>
						<display:column sortable="true" title="Branch Name" property="BRANCH_NAME" ></display:column>
						<display:column sortable="true" title="Branch Code" property="BRANCH_CODE" />
						<display:column sortable="true" title="City" property="CITY" />
						<display:column sortable="true" title="Email" property="EMAIL" />
						<display:column sortable="true" title="More">
							<a href="#" class="btn btn-sm btn-primary" style="text-decoration: none;" onclick="forward('${record.BRANCH_CODE}','edit')" >More</a>
						</display:column>
						<display:column sortable="true" title="Status" property="STATUS"/>
				</display:table>
				</div>
			</td>
		</tr>
	</table>
	<s:hidden name="branchCode" id="branchCode"/>
	<s:hidden name="mode"  />
</s:form>
</s:if>
<s:elseif test='"edit".equals(display)'>
	 
	<div class="easyui-layout" style="width:900px;height:700px;">
		<s:if test='!"new".equals(mode)'>
		<div data-options="region:'west',split:true" title="Options" style="width:150px;">
			<div class="easyui-accordion" data-options="fit:true,border:false">
			    <input type="button" class="btntab" value="View" onclick="fnCall('view')"/><br/>
			</div>
		</div>
		</s:if>
		<s:if test='"new".equals(mode)'>
		<div data-options="region:'center',title:'New Branch Creation',iconCls:'icon-ok'">
		<div class="easyui-tabs" data-options="fit:true,border:false,plain:true" id="tabs">
		<div title="Branch Info" style="padding:10px" >
		</s:if>
		<s:else>
		<div data-options="region:'center',title:'<s:property value="borganization"/>(<s:property value="agencyCode"/>)',iconCls:'icon-ok'">
		<div class="easyui-tabs" data-options="fit:true,border:false,plain:true" id="tabs">
		<div title="Edit Branch Info" style="padding:10px" >
		</s:else>
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#E5E5E5">
			<tr>
				<td bgcolor="#FFFFFF" style="padding:10px; background:#F8F8F8">
				<s:form id="info" name="info" method="post" action="" theme="simple" enctype="multipart/form-data">
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
						<td class="heading"><s:label key="label.branchManagement" /></td>
					</tr>
					<tr style="height: 10px"><td>&nbsp;</td></tr>					
					<tr>
						<td  style="color:red;"><s:actionerror/> <s:actionmessage/> </td>
					</tr>					
					<tr style="height: 10px"><td>&nbsp;</td></tr>
  					<tr>
  						<td bgcolor="#FFFFFF">
  							<table width="100%" border="0" cellspacing="0" cellpadding="0">
          					 	<tr>
              						<td class="heading"><s:label  key="label.branchInformation" /></td>
          						</tr>
               					<tr>	                                                 
                					<td class="bg">
                						<table width="100%" border="0" align="center" cellpadding="4" cellspacing="0">
                      						<tr>
                      							<td width="5%"></td>
                        						<td width="30%"><s:label key="branch.branchName"/> <font color="red">*</font><br/>
                           							 <s:textfield name="branchName" id="branchName" cssClass="inputBox" size="35" maxlength="30"/></td>
                        					    <td width="30%"><s:label key="branch.branchPrefix"/> <font color="red">*</font><br/>
                           							 <s:textfield name="branchPrefix" id="branchPrefix" cssClass="inputBox" size="35" maxlength="10"/></td>
                            					<td width="30%"><s:label key="branch.belongingBranch"/> <font color="red">*</font><br/>
                            						<s:select name="belongingBranch" id="belongingBranch" list="belongingBranchList" cssClass="inputSelect" headerKey="" headerValue="-select-" listKey="BRANCH_CODE" listValue="BRANCH_NAME"/>
                            					</td>
                            					<td width="5%"></td>
                            				</tr>
                            				<tr>
                      							<td width="5%"></td>
                        						<td width="30%"><s:label key="branch.address1"/> <font color="red">*</font><br/>
                           							 <s:textfield name="address1" id="address1" cssClass="inputBox" size="35" maxlength="60"/></td>
                        					    <td width="30%"><s:label key="branch.address2"/> <font color="red">*</font><br/>
                           							 <s:textfield name="address2" id="address2" cssClass="inputBox" size="35" maxlength="60"/></td>
                           						<td width="30%"><s:label key="branch.address3"/> <font color="red">*</font><br/>
                           							 <s:textfield name="address3" id="address3" cssClass="inputBox" size="35" maxlength="60"/></td>
                            					<td width="5%"></td>
                            				</tr>
                            				<tr>
                      							<td width="5%"></td>
                        						<td width="30%"><s:label key="branch.country"/> <font color="red">*</font><br/>
                           							 <s:select name="country" id="country" list="countryList" cssClass="inputSelect" headerKey="" headerValue="-select-" listKey="COUNTRY_ID" listValue="COUNTRY_NAME" onchange="loadCities(this.value)"/></td>
                           						<td width="30%">
                           							<s:label key="branch.city"/> <font color="red">*</font><br/>
                        							<div id="cityAjax">                         							         						
                        							 	<s:select name="city" id="city" list="cityList" cssClass="inputSelect" headerKey="" headerValue="-select-" listKey="CITY_ID" listValue="CITY_NAME"/>
                        							</div>
                           						</td>
                           							 
                           						<td width="30%"><s:label key="branch.currency"/> <font color="red">*</font><br/>
                           							 <s:select name="currency" id="currency" list="currrencyList" cssClass="inputSelect" headerKey="" headerValue="-select-" listKey="CURRENCY_ID" listValue="CURRENCY_NAME"/></td>
                            					<td width="5%"></td>
                            				</tr>
                            				 
                            				<tr>
                      							<td width="5%"></td>
                        						<td width="30%"><s:label key="branch.phone"/> <font color="red">*</font><br/>
                           							 <s:textfield name="phone" id="phone" cssClass="inputBox" size="35" maxlength="10"/></td>
                        					    <td width="30%"><s:label key="branch.fax"/> <font color="red">*</font><br/>
                           							 <s:textfield name="fax" id="fax" cssClass="inputBox" size="35" maxlength="30"/></td>
                           						<td width="30%"><s:label key="branch.email"/> <font color="red">*</font><br/>
                           							 <s:textfield name="email" id="email" cssClass="inputBox" size="35" maxlength="30"/></td>
                            					<td width="5%"></td>
                            				</tr>
                            				<tr>
                      							<td width="5%"></td>
                        						<td width="30%"><s:label key="branch.status"/> <font color="red">*</font><br/>
                           							 <s:radio list="#{'Y':'Active','N':'Deactivate'}" name="status" id="status"></s:radio></td>
                        					    <td width="30%"><s:label key="branch.effectiveDate"/> <font color="red">*</font><br/>
                           							 <div class="inputAppend">                           							 
                           							 <sj:datepicker id="effectiveDate" name="effectiveDate" cssClass="inputBox1" displayFormat="dd/mm/yy" changeMonth="true" changeYear="true" readonly="true" cssStyle="width:80%; border: none; background-color: #ffffff;"/></div></td>
                           						<td width="30%"><s:label key="branch.remarks"/><br/>
                           							 <s:textfield name="remarks" id="remarks" cssClass="inputBox" size="35" maxlength="30"/></td>
                            					<td width="5%"></td>
                            				</tr>
                            			</table>
                            		</td>
                            	</tr>
                            	<tr><td>&nbsp;</td></tr>
                            	<tr>
              						<td class="heading"><s:label  key="branch.branchCurrencySettings" /></td>
          						</tr>
               					<tr>	                                                 
                					<td class="bg">
                						<table width="100%" border="0" align="center" cellpadding="4" cellspacing="0">
                      						<tr>
                      							<td width="5%"></td>
                        						<td width="30%"><s:label key="branch.originCountry"/> <font color="red">*</font><br/>
                           							 <s:select name="originCountry" id="originCountry" list="countryList" cssClass="inputSelect" headerKey="" headerValue="-select-" listKey="COUNTRY_ID" listValue="COUNTRY_NAME"/></td>
                        					    <td width="30%"><s:label key="branch.destiCountry"/> <font color="red">*</font><br/>
                           							 <s:select name="destiCountry" id="destiCountry" list="countryList" cssClass="inputSelect" headerKey="" headerValue="-select-" listKey="COUNTRY_ID" listValue="COUNTRY_NAME"/></td>
                           						<td width="30%"><s:label key="branch.tax"/> <font color="red">*</font><br/>
                           							 <s:textfield name="tax" id="tax" cssClass="inputBox" size="35" maxlength="10"/></td>
                            					<td width="5%"></td>
                            				</tr>
                            				<tr>
                      							<td width="5%"></td>                        						
                           						<td width="30%"><s:label key="branch.decimalPlaces"/> <font color="red">*</font><br/>
                           							 <s:textfield name="decimalPlaces" id="decimalPlaces" cssClass="inputBox" size="35" maxlength="3"/></td>                           						
                        					    <td width="30%"><s:label key="branch.lang"/> <font color="red">*</font><br/>
                           							 <s:radio list="#{'Y':'Yes','N':'No'}" name="lang" id="lang"></s:radio></td>
                           						<td width="30%"></td>
                            					<td width="5%"></td>
                            				</tr>                            				
                            			</table>
                					</td>
                				</tr>
                            	<tr><td>&nbsp;</td></tr>
                            	<tr>
              						<td class="heading"><s:label  key="branch.fileInforamtion" /></td>
          						</tr>
          						<s:if test='!"new".equals(mode)'>
          						<tr>
          							<td class="heading"><a href="#" onclick="fnShowImgInput('new')">Upload New Image</a></td>
          						</tr> 
          						</s:if>
               					<tr>	                                                 
                					<td class="bg">
                						<table width="100%" border="0" align="center" cellpadding="4" cellspacing="0">
                      						<tr>
                      							<td width="42px;"></td>
                        						<td width="252px">
                        							<div id="headerEdit">
                        							<s:label key="branch.headerImage"/> <font color="red">*</font><br/>                        							
                           							 <s:file name="headerImage" id="headerImage" cssClass="inputBox" onchange="fnImgPreview(this.id);" />
                           							</div> 
												</td>
                        					    <td width="504px">
                        					    	<div style=" width: 504px; height:64px; overflow: auto;"><img id="headerImagePreview" alt="Header Image" src="${pageContext.request.contextPath}/images/<s:property value='headerName'/>" /></div>                        					    	
                        					    	<div style="color: red; display: none;" id="headerImageError" ><br/>You uploaded image width is<span id="headerImageWidth"></span> and height is <span id="headerImageHeight"></span><br/>Please Upload the image containg Width = <b>715 px</b> and Height = <b>64px</b>  </div>
                        					    </td>                            					
                            					<td width="42px"></td>
                            				</tr>
                            				<tr>
                      							<td></td>
                        						<td>
                        							<div id="footerEdit">
                        							<s:label key="branch.footerImage"/> <font color="red">*</font><br/>
                           							 <s:file name="footerImage" id="footerImage" cssClass="inputBox" onchange="fnImgPreview(this.id);" />
                           							</div> 
                           						</td>
                        					    <td>
                        					    	<div style=" width: 504px; height:80px; overflow: auto;"><img id="footerImagePreview" alt="Footer Image"  src="${pageContext.request.contextPath}/images/<s:property value='footerName'/>"  /></div>
                        					    	<div style="color: red; display: none;" id="footerImageError" ><br/>You uploaded image width is <span id="footerImageWidth"></span> and height is <span id="footerImageHeight"></span><br/>Please Upload the image containg Width = <b>815 px</b> and Height = <b>80px</b>  </div>
                        					    </td>                            					
                            					<td></td>
                            				</tr>
                            				<tr>
                      							<td></td>
                        						<td>
                        							<div id="signEdit">
                        							<s:label key="branch.signImage"/> <font color="red">*</font><br/>
                           							 <s:file name="signImage" id="signImage" cssClass="inputBox" onchange="fnImgPreview(this.id);"  />
                           							</div> 
                           						</td>
                        					    <td>
                        					    	<div style=" width: 504px; height:322px; overflow: auto;"><img id="signImagePreview" alt="Sign Image" width="300" height="192" src="${pageContext.request.contextPath}/images/<s:property value='signImageName'/>"  /></div>
                        					    	<div style="color: red; display: none;" id="signImageError" ><br/>You uploaded image width is <span id="signImageWidth"></span> and height is <span id="signImageHeight"></span><br/>Please Upload the image containg Width = <b>470 px</b> and Height = <b>322px</b>  </div>
                        					    </td>                            					
                            					<td></td>
                            				</tr>
                            				<tr>
                      							<td></td>
                        						<td>
                        							<div id="stampEdit">
                        							<s:label key="branch.stamp"/> <font color="red">*</font><br/>
                           							 <s:file name="stampImage" id="stampImage" cssClass="inputBox" onchange="fnImgPreview(this.id);" />
                           							</div> 
                           						</td>
                        					    <td>
                        					    	<div style=" width: 504px; height:322px; overflow: auto;"><img id="stampImagePreview" alt="Stamp Image" width="300" height="192" src="${pageContext.request.contextPath}/images/<s:property value='stampImageName'/>"/></div>
                        					    	<div style="color: red; display: none;" id="stampImageError" ><br/>You uploaded image width is <span id="stampImageWidth"></span> and height is <span id="stampImageHeight"></span><br/>Please Upload the image containg Width = <b>470 px</b> and Height = <b>322px</b>  </div>
                        					    </td>                            					
                            					<td>=&quot;</td>
                            				</tr>
                            			</table>
                            		</td>
                            	</tr>
                            </table>
						</td>
  					</tr>
  					<tr><td>&nbsp;</td></tr>
  					<tr>
  					<s:hidden name="branchCode" id="branchCode"/>
  					<s:hidden name="" />
  						<td align="center">
  							<s:submit  name="back" cssClass="btn" value="Back" action="listBranch"/> &nbsp;&nbsp;&nbsp;
  							<s:submit cssClass="btn btn-sm btn-success" action="saveBranch" id="submitBtn" value="Submit"></s:submit>
  						</td>
  					</tr>
  					</s:else>
					</table>
				</s:form>
				</td>
			</tr>
			</table>
		</div>
		</div>
		</div>
	</div>
</s:elseif>

</body>
<script type="text/javascript">
<s:if test='"new".equals(mode)'>
	fnShowImgInput('new');
</s:if>
<s:else>
	fnShowImgInput('edit');
</s:else>

function fnShowImgInput(val) {
	if (val == "new") {
		document.getElementById('headerEdit').style.display = 'block';
		document.getElementById('footerEdit').style.display = 'block';
		document.getElementById('stampEdit').style.display = 'block';
		document.getElementById('signEdit').style.display = 'block';
		document.getElementById('headerImagePreview').src='';
		document.getElementById('footerImagePreview').src='';
		document.getElementById('signImagePreview').src='';
		document.getElementById('stampImagePreview').src='';
	} else if (val == "edit") {
		document.getElementById('headerEdit').style.display = 'none';
		document.getElementById('footerEdit').style.display = 'none';
		document.getElementById('stampEdit').style.display = 'none';
		document.getElementById('signEdit').style.display = 'none';
	}
}

function fnImgPreview(id) {
	var width, height;
	var oFReader = new FileReader();
    oFReader.readAsDataURL(document.getElementById(id).files[0]);        
    oFReader.onload = function (oFREvent) {
    	var element = document.getElementById(id+"Preview");
        document.getElementById(id+"Preview").src = oFREvent.target.result;

        //total size in bytes
        var sizeInBytes = oFREvent.total;

        //for this to work, you have to remove the width/height from the img#uploadPreview element
        var imageWidth = element.width;
        var imageHeight = element.height;
        
        if (id.toLowerCase() == "headerImage".toLowerCase()) {
	    	if (imageWidth != 715 || imageHeight != 64) {	    		
	    		document.getElementById(id+"Error").style.display = "block";
	    		document.getElementById(id+"Width").innerHTML = imageWidth;
	    		document.getElementById(id+"Height").innerHTML = imageHeight;	    		
	    		document.getElementById("submitBtn").style.display = "none";
	    	} else {
	    		document.getElementById("submitBtn").style.display = "block";
	    		document.getElementById(id+"Error").style.display = "none";
	    	}
	    } else if (id.toLowerCase() == "footerImage".toLowerCase()) {
	    	if (imageWidth != 815 || imageHeight != 80) {	    		
	    		document.getElementById(id+"Error").style.display = "block";
	    		document.getElementById(id+"Width").innerHTML = imageWidth;
	    		document.getElementById(id+"Height").innerHTML = imageHeight;	    		
	    		document.getElementById("submitBtn").style.display = "none";
	    	} else {
	    		document.getElementById("submitBtn").style.display = "block";
	    		document.getElementById(id+"Error").style.display = "none";
	    	}
	    } else if (id.toLowerCase() == "signImage".toLowerCase()) {
	    	if (imageWidth != 470 || imageHeight != 322) {	    		
	    		document.getElementById(id+"Error").style.display = "block";
	    		document.getElementById(id+"Width").innerHTML = imageWidth;
	    		document.getElementById(id+"Height").innerHTML = imageHeight;	    		
	    		document.getElementById("submitBtn").style.display = "none";
	    	} else {
	    		document.getElementById("submitBtn").style.display = "block";
	    		document.getElementById(id+"Error").style.display = "none";
	    	}
	    } else if (id.toLowerCase() == "stampImage".toLowerCase()) {
	    	if (imageWidth != 470 || imageHeight != 322) {	    		
	    		document.getElementById(id+"Error").style.display = "block";
	    		document.getElementById(id+"Width").innerHTML = imageWidth;
	    		document.getElementById(id+"Height").innerHTML = imageHeight;	    		
	    		document.getElementById("submitBtn").style.display = "none";
	    	} else {
	    		document.getElementById("submitBtn").style.display = "block";
	    		document.getElementById(id+"Error").style.display = "none";
	    	}
	    }
                        
    }  
}
	<s:if test='(country!=null && !"".equals(country) )&& (!"new".equals(mode))'>
		//loadCities('<s:property value="country"/>');
	</s:if>
	function loadCities(val){		 	
		 postRequest("getCityBranch.action?countryAjax="+val,"cityAjax");
		
	} 
	function forward(code,mode)
	{
		document.form1.branchCode.value=code;
		document.form1.mode.value=mode;
		document.form1.action='editBranch.action';
		document.form1.submit();
	} 
	
</script>
</html>