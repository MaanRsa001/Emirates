





<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
    <title>test</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <meta http-equiv="Content-Script-Type" content="text/javascript">   
    <style type="text/css"><!--

    table#pagelayout {
        height:100%;
        width:100%;
        margin:auto;
        align:left;
    }

    // -->
    </style>
</head>
   
<body  leftmargin="0" topmargin="0" marginwidth="0"   marginheight="0"  height="100%" bgcolor="#EFDCE2">
<table width="1003"  border="1" cellpadding="0" cellspacing="0" align="left" height="100%" background="images/vm_bg_new.jpg" bordercolor="#941C42" style="border-collapse:collapse;" >
<tr><td valign="top">
<table id="pagelayout"  border="0" cellpadding="0" cellspacing="0" align="left">
<tr style="height:130px;">
<td align="right" valign="top">

<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
<tr>
    <td height="85" align="right" valign="top">
        <table width="100%" height="68" border="0" cellspacing="0" cellpadding="0" background="images/headerbg.jpg">
            <tr>
                
                <td  width="524" align="left" valign="middle" height="68"  > <img src="images/vishnu_logo_1.jpg">   </td>
                <td  align="right" valign="middle" height="68" >   <font color="white" size="3" face="Verdana, Arial, Helvetica, sans-serif"> <b></b>  </font>   </td>
                <td   align="left" valign="middle" > <span><font color="white" size="3" face="Verdana, Arial, Helvetica, sans-serif"> &nbsp;&nbsp; <b> </b> </font></span>  </td>
                <td  align="right" valign="middle" height="68" >   <font color="white" size="3" face="Verdana, Arial, Helvetica, sans-serif"> <b>User:</b>  </font>   </td>
                <td  width="100" align="left" valign="middle" > <span><font color="white" size="3" face="Verdana, Arial, Helvetica, sans-serif"> &nbsp;&nbsp; <b>  web </b> </font></span>  </td>
            </tr>

            <tr bgcolor="white">
                <td  valign="bottom" colspan="5" height=20> <script type='text/javascript'>
	function Go(){return}
</script>
<script type='text/javascript' src='Menu.js'></script>
<script type='text/javascript' src='menu_com.js'></script>


</td>
            </tr>
        </table>
        <br>
    </td>
</tr>



<tr>
    <td  align="center" valign="top">
        <strong>
       <span class="message">
       <font color="#FF0000" size="3" face="Verdana, Arial, Helvetica, sans-serif">
           
       </font>
       &nbsp;
       </span>
       <span class="message1">
       <font color="#006600" size="3" face="Verdana, Arial, Helvetica, sans-serif">
           
       </font>
       </span>

        </strong>
    </td>
</tr>
</table>

</td>
</tr>

<tr style="height:100%;">
    <td valign="top">









<script type="text/javascript" src="CommanAjax.js"></script>
<link rel="stylesheet" href="AutoComplete.css" media="screen" type="text/css">
<script language="javascript" type="text/javascript" src="autocomplete.js"></script>
<script type="text/javascript" src="New/check_availability.js"></script>




<script type="text/javascript">

function refresh()
{
    document.formgrn.action='GrvItemDetailsNew.jsp';
    document.formgrn.submit();
}

