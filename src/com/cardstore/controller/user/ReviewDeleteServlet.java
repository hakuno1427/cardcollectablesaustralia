package com.cardstore.controller.user;

import java.io.IOException;

import com.cardstore.service.CardServices;
import com.cardstore.service.ReviewServices;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author Sera Jeong 12211242 Created Date: 06/09/2024
 */

@WebServlet("/review_delete")
public class ReviewDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ReviewDeleteServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// use reviewservices for delete business logic
        ReviewServices reviewServices = new ReviewServices(request, response);
        reviewServices.deleteReview();
	}
}