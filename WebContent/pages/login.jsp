<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page isELIgnored="false" %>
<%@ page import="nl.captcha.Captcha"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
  <title><s:text name="application.name" /></title>
  <link rel="stylesheet" type="text/css" href='${pageContext.request.contextPath}/assets1/bootstrap/css/bootstrap.min.css'>
  <script type="text/javascript" src='${pageContext.request.contextPath}/assets1/plugins/jquery/jquery-3.7.1.min.js'></script>
        <script type="text/javascript" src='${pageContext.request.contextPath}/assets1/bootstrap/js/bootstrap.min.js'></script>
	<script type="text/javascript">
		function callSubmit(val){
			if(val=='login'){
		    	document.form1.action="${pageContext.request.contextPath}/Loginsubmit.action";
		    }
		    document.form1.submit();
		}
	</script>
	<style>
	 .Logincontainer .row {
       margin-left: 0px; 
       margin-right: 0px;
      }
      .Imagediv{
		  height:350px;
		  background-image:url("assets/marine-aviation2.jpg");
		  background-position:center center;
		  background-size:cover;
		  background-repeat:no-repeat;
		  position: absolute;
          width: 100%;
	  }
	  .logincontainer , .textContainer {
		  background-color:white;
		  padding:10px;
		  box-shadow: rgba(149, 157, 165, 0.2) 0px 8px 24px;
	  }
	  .logincontainer .form-control, .passworddiv .form-control{
		  border-radius:0px;
		  padding:7px;
		  height:55px;
		  border-width: 1px 1px 1px 0;
		  border-style: solid;
		  border-color: #235aa9 #235aa9 #235aa9 #235aa9;
	  }
	  
	  .logincontainer .glyphicon, .passworddiv .glyphicon{
		  color:#235aa9;
	  }
	  .logincontainer .input-group{
		  padding:10px;
	  }
	  .logincontainer .input-group-addon, .passworddiv .input-group-addon{
		  border-radius:0px;
		  background-color:white;
		  border-width: 1px 0px 1px 1px;
		  border-style: solid;
		  border-color: #235aa9 #235aa9 #235aa9 #235aa9;
	  }
	  .loginheading h3{
		  font-family:"Arial";
		  font-weight:bolder;
		  text-align:center;
		  color:#1ea55d;

	  }
	  .passworddiv .input-group , .btn{
		  margin-top:5px;
	  }
	  .loginheading{
		  margin-top: 52px;
	  }
	  .logincontainer .btn-default, .passworddiv .btn-default{
		  border:2px solid #235aa9;
		  border-radius:0px;
		  padding:14px;
		  color:#1ea55d;
		  font-weight:bolder;
          -webkit-transition: background 1s; /* For Safari 3.0 to 6.0 */
          transition: background 1s;
	  }
	  .logincontainer .btn-default:hover, .passworddiv .btn-default:hover{
		  background-color:#235aa9;
		  color:white;

	  }
	  .logindiv{
		  margin-top:150px;
		  padding:20px
	  }
		.logincontainer .form-control:focus {
			outline:none !important;
			outline-width: 0 !important;
			box-shadow: none;
			-moz-box-shadow: none;
			-webkit-box-shadow: none;
		}
		.passworddiv .form-control:focus {
			outline:none !important;
			outline-width: 0 !important;
			box-shadow: none;
			-moz-box-shadow: none;
			-webkit-box-shadow: none;
		}
		.logincontainer .form-control:focus .logincontainer .input-group-addon{
            border:1px solid #235aa9
		}
		.logincontainer .loginbtn{
             margin-top:20px;
		}
		.logincontainer .input-group{
			margin-top:10px;
		}
		.passwordissues{
			margin-top:24px;
		}
		.ContentHeading a{
          text-decoration: none;
		  font-weight:bold;
		  color:#235aa9;

		}

		.paragrapContent p{
			font-family:Verdana;
			font-size:12px;
			margin:8px;
			color:#235aa9

		}
		.ContentHeading h4{
			font-family:Arial;
			margin:8px;
			font-weight:bold;
			color:#1ea55d;
		}
		.paragrapContent .list-group {
			margin-bottom:2px;
		}
		.paragrapContent ul li span{
			padding:2px;
			color:#1ea55d;
		}
		.paragrapContent ul li{
			padding:5px;
		}
		.loginSection .phonenumber h4{
		    margin-top: 40px;
		}

	</style>
