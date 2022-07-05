package com.highradius;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.highradius.Invoice;

@WebServlet("/Fetch")
public class Fetch extends HttpServlet{
     
	private static final long serialVersionUID = 1L;
	private String url="jdbc:mysql://localhost:3306/grey_goose?zeroDateTimeBehavior=convertToNull";
	private String uname="root";
	private String passwd="root";
	private String query="select sl_no,business_code,cust_number,clear_date,buisness_year,doc_id,posting_date,document_create_date,due_in_date,invoice_currency,document_type,posting_id,total_open_amount,baseline_create_date,cust_payment_terms,invoice_id from winter_internship limit 100;";
	ArrayList<Invoice> AllInvoice = new ArrayList<Invoice>();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
  
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found " + e);
        }
        
        try {
        	int sl_no;
            String business_code="";
            long cust_number;
            String clear_date="";
            int buisness_year;
            long doc_id;
            String posting_date="";
            String document_create_date="";
            String due_in_date="";
            String invoice_currency="";
            String document_type="";
            int posting_id;
            double total_open_amount;
            String baseline_create_date="";
            String cust_payment_terms="";
            long invoice_id;
        	
            Connection conn = DriverManager.getConnection(url,uname,passwd);
            System.out.println("connection successful");
            Statement st = conn.createStatement();
  
            ResultSet rs = st.executeQuery(query);
            while(rs.next())
            {
            Invoice obj = new Invoice();
            sl_no=rs.getInt(1);
            business_code=rs.getString(2);
            cust_number=rs.getLong(3);
            clear_date=rs.getString(4);
            buisness_year=rs.getInt(5);
            doc_id=rs.getLong(6);
            posting_date=rs.getString(7);
            document_create_date=rs.getString(8);
            due_in_date=rs.getString(9);
            invoice_currency=rs.getString(10);
            document_type=rs.getString(11);
            posting_id=rs.getInt(12);
            total_open_amount=rs.getDouble(13);
            baseline_create_date=rs.getString(14);
            cust_payment_terms=rs.getString(15);
            invoice_id=rs.getLong(16);
            
            obj.setSl_no(sl_no);
            obj.setBusiness_code(business_code);
            obj.setCust_number(cust_number);
            obj.setClear_date(clear_date);
            obj.setBuisness_year(buisness_year);
            obj.setDoc_id(doc_id);
            obj.setPosting_date(posting_date);
            obj.setDocument_create_date(document_create_date);
            obj.setDue_in_date(due_in_date);
            obj.setInvoice_currency(invoice_currency);
            obj.setDocument_type(document_type);
            obj.setPosting_id(posting_id);
            obj.setTotal_open_amount(total_open_amount);
            obj.setBaseline_create_date(baseline_create_date);
            obj.setCust_payment_terms(cust_payment_terms);
            obj.setInvoice_id(invoice_id);
            
            AllInvoice.add(obj);
            
            }
            
            Gson gson = new Gson(); 
            String result = gson.toJson(AllInvoice);
            response.getWriter().print(result);
            
            
            rs.close();
            st.close();
            conn.close();
            System.out.println("connection closed");
              
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

