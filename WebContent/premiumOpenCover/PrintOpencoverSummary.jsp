<%@ page language="java" import="java.util.*,java.sql.Connection" pageEncoding="UTF-8"%>
<%@page import="com.maan.premium.DAO.PremiumInputsBean"%>
<html>
<head>
	<title> Emirates -OpenCover Summary</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css">
	<link href="<%=request.getContextPath()%>/css/footable-0.1.css" rel="stylesheet" type="text/css">
	<style type="text/css">
	body {
		-webkit-print-color-adjust:exact;
	}
	</style>
</head>
<%
   String pathq = request.getContextPath();
   String basePaths = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+pathq+"/";
%>
<%
	String adminBranch = (String) session.getAttribute("AdminBranchCode");
	if(adminBranch.indexOf("'")!=-1) {
		adminBranch = adminBranch.replaceAll("'","");
	}
	String belongingBranch = (String) session.getAttribute("BelongingBranch");
	String cid = (String) session.getAttribute("AdminCountryId");
	String currencyType = (String) session.getAttribute("currencyType");
	String decimalPlace = (String) session.getAttribute("decimalPlace")== null ? "2" : (String) session.getAttribute("decimalPlace");

String wsrc = "";
StringTokenizer tokens=null;
String getExisting[][]=new String[0][0];

String proposalNo = "";//request.getParameter("proposalNo")==null?(String)session.getAttribute("proposalNo"): (String)request.getAttribute("proposalNo");
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
if(decimalPlace.equalsIgnoreCase("2"))
	fmt=new java.text.DecimalFormat("##,##0.00");
else if(decimalPlace.equalsIgnoreCase("3"))
	fmt=new java.text.DecimalFormat("##,##0.000");
else if(decimalPlace.equalsIgnoreCase("4"))
	fmt=new java.text.DecimalFormat("##,##0.0000");
else
	fmt=new java.text.DecimalFormat("##,##0.00");

java.text.NumberFormat fmx = new java.text.DecimalFormat("##,###");
java.text.NumberFormat fmt1=new java.text.DecimalFormat("0.00") ;
java.text.NumberFormat fmt3=new java.text.DecimalFormat("0.000000") ;
java.text.NumberFormat fmt2=new java.text.DecimalFormat("00.00") ;
java.text.NumberFormat fmt4=new java.text.DecimalFormat("#0.0000") ;
String productId = (String)session.getAttribute("product_id");
 
%>	
<%
String Aircommodit = "", Seacommodit="", Roadcommodit="";
int Airwidth = 0,Seawidth =0,Roadwidth=0;
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
<jsp:useBean id="theBean" class="com.maan.opencover.ConditionsOpenCover">
<jsp:setProperty name="theBean" property="*"/>
</jsp:useBean>
<%

	String portfolio = request.getParameter("portfolio") == null ? "" : request.getParameter("portfolio");
	if(portfolio.equalsIgnoreCase("portfolio")){
		proposalNo = request.getParameter("proposalNo") == null ? "" :request.getParameter("proposalNo");
	}else{
		proposalNo = (String) session.getAttribute("proposalNo")!=null?(String)session.getAttribute("proposalNo"):"00";
	}
	openCover.setProposalNo(proposalNo);
	openCover.setProductId(productId);
%>
		 		
<%
int count=0;
int i=0;
int index = 0;
int len = 0;
openCover.setProposalNo(proposalNo);
String[][] result= openCover.getDatas();
String[][] moT = openCover.getModeofTransport(belongingBranch,proposalNo);
String[][] colWise = openCover.getColumnWise(belongingBranch);
String[][] commId = openCover.getCommodityId();
String[][] coverName = openCover.getCoverName(belongingBranch);
String[][] branchDet = openCover.getBranchFullDetail((String)session.getAttribute("adminBranch"));
String interestGoods = openCover.getMOCGoodsName(belongingBranch);
String[][] modeMaster=null;
String[][] deductible=null;
String[][] coverMaster=null;
String opencoverNo ="";
String[][] ckId = null;

opencoverNo = (String)session.getAttribute("openCoverNo")==null?"":(String)session.getAttribute("openCoverNo");
String[][] extraCover=null;

java.util.HashMap countryDetails = new java.util.HashMap();
openCover.setProposalNo(proposalNo);
countryDetails = openCover.getCountryDetails();
dest_id = (String)countryDetails.get("dest_id");

warratedest = (String)countryDetails.get("warrate");

String p = "";

  StringTokenizer st5 = new StringTokenizer(dest_id,",");
  while(st5.hasMoreElements())
  {
  String k2 = (String)st5.nextElement();
  count++;
  }
  Destnat_id = new String[count];
  StringTokenizer st6 = new StringTokenizer(dest_id,",");
  i=0;
  String v2 = "";
  while(st6.hasMoreElements())
  {
  Destnat_id[i] = (String)st6.nextElement();
  v2  = v2+ Destnat_id[i]+",";
  }
  v2 = v2.substring(0,v2.length()-1);

  openCover.setProposalNo(proposalNo);
  openCover.setCountyId(v2);
  
    String[][] CountryList1 = openCover.getCountryList();
  
  for(i=0;i<CountryList1.length;i++)
  {
 	// out.println("Result is" +CountryList1[i][0]);   //value of country
  }
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
  for(i=0;i<commId.length;i++)
  {
    p = p+commId[i][1]+",";
  }
  p = p.substring(0,p.length()-1);
  openCover.setProposalNo(proposalNo);
  String[][] coverId = openCover.getCoverId(p,belongingBranch);
  String coId = "";
  for(i=0;i<coverId.length;i++)
    {
     coId = coId+coverId[i][1]+",";
     }
     coId = coId.substring(0,coId.length()-1);
     

  java.util.ArrayList rateModify=new java.util.ArrayList();
  openCover.setProposalNo(proposalNo);
