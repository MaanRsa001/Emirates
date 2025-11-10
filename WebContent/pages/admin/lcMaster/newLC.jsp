<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="/struts-jquery-tags" prefix="sj"%>
<html>
	<head>
		<sj:head jqueryui="true" jquerytheme="start" />
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
			
			function fnSub(value){
				document.newlc.action=value;
				document.newlc.submit();
			}
  		</script>
	</head>
	<body>
    	<table width="100%">
       		<tr><td height="5"></td></tr>
  			<tr>
    			<td>
    				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#E5E5E5">
      					<tr>
        					<td bgcolor="#FFFFFF" style="padding:10px; background:#F8F8F8">
 					 			<s:form id="newlc" name="newlc" method="post" action="" theme="simple">
     					 			<table width="100%" border="0" cellspacing="0" cellpadding="0">
     					 				<tr><td class="heading"><s:label key="lc.master.creation" /></td></tr>
      					 				<s:if test="'successNew'.equals(display)">
											<tr>
   												<td bgcolor="#FFFFFF" style="padding:10px; background:#F8F8F8">
   													<s:label key="lc.new.success"/>
												</td>
											</tr>
											<tr>
												<td>
													<table width="100%" border="0" cellspacing="0" cellpadding="0">
														<tr>
															<td height="25" align="center" valign="middle">
<%-- 																<s:submit name="back" cssClass="btn" value="Back" action="lcDetailsLC"/> --%>
																<input type="button" class="btn" onclick="fnSub('lcDetailsLC.action');" value="Back" >&nbsp;&nbsp;&nbsp;&nbsp;
															</td>
														</tr>
													</table>
													<s:hidden name="openCover"/>
													<s:hidden name="lcNum"/>
													<s:hidden name="brokerName"/>
													<s:hidden name="custName"/>
												</td>
											</tr>
										</s:if>
										<s:elseif test="'successUpdate'.equals(display)">
											<tr>
   												<td bgcolor="#FFFFFF" style="padding:10px; background:#F8F8F8">
   													<s:label key="lc.update.success"/>
												</td>
											</tr>
											<tr>
												<td>
													<table width="100%" border="0" cellspacing="0" cellpadding="0">
														<tr>
															<td height="25" align="center" valign="middle">
