<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
		 String path = request.getContextPath(); 
		String LognCheck = request.getParameter("error");
			LognCheck = LognCheck==null?"":LognCheck;
        if(session.getAttribute("ClaimNumber") != null)
             session.removeAttribute("ClaimNumber");
	    if(session.getAttribute("claimpolicy_no") != null)
	         session.removeAttribute("claimpolicy_no");
	    if(session.getAttribute("userLoginMode")!= null)
	         session.removeAttribute("userLoginMode"); 
	    if(session.getAttribute("frompath") != null)
	         session.removeValue("frompath");     
   
	try
   {
        if(session.getAttribute("user") !=null)
			session.removeAttribute("user");
        if(session.getAttribute("dtdiff") !=null)
			session.removeAttribute("dtdiff");
        if(session.getAttribute("user1") !=null)
			session.removeAttribute("user1");
        
        if(session.getAttribute("quote_no") !=null)
			session.removeAttribute("quote_no");
        
        if(session.getAttribute("admin") !=null)
			session.removeAttribute("admin");
        
        if(session.getAttribute("broker") !=null)
			session.removeAttribute("broker");
        
        if(session.getAttribute("product_id") !=null)
			session.removeAttribute("product_id");
        
        if(session.getAttribute("applicationNo") !=null)
			session.removeAttribute("applicationNo");
        
         if(session.getAttribute("QuoteNo") !=null)
			session.removeAttribute("QuoteNo");
			
		if(session.getAttribute("adminBranch") !=null)
			session.removeAttribute("adminBranch");
		if(session.getAttribute("AdminBranchCode") !=null)
			session.removeAttribute("AdminBranchCode");
		if(session.getAttribute("LoginBranchCode") !=null)
			session.removeAttribute("LoginBranchCode");
		if(session.getAttribute("BrokerDetails") !=null)
			session.removeAttribute("BrokerDetails");
        
   }catch(Exception e){System.out.println("RoyalTest Exception in Login page"+e.toString());e.printStackTrace();}
	
	
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0); 
%>


<%@ page import = "java.io.*,java.net.*, java.util.*, java.sql.*" buffer="100kb"%>
<%@ page import = "javax.servlet.http.HttpServlet.*, javax.servlet.ServletContext.*" %>
<%@ page import = "proj.date.DateFunction" %>



<jsp:useBean id="logSession" class="com.maan.session.LoginSession" >
	<jsp:setProperty name="logSession" property="*" />
</jsp:useBean>

<jsp:useBean id="log_lock" class="com.maan.admin.admLoginNew" >
	<jsp:setProperty name="log_lock" property="*" />
</jsp:useBean>

<jsp:useBean id="sestrack" class="com.maan.session.LoginSession" >
	<jsp:setProperty name="sestrack" property="*" />
</jsp:useBean>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>::Emirates::</title>

<script language="JavaScript" type="text/javascript">
function change_class(val) { 
document.getElementById(val).className='buttonsMenuBlueMOvar';
}

function RevertClass(val)
{
	document.getElementById(val).className='buttonsMenuBlue';
}

function removeLock(count)
{
	var countMe=count;
	if(countMe==1)
	{
<%
logSession.updateLoginStatus((String) session.getAttribute("user")==null?
request.getParameter("user"):"","loggedout",(String) session.getAttribute("ses"));
%>
	document.form1.submit();
	}
	
}

function  func()
{        
	document.form1.user.focus();
	if(document.form1.loger1.value == 4)
	{
		   alert ('Please give correct Login. This is the last chance beforelocking the account.');
	}
}
function method()
{
	document.form1.action="<%=path%>/ClaimNotification/ClaimNotificationEntry.jsp";
	document.form1.submit();
}

function FocusFunction()
{
		document.form1.user.focus();
}

function callSubmit()
{
	document.form1.action="<%=path%>/login/login.jsp";
	document.form1.submit();
}
    
<!-- Disable
function disableselect(e){
return false
}

function reEnable(){
return true
}