rateModify=openCover.getCommodityList(belongingBranch);
String getDetail[][]=null;

getDetail=(String[][])rateModify.get(0);


%>



<%
/*  Get the commodity Name */

%>


<%

/*  Get the Personal Information */

String[][] PersonalInf = openCover.getOpenCoverInformation();
String AdditionalCustomer = openCover.getAdditionalCustomer();
String[][] depositDetails = openCover.getOpenCoverDepostDetails(adminBranch);
//out.println("hahahahahah");
//if(true)return;

%>



<%   /*Mode master */
modeMaster=openCover.getModeMaster(belongingBranch);
deductible=openCover.getDeductible(adminBranch);
coverMaster=openCover.getCoverMaster(belongingBranch);
extraCover=openCover.getExtraCover();

%>


<%
/* Get the Broker details */
java.util.HashMap ht = new java.util.HashMap();
openCover.setProposalNo(proposalNo);
ht = openCover.getBrokerDetail();
br_comp = (String)ht.get("com_name");

if("0".equalsIgnoreCase(br_comp))
   {
   br_name = (String)ht.get("con_per");
   }
   else
    {
     br_name = (String)ht.get("com_name");
     //br_name = "M/s. "+br_name;
     }
%>
<%
//out.println("length is"+result.length);
for(i=0;i<result.length;i++)
  {
//    out.println("sss"+result[i][0]);
  }
  %>
<%
//out.println("Valus is"+result[0][1]);

%>  

<%
if("Y".equalsIgnoreCase(result[0][2]))
   {
      result[0][2] = "Yes";
      }
      else
       {
       result[0][2] = "No";
       }