<%-- 																<s:submit name="back" cssClass="btn" value="Back" action="lcDetailsLC"/> --%>
																<input type="button" class="btn" onclick="fnSub('lcDetailsLC.action');" value="Back" >&nbsp;&nbsp;&nbsp;&nbsp;
															</td>
														</tr>
													</table>
													<s:hidden name="from"/>
													<s:hidden name="broker"/>
													<s:hidden name="openCover"/>
													<s:hidden name="lcNum"/>
													<s:hidden name="from1"/>
													<s:hidden name="brokerName"/>
													<s:hidden name="custName"/>
												</td>
											</tr>
										</s:elseif>
										<s:else>
								 		 	<tr style="height: 10px"><td>&nbsp;</td></tr>
											<tr><td bgcolor="#FFFFFF"><table width="90%" align="center">
														<tr><td width="30%" align="center"><s:text name="label.cims.policy"/>&nbsp; : &nbsp;<b><s:property value="openCover"/></b></td>
															<td width="35%" align="center"><s:text name="user.broker"/>&nbsp; : &nbsp;<b><s:property value="brokerName"/></b></td>
															<td width="35%" align="center"><s:text name="label.quote.customer"/>&nbsp; : &nbsp;<b><s:property value="custName"/></b></td>
														</tr>
													</table>
											</td></tr>
											<tr style="height: 10px"><td>&nbsp;</td></tr>
											<tr><td class="heading"><s:label  key="lc.details" /></td></tr>
											<s:if test='"edit".equals(from)'>
												<tr style="height: 10px"><td>&nbsp;</td></tr>
												<tr><td bgcolor="#FFFFFF">
													<display:table name="lcList" pagesize="" requestURI="" class="table" uid="row" id="record">
														<display:setProperty name="paging.banner.one_item_found" value="" />
														<display:setProperty name="paging.banner.one_items_found" value="" />
														<display:setProperty name="paging.banner.all_items_found" value="" />
														<display:setProperty name="paging.banner.some_items_found" value="" />
														<display:setProperty name="paging.banner.placement"	value="bottom" />
														<display:setProperty name="paging.banner.onepage" value="" />
														<display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="LC Number" property="LC_NUMBER"/>
														<display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="Bank Name" property="BANK_NAME"/>
														<display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="LC Issue Date" property="LC_DATE"/>
														<display:column sortable="true" style="text-align:right;font-size:13px;height:30px;" title="LC Amount" property="LC_AMOUNT" format="{0,number,0.00}"/>
														<display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="Currency Name" property="CURRENCY_NAME"/>
														<display:column sortable="true" style="text-align:right;font-size:13px;height:30px;" title="Actual Amount (AED)" property="ACTUAL_AMT" format="{0,number,0.00}"/>
														<display:column sortable="true" style="text-align:right;font-size:13px;height:30px;" title="Used Amount (AED)" property="USED_AMT" format="{0,number,0.00}"/>
														<display:column sortable="true" style="text-align:right;font-size:13px;height:30px;" title="Available Balance (AED)" property="LC_BALANCE_AMOUNT" format="{0,number,0.00}"/>
													</display:table>
												</td></tr>
												<tr style="height: 10px"><td>&nbsp;</td></tr>
											</s:if>
											<tr><td  style="color:red;"><s:actionerror/> <s:actionmessage/> </td></tr>
					        					<tr>
					          						<td bgcolor="#FFFFFF"><table width="100%" border="0" cellspacing="0" cellpadding="0">
					               					<tr>	                                                 
				                						<td class="bg">
				                							<table width="100%" border="0" align="center" cellpadding="4" cellspacing="0">
					                     						<tr>
					                     							<td width="10%"></td>
					                       							<td width="35%" align="right"><s:label key="lc.bank.name"/> <font color="red">*</font></td>
					                       					    	<td width="35%"><s:select name="bank" id="bank" list="bankList" listKey="bank_id" listValue="bank_name"  headerKey="" headerValue="--select--" cssClass="inputSelect"/></td>
					                           						<td width="20%"></td>
					                           					</tr>
					                     						<tr>
					                     							<td width="10%"></td>
					                       							<td width="35%" align="right"><s:label key="lc.lc.no"/> <font color="red">*</font></td>
					                       							<s:if test='"edit".equals(from)'>
					                       								<td width="35%"><s:property value="lcNum"/><s:hidden name="lcNum"/><s:hidden name="lcId"/></td>
					                       							</s:if>
					                       							<s:else>
					                       					    		<td width="35%">
					                       					    		<s:textfield name="lcNum" id="lcNum" cssClass="inputBox" maxlength="25"/>
					                       					    		<s:hidden name="lcId"/>
					                       					    		</td>
					                       					    	</s:else>
					                           						<td width="20%"></td>
					                           					</tr>
					                           					<tr>
					                     							<td width="10%"></td>
					                       							<td width="35%" align="right"><s:label key="lc.start.date"/> <font color="red">*</font></td>
					                       					    	<td width="35%">
					                       					    		<div class="inputAppend"><sj:datepicker id="startDate" name="startDate" cssClass="inputBox1" displayFormat="mm/dd/yy" changeMonth="true" changeYear="true" minDate="-0D" readonly="true" cssStyle="width:80%; border: none; background-color: #ffffff;"/></div>					                       					    		
					                       					    	</td>
					                           						<td width="20%"></td>
					                           					</tr>
					                           					<tr>
					                     							<td width="10%"></td>
					                       							<td width="35%" align="right"><s:label key="lc.exp.date"/> <font color="red">*</font></td>
					                       					    	<td width="35%">
					                       					    		<div class="inputAppend"><sj:datepicker id="expDate" name="expDate" cssClass="inputBox1" displayFormat="mm/dd/yy" changeMonth="true" changeYear="true" minDate="-0D" readonly="true" cssStyle="width:80%; border: none; background-color: #ffffff;"/></div>
					                       					    	</td>
					                           						<td width="20%"></td>
					                           					</tr>
					                           					<tr>
					                     							<td width="10%"></td>
					                       							<td width="35%" align="right"><s:label key="lc.lc.currency"/> <font color="red">*</font></td>
					                       					    	<td width="35%"><s:select name="lcCurrency" id="lcCurrency" list="currencyList" listKey="currency_id" listValue="currency_name"  headerKey="" headerValue="--select--" cssClass="inputSelect"/></td>
					                           						<td width="20%"></td>
					                           					</tr>
					                           					<tr>
					                     							<td width="10%"></td>
					                       							<td width="35%" align="right"><s:label key="lc.lc.amt"/> <font color="red">*</font></td>
					                       					    	<td width="35%"><s:textfield name="lcAmt" id="lcAmt" cssClass="inputBox" maxlength="20"/></td>
					                           						<td width="20%"></td>
					                           					</tr>
					                           					<tr>
					                     							<td width="10%"></td>
					                       							<td width="35%" align="right"><s:label key="lc.sale.term"/> <font color="red">*</font></td>
					                       					    	<td width="35%"><s:select name="sale" id="sale" list="saleList" listKey="sale_term_id" listValue="sale_term_name"  headerKey="" headerValue="--select--" cssClass="inputSelect"/></td>
					                           						<td width="20%"></td>
					                           					</tr>
					                           					<tr>
					                     							<td width="10%"></td>
					                       							<td width="35%" align="right"><s:label key="lc.sactivate.now"/> <font color="red">*</font></td>
					                       					    	<td width="35%"><s:radio name="lcNumYN" id="lcNumYN" list="#{'Y':'Yes','N':'No'}" cssClass="input"/></td>
					                           						<td width="20%"></td>
					                           					</tr>
				                     							<tr><td height="5" colspan="5"></td></tr>
				                 							</table>
															<table width="100%" border="0" cellspacing="0" cellpadding="0">
																<tr>
																	<td height="25" align="center" valign="middle">
<%-- 																		<s:submit name="back" cssClass="btn" value="Back" action="lcDetailsLC"/>&nbsp;&nbsp;&nbsp;&nbsp; --%>
<%-- 																		<s:submit name="bck" cssClass="btn" value="Submit" action="saveLC"/> --%>
																		
																		<input type="button" class="btn" onclick="fnSub('lcDetailsLC.action');" value="Back" >&nbsp;&nbsp;&nbsp;&nbsp;
																		<input type="button" class="btn" onclick="fnSub('saveLC.action');" value="Submit">
																		
																	</td>
																</tr>
															</table>
															<s:hidden name="from"/>
															<s:hidden name="openCover"/>
															<s:hidden name="broker"/>
															<s:hidden name="from1"/>
															<s:hidden name="brokerName"/>
															<s:hidden name="custName"/>
															<s:hidden name="usedAmt" value="%{lcList[0].USED_AMT}"/>
														</td>
              										</tr>
          						 				</table>
          						 			</td>
        								</tr>
        							</s:else>
								</table>
							</s:form>
						</td>
					</tr>
				</table>
			</td>
		</tr>
        </table>
	</body>
</html>
