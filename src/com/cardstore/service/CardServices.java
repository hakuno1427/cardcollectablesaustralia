package com.cardstore.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import com.cardstore.dao.CardDAO;
import com.cardstore.dao.ListingDAO;
import com.cardstore.dao.UserDAO;
import com.cardstore.entity.Card;
import com.cardstore.entity.Listing;
import com.cardstore.entity.Permission;
import com.cardstore.entity.Role;
import com.cardstore.entity.User;

import jakarta.persistence.EntityManager;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author Sera Jeong 12211242 Created Date: 25/08/2024
 */

public class CardServices {
	public static final String VIEW_CARD_CARTALOGUE = "VIEW_CARD_CARTALOGUE";
	public static final String MANAGE_CARD_CARTALOGUE = "MANAGE_CARD_CARTALOGUE";

	private CardDAO cardDAO;
	private ListingDAO listingDAO;
	private UserDAO userDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public CardServices(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.cardDAO = new CardDAO();
		this.listingDAO = new ListingDAO();
		this.userDAO = new UserDAO();
	}

	public void listCards() throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");

		if (!this.hasPermission(user, VIEW_CARD_CARTALOGUE)) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "You do not have permission to access this page.");
			return;
		}

		int page = 1;
		int pageSize = 10;

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		int start = (page - 1) * pageSize;
		List<Card> listCards = cardDAO.listPaged(start, pageSize);
		long totalCards = cardDAO.count();
		int totalPages = (int) Math.ceil((double) totalCards / pageSize);
		int pageRange = 10;
		int startPage = Math.max(1, page - pageRange / 2);
		int endPage = Math.min(totalPages, startPage + pageRange - 1);

		if (endPage - startPage < pageRange) {
			startPage = Math.max(1, endPage - pageRange + 1);
		}

		request.setAttribute("listCards", listCards);
		request.setAttribute("currentPage", page);
		request.setAttribute("totalPages", totalPages);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
	}

	public void prepareCardAddPage() throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");

		if (!this.hasPermission(user, MANAGE_CARD_CARTALOGUE)) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "You do not have permission to access this page.");
			return;
		}

		// add new game names to the list when they are available
		List<String> games = Arrays.asList("Magic The Gathering", "Pokemon");
		request.setAttribute("games", games);
	}

	public void createCard() throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");

		if (!this.hasPermission(user, MANAGE_CARD_CARTALOGUE)) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "You do not have permission to access this page.");
			return;
		}

		String serialNumber = request.getParameter("serialNumber");
		String cardName = request.getParameter("cardName");
		String description = request.getParameter("description");
		String game = request.getParameter("game");
		String marketprice = request.getParameter("marketprice");
		String imageUrl = request.getParameter("imageUrl");

		Card card = new Card(cardName, game, serialNumber, description, Double.parseDouble(marketprice), imageUrl);
		cardDAO.create(card);

		response.sendRedirect(request.getContextPath() + "/admin/catalogue");
	}

	public void prepareCardUpdatePage() throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");

		if (!this.hasPermission(user, MANAGE_CARD_CARTALOGUE)) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "You do not have permission to access this page.");
			return;
		}

		// add new game names to the list when they are available
		List<String> games = Arrays.asList("Magic The Gathering", "Pokemon");
		request.setAttribute("games", games);

		String serialNumber = request.getParameter("id");
		Card card = cardDAO.get(serialNumber);

		if (card == null) {
			String message = "Could not find card with Serial Number " + serialNumber;
			request.setAttribute("message", message);
			listCards(); // Optionally redirect to the list page if card is not found
			return;
		}

		request.setAttribute("card", card);
	}

	public void updateCard(String serialNumber, String cardName, String description, String game, double marketPrice,
			String imageUrl) {
		Card card = cardDAO.get(serialNumber);
		if (card != null) {
			card.setCardName(cardName);
			card.setDescription(description);
			card.setGame(game);
			card.setMarketprice(marketPrice);
			card.setImageUrl(imageUrl);
			cardDAO.update(card);
		}
	}

	public void deleteCard() throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");

		if (!this.hasPermission(user, MANAGE_CARD_CARTALOGUE)) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "You do not have permission to access this page.");
			return;
		}

		String serialNumber = request.getParameter("id");
		if (serialNumber != null && !serialNumber.trim().isEmpty()) {
			cardDAO.delete(serialNumber);
			response.sendRedirect(request.getContextPath() + "/admin/catalogue");
		} else {
			String message = "Card ID is required for deletion.";
			request.setAttribute("message", message);
			listCards();
		}
	}
	
	public void search() throws ServletException, IOException {
		String keyword = request.getParameter("keyword");
		int page = 1;
	    int pageSize = 9;
	    
	    if (request.getParameter("page") != null) {
	        page = Integer.parseInt(request.getParameter("page"));
	    }
	    int start = (page - 1) * pageSize;
	    
		List<Card> result = null;
		
		if (keyword == null || keyword.trim().isEmpty()) {
			result = cardDAO.listPaged(start, pageSize);
	        long totalCards = cardDAO.count();
	        int totalPages = (int) Math.ceil((double) totalCards / pageSize);
	        request.setAttribute("totalPages", totalPages);
		} else {
			result = cardDAO.searchPaged(keyword, start, pageSize);
	        long totalResults = cardDAO.countSearchResults(keyword);
	        int totalPages = (int) Math.ceil((double) totalResults / pageSize);
	        request.setAttribute("totalPages", totalPages);
		}
		
		for (Card card : result) {
	        List<Listing> listings = listingDAO.findWithNamedQuery("Listing.findBySerialNumber", "serialNumber", card.getSerialNumber());
	        card.setListings(listings);
	    }
		
		request.setAttribute("result", result);
		request.setAttribute("keyword", keyword);
		request.setAttribute("currentPage", page);
		
		String resultPage = "frontend/search_result.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(resultPage);
		requestDispatcher.forward(request, response);
	}
	
	public void viewCardDetail() throws ServletException, IOException {
		String serialNumber = request.getParameter("serialNumber");
		Card card = cardDAO.get(serialNumber);
		
		if (card != null) {
	        List<Listing> listings = listingDAO.findWithNamedQuery("Listing.findBySerialNumber", "serialNumber", serialNumber);
	        
	        for (Listing listing : listings) {
	        	List<User> users = userDAO.findWithNamedQuery("User.findByUserId", "userId", listing.getSellerId());
	        	if (!users.isEmpty()) {
	        	    User user = users.get(0); 
	        	    listing.setSeller(user);
	        	}
	        }
	        
	        card.setListings(listings);
	        request.setAttribute("card", card);
	        
	        String detailPage = "frontend/card_detail.jsp";
	        RequestDispatcher requestDispatcher = request.getRequestDispatcher(detailPage);
	        requestDispatcher.forward(request, response);
	    } else {
	        response.sendError(HttpServletResponse.SC_NOT_FOUND, "Card not found");
	    }
		
	}

	private boolean hasPermission(User user, String permissionName) {
		if (user == null) {
			return false;
		}

		Role role = user.getRole();

		Set<Permission> permissions = role.getPermissions();
		for (Permission permission : permissions) {
			if (permission.getName().equals(permissionName)) {
				return true;
			}
		}

		return false;
	}
}