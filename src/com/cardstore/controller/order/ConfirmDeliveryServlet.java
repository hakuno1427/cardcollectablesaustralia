package com.cardstore.controller.order;

import java.io.IOException;
import java.util.List;

import com.cardstore.entity.Order;
import com.cardstore.entity.OrderItem;
import com.cardstore.entity.User;
import com.cardstore.service.OrderService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/confirm_delivery")
public class ConfirmDeliveryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ConfirmDeliveryServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		OrderService orderService = new OrderService(request, response);
		try {
			orderService.confirmDelivery();

			orderService.showOrders();
		} catch (Exception e) {
			e.printStackTrace();
			String messagePage = "frontend/message.jsp";
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(messagePage);
			request.setAttribute("message", "Some error happened while paying the seller, please notify the admin");
		}
	}

}
