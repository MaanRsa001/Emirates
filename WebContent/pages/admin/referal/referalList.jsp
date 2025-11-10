<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ page isELIgnored="false" %>
<html>
	<head>
		<sj:head jqueryui="true" jquerytheme="start" />
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/cssbootstrap/bootstrap.min.css" />
		<link href="<%=request.getContextPath()%>/cssbootstrap/footable-0.1.css" rel="stylesheet" type="text/css" />
		<style type="text/css">
.royamenuhead a {
	color: #fff;
}

th.sortable a {
	background-color: #337ab7;
}

#loading {
	  width: 100%;
	  height: 100%;
	  top: 0px;
	  left: 0px;
	  position: fixed;
	  display: block;
	  opacity: 0.7;
	  background-color: #fff;
	  z-index: 99;
	  text-align: center;
	}
	
	#loading-image {
	  position: absolute;
	  top: 30%;
	  left: 45%;
	  z-index: 100;
	  width: 100px;
	  height: 100px;
	}
</style>
		<script type="text/javascript">
			function stopRKey(evt) { 
			  var evt = (evt) ? evt : ((event) ? event : null); 
			  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
			  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
			} 
			document.onkeypress = stopRKey;
		
			function fnCall(from){
				if(from=='edit')
					document.rejected.action = from+"BrokerMgm.action?mode=edit";
				else if(from=='userDetail')
					document.rejected.action = "getABListUserMgm.action?mode1=broker";
				else if(from=='customerDetail')
					document.rejected.action = "getABListCustomerMgm.action?mode1=broker";
				else if(from=='referal')
					document.rejected.action = "getOCListReferal.action";
				else if(from=='openCover')
					document.rejected.action = "opencoverOC.action";
				else
					document.rejected.action = from+"BrokerMgm.action";
				document.rejected.submit();
			}
			
			function fnTab(pid, reqFrom, value, index){
				var agencyCode=document.getElementById('agencyCode1').value;
				var borg=document.getElementById('borganization1').value;
				if(reqFrom=="pending"){
					postRequestNew('${pageContext.request.contextPath}/getOCAjaxReferal.action?reqFrom='+reqFrom+'&productID='+pid+'&index=0&from1=ajax&agencyCode='+agencyCode+'&borganization='+borg, 'pendingPolicies');
		   		}else if(reqFrom=="approved"){
		   			postRequestNew('${pageContext.request.contextPath}/getOCAjaxReferal.action?reqFrom='+reqFrom+'&productID='+pid+'&index=1&from1=ajax&agencyCode='+agencyCode+'&borganization='+borg, 'approvedPolicies');
		   		}else if(reqFrom=="rejected"){
		   			postRequestNew('${pageContext.request.contextPath}/getOCAjaxReferal.action?reqFrom='+reqFrom+'&productID='+pid+'&index=2&from1=ajax&agencyCode='+agencyCode+'&borganization='+borg, 'rejectedPolicies');
		   		}else{
		   			postRequestNew('${pageContext.request.contextPath}/getOCAjaxReferal.action?reqFrom='+value+'&productID='+pid+'&from1=ajax&index='+index+'&rdate='+reqFrom+'&agencyCode='+agencyCode+'&borganization='+borg, value+'Policies');
		   		}
		   	}
			/*
			$(function(){
				var index = '<s:property value="index"/>';
				var t = $('#tabs');
				var tabs = t.tabs('tabs');
				t.tabs('select', tabs[index].panel('options').title);
			});
			*/
			function editQuote(applicationNo, loginId, proID, eDate,quoteNo,schemeId, quoteStatus){
				 if(proID=='3'|| proID=='11'){
					document.form1.quoteStatus.value='RU';
					document.form1.applicationNo.value=applicationNo;
					document.form1.loginId.value=loginId;
					document.form1.pid.value=proID;
					document.form1.quoteStatus.value=quoteStatus;
					document.form1.action = "editQuoteQuotation.action";
					document.form1.submit();
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
			
  		</script>
	</head>
	<body>
		<div id="loading" style="width:100%;display:none;">
		   <img id="loading-image" src="${pageContext.request.contextPath}/images/ajax-loader.gif"/>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">
				<s:text name="Referral Cases" />
			</div>
			<div class="panel-body">
				<sj:tabbedpanel id="tabs" cache="false" selectedTab="%{index}" cssStyle="font-family:Arial; font-size:12px;">
					<sj:tab id="tab1" target="tabs-1" label="Pending Quotes"/>
				 	<sj:tab id="tab2" target="tabs-2" label="Approved Quotes"/>
				 	<sj:tab id="tab3" target="tabs-3" label="Rejected Quotes"/>
				 	<div id="tabs-1">
							<div id="pendingPolicies">
								<s:form id="pending" name="pending" theme="simple">
									<table width="50%" style="margin: 0 auto;">
										<tr><td colspan="5" align="center"> <s:text name="product.product"/></td><td><s:select name="productID" cssClass="inputSelect" id="productID" list="productDet" headerKey="" headerValue="-Select-" listKey="PRODUCT_ID" listValue="PRODUCT_NAME" onchange="fnTab(this.value, 'pending','','')"/></td></tr>
										<s:if test='(rdate!=null || "".equals(rdate)) && "0".equals(index)'>
										<tr>
											<td colspan="2">Selected Date is <s:property value="rdate"/></td>
										</tr>
										</s:if>
									</table>
									<br/>
									<s:if test='(rdate!=null || "".equals(rdate)) && "0".equals(index)'>
										 <display:table name="policyList" pagesize="10" requestURI="/getOCAjaxReferal.action?from1=Normal&agencyCode=%{agencyCode}&borganization=%{borganization}" class="footable" uid="row" id="record" excludedParams="from1" style="text-align:center;font-size:13px;">
							                  <display:setProperty name="paging.banner.one_item_found" value="" />
							                  <display:setProperty name="paging.banner.one_items_found" value="" />
							                  <display:setProperty name="paging.banner.all_items_found" value="" />
							                  <display:setProperty name="paging.banner.some_items_found" value="" />
							                  <display:setProperty name="paging.banner.placement" value="bottom" />
							                  <display:setProperty name="paging.banner.onepage" value="" />
							                  <%--<display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="Application No" property="APPLICATION_NO"/>--%>
							                  <display:column sortable="false" style="text-align:left;font-size:13px;" title="Broker Organization" property="brokername"/>
							                  <display:column sortable="false" style="text-align:left;font-size:13px;" title="Quote Created By" property="quote_Created"/>
							                  <display:column sortable="false" style="text-align:left;font-size:13px;" title="Customer Name" property="CUST_NAME"/>
							                  <display:column sortable="false" style="text-align:center;font-size:13px;" title="Quote No">
							                      <a type="button" class="btn btn-sm btn-primary" style="color: #ffffff;" href="#" onclick="editQuote('${record.APPLICATION_NO}', '${session.user}','${productID}','${rdate}',${record.QUOTE_NO},'${record.SCHEME_ID}','RU');">${record.QUOTE_NO}</a>
							                  </display:column>
							                  <display:column sortable="false" style="text-align:left;font-size:13px;" title="Remarks" property="REMARKS"/>
							                  <display:column sortable="false" style="text-align:left;font-size:13px;" title="Status" property="STATUS"/>
							              </display:table>
									</s:if>
									<s:else>
										 <display:table name="occListP" pagesize="10" requestURI="/getOCAjaxReferal.action?from1=Normal&index=0&agencyCode=%{agencyCode}&borganization=%{borganization}" class="footable" uid="row" id="record1" excludedParams="index" style="text-align:center;font-size:13px;">
				                            <display:setProperty name="paging.banner.one_item_found" value="" />
				                            <display:setProperty name="paging.banner.one_items_found" value="" />
				                            <display:setProperty name="paging.banner.all_items_found" value="" />
				                            <display:setProperty name="paging.banner.some_items_found" value="" />
				                            <display:setProperty name="paging.banner.placement" value="bottom" />
				                            <display:setProperty name="paging.banner.onepage" value="" />
				                            <display:column sortable="false" style="text-align:center;font-size:13px;height:30px;width:100px;" title=" S.No " value="${record1_rowNum}"/>
				                            <display:column sortable="false" style="text-align:center;font-size:13px;width:250px;" title="Date">
				                                <a type="button" class="btn btn-sm btn-primary" style="color: #ffffff;" href="#" onclick="fnTab('${productID}', '${record1.entery_date}','pending','0')">${record1.entery_date}</a></display:column>
				                            <display:column sortable="false" style="text-align:center;font-size:13px;width:250px;" title="Number of Policies" property="count_entery_date" />
				                        </display:table>
									</s:else>
								</s:form>
							</div>
				 	</div>
				 	<div id="tabs-2">
							<div id="approvedPolicies">
								<s:form id="approved" name="approved" theme="simple">
									<table width="50%" style="margin: 0 auto;">
										<tr><td colspan="5" align="center"> <s:text name="product.product"/></td><td><s:select name="productID" cssClass="inputSelect" id="productID" list="productDet" headerKey="" headerValue="-Select-" listKey="PRODUCT_ID" listValue="PRODUCT_NAME" onchange="fnTab(this.value, 'approved','','')"/></td></tr>
										<s:if test='(rdate!=null || "".equals(rdate)) && "1".equals(index)'>
										<tr>
											<td colspan="2">Selected Date is <s:property value="rdate"/></td>
										</tr>
										</s:if>
									</table>
									<br/>
									<s:if test='(rdate!=null || "".equals(rdate)) && "1".equals(index)'>
										<display:table name="policyList" pagesize="10" requestURI="/getOCAjaxReferal.action?from1=Normal&agencyCode=%{agencyCode}&borganization=%{borganization}" class="footable" uid="row" id="record2" excludedParams="from1" style="text-align:center;font-size:13px;">
							                 <display:setProperty name="paging.banner.one_item_found" value="" />
							                 <display:setProperty name="paging.banner.one_items_found" value="" />
							                 <display:setProperty name="paging.banner.all_items_found" value="" />
							                 <display:setProperty name="paging.banner.some_items_found" value="" />
							                 <display:setProperty name="paging.banner.placement" value="bottom" />
							                 <display:setProperty name="paging.banner.onepage" value="" />
							                  <%--<display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="Application No" property="APPLICATION_NO"/>--%>
							                 <display:column sortable="false" style="text-align:left;font-size:13px;" title="Broker Organization" property="brokername"/>
							                 <display:column sortable="false" style="text-align:left;font-size:13px;" title="Quote Created By" property="quote_Created"/>
							                 <display:column sortable="false" style="text-align:left;font-size:13px;" title="Customer Name" property="CUST_NAME"/>
							                 <display:column sortable="false" style="text-align:center;font-size:13px;" title="Quote No">
							                     <a type="button" class="btn btn-sm btn-primary" style="color: #ffffff;" href="#" onclick="editQuote('${record2.APPLICATION_NO}', '${session.user}','${productID}','${rdate}',${record2.QUOTE_NO},'${record2.SCHEME_ID}','RA');">${record2.QUOTE_NO}</a>
							                 </display:column>
							                 <display:column sortable="false" style="text-align:left;font-size:13px;" title="Remarks" property="REMARKS"/>
							                 <display:column sortable="false" style="text-align:left;font-size:13px;" title="Status" property="STATUS"/>
							             </display:table>
									</s:if>
									<s:else>
										<display:table name="occListA" pagesize="10" requestURI="/getOCAjaxReferal.action?from1=Normal&index=1&agencyCode=%{agencyCode}&borganization=%{borganization}" class="footable" uid="row" id="record3" excludedParams="index" style="text-align:center;font-size:13px;">
				                            <display:setProperty name="paging.banner.one_item_found" value="" />
				                            <display:setProperty name="paging.banner.one_items_found" value="" />
				                            <display:setProperty name="paging.banner.all_items_found" value="" />
				                            <display:setProperty name="paging.banner.some_items_found" value="" />
				                            <display:setProperty name="paging.banner.placement" value="bottom" />
				                            <display:setProperty name="paging.banner.onepage" value="" />
				                            <display:column sortable="false" style="text-align:center;font-size:13px;height:30px;width:100px;" title=" S.No " value="${record3_rowNum}"/>
				                            <display:column sortable="false" style="text-align:center;font-size:13px;width:250px;" title="Date">
				                                <a type="button" class="btn btn-sm btn-primary" style="color: #ffffff;" href="#" onclick="fnTab('${productID}', '${record3.entery_date}','approved','0')">${record3.entery_date}</a></display:column>
				                            <display:column sortable="false" style="text-align:center;font-size:13px;width:250px;" title="Number of Policies" property="count_entery_date" />
				                            <display:column sortable="false"  style="text-align:right;font-size:13px;width:250px;" title="Premium(AED)" property="PREMIUM" format="{0,number,0.00}"/>
				                        </display:table>
									</s:else>
								</s:form>
							</div>
				 	</div>
				 	<div id="tabs-3">
							<div id="rejectedPolicies">
								<s:form id="rejected" name="rejected" theme="simple">
									<table width="50%" style="margin: 0 auto;">
										<tr><td align="center"> <s:text name="product.product"/></td> <td><s:select name="productID" cssClass="inputSelect" id="productID" list="productDet" headerKey="" headerValue="-Select-" listKey="PRODUCT_ID" listValue="PRODUCT_NAME" onchange="fnTab(this.value, 'rejected','','')"/></td> </tr>
										<s:if test='(rdate!=null || "".equals(rdate)) && "2".equals(index)'>
										<tr>
											<td colspan="2">Selected Date is <s:property value="rdate"/></td>
										</tr>
										</s:if>
									</table>
									<br/>
									<s:if test='(rdate!=null || "".equals(rdate)) && "2".equals(index)'>
										 <display:table name="policyList" pagesize="10" requestURI="/getOCAjaxReferal.action?from1=Normal&agencyCode=%{agencyCode}&borganization=%{borganization}" class="footable" uid="row" id="record4" excludedParams="from1" style="text-align:center;font-size:13px;">
							                 <display:setProperty name="paging.banner.one_item_found" value="" />
							                 <display:setProperty name="paging.banner.one_items_found" value="" />
							                 <display:setProperty name="paging.banner.all_items_found" value="" />
							                 <display:setProperty name="paging.banner.some_items_found" value="" />
							                 <display:setProperty name="paging.banner.placement" value="bottom" />
							                 <display:setProperty name="paging.banner.onepage" value="" />
							                 <%--<display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="Application No" property="APPLICATION_NO"/>--%>
							                 <display:column sortable="false" style="text-align:left;font-size:13px;" title="Broker Organization" property="brokername"/>
							                 <display:column sortable="false" style="text-align:left;font-size:13px;" title="Quote Created By" property="quote_Created"/>
							                 <display:column sortable="false" style="text-align:left;font-size:13px;" title="Customer Name" property="CUST_NAME"/>
							                 <display:column sortable="false" style="text-align:center;font-size:13px;" title="Quote No">
							                     <a type="button" class="btn btn-sm btn-primary" style="color: #ffffff;" href="#" onclick="editQuote('${record4.APPLICATION_NO}', '${session.user}','${productID}','${rdate}',${record4.QUOTE_NO},'${record4.SCHEME_ID}','RR');">${record4.QUOTE_NO}</a>
							                 </display:column>
							                 <display:column sortable="false" style="text-align:left;font-size:13px;" title="Remarks" property="REMARKS"/>
							                 <display:column sortable="false" style="text-align:left;font-size:13px;" title="Status" property="STATUS"/>
							             </display:table>
									</s:if>
									<s:else>
										<display:table name="occListR" pagesize="10" requestURI="/getOCAjaxReferal.action?from1=Normal&index=2&agencyCode=%{agencyCode}&borganization=%{borganization}" class="footable" uid="row" id="record5" excludedParams="index" style="text-align:center;font-size:13px;">
				                            <display:setProperty name="paging.banner.one_item_found" value="" />
				                            <display:setProperty name="paging.banner.one_items_found" value="" />
				                            <display:setProperty name="paging.banner.all_items_found" value="" />
				                            <display:setProperty name="paging.banner.some_items_found" value="" />
				                            <display:setProperty name="paging.banner.placement" value="bottom" />
				                            <display:setProperty name="paging.banner.onepage" value="" />
				                            <display:column sortable="false" style="text-align:center;font-size:13px;height:30px;width:100px;" title=" S.No " value="${record5_rowNum}"/>
				                            <display:column sortable="false" style="text-align:center;font-size:13px;width:250px;" title="Date">
				                                <a type="button" class="btn btn-sm btn-primary" style="color: #ffffff;" href="#" onclick="fnTab('${productID}', '${record5.entery_date}','rejected','0')">${record5.entery_date}</a></display:column>
				                            <display:column sortable="false" style="text-align:center;font-size:13px;width:250px;" title="Number of Policies" property="count_entery_date" />
				                        </display:table>
									</s:else>
									<s:hidden name="borganization" id="borganization"/>
									<s:hidden name="agencyCode" id="agencyCode"/>
									<s:hidden name="mode1"/>
									<s:hidden name="login_Id" />
								</s:form>
							</div>
				 	</div>
				</sj:tabbedpanel>
			</div>
		</div>	    	
	    <s:form name="form1" id="form1" theme="simple">
	    	<s:hidden name="agencyCode" id="agencyCode1"/>
	    	<s:hidden name="borganization" id="borganization1"/>
			<s:hidden name="quoteStatus" id="quoteStatus"/>
			<s:hidden name="applicationNo" id="applicationNo"/>
			<s:hidden name="loginId" id="loginId"/>
			<s:hidden name="quoteNo" id="quoteNo"/>
			<s:hidden name="productId" id="productId"/>
			<s:hidden name="entryDate" id="entryDate"/>
			<s:hidden name="pid" id="pid"/>
			<s:hidden name="reqFrom" id="reqFrom"/>
			<s:hidden name="schemeId" id="schemeId"/>
	    </s:form>
	</body>
</html>
