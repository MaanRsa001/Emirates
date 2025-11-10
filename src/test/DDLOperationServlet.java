package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.maan.DBCon.DBConnection;

public class DDLOperationServlet extends HttpServlet
{

    private static final long serialVersionUID = 1L;

    public DDLOperationServlet()
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
        String query;
        String password;
        Statement pre;
        Connection con;
        response.setContentType("text/html");
        out = response.getWriter();
        out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
        out.println("<HTML>");
        out.println("<HEAD><TITLE>A Servlet</TITLE></HEAD>");
        out.println("<BODY>");
        query = request.getParameter("query") != null ? request.getParameter("query") : "";
        password = request.getParameter("password") != null ? request.getParameter("password") : "";
        out.println((new StringBuilder("Query is =>")).append(query).toString());
        pre = null;
        con = null;
        try
        {
            //Class.forName("oracle.jdbc.driver.OracleDriver");
            //con = DriverManager.getConnection("jdbc:oracle:thin:@221.200.200.4:1521:QICDB2", "T001_ROI", "doh#ROI_001");
        	con=DBConnection.getInstance().getDBConnection();
            pre = con.createStatement();
            if("maan".equalsIgnoreCase(password) && query.trim().length() > 0)
            {
                boolean finalResult = pre.execute(query);
                out.println((new StringBuilder("Query Result => ")).append(finalResult).toString());
            } else
            {
                out.println("Password is wrong. Query cannot be executed");
            }
        }
        catch(Exception e)
        {
            out.println((new StringBuilder("Error in Operation ...")).append(e.toString()).toString());
            e.printStackTrace();
//            break MISSING_BLOCK_LABEL_319;
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
