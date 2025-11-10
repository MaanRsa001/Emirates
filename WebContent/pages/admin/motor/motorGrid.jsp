<%@ page isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html <s:if test="locale.language == 'ar'">dir="rtl"</s:if> >
	<head>
	<script type="text/javascript" src="pages/admin/ratingEngine/menu.js"></script>
		<sj:head jqueryui="true" jquerytheme="start" />
		
        <script>
        function formatarea(cellvalue) {
			var ss='${pageContext.request.contextPath}/editareaMotorAdmin.action?mode=edit&areaID='+cellvalue;
			return "<a href=\'"+ss+"\'>Edit</a>";
		}
		function formatmotor(cellvalue) {
			var ss='${pageContext.request.contextPath}/editareaMotorAdmin.action?mode=edit&areaID='+cellvalue;
			return "<a href=\'"+ss+"\'>Edit</a>";
		}	
		
		function reloadGridScheme() { 
            $.publish('reloadScheme');
        } 
        
		function funcedit(response, postdata){
   			var success = true;
   			var message = ""; 
   			if(response.responseText==''){
            	reloadGridScheme();
            	success = false;
            	message="success";
            }else{
	            var json = eval('(' + response.responseText + ')');
	            if(json.actionErrors){
	            	success = false;
	                for(i=0; i < json.actionErrors.length; i++){
	                	message += json.actionErrors[i] + '<br/>'; 
	                }
	            }
			}
			 return [success,message];
         } 
         $(function(){
    		$("#transID").change(function(e){
            var param = $(this).val(); 
            alert(param); 

         	});  
 		}); 
  
</script>
	</head>
	<body>
	<div style="margin:10px 0;"></div>
    <div class="easyui-layout" style="width:900px;height:500px;">
       <div data-options="region:'west',split:true" title="Options" style="width:150px;">
            <div class="easyui-accordion" data-options="fit:true,border:false">
                <%@ include file="/pages/admin/motor/motormenu.jsp" %>
            </div>
        </div>
        <div data-options="region:'center',title:'',iconCls:''">
            <div class="easyui-tabs" data-options="fit:true,border:false,plain:true" id="mainTab">
                <div title="${menuType}" style="padding:5px">
		<table width="px;" border="0" cellpadding="0" cellspacing="0">
		  	<tr>
		    	<td>
		    		<table width="80%" border="0" cellpadding="0" cellspacing="1" bgcolor="#E5E5E5">
		      			<tr>
		        			<td bgcolor="#FFFFFF" style="padding:10px; background:#F8F8F8">
		        				<table width="100%">
		        					<tr><td  style="color:red;"><s:actionmessage/></td></tr>
							       	<tr><td height="15px" align="right"><a href="edit${menuType}MotorAdmin.action?mode=add">Add New</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							 		</td>					 		
							 		</tr>
							 		<tr><td bgcolor="#ffffff">
								 		<table width="50%" border="0" cellpadding="4" cellspacing="0">
									        <tr><td width="5%"></td>
									        	<td width="50%">
									        		<s:url id="editurl" action="editWsrccRating" />
												    <sjg:grid
												        id="motorid" caption="%{menuType}" href="%{menuType}MotorAdminjson.action?menuType=%{menuType}" pager="true" dataType="json" gridModel="gridList" reloadTopics="reloadScheme"
												        rowList="10,15,20" rownumbers="true" width="700" navigator="true" navigatorView="false" navigatorDelete="false"
												        navigatorAdd="false" navigatorEdit="false" navigatorSearch="true" navigatorRefresh="true" viewrecords="true" editurl="%{editurl}"
												        navigatorEditOptions="{width:450,height:350, reloadAfterSubmit:true, afterSubmit:funcedit}" 
												        navigatorAddOptions="{width:450,height:350, reloadAfterSubmit:true, afterSubmit:funcedit}"
												        navigatorDeleteOptions="{width:450,height:350, reloadAfterSubmit:true, afterSubmit:funcedit}"
												    >
												    <s:if test = 'menuType=="area"'>
												        <sjg:gridColumn name="areaID" index="areaID" title="AREA COVERAGE ID" sortable="false" search="false" editable="true" editrules="{required: false}" onkeyup="false" width="50"/>
												        <sjg:gridColumn name="areaCode" index="areaCode" title="AREA COVER CODE" search="true" searchoptions="{sopt:['cn','eq']}" editable="true" editrules="{required: true, edithidden: true }" width="100"/>
												        <sjg:gridColumn name="areaDesc" index="areaDesc" title="AREA COVER DESCRIPTION" search="true" searchoptions="{sopt:['cn','eq']}"  editable="true" editrules="{required: true, edithidden: true }" width="100"/>
												        <sjg:gridColumn name="arabic" index="arabic" title="ARABIC DESCRIPTION" search="false" editable="true" editrules="{required: true,edithidden: true }" width="80"/>
												        <sjg:gridColumn name="status" index="status" title="STATUS" sortable="true" search="false" editable="true" editrules="{required: true}" edittype="select" editoptions="{value:'Y:Active;N:DeActive'}" width="40"/>
												        <sjg:gridColumn name="edit" title="EDIT" align="center" formatter="formatarea" search="false" width="40"/>
												    </s:if>
												    <s:elseif test = 'menuType=="motorbody"'>
												        <sjg:gridColumn name="typeID" index="typeID" title="TYPE ID" sortable="true" search="true" editable="true" editrules="{required: false}" onkeyup="false" width="50"/>
												        <sjg:gridColumn name="bodyName" index="bodyName" title="BODY NAME" search="true" searchoptions="{sopt:['cn','eq']}" editable="true" editrules="{required: true, edithidden: true }" width="100"/>
												        <sjg:gridColumn name="noYears" index="noYears" title="NO OF YEARS ALLOWED" editable="true" search="false" searchoptions="{sopt:['cn','eq']}" editrules="{required: true,edithidden: true }" width="50"/>
												        <sjg:gridColumn name="thirdParty" index="thirdParty" title="THIRD PARTY LIABLITY" sortable="true" search="false" editable="true" editrules="{required: true}" width="50"/>
												        <sjg:gridColumn name="edit" title="EDIT" align="center" formatter="formatmotor" search="false" width="40"/>
												    </s:elseif>
												</sjg:grid>
									        	</td>
									 			<td width="5%"></td>
							        		</tr>
							        	</table>
							    	</td></tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		   </div>
            </div>
        </div>
    </div>
    <s:hidden name="transID" id="transID"/>
    <s:hidden name="coverID" id="coverID"/>
    <s:hidden name="menuType" id="menuType"/>
    <s:form name="info" id="info">
    </s:form>
    </body>
</html>