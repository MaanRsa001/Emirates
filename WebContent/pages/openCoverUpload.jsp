<%@ page language="java" contentType="application/vnd.ms-excel; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html> 
  <head>
 	 <link href="${pageContext.request.contextPath}/css/displaytag.css" rel="stylesheet" type="text/css">
 </head>
   
  <body>
 <s:if test="display==null">
  	<s:form name="uploadForm"  action="" method="post" enctype="multipart/form-data" theme="simple"  >
    	<table width="50%" border="0" cellspacing="2" cellpadding="2" align="center" >
    		<tr align="left">
				<td height="20" colspan="2" style="color:red">
					<s:actionerror/>
					<s:fielderror/>
				</td>
			</tr>
		</table>
    	<table width="50%" border="0" cellspacing="2" cellpadding="2" align="center" >
    		<tr align="center">
				<td height="20" colspan="2" >
					<div id="loaderImage" style="display:none">
						<b><s:label key="upload.progress" cssStyle="color:#073e8f" /></b>
						<br>
					<img src='${pageContext.request.contextPath}/images/ajax-loader.gif' width="50px" height="50px"/>
					<br>
					</div>	
				</td>
			</tr>
		</table>
    	<table width="80%" border="0" cellspacing="0" cellpadding="2" align="center">
		    <tr class="head">
    			<td align="center" class="heading">
    				<b><s:label key="upload.fileUpload"/></b>
    			</td>
    		</tr>
    		<tr >
				<td   height="25"  align="right" class="style1">
					<a href="#" onclick="goTransaction()"> Transactions </a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<b></b>
				</td>
			</tr>
    		<tr align="center">
				<td height="20">
					<div id="loaderImage" style="display:none">
						<br>
						<br>
					</div>
				</td>
			</tr>
    		<tr>
    			<td align="center">
    				<table width="80%" align="center">
    					<tr>
    						<td width="47%"><b><font size="2"><s:text name="Broker Code"/></font></b></td>
    						<td width="6%">&nbsp;:&nbsp;</td>
    						<td width="47%"><b><font size="2"><s:property value="#session.brokerId"/></font></b></td>
    					</tr>
    					<tr>
    						<td><b><font size="2"><s:text name="Customer Id"/></font></b></td>
    						<td>&nbsp;:&nbsp;</td>
    						<td><b><font size="2"><s:property value="#session.customerId"/></font></b></td>
    					</tr>
    					<tr>
    						<td><b><font size="2"><s:text name="Open Cover No"/></font></b></td>
    						<td>&nbsp;:&nbsp;</td>
    						<td><b><font size="2"><s:property value="#session.openCoverNo"/></font></b></td>
    					</tr>
    					<tr>
    						<td><b><font size="2">Mode of Transport</font><font color="red">*</font></b></td>
    						<td>&nbsp;:&nbsp;</td>
    						<td>
    							<s:if test='modeList!=null && modeList.size==1'>
    								<s:select name="conveyance" id="conveyance" list="modeList" listKey="CODE" listValue="CODEDESC" cssClass="inputSelect" onchange="getList('?modeOfTransport='+this.value,'coverList');" />
    							</s:if>
    							<s:else>
    								<s:select name="conveyance" id="conveyance" list="modeList" headerKey="" headerValue="-Select-" listKey="CODE" listValue="CODEDESC" cssClass="inputSelect" onchange="getList('?modeOfTransport='+this.value,'coverList');" />
    							</s:else>
    							<s:hidden name="conveyance1"/>
    						</td>
    						
	                    </tr>
	                    <tr>
    						<td><b><font size="2">Coverage</font><font color="red">*</font></b></td>
    						<td>&nbsp;:&nbsp;</td>
    						<td>
                               	<div id="coverList">
                               		<s:if test='coverList!=null && coverList.size==1'>
                               			<s:select name="cover" list="coverList" listKey="CODE" listValue="CODEDESC" cssClass="inputSelect"/>
                               		</s:if>
                               		<s:else>
                               			<s:select name="cover" list="coverList" headerKey="" headerValue="-Select-" listKey="CODE" listValue="CODEDESC" cssClass="inputSelect"/>
                               		</s:else>
                               	</div>
	                            <s:hidden name="cover1"/>
	                        </td>
	                    </tr>
	                    <tr>
    						<td><b><font size="2">Sale Term Value</font><font color="red">*</font></b></td>
    						<td>&nbsp;:&nbsp;</td>
    						<td>
    							<s:if test='saleTermList!=null && saleTermList.size==1'>
	                                <s:select name="saleTerm" list="saleTermList" listKey="CODE" listValue="CODEDESC" cssClass="inputSelect" onchange="getList('?saleTerm='+this.value,'percentList');"/>
	                            </s:if>
	                            <s:else>
    								<s:select name="saleTerm" list="saleTermList" headerKey="" headerValue="-Select-" listKey="CODE" listValue="CODEDESC" cssClass="inputSelect" onchange="getList('?saleTerm='+this.value,'percentList');"/>
    							</s:else>
    							<s:hidden name="saleTerm1"/>
    						</td>
	                    </tr>
	                     <tr>
    						<td><b><font size="2">Percentage</font><font color="red">*</font></b></td>
    						<td>&nbsp;:&nbsp;</td>
    						<td><s:if test="%{productId==openCover}" >
	                                	<div id="percentList"><s:select name="saleTermPercent" list="percentList" headerKey="" headerValue="-Select-" listKey="CODE" listValue="CODEDESC" cssClass="inputSelect" onchange="getList('?saleTermPercent='+this.value,'toleranceList');"/></div>
	                           </s:if>	
	                            <s:else>                                
	                                <div id="percentList"><s:select name="saleTermPercent" id="percent" list="percentList" headerKey="" headerValue="-Select-" listKey="CODE" listValue="CODEDESC" cssClass="inputSelect"/></div>                                
	                           </s:else><s:hidden name="saleTermDecVal"/>
	                           <s:hidden name="saleTermPercent1"/>
	                       </td>
	                    </tr>
	                    <tr>
    						<td><b><font size="2">Tolerance</font><font color="red">*</font></b></td>
    						<td>&nbsp;:&nbsp;</td>
    						<td><div id="toleranceList"><s:select name="tolerance" list="toleranceList" listKey="CODE" listValue="CODEDESC" cssClass="inputSelect" disabled="#disable"/></div>
    							<s:hidden name="tolerance1"/>	
    						</td>
	                    </tr>
    					<tr>
    						<td colspan="3"></td>
    					</tr>
    					<tr>
    						<td><b><font size="2"><s:label key="upload.fileToUpload"/></font><font color="red" size="4">*</font></b></td>
    						<td>&nbsp;:&nbsp;</td>
    						<td><b><font size="2"><s:file name="upload" cssClass="input" cssStyle="width:300px"/></font></b></td>
    					</tr>
    				</table>
    			</td>
    		</tr>
    		<tr>
    			<td height="10%">&nbsp;
    			</td>
    		</tr>
    		<tr class="runtext">
   				<td  align="center">
   					<input type="button" class="btn"  style="width:80px;height:25px" value="UPLOAD"  onclick=" return goConfirm()"/>&nbsp;&nbsp;
   				    <input type="button" class="btn"   style="width:80px;height:25px" value="BACK"  onclick=" return goQuote()"/>
   				</td>
    		</tr>
    		
    	</table>
    </s:form>
 </s:if>
 <s:if test="display=='result'">
  <s:form name="uploadForm"  action="redirectOpenUpload" method="post" theme="simple">
	<table width="500" border="0" cellspacing="2" cellpadding="4" align="center" class="data-table-border">
		<tr >
			<td  class="heading" colspan="2" >
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<b><s:label key="upload.uploadDetails"/></b>
			</td>
		</tr>
		<tr class="runtext">
			<td height="14" align="right" colspan="2"></td>
		</tr>
		<tr class="runtext">
			<td height	="14" align="center" colspan="2"><font color="blue" style="font-weight:bold">&nbsp;<s:label key="upload.tranId"/>&nbsp;<s:property value="tranId"/></font></td>
		</tr>
		<s:iterator value="%{#request.UPLOAD_RESULT}" var="result">
			<tr class="runtext">
				<td height="14" align="left" width="50%"><s:label key="upload.totalRecords"/></td>
				<td height="14" align="center" width="50%"><b><s:property value="TOTAL_NO_OF_RECORDS"/></b></td>
			</tr>
			<tr class="runtext">
				<td height="14" align="left" width="50%"><s:label key="upload.uploaded"/></td>
				<s:if test="ERROR_RECORDS!=0">
					<td height="14" align="center" width="50%"><b><a style="color: red;font-weight: bold; text-decoration:underline;cursor:pointer;" onclick="downloadFile()" title="Download"><s:property value="UPLOADED_COUNT"/></a></b></td>
				</s:if>
				<s:else>
					<td height="14" align="center" width="50%"><b><s:property value="UPLOADED_COUNT"/></b></td>
				</s:else>
				
			</tr>
			<tr class="runtext">
				<td height="14" align="left" width="50%"><s:label key="upload.notUploaded"/></td>
				<s:if test="ERROR_RECORDS!=0">
					<td height="14" align="center" width="50%"><b><a style="color: red;font-weight: bold; text-decoration:underline;cursor:pointer;" onclick="downloadErrorFile()" title="Download"><s:property value="PENDING_COUNT"/></a></b></td>
				</s:if>
				<s:else>
					<td height="14" align="center" width="50%"><b><s:property value="PENDING_COUNT"/></b></td>
				</s:else>
			</tr>
			<tr class="runtext">
				<td height="14" align="center" colspan="2">
					<s:submit cssClass="btn" theme="simple" key="upload.back" action="redirectOpenUpload"/>
					<s:hidden name="typeId" />
					<s:hidden name="tranId" />
				</td>
			</tr>
		</s:iterator>
	</table>
	</s:form>
 </s:if>	
 
 <s:if test="display=='uploadList'">
 <s:form name="uploadForm"  action="/downloadOpenUpload.action" method="post" enctype="multipart/form-data" theme="simple"  >
   
 	<table width="750" border="0" cellspacing="0" cellpadding="4" align="center" class="data-table-border">
 	<tr bgcolor="#FDF0D9">
			<td  class="heading" height="37" colspan=2>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<b><s:label key="upload.uploadDetail"/></b>
			</td>
			</tr>
			<tr>
			<td   height="37"  align="left">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<b> Transaction Id:&nbsp;&nbsp;<s:property value="#request.TranId"/></b>
			</td>
		</tr>
 	<tr>
		<td class="formtxtc" style="text-align: center;" colspan="2">
            <br/>	
			<display:table name="UploadedList" pagesize="15"
				requestURI="downloadOpenUpload.action" class="table" uid="row"
				id="record" export="true">
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

				<display:column sortable="true" style="text-align:center;"
					title="Commodity" property="INTEREST" media="html" />
				<display:column sortable="true" style="text-align:center;"
					title="Mode of transport" property="CONVEYANCE" media="html" />
				
				<display:column sortable="true" style="text-align:center;"
				title="Invoice value" property="INVOICE_VALUE" media="html" />
				<display:column sortable="true" style="text-align:center;"
				title="Marine premium" property="MARINE_PREMIUM" media="html" />
				<display:column sortable="true" style="text-align:center;"
				title="Issuance fee" property="POLICY_FEE" media="html" />
				<display:column sortable="true" style="text-align:center;"
				title="Quote no" property="QUOTE_NO" media="html" />
				<display:column sortable="true" style="text-align:center;"
				title="Remarks" property="REMARKS" media="html" />
				
				<display:column sortable="true" style="text-align:center;"
					title="SR NO." property="SR_NO" media="excel" />
				<display:column sortable="true" style="text-align:center;"
					title="REF. NO." property="REF_NO" media="excel" />
				<display:column sortable="true" style="text-align:center;"
					title="ORDER NO." property="ORDER_NO" media="excel" />
				<display:column sortable="true" style="text-align:center;"
					title="INTEREST" property="INTEREST" media="excel" />
				<display:column sortable="true" style="text-align:center;"
					title="COMMODITY DESCRIPTION" property="COMMODITY_DESCRIPTION" media="excel" />
				<display:column sortable="true" style="text-align:center;"
				title="L/C NO." property="LC_NO" media="excel" />
				<display:column sortable="true" style="text-align:center;"
				title="CONVEYANCE" property="CONVEYANCE" media="excel" />
				<display:column sortable="true" style="text-align:center;"
				title="COVER" property="COVER" media="excel" />
				<display:column sortable="true" style="text-align:center;"
				title="VESSEL NAME" property="VESSEL_NAME" media="excel" />
				<display:column sortable="true" style="text-align:center;"
				title="SAILING DATE" property="SAILING_DATE" media="excel" />	
				<display:column sortable="true" style="text-align:center;"
				title="PACKING DETAILS" property="PACKING_DETAILS" media="excel" />
				<display:column sortable="true" style="text-align:center;"
				title="VOYAGE FROM" property="VOYAGE_FROM" media="excel" />
				<display:column sortable="true" style="text-align:center;"
				title="VOYAGE TO" property="VOYAGE_TO" media="excel" />
				<display:column sortable="true" style="text-align:center;"
				title="INVOICE VALUE" property="INVOICE_VALUE" media="excel" />
				<display:column sortable="true" style="text-align:center;"
				title="CURRENCY" property="CURRENCY" media="excel" />
				<display:column sortable="true" style="text-align:center;"
				title="INCOTERMS" property="INCOTERMS" media="excel" />
				<display:column sortable="true" style="text-align:center;"
				title="ROE" property="ROE" media="excel" />	
				<display:column sortable="true" style="text-align:center;"
				title="Basis of Valuation" property="BASIS_OF_VALUATION" media="excel" />
				
				<display:column sortable="true" style="text-align:center;"
				title="PACKAGE DESCRIPTION" property="PACKAGE_DESCRIPTION" media="excel" />
				<display:column sortable="true" style="text-align:center;"
				title="SETTLING AGENT" property="SETTLING_AGENT" media="excel" />
				<display:column sortable="true" style="text-align:center;"
				title="Sum Insured (Dhs)" property="SUM_INSURED_DHS" media="excel" />
				<display:column sortable="true" style="text-align:center;"
				title="Marine Premium" property="MARINE_PREMIUM" media="excel" />
				<display:column sortable="true" style="text-align:center;"
				title="War Premium" property="WAR_PREMIUM" media="excel" />
				<display:column sortable="true" style="text-align:center;"
				title="PREMIUM RATE" property="RATE" media="excel" />
				<display:column sortable="true" style="text-align:center;"
				title="WAR RATE" property="WARRATE" media="excel" />
				<display:column sortable="true" style="text-align:center;"
				title="ISSUANCE FEE" property="POLICY_FEE" media="excel" />
				<display:column sortable="true" style="text-align:center;"
				title="QUOTE NO" property="QUOTE_NO" media="excel" />
				<display:column sortable="true" style="text-align:center;"
				title="Remarks" property="REMARKS" media="excel" />
				
                <display:setProperty name="export.excel" value="true" />
                <display:setProperty name="export.excel.filename"
				value="UploadDetails.xls" />
				<display:setProperty name="export.csv" value="false" />
				<display:setProperty name="export.xml" value="false" />
				<display:setProperty name="export.pdf" value="false" />
				 
			</display:table>
			
			
		</td>
			</tr>
			<tr>
		<td align="center"> 
		 <input type="button"  class="btn" value="Back" onClick="goBack()"/>
		</td>
		</tr>
