package com.highradius;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.highradius.Crud;
import com.google.gson.Gson;
import com.highradius.Invoice;

/**
 * Servlet implementation class AdvSearch
 */
@WebServlet("/AdvSearch")
public class AdvSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdvSearch() {
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
		 String doc_id=request.getParameter("doc_id");
		 String invoice_id=request.getParameter("invoice_id");
		 String cust_number=request.getParameter("cust_number");
		 String buisness_year=request.getParameter("buisness_year");
		 String order=request.getParameter("order");
		 String orderBy=request.getParameter("orderBy");
		  ArrayList<Invoice> data = fetchdata.getAdvSearchData(page,rowsPerPage,doc_id,invoice_id,cust_number,buisness_year,order,orderBy);
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
