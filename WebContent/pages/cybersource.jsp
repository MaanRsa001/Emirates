<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<link href="css/styles.css" rel="stylesheet" type="text/css" />
  <link href="css/tcal.css" rel="stylesheet" type="text/css" />
  <link rel="stylesheet" type="text/css" href='${pageContext.request.contextPath}/assets1/bootstrap/css/bootstrap.min.css'>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>	
  <script type="text/javascript" src='${pageContext.request.contextPath}/assets1/plugins/jquery/jquery-3.7.1.min.js'></script>
   <script type="text/javascript" src='${pageContext.request.contextPath}/assets1/bootstrap/js/bootstrap.min.js'></script>
   <style>
  .table-striped-custom tbody tr:nth-child(odd) {
    background-color: #f1f8ff;
  }
  .table-striped-custom tbody tr:nth-child(even) {
    background-color: #ffffff;
  }
  .custom-header {
    background-color: #0d6efd;
    color: white;
    padding: 10px;
    font-weight: bold;
  }
  .highlight-cell {
    background-color: #e0f7fa;
    font-weight: bold;
  }
  .premium-section {
    margin-top: 2rem;
    border-radius: 10px;
    overflow: hidden;
    box-shadow: 0 0 10px rgba(0,0,0,0.1);
  }
</style>


<s:if test='%{paymentInformation.size()==0}'><br/><br/>
<br/>	 <div class="panel panel-danger text-center">
  <div class="panel-heading">
    <h2 class="panel-title" style="font-size: 28px;">Link Expired!!</h2>
  </div>
  <div class="panel-body">
    <div class="row">
      <div class="col-md-12">
        <p class="text-danger lead" style="margin-top: 15px;">
          Your link has expired. Please generate a new one and try again.
        </p>
      </div>
    </div>
  </div>
</div>
	 
</s:if>
<s:else>
<s:iterator value="paymentInformation" var="pay">
<s:form id="premiumInfo" name="premiumInfo" method="post" action="makePayPayment.do" theme="simple">

<s:if test='"view".equals(reqFrom) && "PENDING".equals(#pay.RESPONSE_STATUS)'>
<div class="container premium-section">    
<s:iterator value="quotationInfo" status="stat" var="quotation">

