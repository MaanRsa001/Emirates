<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
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
		<script type="text/javascript">
		jQuery(function ($) {
				try {
					var table = $('#customerSelectionGrid').DataTable({
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
		<div class="col-md-12">
		   <table class="table table-striped table-bordered dt-responsive nowrap" id="customerSelectionGrid" width="100%" cellspacing="0">
		   		<thead>
					<tr>     				   
						<th></th>
						<th><b>Customer Name</b></th>
						<th><b>P.O.Box</b></th>
						<th><b>City</b></th>
						<th><b>Email</b></th>
						<th><b>Core Application Code</b></th>
					</tr>
				</thead>
				<tbody>			
					<s:iterator value="customerSelection" var="customerdetail" status="stat">
						<tr>   
						 	<td><input type="radio" name="selects" onclick="customerDetail('<s:property value="%{#customerdetail.TITLE}"/>','<s:property value="%{#customerdetail.ADDRESS1}"/>','<s:property value="%{#customerdetail.ADDRESS2}"/>','<s:property value="%{#customerdetail.MOBILE}"/>','<s:property value="%{#customerdetail.EMIRATE}"/>','<s:property value="%{#customerdetail.POBOX}"/>','<s:property value="%{#customerdetail.FIRST_NAME}"/>','<s:property value="%{#customerdetail.MISSIPPI_CUSTOMER_CODE}"/>','<s:property value="%{#customerdetail.CUSTOMER_ID}"/>','<s:property value="%{#customerdetail.VAT_REG_NO}"/>','<s:property value="%{#customerdetail.CUST_VAT_YN}"/>','<s:property value="%{#customerdetail.EMAIL}"/>','<s:property value="%{#customerdetail.CUST_AR_NO}"/>')"/></td>   				  
							<td><s:property value="FIRST_NAME" /></td> 
							<td><s:property value="POBOX"  /></td>
							<td><s:property value="CITY_NAME" /></td>
							<td><s:property value="EMAIL" /></td>
							<td><s:property value="MISSIPPI_CUSTOMER_CODE" /></td>
						</tr>
					</s:iterator>
				</tbody>
		   </table>
		</div>
	</body>
</html>
