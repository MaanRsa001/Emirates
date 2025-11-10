var appPath;
function postRequest(strURL, id) 
{
	var xmlHttp;
    if (window.XMLHttpRequest) { // Mozilla, Safari, ...
		var xmlHttp = new XMLHttpRequest();
    }else if (window.ActiveXObject) { // IE
		var xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
   	}
    xmlHttp.open('POST', strURL, true);
    xmlHttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	xmlHttp.onreadystatechange = function() 
	{
		if (xmlHttp.readyState == 4) 
		{
			document.getElementById(id).innerHTML=xmlHttp.responseText;
		}
   }
	xmlHttp.send(null);
}

function checkAlphaNumeric(e) {
    
    if ((e.keyCode >= 48 && e.keyCode <= 57) ||
       (e.keyCode >= 65 && e.keyCode <= 90) ||
       (e.keyCode >= 97 && e.keyCode <= 122))
        return true;

    return false;
}

function fun_AllowOnlyAmountAndDot(val)
{
	
	
    if(event.keyCode > 47 && event.keyCode < 58 || event.keyCode == 46)
    {
       var txtbx=document.getElementById(val);
       var amount = document.getElementById(val).value;
       var present=0;
       var count=0;

       if(amount.indexOf(".",present)||amount.indexOf(".",present+1));
       {
      // alert('0');
       }

      /*if(amount.length==2)
      {
        if(event.keyCode != 46)
        return false;
      }*/
       do
       {
       present=amount.indexOf(".",present);
       if(present!=-1)
        {
         count++;
         present++;
         }
       }
       while(present!=-1);
       if(present==-1 && amount.length==0 && event.keyCode == 46)
       {
            event.keyCode=0;
            //alert("Wrong position of decimal point not  allowed !!");
            return false;
       }

       if(count>=1 && event.keyCode == 46)
       {

            event.keyCode=0;
            //alert("Only one decimal point is allowed !!");
            return false;
       }
       if(count==1)
       {
        var lastdigits=amount.substring(amount.indexOf(".")+1,amount.length);
        if(lastdigits.length>=4)
                    {
                      //alert("Four decimal places only allowed");
                      event.keyCode=0;
                      return false;
                      }
       }
            return true;
    }
    else
    {
            event.keyCode=0;
            //alert("Only Numbers with dot allowed !!");
            return false;
    }

}

function checkDecimals11(obj)
{
	var validno="1234567890.";
	var temp=obj.value;
	//var minus=temp.split('-').length-1;
	
	var dots=temp.split('.').length-1;
	
	var index=temp.indexOf(".");
	var val=temp.substring(index+1,temp.length);
	/*if(minus>1)
	{
		obj.value=temp.substring(0,index+1);
	}*/
	if(dots>1)
	{
		obj.value=temp.substring(0,index+1);
	}
	
	if(index==-1)
	{
	if(val.length>2)
		obj.value=temp.substring(0,index+19);
	}else
	{
		if(val.length>4)
			obj.value=temp.substring(0,index+5);		
	}
	for(i=0; i<=temp.length; i++)
	{
		if(validno.indexOf(temp.charAt(i))==-1)
		{
			obj.value=temp.substring(0,i);
		}
		
	}
	
return false;
}
function checkDecimals15(obj)
{
	var validno="1234567890.-";
	var temp=obj.value;
	var minus=temp.split('-').length-1;
	var dots=temp.split('.').length-1;
	var index=temp.indexOf(".");
	var val=temp.substring(index+1,temp.length);
	if(minus>1)
	{
		obj.value=temp.substring(0,index+1);
	}
	if(dots>1)
	{
		obj.value=temp.substring(0,index+1);
	}
	
	if(index==-1)
	{
	if(val.length>2)
		obj.value=temp.substring(0,index+19);
	}else
	{
		if(val.length>4)
			obj.value=temp.substring(0,index+5);		
	}
	for(i=0; i<=temp.length; i++)
	{
		if(validno.indexOf(temp.charAt(i))==-1)
		{
			obj.value=temp.substring(0,i);
		}
		
	}
	
return false;
}

