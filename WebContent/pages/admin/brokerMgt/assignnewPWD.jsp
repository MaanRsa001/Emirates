<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<script>
			function stopRKey(evt) { 
			  var evt = (evt) ? evt : ((event) ? event : null); 
			  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
			  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
			} 
			document.onkeypress = stopRKey;
		
			function fnCall(from){
				if(from=='edit')
					document.newPwd.action = from+"BrokerMgm.action?mode=edit";
				else if(from=='userDetail')
					document.newPwd.action = "getABListUserMgm.action?mode1=broker";
				else if(from=='customerDetail')
					document.newPwd.action = "getABListCustomerMgm.action?mode1=broker";
				else if(from=='referal')
					document.newPwd.action = "getOCListReferal.action";
				else if(from=='openCover')
					document.newPwd.action = "opencoverOC.action";
				else
					document.newPwd.action = from+"BrokerMgm.action";
				document.newPwd.submit();
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
			
			function fnSubClick(value){
				document.newPwd.action = value;
				document.newPwd.submit();
			}
		</script>
	</head>
	<body>
		<div style="margin:10px 0;"></div>
    	<div class="easyui-layout" style="height:100vh;">
        <!-- <div data-options="region:'north'" style="height:50px"></div>
        <div data-options="region:'south',split:true" style="height:50px;"></div>
        <div data-options="region:'east',split:true" title="East" style="width:180px;">
        </div> -->
       <div data-options="region:'west',split:true" title="Options" style="width:150px;">
            <div class="easyui-accordion" data-options="fit:true,border:false">
            	<input type="button" class="btntab" value="View" onclick="fnCall('view')"/><br/>
                <input type="button" class="btntab" value="Edit" onclick="fnCall('edit')"/><br/>
                <input type="button" class="btntab" value="User Details" onclick="fnCall('userDetail')"/><br/>
                <input type="button" class="btntab" value="Customer Detail" onclick="fnCall('customerDetail')"/><br/>
                <input type="button" class="btntab" value="OpenCover" onclick="fnCall('openCover')"/><br/>
                <input type="button" class="btntab" value="Referral" onclick="fnCall('referal')"/><br/>
                <input type="button" class="btntab" value="Statistics" onclick="fnCall('statistics')"/><br/>
            </div>
        </div>
        <div data-options="region:'center',title:'<s:property value="borganization"/>(<s:property value="bcode"/>)',iconCls:'icon-ok'">
            <div class="easyui-tabs" data-options="fit:true,border:false,plain:true">
                <div title="Change Password" style="padding:5px">
					<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" >
						<tr>
							<td bgcolor="#FFFFFF" style="padding:10px; background:#F8F8F8">
   								 <s:form id="newPwd" name="newPwd" method="post" theme="simple">
       					 			<table width="100%" border="0" cellspacing="0" cellpadding="0">
   						 				<tr>
											<td class="heading"><s:label key="broker.brokermanagement"/></td>
				 		 				</tr>
     									<tr style="height: 25px">
       										 <td>&nbsp;</td>
       				 					</tr>
       				 					 <tr>
	         								<td  style="color:red;"><s:actionerror/> <s:actionmessage/> </td>
	       								 </tr>
         								<tr>                   
                 							<td class="bg">
                 								<table width="100%" border="0" align="center" cellpadding="4" cellspacing="0">
                 									 <s:if test='"passwordsuccess".equals(display)'>
                 										<tr><td><s:text name="broker.passwordsuccess"/></td></tr>
	       								 				<tr>
	                      									<td colspan="4" align="center">
<%-- 	                      									<s:submit action="getABListBrokerMgm" name="submit1" cssClass="btn" value=" Cancel " /> --%>
	                      									<input type="button" onclick="fnSubClick('getABListBrokerMgm.action')" name="back" class="btn" value="Back" />
	                      									</td>
	                      								</tr>
				       								 </s:if>
				       								 <s:else>
	                      								<tr>
	                      									<td width="2%">&nbsp;</td>
	                        								<td width="30%" align="right"> <s:label key="broker.broker"/> : </td>
	                        								<td width="30%"><s:property value="borganization"/>( <s:property value="agencyCode"/> ) </td>
	                         								<td width="38%">&nbsp;</td>
	                      								</tr>
	                      								<tr>
	                      									<td width="2%">&nbsp;</td>
	                      									<td width="30%" align="right"><s:label key="broker.nameNpassword"/> : </td>
	                      									<td width="30%"><s:password name="password" size="35" onkeyup="passwordStrength(this.value)" cssClass="form-control" maxlength="20"/></td>
	                      									<td width="38%"><s:label key="broker.passwordmessage"/> </td>
	                      								</tr>
	                      								<tr align="center">
			                          						<td> &nbsp;</td>
			                          						<td width="30%">&nbsp;</td>
		                            						<td colspan="2" align="left" style="padding-left: 10px;"><div id="passwordDescription">Password not entered</div><div id="passwordStrength" class="strength0"></div></td>
				                      					</tr>
	                      								<tr>
	                      									<td width="2%">&nbsp;</td>
	                      									<td width="30%" align="right"><s:label key="broker.nameRpassword"/> : </td>
	                      									<td width="30%"><s:password name="rpassword" size="35" cssClass="form-control" maxlength="20"/> </td>
	                      									<td width="38%">&nbsp;</td>
	                      								</tr>
	                      								<tr><td>&nbsp;</td></tr>
	                      								<tr>
	                      									<td colspan="4" align="center">
<%-- 	                      									<s:submit action="getABListBrokerMgm" name="submit1" cssClass="btn" value=" Cancel " /> --%>
<%--                  											<s:submit action="checkPwdBrokerMgm" name="submit1" cssClass="btn" value=" Submit " /> --%>
	                      											
	                      								<input type="button" onclick="fnSubClick('getABListBrokerMgm.action')" name="back" class="btn" value="Back" />
	                      								<input type="button" onclick="fnSubClick('checkPwdBrokerMgm.action')" name="sub" class="btn" value="Submit" />
	                      														
	                      														</td>
	                      								</tr>
	                      							</s:else>
												</table>
											</td>
										</tr>
	          						</table>
	          						<s:hidden name="agencyCode"/>
									<s:hidden name="borganization"/>
									<s:hidden name="bcode"/>
									<s:hidden name="firstname"/>
									<s:hidden name="login_Id"/>
	     						</s:form>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
		</div>
	</body>
</html>
