
package com.maan.common.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.StringTokenizer;


public class ErrorInfo extends OracleDateConversion
{
    
    /** The connection is used for getting Database Connection. */
    public Connection con=null;
    
    /** The errorId is used for holding the error code. */
    private String errorId;

    /** The errorDesc is used for describing the error code. */
    private String errorDesc;

    /** The stringBufferError is used to store group of errors. */
    private StringBuffer stringBufferError;

    /** The status is used to store the Status Code. */
    private String status;

    /**
    *  The static final values that are used as codes 
    *  when querying the database are as follows.
    */
    public static final int MODE_INSERT = 0;
    public static final int MODE_UPDATE = 1;
	
	
	public static final String Y_STATUS   =  "Y";
    public static final String A_STATUS   =  "A";
    public static final String D_STATUS   =  "D";

    public static final String ERROR_MASTER_STATUS = new String("Y");
    public static final String ERROR_MASTER_DELETED_STATUS = new String("D");
        
	StringBuffer error_buff= new StringBuffer();
	public  String error ="";

    /**
     * Initializes a newly created ErrorInfo object.
     */
    public ErrorInfo() 
	{
        this.con = null;
        this.errorId = null;
        this.errorDesc = null;
        this.status = null;
    }

    /**
     * Initializes a newly created ErrorInfo object so that it 
     * represents the error Description for a errorId with "Y" status.
     *
     * @param      errorId      the key field error_id as java.lang.String.
     * @param      connection   the java.sql.Connection object.
     *
     */
    public ErrorInfo(String errorId, Connection con) 
	{
        this.errorId = errorId;
        this.status = ERROR_MASTER_STATUS;
        this.con = con;
        this.undatabasify();
    }

    /**
    *  Parses a line and splits it while parsing into 
    *  an array when a delimiter is encountered.
    *
    * @param    line    the line that has to be parsed.
    * @param    delim   the delimiter for splitting.
    *
    * @return   a String array of required tokens.
    *
    * @see      java.util.StringTokenizer
    *
    */
    public static String[] split(String line, String delim) 
	{
      ArrayList arrayList = new ArrayList();
      if (line == null) return new String[0];
      StringTokenizer token = new StringTokenizer(line, delim);
      while(token.hasMoreTokens())
         arrayList.add(token.nextToken());

      return ( String[] ) arrayList.toArray(new String[0]);
    }

    
    /**
    *   Getter and Setter JavaBean Methods for 
    *   getting and setting the conection.
    */
    public void setCon(Connection con) 
	{
        this.con = con;
    }
    public Connection getCon() 
	{
        return con;
    }

    

    /**
    *   Getter and Setter JavaBean Methods for 
    *   getting and setting the errorId.
    */
    public void setErrorId(String errorId) 
	{
        this.errorId = errorId;
    }
    public String getErrorId() 
	{
        return errorId;
    }


    
    /**
    *   Getter and Setter JavaBean Methods for 
    *   getting and setting the errorDesc.
    */
    public void setErrorDesc(String errorDesc) 
	{
        this.errorDesc = errorDesc;
    }
    public String getErrorDesc() 
	{
        return errorDesc;
    }



    /**
    *   Getter and Setter JavaBean Methods for 
    *   getting and setting the status.
    */
    public void setStatus(String status) 
	{
        this.status = status;
    }
    public String getStatus() 
	{
        return status;
    }
    
