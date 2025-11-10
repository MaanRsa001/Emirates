<%@ taglib prefix="s" uri="/struts-tags"%> 
<s:if test="#request.ELEMENT_NAME=='package'">
	<s:select name="packageCode" list="packageList" headerKey="" headerValue="-Select-" listKey="CODE" listValue="CODEDESC" cssClass="form-control" theme="simple" id="packageCode" />
</s:if>
<s:elseif  test="#request.ELEMENT_NAME=='cover'">
   	<s:if test="%{productId==openCover}" >
   		<%--<s:if test='coverList!=null && coverList.size==1'>
   			<s:select name="cover" id="cover" list="coverList" listKey="CODE" listValue="CODEDESC" cssClass="inputSelect" onchange="getList('?cover='+this.value,'conveyanceList');" />
   		</s:if>
   		<s:else>--%>
    		<s:select name="cover" id="cover" list="coverList" headerKey="" headerValue="-Select-" listKey="CODE" listValue="CODEDESC" cssClass="form-control"  onchange="getList('?cover='+this.value,'conveyanceList');"/>
    	<%--</s:else>--%>
    </s:if>	
    <s:else>
    	<s:select name="cover" id="cover" list="coverList" headerKey="" headerValue="-Select-" listKey="CODE" listValue="CODEDESC" cssClass="form-control" />                                
   	</s:else>
</s:elseif>
<s:elseif  test="#request.ELEMENT_NAME=='conveyance'">
	<%--
	<s:if test="%{productId==openCover}" >
		<s:if test='conveyanceList!=null && conveyanceList.size==1'>
			<s:select name="conveyance" list="conveyanceList" listKey="CODE" listValue="CODEDESC" cssClass="inputSelect" theme="simple" id="conveyanceList" />
		</s:if>
		<s:else>
			<s:select name="conveyance" list="conveyanceList" headerKey="" headerValue="-Select-" listKey="CODE" listValue="CODEDESC" cssClass="inputSelect" theme="simple" id="conveyanceList" />
		</s:else>
	</s:if>
	<s:else>
		<s:select name="conveyance" list="conveyanceList" listKey="CODE" listValue="CODEDESC" cssClass="inputSelect" theme="simple" id="conveyanceList" />
	</s:else>
	--%>
	<s:select name="conveyance" list="conveyanceList" headerKey="" headerValue="-Select-" listKey="CODE" listValue="CODEDESC" cssClass="form-control" theme="simple" id="conveyanceList" />
</s:elseif>
<s:elseif  test="#request.ELEMENT_NAME=='agent'">
	<s:select name="settlingAgent" list="agentList" headerKey="" headerValue="-Select-" listKey="CODE" listValue="CODEDESC" cssClass="form-control" theme="simple" id="agentList" onchange="disableOthers(this);" />
</s:elseif>
<s:elseif  test="#request.ELEMENT_NAME=='saleTermPercent'">
	<s:if test="%{productId==openCover}" >
		<s:if test='conveyanceList!=null && conveyanceList.size==1'>
   			<s:select name="saleTermPercent" list="percentList" listKey="CODE" listValue="CODEDESC" cssClass="form-control"  onchange="getList('?saleTermPercent='+this.value,'toleranceList');"/>
   		</s:if>
		<s:else>
    		<s:select name="saleTermPercent" list="percentList" headerKey="" headerValue="-Select-" listKey="CODE" listValue="CODEDESC" cssClass="form-control"  onchange="getList('?saleTermPercent='+this.value,'toleranceList');"/>
    	</s:else>
    </s:if>	
    <s:else>
    	<s:select name="saleTermPercent" list="percentList" headerKey="" headerValue="-Select-" listKey="CODE" listValue="CODEDESC" cssClass="form-control"   onchange="getList('?saleTermPercent='+this.value,'toleranceList');"/>                                
   	</s:else>
</s:elseif>
<s:elseif  test="#request.ELEMENT_NAME=='tolerance'">
	<s:select name="tolerance" list="toleranceList" listKey="CODE" listValue="CODEDESC" cssClass="form-control" />
</s:elseif>
<s:elseif  test="#request.ELEMENT_NAME=='lcNo'">
	<s:if test='lcList!=null && lcList.size==1'>
		<s:select name="lcNo" list="lcList" listKey="CODE" listValue="CODEDESC" cssClass="form-control" disabled="%{#disable2==false?(quoteStatus=='RA'):#disable2}"/>
	</s:if>
	<s:else>
		<s:select name="lcNo" list="lcList" headerKey="" headerValue="-Select-" listKey="CODE" listValue="CODEDESC" cssClass="form-control" disabled="%{#disable2==false?(quoteStatus=='RA'):#disable2}"/>
	</s:else>
