package com.highradius;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
 * Servlet implementation class SearchId
 */
@WebServlet("/SearchId")
public class SearchId extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchId() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				//response.getWriter().append("Served at: ").append(request.getContextPath());
				Crud fetchdata=new Crud();
				 String page=request.getParameter("page");
				 String rowsPerPage=request.getParameter("rowsPerPage");
				 String cust_num=request.getParameter("cust_number");
				 String order=request.getParameter("order");
				 String orderBy=request.getParameter("orderBy");
				  ArrayList<Invoice> data = fetchdata.getSearchData(page,rowsPerPage,cust_num,order,orderBy);
				  //System.out.println(data);
				  	
				  	Gson gson = new Gson();
					String respData = gson.toJson(data);
					response.setHeader("Access-Control-Allow-Origin","*");
					response.getWriter().print(respData);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

