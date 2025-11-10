<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
    </head>
<body >
	<div class="panel panel-primary">
		<s:form name="commoditySelection" id="commoditySelection" theme="simple" >
			<s:set var="comLst" value="commodityList"/>
			<s:set var="disable1" value="%{((#session.user1==getText('admin')) || status2=='edit') && !'delete'.equals(status1) && (!''.equals(#comLst.size()>0 ? #comLst[0].COMMODITY_ID : ''))}"/>
			<div class="panel-body">
				<div class="row">
					<s:actionerror cssStyle="color:red;"/>
				</div>
				<div class="row">
				<s:if test='productId==3'>
					<div class="col-md-4">
						<div class="form-group">
							<s:text name="Commodity Group"/><font color="red">*</font>
							<s:select name="commoditygroupId" list="goodsCategoryGroupList" headerKey="" headerValue="---Select---" listKey="CODE" listValue="CODEDESC" cssClass="form-control" disabled="%{#disable1}" onchange="fnloadcomList(this.value);"></s:select>
						</div>
					</div>
					</s:if>
				    <div class="col-md-4">
				    	<div class="form-group" id="loadcomList">
				    		<s:text name="Commodity Name" /><font color="red">*</font>
							<s:if test='productId==11'>
								<s:select name="commodityId" list="goodsCategoryList" headerKey="" headerValue="-Select-" listKey="CODE" listValue="CODEDESC" disabled="%{#disable1}" value='#comLst.size()>0 ? #comLst[0].COMMODITY_ID : ""' onchange="this.form.selectCommodity.value=this.value;this.form.__checkbox_selectCommodity.value=this.value;callAjax(this.value);" cssClass="form-control"/>
								<s:checkbox  name="selectCommodity" value="true" fieldValue='%{#comLst.size()>0 ? #comLst[0].COMMODITY_ID : ""}' disabled="#disable" cssStyle="visibility:hidden;"/>
							</s:if>
							<s:else>
								<s:select name="commodityId" list="goodsCategoryList" headerKey="" headerValue="-Select-" listKey="CODE" listValue="CODEDESC" disabled="%{#disable1}" value='#comLst.size()>0 ? #comLst[0].COMMODITY_ID : ""' onchange="this.form.selectCommodity.value=this.value;this.form.__checkbox_selectCommodity.value=this.value;" cssClass="form-control"/>
								<s:checkbox  name="selectCommodity" value="true" fieldValue='%{#comLst.size()>0 ? #comLst[0].COMMODITY_ID : ""}' disabled="#disable" cssStyle="visibility:hidden;"/>
							</s:else>
						</div>
					</div>
				    <div class="col-md-4">
				    	<div class="form-group">
				    		<s:text name="Goods Description" /><font color="red">*</font>
				    		<s:textarea name="commodityDesc" onkeyup="textLimit(this,2000)" value='%{#comLst.size()>0 ? #comLst[0].COMMODITY_DESC==null? #comLst[0].COMMODITY_NAME:#comLst[0].COMMODITY_DESC: ""}' disabled="#disable" cols="34" rows="3"  cssClass="form-control"/>
				    	</div>
				    </div>
			    </div>
				<div class="row">
					<div class="col-md-4">
				    	<div class="form-group">
				    		<s:text name="commodity.insuredValue" /><font color="red">*</font>
				    		<s:textfield name="insuredValue" maxlength="13" onkeyup="checkNumbers(this);"  value='%{#comLst.size()>0 ? #comLst[0].SUM_INSURED : ""}' disabled="#disable" cssClass="form-control" />
				    	</div>
				    </div>
				    <div class="col-md-4">
				    	<div class="form-group">
				    		<s:text name="commodity.invoiceNo" />
				    		<s:textfield name="invoiceNo" cssClass="form-control" maxlength="50" disabled="%{#disable2}"  value='%{#comLst.size()>0 ? #comLst[0].INVOICE_NUMBER : ""}'/>
				    	</div>
				    </div>
				    <div class="col-md-4">
				    	<div class="form-group">
				    		<s:text name="commodity.invoiceDate" />
				    		<s:textfield name="invoiceDate" id="invoiceDate" displayFormat="dd/mm/yy" cssClass="form-control" maxlength="10" disabled="%{#disable2}" onblur="check_date(this);"  value='%{#comLst.size()>0 ? #comLst[0].INVOICE_DATE : ""}'/>
				    		<%--<sj:datepicker cssClass="form-control" displayFormat="dd/mm/yy" showAnim="slideDown" duration="fast" id="invoiceDate" name="invoiceDate"  disabled="%{#disable2}" value='%{#comLst.size()>0 ? #comLst[0].INVOICE_DATE : ""}' theme="simple" /> --%>
				    	</div>
				    </div>
			    </div>
				<div class="row">
					<div class="col-md-4">
				    	<div class="form-group">
				    		<s:text name="Supplier Name" />
				    		<s:textfield name="supplierName" cssClass="form-control" maxlength="50" disabled="%{#disable2 || #disable}"  value='%{#comLst.size()>0 ? #comLst[0].SUPPLIER_NAME: ""}' />
				    	</div>
				    </div>
				    <div class="col-md-4">
						<s:if test='productId==11'>
				    		<div class="form-group" id="ajaxFragile">
				    			<s:text name="commodity.fragile" /><br/>
				    			<s:radio name="fragile[0]" list="#{true:'Yes',false:'No'}" disabled="#disable" value='%{#comLst.size()>0 ? #comLst[0].FRAGILE_SELECTED: false}'/>
				    		</div>
				    	</s:if>
				    	<s:else>
				    		<div class="form-group">
				    			<s:text name="commodity.fragile" /><br/>
				    			<s:radio name="fragile[0]" list="#{true:'Yes',false:'No'}" disabled="#disable" value='%{#comLst.size()>0 ? #comLst[0].FRAGILE_SELECTED: false}'/>
				    		</div>
				    	</s:else>
				    </div>
				</div>
				<br/>
				<div class="row" align="center">
					<a onclick="disableForm(document.commoditySelection,false,'');" class="btn btn-danger btn-oval" style="cursor:pointer" data-dismiss="modal">Close</a>
					<a onclick="disableForm(document.commoditySelection,false,'');fnsubmit('submit','');" class="btn btn-success btn-oval" style="cursor:pointer" >Submit</a>
					<s:if test='%{(applicationNo !=null && applicationNo.length() > 0) && (viewList.get(0).get("COMMODITY_CODE")!=null && !"".equals(viewList.get(0).get("COMMODITY_CODE"))) && !((quoteStatus=="RA" ||(endTypeId!=null && endTypeId.length() > 0)))}'>
						<a onclick="disableForm(document.commoditySelection,false,'');fnsubmit('delete',<s:property value="viewList.get(0).get('COMMODITY_CODE')"/>);" class="btn btn-warning btn-oval" style="cursor:pointer" >Delete</a> 
					</s:if>
				</div>
				<br/>
			</div>
				<s:hidden name="applicationNo" />
				<s:hidden name="refNo" />
				<s:hidden name="status2" id="status2"/>
				<s:hidden name="endTypeId" />
				<s:hidden name="quoteStatus" />
				<s:hidden name="originCountry" />
				<s:hidden name="destCountry" />
				<s:hidden name="commSelTotalCommodity" />
				<s:hidden name="commSelTotalSI" />
				<s:hidden name="commSelcommodity" />
				
		</s:form>
	</div>
</body> 
<SCRIPT type="text/javascript">
if(document.quotation.endTypeId.value!=''){
	try{
		enableForm(document.commoditySelection,false,'<s:property value="%{fields}"/>');
	}catch(err){console.log(err);}
 }
function removeComma(object){
try{
   replaceComma(document.commoditySelection,'insuredValue');
   document.commoditySelection.submit();
 }catch(err){console.log(err);}
}
function fngetCommodityList(val, id){
	postRequest('${pageContext.request.contextPath}/'+id+'Quotation.action?category='+val, id);
}
<s:if test='(applicationNo !=null && applicationNo.length() > 0) && !("delete".equals(status2)||"else".equals(status2)) && viewList.get(0).get("COMMODITY_CODE")!=null && !"".equals(viewList.get(0).get("COMMODITY_CODE"))' >
		fnsubmit('edit','<s:property value="viewList.get(0).get('COMMODITY_CODE')"/>'); 
</s:if>

$(document) .ready(function(){
	$('#invoiceDate') .datepicker({
		dateFormat : 'dd/mm/yy'
	});
});
</SCRIPT>
</html>