</s:elseif>
<s:elseif  test="#request.ELEMENT_NAME=='brokersList'">
	<s:select name="brokerCode" id="brokerCode" list="brokerList" headerKey="" headerValue="-Select-"  listKey="CODE" listValue="CODEDESC" cssClass="form-control" onchange="getList('?brokerCode='+this.value,'executiveList');getList('?brokerCode='+this.value,'promotionalList');emptyCustInfo();"/>
</s:elseif>
<s:elseif  test="#request.ELEMENT_NAME=='executive'">
	<s:select name="executive" list="executiveList" headerKey="" headerValue="-Select-" listKey="CODE" listValue="CODEDESC" cssClass="form-control" value='executive==null?getText("quotation.executiveDefault"):executive'/>
</s:elseif>
<s:elseif  test="#request.ELEMENT_NAME=='brokerList'">
	<s:label key="report.selectuser" theme="simple"/>&nbsp;<s:select list="brokerList" listKey="LOGIN_ID" listValue="USERNAME" name="loginId" cssClass="form-control"  headerKey="" headerValue="Select" />
</s:elseif>
<s:elseif  test="#request.ELEMENT_NAME=='coverUpload'">
	<s:select name="ucover[%{rowNum}]" list="coverList" headerKey="" headerValue="-Select-" listKey="CODE" listValue="CODEDESC" cssClass="form-control" disabled="#disable" cssStyle="width:154px;"/>
</s:elseif>
<s:elseif  test="#request.ELEMENT_NAME=='saleTermPercentUpload'">
	<s:select name="usaleTermPercent[%{rowNum}]" id="percent" list="percentList" headerKey="" headerValue="-Select-" listKey="CODE" listValue="CODEDESC" cssClass="form-control" disabled="%{saleTerm=='205' || #disable}" onchange="getList('?saleTermPercent='+this.value+'&rowNum=%{rowNum}','toleranceList[%{rowNum}]','toleranceList');" cssStyle="width:70px;"/>
</s:elseif>
<s:elseif  test="#request.ELEMENT_NAME=='toleranceUpload'">
	<s:select name="utolerance[%{rowNum}]" list="toleranceList" listKey="CODE" listValue="CODEDESC" cssClass="form-control" disabled="#disable" cssStyle="width:70px;"/>
</s:elseif>
<s:elseif test="#request.ELEMENT_NAME=='packageUpload'">
	<s:select name="upkgDesc[%{rowNum}]" list="packageList" headerKey="" headerValue="-Select-" listKey="CODEDESC" listValue="CODEDESC" cssClass="form-control" disabled="#disable2" cssStyle="width:154px;"/>
</s:elseif>
<s:elseif  test="#request.ELEMENT_NAME=='travelCover'">
<s:select name="travelCover" list="travelCoverList" headerKey="" headerValue="-Select-" listKey="CODE" listValue="CODEDESC" cssClass="form-control" onchange="getAjaxCoverage(this.form,'?schemeCover=%{schemeCover}&travelCover='+this.value,'coverageList')"/>
</s:elseif>
<s:elseif  test="#request.ELEMENT_NAME=='coverages'">
<s:if test="coveragesList.size()>0">
      <table width="100%" border="0" align="center" cellpadding="4" cellspacing="0">
      	<s:iterator value="coveragesList" var="cov" status="stat">
            	<tr>
              	<td width="2%">&nbsp;</td>
               	<td width="40%">		             
               	<s:property value="CODEDESC"/>
               	</td>
               	<td width="35%">
               	<s:radio list="coverageYNList" name="coverages[%{#stat.count-1}]" id="coverages" value="%{'N'}" theme="simple" />
              	</td>
               	<td width="2%">&nbsp;</td>
              </tr>
         </s:iterator>
      </table>
</s:if>
<s:else>
      <table width="100%" border="0" align="center" cellpadding="4" cellspacing="0">
      <tr>
	      <td width="2%">&nbsp;</td>
	      <td><s:text name="travel.noSchemeAvailable"/></td>
	      <td width="2%">&nbsp;</td>
      </tr> 
      </table>
