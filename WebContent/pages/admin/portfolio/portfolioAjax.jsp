<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<%@ page isELIgnored="false" %>
<div class="row">
	<s:actionerror cssStyle="color: red;"/>
</div>
<s:if test='"portfolioLists".equals(reqFrom)'>
   <div id="portfolioLists">
   	<display:table name="portfolioList" pagesize="10" requestURI="/getListPortfolio.action" class="table" uid="row" id="record">
	<display:setProperty name="paging.banner.one_item_found" value="" />
	<display:setProperty name="paging.banner.one_items_found" value="" />
	<display:setProperty name="paging.banner.all_items_found" value="" />
	<display:setProperty name="paging.banner.some_items_found" value="" />
	<display:setProperty name="paging.banner.placement"	value="bottom" />
	<display:setProperty name="paging.banner.onepage" value="" />
	
	<display:column sortable="true" style="text-align:center;font-size:15px;width:15%;" title="SNO" value="${record_rowNum}"/>
	<s:if test='"A".equals(rep)'>
		<display:column sortable="true" style="text-align:center;font-size:13px;width:35%;"  title="DATE" property="ENTRY_DATE" ></display:column>
	</s:if>
	<s:else>
		<display:column sortable="true" style="text-align:center;font-size:15px;width:35%;"  title="DATE" >
		<a href="#" onclick="getViewLists('${record.ENTRY_DATE}','portfolioLists','<s:property value="rep"/>','<s:property value="productID"/>')" >${record.ENTRY_DATE} </a>	</display:column>
	</s:else>
	<display:column sortable="true" style="text-align:center;font-size:15px;width:35%;" title="NUMBER OF QUOTES" property="COU" />
		</display:table>
		<s:if test='"A".equals(rep)'><br/><input type="button" align="center" value="Save" onclick="pendingExcel();"/></s:if>
		