</table>
</s:form>
 </s:if>
 <s:if test="display=='errorList'">
 	<s:form name="uploadForm"  action="/errorOpenUpload.action" method="post" enctype="multipart/form-data" theme="simple"  >
   
 	<table width="750" border="0" cellspacing="2" cellpadding="4" align="center" class="data-table-border">
 	<tr bgcolor="#FDF0D9">
			<td  class="heading" height="37" colspan="2" class="style1">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<b><s:label key="upload.errorDetail"/></b>
			</td>
		</tr>
		<tr>
		<td   height="37"  align="left">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<b> Transaction Id:&nbsp;&nbsp;<s:property value="#request.TranId"/></b>
			</td>
	</tr>
 	<tr>
		<td class="formtxtc" style="text-align: center;">

			<display:table name="ErrorList" pagesize="15"
				requestURI="errorOpenUpload.action" class="table" uid="row"
				id="record" export="true">
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

				<display:column sortable="true" style="text-align:center;"
					title="Commodity" property="INTEREST" media="html" />
				
				<display:column sortable="true" style="text-align:center;"
					title="Mode of Transport" property="CONVEYANCE" media="html" />
				<display:column sortable="true" style="text-align:center;"
					title="Coverage" property="COVER" media="html" />
				<display:column sortable="true" style="text-align:center;"
				title="Invoice Value" property="INVOICE_VALUE" media="html" />
				<display:column sortable="true" style="text-align:center;"
				title="Error Detail" property="VALIDATION_ERROR" media="html" />
				
				<display:column sortable="true" style="text-align:center;"
					title="SR NO." property="SR_NO" media="excel" />
				<display:column sortable="true" style="text-align:center;"
					title="REF. NO." property="REF_NO" media="excel" />
				<display:column sortable="true" style="text-align:center;"
					title="ORDER NO." property="ORDER_NO" media="excel" />
				<display:column sortable="true" style="text-align:center;"
					title="INTEREST" property="INTEREST" media="excel" />
				<display:column sortable="true" style="text-align:center;"
					title="COMMODITY DESCRIPTION" property="COMMODITY_DESCRIPTION" media="excel" />
				<display:column sortable="true" style="text-align:center;"
				title="L/C NO." property="LC_NO" media="excel" />
				<display:column sortable="true" style="text-align:center;"
				title="CONVEYANCE" property="CONVEYANCE" media="excel" />
				<display:column sortable="true" style="text-align:center;"
				title="COVER" property="COVER" media="excel" />
				<display:column sortable="true" style="text-align:center;"
				title="VESSEL NAME" property="VESSEL_NAME" media="excel" />
				<display:column sortable="true" style="text-align:center;"
				title="SAILING DATE" property="SAILING_DATE" media="excel" />	
				<display:column sortable="true" style="text-align:center;"
				title="PACKING DETAILS" property="PACKING_DETAILS" media="excel" />
				<display:column sortable="true" style="text-align:center;"
				title="VOYAGE FROM" property="VOYAGE_FROM" media="excel" />
				<display:column sortable="true" style="text-align:center;"
				title="VOYAGE TO" property="VOYAGE_TO" media="excel" />
				<display:column sortable="true" style="text-align:center;"
				title="INVOICE VALUE" property="INVOICE_VALUE" media="excel" />
				<display:column sortable="true" style="text-align:center;"
				title="CURRENCY" property="CURRENCY" media="excel" />
				<display:column sortable="true" style="text-align:center;"
				title="INCOTERMS" property="INCOTERMS" media="excel" />
				<display:column sortable="true" style="text-align:center;"
				title="ROE" property="ROE" media="excel" />	
				<display:column sortable="true" style="text-align:center;"
				title="Basis of Valuation" property="BASIS_OF_VALUATION" media="excel" />
				<display:column sortable="true" style="text-align:center;"
				title="PACKAGE DESCRIPTION" property="PACKAGE_DESCRIPTION" media="excel" />
				<display:column sortable="true" style="text-align:center;"
				title="SETTLING AGENT" property="SETTLING_AGENT" media="excel" />
				<display:column sortable="true" style="text-align:center;"
				title="Sum Insured (Dhs)" property="SUM_INSURED_DHS" media="excel" />
				<display:column sortable="true" style="text-align:center;"
				title="Reference No" property="SNO" media="excel" />
				
				<display:column sortable="true" style="text-align:center;"
				title="Transaction Id"  media="excel" >
				<s:property value="#request.TranId"/>
				</display:column>
				<display:column sortable="true" style="text-align:center;"
				title="Error Detail" property="VALIDATION_ERROR" media="excel" />
				
                <display:setProperty name="export.excel" value="true" />
                <display:setProperty name="export.excel.filename"
				value="ErrorDetails.xls" />
				<display:setProperty name="export.csv" value="false" />
				<display:setProperty name="export.xml" value="false" />
				<display:setProperty name="export.pdf" value="false" />
				 
			</display:table>
			
		</td>
		</tr>
		<tr>
		<td align="center"> 
		 <input type="button"  class="btn"  value="Back" onClick="goBack()"/>
		</td>
		</tr>
