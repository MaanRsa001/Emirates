<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<html>
	<head>
		<sj:head jqueryui="true" jquerytheme="start" />
	</head>
	<body>
		<s:form name="reportsForm" theme="simple" action="transDetailsUpload.do">
	<s:if test="hasActionErrors()">
		<table width="50%" align="center">
			<tr>
				<td style="color: red">
					<b> <font color='red' style="font-family: Arial; font-size: 12px; font-weight: bold;">
						<s:iterator value="actionErrors">
							<ul>
								<li>
									<s:property escape="false" />
								</li>
							</ul>
						</s:iterator> </font> </b>
				</td>
			</tr>
		</table>
	</s:if>
	<s:if test='"TransHome".equalsIgnoreCase(display)'>
		<div class="tablerow">
			<div>
				<div class="disBorder rEdge" style="width: 60%; margin: 0 auto;" >
					<div class="botBorder">
			    		<span class="heading">				    			
			    			<s:text name="label.trans.title" />		    			
			    		</span>			    		
			    		<br class="clear"/>
			    	</div>
				   	<div class="dataPad">
				   		<div class="boxcontent" style="color: red; margin-left: 20%;">
							<s:actionerror/>
							<s:fielderror/>
							<br class="clear"/>
						</div>										
						<div class="boxcontent" style="margin: 0 auto; width: 70%;">	
							<div class="">
								<div class="text" style="line-height: 25px; text-align: right; margin-right: 5px;"><s:text name="label.startDate" /><font color="red">*</font></div>
								<div class="tVal">									
									<sj:datepicker name="fromDate" id="fromDate"  changeMonth="true" changeYear="true" displayFormat="dd/mm/yy" cssClass="inputBox1 rEdge" />
									
								</div>
								<br class="clear"/>		  
							</div>
							<br class="clear"/>
						</div>
						<div class="boxcontent" style="margin: 0 auto; width: 70%;">
							<div class=""  >
								<div class="text" style="line-height: 25px; text-align: right; margin-right: 5px;">
									<s:label key="label.endDate"/>:<font color="red">*</font>
								</div>
								<div class="tVal">									
									<sj:datepicker name="toDate" id="toDate"  changeMonth="true" changeYear="true" displayFormat="dd/mm/yy" cssClass="inputBox1 rEdge" />
								</div>
								<s:hidden name="reqFrom" value="gettrans"/>								
								<br class="clear"/>								
							</div>	
							<br class="clear"/>
						</div>
						<div class="boxcontent" style="width: 50%;" align="center">
							<s:submit type="button" name="submit" value="Submit" cssClass="iButton rh"/>
						</div>						
				   	</div>
				</div>
			</div>
		</div>		
	</s:if>
	<s:elseif test='"result".equalsIgnoreCase(display)'>
		<%--<table width="100%" align="center" class="footable">
			<thead>
			<tr>
				<th colspan="6" nowrap="nowrap">
					<s:text name="label.trans.title" />
				</th>
			</tr>
			<tr>
				<th width="25%">
					<s:text name="label.startDate" />
				</th>
				<th width="25%">
					<s:property value="fromDate" />
				</th>
				<th width="25%">
					<s:text name="label.endDate" />
				</th>
				<th width="25%">
					<s:property value="toDate" />
				</th>
			</tr>
			</thead>			
			<tr height="10">
				<td></td>
			</tr>
		</table> --%>
		<table width="100%" align="center">
				<tr>
					<td align="center" width="100%">
						<display:table name="transDetList" pagesize="10" cellpadding="1" cellspacing="1" requestURI="transDetailsUpload.do" class="table" uid="row" id="record" export="true">
							<display:setProperty name="basic.msg.empty_list" value="<font color='red'>No Recods Found</font>" />
							<display:setProperty name="paging.banner.one_item_found" value="" />
							<display:setProperty name="paging.banner.one_items_found" value="" />
							<display:setProperty name="paging.banner.all_items_found" value="" />
							<display:setProperty name="paging.banner.some_items_found" value="" />
							<display:setProperty name="paging.banner.placement" value="bottom" />
							<display:setProperty name="paging.banner.onepage" value="" />
							<display:setProperty name="export.types" value="excel pdf" />
							<display:setProperty name="export.pdf" value="true" />
							<display:setProperty name="export.csv" value="false" />
							<display:setProperty name="export.xml" value="false" />
							<display:setProperty name="export.pdf.filename" value="TransactionDetails.pdf" />
							<display:setProperty name="export.excel.filename" value="reports.xls" />

							<display:column sortable="true" style="text-align:left;font-size:15px;" title="Transaction Id" property="TRAN_ID" />
							<display:column sortable="true" style="text-align:left;font-size:15px" title="Uploaded Date" property="TRANSACTION_DATE" />
							<display:column sortable="true" style="text-align:left;font-size:15px" title="User Name" property="USER_NAME" />
							<display:column sortable="true" style="text-align:left;font-size:15px" title="File Name" property="FILE_NAME" />
							<display:column sortable="true" style="text-align:center;font-size:15px" title="Total Records" property="TOTAL_RECORDS" />
							<display:column sortable="true" style="text-align:center;font-size:15px" title="Valid Records">
								<a style="color: blue;font-weight: bold; text-decoration:underline;cursor:pointer;" onclick="downloadFile('${record.TRAN_ID}','V','100')" title="Download">${record.VALID_RECORDS}</a>
							</display:column>
							<display:column sortable="true" style="text-align:center;font-size:15px" title="Error Records">
								<a style="color: blue;font-weight: bold; text-decoration:underline;cursor:pointer;" onclick="downloadFile('${record.TRAN_ID}','E','100')" title="Download">${record.ERROR_RECORDS}</a>
							</display:column>
						</display:table>
						<s:hidden name="tranId" id="tranId"/>
						<s:hidden name="downloadType" id="downloadType"/>
						<s:hidden name="typeId" id="typeId"/>
					</td>
				</tr>
				<tr height="20">
					<td></td>
				</tr>
				<tr>
					<td align="center">
						<input type="button" name="dsfdsg" onclick="fnsmt('transDetailsUpload.do?reqFrom=home');" value="Back" class="iButton rh"/>
					</td>
				</tr>
			</table>
	</s:elseif>
</s:form>
<script>
	try{
		document.getElementById('<s:property value="reqFrom"/>').className='cButton';
	}catch(err){}
 function downloadFile(transId,dtype,typeId)
	{
		document.getElementById("downloadType").value=dtype;
		document.getElementById("tranId").value=transId;
		document.getElementById("typeId").value=typeId;
		document.forms[0].action="${pageContext.request.contextPath}/downloadTransUpload.do";
		document.forms[0].submit();
	}
	
	function fnsmt(val){
		document.reportsForm.action=val;
		document.reportsForm.submit();
	}
</script>
	</body>
</html>