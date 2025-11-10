<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page isELIgnored="false" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<%@ taglib uri="/struts-jquery-tags" prefix="sj"%>
<html>
	<head>
		<sj:head jqueryui="true" jquerytheme="start" />		
		<link href="<%=request.getContextPath()%>/cssbootstrap/footable-0.1.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
			function stopRKey(evt) { 
			  var evt = (evt) ? evt : ((event) ? event : null); 
			  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
			  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
			} 
			document.onkeypress = stopRKey;
		
			function fnCall(from){
				if(from=='edit')
					document.underwriter.action ="viewUnderwriterMgm.action?mode=edit";
				else if(from=='list')
					document.underwriter.action = "UnderwriterCreationContoller.action";
				else if(from=='changePwd')
					document.underwriter.action = "changePassUnderwriterMgm.action?mode=changePass";	
				else if(from=='include')
					document.underwriter.action = "includeIssuerUnderwriterMgm.action?type1=include";
				else if(from=='exclude')
					document.underwriter.action = "excludeIssuerUnderwriterMgm.action?type1=exclude";
				else if(from=='openCover')
					document.info.action = "opencoverOC.action";
				else if(from=='statistics')
					document.info.action = "statisticsRE.action";
				<%--alert(from);
					postRequest('${pageContext.request.contextPath}/getABListCustomerMgm.action?agencyCode=10016&borganization='+'<s:property value="borganization"/>'+'&bcode='+'<s:hidden name="bcode"/>'+'&mode=mainTab', 'mainTab');
				}--%>else
					document.underwriter.action = from+"BrokerMgm.action";
				document.underwriter.submit();
			}
		</script>
		<style type="text/css">
		th.sortable a {
			background-color: #337ab7;
		}
		</style>
	</head>
	<body>
    	<div style="margin:10px 0;"></div>
    	<div class="easyui-layout" style="height:100vh;">
	      	<div data-options="region:'west',split:true" title="Options" style="width:150px;">
            <div class="easyui-accordion" data-options="fit:true,border:false">
                <input type="button" class="btntab" value="Underwriter Details" onclick="fnCall('list')"/><br/>
               <%-- <input type="button" class="btntab" value="Edit" onclick="fnCall('edit')"/><br/> --%>
                <input type="button" class="btntab" value="Change Password" onclick="fnCall('changePwd')"/><br/>
                <input type="button" class="btntab" value="Included Brokers" onclick="fnCall('exclude')"/><br/>
                <input type="button" class="btntab" value="Excluded Brokers" onclick="fnCall('include')"/><br/>
            </div>
        </div>
	       <s:if test='%{borganization!=null && borganization!=""}'>
        		<div data-options="region:'center',title:'<s:property value="borganization"/>(<s:property value="agencyCode"/>)',iconCls:'icon-ok'">
        	</s:if>
		    <s:else>
		    	<div data-options="region:'center',title:'<s:property value="loginId"/>',iconCls:'icon-ok'">
		    </s:else>
            <div class="easyui-tabs" data-options="fit:true,border:false,plain:true" id="mainTab">
                <div title="Issuer Info" style="padding:5px">
                	<table width="100%">
                    	<tr>
							<td height="5"></td>
						</tr>
						<tr>
							<td>
								<s:form id="info" name="underwriter" method="post" action="updateExcludeUnderwriterMgm" theme="simple">
									<table width="100%"  border="0" cellspacing="0" cellpadding="0">
										<tr> 
											<td align="center" ></td> 
										</tr> 
										<tr> 
											<td width="8" height="25" class="heading" bgcolor="#FCC721">&nbsp;</td>
											<td width="1"></td> 
											<td width="100%" class="heading"><strong>EXCLUDED BROKER</strong></td>
										</tr> 
										<tr align="center"> 
											<td colspan="3">&nbsp;</td> 
										</tr> 
										<tr align="center"> <td colspan="3">
											<table width="100%"  border="0" cellspacing="0" cellpadding="0"></table>
										<tr> 
										<td >
										<td  style="color:red;" colspan="3"><s:actionerror/></td>	
										<table width="100%" border="0" align="center" cellspacing='1' cellpadding="1">
										<td bgcolor="#FFFFFF" style="padding:10px; background:#F8F8F8" align="center" width="100%">
																		<div id="underwriterInfo">
																		<input name="type[0]" type="hidden" value="hi"/>
																		<display:table name="includeIssuer" pagesize="20" requestURI="/includeIssuerUnderwriterMgm.action" class="footable" uid="row" id="record">
																			<s:set var="myrow" value="#attr.record"/>
																			<display:setProperty name="paging.banner.one_item_found" value="" />
																			<display:setProperty name="paging.banner.one_items_found" value="" />
																			<display:setProperty name="paging.banner.all_items_found" value="" />
																			<display:setProperty name="paging.banner.some_items_found" value="" />
																			<display:setProperty name="paging.banner.placement"	value="bottom" />
																			<display:setProperty name="paging.banner.onepage" value="" />
																			<display:column sortable="true" style="text-align:left;font-size:13px;" title="Select">
																			<s:checkboxlist name="status[%{#attr.record_rowNum-1}]" list="#{'exclude':''}" />
																			<s:hidden name="brokerCode[%{#attr.record_rowNum-1}]" id="brokerCode[#attr.record_rowNum-1]" value="%{#myrow.AGENCY_CODE}"/>
																	        </display:column>
																			<display:column sortable="true" style="text-align:left;font-size:13px;" title="Broker Code" property="AGENCY_CODE"/>
																			<display:column sortable="true" style="text-align:left;font-size:13px;" title="Broker Name" property="COMPANY_NAME" />
																			<display:column sortable="true" style="text-align:left;font-size:13px;" title="Products">
																			 	<s:checkboxlist name="productId[%{#attr.record_rowNum-1}]" list="productList" listKey="PRODUCT_ID" listValue="PRODUCT_NAME"/>
																			</display:column>
																	 	</display:table>
																		</div>
																		<input name='type[<s:property value="%{underwriterInfo.size+1}"/>]' type="hidden" value="oi"/>
																		<s:hidden name="loginId" />
																		<br/><s:submit type="button" name="Submit"/>
																	</td>
										</table>
						            </td>
						    </tr>
					</table>
					</s:form>
					</td>
					
					</tr>
					</table>
		</body>
		
</html>