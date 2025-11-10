<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<%@ page isELIgnored="false"%>
<html>
	<head>
	</head>
	<body>
    <div style="margin:10px 0;"></div>
    <div class="easyui-layout" style="width:900px;height:1000px;">
       <div data-options="region:'west',split:true" title="Options" style="width:150px;">
            <div class="easyui-accordion" style="overflow-y: scroll" data-options="fit:true,border:false" >
                 <%@ include file="/pages/admin/ratingEngine/ratingEngineMenu.jsp" %>
            </div>
        </div>
        <div data-options="region:'center',title:'',iconCls:''">
            <div class="easyui-tabs" data-options="fit:true,border:false,plain:true" id="mainTab">
                <div title="Base Rate" style="padding:5px">
                <s:form id="info" name="info" method="post" action="baserateCalculateRating" theme="simple">
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
							       	   		<td  style="color:red;"><s:actionerror/></td>
							       		</tr>
										<tr> <td>&nbsp;&nbsp;</td> </tr>
										<tr><td class="heading"><s:text name="base.rate"/></td></tr>
										<tr><td>&nbsp;</td></tr>
										<tr align="center">
												 <td bgcolor="#FFFFFF">
												 <table width="100%" border="0" align="center" cellspacing="0" cellpadding="0">														
														<tr>
														  <td class="bg">
														   
														   <table cellpadding="4" border="0"  cellspacing="0">
															<tr>															
																<td>&nbsp;</td>
																<td><s:text name="rating.mode.transport"/></td><td style="color:red;">&nbsp;*</td>
																<td><s:select name="transID" id="transID" list="transports" listKey="MODE_TRANSPORT_ID" listValue="TRANSPORT_DESCRIPTION" headerKey=""  headerValue="-Select-" onchange="getbaserates(this.value,'baserates'),getbaserates1(this.value,'baserates1')"/>
																
																</td>															
																<td>&nbsp;</td>
															</tr>	
																													
															<tr >
																<td>&nbsp;</td>
																<td><s:text name="coverage.name.baserate"/></td><td style="color:red;">*</td>
																<td id="baserates">
																	<s:select name="coverageID" id="coverageID" headerKey="-1" headerValue="-- Select --" list="#{}"/>
																</td>
																<td>&nbsp;</td>																
															</tr>															
															<tr> 
																<td>&nbsp;</td>
																<td ><s:text name="convey.name.baserate"/></td><td style="color:red;">*</td>
																<td id="baserates1">
																	<s:select name="coveyID" id="coveyID" headerKey="-1" headerValue="-- Select --" list="#{}" />
																</td>
																<td>&nbsp;</td>																
															</tr>															
															
															<tr>
																<td>&nbsp;</td>
																<td><s:text name="country.name.baserate"/></td><td style="color:red;">*</td>
																<td>
																	<s:select name="baserateCountryID" id="baserateCountryID" headerKey="-1" headerValue="-- Select --" list="baserateCountry" listKey="COUNTRY_ID" listValue="COUNTRY_NAME"/> 
																</td>
																<td>&nbsp;</td>																
															</tr>															
															
															<tr>
																<td>&nbsp;</td>
																<td><s:text name="commodity.name.baserate"/></td><td style="color:red;">*</td>
																<td >
																	<s:select name="baserateCommodityID" id="baserateCommodityID" headerKey="-1" headerValue="-- Select --" list="baserateCommodity" listKey="COMMODITY_ID" listValue="COMMODITY_NAME"/>
																</td>
																<td>&nbsp;</td>																
															</tr>															
															<tr>
															<td>&nbsp;</td>
															<td><s:text name="baserate.fragile"/></td><td style="color:red;">*</td>
															<td><s:radio name="fragile" list="#{'on':'Yes','off':'No'}" value='fragile==null?"off":fragile' /></td>
															<td>&nbsp;</td>
															</tr>
															<tr>
															<td>&nbsp;</td>
															<td><s:text name="baserate.sumrate"/></td><td style="color:red;">&nbsp;*</td> 
															<td><s:textfield name="sumrate" cssClass="input" onkeypress="return fun_AllowOnlyAmountAndDot(this.id)"/></td>
															<td>&nbsp;</td>
															</tr>											
												
														</table>
														<p><s:submit cssClass="btn" value="Calculate" /></p>	 </td>
											</tr>		
										</table>
										</td>
										</tr>
									</table>
    								<s:hidden name="agencyCode"/>
									<s:hidden name="login_Id"/>
									<s:hidden name="borganization"/>
									<s:hidden name="bcode"/>
								
								</td>
								</tr>
								</table>
							</td>
						 </tr>
						</table>
						</s:form>
						</div>
						</div>
						</div>
						</div>
						</body>
						<SCRIPT language=Javascript>
      
       function isNumberKey(evt)
       {
          var charCode = (evt.which) ? evt.which : event.keyCode;
          if (charCode != 46 && charCode > 31 
            && (charCode < 48 || charCode > 57))
             return false;

          return true;
       }
       
			function getbaserates(val, id){	
								
					postRequest('${pageContext.request.contextPath}/getListAjaxRating.action?reqFrom='+id+'&searchValue='+val, id);				
			}
			function getbaserates1(val, id){
					
					postRequest('${pageContext.request.contextPath}/getListAjaxRating.action?reqFrom='+id+'&searchValue='+val,id);				 
			}
			<s:if test='%{transID!=null && transID!=""}'>
				postRequest('${pageContext.request.contextPath}/getListAjaxRating.action?reqFrom=baserates&searchValue=<s:property value="transID"/>&coverageID=<s:property value="coverageID"/>','baserates'); 
	 			postRequest('${pageContext.request.contextPath}/getListAjaxRating.action?reqFrom=baserates1&searchValue=<s:property value="transID"/>&coveyID=<s:property value="coveyID"/>','baserates1');
	 			document.info.getElementById('coverageID').value=<s:property value='coverageID'/>;
	 			document.info.getElementById('coveyID').value=<s:property value='coveyID'/>;
	        </s:if>	        		
		function stopRKey(evt) { 
		  var evt = (evt) ? evt : ((event) ? event : null); 
		  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
		  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
		} 
		document.onkeypress = stopRKey; 
		</script>
		<script type="text/javascript" src="pages/admin/ratingEngine/menu.js"></script>
</html>