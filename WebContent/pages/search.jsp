<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
       	<link href="${pageContext.request.contextPath}/css/displaytag.css" rel="stylesheet" type="text/css">
        <meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="Emirates, Maansarovar">
		<meta http-equiv="description" content="Search Page">
		<script src="<%=request.getContextPath()%>/assets1/plugins/jquery/jquery-1.11.1.min.js"></script>
		<link href="<%=request.getContextPath()%>/assets1/plugins/dataTables/css/dataTables.bootstrap.min.css" rel="stylesheet">
		<link href="<%=request.getContextPath()%>/assets1/plugins/dataTables/css/dataTables.bootstrap.min.css" rel="stylesheet">
		<link href="<%=request.getContextPath()%>/assets1/plugins/dataTables/css/buttons.bootstrap.min.css" rel="stylesheet">
		<link href="<%=request.getContextPath()%>/assets1/plugins/dataTables/css/fixedHeader.bootstrap.min.css" rel="stylesheet">
		<link href="<%=request.getContextPath()%>/assets1/plugins/dataTables/css/responsive.bootstrap.min.css" rel="stylesheet">
		<link href="<%=request.getContextPath()%>/assets1/plugins/dataTables/css/scroller.bootstrap.min.css" rel="stylesheet">
		<script src="<%=request.getContextPath()%>/assets1/plugins/dataTables/js/jquery.dataTables.min.js"></script>
		<script src="<%=request.getContextPath()%>/assets1/plugins/dataTables/js/dataTables.bootstrap.min.js"></script>
		<script src="<%=request.getContextPath()%>/assets1/plugins/dataTables/js/dataTables.buttons.min.js"></script>
		<script src="<%=request.getContextPath()%>/assets1/plugins/dataTables/js/buttons.bootstrap.min.js"></script>
		<script src="<%=request.getContextPath()%>/assets1/plugins/dataTables/js/buttons.flash.min.js"></script>
		<script src="<%=request.getContextPath()%>/assets1/plugins/dataTables/js/buttons.html5.min.js"></script>
		<script src="<%=request.getContextPath()%>/assets1/plugins/dataTables/js/buttons.print.min.js"></script>
		<script src="<%=request.getContextPath()%>/assets1/plugins/dataTables/js/dataTables.fixedHeader.min.js"></script>
		<script src="<%=request.getContextPath()%>/assets1/plugins/dataTables/js/dataTables.keyTable.min.js"></script>
		<script src="<%=request.getContextPath()%>/assets1/plugins/dataTables/js/dataTables.responsive.min.js"></script>
		<script src="<%=request.getContextPath()%>/assets1/plugins/dataTables/js/responsive.bootstrap.js"></script>
		<script language="JavaScript">
		function stopRKey(evt) { 
		  var evt = (evt) ? evt : ((event) ? event : null); 
		  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
		  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
		} 
		document.onkeypress = stopRKey; 
		
		jQuery(function ($) {
			try {
				var table = $('#record').DataTable({
					 "lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
						//"order": [[ 0, "asc" ]],
						"responsive": true,
						"deferRender": true
					});
			    
				} catch(err){ console.log(err);}
			});
		</script>			
    </head>

