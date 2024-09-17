package com.cardstore.controller.seller;

import java.io.IOException;

import com.cardstore.dao.UserDAO;
import com.cardstore.service.SellerService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/view_seller")
public class ViewSellerServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private UserDAO userDAO;
	
	

	public ViewSellerServlet() {
		this.userDAO = new UserDAO();
	}



	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		SellerService sellerService = new SellerService(req, resp);
		sellerService.viewSellerProfile();
	}
}
