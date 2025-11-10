<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ page language="java" import="com.maan.report.service.ReportService"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<s:if test='menuType!="CHART"'>
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
		</s:if>
		<s:if test='menuType=="CHART"'>
       		<script language="JavaScript" type="text/javascript" src="${pageContext.request.contextPath}/js/highcharts.js"></script>
       	</s:if>
		<script language="JavaScript">
			function stopRKey(evt) { 
			  var evt = (evt) ? evt : ((event) ? event : null); 
			  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
			  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
			} 
			document.onkeypress = stopRKey;
			
			function updateVat(val) {
				document.report.custMissippiCode.value=val;	
				document.report.action = "${pageContext.request.contextPath}/updateVatReport.action";
				document.report.submit();
			}
			<s:if test='menuType!="CHART"'>
				jQuery(function ($) {
					try {
						var table = $('#gridReportTable').DataTable({
							 ajax: {
						        url: 'getJsonReportJson.action?menuType=<s:property value="menuType"/>&loginId=<s:property value="loginId"/>&policyNo=<s:property value="policyNo"/>&originalOpenCoverNo=<s:property value="openCoverNo"/>&searchField=<s:property value="searchField"/>&searchOper=<s:property value="searchOper"/>&searchString=<s:property value="searchString"/>&searchBy=<s:property value="searchBy"/>&openCoverNo=<s:property value="openCoverNo"/>',
						        dataSrc: 'reportGridList'
						    },
						    "columns": [
						    			   {"render" : function(data,type,row,meta){
						    			   		return meta.row+1
						    			   }, 'class':'no-sort'},
						    			   <s:if test='menuType!="T" && menuType!="PD" && menuType!="C"'>
							    			   {"render" : function(data,type,row,meta){
							                   		return row.gquoteNo
				                   				},'class': 'text-center'},
							    			   {"render" : function(data,type,row,meta){
							                   		return row.gcustName
				                   				},'class': 'text-center'},
				                   				<s:if test='menuType!="L" && menuType!="RR" && menuType!="P"'>
								    			   {"render" : function(data,type,row,meta){
								                   		return row.gquoteDate
					                   				},'class': 'text-center'},
				                   				</s:if>
					        				    <s:if test='menuType=="RR"'>
								    			   {"render" : function(data,type,row,meta){
								                   		return row.gquoteDate
					                   				},'class': 'text-center'},
					        				    </s:if>
					        				    <s:elseif test='menuType=="P"'>
								    			   {"render" : function(data,type,row,meta){
								                   		return row.gquoteDate
					                   				},'class': 'text-center'},
					        				    </s:elseif>
				                   			</s:if>
				                   			<s:if test='menuType=="P" || menuType=="PE"'>
					                   			<s:if test='#session.product_id!="31" && #session.product_id!="32" && #session.product_id!="33" && #session.product_id!="34" && #session.product_id!="41" && #session.product_id!="65" && #session.product_id!="30"'>
								    			   {"render" : function(data,type,row,meta){
								                   		return row.gpremium
					                   				},'class': 'text-center'},
					                   				{"render" : function(data,type,row,meta){
								                   		return row.gpolicyNo
					                   				},'class': 'text-center'},
					                   				<s:if test='#session.usertype==getText("ISSUER1")'>
						                   				{"render" : function(data,type,row,meta){
									                   		return row.gotherPolicyNo
						                   				},'class': 'text-center'},
					                   				</s:if>
					                   				{"render" : function(data,type,row,meta){
								                   		return (row.gschedule==null || row.gschedule=='')?'':'<a class="btn btn-success btn-oval" onClick="javascript:popUp(\'${pageContext.request.contextPath}/Copyofinformation.jsp?policyMode=schedule&policynumber='+row.gschedule+'\',1000,500)">Schedule</a>'
					                   				},'class': 'text-center'},
					                   			</s:if>
					        					<s:else>
					                   				{"render" : function(data,type,row,meta){
								                   		return row.goverAllPremium
					                   				},'class': 'text-center'},
					                   				{"render" : function(data,type,row,meta){
								                   		return row.gpolicyNo
					                   				},'class': 'text-center'},
					                   				<s:if test='#session.usertype==getText("ISSUER")'>
						                   				{"render" : function(data,type,row,meta){
									                   		return row.gotherPolicyNo
						                   				},'class': 'text-center'},
					                   				</s:if>
					                   				{"render" : function(data,type,row,meta){
								                   		return (row.gquoteNo==null || row.gquoteNo=='')?'':'<a class="btn btn-success btn-oval" onClick="javascript:popUp(\'${pageContext.request.contextPath}/PdfSummary_Schedule.Travel?quoteNo='+row.gquoteNo+'\',1000,500)">Schedule</a>'
					                   				},'class': 'text-center'},
					                   			</s:else>
				                   				<s:if test='#session.product_id!="31" && #session.product_id!="32" && #session.product_id!="33" && #session.product_id!="34" && #session.product_id!="41" && #session.product_id!="65" && #session.product_id!="30" '>
					                   				{"render" : function(data,type,row,meta){
								                   		return (row.gdebitVat==null || row.gdebitVat=='')?'':'<a class="btn btn-success btn-oval" onClick="javascript:popUp(\'${pageContext.request.contextPath}/Copyofinformation.jsp?policyMode=vatdebit&policynumber='+row.gdebitVat+'\',1000,500)">Tax Invioce</a>'
					                   				},'class': 'text-center'},
					                   				{"render" : function(data,type,row,meta){
								                   		return (row.gcreditVat==null || row.gcreditVat=='')?'':'<a  class="btn btn-success btn-oval" onClick="javascript:popUp(\'${pageContext.request.contextPath}/Copyofinformation.jsp?policyMode=vatcredit&policynumber='+row.gcreditVat+'\',1000,500)">Tax Credit</a>'
					                   				},'class': 'text-center'},
				                   				</s:if>
					                   			<s:if test='#session.product_id=="3"'>
					                   				{"render" : function(data,type,row,meta){
								                   		return (row.gpolicyWording==null || row.gpolicyWording=='')?'':'<a class="btn btn-success btn-oval" onClick="javascript:popUp(\'${pageContext.request.contextPath}/Copyofinformation.jsp?policyMode=clauses&policynumber='+row.gpolicyWording+'\',1000,500)">Policy Wording</a>'
					                   				},'class': 'text-center'},
					                   			</s:if>
				                   				{"render" : function(data,type,row,meta){
							                   		return (row.gendorse==null || row.gendorse=='')?'':'<a class="btn btn-success btn-oval" onClick="javascript:endorsement('+row.gendorse+')">Endorse</a>'
				                   				},'class': 'text-center'},
				                   				{"render" : function(data,type,row,meta){
							                   		return (row.glcupload==null || row.glcupload=='')?'':'<a class="btn btn-success btn-oval" onClick="javascript:lcupload('+row.gquoteNo+')">Documents</a>'
				                   				},'class': 'text-center'},
				                   			</s:if>
				                   			<s:elseif test='menuType=="QL"'>
				                   				{"render" : function(data,type,row,meta){
							                   		return row.gvalidityDate
				                   				},'class': 'text-center'},
				                   				{"render" : function(data,type,row,meta){
							                   		return (row.gactive==null || row.gactive=='')?'':'<a class="btn btn-success btn-oval" onClick="javascript:sentEMail('+row.gactive+')">Active</a>'
				                   				},'class': 'text-center'},
				                   				{"render" : function(data,type,row,meta){
							                   		return (row.gdeactive==null || row.gdeactive=='')?'':'<a class="btn btn-success btn-oval" onClick="javascript:sentEMail('+row.gdeactive+')">Reject</a>'
				                   				},'class': 'text-center'},
				                   			</s:elseif>	
				                   			<s:elseif test='menuType=="QE" || menuType=="QS"'>
				                   				{"render" : function(data,type,row,meta){
							                   		return row.gvalidityDate
				                   				},'class': 'text-center'},
				                   				<s:if test='menuType=="QE"'>
					                   				{"render" : function(data,type,row,meta){
								                   		return row.gpremium
					                   				},'class': 'text-center'},
				                   				</s:if>
				                   				{"render" : function(data,type,row,meta){
							                   		return (row.gedit==null || row.gedit=='')?'':'<a class="btn btn-success btn-oval" onClick="javascript:editQuote('+row.gedit+')">Edit</a>'
				                   				},'class': 'text-center'},
				                   				<s:if test='menuType=="QE"'>
					                   				<s:if test='#session.product_id!="31" && #session.product_id!="32" && #session.product_id!="33" && #session.product_id!="34" && #session.product_id!="65" && #session.product_id!="30" '>
						                   				{"render" : function(data,type,row,meta){
									                   		return  (row.gemailSent==null || row.gemailSent=='')?'':'<a class="btn btn-success btn-oval" onClick="javascript:return sentEMail('+row.gemailSent+')">Email</a>'
						                   				},'class': 'text-center'},
						                   				{"render" : function(data,type,row,meta){
									                   		return (row.gquoteNo==null || row.gquoteNo=='')?'':'<a class="btn btn-success btn-oval" onClick="javascript:viewQuote('+row.gquoteNo+')">Print</a>'
						                   				},'class': 'text-center'},
					                   				</s:if>
						                   			<s:else>
						                   				{"render" : function(data,type,row,meta){
						                   					return  (row.gemailSent==null || row.gemailSent=='')?'':'<a class="btn btn-success btn-oval" onClick="javascript:return sentEMail('+row.gemailSent+')">Email</a>'
						                   				},'class': 'text-center'},
						                   				{"render" : function(data,type,row,meta){
									                   		return (row.gquoteNo==null || row.gquoteNo=='')?'':'<a class="btn btn-success btn-oval" onClick="javascript:popUp(\'${pageContext.request.contextPath}/PdfSummary_Draft.Travel?quoteNo='+row.gquoteNo+'\',1000,500)">Print</a>'
						                   				},'class': 'text-center'},
						                   		    </s:else>
				                   				</s:if>
				                   				{"render" : function(data,type,row,meta){
							                   		return (row.greject==null || row.greject=='')?'':'<a class="btn btn-success btn-oval" onClick="javascript:sentEMail('+row.greject+')">Reject</a>'
				                   				},'class': 'text-center'},
				                   			</s:elseif>
				                   				
				                   		    <s:elseif test='menuType=="RU" || menuType=="RA"'>
						                   		{"render" : function(data,type,row,meta){
								                   	return (row.gedit==null || row.gedit=='')?'':'<a class="btn btn-success btn-oval" onClick="javascript:editQuote('+row.gedit+')">Edit</a>'
					                   			},'class': 'text-center'},
				                   				{"render" : function(data,type,row,meta){
							                   		return (row.gquoteNo==null || row.gquoteNo=='')?'':'<a class="btn btn-success btn-oval" onClick="javascript:viewQuote('+row.gquoteNo+')">Print</a>'
				                   				},'class': 'text-center'},
				                   				{"render" : function(data,type,row,meta){
							                   		return (row.greject1==null || row.greject1=='')?'':'<a class="btn btn-success btn-oval" onClick="javascript:sentEMail('+row.greject1+')">Reject</a>'
				                   				},'class': 'text-center'},
				                   			</s:elseif>
				                   			<s:elseif test='menuType=="RR"'>
					                   			{"render" : function(data,type,row,meta){
							                   		return row.glapsedRemark
				                   				},'class': 'text-center'},
				                   				{"render" : function(data,type,row,meta){
							                   		return (row.gquoteNo==null || row.gquoteNo=='')?'':'<a class="btn btn-success btn-oval" onClick="javascript:viewQuote('+row.gquoteNo+')">Print</a>'
				                   				},'class': 'text-center'},
				                   			</s:elseif>
				                   			<s:elseif test='menuType=="L"'>	
					                   			{"render" : function(data,type,row,meta){
							                   		return row.gquoteDate
				                   				},'class': 'text-center'},
				                   				{"render" : function(data,type,row,meta){
							                   		return row.glapsedDate
				                   				},'class': 'text-center'},
				                   				{"render" : function(data,type,row,meta){
							                   		return row.glapsedRemark
				                   				},'class': 'text-center'},
				                   				{"render" : function(data,type,row,meta){
							                   		return (row.gquoteNo==null || row.gquoteNo=='')?'':'<a class="btn btn-success btn-oval" onClick="javascript:viewQuote('+row.gquoteNo+')">Print</a>'
				                   				},'class': 'text-center'},
				                   			</s:elseif>	
				                   			<s:elseif test='menuType=="T"'>
					                   			{"render" : function(data,type,row,meta){
							                   		return row.gtransactionId
				                   				},'class': 'text-center'},
				                   				{"render" : function(data,type,row,meta){
							                   		return row.gtransactionId1
				                   				},'class': 'text-center'},
				                   				{"render" : function(data,type,row,meta){
							                   		return row.ginvalidRecords
				                   				},'class': 'text-center'},
				                   			</s:elseif>
				                   			<s:elseif test='menuType=="PD"'>
					                   			{"render" : function(data,type,row,meta){
							                   		return row.gpolicyNo
				                   				},'class': 'text-center'},
				                   				{"render" : function(data,type,row,meta){
							                   		return row.gocCustName
				                   				},'class': 'text-center'},
				                   				{"render" : function(data,type,row,meta){
							                   		return row.gpremium
				                   				},'class': 'text-center'},
				                   				{"render" : function(data,type,row,meta){
							                   		return row.gtotalCert
				                   				},'class': 'text-center'},
				                   				{"render" : function(data,type,row,meta){
							                   		return (row.gschedule==null || row.gschedule=='')?'':'<a class="btn btn-success btn-oval" onClick="javascript:popUp(\'${pageContext.request.contextPath}/Copyofinformation.jsp?policyMode=scheduleMultiple&policynumber='+row.gschedule+'\',1000,500)">Schedule</a>'
				                   				},'class': 'text-center'},
				                   				{"render" : function(data,type,row,meta){
							                   		return (row.gdebit==null || row.gdebit=='')?'':'<a class="btn btn-success btn-oval" onClick="javascript:popUp(\'${pageContext.request.contextPath}/Copyofinformation.jsp?policyMode=debitMultiple&policynumber='+row.gdebit+'\',1000,500)">Debit Note</a>'
				                   				},'class': 'text-center'},
				                   				{"render" : function(data,type,row,meta){
							                   		return (row.gcredit==null || row.gcredit=='')?'':'<a class="btn btn-success btn-oval" onClick=javascript:popUp(\'${pageContext.request.contextPath}/Copyofinformation.jsp?policyMode=credit&loginid=<s:property value="loginId"/>&policynumber='+row.gcredit+'\',1000,500)>Credit Note</a>'
				                   				},'class': 'text-center'},
				                   			</s:elseif>
				                   			<s:elseif test='menuType=="C"'>
					                   			{"render" : function(data,type,row,meta){
							                   		return row.gfirstName
				                   				},'class': 'text-center'},
				                   				{"render" : function(data,type,row,meta){
							                   		return row.gaddress
				                   				},'class': 'text-center'},
				                   				{"render" : function(data,type,row,meta){
							                   		return row.gemail
				                   				},'class': 'text-center'},
				                   				{"render" : function(data,type,row,meta){
							                   		return row.gmobile
				                   				},'class': 'text-center'},
				                   				{"render" : function(data,type,row,meta){
							                   		return row.gcustMisspiCode
				                   				},'class': 'text-center'},
				                   				{"render" : function(data,type,row,meta){
							                   		return row.gvatRegNo
				                   				},'class': 'text-center'},
				                   				{"render" : function(data,type,row,meta){
							                   		return (row.gupdateVat==null || row.gupdateVat=='')?'':'<a class="btn btn-success btn-oval" onClick="javascript:updateVat('+row.gupdateVat+')">Update VAT RegNo</a>'
				                   				},'class': 'text-center'},
				                   			</s:elseif>
				                   			<s:elseif test='menuType=="E"'>
					                   			{"render" : function(data,type,row,meta){
							                   		return row.gpremium
				                   				},'class': 'text-center'},
				                   				{"render" : function(data,type,row,meta){
							                   		return  (row.gstatus=='P')?row.gpolicyNo:''
				                   				},'class': 'text-center'},
					                   			<s:if test='#session.product_id=="3" || #session.product_id=="11"'>
							                   			{"render" : function(data,type,row,meta){
									                   		return (row.gstatus=='P')?((row.gschedule==null || row.gschedule=='')?'':'<a class="btn btn-success btn-oval" onClick="javascript:popUp(\'${pageContext.request.contextPath}/Copyofinformation.jsp?policyMode=schedule&policynumber='+row.gschedule+'\',1000,500)">Schedule</a>'):''
						                   				},'class': 'text-center'},
							                   			{"render" : function(data,type,row,meta){
									                   		return (row.gstatus=='P')?((row.gdebit==null || row.gdebit=='')?'':'<a class="btn btn-success btn-oval" onClick="javascript:popUp(\'${pageContext.request.contextPath}/Copyofinformation.jsp?policyMode=debitMultiple&policynumber='+row.gdebit+'\',1000,500)">Debit Note</a>'):''
						                   				},'class': 'text-center'},
					        							{"render" : function(data,type,row,meta){
									                   		return (row.gstatus=='P')?((row.gcredit==null || row.gcredit=='')?'':'<a class="btn btn-success btn-oval" onClick="javascript:popUp(\'${pageContext.request.contextPath}/Copyofinformation.jsp?policyMode=credit&loginid=<s:property value="loginId"/>&policynumber='+row.gcredit+'\',1000,500)">Credit Note</a>'):''
						                   				},'class': 'text-center'},
					                   			</s:if>
					                   			<s:else>
							                   			{"render" : function(data,type,row,meta){
									                   		return (row.gstatus=='P')?((row.gquoteNo==null || row.gquoteNo=='')?'':'<a class="btn btn-success btn-oval" onClick="javascript:popUp(\'${pageContext.request.contextPath}/PdfSummary_Schedule.Travel?quoteNo='+row.gquoteNo+'\',1000,500)">Schedule</a>'):''
						                   				},'class': 'text-center'},
							                   			{"render" : function(data,type,row,meta){
									                   		return (row.gstatus=='P')?((row.gdebit1==null || row.gdebit1=='')?'':'<a class="btn btn-success btn-oval" onClick="javascript:popUp(\'${pageContext.request.contextPath}/PdfSummary_Debit.Travel?quoteNo='+row.gdebit1+'\',1000,500)">Debit Note</a>'):''
						                   				},'class': 'text-center'},
					        							{"render" : function(data,type,row,meta){
									                   		return (row.gstatus=='P')?((row.gcredit==null || row.gcredit=='')?'':'<a class="btn btn-success btn-oval" onClick="javascript:popUp(\'${pageContext.request.contextPath}/Copyofinformation.jsp?policyMode=credit&loginid=<s:property value="loginId"/>&policynumber='+row.gcredit+'\',1000,500)">Credit Note</a>'):''
						                   				},'class': 'text-center'},
						                   		</s:else>
				                   				{"render" : function(data,type,row,meta){
							                   		return row.grefSatus
				                   				},'class': 'text-center'},
				                   				/*{"render" : function(data,type,row,meta){
							                   		return (row.gschedule==null || row.gschedule=='')?'':'<a class="btn btn-success btn-oval" onClick="javascript:popUp(\'${pageContext.request.contextPath}/Copyofinformation.jsp?policyMode=schedule&policynumber='+row.gschedule+'\',1000,500)">Schedule</a>'
				                   				},'class': 'text-center'},*/
				                   				<s:if test='menuBlocker != "viewcert"'>
					                   				{"render" : function(data,type,row,meta){
								                   		return (row.gstatus!='ExistingQuote')?(row.gendtSchedule):''
					                   				},'class': 'text-center'},
				                   				</s:if>
				                   				/*{"render" : function(data,type,row,meta){
							                   		return (row.gdebit==null || row.gdebit=='')?'':'<a class="btn btn-success btn-oval" onClick="javascript:popUp(\'${pageContext.request.contextPath}/Copyofinformation.jsp?policyMode=debitMultiple&policynumber='+row.gdebit+'\',1000,500)">Debit Note</a>'
				                   				},'class': 'text-center'},
				                   				{"render" : function(data,type,row,meta){
							                   		return (row.gcredit==null || row.gcredit=='')?'':'<a class="btn btn-success btn-oval" onClick="javascript:popUp(\'${pageContext.request.contextPath}/Copyofinformation.jsp?policyMode=credit&loginid=<s:property value="loginId"/>&policynumber='+row.gcredit+'\',1000,500)">Credit Note</a>'
				                   				},'class': 'text-center'},*/
				                   			</s:elseif>
				                   			<s:elseif test='menuType=="O"'>
					                   			{"render" : function(data,type,row,meta){
							                   		return row.gpremium
				                   				},'class': 'text-center'},
				                   				{"render" : function(data,type,row,meta){
							                   		return '<a class="btn btn-success btn-oval" onClick="javascript:regeneratePay('+row.gquoteNo+','+row.gapplicationNo+');">Regenerate</a>';
				                   				},'class': 'text-center'},
				                   				{"render" : function(data,type,row,meta){
							                   		return (row.greject==null || row.greject=='')?'':'<a class="btn btn-success btn-oval" onClick="javascript:deactivatePay('+row.gquoteNo+','+row.gapplicationNo+')">DeActivate</a>'
				                   				},'class': 'text-center'}				                   				
				                   			</s:elseif>
						                   ],
					        "lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
							//"order": [[ 0, "asc" ]],
							"responsive": true,
							"deferRender": true
						});
				    
					} catch(err){ console.log(err);}
				});
			</s:if>
			
		</script>
    </head>
