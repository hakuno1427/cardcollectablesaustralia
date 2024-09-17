package com.cardstore.controller.order;

import java.io.IOException;
import java.util.List;

import com.cardstore.dao.OrderDAO;
import com.cardstore.dao.OrderItemDAO;
import com.cardstore.entity.Order;
import com.cardstore.entity.OrderItem;
import com.cardstore.entity.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class ViewOrderServlet
 */
@WebServlet("/view_orders")
public class ViewOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderDAO orderDAO;
	private OrderItemDAO orderItemDAO;

	/**
	 * Default constructor.
	 */
	public ViewOrderServlet() {
		this.orderDAO = new OrderDAO();
		this.orderItemDAO = new OrderItemDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User loggedInUser = (User) session.getAttribute("user");

		if (loggedInUser == null) {
			response.sendRedirect("login");
			return;
		}

		int buyerId = loggedInUser.getUserId();
		List<Order> userOrders = orderDAO.findOrdersByBuyer(buyerId);

		for (Order order : userOrders) {
			List<OrderItem> orderItems = orderItemDAO.findWithNamedQuery("OrderItem.findByOrderId", "orderId",
					order.getOrderId());
			order.setOrderItems(orderItems);

		}

		request.setAttribute("orders", userOrders);
		request.getRequestDispatcher("/frontend/view_orders.jsp").forward(request, response);
	}

}
