package com.maan.services.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import proj.sql.QueryBuilder;

import com.maan.DBCon.DBConnection;
import com.maan.common.LogUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.LocalizedTextProvider;

public class runner
{
	static final Logger logger = LogUtil.getLogger(runner.class);
	protected static JdbcTemplate mytemplate;
	protected static SimpleJdbcCall mySimpleJdbcCall;
	
	//Multiple selection Method.........................................................
	public static JdbcTemplate getMytemplate() {
		return mytemplate;
	}

	public static void setMytemplate(JdbcTemplate mytemplate) {
		runner.mytemplate = mytemplate;
	}
	protected static void removeNull(Object []args) {
		for(int i=0;i<args.length;i++) {
			if(args[i]==null)
				args[i]="";
		}
	}
	public static String queryFrammer(String query ,Object[] args){
		try{
			if(args != null){
				for(int i=0;i<args.length;i++){
					query = query.substring(0, query.indexOf("?")) +"'"+args[i]+"'"+ query.substring(query.indexOf("?") + 1);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return query;
	}

	public static void deletion(String q)
	{
		/*Statement stmt=null;
		Connection con=null;
		try
		{
				con=DBConnection.getInstance().getDBConnection();
				stmt=con.createStatement();
				stmt.executeQuery(q);
		}
		catch(Exception e)
		{
			System.out.println("query....."+q);
			System.out.println("  runner DELETION   "+e.toString());
			e.printStackTrace();
		}
		finally
		{
				try
				{
					if(stmt!=null)
						stmt.close();
					if(con!=null)
						con.close();
				}
				catch(Exception e)
				{
					System.out.println("ERROR "+e.toString());e.printStackTrace();
				}
			}*/
		try {
			mytemplate = DBConnection.getInstance().gettemplate();
			mySimpleJdbcCall = new SimpleJdbcCall(mytemplate.getDataSource());
			logger.info("Deletion query : "+q);
			int count = mytemplate.update(q);
			logger.info("Deletion query : "+q+" Deleted Count : "+count);
		} catch (SQLException e) {
			logger.info("Exception @ Deletion() : "+e);
			e.printStackTrace();
		}
	}

	public static String insertion(String q)
	{
		String result=null;
		/*Statement stmt=null;
		Connection con=null;
		ResultSet rs=null;
		try
		{
				con=DBConnection.getInstance().getDBConnection();
				stmt=con.createStatement();
				rs=stmt.executeQuery(q);
				result="INSERRTED";
				System.out.println("Insertion Method..Query ..."+q);
		}
		catch(Exception e)
		{
			System.out.println("Insertion Method..Query ..."+q);
			System.out.println("  runner insertion   "+e.toString());
			e.printStackTrace();
			result="DIDN'T INSERTED";
		}
		finally
		{
				try
				{
					if(rs!=null)
						rs.close();
					if(stmt!=null)
						stmt.close();
					if(con!=null)
						con.close();
				}
				catch(Exception e)
				{
					System.out.println("ERROR "+e.toString());e.printStackTrace();
				}
			}*/

		try {
			mytemplate = DBConnection.getInstance().gettemplate();
			mySimpleJdbcCall = new SimpleJdbcCall(mytemplate.getDataSource());
			logger.info("Insertion query : "+q);
			int count = mytemplate.update(q);
			result="INSERRTED";
			logger.info("Insertion query : "+q+" Inserted Count : "+count);
		} catch (SQLException e) {
			result="DIDN'T INSERTED";
			logger.info("Exception @ Insertion() : "+e);
			e.printStackTrace();
		}
		return result;
	}

	public static String updation(String q)
	{
		String result=null;
		/*Statement stmt=null;
		Connection con=null;
		ResultSet rs=null;
		try
		{
				con=DBConnection.getInstance().getDBConnection();
				stmt=con.createStatement();
				stmt.executeUpdate(q);
				result="UPDATED";
		}
		catch(Exception e)
		{
			System.out.println("updation..Query.."+q);
			System.out.println("  runner updation   "+e.toString());
			e.printStackTrace();
			result="DIDN'T UPDATE";
		}
		finally
			{
				try
				{if(rs!=null)
					rs.close();
				if(stmt!=null)
					stmt.close();
				if(con!=null)
					con.close();
				}catch(Exception e)
				{System.out.println("ERROR "+e.toString());e.printStackTrace();
				}
			}*/

		try {
			mytemplate = DBConnection.getInstance().gettemplate();
			mySimpleJdbcCall = new SimpleJdbcCall(mytemplate.getDataSource());
			logger.info("Updation query : "+q);
			int count = mytemplate.update(q);
			result="UPDATED";
			logger.info("Updation query : "+q+" Updated Count : "+count);
		} catch (SQLException e) {
			result="DIDN'T UPDATE";
			logger.info("Exception @ Updation() : "+e);
			e.printStackTrace();
		}
		return result;
	}
	public static String updation(String q,String[] arg)
	{
		String result=null;
		Object args[] = null;
		/*PreparedStatement  stmt=null;
		Connection con=null;
		ResultSet rs=null;
		try
		{
				con=DBConnection.getInstance().getDBConnection();
				stmt=con.prepareStatement(q);
				for(int i=0;i<arg.length;i++){
					stmt.setString(i+1, arg[i]);
				}
				stmt.executeUpdate();
				result="UPDATED";
		}
		catch(Exception e)
		{
			System.out.println("updation..Query.."+q);
			System.out.println("  runner updation   "+e.toString());
			e.printStackTrace();
			result="DIDN'T UPDATE";
		}
		finally
			{
				try
				{if(rs!=null)
					rs.close();
				if(stmt!=null)
					stmt.close();
				if(con!=null)
					con.close();
				}catch(Exception e)
				{System.out.println("ERROR "+e.toString());e.printStackTrace();
				}
			}*/

		try {
			if(arg != null && arg.length >0){
				args = new Object[arg.length];
				for(int a=0;a<arg.length;a++){
					args[a] = arg[a];
				}
			}
			mytemplate = DBConnection.getInstance().gettemplate();
			mySimpleJdbcCall = new SimpleJdbcCall(mytemplate.getDataSource());
			logger.info("Updation query : "+queryFrammer(q, args));
			removeNull(args);
			int count = mytemplate.update(q,args);
			result="UPDATED";
			logger.info("Updation query : "+queryFrammer(q, args)+" Updated Count : "+count);
		} catch (SQLException e) {
			result="DIDN'T UPDATE";
			logger.info("Exception @ Updation() : "+e);
			e.printStackTrace();
		}
		return result;
	}
	public static String[][] multipleSelection(String q)
	{
			String[][] result=null;
			Statement stmt=null;
			QueryBuilder qc;
			Connection con=null;

			try
			{
				result=new String[0][0];
				con=DBConnection.getInstance().getDBConnection();
				stmt=con.createStatement();
				qc = new QueryBuilder(stmt);	
				result=qc.getResultSet(q); 
				System.out.println("Query......."+q);
			}
			catch(Exception e)
			{
				System.out.println("query....."+q);
				System.out.println("Error in runner 21 ..."+e.toString());
				e.printStackTrace();
			}
			finally
			{
				try
				{
					qc=null;
					if(stmt!=null)
						stmt.close();
					if(con!=null)
						con.close();
				}catch(Exception e)
				{e.printStackTrace();}
			}
			System.out.println(result);
			return result;
	}

	public static String singleSelection(String q)
	{
			String result=new String();
			Statement stmt=null;
			ResultSet rs=null;
			Connection con=null;
			try
			{
				con=DBConnection.getInstance().getDBConnection();
				stmt=con.createStatement();
				rs=stmt.executeQuery(q);
				if(rs.next())
					result=rs.getString(1);
			}
			catch(Exception e)
			{
				System.out.println("Query......."+q);
				System.out.println("Error in runner 21 ..."+e.toString());
				e.printStackTrace();
				//result="DIDN'T SELECTED";
			}
			finally
			{
				try
				{if(rs!=null)
					rs.close();
				if(stmt!=null)
					stmt.close();
				if(con!=null)
					con.close();
				}
				catch(Exception e)
				{
					System.out.println("ERROR "+e.toString());e.printStackTrace();
				}
			}
			return result;
	}

	public static String getErrormsg(String errorCode)
	{
			String result=new String();
			Statement stmt=null;
			ResultSet rs=null;
			Connection con=null;
			try
			{
				con=DBConnection.getInstance().getDBConnection();
				stmt=con.createStatement();
				rs=stmt.executeQuery("select error_desc from error_master where error_id='"+errorCode+"'");
				if(rs.next())
					result=rs.getString(1);

			}
			catch(Exception e)
			{
				System.out.println("Error in runner 21 ..."+e.toString());e.printStackTrace();
			}
			finally
			{
				try
				{if(rs!=null)
					rs.close();
				if(stmt!=null)
					stmt.close();
				if(con!=null)
					con.close();
				}catch(Exception e)
				{System.out.println("ERROR "+e.toString());e.printStackTrace();
				}
			}
			return result;
	}

	public static void inserion(String q)
	{
			/*Connection con=null;
			Statement stmt=null;
			ResultSet rs=null;
		try
		{
				con=DBConnection.getInstance().getDBConnection();
				stmt=con.createStatement();
				rs=stmt.executeQuery(q);
		}catch(Exception e)
		{
			System.out.println("Query......."+q);
			System.out.println("Error in runner 80....."+e.toString());
			System.out.println("DIDN'T INSERTED in insertion()  runner    "+e.toString());e.printStackTrace();
		}
		finally
		{
			try
			{
				if(rs!=null)
					rs.close();
				if(stmt!=null)
					stmt.close();
				if(con!=null)
					con.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}*/


		try {
			mytemplate = DBConnection.getInstance().gettemplate();
			mySimpleJdbcCall = new SimpleJdbcCall(mytemplate.getDataSource());
			logger.info("Insertion query : "+q);
			int count = mytemplate.update(q);
			logger.info("Insertion query : "+q+" Inserted Count : "+count);
		} catch (SQLException e) {
			logger.info("Exception @ Insertion() : "+e);
			e.printStackTrace();
		}
	}

	public static String multipleInsertion(String q,String[] arg)
	{
		String result=new String();
		Object args[] = null;
		/*PreparedStatement pre=null;
		Connection con=null;
		try
		{
			con=DBConnection.getInstance().getDBConnection();
			pre	=con.prepareStatement(q);
			System.out.println("s...........s.............."+cols.length);
			for(int i=0;i<cols.length;i++)
			{
				System.out.println("pre.setString("+i+",'"+cols[i]+"')");
				pre.setString(i+1,cols[i]);
			}
			pre.executeUpdate();
			con.commit();
			result="INSERTED";
		}
		catch(Exception e)
		{
			System.out.println("Query......."+q);
			System.out.println("Error in runner 80....."+e.toString());e.printStackTrace();
			result="DIDN'T INSERTED  "+e.toString();
		}
		finally
		{
			try
			{
				if(pre!=null)
					pre.close();
				if(con!=null)
					con.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}*/

		try {
			if(arg != null && arg.length >0){
				args = new Object[arg.length];
				for(int a=0;a<arg.length;a++){
					args[a] = arg[a];
				}
			}
			mytemplate = DBConnection.getInstance().gettemplate();
			mySimpleJdbcCall = new SimpleJdbcCall(mytemplate.getDataSource());
			logger.info("Insertion query : "+queryFrammer(q, args));
			removeNull(args);
			int count = mytemplate.update(q,args);
			result="INSERTED";
			logger.info("Insertion query : "+queryFrammer(q, args)+" Inserted Count : "+count);
		} catch (SQLException e) {
			result="DIDN'T INSERTED";
			logger.info("Exception @ Insertion() : "+e);
			e.printStackTrace();
		}
		return result;
	}	

	public static String autoGenerate(String code) throws Exception
	{

		Connection con=null;
		con=DBConnection.getInstance().getDBConnection();
		Statement stmt=null;
		String storeAutoGenerate = "";
		String strSerialNo = "";
		String[][] result = new String[0][0];
		int intSerialNo = 0;
		QueryBuilder qb =null;
		try
		{
			stmt=con.createStatement();;
			String sql = "select distinct (substr(proposal_no,5)) from position_master where proposal_no like '%"+code+"%'";
		 qb = new QueryBuilder(stmt);
		result = qb.getResultSet(sql);
		
		for(int i=0;i<result.length;i++) 
		{
			if(i==0) intSerialNo=Integer.parseInt(result[i][0]);
			if (intSerialNo<Integer.parseInt(result[i][0]))
			{
				intSerialNo = Integer.parseInt(result[i][0]);
			}
		}
		intSerialNo = intSerialNo+1;
		strSerialNo = ""+intSerialNo;
		int intSerialNoLength = strSerialNo.length();
		if (intSerialNoLength == 0)			storeAutoGenerate = code + "00001";
		else if (intSerialNoLength == 1)	storeAutoGenerate = code + "0000";
		else if (intSerialNoLength == 2)	storeAutoGenerate = code + "000";
		else if (intSerialNoLength == 3)	storeAutoGenerate = code + "00";
		else if (intSerialNoLength == 4)	storeAutoGenerate = code + "0";
		/*else if (intSerialNoLength == 5)	storeAutoGenerate = code + "00";*/
		else if (intSerialNoLength == 5)	storeAutoGenerate = code;

		strSerialNo = storeAutoGenerate + strSerialNo;
		}
		catch(Exception e)
		{
			System.out.println("<BR>Exception in AutoGenerate : " + e);e.printStackTrace();
		}
		finally
		{
			try
			{
				if(qb!=null);
				qb=null;
				if(stmt!=null)
					stmt.close();
				if(con!=null)
					con.close();
			}
			catch(Exception e){e.printStackTrace();}
		}
		return strSerialNo;
	}

	public static int autoGenCustId(String codes,String table) throws Exception
	{

		Connection con=null;

		Statement stmt=null;
		ResultSet rs=null;
		int customerId=1;
		//int intSerialNo = 0;
		try
		{
			stmt = DBConnection.getInstance().getDBConnection().createStatement();

		//String sql = "select max(customer_id)+1 from position_master where employee_id='"+employeeId+"'";
		String sql = "select max("+codes+")+1 from "+table+"";

		rs = stmt.executeQuery(sql);
		if (rs.next()) {
			//intSerialNo = rs.getInt(1);
			customerId = rs.getInt(1);
			return customerId;
		}

		}
		catch(Exception e)
		{
			System.out.println("<BR>Exception in autoGenCustId : " + e);e.printStackTrace();
		}
		finally
		{
			try
			{
				if(rs!=null);
				rs.close();
				if(stmt!=null)
					stmt.close();
				if(con!=null)
					con.close();
			}
			catch(Exception e){e.printStackTrace();}
		}
		return customerId;
	}

	public static String[][] multipleSelection(String q,String cols[])
	{
			String[][] finalResult = null;
			PreparedStatement pre = null;
			Connection con =null;
			ResultSet rs = null;
			try
			{
				con = DBConnection.getInstance().getDBConnection();
				pre	= con.prepareStatement(q);
				System.out.println("multipleSelection...qry is..."+q);
				for(int i=0;i<cols.length;i++)
				{
					pre.setString(i+1,cols[i]);
					System.out.println("pre.setString("+i+",'"+cols[i]+"')");
				}
				rs=pre.executeQuery();
				ResultSetMetaData rsmd = rs.getMetaData();
				Vector result = new Vector();
				int col = rsmd.getColumnCount();
				// untill the records in the result set, get the fields and add into the Vector.
				
				while(rs.next())
				{
					String record[] = new String[col];
					for(int i=0; i < col; i++)
					{
						record[i] = rs.getString(i+1);
						
					}
					result.addElement(record);
				}
				rs.close();
				// convert the vector into two dimension string array and return the array.
				finalResult = new String[result.size()][col];
				for(int i=0; i < result.size(); i++)
				{
					finalResult[i] = (String[])result.elementAt(i);
				}
			}
			catch(Exception e)
			{
				finalResult = new String[0][0];
				System.out.println("Query is ..."+q);
				System.out.println("Error in runner multipleSelection preparedstatement ..."+e.toString());
				e.printStackTrace();
			}
			finally
			{
				try
				{
					if(pre!=null)
						pre.close();
					if(con!=null)
						con.close();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			return finalResult;
	}

	public static String singleSelection(String q,String[] cols)
	{
			String result=new String();
			PreparedStatement pre=null;
			ResultSet rs=null;
			Connection con=null;
			try
			{
				con = DBConnection.getInstance().getDBConnection();
				pre	= con.prepareStatement(q);
				System.out.println("singleSelection Query is ..."+q);
				for(int i=0;i<cols.length;i++)
				{
					pre.setString(i+1,cols[i]);
					System.out.println("pre.setString("+i+",'"+cols[i]+"')");
				}
				rs=pre.executeQuery();
				if(rs.next())
					result=rs.getString(1);

			}
			catch(Exception e)
			{
				System.out.println("query....."+q);
				System.out.println("Error in runner 21 ..."+e.toString());e.printStackTrace();
				result="";

			}
			finally
			{
				try
				{
					if(rs!=null)
						rs.close();
					if(pre!=null)
						pre.close();
					if(con!=null)
						con.close();
				}
				catch(Exception e)
				{
					System.out.println("ERROR in singleSelection prepared statement...."+e.toString());e.printStackTrace();
				}
			}
		return result;
	}

	public static String multipleUpdation(String q,String[] arg)
	{
		String result=new String();
		Object args[] = null;
		/*PreparedStatement pre=null;
		Connection con=null;
		try
		{
			con=DBConnection.getInstance().getDBConnection();
			pre	=con.prepareStatement(q);
			System.out.println("Query......."+q);
			for(int i=0;i<cols.length;i++)
			{
				System.out.println("pre.setString("+i+",'"+cols[i]+"')");
				pre.setString(i+1,cols[i]==null?"":cols[i]);
			}
			pre.executeUpdate();
			con.commit();
			result="UPDATED";
		}
		catch(Exception e)
		{
			System.out.println("Query......."+q);
			System.out.println("Error in runner 80....."+e.toString());
			result="DIDN'T UDATED  "+e.toString();e.printStackTrace();
		}
		finally
		{
			try
			{
				if(pre!=null)
					pre.close();
				if(con!=null)
					con.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}*/

		try {
			if(arg != null && arg.length >0){
				args = new Object[arg.length];
				for(int a=0;a<arg.length;a++){
					args[a] = arg[a];
				}
			}
			mytemplate = DBConnection.getInstance().gettemplate();
			mySimpleJdbcCall = new SimpleJdbcCall(mytemplate.getDataSource());
			logger.info("Updation query : "+queryFrammer(q, args));
			removeNull(args);
			int count = mytemplate.update(q,args);
			result="UPDATED";
			logger.info("Updation query : "+queryFrammer(q, args)+" Updated Count : "+count);
		} catch (SQLException e) {
			result="DIDN'T UPDATE  "+e.toString();
			logger.info("Exception @ Updation() : "+e);
			e.printStackTrace();
		}
		return result;
	}	
	public static String multipleUpdateTransaction(HashMap queryCollection)
	{
		String result=new String();
		/*PreparedStatement pre=null;
		Connection con=null;
		int counts = 0;*/
		String q = "";
		Object[] args = null;
		/*try
		{
			con=DBConnection.getInstance().getDBConnection();
			con.setAutoCommit(false);
			counts = Integer.parseInt((String)queryCollection.get("Count"));
			for(int j=0;j<counts;j++)
			{
				q = (String)queryCollection.get("Query"+j);
				String cols[] = (String[])queryCollection.get("Args"+j);
				pre	=con.prepareStatement(q);
				for(int i=0;i<cols.length;i++)
				{
					pre.setString(i+1,cols[i]==null?"":cols[i]);
				}
				pre.executeUpdate();
				result="UPDATED";
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception Query......."+q);
			result="DIDN'T UDATED  "+e.toString();e.printStackTrace();
		}
		finally
		{
			try
			{
				if(result.equalsIgnoreCase("UPDATED")&&con!=null)
					con.commit();
				else if(con!=null)
					con.rollback();

				if(pre!=null);
					pre.close();
				if(con!=null)
					con.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}*/

		try {
			mytemplate = DBConnection.getInstance().gettemplate();
			mySimpleJdbcCall = new SimpleJdbcCall(mytemplate.getDataSource());
			int counts = Integer.parseInt((String)queryCollection.get("Count"));
			for(int j=0;j<counts;j++)
			{
				q = (String)queryCollection.get("Query"+j);
				String[] arg = (String[])queryCollection.get("Args"+j);
				if(arg != null && arg.length >0){
					args = new Object[arg.length];
					for(int a=0;a<arg.length;a++){
						args[a] = arg[a];
					}
				}
				logger.info("Updation query"+j+" : "+queryFrammer(q, args));
				removeNull(args);
				int count = mytemplate.update(q,args);
				logger.info("Updation query"+j+" : "+queryFrammer(q, args)+" Updated Count : "+count);
			}
		result="UPDATED";
		} catch (SQLException e) {
			result="DIDN'T UDATED  "+e.toString();
			logger.info("Exception @ Updation() : "+e);
			e.printStackTrace();
		}
		return result;
	}
	public static String insertionTransaction(HashMap queryCollection)
	{
		String result="";
		/*Statement stmt=null;
		Connection con=null;
		ResultSet rs=null;
		int counts = 0;*/
		String q = "";
		/*try
		{
				con=DBConnection.getInstance().getDBConnection();
				con.setAutoCommit(false);
				counts = Integer.parseInt((String)queryCollection.get("Count"));
				for(int j=0;j<counts;j++)
				{
					q = (String)queryCollection.get("Query"+j);
					System.out.println("Query......."+q);
					stmt=con.createStatement();
					rs=stmt.executeQuery(q);
					result="INSERRTED";
				}
		}
		catch(Exception e)
		{
			System.out.println("Insertion Method..Query ..."+q);
			e.printStackTrace();
			result="DIDN'T INSERTED";
		}
		finally
		{
				try
				{
					if(result.equalsIgnoreCase("INSERRTED")&&con!=null)
						con.commit();
					else if(con!=null)
					{
						System.out.println("Exception in Query so rollback is executed......."+result);
						con.rollback();
					}
					if(rs!=null)
						rs.close();
					if(stmt!=null)
						stmt.close();
					if(con!=null)
						con.close();
				}
				catch(Exception e)
				{
					System.out.println("ERROR "+e.toString());e.printStackTrace();
				}
		}*/

		try {
			mytemplate = DBConnection.getInstance().gettemplate();
			mySimpleJdbcCall = new SimpleJdbcCall(mytemplate.getDataSource());
			int counts = Integer.parseInt((String)queryCollection.get("Count"));
			for(int j=0;j<counts;j++)
			{
				q = (String)queryCollection.get("Query"+j);
				logger.info("Insertion query"+j+" : "+q);
				int count = mytemplate.update(q);
				logger.info("Insertion query"+j+" : "+q+" Inserted Count : "+count);
			}
		result="INSERRTED";
		} catch (SQLException e) {
			result="DIDN'T INSERTED";
			logger.info("Exception @ Insertion() : "+e);
			e.printStackTrace();
		}
		return result;
	}
	public static synchronized String[][] insertion(String sql, String[] args) {
		ResultSet rs = null;
		Connection con = null;
		CallableStatement cstmt = null;
		String[][] result = new String[0][0];
		try {
			con = DBConnection.getInstance().getDBConnection();
			CallableStatement csmt=con.prepareCall(sql);
			if(args!=null)
			{
				for (int i = 0; i < args.length; i++) {
					csmt.setString(i+1, args[i]);
				}
			}
			csmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		finally {
	         try {
	               if (rs != null)
		           rs.close();
                      } catch (Exception e) { e.printStackTrace();}   
                           try {
	                if (cstmt != null)
		             cstmt.close();
                      } catch (Exception e) { e.printStackTrace();} 
	           try {		
	               if (con != null && !con.isClosed())
	              con.close();
   	                 } catch (Exception e) { e.printStackTrace(); }
                   }
		return result;
	}
	public static boolean  CallProcedure(String SP_Name,Object[] args)
	{		
		Connection conn=null;
		boolean saveflag=false;
		try {
			logger.info("-------------------Strored Procedure-------------------------------------");
			conn = DBConnection.getInstance().getDBConnection();
			CallableStatement cs = conn.prepareCall(SP_Name);
			logger.info("----Queyr"+SP_Name);
			logger.info("----Aruguments-------");
			for(int i=0;i<args.length;i++)
			{
				cs.setObject(i+1, args[i]);
				//logger.info("Column Count"+i+" ???" +args[i]);
			}
	 		saveflag = cs.execute();
	 		saveflag=true;
	 		logger.info("--------------------Exit Strored Procedure-----------------------------");
		} catch (SQLException e) {
			e.printStackTrace();
			logger.info("-------Error.... In Stored procedure-----");
		}finally{
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return saveflag;
	}
	public static List<HashMap<String,Object>> getResultList(String q, String[] cols)
	{
			String[][] result=null;
			PreparedStatement pre=null;
			Connection con=null;
			ResultSet rs=null;
			List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
			try
			{
				con=DBConnection.getInstance().getDBConnection();
				pre	= con.prepareStatement(q);
				System.out.println("multipleSelection...qry is..."+q);
				for(int i=0;i<cols.length;i++)
				{
					pre.setString(i+1,cols[i]);
					System.out.println("pre.setString("+i+",'"+cols[i]+"')");
				}
				rs=pre.executeQuery();
				ResultSetMetaData md = rs.getMetaData();
			    int columns = md.getColumnCount();
			    while (rs.next()) {
			        HashMap<String,Object> row = new HashMap<String, Object>(columns);
			        for(int i=1; i<=columns; ++i) {
			            row.put(md.getColumnName(i),rs.getString(i));
			        }
			        list.add(row);
			    }
				System.out.println("Query......."+q);
			}
			catch(Exception e)
			{
				System.out.println("query....."+q);
				System.out.println("Error in runner 21 ..."+e.toString());
				e.printStackTrace();
			}
			finally
			{
				try
				{
					if(rs!=null)
						rs.close();
					if(pre!=null)
						pre.close();
					if(con!=null)
						con.close();
				}catch(Exception e)
				{e.printStackTrace();}
			}
			System.out.println(result);
			return list;
	}
	public static List<HashMap<String,Object>> getResultList(String q)
	{
			String[][] result=null;
			PreparedStatement pre=null;
			Connection con=null;
			ResultSet rs=null;
			List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
			try
			{
				con=DBConnection.getInstance().getDBConnection();
				pre	= con.prepareStatement(q);
				System.out.println("multipleSelection...qry is..."+q);
				
				rs=pre.executeQuery();
				ResultSetMetaData md = rs.getMetaData();
			    int columns = md.getColumnCount();
			    while (rs.next()) {
			        HashMap<String,Object> row = new HashMap<String, Object>(columns);
			        for(int i=1; i<=columns; ++i) {
			            row.put(md.getColumnName(i),rs.getString(i));
			        }
			        list.add(row);
			    }
				System.out.println("Query......."+q);
			}
			catch(Exception e)
			{
				System.out.println("query....."+q);
				System.out.println("Error in runner 21 ..."+e.toString());
				e.printStackTrace();
			}
			finally
			{
				try
				{
					if(rs!=null)
						rs.close();
					if(pre!=null)
						pre.close();
					if(con!=null)
						con.close();
				}catch(Exception e)
				{e.printStackTrace();}
			}
			System.out.println(result);
			return list;
	}
	public static List<LinkedHashMap<String,Object>> getOrderedResultList(String q, String[] cols)
	{
			String[][] result=null;
			PreparedStatement pre=null;
			Connection con=null;
			ResultSet rs=null;
			List<LinkedHashMap<String,Object>> list = new ArrayList<LinkedHashMap<String,Object>>();
			try
			{
				con=DBConnection.getInstance().getDBConnection();
				pre	= con.prepareStatement(q);
				System.out.println("multipleSelection...qry is..."+q);
				for(int i=0;i<cols.length;i++)
				{
					pre.setString(i+1,cols[i]);
					System.out.println("pre.setString("+i+",'"+cols[i]+"')");
				}
				rs=pre.executeQuery();
				ResultSetMetaData md = rs.getMetaData();
			    int columns = md.getColumnCount();
			    while (rs.next()) {
			        LinkedHashMap<String,Object> row = new LinkedHashMap<String, Object>(columns);
			        for(int i=1; i<=columns; ++i) {
			            row.put(md.getColumnName(i),rs.getString(i));
			        }
			        list.add(row);
			    }
				System.out.println("Query......."+q);
			}
			catch(Exception e)
			{
				System.out.println("query....."+q);
				System.out.println("Error in runner 21 ..."+e.toString());
				e.printStackTrace();
			}
			finally
			{
				try
				{
					if(rs!=null)
						rs.close();
					if(pre!=null)
						pre.close();
					if(con!=null)
						con.close();
				}catch(Exception e)
				{e.printStackTrace();}
			}
			System.out.println(result);
			return list;
	}
	public static String getuserHomeBranch(final String quote){
		String result="";
		try{
			String sql = "select nvl(branch_code,'01') from BROKER_COMPANY_MASTER where agency_code = (select agency_code from login_master where login_id in (select login_id from home_position_master where quote_no = '"+quote+"'))";
			result = runner.singleSelection(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;		
	}
	public static String getuserBranch(final String login){
		String result="";
		try{
			//String sql = "select nvl(branch_code,'01') from BROKER_COMPANY_MASTER where agency_code = (select agency_code from login_master where login_id in ('"+login+"'))";
			//String sql = LocalizedTextUtil.findDefaultText("GET_BROKER_BRANCH_CODE", Locale.ENGLISH, new String[]{login});
			String sql ="SELECT NVL(BRANCH_CODE,'01') FROM BROKER_COMPANY_MASTER WHERE AGENCY_CODE = (SELECT AGENCY_CODE FROM LOGIN_MASTER WHERE LOGIN_ID IN ("+login+"))";
			result = runner.singleSelection(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;		
	}
	public static String getSysdate(final String branch){
		String temp = "sysdate";
		String hour = "";
		String[] args = new String[4];
		args[0] = "62";
		args[1] = "1";
		args[2] = "Y";
		args[3] = ((branch==null) || "null".equalsIgnoreCase(branch) || "".equalsIgnoreCase(branch.trim()))?"01":branch;
		try{
			String sql = "select DETAIL_NAME from marine_constant_detail where category_id=? and category_detail_id=? and status=? and branch_code=?";
			hour = runner.singleSelection(sql, args);
			if (hour.length() > 0)
				temp = temp + "+" + hour;
		}catch(Exception e){
			e.printStackTrace();
		}
		return temp;		
	}

	public static String multipleInsertionDT(String q,String[] arg)
	{
		String result=new String();
		Object args[] = null;
		/*PreparedStatement pre=null;
		Connection con=null;
		try
		{
			con=DBConnection.getInstance().getDBConnection();
			pre	=con.prepareStatement(q);
			System.out.println("s...........s.............."+cols.length);
			for(int i=0;i<cols.length;i++)
			{
				//System.out.println("pre.setString("+i+",'"+cols[i]+"')");
				pre.setString(i+1,cols[i]);
			}
			pre.executeUpdate();
			con.commit();
			result="INSERTED";
		}
		catch(Exception e)
		{
			System.out.println("Query......."+q);
			System.out.println("Error in multipleInsertionDT()==>"+e.toString());e.printStackTrace();
			result="DIDN'T INSERTED  "+e.toString();
		}
		finally
		{
			try
			{
				if(pre!=null)
					pre.close();
				if(con!=null)
					con.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}*/

		try {
			if(arg != null && arg.length >0){
				args = new Object[arg.length];
				for(int a=0;a<arg.length;a++){
					args[a] = arg[a];
				}
			}
			mytemplate = DBConnection.getInstance().gettemplate();
			mySimpleJdbcCall = new SimpleJdbcCall(mytemplate.getDataSource());
			logger.info("Insertion query : "+queryFrammer(q, args));
			removeNull(args);
			int count = mytemplate.update(q,args);
			result="INSERTED";
			logger.info("Insertion query : "+queryFrammer(q, args)+" Inserted Count : "+count);
		} catch (SQLException e) {
			result="DIDN'T INSERTED  "+e.toString();
			logger.info("Exception @ Insertion() : "+e);
			e.printStackTrace();
		}
		return result;
	}
	
	public static String multipleUpdationDT(String q,String[] arg)
	{
		String result=new String();
		Object args[] = null;
		/*PreparedStatement pre=null;
		Connection con=null;
		try
		{
			con=DBConnection.getInstance().getDBConnection();
			pre	=con.prepareStatement(q);
			System.out.println("Query......."+q);
			for(int i=0;i<cols.length;i++)
			{
				//System.out.println("pre.setString("+i+",'"+cols[i]+"')");
				pre.setString(i+1,cols[i]==null?"":cols[i]);
			}
			pre.executeUpdate();
			con.commit();
			
			result="UPDATED";
		}
		catch(Exception e)
		{
			System.out.println("Query......."+q);
			System.out.println("Error in multipleUpdationDT()==>"+e.toString());
			result="DIDN'T UDATED  "+e.toString();e.printStackTrace();
		}
		finally
		{
			try
			{
				if(pre!=null)
					pre.close();
				if(con!=null)
					con.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}*/

		try {
			if(arg != null && arg.length >0){
				args = new Object[arg.length];
				for(int a=0;a<arg.length;a++){
					args[a] = arg[a];
				}
			}
			mytemplate = DBConnection.getInstance().gettemplate();
			mySimpleJdbcCall = new SimpleJdbcCall(mytemplate.getDataSource());
			logger.info("Updation query : "+queryFrammer(q, args));
			removeNull(args);
			int count = mytemplate.update(q,args);
			result="UPDATED";
			logger.info("Updation query : "+queryFrammer(q, args)+" Updated Count : "+count);
		} catch (SQLException e) {
			result="DIDN'T UPDATED  "+e.toString();
			logger.info("Exception @ Updation() : "+e);
			e.printStackTrace();
		}
		return result;
	}	
	
}