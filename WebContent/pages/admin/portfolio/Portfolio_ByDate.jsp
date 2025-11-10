<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
<script>
function fnBack()
{

document.form1.action="checkPortfolio.action";
document.form1.submit();
}
</script>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.6.min.js"></script>
		<script type="text/javascript" src="pages/admin/jquery.reveal.js"></script>
		<link rel="stylesheet" href="pages/admin/reveal.css">	
		<style type="text/css">
			body { font-family: "HelveticaNeue","Helvetica-Neue", "Helvetica", "Arial", sans-serif; }
			.big-link { text-align: center; color: #06f; }
		</style>

	</head>
	<body>
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0"> 
			<tr>
		    	<td height="5"></td>
		  	</tr>
		  	<tr>
		    	<td><table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#E5E5E5">
		      		<tr>
		        		<td bgcolor="#FFFFFF" style="padding:10px; background:#F8F8F8">
								<s:form name="form1" theme="simple">
							 		<table width="100%" align="center">
								        <tr>
								            <td class="heading" width="100%"><s:text name="portfolio.view"/></td>
								        </tr>
								 		<tr>
								        	<td height="10%">
								        		
								        	</td>
							        	</tr>
							      		<tr>
							      			<td>
										      	<table>
										      	<!--<display:table name="brokerList" pagesize="10" requestURI="/getABListBrokerMgm.action" class="table" uid="row" id="record">
												<display:setProperty name="paging.banner.one_item_found" value="" />
												<display:setProperty name="paging.banner.one_items_found" value="" />
												<display:setProperty name="paging.banner.all_items_found" value="" />
												<display:setProperty name="paging.banner.some_items_found" value="" />
												<display:setProperty name="paging.banner.placement"	value="bottom" />
												<display:setProperty name="paging.banner.onepage" value="" />
												<display:column sortable="true" style="text-align:left;font-size:13px;height:30px;" title=" S.NO " value="${record_rowNum}"/>
												
												<display:column sortable="true" style="text-align:left;font-size:13px;"  title="DATE" property="missippi_id" />
												<display:column sortable="true" style="text-align:left;font-size:13px;" title="NUMBER OF QUOTES" property="LOGIN_ID" />
												
												</display:table>
												-->
										      	</table>
											</td>
										</tr>
										<tr>
										<td>
										<table width="80%" align="center" border="1">
										
										<tr>
										<th>S.NO</th>
										<th>Broker Organization</th>
										<th>Quote Created By</th>
										<th>Quote No</th>
										<th>Customer Name</th>
										<th>Premium(AED)</th>
										<th>View Draft</th>
										<th></th>
										</tr>
										<tr>
										<td>1</td>
										<td>BROKERMAAN</td>
										<td>BROKERMAAN</td>
										<td>51905</td>
										<td>WEBSERVICE CUSTOMER</td>
										<td>2,319.00</td>
										<td><a href="" class="big-link" data-reveal-id="myModal" data-animation="fade">Draft</a></td>
										<td><a href="#" class="two" title="View Policy Schedule" onclick="return viewPolicys('H5C02501360-0-5','brokermaan1','schedule','11','H5C02501360-0')"><b> View </b></a></td>
										
										</tr>
										
										<s:hidden name="policyMode" value="draft"/>
										<s:hidden name="policynumber" value="1105009"/>
										<s:hidden name="loginid" value="brokermaan1"/>
										<s:hidden name="productTypeIds" value="11"/>
										<s:hidden name="openCoverNoSettingCert" value="H5C02501260-0"/>
										</table></td>
										</tr>
										<tr align="center"><td class="text"><input name="back" type="button" class="btn" onclick="fnBack()" value="Back" style ="cursor:hand"/></td></tr>
									</table>
								</s:form>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<div id="myModal" class="reveal-modal">
			<h1 align="center">Printing Options</h1>
			<table border="0" align="center">
				<tr>				
					<td><a href="information.jsp" target="#" class="big-link" >Debit Note</a></td>
					<td><a href="information.jsp" target="#" class="big-link" >Credit Note</a></td>
					<td><a href="information.jsp" target="#" class="big-link" >Original</a></td>
					<td><a href="information.jsp" target="#" class="big-link" >Original Copy</a></td>
					<td><a href="information.jsp" target="#" class="big-link" >Copy</a></td>
					<td><a href="information.jsp" target="#" class="big-link" >Delete</a></td>
					<td><a href="information.jsp" target="#" class="big-link" >Authorization</a></td>
				</tr>
			</table>
			<a class="close-reveal-modal">&#215;</a>
		</div>
	<div id="myModal1" class="reveal-modal">
	</div>
	<script>
function viewPolicys(policynumber,loginid,policyModee,productId,openNo)
{
		 document.form1.policynumber.value=policynumber;
		 document.form1.loginid.value=loginid;
		 document.form1.policyMode.value=policyModee;

	var URL ="/ecargostruts/Copy of information Admin.jsp?policyMode="+policyModee+"&policynumber="+policynumber+"&loginid="+loginid+"&productTypeIds="+productId+"&openCoverNoSettingCert="+openNo;

		 //alert(URL);
		var windowName = "PolicyView";
		var width  = screen.availWidth;
		var height = screen.availHeight;
		var w = 700;
		var h =	450;
		var features =
			'width='          + w +
			',height='		  + h +
			',left='		  + ((width - w - 10) * .5)  +
			',top='			  + ((height - h - 30) * .5) +
			',directories=no' +
			',location=no'	  +
			',menubar=no'	  +
			',scrollbars=yes' +
			',status=yes'	  +
			',toolbar=no'	  +
			',resizable=false';
		var strOpen = window.open (URL, windowName, features);
		strOpen.focus();
		return false;
}

</script>

	</body>
</html>



 
