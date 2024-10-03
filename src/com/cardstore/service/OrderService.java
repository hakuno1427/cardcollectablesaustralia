package com.cardstore.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.cardstore.controller.cart.ShoppingCart;
import com.cardstore.dao.ListingDAO;
import com.cardstore.dao.OrderDAO;
import com.cardstore.dao.OrderItemDAO;
import com.cardstore.dao.UserDAO;
import com.cardstore.entity.Listing;
import com.cardstore.entity.Order;
import com.cardstore.entity.OrderItem;
import com.cardstore.entity.User;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.ShippingAddress;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class OrderService {
	private OrderDAO orderDao;
	private ListingDAO listingDao;
	private OrderItemDAO orderItemDao;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private MessageService messageService;
	private PaymentService paymentService;
	private UserDAO userDAO;

	public OrderService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.orderDao = new OrderDAO();
		this.listingDao = new ListingDAO();
		this.orderItemDao = new OrderItemDAO();
		this.messageService = new MessageService(request, response);
		this.userDAO = new UserDAO();
		this.paymentService = new PaymentService(request, response);
	}

	public void showCheckoutForm() throws ServletException, IOException {
		HttpSession session = request.getSession();
		ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("cart");
		
		float subTotal = shoppingCart.getTotalAmount();

		float shippingFee = shoppingCart.getTotalQuantity() < 20 ? 8.0f : 0;

		float total = subTotal + shippingFee;

		session.setAttribute("total", total);
		session.setAttribute("subTotal", subTotal);
		session.setAttribute("shippingFee", shippingFee);
		String checkOutPage = "frontend/checkout.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(checkOutPage);
		dispatcher.forward(request, response);
	}

	public void placeOrder() throws ServletException, IOException {
		Order order = readOrderInfo();

		PaymentService paymentServices = new PaymentService(request, response);
		request.getSession().setAttribute("order4Paypal", order);
		paymentServices.authorizePayment(order);
	}

	private Integer saveOrder(Order order) {
		Order savedOrder = orderDao.create(order);

		// update listing quantity
		for (OrderItem orderItem : savedOrder.getOrderItems()) {
			Listing listing = orderItem.getListing();
			orderItem.setOrderId(savedOrder.getOrderId());
			orderItemDao.create(orderItem);
			listing.setQuantity(listing.getQuantity() - orderItem.getQuantity());

			if (listing.getQuantity() > 0) {
				listingDao.update(listing);
			} else
				listingDao.delete(listing.getListingId());
		}
		
		messageService.sendAdminMessage(order.getSellerId(), "Order Notification", "Someone bought from you. Please check your pending order page to see detail");

		ShoppingCart shoppingCart = (ShoppingCart) request.getSession().getAttribute("cart");
		shoppingCart.clear();
				
		return savedOrder.getOrderId();
	}

	private Order readOrderInfo() {
		User user = (User) request.getSession().getAttribute("user");

		String shippingAddress = String.format("%s\n%s\n%s %s", request.getParameter("streetAddress"),
				request.getParameter("city"), request.getParameter("state"), request.getParameter("zipcode"));
		
		ShippingAddress paypalShippingAddress = new ShippingAddress();
		String recipientName = user.getFirstName() + " " + user.getLastName();
		paypalShippingAddress.setRecipientName(recipientName)
					   .setPhone(""+user.getPhone())
					   .setLine1(request.getParameter("streetAddress"))
					   .setLine2("")
					   .setCity(request.getParameter("city"))
					   .setState(request.getParameter("state"))
					   .setCountryCode("AU")
					   .setPostalCode(request.getParameter("zipcode"));
		
		request.getSession().setAttribute("paypalShippingAddress4Paypal", paypalShippingAddress);

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
		float subTotal = (Float) session.getAttribute("subTotal");

		
		order.setTotalPrice(total);
		order.setSubTotal(subTotal);
		order.setShippingFee((Float) session.getAttribute("shippingFee"));
		order.setOrderDate(LocalDate.now());
		order.setStatus(Order.STATUS_SHIPMENT_PENDING);

		return order;
	}

	public void shipOrder() throws IOException, ServletException {
		Order order = orderDao.get(request.getParameter("orderId"));
		order.setTrackingNumber(request.getParameter("trackingNumber"));
		order.setStatus(Order.STATUS_SHIPPED);
		orderDao.update(order);
		
		messageService.sendMessage(order.getBuyerId(), "Order Number:" + order.getOrderId(), request.getParameter("message"));
		
		showSellerOrders();
	}
	
	public void showSellerOrders() throws IOException, ServletException {
		HttpSession session = request.getSession();
		User loggedInUser = (User) session.getAttribute("user");

		if (loggedInUser == null) {
			response.sendRedirect("login");
			return;
		}

		int buyerId = loggedInUser.getUserId();
		List<Order> userOrders = orderDao.findOrdersBySeller(buyerId);
		float totalEarning = 0;
		
		for (Order order : userOrders) {
			List<OrderItem> orderItems = orderItemDao.findWithNamedQuery("OrderItem.findByOrderId", "orderId",
					order.getOrderId());
			order.setOrderItems(orderItems);
			
			if (order.getStatus() == Order.STATUS_COMPLETE) {
				totalEarning += order.getTotalPrice();
			}
		}

		request.setAttribute("orders", userOrders);
		request.setAttribute("totalEarning", totalEarning);
		request.getRequestDispatcher("/frontend/view_seller_orders.jsp").forward(request, response);
	}

	public void createOrderPaypal() throws ServletException, IOException {
		Order order = (Order) request.getSession().getAttribute("order4Paypal");

		saveOrder(order);

		String message = "Thank you. Your order has been received. " + "The seller will contact you within a few days.";
		request.setAttribute("message", message);
		request.setAttribute("pageTitle", "Order Completed");

		String messagePage = "frontend/message.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(messagePage);
		dispatcher.forward(request, response);
	}
	
	public void markOrderAsDone(Order order) throws Exception {
		UserDAO userDAO = new UserDAO();
		
		User seller = userDAO.get(order.getSellerId());
		
		if (!PaypalConfig.getMode().equals("sandbox"))
		paymentService.sendPayout(seller.getEmail(), order.getTotalPrice(), "Payment for CCA order " + order.getOrderId());
		
		messageService.sendAdminMessage(seller.getUserId(), "Payment", String.format("We have paid you %.2f$ for order %d", order.getTotalPrice(), order.getOrderId()));
		order.setStatus(Order.STATUS_COMPLETE);
		orderDao.update(order);
	}

	public void confirmDelivery() throws Exception {
		Order completeOrder = orderDao.get(request.getParameter("orderId"));
		markOrderAsDone(completeOrder);
		
		HttpSession session = request.getSession();
		User loggedInUser = (User) session.getAttribute("user");

		if (loggedInUser == null) {
			response.sendRedirect("login");
			return;
		}

		int buyerId = loggedInUser.getUserId();
		List<Order> userOrders = orderDao.findOrdersByBuyer(buyerId);

		for (Order order : userOrders) {
			List<OrderItem> orderItems = orderItemDao.findWithNamedQuery("OrderItem.findByOrderId", "orderId",
					order.getOrderId());
			order.setOrderItems(orderItems);

		}

		request.setAttribute("orders", userOrders);
	}
	
	public void showOrders() throws ServletException, IOException {
		HttpSession session = request.getSession();
		User loggedInUser = (User) session.getAttribute("user");

		if (loggedInUser == null) {
			response.sendRedirect("login");
			return;
		}

		int buyerId = loggedInUser.getUserId();
		List<Order> userOrders = orderDao.findOrdersByBuyer(buyerId);

		for (Order order : userOrders) {
			List<OrderItem> orderItems = orderItemDao.findWithNamedQuery("OrderItem.findByOrderId", "orderId",
					order.getOrderId());
			order.setOrderItems(orderItems);
		}

		request.setAttribute("orders", userOrders);
		request.getRequestDispatcher("/frontend/view_orders.jsp").forward(request, response);
	}
}
