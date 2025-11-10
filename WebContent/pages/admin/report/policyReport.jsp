<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<html>
	<head>
	    <sj:head jqueryui="true" jquerytheme="start" />
		<script>
		  
			function stopRKey(evt) { 
			  var evt = (evt) ? evt : ((event) ? event : null); 
			  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
			  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
			} 
			document.onkeypress = stopRKey;
			
			$(function(){
				try{
					var index = '<s:property value="index"/>';
					var t = $('#tabs');
					var tabs = t.tabs('tabs');
						t.tabs('select', tabs[index].panel('options').title);
				}catch(err){}
			});
			
			function forward(from, frm){
				if(from=='reportBR'){
					var startDate=document.getElementsByName('startDate')[1].value;
					var endDate=document.getElementsByName('endDate')[1].value;
					var pid=document.getElementById('productID1').value;
					var y=document.getElementById('broker1');
						var broker="";
						  for (var i = 0; i < y.options.length; i++) {
						     if(y.options[i].selected ==true){
						     	broker=broker+","+y.options[i].value;
						      }
						  }
				}else{
					var startDate=document.uwReport.startDate.value;
					var endDate=document.uwReport.endDate.value;
					var pid=document.getElementById('productID').value;
					var y=document.getElementById('broker');
						var broker="";
						  for (var i = 0; i < y.options.length; i++) {
						     if(y.options[i].selected ==true){
						     	broker=broker+","+y.options[i].value;
						      }
						  }
				}
				if(startDate==''){
					alert('Please Select Start Date');
				}else if(endDate==''){
					alert('Please Select End Date');
				}else if(pid==''){
					alert('Please Select Product');
				}else{
					if(from=='reportBR')
						document.getElementById('loaderImage1').style.display="block";
					else
						document.getElementById('loaderImage').style.display="block";
					postRequest('${pageContext.request.contextPath}/getRePolicyAreport.action?productID='+pid+'&mode1='+from+'&broker='+broker+'&startDate='+startDate+'&endDate='+endDate, from);
				}
		   	}
		   	function getPolicyReport(loginId,mode1, broker){
		   		try{
			   		document.getElementById('lstartDate').value=document.getElementById('startdate').value;
			   		document.getElementById('lendDate').value=document.getElementById('enddate').value;
			   		document.getElementById('lproductID').value=document.getElementById('productid').value;
			   		//document.getElementById('lmode1').value='<s:property value="mode1" />';
			   		//document.getElementById('lmode1').value=document.getElementById('mode1').value;
			   		document.getElementById('lmode1').value=mode1;
			   		//var broker=document.getElementById('rbroker').value;
			   		document.lform.action="branchResultAreport.action?reportStatus=P&broker="+broker+"&from1=report&loginId="+loginId;
			   		document.lform.submit();
			   	}catch(err){}
		   	}
		   	
		   	function fnBack(value){
		   		document.uwReport.action=value;
		   		document.uwReport.submit();
		   	}
		   	
  		</script>
  		<style type="text/css">
  		thead tr {
		    background-color: #337ab7;
		    color : white;
		}
  		</style>
	</head>
	<body>
    <div style="margin:10px 0;"></div>
    <div class="easyui-layout" style="height:100vh;">
        <div data-options="region:'center',title:'Reports',iconCls:'icon-ok'">
            <div class="easyui-tabs" data-options="fit:true,border:false,plain:true" id="tabs">
                <div title="UnderWriter Report" style="padding:10px" >
                		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#E5E5E5">
						      <tr>
						        <td bgcolor="#FFFFFF" style="padding:10px; background:#F8F8F8">
			   					 <s:form id="" name="uwReport" method="post" action="getRePolicyAreport.action?mode1=uw" theme="simple">
			       					 <table width="100%" border="0" cellspacing="0" cellpadding="0" id="uwReport">
			       					 	<tr><td></td></tr>
			   						 	<tr>
											<td class="heading"><s:text name="label.under.writer" /></td>
							 		 	</tr>
							 		 	<s:if test='"reportUW".equals(mode1)'>
							 		 		<tr id="actionerrortext">
												<td><font color="red"><s:actionerror/></font></td>
							 		 		</tr>
							 		 	</s:if>
										<tr style="height: 10px"><td>&nbsp;</td></tr>
			         					<tr>
			           						 <td bgcolor="#FFFFFF">
			           							<div id="reportUW">
			           								<s:if test='%{"ajax".equals(from1) && "0".equals(index)}'>
			           						 			<table width="100%" align="center">
															<tr><td><table width="90%" align="center"><tr><td width="50%" align="center"><s:text name="policy.report.startdate"/>&nbsp; : &nbsp;<s:property value="startDate"/></td><s:hidden name="startDate" id="startdate"/>
																										  <td width="50%" align="center"><s:text name="policy.report.enddate"/>&nbsp; : &nbsp;<s:property value="endDate"/></td><s:hidden name="endDate" id="enddate"/><s:hidden name="productID" id="productid"/>
																										</tr>
																	</table>
															</td></tr>
															<tr><td colspan="5" align="center"> <s:property value="broker"/>
																	<display:table name="policyList" pagesize="10" requestURI="getRePolicyAreport.action?from1=ajax" class="table" uid="row" id="record" excludedParams="from1" style="text-align:center;font-size:13px;">
										                                <display:setProperty name="paging.banner.one_item_found" value="" />
										                                <display:setProperty name="paging.banner.one_items_found" value="" />
										                                <display:setProperty name="paging.banner.all_items_found" value="" />
										                                <display:setProperty name="paging.banner.some_items_found" value="" />
										                                <display:setProperty name="paging.banner.placement" value="bottom" />
										                                <display:setProperty name="paging.banner.onepage" value="" />
										                                <display:column sortable="false" style="text-align:left;font-size:13px;height:30px;" title="Broker Name" property="COMPANY_NAME"/>
										                                <display:column sortable="false" style="text-align:left;font-size:13px;height:30px;" title="RSA Branch Name" property="RSA_BRANCH_NAME"/>
										                                <display:column sortable="false" style="text-align:right;font-size:13px;height:30px;" title="No.Of Policies" >
																		<display:column sortable="false" style="text-align:left;font-size:13px;height:30px;" title="Core Application Policy No" property="missippi_opencover_no"/>
										                                 <a href="#" onclick="getPolicyReport('${record.UW_LOGIN_ID}','reportUW','${record.LOGIN_ID}')" >${record.POLICY} </a>
										                                </display:column>
										                                <display:column sortable="false" style="text-align:right;font-size:13px;height:30px;" title="Premium" property="premium" format="{0,number,#,##0.00}"/>
										                                <display:column sortable="false" style="text-align:right;font-size:13px;height:30px;" title="Commission" property="COMMISSION" format="{0,number,#,##0.00}"/>
										                        </display:table>
															</td></tr>
															<tr><td height="5px;"><s:hidden name="index"/><s:hidden name="mode1" id="mode1"/><s:hidden name="broker" id="rbroker"/></td></tr>
															<tr><td colspan="5"><table width="90%" align="center"><tr><td align="center">
