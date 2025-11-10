<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>       
        <meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page"> 
    </head>
<body><br> 
<s:form name="report" theme="simple" action="initReport.action?menuType=P">
	<table width="90%" border="0" cellspacing="0" cellpadding="2" align="center" bgcolor="#FFFFFF">	   
  		<tr>
  			<td>&nbsp;</td>
  		</tr>
  		<tr>
          <td  style="color:red;"><s:actionerror/></td>
        </tr>
  		<tr>
  			<td class="heading" width="100%">
  			   <s:text name="declaration.%{menuType}" />		      		  
     		</td>
  		</tr>
  		<s:if test='menuType=="D"'>
	  		<s:if test='policyList.size > 0 && "details".equals(listType)'>
		  		<tr>
		  			<td width="100%">
		  				<table width="60%" align="center">
		  					<tr>
		  						<td width="20%" ></td>
		  						<td width="20%" align="left"><b>Transaction Id</b></td>
		  						<td width="40%" align="left">&nbsp;:&nbsp;${tranId}</td>
		  					</tr>
		  					<tr>
		  						<td width="20%" ></td>
		  						<td width="20%" align="left"><b>Consignee Name</b></td>
		  						<td width="40%" align="left">&nbsp;:&nbsp;${supplier}</td>
		  					</tr>
		  					<tr>
		  						<td width="20%" ></td>
		  						<td width="20%" align="left"><b>Vessel Name</b></td>
		  						<td width="40%" align="left">&nbsp;:&nbsp;${vessel}</td>
		  					</tr>
		  				</table>
		  			</td>
		  		</tr>
		  		<tr>
		  			<td width="100%">
		  				<div style="max-height:400px;overflow:scroll;overflow-x:hidden;">
			  			   <table width="100%" border="0" cellspacing="4" cellpadding="2" align="center" bgcolor="#FFFFFF">
							  		<tr class="bg">
										<td><s:label key="declaration.select" /></td>
										<td><s:label key="declaration.quoteNo" /></td>
										<td><s:label key="declaration.customerName" /></td>
										<td><s:label key="declaration.sumInsured" /></td>
										<td><s:label key="declaration.premium" /></td>
										<td><s:label key="declaration.status" /></td>
										<td><s:label key="declaration.draft" /></td>
									</tr>
									<s:iterator value="policyList" status="status" var="policyList">
										<tr class="bg">								
											<td align="center" width="5%">
												<s:if test='#policyList.PACKAGE_DESCRIPTION =="0"'>
												<s:property value="%{#policyList.SELECTED}"/><s:checkbox  name="selectedQuote" value="%{#policyList.SELECTED}" fieldValue="%{#policyList.QUOTE_NO}"/><s:hidden  name="quoteNo" value="%{#policyList.QUOTE_NO}"/>
												</s:if>
											</td>
											<td width="15%"><s:property value="%{#policyList.QUOTE_NO}"/></td>
											<td width="30%"><s:property value="%{#policyList.FIRST_NAME}"/></td>
											<td width="10%" align="right"><s:property value="%{#policyList.TOTAL_SUM_INSURED}"/></td>
											<td width="10%" align="right"><s:property value="%{#policyList.PREMIUM}"/></td>
											<s:if test='#policyList.PACKAGE_DESCRIPTION =="0"'>
												<td width="10%"><s:label key="declartion.completed"></s:label></td>
												<td width="10%"><a href="#" onclick="return popUp('${pageContext.request.contextPath}/Copy of information Admin.jsp?policyMode=draft&policynumber=<s:property value="%{#policyList.QUOTE_NO}"/>&loginid=<s:property value="%{#session.userName}"/>&productTypeIds=<s:property value="%{#session.product_id}"/>','1000','500')"><s:label key="declartion.draft"/></a></td>
											</s:if>
											<s:else>
												<td width="10%"><s:label key="declartion.missing"></s:label></td>
												<td width="10%"><s:label key="declartion.na"></s:label></td>
											</s:else>
										</tr>
								</s:iterator>
							</table>
						</div>
		     		</td>
		  		</tr>
		  		<tr align="center">
					<td class="text" >
						<s:submit type="button" name="close"  key="Back" cssClass="btn" onclick="forward('KD')"/>&nbsp;
						<s:submit type="submit" name="submit" key="Submit" onclick="forward('MD')" cssClass="btn"/>
					</td>
				</tr>
			</s:if>
  			<s:elseif test='policyList.size > 0 && "result".equals(reqFrom)'>
  				<tr>
		  			<td width="100%">
		  				<div style="max-height:600px;overflow:scroll;overflow-x:hidden;">
			  			   <table width="100%" border="0" cellspacing="4" cellpadding="2" align="center" bgcolor="#FFFFFF">
			  			   		<s:set var="tid" value=""/>
								<s:set var="sname" value=""/>
								<s:if test='%{"admin".equals(#session.user1)}'>
					          		<s:set var="format" value="%{'number.format.2'}"/>
					          	</s:if>
					          	<s:else>
					          		<s:set var="format" value="%{'number.format.'+#session.BrokerDetails.CurrencyDecimal}"/>
					          	</s:else>
					          	<tr>
					          		<td colspan="5" align="center"><b>Transaction Id</b>&nbsp;:&nbsp;${tranId}</td>
					          	</tr>
			  			   		<s:iterator value="policyList" status="status" var="mainpolicyList">
								  	<s:if test='%{!(#attr.tid==#mainpolicyList.TRANSACTION_ID && #attr.sname==#mainpolicyList.supplier_name)}'>
									  	<tr class="heading">
								  			<td colspan="5">Consignee Name: &nbsp;${supplier_name}</td>
								  		</tr>
									  	<s:set var="tid" value="%{#mainpolicyList.TRANSACTION_ID}"/>
									  	<s:set var="sname" value="%{#mainpolicyList.supplier_name}"/>
				  			   			<s:iterator value="policyList" status="stat" var="subpolicyList">
				  			   				<s:if test='%{#subpolicyList.TRANSACTION_ID==#mainpolicyList.TRANSACTION_ID && #subpolicyList.supplier_name==#mainpolicyList.supplier_name}'>
				  			   					<tr class="bg">
				  			   						<td width="10%"></td>
				  			   						<td width="40%">${VESSEL_NAME}</td>
				  			   						<td width="20%" align="center"><a href="#" onclick="getVesselDeclare('${VESSEL_NAME}','${TRANSACTION_ID}','${supplier_name}');">${vessel_count}</a></td>
				  			   						<td width="20%" align="right"><s:property value="getText(#format,{@java.lang.Double@parseDouble(PREMIUM)})" default="" /></td>
				  			   						<td width="10%"></td>
				  			   					</tr>
				  			   				</s:if>
				  			   			</s:iterator>
				  			   			<tr><td colspan="2"></td></tr>
			  			   			</s:if>
								</s:iterator>
							</table>
						</div>
		     		</td>
		  		</tr>
		  		<tr align="center">
					<td class="text" >
						<s:submit type="button" name="close"  key="Back" cssClass="btn" onclick="forward('D')"/>&nbsp;
					</td>
				</tr>
  			</s:elseif>
  			<s:elseif test='policyList.size > 0'>
  				<tr>
		  			<td width="100%">
		  				<display:table name="policyList" pagesize="15" requestURI="initDeclaration.action" class="table" uid="row" id="record" >
							<display:setProperty name="paging.banner.one_item_found" value="" />
							<display:setProperty name="paging.banner.one_items_found" value="" />
							<display:setProperty name="paging.banner.all_items_found" value="" />
							<display:setProperty name="paging.banner.some_items_found" value="" />
							<display:setProperty name="paging.banner.placement" value="bottom" />
							<display:setProperty name="paging.banner.onepage" value="" />
							
							<display:column sortable="true" style="text-align:center;" title="Transaction Id" property="TRANSACTION_ID"  />
							<display:column sortable="true" style="text-align:center;" title="Uploaded Date" property="TRN_DATE"  />
							<display:column sortable="true" style="text-align:center;" title="Total Quotes" property="TOTAL_NO_OF_RECORDS"  />
							<display:column sortable="true" style="text-align:center;" title="Uploaded Quotes"  >
								<a href="#" onclick="getConsigneeDetail(${record.TRANSACTION_ID})"> <s:property value="#attr.record.UPLOADED_COUNT" /></a> 
							</display:column>
							<display:column sortable="true" style="text-align:center;" title="Pending Quotes" property="PENDING_COUNT"/>
						</display:table>
		     		</td>
		  		</tr>
  			</s:elseif>
	  		<s:else>
	  			<tr class="bg" ><td colspan="7"><s:label key="declaration.nothingfound"></s:label></tr>
	  		</s:else>
  		</s:if>
		<s:if test='menuType=="DP"'>
  			<s:if test="policyList.size > 0">
	  		<tr>
	  			<td width="100%">
	  				<div style="max-height:400px;overflow:scroll;overflow-x:hidden;">
		  			   <table width="100%" border="0" cellspacing="4" cellpadding="2" align="center" bgcolor="#FFFFFF">
						  		<tr class="bg">
									<td><s:label key="declaration.policyNo" /></td>
									<td><s:label key="declaration.quoteNo" /></td>
									<td><s:label key="declaration.customerName" /></td>
									<td><s:label key="declaration.sumInsured" /></td>
									<td><s:label key="declaration.premium" /></td>
									<td><s:label key="declaration.schedule" /></td>
								</tr>
								<s:iterator value="policyList" status="status" var="policyList">
									<tr class="bg">								
										<td width="16%"><s:property value="%{#policyList.POLICY_NO}"/>/<s:property value="%{#status.count}"/></td>
										<td width="7%"><s:property value="%{#policyList.QUOTE_NO}"/></td>
										<td width="30%"><s:property value="%{#policyList.FIRST_NAME}"/></td>
										<td width="10%" align="right"><s:property value="getText('number.format.2',{@java.lang.Double@parseDouble(TOTAL_SUM_INSURED)})"/></td>
										<td width="10%" align="right"><s:property value="getText('number.format.2',{@java.lang.Double@parseDouble(PREMIUM)})"/></td>
										<td width="7%"><a href="#" onclick="return popUp('${pageContext.request.contextPath}/Copy of information.jsp?policyMode=suppleMentMultiple&policynumber=<s:property value="%{#policyList.QUOTE_NO}"/>&loginid=<s:property value="%{#session.userName}"/>&verNo=<s:property value="%{#status.count}"/>','1000','500')"><s:label key="declaration.schedule"/></a></td>
									</tr>
							</s:iterator>
						</table>
					</div>
	     		</td>
	  		</tr>
	  		<tr align="center">
				<td class="text" >
				</td>
			</tr>
			<tr align="center">
				<td><input type="button" name="back123" value="Back" class="btn" onclick="getBack('<s:property value="%{#session.userName}"/>');"/></td>
			</tr>
			</s:if>
	  		<s:else>
	  			<tr class="bg" ><td colspan="7"><s:label key="declaration.nothingfound"></s:label></tr>
	  		</s:else>
  		</s:if>
  		<s:elseif test='menuType=="MD"'>
	  		<tr>
	    	     <td class="bg" width="100%">    	     	   	     	     
					<display:table name="declarationList" pagesize="10"  requestURI="${pageContext.request.contextPath}/initDeclaration.action"
					class="table" uid="row" id="record" style="width:100%; align:center; " cellpadding="1" cellspacing="1" >
					<display:setProperty name="paging.banner.one_item_found" value="" />
					<display:setProperty name="paging.banner.one_items_found" value="" />
					<display:setProperty name="paging.banner.all_items_found" value="" />
					<display:setProperty name="paging.banner.some_items_found" value="" />
					<display:setProperty name="paging.banner.placement"	value="bottom" />
					<display:setProperty name="paging.banner.onepage" value="" />
					<display:column sortable="true" style="text-align:center;width:9%" title="Quote No">
						<input type="hidden" name="selectedQuote" value="${record.QUOTE_NO}"/>${record.QUOTE_NO}
					</display:column>
					<display:column sortable="true" style="text-align:center;width:9%" title="Customer Name" property="FIRST_NAME"/>
					<display:column sortable="true" style="text-align:right;width:9%" title="Sum Insured" property="TOTAL_SUM_INSURED"/>
					<display:column sortable="true" style="text-align:right;width:9%" title="Premium" property="PREMIUM"/>
					</display:table>				
	    	     </td>
	    	</tr>
	    	<tr>
				<td>     				
	  				<div id="policyGeneration" style='%{referralUpdate=="Y"?"display:none":"display:block"}'>
		   				<table width="100%">
		   					<tr>
		   						<td>&nbsp;</td>
		   					</tr>
		   					<tr> 
		   						<td class="heading" width="100%"><s:label key="declaration.policyGeneration" /></td>
		   					</tr>
		   					<tr>
		   						<td class="bg">
		   			    			<table width="100%" border="1" bordercolor="#F2F2F2" align="center" cellpadding="4" cellspacing="0">
		   								<tr>
					     				 <td width="40%"><b><s:label key="declaration.generatePolicy" /></b></td>
					     				  <td width="60%"><s:radio list="#{'Y':'Yes','N':'No'}"  name="generatePolicy" id="generatePolicy" value="%{generatePolicy==null?'N':generatePolicy}" onclick="disablePolicyDetail(this);"/></td>     								  
		   								</tr> 
		   							 </table>
		   						</td>
		   					</tr>
		   				</table>
					</div>	
				</td>
			</tr>			
			<tr>
				<td>
	  				<div id="getPolicyDetail" style="display: none;">  
		    			<table width="100%">				     				
		    				<tr><td>&nbsp;</td></tr>
		    				<tr> 
		    					<td class="heading" width="100%"><s:label key="declaration.certificateDisplayInfo" /></td>
		    				</tr>	
		    				<tr>
		                      <td class="bg">                       
		                      		<table width="100%" border="1" bordercolor="#F2F2F2" align="center" cellpadding="4" cellspacing="0">
			                       <tr>                       
				                       <td width="20%"><s:checkbox name="rubberStamp" id="check_rubber_stamp" fieldValue="Y"   /><s:label key="declaration.printRubberStamp"  name="certificateInfo"/></td>
			                      </tr>
		                      		</table>
		                      	</td>
		                   </tr>       				
		                   <tr><td>&nbsp;</td></tr>
		    				<tr> 
		    					<td class="heading" width="100%"><s:label key="declaration.documentBasis" /></td>
		    				</tr>
		    				<tr>
		    					<td class="bg">
		    			    		<table width="100%" border="1" bordercolor="#F2F2F2" align="center" cellpadding="4" cellspacing="0">
		    							<tr>
						     				 <td width="40%"><b><s:label key="declaration.noteType" /></b></td>
						     				 <td width="60%"><s:radio name="noteType" list="#{'N':'Net','G':'Gross'}" value="%{noteType==null?'N':noteType}" onclick="toggleCredit(this.value)"/></td>     								  
		    							</tr>
					     				<tr>
						     				 <td width="40%"><b><s:label key="declaration.paymentMode" /></b></td>
						     				 <td width="60%"><s:radio list="#{'CA':'Cash','CR':'Credit'}" name="paymentMode"  value="%{paymentMode==null?'CA':paymentMode}" /></td>     
					     				</tr>
	    							</table> 
								</td>
							</tr>
						</table>
	  				</div>  
          		</td>
          	</tr>
	    	<tr align="center">
				<td class="text" >
					<input type="button" name="close"  value="Back" class="btn" onclick="getVesselDeclare('${vessel}','${tranId}','${supplier}');"/>&nbsp;
					<s:submit type="submit" name="submit" key="Submit" onclick="update('MC')" cssClass="btn"/>
				</td>
			</tr> 
  		</s:elseif>
  		<s:elseif test='menuType=="MC"'>
  			<s:if test='!hasActionErrors()'>
				<tr>
					<td class="bg">
						<table width="100%" border="0" cellspacing="0" cellpadding="2" align="center">
						 	<tr>
						 		<td align="center"><font size="5px" style="font-weight: bold;"><s:label key="declaration.certNo"/>&nbsp;<font color="blue"><s:property value="declarationList[0].POLICY_NO.toString()"/></font></font></td>
						 	</tr>					 	
						 </table>			 				
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;
					</td>
				</tr>
				<tr>
					<td class="heading" width="100%">	
		     			<s:label key="declaration.title" />			
					</td>
				</tr>
		  		<tr>
		    	     <td class="bg" width="100%">    	     	   	     	     
						<display:table name="declarationList" pagesize="10"  requestURI="${pageContext.request.contextPath}/initDeclaration.action"
						class="table" uid="row" id="record" style="width:100%; align:center; " cellpadding="1" cellspacing="1" >
						<display:setProperty name="paging.banner.one_item_found" value="" />
						<display:setProperty name="paging.banner.one_items_found" value="" />
						<display:setProperty name="paging.banner.all_items_found" value="" />
						<display:setProperty name="paging.banner.some_items_found" value="" />
						<display:setProperty name="paging.banner.placement"	value="bottom" />
						<display:setProperty name="paging.banner.onepage" value="" />
						<display:column sortable="true" style="text-align:center;width:9%" title="Quote No" property="QUOTE_NO"/>
						<display:column sortable="true" style="text-align:center;width:9%" title="Customer Name" property="FIRST_NAME"/>
						<display:column sortable="true" style="text-align:right;width:9%" title="Sum Insured" property="TOTAL_SUM_INSURED"/>
						<display:column sortable="true" style="text-align:right;width:9%" title="Premium" property="PREMIUM"/>
						</display:table>				
		    	     </td>
		    	</tr>
		    	<tr>
					<td class="heading" width="100%">	
		     			<s:label key="policyInfo.documentInfo" />			
					</td>
				</tr>
		        <tr>
					<td>
						<table width="100%" border="1" bordercolor="#A4A4A4"  align="center" cellpadding="4" cellspacing="0">
				   			 <tr>		     				    
			                  	<td><s:label key="policyInfo.schedule" /></td>                        			 
			                  	<td align="center"><s:property value="declarationList[0].POLICY_NO"/></td>                        			 
			                  	<td align="center"><s:submit name="Submit2" type="button" cssClass="btn" value="Schedule" cssStyle="width:100px" onclick="return popUp('Copy of information.jsp?policyMode=scheduleMultiple&policynumber=%{declarationList[0].POLICY_NO}&loginid=%{#session.user}','1000','500')"/></td>                        			 
		               		 </tr>
		               		 <s:if test='declarationList[0].DEBIT_NOTE_NO != "0"'>
			               		 <tr>		     				    
				                  	<td><s:label key="policyInfo.debit" /></td>                        			 
				                  	<td align="center"><s:property value="declarationList[0].DEBIT_NOTE_NO"/></td>                        			 
				                  	<td align="center"><s:submit name="Submit2" type="submit" cssClass="btn" key="policyInfo.debit" cssStyle="width:100px" onclick="return popUp('Copy of information.jsp?policyMode=debitMultiple&policynumber=%{declarationList[0].POLICY_NO}&loginid=%{#session.user}','1000','500')"/></td>                        			 
			               		 </tr>
	                      	</s:if>
		               		 <s:if test='declarationList[0].CREDIT_NOTE_NO != "0"'>
			               		 <tr>		     				    
				                  	<td><s:label key="policyInfo.credit" /></td>                        			 
				                  	<td align="center"><s:property value="declarationList[0].CREDIT_NOTE_NO"/></td>                        			 
				                  	<td align="center"><s:submit name="Submit2" type="submit" cssClass="btn" key="policyInfo.credit" cssStyle="width:100px" onclick="return popUp('Copy of information.jsp?policyMode=creditMultiple&policynumber=%{POLICY_NO}&loginid=%{#session.user}','1000','500')"/></td>                        			 
			               		 </tr>
	                      	</s:if>
		     			</table>
					</td>
				</tr>
			</s:if>
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
	                      		<s:submit name="Submit" type="submit" cssClass="btn" value="Proceed"  onclick=""/>                    	
	                     	</td>
	                    	<td>
	                    	&nbsp;
	                    	</td>
	                    </tr>
	                 </table>
	             </td>
	         </tr>
    	</s:elseif>
    	<s:elseif test='menuType=="DE"'>
    		<s:if test='policyList.size > 0'>
		  		 <tr>
		  			<td width="100%">
		  				<div style="max-height:400px;overflow:scroll;overflow-x:hidden;">
			  			   <table width="100%" border="0" cellspacing="4" cellpadding="2" align="center" bgcolor="#FFFFFF">
							  		<tr class="bg">
										<td><s:label key="declaration.select" /></td>
										<td><s:label key="declaration.quoteNo" /></td>
										<td><s:label key="declaration.customerName" /></td>
										<td><s:label key="declaration.sumInsured" /></td>
										<td><s:label key="declaration.premium" /></td>
										<td><s:label key="declaration.status" /></td>
										<td><s:label key="declaration.draft" /></td>
									</tr>
									<s:iterator value="policyList" status="status" var="policyList">
										<tr class="bg"> 
											<td align="center" width="5%"> 
												<s:property value="%{#policyList.SELECTED}"/><s:checkbox  name="selectedQuote" value="%{#policyList.SELECTED}" fieldValue="%{#policyList.QUOTE_NO}"/><s:hidden  name="quoteNo" value="%{#policyList.QUOTE_NO}"/>
											</td>
											<td width="15%"><s:property value="%{#policyList.QUOTE_NO}"/></td>
											<td width="30%"><s:property value="%{#policyList.FIRST_NAME}"/></td>
											<td width="10%" align="right"><s:property value="%{#policyList.TOTAL_SUM_INSURED}"/></td>
											<td width="10%" align="right"><s:property value="%{#policyList.PREMIUM}"/></td>
											<s:if test='"0".equals(#policyList.PACKAGE_DESCRIPTION.toString())'>
												<td width="10%">Application</td>											
											</s:if>
											<s:else>
												<td width="10%">Excel Upload</td>
											</s:else>
											<td width="10%"><a href="#" onclick="return popUp('${pageContext.request.contextPath}/Copy of information Admin.jsp?policyMode=draft&policynumber=<s:property value="%{#policyList.QUOTE_NO}"/>&loginid=<s:property value="%{#session.userName}"/>&productTypeIds=<s:property value="%{#session.product_id}"/>','1000','500')"><s:label key="declartion.draft"/></a></td>
											<!--
											<s:else>
												<td width="10%"><s:label key="declartion.missing"></s:label></td>
												<td width="10%"><s:label key="declartion.na"></s:label></td>
											</s:else> 
										--></tr>
								</s:iterator>
							</table>
						</div>
		     		</td>
		  		</tr>
		  		<tr align="center">
					<td class="text">
						<!--<s:submit type="button" name="close"  key="Back" cssClass="btn" onclick="forward('KD')"/>&nbsp;
						--><s:submit type="submit" name="submit" key="Submit" onclick="forward('DEQ')" cssClass="btn"/>
					</td>
				</tr>
			</s:if>
    	</s:elseif>
    	<s:elseif test='menuType=="DEQ"'>
    	 
	  		<tr>
	    	     <td class="bg" width="100%">    	     	   	     	     
					<display:table name="declarationList" pagesize="10"  requestURI="${pageContext.request.contextPath}/initDeclaration.action"
					class="table" uid="row" id="record" style="width:100%; align:center; " cellpadding="1" cellspacing="1" >
					<display:setProperty name="paging.banner.one_item_found" value="" />
					<display:setProperty name="paging.banner.one_items_found" value="" />
					<display:setProperty name="paging.banner.all_items_found" value="" />
					<display:setProperty name="paging.banner.some_items_found" value="" />
					<display:setProperty name="paging.banner.placement"	value="bottom" />
					<display:setProperty name="paging.banner.onepage" value="" />
					<display:column sortable="true" style="text-align:center;width:9%" title="Quote No">
						<input type="hidden" name="selectedQuote" value="${record.QUOTE_NO}"/>${record.QUOTE_NO}
					</display:column>
					<display:column sortable="true" style="text-align:center;width:9%" title="Customer Name" property="FIRST_NAME"/>
					<display:column sortable="true" style="text-align:right;width:9%" title="Sum Insured" property="TOTAL_SUM_INSURED"/>
					<display:column sortable="true" style="text-align:right;width:9%" title="Premium" property="PREMIUM"/>
					</display:table>				
	    	     </td>
	    	</tr>
	    	<tr>
				<td>     				
	  				<div id="policyGeneration" style='%{referralUpdate=="Y"?"display:none":"display:block"}'>
		   				<table width="100%">
		   					<tr>
		   						<td>&nbsp;</td>
		   					</tr>
		   					<tr> 
		   						<td class="heading" width="100%"><s:label key="declaration.policyGeneration" /></td>
		   					</tr>
		   					<tr>
		   						<td class="bg">
		   			    			<table width="100%" border="1" bordercolor="#F2F2F2" align="center" cellpadding="4" cellspacing="0">
		   								<tr>
					     				 <td width="40%"><b><s:label key="declaration.generatePolicy" /></b></td>
					     				  <td width="60%"><s:radio list="#{'Y':'Yes','N':'No'}"  name="generatePolicy" id="generatePolicy" value="%{generatePolicy==null?'N':generatePolicy}" onclick="disablePolicyDetail(this);"/></td>     								  
		   								</tr> 
		   							 </table>
		   						</td>
		   					</tr>
		   				</table>
					</div>	
				</td>
			</tr>			
			<tr>
				<td>&nbsp;
	  				<div id="getPolicyDetail" style="display: none;">  
		    			<table width="100%">				     				
		    				<tr><td>&nbsp;</td></tr>
		    				<tr> 
		    					<td class="heading" width="100%"><s:label key="declaration.certificateDisplayInfo" /></td>
		    				</tr>	
		    				<tr>
		                      <td class="bg">                       
		                      		<table width="100%" border="1" bordercolor="#F2F2F2" align="center" cellpadding="4" cellspacing="0">
			                       <tr>                       
				                       <td width="20%"><s:checkbox name="rubberStamp" id="check_rubber_stamp" fieldValue="Y"   /><s:label key="declaration.printRubberStamp"  name="certificateInfo"/></td>
			                      </tr>
		                      		</table>
		                      	</td>
		                   </tr>       				
		                   <tr><td>&nbsp;</td></tr>
		    				<!--<tr> 
		    					<td class="heading" width="100%"><s:label key="declaration.documentBasis" /></td>
		    				</tr>
		    				<tr>
		    					<td class="bg">
		    			    		<table width="100%" border="1" bordercolor="#F2F2F2" align="center" cellpadding="4" cellspacing="0">
		    							<tr>
						     				 <td width="40%"><b><s:label key="declaration.noteType" /></b></td>
						     				 <td width="60%"><s:radio name="noteType" list="#{'N':'Net','G':'Gross'}" value="%{noteType==null?'N':noteType}" onclick="toggleCredit(this.value)"/></td>     								  
		    							</tr>
					     				<tr>
						     				 <td width="40%"><b><s:label key="declaration.paymentMode" /></b></td>
						     				 <td width="60%"><s:radio list="#{'CA':'Cash','CR':'Credit'}" name="paymentMode"  value="%{paymentMode==null?'CA':paymentMode}" /></td>     
					     				</tr>
	    							</table> 
								</td>
							</tr>
						-->
						<s:hidden name="noteType" value="N" />
						<s:hidden name="paymentMode" value="" />
						</table>
	  				</div>  
          		</td>
          	</tr>
	    	<tr align="center">
				<td class="text" >
					<input type="submit" name="close"  value="Back" class="btn" onclick="forward('DE')"/>&nbsp;
					<s:submit type="submit" name="submit" key="Submit" onclick="update('DEQ')" cssClass="btn"/>
				</td>
			</tr> 
			  
		</s:elseif>	
   </table>
   <s:hidden name="tranId"/>
   <s:hidden name="vessel"/>
   <s:hidden name="supplier"/>
   <s:hidden name="reqFrom"/>
   
 </s:form>
 <s:form name="report1" theme="simple" action="initDeclaration.action">
 	<s:hidden name="tranId" id="tranId1"/>
   	<s:hidden name="vessel" id="vessel1"/>
   	<s:hidden name="supplier" id="supplier1"/>
   	<s:hidden name="menuType" id="menuType1"/>
   	<s:hidden name="reqFrom" id="reqFrom1"/>
   	<s:hidden name="loginId" id="loginId1"/>
   	<s:hidden name="productId" id="productId1"/>
 </s:form>

</body>
<script type="text/javascript">
function disablePolicyDetail(obj){
   	var displayStatus=obj.value;   
	if(displayStatus=="N")
		document.getElementById('getPolicyDetail').style.display='none';
	else
		document.getElementById('getPolicyDetail').style.display='block';
}

function declaration(tranId){
	document.report.tranId.value=tranId;
	document.report.action = "${pageContext.request.contextPath}/showMultipleQuotes.jsp";
	document.report.submit();
}

function forward(menuType){
	if(menuType=='D'){
		document.report.tranId.value='';
		document.report.reqFrom.value='';
	}else if(menuType=='KD'){
		menuType='D';
		document.report.reqFrom.value='result';
		document.report.vessel.value='';
		document.report.supplier.value='';
	}	 
	document.report.action = "${pageContext.request.contextPath}/initDeclaration.action?menuType="+menuType;
	document.report.submit();
}

function update(menuType){
	document.report.action = "${pageContext.request.contextPath}/updateDeclaration.action?menuType="+menuType;
	document.report.submit();
}

function getVesselDeclare(vessel, transid, supplier){
	document.getElementById('tranId1').value=transid;
	document.getElementById('vessel1').value=vessel;
	document.getElementById('supplier1').value=supplier;
	document.getElementById('menuType1').value='D';
	document.getElementById('reqFrom1').value='result';
	document.report1.action = "${pageContext.request.contextPath}/initDeclaration.action";
	document.report1.submit();
}

function getBack(loginId){
	document.getElementById('loginId1').value=loginId;
	document.getElementById('menuType1').value='PD';
	document.getElementById('productId1').value='11';
	document.report1.action = "${pageContext.request.contextPath}/initReport.action";
	document.report1.submit();
}

function getConsigneeDetail(transid){
	document.getElementById('tranId1').value=transid;
	document.getElementById('reqFrom1').value='result';
	document.getElementById('menuType1').value='D';
	document.report1.action = "${pageContext.request.contextPath}/initDeclaration.action";
	document.report1.submit(); 
}
</script>
</html>


