<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<link href="css/table-design.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/highcharts.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/exporting.js"></script>
		<script language="JavaScript">
			function datealert(dtdiff,flag) {
				if(flag==0) {
					if(dtdiff>=-10 & dtdiff<=-1) {
					 alert("You Must Change Your Password After  "+(Math.abs(dtdiff)-1)+" Days");;
					}
				}
			}
		</script>
		<style type="text/css">			
			.ui-datepicker-trigger {
				width: 10%;
				float: right;
			}
			.sblue {
				width: 30px;
				height: 10px;
				background: #2f7ed8;
			}
			.sblack {
				width: 10px;
				height: 10px;
				background: #0d233a;
			}
			.sgreen {
				width: 10px;
				height: 10px;
				background: #8bbc21;
			}
			.sorange{
				width: 10px;
				height: 10px;
				background: #910000;
			}			
			.svilot {
				width: 10px;
				height: 10px;
				background: #1aadce;
			}
			.spink {
				width: 10px;
				height: 10px;
				background: #492970;
			}
		</style>
	</head>
	<%
	int flag=0;
	
	String dtdiff=request.getParameter("dtdiff")==null?"":request.getParameter("dtdiff");
	if(session.getAttribute("collections")!=null)
		session.removeAttribute("collections");
	%>
	<body onLoad="datealert('<%=dtdiff%>','<%=flag%>')">
		<s:form name="form1" method="post" action="">
			<table width="90%" border="0" cellspacing="0" cellpadding="0" align="center">
				<tr>
				    <s:iterator value="dashBoard"></s:iterator>
       				<td align="center" valign="top"  bgcolor="#FFFFFF">
       				 	<table width="100%"  border="0" cellspacing="0" cellpadding="0">
       				 		<tr><td colspan="5" height="50px">&nbsp;</td></tr>
       				 		<tr>
			       				<td width="45%">
									&nbsp;&nbsp;<font color="#156BAB"><b></b></font>
									<div >							 
										<table border="0" cellspacing="0" cellpadding="0" 
											width="100%">
											<tr>
												<%--<td colspan="3">Portfolio</td> --%>
												<td colspan="3" align="center">
													<script type="text/javascript">
													$(function () {
													    $('#portfolioChart').highcharts({
													        chart: {
													        	plotBackgroundColor: '#FFFFFF',
													            margin: [0, 0, 0, 0],
													            plotShadow: false
													        },
													        title: {
													            text: 'portfolio',
													            align: 'center',
													            verticalAlign: 'middle',
													            y: 50
													        },
													        tooltip: {
													            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
													        },
													        exporting: {
																enabled: false
															},
															credits: {
													            enabled: false
													        },
													        plotOptions: {
													            pie: {
													                dataLabels: {
													                    enabled: true,
													                    distance: -50,
													                    style: {
													                        fontWeight: 'bold',
													                        color: 'white',
													                        textShadow: '0px 1px 2px black'
													                    }
													                },
													                startAngle: -90,
													                endAngle: 90,
													                center: ['50%', '75%']
													            }
													        },
													        series: [{
													            type: 'pie',
													            name: 'portfolio',
													            innerSize: '50%',
													            data: [
													                {
													                    name: 'One Off Policies',
													                    y: <s:property value="oneOffPortFolio"/>,
													                    dataLabels: {
													                        enabled: false
													                    }
													                },
													                {
													                    name: 'Open Cover Policies',
													                    y: <s:property value="openCoverPortFolio"/>,
													                    dataLabels: {
													                        enabled: false
													                    }
													                }
													            ]
													        }]
													    });
												    });
													</script>
													<div id="portfolioChart" align="center" style="width: 100%; height: 200px;"></div>
												</td>
											</tr>	
											<tr>
											   <td>
											      <table width="100%" border="1" cellspacing="2" cellpadding="5" style="border-collapse: collapse;">
											         <tr>
														<td><b>Product</b></td>
														<td><b>No.of Policies</b></td>
														<td><b>Premium</b></td>
													</tr>
													<tr>
														<td>One Off</td>
														<td><span class="sblue">&nbsp;&nbsp;&nbsp;&nbsp;</span>&nbsp;&nbsp;&nbsp;<s:property value="oneOffPortFolio"/>  </td>
														<td><s:property value="oneOffPremium"/></td>
													</tr>
													<tr>
														<td>Open Cover</td>
														<td><span class="sblack">&nbsp;&nbsp;&nbsp;&nbsp;</span>&nbsp;&nbsp;&nbsp;<s:property value="openCoverPortFolio"/>  </td>
														<td><s:property value="openCoverPremium"/></td>
													</tr>
													      </table>
													   </td>
													</tr>
										</table>							
									</div>
								</td>
								<td width="10%">&nbsp;</td>
								<td width="45%">
									&nbsp;&nbsp;<font color="#156BAB"><b></b></font>
									<div >							 
										<table border="0" cellspacing="0" cellpadding="0" width="100%">
											<tr>
												<%--<td colspan="4">Referral</td> --%>
												<td colspan="4" align="center">
													<script type="text/javascript">
													$(function () {
													    $('#referralChart').highcharts({
													        chart: {
													        	plotBackgroundColor: '#FFFFFF',
													            margin: [0, 0, 0, 0],
													            plotShadow: false
													        },
													        title: {
													            text: 'Referral',
													            align: 'center',
													            verticalAlign: 'middle',
													            y: 50
													        },
													        tooltip: {
													            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
													        },
													        exporting: {
																enabled: false
															},
															credits: {
													            enabled: false
													        },
													        plotOptions: {
													            pie: {
													                dataLabels: {
													                    enabled: true,
													                    distance: -50,
													                    style: {
													                        fontWeight: 'bold',
													                        color: 'white',
													                        textShadow: '0px 1px 2px black'
													                    }
													                },
													                startAngle: -90,
													                endAngle: 90,
													                center: ['50%', '75%']
													            }
													        },
													        series: [{
													            type: 'pie',
													            name: 'Referral',
													            innerSize: '50%',
													            data: [
													                {
													                    name: 'One Off Pending',
													                    y: <s:property value="oneOffPending"/>,
													                    dataLabels: {
													                        enabled: false
													                    }
													                },
													                {
													                    name: 'One Off Accepted',
													                    y: <s:property value="oneOffAccepted"/>,
													                    dataLabels: {
													                        enabled: false
													                    }
													                },
													                {
													                    name: 'One Off Rejected',
													                    y: <s:property value="oneOffRejected"/>,
													                    dataLabels: {
													                        enabled: false
													                    }
													                },
													                {
													                    name: 'Open Cover Pending',
													                    y: <s:property value="openCoverPending"/>,
													                    dataLabels: {
													                        enabled: false
													                    }
													                },
													                {
													                    name: 'Open Cover Accepted',
													                    y: <s:property value="openCoverAccepted"/>,
													                    dataLabels: {
													                        enabled: false
													                    }
													                },
													                {
													                    name: 'Open Cover Rejected',
													                    y: <s:property value="openCoverRejected"/>,
													                    dataLabels: {
													                        enabled: false
													                    }
													                }
													            ]
													        }]
													    });
												    });
													</script>
													<div id="referralChart" align="center" style="width: 100%; height: 200px;"></div>
												</td>
											</tr>	
											<tr>
											    <td>
											       <table width="100%" border="1" cellspacing="2" cellpadding="5" style="border-collapse: collapse;">
											         <tr>
														<td><b>Product</b></td>
														<td><b>Pending</b></td>
														<td><b>Accepted</b></td>
													    <td><b>Rejected</b></td>
													</tr>
													<tr>
														<td>One Off</td>
														<td><span class="sblue">&nbsp;&nbsp;&nbsp;&nbsp;</span>&nbsp;&nbsp;&nbsp;<s:property value="oneOffPending"/></td>
														<td><span class="sblack">&nbsp;&nbsp;&nbsp;&nbsp;</span>&nbsp;&nbsp;&nbsp;<s:property value="oneOffAccepted"/></td>
													    <td><span class="sgreen">&nbsp;&nbsp;&nbsp;&nbsp;</span>&nbsp;&nbsp;&nbsp;<s:property value="oneOffRejected"/></td>
													</tr>
													<tr>
														<td>Open Cover</td>
														<td><span class="sorange">&nbsp;&nbsp;&nbsp;&nbsp;</span>&nbsp;&nbsp;&nbsp;<s:property value="openCoverPending"/></td>
														<td><span class="svilot">&nbsp;&nbsp;&nbsp;&nbsp;</span>&nbsp;&nbsp;&nbsp;<s:property value="openCoverAccepted"/></td>
													    <td><span class="spink">&nbsp;&nbsp;&nbsp;&nbsp;</span>&nbsp;&nbsp;&nbsp;<s:property value="openCoverRejected"/></td>
													</tr>
												  </table>
											    </td>
											</tr>				
										</table>							
									</div>
								</td>
							</tr>
			            </table>
            		</td>
          		</tr>
          	</table>
        </s:form>
	</body>
</html>