<body> 
<s:form name="report" theme="simple">
	<s:set var="format" value="%{'number.format.'+#session.BrokerDetails.CurrencyDecimal}"></s:set>
	<div class="container-fluid">
		<s:if test='menuType!="ET" && menuType!="E" && menuType!="CHART" && (#session.usertype==getText("BROKER") || #session.usertype==getText("ISSUER") || #session.usertype==getText("USER"))'>
	        <s:if test='#session.product_id==getText("OPEN_COVER")'>
	           <ul class="list-group">
		             <li class="list-group-item list-group-item-default">
			             <div class="row">
				             <div class="col-md-4">
					          	<s:label key="endt.policyNo"  />&nbsp;&nbsp;:&nbsp;&nbsp;<s:property value="%{originalPolicyNo}"/>
					         </div>
					         <s:if test='(#session.usertype==getText("ISSUER") && menuType=="P")'>
					 		<div class="col-md-3 col-md-offset-4">
					 			<s:label key="Search By Other Policy No" /> <br/> 
					 			<s:textfield name="otherPolicySearch" id="otherPolicySearch" cssClass="form-control" maxlength="50" /> 
					 		</div> 
					 		<div class="col-md-1">
						  	 	<s:submit value="Search" onclick="initReport" cssClass="btn btn-sm btn-success" />								 		 
					 		</div>
					 	</s:if>
				      	 </div>
			   		</li>
		 	   </ul>
	    	</s:if>
		    <s:if test='(#session.usertype!=getText("USER"))'>
		     	 <s:if test='searchFrom == "BS"'> 
 						<s:hidden name="searchValue" id="searchValue" />&nbsp;<s:hidden name="searchBy" value="policyNo"/>
 					</s:if>
 					<s:if test='(#session.usertype==getText("BROKER") || (#session.product_id!=getText("OPEN_COVER") && #session.usertype==getText("ISSUER")))'>
 						<s:if test='menuType=="R" || menuType=="PR"'>
 							<s:hidden name="loginId"/>
 						</s:if>
 						<s:else>
	 						<ul class="list-group">
					             <li class="list-group-item list-group-item-default">
						             <div class="row">
								         <div class="col-md-4">
							    			<div class="form-group">
								          		<s:label key="report.selectuser"/>
								          		<s:select list="userSelection" cssClass="form-control" listKey="LOGIN_ID" listValue="USERNAME" name="loginId"  headerKey="" headerValue="Select"  onchange="userSelectReport(this)" disabled='issuer !=null && issuer.length() > 0 && #session.product_id==getText("OPEN_COVER")'/>
								          	</div>
								         </div>
								       <s:if test='(#session.usertype==getText("ISSUER") && menuType=="P")'>
								 		<div class="col-md-3 col-md-offset-4">
								 			<s:label key="Search By Other Policy No" /> <br/> 
								 			<s:textfield name="otherPolicySearch" id="otherPolicySearch" cssClass="form-control" maxlength="50" /> 
								 		</div> 
								 		<div class="col-md-1">
									  	 	<s:submit value="Search" onclick="initReport" cssClass="btn btn-sm btn-success" />								 		 
								 		</div>
								 		</s:if>
							 		</div>
								      
								  </li>
							 </ul>
							 <%-- <s:if test='(#session.usertype==getText("ISSUER") && menuType=="P")'>			  				 
								<ul class="list-group">
					             <li class="list-group-item list-group-item-default">					 		
							 		<div>
								 		<div class="col-xs-9">
								 			<s:label key="report.otherPolicyNo" /> <br/> 
								 			<s:textfield name="otherPolicySearch" id="otherPolicySearch" cssClass="inputBox" maxlength="50" /> 
								 		</div> 
								 		<div class="col-xs-3">
									  	 	<s:submit value="Search" onclick="initReport" cssClass="btn btn-sm btn-success" />								 		 
								 		</div>
							 		</div>
							 	</li>
							 	</ul>
							 </s:if> --%>
 						</s:else>
 					</s:if>
 					<s:else>
 						<s:hidden name="loginId"/>
 						
 					</s:else>
 					
		     </s:if>
			 <s:else>
				<s:hidden name="loginId"/>
			 </s:else>
		</s:if>
		<s:elseif test='menuType!="ET" || menuType!="E"'>
			<s:hidden name="loginId"></s:hidden>
		</s:elseif>
		<div class="panel panel-primary">
			<s:if test='menuType!="CHART"'>
				<div class="panel-heading">
					<div class="row">
	       				<div class="col-md-12 col-xs-12">
				         	<h4><s:text name="report.%{menuType}" /></h4>
				         </div>
	    			</div>
				</div>
			</s:if>
			<div class="panel-body">
				<s:if test='menuType=="R"'>
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
					    		<sj:datepicker name="policyStartDate" cssClass="form-control" displayFormat="dd/mm/yy" readonly="true" id="policyStartDate" changeMonth="true" changeYear="true" showAnim="slideDown" duration="fast" />
					    	</div>
					    </div>
					    <div class="col-md-4">
					    	<div class="form-group">
					    		<s:label key="report.endDate"></s:label><font color="red">*</font>
					    		<sj:datepicker name="policyEndDate" cssClass="form-control" displayFormat="dd/mm/yy" readonly="true" id="policyEndDate" changeMonth="true" changeYear="true" showAnim="slideDown" duration="fast"/>
					    	</div>
					    </div>
					    <div class="col-md-4">
					    	<div class="form-group">
					    		<s:label key="report.selectuser"></s:label><font color="red">*</font>
					    		<s:select list="policyList1" name="userLoginId" listKey="FIRST_NAME" listValue="USERNAME" headerKey="" headerValue="-Select-" cssClass="form-control" />
					    	</div>
					    </div>
					</div>
					<br/>
					<div class="row" align="center">
						<s:submit type="submit" name="submit" key="Submit" onclick="forwardReport('PR')" cssClass="btn btn-success btn-oval"/>
					</div>
					<br/>
				</s:if>
				<s:elseif test='menuType=="PR"'>
  			    	<div class="row">
  			       		<div class="col-md-12">
		  					  	<s:if test='#session.product_id!="31" && #session.product_id!="32" && #session.product_id!="33" && #session.product_id!="34" && #session.product_id!="41" && #session.product_id!="65" && #session.product_id!="30"'>  
									<display:table name="policyList" pagesize="10"  requestURI="${pageContext.request.contextPath}/initReport.action" class="table" uid="row" id="record" style="width:250%; align:center; " cellpadding="1" cellspacing="1" export="false" varTotals="totals" >
									<display:setProperty name="paging.banner.one_item_found" value="" />
									<display:setProperty name="paging.banner.one_items_found" value="" />
									<display:setProperty name="paging.banner.all_items_found" value="" />
									<display:setProperty name="paging.banner.some_items_found" value="" />
									<display:setProperty name="paging.banner.placement"	value="bottom" />
									<display:setProperty name="paging.banner.onepage" value="" />	
									<display:setProperty name="basic.empty.showtable" value="true"/>
									<display:setProperty name="paging.banner.no_items_found" value=""/>		
									<display:setProperty name="export.excel" value="false"/>
									<display:setProperty name="export.excel.filename" value="Policy Report.xls"/>	
									<display:setProperty name="export.csv" value="false"/>
									<display:setProperty name="export.pdf" value="false"/>
									<display:setProperty name="export.pdf.filename" value="Policy Report.pdf"/>
									<display:setProperty name="export.xml" value="false"/>		
									<s:set var="myrow" value="#attr.record"/>
																		
									<display:column sortable="true" style="text-align:center;width:2%" title="S.No" value="${record_rowNum}"/> 
									<display:column sortable="true" style="text-align:center;width:4%" title="Policy Issue Date" property="POLICY_ISSUE_DATE"/>
									<display:column sortable="true" style="text-align:left;width:5%" title="Policy No" property="POLICY_NO"/>
									<display:column sortable="true"	style="text-align:center;width:4%" title="Inception Date"	property="INCEPTION_DATE"/>
									<display:column sortable="true" style="text-align:center;width:5%" title="Debit Note No" property="DEBIT_NOTE_NO"/>
									<display:column sortable="true" style="text-align:left;width:5%" title="Invoice No" property="INVOICE_NUMBER"/>
									<display:column sortable="true" style="text-align:left;width:5%" title="Creditnote no" property="CREDIT_NOTE_NO"/>
									<display:column sortable="true"	style="text-align:left;width:8%" title="Insured Name"	property="INSURED_NAME"/>			
									<%--<display:column sortable="true" style="text-align:left;width:7%" title="bank Name" property="BANK_NAME"/>--%>
									<display:column sortable="true" style="text-align:left;width:7%" title="Carrier Name" property="CARRIER_NAME"/>
									<display:column sortable="true"	style="text-align:left;width:8%" title="Country Of Origin"	property="ORIGIN_COUNTRY"/>				
									<display:column sortable="true" style="text-align:left;width:8%" title="Country Of Destination" property="DEST_COUNTRY"/>
									<display:column sortable="true" style="text-align:left;width:6%" title="Form of Transport" property="FORM_OF_TRANSPORT"/>
									<display:column sortable="true"	style="text-align:right;width:4%" title="Sum Insured(Local Currency)"	property="EQUIVALENT_DH" format="{0,number,##,##0.00}" />				
									<display:column sortable="true" style="text-align:right;width:4%" title="Sum Insured(Foreign  Currency)" property="EQUIVALENT_USD" format="{0,number,##,##0.00}"/>
									<display:column sortable="true" style="text-align:center;width:6%" title="Currency Type" property="CURRENCY_TYPE"/>
									<display:column sortable="true"	style="text-align:center;width:4%" title="Basis of Valuation">
										<s:property value='%{#myrow.BASIS_OF_VALUATION}'/>
										<s:if test='%{!"NONE".equals(#myrow.TOLERANCE_NAME)}'>
											+ <s:property value='%{#myrow.TOLERANCE_NAME}'/>
										</s:if>
									</display:column>
									<display:column sortable="true" style="text-align:right;width:4%" title="Marine Premium" property="MARINE_PREMIUM" format="{0,number,##,##0.00}"/>
									<display:column sortable="true" style="text-align:right;width:4%" title="WSRCC Premium" property="WSRCC_PREMIUM" format="{0,number,##,##0.00}" total="true"/>
									<display:column sortable="true"	style="text-align:right;width:4%" title="Excess Premium"	property="EXCESS_PREMIUM" format="{0,number,##,##0.00}"/>				
									<display:column sortable="true"	style="text-align:right;width:4%" title="Issuance Fee"	property="POLICY_FEE" format="{0,number,##,##0.00}"/>			
									<display:column sortable="true" style="text-align:right;width:4%" title="Total Premium" property="TOTAL_PREMIUM" format="{0,number,##,##0.00}" total="true"/>
									<s:if test='creditNoteStatus=="Y"'>
										<display:column sortable="true" style="text-align:right;width:4%" title="Commission" property="COMMISSION" format="{0,number,##,##0.00}" total="true"/>
									</s:if>
									<display:column sortable="true"	style="text-align:center;width:4%" title="Goods Description"	property="GOODS_DESCRIPTION"/>
									<s:if test='creditNoteStatus=="Y"'>
										<display:footer><tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
										<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td align="right">Total :</td><td align="right"><s:property value="getText(#format,{#attr.totals.column20})"/></td><td align="right"><s:property value="getText(#format,{#attr.totals.column21})"/></td></tr></display:footer>												
									</s:if>
									<s:else>
										<display:footer><tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
										<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td align="right">Total :</td><td align="right"><s:property value="getText(#format,{#attr.totals.column20})"/></td></tr></display:footer>												
									</s:else>
									</display:table>
								</s:if>
								<s:else>
									<display:table name="policyList" pagesize="10"  requestURI="${pageContext.request.contextPath}/initReport.action?reqFrm=Normal" class="table" uid="row" id="record" style="width:250%; align:center; " cellpadding="1" cellspacing="1" export="false" varTotals="totals" >
										<display:setProperty name="paging.banner.one_item_found" value="" />
										<display:setProperty name="paging.banner.one_items_found" value="" />
										<display:setProperty name="paging.banner.all_items_found" value="" />
										<display:setProperty name="paging.banner.some_items_found" value="" />
										<display:setProperty name="paging.banner.placement"	value="bottom" />
										<display:setProperty name="paging.banner.onepage" value="" />	
										<display:setProperty name="basic.empty.showtable" value="true"/>
										<display:setProperty name="paging.banner.no_items_found" value=""/>
										<display:setProperty name="export.excel" value="false"/>
										<display:setProperty name="export.pdf" value="false"/>
										<display:setProperty name="export.excel.filename" value="Policy Report.xls"/>	
										<display:setProperty name="export.pdf.filename" value="Policy Report.pdf"/>
										<display:setProperty name="export.csv" value="false"/>
										
										<display:setProperty name="export.xml" value="false"/>											
										<display:column sortable="true" style="text-align:center;" titleKey="report.sno" value="${record_rowNum}"/> 
										<display:column sortable="true" style="text-align:left;" titleKey="report.insuredName" property="INSURED_NAME"/>
										<display:column sortable="true" style="text-align:center;" titleKey="report.policyNo" property="POLICY_NO"/>
										<display:column sortable="true"	style="text-align:right;" title="Premium"	property="OVERALL_PREMIUM"  format="{0,number,##,##0.00}" total="true"/>				
										<display:column sortable="true" style="text-align:right;" title="Commission" property="COMMISSION"  format="{0,number,##,##0.00}" total="true"/>
										<display:column sortable="true" style="text-align:left;" title="Mode of Payment" property="PAYMENT_MODE"/>
										<%--<display:column sortable="true" style="text-align:left;width:15%" title="Product/Scheme Name" property="PRODUCT_DESCRIPTION"/>--%>
										<display:column sortable="true" style="text-align:center;" title="Policy Issue Date" property="EFFECTIVE_DATE"/>
										<display:column sortable="true"	style="text-align:left;" title="Policy Inception Date"	property="INCEPTION_DATE"/>				
										<display:column sortable="true" style="text-align:center;" title="Policy Expiry Date" property="EXPIRY_DATE"/>
										<display:column sortable="true" style="text-align:center;" title="Debit Note No" property="DEBIT_NOTE_NO"/>
										<display:column sortable="true" style="text-align:center;" title="Receipt No" property="RECEIPT_NO"/>
										<display:footer><tr><td>&nbsp;</td><td>&nbsp;</td><td align="right">Sub Total :</td><td align="right"><s:property value="getText(#format,{#attr.totals.column4})"/></td><td align="right"><s:property value="getText(#format,{#attr.totals.column5})"/></td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr></display:footer>
									</display:table>
								</s:else>
		  			  		</div>
  			  			</div>
  			  		</div>
  			  		<br/>
	  			    <div class="row" align="center">
		  				<input type="button" onclick="exportdata('excel')" class="btn btn-info btn-oval" value="Excel"/>
		  				<input type="button" onclick="exportdata('pdf')" class="btn btn-info btn-oval" value="Pdf"/>
		  				<input type="button" onclick="menuSelect('R')" class="btn btn-danger btn-oval" value="Back"/>
	  				</div>
  			  		<br/>
			  		<s:hidden name="policyStartDate"/>
					<s:hidden name="policyEndDate"/>
					<s:hidden name="userLoginId"/>
					<s:hidden name="downloadType" id="downloadType"/>		 
  				</s:elseif>
  				<s:elseif test='menuType=="ET"'>
  					<ul class="list-group">
	                    <li class="list-group-item list-group-item-default">
	                      <div class="row">
				             <div class="col-md-4">
					          	<s:label key="endt.policyNo"  />&nbsp;&nbsp;:&nbsp;&nbsp;<s:property value="%{policyNo}"/>
					         </div>
					         <div class="col-md-4">
					          	<s:label key="endt.brokerName"/>&nbsp;&nbsp;:&nbsp;&nbsp;<s:property value="%{brokerCompany}"/>
					         </div>
				             <div class="col-md-4">
					          	<s:label key="endt.custName"  />&nbsp;&nbsp;:&nbsp;&nbsp;<s:property value="%{custName}"/>
					         </div>
				          </div>
	                    </li>
	                </ul>
	                <br/>
	                <s:actionerror cssClass="color:red;"/>
	                <s:set var="list" value="endType"/>
	        		<s:set var="#typelist" value="endTypeList"/>
	                <div class="panel panel-primary">
	                	<div class="panel-heading">
							<div class="row">
			    				<div class="col-md-12">
			    					<h4><s:text name="Non-Financial Endorsement" /></h4>
			    				</div>
			    			</div>
						</div>
						<div class="panel-body">
							<div class="row">
								<s:iterator value="#typelist" var="type" status="stat">
									<s:if test='%{"1"==#type.ENDT_TYPE_CATEGORY_ID}'>
										<div class="col-md-4">
											<s:checkbox name="endType" value="%{#type.ENDT_TYPE_ID in #list}" id="%{#type.ENDT_TYPE_ID}" fieldValue="%{#type.ENDT_TYPE_ID}" onclick="toggleConfirm();"/><s:label value="%{#type.ENDT_TYPE}" ></s:label>
										</div>
									</s:if>
								</s:iterator>
							</div>
						</div>
	                </div>
	                <s:if test='(#session.usertype==getText("RSAIssuer"))'>
		                <div class="panel panel-primary">
		                	<div class="panel-heading">
								<div class="row">
				    				<div class="col-md-12">
				    					<h4><s:text name="Financial Endorsement" /></h4>
				    				</div>
				    			</div>
							</div>
							<div class="panel-body">
								<div class="row">
									<s:iterator value="#typelist" var="type" status="stat">
										<s:if test='%{"2"==#type.ENDT_TYPE_CATEGORY_ID}'>
											<div class="col-md-4">
												<s:checkbox name="endType" value="%{#type.ENDT_TYPE_ID in #list}" id="%{#type.ENDT_TYPE_ID}" fieldValue="%{#type.ENDT_TYPE_ID}" onclick="toggleConfirm();"/><s:label value="%{#type.ENDT_TYPE}" ></s:label>
											</div>
										</s:if>
									</s:iterator>
								</div>
							</div>
		                </div>
		            </s:if>
		            <div class="panel panel-primary" id="toggle" style="display: none;">
		            	<div class="panel-body">
							<div class="row">
								<div class="col-md-6" >
									Do you really want to cancel this policy?&nbsp;&nbsp;&nbsp;&nbsp;<s:radio list="#{'Y':'Yes','N':'No'}" name="cancelYN"  />
								</div>
								<div class="col-md-6" id="cancelRemarks" style="display: none;">
									<div class="form-group">
										Cancellation Remarks <font color="red">* </font>
										<s:textarea  name="cancelRemarks" rows="5" cols="50"  onkeyup="textLimit(this,500)" cssClass="form-control" />
									</div>
								</div>
							</div>
						</div>
		            </div>
		            <br/>
		            <div class="row" align="center">
		            	<s:submit type="button" name="close"  key="Back" cssClass="btn btn-danger btn-oval" onclick="forward('E')"/>
		            	<s:submit type="button" name="submit" key="Proceed" onclick="redirect('/endorsementReport.action?openCoverNo=%{openCoverNo}')" cssClass="btn btn-success btn-oval"/>
		            </div>
		            <br/>
  				</s:elseif>
  				<s:else>
  					<s:if test='menuType=="E"'>
	  					<ul class="list-group">
	  						<li class="list-group-item list-group-item-default">
		                      <div class="row">
					             <div class="col-md-4">
						          	<s:label key="endt.policyNo"  />&nbsp;&nbsp;:&nbsp;&nbsp;<s:property value="%{policyNo}"/>
						         </div>
						         <div class="col-md-4">
						          	<s:label key="endt.brokerName"/>&nbsp;&nbsp;:&nbsp;&nbsp;<s:property value="%{brokerCompany}"/>
						         </div>
					             <div class="col-md-4">
						          	<s:label key="endt.custName"  />&nbsp;&nbsp;:&nbsp;&nbsp;<s:property value="%{custName}"/>
						         </div>
					          </div>
		                    </li>
		                </ul>
  					</s:if>
  					<s:if test='menuType!="CHART"'>
  						<div class="row">
				             <div class="col-md-12">
				             	<table class="table table-striped table-bordered dt-responsive nowrap" id="gridReportTable" width="100%" cellspacing="0">
									<thead>
										<tr>
											<th class="no-sort"><s:text name="S.NO"/></th>
											<s:if test='menuType!="T" && menuType!="PD" && menuType!="C"'>
												<th> <s:text name="Quote No" /> </th>
												<th> <s:text name="Customer Name" /> </th>
												 <s:if test='menuType!="L" && menuType!="RR" && menuType!="P"'>
													<th> <s:text name="Quote Date" /> </th>
												 </s:if>
							        			 <s:if test='menuType=="RR"'>
							        			 	<th> <s:text name="Rejected Date" /> </th>
							        			 </s:if>
							        			 <s:elseif test='menuType=="P"'>
							        			 	<th> <s:text name="Policy Date" /> </th>
							        			 </s:elseif>
											</s:if>
											<s:if test='menuType=="P" || menuType=="PE"'>
												<s:if test='#session.product_id!="31" && #session.product_id!="32" && #session.product_id!="33" && #session.product_id!="34" && #session.product_id!="41" && #session.product_id!="65" && #session.product_id!="30"'>
													<th> <s:text name="Premium" /> </th>
													<th> <s:text name="Policy No" /> </th>
													<s:if test='#session.usertype==getText("ISSUER1")'>
														<th> <s:text name="Other Policy No" /> </th>
													</s:if>
													<th> <s:text name="Schedule" /> </th>
												</s:if>
												<s:else>
													<th> <s:text name="Premium" /> </th>
													<th> <s:text name="Policy No" /> </th>
													<s:if test='#session.usertype==getText("ISSUER")'>
														<th> <s:text name="Other Policy No" /> </th>
													</s:if>
													<th> <s:text name="Schedule" /> </th>
												</s:else>
												<s:if test='#session.product_id!="31" && #session.product_id!="32" && #session.product_id!="33" && #session.product_id!="34" && #session.product_id!="41" && #session.product_id!="65" && #session.product_id!="30" '>
													<th> <s:text name="Tax Invoice" /> </th>
													<th> <s:text name="Tax Credit" /> </th>
												</s:if>
												<s:if test='#session.product_id=="3"'>
													<th> <s:text name="Policy Wording" /> </th>
												</s:if>
												<th> <s:text name="Endorse" /> </th>
												<th> <s:text name="Documents" /> </th>
											</s:if>
											<s:elseif test='menuType=="QL"'>
												<th> <s:text name="Validity Date" /> </th>
												<th> <s:text name="Active" /> </th>
												<th> <s:text name="Reject" /> </th>
											</s:elseif>
											<s:elseif test='menuType=="QE" || menuType=="QS"'>
												<th> <s:text name="Validity Date" /> </th>
												<s:if test='menuType=="QE"'>
													<th> <s:text name="Premium" /> </th>
												</s:if>
												<th> <s:text name="Edit" /> </th>
												<s:if test='menuType=="QE"'>
													<s:if test='#session.product_id!="31" && #session.product_id!="32" && #session.product_id!="33" && #session.product_id!="34" && #session.product_id!="65" && #session.product_id!="30" '>		
														<th> <s:text name="Email" /> </th>
														<th> <s:text name="Print" /> </th>
													</s:if>
													<s:else>
														<th> <s:text name="Email" /> </th>
														<th> <s:text name="Print" /> </th>
													</s:else>
												</s:if>
												<th> <s:text name="Reject" /> </th>
											</s:elseif>
											<s:elseif test='menuType=="RU" || menuType=="RA"'>
												<th> <s:text name="Edit" /> </th>
												<th> <s:text name="Print" /> </th>
												<th> <s:text name="Reject" /> </th>
											</s:elseif>
											<s:elseif test='menuType=="RR"'>
												<th> <s:text name="Remarks" /> </th>
												<th> <s:text name="Print" /> </th>
											</s:elseif>
											<s:elseif test='menuType=="L"'>
												<th> <s:text name="Quote Date" /> </th>
												<th> <s:text name="Rejected Date" /> </th>
												<th> <s:text name="Remarks" /> </th>
												<th> <s:text name="Print" /> </th>
											</s:elseif>
											<s:elseif test='menuType=="T"'>
												<th> <s:text name="Transaction Id" /> </th>
												<th> <s:text name="Valid Records" /> </th>
												<th> <s:text name="Invalid Records" /> </th>
											</s:elseif>
											<s:elseif test='menuType=="PD"'>
												<th> <s:text name="Policy No" /> </th>
												<th> <s:text name="OpenCover Customer Name" /> </th>
												<th> <s:text name="Premium" /> </th>
												<th> <s:text name="Total of Certificates" /> </th>
												<th> <s:text name="Schedule" /> </th>
												<th> <s:text name="Debit Note" /> </th>
												<th> <s:text name="Credit Note" /> </th>
											</s:elseif>
											<s:elseif test='menuType=="C"'>
												<th> <s:text name="Customer Name" /> </th>
												<th> <s:text name="Address" /> </th>
												<th> <s:text name="Email Id" /> </th>
												<th> <s:text name="Contact No." /> </th>
												<th> <s:text name="Customer Code." /> </th>
												<th> <s:text name="VAT Reg No." /> </th>
												<th> <s:text name="Update VAT RegNo" /> </th>
											</s:elseif>
											<s:elseif test='menuType=="E"'>
												<th> <s:text name="Premium" /> </th>
												<th> <s:text name="Policy No" /> </th>
												<s:if test='#session.product_id=="3" || #session.product_id=="11"'>
													<th> <s:text name="Schedule" /> </th>
													<th> <s:text name="Debit Note" /> </th>
													<th> <s:text name="Credit Note" /> </th>
												</s:if>
												<s:else>
													<th> <s:text name="Schedule" /> </th>
													<th> <s:text name="Debit Note" /> </th>
													<th> <s:text name="Credit Note" /> </th>
												</s:else>
												<th> <s:text name="Status" /> </th>
												<%--<th> <s:text name="Schedule" /> </th> --%>
												<s:if test='menuBlocker != "viewcert"'>
													<th> <s:text name="Endorsement" /> </th>
												</s:if>
												<%--<th> <s:text name="Debit Note" /> </th>
												<th> <s:text name="Credit Note" /> </th> --%>
											</s:elseif>
											<s:elseif test='menuType=="O"'>
												<th> <s:text name="Premium" /> </th>
												<th> <s:text name="Regenerate Payment Link" /> </th>
												<th> <s:text name="DeActivate Payment Link" /> </th>
											</s:elseif>
										</tr>
									</thead>
								</table>
				             </div>
				        </div>
  					</s:if>
  					<s:if test='menuType=="P" || menuType=="PE"'>
  						<div class="row" align="center">
  							<s:if test='#session.product_id!=getText("OPEN_COVER")'>
  							</s:if>
  							<s:else>
  								<s:submit type="button" name="close"  key="Back" cssClass="btn btn-danger btn-oval" onclick="redirect('/ViewOpenCovers.jsp')"/>
  							</s:else>
  						</div>
  					</s:if>
  					<s:if test="menuBlocker != 'viewcert'">
  						<s:if test='menuType=="E"'>
  							<div class="row" align="center">
  								<s:submit type="button" name="close"  key="Back" cssClass="btn btn-danger btn-oval" onclick="forward('P')"/>
  								<s:if test='%{(endtStatus==null || endtStatus=="")}'>
  									<s:submit type="button" name="new"  key="Create New" cssClass="btn btn-success btn-oval" onclick="endorsementTypeNew('ET','N')"/>
  								</s:if>
  								<s:elseif test='%{ endtStatus=="N"}'>
  									<a class="btn btn-success btn-oval" onclick="endorsementType('ET','P','','${quotationNo}','${referalStatus}','${appNo}')">Create New</a>
  								</s:elseif>
  							</div>
  						</s:if>
  					</s:if>
  					<s:elseif  test='menuType=="E"'>
  						<div class="row" align="center">
  							<s:submit type="button" name="close"  key="Back" cssClass="btn btn-danger btn-oval" onclick="forward('P')"/>
  						</div>
  					</s:elseif>
  					<s:if test='menuType=="D"'>
  						<div class="row" align="center">
  							<s:submit type="button" name="close"  key="Back" cssClass="btn btn-danger btn-oval" onclick="forward('T')"/>
							<s:submit type="submit" name="submit" key="Submit" onclick="forward()" cssClass="btn btn-success btn-oval"/>
  						</div>
  					</s:if>
  				</s:else>
  				<s:if test='menuType=="CHART"'>
			        <div class="panel panel-primary">
	  					<div class="panel-heading">
							<div class="row">
			       				<div class="col-md-12 col-xs-12">
			       					<s:if test='#session.product_id=="33" || #session.product_id=="34"'>
			       						<s:text name="report.travelInsurance"/>
			       					</s:if>
			       					<s:elseif test='#session.product_id=="41"'>
			       						<s:text name="report.healthInsurance"/>
			       					</s:elseif>
			       					<s:elseif test='#session.product_id=="65"'>
			       						<s:text name="report.motorInsurance"/>
			       					</s:elseif>
			       					<s:elseif test='#session.product_id=="30"'>
			       						<s:text name="report.homeInsurance"/>
			       					</s:elseif>
			       					<s:elseif test='#session.product_id=="3"'>
			       						<s:text name="Marine Insurance"/>
			       					</s:elseif>
			       					<s:elseif test='#session.product_id=="11"'>
			       						<s:text name="Marine Insurance"/>
			       					</s:elseif>
			       				</div>
			       			</div>
			       		</div>
			       		
	      					<%  String product_id = (String)session.getAttribute("product_id");
			     			String login_id = (String)session.getAttribute("BROKER_LOGIN_ID");
			      			String issuer=(String)session.getAttribute("RSAISSUER")==null?"":(String)session.getAttribute("RSAISSUER");				
							String openCoverNo=(String)session.getAttribute("openCoverNo")==null?"":(String)session.getAttribute("openCoverNo");
							
			      			if(login_id==null){
			      				login_id = (String)session.getAttribute("user");
			      				}
							final ReportService service=new ReportService();
							String originalOC=service.getOriginalPolicyNo(openCoverNo);
							int[] exist=service.getTRExisting(login_id, product_id,issuer,originalOC);
							int[] portfo=service.getTRPortfolio(login_id, product_id,issuer,originalOC);
							int[] laps=service.getTRLapsed(login_id, product_id,issuer,originalOC);
							int[] unapprove=service.getTRUnapproved(login_id, product_id,issuer,originalOC);
							int[] approve=service.getTRApproved(login_id, product_id,issuer,originalOC);
							int[] rej=service.getTRReject(login_id, product_id,issuer,originalOC);
						%>
						<script language="JavaScript" type="text/javascript">
							$(function () {
							        $('#container').highcharts({
							    
							            chart: {
							                type: 'column'
							            },
							    
							            title: {
							                text: 'Last One Month Reports'
							            },
							    
							            xAxis: {
							                categories: ['Existing + Lapsed', 'Portfolio', 'UnApproved + Approved + Rejected']
							            },
							    
							            yAxis: {
							                allowDecimals: false,
							                min: 0,
							                title: {
							                    text: 'Number of Quotations'
							                }
							            },
							    
							            tooltip: {
							                formatter: function() {
							                    return '<b>'+ this.x +'</b><br/>'+
							                        this.series.name +': '+ this.y +'<br/>'+
							                        'Total: '+ this.point.stackTotal;
							                }
							            },
							    
							            plotOptions: {
							                column: {
							                    stacking: 'normal'
							                }
							            },
							    
							            series: [{
							                name: 'Existing',
							                data: [<%= exist[0] %>, 0, 0],
							                stack: 'Existing + Lapsed'
							            }, {
							                name: 'Lapsed',
							                data: [ <%= laps[0] %>, 0, 0],
							                stack: 'Existing + Lapsed'
							            },  {
							                name: 'Portfolio',
							                data: [0, <%= portfo[0] %>,0],
							                stack: 'Portfolio'
							            }, {
							                name: 'UnApproved',
							                data: [0, 0, <%= unapprove[0] %>],
							                stack: 'UnApproved + Approved + Rejected'
							            }, 
							            {
							                name: 'Approved',
							                data: [0, 0, <%= approve[0] %>],
							                stack: 'UnApproved + Approved + Rejected'
							            }, {
							                name: 'Rejected',
							                data: [0,0, <%= rej[0] %>],
							                stack: 'UnApproved + Approved + Rejected'
							            }]
							        });
							    });
						    
						 $(document).ready(function(){
					        var colors = Highcharts.getOptions().colors,
					            categories = ['Existing', 'Lapsed', 'Portfolio','Approved'],
					            name='Total Revenue',
					            data = [{
					                    y: <%= exist[1] %>,
					                    color: colors[0]
					                }, {
					                    y: <%= laps[1] %>,
					                    color: colors[1]
					                }, {
					                    y: <%= portfo[1] %>,
					                    color: colors[2]
					                }, {
					                    y: <%= approve[1] %>,
					                    color: colors[4]
					                }];
							    var chart = $('#container2').highcharts({
						            chart: {
						                type: 'column'
						            },
						            title: {
						                text: 'Last One Month Reports'
						            },
						            xAxis: {
						                categories: categories
						            },
						            yAxis: {
						                title: {
						                    text: 'Total Revenue'
						                }
						            },
						            plotOptions: {
						                column: {
						                    cursor: 'pointer',
						                    dataLabels: {
						                        enabled: true,
						                        color: colors[0],
						                        style: {
						                            fontWeight: 'bold'
						                        }
						                    }
						                }
						            },
						            tooltip: {
						                formatter: function() {
						                    var point = this.point,
						                        s = this.x +':<b>'+ this.y+' <b><s:property value="#session.BrokerDetails.CurrencyAbb"/>';
						                    return s;
						                }
						            },
						            series: [{
						                name: name,
						                data: data,
						                color: 'white'
						            }],
						            exporting: {
						                enabled: false
						            }
						        })
						    });
						
						</script>
						<div class="panel-body">
							<div class="row" id="container">
							</div>
							<div class="row" id="container2">
							</div>
						</div>
					</div>	
  				</s:if>
			   <s:hidden name="quoteNo" ></s:hidden>
			   <s:hidden name="policyNo" ></s:hidden>
			   <s:hidden name="quoteStatus" ></s:hidden>
			   <s:hidden name="tranId" ></s:hidden>
			   <s:hidden name="menuType"></s:hidden> 
			   <s:hidden name="applicationNo" ></s:hidden>
			   <s:hidden name="linkType" ></s:hidden>
			   <s:hidden name="endTypeId" ></s:hidden>
			   <s:hidden name="endStatus" ></s:hidden>
			   <s:hidden name="custName" ></s:hidden>
			   <s:hidden name="brokerCompany" ></s:hidden>
			   <s:hidden name="searchFrom" ></s:hidden>
			   <s:hidden name="customerId" ></s:hidden>
			   <s:hidden name="searchFrom" ></s:hidden>
			   <s:hidden name="display" ></s:hidden>
			   <s:hidden name="reqFrm" ></s:hidden>
			   <s:hidden name="reqFrom" ></s:hidden>
			   <s:hidden name="selRow" id="selRow"/>
			   <s:hidden name="endtLoginId"/>
			   <s:hidden name="menuBlocker"/>
			    <s:hidden name="custMissippiCode"/>
   			</div>
   		</div>
    </div>
 </s:form>
