
<%@page import="com.maan.premium.DAO.PremiumLogic"%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path=request.getContextPath();
%>
<%@ include file="header.jsp" %>
<%@ page import="com.maan.common.util.StringUtil" %>
<%@ page import="java.util.*" %>
<html>
<head>
<title>Emirates</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="../css/style.css" rel="stylesheet" type="text/css">

<jsp:useBean id="admin" class="com.maan.admin.AdminBean">
</jsp:useBean>
</head>

<body>
<form name="form1" method="post" action="BrokerCreationController">
<table width="213" border="0" cellspacing="0" cellpadding="0">

<%--@ include file="left.jsp" --%>

</tr>
</table>
</td>

<td width="1"></td>
<td align="left" valign="top">
<table width="100%"  border="0" cellspacing="0" cellpadding="0">
<tr> 
<td width="8" height="25" class="heading">&nbsp;</td>
<td width="1"></td>
<td width="98%" class="heading"><strong>REFERRAL CASES</strong></td>
</tr>
<tr align="center">
<td colspan="3">&nbsp;</td>
</tr>
<tr align="center">
<td colspan="3">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
<%
int no_of_records=10;
int displaypages=3;
int samplepages=displaypages;
if(request.getParameter("displaypages")!=null&&!request.getParameter("displaypages").equalsIgnoreCase(""))
displaypages=request.getParameter("displaypages")==null?3:Integer.parseInt(request.getParameter("displaypages"));
String  adminBranch = (String) session.getAttribute("AdminBranchCode") ==null ? "":(String) session.getAttribute("AdminBranchCode");
String search_option=request.getParameter("search_option")==null?"":request.getParameter("search_option");
String qno=request.getParameter("qno")==null?"":request.getParameter("qno");
String error="";
if(qno.trim().equals("")&&search_option.equalsIgnoreCase("YES"))
{
	error="Enter Valid QuoteNo";
}
else if(!StringUtil.isNumber(qno)&&search_option.equalsIgnoreCase("YES"))
{
	error="Enter Valid QuoteNo";
}

	String[][] app=new String[0][0];
	String[][] unApp=new String[0][0];
	String applicationNo="";
	String from123="";
	String eDate=request.getParameter("entryDate")==null?"":request.getParameter("entryDate");
	String freightStatus="";
	freightStatus=request.getParameter("freightStatus")==null?"":request.getParameter("freightStatus");
if(eDate.length()>0)
{
	session.removeAttribute("eDate");
    session.setAttribute("eDate",eDate);
}
String pid = "";
pid = (String)session.getAttribute("pid");
session.setAttribute("product_id",pid);

String broker_codes="";
broker_codes = (String)session.getAttribute("broker_codes");
broker_codes = broker_codes == null ? "" : broker_codes;

