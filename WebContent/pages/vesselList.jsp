<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
					var table = $('#VesselSelectionGrid').DataTable({
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
		<table class="table table-striped table-bordered dt-responsive nowrap" id="VesselSelectionGrid" width="100%" cellspacing="0">
 			<thead>
				<tr>
					<th></th>
					<th>Vessel Name</th>
					<th>Vessel Type</th>
					<th>Vessel Class</th>
					<th>Mfr. Year</th>
				</tr>
			</thead>
			<tbody>	
				<s:iterator value="vesselList" var="vesselList" status="stat">
					<tr>
						<td><input type="radio" name="select" onclick="selectVessel('<s:property value="VESSEL_ID" />','<s:property value="VESSEL_NAME" />')" value='<s:property value="VESSEL_ID" />' title='<s:property value="VESSEL_NAME" />'/></td>
						<td><s:property value="VESSEL_NAME" /></td>
						<td><s:property value="VESSEL_TYPE" /></td>
						<td><s:property value="VESSEL_CLASS" /></td>
						<td><s:property value="MANUFACTURE_YEAR" /></td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</body>
</html>