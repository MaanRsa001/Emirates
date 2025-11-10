<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%

	String path = request.getContextPath();
%>
<%@ include file="header.jsp" %>
<html>
<head>
<title>Emirates</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="/css/style.css" rel="stylesheet" type="text/css">
<jsp:useBean id="admin" class="com.maan.admin.ExportBean">
</jsp:useBean>
</head>

<%

	int no_of_records=10;
	int displaypages=5;
	int samplepages=displaypages;
	if(request.getParameter("displaypages")!=null&&!request.getParameter("displaypages").equalsIgnoreCase(""))
		displaypages = request.getParameter("displaypages")==null?3:Integer.parseInt(request.getParameter("displaypages"));

%>
	           
<body>
<form name="form1" method="post" action="BrokerCreationController">
<br/><br/>
 <table width="900" border="0" cellspacing="0" cellpadding="0" align="center">
           <tr> 
                 <td width="8" height="25" class="heading">&nbsp;</td>
                 <td width="1" class="heading"></td>
                 <td width="98%" class="heading"><strong>TRN CLOSING REPORTS</strong></td>
          </tr>
          <tr align="center">
            <td colspan="3">&nbsp;</td>
          </tr>
          <tr align="center">
            <td colspan="3">
			<table width="100%"  border="0" cellspacing="1" cellpadding="0" >
              <tr>
               <td align="center">
                          				  

<%
	String productCodes=request.getParameter("productCode")==null?"":(String)request.getParameter("productCode");
	String[][] app = new String[0][0];
	app = admin.selectTRNClosingDetails(actualBranch,productCodes);

	int length123=0;
	if(app.length==0)
		length123=1;
	else
		length123=app.length;
	int pages=length123/no_of_records;
	int rem=length123%no_of_records;
	if(rem!=0)
		pages=pages+1;
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

 <tr class="blueborder" align="center">
			<td width="200" align="center" class="heading">S.No</td>
			<td width="200" align="center" class="heading">MONTH</td>
			<td width="200" align="center" class="heading">START DATE</td>
			<td width="200" align="center" class="heading">END DATE</td>
			<td width="200" align="center" class="heading">FUTURE DATE</td>
			<td width="200" align="center" class="heading">REMARKS</td>
			<td width="200" align="center" class="heading">ENTRY DATE</td>
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
		<td width="200" align="center" class="formtextf"><%=(app[i][0]!=null?app[i][0]:"")%></td>
		<td width="200" align="center" class="formtextf"><%=(app[i][1]!=null?app[i][1]:"")%></td>
		<td width="200" align="center" class="formtextf"><%=(app[i][2]!=null?app[i][2]:"")%></td>
		<td width="200" align="center" class="formtextf"><%=(app[i][3]!=null?app[i][3]:"")%></td>
		<td width="200" align="center" class="formtextf"><%=(app[i][4]!=null?app[i][4]:"")%></td>
		<td width="200" align="center" class="formtextf"><%=(app[i][6]!=null?app[i][6]:"")%></td>
		<td width="200" align="center" class="formtextf"><%=(app[i][5]!=null?app[i][5]:"")%></td>
	</tr>
<%
									
		 if(k==display)
			  break;
	  }
%>
<tr class="blueborder">
							 <td height="12" align="right" colspan="8">
 <%
	 if(app.length>no_of_records)
	 {
		if(start>0)
		{
%>
	<a href = "javaScript:Existing('<%=(start+1)%>','<%=displaypages%>','<%=start%>')"> <font  color="blue"> <<</font>&nbsp;&nbsp;</a>
<%
		}
	 boolean flag=false;
	 int iValue=0;
	for(int i=start;i<pages;i++)	 
	{
		if(i<displaypages)
		{
%>
	<a href ="javaScript:Existing('<%=i+1%>','<%=displaypages%>','<%=start%>')"> <font  color="blue"><%=i+1%></font></a>
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
<a href ="javaScript:Existing('<%=iValue%>','<%=displaypages%>','<%=start%>')">&nbsp;&nbsp;<font  color="blue">>></font></a>
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
           <tr class="mdblue">
				<td width="200" align="center" colspan="3"><b>
					There is no records in this period</b>
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
                    <table width="100%"  border="0" cellspacing="0" cellpadding="0">
                      <tr> 
                   

 <td height="32" align="center" valign="middle">
                         <input name="image" type="image"  src='<%=path%>/images/Back.jpg'  onclick="back()" /></td>
	
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
	<input type="hidden" name="spage">
	<input type="hidden" name="displaypages">
	<input type="hidden" name="start">
</form>
</body>

<script>
function back()
{
	document.form1.action="Close_Date.jsp";
	document.form1.submit();
}

function Existing(value123,displaypages,start)
{
	
	document.form1.spage.value=value123;
	document.form1.start.value=start;
	document.form1.displaypages.value=displaypages;
	document.form1.action="Close_Date_Report.jsp"
	document.form1.submit();
}
</script>
</html>