<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="../admin/header.jsp" %>
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

<jsp:useBean id="lcBean" class="com.maan.opencover.LCDetails.LCInputsBean">
<jsp:setProperty name= "cus"   property = "*"/>
</jsp:useBean>
	
	<%
		String loginId = (String) session.getAttribute("user");
		boolean isEditable=false,disp=true,readOnly=false;
		
		//Brokers=lcBean.getBrokersHasCover(actualBranch);
		HashMap bnameMap = lcBean.getLCBrokerDetailReports(actualBranch);
		String temp = (String)bnameMap.get("size");
		int len = 0;
		if(temp.length()>0)
			len = Integer.parseInt(temp);
		String[][] Brokers = new String[len][4];
		for(int j=0;j<len;j++)
		{
			String bnames = (String)bnameMap.get("bro"+j);
			String logins = (String)bnameMap.get("login"+j);
			String openCounts = (String)bnameMap.get("openCount"+bnames);
			String lcCounts = (String)bnameMap.get("lcCount"+bnames);
			Brokers[j][0] = bnames;
			Brokers[j][1] = logins;
			Brokers[j][2] = openCounts;
			Brokers[j][3] = lcCounts;
		}
		String paths = request.getContextPath();
   %>

 <body >

        <form name="personal" method="post">
         <input type="hidden" name="from" value="AdminLCMaster"/>
			 <table width="213" border="0" cellspacing="0" cellpadding="0">
<%--@ include file="../admin/left.jsp" --%>

                </tr>
              </table>
            </td>

		
            <td width="1"></td>
            <td align="left" valign="top"> 

              <table width="100%"  border="0" cellspacing="0" cellpadding="0">
                <tr> 
                    <td width="98%" class="heading"><strong>LC MASTER CREATION</strong></td>
     </tr>
          <tr align="center">
            <td colspan="3">&nbsp;</td>
          </tr>
          <tr align="center">
            <td colspan="6">
			<table width="100%"  border="0" cellspacing="1" cellpadding="0" class="blueborder">
              <tr>
               <td align="center" class="ltbgyellow">
                          				  
																
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
	  
			
			
	

<table width="90%"  border="0" cellspacing="0" cellpadding="0">
<tr class="royamenuhead" align="center">
<td WIDTH="5%" class='bottomtext'><b>Select</b></td>
<td width="50%" class='bottomtext'><b>Broker Company Name</b></td>
<td width="10%" class='bottomtext'><b>Total Open Covers</b></td>
<td width="10%" class='bottomtext'><b>Total LC Numbers</b></td>
</tr>
	<%
			
			int no_of_records1=10;
			int displaypages1=5;
			int samplepages1=displaypages1;
			if(request.getParameter("displaypages1")!=null&&!request.getParameter("displaypages1").equalsIgnoreCase(""))
				displaypages1=request.getParameter("displaypages1")==null?3:Integer.parseInt(request.getParameter("displaypages1"));

				int length124 = 0;
				if(Brokers.length == 0)
				{
					length124=1;
				}
				else
				{
					length124=Brokers.length;
				}
				int pages1=length124/no_of_records1;
				int rem1=length124%no_of_records1;
				if(rem1!=0)
				{
					pages1=pages1+1;
				}
				int display1=0;
				int spage1=1;
				int  start1=0;
				if(request.getParameter("spage1")!=null&&!request.getParameter("spage1").equalsIgnoreCase(""))
				spage1=request.getParameter("spage1")==null?1:Integer.parseInt(request.getParameter("spage1"));
				if(request.getParameter("start1")!=null&&!request.getParameter("start1").equalsIgnoreCase(""))
				start1=request.getParameter("start1")==null?0:Integer.parseInt(request.getParameter("start1"));
				display1=no_of_records1*spage1;
				if(spage1>=displaypages1)
				{
					if(pages1>displaypages1)
					{
						start1++;
						displaypages1++;
					}
				}
				else	if((displaypages1-spage1)==(samplepages1-1)&& start1!=0)
				{
					start1--;
					displaypages1--;
				}
		int k1=0;
		int skip1=0;
		int count1=0;
		for(int i=0;i<Brokers.length;i++)
		{
			k1++;
			if(spage1>1)
			{
				skip1=spage1-1;
				if(k1<=(skip1*no_of_records1))
					continue;
			}
			if(k1==1)
			{
			}
			int k=i+1;
	%>
			<tr class="formtxtr">
				<td WIDTH="5%" class="text"><input type="radio" name="lcBroker" value="<%=Brokers[i][1]%>"></td>
				<td width="70%" class="text" align="left" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="proceed('<%=Brokers[i][1]%>')"><%=Brokers[i][0]%></a></td>
				<td width="70%" class="text" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="proceed('<%=Brokers[i][1]%>')"><%=Brokers[i][2]%></a></td>
				<td width="70%" class="text" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="proceed('<%=Brokers[i][1]%>')"><%=Brokers[i][3]%></a></td>
				<input type="hidden" name="<%=Brokers[i][1]%>" value="<%=Brokers[i][0]%>"/>
			</tr>
	<%
			if(k1==display1)
			  break;
		}
	%>
			<tr class="royamenuhead">
							<td height="12" align="right" colspan="6">
							<%
								if(length124>no_of_records1)
								{
									if(start1>0)
									{
							%>
							<a href ="javaScript:LC_Broker_List('<%=(start1+1)%>','<%=displaypages1%>','<%=start1%>')"><font  color="blue"><<</font>&nbsp;&nbsp;</a>
							<%
									}
									boolean flag=false;
									int iValue=0;
									for(int s=start1;s<pages1;s++)	 
									{
										if(s<displaypages1)
										{
							%>
							<a href ="javaScript:LC_Broker_List('<%=s+1%>','<%=displaypages1%>','<%=start1%>')"><font  color="blue"><%=s+1%></font></a>
							<%
										}
										else
										{
											flag=true;
											iValue=s;
											break;
										}
									}	 
									if(flag)
									{
								%>
							<a href ="javaScript:LC_Broker_List('<%=iValue%>','<%=displaypages1%>','<%=start1%>')">&nbsp;&nbsp;<font  color="blue">>></font></a>
							<%
									}
								}
							%>
							</td>
							
</table>
       <table>

                                        <tr align="center">
                                            <td height="1" colspan="3"></td>
                                        </tr>
                                        <tr align="center">
                                            <td colspan="3"><table width="95%"  border="0" cellspacing="0" cellpadding="0">
                                                    <tr>
                                                        <td height="32" align="center" valign="middle" >&nbsp;&nbsp;&nbsp;
                                                            
                                                  <a href="#" onclick="proceed('valid')"><img type="image"  src='../images/Proceed.jpg'/ ></a>
														
														</td>
														
												      </tr>
                                            </table>
<input type="hidden" name="valCheck"/>
<input type="hidden" name="spage1">
<input type="hidden" name="displaypages1">
<input type="hidden" name="start1">
	</form>
                     </body>
  <script>
  function proceed(valCheck)
  {
	document.personal.valCheck.value=valCheck;
	document.personal.action="<%=paths%>/LCControl/LCDetailsController";
	document.personal.submit();
  }
  function LC_Broker_List(value124,displaypages1,start1)
  {
	document.personal.spage1.value=value124;
	document.personal.start1.value=start1;
	document.personal.displaypages1.value=displaypages1;
	document.personal.action="<%=paths%>/LCCreation/LCCreationBrokerList.jsp"
	document.personal.submit();
  }
  </script>
</html>