</s:else>
</s:elseif>
<s:elseif  test="#request.ELEMENT_NAME=='searchResult'">
<display:table name="policyList" pagesize="10"  requestURI="${pageContext.request.contextPath}/initReport.action?reqFrm=Normal"
					class="table" uid="row" id="record" style="width:100%; align:center; " cellpadding="1" cellspacing="1" >
					<display:setProperty name="paging.banner.one_item_found" value="" />
					<display:setProperty name="paging.banner.one_items_found" value="" />
					<display:setProperty name="paging.banner.all_items_found" value="" />
					<display:setProperty name="paging.banner.some_items_found" value="" />
					<display:setProperty name="paging.banner.placement"	value="bottom" />
					<display:setProperty name="paging.banner.onepage" value="" />
					<display:setProperty name="basic.empty.showtable" value="true"/>
					<display:setProperty name="paging.banner.no_items_found" value=""/>	
					<s:if test='menuType!="T" && menuType!="PD" && menuType!="C"'>									
						<display:column sortable="true" style="text-align:center;width:3%" titleKey="report.sno" value="${record_rowNum}"/> 
						<display:column sortable="true" style="text-align:center;width:9%" titleKey="report.quoteNo" property="QUOTE_NO"/>
						<display:column sortable="true" style="text-align:left;width:16%" titleKey="report.custName" property="CUSTOMER_NAME"/>
						<display:column sortable="true"	style="text-align:center;width:13%" titleKey="report.quoteDate"	property="QUOTATION_DATE"/>
					</s:if>							
					<s:if test='menuType=="P" || menuType=="PE"'> 
						<s:if test='#session.product_id!="31" && #session.product_id!="32" && #session.product_id!="33" && #session.product_id!="41" && #session.product_id!="65" && #session.product_id!="30"'>
						<display:column sortable="true"	style="text-align:right;width:6%" titleKey="report.premium" format="{0,number,##,##0.00}"  property="PREMIUM"/>					
						<display:column sortable="true" style="text-align:center;width:13%" titleKey="report.policyNo" property="POLICY_NO"/>
						<display:column sortable="true"	style="text-align:center;width:10%" titleKey="report.schedule" >
							<a href="#" onclick="return popUp('${pageContext.request.contextPath}/Copy of information.jsp?policyMode=schedule&policynumber=${record.POLICY_NO}&loginid=${record.LOGIN_ID}','1000','500')">Schedule</a>
						</display:column>
						<display:column sortable="true"	style="text-align:center;width:10%" titleKey="report.debitNote" >
							<c:if test="${record.DEBIT_NOTE_NO != null}">
								<a href="#" onclick="return popUp('${pageContext.request.contextPath}/Copy of information.jsp?policyMode=debit&policynumber=${record.POLICY_NO}&loginid=${record.LOGIN_ID}','1000','500')">Debit Note</a>
							</c:if>
						</display:column>
						</s:if>
						<s:else>
						<display:column sortable="true"	style="text-align:right;width:6%" titleKey="report.premium" format="{0,number,##,##,###}"  property="OVERALL_PREMIUM"/>					
						<display:column sortable="true" style="text-align:center;width:13%" titleKey="report.policyNo" property="POLICY_NO"/>
						<%--<display:column sortable="true"	style="text-align:center;width:8%" titleKey="report.corrections">
							<a href="#" onclick="popUp('viewTravel.action?quoteNo=${record.QUOTE_NO}&applicationNo=${record.APPLICATION_NO}',650,550)">Correction</a>
						</display:column>--%>
						<display:column sortable="true"	style="text-align:center;width:10%" titleKey="report.schedule" >
							   <a href="#" onclick="return popUp('${pageContext.request.contextPath}/PdfSummary_Schedule.Travel?quoteNo=${record.QUOTE_NO}','1000','500')">Schedule</a>
						</display:column>
						<display:column sortable="true"	style="text-align:center;width:10%" titleKey="report.debitNote" >
							<c:if test="${record.DEBIT_NOTE_NO != null}">
							    <a href="#" onclick="return popUp('${pageContext.request.contextPath}/PdfSummary_Debit.Travel?quoteNo=${record.QUOTE_NO}','1000','500')">Debit</a>
							</c:if>
						</display:column>
						<display:column sortable="true"	style="text-align:center;width:10%" titleKey="report.receipt" >
							<c:if test="${record.DEBIT_NOTE_NO != null}">
							    <a href="#" onclick="return popUp('${pageContext.request.contextPath}/PdfSummary_Receipt.Travel?quoteNo=${record.QUOTE_NO}','1000','500')">Receipt</a>
							</c:if>
						</display:column>
						</s:else>
						<s:if test='#session.product_id!="31" && #session.product_id!="32" && #session.product_id!="33"&& #session.product_id!="41" && #session.product_id!="65" && #session.product_id!="30"'>
						<display:column sortable="true"	style="text-align:center;width:10%" titleKey="report.creditNote" >
							<c:if test="${record.CREDIT_NOTE_NO != null}">
								<a href="#" onclick="return popUp('${pageContext.request.contextPath}/Copy of information.jsp?policyMode=credit&policynumber=${record.POLICY_NO}&loginid=${record.LOGIN_ID}','1000','500')">Credit Note</a>
							</c:if>
						</display:column>
						</s:if>
						<%--<s:else>
						<display:column sortable="true"	style="text-align:center;width:10%" titleKey="report.cancelReIssue" >
		                    <a href="#" onclick="return popUp('${pageContext.request.contextPath}/cancelReissueTravel.action?quoteNo=${record.QUOTE_NO}&applicationNo=${record.APPLICATION_NO}&policyNo=${record.POLICY_NO}&product_id=#session.product_id &branch_code=#session.branch_code','650','420');">Cancel/ReIssue</a>
						</display:column>
						</s:else>--%>
						<display:column sortable="true"	style="text-align:center;width:10%" titleKey="report.endorse" >
							<a href="#" onclick='endorsement("E","${record.QUOTE_NO}","${record.POLICY_NO}","${record.CUSTOMER_NAME}","${record.BROKER_NAME}")'>Endorse</a>
						</display:column>
					</s:if>
					<s:elseif test='menuType=="QL"'>
						<display:column sortable="true"	style="text-align:center;width:13%" title="Validity Date"	property="VALIDITY_DATE"/>
						<display:column sortable="true"	style="text-align:center;width:8%" title="Active">
							<a href="#" onclick="sentEMail('','ACTIVE','${record.QUOTE_NO}')" >Active</a>
						</display:column>
						<display:column sortable="true"	style="text-align:center;width:8%" title="DeActive">
						<a href="#" onclick="sentEMail('','LAPSED','${record.QUOTE_NO}')">Reject</a>
						</display:column>
					</s:elseif>
					<s:elseif test='menuType=="QE" || menuType=="QS"'>
						<display:column sortable="true"	style="text-align:center;width:13%" title="Validity Date"	property="VALIDITY_DATE"/>
						<s:if test='menuType=="QE"'>
						<s:if test='#session.product_id=="31" || #session.product_id=="32" || #session.product_id=="33" || #session.product_id=="65" && #session.product_id=="30"'>					
						<display:column sortable="true"	style="text-align:right;width:6%" title="Premium" format="{0,number,##,##,###}"  property="PREMIUM"/>
						</s:if>
						<s:else>					
						<display:column sortable="true"	style="text-align:right;width:6%" title="Premium" format="{0,number,##,##,###.00}"  property="PREMIUM"/>
						</s:else>					
						</s:if>
						<display:column sortable="true"	style="text-align:center;width:8%" title="Edit">
							<a href="#" onclick="editQuote('${record.APPLICATION_NO}','${record.QUOTE_NO}','<s:property value="%{menuType}" />','${record.CUSTOMER_ID}')">Edit</a>
						</display:column>
						<s:if test='menuType=="QE"'>
						<s:if test='#session.product_id!="31" && #session.product_id!="32" && #session.product_id!="33" && #session.product_id!="65" && #session.product_id!="30"'>
						<display:column sortable="true"	style="text-align:center;width:8%" title="Email">
							<a href="#" onclick="sentEMail('${record.APPLICATION_NO}','MAIL','${record.QUOTE_NO}')">Email</a>
						</display:column>
						<display:column sortable="true"	style="text-align:center;width:8%" title="Print">
							<a href="#" onclick="viewQuote('${record.QUOTE_NO}')">Print</a>
						</display:column>
						</s:if>
						<s:else>
						<display:column sortable="true"	style="text-align:center;width:8%" title="Email">
							<a href="#" onclick="sentEMail('${record.APPLICATION_NO}','MAIL','${record.QUOTE_NO}')">Email</a>
						</display:column>
						<display:column sortable="true"	style="text-align:center;width:8%" title="Print">
							<a href="#" onclick="return popUp('${pageContext.request.contextPath}/PdfSummary_Draft.Travel?quoteNo=${record.QUOTE_NO}','1000','500')">Print</a>
						</display:column>
						</s:else>
						</s:if>
						<display:column sortable="true"	style="text-align:center;width:8%" title="Reject">
							<a href="#" onclick="sentEMail('${record.APPLICATION_NO}','LAPSED','${record.QUOTE_NO}')">Reject</a>
						</display:column>	
					</s:elseif>
					<s:elseif test='menuType=="RU" || menuType=="RA"'>
						<display:column sortable="true"	style="text-align:center;width:8%" title="Edit">
							<a href="#" onclick="editQuote('${record.APPLICATION_NO}','${record.QUOTE_NO}','<s:property value="%{menuType}" />','${record.CUSTOMER_ID}')">Edit</a>
						</display:column>
						<display:column sortable="true"	style="text-align:center;width:8%" title="Reject">
							<a href="#" onclick="sentEMail('','LAPSED','${record.QUOTE_NO}')">Reject</a>
						</display:column>
					</s:elseif>
					<s:elseif test='menuType=="RR"'>
						<display:column sortable="true"	style="text-align:center;width:13%" title="Remarks"	property="LAPSED_REMARKS"/>
						<display:column sortable="true"	style="text-align:center;width:8%" title="View">
							<a href="#" onclick="popUp('viewTravel.action?quoteNo=${record.QUOTE_NO}&applicationNo=${record.APPLICATION_NO}&selection=profile',650,650)">View</a>
						</display:column>
					</s:elseif>
					<s:elseif test='menuType=="L"'>
						<display:column sortable="true"	style="text-align:center;width:13%" title="Validity Date"	property="VALIDITY_PERIOD"/>
						<display:column sortable="true"	style="text-align:center;width:15%" title="Rejected Date" property="LAPSED_DATE"/>	
						<display:column sortable="true"	style="text-align:center;width:15%" title="Remarks" property="LAPSED_REMARKS"/>
						<display:column sortable="true"	style="text-align:center;width:8%" title="View">
							<a href="#" onclick="popUp('viewTravel.action?quoteNo=${record.QUOTE_NO}&applicationNo=${record.APPLICATION_NO}&selection=profile',650,650)">View</a>
						</display:column>
					</s:elseif>
					<s:elseif test='menuType=="T"'>
						<display:column sortable="true" style="text-align:center;width:6%" title="S.No" value="${record_rowNum}"/>
						<display:column sortable="true" style="text-align:center;width:9%" title="Transaction Id" property="TRANSACTION_ID"/>
						<display:column sortable="true" style="text-align:center;width:16%" title="Valid Records" >
							<a href="#" onclick="return declaration('${record.TRANSACTION_ID}')">${record.VALID_RECORDS}</a>
						</display:column>
						<display:column sortable="true"	style="text-align:center;width:13%" title="Invalid Records"	property="INVALID_RECORDS"/>
					</s:elseif>
					<s:elseif test='menuType=="PD"'>
						<display:column sortable="true" style="text-align:center;width:6%" title="S.No" value="${record_rowNum}"/> 
						<display:column sortable="true" style="text-align:center;width:10%" title="Policy No." property="POLICY_NO"/>
						<display:column sortable="true" style="text-align:center ;width:14%" title="OpenCover Customer Name" property="OPENCOVER_CUST_NAME"/>
						<display:column sortable="true"	style="text-align:right;width:6%" title="Premium"  format="{0,number,##,##,###.00}"	property="PREMIUM"/>						
						<display:column sortable="true"	style="text-align:center;width:8%" title="Total of Certificates" >
							<a href="#" onclick="declaration('${record.POLICY_NO}')"/><s:property value="#attr.record.POLICY_COUNT"/> </a>
						</display:column>
						<display:column sortable="true"	style="text-align:center;width:8%" title="Schedule" >
							<a href="#" onclick="return popUp('${pageContext.request.contextPath}/Copy of information.jsp?policyMode=scheduleMultiple&policynumber=${record.POLICY_NO}&loginid=${record.LOGIN_ID}','1000','500')">Schedule</a>
						</display:column>
						<display:column sortable="true"	style="text-align:center;width:8%" title="Debit Note" >
							<c:if test="${record.DEBIT_NOTE_NO != null}">
								<a href="#" onclick="return popUp('${pageContext.request.contextPath}/Copy of information.jsp?policyMode=debitMultiple&policynumber=${record.POLICY_NO}&loginid=${record.LOGIN_ID}','1000','500')">Debit</a>
							</c:if>
						</display:column>
						<display:column sortable="true"	style="text-align:center;width:8%" title="Credit Note" >
							<c:if test="${record.CREDIT_NOTE_NO != null}">
								<a href="#" onclick="return popUp('${pageContext.request.contextPath}/Copy of information.jsp?policyMode=creditMultiple&policynumber=${record.POLICY_NO}&loginid=${record.LOGIN_ID}','1000','500')">Credit</a>
							</c:if>
						</display:column>
					</s:elseif>		
					<s:elseif test='menuType=="C"'>
						<display:column sortable="true" style="text-align:center;width:6%" title="S.No" value="${record_rowNum}"/> 
						<display:column sortable="true"	style="text-align:left;width:13%" title="Customer Name"	property="FIRST_NAME"/>					
						<display:column sortable="true"	style="text-align:left;width:10%" title="Address"  property="ADDRESS"/>
						<display:column sortable="true"	style="text-align:left;width:10%" title="Customer Civil ID" property="CUSTOMER_SOURCE"/>					
						<display:column sortable="true"	style="text-align:left;width:15%" title="Email Id" property="EMAIL"/>
						<display:column sortable="true"	style="text-align:left;width:10%" title="Contact No." property="MOBILE"/>	
					<%--	<display:column sortable="true"	style="text-align:left;width:10%" title="Core App. Code" property="MISSIPPI_CUSTOMER_CODE"/> --%>
						<display:column sortable="true"	style="text-align:center;width:10%" title="Edit">
							<a href="#" onclick="editQuote('${record.APPLICATION_NO}','${record.QUOTE_NO}','<s:property value="%{menuType}" />','${record.CUSTOMER_ID}')">Edit</a>						
						</display:column>									
					</s:elseif>	
					<s:elseif test='menuType=="E"'> 
						<display:column sortable="true"	style="text-align:right;width:6%" title="Premium" format="{0,number,##,###}"  property="PREMIUM"/>					
						<display:column sortable="true" style="text-align:center;width:13%" title="Policy Number">
							<c:if test='${record.STATUS == "P"}'>
								<c:out value="${record.POLICY_NO}"/>
							</c:if>
						</display:column>
						<s:if test='#session.product_id=="3" || #session.product_id=="11"'>
						<display:column sortable="true"	style="text-align:center;width:10%" title="Schedule" >
							<c:if test='${record.STATUS == "P"}'>
								<a href="#" onclick="return popUp('${pageContext.request.contextPath}/Copy of information.jsp?policyMode=schedule&policynumber=${record.POLICY_NO}&loginid=${record.LOGIN_ID}','1000','500')">Schedule</a>
							</c:if>
						</display:column>
						<display:column sortable="true"	style="text-align:center;width:10%" title="Debit Note" >
							<c:if test='${record.STATUS == "P" && record.DEBIT_NOTE_NO != null}'>
								<a href="#" onclick="return popUp('${pageContext.request.contextPath}/Copy of information.jsp?policyMode=debit&policynumber=${record.POLICY_NO}&loginid=${record.LOGIN_ID}','1000','500')">Debit Note</a>
							</c:if>
						</display:column>
						<display:column sortable="true"	style="text-align:center;width:10%" title="Credit Note" >
							<c:if test='${record.STATUS == "P" && record.CREDIT_NOTE_NO != null}'>
								<a href="#" onclick="return popUp('${pageContext.request.contextPath}/Copy of information.jsp?policyMode=credit&policynumber=${record.POLICY_NO}&loginid=${record.LOGIN_ID}','1000','500')">Credit Note</a>
							</c:if>
						</display:column>
						</s:if>
						<s:else>
						<display:column sortable="true"	style="text-align:center;width:10%" title="Schedule" >
						    <c:if test='${record_rowNum == "1"}'>
								<c:set var="eQuoteNo" value="${record.QUOTE_NO}"/>
								<c:set var="eApplicationNo" value="${record.APPLICATION_NO}"/>
					        </c:if>
							<c:if test='${record.STATUS == "P"}'>
								<a href="#" onclick="return popUp('${pageContext.request.contextPath}/PdfSummary_Schedule.Travel?quoteNo=${record.QUOTE_NO}','1000','500')">Schedule</a>
							</c:if>
						</display:column>
						<display:column sortable="true"	style="text-align:center;width:10%" title="Debit Note" >
							<c:if test='${record.STATUS == "P" && record.DEBIT_NOTE_NO != null}'>
								<a href="#" onclick="return popUp('${pageContext.request.contextPath}/PdfSummary_Debit.Travel?quoteNo=${record.QUOTE_NO}','1000','500')">Debit Note</a>
							</c:if>
						</display:column>
						 <display:column sortable="true"	style="text-align:center;width:10%" title="Receipt" >
							<c:if test='${record.STATUS == "P" && record.RECEIPT_NO != null}'>
								<a href="#" onclick="return popUp('${pageContext.request.contextPath}/PdfSummary_Receipt.Travel?quoteNo=${record.QUOTE_NO}','1000','500')">Receipt</a>
							</c:if>
						  </display:column>
						</s:else>
						<display:column sortable="true"	style="text-align:center;width:10%" title="Status" >
								<c:choose>
									<c:when test='${record.REF_STATUS == "RU"}'>
										Referral
									</c:when>
									<c:when test='${record.REF_STATUS == "RA"}'>
										Referral Approved
									</c:when>
									<c:when test='${record.REF_STATUS == "RR"}'>
										Referral Rejected
									</c:when>
									<c:when test='${record.REF_STATUS == "N"}'>
										Normal
									</c:when>
								</c:choose>
						</display:column>
						<display:column sortable="true"	style="text-align:center;width:10%" titleKey="report.endorse" >
							<s:if test='issuer !=null && issuer.length() > 0' >
								<c:choose>
									<c:when test='${record.STATUS == "E" && record.ENDT_STATUS == "Y"}'>
										<a href="#" onclick="endorsementType('ET','P','${record.ENDT_TYPE}','${record.QUOTE_NO}','${record.REF_STATUS}','${record.APPLICATION_NO}')">Edit</a>
										<s:set var="endtStatus" value="%{'Y'}" ></s:set>
									</c:when>
									<c:when test='#session.product_id=="3" || #session.product_id=="11"'>
									<c:when test='${record.STATUS == "P" && record.ENDT_STATUS == "Y"}'>
										<a href="#" onclick="popUp('${pageContext.request.contextPath}/documentReport.action?policyNo=${record.POLICY_NO}&applicationNo=${record.APPLICATION_NO}&endTypeId=${record.ENDT_TYPE}','1000','500')">Endt Schedule</a>
										<s:set var="endtStatus" value="%{'N'}" ></s:set>
										<c:set var="quoteNo" value="${record.QUOTE_NO}" ></c:set>
										<c:set var="applicationNo" value="${record.APPLICATION_NO}" ></c:set>
									</c:when>
									<c:when test='${record.STATUS == "P"}'>
										<a href="#" onclick="popUp('${pageContext.request.contextPath}/documentReport.action?policyNo=${record.POLICY_NO}&applicationNo=${record.APPLICATION_NO}&endTypeId=${record.ENDT_TYPE}','1000','500')">Endt Schedule</a>
									</c:when>
									</c:when>
								</c:choose>
							</s:if>
						</display:column>
					</s:elseif>
					</display:table>
