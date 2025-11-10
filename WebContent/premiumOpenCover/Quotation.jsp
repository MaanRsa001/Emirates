<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@page import="com.maan.premium.DAO.PremiumInputsBean"%>
<%String cpath1 = request.getContextPath(); %>
<!DOCTYPE HTML>
<html>
<head>
	<title>Emirates</title>
	<jsp:useBean id = "OCE" class = "com.maan.opencover.bean.opencoverEntry">
		<jsp:setProperty name = "OCE" property = "*"/>
	</jsp:useBean>

	<jsp:useBean id="newCover" class="com.maan.opencover.bean.newCoverBean">
		<jsp:setProperty name="newCover" property="*"/>
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
<%try {%>
<tr>
<td>
<%!
	String mode="";
	String modeValue="";
	String seaValues="";
	String currencyId="";
	String currencyValue="";
	String coverTypeId="";
	boolean isEdit = false;
	java.util.StringTokenizer tokens = null;
	String []datas = new String[23];
	String[][] getExisting = new String[0][0];
	String[][] modeMaster = new String[0][0];
	String[][] coverMaster = new String[0][0];
	String[][] commodities = new String[0][0];
	String[][] extraCover = new String[0][0];
%>

<jsp:useBean id="beans" class="com.maan.opencover.bean.openCoverQuotation">
	<jsp:setProperty name="beans" property="*"/>
</jsp:useBean>
<form name="Quotation" method="post" action="Quotation.jspa">
<%
	String Data[][] = new String[0][0];
	String proposalStatus="",confirmStatus="";
	if(request.getParameter("proposalno")!=null){
	Data=beans.getProposalStatus(request.getParameter("proposalno"));
	}else if((String)session.getAttribute("proposalNo")!=null){
		Data=beans.getProposalStatus((String)session.getAttribute("proposalNo"));
	}
	if(Data.length>0){
	proposalStatus=Data[0][0]==null?"Normal":Data[0][0];
	confirmStatus=Data[0][1]==null?"NO":Data[0][1];
	}
	String chkProposalNo = (String)session.getAttribute("proposalNo");
	
	if(session.getAttribute("modeMaster")!=null || session.getAttribute("coverMaster")!=null) {
		if(request.getAttribute("error")==null) {
			session.removeAttribute("modeMaster");
			session.removeAttribute("coverMaster");
		}
	}		
	
	//String adminBranch = braCode;
	String adminBranch = (String)session.getAttribute("adminBranch");
	//Belonging branch
	String belongingBranch = (String)session.getAttribute("BelongingBranch");
		

	if(adminBranch.indexOf("'")!=-1)
		adminBranch = adminBranch.replaceAll("'","");
	String cid = (String) session.getAttribute("AdminCountryId");
	String currencyType = (String) session.getAttribute("currencyType");
	beans.setProposalNo((String)session.getAttribute("proposalNo"));
	String type=(String) session.getAttribute("type");
	//out.println("type:"+type);
	String brokerId="", customerId="", customer="", originalInsured="";
	String[][] additionalInsuredInfo=beans.getAdditionalInsuredInfo((String)session.getAttribute("proposalNo"));
	
	if(additionalInsuredInfo!=null && additionalInsuredInfo.length>0) {
		brokerId=additionalInsuredInfo[0][0]==null?"":additionalInsuredInfo[0][0];
		customerId=additionalInsuredInfo[0][1]==null?"":additionalInsuredInfo[0][1];
		customer=additionalInsuredInfo[0][2]==null?"":additionalInsuredInfo[0][2];
		originalInsured=additionalInsuredInfo[0][3]==null?"":additionalInsuredInfo[0][3];
	}
	
	session.setAttribute("mode","edit");
	String action="Premium.jsp";
	String wsrc = "";
	wsrc = request.getParameter("wsrc")==null?"":request.getParameter("wsrc");
	/*wsrc = (String) session.getAttribute("wsrc1");
	wsrc = wsrc==null?"":wsrc;*/
	String CountryRemarks="";
	if(wsrc.length()<=0)
		wsrc = beans.getWSRCOpt((String)session.getAttribute("proposalNo"));
	//
	if(!isEdit && request.getAttribute("error")!=null) {
		datas[0]=request.getParameter("startDay");
		datas[1]=request.getParameter("startMonth");
		datas[2]=request.getParameter("startYear");
		datas[3]=request.getParameter("endDay");
		datas[4]=request.getParameter("endMonth");
		datas[5]=request.getParameter("endYear");
		datas[6]=request.getParameter("transit_country");
		datas[7]=request.getParameter("destination_country");
		datas[8]=request.getParameter("commodity_TA");
		datas[9]=request.getParameter("TotalCommudity");
		datas[10]=request.getParameter("commodityIds");
		datas[11]=request.getParameter("transit_countryId");
		datas[12]=request.getParameter("totalTransitId");
		datas[13]=request.getParameter("destination_countryId");
		datas[14]=request.getParameter("totalDestinationId");
		datas[15]=request.getParameter("effectiveDay");
		datas[16]=request.getParameter("effectiveMonth");
		datas[17]=request.getParameter("effectiveYear");
		wsrc = request.getParameter("wsrc")==null?"":request.getParameter("wsrc");
		datas[18] = request.getParameter("saleTermName");// Sale Term Name
		datas[19] = request.getParameter("saleTermId");// Sale Term Id	
		datas[20]=request.getParameter("txtarCryRemarkes")==null?"":request.getParameter("txtarCryRemarkes");;
		datas[21] = request.getParameter("toleranceId");// Sale Term Id	
		datas[22] = request.getParameter("tolerance");// Sale Term Id	
	} else {
		datas=beans.getExistingCountry(belongingBranch);
	}
	
	extraCover=beans.getExtraCover(belongingBranch);
	commodities=beans.getCommodity(belongingBranch);
	if(session.getAttribute("modeMaster")==null || session.getAttribute("coverMaster")==null) {
		modeMaster=beans.getModeMaster(belongingBranch);
		coverMaster=beans.getCoverMaster(belongingBranch);
		session.setAttribute("modeMaster",modeMaster);
		session.setAttribute("coverMaster",coverMaster);
		//session.setAttribute("commodities",commodities);
	} else {
		modeMaster=(String[][])session.getAttribute("modeMaster");
		coverMaster=(String[][])session.getAttribute("coverMaster");
		//commodities=(String[][])session.getAttribute("commodities");	
	}

	Date date = new Date();
	String rptDate;

	SimpleDateFormat YearOnly = new SimpleDateFormat("yyyy");
	SimpleDateFormat MonthOnly = new SimpleDateFormat("MMM");
	SimpleDateFormat DayOnly = new SimpleDateFormat("dd");
	
	String YearString = YearOnly.format(date);
	String MonthString = MonthOnly.format(date);
	String DayString = DayOnly.format(date);
	String[] Mon={"JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC"};
	String[][] channeldetails = (String[][]) new PremiumInputsBean().getCurrencyDetails(cid);
%>
<table  align="center" border="0" cellpadding="2" cellspacing="2" width="100%">
	<tr>
		<td width="25%"></td>
		<td width="75%"><font color="red" size="2">  <%=request.getAttribute("error")==null?"&nbsp;":((String)request.getAttribute("error"))%></font></td>
	</tr>
</table>
<table align="center" border="0" cellpadding="2" cellspacing="2" width="100%">
	<tr>
		<td colspan="4">
			<span class="heading">Proposal Number:&nbsp;&nbsp;<%=(String)session.getAttribute("proposalNo")%></span>
			<span class ="heading" style="float: right"><%=(session.getAttribute("missippiCode")!=null && !"".equals((String)session.getAttribute("missippiCode")))?("Core Application Policy No: "+(String)session.getAttribute("missippiCode")):""%></span>
		</td>
	</tr>
	<%
	//session.setAttribute("brokerName",request.getParameter("brokerId")==null?"":request.getParameter("brokerId"));
	//session.setAttribute("productType",request.getParameter("ProductName")==null?"":request.getParameter("ProductName"));
	%>
	<tr>
		<td class="formtxtf1" width="25%" style="padding: 5px;">Effective Date<font color='red'>*</font></td>
		<td class="formtxtf1" width="25%" style="padding: 5px;">
			<select name="effectiveDay" style="width: 33%; float: left;" class='inputSelect' <%="GUEST".equalsIgnoreCase(loginIds)?"DISABLED":""%>>
				<option value="Select">DD</option>
				<% for(int i=1;i<=31;i++) { %>
				<option  value='<%=i%>' <%=(""+i).equalsIgnoreCase(datas[15])?"selected":""%>><%=i%></option>	
				<% }%>		
			</select>
			<select name="effectiveMonth" style="width: 33%; float: left;" class='inputSelect' <%="GUEST".equalsIgnoreCase(loginIds)?"DISABLED":""%>>
				<option value="Select" >MM</option>
				<% for(int i=1;i<=Mon.length;i++) { %>
				<option  value='<%=i%>' <%=(""+i).equalsIgnoreCase(datas[16])?"selected":""%>><%=Mon[i-1]%></option>		
				<% } %>		
			</select>
			<select name="effectiveYear" style="width: 33%; float: left;" class='inputSelect' <%="GUEST".equalsIgnoreCase(loginIds)?"DISABLED":""%>>
				<option value="Select" >YYYY</option>
				<%	java.util.Calendar cal = java.util.Calendar.getInstance();
					for(int i=cal.get(java.util.Calendar.YEAR)-5;i<=cal.get(java.util.Calendar.YEAR)+1;i++) { %>
						<option  value='<%=i%>' <%=(""+i).equalsIgnoreCase(datas[17])?"selected":""%>><%=i%></option>	
					<% } %>		
			</select>	
		</td>
		<%-- <tr class='formtxtf'>
		        <td class='formtxtf' width="10%">&nbsp;&nbsp;&nbsp;
				Open Cover Start Date<font color='red'>*</font></td>
				<td class='formtxtf' width="40%" align="left">
				<select name="startDay" style="width:40px" class='scrolLet'>
					<option value="Select">DD</option>
					<%
						for(int i=1;i<=31;i++)
						{
							%>
								<option  value='<%=i%>' <%=(""+i).equalsIgnoreCase(datas[0])?"selected":""%>><%=i%></option>	
							<%
						}
					%>		
				</select>
				<select name="startMonth" style="width:50px" class='scrolLet'>
					<option value="Select" >MM</option>
					<%
						for(int i=1;i<=Mon.length;i++)
						{
							%>
								<option  value='<%=i%>' <%=(""+i).equalsIgnoreCase(datas[1])?"selected":""%>><%=Mon[i-1]%></option>		
							<%
						}
					%>		
				</select>
				<select name="startYear" style="width:50px" class='scrolLet'>
					<option value="Select" >YYYY</option>
					<%
						for(int i=cal.get(java.util.Calendar.YEAR)-5;i<=cal.get(java.util.Calendar.YEAR)+1;i++)
						{
							%>
								<option  value='<%=i%>' <%=(""+i).equalsIgnoreCase(datas[2])?"selected":""%>><%=i%></option>	
							<%
						}
					%>		
			 </select>				</td>
			 <input type="hidden" name="type" value="<%=type%>>"/>
			 <%
			 if(!type.trim().equals("Standard")){ %>
				<td class='formtxtf' width="20%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Open Cover End Date<font color='red'>*</font></td>
				<td class='formtxtf' width="30%" align="left">	

					<select name="endDay" style="width:40px" class='scrolLet'>
					<option value="Select" >DD</option>
					<%
						for(int i=1;i<=31;i++)
						{
							%>
								<option  value='<%=i%>' <%=(""+i).equalsIgnoreCase(datas[3])?"selected":""%>><%=i%></option>	
							<%
						}
					%>		
				</select>
				<select name="endMonth" style="width:50px" class='scrolLet'>
					<option value="Select" >MM</option>
					<%
						for(int i=1;i<=Mon.length;i++)
						{
							%>
								<option  value='<%=i%>' <%=(""+i).equalsIgnoreCase(datas[4])?"selected":""%>><%=Mon[i-1]%></option>	
							<%
						}
					%>		
				</select>
				<select name="endYear" style="width:50px" class='scrolLet'>
					<option value="Select" >YYYY</option>
					<%
						for(int i=2007;i<=2013;i++)
						{%>
								<option  value='<%=i%>' <%=(""+i).equalsIgnoreCase(datas[5])?"selected":""%>><%=i%></option>	
							<%
						}
					%>		
			 </select>		
			 </td>
			 <%
			 }
			 else{ %>
			   <input type="hidden" name="endDay" value="<%=datas[3]%>"/>
			   <input type="hidden" name="endMonth" value="<%=datas[4]%>"/>
			   <input type="hidden" name="endYear" value="<%=datas[5]%>"/>
			 <td colspan="2" class='formtxtf'>&nbsp;</td>
			 <%
			 } %>
           </tr>

		   <tr class='formtxtf'>			
				<td class=''>&nbsp;</td>
				<td class=''>&nbsp;</td>
				<td class=''>&nbsp;</td>
				<td class=''>&nbsp;</td>
		   </tr>--%>		
		<td class="formtxtf1" width="25%" style="padding: 5px;"></td>
		<td class="formtxtf1" width="25%" style="padding: 5px;"></td>
	</tr>
	<tr>
		<td class="formtxtf1" style="padding: 5px;">Country Of Origin <sup><font color="red"> *</font></sup></td>
		<td class="formtxtf1" style="padding: 5px;">
			<textarea name="transit_country" readOnly class="fdel" rows='3' style="width: 80%; float: left;" ><%=datas[6]%></textarea>
			<input name="chooseCommodityC" value="..." class="inputButton" onclick="showCommodities('transit-country')" type="button">
		</td>
		<td class="formtxtf1" style="padding: 5px;">Country of Destination <sup><font color="red"> *</font></sup></td>
		<td class="formtxtf1" style="padding: 5px;">
			<textarea name="destination_country" readOnly class="fdel" rows='3' style="width: 80%; float: left;"><%=datas[7]%></textarea>
			<input name="chooseCommodityC2" value="..."  class="inputButton"onclick="showCommodities('destination-country')" type="button">
		</td>
	</tr>
	<tr>
		<td class="formtxtf1" style="padding: 5px;">Country Remarks</td>
		<td class="formtxtf1" style="padding: 5px;">
			<input readonly type="hidden" name="remLen1" id="remLen1" size="3" maxlength="3" value="125">
			<textarea name="txtarCryRemarkes" id="this.id" wrap="physical" rows="3" style="width: 95%;" onkeyup="textCounter()"><%=datas[20]%></textarea>
		</td>
		<%if(!"GUEST".equalsIgnoreCase(loginIds)){%>
			<td class="formtxtf1" style="padding: 5px;">Customer</td>
			<td class="formtxtf1" style="padding: 5px;">
				<textarea name="customer" readOnly class="fdel" rows='3' style="width: 80%; float: left;"><%=customer%></textarea>
				<input name="chooseCustomer" value="..."  class="inputButton" onclick="showCommodities('customer')" type="button">
				<input type="hidden" name="customerId" value="<%=customerId%>"/>
			</td>
		<%}%>
         <%if("GUEST".equalsIgnoreCase(loginIds)){%>
         <td class="formtxtf" colspan="3"></td>
         <%}%>
	</tr>
	<tr>
		<td colspan="4">
			<span class="heading">Category of Goods</span>
		</td>
	</tr>
	<tr>
		<td class="formtxtf1" style="padding: 5px;">Goods&nbsp;Category&nbsp;to&nbsp;Insure&nbsp;<sup><font color="red">*</font></sup></td>
		<td class="formtxtf1" style="padding: 5px;">
			<textarea name="commodity_TA" readOnly class="fdel" cols="15" rows='3' style="width: 80%; float: left;"><%=datas[8]%></textarea>
			<input name="chooseCommodityC" value="..."  class="inputButton" onclick="showCommodities('commodity')" type="button" <%=("GUEST".equalsIgnoreCase(loginIds) && "Approved".equalsIgnoreCase(proposalStatus))?"DISABLED":""%>>
		</td>
		<td class="formtxtf1" style="padding: 5px;">Total Number Of Goods<sup><font color="red"> *</font></sup></td>
		<td class="formtxtf1" style="padding: 5px;">
			<input type='text' class="inputBox" name='TotalCommudity' readOnly value="<%=datas[9]%>"/>
		</td>
	</tr>
	<tr>
		<td colspan="4">
			<span class="heading">Value Basis</span>
		</td>
	</tr>
	<tr>
		<td class="formtxtf1" style="padding: 5px;">Value&nbsp;Basis&nbsp;<sup><font color="red">*</font></sup></td>
		<td class="formtxtf1" style="padding: 5px;">
			<textarea name="saleTermName" readOnly class="fdel" cols="15" rows='3' style="width: 80%; float: left;"><%=datas[18]%></textarea>
			<input name="chooseSaleTerm" value="..."  class="inputButton" onclick="showCommodities('saleterm')" type="button" >
		</td>
		<td class="formtxtf1" style="padding: 5px;">Tolerance</td>
		<td class="formtxtf1" style="padding: 5px;">
			<textarea name="tolerance" readOnly class="fdel" cols="15" rows='3' style="width: 80%; float: left;"><%=datas[22]%></textarea>
			<input name="chooseTolerance" value="..."  class="inputButton" onclick="showCommodities('tolerance')" type="button">
            <input type="hidden" name="toleranceId" value="<%=datas[21]%>"/>
		</td>
	</tr>
	<tr>
		<td colspan="4">
			<table width="100%" class="footable">
				<thead>
				<tr>
					<th width="20%">Form of Transport&nbsp;<FONT color=red>**</FONT></th>
					<th width="40%">Coverages&nbsp;<FONT color=red>**</FONT></th>
					<th width="20%">Per Bottom Limit<FONT color=red>**</FONT></th>
					<th width="20%">Currency<FONT color=red>**</FONT></th>
				</tr>
				</thead>
          		<tbody>
          		
<%
	int g=0;		 
	getExisting=new String[0][0];
	if(request.getAttribute("error")==null)
		getExisting=beans.getExistingMode();
		for(int i=0;i<modeMaster.length;i++) {
			mode="";
			modeValue="";seaValues="";currencyId="";currencyValue="";coverTypeId="";
			for(int t=0;t<getExisting.length;t++) {
				if("".equalsIgnoreCase(mode)&& getExisting[t][0].equalsIgnoreCase(modeMaster[i][0])) {
					  mode="checked";
					  modeValue=getExisting[t][6];
					  seaValues=getExisting[t][2];
					  currencyId=getExisting[t][7];
					  currencyValue=getExisting[t][8];
				  }
			  }
			//out.println("<br> "+i+"--- mode---->"+mode);
%>
			<tr>
            	<td>
					<input name="modeTransport_<%=modeMaster[i][0]%>" type="checkbox" value="<%=modeMaster[i][1]%>" <%=request.getParameter("modeTransport_"+modeMaster[i][0])!=null?(modeMaster[i][1].equalsIgnoreCase(request.getParameter("modeTransport_"+modeMaster[i][0]))?"checked":""):mode%>/><%=modeMaster[i][1]%>
				</td>
				<td>
					<table width="410">
						<tr>
<%
	int k=0;
	for(int j=0;j<coverMaster.length;j++) {
		if(coverMaster[j][0].equalsIgnoreCase(modeMaster[i][0])) {
			g++;
			mode="";
			for(int t=0;t<getExisting.length;t++) {
				if(getExisting[t][1].equalsIgnoreCase(coverMaster[j][1])) {
					mode="checked";
					coverTypeId=getExisting[t][9];
					break;
				}
			}
			if(k==2) {
%>
						</tr>
						<tr>
<%			}%>				
							<td>
								<input name="cover_<%=coverMaster[j][1]%>" value="<%=coverMaster[j][1]%>" <%=request.getParameter("cover_"+coverMaster[j][1])!=null?(coverMaster[j][1].equalsIgnoreCase(request.getParameter("cover_"+coverMaster[j][1]))?"checked":""):mode%> type="checkbox" >
								&nbsp;&nbsp;<img src="<%=request.getContextPath()%>/images/list_icon.png"  style="cursor:hand" height="16" onClick="return openCoverType('coverTypeIds_<%=coverMaster[j][1]%>','<%=modeMaster[i][0]%>')" border='0' name="chkICC_A_SEAClause" alt="Add Conveyance">&nbsp;&nbsp;
								<input type="hidden" name="coverTypeIds_<%=coverMaster[j][1]%>" id="coverTypeIds_<%=coverMaster[j][1]%>" value="<%=request.getParameter("coverTypeIds_"+coverMaster[j][1])!=null?request.getParameter("coverTypeIds_"+coverMaster[j][1]):coverTypeId %>"/>
								<input type="hidden" name="coverIds_<%=modeMaster[i][0]%>_<%=g%>" value="cover_<%=coverMaster[j][1]%>"><%=coverMaster[j][2]%>
							</td>
<%			if(k==2)
				k=0;
				k++;
			}
		}
		if("1".equalsIgnoreCase(modeMaster[i][0])) {
			int l=0;
%>
						</tr>
						<tr>
<%
		for(int m=0;m<extraCover.length;m++) {
			g++;
			mode="";
			if(seaValues!=null) {
				tokens=new StringTokenizer(seaValues,",");
				while(tokens.hasMoreTokens()) {
					if(extraCover[m][1].equalsIgnoreCase(tokens.nextToken()))
						mode="checked";
				}
			}
			if(l==2) {
%>
						</tr>
						<tr>
<%			}%>
							<td> 
					   			<input name="extracover<%=m%>" <%=request.getParameter("extracover"+m)!=null?(extraCover[m][1].equalsIgnoreCase(request.getParameter("extracover"+m))?"checked":""):mode%> type="checkbox" value="<%=extraCover[m][1]%>" >
  								<input type="hidden" name="coverIds_<%=modeMaster[i][0]%>_<%=g%>" value="extracover<%=m%>">                    <%=extraCover[m][1]%>
  							</td>
<%			if(l==2)
				l=0;
				l++;
			}
		}
%>
						</tr>
					</table>
				</td>
            	<td>
					<input name="perbottomLimit_<%=modeMaster[i][0]%>" size="14" maxlength="12" value="<%=request.getParameter("perbottomLimit_"+modeMaster[i][0])!=null?(request.getParameter("perbottomLimit_"+modeMaster[i][0])):modeValue%>" type="text" class="inputBox" >
				</td>
				<td>
					<select name='currencyId_<%=modeMaster[i][0]%>' id='currencyId_<%=modeMaster[i][0]%>' class='inputSelect' ONCHANGE="return showValue(this.value,'currencyValue_<%=modeMaster[i][0]%>')">
						<option value='0'>
							Select
						</option>
						<%for (int j = 0; j < channeldetails.length; j++) {%>
						<option value=<%=channeldetails[j][4]%> <%=channeldetails[j][4].equalsIgnoreCase(request.getParameter("currencyId_"+modeMaster[i][0])!=null?(request.getParameter("currencyId_"+modeMaster[i][0])):currencyId)?"selected":""%>>
							<%=channeldetails[j][1]%>
						</option>
						<%}%> 
					</select>
					<input name="currencyValue_<%=modeMaster[i][0]%>" id="currencyValue_<%=modeMaster[i][0]%>" size="14" maxlength="12" value="<%=request.getParameter("currencyValue_"+modeMaster[i][0])!=null?(request.getParameter("currencyValue_"+modeMaster[i][0])):currencyValue%>" type="hidden" class="fde1" >
				</td>
<%	}%>
<%for (int c = 0; c < channeldetails.length; c++) {%>
				<input type="hidden" name="<%=channeldetails[c][4]%>" id="<%=channeldetails[c][4]%>" value="<%=channeldetails[c][0]%>" />
<%}%>
				<input type="hidden" name="totalg" value="<%=g%>">
		
          	</tr>
		 <!-- //Rajesh New on 18/05 -->
		  	<tr>
		  		<td colspan="4"><input type="checkbox" name="wsrc" value="Y" <%=(wsrc.equalsIgnoreCase("Y")?"checked":"")%>/> W&SRCC</td></tr>
		  <!-- //Rajesh New on 18/05 -->
          		</tbody>
          	</table>
          	<br>
		<table width="95%" align="center" border="0" cellspacing="0" cellpadding="3"> 
<%
				
			getExisting=new String[0][0];
			  if(request.getAttribute("error")==null)
				 getExisting=beans.getCommoditys(belongingBranch);
				 %>
				 <input type="hidden" name="commoditylen" value="<%=commodities.length%>"/>
				 <%
		 try
		 {
		   if(commodities!=null)
		   {
				if(request.getParameter("commodityIds")==null)
				datas[10]="";
				
			   for(int i=1;i<=commodities.length;i++)
			   {
				   mode="";
				   modeValue="";
					String tempTypeID="";
				 for(int j=0;j<getExisting.length;j++)
				 {
					if(commodities[i-1][0].equalsIgnoreCase(getExisting[j][0]))
					 {
						datas[10]=datas[10]+","+commodities[i-1][0];
						mode="on ".equalsIgnoreCase(getExisting[j][2])?"selected":"";
						modeValue=getExisting[j][1];
						datas[8]=datas[8]+","+commodities[i-1][1];
						//datas[10]=datas[10].substring(1,datas[10].length());
						//datas[8]=datas[8].substring(1,datas[8].length());
						tempTypeID = getExisting[j][4]!=null?getExisting[j][4]:"";
						break;
					 }
				 }
				 
				   %>
				<input type="hidden" name="commodityId<%=i%>" value="<%=request.getParameter("commodityId"+i)!=null?request.getParameter("commodityId"+i):(modeValue.length()>0?"selected":"")%>">
				<%modeValue=modeValue.replaceAll("\"","&#34;");%>
				<input type="hidden" name="commodity_desc<%=i%>" value="<%=request.getParameter("commodity_desc"+i)!=null?(request.getParameter("commodity_desc"+i).replaceAll("\"","&#34;")):modeValue%>" maxlength="480" />
				<input type="hidden" name="commodity_Fragile<%=i%>" value="<%=request.getParameter("commodity_Fragile"+i)!=null?request.getParameter("commodity_Fragile"+i):mode%>">
				<input type="hidden" name="commodityType<%=i%>" value="<%=request.getParameter("commodityType"+i)!=null?request.getParameter("commodityType"+i):(modeValue.length()>0?tempTypeID:"")%>">
 				<input type="hidden" name="commodityNum<%=i%>" value="<%=commodities[i-1][0] %>">
 				
<%			}
			
		   }
		   %>
			
			<% 	 }
		 
		 catch(Exception e)
		 {
			//out.println("<br>   FINAL ERROR  "+e.toString());
		 }
		 if(request.getParameter("commodityIds")==null)
		 {
		  if(datas[10]!=null && datas[10].length()>0)
		  datas[10]=datas[10].substring(1,datas[10].length());
		  if(datas[8]!=null && datas[8].length()>0)
		 datas[8]=datas[8].substring(1,datas[8].length());
		 }
