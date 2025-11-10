
<script language="JavaScript">
</script>
<%
String pathq = request.getContextPath();
String basePaths = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+pathq+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<%@ page import="java.util.*" %>
<%@ include file="../login/home1.jsp" %>

<jsp:useBean id="lcBean" class = "com.maan.opencover.LCDetails.LCInputsBean">
<jsp:setProperty property="*" name="lcBean"/>
</jsp:useBean>





<html>
<head>
<title>Emirates</title>

</head>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<body onLoad="window.print()">


  </td></tr></table>
</td></tr></table>
</td></tr></table>
<table width="100%"  border="0" cellspacing="0" cellpadding="0" align=left>
<tr>
<td align="left" valign="top" width="100%">
<table width="100%"  border="0" cellspacing="0" cellpadding="0">
<tr> 
<td width="8" height="25" class="formtxtg" bgcolor="#FCC721">&nbsp;</td>
<td width="98%" class="formtxtg"><strong>LC POLICY REPORTS</strong></td>
       
    <%
		
		//rajesh world work stated
		String currencyType = "";
		HashMap brokerDetails = new HashMap();
		String brokerBra = (String)session.getAttribute("LoginBranchCode");
		brokerDetails = (HashMap)session.getAttribute("BrokerDetails");
		String cid="";
		String dcid="";
		String decimalPlace="";
		if(brokerDetails.size()>0)
		{
			cid = (String)brokerDetails.get("Orgination");
			dcid = (String)brokerDetails.get("Destination");
			currencyType = (String)brokerDetails.get("CurrencyAbb");
			decimalPlace = (String)brokerDetails.get("CurrencyDecimal");
		}
	   //End
		java.text.NumberFormat fmt=null;
		if(decimalPlace.equalsIgnoreCase("2"))
			fmt=new java.text.DecimalFormat("##,##0.00");
		else if(decimalPlace.equalsIgnoreCase("3"))
			fmt=new java.text.DecimalFormat("##,##0.000");
		else if(decimalPlace.equalsIgnoreCase("4"))
			fmt=new java.text.DecimalFormat("##,##0.0000");
		else
			fmt=new java.text.DecimalFormat("##,##0.00");
		String from =request.getParameter("from")==null?"":request.getParameter("from");
		

		String opencover=request.getParameter("opencover")==null?"":request.getParameter("opencover");
		String lcNos=request.getParameter("lcNos")==null?"":request.getParameter("lcNos");
		String bcName = request.getParameter("bcName")==null?"":request.getParameter("bcName");
		String login = request.getParameter("login")==null?"":request.getParameter("login");
		String moc = request.getParameter("moc")==null?"":request.getParameter("moc");
		String cName = request.getParameter("cName")==null?"":request.getParameter("cName");
		
		String LcNo = request.getParameter("LcNo")==null?"":request.getParameter("LcNo");
		String bankName = request.getParameter("bankName")==null?"":request.getParameter("bankName");
		String curName = request.getParameter("curName")==null?"":request.getParameter("curName");
		String bankId = request.getParameter("bankId")==null?"":request.getParameter("bankId");

		double usedAmt = 0;
		double available = 0;
		double lcamount = 0;
		double LCAmoutGiven = 0;
		double exRate = 0;
		String LCDate = "";
		HashMap amtDetails = new HashMap();
		
		String[][] LCDetails=new String[0][0];
		
		LCDetails = lcBean.getLCDetailsReports(lcNos,bankId,opencover,decimalPlace);
		if(LCDetails.length>0)
		{
			for(int j=0;j<LCDetails.length;j++)
			{
				usedAmt=usedAmt+Double.parseDouble(LCDetails[j][4]);
			}
			amtDetails = lcBean.getLcAmtDetails(lcNos,bankId,opencover,usedAmt,cid);
			if(amtDetails.size()>0)
			{
				LCAmoutGiven = Double.parseDouble((String)amtDetails.get("LCAmoutGiven"));
				exRate = Double.parseDouble((String)amtDetails.get("exRate"));
				lcamount = Double.parseDouble((String)amtDetails.get("LCAmout"));
				lcamount = Double.parseDouble((String)amtDetails.get("LCAmout"));
				available = Double.parseDouble((String)amtDetails.get("balance"));
				LCDate = (String)amtDetails.get("LCDate");
			}
		}
     %>
        
