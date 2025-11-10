<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>       
        <meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page"> 
    </head>

<body> 
<s:form name="declarationPolicy" theme="simple">
	<table width="90%" border="0" cellspacing="0" cellpadding="2" align="center" bgcolor="#FFFFFF">	   
  		<tr>
  			<td>&nbsp;</td>
  		</tr>  		
  		<tr>
  			<td class="heading" width="100%">
  			   <s:label key=""/>	      		  
     		</td>
  		</tr>  		
    	<tr>
    	     <td class="bg" width="100%">    	     	   	     	     
					<display:table name="declarationPolicy" pagesize="10"  requestURI="${pageContext.request.contextPath}/declarationReport.action"
					class="table" uid="row" id="record" style="width:100%; align:center; " cellpadding="1" cellspacing="1" >
					<display:setProperty name="paging.banner.one_item_found" value="" />
					<display:setProperty name="paging.banner.one_items_found" value="" />
					<display:setProperty name="paging.banner.all_items_found" value="" />
					<display:setProperty name="paging.banner.some_items_found" value="" />
					<display:setProperty name="paging.banner.placement"	value="bottom" />
					<display:setProperty name="paging.banner.onepage" value="" />
						<display:column sortable="true" style="text-align:center;width:6%" title="S.No" value="${record_rowNum}"/> 
						<display:column sortable="true" style="text-align:center;width:12%" title="Policy No." property="POLICY_NO"/>
						<display:column sortable="true" style="text-align:center;width:9%" title="Quote No." property="QUOTE_NO"/>
						<display:column sortable="true" style="text-align:center;width:10%" title="Core Application Policy No." property="OPEN_COVER_NO"/>
						<display:column sortable="true" style="text-align:center;width:15%" title="Customer Name" property="CUSTOMER_NAME"/>
						<display:column sortable="true"	style="text-align:center;width:10%" title="Quotation Date"	property="QUOTE_DATE"/>
						<display:column sortable="true"	style="text-align:right;width:8%" title="Premium"	property="PREMIUM" format="{0,number,##,##,###.00}"/>
						<display:column sortable="true"	style="text-align:center;width:8%" title="Schedule" >
							<a href="#" onclick="return popUp('${pageContext.request.contextPath}/Copy of information.jsp?policyMode=scheduleMultiple&policynumber=${record.POLICY_NO}&loginid=<s:property value="%{#session.user}"/>','1000','500')">Schedule</a>
						</display:column>
						<display:column sortable="true"	style="text-align:center;width:8%" title="Debit Note" >
							<c:if test="${record.DEBIT_NOTE_NO != null}">
								<a href="#" onclick="return popUp('${pageContext.request.contextPath}/Copy of information.jsp?policyMode=debitMultiple&policynumber=${record.POLICY_NO}&loginid=<s:property value="%{#session.user}"/>','1000','500')">Debit</a>
							</c:if>
						</display:column>
						<display:column sortable="true"	style="text-align:center;width:8%" title="Credit Note" >
							<c:if test="${record.CREDIT_NOTE_NO != null}">
								<a href="#" onclick="return popUp('${pageContext.request.contextPath}/Copy of information.jsp?policyMode=creditMultiple&policynumber=${record.POLICY_NO}&loginid=<s:property value="%{#session.user}"/>','1000','500')">Credit</a>
							</c:if>
						</display:column>					
					</display:table>				
    	     </td>
    	</tr>    	
	    	<tr align="center">
				<td class="text" >
					<s:submit type="button" name="close"  key="Back" cssClass="btn" onclick="forward('T')"/>&nbsp;				
				</td>
			</tr>		
   </table>
 </s:form>
</body>
</html>