    /**
    *  Insert/Update the err0rId,errorDescription into error_master table.
    *
    * @see      java.sql.PreparedStatement
    * @see      java.sql.ResultSet
    *
    */
    public void databasify(int mode) 
	{
        Statement statement;
        String databasifyQuery = null;
        ResultSet resultSet;
        String status = (getStatus() == null || "".equals(getStatus()) || " ".equals(getStatus())) ? ERROR_MASTER_STATUS : getStatus();

        clearError();

        try {
            if (mode == MODE_UPDATE) {
                databasifyQuery = "UPDATE error_master SET error_desc = '" + getErrorDesc() + "', status = '" + status + "' WHERE error_id = '" + getErrorId() + "'";

                if (con == null) setError("Connection Null");
                statement = con.createStatement();
                statement.executeUpdate(databasifyQuery);
                setError(databasifyQuery);
            }
        }
        catch (SQLException sqlException) {
            setError(sqlException.getMessage());
        } 
        catch (Exception exception) {
            setError(exception.getMessage());
        }
    }
    

    
    /**
    *  Select the error description for the
    *  particular errorId from error_master table 
    *  and set it to appropriate methods.
    *
    * @see      java.sql.PreparedStatement
    * @see      java.sql.ResultSet
    *
    */

    public void undatabasify() 
	{
        PreparedStatement preparedStatement;
        String undatabasifyQuery = null;
        ResultSet resultSet;
        String errorMessage = "";

        clearError();

        try 
		{
                
            undatabasifyQuery = "SELECT * FROM error_master WHERE error_id = ? AND status = ?";
            if (con == null) setError("Connection Null");

            preparedStatement = con.prepareStatement(undatabasifyQuery);
            preparedStatement.setString(1,getErrorId());
            preparedStatement.setString(2,(isEmpty(getStatus())) ? Y_STATUS : getStatus());

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) 
			errorMessage = resultSet.getString("error_desc");
            setErrorDesc(errorMessage);
        }
        catch (SQLException sqlException) 
		{
            setError(sqlException.getMessage());
        } 
        catch (Exception exception) 
		{
            setError(exception.getMessage());
        }
    }

    /**
    *  Select all the error descriptions 
    *  from error_master table 
    *
    * @see      java.sql.Statement
    * @see      java.sql.ResultSet
    *
    */
    public Hashtable getErrorHash() 
	{
        Statement statement;
        String getAllErrorsQuery = "";
        ResultSet resultSet;
        Hashtable errorHash = new Hashtable();

        clearError();

        try 
		{
                
            getAllErrorsQuery = "SELECT * FROM error_master WHERE status='Y' ORDER BY error_id";

            if (con == null) setError("Connection Null");

            statement = con.createStatement();
            resultSet = statement.executeQuery(getAllErrorsQuery);

            while (resultSet.next()) 
			{
                String[] strArray = new String[2];
                strArray[0] = resultSet.getString("error_id");
                strArray[1] = resultSet.getString("error_desc");
                errorHash.put(strArray[0],strArray);
            }
        }
        catch (SQLException sqlException) 
		{
            setError(sqlException.getMessage());
        } 
        catch (Exception exception) 
		{
            setError(exception.getMessage());
        }
        return errorHash;
    }


    /** The errorBufferForDebugging is used as a buffer to capture errors.  */
    private String errorBufferForDebugging = new String( " " );

    /**
    *   Getter and Setter JavaBean Methods for 
    *   getting and setting the errorBufferForDebugging.
    */
	public void setError(String s)
	{
		String s1 ="<br>* "+s.trim();
		error_buff.append(s1);
	}
	
	public String getError()
	{
		return error_buff.toString();
	}
/*	
  public void setError(String e) 
	{
		errorBufferForDebugging += "\n<br>" + e; 
	}
    public String getError()
	{
		return errorBufferForDebugging; 
	}  */
    /** The methods to empty the contents of the error buffer. */
    public void clearError() 
	{
		errorBufferForDebugging = "\n"; 
	}
    public void clearError(String str) 
	{
		errorBufferForDebugging = "\n" + str; 
	}

	/*public void setGroupError(String str){
	 
	  stringBufferError.append(str);

	}

	public String getGroupError(){

	  return stringBufferError.toString();
	}
*/
    public boolean isEmpty(String value) 
	{
       if (value == null || "".equals(value) || " ".equals(value)) return true;
       else return false;
    } 
}