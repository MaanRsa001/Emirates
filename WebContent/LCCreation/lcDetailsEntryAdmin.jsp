
<script language="JavaScript">
</script>
<%
String pathq = request.getContextPath();
String basePaths = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+pathq+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<%@ page import="java.util.*" %>
<%@ include file="../login/home1.jsp" %>

<jsp:useBean id="premiumInputs" class = "com.maan.opencover.LCDetails.LCInputsBean">
<jsp:setProperty property="*" name="premiumInputs"/>
</jsp:useBean>
<jsp:useBean id="premiumInputs1" class = "com.maan.premium.DAO.PremiumInputsBean">
<jsp:setProperty property="*" name="premiumInputs1"/>
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
	<table width="90%"  border="0" cellspacing="0" cellpadding="0" align="center">
		<tr>
		<td align="center" valign="top" width="90%">
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
<td width="8">&nbsp;</td>
<td width="98%"><span class="heading">LC CREATION</span></td>
       
   <%
		String currencyType = "";
		String brokerBranch = (String)session.getAttribute("LoginBranchCode");
		HashMap brokerDetails1 = new HashMap();
		brokerDetails1 = (HashMap)session.getAttribute("BrokerDetails");
		String cid="";
		String dcid="";
		String decimalPlace ="";
		String openNo = request.getParameter("openNo");
		openNo = openNo==null?"0":openNo;
		if(brokerDetails1.size()>0)
		{
			cid = (String)brokerDetails1.get("Orgination");
			dcid = (String)brokerDetails1.get("Destination");
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
		String from =request.getParameter("from")==null?"":request.getParameter("from");
		
		String sessionId="";
        String companyId="";
        String productId="";
        String loginId="";
        
		String inceptionDate="";
		
		String lcAmount="";
		String lcCurrencyId="";
		String lcNumber="";
		String bankNameLc="";
		String policyDay="";
		String policyMonth="";
		String policyYear="";
		String expDay="";
		String expMonth="";
		String expYear="";
		String lcStatus="";
		
		String saleTermDetails[][] = new String[0][0];
		String saleTermIdentifier ="";
		String saleTerm="";
		saleTermDetails = premiumInputs1.getSaleTermDetails(brokerBranch);

		String opencover=request.getParameter("opencover")==null?"":request.getParameter("opencover");
		String lcNos=request.getParameter("lcNos")==null?"":request.getParameter("lcNos");
		String bcName = request.getParameter("bcName")==null?"":request.getParameter("bcName");
		String login = request.getParameter("login")==null?"":request.getParameter("login");
		String moc = request.getParameter("moc")==null?"":request.getParameter("moc");
		String cName = request.getParameter("cName")==null?"":request.getParameter("cName");
		String usedamt = request.getParameter("usedamt")==null?"":request.getParameter("usedamt");
		double availableBalance = 0;
		String LcNo = request.getParameter("LcNo")==null?"":request.getParameter("LcNo");
		String bankName = request.getParameter("bankName")==null?"":request.getParameter("bankName");
		String curName = request.getParameter("curName")==null?"":request.getParameter("curName");
		String bankId = request.getParameter("bankId")==null?"":request.getParameter("bankId");
		saleTerm = request.getParameter("saleTerm")==null?"":request.getParameter("saleTerm");

		double usedAmt = Double.parseDouble(usedamt);
		double available = 0;
		double lcamount = 0;
		double LCAmoutGiven = 0;
		double exRate = 0;
		String LCDate = "";
		double finalLCamt = 0;
		HashMap amtDetails = new HashMap();
		String[][] LCDetails=new String[0][0];
		
		if(!lcNos.equals("0"))
			LCDetails=premiumInputs.getsLCDetailsByOpenCover(opencover,lcNos);
		if(!opencover.equals(""))
		{
			session.setAttribute("openCoverNo",opencover);
		}
		
  
        Calendar cal = new GregorianCalendar();
	    int ddCurr=cal.get(Calendar.DATE);
	    boolean isCurrentMode=true;
	    String mmSCurr="";
	    String mmSCurr1="";
   	    int mmCurr=(cal.get(Calendar.MONTH)+1);
   	    int yyCurr=cal.get(Calendar.YEAR);
	    int hour12 = cal.get(Calendar.HOUR);            // 0..11
	    int hour24 = cal.get(Calendar.HOUR_OF_DAY);     // 0..23
	    int min = cal.get(Calendar.MINUTE);             // 0..59
	    int sec = cal.get(Calendar.SECOND);             // 0..59
	    int ms = cal.get(Calendar.MILLISECOND);         // 0..999
	    int ampm = cal.get(Calendar.AM_PM);      
                
       String[] mon={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
       String[] monNo={"1","2","3","4","5","6","7","8","9","10","11","12"}; 
       
       for(int i=0;i<mon.length;i++)
       {
       
		   if(Integer.parseInt(monNo[i])==mmCurr)
		   {
			   mmSCurr=mon[i];
			   mmSCurr1=mon[i];
		   }
       
       }        
                premiumInputs.setPolicyDay(""+ddCurr);
                premiumInputs.setPolicyMonth(mmSCurr);
                premiumInputs.setPolicyYear(""+yyCurr);
				premiumInputs.setExpDay(""+ddCurr);
                premiumInputs.setExpMonth(mmSCurr);
                premiumInputs.setExpYear(""+yyCurr);
                lcAmount="";
                lcCurrencyId="";
                lcNumber="";
                bankNameLc="0";
              
        sessionId=(String) session.getAttribute("ses")==null?"":(String) session.getAttribute("ses");
        loginId=(String) session.getAttribute("user")==null?"":(String) session.getAttribute("user");
        productId=(String) session.getAttribute("product_id")==null?"":(String) session.getAttribute("product_id");
        HashMap selectedAddons=new HashMap();
        StringBuffer error=new StringBuffer();
        boolean isErrorPage=false;
		String flag="S";
        if(request.getAttribute("errorMessage") !=null)
        {
        error=(StringBuffer)request.getAttribute("errorMessage");
        isErrorPage=true;
		flag="T";
        }
        String r="<sup><font color='red'> *</font></sup>";
        String []req=new String[]{r,r,r,r,r,r,r,r,r,r,r,r};
        String title =request.getParameter("Title")==null?"":request.getParameter("Title");
	    if(title!=null)
            session.setAttribute("customer","new");
        
        String option =request.getParameter("mode")==null?"":request.getParameter("mode");

if(option.equals(""))
{
	option=request.getAttribute("mode")==null?"":(String)request.getAttribute("mode");
}

        HashMap fullDetails=new HashMap();
		
      if(isErrorPage&&!option.equalsIgnoreCase("edit"))
      {
        option="eee";
       }
	   else if(option.equals("edit"))
       {
			option="edit";
		    String[][] Details=new String[0][0];
			Details=premiumInputs.getLcDetails(opencover,lcNos);
			if(Details.length>0)
			{
				lcNumber=Details[0][2]==null?"":Details[0][2];
				bankNameLc=Details[0][1]==null?"":Details[0][1];
				for(int i=0;i<mon.length;i++)
				{
					   if(Integer.parseInt(monNo[i])==Integer.parseInt(Details[0][4]))
					   {
						mmSCurr=mon[i];
					   }
					   if(Details[0][16]!=null&&(Integer.parseInt(monNo[i])==Integer.parseInt(Details[0][16])))
					   {
							mmSCurr1=mon[i];
					   }
				}        
                policyDay=Details[0][3]==null?"":Details[0][3];
                policyMonth=mmSCurr;
                policyYear=Details[0][5]==null?"":Details[0][5];
				lcStatus =Details[0][13]==null?"":Details[0][13];
				if(Details[0][15]!=null&&Details[0][17]!=null)
				{
					expDay=Details[0][15]==null?"":Details[0][15];
					expMonth=mmSCurr1;
					expYear=Details[0][17]==null?"":Details[0][17];
				}

                saleTerm = Details[0][19]==null?"0":Details[0][19];

                lcAmount = Details[0][20]==null?"0":Details[0][20];
				if(lcAmount.equals("0"))
					lcAmount=Details[0][7]==null?"":Details[0][7];
					
				lcCurrencyId=Details[0][6]==null?"":Details[0][6];
				amtDetails = premiumInputs.getLcAmtDetails(lcNos,bankId,opencover,usedAmt,cid);
				if(amtDetails.size()>0)
				{
					LCAmoutGiven = Double.parseDouble((String)amtDetails.get("LCAmoutGiven"));
					exRate = Double.parseDouble((String)amtDetails.get("exRate"));
					lcamount = Double.parseDouble((String)amtDetails.get("LCAmout"));
					lcamount = Double.parseDouble((String)amtDetails.get("LCAmout"));
					available = Double.parseDouble((String)amtDetails.get("balance"));
					LCDate = (String)amtDetails.get("LCDate");
					availableBalance = available;
					finalLCamt = available / exRate;
				}
				/*availableBalance = 	Double.parseDouble(Details[0][18]==null?"0":Details[0][18]);
				double exRate = premiumInputs.getExchangeRate(lcCurrencyId,cid);
				if(availableBalance<=0)
				{
					try
					{
						double actualLcAmt = Double.parseDouble(lcAmount)*exRate;
						availableBalance = actualLcAmt - Double.parseDouble(usedamt);
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
					try
					{
						//availableBalance = availableBalance/exRate;
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}*/
			}
            premiumInputs.setBankNameLc(bankNameLc);
			premiumInputs.setPolicyDay(policyDay);
			premiumInputs.setPolicyMonth(policyMonth);
			premiumInputs.setPolicyYear(policyYear);
			premiumInputs.setLcCurrencyId(lcCurrencyId);
			premiumInputs.setExpDay(expDay);
			premiumInputs.setExpMonth(expMonth);
			premiumInputs.setExpYear(expYear);
			String[][] premiumDetails=new String[0][0];
            inceptionDate=(String)fullDetails.get("inceptionDate")==null?"":(String)fullDetails.get("inceptionDate");
        } 
		if(lcStatus.length()<=0)
			lcStatus = "Y";
        boolean isEditMode=false;
        if("edit".equalsIgnoreCase(option)) 
		{
           isEditMode=true;
           session.setAttribute("mode","edit");
        }
      
	  try
	  {
		String sds=null;
		String sds1=null;
		sds=premiumInputs.getPolicyDay();
		sds1=premiumInputs.getExpDay();
		if(Integer.parseInt(sds)<=9)
		sds="0"+Integer.parseInt(sds);

		if(Integer.parseInt(sds1)<=9)
		sds1="0"+Integer.parseInt(sds1);
		      premiumInputs.setPolicyDay(sds);
		      premiumInputs.setExpDay(sds1);
	  }catch(Exception e)
	  {}
        %>
<form name="premium" method="post" >
<%
	String FreshModeInfo = request.getParameter("freshMode")==null?"":request.getParameter("freshMode");
%>
            <input type="hidden" name="mode" value="<%=request.getParameter("mode")%>"/>
			<%
				if(error.length()>0)
				{
			%>
			   <table width="50%" align="center" border="0" cellspacing="0" cellpadding="0" > 
                                    <tr align="center" >
                            <td colspan="5" align='left'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <%=error.length()>0?error.toString():"&nbsp;"%></td>
                           </tr >
							
							</table>
			<%
				}
			%>
            <table width="100%" cellspacing="0" cellpadding="0">
                <tr align="center">
                <td colspan="2"> 
                    <table width="95%" cellspacing="1" cellpadding="0" class="text">
                        <tr>
                            <td align="center">
							<table width="100%" align="center" cellspacing="1" cellpadding="1"> 
	<tr> 
          <td colspan="8">&nbsp;&nbsp;&nbsp;<span  class="heading"><strong>Core Application POLICY NO DETAILS</strong></span></td>
	</tr>  
	<tr class="royamenuhead" height="20">
		   <td class="bottomtext" colspan="2">Core Application Policy No</td>                                 
           <td class="bottomtext" colspan="4">Customer Name</td>                                 
           <td class="bottomtext" colspan="2">Broker Name</td>                                 
	</tr>
	<tr height="20">
		   <td class="formtxtc" colspan="2"><%=moc%></td>                                 
           <td class="formtxtf" colspan="4"><%=cName%></td>                                 
           <td class="formtxtf" colspan="2"><%=bcName%></td>                                 
	</tr>
		<%
			if(option.equalsIgnoreCase("edit"))
			{
		%>	
	<tr> 
		<td colspan="8">&nbsp;&nbsp;&nbsp;<span class="heading">LC DETAILS</span></td>
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
	<tr height="20">
	<td class="formtxtc" ><%=LcNo%></td>                                 
           <td class="formtxtc"><%=bankName%></td>                                 
           <td class="formtxtc"><%=LCDate%></td>                                 
           <td class="formtxtc"><%=fmt.format(LCAmoutGiven)%></td>                                
           <td class="formtxtc"><%=fmt.format(exRate)%></td>                                
           <td class="formtxtc"><%=fmt.format(lcamount)%></td>                                
           <td class="formtxtc"><%=fmt.format(usedAmt)%></td> 
           <td class="formtxtc"><%=fmt.format((lcamount-usedAmt))%></td> 
	</tr>
			<%
			}
			%>
			</table>
		<table width="98%" align="center" cellspacing="1" cellpadding="1">
		 <tr align="center"> 
               <td height="17" colspan="8">&nbsp;&nbsp;&nbsp;<span class="heading">LC DETAILS</span></td>
		</tr>
        <tr>  <td height="5" colspan="5" align="left" >      </tr>
           <tr>
            <td width="120" class="formtxtf">Bank Name&nbsp;<FONT color=red>**</FONT></label></td>
            <td class="formtxtf" width="497"> <%=premiumInputs.getBankDetails(cid).toString()%> 
            </td>
            </tr>   
			<tr> 
            <td width="120" class="formtxtf">LC Number&nbsp;<FONT color=red>**</FONT></td>
            <td class="formtxtf" width="497">
              <input name="lcNumber" type="text"  style="width:133px"  size="13" class = 'fdel' 
value='<%=isEditMode?lcNumber:isErrorPage?(request.getParameter("lcNumber")==null?"":request.getParameter("lcNumber")):""%>' maxlength="25" <%=isEditMode?"readonly":""%>>
                                        </td>
                                    </tr>
                                    <tr >
            <td class="formtxtf" width="120" >LC Start Date</td>
            <td class="formtxtf" width="497"> 
              <SELECT name='policyDay' class='scrolLet' style="width:45px"> 
                                            <OPTION value="DD">DD</OPTION>
                                             <%
										String sd=null;
										int d=0;
			for(int i=1;i<=31;i++)
			{
				if(i<=9)
					sd="0"+i;
				else
					sd=""+i;
				String sel="";
			
			if(sd.equalsIgnoreCase(premiumInputs.getPolicyDay())&&d==0)
			{
				sel="Selected";
            }
            else if(sd.equalsIgnoreCase(request.getParameter("policyDay")))
			{
			    sel="Selected";
				d++;
			}
	     %>
			<option value=<%=sd%> <%=sel%>><%=i%></option>
			<%}%>
	   </SELECT>
	   <SELECT name="policyMonth" class='scrolLet' style="width:45px">
       <OPTION value="MM">MM</OPTION>
        <%
				int j=0;
		String[] months={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
			for(int i=1;i<=months.length;i++)
		{
			String sel="";
			
			
				if(months[i-1].equalsIgnoreCase(premiumInputs.getPolicyMonth())&&j==0)
			{
				sel="Selected";
              }
                else
	                if(months[i-1].equalsIgnoreCase(request.getParameter("policyMonth")))
			          {
			            sel="Selected";
						j++;
			           }
	                else
		                 {
			                 }
		
			%>
		<option value="<%=months[i-1]%>" <%=sel%> ><%=months[i-1]%></option>
		<%}%>
	  </SELECT>
	  
	  <SELECT name="policyYear" class='scrolLet' style="width:50px">
                                                    <OPTION value="YYYY" selected>YYYY</OPTION>
                                                     <%
													 int y=0;
		java.util.Calendar cal1 = java.util.Calendar.getInstance();
		for(int i=cal1.get(java.util.Calendar.YEAR)-2;i<cal1.get(java.util.Calendar.YEAR)+2;i++)
		{
			String sel="";
				if((""+i).equalsIgnoreCase(premiumInputs.getPolicyYear())&&y==0)
			{
				sel="Selected";
              }
                else
	                if((""+i).equalsIgnoreCase(request.getParameter("policyYear")))
			          {
			            sel="Selected";
						y++;
			           }
	                else
		                 {
			                 }
			%>
		<option value=<%=i%> <%=sel%>><%=i%></option>
		<%}%>
	  </SELECT>
	  </td>
                                    </tr>
									  <tr >
            <td width="120" class="formtxtf">LC Expiry Date</td>
            <td class="formtxtf" width="497"> 
              <SELECT name='expDay' class='scrolLet' style="width:45px"> 
                                            <OPTION value="DD">DD</OPTION>
                                             <%
										String sd1=null;
										int d1=0;
			for(int i=1;i<=31;i++)
			{
				if(i<=9)
					sd1="0"+i;
				else
					sd1=""+i;

				String sel="";
			
				if(sd1.equalsIgnoreCase(premiumInputs.getExpDay())&&d1==0)
			{
				sel="Selected";
              }
                else
	                if(sd1.equalsIgnoreCase(request.getParameter("expDay")))
			          {
			            sel="Selected";
						d1++;
			           }
	                else
		                 {
			                 }
				%>
			<option value=<%=sd1%> <%=sel%>><%=i%></option>
			<%}%>
	   </SELECT>
	   <SELECT name="expMonth" class='scrolLet' style="width:45px">
       <OPTION value="MM">MM</OPTION>
        <%
				int j1=0;
		//String[] months={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
			for(int i=1;i<=months.length;i++)
		{
			String sel="";
			
			
				if(months[i-1].equalsIgnoreCase(premiumInputs.getExpMonth())&&j1==0)
			{
				sel="Selected";
              }
                else
	                if(months[i-1].equalsIgnoreCase(request.getParameter("expMonth")))
			          {
			            sel="Selected";
						j1++;
			           }
	                else
		                 {

			                 }
		//out.println("the month is "+months[i]);
			//if(2>1) return;	
			%>
		<option value="<%=months[i-1]%>" <%=sel%> ><%=months[i-1]%></option>
		<%}%>
	  </SELECT>
	  
	  <SELECT name="expYear" class='scrolLet' style="width:50px">
                                                    <OPTION value="YYYY" selected>YYYY</OPTION>
                                                     <%
													 int y1=0;
		
		for(int i=cal1.get(java.util.Calendar.YEAR)-2;i<cal1.get(java.util.Calendar.YEAR)+2;i++)
		{
			
			String sel="";
			
			
				if((""+i).equalsIgnoreCase(premiumInputs.getExpYear())&&y1==0)
			{
				sel="Selected";
              }
                else
	                if((""+i).equalsIgnoreCase(request.getParameter("expYear")))
			          {
			            sel="Selected";
						y1++;
			           }
	                else
		                 {
			                 }
			%>
		<option value=<%=i%> <%=sel%>><%=i%></option>
		<%}%>
	  </SELECT>
	  </td>
                                    </tr>
                                         <tr>
            <td width="120" class="formtxtf">LC Currency&nbsp;<FONT color=red>**</FONT></td>
            <td class="formtxtf" width="497"> <%=premiumInputs.getCurrencyDetails(cid).toString()%> 
            </td>
                                    </tr>   
                                    <tr> 
            <td width="120" class="formtxtf" >LC Amount&nbsp;<FONT color=red>**</FONT> </td>
            <td class="formtxtf" width="497"> 
              <input name="lcAmount"  type="text" size="20" class='fdel'  style="width:133px" 
value='<%=isEditMode?lcAmount:isErrorPage?(request.getParameter("lcAmount")==null?"":request.getParameter("lcAmount")):""%>' maxlength="18"></td> </tr>
									 <tr> 
            <td width="120" class="formtxtf" >Sale Term&nbsp;<FONT color=red>**</FONT> </td>
            <td class="formtxtf" width="497"> 
					<select name='saleTerm' class='scrolLet' style='width:133px'>
				<option value ='0'>Select</option>
			<%
				for(int i = 0; i < saleTermDetails.length;i++)
				{
					if (saleTermDetails[i][0].equalsIgnoreCase(saleTerm))
					{
					    saleTermIdentifier = "selected";
					}
					else
					{
						saleTermIdentifier = "";
					}
			%>
			<option value=<%=saleTermDetails[i][0]%> <%=saleTermIdentifier%>><%=saleTermDetails[i][1]%></option>
			<%	 
					}
			%>
					</select></td> </tr>
				<%if(fromBroker.equalsIgnoreCase("Yes")&&option.equalsIgnoreCase("edit")){%>
				<input type="hidden" name="lcStatus" value="<%=lcStatus%>"/>
				<%}else{%>
									<tr> 
            <td width="420" class="formtxtf" >Do you want to activate this LC Number?</td>
                                        
            <td class="formtxtf" width="497"> 
              <input name="lcStatus" type="radio" class='fdel' value='Y' <%=lcStatus.equalsIgnoreCase("Y")?"checked":""%>>Yes&nbsp;<input name="lcStatus"  type="radio" class='fdel' value='N' <%=lcStatus.equalsIgnoreCase("N")?"checked":""%>>No</td>
                               </tr>
				  <%}%>
                <tr>
                    <td colspan="5" >
                        <table width="98%"  border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td height="32" align="center" valign="middle" class="ltbgyellow">
                                  <table border="0" align="center" cellpadding="0" cellspacing="0">
			<tr>   
								 <td ><a href="javaScript:onClick=backEdit('<%=fromBroker%>')" 
class="buttonsMenu"><img src="<%=pathq%>/images/Back.jpg"></a></td>
									
									<td>&nbsp;</td>
									 <td ><a href="#" onClick="return premiumSummary()" 
class="buttonsMenu"><img src="<%=pathq%>/images/Submit.jpg"></a> </td>
									<td ><a href="javaScript:onClick=backEdit('<%=fromBroker%>')" 
class="buttonsMenu"><img src="<%=pathq%>/images/Cancel.jpg"></a></td>
								</table>
                                       &nbsp;&nbsp;&nbsp;</td>
                            </tr>
                        </table>

                    </td>
                </tr>
                	
				<tr> 
                <td height="17" colspan="3" align="left">&nbsp;&nbsp;&nbsp;</td>
                <input type="hidden" name="existing" <%=(title!=null)?"value='YES'":""%>/>
                <input type="hidden" name="validation" value='Y'/>
                <input type="hidden" name="option" <%=isEditMode?"value='edit'":""%>/>
                <input type="hidden" name="option" <%=isEditMode?"value='edit'":""%>/>
                <input type="hidden" name="productId" value="<%=productId%>" >
                <input type="hidden" name="loginId" value="<%=loginId%>" >
                <input type="hidden" name="sessionId" value="<%=sessionId%>" >
                <input type="hidden" name="companyId" value="<%=companyId%>" >
                <input type="hidden" name="availableBalance" value="<%=(""+availableBalance)%>">
                <input type="hidden" name="usedamt" value="<%=(""+usedAmt)%>">
                </tr>
                <tr>
                <td>&nbsp;</td>
                </tr>
            </table>
            <%request.setAttribute("premiumInputsBean",premiumInputs);%>

			
<input type="hidden" name="from" value='AdminLCDCreation'>
<input type="hidden" name="lcNos" value='<%=lcNos%>'>
<input type="hidden" name="openNo" value="<%=openNo%>"/>
<input type="hidden" name="opencover" value='<%=opencover%>'>
<input type="hidden" name="lcBroker" value='<%=login%>'>
<input type="hidden" name="login" value='<%=login%>'>
<input type="hidden" name="bcName" value='<%=bcName%>'>
<input type="hidden" name="cName" value='<%=cName%>'>
<input type="hidden" name="moc" value='<%=moc%>'>
<input type="hidden" name="fromBroker" value="<%=fromBroker%>">
<input type="hidden" name="LcNo" value='<%=LcNo%>'>
<input type="hidden" name="bankName" value='<%=bankName%>'>
<input type="hidden" name="curName" value='<%=curName%>'>
<input type="hidden" name="bankId" value='<%=bankId%>'>
        </form>
    </body>
</html>
<script>
function backEdit(from)
{
	/*if(from=="Yes")
	 document.premium.action="../ViewOpenCovers.jsp";
	else*/
	document.premium.fromBroker.value = from;
	 document.premium.action="<%=pathq%>/LCCreation/LCOpenCoverWise.jsp";
	document.premium.submit();
}
function selectOne()
{
	document.getElementById("UserId1").style.display="block";
	document.getElementById("UserId2").style.display="block";
}
function selectTwo()
{
	document.getElementById("UserId1").style.display="none";
	document.getElementById("UserId2").style.display="none";
}
function premiumSummary()
{
    document.premium.action="<%=basePaths%>LCControl/LCDetailsController";
    document.premium.submit();
	return false;
}
function openLCDetails(opencover)
{
	  document.premium.action="<%=pathq%>/premium/OpenCoverView.jsp?opencover="+opencover;
	  document.premium.submit();
}
function openLCDetails123(opencover)
{
var URL = "../premium/OpenCoverView.jsp?opencover="+opencover;
	var windowName = "DetailsView";
	var width  = screen.availWidth;
	var height = screen.availHeight;
	var w = 520;
	var h = 300;
	var features =
		'width='          + w +
		',height='		  + h +
		',left='		  + ((0) * .5)  +
		',top='			  + ((0) * .5) +
		',directories=no' +
		',location=no'	  +
		',menubar=no'	  +
		',scrollbars=yes' +
		',status=no'	  +
		',toolbar=no'	  +
		',resizable=no';
	var strOpen = window.open (URL, windowName, features);
	strOpen.focus();
	}
</script>