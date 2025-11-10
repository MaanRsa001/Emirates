<%@ page isELIgnored="false" %>
<%String cpath1 = request.getContextPath(); %>
<%@page import="org.springframework.util.CollectionUtils"%>
<%try{ %>
<%@page import="com.maan.premium.DAO.PremiumInputsBean"%>
<!DOCTYPE HTML>
<html>
<head>
	<title>Emirates</title>
	<jsp:useBean id="rate" class="com.maan.opencover.bean.rateModification">
	<jsp:setProperty name="rate" property="*"/>
	</jsp:useBean>
	<jsp:useBean id="beans" class="com.maan.opencover.bean.openCoverQuotation">
	<jsp:setProperty name="beans" property="*"/>
	</jsp:useBean>
	<jsp:useBean id="ct" class = "com.maan.opencover.ConditionsOpenCover">
	<jsp:setProperty property="*" name="ct"/>
	</jsp:useBean>
	<jsp:useBean id="report" class="com.maan.report.service.ReportService">
	<jsp:setProperty name="report" property="*"/>
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
			width: 95%;
			height: 20px;
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
<%!
StringTokenizer tokens1=null;String selection=null;
java.util.ArrayList rateModify=null;
String action="";String modeNames="0";
String getDetail[][]=null;
String getExisting[][]=new String[0][0];
String countryId=null;
StringTokenizer tokens=null;
StringTokenizer tokensTransMode=null;
StringTokenizer tokensTransMode2=null;
String Nationality[][]=null;
int countSea=0;
int g=0;
String modeName="";
String coverName="",coverIDs="";
String wsrc = "";
String channeldetails[][] = new String[0][0];
String channelIdentifier = "";
String defaultClauses = "";
String disPercent="0",disRate="0";
%>

<%
List endtTypeList=report.getEndTypeList((String)session.getAttribute("product_id"));
int totalDeductibles = Integer.parseInt(request.getParameter("totalDeductibles")==null?(request.getAttribute("totalDeductibles")==null?"4":request.getAttribute("totalDeductibles").toString()):request.getParameter("totalDeductibles"));
String start[] = new String[totalDeductibles];
String end[] = new String[totalDeductibles];
String amount[] = new String[totalDeductibles];
String currencyValue[] = new String[totalDeductibles];
String percent[] = new String[totalDeductibles];
for(int j=0;j<totalDeductibles;j++){
 start[j] = request.getParameter("startRange"+(j+1)) == null ? "": request.getParameter("startRange"+(j+1));
 end[j] = request.getParameter("endRange"+(j+1)) == null ? "": request.getParameter("endRange"+(j+1));
 amount[j] = request.getParameter("excessAmount"+(j+1)) == null ? "": request.getParameter("excessAmount"+(j+1));
 currencyValue[j] = request.getParameter("currencyValue"+(j+1)) == null ? "": request.getParameter("currencyValue"+(j+1));
 percent[j]=request.getParameter("excessPercent"+(j+1)) == null ? "": request.getParameter("excessPercent"+(j+1));
}

String currency = request.getParameter("currency") == null ? "0": request.getParameter("currency");
String chkProposalNo = (String)session.getAttribute("proposalNo");
String adminBranch = braCode;
if(adminBranch.indexOf("'")!=-1) {
	adminBranch = adminBranch.replaceAll("'","");
}
String belongingBranch = session.getAttribute("BelongingBranch")==null?"":(String)session.getAttribute("BelongingBranch"); 
String cid = (String) session.getAttribute("AdminCountryId");
channeldetails = (String[][]) new PremiumInputsBean().getCurrencyDetails(cid);

String decimalPlace = (String) session.getAttribute("decimalPlace");
String currencyType = (String) session.getAttribute("currencyType");
java.text.DecimalFormat fmt =null;
if(decimalPlace.equalsIgnoreCase("2"))
	fmt=new java.text.DecimalFormat("##,##0.00");
else if(decimalPlace.equalsIgnoreCase("3"))
	fmt=new java.text.DecimalFormat("##,##0.000");
else if(decimalPlace.equalsIgnoreCase("4"))
	fmt=new java.text.DecimalFormat("##,##0.0000");
else
	fmt=new java.text.DecimalFormat("##,##0.00");

rate.setProposalNo((String)session.getAttribute("proposalNo"));
rate.setProductId((String)session.getAttribute("product_id"));

rateModify=rate.getSharedLevel("edit",adminBranch,loginIds,belongingBranch);
action="showOpencoverSummary.jsp";
getDetail=(String[][])rateModify.get(3);
String namess[]=new String[2];
namess[0]="john";
namess[1]="jayaraj";
String[] Mon={"JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC"};
java.util.Date days=new java.util.Date();
//days+1;
String day="";
String month="";
String year="";
defaultClauses = beans.getDefaultClauses((String)session.getAttribute("proposalNo"));

			getExisting=(String[][])rateModify.get(5);		
			if(request.getParameter("effectiveDay")!=null && request.getParameter("effectiveMonth")!=null && request.getParameter("effectiveYear")!=null)
			{
				day=request.getParameter("effectiveDay");
				month=request.getParameter("effectiveMonth");
				year=request.getParameter("effectiveYear");
			}
			else
			{
				try
				{
						if(getExisting!=null && getExisting.length>0){ 
							day=""+Integer.parseInt(getExisting[0][3]);
							month=""+Integer.parseInt(getExisting[0][4]);
							year=""+Integer.parseInt(getExisting[0][5]);
						}else{
							String eDate[][] = beans.getOpenCoverDate((String)session.getAttribute("proposalNo"));
							if(eDate!=null && eDate.length>0){
								day=""+Integer.parseInt(eDate[0][0]);
								month=""+Integer.parseInt(eDate[0][1]);
								year=""+Integer.parseInt(eDate[0][2]);
							}
						}
				 	 	String effdate = day+"/"+month+"/"+year;
						String[][] values = rate.getDeductibles((String)session.getAttribute("proposalNo"),effdate);
						if(values!=null && values.length>0){
							start = new String [values.length];
							end = new String [values.length];
							amount = new String [values.length];
							currencyValue = new String [values.length];
							for(int j=0;j<values.length;j++){
							 start[j] = values[j][0] == null ? "": values[j][0];
							 end[j] = values[j][1] == null ? "": values[j][1];
							 amount[j] = values[j][2] == null ? "": values[j][2];
							 currencyValue[j] = values[j][3] == null ? "": values[j][3];
							 percent[j]=values[j][4]==null?"":values[j][4];
							}
							totalDeductibles = values.length;
						}
						System.out.println("totalDeductibles:::"+totalDeductibles);
										
				}catch(Exception e)
				{
				    e.printStackTrace();
					/*day=""+(days.getDate());
					month=""+(days.getMonth()+1);
					year=""+(days.getYear()+1900);*/
				}
			}
%>

<form name="form1" method="post" action="rateModification.jspa">
<table width="100%" align="center" border="0" > 
<tr>
<td align="left" width="35%">
<input type="hidden" name="actionPath" value="<%=action%>">
</td>
<td align="left">
<font color="red" size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=request.getAttribute("errorDetail")!=null?(String)request.getAttribute("errorDetail"):"&nbsp;"%></font>
</td>
</tr>
</table>
<table  align="center" border="0" cellpadding="2" cellspacing="2" width="100%">
	<tr>
		<td>
			<table  align="center" border="0" cellpadding="2" cellspacing="2" width="100%">
				<tr>
					<td width="33%"><span class="heading"><b>Proposal Number:&nbsp;<%=(String)session.getAttribute("proposalNo")%><b></span></td>
					<td width="33%">
						<%if(session.getAttribute("missippiCode")!=null &&!"".equals((String)session.getAttribute("missippiCode"))) {%>
						<span class="heading"> Core Application Policy No:&nbsp;<%=(String)session.getAttribute("missippiCode")%> </span>
						<%} %>
					</td>
					<td width="33%">
						<span class="heading" style="float: left; line-height: 30px;">EffectiveDate</span>
						<select name="effectiveDay" class="inputSelect" style="width:50px; float: left;">
							<option value="Select">DD</option>
							<%	try {
									for(int i=1;i<=31;i++) {
							%>
								<option  value="<%=i%>" <%=(""+i).equalsIgnoreCase(day)?"selected":""%>><%=i%></option>	
							<% } %>		
						</select>
						<select name="effectiveMonth" class="inputSelect" style="width:65px; float: left;">
							<option value="Select" >MM</option>
							<%	for(int i=1;i<=Mon.length;i++) { %>
								<option  value="<%=i%>" <%=(""+i).equalsIgnoreCase(month)?"selected":""%>><%=Mon[i-1]%> </option>		
							<%	}%>		
						</select>
						<select name="effectiveYear" class="inputSelect" style="width:65px; float: left;">
							<option value="Select" >YYYY</option>
							<%	java.util.Calendar cal = java.util.Calendar.getInstance();
								for(int i=cal.get(java.util.Calendar.YEAR)-5;i<=cal.get(java.util.Calendar.YEAR)+1;i++) { %>
									<option  value="<%=i%>" <%=(""+i).equalsIgnoreCase(year)?"selected":""%>><%=i%></option>	
									<% }
							}catch(Exception e) {
								out.println("<br>  ERRORRR"+e.toString());
							}
							%>
						</select>
					</td>
				</tr>			
			</table>
		</td>
	</tr>
</table>
<input type="hidden" name="vv" value="<%=namess%>">

	<%
		String[][] coverTypeMaster=(String[][]) new PremiumInputsBean().getSeaCoverValues(belongingBranch);
		Map coverType=new HashMap();
		List typeList=new ArrayList();
		Map modeList=new HashMap();
		String ids[]=new String[0];
		int typeCount=0;
		String freeText = "";
		freeText = beans.getFreeTextStatus((String)session.getAttribute("proposalNo"));
		String modes=null;
		modeName="";
		coverName="";
		countSea=0;
			try {
				for(int i=0;i<getDetail.length;i++) {
					if(i==0)
						modes=getDetail[i][0];
					ids=(getDetail[i][4]==null?"":getDetail[i][4]).split(",");											
					if(!modes.equalsIgnoreCase(getDetail[i][0])) {	
						modeName=modeName+","+(getDetail[i-1][2]+"-"+(countSea));
						modes=getDetail[i][0];
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
					coverName=coverName+","+(getDetail[i][1]+"-"+getDetail[i][3]);
					coverType.put(getDetail[i][1],(String)getDetail[i][4]);
					modeList.put(getDetail[i][1],(String)getDetail[i][0]);
				}
			} catch(Exception e) {
				//out.println("<br>   Eeee-->"+e.toString());
			}
			wsrc = modeName;
			if(modeName!=null && modeName.length()>1)
				modeName=modeName.substring(1,modeName.length());
			if(coverName!=null && coverName.length()>1)
				coverName=coverName.substring(1,coverName.length());
			tokens=new StringTokenizer(modeName,",");
			tokensTransMode=new StringTokenizer(modeName,",");
			tokensTransMode2=new StringTokenizer(modeName,",");
			String covers=null;
			g=0;
		
	%>
			<div style="width:100%;overflow:scroll;overflow-y:hidden;">
			<table width="100%" class="footable">
				<thead>
				<tr>
					<th width="1%">SNo</th>
					<th width="24%">Category Of Goods</th>
					<%			
						while(tokens.hasMoreTokens()) {	
							covers=(String)tokens.nextToken();
							if(g==0 && "LAND".equalsIgnoreCase(covers.substring(0,covers.indexOf('-'))))
								g=0;
							else
								g++;
					%>
					<th width='25%' colspan="<%=covers.substring(covers.indexOf('-')+1,covers.length())%>"><strong><%=covers.substring(0,covers.indexOf('-'))%></strong></th>
					<% } %>
				</tr>
				<tr>
					<th>&nbsp;</th>
					<th>&nbsp;</th>
					<%
						getDetail=(String[][])rateModify.get(7);
						tokens=new StringTokenizer(modeName,",");
						covers=null;
						while(tokens.hasMoreTokens())
						{
						covers=(String)tokens.nextToken();
					%>
						<th width='25%' align="center" colspan="<%=covers.substring(covers.indexOf('-')+1,covers.length())%>"><strong>Base Rate (%)</strong></th>
					<%	
						}
					%>
				</tr>
				<tr>
					<th>&nbsp;</th>
					<th>&nbsp;</th>
					<%
						tokens=new StringTokenizer(coverName,",");
						String coverId=null;
						coverIDs = "";
						while(tokens.hasMoreTokens()) {
							covers=(String)tokens.nextToken();
							coverId=covers.substring(0,covers.indexOf('-'));
							System.out.println("coverIDs " + coverIDs);
							if(coverIDs.equalsIgnoreCase(""))
								coverIDs = coverId;
							else
								coverIDs = coverIDs + "," + coverId;
						
							String cvrName = covers.substring(covers.indexOf('-')+1,covers.length());
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
					<th colspan="<%=ids.length %>">
						<%if("GUEST".equalsIgnoreCase(loginIds)){%>
							<%=covers.substring(covers.indexOf('-')+1,covers.length())%>&nbsp;
						<%}else if("Y".equalsIgnoreCase(defaultClauses)){%>
							<strong><%=covers.substring(covers.indexOf('-')+1,covers.length())%></strong>
						<%}else{%>
							<a Style="color: yellow" href="#" onClick="return ccc('<%=coverId%>','<%=cvrName%>')"><strong><%=covers.substring(covers.indexOf('-')+1,covers.length())%>&nbsp;<FONT color=red>**</FONT></strong></a>
						<%}%>
							<%if(coverTypeIds!=null && coverTypeIds.length()>0){ %>
								<input type="hidden" name="coverType<%=coverId %>" value="<%= coverTypeIds%>"/>
							<%} %>
					</th>
					<%
					selection="0";
					for(int t=0;t<getDetail.length;t++) {
						if(coverId.equalsIgnoreCase(getDetail[t][0]))
							selection="1";
					}
					%>
						<input type="hidden" name="clauses_<%=coverId%>" value="<%=request.getParameter("clauses_"+coverId)!=null?request.getParameter("clauses_"+coverId):selection%>">
					<%			
					}
					System.out.println("coverIDs " + coverIDs);
					%>
				</tr>
				<% if(typeList!=null && typeList.size()>0 && typeList.size()!=typeCount) { %>
				<tr>
					<th colspan="2"></th>
					<% for(int l=0; l<typeList.size(); l++){ %>
			 		<th><strong><%=typeList.get(l)%></strong></th>
			 		<%}%>
				</tr>
				</thead>	
			<%}
			   	 modeName="0";
				//getDetail=null;
				getDetail=(String[][])rateModify.get(4);
				//if("edit".equalsIgnoreCase((String)session.getAttribute("mode")))
				Map rateList=new HashMap();
				Map disPercentList=new HashMap();
				Map disRateList=new HashMap();
				for(int i=0;i<getDetail.length;i++)
				{
					modeName="0";%>
				<tbody>
				<tr >
				<td ><strong>&nbsp;&nbsp;&nbsp;&nbsp;<%=(i+1)%></strong></td>
				<td ><strong>
					<%=(getDetail[i][1]==null?getDetail[i][2]:((getDetail[i][1].trim()).length()<=1?getDetail[i][2]:getDetail[i][1]))%> <br/><br/>
					<font color="blue"><strong>Excess Percent&nbsp;%</strong></font> <br/><br/>
					<font color="blue"><strong>Excess Value</strong></font>
				</strong></td>
		<%

			tokens=new StringTokenizer(coverName,",");
						while(tokens.hasMoreTokens())
						{covers=(String)tokens.nextToken();
						modeName="0";
						disPercent="0";
						disRate="0";
						//New
						String coverTypeIds=(String)coverType.get(covers.substring(0,covers.indexOf('-')));
						ids=new String[0];
						if(coverTypeIds!=null && !"null".equalsIgnoreCase(coverTypeIds) && coverTypeIds.length()>0)
						{
							ids= coverTypeIds.split(",");
						}
						rateList=new HashMap();
						disPercentList=new HashMap();
						disRateList=new HashMap();
						//
							
						for(int j=0;j<getExisting.length;j++)
							{
							if(ids!=null && ids.length>0)
							{
								for(int d=0;d<ids.length;d++)
								{
									if(getExisting[j][0].equalsIgnoreCase(getDetail[i][0]) && getExisting[j][1].equalsIgnoreCase(covers.substring(0,covers.indexOf('-'))) && ids[d].equalsIgnoreCase(getExisting[j][6]))
									{
										rateList.put(ids[d],""+Double.parseDouble(getExisting[j][2]==null || "null".equals(getExisting[j][2])?"0":getExisting[j][2]));
										disPercentList.put(ids[d],""+getExisting[j][7]==null || "null".equals(getExisting[j][7])?"0":getExisting[j][7]);
										disRateList.put(ids[d],""+getExisting[j][8]==null || "null".equals(getExisting[j][7])?"0":getExisting[j][8]);
									}
								}
							}else if(getExisting[j][0].equalsIgnoreCase(getDetail[i][0]) && getExisting[j][1].equalsIgnoreCase(covers.substring(0,covers.indexOf('-'))) && "0".equalsIgnoreCase(getExisting[j][6])){
								modeName=getExisting[j][2]==null?"0":getExisting[j][2];
								disPercent=getExisting[j][7]==null?"0":getExisting[j][7];
								disRate=getExisting[j][8]==null?"0":getExisting[j][8];
								}
							}
							modeName=""+Double.parseDouble(modeName);
							if("0.0".equalsIgnoreCase(modeName)){// For Default get From Commodity
							modeName=Double.parseDouble(getDetail[i][3])+"";
							}
						%>
						<% 	
							if(coverTypeIds!=null && !"null".equalsIgnoreCase(coverTypeIds) && coverTypeIds.length()>0)
							{
							for(int c=0; c<coverTypeMaster.length; c++){
							if(coverTypeMaster[c][0].equalsIgnoreCase((String)modeList.get(covers.substring(0,covers.indexOf('-')))))
							{
								for(int k=0; k<ids.length; k++){
								if(coverTypeMaster[c][1].equalsIgnoreCase(ids[k])){
									modeName="0";
									if(rateList.containsKey(ids[k]))
										modeName=(String)rateList.get(ids[k]);
									if(disPercentList.containsKey(ids[k]))
										disPercent=(String)disPercentList.get(ids[k]);
									if(disRateList.containsKey(ids[k]))
										disRate=(String)disRateList.get(ids[k]);
						%>
						<td align="center">
							<input type="text"  class="inputBox" name="rate_<%=(i+1)%>_<%=covers.substring(0,covers.indexOf('-'))%>_<%=ids[k]%>" value="<%=request.getParameter("rate_"+(i+1)+"_"+covers.substring(0,covers.indexOf('-'))+"_"+ids[k])!=null?(request.getParameter("rate_"+(i+1)+"_"+covers.substring(0,covers.indexOf('-'))+"_"+ids[k])):(Double.parseDouble(modeName)<0?"":modeName)%>" onkeyup="checkNumbers(this)" maxlength="8">
							<input type="text"  class="inputBox" size="20" name="disPercent_<%=(i+1)%>_<%=covers.substring(0,covers.indexOf('-'))%>_<%=ids[k]%>" value="<%=request.getParameter("disPercent_"+(i+1)+"_"+covers.substring(0,covers.indexOf('-'))+"_"+ids[k])!=null?(request.getParameter("disPercent_"+(i+1)+"_"+covers.substring(0,covers.indexOf('-'))+"_"+ids[k])):(Double.parseDouble(disPercent)<0?"":disPercent)%>" onkeyup="validamt(this)" maxlength="5"><br/>
							<input type="text"  class="inputBox" size="20" name="disrate_<%=(i+1)%>_<%=covers.substring(0,covers.indexOf('-'))%>_<%=ids[k]%>" value="<%=request.getParameter("disrate_"+(i+1)+"_"+covers.substring(0,covers.indexOf('-'))+"_"+ids[k])!=null?(request.getParameter("disrate_"+(i+1)+"_"+covers.substring(0,covers.indexOf('-'))+"_"+ids[k])):(Double.parseDouble(disRate)<0?"":disRate)%>" onkeyup="validamt(this)" maxlength="15">
						</td>
						<%}}}}}else{ %>						
						<td align="center">
							<input type="text"  class="inputBox" name="rate_<%=(i+1)%>_<%=covers.substring(0,covers.indexOf('-'))%>" value="<%=request.getParameter("rate_"+(i+1)+"_"+covers.substring(0,covers.indexOf('-')))!=null?(request.getParameter("rate_"+(i+1)+"_"+covers.substring(0,covers.indexOf('-')))):(Double.parseDouble(modeName)<0?"":modeName)%>" onkeyup="checkNumbers(this)" maxlength="8" <%="GUEST".equalsIgnoreCase(loginIds)?"DISABLED":""%>>
							<input type="text"  class="inputBox" size="20" name="disPercent_<%=(i+1)%>_<%=covers.substring(0,covers.indexOf('-'))%>" value="<%=request.getParameter("disPercent_"+(i+1)+"_"+covers.substring(0,covers.indexOf('-')))!=null?(request.getParameter("disPercent_"+(i+1)+"_"+covers.substring(0,covers.indexOf('-')))):(Double.parseDouble(disPercent)<0?"":disPercent)%>" onkeyup="validamt(this)" maxlength="5"><br/>
							<input type="text"  class="inputBox" size="20" name="disrate_<%=(i+1)%>_<%=covers.substring(0,covers.indexOf('-'))%>" value="<%=request.getParameter("disrate_"+(i+1)+"_"+covers.substring(0,covers.indexOf('-')))!=null?(request.getParameter("disrate_"+(i+1)+"_"+covers.substring(0,covers.indexOf('-')))):(Double.parseDouble(disRate)<0?"":disRate)%>" onkeyup="validamt(this)" maxlength="15">
						</td>
						<%if("GUEST".equalsIgnoreCase(loginIds)){%>
						<input type="hidden" name="rate_<%=(i+1)%>_<%=covers.substring(0,covers.indexOf('-'))%>" value="<%=request.getParameter("rate_"+(i+1)+"_"+covers.substring(0,covers.indexOf('-')))!=null?(request.getParameter("rate_"+(i+1)+"_"+covers.substring(0,covers.indexOf('-')))):(Double.parseDouble(modeName)<0?"":modeName)%>">
						<%}%>
						<%}}%>
						<input type="hidden" name="commoditys_<%=i%>" value="<%=getDetail[i][0]%>">
			</tr>
			</tbody>
				<%
				}%>
				<input type="hidden" name="coverName" value="<%=coverName%>">
				<input type="hidden" name="totalCommodity" value="<%=getDetail.length%>">
		<%
				if(wsrc!= null && wsrc.trim().indexOf("BY LAND OR SEA OR AIR") != -1)
				{
					wsrc = wsrc.replaceAll("BY LAND OR SEA OR AIR","MULTI MODE");
				} 
				session.setAttribute("coverName", wsrc==null?"":wsrc);	
				/*int air=0;
				int sea=0;
				int multimode = 0;
				try
				{
					if(wsrc!=null)
					{

				 air = wsrc.lastIndexOf("AIR");
				//road = wsrcc.lastIndexOf("ROAD");
 				sea = wsrc.lastIndexOf("SEA");
 				multimode = wsrc.lastIndexOf("MULTI MODE");
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
      }
				}}catch(Exception e)
				{}*/
				
		
				%>
				
	</table>
	</div>
<%
		String wsrc = "";
		wsrc = request.getParameter("wsrc")==null?"":request.getParameter("wsrc");
		if(wsrc.length()<=0)
			wsrc = beans.getWSRCOpt((String)session.getAttribute("proposalNo"));
%>
<%if(!"GUEST".equalsIgnoreCase(loginIds)){%>
<table width="100%" align="center" border="0" cellspacing="0" cellpadding="0">
	<tr align="right" class="">
	<td class='formtxt'>&nbsp;</td>
		<td align="center"><strong>
		<%if(freeText.equalsIgnoreCase("Y") || !"Y".equalsIgnoreCase(defaultClauses)) { %>
			<span  class='heading'> Click here for</span> &nbsp;&nbsp;&nbsp;&nbsp;
		<%} %>
		<%if(!"Y".equalsIgnoreCase(defaultClauses)) { %>
			<a href="#" Style="color: blue" onClick="return warranties(1)">Warranties</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="#" Style="color: blue" onClick="return warranties(2)">Exclusions</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<%} %>
		<%
			if(freeText.equalsIgnoreCase("Y"))
			{
		%>
		<a href="#" Style="color: blue" onClick="return freetext()">Free Text</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<%	
			}if(wsrc.equalsIgnoreCase("Y") && !"Y".equalsIgnoreCase(defaultClauses)){
		%>
		<a href="#" Style="color: blue" title="WSRCC" onClick="return warranties(4)">WSRCC <sup><font color="red"> **</font></sup></a>
		<%} %>
		</strong></td>
		<td class='formtxt'>&nbsp;</td>

	</tr>
	</table>
<%}%>
	<br>
<!-- Royal New Check -->
<%
		if(wsrc.equalsIgnoreCase("Y"))
		{
%>
<table  align="center" border="0" cellpadding="2" cellspacing="2" width="100%">
	<tr>
		<td><span class="heading">War&SRCC</span></td>
	</tr>
	<tr>
	<td>
		<table width="100%" class="footable">
			<thead>
			<tr>
				<th width="50%">Form Of Transport</th>
				<th width="50%">Base Rate (%)</th>
			</tr>
			</thead>
			<tbody>
			<%	getDetail=(String[][])rateModify.get(13);
				com.maan.opencover.bean.newCoverBean newCover=new com.maan.opencover.bean.newCoverBean();
				Nationality=newCover.getTransports();
				session.setAttribute("Nationality",Nationality);
				countryId=null;
		        String tranvalues ="";
				String tranId = "";
				tokens=new StringTokenizer(getDetail[0][3],",");
				while(tokensTransMode.hasMoreTokens()) {
					countryId=tokensTransMode.nextToken();
					System.out.println("countryIdcountryId"+countryId);
					String countryName = countryId.substring(0,countryId.indexOf('-'));
			
					for(int i=0;i<Nationality.length;i++) {
					//	modeNames=""+Double.parseDouble((String)rateModify.get(12));
						String mode[][] = (String [][])rateModify.get(12);
						for(int j=0;j<mode.length;j++) {
							if(mode[j][1].equalsIgnoreCase(countryName)) {
								modeNames = mode[j][0];
								tranId = mode[j][2];
							} 
						}
					modeName="0";
					String modevalues="";
					if(Nationality[i][0].equalsIgnoreCase(countryName)) { %>
			<tr>
				<td><%=Nationality[i][0]%></td>
				<%}}

			if(getDetail[0][6].length()>1) {
				tokens1=new java.util.StringTokenizer(getDetail[0][6],"#");
				while(tokens1.hasMoreTokens()) {
					modeName=tokens1.nextToken();
					
					if(tranId.equalsIgnoreCase(modeName.substring(0,modeName.indexOf('~'))))
					modeNames=modeName.substring(modeName.indexOf('~')+1,modeName.length());
				}}%>
				<td>
					<input type="text" class="inputBox" name="dest_wsrcc_<%=tranId%>" value="<%=request.getParameter("dest_wsrcc_"+tranId)!=null?((""+request.getParameter("dest_wsrcc_"+tranId)).equalsIgnoreCase("")?"0":request.getParameter("dest_wsrcc_"+tranId)):modeNames%>" onkeyup="checkNumbers(this)" maxlength="8"  <%="GUEST".equalsIgnoreCase(loginIds)?"DISABLED":""%>>					
				</td>
				<%tranvalues  = tranvalues + tranId+",";%>
			</tr>
			<%if("GUEST".equalsIgnoreCase(loginIds)){%>
				<input type="hidden" name="dest_wsrcc_<%=tranId%>" value="<%=request.getParameter("dest_wsrcc_"+tranId)!=null?((""+request.getParameter("dest_wsrcc_"+tranId)).equalsIgnoreCase("")?"0":request.getParameter("dest_wsrcc_"+tranId)):modeNames%>">
			<%}%>
			<%}%>
			<input type="hidden" name="dest_countryId" value="<%=tranvalues%>">
			</tbody>
		</table>
		<% } %>
	</td>
	</tr>	
</table>
	
<input type="hidden" name="wsrc" value="<%=wsrc%>">

<!-- Deductible Block -->
<br/>
<table  align="center" border="0" cellpadding="2" cellspacing="2" width="100%" style="display: none;">
	<tr>
		<td><span class="heading">Deductibles</span></td>
	</tr>
	<tr>
		<td>
			<table width="100%" class="footable">
				<thead>
				<tr>
					<th width="20%">Start Range (<%=currencyType%>)</th>
					<th width="20%">End Range (<%=currencyType%>)</th>
					<th width="20%">Excess Amount</th>
					<th width="20%">Excess Percent</th>
					<th width="20%">Currency</th>
					<input type="hidden" name="totalDeductibles" value="<%=totalDeductibles %>">
					<input type="hidden" name="addDeductible" value="N">
				</tr>
				</thead>
				<tbody>
				<% for(int j=1;j<=totalDeductibles;j++){ %>				
				<tr>
					<td><input type="text" class="inputBox" style="text-align: right;" name="startRange<%=j%>" value="<%= start[j-1]%>" maxlength="15"/></td>
					<td><input type="text" class="inputBox" style="text-align: right;" name="endRange<%=j%>" value="<%= end[j-1]%>" maxlength="15"/></td>
					<td><input type="text" class="inputBox" style="text-align: right;" name="excessAmount<%=j%>" value="<%= amount[j-1]%>" maxlength="10"/></td>
					<td><input type="text" class="inputBox" style="text-align: right;" name="excessPercent<%=j%>" value="<%=percent[j-1]%>" maxlength="5"/></td>
					<!--<td width="25%" align="center" class='formtxtf'>
						<input type="text" name="currencyValue<%=j%>" value="<%= currencyValue[j-1]%>"/>
					</td>-->
					<td>
						<select name='currencyValue<%=j%>' id='currencyValue<%=j%>' class='inputSelect'>
							<option value='0'>
								Select
							</option>
							<%for (int k = 0; k < channeldetails.length; k++) {%>
							<option value=<%=channeldetails[k][4]%> <%=channeldetails[k][4].equalsIgnoreCase(currencyValue[j-1])?"selected":""%>>
								<%=channeldetails[k][1]%>
							</option>
							<%}%> 
						</select>
					</td>
				</tr>
				<%} %>
				<tr>
					<td colspan="5" align="right"><input type="button" class="btn" value="Add More" onClick="return addDeductibles()" /></td>
				</tr>
				</tbody>
			</table>
		</td>
	</tr>
</table>
<br/>
<% 
	String proposalConfirm="NO";
	proposalConfirm=rate.getConfirmInfo((String)session.getAttribute("proposalNo"));
%>
<%if("GUEST".equalsIgnoreCase(loginIds)){%>
<table  align="center" border="0" cellpadding="2" cellspacing="2" width="100%">
	<tr> 
		<td class="formtxtc" align="center">Would you like to confirm the proposal Rates?
		<input name="guestRateYN" type="radio" value="YES"  <%=("YES".equalsIgnoreCase(proposalConfirm))?"checked":"" %> >YES
		<input name="guestRateYN" type="radio" value="NO" <%=("".equalsIgnoreCase(proposalConfirm)|| "NO".equalsIgnoreCase(proposalConfirm))?"checked":"" %>>NO
		</td> 
	</tr>
</table>
<br/>
<%}%>
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
%>	
<%if(!"GUEST".equalsIgnoreCase(loginIds) && ("Approved".equalsIgnoreCase(proposalStatus)|| "Referral".equalsIgnoreCase(proposalStatus))){%>
<table  align="center" border="0" cellpadding="2" cellspacing="2" width="100%">
	<tr> 
		<td class="formtxtc" align="center">Would you like to Approve the Rates and Clauses?
		<input name="proposalStatus" type="radio" value="Approved"  <%=("Approved".equalsIgnoreCase(proposalStatus))?"checked":"" %> >YES
		<input name="proposalStatus" type="radio" value="Referral" <%=("".equalsIgnoreCase(proposalStatus)|| "Referral".equalsIgnoreCase(proposalStatus))?"checked":"" %>>NO
		</td> 
	</tr>
</table>
<br/>
<%}%>

<!-- Deductible Block Ends -->
<% 
	String endtTypeId="";
	List list=null;
	String[][] endtInfo=beans.getEndtTypeInfo(chkProposalNo);
	if(endtInfo!=null && endtInfo.length>0){
		Map map=null;
		String endtYN=endtInfo[0][0]==null?"N":"Y";
		if(request.getAttribute("errorDetail")==null){
			endtTypeId=endtInfo[0][1]==null?"":endtInfo[0][1];
			list=CollectionUtils.arrayToList(endtTypeId.split(","));
		}else{
			list=CollectionUtils.arrayToList(request.getParameterValues("endtType"));
		}
		if("Y".equalsIgnoreCase(endtYN)){
%>
<br/>
<table  align="center" border="0" cellpadding="2" cellspacing="2" width="100%">
	<tr>
		<td><font class='heading'>&nbsp;&nbsp;Endorsement Type</font>
			<input type="hidden" name="endtYN" value="<%=endtYN%>"/></td>
	</tr>
	<tr>
		<td>
			<table width="100%" align="center" border="0" cellspacing="0" cellpadding="6" >
			   <tr>
				   <%
				   if(endtTypeList!=null && !endtTypeList.isEmpty()){
				   		for(int i=0; i<endtTypeList.size(); i++){
				   			map=(Map)endtTypeList.get(i);
				   %>
					<td class="formtxtf">
				   		<input type="checkbox" name="endtType" value="<%=map.get("ENDT_TYPE_ID").toString()%>" title="<%=(String)map.get("ENDT_TYPE_DESC")%>" id="endtType<%=map.get("ENDT_TYPE_ID").toString()%>"  <%=list.contains(map.get("ENDT_TYPE_ID").toString())?"checked":""%>/><%=(String)map.get("ENDT_TYPE")%>
					</td>
					<%if((i+1)%3==0) {%>
					</tr><tr>
			  	 	<%}}}%>
			   </tr>
		   </table>
		</td>
	</tr>
</table>
<%}} %>	
<!-- Royal New Check -->

<table  align="center" border="0" cellpadding="2" cellspacing="2" width="100%">
	<!--<tr>
				<td width="23%" class='bottomtext'><b>&nbsp;&nbsp;&nbsp;Insurance Company Name</b></td>
				
				<td width="27%" class='bottomtext'><b>Shared Percentage</b></td>
				
	</tr>
	--><% getDetail=(String[][])rateModify.get(0); %>
	<%--<tr>
				<td width="23%" class='formtxtf'>&nbsp;&nbsp;&nbsp;Emirates Shared Percentage </td>
				<td width="23%" class='formtxtf' align = "">
				<%=fmt.format(Double.parseDouble(getDetail[0][1]==null?"0":getDetail[0][1]))%></td>
				
	</tr>
	--%><%
	getExisting=(String[][])rateModify.get(6);
	
	for(int i=0;i<Integer.parseInt(getDetail[0][0]==null?"0":getDetail[0][0]);i++) {
		modeName="";
		modeNames="";
		if(getExisting.length>i && getExisting[i][0]!=null) {
			modeName=getExisting[i][0];
			modeNames=""+Double.parseDouble(getExisting[i][1]);
		}%>
	<tr>
		<td width="25%" class='formtxtf1'></td>
				<!--<td width="23%" class='formtxtf'>Company Name </td>-->
				<td >&nbsp;&nbsp;&nbsp;
				<select name="company_<%=i%>" class="inputSelect">
				<option value="select">Select</option>
				<%for(int j=0;j<getDetail.length;j++){%>
				<option value="<%=getDetail[j][2]%>" <%=getDetail[j][2].equalsIgnoreCase(request.getParameter("company_"+i))?"selected":(modeName.equalsIgnoreCase(getDetail[j][2])?"selected":"")%> ><%=getDetail[j][3]%></option><%}%>
				</select></td>
				<!--<td width="27%" class='formtxtf'>Shared Percentage</td>-->
				<td width="23%" class='formtxtf'><input type="text" size = '6' class="inputBox" align = 'right' name="values_<%=i%>" value="<%=request.getParameter("values_"+i)!=null?request.getParameter("values_"+i):modeNames%>"></td>
	</tr>
	<%}%>
	<%selection="0";
	selection=(String)rateModify.get(8);
	%>
		
	<input type="hidden" name="warranty_clauses" value="<%=request.getParameter("warranty_clauses")!=null?request.getParameter("warranty_clauses"):selection%>">
	<%selection="0";
	selection=(String)rateModify.get(9);
	%>
	<input type="hidden" name="Exclusion_clauses" value="<%=request.getParameter("Exclusion_clauses")!=null?request.getParameter("Exclusion_clauses"):selection%>">
	<%selection="0";
	getExisting=null;
	try
	{
	getExisting=(String[][])rateModify.get(10);
	}catch(Exception e)
	{
	}
	String extraCoverIds=ct.getExtraCoverIds((String)session.getAttribute("proposalNo"), adminBranch);
	String[][] getTotWAR=rate.getWARClause(belongingBranch,extraCoverIds);

	try
	{
		if(getTotWAR!=null)
		{
		for(int i=0;i<getTotWAR.length;i++)
		{
			for(int j=0;j<getExisting.length;j++)
			{
				if(Integer.parseInt(getTotWAR[i][0])==Integer.parseInt(getExisting[j][1]))
						selection="1";
			}
		}
	}
	}
	catch(Exception e){}
	%>
	<input type="hidden" name="wsrcc_clauses" value="<%=(g==0?"1":selection)%>">
	<input type="hidden" name="shareCompany" value="<%=getDetail[0][0]==null?"0":getDetail[0][0]%>" >
	<input type = "hidden" name = "rsaper" value = "<%=getDetail[0][1]==null?"0":getDetail[0][1]%>">
</table>

<table  align="center" border="0" cellpadding="2" cellspacing="2" width="100%">
	<tr>
		<td align="center">
			<!--<td align='center'><input border='0' name="image" type="image"  src='../images/save_quit.gif' onclick='cancelAction()'/ >-->
			<!--<input border='0' name="image" type="image" src="../images/btn_cancel.gif"  onClick="return back()" height="25" width="74">
			<input name="image"  src="../images/save.gif" onClick="saves('1')" accesskey="p" height="25" type="image">
			<input name="image" accesskey="p"  src='<%=path%>/images/btn_proceed.gif' onClick="saves('2')" accesskey="p" height="25" type="image">
			-->
			<a href="#" onClick="return back()" > <img src="../images/Back.jpg"></a>
			&nbsp;&nbsp;&nbsp;
			<%if(session.getAttribute("openCoverNo")==null){ %>
			<a href="#" onClick="return saves('1')" > <img src="../images/SaveQuote.jpg"></a>
			&nbsp;&nbsp;&nbsp;
			<%} %>
			<a href="#" onClick="return saves('2')" > <img src="../images/Proceed.jpg" ></a>
		</td>
	</tr>
</table>
<input type="hidden" name="chkProposalNo" value="<%=chkProposalNo%>"/>
<%if("GUEST".equalsIgnoreCase(loginIds)){%>
	<input type="hidden" name="user" value="GUEST"/>
<%}%>
</form>
	</td>
</tr>
</table>
<script language="Javascript">

 function saves(i)
 {
	if(i==1)
		document.form1.actionPath.value="showOpencover.jsp";
	else
 		document.form1.actionPath.value="showOpencoverSummary.jsp";
	
	document.form1.action="rateModification.jspa";
	document.form1.submit();
	return false;
 }

 function cancelAction()
 {
	  document.form1.action = "ShowOpenCoverEntry.jsp"
	  document.form1.submit();
 }
 
 function back()
 {
	document.form1.action="Quotation.jsp";
	document.form1.submit();
	return false;
 }
 
 function warranties(id)
 {
 	var defaultClauses = "<%=defaultClauses%>";
    var URL = '';
    if(defaultClauses=="Y" && (id==1||id==2)){
    	//alert("Default Clauses has selected");
    }
    else {
	 	if(id==1)
			URL='warrantiesShow.jsp';
		else if(id==2)
			URL='exclusionShow.jsp';
		else if(id==3)
			URL='showCoverages.jsp';
		else if(id==4)
			URL='WSRCC.jsp?coverNo=<%=coverIDs%>';
		var windowName = "Warranty";
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
	 	}
	return false;
 }
	
 function freetext()
 {
   	  var URL = '';
  	  URL='showCoverages.jsp';
  	  var windowName = "freetext";
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
	   
 function ccc(values,cvrName)
 {
 	var defaultClauses = "<%=defaultClauses%>";
 	if(defaultClauses=="Y"){
    	//alert("Default Clauses has selected");
    }
 	else {
		var where_to= confirm("Do You Want to Edit the Clauses and Warrenties?");
		if(where_to==true)
		{
			 var URL = '';
	 		URL='conditionsOpenEdit.jsp?coverNo='+values+'&cvrName='+cvrName;
		
	 		var windowName = "Clauses";
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
		}
		return false;
	}
 }
 function checkNumbers(val){
	var value=val.value;
	var temp="";
	if(value!=null){
		temp=value.charAt(value.length-1);
		if((isNaN(temp) || temp==" ")&& temp!='.'){
			val.value=value.substring(0,value.length-1);
		}else if(value.indexOf('.')!=-1){
			val.value = value.substring(0,value.indexOf('.')) + value.substring(value.indexOf('.'),(value.indexOf('.')+7));
		}
	}	
}
 function addDeductibles()
 {
 	document.form1.totalDeductibles.value="<%=(totalDeductibles+1)%>";
    document.form1.addDeductible.value="Y"; 
	document.form1.actionPath.value="Premium.jsp";
	document.form1.action="rateModification.jspa";
	document.form1.submit();
	return false;
 }
 function validamt(val){ var value=val.value; if(value!=null){ val.value = value.replace(/[^,0-9.]/g,''); }}
</script>
</body>
</html>
<%}catch(Exception e){e.printStackTrace();
}%>