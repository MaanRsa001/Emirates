<%@ page import="com.maan.common.util.StringUtil"%>
<%@ page import="java.util.*"%>
<html>
<head>
<title>Emirates</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="../css/style.css" rel="stylesheet" type="text/css">
</head>


<jsp:useBean id="admin" class="com.maan.admin.DAO.AdminBean">
</jsp:useBean>
<jsp:useBean id="premiumBean" class = "com.maan.premium.DAO.PremiumInputsBean">
<jsp:setProperty property="*" name="premiumBean"/>
</jsp:useBean>

<%		
	HashMap brokDetails = new HashMap();
	String currencyType = "";
	String decimalPlace="";
	brokDetails = (HashMap) session.getAttribute("BrokerDetails");
	if(brokDetails.size() >0)
	{
		currencyType = (String) brokDetails.get("CurrencyName");
		decimalPlace =(String) brokDetails.get("CurrencyDecimal");
		decimalPlace = decimalPlace == null ? "2" :decimalPlace;
	}

	java.text.NumberFormat fmt=null;
	if(decimalPlace.equalsIgnoreCase("2"))
		fmt=new java.text.DecimalFormat("##,##0.00");
	else if(decimalPlace.equalsIgnoreCase("3"))
	{
		fmt=new java.text.DecimalFormat("##,##0.000");
	}
	else if(decimalPlace.equalsIgnoreCase("4"))
		fmt=new java.text.DecimalFormat("##,##0.0000");
	else
		fmt=new java.text.DecimalFormat("##,##0.00");

 // for quote search
 String search_option=request.getParameter("search_option")==null?"":request.getParameter("search_option");
 String eDate=request.getParameter("eDate")==null?"":request.getParameter("eDate");
 String productId=request.getParameter("productId")==null?"":request.getParameter("productId");
 String freightStatus=request.getParameter("freightStatus")==null?"":request.getParameter("freightStatus");
 String  adminBranch = (String) session.getAttribute("adminBranch") ==null ? "":(String) session.getAttribute("adminBranch");

String error="";

int no_of_records=100;
int displaypages=5;
int samplepages=displaypages;
if(request.getParameter("displaypages")!=null&&!request.getParameter("displaypages").equalsIgnoreCase(""))
displaypages=request.getParameter("displaypages")==null?3:Integer.parseInt(request.getParameter("displaypages"));

String rep=request.getAttribute("rep1")==null?"":(String)request.getAttribute("rep1");
String data1="";
String data2="";
String company="";
if(rep.equalsIgnoreCase("d")||rep.equalsIgnoreCase("p")||rep.equalsIgnoreCase("c"))
{
	data1=request.getAttribute("data1")==null?"":(String)request.getAttribute("data1");
	data2=request.getAttribute("data2")==null?"":(String)request.getAttribute("data2");
}
else
{
	company=request.getAttribute("company")==null?"":(String)request.getAttribute("company");
}
if(data1.equals("")||data2.equals(""))
{
	rep=request.getParameter("rep")==null?"":request.getParameter("rep");
	data1=request.getParameter("data1")==null?"":request.getParameter("data1");
	data2=request.getParameter("data2")==null?"":request.getParameter("data2");
	company=request.getParameter("company")==null?"":request.getParameter("company");
}
    String PoStatus="QuoteNo";
    String pdfstatus="View PDF";
	if(rep.equalsIgnoreCase("p"))
	{
		PoStatus="Policy No";
		pdfstatus="View PDF";
	}
	else
	{
		PoStatus="Quote No";
		pdfstatus="View Draft";
	}
   %>
<body onload="window.print()">
<form name="form1" method="post">

<%
	String[][] unApp=new String[0][0];
	String applicationNo="";
	String from123="";
	String policyno="";
	String searchstatus="";
	String broker_codes="";
	broker_codes = (String)session.getAttribute("broker_codes");
	broker_codes = broker_codes == null ? "" : broker_codes;

	String loginProIds = "";
	loginProIds = (String)session.getAttribute("loginProIds");
	loginProIds = loginProIds == null ? "": loginProIds;

	if(productId.equalsIgnoreCase("All"))
		productId = loginProIds;

	unApp=admin.getPortfolioByDate(eDate,"Admin",rep,productId,adminBranch,broker_codes,freightStatus);

