<%@ page isELIgnored="false" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<html>
	<head>
		<link href="<%=request.getContextPath()%>/cssbootstrap/footable-0.1.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/cssbootstrap/bootstrap.min.css" />
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<script type="text/javascript">
			function stopRKey(evt) { 
			  var evt = (evt) ? evt : ((event) ? event : null); 
			  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
			  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
			} 
			document.onkeypress = stopRKey;
			
			function getBroker(val, id){
				var searchBy=document.getElementById("searchBy").value;
				if((searchBy=="status" && val!=null) || (searchBy!=null && val.length>=2)){
					var bCode=document.getElementById("branchCode").value;
					postRequest('${pageContext.request.contextPath}/getBrokerAjaxBrokerMgm.action?reqFrom='+id+'&branchCode='+bCode+'&searchBy='+searchBy+'&searchValue='+val, id);
				}
			}
		</script>
		<style type="text/css">
		th.sortable a {
			background-color: #337ab7;
		}
		</style>
	</head>
	<body>
		<s:form name="form1" method="post" action="" theme="simple" validate="false">
			<table width="90%" border="0" align="center" cellspacing="0" cellpadding="0" class='text'>
				<tr> <td>&nbsp;&nbsp;</td> </tr>
				<tr><td class="heading"><s:text name="broker.brokermanagement"/></td></tr>
				<tr><td>&nbsp;</td></tr>
				<tr><td>
						<table width="100%">
						<tr>
							<td width="50%">
								<table width="100%">
									<tr>
										<td width="15%">Search By:</td>
										<td width="20%"><s:select name="searchBy" id="searchBy" cssClass="inputSelect" list="#{'name':'Broker Name', 'code':'Broker Code','status':'Status'}" headerKey="" headerValue="-Select-" onchange="fnStatus(this.value)"/></td>
										<td width="32%" id="searchTxt"><s:textfield name="searchVal" cssClass="inputBox" onkeyup="getBroker(this.value,'brokerLists')" value="%{searchValue}"/></td>
										<td width="60%" id="searchradio" style="display: none;"><s:radio name="searchVal" onclick="getBroker(this.value,'brokerLists')" list="#{'Y':'Active','D':'DeActive','L':'Locked'}" value="%{searchValue}"/></td></td>
									</tr>
								</table>
							</td>
							<td width="50%" align="right">
								<a class="btn btn-sm btn-primary" style="text-decoration: none;" title="Broker Creation" href="editBrokerMgm.action?mode=new"><s:text name="broker.new.create"/></a>
							</td>							
						</tr>
					</table></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;</td>
				</tr>
				<tr align="center">
					<td bgcolor="#FFFFFF" style="padding:10px; background:#F8F8F8" align="center" width="100%">
						<div id="brokerLists">
						<display:table name="brokerList" pagesize="10" requestURI="/getABListBrokerMgm.action" class="footable" uid="row" id="record">
								<display:setProperty name="paging.banner.one_item_found" value="" />
								<display:setProperty name="paging.banner.one_items_found" value="" />
								<display:setProperty name="paging.banner.all_items_found" value="" />
								<display:setProperty name="paging.banner.some_items_found" value="" />
								<display:setProperty name="paging.banner.placement"	value="bottom" />
								<display:setProperty name="paging.banner.onepage" value="" />
								<display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title=" S.No " value="${record_rowNum}"/>
								<display:column sortable="true" style="text-align:left;font-size:13px;" title="Broker Name">
									${record.COMPANY_NAME}&nbsp;(${record.AGENCY_CODE})
								</display:column>
								<display:column sortable="true" style="text-align:left;font-size:13px;"  title="BrokerCode" property="RSA_BROKER_CODE" />
								<display:column sortable="true" style="text-align:left;font-size:13px;" title="Login Id" property="LOGIN_ID" />
								<display:column sortable="true" style="text-align:left;font-size:13px;" title="	CreatedDate	" property="cr_date" />
								<display:column sortable="true" style="text-align:center;font-size:13px;" title="More">
									<a href="viewBrokerMgm.action?mode=edit&agencyCode=${record.AGENCY_CODE}" class="btn btn-sm btn-primary" style="text-decoration: none;" >More</a>
								</display:column>
								<display:column sortable="true" style="text-align:center;font-size:13px;" property="STATUS"/>
						</display:table>
						</div>
					</td>
				</tr>
			</table>
			<s:hidden name="branchCode" id="branchCode"/>
		</s:form>
	</body>
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
</html>