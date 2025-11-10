
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ include file="header.jsp" %>

<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="../css/style.css" rel="stylesheet" type="text/css">

<jsp:useBean id="admin" class="com.maan.admin.AdminBean">
</jsp:useBean>

</head>
<%
		int no_of_records=10;
		int displaypages=5;
		int samplepages=displaypages;
		if(request.getParameter("displaypages")!=null&&!request.getParameter("displaypages").equalsIgnoreCase(""))
	displaypages=request.getParameter("displaypages")==null?3:Integer.parseInt(request.getParameter("displaypages"));
		String productId=request.getAttribute("pid")==null?"":(String)request.getAttribute("pid");
		String  adminBranch = (String) session.getAttribute("AdminBranchCode") ==null ? "":(String) session.getAttribute("AdminBranchCode");
		String freightStatus="";
		freightStatus=request.getParameter("freightStatus")==null?"":request.getParameter("freightStatus");
		if(productId.equals(""))
		{
			 productId=(String)session.getAttribute("pid")==null?"":(String)session.getAttribute("pid");
			 
			if(productId.equalsIgnoreCase("FF"))
			{
				productId = "3";freightStatus="Yes";
			}
		}
		else
		{
			if(productId.equalsIgnoreCase("FF"))
			{
				productId = "3";freightStatus="Yes";
			}
			session.removeAttribute("pid");
			session.setAttribute("pid",productId);
		}

		String rep=request.getAttribute("rep1")==null?"":(String)request.getAttribute("rep1");
		//Rajesh Modified on 12/05
		String royalRep=request.getAttribute("PolicyCancel")==null?"":(String)request.getAttribute("PolicyCancel");
		if(royalRep.length()<=0)
			royalRep=request.getParameter("royalRep")==null?"":request.getParameter("royalRep");

		String data1="";
		String data2="";
		String company="";
			data1=request.getAttribute("data1")==null?"":(String)request.getAttribute("data1");
			data2=request.getAttribute("data2")==null?"":(String)request.getAttribute("data2");
			company=request.getAttribute("company")==null?"":(String)request.getAttribute("company");
		if(data1.equals("")||data2.equals(""))
		{
			rep=request.getParameter("rep")==null?"":request.getParameter("rep");
			data1=request.getParameter("data1")==null?"":request.getParameter("data1");
			data2=request.getParameter("data2")==null?"":request.getParameter("data2");
			//brokerId=request.getParameter("brokerId")==null?"":request.getParameter("brokerId");
			company=request.getParameter("company")==null?"":request.getParameter("company");
			if(data1.length()>7&&data2.length()>7)
			{
				session.setAttribute("data1",data1);
				session.setAttribute("data2",data2);
				session.setAttribute("rep",rep);
			}
		}	
			String PoStatus="No.OF Policies";
			if(rep.equalsIgnoreCase("p")||rep.equalsIgnoreCase("A"))
			{
				PoStatus="Number Of Policies";
			}
			else
			{
				PoStatus="Number Of Quotes";
			}
%>
<br/><br/>
<body>
<form name="form1" method="post" action="BrokerCreationController">
<table width="90%"  border="0" cellspacing="0" cellpadding="0">
            <td align="center" valign="top">
