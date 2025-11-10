<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	 	<sj:head jqueryui="true" jquerytheme="start" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>Deposit Master</title>
		<link href="css/styles.css" rel="stylesheet" type="text/css" />
		<link href="css/tcal.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="js/tcal.js"></script>
		<script type="text/javascript" src="js/common.js"></script>
		<script language="JavaScript">javascript:window.history.forward(1);</script>
		<script language="JavaScript">
				function stopRKey(evt) { 
				  var evt = (evt) ? evt : ((event) ? event : null); 
				  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
				  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
				} 
				document.onkeypress = stopRKey; 
				
				function formatEdit(cellvalue, options, row) {
					var ss='${pageContext.request.contextPath}/editDeposit.action?mode=edit&cbcNo='+cellvalue;
					return "<a href=\'"+ss+"\'>Edit</a>";
				}

				function formatEdit1(cellvalue, options, row) {
					var ss='${pageContext.request.contextPath}/editChequeDeposit.action?option=editCheque&depositNo='+cellvalue;
					return "<a href='#' onClick=javascript:popUp(\'"+ss+"\',550,350)>Edit</a>";
				}
				
				function formatChequeDetails(cellvalue, options, row) {
					var ss='${pageContext.request.contextPath}/chequeDetailsDeposit.action?cbcNo='+cellvalue;
					return "<a href=\'"+ss+"\'>Cheque Details</a>";
				}
				
				function formatChequeDetails1(cellvalue, options, row) {
					var ss='${pageContext.request.contextPath}/depositDetailDeposit.action?depositNo='+cellvalue;
					return "<a href=\'"+ss+"\'>More</a>";
				}
				
				function refreshGrid(){
					 $.publish('reloadReport');
				}
				
				function depositTYPE(id){
					var val = '';
					var elements = document.getElementsByName('depositType');
				    for (var i = 0, l = elements.length; i < l; i++) {
				        if (elements[i].checked){
				            val=elements[i].value;
				        }
				    }
                	postRequest('${pageContext.request.contextPath}/getAjaxDeposit.action?depositType='+val+'&reqFrom='+id, id);
       			}
		</script>
	</head>
	<body>
		<table width="900" border="0" align="center" cellpadding="0" cellspacing="0"> 
  			<tr><td height="5"></td></tr>
  			<tr>
    			<td><table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#E5E5E5">
     					<tr>
        					<td bgcolor="#FFFFFF" style="padding:10px; background:#F8F8F8">
        						<s:form id="deposit" name="deposit" method="post" action="saveCBCDeposit" theme="simple">
        							<s:if test='%{"edit".equals(mode) || "new".equals(mode)}'>
	            						<table width="100%" border="0" cellspacing="0" cellpadding="0">
								            <tr>
								                <td bgcolor="#FFFFFF"><table width="100%" border="0" cellspacing="0" cellpadding="0">
									            	<tr><td class="heading"><s:text name="labe.deposit.master" /></td></tr>
									            	<tr><td><table width="100%"><tr><td width="25%"></td><td width="75%"><font color="red"><s:actionerror/></font></td></tr></table></td></tr>
		                    						<tr>	                                                 
		                     							<td class="bg"><table width="100%" border="0" align="center" cellpadding="4" cellspacing="0">
		                          							<tr>
		                           								<td width="15%">&nbsp;</td>
		                            							<td width="20%" align="right"><s:text name="label.cbc.no"/><font color="red">*</font></td>
		                            							<td width="3%">&nbsp;</td>
		                            							<td width="47%"><s:textfield name="cbcNo" cssClass="input" readonly='%{"edit".equals(mode)?true:false}' maxlength="10" onblur="validamt(this);" /></td>
		                            							<td width="15%">&nbsp;</td>
		                              						</tr>
		                              						<tr>
		                           								<td width="15%">&nbsp;</td>
		                            							<td width="20%" align="right"><s:text name="label.deposit.type"/></td>
		                            							<td width="3%">&nbsp;</td>
		                            							<td width="47%"><s:radio name="depositType" id="depositType" list="#{'INDIVIDUAL':'Individual','FLOAT':'Float'}" cssClass="input" onchange="depositTYPE('depositTYPE')"/></td>
		                            							<td width="15%">&nbsp;</td>
		                              						</tr>
		                              						<tr>
		                           								<td width="15%">&nbsp;</td>
		                            							<td width="20%" align="right"><s:text name="label.broker.code"/><font color="red">*</font></td>
		                            							<td width="3%">&nbsp;</td>
		                            							<td width="47%">
			                            							<s:if test='"edit".equals(mode)'>
			                            								<b><s:property value="brokerName"/></b><s:hidden name="agencyCode" id="agencyCode"/>
			                            							</s:if>
		                            								<s:else>
		                            									<s:select name="agencyCode" id="agencyCode" list="brokerList" listKey="AGENCY_CODE" listValue="COMPANY_NAME" cssClass="input" headerKey="" headerValue="-Select-"/>
		                            								</s:else>
		                            							</td>
		                            							<td width="15%">&nbsp;</td>
		                              						</tr>
		                              						<tr>
		                           								<td width="15%">&nbsp;</td>
		                            							<td width="20%" align="right"><s:text name="Customer Option"/><font color="red">*</font></td>
		                            							<td width="3%">&nbsp;</td>
		                            							<td width="47%"><s:radio name="customerOpt" id="customerOpt" list="#{'INDIVIDUAL':'Individual','MULTIPLE':'Multiple'}" cssClass="input"/></td>
		                            							<td width="15%">&nbsp;</td>
		                              						</tr>
		                              						<tr>
		                           								<td width="15%">&nbsp;</td>
		                            							<td width="20%"align="right"><s:text name="label.customer.name"/><font color="red">*</font></td>
		                            							<td width="3%">&nbsp;</td>
		                            							<td width="47%"><s:textarea name="customerId" id="customerId" cssClass="input" rows="3" cssStyle="width:300px;" readonly="true" /><input type="button" class="btn" value="..." name="custId" onclick="customerSelection()"/></td>
		                            							<td width="15%">&nbsp;</td>
		                              						</tr>
		                              						<tr>
		                           								<td width="15%">&nbsp;</td>
		                            							<td width="20%" align="right"><s:text name="label.product.id"/><font color="red">*</font></td>
		                            							<td width="3%">&nbsp;</td>
		                            							<td width="47%"><div id="depositTYPE"><s:checkboxlist name="productsId" id="productsId" list="productList" cssClass="input" listKey="PRODUCT_ID" listValue="PRODUCT_NAME" onclick="checkOc()"/></div></td>
		                            							<td width="15%">&nbsp;</td>
		                              						</tr>
		                              						<tr>
		                           								<td width="15%">&nbsp;</td>
		                            							<td width="20%" align="right"><s:text name="label.opencover.number"/></td>
		                            							<td width="3%">&nbsp;</td>
		                            							<td width="47%"><s:textarea name="ocNo" id="oCNo" cssClass="input" rows="3" cssStyle="width:300px;" readonly="true" /><input id="ocNo1" type="button" class="btn" value="..." name="openCoverNo" onclick="opencoverSelection()"/></td>
		                            							<td width="15%">&nbsp;</td>
		                              						</tr>
	                    								</table></td>
	                    							</tr>
	                    							<tr>
	                    								<td><table align="center">
	                    									<tr>
		                            							<td align="center">
									                            	<s:submit name="back" value="Back" cssClass="btn" action="viewDeposit"/>&nbsp;&nbsp;&nbsp;&nbsp;
									                            	<s:submit name="Submit" type="submit" cssClass="btn" value="Submit"/>
									                            </td>
									                        </tr>
	                									</table></td>
	              									</tr>
	              								</table></td>
	              							</tr>
	            						</table>
	            					</s:if>
	            					<s:elseif test='%{"view".equals(mode) || "chequeDetail".equals(mode)}'>
	            						<table width="100%" border="0" cellspacing="0" cellpadding="0">
	            							<tr><td class="heading"><s:text name="labe.deposit.master" /></td></tr>
           									<s:if test='"view".equals(mode)'>
           										<tr align="center"><td align="right"  height="40px;">
           											<a href="${pageContext.request.contextPath}/editDeposit.action?mode=new">Add New Deposit</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
           										</td></tr>
           									</s:if>
           									<s:if test='"chequeDetail".equals(mode)'>
           										<tr align="center"><td align="right"  height="40px;">
           											<a href="#" onclick="javascript:popUp('${pageContext.request.contextPath}/editChequeDeposit.action?option=newCheque&cbcNo=${cbcNo}',550,350)">Add Cheque</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
           										</td></tr>
           										<tr><td></td></tr>
           										<tr>
           											<td><table width="100%">
           													<s:iterator value="cbcList">
           														<tr><td></td></tr>
	           													<tr>
	           														<td width="3%"></td>
	           														<td width="15%" align="right"><s:text name="label.cbc.no"/></td><td width="15%" align="left"><b> : ${cbcNo}</b></td>
	           														<td width="15%" align="right"><s:text name="label.broker.code"/></td><td width="15%" align="left"><b> : ${brokerName}</b></td>
	           														<td width="15%" align="right"><s:text name="label.deposit.type"/></td><td width="15%" align="left"><b> : ${depositType}</b></td>
	           														<td width="7%"></td>
	           													</tr>
	           													<tr>
	           														<td width="3%"></td>
	           														<td width="15%" align="right"><s:text name="label.customer.option"/></td><td width="15%" align="left"><b> : ${customerOpt}</b></td>
	           														<td width="15%" align="right"><s:text name="label.product.id"/></td><td width="15%" align="left"><b> : ${productName}</b></td>
	           														<td width="15%" align="right">&nbsp;</td><td width="15%" align="right">&nbsp;</td>
	           														<td width="7%"></td>
	           													</tr>
	           													<tr><td></td></tr>
           													</s:iterator>
           											</table></td>
           										</tr>
           									</s:if>
	           								<tr><td>
	           										<s:if test='"chequeDetail".equals(mode)'>
						            					<s:url id="remoteurl" action="getJsonDepositJson.action?mode=%{mode}&cbcNo=%{cbcNo}"/>
						            				</s:if>
						            				<s:else>
						            					<s:url id="remoteurl" action="getJsonDepositJson.action?mode=%{mode}"/>
						            				</s:else>
								    	     		<sjg:grid
													       id="gridReport" href="%{remoteurl}" pager="true" dataType="json" gridModel="gridReport" reloadTopics="reloadReport"
													       rowList="10,15,20" rownumbers="true" width="800" navigator="true" navigatorView="false" navigatorDelete="false" autowidth="true"
													       navigatorAdd="false" navigatorEdit="false" navigatorSearch="true" navigatorRefresh="false" viewrecords="true" onSelectRowTopics="rowselect"
														>
														<s:if test='"view".equals(mode)'>
													   		<sjg:gridColumn name="cbcNo" index="cbcNo"  title="CBC No" search="true" searchoptions="{sopt:['cn','nc']}"  sortable="false"/>
													        <sjg:gridColumn  name="brokerName" index="brokerName" title="Broker Name" search="true" searchoptions="{sopt:['cn','nc']}"  sortable="false"/>
													        <sjg:gridColumn name="depositType" index="depositType" title="Deposit Type" search="false" sortable="false"/>
													        <sjg:gridColumn name="customerOpt" index="customerOpt" title="Customer option" search="false" sortable="false"/>
													        <sjg:gridColumn name="productName" index="productName" title="Product Name" search="false" sortable="false"/>
													        <sjg:gridColumn name="cbcNo" title="Edit" align="center" formatter="formatEdit" search="false" sortable="false"/>
													        <sjg:gridColumn name="cbcNo" title="Cheque Details" align="center" formatter="formatChequeDetails" search="false" sortable="false"/>
													    </s:if>
													    <s:elseif test='"chequeDetail".equals(mode)'>
													    	<sjg:gridColumn name="depositNo" index="depositNo"  title="Deposit No" search="true" searchoptions="{sopt:['cn','nc']}"  sortable="false"/>
													    	<sjg:gridColumn  name="chequeNo" index="chequeNo" title="Cheque No" search="true" searchoptions="{sopt:['cn','nc']}"  sortable="false"/>
													    	<sjg:gridColumn name="chequeBank" index="chequeBank" title="Cheque Bank" search="false" sortable="false"/>
													        <sjg:gridColumn name="chequeDate" index="chequeDate" title="Check Date" search="false" sortable="false"/>
													        <sjg:gridColumn name="receiptNo" index="receiptNo" title="Receipt No" search="true" searchoptions="{sopt:['cn','nc']}" sortable="false"/>
													    	<sjg:gridColumn name="depositAmt" index="depositAmt" title="Deposit Amount" search="false"/>
															<sjg:gridColumn name="depositUtilsed" index="depositUtilsed" title="Utilised Amount" search="false"/>
													        <sjg:gridColumn name="depositNo" title="Edit" align="center" formatter="formatEdit1" search="false"/>
													        <sjg:gridColumn name="depositCBCNo" title="Details" align="center" formatter="formatChequeDetails1" search="false"/>
													    </s:elseif>
													</sjg:grid>
												</td>
											</tr>
											<s:if test='"chequeDetail".equals(mode)'>
												<tr><td align="center">&nbsp;</td></tr>
												<tr><td align="center"><s:submit name="back1" action="viewDeposit" cssClass="btn" value="Back"/></td></tr>
											</s:if>
										</table>
	            					</s:elseif>
	            					<s:elseif test='"newSuccess".equals(mode)'>
	            						<table width="100%" border="0" cellspacing="0" cellpadding="0">
	            							<tr><td class="heading"><s:text name="labe.deposit.master" /></td></tr>
	            							<tr align="center"><td class="bg" height="100px;" align="center"><s:text name="label.cbc.add.success" /></td></tr>
	            							<tr><td align="center"><s:submit name="back1" action="viewDeposit" cssClass="btn" value="Back"/></td></tr>
	            						</table>
	            					</s:elseif>
	            					<s:elseif test='"editSuccess".equals(mode)'>
	            						<table width="100%" border="0" cellspacing="0" cellpadding="0">
	            							<tr><td class="heading"><s:text name="labe.deposit.master" /></td></tr>
	            							<tr align="center"><td class="bg" height="100px;" align="center"><s:text name="label.cbc.edit.success" /></td></tr>
	            							<tr><td align="center"><s:submit name="back1" action="viewDeposit" cssClass="btn" value="Back"/></td></tr>
	            						</table>
	            					</s:elseif>
	            					<s:hidden name="mode"/>
        						</s:form>
        					</td>
      					</tr>
    			</table></td>
  			</tr>
  			<tr><td>&nbsp;</td></tr>
		</table>
		<script type="text/javascript">
			<s:if test='%{!(depositType!=null && "".equals(depositType))}'>
				depositTYPE('<s:property value="depositType"/>');
			</s:if>
			<s:if test='%{!(productsId!=null && "".equals(productsId))}'>
				checkOc('<s:property value="productsId"/>');
				radioOc('<s:property value="productsId"/>');
			</s:if>
		
				function customerSelection(){
					var custOpt='';
					var elements = document.getElementsByName('customerOpt');
				    for (var i = 0, l = elements.length; i < l; i++) {
				        if (elements[i].checked){
				            custOpt=elements[i].value;
				        }
				    }
					var broker=document.getElementById('agencyCode').value;
					if(custOpt!='' && broker!=''){
						var URL='${pageContext.request.contextPath}/custmerSelectDeposit.action?customerOpt='+custOpt+"&agencyCode="+broker;
							var windowName = "CustomerSelection";
							var width  = screen.availWidth;
							var height = screen.availHeight;
							var w = 520;
							var h = 350;
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
					}else if(broker==''){
						alert("Please Select Broker");
					}else if(custOpt==''){
						alert("Please Select Customer Option");
					}
				}
				
				function opencoverSelection(){
					var product = '';
					var elements = document.getElementsByName('productsId');
				    for (var i = 0, l = elements.length; i < l; i++) {
				        if (elements[i].checked){
				            product+=elements[i].value+',';
				        }
				    }
				    if(product.length>1)
						product=product.substring(0, product.length-1);
					var custId=document.getElementById('customerId').value;
					if(product!='' && custId!=''){
						var URL='${pageContext.request.contextPath}/ocSelectDeposit.action?customerId='+custId;
							var windowName = "CustomerSelection";
							var width  = screen.availWidth;
							var height = screen.availHeight;
							var w = 520;
							var h = 350;
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
					}else if(custId==''){
						alert("Please Select Customer Id");
					}
					else if(product==''){
						alert("Please Select Product");
					}
				}
				
				function checkOc(){
					var product = '';
					var elements = document.getElementsByName('productsId');
				 	for (var i = 0, l = elements.length; i < l; i++) {
				        if (elements[i].checked){
				            product+=elements[i].value+',';
				        }
				    }
				    if(product.indexOf("11")== -1){
				    	document.getElementById('oCNo').value='';
				    	document.getElementById('oCNo').disabled=true;
				    	document.getElementById('ocNo1').disabled=true;
				    }else{
				    	document.getElementById('oCNo').disabled=false;
				    	document.getElementById('ocNo1').disabled=false;
				    }
				}
				
				function radioOc(){
					var product = '';
					var elements = document.getElementsByName('productsId');
				    for (var i = 0, l = elements.length; i < l; i++) {
				        if (elements[i].checked){
				            product=elements[i].value;
				        }
				    }
				    if(product.indexOf("11")== -1){
				    	document.getElementById('oCNo').value='';
				    	document.getElementById('oCNo').disabled=true;
				    	document.getElementById('ocNo1').disabled=true;
				    }else{
				    	document.getElementById('oCNo').disabled=false;
				    	document.getElementById('ocNo1').disabled=false;
				    }
				}
				function validamt(val){
					var value=val.value;
					if(value!=null){
						val.value = value.replace(/[^0-9]/g,'');
					}
				}
		</script>
	</body>
</html>
