<%@ include file="submenu.jsp"%>

<%@ page import="java.util.StringTokenizer,java.util.Hashtable" %>
<head>
	<title> Sale Term Selection</title>

 </head>
  <link href="<%=request.getContextPath() %>/css/style.css" rel="stylesheet" type="text/css">
  
  <%!
  	String[][] saleTerm=null;
  %>


<jsp:useBean id="newCover" class="com.maan.opencover.bean.newCoverBean">
<jsp:setProperty name="newCover" property="*"/>
</jsp:useBean>


<script language="javascript">
	NS = document.layers? 1:0;

	function moveAll(input,out) {
		for(k=0; k < out.options.length; k++) { 
			out.options[k] = new Option(out.options[k].text,out.options[k].value); 
		} 
		j = k;
		for(i=1; i < input.options.length; i++) { 
			out.options[k] = new Option(input.options[i].text,input.options[i].value);
			k++;
		} 
		out.length =k; 
		input.length = 1;
		if (NS) {  
		   history.go(0);  
		}
	}

	function add(input,out)
	 { 
      	var selLen = out.options.length; 
        var availLen = 0;

        for(k=0; k < out.options.length; k++) { 
            out.options[k] = new Option(out.options[k].text,out.options[k].value); 
        } 
        j = k;
		input.options[availLen++] = new Option(input.options[0].text,input.options[0].value); 
			
        for(i=1; i < input.options.length; i++) { 
			if(input.options[i].selected) { 
				out.options[j++] = new Option(input.options[i].text,input.options[i].value); 
				selLen++;
			} 
			else { 
				input.options[availLen++] = new Option(input.options[i].text,input.options[i].value); 
			}
        }
        input.length = availLen; 
        out.length = selLen; 
        if (NS) {  
			history.go(0);  
        }
	}

	function move(input,out,value) {
		var selLen = out.options.length;
		var availLen = 0;
		
		for(k=0; k < out.options.length; k++) { 
            out.options[k] = new Option(out.options[k].text,out.options[k].value); 
			
        } 
        j = k;
		input.options[availLen++] = new Option(input.options[0].text,input.options[0].value); 

		for(i=1; i < input.options.length; i++) { 
			if(input.options[i].value == value) { 
				out.options[j++] = new Option(input.options[i].text,input.options[i].value); 
				selLen++;
			} 
			else { 
				input.options[availLen++] = new Option(input.options[i].text,input.options[i].value); 
			}
        }
		input.length = availLen; 
        out.length = selLen; 
        if (NS) {  
			history.go(0);  
        }
	}
	
	function validate() {
		var docname = document.form1;
		var statesList;
		var length = docname.rightSide.options.length;
		if (length < 2) {
			alert("Please select a state to proceed further.");
			return;
		}
		var statesList = docname.rightSide[1].value;
		for(i = 2; i < docname.rightSide.options.length; i++) {
			statesList += "," + docname.rightSide[i].value;
		}
		docname.statesList.value = statesList;
		docname.submit();
	}
</script>

<%
	String adminBranch = (String) session.getAttribute("AdminBranchCode");
	//Belonging Branch
	String belongingBranch = (String) session.getAttribute("BelongingBranch");
		
		if(adminBranch.indexOf("'")!=-1)
			adminBranch = adminBranch.replaceAll("'","");
			
	saleTerm = newCover.getSaleTerms(belongingBranch);
  	StringBuffer saleTermArray= new StringBuffer();
	String stOption =request.getParameter("identify") == null ? "" : request.getParameter("identify");
	String saleTermMsg = "";
	if(stOption.equalsIgnoreCase("saleterm")){
		saleTermMsg = "Value Basis";
	}
 %>
       
       <body>
		<form name="form1" method="post">
		<br><br>
              <table width="100%" cellspacing="0" cellpadding="0">
                <tr><td height="16" align="center"><span class="heading"><%=saleTermMsg %> [ Multiple Selection ]</span></td></tr>
              </table>
		<br>
		<%
			String chkProposalNo = request.getParameter("chkProposalNo") == null ? "0" : request.getParameter("chkProposalNo");
			StringTokenizer tokens=new StringTokenizer(request.getParameter("saleTermIds")==null?"":request.getParameter("saleTermIds"),",");
			String tokenValue=null;
			Hashtable CountryIdChoose =	new Hashtable();
			int s=0;
			while(tokens.hasMoreTokens())
			{
				tokenValue=(String)tokens.nextToken();
				CountryIdChoose.put(""+s,tokenValue);
				s	=	s	+	1;
			}
		%>