//if IE4+
document.onselectstart=new Function ("return false")
document.oncontextmenu=new Function ("return false")
//if NS6
if (window.sidebar){
document.onmousedown=disableselect
document.onclick=reEnable
}
//-->
</script>

    <link href="<%=path%>/App_Themes/Standard/css-content.css" rel="stylesheet" type="text/css"
        media="interactive, braille, emboss, handheld, projection, screen, tty, tv" />
    <link rel="shortcut icon" href="<%=path%>/images/favicon.ico" />
    <style type="text/css">
    .input_Upper
{
	text-transform:uppercase;
	}
    </style>
</head>
<body>
    <form name="form1" method="post" action="" >
        <!--

TOP INFORMTION

-->
  		<div id="top-information" style="">
        </div>
        <div id="top-information" style="right: 0px; top: 0px; text-align: right">
            <div id="logo" style="left: 18px; top: 0.2em">
                <img src="<%=path%>/images/logo.jpg"  />
            </div>
            </div>
             <div id="top-information" style="">
             </div>
        <!--

TOP NAV

-->
        <div class="none">
            &nbsp;</div>
        <div id="nav-main" style="right: 0px; top: 0px">
            <table border="0" cellpadding="0" cellspacing="0" style="width: 33%; text-align: center">
                <tr>
                    <td style="width: 100px; height: 16px;">
                        <a id="HyperLink2" style="color:LightGray" href="~/Default.aspx">Home</a>
                    </td>
                    <td style="width: 100px; height: 16px;width: 200px">
                    	<a id="HyperLink2" style="color:LightGray" href="~/Default.aspx" >About Emirates</a>
                    </td>
                    <td style="width: 100px; height: 16px;">
                    	<a id="HyperLink2" style="color:LightGray" href="~/Default.aspx">Products</a>
                    </td>
                </tr>
            </table>
            <div style="right: 0px">
                &nbsp;</div>
        </div>
      <%--  <div id="poster-photo-container" style="right: 0px; top: 0px">
            &nbsp;<object id="Object2" align="middle" classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000"
                codebase="https://fpdownload.macromedia.com/pub/&#13;&#10;shockwave/cabs/flash/swflash.cab#version=7,0,0,0"
                height="180" style="text-align: left; width: 566px;"><param name="movie" value="<%=path%>/images/header.swf"><param
                    name="quality" value="high">
                <embed align="middle" bgcolor="#ffffff" height="300" name="final" pluginspage="https://www.macromedia.com/go/getflashplayer"
                    quality="high" src="http://www.Emirates.ae/en/index.aspx" type="application/x-shockwave-flash"
                    width="711"></embed>
            </object>
            <div id="feature-area-home" style="text-align: center">
                <asp:label id="Label1" runat="server" text="The RELIABLE Insurer" width="114px"></asp:label>
                <br />
            </div>
        </div> --%>
        <!--

CONTENT CONTAINER

-->
        <div id="content-container-two-column" style="right: 0px; top: 0px">
            <!--

CONTENT MAIN COLUMN