</div>
</s:if>
<s:elseif test='"viewLists".equals(reqFrom)'>
   Selected Date :<s:property value="viewdate"/>
   <s:hidden name="viewdate"  />
    <display:table name="viewList" pagesize="10" requestURI="/getViewList1Portfolio.action"  class="table" uid="row" id="record">
    <display:setProperty name="paging.banner.one_item_found" value="" />
	<display:setProperty name="paging.banner.one_items_found" value="" />
	<display:setProperty name="paging.banner.all_items_found" value="" />
	<display:setProperty name="paging.banner.some_items_found" value="" />
	<display:setProperty name="paging.banner.placement"	value="bottom" />
	<display:setProperty name="paging.banner.onepage" value="" />
	<display:column></display:column>
	<s:set var="myrow" value="#attr.record"/>
	<s:if test='"c".equals(rep)'>
	<display:column sortable="true" style="text-align:left;font-size:13px;" title="SNO"  value="${record_rowNum}"/>
	<display:column sortable="true" style="text-align:left;font-size:13px;" title="BROKER ORAGANIZATION" property="BROKER_ORGANIZATION" />
	<display:column sortable="true" style="text-align:left;font-size:13px;" title="QUOTE CREATED BY" property="LOGIN_ID" />
	<display:column sortable="true" style="text-align:left;font-size:13px;" title="QUOTE NO" property="QUOTE_NO" />
	<display:column sortable="true" style="text-align:left;font-size:13px;" title="CUSTOMER NAME" property="FIRST_NAME" />
	<display:column sortable="true" style="text-align:right;font-size:13px;" title="PREMIUM" property="PREMIUM" format="{0,number,0.00}"/>
	<display:column sortable="true" style="text-align:left;font-size:13px;" title="VIEW DRAFT">
	   <a href="#" onclick="viewPolicys('${record.QUOTE_NO}','${record.LOGIN_ID}','draft','${record.PRODUCT_ID}','${record.OPEN_COVER_NO}')">Draft</a>
	</display:column>
	</s:if>
	<s:elseif test='"p".equals(rep)'>
	<display:column sortable="true" style="text-align:left;font-size:13px;" title="SNO"  value="${record_rowNum}"/>
	<display:column sortable="true" style="text-align:left;font-size:13px;" title="BROKER ORAGANIZATION" property="BROKER_ORGANIZATION" />
	<display:column sortable="true" style="text-align:left;font-size:13px;" title="QUOTE CREATED BY" property="LOGIN_ID" />
	<display:column sortable="true" style="text-align:left;font-size:13px;" title="POLICY NO" property="POLICY_NO" />
	<display:column sortable="true" style="text-align:left;font-size:13px;" title="CUSTOMER NAME" property="FIRST_NAME" />
	<display:column sortable="true" style="text-align:right;font-size:13px;" title="PREMIUM" property="PREMIUM" format="{0,number,0.00}"/>
	<display:column sortable="true" style="text-align:left;font-size:13px;" title="VIEW PDF">
	   <a href="#" onclick="viewPolicys('${record.POLICY_NO}','${record.LOGIN_ID}','schedule','${record.PRODUCT_ID}','${record.OPEN_COVER_NO}')">View</a>
	</display:column>
	<%--<<display:column sortable="true" style="text-align:left;font-size:13px;" title="TREATY CODE">
	   	<s:if test='%{!"LINKED".equals(#myrow.OPEN_COVER_INT_STATUS)}'>
			<a href="#" onclick="viewPolicys('${record.POLICY_NO}','${record.LOGIN_ID}','treaty','${record.PRODUCT_ID}','${record.OPEN_COVER_NO}')">Edit</a>
		</s:if>	
	</display:column>--%>
	</s:elseif>
	<s:elseif test='"pc".equals(rep)'>
	<display:column sortable="true" style="text-align:left;font-size:13px;" title="SNO"  value="${record_rowNum}"/>
	<display:column sortable="true" style="text-align:left;font-size:13px;" title="BROKER ORAGANIZATION" property="BROKER_ORGANIZATION" />
	<display:column sortable="true" style="text-align:left;font-size:13px;" title="QUOTE CREATED BY" property="LOGIN_ID" />
		<display:column sortable="true" style="text-align:left;font-size:13px;" title="POLICY NO" property="POLICY_NO" />
	<display:column sortable="true" style="text-align:left;font-size:13px;" title="CUSTOMER NAME" property="FIRST_NAME" />
	<display:column sortable="true" style="text-align:right;font-size:13px;" title="PREMIUM" property="PREMIUM"  format="{0,number,0.00}"/>
	<display:column sortable="true" style="text-align:left;font-size:13px;" title="VIEW PDF">
	   <a href="#" onclick="viewPolicys('${record.POLICY_NO}','${record.LOGIN_ID}','schedule','${record.PRODUCT_ID}','${record.OPEN_COVER_NO}')">View</a>
	</display:column>
	<display:column sortable="true" style="text-align:left;font-size:13px;" title="Status">
	<s:hidden name="policyNo[%{#attr.record_rowNum-1}]" value="%{#myrow.POLICY_NO}" />
	<s:radio list="#{'A':'Active','D':'Deactive'}" name="status[%{#attr.record_rowNum-1}]" value="%{#myrow.POLICY_CANCEL_STATUS}"></s:radio>
	</display:column>
	</s:elseif>
	<s:elseif test='"d".equals(rep)'>
	<display:column sortable="true" style="text-align:left;font-size:13px;" title="SNO"  value="${record_rowNum}"/>
	<display:column sortable="true" style="text-align:left;font-size:13px;" title="BROKER ORAGANIZATION" property="BROKER_ORGANIZATION" />
	<display:column sortable="true" style="text-align:left;font-size:13px;" title="QUOTE CREATED BY" property="LOGIN_ID" />
	<display:column sortable="true" style="text-align:left;font-size:13px;" title="POLICY NO" property="POLICY_NO" />
	<display:column sortable="true" style="text-align:left;font-size:13px;" title="CUSTOMER NAME" property="FIRST_NAME" />
	<display:column sortable="true" style="text-align:right;font-size:13px;" title="PREMIUM" property="PREMIUM" format="{0,number,0.00}"/>
	<display:column sortable="true" style="text-align:left;font-size:13px;" title="VIEW PDF">
	   <a href="#" onclick="viewPolicys('${record.POLICY_NO}','${record.LOGIN_ID}','schedule','${record.PRODUCT_ID}','${record.OPEN_COVER_NO}')">View</a>
	</display:column>
	<%--<<display:column sortable="true" style="text-align:left;font-size:13px;" title="TREATY CODE">
	   	<s:if test='%{!"LINKED".equals(#myrow.OPEN_COVER_INT_STATUS)}'>
			<a href="#" onclick="viewPolicys('${record.POLICY_NO}','${record.LOGIN_ID}','treaty','${record.PRODUCT_ID}','${record.OPEN_COVER_NO}')">Edit</a>
		</s:if>	
	</display:column>--%>
	</s:elseif>
	<s:elseif test='"r".equals(rep)'>
	<display:column sortable="true" style="text-align:left;font-size:13px;" title="SNO"  value="${record_rowNum}"/>
	<display:column sortable="true" style="text-align:left;font-size:13px;" title="BROKER ORAGANIZATION" property="BROKER_ORGANIZATION" />
	<display:column sortable="true" style="text-align:left;font-size:13px;" title="QUOTE CREATED BY" property="LOGIN_ID" />
	<display:column sortable="true" style="text-align:left;font-size:13px;" title="QUOTE NO" property="QUOTE_NO" />
	<display:column sortable="true" style="text-align:left;font-size:13px;" title="CUSTOMER NAME" property="FIRST_NAME" />
	<display:column sortable="true" style="text-align:right;font-size:13px;" title="PREMIUM" property="PREMIUM" format="{0,number,0.00}"/>
	</s:elseif>
	<display:column></display:column>
	</display:table>
	<s:if test='"c".equals(rep)'><br/><s:submit align="center" value="Back"  cssClass="btn" onclick="getBack();" /></s:if>
	<s:if test='"p".equals(rep)'><br/><s:submit align="center" value="Back"   cssClass="btn" onclick="getBack();"/></s:if>
	<s:if test='"pc".equals(rep)'><br/><s:submit align="center" value="Back" cssClass="btn"  onclick="getBack();"/><input type="button" onclick="getSubmit();" value="SUBMIT" /></s:if>
	<s:if test='"r".equals(rep)'><br/><s:submit align="center" value="Back" cssClass="btn" onclick="getBack();"/>
	<input type="button" onclick="print('${record.ENTRY_DATE}')" value="PRINT QUOTE" />
	<br/></s:if>