<s:set var="format" value="%{'number.format.2'}"/>
				<div class="panel panel-primary">
					<div class="panel-heading">
						<div class="row">
				             <div class="col-md-3"  id="plus" >
					          	<h4>&nbsp;&nbsp;<s:text name="premiumInfo.quotation" /></h4>
					         </div>					        
					         <div class="col-md-3">
					          	<h5><s:text name="premiumInfo.quoteNo" />&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;<s:property value="QUOTE_NUMBER" /></h5>
					         </div>
					         <div class="col-md-3">
					          	<h5><s:text name="premiumInfo.customerName" />&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;<s:property value="CUSTOMER_NAME"/></h5>
					         </div>
					         <div class="col-md-3">
					          	<h5><s:text name="premiumInfo.coverStartDate" />&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;<s:property value="POLICY_START_DATE" /></h5>
					         </div>
		    			</div>
					</div>
					<div class="panel-body" id="quoteInfo"  >
						<ul class="list-group">
		                    <li class="list-group-item list-group-item-default">
		                      <div class="row">
					             <div class="col-md-4">
						          	<s:label key="premiumInfo.formTrans"  />&nbsp;&nbsp;:&nbsp;&nbsp;<s:property value="TRANSPORT_DESCRIPTION"/>
						         </div>
						         <div class="col-md-4">
						          	<s:label key="premiumInfo.coverage" />&nbsp;&nbsp;:&nbsp;&nbsp;<s:property value="COVER_NAME"/>
						         </div>
					             <div class="col-md-4">
						          	<s:label key="premiumInfo.conveyance" />&nbsp;&nbsp;:&nbsp;&nbsp;<s:property value="CONVDESC" />
						         </div>
					          </div>
		                    </li>
		                    <li class="list-group-item list-group-item-default">
		                      <div class="row">
					             <div class="col-md-4">
						          	<s:if test='%{"3".equals(MODE_OF_TRANSPORT)}'>
                                 		<s:label key="quotation.Srcc" />
									</s:if>
									<s:else>
										<s:label key="premiumInfo.warSrcc" />
									</s:else>&nbsp;&nbsp;:&nbsp;&nbsp;<s:property value="WAR_SRCC" />
						         </div>
						         <div class="col-md-4">
						          	<s:label key="premiumInfo.originatingCountry" />&nbsp;&nbsp;:&nbsp;&nbsp;<s:property value="ORIGIN_COUNTRY" default="nil" />
						         </div>
					             <div class="col-md-4">
						          	<s:label key="premiumInfo.designationCountry" />&nbsp;&nbsp;:&nbsp;&nbsp;<s:property value="DEST_COUNTRY" default="nil" />
						         </div>
					          </div>
		                    </li>
		                    <li class="list-group-item list-group-item-default">
		                      <div class="row">
					             <div class="col-md-4">
						          	<s:label key="premiumInfo.currency" />&nbsp;&nbsp;:&nbsp;&nbsp;<s:property value="CURRENCY_NAME"/>(<s:property value="EXCHANGE_RATE"/>)
						         </div>
						         <div class="col-md-4">
						          	<s:label key="premiumInfo.saleTermForValuation" />&nbsp;&nbsp;:&nbsp;&nbsp;<s:property value="SALES_TERM" default="nil" />
						         </div>
					             <div class="col-md-4">
						          	<s:label key="premiumInfo.totalInsuredValue" />&nbsp;&nbsp;:&nbsp;&nbsp;<s:property value="getText(#format,{TOTAL_INSURED})" default="" />
						         </div>
					          </div>
		                    </li>
		                    <li class="list-group-item list-group-item-default">
		                      <div class="row">
					             <div class="col-md-12">
						          	<s:label key="premiumInfo.equivalent"  />&nbsp;&nbsp;:&nbsp;&nbsp;<s:property value="getText(#format,{EQUIVALENT})" default="nil" />
						         </div>
					          </div>
		                    </li>
		                </ul>
					</div>
				</div>
				<div class="panel panel-primary">
				      <div class="panel-heading">
				      	   <h4><s:text name="premiumInfo.insuredGoods" /></h4>
				      </div>
					  <div class="panel-body" >
					      <table class="table">
					      		<thead>
					      			<tr>
					      				<th scope="col"><s:label key="S.No" /></th>
					      				<th scope="col"><s:label key="premiumInfo.categoryOfGoods" /></th>
					      				<th scope="col"><s:label key="premiumInfo.policyExcess" /></th>
					      				<th scope="col"><s:label key="premiumInfo.description" /></th>
					      				<th scope="col"><s:label key="premiumInfo.insuredValue" /></th>					      				
					      				<th scope="col"><s:label key="premiumInfo.ratePercentage" /></th>
					      				<th scope="col">
					      					<s:if test='%{"3".equals(MODE_OF_TRANSPORT)}'>
				                        		<s:label key="premiumInfo.srccRetePercentage" />
				                        	</s:if>
				                        	<s:else>
				                        		<s:label key="premiumInfo.warRetePercentage" />
				                        	</s:else>
					      				</th> 
					      				<th scope="col"><s:label key="premiumInfo.fragile" /></th>
					      				
					      			</tr>
					      		</thead>
					      		<tbody>
					      			<s:iterator value="insuredGoodsInfo" var="commodityList" status="stat">
						      			<tr>
						      				<td scope="row"><s:property value="#stat.index+1" default=" "/></td>
						      				<td><s:property value="COMMODITY_NAME"  default=" "/></td>						      				 
			                        		<td><s:property value="POLICY_EXCESS"/></td>		                        			 
					                        <td><s:property value="DESCRIPTION" default=" " /></td>
					                        <td><s:property value="SUM_INSURED" default=" " /></td>  
                        					<td><s:property value="RATE" default=" " /></td>
				                      		<td><s:property value="WARRATE" default=" " /></td>  
					                      	<td><s:property value="FRAGILE"/></td>
					                        
						      			</tr>
						      		</s:iterator>
					      		</tbody>
					      </table>
				     </div>
				</div>
				<div class="panel panel-primary">
						<div class="panel-heading">
							<h4><s:text name="premiumInfo.premiumInfo" /></h4>
						</div>
						<div class="panel-body" >
							<s:if test='"admin".equalsIgnoreCase(#session.usertype) || "RSAIssuer".equalsIgnoreCase(#session.usertype)'>
								<div class="row">
						             <div class="col-md-3">
						             </div>
						             <div class="col-md-3" align="left">
						             	<s:label key="premiumInfo.marinePremium" />
						             </div>
						             <div class="col-md-3" align="right">
						             	<s:property value="getText(#format,{MARINE_PREMIUM})" default=" "/>
						             </div>
						             <div class="col-md-3">
						             </div>
						        </div>
						        <br/>
						        <div class="row">
						             <div class="col-md-3" align="right">
						             	<s:label key="premiumInfo.add" />
						             </div>
						             <div class="col-md-3" align="left">
						             	<s:if test='%{"3".equals(MODE_OF_TRANSPORT)}'>
                        	    			<s:label key="premiumInfo.srccPremium" />
                        	    		</s:if>
                        	    		<s:else>
                        	    			<s:label key="premiumInfo.warPremium" />
                        	    		</s:else>
						             </div>
						             <div class="col-md-3" align="right">
						             	<s:property value="getText(#format,{WAR_PREMIUM})" />
						             </div>
						             <div class="col-md-3" align="right">
						             	<s:property value="getText(#format,{TOTAL_PREMIUM})"/>
						             </div>
						        </div>
						    </s:if>
						    <s:else>
						    	<div class="row">
						             <div class="col-md-3" align="right">
						             </div>
						             <div class="col-md-3" align="left">
						             </div>
						             <div class="col-md-3" align="left">
						             	<s:label key="Total Premium" />
						             </div>
						             <div class="col-md-3" align="right">
						             	<s:property value="getText(#format,{TOTAL_PREMIUM})"/>
						             </div>
						        </div>
						    </s:else>
						    <br/>
						    <div class="row">
					             <div class="col-md-3">
					             </div>
					             <div class="col-md-3">
					             </div>
					             <div class="col-md-3">
					             	<s:label key="premiumInfo.additionalPremium" />&nbsp;
					             	<s:if test='"RA".equals(quoteStatus) && !"admin".equalsIgnoreCase(#session.user1)'>
                                 		<s:select list="#{'+':'+','-':'-'}" name="premiumOption" value="%{#quotation.EXCESS_SIGN}" headerKey="" disabled="true" />
                                 	</s:if>
                                 	<s:else>
                                 		<s:select list="#{'+':'+','-':'-'}" name="premiumOption" value="%{#quotation.EXCESS_SIGN}" headerKey="" disabled="#disable"/>
                                 	</s:else>
					             </div>
					             <div class="col-md-3" align="right">
					             	
			                       		<s:property  value="%{#quotation.ADDITIONAL_PREMIUM}" />                          
				                     
					             </div>
					        </div>
						    <br/>
			                <s:if test='#session.user1 == "admin"'>
							    <div class="row">
						             <div class="col-md-3" align="right">
						             	<s:label key="premiumInfo.add" />
						             </div>
						             <div class="col-md-3" align="right">
						             	<s:label key="premiumInfo.policyIssuanceFee" />&nbsp;<s:property value="#session.BrokerDetails.CurrencyAbb"/>
						             </div>
						             <div class="col-md-3">
						             	<s:label key="premiumInfo.editPolicyIssuanceFee" />&nbsp;<s:radio list="#{'Y':'Yes','N':'No'}" name="editPolicyFee" value="%{editPolicyFee==null?'N':editPolicyFee}" onclick="enable(this.value,'policyFee')"/>
						             </div>
						             <div class="col-md-3" align="right">
						             	<s:textfield name="policyFee" id="policyFee" value="%{#quotation.POLICY_ISSUNCE_FEE}" readonly='%{editPolicyFee=="Y"?"false":"true"}'  cssClass="form-control" style="text-align: right"/>
						             </div>
						        </div>
					        </s:if>
			                <s:else>
			                	<div class="row">
						             <div class="col-md-3" align="right">
						             </div>
						             <div class="col-md-3" align="right">
						             	<s:label key="premiumInfo.add" />
						             </div>
						             <div class="col-md-3">
						             	<s:label key="premiumInfo.policyIssuanceFee" />&nbsp;<s:property value="#session.BrokerDetails.CurrencyAbb"/>
						             </div>
						             <div class="col-md-3" align="right">
						             	<s:textfield name="policyFee" id="policyFee" value="%{#quotation.POLICY_ISSUNCE_FEE}" readonly='%{editPolicyFee=="Y"?"false":"true"}'  cssClass="form-control"  style="text-align: right"/>
						             </div>
						        </div>
			                </s:else>
						    <br/>
			                <s:if test='VAT_TAX_PERCENT!=0'>
			                	<div class="row">
						             <div class="col-md-3" align="right">
						             </div>
						             <div class="col-md-3" align="right">
						             	<s:label key="Vat Tax " />
						             </div>
						             <div class="col-md-3" align="left">
						             	<s:property value="VAT_TAX_PERCENT"/>% 
						             </div>
						             <div class="col-md-3" align="right">
						             	<s:textfield name="vatTax" id="vatTax" value="%{#quotation.VAT_TAX_AMT}" readonly='true'  cssClass="form-control" style="text-align: right"/>
						             </div>
						        </div>
						    	<br/>
						    </s:if>
                      	    <div class="row">
					             <div class="col-md-3">
					             </div>
					             <div class="col-md-3">
					             </div>
					             <div class="col-md-3">
					             	<s:label key="premiumInfo.totalPremium" />
					             </div>
					             <div class="col-md-3" align="right">
					             	<s:if test="%{endt && endTypeId!='15'}">
			                     		<s:include value="/pages/premiumInfoEndt.jsp"></s:include>
			                     	</s:if>
					             	<s:textfield name="netPremium"  value="%{#quotation.NET_PREMIUM}" readonly="true"  cssClass="form-control" style="text-align: right"/>
					             </div>
					        </div>
						</div>
					</div>
					<div class="row">
			             <div class="col-md-6" align="right">
			             	<s:label key="premiumInfo.viewClausesDesc"/>
			             </div>
			             <div class="col-md-6" align="left">
			             	<a class="btn btn-info btn-oval" onclick="popUp('${pageContext.request.contextPath}/clausesPayment.action?applicationNo=<s:property value="%{applicationNo}"/>','1000','500');"><s:label key="premiumInfo.viewClauses"/></a>
			             </div>
			        </div>
		<br/>
					
 
       