%>
		
		 </table>
		 <input type="hidden" name="extraCoverLength" value="<%=extraCover.length%>"/>
<input type="hidden" name="commodityIds" value="<%=datas[10]%>"/>
<input type="hidden" name="transit_countryId" value="<%=datas[11]%>"/>
<input type="hidden" name="totalTransitId" value="<%=datas[12]%>"/>
<input type="hidden" name="destination_countryId" value="<%=datas[13]%>"/>
<input type="hidden" name="totalDestinationId" value="<%=datas[14]%>"/>
<input type="hidden" name="saleTermId" value="<%=datas[19]%>"/>
<input type="hidden" name="actionPath" value="<%=action%>" />
<input type="hidden" name="chkProposalNo" value="<%=chkProposalNo%>"/>
<input type="hidden" name="brokerId" value="<%=brokerId%>"/>
<input type="hidden" name="originalInsured" value="<%=originalInsured%>"/>
<input type="hidden" name="proposalStatus" value="<%=proposalStatus%>"/>
<%if("GUEST".equalsIgnoreCase(loginIds)){%>
	<input type="hidden" name="user" value="GUEST"/>
<%}%>
	
<table width="100%">
	<tr>
		<td align="center">
			<a href="#" onClick="return back()"  class="buttonsMenu" >
				 <img src="../images/Back.jpg">
			</a> &nbsp;&nbsp;&nbsp;
			<%if(session.getAttribute("openCoverNo")==null){ %>
			<a href="#" onClick="return SaveAction(1)"  class="buttonsMenu" >
				 <img src="../images/SaveQuote.jpg" >
			</a> &nbsp;&nbsp;&nbsp;
			<%} %>
			<a href="#" onClick="return SaveAction(2)"  class="buttonsMenu" >
				 <img src="../images/Proceed.jpg" >
			</a> 
		</td>
	</tr>