</head>
<body>
	<div class="loginSection">
		<div class="row" style="margin: 0px;">
	        <div class="col-md-3">
	           <img  src="assets/logo-main.png" alt="Emirates logo" >
			</div>
			<div class="col-md-3 col-md-offset-6 text-center phonenumber">
	            <h4>CALL US:(971)2-6440400</h4>
			</div>
		</div>
		<div class="Imagediv">
		</div>
	    <div class="logindiv">
	       <div class="row">
				<s:form name="form1" method="post" action="" theme="simple" enctype="multipart/form-data">
		            <div class="col-md-4">
	                    <div class="logincontainer">
							<div class="loginheading">
								<h3>SECURE LOGIN</h3>
							</div>
							<s:actionerror cssStyle="color:red;"/>
							<div class="input-group">
								<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
								<s:textfield name="loginId" id="loginId" class="form-control" placeholder="Username" maxlength="20" />
							</div>
							<div class="input-group">
								<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
								<s:password name="pwd" id="pwd" class="form-control" placeholder="Password" maxlength="100" />
								<s:hidden name="swidth" id="swidth" value=""/>
							</div>
							<%-- <div class="input-group">							
 										<div class="captchaBg" style="width: 260px; float: left;">
							            			<img id="captcha" src="<c:url value="simpleCaptcha.jpg"  />" width="160" name="captcha">							            			
							            </div>
							             <a href="#" onclick="reloadCaptcha();" style="float: left;"><img src="${pageContext.request.contextPath}/images/reload.png" alt="reload" width="40" height="40"/> </a>
							</div>
							<div class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-hand-right"></i></span>
							<s:textfield name="captchavalue" id="captchavalue" value=""  class="form-control" data-content="Please enter the text that you see in the image" />	
							</div>--%>
							<div class="row">
								<div class="col-md-6 col-xs-6 col-md-offset-3 col-xs-offset-3">
									<div class="loginbtn">
										<button type="submit" name="LoginButton" id="LoginButton" class="btn btn-default btn-block" onclick="callSubmit('login');">Login</button>
									</div>
								</div>
							</div>
							<div class="row passwordissues">
								<div class="col-md-5 col-xs-5 col-md-offset-1 col-xs-offset-1">
									<a href="${pageContext.request.contextPath}/Loginoption.action?status=changePwd" onclick="changePass()" >Change Password</a>
								</div>
								<div class="col-md-5 col-xs-5">
									<a href="${pageContext.request.contextPath}/Loginoption.action?status=forgotPwd" onclick="forgotPass()" >Forgot Password</a>
								</div>
							</div>
				        </div>
			        </div>
			    </s:form>
			    <div class="col-md-8">
			        <div class="textContainer">
                          <div class="ContentHeading">
			                <h4>MARINE CARGO INSURANCE</h4>
				        </div> 
				   
						<div class="paragrapContent">
							<p>
								Covers cargo against loss and/or damage during transit by sea, air or 
								land caused by the perils incidental to the mode of transit, as may be agreed. 
								A variety of insurance covers to cater for your needs are available, be it any 
								kind of general cargo or specialized cargo. Coverage is available for transit 
								risks as well as for war & strikes risks. Policies may be issued to cover single 
								consignments, or shipments over a period of time.
							</p>
						</div>
						<div class="ContentHeading">
							<h4>MARINE HULL INSURANCE</h4>
						</div> 
						<div class="paragrapContent">
							<p>
								We offer a variety of covers for a wide range of commercial waterborne tonnage,
								and the following types of vessels :-
							</p>
							<ul class="list-group">
								<li class="list-group-item"><span class="glyphicon glyphicon-ok-sign"></span>Ocean-going tonnage</li>
								<li class="list-group-item"><span class="glyphicon glyphicon-ok-sign"></span>Oil & Gas related tonnage</li>
								<li class="list-group-item"><span class="glyphicon glyphicon-ok-sign"></span>Coastal and Port craft</li>
								<li class="list-group-item"><span class="glyphicon glyphicon-ok-sign"></span>Yachts and other pleasure craft</li>
								<li class="list-group-item"><span class="glyphicon glyphicon-ok-sign"></span>Dry Docks and Floating Docks</li>
							</ul>
								
						</div>
						<div class="ContentHeading">
							<h4 class="text-center">
							  	<a data-toggle="collapse" data-target="#demo" style="cursor: pointer;">REPORT CLAIM</a>
                            </h4>
						</div> 
						<s:form name="searchForm" method="post" action="claimSearchPolicyNo" theme="simple">
							<div id="demo" class="collapse passworddiv">
								<s:if test="hasActionMessages()">
									<s:actionmessage cssStyle="list-style:none; color:green;"/>
								</s:if>
								<div class="row">
									<div class="col-md-6 col-md-offset-2">
										<div class="input-group">
											<span class="input-group-addon"><i class="glyphicon glyphicon-question-sign"></i></span>
											<s:textfield name="policyNo" id="policyNo" class="form-control" placeholder="Policy No" maxlength="30" />
										</div>
									</div>
									<div class="col-md-3">
										<div class="reportClaim">
											<button type="submit" name="search" id="search" class="btn btn-default btn-block">Submit</button>
										</div>
									</div>
								</div>
							</div>
						</s:form>
					</div>
			   </div>
	        </div>
	    </div>
	</div>
	<script>

	function reloadCaptcha(){
		    $("#captcha").attr("src", "${pageContext.request.contextPath}/simpleCaptcha.jpg");
		}
	</script>
	<div class="panel panel-primary">
		<div class="panel-footer">
			<div class="row" align="center">
				<div class="col-md-12">
					<h5>2020 &copy; <a href="http://www.eminsco.com/">Copyright Emirates Insurance</a> . All rights reserved</h5>
				</div>
				<div class="col-md-12">
					<h6>Developed By  <a href="http://www.maansarovar.com" >Maan Saravor Tech Solutions Pvt. Ltd.</a></h6>
				</div>
			</div>
		</div>
	</div>
</body>
</html>