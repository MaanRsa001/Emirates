package master.control;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import master.bean.*;
public class BhomeReportCont extends HttpServlet
{
	
	String[][] TValues= new String[0][0];
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("Do get Initialized");
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
	
		HttpSession session = request.getSession(true);
		PrintWriter out = response.getWriter();
		String path = request.getContextPath();
		
		RequestDispatcher dispatcher =null;
		
        RequestDispatcher check = request.getRequestDispatcher(path+"login/sessionsCheckNormal.jsp");
        check.include(request,response);
        
        if(session.getAttribute("ses")==null){	
			response.sendRedirect(path+"/login/error_messg.jsp");
			return;
		}          
         
         String datestatus=(String)request.getParameter("datestatus")==null?"new":(String)request.getParameter("datestatus");
         String start_datee="", end_datee="",error="";
         System.out.print("login----------------------------------status"+datestatus);
         
         if(datestatus.equalsIgnoreCase("new"))
         {
         String start_date=(String)request.getParameter("start_date")==null?"0":(String)request.getParameter("start_date");
         String start_month=(String)request.getParameter("start_month")==null?"0":(String)request.getParameter("start_month");
         String start_year=(String)request.getParameter("start_year")==null?"0":(String)request.getParameter("start_year");
         String end_date=(String)request.getParameter("end_date")==null?"0":(String)request.getParameter("end_date");
         String end_month=(String)request.getParameter("end_month")==null?"0":(String)request.getParameter("end_month");
         String end_year=(String)request.getParameter("end_year")==null?"0":(String)request.getParameter("end_year");
         
         Transaction_Details TDetails=new Transaction_Details();
         start_datee=start_date+"-"+start_month+"-"+start_year;
         end_datee=end_date+"-"+end_month+"-"+end_year;
         System.out.print(start_datee+"-"+end_datee);
         TDetails.setStart_date(start_date);
         TDetails.setStart_month(start_month);
         TDetails.setStart_year(start_year);
         TDetails.setEnd_date(end_date);
         TDetails.setEnd_month(end_month);
         TDetails.setEnd_year(end_year);
         TDetails.setStart_datee(start_datee);
         TDetails.setEnd_datee(end_datee);
         error=TDetails.Validation();
         if(error.length()<=0)
         TValues=TDetails.TranProcessDetails();
         
         if(TValues.length<=0)
         {
        	 System.out.print("its setting values-------------------------------------------");
        	 request.setAttribute("start_date",start_date);
        	 request.setAttribute("start_month",start_month);
        	 request.setAttribute("start_year",start_year);
        	 request.setAttribute("end_date",end_date);
        	 request.setAttribute("end_month",end_month);
        	 request.setAttribute("end_year",end_year);
        	 
         }
        	 
         
         }
         else if (datestatus.equalsIgnoreCase("exist")) {
        	 start_datee=(String)request.getParameter("start_datee")==null?"0":(String)request.getParameter("start_datee");
        	 end_datee=(String)request.getParameter("end_datee")==null?"0":(String)request.getParameter("end_datee");
             String pagecount=(String)request.getParameter("pagecount")==null?"0":(String)request.getParameter("pagecount");
        	 String pagecount1=(String)request.getParameter("pagecount1")==null?"0":(String)request.getParameter("pagecount1");
        	 request.setAttribute("pagecount", pagecount);
        	 request.setAttribute("pagecount1", pagecount1);
			
		}
         
         if(error.length()<=0)
         {
         
         request.setAttribute("data", TValues);
         request.setAttribute("start_datee", start_datee);
         request.setAttribute("end_datee", end_datee);
         dispatcher = request.getRequestDispatcher("/bhomes/tranView.jsp");
         }else
         {
        	 request.setAttribute("error",error);
        	 dispatcher = request.getRequestDispatcher("/bhomes/TranSelect.jsp");
         }
         
		  
		 if(dispatcher!=null)
           dispatcher.forward(request,response);
		   return;
	}
}
