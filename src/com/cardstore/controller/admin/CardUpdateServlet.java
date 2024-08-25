package com.cardstore.controller.admin;

import java.io.IOException;

import com.cardstore.service.CardServices;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author Sera Jeong 12211242 Created Date: 23/08/2024
 */

@WebServlet("/admin/card_update_save")
public class CardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CardUpdateServlet() {
		super();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String serialNumber = request.getParameter("serialNumber");
		String cardName = request.getParameter("cardName");
		String description = request.getParameter("description");
		String game = request.getParameter("game");
		double marketPrice = Double.parseDouble(request.getParameter("marketprice"));
		String imageUrl = request.getParameter("imageUrl");

		// use cardservices for update business logic
		CardServices cardServices = new CardServices(request, response);
		cardServices.updateCard(serialNumber, cardName, description, game, marketPrice, imageUrl);

		response.sendRedirect(request.getContextPath() + "/admin/catalogue");
	}
}