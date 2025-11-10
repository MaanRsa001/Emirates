<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<%@ include file="header.jsp" %>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1/">
<link href="../css/style.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color: #FFFFFF;
}
a:hover {
	color: #003366;
	text-decoration: none;
}
a:visited {
	text-decoration: none;
	color: #FFFFFF;
}
a:visited:hover {
	text-decoration: none;
	color: #000000;
}
a:active {
	color: #003366;
	text-decoration: none;

}
-->
</style>
</head>
<br/><br/>
<%@ page language="java" import="java.text.SimpleDateFormat,com.maan.admin.DAO.MotorBodyCreation"%>
<form name="form1" method="post" action="../servlet/MotorBodyController">
<table width="90%"  border="0" cellspacing="0" cellpadding="0">
<tr> <td align="center" ></td> <tr>
<td width="1"></td> <td width="90%" class="heading"><strong>MOTOR TARIFF CONFIGURATION </strong></td>
</tr> <tr align="center"> <td colspan="3">&nbsp;</td> </tr> <tr align="center"> <td colspan="3">
<table width="100%" border="0" align="center" cellspacing='1'> 
<%@ include file="admin_sub_menu_motor_body.jsp" %>

<%
Date date = new Date();
				SimpleDateFormat YearOnly = new SimpleDateFormat("yyyy");
				SimpleDateFormat MonthOnly = new SimpleDateFormat("MM");
				SimpleDateFormat DayOnly = new SimpleDateFormat("dd");
                int previous = Integer.parseInt(YearOnly.format(date)) - 1;
				int after = Integer.parseInt(YearOnly.format(date))+1;
				String day=(DayOnly.format(date));
				String year=(YearOnly.format(date));
				String month=(MonthOnly.format(date));
				MotorBodyCreation broker=request.getAttribute("motorBody")==null?new MotorBodyCreation():(MotorBodyCreation)request.getAttribute("motorBody");
				String branchCode=(String)session.getAttribute("LoginBranchCode");				
                String productId="65";
                //System.out.println("============================>"+request.getParameter("mode"));
 %>

	<tr>
	<td colspan="6" height="15" align="center">
	<font color="red"><%=(request.getAttribute("error")==null?"":((StringBuffer)request.getAttribute("error")).toString()) %></font></td>
	</tr>
    <tr> 
	<td colspan="6" height="15" class = "heading"> 
	<div align="center" ><B>Make Configuration</div>	</td> 
    </tr>
 
							 <%
                              
                // String result[][]=new String [0][0];
				//System.out.println((String)request.getParameter("typeid"));
				//result=broker.getMotorMake(branchCode);
				//System.out.println("output of motorbodylimit"+result.length);
				 %>
				 <table width="80%" border="0" align="center" cellspacing='1'>  
				 <tr height="10"></tr>
				<tr>
				<td colspan="5">
			 	                      <display:table name="makeList" pagesize="10"
													requestURI="/servlet/MotorBodyController?requestFrom=makeconfig" class="table" uid="row"
													id="record" >
													<display:setProperty name="paging.banner.one_item_found"
														value="" />
													<display:setProperty name="paging.banner.one_items_found"
														value="" />
													<display:setProperty name="paging.banner.all_items_found"
														value="" />
													<display:setProperty name="paging.banner.some_items_found"
														value="" />
													<display:setProperty name="paging.banner.placement"
														value="bottom" />
													<display:setProperty name="paging.banner.onepage" value="" />
													<display:column style="text-align:center;" sortable="true" title="Make ID" property="makeId" class="formtxtc"/>
													<display:column style="text-align:center;" sortable="true" title="Make Name"
														property="makeName" class="formtxtc"/>
													<display:column sortable="true" style="text-align:left;"
														title="Make Name Arabic" property="makeArabic" class="formtxtc"/>
													<display:column style="text-align:center;" sortable="true" title="Core Code"
														property="coreCode" class="formtxtc"/>
													<display:column style="text-align:center;" sortable="true" title="Status"
														property="status" class="formtxtc"/>
													<display:column style="text-align:center;" sortable="true" title="Edit" 													 class="formtxtc">
												
														<a href="#" onclick="return edit('<jsp:getProperty name="record" property="makeId"/>')">
														Edit</a>	</display:column> 
														
												</display:table>
				</td>
				</tr>
                 
                 </table>
              <table width="90%" border="0" cellpadding="0" cellspacing="0" align='center'>
					<tr>
						<td valign="top">
							<table border="0" align="center" cellpadding="4" cellspacing="0">
								<tr>
									<td>
										<table border="0" align="center" cellpadding="0"
											cellspacing="0">
											<tr>
												<td class="royamenuhead">
													<a href="#" onclick="addmore()"><img src= "../images/addmore.jpg"/></a>
												</td>
											</tr>
										</table>
									</td>
									</tr>
							</table>
							
</tr>	</table>		

                            <input type="hidden" name="makeconfig" value="insert">
							<input type="hidden" name="productId" value="<%=session.getAttribute("productid")==null?"65":session.getAttribute("productid") %>">
							<input type="hidden" name="branchCode" value="<%=session.getAttribute("LoginBranchCode")%>">  
							<input type="hidden" name="mode" id="mode" >
							<input type="hidden" id="makeid" name="makeid" value="">
							<input type="hidden" name="typeid" value="">
							<input type="hidden" name="requestFrom" value="makeconfig">
							
											
</form>
<script>
function edit(values)
{
document.getElementById("mode").value="Edit";
document.getElementById("makeid").value=values;
document.form1.requestFrom.value="editmakeconfig";
document.form1.action="../servlet/MotorBodyController";
document.form1.submit();
return false;
}
function addmore()
{
//../admin/MotorBody.jsp?mode=new"
document.getElementById("mode").value="Add";
document.form1.requestFrom.value="updateconfig";
document.form1.action="../admin/MakeEdit.jsp";
document.form1.submit();
return false;
}
</script>
</html>				
