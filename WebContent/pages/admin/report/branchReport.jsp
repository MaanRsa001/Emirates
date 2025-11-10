<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/struts-jquery-tags" prefix="sj"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<%@ page isELIgnored="false"%>
<html>
  <head>
  		<sj:head jqueryui="true" jquerytheme="start" />
<%--         <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/cssbootstrap/bootstrap.min.css" /> --%>
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
  <table width="100%" border="0"  cellpadding="0" cellspacing="1" bgcolor="#E5E5E5">
	<tr>
		<td bgcolor="#FFFFFF" style="padding:10px; background:#F8F8F8">
		<s:if test ="reqFrom==null || reqFrom == ''">
           <s:form id="branch" name="branch" method="post" action="branchAreport.action" theme="simple">
          		 <div align="justify">
					 <table width="100%" border="0" cellspacing="0" cellpadding="10">
					 <tr><td></td></tr>
				   		<tr>
							<td class="heading"><s:text name="Branch Report" /></td>
						</tr>
						<tr>
	      					<td  style="color:red;"><s:actionerror/> <s:actionmessage/> </td>
	    				</tr>
	  					<tr>	                                                 
	  						<td class="bg"><table width="100%" border="0" align="center" cellpadding="4" cellspacing="0">
	      						<tr>
	      							<td width="20%"></td>
	        						<td width="5%" align="left"><s:text name="policy.report.startdate"/> <font color="red">*</font></td>
	           						<td width="3%"></td>
	        					    <td width="32%" align="left"><sj:datepicker name="startDate" cssClass="inputBox1" displayFormat="dd/mm/yy" changeMonth="true" changeYear="true" maxDate="+0D" readonly="true"/></td>
	            					<td width="20%"></td>
	            				</tr>
	      						<tr>
	      							<td width="20%"></td>
	        						<td width="5%" align="left"><s:text name="policy.report.enddate"/> <font color="red">*</font></td>
	           						<td width="3%"></td>
	        					    <td width="32%" align="left"><sj:datepicker name="endDate" cssClass="inputBox1" displayFormat="dd/mm/yy" changeMonth="true" changeYear="true" maxDate="+0D" readonly="true"/></td>
	            					<td width="20%"></td>
	            				</tr>
	            				<tr>
	      							<td width="20%"></td>
	        						<td width="5%" align="left"><s:text name="policy.report.product"/> <font color="red">*</font></td>
	           						<td width="3%"></td>
	        					    <td width="32%" align="left"><s:select name="productID" id="productID" list="productList" headerKey="" headerValue="-Select-" listKey="PRODUCT_ID"  listValue="PRODUCT_NAME" cssClass="inputSelect"/></td>
	              					<td width="20%"></td>
	              				</tr>
	              				<tr>
	              					<td width="20%"></td>
	        						<td width="5%" align="left"><s:text name="policy.report.Status"/> <font color="red">*</font></td>
	           						<td width="3%"></td>
	        					    <td width="32%" align="left"><s:radio list="#{'P':'Policy Status','Y':'Quote Status'}" name="reportStatus" id="reportStatus" value="%{reportStatus==null?'P':reportStatus}" /></td>
	              					<td width="20%"></td>
	              				</tr>
	              				<tr>
	              					<td width="20%"></td>
	        						<td width="5%" align="left"><s:text name="policy.report.Branch"/> <font color="red">*</font></td>
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
				</div>
             </s:form>
   		</s:if>
	    <s:else>
	    <div class="easyui-layout" style="height:100vh;">
	    <s:form id="branch1" name="branch1" method="post" action="branchAreport.action" theme="simple">
	    	<table cellspacing="2" cellpadding="2" border=0>
	    		<tr>
					<td class="heading">
					<s:if test='"reportBR".equals(mode1)'> <s:text name="Broker Report" /></s:if>
					<s:elseif test='"reportUW".equals(mode1)'> <s:text name="Underwriter Report" /></s:elseif>
					<s:else> <s:text name="Branch Report" /></s:else>
					</td>
				</tr>  
			<tr>
				<td>	
				<br>
				<br>
