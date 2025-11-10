<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ page isELIgnored="false"%>
<html>
	<head>
		<sj:head jqueryui="true" jquerytheme="start" />
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/cssbootstrap/bootstrap.min.css" />
		<link href="<%=request.getContextPath()%>/cssbootstrap/footable-0.1.css" rel="stylesheet" type="text/css" />
		<style type="text/css">
.royamenuhead a {
	color: #fff;
}

th.sortable a {
	background-color: #337ab7;
}
</style>
		<script type="text/javascript">
	function stopRKey(evt) { 
		var evt = (evt) ? evt : ((event) ? event : null); 
		var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
		if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
	} 
	document.onkeypress = stopRKey;
</script>
	</head>
	<body>
		<div class="panel panel-default">
			<div class="panel-heading">
				<s:text name="claim.claimIntimation" />
			</div>
			<div id="tabs-1">
				<div class="panel-body">
					<div id="pendingPolicies">
						<s:form id="pending" name="pending" theme="simple">
							
								<display:table name="claimIntimation" pagesize="10"
									requestURI="claimIntimation.action" class="footable" uid="row"
									id="record1" excludedParams="from1"
									style="text-align:center;font-size:13px;">
									<display:setProperty name="paging.banner.one_item_found"
										value="" />
									<display:setProperty name="paging.banner.one_items_found"
										value="" />
									<display:setProperty name="paging.banner.all_items_found"
										value="" />
									<display:setProperty name="paging.banner.some_items_found"
										value="" />
									<display:setProperty name="paging.banner.placement"
										value="bottom" />
									<display:setProperty name="paging.banner.onepage" value="" />
									<display:column sortable="false"
										style="text-align:center;font-size:13px;height:30px;width:100px;"
										title=" S.No ">
										${record1_rowNum}
									</display:column>
									<display:column sortable="false"
										style="text-align:center;font-size:13px;width:250px;"
										title="Date">
										<a type="button" class="btn btn-sm btn-primary"
											style="color: #ffffff;" href="#"
											onclick="fnTab('${record1.INTIMATION_DATES}','claimPending')">${record1.INTIMATION_DATES}</a>
									</display:column>
									<display:column sortable="false"
										style="text-align:center;font-size:13px;width:250px;"
										title="Number of Policies" property="POLICY_COUNT" />

								</display:table>
							
							
						</s:form>
					</div>
				</div>
			</div>
		</div>
	</body>
	<SCRIPT type="text/javascript">
	
			function fnTab(date,reqFrom){
				if(reqFrom=="claimPending"){
					postRequest('${pageContext.request.contextPath}/claimIntimation.action?reqFrom='+reqFrom+'&rdate='+date,'pendingPolicies');
		   		}
		   		if(reqFrom=="View"){
					document.pending.action='${pageContext.request.contextPath}/claimIntimation.action?reqFrom='+reqFrom+'&policyNo='+date,'claimPendingPolicies';
					document.pending.submit();
		   		}
		   	}
	
	</SCRIPT>
</html>