var rowIdCNT=1;
function addRow(tab)
{

    var tabObj;
    var len=0;

    tabObj=document.getElementById(tab);

    len=tabObj.rows.length;
    //// alert(len)
    document.formgrn.HidRowCnt.value=len;
    var len1=parseInt(len)+1;
    var Chemtype='';

    var val=new Array();
    val[0]='<select name="itemcode" id="itemcode'+len+'" style="width:200px;"   class="FIELD">'+Chemtype+'</select>';
    val[1]='<input type="text" style="width:100px;text-align:right;" name="quty" id="quty'+len+'"  class="box1"    maxlength=7 >';
    val[2]='<input type="text" style="width:100px;text-align:right;" name="itmrate" id="itmrate'+len+'"  class="FIELD"   " maxlength=10 "    >';
    val[3]='<input type="text" style="width:100px;text-align:right;" readonly name="itmtorate" id="itmtorate'+len+'"  class="FIELD"  maxlength=10 "    >';
    var temprowIdCNT=rowIdCNT;
    row = tabObj.insertRow(len);
    row.id ='rowId'+temprowIdCNT;
    row.onclick=function(){

    };

    for(i=0;i<val.length;i++){

        cell = row.insertCell(i);
        cell.innerHTML=val[i];
    }
    rowIdCNT++;

}
function delRow(tabId){
    var table=document.getElementById(tabId);
    var len=document.getElementById(tabId).rows.length-1;
    document.formgrn.HidRowCnt.value =parseInt(len)-1;
    if(len<0){
        alert("No Records to Delete");
    }
    else{
        var r=confirm("Do you want to delete the Last Row ? ");
        if (r==true)
        {
            table.deleteRow(len);
        }
    }
}
function setval(a,b){

    /*alert("a:"+a);*/
    document.getElementById('shqutynew'+b).value='';
    var   idname=0;
    var   pric=0;
    var   unitname=0;
    var   val=0;
    var   rte=0;
    var   tax=0;
    var   vat=0;
    var   vatv=0;


    var First=parseInt(a.indexOf("$"));
    idname=a.substr(0,First);
    val=a.slice(First+1) ;



    var second=parseInt(val.indexOf("$"));
    pric=val.substr(0,second);
    unitname=val.slice(second+1) ;

    var third=parseInt(unitname.indexOf("$"));
    vatv=unitname.substr(0,third);
    vat=unitname.slice(third+1) ;


    document.getElementById('shqutynew'+b).value=parseInt(val);
    document.getElementById('itmratenew'+b).value=parseFloat(unitname);
    document.getElementById('itmvatnew'+b).value=parseInt(vat);




}


