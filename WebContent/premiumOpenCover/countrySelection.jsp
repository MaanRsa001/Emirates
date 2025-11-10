<%@ include file="submenu.jsp"%>

<%@ page import="java.util.StringTokenizer,java.util.Hashtable" %>
<head>
	<title> Country Selection</title>
<link href="<%=request.getContextPath() %>/css/style.css" rel="stylesheet" type="text/css">
 </head>
  
  
  <%!
  	String[][] Nationality=null;
  	String[][] contientList=null;
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


	function add(input,out) { 
		//alert("SKRTEST"+"----------Input"+input+"------------Out"+out);

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
	
	function moveContinent(input, out, countryList) {
		for(j=1; j < input.options.length; j++) {
			input.options[j].selected = false;
		}
		var res = countryList.split("~");
		for(i=0 ; i < res.length ; i++) {
			for(j=1; j < input.options.length; j++) {
				var inputOption = input.options[j].value.split("~");
				if(inputOption[1]==res[i]) {
					input.options[j].selected = true;
				}
			}
		}
		add(input,out);
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
	String adminBranch = (String)session.getAttribute("adminBranch");
	Nationality = newCover.getCountrys();
	contientList = newCover.getContientList(adminBranch);
  	StringBuffer countryArray= new StringBuffer();
	String countryOption =request.getParameter("identify") == null ? "" : request.getParameter("identify");
	String countryMsg = "";
	if(countryOption.equalsIgnoreCase("transit-country")){
		countryMsg = "Country Of Origin";
	}
	else{
		countryMsg = "Country Of Destination";
	}
 %>
       
       <body>
		<form name="form1" method="post">
		<br><br>
              <table width="100%" cellspacing="0" cellpadding="0">
                <tr><td height="16" align="center"><span class="heading"><%=countryMsg %> [ Multiple Country ]</span></td></tr>
              </table>
		<br>
		<%
			String chkProposalNo = request.getParameter("chkProposalNo") == null ? "0" : request.getParameter("chkProposalNo");
			StringTokenizer tokens=new StringTokenizer(request.getParameter("countryIds")==null?"":request.getParameter("countryIds"),",");
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
					<select name="leftSide" multiple size="15" style="width:280px" class='inputSelect1'>
					<option value=''>--- Available Country ---</option>
					<%
					String TempTest	=	new String();
					for(int i=0;i<Nationality.length;i++)
						{
								countryArray.append("'"+Nationality[i][0]+"',");
								for(int j=0;j<CountryIdChoose.size();j++)
								{
									String TempVal	=	(String)CountryIdChoose.get(""+j);
									if(Nationality[i][0].equalsIgnoreCase(TempVal))
												TempTest		=	"Selected";
								}
								if(!TempTest.equalsIgnoreCase("Selected"))
								{
									%>
										<option value="<%=Nationality[i][1]+"~"+Nationality[i][0]%>"><%=Nationality[i][1]%></option>
									<%
								}
								TempTest		=	"";


						}
					%>
					</select>
				  </td>
                  <td height="22" class="t" width="10%" align="middle">
						<table border="0" cellpadding="10" cellspacing="0">
							<tr><td align="center"><input type="button" name="button3" value="  >  "  class="fde1" style="cursor: pointer;" onClick="javascript:add(document.form1.leftSide,document.form1.rightSide);"     ></td></tr>
							<tr><td align="center"><input type="button" name="button4" value="  <  "  class="fde1" style="cursor: pointer;" onClick="javascript:add(document.form1.rightSide,document.form1.leftSide);"     ></td></tr>
							<tr><td align="center"><input type="button" name="button1" value=" >> "   class="fde1" style="cursor: pointer;" onClick="javascript:moveAll(document.form1.leftSide,document.form1.rightSide);" ></td></tr>
							<tr><td align="center"><input type="button" name="button2" value=" << "   class="fde1" style="cursor: pointer;" onClick="javascript:moveAll(document.form1.rightSide,document.form1.leftSide);" ></td></tr>
						</table>
				  </td>
				  
                  <td height="22" class="t" width="45%" align="center">
						<select name="rightSide" multiple size="15" style="width:280px" class='inputSelect1'>
						  <option value=''>--- Selected Country ---</option>
						  <%
							for(int i=0;i<Nationality.length;i++)
							{
								countryArray.append("'"+Nationality[i][0]+"',");
								for(int j=0;j<CountryIdChoose.size();j++)
								{
									String TempVal	=	(String)CountryIdChoose.get(""+j);
									if(Nationality[i][0].equalsIgnoreCase(TempVal))
									{
									%>
										<option value="<%=Nationality[i][1]+"~"+Nationality[i][0]%>"><%=Nationality[i][1]%></option>
									<%
									}
								}
							}
						countryArray.deleteCharAt(countryArray.length()-1);
						  %>
						</select>
				  </td>
              </tr>
              <%
              	if(contientList.length>0) {
              %>
	              <tr class="tabin"><td colspan="3">&nbsp;</td></tr>
	              <tr class="tabin">
	              	<td height="16" align="center">
	              		<span class="heading">Include</span>
	              	</td>
		            <td></td>
		            <td height="16" align="center">
	              		<span class="heading">Exclude</span>
	              	</td>
	              </tr>
	              <%
	              	for(int i=0 ; i<contientList.length ; i++) {
	              %>
		              <tr class="tabin"><td colspan="3">&nbsp;</td></tr>
		              <tr>
		              	  <td align="center">
		              	  	<input type="button" name="button1" value=" <%=contientList[i][2] %> " style="width: 200px;cursor: pointer;"  class="fde1" onClick="moveContinent(document.form1.leftSide,document.form1.rightSide,'<%=contientList[i][3] %>');" >
		              	  </td>
			              <td></td>
			              <td align="center">
			              	<input type="button" name="button2" value=" <%=contientList[i][2] %> " style="width: 200px;cursor: pointer;"  class="fde1" onClick="moveContinent(document.form1.rightSide,document.form1.leftSide,'<%=contientList[i][3] %>');" >
		              	  </td>
			          </tr>
			      <%
		          	}
		          %>
	          <%
	          	}
	          %>
			  <tr class="tabin"><td colspan="3">&nbsp;</td></tr>
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
						 <a href="#" onClick="settingValues('<%=Nationality.length%>')"  >
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
			alert("Please select Counry");
		}
		else
		{
			nationalitys=new Array(<%=countryArray%>);
			
			var countrys="";
			var ids="";
			var val="";
			var count=0;
			
			var rightlength	=	document.form1.rightSide.length;

			for(i = 1; i < document.form1.rightSide.options.length; i++) 
			{
				count++;
				var val	=0;
				val=document.form1.rightSide.options[i].value;
				countrys=countrys+","+(val.substr(0,val.indexOf('~')));
				ids=ids+","+(val.substr(val.indexOf('~')+1,val.length));
			}
					
			ids=ids.substr(1,ids.length);
			countrys=countrys.substr(1,countrys.length);

			if('transit-country'==document.form1.identify.value)
			{
				window.opener.document.Quotation.transit_country.value=countrys;
				window.opener.document.Quotation.transit_countryId.value=ids;
				window.opener.document.Quotation.totalTransitId.value=count;
			}else if('destination-country'==document.form1.identify.value)
			{
				window.opener.document.Quotation.destination_country.value=countrys;
				window.opener.document.Quotation.destination_countryId.value=ids;
				window.opener.document.Quotation.totalDestinationId.value=count;
			}
			//window.opener.Quotation.totalCountry.value=id;
			window.close();
		}
	} // chkProposalNo if
	else
	{
		alert("please close the country selection window and open again");
		window.close();
	}
}
</script>
</body>
                     