int length123=0;
if(unApp.length==0)
{
length123=1;
}
else
{
length123=unApp.length;
}
int pages=length123/no_of_records;

int rem=length123%no_of_records;
if(rem!=0)
{
pages=pages+1;
}

int display=0;

int spage=1;
int  start=0;
if(request.getParameter("spage")!=null&&!request.getParameter("spage").equalsIgnoreCase(""))
spage=request.getParameter("spage")==null?1:Integer.parseInt(request.getParameter("spage"));

if(request.getParameter("start")!=null&&!request.getParameter("start").equalsIgnoreCase(""))
start=request.getParameter("start")==null?0:Integer.parseInt(request.getParameter("start"));
display=no_of_records*spage;

if(spage>=displaypages)
{
	if(pages>displaypages)
	{
		start++;
		displaypages++;
	}
}
else if((displaypages-spage)==(samplepages-1)&&start!=0)
{
	start--;
	displaypages--;
}
String fontcolor="";

%>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
	<table width="100%"  border="0" cellspacing="1" cellpadding="0" >
	<tr>
	<td>
	<table  width="100%"  border="0" style="align:justify; font-family:Arial;font-size:12px;font-weight:normal;">
	<%
	if(!search_option.equalsIgnoreCase("YES"))
	{
	%>
		<tr><td colspan="6" align="right"><b>Quotes Generated Date :</b><%=eDate%></td></tr>
	<%

	}
	%>
		<tr class="royamenuhead" >
		<td class="bottomtext" height="25"   ><b>S.No</b></td>
		<td class="bottomtext" height="25" ><b>Broker Organization</b></td>
		<td class="bottomtext" height="25" ><b>Quote Created By</b></td>
		<td class="bottomtext" height="25"  ><b><%=PoStatus%></b></td>
		<td class="bottomtext" height="25"  ><b>Customer Name</b></td>
		<td class="bottomtext" height="25"  ><b>Entry Date</b></td>
		<td class="bottomtext" height="25"  ><b>Premium(<%=currencyType%>)</b></td>
		<td class="bottomtext" height="25"  ><b>Status</b></td>
		<td class="bottomtext" height="25"  ><b>Remarks</b></td>
	

	</tr>

	<%

	int k=0;
int skip=0;
int count=0;
String login = "";
HashMap quotePersons = new HashMap();
for(int i=0;i<unApp.length;i++)
{
	login = login+"'"+(unApp[i][1]!=null?unApp[i][1]:"")+"',";
}
if(login.length()>0)
	login = login.substring(0,(login.length()-1));

quotePersons = admin.getTotalQuotedPersons(login,adminBranch);



