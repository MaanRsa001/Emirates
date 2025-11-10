<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="policyList !=null && policyList.size()>0">
  	<table class="table table-striped table-bordered dt-responsive nowrap" id="record" width="100%" cellspacing="0">
		<thead>
			<tr>
				<th scope="col">S.No</th>
				<th scope="col">Quote No.</th>
				<th scope="col">Customer Name</th>
				<th scope="col">Quotation Date</th>
				<th scope="col">Policy Start Date</th>
				<th scope="col">Policy Number</th>
				<th scope="col">Status</th>
				<th scope="col">View / Edit</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="policyList" var="result" status="stat">
				<tr>
					<td><s:property value="#stat.index+1"/></td>
					<td><s:property value="QUOTE_NO"/></td>
					<td><s:property value="CUSTOMER_NAME"/></td>
					<td><s:property value="QUOTATION_DATE"/></td>
					<s:if test='"policyNo".equals(searchBy)'>
						<td><s:property value="POLICY_START_DATE"/></td>
					</s:if>
					<s:else>
						<td><s:property value="POLICY_DATE"/></td>
					</s:else>
					<td><s:property value="POLICY_NO"/></td>
					<s:if test='"policyNo".equals(searchBy)'>
						<td>Portfolio</td>
					</s:if>
					<s:else>
						<td><s:property value="STATUS_TYPE_NAME"/></td>
					</s:else>
					<td>
						<s:if test='"policyNo".equals(searchBy) || "P".equals(STATUS_TYPE)'>
							<a class="btn btn-success btn-oval" onclick="javascript:popUp('${pageContext.request.contextPath}/Copyofinformation.jsp?policyMode=schedule&policynumber=<s:property value="POLICY_NO"/>&loginid=<s:property value="loginId"/>',1000,500);">Schedule</a>
						</s:if>
						<s:elseif test='"QE".equals(STATUS_TYPE) || "RA".equals(STATUS_TYPE) || "RU".equals(STATUS_TYPE)'>
							<a class="btn btn-success btn-oval" onclick="editQuote('<s:property value="APPLICATION_NO"/>', '<s:property value="QUOTE_NO"/>','<s:property value="STATUS_TYPE"/>','<s:property value="CUSTOMER_ID)"/>');">Edit</a>
						</s:elseif>
						<s:elseif test='"QL".equals(STATUS_TYPE)'>
							<a class="btn btn-success btn-oval" onclick="sentMail('','ACTIVE','<s:property value="QUOTE_NO"/>');">Active</a>
						</s:elseif>
						<s:elseif test='"L".equals(STATUS_TYPE) || "RR".equals(STATUS_TYPE)'>
							<a class="btn btn-success btn-oval" onclick="view('<s:property value="APPLICATION_NO"/>','<s:property value="QUOTE_NO"/>','<s:property value="productId"/>');">View</a>
						</s:elseif>
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</s:if>
<s:else>
	<h5>No Data Found</h5>
</s:else>