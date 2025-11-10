<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
        <link href="${pageContext.request.contextPath}/css/claim.css" rel="stylesheet" type="text/css">
       	<link href="${pageContext.request.contextPath}/css/displaytag.css" rel="stylesheet" type="text/css">
       	<link href="css/styles.css" rel="stylesheet" type="text/css" />
		<link href="css/tcal.css" rel="stylesheet" type="text/css" />
 	</head>

	<body>
		<s:form name="ocSelect" theme="simple" >
			<table width="55%" border="0" cellspacing="0" cellpadding="2" align="center" class="data-table-border" style="table-layout: fixed">
				<tr>
	 				<td class="formtxtc" style="text-align: center;">
						 <display:table name="openCoverList" pagesize="" requestURI="" class="table" uid="row" id="record">
							<display:setProperty name="paging.banner.one_item_found" value="" />
							<display:setProperty name="paging.banner.one_items_found" value="" />
							<display:setProperty name="paging.banner.all_items_found" value="" />
							<display:setProperty name="paging.banner.some_items_found" value="" />
							<display:setProperty name="paging.banner.placement" value="bottom" />
							<display:setProperty name="paging.banner.onepage" value="" />
							<display:column sortable="true" style="text-align:center;" title="<input type='checkbox'  onclick='SelectAll()' id='checkall' />">
								<input type="checkbox"  value="${record.MISSIPPI_OPENCOVER_NO}" id="checkbox${record.MISSIPPI_OPENCOVER_NO}" />
							</display:column>
							<display:column   sortable="true" style="text-align:left;" title="OpenCover No" property="MISSIPPI_OPENCOVER_NO" />
							<display:column   sortable="true" style="text-align:left;" title="Customer Name" property="cust_name" />
						</display:table>	
					</td>
				</tr>
				<tr align="center"><td class="text"><input type="button" onClick="fnsubmit()"  Class="btn" value="Submit"/>&nbsp;<input type="button"  Class="btn" onClick="javascript:window.close()" value="Back" style="cursor:hand"></td></tr>
			</table>
		</s:form>
	</body> 
	<script type="text/javascript">
		 if(window.opener.deposit.ocNo.value.length>=1){
				var val=window.opener.deposit.ocNo.value;
				var elements=val.split(',');
				
				for(i=0; i<elements.length; i++){
					document.getElementById('checkbox'+elements[i]).checked=true;
				}
			
		}
		function SelectAll(){
			try{
				var elements=document.forms[0].elements;
				if(document.getElementById('checkall').checked){
		 			for(i=0;i<elements.length;i++){
		   				obj=elements[i];
		    			if(obj.type=='checkbox'){
		      				obj.checked=true;
		    			}
		 			}
		 		}else{
 					for(i=0;i<elements.length;i++){
    					obj=elements[i];
   						if(obj.type=='checkbox'){
      						obj.checked=false;
    					}
 					}
 				}
			}catch(e){}
		}
		
		function fnsubmit(){
			var checkedItems='';
			try{
				var elements=document.forms[0].elements;
				for(i=1; i<elements.length; i++){
					obj=elements[i];
					if(obj.type=='checkbox' && obj.checked)
						checkedItems+=obj.value+',';
				}
			}catch(e){}
			if(checkedItems.length>1)
				checkedItems=checkedItems.substring(0, checkedItems.length-1);
			window.opener.deposit.ocNo.value=checkedItems;
			window.close();
		}
	</script>
</html>