<input type="hidden" name="statesList">
<table width="570" border="0" cellspacing="0" cellpadding="0" align="center">
 
			  <tr><td colspan="3">&nbsp;</td></tr>
              <tr > 
                  <td height="22" class="t" width="45%" align="center">
					<select name="leftSide" multiple size="15" style="width:180px" class='inputSelect1'>
					<option value=''>--- Available Value Basis ---</option>
					<%
					String TempTest	=	new String();
					for(int i=0;i<saleTerm.length;i++)
						{
								saleTermArray.append("'"+saleTerm[i][0]+"',");
								for(int j=0;j<CountryIdChoose.size();j++)
								{
									String TempVal	=	(String)CountryIdChoose.get(""+j);
									if(saleTerm[i][0].equalsIgnoreCase(TempVal))
												TempTest		=	"Selected";
								}
								if(!TempTest.equalsIgnoreCase("Selected"))
								{
									%>
										<option value="<%=saleTerm[i][1]+","+saleTerm[i][0]%>"><%=saleTerm[i][1]%></option>
									<%
								}
								TempTest		=	"";


						}
					%>
					</select>
				  </td>
                  <td height="22" class="t" width="10%" align="middle">
						<table border="0" cellpadding="10" cellspacing="0">
							<tr><td align="center"><input type="button" name="button3" value="  >  "  class="fde1"  onClick="javascript:add(document.form1.leftSide,document.form1.rightSide);"     ></td></tr>
							<tr><td align="center"><input type="button" name="button4" value="  <  "  class="fde1"  onClick="javascript:add(document.form1.rightSide,document.form1.leftSide);"     ></td></tr>
							<tr><td align="center"><input type="button" name="button1" value=" >> "   class="fde1" onClick="javascript:moveAll(document.form1.leftSide,document.form1.rightSide);" ></td></tr>
							<tr><td align="center"><input type="button" name="button2" value=" << "   class="fde1" onClick="javascript:moveAll(document.form1.rightSide,document.form1.leftSide);" ></td></tr>
						</table>
				  </td>
				  
                  <td height="22" class="t" width="45%" align="center">
						<select name="rightSide" multiple size="15" style="width:180px" class='inputSelect1'>
						  <option value=''>--- Selected Value Basis ---</option>
						  <%
							for(int i=0;i<saleTerm.length;i++)
							{
								saleTermArray.append("'"+saleTerm[i][0]+"',");
								for(int j=0;j<CountryIdChoose.size();j++)
								{
									String TempVal	=	(String)CountryIdChoose.get(""+j);
									if(saleTerm[i][0].equalsIgnoreCase(TempVal))
									{
									%>
										<option value="<%=saleTerm[i][1]+","+saleTerm[i][0]%>"><%=saleTerm[i][1]%></option>
									<%
									}
								}
							}
						saleTermArray.deleteCharAt(saleTermArray.length()-1);
						  %>
						</select>
				  </td>
              </tr>
			</table>
          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>
<table width="100%"  border="0" cellspacing="0" cellpadding="0">
			<tr align="center">
				  <td height="32" align="center" valign="middle" >  
						<table border="0" align="center" cellpadding="0" cellspacing="0">
			<tr> 
				 <td align="center" class="">
				 <a href="#" onClick="window.close()" >
				 <img src="<%=request.getContextPath() %>/images/Back.jpg" > </td>
				<td>&nbsp;<input type="hidden" name="identify" value="<%=request.getParameter("identify")%>"/></td>
						 <td align="center" class="">
						 <a href="#" onClick="settingValues('<%=saleTerm.length%>')"  >
						 <img src="<%=request.getContextPath() %>/images/Submit.jpg"> </td>
					</table>
						&nbsp;&nbsp;&nbsp;
				  </td>
			</tr>
</table>
<input type="hidden" name="chkProposalNo" value="<%=chkProposalNo%>"/>
</form>

<script language="javascript">
function settingValues(lengthValue)
{
	if(window.opener.document.Quotation.chkProposalNo.value == document.form1.chkProposalNo.value)
	{
		if(document.form1.rightSide.options.length<=1)
		{
			alert("Please select value Basis");
		}
		else
		{
			nationalitys=new Array(<%=saleTermArray%>);
			
			var stNames="";
			var ids="";
			var val="";
			var count=0;
			
			var rightlength	=	document.form1.rightSide.length;

			for(i = 1; i < document.form1.rightSide.options.length; i++) 
			{
				count++;
				var val	=0;
				val=document.form1.rightSide.options[i].value;
				stNames=stNames+","+(val.substr(0,val.indexOf(',')));
				ids=ids+","+(val.substr(val.indexOf(',')+1,val.length));
			}
					
			ids=ids.substr(1,ids.length);
			stNames=stNames.substr(1,stNames.length);

			if('saleterm'==document.form1.identify.value)
			{
				window.opener.document.Quotation.saleTermName.value=stNames;
				window.opener.document.Quotation.saleTermId.value=ids;
				//window.opener.document.Quotation.totalSaleTermId.value=count;
				window.opener.document.Quotation.tolerance.value='';
				window.opener.document.Quotation.toleranceId.value=''; 
			}
			window.close();
		}
	} // chkProposalNo if
	else
	{
		alert("please close the value Basis selection window and open again");
		window.close();
	}
}
</script>
</body>
                     