function addRownew(tab)
{

    if(document.getElementById("off").value=="")
    {
        alert("Select Office")
      document.getElementById("off").focus();
        return false;
    }
 if(document.getElementById("btnm").value=="")
    {
          alert("Select Beat")
      document.getElementById("btnm").focus();
        return false;
    }
 if(document.getElementById("outnm").value=="")
    {
        alert("Select Outlet")
      document.getElementById("outnm").focus();
        return false;
    }
    if(document.getElementById("sname").value=="")
    {
        alert("Select Salesman")
      document.getElementById("sname").focus();
        return false;
    }
    if(document.getElementById("grvrefno").value=="")
    {
        alert("Enter The GRV REF NO");
      document.getElementById("grvrefno").focus();
        return false;
    }


    var tabObj;
    var lennew=0;

    tabObj=document.getElementById(tab);

    lennew=tabObj.rows.length;

    if(lennew>0)
    {
        for(var  k=0;k<lennew;k++)
    {
         if((document.getElementById('qutynew'+k).value)=="")
        {
            alert("Enter the Quantity") ;
            document.getElementById('qutynew'+k).focus()
            return false;

        }

    }

    }
    //// alert(len)
    document.formgrn.HidRowCntnew.value=lennew;
    var len1new=parseInt(lennew)+1;
    var Chemtypenew='';


    Chemtypenew+='<option value="">Select</option>';


    var val=new Array();
    val[0]='<select name="itemcodenew" id="itemcodenew'+lennew+'" style="width:200px;"  OnChange="setval(this.value,'+lennew+')"   class="FIELD">'+Chemtypenew+'</select>';
    val[1]='<input type="text" style="width:100px;text-align:right;" name="" id="shqutynew'+lennew+'"  readonly class="box1" onkeypress="numeric()"  onblur="gettotalnew()"  maxlength=7 >';
    val[2]='<input type="text" style="width:100px;text-align:right;" name="qutynew" id="qutynew'+lennew+'"  class="box1" onkeypress="numeric()"  onblur="gettotalnew()"  maxlength=7 >';
    val[3]='<input type="text" style="width:100px;text-align:right;" name="itmratenew" id="itmratenew'+lennew+'"   class="FIELD"  onkeypress="numericanddot(); " maxlength=10 readOnly>';
    val[4]='<input type="text" style="width:100px;text-align:right;" name="itmvatnew" id="itmvatnew'+lennew+'"   class="FIELD"  onkeypress="numericanddot(); " maxlength=10 readOnly>';
    val[5]='<input type="text" style="width:100px;text-align:right;" readonly name="itmtaxnew" id="itmtaxnew'+lennew+'"  class="FIELD"  onkeypress="numericanddot(); " maxlength=10 onblur="gettotal(),pointVal12(this.id,'+lennew+')"    >';
    val[6]='<input type="text" style="width:100px;text-align:right;" readonly name="itmtoratenew" id="itmtoratenew'+lennew+'"  class="FIELD"  onkeypress="numericanddot(); " maxlength=10 onblur="gettotal(),pointVal12(this.id,'+lennew+')"    >';


    var temprowIdCNTnew=rowIdCNT;
    row = tabObj.insertRow(lennew);
    row.id ='rowIdnew'+temprowIdCNTnew;
    row.onclick=function(){

    };

    for(i=0;i<val.length;i++){

        cell = row.insertCell(i);
        cell.innerHTML=val[i];
    }
    rowIdCNT++;

}
function delRownew(tabId){
    var table=document.getElementById(tabId);
    var len=document.getElementById(tabId).rows.length-1;
    document.formgrn.HidRowCntnew.value =parseInt(len)-1;
    if(len<0){
        alert("No Records to Delete");
    }
    else{
        var r=confirm("Do you want to delete the Last Row ? ");
        if (r==true)
        {
            table.deleteRow(len);
        }
    }
}
function pointVal12(amnt,len)
{
    var amntVal=document.getElementById(amnt,len).value;
    var len1=len
    if(amntVal !='' || amntVal !='0' || amntVal !=' '){
        var decPnt=0;
        for (var i = 0; i < amntVal.length; i++)  {
            var ch1 = amntVal.substring(i, i + 1);
            if ( (ch1 == '.'))                     {
                decPnt++;
            }                                              }

        if(parseFloat(decPnt)>1){
            alert("One Decimal Point only Allowed");
            document.getElementById(amnt).focus();
            return false;
        }

    }

}

function setamt(val,len)
{

    var url="grvitemcostAjax.jsp?itemcode="+val+"&length="+len+"&rand="+Math.random();
    getData(url,'ProjectCli');
    setSeparator("~");
}

function ProjectCli(res)
{



    var d=parseFloat(res[0]);
    var d1=parseFloat(res[1]);
    var d2=parseFloat(res[2]);



    document.getElementById('itmratenew'+d2).value=d;
    document.getElementById('itmtaxnew'+d2).value=d1;
}