if(!search_option.equalsIgnoreCase("YES"))
{
	session.removeAttribute("search_option");
	session.removeAttribute("qno");
	if(pid!=null && !pid.equals("") && pid.length()>0)
		unApp=admin.getApprovedByDate(eDate,"Admin",pid,adminBranch,broker_codes,freightStatus);
	else
		unApp=admin.getApprovedByDate(eDate,"Admin","3",adminBranch,broker_codes,freightStatus);
}
else if(error.length()==0)
{
	session.removeAttribute("search_option");
	session.removeAttribute("qno");
	session.setAttribute("search_option",search_option);
	session.setAttribute("qno",qno);
	unApp=admin.getQuoteSearch(qno,"Admin",adminBranch,broker_codes);
}

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
%>
<%//@ include file="RefferalQuoteSearch.jsp" %>
<tr><td align="center" colspan="8"><font color="red"><%=error%></font></td><tr>
<%@ include file="admin_sub_menu_referal_approved.jsp" %>
<table width="100%"  border="0" cellspacing="1" cellpadding="0" >
<tr>
<td>
<table  width="100%" align="center" border="0">
<tr class="royamenuhead">
<td class="bottomtext"><b>S.No</b></td>
<td class="bottomtext"><b>Broker Organization</b></td>
<td class="bottomtext"><b>Quote Created By</b></td>
<td class="bottomtext"><b>Customer Name</b></td>
<td class="bottomtext"><b>Quote No</b></td>
<td class="bottomtext"><b>Referral Type</b></td>
<td class="bottomtext" ><b>Quote Type</b></td>
<%--<td align="center"><b>Upload/Download</b></td>
--%></tr>
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
		String broker123="";
		String referaltype="", quoteType="";
		String user123="",customerName="";

		k++;
		if(spage>1)
		{
			skip=spage-1;
			if(k<=(skip*no_of_records))
				continue;
		}
	customerName=new PremiumLogic().getCName(unApp[i][0]);
	String[][] QuotedUser = new String[0][0];
	if(unApp[i][4].equalsIgnoreCase("1"))
		QuotedUser = admin.getQuotedPerson(unApp[i][1],adminBranch);
	else
		QuotedUser = admin.getQuotedPerson(unApp[i][4],adminBranch);
	
	String[][] BCompanyName = new String[0][0];
	if(quotePersons.get("brokers"+unApp[i][1])!=null)
		BCompanyName = (String[][])quotePersons.get("brokers"+unApp[i][1]);
	if(BCompanyName.length<=0)
		BCompanyName = admin.getBrokerNameByUserId123(unApp[i][1],adminBranch);
		if(BCompanyName.length>0)
		{
			broker123=BCompanyName[0][1]==null?"":BCompanyName[0][1];
		}
		referaltype=unApp[i][3]==null?"AdminReferal":unApp[i][3];
		quoteType="E".equalsIgnoreCase(unApp[i][5])?"ENDT":"NORMAL";
		String bgColor="E".equalsIgnoreCase(unApp[i][5])?"style='background-color: silver;'":"";
		
		if(referaltype.equalsIgnoreCase("null"))
		{
			referaltype="AdminReferal";
		}
		if(QuotedUser.length>0)
		{
			if(QuotedUser[0][0].equals("2")&&unApp[i][4].equalsIgnoreCase("1"))
			{
				user123=broker123;
			}
			else
			{
			user123=QuotedUser[0][1]==null?"":QuotedUser[0][1];
		}
	}		
%>
							<tr> 
                              <td class="formtxtc" <%=bgColor %>><%=(i+1)%></td>
                              <td class="formtxtc" <%=bgColor %>><%=broker123==null?"":broker123%></td>
                              <td class="formtxtc" <%=bgColor %>><%=user123==null?"":user123%></td>
                              <td class="formtxtc" <%=bgColor %>><%=customerName==null?"":customerName%></td>
                                <!--rajesh -->
					 <%
								
							if(!pid.equalsIgnoreCase("21")&&!pid.equalsIgnoreCase("23"))
							{
					%>
								 <td class="formtxtc" <%=bgColor %>><a href="#" class="two" title="Click to Edit" onClick="byApp('<%=unApp[i][0]%>','<%=eDate%>','<%=broker123%>','<%=unApp[i][2]%>','<%=search_option%>','<%=qno%>','<%=unApp[i][5]%>')"><%=unApp[i][2]%></a></td>
                              
					<%
							}
							else if(pid.equalsIgnoreCase("21")||pid.equalsIgnoreCase("23"))
							{
										
					%>
								<td class="formtxtc" <%=bgColor %>><a href="#" class="two" title="Click to Edit" onClick="byHomeApp('<%=eDate%>','<%=unApp[i][2]%>','<%=pid%>')"><%=unApp[i][2]%></a></td>
                              
                    <%
							}
					%>
					<!--rajesh -->
                    <td class="formtxtc" <%=bgColor %>><%=referaltype%></td>
                    <td class="formtxtc" <%=bgColor %>><%=quoteType%></td>
                    <%--<td align="center" class="text">
               		<a href="#"> <img border='0' src="<%=path%>/images/icon_change_address.gif" title="Upload/Download File" width="19" height="22" onclick="return fileProcess('<%=unApp[i][2]%>','<%=(String)session.getAttribute("pid")%>','<%=start%>','<%=eDate%>')"></a> 
                	</td>
                    --%></tr>
					<%
						 if(k==display)
							  break;
							}
					%>
						<%
						if(unApp.length==0&&error.length()==0)
						{
						%>
							  <tr> 
                              <td height="23" align="center" colspan="4">There is no records</td>
							  </tr>
						<%
						}	
						%>
						<tr class="royamenuhead">
						<td class="bottomtext" align="right" colspan="8">
						
						 <%