/*function checkDecimals(obj)
{
	var validno="1234567890.";
	var temp=obj.value;
	var index=temp.indexOf(".");
	var val=temp.substring(index+1,temp.length);
	if(val.length>2)
		obj.value=temp.substring(0,index+3);
	for(i=0; i<=temp.length; i++)
	{
		if(validno.indexOf(temp.charAt(i))==-1)
		{
			obj.value=temp.substring(0,i);
		}
	}
return false;
}*/

function checkDecimals(obj)
{
	var validno="1234567890.";
	var temp=obj.value;
	var index=temp.indexOf(".");
	if(index!=-1)
	{
		var val=temp.substring(index+1,temp.length);
		if(val.length>2)
			obj.value=temp.substring(0,index+3);
	}
	for(i=0; i<=temp.length; i++)
	{
		if(validno.indexOf(temp.charAt(i))==-1)
		{
			obj.value=temp.substring(0,i);
		}
	}
return false;
}


function checkDecimal(obj)
{
	var validno="1234567890.-";
	var temp=obj.value;
	var index=temp.indexOf(".");
	var val=temp.substring(index+1,temp.length);
	if(index>0)
		obj.value=temp.substring(0,index+5);
	for(i=0; i<=temp.length; i++)
	{
		if(validno.indexOf(temp.charAt(i))==-1)
		{
			obj.value=temp.substring(0,i);
		}
	}
	return false;
}
function checkNegative(obj)
{
	var validno="1234567890.";
	var temp=obj.value;
	var index=temp.indexOf(".");
	var val=temp.substring(index+1,temp.length);
	if(index>0)
		obj.value=temp.substring(0,index+5);
	for(i=0; i<=temp.length; i++)
	{
		if(validno.indexOf(temp.charAt(i))==-1)
		{
			obj.value=temp.substring(0,i);
		}
	}
	return false;
}
function checkDecimalPoint(obj)
{
	var validno="1234567890.";
	var temp=obj.value;
	var index=temp.indexOf(".");
	var val=temp.substring(index+1,temp.length);
	if(index>0)
		obj.value=temp.substring(0,index+5);
	for(i=0; i<=temp.length; i++)
	{
		if(validno.indexOf(temp.charAt(i))==-1)
		{
			obj.value=temp.substring(0,i);
		}
	}
return false;
}
function checkNumbers(val){
	var value=val.value;
	var temp="";
	if(value!=null){
		temp=value.charAt(value.length-1);
		if((isNaN(temp) || temp==" ")&& temp!='.'){
			val.value=value.substring(0,value.length-1);
		}else if(value.indexOf('.')!=-1){
			val.value = value.substring(0,value.indexOf('.')) + value.substring(value.indexOf('.'),(value.indexOf('.')+7));
		}
	}	
}
function fun_AllowOnlyAmountAndDot(val)
{
    if(event.keyCode > 47 && event.keyCode < 58 || event.keyCode == 46)
    {
       var txtbx=document.getElementById(val);
       var amount = document.getElementById(val).value;
       var present=0;
       var count=0;

       if(amount.indexOf(".",present)||amount.indexOf(".",present+1));
       {
      // alert('0');
       }

      /*if(amount.length==2)
      {
        if(event.keyCode != 46)
        return false;
      }*/
       do
       {
       present=amount.indexOf(".",present);
       if(present!=-1)
        {
         count++;
         present++;
         }
       }
       while(present!=-1);
       if(present==-1 && amount.length==0 && event.keyCode == 46)
       {
            event.keyCode=0;
            //alert("Wrong position of decimal point not  allowed !!");
            return false;
       }

       if(count>=1 && event.keyCode == 46)
       {

            event.keyCode=0;
            //alert("Only one decimal point is allowed !!");
            return false;
       }
       if(count==1)
       {
        var lastdigits=amount.substring(amount.indexOf(".")+1,amount.length);
        if(lastdigits.length>=4)
                    {
                      //alert("Four decimal places only allowed");
                      event.keyCode=0;
                      return false;
                      }
       }
            return true;
    }
    else
    {
            event.keyCode=0;
            //alert("Only Numbers with dot allowed !!");
            return false;
    }

}
function fun_AllowOnlyAmountAndDotSix(val)
{
    if(event.keyCode > 47 && event.keyCode < 58 || event.keyCode == 46)
    {
       var txtbx=document.getElementById(val);
       var amount = document.getElementById(val).value;
       var present=0;
       var count=0;

       if(amount.indexOf(".",present)||amount.indexOf(".",present+1));
       {
      // alert('0');
       }

      /*if(amount.length==2)
      {
        if(event.keyCode != 46)
        return false;
      }*/
       do
       {
       present=amount.indexOf(".",present);
       if(present!=-1)
        {
         count++;
         present++;
         }
       }
       while(present!=-1);
       if(present==-1 && amount.length==0 && event.keyCode == 46)
       {
            event.keyCode=0;
            //alert("Wrong position of decimal point not  allowed !!");
            return false;
       }

       if(count>=1 && event.keyCode == 46)
       {

            event.keyCode=0;
            //alert("Only one decimal point is allowed !!");
            return false;
       }
       if(count==1)
       {
        var lastdigits=amount.substring(amount.indexOf(".")+1,amount.length);
        if(lastdigits.length>=6)
                    {
                      //alert("Six decimal places only allowed");
                      event.keyCode=0;
                      return false;
                      }
       }
            return true;
    }
    else
    {
            event.keyCode=0;
            //alert("Only Numbers with dot allowed !!");
            return false;
    }

}
function checkMobileNo(obj){
	var validno="1234567890+";
	var temp=obj.value;
	for(i=0; i<=temp.length; i++)
	{
		if(validno.indexOf(temp.charAt(i))==-1)
		{
			obj.value=temp.substring(0,i);
		}
	}
}
function popUp(url,w,h)
{
	var windowName = "PolicyView";
		 var width  = screen.availWidth;
		 var height = screen.availHeight;
		 var features =
			'width='          + w +
			',height='		  + h +
			',left='		  + ((width - w - 10) * .5)  +
			',top='			  + ((height - h - 30) * .5) +
			',directories=no' +
			',location=no'	  +
			',menubar=no'	  +
			',scrollbars=yes' +
			',status=no'	  +
			',toolbar=no'	  +
			',resizable=false';
		var strOpen = window.open (url, windowName, features);
		strOpen.focus();
		return false;
}

