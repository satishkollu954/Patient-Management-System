package com.aw.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PatientDetails extends HttpServlet
{
	
    public void service(HttpServletRequest request,HttpServletResponse response) throws IOException
    {
    	PrintWriter out=response.getWriter();
    	response.setContentType("text/html");
    	String id=request.getParameter("id");
        String name=request.getParameter("name");
    	String gender=request.getParameter("gender");
    	String disease=request.getParameter("disease");
    	String history=request.getParameter("history");
    	
    	Connection con=null;
    	PreparedStatement pst=null;
    	
    	try
    	{
    		Class.forName("oracle.jdbc.driver.OracleDriver");
    		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "system");
    		pst=con.prepareStatement("insert into patient values(?,?,?,?,?)");
    		pst.setString(1, id);
    		pst.setString(2, name);
    		pst.setString(3, gender);
    		pst.setString(4, disease);
    		pst.setString(5, history);
    		
    	    int n=pst.executeUpdate();
    	    if(n>0)
    	    {
    	    	out.println("record is updated");
    	    }
    	    else
    	    {
    	    	out.println("record is not updated");
    	    }
    	}
         catch(ClassNotFoundException | SQLException e)
    	{
    		response.sendError(500,e.toString());
    	}
    	finally
    	{
    		try
    		{
    			if(pst!= null)
    			{
    				pst.close();
    			}
    			if(con!=null)
    			{
    				con.close();
    			}
    		}
    		catch(SQLException e) {}
    	
    	}
    }
}

