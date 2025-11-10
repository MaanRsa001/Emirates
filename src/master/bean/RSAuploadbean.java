package master.bean;
//import com.bitmechanic.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.sql.ResultSetMetaData;
import java.util.*;
import proj.sql.QueryBuilder;
import java.io.*;
import bean.*;

//import bean.DBConnection;
public class RSAuploadbean
{
		Connection con=null;
		String name,path,dbname,startdate,enddate,cid,cycle_date=null,rcode_status=null,file_name=null;
		int no,rs1,batch_id=0;
        
	 	public void setCon(Connection con)
	    {
			this.con=con;
	    }
	   public void setPath(String path)
	   {  
		   this.path=path;
	   }
	   public void setDbname(String dbname)
	  {
		   this.dbname=dbname;
	   }
	   public void setStartdate(String startdate)
	 { 
		    this.startdate=startdate;
	 }
	  public void setEnddate(String enddate)
	 { 
		    this.enddate=enddate;
	 }
	 public void setCid(String cid)
	{ 
		 this.cid=cid;
	}
	public void setCycle_date(String cycle_date)
	{ 
		this.cycle_date=cycle_date;
	}
	public void setBatch_id(int batch_id)
	{ 
		this.batch_id=batch_id;
	}
	public void setRcode_status(String rcode_status)
	{ 
		this.rcode_status=rcode_status;
	}
	public void setFile_name(String file_name)
	{ 
		this.file_name=file_name;
	}

	   public Connection getCon()
	   {
			return con;
	    }
	    public String getPath()
	   {  
		    return path;
	   }
	   public String getDbname()
	   {  
		    return dbname;
	   }
	    public String getStartdate()
	 { 
		    return startdate;
	 }
	  public String getEnddate()
	 { 
		    return enddate;
	 }
    public String getCid()
	{ 
		return cid;
	}
	public String getCycle_date()
	{
		return cycle_date;
	}
	public int getBatch_id()
	{
		return batch_id;
	}
	public String getRcode_status()
	{
		return rcode_status;
	}
	public String getFile_name()
	{
		return file_name;
	}


