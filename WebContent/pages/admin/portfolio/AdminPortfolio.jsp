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
								            <td class="heading" width="100%">Portfolio</td>
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
																	style="padding-right: 5px;"><s:text
																		name="from.quote" /></td>
																<td width="2%">:</td>
																<td><s:textfield name="quoteno" id="quoteno"
																		cssClass="inputBox1" /></td>
																<td width="5%"></td>
															</tr>
															
															<!-- Row 3: Type (Swapped with Quote No) -->
															<tr>
																<td width="5%"></td>
																<td width="10%" align="right"
																	style="padding-right: 5px;"><s:text name="Type" /></td>
																<td width="2%">:</td>
																<td width="25%"><s:select name="rep" id="rep"
																		list="#{'c':'Pending Quotations','p':'Policy Generated','r':'Pending Reports','d':'Policy Canceled'}"
																		cssClass="inputSelect" /></td>
																<td width="5%"></td>
															</tr>
															<!-- Row 4: Search Button -->
															<tr>
																<td colspan="9" align="center"
																	style="padding-top: 15px;"><input type="button"
																	onclick="getPortfolio('portfolioLists')" class="btn"
																	value="Search" /></td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</td>
									</tr>


									<tr>
							      			<td align="center"><div id="portfolioLists">
							      			     <s:if test='!"viewList".equals(optionMode)'>	
							      					 <display:table name="portfolioList" pagesize="10" requestURI="/getListPortfolio.action" class="table" uid="row" id="record">
														<display:setProperty name="paging.banner.one_item_found" value="" />
														<display:setProperty name="paging.banner.one_items_found" value="" />
														<display:setProperty name="paging.banner.all_items_found" value="" />
														<display:setProperty name="paging.banner.some_items_found" value="" />
														<display:setProperty name="paging.banner.placement"	value="bottom" />
														<display:setProperty name="paging.banner.onepage" value="" />
														
														<display:column sortable="true" style="text-align:center;font-size:15px;width:15%;" title="SNO" value="${record_rowNum}"/>
														<s:if test='"A".equals(rep)'>
															<display:column sortable="true" style="text-align:center;font-size:13px;width:35%;"  title="DATE" property="ENTRY_DATE" ></display:column>
														</s:if>
														<s:else>
															<display:column sortable="true" style="text-align:center;font-size:15px;width:35%;"  title="DATE" >
															<a href="#" onclick="getViewLists('${record.ENTRY_DATE}','portfolioLists','<s:property value="rep"/>','<s:property value="productID"/>')" >${record.ENTRY_DATE} </a>	</display:column>
														</s:else>
														<display:column sortable="true" style="text-align:center;font-size:15px;width:35%;" title="NUMBER OF QUOTES" property="COU" />
													</display:table>
													<s:if test='"A".equals(rep)'><br/><input type="button" align="center" value="Save" onclick="pendingExcel();"/></s:if>
													</s:if>		
                                                 <s:if test='"viewList".equals(optionMode)'>
                                                     Selected Date :<s:property value="viewdate"/>
                                                     <s:hidden name="viewdate"  />
                                                       <display:table name="viewList" pagesize="10" requestURI="/getViewList1Portfolio.action"  class="table" uid="row" id="record">
														    <display:setProperty name="paging.banner.one_item_found" value="" />
															<display:setProperty name="paging.banner.one_items_found" value="" />
															<display:setProperty name="paging.banner.all_items_found" value="" />
															<display:setProperty name="paging.banner.some_items_found" value="" />
															<display:setProperty name="paging.banner.placement"	value="bottom" />
															<display:setProperty name="paging.banner.onepage" value="" />
															<display:column></display:column>
															<s:set var="myrow" value="#attr.record"/>
															
															<s:if test='"c".equals(rep)'>
															<display:column sortable="true" style="text-align:left;font-size:13px;" title="SNO"  value="${record_rowNum}"/>
															<display:column sortable="true" style="text-align:left;font-size:13px;" title="BROKER ORAGANIZATION" property="BROKER_ORGANIZATION" />
															<display:column sortable="true" style="text-align:left;font-size:13px;" title="QUOTE CREATED BY" property="LOGIN_ID" />
															<display:column sortable="true" style="text-align:left;font-size:13px;" title="QUOTE NO" property="QUOTE_NO" />
															<display:column sortable="true" style="text-align:left;font-size:13px;" title="CUSTOMER NAME" property="FIRST_NAME" />
															<display:column sortable="true" style="text-align:right;font-size:13px;" title="PREMIUM" property="PREMIUM" format="{0,number,0.00}"/>
															<display:column sortable="true" style="text-align:left;font-size:13px;" title="VIEW DRAFT">
															   <a href="#" onclick="viewPolicys('${record.QUOTE_NO}','${record.LOGIN_ID}','draft','${record.PRODUCT_ID}','${record.OPEN_COVER_NO}')">Draft</a>
															</display:column>
															</s:if>
															<s:elseif test='"p".equals(rep)'>
																<display:column sortable="true" style="text-align:left;font-size:13px;" title="SNO"  value="${record_rowNum}"/>
																<display:column sortable="true" style="text-align:left;font-size:13px;" title="BROKER ORAGANIZATION" property="BROKER_ORGANIZATION" />
																<display:column sortable="true" style="text-align:left;font-size:13px;" title="QUOTE CREATED BY" property="LOGIN_ID" />
																<display:column sortable="true" style="text-align:left;font-size:13px;" title="POLICY NO" property="POLICY_NO" />
																<display:column sortable="true" style="text-align:left;font-size:13px;" title="CUSTOMER NAME" property="FIRST_NAME" />
																<display:column sortable="true" style="text-align:right;font-size:13px;" title="PREMIUM" property="PREMIUM" format="{0,number,0.00}"/>
																<display:column sortable="true" style="text-align:center;font-size:13px;" title="VIEW PDF">
																   <a href="#" onclick="viewPolicys('${record.POLICY_NO}','${record.LOGIN_ID}','schedule','${record.PRODUCT_ID}','${record.OPEN_COVER_NO}')">View</a>
																</display:column>
																<%--<display:column sortable="true" style="text-align:center;font-size:13px;" title="TREATY CODE">
																	<s:if test='%{!"LINKED".equals(#myrow.OPEN_COVER_INT_STATUS)}'>
																   		<a href="#" onclick="viewPolicys('${record.POLICY_NO}','${record.LOGIN_ID}','treaty','${record.PRODUCT_ID}','${record.OPEN_COVER_NO}')">Edit</a>
																   	</s:if>
																</display:column>--%>
															</s:elseif>
															<s:elseif test='"pc".equals(rep)'>
																<display:column sortable="true" style="text-align:left;font-size:13px;" title="SNO"  value="${record_rowNum}"/>
																<display:column sortable="true" style="text-align:left;font-size:13px;" title="BROKER ORAGANIZATION" property="BROKER_ORGANIZATION" />
																<display:column sortable="true" style="text-align:left;font-size:13px;" title="QUOTE CREATED BY" property="LOGIN_ID" />
																	<display:column sortable="true" style="text-align:left;font-size:13px;" title="POLICY NO" property="POLICY_NO" />
																<display:column sortable="true" style="text-align:left;font-size:13px;" title="CUSTOMER NAME" property="FIRST_NAME" />
																<display:column sortable="true" style="text-align:right;font-size:13px;" title="PREMIUM" property="PREMIUM" format="{0,number,0.00}"/>
																<display:column sortable="true" style="text-align:left;font-size:13px;" title="VIEW PDF">
																   <a href="#" onclick="viewPolicys('${record.POLICY_NO}','${record.LOGIN_ID}','schedule','${record.PRODUCT_ID}','${record.OPEN_COVER_NO}')">View</a>
																</display:column>
																<display:column sortable="true" style="text-align:left;font-size:13px;" title="Status">
																<s:hidden name="policyNo[%{#attr.record_rowNum-1}]" value="%{#myrow.POLICY_NO}" />
																<s:radio list="#{'A':'Active','D':'Deactive'}" name="status[%{#attr.record_rowNum-1}]" value="%{#myrow.POLICY_CANCEL_STATUS}"></s:radio>
																</display:column>
															</s:elseif>
															<s:elseif test='"r".equals(rep)'>
																<display:column sortable="true" style="text-align:left;font-size:13px;" title="SNO"  value="${record_rowNum}"/>
																<display:column sortable="true" style="text-align:left;font-size:13px;" title="BROKER ORAGANIZATION" property="BROKER_ORGANIZATION" />
																<display:column sortable="true" style="text-align:left;font-size:13px;" title="QUOTE CREATED BY" property="LOGIN_ID" />
																<display:column sortable="true" style="text-align:left;font-size:13px;" title="QUOTE NO" property="QUOTE_NO" />
																<display:column sortable="true" style="text-align:left;font-size:13px;" title="CUSTOMER NAME" property="FIRST_NAME" />
																<display:column sortable="true" style="text-align:right;font-size:13px;" title="PREMIUM" property="PREMIUM" format="{0,number,0.00}" />
															</s:elseif>
															<display:column></display:column>
														</display:table>
														<s:if test='"c".equals(rep)'><br/><s:submit align="center" value="Back" cssClass="btn" onclick="getBack();" /></s:if>
														<s:if test='"p".equals(rep)'><br/><s:submit align="center" value="Back" cssClass="btn" /></s:if>
														<s:if test='"pc".equals(rep)'><br/><s:submit align="center" value="Back" cssClass="btn" onclick="getBack();"  /><input type="button" onclick="getSubmit();" value="Submit" /></s:if>
														<s:if test='"r".equals(rep)'><br/><s:submit align="center" value="Back" cssClass="btn" />
														<input type="button" onclick="print('${record.ENTRY_DATE}')" value="PRINT QUOTE" class="btn" />
														<br/></s:if>
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
	postRequest('${pageContext.request.contextPath}/getListAjaxPortfolio.action?reqFrom='+id+'&fromdate='+fromdate+'&todate='+todate+'&productID='+productID+'&rep='+rep+'&quoteno='+quoteno, id);
}

function getViewLists(date,id,status,productID){

  postRequest('${pageContext.request.contextPath}/viewPortfolio.action?mode=edit&reqFrom=viewLists&viewdate='+date+'&productID='+productID+'&rep='+status, id);
}
function getSubmit(){

 document.form1.action='updatePolicyPortfolio.action';
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