</s:iterator>
</div>
</s:if>
<s:elseif test='"policy".equals(reqFrom) ||  !"PENDING".equals(#pay.RESPONSE_STATUS)'>
<!--  Policy No -->
<s:iterator value="policyInformation">
			<div class="panel panel-primary">
				 
				  <div class="panel-body">
				 		<s:if test="POLICY_NO != null">
					  		<div class="panel panel-primary">
					  			<div class="panel-heading">
						      	   <h4><s:text name="policyInfo.policyInfo" /></h4>
						      	</div>
				  				<div class="panel-body">
			                      <div class="row" align="center">
						             <div class="col-md-12">
						             	<h3>Your Policy Number is <s:property value="POLICY_NO"/></h3>
						             </div>
						           </div>
				  				</div>
					  		</div>
				  		</s:if>
				  		<div class="panel panel-primary">
				  			<div class="panel-heading">
					      	   <h4><s:text name="quotation.quoteInfo" /></h4>
					      	</div>
			  				<div class="panel-body">
		                      <ul class="list-group">
			                    <li class="list-group-item list-group-item-default">
			                      <div class="row">
					             	<div class="col-md-3">
					             		<s:label key="premiumInfo.quoteNo" />&nbsp;&nbsp;:&nbsp;&nbsp;<s:property value="QUOTE_NO"/>
					             	</div>
					             	<div class="col-md-3">
					             		<s:label key="policyInfo.quotationDate" />&nbsp;&nbsp;:&nbsp;&nbsp;<s:if test="POLICY_NO != null"><s:property value="POL_DATE"/></s:if><s:else><s:property value="QUOTE_DATE"/></s:else>
					             	</div>
					             	<div class="col-md-3">
					             		<s:label key="policyInfo.proposerName" />&nbsp;&nbsp;:&nbsp;&nbsp;<s:property value="PROPOSER_NAME"/>
					             	</div>
					             	<div class="col-md-3">
					             		<s:label key="policyInfo.coverStartDate" />&nbsp;&nbsp;:&nbsp;&nbsp;<s:property value="COVER_START_DATE"/>
					             	</div>
			                      </div>
			                     </li>
			                   </ul>
			  				</div>
				  		</div>
						<s:if test='STATUS == "N"'>
							<div class="panel panel-primary">
					  			<div class="panel-heading">
						      	   <h4><s:text name="premiumInfo.premiumInfo" /></h4>
						      	</div>
				  				<div class="panel-body">
			                      <ul class="list-group">
				                    <li class="list-group-item list-group-item-default">
				                    	<s:if test="%{#session.product_id == 31 || #session.product_id == 32 || #session.product_id == 33 || #session.product_id == 34 || #session.product_id == 41 || #session.product_id == 65 || #session.product_id == 30}">
						                      <div class="row">
									             	<div class="col-md-12">
									             		<s:label key="travel.Premium" />&nbsp;<s:property value="#session.BrokerDetails.CurrencyAbb"/>&nbsp;&nbsp;:&nbsp;&nbsp;<s:property value="getText('{0,number,#,##0.00}',{PREMIUM})"/>
									             	</div>
						                      </div>
					                    </s:if>
										<s:else>
											<div class="row">
								             	<div class="col-md-6">
								             		<s:label key="policyInfo.totalInsuredValueUSD" />&nbsp;<s:property value="#session.BrokerDetails.CurrencyAbb"/>&nbsp;&nbsp;:&nbsp;&nbsp;<s:property value="getText('{0,number,#,##0.00}',{EQUIVALENT})"/>
								             	</div>
								             	<div class="col-md-6">
								             		<s:label key="premiumInfo.showPremium" />&nbsp;<s:property value="#session.BrokerDetails.CurrencyAbb"/>&nbsp;&nbsp;:&nbsp;&nbsp;<s:property value="getText('{0,number,#,##0.00}',{PREMIUM})"/>
								             	</div>
						                    </div>
										</s:else>
				                     </li>
				                   </ul>
				  				</div>
					  		</div>
					  		
						  		<div class="panel panel-primary">
						  			<div class="panel-heading">
							      	   <h4>Payment Information</h4>
							      	</div>
					  				<div class="panel-body">
										<div class="row">
											<div class="col-md-3">
												Payment Status is <b><s:property value="#pay.RESPONSE_STATUS"/></b>
											</div>
											<div class="col-md-3">
												Payment Transaction No <b><s:property value="#pay.MERCHANT_REFERENCE"/></b>
											</div>
											<div class="col-md-3">
												Transaction Message <b> <s:property value="#pay.RESPONSE_MESSAGE"/></b>
											</div>
											<div class="col-md-3">
												AUTH Code <b> <s:property value="#pay.RES_AUTH_CODE" default="N/A"/></b>
											</div>
										</div>
									</div>
								</div>
						
									
							 
						</s:if>
						<s:else>
							<div class="panel panel-primary">
				  				<div class="panel-body">
				  					<s:if test='%{#session.product_id == 30 && validCoverage!=null && !"".equals(validCoverage)}'>
										<div class="row">
											<div class="col-md-12">
												<s:property value="getText('label.error.validcoverage.referralmsg')"/>&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;${validCoverage}
											</div>
										</div>
									</s:if>
									<div class="row">
										<div class="col-md-12">
											<s:label key="policyInfo.referralMsg"/>&nbsp;&nbsp;<s:property value="REFERRAL_DESC"/>
										</div>
									</div>
								</div>
							</div>
						</s:else>
                   		<s:if test="%{endTypeId!=null && endTypeId.length() > 0}">
                           	<s:hidden name="menuType" value="P"/>
                        </s:if>
                   		<s:elseif test='STATUS=="Y"'>
                   			<s:hidden name="menuType" value="RU"/>
                   		</s:elseif>
                   		<s:else>
	                    	<s:if test="%{#session.product_id == 3 || #session.product_id == 11}">
                    			<s:hidden name="menuType" value="%{POLICY_NO==null?'QE':'P'}"/>
                    		</s:if>
                    		<s:else>
	                    		<s:if test="%{quoteStatus==null}">
	                    			<s:hidden name="menuType" value="%{POLICY_NO==null?menuType=='RA'?'RA':'QE':'P'}"/>
	                    		</s:if>
	                    		<s:else>
	                    			<s:hidden name="menuType" value="%{POLICY_NO!=null?'P':quoteStatus}"/>
	                    		</s:else>
                    		</s:else>
                   		</s:else> 
				  </div>
			</div>
			
		<s:hidden name="loginId" value="%{LOGIN_ID}"/> 
		</s:iterator>