</table>
		</td>
	</tr>
</table>
</form>
<script type="text/javascript">
function selectCoverages(modeId) {
	/*var g=document.Quotation.totalg.value;
	var coverValue;
	for(var i=1;i<=g;i++)
	{
		if((modeId==1&& i<=7)|| (modeId==2 && (i>=8&&i<=9)) || (modeId==3 && i>=10))
		{coverValue=eval("document.Quotation.coverIds_"+modeId+"_"+i+".value");
		if(eval("document.Quotation.modeTransport_"+modeId+".checked"))
		eval("document.Quotation."+coverValue+".checked=true");
		else
		eval("document.Quotation."+coverValue+".checked=false");
		}
	}*/
}

function SaveAction(id) {
	
	if(id==1)
		document.Quotation.actionPath.value="showOpencover.jsp";
	else
		document.Quotation.actionPath.value="Premium.jsp";
	document.Quotation.effectiveDay.disabled=false;
	document.Quotation.effectiveMonth.disabled=false;
	document.Quotation.effectiveYear.disabled=false;
	document.Quotation.action="Quotation.jspa"
	document.Quotation.submit();
	return false;
}

function showCommodities(values) {
 	var URL = '';
  	var windowName = "";  
	if(values == 'commodity') {
		
		URL='commodity.jsp?commodityIds='+document.Quotation.commodityIds.value+'&chkProposalNo='+document.Quotation.chkProposalNo.value;
	} else if(values == 'transit-country') {
		URL='countrySelection.jsp?identify='+values+'&&countryIds='+document.Quotation.transit_countryId.value+'&&chkProposalNo='+document.Quotation.chkProposalNo.value;
	} else if(values == 'destination-country') {
		URL='countrySelection.jsp?identify='+values+'&&countryIds='+document.Quotation.destination_countryId.value+'&&chkProposalNo='+document.Quotation.chkProposalNo.value;
	} else if(values == 'saleterm') {
		URL='SaleTermSelection.jsp?identify='+values+'&&saleTermIds='+document.Quotation.saleTermId.value+'&&chkProposalNo='+document.Quotation.chkProposalNo.value;
	} else if(values == 'tolerance') {
		URL='Tolerance.jsp?identify='+values+'&&saleTermIds='+document.Quotation.saleTermId.value+'&&chkProposalNo='+document.Quotation.chkProposalNo.value;
	} else if(values == 'customer') {
		URL='customerSelection.jsp?brokerId='+document.Quotation.brokerId.value+'&originalInsured='+document.Quotation.originalInsured.value;
	} 
	
	var width  = screen.availWidth;
	var height = screen.availHeight;
	var h=450;
	var w=750;
	var features =
		'width='		 + w +
		',height='		 + h +
		',left='		 + ((width - w - 0) * .4)  +
		',top='			 + ((height - h - 0) * .4) +
		',directories=no'+
		',location=no'	 +
		',menubar=no'	 +
		',scrollbars=yes'+
		',status=yes'	 +
		',toolbar=no'	 +
		',resizable=false';
	var strOpen = window.open (URL, windowName, features);
	strOpen.focus();
}

