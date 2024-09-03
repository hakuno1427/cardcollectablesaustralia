package com.cardstore.controller.user;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author Sera Jeong 12211242 Created Date: 01/09/2024
 */

@WebServlet("/review_buyer")
public class ShowReviewBuyerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ShowReviewBuyerServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String reviewBuyerPage = "/frontend/review_buyer.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(reviewBuyerPage);
		dispatcher.forward(request, response);
	}
}
