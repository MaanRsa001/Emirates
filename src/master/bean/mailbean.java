package master.bean;
import java.sql.*;
import java.sql.Connection;
import java.io.IOException;
import java.util.*;
import proj.sql.QueryBuilder;
import java.io.*;
import java.util.*;
import bean.*;
import java.text.*;
import java.util.Date;
import com.maan.services.util.runner;

public class mailbean
{
	String tranid="",error="";
	String master_query="",client_query="";
	String[][] abc=new String[0][0];
	
	int up=0; 
	int nup=0;
   
	 public void setTranid(String tranid)
	{ 
		 this.tranid=tranid;
	}
	
	 public String getTranid()
	{ 
		return tranid;
	}
	
	public void setError(String error)
	 {
		 this.error=error;
    }
	 public String getError()
	 {
		 return error;
    }
   
    


    

public String getMailDetails()
{
		String status="",temptbl="",sql="",sql1="",qry1="",qry2="",qry3="";
		String name="",spec="",email="",sum_ins="",price="",quote_no="",premium="",inception_date="",exception_date="";
		String[][] abc=new String[0][0];
		String[][] result=new String[0][0];
		String values[]=new String[1];
		StringBuffer sb = new StringBuffer("");
		java.text.NumberFormat preFmt = new java.text.DecimalFormat("##,##0") ;
	 	try
		{

		   values[0]=tranid;
		   sql="select bhm.client_name,bpm.BED_RM_NO,bhm.email,bpm.SUM_INSURED,bhm.price,bhm.quote_no,bpm.PREMIUM,to_char(bhm.file_upload_date,'DD-MM-YYYY'),to_char((select add_months(sysdate,12)-1 from dual),'DD-MM-YYYY') from BETTER_HOME_MASTER bhm,BHOMEPREMIUM_MASTER bpm where bhm.tran_id=? and bhm.spec = bpm.spec";
		   //system.out.println("Query is"+sql);
		 	abc = runner.multipleSelection(sql,values);     


			if(abc.length>0)
		   {
                String content="";
				  
	            master.bean.SimpleMail sm=new master.bean.SimpleMail();
				sm.setFrom("betterplus@bhomes.com");

				 for(int x=0;x<abc.length;x++)
			    {
					name="";spec="";email="";sum_ins="";price="";quote_no="";premium="";inception_date="";
                    sb = new StringBuffer("");content="";exception_date="";
					 if(abc[x][0] !=null)
						 name=abc[x][0].trim();

					  if(abc[x][1] !=null)
						 spec=abc[x][1].trim();

					   if(abc[x][2] !=null)
						 email=abc[x][2].trim();

					    if(abc[x][3] !=null)
						 sum_ins=abc[x][3].trim();

						 if(abc[x][4] !=null)
						 price=abc[x][4].trim();

						  if(abc[x][5] !=null)
						 quote_no=abc[x][5].trim();

						   if(abc[x][6] !=null)
						   premium=abc[x][6].trim();

						   if(abc[x][7] !=null)
						   inception_date=abc[x][7].trim();

						   if(abc[x][8] !=null)
						   exception_date=abc[x][8].trim();

                            

				  


				  sb.append(" <html>");
				  sb.append(" <head>");
			      sb.append(" </head>");
			     sb.append("  <meta http-equiv='Content-Type' content='text/html; charset=windows-1252'>");
			      sb.append(" <meta name='GENERATOR' content='Microsoft FrontPage 4.0'>");
			      sb.append(" <meta name='ProgId' content='FrontPage.Editor.Document'>");
			     sb.append("  <meta name='Microsoft Theme' content='rmnsque 1111'>");
			      
				   sb.append(" <table border='0' width='100%' height='50' align='center' bgcolor='White'>");
				   sb.append("  <tr >");
				   sb.append(" <td align = 'left'>");
				  sb.append("  <table border='0' bgcolor='White' bordercolor='White' width='100%'"); 
				  sb.append("align='center'>");
				 sb.append("  <tr height='10'><td>");
				  sb.append("  <tr height='10'>");
				   sb.append(" <td  width='25%' bgcolor='White' align='center'><img height='60' width='120' src='http://www.insureuae.com/bhomes/images/logo_rsa.gif' /></td>");
				   sb.append(" <td bgcolor='White'  width='50%' align='center'><font  face = 'Arial'  size =4 color='#E3319D'><b>");
				 sb.append("   &nbsp;&nbsp;&nbsp;&nbsp;BETTER HOMES CONTENTS INSURANCE ESTIMATE</b></font></td>");
				  sb.append("  <td width='25%' bgcolor='White' align='center'>");
				  sb.append(" <img height='55' width='175' src='http://www.insureuae.com/bhomes/images/bplus-logo.jpg'/> </td>");
					sb.append(" </tr>");
					sb.append(" </td>");
					sb.append(" </tr>");
					sb.append(" </table>");
				   
				   sb.append(" </td> <tr>");
				/*  sb.append("  <tr height='50'>");
				   sb.append(" <td align = 'center' ><b><font  size='3'  face='CENTURY SCHOOLBOOK'>Quote Information</font> </td> </tr>");*/
				   sb.append(" <tr><td>");
				   sb.append(" <table border='0' width='90%' height='50' align='center' bgcolor='White'>");
                   sb.append(" <tr height='20'> <td width='100%' align='left'> </td><tr>");
		        //  sb.append(" <tr height='10'> <td width='100%' align='left'> <font face = 'Arial'  size =2  >Dear "+name+", </font></td>  <tr>");
					
					
					  sb.append(" <tr height='5'> <td width='100%' align='left'> </td><tr>");
					  
					 sb.append(" <tr height='10'> <td width='100%' align='left'> <font face = 'Arial'  size =4  ><b>Safe Guard Your Home Contents</b></font></td>  <tr>");
					    sb.append(" <tr height='5'> <td width='100%' align='left'> </td><tr>");
						
				  sb.append(" <tr height='10'> <td width='100%' align='left'> <font face = 'Arial'  size =2  >As a client of Better Homes you are entitled to preferential rates to insure your home contents through RSA Insurance, one of the Worlds leading insurance groups. If you would like to take advantage of this offer, please call us on <b>+ 971 4 <font face = 'Arial'  size =2>407 4600</font></b> within 7 days to validate the offer quoting reference number <b><font face = 'Arial'  size =2>"+quote_no+".</font></b></font></td> <tr>");
				  sb.append(" <tr height='5'> <td width='100%' align='left'> </td><tr>");
				   sb.append(" <tr height='10'> <td width='100%' align='left'> <font face = 'Arial'  size =2  >The process is simple and takes only a few minutes to confirm full cover via e-mail. (We strongly recommend that your home contents are adequately insured by a reputable insurance company).</font></td> </tr>");
				  //sb.append(" <tr height='5'> <td width='100%' align='left'> </td><tr>");

				  sb.append("</table>");
				  sb.append(" </td></tr>");
                  
				 sb.append("  <tr><td>");
			      sb.append(" <table border='0' width='90%' height='50' align='center' bgcolor='White'>");
                    
		         
					sb.append("  <tr height='10'> <td width='45%' align='left'> <font face = 'Arial' size = 2 ><b>Your Premium</b></font></td> <td width='10%'> <td width='45%'><font face = 'Arial'  size = 2 ></font></td></tr>");
					//sb.append("  <tr height='5'> <td width='45%' align='center'> </td> <td width='10%'> <td width='45%'></td></tr>");
					 
					sb.append("<tr height='10'> <td width='100%' align='left'><font face = 'Arial'  size = 2 >Based on the information below, your estimated premium will be only <b>AED "+(premium.length()>0?preFmt.format(Double.parseDouble(premium)):premium)+".</b></font></td></tr>");
                   // sb.append("    <tr height='5'> <td width='45%' align='center'> </td> <td width='10%'> <td width='45%'></td></tr>");
						
						
				    sb.append(" </table>");

					sb.append(" </td></tr>");

					 sb.append("  <tr><td>");
			      sb.append(" <table border='0' width='90%' height='50' align='center' bgcolor='White'>");
                    
		         
					sb.append("  <tr height='10'> <td width='45%' align='left'> <font face = 'Arial' size = 2 ><b>Your Information</b></font></td> <td width='10%'> <td width='45%'><font face = 'Arial'  size = 2 ></font></td></tr>");
					//sb.append("  <tr height='5'> <td width='45%' align='center'> </td> <td width='10%'> <td width='45%'></td></tr>");
					 
					sb.append("<tr height='10'> <td width='45%' align='left'><font face = 'Arial'  size = 2 >Number of bedroom in your home: <b>"+spec+"</b></font></td></tr>");
                   // sb.append("    <tr height='5'> <td width='45%' align='center'> </td> <td width='10%'> <td width='45%'></td></tr>");
					sb.append("<tr height='10'> <td width='45%' align='left'><font face = 'Arial'  size = 2 >Value of your contents: <b>AED "+(sum_ins.length()>0?preFmt.format(Double.parseDouble(sum_ins)):sum_ins)+"</b></font></td></tr>");	
					 //sb.append("    <tr height='5'> <td width='45%' align='center'> </td> <td width='10%'> <td width='45%'></td></tr>");	
				    sb.append(" </table>");

					sb.append(" </td></tr>");

					 sb.append("  <tr><td>");
			      sb.append(" <table border='0' width='90%' height='50' align='center' bgcolor='White'>");
                    
		         
					sb.append("  <tr height='10'> <td width='45%' align='left'> <font face = 'Arial' size = 2 ><b>Your cover includes:</b></font></td> <td width='10%'> <td width='45%'><font face = 'Arial'  size = 2 ></font></td></tr>");
					//sb.append("  <tr height='5'> <td width='45%' align='center'> </td> <td width='10%'> <td width='45%'></td></tr>");
					 
					sb.append("<tr height='10'> <td width='100%' align='left'><font face = 'Arial'  size = 2 >1) Home contents against fire, theft, natural calamities and accidental loss or damage.</font></td></tr>");
                   	sb.append("<tr height='10'> <td width='100%' align='left'><font face = 'Arial'  size = 2 >2) 'New for Old' cover.</font></td></tr>");
					sb.append("<tr height='10'> <td width='100%' align='left'><font face = 'Arial'  size = 2 >3) Cover up to AED 1,000 for your money whilst you are at home.</font></td></tr>");
					sb.append("<tr height='10'> <td width='100%' align='left'><font face = 'Arial'  size = 2 >4) Cover for breakage of mirrors, glass tops and fixed glass in furniture.</font></td></tr>");
					sb.append("<tr height='10'> <td width='100%' align='left'><font face = 'Arial'  size = 2 >5) Cover up to AED 2,500  for deterioration of food in your freezer due to accidental power failure.</font></td></tr>");
					sb.append("<tr height='10'> <td width='100%' align='left'><font face = 'Arial'  size = 2 >6) Accidental loss or damage to contents during transit.</font></td></tr>");

                    sb.append("    <tr height='5'> <td width='45%' align='center'> </td> <td width='10%'> <td width='45%'></td></tr>");	
						
				    sb.append(" </table>");

					sb.append(" </td></tr>");
					
				/*	sb.append("  <tr><td>");
			       sb.append(" <table border='0' width='90%' height='50' align='center' bgcolor='White'>");
                    
		         				
					sb.append("  <tr height='5'> <td width='45%' align='center'> </td> <td width='10%'> <td width='45%'></td></tr>");
					sb.append("  <tr height='5'> <td width='45%' align='center'> </td> <td width='10%'> <td width='45%'></td></tr>");
					 
					
										
				    sb.append(" </table>");

					sb.append(" </td></tr>"); */

					sb.append("  <tr><td>");
			       sb.append(" <table border='0' width='90%' height='30' align='center' bgcolor='White'>");
                    
		         	/*			
					sb.append("  <tr height='5'> <td width='45%' align='center'> </td> <td width='10%'> <td width='45%'></td></tr>");
					sb.append("  <tr height='5'> <td width='45%' align='center'> </td> <td width='10%'> <td width='45%'></td></tr>");
					 */
					sb.append("<br><tr height='20'> <td width='100%' align='left'><font face = 'Arial'  size = 2 ><b>Please call Better Plus on +971 4 407 4600, to take advantage of your preferred insurance offer.</b></font></td></tr>");
                   // sb.append("    <tr height='5'> <td width='45%' align='center'> </td> <td width='10%'> <td width='45%'></td></tr>");
										
				    sb.append(" </table>");

					sb.append(" </td></tr>");
                   
				sb.append("<br><tr height='10'> <td width='100%' align='left'> <font face = 'Arial'  size =2  ><b>&nbsp;&nbsp;Kind regards,</b><br><b>&nbsp;&nbsp;Better Plus Team</b>");
                 sb.append("  <br>&nbsp;&nbsp;Tel. No. +971 4 407 4600  <br>&nbsp;&nbsp;betterplus@bhomes.com</b><br><br><b> Kindly contact Better Plus for all your insurance needs.</b></font></td> <tr>");
				
			//	sb.append(" <tr height='10'> <td width='100%' align='left'> </td> <tr>");

				 sb.append("<br><tr height='10'> <td width='100%' align='left'> <font face = 'Arial'  size = 1 >The quotes generated by this program are not a contract, binder or agreement to extend insurance coverage and are based on information you have supplied. The above estimate and cover summary is subject to RSA underwriting approval, terms and conditions and subject to applicable federal law.</font></td></tr>");
                    //sb.append("    <tr height='5'> <td width='45%' align='center'> </td> <td width='10%'> <td width='45%'></td></tr>");


					sb.append(" </table>");
                   sb.append(" </td></tr>");
					
					sb.append("  <tr height='40' >");
				  sb.append("  <td align = 'left'></td></tr></Table>");
			     sb.append("  </html>");
                 
                 


               
                 
                    sm.setTo(email);
					sm.setSubject("Your personal Better Homes insurance estimate");
					content=sb.toString();
					sm.setContent(content);
                
	             try
	                 {
		                //system.out.println("mail ready");
		                sm.send();
		                 //system.out.println("mail send");
                         sql1="update BETTER_HOME_MASTER set email_status='Y'";
						 //system.out.println("query is"+sql1);
						 runner.updation(sql1);

	                   }
	                      catch(Exception e)
	                       {
		                       System.out.println("Error while sending the report for file: "+e);
	                            }

					 

				 }// for loop

		    }// abc length

			
		 }catch(Exception e){
                       System.out.println("prob in connnnnnnnnnn to get product master list"+e);
					   e.printStackTrace();
	                }



	

                      
	   

   return "Successfully uploaded";


	} 





}