-->
            <div id="content-main-two-column" style="height: 100%">
                <h1>
                    CARGO <span style="font-size: 10pt">-</span> <span style="font-size: 10pt">Online Insurance</span></h1>
                <p>
                    <span style="color: background">Company Info:</span> Emirates Company Limited is a public shareholding company incorporated in Middle East, 
                    India. &nbsp; »
                    <asp:hyperlink id="HyperLink1" runat="server" forecolor="Firebrick" navigateurl="www.Emirates.ae">Read More</asp:hyperlink>
                    &nbsp;</p>
                <p style="background-position: center center; background-image: url(../<%=path%>/images/logo-lon_3.JPG);
                    background-repeat: no-repeat">
                    <table border="0" cellpadding="0" cellspacing="0" style="width: 81%">
                        <tr>
                            <td style="width: 164px; vertical-align: top;">
                                <div id="three-column-side1" style="width: 99%">
                                    <img src="<%=path%>/images/User_Management6.JPG" class="photo-border" style="width: 147px;
                                        height: 117px; background-color: #ffffff;" />
                                    <h2>
                                        Cargo Insurance</h2>
                                    <p>
                                        Best tool for Customer / Company/ Broker to&nbsp; prepare online policy, print certificate,
                                        rapid online enquiry.</p>
                                </div>
                            </td>
                            <td style="vertical-align: top; font-family: Verdana;">
                                <table style="border-right: darkgray 1px dashed; padding-right: 0px; background-position: left center;
                                    border-top: darkgray 1px dashed; padding-left: 0px; background-attachment: fixed;
                                    padding-bottom: 0px; border-left: darkgray 1px dashed; padding-top: 0px; border-bottom: darkgray 1px dashed;
                                    background-repeat: no-repeat; border-collapse: collapse" cellspacing="0" cellpadding="0"
                                    border="0">
                                    <tbody>
                                        <tr>
                                            <td style="height: 160px">
                                                <table style="width: 254px; height: 24px" cellpadding="0" border="0">
                                                    <tbody>
                                                        <tr>
                                                            <td align="center" colspan="2">
                                                            	<table>
																 <%
																		if(session!=null)
																		{ 
																			if(session.getAttribute("LoginError")!=null||LognCheck.equalsIgnoreCase("Invalid"))
																			{
																	%>
																				<tr class="rsaLightAqua"> 
																					 <td height='35' colspan='3' align="left" style="padding-left:10px">
																					<b/><font color = 'red' style="font-family:Arial;font-size:12px;font-weight:bold;">
																					Invalid Login</font></td>
																						
																				</tr> 
																	<%
																				session.removeAttribute("LoginError");
																			}
																			//
																			String sess="";
																			String user="";
																			sess =(String) session.getAttribute("ses");
																			user=(String) session.getAttribute("user");
																				
																			try	
																			{
																				//Updating the End time for the Session
																				sestrack.endSession(sess,user);
																			}
																			catch(Exception ex)
																			{
																				System.out.println("the EXCEPTION IN SESSION ENDTIME UPDATING in ADMLOGIN IS "+ex.toString());
																			}
													
																		} 
																		session = request.getSession(true); 
													
																		//This is the NEWLY ADDED by karthy FOR SESSION TIMING OUT 
													
																			int loger = 0;
																			try
																			{
																				Enumeration enumer = request.getParameterNames();
																				while(enumer.hasMoreElements())
																				{
																					String s = (String)enumer.nextElement();
																					if(s.equals("loger1")) loger = Integer.parseInt(request.getParameter(s));
																				}
																			}
																			catch (Exception e)
																			{	
																			}
													
																			String userName = "";
																			String usertype="";
																			String userMode="";
																			String dbName = "";
																			String min="60";
													
																			String removeLock="Nolock";
													
																			String liveStatus="checked";
																			String testStatus="";
													
																			int countCheck=0;
																			String userLogStatus="";
																			if(request.getMethod().equalsIgnoreCase("post"))
																			{
																				
																				String checkUser = request.getParameter("user")!=null?request.getParameter("user"):"";
																				String checkPass = request.getParameter("pass")!=null?request.getParameter("pass"):"";
																				String dbStatus = request.getParameter("userLoginMode")!=null?request.getParameter("userLoginMode"):(path.indexOf("test")!=-1?"Test":"Live");
																				if("Live".equalsIgnoreCase(dbStatus))
																				{
																					
																					liveStatus="checked";
																					userMode="Live";
																					session.setAttribute("userLoginMode","Live");
																					
																				}
																				else if("Test".equalsIgnoreCase(dbStatus))
																				{
																					testStatus="checked";
																					userMode="Test";
																					session.setAttribute("userLoginMode","Test");
																				}
																				else
																				{
																					testStatus="checked";
																					userMode="Test";
																					session.setAttribute("userLoginMode","Test");
																				}
															
																				boolean usernme = logSession.uname();
																				String qry=logSession.getMsg();
																				String userStatus="";
																				
																				if(usernme)
																				{
																					boolean retValue = logSession.validate();
																					userName = logSession.getUser();
													
																					if((session.getAttribute("user"))!=null)
																					{
																						logSession.updateLoginStatus(((String)session.getAttribute("user")),"loggedout",((String)session.getAttribute("ses")));
																							userLogStatus=logSession.getLoginStatus(userName);
																							
																						}
																					else
																					{
																						
																							countCheck=logSession.checkUserSessionTimeOut(userName,min);
																							
																							userLogStatus=logSession.getLoginStatus(userName);
													
																							if(userLogStatus.equalsIgnoreCase("loggedin"))
																							{
													
																								if(countCheck <= 0)
																								{
																									removeLock="locked";
																								}else
																								{
																										removeLock="unlocked";
																								}
													
																							}
																					}
																					if(!userLogStatus.equalsIgnoreCase("loggedin"))
																					{
													
																						if(!retValue)
																						{
																							
																		%>
																		<tr  class="rsaLightAqua">
																			<td colspan=4 height=25 align="right" width=250 class="rsaLightAqua">
																					<center><font color = 'red' style="font-family:Arial;font-size:12px;font-weight:bold;">Invalid Login</font></center>
																			</td>
																		</tr>
																									
															
																		<%
																		
																			usertype=logSession.getUserType();
																			if(!usertype.equalsIgnoreCase("admin"))
																				
																						loger = loger + 1;
																		
																					}
																					else if(retValue)
																					{
																						String sessionid = request.getSession().getId();
																						Random rndm = new Random();
																						String rnd = ""+rndm.nextInt(99999);
																						String sesrnd = sessionid + rnd;
																						
																						synchronized(this) {
																						session.setAttribute("ses",sesrnd);
																						session.setAttribute("rsa_type","s");
																						session.setAttribute("user",userName);
																						}
													
																						usertype=logSession.getUserType();
																						boolean userTypeFlag = logSession.isAdminType();
																						
																						//This is added by Karthy for Getting the Ip Address
																						String ipAddress="";
																						InetAddress ip=InetAddress.getLocalHost();
																						//ipAddress=ip.getHostAddress();
																						ipAddress=request.getRemoteAddr();
																						//This is added by  for Getting the Ip Address
													
																						//Method modified in AdmLogin.java to Accomadate Ip Addres by Karthy
													
																						logSession.strtSession(sesrnd,userName,ipAddress);
													
																						//Method modified to Restrict Multiple Logins to On April 5 2007 by Karthy
																						//logSession.insertLoginStatus(userName,"loggedin",sesrnd);
																						logSession.insertLoginStatus(userName,"loggedout",sesrnd);
																						session.setAttribute("usertype",usertype);
																						if(userTypeFlag)
																							session.setAttribute("user1","admin");
																						else
																							session.setAttribute("user1","brokers");
																						//session.setAttribute("product_id","3");
																						
																						
													%>
																						<jsp:forward page="../admin/mainnew.jsp">
																						<jsp:param name="user" value="<%=userName%>"/>
																						<jsp:param name="userLoginMode" value="<%=userMode%>"/>
																						</jsp:forward>
																						<%
													
													
																						if(usertype.equalsIgnoreCase("agent"))
																						{
																							out.println("the useeAGEBT  Mode is "+userMode);
																						}
																						else if(usertype.equalsIgnoreCase("admin"))
																						{
																							
																						}
																						else if(usertype.equalsIgnoreCase("broker"))
																						{
													
																						}
																						else
																						{
																						}
																					}
																
																					if (loger == 5)	//3 times failed
																					{
																						response.sendRedirect("login/LockedMessage.jsp?user=" + userName);
																					}
																				
																				}
																				else
																				{
																					userName = "";
																					
																%>
																					
																				<tr  class="rsaLightAqua">
																					<td height='26' colspan='4' align="center" style="padding-left:10px">
																					<b/><font color = 'red' style="font-family:Arial;font-size:12px;font-weight:bold;">
																					Sorry !--The Requested User Already Logged In
																					</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																					<%
																					if(removeLock.equalsIgnoreCase("locked"))
																					{
																						%>
																					<a href='#' onclick='removeLock(1)' >
																					<font color = 'blue' style="font-family:Arial;font-size:12px;font-weight:bold;">
																					Remove Lock and Proceed</font></a>
													
													
																					<%
																					}
																						%>
																					
																					</td>
																				</tr>
																	<%
																					//}
																			}
																	
																		}
																		else
																		{
																				userName = "";
																				String statusOfUser=log_lock.LogStatus();
																				if(statusOfUser.equalsIgnoreCase("L"))
																				{
																	%>
																				
																		<tr  class="rsaLightAqua">
																		<td colspan=4 height=25 align="center" width=250><center><font color = 'red' style="font-family:Arial;font-size:12px;font-weight:bold;">
																						This LoginId Is Locked. Please Contact Administartor</font></center></td>
																					
																				</tr>
																	<%
																					}
																				else
																				{
																					String msg="";
																					msg = "Invalid Login";
													%>
																				<tr class="rsaLightAqua">
																			<td colspan=4 height=25 align="right" width=250><center>
																			<font color = 'red' style="font-family:Arial;font-size:12px;font-weight:bold;"><%=msg%>
																						</font></center></td>
																					
																				</tr>
																	<%
																					}
																			}
																				
																		}
																		else
																		{
																		}
																	%>
																	
																	<tr>
													                              <td class="" valign="top">
													                              <input type="hidden" value="<%= loger %>" name="loger1"/>
													                              <table width="100%" border="0" cellpadding="0" cellspacing="0" >
													                              <%if(path.indexOf("test")!=-1){%>
													                              <tr>
													                        <td height="5px" style="color:red;font-weight:bold;font-size:x">Test Environment</td>
													                        </tr>
													                        <%} %>
													                                      <tr>
													                                          <td align="center" colspan="2">
													                                              Log In</td>
													                                      </tr>
													                                    </table>
													                              </td>
													                            </tr>
													                            <tr>
													                              <td><table width="100%" border="0" cellpadding="0" cellspacing="0">
													                            	<tr>
													                                      <td style="height: 25px" align="right">
													                                          User Name:
													                                      </td>
													                                      <td style="height: 25px">
													                                          <input name="user" type="text" id="user" style="width: 152px"/>
													                                      </td>
													                                  </tr>
													                                  <tr>
													                                      <td align="right">
													                                          Password:
													                                      </td>
													                                      <td>
													                                          <input name="pass" type="password"  id="pass" style="width: 152px"/>
													                                      </td>
													                                  </tr>
													                                  
													                                  <tr>
													                                      <td style="color: red" align="center" colspan="2">
													                                      </td>
													                                  </tr>
													                                  <tr>
													                                      <td style="height: 24px" align="right" colspan="2">
													                                          <button id="LoginButton" value="Log In" style="width: 112px" onclick="this.form.submit();">
													                                              Log In</button>&nbsp;
													                                      </td>
													                                  </tr>
													                              </table>
											                             	 </td>
											                            </tr>
									                            </table>
                                                            </td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </td>
                        </tr>
                    </table>
                </p>
            </div>
            <!--

CONTENT SIDE COLUMN

-->
            <div id="content-side-two-column" style="font-family: Verdana">
                <h2 style="text-align: center">
                    <span style="font-size: 10pt">&nbsp;Cargo</span></h2>
                <h2 style="text-align: center">
                    <span style="font-size: 10pt"></span><span style="color: dimgray"><span style="font-size: 8pt">
                        <span lang="EN-GB" style="text-transform: uppercase; mso-fareast-font-family: 'Times New Roman';
                            mso-bidi-font-family: Tahoma; mso-ansi-language: EN-GB; mso-fareast-language: ES;
                            mso-bidi-language: AR-SA; mso-bidi-font-style: italic">Online insurance</span></span></span></h2>
                <p>
                    Emirates offers cargo insurance policies covering the risks of transit for all types
                    of cargo transported.</p>
            </div>
            <div class="clear">
                &nbsp;</div>
        </div>
        <!--

FOOTER

-->
        <div id="footer">
            ©2010 Emirates Company Limited. All rights reserved. <a href="LegalNotice.aspx">
                Terms of Use</a>
            <div>
                &nbsp;</div>
        </div>
    </form>
</body>
</html>
--%>
