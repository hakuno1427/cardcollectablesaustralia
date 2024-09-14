package com.cardstore.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import com.cardstore.controller.cart.ShoppingCart;
import com.cardstore.dao.ListingDAO;
import com.cardstore.dao.OrderDAO;
import com.cardstore.entity.Listing;
import com.cardstore.entity.Order;
import com.cardstore.entity.OrderItem;
import com.cardstore.entity.User;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class OrderService {
	private OrderDAO orderDao;
	private ListingDAO listingDao;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public OrderService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.orderDao = new OrderDAO();
		this.listingDao = new ListingDAO();
	}

	public void showCheckoutForm() throws ServletException, IOException {
		HttpSession session = request.getSession();
		ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("cart");

		float shippingFee = shoppingCart.getTotalQuantity() < 20 ? 8.0f : 0;

		float total = shoppingCart.getTotalAmount() + shippingFee;

		session.setAttribute("total", total);
		session.setAttribute("shippingFee", shippingFee);

		String checkOutPage = "frontend/checkout.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(checkOutPage);
		dispatcher.forward(request, response);
	}

	public void placeOrder() throws ServletException, IOException {
		Order order = readOrderInfo();

		saveOrder(order);

		String message = "Thank you. Your order has been received. " + "The seller will contact you within a few days.";
		request.setAttribute("message", message);
		request.setAttribute("pageTitle", "Order Completed");

		String messagePage = "frontend/message.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(messagePage);
		dispatcher.forward(request, response);
	}

	private Integer saveOrder(Order order) {
		Order savedOrder = orderDao.create(order);

		// update listing quantity
		for (OrderItem orderItem : savedOrder.getOrderItems()) {
			Listing listing = orderItem.getListing();
			listing.setQuantity(listing.getQuantity() - orderItem.getQuantity());

			if (listing.getQuantity() > 0) {
				listingDao.update(listing);
			} else
				listingDao.delete(listing);
		}

		ShoppingCart shoppingCart = (ShoppingCart) request.getSession().getAttribute("cart");
		shoppingCart.clear();
				
		return savedOrder.getOrderId();
	}

	private Order readOrderInfo() {
		User user = (User) request.getSession().getAttribute("user");

		String shippingAddress = String.format("%s\n%s\n%s %s", request.getParameter("streetAddress"),
				request.getParameter("city"), request.getParameter("state"), request.getParameter("zipcode"));

		Order order = new Order();
		order.setBillingAddress(shippingAddress);
		order.setShippingAddress(shippingAddress);

		HttpSession session = request.getSession();
		order.setBuyerId(user.getUserId());

		ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("cart");
		Map<Listing, Integer> items = shoppingCart.getItems();

		Iterator<Listing> iterator = items.keySet().iterator();
		ArrayList<OrderItem> orderItems = new ArrayList<OrderItem>();

		while (iterator.hasNext()) {
			Listing listing = iterator.next();
			Integer quantity = items.get(listing);

			OrderItem orderItem = new OrderItem();
			orderItem.setListing(listing);
			orderItem.setOrderId(order.getOrderId());
			orderItem.setQuantity(quantity);
			orderItems.add(orderItem);
			order.setSellerId(listing.getSeller().getUserId());
		}
		order.setOrderItems(orderItems);

		float total = (Float) session.getAttribute("total");

		order.setTotalPrice(total);
		order.setOrderDate(LocalDate.now());
		order.setStatus(Order.STATUS_SHIPMENT_PENDING);

		return order;
	}
}