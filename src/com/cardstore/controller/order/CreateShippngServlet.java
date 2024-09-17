package com.cardstore.controller.order;

import java.io.IOException;

import com.cardstore.service.OrderService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ship_order")
public class CreateShippngServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CreateShippngServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		OrderService orderService = new OrderService(request, response);
		orderService.shipOrder();
	}

}