</table>
</s:form>
 </s:if>
  <s:if test="display=='TransactionList'">
 	<s:form name="uploadForm" action="/listOpenUpload.action" method="post" enctype="multipart/form-data" theme="simple"  >
   
 	<table width="750" border="0" cellspacing="2" cellpadding="4" align="center" class="data-table-border">
 	<tr bgcolor="#FDF0D9">
			<td  class="heading" height="37" colspan="2" class="style1">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<b><s:label key="upload.transactionDetail"/></b>
			</td>
	</tr>
		
 	<tr>
		<td class="formtxtc" style="text-align: center;">
			<s:hidden name="typeId" />
			<s:hidden name="tranId" />
			<display:table name="TransactionList" pagesize="15"
				requestURI="listOpenUpload.action" class="table" uid="row"
				id="record" >
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
				<display:column sortable="true" style="text-align:center;"
				title="Transaction Id" property="TRANSACTION_ID"  />
				<display:column sortable="true" style="text-align:center;"
				title="Transaction Date" property="TRANSACTION_DATE"  />
				<display:column sortable="true" style="text-align:center;"
				title="Total Records" property="TOTAL_NO_OF_RECORDS"  />
				<display:column sortable="true" style="text-align:center;"
				title="Uploaded Count"  >
				<a href="#" onclick="downloadSuccess(${record.TRANSACTION_ID})"> <s:property value="#attr.record.UPLOADED_COUNT" /></a> 
				</display:column>
				<display:column sortable="true" style="text-align:center;"
				title="Pending Count"   >
					<a href="#" onclick="downloadError(${record.TRANSACTION_ID})"> <s:property value="#attr.record.PENDING_COUNT" /></a>
				</display:column>
				<%--<display:column sortable="true" style="text-align:center;" title="Pending Reupload Count"   >
					<a href="#" onclick="pendingReupload(${record.TRANSACTION_ID})"> <s:property value="#attr.record.PENDING_COUNT" /></a>
				</display:column>--%>
			</display:table>
			
		</td>
		</tr>
		<tr>
			<td align="center"></td>
		</tr>
		<tr>
			<td align="center"><input type="button"  class="btn"  value="Back" onClick="goBack()"/></td>
		</tr>
