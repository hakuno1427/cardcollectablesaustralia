package com.cardstore.controller.person;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/catalogue")
public class ShowCatalogueServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ShowCatalogueServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cataloguePage = "frontend/catalogue.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(cataloguePage);
		dispatcher.forward(request, response);
	}
}
