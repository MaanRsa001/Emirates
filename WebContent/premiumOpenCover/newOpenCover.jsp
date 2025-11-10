<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@page import="com.maan.premium.DAO.PremiumInputsBean"%>	
<%String cpath1 = request.getContextPath(); %>
<!DOCTYPE HTML>
<html>
<head>
	<title>New OpenCover Generation</title>
	<jsp:useBean id="newCover" class="com.maan.opencover.bean.newCoverBean">
		<jsp:setProperty name="newCover" property="*"/>
	</jsp:useBean>
	<jsp:useBean id="premiumInputs" class="com.maan.premium.DAO.PremiumInputsBean">
		<jsp:setProperty property="*" name="premiumInputs" />
	</jsp:useBean>
	<jsp:useBean id="beans" class="com.maan.opencover.bean.openCoverQuotation">
		<jsp:setProperty name="beans" property="*"/>
	</jsp:useBean>
	<jsp:useBean id="branch" class="com.maan.opencover.bean.opencoverEntry">
		<jsp:setProperty name="branch" property="*"/>
	</jsp:useBean>
	
	<link href="<%=cpath1%>/css/style.css" rel="stylesheet" type="text/css">
	<link href="<%=cpath1%>/css/footable-0.1.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
	<script language="JavaScript">
		function stopRKey(evt) { 
		 	 var evt = (evt) ? evt : ((event) ? event : null); 
		  	 var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
		 	 if ((evt.keyCode == 13) && ((node.type=="text") || (node.type=="password"))) {return false;}
		} 
		document.onkeypress = stopRKey; 
	</script>
	<style type="text/css">
	.inputBox {
		height: 20px;
		width: 95%;
	}
	</style>
</head>
<body>
<table width="90%"  border="0" cellspacing="0" cellpadding="0"  align="center">
<tr>
	<td>
		<%@include file="menus.jsp"%>
	</td>
</tr>
<tr>
	<td>
