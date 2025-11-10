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
			
			function getUser(val, id){
				var searchBy=document.getElementById("searchBy").value;
				if((searchBy=="status" && val!=null) || (searchBy!="status" && val.length>=2)){
					
					var aCode=document.getElementById("agencyCode").value;
					postRequest('${pageContext.request.contextPath}/getUserAjaxUserMgm.action?agencyCode='+aCode+'&reqFrom='+id+'&searchBy='+searchBy+'&searchValue='+val, id);
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
        	<div data-options="region:'center',title:'User Management',iconCls:'icon-ok'">
       	</s:else>
                <div title="User List" style="padding:5px">
					<s:form name="info" id="info" method="post" action="" theme="simple" validate="false">
						<table width="95%" border="0" align="center" cellspacing="0" cellpadding="0" class='text'>
							<tr> <td>&nbsp;&nbsp;</td> </tr>
							<tr><td class="heading"><s:text name="user.userList"/></td></tr>
							<tr><td>&nbsp;</td></tr>
							<tr><td>
									<table width="100%">
									<tr>
										<td width="50%">
											<table width="100%">
												<tr>
													<td width="15%">Search By:</td>
													<td width="20%"><s:select name="searchBy" id="searchBy" list="#{'name':'User Name', 'code':'User Code','status':'Status'}" headerKey="" cssClass="inputSelect" headerValue="-Select-" onchange="fnStatus(this.value)"/></td>
													<td width="32%" id="searchTxt"><s:textfield name="searchVal" onkeyup="getUser(this.value,'userLists')" cssClass="inputBox" value="%{searchValue}"/></td></td>
													<td width="50%" id="searchradio" style="display: none;"><s:radio name="searchVal" onclick="getUser(this.value,'userLists')" list="#{'Y':'Active','D':'DeActive','L':'Locked'}" value="%{searchValue}"/></td></td>
												</tr>
											</table>
										</td>
										<td width="50%" align="right">
											<a class="btn btn-sm btn-primary" style="text-decoration: none;" title="Customer Creation" href="editUserMgm.action?mode=new&agencyCode=<s:property value="agencyCode"/>&borganization=<s:property value="borganization"/>"><s:text name="user.new.create"/></a>
										</td>
									</tr>
								</table></td>
							</tr>
							<tr>
								<td>&nbsp;&nbsp;</td>
							</tr>
							<tr align="center">
								<td bgcolor="#FFFFFF" style="padding:10px; background:#F8F8F8" align="center" width="100%">
									<div id="userLists">
										<display:table name="userList" pagesize="10" requestURI="/getABListUserMgm.action" class="footable" uid="row" id="record">
											<display:setProperty name="paging.banner.one_item_found" value="" />
											<display:setProperty name="paging.banner.one_items_found" value="" />
											<display:setProperty name="paging.banner.all_items_found" value="" />
											<display:setProperty name="paging.banner.some_items_found" value="" />
											<display:setProperty name="paging.banner.placement"	value="bottom" />
											<display:setProperty name="paging.banner.onepage" value="" />
											<display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title=" S.No " value="${record_rowNum}"/>
											<display:column sortable="true" style="text-align:left;font-size:13px;" title="User Name" property="company_name"/>
											<display:column sortable="true" style="text-align:left;font-size:13px;"  title="User Code" property="AGENCY_CODE" />
											<display:column sortable="true" style="text-align:left;font-size:13px;" title="Login Id" property="LOGIN_ID" />
											<display:column sortable="true" style="text-align:left;font-size:13px;" title="Broker Name" property="brokerName" />
											<display:column sortable="true" style="text-align:left;font-size:13px;" title="Created Date" property="cr_date" />
											<display:column sortable="true" style="text-align:center;font-size:13px;" title="More">
												<a href="viewUserMgm.action?mode=edit&uagencyCode=${record.AGENCY_CODE}&borganization=${borganization}&agencyCode=${agencyCode}" class="btn btn-sm btn-primary" style="text-decoration: none;" >More</a>
											</display:column>
											<display:column sortable="true" style="text-align:center;font-size:13px;" property="STATUS"/>
										</display:table>
									</div>
								</td>
							</tr>
						</table>
						<s:hidden name="borganization"/>
						<s:hidden name="mode1"/>
						<s:hidden name="agencyCode" id="agencyCode"/>
						<s:hidden name="login_Id"/>
					</s:form>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	fnStatus('<s:property value="searchBy"/>')
		function fnStatus(val){
			if(val=="status"){
				document.getElementById('searchradio').style.display='block';
				document.getElementById('searchTxt').style.display='none';
			}else{
				document.getElementById('searchTxt').style.display='block';
				document.getElementById('searchradio').style.display='none';
			}
		}
		
	</script>
	</body>
</html>