</table>
</s:form>
 </s:if>
 
  </body>
  <SCRIPT type="text/javascript">
  
  	function loaderImage()
	{
		document.getElementById('loaderImage').style.display="block";
	}
	
	function downloadFile()
	{
		
		document.uploadForm.action="${pageContext.request.contextPath}/downloadOpenUpload.action";
		document.uploadForm.submit();
	}
	function downloadErrorFile()
	{
		document.uploadForm.action="${pageContext.request.contextPath}/errorOpenUpload.action";
		document.uploadForm.submit();
	}
	function downloadSuccess(val)
	{
		document.uploadForm.tranId.value=val;
		document.uploadForm.action="${pageContext.request.contextPath}/downloadOpenUpload.action";
		document.uploadForm.submit();
	}
	
	function pendingReupload(val)
	{
		document.uploadForm.tranId.value=val;
		document.uploadForm.action="${pageContext.request.contextPath}/reuploadOpenUpload.action";
		document.uploadForm.submit();
	}
	
	function downloadError(val)
	{  
	    document.uploadForm.tranId.value=val;
		document.uploadForm.action="${pageContext.request.contextPath}/errorOpenUpload.action";
		document.uploadForm.submit();
	}
	function goBack()
	{
	     document.uploadForm.action="${pageContext.request.contextPath}/redirectOpenUpload.action";
	 document.uploadForm.submit();
	 return true;
	}
	function goConfirm()
	{
	 if(confirm("Do you want to Continue Upload?")) {
	 	document.uploadForm.action="${pageContext.request.contextPath}/uploadOpenUpload.action";
	 	try{
	 		document.uploadForm.conveyance1.value=document.uploadForm.conveyance.options[document.uploadForm.conveyance.selectedIndex].text;
	 	}catch(err){}
	 	try{
	 		document.uploadForm.cover1.value=document.uploadForm.cover.options[document.uploadForm.cover.selectedIndex].text;
	 	}catch(err){}
	 	try{
	 		document.uploadForm.saleTerm1.value=document.uploadForm.saleTerm.options[document.uploadForm.saleTerm.selectedIndex].text;
	 	}catch(err){}
	 	try{
	 		document.uploadForm.saleTermPercent1.value=document.uploadForm.saleTermPercent.options[document.uploadForm.saleTermPercent.selectedIndex].text;
	 	}catch(err){}
	 	try{
	 		document.uploadForm.tolerance1.value=document.uploadForm.tolerance.options[document.uploadForm.tolerance.selectedIndex].text;
	 	}catch(err){}
	 	document.uploadForm.submit();
		return true;
	 } else
	 	return false;
	}
	function goTransaction()
	{
	 document.uploadForm.action="${pageContext.request.contextPath}/listOpenUpload.action";
	 document.uploadForm.submit();
	 return true;
	}
	function goQuote()
	{
	 document.uploadForm.action="${pageContext.request.contextPath}/ViewOpenCovers.jsp";
	 document.uploadForm.submit();
	 return true;
	
	}
	<s:if test='modeList!=null && modeList.size==1'>
		getList('?modeOfTransport=<s:property value="(modeList.get(0).get('CODE'))"/>','coverList');
	</s:if>
	<s:if test='saleTermList!=null && saleTermList.size==1'>
		getList('?saleTerm=<s:property value="(saleTermList.get(0).get('CODE'))"/>','percentList');
	</s:if>
	<s:if test='percentList!=null && percentList.size==1'>
		getList('?saleTermPercent=<s:property value="(percentList.get(0).get('CODE'))"/>','toleranceList');
	</s:if>
	
	
	function getList(val, id) {
	 	if(id=='percentList'){
	 		if(setBasisVal(document.uploadForm.saleTerm, id)){
	 			postRequest('${pageContext.request.contextPath}/'+id+'Quotation.action'+val, id);
	 		}
	 	}else if(id=='conveyanceList'){}
	 	else{
	 		postRequest('${pageContext.request.contextPath}/'+id+'Quotation.action'+val, id);
	 	}
	}
	setBasisVal(document.uploadForm.saleTerm, 'percentList');
	function setBasisVal(obj, id){
		 var flag=true;
		 var value=obj.options[obj.selectedIndex].innerText;
		 if(value=='Declared Value'){
		 	document.uploadForm.tolerance.disabled=true;
		 	document.uploadForm.saleTermPercent.disabled=true;
		 	document.uploadForm.saleTermDecVal.value=document.uploadForm.saleTerm.value;
		 	document.getElementById(id).disabled=true;
		 	document.uploadForm.saleTermPercent.value='';
		 	document.uploadForm.tolerance.value='Declared Value';
			flag=false;
		 }else{
		 	document.uploadForm.tolerance.disabled=false;
			 document.getElementById(id).disabled=false;
		 	document.uploadForm.saleTermDecVal.value='';
		 }
		 return flag;
	}
  </SCRIPT>
</html>
