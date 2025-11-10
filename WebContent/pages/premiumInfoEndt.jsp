<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
	<a onmouseover="TagToTip('premiumList',LEFT, true)" onmouseout="UnTip()"><img src="<%=request.getContextPath()%>/images/Help.jpg" width="20" height="20"/> </a>
	
	
	<div class="container-fluid" id="premiumList" style="display: none">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<div class="row">
       				<div class="col-md-12 col-xs-12">
			         	<h4><s:text name="premiumInfo.endtPremium" /></h4>
			         </div>
    			</div>
			</div>
			<div class="panel-body">
				<div class="row">
	    			<div class="col-md-12">
	    				<table class="table">
	    					<thead>
			      				<tr>
			      					<th scope="col"><s:label key="premiumInfo.endt" /></th>
			      					<th scope="col"><s:label key="premiumInfo.marinePremium" />&nbsp;<s:property value="#session.BrokerDetails.CurrencyAbb"/></th>
			      					<th scope="col"><s:label key="premiumInfo.warPremium" />&nbsp;<s:property value="#session.BrokerDetails.CurrencyAbb"/></th>
			      					<th scope="col"><s:label key="premiumInfo.additionalPremium" />&nbsp;<s:property value="#session.BrokerDetails.CurrencyAbb"/></th>
			      					<th scope="col"><s:label key="premiumInfo.policyIssuanceFee" />&nbsp;<s:property value="#session.BrokerDetails.CurrencyAbb"/></th>
			      					<th scope="col"><s:label key="premiumInfo.totalPremium" />&nbsp;<s:property value="#session.BrokerDetails.CurrencyAbb"/></th>
			      				</tr>
	    					</thead>
	    					<s:set var="format" value="%{'number.format.'+#session.BrokerDetails.CurrencyDecimal}"></s:set>
							<s:set value="endtPremiumInfo" var="endtPremiumInfo"></s:set>
	    					<tbody>
		      					<tr>
		      						<td scope="row"><s:label key="premiumInfo.endt.prev" /></td>
									<td align="right"><s:property value="getText(#format,{#endtPremiumInfo.MARINE_PREMIUM_PREV})"/></td>
									<td align="right"><s:property value="getText(#format,{#endtPremiumInfo.WAR_PREMIUM_PREV})"/></td>
									<td align="right"><s:property value="getText(#format,{#endtPremiumInfo.EXCESS_PREMIUM_PREV})"/></td>
									<td align="right"><s:property value="getText(#format,{#endtPremiumInfo.POLICY_FEE_PREV})"/></td>
									<td align="right"><s:property value="getText(#format,{#endtPremiumInfo.PREMIUM_PREV})"/></td>
		      					</tr>
								<tr>
									<td scope="row"><s:label key="premiumInfo.endt.actual" /></td>
									<td align="right"><s:property value="getText(#format,{#endtPremiumInfo.MARINE_PREMIUM})"/></td>
									<td align="right"><s:property value="getText(#format,{#endtPremiumInfo.WAR_PREMIUM})"/></td>
									<td align="right"><s:property value="getText(#format,{#endtPremiumInfo.EXCESS_PREMIUM})"/></td>
									<td align="right"><s:property value="getText(#format,{#endtPremiumInfo.POLICY_FEE})"/></td>
									<td align="right"><s:property value="getText(#format,{#endtPremiumInfo.PREMIUM})"/></td>
								</tr>
								<tr>
									<td scope="row"><s:label key="premiumInfo.endt.diff" /></td>
									<td align="right"><s:property value="getText(#format,{#endtPremiumInfo.MARINE_PREMIUM_DIFF})"/></td>
									<td align="right"><s:property value="getText(#format,{#endtPremiumInfo.WAR_PREMIUM_DIFF})"/></td>
									<td align="right"><s:property value="getText(#format,{#endtPremiumInfo.EXCESS_PREMIUM_DIFF})"/></td>
									<td align="right"><s:property value="getText(#format,{#endtPremiumInfo.POLICY_FEE_DIFF})"/></td>
									<td align="right"><s:property value="getText(#format,{#endtPremiumInfo.PREMIUM_DIFF})"/></td>
								</tr>
			      			</tbody>
	    				</table>
	    			</div>
	    		</div>
	    	</div>
		</div>
	</div>
