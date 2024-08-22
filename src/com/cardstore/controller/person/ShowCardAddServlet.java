package com.cardstore.controller.person;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/card_add")
public class ShowCardAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ShowCardAddServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cardAddForm = "frontend/card_add.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(cardAddForm);
		dispatcher.forward(request, response);
	}
}
