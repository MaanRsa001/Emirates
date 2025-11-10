<%@ page isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<script language="JavaScript">javascript:window.history.forward(1);</script>
		<script>
			function stopRKey(evt) { 
			  var evt = (evt) ? evt : ((event) ? event : null); 
			  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
			  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
			} 
			document.onkeypress = stopRKey;
		
			function fnTab(val, reqFrom, value, index){
				if(reqFrom=="portfo"){
					postRequest('${pageContext.request.contextPath}/getPortfolioOC.action?broker='+val+'&reqFrom='+reqFrom+'&index=2&from1=ajax', reqFrom);
		   		}
			}
			function fnCall(from){
				if(from=='edit')
					document.info.action = from+"BrokerMgm.action?mode=edit";
				else if(from=='userDetail')
					document.info.action = "getABListUserMgm.action?mode1=broker";
				else if(from=='customerDetail')
					document.info.action = "getABListCustomerMgm.action?mode1=broker";
				else if(from=='referal')
					document.info.action = "getOCListReferal.action";
				else if(from=='openCover')
					document.info.action = "opencoverOC.action";
				else
					document.info.action = from+"BrokerMgm.action";
				document.info.submit();
			}
		   $(function(){
				var index = '<s:property value="index"/>';
				var t = $('#tabs');
				var tabs = t.tabs('tabs');
					t.tabs('select', tabs[index].panel('options').title);
			});
		 </script>
	</head>
	<body>
		<div style="margin:10px 0;"></div>
		    <div class="easyui-layout" style="height:100vh;">
		       <div data-options="region:'west',split:true" title="Options" style="width:150px;">
		            <div class="easyui-accordion" data-options="fit:true,border:false">
		                <input type="button" class="btntab" value="View" onclick="fnCall('view')"/><br/>
		                 <input type="button" class="btntab" value="Edit" onclick="fnCall('edit')"/><br/>
		                <input type="button" class="btntab" value="Change Password" onclick="fnCall('changePwd')"/><br/>
		                <input type="button" class="btntab" value="User Details" onclick="fnCall('userDetail')"/><br/>
		                <input type="button" class="btntab" value="Customer Detail" onclick="fnCall('customerDetail')"/><br/>
		                <input type="button" class="btntab" value="Referral" onclick="fnCall('referal')"/><br/>
		                <input type="button" class="btntab" value="Statistics" onclick="fnCall('statistics')"/><br/>
		            </div>
		        </div>
   		 		<div data-options="region:'center',title:'<s:property value="borganization"/>(<s:property value="agencyCode"/>)',iconCls:'icon-ok'">
   		 		  	<div class="easyui-tabs" data-options="fit:true,border:false,plain:true" id="tabs">
		                <div title="Open Cover" style="padding:10px" >
							<table width="100%">
	                          <tr>
							    <td height="5"></td>
							  </tr>
							  <tr>
							    <td><table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#E5E5E5">
							      <tr>
							        <td bgcolor="#FFFFFF" style="padding:10px; background:#F8F8F8">
				   					<s:form id="info" name="info" theme="simple">
								  		<table width="100%" align="center" width="80%">
											<tr><td></td></tr>
											<tr><td>
												<div id="portfo">
													<display:table name="portfolioList" pagesize="10" requestURI="" class="table" uid="row" id="record" excludedParams="from1">
															<display:setProperty name="paging.banner.one_item_found" value="" />
															<display:setProperty name="paging.banner.one_items_found" value="" />
															<display:setProperty name="paging.banner.all_items_found" value="" />
															<display:setProperty name="paging.banner.some_items_found" value="" />
															<display:setProperty name="paging.banner.placement"	value="bottom" />
															<display:setProperty name="paging.banner.onepage" value="" />
															<display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="Core Application Policy No" property="MISSIPPI_OPENCOVER_NO"/>
															<display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="Proposal No" property="PROPOSAL_NO"/>
															<display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="Customer Name" property="CUSTOMER_NAME"/>
															<display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="Policy Start Date" property="START_DATE"/>
															<display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="Policy End date" property="END_DATE"/>
															<display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="Schedule">
																<a href="#" onclick="fndoc('${record.MISSIPPI_OPENCOVER_NO}', '${session.user}', 'schedule', '${record.MISSIPPI_OPENCOVER_NO}', '', '${record.proposal_no}', 'false')">Schedule</a>
															</display:column>
															<display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title="LC Details">
																<a href="#" onclick="fnlcdetail('${record.MISSIPPI_OPENCOVER_NO}', '${record.PROPOSAL_NO}', '${session.user}')">LC Details</a>
															</display:column>
													</display:table>
												</div>
											</td></tr>
			    						</table>
			    						<s:hidden name="agencyCode"/>
										<s:hidden name="borganization"/>
										<s:hidden name="bcode"/>
										<s:hidden name="firstname"/>
			    					</s:form>
									</td>
								</tr>
							</table></td></tr>
	                    </table>
		          </div>
	        </div>
	    </div>
	    <script>
		    function fndoc(policynumber,loginId,docType,docNo,amendId,proposalNo,endtstatus){
		     	var URL ="${pageContext.request.contextPath}/scheduleOC.action?docType="+docType+"&policynumber="+policynumber+"&loginId="+loginId+"&docNo="+docNo+"&amendId="+amendId+"&proposalNo="+proposalNo+"&endtstatus="+endtstatus;
		     	 popUp(URL, 800, 800);
		     }
		    function fnlcdetail(policynumber, proposalNo ,loginId){
		     	var URL ="${pageContext.request.contextPath}/lcDetailOC.action?policynumber="+policynumber+"&loginId="+loginId+"&proposalNo="+proposalNo;
		     	 popUp(URL, 800, 500);
		     }
	     </script>
	</body>
</html>