          public synchronized String UploadFields(String login)
	        {
			  Connection conn=null;
			  Statement stmt=null;
	     	  Statement stmt1=null,stmt2=null;
		      ResultSet rs=null;ResultSet rss=null;
			  boolean sts=false,exe=false,exe1=false,exe2=false,exe3=false,exe4=false,exe5=false,checkrow=true;
			  String fname="",checkval;String mname="",rowstatus="",error="",Temp="", colname="",qry="",getvarchar="";int xyz=0;
			  int uploaded=0,notuploaded=0,row=0,cut=0;
				  int ab=0,bc=0,cd=0,de=0,ef=0,fg=0,no=1000,length=0,maxrow=100;
			  double abc=0;
			  String table_name="",tempqry="",deletestatus="",errorpath="",errorstatus="",uploadstatus="",rtsts="";
			  String sheetnames[]={"sheet1"};
			  //validator v=new validator();
			  int batch=batchno();



			  
				                        
							      if("new_policy_details".equalsIgnoreCase(dbname))
				                           no=0; 
								        else if("payment_details".equalsIgnoreCase(dbname))
				                             no=0; 
										        
				 //linux ("/")  windows ("\\") we have to change						
				 int idx=path.lastIndexOf("/");	//linux upload				
				// int idx=path.lastIndexOf("\\"); // Local					
				 String folderpath=path.substring(0,idx+1);
				 System.out.println("folder path"+folderpath);
		      
	             try { 
	               //Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				   Class.forName("org.relique.jdbc.csv.CsvDriver");
				  // path=path.replace('/','\\');
				 //  System.out.println("con"+con+"path"+path);
                  // conn = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Excel Driver (*.xls)};DBQ="+path);
	              // stmt  = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);  
                  // conn = DriverManager.getConnection("jdbc:relique:csv:D:\\Tomcat 5.0\\webapps\\BetterHome\\RSAuploadedfiles\\" );
				   conn = DriverManager.getConnection("jdbc:relique:csv:"+folderpath);
				   stmt = conn.createStatement();
				  // System.out.println("con"+conn);
				      }//end of 1st try
                   catch(Exception e){
                   System.out.println("db error in excel driver =  "+e);
					   e.printStackTrace();
                                              }
	 try{
                 try{
				   //rs=stmt.executeQuery("select * from ["+sheetnames[no]+"$]");
				   //rs=stmt.executeQuery("select * from [sheet1$]");
				   rs=stmt.executeQuery("SELECT * FROM "+file_name.trim());
				     } catch(Exception e){
                            System.out.println("xlsheet error  =  "+e);
							e.printStackTrace();
							error+="Please change the xl sheetname as - "+sheetnames[no];
                                              }
				   ResultSetMetaData  rsMetaData = rs.getMetaData();
				   int numberOfColumns=rsMetaData.getColumnCount();
				   System.out.println("resultSet MetaData column Count=" + numberOfColumns);
				
				    //DB file column count
					//if(con == null)
                       // throw new Exception("connnnnnnnnection is nullllll");
					//   System.out.println("Connectios is"+con);
					   
					//con.setAutoCommit(false);
					
					stmt1=con.createStatement();
					
                    
					
					rss=stmt1.executeQuery("select * from "+dbname);
					//System.out.println("cusrsor name"+rss.getCursorName());
					
				    ResultSetMetaData  rsMetaDB = rss.getMetaData();
				   int numberOfColumnsDB=rsMetaDB.getColumnCount();
				   System.out.println("resultSet MetaData column Count from db=" + numberOfColumnsDB);
				   tempqry=dbname+"(";
				   // if rcode column in db we have to omitt before reading xlfile
				   if("rcode".equalsIgnoreCase(rcode_status))
	               {
					    numberOfColumnsDB-=7;
				   }
                   // if  approved then we have to omitt 4 columns on db 
				   if("appr".equalsIgnoreCase(rcode_status))
	               {
					    numberOfColumnsDB-=7;
				   }
				  for (int i = 1; i <= numberOfColumnsDB; i++) {
				 colname=rsMetaDB.getColumnName(i);
				 tempqry+=colname.trim()+",";
				 int x=rsMetaDB.getColumnType(i);
				 int y=rsMetaDB.getPrecision(i);
				 
				 table_name=rsMetaDB.getTableName(i);
				 
				 System.out.println("colname"+colname);
				 System.out.println("type"+x);
				System.out.println("max no's"+y);
				System.out.println("precision type"+rsMetaDB.getColumnDisplaySize(i));
				System.out.println(rsMetaDB.isAutoIncrement(i));
				System.out.println(rsMetaDB.isNullable(i));
				  }
				  if("appr".equalsIgnoreCase(rcode_status))
	               {
					    tempqry+="tran_id";
						numberOfColumnsDB+=1;
				   }
				  tempqry=tempqry.substring(0,(tempqry.length() - 0))+")";

				   
				   if((numberOfColumns+1)==numberOfColumnsDB)
												  {				
					   
					    //deletestatus=deletetable(dbname);
						
      
			           
					 
					while(rs.next() && checkrow )
				 {
						
						row++;
					System.out.println("row is"+row+"no of cols"+numberOfColumnsDB);	
						
						try{
                   for (int i = 1; i <=numberOfColumns; i++) {
					   
					  

				colname=rsMetaData.getColumnName(i);
				int x=12;int y=200;
			   
				
		   		

		

          //VarChar Validation
		            		if(x==12) {
								          //telephone no omitting
							              if(i==4 && "appr".equalsIgnoreCase(rcode_status))
								         {
											   fname="'',";
										 }else
								         {
										  try{
									      getvarchar=(String)(rs.getString(colname));
										  }catch(Exception e){System.out.println("error"+e);}
                                           System.out.println("coming after");
										   if(getvarchar!=null && getvarchar.length()>0)
								           {
										  getvarchar=getvarchar.replaceAll("'","");
										  //getvarchar=getvarchar.replaceAll(" ","");
										  fname="'"+getvarchar.trim()+"',";
										  }
										  else
								            fname="'',";
										 }//appr else

										 System.out.println("varchar name"+fname);
										  mname+=fname.trim();
						                  exe1=true; bc++;
										
					               }
	
									 
    
		
        

                          
			           	  
				     }//for loop
                //checking no values availabe on row
				  System.out.println("stringval"+mname);
               //    System.out.println("mname value"+mname.length());
				   if(mname.length()<=numberOfColumns*4)
							{ checkrow=false; row=row-1;}
				  //mname=mname.substring(0,(mname.length() - 1));
				  mname+=batch;
				 // mname+=","+cid;
				  System.out.println("stringval"+mname);
				
				 
				 
			
                   
                      

				 if  (exe1  && checkrow)
							{ 
					      // con.setAutoCommit(false);
 
					        stmt2=con.createStatement();
								//System.out.println("row is function"+ rs.getRow());	
								
 				         System.out.println("insert into "+tempqry+" values("+mname+")");
						 					  
						     //System.out.println("row is"+row);	
							//	System.out.println("maxrow is"+ maxrow);
								
								// stmt1.addBatch("insert into "+tempqry+" values("+mname+")");
						 /* if(row==3)
								{
							      System.out.println("comes to batch"+row);
							      int[] updateCounts = stmt1.executeBatch();
								  System.out.println("comes to batch"+updateCounts.length);
                                    stmt1.clearBatch();
									maxrow+=100;
									
					          rs1=stmt2.executeUpdate("insert into "+qry+" values("+mname+")");
								}*/
								rs1=stmt2.executeUpdate("insert into "+tempqry+" values("+mname+")");
							  uploaded++;
							}
				 
				  sts=true;
				  mname="";
				  fname="";
				  xyz=0;
				  
				 }//end of 3rd try
				  
				 catch (  SQLException se  )  
                         {  
                                        System.out.println (  "SQL Exception:"  )  ; 
  
                                     // Loop through the SQL Exceptions 
                                       while (  se != null  )  
                                       {   
                                            System.out.println (  "State : " + se.getSQLState (  )    )  ; 
                                            System.out.println (  "Message: " + se.getMessage (  )     )  ; 
											
		                                    String erstatus=se.getMessage (  ); 
											int index = erstatus.lastIndexOf(":");
                                            erstatus=erstatus.substring(index+1,erstatus.length());
											System.out.println (  "Error status : " + erstatus  )  ; 
											if(se.getSQLState().equals("23000") && se.getErrorCode()==1)
										    {
												erstatus = "already exist in the database.";
											}
                                            error+="<br><font color='blue'>row"+(row)+" "+erstatus+"</font><br>";
                                            System.out.println (  "Error : " + se.getErrorCode (  )   )  ; 
                                            se = se.getNextException (  )  ; 
                                         }   
										 mname="";
				                        fname="";
				                        notuploaded++;
				                        //rowstatus+=(row+1)+",";
				                       System.out.println("prob in row  =  "+(row));
				                       sts=true;
                         }  


                  catch(Exception e){
				  System.out.println("db error in oracle =  "+e);e.printStackTrace();
				  //error+="<br>error in coulmn name <font color='red'>  "+colname+" </font>row no  <font color='red'> "+(row+1)+"</font><br>";
				  mname="";
				  fname="";
				  notuploaded++;
				   rowstatus+=(row+1)+",";

				  sts=true;
				 

		           	 }
					 

				 
	                
			  }//end of while loop
			                     /*  try{
			                         int[] updateCounts = stmt1.executeBatch();
									 
								  System.out.println("comes to batch"+updateCounts.length);
                                    stmt1.clearBatch();
									
								   } catch(Exception e){ System.out.println("geting problem in exe batch"+e); sts=false;}*/
				  
			    }//end of if cond(num of column)
				   else
					   sts=false;
		  
			 }//end of 2nd try
			 catch(Exception ey){ }
			 finally
	                {
	              try{
			//	qry=null;
				if(rs!=null)
					rs.close();
				if(rss!=null)
					rss.close();
				if(stmt!=null)
				  stmt.close();
				 if(stmt1!=null)
				  stmt1.close();
				if(stmt2!=null)
					stmt2.close();
				if(conn!=null)
					conn.close();
				
	              }
                  catch(Exception e){ }}
				 if(sts){
					       
						   //String uploaddate="'startdate "+startdate+" enddate "+enddate+"'";
						 //  System.out.println("uploaddate is"+cycle_date);
					       String returnval="<br><br><b><font color=#cd0084 size=3>Successfully uploaded count - "+uploaded+".</b></font><br><br><font color=red size=2><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Warning - </b>"+(notuploaded)+" records already available in the database.</font><br>";
						  
						   if(notuploaded>0)
							   returnval+= "<br>"+rowstatus;
						  
						   // error is there,create a new file and write
						    if(error.length()>0)
					        {    
							  // returnval+="<br>"+error;
						       	FileInput FI=new FileInput();
						        errorpath=path+"error.txt";
						        FI.WriteFiles(errorpath,error);
							    errorstatus=errorpath;
							    uploadstatus="E";
						    }
						      else 
					               {  errorstatus=error; uploadstatus="C";}

							   rtsts=upload(batch,row,uploaded,notuploaded,dbname,path,errorstatus,uploadstatus,login);
						  

						   return returnval;
						   //return "<br>Upload DB Status  "+rtsts+"<br>Deleted rows "+deletestatus+" <br>Total No.of rows "+row+"<br>successfully updated rows count is  "+uploaded+"<br>Problematic rows count is   "+(notuploaded)+"<br> row nos are "+rowstatus +"*error"+error;
				         }
				 else
                           	{
					             String returnval="";
					             if(error.length()>0)
                                       returnval+="Error  <font color=green>"+error+"</font>";
								      else 
										   returnval+="<br>Column count mismatch*" ;

                                   FileInput FI=new FileInput();
						           errorpath=folderpath+"error.txt";
						           FI.WriteFiles(errorpath,error);
							       errorstatus=errorpath;
							       uploadstatus="E";
                                   rtsts=upload(batch,row,uploaded,notuploaded,dbname,path,errorstatus,uploadstatus,login);
								  return returnval;
							}
                         									
  				 
	                }

//    getting maxmium no of upload_id