<body> 
<s:form name="search" theme="simple" action="searchReport.action">
	<div class="container-fluid">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<div class="row">
    				<div class="col-md-12">
    					<h4><s:text name="search.search" /></h4>
    				</div>
    			</div>
			</div>
			<div class="panel-body">
				<div class="row">
					<s:actionerror cssStyle="color:red;"/>
				</div>
				<div class="row">
    				<div class="col-md-4">
    					<div class="form-group">
    						<s:label key="search.searchBy" />
    						<s:if test='#session.usertype==getText("ISSUER")'>
								<s:select name="searchBy" id="searchBy" list="#{'policyNo':'Policy No','quoteNo':'Quote No','custName':'Customer Name','otherUsers':'Other Users'}"  headerKey="" headerValue="Select" cssClass="form-control" onchange="setDefault(this)"/>
							</s:if>
							<s:else>
								<s:select name="searchBy" id="searchBy" list="#{'policyNo':'Policy No','quoteNo':'Quote No','custName':'Customer Name'}"  headerKey="" headerValue="Select"  onchange="setDefault(this)" cssClass="form-control"/>
							</s:else>
    					</div>
    				</div>
    				<s:if test='#session.usertype==getText("BROKER") || #session.usertype==getText("ISSUER")'>
	    				<div class="col-md-4">
	    					<div class="form-group">
	    						<div id="brokerList">
		    						<s:label key="report.selectuser"/>
		    						<s:select list="userSelection" listKey="LOGIN_ID" listValue="USERNAME" name="loginId"  headerKey="" headerValue="Select" value='%{#session.product_id==getText("OPEN_COVER")?#session.userName:loginId}'  cssClass="form-control"/>
		    					</div>
	    					</div>
	    				</div>
	    			</s:if>
	    			<div class="col-md-4">
    					<div class="form-group">
    						<s:label key="search.enterDataForSearch"/>
	    					<s:textfield name="searchValue" id="searchValue" onkeyup="getSearchResult('searchResult',this.value)" cssClass="form-control"/>
    					</div>
    				</div>
				</div>
				<div class="row">
					<div class="col-md-12"  id="searchResult" align="center">
					</div>
				</div>
			</div>
		</div>
	</div>
	<s:hidden name="openCoverNo" value="%{#session.openCoverNo}"/>
	<s:hidden name="quoteNo"/>
	<s:hidden name="quoteStatus"/>
	<s:hidden name="tranId"/>
	<s:hidden name="policyNo"/>
	<s:hidden name="menuType"/> 
	<s:hidden name="applicationNo"/>
	<s:hidden name="linkType" />
	<s:hidden name="scrnFrom" />
	<s:hidden name="quote_no" />
</s:form>	
 <script type="text/javascript">
 function setDefault(obj)
 { 
 	var value=obj.value;  
 	var productCode='<s:text name="OPEN_COVER"/>';
 	var session_productCode='<s:property value="#session.product_id"/>'
 	var loginId='';
	loginId='<s:property value="#session.user"/>'; 
 	if(value=='policyNo' && session_productCode == productCode )
 	{  
 		document.getElementById('searchValue').value='<s:property value="#session.openCoverNo"/>';
 		getSearchResult('searchResult',document.search.searchValue.value);
 	}else{
 		 document.getElementById('searchValue').value='';   
 	}
 	if(session_productCode != productCode)
 	{
 		postRequest('${pageContext.request.contextPath}/brokerListReport.action?searchBy='+value+'&loginId='+loginId,'brokerList');
 	}
 }

if(document.search.searchValue.value!=''){
	getSearchResult('searchResult',document.search.searchValue.value);
}
function getSearchResult(id, searchValue)
{
	var error='';
	var loginId='';
	var searchBy=document.search.searchBy.value;
	if('User'=='<s:property value="#session.usertype"/>'){
		loginId='<s:property value="#session.user"/>'; 
	}else{
		loginId=document.search.loginId.value;
	}
	if(searchBy=='')
	{
		error+='- <s:text name="error.searchBy.empty" />\n';
	}if(loginId=='')
	{
		error+='- <s:text name="error.loginId.empty" />\n';
	}if(error=='' && searchValue!='')
	{
		postRequest('${pageContext.request.contextPath}/searchReport.action?searchBy='+searchBy+'&loginId='+loginId+'&searchValue='+searchValue+'&openCoverNo=<s:property value="#session.openCoverNo"/>'+'&searchFrom=S',id);
	}else if(error!=''){
		alert(error);
	}
}
function view(appNo,quoteNo,productId){
	popUp('\quotationSchedule.jsp?quoteNo='+quoteNo,650,650);
}
 

function editQuote(applicationNo,quoteNo, status,customerId)
{ 
	document.search.quoteNo.value=quoteNo;
	document.search.quoteStatus.value=status;
	document.search.applicationNo.value=applicationNo;
	document.search.action = "${pageContext.request.contextPath}/editQuoteQuotation.action";
	document.search.submit();
}

/*function sentMail(val){
	document.search.scrnFrom.value = "QuoteRegister";
	document.search.quote_no.value=val;
	document.search.action="mailController";
	document.search.submit();
}*/
function sentMail(applicationNo,linkType,quoteNo) {
	document.search.menuType.value='QL';
	document.search.applicationNo.value=applicationNo;
	document.search.linkType.value=linkType;
	document.search.quoteNo.value=quoteNo;		
	document.search.action='/Emirates/mailReport.action';
	document.search.submit();
}
 </script>
  </body>
</html>


         		