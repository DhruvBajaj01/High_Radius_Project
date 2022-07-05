package com.highradius;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class Edit
 */
@WebServlet("/Edit")
public class Edit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Edit() {
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
		// TODO Auto-generated method stub
				try {
				 HashMap <Object,Object> Response=new HashMap<Object,Object>();
				 Connection conn = getConnection();
				 String sl_no=request.getParameter("sl_no");
				 String invoice_currency=request.getParameter("invoice_currency");
				 String cust_payment_terms=request.getParameter("cust_payment_terms");
				 System.out.println(sl_no);System.out.println(invoice_currency);System.out.println(cust_payment_terms);
				 String sql_query="UPDATE winter_internship SET invoice_currency = ? , cust_payment_terms = ? WHERE sl_no = ?";
				 PreparedStatement pst = conn.prepareStatement(sql_query);
				 pst.setInt(3, Integer.parseInt(sl_no));
				 pst.setString(1, invoice_currency);
				 pst.setString(2, cust_payment_terms);
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