<!-- 	            <div style="width:1000px;overflow:scroll;overflow-y:hidden;">	 -->
<!-- 	            <div style="overflow:scroll;overflow-y:hidden;width: 1300px;"> -->
	            <div style="overflow-x: scroll;overflow-y: scroll;width: 1350px;height:600px">
				<display:table name="branchList" pagesize="10" style="width:6000px"	requestURI="branchResultAreport.action" class="table" uid="row"	id="record" export="true" excludedParams="true">
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
				<display:column sortable="false" style="text-align:right;width:10px"
				title="Entry Date" property="ENTRY_DATE" class="formtxtc" headerClass="headerClass"/>
				<display:column sortable="false" style="text-align:center;width:10px"
				title="Branch Name" property="BRANCH_NAME" class="formtxtc" headerClass="headerClass"/>
				<display:column sortable="false" style="text-align:center;width:10px"
				title="Login Id" property="LOGIN_ID" class="formtxtc" headerClass="headerClass"/>
				<display:column sortable="false" style="text-align:center;width:10px"
				title="Application Id" property="APPLICATIONID" class="formtxtc" headerClass="headerClass"/>
				<display:column sortable="false" style="text-align:right;width:10px"
				title="Quote No" property="QUOTE_NO" class="formtxtc" headerClass="headerClass"/>
				<display:column sortable="false" style="text-align:right;width:10px"
				title="Application No" property="APPLICATION_NO" class="formtxtc" headerClass="headerClass"/>
				<display:column sortable="false" style="text-align:right;width:10px"
				title="Policy No" property="POLICY_NO" class="formtxtc" headerClass="headerClass"/>
				<display:column sortable="false" style="text-align:right;width:10px"
				title="Inception Date" property="INCEPTION_DATE" class="formtxtc" headerClass="headerClass"/>
				<display:column sortable="false" style="text-align:right;width:10px"
				title="Effective Date" property="EFFECTIVE_DATE" class="formtxtc" headerClass="headerClass"/>
				<display:column sortable="false" style="text-align:right;width:10px"
				title="Product Id" property="PRODUCT_ID" class="formtxtc" headerClass="headerClass"/>
				<display:column sortable="false" style="text-align:center;width:10px"
				title="Customer Name" property="CUSTOMER_NAME" class="formtxtc" headerClass="headerClass"/>
				<display:column sortable="false" style="text-align:center;width:10px"
				title="Transport Desc" property="TRANSPORT_DESCRIPTION" class="formtxtc" headerClass="headerClass"/>
				<display:column sortable="false" style="text-align:center;width:10px"
				title="Cover Name" property="COVER_NAME" class="formtxtc" headerClass="headerClass"/>
				<display:column sortable="false" style="text-align:center;width:10px" title="Goods Description" property="GOODS_DESCRIPTION" class="formtxtc" headerClass="headerClass"/>
				<display:column sortable="false" style="text-align:center;width:10px"
				title="Saleterm For Valuation" property="SALE_TERM_NAME" class="formtxtc" headerClass="headerClass"/>
				<display:column sortable="false" style="text-align:right;width:10px"
				title="Saleterm Charges(AED)" property="TOTAL_SLAE_TERM_CHARGES" class="formtxtc" headerClass="headerClass"/>
				<display:column sortable="false" style="text-align:right;width:10px"
				title="Tolerance Charges(AED)" property="TOTAL_TOLERANCE_CHARGES" class="formtxtc" headerClass="headerClass"/>
				<display:column sortable="false" style="text-align:center;width:10px"
				title="Extracover Name" property="EXTRA_COVER_NAME" class="formtxtc" headerClass="headerClass"/>
				<display:column sortable="false" style="text-align:right;width:10px"
				title="SumInsured Local" property="SUM_INSURED_LOCAL" class="formtxtc" headerClass="headerClass"/>
				<display:column sortable="false" style="text-align:center;width:10px"
				title="Currency Name" property="CURRENCY_NAME" class="formtxtc" headerClass="headerClass"/>
				<display:column sortable="false" style="text-align:right;width:10px"
				title="Exchange Rate" property="EXCHANGE_RATE" class="formtxtc" headerClass="headerClass"/>
				<display:column sortable="false" style="text-align:right;width:10px"
				title="SumInsured Foreign" property="SUM_INSURED_FOREIGN" class="formtxtc" headerClass="headerClass"/>
				<display:column sortable="false" style="text-align:right;width:10px"
				title="Marine Premium" property="BASIC_PREMIUM" class="formtxtc" headerClass="headerClass"/>
				<display:column sortable="false" style="text-align:right;width:10px"
				title="War Premium" property="TOTAL_WAR_CHARGES" class="formtxtc" headerClass="headerClass"/>
				<display:column sortable="false" style="text-align:right;width:10px"
				title="Excess Premium" property="EXCESS_PREMIUM" class="formtxtc" headerClass="headerClass"/>
				<display:column sortable="false" style="text-align:right;width:10px"
				title="Policy Fee" property="POLICY_FEE" class="formtxtc" headerClass="headerClass"/>
				<display:column sortable="false" style="text-align:right;width:10px"
				title="Total Premium(AED)" property="TOTAL_PREMIUM_LOCAL" class="formtxtc" headerClass="headerClass"/>
				<display:column sortable="false" style="text-align:center;width:10px"
				title="Foreign Currency Status" property="FOREIGN_CURRENCY" class="formtxtc" headerClass="headerClass"/>
				<display:column sortable="false" style="text-align:right;width:10px"
				title="Total Premium(Foreign)" property="TOTAL_PREMIUM_FOREIGN" class="formtxtc" headerClass="headerClass"/>
				<display:column sortable="false" style="text-align:right;width:10px"
				title="Commission" property="COMMISSION" class="formtxtc" headerClass="headerClass"/>
				<display:column sortable="false" style="text-align:center;width:10px"
				title="Transit from City" property="TRANSIT_FROM_CITY" class="formtxtc" headerClass="headerClass"/>
				<display:column sortable="false" style="text-align:center;width:10px"
				title="Transit from Country" property="TRANSIT_FROM_COUNTRY" class="formtxtc" headerClass="headerClass"/>
				<display:column sortable="false" style="text-align:center;width:10px"
				title="Finaldest City" property="FINAL_DEST_CITY" class="formtxtc" headerClass="headerClass"/>
				<display:column sortable="false" style="text-align:center;width:10px"
				title="Finaldest Country" property="FINAL_DESTINATION_COUNTRY" class="formtxtc" headerClass="headerClass"/>
				<display:column sortable="false" style="text-align:right;width:10px"
				title="Debitnote No" property="DEBIT_NOTE_NO" class="formtxtc" headerClass="headerClass"/>
				<display:column sortable="false" style="text-align:center;width:10px"
				title="Referral" property="REFERRAL" class="formtxtc" headerClass="headerClass"/>
				<display:column sortable="false" style="text-align:center;width:275px"
				title="Referral Status" property="REFERAL_STATUS" class="formtxtc" headerClass="headerClass"/>
				<display:column sortable="false" style="text-align:center;width:10px"
				title="Status" property="STATUS" class="formtxtc" headerClass="headerClass"/>
				<display:column sortable="false" style="text-align:center;width:10px"
				title="Integration Status" property="INTEGRATION_STATUS" class="formtxtc" headerClass="headerClass"/>
				<display:column sortable="false" style="text-align:center;width:175px"
				title="Integration Error" property="INTEGRATION_ERROR" class="formtxtc" headerClass="headerClass"/>
				<display:column sortable="false" style="test-align:center;width:10px"
					 title="Insured Name" property="INSURED_NAME" class="formtxtc" headerClass="headerClass"/>
				<display:column sortable="false" style="test-align:center;width:10px"
					 title="Open Policy No" property="open_Policy_no" class="formtxtc" headerClass="headerClass"/>
				<display:column sortable="false" style="test-align:center;width:10px"
					 title="Broker Name" property="Broker_Name" class="formtxtc" headerClass="headerClass"/>
				<display:column sortable="false" style="test-align:center;width:10px"
					 title="Conveyance" property="CONVEYANCE" class="formtxtc" headerClass="headerClass"/>
				<display:column sortable="false" style="test-align:center;width:10px"
					 title="Vessel Name" property="vessel_name" class="formtxtc" headerClass="headerClass"/>
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
	</s:form>
	</div>
	
   	</s:else>	
      </td>
    </tr>
 </table>
	<script type="text/javascript">
		function fnsubmit(frm,from,val) {
			if(from == 'result'){
				document.branch.action = "${pageContext.request.contextPath}/branchResultAreport.action?reqFrom="+from;
				document.branch.submit();
			}else{
				<s:if test='"reportBR".equals(mode1)'>document.branch1.action = "${pageContext.request.contextPath}/policyAreport.action?index=1";</s:if>
				<s:elseif test='"reportUW".equals(mode1)'>document.branch1.action = "${pageContext.request.contextPath}/policyAreport.action";</s:elseif>
				<s:else>document.branch1.action = "${pageContext.request.contextPath}/branchAreport.action";</s:else>
				
				document.branch1.submit();
			}
		}
	</script>
</body>
</html>