<form name="lcReport" method="post" >


			
            <table width="100%"  border="0" cellspacing="0" cellpadding="0">
                <tr align="center">
                <td colspan="2"> 
                    <table width="95%"  border="0" cellspacing="1" cellpadding="0" class="text">
                        <tr>
                            <td align="center" class="ltbgyellow">
                             
							<table width="100%"  border="0" cellspacing="1" cellpadding="1" class = 'text'> 
							 <tr align="center" class="ltbgyellow"> 
          <td height="14" colspan="8" align="left">&nbsp;&nbsp;&nbsp;<span class="heading"><strong>Core Application POLICY NO DETAILS</strong></span></td>
	</tr>  
	<tr class="royamenuhead"  height="20">
		   <td class="bottomtext" align="center" colspan="2"><b>Core Application Policy No</b></td>                                 
           <td class="bottomtext" align="center" colspan="4"><b>Customer Name</b></td>                                 
           <td class="bottomtext" align="center"  colspan="2"><b>Broker Name</b></td>                                 
          
	</tr>
	<tr class="formtxtc" align='center'  height="20">
		   <td align="center" colspan="2"><%=moc%></td>                                 
           <td align="left" colspan="4"><%=cName%></td>                                 
           <td align="left"  colspan="2"><%=bcName%></td>                                 
          
	</tr>
	<tr align="center" class="ltbgyellow"> 
		<td height="14" colspan="8" align="left">&nbsp;&nbsp;&nbsp;<span class="heading"><strong>LC DETAILS</strong></span></td>
	</tr>
	<tr class="royamenuhead"  height="20">
           <td class="bottomtext" align="center" ><b>LC Number</b></td>                                 
           <td class="bottomtext" align="center"><b>Bank Name</b></td>                                 
           <td class="bottomtext" align="center"><b>LC Issue Date</b></td>                                 
           <td class="bottomtext" align="center"><b>LC Amount (<%=curName%>)</b></td>                                
           <td class="bottomtext" align="center"><b>Exchange Rate</b></td>                                
           <td class="bottomtext" align="center"><b>LC Amount  (<%=currencyType%>)</b></td>                                
           <td class="bottomtext" align="center"><b>Used Amount  (<%=currencyType%>)</b></td> 
           <td class="bottomtext" align="center"><b>Available Balance  (<%=currencyType%>)</b></td> 
	</tr>
	<tr class="formtxtc" align='center'  height="20">
	<td class="formtxtc" align="left" ><%=LcNo%></td>                                 
           <td class="formtxtc" align="left"><%=bankName%></td>                                 
           <td class="formtxtc" align="center"><%=LCDate%></td>                                 
           <td class="formtxtc" align="right"><%=fmt.format(LCAmoutGiven)%></td>                                
           <td class="formtxtc" align="right"><%=fmt.format(exRate)%></td>                                
           <td class="formtxtc" align="right"><%=fmt.format(lcamount)%></td>                                
           <td class="formtxtc" align="right"><%=fmt.format(usedAmt)%></td> 
           <td class="formtxtc" align="right"><%=fmt.format((lcamount-usedAmt))%></td> 
	</tr>
                                    
   <tr align="center" class="ltbgyellow"> 
       <td height="14" colspan="8" align="left">&nbsp;&nbsp;&nbsp;<span class="heading"><strong>POLICY DETAILS</strong></span>
       </td></tr>
	</td>
                </tr>
                	
                    <td>&nbsp;</td>
                </tr>
            </table>
		<%
			double runBal = 0;
			runBal = lcamount;
		%>
	 <table width="98%"  border="0" cellspacing="1" cellpadding="1" class="LTblueBG">
	  <tr class="formtxtg"  height="20">
           <td align="center" >&nbsp;</td>                                 
           <td align="center">&nbsp;</td>                                 
           <td align="center">&nbsp;</td>                                 
           <td align="center">&nbsp;</td>                                
           <td align="center">&nbsp;</td>                                
           <td align="center" class="formtxtg"><b>Opening Balance (<%=currencyType%>)</b></td>                                
           <td align="center"><b><%=fmt.format(runBal)%></b></td> 
	</tr>
	 <tr class="royamenuhead"  height="20">
           <td class="bottomtext" align="center" ><b>SNo</b></td>                                 
           <td class="bottomtext" align="center"><b>Policy No</b></td>                                 
           <td class="bottomtext" align="center"><b>Customer Name</b></td>                                 
           <td class="bottomtext" align="center"><b>Policy Issue Date</b></td>                                
           <td class="bottomtext" align="center"><b>Policy Start Date</b></td>                                
           <td class="bottomtext" align="center"><b>Sum Insured&nbsp;(<%=currencyType%>)</b></td>                                
           <td class="bottomtext" align="center"><b>Running Balance (<%=currencyType%>)</b></td> 
	</tr>
	
          <%
			//8500706 = internatonal - quote and print date time
			for(int i=0;i<LCDetails.length;i++)
			{
				runBal = runBal - Double.parseDouble(LCDetails[i][4]);
		  %>
			<tr class="ltbgyellow" align='center'  height="20">
				<td><%=(i+1)%></td>
				<td><%=LCDetails[i][0]%></td>
				<td align="left"><%=LCDetails[i][1]%></td>
				<td><%=LCDetails[i][2]%></td>
				<td><%=LCDetails[i][3]%></td>
				<td align="right"><%=fmt.format(Double.parseDouble(LCDetails[i][4]))%></td>
				<td align="right"><%=fmt.format(runBal)%></td>
                  
			</tr>
		<%
			}
		%>
		<tr class="formtxtg"  height="20">
		<td colspan="5">&nbsp;</td>	
		<td align="left" class="formtxtg"><b>Closing Balance (<%=currencyType%>)</b></td>		
		<td align="right"><b><%=fmt.format(runBal)%></b></td>	
		</table>
                <tr>
                    <td colspan="5" >
                        <table width="98%"  border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td height="32" align="center" valign="middle" class="ltbgyellow">
                                  <table border="0" align="center" cellpadding="0" cellspacing="0">
			<tr>   
                                   
                                    &nbsp;	&nbsp;	&nbsp;

										<%
					
									%>
								
								 <td><a href="#" onclick="window.close()" 
class="buttonsMenu"><img src="<%=pathq%>/images/Back.jpg"></a></td>
									
                                    &nbsp;	&nbsp;	&nbsp;
									<!--<td>&nbsp;</td>
									 <td><a href="#" onClick="return premiumSummary()" 
class="buttonsMenu"><img src="<%=pathq%>/images/Submit.gif"></a> </td> -->

								</table>
                                       &nbsp;&nbsp;&nbsp;</td>
                            </tr>
                        </table>

                    
			
<input type="hidden" name="lcNos" value='<%=lcNos%>'>
<input type="hidden" name="opencover" value='<%=opencover%>'>
<input type="hidden" name="lcBroker" value='<%=login%>'>
<input type="hidden" name="login" value='<%=login%>'>
<input type="hidden" name="bcName" value='<%=bcName%>'>
<input type="hidden" name="cName" value='<%=cName%>'>
<input type="hidden" name="moc" value='<%=moc%>'>

        </form>
    </body>
</html>
<script>
function backEdit()
{
	document.lcReport.action="<%=pathq%>/LCCreation/LCOpenCoverWise.jsp";
	document.lcReport.submit();
}
</script>