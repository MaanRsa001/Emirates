<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<%@ taglib uri="/struts-jquery-tags" prefix="sj"%>
<html>
	<head>
		<link href="<%=request.getContextPath()%>/cssbootstrap/footable-0.1.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
			function stopRKey(evt) { 
			  var evt = (evt) ? evt : ((event) ? event : null); 
			  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
			  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
			} 
			document.onkeypress = stopRKey;
			
			function getPolicy(val, id){
				if(val.length>=2){
					var searchBy=document.getElementById("searchBy").value;
					var cId=document.getElementById("customerId").value;
					var acode=document.getElementById("agencyCode").value;
					postRequest('${pageContext.request.contextPath}/getCustomerAjaxCustomerMgm.action?agencyCode='+acode+'&customerId='+cId+'&reqFrom='+id+'&searchBy='+searchBy+'&searchValue='+val, id);
				}
			}
		
			$(function(){
				var index = '<s:property value="index"/>';
				var t = $('#tabs');
				var tabs = t.tabs('tabs');
					t.tabs('select', tabs[index].panel('options').title);
			});
			
			function fnCall(from){
				if(from=='edit')
					document.info.action = from+"BrokerMgm.action?mode=edit";
				else if(from=='userDetail')
					document.info.action = "getABListUserMgm.action?mode1=broker";
				else if(from=='customerDetail')
					document.info.action = "getABListCustomerMgm.action?mode1=broker";
				else if(from=='referal')
					document.info.action = "getOCListReferal.action";
				else if(from=='openCover')
					document.info.action = "opencoverOC.action";
				else
					document.info.action = from+"BrokerMgm.action";
				document.info.submit();
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
    	<s:if test='%{borganization!=null && borganization!=""}'>
	       <div data-options="region:'west',split:true" title="Options" style="width:150px;">
	            <div class="easyui-accordion" data-options="fit:true,border:false">
	            	<input type="button" class="btntab" value="View" onclick="fnCall('view')"/><br/>
	                <input type="button" class="btntab" value="Edit" onclick="fnCall('edit')"/><br/>
	                <input type="button" class="btntab" value="Change Password" onclick="fnCall('changePwd')"/><br/>
	                <input type="button" class="btntab" value="User Details" onclick="fnCall('userDetail')"/><br/>
	                <input type="button" class="btntab" value="OpenCover" onclick="fnCall('openCover')"/><br/>
	                <input type="button" class="btntab" value="Referral" onclick="fnCall('referal')"/><br/>
	                <input type="button" class="btntab" value="Statistics" onclick="fnCall('statistics')"/><br/>
	            </div>
	        </div>
	       <div data-options="region:'center',title:'<s:property value="borganization"/>(<s:property value="agencyCode"/>)',iconCls:'icon-ok'">
       	</s:if>
       	<s:else>
        	<div data-options="region:'center',title:'Customer Management',iconCls:'icon-ok'">
       	</s:else>
            <div class="easyui-tabs" data-options="fit:true,border:false,plain:true" id="tabs">
                <div title="Customer Info" style="padding:5px">
                	<table width="100%">
                          <tr>
						    <td height="5"></td>
						  </tr>
						  <tr>
						    <td><table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#E5E5E5">
						      <tr>
						        <td bgcolor="#FFFFFF" style="padding:10px; background:#F8F8F8">
						        <s:form id="info" name="info" method="post" action="" theme="simple">
						            <table width="100%" border="0" cellspacing="0" cellpadding="0">
						            	<tr>
							       	   		<td  style="color:red;"><s:actionerror/></td>
							       		</tr>
										<tr> <td>&nbsp;&nbsp;</td> </tr>
										<tr><td class="heading"><s:text name="broker.brokermanagement"/></td></tr>
										<tr><td>&nbsp;</td></tr>
										<tr align="center">
											 <td bgcolor="#FFFFFF"><table width="100%" border="0" cellspacing="0" cellpadding="0">
													<tr>
														<td class="heading" colspan="5" align="left"><s:text name="customer.contactinfo"/></td>
													</tr>
													<tr>
													  <td class="bg"><table width="100%" border="0" align="center" cellpadding="4" cellspacing="0">
														<tr>
															<td width="5%">&nbsp;</td>
															<td width="30%" align="left">
																<b><s:text name="customer.customerId"/> </b><br/>
																<s:property value="customerId"/>
															</td>
															<td width="30%" align="left">
																<b><s:text name="customer.name"/></b><br/>
																<s:property value="cfirstname"/>
															</td>
															<td width="30%" align="left">
																<b><s:text name="customer.gender"/></b><br/>
																<s:property value='%{cgender=="M"?"Male":"Female"}'/>
															</td>
															<td width="5%">&nbsp;</td>
														</tr>
														<tr>
															<td width="5%">&nbsp;</td>
															<td><b><s:text name="customer.dob"/></b><br/>
																<s:property value="cdob"/>
															</td>
															<td><b><s:text name="customer.nationality"/></b><br/>
																<s:property value="cnationalityNa"/>
															</td>
															<td><b><s:text name="customer.agencycode"/></b><br/>
																<s:property value="cagencyCode"/>
															</td>
															<td width="5%">&nbsp;</td>
														</tr>
														<tr>
															<td width="5%">&nbsp;</td>
															<td align="left">
																<b><s:text name="customer.occupation"/></b><br/>
																<s:property value="coccupation"/>
															</td>
															<td><b><s:text name="customer.address1"/></b><br/>
																<s:property value="caddress1"/>
															</td>
															<td align="left">
																<b><s:text name="customer.address2"/></b><br/>
																<s:property value="caddress2"/>
															</td>
															<td width="5%">&nbsp;</td>
														</tr>
														<tr>
															<td width="5%">&nbsp;</td>
															<td align="left">
																<b><s:text name="customer.city"/></b><br/>
																<s:property value="ccity"/>
															</td>
															<td align="left">
																<b><s:text name="customer.country"/></b><br/>
																<s:property value="ccountryNa"/>
															</td>
															<td align="left">
																<b><s:text name="customer.pobox"/></b><br/>
																<s:property value="cpobox"/>
															</td>
															<td width="5%">&nbsp;</td>
														</tr>
														<tr>
															<td width="5%">&nbsp;</td>
															<td align="left">
																<b><s:text name="customer.mobile"/></b><br/>
																<s:property value="cmobile"/>
															</td>
															<td align="left">
																<b><s:text name="customer.telephone"/></b><br/>
																<s:property value="cphone"/>
															</td>
															<td align="left">
																<b><s:text name="customer.email"/></b><br/>
																<s:property value="cemail"/>
															</td>
															<td width="5%">&nbsp;</td>
														</tr>
														<tr>
															<td width="5%">&nbsp;</td>
															<td align="left">
																<b><s:text name="customer.fax"/></b><br/>
																<s:property value="cfax"/>
															</td>
															<td></td>
															<td></td>
															<td width="5%">&nbsp;</td>
														</tr>
													</table></td></tr>
											</table>
										</td>
									</tr>
								</table>
   								<s:hidden name="agencyCode"/>
   								<s:hidden name="customerId"/>
								<s:hidden name="borganization"/>
							</s:form>
							</td>
							</tr>
						</table></td></tr>
                    </table>
                </div>
                <div title="Open Cover List" style="padding:5px">
                	<s:form name="info1" id="info1" method="post" action="" theme="simple">
						<table width="90%" border="0" align="center" cellspacing="0" cellpadding="0" class='text'>
							<tr> <td>&nbsp;&nbsp;</td> </tr>
							<tr><td class="heading"><s:text name="customer.opencoverList"/></td></tr>
							<tr><td>&nbsp;</td></tr>
							<tr><td>
									<table width="100%">
									<tr>
										<td width="50%">
											<table width="100%">												
												<tr>
													<td width="33%">Search By:</td>
													<td width="33%"><s:select name="searchBy" id="searchBy" list="#{'policy':'Policy No', 'ocover':'OpenCover No'}" headerKey="" cssClass="inputSelect" headerValue="-Select-"/></td>
													<td width="33%"><s:textfield name="searchVal" onkeyup="getPolicy(this.value,'opencover')" cssClass="inputBox" /></td>
												</tr>
											</table>
										</td>
										<td width="50%">
										</td>									
									</tr>
								</table></td>
							</tr>
							<tr>
								<td>&nbsp;&nbsp;</td>
							</tr>
							<tr align="center">
								<td bgcolor="#FFFFFF" style="padding:10px; background:#F8F8F8" align="center" width="100%">
									<div id="opencover">
										<display:table name="openCover" pagesize="10" requestURI="/viewCustomerMgm.action?reqFrom=opencover" class="footable" uid="row" id="record" excludedParams="reqFrom">
											<display:setProperty name="paging.banner.one_item_found" value="" />
											<display:setProperty name="paging.banner.one_items_found" value="" />
											<display:setProperty name="paging.banner.all_items_found" value="" />
											<display:setProperty name="paging.banner.some_items_found" value="" />
											<display:setProperty name="paging.banner.placement"	value="bottom" />
											<display:setProperty name="paging.banner.onepage" value="" />
											<display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title=" S.No " value="${record_rowNum}"/>
											<display:column sortable="true" style="text-align:left;font-size:13px;"  title="Core Application Policy No" property="MISSIPPI_OPENCOVER_NO" />
											<display:column sortable="true" style="text-align:left;font-size:13px;" title="Open Cover No" property="open_cover_no"/>
											<display:column sortable="true" style="text-align:left;font-size:13px;" title="Start Date" property="pol_start_date" />
											<display:column sortable="true" style="text-align:left;font-size:13px;" title="Expiry Date" property="pol_expire_date" />
											<display:column sortable="true" style="text-align:left;font-size:13px;" title="Status" property="STATUS"/>
										</display:table>
									</div>
								</td>
							</tr>
						</table>
						<s:hidden name="agencyCode" id="agencyCode"/>
 						<s:hidden name="customerId" id="customerId"/>
						<s:hidden name="borganization"/>
					</s:form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>

