package bean;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

import com.maan.DBCon.DBConnection;

import proj.sql.QueryBuilder;

public class getTranCurrent_Details {
	Connection conn = null;

	Statement smt = null;

	String qry = null;

	String data = null;

	int Tid = 0;

	// transaction information
	public String[][] TranProcessDetails(int Tranid, String status,
			String start_date, String end_date) {
		String[][] abc = new String[0][0];
		String sql = "";

		Tid = Tranid;

		try {
			conn = DBConnection.getInstance().getDBConnection();

			smt = conn.createStatement();
			QueryBuilder qry = new QueryBuilder(smt);
			System.out.println("connnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn");

			if ("total".equalsIgnoreCase(status))
				sql = "select TRAN_ID,FILE_ID,TRANSACTION_DATE,TABLE_NAME,FILE_NAME,MAX_ROW,DATAUPDATED,DELETE_STATUS,UPLOADED,NOT_UPLOADED,ERROR,ROW_STATUS,UNKNOWN_COLUMNS,UPLOAD_STATUS,REMARKS from BHOME_NEWTRANSACTION_MASTER where to_char(transaction_date,'YYYY-MM-DD') between '"
						+ start_date
						+ "' and  '"
						+ end_date
						+ "' order by TRAN_ID DESC";

			if ("current".equalsIgnoreCase(status))
				sql = "select TRAN_ID,FILE_ID,TRANSACTION_DATE,TABLE_NAME,FILE_NAME,MAX_ROW,DATAUPDATED,DELETE_STATUS,UPLOADED,NOT_UPLOADED,ERROR,ROW_STATUS,UNKNOWN_COLUMNS,UPLOAD_STATUS,AVAYA_DETAILS from RSA_NEWTRANSACTION_MASTER where TRAN_ID="
						+ Tid + " order by FILE_ID";

			if ("avaya_tran".equalsIgnoreCase(status))
				sql = "select TRAN_ID,to_char(TRAN_DATE,'DD-MM-YYYY'),to_char(START_DATE,'DD-MM-YYYY'),to_char(END_DATE,'DD-MM-YYYY'),APPROVAL_STATUS,FINAL_STATUS,PRODUCT_CODE,CAMP_CODE,TOTAL_ROW from RSA_AVAYATRANSACTION_MASTER where to_char(tran_date,'YYYY-MM-DD') between '"
						+ start_date
						+ "' and  '"
						+ end_date
						+ "' order by TRAN_ID DESC";

			System.out.println("Query is" + sql);
			abc = qry.getResultSet(sql);

			System.out.println("Query is" + sql);
			System.out.println("abc length" + abc.length);

		} catch (Exception e) {

		} finally {
			try {

				if (smt != null)
					smt.close();
				if (conn != null)
					conn.close();

			} catch (Exception e) {
			}
		}

		return abc;
	}

	public String ReadFiles(String pathh) {
		String status = "";
		File file = new File(pathh);
		try {

			FileInputStream fis = null;
			BufferedInputStream bis = null;
			DataInputStream dis = null;

			fis = new FileInputStream(file);
			// Here BufferedInputStream is added for fast reading.
			bis = new BufferedInputStream(fis);
			dis = new DataInputStream(bis);

			// dis.available() returns 0 if the file does not have more lines.
			while (dis.available() != 0) {

				// this statement reads the line from the file and print it to
				// the console.
				status += dis.readLine();
			}

			// dispose all the resources after using them.
			fis.close();
			bis.close();
			dis.close();

		} catch (FileNotFoundException e) {
			status = pathh;
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("status is" + status);
		return status;
	}

	public String getSpace(int no) {
		String space = "";
		for (int i = 0; i < no; i++) {
			space += " ";
		}
		return space;

	}

	public String getDate(String oldDate) {
		boolean flag = true;
		int currentYear = 0, currentMonth = 0, currentDay = 0;
		String start_date = "", start_month = "", start_year = "", FinalDate = "";
		java.util.Date sysDate = null;
		// System.out.println(" old date is "+oldDate);
		try {
			sysDate = new java.util.Date(oldDate);
		} catch (Exception e) {
			System.out.println("prob in date conv" + e);
			e.printStackTrace();
			flag = false;
		}

		if (flag) {
			currentYear = sysDate.getYear();
			currentMonth = sysDate.getMonth();
			currentDay = sysDate.getDate();
			if (currentYear < 1900)
				currentYear = currentYear + 1900;

			if (currentDay <= 9)
				start_date = "0" + currentDay;
			else
				start_date = "" + currentDay;

			if ((currentMonth + 1) <= 9)
				start_month = "0" + (currentMonth + 1);
			else
				start_month = "" + (currentMonth + 1);

			start_year = "" + currentYear;

			FinalDate = start_date + "-" + start_month + "-" + start_year;
			FinalDate = FinalDate.trim();
		} else {
			FinalDate = oldDate;
		}

		// System.out.println(" FinalDate is "+FinalDate);
		return FinalDate;
	}

}