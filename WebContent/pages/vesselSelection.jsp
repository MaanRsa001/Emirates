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
		<div class="panel panel-primary">
			<s:form name="vesselSelection" id="vesselSelection" theme="simple">
				<div class="panel-body">
					<div class="row">
			   			<div class="col-md-6">
				    		<div class="form-group">
								<s:label key="Vessel Name" />
								<input type="text" name="vesselSearch" value="" onkeyup="return getVesselList('vesselListId',this.value)" class="form-control"/>&nbsp;&nbsp;(Enter atleast 2 chars to search)
							</div>
						</div>
			   			<div class="col-md-3">
				    		<div class="form-group">
				    			<input type="radio" name="select" id="selectNone" checked="checked"/>
				    			<s:label key="None" />
				    		</div>
						</div>
			   			<div class="col-md-3">
				    		<div class="form-group">
				    			<input type="radio" name="select" id="selectOther" />
								<s:label key="Others" />
				    			<input type="text" name="vesselOthers" maxlength="100" class="form-control">
				    		</div>
						</div>
					</div>
					<br/>
					<div class="row">
			   			<div class="col-md-12" id="vesselListId">
			   				<table class="table table-striped table-bordered dt-responsive nowrap" id="VesselSelectionGrid" width="100%" cellspacing="0">
			   					<thead>
									<tr>     				   
										<th>Vessel Name</th>
										<th>Vessel Type</th>
										<th>Vessel Class</th>
										<th>Mfr. Year</th>
									</tr>
								</thead>
								<tbody>	
									<tr>
										<td colspan="4" align="center">Search with vessel Name</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<br/>
					<div class="row" align="center">
						<a data-dismiss="modal" style="cursor:pointer;" class="btn btn-danger btn-oval" >Close</a>
						<a onClick="return fnVesselSubmit()"style="cursor:pointer;"  class="btn btn-success btn-oval" >Submit</a>
					</div>
					<br/>
				</div>
				<input type="hidden" name="vesselId"/>
				<input type="hidden" name="vesselName"/>
			</s:form>
		</div>
		<script>
			  String.prototype.trim = function(){
			  	return this.replace(/^\s+|\s+$/g, "");
			  }
			  if(document.quotation.vesselId.value=='' && document.quotation.vesselName.value!=''){
			  	if(document.vesselSelection.select[1]){
			  		var othersObj=document.vesselSelection.select[1];
			  	}else{
			  		var othersObj=document.vesselSelection.select;
			  	}
			  	othersObj.checked=true;
			  	document.vesselSelection.vesselOthers.value=document.quotation.vesselName.value;
			  }else{
			  	getVesselList('vesselList', document.quotation.vesselName.value);
			  }
		</script>
	</body>
</html>