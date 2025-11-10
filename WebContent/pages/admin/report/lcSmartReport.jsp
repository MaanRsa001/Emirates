<%@ page isELIgnored="false" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<script>
			function stopRKey(evt) { 
			  var evt = (evt) ? evt : ((event) ? event : null); 
			  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
			  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
			} 
			document.onkeypress = stopRKey;
			function getlcreport(val, id){
				document.getElementById('ajaxLoader').style.display="block";
				document.getElementById('search').style.display="block";
				postRequest('${pageContext.request.contextPath}/lcSmartAreport.action?reqFrom='+id+'&broker='+val, id);
			}
			
			function getlcsmart(val, id){
				if(val.length>=2){
					var searchBy=document.getElementById("searchBy").value;
					var broker=document.getElementById("broker").value;
					postRequest('${pageContext.request.contextPath}/lcSmartAreport.action?reqFrom='+id+'&searchBy='+searchBy+'&searchValue='+val+'&broker='+broker, id);
				}
			}
		</script>
	</head>
	<body>
		<s:form name="form1" method="post" action="" theme="simple" validate="false">
			<table width="90%" border="0" align="center" cellspacing="0" cellpadding="0" class='text'>
				<tr> <td>&nbsp;&nbsp;</td> </tr>
				<tr><td class="heading"><s:text name="lc.smart.report"/></td></tr>
				<tr><td>&nbsp;</td></tr>
				<tr><td align="center"><s:text name="policy.report.select.br"/>&nbsp; : &nbsp;<s:select name="broker" id="broker" list="brokerList" headerKey="" headerValue="-Select-" listKey="login_id" listValue="COMPANY_NAME" cssClass="input" onchange="getlcreport(this.value, 'lcsmartLists')"/></td></tr>
				<tr><td>&nbsp;</td></tr>
				<tr align="center">
					<td style="padding:10px;" align="center">
						<table width="100%" align="center">
							<tr>
								<td align="center" id="search" style="display:<s:if test='"ajax".equals(from1)'>block</s:if><s:else>none</s:else>"> Search By:<s:select name="searchBy" id="searchBy" list="#{'ocm.MISSIPPI_OPENCOVER_NO':'Open Cover No', 'oclm.LC_NUMBER':'LC Number'}" headerKey="" headerValue="-Select-" theme="simple"/>
								<s:textfield name="searchValue" onkeyup="getlcsmart(this.value,'lcsmartLists')" theme="simple"/></td>
							</tr>
							<tr><td align="center">
								<div id="lcsmartLists">
									<br>
										<img id="ajaxLoader" style="display:none" src='${pageContext.request.contextPath}/images/ajax-loader.gif' width="50px" height="50px"/>
									<br>
									<s:if test='"ajax".equals(from1)'>
										<display:table name="lcsmartList" pagesize="20" requestURI="lcSmartAreport.action?from1=normal" class="table" uid="row" id="record" export="true" excludedParams="from1">
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
											<display:setProperty name="export.pdf.filename" value="LCSmartReport.pdf" />
											<display:setProperty name="export.excel.filename" value="LCSmartReport.xls" />
											
											<display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title=" S.No " value="${record_rowNum}"/>
											<display:column sortable="true" style="text-align:left;font-size:13px;"  title="Open Cover No" property="MISSIPPI_OPENCOVER_NO" />
											<display:column sortable="true" style="text-align:left;font-size:13px;" title="Customer Name" property="cust_name" />
											<display:column sortable="true" style="text-align:left;font-size:13px;" title="Bank Name" property="bank_name" />
											<display:column sortable="true" style="text-align:left;font-size:13px;"  title="LC Number" property="LC_NUMBER" />
											<display:column sortable="true" style="text-align:left;font-size:13px;" title="Start Date" property="start_date" />
											<display:column sortable="true" style="text-align:left;font-size:13px;" title="End Date" property="end_date" />
											<display:column sortable="true" style="text-align:right;font-size:13px;"  title="LC Currency" property="SHORT_NAME" />
											<display:column sortable="true" style="text-align:right;font-size:13px;" title="LC Currency Amount" property="EXCHANGE_RATE" format="{0,number,0.00}"/>
											<display:column sortable="true" style="text-align:right;font-size:13px;"  title="LC Amount" property="LC_AMOUNT" format="{0,number,0,000.00}"/>
											<display:column sortable="true" style="text-align:right;font-size:13px;" title="LC Amount(AED)" property="LC_AMT_DH" format="{0,number,0,000.00}"/>
											<display:column sortable="true" style="text-align:right;font-size:13px;" title="Used Amount(AED)" property="used_amt" format="{0,number,0,000.00}"/>
											<display:column sortable="true" style="text-align:right;font-size:13px;" title="Pending LC Amount(AED)" property="LC_BALANCE_AMOUNT" format="{0,number,0,000.00}"/>
										</display:table>
									
									</s:if>
								</div>
							</td></tr>
						</table>
					</td>
				</tr>
				<tr><td align="center">
		        	<input type="button" onclick="exportdata('excel')" class="btn" value="Excel"/>
			  		<input type="button" onclick="exportdata('pdf')" class="btn" value="Pdf"/>
		        </td></tr>
			</table>
			<s:hidden name="downloadType"/>
		</s:form>
		<script type="text/javascript">
		function exportdata(val) {
			document.form1.downloadType.value=val;	
			document.form1.action='${pageContext.request.contextPath}/lcSmartJasperAreport.action';	
			document.form1.submit();
		}
		</script>
	</body>
</html>