<%  boolean isEditable=false,disp=true,readOnly=false;%>
<%
	String Data[][] = new String[0][0];
	String proposalStatus="",confirmStatus="";
	if(request.getParameter("proposalno")!=null){
		Data=beans.getProposalStatus(request.getParameter("proposalno"));
		defaultClauses = beans.getDefaultClauses(request.getParameter("proposalno"));
	}else if((String)session.getAttribute("proposalNo")!=null){
		Data=beans.getProposalStatus((String)session.getAttribute("proposalNo"));
		defaultClauses = beans.getDefaultClauses((String)session.getAttribute("proposalNo"));
	}
	if(Data.length>0){
		proposalStatus=Data[0][0]==null?"Normal":Data[0][0];
		confirmStatus=Data[0][1]==null?"NO":Data[0][1];
	}
	String loginId = (String) session.getAttribute("user");
	String currenctDetials[][] = new String[0][0];
	currenctDetials = newCover.getCurrencyName(actualBranch);
	if(currenctDetials.length>0)
	{
		session.setAttribute("currencyType",(currenctDetials[0][0]!=null?currenctDetials[0][0]:"AED"));
		session.setAttribute("decimalPlace",(currenctDetials[0][1]!=null?currenctDetials[0][1]:"2"));
	}
		String radtype=request.getParameter("radtype")==null?"":request.getParameter("radtype");
		String currencyType = (String) session.getAttribute("currencyType");
		String decimalPlace = (String) session.getAttribute("decimalPlace");
		String pathq = request.getContextPath();
		String basePaths = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+pathq+"/";
		String sdate=request.getAttribute("data1")==null?"":(String)request.getAttribute("data1");
		String edate=request.getAttribute("data2")==null?"":(String)request.getAttribute("data2");
		String	dobDay=request.getParameter("dobDay")==null?"0":request.getParameter("dobDay");
		String	dobMonth=request.getParameter("dobMonth")==null?"0":request.getParameter("dobMonth");
		String	dobYear=request.getParameter("dobYear")==null?"0":request.getParameter("dobYear");	
		String	dobDay1=request.getParameter("dobDay1")==null?"0":request.getParameter("dobDay1");
	    String		dobMonth1=request.getParameter("dobMonth1")==null?"0":request.getParameter("dobMonth1");
		String		dobYear1=request.getParameter("dobYear1")==null?"0":request.getParameter("dobYear1");
		String pid = request.getParameter("pid")==null?"":request.getParameter("pid");
		String renewalYN = request.getParameter("renewalYN")==null?"":request.getParameter("renewalYN");
		String endtYN = request.getParameter("endtYN")==null?"":request.getParameter("endtYN");
		java.util.Calendar calendar	 = java.util.Calendar.getInstance();
		
		String channeldetails[][] = new String[0][0];
		String channelIdentifier = "";
		String currency = request.getParameter("currency") == null ? "0"
				: request.getParameter("currency");
		
		
		String currencyId =  "0";
		String exchangeRate = "";
		HashMap brokerDetails = (HashMap) session
						.getAttribute("BrokerDetails");
		String cid = "";
		
		if (brokerDetails.size() > 0) {
			cid = (String) brokerDetails.get("Orgination");
			}
		try {
			String[][] currRateDetails = premiumInputs
					.getCurrencyRates(currencyId, cid);
			if (currRateDetails.length > 0) {
				if (currRateDetails[0][0] == null
						|| ""
								.equalsIgnoreCase(currRateDetails[0][0])) {
				} else {
					exchangeRate = currRateDetails[0][0];
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		channeldetails = (String[][]) premiumInputs.getCurrencyDetails(cid);
					
		if(sdate.length()>0 && edate.length()>0)
		{
			dobDay=sdate.substring(0,sdate.indexOf("-"));
			dobMonth=sdate.substring((sdate.indexOf("-")+1),sdate.lastIndexOf("-"));
			dobYear=sdate.substring((sdate.lastIndexOf("-")+1),sdate.length());

			dobDay1=edate.substring(0,edate.indexOf("-"));
			dobMonth1=edate.substring((edate.indexOf("-")+1),edate.lastIndexOf("-"));
			dobYear1=edate.substring((edate.lastIndexOf("-")+1),edate.length());
		}
		else if(request.getAttribute("errorDetail")==null)
		{
										
			dobDay=""+calendar.get(java.util.Calendar.DATE);
			dobMonth=""+(calendar.get(java.util.Calendar.MONTH)+1);
			dobYear=""+calendar.get(java.util.Calendar.YEAR);

			dobDay1=""+calendar.get(java.util.Calendar.DATE);
			dobMonth1=""+(calendar.get(java.util.Calendar.MONTH)+1);
			dobYear1=""+calendar.get(java.util.Calendar.YEAR);
		}
		if("GUEST".equalsIgnoreCase(loginIds)){
			dobDay=""+calendar.get(java.util.Calendar.DATE);
			dobMonth=""+(calendar.get(java.util.Calendar.MONTH)+1);
			dobYear=""+calendar.get(java.util.Calendar.YEAR);

			dobDay1=""+calendar.get(java.util.Calendar.DATE);
			dobMonth1=""+(calendar.get(java.util.Calendar.MONTH)+1);
			dobYear1=""+(calendar.get(java.util.Calendar.YEAR)+1);
		}
%>

<!-- This is for Declaration   -->

<%!
	String[][] BrokerNames=new String[0][0];
	String directLoginId = "";
	String[][] executiveIdList = new String[0][0];
	String[][] businessTypeList=new String[0][0];
	String[] backDays=new String[5];
	String action=null;
	String datas[]=new String[45];
	String dobDay = "";
	String dobDay1 = "";
	String dobMonth = "";
	String dobMonth1 = "";
	String dobYear = "";
	String dobYear1 = "";
	String proCommission = "";
	String wsrc = "";
	String defaultClauses = "";
	String BROKER_USER_ID = "";
	String issuance="";
	String minPremiumIssuance = "";
	String currencyValue="";
	String currency= "", minPreMulType="", minPreMul="";
	String lossDetail= "", claimRatio="", additionalInfo="",guestLogin="";
	String executiveId = "";
	String debitType = "";
%>
<!-- This is for Declaration   -->

<!-- This All are get from the Table -->
<%
			
if(request.getParameter("coverNo")!=null)
	session.setAttribute("openCoverNo",request.getParameter("coverNo"));

if(request.getParameter("proposalno")!=null)
	session.setAttribute("proposalNo",request.getParameter("proposalno"));


String s = null;
try
{
	String openMode = request.getParameter("freshMode");
	openMode = openMode == null ? "" :openMode;
	if("fresh".equalsIgnoreCase(openMode))
	{
		session.removeAttribute("openCoverNo");
		session.removeAttribute("proposalNo");
		session.removeAttribute("missippiCode");
	}
}
catch(Exception e)
{
	e.printStackTrace();
}
	BrokerNames=newCover.getBrokers(actualBranch);
	directLoginId=newCover.getDirectLoginId(actualBranch);
	executiveIdList = newCover.getexecutiveIdList();
	businessTypeList=new PremiumInputsBean().getConstantInfo("142",actualBranch.replaceAll("'",""));
	
	backDays[0]="5";backDays[1]="10";backDays[2]="12";backDays[3]="15";backDays[4]="25";  //Hardcoded
	action="Quotation.jsp";	
	if(request.getAttribute("errorDetail")!=null)
	{
		datas[0]=request.getParameter("brokerId")==null?"":request.getParameter("brokerId");
		datas[2]=request.getParameter("type")==null?"":request.getParameter("type");
		datas[1]=(request.getParameter("customerName")==null?"":request.getParameter("customerName")).replaceAll("\"","&#34;");
		datas[3] = request.getParameter("customerId")==null?"":request.getParameter("customerId");
		//datas[2] = request.getParameter("customer_Id")==null?"":request.getParameter("customer_Id");
		//datas[3]=request.getParameter("customerName")==null?"":request.getParameter("customerName");
		datas[4]=request.getParameter("backDays")==null?"":request.getParameter("backDays");
		datas[5]=request.getParameter("estimateAmount")==null?"":request.getParameter("estimateAmount");
		//datas[6]=request.getParameter("rsaValue")==null?"":request.getParameter("rsaValue");
		//datas[7]=request.getParameter("no_ofCompany")==null?"":request.getParameter("no_ofCompany");
		datas[8]=request.getParameter("crossVoyage")==null?"":request.getParameter("crossVoyage");
		datas[9]=request.getParameter("voyageValue")==null?"":request.getParameter("voyageValue");
		datas[10]=request.getParameter("freeText")==null?"":request.getParameter("freeText");
		datas[11]=request.getParameter("commission")==null?"":request.getParameter("commission");
		datas[12]=request.getParameter("miniPremium")==null?"":request.getParameter("miniPremium");
		datas[13]=request.getParameter("missippiCode")==null?"":request.getParameter("missippiCode");
		datas[14]=request.getParameter("businessType")==null?"":request.getParameter("businessType");
		datas[15]=request.getParameter("missi_open_policyId")==null?"":request.getParameter("missi_open_policyId");
		//datas[16]=request.getParameter("impMiniPremium")==null?"":request.getParameter("miniPremium");
		//datas[17]=request.getParameter("expMiniPremium")==null?"":request.getParameter("miniPremium");
		//datas[23]=request.getParameter("crossMiniPremium")==null?"0":request.getParameter("miniPremium");
		proCommission=request.getParameter("proCommission")==null?"0":request.getParameter("proCommission");
		dobDay=request.getParameter("dobDay")==null?"0":request.getParameter("dobDay");
		dobMonth=request.getParameter("dobMonth")==null?"0":request.getParameter("dobMonth");
		dobYear=request.getParameter("dobYear")==null?"0":request.getParameter("dobYear");
		dobDay1=request.getParameter("dobDay1")==null?"0":request.getParameter("dobDay1");
		dobMonth1=request.getParameter("dobMonth1")==null?"0":request.getParameter("dobMonth1");
		dobYear1=request.getParameter("dobYear1")==null?"0":request.getParameter("dobYear1");
		datas[18] = proCommission;
		datas[19] = dobDay+"-"+dobMonth+"-"+dobYear;
		datas[20] = dobDay1+"-"+dobMonth1+"-"+dobYear1;
		datas[21] = request.getParameter("currency")==null?"0":request.getParameter("currency");
		currencyValue = request.getParameter("currencyValue")==null?"0":request.getParameter("currencyValue");
		currency = request.getParameter("currency")==null?"0":request.getParameter("currency");
		issuance = request.getParameter("issuanceFee")==null?"0":request.getParameter("issuanceFee");
		minPremiumIssuance = request.getParameter("minPremiumIssuance")==null?"0":request.getParameter("minPremiumIssuance");
		wsrc = request.getParameter("wsrc")==null?"N":request.getParameter("wsrc");
		minPreMulType = request.getParameter("minPreMulType")==null?"":request.getParameter("minPreMulType");
		minPreMul = request.getParameter("minPreMul")==null?"":request.getParameter("minPreMul");
		datas[38] = request.getParameter("lossDetail")==null?"":request.getParameter("lossDetail");
		datas[39] = request.getParameter("claimRatio")==null?"":request.getParameter("claimRatio");
		datas[40] = request.getParameter("additionalInfo")==null?"":request.getParameter("additionalInfo");
		defaultClauses = request.getParameter("defaultClauses")==null?"N":request.getParameter("defaultClauses");
		executiveId = request.getParameter("executiveId")==null?"":request.getParameter("executiveId");
		debitType = request.getParameter("debitType")==null?"":request.getParameter("debitType");
		datas[44]=request.getParameter("utilizedAmount")==null?"0":request.getParameter("utilizedAmount");
	}
	else
	{
			if((String)session.getAttribute("proposalNo")!=null)
			{
				datas=newCover.getEditModeValue((String)session.getAttribute("proposalNo"));
				if(datas.length >0) // wsrc
				{
					wsrc = datas[21];
					BROKER_USER_ID = datas[22];
					dobDay=datas[24];
					dobMonth=datas[25];
					dobYear=datas[26];
					dobDay1=datas[27];
					dobMonth1=datas[28];
					dobYear1=datas[29];
					currency = datas[30];
					currencyValue  = datas[31];
					issuance = datas[32];
					minPremiumIssuance = datas[33];
					minPreMulType = datas[34];
					minPreMul = datas[35];
					renewalYN=datas[36];
					endtYN=datas[37];
					lossDetail=datas[38]==null?"":datas[38];
					claimRatio=datas[39]==null?"":datas[39];
					additionalInfo=datas[40]==null?"":datas[40];
					guestLogin=datas[41]==null?"":datas[41];
					executiveId = datas[42]==null?"":datas[42];
					debitType =  datas[43]==null?"":datas[43];
					System.out.println("GUST LOGIN STATUS===>"+guestLogin);
				}
			}
			else
			{	
				for(int i=0;i<datas.length;i++)
				{
					datas[i]="";
				}
				currency ="";
				currencyValue="";
				issuance="";
				minPremiumIssuance="";
				minPreMulType = "";
				minPreMul = "";
				lossDetail= "";
				claimRatio= "";
				additionalInfo= "";
				executiveId = "5";
			}
	}
	if(!radtype.equals(""))
	{
	if(radtype.equals("O"))
	{
	datas[2]="One Stop";
	}
	else{
	datas[2]="Standard";
	}
	}
    if("GUEST".equalsIgnoreCase(loginIds)){
	datas[2]="One Stop";
	}
	session.setAttribute("wsrc1",wsrc);
	session.setAttribute("BROKERUSERID",BROKER_USER_ID);// new July 30th
	///out.println("datas[2]:"+datas[2]);
	readOnly="Y".equalsIgnoreCase(renewalYN)||"R".equalsIgnoreCase(renewalYN);
%>
<!-- This All are get from the Table -->
<form name="form1" method="post" action="newOpenCover.jspa">
<input type="hidden" name="actionPath" value="<%=action%>">
		
		<% if(request.getAttribute("errorDetail")!=null)
		{
		%>
		
		<table  align="center" border="0" cellpadding="0" cellspacing="1" width="100%">
			<tr>
			<td align="left" width="35%"></td>
	<td align="left"> <font color="red" size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=request.getAttribute("errorDetail")!=null?(String)request.getAttribute("errorDetail"):"&nbsp;"%></font>
											</td>
			</tr>
			</table>
		<%
		}
		%>
<table  align="center" border="0" cellpadding="2" cellspacing="2" width="100%"> 
	<tr>
		<td>
			<span class ="heading">New OpenCover Information</span>
			<div style="float: right">
				<span class ="heading"><%=(String)session.getAttribute("proposalNo")!=null?"Proposal NO:"+(String)session.getAttribute("proposalNo"):""%></span>
			</div>
		</td>
	</tr>
	<tr>
		<td>
			<table  align="center" border="0" cellpadding="2" cellspacing="2" width="100%">
				 <%if(!"GUEST".equalsIgnoreCase(loginIds)){%>
				 <tr>
				 	<td class="formtxtf1" width="25%">Business Type&nbsp;<FONT color=red>**</FONT></td>
				 	<td class="formtxtf1" width="25%">
				 		<select name="businessType" class="inputSelect" <%=session.getAttribute("openCoverNo")!=null?"disabled":""%>>
							<option value="Select">Select</option>
							<%for(int i=0;i<businessTypeList.length;i++){%>
								<option value="<%=businessTypeList[i][2]%>" <%=businessTypeList[i][2].equalsIgnoreCase(datas[14])?"selected":""%>><%=businessTypeList[i][1]%></option>
							<%}%>
						</select>
				 	</td>
				 	<td class="formtxtf1" width="25%">Open Cover Type&nbsp;<FONT color=red>**</FONT></td>
				 	<td class="formtxtf1" width="25%">
				 		<input type="radio" name="type" value="One Stop" onclick="divdis(this.value)" <%="One Stop".equalsIgnoreCase(datas[2])?"checked":""%> >One Stop OpenCover<br/>
			  			<input type="radio" name="type" value="Standard" onclick="divdis(this.value)"  <%=("Standard".equalsIgnoreCase(datas[2])||"".equalsIgnoreCase(datas[2]))?"checked":""%> >Standard OpenCover
				 	</td>		 	
				 </tr>
				 <tr>
				 	<td class="formtxtf1">Select Broker&nbsp;<FONT color=red>**</FONT></td>
				 	<td class="formtxtf1" width="25%">
				 		<select name="brokerId" id="brokerId" onChange="resett(this.form);toggleDebitType(this.value);ToggleCommission();" class="inputSelect">
							<option value="Select">Select</option>
							<%for(int i=0;i<BrokerNames.length;i++) { %>
								<option value="<%=BrokerNames[i][1]%>" <%=BrokerNames[i][0].equalsIgnoreCase(datas[0])?"selected":
									(BrokerNames[i][1].equalsIgnoreCase(datas[0])?"selected":"")%>><%=BrokerNames[i][0]%></option>
							<%}
							try
							{%>
						</select>	
				 	</td>					 	
				 	<td class="formtxtf1" width="25%">
						<div id="executiveIdLabel">
							Sales Executive&nbsp;<FONT color=red>**</FONT>
						</div>	
					</td>
					<td class="formtxtf1" width="25%">
						<div id="executiveIdSelect">
							<select name="executiveId" id="executiveId" class="inputSelect" onclick="ToggleCommission();">
								<option value="">Select</option>
								<%for(int i=0;i<executiveIdList.length;i++) { %>
									<option value="<%=executiveIdList[i][0]%>" <%=executiveIdList[i][1].equalsIgnoreCase(executiveId)?"selected":
										(executiveIdList[i][0].equalsIgnoreCase(executiveId)?"selected":"")%>><%=executiveIdList[i][1]%></option>
								<%}%>
							</select>
						</div>
					</td>
				 </tr>
				<%}catch(Exception e){System.out.println("<br>  ERROR");}%>
				<%}%>
				<%try{%>
				<tr>
					<td class="formtxtf1" width="25%">Customer Name&nbsp;<FONT color=red>**</FONT></td>
					<td class="formtxtf1" width="25%">
						<div class="inputAppend">
							<input type="text" class="inputBox" style="border: none; background: transparent; width: 80%; float: left;" name="customerName" value="<%=datas[1]%>" readOnly>
						  <%if(!"GUEST".equalsIgnoreCase(loginIds)){%>	
						   <input name="chooseCustomer" value="..."  class="inputButton" onClick="showCustomer()" type="button">
						   <%}else{%>
						   <input name="chooseCustomer" value="..."  class="inputButton" onClick="guestCustomer()" type="button">	
						   <%}%>
						</div>
					</td>
					<td class="formtxtf1" width="25%">Debit To&nbsp;<FONT color=red>**</FONT></td>
				 	<td class="formtxtf1" width="25%">
				 		<input type="radio" name="debitType" id="debitTypeB" value="Broker" <%="Broker".equalsIgnoreCase(debitType)?"checked":""%> >Broker<br/>
			  			<input type="radio" name="debitType" id="debitTypeC" value="Customer"  <%=("Customer".equalsIgnoreCase(debitType)||"".equalsIgnoreCase(debitType))?"checked":""%> >Customer
				 	</td>
				</tr>
				<tr>
					<td class="formtxtf1">Open Cover Start Date</td>
					<td class="formtxtf1">
						<SELECT name=dobDay <%=readOnly?"DISABLED":""%> class="inputSelect" style="width: 33%; float: left;">  
					       <OPTION value='0' >DD</OPTION>
							   <%
								String sday1="";
								for(int i=1;i<=31;i++)
								{
									if(Integer.parseInt(dobDay)==i)
										sday1="selected";
									else
										sday1="";
								%>
								<option value=<%=i%> <%=sday1%>><%=i%></option>
								<%}%>
						   </SELECT>
						   <SELECT class="inputSelect" name=dobMonth <%=readOnly?"DISABLED":""%> style="width: 33%; float: left;">
						       <OPTION value='0'>MMM</OPTION>
						       <%
								String smon1="";
								String[] months={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
								for(int i=1;i<=months.length;i++)
								{
									if(Integer.parseInt(dobMonth)==i)
										smon1="selected";
									else
										smon1="";
								%>
								<option value=<%=i%> <%=smon1%> ><%=months[i-1]%></option>
								<%}%>
							  </SELECT>
							  <SELECT class="inputSelect" name=dobYear <%=readOnly?"DISABLED":""%> style="width: 33%; float: left;">
						       <OPTION value='0' >YYYY</OPTION>
						       <%
									java.util.Date dt=new java.util.Date();
									int maxYear=dt.getYear()+1900 ;
									String syear1="";
									for(int i=2000;i<(maxYear+2);i++)
									{
										if(Integer.parseInt(dobYear)==i)
											syear1="selected";
										else
											syear1="";
								%>
								<option value=<%=i%> <%=syear1%>><%=i%></option>
								<%}%>
							  </SELECT>
					</td>
					<%if(!"GUEST".equalsIgnoreCase(loginIds)){%>
					<td class="formtxtf1" ><div id="endDateLabel">Open Cover End Date</div></td>
					<td class="formtxtf1"><div id="endDate">
						<SELECT name="dobDay1" class="inputSelect" style="width: 33%; float: left;">
					       <OPTION value='0' >DD</OPTION>
						   <%
							   String sday2="";
								for(int i=1;i<=31;i++)
								{
									if(Integer.parseInt(dobDay1)==i)
										sday2="selected";
									else
										sday2="";
							%>
							<option value=<%=i%> <%=sday2%>><%=i%></option>
							<%}%>
						   </SELECT>
						  <SELECT name="dobMonth1" class="inputSelect" style="width: 33%; float: left;">
					       <OPTION value='0' >MMM</OPTION>
					       <%
							String smon2="";
							String[] months1={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
							for(int i=1;i<=months1.length;i++)
							{
								if(Integer.parseInt(dobMonth1)==i)
									smon2="selected";
								else
									smon2="";
								
							%>
							<option value=<%=i%> <%=smon2%> ><%=months[i-1]%></option>
							<%}%>
						  </SELECT>
						 <SELECT name="dobYear1" class="inputSelect" style="width: 33%; float: left;">
					       <OPTION value='0' >YYYY</OPTION>
					       <%
							   java.util.Date dt1=new java.util.Date();
								int maxYear1=dt1.getYear()+1900 ;
							   String syear2="";
								for(int i=2000;i<(maxYear1+11);i++)
								{
									if(Integer.parseInt(dobYear1)==i)
										syear2="selected";
									else
										syear2="";
							%>
						<option value=<%=i%> <%=syear2%>><%=i%></option>
						<%}%>
					 </SELECT>
					</div></td>
					<%}%>
					<%if("GUEST".equalsIgnoreCase(loginIds)){%>	
						  <td  class="formtxtf1" colspan="2">
						  <input type="hidden" name="dobDay1" value="<%=dobDay%>"/>
						  <input type="hidden" name="dobMonth1" value="<%=dobMonth%>"/>
						  <input type="hidden" name="dobYear1" value="<%=dobYear1%>"/>
						  </td>
					 <%}%>
				</tr>
			</table>					 
			<%
			}
			catch(Exception e)
			{
				System.out.println("<br>  ERROR");
			}
			%>
		</td>
	</tr>
	<tr>
		<td>
			<span class ="heading">Extra Information</span>			
		</td>
	</tr>
	<tr>
		<td>
			<table  align="center" border="0" cellpadding="2" cellspacing="2" width="100%">
				<tr>
					<td class="formtxtf1" width="25%">Annual Estimated Turnover&nbsp;<FONT color=red>**</FONT></td>
					<td class="formtxtf1" width="25%">
						<input type="text" name="estimateAmount" class="inputBox" value="<%=datas[5]%>" maxlength="15">
					</td>
					<td class="formtxtf1" width="25%">Utilized Amount&nbsp;<FONT color=red>**</FONT></td>
					<td class="formtxtf1" width="25%">
						<input type="text" class="inputBox" name="utilizedAmount" value="<%="".equals(datas[44])?"":datas[44]%>" maxlength="15">
					</td>
				</tr>
				<tr>
					<td class="formtxtf1" width="25%">Currency&nbsp;<FONT color=red>**</FONT></td>
					<td class="formtxtf1" width="25%">
						<select name='currency' id='currency' class='inputSelect' style="width: 63%; float: left;" ONCHANGE='return showValue(this.value)'>
							<option value='0'>
								Select
							</option>
							<%	for (int i = 0; i < channeldetails.length; i++) {
									if (channeldetails[i][4].equalsIgnoreCase(currency)) {
										channelIdentifier = "selected";
									} else {
										channelIdentifier = "";
									}
							%>
							<option value=<%=channeldetails[i][4]%>
							<%=request.getParameter("currency") == null ? (currency== null ? (currency
							.equalsIgnoreCase(channeldetails[i][4]) ? "selected"
							: "")
							: currency
									.equalsIgnoreCase(channeldetails[i][4]) ? "selected"
									: "")
							: (channeldetails[i][4]
									.equalsIgnoreCase(request
											.getParameter("currency")) ? "selected"
									: "")%>>
							<%=channeldetails[i][1]%>
							</option>
							<% } %> 
						</select>							
						<%if(!"GUEST".equalsIgnoreCase(loginIds)){%>	
						<input type="text" size="3" readonly
							name="currencyValue" class='inputBox' style="width: 30%; float: left;"
							value='<%=request.getParameter("currencyValue") == null ? currencyValue : request
							.getParameter("currencyValue")%>'>
						<%}%>
						<% for (int i = 0; i < channeldetails.length; i++) { %>
							<input type="hidden" name="<%=channeldetails[i][4]%>" id="<%=channeldetails[i][4]%>"												value="<%=channeldetails[i][0]%>" />
						<% } %>							
					</td>
					<td class="formtxtf1" width="25%"></td>
					<td class="formtxtf1" width="25%"></td>
				</tr>
				<!--<tr  >
				  <td >Emirates Shared Percentage&nbsp;<FONT color=red>**</FONT></td>
				  <td><input type="text" class="fde1" name="rsaValue" readonly onchange='TextSubmit()' value="<%=100%>"></td>
				  <td >No.of&nbsp;Co-Insurance&nbsp;Company&nbsp;<FONT color=red>**</FONT></td>
				   <td><input type="text" class="fde1"name="no_ofCompany"   value="<%=datas[7]%>"></td>
				</tr>  -->
				<%
				String ycheck="",ncheck="";
				System.out.println("Checked===>"+datas[8]);
				if("Y".equalsIgnoreCase(datas[8]))
				{
				ycheck="checked";
				System.out.println("Checked===>"+ycheck);
				}
				else
				ncheck="checked";
				
				%>
				<tr>
					<td class="formtxtf1">Cross Voyage</td>
					<td class="formtxtf1">
						<input name="crossVoyage" type="radio" value="Y" onclick='RadioSubmit("Y")'<%=ycheck%>>YES
			    		<input name="crossVoyage" type="radio" value="N" onclick='RadioSubmit("N")' <%=ncheck%>>NO
					</td>
					<td class="formtxtf1">Cross Voyage(%)</td>
					<td class="formtxtf1">
						<input type="text" class="inputBox" name="voyageValue" value="<%=datas[9]%>" <%="Y".equalsIgnoreCase(datas[8])?"enabled":"disabled"%> maxlength="3">
					</td>
				</tr>					
			<%
				String ycheck1="",ncheck1="";
				System.out.println("Checked===>"+datas[10]);
				if("Y".equalsIgnoreCase(datas[10])) {
					ycheck1="checked";
					System.out.println("Checked===>"+ycheck);
				}
				else
				ncheck1="checked";
			%>
				<%if(!"GUEST".equalsIgnoreCase(loginIds)){%>
				<tr>
					<td class="formtxtf1">Free Text Allowed</td>
					<td class="formtxtf1">
						<input name="freeText" type="radio" value="Y" <%=ycheck1%>>YES
      					<input name="freeText" type="radio" value="N" <%=ncheck1%>>NO
					</td>
					<td class="formtxtf1">
						<span id="commissionText">Commission(%)</span>
						&nbsp;<FONT color=red></FONT></td>
					<td class="formtxtf1">
						<input type="text" name="commission" id="commission" class="inputBox" value="<%=datas[11]%>" maxlength="6">
					</td>
				</tr>
				<%}%>
				<%
			try
			{
			%>
			<!--<tr >
			<td >Minimum&nbsp;Premium(<%=currencyType%>)&nbsp;<FONT color=red>**</FONT></td>
			<td><input type="text" name="miniPremium"  class="fde1" value="<%=datas[12]%>"></td>
			<td >Import&nbsp;Minimum&nbsp;Premium(<%=currencyType%>)&nbsp;<FONT color=red>**</FONT></td>
			<td><input type="text" name="impMiniPremium" class="fde1" value="<%=datas[16]%>"></td>
			</tr>
			<tr >
			<td >Export&nbsp;Minimum&nbsp;Premium(<%=currencyType%>)&nbsp;<FONT color=red>**</FONT></td>
			<td><input type="text" name="expMiniPremium"  class="fde1" value="<%=datas[17]%>"></td>
			<td >Cross&nbsp;Voyage&nbsp;Minimum&nbsp;Premium(<%=currencyType%>)&nbsp;<FONT color=red>**</FONT></td>
			<td><input type="text" name="crossMiniPremium" class="fde1" value="<%=datas[23]%>"></td>
			</tr>  -->
			<%}catch(Exception e)
			{
				//out.println("erer"+e.toString());
			}
				String policyNo="", renNo="";
				if(datas[13]!=null && datas[13].indexOf("-")!=-1)
				{
					policyNo=datas[13].substring(0, datas[13].indexOf("-"));
					renNo=datas[13].substring(datas[13].indexOf("-")+1, datas[13].length());
				}
				else if(datas[13]!=null && datas[13].length()>0) {
					policyNo=datas[13];
					renNo = "";
				}
				%>
				<%if(!"GUEST".equalsIgnoreCase(loginIds)){%>
				<tr>
					<td class="formtxtf1">Existing Core Application Policy No.&nbsp;<FONT color=red></FONT></td>
					<td class="formtxtf1">
						<input type="hidden" name="missippiCode" class="fde1" value="<%=datas[13]%>" <%= session.getAttribute("openCoverNo")!=null?"readonly":""%> maxlength="20">
						<%--
						<input type="text" name="policyNo" class="inputBox" style="width:62%; float:left;" value="<%=policyNo%>" <%= session.getAttribute("openCoverNo")!=null || "Y".equalsIgnoreCase(renewalYN) || "Y".equalsIgnoreCase(endtYN)?"readonly":""%> maxlength="20"><span style="float:left; line-height: 30px;">-</span><input type="text" name="renNo" class="inputBox" value="<%=renNo%>" <%=session.getAttribute("openCoverNo")!=null || "Y".equalsIgnoreCase(renewalYN) || "Y".equalsIgnoreCase(endtYN)?"readonly":""%> maxlength="3" style="width: 25%; float: left;">
						--%>
						<input type="text" name="policyNo" class="inputBox" style="width:62%; float:left;" value="<%=policyNo%>" <%="Y".equalsIgnoreCase(endtYN)?"readonly":""%> maxlength="30"><span style="float:left; line-height: 30px;">-</span><input type="text" name="renNo" class="inputBox" value="<%=renNo%>" <%="Y".equalsIgnoreCase(endtYN)?"readonly":""%> maxlength="3" style="width: 25%; float: left;">
					</td>
					<!--<td >Core Application&nbsp;Open&nbsp;Policy&nbsp;Id&nbsp;<FONT color=red>**</FONT></td>
			  		<td><input type="text" name="missi_open_policyId" class="fde1" value="<%=datas[15]%>"/></td>-->
					<td class="formtxtf1">Minimum&nbsp;Premium&nbsp;(<%=currencyType%>)&nbsp;<FONT color=red>**</FONT></td>
					<td class="formtxtf1"><input type="text" name="miniPremium"  class="inputBox" value="<%=datas[12]%>" maxlength="9"></td>
				</tr>
				<!-- Added by Hari 16/07/2012 -->
				<tr>
					<td class="formtxtf1">Issuance&nbsp;Fee(%)&nbsp;<FONT color=red>**</FONT></td>
					<td class="formtxtf1">
						<input type="text" name="issuanceFee"  class="inputBox" value="<%= issuance%>" maxlength="10">
					</td>
					<td class="formtxtf1">Minimum&nbsp;Premium Issuance Fee&nbsp;<FONT color=red>**</FONT></td>
					<td class="formtxtf1">
						<input type="text" name="minPremiumIssuance"  class="inputBox" value="<%=minPremiumIssuance%>" maxlength="10">
					</td>
				</tr>
				<tr>
					<td class="formtxtf1">Multiple&nbsp;Declaration&nbsp;(Minimum Premium)</td>
					<td class="formtxtf1">
						<input name="minPreMulType" type="radio" value="I" <%="".equalsIgnoreCase(minPreMulType)||"I".equalsIgnoreCase(minPreMulType)?"checked":""%> onclick="declaration('I')">Individual&nbsp;&nbsp;
		     			<input name="minPreMulType" type="radio" value="M" <%="M".equalsIgnoreCase(minPreMulType)?"checked":""%> onclick="declaration('M')" >Multiple
					</td>
					<td class="formtxtf1">Multiple&nbsp;Declaration&nbsp;Minimum&nbsp;Premium&nbsp;(<%=currencyType%>)</td>
					<td class="formtxtf1"><input type="text" name="minPreMul"  class="inputBox" value="<%=minPreMul%>" maxlength="10"></td>
				</tr>
				<tr>
					<td class="formtxtf1">Number&nbsp;of&nbsp;Back&nbsp;date&nbsp;Allowed&nbsp;<FONT color=red>**</FONT></td>
					<td class="formtxtf1"><input type="text" name="backDays"  class="inputBox" value="<%=datas[4]%>" maxlength="10"></td>
					
					<td class="formtxtf1">Default Clauses</td>
					<td class="formtxtf1">
						<input name="defaultClauses" type="radio" value="Y" <%="Y".equalsIgnoreCase(defaultClauses)?"checked":""%> >Yes&nbsp;&nbsp;
		     			<input name="defaultClauses" type="radio" value="N" <%="".equalsIgnoreCase(defaultClauses)||"N".equalsIgnoreCase(defaultClauses)?"checked":""%>>No
					</td>
				</tr>
				<%}%>
				<%
				System.out.println("LOGIN STATUS===>"+loginIds);
				System.out.println("GUST LOGIN STATUS===>"+guestLogin);		
				System.out.println("MODE===>"+request.getParameter("freshMode")==null?"":request.getParameter("freshMode"));		
				%>
				<%if("GUEST".equalsIgnoreCase(loginIds) || ("GUEST".equalsIgnoreCase(guestLogin) && !"fresh".equalsIgnoreCase(request.getParameter("freshMode")==null?"":request.getParameter("freshMode")))){%>
				<tr>
					<td class="formtxtf1">Loss Details(Max 2000 chars)</td>
					<td class="formtxtf1">
						<textarea name="lossDetail"  rows="3" cols="35" style="width: 100%;" onkeyup="textLimit(this,1000)"><%=datas[38]%></textarea>
					</td>
					<td class="formtxtf1">Additional Info(Max 2000 chars)</td>
					<td class="formtxtf1">
						<textarea name="additionalInfo"  rows="3" cols="35" style="width: 100%;" onkeyup="textLimit(this,1000)"><%=datas[40]%></textarea>
					</td>
				</tr>
				<tr>
					<td class="formtxtf1">Claim Ratio(Max 2000 chars)</td>
					<td class="formtxtf1">
						<textarea name="claimRatio" rows="3" cols="35" style="width: 100%;" onkeyup="textLimit(this,1000)"><%=datas[39]%></textarea>
					</td>
					<td class="formtxtf1" colspan="2"></td>
				</tr>
				<%}%>
			<!-- End addition -->
			
			<!--<tr >
			  <td >Minimum&nbsp;Premium(<%=currencyType%>)&nbsp;<FONT color=red>**</FONT></td>
			<td colspan="3"><input type="text" name="miniPremium"  class="fde1" value="<%=datas[12]%>"></td>
			  <td >&nbsp;</td>
			  <td >&nbsp;</td>  
			</tr>
	
		-->
		
			</table>
			<input type="hidden" name="customerId" value="<%=datas[3]%>">
		</td>
	</tr>
</table>

		<%if("GUEST".equalsIgnoreCase(loginIds)){%>
			<input type="hidden" name="businessType" value="1"/>
			<!--<input type="hidden" name="brokerId" value="abudirect"/>-->
			<input type="hidden" name="brokerId" value="adnicdirect"/>
			<input type="hidden" name="type" value="One Stop"/>
			<input type="hidden" name="currencyValue" value="<%=request.getParameter("currencyValue") == null ? currencyValue : request.getParameter("currencyValue")%>"/>
			<input type="hidden" name="miniPremium" value="<%="".equalsIgnoreCase(datas[12])?"0":datas[12]%>"/>
			<input type="hidden" name="issuanceFee" value="<%="".equalsIgnoreCase(issuance)?"0":issuance%>"/>
			<input type="hidden" name="minPremiumIssuance" value="<%="".equalsIgnoreCase(minPremiumIssuance)?"0":minPremiumIssuance%>"/>
			<input type="hidden" name="backDays" value="<%="".equalsIgnoreCase(datas[4])?"0":datas[4]%>"/>
			<input type="hidden" name="commission" value="<%="".equalsIgnoreCase(datas[11])?"0":datas[11]%>"/>
			<input type="hidden" name="freeText" value="N"/>
			<input type="hidden" name="policyNo" value=""/>
			<input type="hidden" name="renNo" value=""/>
			<input type="hidden" name="missippiCode" value=""/>
			<input type="hidden" name="user" value="GUEST"/>
		<%}%>
		<input type="hidden" name="radtype" value="" id="radType" />
		<input type="hidden" name="wsrc" value="<%=wsrc%>" />
		<input type="hidden" name="renewalYN" value="<%=renewalYN%>" />
		<input type="hidden" name="endtYN" value="<%=endtYN%>" />
		<input type="hidden" name="proposalStatus" value="<%=proposalStatus%>" />
		<input type="hidden" name="confirmStatus" value="<%=confirmStatus%>" />
		<br>
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td align="center">
				<%if(!"GUEST".equalsIgnoreCase(loginIds)){%>
					<a href= "#" onClick='return back()'> <img src="<%=pathq%>/images/Back.jpg" > </a>
					&nbsp;&nbsp;&nbsp;
				<%}else{%>
					<a href= "#" onClick='return back1()'> <img src="<%=pathq%>/images/Back.jpg" > </a>
					&nbsp;&nbsp;&nbsp;					
				<%}%>
				<%if(session.getAttribute("openCoverNo")==null){ %>
					<a href="#" onClick= 'return SaveAction(1)'> <img src="<%=pathq%>/images/SaveQuote.jpg" > </a>
					&nbsp;&nbsp;&nbsp;
				<%} %>
				<a href="#" onClick='return SaveAction(2)'> <img src="<%=pathq%>/images/Proceed.jpg"></a>	
			</td>
		</tr>
        </table>
</form>
	</td>
</tr>
</table>
<script type="text/javascript">
function RadioSubmit(val) {
	//var val =	document.form1.crossVoyage.value;
	if(val == 'N') {
		document.form1.voyageValue.value='';
		document.form1.voyageValue.disabled=true;
	} else {
		document.form1.voyageValue.disabled=false;
	}
}
declaration("<%=minPreMulType%>");
function declaration(val) {
	if("M"==val) {
		document.form1.minPreMul.disabled = false;
	} else {
		document.form1.minPreMul.value = "";
		document.form1.minPreMul.disabled = true;
	}
}
	
function TextSubmit() {
	var val	=	document.form1.rsaValue.value;
	if(!isNaN(val)) {
		var vals		=	parseInt(val);
		if(vals>99) {
			//document.form1.no_ofCompany.value='0';
			//document.form1.no_ofCompany.disabled=true;
		} else {
			//document.form1.no_ofCompany.disabled=false;
		}
	} else {
		//document.form1.no_ofCompany.value='0';
		//document.form1.no_ofCompany.disabled=true;
	}
}
	
function resett(Form) {
	with(Form) {
		customerName.value="";
		customerId.value=null;
		if(brokerId.value=='RSABROKER123') {   // Hardcode
			commission.value='0';
			commission.disabled=true;
		} else {   // coding by marimuthu on 13th December 2007
			commission.value='';
			commission.disabled=false;
		}
	}
}

function SaveAction(id)
{
	if(id==1)
	{
		document.form1.actionPath.value="showOpencover.jsp";
	} 
	else
	{
		document.form1.actionPath.value='Quotation.jsp';
	}
	//if(document.form1.no_ofCompany.disabled	==	true)
	//{
		//document.form1.no_ofCompany.value		=	'0';
		//document.form1.no_ofCompany.disabled	=	false;
	//}
	if(document.form1.voyageValue.disabled		==	true)
	{
		//document.form1.voyageValue.value='0';
		document.form1.voyageValue.disabled		=	false;
		//document.form1.voyageValue.value		=	'';
	}
	if(document.form1.policyNo.value!='' || document.form1.renNo.value!='')
	{
		if(document.form1.renNo.value!='') {
			document.form1.missippiCode.value=document.form1.policyNo.value+'-'+document.form1.renNo.value;
		}
		else {
			document.form1.missippiCode.value=document.form1.policyNo.value;
		}
	}else{
	document.form1.missippiCode.value='';
	}
	document.form1.businessType.disabled=false;
	document.form1.dobDay.disabled=false;
	document.form1.dobMonth.disabled=false;
	document.form1.dobYear.disabled=false;
	document.form1.dobDay1.disabled=false;
	document.form1.dobMonth1.disabled=false;
	document.form1.dobYear1.disabled=false;
	document.getElementById('debitTypeB').disabled = false;
	document.getElementById('debitTypeC').disabled = false;
	document.getElementById("commission").disabled = false;
	document.form1.submit();
	return false;
}
		function showCustomer()
		{
			var broker=document.form1.brokerId.value;
			if(broker=='Select')
				alert('Please Select Broker');
			else
			{
			var URL = '';
			var windowName = "";
			
		URL='chooseCustomer.jsp?brokerId='+document.form1.brokerId.value+'&&customerId='+document.form1.customerId.value;

		var width  = screen.availWidth;
		var height = screen.availHeight;
		var h=350;
		var w=680;
		var features =
			'width='          + w +
			',height='		  + h +
			',left='		  + ((width - w - 0) * .4)  +
			',top='			  + ((height - h - 0) * .4) +
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
		}
function guestCustomer()
{
	var broker=document.form1.brokerId.value;
	var customerid=document.form1.customerId.value;
	if(broker=='Select')
		alert('Please Select Broker');
	else
	{
	var URL = '';
	var windowName = "";
	if(customerid==''){	
	URL='newCustomer.jsp?mode=new&&brokerId='+document.form1.brokerId.value+'&&customerId='+document.form1.customerId.value;
	}else{
	URL='newCustomer.jsp?mode=edit&&customers='+customerid;
	}

	var width  = screen.availWidth;
	var height = screen.availHeight;
	var h=350;
	var w=680;
	var features =
		'width='          + w +
		',height='		  + h +
		',left='		  + ((width - w - 0) * .4)  +
		',top='			  + ((height - h - 0) * .4) +
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
}
function back()
{
	document.form1.action="ShowOpenCoverEntry.jsp";
	document.form1.submit();
	return true;
}
function back1()
{
	document.form1.action="onlineProposal.jsp";
	document.form1.submit();
	return true;
}
function resetting()
{
	var val=document.form1.brokerId.value;
	if('RSABROKER123'==val)   //Hardcoded
	{
		document.form1.commission.value='0';
		document.form1.commission.disabled=true;

	}
}
resetting();

divdis('<%=datas[2]%>');
function divdis(rad) {
if(rad=='One Stop')
{
 document.getElementById("endDateLabel").style.visibility="visible";
 document.getElementById("endDate").style.visibility="visible";
}else
{
	document.getElementById("endDateLabel").style.visibility = "hidden";
 	document.getElementById("endDate").style.visibility = "hidden";
}
}
		   
function showValue(Field)
{
	var selected = document.getElementById(""+Field);
	with(selected)
	{
		document.form1.currencyValue.value=value;
	}
}
toggleDebitType('<%=datas[0]%>');
function toggleDebitType(val) {
	var directLoginId = "<%=directLoginId%>";
	if(val == directLoginId) {
		document.getElementById('debitTypeB').checked = false;
		document.getElementById('debitTypeC').checked = true;
		document.getElementById('debitTypeB').disabled = true;
		document.getElementById('debitTypeC').disabled = true;
		
	}
	else {
		document.getElementById('debitTypeB').disabled = false;
		document.getElementById('debitTypeC').disabled = false;
	}
}
try {
ToggleCommission();
}
catch(e){
console.log(e);
}
function ToggleCommission() {
	var directLoginId = '<%=directLoginId%>';
	var brokerId = document.getElementById("brokerId").value;
	var executiveId = document.getElementById("executiveId").value;
	if(directLoginId == brokerId && executiveId!="5") {
		document.getElementById("commissionText").innerHTML = "Sales Executive Commission(%)";
		document.getElementById("commission").disabled = false;
	}
	else if(brokerId!="" && brokerId != "Select" && directLoginId != brokerId) {
		document.getElementById("commissionText").innerHTML = "Broker Commission(%)";
		document.getElementById("commission").disabled = false;
	}
	else {
		document.getElementById("commissionText").innerHTML = "Commission(%)";
		document.getElementById("commission").value = "0";
		document.getElementById("commission").disabled = true;
	}
}
</script>
</body>
</html>