function gettotal(){

    var total=0;
    /* var qty = parseInt(value);*/
    var qty =0
    var rate=0
    var totalcolumn=parseInt(document.getElementById("HidRowCnt").value);
    for(var i=0;i<=totalcolumn;i++){

        if((document.getElementById('quty'+i).value)=="")
        {
            alert("Enter the Quantity") ;
            document.getElementById('quty'+i).focus()
            return false;

        }
        else
        {
            qty= parseFloat((document.getElementById('quty'+i).value));
        }


        if(document.getElementById('itmrate'+i).value!="")
        {
            rate=parseFloat(document.getElementById('itmrate'+i).value);
        }
        else{

            rate=0;
        }
        var  grandtotal =  qty*rate;
        document.getElementById('itmtorate'+i).value=grandtotal
        total+=(qty*rate);
    }

    document.getElementById('totamount').value=total;
}
function gettotalnew(){
    var total=0;
    /* var qty = parseInt(value);*/
    var qty =0
    var rate=0
    var tax=0
    var vat=0
    var totalcolumn=parseInt(document.getElementById("HidRowCntnew").value);
    for(var i=0;i<=totalcolumn;i++){



            if(parseInt(document.getElementById('qutynew'+i).value) > parseInt(document.getElementById('shqutynew'+i).value))
            {
                alert("Check QTY")
                document.getElementById('qutynew'+i).focus();
                return false;
            }
            else{
                qty= parseFloat((document.getElementById('qutynew'+i).value));
            }



        if(document.getElementById('itmratenew'+i).value!="")
        {
            rate=parseFloat(document.getElementById('itmratenew'+i).value);
        }if(document.getElementById('itmvatnew'+i).value!="")
        {
            vat=parseFloat(document.getElementById('itmvatnew'+i).value);
        }
        else{

            vat=0;
        }
       /* alert("qty"+qty)
        alert("rate"+rate)
        alert("vat"+vat)*/
        tax=(qty*rate*vat)/100;

          /* alert("tax:"+parseFloat(tax).toFixed(2))*/
      document.getElementById('itmtaxnew'+i).value=parseFloat(tax).toFixed(2) ;
        var  grandtotal =  qty*rate;
        var  grandtotal2 = grandtotal+(grandtotal*vat)/100;
        document.getElementById('itmtoratenew'+i).value=parseFloat(grandtotal2).toFixed(2);
        total+=grandtotal2;
    }

    document.getElementById('totamountnew').value=parseFloat(total).toFixed(2);
}
function validate()
{
    if(document.formgrn.off.value=="")
    {
        alert("Select Office");
        document.formgrn.off.focus();
        return false;
    }
    if(document.getElementById("btnm").value=="")
    {
          alert("Select Beat")
      document.getElementById("btnm").focus();
        return false;
    }
 if(document.getElementById("outnm").value=="")
    {
        alert("Select Outlet")
      document.getElementById("outnm").focus();
        return false;
    }
    if(document.getElementById("sname").value=="")
    {
        alert("Select Salesman")
      document.getElementById("sname").focus();
        return false;
    }
    if(document.getElementById("grvrefno").value=="")
    {
        alert("Enter The GRV REF NO");
      document.getElementById("grvrefno").focus();
        return false;
    }
    if(document.getElementById("HidRowCnt").value=="")
    {
        alert("Enter the Item Details")
        return false;
    }
    else{
        var totalcolumn=parseInt(document.getElementById("HidRowCnt").value);

    }

    for(var i=0;i<=totalcolumn;i++){
        if(document.getElementById('itemcode'+i).value=='')
        {
            alert("select the item ")
            document.getElementById('itemcode'+i).focus()
            return false;


        } if(document.getElementById('quty'+i).value=='')
    {
        alert("Enter The Item Quantity")
        document.getElementById('quty'+i).focus()
        return false;


    }if(document.getElementById('itmrate'+i).value=='')
    {
        alert("Enter The Item Rate ")
        document.getElementById('itmrate'+i).focus()
        return false;


    }
    }

    if(document.getElementById("HidRowCntnew").value=="")
    {
        alert("Enter the Exchange Item Details")
        return false;
    }
    else{
        var totalcolumn1=parseInt(document.getElementById("HidRowCntnew").value);

    }

    for(var i=0;i<=totalcolumn1;i++){
        if(document.getElementById('itemcodenew'+i).value=='')
        {
            alert("select the item ")
            document.getElementById('itemcodenew'+i).focus()
            return false;


        } if(document.getElementById('qutynew'+i).value=='')
    {
        alert("Enter The Item Quantity")
        document.getElementById('qutynew'+i).focus()
        return false;


    }if(document.getElementById('itmratenew'+i).value=='')
    {
        alert("Enter The Item Rate ")
        document.getElementById('itmratenew'+i).focus()
        return false;


    }
    }
    gettotal();
    var tot=document.getElementById('totamount').value;
    var tot1=document.getElementById('totamountnew').value;
    var totdif1=parseInt(tot1)-parseInt(tot);
    //             alert("DIFF:" +totdif1);

    if(totdif1>10)
    {
        alert("Total Amount exceed");
        document.getElementById('itmratenew').focus()
        return false;

    }

    document.formgrn.action="GrvItemDetailsNewResp.jsp"
    document.formgrn.submit();

}


