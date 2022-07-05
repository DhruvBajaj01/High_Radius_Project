package com.highradius;



import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.highradius.Crud;
import com.google.gson.Gson;
import com.highradius.Invoice;

/**
 * Servlet implementation class Add
 */
@WebServlet("/Add")
public class Add extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Add() {
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
//		 String sql="SELECT COUNT(*) FROM winter_internship";
//		 PreparedStatement ps = conn.prepareStatement(sql);
//		 ResultSet r = ps.executeQuery();
//		 int count=0;
//		 while(r.next())
//		 {
//			 count=r.getInt(1);
//		 }
		 String business_code=request.getParameter("business_code");
		 String cust_number=request.getParameter("cust_number");
		 String clear_date=request.getParameter("clear_date");
		 String buisness_year=request.getParameter("buisness_year");
		 String doc_id=request.getParameter("doc_id");
		 String posting_date=request.getParameter("posting_date");
		 String document_create_date=request.getParameter("document_create_date");
		 String due_in_date=request.getParameter("due_in_date");
		 String invoice_currency=request.getParameter("invoice_currency");
		 String document_type=request.getParameter("document_type");
		 String posting_id=request.getParameter("posting_id");
		 String total_open_amount=request.getParameter("total_open_amount");
		 String baseline_create_date=request.getParameter("baseline_create_date");
		 String cust_payment_terms=request.getParameter("cust_payment_terms");
		 String invoice_id=request.getParameter("invoice_id");
		 String sql_query="INSERT INTO winter_internship (business_code,cust_number,clear_date,buisness_year,doc_id,posting_date,document_create_date,due_in_date,invoice_currency,document_type,posting_id,total_open_amount,baseline_create_date,cust_payment_terms,invoice_id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		 PreparedStatement pst = conn.prepareStatement(sql_query);
		 //pst.setInt(1, count+1);
		 pst.setString(2, business_code);
		 pst.setInt(3, Integer.parseInt(cust_number));
		 Date date1=Date.valueOf(clear_date);
		 pst.setDate(4, date1);
		 pst.setInt(5, Integer.parseInt(buisness_year));
		 pst.setLong(6, Long.parseLong(doc_id));
		 Date date2=Date.valueOf(posting_date);
		 pst.setDate(7, date2);
		 Date date3=Date.valueOf(document_create_date);
		 pst.setDate(8, date3);
		 Date date4=Date.valueOf(due_in_date);
		 pst.setDate(9, date4);
		 pst.setString(10, invoice_currency);
		 pst.setString(11, document_type);
		 pst.setInt(12, Integer.parseInt(posting_id));
		 pst.setDouble(13, Double.parseDouble(total_open_amount));
		 Date date5=Date.valueOf(baseline_create_date);
		 pst.setDate(14, date5);
		 pst.setString(15, cust_payment_terms);
		 pst.setLong(16, Long.parseLong(invoice_id));
		  	
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
		//doGet(request, response);
	}

}