	       public int batchno()
	        {
			int batch=0;
			Statement smt=null;
			ResultSet bn=null;
			String query="select max(tran_id) from BHOME_NEWTRANSACTION_MASTER";
			try{
			    smt=con.createStatement();
			    bn=smt.executeQuery(query);
			    while(bn.next()){
				  batch=bn.getInt(1);
			    }
			    if(batch==0){
				   batch=101;
			    }
			    else{
				   batch=batch+1;
			    }
		    }
            catch(Exception e){
                //out.println("testing total number of records "+e);
		   }
		   finally{
			  try{
				if(bn!=null)
					bn.close();
				if(smt!=null)
					smt.close();
				 }
              catch(Exception e){
              }
		   }
		   setBatch_id(batch);
	      return batch;
	        }
 //insert upload details to upload_master table   
	     public String upload(int batchid,int row,int uploaded,int notuploaded,String table_name,String path,String err,String up_status,String login)
	        {
			boolean upsts=false;
			Statement smt1=null;
			int bn;
			//String remarks="'Row-"+row+" Uploaded-"+uploaded+" NotUploaded-"+notuploaded+deletestatus+"'";
			String query="insert into BHOME_NEWTRANSACTION_MASTER(tran_id,transaction_date,user_name,table_name,file_name,path,product_id,max_row,uploaded,not_uploaded,upload_status,error)values("+batchid+",sysdate,'"+login+"','"+table_name+"','"+file_name+"','"+path+"',101,'"+(""+row)+"','"+(""+uploaded)+"','"+(""+notuploaded)+"','"+up_status+"','"+err+"')";
			System.out.println("Query is "+query);
			try{
			    smt1=con.createStatement();
				
			     bn=smt1.executeUpdate(query);  
				 upsts=true;
				//System.out.println("Query1 is "+query);
				
			    }
            catch(Exception e){
                System.out.println("testing total number of records "+e);
		   }
		   finally{
			  try{
					if(smt1!=null)
					smt1.close();
					//if(con!=null)
					//con.close();
				 }
              catch(Exception e){
              }
		   }
		   if(upsts)
	       return "Success";
		   else
           return "Failure";
	        }

//getting values from cycle_master
			public String cycleinsert()
	        {
			boolean upsts=false;
			Statement smt1=null;
			String newstartdate=null; String newenddate=null;
			int bn;
			try{
			java.util.Date dtTmp = new java.text.SimpleDateFormat("dd-MM-yyyy").parse(startdate);
           newstartdate = new  java.text.SimpleDateFormat("yyyy-MM-dd").format(dtTmp);   
		  java.util.Date Tmp = new java.text.SimpleDateFormat("dd-MM-yyyy").parse(enddate);
          newenddate = new  java.text.SimpleDateFormat("yyyy-MM-dd").format(Tmp);   
		  }
            catch(Exception e){
                System.out.println("error in date convert "+e);
		   }
			String query="insert into cycle_master(start_date,end_date,status) values('"+newstartdate+"','"+newenddate+"','y')";
			System.out.println("Query is "+query);
			try{
			    smt1=con.createStatement();
				bn=smt1.executeUpdate(query);  
				 upsts=true;
				
			    }
            catch(Exception e){
                System.out.println("testing total number of records "+e);
		   }
		   finally{
			  try{
				    
					if(smt1!=null)
					smt1.close();
					//if(con!=null)
					//con.close();
				 }
              catch(Exception e){
              }
		   }
		   if(upsts)
	       return "Success";
		   else
           return "Failure";
	        }




//deleting values from table
			public String deletetable(String dbname)
	        {
			boolean upsts=false;
			Statement smt1=null;
			String newstartdate=null; String newenddate=null;
			ResultSet bn=null;
			int batch=0;
			String query="select count(*) from "+dbname;
			
			
			try{
			    smt1=con.createStatement();
				
			    bn=smt1.executeQuery(query);
				
			    while(bn.next()){
				   batch=bn.getInt(1);
				  
			    }
			    if(batch>0){
					 
				   String query1="delete from "+dbname;
				   bn=smt1.executeQuery(query1);
				   upsts=true;
			    }
			    
			}
            catch(Exception e){
                System.out.println("testing total number of records "+e);
		   }
		   finally{
			  try{
				    if(bn!=null)
					bn.close();
					if(smt1!=null)
					smt1.close();
					//if(con!=null)
					//con.close();
				 }
              catch(Exception e){
              }
		   }
		   if(upsts)
	       return ""+batch;
		   else
           return "Failure";
	        }


