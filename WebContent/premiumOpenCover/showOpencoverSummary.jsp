<%	try{ %>
<%String cpath1 = request.getContextPath(); %>
<%@page import="com.maan.premium.DAO.PremiumInputsBean"%>
<!DOCTYPE HTML>
<html>
<head>
	<title>Emirates</title>
	<link rel="stylesheet" type="text/css" href="<%=cpath1%>/css/skin1.css"/>
	<link href="<%=cpath1%>/css/style.css" rel="stylesheet" type="text/css">
	<link href="<%=cpath1%>/css/footable-0.1.css" rel="stylesheet" type="text/css">
	<script src="<%=cpath1%>/css/jquery.tools.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
	<script language="JavaScript">
		function stopRKey(evt) { 
		 	 var evt = (evt) ? evt : ((event) ? event : null); 
		  	 var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
		 	 if ((evt.keyCode == 13) && ((node.type=="text") || (node.type=="password"))) {return false;}
		} 
		document.onkeypress = stopRKey; 
	</script>
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
<%
    String pathq = request.getContextPath();
    String basePaths = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+pathq+"/";
	String wsrc = "";
	StringTokenizer tokens=null;
	String getExisting[][]=new String[0][0];
	String proposalNo = request.getParameter("proposalNo")==null?(String)session.getAttribute("proposalNo"): (String)request.getAttribute("proposalNo");
	String org_id="",warehouse_org="",dest_id="",warehouse_dest="";
	String orginat_id[] = new String[0];
	String warehouse_org_1[] = new String[0];
	String Destnat_id[] = new String[0];
	String warehouse_dest_1[] = new String[0];
	String warratedest = "";
	String compId = "", compName = "", ckid = "";
	String[][] comp_Id = null;
	String[][] comp_Name = null;
	String Nationality[][]=null;
	String countryId=null;
	StringTokenizer tokens1=null;
	String modeNames="0";
	String warratedest_1[] = new String[0];
	java.text.NumberFormat fmt=null;
	java.text.NumberFormat fmx = new java.text.DecimalFormat("##,###");
	java.text.NumberFormat fmt1=new java.text.DecimalFormat("0.00") ;
	java.text.NumberFormat fmt3=new java.text.DecimalFormat("0.000000") ;
	java.text.NumberFormat fmt2=new java.text.DecimalFormat("00.00") ;
	java.text.NumberFormat fmt4=new java.text.DecimalFormat("#0.0000") ;
	String productId = (String)session.getAttribute("product_id");
	String Aircommodit = "", Seacommodit="", Roadcommodit="";
	int countAir=0,countSea =0, countRoad = 0;
	int Airspan=0, Seaspan=0, Roadspan=0;
	int vcount = 0;
	String br_comp = "",br_name="";
	

%>
<jsp:useBean id = "openCover" class = "com.maan.opencover.bean.opencoverSummary">
	<jsp:setProperty name = "openCover" property = "*"/>
</jsp:useBean>

<jsp:useBean id="beans" class="com.maan.opencover.bean.openCoverQuotation">
	<jsp:setProperty name="beans" property="*"/>
</jsp:useBean>
<%
	proposalNo =(String)session.getAttribute("proposalNo");
	openCover.setProposalNo(proposalNo);
	openCover.setProductId(productId);

	String adminBranch = (String) session.getAttribute("AdminBranchCode");
	String belongingBranch = (String) session.getAttribute("BelongingBranch");
	if(adminBranch.indexOf("'")!=-1)
		adminBranch = adminBranch.replaceAll("'","");
	String cid = (String) session.getAttribute("AdminCountryId");
	String currencyType = (String) session.getAttribute("currencyType");
	String decimalPlace = (String) session.getAttribute("decimalPlace");

	if(decimalPlace.equalsIgnoreCase("2"))
		fmt=new java.text.DecimalFormat("##,##0.00");
	else if(decimalPlace.equalsIgnoreCase("3"))
		fmt=new java.text.DecimalFormat("##,##0.000");
	else if(decimalPlace.equalsIgnoreCase("4"))
		fmt=new java.text.DecimalFormat("##,##0.0000");
	else
		fmt=new java.text.DecimalFormat("##,##0.00");
	
	int count=0;
	int i=0;
	int index = 0;
	int len = 0;
	String[][] result= openCover.getDatas();
	String[][] conveyance=openCover.getConveyance(belongingBranch, proposalNo);
	String[][] moT = openCover.getModeofTransport(belongingBranch, proposalNo);
	String[][] colWise = openCover.getColumnWise(belongingBranch);
	String[][] commId = openCover.getCommodityId();
	String[][] coverName = openCover.getCoverName(belongingBranch);
	String[][] modeMaster=null;
	String[][] deductible=null;
	String[][] coverMaster=null;
	String opencoverNo ="";
	String[][] ckId = null;
	String[][] depositeData=new String[0][9];
	depositeData=openCover.getDepositeInfo(proposalNo);
		if(depositeData.length==0){
		depositeData=new String[0][9];
	}
	opencoverNo = (String)session.getAttribute("openCoverNo")==null?"":(String)session.getAttribute("openCoverNo");
	List depositList=null; 
	if(!"".equals(opencoverNo))
		depositList=openCover.getDepositInfo(proposalNo);
	
	String[][] extraCover=null;

	java.util.HashMap countryDetails = new java.util.HashMap();
	countryDetails = openCover.getCountryDetails();
	dest_id = (String)countryDetails.get("dest_id");

	warratedest = (String)countryDetails.get("warrate");

	String p = "";

	  StringTokenizer st5 = new StringTokenizer(dest_id,",");
	  while(st5.hasMoreElements()) {
		 String k2 = (String)st5.nextElement();
		 count++;
	  }
	  Destnat_id = new String[count];
	  StringTokenizer st6 = new StringTokenizer(dest_id,",");
	  i=0;
	  String v2 = "";
	  while(st6.hasMoreElements()) {
		 Destnat_id[i] = (String)st6.nextElement();
		 v2  = v2+ Destnat_id[i]+",";
	  }
	  v2 = v2.substring(0,v2.length()-1);
	  openCover.setProposalNo(proposalNo);
	  openCover.setCountyId(v2);
  
	String[][] CountryList1 = openCover.getCountryList();
	warratedest = warratedest==null?"":warratedest;
	warratedest = warratedest==null?"":warratedest;
	String warRateInfo[]=warratedest.split("#");
		//Some bullshit code goes here. hence it is commented
       /*count = 0;
        StringTokenizer st9 = new StringTokenizer(warratedest,"#");
        while(st9.hasMoreElements())
        {
        String k1 = (String)st9.nextElement();
        count++;
        }
        warratedest_1 = new String[count];
        StringTokenizer st10 = new StringTokenizer(warratedest,"#");
        i=0;
        index = 0;
        len = 0;
        while(st10.hasMoreElements())
        {
		    warratedest_1[i] = (String)st10.nextElement();
	        index = warratedest_1[i].lastIndexOf("~");
			len = warratedest_1[i].length();
		    warratedest_1[i] = warratedest_1[i].substring(index+1,len);
			 i++;
	    }*/

	/* New commodity details */
	for(i=0;i<commId.length;i++) {
		p = p+commId[i][1]+",";
	}
	p = p.substring(0,p.length()-1);
	String[][] coverId = openCover.getCoverId(p,adminBranch);
	String coId = "";
	for(i=0;i<coverId.length;i++) {
		 coId = coId+coverId[i][1]+",";
	}
	
	 coId = coId.substring(0,coId.length()-1);
	 

	java.util.ArrayList rateModify=new java.util.ArrayList();
	rateModify=openCover.getCommodityList(belongingBranch);
	String getDetail[][]=null;

	getDetail=(String[][])rateModify.get(0);

	/*  Get the Personal Information */

	String[][] PersonalInf = openCover.getOpenCoverInformation();
	String AdditionalCustomer = openCover.getAdditionalCustomer();

   /*Mode master */
	modeMaster=openCover.getModeMaster(belongingBranch);
	deductible=openCover.getDeductible(adminBranch);
	coverMaster=openCover.getCoverMaster(belongingBranch);
	extraCover=openCover.getExtraCover();
	
	/* Get the Broker details */
	java.util.HashMap ht = new java.util.HashMap();
	ht = openCover.getBrokerDetail();
	br_comp = (String)ht.get("com_name");

   if("0".equalsIgnoreCase(br_comp)) {
		br_name = (String)ht.get("con_per");
   } else {
	     br_name = (String)ht.get("com_name");
   }
   
   if("Y".equalsIgnoreCase(result[0][2])) {
      result[0][2] = "Yes";
   } else {
       result[0][2] = "No";
   }
	ckId = openCover.getcheckInsuranceId();
	for(i=0;i<ckId.length;i++) {
	   ckid = ckId[0][0];
    }

   if(Integer.parseInt(ckid==null?"0":ckid) > 0) {
   		comp_Id = openCover.getInsuranceCompanyId();
		if(comp_Id.length>0) {
			for(i=0;i<comp_Id.length;i++) {
				compId = compId+comp_Id[i][0]+",";
			}
			compId = compId.substring(0,compId.length()-1);
			comp_Name = openCover.getInsuranceCompanyName(compId);
	  	}
  	}

	String StartingDate=(String)session.getAttribute("PolicyStartDate");
	String EndingDate=(String)session.getAttribute("PolicyEndingDate");
	java.util.HashMap country=(java.util.HashMap)session.getAttribute("T_FromCountry");
	java.util.HashMap MOT=(java.util.HashMap)session.getAttribute("MOT");
	java.util.HashMap COVER=(java.util.HashMap)session.getAttribute("COVER");
	int values=0;
	String[][] DebitDetails =openCover.getDebitDetails(adminBranch);
	String[][] InstallDetails =openCover.getInstallmentDetails(adminBranch);
	String renewalYN=result[0][14]==null?"":result[0][14];
	String endtYN=result[0][15]==null?"N":"Y";
	String endtType=result[0][16]==null?"":result[0][16];
	String endtTypeDesc="";
	if(endtType.length()>0){
		endtTypeDesc=openCover.getEndtTypeDescInfo(endtType);
	}
%>
<form name="form1" method="post" action = "numberGenerate.jspa">
<%
if("".equals(opencoverNo)|| opencoverNo==null){
%>
<table border="0" cellpadding="2" cellspacing="2" width="100%">
	<tr>
		<td class="formtxtf1" style="padding: 5px; text-align: center">
			Do You want to <%="Y".equalsIgnoreCase(renewalYN)?"Renew":"Generate"%> Open Cover  &nbsp; &nbsp;
			<input name="opencover" type="radio" value="Y" onclick="toggleDisplay('DebitInfo',this.value)">YES
			<input name="opencover" type="radio" value="N" checked onclick="toggleDisplay('DebitInfo',this.value);checkNo(this.value)">NO
			<input type="hidden" name="renewalYN" value="<%=renewalYN%>">
			<input type="hidden" name="endtYN" value="<%=endtYN%>">
		</td>		
	</tr>
</table>
<br>
<%
}
%>
<div id="DebitInfo" style="display:none">
	<table border="0" cellpadding="2" cellspacing="2" width="100%">
		<tr>
			<td class="formtxtf1" width="25%" style="padding: 5px; font-weight: bolder;">
				Deposit Premium
			</td>
			<td class="formtxtf1" width="75%" style="padding: 5px;">
				<input name="deposit" id="DepositYes" type="radio" value="Y" onclick="toggleDisplay('DepositInfo', this.value);toggleDisplay('ExistingDepositInfo', 'N');toggleDisplay('DebitNameInfo', this.value)">YES
				<input name="deposit" id="DepositNo" type="radio" value="N" checked onclick="toggleDisplay('DepositInfo', this.value);toggleDisplay('DebitNameInfo', this.value)">NO
				<input name="deposit" id="DepositOld" type="radio" value="O" onclick="toggleDisplay('DepositInfo', this.value);toggleDisplay('ExistingDepositInfo', this.value);toggleDisplay('DebitNameInfo', this.value)">OLD
			</td>
		</tr>
	</table>
	<div id="DepositInfo" style="display:none;">
		<table  align="center" border="0" cellpadding="2" cellspacing="2" width="100%">
			<tr>
				<td class="formtxtf1" width="25%" style="padding: 5px; font-weight: bolder;">
					Existing Deposit
				</td>
				<td class="formtxtf1" width="75%" style="padding: 5px;">
					<table class="footable" width="100%">
						<thead>
						<tr>
							<th width="33%">Deposit Date</th>
							<th width="33%">DN No.</th>
							<th width="33%">CN No.</th>
						</tr>
						</thead>
						<tbody>
						<tr>
							<td align="center"><input type="date" id="depositDate" name="depositDate"  value="<%= depositeData[0][7]==null?"":depositeData[0][7]%>"  size="25" class="inputBox" /></td>
							<td align="center"><input type="text" name="debitNoteNo" id="debitNoteNo"  value="<%= depositeData[0][3]==null?"":depositeData[0][3]%>"  class="inputBox" maxlength="15" /></td>
							<td align="center"><input type="text" name="creditNoteNo" id="creditNoteNo"  value="<%= depositeData[0][4]==null?"":depositeData[0][4]%>"  class="inputBox" maxlength="15" /></td>
						</tr>
						</tbody>
					</table>					
				</td>
			</tr>			
		</table>
		<table border="0" cellpadding="2" cellspacing="2" width="100%">
			<tr>
				<td class="formtxtf1" width="25%" style="padding: 5px; font-weight: bolder;">
					Type of Deposit
				</td>
				<td class="formtxtf1" width="75%" style="padding: 5px;">
					<select name="depositType" id="depositType" onchange="toggleDisplay('InstallInfo', this.value)" style="width:200px" class="inputSelect">
						<option value="">Select</option>
						<%for(int j=0;j<DebitDetails.length;j++){%>
						<option value="<%=DebitDetails[j][0]%>"<%=DebitDetails[j][0].equalsIgnoreCase(depositeData[0][1])?"selected":""%>>
							<%=DebitDetails[j][1]%></option>
						<%}%>
					</select>
				</td>
			</tr>
		</table>		
		<table border="0" cellpadding="2" cellspacing="2" width="100%" id="InstallInfo" style="display:none" >
			<tr>
				<td class="formtxtf1" width="25%" style="padding: 5px; font-weight: bolder;">
					Installment
				</td>
				<td class="formtxtf1" width="75%" style="padding: 5px;">
						<select name="installType" id="installType" style="width:200px" class="inputSelect">
						<option value="">Select</option>
						<%for(int j=0;j<InstallDetails.length;j++){%>
							<option value="<%=InstallDetails[j][0]%>"<%=InstallDetails[j][0].equalsIgnoreCase(depositeData[0][2])?"selected":""%>>
								<%=InstallDetails[j][1]%></option>
						<%}%>
					</select>
				</td>
			</tr>
		</table>
		<table border="0" cellpadding="2" cellspacing="2" width="100%">
			<tr>
				<td class="formtxtf1" width="25%" style="padding: 5px; font-weight: bolder;">
					Deposit Amount
				</td>
				<td class="formtxtf1" width="75%" style="padding: 5px;">
					<table class="footable" width="100%">
						<thead>
						<tr>
							<th width="50%">Marine Premium</th>
							<th width="50%">War Premium</th>
						</tr>
						</thead>
						<tbody>
						<tr>
							<td align="center"><input type="text" name="marinePremium" id="marinePremium" value="<%= depositeData[0][5]==null?"":depositeData[0][5]%>" maxlength="15" class="inputBox" onkeyup="checkNumbers(this)" style="width:135px"/></td>
							<td align="center"><input type="text" name="warPremium" id="warPremium" maxlength="15" value="<%= depositeData[0][6]==null?"":depositeData[0][6]%>" class="inputBox" onkeyup="checkNumbers(this)" style="width:135px"/></td>
						</tr>
						</tbody>
					</table>					
				</td>
			</tr>
		</table>
		<table border="0" cellpadding="2" cellspacing="2" width="100%">
			<tr>
				<td class="formtxtf1" width="25%" style="padding: 5px; font-weight: bolder;">
					Remarks
				</td>
				<td class="formtxtf1" width="75%" style="padding: 5px;">
					<textarea rows="5" name="remarks" cols="55" style="width: 100%;" onkeyup="textLimit(this,1000)" ><%= depositeData[0][8] %></textarea>
				</td>
			</tr>
		</table>
		<table border="0" cellpadding="2" cellspacing="2" width="100%" id="DebitNameInfo" style="display:none">
			<tr>
				<td class="formtxtf1" width="25%" style="padding: 5px; font-weight: bolder;">
					Debit note in the name of
				</td>
				<td class="formtxtf1" width="75%" style="padding: 5px;">
					<input name="debitNoteName" id="broker" type="radio" value="B" checked> Broker
					<input name="debitNoteName" id="assured" type="radio" value="A">	Assured
				</td>
			</tr>
		</table>			
	</div>
</div>
<%if("Y".equalsIgnoreCase(endtYN) && endtTypeDesc.length()>0){%>
<br>
<table border="0" cellpadding="2" cellspacing="2" width="100%" id="DebitNameInfo" style="display:none">
	<tr>
		<td class="formtxtf1" width="25%" style="padding: 5px; font-weight: bolder;">
			Endorsement Type
		</td>
		<td class="formtxtf1" width="75%" style="padding: 5px;">
			<%=endtTypeDesc%>
		</td>
	</tr>
</table>
<%} %>
<br>
<table border="0" cellpadding="2" cellspacing="2" width="100%">
	<tr>
		<td><span class='heading'>Print in Schedule</span></td>
	</tr>
</table>
<table border="0" cellpadding="2" cellspacing="2" width="100%">
	<tr>
		<td class="formtxtf1" width="25%" style="padding: 5px; font-weight: bolder;">
			Cancellation Clauses
		</td>
		<td class="formtxtf1" width="75%" style="padding: 5px;">
			<input type="text" name="cancelClaus" value="<%= result[0][12]==null?"": result[0][12]%>" maxlength="15" onkeyup="checkNumbers(this)" style="width:200px;" class="inputBox" />
		</td>
	</tr>
</table>
<table border="0" cellpadding="2" cellspacing="2" width="100%">
	<tr>
		<td><span class='heading'>Conveyance</span></td>
	</tr>
	<%if(conveyance!=null &&conveyance.length>0) {
		for(int j=0;j<conveyance.length;j++) { %>
	<tr> 
		<td class="formtxtf1" width="25%" style="padding: 5px;"><%=conveyance[j][1] %></td>
		<td class="formtxtf1" width="75%" style="padding: 5px;">
			<input type="hidden" name="transportId<%=j%>" value="<%=conveyance[j][0]%>">
			<textarea rows="5" name="conveyance<%=j%>"  cols="55" onkeyup="textLimit(this,1000)"><%=conveyance[j][2]==null?"":conveyance[j][2]%></textarea>
		</td>
	</tr>
	<%	}%>
	<input type="hidden" name="conveyanceCount" value="<%=conveyance.length%>"/>
	<%}%>
	<tr>
		<td class="formtxtf1" style="padding: 5px; font-weight: bolder;">
			Schedule
		</td>
		<td class="formtxtf1" style="padding: 5px;">
			<input name="ratesYN" type="radio" value="Y" <%="Y".equalsIgnoreCase(result[0][11])?"checked":""%>>Rate
			<input name="ratesYN" type="radio" value="N" <%="N".equalsIgnoreCase(result[0][11])?"checked":""%> >As Agreed
		</td>
	</tr>
	<tr>
		<td class="formtxtf1" style="padding: 5px; font-weight: bolder;">
			Do You want to print Amended Insolvency Exclusion Clause
		</td>
		<td class="formtxtf1" style="padding: 5px;">
			<input name="amendedClauseYN" type="radio" value="Y" <%="Y".equalsIgnoreCase(result[0][13])?"checked":""%>>Yes
			<input name="amendedClauseYN" type="radio" value="N" <%="N".equalsIgnoreCase(result[0][13])?"checked":""%> >No
		</td>
	</tr>
</table>		
<br>
<table border="0" cellpadding="2" cellspacing="2" width="100%">
	<tr>
		<td width="25%"><span class='heading'>Core Application Policy No:</span></td>
		<!--<td width="23%"><span class='heading'><%=(String)session.getAttribute("missippiCode")%></span></td>-->
		<td width="25%"><strong><%=(session.getAttribute("missippiCode")==null ||"".equals((String)session.getAttribute("missippiCode")))?"Not Generated":(String)session.getAttribute("missippiCode")%></strong></td>
		<%
			String mopid = "";
			String busiType = "";
			mopid = (PersonalInf[0][10]==null?"":PersonalInf[0][10]);
			busiType = (PersonalInf[0][11]==null?"":PersonalInf[0][11]);
		%>
		<td width="25%"><span class='heading'>Core Application Proposal No:</span></td>
		<td width="25%"><strong><%=proposalNo%></strong></td>
	</tr>
	<tr>
		<td class="formtxtf1" style="padding: 5px; font-weight: bolder;">Start Date</td>
		<td class="formtxtf1" style="padding: 5px;">
			<%=result[0][0]==null?"":result[0][0]%>	
		</td>
		<%
		if(result[0][8].equalsIgnoreCase("One Stop")) {
		%>
		<td class="formtxtf1" style="padding: 5px; font-weight: bolder;">End Date</td>
		<td class="formtxtf1" style="padding: 5px;">
			<%=result[0][1]==null?"":result[0][1]%>
		</td>
		<%} else { %>
		<td class="formtxtf1" style="padding: 5px; font-weight: bolder;"> &nbsp; </td>
		<td class="formtxtf1" style="padding: 5px;"> &nbsp; </td>
		<%}%>
	</tr>
	<tr>
		<td class="formtxtf1" style="padding: 5px; font-weight: bolder;">Product Type</td>
		<td class="formtxtf1" style="padding: 5px;">
			<%=result[0][3]%>
		</td>
		<td class="formtxtf1" style="padding: 5px; font-weight: bolder;">Cross Voyage</td>
		<td class="formtxtf1" style="padding: 5px;">
			<%=result[0][2]%>
		</td>
	</tr>
</table>
<table border="0" cellpadding="2" cellspacing="2" width="100%">
	<tr>
		<td colspan="4"><span class='heading'>Proposal Information</span></td>
	</tr>
	<%for(i=0;i<PersonalInf.length;i++) { %>
	<tr>
		<td width="25%" class="formtxtf1" style="padding: 5px; font-weight: bolder;">Product Type</td>
		<td width="25%" class="formtxtf1" style="padding: 5px;">
			<LABEL FOR='StartingDate' ACCESSKEY=S><%=br_name%></LABEL>
		</td>
		<td width="25%" class="formtxtf1" style="padding: 5px; font-weight: bolder;">Open Cover Type</td>
		<td width="25%" class="formtxtf1" style="padding: 5px;">
			<LABEL FOR='StartingDate' ACCESSKEY=E><%=result[0][8].equalsIgnoreCase("One Stop")?"One Stop":result[0][8]%></LABEL>
		</td>
	</tr>
	<tr>
		<td class="formtxtf1" style="padding: 5px; font-weight: bolder;">Name Of Assured</td>
		<td class="formtxtf1" style="padding: 5px;" colspan="3">
			<%	if(AdditionalCustomer!=null && AdditionalCustomer.trim().length()>0) { 
					out.print(AdditionalCustomer);
		   		}else if("0".equalsIgnoreCase(PersonalInf[i][2])) {		   			   
		   	%>
		   	<LABEL FOR='StartingDate' ACCESSKEY=S><%=PersonalInf[i][0]==null || "select".equalsIgnoreCase(PersonalInf[i][0])?"":PersonalInf[i][0]+"."%> <%=PersonalInf[i][1]%></LABEL>
		   	<%	} else { %>
		   	<%=PersonalInf[i][0]==null || "select".equalsIgnoreCase(PersonalInf[i][0])?"":PersonalInf[i][0]+"."%> <%=PersonalInf[i][2]%>
		   	<%}%>
		</td>			
	</tr>
	<tr>
		<td class="formtxtf1" style="padding: 5px; font-weight: bolder;">Estimated Turn<br>&nbsp;Over Amount(<%=currencyType%>)</td>
		<td class="formtxtf1" style="padding: 5px;">
			<%	String tom="";
				tom =""+Double.parseDouble(PersonalInf[i][4]); %>
			<LABEL FOR='StartingDate' ACCESSKEY=S><%=fmt.format(Double.parseDouble(PersonalInf[i][4]))%> </LABEL>
		</td>
		<td class="formtxtf1" style="padding: 5px; font-weight: bolder;">Commission(%)</td>
		<td class="formtxtf1" style="padding: 5px;"><%=PersonalInf[i][9]%></td>
	</tr>
	<%	String Opt = "";
		if("Y".equalsIgnoreCase(PersonalInf[i][7])) {
			Opt = "Yes";
		} else {
			Opt = "No";
		}%>
	<tr>
		<td class="formtxtf1" style="padding: 5px; font-weight: bolder;">Cross Voyage</td>
		<td class="formtxtf1" style="padding: 5px;"><%=Opt%></td>
		<td class="formtxtf1" style="padding: 5px; font-weight: bolder;">Cross Voyage (%)</td>
		<td class="formtxtf1" style="padding: 5px;">
			<LABEL FOR='StartingDate' ACCESSKEY=S><%=PersonalInf[i][8]==null?"&nbsp;":PersonalInf[i][8]%> </LABEL>
		</td>
	</tr>
	<tr>
		<td class="formtxtf1" style="padding: 5px; font-weight: bolder;">Minimum Premium(<%=currencyType%>)</td>
		<td class="formtxtf1" style="padding: 5px;"><%=fmt.format(Long.parseLong(result[0][4]))%>			</td>
		<td class="formtxtf1" style="padding: 5px; font-weight: bolder;">No.of Back date Allowed</td>
		<td class="formtxtf1" style="padding: 5px;"><%=PersonalInf[i][3]+""+"   days"%>
		<%--<td class='formtxtf'>Import Minimum Premium(<%=currencyType%>)</td>
		<td class='formtxtf'><%=fmt.format(Long.parseLong(result[0][5]))%></td>--%>
	</tr>
	<tr>
		<td class="formtxtf1" style="padding: 5px; font-weight: bolder;">Country Remarks</td>
		<td colspan="3" class="formtxtf1" style="padding: 5px;">&nbsp;<%=result[0][9]==null?"":result[0][9]%></td>
		<%--<td class='formtxtf'>Cross Voyage Minimum Premium(<%=currencyType%>)</td>
		<td class='formtxtf'><%=fmt.format(Long.parseLong(result[0][7]))%>			</td>--%>
	</tr>
	<%--<tr>
		   <td class='formtxtf'>&nbsp;&nbsp;&nbsp;Business Type</td>
		   <% busiType = (busiType!=null?busiType:"");
			   if(busiType.equalsIgnoreCase("0"))
					busiType = "Existing Business";
			   else if(busiType.equalsIgnoreCase("1"))
					busiType = "New Business";
			   else
				   busiType = "NIL"; %>	
		  		<td class='formtxtf'><%=busiType%> </td>
		   		<td class='formtxtf'>&nbsp;&nbsp;&nbsp;&nbsp;</td>
		   		<td class='formtxtf'>&nbsp;&nbsp;&nbsp;&nbsp;</td>
		   </tr>--%>
	<% } %>
	<%--<tr> <td colspan="4" width="10%">&nbsp;&nbsp;<span>&nbsp;</span></td>	</tr>
		<tr>
			<td width="20%" class='bottomtext' align = "left"><strong>&nbsp;&nbsp;&nbsp;S.No</strong></td>
			<td width="30%" colspan="2" class='bottomtext'><strong>Insurance Company</strong></td>
			<td width="30%" class='bottomtext'><strong>Share in (%)</strong></td>
		</tr>	     
	<%	for(i=0;i<PersonalInf.length;i++) { %>
		<tr>
			<td class='formtxtf'>&nbsp;&nbsp;&nbsp;1</td>
			<td class='formtxtf' colspan="2" >Emirates</td>
			<td class='formtxtf'><%=fmt.format(Double.parseDouble(""+PersonalInf[i][5]))%></td>
		</tr>
    <%	}
		int sno = 2;
		if(Integer.parseInt(ckid==null?"0":ckid) > 0) {
			for( i=0;i<comp_Id.length;i++) {
	%>
		<tr>
			<td class='formtxtf'>&nbsp;&nbsp;&nbsp;<%=sno%></td>
			<td colspan="2" class='formtxtf'><%=comp_Name[i][0]%></td>
			<td class='formtxtf'><%=fmt.format(Double.parseDouble(comp_Id[i][2]))%></td>
		</tr>
	<%
				sno++;
			}
		}
	%>--%><%--
	    <tr> <td colspan="4" width="10%">&nbsp;&nbsp;<span class='heading'>Risk Covered</span></td>	</tr>
       
        <tr>
            <td class='bottomtext' width='20%' align='left'>&nbsp;<strong>&nbsp;&nbsp;Form of Transport </strong></td>

           
            <td class='bottomtext' colspan="1" width='45%' align='left'><strong>Coverages</strong></td>
              <td class='bottomtext' width='10%' align='left'>&nbsp;<strong>&nbsp;&nbsp;WSRCC </strong></td>
           
            <td class='bottomtext' width='35%' align='left'><strong>Per Bottom Limit (<%=currencyType%>)</strong></td>
            
		</tr>
				<%
				  for( i=0;i<modeMaster.length;i++)
				  {
				%>
				 <tr>
		            <td class='formtxtf' width='20%'>&nbsp;&nbsp;
					<%=modeMaster[i][1]%></td>
					 
				   <td colspan="1" class='formtxtf' width='45%'>
					  <table width="100%">
					  <tr>
					  <%
					  int k=0;
					  
					  for(int j=0;j<coverMaster.length;j++)
					  {
						if(coverMaster[j][0].equalsIgnoreCase(modeMaster[i][0]))
						  {
							 if(k==2)
							 {
						%>
								</tr> <tr>
						<%	 
							 }
						%>
		                  <td class='formtxtf' nowrap> <%=coverMaster[j][2]%></td>
						<%
						      if(k==2)
								k=0;
						   	  k++;
						  }
					 }
					 if("1".equalsIgnoreCase(modeMaster[i][0]))
					  {
							 int l=0;
					%>
							<tr>
					<%
							if(extraCover.length >0)
							{
							  for(int m=0;m<extraCover.length;m++)
						  	  {
								  if(l==2)
								  {
					%>
							    	</tr><tr>
					<%
								  }
					%>
							   <td class='formtxtf' nowrap> <%=extraCover[m][1]%></td>
					<%			if(l==2)
									l=0;
							   	 l++;
						  	  }
					  		}
					  	}
					
					%>
					  </tr>
					  </table>
			       </td>
			      <td class='formtxtf' width='20%'>&nbsp;&nbsp;
			       <%if(warratedest!=null && warratedest.length()== modeMaster.length){%>
					<%=fmt4.format(Double.parseDouble(warratedest_1[i]))%>
					<%}else{ %>
					<%=fmt4.format(Double.parseDouble("0.0"))%>
					<%} %></td>
		           <td class='formtxtf' width="35%" ><%=fmt.format(Double.parseDouble(""+moT[i][2]==null?"":moT[i][2]))%></td>
		     
				  <%}%>
                          <% double total1 = 0;
                          for(i=0;i<moT.length;i++)
				    {
				    total1 += Double.parseDouble(""+moT[i][2]==null?"":moT[i][2]);
				  %>
		     <%}
		     				
				%>
				</td>
				 </tr>
		          --%>
	<%
		String valueBasis = "";
		valueBasis = beans.getValueBasis(proposalNo,belongingBranch);
		if(valueBasis!=null && valueBasis.length()>0 && valueBasis.lastIndexOf(", ")!=-1) {
			valueBasis=valueBasis.substring(0, valueBasis.lastIndexOf(", "));
		}
	%>
	<tr>
		<td class="formtxtf1" style="padding: 5px; font-weight: bolder;">Value Basis</td>
		<td colspan="3" class="formtxtf1" style="padding: 5px;">
			<%=valueBasis%>
		</td>
	</tr>
</table>
<br>
<table border="0" cellpadding="2" cellspacing="2" width="100%">
	<tr>
		<td><span class='heading'>Category Of Goods Summary</span></td>
	</tr>
</table>
<div style="width:100%;overflow:scroll;overflow-y:hidden;">
<table class="footable" width="100%">
	<thead>
	<tr>
		<th width="1%">SNo</th>
		<th width="25%">Category Of Goods</th>
		<%
			String[][] coverTypeMaster=(String[][]) new PremiumInputsBean().getSeaCoverValues(belongingBranch);
			Map coverType=new HashMap();
			List typeList=new ArrayList();
			Map modeList=new HashMap();
			String ids[]=new String[0];
			int typeCount=0;
			String modes=null;
			String modeName="";
			String coverNames="";
			 countSea=0;
			Map rateList=new HashMap();
			for( i=0;i<getDetail.length;i++) {
				if(i==0)
					modes=getDetail[i][0];
				ids=(getDetail[i][4]==null?"":getDetail[i][4]).split(",");						
				if(!modes.equalsIgnoreCase(getDetail[i][0])) {
					modeName=modeName+","+(getDetail[i-1][2]+"-"+(countSea));
					modes=getDetail[i][0];
					//countSea=1;
					countSea=ids.length;
					if((getDetail.length-1)==i)
					modeName=modeName+","+(getDetail[i][2]+"-"+(countSea));
				} else {
					//countSea++;
					countSea+=ids.length;
					modes=getDetail[i][0];
					if((getDetail.length-1)==i && i!=0)
						modeName=modeName+","+(getDetail[i-1][2]+"-"+(countSea));
					if((getDetail.length-1)==i && i==0)
						modeName=modeName+","+(getDetail[i][2]+"-"+(countSea));
				}
				coverNames=coverNames+","+(getDetail[i][1]+"-"+getDetail[i][3]);
				coverType.put(getDetail[i][1],(String)getDetail[i][4]);
				modeList.put(getDetail[i][1],(String)getDetail[i][0]);
			}
			wsrc = modeName;
			modeName=modeName.substring(1,modeName.length());
			coverNames=coverNames.substring(1,coverNames.length());
			tokens=new StringTokenizer(modeName,",");
			String covers=null;
			while(tokens.hasMoreTokens())
			{
				covers=(String)tokens.nextToken();
		%>
		<th width='25%' colspan="<%=covers.substring(covers.indexOf('-')+1,covers.length())%>">
				<strong><%=covers.substring(0,covers.indexOf('-'))%></strong>
		</th>
		<% } %>		
	</tr>
	<tr>
		<th>&nbsp;</th>
		<th>&nbsp;</th>
		<%	tokens=new StringTokenizer(modeName,",");
			covers=null;
			while(tokens.hasMoreTokens()) {
				covers=(String)tokens.nextToken();
		%>
		<th width='25%' colspan="<%=covers.substring(covers.indexOf('-')+1,covers.length())%>">
			Base Rate (%)
		</th>
		<% } %>
	</tr>
	<tr>
		<th>&nbsp;</th>
		<th>&nbsp;</th>
		<%	tokens=new StringTokenizer(coverNames,",");
			String coverIds=null;
			while(tokens.hasMoreTokens()) {
				covers=(String)tokens.nextToken();
				coverIds=covers.substring(0,covers.indexOf('-'));
				String coverTypeIds=(String)coverType.get(covers.substring(0,covers.indexOf('-')));
				if(coverTypeIds!=null && coverTypeIds.length()>0 && !"null".equalsIgnoreCase(coverTypeIds)) {
					ids= coverTypeIds.split(",");
					for(int c=0; c<coverTypeMaster.length; c++) {
						if(coverTypeMaster[c][0].equalsIgnoreCase((String)modeList.get(covers.substring(0,covers.indexOf('-'))))) {
								for(int k=0; k<ids.length; k++) {
									if(coverTypeMaster[c][1].equalsIgnoreCase(ids[k])) {
										typeList.add(coverTypeMaster[c][2]);
									}
								}
							}
						}
					}else {
						ids=new String[0];
						typeList.add("&nbsp;");
						typeCount++;
					}
		%>
		<th colspan="<%=ids.length %>"><%=covers.substring(covers.indexOf('-')+1,covers.length())%></th>
		<%		} %>
	</tr>
	<%if(typeList!=null && typeList.size()>0 && typeList.size()!=typeCount) { %>
	<tr>
		<th>&nbsp;</th>
		<th>&nbsp;</th>
		<%for(int l=0; l<typeList.size(); l++) { %>
		<th><%=typeList.get(l)%></th>
		<%}%>
	</tr>
	</thead>	
	<%}
		modeName="0";
		getDetail=(String[][])rateModify.get(1);
		getExisting=(String[][])rateModify.get(2);
		for( i=0;i<getDetail.length;i++)
		{
			modeName="0";
	%>
	<tbody>
	<tr>
		<td><%=(i+1)%></td>
		<td><%=(getDetail[i][1]==null?getDetail[i][2]:((getDetail[i][1].trim()).length()<=1?getDetail[i][2]:getDetail[i][1]))%></td>
		<%
			tokens=new StringTokenizer(coverNames,",");
			while(tokens.hasMoreTokens()) {
				covers=(String)tokens.nextToken();
				//New
				String coverTypeIds=(String)coverType.get(covers.substring(0,covers.indexOf('-')));
				ids=new String[0];
				if(coverTypeIds!=null && !"null".equalsIgnoreCase(coverTypeIds) && coverTypeIds.length()>0) {
					ids= coverTypeIds.split(",");
				}
				rateList=new HashMap();
				//
				for(int j=0;j<getExisting.length;j++) {
					if(ids!=null && ids.length>0) {
						for(int d=0;d<ids.length;d++) {
							if(getExisting[j][0].equalsIgnoreCase(getDetail[i][0]) && getExisting[j][1].equalsIgnoreCase(covers.substring(0,covers.indexOf('-'))) && ids[d].equalsIgnoreCase(getExisting[j][3])) {
								rateList.put(ids[d],""+Double.parseDouble(getExisting[j][2]==null || "null".equals(getExisting[j][2])?"0":getExisting[j][2]));
							}
						}
					}else if(getExisting[j][0].equalsIgnoreCase(getDetail[i][0]) && getExisting[j][1].equalsIgnoreCase(covers.substring(0,covers.indexOf('-'))) && "0".equalsIgnoreCase(getExisting[j][3]))
						modeName=getExisting[j][2]==null?"0":getExisting[j][2];
				}
			modeName=""+fmt3.format(Double.parseDouble(modeName));
		%>
		<% 	
			if(coverTypeIds!=null && !"null".equalsIgnoreCase(coverTypeIds) && coverTypeIds.length()>0) {
				for(int c=0; c<coverTypeMaster.length; c++)	{
					if(coverTypeMaster[c][0].equalsIgnoreCase((String)modeList.get(covers.substring(0,covers.indexOf('-'))))) {
						for(int k=0; k<ids.length; k++)	{
							if(coverTypeMaster[c][1].equalsIgnoreCase(ids[k])) {
								modeName="0";
								if(rateList.containsKey(ids[k]))
									modeName=(String)rateList.get(ids[k]);
									modeName=""+fmt3.format(Double.parseDouble(modeName));
		%>
		<td align="center" width="12.5%"><%=request.getParameter("rate_"+(i+1)+"_"+covers.substring(0,covers.indexOf('-')))!=null?(request.getParameter("rate_"+(i+1)+"_"+covers.substring(0,covers.indexOf('-')))):(Double.parseDouble(modeName)<0?"":modeName)%></td>
		<%}}}}}else{ %>
		<td align="center" width="12.5%"><%=request.getParameter("rate_"+(i+1)+"_"+covers.substring(0,covers.indexOf('-')))!=null?(request.getParameter("rate_"+(i+1)+"_"+covers.substring(0,covers.indexOf('-')))):(Double.parseDouble(modeName)<0?"":modeName)%></td>
		<%} }%>
	</tr>
	<% } %>
	</tbody>
</table>	
</div>
<%
	String freeText = "";
	freeText = beans.getFreeTextStatus((String)session.getAttribute("proposalNo"));
	String wsrcRoyal = "";
	wsrcRoyal = request.getParameter("wsrc")==null?"":request.getParameter("wsrc");
	if(wsrcRoyal.length()<=0)
		wsrcRoyal = beans.getWSRCOpt((String)session.getAttribute("proposalNo"));
	if(wsrcRoyal.equalsIgnoreCase("Y")) {	
%>
<table border="0" cellpadding="2" cellspacing="2" width="100%">
	<tr>
		<td><span class='heading'>War Rate</span></td>
	</tr>
</table>
<table class="footable" width="100%">
	<thead>
	<tr>
		<th width="5%">SNo</th>
		<th width="45%">Mode of Transport</th>
		<th width="40%">War Rate (%)</th>
	</tr>
	</thead>
	<tbody>
	<%for(int j=0; j<modeMaster.length; j++) {%>
	<tr>
		<td><%=j+1%></td>
		<td><%= modeMaster[j][1]%></td>
		<%	for(int w=0; w<warRateInfo.length; w++) {
				String warRate[]=warRateInfo[w].split("~");
				if(modeMaster[j][0].equalsIgnoreCase(warRate[0])) {
		%>
		<td align="center"><%=warRate[1]%></td>
		<%}} %>
	</tr>
	<%} %>
	</tbody>
</table>
<%}%>
<br>	
<%if (deductible.length>0){ %>
<table border="0" cellpadding="2" cellspacing="2" width="100%">
	<tr>
		<td><span class='heading'>Deductibles</span></td>
	</tr>
</table>
<table class="footable" width="100%">
	<thead>
	<tr>
		<th width="20%">Start Range</th>
		<th width="20%">End Range</th>
		<th width="20%">Excess Amount</th>
		<th width="20%">Currency</th>
		<!--<th width="20%">Excess Percent</th>
	--></tr>
	</thead>
	<tbody>
	<%for(int j=0; j<deductible.length; j++) {%>
	<tr>
		<td><%=fmt.format(Double.parseDouble(deductible[j][0]))==null?"":fmt.format(Double.parseDouble(deductible[j][0]))%></td>
		<td><%=fmt.format(Double.parseDouble(deductible[j][1]))==null?"":fmt.format(Double.parseDouble(deductible[j][1]))%></td>
		<td align="right"><%=fmt.format(Double.parseDouble(deductible[j][2]))==null?"":fmt.format(Double.parseDouble(deductible[j][2]))%></td>
		<td><%=deductible[j][3]==null?"":deductible[j][3]%></td>
		<!--<td><%=fmt.format(Double.parseDouble(deductible[j][4]))==null?"":fmt.format(Double.parseDouble(deductible[j][4]))%></td>
	--></tr>
	<%} %>
	</tbody>
</table>	
<br/>	
<%} %>
<table border="0" cellpadding="2" cellspacing="2" width="100%">
	<tr>
		<td><span class='heading'>Per Bottom Limit</span></td>
	</tr>
</table>
<table class="footable" width="100%">
	<thead>
	<tr>
		<th width="20%">Mode of Transport</th>
		<th width="30%">Coverages List</th>
		<th width="30%">Per Bottom Limit (<%=currencyType%>)</th>
		<th width="20%">Clauses</th>
	</tr>
	</thead>
	<tbody>
	<%	countSea = 0; countAir = 0; countRoad = 0;
		for( i=0;i<coverName.length;i++) {
			if("Sea".equalsIgnoreCase(coverName[i][2])) {
				Seacommodit = "SEA";
				countSea++;
			}
			if("AIR".equalsIgnoreCase(coverName[i][2])) {
				countAir++;
				Aircommodit = "AIR";
			}
			if("LAND".equalsIgnoreCase(coverName[i][2])) {
				countRoad++;
				Roadcommodit = "LAND";
			}
		}
		
		for(i=0;i<coverName.length;i++) {
	%>
	<tr>
		<td><%=coverName[i][2]%></td>
		<td><%=coverName[i][3]%></td>
		<td align="right"><%=fmt.format(Double.parseDouble(""+moT[i][2]==null?"":moT[i][2]))%></td>
		<td align="center"><a href="#" class="two" title="View Information" onClick="return viewClauseInf('<%=coverName[i][0]%>','<%=coverName[i][1]%>')"> <b> View </b></a></td>
	</tr>
	<%}%>
	</tbody>
</table>
<br>
<%if(depositList!=null && depositList.size()>0) {%>
<table border="0" cellpadding="2" cellspacing="2" width="100%">
	<tr>
		<td><span class='heading'>Deposit Information</span></td>
	</tr>
</table>
<table class="footable" width="100%">
	<thead>
	<tr>
		<th width="10%">SNo</th>
		<th width="20%">Deposit Type</th>
		<th width="20%">Installment Type</th>
		<th width="20%">Deposit Amount</th>
		<th width="10%">Issuance Fee</th>
		<th width="10%">Debit Note</th>
		<th width="10%">Credit Note</th>
	</tr>
	</thead>
	<tbody>
	<%  for(int d=0; d<depositList.size(); d++) {
		Map depositInfo=(Map)depositList.get(d);
	%>
	<tr>
		<td><%=d+1%></td>
		<td><%=openCover.replaceNull((String)depositInfo.get("DEPOSIT_TYPE"),"&nbsp;")%></td>
		<td><%=openCover.replaceNull((String)depositInfo.get("INSTALLMENT_TYPE"),"&nbsp;")%></td>
		<td align="right"><%=openCover.replaceNull((String)depositInfo.get("DEPOSIT_AMOUNT"),"&nbsp;")%></td>
		<td align="right"><%=openCover.replaceNull((String)depositInfo.get("ISSUANCE_FEE_DEBIT"),"&nbsp;")%></td>
		<%if(depositInfo.get("DEBIT_NOTE_NO")!=null && "N".equalsIgnoreCase((String)depositInfo.get("EXISTING_DEPOSIT_YN"))){ %>
		<td><a href="#" class="two" title="View Dedit" onclick="return viewPolicys('<%=opencoverNo%>','<%=(String)session.getAttribute("user")%>','debit','<%=openCover.replaceNull((String)depositInfo.get("DEBIT_NOTE_NO"),"")%>','<%=openCover.replaceNull((String)depositInfo.get("AMEND_ID"),"")%>');">Debit</a></td>
		<%}else{ %>
		<td>N/A</td>
		<%}if(depositInfo.get("CREDIT_NOTE_NO")!=null && "N".equalsIgnoreCase((String)depositInfo.get("EXISTING_DEPOSIT_YN"))){ %>
		<td><a href="#" class="two" title="View Credit" onclick="return viewPolicys('<%=opencoverNo%>','<%=(String)session.getAttribute("user")%>','credit','<%=openCover.replaceNull((String)depositInfo.get("CREDIT_NOTE_NO"),"")%>','<%=openCover.replaceNull((String)depositInfo.get("AMEND_ID"),"")%>');">Credit</a></td>
		<%}else{ %>
		<td>N/A</td>
		<%} %>
	</tr>
	<%}%>
	</tbody>
</table>
<br>
<%}%>
<table border="0" cellpadding="2" cellspacing="2" width="100%">
	<tr>
		<td align="center">
			<span class='heading'>Click Here for</span>
			<b>&nbsp;&nbsp;&nbsp;
			<%if(wsrcRoyal.equalsIgnoreCase("Y")){%>
				<a href="#" class="two" onClick="return Wsrcc(1)">WSRCC</a>&nbsp;&nbsp;&nbsp;
			<%}%>
				<a href="#" class="two" onClick="return Wsrcc(3)">Exclusions</a>&nbsp;&nbsp;&nbsp;
			<%if(freeText.equalsIgnoreCase("Y")){%>
				<a href="#" class="two" onClick="return Wsrcc(2)">Free Text</a>
			<%}%>
			</b>
		</td>
	</tr>
</table>
<br>
<table width="100%">
	<tr>
		<td align="center">
			<a href= "#" onClick='return back()' class="buttonsMenu" ><img src="<%=pathq%>/images/Back.jpg" ></a>
			&nbsp;&nbsp;&nbsp;
			<a href="#" onClick= 'return printQuote()' class="buttonsMenu" ><img src="<%=pathq%>/images/PrintQuote.jpg" ></a>
			&nbsp;&nbsp;&nbsp;
			<a href="#" onClick= 'proceed()' class="buttonsMenu" ><img src="<%=pathq%>/images/Proceed.jpg">	</a>
		</td>
	</tr>
</table>
<input type = "hidden" name = "coverName">
<input type = "hidden" name = "coverId">
<input type = "hidden" name = "proposal">
<input type = "hidden" name = "opt1">
<input type = "hidden" name = "opt2">
</form>

	</td>
</tr>
</table>
</body>
<script language = "javascript">


<%if("Y".equalsIgnoreCase(depositeData[0][0])){
	if("N".equalsIgnoreCase(depositeData[0][9])){%>
	toggleDisplayTemp('DepositInfo', 'Y');
	<%	if("5".equalsIgnoreCase(depositeData[0][1])&&depositeData[0][1]!=null){%>
	toggleDisplay('InstallInfo', 'Y')
<%}
}else{%>	
	toggleDisplayTemp('DepositInfo', 'O');
	<%	if("5".equalsIgnoreCase(depositeData[0][1])&&depositeData[0][1]!=null){%>
	toggleDisplay('InstallInfo', 'Y')
	
<%}}}%>
  
 function toggleDisplayTemp(element, val) 
{	
 if(val=='Y')
	{
		document.getElementById('DepositYes').checked=true;
	}
 if(val=='O')
	{
		document.getElementById('DepositOld').checked=true;
	}
toggleDisplay(element,val)
} 
function viewClauseInf(coverName, coverId) {
	document.form1.coverId.value = coverId;
	document.form1.coverName.value = coverName;
	testwindow= window.open ("showPremiumClausesEdit.jsp?coverId="+coverId, "mywindow",
	"location=1,status=1,scrollbars=1,width=600,height=400");
	testwindow.moveTo(200,200);
	return false;
}

function PopupInsurance(k) {
	document.form1.proposal.value = k;
	testwindow1 = window.open("showCompanyDetails.jsp?proposal="+k,"mywind","location=1,status=yes,scrollbars=1,width=400, toolbar = no, directories = no, menubar = no, addressbar = no, height = 200");
	testwindow1.moveTo(200,200);
	return false;
}
   
function showWSRCC() {
	 	var URL = '';
  	   	URL='showWSRCC.jsp';
		var windowName = "showWSRCC";
		var width  = screen.availWidth;
		var height = screen.availHeight;
		var h=400;
		var w=700;
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
		
	function cancelAction()
	{
		document.form1.action = "ShowOpenCoverEntry.jsp"
		document.form1.submit();
	}

	function back()
	{
		document.form1.action="Premium.jsp";
		document.form1.submit();
		return false;
	}

	function ActionBack()
	{
		document.form1.submit();
	}
   
	function Warranties()
  	{
		var URL = '';
		URL='Warranties.jsp';
		var windowName = "Warranties";
		var width  = screen.availWidth;
		var height = screen.availHeight;
		var h=400;
		var w=700;
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
    function printQuote()
    {
		var URL = '';
		URL='PrintOpencoverSummary.jsp';
		var windowName = "PrintOpencoverSummary";
		var width  =	 screen.availWidth;
		var height = screen.availHeight;
		var w = 900;
		var h =	400;
		var features =
		'width='          + w +
		',height='		  + h +
		',left='		  + ((width - w) * .5)  +
		',top='			  + ((height - h ) * .5) +
		',directories=no' +
		',location=no'	  +
		',menubar=no'	  +
		',scrollbars=yes' +
		',status=no'	  +
		',toolbar=no'	  +
		',resizable=false';
		var strOpen = window.open (URL, windowName, features);
		strOpen.focus();
		strOpen.focus();
		return false;
	}

	function Wsrcc(id)
	{
		var URL = '';
		if(id==1)
		URL='showWSRCC.jsp';
		else if(id==2)
		{
			URL='showCoverages.jsp?id=1';
		}
		else if(id==3)
		URL = 'showExclusions.jsp';
		var windowName = "WSRCC";
		var width  = screen.availWidth;
		var height = screen.availHeight;
		var h=400;
		var w=700;
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
<%if(opencoverNo!=null && opencoverNo.length()>0){%>
toggleDisplay("DebitInfo", "Y");
<%}%>
  
function toggleDisplay(element, val) 
{
	if(val=='Y' || val=='5' || val=='O')
	{
		document.getElementById(element).style.display="block";
	}
	else
	{
		document.getElementById(element).style.display="none";
	}
	return false;
}
function checkNo(val) 
{
	if(val=='N')
	{
		document.getElementById('DepositYes').checked=false;
		document.getElementById('DepositNo').checked=true;
	}
	return false;
}
function proceed()
{
	try
	{
		var error='';
		
		if((document.getElementById('DepositYes') && document.getElementById('DepositYes').checked)||(document.getElementById('DepositOld') && document.getElementById('DepositOld').checked))
		{
			if(document.getElementById('depositType').value=='')
			{
				error+='- Please select Deposit Type\n';
			}else if(document.getElementById('depositType').value=='5' && document.getElementById('installType').value=='')
			{
				error+='- Please select Installment Type\n';
			}
			var marinePremium=document.getElementById('marinePremium').value;
			var warPremium=document.getElementById('warPremium').value;
			marinePremium=marinePremium==''?0:marinePremium;
			warPremium=warPremium==''?0:warPremium;
			if((parseInt(marinePremium)+parseInt(warPremium))<=0)
			{
				error+='- Deposit Amount cannot be empty or zero\n';
			}
		}
		if(error=='')
		{
			document.form1.submit();
		}else
		{
			alert(error);
		}
	}catch(e){alert(e);}
	return false;
}
function checkNumbers(val){
	var value=val.value;
	var temp="";
	if(value!=null){
		temp=value.charAt(value.length-1);
		if((isNaN(temp) || temp==" ")&& temp!='.'){
			val.value=value.substring(0,value.length-1);
		}else if(value.indexOf('.')!=-1){
			val.value = value.substring(0,value.indexOf('.')) + value.substring(value.indexOf('.'),(value.indexOf('.')+5));
		}
	}	
}
function viewPolicys(policynumber,loginId,docType,docNo,amendId)
{
	     var URL ="<%=pathq%>/GenerateDocument.jspa?docType="+docType+"&policynumber="+policynumber+"&loginId="+loginId+"&docNo="+docNo+"&amendId="+amendId;
		 var windowName = "PolicyView";
		 var width  = screen.availWidth;
		 var height = screen.availHeight;
		 var w = 900;
		 var h =	500;
		 var features =
			'width='          + w +
			',height='		  + h +
			',left='		  + ((width - w - 10) * .5)  +
			',top='			  + ((height - h - 30) * .5) +
			',directories=no' +
			',location=no'	  +
			',menubar=no'	  +
			',scrollbars=yes' +
			',status=no'	  +
			',toolbar=no'	  +
			',resizable=false';
		var strOpen = window.open (URL, windowName, features);
		strOpen.focus();
		return false;
}

function textLimit(field, maxlen) 
{
	if (field.value.length > maxlen + 1)
		alert('Character is Exceed Maximum Length!');
	if (field.value.length > maxlen)
		field.value = field.value.substring(0, maxlen);
} 
$.noConflict();
  	jQuery(document).ready(function($) {
    $(":date").dateinput({
	format: 'dd/mm/yyyy',	// the format displayed for the user
	selectors: true,             	// whether month/year dropdowns are shown
	min: -730,                    // min selectable day (100 days backwards)
	max: 730,                    	// max selectable day (100 days onwards)
	offset: [10, 20],            	// tweak the position of the calendar
	speed: 'fast',               	// calendar reveal speed
	firstDay: 1                  	// which day starts a week. 0 = sunday, 1 = monday etc..
	});
  });
  </script>
</html>
<%}catch(Exception e){e.printStackTrace();}%>