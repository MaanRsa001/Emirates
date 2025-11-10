branch<%@ page isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<script language="JavaScript">javascript:window.history.forward(1);</script>
        <link href="${pageContext.request.contextPath}/css/displaytag.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/css/tcal.css" rel="stylesheet" type="text/css" />
		<script>
			function stopRKey(evt) { 
			  var evt = (evt) ? evt : ((event) ? event : null);
			  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
			  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;}
			}
			document.onkeypress = stopRKey;
		</script>
	</head>
	<body>
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0"> 
			<tr>
		    	<td height="5"></td>
		  	</tr>
		  	<tr>
		    	<td><table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#E5E5E5">
		      		<tr>
		        		<td bgcolor="#FFFFFF" style="padding:10px; background:#F8F8F8">
								<s:form name="form1" theme="simple">
							 		<table width="100%" align="center">
								        <tr>
								            <td class="heading" width="100%"><s:text name="label.menu.selection"/></td>
								        </tr>
								 		<tr>
								        	<td height="10%">
								        		&nbsp;
								        	</td>
							        	</tr>
							      		<tr>
							      			<td>
										      	<display:table name="menuList" requestURI="menuSelectionAdmin.action" pagesize="" class="table" uid="row" id="record">
													<display:setProperty name="paging.banner.one_item_found" value="" />
													<display:setProperty name="paging.banner.one_items_found" value="" />
													<display:setProperty name="paging.banner.all_items_found" value="" />
													<display:setProperty name="paging.banner.some_items_found" value="" />
													<display:setProperty name="paging.banner.placement" value="bottom" />
													<display:setProperty name="paging.banner.onepage" value="" /> 
													
													<display:column headerClass="sortable" sortable="true" style="text-align:center;" title="<input type='checkbox'  onclick='SelectAll()' id='checkall' />">
														 <input type="checkbox"  value="${record.CATEGORY_DETAIL_ID}" id="checkbox${record.CATEGORY_DETAIL_ID}" />
													</display:column>
													<display:column headerClass="sortable" sortable="true" style="text-align:left;" title="Menu Name" property="DETAIL_NAME" />
												</display:table>
											</td>
										</tr>
										<tr align="center"><td class="text"><input type="button" onclick="fnsubmit()"  class="btn" value="Submit"/>&nbsp;<input name="back" type="button" class="btn" onclick="javascript:window.close()" value="Back" style ="cursor:hand"/></td></tr>
									</table>
								</s:form>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	
		<script>
		 	if(window.opener.adminlist.mid.value.length>=1){
				var val=window.opener.adminlist.mid.value;
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
				try
				{
					var elements=document.forms[0].elements;
					for(i=1; i<elements.length; i++)
					{
						obj=elements[i];
						if(obj.type=='checkbox' && obj.checked)
							checkedItems+=obj.value+',';
					}
				}catch(e){}
				if(checkedItems.length>1)
					checkedItems=checkedItems.substring(0, checkedItems.length-1);
				window.opener.adminlist.mid.value=checkedItems;
				window.close();
			}
		</script>
	</body>
</html>