</s:elseif>
<s:hidden name="quoteNo" value="%{#quotation.QUOTE_NUMBER}"/>    
<s:hidden name="quoteStatus" value="%{quoteStatus}"/>    
<s:hidden name="applicationNo"/>  
<s:hidden name="openCoverNo"/>  
<s:hidden name="loginId" />
<s:hidden name="searchBy" />
<s:hidden name="searchValue" />
<s:hidden name="reqFrom"></s:hidden>
<s:hidden name="merchantRefno"></s:hidden>
<div class="row">
         <div class="col-md-6" align="right">   	&nbsp;        </div>
         	<s:if test='"view".equals(reqFrom) && "PENDING".equals(#pay.RESPONSE_STATUS) ' >
           		<div class="col-md-6" align="left"> 	<input type="submit" class="btn btn-success btn-oval" value="Make Payment"></input>       </div>
           	</s:if>
</div>
</s:form>
 <s:if test='"formsubmit".equals(reqFrom) && "PENDING".equals(#pay.RESPONSE_STATUS) ' >
	<s:form name="cybersource" id="cybersource"  theme="simple" method="post" action="https://testsecureacceptance.cybersource.com/pay">
		<s:iterator value="%{cyberSourceInfo}" var="cyber">
   			<input type="hidden" id='<s:property value="#cyber.key" />' name='<s:property value="#cyber.key" />' value='<s:property value="#cyber.value" />'/> 
		</s:iterator>		
		<script type="text/javascript">
			document.cybersource.submit();
		</script>
	</s:form>
	</s:if>
</s:iterator>
</s:else>

