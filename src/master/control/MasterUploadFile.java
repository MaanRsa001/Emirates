package master.control;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.sql.*;
import org.apache.commons.fileupload.*;
import com.maan.DBCon.DBConnection;
import java.text.*;
import java.util.Date;
import master.bean.*;
import com.maan.DBCon.DBConnectionStatus;
public class MasterUploadFile extends HttpServlet
{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("Do get Initialized");
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		File fullFile = null;
		String[] data = new String[10];
		String[] table_name=new String[10];
		String[] c_id=new String[10];
		String[] cycle_date=new String[10];
		String[] filename=new String[10];
		String[] newfilename=new String[10];
		String[] extname=new String[10];
		String savedPath="";
		File savedFile = null;
		int i=0,j=0,m=0,l=0;
		StringBuffer sb = new StringBuffer();
		StringBuffer sb1 = new StringBuffer();
		HttpSession session = request.getSession(true);
		String polNo = (String)session.getAttribute("polNo");
		//system.out.println("Policy No is"+polNo);
		int index=0,k=0, count=0,ct=0;
		Vector a1 = new Vector();
		String name = null;
		Connection CON=null;
		String status="",RcodeStatus="";
	
		////////files upload:
		// first check if the upload request coming in is a multipart request
		PrintWriter out = response.getWriter();
		boolean isMultipart = FileUpload.isMultipartContent(request);
		// Create a new file upload handler
		DiskFileUpload upload = new DiskFileUpload();
		// parse this request by the handler
		// this gives us a list of items from the request
		List items = null;

