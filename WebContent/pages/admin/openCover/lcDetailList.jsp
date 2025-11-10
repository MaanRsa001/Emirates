<%@ page isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<script language="JavaScript">javascript:window.history.forward(1);</script>
        <link href="${pageContext.request.contextPath}/css/displaytag.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/css/tcal.css" rel="stylesheet" type="text/css" />
		<script>
			function stopRKey(evt) { 
			  var evt = (evt) ? evt : ((event) ? event : null); 
			  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
			  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
			} 
			document.onkeypress = stopRKey;
		</script>
	</head>
	<body>
		<table width="100%">
          <tr><td height="5"></td></tr>
		  <tr>
		    <td><table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#E5E5E5">
		      <tr>
		        <td bgcolor="#FFFFFF" style="padding:10px; background:#F8F8F8">
  					<s:form id="info" name="info" theme="simple">
			  		<table width="100%" align="center" width="80%">
			  			<tr><td></td></tr>
						<tr><td class="heading" width="100%"><s:text name="label.lc.detail"/></td></tr>
						<tr><td></td></tr>
						<tr><td><table width="100%"><tr><td width="50%"><s:text name="label.cims.policy"/> : <s:property value="policynumber"/></td>
													<td width="50%"><s:text name="label.cims.proposal"/> : <s:property value="proposalNo"/></td>
						</tr></table></td></tr>
						<tr><td></td></tr>
						<tr><td>
							<div id="lclist">
								<display:table name="lcList" pagesize="10" requestURI="lcDetailOC.action" class="table" uid="row" id="record" excludedParams="from1">
									<display:setProperty name="paging.banner.one_item_found" value="" />
									<display:setProperty name="paging.banner.one_items_found" value="" />
									<display:setProperty name="paging.banner.all_items_found" value="" />
									<display:setProperty name="paging.banner.some_items_found" value="" />
									<display:setProperty name="paging.banner.placement"	value="bottom" />
									<display:setProperty name="paging.banner.onepage" value="" />
									<display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="LC NUMBER" property="LC_NUMBER"/>
									<display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="BANK NAME" property="bank_name"/>
									<display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="LC AMOUNT" property="LC_AMOUNT"/>
									<display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="CURRENCY TYPE" property="currency_name"/>
									<display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="LC DATE" property="LC_DATE"/>
									<display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="EXPIRY DATE" property="EXPIRY_DATE"/>
								</display:table>
							</div>
						</td></tr>
  						</table>
  					</s:form>
  				</td>
				</tr>
			</table></td></tr>
       </table>
	</body>
</html>