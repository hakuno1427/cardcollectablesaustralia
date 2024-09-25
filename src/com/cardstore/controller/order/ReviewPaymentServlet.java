package com.cardstore.controller.order;

import java.io.IOException;


import com.cardstore.service.PaymentService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/review_payment")
public class ReviewPaymentServlet  extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ReviewPaymentServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PaymentService paymentService = new PaymentService(request, response);
		paymentService.reviewPayment();
	}

}