for(int i=0;i<unApp.length;i++)
{
	String status = unApp[i][3]==null?"":unApp[i][3];
	if(unApp[i][19].equalsIgnoreCase("Y"))
		status = "Referral";
	if(status.equalsIgnoreCase("Referral"))
		status = "Referral Pending";
	else if(status.equalsIgnoreCase("Admin"))
		status = "Referral Approved";
	else
		status = "Normal Pending";
		k++;
									
							 if(spage>1)
									{
							 skip=spage-1;
                 if(k<=(skip*no_of_records))
										{

				continue;

										}
	}
							if(k==1)
										{
						
								}


                    String str="";

								if(unApp[i][7]==null||unApp[i][7].equals("")||unApp[i][7].equalsIgnoreCase("null"))
									{
                                      str=(unApp[i][4]==null?"":unApp[i][4])+" "+(unApp[i][8]==null?"":unApp[i][8]);
									}
									else
									{
                                       str=unApp[i][7];
									}

String user="";
String broker123="";
	String[][] QuotedUser = new String[0][0];
	
	if(unApp[i][18].equalsIgnoreCase("1"))
		QuotedUser = admin.getQuotedPerson(unApp[i][1],adminBranch);
	else
		QuotedUser = admin.getQuotedPerson(unApp[i][18],adminBranch);

	String[][] BCompanyName = new String[0][0];
	if(quotePersons.get("brokers"+unApp[i][1])!=null)
		BCompanyName = (String[][])quotePersons.get("brokers"+unApp[i][1]);
	if(BCompanyName.length<=0)
		BCompanyName = admin.getBrokerNameByUserId123(unApp[i][1],adminBranch);
	if(BCompanyName.length>0)
	{
		broker123=BCompanyName[0][1]==null?"":BCompanyName[0][1];
	}
	if(QuotedUser.length > 0)
	{	
		if(QuotedUser[0][0].equals("2"))
		{
			user=broker123;
		}
		else
		{
			user=QuotedUser[0][1]==null?"":QuotedUser[0][1];
		}
	}		
%>
<tr class="formtxtf"> 
<td height="23" align="center" <%=fontcolor%>><%=(k)%></td>
<td align="left" <%=fontcolor%>><%=broker123%></td>
<td align="left" <%=fontcolor%>><%=user%></td>
<td align="center" <%=fontcolor%>><%=unApp[i][9]==null?"":unApp[i][9]%></td>
<td align="left" <%=fontcolor%>><%=str%></td>
<td align="left" <%=fontcolor%>><%=(unApp[i][20]==null?"0.0":unApp[i][20])%></td>
<td align="right" <%=fontcolor%>> <%=fmt.format((Double.parseDouble(unApp[i][5]==null?"0.0":unApp[i][5])+Double.parseDouble(unApp[i][6]==null?"":unApp[i][6])))%></td>
							<td align="left" <%=fontcolor%>><%=status%></td>
                              <td align="left" <%=fontcolor%>><%=unApp[i][10]==null?"Normal":unApp[i][10]%></td>
									
  </tr>

			
							<%

								  if(k==display)
								  break;
								}
							%>
<%
if(unApp.length<=0&&error.length()<=0)
{


%>
							<tr class="formtxtf">
							<td align="center" colspan="9">
						No Record For This QuoteNo
							</td>
							</tr>
							<%
						}	
							
							%>
						<tr class="blueborder">
							 <td height="12" align="right" colspan="9">


							 <%
							 if(unApp.length>no_of_records)
							 {
if(start>0)
						{
							 %>
<a href 
="javaScript:ExistingCustomers_B2B('<%=(start+1)%>','<%=displaypages%>','<%=start%>','<%=eDate%>','<%=data1%>','<%=data2%>','<%=rep%>','<%=search_option%>')"><font  color="blue"><<</font>&nbsp;&nbsp;</a>
							<%
						}

							 boolean flag=false;
							 int iValue=0;
						for(int i=start;i<pages;i++)	 
						{
							if(i<displaypages)
							{
							 %>
						<a href 
="javaScript:ExistingCustomers_B2B('<%=i+1%>','<%=displaypages%>','<%=start%>','<%=eDate%>','<%=data1%>','<%=data2%>','<%=rep%>','<%=search_option%>')"><font  color="blue"><%=i+1%></font></a>
							 
							 <%
							}
							 else
							{
								 flag=true;
								 iValue=i;
								
%>


<%
	//break;
 break;
							}
						}	 
						if(flag)
						{
							 %>
<a href 
="javaScript:ExistingCustomers_B2B('<%=iValue%>','<%=displaypages%>','<%=start%>','<%=eDate%>','<%=data1%>','<%=data2%>'
,'<%=rep%>','<%=search_option%>')">&nbsp;&nbsp;<font  color="blue">>></font></a>
							<%
						}
							 }
							 %>
							 </td>
							 </tr>
						</table>

						<tr>
						<td height="32" align="center" valign="middle" > 
						<input name="image" type="image"  src='../images/Back.jpg'  onclick="window.close()" / >
                          </td>
						</tr>
					</td>
				</tr>
				
			</table>
     

 
		</tr>
		</table>
                  </td>
                </tr>
                <tr align="center"> 
                  <td colspan="3">
                    <table width="100%"  border="0" cellspacing="0" cellpadding="0">
                     
                    </table>
                  </td>
                </tr>
                
              </table>
            </td>
          </tr>
        </table>
      </td>
    </tr>
    
  </table>

</form>
</body>
</html>
