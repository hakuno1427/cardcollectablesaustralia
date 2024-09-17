package com.cardstore.service;

import com.cardstore.dao.CardDAO;
import com.cardstore.dao.ListingDAO;
import com.cardstore.entity.Card;
import com.cardstore.entity.Listing;
import com.cardstore.entity.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Rutvi
 */

public class ListingService {

	private ListingDAO listingDAO;

	private HttpServletRequest request;

	private HttpServletResponse response;

	private static final Logger logger = Logger.getLogger(ListingDAO.class.getName());

	public ListingService(HttpServletRequest request, HttpServletResponse response) {
		this.listingDAO = new ListingDAO();
		this.request = request;
		this.response = response;
	}

	public void addListing() throws ServletException, IOException {
		String serialNumber = request.getParameter("serialNumber");
		Listing listingBySerialNumber = listingDAO.findBySerialNumber(serialNumber);
		String message = "";
		if (listingBySerialNumber != null) {
			message = "Listing already added with serial number: " + serialNumber + "<br/>"
					+ "<a href='listings'>Click here</a> to check listing.";
		} else {
			HttpSession httpSession = request.getSession();
			User user = (User) httpSession.getAttribute("user");
			Integer userId = user.getUserId();

			Listing newListing = updateFieldsOfListing();
			newListing.setSeller(user);

			listingDAO.create(newListing);

			message = "You have added listing successfully.<br/>"
					+ "<a href='listings'>Click here</a> to check listing.";

		}

		String messagePage = "frontend/message.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(messagePage);
		request.setAttribute("message", message);
		requestDispatcher.forward(request, response);
	}

	public void updateListing() {

		String message = "";

		try {
			HttpSession httpSession = request.getSession();
			User user = (User) httpSession.getAttribute("user");
			Integer userId = user.getUserId();

			Listing listing = updateFieldsOfListing();
			listing.setListingId(Integer.valueOf(request.getParameter("listingId")));
			listing.setSeller(user);

			listingDAO.update(listing);

			message = "You have updated listing successfully.<br/>"
					+ "<a href='listings'>Click here</a> to check listing";

			String messagePage = "frontend/message.jsp";
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(messagePage);
			request.setAttribute("message", message);
			requestDispatcher.forward(request, response);

		} catch (Exception e) {
			logger.log(Level.SEVERE, "Failed to update listing.", e);
			throw new RuntimeException("Failed to update listing.", e);
		}

	}

	public void deleteListing() {
		String message = "";

		try {
			Integer listingId = Integer.valueOf(request.getParameter("listingId"));
			listingDAO.delete(listingId);

			message = "You have deleted listing successfully.<br/>"
					+ "<a href='listings'>Click here</a> to check listing";

			String messagePage = "frontend/message.jsp";
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(messagePage);
			request.setAttribute("message", message);
			requestDispatcher.forward(request, response);

		} catch (Exception e) {
			logger.log(Level.SEVERE, "Failed to delete listing.", e);
			throw new RuntimeException("Failed to delete listing.", e);
		}
	}

	private Listing updateFieldsOfListing() {
		Listing newListing = new Listing();
		CardDAO cardDAO = new CardDAO();
		Card card = cardDAO.get(request.getParameter("serialNumber"));
		newListing.setCard(card);
		newListing.setCondition(request.getParameter("condition"));
		newListing.setQuantity(Integer.valueOf(request.getParameter("quantity")));
		newListing.setPrice(Double.parseDouble(request.getParameter("price")));
		return newListing;
	}

}
