<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<%@ page isELIgnored="false"%>
<html>
  <head>
  <script>
			$.fn.datebox.defaults.formatter = function(date){
				var y = date.getFullYear();
				var m = date.getMonth()+1;
				var d = date.getDate();
				return d+'/'+m+'/'+y;
			}
			
			$(function(){
				var index = '<s:property value="index"/>';
				var t = $('#tabs');
				var tabs = t.tabs('tabs');
					t.tabs('select', tabs[index].panel('options').title);
			});
			
  		</script>
  </head>
  <body>
  <table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#E5E5E5">
	<tr>
		<td bgcolor="#FFFFFF" style="padding:10px; background:#F8F8F8">
		<s:if test ="reqFrom==null or reqFrom == ''">
           <s:form id="exchange" name="exchange" method="post" action="exchangeAreport.action" theme="simple">
				 <table width="100%" border="0" cellspacing="0" cellpadding="0">
				 <tr><td></td></tr>
			   		<tr>
						<td class="heading"><s:text name="label.exchange.report" /></td>
					</tr>
  					<tr>	                                                 
  						<td class="bg"><table width="100%" border="0" align="center" cellpadding="4" cellspacing="0">
      						<tr>
      							<td width="20%"></td>
        						<td width="25%" align="right"><s:text name="policy.report.effectiveDate"/> <font color="red">*</font></td>
           						<td width="3%"></td>
        					    <td width="32%" align="left"><s:textfield name="effDate" id="effDate" cssClass="easyui-datebox"/></td>
            					<td width="20%"></td>
            				</tr>
  						   </table>
  						</td>
   					</tr>
					<tr>
	    				<td>
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td height="25" align="center" valign="middle">
										<input type="button" name="sub" value="Submit" onclick="fnsubmit(this.form,'result','')" class="btn" />
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
             </s:form>
   		</s:if>
	    <s:else>
	    <s:form id="exchange1" name="exchange1" method="post" action="exchangeAreport.action" theme="simple">
	    	<table cellspacing="2" cellpadding="2" border=0 align="center">
			  <tr>
				<td align="center">	
	            <display:table name="exchangeList" pagesize="15" uid="row" id="record" style="width:600px;" requestURI="exchangeResultAreport.action" export="true" >
				<display:setProperty name="paging.banner.one_item_found" value="" />
				<display:setProperty name="paging.banner.one_items_found" value="" />
				<display:setProperty name="paging.banner.all_items_found" value="" />
				<display:setProperty name="paging.banner.some_items_found" value="" />
				<display:setProperty name="paging.banner.placement" value="bottom" />
				<display:setProperty name="paging.banner.onepage" value="" />
				<display:setProperty name="basic.empty.showtable" value="true" />
				<display:setProperty name="paging.banner.no_items_found" value="" />
				<display:setProperty name="export.csv" value="false" />
				<display:setProperty name="export.xml" value="false" />
				<display:setProperty name="export.pdf" value="false" />
                <display:setProperty name="export.excel.filename" value="ExchangeRateReport.xls" />
                <display:setProperty name="export.pdf.filename" value="ExchangeRateReport.pdf" />
				<display:column style="text-align:center;" sortable="false" title="S.No" value="${record_rowNum}" class="formtxtc" headerClass="headerClass"/>
				<display:column style="text-align:left;" sortable="false" title="Currency Name" property="CURRENCY_NAME" class="formtxtc" headerClass="headerClass"/>
				<display:column sortable="false" style="text-align:left;" title="ROE" property="EXCHANGE_RATE" class="formtxtc" headerClass="headerClass"/>
				<display:column sortable="false" style="text-align:center;" title="Effective Date" property="EFFECTIVE_DATE" class="formtxtc" headerClass="headerClass"/>
				</display:table>
				</td>
			</tr>
			<tr>
   				<td>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="25" align="center" valign="middle">
								<input type="button" name="sub" value="Back" onclick="fnsubmit(this.form,'Back','')" class="btn" />
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<s:hidden name="effDate"/>
	</s:form>
   	</s:else>	
      </td>
    </tr>
 </table>
<script type="text/javascript">
function fnsubmit(frm,from,val)
{
if(from == 'result'){
document.exchange.action = "${pageContext.request.contextPath}/exchangeResultAreport.action?reqFrom="+from;
document.exchange.submit();
}else{
document.exchange1.action = "${pageContext.request.contextPath}/exchangeAreport.action";
document.exchange1.submit();
}
}
</script>
</body>
</html>
