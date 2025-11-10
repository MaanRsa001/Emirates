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
			
			function getIssuer(val, id){
				if(val.length>=2){
					var searchBy=document.getElementById("searchBy").value;
					postRequest('${pageContext.request.contextPath}/getIssuerAjaxUnderwriterMgm.action?reqFrom='+id+'&searchBy='+searchBy+'&searchValue='+val, id);
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
		   function more(aaction,mode,name,loginId){
		     document.lform.action=aaction;
		     if('getNewUnderwriterMgm.action'!=aaction){
		        document.lform.mode.value=mode;
		     }
		     document.lform.name.value=name;
		     document.lform.loginId.value=loginId;
		     if('getNewUnderwriterMgm.action'==aaction){
		       document.lform.optionMode.value=mode;
		     } 
		     document.lform.submit();
		   }	
			
			
		</script>
		<style type="text/css">
			.inputBox {
				width: 50%;
			}
			
			.inputAppend {
				width: 51%;
			}
		
			.inputSelect {
				width: 52%;
			}
			
			th.sortable a {
				background-color: #337ab7;
			}
		</style>
	</head>
	<body>
    <div style="margin:10px 0;"></div>
    <div class="easyui-layout" style="height:100vh;">
    	<s:if test='%{borganization!=null && !"".equals(borganization)}'>
       <div data-options="region:'west',split:true" title="Options" style="width:150px;">
            <div class="easyui-accordion" data-options="fit:true,border:false">
             	<input type="button" class="btntab" value="View" onclick="fnCall('view')"/><br/>
                <input type="button" class="btntab" value="Edit" onclick="fnCall('edit')"/><br/>
                <input type="button" class="btntab" value="Change Password" onclick="fnCall('changePwd')"/><br/>
                <input type="button" class="btntab" value="Customer Details" onclick="fnCall('customerDetail')"/><br/>
                <input type="button" class="btntab" value="OpenCover" onclick="fnCall('openCover')"/><br/>
                <input type="button" class="btntab" value="Referral" onclick="fnCall('referal')"/><br/>
                <input type="button" class="btntab" value="Statistics" onclick="fnCall('statistics')"/><br/>
            </div>
        </div>
        <div data-options="region:'center',title:'<s:property value="borganization"/>(<s:property value="agencyCode"/>)',iconCls:'icon-ok'">
            <div class="easyui-tabs" data-options="fit:true,border:false,plain:true" id="mainTab">
       </s:if>
       	<s:else>
        	<div data-options="region:'center',title:'Underwriter Creation',iconCls:'icon-ok'">
       	</s:else>
                <div title="User List" style="padding:5px">
					<s:form name="info" id="info" method="post" action="" theme="simple" validate="false">
						<table width="95%" border="0" align="center" cellspacing="0" cellpadding="0" class='text'>
							<tr> <td>&nbsp;&nbsp;</td> </tr>
							<tr><td class="heading"><s:text name="user.underwriterList"/></td></tr>
							<tr><td>&nbsp;</td></tr>
						<tr><td style="color:red"><s:actionerror/><s:actionmessage/></td></tr>
							<tr>
							<%-- <td>
									<table width="100%">
									<tr>
										<td> Search By:<s:select name="searchBy" id="searchBy" list="#{'name':'Issuer Name', 'code':'Issuer Code'}" headerKey="" headerValue="-Select-"/>
										<s:textfield name="searchVal" onkeyup="getIssuer(this.value,'issuerLists')"/></td>
									</tr>
								</table></td> --%>
							</tr>
							<tr>
								<td>&nbsp;&nbsp;</td>
							</tr>
							<tr><td>
									<table width="100%">
									<tr>
										<td width="50%">
											<table width="100%">
												<tr>
													<td width="33%" align="right"> Search By:</td>
													<td width="33%"><s:select name="searchBy" id="searchBy" cssClass="inputSelect" cssStyle="width: 100%" list="#{'name':'Issuer Name'}" /></td>
													<td width="33%"><s:textfield name="searchVal" cssClass="inputBox" cssStyle="width: 100%" onkeyup="getIssuer(this.value,'issuerList')"/></td>
												</tr>
											</table>
										</td>
										<td width="50%" align="right">
											<a class="btn btn-sm btn-primary" style="text-decoration: none;" title="Customer Creation" onclick="more('getNewUnderwriterMgm.action','new','','')" href="#" ><s:text name="Add new Issuer"/></a>
										</td>
									</tr>
								</table></td>
							</tr>
							<tr align="center">
								<td bgcolor="#FFFFFF" style="padding:10px; background:#F8F8F8" align="center" width="100%">
									<div id="issuerList">
										<display:table name="underwriterList" pagesize="10" requestURI="/getABListUnderwriterMgm.action" class="footable" uid="row" id="record">
											<display:setProperty name="paging.banner.one_item_found" value="" />
											<display:setProperty name="paging.banner.one_items_found" value="" />
											<display:setProperty name="paging.banner.all_items_found" value="" />
											<display:setProperty name="paging.banner.some_items_found" value="" />
											<display:setProperty name="paging.banner.placement"	value="bottom" />
											<display:setProperty name="paging.banner.onepage" value="" />
											<display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title=" S.No " value="${record_rowNum}"/>
											<display:column sortable="true" style="text-align:left;font-size:13px;" title="Issuer Name" property="COMPANY_NAME"/>
											<display:column sortable="true" style="text-align:left;font-size:13px;" title="Issuer Type" property="USERTYPE" />
											<display:column sortable="true" style="text-align:left;font-size:13px;" title="Login Id" property="LOGIN_ID" />
											<display:column sortable="true" style="text-align:left;font-size:13px;" title="Created Date" property="CR_DATE" />
											<display:column sortable="true" style="text-align:center;font-size:13px;" title="More">
												<a class="btn btn-sm btn-primary" style="text-decoration: none;" onclick="more('viewUnderwriterMgm.action','edit','${record.COMPANY_NAME}','${record.LOGIN_ID}')" href="#">More</a>
											</display:column>
											<display:column sortable="true" style="text-align:center;font-size:13px;" title="Status" property="STATUS"/>
										</display:table>
									</div>
								</td>
							</tr>
						</table>
					</s:form>
				</div>
			</div>
		</div>
	</div>
	</body>
	<s:form name="lform">
	  <s:hidden  name="mode"/>
<%-- 	  <s:hidden  name="issurName"/> --%>
	  <s:hidden  name="loginId"/>
	  <s:hidden name="optionMode" />
	</s:form>
</html>