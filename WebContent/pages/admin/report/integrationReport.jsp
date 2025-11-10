<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/struts-jquery-tags" prefix="sj"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<%@ page isELIgnored="false"%>
<html>
  <head>
  		<sj:head jqueryui="true" jquerytheme="start" />
<%--   		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/cssbootstrap/bootstrap.min.css" /> --%>
  		<style type="text/css">
			.courier {
				color: #ffffff;
			}
			td, th {
		    	padding: 5px;
			}
		</style>
  </head>
  <body>
  <table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#E5E5E5">
	<tr>
		<td bgcolor="#FFFFFF" style="padding:10px; background:#F8F8F8">
		<s:if test ="reqFrom==null || reqFrom == ''">
           <s:form id="branch" name="branch" method="post" action="integrationAreport.action" theme="simple">
				 <table width="100%" border="0" cellspacing="0" cellpadding="0">
				 <tr><td></td></tr>
			   		<tr>
						<td class="heading"><s:text name="Integration Report" /></td>
					</tr>
					<tr>
      					<td  style="color:red;"><s:actionerror/> <s:actionmessage/> </td>
    				</tr>
  					<tr>	                                                 
  						<td class="bg"><table width="100%" border="0" align="center" cellpadding="4" cellspacing="0">
      						<tr>
      							<td width="15%"></td>
        						<td width="10%" align="right"><s:text name="policy.report.startdate"/> <font color="red">*</font></td>
           						<td width="3%"></td>
        					    <td width="32%" align="left"><sj:datepicker name="startDate" cssClass="inputBox1" displayFormat="dd/mm/yy" changeMonth="true" changeYear="true" maxDate="+0D" readonly="true"/></td>
            					<td width="20%"></td>
            				</tr>
      						<tr>
      							<td width="15%"></td>
        						<td width="10%" align="right"><s:text name="policy.report.enddate"/> <font color="red">*</font></td>
           						<td width="3%"></td>
        					    <td width="32%" align="left"><sj:datepicker name="endDate" cssClass="inputBox1" displayFormat="dd/mm/yy" changeMonth="true" changeYear="true" maxDate="+0D" readonly="true"/></td>
            					<td width="20%"></td>
            				</tr>
            				<tr>
      							<td width="15%"></td>
        						<td width="10%" align="right"><s:text name="policy.report.product"/> <font color="red">*</font></td>
           						<td width="3%"></td>
        					    <td width="32%" align="left"><s:select name="productID" id="productID" list="productList" headerKey="" headerValue="-Select-" listKey="PRODUCT_ID"  listValue="PRODUCT_NAME" cssClass="inputSelect"/></td>
              					<td width="20%"></td>
              				</tr>
              				<tr>
              					<td width="15%"></td>
        						<td width="10%" align="right"><s:text name="policy.report.Status"/> <font color="red">*</font></td>
           						<td width="3%"></td>
        					    <td width="50%" align="left"><s:radio list="#{'Y':'Sucess Status','F':'Failed Status','E':'Error Status'}" name="reportStatus" id="reportStatus" value="%{reportStatus==null?'P':reportStatus}" /></td>
              					<td width="20%"></td>
              				</tr>
              				<tr>
              					<td width="15%"></td>
        						<td width="10%" align="right"><s:text name="policy.report.Branch"/> <font color="red">*</font></td>
           						<td width="3%"></td>
        					    <td width="32%" align="left"><s:select name="branch" id="branch" list="branchName" headerKey="" headerValue="-Select-" listKey="BRANCH_CODE"  listValue="BRANCH_NAME" cssClass="inputSelect"/></td>
              					<td width="20%"></td>
              				</tr>
  						   </table>
  						</td>
   					</tr>
					<tr>
	    				<td>
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td height="25" align="center" valign="middle">
										<input type="button" name="sub" value="Submit" onclick="fnsubmit(this.form,'result','')" class="btn" />
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
             </s:form>
   		</s:if>
   		<s:elseif test="reqFrom=='policyList'">
   			<s:form id="branch2" name="branch2" method="post"  theme="simple">
   				<table cellspacing="2" cellpadding="2" border=0>
	    		<tr>
					<td class="heading">
					<s:if test='"reportBR".equals(reportStatus)'> <s:text name="Broker Report" /></s:if>
					<s:elseif test='"reportUW".equals(mode1)'> <s:text name="Underwriter Report" /></s:elseif>
					<s:else> <s:text name="Integration Report" /></s:else>
					</td>
				</tr>  
				<tr>
					<td  style="color:green;"><B><s:property value="integrationStatus"/> </B></td>
				</tr>  
			<tr>
				<td>	
	            <div style="overflow-x: scroll; width:1000px;"">
	            	
				<display:table name="branchList" pagesize="15" style="width:1000px"	requestURI="integrationResultAreport.action" class="table" uid="row"	id="record" export="true" excludedParams="true">
				<display:setProperty name="paging.banner.one_item_found"
					value="" />
				<display:setProperty name="paging.banner.one_items_found"
					value="" />
				<display:setProperty name="paging.banner.all_items_found"
					value="" />
				<display:setProperty name="paging.banner.some_items_found"
					value="" />
				<display:setProperty name="paging.banner.placement"
					value="bottom" />
				<display:setProperty name="paging.banner.onepage" value="" />
				<display:setProperty name="basic.empty.showtable" value="true" />
				<display:column style="text-align:center;width:10px" sortable="false" title="S.No" value="${record_rowNum}" class="formtxtc" headerClass="headerClass"/>
				
				<display:column sortable="false" style="text-align:center;width:10px"
				title="Quote No" property="QUOTE_NO" class="formtxtc" headerClass="headerClass"/>
				<display:column sortable="false" style="text-align:center;width:10px"
				title="Policy No" property="POLICY_NO" class="formtxtc" headerClass="headerClass"/>
				<display:column sortable="false" style="text-align:center;width:10px"
				title="Status" property="STATUS" class="formtxtc" headerClass="headerClass"/>
				<display:setProperty name="export.excel" value="true" />
                <display:setProperty name="export.excel.filename" value="intgStatusReports.xls" />
				<display:setProperty name="export.csv" value="false" />
				<display:setProperty name="export.xml" value="false" />
				<display:setProperty name="export.pdf" value="false" />
				</display:table>
				</div>
				</td>
			</tr>
			<tr>
   				<td>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="25" align="center" valign="middle">
								<input type="button" name="sub" value="Back" onclick="fnsubmit(this.form,'back1','')" class="btn" />
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
		<s:hidden name="quoteNo"/>
		<s:hidden name="applicationNo"/>
		<s:hidden name="policynumber"/>
   			</s:form>
   		</s:elseif>
	    <s:else>
	    <s:form id="branch1" name="branch1" method="post"  theme="simple">
	    	<table cellspacing="2" cellpadding="2" border=0>
	    		<tr>
					<td class="heading">
					<s:if test='"reportBR".equals(reportStatus)'> <s:text name="Broker Report" /></s:if>
					<s:elseif test='"reportUW".equals(mode1)'> <s:text name="Underwriter Report" /></s:elseif>
					<s:else> <s:text name="Integration Report" /></s:else>
					</td>
				</tr>  
				<tr>
					<td  style="color:green;"><B><s:property value="integrationStatus"/> </B></td>
				</tr>  
			<tr>
				<td>	