			//Added by Prasad
	public void UpdateRcode(int up_id,String table){
    String[][] abc=new String[0][0];
      String sql="",query="";
	   Statement smt,smt1 = null;int bn;
	 try{
		 smt = con.createStatement();
         QueryBuilder qry = new QueryBuilder(smt); 
	   
		  
		  sql="select distinct(a.agent_code),b.rcode from "+table+" a,agent_agency_info b"
                 +" where a.upload_id="+up_id+" and a.agent_code=b.agency_code ";
		  		 
		 abc = qry.getResultSet(sql);     
		 

	       
	     }catch(Exception e){

	        }

                 if(abc.length>0)
		            { 
					            try{
									      
			                             smt1=con.createStatement();
										 for(int i=0;i<abc.length;i++)
									     { 
										query="update "+table+" set rcode="+abc[i][1]+" where agent_code='"+abc[i][0]+"'";
			                             bn=smt1.executeUpdate(query);  
										 }
							         }    catch(Exception e){ System.out.println("error"+e); }

   		              }
       }



	   public String getDate(String oldDate)
	{
		int currentYear=0,currentMonth=0,	currentDay=0;
		String start_date="",start_month="",start_year="",FinalDate="";
		java.util.Date sysDate = null;
		//System.out.println(" old date is "+oldDate);
         try{
	     sysDate = new java.util.Date(oldDate);
		 }catch(Exception e){ System.out.println("prob in date conv"+e); //e.printStackTrace();
		 }

	 currentYear		= sysDate.getYear();
	 currentMonth	= sysDate.getMonth();
	 currentDay		= sysDate.getDate();
 	if(currentYear < 1900)
		currentYear = currentYear +1900;


	 if(currentDay<=9)
		start_date="0"+currentDay; 
     else
       start_date=""+currentDay; 

     if((currentMonth+1)<=9)
		 start_month="0"+(currentMonth+1);
     else
        start_month=""+(currentMonth+1);

	    start_year=""+currentYear ;

		FinalDate=start_year+"-"+start_month+"-"+start_date;
		FinalDate=FinalDate.trim();
         //System.out.println(" FinalDate is "+FinalDate);
		return FinalDate;
	}


}


  /*for (int i = 1; i <= numberOfColumnsDB; i++) {
				 colname=rsMetaDB.getColumnName(i);
				 int x=rsMetaDB.getColumnType(i);
				 int y=rsMetaDB.getPrecision(i);
				 System.out.println("colname"+colname);
				 System.out.println("type"+x);
				 System.out.println("max no's"+y);
				 System.out.println(rsMetaDB.isAutoIncrement(i));
				 System.out.println(rsMetaDB.isNullable(i));
				 }*/
