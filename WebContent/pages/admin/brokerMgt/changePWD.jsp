<%@ taglib prefix="s" uri="/struts-tags" %>
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
		<div class="easyui-layout" style="height:100vh;">
			<table width="900" border="0" align="center" cellpadding="0" cellspacing="0" >
				<tr>
					<td valign="top">
						<s:form id="brokeredit" name="brokeredit" method="post"  action="checkPwdBrokerMgm" theme="simple">
						<table width="650">
							<tr><td style="color:red;"><s:actionerror/><s:actionmessage/></td></tr>
							<s:if test="'passwordsuccess'.equals(display)">
									<tr>
			   							<td bgcolor="#FFFFFF" style="padding:10px; background:#F8F8F8">
			   									<s:label key="broker.passwordsuccess"/>
										</td>
									</tr>
									<tr><td>&nbsp;</td>
									</tr>
									<tr>
			   							<td align="center"><s:submit name="back11" action="getABListBrokerMgm" cssClass="btn" value=" Proceed "/></td>
	                      			</tr>
							</s:if>
							<s:else>
								<tr>
									<td  style="color:red;">
										<s:if test="'invalid'.equals(display)">
											<s:label key="error.invalid"/>
										</s:if>
										<s:elseif test="'different'.equals(display)">
											<s:label key="error.different"/>
										</s:elseif>
									</td>
								</tr>
								<tr>
									<td bgcolor="#FFFFFF" style="padding:10px; background:#F8F8F8">
	   								
	       					 			<table width="100%" border="0" cellspacing="0" cellpadding="0">
	   						 				 <tr>
	    										<td  style="color:red;"></td>
	   						  				</tr>
	   						 				 <tr>
												<td class="heading"><s:label key="broker.brokermanagement"/></td>
					 		 				</tr>
	     									<tr style="height: 25px">
	       										 <td>&nbsp;</td>
	       				 					</tr>
	         								<tr>                   
	                 							<td class="bg">
	                 								<table width="100%" border="0" align="center" cellpadding="4" cellspacing="0">
	                 									<tr>
	                      									<td width="2%">&nbsp;</td>
	                        								<td width="30%" align="right"> <s:label key="broker.broker"/> : </td>
	                        								<td width="30%"><s:property value="firstname"/>( <s:property value="agencyCode"/> ) </td>
	                         								<td width="38%">&nbsp;</td>
	                         									<s:hidden name="display"/>
	                      								</tr>
	                      								<s:if test="'newlogin'.equals(display1)">
		                      								<tr>
		                      									<td width="2%">&nbsp;</td>
		                      									<td width="30%" align="right"><s:label key="broker.new"/> : </td>
		                      									<td width="30%"><s:textfield name="loginid" cssClass="inputBox" size="20"/> </td>
		                      									<td width="38%"></td>
		                      								</tr>
		                      								<tr>
		                      									<td width="2%">&nbsp;</td>
		                      									<td width="30%" align="right"><s:label key="broker.password"/> : </td>
		                      									<td width="30%"><s:password name="password" size="35" cssClass="inputBox" maxlength="20"/> </td>
		                      									<td width="38%"><s:label key="broker.passwordmessage"/> </td>
		                      								</tr>
		                      							</s:if>
		                      							<s:else>
	                      									<tr>
		                      									<td width="2%">&nbsp;</td>
		                      									<td width="30%" align="right"><s:label key="broker.nameNpassword"/> : </td>
		                      									<td width="30%"><s:password name="newpassword" size="35" cssClass="inputBox" maxlength="20"/> </td>
		                      									<td width="38%"><s:label key="broker.passwordmessage"/> </td>
	                      									</tr>
	                      								</s:else>
	                      								<tr>
	                      									<td width="2%">&nbsp;</td>
	                      									<td width="30%" align="right"><s:label key="broker.nameRpassword"/> : </td>
	                      									<td width="30%"><s:password name="repassword" size="35" cssClass="inputBox" maxlength="20"/> </td>
	                      									<td width="38%"></td>
	                      								</tr>
	                      								<tr><td>&nbsp;</td></tr>
	                      								<s:if test="'newlogin'.equals(display1)">
		                      								<tr>
		                      									<td width="2%">&nbsp;</td>
		                      									<td width="30%">&nbsp;</td>
		                      									<td width="30%" align="center"><s:submit action="newBrokerBrokerMgm" name="submit1" cssClass="btn" value="Cancel" /></td>
		                      									<td width="38%" align="left"><s:submit name="submit1" action="newloginBrokerMgm" cssClass="btn" value="Submit" /></td>
		                      								</tr>
	                      								</s:if>
	                      								<s:else>
		                      								<tr>
		                      									<td width="2%">&nbsp;</td>
		                      									<td width="30%">&nbsp;</td>
		                      									<td width="30%" align="center"><s:submit name="back" cssClass="btn" action="getABListBrokerMgm" value="Back"/>&nbsp;&nbsp;&nbsp;&nbsp;</td>
		                      									<td width="38%" align="left"><s:submit name="submit1" cssClass="btn" value="Submit" /></td>
		                      								</tr>
		                      							</s:else>
													</table>
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
		</div>
	</body>
</html>
