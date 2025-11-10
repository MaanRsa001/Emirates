<% String reqfrom = request.getParameter("reqfrom")==null?"":request.getParameter("reqfrom"); %>
<div class="footer_body">
<table width="90%" align="center" cellpadding="0" cellspacing="0" >
	<!--<tr>
		<td valign="bottom" width="100%" valign="bottom" bgColor="#4f6180">&nbsp;
			<table width="100%">
				<tr>
					<td style="PADDING-RIGHT: 10px" bgColor=#4f6180>
						<a class=privacy
							href="http://insuranceuae.com/LinkClick.aspx?link=105&amp;tabid=105"><SPAN
							class=bottomtext id=dnn_dnnCOPYRIGHT_lblCopyright>Privacy
								Policy &amp; Terms of Use</SPAN> </A>
					</td>
					<td style="PADDING-RIGHT: 10px" align=right bgColor=#4f6180>
						<span class=bottomtext id=dnn_dnnCOPYRIGHT_lblCopyright>Al-Futtaim
							Group Co. LLC - Commercial Registration No. 207200 </SPAN>
					</td>
				</tr>
			</table
		</td>
	</tr>-->
	<%if("show".equalsIgnoreCase(reqfrom)){ %>
	<tr>
		<td align="center">Payment Gateway Screen</td>		
	</tr>
	<tr>
		<td align="center">&nbsp;</td>		
	</tr>
	<tr>
		<td align="center">&nbsp;</td>		
	</tr>
	<tr>
		<td align="center">&nbsp;</td>		
	</tr>
	<tr>
		<td align="center">&nbsp;</td>		
	</tr>
	<tr>
		<td align="center">&nbsp;</td>		
	</tr>
	<tr>
		<td align="center">
			<a href="#" onClick="javascript:window.close();">
		  		<img src="<%=request.getContextPath() %>/images/Back.jpg">
		  	</a>
		</td>		
	</tr>	
	<%} %>
</table>
</div>