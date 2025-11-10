
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
		<script>
			function stopRKey(evt) { 
			  var evt = (evt) ? evt : ((event) ? event : null); 
			  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
			  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
			} 
			document.onkeypress = stopRKey;
		</script>
	</head>

	<body>
		<table width="900px" border="0" align="center" cellpadding="0" cellspacing="0" >
			<tr>
   				<td bgcolor="#FFFFFF" style="background:#F8F8F8" valign="top" >
					 <s:form id="form1" name="form1" method="post" action="" theme="simple">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr class="bottomtext">
								<td class="heading">
									<s:label key="broker.brokermanagement" />
								</td>
							</tr>
							<tr>
			       				<td  style="color:red;"><s:actionerror/> <s:actionmessage/> </td>
			     			</tr>
							<tr align="center" class="royamenuhead">
								<td class="bottomtext"> <s:label key="broker.brokercode" /> : <s:property value="firstname"/>(<s:property value="agencyCode"/>) &nbsp;&nbsp; <s:label key="broker.brokerOrg" /> : <s:property value="borganization"/> </td>
							</tr>
							<tr align="center">
								<td>
									<table width="100%" border="0" cellspacing="4" cellpadding="0">
										<tr align="center" class="royamenuhead">
											<td align="left" class="bottomtext" width="7%"><s:label key="product.products"/></td>
											<td align="left" class="bottomtext" width="9%"><s:label key="product.commission" /></td>
											<td align="left" class="bottomtext" width="9%"><s:label key="product.suminsured" /></td>
											<td align="left" class="bottomtext" width="9%"><s:label key="product.minpremium" /></td>
											<td align="left" class="bottomtext" width="9%"><s:label key="product.loading" /></td>
											<td align="left" class="bottomtext" width="9%"><s:label key="product.discount" /></td>
											<td align="left" class="bottomtext" width="9%"><s:label key="product.backdays" /></td>
											<td align="left" class="bottomtext" width="10%"><s:label key="product.assignproduct" /></td>
											<td align="left" class="bottomtext" width="5%"><s:label key="product.provision" /></td>
											<td align="left" class="bottomtext" width="6%"><s:label key="product.remarks" /></td>
											<td align="left" class="bottomtext" width="6%"><s:label key="product.paymentrequired" /></td>
											<td align="left" class="bottomtext" width="6%"><s:label key="product.provisiondebit" /></td>
											<td align="left" class="bottomtext" width="6%"><s:label key="product.provisionloading" /></td>
										</tr>
										<s:if test='"new".equals(mode)'>
										<s:iterator  value="product_Det" status="status" var="product_Det">
											<tr class="formtxtf" align="center"><s:hidden name="product_id[%{#status.index}]" value="%{#product_Det.product_id}"/>
											<td><s:checkbox name="productStatus[%{#status.index}]"  fieldValue='true' /><s:property value="%{#product_Det.PRODUCT_NAME}"/></td>
											<td><s:textfield name="commission[%{#status.index}]" size="10"  /></td>
											<td><s:textfield name="insurance_End_Limit[%{#status.index}]" size="10" /></td>
											<td><s:textfield name="min_Premium_Amount[%{#status.index}]" size="10" /></td>
											<td><s:textfield name="loadingPremium[%{#status.index}]" size="10"  /></td>
											<td><s:textfield name="discountPremium[%{#status.index}]" size="10" /></td>
											<td><s:textfield name="back_Date_Allowed[%{#status.index}]" size="10" /></td>
											<td><input type="button" name="assignproduct" value="User(s)List" onclick="userPopups('<s:property value="agencyCode"/>','<s:property value="%{#commission_Det.productId}"/>','<s:property value="borganization"/>')"/></td>
											<td><s:radio name="user_Id_Creation[%{#status.index}]" id="user_Id_Creation[%{#status.index}]" list="#{'Y':'Yes','N':'No'}" /></td>
											<td><s:radio name="remark[%{#status.index}]" id="remark[%{#status.index}]" list="#{'Y':'Yes','N':'No'}" /></td>
											<td>&nbsp;</td>
											<td><s:radio name="freight[%{#status.index}]" id="freight[%{#status.index}]" list="#{'Y':'Yes','N':'No'}" /></td>
											<td><s:radio name="provision[%{#status.index}]" id="provision[%{#status.index}]" list="#{'Y':'Yes','N':'No'}"  /></td>
										</tr>
										<s:set name="agencyCode" var="broker_Code"/>
										</s:iterator>
										</s:if>
										<s:else>
											<s:iterator  value="commission_Det" status="status" var="commission_Det">
												<s:hidden name="commission_Det.productId" value="%{#commission_Det.productId}"/>
												<tr class="formtxtf" align="center">
													<td>	<s:checkbox name="productStatus[%{#status.index}]" fieldValue='%{#commission_Det.commission==""?false:true}' value="%{#commission_Det.commission}" /><s:property value="%{#commission_Det.PRODUCT_NAME}"/>
															<s:hidden name="product_id[%{#status.index}]" value="%{#commission_Det.productId}"/>
													</td>
													<td>	<s:textfield name="commission[%{#status.index}]" value="%{#commission_Det.commission}" size="10" />
													</td>
													<td>	<s:textfield name="insurance_End_Limit[%{#status.index}]" value="%{#commission_Det.insurance_End_Limit}" size="10" />
													</td>
													<td>	<s:textfield name="min_Premium_Amount[%{#status.index}]" value="%{#commission_Det.min_Premium_Amount}" size="10" />
													</td>
													<td>	<s:textfield name="loadingPremium[%{#status.index}]" value="%{#commission_Det.loadingPremium}" size="10"  />
													</td>
													<td>	<s:textfield name="discountPremium[%{#status.index}]" value="%{#commission_Det.discountPremium}" size="10" />
													</td>
													<td>
															<s:textfield name="back_Date_Allowed[%{#status.index}]" value="%{#commission_Det.back_Date_Allowed}" size="10" />
													</td>
													<td>
															<input type="button" value="User(s)List" onclick="userPopups('<s:property value="agencyCode"/>','<s:property value="%{#commission_Det.productId}"/>','<s:property value="borganization"/>')"/>
													</td>
													<td>
													<s:radio name="user_Id_Creation[%{#status.index}]" id="user_Id_Creation[%{#status.index}]"  list="#{'Y':'Yes','N':'No'}" value="%{#commission_Det.USER_ID_CREATION}" />
													</td>
													<td>
													<s:radio name="remark[%{#status.index}]" id="remark[%{#status.index}]" list="#{'Y':'Yes','N':'No'}" value="%{#commission_Det.remark}"  />
													</td>
													<td>
															&nbsp;
													</td>
													<td><s:radio name="freight[%{#status.index}]" id="freight[%{#status.index}]" list="#{'Y':'Yes','N':'No'}" value="%{#commission_Det.FREIGHT}" />
													</td>
													<td><s:radio name="provision[%{#status.index}]" id="provision[%{#status.index}]" list="#{'Y':'Yes','N':'No'}" value="%{#commission_Det.PROVISION}" />
													</td>
												</tr>
										</s:iterator>
										</s:else>
										<s:hidden name="agencyCode"/>
										<s:hidden name="borganization"/>
										<s:hidden name="firstname"/>
										<s:hidden name="mode" value="new"/>
										<s:hidden name="customer_id"/>
										<s:hidden name="login_Id"/>
									</table>
								</td>
							</tr>
							<tr><td>&nbsp;</td>
							</tr>
							<tr align="center">
								<td>
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td height="25" align="center" valign="middle">
													<s:submit action="editBrokerMgm" name="cancel1" cssClass="btn" value=" Cancel " />
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<s:submit name="submit1"  action="addProductBrokerMgm" value="Submit" cssClass="btn"/>
												</td>
											</tr>
									</table>
								</td>
							</tr>
						</table>
					</s:form>
				</td>
			</tr>
		</table>
	</body>
	<script>
		function userPopups(agencyCode,productId,borganization) 
		{
			var URL = "getBrokerUserInfoBrokerMgm.action?agencyCode="+agencyCode+"&productId="+productId+"&borganization="+borganization;
			var windowName = "USERSLIST";
			var width  = screen.availWidth;
			var height = screen.availHeight;
			var w = 750;
			var h = 460;
			var features =
				"width="          + w +
				",height="		  + h +
				",left="		  + ((0) * .5)  +
				",top="			  + ((0) * .5) +
				",directories=no" +
				",location=no"	  +
				",menubar=no"	  +
				",scrollbars=yes" +
				",status=no"	  +
				",toolbar=no"	  +
				",resizable=no";
			var strOpen = window.open (URL, windowName, features);
			strOpen.focus();
		}
	</script>
</html>