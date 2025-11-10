<!DOCTYPE HTML>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/struts-jquery-tags" prefix="sj"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<%@ page isELIgnored="false"%>
<html>
  <head>
  		<sj:head jqueryui="true" jquerytheme="start" />
  		<style type="text/css">
			.courier {
				color: #ffffff;
			}
		</style>
  </head>
  <body>
  <table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#E5E5E5">
	<tr>
		<td bgcolor="#FFFFFF" style="padding:10px; background:#F8F8F8">
			<s:if test ='reqFrom==null || "".equals(reqFrom)'>
	           <s:form id="closeTrn" name="closeTrn" method="post" action="saveTrnCloseTrn.action" theme="simple">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					 <tr><td></td></tr>
				   		<tr>
							<td class="heading"><s:text name="label.trnClosingDetails" /></td>
						</tr>
						<tr><td></td></tr>
						<tr> <td><font color="red"><s:actionerror/></font></td> </tr>
	  					<tr>	                                                 
	  						<td class="bg"><table width="100%" border="0" align="center" cellpadding="4" cellspacing="0">
	      						<tr>
	      							<td width="20%"></td>
	        						<td width="25%" align="right"><s:text name="label.policyOpenDate"/> <font color="red">*</font></td>
	           						<td width="3%"></td>
	        					    <td width="32%" align="left"><sj:datepicker name="openDate" cssClass="inputBox1" displayFormat="dd/mm/yy" changeMonth="true" changeYear="true" minDate="-2M" maxDate="+1M" readonly="true" /></td>
	            					<td width="20%"></td>
	            				</tr>
	      						<tr>
	      							<td width="20%"></td>
	        						<td width="25%" align="right"><s:text name="label.policyClosingDate"/> <font color="red">*</font></td>
	           						<td width="3%"></td>
	        					    <td width="32%" align="left"><sj:datepicker name="closeDate" cssClass="inputBox1" displayFormat="dd/mm/yy" changeMonth="true" changeYear="true" minDate="-1M" maxDate="+2M" readonly="true" /></td>
	            					<td width="20%"></td>
	            				</tr>
	            				<tr>
	      							<td width="20%"></td>
	        						<td width="25%" align="right"><s:text name="Month Close Date"/> <font color="red">*</font></td>
	           						<td width="3%"></td>
	        					    <td width="32%" align="left"><sj:datepicker name="monthCloseDate" cssClass="inputBox1" displayFormat="dd/mm/yy" changeMonth="true" changeYear="true" minDate="-2M" maxDate="+2M" readonly="true" /></td>
	            					<td width="20%"></td>
	            				</tr>
	              				<tr>
	              					<td width="20%"></td>
	        						<td width="25%" align="right"><s:text name="product.remarks"/> </td>
	           						<td width="3%"></td>
	        					    <td width="32%" align="left"><s:textarea name="remarks" rows="3" cssStyle="width: 90%;" /> </td>
	              					<td width="20%"></td>
	              				</tr>
	              				<tr>
	              					<td colspan="5" align="right">
	              						<a class="btn" type="button" href="viewListCloseTrn.action">View Report</a>
	              					</td>
	              				</tr>
	  						   </table>
	  						</td>
	   					</tr>
						<tr>
		    				<td>
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="25" align="center" valign="middle">
											<s:submit name="sub" value="Submit" cssClass="btn" />
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
	             </s:form>
	   		</s:if>
	   		<s:elseif test ='"insSuccess".equals(reqFrom)'>
	           <s:form id="closeTrn" name="closeTrn" method="post" action="saveTrnCloseTrn.action" theme="simple">
					 <table width="100%" border="0" cellspacing="0" cellpadding="0">
					 	<tr><td></td></tr>
				   		<tr>
							<td class="heading"><s:text name="label.trnClosingDetails" /></td>
						</tr>
	  					<tr>	                                                 
	  						<td class="bg"><table width="100%" border="0" align="center" cellpadding="4" cellspacing="0">
	      						<tr>
	      							<td align="center" colspan="4"><font style="color: red; font-weight: bold;">Record inserted Successfully</font></td>
	            				</tr>
	      						<tr>
	      							<td align="left" colspan="3"><font style="font-weight: bold;">TRN Closing Details Confirmations</font></td>
	      							<td align="right" ><font style="font-weight: bold;"><a class="btn" type="button" href="viewListCloseTrn.action">View Report</a></font></td>
	            				</tr>
	            				<tr><td colspan="3"></td></tr>
	            				<tr>
	            					<td width="20%"></td>
	      							<td align="left" colspan="3"><b><u>For the Month of <s:property value="description"/></u></b></td>
	            				</tr>
	              				<tr>
	              					<td width="20%"></td>
	              					<td width="10%"></td>
	              					<td width="20%">Policy Open Date</td>
	              					<td width="50%">:&nbsp;&nbsp;<font color="red"><s:property value="openDate"/></font></td>
	              				</tr>
	              				<tr>
	              					<td width="20%"></td>
	              					<td width="10%"></td>
	              					<td width="20%">Policy Close Date</td>
	              					<td width="50%">:&nbsp;&nbsp;<font color="red"><s:property value="closeDate"/></font></td>
	              				</tr>
	              				<tr>
	              					<td width="20%"></td>
	              					<td width="10%"></td>
	              					<td width="20%">Month Close Date</td>
	              					<td width="50%">:&nbsp;&nbsp;<font color="red"><s:property value="monthCloseDate"/></font></td>
	              				</tr>
	              				<tr>
	              					<td colspan="4" align="center">All policies generated from <font style="color: blue; font-weight: bold;"><s:property value="openDate"/></font> to <font style="color: blue; font-weight: bold;"><s:property value="closeDate"/></font> are inserted under <font style="color: blue; font-weight: bold;"><s:property value="closeNextDate"/></font></td>
	              				</tr>
	  						   </table>
	  						</td>
	   					</tr>
						<tr>
		    				<td>
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="25" align="center" valign="middle">
											<s:submit name="sub" value="Back" cssClass="btn" action="enterDetailCloseTrn"/>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
	             </s:form>
	   		</s:elseif>
		   <s:elseif test ='"list".equals(reqFrom)'>
		    <s:form id="viewCloseTrn" name="viewCloseTrn" method="post" action="viewTrnCloseTrn.action" theme="simple">
		    	<table cellspacing="2" cellpadding="2" border="0" width="100%" >
		    		<tr>
						<td class="heading">
							<s:text name="TRN Closing Repots" />
						</td>
					</tr>  
					<tr>
					<td>	
		            	<display:table name="viewList" pagesize="15" requestURI="viewListCloseTrn.action" class="table" uid="row" id="record" export="true" excludedParams="true">
							<display:setProperty name="paging.banner.one_item_found" value="" />
							<display:setProperty name="paging.banner.one_items_found" value="" />
							<display:setProperty name="paging.banner.all_items_found" value="" />
							<display:setProperty name="paging.banner.some_items_found" value="" />
							<display:setProperty name="paging.banner.placement"	value="bottom" />
							<display:setProperty name="paging.banner.onepage" value="" />
							<display:setProperty name="export.types" value="excel pdf" />
							<display:setProperty name="export.pdf" value="true" />
							<display:setProperty name="export.csv" value="false" />
							<display:setProperty name="export.xml" value="false" />
							<display:setProperty name="export.pdf.filename" value="Close TRN Report.pdf" />
							<display:setProperty name="export.excel.filename" value="Close TRN Report.xls" />
							
							<display:column style="text-align:center;width:10px" sortable="false" title="S.No" property="RUNNING_SNO" class="formtxtc" headerClass="courier"/>
							<display:column style="text-align:center;width:10px" sortable="false" title="Month" property="MONTH_DESC" class="formtxtc" headerClass="courier"/>
							<display:column style="text-align:center;width:10px" sortable="false" title="Policy Start Date" property="START_DATE" class="formtxtc" headerClass="courier"/>
							<display:column style="text-align:center;width:10px" sortable="false" title="Policy Close Date" property="END_DATE" class="formtxtc" headerClass="courier"/>
							<display:column style="text-align:center;width:10px" sortable="false" title="Future Date" property="FUTURE_DATE" class="formtxtc" headerClass="courier"/>
							<display:column style="text-align:center;width:10px" sortable="false" title="Remarks" property="REMARKS" class="formtxtc" headerClass="courier"/>
							<display:column style="text-align:center;width:10px" sortable="false" title="Entry Date" property="SYS_DATE" class="formtxtc" headerClass="courier"/>
							
						</display:table>
					</td>
				</tr>
				<tr>
	   				<td>
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td height="25" align="center" valign="middle">
									<s:submit name="sub" value="Back" cssClass="btn" action="enterDetailCloseTrn"/>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<s:hidden name="startDate"/>
			<s:hidden name="mode1" id="lmode1"/>
			<s:hidden name="endDate"/>
			<s:hidden name="productID"/>
			<s:hidden name="reportStatus"/>
			<s:hidden name="branch"/>
		</s:form>
	   	</s:elseif>
      </td>
    </tr>
 </table>
	<script type="text/javascript">
		
	</script>
</body>
</html>