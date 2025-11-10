<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="header.jsp" %>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="../css/style.css" rel="stylesheet" type="text/css">
<style type="text/css">

</style>
</head>
<br/><br/>
<%@ page language="java" import="java.text.SimpleDateFormat,com.maan.admin.DAO.MotorBodyCreation"%>
<form name="form1" method="post" action="../servlet/MotorBodyController">
<table width="90%"  border="0" cellspacing="0" cellpadding="0">
<tr> <td align="center" ></td> <tr>
<td width="1"></td> <td width="90%" class="heading"><strong>MOTOR TARIFF CONFIGURATION </strong></td>
</tr> <tr align="center"> <td colspan="3">&nbsp;</td> </tr> <tr align="center"> <td colspan="3">
<table width="90%" border="0" align="center" cellspacing='1'> 
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
				String branchCode=(String)session.getAttribute("LoginBranchCode");				
				String makeName = request.getAttribute("makeName1")==null?"":(String)request.getAttribute("makeName1");
              	String makeType= request.getAttribute("MakeType")==null?"":(String)request.getAttribute("MakeType");
              	String makeId3= request.getAttribute("makeId")==null?"":(String)request.getAttribute("makeId");
               System.out.println("rrrrrrrrrrrrrr"+makeName); 
              ArrayList modelEditList=request.getAttribute("modelEditList")==null?new ArrayList():(ArrayList)request.getAttribute("modelEditList");
             System.out.println("In JSP Size Of List is "+modelEditList.size());
 %>

	<tr>
	<td colspan="6" height="15" align="center">
	<font color="red"><%=(request.getAttribute("error")==null?"":((StringBuffer)request.getAttribute("error")).toString()) %></font></td>
	</tr>
    <tr> 
	<td colspan="6" height="15" class = "heading"> 
	<div align="center" ><B>Model Configuration</div>	</td> 
    </tr>
    <tr> 
	<td colspan="6" height="15" class = "heading"> 
	<div align="left" ><B>Make Type :<%=makeType %></div>	</td> 
    </tr>
 
							
				 <table width="90%" border="0" align="center" cellspacing='1'>  
				 <tr height="10"></tr>
				<tr>
				<td colspan="5">
			 	               		
			 	                      <display:table name="modelEditList" pagesize="10"
													requestURI="/servlet/MotorBodyController" class="table" uid="row"
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
													<display:column style="text-align:center;" sortable="true" title="Model ID" property="modelId" class="formtxtc"/>
													
													<display:column style="text-align:center;" sortable="true" title="Model Name"
														property="modelName" class="formtxtc"/>
													<display:column sortable="true" style="text-align:left;"
														title="Model Name Arabic" property="modelArabic" class="formtxtc"/>
													<display:column style="text-align:center;" sortable="true" title="Core Code"
														property="coreCode" class="formtxtc"/>
													<display:column style="text-align:center;" sortable="true" title="Status"
														property="status" class="formtxtc"/>
													<display:column style="text-align:center;" sortable="true" title="Edit"class="formtxtc">
												
														<a href="#" onclick="return edit('<jsp:getProperty name="record" property="makeId"/>','<jsp:getProperty name="record" property="modelId"/>')">
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
									<td>
										<table border="0" align="center" cellpadding="0"
											cellspacing="0">
											<tr>
												<td valign="middle">
													<input name="image" type="image" src= "../images/Back.jpg" onclick="goback()" />
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
							
</tr>	</table>		

                            <input type="hidden" name="productId" value="<%=session.getAttribute("productid")==null?"65":session.getAttribute("productid") %>">
							<input type="hidden" name="branchCode" value="<%=session.getAttribute("LoginBranchCode")%>">  
							<input type="hidden" name="mode" value="">
							<input type="hidden" name="makeName" value="<%=makeName %>">
							<input type="hidden" name="makeType" value="<%=makeType %>">
							
							<input type="hidden" id="makeID" name="makeID" value="<%=request.getAttribute("makeId")==null?"":request.getAttribute("makeId") %>">
							<input type="hidden" id="modelId" name="modelId" value="">
							<input type="hidden" name="requestFrom" value="makeconfig">
							
											
</form>
<script>
function edit(values,values1)
{
document.getElementById("makeID").value=values;
document.getElementById("modelId").value=values1;
document.form1.mode.value="Edit";
document.form1.requestFrom.value="editmodelconfig";
document.form1.action="../servlet/MotorBodyController";
document.form1.submit();
return false;
}
function goback()
{
document.form1.action="../servlet/MotorBodyController?requestFrom=modelconfig&val=config";
document.form1.submit();
return false;
}
function addmore()
{
document.form1.action="../servlet/MotorBodyController?requestFrom=editmodelconfig&mode=new";
document.form1.submit();
return false;
}
</script>
</html>				
