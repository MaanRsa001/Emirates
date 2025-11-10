<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
  <link href="css/styles.css" rel="stylesheet" type="text/css" />
  <link href="css/tcal.css" rel="stylesheet" type="text/css" />
  <script type="text/javascript" src="js/tcal.js"></script>
  <script type="text/javascript" src="js/common.js"></script>
  <script language="JavaScript">javascript:window.history.forward(1);</script>
  </head>
  <body>
<s:iterator value="quotationInfo" status="stat" var="quotation">
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#E5E5E5">
  <tr>
    <td bgcolor="#FFFFFF" style="padding:10px; background:#F8F8F8">
          <s:form id="premiumInfo" name="premiumInfo" method="post" action="" theme="simple">
          	<s:set var="format" value="%{'number.format.'+#session.BrokerDetails.CurrencyDecimal}"></s:set>
              <table width="100%" border="0" cellspacing="0" cellpadding="0">
 				<tr>
		      		<td bgcolor="#FFFFFF"><table width="100%" border="0" cellspacing="0" cellpadding="0">
	            <tr>
                	 <td  style="color:red;"><s:actionerror/></td>
                </tr>
                <tr> 
                    <td class="heading" width="100%">  
                         <table width="100%" >
                      		<tr>
                       			 <td width="22%"><s:label key="premiumInfo.quotation" /></td>
                        		 <td width="25%"><s:label key="premiumInfo.quoteNo" />&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;<s:property value="QUOTE_NUMBER" /></td>
                        		 <td width="25%"><s:label key="premiumInfo.customerName" />&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;<s:property value="CUSTOMER_NAME"/></td>
                                 <td width="25%"><s:label key="premiumInfo.coverStartDate" />&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;<s:property value="POLICY_START_DATE" /></td>
                            </tr>
                         </table>
                     </td>            
                     
                 </tr> 
                  <tr>
                      <td class="bg">
                          <div id="quoteInfo" >
                             <table width="100%" border="1" bordercolor="#F2F2F2" align="center" cellpadding="4" cellspacing="0">
                       			<tr>                           
                                    <td width="30%"><b><s:label key="premiumInfo.formTrans"  /></b></td>
                                    <td width="20%"><s:property value="TRANSPORT_DESCRIPTION"/></td>                            
                            		<td width="30%"><b><s:label key="premiumInfo.coverage" /></b></td>
                              		<td width="20%"><s:property value="COVER_NAME"/></td>                                
                                 </tr>
                                  <tr>
	                         	     <td width="30%"><b><s:label key="premiumInfo.conveyance" /></b></td>
	                                 <td width="20%"> <s:property value="CONVDESC" /></td>   
	                                 <td width="30%"><b><s:label key="premiumInfo.warSrcc" /></b></td>
	                                  <td width="20%"><s:property value="WAR_SRCC" /></td>	   								 
                                  </tr>
                                   <tr>
		                         	   <td width="30%"><b><s:label key="premiumInfo.originatingCountry" /></b></td>
		                               <td width="20%"><s:property value="ORIGIN_COUNTRY" default="nil" /></td>   
		                               <td width="30%"><b><s:label key="premiumInfo.designationCountry" /></b></td>
		                               <td width="20%"><s:property value="DEST_COUNTRY" default="nil" /></td>   
                                   </tr>                             
                                    <tr>
		                         	    <td width="30%"><b><s:label key="premiumInfo.currency" /></b></td>
		                                <td width="20%"><s:property value="CURRENCY_NAME"/>(<s:property value="EXCHANGE_RATE"/>)</td>   
		                                <td width="30%"><b><s:label key="premiumInfo.saleTermForValuation" /></b></td>
		                                <td width="20%"><s:property value="SALES_TERM" default="nil" /></td>   
                               		</tr>   
                                	<tr>
		                         	     <td width="30%"><b><s:label key="premiumInfo.totalInsuredValue" /></b></td>
		                                 <td width="20%"><s:property value="getText(#format,{TOTAL_INSURED})" default="nil" /></td>   
		                                 <td width="30%"><b><s:label key="premiumInfo.equivalent"  />(<s:property value="#session.BrokerDetails.CurrencyAbb"/>)</b></td>
		                                 <td width="20%"><s:property value="getText(#format,{EQUIVALENT})" default="nil" /></td>   
                               		</tr>        
      					
						       </table>
							 </div>
						</td>
					</tr>
					<tr>
					 	<td>
					 		&nbsp;
					 	</td>
					</tr>										  
   				 	<s:if test='Status=="N" || #session.user1 == "admin"'> 							
   						<tr>      			
   							<td class="heading" width="100%"><s:label key="premiumInfo.premiumInfo" /></td>
   						</tr>
   						<tr>
   							<td class="bg">
   								<table width="100%" border="1"  bordercolor="#F2F2F2" align="center" cellpadding="4" cellspacing="0">
	     				   		<tr>
			     				   <td width="25%">&nbsp;</td>
			                        <td width="25%" align="left"><b><s:label key="premiumInfo.marinePremium" />&nbsp;<s:property value="#session.BrokerDetails.CurrencyAbb"/></b></td>
			                        <td width="25%" align="right">
				                         <s:property value="getText(#format,{MARINE_PREMIUM})" default=" "/>      
			                        </td>                          
			                        <td width="25%" align="right"></td>   
	                      		</tr>
	                        	<tr>
			     				   <td width="25%" align="right"><b><s:label key="premiumInfo.add" /></b></td>
	                        	    <td width="25%"><b><s:label key="premiumInfo.warPremium" />&nbsp;<s:property value="#session.BrokerDetails.CurrencyAbb"/></b></td>
	                                <td width="25%" align="right"><s:property value="getText(#format,{WAR_PREMIUM})" /></td>                               
	                                <td width="25%" align="right"><s:property value="getText(#format,{TOTAL_PREMIUM})"/></td>   
	                      	    </tr>
	                      	    <s:if test="#session.user1 == 'admin' || productId != openCover">
		                        	<tr>
		     				   			<td width="25%">&nbsp;</td>
		                         	     <td width="25%">&nbsp;</td>
		                                 <td width="25%"><b><s:label key="premiumInfo.additionalPremium" />&nbsp;<s:property value="#session.BrokerDetails.CurrencyAbb"/></b>
		                                 </td>                               
		                                 <td width="25%" align="right">
			                                 <s:if test="hasActionErrors()">
						                        <s:textfield name="additionalPremium" readonly="%{quoteStatus=='RA' && #session.user1 != 'admin'?'true':'false'}" readonly="true"/>                        
						                     </s:if>
						                     <s:else>
					                       		<s:textfield name="additionalPremium"  value="%{#quotation.ADDITIONAL_PREMIUM}" readonly="true"/>                          
						                     </s:else>
		                                  </td>   
		                      		</tr>
	                      		</s:if>
	                      		<s:else>
	                      			<s:hidden name="additionalPremium"  value="%{#quotation.ADDITIONAL_PREMIUM}"/>
	                      		</s:else>
	                        	<tr>
			     				   <td width="25%" align="right"><b><s:label key="premiumInfo.add" /></b></td>
			                       <td width="25%"><b><s:label key="premiumInfo.policyIssuanceFee" />&nbsp;<s:property value="#session.BrokerDetails.CurrencyAbb"/></b></td>
			                       <td width="25%">
			                       <s:if test='#session.user1 == "admin"'>
			                       		<b><s:label key="premiumInfo.editPolicyIssuanceFee" /><s:radio list="#{'Y':'Yes','N':'No'}" name="editPolicyFee" value="%{editPolicyFee==null?'N':editPolicyFee}" onclick="enable(this.value,'policyFee')"/></b>
			                       </s:if>
			                       </td>                               
			                       <td width="25%" align="right"><s:textfield name="policyFee" id="policyFee" value="%{#quotation.POLICY_ISSUNCE_FEE}" readonly='%{editPolicyFee=="Y"?"false":"true"}'/></td>   
			                    </tr>
	                       		<tr>
			     				   <td width="25%"></td>
			                       <td width="25%"></td>
			                       <td width="25%"><b><s:label key="premiumInfo.totalPremium" />	<s:property value="#session.BrokerDetails.CurrencyAbb"/></b></td>                               
			                       <td width="25%" align="right"><s:textfield name="netPremium"  value="%{#quotation.NET_PREMIUM}" readonly="true"/></td>   
			                    </tr>
	     					</table>
   					</td>
     			</tr>
     			<tr>
     				<td>
     					&nbsp;
     				</td>
     			</tr>
     			<s:if test='Status=="N" || #session.user1 == "admin"'>
	     			<tr> 
	     				<td class="heading" width="100%"><s:label key="premiumInfo.clausesInfo" /></td>
	     			</tr>
	     			<tr>
	     				<td class="bg">
	     					<table width="100%" border="1"  bordercolor="#F2F2F2" align="center" cellpadding="4" cellspacing="0">
			     				<tr>
				     				<s:if test='#session.user1 == "admin"'>
					     				<td colspan="2">
					     					<table width="100%"  border="0" cellspacing="0" cellpadding="0">
											    <tr>
												 	 <td width="41%" align="left">
													 	 <table width="100%"  border="0" cellspacing="0" cellpadding="0" >
														    <tr>
															 	 <td align="center"><b><a style="color:#000000;font-size: 13px;" href="#" onclick="popUp('${pageContext.request.contextPath}/clausesPremium.action?quoteNo=<s:property value="%{#quotation.QUOTE_NUMBER}"/>&openCoverNo=<s:property value="openCoverNo"/>','1000','500');"><s:label key="premiumInfo.editClauses"/></a></b></td>
															 	 <td align="center"><b><a style="color:#000000;font-size: 13px;" href="#" onclick="popUp('${pageContext.request.contextPath}/addClausesPremium.action?quoteNo=<s:property value="%{#quotation.QUOTE_NUMBER}"/>&coverId=<s:property value="COVER_ID"/>&conditionType=1','1000','500');"><s:label key="premiumInfo.addClauses"/></a></b></td>
															 	 <td align="center"><b><a style="color:#000000;font-size: 13px;" href="#" onclick="popUp('${pageContext.request.contextPath}/addClausesPremium.action?quoteNo=<s:property value="%{#quotation.QUOTE_NUMBER}"/>&coverId=<s:property value="COVER_ID"/>&conditionType=2','1000','500');"><s:label key="premiumInfo.addWarClauses"/></a></b></td>
															 	 <td align="center"><b><a style="color:#000000;font-size: 13px;" href="#" onclick="popUp('${pageContext.request.contextPath}/addClausesPremium.action?quoteNo=<s:property value="%{#quotation.QUOTE_NUMBER}"/>&coverId=<s:property value="COVER_ID"/>&conditionType=3','1000','500');"><s:label key="premiumInfo.addExclusions"/></a></b></td>
															 	 <td align="center"><b><a style="color:#000000;font-size: 13px;" href="#" onclick="popUp('${pageContext.request.contextPath}/addClausesPremium.action?quoteNo=<s:property value="%{#quotation.QUOTE_NUMBER}"/>&coverId=<s:property value="COVER_ID"/>&conditionType=4','1000','500');"><s:label key="premiumInfo.addWarranties"/></a></b></td>
										                  	</tr>
									                  	</table>
												  	 </td>
							                  	</tr>
						                  	</table>
					     				</td>
				     				</s:if>
				     				<s:else>
					     				<td width="70%"><s:label key="premiumInfo.viewClausesDesc"/></td>
					     				<td width="30%"><b><a style="color:#000000;font-size: 13px;" href="#" onclick="popUp('${pageContext.request.contextPath}/clausesPremium.action?applicationNo=<s:property value="%{applicationNo}"/>','1000','500');"><s:label key="premiumInfo.viewClauses"/></a></b></td>
				     				</s:else>
			     				</tr>
	     					</table>
	     				</td>
	     			</tr>
	     			<tr>
	     				<td>
	     					&nbsp;
	     				</td>
	     			</tr>    			
                 	</s:if>     				
    				</s:if>
     				<s:elseif test='Status=="Y"'>  
	     				 <tr>
		     				<td class="bg" align="center">
		     				<div style="color:red;font-size: 15px;"><b><s:label key="premiumInfo.referralMsg"/><s:property value="REFERRAL_DESC"/></b></div>
		     				</td>
	     				</tr>   				
     				</s:elseif>
     				<s:if test='#session.user1 == "admin"'>      	
	     				<tr>
	     					<td class="bg" align="center">
	     						<s:label key="premiumInfo.commissionYN"/>&nbsp;<s:radio name="commissionYN" list="#{'YES':'Yes','NO':'No'}" value="%{commissionYN==null?'NO':commissionYN}" onclick="enable(this.value,'commission')"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:label key="premiumInfo.commission"/>&nbsp;&nbsp;&nbsp;<s:textfield name="commission" id="commission" readonly='%{commissionYN=="YES"?"false":"true"}'/>
	     					</td>
	     				</tr>
	     				<tr>
	     					<td class="bg" align="center">
	     						<s:label key="premiumInfo.referralStatus"/>&nbsp;<s:radio name="referralStatus" list="#{'A':'Approve','R':'Reject','N':'None'}" value="%{referralStatus==null?'A':referralStatus}"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:label key="premiumInfo.remarks"/>&nbsp;&nbsp;&nbsp;<s:textarea name="adminRemarks" onkeyup="textLimit(this,'450')"/>
	     					</td>
	     				</tr>
     				</s:if> 
       			 </table>			
     		   </td>
     		</tr>
     		<tr>
     			<td>
     				&nbsp;
     			</td>
     		</tr> 
		      <tr>
			      <td align="center">
			      	<table>
			      		<tr>
	                    	<td>
	                    		&nbsp;
	                    	</td>
	                     	<td colspan="3" align="center">
	                     	<s:submit name="Submit2" type="submit" cssClass="btn" value="Back"  onclick="return update('initSearchReport.action')"/>&nbsp;
	                       	<s:if test='%{quoteStatus!="P"}'>
		                       	<s:submit name="Submit3" type="button" cssClass="btn" value="Edit" onclick="return editQuote()"/>
		                       	&nbsp;
	                       	</s:if>
	                      	<s:submit name="Submit" type="button" cssClass="btn" value="Proceed"  onclick="return update('policyInfoPremium.action')"/></td>
	                     	<td>
	                     	&nbsp;
	                     	</td>
	                    </tr>
	                  </table>
	                </td>
               </tr>              
           </table>  
          <s:hidden name="quoteNo" value="%{#quotation.QUOTE_NUMBER}"/>    
          <s:hidden name="quoteStatus" value="%{quoteStatus}"/>    
          <s:hidden name="applicationNo"/>  
          <s:hidden name="openCoverNo"/>  
          <s:hidden name="loginId" />
          <s:hidden name="searchBy" />
          <s:hidden name="searchValue" />
   		</s:form>  
   	</td>
  </tr>
</table>
</s:iterator>
</body>
    <SCRIPT type="text/javascript">
   function update(actionPath)
    { 
   		document.premiumInfo.action = '${pageContext.request.contextPath}/' + actionPath;
    	document.premiumInfo.submit();
    	return false;
    }
    function editQuote()
	{
		document.premiumInfo.action = "${pageContext.request.contextPath}/editQuoteQuotation.action";
		document.premiumInfo.submit();
	}
  </SCRIPT>  
</html>
