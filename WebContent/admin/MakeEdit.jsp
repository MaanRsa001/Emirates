<%@ page pageEncoding="UTF-8" %> <%@ page language="java" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<%--<%@page contentType="text/html; charset=iso-8859-1" pageEncoding="iso-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@
page contentType="text/html; charset=windows-1256"%> 
<%@
page pageEncoding="Cp1256"%>

--%><html>
<%@ include file="header.jsp" %>

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
<form name="form1" method="post" action="../servlet/MotorBodyController?requestFrom=updateconfig">
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
                int previous = Integer.parseInt(YearOnly.format(date)) - 1;
				int after = Integer.parseInt(YearOnly.format(date))+1;
				String day=(DayOnly.format(date));
				String year=(YearOnly.format(date));
				String month=(MonthOnly.format(date));
				//MotorBodyCreation broker=request.getAttribute("motorBody")==null?new MotorBodyCreation():(MotorBodyCreation)request.getAttribute("motorBody");
			    MakeBean bean=request.getAttribute("makeBean")==null?new MakeBean():(MakeBean)request.getAttribute("makeBean");
				
				String mode=request.getParameter("mode")==null?"":(String)request.getParameter("mode");
				System.out.println("Mode: "+mode);
				
				
				String makename_eng=request.getParameter("makename_eng")==null?"":(String)request.getParameter("makename_eng");
				String makename_arabic=request.getParameter("makename_arab")==null?"":(String)request.getParameter("makename_arab");
				String corecode=request.getParameter("corecode")==null?"":(String)request.getParameter("corecode");
				String status=request.getParameter("status")==null?"":(String)request.getParameter("status");
				String makeid=request.getParameter("makeID")==null?"":(String)request.getParameter("makeID");
				String effDay=request.getParameter("effDay")==null?"":(String)request.getParameter("effDay");
				String effMonth=request.getParameter("effMon")==null?"":(String)request.getParameter("effMon");
				String effYear=request.getParameter("effYear")==null?"":(String)request.getParameter("effYear");
				String refstatus=request.getParameter("refstatus")==null?"":(String)request.getParameter("refstatus");
				String remarks=request.getParameter("remarks")==null?"":(String)request.getParameter("remarks");

				if(request.getAttribute("makeBean")!=null)
				{
					makename_eng=bean.getMakeName()==null?"":bean.getMakeName();
					makename_arabic=bean.getMakeArabic()==null?"":bean.getMakeArabic();
					corecode=bean.getCoreCode()==null?"":bean.getCoreCode();
					status=bean.getStatus()==null?"":bean.getStatus();
					makeid=bean.getMakeId()==null?"":bean.getMakeId();
					effDay=bean.getEffDate();
					effMonth=bean.getEffMonth();
					effYear=bean.getEffYear();
					refstatus = bean.getReferalStatus();
					remarks = bean.getRemarks();
				}
				
				
				String branchCode=(String)session.getAttribute("LoginBranchCode");	
				System.out.println("inside jsp"+bean.getMakeName());
				System.out.println("inside jsp"+bean.getMakeArabic());	
				System.out.println("makename_arabic"+makename_arabic);	
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
	<div align="center" ><B>Make Configuration</div>	</td> 
    </tr>
    						
								
				<tr class="formtxtr">
				<td  width="194" align="left" class="text">Make Name In English<FONT color=red>**</FONT>&nbsp;&nbsp;:</td>
				<td class="text" align="left"><textarea rows="2" cols="25" name="makename_eng" /><%=makename_eng %></textarea></td>
				</tr>
				<tr class="formtxtr">
				<td  width="194" align="left" class="text">Make Name In Arabic&nbsp;&nbsp;:</td>
				<td class="text" align="left"><textarea rows="2" cols="25" name="makename_arab" ><%=makename_arabic %></textarea></td>
				</tr>
				<tr class="formtxtr">			
				<td class = "text"  width="194" align="left">Core App Code<FONT color=red>**</FONT>&nbsp;&nbsp;:</td>
				<td class = "text" align="left"><input type="text" class="fde1" name="corecode"  value="<%=corecode%>"/></td>
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
								</option>
									<%} %>
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
				String refradio1="";String refradio2="";
				 if("Y".equalsIgnoreCase(refstatus)){
				 refradio1="checked";
				}
				else if("N".equalsIgnoreCase(refstatus))
				{
				 refradio2="checked";
				}
				%>
				<td  width="194" align="left" class="text">Referal Status<FONT color=red>**</FONT>&nbsp;&nbsp;:</td>
				<td class="text" align="left"><input type="radio" name="refstatus" value="Y" <%=refradio1%>>Activate
				&nbsp;&nbsp;&nbsp;<input type="radio" name="refstatus" value="N" <%=refradio2%>>Deactivate
				</td>
				</tr>
				<tr class="formtxtr">
				<td  width="194" align="left" class="text">Remarks&nbsp;&nbsp;:</td>
				<td class="text" align="left">
					<textarea rows="2" cols="25" name="remarks" /><%=remarks %></textarea>
				</td>
				</tr>
				</tr>
				</tr>
				</table>
                 
                 <table width="80%" border="0" cellpadding="0" cellspacing="0" align='center'>
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
													<a href="#" onclick="goback()"><img src= "../images/Back.jpg"/></a> 
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
							<input type="hidden" name="mode" value="<%=mode %>" />
											
</form>
<script>
function goback()
{
document.form1.action="../servlet/MotorBodyController?requestFrom=makeconfig";
document.form1.submit();
return false;
}
</script>
</html>				