ckId = openCover.getcheckInsuranceId();
for(i=0;i<ckId.length;i++)
 {
   ckid = ckId[0][0];
   }
   if(Integer.parseInt(ckid==null?"0":ckid) > 0)
     {
   		comp_Id = openCover.getInsuranceCompanyId();
		if(comp_Id.length>0)
  		{
		for(i=0;i<comp_Id.length;i++)
			 {
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
	String values= "";
	
	String endtYN=result[0][15]==null?"N":"Y";
	String endtType=result[0][16]==null?"":result[0][16];
	String endtTypeDesc="";
	if(endtType.length()>0){
		endtTypeDesc=openCover.getEndtTypeDescInfo(endtType);
	}
%>

<body onload="window.print()">  
<form name="form1" method="post" action = "numberGenerate.jspa">
		<table align="center">
		<tr> <td align="center"> <b>MARINE CARGO OPEN COVER </b></td>	</tr>
		</table>
		
		<%
			String branchCompany = "";
			String branchAdd1 = "";
			String branchPOB = "";
			String branchCity = "";
			String branchCountry = "";
			if(branchDet.length > 0)
			{
				branchCompany = branchDet[0][0];
				branchAdd1 = branchDet[0][1];
				branchPOB = branchDet[0][2];
				branchCity = branchDet[0][3];
				branchCountry = branchDet[0][4];
			}
		%>
		
		<table style="align:justify; font-family:Arial;font-size:12px;font-weight:normal;">
		<tr>
			<td><strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Welcome : <%=br_name%></strong></td>
			</tr>
			<tr><td> </td></tr>
			</table>
			
		<table width="95%" align="center" class="footable">
			<tbody>
				<tr>
					<td width="25%"><span class="heading">Core Application Policy No:</span></td>
					<td width="25%">
						<strong><%=(PersonalInf[0][12]==null?"Not Generated":PersonalInf[0][12])%></strong>
						<!--<td class='heading'> <strong> <%=(PersonalInf[0][12]==null?"":PersonalInf[0][12]) %></strong><%--<%=(String)session.getAttribute("missippiCode")%>--%></td>
						--><%
							String mopid = "";
							String busiType = "";
							mopid = (PersonalInf[0][10]==null?"":PersonalInf[0][10]);
							busiType = (PersonalInf[0][11]==null?"":PersonalInf[0][11]);
						%>
					</td>
					<td width="25%"><span class="heading">Proposal No:</span></td>
					<td width="25%"><%=proposalNo%></td>
				</tr>
				<tr>
					<td><b>Start Date</b></td>
					<td><%=result[0][0]==null?"":result[0][0]%></td>
					<% if(result[0][8].equalsIgnoreCase("One Stop")) { %>
					<td><b>End Date</b></td>
					<td><%=result[0][1]==null?"":result[0][1]%></td>
					<% }else{ %>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<% } %>
				</tr>
				<tr>
					<td><b>Product Type</b></td>
					<td><LABEL FOR='StartingDate' ACCESSKEY=S><%=result[0][3]%> </LABEL></td>
					<td><b>Cross Voyage</b></td>
					<td><%=result[0][2]%></td>
				</tr>
				<%if("Y".equalsIgnoreCase(endtYN) && endtTypeDesc.length()>0){%>
				<tr>
					<td><b>Endorsement Type</b></td>
					<td colspan="3"><%=endtTypeDesc%></td>
				</tr>
				<%} %>
			</tbody>
		</table>			
		<table  align="center" border="0" cellpadding="2" cellspacing="2" width="100%">
			<tr>
				<td><span class="heading">Proposal Information</span></td>
			</tr>		  
		   	<tr>
		   		<td>
		   			<table width="100%" class="footable">
		   				<tbody>
		   				<% for(i=0;i<PersonalInf.length;i++) {%>
		   				<tr>
		   					<td width="25%"><b>Broker Name</b></td>
		   					<td width="25%"><LABEL FOR='StartingDate' ACCESSKEY=S><%=br_name%></LABEL></td>
		   					<td width="25%"><b>Open Cover Type</b></td>
		   					<td width="25%"><LABEL FOR='StartingDate' ACCESSKEY=E><%=result[0][8].equalsIgnoreCase("One Stop")?"One Stop":result[0][8]%></LABEL></td>
		   				</tr>
		   				<tr>
		   					<td><b>Name Of Assured</b></td>
		   					<td colspan="3">
		   						<% if(AdditionalCustomer!=null && AdditionalCustomer.trim().length()>0){%>
								<%	   			 			out.print(AdditionalCustomer);
					   			}else if("0".equalsIgnoreCase(PersonalInf[i][2]) ){		   			   
					   			 %>
					   			   <LABEL FOR='StartingDate' ACCESSKEY=S>
					   			   
					   			   <%=PersonalInf[i][0]==null || "select".equalsIgnoreCase(PersonalInf[i][0])?"":PersonalInf[i][0]+"."%> <%=PersonalInf[i][1]%></LABEL>
					   			  <% }else {%>
					   			  <LABEL FOR='StartingDate' ACCESSKEY=S><%=PersonalInf[i][0]==null || "select".equalsIgnoreCase(PersonalInf[i][0])?"":PersonalInf[i][0]+"."%> <%=PersonalInf[i][2]%></LABEL>
					   			  <%} %>
		   					</td>
		   					<%--<td class='formtxtf'>No.of Back date Allowed</td>
							   				<td class='formtxtf'><%=PersonalInf[i][3]+""+"   days"%>				</td> 
							   				
							   				<td class='formtxtf'>&nbsp;&nbsp;&nbsp;Estimated Turn<br>&nbsp;&nbsp; Over Amount(<%=currencyType%>)</td>
							   			 <td class='formtxtf'>		   		   			 <% 
							 String tom="";
					  		   		
							
								   	 tom =""+Double.parseDouble(PersonalInf[i][4]);
										
										 %>
							     			   <LABEL FOR='StartingDate' ACCESSKEY=S><%=fmt.format(Double.parseDouble(""+PersonalInf[i][4]))%> </LABEL></td>
							   				
							   				</td>--%>
		   				</tr>
		   				<%-- <tr>
						   		   <td class='formtxtf'>&nbsp;&nbsp;&nbsp;Estimated Turn<br>&nbsp;&nbsp; Over Amount(<%=currencyType%>)</td>
						   			 <td class='formtxtf'>		   		   			 <% 
						 String tom="";
				  		   		
						
							   	 tom =""+Double.parseDouble(PersonalInf[i][4]);
									
									 %>
						     			   <LABEL FOR='StartingDate' ACCESSKEY=S><%=PersonalInf[i][4]%> </LABEL></td>
						     			   <td class='formtxtf'>Commission(%)</td>
						   		<td class='formtxtf'><%=PersonalInf[i][9]%>				</td> 
					
						   				</td>
						   					
						   </tr>--%>
						   <%		   		//{
		   						String Opt = "";
		   						if("Y".equalsIgnoreCase(PersonalInf[i][7])) { 
		   		  					Opt = "Yes";
		   		  				} else {
		   		  					Opt = "No";
		   		  				}
		  					%>
		  					<tr>
		  						<td><b>Estimated Turn<br>&nbsp;&nbsp; Over Amount(<%=currencyType%>)</b></td>
		  						<td>
		  							 <% String tom="";
			   	 						tom =""+Double.parseDouble(PersonalInf[i][4]); %>
		     			   			<LABEL FOR='StartingDate' ACCESSKEY=S><%=fmt.format(Double.parseDouble(""+PersonalInf[i][4]))%> </LABEL>
		  						</td>
		  							     	<%-- 	<td class='formtxtf'>&nbsp;&nbsp;&nbsp;Cross Voyage</td>
									   		<td class='formtxtf'><%=Opt%>				</td>
									  <td class='formtxtf'>Cross Voyage (%)</td>
									  		<td class='formtxtf'>
									  		
									  		   
									  		   	 <LABEL FOR='StartingDate' ACCESSKEY=S><%=PersonalInf[i][8]==null?"&nbsp;":PersonalInf[i][8]%> </LABEL></td>--%>
								<td><b>Minimum&nbsp;Premium(<%=currencyType%>)</b></td>
								<td><%=fmt.format(Long.parseLong(result[0][4]))%></td>
		  					</tr>
		  					<%--<tr>
					     		<td class='formtxtf'>&nbsp;&nbsp;&nbsp;Minimum&nbsp;Premium(<%=currencyType%>)</td>
						   		<td class='formtxtf'><%=fmt.format(Long.parseLong(result[0][4]))%>			</td>
						   		<td class='formtxtf'>&nbsp;</td><td class='formtxtf'>&nbsp;</td>
								<!--<td class='formtxtf'>Import Minimum Premium(<%=currencyType%>)</td>
						   		<td class='formtxtf'><%=fmt.format(Long.parseLong(result[0][5]))%>			</td>
							--></tr> --%>
							<tr>
								<td><b>Country Remarks</b></td>
								<td colspan="3"><%=result[0][9]==null?"":result[0][9]%></td>
								<!--<td class='formtxtf'>Cross Voyage Minimum Premium(<%=currencyType%>)</td>
							   		<td class='formtxtf'><%=fmt.format(Long.parseLong(result[0][7]))%>			</td>
								-->
							</tr>
							<!--<tr>
		   <td class='formtxtf'>&nbsp;&nbsp;&nbsp;Business Type</td>
		   <% 
			   busiType = (busiType!=null?busiType:"");
			   if(busiType.equalsIgnoreCase("0"))
					busiType = "Existing Business";
			   else if(busiType.equalsIgnoreCase("1"))
					busiType = "New Business";
			   else
				   busiType = "NIL";
			%>	
		  		<td class='formtxtf'><%=busiType%> </td>
		   		<td class='formtxtf'>&nbsp;&nbsp;&nbsp;&nbsp;</td>
		   		<td class='formtxtf'>&nbsp;&nbsp;&nbsp;&nbsp;</td>
		   		   					
		   </tr>
			--><!-- <tr>
				   		   <td class='formtxtf'>Cross Voyage (%)</td>
					<td class='formtxtf'>
					
					   
					   	 <LABEL FOR='StartingDate' ACCESSKEY=S><%=PersonalInf[i][8]%> </LABEL></td>
					   		<td class='formtxtf'>Commission</td>
					   		<td class='formtxtf'><%=PersonalInf[i][9]%>				</td>
					   		   		   				</td>
					   		<%}%>   					
					   </tr>-->
			
					<!--<tr> <td width="10%" colspan="3" class='heading'><strong>&nbsp;&nbsp;Risk Covered</strong></td> </tr>
					  <tr>
							<td width="20%" class='heading' align = "left"><strong>&nbsp;&nbsp;&nbsp;S.No</strong></td>
							<td width="30%" colspan="2" class='heading'><strong>Insurance Company</strong></td>
							<td width="30%" class='heading'><strong>Share in (%)</strong></td>
				      </tr>
							    <tr>
							   <%
							   for(i=0;i<PersonalInf.length;i++)
							     {
							     %>
							   <td width='20%' align='left'>&nbsp;&nbsp;&nbsp;1</td>
							   <td colspan="2"  width='40%' align='left'>Emirates</td>
							   <td width='40%' align='left'><%=fmt.format(Double.parseDouble(""+PersonalInf[i][5]))%></td>
							   <%}%>
							   </tr>
							   <%
							   int sno = 2;
							   
							   if(Integer.parseInt(ckid==null?"0":ckid) > 0)
							    {
							   for( i=0;i<comp_Id.length;i++)
							   {
							   %>
							   <tr>
							   <td width='20%' align='left'>&nbsp;&nbsp;&nbsp;<%=sno%></td>
							   <td colspan="2"  width='40%' align='left'><%=comp_Name[i][0]%></td>
							   <td width='40%' align='left'><%=fmt.format(Double.parseDouble(comp_Id[i][2]))%></td>
							   </tr>
							   <%
							   sno++;} }
							   %> --> 
		   				</tbody>
		   			</table>
		   		</td>
		   	</tr>
		</table>
		<br/>
		<% if(depositDetails.length>0) { %>	
			<table  align="center" border="0" cellpadding="2" cellspacing="2" width="100%">
				<tr>
					<td><span class="heading">Deposit Details</span></td>
				</tr>		  
			   	<tr>
			   		<td>
			   			<table class="footable" width="100%">
			   				<tbody>
			   					<tr>
				   					<td width="25%"><b>Deposit Premium</b></td>
				   					<td width="25%"><%=depositDetails[0][0]==null?"":depositDetails[0][0]%></td>
				   					<td width="25%"><b>Type of Deposit</b></td>
				   					<td width="25%"><%=depositDetails[0][1]==null?"":depositDetails[0][1]%></td>
				   				</tr>
			   					<tr>
				   					<td><b>Debit Note No</b></td>
				   					<td><%=depositDetails[0][2]==null?"":depositDetails[0][2]%></td>
				   					<td><b>Credit Note No</b></td>
				   					<td><%=depositDetails[0][3]==null?"":depositDetails[0][3]%></td>
				   				</tr>
				   				<tr>
				   					<td><b>Marine Premium</b></td>
				   					<td><%=depositDetails[0][4]==null?"":depositDetails[0][4]%></td>
				   					<td><b>War Premium</b></td>
				   					<td><%=depositDetails[0][5]==null?"":depositDetails[0][5]%></td>
				   				</tr>
				   				<tr>
				   					<td><b>Remarks</b></td>
				   					<td colspan="3"><%=depositDetails[0][6]==null?"":depositDetails[0][6]%></td>
				   				</tr>
			   				</tbody>
			   			</table>
			   		</td>
			   	</tr>
			</table>
			<br/>
		<% } %>	
		<table class="footable" width="100%">
			<thead>
			<tr>
				<th width="20%">Form of Transport</th>
				<th width="60%">Coverages</th>
				<th width="20%">Per Bottom Limit (<%=currencyType%>)</th>
			</tr>				
			</thead>
	     	<tbody>
     		<% for( i=0;i<modeMaster.length;i++) {%>
     		<tr>
     			<td><%=modeMaster[i][1]%></td>
     			<td>
     				<table width="100%">
     					<tr>
     					<% 	int k=0;
					  		for(int j=0;j<coverMaster.length;j++) {
							if(coverMaster[j][0].equalsIgnoreCase(modeMaster[i][0])) {
								if(k==2) {%>
						</tr>
						<tr>
								<%}%>
							<td >
		                    	<%=coverMaster[j][2]%>
		                    </td>
						      <%if(k==2)
									k=0;
						   	  		k++;
						  		}
					 		}
					 		if("1".equalsIgnoreCase(modeMaster[i][0])) {
								int l=0;
							%>
							<tr>
							<%
							if(extraCover.length >0) {
							  for(int m=0;m<extraCover.length;m++) {
								  if(l==2) {%>
					    	</tr>
					    	<tr><%}%>
							   <td  width="172" align="right">
		                    		<%=extraCover[m][1]%></td>
										<%	if(l==2) 
											l=0;
							   	  			l++;
								}
					  		}}%>
						</tr>
     				</table>
     			</td>
     			<!--<td><%=fmt.format(Double.parseDouble(""+moT[i][2]))%></td>
				  -->
				  <td><%=fmt.format(Double.parseDouble(""+modeMaster[i][2]))%></td>
				  <%}%>
                          <% double total1 = 0;
                          for(i=0;i<moT.length;i++)
				    {
				    total1 += Double.parseDouble(""+moT[i][2]);
				  %>
		     	<%}
				%>	
     		</tr>
     		<tr>
     			<%
			  		String valueBasis = "";
		  			valueBasis = beans.getValueBasis(proposalNo,belongingBranch);
		  			if(valueBasis!=null && valueBasis.length()>0 && valueBasis.lastIndexOf(", ")!=-1){
						valueBasis=valueBasis.substring(0, valueBasis.lastIndexOf(", "));
					}
			  %>
     			<td><b class="heading">Value Basis </b></td>
     			<td colspan="3"><%=valueBasis%></td>
     		</tr>
	     	</tbody>
		</table>
		<br/>
		
		<div style="width:100%;overflow:scroll;overflow-y:hidden;margin-left: 0px">
		<table  align="center" border="0" cellpadding="2" cellspacing="2" width="100%">
			<tr><td class="heading">Category Of Goods Summary</td></tr>
			<tr>
				<td>
					<table width="100%" class="footable">
						<thead>
						<tr>
							<th width="1%">SNo</th>
							<th width="25%">Category Of Goods</th>
							<%
								String[][] coverTypeMaster=(String[][])new PremiumInputsBean().getSeaCoverValues(belongingBranch);
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
											//out.println("<br>  n------->"+(getDetail[i-1][2]+"-"+(countSea)));
											modeName=modeName+","+(getDetail[i-1][2]+"-"+(countSea));
											//out.println("<br> NOT ");
											modes=getDetail[i][0];
											//countSea=1;
											countSea=ids.length;
											if((getDetail.length-1)==i)
											modeName=modeName+","+(getDetail[i][2]+"-"+(countSea));
										} else {
											//countSea++;
											countSea+=ids.length;
											//out.println("<br> EQUALS ");
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
								//out.println("coverName--->"+coverName);
								//out.println("modeName--->"+modeName);			
								tokens=new StringTokenizer(modeName,",");
								String covers=null;
								while(tokens.hasMoreTokens()) {
									covers=(String)tokens.nextToken();
							%>
							<th width="25%" colspan="<%=covers.substring(covers.indexOf('-')+1,covers.length())%>"><strong><%=covers.substring(0,covers.indexOf('-'))%></strong></th>
							<%}%>
						</tr>
						<tr>
							<th>&nbsp;</th>
							<th>&nbsp;</th>
							<%	tokens=new StringTokenizer(modeName,",");
						 		covers=null;
								while(tokens.hasMoreTokens()) {
									covers=(String)tokens.nextToken();
							%>
							<th width='25%' colspan="<%=covers.substring(covers.indexOf('-')+1,covers.length())%>"><strong>Base Rate (%)</strong></th>
							<%}%>
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
							<%}%>
						</tr>
						<% if(typeList!=null && typeList.size()>0 && typeList.size()!=typeCount) { %>
						<tr>
							<th>&nbsp;</th>
							<th>&nbsp;</th>
							<% for(int l=0; l<typeList.size(); l++) { %>
							<th><%=typeList.get(l)%></th>
							<%}%>
						</tr>
						<%}			
							modeName="0";
							getDetail=(String[][])rateModify.get(1);
							//if("edit".equalsIgnoreCase((String)session.getAttribute("mode")))
							getExisting=(String[][])rateModify.get(2);
							for( i=0;i<getDetail.length;i++)
							{modeName="0";%>
						</thead>
						<tbody>
						<tr>
							<td><%=(i+1)%></td>
							<td><%=(getDetail[i][1]==null?getDetail[i][2]:((getDetail[i][1].trim()).length()<=1?getDetail[i][2]:getDetail[i][1]))%></td>
							<%	tokens=new StringTokenizer(coverNames,",");
								while(tokens.hasMoreTokens()) {
									covers=(String)tokens.nextToken();
									//out.println("br>   covers1------>"+covers);
									//out.println("br>   commodityId------>"+getDetail[i][0]);
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
										for(int c=0; c<coverTypeMaster.length; c++) {
											if(coverTypeMaster[c][0].equalsIgnoreCase((String)modeList.get(covers.substring(0,covers.indexOf('-'))))) {
												for(int k=0; k<ids.length; k++) {
													if(coverTypeMaster[c][1].equalsIgnoreCase(ids[k])) {
														modeName="0";
														if(rateList.containsKey(ids[k]))
															modeName=(String)rateList.get(ids[k]);
															modeName=""+fmt3.format(Double.parseDouble(modeName));
							%>
							<td align="center"><%=request.getParameter("rate_"+(i+1)+"_"+covers.substring(0,covers.indexOf('-')))!=null?(request.getParameter("rate_"+(i+1)+"_"+covers.substring(0,covers.indexOf('-')))):(Double.parseDouble(modeName)<0?"":modeName)%></td>
							<%}}}}}else{ %>
							<td align="center"><%=request.getParameter("rate_"+(i+1)+"_"+covers.substring(0,covers.indexOf('-')))!=null?(request.getParameter("rate_"+(i+1)+"_"+covers.substring(0,covers.indexOf('-')))):(Double.parseDouble(modeName)<0?"":modeName)%></td>
							<%}%>
							<%}%>
						</tr>
						<% } %>
						</tbody>
					</table>
				</td>
			</tr>
		</table>		
   		</div>	
  <!-- Royal New Check -->
<%
//Rajesh New on 18/05
	String wsrcRoyal = "";
	wsrcRoyal = request.getParameter("wsrc")==null?"":request.getParameter("wsrc");
	if(wsrcRoyal.length()<=0)
		wsrcRoyal = beans.getWSRCOpt(proposalNo);
	if(wsrcRoyal.equalsIgnoreCase("Y")) {
%>
<table  align="center" border="0" cellpadding="2" cellspacing="2" width="100%">	
	<tr>
		<td><span class="heading">War Rate</span></td>
	</tr> 
	<tr>
		<td>
			<table class="footable" width="100%" >				
				<thead>
				<tr>
					<th width="5%">SNo</th>
					<th width="47.5%">Mode of Transport</th>
					<th width="47.5%">War Rate (%)</th>
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
					<td><%=warRate[1]%></td>
					<%}} %>
				</tr>
				<%} %>
				</tbody>
			</table>
			<%} %>
		</td>
	</tr>	  