function textLimit(field,maxlen) 
{

	if(field.value.length > maxlen)
	{
		while(field.value.length > maxlen){
			field.value=field.value.replace(/.$/,'');
		}
	    alert('Maximum Limit '+maxlen);
	}
}
function disableForm(theform, disable, exclude) {
	if (document.all || document.getElementById) {
		for (i = 0; i < theform.length; i++) {
		var formElement = theform.elements[i];
			if (exclude.indexOf(formElement.name.toString().indexOf("[")!=-1?formElement.name.toString().substring(0,formElement.name.toString().indexOf("[")):formElement.name.toString())==-1) {
				formElement.disabled = disable;
			}
		}
	}
}
function formatCurrency(value){
	value += '';
	value = value.replace(/,/g, '');
    x = value.split('.');
    x1 = x[0];
    x2 = x.length > 1 ? '.' + x[1] : '';
    var rgx = /(\d+)(\d{3})/;
    while (rgx.test(x1))
    x1 = x1.replace(rgx, '$1' + ',' + '$2');
    return x1 + x2;
}
function enableForm(theform, disable, include) {
	if (document.all || document.getElementById) {
		for (i = 0; i < theform.length; i++) {
		var formElement = theform.elements[i];
			if (include.indexOf(formElement.name.toString().indexOf("[")!=-1?formElement.name.toString().substring(0,formElement.name.toString().indexOf("[")):formElement.name.toString())!=-1) {
				formElement.disabled = disable;
			}else if (include.indexOf('fragile')!=-1 && formElement.name.indexOf('fragile')!=-1) {
				formElement.disabled = disable;
			}
		}
	}
}
function showHideDetails(obj, id){
	var src = obj.src;
	if(src.indexOf("minus.png")!=-1){
		obj.src = appPath + "/images/plus.png";
		document.getElementById(id).style.display="none";
	}else{
		obj.src = appPath + "/images/minus.png";
		document.getElementById(id).style.display="";
	}			
}
function getAjaxContentPost(strURL, id, params){
	var xmlHttp;
    if (window.XMLHttpRequest) { // Mozilla, Safari, ...
		var xmlHttp = new XMLHttpRequest();
    }else if (window.ActiveXObject) { // IE
		var xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
   	}
    xmlHttp.open('POST', strURL, true);
    xmlHttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	xmlHttp.onreadystatechange = function(){
		if (xmlHttp.readyState == 4){
			var response = xmlHttp.responseText;
			if(response.indexOf("Script-Execute", 0)!=-1){
				response = response.replace("Script-Execute", "");
                var ss = document.createElement('script');
                var scr = response;
                ss.text = scr;
                var hh = document.getElementsByTagName('head')[0];
                hh.appendChild(ss);
            }else{
                document.getElementById(id).innerHTML = response;            
            }
			if(id == "CauseOfLossDiv"){
				LoadFromCOL("0");
			}else if(id == "CauseOfLossDetailsDiv"){
				OthersDiv();
			}
		}
   }
	xmlHttp.send(params+"&RandomId="+Math.random());
}
function OpenUploadPage(docId){
	var fileCount = document.getElementById("FileCountforDrm"+docId).value;
	var width = 650;
	var height = 500;
	var leftPosition=(screen.width)?(screen.width-width)/2:100;
    var topPosition=(screen.height)?(screen.height-height)/2:100;
    var applicationNo = document.getElementById("applicationNo").value;
	uploadWindow = window.open(appPath + "GetUploadPage.action?fileCount="+fileCount+"&applicationNo="+applicationNo+"&docId="+docId, 
					'UploadWindow', 'scrollbars=1,width='+width+',height='+height+',left='+leftPosition+',top='+topPosition);
    uploadWindow.focus();
}
function reloadDocuments(docId){
	var applicationNo = document.getElementById("applicationNo").value;
	var params = "applicationNo="+applicationNo+"&docId="+docId;
	var url = appPath + "GetUploadFileDetails.action";
	getAjaxContentPost(url, "DocDiplayForDrm"+docId, params);	
}
function openUploadedDoc(fileName){
	var applicationNo = document.getElementById("applicationNo").value;
	docWindow = window.open(appPath + "/HealthDocuments/"+applicationNo+"/"+fileName);
	docWindow.focus();
}
function downloadFile(docOurName,docName,form){
	form.docOurName.value =docOurName;
	form.docName.value =docName;
	var action=form.action;
	form.action = "${pageContext.request.contextPath}/DownloadFile.action";
	form.submit();
	form.action=action;
}
function deleteDocuments(docId, docSNo){
	var conf = confirm("File will be Deleted. Are you sure?");
	if(conf){
		var applicationNo = document.getElementById("applicationNo").value;
		var params = "applicationNo="+applicationNo+"&docId="+docId+"&docSNo="+docSNo;
		var url = appPath + "/DeleteUploadFileDetail.action";
		getAjaxContentPost(url, "DocDiplayForDrm"+docId, params);
	}
}

function check_date(input){
	var validformat=/^\d{2}\/\d{2}\/\d{4}$/ //Basic check for format validity
	var returnval=false
	if (!validformat.test(input.value)) {
		input.value ='';
	}
	else{ //Detailed check for valid date ranges
		var dayfield=input.value.split("/")[0]
		var monthfield=input.value.split("/")[1]
		var yearfield=input.value.split("/")[2]
		var dayobj = new Date(yearfield, monthfield-1, dayfield)
		if ((dayobj.getMonth()+1!=monthfield)|| (dayobj.getDate()!=dayfield)||(dayobj.getFullYear()!=yearfield)){
			input.value ='';
		}
	}
}

function validamt(val){
	var value=val.value;
	if(value!=null){
		val.value = value.replace(/[^,0-9.]/g,'');
	}
}
