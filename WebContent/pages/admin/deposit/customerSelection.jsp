<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<sj:head jqueryui="true" jquerytheme="start" />
		<link href="css/styles.css" rel="stylesheet" type="text/css" />
		<link href="css/tcal.css" rel="stylesheet" type="text/css" />
        <link href="css/claim.css" rel="stylesheet" type="text/css">
       	<link href="css/displaytag.css" rel="stylesheet" type="text/css">
 	</head>

	<body>
		<s:if test='"customerSelection".equals(option)'>
			<s:form name="menuSelection" theme="simple" >
				<table width="55%" border="0" cellspacing="0" cellpadding="2" align="center" class="data-table-border" style="table-layout: fixed">
					<tr>
		 				<td class="formtxtc" style="text-align: center;">
							<display:table name="customerList" pagesize="" requestURI="" class="table" uid="row" id="record">
									<display:setProperty name="paging.banner.one_item_found" value="" />
									<display:setProperty name="paging.banner.one_items_found" value="" />
									<display:setProperty name="paging.banner.all_items_found" value="" />
									<display:setProperty name="paging.banner.some_items_found" value="" />
									<display:setProperty name="paging.banner.placement" value="bottom" />
									<display:setProperty name="paging.banner.onepage" value="" />
									<s:if test='"INDIVIDUAL".equals(customerOpt)'>
										<display:column sortable="true" style="text-align:center;">
											<input type="radio" value="${record.CUSTOMER_ID}" id="customerSelect" name="customerSelect" />
										</display:column>
									</s:if>
									<s:else>
										<display:column sortable="true" style="text-align:center;" title="<input type='checkbox'  onclick='SelectAll()' id='checkall' />">
											<input type="checkbox" value="${record.CUSTOMER_ID}" id="checkbox${record.CUSTOMER_ID}" />
										</display:column>
									</s:else>
									<display:column sortable="true" style="text-align:left;" title="Customer Name" property="cust_name" />
									<display:column sortable="true" style="text-align:left;" title="Mobile No" property="MOBILE" />
							</display:table>	
						</td>
					</tr>
					<tr align="center"><td class="text"><input type="button" onClick="fnsubmit()"  Class="btn" value="Submit"/>&nbsp;<input type="button"  Class="btn" onClick="javascript:window.close()" value="Back" style = "cursor:hand"></td></tr>
				</table>
			</s:form>
			<script type="text/javascript">
				 if(window.opener.deposit.customerId.value.length>=1){
					<s:if test='"INDIVIDUAL".equals(customerOpt)'>
						var val=window.opener.deposit.customerId.value;
						try{
							var elements = document.getElementsByName('customerSelect');
						    for (var i = 0, l = elements.length; i < l; i++) {
						        if (elements[i].value==val){
						            elements[i].enabled=true;
						        }
						    }
						}catch(e){}
					</s:if>
					<s:else>
						var val=window.opener.deposit.customerId.value;
						var elements=val.split(',');
						
						for(i=0; i<elements.length; i++){
							document.getElementById('checkbox'+elements[i]).checked=true;
						}
					</s:else>
				}
				
				function SelectAll(){
					try{
						var elements=document.forms[0].elements;
						if(document.getElementById('checkall').checked){
							for(i=0;i<elements.length;i++){
							   obj=elements[i];
							   if(obj.type=='checkbox'){
							      obj.checked=true;
							   }
							}
				 		}else{
							for(i=0;i<elements.length;i++){
				    			obj=elements[i];
				    			if(obj.type=='checkbox'){
				      				obj.checked=false;
				    			}
				 			}
				 		}
					}catch(e){}
				}
				
				function fnsubmit(){
					<s:if test='"INDIVIDUAL".equals(customerOpt)'>
						var checkedItems='';
						try{
							var elements = document.getElementsByName('customerSelect');
						    for (var i = 0, l = elements.length; i < l; i++) {
						        if (elements[i].checked){
						            checkedItems=elements[i].value;
						        }
						    }
						}catch(e){}
						window.opener.deposit.customerId.value=checkedItems;
						window.close();
					</s:if>
					<s:else>
						var checkedItems='';
						try{
							var elements=document.forms[0].elements;
							for(i=1; i<elements.length; i++){
								obj=elements[i];
								if(obj.type=='checkbox' && obj.checked)
									checkedItems+=obj.value+',';
							}
						}catch(e){}
						if(checkedItems.length>1)
							checkedItems=checkedItems.substring(0, checkedItems.length-1);
						window.opener.deposit.customerId.value=checkedItems;
						window.close();
					</s:else>
				}
			</script>
		</s:if>
		<s:elseif test='%{"newCheque".equals(option) || "editCheque".equals(option)}'>
			<table width="500" border="0" align="center" cellpadding="0" cellspacing="0"> 
	  			<tr><td height="5"></td></tr>
	  			<tr>
	    			<td><table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#E5E5E5">
	     					<tr>
	        					<td bgcolor="#FFFFFF" style="padding:10px; background:#F8F8F8">
	        						<s:form id="addcheck" name="addcheck" method="post" action="saveChequeDeposit" theme="simple">
	            						<table width="100%" border="0" cellspacing="0" cellpadding="0">
								            <tr>
								                <td bgcolor="#FFFFFF"><table width="100%" border="0" cellspacing="0" cellpadding="0">
									            	<tr><td class="heading" colspan="5">
									            			<s:if test='"newCheque".equals(option)'><s:text name="label.add.check" /></s:if>
									            			<s:elseif test='"editCheque".equals(option)'><s:text name="label.check.modify" /></s:elseif>
									            	</td></tr>
									            	<tr><td colspan="5"></td></tr>
									            	<tr><td  style="color:red;" colspan="3"><s:actionerror/></td></tr>
									            	<tr><td colspan="5"></td></tr>
									            	<tr height="30px;" align="center">
														<td width="30%" align="right"><s:text name="label.cbc.no"/><font color="red">*</font></td>
														<td width="3%">&nbsp;</td>
														<td width="67%" align="left"><b><s:property value="cbcNo"/></b><s:hidden name="cbcNo"/></td>
													</tr>
									            	<tr height="30px;" align="center">
														<td width="30%" align="right"><s:text name="label.cheque.no"/><font color="red">*</font></td>
														<td width="3%">&nbsp;</td>
														<td width="67%" align="left"><s:textfield name="chequeNo" cssClass="input" maxlength="6" readonly='%{"editCheque".equals(option)?true:false}'/></td>
													</tr>
													<tr height="30px;" align="center">
														<td width="30%" align="right"><s:text name="label.cheque.amt"/><font color="red">*</font></td>
														<td width="3%">&nbsp;</td>
														<td width="67%" align="left"><s:textfield name="chequeAmt" cssClass="input" maxlength="15"/></td>
													</tr>
													<tr height="30px;" align="center">
														<td width="30%" align="right"><s:text name="label.cheque.bank"/><font color="red">*</font></td>
														<td width="3%">&nbsp;</td>
														<td width="67%" align="left"><s:textfield name="chequeBank" cssClass="input" maxlength="50"/></td>
													</tr>
													<tr height="30px;" align="center">
														<td width="30%" align="right"><s:text name="label.cheque.date"/><font color="red">*</font></td>
														<td width="3%">&nbsp;</td>
														<td width="67%" align="left"><sj:datepicker id="chequeDate" name="chequeDate" cssClass="input" minDate="-3M" displayFormat="dd/mm/yy" readonly="true" showAnim="slideDown" duration="fast" cssStyle="154px;" /></td>
													</tr>
													<tr height="30px;" align="center">
														<td width="30%" align="right"><s:text name="label.receipt.no"/><font color="red">*</font></td>
														<td width="3%">&nbsp;</td>
														<td width="67%" align="left"><s:textfield name="receiptNo" cssClass="input" maxlength="10"/></td>
													</tr>
													<tr height="10px;" align="center"><td></td></tr>
									            </table></td>
									        </tr>
									        <tr align="center">
									        	<td><input type="button" Class="btn" onClick="javascript:window.close()" value="Back" style = "cursor:hand">
									        		<s:submit name="submit12" cssClass="btn" value="Submit"/>&nbsp;
									        	</td>
									        </tr>
									    </table>
									    <s:hidden name="option"/>
									</s:form>
								</td>
							</tr>
					</table></td>
				</tr>
			</table>
		</s:elseif>
		<s:elseif test='%{"newChequeSuccess".equals(option) || "editChequeSuccess".equals(option)}'>
			<table width="500" border="0" align="center" cellpadding="0" cellspacing="0"> 
	  			<tr><td height="5"></td></tr>
	  			<tr>
	    			<td><table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#E5E5E5">
	     					<tr>
	        					<td bgcolor="#FFFFFF" style="padding:10px; background:#F8F8F8">
	        						<s:form id="deposit" name="deposit" method="post" action="saveCBCDeposit" theme="simple">
		            						<table width="100%" border="0" cellspacing="0" cellpadding="0">
									            <tr>
									                <td bgcolor="#FFFFFF"><table width="100%" border="0" cellspacing="0" cellpadding="0">
										            	<tr><td class="heading"><s:if test='"newChequeSuccess".equals(option)'><s:text name="label.add.check" /></s:if>
									            			<s:elseif test='"editChequeSuccess".equals(option)'><s:text name="label.check.modify" /></s:elseif>
									            		</td></tr>
									            		<s:hidden name="cbcNo"/>
									            		<tr align="center"><td class="bg" height="100px;" align="center"><s:if test='"newChequeSuccess".equals(option)'><s:text name="label.add.cheque.success" /></s:if>
									            									<s:elseif test='"editChequeSuccess".equals(option)'><s:text name="label.check.modify.success" /></s:elseif></td></tr>
	            										<tr><td align="center"><s:submit name="back1" onclick="javascript:window.opener.refreshGrid();javascript:window.close();" cssClass="btn" value="Back"/></td></tr>
										            </table></td>
										        </tr>
										    </table>
									</s:form>
								</td>
							</tr>
					</table></td>
				</tr>
			</table>
		</s:elseif>
	</body>
</html>


