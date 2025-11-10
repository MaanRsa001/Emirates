package com.maan.services.util;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.sql.*;
import org.apache.commons.fileupload.*;
import java.text.*;
import java.util.Date;
import master.bean.*;
import com.maan.DBCon.DBConnectionStatus;
import com.maan.services.policyInfo;

public class MarineUploadFileController extends HttpServlet
{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("Do get Initialized MarineUploadFileController");
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		File fullFile = null;
	
		String[] filename = new String[100];
		String[] newfilename = new String[100];
		String[] extname = new String[100];
		
		String cusInfo[][] = new String[0][0];
		String uploadFileMaster[][] = new String[0][0];
		String savedPath="";
		File savedFile = null;
		int i=0,j=0,m=0,l=0;
		StringBuffer sb = new StringBuffer();
		StringBuffer sb1 = new StringBuffer();
		HttpSession session = request.getSession(true);
		String polNo = (String)session.getAttribute("polNo");

		System.out.println("Policy No is"+polNo);
		int index=0,k=0, count=0,ct=0;
		Vector a1 = new Vector();
		String name = null;
		Connection CON=null;
		String status="",RcodeStatus="";

		/** Upload Information **/

		String quoteNo = "";
		String coverId = "";
		String uploadRemarks = "";
		String uploadRemarks1 = "";
		String uploadRemarks2= "";
		String uploadRemarks3 = "";
		String uploadRemarks4 = "";

		String uploadFileType = "";
		String uploadFileType1 = "";
		String uploadFileType2 = "";
		String uploadFileType3 = "";
		String uploadFileType4 = "";
		String uploadFilePrefix = "";

		String contentTypeId = "";
		String proId = "";
		String loginId = "";
		String schemeId = "";
		String custFileName = "";
		String systemFileName = "";
		String error = "";
		String remarks = "";
		String adminRefStatus = "";
		String save = "";
		String reqFrom = "";
		String screenFrom = "";
		String productId = "";
		String branch = "";
		int cnt = 1;
		String quoteInfo[][] = new String[0][0];

		proId = (String) session.getAttribute("product_id");
		loginId = (String) session.getAttribute("user");
		schemeId = (String) session.getAttribute("schemeId");
		branch = (String) session.getAttribute("LoginBranchCode");

		proId = proId == null ? "" : proId;
		loginId = loginId == null ? "" : loginId;
		
		System.out.println("loginId ...MarineUploadFileController..."+loginId);
		System.out.println("quoteNo ...MarineUploadFileController..."+quoteNo);
		System.out.println("proId ...MarineUploadFileController..."+proId);
		
		/** Upload Information **/

		//OfficeShieldBean OSB= new OfficeShieldBean();
		policyInfo policy = new policyInfo();

		
		
		// Only For Office shield Marine is Default - START
		coverId = "0";
		schemeId = "0";
		contentTypeId = "0";
		// Only For Office shield Marine is Default - END

		// first check if the upload request coming in is a multipart request
		
		PrintWriter out = response.getWriter();
		
		boolean isMultipart = FileUpload.isMultipartContent(request);
		
		// Create a new file upload handler
		
		DiskFileUpload upload = new DiskFileUpload();

		// parse this request by the handler this gives us a list of items from the request
		
		List items = null;

		//SESSION CHECK
		String path = request.getContextPath();
        RequestDispatcher check = request.getRequestDispatcher("/login/sessionsCheckNormal.jsp");
        check.include(request,response);
        if(session.getAttribute("ses")==null)
		{	
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
			} 
			catch (FileUploadException e)
			{
				// TO DO Auto-generated catch block
				System.out.println("MarineUploadFileController ..."+e.toString());
				e.printStackTrace();
			}
						