</body>
<script type="text/javascript">
<s:if test='"ET".equals(menuType)' >
    toggleConfirm();
</s:if>
function exportdata(val) {
	document.report.downloadType.value=val;	
	document.report.action='${pageContext.request.contextPath}/reportReport.action';	
	document.report.submit();
}
function editQuote(applicationNo,quoteNo, status,customerId)
{ 
	document.report.quoteNo.value=quoteNo;
	document.report.quoteStatus.value=status;
	document.report.applicationNo.value=applicationNo;
	if(31=='<s:property value="#session.product_id"/>' || 32=='<s:property value="#session.product_id"/>' || 33=='<s:property value="#session.product_id"/>' || 34=='<s:property value="#session.product_id"/>' || 65=='<s:property value="#session.product_id"/>'){
		if(status=='RA'){
		if(65=='<s:property value="#session.product_id"/>')
			document.report.action = "${pageContext.request.contextPath}/editQuoteMotor.action";
		else
			document.report.action = "${pageContext.request.contextPath}/showQuoteTravel.action";
		}else
		{
			document.report.customerId.value=customerId;
			document.report.display.value='getQuote';
			if(65=='<s:property value="#session.product_id"/>')
				document.report.action = "${pageContext.request.contextPath}/editQuoteMotor.action";
			else
				document.report.action = "${pageContext.request.contextPath}/editCustomer.action";
		}
	}else if(41=='<s:property value="#session.product_id"/>')
	{
		if(status=='RA')
			document.report.action = "${pageContext.request.contextPath}/showQuoteHealth.action";
		else
		{
			document.report.customerId.value=customerId;
			document.report.display.value='getQuote';
			document.report.action = "${pageContext.request.contextPath}/editCustomer.action";
		}
	}else if(30=='<s:property value="#session.product_id"/>')
	{
		if(status=='RA')
			document.report.action = "${pageContext.request.contextPath}/initHome.action";
		else
		{
			document.report.customerId.value=customerId;
			document.report.display.value='getQuote';
			document.report.action = "${pageContext.request.contextPath}/initHome.action";
		}
	}else{
		document.report.action = "${pageContext.request.contextPath}/editQuoteQuotation.action";
	}
	document.report.submit();
}
function declaration(tranId)
{
	document.report.tranId.value=tranId;
	document.report.menuType.value='D';
	document.report.reqFrom.value='declare';
	document.report.action = "${pageContext.request.contextPath}/initDeclaration.action";
	document.report.submit();
}
function forward(menuType)
{
	document.report.menuType.value=menuType;
	document.report.action = "${pageContext.request.contextPath}/initReport.action";
	document.report.submit();
}
function userSelectReport(obj)
{ 	
	if(document.report.searchValue){
		document.report.searchValue.value='';
	}
	if(obj.value==''){
		document.report.loginId.value='<s:property value="#session.user"/>';	
	}else{
	document.report.loginId.value=obj.value;	
	}
			
	document.report.action='${pageContext.request.contextPath}/initReport.action';	
	document.report.submit();
}