function funtxtarlen(id) {
	var val=document.getElementById("txtarCryRemarkes").value; 
	if(parseInt(val.length)>300) {
		alert("val.length:"+val.length) 
		document.getElementById("txtarCryRemarkes").readOnly=true;
	}
}

function back() {
	document.Quotation.action="newOpenCover.jsp";
	document.Quotation.submit();
	return false;
}

function textCounter() {
	var field=document.getElementById("txtarCryRemarkes")
	var maxlimit=300
	if (field.value.length > maxlimit) { 
		field.value = field.value.substring(0, maxlimit);
	}
}

function setCommodityName(values,val) {
	document.Quotation.commodity_TA.value=values;
	document.Quotation.TotalCommudity.value=val;
}

<%
if(request.getAttribute("error")==null) {
%>
	setCommodityName('<%=datas[8]%>','<%=getExisting.length%>');
<%}%>

function showValue(Field, obj) {
	var selected = document.getElementById(""+Field);
	with(selected) {
		document.getElementById(obj).value=value;
	}
}

function openCoverType(obj, mode) {
 	var URL = '';
  	var windowName = "";
	URL='CoverType.jsp?coverTypeName='+obj+'&&coverTypeIds='+document.getElementById(obj).value+'&modeOfTransport='+mode+'&&chkProposalNo='+document.Quotation.chkProposalNo.value;
	var width  = screen.availWidth;
	var height = screen.availHeight;
	var h=300;
	var w=500;
	var features =
		'width='		 + w +
		',height='		 + h +
		',left='		 + ((width - w - 0) * .4)  +
		',top='			 + ((height - h - 0) * .4) +
		',directories=no'+
		',location=no'	 +
		',menubar=no'	 +
		',scrollbars=yes'+
		',status=yes'	 +
		',toolbar=no'	 +
		',resizable=false';
	var strOpen = window.open (URL, windowName, features);
	strOpen.focus();
	return false;
}

</script>
</td>
</tr>
<%}catch(Exception e){e.printStackTrace();} %>
</table>
</body>
</html>