		//SESSION CHECK
		String path = request.getContextPath();
        RequestDispatcher check = request.getRequestDispatcher(path+"login/sessionsCheckNormal.jsp");
        check.include(request,response);
        if(session.getAttribute("ses")==null){	
			response.sendRedirect(path+"/login/error_messg.jsp");
			return;
		}          
        String usrModeSC=(String)session.getAttribute("userLoginMode")==null?"":(String)session.getAttribute("userLoginMode");
        DBConnectionStatus.statusStatic=usrModeSC;
        //END OF SESSION CHECK
	try
	{
		
		try
		{
			items = upload.parseRequest(request);
		} catch (FileUploadException e)
		{
		
		e.printStackTrace();
		}
		Iterator itr = items.iterator();
		while(itr.hasNext())
		{
			savedPath="";
			FileItem item = (FileItem) itr.next();
			// check if the current item is a form field or an uploaded file
			if(item.isFormField())
			{
				// get the name of the field
				String fieldName = item.getFieldName();
				//System.out.println("Field name is"+fieldName);
				// if it is name, we can set it in request to thank the user
				if(fieldName.equals("name"))
				{
					request.setAttribute("msg", "Thank You: " + item.getString());
					// later you can use it like this:
					// out.println(request.getAttribute("msg"));
				}
				out.println(item.getFieldName() + " = " + item.getString() + "");
				if("dbname".equalsIgnoreCase(item.getFieldName()))
				{
					  table_name[j]=item.getString();
					  //system.out.println("table name is"+j+" "+item.getString());
					  j++;
				}
				if("cid".equalsIgnoreCase(item.getFieldName()))
				{
					  c_id[m]=item.getString();
					  //system.out.println("cycle id is"+m+" "+item.getString());
					  m++;
				}
				if("cycle_date".equalsIgnoreCase(item.getFieldName()))
				{
					  cycle_date[l]=item.getString();
					  //system.out.println("cycle date is"+l+" "+item.getString());
					  l++;
				}
				if("rcode_status".equalsIgnoreCase(item.getFieldName()))
				{
					  RcodeStatus=item.getString();
					  //system.out.println("rcode status is"+l+" "+item.getString());
					  
				}
		}
	else
	{
		// the item must be an uploaded file save it to disk. Note that there
		// seems to be a bug in item.getName() as it returns the full path on
		// the client's machine for the uploaded file name, instead of the file
		// name only. To overcome that, I have used a workaround using
		// fullFile.getName().
 
		 String s = request.getRealPath("/"+"RSAuploadedfiles/");
		 fullFile = new File(item.getName());
		// savedFile = new File(""+s, fullFile.getName());
		 //System.out.println("Full File is"+fullFile);
		 
		// System.out.println("Saved file is"+savedFile);
		 index = fullFile.getName().lastIndexOf("\\");
		// System.out.println("Full File index"+index);
		 k = fullFile.getName().length();
		 name = fullFile.getName().substring(index+1,k);
		 //system.out.println("Name is"+name.length());
		 if( name.length() > 0)
				{
		  index = name.lastIndexOf(".");
		 //System.out.println("Full File index"+index);
		 try{
		  filename[i] = name.substring(0,index);
		  extname[i] = name.substring(index+1,name.length());
		 }catch(Exception e){ System.out.println("error in file name");}
		 //getting system time and adding to file name
		   Calendar cal = Calendar.getInstance();
		   //SimpleDateFormat sdf = new SimpleDateFormat("ddMMMyyyy");
		   SimpleDateFormat sdf = new SimpleDateFormat("'on'ddMMMyyyy h.mm.ss a ");
		   String date=sdf.format(cal.getTime());
		 
		  //system.out.println("file Name is"+filename[i]+date);
		 //system.out.println("extension Name is"+extname[i]);
		 newfilename[i]=filename[i]+date;
		 savedFile = new File(""+s, filename[i]+date.trim()+"."+extname[i]);
		 savedPath=savedFile.toString();
		}
		count++;
		if(!name.equals(""))
	   {
			sb.append(""+count);
			//sb1.append(""+name);
		}
	  if(!name.equals(""))
	  {
		  sb1.append(""+name);
	  }
//data[i] = ""+name;
 
data[i] = savedPath;
//system.out.println("i is"+i+" "+savedPath);

//String path = getServletContext().getRealPath("/");
if("csv".equalsIgnoreCase(extname[i]))
		{
	    //system.out.println("coming to write file");
       try {
		item.write(savedFile);
		//system.out.println("file written");
		     } catch (Exception e1)
		       	{
	              
				  //system.out.println("unable to write file");
	              e1.printStackTrace();
	               }
		 }
		 i++;
}
}



	CON=DBConnection.getInstance().getDBConnection();
	//RSAexcelupload xl =new RSAexcelupload();
	master.bean.RSAuploadbean xl =new master.bean.RSAuploadbean();
	xl.setCon(CON);



	for(int x=0;x<1;x++)
	{
		if( (extname[x]==null) && ("select".equalsIgnoreCase(table_name[x])) )
	 	{       
			    ct++;
		} 
		else
		{
				// status+="<br><br><font color=blue> Table Name "+table_name[x]+"</font><br>";
	
	  if("csv".equalsIgnoreCase(extname[x]))
	  {
		xl.setPath(data[x]);
		xl.setDbname(table_name[x]);
		xl.setCid(c_id[x]);
		xl.setCycle_date(cycle_date[x]);
		xl.setFile_name(newfilename[x]);
		
		//system.out.println("file name to bean"+newfilename[x]);

		// coming from renewal,unset upload
		if("rcode".equalsIgnoreCase(RcodeStatus))
			xl.setRcode_status(RcodeStatus);

		//coming from apprupload
		if("appr".equalsIgnoreCase(RcodeStatus))
			xl.setRcode_status(RcodeStatus);
					 
		status+=xl.UploadFields((String)session.getAttribute("user"));
				 
		}
		else
		{
			status+="<br>Please select CSV Format File* ";
		}
		if("select".equalsIgnoreCase(table_name[x]))
       		status+="<br>Please select Database Name* ";
		}
			
	}
	        if(ct==1)
	         status+="<br> Please select files to Upload";

			//system.out.println("rcode status is"+RcodeStatus);

             if("rcode".equalsIgnoreCase(RcodeStatus))
	         {
                    //system.out.println("upload id is"+xl.getBatch_id());
                    xl.UpdateRcode(xl.getBatch_id(),table_name[0]);
	         }

			// calculating premium
			
            updatePremium uP=new updatePremium();
			uP.setTranid(""+xl.getBatch_id());
			uP.TestUpdate();
			
			
			master.bean.mailbean mB=new master.bean.mailbean();
			 mB.setTranid(""+xl.getBatch_id());
			 mB.getMailDetails(); 

			/*request.setAttribute("transId",""+xl.getBatch_id());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/QuotationMailController");
			if(dispatcher!=null)
				 dispatcher.include(request,response);*/
	}
	catch(Exception e)
	{ 
		System.out.println("exception in con.close in controller[update]");
	}
	finally
	{
		 try
		 {
			 if(CON!=null)
			 {
				CON.commit();
				CON.close();
			 }
		} 
		catch(Exception e)
		{ 
			System.out.println("exception in con.close in controller[update]");
		}
	}
         request.setAttribute("result",status);
		 RequestDispatcher dispatcher = request.getRequestDispatcher("/bhomes/commUploadresult123.jsp");
		 if(dispatcher!=null)
           dispatcher.forward(request,response);
	}
}
