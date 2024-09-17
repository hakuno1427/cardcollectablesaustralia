package com.cardstore.controller.cart;

import java.io.IOException;

import com.cardstore.dao.ListingDAO;
import com.cardstore.entity.Listing;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/remove_from_cart")
public class RemoveListingFromCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RemoveListingFromCartServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer listingId = Integer.parseInt(request.getParameter("listingId"));

		Object cartObject = request.getSession().getAttribute("cart");

		ShoppingCart shoppingCart = (ShoppingCart) cartObject;
		ListingDAO listingDAO = new ListingDAO();

		shoppingCart.removeItem(listingDAO.get(listingId));

		String cartPage = request.getContextPath().concat("/view_cart");
		response.sendRedirect(cartPage);
	}

}