</s:elseif>
<s:elseif  test="#request.ELEMENT_NAME=='showDoc'">
<s:hidden name="FileCountforDrm%{docId}" id="FileCountforDrm%{docId}" value="%{updDocumentList.size()}" />
	<table align="center" width="100%">
	<s:if test='updDocumentList!=null && updDocumentList.size()>0'>
	<tr>
      	<td width="5%" align="center" class="heading"><s:text name="label.file.id" /></td>
	    <td width="20%" align="center" class="heading"><s:text name="label.file.name" /></td>
	    <td width="45%" align="center" class="heading"><s:text name="label.file.desc" /></td>
	    <td width="10%" align="center" class="heading"><s:text name="label.upload" />&nbsp;<s:text name="label.date" /></td>
	    <td width="10%" align="center" class="heading"><s:text name="label.download" /></td>
	    <td width="10%" align="center" class="heading"><s:text name="label.delete" /></td>
   	</tr>
   	<s:hidden name="FileCountforDrm%{docId}" id="FileCountforDrm%{docId}" value="%{updDocumentList.size()}" />
   	<s:iterator value="updDocumentList" var="stat">
    	<tr style="background-color: #F7F7F7;">
    		<td style="text-align: center;"><s:property value="#stat.docSNo" /></td>
    		<td>
    			<a href="#" onclick="openUploadedDoc('<s:property value="#stat.docOurName" />')">
    				<s:property value="#stat.docName" />
    			</a>
   			</td>
    		<td><s:property value="#stat.docDesc" /></td>
    		<td style="text-align: center;"><s:property value="#stat.docUploadDate" /></td>
    		<td align="center">
			    <input type="button" class="btn" name="download" value="Download" onclick="downloadFile('<s:property value="#stat.docOurName" />','<s:property value="#stat.docName" />',this.form)" />
			</td>
			<td align="center">
				<input type="button" class="btn" name="delete" value="Delete" onclick="deleteDocuments('<s:property value="#stat.docId" />', '<s:property value="#stat.docSNo" />')" />
   			</td>  
    	</tr>
   	</s:iterator>
   	</s:if>
   	<s:else>
   		<tr>
   			<td align="center"><s:text name="msg.details.not.found" /></td>
   		</tr>
   	</s:else>
   	</table>
