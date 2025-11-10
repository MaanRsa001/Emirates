<%@ page pageEncoding="UTF-8" %> <%@ page language="java" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">


<%--<%@page contentType="text/html; charset=iso-8859-1" pageEncoding="iso-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

--%><html>
<%@ include file="header.jsp" %>

 <%@ page language="java" contentType="text/html;charset=UTF-8" %>

<head>
<title>XYZ - Insurance</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
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
<%@ page language="java" import="java.text.SimpleDateFormat,com.maan.admin.DAO.MotorBodyCreation,com.maan.admin.DAO.MakeBean"%>
<form name="form1" method="post" action="../servlet/MotorBodyController?requestFrom=submitModelconfig">
<table width="90%"  border="0" cellspacing="0" cellpadding="0">
<tr> <td align="center" ></td> <tr>
<td width="1"></td> <td width="90%" class="heading"><strong>MOTOR TARIFF CONFIGURATION </strong></td>
</tr> <tr align="center"> <td colspan="3">&nbsp;</td> </tr> <tr align="center"> <td colspan="3">
<table width="90%" border="0" align="center" cellspacing='1'> 
<%@ include file="admin_sub_menu_motor_body.jsp" %>

<%		
				session.setAttribute("doProcess","yes");
				Date date = new Date();
				SimpleDateFormat YearOnly = new SimpleDateFormat("yyyy");
				SimpleDateFormat MonthOnly = new SimpleDateFormat("MM");
				SimpleDateFormat DayOnly = new SimpleDateFormat("dd");
                int previous = Integer.parseInt(YearOnly.format(date)) -1;
				int after = Integer.parseInt(YearOnly.format(date))+1;
				String day=(DayOnly.format(date));
				String year=(YearOnly.format(date));
				String month=(MonthOnly.format(date));
				MakeBean bean=request.getAttribute("makeBean")==null?new MakeBean():(MakeBean)request.getAttribute("makeBean");
				
				String mode=request.getParameter("mode")==null?"":(String)request.getParameter("mode");
				System.out.println("Mode: "+mode);
				
				String makeName=request.getParameter("makeName")==null?"":(String)request.getParameter("makeName");
				String modelname_eng=request.getParameter("modelname_eng")==null?"":(String)request.getParameter("modelname_eng");
				String modelname_arabic=request.getParameter("modelname_arabic")==null?"":(String)request.getParameter("modelname_arabic");
				String corecode=request.getParameter("corecode")==null?"":(String)request.getParameter("corecode");
				String status=request.getParameter("status")==null?"":(String)request.getParameter("status");
				String rstatus=request.getParameter("rstatus")==null?"":(String)request.getParameter("rstatus");
				String makeid=request.getParameter("makeID")==null?"":(String)request.getParameter("makeID");
				String effDay=request.getParameter("effDay")==null?"":(String)request.getParameter("effDay");
				String effMonth=request.getParameter("effMon")==null?"":(String)request.getParameter("effMon");
				String effYear=request.getParameter("effYear")==null?"":(String)request.getParameter("effYear");
				String bodyId=request.getParameter("bodyId")==null?"":(String)request.getParameter("bodyId");
				String modelId = request.getParameter("modelId")==null?"":(String)request.getParameter("modelId");
				String categoryId=request.getParameter("categoryId")==null?"":(String)request.getParameter("categoryId");
				String remarks=request.getParameter("remarks")==null?"":(String)request.getParameter("remarks");
				String[][] model=(String[][])request.getAttribute("bodyId");
				
				String makeType= request.getAttribute("MakeType")==null?"":(String)request.getAttribute("MakeType");
              	if(makeType.equals(null)||makeType.equals(""))
				{
					makeType=request.getParameter("makeType");
				}
              	System.out.println("Body Id IS---->: "+request.getAttribute("bodyId"));
				System.out.println("fsdf: "+model);
				System.out.println("Make Id In JSP: "+makeid);
				
				
				if(request.getAttribute("makeBean")!=null)
				{
					
					modelname_eng=bean.getModelName()==null?"":bean.getModelName();
					modelname_arabic=bean.getModelArabic()==null?"":bean.getModelArabic();
					corecode=bean.getCoreCode()==null?"":bean.getCoreCode();
					status=bean.getStatus()==null?"":bean.getStatus();
					makeid=bean.getMakeId()==null?"":bean.getMakeId();
					effDay=bean.getEffDate()==null?"":bean.getEffDate();
					effMonth=bean.getEffMonth()==null?"":bean.getEffMonth();
					effYear=bean.getEffYear()==null?"":bean.getEffYear();
					bodyId=bean.getBodyId()==null?"":bean.getBodyId();
					categoryId=bean.getCategoryId()==null?"":bean.getCategoryId();
					remarks=bean.getRemarks()==null?"":bean.getRemarks();
					modelId = bean.getModelId()==null?"":bean.getModelId();
					rstatus=bean.getReferalStatus()==null?"":bean.getReferalStatus();
					
					
				}
				System.out.println("Make Id In JSP: "+makeid);
					System.out.println("Refral Status Is......................"+rstatus);
				
				String branchCode=(String)session.getAttribute("LoginBranchCode");	
				System.out.println("inside jsp"+bean.getMakeName());
				System.out.println("inside jsp"+bean.getMakeArabic());	
				System.out.println("inside jsp"+bean.getCoreCode());	
				System.out.println("inside jsp"+bean.getEffDate()+bean.getEffMonth()+bean.getEffYear());	
				System.out.println("inside jsp"+bean.getStatus());				
                String productId="65";
                //System.out.println("============================>"+request.getParameter("mode"));
 %>

	<tr>
	<td colspan="6" height="15" align="center">
	<font color="red"><%=(request.getAttribute("error")==null?"":((StringBuffer)request.getAttribute("error")).toString()) %></font></td>
	</tr>
    <tr> 
	<td colspan="6" height="15" class = "heading"> 
	<div align="center" ><B>Model Configuration</div>	</td> 
    </tr>
    
    						
								
				<tr class="formtxtr">
				<td  width="194" align="left" class="text">Make Type :<FONT color=red></FONT>&nbsp;&nbsp;</td>
				<td class="text" align="left"><%=makeType %></textarea></td>
				</tr>
				<tr class="formtxtr">
				<td  width="194" align="left" class="text">Model Name In English<FONT color=red>**</FONT>&nbsp;&nbsp;:</td>
				<td class="text" align="left"><textarea rows="2" cols="25" name="modelname_eng" /><%=modelname_eng %></textarea></td>
				</tr>
				<tr class="formtxtr">
				<td  width="194" align="left" class="text">Model Name In Arabic&nbsp;&nbsp;:</td>
				<td class="text" align="left"><textarea rows="2" cols="25" name=modelname_arabic ><%=modelname_arabic %></textarea></td>
				</tr>
			
			 	<tr class="formtxtr">			
				<td class="formtxtf" align="center">
							<STRONG>Type of Body&nbsp;:</STRONG>
						</td>
						<td class="formtxtf">	<SELECT id="bodyId" name="bodyId" class="form">
							 <%for(int i=0;i<model.length;i++){
							 	String selected = "";
							 	if(bodyId.equalsIgnoreCase(model[i][0])){
							 		selected = "selected='selected'";
							 	}
							 %>
								 <OPTION value='<%=model[i][0]%>' <%=selected%>><%=model[i][1]%></OPTION>
							<%}%>
								</SELECT>
					  </td>				
					  </tr>
				<tr class="formtxtr">			
				<td class = "text"  width="194" align="left" >Core App Code<FONT color=red>**</FONT>&nbsp;&nbsp;:</td>
				<td class = "text" align="left"><input type="text" class="fde1" name="corecode"  value="<%=corecode%>" maxlength="8"/></td>
				<tr class="formtxtr">			
				<td class = "text"  width="194" align="left">Category Id<FONT color=red>**</FONT>&nbsp;&nbsp;:</td>
				<td class = "text" align="left"><input type="text" class="fde1" name="categoryId"  value="<%=categoryId%>" maxlength="8"/></td>
				</tr>
				<tr class="formtxtr">
						<td  class="text" width = "194" align="left">
							Effective Date&nbsp;&nbsp;:
						</td>
						<td align="left" class="text" >
							<SELECT name="effDay" class="fde1">
								<OPTION value=''>
									DD
								</OPTION>
								<%
								for (int i = 1; i <=31; i++) {
								%>
								<option value=<%=i%> <%=i==Integer.parseInt(effDay.length()==0?day:effDay)?"selected":""%>>
									
										
									<%=i%>
									<%} %>
								</option>
							</SELECT>
							
							<% String[] months = { "Jan", "Feb", "Mar", "Apr", "May", "Jun",
											"Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };%>
							<SELECT name="effMon" class="form">
								<OPTION value=''>
									MON
								</OPTION>
								<%		
									for (int i = 1; i <= months.length; i++) {
								%>
								<option value=<%=i%> <%=i==Integer.parseInt(effMonth.length()==0?month:effMonth)?"selected":""%>>
									<%=months[i - 1]%>	
								</option>
								<%
								}
								%>
							</SELECT>
							
							
							<SELECT name="effYear" class="form">
								<OPTION value=''>
									YYYY
								</OPTION>
								<%
								
									for (int i = previous; i <= after; i++) {
								%>
								<option value=<%=i%> <%=i==Integer.parseInt(effYear.length()==0?year:effYear)?"selected":"" %>>
									<%=i%>
								</option>
								<%
								}
								%>
							</SELECT>
							</td>
							</tr>
							
				<tr class="formtxtr">
				<%
				String radio1="";String radio2="";
				 if("Y".equalsIgnoreCase(status)){
				 radio1="checked";
				}
				else if("N".equalsIgnoreCase(status))
				{
				 radio2="checked";
				}
				%>
				<td  width="194" align="left" class="text">Status<FONT color=red>**</FONT>&nbsp;&nbsp;:</td>
				<td class="text" align="left"><input type="radio" name="status" value="Y" <%=radio1%>>Activate
				&nbsp;&nbsp;&nbsp;<input type="radio" name="status" value="N" <%=radio2%>>Deactivate
				</td>
				</tr>
				
				
				<tr class="formtxtr">
				<%
				String radio3="";String radio4="";
				 if("Y".equalsIgnoreCase(rstatus)){
				 radio3="checked";
				}
				else if("N".equalsIgnoreCase(rstatus))
				{
				 radio4="checked";
				}
				%>
				<td  width="194" align="left" class="text">Referral Status<FONT color=red>**</FONT>&nbsp;&nbsp;:</td>
				<td class="text" align="left"><input type="radio" name="rstatus" value="Y" <%=radio3%>>Activate
				&nbsp;&nbsp;&nbsp;<input type="radio" name="rstatus" value="N" <%=radio4%>>Deactivate
				</td>
				<tr class="formtxtr">
				<td  width="194" align="left" class="text">Remarks<FONT color=red>**</FONT>&nbsp;&nbsp;:</td>
				<td class="text" align="left"><textarea rows="2" cols="25" name="remarks" /><%=remarks %></textarea></td>
				</tr>
				
				
				</tr>
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
												<td>
													<input name="image" type="image" src="../images/Submit.jpg"  tabindex="16" />
												</td>
											</tr>
										</table>
									</td>
									<td>
										<table border="0" align="center" cellpadding="0"
											cellspacing="0">
											<tr>
												<td valign="middle">
												  	<a href="#" onclick="goback('<%=makeid%>')"><img src= "../images/Back.jpg"/></a> 
												
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>  
                
            
							
</tr>	</table>		

                            <input type="hidden" name="motorbodyform" value="insert">
							<input type="hidden" name="productId" value="<%=session.getAttribute("productid")==null?"65":session.getAttribute("productid") %>">
							<input type="hidden" name="branchCode" value="<%=session.getAttribute("LoginBranchCode")%>">  
							<input type="hidden" name="typeid" value="">
							<input type="hidden" name="makeID" value="<%=makeid%>"/>
							<input type="hidden" name="modelId" value="<%=modelId%>"/>
							<input type="hidden" name="mode" value="<%=mode %>" />
							<input type="hidden" name="requestFrom" value="modelconfig"/>
							<input type="hidden" name="makeName" value=<%=makeName %> />
											
</form>
<script>
function goback(a)
{
document.form1.makeID.value=a
document.form1.action="../servlet/MotorBodyController?requestFrom=modelconfig&val=display";
document.form1.submit();
return false;
}
</script>
</html>				
