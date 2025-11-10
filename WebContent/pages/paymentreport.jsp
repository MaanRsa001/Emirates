<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		
			<link href="<%=request.getContextPath()%>/assets1/plugins/dataTables/css/dataTables.bootstrap.min.css" rel="stylesheet">
			<link href="<%=request.getContextPath()%>/assets1/plugins/dataTables/css/dataTables.bootstrap.min.css" rel="stylesheet">
			<link href="<%=request.getContextPath()%>/assets1/plugins/dataTables/css/buttons.bootstrap.min.css" rel="stylesheet">
			<link href="<%=request.getContextPath()%>/assets1/plugins/dataTables/css/fixedHeader.bootstrap.min.css" rel="stylesheet">
			<link href="<%=request.getContextPath()%>/assets1/plugins/dataTables/css/responsive.bootstrap.min.css" rel="stylesheet">
			<link href="<%=request.getContextPath()%>/assets1/plugins/dataTables/css/scroller.bootstrap.min.css" rel="stylesheet">
			<script src="<%=request.getContextPath()%>/assets1/plugins/dataTables/js/jquery.dataTables.min.js"></script>
			<script src="<%=request.getContextPath()%>/assets1/plugins/dataTables/js/dataTables.bootstrap.min.js"></script>
			<script src="<%=request.getContextPath()%>/assets1/plugins/dataTables/js/dataTables.buttons.min.js"></script>
			<script src="<%=request.getContextPath()%>/assets1/plugins/dataTables/js/buttons.bootstrap.min.js"></script>
			<script src="<%=request.getContextPath()%>/assets1/plugins/dataTables/js/buttons.flash.min.js"></script>
			<script src="<%=request.getContextPath()%>/assets1/plugins/dataTables/js/buttons.html5.min.js"></script>
			<script src="<%=request.getContextPath()%>/assets1/plugins/dataTables/js/buttons.print.min.js"></script>
			<script src="<%=request.getContextPath()%>/assets1/plugins/dataTables/js/dataTables.fixedHeader.min.js"></script>
			<script src="<%=request.getContextPath()%>/assets1/plugins/dataTables/js/dataTables.keyTable.min.js"></script>
			<script src="<%=request.getContextPath()%>/assets1/plugins/dataTables/js/dataTables.responsive.min.js"></script>
			<script src="<%=request.getContextPath()%>/assets1/plugins/dataTables/js/responsive.bootstrap.js"></script>
			
			<script type="text/javascript">
			jQuery(function ($) {
				
				try {
					var policyStartDate = document.getElementById("policyStartDate").value;
					var policyEndDate = document.getElementById("policyEndDate").value;
					var userLoginId = document.getElementById("userLoginId").value;
					
					var table = $('#gridReportTable').DataTable({
						 ajax: {
					        url: 'getPaymentJsonReportJson.action?policyStartDate='+policyStartDate+'&policyEndDate='+policyEndDate+'&userLoginId='+userLoginId,
					        dataSrc: 'paymentList'
					    },
					    "columns": [
					    		{"render" : function(data,type,row,meta){
					    		return meta.row+1
					    		}, 'class':'no-sort'},					    			 
					    		{"render" : function(data,type,row,meta){
					    		return row.QUOTE_NO
					    		},'class': 'text-center'},
					    		{"render" : function(data,type,row,meta){
					    		return row.CUSTOMER_NAME
					    		},'class': 'text-center'},
					    		{"render" : function(data,type,row,meta){
					    		return row.CUSTOMER_EMAIL
					    		},'class': 'text-center'},
					    		{"render" : function(data,type,row,meta){
					    		return row.MERCHANT_REFERENCE
					    		},'class': 'text-center'},
					    		{"render" : function(data,type,row,meta){
					    		return row.REQUEST_TIME
					    		},'class': 'text-center'},
					    		{"render" : function(data,type,row,meta){
					    		return row.PREMIUM
					    		},'class': 'text-right'},
					    		{"render" : function(data,type,row,meta){
					    		return row.PAYMENT_LINK
					    		},'class': 'text-center'},
					    		{"render" : function(data,type,row,meta){
					    		return row.ONLINE_PAYLINK_EXPTIME
					    		},'class': 'text-center'},
					    		{"render" : function(data,type,row,meta){
					    		return row.RESPONSE_STATUS
					    		},'class': 'text-center'},
					    		{"render" : function(data,type,row,meta){
					    		return row.RESPONSE_MESSAGE
					    		},'class': 'text-center'},
					    		{"render" : function(data,type,row,meta){
					    		return row.RESPONSE_TIME
					    		},'class': 'text-center'},
					    		{"render" : function(data,type,row,meta){
					    		return row.RESPONSE_TRAN_NO
					    		},'class': 'text-center'},
					    		{"render" : function(data,type,row,meta){
					    		return row.RES_AUTH_CODE
					    		},'class': 'text-center'},
					    		{"render" : function(data,type,row,meta){
					    		return row.CARD_NUMBER_MASKED
					    		},'class': 'text-center'},
					    		{"render" : function(data,type,row,meta){
					    		return row.RES_REQ_CURRENCY
					    		},'class': 'text-center'},
					    		{"render" : function(data,type,row,meta){
					    		return row.RES_DECISION
					    		},'class': 'text-center'},
					    		{"render" : function(data,type,row,meta){
					    		return row.BILL_TO_ADDRESS_LINE1
					    		},'class': 'text-center'},
					    		{"render" : function(data,type,row,meta){
					    		return row.BILL_TO_ADDRESS_CITY
					    		},'class': 'text-center'},
					    		{"render" : function(data,type,row,meta){
					    		return row.BILL_TO_ADDRESS_COUNTRY
					    		},'class': 'text-center'},
					    		{"render" : function(data,type,row,meta){
					    		return row.BILL_TO_ADDRESS_POSTAL_CODE
					    		},'class': 'text-center'}
						    			    
					                   ],
				        "lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
						//"order": [[ 0, "asc" ]],
						"responsive": true,
						"deferRender": true
					});
			    
				} catch(err){ console.log(err);}
			});
			</script>
		</head>
		 
		<s:form name="paymentreport" theme="simple" action="paymentReport.do">
			<div class="container-fluid">
			<div class="panel panel-primary">
			<div class="panel-heading">
					<div class="row">
	       				<div class="col-md-12 col-xs-12">
				         	<h4>Payment Report</h4>
				         </div>
	    			</div>
				</div>
			<div class="panel-body">
				
					<sj:head jqueryui="true" jquerytheme="start" />
					<style type="text/css">
						.ui-datepicker-title .ui-datepicker-year option, .ui-datepicker-month option, select{
							color: black !important;
						}
					</style>
					<div class="row">
						<s:actionerror cssStyle="color: red;"/>
					</div>
					<div class="row">
						<div class="col-md-4">
					    	<div class="form-group">
					    		<s:label key="report.startDate"></s:label><font color="red">*</font>
					    		<sj:datepicker name="policyStartDate"  cssClass="form-control" displayFormat="dd/mm/yy" readonly="true" id="policyStartDate" changeMonth="true" changeYear="true" showAnim="slideDown" duration="fast" />
					    	</div>
					    </div>
					    <div class="col-md-4">
					    	<div class="form-group">
					    		<s:label key="report.endDate"></s:label><font color="red">*</font>
					    		<sj:datepicker name="policyEndDate"   cssClass="form-control" displayFormat="dd/mm/yy" readonly="true" id="policyEndDate" changeMonth="true" changeYear="true" showAnim="slideDown" duration="fast"/>
					    	</div>
					    </div>					    
					    <s:if test='#session.usertype=="RSAIssuer"'>					    
						    <div class="col-md-4">
						    	<div class="form-group">
						    		<s:label key="report.selectuser"></s:label><font color="red">*</font>
						    		<s:select list="policyList1" name="userLoginId" id="userLoginId" listKey="FIRST_NAME" listValue="USERNAME" headerKey="ALL" headerValue="ALL" cssClass="form-control" />
						    	</div>
						    </div>
					    </s:if>
					    <s:else>
					    	<s:hidden name="userLoginId" value="%{#session.user}" id="userLoginId"/>
					    </s:else>
					    
					</div>
					<br/>
					<div class="row" align="center">
						<s:submit type="submit" name="submit" key="Submit"  cssClass="btn btn-success btn-oval"/>
					</div>
					<br/>
				 
				<s:if test='display=="showreport"'>
  			    	<div class="row">  			    	
  			       		<div class="col-md-12">
  			       			<table class="table table-striped table-bordered dt-responsive nowrap" id="gridReportTable" width="100%" cellspacing="0">
									<thead>
										<tr>
											<th class="no-sort"><s:text name="S.NO"/></th>
											<th> <s:text name="Quote No" /> </th>
											<th> <s:text name="Customer Name"/> </th>
											<th> <s:text name="Customer Email"/> </th>
											<th> <s:text name="Unique Number"/> </th>
											<th> <s:text name="Payment Requested Date"/> </th>
											<th> <s:text name="Premium"/> </th>
											<th> <s:text name="Customer Payment Link"/> </th>
											<th> <s:text name="Customer Payment Expire on"/> </th>
											<th> <s:text name="Payment Response"/> </th>
											<th> <s:text name="Response Message"/> </th>
											<th> <s:text name="Response Time"/> </th>
											<th> <s:text name="Response Transaction No"/> </th>
											<th> <s:text name="Response Auth Code"/> </th>
											<th> <s:text name="Card Number Masked"/> </th>
											<th> <s:text name="Currency"/> </th>
											<th> <s:text name="Payment Response (Gateway)"/> </th>
											<th> <s:text name="Payment Address Line"/> </th>
											<th> <s:text name="Payment Address City"/> </th>
											<th> <s:text name="Payment Address Country"/> </th>
											<th> <s:text name="Payment Address Postal Code"/> </th>
											</tr>
										</thead>
							</table>  
		  			  		</div>
  			  			</div>
  			  			</s:if>
  			  		</div>
  			  		</div>
  			  		
					<s:hidden name="downloadType" id="downloadType"/>		
				 
			</div>		
		</s:form>
		