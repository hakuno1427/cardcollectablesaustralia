package com.cardstore.controller.user;

import java.io.IOException;
import java.util.List;

import com.cardstore.entity.User;
import com.cardstore.service.ReviewServices;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author Sera Jeong 12211242 Created Date: 03/09/2024
 */


@WebServlet("/review_add")
public class ShowReviewAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ShowReviewAddServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

        ReviewServices reviewServices = new ReviewServices(request, response);
        
        List<User> sellers = reviewServices.getSellers(); 
        request.setAttribute("sellers", sellers);

		String reviewAddPage = "/frontend/review_add.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(reviewAddPage);
		dispatcher.forward(request, response);
	}
}

