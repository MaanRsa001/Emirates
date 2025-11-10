<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
<script>
function fnBack()
{
document.form1.action="openPortfolio.action";
document.form1.submit();
}
</script>
	</head>
	<body>
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0"> 
			<tr>
		    	<td height="5"></td>
		  	</tr>
		  	<tr>
		    	<td><table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#E5E5E5">
		      		<tr>
		        		<td bgcolor="#FFFFFF" style="padding:10px; background:#F8F8F8">
								<s:form name="form1" theme="simple">
							 		<table width="100%" align="center">
								        <tr>
								            <td class="heading" width="100%"><s:text name="portfolio.view"/></td>
								        </tr>
								 		<tr>
								        	<td height="10%">
								        	<table>
								        	<tr><td colspan="4">&nbsp;</td></tr>
								        	<tr>
								        	<td><s:text name="from.date"/></td>
								        	<td>2013-09-07</td>
								        	<td><s:text name="to.date"/></td>
								        	<td>2013-09-10</td>
								        	</tr>
								        	<tr><td colspan="4">&nbsp;</td></tr>
								        									        	</table>
								        		
								        	</td>
							        	</tr>
							      		<tr>
							      			<td>
										      	<table>
										      	<!--<display:table name="brokerList" pagesize="10" requestURI="/getABListBrokerMgm.action" class="table" uid="row" id="record">
												<display:setProperty name="paging.banner.one_item_found" value="" />
												<display:setProperty name="paging.banner.one_items_found" value="" />
												<display:setProperty name="paging.banner.all_items_found" value="" />
												<display:setProperty name="paging.banner.some_items_found" value="" />
												<display:setProperty name="paging.banner.placement"	value="bottom" />
												<display:setProperty name="paging.banner.onepage" value="" />
												<display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title=" S.NO " value="${record_rowNum}"/>
												
												<display:column sortable="true" style="text-align:left;font-size:13px;"  title="DATE" property="missippi_id" />
												<display:column sortable="true" style="text-align:left;font-size:13px;" title="NUMBER OF QUOTES" property="LOGIN_ID" />
												
												</display:table>
												-->
										      	</table>
											</td>
										</tr>
										<tr>
										<td>
										<table align="center" width="60%" border="1">
										
										<tr>
										<th>S.NO</th>
										<th>Date</th>
										<th>Quotes</th>
										</tr>
										<tr>
										<td>1</td>
										<td><a href="byDatePortfolio.action">2013-09-17</a></td>
										<td>1</td>
										</tr>
										
										</table></td>
										</tr>
										<tr align="center"><td><input name="back" type="button" class="btn" onclick="fnBack()" value="Back" style ="cursor:hand"/></td></tr>
									</table>
								</s:form>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	
	</body>
</html>



 
