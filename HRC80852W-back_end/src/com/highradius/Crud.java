package com.highradius;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.highradius.Invoice;

public class Crud {

	public Connection getConnection()
	{
		 Connection conn =null;
		 String url ="jdbc:mysql://localhost:3306/grey_goose";
		 String user = "root";
		 String pass ="root";
			
			
				try {
					Class.forName("com.mysql.jdbc.Driver");
					conn =DriverManager.getConnection(url,user,pass);
				} catch (ClassNotFoundException e) {
					
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				return conn;

		}
	
	
		public ArrayList<Invoice> getData(String page,String rowsPerPage,String order,String orderBy)
		{
			ArrayList<Invoice> ALLInvoices =new ArrayList<Invoice>();
			String business_code,clear_date,posting_date,document_create_date,due_in_date,invoice_currency,document_type,baseline_create_date,cust_payment_terms,aging_bucket;
			Integer sl_no,cust_number,posting_id,buisness_year;
			Long doc_id,invoice_id;
			Double total_open_amount;
			try {
			 Connection conn = getConnection();
			 String sql="SELECT COUNT(*) FROM winter_internship";
			 PreparedStatement ps = conn.prepareStatement(sql);
			 ResultSet r = ps.executeQuery();
			 int totalCount=0;
			 while(r.next())
			 {
				 totalCount=r.getInt(1);
			 }
			 int pg=Integer.parseInt(page);
			 int rowsInPage=Integer.parseInt(rowsPerPage);
			 int count=rowsInPage*pg;
			 totalCount=totalCount-count;
			 String sql_query="";
			 if(orderBy.equals("sl_no") && order.equals("desc")) {
				 sql_query="SELECT * from winter_internship WHERE sl_no < "+totalCount+" ORDER BY "+orderBy+" "+order +" LIMIT "+rowsInPage;
				 System.out.println("Hi");
			 }
			 else {
				 sql_query="SELECT * from winter_internship WHERE sl_no > "+count+" ORDER BY "+orderBy+" "+order +" LIMIT "+rowsInPage;
				 System.out.println("Hello");
			 }
			 PreparedStatement pst = conn.prepareStatement(sql_query);
			 
			 ResultSet rs = pst.executeQuery();
			
			 while(rs.next())
			 {
					Invoice inv = new Invoice();
					sl_no = rs.getInt("sl_no");
					business_code = rs.getString("business_code");
					cust_number = rs.getInt("cust_number");
					clear_date = rs.getString("clear_date");
					buisness_year = rs.getInt("buisness_year");
					doc_id = rs.getLong("doc_id");
					posting_date = rs.getString("posting_date");
					document_create_date = rs.getString("document_create_date");
					due_in_date = rs.getString("due_in_date");
					invoice_currency = rs.getString("invoice_currency");
					document_type = rs.getString("document_type");
					posting_id = rs.getInt("posting_id");
					total_open_amount = rs.getDouble("total_open_amount");
					baseline_create_date = rs.getString("baseline_create_date");
					cust_payment_terms = rs.getString("cust_payment_terms");
					invoice_id = rs.getLong("invoice_id");
					aging_bucket = rs.getString("aging_bucket");
					
					inv.setSl_no(sl_no);
					inv.setBusiness_code(business_code);
					inv.setCust_number(cust_number);
					inv.setClear_date(clear_date);
					inv.setBuisness_year(buisness_year);
					inv.setDoc_id(doc_id);
					inv.setPosting_date(posting_date);
					inv.setDocument_create_date(document_create_date);
					inv.setDue_in_date(due_in_date);
					inv.setInvoice_currency(invoice_currency);
					inv.setDocument_type(document_type);
					inv.setPosting_id(posting_id);
					inv.setTotal_open_amount(total_open_amount);
					inv.setBaseline_create_date(baseline_create_date);
					inv.setCust_payment_terms(cust_payment_terms);
					inv.setInvoice_id(invoice_id);
//					inv.setAging_bucket(aging_bucket);
					
					ALLInvoices.add(inv);
					
					
			 }
			 
			 for(Invoice in: ALLInvoices)
			 {
				 System.out.println(in.toString());
			 }
			 
			}
			catch (Exception e) {
				e.printStackTrace();
				System.out.println("exception occur");
			}
			
			return ALLInvoices;
			
		
		}
		
		public ArrayList<Invoice> getSearchData(String page,String rowsPerPage,String cust_num,String order,String orderBy)
		{
			ArrayList<Invoice> ALLInvoices =new ArrayList<Invoice>();
			String business_code,clear_date,posting_date,document_create_date,due_in_date,invoice_currency,document_type,baseline_create_date,cust_payment_terms,aging_bucket;
			Integer sl_no,cust_number,posting_id,buisness_year;
			Long doc_id,invoice_id;
			Double total_open_amount;
			try {
			 Connection conn = getConnection();
			 String sql="SELECT COUNT(*) FROM winter_internship";
			 PreparedStatement ps = conn.prepareStatement(sql);
			 ResultSet r = ps.executeQuery();
			 int totalCount=0;
			 while(r.next())
			 {
				 totalCount=r.getInt(1);
			 }
			 int pg=Integer.parseInt(page);
			 int rowsInPage=Integer.parseInt(rowsPerPage);
			 int count=rowsInPage*pg;
			 totalCount=totalCount-count;
			 String sql_query="";
			 if(orderBy.equals("sl_no") && order.equals("desc")) {
				 sql_query="SELECT * from winter_internship WHERE  sl_no < "+totalCount+" AND cust_number LIKE ? ORDER BY "+orderBy+" "+order+" LIMIT "+rowsInPage;
			 }
			 else {
				 sql_query="SELECT * from winter_internship WHERE  sl_no > "+count+" AND cust_number LIKE ? ORDER BY "+orderBy+" "+order+" LIMIT "+rowsInPage;
			 }
			 PreparedStatement pst = conn.prepareStatement(sql_query);
			pst.setString(1, cust_num + "%");
			 ResultSet rs = pst.executeQuery();
			 while(rs.next())
			 {
					Invoice inv = new Invoice();
					sl_no = rs.getInt("sl_no");
					business_code = rs.getString("business_code");
					cust_number = rs.getInt("cust_number");
					clear_date = rs.getString("clear_date");
					buisness_year = rs.getInt("buisness_year");
					doc_id = rs.getLong("doc_id");
					posting_date = rs.getString("posting_date");
					document_create_date = rs.getString("document_create_date");
					due_in_date = rs.getString("due_in_date");
					invoice_currency = rs.getString("invoice_currency");
					document_type = rs.getString("document_type");
					posting_id = rs.getInt("posting_id");
					total_open_amount = rs.getDouble("total_open_amount");
					baseline_create_date = rs.getString("baseline_create_date");
					cust_payment_terms = rs.getString("cust_payment_terms");
					invoice_id = rs.getLong("invoice_id");
					aging_bucket = rs.getString("aging_bucket");
					
					inv.setSl_no(sl_no);
					inv.setBusiness_code(business_code);
					inv.setCust_number(cust_number);
					inv.setClear_date(clear_date);
					inv.setBuisness_year(buisness_year);
					inv.setDoc_id(doc_id);
					inv.setPosting_date(posting_date);
					inv.setDocument_create_date(document_create_date);
					inv.setDue_in_date(due_in_date);
					inv.setInvoice_currency(invoice_currency);
					inv.setDocument_type(document_type);
					inv.setPosting_id(posting_id);
					inv.setTotal_open_amount(total_open_amount);
					inv.setBaseline_create_date(baseline_create_date);
					inv.setCust_payment_terms(cust_payment_terms);
					inv.setInvoice_id(invoice_id);
//					inv.setAging_bucket(aging_bucket);
					
					ALLInvoices.add(inv);
					
					
			 }
			 
			 for(Invoice in: ALLInvoices)
			 {
				 System.out.println(in.toString());
			 }
			 
			}
			catch (Exception e) {
				e.printStackTrace();
				System.out.println("exception occur");
			}
			
			return ALLInvoices;
			
		
		} 
		
		public ArrayList<Invoice> getAdvSearchData(String page,String rowsPerPage,String doc,String invo,String cust,String buis,String order,String orderBy)
		{
			ArrayList<Invoice> ALLInvoices =new ArrayList<Invoice>();
			String business_code,clear_date,posting_date,document_create_date,due_in_date,invoice_currency,document_type,baseline_create_date,cust_payment_terms,aging_bucket;;
			Integer sl_no,cust_number,posting_id,buisness_year;
			Long doc_id,invoice_id;
			Double total_open_amount;
			try {
			 Connection conn = getConnection();
			 String sql="SELECT COUNT(*) FROM winter_internship";
			 PreparedStatement ps = conn.prepareStatement(sql);
			 ResultSet r = ps.executeQuery();
			 int totalCount=0;
			 while(r.next())
			 {
				 totalCount=r.getInt(1);
			 }
			 int pg=Integer.parseInt(page);
			 int rowsInPage=Integer.parseInt(rowsPerPage);
			 int count=rowsInPage*pg;
			 totalCount=totalCount-count;
			 String sql_query="";
			 if(orderBy.equals("sl_no") && order.equals("desc")) {
				 sql_query="SELECT * from winter_internship WHERE sl_no < "+totalCount+" AND doc_id LIKE ? AND invoice_id LIKE ? AND cust_number LIKE ? AND buisness_year LIKE ? ORDER BY "+orderBy+" "+order+" LIMIT "+rowsInPage;
			 }
			 else {
				 sql_query="SELECT * from winter_internship WHERE sl_no > "+count+" AND doc_id LIKE ? AND invoice_id LIKE ? AND cust_number LIKE ? AND buisness_year LIKE ? ORDER BY "+orderBy+" "+order+" LIMIT "+rowsInPage;
			 }
			 PreparedStatement pst = conn.prepareStatement(sql_query);
			pst.setString(1, doc + "%");
			pst.setString(2, invo + "%");
			pst.setString(3, cust + "%");
			pst.setString(4, buis + "%");
			 ResultSet rs = pst.executeQuery();
			 while(rs.next())
			 {
					Invoice inv = new Invoice();
					sl_no = rs.getInt("sl_no");
					business_code = rs.getString("business_code");
					cust_number = rs.getInt("cust_number");
					clear_date = rs.getString("clear_date");
					buisness_year = rs.getInt("buisness_year");
					doc_id = rs.getLong("doc_id");
					posting_date = rs.getString("posting_date");
					document_create_date = rs.getString("document_create_date");
					due_in_date = rs.getString("due_in_date");
					invoice_currency = rs.getString("invoice_currency");
					document_type = rs.getString("document_type");
					posting_id = rs.getInt("posting_id");
					total_open_amount = rs.getDouble("total_open_amount");
					baseline_create_date = rs.getString("baseline_create_date");
					cust_payment_terms = rs.getString("cust_payment_terms");
					invoice_id = rs.getLong("invoice_id");
					aging_bucket = rs.getString("aging_bucket");
					
					inv.setSl_no(sl_no);
					inv.setBusiness_code(business_code);
					inv.setCust_number(cust_number);
					inv.setClear_date(clear_date);
					inv.setBuisness_year(buisness_year);
					inv.setDoc_id(doc_id);
					inv.setPosting_date(posting_date);
					inv.setDocument_create_date(document_create_date);
					inv.setDue_in_date(due_in_date);
					inv.setInvoice_currency(invoice_currency);
					inv.setDocument_type(document_type);
					inv.setPosting_id(posting_id);
					inv.setTotal_open_amount(total_open_amount);
					inv.setBaseline_create_date(baseline_create_date);
					inv.setCust_payment_terms(cust_payment_terms);
					inv.setInvoice_id(invoice_id);
//					inv.setAging_bucket(aging_bucket);
					
					ALLInvoices.add(inv);
					
					
			 }
			 
			 for(Invoice in: ALLInvoices)
			 {
				 System.out.println(in.toString());
			 }
			 
			}
			catch (Exception e) {
				e.printStackTrace();
				System.out.println("exception occur");
			}
			
			return ALLInvoices;
		
}
}