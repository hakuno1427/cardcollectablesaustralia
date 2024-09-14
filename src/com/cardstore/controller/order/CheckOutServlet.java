package com.cardstore.controller.order;

import java.io.IOException;

import com.cardstore.entity.User;
import com.cardstore.service.OrderService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/checkout")
public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CheckOutServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			String checkOutPage = "frontend/login.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(checkOutPage);
			dispatcher.forward(request, response);
		}
		OrderService orderService = new OrderService(request, response);
		orderService.showCheckoutForm();
	}
}