function alreadyrefno(val)
{  ////alert("val:"+val)
    var off=document.formgrn.off.value
    var url="GrvItemAjax.jsp?grvrefno="+val+"&off="+off+"&rand="+Math.random();
    getData(url,'refnamefun');
    setSeparator("~");

}
function refnamefun(res)
{
    var d=parseInt(res[0]);
    if(d>0)
    {
        alert("already exists")
        document.getElementById("grvrefno").focus();
        return false;

    }
}

</script>

<form name="formgrn" method="post"  background="images/bg.jpg">
<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
<input type="hidden" value="19" id="UCode"  name="UCode">
<tr>                  
    <td height="0" align="left" valign="top">
                                                     

        <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr >
                <td width="1%" background="images/form-M.gif" align="left" valign="top"><img src="images/form-L.gif" width="17" height="23"></td>
                <td class="headingtop1" width="97%" height="25" background="images/form-M.gif">&nbsp;GRV DETAILS  </td>
                <td width="2%" align="right" valign="top" background="images/form-M.gif"><img src="images/form-R.gif" width="21" height="23"></td>
            </tr>
        </table></td>
</tr>
<tr>
<td height="0" align="left" valign="top" bgcolor="#666666"><table width="100%" border="0" cellspacing="1" cellpadding="0">
<tr>
<td height="0" align="center" bgcolor="#FFFFFF"><TABLE cellSpacing=0 cellPadding=0 width="100%"
                                                       align=center border=0>
<TBODY>
<TR bgColor=#F8F7F3>
<TD width="98%" height=45 colSpan=2 bgcolor="#FFFFFF"> <DIV align=center>
<TABLE cellSpacing=1 cellPadding=0 width="100%" align=center border=0>
<TBODY>
<TR align="center" bgColor=#F4F4F4>
    <TD height=25 colspan="2" class="message"><strong></strong></TD>
</TR>


<TR  class="textcon_nobg" bgcolor="#F4F4F4">

     <td align="right" bgColor=#F4F4F4 width="50%">
        <FONT face="Verdana, Arial, Helvetica, sans-serif"  color=#000000 size=1>
            <STRONG> OFFICE&nbsp;
            </STRONG>
        </FONT></td>
    <TD  height=25 align="left" bgcolor="#F4F4F4" class="Form-Text">
        <select name="off" id="off" class="FIELD1" style={width:135;}  OnChange="refresh()">
            <option value=''>Select OFFICE</option>
            <option value='1'>JAYANAGAR</option>
<option value='2'>VVPURAM</option>
<option value='6'>MYSORE</option>



        </select>

    </TD>
</TR>
<TR  class="textcon_nobg" bgcolor="#F4F4F4">
     <td align="right" bgColor=#F4F4F4 width="50%">
        <FONT face="Verdana, Arial, Helvetica, sans-serif"  color=#000000 size=1>
            <STRONG> BEAT&nbsp;&nbsp;
            </STRONG>
        </FONT></td>
    <TD  height=25 align="left" bgcolor="#F4F4F4" class="Form-Text">
        <select name="btnm" id="btnm" class="FIELD1" style={width:135;}  OnChange="refresh()">
            <option value=''>Select Beat</option>
            


        </select>

    </TD>
</TR>

