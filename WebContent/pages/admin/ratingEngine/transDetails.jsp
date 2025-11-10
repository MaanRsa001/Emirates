<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<html>
	<head>
		<script language=Javascript>
	       function isNumberKey(evt)  {
	          var charCode = (evt.which) ? evt.which : event.keyCode;
	          if (charCode != 46 && charCode > 31 
	            && (charCode < 48 || charCode > 57))
	             return false;
	          return true;
	       }
			function stopRKey(evt) { 
			  var evt = (evt) ? evt : ((event) ? event : null); 
			  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
			  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
			} 
			document.onkeypress = stopRKey; 
			
			function resetSearch() {
				window.location.href = "${pageContext.request.contextPath}/listPolicy.do";
				return false;
			}
		
			function goSearch(form){
				form.action = "${pageContext.request.contextPath}/listPolicy.do";
				form.submit();
				return false;
			}
			
			function uploadDoc(form){
				form.action = "${pageContext.request.contextPath}/uploadRating.do";
				form.submit();
				return false;
			}
			
			function loaderImage() {
				document.getElementById('loaderImage').style.display="block";
			}
			
			function downloadFile(tranId,val) {
				document.getElementById("tranId").value=tranId;
				document.getElementById("downloadType").value=val;
				document.forms[0].action="${pageContext.request.contextPath}/downloadTransRating.do";
				document.forms[0].submit();
			}
			function transHome() {
				document.getElementById("tranId").value='';
				document.getElementById("reqFrom").value='';
				document.forms[0].action="${pageContext.request.contextPath}/uploadRating.do";
				document.forms[0].submit();
			}
			
			function transDetails() {
				document.forms[0].action="${pageContext.request.contextPath}/transDetailsRating.do";
				document.forms[0].submit();
			}
			
		</script>
		<script type="text/javascript" src="pages/admin/ratingEngine/menu.js"></script>
	</head>
	<body>
    <div style="margin:10px 0;"></div>
    <div class="easyui-layout" style="height:100vh;">
       <div data-options="region:'west',split:true" title="Options" style="width:150px;">
            <div class="easyui-accordion" data-options="fit:true,border:false">
               <%@ include file="/pages/admin/ratingEngine/ratingEngineMenu.jsp" %>
            </div>
        </div>
        <div data-options="region:'center',title:'',iconCls:''">
            <div class="easyui-tabs" data-options="fit:true,border:false,plain:true" id="mainTab">
                <div title="${title}" style="padding:5px">
                <s:if test='"TransHome".equals(display)'>
					<s:form  name="info"  action="transDetailsRating" method="post" theme="simple" id="info" >
						<table width="100%">
                          <tr>
						    <td height="5"></td>
						  </tr>
						  <tr>
						    <td><table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#E5E5E5">
						      <tr>
						        <td bgcolor="#FFFFFF" style="padding:10px; background:#F8F8F8">
						        
						            <table width="100%" border="0" cellspacing="0" cellpadding="0">
						            	<tr>
							       	   		<td  style="color:red;"><s:actionerror/></td>
							       		</tr>
										<tr> <td>&nbsp;&nbsp;</td> </tr>
										<tr><td class="heading">${title}</td></tr>
										<tr><td>&nbsp;</td></tr>
										<tr align="center">
											<td bgcolor="#FFFFFF">
												<table width="100%" border="0" cellspacing="0" cellpadding="0">
													<tr>
														<td class="bg">
														  <table width="100%" border="0" align="center" cellpadding="4" cellspacing="0">
															
															<tr>
																<td>&nbsp;</td>
																<td> <s:text name="Start Date"/><font color="red">*</font> </td>
																<td> <s:textfield name="startDate" id="startDate" cssClass="tcal" /> </td>
																<td>&nbsp;</td>
															</tr>
															<tr>
																<td>&nbsp;</td>
																<td> <s:text name="End Date"/><font color="red">*</font> </td>
																<td> <s:textfield name="endDate" id="endDate" cssClass="tcal" /> </td>
																<td>&nbsp;</td>
															</tr>
															<tr> <td colspan="4" align="center"><input type="button" onclick="transHome()" value="Back" class="btn"/> &nbsp;&nbsp;&nbsp;<input type="button" onclick="transDetails()"  value="Submit" class="btn"/> </td>
															</tr>
															<tr> <td colspan="4" align="center">
														 </table></td>
														 </tr>
														</table>
													  </td>
													</tr>
												</table>
											</tr>
										</table>
								   		<s:hidden name="menuType"/>
								   		<s:hidden name="tranId" id="tranId"/>
								   		<s:hidden name="reqFrom" value="%{menuType}" id="reqFrom"/>
									</td>
								</tr>
							</table>
				    </s:form>
				</s:if>
				<s:elseif test="display=='result'">
					<s:form name="info" action="redirectDoUpload" method="post" theme="simple">
						<%--<table width="100%" align="center" class="footable">
							<thead>
							<tr>
								<th colspan="6" nowrap="nowrap">
									<s:text name="label.trans.title" />
								</th>
							</tr>
							<tr>
								<th width="25%">
									<s:text name="label.startDate" />
								</th>
								<th width="25%">
									<s:property value="startDate" />
								</th>
								<th width="25%">
									<s:text name="label.endDate" />
								</th>
								<th width="25%">
									<s:property value="endDate" />
								</th>
							</tr>
							</thead>			
							<tr height="10">
								<td></td>
							</tr>
						</table> --%>
						<table width="100%" align="center">
								<tr>
									<td align="center" width="100%">
										<display:table name="transDetList" pagesize="10" cellpadding="1" cellspacing="1" requestURI="transDetailsUpload.do" class="table" uid="row" id="record" export="true">
											<display:setProperty name="basic.msg.empty_list" value="<font color='red'>No Recods Found</font>" />
											<display:setProperty name="paging.banner.one_item_found" value="" />
											<display:setProperty name="paging.banner.one_items_found" value="" />
											<display:setProperty name="paging.banner.all_items_found" value="" />
											<display:setProperty name="paging.banner.some_items_found" value="" />
											<display:setProperty name="paging.banner.placement" value="bottom" />
											<display:setProperty name="paging.banner.onepage" value="" />
											<display:setProperty name="export.types" value="excel pdf" />
											<display:setProperty name="export.pdf" value="true" />
											<display:setProperty name="export.csv" value="false" />
											<display:setProperty name="export.xml" value="false" />
											<display:setProperty name="export.pdf.filename" value="TransactionDetails.pdf" />
											<display:setProperty name="export.excel.filename" value="reports.xls" />
				
											<display:column sortable="true" style="text-align:left;font-size:15px;" title="Transaction Id" property="TRAN_ID" />
											<display:column sortable="true" style="text-align:left;font-size:15px" title="Uploaded Date" property="TRANSACTION_DATE" />
											<display:column sortable="true" style="text-align:left;font-size:15px" title="User Name" property="USER_NAME" />
											<display:column sortable="true" style="text-align:left;font-size:15px" title="File Name" property="FILE_NAME" />
											<display:column sortable="true" style="text-align:center;font-size:15px" title="Total Records" property="TOTAL_RECORDS" />
											<display:column sortable="true" style="text-align:center;font-size:15px" title="Valid Records">
												<a style="color: blue;font-weight: bold; text-decoration:underline;cursor:pointer;" onclick="downloadFile('${record.TRAN_ID}','V')" title="Download">${record.VALID_RECORDS}</a>
											</display:column>
											<display:column sortable="true" style="text-align:center;font-size:15px" title="Error Records">
												<a style="color: blue;font-weight: bold; text-decoration:underline;cursor:pointer;" onclick="downloadFile('${record.TRAN_ID}','E')" title="Download">${record.ERROR_RECORDS}</a>
											</display:column>
										</display:table>
										<s:hidden name="tranId" id="tranId"/>
										<s:hidden name="downloadType" id="downloadType"/>
										<s:hidden name="typeId" id="typeId"/>
										<s:hidden name="menuType"/>
								   		<s:hidden name="reqFrom" value="%{menuType}" id="reqFrom"/>
									</td>
								</tr>
								<tr height="20">
									<td></td>
								</tr>
								<tr>
									<td align="center">
										<input type="button" name="dsfdsg" onclick="transHome();" class="btn" value="Back" />
									</td>
								</tr>
							</table>
						</s:form>
					</s:elseif>
                </div>
            </div>
        </div>
    </div>
</body>
</html>

