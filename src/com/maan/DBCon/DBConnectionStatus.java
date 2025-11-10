package com.maan.DBCon;

import javax.servlet.ServletRequest;

public class DBConnectionStatus
{

    private ServletRequest request;
    private String status;
    public static String statusStatic = "";

    public DBConnectionStatus()
    {
        request = null;
        status = "";
    }

    public void setRequest(ServletRequest servletrequest)
    {
        request = servletrequest;
    }

    public void setStatus(String s)
    {
        status = s;
    }

    public String getStatus()
    {
        return status;
    }

    public static void setStatusStatic(String s)
    {
        s = s;
    }

    public static String getStatusStatic()
    {
        return statusStatic;
    }

}
