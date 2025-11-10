<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <link href="css/easyui.css" rel="stylesheet" type="text/css">
        <link href="css/icon.css" rel="stylesheet" type="text/css">
        <link href="css/demo.css" rel="stylesheet" type="text/css">
        <link href="css/styles.css" rel="stylesheet" type="text/css" />
        <link href="css/tcal.css" rel="stylesheet" type="text/css" />
        <link href="css/displaytag.css" rel="stylesheet" type="text/css">
  		<link rel="stylesheet" type="text/css" href='${pageContext.request.contextPath}/assets1/bootstrap/css/bootstrap.min.css'>
        <script type="text/javascript" src="js/tcal.js"></script>
        <script type="text/javascript" src="js/common.js"></script>
        
        <script type="text/javascript" src='${pageContext.request.contextPath}/assets1/plugins/jquery/jquery-3.7.1.min.js'></script>
        <script type="text/javascript" src='${pageContext.request.contextPath}/assets1/bootstrap/js/bootstrap.min.js'></script>
		 
		  
    <style>
          .btn-oval{
          	border-radius:30px;
          }
          
          .btn-oval:focus{
          	outline: none !important;
          }

         button.ui-datepicker-trigger{
          	display:none;
         }
         .input-group{		
			z-index: 1;		
          }
          .form-control:focus {
            outline: none !important;
            border: 1px solid #1ea55d;
            box-shadow: 0 0 10px #1ea55d;
        }
        select option{
        	padding: 10px;
        }
        .form-group{
        	font-size: 14px;
        }
        .list-group li .col-md-4,.list-group li .col-md-6,.list-group li .col-md-12, .list-group label{
        	font-size: 14px !important;
        }
        .col-md-1 label, .col-md-2 label, .col-md-3 label, .col-md-4 label, .col-md-5 label, .col-md-6 label, .col-md-7 label, .col-md-8 label, .col-md-9 label, .col-md-10 label, .col-md-11 label, .col-md-12 label{
        	font-size: 14px !important;
        }
        
        .col-md-1 , .col-md-2 , .col-md-3 , .col-md-4 , .col-md-5 , .col-md-6 , .col-md-7 , .col-md-8 , .col-md-9 , .col-md-10 , .col-md-11 , .col-md-12 {
        	font-size: 14px !important;
        }
        .table th,td{
         	font-size: 14px !important;
        }
        
        .table th label, .table td label{
         	font-size: 14px !important;
        }
    </style>
    </head>
    <body>
        <div>
        	<tiles:insertAttribute name="header" />
        </div>
        <div>
        	<tiles:insertAttribute name="menu" />
        </div>
        <div>
        	<tiles:insertAttribute name="body" />
        </div>
        <div>
        	<tiles:insertAttribute name="footer" />
        </div>
    </body>
</html>
