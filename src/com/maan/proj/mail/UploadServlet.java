package com.maan.proj.mail;import java.io.IOException;import java.io.PrintWriter;import java.util.HashMap;import javax.servlet.ServletException;import javax.servlet.ServletInputStream;import javax.servlet.http.HttpServlet;import javax.servlet.http.HttpServletRequest;import javax.servlet.http.HttpServletResponse;

/**
 * Upload servlet works in conjunction with a browser's form-
 * based file upload capability to allow the client to transfer
 * a file (binary or ASCII) to the server which is then stored
 * in the server's file system. This servlet uses the
 * MultipartRequestParser class to parse the upload request and
 * store the file.
 *
 * @author Dustin R. Callaway
 */
public class UploadServlet extends HttpServlet
{
  //indicates the action to take if the upload file is too large
  // 0 - throw IOException
  // 1 - send MAX_SIZE_MSG to client
  // 2 - redirect client to MAX_SIZE_REDIRECT
  private static final int MAX_SIZE_ACTION = 0;
  private static final String MAX_SIZE_MSG =
    "Sorry, that file exceeds the maximum file size limit.";
  private static final String MAX_SIZE_REDIRECT = "/TooBig.html";

  //default maximum allowable file size is 1MB
  private static final int MAX_SIZE = 1024 * 1024;

  //names of initialization and web parameters
  private static final String SAVE_DIRECTORY = "SaveDirectory";
  private static final String SUCCESS_MESSAGE = "SuccessMessage";
  private static final String ERROR_MESSAGE = "ErrorMessage";
  private static final String SUCCESS_PAGE = "SuccessPage";

  String fileSeparator;
  String saveDirectory = "insurancemust/";
  String successMessage;
  String errorMessage;


  /**
   * Called when the first time the servlet loads. This method
   * initializes the saveDirectory, successMessage, and
   * errorMessage variables using either an initialization
   * parameter (if it exists) or a hard-coded default.
   *
   * @exception ServletException
   */
  public void init() throws ServletException
  {
    //get the file separator for the current operating system
    fileSeparator = System.getProperty("file.separator");

    //get the directory to which to save files
    //saveDirectory = getInitParameter(SAVE_DIRECTORY);
	
    if (saveDirectory == null)
    {
      saveDirectory = fileSeparator; //default to root directory
    }

    //Get message to display when upload is complete. Used only
    //if a success redirect page is not supplied by upload form.
    //successMessage = getInitParameter(SUCCESS_MESSAGE);
    if (successMessage == null)
    {
      //default success message
      successMessage = "File upload complete!";
    }

    //Get message to display when an error occurs.
    errorMessage = getInitParameter(ERROR_MESSAGE);
    if (errorMessage == null)
    {
      //default error message
      errorMessage = "File upload failed. Error: ";
    }
  }


  /**
   * Handles all HTTP GET requests. The upload servlet returns a
   * generic upload HTML form in response to all GET requests.
   * Upon success, this form indicates that the servlet should
   * redirect to a file called "/success.html" located at the web
   * server's root. Though this form may be used, in most cases a
   * custom upload form should be created that posts to this
   * servlet.
   *
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @exception ServletException
   * @exception IOException
   */
  public void doGet(HttpServletRequest request,
    HttpServletResponse response) throws ServletException,
    IOException
  {
    response.setContentType("text/html");
	String username = request.getParameter("username");
    PrintWriter out = new PrintWriter(
      response.getOutputStream());
	//fileSeparator="\\";
    out.println("<HTML>");
    out.println("<HEAD><TITLE>File Upload</TITLE></HEAD>");
    out.println("<BODY>");
	
    out.println("<H2>File Upload</H2>");
	

    //the encoding type is multipart/form-data for file uploads
    out.println("<FORM name =uploadForm  ENCTYPE=\"multipart/form-data\" ");
    out.println("METHOD=\"POST\">");
	out.println("<input type=hidden name = username value=" + username + ">");
    out.println("Press the <I>Browse</I> button to select the ");
    out.println("file to upload and then click the ");
    out.println("<I>Upload</I> button.<P>");

    //these lines add the file box and browse button to the form
    out.println("<B>File Name:</B> <INPUT TYPE=\"FILE\" ");
    out.println("NAME=\"Filename\" SIZE=\"25\" ");
    out.println("MAXLENGTH=\"255\"><P>");

    out.println("<center><INPUT TYPE=\"SUBMIT\" VALUE=\"Upload\"></center>");
	   

    //these lines indicate where to redirect in case of success
  /*  out.println("<INPUT TYPE=\"HIDDEN\" NAME=\"SuccessPage\" ");
    out.println("VALUE=\"/success.html\">"); */

    out.println("</FORM></BODY></HTML>");
    out.close();
  }


