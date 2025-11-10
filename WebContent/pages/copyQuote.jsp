<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1" isELIgnored="false"%>
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
		<meta http-equiv="description" content="Copy Quote"> 				
    </head>

<body> 
<s:if test="display=='init'">
	<s:form name="copyCode" theme="simple" action="searchCopyQuote.action">
		<div class="container-fluid">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<div class="row">
	    				<div class="col-md-12">
	    					<h4><s:text name="copyQuote.copyQuote" /></h4>
	    				</div>
	    			</div>
				</div>
				<div class="panel-body">
					<div class="row">
						<s:actionerror cssStyle="color:red;"/>
					</div>
					<div class="row">
						<div class="col-md-4">
							<s:label key="copyQuote.searchBy" />
							<s:select name="searchType" id="searchType" cssClass="form-control" list="#{'policyNo':'Policy No','quoteNo':'Quote No','custName':'Customer Name'}"  headerKey="" headerValue="Select" />
						</div>
						<div class="col-md-4">
							<s:label key="copyQuote.enterDataForSearch"/>
							<s:textfield name="searchValue" cssClass="form-control"/>
						</div>
						<div class="col-md-4" align="center">
							<s:submit type="button" name="Go"  key="copyQuote.go" cssClass="btn btn-success btn-oval" />
						</div>
					</div>
				</div>
			</div>
		</div>
	</s:form>
</s:if>
<s:elseif test="display=='search'">
	<s:form name="copyCodeSearch" theme="simple" action="copyCopyQuote.action">
		<div class="container-fluid">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<div class="row">
	    				<div class="col-md-12">
	    					<h4><s:text name="copyQuote.copyQuote" /></h4>
	    				</div>
	    			</div>
				</div>
				<div class="panel-body">
					<div class="row">
						<s:actionerror cssClass="color:red"/>
					</div>
					<div class="row">
						<div class="col-md-12" align="center">
							<s:if test="searchResult.size > 0">
								<display:table name="searchResult" pagesize="10" requestURI="${pageContext.request.contextPath}/searchCopyQuote.action"
									class="table" uid="row" id="record" style="width:95%; align:center; " cellpadding="1" cellspacing="1">
									<display:setProperty name="paging.banner.one_item_found" value="" />
									<display:setProperty name="paging.banner.one_items_found" value="" />
									<display:setProperty name="paging.banner.all_items_found" value="" />
									<display:setProperty name="paging.banner.some_items_found" value="" />
									<display:setProperty name="paging.banner.placement"	value="bottom" />
									<display:setProperty name="paging.banner.onepage" value="" />
									<display:column sortable="true" style="text-align:left;width:5%" title="S.No" value="${record_rowNum}"/>
									<display:column sortable="true" style="text-align:center;width:2%" title="&nbsp; ">
									<input type="radio" name="selects"onclick="getQuoteNo('${record.QUOTE_NO}')"/>				
									</display:column>					
									<display:column sortable="true" style="text-align:center;width:10%" title="Quote Number" property="QUOTE_NO"/>
									<display:column sortable="true"	style="text-align:center;width:16%" title="Customer Name" property="FIRST_NAME"/>
									<display:column sortable="true"	style="text-align:right;width:10%" title="Premium" property="PREMIUM" format="{0,number,##,##,###.00}"/>					
								</display:table>
								<s:hidden name="searchType"/>
								<s:hidden name="searchValue"/>  
							</s:if>
							<s:else>
								<h5><s:label key="copyQuote.notfoundmsg"/></h5>
							</s:else>
						</div>
					</div>
					<br/>
					<div class="row" align="center">
						<s:submit type="button" name="close"  key="Cancel" cssClass="btn btn-danger btn-oval" onclick="backButtonAction();"/>
						<s:if test="searchResult.size > 0">
							<s:submit type="submit" name="submit" key="Submit" cssClass="btn btn-success btn-oval"/>
						</s:if>
					</div>
				</div>
			</div>
		</div>
		<s:hidden name="copyQuoteValue"/>
	</s:form>
</s:elseif>
<s:elseif test="display=='copyquote'">
	<s:form name="copyCode" theme="simple" action="redirectCopyQuote.action">
		<div class="container-fluid">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<div class="row">
	    				<div class="col-md-12">
	    					<h4><s:text name="copyQuote.copyQuote" /></h4>
	    				</div>
	    			</div>
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-md-12" align="center">
							<h5><s:property value="msg" /></h5>
						</div>
					</div>
					<br/>
					<div class="row" align="center">
						<s:submit type="submit" name="submit" key="copyQuote.proceed" cssClass="btn btn-success btn-oval"/>
					</div>
				</div>
			</div>
		</div>
		 <s:hidden name="searchCriteria"/>
	</s:form>
</s:elseif>
 <script type="text/javascript">
 function setDefault(obj)
 { 
 	var value=obj.value;  
 	var productCode='<s:text name="OPEN_COVER"/>';
 	var session_productCode='<s:property value="#session.product_id"/>'
 	if(value=='policyNo' && session_productCode == productCode )
 	{  
 		document.getElementById('openCover_No').value='<s:property value="#session.openCoverNo"/>'
 	    document.getElementById('openCover').style.display='block';  	
 	    document.getElementById('oneOff').style.display='none';     		
 	}else{
 		 document.getElementById('oneOff').style.display='block';   
 		  document.getElementById('openCover').style.display='none';   
 	}
 }
 function getQuoteNo(obj)
 { 
 	document.copyCodeSearch.copyQuoteValue.value=obj; 	
 }
 function backButtonAction()
 {
 	document.copyCodeSearch.action='${pageContext.request.contextPath}/initCopyQuote.action'
 	document.copyCodeSearch.submit();
 }
 </script>
  </body>
</html>


         		