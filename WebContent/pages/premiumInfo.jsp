<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <script type="text/javascript" src="css/wz_tooltip.js"></script>
  <script language="JavaScript">javascript:window.history.forward(1);</script>
  <script language="JavaScript">
		function stopRKey(evt) { 
		  var evt = (evt) ? evt : ((event) ? event : null); 
		  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
		  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
		} 
		document.onkeypress = stopRKey; 
  </script>
  </head>
  <body>
	<div class="container-fluid">
		<s:iterator value="quotationInfo" status="stat" var="quotation">
			<s:form id="premiumInfo" name="premiumInfo" method="post" action="" theme="simple">
				<s:set var="disable" value="%{endTypeId!=null && endTypeId.length() > 0}"/>
		       	<s:if test='%{"admin".equalsIgnoreCase(#session.user1)}'>
		       		<s:set var="format" value="%{'number.format.2'}"/>
		       	</s:if>
		       	<s:else>
		       		<s:set var="format" value="%{'number.format.'+#session.BrokerDetails.CurrencyDecimal}"/>
		       	</s:else>
		       	<s:actionerror style="color: red"/>
				<s:if test="%{endt}">
					<s:set value="policyEndtInfo" var="policyEndtInfo"/>
					<ul class="list-group">
		                <li class="list-group-item list-group-item-default">
			                <div class="row">
					             <div class="col-md-4">
						          	<s:label key="endt.policyNo"  />&nbsp;&nbsp;:&nbsp;&nbsp;<s:property value="%{#policyEndtInfo.POLICY_NO}"/>
						         </div>
						         <div class="col-md-4">
						          	<s:label key="endt.brokerName"/>&nbsp;&nbsp;:&nbsp;&nbsp;<s:property value="%{#policyEndtInfo.BROKER_NAME}"/>
						         </div>
					             <div class="col-md-4">
						          	<s:label key="endt.custName"  />&nbsp;&nbsp;:&nbsp;&nbsp;<s:property value="%{#policyEndtInfo.CUSTOMER_NAME}"/>
						         </div>
					        </div>
				            <div class="row">
						         <div class="col-md-12">
						          	<s:label key="quotation.endtTypeDesc"/>&nbsp;&nbsp;:&nbsp;&nbsp;<s:property value="endtType" />
						         </div>
				           </div>
		                </li>
		            </ul>
		        </s:if>
				<s:elseif test="%{productId==openCover}" >
					<s:set value="openCoverInfo" var="openCoverInfo"/>
					<ul class="list-group">
		                   <li class="list-group-item list-group-item-default">
		                     <div class="row">
					             <div class="col-md-4">
						          	<s:text name="endt.openPolicyNo"/>&nbsp;&nbsp;:&nbsp;&nbsp;<s:property value="%{#openCoverInfo.OPEN_COVER_NO}"/>
						         </div>
						         <div class="col-md-4">
						          	<s:text name="endt.brokerName"/>&nbsp;&nbsp;:&nbsp;&nbsp;<s:property value="%{#openCoverInfo.BROKER_NAME}"/>
						         </div>
					             <div class="col-md-4">
						          	<s:text name="endt.custName"/>&nbsp;&nbsp;:&nbsp;&nbsp;<s:property value="%{#openCoverInfo.CUSTOMER_NAME}"/>
						         </div>
					          </div>
		                   </li>
	                </ul>
				</s:elseif>
		        <div class="panel panel-primary">
					<div class="panel-heading">
						<div class="row">
				             <div class="col-md-3"  id="plus" onclick="quotationDetail();" style="display: block; cursor: pointer;">
					          	<h4>+&nbsp;&nbsp;<s:text name="premiumInfo.quotation" /></h4>
					         </div>
					         <div class="col-md-3"  id="miuns" onclick="normalForm();" style="display: none; cursor: pointer;">
					          	<h4>-&nbsp;&nbsp;<s:text name="premiumInfo.quotation" /></h4>
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
					<div class="panel-body" id="quoteInfo" style=" display: none;" >
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
						          	<s:label key="premiumInfo.equivalent"  />(<s:property value="#session.BrokerDetails.CurrencyAbb"/>)&nbsp;&nbsp;:&nbsp;&nbsp;<s:property value="getText(#format,{EQUIVALENT})" default="nil" />
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
					      				<s:if test='Status=="N" || #session.user1 == "admin"'>
					      					<s:if test='"admin".equalsIgnoreCase(#session.usertype) || "RSAIssuer".equalsIgnoreCase(#session.usertype)'>
							      				<th scope="col"><s:label key="premiumInfo.ratePercentage" /></th>
							      				<th scope="col">
							      					<s:if test='%{"3".equals(MODE_OF_TRANSPORT)}'>
						                        		<s:label key="premiumInfo.srccRetePercentage" />
						                        	</s:if>
						                        	<s:else>
						                        		<s:label key="premiumInfo.warRetePercentage" />
						                        	</s:else>
							      				</th>
					      					</s:if>
                         					<s:else>
                         						<th scope="col"><s:label key="premiumInfo.ratePercentage" /></th>
                         					</s:else>
					      				</s:if>
					      				<th scope="col"><s:label key="premiumInfo.fragile" /></th>
					      				<th scope="col"><s:label key="premiumInfo.supplierName" /></th>
					      				<th scope="col"><s:label key="premiumInfo.invoice" /></th>
					      			</tr>
					      		</thead>
					      		<tbody>
					      			<s:iterator value="insuredGoodsInfo" var="commodityList" status="stat">
						      			<tr>
						      				<td scope="row"><s:property value="#stat.index+1" default=" "/></td>
						      				<td><s:property value="COMMODITY_NAME"  default=" "/></td>
						      				<s:if test="#session.user1 == 'admin'">
						      					<td>
						      						<s:if test="hasActionErrors()">
					                      				<s:textfield value="%{policyExcess[#stat.index]}" name="policyExcess" cssClass="form-control" onkeyup="checkNumbers(this)" maxlength="6"/>                        
							                       </s:if>
							                       <s:else>
							                       		<s:textfield value="%{POLICY_EXCESS}" name="policyExcess" cssClass="form-control" onkeyup="checkNumbers(this)" maxlength="6"/>                      
							                       </s:else>
						      					</td>
						      				</s:if>
		                        			<s:else >
			                        			<td><s:property value="POLICY_EXCESS"/></td>
		                        			</s:else>
					                        <td><s:property value="DESCRIPTION" default=" " /></td>
					                        <td><s:property value="getText(#format,{SUM_INSURED})" default=" " /></td>
					                        <s:if test="#session.user1 == 'admin'">
						                       <s:if test="hasActionErrors()">
	   						                        <td><s:textfield name="commodityRate" value="%{commodityRate[#stat.index]}" cssClass="form-control" onkeyup="checkNumbers(this)"/><s:hidden name="commodityId" value="%{#commodityList.COMMODITY_ID}"/></td>
				                      				<td><s:textfield name="commodityWarRate" value="%{commodityWarRate[#stat.index]}" cssClass="form-control" onkeyup="checkNumbers(this)"/></td>                        
						                       </s:if>
						                       <s:else>
						                       		<td><s:textfield name="commodityRate" value="%{getText('{0,number,#,##0.0000}',{RATE})}" cssClass="form-control" onkeyup="checkNumbers(this)"/><s:hidden name="commodityId" value="%{#commodityList.COMMODITY_ID}"/></td>
					                      			<td><s:textfield name="commodityWarRate" value="%{getText('{0,number,#,##0.0000}',{WARRATE})}" cssClass="form-control" onkeyup="checkNumbers(this)"/></td>                        
						                       </s:else>
					                        </s:if>
					                        <s:elseif test='Status=="N"'>
		                        				<s:if test='"admin".equalsIgnoreCase(#session.usertype) || "RSAIssuer".equalsIgnoreCase(#session.usertype)'>
		                        					<td><s:property value="getText('{0,number,#,##0.000000}',{RATE})" default=" " /></td>
						                      		<td><s:property value="getText('{0,number,#,##0.000000}',{WARRATE})" default=" " /></td>
		                        				</s:if>
		                        				<s:else>
		                        					<td><s:property value="getText('{0,number,#,##0.000000}',{MARINE_WAR_RATE})" default=" " /></td>
		                        				</s:else>
						                    </s:elseif>
					                      	<td><s:property value="FRAGILE"/></td>
					                        <td><s:property value="SUPPLIER_NAME"/></td> 
					                        <td><s:property value="INVOICE_NUMBER"  /></td>
						      			</tr>
						      		</s:iterator>
					      		</tbody>
					      </table>
				     </div>
				</div>
				<s:if test='Status=="N" || #session.user1 == "admin"'>
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
					             	<s:if test="hasActionErrors()">
				                        <s:textfield name="additionalPremium" onkeyup="validamt(this)" readonly="%{quoteStatus=='RA' && #session.user1 != 'admin'?'true':'false'}" disabled="#disable" cssClass="form-control" style="text-align: right"/>                        
				                     </s:if>
				                     <s:else>
			                       		<s:textfield name="additionalPremium"  value="%{#quotation.ADDITIONAL_PREMIUM}" onkeyup="validamt(this)" readonly='%{ (#session.user1 != "admin" && "11".equals(#session.product_id)) || (quoteStatus=="RA" && #session.user1 != "admin")?"true":"false"}' disabled="#disable" cssClass="form-control" style="text-align: right"/>                          
				                     </s:else>
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
	     			<s:if test='Status=="N" || #session.user1 == "admin"'>
	     				<s:if test='"admin".equalsIgnoreCase(#session.usertype) || "RSAIssuer".equalsIgnoreCase(#session.usertype) || "N".equalsIgnoreCase(viewClausesOption)'>
			     			<div class="panel panel-primary">
								<div class="panel-heading">
									<h4><s:text name="premiumInfo.clausesInfo" /></h4>
								</div>
								<div class="panel-body" >
									<s:if test='#session.user1 == "admin"'>
			                      	    <div class="row">
								             <div class="col-md-2">
								             </div>
								             <div class="col-md-2" align="center">
								             	<a class="btn btn-warning btn-oval" onclick="popUp('${pageContext.request.contextPath}/clausesPremium.action?applicationNo=<s:property value="%{applicationNo}"/>','1000','500');"><s:label key="premiumInfo.editClauses"/></a>
								             </div>
								             <div class="col-md-2" align="center">
								             	<a class="btn btn-info btn-oval" onclick="popUp('${pageContext.request.contextPath}/addClausesPremium.action?applicationNo=<s:property value="%{applicationNo}"/>&coverId=<s:property value="COVER_ID"/>&conditionType=1','1000','500');"><s:label key="premiumInfo.addClauses"/></a>
								             </div>
								             <div class="col-md-2" align="center">
								             	<a class="btn btn-info btn-oval" onclick="popUp('${pageContext.request.contextPath}/addClausesPremium.action?applicationNo=<s:property value="%{applicationNo}"/>&coverId=<s:property value="COVER_ID"/>&conditionType=2','1000','500');"><s:label key="premiumInfo.addWarClauses"/></a>
								             </div>
								             <div class="col-md-2" align="center">
								             	<a class="btn btn-info btn-oval" onclick="popUp('${pageContext.request.contextPath}/addClausesPremium.action?applicationNo=<s:property value="%{applicationNo}"/>&coverId=<s:property value="COVER_ID"/>&conditionType=3','1000','500');"><s:label key="premiumInfo.addExclusions"/></a>   
								             </div>
								             <div class="col-md-2" align="center">
								             	<a class="btn btn-info btn-oval" onclick="popUp('${pageContext.request.contextPath}/addClausesPremium.action?applicationNo=<s:property value="%{applicationNo}"/>&coverId=<s:property value="COVER_ID"/>&conditionType=4','1000','500');"><s:label key="premiumInfo.addWarranties"/></a>
								             </div>
								        </div>
									</s:if>
					     			<s:else>
					     				<div class="row">
								             <div class="col-md-6" align="right">
								             	<s:label key="premiumInfo.viewClausesDesc"/>
								             </div>
								             <div class="col-md-6" align="left">
								             	<a class="btn btn-info btn-oval" onclick="popUp('${pageContext.request.contextPath}/clausesPremium.action?applicationNo=<s:property value="%{applicationNo}"/>','1000','500');"><s:label key="premiumInfo.viewClauses"/></a>
								             </div>
								        </div>
					     			</s:else>
								</div>
							</div>
						</s:if>
		    		    <s:if test='Status=="N" && #session.user1 != "admin"'>
		    		    	<s:if test="!endt || (endt && financial)">
		    		    		<div class="panel panel-primary">
									<div class="panel-heading">
										<h4><s:text name="premiumInfo.referralInfo" /></h4>
									</div>
									<div class="panel-body" >
					     				<div class="row">
					     					 <div class="col-md-6">
					     					 	<div class="form-group">
					     					 		<s:text name="Would you like to get in touch with under writer for getting better quote?" />&nbsp;
					     					 		<s:radio list="#{'Y':'Yes','N':'No'}" name="referralUpdate"  id="referralUpdate"  onclick="disablePolicyOption(this.value);enable(this.value, 'referralComment')" value="%{referralUpdate==null?(ADMIN_REFERRAL_STATUS==null?'N':ADMIN_REFERRAL_STATUS):referralUpdate}"/>
					     					 	</div>
					     					 </div>
					     					 <div class="col-md-6">
					     					 	<div class="form-group">
					     					 		<s:text name="premiumInfo.comments" /><br/>
					     					 		<s:textarea name="referralComment" id="referralComment" cssClass="form-control" cols="25" rows="2" readonly='%{referralUpdate==null || referralUpdate=="N"?(ADMIN_REFERRAL_STATUS!="Y"):"false"}' value='%{referralComment==null?(ADMIN_REFERRAL_STATUS=="Y"?REFERRAL_DESC:""):referralComment}'/>
					     					 	</div>
					     					 </div>
					     				</div>
									</div>
								</div>
		    		    	</s:if>
		    		    	<div class="panel panel-primary" id="policyGeneration">
								<div class="panel-heading">
									<h4><s:text name="premiumInfo.policyGeneration" /></h4>
								</div>
								<div class="panel-body" >
				     				<div class="row">
				     					 <div class="col-md-12">
				     					 	<div class="form-group">
				     					 		<s:label key="premiumInfo.generatePolicy" />&nbsp;
				     					 		<s:radio list="#{'Y':'Schedule','N':'Draft'}"  name="generatePolicy" id="generatePolicy" value="%{generatePolicy==null?'N':generatePolicy}" onclick="disablePolicyDetail(this);disablePaymentMethod(this.value);"/>
				     					 	</div>
				     					 </div>
				     				</div>
				     				<s:if test='"RSAIssuer".equalsIgnoreCase(#session.usertype)  '>
				     					<div class="row" id="blockPaymentMethod" style="display: none;">
					     					<div class="col-md-12">
					     					 	<div class="form-group">
					     					 		<s:label key="premiumInfo.paymentmethod" />&nbsp;
					     					 		<s:radio list="#{'C':'Credit','O':'Online (Default)'}"  name="paymentMethod" id="paymentMethod" value="%{paymentMethod==null?'O':paymentMethod}" onclick="disablePolicyDetail(this);"/>
					     					 	</div>
					     					 </div>
					     				</div>
				     				</s:if>
				     				<s:elseif test='"3"==#session.product_id'>
				     					<div class="row" id="blockPaymentMethod" >					     					
					     						<s:hidden name="paymentMethod" id="paymentMethod" value="O"></s:hidden> 
					     				</div>
				     				</s:elseif>
				     				<s:else>
				     					<div class="row" id="blockPaymentMethod" > 
					     						<s:hidden name="paymentMethod" id="paymentMethod" value="C"></s:hidden> 
					     				</div>
				     				</s:else>
				     				
				     			</div>
					     	</div>
		                 	<div class="panel panel-primary" id="getPolicyDetail">
		                 		<div class="panel-body" >
			                 		<div class="panel panel-primary">
										<div class="panel-heading">
											<h4><s:text name="premiumInfo.certificateDisplayInfo" /></h4>
										</div>
										<div class="panel-body" >
											<s:iterator var="docInfo" value="policyInformation"> 
							     				<div class="row">
					     					 		<s:if test="productId != openCover">
					     					 			<s:if test='"RSAIssuer".equalsIgnoreCase(#session.usertype)'>
							     					 		<div class="col-md-2">
					     					 					<s:checkbox name="premiumYes"  id="premiumYes"  fieldValue="YES" value='%{premiumYN==null?PDF_PRE_SHOW_STATUS=="YES":premiumYN=="YES"}' disabled='%{premiumYN==null?PDF_PRE_SHOW_STATUS=="PAID":premiumYN=="PAID"}' onclick="setPremiumYN();"/><s:label key="premiumInfo.showPremium"  name="certificateInfo"/>
					     					 				</div>
					     					 				<div class="col-md-2">
					     					 					<s:checkbox name="premiumPaid"  id="premiumPaid"  fieldValue="PAID" value='%{premiumYN==null?PDF_PRE_SHOW_STATUS=="PAID":premiumYN=="PAID"}' disabled='%{premiumYN==null?PDF_PRE_SHOW_STATUS=="YES":premiumYN=="YES"}' onclick="setPremiumYN();"/><s:label key="premiumInfo.paidPremium"/>
					     					 				</div>
					     					 				<s:hidden name="premiumYN" id="premiumYN"/>
					     					 			</s:if>
					     					 			<s:else>
					     					 				<div class="col-md-2">
					     					 					<s:checkbox name="premiumYN"  id="check_premium"  fieldValue="YES" value='%{premiumYN==null?PDF_PRE_SHOW_STATUS=="YES":premiumYN=="YES"}' /><s:label key="premiumInfo.showPremium"  name="certificateInfo"/>
					     					 				</div>
					     					 			</s:else>
					     					 			<div class="col-md-2">
					     					 				<s:checkbox name="banker" id="check_banker"  fieldValue="YES"  value='%{banker==null?PDF_BANKER_STATUS=="YES":banker=="YES"}' onclick="if (this.checked) document.getElementById('check_both').disabled=true; else document.getElementById('check_both').disabled = false;" disabled='%{BANK_NAME==null || both=="YES"}'/><s:label key="premiumInfo.shouldBankerOnly"  name="certificateInfo"/>
					     					 			</div>
					     					 			<div class="col-md-2">
								    				    	<s:checkbox name="both" id="check_both"  fieldValue="YES"  value='%{both==null?PDF_BANKER_ASSURED_STATUS=="YES":both=="YES"}' onclick="if (this.checked) document.getElementById('check_banker').disabled=true; else document.getElementById('check_banker').disabled = false;" disabled='%{BANK_NAME==null || banker=="YES"}'/><s:label key="premiumInfo.shouldBankerAndAssured"  name="certificateInfo"/>
								    				    </div>
					     					 			<div class="col-md-2">
								                        	<s:checkbox name="foreign" id="check_foreign" fieldValue="YES" value='%{foreign==null?PDF_CURRENCY_STATUS=="YES":foreign=="YES"}' /><s:label key="premiumInfo.showForeign"  name="certificateInfo"/>
								                        </div>
					     					 			<div class="col-md-2">
								                        	<s:checkbox name="rubberStamp" id="check_rubber_stamp" fieldValue="Y" value='%{rubberStamp==null?PDF_STAMP_STATUS=="Y":rubberStamp=="Y"}' /><s:label key="premiumInfo.printRubberStamp"  name="certificateInfo"/>
								                        </div>
								                        <s:hidden name="certClausesYN" value="Y"/>
								                        <s:if test='"RSAIssuer".equalsIgnoreCase(#session.usertype)'>
					     					 				<div class="col-md-2">
								                       			<s:checkbox name="excess"  id="check_excess"  fieldValue="YES" value='%{excess==null?PDF_EXCESS_STATUS=="YES":excess=="YES"}' /><s:label key="premiumInfo.showExcess"  name="certificateInfo"/>
								                       		</div>
								                        </s:if>
								                        <s:else>
								                       	 	<s:hidden name="excess"  id="check_excess" value=""/>
								                        </s:else>
					     					 		</s:if>
					     					 		<s:else>
					     					 			<s:if test='"RSAIssuer".equalsIgnoreCase(#session.usertype)'>
					     					 				<div class="col-md-2">
																<s:checkbox name="premiumYes"  id="premiumYes"  fieldValue="YES" value='%{premiumYN==null?PDF_PRE_SHOW_STATUS=="YES":premiumYN=="YES"}' disabled='%{premiumYN==null?PDF_PRE_SHOW_STATUS=="PAID":premiumYN=="PAID"}' onclick="setPremiumYN();"/><s:label key="premiumInfo.showPremium"  name="certificateInfo"/>
															</div>
															<div class="col-md-2">
																<s:checkbox name="premiumPaid"  id="premiumPaid"  fieldValue="PAID" value='%{premiumYN==null?PDF_PRE_SHOW_STATUS=="PAID":premiumYN=="PAID"}' disabled='%{premiumYN==null?PDF_PRE_SHOW_STATUS=="YES":premiumYN=="YES"}' onclick="setPremiumYN();"/><s:label key="premiumInfo.paidPremium"/>
															</div>
															<s:hidden name="premiumYN" id="premiumYN"/>
								                        </s:if>
								                        <s:else>
								                        	<div class="col-md-2">
																<s:checkbox name="premiumYN"  id="check_premium"  fieldValue="YES" value='%{premiumYN==null?PDF_PRE_SHOW_STATUS=="YES":premiumYN=="YES"}' /><s:label key="premiumInfo.showPremium"  name="certificateInfo"/>
															</div>
								                        </s:else>
								                        <div class="col-md-2">
							                       		   		<s:checkbox name="banker" id="check_banker"  fieldValue="YES"  value='%{banker==null?PDF_BANKER_STATUS=="YES":banker=="YES"}' onclick="if (this.checked) document.getElementById('check_both').disabled=true; else document.getElementById('check_both').disabled = false;" disabled='%{BANK_NAME==null || both=="YES"}'/><s:label key="premiumInfo.shouldBankerOnly"  name="certificateInfo"/>
							                       		 </div>
							                       		 <div class="col-md-2">
									    				   		<s:checkbox name="both" id="check_both"  fieldValue="YES"  value='%{both==null?PDF_BANKER_ASSURED_STATUS=="YES":both=="YES"}' onclick="if (this.checked) document.getElementById('check_banker').disabled=true; else document.getElementById('check_banker').disabled = false;" disabled='%{BANK_NAME==null || banker=="YES"}'/><s:label key="premiumInfo.shouldBankerAndAssured"  name="certificateInfo"/>
									    				 </div>
							                       		 <div class="col-md-2">
									                       		<s:checkbox name="foreign" id="check_foreign" fieldValue="YES" value='%{foreign==null?PDF_CURRENCY_STATUS=="YES":foreign=="YES"}' /><s:label key="premiumInfo.showForeign"  name="certificateInfo"/>
									                     </div>
							                       		 <div class="col-md-2">
									                       		<s:checkbox name="rubberStamp" id="check_rubber_stamp" fieldValue="Y" value='%{rubberStamp==null?PDF_STAMP_STATUS=="Y":rubberStamp=="Y"}' /><s:label key="premiumInfo.printRubberStamp"  name="certificateInfo"/>
									                     </div>
								                       	 <s:if test='"RSAIssuer".equalsIgnoreCase(#session.usertype)'>
							                       		 	<div class="col-md-2">
								                       			<s:checkbox name="excess"  id="check_excess"  fieldValue="YES" value='%{excess==null?PDF_EXCESS_STATUS=="YES":excess=="YES"}' /><s:label key="premiumInfo.showExcess"  name="certificateInfo"/>
								                       		</div>
								                        </s:if>
								                        <s:else>
								                       		<s:hidden name="excess" id="check_excess"  value=""/>
								                        </s:else>
					     					 		</s:else>
								     				<s:if test="%{endt}">
								     					<div class="col-md-2">
								     					 	<s:checkbox name="showpremiumYN"  id="check_showpremium"  fieldValue="Y" value='%{showpremiumYN==null?ENDT_PREM_YN=="Y":showpremiumYN=="Y"}' /><s:label key="premiumInfo.showPremiumEndt"  name="certificateInfo"/>
								     					</div>
							                       		<div class="col-md-2">
									                       	<s:checkbox name="printClausesYN" id="printClausesYN" fieldValue="Y" value='%{printClausesYN==null?ENDT_CLAUSES_YN=="Y":printClausesYN=="Y"}' /><s:label key="premiumInfo.showClausesEndt"  name="certificateInfo"/>
								     					</div>
								     				</s:if>
							     				</div>
							     			</s:iterator>
						     			</div>
					     			</div>
					     			<div class="panel panel-primary">							     		
							     			<div class="panel-heading">
												<h4>Document Upload</h4>
											</div>							     		
							     		<div class="panel-body" >
							     			<div class="text-center">
									     				<div >
									     					 <div class="form-group">									     				 		
									     				 		<a class="btn btn-success btn-oval" onClick="javascript:lcupload('<s:property value="%{#quotation.QUOTE_NUMBER}"/>')">Documents</a>
									     				 	</div>
									     				</div>
									     			</div>
							     		</div>
							     	</div>
					     			<div class="panel panel-primary">
							     		<s:if test='%{"RSAIssuer".equalsIgnoreCase(#session.usertype)}'>
							     			<div class="panel-heading">
												<h4><s:text name="premiumInfo.documentBasis" /></h4>
											</div>
							     		</s:if>
							     		<div class="panel-body" >
								     		<s:if test="%{endt}">
								     			<s:hidden name="noteType" value="G"/>
								     			<s:if test='%{#quotation.ISSUER!=null && !"".equals(#quotation.ISSUER) && "cash".equalsIgnoreCase(#quotation.CHANNEL_TYPE)}'>
								     				<s:hidden name="paymentMode" value="CA"/>
								     			</s:if>
								     			<s:else>
								     				<s:hidden name="paymentMode" value="CR"/>
								     			</s:else>
								     			<s:if test='%{"RSAIssuer".equalsIgnoreCase(#session.usertype)}'>
								     				<div class="row">
									     				<div class="col-md-6">
									     					 <div class="form-group">
									     				 		<s:label key="premiumInfo.basisOfValidation" />
									     				 		<s:select list="basisValList" name="basisValDesc" cssClass="form-control" listKey="REMARKS" listValue="REMARKS"/>
									     				 	</div>
									     				</div>
									     			</div>
								     			</s:if>
								     			<s:else>
							     				 	<s:hidden name="basisValDesc" value="ACTUAL AMOUNT"/>
							     				</s:else>
								     		</s:if>
								     		<s:else>
							     				<s:hidden name="noteType" value="G"/>
							     				<s:if test='%{#quotation.ISSUER!=null && !"".equals(#quotation.ISSUER) && "cash".equalsIgnoreCase(#quotation.CHANNEL_TYPE)}'>
							     					<s:hidden name="paymentMode" value="CA"/>
							     				 </s:if>
							     				 <s:else>
							     				 	<s:hidden name="paymentMode" value="CR"/>
							     				 </s:else>
							     				<s:if test='%{"RSAIssuer".equalsIgnoreCase(#session.usertype)}'>
							     					<div class="row">
									     				<div class="col-md-6">
									     					 <div class="form-group">
									     						<s:label key="premiumInfo.basisOfValidation" />
									     				 		<s:select list="basisValList" name="basisValDesc" cssClass="form-control" listKey="REMARKS" listValue="REMARKS"/>
									     				 	</div>
									     				 </div>
									     			</div>
							     				</s:if>
							     				<s:else>
							     				 	<s:hidden name="basisValDesc" value="ACTUAL AMOUNT"/>
							     				</s:else>
								     		</s:else>
								     	</div>
					     			</div>
				     			</div>
				     		</div>
		    		    </s:if>
					</s:if>
				</s:if>
				<s:elseif test='Status=="Y"'>
					<div class="panel panel-primary">
						<div class="panel-heading">
							<h4><s:text name="List of Refferal in Your Quotation" /></h4>
						</div>
						<div class="panel-body" >
							<div class="row">
						     	<div class="col-md-12">
									<ol>
										<s:iterator value='REFERRAL_DESC.split("~")' status="stat"> 
											<li><s:property/></li>
										</s:iterator>
									</ol>
								</div>
		     				</div>
						</div>
					</div>		
    			</s:elseif>
    			<s:if test='#session.user1 == "admin"'>
    				<div class="panel panel-primary">
						<div class="panel-body" >
							<div class="row">
						     	<div class="col-md-6">
						     		<div class="form-group">
						     			<s:label key="premiumInfo.commissionYN"/><br/>
						     			<s:radio name="commissionYN" list="#{'YES':'Yes','NO':'No'}" value="%{commissionYN==null?'NO':commissionYN}" onclick="enable(this.value,'commission')"/>
						     		</div>
						     	</div>
						     	<div class="col-md-6">
						     		<div class="form-group">
						     			<s:label key="premiumInfo.commission"/>
						     			<s:textfield name="commission" id="commission" cssClass="form-control" readonly="%{commissionYN==null || commissionYN=='NO'}" value="%{commission==null?#quotation.DISCOUNT_PREMIUM:commission}" maxlength="5" onkeyup="checkNumbers(this)"/>
						     		</div>
						     	</div>
						     </div>
							<div class="row">
						     	<div class="col-md-6">
						     		<div class="form-group">
						     			<s:label key="premiumInfo.referralStatus"/><br/>
						     			<s:radio name="referralStatus" list="#{'A':'Approve','R':'Reject','N':'None'}" value='%{(referralStatus==null || "".equals(referralStatus))?quoteStatus=="RA"?"A":quoteStatus=="RR"?"R":"N":referralStatus}'/>
						     		</div>
						     	</div>
						     	<div class="col-md-6">
						     		<div class="form-group">
						     			<s:label key="premiumInfo.remarks"/>
						     			<s:textarea name="adminRemarks" cssClass="form-control" value="%{adminRemarks==null?#quotation.ADMIN_REMARKS:adminRemarks}" onkeyup="textLimit(this,'450')"/>
						     		</div>
						     	</div>
						     </div>
						 </div>
					</div>
    			</s:if>
    			<br/>
    			<div class="row" align="center">
    				<s:if test="%{endTypeId!=null && endTypeId.length() > 0 && quoteStatus=='RA'}">
                   		<s:submit name="Submit2" type="submit" cssClass="btn btn-danger btn-oval" value="Back"  onclick="update('initReport.action?menuType=E')"/>&nbsp;&nbsp;&nbsp;&nbsp;
                   	</s:if>
                   	<s:else>
                   		<s:submit name="Submit2" type="submit" cssClass="btn btn-danger btn-oval" value="Back"  onclick="update('editQuoteQuotation.action')"/>&nbsp;&nbsp;&nbsp;&nbsp;
                   	</s:else>
                   	<s:if test='Status=="N" || #session.user1 == "admin"'>                              
                       	<s:if test="!(quoteStatus=='RA' && #session.user1 != 'admin')">
	                       	<s:submit name="Submit3" type="button" cssClass="btn btn-info btn-oval" value="Calculate" onclick="disableForm(this.form,false,'');update('calculatePremium.action')"/>&nbsp;&nbsp;&nbsp;&nbsp;
                       	</s:if>
                    </s:if>
                    <input type="button" name="SubmitProceed" id="SubmitProceed" class="btn btn-success btn-oval"  value="Proceed"  onclick="disableForm(this.form,false,'');update('updatePremium.action');return false;"/>
    			</div>
    			<br/>
	          <s:hidden name="quoteNo" value="%{#quotation.QUOTE_NUMBER}"/>    
	          <s:hidden name="totalPremium" value="%{#quotation.TOTAL_PREMIUM}"/>    
	          <s:hidden name="applicationNo"/>  
	          <s:hidden name="refNo" />  
	          <s:hidden name="openCoverNo"/>  
	          <s:hidden name="loginId" value="%{#quotation.LOGIN_ID}"/>
	          <s:hidden name="issuer" value="%{#quotation.ISSUER}"/>
	          <s:hidden name="totalWarPremium" value="%{#quotation.WAR_PREMIUM}"/>
	          <s:hidden name="status" value="%{#quotation.STATUS}"/>
	          <s:hidden name="quoteStatus" />
	          <s:hidden name="settlingAgent" value="%{#quotation.SETTLING_AGENT_NAME}"/>
	          <s:hidden name="packDesc" value="%{#quotation.PACKAGE_DESCRIPTION}"/>
	          <s:hidden name="addClauses"/>
	          <s:hidden name="addWarClauses"/>
	          <s:hidden name="addExclusions"/>
	          <s:hidden name="addWarranties"/>
	          <s:hidden name="endTypeId" />
	          <s:hidden name="adminRefStatus" value="%{#quotation.ADMIN_REFERRAL_STATUS}"/>
	          <s:hidden name="policyNo"/> 
			  <s:hidden name="custName"/>
			  <s:hidden name="brokerCompany"/>
			  <s:hidden name="updateClauses" value="Y"/>
			  <s:hidden name="searchFrom" />
			  <s:hidden name="searchBy" />
			  <s:hidden name="searchValue" />
			  <s:hidden name="customerId" value="%{#quotation.CUSTOMER_ID}"/>
			  <s:hidden name="channelType" value="%{#quotation.CHANNEL_TYPE}"/>
			</s:form>
		</s:iterator>
	</div>
</body>
    <SCRIPT type="text/javascript">
	if(document.premiumInfo.endTypeId.value!=''){
		enableForm(document.premiumInfo,false,'<s:property value="%{fields}"/>');
	}  
	function quotationDetail()
	{
    	document.getElementById('quoteInfo').style.display='block';
   	    document.getElementById('miuns').style.display='block';
        document.getElementById('plus').style.display='none';
    }
    <s:if test='"RSAIssuer".equalsIgnoreCase(#session.usertype)'>
    	try {
    	setPremiumYN();
    	}catch(e){}
    </s:if>
    function setPremiumYN() {
    	if(document.getElementById('premiumYes').checked == true) {
    		document.getElementById('premiumPaid').disabled = true;
    		document.getElementById('premiumYN').value = document.getElementById('premiumYes').value;
    	}
    	else if(document.getElementById('premiumPaid').checked == true) {
    		document.getElementById('premiumYes').disabled = true;
    		document.getElementById('premiumYN').value = document.getElementById('premiumPaid').value;
    	}
    	else {
    		document.getElementById('premiumYes').disabled = false;
    		document.getElementById('premiumPaid').disabled = false;
    		document.getElementById('premiumYN').value = "";
    	}
    }
    function normalForm()
    {
    	document.getElementById('quoteInfo').style.display='none';
    	document.getElementById('miuns').style.display='none';
    	document.getElementById('plus').style.display='block';
    }
        
   function disablePolicyDetail(obj)
   {
        var displayStatus=obj.value;   
   		if(displayStatus=="N")
   		{
   			document.getElementById('getPolicyDetail').style.display='block';
   		}	    
   		else
   		{
    		document.getElementById('getPolicyDetail').style.display='block';    
    	}
   }
   disablePaymentMethod('<s:property value="generatePolicy" default="N"/>');
   function disablePaymentMethod(readytoPay){
	   if(readytoPay=="Y")
		   document.getElementById('blockPaymentMethod').style.display='block';
	   else
		   document.getElementById('blockPaymentMethod').style.display='none';
   }
   
   if(document.getElementById('policyGeneration') && document.getElementById('getPolicyDetail') && document.premiumInfo.referralUpdate[0].checked){
   		disablePolicyOption('Y');
   }
   function disablePolicyOption(value)
   {
		 if(value=="Y")
		 {
			document.getElementById('policyGeneration').style.display='none';
			document.getElementById('getPolicyDetail').style.display='none';
			document.premiumInfo.generatePolicy[0].checked=false;
			document.premiumInfo.generatePolicy[1].checked=true;
		 }   
		 else {   
		 	document.getElementById('policyGeneration').style.display='block';
		 	document.getElementById('referralComment').value='';
		 	//if(document.premiumInfo.generatePolicy[0].checked){
		 		document.getElementById('getPolicyDetail').style.display='block';
		 	//}
		 } 
    }
    
   function update(actionPath)
    { 
    	document.getElementById('SubmitProceed').disabled=true;
   		document.premiumInfo.action = '${pageContext.request.contextPath}/' + actionPath;
    	document.premiumInfo.submit();
    	return false;
    }
   function enable(value, id)
    { 
   		if('Y'==value || 'YES'==value){
   			document.getElementById(id).readOnly=false;
   		}else{
   			document.getElementById(id).readOnly=true;
   			document.getElementById('commission').value='';
   		}if(id=='referralComment' && 'N'==value){
   			document.getElementById(id).value='';
 		}
    	return false;
    }
   /* if(document.premiumInfo.paymentMode){
    <s:if test="%{!endt}">
    	toggleCredit('<s:property value="%{noteType==null?'N':noteType}"/>');
    </s:if>
    }*/
    function toggleCredit(value)
	{
		var accountStatus='<s:property value="accountStatus"/>';
		var commission='<s:property value="commissionStatus"/>';	
		var opencover='<s:text name="OPEN_COVER"/>';
 	    var session_productCode='<s:property value="#session.product_id"/>';
		if(session_productCode == opencover)
			{				
			   var arInsuredStatus=accountStatus;
			   var arNo='<s:property value="arInsuredStatus"/>';
			}
			else{
				  var arNo=accountStatus;
			}		
 	    
		if(value=='N'){		
			if(!arNo || commission)
			{		
				document.premiumInfo.paymentMode[1].disabled=true;
				document.premiumInfo.paymentMode[1].checked=false;
				document.premiumInfo.paymentMode[0].checked=true;
			}			
			if(session_productCode == opencover && (!arInsuredStatus || commission))
			{					
				document.premiumInfo.paymentMode[2].disabled=true;
				document.premiumInfo.paymentMode[2].checked=false;
				document.premiumInfo.paymentMode[0].checked=true;		
			}
		
		}
		if(value=='G'){
			document.premiumInfo.paymentMode[0].disabled=false;
			if(arNo){
				document.premiumInfo.paymentMode[1].disabled=false;
			}else{
				document.premiumInfo.paymentMode[1].disabled=true;
			}
			if(arInsuredStatus){
				document.premiumInfo.paymentMode[2].disabled=false;
			}else{
				document.premiumInfo.paymentMode[2].disabled=true;
			}
		}
	}
	function directMsg(value)
	{
	  var status='<s:property value="directStatus"/>';
	  if(value=='CA'){		
		   if(status=='Y'){
		   alert('You are trying to create debit note under Cash customer');
		   }
	   }
	}
 	function addConditions(type)
	{
		 var exclude='';
		 var obj='';
		 var ids='';
		 if(type=='1'){
		 	obj=document.form1.clausesIds;
		 }else if(type=='2'){
		 	obj=document.form1.exClausesIds;
		 }else if(type=='3'){
		 	obj=document.form1.exIds;
		 }else if(type=='4'){
		 	obj=document.form1.warIds;
		 }
		 if(obj.length)
		 {
		 	for ( var int = 0; int < obj.length; int++) {
				exclude+=obj[int].value+',';
			}
		 }else{
		 	exclude=obj.value;
		 }
		 if(exclude.lastIndexOf(',')!=-1){
		 	ids=exclude.substring(0, exclude.lastIndexOf(',')-1);
	 	 }
		popUp('${pageContext.request.contextPath}/admin/addConditions.jsp?type='+type+'&exclude='+ids+'&coverId='+document.form1.exisCoverId.value,'1000','500');
	}	
	function validamt(val){
		
		try{
			var value=val.value;
			if(value!=null && value){
				val.value = value.replace(/[^0-9.]/g,'');
			}
			if(val.value=='')
				val.value = '0';
		}catch(err){ val.value = '0';}
	}
	function validamt1(val){
		try{
			var value=val.value;
			if(value!=null){
				val.value = value.replace(/[^0-9]/g,'');
			}
			if(val.value=='')
				val.value = '0';
		}catch(err){val.value = '0';}
	}
	
	function lcupload(policyNo){
		document.premiumInfo.policyNo.value = policyNo; 
		document.premiumInfo.action = "${pageContext.request.contextPath}/lcUploadReport.action?reqFrom=Quote";
		document.premiumInfo.submit();
	}

  </SCRIPT>  
</html>
