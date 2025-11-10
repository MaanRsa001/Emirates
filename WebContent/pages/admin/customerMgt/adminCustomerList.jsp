<%@ page isELIgnored="false" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<%@ taglib uri="/struts-jquery-tags" prefix="sj"%>
<html>
	<head>
		<sj:head jqueryui="true" jquerytheme="start" />
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<link href="<%=request.getContextPath()%>/cssbootstrap/footable-0.1.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/cssbootstrap/bootstrap.min.css" />
		<script type="text/javascript">
		
			function stopRKey(evt) { 
			  var evt = (evt) ? evt : ((event) ? event : null); 
			  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
			  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
			} 
			document.onkeypress = stopRKey;
			
			function getCustomer(val, id){
				if(val.length>=2){
					var searchBy=document.getElementById("searchBy").value;
					var aCode=document.getElementById("agencyCode").value;
					postRequest('${pageContext.request.contextPath}/getCustomerAjaxCustomerMgm.action?agencyCode='+aCode+'&reqFrom='+id+'&searchBy='+searchBy+'&searchValue='+val, id);
				}
			}
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
			function forward(from,val){
				document.getElementById("customerId").value=val;
				document.info.action ="viewCustomerMgm.action?mode=edit";
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
    	<s:if test='%{borganization!=null}'>
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
	       		 <div class="easyui-tabs" data-options="fit:true,border:false,plain:true" id="mainTab">
       	</s:if>
       	<s:else>
        	<div data-options="region:'center',title:'Customer Management',iconCls:'icon-ok'">
       	</s:else>
                <div title="Customer List" style="padding:5px">
					<s:form name="info" id="info" method="post" action="" theme="simple" validate="false">
						<table width="95%" border="0" align="center" cellspacing="0" cellpadding="0" class='text'>
							<tr> <td>&nbsp;&nbsp;</td> </tr>
							<tr><td class="heading"><s:text name="customer.customerList"/></td></tr>
							<tr><td>&nbsp;</td></tr>
							<tr><td>
									<table width="100%">
									<tr>
										<td width="50%">
										<table width="100%">
											<tr>
												<td width="30%" align="center">Search By:</td>
												<td width="30%"><s:select name="searchBy" id="searchBy" cssClass="inputSelect" list="#{'name':'Customer Name', 'code':'Customer Code'}" headerKey="" headerValue="-Select-"/></td>
												<td width="30%">													
													<s:textfield name="searchVal" cssClass="inputBox" onkeyup="getCustomer(this.value,'customerLists')"/>
												</td>
											</tr>
										</table>
										</td>
										<td width="50%"></td>
										</tr>
								</table></td>
							</tr>
							<tr>
								<td>&nbsp;&nbsp;</td>
							</tr>
							<tr align="center">
								<td bgcolor="#FFFFFF" style="padding:10px; background:#F8F8F8" align="center" width="100%">
									<div id="customerLists">
										<display:table name="customerList" pagesize="10" requestURI="/getABListCustomerMgm.action" class="footable" uid="row" id="record">
											<display:setProperty name="paging.banner.one_item_found" value="" />
											<display:setProperty name="paging.banner.one_items_found" value="" />
											<display:setProperty name="paging.banner.all_items_found" value="" />
											<display:setProperty name="paging.banner.some_items_found" value="" />
											<display:setProperty name="paging.banner.placement"	value="bottom" />
											<display:setProperty name="paging.banner.onepage" value="" />
											<display:column sortable="false" style="text-align:left;font-size:13px;height:30px;" title=" S.No " value="${record_rowNum}"/>
											<display:column sortable="false" style="text-align:left;font-size:13px;" title="Customer Name" property="company_name"/>
											<display:column sortable="false" style="text-align:left;font-size:13px;"  title="Customer Code" property="customer_id" />
											<display:column sortable="false" style="text-align:left;font-size:13px;" title="Broker Name" property="broker_name"/>
											<display:column sortable="false" style="text-align:left;font-size:13px;" title="Created Date" property="ENTRY_DATE" />
											<display:column sortable="false" style="text-align:center;font-size:13px;" title="More">
												<a class="btn btn-sm btn-primary" style="text-decoration: none;" href="#" onclick="forward('view','${record.customer_id}')">More</a>
											</display:column>
										</display:table>
									</div>
								</td>
							</tr>
						</table>
						<s:hidden name="borganization"/>
						<s:hidden name="agencyCode" id="agencyCode"/>
						<s:hidden name="customerId" id="customerId"/>
						<s:hidden name="login_Id" />
					</s:form>
				</div>
			</div>
		</div>
	</div>
	</body>
</html>