			Iterator itr = items.iterator();
			while(itr.hasNext())
			{
				savedPath="";
				FileItem item = (FileItem) itr.next();
				if(item.isFormField())
				{
					String fieldName = item.getFieldName();

					if(fieldName.equals("upldRemarks_1"))
						uploadRemarks1 = item.getString();

					if(fieldName.equals("upldRemarks_2"))
						uploadRemarks2 = item.getString();

					if(fieldName.equals("upldRemarks_3"))
						uploadRemarks3 = item.getString();

					if(fieldName.equals("upldRemarks_4"))
						uploadRemarks4 = item.getString();

					if(fieldName.equals("upldFileType_1"))
						uploadFileType1 = item.getString();	

					if(fieldName.equals("upldFileType_2"))
						uploadFileType2 = item.getString();	

					if(fieldName.equals("upldFileType_3"))
						uploadFileType3 = item.getString();	

					if(fieldName.equals("upldFileType_4"))
						uploadFileType4 = item.getString();	

					if(fieldName.equals("reqFrom"))
						reqFrom = item.getString();
					if(fieldName.equals("screenFrom"))
						screenFrom = item.getString();
					if(fieldName.equals("productId"))
						productId = item.getString();

					if(fieldName.equals("pqno"))
						quoteNo = item.getString();
				}
				else
				//if(!item.isFormField())
				{
					// check if the current item is a form field or an uploaded file
					// the item must be an uploaded file save it to disk. Note that there
					// seems to be a bug in item.getName() as it returns the full path on
					// the client's machine for the uploaded file name, instead of the file
					// name only. To overcome that, I have used a workaround using
					// fullFile.getName().
					
					if(screenFrom.equalsIgnoreCase("AdminPortfolio")||screenFrom.equalsIgnoreCase("AdminMulPortfolio")||screenFrom.equalsIgnoreCase("AdminSearchPortfolio")||screenFrom.equalsIgnoreCase("AdminClaim")||screenFrom.equalsIgnoreCase("AdminClaimMulti"))
						proId = productId;
					
					
					uploadFileMaster = policy.getMarineUploadMasterDetails(proId,branch);

					String fileRealPath = request.getRealPath("/"+"MarineUploadedFiles/");
					fullFile = new File(item.getName());
					
					// savedFile = new File(""+s, fullFile.getName());
					if(fullFile!=null)
					{
						index = fullFile.getName().lastIndexOf("\\");
						k = fullFile.getName().length();
						name = fullFile.getName().substring(index+1,k);
						custFileName = fullFile.getName().substring(index+1,k);
						
						
						if( name.length() > 0)
						{
							index = name.lastIndexOf(".");
							try
							{
							  filename[i] = name.substring(0,index);
							  extname[i] = name.substring(index+1,name.length());
							}
							catch(Exception e)
							{
								System.out.println("error in file name");
								e.printStackTrace();
							}
							//getting system time and adding to file name
							Calendar cal = Calendar.getInstance();
							SimpleDateFormat sdf = new SimpleDateFormat("'on'ddMMMyyyy h.mm.ss a ");
							String date = sdf.format(cal.getTime());
							System.out.println("extension Name is"+extname[i]);
							/*newfilename[i]=filename[i]+date;
							systemFileName = filename[i]+date.trim()+"."+extname[i];*/
							
							if(cnt ==1)
							{
								uploadRemarks = uploadRemarks1;
								uploadFileType = uploadFileType1;
							}
							if(cnt ==2)
							{
								uploadRemarks = uploadRemarks2;
								uploadFileType = uploadFileType2;
							}
							if(cnt ==3)
							{
								uploadRemarks = uploadRemarks3;
								uploadFileType = uploadFileType3;
							}
							if(cnt ==4)
							{
								uploadRemarks = uploadRemarks4;
								uploadFileType = uploadFileType4;
							}
							for(j=0;j<uploadFileMaster.length;j++)
							{
								if(uploadFileMaster[j][0].equalsIgnoreCase(uploadFileType))
										uploadFilePrefix = uploadFileMaster[j][1];
							}
							
							
							systemFileName = "";
							systemFileName = uploadFilePrefix+"_"+quoteNo+"_proId"+proId+" "+date.trim()+"."+extname[i];
							
							
							
							savedFile = new File(""+fileRealPath, systemFileName);
							savedPath=savedFile.toString();
					    }
							count++;
						   if(!name.equals(""))
						   {
							sb.append(""+count);
						   }
						   if(!name.equals(""))
						   {
							 sb1.append(""+name);
						   }
											
					/*	if("xls".equalsIgnoreCase(extname[i]))
						{	*/
						 if(!name.equals(""))
						 {
							System.out.println("coming to write file");
							try 
							{
								item.write(savedFile);
								System.out.println("file written");
							}
							catch (Exception e1)
							{
							  
							  System.out.println("unable to write file");
							  e1.printStackTrace();
							}
						 }
					  //} // extname if
					
					 //Insertion Of OFS_Upload_Data For Marine Files
						System.out.println("....name...."+name);
						System.out.println("....uploadFileType...."+uploadFileType);
						System.out.println("...MarineUploadFileController.uploadFileType2..."+uploadFileType2);

						if(name.length() > 0 && !uploadFileType.equalsIgnoreCase("Select") && !uploadFileType.equalsIgnoreCase(""))
						{	
							policy.insertOFSUploadedData(quoteNo, branch, proId, loginId, custFileName, systemFileName, uploadRemarks, uploadFileType);
							cnt++; //Existing
						}
						else
						{
							if(cnt !=0&&name.length()>0)
							{
								if(uploadFileType.equalsIgnoreCase("Select")|| uploadFileType.equalsIgnoreCase(""))
									error = error+"* Please Select Upload File Type in row "+cnt+"<br>";
								if(name.length()<=0)
									error = error+"* Please Select File for Upload<br>";
							}
				
						}
						cnt++; // Jan 2 2009
					 } 
			  } // formfield else
			i++;
			}//while Loop			
		}
		catch(Exception e)
		{ 
			System.out.println("Exception is Insertion Of OFS_Upload_Data ...."+e);
			e.printStackTrace();
		}
		
		/*	
			RequestDispatcher dispatcher = null;
			dispatcher = request.getRequestDispatcher("/marineUploadDownload.jsp");
			if(dispatcher!=null)
			  dispatcher.forward(request,response);
		*/
			//String screenFrom = request.getParameter("screenFrom")!=null?request.getParameter("screenFrom"):"";
			if(error.length()>0)
			{
				if(session.getAttribute("error")!=null)
				 session.removeAttribute("error");
			    session.setAttribute("error",error);	
			}
			if(screenFrom.length()>0)
			{
				response.sendRedirect(path+"/marineUploadDownload.jsp?pqno="+quoteNo+"&screenFrom="+screenFrom+"&productId="+productId); 
			}
			else
  				response.sendRedirect(path+"/marineUploadDownload.jsp?pqno="+quoteNo); 
	}

} //Class