</s:elseif>
<s:elseif  test="#request.ELEMENT_NAME=='customerList'">
	<table width="100%" border="1" bordercolor="#A4A4A4"  align="center" cellpadding="4" cellspacing="0" >
					<tr>     				   
						<td width="2%" bgcolor="#4f6180" style="color: #FFFFFF" align="center"></td>
						<td width="4%" bgcolor="#4f6180" style="color: #FFFFFF" align="center"><b><s:label key="customer.customerName"  theme="simple"/></b></td>
						<td width="10%"  bgcolor="#4f6180" style="color: #FFFFFF" align="center"><b><s:label key="customer.coreAppCode"  theme="simple"/></b></td>
						<td width="6%"  bgcolor="#4f6180" style="color: #FFFFFF" align="center"><b><s:label key="customer.city"  theme="simple"/></b></td>
						<td width="6%"  bgcolor="#4f6180" style="color: #FFFFFF" align="center"><b><s:label key="customer.mobileNo"  theme="simple"/></b></td>
						<td width="10%"  bgcolor="#4f6180" style="color: #FFFFFF" align="center"><b><s:label key="customer.email"  theme="simple"/></b></td>                       
					</tr>
					<s:if test="customerSelection.size > 0">					
					<s:iterator value="customerSelection" var="customerdetail" status="stat">
						<tr>   
						 	<td width="2%"><input type="radio" name="selects"onclick="customerDetail('<s:property value="%{#customerdetail.TITLE}"/>','<s:property value="%{#customerdetail.ADDRESS1}"/>','<s:property value="%{#customerdetail.ADDRESS2}"/>','<s:property value="%{#customerdetail.MOBILE}"/>','<s:property value="%{#customerdetail.EMIRATE}"/>','<s:property value="%{#customerdetail.POBOX}"/>','<s:property value="%{#customerdetail.FIRST_NAME}"/>','<s:property value="%{#customerdetail.MISSIPPI_CUSTOMER_CODE}"/>','<s:property value="%{#customerdetail.CUSTOMER_ID}"/>','<s:property value="%{#customerdetail.EMAIL}"/>','<s:property value="%{#customerdetail.CUST_AR_NO}"/>','<s:property value="%{#customerdetail.cust_name}"/>')"/></td>   				  
							<td width="10%" class="bg"><s:property value="FIRST_NAME" /></td>
							<td width="4%" class="bg"><s:property value="MISSIPPI_CUSTOMER_CODE"  /></td>
							<td width="10%" class="bg"><s:property value="CITY_NAME" /></td>
							<td width="6%" class="bg"><s:property value="MOBILE" /></td>
							<td width="10%" class="bg"><s:property value="EMAIL" /></td>
						</tr>
					</s:iterator>
					</s:if>
					<s:else>
					<tr><td colspan="5"><s:label key="customer.msg.nothingFound" theme="simple"></s:label> </tr>
					</s:else>
	</table>					
</s:elseif>
<s:elseif  test="#request.ELEMENT_NAME=='model'">
	<s:select name="model" id="model" list="modelList" listKey="CODE" listValue="CODEDESC" headerKey="" headerValue="-Select-" cssClass="form-control"/>
</s:elseif>
<s:elseif  test="#request.ELEMENT_NAME=='noOfCylinder'">
	<s:select name="noOfCylinder" list="noOfCylinderList" listKey="CODE" listValue="CODEDESC" headerKey="" headerValue="-Select-" cssClass="form-control"/>
</s:elseif>		
<s:elseif  test="#request.ELEMENT_NAME=='promotionalList'">
	<s:if test='dubaiTradeStatus'>
		<s:text name="Promotional Code" /><br />
	    <s:textfield name="promotionalCode" cssClass="form-control"  maxlength="25" disabled="#disable"/>
	</s:if>
</s:elseif>
<s:elseif  test="#request.ELEMENT_NAME=='fragileoff'">
	<s:radio name="fragile[0]" list="#{true:'Yes',false:'No'}" disabled="true" value='false' />
</s:elseif>	
<s:elseif  test="#request.ELEMENT_NAME=='fragileon'">
	<s:radio name="fragile[0]" list="#{true:'Yes',false:'No'}" disabled="#disable" value='%{#comLst.size()>0 ? #comLst[0].FRAGILE_SELECTED: false}'/>
</s:elseif>
<s:elseif test="#request.ELEMENT_NAME=='comList'">
	<s:text name="Commodity Name" /><font color="red">*</font>
		<s:if test='productId==11'>
			<s:select name="commodityId" list="goodsCategoryList" headerKey="" headerValue="-Select-" listKey="CODE" listValue="CODEDESC" disabled="%{#disable1}" value='#comLst.size()>0 ? #comLst[0].COMMODITY_ID : ""' onchange="this.form.selectCommodity.value=this.value;this.form.__checkbox_selectCommodity.value=this.value;callAjax(this.value);" cssClass="form-control"/>
			<s:checkbox  name="selectCommodity" value="true" fieldValue='%{#comLst.size()>0 ? #comLst[0].COMMODITY_ID : ""}' disabled="#disable" cssStyle="visibility:hidden;"/>
		</s:if>
		<s:else>
			<s:select name="commodityId" list="goodsCategoryList" headerKey="" headerValue="-Select-" listKey="CODE" listValue="CODEDESC" disabled="%{#disable1}" value='#comLst.size()>0 ? #comLst[0].COMMODITY_ID : ""' onchange="this.form.selectCommodity.value=this.value;this.form.__checkbox_selectCommodity.value=this.value;" cssClass="form-control"/>
			<s:checkbox  name="selectCommodity" value="true" fieldValue='%{#comLst.size()>0 ? #comLst[0].COMMODITY_ID : ""}' disabled="#disable" cssStyle="visibility:hidden;"/>
		</s:else>
</s:elseif>