</s:elseif>
<s:elseif test='"paymentsLists".equals(reqFrom)'>
												<display:table name="portfolioList" pagesize="10" requestURI="/getListPortfolio.action" class="table" uid="row" id="record">
														<display:setProperty name="paging.banner.one_item_found" value="" />
														<display:setProperty name="paging.banner.one_items_found" value="" />
														<display:setProperty name="paging.banner.all_items_found" value="" />
														<display:setProperty name="paging.banner.some_items_found" value="" />
														<display:setProperty name="paging.banner.placement"	value="bottom" />
														<display:setProperty name="paging.banner.onepage" value="" />
														
														<display:column sortable="true" style="text-align:center;font-size:15px;width:15%;" title="SNO" value="${record_rowNum}"/>
														<display:column sortable="true" style="text-align:left;font-size:13px;" title="QUOTE_NO" property="QUOTE_NO" />
														<display:column sortable="true" style="text-align:left;font-size:13px;" title="MERCHANT_REFERENCE" property="MERCHANT_REFERENCE" />
														<display:column sortable="true" style="text-align:left;font-size:13px;" title="CUSTOMER NAME" property="CUSTOMER_NAME" />
														<display:column sortable="true" style="text-align:left;font-size:13px;" title="PREMIUM" property="PREMIUM" />
														<display:column sortable="true" style="text-align:center;font-size:13px;width:35%;"  title="DATE" property="REQUEST_TIME" ></display:column>
														<display:column sortable="true" style="text-align:left;font-size:13px;" title="RESPONSE_STATUS" property="RESPONSE_STATUS" />
														<display:column sortable="true" style="text-align:left;font-size:13px;" title="RESPONSE_MESSAGE" property="RESPONSE_MESSAGE" />
														
 														<display:column sortable="true" style="text-align:left;font-size:13px;" title="Update Payment">
	   															<a href="#" onclick="getView('${record.MERCHANT_REFERENCE}','${record.QUOTE_NO}');">Update</a>
													</display:column>
														
														
														
													</display:table>
</s:elseif>


