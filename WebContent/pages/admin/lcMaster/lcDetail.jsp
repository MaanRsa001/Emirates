<%@ page isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<script>
			function stopRKey(evt) { 
			  var evt = (evt) ? evt : ((event) ? event : null); 
			  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
			  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
			} 
			document.onkeypress = stopRKey;
			
			function forward(from,openCover, lcId, lcNumber, action){
				conf=true;
				document.info.openCover.value=openCover;
				document.info.lcNum.value=lcNumber;
				document.info.lcId.value=lcId;
				document.info.from.value=from; 
				if(from=='view')
				document.info.bank.value=lcNumber;
				if(from=='delete'){
					conf=confirm('Are you want to delete LC Number of '+lcNumber+"?");
				}
				if(conf){
					document.info.action=action+".action";
					document.info.submit();
				}
			}
			
			function fnBack(value){
				document.info.action=value;
				document.info.submit();
				}
		</script>
	</head>
	<body>
		<table width="100%">
          <tr><td height="5"></td></tr>
		  <tr>
		    <td><table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#E5E5E5">
		      <tr>
		        <td bgcolor="#FFFFFF" style="padding:10px; background:#F8F8F8">
  					<s:form id="info" name="info" theme="simple">
  					<s:if test='"useamtView".equals(display)'>
  					<table width="100%" align="center" width="80%">
			  			<tr><td></td></tr>
						<tr><td class="heading" width="100%"><s:text name="LC Policy Report	"/></td></tr>
							<tr><td></td></tr>
							<tr><td align="center"><s:text name="user.broker"/>&nbsp; : &nbsp;<b><s:property value="brokerName"/></b></td></tr>
						 	<tr><td colspan="6">Total LC Amount : &nbsp;&nbsp;<s:property value="lcAmt"/></td></tr>
							<tr><td>
									<display:table name="lcList" pagesize="20" requestURI="useAmtDetailsLC.action" class="table" uid="row" id="record">
										<s:set var="myrow" value="#attr.record"/>
										<display:setProperty name="paging.banner.one_item_found" value="" />
										<display:setProperty name="paging.banner.one_items_found" value="" />
										<display:setProperty name="paging.banner.all_items_found" value="" />
										<display:setProperty name="paging.banner.some_items_found" value="" />
										<display:setProperty name="paging.banner.placement"	value="bottom" />
										<display:setProperty name="paging.banner.onepage" value="" />
										<s:set var="myrow" value="#attr.record"/>
										<s:set var="totalAmt" value="lcAmt"/>
										<display:column sortable="false" style="text-align:left;font-size:13px;height:30px;" title=" S.No " value="${record_rowNum}"/>
										<display:column sortable="false" style="text-align:left;font-size:13px;height:30px;" title="Core Application Policy No" property="POLICY_NO"/>
										<display:column sortable="false" style="text-align:left;font-size:13px;height:30px;" title="Customer Name" property="NAME"/>
										<display:column sortable="false" style="text-align:left;font-size:13px;height:30px;" title="Policy Issue Date" property="POLICY_ISSUE_DATE"/>
										<display:column sortable="false" style="text-align:left;font-size:13px;height:30px;" title="Policy Start Date" property="POLICY_START_DATE"/>
										<display:column sortable="false" style="text-align:center;font-size:13px;height:30px;" title="Sum Insured" property="SUM_INSURED"/>														
										<display:column sortable="false" style="text-align:center;font-size:13px;height:30px;" title="Running Balance ">
												 <s:set var="totalAmt" value="%{@java.lang.Double@parseDouble(totalAmt)-@java.lang.Double@parseDouble(#myrow.SUM_INSURED)}"/>
												 <s:property value="totalAmt"/>
										 </display:column>	
									 </display:table>
							</td></tr>
							<tr><td></td></tr>
							<tr><td align="center">
