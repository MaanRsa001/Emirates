package bean;
import java.sql.*;
import java.sql.Connection;
import java.io.IOException;
import java.util.*;
import java.util.Random;
import com.maan.services.util.runner;
import proj.sql.QueryBuilder;

public class getMaster_Details
{
	Connection conn=null;
	Statement smt=null;
  	String qry=null;
	String data=null;
	int Tid=0;

	public void setConn(Connection con)
	{
           this.conn=con;
	}
	public Connection getConn()
    {
		return conn;
	}
	  
    public String[][] MasterDetails(int Tranid){
    String[][] abc=new String[0][0];
      String sql="";
	  String values[]=new String[1];
	  values[0]=""+Tranid;
	  
	 try{
		   sql="select FILE_ID,TABLE_NAME,PATH,PRODUCT_ID,MAX_ROW from BHOME_NEWTRANSACTION_MASTER where TRAN_ID=? order by FILE_ID";
		  
		 abc = runner.multipleSelection(sql,values);     
	       
	     }catch(Exception e){
          System.out.println("error"+e);
	  }
				
          return abc;
 }



 public String datastatus(int tid,int file_id, String uploaded,String notuploaded,String error,String rowstatus,String unknowncolumn,String uploadstatus,String avayacolumnstatus)
	{
	 int maxrow=0;
	 String values[]=new String[10];
	     try{
	 	  maxrow= Integer.parseInt(uploaded) + Integer.parseInt(notuploaded);
		  //system.out.println("max rows"+maxrow);
		   }catch(Exception e){}
		  

	      try{
			  //system.out.println("connnnnnnnnnnnnn in getmaster details data staus");
		 values[0]=uploaded;	   
		 values[1]=notuploaded;
		 values[2]=error;
		 values[3]=rowstatus;
		 values[4]=unknowncolumn;
		 values[5]=uploadstatus;
		 values[6]=""+maxrow;
		 values[7]=avayacolumnstatus;
		 values[8]=""+tid;
		 values[9]=""+file_id;
		        
          String query2="update BHOME_NEWTRANSACTION_MASTER set UPLOADED=?,NOT_UPLOADED=?,ERROR=?,ROW_STATUS=?,UNKNOWN_COLUMNS=?,UPLOAD_STATUS=?,MAX_ROW=?,AVAYA_DETAILS=? where TRAN_ID=? AND  FILE_ID=?";
		  //system.out.println("Query is"+query2);
		  runner.multipleUpdation(query2,values);  
		  }
	          catch(Exception e){
                 System.out.println("error"+e);
	  }
	 
				  return "success";
	  }


	  public String[][] TranProcessDetails(int Tranid){
      String[][] abc=new String[0][0];
      String sql="";
	  String values[]=new String[1];
	 values[0]=""+Tranid;
	  
	 try{
		
		  sql="select FILE_ID,TABLE_NAME,FILE_NAME,MAX_ROW,DATAUPDATED,DELETE_STATUS,UPLOADED,NOT_UPLOADED,ERROR,ROW_STATUS,UNKNOWN_COLUMNS,UPLOAD_STATUS from RSA_NEWTRANSACTION_MASTER where TRAN_ID=? order by FILE_ID";
		  
		 //system.out.println("Query is"+sql);
		 abc =runner.multipleSelection(sql,values);     
		       
	     }catch(Exception e){
              System.out.println("error"+e);
	    }
	 		
          return abc;
    }

// while starting thr upload process the upload_status changed to 'P'

public String UpdateProcessStatus(int Tranid)
	{

	      String sql="";
	  String values[]=new String[1];
	 values[0]=""+Tranid;
	  
	  
	 try{
		 
		  sql="update BHOME_NEWTRANSACTION_MASTER set UPLOAD_STATUS='N' where TRAN_ID=?";
		  runner.multipleUpdation(sql,values); 	
		 
	     //system.out.println("Query is"+sql);
		        
	     }catch(Exception e){
	           System.out.println("error"+e);

	  }
	 
				  return "success";
	  }



 public String[][] getTable_name(String[] xlfiles){
      String[][] abc=new String[xlfiles.length][3];
      String sql="";
	  ResultSet rsnew =null;
	  
	  
	 try{
		 smt = conn.createStatement();
        // QueryBuilder qry = new QueryBuilder(smt); 
	    //system.out.println("connnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn");
		 //system.out.println("xlfiles length"+xlfiles.length); 
		
		for(int x=0;x<xlfiles.length;x++)
		 {
		  sql="SELECT PRODUCT_ID,TABLE_NAME FROM RSA_FILENAME_MASTER WHERE FILE_NAME LIKE '"+xlfiles[x]+"'";
	        //system.out.println("Query is"+sql);
		// abc = qry.getResultSet(sql);  
		                    try{
			                        rsnew =smt.executeQuery(sql);
									while(rsnew.next())
								    {
									abc[x][0]=xlfiles[x];
									abc[x][1]=String.valueOf(rsnew.getInt(1));
									abc[x][2]=rsnew.getString(2);
									}
								   } catch(Exception e){System.out.println("problem in batch execution"+e);}
		 
	    // System.out.println("Query is"+sql);
		 }
		 //system.out.println("abc length"+abc.length);
		
	       
	     }catch(Exception e){
  System.out.println("error"+e);
	  }
	  finally
	                {
	              try{
			    if(rsnew!=null)
					rsnew.close();
				if(smt!=null)
					smt.close();
				//if(conn!=null)
					//conn.close();
				
	              }
                  catch(Exception e){ }}


			
          return abc;
 }


// product master details
  public String[][] getProductMasterDetails(){
      String[][] abc=new String[0][0];
      String sql="";
	  
	  
	  
	 try{
		   	  
		  sql="select PRODUCT_NAME,PRODUCT_ID,TABLE_NAME from BHOMEPRODUCT_MASTER order by product_id";
		  
		 abc = runner.multipleSelection(sql,new String[0]);     
		 
	     //system.out.println("Query is"+sql);
		 //system.out.println("abc length"+abc.length);
		
	       
	     }catch(Exception e){
  System.out.println("error"+e);
	  }
	 			
          return abc;
 }



}