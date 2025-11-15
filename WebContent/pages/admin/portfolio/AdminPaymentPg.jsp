<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="/struts-jquery-tags" prefix="sj"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<sj:head jqueryui="true" jquerytheme="start" />
		<script>
		function print(ed)
		{
		    var pid=document.getElementById("productID").value;
			var rep=document.getElementById("rep").value;
		    var fs='';
			var Print="print";
			var URL = "${pageContext.request.contextPath}/admin/PendingQuotesPrint.jsp?eDate="+ed+"&rep="+rep+"&productId="+pid+"&freightStatus="+fs;
		
			var windowName = "PremiumSummarySheet_BI";
			var width  = screen.availWidth;
			var height = screen.availHeight;
			var w = 900;
			var h = 450;
			var features =
			'width='          + w +
			',height='		  + h +
			',left='		  + ((width - w - 10) * .5)  +
			',top='			  + ((height - h - 30) * .5) +
			',directories=no' +
			',location=no'	  +
			',menubar=no'	  +
			',scrollbars=yes' +
			',status=yes'	  +
			',toolbar=no'	  +
			',resizable=false';
			var strOpen = window.open (URL, windowName, features);
			return false;
		}
		function viewPolicys(policynumber,loginid,policyModee,productId,openNo) {
			document.form1.policynumber.value=policynumber;
			document.form1.loginid.value=loginid;
			document.form1.policyMode.value=policyModee;
			
			if(policyModee=='treaty'){
				document.form1.polNo.value=policynumber;
				document.form1.reqFrom1.value='view';
				document.form1.action='treatyPortfolio.action';
				document.form1.submit();
			
			}else{
			
				var verno='';
				var pdfstatus='';
				var displaytext='';
		      	var URL ='${pageContext.request.contextPath}/Copy of information Admin.jsp?policyMode='+policyModee+'&policynumber='+policynumber+'&loginid='+loginid+'&productTypeIds='+productId+'&openCoverNoSettingCert='+openNo+'verNo='+verno+'pdfStatus='+pdfstatus+'disPlayText='+displaytext;
		
				var windowName = "PolicyView";
				var width  = screen.availWidth;
				var height = screen.availHeight;
				var w = 700;
				var h =	450;
				var features =
					'width='          + w +
					',height='		  + h +
					',left='		  + ((width - w - 10) * .5)  +
					',top='			  + ((height - h - 30) * .5) +
					',directories=no' +
					',location=no'	  +
					',menubar=no'	  +
					',scrollbars=yes' +
					',status=yes'	  +
					',toolbar=no'	  +
					',resizable=false';
				var strOpen = window.open (URL, windowName, features);
				strOpen.focus();
				return false;
			}
		}
				
		function fnSubmit() {
		document.form1.action="openPortfolio.action";
		document.form1.submit();
		}

	</script>
	<style>
	
	
	</style>
	
	
	</head>
	<body>
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0"> 
			<tr>
		    	<td height="5"><b id="val1" style="color:red"></b><br/>
		<b id="val" style="color:red"></b> <br/></td>
		  	</tr>
		  	<tr>
		    	<td><table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#E5E5E5">
		      		<tr>
		        		<td bgcolor="#FFFFFF" style="padding:10px; background:#F8F8F8">
								<br /><br /><s:form name="form1" theme="simple">
							 		<table width="100%" align="center">
								        <tr>
								            <td class="heading" width="100%">Payment Report</td>
								        </tr>
									<tr>
										<td>
											<table width="100%">
												<!-- Empty Row for Spacing -->
												<tr>
													<td>&nbsp;</td>
												</tr>
												<tr>
													<td>
														<table width="100%">
															<!-- Row 1: From Date and To Date -->
															<tr>
																<td width="5%"></td>
																<td width="10%" align="right"
																	style="padding-right: 5px;"><s:text
																		name="from.date" /></td>
																<td width="2%">:</td>
																<td width="25%">
																	<div class="inputAppend">
																		<sj:datepicker name="fromdate" cssClass="inputBox1"
																			displayFormat="dd/mm/yy" changeMonth="true"
																			changeYear="true" maxDate="+0D" readonly="true"
																			cssStyle="width:80%; border: none; background-color: #ffffff;" />
																	</div>
																</td>
																<td width="6%"></td>
																<td width="15%" align="right"
																	style="padding-right: 5px;"><s:text name="to.date" /></td>
																<td width="2%">:</td>
																<td width="25%">
																	<div class="inputAppend">
																		<sj:datepicker name="todate" cssClass="inputBox1"
																			displayFormat="dd/mm/yy" changeMonth="true"
																			changeYear="true" maxDate="+0D" readonly="true"
																			cssStyle="width:80%; border: none; background-color: #ffffff;" />
																	</div>
																</td>
																<td width="5%"></td>
															</tr>
															<!-- Row 2: Product and Type (Swapped with Quote No) -->
															<tr>
																<td width="5%"></td>
																<td width="10%" align="right"
																	style="padding-right: 5px;"><s:text
																		name="product.select" /></td>
																<td width="2%">:</td>
																<td width="25%"><s:select name="productID"
																		id="productID" list="products" listKey="PRODUCT_ID"
																		listValue="PRODUCT_NAME" headerKey="1"
																		headerValue="ALL" cssClass="inputSelect" /></td>
																<td colspan="1" align="center" style="padding: 10px 0;">(OR)</td>
																<td width="15%" align="right"
																	style="padding-right: 5px;"> Quote No and Merchant Reference No</td>
																<td width="2%">:</td>
																<td><s:textfield name="quoteno" id="quoteno"
																		cssClass="inputBox1" /></td>
																<td width="5%"></td>
															</tr>
															
															<!-- Row 3: Type (Swapped with Quote No) -->
															<tr style="display: none;">
																<td width="5%"></td>
																<td width="10%" align="right"
																	style="padding-right: 5px;"><s:text name="Type" /></td>
																<td width="2%">:</td>
																<td width="25%"><s:select name="rep" id="rep"
																		list="#{'P':'Pending Payment','S':'Success Payment','E':'Expried & Failed Payment'}"
																		cssClass="inputSelect" /></td>
																<td width="5%"></td>
															</tr>  
															<!-- Row 4: Search Button -->
															<tr>
																<td colspan="9" align="center"
																	style="padding-top: 15px;"><input type="button"
																	onclick="getPortfolio('paymentsLists')" class="btn"
																	value="Search" /></td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</td>
									</tr>


									<tr>
							      			<td align="center"><div id="paymentsLists">
							      			     <s:if test='!"viewList".equals(optionMode)'>	
							      					 <display:table name="portfolioList" pagesize="10" requestURI="/getListPortfolio.action" class="table" uid="row" id="record">
														<display:setProperty name="paging.banner.one_item_found" value="" />
														<display:setProperty name="paging.banner.one_items_found" value="" />
														<display:setProperty name="paging.banner.all_items_found" value="" />
														<display:setProperty name="paging.banner.some_items_found" value="" />
														<display:setProperty name="paging.banner.placement"	value="bottom" />
														<display:setProperty name="paging.banner.onepage" value="" />
														
														<display:column sortable="true" style="text-align:center;font-size:15px;width:15%;" title="SNO" value="${record_rowNum}"/>
														<display:column sortable="true" style="text-align:left;font-size:13px;" title="QUOTE_NO" property="QUOTE_NO" />
														<display:column sortable="true" style="text-align:left;font-size:13px;" title="MERCHANT_REFERENCE" property="MERCHANT_REFERENCE" />
														<display:column sortable="true" style="text-align:left;font-size:13px;" title="CUSTOMER NAME" property="CUSTOMER_NAME" />
														<display:column sortable="true" style="text-align:left;font-size:13px;" title="PREMIUM" property="PREMIUM" />
														<display:column sortable="true" style="text-align:center;font-size:13px;width:35%;"  title="DATE" property="REQUEST_TIME" ></display:column>
														<display:column sortable="true" style="text-align:left;font-size:13px;" title="RESPONSE_STATUS" property="RESPONSE_STATUS" />
														<display:column sortable="true" style="text-align:left;font-size:13px;" title="RESPONSE_MESSAGE" property="RESPONSE_MESSAGE" />
 														
														
														
														
													</display:table>
													
													</s:if>		
                                                 
												</div>
												<div id="viewLists">
												</div>	
											</td>
										</tr>
							      	</table>
										<s:hidden name="policynumber" />
										<s:hidden name="policyno" />
										<s:hidden name="loginid" />
										<s:hidden name="policyMode" />
										<s:hidden name="polNo" />
										<s:hidden name="reqFrom1" />
										<s:hidden name="quoteNo"></s:hidden>
										<s:hidden name="merchantReference"></s:hidden>
							      </s:form>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<script type="text/Javascript">
