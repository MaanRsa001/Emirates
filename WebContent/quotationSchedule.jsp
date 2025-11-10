<%try{ %>
<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1" isELIgnored ="false" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="<%=path%>/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/css/displaytag.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="<%=path %>/css/calendar1.js"></script>

<jsp:useBean id="pol" class="com.maan.services.policyInfo">
		<jsp:setProperty name="pol" property="*" />
		</jsp:useBean>

 
<style type="text/css"> 
@media print {
thead { display: table-header-group; }
divbody{ display: block; }
}
@media screen {
thead { display: table-header-group; }
}
</style>
<%String userType = (String)session.getAttribute("usertype");%>
</head>
<!--<body onload="window.print();">  -->
<body>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td align="left" width="100%"> <img src="${pageContext.request.contextPath}/images/logo.jpg" border="0" width="200" height="50"></td>
	</tr>
	<tr>
		<td width="100%" align="center" colspan="2">
			<%
				com.maan.services.util.dataCollection collect = null;
				collect = new com.maan.services.util.dataCollection();				
				String[][] quoteInfo= new String[0][0];		
				String[][] commInfo=new String[0][0];				
				String applicationNo=collect.getApplicationNo(request.getParameter("quoteNo"));
				String loginBranch = (String)session.getAttribute("LoginBranchCode");
				//Belonging Branch
				String belongingBranch = (String)session.getAttribute("BelongingBranch");
				quoteInfo=collect.getQuoteInfo(applicationNo,belongingBranch);	
				commInfo=collect.getCommodityInfo(applicationNo,belongingBranch);
				String currencyType="";	
				String decimalPlace="";			
				HashMap brokerDetails = (HashMap)session.getAttribute("BrokerDetails");					
				if(brokerDetails.size()>0)
				{					
					currencyType = (String)brokerDetails.get("CurrencyAbb");
					System.out.print(currencyType);
					decimalPlace =(String) brokerDetails.get("CurrencyDecimal");
				}	
				java.text.NumberFormat fmt =null;
						if(decimalPlace.equalsIgnoreCase("2"))
							fmt=new java.text.DecimalFormat("##,##0.00");
						else if(decimalPlace.equalsIgnoreCase("3"))
							fmt=new java.text.DecimalFormat("##,##0.000");
						else if(decimalPlace.equalsIgnoreCase("4"))
							fmt=new java.text.DecimalFormat("##,##0.0000");
						else	
							fmt=new java.text.DecimalFormat("##,##0.00");	
				java.text.NumberFormat rate =new java.text.DecimalFormat("0.000000");
			%>
		</td>				
	</tr>

	<tr>
		<td align="center"  colspan="2">				
			<b><font style="font-weight: bold;font-size: 20px;text-decoration: underline;">Quotation Details</font></b>
		</td>				
	</tr>
	<tr><td width="1%"  colspan="2">&nbsp;</td></tr>
	<tr>
		<td width="100%" align="center" colspan="2">
			<table width="90%" cellpadding="0" cellspacing="6" align="center">			
	<tr valign="top">		 	
		<td width="13%" style="font-weight: bold;font-size: 15px;">Insured</td><td width="1%"><b>:</b></td>					
		<td width="40%" style="font-weight: bold;font-size: 15px;">
		<%=quoteInfo[0][17] == null ? "": quoteInfo[0][17]%>
		<%if(quoteInfo[0][27] != null && quoteInfo[0][27]!="" ){ %><br/>
		P.O. Box <%=quoteInfo[0][27]%>
		<%} %>
		<%if(quoteInfo[0][28] != null && quoteInfo[0][28]!="" ){ %><br/>
		<%=quoteInfo[0][28]%>
		<%} %>
		<%if(quoteInfo[0][29] != null && quoteInfo[0][29]!="" ){ %><br/>
		<%=quoteInfo[0][29]%>
		<%} %>	
		</td>			
	</tr>
	<tr valign="top">		 	
		<td width="13%" style="font-weight: bold;font-size: 15px;">Subject-Matter Insured</td><td width="1%"><b>:</b></td>
			<%
				if (commInfo.length > 1){
			%>
		<td width="40%" style="font-weight: bold;font-size: 15px;" valign="bottom"> 
			<table  width="100%" border="1" cellpadding="0" cellspacing="1">
				<tr>
					<td style="font-weight: bold;font-size: 15px;">S.No</td>
					<td style="font-weight: bold;font-size: 15px;">Commodity Name</td>
					<td style="font-weight: bold;font-size: 15px;">Sum Insured</td>
					<td style="font-weight: bold;font-size: 15px;">Rate</td>
					<td style="font-weight: bold;font-size: 15px;">Invoice No.</td>
				</tr>
				<%for(int i=0; i<commInfo.length; i++){ %>
				<tr>
					<td style="font-weight: bold;font-size: 15px;" width="2%" align="center"><%=i+1%></td>
					<td style="font-weight: bold;font-size: 15px;" width="12%"><%=commInfo[i][3]%></td>
					<td style="font-weight: bold;font-size: 15px;" width="6%" align="right"><%=commInfo[i][4] == null ? "": fmt.format(Double.parseDouble(commInfo[i][4]))%>(<%=currencyType %>)</td>
					<td style="font-weight: bold;font-size: 15px;" width="5%" align="right"><%=commInfo[i][5] == null ? "": fmt.format(Double.parseDouble(commInfo[i][5]))%></td>
					<td style="font-weight: bold;font-size: 15px;" width="5%"><%=commInfo[i][10] == null ? "": commInfo[i][10]%>&nbsp;</td>					
				</tr>
				<%} %>
			</table>
		</td>
			 <%}else{%>			
			<td width="40%" style="font-weight: bold;font-size: 15px;"> 		
				<%=commInfo[0][3] == null ? "": commInfo[0][3]%>
			 </td>	
			<%} %>		
	</tr>			
	<tr valign="top">		 	
		<td width="13%" style="font-weight: bold;font-size: 15px;">Voyage</td><td width="1%"><b>:</b></td>
		<td width="40%" style="font-weight: bold;font-size: 15px;"> 	
		  From &nbsp; <%=quoteInfo[0][4] == null ? "": quoteInfo[0][4]%> To &nbsp;<%=quoteInfo[0][5] == null ? "": quoteInfo[0][5]%>
		</td>
	</tr>
	<tr valign="top">	
		 	
		<td width="13%" style="font-weight: bold;font-size: 15px;">
			Mode of Transport
		</td>
		<td width="1%"><b>:</b></td>
		<td width="40%" style="font-weight: bold;font-size: 15px;"> 	
		   <%=quoteInfo[0][0] == null ? "": quoteInfo[0][0]%> 
		</td>
	</tr>
	<tr valign="top">	
		 	
		<td width="13%" style="font-weight: bold;font-size: 15px;">
			Estimated Sum Insured
		</td>
		<td width="1%"><b>:</b></td>
		<td width="40%" style="font-weight: bold;font-size: 15px;"> 	
		  <%=currencyType %> <%=quoteInfo[0][8] == null ? "": fmt.format(Double.parseDouble(quoteInfo[0][8]))%> (Being  
		  <%=quoteInfo[0][6] == null ? "": quoteInfo[0][6]%> <%=quoteInfo[0][9] == null ? "": fmt.format(Double.parseDouble(quoteInfo[0][9]))%>
		 <%if(quoteInfo[0][31] != null && quoteInfo[0][31]!="" && !("0%".equalsIgnoreCase(quoteInfo[0][31])) && !("NONE".equalsIgnoreCase(quoteInfo[0][31]))){ %>
			+<%=quoteInfo[0][31]%>
		<%} %>
		 <%if(quoteInfo[0][32] != null && quoteInfo[0][32]!="" && !("0%".equalsIgnoreCase(quoteInfo[0][32])) && !("NONE".equalsIgnoreCase(quoteInfo[0][32]))){ %>
			+<%=quoteInfo[0][32]%>
		<%} %>;
		<%=quoteInfo[0][7] == null ? "": quoteInfo[0][7]%>        ;Exchange Rate   <%=quoteInfo[0][18] == null ? "": quoteInfo[0][18]%>)
		</td>
	</tr>
	<tr valign="top">	
		 	
		<td width="13%" style="font-weight: bold;font-size: 15px;">
			Conditions
		</td>
		<td width="1%"><b>:</b></td>
		<td width="40%" style="font-weight: bold;font-size: 15px;"> 
			Subject to terms and conditions of standard Marine Insured Policy to issued, based on:-
		</td>
	</tr>
	<tr valign="top">		 	
		<td width="13%" style="font-weight: bold;font-size: 15px;">			
		  		<%
					String clauseEdit = "";
					String openCoverNo = "";
					String productId = "";						
					String[][] clausesmaster = new String[0][0];
					com.maan.services.ClausesWarrenty clausesValue = new com.maan.services.ClausesWarrenty();
					
					//String brokerBra = (String) session.getAttribute("LoginBranchCode");
					String brokerBra = (String) session.getAttribute("BelongingBranch");

					try {
						openCoverNo = request.getParameter("openCoverNo") == null ? (String) session
						.getAttribute("openCoverNo") == null ? "0"
						: (String) session.getAttribute("openCoverNo")
						: request.getParameter("openCoverNo");
						productId = request.getParameter("selectProd") == null ? (String) session
						.getAttribute("product_id") == null ? "0"
						: (String) session.getAttribute("product_id")
						: request.getParameter("selectProd");
						clausesmaster = clausesValue.getClause(belongingBranch);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					String[][] getDetails = new String[0][0];
					getDetails=pol.getPolicy(applicationNo);
					
			%>					
			<%
						String QuoteNoInfo = (String) session.getAttribute("quote_no");
						String remarksAdmin = "";
						String remarksPosition = "";
						String[][] backDaysCheck = new String[0][0];
						String brokerUserText="";
						String backDaysPolicy = "";
						String backDaysBL = "";
						String freeModeId = "";
						String freeCoverId = "";
						String freeSeaOption = "";
						String[][] countBackDaysCheck = new String[0][0];
						try {
							remarksAdmin = pol.getAdminRemarksPosition(QuoteNoInfo);
							remarksPosition = pol.getRemarksPosition(QuoteNoInfo);
							backDaysCheck = pol.getBackDatedStatus(QuoteNoInfo);
							if (backDaysCheck.length > 0) {
								backDaysPolicy = backDaysCheck[0][0] == null ? "0"
								: backDaysCheck[0][0];
								backDaysBL = backDaysCheck[0][1] == null ? "0"
								: backDaysCheck[0][1];
								freeModeId = backDaysCheck[0][2] == null ? "0"
								: backDaysCheck[0][2];
								freeCoverId = backDaysCheck[0][3] == null ? "0"
								: backDaysCheck[0][3];
								freeSeaOption = backDaysCheck[0][4] == null ? "0"
								: backDaysCheck[0][4];
							} else {
								backDaysPolicy = "0";
								backDaysBL = "0";
							}
						} catch (Exception ex) {
							backDaysPolicy = "0";
							backDaysBL = "0";
							ex.printStackTrace();
						}
						int countBackDays = 0;
						try {
							countBackDaysCheck = pol.getOpenCoverMasterDatas(openCoverNo);
							if (countBackDaysCheck.length > 0) {
								countBackDays = Integer
								.parseInt(countBackDaysCheck[0][9] == null ? "0"
								: countBackDaysCheck[0][9]);
							} else {
								countBackDays = 0;
							}
						} catch (Exception ex) {
							countBackDays = 0;
						}
						String StatusInfo = "y";
						if (QuoteNoInfo != null)
							StatusInfo = pol.getStatus(QuoteNoInfo);
					%>																
					<%
					int clausesCount = 0;
					int exClausesCount = 0;
					int exCount = 0;
					int warCount = 0;
					String claId = "";
					String warId = "";
					String exId = "";
					String exClaId = "";
					
					String exisCoverId = "0";	
					String exisExCoverId = "0";
					String exisTransportId = "0";
					
					try {
						Map displayResults = new HashMap();
						String exStatus = "";
						String warStatus = "";
						String claStatus = "";
						String exClaStatus = "";
					
						String[][] extraClauses = new String[0][0];
					
						String[][] exclusions = new String[0][0];
						String[][] warranties = new String[0][0];
						String[][] clauses = new String[0][0];
					
						String[][] EditExtraClauses = new String[0][0];
					
						String[][] EditExClauses = new String[0][0];
						String[][] EditWarClauses = new String[0][0];
						String[][] EditClauses = new String[0][0];
					
							if(session.getAttribute("quote_no")!=null)
								displayResults = pol.getExclusionWarrantyClauses((String) session.getAttribute("quote_no"),(String) session.getAttribute("user"), "rer",productId, openCoverNo, brokerBra);
							else
							{
								pol.setLoginBra(brokerBra);
								displayResults = pol.getExclusionWarrantyClauses(applicationNo,(String) session.getAttribute("user"));
							} 	
					
					
						if (displayResults.size() > 0) {
						exisCoverId = (String) displayResults.get("coverId") == null ? exisCoverId
								: (String) displayResults.get("coverId");
						exisExCoverId = (String) displayResults
								.get("extraCoverId") == null ? exisExCoverId
								: (String) displayResults.get("extraCoverId");
						exisTransportId = (String) displayResults
								.get("transportId") == null ? exisTransportId
								: (String) displayResults.get("transportId");
					
						exclusions = (String[][]) displayResults
								.get("exclusionsDesc") == null ? exclusions
								: (String[][]) displayResults
								.get("exclusionsDesc");
						warranties = (String[][]) displayResults
								.get("warrantyDesc") == null ? warranties
								: (String[][]) displayResults
								.get("warrantyDesc");
						clauses = (String[][]) displayResults
								.get("clausesDesc") == null ? clauses
								: (String[][]) displayResults
								.get("clausesDesc");
					
						extraClauses = (String[][]) displayResults
								.get("extraClausesDesc") == null ? extraClauses
								: (String[][]) displayResults
								.get("extraClausesDesc");
					
						EditClauses = (String[][]) displayResults
								.get("editedClauses") == null ? EditClauses
								: (String[][]) displayResults
								.get("editedClauses");//[0][83]);
						EditExtraClauses = (String[][]) displayResults
								.get("editedExtraClauses") == null ? EditExtraClauses
								: (String[][]) displayResults
								.get("editedExtraClauses");//[0][83]);
					
						EditWarClauses = (String[][]) displayResults
								.get("editedWarClauses") == null ? EditWarClauses
								: (String[][]) displayResults
								.get("editedWarClauses");//[0][83]);
						EditExClauses = (String[][]) displayResults
								.get("editedExClauses") == null ? EditExClauses
								: (String[][]) displayResults
								.get("editedExClauses");//[0][83]);
						}
					%>
												
												
					<%
							if (EditClauses.length > 0 || clauses.length > 0)
							{
					%>
			
				Clauses
				</td>
				<td width="1%"><b>:</b></td>
				<td width="40%" style="font-weight: bold;font-size: 15px;"> 											
				<%
						}
						try {
							if (EditClauses.length > 0) {
						for (int a222 = 0; a222 < EditClauses.length; a222++) {
							clauseEdit = "readOnly";
							for (int i = 0; i < clausesmaster.length; i++) {
								if (EditClauses[a222][0].equalsIgnoreCase(clausesmaster[i][0]))
									clauseEdit = "";
							}
				%>
				<table>
					<tr valign="top">
						<td>
							<b><input type="image" src="<%=basePath%>/images/bullet.jpg" width="7px" height="7px"></b>&nbsp;
						</td>
							
						<td style="font-weight: bold;font-size: 15px;"><%=EditClauses[a222][1] == null ? "": EditClauses[a222][1]%>		
						</td>
					</tr>						
				</table>											
				
				<%
				clausesCount = clausesCount + 1;
				}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					if (clauses.length > 0) {
				for (int kc = 0; kc < clauses.length; kc++) {
					claStatus = clauses[kc][1] == null ? ""
					: clauses[kc][1];
					claId = clauses[kc][2] == null ? ""
					: clauses[kc][2];

				  if ("R".equalsIgnoreCase(claStatus)) {
					%>					
							<input type="hidden" name="clausesIds" value="<%=claId%>">
				<table>
					<tr valign="top">
						<td>
							<b><input type="image" src="<%=basePath%>/images/bullet.jpg" width="7px" height="7px"></b>&nbsp;
						</td>
							
						<td style="font-weight: bold;font-size: 15px;"><%=clauses[kc][0] == null ? "": clauses[kc][0]%>		
						</td>
					</tr>						
				</table>
					
						
					<%
								clausesCount = clausesCount + 1;
								} else {
					%>
					<table>
					<tr valign="top">
						<td>
							<b><input type="image" src="<%=basePath%>/images/bullet.jpg" width="7px" height="7px"></b>&nbsp;
						</td>
							
						<td style="font-weight: bold;font-size: 15px;"><%=clauses[kc][0]== null ? "": clauses[kc][0]%>		
						</td>
					</tr>						
				</table>			
						
					<%
							}
							}
							}
							} catch (Exception e) {
								e.printStackTrace();
							}
									try {
									if (EditExtraClauses.length > 0) {
								for (int a222 = 0; a222 < EditExtraClauses.length; a222++) {
									clauseEdit = "readOnly";

									for (int i = 0; i < clausesmaster.length; i++) {
										if (EditExtraClauses[a222][0].equalsIgnoreCase(clausesmaster[i][0]))
											clauseEdit = "";
									}
						%>																				
						<input type="hidden" name="exClausesIds" value="<%=EditExtraClauses[a222][0] == null ? "0": EditExtraClauses[a222][0]%>">
					<table>
						<tr valign="top">
							<td>
								<b><input type="image" src="<%=basePath%>/images/bullet.jpg" width="7px" height="7px"></b>&nbsp;
							</td>
								
							<td style="font-weight: bold;font-size: 15px;"><%=EditExtraClauses[a222][1]== null ? "": EditExtraClauses[a222][1]%>		
							</td>
						</tr>						
					</table>			
						<%
							exClausesCount = exClausesCount + 1;
							}
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
							try {
								if (extraClauses.length > 0) {
							for (int kec = 0; kec < extraClauses.length; kec++) {
								clauseEdit = "readOnly";
								for (int i = 0; i < clausesmaster.length; i++) {
									if (extraClauses[kec][0].equalsIgnoreCase(clausesmaster[i][0]))
										clauseEdit = "";
								}
								exClaStatus = extraClauses[kec][1] == null ? ""
								: extraClauses[kec][1];
								exClaId = extraClauses[kec][2] == null ? ""
								: extraClauses[kec][2];

								if ("R".equalsIgnoreCase(exClaStatus)) {
							%>
															
							<input type="hidden" name="exClausesIds" value="<%=exClaId%>">	
						<table>
							<tr valign="top">
								<td>
									<b><input type="image" src="<%=basePath%>/images/bullet.jpg" width="7px" height="7px"></b>&nbsp;
								</td>
									
								<td style="font-weight: bold;font-size: 15px;"><%=extraClauses[kec][0]== null ? "": extraClauses[kec][0]%>		
								</td>
							</tr>						
						</table>
							
							<%=extraClauses[kec][0] == null ? "": extraClauses[kec][0]%><br/>					

							<%
									exClausesCount = exClausesCount + 1;
									} else {
							%>
							<table>
								<tr valign="top">
									<td>
										<b><input type="image" src="<%=basePath%>/images/bullet.jpg" width="7px" height="7px"></b>&nbsp;
									</td>
										
									<td style="font-weight: bold;font-size: 15px;"><%=extraClauses[kec][0]== null ? "": extraClauses[kec][0]%>		
									</td>
								</tr>						
							</table>											
							<%
								}
								}
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
							%>
							</td>
			</tr>				
							<%
								if (EditExClauses.length > 0 || exclusions.length > 0)
								{
							%>
			<tr valign="top">				
				 	
				<td width="13%" style="font-weight: bold;font-size: 15px;">	
					Exclusions
				</td>
				<td width="1%"><b>:</b></td>
				<td width="40%" style="font-weight: bold;font-size: 15px;"> 	
							<%
									}
									try {
										if (EditExClauses.length > 0) {
									for (int a222 = 0; a222 < EditExClauses.length; a222++) {

										clauseEdit = "readOnly";
							%>
															
							<input type="hidden" name="exIds" value="<%=EditExClauses[a222][0] == null ? "0" : EditExClauses[a222][0]%>">
							<table>
								<tr valign="top">
									<td>
										<b><input type="image" src="<%=basePath%>/images/bullet.jpg" width="7px" height="7px"></b>&nbsp;
									</td>
										
									<td style="font-weight: bold;font-size: 15px;"><%=EditExClauses[a222][1] == null ? "": EditExClauses[a222][1]%>
									</td>
								</tr>						
							</table>
							
																						
							<%
									exCount = exCount + 1;
									}
										}
									} catch (Exception e) {
										e.printStackTrace();
									}
									try {
										if (exclusions.length > 0) {
							%>
							<%
										for (int ke = 0; ke < exclusions.length; ke++) {
	
										exStatus = exclusions[ke][1] == null ? ""
										: exclusions[ke][1];
										exId = exclusions[ke][2] == null ? ""
										: exclusions[ke][2];
	
										if ("R".equalsIgnoreCase(exStatus)) {
							%>
															
							<input type="hidden" name="exIds" value="<%=exId%>">
							<table>
								<tr valign="top">
									<td>
										<b><input type="image" src="<%=basePath%>/images/bullet.jpg" width="7px" height="7px"></b>&nbsp;
									</td>
										
									<td style="font-weight: bold;font-size: 15px;"><%=exclusions[ke][0] == null ? "":exclusions[ke][0]%>
									</td>
								</tr>						
							</table>					
																
							<%
										exCount = exCount + 1;
										} else {
							%>
							<table>
								<tr valign="top">
									<td>
										<b><input type="image" src="<%=basePath%>/images/bullet.jpg" width="7px" height="7px"></b>&nbsp;
									</td>										
									<td style="font-weight: bold;font-size: 15px;"><%=exclusions[ke][0] == null ? "":exclusions[ke][0]%>
									</td>
								</tr>						
							</table>					
															
							<%
										  }
										 }
										}
									  } 
									  catch (Exception e) {
											e.printStackTrace();
									 }
							%>
					</td>
				</tr>
							<%
									try 
									{
										if (EditWarClauses.length > 0 || warranties.length > 0) {
							%>

				<tr valign="top">						 	
					<td width="13%" style="font-weight: bold;font-size: 15px;">	
						Warranties
					</td>
					<td width="1%"><b>:</b></td>
					<td width="40%" style="font-weight: bold;font-size: 15px;"> 
																	
								<%
											}
											if (EditWarClauses.length > 0)
											{
										for (int a222 = 0; a222 < EditWarClauses.length; a222++) {
								%>
															
								<input type="hidden" name="warIds"	value="<%=EditWarClauses[a222][0] == null ? "0"	: EditWarClauses[a222][0]%>">
							<table>
								<tr valign="top">
									<td>
										<b><input type="image" src="<%=basePath%>/images/bullet.jpg" width="7px" height="7px"></b>&nbsp;
									</td>
										
									<td style="font-weight: bold;font-size: 15px;"><%=EditWarClauses[a222][1] == null ? "": EditWarClauses[a222][1]%>
									</td>
								</tr>						
							</table>																								
								<%
										warCount = warCount + 1;
										}
											}
										} catch (Exception e) {
											e.printStackTrace();
										}
										try {
											if (warranties.length > 0) {
										for (int kw = 0; kw < warranties.length; kw++) {
											warStatus = warranties[kw][1] == null ? ""
											: warranties[kw][1];
											warId = warranties[kw][2] == null ? ""
											: warranties[kw][2];
	
											if ("R".equalsIgnoreCase(warStatus)) {
								%>
															
							<input type="hidden" name="warIds" value="<%=warId%>">
							<table>
								<tr valign="top">
									<td>
										<b><input type="image" src="<%=basePath%>/images/bullet.jpg" width="7px" height="7px"></b>&nbsp;
									</td>
										
									<td style="font-weight: bold;font-size: 15px;"><%=warranties[kw][0] == null ? "": warranties[kw][0]%>
									</td>
								</tr>						
							</table>													
							<%
									warCount = warCount + 1;
									} else {
							%>
							<table>
								<tr valign="top">
									<td>
										<b><input type="image" src="<%=basePath%>/images/bullet.jpg" width="7px" height="7px"></b>&nbsp;
									</td>
										
									<td style="font-weight: bold;font-size: 15px;"><%=warranties[kw][0] == null ? "": warranties[kw][0]%>
									</td>
								</tr>						
							</table>
																					
							<%
									}
									}
										}
									} catch (Exception e) {
										e.printStackTrace();
									}
							%>
							<%
									backDaysPolicy = backDaysPolicy == null ? "0" : backDaysPolicy;
									backDaysBL = backDaysBL == null ? "0" : backDaysBL;

									if ("3".equalsIgnoreCase(productId)) {
										countBackDays = 0;
									} else if ("11".equalsIgnoreCase(productId)) {
										countBackDays = countBackDays;
									}
									String pdfModify = pol.getPolicysBackDesc((String) session.getAttribute("quote_no"));
									pdfModify = pdfModify == null ? "" : pdfModify;
									String adminpdfModify = "";
									if (pdfModify.length() > 0
									&& !pdfModify.equalsIgnoreCase("NOTHING"))
										adminpdfModify = pdfModify;
									else if (!pdfModify.equalsIgnoreCase("Admin"))
										pdfModify = pol.getPolicysFreshBackDesc(brokerBra);
									if (pdfModify.equalsIgnoreCase("Admin")) {
							%>
							<input type="hidden" name="policysStatus" value="1000">
							<%
									}
									if ("3".equalsIgnoreCase(productId)
									&& adminpdfModify.length() <= 0
									&& !pdfModify.equalsIgnoreCase("NOTHING")
									&& !pdfModify.equalsIgnoreCase("Admin")) {
							%>													
							<%=pdfModify%>
							<br/>																
							<%
									} else if ("3".equalsIgnoreCase(productId)
									&& adminpdfModify.length() > 0
									&& !pdfModify.equalsIgnoreCase("NOTHING")
									&& !pdfModify.equalsIgnoreCase("Admin")) {
							%>															
							<input type="hidden" name="policysStatus" value="1000">
							<textarea cols=90 rows=2 name="policyBackDesc"
								readOnly title=" Policy Back Date no Known Clause">							
								<%=adminpdfModify%>
							</textarea>
															
							<%
									}
									if (adminpdfModify.length() > 0)
										pdfModify = adminpdfModify;
									if (!pol.getPolicyBackStatus((String) session.getAttribute("quote_no")).equalsIgnoreCase("0")
									&& "11".equalsIgnoreCase(productId)) {
							%>
															
							<input type="hidden" name="policysStatus" value="1000">
							<textarea cols=90 rows=2 name="policyBackDesc"
								readOnly title=" Policy Back Date no Known Clause">
								<%=pdfModify%>
							</textarea>																
							<%
									}
									try {
										if ("11".equalsIgnoreCase(productId)) {
									String[][] openFreeText = new String[0][0];
									String[][] openSeaFreeTextOptions = new String[0][0];
									openFreeText = pol.getOpenCoverRelatedDatas(openCoverNo, freeModeId, freeCoverId,"OPENFREETEXT", brokerBra);
									if ("1".equalsIgnoreCase(freeModeId)) {
										if ("FCL".equalsIgnoreCase(freeSeaOption)) {
											freeSeaOption = "101";
										} else if ("LCL".equalsIgnoreCase(freeSeaOption)) {
											freeSeaOption = "102";
										} else if ("BREAK BULK"
										.equalsIgnoreCase(freeSeaOption)) {
											freeSeaOption = "103";
										} else if ("BULK".equalsIgnoreCase(freeSeaOption)) {
											freeSeaOption = "104";
										} else {
											freeSeaOption = "0";
										}
									}
									if ("0".equalsIgnoreCase(freeSeaOption)
											|| "".equalsIgnoreCase(freeSeaOption)) {
										openSeaFreeTextOptions = new String[0][0];
									} else {
										openSeaFreeTextOptions = pol
										.getOpenCoverRelatedDatas(openCoverNo,
												freeModeId, freeSeaOption,
												"OPENFREETEXT", brokerBra);
									}
				%>
				</td>
				</tr>
				<%
									if (openFreeText.length > 0
											|| openSeaFreeTextOptions.length > 0) {
							%>
				
				<tr valign="top">						 	
					<td width="13%" style="font-weight: bold;font-size: 15px;">	
						Special Conditions
					</td>
					<td width="1%"><b>:</b></td>
					<td width="40%" style="font-weight: bold;font-size: 15px;"> 
					
							<%
									}
									if (openFreeText.length > 0) {
										for (int ft = 0; ft < openFreeText.length; ft++) {
							%>
							<table>
								<tr>
									<td>
										<b><input type="image" src="<%=basePath%>/images/bullet.jpg" width="7px" height="7px"></b>&nbsp;
									</td>
										
									<td style="font-weight: bold;font-size: 15px;"><%=openFreeText[ft][0] == null ? ""	: openFreeText[ft][0]%>		
									</td>
								</tr>						
							</table>									
															
							<%
									}
									}
									if (openSeaFreeTextOptions.length > 0) {
										for (int fts = 0; fts < openSeaFreeTextOptions.length; fts++) {
							%>
							<table>
								<tr>
									<td>
										<b><input type="image" src="<%=basePath%>/images/bullet.jpg" width="7px" height="7px"></b>&nbsp;
									</td>
										
									<td style="font-weight: bold;font-size: 15px;"><%=openSeaFreeTextOptions[fts][0] == null ? "": openSeaFreeTextOptions[fts][0]%>		
									</td>
								</tr>						
							</table>												
							<%
								}
								}
									}//End of Product Id 11
								} catch (Exception e) {
									e.printStackTrace();
								}
				%>
				</td>
				</tr>
				<%
								if ("NIL".equalsIgnoreCase(remarksAdmin)
								|| "".equalsIgnoreCase(remarksAdmin)) {

								} else {
							%>
				<tr valign="top">						 	
					<td width="13%" style="font-weight: bold;font-size: 15px;">	
						Admin Clauses
					</td>
					<td width="1%"><b>:</b></td>
					<td width="40%" style="font-weight: bold;font-size: 15px;"> 
								<%
									}
									} catch (Exception ex) {
									ex.printStackTrace();
									}
								%>				
				</td>
				</tr>			
					
								<% 
								brokerUserText=pol.getBrokerUserFreeText(QuoteNoInfo);
								if(brokerUserText.trim().length()>0)
								{
								
								%>
															
				<tr valign="top">						 	
					<td width="13%" style="font-weight: bold;font-size: 15px;">	
						Broker/User Free Text
					</td>
					<td width="1%"><b>:</b></td>
					<td width="40%" style="font-weight: bold;font-size: 15px;"> 
					<table>
								<tr>
									<td>
										<b><input type="image" src="<%=basePath%>/images/bullet.jpg" width="7px" height="7px"></b>&nbsp;
									</td>
										
									<td style="font-weight: bold;font-size: 15px;"><%=brokerUserText%>				
									</td>
								</tr>						
							</table>					
					
								<% 
								
								}
								%>	
			 </td>
			</tr>
			
					<%
						if (commInfo.length ==1){
					%>			
	
		   <tr valign="top">					 	
				<td width="13%" style="font-weight: bold;font-size: 15px;">	
					Rates
				</td>
				<td width="1%"><b>:</b></td>
				<td width="40%" style="font-weight: bold;font-size: 15px;"> 
					<table>
						<%if("admin".equalsIgnoreCase(userType) || "RSAIssuer".equalsIgnoreCase(userType)) { %>
							<tr valign="top">
								<td style="font-weight: bold;font-size: 15px;">
									Marine
								</td>	
								<td style="font-weight: bold;font-size: 15px;">
									:
								</td>								
								<td style="font-weight: bold;font-size: 15px;">
								 At <%=commInfo[0][5] == null ? "": rate.format(Double.parseDouble(commInfo[0][5]))%>	%			
								</td>
							</tr>	
							<tr valign="top">
								<td style="font-weight: bold;font-size: 15px;" width="20%">
									War& Srcc 
								</td>	
								<td style="font-weight: bold;font-size: 15px;" width="2%">
									:
								</td>								
								<td style="font-weight: bold;font-size: 15px;"> Rate ruling at the time of shipment shall be applied currently being at 
								<%=commInfo[0][6] == null ? "": rate.format(Double.parseDouble(commInfo[0][6]))%>%			
								</td>
							</tr>
						<%} else {%>
							<tr valign="top">
								<td style="font-weight: bold;font-size: 15px;"> 
								<%=(commInfo[0][5]==null || commInfo[0][6]== null) ? "": rate.format(Double.parseDouble(commInfo[0][5])+Double.parseDouble(commInfo[0][6]))%>%			
								</td>
								<td style="font-weight: bold;font-size: 15px;" width="20%"></td>
								<td style="font-weight: bold;font-size: 15px;" width="2%"></td>
							</tr>
						<%}%>
					</table>					
				</td>		
			</tr>			
					<%} %>	
			<tr valign="top">						 	
					<td width="13%" style="font-weight: bold;font-size: 15px;">	
						Premium
					</td>
					<td width="1%"><b>:</b></td>
					<td width="40%" style="font-weight: bold;font-size: 15px;">
					<%if("admin".equalsIgnoreCase(userType) || "RSAIssuer".equalsIgnoreCase(userType)) { %> 	
						<table>
							<tr valign="top">
								<td style="font-weight: bold;font-size: 15px;" width="30%">
									Marine
								</td>	
								<td style="font-weight: bold;font-size: 15px;" width="2%">
									:
								</td>								
								<td style="font-weight: bold;font-size: 15px;">
								 <%=currencyType %> <%=quoteInfo[0][10] == null ? "": fmt.format(Double.parseDouble(quoteInfo[0][10]))%>			
								</td>
							</tr>	
							<tr valign="top">
								<td style="font-weight: bold;font-size: 15px;" width="30%">
									War
								</td>	
								<td style="font-weight: bold;font-size: 15px;" width="2%">
									:
								</td>								
								<td style="font-weight: bold;font-size: 15px;"> 
								<%=currencyType %> <%=quoteInfo[0][11] == null ? "": fmt.format(Double.parseDouble(quoteInfo[0][11]))%>				
								</td>
							</tr>
							<tr valign="top">
								<td style="font-weight: bold;font-size: 15px;" width="30%">
									<b>Total</b>
								</td>	
								<td style="font-weight: bold;font-size: 15px;" width="2%">
									:
								</td>								
								<td style="font-weight: bold;font-size: 15px;"> 
								 <b><%=currencyType %> <%=fmt.format(Double.parseDouble(quoteInfo[0][26]))%></b>				
								</td>
							</tr>
						</table>
					<%} else {%>
						<table>
							<tr valign="top">
								<td style="font-weight: bold;font-size: 15px;"> 
								 <b><%=currencyType %> <%=fmt.format(Double.parseDouble(quoteInfo[0][26]))%></b>				
								</td>
								<td></td>	
								<td></td>
							</tr>
						</table>
					<%}%>
					</td>						
			</tr>
			<tr valign="top">					 	
				<td width="13%" style="font-weight: bold;font-size: 15px;">	
					Issuance Fees
				</td>
				<td width="1%"><b>:</b></td>
				<td width="40%" style="font-weight: bold;font-size: 15px;"> 	
				<%=currencyType %> <%=quoteInfo[0][13] == null ? "": fmt.format(Double.parseDouble(quoteInfo[0][13]))%>		
				</td>		
			</tr>
			<tr valign="top">					 	
				<td width="13%" style="font-weight: bold;font-size: 15px;">	
					Validity
				</td>
				<td width="1%"><b>:</b></td>
				<td width="40%" style="font-weight: bold;font-size: 15px;"> 	
					Our Quotation is valid for 30 days from <%=quoteInfo[0][16] == null ? "": quoteInfo[0][16]%>
				</td>		
			</tr>
			<%
				if (quoteInfo[0][33]!=null && !"".equals(quoteInfo[0][33])){
			%>
			<tr valign="top">					 	
				<td width="13%" style="font-weight: bold;font-size: 15px;">	
					Rejected Rematks
				</td>
				<td width="1%"><b>:</b></td>
				<td width="40%" style="font-weight: bold;font-size: 15px;"> 	
					<%=quoteInfo[0][33]%>
				</td>
			</tr>
			<%} %>	
	  </table>
	 </td>
	</tr>
</table>
<script language="JavaScript">
	javascript:window.history.forward(1);

</script>

</body>
</html>


	<%}catch(Exception e){e.printStackTrace();}%>