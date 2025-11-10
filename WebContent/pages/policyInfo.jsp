<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
        <meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page"> 
		<script language="JavaScript">javascript:window.history.forward(1);</script>
		
    </head>
<body>
<div class="container-fluid">
	<s:form name="policyInfo" theme="simple" action="initReport.action">
		<s:iterator value="policyInformation">
			<div class="panel panel-primary">
				<s:if test="%{#session.product_id == 31 || #session.product_id == 32 || #session.product_id == 33 || #session.product_id == 34}">
					<div class="panel-heading">
			      	   <h4><s:text name="policyInfo.travelInsurance" /></h4>
			      	</div>
			    </s:if>
				<s:elseif test="%{#session.product_id == 41}">
					<div class="panel-heading">
			      	   <h4><s:text name="policyInfo.healthInsurance" /></h4>
			      	</div>
				</s:elseif>
				<s:elseif test="%{#session.product_id == 65}">
					<div class="panel-heading">
			      	   <h4><s:text name="policyInfo.motorInsurance" /></h4>
			      	</div>
				</s:elseif>
				<s:elseif test="%{#session.product_id == 30}">
					<div class="panel-heading">
			      	   <h4><s:text name="policyInfo.homeInsurance" /></h4>
			      	</div>
				</s:elseif>
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
						      	   <h4><s:text name="policyInfo.documentInfo" /></h4>
						      	</div>
				  				<div class="panel-body">
									<div class="row">
										<s:if test="POLICY_NO == null">
								             <div class="col-md-4">
					     					 	<div class="form-group">
								             		<s:label key="policyInfo.draft" />&nbsp;&nbsp;<s:property value="QUOTE_NO"/><br/>
				                  					<s:if test="%{#session.product_id == openCover}">
								             			<s:submit name="Submit2" type="button" cssClass="btn btn-info btn-oval" value="Draft" onclick="return popUp('print4ScheduleOpen.OpenCertificate?policynumber=%{QUOTE_NO}&loginid=%{LOGIN_ID}&printoption=%{PDF_PRE_SHOW_STATUS}&bankerOption=%{PDF_BANKER_STATUS}&displayText=DRAFT&displayMode=draftMode&bankerAssuredOption=%{PDF_BANKER_ASSURED_STATUS}','1000','500')"/>
								             		</s:if>
								             		<s:elseif test="%{#session.product_id == 31 || #session.product_id == 32 || #session.product_id == 33 || #session.product_id == 34 || #session.product_id == 41 || #session.product_id == 65 || #session.product_id == 42}">
								             			<input type="button" name="Submit2" type="button" class="btn btn-info btn-oval" value="Draft" onclick="return popUp('PdfSummary_Draft.Travel?quoteNo='+<s:property value="QUOTE_NO"/>,'1000','500')"/>
								             		</s:elseif>
				     				 				<s:else>
				     				 					<s:submit name="Submit2" type="button" cssClass="btn btn-info btn-oval" value="Draft" onclick="return popUp('print4Schedule.pdfSchedule?policynumber=%{QUOTE_NO}&loginid=%{LOGIN_ID}&printoption=%{PDF_PRE_SHOW_STATUS}&bankerOption=%{PDF_BANKER_STATUS}&displayText=DRAFT&displayMode=draftMode&bankerAssuredOption=%{PDF_BANKER_ASSURED_STATUS}','1000','500')"/>
				     				 				</s:else>
								             	</div>
								             </div>
								             
										</s:if>
										<s:if test="POLICY_NO != null">
								             <div class="col-md-4">
					     					 	<div class="form-group">
					     					 		<s:label key="policyInfo.schedule" />&nbsp;&nbsp;<s:property value="POLICY_NO"/><br/>
					     					 		<s:if test="%{#session.product_id == 31 ||#session.product_id == 32|| #session.product_id == 33 || #session.product_id == 34 || #session.product_id == 41 || #session.product_id == 65 || #session.product_id == 30}">
								                 	 	<input type="button" name="Submit2" type="button" class="btn btn-info btn-oval" value="Schedule" onclick="return popUp('PdfSummary_Schedule.Travel?quoteNo='+<s:property value="QUOTE_NO"/>,'1000','500');"/>
								                  	</s:if>
								                  	<s:else>
								                  		<s:submit name="Submit2" type="button" cssClass="btn btn-info btn-oval" value="Schedule" onclick="return popUp('Copyofinformation.jsp?policyMode=schedule&policynumber=%{POLICY_NO}&loginid=%{LOGIN_ID}','1000','500')"/>
								                  	</s:else> 
					     					 	</div>
					     					 </div>
					     					 <s:if test="%{endTypeId!=null && endTypeId.length() > 0 && (#session.product_id == 3|| #session.product_id == 11)}">
									             <div class="col-md-4">
						     					 	<div class="form-group">
						     					 		<s:label key="policyInfo.endtSchedule" />&nbsp;&nbsp;<s:property value="POLICY_NO"/><br/>
						     					 		<s:submit name="Submit2" type="button" cssClass="btn btn-info btn-oval" value="Endorsement" cssStyle="width:100px" onclick="return popUp('documentReport.action?policyNo=%{POLICY_NO}&applicationNo=%{applicationNo}&endTypeId=%{endTypeId}','1000','500')"/>
						     					 	</div>
						     					 </div>
						     				 </s:if>
										</s:if>
										<s:if test="%{DEBIT_NOTE_NO != null}">
											<s:if test="%{#session.product_id == 31 || #session.product_id == 32 || #session.product_id == 33 || #session.product_id == 34 || #session.product_id == 41 || #session.product_id == 65  || #session.product_id == 30}">
												 <div class="col-md-4">
						     					 	<div class="form-group">
						     					 		<s:label key="policyInfo.debit" />&nbsp;&nbsp;<s:property value="DEBIT_NOTE_NO"/><br/>
						     					 		<input type="button" name="Submit3" type="button" class="btn btn-info btn-oval" value="Debit Note" onclick="return popUp('PdfSummary_Debit.Travel?quoteNo='+<s:property value="QUOTE_NO"/>,'1000','500');"/>
						     					 	</div>
						     					 </div>
						     					 <div class="col-md-4">
						     					 	<div class="form-group">
						     					 		<s:label key="policyInfo.receipt" />&nbsp;&nbsp;<s:property value="RECEIPT_NO"/><br/>
						     					 		<input type="button" name="Submit3" type="button" class="btn btn-info btn-oval" value="Receipt" onclick="return popUp('PdfSummary_Receipt.Travel?quoteNo='+<s:property value="QUOTE_NO"/>,'1000','500');"/>
						     					 	</div>
						     					 </div>
											</s:if>
											<s:else>
												<div class="col-md-4">
						     					 	<div class="form-group">
						     					 		<s:set var="btnLabeldebit" value="TAX Invoice" />
						     					 		<s:if test='%{endTypeId!=null && endTypeId.length() > 0 && "41".equals(endTypeId)}'>
						     					 			<s:label key="TAX Credit note" />
						     					 			<s:set var="btnLabeldebit" value="TAX Credit note" />
						     					 		</s:if>
				                  	 					<s:else>
				                  	 						<s:label key="TAX Invoice" />
				                  	 					</s:else> 
				                  	 					&nbsp;&nbsp;<s:property value="DEBIT_NOTE_NO"/><br/>
				                  	 					<s:submit name="Submit2" type="submit" cssClass="btn btn-info btn-oval" key='%{(endTypeId!=null && endTypeId.length() > 0 && "41".equals(endTypeId))?"TAX Credit note":"TAX Invoice"}' onclick="return popUp('Copyofinformation.jsp?policyMode=vatdebit&policynumber=%{POLICY_NO}&loginid=%{LOGIN_ID}','1000','500')"/>
						     					 	</div>
						     					 </div>
											</s:else>
	                      				</s:if>
	                      				<s:if test="%{#session.product_id == 41}">
											<div class="col-md-4">
					     					 	<div class="form-group">
					     					 		Bill No&nbsp;&nbsp;1111<br/>
					     					 		<input type="button" name="Submit2" type="submit" value="Bill" class="btn btn-info btn-oval" />
					     					 	</div>
					     					 </div>
	                      					 <div class="col-md-4">
					     					 	<div class="form-group">
					     					 		CCHI&nbsp;&nbsp;2222<br/>
					     					 		<input type="button" name="Submit2" type="submit" value="CCHI" class="btn btn-info btn-oval" />
					     					 	</div>
					     					 </div>	
	                      				</s:if>
	                      				<s:if test="%{CREDIT_NOTE_NO != null}">
		               		 				<s:if test='"Y".equalsIgnoreCase(creditNoteStatus)'>
												<div class="col-md-4">
						     					 	<div class="form-group">
				               		 					<s:set var="btnLabelcredit" value="TAX Invoice Raised By Buyer" />
				               		 					<s:if test='%{endTypeId!=null && endTypeId.length() > 0 && "41".equals(endTypeId)}'>
									                  	 	<s:label key="Tax Credit Note Created By Buyer" />
									                  	 	<s:set var="btnLabelcredit" value="Tax Credit Note Created By Buyer" />
									                  	</s:if>
									                  	<s:else>
									                  	 	<s:label key="TAX Invoice Raised By Buyer" />
									                  	</s:else>
									                  	&nbsp;&nbsp;<s:property value="CREDIT_NOTE_NO"/><br/>
									                  	<s:submit name="Submit2" type="submit" cssClass="btn btn-info btn-oval" key='%{(endTypeId!=null && endTypeId.length() > 0 && "41".equals(endTypeId))?"Tax Credit Note Created By Buyer":"TAX Invoice Raised By Buyer"}' onclick="return popUp('Copyofinformation.jsp?policyMode=vatcredit&policynumber=%{POLICY_NO}&loginid=%{LOGIN_ID}','1000','500')"/>
									                 </div>
									            </div>
		               		 				</s:if>
	                      				</s:if>
	                      				<s:if test="%{#session.product_id == 3 && POLICY_NO != null}">
	                      					<div class="col-md-4">
					     					 	<div class="form-group">
					     					 		<s:label key="policyInfo.policyWording" />&nbsp;&nbsp;<s:property value="POLICY_NO"/><br/>
					     					 		<s:submit name="Submit3" type="submit" cssClass="btn btn-info btn-oval" key="policyInfo.policyWording" onclick="return popUp('Copyofinformation.jsp?policyMode=clauses&policynumber=%{POLICY_NO}&loginid=%{LOGIN_ID}','1000','500')"/>
					     					 	</div>
						     				</div>
	                      				</s:if>
	                      				<s:if test='%{#session.product_id == 31 || #session.product_id == 32 || #session.product_id == 33 || #session.product_id == 34 || #session.product_id == 65  || #session.product_id == 30}'>
	                      					<div class="col-md-4">
					     					 	<div class="form-group">
					     					 		<s:label key="policyInfo.policyTermsAndCondition" /><br/>
					     					 		<input type="button" name="Submit3" type="button" class="btn btn-info btn-oval" value="Download" style="width:100px" onclick="return popUp('PdfSummary_PolicyWording.Travel?quoteNo==%{QUOTE_NO}','1000','500');"/>
					     					 	</div>
					     					 </div>
	                      				</s:if>
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
                   		<br/>
						<div class="row" align="center">
                   			<s:if test="%{POLICY_NO==null}">  
                    			<s:if test="%{#session.product_id == 31 || #session.product_id == 32 || #session.product_id == 33 || #session.product_id == 34}">
	                    			<s:if test='referralMsg==null || "".equalsIgnoreCase(referralMsg)'>
	                    				<s:submit name="Submit" type="submit" cssClass="btn btn-danger btn-oval" value="Back"  onclick="update('showQuoteTravel.action')"/>
	                    			</s:if>
	                    			<s:else>
	                    				<s:submit name="Submit" type="submit" cssClass="btn btn-danger btn-oval" value="Back"  onclick="update('initTravel.action')"/>																
	                    			</s:else>
	                    		</s:if>
	                    		<s:elseif test="%{#session.product_id == 41}">																
                    				<s:submit name="Submit" type="submit" cssClass="btn btn-danger btn-oval" value="Back"  onclick="update('showQuoteHealth.action')"/>
	                    		</s:elseif>
	                    		<s:elseif test="%{#session.product_id == 65}">
		                    		<s:if test='referralMsg==null || "".equalsIgnoreCase(referralMsg)'>
	                    				<s:submit name="Submit" type="submit" cssClass="btn btn-danger btn-oval" value="Back"  onclick="update('showPolicyInfoMotor.action')"/>
	                    			</s:if>
	                    			<s:else>
	                    				<s:submit name="Submit" type="submit" cssClass="btn btn-danger btn-oval" value="Back"  onclick="update('showSummarryMotor.action')"/>
	                    			</s:else>
	                    		</s:elseif>
	                    		<s:elseif test='%{#session.product_id == 30}'>
									<s:if test='%{validCoverage!=null && !"".equals(validCoverage)}'>
										<s:submit name="Submit" type="submit" cssClass="btn btn-danger btn-oval" value="Back"  onclick="update('premiumSummaryHome.action')"/>
									</s:if>
									<s:else>
										<s:submit name="Submit" type="submit" cssClass="btn btn-danger btn-oval" value="Back"  onclick="update('showQuoteHome.action')"/>
									</s:else>
								</s:elseif>
	                    		<s:else>
                    				<s:submit name="Submit" type="submit" cssClass="btn btn-danger btn-oval" value="Back"  onclick="update('initPremium.action')"/>
                    			</s:else>  
                    		</s:if>
                    		<s:if test='#session.b2c == "b2c"'>                       
                      			<s:submit name="Submit" type="submit" cssClass="btn btn-success btn-oval" value="Proceed"  onclick="getHome()"/>
                      		</s:if>
                      		<s:else>
                      			<s:submit name="Submit" type="submit" cssClass="btn btn-success btn-oval" value="Proceed"  onclick=""/>
                      		</s:else> 
	                    </div>
						<br/>
				  </div>
			</div>
			
		<s:hidden name="loginId" value="%{LOGIN_ID}"/> 
		</s:iterator>
		<s:hidden name="applicationNo"/> 
		<s:hidden name="quoteNo"/> 
		<s:hidden name="policyNo"/> 
		<s:hidden name="custName"/> 
		<s:hidden name="refNo"/> 
		<s:hidden name="openCoverNo"/>
		<s:hidden name="quoteStatus" />
		<s:hidden name="endTypeId" />
		<s:hidden name="brokerCompany"/>
		<s:hidden name="searchFrom" />
		<s:hidden name="searchBy" />
		<s:hidden name="searchValue" />
		<s:hidden name="referralMsg"/>
		<s:hidden name="menuFrom" value="%{quoteStatus}"/>
		<s:hidden name="paymentMethod"></s:hidden>
		<s:hidden name="generatePolicy"></s:hidden>
	</s:form>
</div>
</body>
<SCRIPT type="text/javascript">
function update(actionPath)
    { 
   		document.policyInfo.action = actionPath;
    	document.policyInfo.submit();
    	return false;
    }
function getHome(){
document.policyInfo.action ='${pageContext.request.contextPath}/login/ProductSelection.jsp'
document.policyInfo.submit();
return false;
}    
<s:if test='"O".equals(paymentMethod) && "Y".equals(generatePolicy) '>
	document.policyInfo.submit();
</s:if>
</SCRIPT>  
</html>
