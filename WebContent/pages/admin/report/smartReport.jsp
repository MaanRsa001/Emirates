<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="/struts-jquery-tags" prefix="sj"%>
<html>
	<head>
		<sj:head jqueryui="true" jquerytheme="start" />
		<script>
			function stopRKey(evt) { 
			  var evt = (evt) ? evt : ((event) ? event : null); 
			  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
			  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
			}
			function fncommodity(policynumber, proposalNo ,loginId){
		     	var URL ="${pageContext.request.contextPath}/commodityAreport.action?policynumber="+policynumber+"&loginId="+loginId+"&proposalNo="+proposalNo;
		     	 popUp(URL, 800, 500);
		     }
		 </script>
	</head>
	<body>
    	<table width="100%">
 	        <tr><td height="5"></td></tr>
 			<tr>
   				<td><table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#E5E5E5">
     				<tr><td bgcolor="#FFFFFF" style="padding:10px; background:#F8F8F8">
				 			<s:form id="smart" name="smart" method="post" action="" theme="simple">
   					 			<table width="100%" border="0" cellspacing="0" cellpadding="0">
					 				<tr><td class="heading"><s:label key="label.smart.report" /></td></tr>
 		 							<tr style="height: 10px"><td>&nbsp;</td></tr>
  									<tr><td  style="color:red;"><s:actionerror/> <s:actionmessage/> </td></tr>
     								<tr><td>	                                                 
             								<table width="100%" border="0" align="center" cellpadding="4" cellspacing="0" bgcolor="#FFFFFF">
                  								<tr><td width="5%"></td>
		                     						<td width="35%" align="left"><s:text name="policy.report.startdate" /> <font color="red">*</font><br/>
		                     							<sj:datepicker name="startDate" cssClass="inputBox1" displayFormat="dd/mm/yy" changeMonth="true" changeYear="true" maxDate="+0D" readonly="true" cssStyle="width:150px;"/></td>
		                    						<td width="35%" align="left"><s:text name="policy.report.enddate"/> <font color="red">*</font><br/>
		                       							<sj:datepicker name="endDate" cssClass="inputBox1" displayFormat="dd/mm/yy" changeMonth="true" changeYear="true" maxDate="+0D" readonly="true" cssStyle="width:150px;"/></td>
		                    					    <td width="30%" align="left"><s:label key="policy.report.product" /> <font color="red">*</font><br/>
		                        						<s:select name="productID" id="productID" list="productList" headerKey="" headerValue="-Select-" listKey="PRODUCT_ID"  listValue="PRODUCT_NAME" cssClass="inputSelect" cssStyle="width:150px;"/></td>
		                        					<td width="5%"></td>
		                        				</tr>
		                  						<tr>
		                  							<td width="5%"></td>
		                        				   <td><s:label key="policy.report.select.br" /> <font color="red">*</font><br/>
		                        				   		<div style="overflow:scroll;overflow-x:hidden;height:100px;width:180px;" class="input">	
		                       								<s:checkboxlist name="brokers" id="brokers" list="brokerList" listKey="login_id"  listValue="COMPANY_NAME" cssStyle="vertical"/>
		                       							</div>
		                       						</td>
		                       						<td><s:label key="policy.report.orgination" /> <br/>
		                       							<div style="overflow:scroll;overflow-x:hidden;height:100px;width:180px;" class="input">	
		                       								<s:checkboxlist name="orginCountries" id="orginCountries" list="countrySmartList" listKey="COUNTRY_ID"  listValue="COUNTRY_NAME" cssStyle="vertical"/>
		                       							</div>
		                       						</td>
		                       						<td><s:label key="policy.report.destination" /> <font color="red">*</font><br/>
		                       							<div style="overflow:scroll;overflow-x:hidden;height:100px;width:180px;" class="input">	
		                       								<s:checkboxlist name="destCountries" id="destCountries" list="countrySmartList" listKey="COUNTRY_ID"  listValue="COUNTRY_NAME" cssStyle="vertical"/>
		                       							</div>
		                       						</td>
		                   						   
		                       					   <s:hidden name="busType" value="2"/>
		                       						<td width="5%"/>
		                   						</tr>
		                   						<tr>
		                  							<td width="5%"></td>
		                        				  	<%--<td><s:label key="policy.report.business.type" /> <font color="red">*</font><br/>
		                       							<s:radio name="busType" id="busType" list="#{'2':'All','1':'New Business','0':'Existing Business'}" cssClass="input"/></td>
		                   							--%>
		                   							<td><s:label key="policy.report.commodity" /> <br/>
		                       							<s:textarea name="commodity" id="commodity" cssClass="input" rows="3" readonly="true" /><input class="btn" value="..." type="button" name="commod" onclick="commodityselect()"/></td>
		                       					  	 <td colspan="2"></td>
		                       						<td width="5%"/>
		                   						</tr>
		                   						<tr style="height: 10px"><td>&nbsp;</td></tr>
		                   						<tr><td class="heading" colspan="5"><s:label key="policy.report.select.coverages" /></td></tr>
		                   						<tr>
		                  							<td width="5%"></td>
		                        				  	<td><s:label key="policy.report.select.coverages" /> <font color="red">*</font></td>
		                   							<td colspan="2">
		                   								<table>
                  											<s:iterator value="transportModeList" var="trans">
                  												<tr><td>
                  														<s:checkbox name="transportId" />
                  														<s:label value="%{#trans.TRANSPORT_DESCRIPTION}"></s:label>
		                   												<s:iterator value="coverList" var="cover" status="stat">
																				<s:if test='%{#trans.MODE_TRANSPORT_ID==#cover.MODE_TRANSPORT_ID}'>
																					<s:checkbox name="coverId"/><s:label value="%{#cover.COVER_NAME}"></s:label>
																				</s:if>
													        			</s:iterator>
										        				</td></tr>
                  											</s:iterator>
                  										</table>
		                   							</td>
		                       						<td width="5%"></td>
		                   						</tr>
		                   						<s:hidden name="rag"/>
		                   						<%--<tr>
		                  							<td width="5%"></td>
		                        				  	<td><s:label key="policy.report.select.rag" /> <font color="red">*</font></td>
		                   							<td colspan="2"><s:checkboxlist name="rag" list="#{'R':'R', 'A':'A','G':'G'}" /></td>
		                       						<td width="5%"></td>
		                   						</tr> --%>
        									</table>
        								</td>
      								</tr>
      								<tr>
										<td colspan="3">
											<table width="100%" border="0" cellspacing="0" cellpadding="0">
												<tr>
													<td height="25" align="center" valign="middle">
														<s:submit name="submit2" cssClass="btn" value="Submit" action="smartlistAreport"/>
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
			</td>
		</tr>
     </table>
	</body>
	<script>
		function commodityselect()
		{
			var URL='${pageContext.request.contextPath}/commoditySelectAreport.action';
			var windowName = "Commodity List";
			var width  = screen.availWidth;
			var height = screen.availHeight;
			var w = 700;
			var h = 400;
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
		}
	</script>

</html>