<!-- 	            <div style="overflow-x: scroll; width:900px;""> -->
	            <div style="overflow-x: scroll;width: 1368px;">
	            
	            	
				<display:table name="branchList" pagesize="15" style="width:1500px"	requestURI="integrationResultAreport.action" class="table" uid="row"	id="record" export="true" excludedParams="true">
				<display:setProperty name="paging.banner.one_item_found"
					value="" />
				<display:setProperty name="paging.banner.one_items_found"
					value="" />
				<display:setProperty name="paging.banner.all_items_found"
					value="" />
				<display:setProperty name="paging.banner.some_items_found"
					value="" />
				<display:setProperty name="paging.banner.placement"
					value="bottom" />
				<display:setProperty name="paging.banner.onepage" value="" />
				<display:setProperty name="basic.empty.showtable" value="true" />
				<display:column style="text-align:center;width:10px" sortable="false" title="S.No" value="${record_rowNum}" class="formtxtc" headerClass="headerClass"/>
				
				<s:if test='!"Y".equals(reportStatus)'><display:column title="Select" class="formtxtc" headerClass="headerClass" style="text-align:center;width:10px" >
     				 <input type="checkbox" name="multiIntegrate"  style="margin: 0 0 0 4px"  value="${record.POLICY_NO}" fieldValue="${record.POLICY_NO}"/>
 			 	</display:column>	
 			 	</s:if>
 			 	
 			 	 <display:column sortable="false" style="text-align:right;width:10px"
				title="Entry Date" property="ENTRY_DATE" class="formtxtc" headerClass="headerClass"/>
				<display:column sortable="false" style="text-align:center;width:10px"
				title="Login Id" property="LOGIN_ID" class="formtxtc" headerClass="headerClass"/>
				<display:column sortable="false" style="text-align:center;width:10px"
				title="Application Id" property="APPLICATION_ID" class="formtxtc" headerClass="headerClass"/>
				<display:column sortable="false" style="text-align:right;width:10px"
				title="Quote No" property="QUOTE_NO" class="formtxtc" headerClass="headerClass"/>
				<display:column sortable="false" style="text-align:right;width:10px"
				title="Policy No" property="POLICY_NO" class="formtxtc" headerClass="headerClass"/>
				<display:column sortable="false" style="text-align:right;width:10px"
				title="Inception Date" property="INCEPTION_DATE" class="formtxtc" headerClass="headerClass"/>
				<display:column sortable="false" style="text-align:right;width:10px"
				title="Product Id" property="PRODUCT_ID" class="formtxtc" headerClass="headerClass"/>
				<display:column sortable="false" style="text-align:center;width:10px"
				title="Customer Name" property="CUS_NAME" class="formtxtc" headerClass="headerClass"/>
				<display:column sortable="false" style="text-align:center;width:10px"
				title="Integration Status" property="INTEGRATION_STATUS" class="formtxtc" headerClass="headerClass"/>
				<display:column sortable="false" style="text-align:center;width:175px"
				title="Integration Message" property="INTEGRATION_ERROR" class="formtxtc" headerClass="headerClass"/>
				
				<s:if test='!"Y".equals(reportStatus)'>
				<display:column sortable="false" style="text-align:center;width:175px" title="Re Integrate" >
					<a href="#" onclick="getReIntegrate('${record.QUOTE_NO}','${record.PRODUCT_ID}','${record.APPLICATION_NO}','${record.POLICY_NO}')" > Re Integrate </a>
				 </display:column>
				 </s:if>
				 
				<display:setProperty name="export.excel" value="true" />
                <display:setProperty name="export.excel.filename" value="BranchReports.xls" />
				<display:setProperty name="export.csv" value="false" />
				<display:setProperty name="export.xml" value="false" />
				<display:setProperty name="export.pdf" value="false" />
				</display:table>
				</div>
				</td>
			</tr>
			<tr>
   				<td>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<s:if test='!"Y".equals(reportStatus)'>
								<td height="25" align="center" valign="middle">
									<input type="button" name="sub" value="Re Integrate" onclick="fnsubmit(this.form,'multiReIntegrate','')" class="btn" />
								</td>
							</s:if>
							<td height="25" align="center" valign="middle">
								<input type="button" name="sub" value="Back" onclick="fnsubmit(this.form,'Back','')" class="btn" />
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
		<s:hidden name="quoteNo"/>
		<s:hidden name="applicationNo"/>
		<s:hidden name="policynumber"/>
		
	</s:form>
   	</s:else>	
      </td>
    </tr>
    
 </table>
	<script type="text/javascript">
		function fnsubmit(frm,from,val) {
			if(from == 'result'){
				
				document.branch.action = "${pageContext.request.contextPath}/integrationResultAreport.action?reqFrom="+from;
				document.branch.submit();
			}else if(from == 'multiReIntegrate'){
				document.branch1.action = "${pageContext.request.contextPath}/multiReIntegrateAreport.action?reqFrom="+from;
				document.branch1.submit();
			
			}else if(from == 'back1'){
				document.branch2.action = "${pageContext.request.contextPath}/integrationAreport.action?reqFrom="+from;
				document.branch2.submit();
			
			}else{				
				document.branch1.action = "${pageContext.request.contextPath}/integrationAreport.action?reqFrom="+from;
				document.branch1.submit();
			}
		}
		
		
		function getReIntegrate(quoteNo,product,appNo,policyNo) {
				document.branch1.quoteNo.value=quoteNo;
				document.branch1.productID.value=product;
				document.branch1.applicationNo.value=appNo;
				document.branch1.policynumber.value=policyNo;
				document.branch1.action = "${pageContext.request.contextPath}/reIntegrateAreport.action";
				document.branch1.submit();
		}
	</script>
</body>
</html>
