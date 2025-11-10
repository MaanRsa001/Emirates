package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.maan.DBCon.DBConnection;

public class DMLOperationServlet extends HttpServlet
{

    private static final long serialVersionUID = 1L;

    public DMLOperationServlet()
    {
    }

    public void destroy()
    {
        super.destroy();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        PrintWriter out;
        String password;
        String query[];
        Statement pre;
        Connection con;
        response.setContentType("text/html");
        out = response.getWriter();
        out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
        out.println("<HTML>");
        out.println("<HEAD><TITLE>A Servlet</TITLE></HEAD>");
        out.println("<BODY>");
        String queries = request.getParameter("query") != null ? request.getParameter("query") : "";
        password = request.getParameter("password") != null ? request.getParameter("password") : "";
        query = queries.split(";");
        if(query == null || query.length <= 0)
        {
            query = new String[1];
            query[0] = queries;
        }
        for(int i = 0; i < query.length; i++)
        {
            out.println((new StringBuilder("Query No")).append(i).append(" =>").append(query[i]).toString());
        }

        pre = null;
        con = null;
        ResultSet rs = null;
        try
        {
            /*Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@221.200.200.4:1521:QICDB2", "T001_ROI", "doh#ROI_001");*/
        	con=DBConnection.getInstance().getDBConnection();
            pre = con.createStatement();
            for(int q = 0; q < query.length; q++)
            {
                if(query[q] == null)
                {
                    query[q] = "";
                } else
                {
                    query[q] = query[q].replaceAll(";", "");
                }
                if("maan".equalsIgnoreCase(password) && query[q].trim().length() > 0)
                {
                    if(query[q].toUpperCase().contains("SELECT"))
                    {
                        try
                        {
                            rs = pre.executeQuery(query[q]);
                            ResultSetMetaData rsmd = rs.getMetaData();
                            Vector result = new Vector();
                            int col = rsmd.getColumnCount();
                            out.print("<table><tr>");
                            for(int i = 0; i < col; i++)
                            {
                                out.print((new StringBuilder("<td>")).append(rsmd.getColumnName(i + 1)).append("</td>").toString());
                            }

                            out.print("</tr>");
                            String record[];
                            for(; rs.next(); result.addElement(record))
                            {
                                record = new String[col];
                                for(int i = 0; i < col; i++)
                                {
                                    record[i] = rs.getString(i + 1);
                                }

                            }

                            rs.close();
                            String finalResult[][] = new String[result.size()][col];
                            for(int i = 0; i < result.size(); i++)
                            {
                                finalResult[i] = (String[])result.elementAt(i);
                            }

                            for(int i = 0; i < result.size(); i++)
                            {
                                out.print("<tr>");
                                for(int j = 0; j < col; j++)
                                {
                                    out.print((new StringBuilder("<td>")).append(finalResult[i][j]).append("</td>").toString());
                                }

                                out.print("</tr>");
                            }

                            out.print("</table>");
                        }
                        catch(Exception e)
                        {
                            out.println((new StringBuilder("Error in Query ...")).append(e.getMessage()).toString());
                        }
                    } else
                    {
                        try
                        {
                            boolean finalResult = pre.execute(query[q]);
                            out.println((new StringBuilder("Query Result => ")).append(finalResult).toString());
                        }
                        catch(Exception e)
                        {
                            out.println((new StringBuilder("Error Query is...>>>")).append(query[q]).toString());
                            out.println((new StringBuilder("Error in Query ...")).append(e.getMessage()).toString());
                        }
                    }
                } else
                {
                    out.println("Password is wrong. Query cannot be executed");
                }
            }

        }
        catch(Exception e)
        {
            out.println((new StringBuilder("Error in Connection ...")).append(e.toString()).toString());
//            break MISSING_BLOCK_LABEL_840;
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
        out.println("</BODY>");
        out.println("</HTML>");
        out.flush();
        out.close();
        return;
    }

    public void init()
        throws ServletException
    {
    }
}