<%-- 															<s:submit name="bck" value="Back" cssClass="btn" action="policyAreport"/> --%>
															<input type="button" value="Back" onclick="fnBack('policyAreport.action')">
															</td></tr></table></td></tr>
														</table>
			           						 		</s:if>
			           						 		<s:else>
				           								<table width="100%" border="0" cellspacing="0" cellpadding="0">
						                					<tr>	                                                 
						                 						<td class="bg"><table width="100%" border="0" align="center" cellpadding="4" cellspacing="0">
						                      						<tr>
						                      							<td width="20%"></td>
						                        						<td width="5%" align="right"><s:text name="policy.report.startdate"/> <font color="red">*</font></td>
						                           						<td width="3%"></td>
						                        					    <td width="32%" align="left">
						                        					    <sj:datepicker name="startDate" id="startDate" cssClass="inputBox1"  displayFormat="dd/mm/yy" readonly="true"  duration="fast"  />
						                        					    </td>
						                            					<td width="20%"></td>
						                            				</tr>
						                      						<tr>
						                      							<td width="20%"></td>
						                        						<td width="5%" align="right"><s:text name="policy.report.enddate"/> <font color="red">*</font></td>
						                           						<td width="3%"></td>
						                        					    <td width="32%" align="left">
						                        					    <sj:datepicker name="endDate" id="endDate" cssClass="inputBox1"  displayFormat="dd/mm/yy" readonly="true"  duration="fast"  />
						                        					    </td>
						                            					<td width="20%"></td>
						                            				</tr>
						                            				<tr>
						                      							<td width="20%"></td>
						                        						<td width="5%" align="right"><s:text name="policy.report.select.uw"/> <font color="red">*</font></td>
						                           						<td width="3%"></td>
						                        					    <td width="32%" align="left"><s:select name="broker" id="broker" list="uwList" headerKey="ALL" headerValue="-ALL-" listKey="login_id"  listValue="username" cssClass="inputSelect"/></td>
						                            					<td width="20%"></td>
						                            				</tr>
						                            				<tr>
						                      							<td width="20%"></td>
						                        						<td width="5%" align="right"><s:text name="policy.report.product"/> <font color="red">*</font></td>
						                           						<td width="3%"></td>
						                        					    <td width="32%" align="left"><s:select name="productID" id="productID" list="productList" headerKey="" headerValue="-Select-" listKey="PRODUCT_ID"  listValue="PRODUCT_NAME" cssClass="inputSelect"/></td>
						                            					<td width="20%"></td>
						                            				</tr>
						                      					   <tr><td colspan="5"></td></tr>
						               						   </table></td>
						               						</tr>
						               						 <tr>
						            							<td height="2" bgcolor="#FFFFFF"></td>
						      							    </tr>
						        						    <tr><td>
																	<table width="100%" border="0" cellspacing="0" cellpadding="0">
																		<tr>
																			<td height="25" align="center" valign="middle">
																				<input type="button" name="submit1" class="btn" value="Submit" onclick="forward('reportUW',this.form)"/>
																			</td>
																		</tr>
																	</table>
																</td>
															</tr>
															<tr align="center">
																<td height="20" colspan="2" >
																	<div id="loaderImage" style="display:none">
																		<br>
																			<img src='${pageContext.request.contextPath}/images/ajax-loader.gif' width="50px" height="50px"/>
																		<br>
																	</div>
																</td>
															</tr>
													</table>
												</s:else>
											</div>
											</td></tr>
                    				</table>
                    			</s:form>
                    		</td>
                    	</tr>
                    </table>
                </div>
                <div title="Broker Report" style="padding:10px" >
                		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#E5E5E5">
						      <tr>
						        <td bgcolor="#FFFFFF" style="padding:10px; background:#F8F8F8">
			   					 <s:form id="" name="brReport" method="post" action="getRePolicyAreport.action?mode1=br" theme="simple">
			       					 <table width="100%" border="0" cellspacing="0" cellpadding="0" id="brReport">
			       					 	<tr><td></td></tr>
			   						 	<tr>
											<td class="heading"><s:text name="label.broker.report" /></td>
							 		 	</tr>
							 		 	<tr style="height: 10px"><td>&nbsp;</td></tr>
							 		 	<s:if test='"reportBR".equals(mode1)'>
				     						<tr id="actionerrortext"><td  style="color:red;"><s:actionerror/> <s:actionmessage/> </td></tr>
					       				</s:if>
										<tr style="height: 10px"><td>&nbsp;</td></tr>
			         					<tr>
			           						 <td bgcolor="#FFFFFF">
			           						 	<div id="reportBR">
			           						 		<s:if test='%{"ajax".equals(from1) && "1".equals(index)}'>
			           						 			<table width="100%" align="center">
															<tr><td><table width="90%" align="center"><tr><td width="50%" align="center"><s:text name="policy.report.startdate"/>&nbsp; : &nbsp;<s:property value="startDate"/></td><s:hidden name="startDate" id="startdate"/>
																										  <td width="50%" align="center"><s:text name="policy.report.enddate"/>&nbsp; : &nbsp;<s:property value="endDate"/></td><s:hidden name="endDate" id="enddate"/><s:hidden name="productID" id="productid"/>
																										</tr>
																	</table>
															</td></tr>
															<tr><td colspan="5" align="center">
																	<display:table name="policyList" pagesize="10" requestURI="getRePolicyAreport.action?from1=ajax" class="table" uid="row" id="record" excludedParams="from1" style="text-align:center;font-size:13px;">
										                                <display:setProperty name="paging.banner.one_item_found" value="" />
										                                <display:setProperty name="paging.banner.one_items_found" value="" />
										                                <display:setProperty name="paging.banner.all_items_found" value="" />
										                                <display:setProperty name="paging.banner.some_items_found" value="" />
										                                <display:setProperty name="paging.banner.placement" value="bottom" />
										                                <display:setProperty name="paging.banner.onepage" value="" />
										                                <display:column sortable="false" style="text-align:left;font-size:13px;height:30px;" title="Broker Name" property="COMPANY_NAME"/>
										                                <display:column sortable="false" style="text-align:center;font-size:13px;height:30px;" title="No.Of Policies" >
										                                   <a href="#" onclick="getPolicyReport('${record.LOGIN_ID}','reportBR','${record.LOGIN_ID}')" >${record.POLICY} </a>
										                                </display:column>
										                                <display:column sortable="false" style="text-align:right;font-size:13px;height:30px;" title="Premium" property="premium" format="{0,number,#,##0.00}"/>
										                                <display:column sortable="false" style="text-align:right;font-size:13px;height:30px;" title="Commission" property="COMMISSION" format="{0,number,#,##0.00}"/>
										                        </display:table>
															</td></tr>
															<tr><td height="5px;"><s:hidden name="index"/><s:hidden name="mode1" id="mode1"/> <s:hidden name="broker" id="rbroker"/></td></tr>
															<tr><td colspan="5"><table width="90%" align="center"><tr><td align="center">
