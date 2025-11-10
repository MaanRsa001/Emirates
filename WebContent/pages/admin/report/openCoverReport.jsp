<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/struts-jquery-tags" prefix="sj"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<%@ page isELIgnored="false"%>
<html>
  <head>
  		<sj:head jqueryui="true" jquerytheme="start" />
  		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/cssbootstrap/bootstrap.min.css" />
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
		<s:if test ='%{reqFrom==null || "".equals(reqFrom)}'>
           <s:form id="opencover" name="opencover" method="post" action="openCoverAreport.action" theme="simple">
				 <table width="100%" border="0" cellspacing="0" cellpadding="0">
				 <tr><td></td></tr>
			   		<tr>
						<td class="heading"><s:text name="OpenCover Report" /></td>
					</tr>
					<tr>
						<td><table width="100%" border="0" align="center" cellpadding="4" cellspacing="0">
	      						<tr><td width="30%"></td>
	        						<td><font color="red"><s:actionerror/></font></td>
	            				</tr>
	            			</table>
	            		</td>
					</tr>
  					<tr>	                                                 
  						<td><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
      						<tr>
      							<td width="10%"></td>
        						<td width="25%" align="right"><s:text name="policy.report.startdate"/> <font color="red">*</font></td>
           						<td width="3%">:</td>
        					    <td width="32%" align="left"><sj:datepicker name="startDate" cssClass="inputBox1" displayFormat="dd/mm/yy" changeMonth="true" changeYear="true" maxDate="+0D" readonly="true"/></td>
            					<td width="30%"></td>
            				</tr>
            				<tr><td colspan="5"><br/></td></tr>
      						<tr>
      							<td width="10%"></td>
        						<td width="25%" align="right"><s:text name="policy.report.enddate"/> <font color="red">*</font></td>
           						<td width="3%">:</td>
        					    <td width="32%" align="left"><sj:datepicker name="endDate" cssClass="inputBox1" displayFormat="dd/mm/yy" changeMonth="true" changeYear="true" maxDate="+0D" readonly="true"/></td>
            					<td width="30%"></td>
            				</tr>
            				<tr><td colspan="5"><br/></td></tr>
            				<tr>
      							<td width="10%"></td>
        						<td width="25%" align="right"><s:text name="Select Broker"/> <font color="red">*</font></td>
           						<td width="3%">:</td>
        					    <td width="32%" align="left"><s:select name="broker" list="brokerList" headerKey="ALL" headerValue="ALL" listKey="login_id" listValue="COMPANY_NAME" cssClass="inputSelect"/></td>
              					<td width="30%"></td>
              				</tr>
              				<tr><td colspan="5"><br/></td></tr>
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
	    <s:else>
	    <s:form id="branch1" name="branch1" method="post" action="branchAreport.action" theme="simple">
	    	<table width="100%" cellspacing="0" cellpadding="0" border="0">
	    	  <tr>
				  <td class="heading"><s:text name="OpenCover Report" /></td>
			  </tr>
			    <tr>
					  <td>
					  		<table width="100%">
					  			<tr>
					  				<td width="20%">&nbsp;</td>
					  				<td width="40%"><b><s:text name="Start Date"/></b> &nbsp;:&nbsp;<s:property value="startDate"/></td>
					  				<td width="40%"><b><s:text name="End Date"/></b> &nbsp;:&nbsp;<s:property value="endDate"/></td>
					  				<td width="20%">&nbsp;</td>
					  			</tr>
					  		</table>
					  </td>
				  </tr>
			  <tr>
				<td width="100%">
				<display:table name="coverList" pagesize="15" style="width:100%;" class="table" requestURI="openCoverAreport.action" uid="row" id="record" export="false" excludedParams="true" varTotals="totals">
					<display:setProperty name="paging.banner.one_item_found" value="" />
					<display:setProperty name="paging.banner.one_items_found" value="" />
					<display:setProperty name="paging.banner.all_items_found" value="" />
					<display:setProperty name="paging.banner.some_items_found" value="" />
					<display:setProperty name="paging.banner.placement" value="bottom" />
					<display:setProperty name="export.excel" value="false" />
               	 	<display:setProperty name="export.excel.filename" value="OpenCoverReports.xls" />
               	 	<display:setProperty name="export.pdf.filename" value="OpenCoverReports.pdf" />
               	 	<display:setProperty name="export.csv" value="false" />
					<display:setProperty name="export.xml" value="false" />
					<display:setProperty name="export.pdf" value="false" />
					<%--<display:caption media="html">
				        <strong>Open Cover Report</strong>
				    </display:caption>
				      <display:caption media="excel pdf">A Caption</display:caption>
					--%>
					<display:column style="text-align:center;width:10%" sortable="false" headerClass="courier" title="S.No" value="${record_rowNum}"/>
					<display:column sortable="false" style="text-align:left;width:20%" headerClass="courier" title="Broker Name" property="BROKER_NAME"/>
					<display:column sortable="false" style="text-align:left;width:20%" headerClass="courier" title="Customer Name" property="customer_name"/>
					<display:column sortable="false" style="text-align:center;width:20%" headerClass="courier" title="Open Cover No" property="open_cover_no"/>
					<display:column sortable="false" style="text-align:center;width:10%" headerClass="courier" title="No Of Policies" property="POLICY_COUNT"/>
					<display:column sortable="false" style="text-align:right;width:10%" headerClass="courier" title="Total Premium" property="premium" format="{0,number,#,##0.00}" total="true"/>
					<display:column sortable="false" style="text-align:right;width:10%" headerClass="courier" title="Total Commission" property="commission" format="{0,number,#,##0.00}"  total="true"/>
					 <display:footer>
					   	<td colspan="6" align="right"><b><s:property value="getText('number.format.2',{@java.lang.Double@parseDouble(#attr.totals.column6)})"/></b></td>
					  	<td align="right"><b><s:property value="getText('number.format.2',{@java.lang.Double@parseDouble(#attr.totals.column7)})"/></b></td>
					</display:footer>
					
				</display:table>
				</td>
			</tr>
			<tr>
   				<td>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="25" align="center" valign="middle">
								<input type="button" onclick="exportdata('excel')" class="btn" value="Excel"/>
	  							<input type="button" onclick="exportdata('pdf')" class="btn" value="Pdf"/>
								<input type="button" name="sub" value="Back" onclick="fnsubmit(this.form,'Back','')" class="btn" />
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<s:hidden name="startDate"/>
		<s:hidden name="endDate"/>
		<s:hidden name="broker"/>
		<s:hidden name="productID"/>
		<s:hidden name="reportStatus"/>
		<s:hidden name="branch"/>
		<s:hidden name="downloadType" id="downloadType"/>
	</s:form>
   	</s:else>	
      </td>
    </tr>
 </table>
<script type="text/javascript">
function fnsubmit(frm,from,val) {
	if(from == 'result'){
		document.opencover.action = "${pageContext.request.contextPath}/openCoverAreport.action?reqFrom="+from;
		document.opencover.submit();
	}else{
		document.branch1.action = "${pageContext.request.contextPath}/openCoverAreport.action?reqFrom=";
		document.branch1.submit();
	}
}
function exportdata(val) {
	document.branch1.downloadType.value=val;	
	document.branch1.action='${pageContext.request.contextPath}/openCoverJasperAreport.action';	
	document.branch1.submit();
}
</script>
</body>
</html>
