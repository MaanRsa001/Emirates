package com.maan.services.util;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;

import org.apache.logging.log4j.Logger;

import proj.sql.QueryBuilder;

import com.maan.DBCon.DBConnection;
import com.maan.common.LogUtil;
import com.maan.common.exception.BaseException;

public class TestRunner implements Serializable 
{	
	private static final Logger logger = LogUtil.getLogger(TestRunner.class);
	private static final long serialVersionUID = -860793789317785385L;
	public static void deletion(final String query) throws BaseException {
		Statement statement = null;
		Connection connection = getDBConnection();
		try {		
			statement = connection.createStatement();
			statement.executeQuery(query);
			logger.info("query....." + query);
		} catch (Exception e) {
			logger.debug(e);
			try {
				getException(e);
			} catch (BaseException exception) {
				throw new BaseException(e, "Error Deletion :: ");
			}
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e) {
				logger.debug(e);
				try {
					getException(e);
				} catch (BaseException exception) {
					throw new BaseException(e, "Error");
				}
			}
		}
	}

	public static String insertion(final String query) throws BaseException {
		String result = null;
		Statement statement = null;
		Connection connection = getDBConnection();
		try {			
			statement = connection.createStatement();
			statement.executeQuery(query);
			result = "INSERRTED";
			logger.info("Insertion Method..Query ..." + query);
		}
		catch (Exception e) {
			logger.debug(e);
			try {
				getException(e);
			}
			catch (BaseException exception) {
				throw new BaseException(e, "Error Insertion :: ");
			}
		}
		finally {
			try {
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} 
			catch (Exception e) {
				logger.debug(e);
				try {
					getException(e);
				}
				catch (BaseException exception) {
					throw new BaseException(e, "Error");
				}
			}
		}
		return result;
	}
	/*public static String royalUpdation(final String query) throws BaseException {
		JdbcTemplate jdbcTemplate = DBConnection.getInstance().getJdbcTemplate();
		try{
			int status = jdbcTemplate.update(query);
			if(status>0){return "UPDATED";}
			else{return "Error In Updation";}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "Error In Updation";
	}*/
	public static String updation(final String query) throws BaseException {
		String result = null;
		Statement statement = null;
		Connection connection = getDBConnection();
		try {
			logger.info("Query is..."+query);			
			statement = connection.createStatement();
			statement.executeUpdate(query);
			result = "UPDATED";
		} catch (Exception e) {
			logger.debug(e);
			try {
				getException(e);
			} catch (BaseException exception) {
				throw new BaseException(e, "Error Updation :: ");
			}
		} 
		finally{
			try{
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} 
			catch (Exception e) {
				logger.debug(e);
				try {
					getException(e);
				} catch (BaseException exception) {
					throw new BaseException(e, "Error");
				}
			}
		}
		return result;
	}

	public static String[][] multipleSelection(final String query)
			throws BaseException {
		String[][] result = null;
		Statement statement = null;
		QueryBuilder builder;	
		Connection connection = getDBConnection();
		try {
			
			logger.info("Query......." + query);
			result = new String[0][0];			
			statement = connection.createStatement();
			builder = new QueryBuilder(statement);
			result = builder.getResultSet(query);
			
		} catch (Exception e) {
			logger.info("Query..."+query);
			logger.debug(e);
			try {
				getException(e);
			} catch (BaseException exception) {
				throw new BaseException(e, "Error");
			}
		} finally {
			try {
				builder = null;
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e) {
				logger.debug(e);
				try {
					getException(e);
				} catch (BaseException exception) {
					throw new BaseException(e, "Error");
				}
			}
		}
		return result;
	}

	public static String singleSelection(final String query)
			throws BaseException {
		String result = new String();
		Statement statement = null;
		ResultSet resultSet = null;
		Connection connection = getDBConnection();
		try {
			logger.info("Query is..."+query);
		
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			
			if (resultSet.next()) {
				result = resultSet.getString(1);
			}
		} catch (Exception e) {
			logger.debug(e);
			try {
				getException(e);
			} catch (BaseException exception) {
				throw new BaseException(e, "Error Single Selection ::");
			}
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e) {
				logger.debug(e);
				try {
					getException(e);
				} catch (BaseException exception) {
					throw new BaseException(e, "Error");
				}
			}
		}
		return result;
	}

	public static String getErrormsg(final String errorCode)
			throws BaseException {
		String result = new String();
		Statement statement = null;
		ResultSet resultSet = null;
		Connection connection = getDBConnection();
		try {
			
			statement = connection.createStatement();
			resultSet = statement
					.executeQuery("select error_desc from error_master where error_id in ("	+ errorCode + ")");
			if (resultSet.next()) {
				result = resultSet.getString(1);
			}

		} catch (Exception e) {
			logger.debug(e);
			try {
				getException(e);
			} catch (BaseException exception) {
				throw new BaseException(e, "Error Get Error Message ::");
			}
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e) {
				logger.debug(e);
				try {
					getException(e);
				} catch (BaseException exception) {
					throw new BaseException(e, "Error");
				}
			}
		}
		return result;
	}

	public static void inserion(final String query) throws BaseException {
		
		Statement statement = null;
		ResultSet resultSet = null;
		Connection connection = getDBConnection();
		try {
			
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			logger.info("Query......." + query);
		} catch (Exception e) {
			logger.debug(e);
			try {
				getException(e);
			} catch (BaseException exception) {
				throw new BaseException(e, "Error");
			}
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e) {
				logger.debug(e);
				try {
					getException(e);
				} catch (BaseException exception) {
					throw new BaseException(e, "Error");
				}
			}
		}
	}

	public static String multipleInsertion(final String query,
			final String[] cols) throws BaseException {
		String result = new String();
		PreparedStatement pre = null;
		Connection connection = getDBConnection();
		try {
			
			pre = connection.prepareStatement(query);
			logger.info("argument Length  :: " + cols.length);
			for (int i = 0; i < cols.length; i++) {
				logger.info("pre.setString(" + i + ",'" + cols[i] + "')");
				pre.setString(i + 1, cols[i]);
			}
			pre.executeUpdate();
			connection.commit();
			result = "INSERTED";
		} catch (Exception e) {
			logger.debug(e);
			try {
				getException(e);
			} catch (BaseException exception) {
				throw new BaseException(e, "Error");
			}
		} finally {
			try {
				if (pre != null) {
					pre.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e) {
				logger.debug(e);
				try {
					getException(e);
				} catch (BaseException exception) {
					throw new BaseException(e, "Error");
				}
			}
		}
		return result;
	}

	public static String[][] multipleSelection(final String query,
			final String cols[]) throws BaseException {
		String[][] finalResult = null;
		PreparedStatement pre = null;
		
		ResultSet resultSet = null;
		Connection connection = getDBConnection();
		try {
			pre = connection.prepareStatement(query);
			logger.info("multipleSelection...qry is..." + query);
			for (int i = 0; i < cols.length; i++) {
				pre.setString(i + 1, cols[i]);
				logger.info("pre.setString(" + i + ",'" + cols[i] + "')");
			}
			resultSet = pre.executeQuery();
			ResultSetMetaData rsmd = resultSet.getMetaData();
			Vector result = new Vector();
			int col = rsmd.getColumnCount();
			// untill the records in the result set, get the fields and add into
			// the Vector.

			while (resultSet.next()) {
				String record[] = new String[col];
				for (int i = 0; i < col; i++) {
					record[i] = resultSet.getString(i + 1);
				}
				result.addElement(record);
			}
			resultSet.close();
			// convert the vector into two dimension string array
			finalResult = new String[result.size()][col];
			for (int i = 0; i < result.size(); i++) {
				finalResult[i] = (String[]) result.elementAt(i);
			}

		} catch (Exception e) {
			logger.debug(e);
			try {
				getException(e);
			} catch (BaseException exception) {
				throw new BaseException(e, "Error");
			}

		} finally {
			try {
				if (pre != null) {
					pre.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e) {
				logger.debug(e);
				try {
					getException(e);
				} catch (BaseException exception) {
					throw new BaseException(e, "Error");
				}
			}
		}
		return finalResult;
	}

	public static String singleSelection(final String query, final String[] cols)
			throws BaseException {
		String result = new String();
		PreparedStatement pre = null;
		ResultSet resultSet = null;
		Connection connection = getDBConnection();
		try {			
			pre = connection.prepareStatement(query);
			logger.info("singleSelection Query is ..." + query);
			for (int i = 0; i < cols.length; i++) {
				pre.setString(i + 1, cols[i]);
				logger.info("pre.setString(" + i + ",'" + cols[i] + "')");
			}
			resultSet = pre.executeQuery();
			if (resultSet.next())
				result = resultSet.getString(1);

		} catch (Exception e) {
			logger.debug(e);
			try {
				getException(e);
			} catch (BaseException exception) {
				throw new BaseException(e, "Error");
			}

		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (pre != null) {
					pre.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e) {
				logger.debug(e);
				try {
					getException(e);
				} catch (BaseException exception) {
					throw new BaseException(e, "Error");
				}
			}
		}
		return result;
	}

	public static String multipleUpdation(final String query,
			final String[] cols) throws BaseException {
		String result = new String();
		PreparedStatement pre = null;
		Connection connection = getDBConnection();
		try {
			logger.info("Query......." + query);			
			pre = connection.prepareStatement(query);
			
			for (int i = 0; i < cols.length; i++) {
				logger.info("pre.setString(" + i + ",'" + cols[i] + "')");
				pre.setString(i + 1, cols[i] == null ? "" : cols[i]);
			}
			pre.executeUpdate();
			connection.commit();
			result = "UPDATED";
		} catch (Exception e) {
			logger.debug(e);
			try {
				getException(e);
			} catch (BaseException exception) {
				throw new BaseException(e, "Error");
			}
		} finally {
			try {
				if (pre != null) {
					pre.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e) {
				logger.debug(e);
				try {
					getException(e);
				} catch (BaseException exception) {
					throw new BaseException(e, "Error");
				}
			}
		}
		return result;
	}

	public static String multipleUpdateTransaction(final HashMap queryCollection)
			throws BaseException {
		String result = new String();
		PreparedStatement pre = null;
		
		int counts = 0;
		String q = "";
		Connection connection = getDBConnection();
		try {			
			connection.setAutoCommit(false);
			counts = Integer.parseInt((String) queryCollection.get("Count"));
			for (int j = 0; j < counts; j++) {
				q = (String) queryCollection.get("Query" + j);
				String cols[] = (String[]) queryCollection.get("Args" + j);
				pre = connection.prepareStatement(q);
				for (int i = 0; i < cols.length; i++) {
					pre.setString(i + 1, cols[i] == null ? "" : cols[i]);
				}
				pre.executeUpdate();
				result = "UPDATED";
			}
		} catch (Exception e) {
			try {
				getException(e);
			} catch (BaseException exception) {
				throw new BaseException(e, "Error");
			}
		} finally {
			try {
				if (result.equalsIgnoreCase("UPDATED") && connection != null) {
					connection.commit();
				} else if (connection != null) {
					connection.rollback();
				}

				if (pre != null) {
					pre.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e) {
				try {
					getException(e);
				} catch (BaseException exception) {
					throw new BaseException(e, "Error");
				}
			}
		}
		return result;
	}

	public static String insertionTransaction(final HashMap queryCollection) throws BaseException 
	{
		String result = "";
		Statement statement = null;		
		ResultSet resultSet = null;
		int counts = 0;
		String q = "";
		Connection connection = getDBConnection();
		try {			
			connection.setAutoCommit(false);
			counts = Integer.parseInt((String) queryCollection.get("Count"));
			for (int j = 0; j < counts; j++) {
				q = (String) queryCollection.get("Query" + j);
				logger.info("Query......." + q);
				statement = connection.createStatement();
				resultSet = statement.executeQuery(q);
				result = "INSERRTED";
			}
		} catch (Exception e) {
			logger.debug(e);
			try {
				getException(e);
			} catch (BaseException exception) {
				throw new BaseException(e, "Error");
			}
		} finally {
			try {
				if (result.equalsIgnoreCase("INSERRTED") && connection != null) {
					connection.commit();
				} else if (connection != null) {
					connection.rollback();
				}
				if (resultSet != null) {
					resultSet.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e) {
				logger.debug(e);
				try {
					getException(e);
				} catch (BaseException exception) {
					throw new BaseException(e, "Error");
				}
			}
		}
		return result;
	}

	private static BaseException getException(Exception e) throws BaseException {
		logger.info("Time ::"
				+ new Timestamp(new Date().getTime()).toString());
		logger.info("Sql error ::" + e);
		new BaseException(e, "Error");
		return (BaseException) e;
	}
	public static String[][] getCoreDatas(final String tableName, final String Condition,final StringBuffer qnos) throws BaseException {

		logger.info("getCoreDatas method()");
		logger.debug("- Enter");
		String getValue[][] = new String[0][0];
		try {
			String result[][] = new String[0][0];
			String cols[] = {tableName};
			result = multipleSelection("select case when data_type='DATE' then '''royaldates''||'||'to_char('||column_name||',''DD/MM/YYYY HH24:MI:SS'')' else column_name end FROM USER_TAB_COLUMNS " +
					"WHERE table_name=? order by column_id",cols);
			StringBuffer columnName = new StringBuffer();
			for (int i = 0; i <result.length; i++) {
				columnName.append(result[i][0]);
				columnName.append(",");
			}
			columnName.deleteCharAt(columnName.length()-1);
			getValue = multipleSelection("select "+columnName.toString()+" from " + tableName + " where "+Condition+" in("+qnos+") order by "+Condition);
		} catch (Exception e) {
			logger.debug(e);
		}
		logger.debug("- Exit");
		
		return getValue;

	}
	
	// Jasper Report
	
	public static ResultSet getMultipleResults(String q,String cols[])
	{
			PreparedStatement pre = null;
			
			ResultSet rs = null;
			Connection con = getDBConnection();
			try	{				
				pre	= con.prepareStatement(q);
				System.out.println("multipleSelection...qry is..."+q);
				for(int i=0;i<cols.length;i++)
				{
					pre.setString(i+1,cols[i]);
					System.out.println("pre.setString("+i+",'"+cols[i]+"')");
				}
				rs=pre.executeQuery();
			}
			catch(Exception e)
			{
				System.out.println("Query is ..."+q);
				System.out.println("Error in runner multipleSelection preparedstatement ..."+e.toString());
				e.printStackTrace();
			}
			finally
			{
				try	{
					/*if(pre!=null)
						pre.close();
					if(con!=null)
						con.close();*/
					
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
			return rs;
	}
	
	public static Connection getDBConnection()
	{
		Connection connection = null;
		try{		  
			/*Class.forName("oracle.jdbc.driver.OracleDriver");
			//connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.1.14:1521:twheeler","stp","twowheeler");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@araborient.revion.net:15210:arabor","araborient","ara$123");*/
			
			connection = DBConnection.getInstance().getDBConnection();
		}
		catch(Exception exception){			
		    exception.printStackTrace();
		}finally {
			if(connection!=null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return connection;
	}
}