<%-- 							<s:submit name="back" cssClass="btn" value="Back" action="lcDetailsLC"/> --%>
								<input type="button" value="Back" onclick="fnBack('lcDetailsLC.action');" class="btn"/>
							</td></tr>
							<tr><td></td></tr>
							</table>
						</s:if>
						<s:else>
			  		<table width="100%" align="center" width="80%">
			  			<tr><td></td></tr>
						<tr><td class="heading" width="100%"><s:text name="label.lc.detail"/></td></tr>
						<tr><td></td></tr>
						<tr><td><table width="90%" align="center">
									<tr><td width="30%" align="center"><s:text name="label.cims.policy"/>&nbsp; : &nbsp;<b><s:property value="openCover"/></b></td>
										<td width="35%" align="center"><s:text name="user.broker"/>&nbsp; : &nbsp;<b><s:property value="brokerName"/></b></td>
										<td width="35%" align="center"><s:text name="label.quote.customer"/>&nbsp; : &nbsp;<b><s:property value="custName"/></b></td>
									</tr>
								</table>
						</td></tr>
						<s:if test='"admin".equalsIgnoreCase(#session.usertype) || "RSAIssuer".equalsIgnoreCase(#session.usertype)'>
							<tr><td><table width="90%" align="center">
										<tr><td width="90%" align="right">
											<a href="#" onclick="forward('new','${openCover}','','','addLC')">Add New LC Number</a></td>
											<td width="10%">&nbsp;</td>
										</tr>
									</table>
							</td></tr>
						</s:if>
						<tr><td></td></tr>
						<tr><td>
							<div id="lclist">
								<display:table name="lcList" pagesize="10" requestURI="lcDetailsOC.action" class="table" uid="row" id="record" excludedParams="from1">
									<s:set var="myrow" value="#attr.record"/>
									<display:setProperty name="paging.banner.one_item_found" value="" />
									<display:setProperty name="paging.banner.one_items_found" value="" />
									<display:setProperty name="paging.banner.all_items_found" value="" />
									<display:setProperty name="paging.banner.some_items_found" value="" />
									<display:setProperty name="paging.banner.placement"	value="bottom" />
									<display:setProperty name="paging.banner.onepage" value="" />
									
									<display:column sortable="false" style="text-align:left;font-size:13px;height:30px;" title=" S.No " value="${record_rowNum}"/>
									<display:column sortable="false" style="text-align:left;font-size:13px;height:30px;" title="LC NUMBER" property="LC_NUMBER"/>
									<display:column sortable="false" style="text-align:left;font-size:13px;height:30px;" title="BANK NAME" property="bank_name"/>
									<display:column sortable="false" style="text-align:right;font-size:13px;height:30px;" title="LC AMOUNT" property="LC_AMOUNT" format="{0,number,0.00}"/>
									<display:column sortable="false" style="text-align:left;font-size:13px;height:30px;" title="CURRENCY TYPE" property="currency_name"/>
									<display:column sortable="false" style="text-align:left;font-size:13px;height:30px;" title="LC DATE" property="LC_DATE"/>
									<display:column sortable="false" style="text-align:left;font-size:13px;height:30px;" title="EXPIRY DATE" property="EXPIRY_DATE"/>
									<display:column sortable="false" style="text-align:right;font-size:13px;height:30px;" title="Used Amount" format="{0,number,0.00}">
									 	<a href="#" onclick="viewUsedAmt('${openCover}','${record.LC_ID}','${record.BANK_ID}','useAmtDetailsLC','${record.LC_AMOUNT}')">${record.USED_AMT}</a>
									</display:column>
									<s:if test='"admin".equalsIgnoreCase(#session.usertype) || "RSAIssuer".equalsIgnoreCase(#session.usertype)'>
										<display:column sortable="false" style="text-align:center;font-size:13px;height:30px;" title="Edit">
											<a href="#" onclick="forward('edit','${openCover}','${record.LC_ID}','${record.LC_NUMBER}','addLC')">Edit</a>
										</display:column>
										<display:column sortable="false" style="text-align:center;font-size:13px;height:30px;" title="Delete">
											<s:if test='%{0==#myrow.USED_AMT}'>
												<a href="#" onclick="forward('delete','${openCover}','${record.LC_ID}','${record.LC_NUMBER}','lcDeleteLC')">Delete</a>
											</s:if>
										</display:column>
									</s:if>
								</display:table>
							</div>
						</td></tr>
						<tr><td align="center">
<%-- 						<s:submit name="bck" cssClass="btn" value="Back" action="%{from1}LC"/> --%>
						<input type="button" class="btn" value="Back" onclick="fnBack('<s:property value="from1" />LC.action')" />
						</td></tr>
  						</table>
  						</s:else>
  						<s:hidden name="openCover" id="openCover"/>
  						<s:hidden name="brokerName" id="brokerName"/>
  						<s:hidden name="lcNum" id="lcNum"/>
  						<s:hidden name="lcId" id="lcId"/>
  						<s:hidden name="custName" id="custName"/>
  						<s:hidden name="from1" id="from1"/>
  						<s:hidden name="from" id="from"/>
  						<s:hidden name="broker" id="broker"/>
  						<s:hidden name="bank" id="bank"/> 
  						<s:hidden name="lcAmt" id="lcAmt"/>
  					</s:form>
  				</td>
				</tr>
			</table></td></tr>
       </table>
       <script>
       function viewUsedAmt(openCover, lcId,bank, action,lcAmt){
			document.info.openCover.value=openCover;
			document.info.bank.value=bank;
			document.info.lcId.value=lcId;
		    document.info.lcAmt.value=lcAmt; 
		    document.info.action=action+".action?lcAmtc="+lcAmt;
			document.info.submit(); 
	  }
      </script>
	</body>
</html>