<TR  class="textcon_nobg" bgcolor="#F4F4F4">

    <td align="right" bgColor=#F4F4F4 width="50%">
        <FONT face="Verdana, Arial, Helvetica, sans-serif"  color=#000000 size=1>
            <STRONG> OUTLET&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            </STRONG>
        </FONT></td>
    <TD  height=25 align="left" bgcolor="#F4F4F4" class="Form-Text">

        <select name="outnm" id="outnm" class="FIELD1" style={width:135;}  >
            <option value=''>Select Outlet</option>
            


        </select>

    </TD>
</TR>
<TR >

    <TD  align="right" bgColor=#F4F4F4>
        <FONT face="Verdana, Arial, Helvetica, sans-serif"  color=#000000 size=1>
            <STRONG> SALESMAN NAME&nbsp;&nbsp;&nbsp;&nbsp;
            </STRONG>
        </FONT>
    </TD>
    <TD  height=25 bgColor=#DDDDDD>
        <select name="sname" id="sname" style="width:160" >
            <option value="">Select Salesman </option>
            

        </select>
    </TD>

</TR><TR >

    <TD  align="right" bgColor=#F4F4F4>
        <FONT face="Verdana, Arial, Helvetica, sans-serif"  color=#000000 size=1>
            <STRONG> GRV REF NO:&nbsp;&nbsp;&nbsp;&nbsp;
            </STRONG>
        </FONT>
    </TD>
    <TD  height=25 bgColor=#DDDDDD>
       <input type="text" name="grvrefno" id="grvrefno"  class="box1" onkeypress="numeric()" onblur="alreadyrefno(this.value)"    maxlength=10 >
    </TD>

</TR>



<tr height="30" class="textcon_nobg" bgColor=#F4F4F4>
    <td colspan="2" align="center">

        <strong>

        </strong>
    </td>
</tr>

<tr class="textcon_nobg" bgColor=#F4F4F4>
    <td colspan="2" align="center">

        <strong>
            GRV ITEM DETAILS
        </strong>
    </td>
</tr>


<tr class="textcon_nobg" bgColor=#F4F4F4>
    <td colspan="2" class="righttd" align="right">
        <input name="btnAdd" type="button"  class="buttn7"  value="+" onclick="addRow('tabId');" >
        <input name="btnRemove" type="button" class="buttn7"  value="-"   onclick="delRow('tabId');">
    </td>
</tr>

<TR class="textcon_nobg" bgColor=#F4F4F4>



    <td colspan="2" align="center">
        <table width="600px" border="0">
            <tr>
                <td height="15" width="200px" bgColor=#F4F4F4 align="center" class="HomeText"><FONT
                        face="Verdana, Arial, Helvetica, sans-serif"
                        color=#000000 size=1>ITEM </FONT></td>
                <td height="15" width="100px"  bgColor=#F4F4F4 align="center" class="HomeText"><FONT
                        face="Verdana, Arial, Helvetica, sans-serif"
                        color=#000000 size=1>QTY </FONT></td>
                <td height="15" width="100px" bgColor=#F4F4F4 align="center" class="HomeText"><FONT
                        face="Verdana, Arial, Helvetica, sans-serif"
                        color=#000000 size=1>RATE</FONT>
                </td><td height="15" width="100px" bgColor=#F4F4F4 align="center" class="HomeText"><FONT
                    face="Verdana, Arial, Helvetica, sans-serif"
                    color=#000000 size=1>VALUE</FONT>
            </td>
            </tr>
        </table>
    </td>


</tr>

<tr bgColor=#F4F4F4><td colspan="2" align="center">
    <table width="600px"  border="0" cellpadding="0" cellspacing="2" align="center" name="tabId" id="tabId">
    </table>
</td> </tr>
<input type="hidden" name="HidRowCnt" value="">

<TR class="textcon_nobg" bgColor=#F4F4F4>
    <td height="25" bgColor=#F4F4F4 align="right" class="HomeText">

        &nbsp;&nbsp;&nbsp;&nbsp;</td>

    <TD width="50%" align = "left" height=25 bgColor=#F4F4F4>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;<b>TOTAL</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input name="totamount" id="totamount" style="text-align:right"    type="text"  size="7" readonly   VALUE = "0" maxlength=15>

    </TD>


