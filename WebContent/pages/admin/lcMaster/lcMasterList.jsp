<%@ page isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<script language="JavaScript">javascript:window.history.forward(1);</script>
		<link href="${pageContext.request.contextPath}/css/tcal.css" rel="stylesheet" type="text/css" />
		<script>
			function stopRKey(evt) { 
			  var evt = (evt) ? evt : ((event) ? event : null); 
			  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
			  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
			} 
			document.onkeypress = stopRKey;
			
			function forward(broker, bname, action, openCover, custName){
				document.getElementById('broker1').value=broker;
				document.getElementById('brokerName1').value=bname;
				document.getElementById('openCover1').value=openCover;
				document.getElementById('custName1').value=custName;
				document.info.action=action+".action";
				document.info.submit();
			}
			
			function fnBack(value){
				document.info.action=value;
				document.info.submit();
			}
		</script>
		<style >
		.heading {
		    background: #337ab7;
		}
		</style>
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
			  			<s:if test='"lcview".equals(display)'>
							<tr><td class="heading" width="100%"><s:text name="label.exist.policies"/></td></tr>
							<tr><td></td></tr>
							<tr><td align="center"><s:text name="user.broker"/>&nbsp; : &nbsp;<b><s:property value="brokerName"/></b></td></tr>
							<tr><td></td></tr>
							<tr><td>
									<display:table name="lcList" pagesize="20" requestURI="lcViewLC.action" class="table" uid="row" id="record">
										<s:set var="myrow" value="#attr.record"/>
										<display:setProperty name="paging.banner.one_item_found" value="" />
										<display:setProperty name="paging.banner.one_items_found" value="" />
										<display:setProperty name="paging.banner.all_items_found" value="" />
										<display:setProperty name="paging.banner.some_items_found" value="" />
										<display:setProperty name="paging.banner.placement"	value="bottom" />
										<display:setProperty name="paging.banner.onepage" value="" />
										<display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title=" S.No " value="${record_rowNum}"/>
										<display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="Core Application Policy No" property="MISSIPPI_OPENCOVER_NO"/>
										<display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="Open Cover No" property="open_cover_no"/>
										<display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="Customer Name" property="cust_name"/>
										<display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="Open Cover Date" property="inception_date"/>
										<display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="Validity Period" property="expiry_date"/>
										<display:column sortable="true" style="text-align:center;font-size:13px;height:30px;" title="LC Details">
											<s:if test='%{!"NONE".equals(#myrow.LC_NUMBER)}'>
												<a href="#" onclick="forward('<s:property value="broker"/>','<s:property value="brokerName"/>','lcDetailsLC','<s:property value="#myrow.open_cover_no"/>','<s:property value="#myrow.cust_name"/>')">View</a>
											</s:if>
										</display:column>
									</display:table>
							</td></tr>
							<tr><td></td></tr>
							<tr><td align="center">
<%-- 							<s:submit name="back" cssClass="btn" value="Back" action="listLC"/> --%>
							<input type="button" class="btn" value="Back" onclick="fnBack('listLC.action')" />
							</td></tr>
							<tr><td></td></tr>
						</s:if>
						<s:elseif test='"ocview".equals(display)'>
							<tr><td class="heading" width="100%"><s:text name="label.exist.policies"/></td></tr>
							<tr><td></td></tr>
							<tr><td align="center"><s:text name="user.broker"/>&nbsp; : &nbsp;<b><s:property value="brokerName"/></b></td></tr>
							<tr><td></td></tr>
							<tr><td>
									<display:table name="lcList" pagesize="20" requestURI="	`.action" class="table" uid="row" id="record">
										<s:set var="myrow" value="#attr.record"/>
										<display:setProperty name="paging.banner.one_item_found" value="" />
										<display:setProperty name="paging.banner.one_items_found" value="" />
										<display:setProperty name="paging.banner.all_items_found" value="" />
										<display:setProperty name="paging.banner.some_items_found" value="" />
										<display:setProperty name="paging.banner.placement"	value="bottom" />
										<display:setProperty name="paging.banner.onepage" value="" />
										<display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title=" S.No " value="${record_rowNum}"/>
										<display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="Core Application Policy No" property="MISSIPPI_OPENCOVER_NO"/>
										<display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="Open Cover No" property="open_cover_no"/>
										<display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="Customer Name" property="cust_name"/>
										<display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="Open Cover Date" property="inception_date"/>
										<display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="Validity Period" property="expiry_date"/>
										<display:column sortable="true" style="text-align:center;font-size:13px;height:30px;" title="LC Details">
											<s:if test='%{!"NONE".equals(#myrow.LC_NUMBER)}'>
												<a href="#" onclick="forward('<s:property value="broker"/>','<s:property value="brokerName"/>','lcDetailsLC','<s:property value="#myrow.open_cover_no"/>','<s:property value="#myrow.cust_name"/>')">View</a>
											</s:if>
										</display:column>
									</display:table>
							</td></tr>
							<tr><td></td></tr>
							<tr><td align="center">
<%-- 							<s:submit name="back" cssClass="btn" value="Back" action="listLC"/> --%>
							<input type="button" class="btn" value="Back" onclick="fnBack('listLC.action')" />
							
							</td></tr>
							<tr><td></td></tr>
						</s:elseif>
						<s:else>
							<tr><td class="heading" width="100%"><s:text name="label.lc.master.list"/></td></tr>
							<tr><td></td></tr>
							<tr><td>
									<display:table name="lcList" pagesize="" requestURI="" class="table" uid="row" id="record">
										<s:set var="myrow" value="#attr.record"/>
										<display:setProperty name="paging.banner.one_item_found" value="" />
										<display:setProperty name="paging.banner.one_items_found" value="" />
										<display:setProperty name="paging.banner.all_items_found" value="" />
										<display:setProperty name="paging.banner.some_items_found" value="" />
										<display:setProperty name="paging.banner.placement"	value="bottom" />
										<display:setProperty name="paging.banner.onepage" value="" />
										<display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title=" S.No " value="${record_rowNum}"/>
										<display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="Broker Company Name" property="COMPANY_NAME"/>
										<display:column sortable="true" style="text-align:center;font-size:13px;height:30px;" title="Total OpenCovers" format="{0,number,0}">
											<a href="#" onclick="forward('<s:property value="#myrow.LOGIN_ID_1"/>','<s:property value="#myrow.COMPANY_NAME"/>','ocViewLC','','')"><s:property value="#myrow.OPEN_COVER_COUNT"/></a>
										</display:column>
										<display:column sortable="true" style="text-align:center;font-size:13px;height:30px;" title="Total LC Numbers" format="{0,number,0}">
											<s:if test='%{"".equals(#myrow.LC_NUMBER_COUNT) || #myrow.LC_NUMBER_COUNT==null}'>0</s:if>
											<s:else><a href="#" onclick="forward('<s:property value="#myrow.LOGIN_ID_1"/>','<s:property value="#myrow.COMPANY_NAME"/>','lcViewLC','','')"><s:property value="#myrow.LC_NUMBER_COUNT"/></a></s:else>
										</display:column>
									</display:table>
							</td></tr>
						</s:else>
  					</table>
  					<s:hidden name="broker" id="broker1"/>
  					<s:hidden name="brokerName" id="brokerName1"/>
  					<s:hidden name="openCover" id="openCover1"/>
  					<s:hidden name="custName" id="custName1"/>
  					<s:hidden name="from1" id="from1"/>
  					</s:form>
  				</td>
				</tr>
			</table></td></tr>
       </table>
	</body>
</html>