<%-- 															<s:submit name="bck" value="Back" cssClass="btn" action="policyReport"/> --%>
															<input type="button" value="Back" onclick="fnBack('policyReport.action')">
															
															</td></tr></table></td></tr>
														</table>
			           						 		</s:if>
			           						 		<s:else>
					           						 <table width="100%" border="0" cellspacing="0" cellpadding="0">
					                					<tr>	                                                 
					                 						<td class="bg"><table width="100%" border="0" align="center" cellpadding="4" cellspacing="0">
					                      						<tr>
					                      							<td width="20%"></td>
					                        						<td width="5%" align="right"><s:text name="policy.report.startdate"/> <font color="red">*</font></td>
					                           						<td width="3%"></td>
					                        					    <td width="32%" align="left">
					                        					    <sj:datepicker name="startDate" id="startDate1" cssClass="inputBox1"  displayFormat="dd/mm/yy" readonly="true"  duration="fast"  />
					                        					    </td>
					                            					<td width="20%"></td>
					                            				</tr>
					                      						<tr>
					                      							<td width="20%"></td>
					                        						<td width="5%" align="right"><s:text name="policy.report.enddate"/> <font color="red">*</font></td>
					                           						<td width="3%"></td>
					                        					    <td width="32%" align="left">
					                        					    <sj:datepicker name="endDate" id="endDate1" cssClass="inputBox1"  displayFormat="dd/mm/yy" readonly="true"  duration="fast"  />
					                        					    </td>
					                            					<td width="20%"></td>
					                            				</tr>
					                            				<tr>
					                      							<td width="20%"></td>
					                        						<td width="5%" align="right"><s:text name="policy.report.select.br"/> <font color="red">*</font></td>
					                           						<td width="3%"></td>
					                        					    <td width="32%" align="left"><s:select name="broker" id="broker1" list="brokerList" headerKey="ALL" headerValue="-ALL-" listKey="login_id"  listValue="COMPANY_NAME" cssClass="inputSelect"/></td>
					                            					<td width="20%"></td>
					                            				</tr>
					                            				<tr>
					                      							<td width="20%"></td>
					                        						<td width="5%" align="right"><s:text name="policy.report.product"/> <font color="red">*</font></td>
					                           						<td width="3%"></td>
					                        					    <td width="32%" align="left"><s:select name="productID" id="productID1" list="productList" headerKey="" headerValue="-Select-" listKey="PRODUCT_ID"  listValue="PRODUCT_NAME" cssClass="inputSelect"/></td>
					                            					<td width="20%"></td>
					                            				</tr>
					                      					   <tr><td colspan="5"></td></tr>
					               						   </table></td>
					               						</tr>
					               						 <tr>
					            							<td height="2" bgcolor="#FFFFFF"></td>
					      							    </tr>
					        						    <tr><td>
																<table width="100%" border="0" cellspacing="0" cellpadding="0">
																	<tr>
																		<td height="25" align="center" valign="middle">
																			<input type="button" name="submit1" class="btn" value="Submit" onclick="forward('reportBR',this.form)"/>
																		</td>
																	</tr>
																</table>
															</td>
														</tr>
														<tr align="center">
															<td height="20" colspan="2" >
																<div id="loaderImage1" style="display:none">
																	<br>
																		<img src='${pageContext.request.contextPath}/images/ajax-loader.gif' width="50px" height="50px"/>
																	<br>
																</div>
															</td>
														</tr>
													</table>
												</s:else>
											</div>
										</td></tr>
                    				</table>
                    			</s:form>
                    		</td>
                    	</tr>
                    </table>
                    <s:form name="lform" id="lform">
					<s:hidden name="startDate" id="lstartDate"/>
					<s:hidden name="endDate" id="lendDate"/><s:hidden name="productID" id="lproductID"/>
					<s:hidden name="index"/><s:hidden name="mode1" id="lmode1"/><s:hidden name="pvapplication"/>

				</s:form>
                </div>
            </div>
        </div>
    	</div>
	</body>
</html>
