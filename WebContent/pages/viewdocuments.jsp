<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<%
  String path = request.getContextPath();
%>
<html>
<head>
    <title>Emirates</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<link href="<%=path%>/css/style.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/cssbootstrap/bootstrap.min.css" rel="STYLESHEET" type="text/css">
	<link href="${pageContext.request.contextPath}/cssbootstrap/footable-0.1.css" rel="STYLESHEET" type="text/css">
</head>
<body>
<form action="" name="document1" id="document1">
		<table width="100%"  border="0" cellspacing="1" cellpadding="0">
            <tr>
              <td align="center" >
               <table class="footable" style="width:100%;margin:0 auto;" id="lcDoctable">
	 				<thead>
	 					<tr>
						  <th width="25%">Documents</th>
						  <th width="25%">Remarks</th>
						  <th width="25%">Download <br></th>
	 					</tr>
	 				</thead>
				   	 <tbody>
				   	 <s:if test="allDocumentsList.size()>0">
						<s:iterator value="allDocumentsList" var="lcfile">
							<s:set var="lcDoc" value="1"/>
							<tr>
								<td><s:property value="%{#lcfile.ORIGINAL_FILE}"/><s:hidden name="hiddencheque" id="hiddencheque" value="%{#lcfile.ORIGINAL_FILE}"></s:hidden></td>
									<td><s:property value="%{#lcfile.remarks}"/></td>
									<td>
										<a href="#" class="btn btn-sm btn-info" onclick="downloadDoc('downloadLcFileReport.action','<s:property value="%{#lcfile.ORIGINAL_FILE}"/>','<s:property value="%{#lcfile.file_Name}"/>')">
										<i class="glyphicon glyphicon-save"></i></a>
									</td>
							</tr>
						</s:iterator>
					</s:if>
					<s:else>
						<tr><td colspan="3">No Documents Found</td></tr>
					</s:else>
						<s:hidden name="lcFileName" id="lcFileName"/>
						<s:hidden name="downloadFileName" id="downloadFileName"/>
					 </tbody>	
				</table>
			</td>
		</tr>
		<tr align="center">
			<td>
				<img src="<%=path%>/images/Back.jpg" onclick="window.close()" style="cursor:hand">
			</td>
		</tr> 
	</table>
</form>
<script type="text/javascript">
function downloadDoc(val,downloadFileName,fileName1){
	document.getElementById("downloadFileName").value=downloadFileName;
	document.getElementById("lcFileName").value=fileName1;
	document.document1.action = "${pageContext.request.contextPath}/"+val;
	document.document1.submit();
}
</script>
</body>
</html>
