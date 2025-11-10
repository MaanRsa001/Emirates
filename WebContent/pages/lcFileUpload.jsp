<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ page language="java" import="com.maan.report.service.ReportService"%>
<%String cpath1 = request.getContextPath(); %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<sj:head jqueryui="true" jquerytheme="start" />
		<script language="JavaScript">
			function stopRKey(evt) { 
			  var evt = (evt) ? evt : ((event) ? event : null); 
			  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
			  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
			} 
			document.onkeypress = stopRKey;
		</script>
    </head>
<body> 
<s:form name="report" theme="simple" enctype="multipart/form-data">
	<s:set var="format" value="%{'number.format.'+#session.BrokerDetails.CurrencyDecimal}"></s:set>
	<div class="container-fluid">
		<div class="panel panel-primary">
			<div class="panel-body">
				<s:actionerror cssClass="color:red;"/>
				<s:set var="lcUploadDetailsVar" value="lcUploadDetails"/>
				<s:set var="lcUploadPloDtlsVar" value="lcUploadPloDtls"/>
				<s:set var="lcUploadPolicyVar" value="lcUploadPolicy"/>
				<s:if test='(#lcUploadPolicyVar!=null && #lcUploadPolicyVar.size()>0) ||  (#lcUploadPloDtlsVar!=null && #lcUploadPloDtlsVar.size()>0)'>
					<div class="panel panel-primary">
						<div class="panel-heading">
							<div class="row">
			    				<div class="col-md-12">
			    					<h4><s:text name="lc.policydetails" /></h4>
			    				</div>
			    			</div>
						</div>
						<div class="panel-body">
							<ul class="list-group">
								<s:iterator value="#lcUploadDetailsVar" var="lcupload">
				                    <li class="list-group-item list-group-item-default">
				                      <div class="row">
							             <div class="col-md-6">
							             	<s:if test=' #lcupload.policy_no ==null || "".equals(#lcupload.policy_no)'>
							             		Quote No&nbsp;&nbsp;:&nbsp;&nbsp;<s:property value="%{#lcupload.quote_no}"/>
							             	</s:if>
							             	<s:else>
								          		<s:label key="lc.policyNo"/>&nbsp;&nbsp;:&nbsp;&nbsp;<s:property value="%{#lcupload.policy_no}"/>
								          	</s:else>
								         </div>
								         <div class="col-md-6">
								          	<s:label key="lc.custName"/>&nbsp;&nbsp;:&nbsp;&nbsp;<s:property value="%{#lcupload.First_name}"/>
								         </div>
							          </div>
				                    </li>
					            </s:iterator>	
					            <s:iterator value="#lcUploadPloDtlsVar" var="lcdtls">
				                    <li class="list-group-item list-group-item-default">
				                      <div class="row">
				                      	 <s:if test='%{!"".equals(#lcdtls.invoice_number) && #lcdtls.invoice_number!=null}'>
								             <div class="col-md-4">
									          	<s:label key="lc.invoiceno"/>&nbsp;&nbsp;:&nbsp;&nbsp;<s:property value="%{#lcdtls.invoice_number}"/>
									         </div>
								         </s:if>
								         <s:if test='%{!"".equals(#lcdtls.invoice_date) && #lcdtls.invoice_date!=null}'>
								             <div class="col-md-4">
									          	<s:label key="lc.invoicedate"/>&nbsp;&nbsp;:&nbsp;&nbsp;<s:property value="%{#lcdtls.invoice_date}"/>
									         </div>
								         </s:if>
								         <s:if test='%{!"".equals(#lcdtls.DESCRIPTION) && #lcdtls.DESCRIPTION!=null}'>
								             <div class="col-md-4">
									          	<s:label key="lc.description"/>&nbsp;&nbsp;:&nbsp;&nbsp;<s:property value="%{#lcdtls.DESCRIPTION}"/>
									         </div>
								         </s:if>
							          </div>
				                    </li>
					            </s:iterator>
			                </ul>
						</div>
					</div>
				</s:if>
				<s:if test='(#lcUploadPolicyVar!=null && #lcUploadPolicyVar.size()>0) '>
					<div class="panel panel-primary">
						<div class="panel-heading">
							<div class="row">
			    				<div class="col-md-12">
			    					<h4><s:text name="report.lcbankdetails" /></h4>
			    				</div>
			    			</div>
						</div>
						<div class="panel-body">
							<ul class="list-group">
								<s:iterator value="#lcUploadPolicyVar" var="lcuploadpolicy">
									<li class="list-group-item list-group-item-default">
										<div class="row">
								             <div class="col-md-4">
								             	<s:label key="lc.bankname"/>&nbsp;&nbsp;:&nbsp;&nbsp;<s:property value="%{#lcuploadpolicy.BANK_NAME}"/>
								             </div>
								             <div class="col-md-4">
								             	<s:label key="lc.lcno"/>&nbsp;&nbsp;:&nbsp;&nbsp;<s:property value="%{#lcuploadpolicy.LC_NUMBER}"/>
								             </div>
								             <s:if test='%{!"".equals(#lcuploadpolicy.LC_DATE) && #lcuploadpolicy.LC_DATE!=null}'>
									             <div class="col-md-4">
									             	<s:label key="lc.lcdate"/>&nbsp;&nbsp;:&nbsp;&nbsp;<s:property value="%{#lcuploadpolicy.LC_DATE}"/>
									             </div>
								             </s:if>
										</div>
									</li>
									<li class="list-group-item list-group-item-default">
										<div class="row">
											<s:if test='%{!"".equals(#lcuploadpolicy.BL_AWB_NO) && #lcuploadpolicy.BL_AWB_NO!=null}'>
									             <div class="col-md-4">
									             	<s:label key="lc.lcblawno"/>&nbsp;&nbsp;:&nbsp;&nbsp;<s:property value="%{#lcuploadpolicy.BL_AWB_NO}"/>
									             </div>
								             </s:if>
								             <s:if test='%{!"".equals(#lcuploadpolicy.BL_AWB_DATE) && #lcuploadpolicy.BL_AWB_DATE!=null}'>
									             <div class="col-md-4">
									             	<s:label key="lc.lcblawdate"/>&nbsp;&nbsp;:&nbsp;&nbsp;<s:property value="%{#lcuploadpolicy.BL_AWB_DATE}"/>
									             </div>
									         </s:if>
									         <s:if test='%{!"".equals(#lcuploadpolicy.SAILING_DATE) && #lcuploadpolicy.SAILING_DATE!=null}'>
									             <div class="col-md-4">
									             	<s:label key="lc.lcsailingdate"/>&nbsp;&nbsp;:&nbsp;&nbsp;<s:property value="%{#lcuploadpolicy.SAILING_DATE}"/>
									             </div>
									         </s:if>
										</div>
									</li>
								</s:iterator>
							</ul>
						</div>
					</div>
				</s:if>
				<div class="row">
					<div class="col-md-12">
						<table class="table" id="lcDoctable">
							<thead>
			      				<tr>
			      					<th scope="col"><s:label key="Upload File" /></th>
			      					<th scope="col"><s:label key="Remarks" /></th>
			      					<th scope="col"><s:label key="Delete" /></th>
			      				</tr>
			      			</thead>
					   		<s:if test="lcFileList.size()>0">
				      			<tbody>
				      				<s:iterator value="lcFileList" var="lcfile">
				      					<s:set var="lcDoc" value="1"/>
				      					<tr>
				      						<td scope="row"><s:property value="%{#lcfile.ORIGINAL_FILE}"/><s:hidden name="hiddencheque" id="hiddencheque" value="%{#lcfile.ORIGINAL_FILE}"></s:hidden></td>
				      						<td><s:property value="%{#lcfile.remarks}"/></td>
				      						<td>
				      							<a href="#" class="btn btn-success btn-oval" onclick="downloadDoc('downloadLcFileReport.action','<s:property value="%{#lcfile.ORIGINAL_FILE}"/>','<s:property value="%{#lcfile.file_Name}"/>')">
												<i class="glyphicon glyphicon-save"></i></a>&nbsp;&nbsp;
												<a href="#" class="btn btn-danger btn-oval" onclick="deleteDoc('deleteLcFileReport.action','<s:property value="%{#lcfile.upload_id}"/>','<s:property value="%{#lcfile.file_Name}"/>')">
												<i class="glyphicon glyphicon-remove"></i></a>
				      						</td>
				      					</tr>
				      				</s:iterator>
				      				<s:if test="menuBlocker != 'viewcert'">
										<tr >
											<td> <s:file cssClass="form-control" name="upload" ></s:file></td>
											<td> <s:textfield name="lcdocremarks" id="lcdocremarks" value='%{lcdocremarks.isEmpty()?"":lcdocremarks}' cssClass="form-control"></s:textfield></td>
											<td></td>
										</tr>
									</s:if>
									<s:hidden name="uploadId" id="uploadId"/>
									<s:hidden name="lcFileName" id="lcFileName"/>
									<s:hidden name="downloadFileName" id="downloadFileName"/>
				      			</tbody>
				      		</s:if>
				      		<s:else>
								<tbody>
					 				<tr>
										<td> <s:file cssClass="form-control" name="upload" ></s:file></td>
										<td> <s:textfield name="lcdocremarks" value='%{lcdocremarks.isEmpty()?"":lcdocremarks}' id="lcdocremarks" cssClass="form-control"></s:textfield>  </td>
										<td>  </td>
									</tr>
						   		</tbody>
							</s:else>
						</table>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12" align="right">
						<input type="button" class="btn btn-info btn-oval" onclick="addMorelc()" value="Add More">
					</div>
				</div>
				<br/>
				<div class="row" align="center">
					
					<s:if test='"Quote".equals(reqFrom)'>
						<input type="button" name="Back"  value="Back" class="btn btn-danger btn-oval" onclick="lcFileUploadBack2();"/>&nbsp;
					</s:if>
					<s:else>
							<input type="button" name="Back"  value="Back" class="btn btn-danger btn-oval" onclick="lcFileUploadBack();"/>&nbsp;
					</s:else>
					<input type="button" name="Submit"  value="Submit" class="btn btn-success btn-oval" onclick="lcFileUpload('<s:property value="%{policyNo}"/>');"/>&nbsp;
				</div>
				<br/>
			</div>
		</div>
	</div>
	<s:hidden name="policyNo"/>
	<s:hidden name="menuType"/>
	<s:hidden name="userLoginId"/>
	<s:hidden name="loginId"/>
	<s:hidden name="menuBlocker"/>
	<s:hidden name="reqFrom"></s:hidden>
	<s:hidden name="applicationNo" value="%{#lcupload.application_no}"></s:hidden>
</s:form>
</body>
<script type="text/javascript">

function lcupload(){
	document.report.menuType.value="U";
	document.report.action = "${pageContext.request.contextPath}/initReport.action";
	document.report.submit();
}
function lcFileUpload(policyNo){
	document.report.policyNo.value = policyNo;
	document.report.action = "${pageContext.request.contextPath}/lcFileUploadReport.action";
	document.report.submit();
}
function addMorelc(){
			var table = document.getElementById('lcDoctable');
			var rowCount = table.rows.length;
			var row = table.insertRow(rowCount);
			row.className="runtext";
			row.id = "new_"+rowCount;
			
			cell = row.insertCell(0);
			var element = document.createElement("input");
			element.className = "form-control";
			element.name = "upload";
			element.id = "upload";
			element.type = "file";
			cell.appendChild(element);
			
			cell = row.insertCell(1);
			var element = document.createElement("input");
			element.className = "form-control";
			element.name = "lcdocremarks";
			element.id = "lcdocremarks";
			element.type = "text";
			cell.appendChild(element);
			
			cell = row.insertCell(2);
			var element = document.createElement("input");
			element.type = "checkbox";
			element.id = "chk"+rowCount;
			element.align = "right";
			element.onclick = function () {deleteRow(this.id,this)};	
		    cell.appendChild(element);
}
function deleteRow(id, el) {
	var decision = confirm("Row will be deleted. Do You Want to continue? ","");{
		if (decision==true){
			while (el.parentNode && el.tagName.toLowerCase() != 'tr') {
				el = el.parentNode;
			}
			if (el.parentNode && el.parentNode.rows.length > 1) {
				el.parentNode.removeChild(el);
			}
		} else {
			document.getElementById(id).checked=false;
		}	   
	}
}
function deleteDoc(val,uplaodId1,fileName1){
	var decision = confirm("Document will be deleted. Do You Want to continue? ","");{
	if (decision==true)
		document.getElementById("uploadId").value=uplaodId1;
		document.getElementById("lcFileName").value=fileName1;
		document.report.action = "${pageContext.request.contextPath}/"+val;
		document.report.submit();
	}
}
function downloadDoc(val,downloadFileName,fileName1){
	document.getElementById("downloadFileName").value=downloadFileName;
	document.getElementById("lcFileName").value=fileName1;
	document.report.action = "${pageContext.request.contextPath}/"+val;
	document.report.submit();
}
function lcFileUploadBack(){
	
	document.report.action = "${pageContext.request.contextPath}/initReport.action";
	document.report.submit();
}
function lcFileUploadBack2(){
	document.report.action = "${pageContext.request.contextPath}/initPremium.action";
	document.report.submit();
}

</script>
</html>


