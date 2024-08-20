package com.cardstore.controller.person;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/add_listing")
public class ShowAddListingFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ShowAddListingFormServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String registerForm = "frontend/add_listing_form.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(registerForm);
		dispatcher.forward(request, response);
	}
}
