package test;

import java.io.PrintStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DBConnection
{

    public DBConnection()
    {
    }

    public String getTables()
    {
        String finalResult;
        Statement pre;
        Connection con;
        finalResult = null;
        pre = null;
        con = null;
        ResultSet rs = null;
        try
        {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@221.200.200.4:1521:QICDB2", "T001_ROI", "doh#ROI_001");
            pre = con.createStatement();
//            ResultSet rs;
            for(rs = pre.executeQuery("select sysdate from dual"); rs.next();)
            {
                finalResult = rs.getString(1);
            }

            rs.close();
        }
        catch(Exception e)
        {
            finalResult = "database not connected";
            System.out.println((new StringBuilder("Error in runner multipleSelection preparedstatement ...")).append(e.toString()).toString());
            e.printStackTrace();
        }
        Exception exception;
//        exception;
        try
        {
            if(pre != null)
            {
                pre.close();
            }
            if(con != null)
            {
                con.close();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
//        throw exception;
        try
        {
            if(pre != null)
            {
                pre.close();
            }
            if(con != null)
            {
                con.close();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
//        break MISSING_BLOCK_LABEL_211;
        try
        {
            if(pre != null)
            {
                pre.close();
            }
            if(con != null)
            {
                con.close();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return finalResult;
    }

    public String[][] getTablesDetails()
    {
        String result[][];
        List list;
        Statement pre;
        Connection con;
        result = new String[0][0];
        list = new ArrayList();
        pre = null;
        con = null;
        ResultSet rs = null;
        try
        {
        	con=com.maan.DBCon.DBConnection.getInstance().getDBConnection();
            pre = con.createStatement();
//            ResultSet rs;
            String temp;
            for(rs = pre.executeQuery("select tname from tab"); rs.next(); list.add(temp))
            {
                temp = rs.getString(1);
            }

            rs.close();
            result = new String[list.size()][1];
            for(int i = 0; i < list.size(); i++)
            {
                result[i][0] = (String)list.get(i);
            }

        }
        catch(Exception e)
        {
            System.out.println((new StringBuilder("Error in runner multipleSelection preparedstatement ...")).append(e.toString()).toString());
            e.printStackTrace();
//            break MISSING_BLOCK_LABEL_257;
        }
        finally{
            try
            {
                if(pre != null)
                {
                    pre.close();
                }
                if(con != null)
                {
                    con.close();
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
    }
        return result;
    }
}