// gets the designated column's suggested title
      // for use in printouts and displays.
  //    System.out.println(rsMetaData.getColumnLabel(i));
  // get the designated column's class name.
    /*     System.out.println(rsMetaData.getColumnClassName(i));

      // get the designated column's table name.
      System.out.println(rsMetaData.getTableName(i));

      // get the designated column's number of decimal digits.
      System.out.println(rsMetaData.getPrecision(i));

      // gets the designated column's number of
      // digits to right of the decimal point.
      System.out.println(rsMetaData.getScale(i));

      // indicates whether the designated column is
      // automatically numbered, thus read-only.
      System.out.println(rsMetaData.isAutoIncrement(i));

      // indicates whether the designated column is a cash value.
      System.out.println(rsMetaData.isCurrency(i));

      // indicates whether a write on the designated
      // column will succeed.
      System.out.println(rsMetaData.isWritable(i));

      // indicates whether a write on the designated
      // column will definitely succeed.
      System.out.println(rsMetaData.isDefinitelyWritable(i));

      // indicates the nullability of values
      // in the designated column.
      System.out.println(rsMetaData.isNullable(i));

      // Indicates whether the designated column
      // is definitely not writable.
      System.out.println(rsMetaData.isReadOnly(i));

      // Indicates whether a column's case matters
      // in the designated column.
      System.out.println(rsMetaData.isCaseSensitive(i));

      // Indicates whether a column's case matters
      // in the designated column.
      System.out.println(rsMetaData.isSearchable(i));

      // indicates whether values in the designated
      // column are signed numbers.
      System.out.println(rsMetaData.isSigned(i));

      // Gets the designated column's table's catalog name.
      System.out.println(rsMetaData.getCatalogName(i));

      // Gets the designated column's table's schema name.
      System.out.println(rsMetaData.getSchemaName(i));*/