function forwardReport(menuType)
{
    document.report.menuType.value=menuType;
	document.report.action='${pageContext.request.contextPath}/initReport.action';	
	document.report.submit();	
}
function viewQuote(val)
{
	var URL ='${pageContext.request.contextPath}/QuotePrint.pdfSchedule?quote_no='+val+'&reqFrom=QuotePrint';
	var windowName = "QuotatiionPrint";
	var width  = screen.availWidth;
	var height = screen.availHeight;
	var w = 900;
	var h =	500;
	var features =
		'width='          + w +
		',height='		  + h +
		',left='		  + ((width - w - 10) * .5)  +
		',top='			  + ((height - h - 30) * .5) +
		',directories=no' +
		',location=no'	  +
		',menubar=no'	  +
		',scrollbars=yes' +
		',status=no'	  +
		',toolbar=no'	  +
		',resizable=false';
	var strOpen = window.open (URL, windowName, features);
	strOpen.focus();
	return false;
}
function sentMail(val)
{
	document.report.scrnFrom.value = "QuoteRegister";
	document.report.quote_no.value=val;
	document.report.action="mailController";
	document.report.submit();
}
function sentEMail(applicationNo,linkType,quoteNo)
{
	document.report.menuType.value='<s:property value="%{menuType}" />';
	document.report.applicationNo.value=applicationNo;
	document.report.linkType.value=linkType;
	document.report.quoteNo.value=quoteNo;		
	document.report.action='${pageContext.request.contextPath}/mailReport.action';
	document.report.submit();
}
function delQuotes(val)
{
	document.report.quote_no.value=val;
	document.report.action='${pageContext.request.contextPath}/LapsedQuote1.jsp';
	document.report.submit();
}
function lapsedQuotes(val)
{
	document.report.quote_no.value=val;
	document.report.action='${pageContext.request.contextPath}/lapsedReport.action';
	document.report.submit();
}
function view(appNo,quoteNo,productId)
{
	if(productId=='3' || productId=='11'){
		popUp('\quotationSchedule.jsp?quoteNo='+quoteNo,650,650);
	}else
	{
		popUp('${pageContext.request.contextPath}/viewTravel.action?quoteNo='+quoteNo+'&applicationNo='+appNo+'&selection=profile',650,650);
	}
}
function redirect(url)
{
	document.report.action='${pageContext.request.contextPath}'+url;
	//document.report.submit();
}
function endorsement(menuType, quoteNo, policyNo, custName, brokerCompany, loginId)
{
	document.report.brokerCompany.value=brokerCompany;
	document.report.custName.value=custName;
	document.report.quoteNo.value=quoteNo;
	document.report.policyNo.value=policyNo;
	document.report.menuType.value=menuType;
	document.report.endtLoginId.value=loginId;
	document.report.action = "${pageContext.request.contextPath}/initReport.action";
	document.report.submit();
}
function endorsementType(menuType, endStatus, typeId, quoteNo, status, applicationNo)
{
	document.report.applicationNo.value=applicationNo;
	document.report.quoteNo.value=quoteNo;
	document.report.menuType.value=menuType;
	document.report.endTypeId.value=typeId;
	document.report.endStatus.value=endStatus;
	document.report.quoteStatus.value=status;
	if(status=='ReferalApproved' || status=='RA'){ //if(status=='RA'){
		document.report.quoteStatus.value='RA';
		document.report.action = "${pageContext.request.contextPath}/initPremium.action";
	}else{
		document.report.action = "${pageContext.request.contextPath}/initReport.action";
	}
	document.report.submit();
}
function endorsementTypeNew(menuType, endStatus)
{
	document.report.menuType.value=menuType;
	document.report.endStatus.value=endStatus;
	document.report.endTypeId.value='';
	document.report.action = "${pageContext.request.contextPath}/initReport.action";
	document.report.submit();
}
function searchByPolicy()
{
	if(document.report.searchValue.value==''){
		alert('Please enter Policy No to search');
		return false;
	}
}
function searchByQuote()
{
	if(document.report.searchValue.value==''){
		alert('Please enter Quote No to search');
		return false;
	}
}
function toggleConfirm(){
	var status=document.getElementById('41').checked;
	if(status){
	   document.getElementById('toggle').style.display="";
	   document.getElementById('cancelRemarks').style.display="";
	}else{
	   document.getElementById('toggle').style.display="none";
	   document.getElementById('cancelRemarks').style.display="none";
	} 
}
function lcupload(policyNo){
	document.report.policyNo.value = policyNo; 
	document.report.action = "${pageContext.request.contextPath}/lcUploadReport.action";
	document.report.submit();
}
function regeneratePay(quoteNo,applicationNo){
	 const xhr = new XMLHttpRequest();
	  const url = '${pageContext.request.contextPath}/regeneratePayPremium.do?quoteNo='+quoteNo+'&applicationNo='+applicationNo+'';

	  xhr.open('POST', url, true); // You can switch to 'POST' and send data in the body if needed

	  xhr.onload = function () {
	    if (xhr.status === 200) {
	      alert('Mail Sent');
	      console.log('Response:', xhr.responseText);
	    } else {
	      alert('Mail call failed. Status: ' + xhr.status);
	    }
	  };

	  xhr.onerror = function () {
	    alert('Request error occurred.');
	  };

	  xhr.send();
}
function deactivatePay(quoteNo,applicationNo){
	 const xhr = new XMLHttpRequest();
	  const url = '${pageContext.request.contextPath}/deactivatePayPremium.do?quoteNo='+quoteNo+'&applicationNo='+applicationNo+'';
	  xhr.open('POST', url, true); 
	  xhr.onload = function () {
	    if (xhr.status === 200) {

	      console.log('Response:', xhr.responseText);
	    } else {
	      alert('error failed. Status: ' + xhr.status);
	    }
	  };

	  xhr.onerror = function () {
	    alert('Request error occurred.');
	  };
	  xhr.send();
	  	document.report.applicationNo.value=applicationNo;
		document.report.quoteNo.value=quoteNo;
		document.report.action = "${pageContext.request.contextPath}/editQuoteQuotation.action";
		document.report.submit();
}

</script>
</html>