<table width="90%"  border="0" cellspacing="0" cellpadding="0">
<tr> <td width="90%" class="heading"><strong>PORTFOLIO </strong></td>
</tr>
<tr align="center">
<td colspan="3">&nbsp;</td>
</tr>
<tr align="center">
<td colspan="3">
<table width="100%"  border="0" cellspacing="1" cellpadding="0" style="align:justify; font-family:Arial;font-size:12px;font-weight:normal;" >
<tr>
<td align="center" class="ltbgyellow">
<%
	String entryDate="";
	session.removeAttribute("refferalFrom");
	session.setAttribute("refferalFrom","approved");
	String[][] app=new String[0][0];
	String broker_codes="";
		broker_codes = (String)session.getAttribute("broker_codes");
	broker_codes = broker_codes == null ? "" : broker_codes;

	String loginProIds = "";
		loginProIds = (String)session.getAttribute("loginProIds");
	loginProIds = loginProIds == null ? "": loginProIds;
	
	if(productId.equalsIgnoreCase("all"))
		productId = loginProIds;
	
	app=admin.getPortFolio(data1,data2,rep,productId,adminBranch,broker_codes,freightStatus);
	int length123=0;
	if(app.length==0)
	{
		length123=1;
	}
	else
	{
		length123=app.length;
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
	else
	if((displaypages-spage)==(samplepages-1)&&start!=0)
	{
		start--;
		displaypages--;
	}
	%>
		<tr >
		<td class="formtxtr" align="left">Start Date:<%=data1%></td><td></td>
		<td class="formtxtr" align="right">End Date:<%=data2%></td>
		</tr>
        <tr class="royamenuhead" >
		<td width="200" class="bottomtext" align="center"><b>S.No</b></td>
		<td width="200" class="bottomtext" align="center"><b>Date</b></td>
		<td width="200" class="bottomtext" align="center"><b><%=PoStatus%></b></td>
		</tr>
		<%
			int k=0;
			int skip=0;
			int count=0;
				for(int i=0;i<app.length;i++)
				{
				  k++;
				 if(spage>1)
				 {
					 skip=spage-1;
				     if(k<=(skip*no_of_records))
				    	continue;
				}
	%>
	<tr>
	<td width="200" class="formtxtc"><b><%=( k)%></b></td>
	<td width="200" class="formtxtc"><b>
	<%if(rep.equalsIgnoreCase("A")){%>
	<%=app[i][0]%>
	<%}else{%>
	<a href="#" class="two" title="Click to View" onClick="byDate('<%=app[i][0]%>','<%=rep%>','<%=data1%>','<%=data2%>')"><%=app[i][0]%></a>
	<%}%>
	</b></td>
	<td class="formtxtc" width="200" ><b><%=app[i][1]%></b></td>
	</tr>
	<%
		if(k==display)
			  break;
		  }
	%>
			<tr class="royamenuhead">
			<td align="right" colspan="8">
	<%
			 if(app.length>no_of_records)
			 {
					if(start>0)
					{
	%>
<a href ="javaScript:ExistingCustomers_B2B('<%=(start+1)%>','<%=displaypages%>','<%=start%>','<%=data1%>','<%=data2%>','<%=rep%>')">
<font  color="white"><<</font>&nbsp;&nbsp;</a>
						<%
						}
							 boolean flag=false;
							 int iValue=0;
						for(int i=start;i<pages;i++)	 
						{
							if(i<displaypages)
							{
			%>
				<a href ="javaScript:ExistingCustomers_B2B('<%=i+1%>','<%=displaypages%>','<%=start%>','<%=data1%>','<%=data2%>','<%=rep%>')">
				<font  color="white"><%=i+1%></font></a>
			<%
							}
							 else
							{
								flag=true;
								iValue=i;
								break;
							}
						}	 
						if(flag)
						{
			 %>
<a href ="javaScript:ExistingCustomers_B2B('<%=iValue%>','<%=displaypages%>','<%=start%>','<%=data1%>','<%=data2%>','<%=rep%>')">
&nbsp;&nbsp;<font color="white">>></font></a>
			<%
						}
							 }
			%>
							 </td>
							 </tr>
                    </table>
		<%
					if(app.length<=0)
					{
		%>
				<table>
                <tr>
				<td width="200" align="center" colspan="3" style="align:justify; font-family:Arial;font-size:12px;font-weight:normal;"><b>
				There is no Records in this period</b>
								</td>
								</tr>
								</table>
							  <%
							  }	
								%>
                  </td>
                </tr>
                <tr align="center"> 
                  <td colspan="3">
                    <table width="100%" align="center" border="0" cellspacing="0" cellpadding="0">
                     <tr align="center"> 
<td height="32" valign="middle"> 
<input name="image" type="image"  src='../images/Back.jpg'  onclick="back('<%=rep%>','<%=data1%>','<%=data2%>','<%=company%>')" />
	
					<%if(rep.equalsIgnoreCase("r")||rep.equalsIgnoreCase("A"))
					{
					%>
					&nbsp;&nbsp;&nbsp;
					<input name="image" type="image"  src='../images/Save.jpg'  onclick="SaveExcel('<%=rep%>','<%=data1%>','<%=data2%>','<%=productId%>')" />
				
					<%
					}
					%>
					</td>
	                  </tr>
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

  
  <input type="hidden" name="entryDate" >
  <input type="hidden" name="rep" >
  <input type="hidden" name="data1" >
  <input type="hidden" name="data2" >
  <input type="hidden" name="productId" >
  <input type="hidden" name="spage">
<input type="hidden" name="displaypages">
<input type="hidden" name="start">
<input type="hidden" name="identifyCus">
<input type="hidden" name="freightStatus" value="<%=freightStatus%>">
</form>
</body>

<script>
function back(rep,data1,data2,company)
{
	document.form1.rep.value=rep;
	document.form1.data1.value=data1;
	document.form1.data2.value=data2;
	document.form1.action="AdminPortfolio.jsp";
	document.form1.submit();
}
function byDate(eDate,status,data1,data2)
{
	document.form1.entryDate.value=eDate;
	document.form1.data1.value=data1;
	document.form1.data2.value=data2;
	document.form1.rep.value=status;
	document.form1.action="Portfolio_ByDate.jsp"
	document.form1.submit();

}
function ExistingCustomers_B2B(value123,displaypages,start,data1,data2,rep)
{
	document.form1.spage.value=value123;
	document.form1.start.value=start;
	document.form1.data1.value=data1;
	document.form1.data2.value=data2;
	document.form1.rep.value=rep;
	document.form1.displaypages.value=displaypages;
	document.form1.action="Approved_Policy_Portfolio.jsp"
	document.form1.submit();
}
function SaveExcel(reps,data1,data2,pid)
{
		document.form1.data1.value=data1;
		document.form1.data2.value=data2;
		document.form1.rep.value=reps;
		document.form1.productId.value=pid;
		if(reps=='A')
			document.form1.action="../admin/AuditShowxcelusingPoi.jsp";
		else
			document.form1.action="../admin/PendingQuoteShowxcelusingPoi.jsp";
		document.form1.submit();
		return false;
}	
</script>
</html>