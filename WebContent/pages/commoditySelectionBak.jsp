<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>Category Of Goods Information</title>
       	<link href="${pageContext.request.contextPath}/css/displaytag.css" rel="stylesheet" type="text/css">
        <meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page"> 
		<link href="css/styles.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="js/common.js"></script>
    </head>

<body >
<s:form name="commoditySelection" theme="simple" action="addCommodityQuotation.action" disabled="true">
	<s:set var="disable" value="%{quoteStatus=='RA'||(endTypeId!=null && endTypeId.length() > 0)}"/>
	<s:set var="disable2" value="%{endTypeId!=null && endTypeId.length() > 0}"/>
	<table width="100%" border="0" cellspacing="0" cellpadding="2" align="center" bgcolor="#FFFFFF">
		<tr>
          <td  style="color:red;"><s:actionerror/></td>
        </tr>
		<tr>
          <td class="heading" ><s:label key="commodity.commodityInfo" /></td>
        </tr>
		<tr>
		    <td class="bg" >
		    <div style="width: 98%">
		    <table id="record" style="width:100%; align:center;" cellpadding="1" class="table" cellspacing="1" border="1">
				<thead>
			        <tr class="bg">
						<td width="55px"><s:label key="commodity.select" /></td>
						<td width="183px"><s:label key="commodity.desc" /></td>
						<td width="133px"><s:label key="commodity.insuredValue" /></td>
						<td width="183px"><s:label key="commodity.supplierName" /></td>
						<td width="183px"><s:label key="commodity.commodityPackDesc" /></td>
						<td width="133px"><s:label key="commodity.invoiceNo" /></td>
						<td width="55px"><s:label key="commodity.fragile" /></td>
					</tr>
				</thead>
			</table>
			</div>
		    <div style="height:450px;overflow-x:hidden;overflow-y:scroll;">    	     	   	     	     
				<table id="record" style="width:100%; align:center;" cellpadding="1" class="table" cellspacing="1" border="1">
					<tbody>
						<s:iterator value="commodityList" status="status" var="commodityList">
							<tr class="bg">
								<td width="55px" align="center"><s:checkbox  name="selectCommodity" value="%{#commodityList.SELECTED}" fieldValue="%{#commodityList.COMMODITY_ID}" disabled="#disable"/><s:hidden  name="commodityId" value="%{#commodityList.COMMODITY_ID}"/></td>
								<td ><s:textarea  name="commodityDesc" value="%{#commodityList.COMMODITY_NAME}" onkeyup="textLimit(this,480)" disabled="%{#session.user1==getText('admin')?'false':#disable}" rows="3" cssStyle="width: 180px;" /></td>
								<td ><s:textfield  name="insuredValue" value="%{#commodityList.SUM_INSURED}" cssStyle="width:125px" maxLength="12" onblur="validamt(this);" onkeyup="checkNumbers(this);" disabled="#disable"/></td>
								<td ><s:textarea  name="supplierName" value="%{#commodityList.SUPPLIER_NAME}" onkeyup="textLimit(this,50)" disabled="#disable2" rows="3" cssStyle="width: 180px;" /></td>
								<td ><s:textarea  name="commodityPackDesc" value="%{#commodityList.PACKAGE_DESC}" onkeyup="textLimit(this,50)" disabled="#disable2" rows="3" cssStyle="width: 180px;" /></td>
								<td ><s:textfield  name="invoiceNo" value="%{#commodityList.INVOICE_NUMBER}" cssStyle="width:125px"  maxLength="40" disabled="#disable2"/></td>
								<td width="55px" align="center"><s:checkbox name="fragile[%{#status.index}]" value="%{#commodityList.FRAGILE_SELECTED}" disabled="#disable"/></td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			 </div>						
		     </td>
		</tr>	
		<tr align="center">
			<td class="text" >
				<s:hidden name="applicationNo" />
				<s:hidden name="refNo" />
				<s:hidden name="endTypeId" />
				<s:submit type="button" name="close"  key="commodity.close" cssClass="btn" onclick="window.close();return false;"/>&nbsp;
				<s:submit type="submit" name="submit" key="commodity.submit" onclick="disableForm(this.form,false,'');fnsubmit();" cssClass="btn"/>
			</td>
		</tr>
	</table>
	</s:form>
	</body> 
<SCRIPT type="text/javascript">
if(window.opener.quotation.endTypeId.value!=''){
		enableForm(document.commoditySelection,false,'<s:property value="%{fields}"/>');
	}
function fnsubmit()
{
	try{
		var checkedItems=0;
		var totalSI=0;
		var commodity='';
		var elements=document.commoditySelection.selectCommodity;
		var elementsSI=document.commoditySelection.insuredValue;
		var commodityDesc=document.commoditySelection.commodityDesc
        }catch(e){alert('a   '+e)}
		if(elements.type=='checkbox' && elements.checked) {
                    try{
			checkedItems++;
			var obj=elements;
			var si=elementsSI;
			var name=commodityDesc;
			if(si.type=='text' && si.value!='' && !isNaN(si.value))
			{
				totalSI+=parseFloat(si.value);
			}
			commodity+=name.value+',';
                    }catch(e){}
		}else{
                     try{
			for(i=0; i<elements.length; i++){
				var obj=elements[i];
				if(obj.type=='checkbox' && obj.checked){
					checkedItems++;
					var si=elementsSI[i];
					var name=commodityDesc[i];
					if(si.type=='text' && si.value!='' && !isNaN(si.value)){
						totalSI+=parseFloat(si.value);
					}
					commodity+=name.value+',';
				}
			}
                    }catch(e){}
		}
        try{
		    if(commodity.length>1){
				commodity=commodity.substring(0, commodity.length-1);
		    	window.opener.document.getElementById("commodity").value=commodity;
		   		window.opener.document.getElementById("totalCommodity").value=checkedItems;
		   		window.opener.document.getElementById("totalSI").value=totalSI;
		   	}
        }catch(e){}
}
</SCRIPT>
</html>