</table>
<!-- Royal New Check -->
<!--for Clauses-->
<%if(deductible.length>0){ %>
<table align="center" border="0" cellpadding="2" cellspacing="2" width="100%">
	<tr>
		<td><span class="heading">Deductibles</span></td>
	</tr>
	<tr>
		<td>
			<table class="footable" width="100%">
				<thead>
				<tr>
					<th width="25%">Start Range</th>
					<th width="25%">End Range</th>
					<th width="25%">Excess Amount</th>
					<th width="25%">Currency</th>
				</tr>
				</thead>
				<tbody>
				<%for(int j=0; j<deductible.length; j++) {%>
				<tr>
					<td align="right"><%=fmt.format(Double.parseDouble(""+deductible[j][0]))==null?"":fmt.format(Double.parseDouble(""+deductible[j][0]))%></td>
					<td align="right"><%=fmt.format(Double.parseDouble(""+deductible[j][1]))==null?"":fmt.format(Double.parseDouble(""+deductible[j][1]))%></td>
					<td align="right"><%=fmt.format(Double.parseDouble(""+deductible[j][2]))==null?"":fmt.format(Double.parseDouble(""+deductible[j][2]))%></td>
					<td align="center"><%=deductible[j][3]==null?"":deductible[j][3]%></td>
				</tr>
				<%} %>
				</tbody>
			</table>
		</td>
	</tr>
</table>
<br/>
<%} %>
<%	count = 0;
	String coverss = request.getParameter("coverId");
	String[][] commodities=null;
	String Destnat_idx[] = new String[0];
	StringTokenizer st1 = new StringTokenizer(coId,",");
	while(st1.hasMoreElements()) {
		String k2 = (String)st1.nextElement();
		count++;
	}
	//out.println("count value is"+count);	
	Destnat_idx = new String[count];
	StringTokenizer st2 = new StringTokenizer(coId,",");
	i=0;
	String v3 = "";
	while(st2.hasMoreElements())  {
   		Destnat_idx[i] = (String)st2.nextElement();
    	//out.println("<br>value"+Destnat_idx[i]);
 		i++;	
  	}
	for(i=0;i<Destnat_idx.length;i++) {
   	//  out.println("<br>value"+Destnat_idx[i]);
    }
