<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<%@ page isELIgnored="false" %>
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<script>
			function getTransport(val, id){
				if(val.length>=2){
					var searchBy=document.getElementById("searchBy").value;
					
					postRequest('${pageContext.request.contextPath}/getTransportListAjaxRatingEngine.action?reqFrom='+id+'&searchBy='+searchBy+'&searchValue='+val, id);
				}
			}
		</script>
		<script type="text/javascript" src="pages/admin/ratingEngine/menu.js">
					</script><script language="JavaScript">
		function stopRKey(evt) { 
		  var evt = (evt) ? evt : ((event) ? event : null); 
		  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
		  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
		} 
		document.onkeypress = stopRKey; 
	</script>
	</head>
	<body>
    <div style="margin:10px 0;"></div>
    <div class="easyui-layout" style="width:900px;height:1000px;">
       <div data-options="region:'west',split:true" title="Options" style="width:150px;">
            <div class="easyui-accordion" data-options="fit:true,border:false">
                 <%@ include file="/pages/admin/ratingEngine/ratingEngineMenu.jsp" %>
            </div>
        </div>
        <div data-options="region:'center',title:'',iconCls:''">
            <div class="easyui-tabs" data-options="fit:true,border:false,plain:true" id="mainTab">
                <div title="Mode of Transport" style="padding:5px">
                <s:form id="info" name="info" method="post" theme="simple" validate="false">
                	<table width="100%">
                          <tr>
						    <td height="5"></td>
						  </tr>
						  <tr>
						    <td><table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#E5E5E5">
						      <tr>
						        <td bgcolor="#FFFFFF" style="padding:10px; background:#F8F8F8">
						        
						            <table width="100%" border="0" cellspacing="0" cellpadding="0">
						            	<tr>
							       	   		<td  style="color:red;"><s:actionmessage/></td>
							       		</tr>
										<tr> <td>&nbsp;&nbsp;</td> </tr>
										<tr><td class="heading"><s:text name="rating.mode.transport"/></td></tr>
										<tr><td>&nbsp;</td></tr>
										<tr align="center">
												 <td bgcolor="#FFFFFF">
												 <table width="100%" border="0" cellspacing="0" cellpadding="0">
														
														<tr>
														  <td class="bg">
														  <table width="100%" border="0" align="center" cellpadding="4" cellspacing="0">
															
															
															
															<tr>
							                                   <td> Search By:<s:select name="searchBy" id="searchBy" list="#{'name':'Mode of Description'}" headerKey="" headerValue="-Select-"/>
							                                   <s:textfield name="searchVal" onkeyup="getTransport(this.value,'transportLists')"/></td>
							                                   <td align="right">
								                               <a class="two" title="Add Transport" href="editTransportRatingEngine.action?mode=add"><s:text name="add.new.transport"/></a>
							                                  </td>
						                                    </tr>
														 </table></td>
														 </tr>
														</table>
													  </td>
													  
													</tr>
													<tr align="center">
					                                <td bgcolor="#FFFFFF" style="padding:10px; background:#F8F8F8" align="center" width="100%">
						                           <div id="transportLists">
						                            <display:table name="transportList" pagesize="10" requestURI="/getTListRatingEngine.action" class="table" uid="row" id="record">
								                    <display:setProperty name="paging.banner.one_item_found" value="" />
								                    <display:setProperty name="paging.banner.one_items_found" value="" />
								                    <display:setProperty name="paging.banner.all_items_found" value="" />
								                    <display:setProperty name="paging.banner.some_items_found" value="" />
								                    <display:setProperty name="paging.banner.placement"	value="bottom" />
								                    <display:setProperty name="paging.banner.onepage" value="" />
								                    
								
								                    <display:column sortable="true" style="text-align:left;font-size:13px;"  title="TRANSPORT ID" property="MODE_TRANSPORT_ID" />
								                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="MODE OF TRANSPORT" property="TRANSPORT_DESCRIPTION" />
								                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="CORE APP CODE" property="RSACODE" />
								                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="EDIT">
									                <a href="editTransportRatingEngine.action?transID=${record.MODE_TRANSPORT_ID}">Edit</a>
								                    </display:column>
								                    <display:column sortable="true" style="text-align:left;font-size:13px;" property="STATUS"/>
						                            </display:table>
						                          </div>
					                               </td>
				                                   </tr>
										</table>
										</tr>
									</table>
    								<s:hidden name="agencyCode"/>
									<s:hidden name="login_Id"/>
									<s:hidden name="borganization"/>
									<s:hidden name="bcode" />
								<s:hidden name="branchCode" id="branchCode"/>								
								</td>
								</tr>
								</table>
                    </s:form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>

