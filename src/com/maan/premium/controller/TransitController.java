package com.maan.premium.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.maan.premium.DAO.TransitTransactions;

public class TransitController extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private StringBuffer error=new StringBuffer();	
		
	TransitTransactions transitTransaction=new TransitTransactions();	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
	{
		
		HttpSession session=null;
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		session=request.getSession(true);
		if(session.getAttribute("ses")==null){
			response.sendRedirect("../login/error_messg_pdf.jsp");	
			return;
		}
		String usrModeController=(String)session.getAttribute("userLoginMode")==null?"":(String)session.getAttribute("userLoginMode");
		com.maan.DBCon.DBConnectionStatus.statusStatic=usrModeController;
		System.out.println("RoyalTest for databese mode checking in  TransitController.."+usrModeController);
		if("".equalsIgnoreCase(usrModeController) || " ".equalsIgnoreCase(usrModeController)){
			response.sendRedirect("../login/error_messg_pdf.jsp");	
			return;
		}
		
		 String country		=	"";
		String city			=	"";
		String src			=	"";
		String otherCity	=	"";	
		String place		=	"";	
		String countryId			=	"";
		String cityId				=	"";
		String countryName			=	"";
		String cityName				=	"";
		HashMap countryDet=new HashMap();	
		String frmCountry	=	"";
		String toCountry	=	"";
		RequestDispatcher dispatcher;
		
		error=new StringBuffer();
		country=request.getParameter("country")==null?country:request.getParameter("country");
		error=checkInputs(country,"COUNTRY");
		city=request.getParameter("city")==null?city:request.getParameter("city");
		error=checkInputs(city,"CITY");
		otherCity=request.getParameter("other")==null?otherCity:request.getParameter("other");
		if("0".equalsIgnoreCase(city)){
		}
		else{
			countryName=city.substring(0,city.indexOf('#'));
			countryId=city.substring((city.indexOf('#')+1),city.indexOf('$'));
			cityName=city.substring((city.indexOf('$')+1),city.indexOf('*'));
			cityId=city.substring((city.indexOf('*')+1),city.indexOf('~'));
			place=city.substring((city.indexOf('~')+1),city.length());
		}
		if(cityName.equalsIgnoreCase("others")){
			error=checkInputs(otherCity,"OTHERS");	
		}
		src=request.getParameter("src")==null?src:request.getParameter("src");
		out.println("the COUNTRY is"+country);
		out.println("the CITY is"+city);
		out.println("the CITY OTHERS  is"+otherCity);
		out.println("the Src is"+src);
		if(error.length()>0){
			out.println("error.length()>0");
			request.setAttribute("errorMessageTransit",error);
			request.setAttribute("windowClose","no");
			//context.getRequestDispatcher("/Quotation.jsp").forward(request, response);
			//piB.setModeOfTransport("0");
			dispatcher=request.getRequestDispatcher("/premium/transit.jsp");
			dispatcher.forward(request, response);
	        //dispatcher.include(request, response);
		}
		else{
			//	dispatcher=request.getRequestDispatcher("/premium/Quotation.jsp");
			//dispatcher=request.getRequestDispatcher("/premium/transit.jsp");
			//dispatcher.forward(request, response);
			/*if("F".equalsIgnoreCase(src))
			{
			//session.setAttribute("frmCountryId",country);
			}else
			{
			//session.setAttribute("toCountryId",country);
			}*/
			//THis is Newly Added
			//UNITED ARAB EMIRATES#1$Dubai*1~Dubai
			countryName=city.substring(0,city.indexOf('#'));
			countryId=city.substring((city.indexOf('#')+1),city.indexOf('$'));
			cityName=city.substring((city.indexOf('$')+1),city.indexOf('*'));
			cityId=city.substring((city.indexOf('*')+1),city.indexOf('~'));
			place=city.substring((city.indexOf('~')+1),city.length());
			HashMap countryDetails=new HashMap();
			countryDetails=(HashMap)session.getAttribute("countryDetails")==null?countryDet:(HashMap)session.getAttribute("countryDetails");
			if(place.equalsIgnoreCase("others")){
				//place=otherCity;
				city=countryName+"#"+countryId+"$"+cityName+"*"+cityId+"~"+otherCity;
			}
			else{
				//place=cityName;	
				city=countryName+"#"+countryId+"$"+cityName+"*"+cityId+"~"+cityName;	
			}
			out.println("Final Contry Id Is------------->>>>>"+src);
			if("F".equalsIgnoreCase(src)){
					//				REmoving Values
					try{
							countryDetails.remove("transitCityId");
							countryDetails.remove("transitCountryId");
							countryDetails.remove("transitFrom");
					}
					catch(Exception ex){
						System.out.println("Exception while Transit Removing"+ex.toString());
					}
					//REmoving Values
					session.setAttribute("fromCountryId",countryId);
					session.setAttribute("fromCityId",cityId);
					countryDetails.put("transitCityId",cityId);
					countryDetails.put("transitCountryId",countryId);
					countryDetails.put("transitFrom",city);
				//REmoving T
					try{
						countryDetails.remove("finalCityId");
						countryDetails.remove("finalCountryId");
						countryDetails.remove("finalDestination");
					}
					catch(Exception ex){
						System.out.println("Exception while REmoving Values DESTINATION Removing"+ex.toString());
					}
					//REmoving Values
					session.setAttribute("toCountryId",countryId);
					session.setAttribute("toCityId",cityId);
					countryDetails.put("finalCityId",cityId);
					countryDetails.put("finalCountryId",countryId);
					countryDetails.put("finalDestination",city);
				}
				if("T".equalsIgnoreCase(src)){
					//REmoving Values
					try{
						countryDetails.remove("finalCityId");
						countryDetails.remove("finalCountryId");
						countryDetails.remove("finalDestination");
					}
					catch(Exception ex){
						System.out.println("Exception while REmoving Values DESTINATION Removing"+ex.toString());
					}
					//REmoving Values
					session.setAttribute("toCountryId",countryId);
					session.setAttribute("toCityId",cityId);
					countryDetails.put("finalCityId",cityId);
					countryDetails.put("finalCountryId",countryId);
					countryDetails.put("finalDestination",city);
				}
				session.setAttribute("countryDetails",countryDetails);
				session.setAttribute("sessionCountry","Yes");
				dispatcher=request.getRequestDispatcher("/premium/transit.jsp");
				dispatcher.forward(request, response);
				
		}
		out.flush();
		out.close();
	}
	
	public StringBuffer checkInputs(String inputName,String inputDescription)
	{
		
		if("".equalsIgnoreCase(inputName) || "0".equalsIgnoreCase(inputName) || null==(inputName) 
		|| "null".equalsIgnoreCase(inputName) || "select".equalsIgnoreCase(inputName) || "DD".equalsIgnoreCase(inputName)
		|| "MM".equalsIgnoreCase(inputName) || "YYYY".equalsIgnoreCase(inputName))
		{
			error.append("<br><font color=red size=2><b>* Please Provide Input for ---"+inputDescription
					+"</b></font><br>");
		}
		else if(inputDescription.equalsIgnoreCase("OTHERS")){
			try{
				Long.parseLong(inputName);
				error.append("<br><font color=red size=2><b>* Please Provide VALID INPUT for ---"+inputDescription
						+"</b></font><br>");
			}
			catch(Exception exception){
			}
		}
		return error;
	}
	public void init() throws ServletException {
	}
}
