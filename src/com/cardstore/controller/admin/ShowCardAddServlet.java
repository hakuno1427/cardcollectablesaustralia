package com.cardstore.controller.admin;

import java.io.IOException;

import com.cardstore.service.CardServices;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author Sera Jeong 12211242 Created Date: 22/08/2024
 */

@WebServlet("/admin/card_add")
public class ShowCardAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ShowCardAddServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        CardServices cardServices = new CardServices(request, response);

        cardServices.prepareCardAddPage();

        String cardAddForm = "/admin/card_add.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(cardAddForm);
        dispatcher.forward(request, response);
    }
}