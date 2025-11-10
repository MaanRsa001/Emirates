
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


<%
String productTypeId = "";
if(session.getAttribute("product_id")!=null)
	productTypeId = (String)session.getAttribute("product_id");
String fromBroker = request.getParameter("fromBroker");
fromBroker = fromBroker==null?"No":fromBroker;
if(fromBroker.equalsIgnoreCase("Yes"))
{
%>
	<%@include file="../openMenu.jsp"%>
<%
}
else
{
%>
	<%@ include file="../admin/header.jsp" %>
	</td></tr></table>
</td></tr></table>
</td></tr></table>
<table width="100%"  border="0" cellspacing="0" cellpadding="0" align=left>
<tr>
<td align="left" valign="top" width="100%">
<%
}
%>


<html>
<head>
<title>Emirates</title>

</head>
<link href="/css/style.css" rel="stylesheet" type="text/css" />
<body>

  
<table width="100%"  border="0" cellspacing="0" cellpadding="0">
<tr> 
<td width="8" height="25" >&nbsp;</td>
<td width="98%"><span class="heading">LC POLICY REPORTS</span></td>
<form name="lcReport" method="post" >
    <%
		
		String currencyType = "";

		HashMap brokerDetails1 = new HashMap();
		brokerDetails1 = (HashMap)session.getAttribute("BrokerDetails");
		String cid="";
		String decimalPlace="";
		if(brokerDetails1.size()>0)
		{
			cid = (String)brokerDetails1.get("Orgination");
			currencyType = (String)brokerDetails1.get("CurrencyAbb");
			decimalPlace = (String)brokerDetails1.get("CurrencyDecimal");
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
		String openNo = request.getParameter("openNo");
		openNo = openNo==null?"0":openNo;
		double usedAmt = 0;
		double available = 0;
		double lcamount = 0;
		double LCAmoutGiven = 0;
		double exRate = 0;
		double finalLCamt = 0;
		HashMap amtDetails = new HashMap();
		String LCDate =  "";
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
				finalLCamt = available / exRate;
			}
		
     %>
    
            <table width="100%"  border="0" cellspacing="0" cellpadding="0">
                <tr align="center">
                <td colspan="2"> 
                    <table width="95%" border="0" cellspacing="1" cellpadding="0" class="text">
    <tr> 
          <td colspan="8" >&nbsp;&nbsp;&nbsp;<span class="heading">Core Application POLICY NO DETAILS</span></td>
	</tr>  
	<tr class="royamenuhead">
		   <td class="bottomtext" colspan="2"><b>Core Application Policy No</b></td>                                 
           <td class="bottomtext" colspan="4"><b>Customer Name</b></td>                                 
           <td class="bottomtext" colspan="2"><b>Broker Name</b></td>                                 
	</tr>
	<tr align='center' height="20">
		   <td class="formtxtc" colspan="2"><%=moc%></td>                                 
           <td class="formtxtf" colspan="4"><%=cName%></td>                                 
           <td class="formtxtf"  colspan="2"><%=bcName%></td>                                 
	</tr>
	
	<tr> 
		<td height="14" colspan="8">&nbsp;&nbsp;&nbsp;<span class="heading">LC DETAILS</span></td>
	</tr>
	<tr class="royamenuhead" height="20">
           <td class="bottomtext">LC Number</td>                                 
           <td class="bottomtext">Bank Name</td>                                 
           <td class="bottomtext">LC Issue Date</td>                                 
           <td class="bottomtext">LC Amount (<%=curName%>)</td>                                
           <td class="bottomtext">Exchange Rate</td>                                
           <td class="bottomtext">LC Amount  (<%=currencyType%>)</td>                                
           <td class="bottomtext">Used Amount  (<%=currencyType%>)</td> 
           <td class="bottomtext">Available Balance  (<%=currencyType%>)</td> 
	</tr>
	<tr>
		   <td class="formtxtc" ><%=LcNo%></td>                                 
           <td class="formtxtc"class=><%=bankName%></td>                                 
           <td class="formtxtc"><%=LCDate%></td>                                 
           <td class="formtxtr" align="right"><%=fmt.format(LCAmoutGiven)%></td>                                
           <td class="formtxtr" align="right"><%=fmt.format(exRate)%></td>                                
           <td class="formtxtr" align="right"><%=fmt.format(lcamount)%></td>                                
           <td class="formtxtr" align="right"><%=fmt.format(usedAmt)%></td> 
           <td class="formtxtr" align="right"><%=fmt.format((lcamount-usedAmt))%></td> 
	</tr>
   </table>
	
          <%
			int no_of_records1=20;//rajesh
			int displaypages1=5;
			int samplepages1=displaypages1;
			if(request.getParameter("displaypages1")!=null&&!request.getParameter("displaypages1").equalsIgnoreCase(""))
				displaypages1=request.getParameter("displaypages1")==null?3:Integer.parseInt(request.getParameter("displaypages1"));

				int length124 = 0;
				if(LCDetails.length == 0)
				{
					length124=1;
				}
				else
				{
					length124=LCDetails.length;
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
		double usedAmts=0;
		double runBal = 0;
		double usedAmtsSum = 0;
		if(request.getParameter("runBal")==null)
			runBal = lcamount;
		else
		{
			/*String temp = request.getParameter("runBal");
			temp = temp==null?"0":temp;
			if(temp.length()>0)
				runBal = Double.parseDouble(temp);*/
			try
			{
				if(spage1==1)
				{
					runBal = lcamount;
				}
				else
				{
					for(int i=0;i<(display1-no_of_records1);i++)
					{
						usedAmtsSum = usedAmtsSum + Double.parseDouble(LCDetails[i][4]);
					}
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			runBal = lcamount - usedAmtsSum;
		}
	%>
	<table width="95%" border="0" cellspacing="1" cellpadding="1">
	<tr> <td align="left" colspan="8"><span class="heading">&nbsp;&nbsp; Policy Details </span></td>	</tr>
	
	 <tr>
           <td class="formtxtf" colspan="6">&nbsp;</td>	
			<td class="formtxtf" align="center"><b>Opening Balance (<%=currencyType%>)</b></td>                                
           <td align="center"><b><%=fmt.format(runBal)%></b></td> 
	</tr>
	 <tr class="royamenuhead" >
           <td colspan="2" class="bottomtext">SNo</td>                                 
           <td class="bottomtext">Policy No</td>                                 
           <td class="bottomtext">Customer Name</td>                                 
           <td class="bottomtext">Policy Issue Date</td>                                
           <td class="bottomtext">Policy Start Date</td>                                
           <td class="bottomtext">Sum Insured (<%=currencyType%>)</td>                                
           <td class="bottomtext">Running Balance (<%=currencyType%>)</td> 
	</tr>
	<%
			for(int i=0;i<LCDetails.length;i++)
			{
				k1++;
				if(spage1>1)
				{
					skip1=spage1-1;
					if(k1<=(skip1*no_of_records1))
						continue;
				}
				int k = i+1;
				usedAmts = usedAmts + Double.parseDouble(LCDetails[i][4]);
				runBal = runBal - Double.parseDouble(LCDetails[i][4]);
		  %>
			<tr >
				<td colspan="2" class="formtxtf"><%=(i+1)%></td>
				<td class="formtxtf"><%=LCDetails[i][0]%></td>
				<td class="formtxtf"><%=LCDetails[i][1]%></td>
				<td class="formtxtf"><%=LCDetails[i][2]%></td>
				<td class="formtxtf"><%=LCDetails[i][3]%></td>
				<td class="formtxtf" align="right"><%=fmt.format(Double.parseDouble(LCDetails[i][4]))%></td>
				<td class="formtxtf" align="right"><%=fmt.format(runBal)%></td>
			</tr>
		<%
				if(k1==display1)
					 break;
			}
		%>
		<tr>
		<td class="formtxtf" colspan="6">&nbsp;</td>	
		<td class="formtxtf" align="center"><b>Closing Balance (<%=currencyType%>)</b></td>	
		<td class="formtxtf" align="right"><b><%=fmt.format(runBal)%></b></td>	
		</tr>
		
		<tr> <td colspan="8">&nbsp;</td>	 </tr>
		
		<%
		if(length124>no_of_records1)
		{
		%>
		<tr class="royamenuhead">
		<td class="bottomtext" align="right" colspan="8">
		<%
			if(start1>0)
			{
		%>
			<a href ="javaScript:LC_Broker_List('<%=(start1+1)%>','<%=displaypages1%>','<%=start1%>','<%=(lcamount-usedAmts)%>')"><font  color="white"><<</font>&nbsp;&nbsp;</a>
		<%
			}
			boolean flag=false;
			int iValue=0;
			for(int s=start1;s<pages1;s++)	 
			{
				if(s<displaypages1)
				{
		%>
			<a href ="javaScript:LC_Broker_List('<%=s+1%>','<%=displaypages1%>','<%=start1%>','<%=(lcamount-usedAmts)%>')"><font  color="white"><%=s+1%></font></a>
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
			<a href ="javaScript:LC_Broker_List('<%=iValue%>','<%=displaypages1%>','<%=start1%>','<%=(lcamount-usedAmts)%>')">&nbsp;&nbsp;<font  color="white">>></font></a>
		<%
			}
		%>
			</td></tr>
	<%
		}
	%>
		
		</table>
                <tr>
                    <td colspan="5" >
                        <table width="98%"  border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td height="32" align="center" valign="middle" class="ltbgyellow">
                                  <table border="0" align="center" cellpadding="0" cellspacing="0">
			<tr>   
								 <td ><a href="javaScript:onClick=backEdit()" 
class="buttonsMenu"><img src="<%=pathq%>/images/Back.jpg"></a></td>
									<td>&nbsp;</td>
									 <td ><a href="#" onClick="return PrintReport('<%=lcNos%>','<%=bankId%>','<%=opencover%>','<%=bcName%>','<%=moc%>','<%=cName%>','<%=LcNo%>','<%=bankName%>','<%=curName%>')" 
class="buttonsMenu"><img src="<%=pathq%>/images/PrintQuote.jpg"></a> </td> 
									<td>&nbsp;</td>
									 <td ><a href="#" onClick="return SaveReport('<%=lcNos%>','<%=bankId%>','<%=opencover%>','<%=bcName%>','<%=moc%>','<%=cName%>','<%=LcNo%>','<%=bankName%>','<%=curName%>')" 
class="buttonsMenu"><img src="<%=pathq%>/images/Save.jpg"></a> </td> 
								</table>
                                  &nbsp;&nbsp;&nbsp;</td>
                            </tr>
                        </table>   

           <%
		}
		   else
		   {
			   if(!fromBroker.equalsIgnoreCase("Yes"))
			   {
		  %>
		   &nbsp;&nbsp;&nbsp;</td>
                            </tr>
                        </table>
			<%
			   }
		  %>
						<br><br><br><br><br><br>
		  <tr class="heading">
							<td height="32" align="center" colspan="7"><b>There is no Policy issued for this LC Number</b>&nbsp;&nbsp;&nbsp;</td>
			</tr>
			<tr>
			<td colspan="7" align="center" ><a href="javaScript:onClick=backEdit()" 
class="buttonsMenu"><img src="<%=pathq%>/images/Back.jpg"></a></td>
</tr>
		  <%
		   }
		  %>
			  
<input type="hidden" name="lcNos" value='<%=lcNos%>'>
<input type="hidden" name="opencover" value='<%=opencover%>'>
<input type="hidden" name="openNo" value="<%=openNo%>"/>
<input type="hidden" name="lcBroker" value='<%=login%>'>
<input type="hidden" name="login" value='<%=login%>'>
<input type="hidden" name="bcName" value='<%=bcName%>'>
<input type="hidden" name="cName" value='<%=cName%>'>
<input type="hidden" name="moc" value='<%=moc%>'>
<input type="hidden" name="fromBroker" value='<%=fromBroker%>'>
<input type="hidden" name="LcNo" value='<%=LcNo%>'>
<input type="hidden" name="bankName" value='<%=bankName%>'>
<input type="hidden" name="curName" value='<%=curName%>'>
<input type="hidden" name="bankId" value='<%=bankId%>'>
<input type="hidden" name="spage1">
<input type="hidden" name="displaypages1">
<input type="hidden" name="start1">
<input type="hidden" name="runBal">
        </form>
    </body>
</html>
<script>
function backEdit()
{
	document.lcReport.action="<%=pathq%>/LCCreation/LCOpenCoverWise.jsp";
	document.lcReport.submit();
}
function SaveReport(lcid,bid,open,bcName,moc,cName,LcNo,bankName,curName)
{
	document.lcReport.action="<%=pathq%>/LCCreation/lcDetailsReportsSave.jsp?opencover="+open+"&lcNos="+lcid+"&bcName="+bcName+"&moc="+moc+"&cName="+cName+"&LcNo="+LcNo+"&bankName="+bankName+"&curName="+curName+"&bankId="+bid;
	document.lcReport.submit();
	return false;
}
function PrintReport(lcid,bid,open,bcName,moc,cName,LcNo,bankName,curName)
{
	var URL = "lcDetailsReportsPrint.jsp?opencover="+open+"&lcNos="+lcid+"&bcName="+bcName+"&moc="+moc+"&cName="+cName+"&LcNo="+LcNo+"&bankName="+bankName+"&curName="+curName+"&bankId="+bid;
	var windowName = "BROKERREPORT";
	var width  = screen.availWidth;
	var height = screen.availHeight;
	var w = 900;
	var h = 450;
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
function LC_Broker_List(value124,displaypages1,start1,run)
  {
	document.lcReport.spage1.value=value124;
	document.lcReport.start1.value=start1;
	document.lcReport.runBal.value=run;
	document.lcReport.displaypages1.value=displaypages1;
	document.lcReport.action="<%=pathq%>/LCCreation/lcDetailsReports.jsp"
	document.lcReport.submit();
  }
</script>