</tr>

</TBODY></TABLE></DIV></TD></TR>

<tr class="textcon_nobg" bgColor=#F4F4F4>
    <td colspan="2" class="righttd" align="right">
        <input name="btnAdd" type="button"  class="buttn7"  value="+" onclick="addRownew('tabnewId');" >
        <input name="btnRemove" type="button" class="buttn7"  value="-"   onclick="delRownew('tabnewId');">
    </td>
</tr>

<tr class="textcon_nobg" bgColor=#F4F4F4>
    <td colspan="2" align="center">
        <strong>
            EXCHANGE DETAILS
        </strong>
    </td>
</tr>

<TR class="textcon_nobg" bgColor=#F4F4F4>



    <td colspan="2" align="center">
        <table width="750px"  >
            <tr>
                <td height="15" width="200px" bgColor=#F4F4F4 align="left" class="HomeText"><FONT
                        face="Verdana, Arial, Helvetica, sans-serif"
                        color=#000000 size=1>ITEM </FONT></td>
                <td height="15" width="100px"  bgColor=#F4F4F4 align="center" class="HomeText"><FONT
                        face="Verdana, Arial, Helvetica, sans-serif"
                        color=#000000 size=1>SIH </FONT></td>
                <td height="15" width="100px"  bgColor=#F4F4F4 align="center" class="HomeText"><FONT
                        face="Verdana, Arial, Helvetica, sans-serif"
                        color=#000000 size=1>QTY </FONT></td>
                <td height="15" width="100px" bgColor=#F4F4F4 align="center" class="HomeText"><FONT
                        face="Verdana, Arial, Helvetica, sans-serif"
                        color=#000000 size=1>RATE</FONT>
                <td height="15" width="100px" bgColor=#F4F4F4 align="center" class="HomeText"><FONT
                        face="Verdana, Arial, Helvetica, sans-serif"
                        color=#000000 size=1>VAT</FONT>
                <td height="15" width="100px" bgColor=#F4F4F4 align="center" class="HomeText"><FONT
                        face="Verdana, Arial, Helvetica, sans-serif"
                        color=#000000 size=1>TAX</FONT>
                </td><td height="15" width="100px" bgColor=#F4F4F4 align="right" class="HomeText"><FONT
                    face="Verdana, Arial, Helvetica, sans-serif"
                    color=#000000 size=1>VALUE</FONT>
            </td>
            </tr>
        </table>
    </td>


</tr>
<tr bgColor=#F4F4F4><td colspan="2" align="center">
    <table width="600px"  border="0" cellpadding="0" cellspacing="2" align="center" name="tabnewId" id="tabnewId">
    </table>
</td> </tr>
<input type="hidden" name="HidRowCntnew" value="">
<TR class="textcon_nobg"  bgColor=#F4F4F4>
    <td height="25" bgColor=#F4F4F4 align="right" class="HomeText">

        &nbsp;&nbsp;&nbsp;&nbsp;</td>

    <TD width="50%" align = "center"  height=25 bgColor=#F4F4F4>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;<b>TOTAL</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input name="totamountnew" id="totamountnew" style="text-align:right;width:130px;"    type="text"  size="7" readonly   VALUE = "0" maxlength=15>

    </TD>


</tr>



<TR class="textcon_nobg" align="center" bgColor=#F4F4F4>
    <TD height=45 colspan="2" class="Form-Text">


        <input name="Submit"  id="Submit" type=button class="Button"   value=Submit onClick="return validate();">
        <input type="hidden" name="totalcount" value=0>
        &nbsp;


        <input name=Cancel type=button class="Button" value=Cancel onClick="location.href='header.jsp'">
    </TD>
</TR>

</td></tr></TBODY></TABLE></table></td></tr></table></form>
