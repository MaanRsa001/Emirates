package master.bean;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.maan.services.util.runner;

public class Transaction_Details {
	String start_datee="",end_datee="",start_date="",start_month="",start_year="",end_date="",end_month="",end_year="";

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	
	public String getEnd_datee() {
		return end_datee;
	}

	public void setEnd_datee(String end_datee) {
		this.end_datee = end_datee;
	}

	public String getEnd_month() {
		return end_month;
	}

	public void setEnd_month(String end_month) {
		this.end_month = end_month;
	}

	public String getEnd_year() {
		return end_year;
	}

	public void setEnd_year(String end_year) {
		this.end_year = end_year;
	}

	public String getStart_datee() {
		return start_datee;
	}

	public void setStart_datee(String start_datee) {
		this.start_datee = start_datee;
	}

	public String getStart_month() {
		return start_month;
	}

	public void setStart_month(String start_month) {
		this.start_month = start_month;
	}

	public String getStart_year() {
		return start_year;
	}

	public void setStart_year(String start_year) {
		this.start_year = start_year;
	}

	// transaction information
	public String[][] TranProcessDetails() {
		String[][] abc = new String[0][0];
		String values[]=new String[2];
		String sql = "";

		
   try {
			sql = "select TRAN_ID,FILE_ID,to_char(TRANSACTION_DATE,'dd/mm/yyyy'),TABLE_NAME,FILE_NAME,MAX_ROW,DATAUPDATED,DELETE_STATUS," +
					"UPLOADED,NOT_UPLOADED,ERROR,ROW_STATUS,UNKNOWN_COLUMNS,UPLOAD_STATUS,REMARKS from " +
					"BHOME_NEWTRANSACTION_MASTER where transaction_date between to_date(?,'dd-mm-yyyy') " +
					"and to_date(?,'dd-mm-yyyy') order by TRAN_ID DESC";
			values[0]=this.start_datee;
			values[1]=this.end_datee;
			abc = new runner().multipleSelection(sql,values);

		} catch (Exception e) {}

		return abc;
	}
	
	public String Validation()
	{
		String error="";
		int currentYear=0,currentMonth=0,	currentDay=0;
		
		if("0".equalsIgnoreCase(start_date.trim()) || "0".equalsIgnoreCase(start_month.trim()) ||  "0".equalsIgnoreCase(start_year.trim()))
			  error="Please verify the start date<br>";
		if("0".equalsIgnoreCase(end_date.trim()) || "0".equalsIgnoreCase(end_month.trim()) ||  "0".equalsIgnoreCase(end_year.trim()) )
		    error+="Please verify the end date<br>";
		
		
		
		return error;
	}

	


}