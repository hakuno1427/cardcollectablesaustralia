package com.cardstore.controller.admin;

import java.io.IOException;
import com.cardstore.service.AdminReviewServices;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author Sera Jeong 12211242 Created Date: 24/08/2024
 */

@WebServlet("/admin/review_manage")
public class ShowReviewManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ShowReviewManageServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		AdminReviewServices reviewServices = new AdminReviewServices(request, response);

		reviewServices.listReviews();

		String reviewManagePage = "/admin/review_manage.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(reviewManagePage);
		dispatcher.forward(request, response);
	}
}