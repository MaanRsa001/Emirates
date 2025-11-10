<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>Deposit Master</title>
		<link href="css/styles.css" rel="stylesheet" type="text/css" />
		<link href="css/tcal.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="js/tcal.js"></script>
		<script type="text/javascript" src="js/common.js"></script>
		<script language="JavaScript">javascript:window.history.forward(1);</script>
		<script language="JavaScript">
			function stopRKey(evt) { 
			  var evt = (evt) ? evt : ((event) ? event : null); 
			  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
			  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
			} 
			document.onkeypress = stopRKey;
			
		 	function forward(val){
		 		document.deposit.action=val;
		 		document.deposit.submit();
		 	}
		</script>
	</head>
	<body>
		<table width="900" border="0" align="center" cellpadding="0" cellspacing="0"> 
  			<tr><td height="5"></td></tr>
  			<tr>
    			<td><table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#E5E5E5">
     					<tr>
        					<td bgcolor="#FFFFFF" style="padding:10px; background:#F8F8F8">
        						<s:form id="deposit" name="deposit" method="post" action="" theme="simple">
            						<table width="100%" border="0" cellspacing="0" cellpadding="0">
           								<tr><td  style="color:red;"><s:actionerror/></td></tr>
							            <tr>
							                <td bgcolor="#FFFFFF"><table width="100%" border="0" cellspacing="0" cellpadding="0">
								            	<tr><td class="heading"><s:text name="label.cheque.details" /></td></tr>
								            	<tr><td></td></tr>
								            	<tr>
           											<td><table width="100%">
           													<s:iterator value="cbcList">
           														<tr><td></td></tr>
	           													<tr>
	           														<td width="3%"></td>
	           														<td width="15%" align="right"><s:text name="label.cbc.no"/></td><td width="15%" align="left"><b> : ${cbcNo}</b></td>
	           														<td width="15%" align="right"><s:text name="label.broker.code"/></td><td width="15%" align="left"><b> : ${brokerName}</b></td>
	           														<td width="15%" align="right"><s:text name="label.deposit.type"/></td><td width="15%" align="left"><b> : ${depositType}</b></td>
	           														<td width="7%"></td>
	           													</tr>
	           													<tr>
	           														<td width="3%"></td>
	           														<td width="15%" align="right"><s:text name="label.customer.option"/></td><td width="15%" align="left"><b> : ${customerOpt}</b></td>
	           														<td width="15%" align="right"><s:text name="label.product.id"/></td><td width="15%" align="left"><b> : ${productName}</b></td>
	           														<td width="15%" align="right">&nbsp;</td><td width="15%" align="right">&nbsp;</td>
	           														<td width="7%"></td>
	           													</tr>
	           													<tr><td></td></tr>
           													</s:iterator>
           											</table></td>
           										</tr>
	                    						<tr>	                                                 
	                     							<td class="bg"><table width="100%" border="0" align="center" cellpadding="4" cellspacing="0">
	                     							
														<jsp:scriptlet>
														     org.displaytag.decorator.TotalTableDecorator totals = new org.displaytag.decorator.TotalTableDecorator();
														     pageContext.setAttribute("totals", totals);
														</jsp:scriptlet>
			                          					<display:table name="detailList" pagesize="20" requestURI="depositDetailDeposit.action" class="table" uid="row" id="record"  export="true" varTotals="rajaTotal">
										                    <display:setProperty name="paging.banner.one_item_found" value="" />
										                    <display:setProperty name="paging.banner.one_items_found" value="" />
										                    <display:setProperty name="paging.banner.all_items_found" value="" />
										                    <display:setProperty name="paging.banner.some_items_found" value="" />
										                    <display:setProperty name="paging.banner.placement"	value="bottom" />
										                    <display:setProperty name="paging.banner.onepage" value="" />
										                    <display:setProperty name="export.types" value="excel pdf"/>
															<display:setProperty name="export.pdf" value="true" />
															<display:setProperty name="export.csv" value="false" />
															<display:setProperty name="export.xml" value="false" />
															<display:setProperty name="export.pdf.filename" value="reports.pdf" />
															<display:setProperty name="export.excel.filename" value="reports.xls" />
															
										                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="Deposit No" property="DEPOSIT_NO" />
										                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="Product Name" property="PRODUCT_NAME" />
										                    <display:column sortable="true" style="text-align:left;font-size:13px;" title="Policy No" property="POLICY_NO" />
										                   <display:column sortable="true" style="text-align:left;font-size:13px;" title="Customer Name" property="CUST_NAME" />
										                    <display:column sortable="true" style="text-align:right;font-size:13px;" title="Premium Amount" property="PREMIUM_AMOUNT"  format="{0,number,0.00}" total="true" />/>
										                    <display:column sortable="true" style="text-align:center;font-size:13px;" title="Policy Issue Date" property="ENTRY_DATE" />
								                        
									                        <display:footer>
									                        	<tr><td colspan="3"></td>
										                        	<td style="text-align:right;font-size:13px;"><b>Total Premium</b></td>
																    <td style="text-align:right;font-size:13px;"><b>${rajaTotal.column5}</b></td>
																    <td></td>
																</tr>
															</display:footer>
								                        </display:table>
                    								</table></td>
                    							</tr>
              								</table></td>
              							</tr>
              							<tr>
               								<td><table align="center">
               									<tr>
                        							<td align="center">
			                            				<input type="button" name="back" value="Back" class="btn" onclick="forward('chequeDetailsDeposit')"/>&nbsp;&nbsp;&nbsp;&nbsp;
			                           				</td>
			                        			</tr>
           									</table></td>
         								</tr>
            						</table>
            						<s:hidden name="cbcNo"/>
        						</s:form>
        					</td>
      					</tr>
    			</table></td>
  			</tr>
  			<tr><td>&nbsp;</td></tr>
		</table>
	</body>
</html>
