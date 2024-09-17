package com.cardstore.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.cardstore.dao.ListingDAO;
import com.cardstore.entity.Listing;
import com.cardstore.service.CardServices;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public HomeServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ListingDAO listingDAO = new ListingDAO();

		List<Listing> listNewListings = listingDAO.listNewListingsWithCardDetails();

		request.setAttribute("listNewListings", listNewListings);
		
		CardServices cardServices = new CardServices(request, response);

		String homepage = "frontend/index.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(homepage);
		dispatcher.forward(request, response);
	}

}
