<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/struts-jquery-tags" prefix="sj"%>
<%@ taglib uri="/WEB-INF/displaytag.tld" prefix="display"%>
<%@ page isELIgnored="false"%>
<%@ include file="../login/home1.jsp" %>
<%@ include file="sessionsCheckNormal.jsp"%>
<% 
	if(session.getAttribute("language") != null)
        session.removeAttribute("language");
	if(session.getAttribute("freight")!=null)
		session.removeAttribute("freight");
	if(session.getAttribute("freightBroker")!=null)
		session.removeAttribute("freightBroker");		
	if(session.getAttribute("product_id")!=null)
		session.removeAttribute("product_id");
	if(session.getAttribute("typeOfProduct")!=null)
		session.removeAttribute("typeOfProduct");
	if(session.getAttribute("quoteNo")!=null)
		session.removeAttribute("quoteNo");
	if(session.getAttribute("openCoverNo") !=null)
		session.removeAttribute("openCoverNo");
	if(session.getAttribute("brokerCode") !=null) {
		session.removeAttribute("brokerCode");
	}
%>
<jsp:useBean id= "product" class = "com.maan.product.ProductSelection">
<jsp:setProperty name= "product" property = "*"/>
<jsp:setProperty name="product" property="out" value="<%= response.getWriter() %>" /> 
</jsp:useBean>
<jsp:useBean id= "freightProduct" class = "com.maan.broker.DAO.FreightCreationBean">
</jsp:useBean>
<jsp:useBean id= "customer" class = "com.maan.broker.DAO.CustomerCreationBean">
</jsp:useBean>
<%
	   String path = request.getContextPath();