function getPortfolio(id){
  var fromdate=document.form1.fromdate.value;
  var todate=document.form1.todate.value;
  var productID=document.getElementById("productID").value;
  var rep=document.getElementById("rep").value;
  var quoteno = document.getElementById('quoteno').value;
	postRequest('${pageContext.request.contextPath}/getPayListAjaxPortfolio.action?reqFrom='+id+'&fromdate='+fromdate+'&todate='+todate+'&productID='+productID+'&rep='+rep+'&quoteno='+quoteno, id);
}

function getViewLists(date,id,status,productID){

  postRequest('${pageContext.request.contextPath}/viewPortfolio.action?mode=edit&reqFrom=viewLists&viewdate='+date+'&productID='+productID+'&rep='+status, id);
}
function getView(merchantReference,quoteNo){
 document.form1.merchantReference.value=merchantReference;
 document.form1.quoteNo.value=quoteNo;
 document.form1.action='viewPayPortfolio.action';
 document.form1.submit();
}
function getBack(){
document.form1.action='getListPortfolio.action';
document.form1.submit();
}
function pendingExcel(){
var fromdate=document.form1.fromdate.value;
 var todate=document.form1.todate.value;
 var status='p';
 var productID=document.getElementById("productID").value;
 var freightstatus='';
 var edate='';
 var brokercode='';
 
 //var URL="${pageContext.request.contextPath}/admin/PendingQuoteShowxcelusingPoi.jspfromdate="+fromdate+'&todate='+todate+'&rep='+status+'&productID='+productID";
 document.form1.action='${pageContext.request.contextPath}/admin/PendingQuoteShowxcelusingPoi.jsp?eDate='+edate+'&data1='+fromdate+'&data2='+todate+'&rep='+status+'&productId='+productID+'&broker_codes='+brokercode+'&freightStatus='+freightstatus;
  document.form1.submit();
}

</script>
	</body>
</html>