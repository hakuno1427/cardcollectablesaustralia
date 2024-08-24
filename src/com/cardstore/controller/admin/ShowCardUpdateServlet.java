package com.cardstore.controller.admin;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.cardstore.entity.Card;
import com.cardstore.dao.CardDAO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author Sera Jeong 12211242 Created Date: 22/08/2024
 */

@WebServlet("/card_update")
public class ShowCardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CardDAO cardDAO = new CardDAO();

	public ShowCardUpdateServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String serialNumber = request.getParameter("id");

		if (serialNumber != null && !serialNumber.trim().isEmpty()) {
			// get card information using the DAO
			Card card = cardDAO.get(serialNumber);

			if (card != null) {
				request.setAttribute("card", card);

				// add new game name to the list when they are available
				List<String> games = Arrays.asList("Magic The Gathering", "Pokemon");
				request.setAttribute("games", games);
			} else {
				// if card is not found
				request.setAttribute("error", "Card is not found.");
			}
		} else {
			// if serial number is invalid
			request.setAttribute("error", "Serial Number is invalid.");
		}

		String cardAddForm = "frontend/card_update.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(cardAddForm);
		dispatcher.forward(request, response);
	}
}