%>
<html>
  <head>
  		<sj:head jqueryui="true" jquerytheme="start" />
  		<style type="text/css">
			.card {

	            padding: 12px 20px 14px 20px;
	            cursor: pointer;
	            border: 0px !important;
	            min-height: 150px;
	            max-height: 100%;
	            
	            box-shadow: 0 0 10px #1ea55d;
	        }
	
	        .Card_Parent {
	            border-radius: 4px;
	            background: #fff;
	            /* box-shadow: 0 5px 10px rgba(154, 160, 185, 0.05),
	                0 15px 40px rgba(166, 173, 201, 0.2);
	            ; */
	            box-shadow: 0 6px 10px rgba(0, 0, 0, .08), 0 0 6px rgba(0, 0, 0, .05);
	            transition: .3s transform cubic-bezier(.155, 1.105, .295, 1.12), .3s box-shadow, .3s -webkit-transform cubic-bezier(.155, 1.105, .295, 1.12);
	        }
	
	        .Card_Parent:hover {
	            transform: scale(1.02);
	            box-shadow: 0 10px 20px rgba(0, 0, 0, .12), 0 4px 8px rgba(0, 0, 0, .06);
	        }
	        
	         .card-1 {
	            background-repeat: no-repeat;
	            background-position: bottom;
	            background-size: 110px;
	        }

		</style>
  </head>
  <body>
	<% 
		String s1=""+session.getAttribute("mode"); 
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		String loginCode	=	(String)session.getAttribute("user");
		product.setLoginCode(loginCode);
		
		String[][] productdetails=new String[0][0];
		String prodIdentifier="";
		String dtdiff="";
	//Royal World WOrk
	HashMap brokerDetails = new HashMap();
	String brokerBra = "";
	String actualBranch = "";
	Map branchDt = product.getBrokerUserBranch(loginCode);
	if(branchDt.size()>0){
		brokerBra = (String)branchDt.get("defaultBranch");
		actualBranch = (String)branchDt.get("actualBranch");
	}
	brokerDetails = product.getBrokerUserDetails(brokerBra);
	session.setAttribute("LoginBranchCode",brokerBra);//Default Branch Code
	session.setAttribute("adminBranch",actualBranch);//Actual Branch Code
	session.setAttribute("BrokerDetails",brokerDetails);
	session.setAttribute("LANG",brokerDetails.get("LANG"));
	//For RSA Issure
	boolean statusRSAIssuer = product.getStatusRSAIssuer(loginCode);
		if(statusRSAIssuer)
		{session.setAttribute("RSAISSUER", (String) session.getAttribute("user"));
	%>
	<%
			
		}
	
	String rsaIssuer = (String)session.getAttribute("RSAISSUER");
	//productdetails=product.getProductDetailsModified(loginCode,brokerBra,rsaIssuer);
	if(rsaIssuer==null) 
		productdetails=product.getProductDetailsModified(loginCode,brokerBra,rsaIssuer); 
	else
		productdetails=product.getProductDetailsModified(rsaIssuer,brokerBra,rsaIssuer); 
	
	
	if(productdetails.length>0)
	{
		session.setAttribute("ProductDetails",productdetails);
	}
	%>
   <s:form id="" name="ChooseProduct" method="post" action="" theme="simple">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<div class="row">
    				<div class="col-md-12">
    					<h4><span class="glyphicon glyphicon-th-list"></span>&nbsp;&nbsp;<s:text name="Product Selection" /></h4>
    				</div>
    			</div>
			</div>
			<div class="panel-body">
		        <div class="row">										
						<%
							String freight="";
							if(productdetails.length>0) {
								String userType = freightProduct.getUserType(loginCode);
								HashMap cusOpt = new HashMap();
								String QuoteOpt="";
								//if(userType.equalsIgnoreCase("Customer"))
								//{
									String [][] cusRights = new String[0][0];
									cusRights = customer.getCustomerRights(loginCode);
									for(int c=0;c<cusRights.length;c++) {
										String cusPid = cusRights[c][0]!=null?cusRights[c][0]:"";
										String cusQuote = cusRights[c][1]!=null?cusRights[c][1]:"";
										String cusSchedule = cusRights[c][2]!=null?cusRights[c][2]:"";
										//String cusDebit = cusRights[c][3]!=null?cusRights[c][3]:"";
										String cusDebit = cusRights[c][7]!=null?cusRights[c][7]:"";
										String cusCusDebit = cusRights[c][4]!=null?cusRights[c][4]:"";
										String policyOpt = cusRights[c][5]!=null?cusRights[c][5]:"";
										String certificateOpt = cusRights[c][6]!=null?cusRights[c][6]:"";
										cusOpt.put("cusQuote"+cusPid,cusQuote);
										cusOpt.put("cusSchedule"+cusPid,cusSchedule);
										cusOpt.put("cusDebit"+cusPid,cusDebit);
										cusOpt.put("cusCusDebit"+cusPid,cusCusDebit);
										cusOpt.put("policyOpt"+cusPid,policyOpt);
										cusOpt.put("certificateOpt"+cusPid,certificateOpt);
									}
									session.setAttribute("cusOptions",cusOpt);
								//}
								if(customer.getIsBrokerHasDC(loginCode)) {
									session.setAttribute("CDMenu","Yes");
								}
							String filePath="";
							String alternative="row1";
							int t=0;
							
							for(int i=0;i<productdetails.length;i++)
							{
								QuoteOpt = (String)cusOpt.get("cusQuote"+productdetails[i][0]);
								QuoteOpt = QuoteOpt!=null?QuoteOpt:"";
								
								//For Home  Common - Rajesh
							
								if(!productdetails[i][0].equals("")) {	
					%>
		            <div class="col-lg-4 col-md-12 col-sm-12 col-12">
		                <div class="Card_Parent">
		                    <div class="card card-1">
		                        <h3><%=productdetails[i][1]%></h3>
		                        <div class="row">
		                            <div class="col-md-6 col-6 col-sm-6">
		                                <a class="btn btn-success btn-oval" href="#" onClick="getPro('<%=productdetails[i][0]%>','NEW','<%=dtdiff%>','','<%=QuoteOpt%>','','','')">Get Quote&nbsp;<span class="glyphicon glyphicon-hand-right"></span></a>
		                            </div>
		                        </div>
		                    </div>
		                </div>
		            </div>
					<%
								}
								if(alternative.equals("row1")){
								alternative ="row2";   
								} else {
									alternative ="row1";
								}
							}
					%>
					<%
						}else {
					%>
						Sorry! No Products Available
					<%}%>			
	         	</div>
      		</div>
     	</div>
	<input type="hidden" name="selectProd" >
	<input type="hidden" name="QuoteOpt" >
	<input type="hidden" name="contenTypeId" >
	<input type="hidden" name="DisplayValue">
	</s:form>
<script type="text/javascript">
function getPro(pid,mod,dtdiff,homepid,qopt,tpid,officeScheme,OFSName)
{	
		
		if(pid=='3')
		{
			document.ChooseProduct.selectProd.value=pid;
			
			if(qopt=='NONE'||qopt=='None')
				document.ChooseProduct.action ="../final.jsp";
			else
				document.ChooseProduct.action ="<%=request.getContextPath()%>/initReport.action?menuType=CHART&reqFrom=PS&productId="+pid;
			document.ChooseProduct.submit();
		}else if(pid=='11'){
			document.ChooseProduct.selectProd.value=pid;
			document.ChooseProduct.QuoteOpt.value=qopt;
			document.ChooseProduct.action ="<%=request.getContextPath()%>/ViewOpenCovers.jsp";
			document.ChooseProduct.submit();
		}
		return false;
}
function singlePro(pid)
{
	document.ChooseProduct.selectProd.value=pid;
	document.ChooseProduct.action ="../CustomerInfo/CustomerInfo.jsp";
	document.ChooseProduct.submit();
}
</script>
</body>
</html>