  /**
   * Process all HTTP POST requests. This method makes sure that
   * the POSTed request is of type multpart/form-data and that
   * the uploaded file is not too large. It then instantiates the
   * MultipartRequestParser to parse the request and write the
   * file to the file system. It then either displays a success
   * message or redirects the user to the success HTML page
   * submitted by the upload HTML form.
   *
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @exception ServletException
   * @exception IOException
   */
  public void doPost(HttpServletRequest request,
    HttpServletResponse response) throws ServletException,
    IOException
  {
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();

    try
    {
      String contentType = request.getContentType();
		//fileSeparator="\\";
      //make sure content type is multipart/form-data
      if(contentType != null && contentType.startsWith(
        "multipart/form-data"))
      {
        int contentLength = request.getContentLength();

        //make sure request is not larger than max upload size
        if (contentLength > MAX_SIZE)
        {
          if (MAX_SIZE_ACTION == 0) //throw exception
          {
            //Upload file is too large. We cannot return a
            //message to the client until we have read the entire
            //request. Since we don't want to waste resources
            //reading the entire request, simply throw an
            //IOException.
            throw new IOException("File too large to upload.");
          }
          else if (MAX_SIZE_ACTION == 1) //return message
          {
            //get handle to request's input stream
            ServletInputStream in = request.getInputStream();

            //read and discard the entire request
            in.skip(contentLength);

            //send a brief error message to the client
            out.println(MAX_SIZE_MSG);
            out.flush();
          }
          else //redirect client
          {
            //redirect to the MAX_SIZE_REDIRECT URL
            response.sendRedirect(MAX_SIZE_REDIRECT);
          }

          return; //stop processing request
        }
		//saveDirectory = fileSeparator  ;
		out.println("<br> The Save Directory is " + saveDirectory);
        //instantiate class to parse multipart/form-data request
        MultipartRequestParser mrp = new MultipartRequestParser(
          request, saveDirectory);

        mrp.parseRequest(); //parse the request

        //get hashmap containing all web variables from request
        HashMap webVars = mrp.getWebVars();

        //get success page URL from web variables
        String successPage = (String)webVars.get(SUCCESS_PAGE);

        if (successPage == null)
        {
          //no success page variable, send default message
		  out.println("Inside Sucess Message Page ");
		  out.println(webVars.get("filename"));
          //out.println(successMessage);
		   out.println("<center><a href='javascript:window.opener.form1.filename.value=1235 ;window.close();'>Close</a></center>");
        }
        else
        {
          //success page variable exists, redirect to page
          //response.sendRedirect(successPage);
		  out.println(" <br>Inside Sucess Message Page else");
		  out.println("<br> FileName :<br> "+request.getParameter("FILENAME"));
          out.println(successMessage);

        }
      }
      else //request is not multipart/form-data
      {
        //send error message to client
        out.println("<H2>Request not multipart/form-data.</H2>");
      }
    }
    catch (Exception e)
    {
      out.println("The error is " + errorMessage + e);
      log("UploadServlet Error: " + e);
    }
    finally
    {
      out.close();
    }
  }


  /**
   * Returns information about this servlet to the server.
   *
   * @return Brief description of this servlet
   */
  public String getServletInfo()
  {
    return "UploadServlet allows the client to upload files.";
  }
}