String cover="";
int cc=0;
int n=1;
if(coId.length()>0) {
   	  System.out.println("cover is present"+Destnat_idx.length);
%>
 <%--<tr><td> <font color = "blue" style="font-weight: bold">&nbsp;&nbsp;&nbsp;Clauses</font></td></tr>  --%>
<table align="center" border="0" cellpadding="2" cellspacing="2" width="100%">	
<tr>
		<td><span class="heading">Clauses</span></td>
	</tr>
<%	
 for(i=0;i<Destnat_idx.length;i++)
   {
     cc++;
	 System.out.println("cc is"+cc);
	 if(coverName!=null && coverName.length>0){
		 for(int k=0; k<coverName.length; k++){
		 	if(Destnat_idx[i].equalsIgnoreCase(coverName[k][1])){
		 		cover=coverName[k][3];
		 	}
		 }
	 }
	 commodities=com.maan.services.util.runner.multipleSelection("select clauses_id,clauses_description from open_cover_clauses where status='Y' and extra_cover_id ='0' and cover_id in ("+Destnat_idx[i]+") and proposal_no = '"+proposalNo+"'  and amend_id in (select max(amend_id) from open_cover_clauses where status='Y' and extra_cover_id ='0' and cover_id in ("+Destnat_idx[i]+")  and proposal_no = '"+proposalNo+"')");
 
 	if(commodities.length > 0) {%>
 	<tr ><td colspan="2"> <b><%=cover %></b></td></tr>	
 	<% for(int jj=0;jj<commodities.length;jj++) { %> 			
		<tr><td><b><%=n%>)</b></td>
			<td><%=commodities[jj][1]%>													
			</td>
		</tr>
 	<% 		n++;
 		}
 	%>
 	<% } 
 }
 	
} %>
<% commodities= openCover.getWSRCC(proposalNo); %>
<% 	n=1;
	if(commodities.length > 0) {%>
	<tr><td colspan="2"> <font size = '2' class="heading">&nbsp;<b> Warranties </b></font></td></tr>
<%for(i=0;i<commodities.length;i++) { %>
	<tr><td><b><%=n%>)</b></td>
		<td><font size = '2'>&nbsp;&nbsp; <%=commodities[i][1]%></td>
	</tr>
<%n++; } %>
<% } %>
<!--  for Exclusions  -->
<%
commodities=null;
commodities=com.maan.services.util.runner.multipleSelection("select occmss.exclusion_id,occmss.exclusion_description,to_char(occmss.EFFECTIVE_DATE,'dd-mm-yyyy') as effectDate  from open_cover_exclusions occmss,open_cover_position_master ocpmss where ocpmss.proposal_no ='"+proposalNo+"' and ocpmss.proposal_no=occmss.proposal_no and occmss.amend_id in (select max(occms.amend_id) from open_cover_exclusions occms,open_cover_position_master ocpms where ocpms.proposal_no ='"+proposalNo+"' and ocpms.proposal_no=occms.proposal_no)");
%>
<%	n=1;
	if(commodities.length > 0) {%>
	<tr><td colspan="2"> <font size = '2' class="heading"><b> Exclusions </b></font></td></tr>
<%	for(i=0;i<commodities.length;i++) { %>
	<tr><td><b><%=n%>)</b></td>
		<td><%=commodities[i][1]%></td>
	</tr>
<% n++;} %>
<%	}%>
<!--  for WSRCC  -->
<%
String wsrcc =null;
int sea=0, air=0,road=0,multimode=0;
	wsrcc = (String)session.getAttribute("coverName");
	//out.println("Wsrcc is"+wsrcc);
	/*air = wsrcc.lastIndexOf("AIR");
	road = wsrcc.lastIndexOf("ROAD");
	sea = wsrcc.lastIndexOf("SEA");
	sea = wsrcc.lastIndexOf("SEA");
	multimode = wsrcc.lastIndexOf("MULTI MODE");
	if(air>0)
	{
	   air=2;
	}
	else
	{
	  air = 101;
	}
	if(sea>0)
	{
	   sea = 1;
	}
	else
	{
	   sea = 101;
	}
	if(multimode>0)
	{
	   multimode = 4;
	}
	else
	{
	   multimode = 101;
	}*/

	if(road != 1 )
		 {
		theBean.setProposalNo(proposalNo);
		String extraCoverIds=theBean.getExtraCoverIds(proposalNo, (String) session.getAttribute("AdminBranchCode"));				 
		commodities= theBean.getWsrccFromOpenCoverMasterResult(extraCoverIds);
		
			if(commodities.length > 0)
						  {%>
	<tr><td colspan="2"><font size = '2' class="heading"><b> War & SRCC</b></font></td></tr>						
	<%
	n =1;String temp="";
	for(i=0;i<commodities.length;i++) {	
		if(coverName!=null && coverName.length>0){
			for(int k=0; k<coverName.length; k++){
		 		if(commodities[i][3].equalsIgnoreCase(coverName[k][1])){
		 		cover=coverName[k][3];
		 	}
		 }
	 }
	 if(!temp.equals(cover)){
	 temp=cover;
	%>
	<tr ><td colspan="2"> <b><%=cover %></b></td></tr>
<%} %>						
	<tr><td><b><%=n%>)</b></td>
		<td><font size = '2'>&nbsp;&nbsp;<%=commodities[i][1]%></td></tr>
<%n++;} } }	
	String freeTextYN = beans.getFreeTextStatus((String)session.getAttribute("proposalNo"));
	if("Y".equalsIgnoreCase(freeTextYN)){
	%>
	<!--  for free text  -->
</table>
<table cellpadding="0" cellspacing="0">
	<%
	count=0;	
	String freeText[][] = null;
	int cou=0;
	String value ="";
	String bulk="";
	commodities=openCover.showCoverages(proposalNo,belongingBranch);
	//System.out.println("len"+commodities.length);
	i=0;
	String v="";
	for(i=0;i<commodities.length;i++)
	  {
	    v = v+commodities[i][1]+",";
	    //out.println("value is"+commodities[i][3]);
	    }
	    //v = v+"101"+","+"102"+","+"103"+;
	    v = v.substring(0,v.length()-1);
	  //  out.println("coverage value is"+v);
	   if(sea == 1)
	     {
	     //out.println("Lendth===>"+commodities.length);
	     for(i=0;i<commodities.length;i++)
	     {
	     if(value.length()>1)
		break;
	     	    	
	     		if(commodities[i][4].equalsIgnoreCase("1") && commodities[i][2]!=null)
	     	{
	     			StringTokenizer stokens=new StringTokenizer(commodities[i][2],",");
	     			while(stokens.hasMoreTokens())
	     		{
	     					value=stokens.nextToken();
	     					if("FCL".equalsIgnoreCase(value))
	     				values="101";
	     					else if("LCL".equalsIgnoreCase(value))
	     						values="102";
	     					else if("BREAK BULK".equalsIgnoreCase(value))
	     						values="103";
	     					else if("BULK".equalsIgnoreCase(value))
	     						values="104";
	     					else 
	     						values="0";
	     			bulk  = bulk+values+",";

			}
			
		}	
	     
	     }
	     
	    v = v+","+bulk;
	      }
	    v = v+",105,106";
	    String free_idx[] = new String[0];
	     
	     int jjj=0;
	     n=1;
	      StringTokenizer stk = new StringTokenizer(v,",");
	       while(stk.hasMoreElements())
	       {
	       v2 = (String)stk.nextElement();
	          count++;
	       }
	     	       free_idx = new String[count];
	       StringTokenizer stk1 = new StringTokenizer(v,",");
	       
	       while(stk1.hasMoreElements())
	       {
	       free_idx[jjj] = (String)stk1.nextElement();
	     	jjj++;	
	      }
	     
		if(free_idx.length > 0)
				 {cou = 1;%>
								
								<%for(i=0;i<free_idx.length;i++)
								{
								
				%>
						
				<%commodities=com.maan.services.util.runner.multipleSelection("select free_text_id,free_text_description from open_cover_free_text where proposal_no='"+proposalNo+"' and cover_id ='"+free_idx[i]+"' and amend_id=(select max(amend_id) from open_cover_free_text where proposal_no='"+proposalNo+"' and cover_id='"+free_idx[i]+"')and free_text_description != 'null' ");%>
				<%
				if(commodities.length>0)
				   {
				   if(cou == 1)
				    {
				   %>
											
					 <br>
				<font size = '2' class="heading">&nbsp;&nbsp;&nbsp;<b> Free text</font></b>
						<br>
							
					<%
					cou++;}
					if("105".equals(free_idx[i])){
						cover="WARRANTIES";
					}else if("106".equals(free_idx[i])){
						cover="EXCLUSIONS";
					}else if(coverName!=null && coverName.length>0){
						 for(int k=0; k<coverName.length; k++){
						 	if(free_idx[i].equalsIgnoreCase(coverName[k][1])){
						 		cover=coverName[k][3];
						 	}
						 }
					 }
					 %>
					 <tr><td colspan="2"><strong>&nbsp;&nbsp;</strong></td></tr>
					 <tr><td colspan="2"><strong>&nbsp;&nbsp;<%=cover%></strong></td></tr>
			 <%}	
				for(int kk=0;kk<commodities.length;kk++)
				  {%>
				  <tr ><td style="font-size: 13px;">&nbsp;&nbsp;<b><%=n%>)</b></td><td style="font-size: 13px;">&nbsp;&nbsp;<%=commodities[kk][1]%></td></tr>
				 <% n++;
				  	
				
	}}
	     
	    }
	                                                                                      
	%>
</table>
<%} %>
 <input type = "hidden" name = "coverName">
  		  <input type = "hidden" name = "coverId">
  		  <input type = "hidden" name = "proposal">
  		  <input type = "hidden" name = "opt1">
  		  <input type = "hidden" name = "opt2">
</form>		
</body>
</html>