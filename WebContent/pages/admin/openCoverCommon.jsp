<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<%@ page isELIgnored="false"%>
<html>
	<head>
       	  <link href="css/styles.css" rel="stylesheet" type="text/css" />
		  <link href="css/tcal.css" rel="stylesheet" type="text/css" />
		  <link href="css/ddlevelsmenu-topbar.css" rel="stylesheet" type="text/css" />
		  <link href="css/ddlevelsmenu-base.css" rel="stylesheet" type="text/css" />
		  <link href="css/displaytag.css" rel="stylesheet" type="text/css">
          <script type="text/javascript" src="js/tcal.js"></script>
		  <script type="text/javascript" src="js/common.js"></script>
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
		<s:form name="form1" theme="simple">
		<table width="100%">
			<tr>
				<td>
					<display:table name="occList" pagesize="" requestURI="" class="table" uid="row" id="record">
						<display:setProperty name="paging.banner.one_item_found" value="" />
						<display:setProperty name="paging.banner.one_items_found" value="" />
						<display:setProperty name="paging.banner.all_items_found" value="" />
						<display:setProperty name="paging.banner.some_items_found" value="" />
						<display:setProperty name="paging.banner.placement"	value="bottom" />
						<display:setProperty name="paging.banner.onepage" value="" />
						<display:column sortable="true" style="text-align:left;font-size:13px;;" title=" S.No " value="${record_rowNum}"/>
						<display:column sortable="true" style="text-align:center;" title="<input type='checkbox'  onclick='SelectAll()' id='checkall' />">
							 <input type="checkbox"  value="${record.open_cover_no}" id="checkbox${record.open_cover_no}"/>
						 </display:column>
						<display:column sortable="true" style="text-align:left;"  title='OPEN COVER NO' property="open_cover_no"/>
						<display:column sortable="true" style="text-align:left;"  title='CUSTOMER NAME' property="name" />
						<display:column sortable="true" style="text-align:left;" title='MISSIPPI OPENCOVER NO' property="MISSIPPI_OPENCOVER_NO"/>
					</display:table>
				</td>
			</tr>
			<tr><td align="center"> <input type="button" name="back" value="Back" class="btn" onclick="fnclose()"/>&nbsp;&nbsp;&nbsp;
									<input type="button" name="back1" value="Submit" class="btn" onclick="fnsubmit()"/>
			</td></tr>
		</table>
		</s:form>
	</body>
	<script>
	if(window.opener.document.form1.openCover.value.length>=1)
 	{
  		var val=window.opener.document.form1.openCover.value;	
		var elements=val.split(',');
		for(i=0; i<elements.length; i++){
			document.getElementById('checkbox'+elements[i]).checked=true;
		}
	} 
	function SelectAll()
	{
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
		window.opener.document.form1.openCover.value=checkedItems;
		window.close();
	}
	function fnclose(){
		window.close();
	}

</script>
</form>