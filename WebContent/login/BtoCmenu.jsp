<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML><HEAD><TITLE>Saudi United Cooperative Insurance Company</TITLE>
<%
	String btcPath = request.getContextPath();
	String[][] ProductDetailsbtc = new String[0][0];
	if(session.getAttribute("ProductDetails")!=null)
	{
		ProductDetailsbtc = (String[][])session.getAttribute("ProductDetails");
	}
			
	String[][] officebtc = new String[0][0];
	if(session.getAttribute("officeScheme")!=null)
	{
		officebtc = (String[][])session.getAttribute("officeScheme");
	}
	String pidbtc = request.getParameter("selectProd");
	String comFrombtc=request.getParameter("compFrom")!=null?request.getParameter("compFrom"):"";
	String officeSchemebtc = request.getParameter("officeScheme");
	if(officeSchemebtc == null)
		officeSchemebtc =  session.getAttribute("scheme_id")==null?"":(String) session.getAttribute("scheme_id");
	if(pidbtc == null&&comFrombtc.equalsIgnoreCase("compNew"))
		pidbtc=request.getParameter("spids");
	if(pidbtc == null)
   		pidbtc = session.getAttribute("product_id")==null?"":(String)session.getAttribute("product_id");
	
%>
<META http-equiv=Content-Type content="text/html; charset=iso-8859-1">
<META content="MSHTML 6.00.2800.1106" name=GENERATOR>
</HEAD>
<BODY  leftMargin=0 topMargin=0 MARGINHEIGHT="0" MARGINWIDTH="0">

<table width="90%" border="0" bgColor="#efefef" cellspacing="0" cellpadding="0" align="center" >
 	<tr>
    	<td bgColor="#efefef">&nbsp;</td>
    	<td bgColor="#efefef">&nbsp;</td>
    	<td width="40%" align="right">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" bgColor="#efefef">
				<tr>
					<td align="center" class="text"><a class="buttonsMenu3" href="#" nowrap>WHY CHOOSE US</a></td>
					<td align="center" class="buttonsMenu3">|</td>
					<td align="center" class="text"><a class="buttonsMenu3" href="<%=btcPath%>/login/ProductSelection.jsp" nowrap>HOME</a></td>
					<td align="center" class="buttonsMenu3">|</td>
					<td align="center" class="text"><a class="buttonsMenu3" href="#" nowrap>FAQs</a></td>
					<td align="center" class="buttonsMenu3">|</td>
					<td align="center" class="text"><a class="buttonsMenu3" href="<%=btcPath%>/FooterContactUs.jsp" nowrap>CONTACT US</a></td>
  				</tr>
			</table>
		</td>		
	</tr>
 	<tr align="center">
    	<td colspan="3">
    		<table width="90%" bgColor="#efefef" border="0" cellspacing="0" cellpadding="0" align="center">
      			<tr>
	  				<td colspan="3">
	  					<table width="100%"  border="0" cellspacing="0" cellpadding="0" align="center">
       						<tr>
      							<td width="20">&nbsp;</td>
						        <td width="1003" height="69" valign="top">
								 	<img src="<%=btcPath%>/images/logo.jpg">
								</td>
								<td align="right" width="78" height="58" >
										<% if(!pidbtc.equalsIgnoreCase("30") && ProductDetailsbtc.length>0){
										for(int p=0; p<ProductDetailsbtc.length; p++) {
										if(pidbtc.equalsIgnoreCase(ProductDetailsbtc[p][0])){
										%>
											<img width="78" height="58" style="cursor: pointer;" src="<%=request.getContextPath() %>/images/<%=ProductDetailsbtc[p][1] %>.gif" alt="<%=ProductDetailsbtc[p][1]%>" />
										<%}}}else if(officebtc.length>0){ 
											for(int o=0; o<officebtc.length; o++){
											if(officeSchemebtc.equalsIgnoreCase(officebtc[o][0])){
										%>
										<img width="78" height="58" style="cursor: pointer;" src="<%=request.getContextPath() %>/images/<%=officebtc[o][1] %>.gif" alt="<%=officebtc[o][1]%>"/>
										<%}}}%>
								</td>
	    					</tr>
						</table>
						<table width="100%" align="center" height="100%" border="0" cellpadding="0" cellspacing="0" bgColor=#efefef>
							<tr>			
								<td width="20">&nbsp;</td>		
								<td width="250">&nbsp;</td>		
								<td>
									<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
										<tr>		
											<td width="100">&nbsp;</td>		
											<td width="100">&nbsp;</td>
											<td align="right">&nbsp;</td>
											<td width="20">&nbsp;</td>		
										</tr>		
									</table>
								</td>		
							</tr>		
							<tr>
								<td width="20">&nbsp;</td>
								<td width="250">&nbsp;</td>
								<td>&nbsp;</td>
							</tr>
						</table>
					</td>
    			</tr>
			</table>
		</td>
	</tr>	
</table>

<table width="90%" border="0" cellspacing="0" cellpadding="0" align="center" >
	<tr>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td valign="middle" id="product" class="buttonsMenuBlue" width="13%">
			<a href="<%=btcPath%>/login/ProductSelection.jsp" onmousemove='change_class("product");' onmouseout='RevertClass("product");'>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>PRODUCTS</b>
			</a>
		</td>
		<td width="1%">&nbsp;</td>
		<td valign="middle" id="retrive" class="buttonsMenuBlue" width="13%">
			<a href="<%=btcPath%>/CopyQuote/BtoCSearchQuote.jsp" onmousemove='change_class("retrive");' onmouseout='RevertClass("retrive");'>
			&nbsp;<b>RETRIEVE QUOTE</b>
			</a>
		</td>
		<td width="1%">&nbsp;</td>
		<td valign="middle" id="policy" class="buttonsMenuBlue" width="13%">
			<a href="<%=btcPath%>/CopyQuote/BtoCSearchPolicy.jsp" onmousemove='change_class("policy");' onmouseout='RevertClass("policy");'>
			&nbsp;<b>RETRIEVE POLICY</b>
			</a>
		</td>
		<td width="1%">&nbsp;</td>
		<td valign="middle" id="policy" width="13%">
		<%--	<a href="<%=btcPath%>/userCreation/adminCreateUser.jsp?editbroker=<%=(String)session.getAttribute("user")%>&mode=edit&userType=3" onmousemove='change_class("policy");' onmouseout='RevertClass("policy");'>
			&nbsp;<b>LOGIN CRATION</b>
			</a> --%>
		</td>
		<%if("M".equalsIgnoreCase((String)session.getAttribute("typeOfProduct"))){ %>
		<td width="1%">&nbsp;</td>
		<td valign="middle" id="renew" width="13%">
		<%--	<a href="<%=btcPath%>/CopyQuote/BtoCSearchPolicy.jsp?reqFrom=renew" onmousemove='change_class("renew");' onmouseout='RevertClass("renew");'>
			&nbsp;<b>RENEW POLICY</b>
			</a> --%>
		</td>
		<td width="31%">&nbsp;</td>
		<%}else{ %>
		<td width="44%">&nbsp;</td>
		<%} %>		
	</tr>
</table>

<script>
function change_class(val) { 
document.getElementById(val).className='buttonsMenuBlueMOvar';
}

function RevertClass(val)
{
	document.getElementById(val).className='buttonsMenuBlue';
}
</script>
