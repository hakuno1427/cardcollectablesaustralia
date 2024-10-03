package com.cardstore.controller.cart;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.cardstore.dao.ListingDAO;
import com.cardstore.entity.Listing;

@WebServlet("/add_to_cart")
public class AddListingToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddListingToCartServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer listingId = Integer.parseInt(request.getParameter("listingId"));

		Object cartObject = request.getSession().getAttribute("cart");

		ShoppingCart shoppingCart = null;

		if (cartObject != null && cartObject instanceof ShoppingCart) {
			shoppingCart = (ShoppingCart) cartObject;
		} else {
			shoppingCart = new ShoppingCart();
			request.getSession().setAttribute("cart", shoppingCart);
		}

		ListingDAO listingDAO = new ListingDAO();
		Listing listing = listingDAO.get(listingId);
		
		int availableQuantity = listing.getQuantity();
	    int currentQuantityInCart = shoppingCart.getItems().getOrDefault(listing, 0);
	    
	    if (currentQuantityInCart < availableQuantity) {
	        shoppingCart.addItem(listing);
	    } else {
	        request.getSession().setAttribute("errorMessage", "You cannot add more than the available quantity.");
	    }


		String cartPage = request.getContextPath().concat("/cart");
		response.sendRedirect(cartPage);

	}

}
