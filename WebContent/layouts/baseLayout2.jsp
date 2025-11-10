<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
     	<sj:head jqueryui="true" jquerytheme="start" />
        <link href="css/easyui.css" rel="stylesheet" type="text/css">
        <link href="css/icon.css" rel="stylesheet" type="text/css">
        <link href="css/demo2.css" rel="stylesheet" type="text/css">
        <link href="css/styles2.css" rel="stylesheet" type="text/css" />
        <link href="css/tcal.css" rel="stylesheet" type="text/css" />
        <link href="css/ddlevelsmenu-topbar.css" rel="stylesheet" type="text/css" />
        <link href="css/ddlevelsmenu-base.css" rel="stylesheet" type="text/css" />
        <link href="css/displaytag2.css" rel="stylesheet" type="text/css">
        <script type="text/javascript" src="js/tcal.js"></script>
        <script type="text/javascript" src="js/common.js"></script>
        <script src="js/ddlevelsmenu.js" type="text/javascript"></script>
        <script src="js/ddtabmenu.js" type="text/javascript"></script>
        <script>
       	 $(document).unbind('keydown').bind('keydown', function (event) {
			    var doPrevent = false;
			    if (event.keyCode === 8) {
			        var d = event.srcElement || event.target;
			        if ((d.tagName.toUpperCase() === 'INPUT' && (d.type.toUpperCase() === 'TEXT' || d.type.toUpperCase() === 'PASSWORD' || d.type.toUpperCase() === 'FILE')) 
			             || d.tagName.toUpperCase() === 'TEXTAREA') {
			            doPrevent = d.readOnly || d.disabled;
			        }
			        else {
			            doPrevent = true;
			        }
			    }
			    if (doPrevent) {
			        event.preventDefault();
			    }
			});
        </script>
    </head>
    <body>
    <tiles:insertAttribute name="header" />
    <tiles:insertAttribute name="menu" />
    <tiles:insertAttribute name="body" />
    <tiles:insertAttribute name="footer" />
    
    </body>
</html>
