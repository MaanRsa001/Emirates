<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>Emirates - Quotation</title>
		<script language="JavaScript">javascript:window.history.forward(1);</script>
		<script language="JavaScript">
			function stopRKey(evt) { 
			 	 var evt = (evt) ? evt : ((event) ? event : null); 
			  	 var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
			 	 if ((evt.keyCode == 13) && ((node.type=="text") || (node.type=="password"))) {return false;}
			} 
			document.onkeypress = stopRKey; 
			
			$(window).on('load',function() {
	    		$('#loading').hide();
	  		});
		</script>
		<style>
			#loading {
			  width: 200%;
			  height: 200%;
			  top: 0px;
			  left: 0px;
			  position: fixed;
			  display: block;
			  opacity: 0.7;
			  background-color: #fff;
			  z-index: 100;
			  text-align: center;
			}
			
			#loading-image {
			  position: absolute;
			  top: 30%;
			  left: 45%;
			  z-index: 100;
			}
		</style>
	</head>
	<body>
		<s:set var="disable" value='%{(quoteStatus=="RA")||(endTypeId!=null && endTypeId.length() > 0)}'/>
		<s:set var="disable2" value="%{endTypeId!=null && endTypeId.length() > 0}"/>
		<div id="loading" style="width:100%;"><img id="loading-image" src="${pageContext.request.contextPath}/images/ajax-loader-bar.gif" id="loader"/></div>
		<s:form id="quotation" name="quotation" method="post" action="getQuoteQuotation.action" theme="simple">
			<div class="container-fluid">
				<s:actionerror style="color:red;"/>
				<s:if test='%{endtType!=null && endtType.length() > 0}'>
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
				<s:if test="issuer != null">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<div class="row">
			    				<div class="col-md-12">
			    					<h4><s:text name="quotation.brokerInfo" /></h4>
			    				</div>
			    			</div>
						</div>
						<div class="panel-body">
							<div class="row">
								<s:if test="%{productId!=openCover}">
				    				<div class="col-md-4">
				    					<div class="form-group">
				    						<s:text name="quotation.channelType"  /><font color="red">*</font>
				    						<s:select name="channelType" id="channelType" list="#{'broker':'Broker','customer':'Customer','cash':'Cash'}" headerKey="" headerValue="-Select-" cssClass="form-control" onchange="getList('?channelType='+this.value,'brokersList');getList('?brokerCode=','executiveList');getList('?brokerCode=','promotionalList');emptyCustInfo();" disabled="%{brokerCode!=null && brokerCode!=''}"/>
				    					</div>
				    				</div>
				    			</s:if>
			    				<div class="col-md-4">
			    					<div class="form-group">
			    						<s:text name="quotation.broker"/><font color="red">*</font>
			    						<span id="brokersList"><s:select name="brokerCode" id="brokerCode" list="brokerList" headerKey="" headerValue="-Select-"  listKey="CODE" listValue="CODEDESC" cssClass="form-control" onchange="getList('?brokerCode='+this.value,'executiveList');getList('?brokerCode='+this.value,'promotionalList');emptyCustInfo();" disabled="%{brokerCode!=null && brokerCode!=''}"/></span>
			    						<s:hidden name="brokerName" />
			    					</div>
			    				</div>
			    				<div class="col-md-4">
			    					<div class="form-group">
			    						<s:text name="quotation.executive" /><font color="red">*</font>
			    						<span id="executiveList"><s:select name="executive" list="executiveList" headerKey="" headerValue="-Select-" listKey="CODE" listValue="CODEDESC" cssClass="form-control" disabled="#disable" value='executive==null?getText("quotation.executiveDefault"):executive'/></span>
			    					</div>
			    				</div>
							</div>
						</div>
					</div>
				</s:if>
		        <s:else>
		        	<s:hidden name="channelType"/>
		        </s:else>
		        <div class="panel panel-primary">
		        	<div class="panel-heading">
						<div class="row">
		       				<div class="col-md-3 col-xs-6">
					         	<h4><span class="glyphicon glyphicon-user"></span>&nbsp;<s:text name="quotation.customerInfo" /></h4>
					         </div>
					         <div class="col-md-1 col-xs-3 col-xs-offset-3 col-md-offset-8">
					           	<input type="button" value="Clear" style="cursor:pointer;" class="btn btn-default" onclick="clearCustomerInfo();"/>
					         </div>
		    			</div>
					</div>
					<div class="panel-body">
						<div class="row">
			    			<div class="col-md-4">
					    		<div class="form-group">
									<s:text name="quotation.title"  /><font color="red">*</font>
									<s:select name="title" id="title" list="titleList" headerKey="" headerValue="-Select-"  listKey="CODEDESC" listValue="CODEDESC" cssClass="form-control" disabled="#disable"/>
								</div>
							</div>
			    			<div class="col-md-4">
					    		<div class="form-group">
									<s:text name="quotation.customerName" /><font color="red">*</font>
									<div class="input-group">
						                <s:textfield name="customerName" id="customerName" class="form-control" maxlength="200" disabled="#disable" onchange="setCustomerId();"/>
							            <s:hidden name="customerId" id="customerId" />
						                <span class="input-group-addon" >
						                	<a onclick="customerSelectionAction()" style="cursor: pointer" data-toggle="modal" data-target="#customerSelectionModal" disabled="#disable"><span class="glyphicon glyphicon-list"></a>
						                </span>
						            </div> 
								</div>
							</div>
			    			<div class="col-md-4">
					    		<div class="form-group">
									<s:text name="quotation.coreAppCode" />
									<s:textfield name="coreAppCode" id="coreAppCode" cssClass="form-control" maxlength="20" readonly="true" disabled="#disable"/>
		                        	<s:hidden name="nameAndCode" id="nameAndCode"/>
		                        	<s:hidden name="custArNo" id="custArNo"/>
		                        	<s:hidden name="coreCustomerName" id="coreCustomerName"/>
								</div>
							</div>
						</div>
						<div class="row">
			    			<div class="col-md-4">
					    		<div class="form-group">
					    			<s:text name="quotation.address1" />
					    			<s:textfield name="address1" id="address1" cssClass="form-control"  maxlength="200" disabled="#disable"/>
					    		</div>
					    	</div>
			    			<div class="col-md-4">
					    		<div class="form-group">
					    			<s:text name="quotation.address2" />
			                        <s:textfield name="address2" id="address2" cssClass="form-control" maxlength="200" disabled="#disable"/>
					    		</div>
					    	</div>
			    			<div class="col-md-4">
					    		<div class="form-group">
					    			<s:text name="quotation.city" /><font color="red">*</font>
					    			<s:select name="city" id="city" list="cityList" headerKey="" headerValue="-Select-" listKey="CODEDESC" listValue="CODEDESC" cssClass="form-control" disabled="#disable"/>
					    		</div>
					    	</div>
						</div>
						<div class="row">
			    			<div class="col-md-4">
					    		<div class="form-group">
					    			<s:text name="quotation.poBox" /><font color="red">*</font>
					    			<s:textfield name="poBox" id="poBox" cssClass="form-control" disabled="#disable" maxlength="6" onchange="checkNumbers(this);" />
					    		</div>
					    	</div>
			    			<div class="col-md-4">
					    		<div class="form-group">
					    			<s:text name="quotation.mobileNo" />
			                        <s:textfield name="mobileNo" id="mobileNo" cssClass="form-control" disabled="#disable"  maxlength="10" onchange="checkNumbers(this);" />
					    		</div>
					    	</div>
			    			<div class="col-md-4">
					    		<div class="form-group">
					    			<s:text name="quotation.email" />
					    			<s:textfield name="email" id="email" cssClass="form-control" disabled="#disable" maxlength="100"/>
					    		</div>
					    	</div>
						</div>
						<div class="row">
			    			<div class="col-md-4">
					    		<div class="form-group">
					    			<s:text name="Customer VAT Reg No" />
					    			<s:if test='"NO".equalsIgnoreCase(custVatRegYn)'>
				                        <s:textfield name="custVatRegNo" id="custVatRegNo" cssClass="form-control" maxlength="20" readonly="false" disabled="#disable"/>
				                     </s:if>
				                     <s:else>
				                     	<s:textfield name="custVatRegNo" id="custVatRegNo" cssClass="form-control" maxlength="20" readonly="true" disabled="#disable"/>
				                     </s:else>
				                     <s:hidden name="custVatRegYn" id="custVatRegYn"/>
					    		</div>
					    	</div>
					    	<s:if test='"admin".equalsIgnoreCase(#session.usertype) || ("RSAIssuer".equalsIgnoreCase(#session.usertype) && "Y".equalsIgnoreCase(vatApplicableNone))'>
				    			<div class="col-md-4">
						    		<div class="form-group">
						    			<s:text name=" VAT Applicable" />
				                        <s:radio list="#{'Y':'Yes','N':'No'}" name="vatApplicable" id="vatApplicable"  value='%{(vatApplicable==null || "".equals(vatApplicable))?"Y":vatApplicable}' disabled="#disable" onclick="editCustomerInfo();"/>
						    		</div>
						    	</div>
					    	</s:if>
						</div>
						<div class="row" id="editCustomerDIV">
			        		<s:if test='%{productId==openCover && customerId.equals(#session.customer_id) && !(#disable)}'>
				    			<div class="col-md-4">
						    		<div class="form-group">
						    			<s:text name="quotation.editCustomer" />
			                            <s:radio list="#{'YES':'Yes','NO':'No'}" name="editCustomer" id="editCustomer"  value='%{(editCustomer==null || "".equals(editCustomer))?"NO":editCustomer}' disabled="#disable" onclick="editCustomerInfo();"/>
						    		</div>
						    	</div>
							</s:if>
					    </div>
					</div>
		        </div>
		        <div class="panel panel-primary">
		        	<div class="panel-heading">
						<div class="row">
					         <div class="col-md-3">
					         	<h4><span class="glyphicon glyphicon-list-alt"></span>&nbsp;<s:text name="quotation.quoteInfo" /></h4>
					         </div>
				       	</div> 
					</div>
					<div class="panel-body">
						<div class="row">
			    			<div class="col-md-4">
					    		<div class="form-group">
					    			<s:text name="quotation.modeOfTransport" /><font color="red">*</font>
					    			<s:select name="modeOfTransport" id="modeOfTransport" list="modeList" headerKey="" headerValue="-Select-" listKey="CODE" listValue="CODEDESC" cssClass="form-control" disabled="#disable"  onchange="getList('?modeOfTransport='+this.value,'coverList');getList('?modeOfTransport='+this.value,'conveyanceList');getList('?modeOfTransport='+this.value,'packageList');disableWarehouse(this);toggleWarSrcc(this);setVesselName();toggleshipment();"  />
					    		</div>
					    	</div>
			    			<div class="col-md-4">
					    		<div class="form-group">
					    			<s:text name="quotation.cover" /><font color="red">*</font>
		                            <span id="coverList"><s:select name="cover" id="cover" list="coverList" headerKey="" headerValue="-Select-" listKey="CODE" listValue="CODEDESC" cssClass="form-control" disabled="#disable" /></span>
					    		</div>
					    	</div>
			    			<div class="col-md-4">
					    		<div class="form-group">
					    			<s:text name="quotation.conveyance" /><font color="red">*</font>
					    			<span id="conveyanceList"><s:select name="conveyance" list="conveyanceList" listKey="CODE" listValue="CODEDESC" headerKey="" headerValue="-Select-" cssClass="form-control" disabled="#disable" /></span>
					    		</div>
					    	</div>
					    </div>
					    <div class="row">
			    			<div class="col-md-4">
					    		<div class="form-group">
					    			<s:text name="quotation.originCountry" /><font color="red">*</font>
					    			<s:select name="originCountry" id="originCountry" list="originList" headerKey="" headerValue="-Select-" listKey="CODE" listValue="CODEDESC" cssClass="form-control" disabled="#disable" value="%{originCountry==null?'1':originCountry}" onchange="emptyCity('orgin');"/>
					    		</div>
					    	</div>
			    			<div class="col-md-4">
					    		<div class="form-group">
					    			<s:text name="quotation.originCity" />
		                             <div class="input-group">
						                <s:textfield name="originCityName" id="originCityName" cssClass="form-control" disabled="#disable" readonly="true"/>
							            <s:hidden name="originCity" id="originCity"/>
						                <span class="input-group-addon" >
						                	<a style="cursor: pointer" onclick="originCitySelectionAction();" name="originCityBtn" data-toggle="modal" data-target="#originationCityModal" disabled="#disable"><span class="glyphicon glyphicon-list"></a>
						                </span>
						            </div>
					    		</div>
					    	</div>
			    			<div class="col-md-4">
					    		<div class="form-group">
					    			<s:text name="quotation.originWarehouse" /><br/>
					    			<s:radio list="#{'YES':'Yes','NO':'No'}" name="originWarehouse" id="originWarehouse"  value='%{originWarehouse==null?"NO":originWarehouse}' disabled="#disable"/>
					    		</div>
					    	</div>
					    </div>
					    <div class="row">
			    			<div class="col-md-4">
					    		<div class="form-group">
					    			<s:text name="quotation.destCountry" /><font color="red">*</font>
					    			<s:select name="destCountry" id="destCountry" list="destList" headerKey="" headerValue="-Select-" listKey="CODE" listValue="CODEDESC" cssClass="form-control" disabled="#disable"  onchange="getList('?quoteStatus=%{quoteStatus}&destCountry='+this.value,'agentList');emptyCity('dest');" value="%{destCountry==null?'1':destCountry}"/>
					    		</div>
					    	</div>
			    			<div class="col-md-4">
					    		<div class="form-group">
					    			<s:text name="quotation.destCity" />
		                             <div class="input-group">
						                <s:textfield name="destCityName" id="destCityName" cssClass="form-control" disabled="#disable" readonly="true"/>
						                <s:hidden name="destCity" id="destCity"/>
						                <span class="input-group-addon" >
						                	<a style="cursor: pointer" name="destCityBtn" data-toggle="modal" data-target="#destinationCityModal" onclick="destCitySelectionAction();" disabled="#disable"><span class="glyphicon glyphicon-list"></a>
						                </span>
						            </div>
					    		</div>
					    	</div>
			    			<div class="col-md-4">
					    		<div class="form-group">
					    			<s:text name="quotation.destWarehouse" /><br/>
					    			<s:radio list="#{'YES':'Yes','NO':'No'}" name="destWarehouse"  value='%{destWarehouse==null?"NO":destWarehouse}' disabled="#disable"/>
					    		</div>
					    	</div>
					    </div>
					    <div class="row">
			    			<div class="col-md-4">
					    		<div class="form-group">
									<sj:head jqueryui="true" jquerytheme="start" />
					    			<s:text name="quotation.policyStartDate" /><font color="red">*</font>
					    			<sj:datepicker id="policyStartDate" name="policyStartDate" cssClass="form-control" displayFormat="dd/mm/yy" readonly="true" showAnim="slideDown" duration="fast" disabled="#disable" />
					    		</div>
					    	</div>
			    			<div class="col-md-4">
					    		<div class="form-group">
					    			 <s:text name="quotation.currency" /><font color="red">*</font>
					    			 <div class="input-group">
						    			 <s:select name="currency" id="currency" list="currencyList" headerKey="" headerValue="-Select-" listKey="CODE" listValue="CODEDESC" cssClass="form-control" disabled="#disable"  onchange="exchageRate(this,'currency')"/>
						    			 <span class="input-group-addon" >
						    			  	<s:textfield id="exchageValue" name="exchageValue" disabled="#disable" cssStyle="border:none;"/>
						    			 </span>
					    			 </div>
					    			 <s:iterator value="currencyList" var="currencyDetail">
	                                	<s:hidden name="%{#currencyDetail.CODE}" id="%{#currencyDetail.CODE}" value="%{#currencyDetail.CODEVALUE}"/>
	                                 </s:iterator>
					    		</div>
					    	</div>
					    	<s:if test='warOption=="Y"'>
				    			<div class="col-md-2">
						    		<div class="form-group">
						    			<span id="warSrccId"><s:text name="quotation.warSrcc"/></span><br/>
						    			<s:radio list="#{'YES':'Yes','NO':'No'}" name="warSrcc"  value='%{warSrcc==null?"YES":warSrcc}' onchange="toggleshipment();" disabled="#disable"/>
						    		</div>
						    	</div>
						    	<div class="col-md-2" id="shipmentId" style="display: none">
						    		<div class="form-group">
						    			<s:text name="Shipment Transiting"/><br/>
						    			<s:select name="shipmenttransit" id="shipmenttransit" list="shipmentTransitList" headerKey="" headerValue="-Select-" listKey="CODE" listValue="CODEDESC" cssClass="form-control" disabled="#disable" />
						    		</div>
						    	</div>
						   </s:if>
					    </div>     
					    <div class="row">
			    			<div class="col-md-4">
					    		<div class="form-group">
					    			<s:text name="quotation.commodity" /><font color="red">*</font>
					    			<div class="input-group">
						    			<s:textarea name="commodity" id="commodity" cssClass="form-control" disabled="#disable" readonly="true"/>
						    			<span class="input-group-addon" >
						    				<a style="cursor: pointer" data-toggle="modal" data-target="#commoditySelectionModal" onclick="commoditySelectionAction()" disabled="#disable"><span class="glyphicon glyphicon-list"></a>
						    			</span>
					    			</div>
					    		</div>
					    	</div>
			    			<div class="col-md-4">
					    		<div class="form-group">
					    			<s:text name="quotation.totalCommodity" />
							        <s:textfield name="totalCommodity"  id="totalCommodity" cssClass="form-control" disabled="#disable" readonly="true"/>
					    		</div>
					    	</div>
			    			<div class="col-md-4">
					    		<div class="form-group">
					    			<s:text name="quotation.totalSI" />
					    			<s:textfield name="totalSI" id="totalSI" cssClass="form-control" disabled="#disable" readonly="true" maxlength="20"/>
					    		</div>
					    	</div>
					    </div>
					    <div class="row">
			    			<div class="col-md-4">
					    		<div class="form-group">
					    			<s:text name="quotation.saleTerms" /><font color="red">*</font>
					    			<s:select name="saleTerm" list="saleTermList" headerKey="" headerValue="-Select-" listKey="CODE" listValue="CODEDESC" cssClass="form-control" disabled="#disable" onchange="getList('?saleTerm='+this.value,'percentList');"/>
					    		</div>
					    	</div>
			    			<div class="col-md-4">
					    		<div class="form-group">
					    			<s:text name="quotation.percentage"/><font color="red">*</font>
	                                <s:if test="%{productId==openCover}" >
	                                	<span id="percentList"><s:select name="saleTermPercent" list="percentList" headerKey="" headerValue="-Select-" listKey="CODE" listValue="CODEDESC" cssClass="form-control" disabled="#disable"  onchange="getList('?saleTermPercent='+this.value,'toleranceList');"/></span>
	                                </s:if>
	                                <s:else>
	                                	<span id="percentList"><s:select name="saleTermPercent" id="percent" list="percentList" headerKey="" headerValue="-Select-" listKey="CODE" listValue="CODEDESC" cssClass="form-control" disabled="%{saleTerm=='205' || #disable}"  onchange="getList('?saleTermPercent='+this.value,'toleranceList');"/></span>                               
	                               	</s:else>
		                            <s:hidden name="saleTermDecVal"/>
					    		</div>
					    	</div>
			    			<div class="col-md-4">
					    		<div class="form-group">
					    			<s:text name="quotation.tolerance" />
					    			<span id="toleranceList"><s:select name="tolerance" list="toleranceList" listKey="CODE" listValue="CODEDESC" cssClass="form-control" disabled="#disable" value='tolerance==null?getText("quotation.toleranceDecVal"):tolerance'/></span>
					    		</div>
					    	</div>
					    </div>   
					    <s:if test="%{productId==openCover}" >
						    <div class="row">
				    			<div class="col-md-4">
						    		<div class="form-group">
						    			<s:text name="quotation.partialShipment" />
						    			<s:select list="#{'N':'None','Y':'Partial','M':'Multiple'}" cssClass="form-control" name="partialShipment"  value='%{partialShipment==null?"N":partialShipment}' disabled="#disable2" onchange="if(this.value=='N'){this.form.shipmentExposure.value='';this.form.exposureCurrency.value='';this.form.exposureValue.value='';this.form.shipmentExposure.disabled=true;this.form.exposureCurrency.disabled=true;this.form.exposureValue.disabled=true;}else{this.form.shipmentExposure.disabled=false;this.form.exposureCurrency.disabled=false;this.form.exposureValue.disabled=false;}"/>
						    		</div>
						    	</div>
				    			<div class="col-md-4">
						    		<div class="form-group">
						    			<s:text name="quotation.exposureOfShipment" /><font color="red">*</font>
		                                <s:textfield name="shipmentExposure" id="shipmentExposure" cssClass="form-control" disabled='%{#disable2==false?(partialShipment==null || partialShipment=="N"):#disable2}' maxlength="20"/>
						    		</div>
						    	</div>
				    			<div class="col-md-4">
						    		<div class="form-group">
						    			<s:text name="quotation.currencyOfExposure" /><font color="red">*</font>
						    			<div class="input-group">
							    			<s:select name="exposureCurrency" id="exposureCurrency" list="currencyList" headerKey="" headerValue="-Select-" listKey="CODE" listValue="CODEDESC" cssClass="form-control" onchange="exchageRate(this,'exposureCurrency')" disabled='%{#disable2==false?(partialShipment==null || partialShipment=="N"):#disable2}'/>
							    			<span class="input-group-addon" >
							    			  	<s:textfield id="exposureValue" name="exposureValue" readonly="true"  cssStyle="border:none;" disabled='%{#disable2==false?(partialShipment==null || partialShipment=="N"):#disable2}' maxlength="20"/>
							    			</span>
						    			</div>
						    			<s:iterator value="currencyList" var="currencyDetail">
		                                	<s:hidden name="%{#currencyDetail.CODE}" id="%{#currencyDetail.CODE}" value="%{#currencyDetail.CODEVALUE}"/>
		                                </s:iterator>
						    		</div>
						    	</div>
						    </div>
					    </s:if>
					    <div class="row">
			    			<div class="col-md-4">
					    		<div class="form-group">
					    			<s:text name="quotation.vesselName" />
					    			<div class="input-group">
						    			<s:textfield name="vesselName" id="vesselName" cssClass="form-control" disabled="#disable2" maxlength="100" readonly="true"/>
						    			<s:hidden name="vesselId" id="vesselId"/>
						    			<span class="input-group-addon" >
						    				<a onclick="callvessel()" style="cursor: pointer" data-toggle="modal" data-target="#vesselSelectionModal" name="vesselBtn" disabled="#disable2"><span class="glyphicon glyphicon-list"></a>
						    			</span>
					    			</div>
					    		</div>
					    	</div>
					    	<div class="col-md-4">
					    	<div class="form-group">
				    			<s:text name="quotation.vessalimoNo"/>
				    			<s:textfield name="vesselImoNo" id="vesselImoNo" cssClass="form-control"/>
					    	</div>
					    	</div>
			    			<div class="col-md-4">
					    		<div class="form-group">
					    			<s:text name="quotation.settlingAgent" /><font color="red">*</font>
	                                <span id="agentList">
	                                	<s:if test='settlingAgent!=null && "0".equals(settlingAgent) && !("admin".equalsIgnoreCase(#session.usertype) || "RSAIssuer".equalsIgnoreCase(#session.usertype)) && "RA".equalsIgnoreCase(quoteStatus)'>
	                            			<s:select name="settlingAgent" id="settlingAgent" list="agentList" headerKey="" headerValue="--Select--" listKey="CODE" listValue="CODEDESC" cssClass="form-control"  onchange="disableOthers(this);" disabled="true" />
	                            		</s:if>
	                            		<s:else>
	                            			<s:select name="settlingAgent" id="settlingAgent" list="agentList" headerKey="" headerValue="--Select--" listKey="CODE" listValue="CODEDESC" cssClass="form-control"  onchange="disableOthers(this);" disabled="#disable2" />
	                            		</s:else>
	                                </span>
					    		</div>
					    	</div>
			    			<div class="col-md-4">
					    		<div class="form-group">
					    			<s:text name="quotation.agentOthers" />
					    			<s:if test='settlingAgent!=null && "0".equals(settlingAgent) && !("admin".equalsIgnoreCase(#session.usertype) || "RSAIssuer".equalsIgnoreCase(#session.usertype)) && "RA".equalsIgnoreCase(quoteStatus)'>
	                               		<s:textarea name="agentOthers" id="agentOthers" cssClass="form-control" disabled="true" maxlength="1000"/>
	                               	</s:if>
	                               	<s:else>
	                               		<s:textarea name="agentOthers" id="agentOthers" cssClass="form-control" disabled="#disable2" maxlength="1000"/>
	                               	</s:else>
					    		</div>
					    	</div>
					    </div>
	                    <s:hidden name="consigneeDetail" value=""/>
	                    <s:hidden name="addCustomerName" value=""/>
			           	<div class="row">
			           		<s:if test='(specialTerm!=null && !"".equals(specialTerm)) || ("admin".equalsIgnoreCase(#session.usertype)||"RSAIssuer".equalsIgnoreCase(#session.usertype))'>
				    			<div class="col-md-4">
						    		<div class="form-group">
						    			<s:text name="Special Term & Condition" />
						    			<s:textarea name="specialTerm" cssClass="form-control" onkeyup="textLimit(this,1000)" disabled='%{(("admin".equalsIgnoreCase(#session.usertype)||"RSAIssuer".equalsIgnoreCase(#session.usertype)) && #disable2!=true)?"false":"true"}'/>
						    		</div>
						    	</div>
						    </s:if>
			    			<div class="col-md-4">
					    		<div class="form-group">
					    			<s:text name="quotation.packageDesc" />
		                            <span id="packageList">
		                               <s:select name="packageCode" list="packageList" headerKey="" headerValue="-Select-" listKey="CODE" listValue="CODEDESC" cssClass="form-control"   disabled="#disable2" />
		                            </span>
					    		</div>
					    	</div>
					    	<s:if test="%{productId!=openCover}" >
				    			<div class="col-md-4">
						    		<div class="form-group">
						    			<s:text name="quotation.partialShipment" /><br/>
						    			<s:radio list="#{'Y':'Yes','N':'No'}" name="partialShipment"  value='%{partialShipment==null?"N":partialShipment}' disabled="#disable2"/>
						    		</div>
						    	</div>
						    </s:if>
				    	</div>
					</div>
				</div>
				<div class="panel panel-primary">
		        	<div class="panel-heading">
						<div class="row">
		       				<div class="col-md-3 col-xs-6" id="plus" onclick="quotationDetail(true);" style="display: none; cursor: pointer;" >
					         	<h4>+&nbsp;&nbsp;<span class="glyphicon glyphicon-user"></span>&nbsp;<s:text name="LC Bank Details" /></h4>
					         </div>
		       				<div class="col-md-3 col-xs-6" id="miuns" onclick="quotationDetail(false);" style="display: block; cursor: pointer;" >
					         	<h4>-&nbsp;&nbsp;<span class="glyphicon glyphicon-user"></span>&nbsp;<s:text name="LC Bank Details" /></h4>
					         </div>
		    			</div>
					</div>
					<div class="panel-body" id="quoteInfo">
						<div class="row">
							<s:if test="%{productId==openCover}" >
				    			<div class="col-md-4">
						    		<div class="form-group">
						    			<s:text name="quotation.lcBank" /><font color="red">*</font>
						    			<s:if test='lcBankList!=null && lcBankList.size==1'>
	                               			<s:select name="lcBank" list="lcBankList" listKey="CODE" listValue="CODEDESC" cssClass="form-control"  onchange="getList('?lcBank='+this.value,'lcList');" disabled="%{#disable2==false?(quoteStatus=='RA'):#disable2}"/>
	                               		</s:if>
	                               		<s:else>
	                               			<s:select name="lcBank" list="lcBankList" headerKey=""  headerValue="-Select-" listKey="CODE" listValue="CODEDESC" cssClass="form-control"  onchange="getList('?lcBank='+this.value,'lcList');" disabled="%{#disable2==false?(quoteStatus=='RA'):#disable2}"/>
	                               		</s:else>
						    		</div>
						    	</div>
						    </s:if>
						    <s:else>
						    	<div class="col-md-4">
						    		<div class="form-group">
						    			<s:text name="quotation.lcBank" />
						    			<s:select name="lcBank" id="lcBank" list="lcBankList" headerKey="" headerValue="-Select-" listKey="CODE" listValue="CODEDESC" cssClass="form-control"  disabled="%{#disable2==false?(quoteStatus=='RA'):#disable2}"/>
						    		</div>
						    	</div>
						    </s:else>
						    <s:if test="%{productId==openCover}" >
						    	<div class="col-md-4">
						    		<div class="form-group">
						    			<s:text name="quotation.lcNo" /><font color="red">*</font>
						    			<span id="lcList">
							    			<s:if test='lcList!=null && lcList.size==1'>
		                               			<s:select name="lcNo" list="lcList" listKey="CODE" listValue="CODEDESC" cssClass="form-control" disabled="%{#disable2==false?(quoteStatus=='RA'):#disable2}"/>
		                               		</s:if>
		                               		<s:else>
		                               			<s:select name="lcNo" list="lcList" headerKey="" headerValue="-Select-" listKey="CODE" listValue="CODEDESC" cssClass="form-control" disabled="%{#disable2==false?(quoteStatus=='RA'):#disable2}"/>
		                               		</s:else>
	                               		</span>
						    		</div>
						    	</div>
						    </s:if>
						    <s:else>
						    	<div class="col-md-4">
						    		<div class="form-group">
						    			<s:text name="quotation.lcNo" />
						    			<s:textfield name="lcNo" cssClass="form-control" maxlength="30" disabled="#disable2"/>
						    		</div>
						    	</div>
						    </s:else>
						    <div class="col-md-4">
						    	<div class="form-group">
						    		<s:text name="quotation.lcDate" />
						    		<sj:datepicker id="lcDate" name="lcDate" cssClass="form-control" displayFormat="dd/mm/yy" showAnim="slideDown" duration="fast" disabled="#disable2"  onblur="check_date(this);" onchange="check_date(this);" showOn="%{#disable2?'focus':''}" />
						    	</div>
						    </div>
					    </div>    
		                <div class="row">
			    			<div class="col-md-4">
					    		<div class="form-group">
					    			<s:text name="quotation.blNo" />
					    			<s:textfield name="blNo" cssClass="form-control"  maxlength="25" disabled="#disable2"/>
					    		</div>
					    	</div>
			    			<div class="col-md-4">
					    		<div class="form-group">
					    			<s:text name="quotation.blDate" />
					    			<sj:datepicker id="blDate" name="blDate" cssClass="form-control" displayFormat="dd/mm/yy" showAnim="slideDown" duration="fast" disabled="#disable2" onchange="check_date(this);" onblur="check_date(this);" showOn="%{#disable2?'focus':''}" />
					    		</div>
					    	</div>
			    			<div class="col-md-4">
					    		<div class="form-group">
					    			<s:text name="quotation.sailingDate" />
					    			<sj:datepicker id="sailingDate" name="sailingDate" cssClass="form-control" displayFormat="dd/mm/yy"  showAnim="slideDown" duration="fast" disabled="#disable2" onchange="check_date(this);" onblur="check_date(this);" showOn="%{#disable2?'focus':''}"/>
					    		</div>
					    	</div>
					    </div>
					    <div class="row" id="promotionalList">
					    	<s:if test='%{(promotionalCode!=null && !"".equals(promotionalCode)) || dubaiTradeStatus}'>
				    			<div class="col-md-4" >
						    		<div class="form-group">
						    			<s:text name="Promotional Code" />
						    			<s:textfield name="promotionalCode" cssClass="form-control"  maxlength="25" disabled="#disable2"/>
						    		</div>
						    	</div>
						    </s:if>
						    <s:else>
						    	<s:hidden name="promotionalCode" value=""/>
						    </s:else>
					    </div>
		                <s:if test="brokerType == brokerOne">     
			                <div class="row">
				    			<div class="col-md-4">
						    		<div class="form-group">
						    			<s:text name="quotation.custContractNo" /><s:if test="%{productId==openCover}" ><font color="red">*</font></s:if>
						    			<s:textfield name="custContractNo" cssClass="inputBox"  disabled="#disable2"/>
						    		</div>
						    	</div>
				    			<div class="col-md-4">
						    		<div class="form-group">
						    			<s:text name="quotation.custFmsCaseNo" /><s:if test="%{productId==openCover}" ><font color="red">*</font></s:if>
						    			<s:textfield name="custFmsCaseNo" cssClass="inputBox"  disabled="#disable2"/>
						    		</div>
						    	</div>
				    			<div class="col-md-4">
						    		<div class="form-group">
						    			<s:text name="quotation.custRefNo" /><s:if test="%{productId==openCover}" ><font color="red">*</font></s:if>
						    			<s:textfield name="custRefNo" cssClass="inputBox"  disabled="#disable2"/>
						    		</div>
						    	</div>
						    </div>
						</s:if>
		                <s:hidden name="brokerType"/>
					</div>
				</div>
				<br/>
			    <div class="row" align="center">
			    	<s:if test="%{endTypeId!=null && endTypeId.length() > 0}">
	                    <s:submit type="button" name="close"  key="Back" cssClass="btn btn-danger btn-oval" onclick="forward('initReport.action?menuType=ET')"/>&nbsp;&nbsp;&nbsp;&nbsp;
			    	</s:if>
			    	<s:submit name="Submit2" type="submit" cssClass="btn btn-warning btn-oval" value="Cancel" onclick="callAction('%{quoteStatus}','%{#session.user1}');" />&nbsp;&nbsp;&nbsp;&nbsp;
			    	<s:submit name="SubmitGetQuote" id="SubmitGetQuote" type="submit" cssClass="btn btn-success btn-oval" value="Get Quote" onclick="disableSubmitGetQuote();disableForm(this.form,false,'')"/>&nbsp;&nbsp;&nbsp;&nbsp;
			    	<s:hidden name="applicationNo" id="applicationNo"/>
	                <s:hidden name="refNo" />
	                <s:hidden name="quoteNo" />
	                <s:hidden name="loginId" />
	                <s:hidden name="quoteStatus" />
	                <s:hidden name="endTypeId" />
	                <s:hidden name="policyNo"/> 
				  	<s:hidden name="custName"/>
				  	<s:hidden name="brokerCompany"/>
				  	<s:hidden name="searchFrom" />
				  	<s:hidden name="searchBy" />
				  	<s:hidden name="searchValue" />
			    	
			    	<s:if test='%{"admin".equalsIgnoreCase(#session.user1) || "Admin".equals(#session.user1)}'>
	               		<s:hidden name="executive"/>
	               	</s:if>
			    </div>
			</div>
			<br/>
		</s:form>
		<div id="customerSelectionModal" class="modal fade" role="dialog">
		  <div class="modal-dialog modal-lg">
		    <!-- Modal content-->
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal">&times;</button>
	        	<div class="modal-title">
					<div class="row">
	       				<div class="col-md-12 col-xs-12">
				         	<h3><span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;&nbsp;<s:text name="Customer Selection" /></h3>
				         </div>
	    			</div>
				</div>
		      </div>
		      <div class="modal-body" >
		        <div class="container-fluid" id="customerSelectionAjaxId">
		        </div>
		      </div>
		    </div>
		  </div>
		</div>
		<div id="originationCityModal" class="modal fade" role="dialog">
		  <div class="modal-dialog modal-lg">
		    <!-- Modal content-->
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal">&times;</button>
	        	<div class="modal-title">
					<div class="row">
	       				<div class="col-md-12 col-xs-12">
				         	<h3><s:text name="City Of Origin Country" /></h3>
				         </div>
	    			</div>
				</div>
		      </div>
		      <div class="modal-body" >
		        <div class="container-fluid" id="originationCityAjaxId">
		        </div>
		      </div>
		    </div>
		  </div>
		</div>
		<div id="originationCityModal" class="modal fade" role="dialog">
		  <div class="modal-dialog modal-lg">
		    <!-- Modal content-->
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal">&times;</button>
	        	<div class="modal-title">
					<div class="row">
	       				<div class="col-md-12 col-xs-12">
				         	<h3><s:text name="City Of Originating Country" /></h3>
				         </div>
	    			</div>
				</div>
		      </div>
		      <div class="modal-body" >
		        <div class="container-fluid" id="originationCityAjaxId">
		        </div>
		      </div>
		    </div>
		  </div>
		</div>
		<div id="destinationCityModal" class="modal fade" role="dialog">
		  <div class="modal-dialog modal-lg">
		    <!-- Modal content-->
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal">&times;</button>
	        	<div class="modal-title">
					<div class="row">
	       				<div class="col-md-12 col-xs-12">
				         	<h3><s:text name="City Of Destination Country" /></h3>
				         </div>
	    			</div>
				</div>
		      </div>
		      <div class="modal-body" >
		        <div class="container-fluid" id="destinationCityAjaxId">
		        </div>
		      </div>
		    </div>
		  </div>
		</div>
		<div id="commoditySelectionModal" class="modal fade" role="dialog">
		  <div class="modal-dialog modal-lg">
		    <!-- Modal content-->
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal">&times;</button>
	        	<div class="modal-title">
					<div class="row">
	       				<div class="col-md-12 col-xs-12">
				         	<h3><s:text name="Commodity Selection" /></h3>
				         </div>
	    			</div>
				</div>
		      </div>
		      <div class="modal-body" >
		        <div class="container-fluid" id="commoditySelectionAjaxId">
		        </div>
		      </div>
		    </div>
		  </div>
		</div>
		<div id="vesselSelectionModal" class="modal fade" role="dialog">
		  <div class="modal-dialog modal-lg">
		    <!-- Modal content-->
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal">&times;</button>
	        	<div class="modal-title">
					<div class="row">
	       				<div class="col-md-12 col-xs-12">
				         	<h3><s:text name="Vessel Selection" /></h3>
				         </div>
	    			</div>
				</div>
		      </div>
		      <div class="modal-body" >
		        <div class="container-fluid" id="vesselSelectionAjaxId">
		        </div>
		      </div>
		    </div>
		  </div>
		</div>
		<script type="text/javascript">
			function validNumber(val){
				var value=val.value;
				if(value!=null){
					val.value = value.replace(/[^,0-9]/g,'');
				}
			}
			
			function disableSubmitGetQuote(){
				document.getElementById('SubmitGetQuote').disabled=true;
			}
			
		
			if(document.quotation.endTypeId.value!=''){
				enableForm(document.quotation,false,'<s:property value="%{fields}"/>');
			}
			setBasisVal(document.quotation.saleTerm, 'percentList');
			
			function setBasisVal(obj, id) {
				 var flag=true;
				 var value=obj.options[obj.selectedIndex].innerText;
				 if(value=='<s:text name="quotation.declaredValue"/>'){
				 	document.quotation.tolerance.disabled=true;
				 	document.quotation.saleTermPercent.disabled=true;
				 	document.quotation.saleTermDecVal.value=document.quotation.saleTerm.value;
				 	document.getElementById(id).disabled=true;
				 	document.quotation.saleTermPercent.value='';
				 	document.quotation.tolerance.value='<s:text name="quotation.toleranceDecVal"/>';
				 	flag=false;
				 }else{
				 	if('RA'!='<s:property value="%{quoteStatus}"/>' && document.quotation.endTypeId.value==''){
					 	document.quotation.tolerance.disabled=false;
					 	document.getElementById(id).disabled=false;
				 	}
				 	document.quotation.saleTermDecVal.value='';
				 }
				 return flag;
			}
			
			function getList(val, id) {
			 	if(id=='percentList'){
			 		if(setBasisVal(document.quotation.saleTerm, id)){
			 			postRequest('${pageContext.request.contextPath}/'+id+'Quotation.action'+val, id);
			 		}
			 	}else{
			 		postRequest('${pageContext.request.contextPath}/'+id+'Quotation.action'+val, id);
			 	}
			}
			
			/*
			****** Commodity Selection Start *****
			*/
			
			/*function commoditySelection(){
			     var URL ='${pageContext.request.contextPath}/commodityListQuotation.action?applicationNo='+document.quotation.applicationNo.value+'&quoteStatus='+document.quotation.quoteStatus.value+'&refNo='+document.quotation.refNo.value+'&endTypeId='+document.quotation.endTypeId.value;
			     return popUp(URL,'1000','650');
			}*/
			
			function commoditySelectionAction(){
				try{
					postRequestNew('${pageContext.request.contextPath}/commodityListQuotation.action?applicationNo='+document.quotation.applicationNo.value+'&quoteStatus='+document.quotation.quoteStatus.value+'&refNo='+document.quotation.refNo.value+'&endTypeId='+document.quotation.endTypeId.value, 'commoditySelectionAjaxId');
				}catch(err){
					console.log(err);
				}
			}
			

			function fnsubmit(status,val){
				try{
				    if(status=='addmore'){
				    	postFormRequestNew('${pageContext.request.contextPath}/addCommodityQuotation.action?status1='+status, 'commoditySelectionAjaxId', 'commoditySelection');
					}else if(status=='edit'){
						if(!(document.getElementById('status2').value=='edit'))
							postFormRequestNew('${pageContext.request.contextPath}/addCommodityQuotation.action?status1='+status+'&commodityCode='+val, 'commoditySelectionAjaxId', 'commoditySelection');
					}else if(status=='delete'){
						postFormRequestCommodityDel('${pageContext.request.contextPath}/addCommodityQuotation.action?status1='+status+'&commodityCode='+val, 'commoditySelectionAjaxId', 'commoditySelection');
					}else{
						postFormRequestCommodity('${pageContext.request.contextPath}/addCommodityQuotation.action?status1='+status, 'commoditySelectionAjaxId', 'commoditySelection');
					}
				}catch(err){console.log(err)}
				}
			

			function callAjax(value){
				try{
			 		postRequestNew('${pageContext.request.contextPath}/getFrgileQuotation.action?category='+value, "ajaxFragile");
				}catch(err){console.log(err)}
			}
			
			function fnloadcomList(val){
				try{
					postRequestNew('${pageContext.request.contextPath}/getComListQuotation.action?commoditygroupId='+val, "loadcomList");
				}catch(err){console.log(err)}
			}
			
			/*
			****** Commodity Selection End *****
			*/
			
			function optionsSelection(option) {
			    var URL ='${pageContext.request.contextPath}/optionsListQuotation.action?option='+option;
				return popUp(URL,'1000','500');
			}
			function coreCustomerSearch() {
			     var URL ='${pageContext.request.contextPath}/coreCustomerSearchQuotation.action';
				 return popUp(URL,'700','500');
			}
			
			/*
			******* Customer Selection  Start *********
			*/
			
			/*function customerSelectionAction() {	
				var brokerCode='';
				var channelType = "";	
				if(document.quotation.brokerCode){
					brokerCode=document.quotation.brokerCode.value;		
				}
				if(document.quotation.channelType) {
					channelType=document.quotation.channelType.value;
				}
			     var URL ='${pageContext.request.contextPath}/customerSelectionQuotation.action?brokerCode='+brokerCode+"&channelType="+channelType;
			     return popUp(URL,'700','500');
			}*/
			function customerSelectionAction() {
				try{
					var brokerCode='';
					var channelType = "";
					if(document.quotation.brokerCode){
						brokerCode=document.quotation.brokerCode.value;
					}
					if(document.quotation.channelType) {
						channelType=document.quotation.channelType.value;
					}
					postRequestNew('${pageContext.request.contextPath}/customerSelectionQuotation.action?brokerCode='+brokerCode+'&channelType='+channelType, 'customerSelectionAjaxId');
				}catch(err){
					console.log(err);
				}
			}

			 function customerDetail(title,address1,address2,mobileNo,city,poBox,customerName,coreAppCode,customerId,custVatRegNo,custVatRegYn,email,custArNo){
				 try{
					document.customerSelection.cstitle.value=title;
					document.customerSelection.csaddress1.value=address1;
					document.customerSelection.csaddress2.value=address2;
					document.customerSelection.csmobileNo.value=mobileNo;
					document.customerSelection.cscity.value=city;
					document.customerSelection.cspoBox.value=poBox;
					document.customerSelection.cscustomerName.value=customerName;
					document.customerSelection.cscoreAppCode.value=coreAppCode;
					document.customerSelection.cscustomerId.value=customerId;
					document.customerSelection.cscustVatRegNo.value=custVatRegNo;
					document.customerSelection.cscustVatRegYn.value=custVatRegYn;
					document.customerSelection.csemail.value=email;
					document.customerSelection.cscustArNo.value=custArNo;
				 }catch(err){
					 console.log(err);
				 }
			 }
			 
			 function fnSubmit(){ 
				 try{
				 	if(!document.customerSelection.cscustomerId.value){
				 		alert("Please Select Customer");
				 	}else{
					if("0"==document.customerSelection.cscustomerId.value) {
						document.customerSelection.cscustomerId.value = "";
					}
					document.quotation.title.value=document.customerSelection.cstitle.value;
					document.quotation.address1.value=document.customerSelection.csaddress1.value;
					document.quotation.address2.value=document.customerSelection.csaddress2.value;
					document.quotation.mobileNo.value=document.customerSelection.csmobileNo.value;
					document.quotation.city.value=document.customerSelection.cscity.value;
					document.quotation.poBox.value=document.customerSelection.cspoBox.value;
					document.quotation.customerName.value=document.customerSelection.cscustomerName.value;
					document.quotation.coreAppCode.value=document.customerSelection.cscoreAppCode.value;
					document.quotation.customerId.value=document.customerSelection.cscustomerId.value;
					document.quotation.email.value=document.customerSelection.csemail.value;
					document.quotation.custVatRegNo.value=document.customerSelection.cscustVatRegNo.value;
					document.quotation.custVatRegYn.value=document.customerSelection.cscustVatRegYn.value;
					document.getElementById("quotation").elements["custVatRegNo"].readOnly=true;
					<s:if test='%{productId==openCover && !((quoteStatus=="RA")||(endTypeId!=null && endTypeId.length() > 0))}'>
						var sescustomerId = '<s:property value="#session.customer_id"/>';
						var customerId = document.customerSelection.customerId.value;
						if(customerId == sescustomerId) {
							document.getElementById("editCustomerNO").checked = true;
						}
						else {
							document.getElementById("editCustomerYES").checked = true;
						}
						editCustomerInfo();
					</s:if>
					$("#customerSelectionModal .close").click()
					}
				 }catch(err){
					 console.log(err);
				 }
			 }
			 
			 function searchList(){
				 try{
				 	ajaxLoading(); 
				 	var search=document.getElementById("customerSelection").elements["searchValue"].value;
					postRequestNew('${pageContext.request.contextPath}/customerSelectionQuotation.action?searchValue='+search+'&brokerCode='+'<s:property value="brokerCode"/>','customerSearchList');
				 }catch(err){
					 console.log(err);
				 }
			 }
			 function coreSearchList(){
				 try{
				 	ajaxLoading();
				 	var search=document.getElementById("customerSelection").elements["coreSearchValue"].value; 
					postRequestNew('${pageContext.request.contextPath}/customerSelectionQuotation.action?coreSearchValue='+search+'&brokerCode='+'<s:property value="brokerCode"/>'+'&channelType=<s:property value="channelType"/>','customerSearchList');
				 }catch(err){
					 console.log(err);
				 }
			 }
			 function ajaxLoading(){
				 try{
			 		document.getElementById("customerSearchList").innerHTML = '<img alt="Loading" src="${pageContext.request.contextPath}/images/ajax-loader.gif" class="ajaxLoader">';
				 }catch(err){
					 console.log(err);
				 }
			 }
			 
			 /*
				******* Customer Selection  End *********
				*/
			function lcSelectionAction() {
			     var URL ='${pageContext.request.contextPath}/lcSelectionQuotation.action';
			     return popUp(URL,'700','500');
			}
			if(document.getElementById("modeOfTransport").value=='<s:text name="LAND_TRANSIT"/>') {
				document.getElementsByName("originWarehouse")[0].checked=true;   
				document.getElementsByName("destWarehouse")[0].checked=true;  
				document.getElementsByName("originWarehouse")[1].disabled=true;   
				document.getElementsByName("destWarehouse")[1].disabled=true;
			}
			//exchageRate(document.getElementById("currency"),"currency");
			<s:if test='!("admin".equalsIgnoreCase(#session.usertype) || "RSAIssuer".equalsIgnoreCase(#session.usertype))'>
			 document.getElementById("exchageValue").disabled = true;
			</s:if>
			if(document.getElementById("lccurrency")){
				exchageRate(document.getElementById("lccurrency"),"lccurrency");
			}
			function exchageRate(obj,colnType){
				if(obj.value!=''){
				    var selected=document.getElementById(obj.value);
					with(selected){
						if(colnType=="currency"){
							document.getElementById("exchageValue").value=value;
							<s:if test='("admin".equalsIgnoreCase(#session.usertype) || "RSAIssuer".equalsIgnoreCase(#session.usertype))'>
								if("1"==obj.value)
							 	document.getElementById("exchageValue").disabled = true;
							 else
							 	document.getElementById("exchageValue").disabled = false;
							</s:if>	
							<s:else>
							 document.getElementById("exchageValue").disabled = true;
							</s:else>
							 							 						
						}else if(colnType=="exposureCurrency"){
							document.getElementById("exposureValue").value=value;
						}else{
							document.getElementById("lcexchageValue").value=value;
						}
					}
				}else{
					if(colnType=="currency"){
						document.getElementById("exchageValue").value='';
					}else if(colnType=="exposureCurrency"){
						document.getElementById("exposureValue").value=value;
					}else{
						document.getElementById("lcexchageValue").value='';
				 	}		
				}
			}
			function disableWarehouse(obj){
				var modeOfTrans=obj.value
				if(modeOfTrans=='<s:text name="LAND_TRANSIT"/>'){		 
					document.getElementsByName("originWarehouse")[0].checked=true;   
					document.getElementsByName("destWarehouse")[0].checked=true;  
					document.getElementsByName("originWarehouse")[1].disabled=true;   
					document.getElementsByName("destWarehouse")[1].disabled=true;	  
			    }	
			    else {                    
			   	 	document.getElementsByName("originWarehouse")[1].checked=true;   
					document.getElementsByName("destWarehouse")[1].checked=true;  
					document.getElementsByName("originWarehouse")[1].disabled=false;   
					document.getElementsByName("destWarehouse")[1].disabled=false;    
			    }   
			}
			toggleWarSrcc(document.getElementById("modeOfTransport"))
			function toggleWarSrcc(obj) {
				<s:if test='warOption=="Y"'>
				var modeOfTrans=obj.value;
				if(modeOfTrans=='3')
				{		 
				    document.getElementById("warSrccId").innerHTML='<s:text name="quotation.Srcc"/>'
			    }	
			    else
			    {                    
					document.getElementById("warSrccId").innerHTML='<s:text name="quotation.warSrcc"/>'    
			    }
			    </s:if>
			}
			toggleshipment();
			function  toggleshipment(){
				<s:if test='warOption=="Y"'>
				var modeOfTrans=document.getElementById("modeOfTransport").value;
				var warsrc=document.getElementsByName("warSrcc")[0].checked;
				if(modeOfTrans=='1' && warsrc){
					document.getElementById('shipmentId').style.display='inline';
				}else{
					document.getElementById('shipmentId').style.display='none';
					document.getElementById("shipmenttransit").value="";
				}
				 </s:if>
			}
			disableOthers(document.getElementById("settlingAgent"));
			
			function disableOthers(obj) {
				var value=obj.value;	
				if(value=='<s:text name="quotation.others.value"/>')
				{		
					document.getElementById("agentOthers").readOnly=false;
				}
				else
				{	
					document.getElementById("agentOthers").value="";
					document.getElementById("agentOthers").readOnly=true;		
				}		
			}
			
			function emptyCity(obj) {		
				if(obj=='<s:text name="quotation.dest"/>')	
				{
					document.getElementById("destCity").value="";	
					document.getElementById("destCityName").value="";	
				}
				else
				{
					document.getElementById("originCity").value="";
					document.getElementById("originCityName").value="";
				} 
			}
			
			/*
			****** Originating City Start *****
			**/
			
			/*function originCitySelection() {	
				var countryId=document.getElementById("originCountry").value;	
				var URL ='${pageContext.request.contextPath}/countryCityListQuotation.action?originCountry='+countryId+'&countrySelect=O';
				return popUp(URL,'750','500');	
			}*/
			
			function originCitySelectionAction() {
				try{
					var countryId=document.getElementById("originCountry").value;
					postRequestNew('${pageContext.request.contextPath}/countryCityListQuotation.action?originCountry='+countryId+'&countrySelect=O', 'originationCityAjaxId');
				}catch(err){
					console.log(err);
				}
			}

			 function toggleOriginField(value){
				try{
				 	if(value=='Others'){
				 		document.getElementById('otherOriginCity').readOnly=false;
				 	}else{
				 		document.getElementById('otherOriginCity').readOnly=true;
				 	}
				}catch(err){
					console.log(err);
				}
			 } 
			 
			 function fnOriginSubmit(){  
			 	try{
			 		var array=document.originCitySelection.originCityName;
			 		var cityObj, cityNameObj, obj;
		 			cityObj=document.quotation.originCity;
		 			cityNameObj=document.quotation.originCityName;
			 		if (array.length > 0) {
			 			for ( var int = 0; int < array.length; int++) {	  	
			 				obj=array[int];
			 				if(obj.checked){
			 					cityObj.value=obj.value;
			 					var regex = /^[a-zA-Z ]{2,30}$/;
			 					if(obj.title=='Others'){
			 						if(document.getElementById("otherOriginCity").value==''){
			 							alert('Please Enter City');
			 							return false;
			 						}	
			 						cityNameObj.value=document.getElementById("otherOriginCity").value;	
			 					}else{
			 						cityNameObj.value=obj.title;
			 					}
			 				}
			 			}
			 		} else {
			 			if(document.getElementById("otherOriginCity").value==''){
			 				alert('Please Enter City');
			 				return false;
			 			} else {	
			 				cityNameObj.value=document.getElementById("otherOriginCity").value;
			 				cityObj.value="9999";
			 			}
			 		}
			 		$("#originationCityModal .close").click()
			 	}catch(e){console.log(e);} 
			 	
			 }
			
			/*
			****** Originating City End *****
			**/
			
			/*
			****** Destination City Start *****
			**/
			
			/*function destCitySelection() {	
				var countryId=document.getElementById("destCountry").value;
				var URL ='${pageContext.request.contextPath}/countryCityListQuotation.action?destCountry='+countryId+'&countrySelect=D';
				return popUp(URL,'750','500');	
			}*/
			
			function destCitySelectionAction() {
				try{
					var countryId=document.getElementById("destCountry").value;
					postRequestNew('${pageContext.request.contextPath}/countryCityListQuotation.action?destCountry='+countryId+'&countrySelect=D', 'destinationCityAjaxId');
				}catch(err){
					console.log(err);
				}
			}

			 function toggleDestField(value){
				 try{
				 	if(value=='Others'){
				 		document.getElementById('otherDestCity').readOnly=false;
				 	}else{
				 		document.getElementById('otherDestCity').readOnly=true;
				 	}
				 }catch(err){
					 console.log(err);
				 }
			 } 

			 
			 function fnDestSubmit(){  
			 	try{
			 		var array=document.destCitySelection.destCityName;
			 		var cityObj, cityNameObj, obj;
		 			cityObj=document.quotation.destCity;
		 			cityNameObj=document.quotation.destCityName;
			 		if (array.length > 0) {
			 			for ( var int = 0; int < array.length; int++) {	  	
			 				obj=array[int];
			 				if(obj.checked){
			 					cityObj.value=obj.value;
			 					var regex = /^[a-zA-Z ]{2,30}$/;
			 					if(obj.title=='Others'){
			 						if(document.getElementById("otherDestCity").value==''){
			 							alert('Please Enter City');
			 							return false;
			 						}	
			 						cityNameObj.value=document.getElementById("otherDestCity").value;	
			 					}else{
			 						cityNameObj.value=obj.title;
			 					}
			 				}
			 			}
			 		} else {
			 			if(document.getElementById("otherDestCity").value==''){
			 				alert('Please Enter City');
			 				return false;
			 			} else {
			 				cityNameObj.value=document.getElementById("otherDestCity").value;
			 				cityObj.value="9999";
			 			}
			 		}
			 		$("#destinationCityModal .close").click()
			 	}catch(e){console.log(e);} 
			 	
			 }
			
			/*
			****** Destination City End *****
			**/
			
			/*
			**** Vessel Start *****
			*/
			
			/*function callvessel() {
				var URL ='${pageContext.request.contextPath}/vesselQuotation.action';
				return popUp(URL,'750','500');	
			}	*/
			
			function callvessel() {
				try{
					postRequestNew('${pageContext.request.contextPath}/vesselQuotation.action', 'vesselSelectionAjaxId');
				}catch(err){
					console.log(err);
				}
			}

		  function getVesselList(id, name){
			  try{
				  	if(name.length>=2){
					  	var url='<%=request.getContextPath()%>/vesselListQuotation.action?vesselName='+name;
						postRequestNew(url, id);
					}
			  }catch(err){
				  console.log(err);
			  }
		  }
			  
		  function selectVessel(id, name){
			  try{
				  	document.vesselSelection.vesselId.value=id;
				  	document.vesselSelection.vesselName.value=name;
			  }catch(err){
				  console.log(err);
			  }
		  }
		  

		  function fnVesselSubmit(){
			  try{
			  	if(document.vesselSelection.select[0]){
			  		var noneObj=document.vesselSelection.select[0];
			  	}else{
			  		var noneObj=document.vesselSelection.select;
			  	}
			  	if(document.vesselSelection.select[1]){
			  		var othersObj=document.vesselSelection.select[1];
			  	}else{
			  		var othersObj=document.vesselSelection.select;
			  	}
			  	if(!noneObj.checked && !othersObj.checked){
			  		var array=document.vesselSelection.select;
				  	for ( var int = 0; int < array.length; int++) {
						if(array[int].checked){
							document.vesselSelection.vesselId.value=array[int].value;
				  			document.vesselSelection.vesselName.value=array[int].title;
						}
					}
			    }
			  	if(noneObj.checked){
			  		document.quotation.vesselName.value='';
			  		document.quotation.vesselId.value='';
			  		$("#vesselSelectionModal .close").click()
			  	}else if(othersObj.checked){
			 		if(document.vesselSelection.vesselOthers.value.trim()==''){ 	
			  			alert('Please Enter Vessel Name');
			  		}else{
			  			document.quotation.vesselName.value=document.vesselSelection.vesselOthers.value;
				  		document.quotation.vesselId.value='';
				  		$('#vesselSelectionModal').modal('hide');
			  		}
			  	}else if(document.vesselSelection.vesselId.value ==''){
			  		alert('Please Select Vessel');
			  	}else{
			  		document.quotation.vesselName.value=document.vesselSelection.vesselName.value;
			  		document.quotation.vesselId.value=document.vesselSelection.vesselId.value;
			  		$('#vesselSelectionModal').modal('hide');
			  	}
			  }catch(err){
				  console.log(err);
			  }
		  }
			
			/*
			**** Vessel End *****
			*/
			
			function emptyCustInfo(){
				var s="poBox&mobileNo&email&title&customerName&coreAppCode&address2&address1&city"; 
				var stringToArray = new Array;
				stringToArray = s.split("&");
				for ( var int = 0; int < stringToArray.length; int++) {		
						var obj=stringToArray[int]
						document.getElementById(""+obj).value="";
				}
			}
			function custInfoDisable(channelType) {
				var issuer = '${issuer}';
				var s="poBox&mobileNo&email&title&coreAppCode&address2&address1&city";
				var stringToArray = new Array;
				stringToArray = s.split("&"); 
				if(issuer!=null && issuer!="" && channelType=="customer") {
					for ( var int = 0; int < stringToArray.length; int++) {
						var obj=stringToArray[int]
						document.getElementById(""+obj).disabled=true;
					}
				}
				else {
					for ( var int = 0; int < stringToArray.length; int++) {
						var obj=stringToArray[int]
						document.getElementById(""+obj).disabled=false;
					}
				}
			}
			function setVesselName() {
				document.getElementById('vesselName').value = "";
				document.getElementById('vesselId').value = "";
			}
			function callAction(status, userType) {
				if(userType=='admin' || userType=='Admin'){
					if(status=='RU'){
						document.quotation.action='${pageContext.request.contextPath}/getOCAjaxReferal.action?index=0';
					}else if(status=='RA'){
						document.quotation.action='${pageContext.request.contextPath}/getOCAjaxReferal.action?index=1';
					}else if(status=='RR'){
						document.quotation.action='${pageContext.request.contextPath}/getOCAjaxReferal.action?index=2';
					}
				}else{
					document.quotation.action='${pageContext.request.contextPath}/initReport.action?menuType=QE';
				}
				document.quotation.submit();
			}
			function forward(url) {
				document.quotation.action = "${pageContext.request.contextPath}/"+url;
				document.quotation.submit();
			}
			
			<s:if test='lcBankList!=null && lcBankList.size==1'>
	           	getList('?lcBank=<s:property value="lcBankList[0].CODE"/>','lcList');
	           </s:if>
	           
			function quotationDetail(val) {
			  	if(val){
				  	document.getElementById('quoteInfo').style.display='';
				    document.getElementById('miuns').style.display='';
				    document.getElementById('plus').style.display='none';
			    }else{
			    	document.getElementById('quoteInfo').style.display='none';
				    document.getElementById('miuns').style.display='none';
				    document.getElementById('plus').style.display='';
			    }
			}
			
			function textLimit(field, maxlen){
				if (field.value.length > maxlen + 1)
					alert('Maximum Length is '+maxlen);
				if (field.value.length > maxlen)
					field.value = field.value.substring(0, maxlen);
					checkSpecialChars(field);
			}
			function clearCustomerInfo() {
				var disableStatus = '<s:property value="#disable"/>';
				if(disableStatus!="disabled" && disableStatus!="true") {
					document.getElementById("title").value="";
					document.getElementById("address1").value="";
					document.getElementById("address2").value="";
					document.getElementById("mobileNo").value="";
					document.getElementById("city").value="";
					document.getElementById("poBox").value="";
					document.getElementById("customerName").value="";
					document.getElementById("coreAppCode").value="";
					document.getElementById("customerId").value="";
					document.getElementById("email").value="";
					document.getElementById("custVatRegNo").value="";
					document.getElementById("custVatRegNo").readOnly=false;
					document.getElementById("custVatRegYn").value="";
					<s:if test='%{productId==openCover && !((quoteStatus=="RA")||(endTypeId!=null && endTypeId.length() > 0))}'>
						document.getElementById("editCustomerYES").checked = true;
						editCustomerInfo();
					</s:if>
				}
			}
			<s:if test='%{productId==openCover && customerId.equals(#session.customer_id) && !(#disable)}'>
				editCustomerInfo();
			</s:if>
			function editCustomerInfo() {
				var chk = document.getElementById("editCustomerNO").checked;
				if( chk == true) {
					document.getElementById("title").disabled=true;
					document.getElementById("address1").disabled=true;
					document.getElementById("address2").disabled=true;
					document.getElementById("mobileNo").disabled=true;
					document.getElementById("city").disabled=true;
					document.getElementById("poBox").disabled=true;
					document.getElementById("customerName").disabled=true;
					document.getElementById("email").disabled=true;
					
					document.getElementById("editCustomerDIV").style.display = "";
				}
				else {
					document.getElementById("title").disabled=false;
					document.getElementById("address1").disabled=false;
					document.getElementById("address2").disabled=false;
					document.getElementById("mobileNo").disabled=false;
					document.getElementById("city").disabled=false;
					document.getElementById("poBox").disabled=false;
					document.getElementById("customerName").disabled=false;
					document.getElementById("email").disabled=false;
					
					document.getElementById("customerId").value="";
					document.getElementById("editCustomerDIV").style.display = "none";
				}
			}
			
			function postRequestNew(strUrl, id){
				$.ajax({
					   url: strUrl,		   
					   error: function() {
					      $('#'+id).html('<p>An error has occurred in jquery Ajax</p>');
					   },		   
					   success: function(data) {
					      $('#'+id).html(data);
					   },
					   beforeSend : function(){
						   $('#loading').show();
						   $('.ajaxLoader').show();
			           },
			           complete : function(){
			        	   $('#loading').hide();
			        	   $('.ajaxLoader').hide();
			           },
					   type: 'POST'
				});	
			}
			
			function postFormRequestCommodity(strUrl, id, formId) {
				$.ajax({
					url : strUrl,
					type : "POST",
					data : $("#" + formId).serialize(),
					error: function() {
						$('#'+id).html('<p>Your Session has been Expired</p>');
					},		   
					success: function(data) {
						$('#'+id).html(data);
						/* $("#commoditySelectionModal .close").click() */
					},
					beforeSend : function(){
						$('#loading').show();
						$('.ajaxLoader').show();
					},
					complete : function(){
						$('#loading').hide();
						$('.ajaxLoader').hide();
						try{
							document.quotation.applicationNo.value=document.commoditySelection.applicationNo.value;
							document.quotation.totalCommodity.value=document.commoditySelection.commSelTotalCommodity.value;
							document.quotation.totalSI.value=document.commoditySelection.commSelTotalSI.value;
							document.quotation.commodity.value=document.commoditySelection.commSelcommodity.value;
							
							
						}catch(err){
							console.log(err);
						}
						
					}
				});
			}
			
			function postFormRequestCommodityDel(strUrl, id, formId) {
				$.ajax({
					url : strUrl,
					type : "POST",
					data : $("#" + formId).serialize(),
					error: function() {
						$('#'+id).html('<p>Your Session has been Expired</p>');
					},		   
					success: function(data) {
						$('#'+id).html(data);
					},
					beforeSend : function(){
						$('#loading').show();
						$('.ajaxLoader').show();
					},
					complete : function(){
						$('#loading').hide();
						$('.ajaxLoader').hide();
						try{
							document.quotation.totalCommodity.value='';
							document.quotation.totalSI.value='';
							document.quotation.commodity.value='';	
						}catch(err){
							console.log(err);
						}
					}
				});
			}
			
			function postFormRequestNew(strUrl, id, formId) {
				$.ajax({
					url : strUrl,
					type : "POST",
					data : $("#" + formId).serialize(),
					error: function() {
						$('#'+id).html('<p>Your Session has been Expired</p>');
					},		   
					success: function(data) {
						$('#'+id).html(data);
					},
					beforeSend : function(){
						$('#loading').show();
						$('.ajaxLoader').show();
					},
					complete : function(){
						$('#loading').hide();
						$('.ajaxLoader').hide();
					}
				});
			}
		</script>
	</body>
</html>