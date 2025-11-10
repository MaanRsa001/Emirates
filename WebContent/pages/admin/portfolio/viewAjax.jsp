<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<%@ page isELIgnored="false" %>
<s:if test='"viewLists".equals(reqFrom)'>
<td width="50%" align="center" id="nextList">

	<display:table name="viewList" pagesize="10" requestURI="/getViewListPortfolio.action" class="table" uid="row" id="record">
	<display:setProperty name="paging.banner.one_item_found" value="" />
	<display:setProperty name="paging.banner.one_items_found" value="" />
	<display:setProperty name="paging.banner.all_items_found" value="" />
	<display:setProperty name="paging.banner.some_items_found" value="" />
	<display:setProperty name="paging.banner.placement"	value="bottom" />
	<display:setProperty name="paging.banner.onepage" value="" />
	
	<display:column sortable="true" style="text-align:left;font-size:13px;" title="NUMBER OF QUOTES" property="APPLICATION_ID" />
	<display:column sortable="true" style="text-align:left;font-size:13px;" title="NUMBER OF QUOTES" property="LOGIN_ID" />
	<display:column sortable="true" style="text-align:left;font-size:13px;" title="NUMBER OF QUOTES" property="QUOTE_NO" />
	<display:column sortable="true" style="text-align:left;font-size:13px;" title="NUMBER OF QUOTES" property="REMARKS" />
	<display:column sortable="true" style="text-align:left;font-size:13px;" title="NUMBER OF QUOTES" property="FIRST_NAME" />
	<display:column sortable="true" style="text-align:left;font-size:13px;" title="NUMBER OF QUOTES" property="PREMIUM" />
	
	</display:table>
	</td>
</s:if>