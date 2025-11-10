<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<%@ page isELIgnored="false"%>
<html>
	<head>
		<script>
			function stopRKey(evt) { 
			  var evt = (evt) ? evt : ((event) ? event : null); 
			  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
			  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
			} 
			document.onkeypress = stopRKey;
		</script>
		<style type="text/css">
			.courier {
				color: #ffffff;
			}
		</style>	
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
					 				<tr><td>&nbsp;</td></tr>
					 				<tr><td><table width="100%">
					 							<tr><td width="5%"></td>
					 								<td width="40%"><s:text name="policy.report.startdate"/>&nbsp; : &nbsp;<b><s:property value="startDate"/></b></td>
					 								<td width="50%"><s:text name="policy.report.enddate"/>&nbsp; : &nbsp;<b><s:property value="endDate"/></b></td>
					 								<td width="5%"></td>
					 						</table></td></tr>
					 				<tr><td align="center">
	 		 								<display:table name="smartList" pagesize="" requestURI="smartAreport.action" class="table" uid="row" id="record" export="true">
												<display:setProperty name="paging.banner.one_item_found" value="" />
												<display:setProperty name="paging.banner.one_items_found" value="" />
												<display:setProperty name="paging.banner.all_items_found" value="" />
												<display:setProperty name="paging.banner.some_items_found" value="" />
												<display:setProperty name="paging.banner.placement"	value="bottom" />
												<display:setProperty name="paging.banner.onepage" value="" />
												<display:setProperty name="export.types" value="excel pdf" />
												<display:setProperty name="export.pdf" value="false" />
												<display:setProperty name="export.csv" value="false" />
												<display:setProperty name="export.xml" value="false" />
												<display:setProperty name="export.pdf.filename" value="SmartReport.pdf" />
												<display:setProperty name="export.excel.filename" value="SmartReport.xls" />
												
												<display:column sortable="false" style="text-align:left;font-size:13px;height:30px;"  headerClass="courier" title=" S.No " value="${record_rowNum}"/>
												<display:column sortable="false" style="text-align:left;font-size:13px;"  headerClass="courier" title="Broker Name" property="company_name" />
												<display:column sortable="false" style="text-align:center;font-size:13px;"  headerClass="courier" title="No.of Policies" property="no_policy" />
												<display:column sortable="false" style="text-align:right;font-size:13px;"  headerClass="courier" title="Total Premium" property="total_premium" format="{0,number,#,##0.00}"/>
												<display:column sortable="false" style="text-align:right;font-size:13px;"  headerClass="courier" title="Commission" property="commission" format="{0,number,#,##0.00}"/>
												<display:column sortable="false" style="text-align:right;font-size:13px;" headerClass="courier" title="Promotional Commission" property="pro_commission" format="{0,number,#,##0.00}"/>
											</display:table>
									</td></tr>
									<tr><td></td></tr>
      								<tr>
										<td colspan="3">
											<table width="100%" border="0" cellspacing="0" cellpadding="0">
												<tr>
													<td height="25" align="center" valign="middle">
														<s:submit name="submit2" cssClass="btn" value="Back" action="smartAreport"/>
													</td>
												</tr>
											</table>
										</td>
									</tr>
       							</table>
       							<s:hidden name="startDate"/>
       							<s:hidden name="endDate"/>
       							<s:hidden name="productID"/>
       							<s:hidden name="broker"/>
       							<s:hidden name="commodity"/>
       							<s:hidden name="busType"/>
       							<s:hidden name="orginCountry"/>
       							<s:hidden name="destCountry"/>
       							<s:hidden name="transportId"/>
       							<s:hidden name="coverId"/>
       							<s:hidden name="rags"/>
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
