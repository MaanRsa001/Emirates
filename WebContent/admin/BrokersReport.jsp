<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="header.jsp" %>
    <head>
        <title>Emirates</title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
        <link href="../css/style.css" rel="stylesheet" type="text/css">
        <style type="text/css">
            <!--
.style1 {color: #FF0000}
            -->
        </style>
    </head>

<jsp:useBean id= "cus" class = "com.maan.admin.AdminBean">
<jsp:setProperty name= "cus"   property = "*"/>
</jsp:useBean>
<jsp:useBean id="broker" class="com.maan.admin.BrokerCreationBean">

<jsp:setProperty name="broker" property="out" value="<%= response.getWriter() %>" /> 

</jsp:useBean>
	
	<%
		String loginId = (String) session.getAttribute("user");
		String adminBranch = "";
		adminBranch = cus.getAdminBranch(loginId);
		session.setAttribute("adminBranch",adminBranch);
		boolean isEditable=false,disp=true,readOnly=false;
		String[][] Brokers=cus.getAllBrokers(adminBranch);
		
  
   %>

 <body >

        <form name="personal" method="post" action="BrokerCreationController">
         
			 <table width="213" border="0" cellspacing="0" cellpadding="0">
<%--@ include file="left.jsp" --%>

                </tr>
              </table>
            </td>

		
            <td width="1"></td>
            <td align="left" valign="top"> 

              <table width="100%"  border="0" cellspacing="0" cellpadding="0">
                <tr> 
                 <!-- <td width="8" height="25" class="heading" bgcolor="#FCC721">&nbsp;</td>
                  <td width="1"></td> -->
                 <td width="98%" class="heading"><strong>BROKER REPORTS</strong></td>
          </tr>
          <tr align="center">
            <td colspan="3">&nbsp;</td>
          </tr>
          <tr align="center">
            <td colspan="3">
			<table width="100%"  border="0" cellspacing="1" cellpadding="0" class="blueborder">
              <tr>
               <td align="center" class="formtxtr">
                          				  
																
							  <%@ include file="admin_sub_menu_reports.jsp" %>
			
			<table width="100%"  border="0" cellspacing="0" cellpadding="0">
			
                                        <tr align="center">
                                            <td colspan="3">
											<table>
											<tr align="left">
											<td colspan="3">
											<font color="red" size="3">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=request.getAttribute("errorDetail")!=null?(String)request.getAttribute("errorDetail"):"&nbsp;"%></font>
											</td>
											</tr>
											</table>

	

<table>
<tr class="formtxtr" align="center"><td></td><td>Broker Reports</td></tr>



	   <tr class="formtxtr">
		<td class="text">Select Broker</td>
			<td class=""><select name="selectBrokers">
			<option value="all">All</option>
				<%
				String allAgency = "";
				for(int i=0;i<Brokers.length;i++)
				{
					allAgency = allAgency+"'"+Brokers[i][2]+"',";
				%>
				<option value="<%=Brokers[i][2]%>"><%=Brokers[i][0]%>(<%=Brokers[i][1]%>)</option>
			<%
				}
				
			%>
			</select>
	   </tr>
		<%	if(allAgency.length()>0)
					allAgency = allAgency.substring(0,(allAgency.length()-1));
		%>
		<input type="hidden" name="allAgency" value="<%=allAgency%>"/>
</table>
       <table>

                                        <tr align="center">
                                            <td height="1" colspan="3"></td>
                                        </tr>
                                        <tr align="center">
                                            <td colspan="3"><table width="95%"  border="0" cellspacing="0" cellpadding="0">
                                                    <tr>
                                                        <td height="32" align="center" valign="middle" >&nbsp;&nbsp;&nbsp;
                                                            
                                                            
                                                            
                                                         <input name="image" type="image"  src='../images/Proceed.jpg' / >
														
														</td>
														
												      </tr>
                                            </table>
											<input type="hidden" name="requestfrom" value="Brokersreport">
											</form>
                     </body>
					  <script>
 
 </script>
</html>
