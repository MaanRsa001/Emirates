package master.bean;
import java.sql.*;
import java.sql.Connection;
import java.io.IOException;
import java.util.*;
import java.util.Random;
import proj.sql.QueryBuilder;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.*;
import java.util.*;
import com.maan.DBCon.DBConnection;
import bean.*;
import java.text.*;
import java.util.Date;
import com.maan.services.util.runner;

public class updatePremium
{
	Connection conn=null,con=null;
	Statement smt=null,smt1=null,stmt=null;
	String tranid="",error="";
	int product_id=0,uploaded=0,notuploaded=0,total=0,file_id=0;
	String master_query="",client_query="";
	String[][] abc=new String[0][0];
	int up=0; 
	int nup=0;
    public void setConn(Connection con)
	{
		this.conn=con;
	}
	public Connection getConn()
	{
		return conn;
	}
		
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
   
    
 public String TestUpdate()
	{
 String status="",temptbl="",sql="",sql1="",qry1="",qry2="",qry3="";
 uploaded=0;notuploaded=0;error="";
 int row=0,up=0,nup=0;
  String[][] abc=new String[0][0];
   String[][] result=new String[0][0];
   String values[]=new String[1];
		



	//premium and quote no updation in BETTER_HOME_MASTER  table

	try{
		
	       values[0]=tranid;
		   sql="select rowid from BETTER_HOME_MASTER where tran_id=?";
		   //system.out.println("Query is"+sql);
		 	abc =runner.multipleSelection(sql,values);     
		    //system.out.println("Query is"+sql);
		    //system.out.println("abc length"+abc.length);

			if(abc.length>0)
		   {
				 for(int x=0;x<abc.length;x++)
			    {
					 int logno=0;
					 // premium updation
					 try{
					 values=new String[6];
					 values[0]=abc[x][0].trim();
					 values[1]=tranid;
					 values[2]=abc[x][0].trim();
					 values[3]=tranid;
					 values[4]=tranid;
					 values[5]=abc[x][0].trim();
			    	 sql1="update BETTER_HOME_MASTER set PREMIUM_BASED_ON_SPEC= (select  a.premium from BHOMEPREMIUM_MASTER a, BETTER_HOME_MASTER b where a.spec=b.spec and b.rowid=? and  b.tran_id=?),SUM_INSURED= (select  a.sum_insured from BHOMEPREMIUM_MASTER a, BETTER_HOME_MASTER b where a.spec=b.spec and b.rowid=? and  b.tran_id=?) where tran_id=? and rowid=?";
        	 	    //system.out.println("Query is"+sql1);
	        	 	runner.multipleUpdation(sql1,values);	
					 }catch(Exception e){ System.out.println("error to update premium"+e);}


                    // quote no updation
					try{
					values=new String[0];
                    qry1="select CURRENT_NO from BHM_POLICYNO_GENERATE where TYPE_ID=2";
		             //system.out.println("query is"+qry1);
		             result=runner.multipleSelection(qry1,values);
		             if(result.length>0)
			         { 
			             logno=Integer.parseInt(result[0][0]);
			             logno++;

						 values=new String[2];
						 values[0]=""+logno;
                         values[1]=""+logno;
                         qry2="UPDATE BHM_POLICYNO_GENERATE SET CURRENT_NO =?,REMARKS=? where TYPE_ID=2 ";
						 //system.out.println("query is"+qry2);
						 runner.multipleUpdation(qry2,values);
			             //smt.executeUpdate(qry2);

                         values[1]=abc[x][0].trim();
						 qry3="update BETTER_HOME_MASTER set QUOTE_NO=?,FILE_UPLOAD_DATE=SYSDATE where rowid=?";
						 //system.out.println("query is"+qry3);
						 runner.multipleUpdation(qry3,values);
						 //smt.executeUpdate(qry3);

			           }
                       }catch(Exception e){ System.out.println("error to update quoteno"+e);}

				 }// for loop

		    }// abc length

			
		 }catch(Exception e){
                       System.out.println("prob in connnnnnnnnnn to get product master list"+e);
					   e.printStackTrace();
	                }



		                  
	   

   return "Successfully uploaded";


	} 





}
