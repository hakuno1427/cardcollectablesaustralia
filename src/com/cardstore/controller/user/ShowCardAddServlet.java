package com.cardstore.controller.user;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author Sera Jeong 12211242 Created Date: 22/08/2024
 */

@WebServlet("/card_add")
public class ShowCardAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ShowCardAddServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// add new game name to the list when they are available
		List<String> games = Arrays.asList("Magic The Gathering", "Pokemon");
		request.setAttribute("games", games);

		String cardAddForm = "frontend/card_add.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(cardAddForm);
		dispatcher.forward(request, response);
	}
}
