package com.maan.common.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import proj.sql.QueryBuilder;

public class runner {

	// Multiple selection
	// Method.........................................................
	public static String[][] multipleSelection(String q, Connection con) {
		String[][] result = null;
		Statement stmt = null;
		QueryBuilder qc;
		// Connection con=null;

		// //DBConnection1 DBConnection1=new DBConnection1();
		try {
			result = new String[0][0];
			// con=DBConnection1.getDBConnection();
			stmt = con.createStatement();
			System.out.println("Query......." + q);
			System.out.println("Statement......." + stmt);

			qc = new QueryBuilder(stmt);
			System.out.println("qc......." + qc);

			result = qc.getResultSet(q);
		} catch (Exception e) {
			System.out.println("Error in runner 21 ..." + e.toString());
			result = new String[0][1];
			result[0][1] = "SQL QUERIES NOT PROPERLY" + e.toString();

		} finally {
			try {
				qc = null;
				if (stmt != null)
					stmt.close();
				// if(con!=null)
				// con.close();

			} catch (Exception e) {
			}
		}
		System.out.println("LENGTH of return---->" + result.length);
		return result;
	}

	// Single Selection
	// Method.......................................................
	public static String singleSelection(String q, Connection con) {
		String result = new String();
		Statement stmt = null;
		ResultSet rs = null;
		// Connection con=null;

		System.out.println("Connection in ruunner method" + con);
		try {
			System.out.println("Connection in ruunner method" + con);
			// //DBConnection1 DBConnection1=new DBConnection1();
			// con=DBConnection1.getDBConnection();
			stmt = con.createStatement();
			System.out.println("Query......." + q);
			rs = stmt.executeQuery(q);
			if (rs.next())
				result = rs.getString(1);

		} catch (Exception e) {
			System.out.println("Error in runner 21 ..." + e.toString());
			result = "DIDN'T SELECTED";

		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				// if(con!=null)
				// con.close();
				// q=null;
			} catch (Exception e) {
				System.out.println("ERROR " + e.toString());
			}
		}
		return result;
	}

	public static String getErrormsg(String errorCode, Connection con) {

		String result = new String();
		Statement stmt = null;
		ResultSet rs = null;
		// Connection con=null;
		try {
			// //DBConnection1 DBConnection1=new DBConnection1();
			// con=DBConnection1.getDBConnection();
			stmt = con.createStatement();

			// System.out.println("Query......."+q);
			rs = stmt
					.executeQuery("select error_desc from error_master where error_id='"
							+ errorCode + "'");
			if (rs.next())
				result = rs.getString(1);

		} catch (Exception e) {
			System.out.println("Error in runner 21 ..." + e.toString());
			result = "DIDN'T SELECTED";

		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				// if(con!=null)
				// con.close();
				// q=null;
			} catch (Exception e) {
				System.out.println("ERROR " + e.toString());
			}
		}
		return result;
	}

	// Multiple Insertion
	// Method.........................................................
	public static void inserion(String q, Connection con) {
		// Connection con=null;
		Statement stmt = null;
		ResultSet rs = null;

		System.out.println("Connection in runner while inserting datas" + con);
		try {

			// //DBConnection1 DBConnection1=new DBConnection1();
			// con=DBConnection1.getDBConnection();
			stmt = con.createStatement();
			System.out.println("Query......." + q);
			rs = stmt.executeQuery(q);
		} catch (Exception e) {
			System.out.println("Error in runner 80....." + e.toString());
			System.out.println("DIDN'T INSERTED in insertion()  runner    "
					+ e.toString());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				// if(con!=null)
				// con.close();

			} catch (Exception e) {
			}

		}
	}

	public static String multipleInsertion(String q, String[] cols,
			Connection con) {
		String result = new String();
		PreparedStatement pre = null;
		// Connection con=null;
		try {
			// //DBConnection1 DBConnection1=new DBConnection1();

			System.out.println("Query......." + q);
			// con=DBConnection1.getDBConnection();
			pre = con.prepareStatement(q);
			System.out.println("s...........s.............." + cols.length);
			for (int i = 0; i < cols.length; i++) {
				System.out
						.println("pre.setString(" + i + ",'" + cols[i] + "')");
				pre.setString(i + 1, cols[i]);
			}
			pre.executeUpdate();
			con.commit();
			// pre.close();
			result = "INSERTED";
		} catch (Exception e) {
			System.out.println("Error in runner 80....." + e.toString());
			result = "DIDN'T INSERTED  " + e.toString();
		} finally {
			try {
				if (pre != null)
					pre.close();
				// if(con!=null)
				// con.close();

			} catch (Exception e) {
			}

		}
		return result;
	}

	public static synchronized String updation(String q, Connection con) {
		String result = null;
		Statement stmt = null;
		// Connection con=null;
		ResultSet rs = null;
		try {
			// con=DBConnection.getDBConnection1();
			stmt = con.createStatement();
			System.out.println("Query......." + q);
			stmt.executeUpdate(q);
			result = "UPDATED";
		} catch (Exception e) {
			System.out.println("  runner updation   " + e.toString());
			result = "DIDN'T UPDATE";
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
				// q=null;
			} catch (Exception e) {
				System.out.println("ERROR " + e.toString());
			}
		}
		return result;
	}

	// Multiple Updation
	// Method.......................................................
	public static String multipleUpdation(String q, String[] cols,
			Connection con) {
		String result = new String();
		PreparedStatement pre = null;
		// Connection con=null;
		try {
			// //DBConnection1 DBConnection1=new DBConnection1();
			System.out.println("Query......." + q);
			// con=DBConnection1.getDBConnection();
			pre = con.prepareStatement(q);
			for (int i = 0; i < cols.length; i++) {
				pre.setString(i + 1, cols[i] == null ? "" : cols[i]);
			}
			pre.executeUpdate();
			con.commit();

			result = "UPDATED";
		} catch (Exception e) {
			System.out.println("Error in runner 80....." + e.toString());
			result = "DIDN'T UDATED  " + e.toString();
		} finally {
			try {
				if (pre != null)
					;
				pre.close();
				// if(con!=null)
				// con.close();

			} catch (Exception e) {
			}
		}
		return result;
	}

	public static String autoGenerate(String code, Connection con)
			throws Exception {
		// DBConnection1 DBConnection1=new DBConnection1();
		// Connection con=null;
		// con=DBConnection1.getDBConnection();
		Statement stmt = null;
		String storeAutoGenerate = "";
		String strSerialNo = "";
		String[][] result = new String[0][0];
		int intSerialNo = 0;
		QueryBuilder qb = null;

		try {
			stmt = con.createStatement();
			;
			// String sql = "select max(substring(proposal_no,5))+1 from
			// position_master";
			String sql = "select distinct (substr(proposal_no,5)) from position_master where proposal_no like '%"
					+ code + "%'";
			qb = new QueryBuilder(stmt);
			result = qb.getResultSet(sql);
			/*
			 * rs = smt.executeQuery(sql); if (rs.next()) { strSerialNo =
			 * rs.getString(1); }
			 */
			for (int i = 0; i < result.length; i++) {
				if (i == 0)
					intSerialNo = Integer.parseInt(result[i][0]);
				if (intSerialNo < Integer.parseInt(result[i][0])) {
					intSerialNo = Integer.parseInt(result[i][0]);
				}
			}
			intSerialNo = intSerialNo + 1;
			strSerialNo = "" + intSerialNo;
			int intSerialNoLength = strSerialNo.length();
			if (intSerialNoLength == 0)
				storeAutoGenerate = code + "00001";
			else if (intSerialNoLength == 1)
				storeAutoGenerate = code + "0000";
			else if (intSerialNoLength == 2)
				storeAutoGenerate = code + "000";
			else if (intSerialNoLength == 3)
				storeAutoGenerate = code + "00";
			else if (intSerialNoLength == 4)
				storeAutoGenerate = code + "0";
			/* else if (intSerialNoLength == 5) storeAutoGenerate = code + "00"; */
			else if (intSerialNoLength == 5)
				storeAutoGenerate = code;

			strSerialNo = storeAutoGenerate + strSerialNo;
		} catch (Exception e) {
			System.out.println("<BR>Exception in AutoGenerate : " + e);
		} finally {
			try {
				if (qb != null)
					;
				qb = null;
				if (stmt != null)
					stmt.close();
				// if(con!=null)
				// con.close();
			} catch (Exception e) {
			}
		}
		return strSerialNo;
	}

	public static int autoGenCustId(String codes, String table, Connection con)
			throws Exception {

		// Connection con=null;

		Statement stmt = null;
		ResultSet rs = null;
		int customerId = 1;
		// int intSerialNo = 0;
		try {

			// DBConnection1 DBConnection1=new DBConnection1();
			// con=DBConnection1.getDBConnection();
			stmt = con.createStatement();

			// String sql = "select max(customer_id)+1 from position_master
			// where employee_id='"+employeeId+"'";
			String sql = "select max(" + codes + ")+1 from " + table + "";

			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				// intSerialNo = rs.getInt(1);
				customerId = rs.getInt(1);
				return customerId;
			}

		} catch (Exception e) {
			System.out.println("<BR>Exception in autoGenCustId : " + e);
		} finally {
			try {
				if (rs != null)
					;
				rs.close();
				if (stmt != null)
					stmt.close();
				// if(con!=null)
				// con.close();
			} catch (Exception e) {
			}
		}
		return customerId;
	}
	/*
	 * public static int autoPolicyNo() throws Exception {
	 * 
	 * Statement stmt=common.getConnection().createStatement(); ResultSet
	 * rs=null; String customerId=1; //int intSerialNo = 0; try {
	 * 
	 * //String sql = "select max(customer_id)+1 from position_master where
	 * employee_id='"+employeeId+"'"; String sql = "select max(customer_id)+1
	 * from position_master";
	 * 
	 * rs = stmt.executeQuery(sql); if (rs.next()) { //intSerialNo =
	 * rs.getInt(1); customerId = rs.getInt(1); return customerId; }
	 *  } catch(Exception e) { System.out.println("<BR>Exception in
	 * autoGenCustId : " + e); } finally { try { rs.close(); stmt.close(); }
	 * catch(Exception e){} } return customerId; }
	 */

}