if(length123>no_of_records)
{
if(start>0)
						{
							 %>
<a href ="javaScript:ExistingCustomers_B2B('<%=(start+1)%>','<%=displaypages%>','<%=start%>','<%=eDate%>')"><font  color="white"><<</font>&nbsp;&nbsp;</a>
							<%
						}

							 boolean flag=false;
							 int iValue=0;
						for(int i=start;i<pages;i++)	 
						{
							if(i<displaypages)
							{
							 %>
						<a href ="javaScript:ExistingCustomers_B2B('<%=i+1%>','<%=displaypages%>','<%=start%>','<%=eDate%>')"><font  color="white"><%=i+1%></font></a>
							 
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
<a href ="javaScript:ExistingCustomers_B2B('<%=iValue%>','<%=displaypages%>','<%=start%>','<%=eDate%>')">&nbsp;&nbsp;<font  color="white">>></font></a>
							<%
						}

		}
							 %>
							 </td>
							 </tr>
						</table>
						<tr>
					<%
						if(!search_option.equalsIgnoreCase("YES"))
						{
					%>
						<td height="32" align="center" valign="middle" > 
                          <a onclick="back()"><img src="<%=path%>/images/Back.jpg"></a></td>
					<%
						}
					%>
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

  <input type="hidden" name="requestfrom" value="BrokerDeactivation">
  <input type="hidden" name="applicationNo" >
  <input type="hidden" name="from123" >
  <input type="hidden" name="eDate" >
  <input type="hidden" name="bName" >
  <input type="hidden" name="quoteNo" >
  <input type="hidden" name="qno" >
  <input type="hidden" name="qno1" >
  <input type="hidden" name="spage">
	<input type="hidden" name="displaypages">
	<input type="hidden" name="start">
	<input type="hidden" name="identifyCus">
	<input type="hidden" name="search_option">
  <input type="hidden" name="entryDate" >
  <!-- Rajesh For Home -->
	<input type="hidden" name="ProId"/>
	<input type="hidden" name="QuoteNo"/>
	<input type="hidden" name="reqFrom"/>
<!-- Rajesh For Home -->
<input type="hidden" name="freightStatus1" value="<%=freightStatus%>">

<!-- Marine Upload  -->
 <input type="hidden" name="data1" >
 <input type="hidden" name="data2" >
<input type="hidden" name="pqno" />
<input type="hidden" name="productId"/>
<input type="hidden" name="screenFrom" />
<input type="hidden" name="scrnFrom" />
<input type="hidden" name="loginId" />
<input type="hidden" name="quoteStatus" />
<!-- Marine Upload -->

</form>
</body>
</html>
<script>
function back()
{
	//document.form1.entryDate.value=eDate;
document.form1.action="Approved_Policy.jsp"
document.form1.submit();

}

function byApp(applicationNo,eDate,bName,quoteNo,search_option,qno1,type)
{
	editQuote(applicationNo, bName);
}
function ExistingCustomers_B2B(value123,displaypages,start,eDate)
{
	//alert("licked page"+value);
	document.form1.spage.value=value123;
	document.form1.start.value=start;
	document.form1.entryDate.value=eDate;
	document.form1.displaypages.value=displaypages;
	document.form1.action="Approved_Policy_ByDate.jsp"
	document.form1.submit();
}
//Rajesh
function byHomeApp(eDate,quoteNo,productId)
{
	
	//Rajesh
	document.form1.QuoteNo.value=quoteNo;
	document.form1.ProId.value=productId;
	document.form1.eDate.value=eDate;
	document.form1.reqFrom.value="Approved";
	//Rajesh
	document.form1.action="../HomeInsurance/AdminHomeSummary.jsp";
	document.form1.submit();
}

// Marine Upload Start
function fileProcess(pqno,productId,data1,data2)
{
	document.form1.entryDate.value=data2;
	document.form1.data1.value=data1;
	document.form1.data2.value=data2;

	document.form1.screenFrom.value="AdminApproved";
	document.form1.scrnFrom.value="AdminApproved";
	document.form1.pqno.value = pqno;
	document.form1.productId.value = productId;
	document.form1.action = "<%=request.getContextPath()%>/marineUploadDownload.jsp?pqno="+pqno;
	document.form1.submit();
}
function editQuote(applicationNo, loginId)
{
	document.form1.quoteStatus.value='RA';
	document.form1.applicationNo.value=applicationNo;
	document.form1.loginId.value=loginId;
	document.form1.action = "<%=request.getContextPath()%>/editQuoteQuotation.action";
	document.form1.submit();
}
// Marine Upload End

</script>