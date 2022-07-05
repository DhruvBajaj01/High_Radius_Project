package com.highradius;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class Del
 */
@WebServlet("/Del")
public class Del extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Del() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    public Connection getConnection()
	{
		 Connection conn =null;
		 String url ="jdbc:mysql://localhost:3306/grey_goose";
		 String user = "root";
		 String pass ="root";
			
			
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					conn =DriverManager.getConnection(url,user,pass);
				} catch (ClassNotFoundException e) {
					
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				return conn;
		}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			 HashMap <Object,Object> Response=new HashMap<Object,Object>();
			 Connection conn = getConnection();
			 String data[]=request.getParameterValues("sl_nos");
			 String sql_query="DELETE FROM winter_internship where sl_no IN ("+data[0]+")";
			 PreparedStatement pst = conn.prepareStatement(sql_query);
			 if(pst.executeUpdate()>0) {
				 Response.put("insert",true);
			 }else {
				 Response.put("insert",false);
			 }
			  	Gson gson = new Gson();
				String respData = gson.toJson(Response);
				response.setHeader("Access-Control-Allow-Origin","*");
				response.getWriter().append(respData);
		     }catch(Exception e) {
			    e.printStackTrace();
		     }
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
