<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
        <meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page"> 
		<link href="css/styles.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="js/common.js"></script>
    </head>

<body >
<s:form name="commoditySelection" theme="simple">
	<table width="100%" border="0" cellspacing="0" cellpadding="2" align="center" bgcolor="#FFFFFF">
		<tr>
          <td  style="color:red;"><s:actionerror/></td>
        </tr>
		<tr>
          <td class="heading" ><s:text name="Commodity List" /></td>
        </tr>
		<tr>
		    <td class="bg" >
		    <div style="width: 98%">
		    <table id="record" style="width:100%; align:center;" cellpadding="1" class="table" cellspacing="1" border="1">
				<thead>
			        <tr class="bg">
						<td width="55px"><s:text name="S.No" /></td>
						<td width="183px"><s:text name="Commodity Description" /></td>
					</tr>
				</thead>
			</table>
			</div> 	     	   	     	     
				<table id="record" style="width:100%; align:center;" cellpadding="1" class="table" cellspacing="1" border="1">
					<tbody>
						<s:iterator value="commodityList" status="status" var="commodityList">
							<tr class="bg">
								<td width="55px" align="center"><s:checkbox  name="selectCommodity" value="%{#commodityList.SELECTED}" fieldValue="%{#commodityList.COMMODITY_NAME_DESC}" disabled="#disable" cssStyle="width:40px;"/><s:hidden  name="commodityId" value="%{#commodityList.COMMODITY_NAME_DESC}"/></td>
								<td width="183px"><s:hidden name="commodityDesc" value="%{#commodityList.COMMODITY_NAME_DESC}"/><s:property value="%{#commodityList.COMMODITY_NAME_DESC}"/></td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
		     </td>
		</tr>	
		<tr align="center">
			<td class="text" >
				<s:submit type="button" name="close"  value="Close" cssClass="btn" onclick="window.close();return false;"/>&nbsp;
				<input type="button" name="submit" value="Submit" onclick="fnsubmit();" class="btn"/>
			</td>
		</tr>
	</table>
	</s:form>
	</body> 
		<SCRIPT type="text/javascript">
function fnsubmit()
{
	try
	{
		var checkedItems=0
		var commodity='';
		var elements=document.commoditySelection.selectCommodity;
		var commodityDesc=document.commoditySelection.commodityDesc;
		if(elements.type=='checkbox' && elements.checked){
			checkedItems++;
			var name=commodityDesc;
			commodity+=name.value+',';
		}else{
			for(i=0; i<elements.length; i++){
				var obj=elements[i];
				if(obj.type=='checkbox' && obj.checked){
					checkedItems++;
					var name=commodityDesc[i];
					commodity+=name.value+',';
				}
			}
		}
		if(commodity.length>1){
			commodity=commodity.substring(0, commodity.length-1);
			window.opener.document.getElementById("ucommodityDesc<s:property value='rowNum'/>").value=commodity;
			window.close();
		}else{
			alert('Please Select atlease one ccommodity');
		}
	}catch(e){}
}
	</SCRIPT>
</html>
